package Abstracoes;

import java.util.ArrayList;
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

    public Trace() {
        linhas = new ArrayList<Linha>();
        IPs = new HashSet<String>();
    }

    public HashSet<String> getIPs() {
        return IPs;
    }

    public void addLinha(Linha linha) {
        linhas.add(linha);
        IPs.add(linha.getP1().getIP());
        IPs.add(linha.getP2().getIP());
        IPs.add(linha.getP3().getIP());

    }

    public int getQtLinhas() {
        return linhas.size();
    }

    public ArrayList<Linha> getLinhas() {
        return linhas;
    }

}
