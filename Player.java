package Java_Files;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.management.StringValueExp;
import javax.swing.*;

public class Player {
    private BufferedImage image;
    private Point pos;
    public int score;
    int length;
    boolean isMoving = false;
    

    public Player(){
        //load the assets
        loadImage();
        //current position ofthe player on the board grid
        pos = new Point(0,0);
        length = 5;
        isMoving = true;
        score = 0;
    }
    public void loadImage(){
        try{
            image = ImageIO.read(new File("Java_Files/player.png"));
        }catch(IOException exc){
            System.out.println("Error opening image file " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer){
        g.drawImage(image, 
        pos.x * Board.TILE_SIZE,
        pos.y * Board.TILE_SIZE,
        observer
        );
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP){
            pos.translate(0,-1);
        }
        if (key == KeyEvent.VK_RIGHT){
            pos.translate(1,0);
        }
        if (key == KeyEvent.VK_LEFT){
            pos.translate(-1,0);
        }
        if (key == KeyEvent.VK_DOWN){
            pos.translate(0,1);
        }
        //score += 1;
    }
    public void tick(){
        if(pos.x < 0){
            pos.x = 0;
        }else if (pos.x >= Board.COLUMNS){
            pos.x = Board.COLUMNS -1;
        }

        if (pos.y < 0){
            pos.y = 0;
        }else if (pos.y >= Board.ROWS){
            pos.y = Board.ROWS -1;
        }
    }
    public String getScore(){
        return String.valueOf(score);
    }
    public void addScore(int amount){
        score += amount;
    }
    public Point getPos(){
        return pos;
    }
    
}
