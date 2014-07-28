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
import java.util.Collections;
import java.util.Comparator;
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
    private ArrayList<Double> pings;
    private Comparator<Double> comp = new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
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
            pings.add(pings2.get(pings2.size()-1));
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

}
