package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Classes.User;

public class TelaMenu {

    private JFrame FrameMenu;
    private JPanel PanelMenu;
    private JTextField UsernameField;
    private JPasswordField UserPasswordField;
    private JButton ButtonLogin;
    private JButton ButtonCadastro;
    private JLabel IconMenu;
    private JLabel UsernameTitle;
    private JLabel PasswordTitle;
    private JLabel MenuTitle;
    private JLabel CadastroTitle;
    private ArrayList<User> usuarios;

    public TelaMenu() {

        FrameMenu = new JFrame("RH Manager - Alpha");
        usuarios = new ArrayList<>();

        User Admin = new User();
        Admin.setUsername("Admin");
        Admin.setPassword("Admin");
        Admin.setNome("Admin");
        Admin.setCargo("Admin");
        usuarios.add(Admin);

        ButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User usuarioLogado = realizarLogin();
                if (usuarioLogado != null) {
                    JOptionPane.showMessageDialog(FrameMenu, "Login feito com sucesso!\n");
                    TelaInicial telaInicial = new TelaInicial(usuarios, FrameMenu, usuarioLogado);
                    FrameMenu.setVisible(false);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(FrameMenu, "Nome de usuario ou senha inválida!");
                }
            }
        });

        ButtonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro telaCadastro = new TelaCadastro(usuarios,FrameMenu);
                FrameMenu.setVisible(false);
                limparCampos();
            }
        });

        FrameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameMenu.add(PanelMenu);
        FrameMenu.pack();
        FrameMenu.setVisible(true);
    }

    private User realizarLogin() {
        String username = UsernameField.getText();
        String password = new String(UserPasswordField.getPassword());

        for (User usuario : usuarios) {
            if (username.equals(usuario.getUsername()) && password.equals(usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }

    public void limparCampos() {
        UsernameField.setText("");
        UserPasswordField.setText("");
    }


}