package com.Miner.entities;


public class Item {
	
	private String nome;
	private int quant;
	
	Item(String nome, int quant){
		this.nome = nome;
		this.quant = quant;
	}

	public String getNome() {
		return nome;
	}

	public int getQuant() {
		return this.quant;
	}
	
	public void setQuant(int newQuant) {
		this.quant = newQuant;
	}
	
	
	/*public int initialPosition = 260;
	public int selected;
	//public int x,y = 7;
	//public String itens[][] = new String[x][y];
	protected double x = Game.player.getX();
	protected double y = Game.player.getY();
	protected double z;
	protected int width;
	protected int height;
	private BufferedImage sprite = Game.spritesheet.getSprite(1, 1, 29, 21);
	
	//O tamanho do inventario vai ser de 15 itens com o maximo de 30 por slot
	//Para resistrar um item se diz o nome e a quantidade
	
	public void render(Graphics g) {
		
		g.drawImage(sprite, (int)x - Camera.x, (int)y - Camera.y, null);
	} */
	
}
