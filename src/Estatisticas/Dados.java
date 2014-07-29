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

    private ArrayList<Trace> traceRoutes;
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

    public int getNumTraces() {
        return traceRoutes.size();
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

    public ArrayList<Double> getPings() {
        return pings;
    }

    public ArrayList<Double> getPingTrace(int numTrace) {
        ArrayList<Double> pings = new ArrayList<Double>();
        for (Linha linha : traceRoutes.get(numTrace).getLinhas()) {
            if (linha.getP1().getIP().compareTo("*") != 0) {
                pings.add(linha.getP1().getPing());
            }
            if (linha.getP2().getIP().compareTo("*") != 0) {
                pings.add(linha.getP2().getPing());
            }
            if (linha.getP3().getIP().compareTo("*") != 0) {
                pings.add(linha.getP3().getPing());
            }
        }
        return pings;
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
        porc = 100 * ((double) numDescartes / numPacotes);
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

    public HashSet<String> getIPs(ArrayList<Linha> linhas) {
        HashSet<String> ips = new HashSet<String>();
        for (Linha linha : linhas) {
            ips.add(linha.getP1().getIP());
            ips.add(linha.getP2().getIP());
            ips.add(linha.getP3().getIP());
        }
        ips.remove("*");
        return ips;
    }

    public HashMap<String, Double> porcentagemIPSalto(int numeroSalto) {
        ArrayList<Linha> linhas = getLinha(numeroSalto);
        HashSet<String> ips = getIPs(linhas);
        HashMap<String, Double> IPsSalto = new HashMap<String, Double>();
        for (String ip : ips) {
            int soma = 0;
            for (Linha linha : linhas) {
                if (linha.getP1().getIP().compareTo(ip) == 0) {
                    soma++;
                }
                if (linha.getP2().getIP().compareTo(ip) == 0) {
                    soma++;
                }
                if (linha.getP3().getIP().compareTo(ip) == 0) {
                    soma++;
                }
            }
            double porc = 100 * (double) soma / (3 * linhas.size());
            IPsSalto.put(ip, porc);
        }
        return IPsSalto;
    }

    public int qtdSaltoIPgeral(String IP) {
        int qtd = 0;
        HashSet<Integer> linhasPassou = new HashSet<Integer>();
        for (Trace trace : traceRoutes) {
            for (Linha linha : trace.getLinhas()) {
                if (!linhasPassou.contains(linha.getNumero())) {
                    if (IP.compareTo(linha.getP1().getIP()) == 0 || IP.compareTo(linha.getP2().getIP()) == 0 || IP.compareTo(linha.getP3().getIP()) == 0) {
                        qtd++;
                        linhasPassou.add(linha.getNumero());
                    }
                }
            }
        }
        return qtd;
    }
    
    public ArrayList<Linha> getLinhasSalto(int Salto){
        ArrayList<Linha> linhas = new ArrayList<Linha>();
        for (Trace trace : traceRoutes) {
            for (Linha linha : trace.getLinhas()) {
                if (linha.getNumero()==Salto) {
                    linhas.add(linha);
                }
            }
        }
        return linhas;
    }
    
    public double porcPerdaSalto(int Salto){
        int qtd = 0;
        double porc;
        ArrayList<Linha> linhas = getLinhasSalto(Salto);
        for (Linha linha: linhas){
            qtd+=linha.getDescartes();
        }
        porc = 100*(double)qtd/(linhas.size()*3);
        return porc;
    }
    
    public double mediaAtrasoSalto(int Salto){
        double media, soma = 0;
        ArrayList<Linha> linhas = getLinhasSalto(Salto);
        for (Linha linha: linhas) {
            soma += linha.getP1().getPing();
            soma += linha.getP2().getPing();
            soma += linha.getP3().getPing();
        }
        media = soma/(linhas.size()*3);
        return media;
    }
    
    public double variacaoAtrasoSalto(int Salto){
        double var, maior = 0, menor = Double.POSITIVE_INFINITY;
        ArrayList<Linha> linhas = getLinhasSalto(Salto);
        for (Linha linha: linhas) {
            if (linha.getP1().getPing()<menor){
                menor=linha.getP1().getPing();
            }
            if (linha.getP2().getPing()<menor){
                menor=linha.getP2().getPing();
            }
            if (linha.getP3().getPing()<menor){
                menor=linha.getP3().getPing();
            }
            if (linha.getP1().getPing()>maior){
                maior=linha.getP1().getPing();
            }
            if (linha.getP2().getPing()>maior){
                maior=linha.getP2().getPing();
            }
            if (linha.getP3().getPing()>maior){
                maior=linha.getP3().getPing();
            }
        }
        var = maior-menor;
        return var;
    }
    
    

}
