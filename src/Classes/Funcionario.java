package Classes;

public class Funcionario {
    //Caracteristicas da classe
    private String nome;
    private String cpf;
    private String email;
    private String dataNascimento;
    private String genero;
    private String cargo;
    private String horario;
    private Integer funcionarioId;
    private String passwordPresenca;
    private String passwordSalt;

    //Getters & Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer FuncionarioId) {
        this.funcionarioId = FuncionarioId;
    }

    public String getPasswordPresenca() {
        return passwordPresenca;
    }

    public void setPasswordPresenca(String passwordPresenca) {
        this.passwordPresenca = passwordPresenca;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}