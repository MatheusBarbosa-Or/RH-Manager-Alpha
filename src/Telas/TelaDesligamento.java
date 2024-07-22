package Telas;

import Classes.Funcionarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDesligamento {
    private JButton ButtonCancelarDesl;
    private JButton ButtonConfirmDesl;
    private JLabel IconDesl;
    private JPanel PanelDesl;
    private JLabel MessageDsl;
    private JLabel ProfileFuncDesl;

    private JFrame FrameHomepage;
    private JFrame FrameDesligamento;
    private Funcionarios funcionario;

    public TelaDesligamento(JFrame frameHomepage, Funcionarios funcionario){
        this.FrameHomepage = frameHomepage;
        this.funcionario = funcionario;

        FrameDesligamento = new JFrame("RH Manager - Alpha");
        FrameDesligamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameDesligamento.add(PanelDesl);
        FrameDesligamento.pack();
        FrameDesligamento.setVisible(true);

        if (funcionario.getGenero().equals("Feminino")){
            ProfileFuncDesl.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile F.png")));
        } else if (funcionario.getGenero().equals("Masculino")) {
            ProfileFuncDesl.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile.png")));
        } else if (funcionario.getGenero().equals("Outros")) {
            ProfileFuncDesl.setIcon(new ImageIcon(getClass().getResource("/IMG/Outros2.png")));
        }

        MessageDsl.setText("<html>Deseja realmente desligar o funcionario:<br>" + "<br>"
                + "Nome: " + funcionario.getNome() + "<br>"
                + "Id: " + funcionario.getFuncionarioId() + "<br>"
                + "Cargo: " + funcionario.getCargo() + "<br>"
                + "Horario: " + funcionario.getHorario() + "</html>");
        ////////////////////////////////////////////////////////////////////////////////////////////

        ButtonCancelarDesl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameDesligamento.setVisible(false);
                FrameHomepage.setVisible(true);
            }
        });

        ButtonConfirmDesl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
