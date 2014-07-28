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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pacote) {
            Pacote p = (Pacote) o;
            if (p.IP.compareTo(this.IP) == 0 && p.ping == this.ping) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.IP);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.ping) ^ (Double.doubleToLongBits(this.ping) >>> 32));
        return hash;
    }

}
