package com.Miner.main;

import java.awt.Graphics;

public class Coin {

	private int coin;
	
	public Coin(int coin) {
		this.coin = coin;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
	
	}
	
	public int getCoin() {
		return this.coin;
	}
	
	public void setCoin(int newCoin, int oldCoin) {
		this.coin = newCoin + oldCoin;
	}
}
