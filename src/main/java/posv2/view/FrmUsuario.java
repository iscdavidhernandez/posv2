package posv2.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import posv2.controller.EmpleadoController;
import posv2.controller.RolController;
import posv2.controller.UsuarioController;
import posv2.model.Empleado;
import posv2.model.Rol;
import posv2.model.Usuario;
import posv2.view.base.BaseCrudFrame;

public class FrmUsuario extends BaseCrudFrame {

    private JTextField txtId;
    private JTextField txtNick;

    private JPasswordField txtContrasenia;

    private JComboBox<Rol> cmbRol;
    private JComboBox<Empleado> cmbEmpleado;

    private final UsuarioController controller;
    private final RolController rolController;
    private final EmpleadoController empleadoController;

    public FrmUsuario() {

        super("Catálogo de Usuarios");

        controller = new UsuarioController();

        rolController = new RolController();

        empleadoController =
                new EmpleadoController();

        configurarTabla();

        crearFormulario();

        cargarRoles();

        cargarEmpleados();

        registrarEventos();

        cargarTabla();

        revalidate();
        repaint();
    }

    private void configurarTabla() {

        tableModel.setColumnCount(0);

        tableModel.addColumn("ID");
        tableModel.addColumn("NICK");
        tableModel.addColumn("ROL");
        tableModel.addColumn("EMPLEADO");
    }

    private void crearFormulario() {

        pnlCenter.setLayout(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(5, 5, 5, 5);

        gbc.anchor =
                GridBagConstraints.WEST;

        // ID

        gbc.gridx = 0;
        gbc.gridy = 0;

        pnlCenter.add(
                new JLabel("ID:"),
                gbc
        );

        txtId = new JTextField(10);

        txtId.setEditable(false);

        gbc.gridx = 1;

        pnlCenter.add(
                txtId,
                gbc
        );

        // NICK

        gbc.gridx = 0;
        gbc.gridy = 1;

        pnlCenter.add(
                new JLabel("Nick:"),
                gbc
        );

        txtNick =
                new JTextField(25);

        gbc.gridx = 1;

        pnlCenter.add(
                txtNick,
                gbc
        );

        // CONTRASEÑA

        gbc.gridx = 0;
        gbc.gridy = 2;

        pnlCenter.add(
                new JLabel("Contraseña:"),
                gbc
        );

        txtContrasenia =
                new JPasswordField(25);

        gbc.gridx = 1;

        pnlCenter.add(
                txtContrasenia,
                gbc
        );

        // ROL

        gbc.gridx = 0;
        gbc.gridy = 3;

        pnlCenter.add(
                new JLabel("Rol:"),
                gbc
        );

        cmbRol =
                new JComboBox<>();

        gbc.gridx = 1;

        pnlCenter.add(
                cmbRol,
                gbc
        );

        // EMPLEADO

        gbc.gridx = 0;
        gbc.gridy = 4;

        pnlCenter.add(
                new JLabel("Empleado:"),
                gbc
        );

        cmbEmpleado =
                new JComboBox<>();

        gbc.gridx = 1;

        pnlCenter.add(
                cmbEmpleado,
                gbc
        );
    }

    private void registrarEventos() {

        table.getSelectionModel()
             .addListSelectionListener(
                     e -> {

                         if(!e.getValueIsAdjusting()) {

                             seleccionarFila();
                         }
                     }
             );
    }

    private void cargarRoles() {

        cmbRol.removeAllItems();

        rolController
                .listar()
                .forEach(
                        cmbRol::addItem
                );
    }

    private void cargarEmpleados() {

        cmbEmpleado.removeAllItems();

        empleadoController
                .listar()
                .forEach(
                        cmbEmpleado::addItem
                );
    }

    @Override
    protected void onNuevo() {

        limpiarFormulario();
    }

    @Override
    protected void onGuardar() {

        guardarUsuario();
    }

    @Override
    protected void onModificar() {

        modificarUsuario();
    }

    @Override
    protected void onEliminar() {

        eliminarUsuario();
    }

    @Override
    protected void onConsultar() {

        cargarTabla();
    }

    private void guardarUsuario() {

        try {

            Usuario usuario =
                    new Usuario();

            usuario.setNick(
                    txtNick.getText().trim()
            );

            usuario.setContrasenia(
                    String.valueOf(
                            txtContrasenia.getPassword()
                    )
            );

            usuario.setRol(
                    (Rol)
                            cmbRol.getSelectedItem()
            );

            usuario.setEmpleado(
                    (Empleado)
                            cmbEmpleado.getSelectedItem()
            );

            Usuario guardado =
                    controller.guardar(
                            usuario
                    );

            txtId.setText(
                    String.valueOf(
                            guardado.getIdUsuario()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario guardado correctamente."
            );

            cargarTabla();

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void modificarUsuario() {

        try {

            if(txtId.getText().isBlank()) {

                return;
            }

            Usuario usuario =
                    new Usuario();

            usuario.setIdUsuario(
                    Integer.parseInt(
                            txtId.getText()
                    )
            );

            usuario.setNick(
                    txtNick.getText().trim()
            );

            usuario.setContrasenia(
                    String.valueOf(
                            txtContrasenia.getPassword()
                    )
            );

            usuario.setRol(
                    (Rol)
                            cmbRol.getSelectedItem()
            );

            usuario.setEmpleado(
                    (Empleado)
                            cmbEmpleado.getSelectedItem()
            );

            controller.actualizar(
                    usuario
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario actualizado."
            );

            cargarTabla();

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void eliminarUsuario() {

        try {

            if(txtId.getText().isBlank()) {

                return;
            }

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Eliminar usuario?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION
                    );

            if(respuesta != JOptionPane.YES_OPTION) {

                return;
            }

            controller.eliminar(
                    Integer.parseInt(
                            txtId.getText()
                    )
            );

            cargarTabla();

            limpiarFormulario();

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario eliminado."
            );

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cargarTabla() {

        tableModel.setRowCount(0);

        List<Usuario> usuarios =
                controller.listar();

        for(Usuario usuario : usuarios) {

            tableModel.addRow(
                    new Object[] {

                            usuario.getIdUsuario(),

                            usuario.getNick(),

                            usuario.getRol()
                                   .getDescripcion(),

                            usuario.getEmpleado()
                                   .getNombre()
                    }
            );
        }
    }

    private void seleccionarFila() {

        int fila =
                table.getSelectedRow();

        if(fila < 0) {

            return;
        }

        int idUsuario =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        fila,
                                        0
                                )
                                .toString()
                );

        Usuario usuario =
                controller.buscarPorId(
                        idUsuario
                );

        if(usuario == null) {

            return;
        }

        txtId.setText(
                String.valueOf(
                        usuario.getIdUsuario()
                )
        );

        txtNick.setText(
                usuario.getNick()
        );

        /*
         * Nunca mostramos
         * la contraseña almacenada.
         */
        txtContrasenia.setText("");

        seleccionarRol(
                usuario.getRol()
        );

        seleccionarEmpleado(
                usuario.getEmpleado()
        );
    }

    private void seleccionarRol(
            Rol rolBuscado) {

        for(int i = 0;
            i < cmbRol.getItemCount();
            i++) {

            Rol rol =
                    cmbRol.getItemAt(i);

            if(rol.getIdRol()
                    == rolBuscado.getIdRol()) {

                cmbRol.setSelectedIndex(i);

                return;
            }
        }
    }

    private void seleccionarEmpleado(
            Empleado empleadoBuscado) {

        for(int i = 0;
            i < cmbEmpleado.getItemCount();
            i++) {

            Empleado empleado =
                    cmbEmpleado.getItemAt(i);

            if(empleado.getIdEmpleado()
                    == empleadoBuscado.getIdEmpleado()) {

                cmbEmpleado.setSelectedIndex(i);

                return;
            }
        }
    }

    private void limpiarFormulario() {

        txtId.setText("");
        txtNick.setText("");
        txtContrasenia.setText("");

        if(cmbRol.getItemCount() > 0) {

            cmbRol.setSelectedIndex(0);
        }

        if(cmbEmpleado.getItemCount() > 0) {

            cmbEmpleado.setSelectedIndex(0);
        }

        txtNick.requestFocus();
    }
}