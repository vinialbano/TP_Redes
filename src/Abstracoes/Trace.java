package Abstracoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vinicius
 */
public class Trace {

    private ArrayList<Linha> linhas;
    private HashSet<String> IPs;
    private int totalDescartes;
    private ArrayList<Double> pings;

    public Trace() {
        linhas = new ArrayList<Linha>();
        IPs = new HashSet<String>();
        pings = new ArrayList<Double>();
        totalDescartes = 0;

    }

    public HashSet<String> getIPs() {
        return IPs;
    }

    public void addLinha(Linha linha) {
        linhas.add(linha);
        IPs.add(linha.getP1().getIP());
        if (linha.getP1().getIP().compareTo("*") != 0) {
            pings.add(linha.getP1().getPing());
        }
        IPs.add(linha.getP2().getIP());
        if (linha.getP2().getIP().compareTo("*") != 0) {
            pings.add(linha.getP2().getPing());
        }
        IPs.add(linha.getP3().getIP());
        if (linha.getP3().getIP().compareTo("*") != 0) {
            pings.add(linha.getP3().getPing());
        }
        totalDescartes += linha.getDescartes();

    }

    public int getQtLinhas() {
        return linhas.size();
    }

    public ArrayList<Linha> getLinhas() {
        return linhas;
    }

    public int getTotalSaltos() {
        return linhas.size();
    }

    public int getTotalDescartes() {
        return totalDescartes;
    }

    public ArrayList<Double> getPings() {
        return pings;
    }

}
