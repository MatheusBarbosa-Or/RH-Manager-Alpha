package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        Teste.setCargo("Teste");
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

    private Connection connect() {

        String url = "jdbc:sqlite:DataBase/RH_Manager_DB.db"; // Coloque o caminho do seu banco de dados aqui
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    private User realizarLogin() {
        String username = UsernameFieldLogin.getText();
        String password = new String(PasswordFieldLogin.getPassword());
        String sql = "SELECT * FROM Usuarios WHERE username = ? AND password = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the values
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            // Loop through the result set
            if (rs.next()) {
                User usuario = new User();
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCargo(rs.getString("cargo"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public void limparCampos() {
        UsernameFieldLogin.setText("");
        PasswordFieldLogin.setText("");
    }


}