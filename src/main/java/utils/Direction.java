package utils;

public enum Direction {
	S('S'),
	E('E'),
	N('N'),
	O('O');
	@SuppressWarnings("unused")
	private final char direction;

	Direction(char direction) {
		this.direction = direction;
	}

}
