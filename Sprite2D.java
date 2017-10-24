//Elizabeth Koshelev
//CT255
//Assignment 6
//Sprite2D Class

import java.awt.Graphics;
import java.awt.Image;

public class Sprite2D {
	//member data
	protected int x,y;
	protected Image myImage;
	protected double xSpeed;
	protected Image myImage2;
	protected int framesDrawn=0;
	int winWidth;
	//constructor
	public Sprite2D(Image i,int windowWidth){
		myImage=i;
		myImage2=myImage;
		winWidth=windowWidth;

	}
	//public interface
	public void paint(Graphics g){	
		framesDrawn++;
		if ( framesDrawn%100<50 )
		g.drawImage(myImage, (int)x, (int)y, null);
		else
		g.drawImage(myImage2, (int)x, (int)y, null);

	}
}
