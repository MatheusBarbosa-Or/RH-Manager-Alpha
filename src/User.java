public class User {
    String username;
    String password;
    String nome;
    String cpf;
    String email;
    String dataNascimento;
    String sexo;
    String cargo;

    void identify(){
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

