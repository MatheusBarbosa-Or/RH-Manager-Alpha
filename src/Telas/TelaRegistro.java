package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Classes.User;


public class TelaRegistro {
    private JPanel PanelRegistro;
    private JLabel ProfileRegistro;
    private JLabel TituloRegistro;
    private JLabel NameTitleRegistro;
    private JLabel CpfTitleRegistro;
    private JLabel DobTitleRegistro;
    private JLabel EmailTitleRegistro;
    private JLabel GeneroTitleRegistro;
    private JLabel PosTitleRegistro;
    private JButton ButtonSaidaRegistro;
    private JLabel TurnTitleRegistro;
    private JButton ButtonAdminRegistro;
    private JFrame FrameRegistro;
    private ArrayList<User> usuarios;
    private JFrame FrameHomepage;

    public TelaRegistro(ArrayList<User> usuarios, JFrame frameHomepage, User usuarioLogado){
        FrameRegistro = new JFrame("RH Manager - Alpha");
        this.usuarios = usuarios;
        this.FrameHomepage = frameHomepage;

        FrameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameRegistro.add(PanelRegistro);
        FrameRegistro.pack();
        FrameRegistro.setVisible(true);

        if(usuarioLogado.getUsername().equals("Admin")){
            ButtonAdminRegistro.setVisible(true);
        }else{
            ButtonAdminRegistro.setVisible(false);
        }

        if (usuarioLogado.getGenero().equals("Feminino")){
            ProfileRegistro.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile F.png")));
        } else if (usuarioLogado.getGenero().equals("Masculino")) {
            ProfileRegistro.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile.png")));
        } else if (usuarioLogado.getGenero().equals("Outros")) {
            ProfileRegistro.setIcon(new ImageIcon(getClass().getResource("/IMG/do-utilizador (1).png")));
        }

        NameTitleRegistro.setText("Nome: " + usuarioLogado.getNome());
        CpfTitleRegistro.setText("CPF: " + usuarioLogado.getCpf());
        DobTitleRegistro.setText("Data de Nascimento: " + usuarioLogado.getDataNascimento());
        EmailTitleRegistro.setText("Email: " + usuarioLogado.getEmail());
        GeneroTitleRegistro.setText("Genero: " + usuarioLogado.getGenero());
        PosTitleRegistro.setText("Cargo: " + usuarioLogado.getCargo());
        TurnTitleRegistro.setText("Horario: " + usuarioLogado.getHorario());


        ButtonSaidaRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameRegistro.setVisible(false);
                FrameHomepage.setVisible(true);
            }
        });

        ButtonAdminRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro telaCadastro = new TelaCadastro(usuarios, FrameRegistro);
                FrameRegistro.setVisible(false);
            }
        });

    }
}