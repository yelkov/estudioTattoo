package edu.badpals.estudio.model.entities;

public enum NumeroAgujas {
    UNO("1"),
    TRES("3"),
    CINCO("5"),
    SIETE("7"),
    NUEVE("9"),
    ONCE("11"),
    TRECE("13"),
    CATORCE("14"),
    QUINCE("15"),
    DIECISIETE("17"),
    DIECINUEVE("19"),
    VEINTIUNO("21"),
    VEINTICUATRO("24"),
    VEINTISIETE("27");

    private final String valor;

    NumeroAgujas(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static NumeroAgujas fromString(String valor) {
        for (NumeroAgujas numero : NumeroAgujas.values()) {
            if (numero.getValor().equals(valor)) {
                return numero;
            }
        }
        throw new IllegalArgumentException("Valor no válido: " + valor);
    }
}
