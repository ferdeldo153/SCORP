/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileScorp;

import Variables.Value;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author home
 */
public class ExcelJava {

    private WritableCellFormat fomato_columna;
    private WritableCellFormat fomato_fila;

    public boolean Exportar(File file, LinkedList table, String nameBook) throws WriteException {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            WritableWorkbook w = Workbook.createWorkbook(out);
            w.createSheet(nameBook, 0);
            WritableSheet s = w.getSheet(0);

            createColumna(s, "", 0);//crea la columna nombre
            Value r = (Value) table.get(0);
            createFilas(s, 0, 0, r.getName());
            for (int i = 0; i < r.getValue().size(); i++) {
                createColumna(s, "", i + 1);
                createFilas(s, (i + 1), 0, (String) r.getValue().get(i));
            }
            table.remove(0);

            for (int x = 0; x < table.size(); x++) {
                Value a = (Value) table.get(x);
                createFilas(s, 0, x + 1, a.getName());
                for (int y = 0; y < a.getValue().size(); y++) {
                    createFilas(s, y + 1, x + 1, "" + a.getValue().get(y));
                }
            }
            w.write();
            w.close();
            out.close();
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void createColumna(WritableSheet sheet, String columna, int number_columna) throws WriteException {
        //creamos el tipo de letra
        WritableFont times10pt = new WritableFont(WritableFont.TAHOMA, 14);
        // definimos el formato d ela celda
        WritableCellFormat times = new WritableCellFormat(times10pt);
	// Permite si se ajusta autom�ticamente a las c�lulas
        //times.setWrap(true);
        // crea una negrita con subrayado
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TAHOMA, 11, WritableFont.BOLD, false, UnderlineStyle.SINGLE);
        fomato_columna = new WritableCellFormat(times10ptBoldUnderline);
			// Permite que se ajusta autom�ticamente a las c�lulas
        //fomato_columna.setWrap(true);
        CellView cevell = new CellView();
        cevell.setSize(920);
        cevell.setDimension(70);
        cevell.setFormat(times);
        cevell.setFormat(fomato_columna);
			//cevell.setAutosize(true);
        // escribimos las columnas
        addColumna(sheet, number_columna, 0, columna, fomato_columna);//numero de columna , 0 es la fila
    }

    /**
     * *************************************
     */
    private void createFilas(WritableSheet sheet, int number_columna, int filas, String name_filas) throws WriteException {
        //creamos el tipo de letra
        WritableFont times10pt = new WritableFont(WritableFont.ARIAL, 12);
        times10pt.setColour(Colour.GOLD);
        // definimos el formato d ela celda
        WritableCellFormat times = new WritableCellFormat(times10pt);
        times.setBorder(Border.TOP, BorderLineStyle.MEDIUM, Colour.GOLD);
			// Permite si se ajusta autom�ticamente a las c�lulas
        //times.setWrap(true);
        // crea una negrita con subrayado
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
        fomato_fila = new WritableCellFormat(times10ptBoldUnderline);
			// Permite que se ajusta autom�ticamente a las c�lulas
        //fomato_fila.setWrap(true);
        CellView cevell = new CellView();
        cevell.setDimension(70);
        cevell.setFormat(times);
        cevell.setFormat(fomato_fila);
			//cevell.setAutosize(true);
        // escribimos las columnas
        addFilas(sheet, number_columna, filas, name_filas, fomato_fila);
    }
    private void addColumna(WritableSheet sheet, int column, int row, String s, WritableCellFormat format) throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, format);
        sheet.addCell(label);
    }
    private void addFilas(WritableSheet sheet, int column, int row, String s, WritableCellFormat format) throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, format);
        sheet.addCell(label);
    }
}
