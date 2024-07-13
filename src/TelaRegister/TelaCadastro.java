package TelaRegister;

import javax.swing.*;
import Classes.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCadastro {

    private JFrame FrameCadastro;
    private JPanel PanelCadastro;
    private JTextField UsernameFieldCadastro;
    private JPasswordField PasswordFieldCadastro;
    private JTextField NameFieldCadastro;
    private JTextField CpfFieldCadastro;
    private JTextField EmailFieldCadastro;
    private JTextField DobFieldCadastro;
    private JRadioButton FemRadioButton;
    private JRadioButton MascRadioButton;
    private JRadioButton OutrosRadioButton;
    private JTextField PosFieldCadastro;
    private JButton CancelButtonCadastro;
    private JButton RegisterButtonCadastro;
    private JLabel IconCadasto;
    private JLabel CadastroTitle;
    private JLabel UsernameTitleCadastro;
    private JLabel PasswordTitleCadastro;
    private JLabel NameTitleCadastro;
    private JLabel CpfTitleCadastro;
    private JLabel EmailTitleCadastro;
    private JLabel DobTitleCadastro;
    private JLabel SexTitleCadastro;
    private JLabel PosTitleCadastro;
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

        ActionListener sexoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == FemRadioButton) {
                    sexo = "Feminino";
                } else if (e.getSource() == MascRadioButton) {
                    sexo = "Masculino";
                } else if (e.getSource() == OutrosRadioButton) {
                    sexo = "Outros";
                }
            }
        };

        FemRadioButton.addActionListener(sexoListener);
        MascRadioButton.addActionListener(sexoListener);
        OutrosRadioButton.addActionListener(sexoListener);

    }

    private User cadastrarUsuario() {
        String username = UsernameFieldCadastro.getText();
        String password = new String(PasswordFieldCadastro.getPassword());
        String nome = NameFieldCadastro.getText();
        String cpf = CpfFieldCadastro.getText();
        String email = EmailFieldCadastro.getText();
        String dataNascimento = DobFieldCadastro.getText();
        String cargo = PosFieldCadastro.getText();

        if (username != null && password != null) {
            User usuario = new User();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setDataNascimento(dataNascimento);
            usuario.setSexo(sexo);
            usuario.setCargo(cargo);
            return usuario;
        }
        return null;
    }
}