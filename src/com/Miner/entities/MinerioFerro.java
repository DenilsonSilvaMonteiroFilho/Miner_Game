package com.Miner.entities;

import java.awt.image.BufferedImage;

import com.Miner.world.Camera;

public class MinerioFerro extends Entity{
	
	public String nome = "Ferro";
	public static int dropPorcent = 50;
	
	public MinerioFerro(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		this.mWidth = 13;
		this.mHeight = 10;
		this.width = this.getX() + maskx - Camera.x + 2;
		this.height = this.getY() + masky - Camera.y + 3;
	}
	
	public static int getDropPor() {
		return dropPorcent;
	}
}
