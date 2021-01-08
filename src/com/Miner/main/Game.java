package com.Miner.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.Miner.entities.Entity;
import com.Miner.entities.Player;
import com.Miner.graficos.Spritesheet;
import com.Miner.world.World;

public class Game extends Canvas implements Runnable,KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Elements Basics */
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	/**/
	public static final int WIDTH = 340;
	public static final int HEIGHT = 660;
	public static final int SCALE = 3;
	private BufferedImage image;
	
	public List<Entity> entities;
	public static Spritesheet spritesheet;
	
	public static World world;
	
	public static Player player;

	public Game() {
		/* Tudo tem de ser inicializado na classe game para poder ser gerado na main */
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT));
		initFrame();
		//INICIALIZANDO OBJETOS
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0, 0, 16, 16, spritesheet.getSprite(33, 0, 16, 16));
		entities.add(player);
		world = new World("/leval1.png");
	}

	public void initFrame() {
		frame = new JFrame("Miner");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {

	}

	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}

	public void tick() {
		/*Atualizacao dos entities*/
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		/**/
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,00,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		/*RENDERIZACAO DO JOGO*/
		world.render(g);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		/**/
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
		bs.show();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amoutOfTicks = 60.0;
		double ns = 1000000000 / amoutOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		// Para dar foco auto na janela
		requestFocus();
		//
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
			player.front = "rt";
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
			player.front = "lt";
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
			player.front = "up";
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
			player.front = "dn";
		}
		
		if(e.getKeyCode() == KeyEvent.VK_X || 
				e.getKeyCode() == KeyEvent.VK_M) {
			player.action = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ||
				e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_X || 
				e.getKeyCode() == KeyEvent.VK_M) {
			player.action = false;
		}
		
	}

}
