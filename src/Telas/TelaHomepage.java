package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Classes.User;

public class TelaHomepage {
    private JTextField FieldPesquisaHomepage;
    private JTable TableFunHomepage;
    private JButton ButtonPesquisaHomepage;
    private JButton ButtonFichaHomepage;
    private JButton ButtonRelatorioHomepage;
    private JButton ButtonDesHomepage;
    private JButton ButtonSaidaHomepage;
    private JPanel PanelHomepage;
    private JButton ButtonNewUserHomepage;
    private JButton ButtonNewFunHomepage;
    private JFrame FrameHomepage;

    private ArrayList<User> usuarios;
    private JFrame FrameLogin;

    public TelaHomepage(ArrayList<User> usuarios, JFrame frameLogin, User usuarioLogado){
        this.usuarios = usuarios;
        this.FrameLogin = frameLogin;
        FrameHomepage = new JFrame("RH Manager - Alpha");


        FrameHomepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameHomepage.add(PanelHomepage);
        FrameHomepage.pack();
        FrameHomepage.setVisible(true);

        if(usuarioLogado.getCargo().equals("Admin")){
            ButtonDesHomepage.setVisible(true);
            ButtonNewUserHomepage.setVisible(true);
            ButtonNewFunHomepage.setVisible(true);
        }else{
            ButtonDesHomepage.setVisible(false);
            ButtonNewUserHomepage.setVisible(false);
            ButtonNewFunHomepage.setVisible(false);
        }


        ButtonNewUserHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro telaCadastro = new TelaCadastro(usuarios, FrameHomepage);
                FrameHomepage.setVisible(false);
            }
        });

        ButtonFichaHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaRegistro telaRegistro = new TelaRegistro(usuarios, FrameHomepage, usuarioLogado);
                FrameHomepage.setVisible(false);
            }
        });

        ButtonSaidaHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameHomepage.setVisible(false);
                FrameLogin.setVisible(true);
            }
        });
    }

}
