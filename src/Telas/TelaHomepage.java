package Telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Classes.Funcionario;
import Classes.User;
import DbConnect.DbConnection;

public class TelaHomepage extends Component {
    //Criação dos elementos graficos na tela
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

        //Configurações padrão da tela
        FrameHomepage = new JFrame("RH Manager - Alpha");
        FrameHomepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameHomepage.setSize(1024, 768);
        FrameHomepage.setLocationRelativeTo(null);
        FrameHomepage.add(PanelHomepage);
        FrameHomepage.pack();
        FrameHomepage.setVisible(true);

        configurarTela(usuarioLogado);

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
                int FuncSelected = TableFunHomepage.getSelectedRow();
                if(FuncSelected != -1){
                    Integer id = (Integer) TableFunHomepage.getValueAt(FuncSelected, 3); // Coluna ID
                    Funcionario funcionario = DbConnection.infoFuncionario(id);
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
                int FuncSelected = TableFunHomepage.getSelectedRow();
                if(FuncSelected != -1){
                    Integer id = (Integer) TableFunHomepage.getValueAt(FuncSelected, 3); // Coluna ID
                    Funcionario funcionario = DbConnection.infoFuncionario(id);
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
                int FuncSelected = TableFunHomepage.getSelectedRow();
                if(FuncSelected != -1){
                    Integer id = (Integer) TableFunHomepage.getValueAt(FuncSelected, 3); // Coluna ID
                    Funcionario funcionario = DbConnection.infoFuncionario(id);
                    if (funcionario != null) {
                        TelaRelatorio telaRelatorio = new TelaRelatorio(FrameHomepage, funcionario, usuarioLogado);
                        FrameHomepage.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(FrameHomepage, "Erro ao buscar detalhes do funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(FrameHomepage, "Nenhum funcionário selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        ButtonPesquisaHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String funcPesquisado = FieldPesquisaHomepage.getText();
                DbConnection PesquisarFunc = new DbConnection();
                List<Funcionario> funcionarioPesquisado;

                if (funcPesquisado.trim().isEmpty()) {
                    funcionarioPesquisado = PesquisarFunc.buscarTodosFuncionarios();
                } else {
                    funcionarioPesquisado = PesquisarFunc.buscarFuncionario(funcPesquisado);
                }

                DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome", "Cargo", "Horário", "ID"}, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                for (Funcionario func : funcionarioPesquisado) {
                    model.addRow(new Object[]{func.getNome(), func.getCargo(), func.getHorario(), func.getFuncionarioId()});
                }
                TableFunHomepage.setModel(model);
            }
        });
    }

    private void configurarTabela() {
        DbConnection BuscarFunc = new DbConnection();
        List<Funcionario> funcionarios = BuscarFunc.buscarTodosFuncionarios();

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome", "Cargo", "Horário", "ID"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas as células não são editáveis
            }
        };
        TableFunHomepage.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (Funcionario func : funcionarios) {
            model.addRow(new Object[]{func.getNome(), func.getCargo(), func.getHorario(), func.getFuncionarioId()});
        }
        TableFunHomepage.setModel(model);
    }

    private void configurarTela(User usuarioLogado){
        //Condiciona os elementos a serem exibidos apenas para os administradores
        if(usuarioLogado.getCargo().equals("Admin")){
            ButtonDeslHomepage.setVisible(true);
            ButtonNewUserHomepage.setVisible(true);
            ButtonNewFunHomepage.setVisible(true);
        }else{
            ButtonDeslHomepage.setVisible(false);
            ButtonNewUserHomepage.setVisible(false);
            ButtonNewFunHomepage.setVisible(false);
        }
    }
}