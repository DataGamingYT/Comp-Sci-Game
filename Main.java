import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
    JFrame frame = new JFrame("Sprite Movement");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    StagePanel stagePanel = new StagePanel();
    stagePanel.setFocusable(true);
    stagePanel.requestFocusInWindow(); // Request focus explicitly

    frame.getContentPane().add(stagePanel);
    frame.pack();
    frame.setVisible(true);
}

}

class StagePanel extends JPanel {
    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 400;
    private static final int SPRITE_SIZE = 50;
    private static final int SPRITE_MOVE_AMOUNT = 10;

    private int spriteX;
    private int spriteY;

    public StagePanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.WHITE);

        // Initial position of the sprite
        spriteX = (PANEL_WIDTH - SPRITE_SIZE) / 2;
        spriteY = (PANEL_HEIGHT - SPRITE_SIZE) / 2;

        addKeyListener(new SpriteKeyListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(spriteX, spriteY, SPRITE_SIZE, SPRITE_SIZE);
    }

    private class SpriteKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    if (spriteY - SPRITE_MOVE_AMOUNT >= 0) {
                        spriteY -= SPRITE_MOVE_AMOUNT;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (spriteY + SPRITE_MOVE_AMOUNT + SPRITE_SIZE <= PANEL_HEIGHT) {
                        spriteY += SPRITE_MOVE_AMOUNT;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (spriteX - SPRITE_MOVE_AMOUNT >= 0) {
                        spriteX -= SPRITE_MOVE_AMOUNT;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (spriteX + SPRITE_MOVE_AMOUNT + SPRITE_SIZE <= PANEL_WIDTH) {
                        spriteX += SPRITE_MOVE_AMOUNT;
                    }
                    break;
            }
            repaint(); // Redraw the sprite
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
