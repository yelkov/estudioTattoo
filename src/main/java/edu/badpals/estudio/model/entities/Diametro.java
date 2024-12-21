package edu.badpals.estudio.model.entities;

public enum Diametro {
        CERO_OCHO("08"),
        DIEZ("10"),
        DOCE("12"),
        CATORCE("14");

        private final String value;

        Diametro(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Diametro fromString(String value) {
            for (Diametro diametro : Diametro.values()) {
                if (diametro.getValue().equals(value)) {
                    return diametro;
                }
            }
            throw new IllegalArgumentException("Valor no válido: " + value);
        }
}
