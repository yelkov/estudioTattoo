module edu.badpals.estudio.estudiotattoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.desktop;


    opens edu.badpals.estudio to javafx.fxml;
    opens edu.badpals.estudio.model.cabina;
    opens edu.badpals.estudio.model.cliente;
    opens edu.badpals.estudio.model.cita;
    exports edu.badpals.estudio;
    exports edu.badpals.estudio.controller;
    opens edu.badpals.estudio.model.trabajador;
    opens edu.badpals.estudio.model.aguja;
    opens edu.badpals.estudio.model.producto;
    opens edu.badpals.estudio.model.stock;
    opens edu.badpals.estudio.controller;
}