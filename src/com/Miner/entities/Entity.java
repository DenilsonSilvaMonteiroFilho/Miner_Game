package com.Miner.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.Miner.entities.Entity;
import com.Miner.main.Game;
import com.Miner.world.Camera;

public class Entity {
	
	public static BufferedImage MINERIO_FERRO = Game.spritesheet.getSprite(64, 16, 16, 16);
	
	public int maskx, masky, mWidth, mHeight;

	protected double x;
	protected double y;
	protected double z;
	protected int width;
	protected int height;
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mWidth = width;
		this.mHeight = height;
	}
	
	public void setMask(int maskx, int masky, int mWidth, int mHeight) {
		this.maskx = maskx;
		this.masky = masky;
		this.mWidth = mWidth;
		this.mHeight = mHeight;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	public void tick() {
		
	}
	
	public static boolean isColidding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mWidth, e1.mHeight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mWidth, e2.mHeight);
		if(e1Mask.intersects(e2Mask) && e1.z == e2.z) {
			return true;
		}
		return false;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		//g.setColor(Color.blue);
		//g.fillRect(this.getX() + maskx - Camera.x + 2, this.getY() + masky - Camera.y + 3, mWidth, mHeight);
		}
}
