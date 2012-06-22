/*
 * Track.java
 */

package com.trackplanner.io;

/** Track class represents one track within a track layout.
 *
 * @author Georg Wächter
 */
public class Track {
    
    private Track[] connectedTracks;
    private BiblioTrack biblioTrack;
    private float angle;
    private java.awt.geom.Point2D.Float position;
    private boolean tunnel;
    
    /** Creates an instance of the Track class. */
    public Track(BiblioTrack biblioTrack) {
        this.biblioTrack = biblioTrack;
        this.connectedTracks = new Track[biblioTrack.getConnectors().length];
    }
    
    /** Checks whether the track is in a tunnel. */
    public boolean isInTunnel() {
        return this.tunnel;
    }
    
    /** Sets the tunnel property. */
    public void setTunnel(boolean inTunnel) {
        this.tunnel = inTunnel;
    }
    
    /** Returns the type of the track, namely the so called BiblioTrack, that defines a track. */
    public BiblioTrack getBiblioTrack() {
        return this.biblioTrack;
    }
    
    /** Gets the position. */
    public java.awt.geom.Point2D.Float getPosition() {
        return this.position;
    }
    
    /** Sets the position. */
    public void setPosition(java.awt.geom.Point2D.Float position) {
        this.position = position;
    }
    
    /** Gets the track angle. */
    public float getAngle() {
        return this.angle;
    }
    
    /** Sets the track angle. */
    public void setAngle(float angle) {
        this.angle = angle;
    }
    
    /** Sets the array of the connected tracks. */
    public void setConnectedTracks(Track[] connectedTracks) {
        this.connectedTracks = connectedTracks;
    }
    
    /** Returns all tracks, that are connected with this instance. The array may contain
     *  null references. */
    public Track[] getConnectedTracks() {
        return this.connectedTracks;
    }
}
