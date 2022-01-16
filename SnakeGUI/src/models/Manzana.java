package models;

import java.awt.Color;
import java.awt.Graphics2D;

public class Manzana {
	
	private static int posX;
	private static int posY;
	private int lado;
	private int color;
	
	public Manzana(int posX, int posY, int lado) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.lado = lado;
	}

	public static int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		Manzana.posX = posX;
	}
	
	public static int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		Manzana.posY = posY;
	}

	public int getLado() {
		return lado;
	}
	
	public int getColor() {
		return color;
	}
	

    public void pintarse(Graphics2D g) {
        
    	g.setColor(new Color(255,0,0));
    	//g.drawRect(posX, posY, lado, lado);
    	g.fillRect(posX, posY, lado, lado);
		//g.fillOval(posX, posY, lado, lado);
    	
    }

}
