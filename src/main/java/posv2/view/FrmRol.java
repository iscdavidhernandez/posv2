package posv2.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import posv2.controller.RolController;
import posv2.model.Rol;

public class FrmRol extends JFrame {

	private static final long serialVersionUID = 1L;

	
	
	private JTable tblRoles;

	private DefaultTableModel modeloTabla;
	
	private JTextField txtId;
	private JTextField txtDescripcion;
	
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnConsultar;
	
	private final RolController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRol frame = new FrmRol();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmRol() {

	    controller = new RolController();

	    inicializarComponentes();

	    configurarVentana();

	    cargarTabla();
	}
	
	private void configurarVentana() {

	    setTitle("Catálogo de Roles");

	    setSize(700,500);

	    setLocationRelativeTo(null);

	    setDefaultCloseOperation(
	            JFrame.DISPOSE_ON_CLOSE
	    );
	}
	
	private void inicializarComponentes() {

	    JPanel panel = new JPanel();

	    panel.setLayout(null);

	    add(panel);

	    crearTabla(panel);

	    crearFormulario(panel);

	    crearBotones(panel);

	    registrarEventos();
	}
	
	private void crearTabla(JPanel panel) {

	    modeloTabla =
	            new DefaultTableModel();

	    modeloTabla.addColumn("ID");

	    modeloTabla.addColumn(
	            "DESCRIPCIÓN"
	    );

	    tblRoles =
	            new JTable(modeloTabla);

	    JScrollPane scroll =
	            new JScrollPane(tblRoles);

	    scroll.setBounds(
	            20,
	            20,
	            640,
	            200
	    );

	    panel.add(scroll);
	}
	
	private void crearFormulario(
	        JPanel panel) {

	    JLabel lblId =
	            new JLabel("ID:");

	    lblId.setBounds(
	            20,
	            250,
	            100,
	            25
	    );

	    panel.add(lblId);

	    txtId =
	            new JTextField();

	    txtId.setBounds(
	            120,
	            250,
	            150,
	            25
	    );

	    txtId.setEditable(false);

	    panel.add(txtId);

	    JLabel lblDescripcion =
	            new JLabel("Descripción:");

	    lblDescripcion.setBounds(
	            20,
	            290,
	            100,
	            25
	    );

	    panel.add(lblDescripcion);

	    txtDescripcion =
	            new JTextField();

	    txtDescripcion.setBounds(
	            120,
	            290,
	            250,
	            25
	    );

	    panel.add(txtDescripcion);
	}
	
	private void crearBotones(
	        JPanel panel) {

	    btnNuevo =
	            new JButton("Nuevo");

	    btnGuardar =
	            new JButton("Guardar");

	    btnModificar =
	            new JButton("Modificar");

	    btnEliminar =
	            new JButton("Eliminar");

	    btnConsultar =
	            new JButton("Consultar");

	    btnNuevo.setBounds(
	            20,
	            350,
	            100,
	            30
	    );

	    btnGuardar.setBounds(
	            130,
	            350,
	            100,
	            30
	    );

	    btnModificar.setBounds(
	            240,
	            350,
	            100,
	            30
	    );

	    btnEliminar.setBounds(
	            350,
	            350,
	            100,
	            30
	    );

	    btnConsultar.setBounds(
	            460,
	            350,
	            100,
	            30
	    );

	    panel.add(btnNuevo);
	    panel.add(btnGuardar);
	    panel.add(btnModificar);
	    panel.add(btnEliminar);
	    panel.add(btnConsultar);
	}
	
	private void registrarEventos() {

	    btnNuevo.addActionListener(
	            e -> limpiarFormulario()
	    );

	    btnGuardar.addActionListener(
	            e -> guardarRol()
	    );

	    btnModificar.addActionListener(
	            e -> modificarRol()
	    );

	    btnEliminar.addActionListener(
	            e -> eliminarRol()
	    );

	    btnConsultar.addActionListener(
	            e -> cargarTabla()
	    );

	    tblRoles.getSelectionModel()
	            .addListSelectionListener(
	                    e -> seleccionarFila()
	            );
	}
	
	private void guardarRol() {

	    try {

	        Rol rol = new Rol();

	        rol.setDescripcion(
	                txtDescripcion.getText()
	        );

	        boolean guardado =
	                controller.guardar(rol);

	        if(guardado) {

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Rol guardado correctamente."
	            );

	            cargarTabla();

	            limpiarFormulario();
	        }

	    } catch(Exception ex) {

	        JOptionPane.showMessageDialog(
	                this,
	                ex.getMessage()
	        );
	    }
	}
	
	private void modificarRol() {

	    try {

	        Rol rol = new Rol();

	        rol.setIdRol(
	                Integer.parseInt(
	                        txtId.getText()
	                )
	        );

	        rol.setDescripcion(
	                txtDescripcion.getText()
	        );

	        if(controller.actualizar(
	                rol)) {

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Rol actualizado."
	            );

	            cargarTabla();
	        }

	    } catch(Exception ex) {

	        JOptionPane.showMessageDialog(
	                this,
	                ex.getMessage()
	        );
	    }
	}
	
	private void eliminarRol() {

	    if(txtId.getText().isBlank()) {

	        return;
	    }

	    int respuesta =
	            JOptionPane.showConfirmDialog(
	                    this,
	                    "¿Eliminar registro?"
	            );

	    if(respuesta ==
	            JOptionPane.YES_OPTION) {

	        controller.eliminar(
	                Integer.parseInt(
	                        txtId.getText()
	                )
	        );

	        cargarTabla();

	        limpiarFormulario();
	    }
	}
	
	private void cargarTabla() {

	    modeloTabla.setRowCount(0);

	    for(Rol rol :
	            controller.listar()) {

	        Object[] fila = {

	                rol.getIdRol(),

	                rol.getDescripcion()
	        };

	        modeloTabla.addRow(fila);
	    }
	}
	
	private void seleccionarFila() {

	    int fila =
	            tblRoles.getSelectedRow();

	    if(fila == -1) {

	        return;
	    }

	    txtId.setText(
	            modeloTabla
	                    .getValueAt(
	                            fila,
	                            0)
	                    .toString()
	    );

	    txtDescripcion.setText(
	            modeloTabla
	                    .getValueAt(
	                            fila,
	                            1)
	                    .toString()
	    );
	}

	private void limpiarFormulario() {

	    txtId.setText("");

	    txtDescripcion.setText("");

	    txtDescripcion.requestFocus();
	}
}
