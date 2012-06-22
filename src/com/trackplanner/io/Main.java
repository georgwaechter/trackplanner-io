/*
 * Main.java
 *
 * Created on 4. M�rz 2007, 00:29
 */

package com.trackplanner.io;

import java.io.IOException;

/**
 *
 * @author Georg W�chter
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
        try {
            // Gleisbibliotheken m�ssen im gleichem Verzeichnis liegen
            TrackLayout layout = TrackLayout.loadFromFile("C:\\x.glp");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
