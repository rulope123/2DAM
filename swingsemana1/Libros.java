/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swingsemana1;

/**
 *
 * @author raulp
 */
public class Libros {
    
    private String autor, genero, titulo, editorial;
    private int paginas, agno_publicacion;
    
    public Libros(String autor, String titulo,String genero, String editorial, int paginas, int agno_publicacion){
        
        this.autor=autor;
        this.genero=genero;
        this.titulo=titulo;
        this.editorial=editorial;
        this.paginas=paginas;
        this.agno_publicacion=agno_publicacion;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public void setAutor(String autor){
        this.autor=autor;
    }
    
        public String getGenero(){
        return genero;
    }
    
    public void setGenero(String genero){
        this.genero=genero;
    }
    
        public String getTitulo(){
        return titulo;
    }
    
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }
    
        public String getEditorial(){
        return editorial;
    }
    
    public void setEditorial(String editorial){
        this.editorial=editorial;
    }
    
        public int getPaginas(){
        return paginas;
    }
    
    public void setPaginas(int paginas){
        this.paginas=paginas;
    }
    
        public int getAgno_publicacion(){
        return agno_publicacion;
    }
    
    public void setAgno_publicacion(int agno_publicacion){
        this.agno_publicacion=agno_publicacion;
    }
    
}
