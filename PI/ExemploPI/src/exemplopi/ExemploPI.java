/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplopi;

import java.io.*;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 05135096159
 */
public class ExemploPI {

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

        // Carrego o driver do banco de dados.
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/pi";
            String user = "postgres";
            String password = "jedy";
            Connection con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch(ClassNotFoundException cnfe) {
            System.out.println("Não carregou o driver");
        } catch(SQLException sqle) {
            System.out.println("Erro na conexão JDBC");
        }

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
//        for(int i = 0; i < l; i++) {
//            System.out.println(matriz[i][1]+"\t"+matriz[i][5]+"\t"+matriz[i][11]+"\t"+matriz[i][17]);
////            System.out.println(matriz[i][1]);
//        }
        for(int i = 1; i < l; i++) {
            String sql = "INSERT INTO tabela VALUES ('"+matriz[i][1]+"', '"+matriz[i][5]+"', '"+matriz[i][11]+"', '"+matriz[i][17]+"');";
            try {
                stmt.executeUpdate(sql);
//            System.out.println(matriz[i][1]+"\t"+matriz[i][5]+"\t"+matriz[i][11]+"\t"+matriz[i][17]);
//            System.out.println(matriz[i][1]);
            } catch (SQLException ex) {
                System.out.println("Erro na conexão JDBC");
            }
        }

    }
    
}
