/*
 * TrackType.java
 */

package com.trackplanner.io;

/** TrackType enumeration defines the type of a track.
 *
 * @author Georg Wächter
 */
public enum TrackType { 
   Curve,
   Straight,
   Flexible,
   SwitchLeft,
   SwitchRight,
   CurvedSwitchLeft,
   CurvedSwitchRight,
   SwitchY,
   ThreeWaySwitch,
   CrossingSwitch,
   DoubleCrossingSwitch,
   Crossing
}