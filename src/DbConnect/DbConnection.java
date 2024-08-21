package DbConnect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Classes.User;
import Classes.Funcionarios;

public class DbConnection {
    private static final String URL = "jdbc:sqlite:DataBase/RH_Manager_DB.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void inserirUsuario(User user) {
        String sql = "INSERT INTO Usuarios(Username, Password, Nome, CPF, Cargo, Id) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNome());
            pstmt.setString(4, user.getCpf());
            pstmt.setString(5, user.getCargo());
            pstmt.setInt(6, user.getUsuarioid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inserirFuncionario(Funcionarios funcionario) {
        String sql = "INSERT INTO Funcionarios(Nome, CPF, Email, DataNascimento, Genero, Cargo, Horario, Id, PasswordPresenca, PasswordSalt) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getEmail());
            pstmt.setString(4, funcionario.getDataNascimento());
            pstmt.setString(5, funcionario.getGenero());
            pstmt.setString(6, funcionario.getCargo());
            pstmt.setString(7, funcionario.getHorario());
            pstmt.setInt(8, funcionario.getFuncionarioId());
            pstmt.setString(9, funcionario.getPasswordPresenca());
            pstmt.setString(10, funcionario.getPasswordSalt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Funcionarios> buscarTodosFuncionarios() {
        List<Funcionarios> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM Funcionarios";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setCpf(rs.getString("CPF"));
                funcionario.setEmail(rs.getString("Email"));
                funcionario.setDataNascimento(rs.getString("DataNascimento"));
                funcionario.setGenero(rs.getString("Genero"));
                funcionario.setCargo(rs.getString("Cargo"));
                funcionario.setHorario(rs.getString("Horario"));
                funcionario.setFuncionarioId(rs.getInt("Id"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    public static void desligarFuncionario(Funcionarios funcionario) {
        String sql = "DELETE FROM Funcionarios WHERE Id = (?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, funcionario.getFuncionarioId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void uploadRelatorio(String caminho, String nomeArquivo, Integer funcionarioId) {
        String sql = "INSERT INTO Relatorios(Titulo, File, FuncionarioId) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             FileInputStream fis = new FileInputStream((caminho))) {

            pstmt.setString(1, nomeArquivo);
            pstmt.setBinaryStream(2, fis, (int) new File(caminho).length());
            pstmt.setInt(3, funcionarioId); // Insere o ID do funcionário

            pstmt.executeUpdate();
            System.out.println("File uploaded successfully!");

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Funcionarios infoFuncionario(int id){
        try(Connection conn = connect()) {
            String sql = "SELECT Nome, CPF, DataNascimento, Email, Genero, Cargo, Horario, Id FROM Funcionarios WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Funcionarios funcSelected = new Funcionarios();

                String nome = rs.getString("Nome");
                String cpf = rs.getString("CPF");
                String dataNascimento = rs.getString("DataNascimento");
                String email = rs.getString("Email");
                String genero = rs.getString("Genero");
                String cargo = rs.getString("Cargo");
                String horario = rs.getString("Horario");
                int funcionarioId = rs.getInt("Id");

                funcSelected.setNome(nome);
                funcSelected.setCpf(cpf);
                funcSelected.setDataNascimento(dataNascimento);
                funcSelected.setEmail(email);
                funcSelected.setGenero(genero);
                funcSelected.setCargo(cargo);
                funcSelected.setHorario(horario);
                funcSelected.setFuncionarioId(funcionarioId);

                return funcSelected;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Funcionarios> buscarFuncionario(String funcPesquisado){
        List<Funcionarios> funcionarioPesquisado = new ArrayList<>();
        String sql = "SELECT * FROM Funcionarios WHERE Nome LIKE ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + funcPesquisado + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setCpf(rs.getString("CPF"));
                funcionario.setEmail(rs.getString("Email"));
                funcionario.setDataNascimento(rs.getString("DataNascimento"));
                funcionario.setGenero(rs.getString("Genero"));
                funcionario.setCargo(rs.getString("Cargo"));
                funcionario.setHorario(rs.getString("Horario"));
                funcionario.setFuncionarioId(rs.getInt("Id"));
                funcionarioPesquisado.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarioPesquisado;
    }

    public static User realizarLogin(String username, String password){
        String sql = "SELECT * FROM Usuarios WHERE username = ? AND password = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User usuario = new User();
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCargo(rs.getString("cargo"));
                usuario.setUsuarioid(rs.getInt("Id"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<String> cpfsExistentes() {
        List<String> cpfsCadastrados = new ArrayList<>();
        String sql = "SELECT CPF FROM Funcionarios";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String cpfExistente = rs.getString("CPF");
                cpfsCadastrados.add(cpfExistente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cpfsCadastrados;
    }

    public static List<String> emailsExistentes() {
        List<String> emailsCadastrados = new ArrayList<>();
        String sql = "SELECT Email FROM Funcionarios";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String emailExistente = rs.getString("Email");
                emailsCadastrados.add(emailExistente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emailsCadastrados;
    }
}