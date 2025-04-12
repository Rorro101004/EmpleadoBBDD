/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sql;

import static com.mycompany.sql.SQL.*;
import java.sql.*;
import java.util.*;


/**
 *
 * @author roroc
 */
public class GestionBBDD {

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String baseDatos = "Empleados";
    private static final String usuario = "root";
    private static final String contrasenya = "";
    private Connection conn;
/*
    Establece la conexión con la base de datos utilizando los parámetros `url`, `usuario` y `contrasenya`; llama 
    a los metodos crearBBDD y CrearTabla
    */
    public GestionBBDD() {
        try {
            conn = DriverManager.getConnection(url, usuario, contrasenya);
            //Otros metodos para crear la base de datos
            CrearBBDD();
            CrearTablaGestionEmpleados();
        } catch (SQLException ex) {
            System.err.println("Error en la conexion con la base de datos");
        }

    }
/*
    Crea la base de datos si no existe, ejecutando la query
 * 
    */
    public void CrearBBDD() throws SQLException {
        String query = "Create database if not exists " + baseDatos + " ;";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn = DriverManager.getConnection(url + baseDatos, usuario, contrasenya);
        } catch (SQLException ex) {
            System.err.println("Error a la hora de crear la base de Datos ");
        } finally {

            stmt.close();
        }
    }
    /*
    Define y ejecuta una consulta SQL para crear la tabla `GestionEmpleado` con sus respectivos campos
    */

    public void CrearTablaGestionEmpleados() throws SQLException {
        String query = "Create Table if not exists GestionEmpleado("
                + "id int primary key auto_increment,"
                + "nombre varchar(100), "
                + "edad int, "
                + "departamento varchar(100), "
                + "salario double);";

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println("Error para crear la tabla");

        } finally {
            stmt.close();
        }
    }
/**
 * Ejecuta una query para insertar empleados en la base de datos
 * @param v_nombre
 * @param v_edad
 * @param v_departamento
 * @param v_salario
 * @throws SQLException 
 */
    public void InsertarEnGestionEmpleados(String v_nombre, int v_edad, String v_departamento, double v_salario) throws SQLException {
        String query = "Insert into GestionEmpleado(nombre,edad,departamento,salario)"
                + " Values('" + v_nombre + "'," + v_edad + ",'" + v_departamento + "'," + v_salario + ");";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            stmt.close();
        }
    }
/**
 * Ejecuta una query para actualizar empleados en la base de datos
 * @param v_id
 * @param v_nombre
 * @param v_edad
 * @param v_departamento
 * @param v_salario
 * @throws SQLException 
 */
    public void ActualizarEnGestionEmpleados(int v_id, String v_nombre, int v_edad, String v_departamento, double v_salario) throws SQLException {
        String query = "UPDATE GestionEmpleado SET nombre='" + v_nombre + "', edad=" + v_edad + ", departamento='" + v_departamento + "', salario=" + v_salario + " WHERE id=" + v_id;

        System.out.println(query);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            stmt.close();
        }
    }
/**
 * Ejecuta una query para borrar empleados en la base de datos
 * @param v_id
 * @throws SQLException 
 */
    public void BorrarEnGestionEmpleados(int v_id) throws SQLException {
        String query = "DELETE FROM GestionEmpleado WHERE id='" + v_id + "'";
        System.out.println(query);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            stmt.close();
        }
    }
/**
 * Ejecuta una query para listas a los empleados de la base de datos
 * @throws SQLException 
 */
    public void ListarEnGestionEmpleados() throws SQLException {
        String query = "Select * from GestionEmpleado;";
        System.out.println(query);
        Statement stmt = null;
        ResultSet rs = null;
        Empleado empleado;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                empleado = new Empleado(rs.getInt("id"),
                        rs.getString("nombre"), 
                        rs.getInt("edad"),
                        rs.getString("departamento"), 
                        rs.getDouble("salario"));
                empleados.put(rs.getInt("id"),empleado);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            stmt.close();
        }
    }
    /**
     * Ejecuta una query para ver si existe un empleado con un id "tal" en la base de datos
     * @param vId
     * @return
     * @throws SQLException 
     */
    public boolean encontrarEmpleado (int vId) throws SQLException{
        String query = "Select * from GestionEmpleado;";
        System.out.println(query);
        Statement stmt = null;
        ResultSet rs = null;
        Empleado empleado;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getInt("id")==vId){
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            stmt.close();
        }
        return false;
        //implementar el metodo para encontrar empleado por id
    }
    /**
     * Devuelve un objeto Empleado
     * @param idBuscado
     * @return 
     */
    public Empleado devolverEmpleado(int idBuscado){
        return empleados.get(idBuscado); 
    }
}
