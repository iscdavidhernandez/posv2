package posv2;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class UsuarioPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	 public UsuarioPanel() {

	        setLayout(
	                new BorderLayout()
	        );

	        add(
	                new UsuarioFormPanel(),
	                BorderLayout.NORTH
	        );

	        add(
	                new UsuarioTablePanel(),
	                BorderLayout.CENTER
	        );
	    }
}
