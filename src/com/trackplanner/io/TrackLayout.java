/*
 * TrackLayout.java
 */

package com.trackplanner.io;

import java.io.*;

/** TrackLayout class represents one track layout (.glp file).
 *
 * @author Georg Wächter
 */
public class TrackLayout {
    
    private String author;
    private float dimensionsX;
    private float dimensionsY;
    private Track[] tracks;
    private TrackLibrary[] biblios;
    private String path;
    
    /** Creates a new instance of the TrackLayout class. */
    private TrackLayout() {
    }
    
    /** Gets the path of the track layout. */
    public String getPath() {
        return this.path;
    }
     
    /** Sets the path of the track layout. */
    protected void setPath(String path) {
        this.path = path;
    }
    
    /** Sets the tracks array. */
    protected void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }
    
    /** Gets all tracks. */
    public Track[] getTracks() {
        return this.tracks;
    }
    
    /** Sets the needed biblios. */
    protected void setBiblios(TrackLibrary[] biblios) {
        this.biblios = biblios;
    }
    
    /** Gets all biblios used by this layout. */
    public TrackLibrary[] getBiblios() {
        return this.biblios;
    }
    
    /** Returns the author of the track layout. */
    public String getAuthor() {
        return this.author;
    }
    
    /** Sets the author of the track layout. */
    protected void setAuthor(String author) {
        this.author = author;
    }
    
    /** Gets the horizontal dimensions of the layout. */
    public float getXDimensions() {
        return this.dimensionsX;
    }
    
    /** Gets the vertical dimensions of the layout. */
    public float getYDimensions() {
        return this.dimensionsY;
    }
    
    /** Sets the horizontal dimensions of the layout. */
    protected void setXDimensions(float xDimensions) {
        this.dimensionsX = xDimensions;
    }
    
    /** Sets the vertical dimensions of the layout. */
    protected void setYDimensions(float yDimensions) {
        this.dimensionsY = yDimensions;
    }
    
    /** Loads a track layout from a file. */
    public static TrackLayout loadFromFile(String path) throws IOException {
       FileInputStream file = null;
       LittleEndianInputStream stream = null;
       
        try {
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            return null;
        } catch (SecurityException e) {
            return null;
        }
        
        try
        {
            stream = new LittleEndianInputStream(file);
            TrackLayout layout = new TrackLayout();
            layout.setPath(path);
            
            // read ID an compare                 
            if (!stream.readFixedLengthString(80).equals("Railroad Construction Pack Gleisplan"))
                throw new IOException("Not a valid track layout!");
            
            // skip needed program version
            stream.skip(4);
            
            // read some statistics
            int countBibliosNeeded = (int)stream.readLEShort() + 1;
            int countTracks = (int)stream.readLEShort() + 1;
            stream.skip(2); // discard count of layers
            
            // read author
            layout.setAuthor(stream.readFixedLengthString(50));
            
            int countConnectors = (int)stream.readLEShort() + 1;
            stream.skip(18); // skip count text boxes, count of dimensions, count of polys, ...
                            
            layout.setXDimensions(stream.readLEFloat() / 4.0f + 10.0f);
            layout.setYDimensions(stream.readLEFloat() / 4.0f + 10.0f);
            
            stream.skip(22); // unused bytes
             
            layout.setBiblios(new TrackLibrary[countBibliosNeeded]);
            layout.setTracks(new Track[countTracks]);
            readTracks(stream, layout);
            
            return layout;
        } finally {
            if (stream != null)
                stream.close();
            
            if (file != null)
                file.close();
        }     
    }
    
    /** Reads all tracks. */
    protected static void readTracks(LittleEndianInputStream stream, TrackLayout layout) throws IOException {
        int countBibliosNeeded = layout.getBiblios().length;
        int countTracks = layout.getTracks().length;
        String libraryPath = new File(layout.getPath()).getParent();
                
        short[] biblioNumbers = new short[countBibliosNeeded];
        TrackLibrary[] biblios = layout.getBiblios();
        Track[] tracks = layout.getTracks();
       
        // read biblio numbers
        for (int i = 0; i < countBibliosNeeded; i++)
            biblioNumbers[i] = stream.readLEShort();
        
        // read biblio names
        for (int i = 0; i < countBibliosNeeded; i++)
            biblios[i] = TrackLibrary.loadFromFile(libraryPath + stream.readFixedLengthString(20) + ".bbl");
       
        // create biblio indices
        int maxBiblio = 0;
        
        for (short biblioNumber : biblioNumbers)
            if (biblioNumber > maxBiblio)
                maxBiblio = biblioNumber;
        
        int[] biblioIndices = new int[maxBiblio + 1];
        
        for (int i = 0; i < biblioNumbers.length; i++)
            biblioIndices[biblioNumbers[i]] = i;
           
        // read tracks
        for (int i = 0; i < countTracks; i++) {
            int library = stream.readLEShort();
            int index = stream.readLEShort() - 1;
            float posX = stream.readLEFloat();
            float posY = stream.readLEFloat();
            
            tracks[i] = new Track(biblios[biblioIndices[library]].getTracks()[index]);
            tracks[i].setPosition(new java.awt.geom.Point2D.Float(posX / 4.0f, posY / 4.0f));
            stream.skip(2);
            short j = stream.readLEShort();
            tracks[i].setAngle((float)(int)j);
            stream.skip(4);
            tracks[i].setTunnel(stream.readBoolean());     
            stream.skip(12);
        }

        // read connectors and connect tracks
        for (Track track : tracks) {
            for (int i = 0; i < track.getConnectedTracks().length; i++) {             
                byte connected = stream.readByte();
                int trackIndex = (int)stream.readLEShort();
                byte connectorIndex = stream.readByte();
                
                if (connected == -1)
                    tracks[trackIndex].getConnectedTracks()[connectorIndex] = track;
            }
        }
    }
}
