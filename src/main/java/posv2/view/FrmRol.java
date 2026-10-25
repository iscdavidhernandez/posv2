package posv2.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import posv2.controller.RolController;
import posv2.model.Rol;
import posv2.view.base.BaseCrudFrame;

public class FrmRol extends BaseCrudFrame {

    private JTextField txtId;
    private JTextField txtDescripcion;

    private final RolController controller;

    public FrmRol() {

        super("Catálogo de Roles");

        controller = new RolController();

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
        tableModel.addColumn("DESCRIPCIÓN");
    }

    private void crearFormulario() {

        pnlCenter.setLayout(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

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

        // DESCRIPCIÓN

        gbc.gridx = 0;
        gbc.gridy = 1;

        pnlCenter.add(
                new JLabel("Descripción:"),
                gbc
        );

        txtDescripcion =
                new JTextField(25);

        gbc.gridx = 1;

        pnlCenter.add(
                txtDescripcion,
                gbc
        );
    }

    private void registrarEventos() {

        table.getSelectionModel()
             .addListSelectionListener(
                     e -> {

                         if (!e.getValueIsAdjusting()) {

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

        guardarRol();
    }

    @Override
    protected void onModificar() {

        modificarRol();
    }

    @Override
    protected void onEliminar() {

        eliminarRol();
    }

    @Override
    protected void onConsultar() {

        cargarTabla();
    }

    private void guardarRol() {

        try {

            Rol rol = new Rol();

            rol.setDescripcion(
                    txtDescripcion.getText().trim()
            );

            Rol rolGuardado =
                    controller.guardar(rol);

            txtId.setText(
                    String.valueOf(
                            rolGuardado.getIdRol()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Rol guardado correctamente."
            );

            cargarTabla();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void modificarRol() {

        try {

            if (txtId.getText().isBlank()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un registro."
                );

                return;
            }

            Rol rol = new Rol();

            rol.setIdRol(
                    Integer.parseInt(
                            txtId.getText()
                    )
            );

            rol.setDescripcion(
                    txtDescripcion.getText().trim()
            );

            controller.actualizar(rol);

            JOptionPane.showMessageDialog(
                    this,
                    "Rol actualizado correctamente."
            );

            cargarTabla();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void eliminarRol() {

        try {

            if (txtId.getText().isBlank()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un registro."
                );

                return;
            }

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Desea eliminar el registro?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION
                    );

            if (respuesta != JOptionPane.YES_OPTION) {

                return;
            }

            controller.eliminar(
                    Integer.parseInt(
                            txtId.getText()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Rol eliminado."
            );

            cargarTabla();

            limpiarFormulario();

        } catch (Exception e) {

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

        List<Rol> roles =
                controller.listar();

        for (Rol rol : roles) {

            tableModel.addRow(
                    new Object[] {
                            rol.getIdRol(),
                            rol.getDescripcion()
                    }
            );
        }
    }

    private void seleccionarFila() {

        int fila =
                table.getSelectedRow();

        if (fila < 0) {

            return;
        }

        txtId.setText(
                tableModel.getValueAt(
                        fila,
                        0
                ).toString()
        );

        txtDescripcion.setText(
                tableModel.getValueAt(
                        fila,
                        1
                ).toString()
        );
    }

    private void limpiarFormulario() {

        txtId.setText("");

        txtDescripcion.setText("");

        txtDescripcion.requestFocus();
    }
}