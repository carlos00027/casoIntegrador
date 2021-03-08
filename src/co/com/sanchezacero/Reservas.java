package co.com.sanchezacero;
import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Reservas {
    
    Connection conn;
    
    String mariaDatabase = "restaurante";
    String mariaUrl = "jdbc:mariadb://localhost:3306/"+mariaDatabase;
    String mariaUser = "root";
    String mariaPassword = "password";

    static Scanner s;
    public Connection conectar(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(mariaUrl,mariaUser,mariaPassword);
            // System.out.println("Conectado a la base de datos");
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
    public static void main(String[] args) throws ParseException {
        s  = new Scanner(System.in);
        DtoReserva dtoReserva = new DtoReserva();
        int opcion = menuOpciones();
        System.out.println("Has seleccionado: " + opcion);

        while (opcion != 5) {
            if(opcion == 1){
                System.out.println("Seleccionaste crear reserva");
                dtoReserva = new DtoReserva();
                
                System.out.println("Digite su identificacion");
                dtoReserva.setNoDocumento(s.nextInt());
                s.nextLine();
                System.out.println("Digite nombre del cliente");
                String nnombre = s.nextLine();
                dtoReserva.setNombreCliente(nnombre);
                
                System.out.println("Digite numero de telefono del cliente");
                dtoReserva.setNoContacto(s.nextLine());
                
                System.out.println("Digite correo electronico");
                dtoReserva.setCorreo(s.nextLine());
                
                System.out.println("Digite fecha en formato dd/mm/yyyy");
                SimpleDateFormat fechaScanner = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaFormat = fechaScanner.parse(s.nextLine());
                dtoReserva.setFechaReserva(fechaFormat);
                

                System.out.println("Seleccione estado");
                System.out.println("1. activo");
                System.out.println("2. inactivo");
                int seleccion = s.nextInt();
                
                if(seleccion == 1){
                    dtoReserva.setEstado("activo");
                } else {
                    dtoReserva.setEstado("inactivo");
                }

                System.out.println("Digite cantidad de personas");
                dtoReserva.setCantidadPersonas(s.nextInt());
                
                s.nextLine();
                System.out.println("Digite motivo");
                dtoReserva.setMotivo(s.nextLine());

                System.out.println("Digite tipo de decoracion");
                dtoReserva.setTipoDecoracion(s.nextLine());


                System.out.println("");
                System.out.println("Resumen de la reserva");
                System.out.println("");
                System.out.println("Documento: "+dtoReserva.getNoDocumento());
                System.out.println("Nombre: "+dtoReserva.getNombreCliente());
                System.out.println("Telefono: "+dtoReserva.getNoContacto());
                System.out.println("Correo electronico: "+dtoReserva.getCorreo());
                System.out.println("Fecha: "+dtoReserva.getFechaReserva());
                System.out.println("Estado: "+dtoReserva.getEstado());
                System.out.println("Cantidad de personas: "+dtoReserva.getCantidadPersonas());
                System.out.println("Motivo: "+dtoReserva.getMotivo());
                System.out.println("Tipo decoracion: "+dtoReserva.getTipoDecoracion());
                System.out.println("");
                
                crearReserva(dtoReserva);
            
            }  else if (opcion == 2){
                System.out.println("Seleccionaste cancelar reserva");
                System.out.println("Ingrese el numero de reserva para cancelar");
                
                cancelarReserva(s.nextInt());
            } else if (opcion == 3) {
                System.out.println("Seleccionaste modificar reserva");
                System.out.println("Ingresa el numero de la reserva");
                int id = s.nextInt();
                
                s.nextLine();
                System.out.println("Digite fecha en formato dd/mm/yyyy");
                SimpleDateFormat fechaScanner = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaFormat = fechaScanner.parse(s.nextLine());
                dtoReserva.setFechaReserva(fechaFormat);
                
                System.out.println("Digite cantidad de personas");
                dtoReserva.setCantidadPersonas(s.nextInt());

                s.nextLine();
                System.out.println("Digite motivo");
                dtoReserva.setMotivo(s.nextLine());

                System.out.println("Digite tipo de decoracion");
                dtoReserva.setTipoDecoracion(s.nextLine());

                modificarReserva(dtoReserva,id);
            } else if(opcion == 4){
                System.out.println("Seleccionaste la opcion listar reservas");
                System.out.println("Seleccione ahora el filtro aplicar");
                System.out.println("1. listar por Fecha");
                System.out.println("2. listar por numero de reserva");
                System.out.println("3. listar por numero de identificacion");
                int filtro = s.nextInt();
                listarReserva(filtro);
            }

            opcion = menuOpciones();
        }

        // try {  
        //     Reservas mariaCx = new Reservas();
        //     mariaCx.conectar();
            
        //     Statement stmt = mariaCx.conn.createStatement();
        //     ResultSet rs = stmt.executeQuery("SELECT * FROM reservas");
        //     while (rs.next()) {
        //         System.out.println(rs.getInt("id"));
        //     }
        //     System.out.println("Hello, World!");
        //     mariaCx.desconectar();
            
        // } catch (Exception e) {
        //     System.out.println(e);
        // }
    }
    public static int menuOpciones(){
        int opcion = 0;
        while (opcion < 1 || opcion > 5) {
            System.out.println("");
            System.out.println("Elige una opcion:");
            System.out.println("");
            System.out.println("1. Crear reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Modificar reserva");
            System.out.println("4. Consultar reservas");
            System.out.println("5. Salir");

            opcion = s.nextInt();
        }
        return opcion;
    }
    public static void crearReserva(DtoReserva dtoReserva) {
        System.out.println("procesando...");

        Reservas mariaCx = new Reservas();
        mariaCx.conectar();
        String queryInsert = "INSERT INTO reservas (identificacion,nombreCliente,noContacto,correo,fechaReserva,estado,cantidadPersonas,motivo,tipoDecoracion) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = mariaCx.conn.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,dtoReserva.getNoDocumento());
            stmt.setString(2,dtoReserva.getNombreCliente());
            stmt.setString(3,dtoReserva.getNoContacto());
            stmt.setString(4,dtoReserva.getCorreo());
            stmt.setDate(5,new java.sql.Date( dtoReserva.getFechaReserva().getTime()) );
            stmt.setString(6, dtoReserva.getEstado());
            stmt.setInt(7, dtoReserva.getCantidadPersonas());
            stmt.setString(8, dtoReserva.getMotivo());
            stmt.setString(9, dtoReserva.getTipoDecoracion());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()){
                System.out.println("El numero de reserva es: "+rs.getString(1));
            }
            mariaCx.conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("NO INSERTO RESERVA");
        }
        
        System.out.println("***Reserva creada con éxito***");
    }
    public static void cancelarReserva(int id) {
        System.out.println("procesando...");
        Reservas mariaCx = new Reservas();
        mariaCx.conectar();
        String queryUpdate = "UPDATE reservas SET estado = ? WHERE id = ? LIMIT 1";
        try {
            PreparedStatement stmt = mariaCx.conn.prepareStatement(queryUpdate);
            stmt.setString(1, "inactivo");
            stmt.setInt(2, id);
            stmt.execute();
            mariaCx.conn.close();
            System.out.println("Reserva "+id+" cancelada con exito");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("No es posible cancelar la reserva");
        }
    }
    public static void modificarReserva(DtoReserva dtoReserva, int id) {
        Reservas mariaCx = new Reservas();
        mariaCx.conectar();
        String query = "UPDATE reservas SET fechaReserva = ?, cantidadPersonas=?, motivo=?, tipoDecoracion=? WHERE id=?";
        try {
            PreparedStatement stmt = mariaCx.conn.prepareStatement(query);
            stmt.setDate(1, new java.sql.Date(dtoReserva.getFechaReserva().getTime()));
            stmt.setInt(2, dtoReserva.getCantidadPersonas());
            stmt.setString(3, dtoReserva.getMotivo());
            stmt.setString(4, dtoReserva.getTipoDecoracion());
            stmt.setInt(5, id);
            stmt.execute();

            System.out.println("Actualizado con exito");
            mariaCx.conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void listarReserva(int filtro) {
        Reservas mariaCx = new Reservas();
        mariaCx.conectar();

        if(filtro == 1){
            s.nextLine();
            System.out.println("Digite fecha en formato dd/mm/yyyy");
            SimpleDateFormat fechaScanner = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date fechaFormat = fechaScanner.parse(s.nextLine());
                String querySearch = "SELECT * FROM reservas WHERE fechaReserva = ? AND estado = 'activo'";
                PreparedStatement stmt;
                try {
                    stmt = mariaCx.conn.prepareStatement(querySearch);
                    stmt.setDate(1, new java.sql.Date(fechaFormat.getTime()));
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        System.out.println("------");
                        System.out.println("numero de reserva: "+rs.getInt("id"));
                        System.out.println("Identificacion: "+rs.getInt("identificacion"));
                        System.out.println("Nombre del cliente: "+rs.getString("nombreCliente"));
                        System.out.println("Numero de telefono: "+rs.getString("noContacto"));
                        System.out.println("Correo electronico: "+rs.getString("correo"));
                        System.out.println("Fecha de reserva: "+rs.getDate("fechaReserva"));
                        System.out.println("Estado: "+rs.getString("estado"));
                        System.out.println("Cantidad de personas: "+rs.getInt("cantidadPersonas"));
                        System.out.println("Motivo: "+rs.getString("motivo"));
                        System.out.println("Tipo decoracion: "+rs.getString("tipoDecoracion"));
                    }
                    mariaCx.conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } else if(filtro == 2){
            System.out.println("Ingresar el numero de reserva");
            int id = s.nextInt();
            String querySearch = "SELECT * from reservas WHERE id=? AND estado = 'activo' LIMIT 1";
            try {
                PreparedStatement stmt = mariaCx.conn.prepareStatement(querySearch);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("------");
                    System.out.println("numero de reserva: "+rs.getInt("id"));
                    System.out.println("Identificacion: "+rs.getInt("identificacion"));
                    System.out.println("Nombre del cliente: "+rs.getString("nombreCliente"));
                    System.out.println("Numero de telefono: "+rs.getString("noContacto"));
                    System.out.println("Correo electronico: "+rs.getString("correo"));
                    System.out.println("Fecha de reserva: "+rs.getDate("fechaReserva"));
                    System.out.println("Estado: "+rs.getString("estado"));
                    System.out.println("Cantidad de personas: "+rs.getInt("cantidadPersonas"));
                    System.out.println("Motivo: "+rs.getString("motivo"));
                    System.out.println("Tipo decoracion: "+rs.getString("tipoDecoracion"));
                }
                mariaCx.conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } else if (filtro == 3){
            System.out.println("Ingresar el numero de identificacion");
            int identificacion = s.nextInt();
            String querySearch = "SELECT * from reservas WHERE identificacion = ? AND estado = 'activo'";
            try {
                PreparedStatement stmt = mariaCx.conn.prepareStatement(querySearch);
                stmt.setInt(1, identificacion);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("------");
                    System.out.println("numero de reserva: "+rs.getInt("id"));
                    System.out.println("Identificacion: "+rs.getInt("identificacion"));
                    System.out.println("Nombre del cliente: "+rs.getString("nombreCliente"));
                    System.out.println("Numero de telefono: "+rs.getString("noContacto"));
                    System.out.println("Correo electronico: "+rs.getString("correo"));
                    System.out.println("Fecha de reserva: "+rs.getDate("fechaReserva"));
                    System.out.println("Estado: "+rs.getString("estado"));
                    System.out.println("Cantidad de personas: "+rs.getInt("cantidadPersonas"));
                    System.out.println("Motivo: "+rs.getString("motivo"));
                    System.out.println("Tipo decoracion: "+rs.getString("tipoDecoracion"));
                }
                mariaCx.conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    
}
