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
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Vinicius
 */
public class GeradorPDF {

    public static void main(String[] args) throws Exception {
// criação do documento
        Document doc = null;
        OutputStream os = null;

        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            os = new FileOutputStream("C:/Users/Vinicius/Dropbox/Java/Redes/Relatorio_TP_Vinicius_Nicolas.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();
            //adiciona o texto ao PDF
            Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
            Font f2 = new Font(FontFamily.COURIER, 14, Font.NORMAL);
            Font f3 = new Font(FontFamily.COURIER, 12, Font.BOLD);
            Font f4 = new Font(FontFamily.COURIER, 12, Font.NORMAL);
             Font f5 = new Font(FontFamily.COURIER, 14, Font.BOLD);

            Paragraph p = new Paragraph("UNIVERSIDADE FEDERAL DE ALFENAS", f);
            Paragraph p2;
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(40);
            doc.add(p);

            p = new Paragraph("Redes de Computadores\nProfessor Flávio Barbieri Gonzaga\nTrabalho Prático", f2);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(30);
            doc.add(p);

            p = new Paragraph("Nicolas Salgado Sena - 2012.1.08.029\nVinícius Vieira Albano - 2012.1.08.036", f4);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(30);
            doc.add(p);

            p = new Paragraph("Descrição", f3);
            p.setSpacingAfter(10);
            p2 = new Paragraph("Para a realização do nosso trabalho, escolhemos três servidores DNS, localizados nos EUA, Japão e França.\n\n"
                    + "O primeiro é da Level3 e seu IP é 209.244.0.3\n"
                    + "O segundo é da University Of Tokyo e seu IP é 157.82.0.1\n"
                    + "O terceiro é da FDN e seu IP é 80.67.169.40", f4);
            p2.setSpacingAfter(20);

            p.setAlignment(Element.ALIGN_CENTER);
            p2.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);
            doc.add(p2);

            p = new Paragraph("Metodologia", f3);
            p.setSpacingAfter(10);
            p2 = new Paragraph("Para obtermos os dados necessários, deixamos rodando um script no servidor Turing por 24 horas"
                    + " consecutivas. Após isso, utilizamos de expressões regulares para classificar e manipular os dados obtidos. Basicamente"
                    + " foram utilizadas três expressões regulares:\n•^\\s?\\d+\\s{2}\nPara detectar o início das linhas válidas do TraceRoute"
                    + " e o número de saltos.\n•^\\S+\\s\\(((?:\\d{1,3}\\.){3}\\d{1,3})\\)\\s*|(\\*)\\s*\nPara detectar o endereço DNS do servidor, assim"
                    + " como o endereço IP, ou os asteriscos que indicam perda de pacote.\n•^(\\d+\\.\\d+)\\sms\nPara detectar os atrasos dos pacotes.\n"
                    + "Para a geração das estatísticas e recuperação dos dados, foi utilizada a linguagem de programação JAVA.", f4);
            p2.setSpacingAfter(20);
            p.setAlignment(Element.ALIGN_CENTER);
            p2.setAlignment(Element.ALIGN_JUSTIFIED);
            doc.add(p);
            doc.add(p2);
            
            doc.newPage();
            
            p = new Paragraph("Resultados", f5);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);

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
