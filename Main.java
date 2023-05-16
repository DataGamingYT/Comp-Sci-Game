import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {

        


        // Create the stage layout

        int[][] stageLayout = {
            {1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0},
            {1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0}
        };

        SwingUtilities.invokeLater(() -> createAndShowGUI(stageLayout));
    }

    private static void createAndShowGUI(int[][] stageLayout) {
        JFrame frame = new JFrame("Main Stage");
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        // Create the stage panel and set the layout manager
        JPanel stagePanel = new JPanel();
        stagePanel.setLayout(new GridLayout(stageLayout.length, stageLayout[0].length));

        // Populate the stage panel with tiles based on the layout
        for (int[] row : stageLayout) {
            for (int tile : row) {
                JPanel tilePanel = new JPanel();
                tilePanel.setBackground(tile == 1 ? Color.BLUE : Color.GRAY);
                stagePanel.add(tilePanel);
            }
        }

        // Create the sprite
        Sprite sprite = new Sprite();

        // Add the sprite to the stage panel
        stagePanel.add(sprite);

        // Add key listener to the frame
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                // Handle sprite movement based on arrow keys
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        sprite.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        sprite.moveDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        sprite.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        sprite.moveRight();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // Set the focus on the frame to receive key events
        frame.setFocusable(true);
        frame.requestFocus();

        frame.getContentPane().add(stagePanel);
        frame.pack();
        frame.setVisible(true);
    }
}

class Sprite extends JPanel {
    private int x;
    private int y;
    private static final int SIZE = 3;
    private static final int MOVE_AMOUNT = 3;

    public Sprite() {
        this.x = 0;
        this.y = 0;
        setPreferredSize(new Dimension(SIZE, SIZE));
        setBackground(Color.RED);
    }

    public void moveUp() {
        if (y - MOVE_AMOUNT >= 0) {
            y -= MOVE_AMOUNT;
            setLocation(x, y);
        }
    }

    public void moveDown() {
        if (y + MOVE_AMOUNT + SIZE <= getParent().getHeight()) {
            y += MOVE_AMOUNT;
            setLocation(x, y);
        }
    }

    public void moveLeft() {
        if (x - MOVE_AMOUNT >= 0) {
            x -= MOVE_AMOUNT;
            setLocation(x, y);
        }
    }


    public void moveRight() {
        if (x + MOVE_AMOUNT + SIZE <= getParent().getWidth()) {
            x += MOVE_AMOUNT;
           
        }
    }
}