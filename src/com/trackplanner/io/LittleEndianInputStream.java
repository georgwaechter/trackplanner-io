/*
 * LittleEndianInputStream.java
 */

package com.trackplanner.io;

import java.io.*;

/** LittleEndianInputStream Klasse.
 *
 * @author Georg Wächter
 */
public class LittleEndianInputStream extends DataInputStream {
    
    /** Creates a new instance of the LittleEndianInputStream. */
    public LittleEndianInputStream(InputStream in) {
        super(in);
    }
    
    /* Reads a little endian int from the stream. */
    public int readLEInt() throws IOException {
        int read1 = super.read();
        int read2 = super.read();
        int read3 = super.read();
        int read4 = super.read();

        if (read1 == -1) 
            throw new EOFException();
        
        return (int)((read4 << 24) + (read3 << 16) + (read2 << 8) + read1);
        //return (int) (read4 << 24) + ((read3 << 24) >>> 8) + ((read2 << 24) >>> 16) + ((read1 << 24) >>> 24);
    }
    
    /** Read a little endian float from the stream. */
    public float readLEFloat() throws IOException {
        return Float.intBitsToFloat(readLEInt());
    }
    
    /** Reads a little endian short from the stream. */
    public short readLEShort() throws IOException {
        int read1 = super.read();
        int read2 = super.read();

        if (read1 == -1) 
            throw new EOFException();
        
        return (short) (read1 + (read2 << 8));
    }
    
    /** Reads a fixed-length string from the stream. */
    public String readFixedLengthString(int length) throws IOException {
        byte[] temp = new byte[length];
        int read = super.read(temp, 0, length);
        
        if (read < length)
            throw new EOFException();
        
        return new String(temp, 0, length, "ISO-8859-1").trim();
    }
}
