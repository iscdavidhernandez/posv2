package posv2;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private CardLayout cardLayout;
    private JPanel contentPanel;

    public MainFrame() {

        configurarVentana();

        inicializarComponentes();
    }

    private void configurarVentana() {

        setTitle("Sistema Escolar");

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);

        SidebarPanel sidebar =
                new SidebarPanel(this);

        contentPanel.add(
                new DashboardPanel(),
                "DASHBOARD"
        );

        contentPanel.add(
                new UsuarioPanel(),
                "USUARIOS"
        );

        add(sidebar, BorderLayout.WEST);

        add(contentPanel, BorderLayout.CENTER);
    }

    public void mostrarVista(String vista) {

        cardLayout.show(
                contentPanel,
                vista
        );
    }

}
