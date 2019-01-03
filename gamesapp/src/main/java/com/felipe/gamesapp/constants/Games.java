package com.felipe.gamesapp.constants;

public enum Games {
	IVORIES_OF_LIERS(1);

	private final int id;

	Games(int id) {
		this.id = id;
	}

	public int getValue() {
		return id;
	}
}
