package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Funcionarios;


public class TelaFicha {
    private JPanel PanelFicha;
    private JLabel ProfileFicha;
    private JLabel TitleFicha;
    private JLabel NameTitleFicha;
    private JLabel CpfTitleFicha;
    private JLabel DobTitleFicha;
    private JLabel EmailTitleFicha;
    private JLabel GeneroTitleFicha;
    private JLabel PosTitleFicha;
    private JButton ButtonSaidaFicha;
    private JLabel TurnTitleFicha;
    private JButton ButtonAdminRegistro;
    private JFrame FrameFicha;
    private JFrame FrameHomepage;

    public TelaFicha(JFrame frameHomepage, Funcionarios funcionario){
        FrameFicha = new JFrame("RH Manager - Alpha");
        this.FrameHomepage = frameHomepage;

        FrameFicha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameFicha.setSize(360, 600);
        FrameFicha.setLocationRelativeTo(null);
        FrameFicha.add(PanelFicha);
        FrameFicha.pack();
        FrameFicha.setVisible(true);

        if (funcionario.getGenero().equals("Feminino")){
            ProfileFicha.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile F.png")));
        } else if (funcionario.getGenero().equals("Masculino")) {
            ProfileFicha.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile.png")));
        } else if (funcionario.getGenero().equals("Outros")) {
            ProfileFicha.setIcon(new ImageIcon(getClass().getResource("/IMG/do-utilizador (1).png")));
        }

        NameTitleFicha.setText("Nome: " + funcionario.getNome());
        CpfTitleFicha.setText("CPF: " + funcionario.getCpf());
        DobTitleFicha.setText("Data de Nascimento: " + funcionario.getDataNascimento());
        EmailTitleFicha.setText("Email: " + funcionario.getEmail());
        GeneroTitleFicha.setText("Genero: " + funcionario.getGenero());
        PosTitleFicha.setText("Cargo: " + funcionario.getCargo());
        TurnTitleFicha.setText("Horario: " + funcionario.getHorario());



        ButtonSaidaFicha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameFicha.setVisible(false);
                FrameHomepage.setVisible(true);
            }
        });
    }
}