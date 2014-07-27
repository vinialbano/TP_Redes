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
    
    public static void main(String[] args) {
        try {
            HashSet<Trace> traceRoutes = Leitor.getTraceRoutes("C:/Users/Vinicius/Dropbox/Java/Redes/teste.txt");
            System.out.println("Fim da leitura");
        } catch (IOException ex) {
            System.out.println("Não foi possível carregar o arquivo");
        }
    }
    
}
