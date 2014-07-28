/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estatisticas;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 *
 * @author Vinicius
 */
public class GeradorPDF {

    public static void main(String[] args) throws Exception {
// criação do documento
        DecimalFormat df = new DecimalFormat("#,##0.00");
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
            Paragraph p3;
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
            p.setSpacingAfter(20);
            doc.add(p);

            p = new Paragraph("Servidor DNS Level3 - 209.244.0.3", f2);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20);
            p2 = new Paragraph("Dados Globais", f3);
            p2.setAlignment(Element.ALIGN_LEFT);
            p2.setSpacingAfter(10);
            doc.add(p);
            doc.add(p2);

            Dados dados = new Dados("C:/Users/Vinicius/Dropbox/Java/Redes/saida1.txt");

            p = new Paragraph("Número de IPs distintos: " + dados.getNumIPsDistintos()
                    + "\nNúmero de operações TraceRoute: " + dados.getNumTraces()
                    + "\nNúmero de pacotes enviados: " + dados.getTotalSaltos() * 3
                    + "\nNúmero de pacotes descartados: " + dados.getTotalDescartes()
                    + "\nPorcentagem de pacotes descartados: " + df.format(dados.porcentagemDescartes(dados.getTotalDescartes(), dados.getTotalSaltos() * 3))
                    + "%\nMaior atraso: " + df.format(dados.getMaiorPingTotal()) + " ms"
                    + "\nMenor atraso: " + df.format(dados.getMenorPingTotal()) + " ms"
                    + "\nMédia de atraso: " + df.format(dados.mediaAtraso(dados.getPings())) + " ms"
                    + "\nMaior quantidade de saltos: " + dados.getMaisSaltos()
                    + "\nMenor quantidade de saltos: " + dados.getMenosSaltos(), f4
            );
            p.setSpacingAfter(10);
            doc.add(p);

            p2 = new Paragraph("");
            p2.setFont(f4);
            p2.add("IP\t\tNúmero de saltos");
            for (String IP : dados.getIPs()) {
                int i = dados.qtdSaltoIPgeral(IP);
                if (i > 1) {
                    p2.add("\n(" + IP + ")\t\t" + i);
                }
            }
            p2.setSpacingAfter(20);
            doc.add(p2);
            doc.newPage();

            p = new Paragraph("Servidor DNS University Of Tokyo - 157.82.0.1", f2);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20);
            p2 = new Paragraph("Dados Globais", f3);
            p2.setAlignment(Element.ALIGN_LEFT);
            p2.setSpacingAfter(10);
            doc.add(p);
            doc.add(p2);

            dados = new Dados("C:/Users/Vinicius/Dropbox/Java/Redes/saida2.txt");

            p = new Paragraph("Número de IPs distintos: " + dados.getNumIPsDistintos()
                    + "\nNúmero de operações TraceRoute: " + dados.getNumTraces()
                    + "\nNúmero de pacotes enviados: " + dados.getTotalSaltos() * 3
                    + "\nNúmero de pacotes descartados: " + dados.getTotalDescartes()
                    + "\nPorcentagem de pacotes descartados: " + df.format(dados.porcentagemDescartes(dados.getTotalDescartes(), dados.getTotalSaltos() * 3))
                    + "%\nMaior atraso: " + df.format(dados.getMaiorPingTotal()) + " ms"
                    + "\nMenor atraso: " + df.format(dados.getMenorPingTotal()) + " ms"
                    + "\nMédia de atraso: " + df.format(dados.mediaAtraso(dados.getPings())) + " ms"
                    + "\nMaior quantidade de saltos: " + dados.getMaisSaltos()
                    + "\nMenor quantidade de saltos: " + dados.getMenosSaltos(), f4
            );
            p.setSpacingAfter(10);
            doc.add(p);

            p2 = new Paragraph("");
            p2.setFont(f4);
            p2.add("IP\t\tNúmero de saltos");
            for (String IP : dados.getIPs()) {
                int i = dados.qtdSaltoIPgeral(IP);
                if (i > 1) {
                    p2.add("\n(" + IP + ")\t\t" + i);
                }
            }
            p2.setSpacingAfter(20);
            doc.add(p2);
            doc.newPage();
            
            p = new Paragraph("Servidor DNS FDN - 80.67.169.40", f2);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20);
            p2 = new Paragraph("Dados Globais", f3);
            p2.setAlignment(Element.ALIGN_LEFT);
            p2.setSpacingAfter(10);
            doc.add(p);
            doc.add(p2);

            dados = new Dados("C:/Users/Vinicius/Dropbox/Java/Redes/saida3.txt");

            p = new Paragraph("Número de IPs distintos: " + dados.getNumIPsDistintos()
                    + "\nNúmero de operações TraceRoute: " + dados.getNumTraces()
                    + "\nNúmero de pacotes enviados: " + dados.getTotalSaltos() * 3
                    + "\nNúmero de pacotes descartados: " + dados.getTotalDescartes()
                    + "\nPorcentagem de pacotes descartados: " + df.format(dados.porcentagemDescartes(dados.getTotalDescartes(), dados.getTotalSaltos() * 3))
                    + "%\nMaior atraso: " + df.format(dados.getMaiorPingTotal()) + " ms"
                    + "\nMenor atraso: " + df.format(dados.getMenorPingTotal()) + " ms"
                    + "\nMédia de atraso: " + df.format(dados.mediaAtraso(dados.getPings())) + " ms"
                    + "\nMaior quantidade de saltos: " + dados.getMaisSaltos()
                    + "\nMenor quantidade de saltos: " + dados.getMenosSaltos(), f4
            );
            p.setSpacingAfter(10);
            doc.add(p);

            p2 = new Paragraph("");
            p2.setFont(f4);
            p2.add("IP\t\tNúmero de saltos");
            for (String IP : dados.getIPs()) {
                int i = dados.qtdSaltoIPgeral(IP);
                if (i > 1) {
                    p2.add("\n(" + IP + ")\t\t" + i);
                }
            }
            p2.setSpacingAfter(10);
            doc.add(p2);

            /*p = new Paragraph("Dados Locais", f3);
             p.setAlignment(Element.ALIGN_LEFT);
             p.setSpacingAfter(10);
             doc.add(p);

             p2 = new Paragraph("");
             p2.setFont(f4);
             p2.add("Pacotes descartados\tTrace");
             ArrayList<Integer> array = dados.getDescartesPorTrace();
             for (int i = 0; i < array.size(); i++) {
             p2.add("\n" + array.get(i) + "\t" + Integer.toString(i + 1));
             }
             p2.add("\n\nTrace\t% Pacotes descartados");
             for (int i = 0; i < array.size(); i++) {
             p2.add("\n" + Integer.toString(i + 1) + "\t" + df.format(dados.porcentagemDescartes(array.get(i), dados.getSaltosPorTrace().get(i) * 3)) + "%");
             }
             p2.add("\nTrace\tAtraso médio");
             for (int i = 0; i < dados.getNumTraces(); i++) {
             p2.add("\n" + Integer.toString(i + 1) + "\t" + df.format(dados.mediaAtraso(dados.getPingTrace(i))) + " ms");
             }
             p2.add("\nTrace\tMaior atraso");
             ArrayList<Double> array2 = dados.getMaiorPingPorTrace();
             for (int i = 0; i < array2.size(); i++) {
             p2.add("\n" + i + "\t" + df.format(array2.get(i)) + " ms");
             }
             p2.add("\nTrace\tMenor atraso");
             array2 = dados.getMenorPingPorTrace();
             for (int i = 0; i < array2.size(); i++) {
             p2.add("\n" + i + "\t" + df.format(array2.get(i)) + " ms");
             }
             p2.add("\nIP\tSalto\tPorcentagem");
             for (int i = 0; i < 30; i++) {
             HashMap<String, Double> map = dados.porcentagemIPSalto(i);
             for (Map.Entry<String, Double> entry : map.entrySet()) {
             p2.add("\n" + entry.getKey() + "\t" + i + "\t" + df.format(entry.getValue()) + "%");
             }
             }
             p2.add("\nIP\tSaltos\tTrace");
             for (String IP : dados.getIPs()) {
             array = dados.qtdSaltoIP(IP);
             for (int i = 0; i < array.size(); i++) {
             if (array.get(i) > 1) {
             p2.add("\n" + IP + "\t" + array.get(i) + "\t" + i);
             }
             }
             }
             p2.setAlignment(Element.ALIGN_JUSTIFIED);
             p2.setSpacingAfter(10);
             doc.add(p2);*/
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
