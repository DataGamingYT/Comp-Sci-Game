import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int[][] stageLayout = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
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
                tilePanel.setBackground(tile == 1 ? Color.BLACK : Color.WHITE);
                stagePanel.add(tilePanel);
            }
        }

        frame.getContentPane().add(stagePanel);
        frame.pack();
        frame.setVisible(true);
    }
}
