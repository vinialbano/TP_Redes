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
    
    double descartes (int totalIp, int qntIp){
        int des;
        des = ((totalIp - qntIp)/totalIp)*100;
        return des;
    };
    
    double descartesSalto (int qntIp){
        int deSalto;
        deSalto = ((3 - qntIp)/3)*100;
        return deSalto;
    };
    
    double atrasoMedio (double ping, int qntPing){
        double media;
        media = ping/qntPing;
        return media;
    };
    
    void maiorMenor (){
        
    };
    
    public static void main(String[] args) {
        try {
            Dados dados = new Dados("C:/Users/Vinicius/Dropbox/Java/Redes/teste.txt");
            for (String ip : dados.getIPs()){
                System.out.println(ip);
            }
            System.out.println("Fim da leitura");
        } catch (IOException ex) {
            System.out.println("Não foi possível carregar o arquivo");
        }
        
    }
    
}
