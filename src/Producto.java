public class Producto {

    private float precio;
    private String referencia;
    private int stock;
    private String categoria;
   
   
    public Producto() {
    }

    public Producto(float precio, String referencia, int stock, String categoria) {
        this.precio = precio;
        this.referencia = referencia;
        this.stock = stock;
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }
   
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public String getReferencia() {
        return referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    public int getStock() {
        return stock;
    }
   
    public void setStock(int stock) {
        this.stock = stock;
    }
   
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return 
                "-> Referencia='" + referencia + '\'' +
                "-> Stock=" + stock +
                "-> Categoria='" + categoria + '\'' +
                "-> Precio=" + precio ;
    }


}
