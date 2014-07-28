/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estatisticas;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Vinicius
 */
public class Main {

    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("#,###.00");

        try { //Relatório 1
            Dados dados = new Dados("C:/Users/Vinicius/Dropbox/Java/Redes/teste.txt");
            System.out.println("Porcentagem de Descartes no Total: " + df.format(dados.porcentagemDescartes(dados.getTotalDescartes(), dados.getTotalSaltos() * 3)) + "%");
            ArrayList<Integer> array = dados.getDescartesPorTrace();
            for (int i=0; i< array.size(); i++) {
                System.out.println(array.get(i));
                System.out.println(dados.getSaltosPorTrace().get(i));
                System.out.println("Porcentagem de Descartes no Trace " + i + ": " + df.format(dados.porcentagemDescartes(array.get(i), dados.getSaltosPorTrace().get(i)*3)) + "%");
            }
        } catch (IOException ex) {
            System.out.println("Não foi possível carregar o arquivo");
        }

    }

}
