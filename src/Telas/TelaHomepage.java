package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Classes.Funcionarios;
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
    private ArrayList<Funcionarios> funcionarios;
    private JFrame FrameLogin;
    private int novoUsuario_Funcionario;

    public TelaHomepage(ArrayList<User> usuarios, ArrayList<Funcionarios> funcionarios, JFrame frameLogin, User usuarioLogado, Funcionarios funcionarioTeste){
        this.usuarios = usuarios;
        this.funcionarios = funcionarios;
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
                novoUsuario_Funcionario = 1;
                TelaCadastro telaCadastro = new TelaCadastro(usuarios, funcionarios,FrameHomepage, novoUsuario_Funcionario);
                FrameHomepage.setVisible(false);
            }
        });

        ButtonNewFunHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoUsuario_Funcionario = 2;
                TelaCadastro telaCadastro = new TelaCadastro(usuarios, funcionarios,FrameHomepage, novoUsuario_Funcionario);
                FrameHomepage.setVisible(false);
            }
        });

        ButtonFichaHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaRegistro telaRegistro = new TelaRegistro(usuarios, funcionarios,FrameHomepage, usuarioLogado, funcionarioTeste);
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
