package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Classes.Funcionarios;
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
    private ArrayList<Funcionarios> funcionarios;

    public TelaLogin() {

        FrameLogin = new JFrame("RH Manager - Alpha");
        usuarios = new ArrayList<>();
        funcionarios = new ArrayList<>();

        User Admin = new User();
        Admin.setUsername("Admin");
        Admin.setPassword("Admin");
        Admin.setNome("Admin");
        Admin.setCargo("Admin");
        usuarios.add(Admin);

        Funcionarios Teste = new Funcionarios();
        Teste.setNome("Teste");
        Teste.setCpf("Teste");
        Teste.setEmail("Teste");
        Teste.setDataNascimento("Teste");
        Teste.setGenero("Masculino");
        Teste.setHorario("Teste");
        funcionarios.add(Teste);

        ButtonLoginLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User usuarioLogado = realizarLogin();
                Funcionarios funcionarioTeste = Teste;
                if (usuarioLogado != null) {
                    JOptionPane.showMessageDialog(FrameLogin, "Login feito com sucesso!\n");
                    TelaHomepage telaHomepage = new TelaHomepage(usuarios,funcionarios, FrameLogin, usuarioLogado, funcionarioTeste);
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