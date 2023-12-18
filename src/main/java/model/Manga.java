
package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Manga {
   private int idmanga;
   private String nombre;
   private String autor;
   private int cantpagina;
   private double precio;
   private String sinopsis;
   private int copias;
   private byte[] imagen;
   private String imagenbase64;

    public Manga(String nombre, String autor, int cantpagina, double precio, String sinopsis, int copias, byte[] imagen) {
        this.nombre = nombre;
        this.autor = autor;
        this.cantpagina = cantpagina;
        this.precio = precio;
        this.sinopsis = sinopsis;
        this.copias = copias;
        this.imagen = imagen;
    }

    public Manga(int idmanga, String nombre, String autor, int cantpagina, double precio, String sinopsis, int copias, byte[] imagen) {
        this.idmanga = idmanga;
        this.nombre = nombre;
        this.autor = autor;
        this.cantpagina = cantpagina;
        this.precio = precio;
        this.sinopsis = sinopsis;
        this.copias = copias;
        this.imagen = imagen;
    }

   
   
}
