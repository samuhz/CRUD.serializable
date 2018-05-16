
/**
 * Ofrece metodos para salvar el almacen ya sea ArrayList o Hashmap
 * usando un fichero de productos.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class AlmacenSerializable
{
    private static String nombreFichero = "productos.objetos";
    
    // Cargo en la lista
    
    public static void cargaProductos ( ArrayList <Producto> lista){
           
           try{
            // Creo Flujo de tipo fichero de byte 
            FileInputStream fin= new FileInputStream(nombreFichero);
            // Creo un Flujo de objetos sobre el fichero
            ObjectInputStream oin= new ObjectInputStream(fin);
            
            // Leo objetos hasta llegar a final de fichero
            try {
                Producto aux = (Producto) oin.readObject();
                // Sale cuando se llege al final de fichero End of File EOF
                while ( true ){
                    lista.add(aux);
                    aux = (Producto) oin.readObject();

                }
            }catch  (EOFException ex){} // No hago nada

            fin.close(); // Cierro el fujo de objectos
            oin.close(); // Cierro el flujo de bytes
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        // Por si falla el casting 
       catch ( ClassNotFoundException ex ){
           ex.printStackTrace();
        }
           
        
    }
    
    public static void grabaProductos( ArrayList <Producto> lista){
        try{
            // Creo Flujo de tipo fichero de byte 
            FileOutputStream fos= new FileOutputStream(nombreFichero);
            // Creo un Flujo de objetos sobre el fichero
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            // Escribo los objetos al fichero
            for ( Producto pro: lista){
                oos.writeObject(pro);
            }
            oos.close(); // Cierro el fujo de objectos
            fos.close(); // Cierro el flujo de 
        }catch(IOException ioe){
            System.err.println(" Error en E/S sobre fichero "+nombreFichero+ " "+ioe);
        }    
    }
    
 
    public static void cargaProductos (HashMap <Integer, Producto> lista){
           
           try{
            // Creo Flujo de tipo fichero de byte 
            FileInputStream fin= new FileInputStream(nombreFichero);
            // Creo un Flujo de objetos sobre el fichero
            ObjectInputStream oin= new ObjectInputStream(fin);
            
            // Leo objetos hasta llegar a final de fichero
            try {
                Producto aux = (Producto) oin.readObject();
                // Sale cuando se llege al final de fichero End of File EOF
                while ( true ){
                    lista.put ( aux.getCodigo(), aux);
                    aux = (Producto) oin.readObject();
                }
            }catch  (EOFException ex){} // No hago nada

            fin.close(); // Cierro el fujo de objectos
            oin.close(); // Cierro el flujo de bytes
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        // Por si falla el casting 
       catch ( ClassNotFoundException ex ){
           ex.printStackTrace();
        }
    }
    
    
    public static void grabaProductos( HashMap <Integer, Producto>  lista){
        try{
            // Creo Flujo de tipo fichero de byte 
            FileOutputStream fos= new FileOutputStream(nombreFichero);
            // Creo un Flujo de objetos sobre el fichero
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            // Escribo los objetos al fichero
             for (Map.Entry entradamap: lista.entrySet()){
               Producto pro = (Producto) entradamap.getValue();
               oos.writeObject(pro);
            }
            oos.close(); // Cierro el fujo de objectos
            fos.close(); // Cierro el flujo de 
        }catch(IOException ioe){
            System.err.println(" Error en E/S sobre fichero "+nombreFichero+ " "+ioe);
        }    
    }
 
    
}
