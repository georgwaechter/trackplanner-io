/*
 * Line.java
 */

package com.trackplanner.io;

/** Line class represents a line within a track.
 *
 * @author Georg Wächter
 */
public class Line {
    
    private java.awt.geom.Point2D.Float start;
    private java.awt.geom.Point2D.Float end;
    private TrackElement color;
   
    /** Creates an instance of the Line class. */
    public Line() {
    }
    
    /** Creates an instance of the Line class with the specified parameters. */
    public Line(java.awt.geom.Point2D.Float start, java.awt.geom.Point2D.Float end, TrackElement color) {
        // remarks: the coords within the file are multiplied by 4
        this.start = start;
        this.end = end;
        this.start.setLocation(this.start.getX() / 4.0f, this.start.getY() / 4.0f);
        this.end.setLocation(this.end.getX() / 4.0f, this.end.getY() / 4.0f);
        this.color = color;
    }
    
    /** Sets the line color. */
    public void setColor(TrackElement color) {
        this.color = color;
    }
    
    /** Gets the line color. */
    public TrackElement getColor() {
        return this.color;
    }
    
    /** Sets the start point of the line. */
    public void setStartPoint(java.awt.geom.Point2D.Float start) {
        this.start = start;
    }
    
    /** Gets the start point of line. */
    public java.awt.geom.Point2D.Float getStartPoint() {
        return this.start;
    }
    
    /** Sets the end point of the line. */
    public void setEndPoint(java.awt.geom.Point2D.Float end) {
        this.end = end;
    }
    
    /** Gets the end point of the line. */
    public java.awt.geom.Point2D.Float getEndPoint() {
        return this.end;
    }
}
