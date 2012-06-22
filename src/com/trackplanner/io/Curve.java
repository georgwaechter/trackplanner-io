/*
 * Curve.java
 */

package com.trackplanner.io;

/** Curve class represents a curve.
 *
 * @author Georg Wächter
 */
public class Curve {
    
    private float radius;
    private java.awt.geom.Point2D.Float center;
    private TrackElement color;
    private float startAngle;
    private float endAngle;
    
    /** Creates a new instance of the Curve class. */
    public Curve() {
    }
    
    /** Creates a new instance of the Curve class with the specified parameters. */
    public Curve(float radius, java.awt.geom.Point2D.Float center, TrackElement color,
            float startAngle, float endAngle) {
        // remarks: the coords within the file are multiplied by 4
        this.radius = radius / 4.0f;
        this.center = center;
        this.center.setLocation(this.center.getX() / 4.0f, this.center.getY() / 4.0f);
        this.color = color;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
    }

    /** Gets the start angle. */
    public float getStartAngle() {
        return this.startAngle;
    }
    
    /** Sets the start angle. */
    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }
    
    /** Gets the end angle. */
    public float getEndAngle() {
        return this.endAngle;
    }
    
    /** Sets the end angle. */
    public void setEndAngle() {
        this.endAngle = endAngle;
    }
    
    /** Gets the color of the curve. */
    public TrackElement getColor() {
        return this.color;
    }
    
    /** Sets the color of the curve. */
    public void setColor(TrackElement color) {
        this.color = color;
    }
    
    /** Gets the center of the curve. */
    public java.awt.geom.Point2D.Float getCenter() {
        return this.center;
    }
    
    /** Sets the center of the curve. */
    public void setCenter(java.awt.geom.Point2D.Float center) {
        this.center = center;
    }
    
    /** Gets the radius. */
    public float getRadius() {
        return this.radius;
    }
    
    /** Sets the radius. */
    public void setRadius(float radius) {
        this.radius = radius;
    }
}
