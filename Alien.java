//Elizabeth Koshelev
//CT255
//Assignment 6
//Alien Class

import java.awt.Graphics;
import java.awt.Image;

public class Alien extends Sprite2D{
	public boolean alive;
	public Alien(Image i,int windowWidth) {
		super(i, windowWidth);
		alive = true;
		// TODO Auto-generated constructor stub
	}
	
	public void setPosition(double xx, double yy){
		x=(int)xx;
		y=(int)yy;
	}
	
	public int xPosition(){
		return x;
	}
	
	public void movePlayer() {
		x=x+(int)xSpeed;
	}
	
	public void setXSpeed(double dx){
		xSpeed=dx;
	}
	
	public void kill(){
		alive = false;
	}

	
}