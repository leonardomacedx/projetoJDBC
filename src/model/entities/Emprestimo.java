package model.entities;

import java.util.Date;
import java.util.Objects;

public class Emprestimo {

    private Integer id;
    private Double valor;
    private Integer parcelas;
    private Double valorTotal;
    private Date dataEmprestimo;
    private Departamento departamento;
    private Cliente cliente;
    private Funcionario funcionario;

    public Emprestimo() {
    }

    public Emprestimo(Integer id, Double valor, Integer parcelas, Date dataEmprestimo, Departamento departamento, Cliente cliente, Funcionario funcionario) {
        this.id = id;
        this.valor = valor;
        this.parcelas = parcelas;
        this.dataEmprestimo = dataEmprestimo;
        this.departamento = departamento;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Double getValorTotal() {
        return valorTotal;
    }


    public void setValorTotal() {
        valorTotal = valor * (1 + (0.02 * parcelas));
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", valor=" + valor +
                ", parcelas=" + parcelas +
                ", valorTotal=" + valorTotal +
                ", dataEmprestimo=" + dataEmprestimo +
                ", departamento=" + departamento +
                ", cliente=" + cliente +
                ", funcionario=" + funcionario +
                '}';
    }
}
