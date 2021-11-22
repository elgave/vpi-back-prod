package com.VPI.VPI.Dtos;

public class RechazoBloqueoDTO {

    private String email;

    private String motivoRechazo;

    public RechazoBloqueoDTO(String email, String motivoRechazo) {
        this.email = email;
        this.motivoRechazo = motivoRechazo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }
}
