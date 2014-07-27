/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Estatisticas;

import Abstracoes.Leitor;
import Abstracoes.Trace;
import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author Vinicius
 */
public class Main {
    
    int descartes (int totalIp, int qntIp){
        int des;
        des = (totalIp - qntIp)/totalIp;
        return des;
    };
    
    int descartesSalto (int qntIp){
        int deSalto;
        deSalto = (3 - qntIp)/3;
        return deSalto;
    };
    
    int atrasoMedio (double ping, int qntPing){
        double media;
        media = ping/qntPing;
        return media;
    };
    
    void maiorMenor (){
        
    };
    
    public static void main(String[] args) {
        try {
            HashSet<Trace> traceRoutes;
            traceRoutes = Leitor.getTraceRoutes("C:\\Users\\nicol_000\\Documents\\BCC\\Redes\\Trab Fin\teste.txt");
            System.out.println("Fim da leitura");
        } catch (IOException ex) {
            System.out.println("Não foi possível carregar o arquivo");
        }
        
    }
    
}
