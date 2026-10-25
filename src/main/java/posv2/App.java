package posv2;

import javax.swing.SwingUtilities;

import posv2.view.MainFrame;

public class App {

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    MainFrame frame =
                            new MainFrame();

                    frame.setVisible(true);
                }
        );
    }
}