package org.example.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{

//    private Conexion cn = new Conexion();

    private JLabel lbl_titulo, lbl_mensaje, lbl_usuario, lbl_password;
    private JSeparator sep_datos, sep_botones;
    private JTextField txt_usuario;
    private JPasswordField txt_password;
    private JButton btn_ingresar, btn_registrar;

    public Login() {
        super();

        ConfigurarVentana();
        IniciarControles();

    }

    private void ConfigurarVentana() {
        this.setTitle("HILOS ANDINOS");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void IniciarControles() {
        // Labels
        lbl_titulo = new JLabel();
        lbl_titulo.setText("BIENVENIDO/A");
        lbl_titulo.setBounds(100, 20, 300, 25);
        lbl_titulo.setForeground(new Color(9, 92, 209));
        lbl_titulo.setFont(new Font("Verdana", Font.BOLD, 24));

        lbl_mensaje = new JLabel();
        lbl_mensaje.setText("inicia sesion o registrate");
        lbl_mensaje.setBounds(20, 60, 300, 25);



        sep_datos = new JSeparator();
        sep_datos.setBounds(20, 90, 340, 10);



        lbl_usuario = new JLabel();
        lbl_usuario.setText("USUARIO");
        lbl_usuario.setBounds(30, 120, 120, 25);
        lbl_usuario.setFont(new Font("Verdana", Font.BOLD, 14));

        txt_usuario = new JTextField();
        txt_usuario.setBounds(150, 120, 200, 25);


        lbl_password = new JLabel();
        lbl_password.setText("CONTRASEÑA");
        lbl_password.setBounds(30, 180, 120, 25);
        lbl_password.setFont(new Font("Verdana", Font.BOLD, 14));

        txt_password = new JPasswordField();
        txt_password.setBounds(150, 180, 200, 25);



        sep_botones = new JSeparator();
        sep_botones.setBounds(20, 240, 340, 10);



        btn_ingresar = new JButton();
        btn_ingresar.setText("INGRESAR");
        btn_ingresar.setFont(new Font("Verdana", Font.BOLD, 15));
        btn_ingresar.setBounds(90, 270, 200, 50);
        btn_ingresar.addActionListener(this);

        btn_registrar = new JButton();
        btn_registrar.setText("REGISTRAR");
        btn_registrar.setFont(new Font("Verdana", Font.BOLD, 15));
        btn_registrar.setBounds(90, 340, 200, 50);
        btn_registrar.addActionListener(this);







        this.add(lbl_titulo);
        this.add(lbl_mensaje);

        this.add(sep_datos);

        this.add(lbl_usuario);
        this.add(txt_usuario);

        this.add(lbl_password);
        this.add(txt_password);

        this.add(sep_botones);

        this.add(btn_ingresar);
        this.add(btn_registrar);

    }

//    public static void main(String[] args) {
//
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_ingresar) {
            System.out.println("BOTON INGRESAR");
            // AQUI SE EVALUA EL TIPO DE USUARIO Y LUEGO SE LANZA EL FRAME CORRESPONDIENTE
//            Usuario usuario = new Usuario();
//            // extraer los datos
//            usuario.setNombre(txt_usuario.getText());
//            usuario.setPassword(txt_password.getText());
//
//
//            UsuarioController usuc = new UsuarioController();
//
//            int salida = usuc.validarUsuario(usuario);
//
//            // AQUI SE DEFINE EL RESULTADO DE LA VALIDACION DEL USUARIO, EXISTE O NO
//            if (salida == 1) {
//                // AQUI SE DEBE DEFINIR EL ROL DEL USUARIO Y DEPENDIENDO DEL ROL SE LANZAR EL FRAME CORRESPONDIENTE
//                int usuarioRol = usuc.obtenerIdRol(usuario);
//
//                if (usuarioRol == 1) {
//                    // SE DESPLIEGA EL FRAME DEL ADMINISTRADOR
//                } else if (usuarioRol == 2) {
//                    // SE DESPLIEGA EL FRAME DEL USUARIO
//                }
//            } else if (salida == 0) {
//                lbl_mensaje.setText("Datos Incorrectos");
//                lbl_mensaje.setForeground(new Color(255, 0, 0));
//            }


        } else if (e.getSource() == btn_registrar) {
            System.out.println("BOTON REGISTRAR");
//            // AQUI SE DEBE LANZAR EL FRAME DE REGISTRO
//
//            btn_ingresar.setEnabled(false);
//
//            Registro registro = Registro.getInstance();
//
//
//            registro.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    // Cuando se cierre el Frame de registro, habilitar el botón de ingreso
//                    btn_ingresar.setEnabled(true);
//                }
//            });
//
//            registro.setVisible(true);
//            registro.setLocationRelativeTo(null);
        }
    }
}

