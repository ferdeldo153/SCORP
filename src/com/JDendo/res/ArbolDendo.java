package com.JDendo.res;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;

/**
 *
 * @author home
 */
public class ArbolDendo {
    class Nodo
      {
        String info;
        Nodo izq, der;
      }
      Nodo raiz;
      LinkedList ordendendo = new LinkedList();
      public ArbolDendo(String s) {
          raiz= new Nodo ();
          raiz.izq = null;
          raiz.der = null;
          raiz.info=s;
      }
  public void insertar(dendoclass f){
      insertar(f,this.raiz);
  }
  private void insertar (dendoclass f,Nodo arbol)
      {
          if(arbol==null){return;}
          Nodo a,b;
          a=new Nodo();a.der=null;a.izq=null;a.info=f.getA();
          b=new Nodo();b.der=null;b.izq=null;b.info=f.getB();
          if(arbol.info.equals(a.info+","+b.info)){
                    arbol.izq=a;
                    arbol.der=b;
                      }
          else{
              insertar(f,arbol.der);
              insertar(f,arbol.izq);
          }
      }
  public void ordenshow(){
      ordenshow(this.raiz);
      }
  private void ordenshow(Nodo arbol){
      if(arbol!=null){
        ordenshow(arbol.izq);
        ordenshow(arbol.der);
         if (arbol.izq==null&&arbol.der==null)
        System.out.println(arbol.info);
      }
  }
 public void orden(){
      orden(this.raiz);
      }
 private void orden(Nodo arbol){
      if(arbol!=null){
        orden(arbol.izq);
        orden(arbol.der);
         if (!arbol.info.contains(","))
             this.ordendendo.add(arbol.info);
      }
}
public LinkedList getOrden(){
    return this.ordendendo;
}
}
