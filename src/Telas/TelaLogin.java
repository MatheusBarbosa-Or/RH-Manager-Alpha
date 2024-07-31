package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Classes.Funcionarios;
import Classes.User;
import DbConnect.DbConnection;

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
    private JLabel DbConnectionIcon;

    public TelaLogin() {

        FrameLogin = new JFrame("RH Manager - Alpha");
        FrameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameLogin.setSize(600, 360);
        FrameLogin.setLocationRelativeTo(null);
        FrameLogin.add(PanelLogin);
        FrameLogin.pack();
        FrameLogin.setVisible(true);

        ButtonLoginLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User usuarioLogado = realizarLogin();
                if (usuarioLogado != null) {
                    JOptionPane.showMessageDialog(FrameLogin, "Login feito com sucesso!\n");
                    TelaHomepage telaHomepage = new TelaHomepage(FrameLogin, usuarioLogado);
                    FrameLogin.setVisible(false);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(FrameLogin, "Nome de usuario ou senha inválida!");
                }
            }
        });

    }

    private User realizarLogin() {
        String username = UsernameFieldLogin.getText();
        String password = new String(PasswordFieldLogin.getPassword());
        String sql = "SELECT * FROM Usuarios WHERE username = ? AND password = ?";

        try (Connection conn = DbConnection.connect();
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
                usuario.setUsuarioid(rs.getInt("Id"));
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