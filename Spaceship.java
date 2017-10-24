
//Elizabeth Koshelev
//CT255
//Assignment 6
//Spaceship Class

import java.awt.Graphics;
import java.awt.Image;

public class Spaceship extends Sprite2D{
	
	public Spaceship(Image i,int windowWidth) {
		super(i, windowWidth);
		// TODO Auto-generated constructor stub
	}

	public void setPosition(double xx, double yy){
		x=(int)xx;
		y=(int)yy;
	}
	
	public int returnx(){
		return x;
	}
	
	public void movePlayer() {
		x=x+(int)xSpeed;
	}
	
	public void setXSpeed(double dx){
		xSpeed=dx;
	}
	
}
