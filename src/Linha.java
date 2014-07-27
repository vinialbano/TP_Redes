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

    public Linha(Pacote p1, Pacote p2, Pacote p3) {
        descartes = 0;
        this.p1 = p1;
        if (p1.getIP() == "*") {
            descartes++;
        }
        this.p2 = p2;
        if (p2.getIP() == "*") {
            descartes++;
        }
        this.p3 = p3;
        if (p3.getIP() == "*") {
            descartes++;
        }
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

}
