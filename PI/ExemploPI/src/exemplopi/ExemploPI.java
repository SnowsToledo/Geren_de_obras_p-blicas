
package exemplopi;

import java.io.*;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExemploPI {

    public static void main(String[] args) {
        
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
                c++;
            }
            l++;

        }

        for(int i = 1; i < l; i++) {
            if(matriz[i][4] == "DF"){                      //    id               nome             situação              cep                         ENDEREÇO               percentual de exec      data prevista    perccentual pago      total pago           
                String sql = "INSERT INTO tabela VALUES ("+matriz[i][0]+", '"+matriz[i][1]+"','"+matriz[i][2]+"', '"+matriz[i][5]+"', '"+matriz[i][3]+" - "+matriz[i][7]+"',"+matriz[i][11]+", '"+matriz[i][12]+"', "+matriz[i][46]+", "+matriz[i][55]+");";
            }
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                System.out.println("Erro na conexão JDBC");
            }
        }

    }
    
}
