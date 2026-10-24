package posv2;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import posv2.view.FrmRol;

public class Main extends JPanel {
	public Main() {
	}

	private static final long serialVersionUID = 1L;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

        	FrmRol  frame =
                    new FrmRol ();

            frame.setVisible(true);

        });
    }

}
