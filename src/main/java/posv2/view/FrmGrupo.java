package posv2.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import posv2.controller.GrupoController;
import posv2.controller.ModuloController;
import posv2.model.Grupo;
import posv2.model.Modulo;
import posv2.view.base.BaseCrudFrame;

public class FrmGrupo extends BaseCrudFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;

    private JComboBox<Modulo> cmbModulo;

    private final GrupoController controller;
    private final ModuloController moduloController;

    public FrmGrupo() {

        super("Catálogo de Grupos");

        controller = new GrupoController();

        moduloController =
                new ModuloController();

        configurarTabla();

        crearFormulario();

        cargarModulos();

        registrarEventos();

        cargarTabla();

        revalidate();
        repaint();
    }

    private void configurarTabla() {

        tableModel.setColumnCount(0);

        tableModel.addColumn("ID");
        tableModel.addColumn("MÓDULO");
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

        // MODULO

        gbc.gridx = 0;
        gbc.gridy = 1;

        pnlCenter.add(
                new JLabel("Módulo:"),
                gbc
        );

        cmbModulo =
                new JComboBox<>();

        gbc.gridx = 1;

        pnlCenter.add(
                cmbModulo,
                gbc
        );

        // NOMBRE

        gbc.gridx = 0;
        gbc.gridy = 2;

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

        // DESCRIPCIÓN

        gbc.gridx = 0;
        gbc.gridy = 3;

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

    private void cargarModulos() {

        cmbModulo.removeAllItems();

        moduloController
                .listar()
                .forEach(
                        cmbModulo::addItem
                );
    }

    @Override
    protected void onNuevo() {

        limpiarFormulario();
    }

    @Override
    protected void onGuardar() {

        guardarGrupo();
    }

    @Override
    protected void onModificar() {

        modificarGrupo();
    }

    @Override
    protected void onEliminar() {

        eliminarGrupo();
    }

    @Override
    protected void onConsultar() {

        cargarTabla();
    }

    private void guardarGrupo() {

        try {

            Grupo grupo =
                    new Grupo();

            grupo.setModulo(
                    (Modulo)
                            cmbModulo.getSelectedItem()
            );

            grupo.setNombre(
                    txtNombre.getText().trim()
            );

            grupo.setDescripcion(
                    txtDescripcion.getText().trim()
            );

            Grupo grupoGuardado =
                    controller.guardar(
                            grupo
                    );

            txtId.setText(
                    String.valueOf(
                            grupoGuardado
                                    .getIdGrupo()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Grupo guardado correctamente."
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

    private void modificarGrupo() {

        try {

            if (txtId.getText().isBlank()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un registro."
                );

                return;
            }

            Grupo grupo =
                    new Grupo();

            grupo.setIdGrupo(
                    Integer.parseInt(
                            txtId.getText()
                    )
            );

            grupo.setModulo(
                    (Modulo)
                            cmbModulo.getSelectedItem()
            );

            grupo.setNombre(
                    txtNombre.getText().trim()
            );

            grupo.setDescripcion(
                    txtDescripcion.getText().trim()
            );

            controller.actualizar(
                    grupo
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Grupo actualizado."
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

    private void eliminarGrupo() {

        try {

            if (txtId.getText().isBlank()) {

                return;
            }

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Eliminar grupo?",
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
                    "Grupo eliminado."
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

        List<Grupo> grupos =
                controller.listar();

        for (Grupo grupo : grupos) {

            tableModel.addRow(
                    new Object[]{
                            grupo.getIdGrupo(),
                            grupo.getModulo()
                                 .getNombre(),
                            grupo.getNombre(),
                            grupo.getDescripcion()
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
                tableModel
                        .getValueAt(
                                fila,
                                0
                        )
                        .toString()
        );

        String moduloNombre =
                tableModel
                        .getValueAt(
                                fila,
                                1
                        )
                        .toString();

        txtNombre.setText(
                tableModel
                        .getValueAt(
                                fila,
                                2
                        )
                        .toString()
        );

        txtDescripcion.setText(
                tableModel
                        .getValueAt(
                                fila,
                                3
                        )
                        .toString()
        );

        for (int i = 0;
             i < cmbModulo.getItemCount();
             i++) {

            Modulo modulo =
                    cmbModulo.getItemAt(i);

            if (modulo.getNombre()
                      .equals(moduloNombre)) {

                cmbModulo.setSelectedItem(
                        modulo
                );

                break;
            }
        }
    }

    private void limpiarFormulario() {

        txtId.setText("");

        txtNombre.setText("");

        txtDescripcion.setText("");

        if (cmbModulo.getItemCount() > 0) {

            cmbModulo.setSelectedIndex(0);
        }

        txtNombre.requestFocus();
    }
}
