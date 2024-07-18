package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Classes.Funcionarios;
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
    private ArrayList<Funcionarios> funcionarios;
    private JFrame FrameHomepage;

    public TelaRegistro(ArrayList<User> usuarios, ArrayList<Funcionarios> funcionarios,JFrame frameHomepage, User usuarioLogado, Funcionarios funcionarioTeste){
        FrameRegistro = new JFrame("RH Manager - Alpha");
        this.usuarios = usuarios;
        this.funcionarios = funcionarios;
        this.FrameHomepage = frameHomepage;

        FrameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameRegistro.add(PanelRegistro);
        FrameRegistro.pack();
        FrameRegistro.setVisible(true);

        if (funcionarioTeste.getGenero().equals("Feminino")){
            ProfileRegistro.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile F.png")));
        } else if (funcionarioTeste.getGenero().equals("Masculino")) {
            ProfileRegistro.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile.png")));
        } else if (funcionarioTeste.getGenero().equals("Outros")) {
            ProfileRegistro.setIcon(new ImageIcon(getClass().getResource("/IMG/do-utilizador (1).png")));
        }

        NameTitleRegistro.setText("Nome: " + funcionarioTeste.getNome());
        CpfTitleRegistro.setText("CPF: " + funcionarioTeste.getCpf());
        DobTitleRegistro.setText("Data de Nascimento: " + funcionarioTeste.getDataNascimento());
        EmailTitleRegistro.setText("Email: " + funcionarioTeste.getEmail());
        GeneroTitleRegistro.setText("Genero: " + funcionarioTeste.getGenero());
        PosTitleRegistro.setText("Cargo: " + funcionarioTeste.getCargo());
        TurnTitleRegistro.setText("Horario: " + funcionarioTeste.getHorario());


        ButtonSaidaRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameRegistro.setVisible(false);
                FrameHomepage.setVisible(true);
            }
        });
    }
}