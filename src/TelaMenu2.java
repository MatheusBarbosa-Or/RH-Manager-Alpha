import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaMenu2 {

    private JFrame FrameMenu2;
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


    public TelaMenu2() {

        FrameMenu2 = new JFrame("RH Manager - Alpha");
        usuarios = new ArrayList<>();

        ButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User usuarioLogado = realizarLogin();
                if (usuarioLogado != null) {
                    JOptionPane.showMessageDialog(FrameMenu2, "Login feito com sucesso!\n" + usuarioLogado);
                    usuarioLogado.identify();
                } else {
                    JOptionPane.showMessageDialog(FrameMenu2, "Nome de usuario ou senha inválida!");
                }

            }
        });


        ButtonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User novoUsuario = cadastrarUsuario();
                if (novoUsuario != null) {
                    usuarios.add(novoUsuario);
                    JOptionPane.showMessageDialog(FrameMenu2, "Cadastro Realizado com Sucesso!");
                }

            }
        });

        FrameMenu2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameMenu2.add(PanelMenu);
        FrameMenu2.pack();
        FrameMenu2.setVisible(true);
    }

    private User cadastrarUsuario() {
        String username = JOptionPane.showInputDialog(FrameMenu2,"Insira um nome de usuário:");
        String password = JOptionPane.showInputDialog(FrameMenu2,"Insira uma senha:");
        String nome = JOptionPane.showInputDialog(FrameMenu2,"Insira seu nome:");
        String cpf = JOptionPane.showInputDialog(FrameMenu2,"Insira seu CPF:");
        String email = JOptionPane.showInputDialog(FrameMenu2,"Insira seu e-mail:");
        String dataNascimento = JOptionPane.showInputDialog(FrameMenu2,"Insira sua data de nascimento:");
        String sexo = JOptionPane.showInputDialog(FrameMenu2,"Insira seu sexo:");
        String cargo = JOptionPane.showInputDialog(FrameMenu2,"Insira seu cargo:");

        if (username != null && password != null) {
            User usuario = new User();
            usuario.username = username;
            usuario.password = password;
            usuario.nome = nome;
            usuario.cpf = cpf;
            usuario.email = email;
            usuario.dataNascimento = dataNascimento;
            usuario.sexo = sexo;
            usuario.cargo = cargo;
            return usuario;
        }
        return null;
    }

    private User realizarLogin() {
        String username = UsernameField.getText();
        String password = new String(UserPasswordField.getPassword());

        for (User usuario : usuarios) {
            if (username.equals(usuario.username) && password.equals(usuario.password)) {
                return usuario;
            }
        }
        return null;
    }




}
