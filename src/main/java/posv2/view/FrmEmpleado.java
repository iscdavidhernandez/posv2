package posv2.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import posv2.controller.EmpleadoController;
import posv2.model.Empleado;
import posv2.view.base.BaseCrudFrame;

public class FrmEmpleado extends BaseCrudFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtRFC;
    private JTextField txtCorreoElectronico;

    private final EmpleadoController controller;

    public FrmEmpleado() {

        super("Catálogo de Empleados");

        controller = new EmpleadoController();

        configurarTabla();

        crearFormulario();

        registrarEventos();

        cargarTabla();

        revalidate();
        repaint();
    }

    private void configurarTabla() {

        tableModel.setColumnCount(0);

        tableModel.addColumn("ID");
        tableModel.addColumn("NOMBRE");
        tableModel.addColumn("RFC");
        tableModel.addColumn("CORREO ELECTRÓNICO");
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

        // NOMBRE

        gbc.gridx = 0;
        gbc.gridy = 1;

        pnlCenter.add(
                new JLabel("Nombre:"),
                gbc
        );

        txtNombre =
                new JTextField(30);

        gbc.gridx = 1;

        pnlCenter.add(
                txtNombre,
                gbc
        );

        // RFC

        gbc.gridx = 0;
        gbc.gridy = 2;

        pnlCenter.add(
                new JLabel("RFC:"),
                gbc
        );

        txtRFC =
                new JTextField(20);

        gbc.gridx = 1;

        pnlCenter.add(
                txtRFC,
                gbc
        );

        // CORREO

        gbc.gridx = 0;
        gbc.gridy = 3;

        pnlCenter.add(
                new JLabel(
                        "Correo Electrónico:"
                ),
                gbc
        );

        txtCorreoElectronico =
                new JTextField(30);

        gbc.gridx = 1;

        pnlCenter.add(
                txtCorreoElectronico,
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

    @Override
    protected void onNuevo() {

        limpiarFormulario();
    }

    @Override
    protected void onGuardar() {

        guardarEmpleado();
    }

    @Override
    protected void onModificar() {

        modificarEmpleado();
    }

    @Override
    protected void onEliminar() {

        eliminarEmpleado();
    }

    @Override
    protected void onConsultar() {

        cargarTabla();
    }

    private void guardarEmpleado() {

        try {

            Empleado empleado =
                    new Empleado();

            empleado.setNombre(
                    txtNombre.getText().trim()
            );

            empleado.setRfc(
                    txtRFC.getText().trim()
            );

            empleado.setCorreoElectronico(
                    txtCorreoElectronico
                            .getText()
                            .trim()
            );

            Empleado guardado =
                    controller.guardar(
                            empleado
                    );

            txtId.setText(
                    String.valueOf(
                            guardado.getIdEmpleado()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Empleado guardado correctamente."
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

    private void modificarEmpleado() {

        try {

            if(txtId.getText().isBlank()) {

                return;
            }

            Empleado empleado =
                    new Empleado();

            empleado.setIdEmpleado(
                    Integer.parseInt(
                            txtId.getText()
                    )
            );

            empleado.setNombre(
                    txtNombre.getText().trim()
            );

            empleado.setRfc(
                    txtRFC.getText().trim()
            );

            empleado.setCorreoElectronico(
                    txtCorreoElectronico
                            .getText()
                            .trim()
            );

            controller.actualizar(
                    empleado
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Empleado actualizado."
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

    private void eliminarEmpleado() {

        try {

            if(txtId.getText().isBlank()) {

                return;
            }

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Eliminar empleado?",
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
                    "Empleado eliminado."
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

        List<Empleado> empleados =
                controller.listar();

        for(Empleado empleado : empleados) {

            tableModel.addRow(
                    new Object[] {

                            empleado.getIdEmpleado(),

                            empleado.getNombre(),

                            empleado.getRfc(),

                            empleado.getCorreoElectronico()
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

        int idEmpleado =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        fila,
                                        0
                                )
                                .toString()
                );

        Empleado empleado =
                controller.buscarPorId(
                        idEmpleado
                );

        if(empleado == null) {

            return;
        }

        txtId.setText(
                String.valueOf(
                        empleado.getIdEmpleado()
                )
        );

        txtNombre.setText(
                empleado.getNombre()
        );

        txtRFC.setText(
                empleado.getRfc()
        );

        txtCorreoElectronico.setText(
                empleado.getCorreoElectronico()
        );
    }

    private void limpiarFormulario() {

        txtId.setText("");
        txtNombre.setText("");
        txtRFC.setText("");
        txtCorreoElectronico.setText("");

        txtNombre.requestFocus();
    }
}