package edu.grounded;

public enum Direction implements GroundedConstants {
	NW(NW_FLAGS), N ( N_FLAGS), NE(NE_FLAGS),
	W ( W_FLAGS), C ( C_FLAGS), E ( E_FLAGS),
	SW(SW_FLAGS), S ( S_FLAGS), SE(SE_FLAGS);

	// TODO is this necessary? or undesired? Too heavy, for now
//	private class AffectedIterator implements Iterator<Direction> {
//		private byte m_flags;
//		private Iterator<Direction> m_wrapped;
//		
//		public AffectedIterator(Direction changed) {
//			m_flags = changed.affected();
//			List<Direction> l = new LinkedList<Direction>();
//			for (Direction d : Direction.values()) {
//				if ((m_flags & d.flags) != 0) {
//					l.add(d);
//				}
//			}
//			m_wrapped = l.iterator();
//		}
//		
//		@Override
//		public boolean hasNext() {
//			if (m_flags == Direction.this.flags) {
//				return m_wrapped.hasNext();
//			}
//			return false;
//		}
//
//		@Override
//		public Direction next() {
//			if (m_flags == Direction.this.flags) {
//				return m_wrapped.next();
//			}
//			return null;
//		}
//
//		@Override
//		public void remove() {
//			if (m_flags == Direction.this.flags) {
//				m_wrapped.next();
//			}
//		}
//	}
	
	private byte flags;

	private Direction(byte b) {
		flags = b;
	}
	
	public byte getFlags() {
		return flags;
	}

	public Direction up() {
		switch (this) {
			case C : return N;
			case E : return NE;
			case SE: return E;
			case S : return C;
			case SW: return W;
			case W : return NW;
			default: return null;
		}
	}

	public Direction down() {
		switch (this) {
			case C : return S;
			case N : return C;
			case NE: return E;
			case E : return SE;
			case W : return SW;
			case NW: return W;
			default: return null;
		}
	}

	public Direction left() {
		switch (this) {
			case C : return W;
			case N : return NW;
			case NE: return N;
			case E : return C;
			case SE: return S;
			case S : return SW;
			default: return null;
		}
	}

	public Direction right() {
		switch (this) {
			case C : return E;
			case N : return NE;
			case S : return SE;
			case SW: return S;
			case W : return C;
			case NW: return N;
			default: return null;
		}
	}
	
	public Direction opposite() {
		switch (this) {
			case N : return S;
			case NE: return SW;
			case E : return W;
			case SE: return NW;
			case S : return N;
			case SW: return NE;
			case W : return E;
			case NW: return SE;
			default: return C;
		}
	}
	
	/**
	 * Returns all the Directions affected by a change to this one
	 * @return the union of these Directions' flags
	 */
	public byte affected() {
		byte a = 0;
		// Top row does not affect bottom row
		if (up() == null) {
			a |= (byte) (SW.flags | S.flags | SE.flags);
		}
		// Bottom row does not affect top row
		if (down() == null) {
			a |= (byte) (NW.flags | N.flags | NE.flags);
		}
		// Right row does not affect left row
		if (right() == null) {
			a |= (byte) (NW.flags | W.flags | SW.flags);
		}
		// Left row does not affect right row
		if (left() == null) {
			a |= (byte) (NE.flags | E.flags | SE.flags);
		}
		return (byte) ~a;
	}

	public int getX() {
		switch (this) {
			case NE: case E: case SE: return  1;
			case NW: case W: case SW: return -1;
			default: return 0;
		}
	}

	public int getY() {
		switch (this) {
			case SW: case S: case SE: return  1;
			case NW: case N: case NE: return -1;
			default: return 0;
		}
	}

	public boolean isCardinal() {
		return (this.ordinal() % 2 == 1);
	}
}
