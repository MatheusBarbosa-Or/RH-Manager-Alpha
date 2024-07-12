package Classes;

public class User {
    private String username;
    private String password;
    private String nome;
    private String cpf;
    private String email;
    private String dataNascimento;
    private String sexo;
    private String cargo;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void identify(){
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Email: " + this.email);
        System.out.println("Data de nascimento: " + this.dataNascimento);
        System.out.println("Sexo: " + this.sexo);
        System.out.println("Cargo: " + this.cargo);
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nCPF: " + this.cpf + "\nEmail: " + this.email + "\nData de nascimento: " + this.dataNascimento + "\nSexo: " + this.sexo + "\nCargo: " + this.cargo;
    }
}
