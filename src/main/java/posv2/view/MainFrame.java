package posv2.view;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JDesktopPane desktopPane;

    private JMenuBar menuBar;

    private JMenu menuCatalogos;
    private JMenu menuSeguridad;

    private JMenuItem itemEmpleado;
    private JMenuItem itemUsuario;
    private JMenuItem itemModulo;
    private JMenuItem itemGrupo;
    private JMenuItem itemPermiso;

    private JMenuItem itemRol;
    private JMenuItem itemRolPermiso;

    public MainFrame() {

        inicializarComponentes();

        configurarVentana();

        registrarEventos();
    }

    private void configurarVentana() {

        setTitle("Sistema Académico");

        setSize(1200, 800);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
    }

    private void inicializarComponentes() {

        setLayout(new BorderLayout());

        desktopPane = new JDesktopPane();

        add(
                desktopPane,
                BorderLayout.CENTER
        );

        crearMenu();
    }

    private void crearMenu() {

        menuBar = new JMenuBar();

        menuCatalogos =
                new JMenu("Catálogos");

        menuSeguridad =
                new JMenu("Seguridad");

        itemEmpleado =
                new JMenuItem("Empleados");

        itemUsuario =
                new JMenuItem("Usuarios");

        itemModulo =
                new JMenuItem("Módulos");

        itemGrupo =
                new JMenuItem("Grupos");

        itemPermiso =
                new JMenuItem("Permisos");

        itemRol =
                new JMenuItem("Roles");

        itemRolPermiso =
                new JMenuItem(
                        "Roles y Permisos"
                );

        menuCatalogos.add(itemEmpleado);
        menuCatalogos.add(itemUsuario);
        menuCatalogos.addSeparator();
        menuCatalogos.add(itemModulo);
        menuCatalogos.add(itemGrupo);
        menuCatalogos.add(itemPermiso);

        menuSeguridad.add(itemRol);
        menuSeguridad.add(itemRolPermiso);

        menuBar.add(menuCatalogos);
        menuBar.add(menuSeguridad);

        setJMenuBar(menuBar);
    }

    private void registrarEventos() {

        itemRol.addActionListener(
                e -> abrirFrmRol()
        );

        itemModulo.addActionListener(
                e -> abrirFrmModulo()
        );

        itemGrupo.addActionListener(
                e -> abrirFrmGrupo()
        );

        itemPermiso.addActionListener(
                e -> abrirFrmPermiso()
        );

        itemEmpleado.addActionListener(
                e -> abrirFrmEmpleado()
        );

        itemUsuario.addActionListener(
                e -> abrirFrmUsuario()
        );

        itemRolPermiso.addActionListener(
                e -> abrirFrmRolPermiso()
        );
    }

    private void abrirFrmRol() {

        for(var frame : desktopPane.getAllFrames()) {

            if(frame instanceof FrmRol) {

                try {

                    frame.setSelected(true);

                } catch (Exception e) {

                    e.printStackTrace();
                }

                return;
            }
        }

        FrmRol frm = new FrmRol();

        desktopPane.add(frm);

        frm.setVisible(true);
    }

    private void abrirFrmModulo() {

        for(var frame : desktopPane.getAllFrames()) {

            if(frame instanceof FrmModulo) {

                try {

                    frame.setSelected(true);

                } catch (Exception e) {

                    e.printStackTrace();
                }

                return;
            }
        }

        FrmModulo frm = new FrmModulo();

        desktopPane.add(frm);

        frm.setVisible(true);
    }

    private void abrirFrmGrupo() {

        // Pendiente
    }

    private void abrirFrmPermiso() {

        // Pendiente
    }

    private void abrirFrmEmpleado() {

        // Pendiente
    }

    private void abrirFrmUsuario() {

        // Pendiente
    }

    private void abrirFrmRolPermiso() {

        // Pendiente
    }
    
    
    

}
