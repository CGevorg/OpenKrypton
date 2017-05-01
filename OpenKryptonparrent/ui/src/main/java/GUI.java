
import javax.swing.*;
import java.util.Map;

public class GUI extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane();

    private GUI() {
        add(tabbedPane);
    }

    public static void create(String windowName, Map<String, JPanel> panels) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        GUI gui = new GUI();
        for (String key : panels.keySet()) {
            gui.tabbedPane.add(key, panels.get(key));
        }
        gui.setTitle(windowName);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(1000, 800);
        gui.setVisible(true);
    }
}
