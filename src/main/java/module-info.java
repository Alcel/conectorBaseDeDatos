module com.example.conectorbasededatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;
    requires java.sql;


    opens com.example.conectorbasededatos to javafx.fxml;
    exports com.example.conectorbasededatos;
}