package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JFrame FrameHomepage;

    public TelaRegistro(JFrame frameHomepage, Funcionarios funcionario){
        FrameRegistro = new JFrame("RH Manager - Alpha");
        this.FrameHomepage = frameHomepage;

        FrameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameRegistro.add(PanelRegistro);
        FrameRegistro.pack();
        FrameRegistro.setVisible(true);

        if (funcionario.getGenero().equals("Feminino")){
            ProfileRegistro.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile F.png")));
        } else if (funcionario.getGenero().equals("Masculino")) {
            ProfileRegistro.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile.png")));
        } else if (funcionario.getGenero().equals("Outros")) {
            ProfileRegistro.setIcon(new ImageIcon(getClass().getResource("/IMG/do-utilizador (1).png")));
        }

        NameTitleRegistro.setText("Nome: " + funcionario.getNome());
        CpfTitleRegistro.setText("CPF: " + funcionario.getCpf());
        DobTitleRegistro.setText("Data de Nascimento: " + funcionario.getDataNascimento());
        EmailTitleRegistro.setText("Email: " + funcionario.getEmail());
        GeneroTitleRegistro.setText("Genero: " + funcionario.getGenero());
        PosTitleRegistro.setText("Cargo: " + funcionario.getCargo());
        TurnTitleRegistro.setText("Horario: " + funcionario.getHorario());


        ButtonSaidaRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameRegistro.setVisible(false);
                FrameHomepage.setVisible(true);
            }
        });
    }
}