package TelaLogin;

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
    private JLabel TitlePassword;
    private JLabel MenuTitle;
    private JLabel CadastroTitle;
    private ArrayList<User> usuarios;

    public TelaMenu() {

        FrameMenu = new JFrame("RH Manager - Alpha");
        usuarios = new ArrayList<>();

        ButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User usuarioLogado = realizarLogin();
                if (usuarioLogado != null) {
                    JOptionPane.showMessageDialog(FrameMenu, "Login feito com sucesso!\n" + usuarioLogado);
                    usuarioLogado.identify();
                } else {
                    JOptionPane.showMessageDialog(FrameMenu, "Nome de usuario ou senha inválida!");
                }
            }
        });

        ButtonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User novoUsuario = cadastrarUsuario();
                if (novoUsuario != null) {
                    usuarios.add(novoUsuario);
                    JOptionPane.showMessageDialog(FrameMenu, "Cadastro Realizado com Sucesso!");
                }
            }
        });

        FrameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameMenu.add(PanelMenu);
        FrameMenu.pack();
        FrameMenu.setVisible(true);
    }

    private User cadastrarUsuario() {
        String username = JOptionPane.showInputDialog(FrameMenu, "Insira um nome de usuário:");
        String password = JOptionPane.showInputDialog(FrameMenu, "Insira uma senha:");
        String nome = JOptionPane.showInputDialog(FrameMenu, "Insira seu nome:");
        String cpf = JOptionPane.showInputDialog(FrameMenu, "Insira seu CPF:");
        String email = JOptionPane.showInputDialog(FrameMenu, "Insira seu e-mail:");
        String dataNascimento = JOptionPane.showInputDialog(FrameMenu, "Insira sua data de nascimento:");
        String sexo = JOptionPane.showInputDialog(FrameMenu, "Insira seu sexo:");
        String cargo = JOptionPane.showInputDialog(FrameMenu, "Insira seu cargo:");

        if (username != null && password != null) {
            User usuario = new User();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setDataNascimento(dataNascimento);
            usuario.setSexo(sexo);
            usuario.setCargo(cargo);
            return usuario;
        }
        return null;
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

}
