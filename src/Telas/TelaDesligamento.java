package Telas;

import Classes.Funcionarios;
import DbConnect.DbConnection;

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

    public TelaDesligamento(JFrame frameHomepage, Funcionarios funcionario, Runnable onSave){
        this.FrameHomepage = frameHomepage;
        this.funcionario = funcionario;

        FrameDesligamento = new JFrame("RH Manager - Alpha");
        FrameDesligamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameDesligamento.setSize(540, 340);
        FrameDesligamento.setLocationRelativeTo(null);
        FrameDesligamento.add(PanelDesl);
        FrameDesligamento.pack();
        FrameDesligamento.setVisible(true);

        configurarTela(funcionario);

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

                DbConnection.desligarFuncionario(funcionario);
                if (funcionario != null) {
                    JOptionPane.showMessageDialog(FrameDesligamento, " Funcionario desligado com sucesso!\n");
                    FrameDesligamento.setVisible(false);
                    FrameHomepage.setVisible(true);
                    onSave.run();
                } else {
                    JOptionPane.showMessageDialog(FrameDesligamento, "Nome de usuario ou senha inválida!");
                    FrameDesligamento.setVisible(false);
                    FrameHomepage.setVisible(true);
                }
            }
        });
    }

    private void configurarTela(Funcionarios funcionario){
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
    }
}