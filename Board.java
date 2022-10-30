package Java_Files;
import java.awt.*;
import java.applet.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class Board extends JPanel implements ActionListener, KeyListener {
    // suppress serialization warning
    private final int DELAY = 25;
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 12;
    public static final int COLUMNS = 18;
    public int NUM_COINS = 3;
    private Point pos;
    private static final long serialVersionUID = 490905409104883233L;

    public Timer timer;
    // keep track of the game state

    private Player player;
    private ArrayList<Coin> coins;
    private int x;
    private int y;

    int time = 0;
    int duration = 10000;

    // public int update(int time, int duration){
    //     while(time < duration){
    //         time += 1;
    //     }
    //     return time;
    // }

    public Board() {
        // set the preferred game board size
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        // set the game board background color
        setBackground(new Color(232, 232, 232));

        // loadPlayerImage();
        x = 100;
        y = 30;
        player = new Player();

        coins = populateCoins(NUM_COINS);

        timer = new Timer(150, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        player.tick();

        collectCoins();
        
        //collectCoins();
        repaint();
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawScore(g);
        
            
        for (Coin coin : coins) {
            coin.draw(g, this);
        }
            
        
        
        player.draw(g, this);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(214, 214, 214));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                // only color every other tile
                if ((row + col) % 2 == 1) {
                    g.fillRect(
                            col * TILE_SIZE,
                            row * TILE_SIZE,
                            TILE_SIZE,
                            TILE_SIZE);
                }
            }
        }
    }

    private void drawScore(Graphics g) {
        // set the text to be displayed
        String text = player.getScore();
        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        // set the text color and font
        g2d.setColor(new Color(30, 201, 139));
        g2d.setFont(new Font("Lato", Font.BOLD, 25));
        // draw the score in the bottom center of the screen
        // https://stackoverflow.com/a/27740330/4655368
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        // the text will be contained within this rectangle.
        // here I've sized it to be the entire bottom row of board tiles
        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
        // determine the x coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // determine the y coordinate for the text
        // (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // draw the string
        g2d.drawString(text, x, y);
    }

    public ArrayList<Coin> populateCoins(int num_coins) {
        ArrayList<Coin> coinList = new ArrayList<>();
        Random rand = new Random();
        System.out.println("hihi");
        for (int i = 0; i < num_coins; i++) {
            int coinX = rand.nextInt(COLUMNS);
            int coinY = rand.nextInt(ROWS);
            coinList.add(new Coin(coinX, coinY));
        }
        // ifayer.getPos(plya)
        return coinList;
    }

    public void collectCoins() {
        Coin coinA = null;
        // Random rand = new Random();
        for (Coin coin : coins) {
            if (player.getPos().equals(coin.getPos())) {
                coinA = coin;
                break;
            }
            // populateCoins(NUM_COINS);
        }
        if (coinA != null) {
            player.addScore(100);
            // collectedCoins.add(coin);
            NUM_COINS--;
            // NUM_COINS = ((NUM_COINS + 1)^2);
            coins.remove(coinA);

            coins.addAll(populateCoins(NUM_COINS));
            //coins.addAll(populateCoins(NUM_COINS-1));
            NUM_COINS = coins.size();
        }
        // populateCoins(NUM_COINS);
        // coins.add(collectedCoins);
    }
}
