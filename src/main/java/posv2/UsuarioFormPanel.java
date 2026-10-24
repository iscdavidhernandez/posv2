package posv2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UsuarioFormPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtCorreo;

    private JButton btnGuardar;

    public UsuarioFormPanel() {

        setBorder(
                BorderFactory
                .createTitledBorder(
                        "Datos del Usuario"
                )
        );

        setLayout(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8,8,8,8);

        txtNombre =
                new JTextField(20);

        txtEdad =
                new JTextField(20);

        txtCorreo =
                new JTextField(20);

        btnGuardar =
                new JButton("Guardar");

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(new JLabel("Nombre"), gbc);

        gbc.gridx = 1;

        add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(new JLabel("Edad"), gbc);

        gbc.gridx = 1;

        add(txtEdad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        add(new JLabel("Correo"), gbc);

        gbc.gridx = 1;

        add(txtCorreo, gbc);

        gbc.gridy = 3;

        add(btnGuardar, gbc);
    }

}
