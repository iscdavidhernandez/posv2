package posv2;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UsuarioTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	
    public UsuarioTablePanel() {

        setLayout(
                new BorderLayout()
        );

        DefaultTableModel model =
                new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Edad");
        model.addColumn("Correo");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(
                scrollPane,
                BorderLayout.CENTER
        );
        
    }

}
