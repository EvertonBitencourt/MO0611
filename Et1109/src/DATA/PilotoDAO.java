
package DATA;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PilotoDAO {
    
    Connection conn;
    PreparedStatement st; //execução dos comandos SQL
    ResultSet rs; //recebe dados do banco para que possamos manipular na aplicação
    
    public String url ="jdbc:mysql://localhost:3306/exemplo_aula"; //nome do banco de dados na máquina do usuário
    public String user ="root"; //nome do usuário
    public String password = "abcd1234"; // senha do MySql
    
    public boolean conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Conexão estabelecida com sucesso");
            return true;
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Erro ao conectar " + ex.getMessage());
            return false;
        }
    }
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }
    
    public int salvar(Piloto p){
        int status;
        
        try{
            st = conn.prepareStatement("INSERT INTO pilotos VALUES (?,?,?,?,?)");
            st.setInt(1, p.getIdPiloto());
            st.setString(2, p.getNome());
            st.setFloat(3, p.getAltura());
            st.setFloat(4, p.getPeso());
            java.sql.Date dataSQL = new java.sql.Date(p.getDataNascimento().getTime());
            st.setDate(5, dataSQL);
            status = st.executeUpdate();
            return status;
        }catch(SQLException ex){
            System.out.println("Erro ao conectar " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    public boolean excluir (int id){
        try{
            st = conn.prepareStatement("DELETE FROM pilotos WHERE idPiloto =?");
            st.setInt(1, id);
            int linhaExcluida = st.executeUpdate();
            return linhaExcluida > 0;
        }catch(SQLException ex){
            System.out.println("Erro ao conectar " + ex.getMessage());
            return false;
        }
    }
    
    public Piloto consultar (String nome){
        try{
            Piloto p = new Piloto();
            st = conn.prepareStatement("SELECT * FROM PILOTOS WHERE NOME = ?");
            st.setString(1, nome);
            rs = st.executeQuery();
            if(rs.next()){
                p.setIdPiloto(rs.getInt("idPiloto"));
                p.setNome(rs.getString("nome"));
                p.setAltura(rs.getFloat("altura"));
                p.setPeso(rs.getFloat("peso"));
                p.setDataNascimento(rs.getDate("dataNascimento"));
                return p;
            }else{
                return null;
            }
        }catch(SQLException ex){
            System.out.println("Erro ao conectar " + ex.getMessage());
            return null;
        }
    }
    
    public int atualizar(Piloto p){
        int status;
        try{
            st = conn.prepareStatement("UPDATE PILOTOS SET NOME = ?, ALTURA = ?, PESO = ?, DATANASCIMENTO = ? WHERE IDPILOTO =?");
            st.setString(1, p.getNome());
            st.setFloat(2, p.getAltura());
            st.setFloat(3, p.getPeso());
            java.sql.Date dataSQL = new java.sql.Date(p.getDataNascimento().getTime());
            st.setDate(4, dataSQL);
            st.setInt(5, p.getIdPiloto());
            status = st.executeUpdate();
            return status;
        }catch(SQLException ex){
            System.out.println("Erro ao conectar " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    public List<Piloto> getPilotos(){
        String sql = "SELECT * FROM pilotos";
        List<Piloto> lista = new ArrayList<>();
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Piloto p = new Piloto();
                p.setIdPiloto(rs.getInt("idPiloto"));
                p.setNome(rs.getString("nome"));
                p.setAltura(rs.getFloat("altura"));
                p.setPeso(rs.getFloat("peso"));
                p.setDataNascimento(rs.getDate("dataNascimento"));
                lista.add(p);
            }
        }catch(SQLException ex){
            System.out.println("Erro ao conectar " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Piloto> getPilotos(String nome){
        String sql = "SELECT * FROM pilotos WHERE nome LIKE ?";
        
        try{
            st = conn.prepareStatement(sql);
            st.setString(1, "%"+ nome + "%");
            rs = st.executeQuery();
            
            List<Piloto> lista = new ArrayList<>();
            while(rs.next()){
                Piloto p = new Piloto();
                p.setIdPiloto(rs.getInt("idPiloto"));
                p.setNome(rs.getString("nome"));
                p.setAltura(rs.getFloat("altura"));
                p.setPeso(rs.getFloat("peso"));
                p.setDataNascimento(rs.getDate("dataNascimento"));
                lista.add(p);
            }
            return lista;
        }catch(SQLException ex){
            System.out.println("Erro ao conectar " + ex.getMessage());
            return new ArrayList<>();
        }
        
    }
    
}
