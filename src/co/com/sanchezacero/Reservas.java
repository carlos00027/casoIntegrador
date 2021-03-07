package co.com.sanchezacero;
import java.sql.*;
public class Reservas {
    
    Connection conn;
    
    String mariaDatabase = "restaurante";
    String mariaUrl = "jdbc:mariadb://localhost:3306/"+mariaDatabase;
    String mariaUser = "root";
    String mariaPassword = "password";

    public Connection conectar(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(mariaUrl,mariaUser,mariaPassword);
            System.out.println("Conectado a la base de datos");
        } catch (ClassNotFoundException|SQLException ex) {
            System.out.println(ex);
            System.out.println("No conectao a la base de datos");
        }
        return conn;
    }
    public void desconectar (){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("No pudo desconectar de la base de datos");
        }
    }
    public static void main(String[] args) {

        try {  
            Reservas mariaCx = new Reservas();
            mariaCx.conectar();
            
            Statement stmt = mariaCx.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservas");
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
            }
            System.out.println("Hello, World!");
            mariaCx.desconectar();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
