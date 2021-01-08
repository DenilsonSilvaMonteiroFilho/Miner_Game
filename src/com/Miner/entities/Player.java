package com.Miner.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Miner.main.Game;
import com.Miner.world.Camera;
import com.Miner.world.World;

public class Player extends Entity{
	
	/*Basicos para o Player*/
	public static String front;
	public static boolean action;
	public static int destruct;
	public boolean right, left, up, down;
	public static double speed = 0.6;
	public int x_dir = 0, y_dir = 1;
	public int dir = y_dir;
	
	private BufferedImage [] rightLeftPlayer;
	private BufferedImage [] upDownPlayer;

	private BufferedImage damagedPlayer;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightLeftPlayer = new BufferedImage[1];
		upDownPlayer = new BufferedImage[1];
		
		for(int i = 0; i < 1; i++) {
			rightLeftPlayer[i] = Game.spritesheet.getSprite(49 + (i*16), 0, 16, 16);
		}
		for(int i = 0; i < 1; i++) {
			upDownPlayer[i] = Game.spritesheet.getSprite(33 + (i*16), 0, 16, 16);
		}
	}
	
	public void tick() {
		if(right && World.isFree((int)(x+speed), this.getY())) {
			dir = x_dir;
			x+=speed;	
		}
		else if(left && World.isFree((int)(x-speed), this.getY())) {
			dir = x_dir;
			x-=speed;
		}
		if(up && World.isFree(this.getX(), (int)(y-speed))) {
			dir = y_dir;
			y-=speed;
		}
		else if(down && World.isFree(this.getX(), (int)(y+speed))) {
			dir = y_dir;
			y+=speed;
		}
		
		if(action) {
			if(front == "rt" && !(World.isFree((int)(x+speed), this.getY()))) {
				//right
				//System.out.println("destruir rt");
				destruct = 1;
			}
			if(front == "lt" && !(World.isFree((int)(x-speed), this.getY()))) {
				//left
				//System.out.println("destruir lt");
				destruct = 2;
			}
			if(front == "up" && !(World.isFree(this.getX(), (int)(y-speed)))) {
				//up
				//System.out.println("destruir up");
				destruct = 3;
			}
			if(front == "dn" && !(World.isFree(this.getX(), (int)(y+speed)))) {
				//down
				//System.out.println("destruir dn");
				destruct = 4;
			}
		}
		
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/3), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/6), 0, World.WIDTH*31 - Game.HEIGHT);
	}
	
	public void render(Graphics g) {
		if(dir == x_dir) {
			g.drawImage(rightLeftPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		if(dir == y_dir) {
			g.drawImage(upDownPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);

		}
	}
}
