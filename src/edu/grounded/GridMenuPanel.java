package edu.grounded;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class GridMenuPanel extends JPanel implements KeyListener, GroundedConstants {
	private static final long serialVersionUID = -110366462407207495L;
	private static final Map<Integer, Direction> KEY_BINDINGS;
	static {
		KEY_BINDINGS = new HashMap<Integer, Direction>();
		KEY_BINDINGS.put(KeyEvent.VK_UP, Direction.N);
		KEY_BINDINGS.put(KeyEvent.VK_RIGHT, Direction.E);
		KEY_BINDINGS.put(KeyEvent.VK_DOWN, Direction.S);
		KEY_BINDINGS.put(KeyEvent.VK_LEFT, Direction.W);
	}
	
	private Option head, current;
	private Map<Direction, Option> options;
	
	public GridMenuPanel(Option root) {
		this.setLayout(new GridLayout(3, 3, MARGIN_SIZE, MARGIN_SIZE));
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.add(new Option(Integer.toString(i * 3 + j)));
			}
		}
		
		options = new EnumMap<Direction, Option>(Direction.class);
		for (Direction d : Direction.values()) {
			options.put(d, null);
		}
		
		head = current = root;
		if (head != null) {
			for (Entry<Direction, Option> e : head) {
				options.put(e.getKey(), e.getValue());
			}
		}

//		// BFS
//		Queue<Option> q = new LinkedList<Option>();
//		Set<Option> m = new HashSet<Option>();
//		q.offer(head);
//		while (!q.isEmpty()) {
//			Option o = q.poll();
//			if (m.add(o)) {
//				System.out.println(o.getLabel());
//				for (Direction d : Direction.values()) {
//					Option c = o.getChild(d);
//					if (c != null) {
//						q.offer(c);
//					}
//				}
//			}
//		}
		
		
	}
	
//	private void setupChild(int x, int y, Direction d) {
//		int dx = x + d.getX();
//		int dy = y + d.getY();
//		options[x][y].setChild(d, options[dx][dy]);
//	}
	
//	private void setupChildren(int x, int y) {
//		if (x >= 0 && x < 3 && y >= 0 && y < 3 && panels[x][y] != null) {
//			for (Entry<Direction, Option> e : panels[x][y]) {
//				Direction d = e.getKey();
//				int dx = x + d.getX();
//				int dy = y + d.getY();
//				panels[x][y].setChild(d, panels[dx][dy]);
//				setupChildren(dx, dy);
//			}
//		}
//	}
	
	public Option setPanel(Direction d, Option o) {
		Option old = null;
		if (d != null && o != null) {
			old = options.get(d);
			if (o != old) {
				byte flags = d.affected();
				for (Direction n : Direction.values()) {
					if ((flags & n.getFlags()) != 0) {
						Option child = o.getChild(n);
						if (child != null) {
							int i = n.ordinal();
							this.remove(i);
							this.add(o, i);
						}
						options.put(n, child);
					}
				}
				this.validate();
				current = options.get(Direction.C);
			}
		}
		return old;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Direction d = KEY_BINDINGS.get(e.getKeyCode());
		if (d != null) {
			this.setPanel(Direction.C, current.getChild(d));
		}
	}
	@Override public void keyReleased(KeyEvent e) { }
	@Override public void keyTyped(KeyEvent e) { }
}
