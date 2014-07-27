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

    private Matcher localizador;
    private Pattern padrao;
    private String inicioLinha = "^\\s?\\d+\\s{2}";
    private String nomeIP = "^\\S+\\s\\(((?:\\d{1,3}\\.){3}\\d{1,3})\\)|(\\*)";
    private String ping = "\\d+\\.\\d+\\sms";
    private HashSet<Trace> traceRoutes;
    private Linha linha;
    private Pacote pacote;
    private String temp, temp2;

    public Leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha;
        while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                padrao = Pattern.compile(inicioLinha);
                localizador = padrao.matcher(linha);
                if (localizador.find()) { //Encontrou o começo de linha válido
                    linha = linha.replaceFirst(inicioLinha, "");
                    padrao = Pattern.compile(nomeIP);
                    localizador = padrao.matcher(linha);
                    if (localizador.find()) {
                        temp = localizador.group(1);
                        temp2 = localizador.group(2);
                        if (temp != null){
                            pacote=new Pacote(temp,0d);
                        } else {
                            pacote=new Pacote(temp2,0d);
                        }
                        System.out.println(pacote.getIP());
                    }
                } else { //Não encontrou o começo e linha válido
                    continue;
                }
            } else {
                break;
            }
        }
        buffRead.close();
    }

    public static void main(String[] args) throws IOException {
        Leitor leitor = new Leitor("C:/Users/Vinicius/Dropbox/Java/Redes/teste.txt");
    }
}
