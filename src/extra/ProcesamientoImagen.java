package extra;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Luis
 */
public class ProcesamientoImagen {

    //Imagen actual que se ha cargado
    private BufferedImage imageActual;
    private BufferedImage imagenori;
    private BufferedImage imgsegmen;
     JFileChooser selector = new JFileChooser();

    //Método que devuelve una imagen abierta desde archivo
    //Retorna un objeto BufferedImagen
    public BufferedImage abrirImagen() {
        //Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp = null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP", "jpg", "gif", "bmp");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de diálog
        int flag = selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if (flag == JFileChooser.APPROVE_OPTION) {
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada = selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
                imagenori=ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }

        }
        //Asignamos la imagen cargada a la propiedad imageActual
        imageActual = bmp;
        //Retornamos el valor
        return bmp;
    }
    public BufferedImage load() {
        //Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp = null;
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada = selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
                imagenori=ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }

        
        //Asignamos la imagen cargada a la propiedad imageActual
        imageActual = bmp;
        //Retornamos el valor
        return bmp;
    }

    public BufferedImage escalaGrises() {
        //Variables que almacenarán los píxeles
        BufferedImage image = this.imageActual;
        int mediaPixel, colorSRGB;
        Color colorAux;

        //Recorremos la imagen píxel a píxel
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                //Almacenamos el color del píxel
                colorAux = new Color(image.getRGB(i, j));
                //Calculamos la media de los tres canales (rojo, verde, azul)
                mediaPixel = (int) ((colorAux.getRed() + colorAux.getGreen() + colorAux.getBlue()) / 3);
                //Cambiamos a formato sRGB
                colorSRGB = (mediaPixel << 16) | (mediaPixel << 8) | mediaPixel;
                //Asignamos el nuevo valor al BufferedImage
                image.setRGB(i, j, colorSRGB);
            }
        }
        //Retornamos la imagen
        return image;
    }

    public BufferedImage escalaSegmentada() {
        //Variables que almacenarán los píxeles 
        BufferedImage image = new BufferedImage(this.imageActual.getWidth(), this.imageActual.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        for (int i = 0; i < this.imageActual.getWidth(); i++) {
            for (int j = 0; j < this.imageActual.getHeight(); j++) {
                image.setRGB(i, j, this.imageActual.getRGB(i, j));
            }
        }
        //Retornamos la imagen
        this.imgsegmen = image;
        return image;
    }

    public BufferedImage escalaSegmentada(int umbral) {
        //Variables que almacenarán los píxeles 
        BufferedImage image = new BufferedImage(this.imageActual.getWidth(), this.imageActual.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        for (int i = 0; i < this.imageActual.getWidth(); i++) {
            for (int j = 0; j < this.imageActual.getHeight(); j++) {
                Color color = new Color(this.imageActual.getRGB(i, j));
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                //dependiendo del valor del umbral, se van separando los
                // valores RGB a 0 y 255  
                r = (r > umbral) ? 255 : 0;
                g = (g > umbral) ? 255 : 0;
                b = (b > umbral) ? 255 : 0;
                image.setRGB(i, j, new Color(r, g, b).getRGB());
                //Retornamos la imagen
            }
        }
        this.imgsegmen = image;
        return image;
    }

    public BufferedImage getseg() {
        return imgsegmen;
    }

    public BufferedImage getimg() {
        return imageActual;
    }

    public int size() {
        //Variables que almacenarán los píxeles 
        return this.imageActual.getWidth() * this.imageActual.getHeight();
    }

    public void resizeimage(int newW, int newH) {
        Image tmp = this.imageActual.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        this.imageActual = dimg;
    }

    public void resizeseg(int newW, int newH) {
        Image tmp = this.imgsegmen.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        this.imgsegmen = dimg;
    }

    public String binary(BufferedImage img, String name, String w, String b) {
        String cad = name + ";";
        for (int i = 0; i < img.getHeight(); i++) {
            String aux = "";
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int r = color.getRed();
                if (r > 0) {
                    aux += w + ";";// blanco
                } else {
                    aux += b + ";";//negro         
                }
            }
            cad += aux;
        }
        return cad;
    }

    public String rgb(BufferedImage img, String name) {
        String cad = name + ";";
        for (int i = 0; i < img.getHeight(); i++) {
            String aux = "";
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int r = color.getRed();
                aux += r + ";";// blanco      
            }
            cad += aux;
        }
        return cad;
    }

    public String rgb2(BufferedImage img, String name) {
        String cad = "";
        for (int i = 0; i < img.getHeight() - 1; i++) {
            String aux = "";
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int r = color.getRed();
                aux += r + ";";// blanco       
            }
            cad += name + (i + 1) + ";" + aux + "\n";
        }
        return cad;
    }

    public String binary2(BufferedImage img, String name, String w, String b) {
        String cad = "";
        for (int i = 0; i < img.getHeight() - 1; i++) {
            String aux = "";
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int r = color.getRed();
                if (r > 0) {
                    aux += w + ";";// blanco
                } else {
                    aux += b + ";";//negro         
                }
            }
            cad += name + (i + 1) + ";" + aux + "\n";
        }
        return cad;
    }

    public String binary(BufferedImage img) {
        String cad = "";
        for (int i = 0; i < img.getHeight(); i++) {
            String aux = "";
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int r = color.getRed();
                if (r > 0) {
                    aux += "1";// blanco
                } else {
                    aux += "0";//negro         
                }
            }
            cad += aux + "\n";
        }
        return cad;
    }

    public String rgb(BufferedImage img) {
        String cad = "";
        for (int i = 0; i < img.getHeight(); i++) {
            String aux = "";
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int r = color.getRed();
                aux += " " + r;
            }
            cad += aux + "\n";
        }
        return cad;
    }
}
