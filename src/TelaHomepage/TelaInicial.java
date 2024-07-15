package TelaHomepage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Classes.User;


public class TelaInicial {
    private JPanel PanelHomepage;
    private JLabel ProfileHomepage;
    private JLabel TituloRegistro;
    private JLabel NameTitleHomepage;
    private JLabel CpfTitleHomepage;
    private JLabel DobTitleHomepage;
    private JLabel EmailTitleHomepage;
    private JLabel GeneroTitleHomepage;
    private JLabel PosTitleHomepage;
    private JButton ButtonSaidaHomepage;
    private JLabel TurnTitleHomepage;
    private JFrame FrameInicial;
    private ArrayList<User> usuarios;
    private JFrame FrameMenu;

    public TelaInicial(ArrayList<User> usuarios, JFrame frameMenu, User usuarioLogado){
        FrameInicial = new JFrame("RH Manager - Alpha");
        this.usuarios = usuarios;
        this.FrameMenu = frameMenu;

        NameTitleHomepage.setText("Nome: " + usuarioLogado.getNome());
        CpfTitleHomepage.setText("CPF: " + usuarioLogado.getCpf());
        DobTitleHomepage.setText("Data de Nascimento: " + usuarioLogado.getDataNascimento());
        EmailTitleHomepage.setText("Email: " + usuarioLogado.getEmail());
        GeneroTitleHomepage.setText("Genero: " + usuarioLogado.getGenero());
        PosTitleHomepage.setText("Cargo: " + usuarioLogado.getCargo());
        TurnTitleHomepage.setText("Horario: " + usuarioLogado.getHorario());


        ButtonSaidaHomepage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameInicial.setVisible(false);
                FrameMenu.setVisible(true);
            }
        });

        FrameInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameInicial.add(PanelHomepage);
        FrameInicial.pack();
        FrameInicial.setVisible(true);
    }
}
