//Elizabeth Koshelev
//CT255
//Assignment 6
//Bullet class

import java.awt.Image;

public class Bullet extends Sprite2D{
	public boolean alive;
	public Bullet(Image i,int windowWidth) {
		super(i, windowWidth);
		alive= true;
		// TODO Auto-generated constructor stub
	}
	public void move() {
		y=y-5;
	}
	public void setPosition(double xx, double yy){
		x=(int)xx;
		y=(int)yy;
	}
	public void kill(){
		alive= false;
	}
	
	
}