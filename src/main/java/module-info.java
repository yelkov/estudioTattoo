module edu.badpals.estudio.estudiotattoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens edu.badpals.estudio to javafx.fxml;
    exports edu.badpals.estudio;
}