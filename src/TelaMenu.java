import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaMenu {

    private JFrame FrameMenu;
    private JPanel PanelMenu;
    private JLabel TitleMenu;
    private JLabel TitleCadastro;
    private JButton ButtonCadastro;
    private JLabel TitleLogin;
    private JLabel TitleUsername;
    private JTextField FieldUsername;
    private JLabel TitlePassword;
    private JPasswordField passwordFieldLogin;
    private JButton ButtonLogin;
    private ArrayList<User> usuarios;

    public TelaMenu() {

        FrameMenu = new JFrame("Menu de Usuários");
        usuarios = new ArrayList<>();

        ButtonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User novoUsuario = cadastrarUsuario();
                if (novoUsuario != null) {
                    usuarios.add(novoUsuario);
                    JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso!");
                }
            }
        });

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

        FrameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameMenu.add(PanelMenu);
        FrameMenu.pack();
        FrameMenu.setVisible(true);
    }

    private User cadastrarUsuario() {
        String username = JOptionPane.showInputDialog(FrameMenu,"Insira um nome de usuário:");
        String password = JOptionPane.showInputDialog(FrameMenu,"Insira uma senha:");
        String nome = JOptionPane.showInputDialog(FrameMenu,"Insira seu nome:");
        String cpf = JOptionPane.showInputDialog(FrameMenu,"Insira seu CPF:");
        String email = JOptionPane.showInputDialog(FrameMenu,"Insira seu e-mail:");
        String dataNascimento = JOptionPane.showInputDialog(FrameMenu,"Insira sua data de nascimento:");
        String sexo = JOptionPane.showInputDialog(FrameMenu,"Insira seu sexo:");
        String cargo = JOptionPane.showInputDialog(FrameMenu,"Insira seu cargo:");

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
        String username = FieldUsername.getText();
        String password = new String(passwordFieldLogin.getPassword());

        for (User usuario : usuarios) {
            if (username.equals(usuario.username) && password.equals(usuario.password)) {
                return usuario;
            }
        }
        return null;
    }

}
