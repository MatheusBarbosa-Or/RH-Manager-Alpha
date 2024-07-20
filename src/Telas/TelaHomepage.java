package Telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Classes.Funcionarios;
import Classes.User;
import Database.DbConnection;

public class TelaHomepage {
    private JTextField FieldPesquisaHomepage;
    private JTable TableFunHomepage;
    private JButton ButtonPesquisaHomepage;
    private JButton ButtonFichaHomepage;
    private JButton ButtonRelatorioHomepage;
    private JButton ButtonDesHomepage;
    private JButton ButtonSaidaHomepage;
    private JPanel PanelHomepage;
    private JButton ButtonNewUserHomepage;
    private JButton ButtonNewFunHomepage;
    private JLabel NomeTeste;
    private JLabel CargoTeste;
    private JLabel HorarioTeste;
    private JFrame FrameHomepage;

    private ArrayList<User> usuarios;
    private ArrayList<Funcionarios> funcionarios;
    private JFrame FrameLogin;
    private int novoUsuario_Funcionario;

    public TelaHomepage(ArrayList<User> usuarios, ArrayList<Funcionarios> funcionarios, JFrame frameLogin, User usuarioLogado, Funcionarios funcionarioTeste){
        this.usuarios = usuarios;
        this.funcionarios = funcionarios;
        this.FrameLogin = frameLogin;
        FrameHomepage = new JFrame("RH Manager - Alpha");


        FrameHomepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameHomepage.add(PanelHomepage);
        FrameHomepage.pack();
        FrameHomepage.setVisible(true);

        if(usuarioLogado.getCargo().equals("Admin")){
            ButtonDesHomepage.setVisible(true);
            ButtonNewUserHomepage.setVisible(true);
            ButtonNewFunHomepage.setVisible(true);
        }else{
            ButtonDesHomepage.setVisible(false);
            ButtonNewUserHomepage.setVisible(false);
            ButtonNewFunHomepage.setVisible(false);
        }

        configurarTabela();

        ButtonNewUserHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoUsuario_Funcionario = 1;
                TelaCadastro telaCadastro = new TelaCadastro(usuarios, funcionarios,FrameHomepage, novoUsuario_Funcionario,  () -> configurarTabela());
                FrameHomepage.setVisible(false);
            }
        });

        ButtonNewFunHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoUsuario_Funcionario = 2;
                TelaCadastro telaCadastro = new TelaCadastro(usuarios, funcionarios,FrameHomepage, novoUsuario_Funcionario,  () -> configurarTabela());
                FrameHomepage.setVisible(false);
            }
        });

        ButtonFichaHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaRegistro telaRegistro = new TelaRegistro(usuarios, funcionarios,FrameHomepage, usuarioLogado, funcionarioTeste);
                FrameHomepage.setVisible(false);
            }
        });

        ButtonSaidaHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameHomepage.setVisible(false);
                FrameLogin.setVisible(true);
            }
        });

    }

    private void configurarTabela() {
        DbConnection BuscarFunc = new DbConnection();
        List<Funcionarios> funcionarios = BuscarFunc.buscarTodosFuncionarios();

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome", "Cargo", "Horário"}, 0);
        for (Funcionarios func : funcionarios) {
            model.addRow(new Object[]{func.getNome(), func.getCargo(), func.getHorario()});
        }
        TableFunHomepage.setModel(model);
    }

}
