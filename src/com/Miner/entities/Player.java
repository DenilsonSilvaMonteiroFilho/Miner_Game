package com.Miner.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.Miner.main.Game;
import com.Miner.world.Camera;
import com.Miner.world.FloorTile;
import com.Miner.world.Tile;
import com.Miner.world.World;

public class Player extends Entity{
	
	/*Basicos para o Player*/
	public static String front;
	public static boolean action;
	public static boolean colect;
	public boolean right, left, up, down;
	public static double speed = 0.6;
	public int x_dir = 0, y_dir = 1;
	public int dir = y_dir;
	public int mx, my;
	private int ability = 10;
	
	private BufferedImage [] rightLeftPlayer;
	private BufferedImage [] upDownPlayer;

	//private BufferedImage damagedPlayer;
	
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
		if(right && World.isFree((int)(x+speed), this.getY()) && World.isBreak((int)(x+speed), this.getY())) {
			dir = x_dir;
			x+=speed;	
		}
		else if(left && World.isFree((int)(x-speed), this.getY()) && World.isBreak((int)(x-speed), this.getY())) {
			dir = x_dir;
			x-=speed;
		}
		if(up && World.isFree(this.getX(), (int)(y-speed)) && World.isBreak(this.getX(), (int)(y-speed))) {
			dir = y_dir;
			y-=speed;
		}
		else if(down && World.isFree(this.getX(), (int)(y+speed)) && World.isBreak(this.getX(), (int)(y+speed))) {
			dir = y_dir;
			y+=speed;
		}
		
		
		if(action) {
			mx =Game.player.getX()/16;
			my = Game.player.getY()/16;
			Random rand = new Random();
			int prob =  rand.nextInt(MinerioFerro.getDropPor());

			if(front == "rt" && !(World.isBreak((int)(x+speed), this.getY()))) {
				//right
				//System.out.println("destruir rt");
				World.tiles[(mx+1)+(my*World.WIDTH)] = new FloorTile((mx+1)*16,my*16, Tile.TILE_FLOOR);
			
				//Chance de drop do minerio
				if(prob+ability > MinerioFerro.getDropPor() ) {
					Game.entities.add(new MinerioFerro((mx+1)*16, my*16, (mx+1)*16, my*16, Entity.MINERIO_FERRO));
				}
			}//end if
			
			if(front == "lt" && !(World.isBreak((int)(x-speed), this.getY()))) {
				//left
				//System.out.println("destruir lt");
				World.tiles[(mx-1)+(my*World.WIDTH)] = new FloorTile((mx-1)*16,my*16, Tile.TILE_FLOOR);
				
				//Chance de drop do minerio
				if(prob+ability > MinerioFerro.getDropPor() ) {
					Game.entities.add(new MinerioFerro((mx-1)*16, my*16, (mx-1)*16, my*16, Entity.MINERIO_FERRO));
				}//end if
				
			}//end if
			
			if(front == "up" && !(World.isBreak(this.getX(), (int)(y-speed)))) {
				//up
				//System.out.println("destruir up");
				World.tiles[mx+((my-1)*World.WIDTH)] = new FloorTile(mx*16,(my-1)*16, Tile.TILE_FLOOR);
				
				//Chance de drop do minerio
				if(prob+ability > MinerioFerro.getDropPor() ) {
					Game.entities.add(new MinerioFerro(mx*16,(my-1)*16,mx*16,(my-1)*16, Entity.MINERIO_FERRO));
				}//end if
				
			}//end if
			
			if(front == "dn" && !(World.isBreak(this.getX(), (int)(y+speed)))) {
				//down
				//System.out.println("destruir dn");
				World.tiles[mx+((my+1)*World.WIDTH)] = new FloorTile(mx*16,(my+1)*16, Tile.TILE_FLOOR);
				
				if(prob+ability > MinerioFerro.getDropPor() ) {
					Game.entities.add(new MinerioFerro(mx*16,(my+1)*16,mx*16,(my+1)*16, Entity.MINERIO_FERRO));
				}//end if
				
			}//end if
			
		}//end if(action)
		
		
		checkCollisionMinerioFerro();
		/*if(Inventario.aprestInven) {
			Game.inventario.seeInven();
		}*/
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/3), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/6), 0, World.WIDTH*31 - Game.HEIGHT);
	}
	
	public void checkCollisionMinerioFerro() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof MinerioFerro ) {
				if(isColidding(this,atual)) {
					Game.entities.remove(i);
					Game.inventario.addItem("Ferro", 1);
				}
				
			}	
		}
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
