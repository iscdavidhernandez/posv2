package posv2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SidebarPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private MainFrame mainFrame;

    public SidebarPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        configurarPanel();

        crearBotones();
    }

    private void configurarPanel() {

        setPreferredSize(
                new Dimension(220, 0)
        );

        setBackground(
                new Color(30, 41, 59)
        );

        setLayout(
                new GridLayout(10,1,5,5)
        );
    }

    private void crearBotones() {

        JButton btnDashboard =
                crearBoton("Dashboard");

        JButton btnUsuarios =
                crearBoton("Usuarios");

        btnDashboard.addActionListener(e -> {

            mainFrame.mostrarVista(
                    "DASHBOARD"
            );

        });

        btnUsuarios.addActionListener(e -> {

            mainFrame.mostrarVista(
                    "USUARIOS"
            );

        });

        add(btnDashboard);

        add(btnUsuarios);
    }

    private JButton crearBoton(String texto) {

        JButton boton =
                new JButton(texto);

        boton.setFocusPainted(false);

        boton.setBackground(
                new Color(51,65,85)
        );

        boton.setForeground(
                Color.WHITE
        );

        return boton;
    }
}
