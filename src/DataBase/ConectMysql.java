/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JoséFerdel
 */
public class ConectMysql {
    Connection Conexion;

public ConectMysql(String host,String user, String pass, String db_name) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://"+host+"/" + db_name, user, pass);
            JOptionPane.showMessageDialog(null, "Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConectMysql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConectMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public DefaultTableModel getValues( String Query) throws SQLException {
        DefaultTableModel v=new DefaultTableModel();
        v.addColumn("Nombre");
            Statement st = this.Conexion.createStatement();
            ResultSet resultSet = st.executeQuery(Query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int NumOfCol=rsmd.getColumnCount();
            for(int x=1;x<=NumOfCol;x++)
                 v.addColumn("Val"+(x));
            int ob=1;
            while (resultSet.next()) {
                String d[]= new String [NumOfCol+1];
                d[0]=""+ob++;
                for(int x=1;x<=NumOfCol;x++){
                    d[x]=resultSet.getString(x);
                }
                v.addRow(d);
            }
            return v;
    }

 public void createTable(String name,int x) {
        try {
            String Query = "CREATE TABLE IF NOT EXISTS " + name + "",bodya=" (";
            
            for(int i=1;i<=x;i++){
                if(i==x)
                 bodya+="Val"+i+" VARCHAR(50))";
                else
                bodya+="Val"+i+" VARCHAR(50),";
            }
            Query+=bodya;
            Statement st = Conexion.createStatement();
              st.executeUpdate(Query);
        } catch (SQLException ex) {
          // JOptionPane.showMessageDialog(null, "No se ha creado la tabla " + name + " de forma exitosa");
        }
    }   
public void insertData(String table_name,LinkedList d) {
        try {
            String Query = "INSERT INTO " + table_name + " ",body="VALUES(";
            for(int x=0;x<d.size();x++){
            if(x==d.size()-1)
               body+= "\"" +d.get(x) + "\") ";
            else
                body+= "\"" +d.get(x) + "\", ";
            }
            Query+=body;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }    
 public void createDB(String name) {
        try {
            String Query = "CREATE DATABASE IF NOT EXISTS " + name;
            Statement st = Conexion.createStatement();
           st.executeQuery(Query);
JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se ha creado la base de datos " + name + " de forma exitosa");
        }
    }
 public void closeConnection() {
        try {
            Conexion.close();
            //JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(ConectMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
