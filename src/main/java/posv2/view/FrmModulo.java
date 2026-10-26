package posv2.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import posv2.controller.ModuloController;
import posv2.model.Modulo;
import posv2.view.base.BaseCrudFrame;

public class FrmModulo extends BaseCrudFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;

    private final ModuloController controller;

    public FrmModulo() {

        super("Catálogo de Módulos");

        controller = new ModuloController();

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
        tableModel.addColumn("DESCRIPCIÓN");
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

        // Nombre

        gbc.gridx = 0;
        gbc.gridy = 1;

        pnlCenter.add(
                new JLabel("Nombre:"),
                gbc
        );

        txtNombre =
                new JTextField(25);

        gbc.gridx = 1;

        pnlCenter.add(
                txtNombre,
                gbc
        );

        // Descripción

        gbc.gridx = 0;
        gbc.gridy = 2;

        pnlCenter.add(
                new JLabel("Descripción:"),
                gbc
        );

        txtDescripcion =
                new JTextField(30);

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

        guardarModulo();
    }

    @Override
    protected void onModificar() {

        modificarModulo();
    }

    @Override
    protected void onEliminar() {

        eliminarModulo();
    }

    @Override
    protected void onConsultar() {

        cargarTabla();
    }

    private void guardarModulo() {

        try {

            Modulo modulo =
                    new Modulo();

            modulo.setNombre(
                    txtNombre.getText().trim()
            );

            modulo.setDescripcion(
                    txtDescripcion.getText().trim()
            );

            Modulo moduloGuardado =
                    controller.guardar(
                            modulo
                    );

            txtId.setText(
                    String.valueOf(
                            moduloGuardado
                                    .getIdModulo()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Módulo guardado correctamente."
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

    private void modificarModulo() {

        try {

            if (txtId.getText().isBlank()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un registro."
                );

                return;
            }

            Modulo modulo =
                    new Modulo();

            modulo.setIdModulo(
                    Integer.parseInt(
                            txtId.getText()
                    )
            );

            modulo.setNombre(
                    txtNombre.getText().trim()
            );

            modulo.setDescripcion(
                    txtDescripcion.getText().trim()
            );

            controller.actualizar(
                    modulo
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Módulo actualizado correctamente."
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

    private void eliminarModulo() {

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
                            "¿Desea eliminar el módulo?",
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
                    "Módulo eliminado."
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

        List<Modulo> modulos =
                controller.listar();

        for (Modulo modulo : modulos) {

            tableModel.addRow(
                    new Object[] {
                            modulo.getIdModulo(),
                            modulo.getNombre(),
                            modulo.getDescripcion()
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

        txtNombre.setText(
                tableModel.getValueAt(
                        fila,
                        1
                ).toString()
        );

        txtDescripcion.setText(
                tableModel.getValueAt(
                        fila,
                        2
                ).toString()
        );
    }

    private void limpiarFormulario() {

        txtId.setText("");

        txtNombre.setText("");

        txtDescripcion.setText("");

        txtNombre.requestFocus();
    }
}