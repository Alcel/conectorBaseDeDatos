package com.example.conectorbasededatos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {
    private ObservableList<Oficina>oficinaLista=FXCollections.observableArrayList();
    private Oficina ofAux;

    private Connection c;
    @FXML
    private TableView tvDatos;
    @FXML
    private TableColumn officeCode;
    @FXML
    private TableColumn city;
    @FXML
    private TableColumn phone;
    @FXML
    private TableColumn addressLine1;
    @FXML
    private TableColumn addressLine2;
    @FXML
    private TableColumn state;
    @FXML
    private TableColumn country;
    @FXML
    private TableColumn postalCode;
    @FXML
    private TableColumn territory;

    private Oficina auxOf;
    @FXML
    private Button btnIns;
    @FXML
    private TextField inboxOfficeCode;
    @FXML
    private TextField inboxCity;
    @FXML
    private TextField inboxPhone;
    @FXML
    private TextField inboxAdress1;
    @FXML
    private TextField inboxAdress2;
    @FXML
    private TextField inboxCountry;
    @FXML
    private TextField inboxState;
    @FXML
    private TextField inboxTerritory;
    @FXML
    private TextField inboxPostalCode;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnBorrar;

    public void initialize(){
        Conectar();
    }

    public void Conectar(){
         ObservableList<Oficina>datos=FXCollections.observableArrayList();

        try{
            tvDatos.getItems().clear();
            c = DriverManager.getConnection("jdbc:mariadb://localhost:5555/noinch?useSSL=false"
                    ,"adminer",
                    "adminer");


            ResultSet rs = c.createStatement().executeQuery("Select * from offices");


            while (rs.next()) {
                auxOf=new Oficina(
                        rs.getString("officeCode"),
                        rs.getString("city"),
                        rs.getString("phone"),
                        rs.getString("addressLine1"),
                        rs.getString("addressLine2"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getString("postalCode"),
                        rs.getString("territory")
                );
                oficinaLista.add(auxOf);
                System.out.println("Row [1] added " + auxOf.toString());
            }
            officeCode.setCellValueFactory(new PropertyValueFactory<Oficina, String>("officeCode"));
            city.setCellValueFactory(new PropertyValueFactory<Oficina, String>("city"));
            phone.setCellValueFactory(new PropertyValueFactory<Oficina, String>("phone"));
            addressLine1.setCellValueFactory(new PropertyValueFactory<Oficina, String>("addressLine1"));
            addressLine2.setCellValueFactory(new PropertyValueFactory<Oficina, String>("addressLine2"));
            state.setCellValueFactory(new PropertyValueFactory<Oficina, String>("state"));
            country.setCellValueFactory(new PropertyValueFactory<Oficina, String>("country"));
            postalCode.setCellValueFactory(new PropertyValueFactory<Oficina, String>("postalCode"));
            territory.setCellValueFactory(new PropertyValueFactory<Oficina, String>("territory"));
            tvDatos.setItems(oficinaLista);
            c.close();
        }
        catch(Exception e){
            e.printStackTrace();
            tvDatos.getColumns().clear();
            tvDatos.setItems(null);
            System.out.println("Error on Building Data");
        }

    }

    public void altaOficina(ActionEvent actionEvent){

        int registrosAfectadoConsulta = 0;
        try {
            c = DriverManager.getConnection("jdbc:mariadb://localhost:5555/noinch?useSSL=false"
                    , "adminer",
                    "adminer");

            String SQL = "INSERT INTO offices("
                    +"officeCode ,"
                    +"city ,"
                    +"phone ,"
                    +"addressLine1 ,"
                    +"addressLine2 ,"
                    +"state ,"
                    +"country ,"
                    +"postalCode ,"
                    +"territory )"
                    +"VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = c.prepareStatement(SQL);
            st.setString(1, inboxOfficeCode.getText().toString());
            st.setString(2, inboxCity.getText().toString());
            st.setString(3, inboxPhone.getText().toString());
            st.setString(4, inboxAdress1.getText().toString());
            st.setString(5, inboxAdress2.getText().toString());
            st.setString(6, inboxState.getText().toString());
            st.setString(7, inboxCountry.getText().toString());
            st.setString(8, inboxPostalCode.getText().toString());
            st.setString(9, inboxTerritory.getText().toString());
            registrosAfectadoConsulta = st.executeUpdate();
            st.close();
            Conectar();
            c.close();

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
        }
    }


    @FXML
    public void actualizar(ActionEvent actionEvent) {
        try {
            c = DriverManager.getConnection("jdbc:mariadb://localhost:5555/noinch?useSSL=false"
                    , "adminer",
                    "adminer");

            String SQL = "update offices " +
                    "set " +
                    "city ='"+inboxCity.getText().toString()+"',"+
                    "phone='"+inboxPhone.getText().toString()+"',"+
                    "addressLine1='"+inboxAdress1.getText().toString()+"',"
                    +"addressLine2='"+inboxAdress2.getText().toString()+"',"
                    +"state='"+inboxState.getText().toString()+"',"
                    +"country='"+inboxCountry.getText().toString()+"',"
                    +"postalCode='"+inboxPostalCode.getText().toString()+"',"
                    +"territory='"+inboxTerritory.getText().toString()+"'"
                    +" where  officeCode ="+"'"+inboxOfficeCode.getText().toString()+"'"
                    ;
            PreparedStatement st = c.prepareStatement(SQL);

            st.executeUpdate();
            st.close();
            Conectar();
            c.close();

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
        }
    }



    @FXML
    public void borrado(ActionEvent actionEvent) {
        try {
            String id = inboxOfficeCode.getText().toString();
            c = DriverManager.getConnection("jdbc:mariadb://localhost:5555/noinch?useSSL=false"
                    , "adminer",
                    "adminer");

            String SQL = "delete from offices where officeCode ="+"'"+id +"'";
            PreparedStatement st = c.prepareStatement(SQL);
            st.executeUpdate();
            st.close();
            Conectar();
            c.close();

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
        }
    }
}