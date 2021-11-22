package com.VPI.VPI.Dtos;

public class TokenMobileDto {
    private String idCliente;
    private String token;

    public TokenMobileDto() {
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
