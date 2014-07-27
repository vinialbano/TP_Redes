package Abstracoes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinicius
 */
public class Pacote {

    private String IP;
    private double ping;

    public Pacote(String IP, double ping) {
        this.IP = IP;
        this.ping = ping;
    }

    /**
     * @return the IP
     */
    public String getIP() {
        return IP;
    }

    /**
     * @return the ping
     */
    public double getPing() {
        return ping;
    }

}
