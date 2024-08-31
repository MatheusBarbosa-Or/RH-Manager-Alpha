package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.User;
import DbConnect.DbConnection;

public class TelaLogin {
    //Criação dos elementos graficos na tela
    private final JFrame FrameLogin;
    private JPanel PanelLogin;
    private JTextField UsernameFieldLogin;
    private JPasswordField PasswordFieldLogin;
    private JButton ButtonLogin;
    private JButton ButtonCadastroLogin;
    private JLabel IconLogin;
    private JLabel UsernameTitleLogin;
    private JLabel PasswordTitle;
    private JLabel MenuTitleLogin;
    private JLabel DbConnectionIcon;

    public TelaLogin() {
        //Configurações padrão da tela
        FrameLogin = new JFrame("RH Manager - Alpha");
        FrameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameLogin.setSize(600, 360);
        FrameLogin.setLocationRelativeTo(null);
        FrameLogin.add(PanelLogin);
        FrameLogin.pack();
        FrameLogin.setVisible(true);

        ButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User usuarioLogado = realizarLogin();
                if (usuarioLogado != null) {
                    JOptionPane.showMessageDialog(FrameLogin, "Login feito com sucesso!\n");
                    new TelaHomepage(FrameLogin, usuarioLogado);
                    FrameLogin.setVisible(false);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(FrameLogin, "Nome de usuario ou senha inválida!");
                }
            }
        });

    }

    private User realizarLogin() {
        //Função responsável por enviar os parametros do usuario para verificação do login
        String username = UsernameFieldLogin.getText();
        String password = new String(PasswordFieldLogin.getPassword());

        return DbConnection.realizarLogin(username, password);
    }

    public void limparCampos() {
        //Função para evitar que informações sejam mantidas quando a tela voltar a ficar visivel.
        UsernameFieldLogin.setText("");
        PasswordFieldLogin.setText("");
    }

}