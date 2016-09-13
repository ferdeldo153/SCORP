/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileScorp;

import Variables.ImportarExcel;
import Variables.Value;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;
import sirp2.Observacional;
import sirp2.Semejanza;

/**
 *
 * @author home
 */
public class Files {
        public static int sizevalue(LinkedList au) {
        Value a = (Value) au.get(0);
        return a.getValue().size();
    }
 public void openOBS(JFileChooser file){
     File[] files = file.getSelectedFiles();
     for (int i=0; i<files.length; i++) {
String name =files[i].getName();
        String ext=Files.getExtension(name);
   if(ext.equals("scorp")||ext.equals("SCORP")){
         File abre = files[i];
        if (abre != null) {
             DefaultTableModel variables =new Files().openTable(abre,";");
                int nvariables = variables.getColumnCount() -1;
                int nobjetos = variables.getRowCount();
                Thread open=new Thread(){
                public void run(){
             new Observacional(variables, nobjetos, nvariables).setVisible(true);
             this.stop();
                }
                };
                open.start();
        }
         System.gc();// limpia el gc
        }
   if(ext.equals("ARFF")||ext.equals("arff")){
        File abre = files[i];
        if (abre != null) {
             DefaultTableModel variables =new Files().openweka(abre,",");
                int nvariables = variables.getColumnCount() -1;
                int nobjetos = variables.getRowCount();
                 Thread open=new Thread(){
                public void run(){
             new Observacional(variables, nobjetos, nvariables).setVisible(true);
             this.stop();
                             }
                };
                open.start();
        }
         System.gc();// limpia el gc
   }
    if(ext.equals("xls")||ext.equals("XLS")){
        File abre = files[i];
        if (abre != null) {
            ImportarExcel a = new ImportarExcel();
            try {
                a.importar(abre);
                DefaultTableModel variables = a.exceltable();
                int nvariables = variables.getColumnCount() - 1;
                int nobjetos = variables.getRowCount();
                 Thread open=new Thread(){
                public void run(){
                new Observacional(variables, nobjetos, nvariables).setVisible(true);
                this.stop();
                                             }
                };
                open.start();

            } catch (IOException ex) {
                Logger.getLogger(Observacional.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                Logger.getLogger(Observacional.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         System.gc();// limpia el gc
        }
     }
}
 public void openOBS2(JFileChooser file){
     File[] files = file.getSelectedFiles();
     for (int i=0; i<files.length; i++) {
String name =files[i].getName();
        String ext=Files.getExtension(name);
   if(ext.equals("scorp")||ext.equals("SCORP")){
         File abre = files[i];
        if (abre != null) {
             DefaultTableModel variables =new Files().openTable(abre,";");
                int nvariables = variables.getColumnCount() -1;
                int nobjetos = variables.getRowCount();
                Thread open=new Thread(){
                public void run(){
              new Semejanza("Abrir",variables,0).setVisible(true);
             this.stop();
                }
                };
                open.start();
        }
         System.gc();// limpia el gc
        }
    if(ext.equals("xls")||ext.equals("XLS")){
        File abre = files[i];
        if (abre != null) {
            ImportarExcel a = new ImportarExcel();
            try {
                a.importar(abre);
                DefaultTableModel variables = a.exceltable();
                int nvariables = variables.getColumnCount() - 1;
                int nobjetos = variables.getRowCount();
                 Thread open=new Thread(){
                public void run(){
                new Semejanza("Abrir",variables,0).setVisible(true);
                this.stop();
                                             }
                };
                open.start();

            } catch (IOException ex) {
                Logger.getLogger(Observacional.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BiffException ex) {
                Logger.getLogger(Observacional.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         System.gc();// limpia el gc
        }
     }
}
public static String getExtension(String filename) {
            int index = filename.lastIndexOf('.');
            if (index == -1) {
                  return "";
            } else {
                  return filename.substring(index + 1);
            }
}
public DefaultTableModel openTable(File abre,String token) {
DefaultTableModel v=new DefaultTableModel();
int n=0;
        LinkedList dato = new LinkedList();
        try {
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);
                String aux;
                while ((aux = lee.readLine()) != null) {

                    StringTokenizer st = new StringTokenizer(aux, token ,true);
                    LinkedList au = new LinkedList();
                    while (st.hasMoreTokens()) {// limpia la cadena quitando tokens
                        String aux1 = st.nextToken();
                        if (!(aux1.equals(";"))) {
                           if(n==0){
                        v.addColumn(aux1);
                        }
                           else {
                          au.add(aux1);
                           }
                        }
                    }
                    if(n==1){
                    String ob[]= new String[au.size()];
                    for(int w=0;w<ob.length;w++){
                        ob[w]=(String) au.get(w);
                    }
                    v.addRow(ob);
                    }
                    n=1;
                }
                lee.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
        //ejemplo de archivo  Objeto 7;9;4.5;35;
        return v;
    }
public DefaultTableModel openweka(File abre,String token) {
DefaultTableModel v=new DefaultTableModel();
v.addColumn("Nombre");
int n=0;
int ne=1;
        LinkedList dato = new LinkedList();
        try {
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);
                String aux;
                while ((aux = lee.readLine()) != null) {
                      if(aux.charAt(0)!='@'&&aux.charAt(0)!=' '&&aux.charAt(0)!='%'){ 
                    StringTokenizer st = new StringTokenizer(aux, token ,true);
                    LinkedList au = new LinkedList();
                    au.add(""+ne++);
                    while (st.hasMoreTokens()) {// limpia la cadena quitando tokens
                        String aux1 = st.nextToken();
                        if (!(aux1.equals(token))) {
                            au.add(aux1);
                        }
                    }
                    String ob[]= new String[au.size()];
                    for(int w=0;w<ob.length;w++){
                        if(n==0&&w>0){
                        v.addColumn("Val"+(w));
                        }
                        ob[w]=(String) au.get(w);
                    }
                    n=1;
                    v.addRow(ob);
                    
                      }
                }
                lee.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
        //ejemplo de archivo  Objeto 7;9;4.5;35;
        return v;
    }
    public LinkedList open(File abre) {

        LinkedList dato = new LinkedList();
        try {
            /**
             * llamamos el metodo que permite cargar la ventana
             */
            /**
             * recorremos el archivo, lo leemos para plasmarlo en el area de texto
             */
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);
                String aux;
                while ((aux = lee.readLine()) != null) {

                    StringTokenizer st = new StringTokenizer(aux, ";", true);
                    //lee la cadena y la separa
                    LinkedList au = new LinkedList();
                    while (st.hasMoreTokens()) {// limpia la cadena quitando tokens
                        String aux1 = st.nextToken();
                        if (!(aux1.equals(";"))) {
                            au.add(aux1);
                        }
                    }
                    String name = (String) au.get(0);
                    au.remove(0);

                    dato.add(new Value(name, au));
                }
                lee.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
        //ejemplo de archivo  Objeto 7;9;4.5;35;
        return dato;
    }

    public void save(File guarda, LinkedList m) {
        try {
            String txt = "";
            if (guarda != null) {
                /*guardamos el archivo y le damos el formato directamente,
                 * si queremos que se guarde en formato doc lo definimos como .doc*/
                FileWriter save = new FileWriter(guarda + ".scorp");
                for (int x = 0; x < m.size(); x++) {
                    Value a = (Value) m.get(x);
                    txt += a.getName() + ";";
                    for (int y = 0; y < a.getValue().size(); y++) {
                        txt += a.getValue().get(y) + ";";
                    }
                    txt += "\n";

                }
                save.write(txt);
                save.close();
                JOptionPane.showMessageDialog(null,
                        "El archivo se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void save(File guarda, String txt) {
        try {
            if (guarda != null) {
                /*guardamos el archivo y le damos el formato directamente,
                 * si queremos que se guarde en formato doc lo definimos como .doc*/
                FileWriter save = new FileWriter(guarda + ".scorp");
                save.write(txt);
                save.close();
                JOptionPane.showMessageDialog(null,
                        "El archivo se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

}
