package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Classes.User;

public class TelaLogin {

    private JFrame FrameLogin;
    private JPanel PanelLogin;
    private JTextField UsernameFieldLogin;
    private JPasswordField PasswordFieldLogin;
    private JButton ButtonLoginLogin;
    private JButton ButtonCadastroLogin;
    private JLabel IconLogin;
    private JLabel UsernameTitleLogin;
    private JLabel PasswordTitle;
    private JLabel MenuTitleLogin;
    private ArrayList<User> usuarios;

    public TelaLogin() {

        FrameLogin = new JFrame("RH Manager - Alpha");
        usuarios = new ArrayList<>();

        User Admin = new User();
        Admin.setUsername("Admin");
        Admin.setPassword("Admin");
        Admin.setNome("Admin");
        Admin.setEmail("Admin@Admin.com");
        Admin.setGenero("Outros");
        Admin.setCargo("Admin");
        usuarios.add(Admin);

        ButtonLoginLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User usuarioLogado = realizarLogin();
                if (usuarioLogado != null) {
                    JOptionPane.showMessageDialog(FrameLogin, "Login feito com sucesso!\n");
                    TelaRegistro telaRegistro = new TelaRegistro(usuarios, FrameLogin, usuarioLogado);
                    FrameLogin.setVisible(false);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(FrameLogin, "Nome de usuario ou senha inválida!");
                }
            }
        });

        FrameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameLogin.add(PanelLogin);
        FrameLogin.pack();
        FrameLogin.setVisible(true);
    }

    private User realizarLogin() {
        String username = UsernameFieldLogin.getText();
        String password = new String(PasswordFieldLogin.getPassword());

        for (User usuario : usuarios) {
            if (username.equals(usuario.getUsername()) && password.equals(usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }

    public void limparCampos() {
        UsernameFieldLogin.setText("");
        PasswordFieldLogin.setText("");
    }


}