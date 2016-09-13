package com.DrawDendo;


import com.JDendo.Gui.Opciondendo;
import com.JDendo.res.dendoclass;
import com.JDendo.res.ArbolDendo;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author home
 */
public class JDendo {
    
   public JDendo(LinkedList Fusiones, int t){
       LinkedList orden;
       dendoclass a=(dendoclass) Fusiones.get(Fusiones.size()-1);
        ArbolDendo o=new ArbolDendo(a.getA()+","+a.getB());
       for(int x=Fusiones.size()-1;x>=0;x--){
            o.insertar((dendoclass) Fusiones.get(x));
        }
        o.orden();
        orden=o.getOrden();
        o=null;
         new Opciondendo(orden,Fusiones, t).setVisible(true);
         orden=null;
         System.gc();
   
   }
    
}
