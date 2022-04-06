package assignment3_thread;

import java.awt.Image;
import java.util.Random;

public class AlienSprite extends Sprite {
	private GalagaGame game;

	public AlienSprite(GalagaGame game, Image image, int x, int y) {
		super(image, x, y);
		this.game = game;
		dx = -2;
	}

	@Override
	public void move() {
		if (((dx < 0) && (x < 10)) || ((dx > 0) && (x > 800))) {
			dx = -dx;
			y += 10;
			if (y > 600) {
				game.endGame();
			}
		}
		// dy = (int) ((Math.random()-0.5)*10);
		x += dx;
		y += dy;
	}

}