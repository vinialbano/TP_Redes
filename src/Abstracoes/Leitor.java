package Abstracoes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vinicius
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Leitor {

    private static Matcher localizador;
    private static Pattern padrao;
    private final static String inicioLinha = "^\\s?(\\d+)\\s{2}";
    private final static String nomeIP = "^\\S+\\s\\(((?:\\d{1,3}\\.){3}\\d{1,3})\\)\\s*|(\\*)\\s*";
    private final static String ping = "^(\\d+\\.\\d+)\\sms\\s*";
    private static ArrayList<Trace> traceRoutes;
    private static Linha linhaTrace;
    private static Trace trace = null;
    private static Pacote pacote;
    private static String txtIp, txtAst, txtPing, txtUltimoIp, txtSalto;
    
    private Leitor(){
    }

    private static void ler(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha;
        traceRoutes = new ArrayList<Trace>();
      
        while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                linhaTrace = new Linha();
                padrao = Pattern.compile(inicioLinha);
                localizador = padrao.matcher(linha);
                if (localizador.find()) { //Encontrou o começo de linha válido
                    txtSalto = localizador.group(1);
                    if (txtSalto.compareTo("1")==0){
                        if (trace!=null){
                            traceRoutes.add(trace);
                        }
                        trace = new Trace();
                    }
                    
                    linha = linha.replaceFirst(inicioLinha, "");

                    for (int i = 0; i < 3; i++) {
                        padrao = Pattern.compile(nomeIP);
                        localizador = padrao.matcher(linha);

                        if (localizador.find()) { //IP ou Asterisco
                            txtIp = localizador.group(1);
                            txtAst = localizador.group(2);
                            linha = linha.replaceFirst(nomeIP, "");
                            if (txtIp != null) { //Se IP
                                txtUltimoIp = txtIp;
                                padrao = Pattern.compile(ping);
                                localizador = padrao.matcher(linha);
                                localizador.find();
                                txtPing = localizador.group(1);
                                pacote = new Pacote(txtUltimoIp, Double.parseDouble(txtPing));
                                linha = linha.replaceFirst(ping, "");

                            } else { //Se Asterisco
                                pacote = new Pacote(txtAst, 0d);
                            }
                        } else { //Ping na segunda ou terceira iteração
                            padrao = Pattern.compile(ping);
                            localizador = padrao.matcher(linha);
                            localizador.find();
                            txtPing = localizador.group(1);
                            pacote = new Pacote(txtUltimoIp, Double.parseDouble(txtPing));
                            linha = linha.replaceFirst(ping, "");
                        }
                        if (linhaTrace.getP1() == null) {
                            linhaTrace.setP1(pacote);
                        } else if (linhaTrace.getP2() == null) {
                            linhaTrace.setP2(pacote);
                        } else if (linhaTrace.getP3() == null) {
                            linhaTrace.setP3(pacote);
                        }
                    }
                    trace.addLinha(linhaTrace);
                } else { //Não encontrou o começo e linha válido
                    continue;
                }
            } else {
                traceRoutes.add(trace);
                break;
            }
        }
        buffRead.close();
    }
    
    public static ArrayList<Trace> getTraceRoutes(String path) throws IOException{
        ler(path);
        return traceRoutes;
    }
}
