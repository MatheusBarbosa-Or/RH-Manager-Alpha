package Telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Classes.Funcionarios;
import Classes.User;
import DbConnect.DbConnection;

public class TelaHomepage extends Component {
    private JTextField FieldPesquisaHomepage;
    private JTable TableFunHomepage;
    private JButton ButtonPesquisaHomepage;
    private JButton ButtonFichaHomepage;
    private JButton ButtonRelatorioHomepage;
    private JButton ButtonDeslHomepage;
    private JButton ButtonSaidaHomepage;
    private JPanel PanelHomepage;
    private JButton ButtonNewUserHomepage;
    private JButton ButtonNewFunHomepage;
    private JScrollPane ScrollPaneTableHomepage;
    private JLabel NomeTeste;
    private JLabel CargoTeste;
    private JLabel HorarioTeste;
    private JFrame FrameHomepage;

    private JFrame FrameLogin;
    private int novoUsuario_Funcionario;

    public TelaHomepage(JFrame frameLogin, User usuarioLogado){
        this.FrameLogin = frameLogin;
        FrameHomepage = new JFrame("RH Manager - Alpha");

        FrameHomepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameHomepage.add(PanelHomepage);
        FrameHomepage.pack();
        FrameHomepage.setVisible(true);

        if(usuarioLogado.getCargo().equals("Admin")){
            ButtonDeslHomepage.setVisible(true);
            ButtonNewUserHomepage.setVisible(true);
            ButtonNewFunHomepage.setVisible(true);
        }else{
            ButtonDeslHomepage.setVisible(false);
            ButtonNewUserHomepage.setVisible(false);
            ButtonNewFunHomepage.setVisible(false);
        }

        configurarTabela();

        ButtonNewUserHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoUsuario_Funcionario = 1;
                TelaCadastro telaCadastro = new TelaCadastro(FrameHomepage, novoUsuario_Funcionario,  () -> configurarTabela());
                FrameHomepage.setVisible(false);
            }
        });

        ButtonNewFunHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoUsuario_Funcionario = 2;
                TelaCadastro telaCadastro = new TelaCadastro(FrameHomepage, novoUsuario_Funcionario,  () -> configurarTabela());
                FrameHomepage.setVisible(false);
            }
        });

        ButtonFichaHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer FuncSelect = TableFunHomepage.getSelectedRow();
                if(FuncSelect != -1){
                    Integer id = (Integer) TableFunHomepage.getValueAt(FuncSelect, 3); // Coluna ID
                    Funcionarios funcionario = DbConnection.infoFuncionario(id);
                    if (funcionario != null) {
                        TelaFicha telaFicha = new TelaFicha(FrameHomepage, funcionario);
                        FrameHomepage.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(FrameHomepage, "Erro ao buscar detalhes do funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(FrameHomepage, "Nenhum funcionário selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        ButtonSaidaHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameHomepage.setVisible(false);
                FrameLogin.setVisible(true);
            }
        });

        ButtonDeslHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer FuncSelect = TableFunHomepage.getSelectedRow();
                if(FuncSelect != -1){
                    Integer id = (Integer) TableFunHomepage.getValueAt(FuncSelect, 3); // Coluna ID
                    Funcionarios funcionario = DbConnection.infoFuncionario(id);
                    if (funcionario != null) {
                        TelaDesligamento telaDesligamento = new TelaDesligamento(FrameHomepage, funcionario, () -> configurarTabela());
                        FrameHomepage.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(FrameHomepage, "Erro ao buscar detalhes do funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(FrameHomepage, "Nenhum funcionário selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        ButtonRelatorioHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer FuncSelect = TableFunHomepage.getSelectedRow();
                if(FuncSelect != -1){
                    Integer id = (Integer) TableFunHomepage.getValueAt(FuncSelect, 3); // Coluna ID
                    Funcionarios funcionario = DbConnection.infoFuncionario(id);
                    if (funcionario != null) {
                        TelaRelatorio telaRelatorio = new TelaRelatorio(FrameHomepage, funcionario);
                        FrameHomepage.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(FrameHomepage, "Erro ao buscar detalhes do funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(FrameHomepage, "Nenhum funcionário selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void configurarTabela() {
        DbConnection BuscarFunc = new DbConnection();
        List<Funcionarios> funcionarios = BuscarFunc.buscarTodosFuncionarios();

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome", "Cargo", "Horário", "ID"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas as células não são editáveis
            }
        };
        TableFunHomepage.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (Funcionarios func : funcionarios) {
            model.addRow(new Object[]{func.getNome(), func.getCargo(), func.getHorario(), func.getFuncionarioId()});
        }
        TableFunHomepage.setModel(model);
    }

}
