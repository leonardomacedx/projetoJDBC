package model.entities;

import java.util.Objects;

public class Funcionario {

    private Integer id;
    private String nome;
    private String email;
    private Departamento departamento;

    public Funcionario() {
    }

    public Funcionario(Integer id) {
        this.id = id;
    }

    public Funcionario(Integer id, String nome, String email, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome());
    }

    @Override
    public String toString() {
        return "Id: " + id +
                ", nome: " + nome +
                ", email: " + email + "\n" +
                "Departamento:\n" + departamento;
    }
}
