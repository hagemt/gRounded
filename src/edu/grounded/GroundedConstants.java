package edu.grounded;

import java.awt.Dimension;

import javax.swing.SwingConstants;

public interface GroundedConstants extends SwingConstants {
	public static final String VERSION = "gRounded v. 0.1a";
	
	public static final Dimension WINDOW_SIZE = new Dimension(800, 600);
	
	public static final int MARGIN_SIZE = 5;
	
	public static final byte NW_FLAGS = (byte) 0x01;
	public static final byte  N_FLAGS = (byte) 0x80;
	public static final byte NE_FLAGS = (byte) 0x40;
	public static final byte  W_FLAGS = (byte) 0x02;
	public static final byte  C_FLAGS = (byte) 0xFF;
	public static final byte  E_FLAGS = (byte) 0x20;
	public static final byte SW_FLAGS = (byte) 0x04;
	public static final byte  S_FLAGS = (byte) 0x08;
	public static final byte SE_FLAGS = (byte) 0x10;
}
