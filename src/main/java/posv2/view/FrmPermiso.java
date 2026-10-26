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
import posv2.controller.PermisoController;

import posv2.model.Grupo;
import posv2.model.Modulo;
import posv2.model.Permiso;

import posv2.view.base.BaseCrudFrame;

public class FrmPermiso extends BaseCrudFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtFormularioDestino;

    private JComboBox<Modulo> cmbModulo;
    private JComboBox<Grupo> cmbGrupo;

    private final PermisoController controller;
    private final GrupoController grupoController;
    private final ModuloController moduloController;

    public FrmPermiso() {

        super("Catálogo de Permisos");

        controller = new PermisoController();

        grupoController = new GrupoController();

        moduloController = new ModuloController();

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
        tableModel.addColumn("GRUPO");
        tableModel.addColumn("NOMBRE");
        tableModel.addColumn("DESCRIPCIÓN");
        tableModel.addColumn("FORMULARIO");
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

        // GRUPO

        gbc.gridx = 0;
        gbc.gridy = 2;

        pnlCenter.add(
                new JLabel("Grupo:"),
                gbc
        );

        cmbGrupo =
                new JComboBox<>();

        gbc.gridx = 1;

        pnlCenter.add(
                cmbGrupo,
                gbc
        );

        // NOMBRE

        gbc.gridx = 0;
        gbc.gridy = 3;

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
        gbc.gridy = 4;

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

        // FORMULARIO

        gbc.gridx = 0;
        gbc.gridy = 5;

        pnlCenter.add(
                new JLabel("Formulario:"),
                gbc
        );

        txtFormularioDestino =
                new JTextField(30);

        gbc.gridx = 1;

        pnlCenter.add(
                txtFormularioDestino,
                gbc
        );
    }

    private void registrarEventos() {

        cmbModulo.addActionListener(
                e -> cargarGrupos()
        );

        table.getSelectionModel()
             .addListSelectionListener(
                     e -> {

                         if(!e.getValueIsAdjusting()) {

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

        cargarGrupos();
    }

    private void cargarGrupos() {

        cmbGrupo.removeAllItems();

        Modulo modulo =
                (Modulo)
                        cmbModulo
                                .getSelectedItem();

        if(modulo == null) {

            return;
        }

        grupoController
                .buscarPorModulo(
                        modulo.getIdModulo()
                )
                .forEach(
                        cmbGrupo::addItem
                );
    }

    @Override
    protected void onNuevo() {

        limpiarFormulario();
    }

    @Override
    protected void onGuardar() {

        guardarPermiso();
    }

    @Override
    protected void onModificar() {

        modificarPermiso();
    }

    @Override
    protected void onEliminar() {

        eliminarPermiso();
    }

    @Override
    protected void onConsultar() {

        cargarTabla();
    }

    private void guardarPermiso() {

        try {

            Permiso permiso =
                    new Permiso();

            permiso.setGrupo(
                    (Grupo)
                            cmbGrupo.getSelectedItem()
            );

            permiso.setNombre(
                    txtNombre.getText().trim()
            );

            permiso.setDescripcion(
                    txtDescripcion.getText().trim()
            );

            permiso.setFormularioDestino(
                    txtFormularioDestino.getText().trim()
            );

            Permiso guardado =
                    controller.guardar(
                            permiso
                    );

            txtId.setText(
                    String.valueOf(
                            guardado.getIdPermiso()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Permiso guardado correctamente."
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

    private void modificarPermiso() {

        try {

            if(txtId.getText().isBlank()) {

                return;
            }

            Permiso permiso =
                    new Permiso();

            permiso.setIdPermiso(
                    Integer.parseInt(
                            txtId.getText()
                    )
            );

            permiso.setGrupo(
                    (Grupo)
                            cmbGrupo.getSelectedItem()
            );

            permiso.setNombre(
                    txtNombre.getText().trim()
            );

            permiso.setDescripcion(
                    txtDescripcion.getText().trim()
            );

            permiso.setFormularioDestino(
                    txtFormularioDestino.getText().trim()
            );

            controller.actualizar(
                    permiso
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Permiso actualizado."
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

    private void eliminarPermiso() {

        try {

            if(txtId.getText().isBlank()) {

                return;
            }

            int respuesta =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Eliminar permiso?",
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

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void cargarTabla() {

        tableModel.setRowCount(0);

        List<Permiso> permisos =
                controller.listar();

        for(Permiso permiso : permisos) {

        	tableModel.addRow(
        	        new Object[] {

        	                permiso.getIdPermiso(),

        	                permiso.getGrupo()
        	                       .getModulo()
        	                       .getNombre(),

        	                permiso.getGrupo()
        	                       .getNombre(),

        	                permiso.getNombre(),

        	                permiso.getDescripcion(),

        	                permiso.getFormularioDestino()
        	        }
        	);
        }
    }

    private void seleccionarFila() {

        int fila = table.getSelectedRow();

        if (fila < 0) {
            return;
        }

        int idPermiso =
                Integer.parseInt(
                        tableModel
                                .getValueAt(fila, 0)
                                .toString()
                );

        Permiso permiso =
                controller.buscarPorId(
                        idPermiso
                );

        if (permiso == null) {
            return;
        }

        txtId.setText(
                String.valueOf(
                        permiso.getIdPermiso()
                )
        );

        txtNombre.setText(
                permiso.getNombre()
        );

        txtDescripcion.setText(
                permiso.getDescripcion()
        );

        txtFormularioDestino.setText(
                permiso.getFormularioDestino()
        );

        seleccionarModulo(
                permiso.getGrupo()
                       .getModulo()
        );

        seleccionarGrupo(
                permiso.getGrupo()
        );
    }
    
    private void seleccionarModulo(
            Modulo moduloBuscado) {

        if (moduloBuscado == null) {
            return;
        }

        for (int i = 0;
             i < cmbModulo.getItemCount();
             i++) {

            Modulo modulo =
                    cmbModulo.getItemAt(i);

            if (modulo.getIdModulo()
                    == moduloBuscado.getIdModulo()) {

                cmbModulo.setSelectedIndex(i);

                cargarGrupos();

                break;
            }
        }
    }
    
    private void seleccionarGrupo(
            Grupo grupoBuscado) {

        if (grupoBuscado == null) {
            return;
        }

        for (int i = 0;
             i < cmbGrupo.getItemCount();
             i++) {

            Grupo grupo =
                    cmbGrupo.getItemAt(i);

            if (grupo.getIdGrupo()
                    == grupoBuscado.getIdGrupo()) {

                cmbGrupo.setSelectedIndex(i);

                break;
            }
        }
    }

    private void limpiarFormulario() {

        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtFormularioDestino.setText("");

        if (cmbModulo.getItemCount() > 0) {

            cmbModulo.setSelectedIndex(0);

            cargarGrupos();
        }

        txtNombre.requestFocus();
    }
}