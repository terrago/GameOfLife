package de.terrago.gol.service;

import de.terrago.gol.model.Arena;

public class ArenaModifierService {

	public Arena getBlinker(Arena arena, int startingPointX, int startingPointY) {
		arena.setPoint(startingPointX + 0, startingPointY + 0, true);
		arena.setPoint(startingPointX + 0, startingPointY + 1, true);
		arena.setPoint(startingPointX + 0, startingPointY + 2, true);
		return arena;
	}

	public Arena getDoubleU(Arena arena, int startingPointX, int startingPointY) {
		arena.setPoint(startingPointX + 0, startingPointY + 0, true);
		arena.setPoint(startingPointX + 1, startingPointY + 0, true);
		arena.setPoint(startingPointX + 2, startingPointY + 0, true);
		arena.setPoint(startingPointX + 0, startingPointY + 1, true);
		arena.setPoint(startingPointX + 2, startingPointY + 1, true);
		arena.setPoint(startingPointX + 0, startingPointY + 2, true);
		arena.setPoint(startingPointX + 2, startingPointY + 2, true);
		arena.setPoint(startingPointX + 0, startingPointY + 4, true);
		arena.setPoint(startingPointX + 2, startingPointY + 4, true);
		arena.setPoint(startingPointX + 0, startingPointY + 5, true);
		arena.setPoint(startingPointX + 2, startingPointY + 5, true);
		arena.setPoint(startingPointX + 0, startingPointY + 6, true);
		arena.setPoint(startingPointX + 1, startingPointY + 6, true);
		arena.setPoint(startingPointX + 2, startingPointY + 6, true);
		return arena;
	}

	public Arena getLwss(Arena arena, int startingPointX, int startingPointY) {
		arena.setPoint(startingPointX + 1, startingPointY + 0, true);
		arena.setPoint(startingPointX + 2, startingPointY + 0, true);
		arena.setPoint(startingPointX + 3, startingPointY + 0, true);
		arena.setPoint(startingPointX + 4, startingPointY + 0, true);
		arena.setPoint(startingPointX + 0, startingPointY + 1, true);
		arena.setPoint(startingPointX + 4, startingPointY + 1, true);
		arena.setPoint(startingPointX + 4, startingPointY + 2, true);
		arena.setPoint(startingPointX + 0, startingPointY + 3, true);
		arena.setPoint(startingPointX + 3, startingPointY + 3, true);
		return arena;
	}

	public Arena getRPentomino(Arena arena, int startingPointX, int startingPointY) {
		arena.setPoint(startingPointX - 1, startingPointY + 1, true);
		arena.setPoint(startingPointX + 0, startingPointY + 0, true);
		arena.setPoint(startingPointX + 0, startingPointY + 1, true);
		arena.setPoint(startingPointX + 0, startingPointY + 2, true);
		arena.setPoint(startingPointX + 1, startingPointY + 0, true);
		return arena;
	}
}
