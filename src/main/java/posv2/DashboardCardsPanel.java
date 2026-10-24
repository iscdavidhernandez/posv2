package posv2;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class DashboardCardsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DashboardCardsPanel() {

        setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        20,
                        20
                )
        );

        add(
            new StatisticCard(
                "Usuarios",
                "150"
            )
        );

        add(
            new StatisticCard(
                "Productos",
                "89"
            )
        );

        add(
            new StatisticCard(
                "Clientes",
                "32"
            )
        );

	}

}
