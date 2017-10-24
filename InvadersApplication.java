
//Elizabeth Koshelev
//CT255
//Assignment 6
//InvadersApplication Class


import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.image.*;

public class InvadersApplication extends JFrame implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	//member data
	private static String workingDirectory;
	private BufferStrategy strategy;
	private static final Dimension WindowSize = new Dimension(800,600);
	private static final int NUMALIENS = 30;
	private Alien[] AliensArray = new Alien[NUMALIENS];
	private Spaceship PlayerShip;
	private Image alienImage;
	private Image bulletImage;
	private ArrayList bulletsList = new ArrayList();
	private boolean isGameInProgress=false;
	int z=0;
	int alienspeed=0;
	int bestscore=0;
	int currentscore=0;
	//constructor
	public InvadersApplication() throws IOException{
		this.setTitle("Space Invaders Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Display the window, centered on the screen
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		setBounds(x, y, WindowSize.width, WindowSize.height);	
		setVisible(true);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		BufferedImage img=ImageIO.read(new File("alienship.png"));
		ImageIcon icon = new ImageIcon(img);
		alienImage = icon.getImage();
		
		BufferedImage img3 = ImageIO.read(new File("alienship.png"));
		ImageIcon icon3 = new ImageIcon(img3);
		bulletImage = icon3.getImage();
		if (isGameInProgress==false){
		int h =0;
		for (int i=0; i<NUMALIENS; i++){
			AliensArray[i] = new Alien(alienImage, WindowSize.width);
			if (i%6==0){
				h++;
			}
			AliensArray[i].setPosition(50+i%6*50,50+50*h);
		}
		}
		BufferedImage img2=ImageIO.read(new File("playership.png"));
		ImageIcon icon2 = new ImageIcon(img2);
		PlayerShip = new Spaceship(icon2.getImage(), WindowSize.width);
		PlayerShip.setPosition(50, 550);
		
		Thread t = new Thread(this);
		t.start();
		
		addKeyListener(this);
	}
	public void makeNewAliens(){
		
		int h =0;
		for (int i=0; i<NUMALIENS; i++){
			AliensArray[i] = new Alien(alienImage, WindowSize.width);
			if (i%6==0){
				h++;
			}
			AliensArray[i].setPosition(50+i%6*50,50+50*h);
		}
		
	}
	public void checkGameOver(){
		int x=0;
		for (int i = 0; i < NUMALIENS; i++) {
    		if (AliensArray[i].alive){
    			isGameInProgress=true;
    		} else {
    			x++;
    		}
		}
		if (x==NUMALIENS){
			isGameInProgress=false;
		}
	}
	public void shootBullet() {
		// add a new bullet to our list
		Bullet b = new Bullet(bulletImage,WindowSize.width);
		b.setPosition(PlayerShip.x+54/2, PlayerShip.y);
		bulletsList.add(b);
		}

	
	public void run(){
		int x=0;
		 while (true) {
	           if (isGameInProgress){
	            	x++;
	            	
	            	if (AliensArray[5].xPosition()==700){
	            		z++;
	            	} else if (AliensArray[0].xPosition()==10){
	            		z++;
	            	}
	            	for (int i = 0; i < NUMALIENS; i++) {
	            		if (z%2==0){
	            			AliensArray[i].setXSpeed(alienspeed);
	            		} else{
		            		AliensArray[i].setXSpeed(-1*alienspeed);
	            		}	
		            		AliensArray[i].movePlayer();
		            		
	            	}
	            	Iterator iterator = bulletsList.iterator();
	            	while(iterator.hasNext()){
	            	Bullet b = (Bullet) iterator.next();
		            	if (b.alive){
		            	b.move();
		            	} else{
		            		b.setPosition(0, 0);
		            	}
	            	}
	            	checkCollision();
	            	checkGameOver();
	           }
	            	this.repaint();
	                try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	      }   
	}
	
	public void checkCollision(){
		int h1 = alienImage.getHeight(null);
		int w1 = alienImage.getWidth(null);
		int h2 = bulletImage.getHeight(null);
		int w2 = bulletImage.getWidth(null);
		for (int i = 0; i < NUMALIENS; i++) {
			int x1 = AliensArray[i].x;
			int y1 = AliensArray[i].y;
			Iterator iterator = bulletsList.iterator();
        	while(iterator.hasNext()){
	        	Bullet b = (Bullet) iterator.next();
	        	int x2 = b.x;
	    		int y2 = b.y;
	    		if (AliensArray[i].alive){
		    		if (( (x1<x2 && x1+w1>x2) || (x2<x1 && x2+w2>x1) ) && ( (y1<y2 && y1+h1>y2) || (y2<y1 && y2+h2>y1) )) {
		    			AliensArray[i].kill();
		    			b.kill();
		    			currentscore= currentscore+5;
		    			if (currentscore>=bestscore){
		    				
		    				bestscore=currentscore;
		    			}
		    		}
	    		}
        	}
		}	
	}
	
	public void keyPressed(KeyEvent e){
	    int key = e.getKeyCode();
	    if (isGameInProgress=true){
	    if (key == KeyEvent.VK_LEFT) {
	    	PlayerShip.setXSpeed(-5);
	        PlayerShip.movePlayer();
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	    	PlayerShip.setXSpeed(5);
	        PlayerShip.movePlayer();
	    }
	    
	    if (key == KeyEvent.VK_SPACE){
			shootBullet();
	    }
	} 
	    if (key == KeyEvent.VK_ENTER){
	    	isGameInProgress=true;
	    	makeNewAliens();
	    	alienspeed= alienspeed+5;
	    	z=0;
	    	currentscore=0;
	    }
		
	}
	
	
	public void keyReleased(KeyEvent e){
		
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	public void paint(Graphics g){
		g = strategy.getDrawGraphics();
		g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);
        if (isGameInProgress==true){
    		Font f = new Font( "Times", Font.PLAIN, 24 );
    		g.setFont(f);
    		Color c = Color.WHITE;
    		g.setColor(c);
    		g.drawString("Current score: " + String.valueOf(currentscore) + "  Best Score: " + String.valueOf(bestscore), 250, 50);
		for (int i = 0; i < NUMALIENS; i++) {
			if (AliensArray[i].alive){
			AliensArray[i].paint(g);
			}
		}
		Iterator iterator = bulletsList.iterator();
    	while(iterator.hasNext()){
	    	Bullet b = (Bullet) iterator.next();
	    	if (b.alive){
	    		b.paint(g);
	    	}
    	}
		PlayerShip.paint(g);

		} else {
		Font f = new Font( "Times", Font.PLAIN, 24 );
		g.setFont(f);
		Color c = Color.WHITE;
		g.setColor(c);
		g.drawString("GAME OVER ", 300, 300);
		g.drawString("Press Enter to Play Again", 250, 350);
		g.drawString("[Arrow keys to move, spacebar to shoot]", 200, 400);
		}
		g.dispose();
		strategy.show();
	}

	
	public static void main(String[] args) throws IOException{
		InvadersApplication d = new InvadersApplication();
	}
}
