package posv2;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;

public class ModuloFormPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtIdModulo;
	private JTextField textField;
	private JTextField txtDescripcion;

	/**
	 * Create the panel.
	 */
	public ModuloFormPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblIdModulo = new JLabel("Id Modulo:");
		GridBagConstraints gbc_lblIdModulo = new GridBagConstraints();
		gbc_lblIdModulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdModulo.anchor = GridBagConstraints.EAST;
		gbc_lblIdModulo.gridx = 1;
		gbc_lblIdModulo.gridy = 1;
		add(lblIdModulo, gbc_lblIdModulo);
		
		txtIdModulo = new JTextField();
		GridBagConstraints gbc_txtIdModulo = new GridBagConstraints();
		gbc_txtIdModulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtIdModulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIdModulo.gridx = 3;
		gbc_txtIdModulo.gridy = 1;
		add(txtIdModulo, gbc_txtIdModulo);
		txtIdModulo.setColumns(10);
		
		JLabel lblModuloNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblModuloNombre = new GridBagConstraints();
		gbc_lblModuloNombre.anchor = GridBagConstraints.EAST;
		gbc_lblModuloNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblModuloNombre.gridx = 1;
		gbc_lblModuloNombre.gridy = 2;
		add(lblModuloNombre, gbc_lblModuloNombre);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 3;
		add(lblDescripcion, gbc_lblDescripcion);
		
		txtDescripcion = new JTextField();
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.gridwidth = 2;
		gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescripcion.gridx = 3;
		gbc_txtDescripcion.gridy = 3;
		add(txtDescripcion, gbc_txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JButton btnNewButton = new JButton("Guardar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 5;
		add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 5;
		add(btnNewButton_2, gbc_btnNewButton_2);

	}

}
