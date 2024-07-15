package TelaRegister;

import javax.swing.*;

import Classes.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class TelaCadastro {

    private JFrame FrameCadastro;
    private JPanel PanelCadastro;
    private JTextField UsernameFieldCadastro;
    private JPasswordField PasswordFieldCadastro;
    private JTextField NameFieldCadastro;
    private JTextField CpfFieldCadastro;
    private JTextField EmailFieldCadastro;
    private JButton CancelButtonCadastro;
    private JButton RegisterButtonCadastro;
    private JLabel IconCadastro;
    private JLabel CadastroTitle;
    private JLabel UsernameTitleCadastro;
    private JLabel PasswordTitleCadastro;
    private JLabel NameTitleCadastro;
    private JLabel CpfTitleCadastro;
    private JLabel EmailTitleCadastro;
    private JLabel DobTitleCadastro;
    private JLabel SexTitleCadastro;
    private JLabel PosTitleCadastro;
    private JLabel TurnTitleCadastro;
    private JComboBox DayComboBoxCadastro;
    private JComboBox MonthComboBoxCadastro;
    private JComboBox YearComboBoxCadastro;
    private JComboBox GeneroComboBoxCadastro;
    private JComboBox PosComboBoxCadastro;
    private JComboBox TurnComboBoxCadastro;

    private ArrayList<User> usuarios;
    private JFrame FrameMenu;
    private String genero;


    public TelaCadastro(ArrayList<User> usuarios, JFrame frameMenu) {
        this.usuarios = usuarios;
        this.FrameMenu = frameMenu;
        FrameCadastro = new JFrame("RH Manager - Alpha (Cadastro)");
        genero = "";

        FrameCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameCadastro.add(PanelCadastro);
        FrameCadastro.pack();
        FrameCadastro.setVisible(true);

        //ComboBox Data de Nascimento
        if (DayComboBoxCadastro.getItemCount() == 0) {
            for (int i = 1; i <= 31; i++) {
                DayComboBoxCadastro.addItem(i);
            }
        }

        if (MonthComboBoxCadastro.getItemCount() == 0) {
            for (int i = 1; i <= 12; i++) {
                MonthComboBoxCadastro.addItem(i);
            }
        }

        if (YearComboBoxCadastro.getItemCount() == 0) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = currentYear; i >= 1900; i--) {
                YearComboBoxCadastro.addItem(i);
            }
        }

        //BoxCargo

        PosComboBoxCadastro.addItem("");
        PosComboBoxCadastro.addItem("Gerente de Projetos"); PosComboBoxCadastro.addItem("Engenheiro de Software");
        PosComboBoxCadastro.addItem("Desenvolvedor Senior"); PosComboBoxCadastro.addItem("Desenvolvedor Pleno");
        PosComboBoxCadastro.addItem("Desenvolvedor Junior"); PosComboBoxCadastro.addItem("Estagiario de Ti");
        PosComboBoxCadastro.addItem("Gerente Administrativo"); PosComboBoxCadastro.addItem("Auxiliar Administrativo");
        PosComboBoxCadastro.addItem("Advogado Chefe"); PosComboBoxCadastro.addItem("Auxiliar Juridico");
        PosComboBoxCadastro.addItem("Estagiario Juridico");

        RegisterButtonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User novoUsuario = cadastrarUsuario();
                if (novoUsuario != null) {
                    usuarios.add(novoUsuario);
                    JOptionPane.showMessageDialog(FrameCadastro, "Cadastro Realizado com Sucesso!");
                    FrameCadastro.setVisible(false);
                    FrameMenu.setVisible(true);
                }
            }
        });

        //ComboBox Genero
        GeneroComboBoxCadastro.addItem("");
        GeneroComboBoxCadastro.addItem("Feminino");
        GeneroComboBoxCadastro.addItem("Masculino");
        GeneroComboBoxCadastro.addItem("Outros");

        //ComboBox Horarios

        TurnComboBoxCadastro.addItem("");
        TurnComboBoxCadastro.addItem("7:00 - 14:00");
        TurnComboBoxCadastro.addItem("14:00 - 21:00");
        TurnComboBoxCadastro.addItem("10:00 - 14:00");
        TurnComboBoxCadastro.addItem("14:00 - 18:00");

        CancelButtonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameCadastro.setVisible(false);
                FrameMenu.setVisible(true);
            }
        });

    }


    private User cadastrarUsuario() {
        String username = UsernameFieldCadastro.getText();
        String password = new String(PasswordFieldCadastro.getPassword());
        String nome = NameFieldCadastro.getText();
        String cpf = CpfFieldCadastro.getText();
        String email = EmailFieldCadastro.getText();
        int dia = (int) DayComboBoxCadastro.getSelectedItem();
        int mes = (int) MonthComboBoxCadastro.getSelectedItem();
        int ano = (int) YearComboBoxCadastro.getSelectedItem();
        String dataNascimento = String.format("%02d/%02d/%04d", dia, mes, ano);
        String genero = (String) GeneroComboBoxCadastro.getSelectedItem();
        String cargo = (String) PosComboBoxCadastro.getSelectedItem();
        String horario = (String) TurnComboBoxCadastro.getSelectedItem();



        if (username != null && password != null) {
            User usuario = new User();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setDataNascimento(dataNascimento);
            usuario.setGenero(genero);
            usuario.setCargo(cargo);
            usuario.setHorario(horario);
            return usuario;
        }
        return null;
    }
}