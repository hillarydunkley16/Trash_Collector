package Java_Files;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.*;

import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
public class Coin {
   private BufferedImage image;
   
   private Point pos;

   public Coin(int x, int y){
       loadImage();
       pos = new Point(x,y);
   }
   
   private void loadImage(){
       try{
        image = ImageIO.read(new File("Java_Files/coin.png"));
       }catch (IOException exc){
            System.out.println("No file found " + exc.getMessage());
       }
   }
   public void draw(Graphics g, ImageObserver observer){
       g.drawImage(
        image, 
        pos.x * Board.TILE_SIZE,
        pos.y * Board.TILE_SIZE,
       observer
       );
   }
   public Point getPos(){
       return pos;
   }
}
