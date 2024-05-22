package br.com.fiap.coletadelixo.enums;

public enum Status {
    CONFIRMED("confirmed"),
    PENDING("pending"),
    ABORTED("aborted");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
