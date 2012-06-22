/*
 * TrackLibrary.java
 */

package com.trackplanner.io;

import java.io.*;

/** TrackLibrary Klasse.
 *
 * @author Georg Wächter
 */
public final class TrackLibrary {
    
    private String name;
    private BiblioTrack[] tracks;
    
    /** Creates a new instance of the TrackLibrary class. */
    private TrackLibrary() {
    }
    
    /** Returns the library name. */
    public String getName() {
        return this.name;
    }
    
    /** Sets the name of the library. */
    protected void setName(String name) {
        this.name = name;
    }
    
    /** Returns the count of all tracks within the track library. */
    public int getCountTracks() {
        if (this.tracks != null)
            return this.tracks.length;
        else
            return 0;
    }
    
    public BiblioTrack[] getTracks() {
        return this.tracks;
    }
    
    protected void setTracks(BiblioTrack[] tracks) {
        this.tracks = tracks;
    }
       
    /** Reads one track library from the specified file. The method
        returns a reference to a TrackLibrary instance or null of the file was not
        found.
        An IOException is raised if the file is corrupt or too small or something else. */
    public static TrackLibrary loadFromFile(String path) throws IOException {
       FileInputStream file = null;
       LittleEndianInputStream stream = null;
       
       file = new FileInputStream(path);
        
        try
        {
            stream = new LittleEndianInputStream(file);
            TrackLibrary library = new TrackLibrary();
            
            // read ID an compare                 
            if (!stream.readFixedLengthString(25).equals("RCPack Gleisbibliothek"))
                throw new IOException("Not a valid track library!");
            
            // read name
            library.setName(stream.readFixedLengthString(20));
            
            // discard version and needed program version
            float vers = stream.skip(9);
            
            // read count of tracks
            int countTracks = (int)stream.readLEShort();
            stream.skip(4);
            
            library.setTracks(new BiblioTrack[countTracks]);
            readTracks(stream, library);
            
            return library;
        } finally {
            if (stream != null)
                stream.close();
            
            if (file != null)
                file.close();
        }     
    }
    
    /** Reads all tracks into the library. */
    protected static void readTracks(LittleEndianInputStream stream, TrackLibrary library) throws IOException {
        BiblioTrack[] tracks = library.getTracks();
        
        for (int i = 0; i < tracks.length; i++) {
            tracks[i] = new BiblioTrack();
            
            // read remarks and article number
            tracks[i].setRemarks(stream.readFixedLengthString(50));
            tracks[i].setArticleNumber(stream.readFixedLengthString(10));
            
            // read type
            tracks[i].setType(TrackType.values()[(int)stream.readByte()]);
            stream.readLEFloat(); // discard price
            int countLines = (int)stream.readLEShort() + 1;
            stream.skip(2);
            boolean hasLines = stream.readBoolean();
            stream.skip(1);
            int countCurves = (int)stream.readLEShort() + 1;
            stream.skip(1);
            boolean hasCurves = stream.readBoolean();
            stream.skip(1);
            stream.readLEFloat(); // radius
            stream.readLEFloat(); // width
            stream.readLEFloat(); // height
            stream.readLEFloat(); // length
            byte rail = stream.readByte(); // rail number
            stream.readLEFloat(); // angle
            tracks[i].setConnectors(new TrackConnector[(int)stream.readByte() + 1]);
            
            for (int n = 0; n < tracks[i].getConnectors().length; n++) {
                float x = stream.readLEFloat();
                float y = stream.readLEFloat();
                
                tracks[i].getConnectors()[n] = new TrackConnector(new java.awt.geom.Point2D.Float(x, y), stream.readLEFloat());
            }
            
            if (hasLines) {
                tracks[i].setLines(new Line[countLines]);
                
                for (int n = 0; n < tracks[i].getLines().length; n++) {
                    float x = stream.readLEFloat();
                    float y = stream.readLEFloat();
                    
                    java.awt.geom.Point2D.Float start = new java.awt.geom.Point2D.Float(x, y);
                    
                    x = stream.readLEFloat();
                    y = stream.readLEFloat();
                    
                    tracks[i].getLines()[n] = new Line(start,  new java.awt.geom.Point2D.Float(x, y),
                      TrackElement.values()[(int)stream.readByte()]);
                }
            }
            
            if (hasCurves) {
                tracks[i].setCurves(new Curve[countCurves]);
                
                for (int n = 0; n < tracks[i].getCurves().length; n++) {
                    float radius = stream.readLEFloat();
                    float centerX = stream.readLEFloat();
                    float centerY = stream.readLEFloat();
                    float startAngle = stream.readLEFloat();
                    float endAngle = stream.readLEFloat();   
                    
                    tracks[i].getCurves()[n] = new Curve(radius, new java.awt.geom.Point2D.Float(centerX,
                            centerY), TrackElement.values()[(int)stream.readByte()], startAngle, endAngle);
                }
            }      
        }
    }
    
}
