package Java_Files;
import javax.swing.*;

public class App {
    public static void initWindow(){
        //create a window frame and set the title in the toolbar
        JFrame window = new JFrame("Snake game");
        //when we close the window stop the app
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create the jpanel to draw on
        //this initializes the game loop
        Board board = new Board();
        window.add(board);
        //pass keyboard inputs to the jpanel
        window.addKeyListener(board);
        //don't allow user to resize the window
        window.setResizable(false);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some platforms
        window.pack();
        // open window in the center of the screen
        window.setLocationRelativeTo(null);
        // display the window
        window.setVisible(true);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                initWindow();
            }
        });
    }
}

