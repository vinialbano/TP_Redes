/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estatisticas;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 *
 * @author Vinicius
 */
public class GeradorPDF {

    public static void main(String[] args) throws Exception {
// criação do documento
        Dados dados = new Dados("C:\\Users\\nicol_000\\Documents\\BCC\\Redes\\Trab Fin\\teste.txt");
        DecimalFormat df = new DecimalFormat("#,##0.00");
        Document doc = null;
        OutputStream os = null;

        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            os = new FileOutputStream("C:\\Users\\nicol_000\\Documents\\BCC\\Redes\\Trab Fin\\Relatorio_TP_Vinicius_Nicolas.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();
            //adiciona o texto ao PDF
            Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
            Font f2 = new Font(FontFamily.COURIER, 16, Font.NORMAL);
            Font f3 = new Font(FontFamily.COURIER, 12, Font.BOLD);
            Font f4 = new Font(FontFamily.COURIER, 12, Font.NORMAL);
            
            PdfPTable table = new PdfPTable(1);
            PdfPCell header = new PdfPCell(new Paragraph("Tabela Descartes"));
            header.setColspan(1);
            table.addCell(header);
            table.addCell("Porcentagem: " + df.format(dados.porcentagemDescartes(dados.getTotalDescartes(), dados.getTotalSaltos() * 3)) + "%");
            

            Paragraph p = new Paragraph("UNIVERSIDADE FEDERAL DE ALFENAS", f);
            Paragraph p2;
            Paragraph p3;
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20);
            doc.add(p);

            p = new Paragraph("Redes de Computadores\nProfessor Flávio Barbieri Gonzaga\nTrabalho Prático", f2);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(40);
            doc.add(p);

            p = new Paragraph("Alunos:\nNicolas Salgado Sena - 2012.1.08.029\nVinícius Vieira Albano - 2012.1.08.036", f4);
            p.setSpacingAfter(30);
            doc.add(p);

            p = new Paragraph("Descrição", f3);
            p.setSpacingAfter(10);
            p2 = new Paragraph("Para a realização do nosso trabalho, escolhemos três servidores DNS, localizados nos EUA, Japão e França.\n\n"
                    + "O primeiro é da Level3 e seu IP é 209.244.0.3\n"
                    + "O segundo é da University Of Tokyo e seu IP é 157.82.0.1\n"
                    + "O terceiro é da FDN e seu IP é 80.67.169.40", f4);
            
            p3 = new Paragraph("\n\nDados descartados: " + dados.getTotalDescartes() + "\n\n", f4);
            p.setAlignment(Element.ALIGN_CENTER);
            p2.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);
            doc.add(p2);
            doc.add(p3);
            doc.add(table);

        } finally {
            if (doc != null) {
                //fechamento do documento
                doc.close();
            }
            if (os != null) {

                //fechamento da stream de saída
                os.close();

            }

        }

    }

}
