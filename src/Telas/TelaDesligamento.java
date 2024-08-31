package Telas;

import Classes.Funcionario;
import DbConnect.DbConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TelaDesligamento {
    //Criação dos elementos graficos na tela
    private JButton ButtonCancelarDesl;
    private JButton ButtonConfirmDesl;
    private JLabel IconDesl;
    private JPanel PanelDesl;
    private JLabel MessageDsl;
    private JLabel ProfileFuncDesl;

    private final JFrame FrameHomepage;
    private final JFrame FrameDesligamento;

    public TelaDesligamento(JFrame frameHomepage, Funcionario funcionario, Runnable onSave){
        this.FrameHomepage = frameHomepage;

        //Configurações padrão da tela
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
                JOptionPane.showMessageDialog(FrameDesligamento, " Funcionario desligado com sucesso!\n");
                FrameDesligamento.setVisible(false);
                FrameHomepage.setVisible(true);
                onSave.run();
            }
        });
    }

    private void configurarTela(Funcionario funcionario){
        switch (funcionario.getGenero()) {
            case "Feminino" -> ProfileFuncDesl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/IMG/Profile F.png"))));
            case "Masculino" -> ProfileFuncDesl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/IMG/Profile.png"))));
            case "Outros" -> ProfileFuncDesl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/IMG/Outros2.png"))));
        }

        MessageDsl.setText("<html>Deseja realmente desligar o funcionario:<br>" + "<br>"
                + "Nome: " + funcionario.getNome() + "<br>"
                + "Id: " + funcionario.getFuncionarioId() + "<br>"
                + "Cargo: " + funcionario.getCargo() + "<br>"
                + "Horario: " + funcionario.getHorario() + "</html>");
    }
}