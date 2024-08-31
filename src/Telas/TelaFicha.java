package Telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import Classes.Funcionario;

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
    private final JFrame FrameFicha;
    private final JFrame FrameHomepage;

    public TelaFicha(JFrame frameHomepage, Funcionario funcionario){
        FrameFicha = new JFrame("RH Manager - Alpha");
        this.FrameHomepage = frameHomepage;

        FrameFicha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameFicha.setSize(360, 600);
        FrameFicha.setLocationRelativeTo(null);
        FrameFicha.add(PanelFicha);
        FrameFicha.pack();
        FrameFicha.setVisible(true);

        configurarTela(funcionario);

        ButtonSaidaFicha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameFicha.setVisible(false);
                FrameHomepage.setVisible(true);
            }
        });
    }

    private void configurarTela(Funcionario funcionario){
        switch (funcionario.getGenero()) {
            case "Feminino" -> ProfileFicha.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/IMG/Profile F.png"))));
            case "Masculino" -> ProfileFicha.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/IMG/Profile.png"))));
            case "Outros" -> ProfileFicha.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/IMG/do-utilizador (1).png"))));
        }

        NameTitleFicha.setText("Nome: " + funcionario.getNome());
        CpfTitleFicha.setText("CPF: " + funcionario.getCpf());
        DobTitleFicha.setText("Data de Nascimento: " + funcionario.getDataNascimento());
        EmailTitleFicha.setText("Email: " + funcionario.getEmail());
        GeneroTitleFicha.setText("Genero: " + funcionario.getGenero());
        PosTitleFicha.setText("Cargo: " + funcionario.getCargo());
        TurnTitleFicha.setText("Horario: " + funcionario.getHorario());
    }
}