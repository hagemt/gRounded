package edu.grounded;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Grounded extends JFrame implements GroundedConstants {
	private static final long serialVersionUID = 995477482707655533L;
	private static final Map<Direction, String> OPTIONS;
	private static final Border SHARED_BORDER;
	static {
		SHARED_BORDER = BorderFactory.createEmptyBorder(MARGIN_SIZE, 0, MARGIN_SIZE, 0);
		OPTIONS = new EnumMap<Direction, String>(Direction.class);
		OPTIONS.put(Direction.N, "Schedule");
		OPTIONS.put(Direction.E, "Connect");
		OPTIONS.put(Direction.S, "Options");
		OPTIONS.put(Direction.W, "Notebook");
		OPTIONS.put(Direction.C, VERSION);
	}

	private GridMenuPanel grid_menu;
	private Map<String, Option> option_map;
	
	public Grounded() {
		// Setup transparency, if possible
		// Is JDK 7.0, Dolphin feature
		
		// Setup components
		option_map = new HashMap<String, Option>();
		for (String label : OPTIONS.values()) {
			option_map.put(label, new Option(label));
		}
		grid_menu = new GridMenuPanel(option_map.get(OPTIONS.get(Direction.C)));
		this.addKeyListener(grid_menu);
		
		// Prepare for display
		JPanel content = new JPanel();
		content.setBorder(SHARED_BORDER);
		content.add(grid_menu);
		this.getContentPane().add(content);
		this.setTitle(VERSION);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		
		// Center the window
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
		int sh, sw, wh, ww;
		sh = (int) screen_size.getHeight();
		sw = (int) screen_size.getWidth();
		wh = (int) WINDOW_SIZE.getHeight();
		ww = (int) WINDOW_SIZE.getWidth();
		this.setBounds((sw - ww) / 2, (sh - wh) / 2, ww, wh);
	}
	
	public static void main(String... args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() { new Grounded().setVisible(true); }
		});
	}
}
