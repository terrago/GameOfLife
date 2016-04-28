package de.terrago.gameOfLive.service.enums;

public enum ArenaModifierEnum {
	EMPTY("empty")
	, BLINKER("blinker")
	, DOUBLEU("double-u")
	, LWSS("lwss")
	, RPENTOMINO("r-pentomino");

	private final String key;

	private ArenaModifierEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		return key;
	}
}
