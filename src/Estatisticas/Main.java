/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Estatisticas;

import java.io.IOException;


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
    
    double[] estatisticaPing (double ping [], int tam){
        double aux;
        double mn[] = new double [3];
        mn[0] = 0;
        mn[1] = 0;
        mn[2] = 0;
        for (int i=0; i<tam; i++){
            aux = ping[i];
            if (aux < mn[0]){ //mn[0] (menor ping)
                mn[0] = aux;
            }
            if (aux > mn[1]){ //mn[1] (maior ping)
                mn[1] = aux;
            }
            mn[3] = mn[3] + ping[i];
        }
        mn[3] = mn[3]/tam; //mn[3] (media ping)
        return mn;
    };
    
    int[] maioresSaltos (int saltos[], int tam){
        int confere = 1, c = 0;
        int maiores[] = new int [tam];
        for (int i = 0; i<tam; i++){
            maiores[c] = saltos[i];
            if (maiores[c] != confere){
                maiores[c] = saltos[i];
            }else{
                maiores[c] = 0;
                c++;
            }            
        }
       return maiores;
    };
    
    int[] estatisticaSalto (int ultimoSalto[], int tam){ //ultimoSalto recebe um vetor só com os ultimosSaltos pra comparar
        int aux, maior = 0, menor = 0, med = 0;
        int estatistica[] = new int [3];
        for (int i=0; i<tam; i++){
            aux = ultimoSalto[i];
            if (aux > maior){
                maior = aux;
            }
            if (aux < menor){
                menor = aux;
            }
            med = med + ultimoSalto[i];
        }
        med = med/tam;
        estatistica[0] = menor; //menor salto
        estatistica[1] = maior; //maior salto
        estatistica[2] = med;   //media saltos
        return estatistica;
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
