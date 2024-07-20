package Database;

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
    private static final String URL = "jdbc:sqlite:DataBase/RH_Manager_DB.db"; // Corrigido a URL

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
        String sql = "INSERT INTO Usuarios(Username, Password, Nome, CPF, Cargo) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNome());
            pstmt.setString(4, user.getCpf());
            pstmt.setString(5, user.getCargo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inserirFuncionario(Funcionarios funcionario) {
        String sql = "INSERT INTO Funcionarios(Nome, CPF, Email, DataNascimento, Genero, Cargo, Horario) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getEmail());
            pstmt.setString(4, funcionario.getDataNascimento());
            pstmt.setString(5, funcionario.getGenero());
            pstmt.setString(6, funcionario.getCargo());
            pstmt.setString(7, funcionario.getHorario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Funcionarios> buscarTodosFuncionarios() {
        List<Funcionarios> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM Funcionarios";

        try (Connection conn = DbConnection.connect();
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
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }
}
