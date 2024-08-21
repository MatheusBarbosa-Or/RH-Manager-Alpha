package Telas;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import Classes.User;
import Classes.Funcionarios;
import DbConnect.DbConnection;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelaCadastro {

    private JFrame FrameCadastro;
    private JPanel PanelCadastro;
    private JTextField UsernameFieldCadastro;
    private JPasswordField PasswordFieldCadastro;
    private JTextField NameFieldCadastro;
    private JFormattedTextField CpfFieldCadastro;
    private JTextField EmailFieldCadastro;
    private JButton CancelButtonCadastro;
    private JButton RegisterButtonCadastro;
    private JLabel IconCadastro;
    private JLabel TitleCadastro;
    private JLabel UsernameTitleCadastro;
    private JLabel PasswordTitleCadastro;
    private JLabel NameTitleCadastro;
    private JLabel CpfTitleCadastro;
    private JLabel EmailTitleCadastro;
    private JLabel DobTitleCadastro;
    private JLabel GeneroTitleCadastro;
    private JLabel PosTitleCadastro;
    private JLabel TurnTitleCadastro;
    private JComboBox DayComboBoxCadastro;
    private JComboBox MonthComboBoxCadastro;
    private JComboBox YearComboBoxCadastro;
    private JComboBox GeneroComboBoxCadastro;
    private JComboBox PosComboBoxCadastro;
    private JComboBox TurnComboBoxCadastro;

    private JFrame FrameHomepage;
    private String genero;
    private int novoUsuario_Funcionario;

    public TelaCadastro(JFrame frameHomepage, int novoUsuario_Funcionario, Runnable onSave) {
        this.FrameHomepage = frameHomepage;
        this.novoUsuario_Funcionario = novoUsuario_Funcionario;
        FrameCadastro = new JFrame("RH Manager - Alpha (Cadastro)");
        genero = "";

        FrameCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameCadastro.setSize(700, 600);
        FrameCadastro.setLocationRelativeTo(null);
        FrameCadastro.add(PanelCadastro);
        FrameCadastro.pack();
        FrameCadastro.setVisible(true);

        configurarTela();

        RegisterButtonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(novoUsuario_Funcionario == 1){
                    User novoUsuario = cadastrarUsuario();
                    if (novoUsuario != null) {
                        DbConnection.inserirUsuario(novoUsuario);
                        JOptionPane.showMessageDialog(FrameCadastro, "Cadastro Realizado com Sucesso!");
                        FrameCadastro.setVisible(false);
                        FrameHomepage.setVisible(true);
                    }
                }else if (novoUsuario_Funcionario == 2) {
                    Funcionarios novoFuncionario = cadastrarFuncionario();
                    if (novoFuncionario != null){
                        DbConnection.inserirFuncionario(novoFuncionario);
                        JOptionPane.showMessageDialog(FrameCadastro, "Cadastro Realizado com Sucesso!");
                        FrameCadastro.setVisible(false);
                        FrameHomepage.setVisible(true);
                        onSave.run();
                    }
                }
            }
        });

        CancelButtonCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameCadastro.setVisible(false);
                FrameHomepage.setVisible(true);
            }
        });

    }

    private void configurarTela(){
        if (novoUsuario_Funcionario == 1){
            EmailTitleCadastro.setVisible(false); GeneroTitleCadastro.setVisible(false);
            EmailFieldCadastro.setVisible(false); GeneroComboBoxCadastro.setVisible(false);
            DobTitleCadastro.setVisible(false); TurnTitleCadastro.setVisible(false);
            DayComboBoxCadastro.setVisible(false); TurnComboBoxCadastro.setVisible(false);
            MonthComboBoxCadastro.setVisible(false);
            YearComboBoxCadastro.setVisible(false);
        } else if (novoUsuario_Funcionario == 2) {
            TitleCadastro.setText("Cadastro de Funcionario");
            UsernameTitleCadastro.setVisible(false);
            UsernameFieldCadastro.setVisible(false);
            PasswordTitleCadastro.setVisible(false);
            PasswordFieldCadastro.setVisible(false);
        }

        try {
            MaskFormatter cpfFormatter = new MaskFormatter("###.###.###-##");
            cpfFormatter.install(CpfFieldCadastro);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao formatar", "ERRO", JOptionPane.ERROR);
        }

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

        PosComboBoxCadastro.addItem("");
        PosComboBoxCadastro.addItem("Gerente de Projetos"); PosComboBoxCadastro.addItem("Engenheiro de Software");
        PosComboBoxCadastro.addItem("Desenvolvedor Senior"); PosComboBoxCadastro.addItem("Desenvolvedor Pleno");
        PosComboBoxCadastro.addItem("Desenvolvedor Junior"); PosComboBoxCadastro.addItem("Estagiario de Ti");
        PosComboBoxCadastro.addItem("Gerente Administrativo"); PosComboBoxCadastro.addItem("Auxiliar Administrativo");
        PosComboBoxCadastro.addItem("Advogado Chefe"); PosComboBoxCadastro.addItem("Auxiliar Juridico");
        PosComboBoxCadastro.addItem("Estagiario Juridico"); PosComboBoxCadastro.addItem("Admin");

        GeneroComboBoxCadastro.addItem("");
        GeneroComboBoxCadastro.addItem("Feminino");
        GeneroComboBoxCadastro.addItem("Masculino");
        GeneroComboBoxCadastro.addItem("Outros");

        TurnComboBoxCadastro.addItem("");
        TurnComboBoxCadastro.addItem("7:00 - 14:00");
        TurnComboBoxCadastro.addItem("14:00 - 21:00");
        TurnComboBoxCadastro.addItem("10:00 - 14:00");
        TurnComboBoxCadastro.addItem("14:00 - 18:00");

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@.{5,}\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private int gerarId(String cargo, String cpf) {
        String prefixo = "";
        if (cargo.equals("Gerente de Projetos") || cargo.equals("Engenheiro de Software") ||
                cargo.equals("Desenvolvedor Senior") || cargo.equals("Desenvolvedor Pleno") ||
                cargo.equals("Desenvolvedor Junior") || cargo.equals("Estagiario de Ti")) {
            prefixo = "987";
        } else if (cargo.equals("Gerente Administrativo") || cargo.equals("Auxiliar Administrativo")) {
            prefixo = "789";
        } else if (cargo.equals("Advogado Chefe") || cargo.equals("Auxiliar Juridico") ||
                cargo.equals("Estagiario Juridico")) {
            prefixo = "897";
        } else if (cargo.equals("Admin")) {
            prefixo = "100";
        }

        String cpfDigitos = cpf.replaceAll("\\D", "");
        String suffixo = cpfDigitos.substring(0, 3);

        String idStr = prefixo + suffixo;
        return Integer.parseInt(idStr);
    }

    private User cadastrarUsuario() {
        String username = UsernameFieldCadastro.getText();
        String password = new String(PasswordFieldCadastro.getPassword());
        String nome = NameFieldCadastro.getText();
        String cpf = CpfFieldCadastro.getText();
        String cargo = (String) PosComboBoxCadastro.getSelectedItem();

        if (username.isEmpty() || password.isEmpty() || nome.isEmpty() || cpf.isEmpty() || cargo.isEmpty()) {
            JOptionPane.showMessageDialog(FrameCadastro, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            if (cpf.length() != 14) {
                throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos!");
            }
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(FrameCadastro, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (username != null && password != null) {
            User usuario = new User();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setCargo(cargo);
            usuario.setUsuarioid(gerarId(cargo, cpf));
            return usuario;
        }
        return null;
    }

    private Funcionarios cadastrarFuncionario() {
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

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(FrameCadastro, "Email inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (!comparaEmail(email)) {
            JOptionPane.showMessageDialog(FrameCadastro, "Email já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || dataNascimento.isEmpty() || genero.isEmpty() || cargo.isEmpty()) {
            JOptionPane.showMessageDialog(FrameCadastro, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            if (cpf.length() != 14 ) {
                throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos!");
            } else if (!isCpfValid(cpf)) {
                throw new IllegalArgumentException("CPF invalido!");
            } else if (!comparaCpf(cpf)) {
                throw new IllegalArgumentException("CPF já cadastrado!");
            }
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(FrameCadastro, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (nome != null && cpf != null && email != null && dataNascimento != null && genero != null && cargo != null && horario != null) {
            Funcionarios funcionario = new Funcionarios();

            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setEmail(email);
            funcionario.setDataNascimento(dataNascimento);
            funcionario.setGenero(genero);
            funcionario.setCargo(cargo);
            funcionario.setHorario(horario);
            funcionario.setFuncionarioId(gerarId(cargo,cpf));
            funcionario.setPasswordSalt(gerarSal());
            funcionario.setPasswordPresenca(gerarSenhaPresenca(funcionario.getFuncionarioId(),cpf, nome, funcionario.getPasswordSalt()));
            return funcionario;
        }
        return null;
    }

    private String gerarSenhaPresenca(Integer funcionarioId, String cpf, String nome, String hashedSalt) {
        String password = nome.substring(0, 2) + "#" + cpf.substring(0, 3);
        String hashedPassword = BCrypt.withDefaults().hashToString(6, password.toCharArray());

        String saltedPassword = hashedPassword + hashedSalt;

        return saltedPassword;
    }

    private String gerarSal() {
        Random random = new Random();
        int sal = random.nextInt(90000) + 10000;
        String salt = String.valueOf(sal);
        String hashedSalt = BCrypt.withDefaults().hashToString(6, salt.toCharArray());

        return hashedSalt;
    }

    private boolean isCpfValid (String cpf){
        String  cpfVerify = cpf.substring(0,3) + cpf.substring(4,7) + cpf.substring(8,11) + cpf.substring(12,14);

        if (cpfVerify.equals("00000000000") || cpfVerify.equals("11111111111") || cpfVerify.equals("22222222222") || cpfVerify.equals("33333333333") || cpfVerify.equals("44444444444") || cpfVerify.equals("55555555555") || cpfVerify.equals("66666666666") || cpfVerify.equals("77777777777") || cpfVerify.equals("88888888888") || cpfVerify.equals("99999999999") || (cpfVerify.length() != 11)){
            return(false);
        }

        char digito10, digito11;
        int soma, i, resto, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            soma = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere "0" no inteiro 0
                // (48 eh a posicao de "0" na tabela ASCII)
                num = (int)(cpfVerify.charAt(i) - 48);
                soma = soma + (num * peso);
                peso = peso - 1;
            }

            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11)){
                digito10 = '0';
            } else{
                digito10 = (char)(resto + 48); // converte no respectivo caractere numerico
            }

            // Calculo do 2o. Digito Verificador
            soma = 0;
            peso = 11;
            for(i = 0; i < 10; i++) {
                num = (int)(cpfVerify.charAt(i) - 48);
                soma = soma + (num * peso);
                peso = peso - 1;
            }

            resto = 11 - (soma % 11);
            if ((resto == 10) || (resto == 11)){
                digito11 = '0';
            } else{
                digito11 = (char)(resto + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((digito10 == cpfVerify.charAt(9)) && (digito11 == cpfVerify.charAt(10))){
                return(true);
            } else{
                return(false);
            }

        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public boolean comparaCpf(String cpf) {
        List<String> cpfsExistentes = DbConnection.cpfsExistentes();

        for (String cpfExistente : cpfsExistentes) {
            if (cpf.equals(cpfExistente)) {
                System.out.println("CPF já Cadastrado");
                return false;
            }
        }
        System.out.println("CPF não Cadastrado");
        return true;
    }

    public boolean comparaEmail(String email) {
        List<String> emailsExistentes = DbConnection.emailsExistentes();

        for (String emailExistente : emailsExistentes) {
            if (email.equals(emailExistente)) {
                System.out.println("E-mail já Cadastrado");
                return false;
            }
        }
        System.out.println("E-mail não Cadastrado");
        return true;
    }

}