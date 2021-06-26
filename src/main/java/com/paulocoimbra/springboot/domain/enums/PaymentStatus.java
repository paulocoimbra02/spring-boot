package com.paulocoimbra.springboot.domain.enums;

public enum PaymentStatus {

    PENDING(1, "Pending"),
    SETTLED(2, "Settled"),
    CANCELLED(3, "Cancelled");

    private int cod;
    private String description;

    private PaymentStatus(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for(PaymentStatus ct : PaymentStatus.values()){
            if(cod.equals(ct.getCod())) {
                return ct;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + cod);

    }
}
