/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitura;

import java.io.*;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author 05135096159
 */
public class Leitura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        File arquivo = new File("tabela.csv");
        byte[] arrayDeBytes = new byte[(int)arquivo.length()];
        try {
            FileInputStream fis = new FileInputStream(arquivo);
            fis.read(arrayDeBytes);
        } catch(FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "O arquivo não foi encontrado.");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Erro de leitura do arquivo.");
        }
        String conteudo = new String(arrayDeBytes);
//        System.out.println(conteudo);

        String[][] matriz = new String[29147][70];

        StringTokenizer st = new StringTokenizer(conteudo, "\n");
        int l = 0;
        while(st.hasMoreTokens()) {
            String linha = st.nextToken();
            StringTokenizer st1 = new StringTokenizer(linha, ";");
            int c = 0;
            while(st1.hasMoreTokens()) {
                String coluna = st1.nextToken();
                matriz[l][c] = coluna;
//                System.out.print(matriz[l][c]+"\t");
                c++;
//                System.out.println(c++ +"º coluna: "+coluna+"    ");
            }
            l++;
//            System.out.println("");
        }
        for(int i = 0; i < l; i++) {
            System.out.println(matriz[i][1]+"\t"+matriz[i][5]+"\t"+matriz[i][11]+"\t"+matriz[i][17]);
//            System.out.println(matriz[i][1]);
        }

    }
    
}
