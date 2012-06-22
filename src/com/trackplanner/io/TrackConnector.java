/*
 * TrackConnector.java
 */

package com.trackplanner.io;

/** TrackConnector class represents an connector at the end of one track.
 *
 * @author Georg Wächter
 */
public class TrackConnector {
    
    private java.awt.geom.Point2D.Float position;
    private float angle;
    
    /** Creates a new instance of the TrackConnector class. */
    public TrackConnector() {
    }
    
    /** Creates a new instance of the TrackConnector class with the specified parameters. */
    public TrackConnector(java.awt.geom.Point2D.Float position, float angle) {
        this.position = position;
        this.angle = angle;
        this.position.setLocation(this.position.getX() / 4.0f, this.position.getY() / 4.0f);
    }

    /** Gets the position of the connector. */
    public java.awt.geom.Point2D.Float getPosition() {
        return this.position;
    }
    
    /** Sets the position of the connector. */
    public void setPosition(java.awt.geom.Point2D.Float postion) {
        this.position = position;
    }
    
    /** Gets the direction angle of the connector. */
    public float getAngle() {
        return this.angle;
    }
    
    /* Sets the direction angle of the connector. */
    public void setAngle(float angle) {
        this.angle = angle;
    }
}
