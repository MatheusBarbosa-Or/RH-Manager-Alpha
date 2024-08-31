package Telas;

import javax.swing.*;

import Classes.Funcionario;
import Classes.User;
import DbConnect.DbConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

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

    public TelaRelatorio(JFrame frameHomepage, Funcionario funcionario, User usuarioLogado){
        this.FrameHomepage =  frameHomepage;

        //Configurações padrão da tela
        FrameRelatorio = new JFrame("RH Manager - Alpha");
        FrameRelatorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameRelatorio.setSize(800, 640);
        FrameRelatorio.setLocationRelativeTo(null);
        FrameRelatorio.add(PanelRelatorio);
        FrameRelatorio.pack();
        FrameRelatorio.setVisible(true);

        configurarTela(funcionario);

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
                try {
                    if (av != null){
                        String caminho = gerarRelatorio(funcionario, av, usuarioLogado);
                        String nomeArquivo = "Relatorio_" + usuarioLogado.getUsuarioid() + ":" + funcionario.getFuncionarioId() + ".txt";
                        JOptionPane.showMessageDialog(FrameRelatorio, "Relatório criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        DbConnection.uploadRelatorio(caminho, nomeArquivo, funcionario.getFuncionarioId());
                        FrameRelatorio.setVisible(false);
                        frameHomepage.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(FrameRelatorio, "Erro ao criar relatório, por favor avalie o funcionario!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(FrameRelatorio, "Erro ao criar relatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    private void configurarTela(Funcionario funcionario){
        switch (funcionario.getGenero()) {
            case "Feminino" -> IconRelatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile_F_G.png")));
            case "Masculino" -> IconRelatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile_M_G.png")));
            case "Outros" -> IconRelatorio.setIcon(new ImageIcon(getClass().getResource("/IMG/do-utilizador (1).png")));
        }

        TitleRelatorio.setText("Relatorio: " + funcionario.getNome());

        InfoTextRelatorio.setText("<html>" + "<br>" + "Nome: " + funcionario.getNome() + "<br>"
                + "Id: " + funcionario.getFuncionarioId() + "<br>"
                + "Email: " + funcionario.getEmail() + "<br>"
                + "Data de Nascimento: " + funcionario.getDataNascimento() + "<br>"
                + "Genero: " + funcionario.getGenero() + "<br>"
                + "Cargo: " + funcionario.getCargo() + "<br>"
                + "Horario: " + funcionario.getHorario() + "</html>");
    }

    public String gerarRelatorio(Funcionario funcionario, String av, User usuarioLogado) throws IOException{
        String obs = ObsTextAreaRelatorio.getText();

        String relatorio = "Relatorio: \n" + "\nNome: " + funcionario.getNome() + "\nId: " + funcionario.getFuncionarioId() +
                "\nCargo: " + funcionario.getCargo() + "\nHorario: " + funcionario.getHorario() + "\nAvaliação: " + av +
                "\nObservação: \n" + obs;

        String nomeArquivo = "src/Relatorios/Relatorio_" + usuarioLogado.getUsuarioid() + ":" + funcionario.getFuncionarioId() + ".txt";
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            escritor.write(relatorio);
        }
        return nomeArquivo;
    }
}
