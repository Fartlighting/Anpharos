package  anpharos.app;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import anpharos.sphero.Sphero;
import anpharos.gui.Anpharos;
import anpharos.structures.Queue;

public class Login extends JFrame implements ActionListener{

    private Hashtable<Integer,Usuario> usuarios;
    private JLabel usuario, contrasena;
    private JButton registrarse, entrar;
    private JTextField u, co;

    public Login(Hashtable hashtable){
        usuarios = hashtable;
        setTitle("Registro");
        setSize(300,100);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        initComponents(c);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initComponents(GridBagConstraints c){
        usuario = new JLabel("Usuario: ");
        contrasena = new JLabel("Contraseña: ");
        u = new JTextField(6);
        co = new JTextField(6);
        registrarse = new JButton("Crear");
        registrarse.addActionListener(this);
        registrarse.setActionCommand("registrarse");
        entrar = new JButton("Entrar");
        entrar.addActionListener(this);
        entrar.setActionCommand("entrar");

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;

        c.gridx = 0;
        c.gridy = 0;
        add(usuario,c);

        c.gridy = 1;
        add(contrasena,c);

        c.gridy = 2;
        add(registrarse,c);

        c.gridx = 1;
        add(entrar,c);

        c.gridy = 1;
        add(co,c);

        c.gridy = 0;
        add(u,c);
    }

    public void actionPerformed(ActionEvent e){
        String nombre = u.getText();
        String contra = co.getText();
        String code = nombre+contra;
        int hash = code.hashCode();
        if ("entrar".equals(e.getActionCommand())) {
            if (usuarios.get(hash) != null) {
                Usuario u = usuarios.get(hash);
                Anpharos a = new Anpharos(u);
                dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas o usuario inexistente");
            }
        }else if ("registrarse".equals(e.getActionCommand())) {
            if (usuarios.get(hash) == null) {
                Usuario u = new Usuario(nombre, contra);
                usuarios.put(hash,u);
                Anpharos a = new Anpharos(u);
                JOptionPane.showMessageDialog(null, "Tu cuenta ha sido creada");
                dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Esa cuenta ya existe");
            }
        }
    }
}
