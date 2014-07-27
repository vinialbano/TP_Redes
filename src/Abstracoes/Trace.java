package Abstracoes;


import java.util.ArrayList;

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
    
    public Trace (){
        linhas = new ArrayList<Linha>();
    }
    
    public void addLinha(Linha linha){
        linhas.add(linha);
    }
    
    public int getQtLinhas(){
        return linhas.size();
    }
    
    public ArrayList<Linha> getLinhas(){
        return linhas;
    }
    
}
