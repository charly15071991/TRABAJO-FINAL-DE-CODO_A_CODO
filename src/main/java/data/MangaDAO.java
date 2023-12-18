
package data;


import static data.conexion.*;
import java.sql.*;
import java.util.*;
import model.Manga;




public class MangaDAO {
    
   
     private static final String SQL_SELECT = "SELECT * FROM tmanga";
     
    static final String SQL_SELECT_BY_ID = "SELECT * FROM tmanga WHERE idmanga = ?";
    
    static final String SQL_INSERT = "INSTERT INTO tmanga(nombre, autor, cantpagina, precio, sinopsis, imagen, imagenbase64) VALUES(?, ?, ?, ?, ?, ?, ?";
    
    static final String SQL_UPDATE = "UPDATE tmanga SET nombre = ?, autor = ?, cantpagina = ?, precio= ?, sinopsis=?, imagen=? WHERE idmanga = ?";
    
    static final String SQL_DELETE = "DELETE FROM tmanga WHERE idmanga = ?";
    
    public static List<Manga> seleccionarPorId(int id) {
        
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Manga mangas = null;
    List<Manga> mangaList = new ArrayList<>();

    try {
        conn = getConexion();
        stmt = conn.prepareStatement(SQL_SELECT);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        while (rs.next()) {
            int idmanga = rs.getInt("idmanga"); // Ajusta el nombre de la columna según tu estructura de base de datos
            String nombre = rs.getString("nombre");
            String autor = rs.getString("autor");
            int cantpagina = rs.getInt("cantPaginas");
            double precio = rs.getDouble("precio");
            String sinopsis = rs.getString("sinopsis");
            int copias = rs.getInt("copias");

            Blob blob = rs.getBlob("imagen");
            byte[] imagenBytes = blob.getBytes(1, (int) blob.length());

            mangas = new Manga(idmanga, nombre, autor, cantpagina, precio, sinopsis, copias, imagenBytes );

            mangaList.add(mangas);
        }
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        try {
            close(rs);
            close(stmt);
            close(conn);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    return mangaList;
}   
    public static int insertar (Manga mangas) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString ( 1,mangas.getNombre());
            stmt.setString(2,mangas.getAutor());
            stmt.setInt(3,mangas.getCantpagina());
            stmt.setDouble(4, mangas.getPrecio());
            stmt.setInt (5, mangas.getCopias());
            
            System.out.println("sisnopsis dao"+ mangas.getSinopsis());
            Blob imagenBlob = conn.createBlob();
            imagenBlob.setBytes(1,mangas.getImagen());
            stmt.setBlob (6,imagenBlob);
            stmt.setString (7, mangas.getSinopsis());
            
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            
        }   
        finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                
            }
            }
        return registros;
        }
    
        public static Manga insertarPorId (int id) {
            
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Manga mangas = null;
        
        try { 
             conn = getConexion();
             stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
             stmt.setInt(1, id);
             rs= stmt.executeQuery();
             
             while (rs.next()) {
                 int idmanga = rs.getInt("idmanga");
                 String nombre = rs.getString ("nombre");
                 String autor = rs.getString("autor");
                 int cantpagina = rs.getInt("cantpagina");
                 String sinopsis = rs.getString("sinopsis");
                 double precio = rs.getDouble("precio");
                 int copias = rs.getInt ("copias");
                 
                 Blob blob = rs.getBlob("imagen");
                 byte[] imagenBytes = blob.getBytes(1,(int) blob.length());
                 
                 mangas = new Manga(idmanga, nombre, autor, cantpagina, precio, sinopsis, copias, imagenBytes );
                 
                 
             }
            
        }    catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                    
                    }
                finally {
                    
                    try {
                    close(rs);
                    close(stmt);
                    close(conn);
                    
                    } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                    
                    }
                    }
          
            
        return mangas;
            
           
    }
    
    
        public static int eliminar( int id) {
            
            Connection conn = null;
            PreparedStatement stmt = null;
            int registros = 0;
            try {
                conn = getConexion();
                
                stmt = conn.prepareStatement(SQL_DELETE);
                stmt.setInt ( 1,id);
                
                registros = stmt.executeUpdate();
            
                        
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                
            }
            finally {
                try {
                    close(stmt);
                    close(conn);
                    
                } catch (SQLException ex) {
                    ex.printStackTrace(System.out);
                }
                
            }
            return registros;
            
            
            
            
            
        }
    
    
    
    
    
        
        
        
    }
    
    
    

    

    
 
                 
                 
                 
                 
                 
      

                  
      
            
            
                  
