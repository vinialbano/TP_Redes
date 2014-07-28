/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estatisticas;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Vinicius
 */
public class Main {

    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("#,##0.00");

        try { //Relatório 1
            Dados dados = new Dados("C:\\Users\\nicol_000\\Documents\\BCC\\Redes\\Trab Fin\\teste.txt");
            System.out.println("Foram descartados " + dados.getTotalDescartes() + " pacotes no total");
            System.out.println("Porcentagem de Descartes no Total: " + df.format(dados.porcentagemDescartes(dados.getTotalDescartes(), dados.getTotalSaltos() * 3)) + "%");
            ArrayList<Integer> array = dados.getDescartesPorTrace();
            for (int i = 0; i < array.size(); i++) {                
            System.out.println("Foram descartados " + array.get(i) + " pacotes no Trace " + i);
                System.out.println("Porcentagem de Descartes no Trace " + i + ": " + df.format(dados.porcentagemDescartes(array.get(i), dados.getSaltosPorTrace().get(i) * 3)) + "%");
            }
            System.out.println("Atraso médio: " + df.format(dados.mediaAtraso(dados.getPings())) + " ms");
            System.out.println("Maior atraso: " + df.format(dados.getMaiorPingTotal()) + " ms");
            System.out.println("Menor atraso: " + df.format(dados.getMenorPingTotal()) + " ms");
            System.out.println("Atraso médio: " + df.format(dados.mediaAtraso(dados.getPings())) + " ms");
            for (int i = 0; i < dados.getNumTraces(); i++) {
                System.out.println("Atraso médio no Trace " + i + ": " + df.format(dados.mediaAtraso(dados.getPingTrace(i))));
            }
            ArrayList<Double> array2 = dados.getMaiorPingPorTrace();
            for (int i = 0; i < array2.size(); i++) {
                System.out.println("Maior atraso no Trace " + i + ": " + df.format(array2.get(i)) + " ms");
            }
            array2 = dados.getMenorPingPorTrace();
            for (int i = 0; i < array2.size(); i++) {
                System.out.println("Menor atraso no Trace " + i + ": " + df.format(array2.get(i)) + " ms");
            }
            System.out.println("Maior quantidade de saltos: " + dados.getMaisSaltos());
            System.out.println("Menor quantidade de saltos: " + dados.getMenosSaltos());
            for (int i = 0; i < 30; i++) {
                HashMap<String, Double> map = dados.porcentagemIPSalto(i);
                for (Entry<String, Double> entry : map.entrySet()) {
                    System.out.println("Porcentagem do IP " + entry.getKey() + " no salto " + i + ": " + df.format(entry.getValue()) + "%");
                }
            }
            for (String IP : dados.getIPs()) {
                array = dados.qtdSaltoIP(IP);
                for (int i = 0; i < array.size(); i++) {
                    if (array.get(i) > 1) {
                        System.out.println("O IP " + IP + " aparece em " + array.get(i) + " saltos diferentes no Trace " + i);
                    }
                }
            }
            for (String IP : dados.getIPs()) {
                int i = dados.qtdSaltoIPgeral(IP);
                if (i > 1) {
                    System.out.println("O IP " + IP + " aparece em " + i + " saltos diferentes no total");
                }
            }
            System.out.println("Ocorreram " + dados.getNumIPsDistintos() + " IPs distintos no teste");
            System.out.println("Ocorreram " + dados.getNumTraces() + " operações de TraceRoute");
        } catch (IOException ex) {
            System.out.println("Não foi possível carregar o arquivo");
        }

    }

}
