module edu.badpals.estudio.estudiotattoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens edu.badpals.estudio to javafx.fxml;
    opens edu.badpals.estudio.model.entities;
    opens edu.badpals.estudio.model.cabina;
    exports edu.badpals.estudio;
    opens edu.badpals.estudio.model.trabajador;
}