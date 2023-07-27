package model.entities;

import java.util.Objects;

public class Cliente {

    private Integer id;
    private String clienteCpf;
    private String nome;
    private String email;
    private String celular;
    private String contaBanco;
    private String endereco;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Integer id, String clienteCpf, String nome, String email, String celular, String contaBanco, String endereco) {
        this.id = id;
        this.clienteCpf = clienteCpf;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.contaBanco = contaBanco;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContaBanco() {
        return contaBanco;
    }

    public void setContaBanco(String contaBanco) {
        this.contaBanco = contaBanco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getId(), cliente.getId()) && Objects.equals(getClienteCpf(), cliente.getClienteCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClienteCpf());
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", clienteCpf='" + clienteCpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", contaBanco='" + contaBanco + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
