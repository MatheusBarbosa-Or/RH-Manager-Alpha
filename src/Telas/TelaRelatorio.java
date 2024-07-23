package Telas;

import javax.swing.*;

import Classes.Funcionarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRelatorio {
    private JPanel PanelRelatorio;
    private JTextArea InfoTextAreaRelatorio;
    private JTextArea ObsTextAreaRelatorio;
    private JButton Star1Relatorio;
    private JButton ButtonSaidaRelatorio;
    private JLabel TitleRelatorio;
    private JLabel IconRelatorio;
    private JLabel ObsTitleRelatorio;
    private JLabel InfoTitleRelatorio;
    private JLabel AvalTitleRelatorio;
    private JButton Star2Relatorio;
    private JButton Star3Relatorio;
    private JButton Star4Relatorio;
    private JButton Star5Relatorio;
    private JButton enviarButton;
    private JLabel InfoTextRelatorio;
    private JFrame FrameRelatorio;
    private JFrame FrameHomepage;

    private String av;

    public TelaRelatorio(JFrame frameHomepage, Funcionarios funcionario){
        FrameRelatorio = new JFrame("RH Manager - Alpha");
        this.FrameHomepage =  frameHomepage;

        FrameRelatorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameRelatorio.add(PanelRelatorio);
        FrameRelatorio.pack();
        FrameRelatorio.setVisible(true);

        if (funcionario.getGenero().equals("Feminino")){
            IconRelatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile_F_G.png")));
        } else if (funcionario.getGenero().equals("Masculino")) {
            IconRelatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile_M_G.png")));
        } else if (funcionario.getGenero().equals("Outros")) {
            IconRelatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/do-utilizador (1).png")));
        }

        TitleRelatorio.setText("Relatorio: " + funcionario.getNome());

        InfoTextRelatorio.setText("<html>" + "<br>" + "Nome: " + funcionario.getNome() + "<br>"
                + "Id: " + funcionario.getFuncionarioId() + "<br>"
                + "Email: " + funcionario.getEmail() + "<br>"
                + "Data de Nascimento: " + funcionario.getDataNascimento() + "<br>"
                + "Genero: " + funcionario.getGenero() + "<br>"
                + "Cargo: " + funcionario.getCargo() + "<br>"
                + "Horario: " + funcionario.getHorario() + "</html>");
        /////////////////////////////////////////////////////////////////////////////////
        ButtonSaidaRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameRelatorio.setVisible(false);
                FrameHomepage.setVisible(true);
            }
        });
        Star1Relatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Star1Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star2Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                Star3Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                Star4Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                Star5Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                av = "1";
            }
        });

        Star2Relatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Star1Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star2Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star3Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                Star4Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                Star5Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                av = "2";
            }
        });

        Star3Relatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Star1Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star2Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star3Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star4Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                Star5Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                av = "3";
            }
        });
        Star4Relatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Star1Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star2Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star3Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star4Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star5Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/EstrelaVazia.png")));
                av = "4";
            }
        });

        Star5Relatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Star1Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star2Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star3Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star4Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                Star5Relatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Estrela.png")));
                av = "5";
            }
        });

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String relatorioFunc = gerarRelatorio(funcionario,av);
                JOptionPane.showMessageDialog(FrameRelatorio, relatorioFunc, "Sucesso", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

    public String gerarRelatorio(Funcionarios funcionario, String av){
        String obs = ObsTextAreaRelatorio.getText();
        String relatorio = "Relatorio: \n" + "\nNome: " + funcionario.getNome() + "\nId: " + funcionario.getFuncionarioId() +
                "\nCargo: " + funcionario.getCargo() + "\nHorario: " + funcionario.getHorario() + "\nAvaliação: " + av +
                "\nObservação: " + obs;
        return(relatorio);
    }
}