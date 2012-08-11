package edu.grounded;

import java.awt.Dimension;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Option extends JPanel implements GroundedConstants, Iterable<Entry<Direction, Option>> {
	private static final long serialVersionUID = 1L;
	private String label;
	private Map<Direction, Option> children;
	private TitledBorder border;
	
	private static final Border SHARED_BORDER;
	private static final Dimension SHARED_SIZE;
	static {
		SHARED_BORDER = BorderFactory.createEtchedBorder();
		SHARED_SIZE = new Dimension(
				WINDOW_SIZE.width / 3 - MARGIN_SIZE * MARGIN_SIZE,
				WINDOW_SIZE.height / 3 - MARGIN_SIZE * MARGIN_SIZE);
	}
	
	public Option(String s) {
		this(null, null, null, null);
		label = s;
		border = BorderFactory.createTitledBorder(SHARED_BORDER);
		border.setTitle(label);
		border.setTitlePosition(TitledBorder.ABOVE_TOP);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
		this.setPreferredSize(SHARED_SIZE);
	}
	
	private Option(Option n, Option e, Option s, Option w) {
		children = new EnumMap<Direction, Option>(Direction.class);
		children.put(Direction.N, n);
		children.put(Direction.E, e);
		children.put(Direction.S, s);
		children.put(Direction.W, w);
	}
	
	public Option getChild(Direction d) {
		return (d != null) ? children.get(d) : null;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Option setChild(Direction d, Option n) {
		if (children.containsKey(d)) {
			return children.put(d, n);
		}
		return null;
	}

	@Override
	public Iterator<Entry<Direction, Option>> iterator() {
		return children.entrySet().iterator();
	}
}
