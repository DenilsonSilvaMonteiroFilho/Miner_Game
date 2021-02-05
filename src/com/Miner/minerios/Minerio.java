package com.Miner.minerios;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Miner.main.Game;
import com.Miner.world.Camera;

public class Minerio {
	
	public static BufferedImage MINERIO_FERRO = Game.spritesheet.getSprite(64, 16, 16, 16);
	
	private int x;
	private int y;
	private int dropChance;
	private BufferedImage sprite;
	
	public Minerio(int x, int y, int dropChance, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.dropChance = dropChance;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

	public int getDropChance() {
		return dropChance;
	}

	public void setDropChance(int newDropChance) {
		this.dropChance = newDropChance;
	}
}
