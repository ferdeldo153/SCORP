package com.DrawDendo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.JDendo.res.Value;
import com.JDendo.res.dendoclass;
import com.JDendo.res.point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author home
 */
public class Dendo_disimilarida extends JFrame {
    double esca=0;
     LinkedList ordenoriginal;
     LinkedList fdendo;
     LinkedList cuts;
      int tipo=0;
     double lim=1;
     int punto=0;
     int separacion=20;
    boolean im=false;
    BufferedImage bimage ;
    public Dendo_disimilarida (LinkedList orden,LinkedList fus,LinkedList cut,int tipo,int punt,int xm,int sep) {
            this.punto=punt;
            this.separacion=sep;
            this.ordenoriginal=orden;
            this.tipo=tipo;
            this.cuts=cut;
            this.fdendo=fus;
            int escala=1;
            this.esca=escala;
setTitle("Dendrograma disimilaridad");
            int  xnvo=xm;
            int ynvo=(((ordenoriginal.size()*separacion))/2)+separacion*2;
            this.lim=Math.abs(((xnvo*2)-70)/maximo());
            bimage = new BufferedImage((xnvo*2)+250,(ynvo*2),
                    BufferedImage.TYPE_INT_RGB);
            final JPanel pnl = new JPanel() {
                LinkedList puntos=new LinkedList();
                LinkedList orden=new LinkedList();
                TreeMap map = new TreeMap(); 
                int v=0;
                public void init(){
                    eje();
                    coordenada();             
                    v=1;
                }
                @Override
                public void paintComponent (Graphics g) {
                    //super.paintComponent(g);
                    Graphics2D g2d=(Graphics2D) g;
                    if(v==0){
                        init();
                        paintComponent(bimage.createGraphics());
                        im=true;
                    }
                    if(im==false){
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, getWidth()+250, getHeight());
                    drawdendo(g2d);
                    for(int x=0;x<cuts.size();x++){
                        cut(g2d, (double) cuts.get(x),"Corte:"+cuts.get(x));
                    }
                    if(punto==1){
                        g.setColor(Color.black);
                        addpoint(g);
                    }
                    eje(g2d);
                    drawEscala(g2d);
                    }
                    else{
                      g2d.drawImage(bimage, null, 0, 0);
                    }
                }
                public void drawEscala(Graphics2D g){
                    double max=disimimax();
                    double inter=0;
                   escala(g,0,""+0);
             escala(g,max,""+max);
                inter=max/10;
             for(int x=1;x<10;x++)
                 escala(g,inter*x,""+String.format("%.1f",inter*x));
                }
                public double disimimax(){
                    double max=0;
                    for (int x = 0; x < fdendo.size(); x++) {
                        dendoclass axu = (dendoclass) fdendo.get(x);
                        if(max<axu.getSeme()){
                            max=axu.getSeme();
                        }
                    }
                return max;
                }
                public void cut(Graphics2D g,double s,String tx){
                    Random ran=new Random();
                    Color c=new Color(ran.nextInt(240),ran.nextInt(240),ran.nextInt(240));
                    g.setColor (c);
                    g.drawLine(50+(int) ajustedendo(s),0,50+(int) ajustedendo(s),ynvo*2);
                    g.drawString(tx,(int) ajustedendo(s)-10,40);
                }
                public void escala(Graphics2D g,double s,String tx){
                    Color c=Color.black;
                    g.setColor (c);
                    g.drawLine(50+(int) ajustedendo(s),0,50+(int) ajustedendo(s),8);
                    g.drawString(tx, 40+(int) ajustedendo(s), 20);
                }
                public double ajustedendo(double seme){
                    double aju=seme*lim;
                    return aju;
                }
                public void drawdendo(Graphics2D g){
                    int lim=fdendo.size();
                    for(int x=lim-1;x>=0;x--){
                        dendoclass axu=(dendoclass) fdendo.get(x);
                        switch(tipo){
                            case 3:
                            g.setColor(Color.white);
                            Dawcu(g,axu.getA(),axu.getB(), (int) ajustedendo(axu.getSeme()),0);//<.test
                            g.setColor(Color.black);
                            break;
                            case 1:
                            g.setColor(new Color(new Random().nextInt(240),new Random().nextInt(240),new Random().nextInt(240)));
                            // trasparencia(g, (float) 0.9,c);
                            Dawcu(g,axu.getA(),axu.getB(), (int) ajustedendo(axu.getSeme()),0);//<.test
                            g.setColor(Color.black);
                            break;
                            case 2:
                            g.setColor(Color.white);
                            Dawcu(g,axu.getA(),axu.getB(), (int) ajustedendo(axu.getSeme()),0);//<.test
                             g.setColor(new Color(new Random().nextInt(240),new Random().nextInt(240),new Random().nextInt(240)));
                            break;
                            case 4:
                            g.setColor(new Color(new Random().nextInt(240),new Random().nextInt(240),new Random().nextInt(240)));
                            break;
                                                        
                        }
                        Dawcu(g,axu.getA(),axu.getB(), (int) ajustedendo(axu.getSeme()),1);
                    }
                    g.setColor(Color.BLACK);
                    g.drawLine(50,0,50,ynvo*2);
                }
                public void addpoint(Graphics g){
                    int x=50;
                    for(int w=0;w<this.puntos.size();w++){
                        point a=(point) this.puntos.get(w);
                        g.fillOval((int) (x+ajustedendo(a.getX())),ynvo-a.getY()-4, 5, 8);
                    }
                }
                public void coordenada(){
                    for(int x=0;x<fdendo.size();x++){
                        dendoclass axu=(dendoclass) fdendo.get(x);
                        if(finder(axu.getA())==-1){
                            int y=(int) obtenery(axu.getA());
                            LinkedList a=new  LinkedList();
                            a.add(50+xnvo*-1);
                            a.add(y);
                            a.add(axu.getSeme());
                            this.map.put(""+axu.getA(), this.orden.size());
                            this.orden.add(new Value(""+axu.getA(),a));
                        }
                        if(finder(axu.getB())==-1){
                            int y=(int) obtenery(axu.getB());
                            LinkedList a=new  LinkedList();
                            a.add(10+xnvo*-1);
                            a.add(y);
                            a.add(axu.getSeme());
                            this.map.put(""+axu.getB(), this.orden.size());
                            this.orden.add(new Value(axu.getB(),a));
                        }
                        this.puntos.add(new point((int) axu.getSeme(), (int) obtenery(axu.getA()+","+axu.getB()),axu.getSeme()));
                    }
                }
                public point cobina(String cad){
                    for(int x=0;x<this.orden.size();x++){
                        Value a=(Value) this.orden.get(x);
                        String nx=a.getName();
                        for(int y=x+1;y<this.orden.size();y++){
                            Value b=(Value) this.orden.get(y);
                            if(cad.equals(nx+","+b.getName())||cad.equals(b.getName()+","+nx)){
                                return new point(x,y,0);
                            }
                        }
                    }
                   // System.out.println("No encontro:"+cad);
                    return new point(0,0,0);
                }
                public double obtenery(String cad){
// separar tokens y colocar en una lista, recrear cadena hasta que cadena no se encuentre dipononoble
                    point n=cobina(cad);
                    // reviar separacion de cadena****** urgente
                    int y=0;
                    Value a=(Value) this.orden.get(n.getX());
                    Value b=(Value) this.orden.get(n.getY());
                    LinkedList obj1=a.getValue();
                    LinkedList obj2=b.getValue();
                    int mayor=0;
                    int menor=0;
                    if(Math.abs((int)obj1.get(1))>Math.abs((int)obj2.get(1))){
                        mayor=(int)obj1.get(1);
                        menor=(int)obj2.get(1);
                    }
                    else{
                        mayor=(int)obj2.get(1);
                        menor=(int)obj1.get(1);
                    }
                    if(mayor>0&&menor>=0){
                        y=(mayor+menor)/2;
                    }
                    if(mayor<0&&menor<=0){
                        y=(mayor+menor)/2;
                    }
                    if(mayor<0&&menor>=0){
                        int tmp=(Math.abs(mayor)+menor)/2;
                        y=(mayor)+tmp;
                    }
                    if(mayor>0&&menor<=0){
                        int tmp=(Math.abs(menor)+mayor)/2;
                        y=((mayor)-tmp);
                    }
                    
                    return y;
                }
                public  int finder(String name){
                            int y=-1;
        try{
        y=(int)this.map.get(name);
        }catch (Exception e)
        {
            y=-1;
        }
        return y;
                }
                public  LinkedList find(String name){
                    int x=finder(name);
                    if(x>-1){
                        Value a=(Value) this.orden.get(x);
                            return a.getValue();
                    }
                    return null;
                }
                public void  Dawcu(Graphics2D g,String a,String b,int seme,int t){
                    int y=0;
                    LinkedList obj1= find(a);
                    LinkedList obj2= find(b);
                    int s=(int)obj1.get(1)-(int)obj2.get(1);
                    s=Math.abs(s);
                    if((int)obj1.get(1)>(int)obj2.get(1))
                        y=(int)obj1.get(1);
                    else
                        y=(int)obj2.get(1);
                    if(t==0)
                        addrec(g,50+xnvo*-1,y,s,seme);
                    if(t==1)
                        addrec1(g,50+xnvo*-1,y,s,seme);
                    
                }
                public void addrec(Graphics2D g,double x, double y,double ancho,int largo){
                    g.fillRect(xnvo+1*(int)x,ynvo-1*(int)y,largo, (int) ancho);
                    
                }
                public void addrec1(Graphics2D g,double x, double y,double ancho,int largo){
                    g.drawRect(xnvo+1*(int)x,ynvo-1*(int)y,largo, (int) ancho);
                    
                }
                public void addtext(Graphics2D g,String text,double x, double y,int scala){
                    g.drawString(text, xnvo+scala*(int)x,ynvo-scala*(int)y);
                    
                }
                
                public void eje(Graphics2D g){
                    g.setColor (Color.black);
                    for(int x=0;x<ordenoriginal.size();x++){
                        addtext(g,""+ordenoriginal.get(x),10+xnvo*-1,(ynvo-separacion*(x+1)-20),1);
                    }
                }
                public void eje(){
                    for(int x=0;x<ordenoriginal.size();x++){
                        LinkedList a=new  LinkedList();
                        a.add(100+xnvo*-1);
                        a.add((ynvo-separacion*(x+1)-20));
                        a.add(0);
                        this.puntos.add(new point(0,(ynvo-separacion*(x+1))-20,0));
                        this.map.put(""+ordenoriginal.get(x), this.orden.size());
                        this.orden.add(new Value(""+ordenoriginal.get(x),a));
                    }
                }
            };
            pnl.setPreferredSize(new Dimension(xnvo*2,(ynvo*2)));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize((xnvo*2)+20,(ynvo*2));
            setLocationRelativeTo(null);
            add(new JScrollPane(pnl));
    }

    public Dendo_disimilarida() {
    }
   private double maximo(){
                    double max=0;
                    for (int x = 0; x < fdendo.size(); x++) {
                        dendoclass axu = (dendoclass) fdendo.get(x);
                        if(max<axu.getSeme()){
                            max=axu.getSeme();
                        }
                    }
                return max;
                }
    public  BufferedImage getImage(){
        return this.bimage;
    }
    public void save(){
                  try {
           // pnl.paint(bimage.createGraphics());
          //  im=true;
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.png", "png");
            file.setFileFilter(filtro);
            file.showSaveDialog(this);
            File guarda = file.getSelectedFile();
            if (guarda != null) {
            ImageIO.write(bimage, "png", guarda);
            }
        } catch (IOException ex) {
            Logger.getLogger(Dendo_disimilarida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
