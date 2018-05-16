import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MiAlmacenHashMap
{
    // USANDO HASHMAP
    static private HashMap<Integer, Producto> mapProducto;
    
    public static void main(String[] args){

        mapProducto = new HashMap<Integer, Producto>();
        
        
        // Carga los datos del fichero.
        AlmacenSerializable.cargaProductos(mapProducto);
        
        int opcion=0;
        do{
            mostrarMenu();
            opcion=leerOpcion();
            switch(opcion){
                case 1: crear();break;
                case 2: consultar();break;
                case 3: borrar();break;
                case 4: modificarPrecio();break;
                case 5: comprar();break;
                case 6: vender();break;
                case 7: listar();break;
                case 8: listarPocoStock();break;
                default: break;
            }            
        }while(opcion!=9);    
        
         // Graba los datos en el fichero
        AlmacenSerializable.grabaProductos(mapProducto);
    }
    
    private static Producto consultar(){        
        Producto p=null;
        System.out.print("Introduzca el código del producto: ");
        Scanner sc=new Scanner(System.in);
        try{
            int id=sc.nextInt();
            p=buscarProducto(id);
            if(p!=null){
               System.out.println(p.toString());
            }else{
                System.out.println("No existe ningún producto con ese código");
            }
        }catch(InputMismatchException e){
            System.out.println("Dato de entrada con formato erróneo");            
        }              
        return p;
    }
    
    private static void borrar(){        
        Producto p=consultar();
        if(p==null){
            return;
        }
        System.out.print("Desea eliminar este producto?(S/N)");
        Scanner sc=new Scanner(System.in);
        String resp=sc.next();
        if(resp.toUpperCase().equals("S")){
             mapProducto.remove(new Integer(p.getCodigo()));
             System.out.println("Producto correctamente eliminado");
        }
        else if(resp.toUpperCase().equals("N")){
            System.out.println("Producto NO eliminado");
        }
        else{            
            System.out.println("Dato de entrada con formato erróneo");            
        }
    }
    
    private static void modificarPrecio(){
        Producto p=consultar();
        if(p==null){
            return;
        }
        System.out.print("Indique el nuevo precio del producto: ");
        Scanner sc=new Scanner(System.in);
        try{
            Float resp=sc.nextFloat();
            if(resp>0){
                p.setPrecio(resp);
                System.out.println("Precio correctamente modificado");
            }
            else{
                System.out.println("Precio tiene que ser mayor que cero");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Dato de entrada con formato erróneo");
        }
    }
        
    
    public boolean modificarProducto (Producto nuevo){
       // Si no esta devuelvo false
       return (mapProducto.containsValue( nuevo));
    }
    
    private static void comprar(){        
        Producto p=consultar();
        if(p==null){
            return;
        }
        System.out.print("Indique las unidades compradas: ");
        Scanner sc=new Scanner(System.in);
        try{
            int resp=sc.nextInt();
            if(resp>0){
                p.setStock(p.getStock()+resp);
                System.out.println("Stock actualizado");
            }
            else{
                System.out.println("Las unidades compradas tienen que ser mayor que cero");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Dato de entrada con formato erróneo");
        }
    }
    
    private static void vender(){       
       Producto p=consultar();
        if(p==null){
            return;
        }
        System.out.print("Indique las unidades vendidas: ");
        Scanner sc=new Scanner(System.in);
        try{
            int resp=sc.nextInt();
            int newStock=p.getStock()-resp;
            if(newStock>=0){
                p.setStock(newStock);
                System.out.println("Stock actualizado");
            }
            else{
                System.out.println("No hay stock para vender tantas unidades");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Dato de entrada con formato erróneo");
        }
    }
    
    private static void listarPocoStock(){
        System.out.println("Listado de productos con stock por debajo del mínimo");
        
       for (Map.Entry entradamap: mapProducto.entrySet()){
            Producto p = (Producto) entradamap.getValue();
            if(p.getStock()<p.getStockMin())
                System.out.println(p.toString());
        }
        
        /** Otra forma con un Iterador 
        Iterator it = mapProducto.keySet().iterator();
        while(it.hasNext()){
            Integer key = (Integer)it.next();
            Producto p=mapProducto.get(key);
            if(p.getStock()<p.getStockMin())
                System.out.println(p.toString());
        }       
       ***/
    }
    
    private static void listar(){ 
        System.out.println("Listado completo de productos");
        String out="";
        out+="Código\t";
        out+="Nombre\t\t";
        out+="Stock_Act ";
        out+="Stock_Min ";
        out+="Precio";
        System.out.println(out);
        
         for (Map.Entry entradamap: mapProducto.entrySet()){
            Producto p = (Producto) entradamap.getValue();
            System.out.println(p.toString());
        }
        
        /** Otra forma con un iterador
        Iterator it = mapProducto.keySet().iterator();
        while(it.hasNext()){
          Integer key = (Integer)it.next();
          System.out.println(mapProducto.get(key));
        }
        ***/
    }
    
    private static Producto buscarProducto(int codigo){
        Producto out=mapProducto.get(new Integer(codigo));
        return out;
    }
    
    private static void crear(){
        Scanner sc=new Scanner(System.in);
        int codigo,stock,stock_min;
        float precio;
        String nombre;
        Producto producto;
        try{
            System.out.print("Código: ");codigo=sc.nextInt();
            if(buscarProducto(codigo)!=null){
                System.out.println("Ya existe un producto con ese código");
                return;
            }
            System.out.print("Nombre: ");nombre=sc.next();
            System.out.print("Stock: ");stock=sc.nextInt();
            if(stock<=0){System.out.println("Stock tiene que ser mayor que cero");return;}       
            System.out.print("Stock mínimo: ");stock_min=sc.nextInt();
            if(stock_min<0){System.out.println("Stock mínimo no puede ser menor que cero");return;}
            System.out.print("Precio: ");precio=sc.nextFloat();
            if(precio<=0){System.out.println("Precio tiene que ser mayor que cero");return;}
            producto=new Producto(codigo,nombre,stock,stock_min,precio);
            mapProducto.put(new Integer(codigo),producto);
        }
        catch(InputMismatchException e){
            System.out.println("Dato de entrada con formato erróneo");
        }
    }
    

    private static void mostrarMenu(){
        System.out.println("MENU");
        System.out.println("1. Nuevo producto ");
        System.out.println("2. Consulta producto ");
        System.out.println("3. Borrar producto ");
        System.out.println("4. Modificar precio ");
        System.out.println("5. Compra de productos ");
        System.out.println("6. Venta de productos ");
        System.out.println("7. Listado completo de productos ");
        System.out.println("8. Listado de productos con stock inferior al mínimo");
        System.out.println("9. Terminar ");
        System.out.print  ("Elige una opción (1-9):");        
    }
    
    private static int leerOpcion(){
        int out=0;        
        Scanner sc=new Scanner(System.in);
        try{
            out=sc.nextInt();
            if(out<1||out>9) System.out.println("Opción no válida");
        }
        catch(InputMismatchException e){
            System.out.println("Formato de opción no válida");
        }
        return out;
    }
}
