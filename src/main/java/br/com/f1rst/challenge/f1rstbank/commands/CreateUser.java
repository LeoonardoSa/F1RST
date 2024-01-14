package br.com.f1rst.challenge.f1rstbank.commands;

import java.io.Serializable;

public class CreateUser implements Serializable {
    private static final long serialVersionUID = 1L;
	private Integer clienteId;
    private String email;
    private String password;

    CreateUser() {
    }

    public CreateUser(Integer clienteId, String email, String password) {
        this.clienteId = clienteId;
        this.email = email;
        this.password = password;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "CreateUser{" +
                "clienteId=" + clienteId +
                ", email='" + email + '\'' +
                '}';
    }
}
