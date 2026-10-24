package posv2;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DashboardPanel() {

        setLayout(
                new BorderLayout()
        );

        JLabel titulo =
                new JLabel(
                        "Dashboard"
                );

        titulo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        add(titulo,
                BorderLayout.NORTH);

        add(
            new DashboardCardsPanel(),
            BorderLayout.CENTER
        );
    }

}
