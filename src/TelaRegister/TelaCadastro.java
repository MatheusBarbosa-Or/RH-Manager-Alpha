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
    private String sexo;


    public TelaCadastro(ArrayList<User> usuarios, JFrame frameMenu) {
        this.usuarios = usuarios;
        this.FrameMenu = frameMenu;
        FrameCadastro = new JFrame("RH Manager - Alpha (Cadastro)");
        sexo = "";

        FrameCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameCadastro.add(PanelCadastro);
        FrameCadastro.pack();
        FrameCadastro.setVisible(true);

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



        if (username != null && password != null) {
            User usuario = new User();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setDataNascimento(dataNascimento);
            usuario.setSexo(sexo);
            usuario.setCargo(username);
            return usuario;
        }
        return null;
    }
}