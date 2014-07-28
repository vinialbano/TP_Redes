/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estatisticas;

import Abstracoes.Leitor;
import Abstracoes.Linha;
import Abstracoes.Trace;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 *
 * @author Vinicius
 */
public class Dados {

    private HashSet<Trace> traceRoutes;
    private HashSet<String> IPs;
    private int totalSaltos, totalDescartes;
    private ArrayList<Integer> saltos, descartes;
    private ArrayList<Double> pings;
    private Comparator<Number> comp = new Comparator<Number>() {
        @Override
        public int compare(Number o1, Number o2) {
            if (o1.doubleValue() < o2.doubleValue()) {
                return -1;
            } else if (o1.doubleValue() == o2.doubleValue()) {
                return 0;
            } else {
                return 1;
            }
        }
    };

    public Dados(String path) throws IOException {
        totalSaltos = 0;
        totalDescartes = 0;
        traceRoutes = Leitor.getTraceRoutes(path);
        saltos = new ArrayList<Integer>();
        descartes = new ArrayList<Integer>();
        pings = new ArrayList<Double>();
        IPs = new HashSet<String>();
        for (Trace trace : traceRoutes) {
            IPs.addAll(trace.getIPs());
            totalSaltos += trace.getTotalSaltos();
            saltos.add(trace.getTotalSaltos());
            pings.addAll(trace.getPings());
            totalDescartes += trace.getTotalDescartes();
            descartes.add(trace.getTotalDescartes());
        }
        IPs.remove("*");

    }

    public HashSet<String> getIPs() {
        return IPs;
    }

    public int getNumIPsDistintos() {
        return IPs.size();
    }

    public int getTotalSaltos() {
        return totalSaltos;
    }

    public ArrayList<Integer> getSaltosPorTrace() {
        return saltos;
    }

    public int getMenosSaltos() {
        Collections.sort(saltos, comp);
        return saltos.get(0);
    }

    public int getMaisSaltos() {
        Collections.sort(saltos, comp);
        return saltos.get(saltos.size() - 1);
    }

    public int getTotalDescartes() {
        return totalDescartes;
    }

    public ArrayList<Integer> getDescartesPorTrace() {
        return descartes;
    }

    public ArrayList<Integer> getDescartesPorLinha(int linha) {
        ArrayList<Integer> descartes = new ArrayList<Integer>();
        for (Trace trace : traceRoutes) {
            descartes.add(trace.getLinhas().get(linha).getDescartes());
        }
        return descartes;
    }

    public double getMenorPingTotal() {
        Collections.sort(pings, comp);
        return pings.get(0);
    }

    public double getMaiorPingTotal() {
        Collections.sort(pings, comp);
        return pings.get(pings.size() - 1);
    }

    public ArrayList<Double> getMaiorPingPorTrace() {
        ArrayList<Double> pings = new ArrayList<Double>();
        ArrayList<Double> pings2;
        for (Trace trace : traceRoutes) {
            pings2 = trace.getPings();
            Collections.sort(pings2, comp);
            pings.add(pings2.get(pings2.size() - 1));
        }
        return pings;
    }

    public ArrayList<Double> getMenorPingPorTrace() {
        ArrayList<Double> pings = new ArrayList<Double>();
        ArrayList<Double> pings2;
        for (Trace trace : traceRoutes) {
            pings2 = trace.getPings();
            Collections.sort(pings2, comp);
            pings.add(pings2.get(0));
        }
        return pings;
    }

    public double porcentagemDescartes(int numDescartes, int numPacotes) {
        double porc;
        porc = 100* ((double) numDescartes / numPacotes);
        return porc;
    }

    public double porcentagemDescartesSalto(int numDescartes) {
        double porc;
        porc = (numDescartes / 3) * 100;
        return porc;
    }

    public double mediaAtraso(ArrayList<Double> pings) {
        double media, soma = 0;
        for (Double d : pings) {
            soma += d.doubleValue();
        }
        media = soma / pings.size();
        return media;
    }

    public double mediaSaltos(ArrayList<Integer> saltos) {
        double media, soma = 0;
        for (Integer i : saltos) {
            soma += i.intValue();
        }
        media = soma / saltos.size();
        return media;
    }

    public ArrayList<Linha> getLinha(int numeroLinha) {
        ArrayList<Linha> linhas = new ArrayList<Linha>();
        for (Trace trace : traceRoutes) {
            if (numeroLinha < trace.getLinhas().size()) {
                linhas.add(trace.getLinhas().get(numeroLinha));
            }
        }
        return linhas;
    }
    
    public HashSet<String> getIPs(ArrayList<Linha> linhas){
        IPs = new HashSet<String>();
        for (Linha linha : linhas) {
            IPs.add(linha.getP1().getIP());
            IPs.add(linha.getP2().getIP());
            IPs.add(linha.getP3().getIP());
        }
        IPs.remove("*");
        return IPs;
    }
    
    public HashMap<String,Double> porcentagemIPSalto(int numeroSalto){
        ArrayList<Linha> linhas = getLinha(numeroSalto-1);
        HashSet<String> IPs = getIPs(linhas);
        HashMap<String,Double> IPsSalto = new HashMap<String,Double>();
        for (String ip : IPs){
            int soma=0;
            for (Linha linha: linhas){
                if (linha.getP1().getIP() == ip){
                    soma++;
                }
                if (linha.getP2().getIP() == ip){
                    soma++;
                }
                if (linha.getP3().getIP() == ip){
                    soma++;
                }
            }
            double porc = soma/(3*linhas.size());
            IPsSalto.put(ip, porc);
        }
        return IPsSalto;
    }
    
    public ArrayList<Integer> qtdSaltoIP(String IP){   
        ArrayList<Integer> qtdSalto = new ArrayList<Integer>();
        for (Trace trace: traceRoutes){
             int qtd = 0;
            for (Linha linha: trace.getLinhas()){
                if (IP == linha.getP1().getIP() || IP == linha.getP2().getIP() || IP == linha.getP3().getIP()){
                    qtd++;
                }
            }
            qtdSalto.add(qtd);
        }
        return qtdSalto;
    }
    
    

}
