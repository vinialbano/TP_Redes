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
public class Dados {
    
    private HashSet<Trace> traceRoutes;
    private HashSet<String> IPs;
    
    public Dados(String path) throws IOException{
        traceRoutes = Leitor.getTraceRoutes(path);
        IPs = new HashSet<String>();
        for (Trace trace: traceRoutes){
           IPs.addAll(trace.getIPs());
        }
        IPs.remove("*");
    }
    
    public HashSet<String> getIPs(){     
        return IPs;
    }
    
    public int getNumIPs(){
        return IPs.size();
    }
    
}
