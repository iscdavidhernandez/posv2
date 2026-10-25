package posv2.view.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public abstract class BaseCrudFrame extends JInternalFrame {

    // Tabla
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected JScrollPane scrollPane;

    // Paneles
    protected JPanel pnlCenter;
    protected JPanel pnlButtons;

    // Botones CRUD
    protected JButton btnNuevo;
    protected JButton btnGuardar;
    protected JButton btnModificar;
    protected JButton btnEliminar;
    protected JButton btnConsultar;

    public BaseCrudFrame(String titulo) {

        super(
                titulo,
                true,   // closable
                true,   // resizable
                true,   // maximizable
                true    // iconifiable
        );

        configurarVentana();

        inicializarComponentes();

        registrarEventos();
    }

    private void configurarVentana() {

        setSize(900, 600);

        setVisible(true);

        setLayout(
                new BorderLayout()
        );
    }

    private void inicializarComponentes() {

        crearTabla();

        crearPanelFormulario();

        crearPanelBotones();
    }

    private void crearTabla() {

        tableModel =
                new DefaultTableModel();

        table =
                new JTable(tableModel);

        scrollPane =
                new JScrollPane(table);

        scrollPane.setPreferredSize(
                new Dimension(
                        800,
                        250
                )
        );

        add(
                scrollPane,
                BorderLayout.NORTH
        );
    }

    private void crearPanelFormulario() {

        pnlCenter =
                new JPanel();

        add(
                pnlCenter,
                BorderLayout.CENTER
        );
    }

    private void crearPanelBotones() {

        pnlButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

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

        pnlButtons.add(btnNuevo);
        pnlButtons.add(btnGuardar);
        pnlButtons.add(btnModificar);
        pnlButtons.add(btnEliminar);
        pnlButtons.add(btnConsultar);

        add(
                pnlButtons,
                BorderLayout.SOUTH
        );
    }

    private void registrarEventos() {

        btnNuevo.addActionListener(
                e -> onNuevo()
        );

        btnGuardar.addActionListener(
                e -> onGuardar()
        );

        btnModificar.addActionListener(
                e -> onModificar()
        );

        btnEliminar.addActionListener(
                e -> onEliminar()
        );

        btnConsultar.addActionListener(
                e -> onConsultar()
        );
    }

    protected abstract void onNuevo();

    protected abstract void onGuardar();

    protected abstract void onModificar();

    protected abstract void onEliminar();

    protected abstract void onConsultar();
}