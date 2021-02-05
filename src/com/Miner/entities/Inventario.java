package com.Miner.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;



public class Inventario {
	public static boolean aprestInven = false;
	public int tamanhoInvent = 14;
	public Item item0 = new Item("", 1);
	
	private final int positionFinalInvenX = 160;
	private final int positionFinalInvenY = 82;
	private int positionItensX;
	private int positionItensY;
	private int positionInventX;
	private int positionInventY;
	public int sizeWidth = 160;
	public int sizeHeight = 100;
	
	//public Item[] inventario = new Item[15];
	public ArrayList<Item> inventario = new ArrayList<Item>();
	
	public void tick() {
		
		
	}
	
	public void addItem(String Item, int quanti) {
		Item item = new Item(Item, quanti);
		if(inventario.size() == 0) {
			System.out.println("item 0 adicionado");
			inventario.add(item);
			setPositionItensX(40);
			setPositionItensY(2);
		}
		
		else if(inventario.size() <= tamanhoInvent) {
			for(int i = 0; i < inventario.size(); i++) {
				if(Item == inventario.get(i).getNome()) {
					//entao so soma a qunat com a que tem
					inventario.get(i).setQuant(quanti+inventario.get(i).getQuant());
					System.out.println("Soma");
					break;
				}
				
				else if(inventario.get(i) == null) {
					inventario.add(item);
					System.out.println("Novo item adicionado");
					setPositionItensX(getPositionItensX() + 30);
					if(positionFinalInvenX <= getPositionItensX()) {
						setPositionItensX(40);
						setPositionItensY(getPositionItensY() + 20);
						if(getPositionItensY() >= positionFinalInvenY) {
							setPositionItensY(2);
						}
					}
				}
			}
		}
		else {
			System.out.println("Inventario cheio");
		}
		

	}
	
	public void render(Graphics g) {
		positionInventX = 70;
		positionInventY = 60;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(positionInventX, positionInventY, sizeWidth, sizeHeight);
		g.setColor(Color.black);
		g.drawRect(positionInventX, positionInventY, sizeWidth, sizeHeight);
		
		for(int i = 120; i >= 30; i=i-30) {
			g.drawRect(positionInventX, positionInventY, sizeWidth - i, sizeHeight);
		}
		for(int i = 80; i >= 60; i = i - 20) {
			g.drawRect(positionInventX + 40, positionInventY, sizeWidth - 40, sizeHeight - i);
		}
		for(int i = 40; i >= 20; i = i - 20) {
			g.drawRect(positionInventX, positionInventY, sizeWidth, sizeHeight - i);
		}
		
		if(inventario.size() != 0) {
			g.drawImage(Entity.MINERIO_FERRO, positionInventX + getPositionItensX(), positionInventY + getPositionItensY(), 16, 16, null);
			g.setFont(new Font("arial", Font.BOLD,10));
			g.drawString(Integer.toString(inventario.get(0).getQuant()),positionInventX + getPositionItensX() + 15, positionInventY + getPositionItensY() + 15);
		}
		
		
		
		
	}

	public int getPositionItensX() {
		return positionItensX;
	}

	public void setPositionItensX(int positionItensX) {
		this.positionItensX = positionItensX;
	}

	public int getPositionItensY() {
		return positionItensY;
	}

	public void setPositionItensY(int positionItensY) {
		this.positionItensY = positionItensY;
	}
	
}
