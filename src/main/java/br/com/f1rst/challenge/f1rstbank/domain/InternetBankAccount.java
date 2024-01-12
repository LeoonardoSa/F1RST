package br.com.f1rst.challenge.f1rstbank.domain;

public class InternetBankAccount {
    private String email;
    private String id;

    public InternetBankAccount(String id, String email) {
        this.id = id;
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "InternetBankAccount{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
