/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileScorp;

import Variables.Value;
import Variables.point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author home
 */
public class Html5 {
       public static String toTable(LinkedList aux,String title) {
       String txt="<h2>"+title+":</h2>\n<table  border=\"1px\">";
            for (int p = 0; p < aux.size(); p++) {
            Value a = (Value) aux.get(p);
            String aw ="<tr><td>"+ a.getName()+"</td>";
            for (int w = 0; w < a.getValue().size(); w++) {
                aw += "<td>" + a.getValue().get(w)+"</td>";
            }
            txt+="</tr>"+aw+"\n";
        }
        return txt+" </table>\n\n";
    }
 public static String toTableSemeColor(LinkedList aux,String title,point m) {
       String txt="<h2>"+title+":</h2>\n<table  border=\"1px\">";
       String n="<tr><td bgcolor=\"#C9C8C8\">Objeto</td>";
       String c="";
            for (int p = 0; p < aux.size(); p++) {
            Value a = (Value) aux.get(p);
            n+= "<td bgcolor=\"#C9C8C8\">" + a.getName()+"</td>";
            String aw ="<tr><td bgcolor=\"#C9C8C8\">"+ a.getName()+"</td>";
            for (int w = 0; w < a.getValue().size(); w++) {
                if(m.getX()==p&&m.getY()==w||m.getY()==p&&m.getX()==w){
                   aw += "<td  bgcolor=\"#D4FFA6\">" + a.getValue().get(w)+"</td>"; 
                }
                else
                aw += "<td>" + a.getValue().get(w)+"</td>";
            }
            c+="</tr>"+aw+"\n";
        }
            txt+=n+"\n"+c;
        return txt+" </table>\n\n";
    }      
 public static String toTableSeme(LinkedList aux,String title) {
       String txt="<h2>"+title+":</h2>\n<table  border=\"1px\">";
       String n="<tr><td bgcolor=\"#C9C8C8\">Objeto</td>";
       String c="";
            for (int p = 0; p < aux.size(); p++) {
            Value a = (Value) aux.get(p);
            n+= "<td bgcolor=\"#C9C8C8\">" + a.getName()+"</td>";
            String aw ="<tr><td bgcolor=\"#C9C8C8\">"+ a.getName()+"</td>";
            for (int w = 0; w < a.getValue().size(); w++) {
                aw += "<td>" + a.getValue().get(w)+"</td>";
            }
            c+="</tr>"+aw+"\n";
        }
            txt+=n+"\n"+c;
        return txt+" </table>\n\n";
    }
 public static String toTableMx(LinkedList aux,String title) {
       String txt="<h2>"+title+":</h2>\n<table  border=\"1px\">";
       String n="<tr><td bgcolor=\"#C9C8C8\">Nombre</td>";
       String c="";
       {
       int l=((Value)aux.get(0)).getValue().size();
       for(int p=0;p<l;p++){
       n+= "<td bgcolor=\"#C9C8C8\">Val " + (p+1)+"</td>";
       }}
            for (int p = 0; p < aux.size(); p++) {
            Value a = (Value) aux.get(p);
            String aw ="<tr><td bgcolor=\"#C9C8C8\">"+ a.getName()+"</td>";
            for (int w = 0; w < a.getValue().size(); w++) {
                aw += "<td>" + a.getValue().get(w)+"</td>";
            }
            c+="</tr>"+aw+"\n";
        }
            txt+=n+"\n"+c;
        return txt+" </table>\n\n";
    }
   public static String toTitle(String title) {
       String txt="<h1>"+title+"</h1>\n";
        return txt;
    }
    public static String toSubtitle(String title) {
      String txt=" <h3>"+title+" </h3>\n";
        return txt;
    }
    public static String toSubtitleColor(String title) {
      String txt=" <h3 color=#489001>"+title+" </h3>\n";
        return txt;
    }
public URL loadHtml( URL url,String r,String f){

try {
    // extract directory from code source url
    String root = (new File(url.toURI())).getParentFile().getPath();
    File doc = new File(root, f);
    // create htm file contents for testing
    FileWriter writer = new FileWriter(doc);
    writer.write(r);
    writer.close();
    // open it in the editor
    return doc.toURI().toURL();
} catch (Exception e) {
    e.printStackTrace();
}
return url;
}
        public void save(File guarda,String txt) {
        try {
            if (guarda != null) {
                /*guardamos el archivo y le damos el formato directamente,
                 * si queremos que se guarde en formato doc lo definimos como .doc*/
                FileWriter save = new FileWriter(guarda + ".html");
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
