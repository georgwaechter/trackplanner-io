/*
 * BiblioTrack.java
 */

package com.trackplanner.io;

/** BiblioTrack class represents a track within one track library. Every track has
 *  a type (see TrackType enumeration) and contains an array of lines and/or curves.
 *
 * @author Georg Wächter
 */
public class BiblioTrack {
    
    private Curve[] curves;
    private Line[] lines;
    private String articleNumber;
    private TrackType type;
    private String remarks;
    private TrackConnector[] connectors;
    
    /** Creates a new instance of the BiblioTrack class. */
    public BiblioTrack() {
    }
    
    /** Gets the article number of the track. */
    public String getArticleNumber() {
        return this.articleNumber;
    }
    
    /** Sets the article number of the track. */
    protected void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }
    
    /** Gets the array of connectors. */
    public TrackConnector[] getConnectors() {
        return this.connectors;
    }
    
    /** Sets the array of connectors. */
    protected void setConnectors(TrackConnector[] connectors) {
        this.connectors = connectors;
    }
    
    /** Gets the array of curves. */
    public Curve[] getCurves() {
        return this.curves;
    }
    
    /** Sets the array of curves. */
    protected void setCurves(Curve[] curves) {
        this.curves = curves;
    }
    
    /** Gets the array of lines. */
    public Line[] getLines() {
        return this.lines;
    }
    
    /** Sets the array of lines. */
    protected void setLines(Line[] lines) {
        this.lines = lines;
    }
    
    /** Gets the track type. */
    public TrackType getType() {
        return this.type;
    }
    
    /** Sets the track type. */
    protected void setType(TrackType type) {
        this.type = type;
    }
    
    /** Gets the remarks of the track. */
    public String getRemarks() {
        return this.remarks;
    }
    
    /** Sets the remarks for the track. */
    protected void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
