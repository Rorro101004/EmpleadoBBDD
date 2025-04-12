/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sql;

import java.sql.SQLException;
import java.util.*;


/**
 *
 * @author roroc
 */
public class SQL {
    public static HashMap<Integer ,Empleado> empleados = new HashMap();
    public static void main(String[] args) {
        MenuEmpleados menu = new MenuEmpleados();
        menu.setVisible(true);

    }
}
