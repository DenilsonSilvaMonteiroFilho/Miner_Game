package com.Miner.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Miner.world.World;
import com.Miner.entities.Player;
import com.Miner.main.Game;

public class World {
	
	//public boolean reConstruct = true;
	//public int cordX ,cordY;
	private static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public final static int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getWidth()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++) {
				//System.out.println(xx+"X");
				for(int yy = 0; yy < map.getHeight(); yy++) {
					
					//System.out.println(yy+"Y");
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					
					tiles [xx +(yy * WIDTH )] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR);
					
					if(pixelAtual == 0xFF000000) {
						//Floor
						tiles [xx +(yy * WIDTH )] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR);
					}
					else if(pixelAtual == 0xFF7F6B0C) {
						//Wall
						tiles[xx +(yy * WIDTH )] = new WallTile(xx*16, yy*16, Tile.TILE_WALL);
					}
					else if(pixelAtual == 0xFF0026FF) {
						//Player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static boolean isFree(int xNext, int yNext) {
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = (xNext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xNext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (yNext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}

	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH>>3);
		int yfinal = ystart + (Game.HEIGHT>>4);
		
		for(int xx = 0; xx <= xfinal; xx++) {
			for(int yy = 0; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				
				if(Player.destruct == 1) {
					//rt
					tiles[((Game.player.getX()/16)+1)+(((Game.player.getY()/16)*WIDTH))] = new FloorTile(300,505, Tile.TILE_FLOOR);
					//cordX = (Game.player.getX()/16)+1;
					//cordY = (Game.player.getY()/16);
					//reConstruct = true;
					Player.destruct = 0;
					tile.render(g);
				}
				if(Player.destruct == 2) {
					//lt
					tiles[((Game.player.getX()/16)-1)+((Game.player.getY()/16)*WIDTH)] = new FloorTile(Game.player.getX()*16,Game.player.getY()*16, Tile.TILE_FLOOR);
					Player.destruct = 0;
				}
				if(Player.destruct == 3) {
					//up
					tiles[(Game.player.getX()/16)+(((Game.player.getY()/16)-1)*WIDTH)] = new FloorTile(Game.player.getX()*16,Game.player.getY()*16, Tile.TILE_FLOOR);
					Player.destruct = 0;
				}
				if(Player.destruct == 4) {
					//dn
					tiles[(Game.player.getX()/16)+((Game.player.getY()/16+1)*WIDTH)] = new FloorTile((Game.player.getX()/16),(Game.player.getY()/16+1), Tile.TILE_FLOOR);
					Player.destruct = 0;
					//System.out.println(Game.player.getX()/16+"player X");
					//System.out.println(Game.player.getY()/16+"player Y");
				}
				
				tile.render(g);
			}
		}
	}
}
