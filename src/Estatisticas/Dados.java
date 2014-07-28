/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Estatisticas;

import Abstracoes.Leitor;
import Abstracoes.Trace;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Vinicius
 */
public class Dados {
    
    private HashSet<Trace> traceRoutes;
    private HashSet<String> IPs;
    private int totalSaltos, totalDescartes;
    private ArrayList<Integer> saltos, descartes;
    
    public Dados(String path) throws IOException{
        totalSaltos=0;
        totalDescartes=0;
        traceRoutes = Leitor.getTraceRoutes(path);
        saltos = new ArrayList<Integer>();
        descartes = new ArrayList<Integer>();
        IPs = new HashSet<String>();
        for (Trace trace: traceRoutes){
           IPs.addAll(trace.getIPs());
           totalSaltos+=trace.getTotalSaltos();
           saltos.add(trace.getTotalSaltos());
           totalDescartes+=trace.getTotalDescartes();
           descartes.add(trace.getTotalDescartes());
        }
        IPs.remove("*");
        
    }
    
    public HashSet<String> getIPs(){     
        return IPs;
    }
    
    public int getNumIPsDistintos(){
        return IPs.size();
    }
    
    public int getTotalSaltos(){
        return totalSaltos;
    }
    
    public ArrayList<Integer> getSaltosPorTrace(){
        return saltos;        
    }
    
    public int getTotalDescartes(){
        return totalDescartes;
    }
    
    public ArrayList<Integer> getDescartesPorTrace(){
        return descartes;
    }
    
    public ArrayList<Integer> getDescartesPorLinha(int linha){
        ArrayList<Integer> descartes = new ArrayList<Integer>();
        for (Trace trace: traceRoutes){
            descartes.add(trace.getLinhas().get(linha).getDescartes());
        }       
        return descartes;
    }
    
    
}
