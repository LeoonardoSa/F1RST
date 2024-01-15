package br.com.f1rst.challenge.f1rstbank.domain;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class DadosDaConta {

    @NotBlank
    private String banco;
    @NotBlank
    private String agencia;
    @NotBlank
    private String numero;
    @NotBlank
    private String status;

    protected DadosDaConta() {
    }

    public DadosDaConta(String banco, String agencia, String numero, String status) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
        this.status = status;
    }

    public String getBanco() {
        return banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }
    
    public String getStatus() {
        return status;
    }

	@Override
	public int hashCode() {
		return Objects.hash(agencia, banco, numero, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DadosDaConta other = (DadosDaConta) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(banco, other.banco)
				&& Objects.equals(numero, other.numero) && Objects.equals(status, other.status);
	}

}
