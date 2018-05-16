
import java.io.Serializable;

public class Producto implements Serializable 
{
    private int codigo;    // Código del producto, se utiliza para buscar
    private String nombre; // Nombre un texto
    private int stock;    // existencia actuales
    private int stock_min; // Número mínimo de existencias recomedadas
    private float precio;  // Precio

    public int getCodigo(){return codigo;}
    public String getNombre(){return nombre;}
    public int getStock(){return stock;}
    public int getStockMin(){return stock_min;}
    public float getPrecio(){return precio;}
    
    public void setPrecio(float precio){this.precio=precio;}
    public void setStock(int stock){this.stock=stock;}
        
    public Producto(int codigo,String nombre,int stock,int stock_min,float precio){
        this.codigo=codigo;
        this.nombre=nombre;
        this.stock=stock;
        this.stock_min=stock_min;
        this.precio=precio;
    }
    
    public String toString(){
        
        
        
        String out=String.format ("%6d  %-15.15s %8d %8d  %7.2f ",codigo,nombre,
                           stock,stock_min,precio);
        
        return out;
    }
           
}
