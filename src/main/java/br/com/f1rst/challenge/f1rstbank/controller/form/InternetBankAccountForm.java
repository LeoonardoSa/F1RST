package br.com.f1rst.challenge.f1rstbank.controller.form;

import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;

public class InternetBankAccountForm {
    private String email;
    private String password;
    private String rePassword;

    private DadosDaConta dadosDaConta;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRePassword() {
        return rePassword;
    }


    public boolean rePasswordMatch() {
        return password.equals(rePassword);
    }

    public DadosDaConta getDadosDaConta() {
        return dadosDaConta;
    }
}
