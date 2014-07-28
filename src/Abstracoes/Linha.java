package Abstracoes;

import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vinicius
 */
public class Linha {

    private Pacote p1, p2, p3;
    private int descartes;

    public Linha() {
        descartes = 0;
        p1 = null;
        p2 = null;
        p3 = null;
    }

    /**
     * @return the p1
     */
    public Pacote getP1() {
        return p1;
    }

    /**
     * @return the p2
     */
    public Pacote getP2() {
        return p2;
    }

    /**
     * @return the p3
     */
    public Pacote getP3() {
        return p3;
    }

    /**
     * @return the descartes
     */
    public int getDescartes() {
        return descartes;
    }

    /**
     * @param p1 the p1 to set
     */
    public void setP1(Pacote p1) {
        this.p1 = p1;
        if (p1.getIP().compareTo("*") == 0) {
            descartes++;
        }
    }

    /**
     * @param p2 the p2 to set
     */
    public void setP2(Pacote p2) {
        this.p2 = p2;
        if (p2.getIP().compareTo("*") == 0) {
            descartes++;
        }
    }

    /**
     * @param p3 the p3 to set
     */
    public void setP3(Pacote p3) {
        this.p3 = p3;
        if (p3.getIP().compareTo("*") == 0) {
            descartes++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Linha) {
            Linha l = (Linha) o;
            if (l.p1.equals(this.p1) && l.p2.equals(this.p2) && l.p2.equals(this.p2)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.p1);
        hash = 61 * hash + Objects.hashCode(this.p2);
        hash = 61 * hash + Objects.hashCode(this.p3);
        return hash;
    }

}
