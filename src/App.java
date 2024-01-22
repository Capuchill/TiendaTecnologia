import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {

    public static Map<String,ArrayList<Producto>> Categorias = new LinkedHashMap<>();
    private static Scanner leer = new Scanner(System.in);

    public static int menu(){
        int op = 0;
        try {
            System.out.println("\n<<< Gestion MasterTech >>>");
            System.out.println("\n1. Añadir producto");
            System.out.println("2. Añadir categoria");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Eliminar categoria");
            System.out.println("5. Ver productos por categoria");
            System.out.println("6. Ver categorias");
            System.out.println("7. Salir");
            System.out.print("\n-> Opcion: ");
            op = leer.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Elija una opción válida.");
        }

        return op;
    }


    // añadir

    public static Producto crearProducto(Map<String,ArrayList<Producto>> mapa){
        Producto p = new Producto();
        try {
            System.out.println("\n-> Categoría del producto: ");
            System.out.println();
            mostrarCategorias(mapa);
            System.err.print("\nElija una categoria: ");
            int op = leer.nextInt();
            String cat = obtenerCategoria(op, mapa);
            System.out.print("\n-> Referencia: ");
            String ref = leer.next();
            System.out.print("\n-> Stock: ");
            int st = leer.nextInt();
            System.out.print("\n-> Precio: ");
            float precio = leer.nextFloat();
            ref = validarReferencia(mapa, ref);
            if (ref!=null) {
                p = new Producto(precio, ref, st, cat);
                return p;  
            }else{
                p = null;
            }
        } catch (InputMismatchException e) {
            System.out.println("Recuerde digitar bien los datos.");
        }
        return p;  
    }

    public static void añadirProducto(Map<String,ArrayList<Producto>> mapa){
        Producto p = crearProducto(mapa);
        
        if(p!=null){
            for (String key : mapa.keySet()) {
                if(key.equalsIgnoreCase(p.getCategoria())){
                    mapa.get(key).add(p);
                    System.out.println("¡Producto añadido con exito!");
                }
            }
        }
    }

    public static void añadirCategoria(Map<String,ArrayList<Producto>> mapa){
        System.out.print("\n Nombre de la categoria: ");
        String cat = leer.next();
        boolean existe;

        existe = buscarCategoria(cat, mapa);
        while (existe){
            System.out.println("\nLa categoria que ingreso ya existe.");
            boolean sino = siNo();
            if (sino) {
                System.out.print("\n-> Categoria: ");
                cat = leer.next();
                existe = buscarCategoria(cat, mapa);
            } else {
                cat = null;
                break;
            }
        }

        if (cat!=null){
            mapa.put(cat, new ArrayList<>());
        }
    }

    // eiminar

    public static void eliminarProducto(Map<String,ArrayList<Producto>> mapa){
        mostrarCategorias(Categorias);
        int c = leer.nextInt();
        String cat = obtenerCategoria(c, Categorias);
        ArrayList<Producto> productos = mapa.get(cat);
        System.out.println("¿Cual producto de la categoria " + cat + " desea eliminar?");
        int cont = 0;
        for (Producto p : productos) {
            cont++;
            System.out.print(cont + ". ");
            System.out.println(p);
        }
        try {
            System.out.println("-> Opcion: ");
            int elec = leer.nextInt();
            mapa.get(cat).remove(elec-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Elige una de las opciones que se muestran");
        }
    }

    public static void eliminarCategoria(Map<String,ArrayList<Producto>> mapa){
        mostrarCategorias(mapa);
        int c = leer.nextInt();
        String cat = obtenerCategoria(c, mapa);
        mapa.remove(cat);
    }

    // mostrar
    
    public static void mostrarCategorias(Map<String,ArrayList<Producto>> mapa){
        Set<String> keys = mapa.keySet();
        String [] listaKeys = keys.toArray(new String[0]);
        for (int i = 0; i < listaKeys.length; i++) {
            System.out.println(i+1 + ". " + listaKeys[i]);
        }
    }

    public static void mostrarProductos(Map<String,ArrayList<Producto>> mapa){
        ArrayList<Producto> productos;
        int cont = 0;
        for (String cat : mapa.keySet()) {
            System.out.println("<< "+cat.toUpperCase()+" >>");
            productos = mapa.get(cat);
            for (Producto producto : productos) {
                cont++;
                System.out.print(cont + ". ");
                System.out.println(producto);
            }
            cont = 0;
        }
    }

    // realizar opcion elegida 

    public static void metodosOp(int op){
        
            switch (op) {
                case 1:
                    System.out.println("\n<< AÑADE UN PRODUCTO >>");
                    añadirProducto(Categorias);
                    break;
                case 2:
                    System.out.println("\n<< AÑADE UNA CATEGORIA >>");
                    añadirCategoria(Categorias);
                    break;
                case 3:
                    System.out.println("\n<< ELIMINA UN PRODUCTO >>");
                    eliminarProducto(Categorias);
                    break;
                case 4:
                    System.out.println("\n<< ELIMINA UNA CATEGORIA >>");
                    eliminarCategoria(Categorias);
                    break;
                case 5:
                    System.out.println("\n<< INVENTARIO DE PRODUCTOS >>");
                    mostrarProductos(Categorias);
                    break;
                case 6:
                    System.out.println("\n<< LISTA DE CATEGORIAS >>");
                    mostrarCategorias(Categorias);
                    break;
                default:
                    System.err.println("\nEscoja una opcion valida :)");
                    menu();
            }
        
    }


    // validar datos


    public static boolean buscarCategoria(String cat,Map<String,ArrayList<Producto>> mapa){
        Set<String> keys = mapa.keySet();
        for (String key : keys) {
            if (cat.equalsIgnoreCase(key)) {
                return true;
            } 
        }
        return false;
    }

    public static String validarReferencia(Map<String, ArrayList<Producto>> mapa, String r) {    
        List<Producto> todosLosProductos = new ArrayList<>();
    
        for (ArrayList<Producto> productos : mapa.values()) {
            todosLosProductos.addAll(productos);
        }
    
        boolean referenciaRepetida;
    
        do {
            referenciaRepetida = false;
            if (r==null) {
                break;
            }
            for (Producto producto : todosLosProductos) {
                if (producto.getReferencia().equalsIgnoreCase(r)) {
                    System.out.println("Error. La referencia que ha ingresado ya se encuentra registrada");
                    boolean sino = siNo();
                    if (sino) {
                        System.out.print("\n-> Referencia: ");
                        r = leer.nextLine();
                        referenciaRepetida = true;
                        break;
                    } else {
                        return null;
                    }
                }
            }
        } while (referenciaRepetida);
        return r;
    }


    public static boolean siNo(){
        try {
            System.out.print("¿Desea volver a digitar el dato? 1=Si/2=No -> ");
            int elec = leer.nextInt();
            if (elec == 1) {
                return true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Recuerda digitar bien tu eleccion");
        }
        return false;
    }

    public static String obtenerCategoria(int op,Map<String,ArrayList<Producto>> mapa){
        String cat = "";
        Set<String> keys = mapa.keySet();
        String [] listaKeys = keys.toArray(new String[0]);
        cat = listaKeys[op-1];
        return cat;
    }



    public static void main(String[] args) throws Exception {
        
        Categorias.put("Computadores", new ArrayList<>());
        Categorias.put("Celulares", new ArrayList<>());
        Categorias.put("Electrodomesticos", new ArrayList<>());
        Categorias.put("TV", new ArrayList<>());
        Categorias.put("Accesorios", new ArrayList<>());
        Categorias.put("Videojuegos", new ArrayList<>());
        Categorias.put("Audio y video", new ArrayList<>());
        
        int op;
        do {   
            op = menu();
            metodosOp(op);
        } while (op>=1 && op<=6);
               
    }
}
