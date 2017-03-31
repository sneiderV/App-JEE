/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_tienda
 * Autor: Diana Puentes - Jun 20, 2005
 * Autor: Jorge Villalobos - Jul 08, 2005
 * Autor: Pablo Barvo - 23/08/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.tienda.mundo;

/**
 * Esta clase representa una tienda que maneja 4 productos
 */
public class Tienda
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Primer producto que comercializa la tienda
     */
    private Producto p1;

    /**
     * Segundo producto que comercializa la tienda
     */
    private Producto p2;

    /**
     * Tercero producto que comercializa la tienda
     */
    private Producto p3;

    /**
     * Cuarto producto que comercializa la tienda
     */
    private Producto p4;

    /**
     * Caja de la tienda: almacena el dinero recibido por las ventas
     */
    private double dineroEnCaja;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una tienda con 4 productos.
     * @param p1P primer producto de la tienda. p1P != null.
     * @param p2P segundo producto de la tienda.p2P != null.
     * @param p3P tercer producto de la tienda. p3P != null.
     * @param p4P cuarto producto de la tienda. p4P != null.
     */
    public Tienda( Producto p1P, Producto p2P, Producto p3P, Producto p4P )
    {
        p1 = p1P;
        p2 = p2P;
        p3 = p3P;
        p4 = p4P;
        dineroEnCaja = 0;
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Vende una cantidad de un producto de la tienda, dado el nombre del producto. <br>
     * <b>post: </b>se disminuye la cantidad de unidades del producto dado y se actualiza el valor total de dinero de la caja. <br>
     * @param nombreProducto El nombre del producto a vender. nombreProducto != null.
     * @param cantidad La cantidad de unidades de producto a vender. cantidad >= 0.
     * @return retorna la cantidad que fue efectivamente vendida. cantidad vendida >= 0.
     */
    public int venderProducto( String nombreProducto, int cantidad )
    {
        int cantidadVendida = 0;
        if( p1.darNombre( ).equals( nombreProducto ) )
        {
            cantidadVendida = p1.vender( cantidad );
            dineroEnCaja += cantidadVendida * p1.calcularPrecioFinal( );
        }
        else if( p2.darNombre( ).equals( nombreProducto ) )
        {
            cantidadVendida = p2.vender( cantidad );
            dineroEnCaja += cantidadVendida * p2.calcularPrecioFinal( );
        }
        else if( p3.darNombre( ).equals( nombreProducto ) )
        {
            cantidadVendida = p3.vender( cantidad );
            dineroEnCaja += cantidadVendida * p3.calcularPrecioFinal( );
        }
        else if( p4.darNombre( ).equals( nombreProducto ) )
        {
            cantidadVendida = p4.vender( cantidad );
            dineroEnCaja += cantidadVendida * p4.calcularPrecioFinal( );
        }
        return cantidadVendida;
    }

    /**
     * Hace el pedido de un producto, dado su nombre. <br>
     * <b>post: </b> Se aumenta la cantidad de unidades en bodega del producto dado <br>
     * @param nombreProducto El nombre del producto a pedir. nombreProducto != null.
     * @param cantidad La cantidad de unidades de producto a pedir. cantidad >= 0.
     * @return true si se pudo efectuar el pedido, false en caso contrario.
     */
    public boolean realizarPedido( String nombreProducto, int cantidad )
    {
        boolean hizoPedido = false;
        if( p1.darNombre( ).equals( nombreProducto ) && p1.esNecesarioHacerPedido( ) )
        {
            p1.hacerPedido( cantidad );
            hizoPedido = true;
        }
        else if( p2.darNombre( ).equals( nombreProducto ) && p2.esNecesarioHacerPedido( ) )
        {
            p2.hacerPedido( cantidad );
            hizoPedido = true;
        }
        else if( p3.darNombre( ).equals( nombreProducto ) && p3.esNecesarioHacerPedido( ) )
        {
            p3.hacerPedido( cantidad );
            hizoPedido = true;
        }
        else if( p4.darNombre( ).equals( nombreProducto ) && p4.esNecesarioHacerPedido( ) )
        {
            p4.hacerPedido( cantidad );
            hizoPedido = true;
        }
        return hizoPedido;
    }

    /**
     * Retorna los ingresos recibidos por la venta de los productos.
     * @return monto total de las ventas de la tienda.
     */
    public double darGananciasTotales( )
    {
        return dineroEnCaja;
    }

    /**
     * Retorna el producto más vendido.
     * @return producto más vendido de la tienda. producto != null.
     */
    public Producto darProductoMasVendido( )
    {
        int venta1 = p1.darProductosVendidos( );
        int venta2 = p2.darProductosVendidos( );
        int venta3 = p3.darProductosVendidos( );
        int venta4 = p4.darProductosVendidos( );
        if( venta1 >= venta2 && venta1 >= venta3 && venta1 >= venta4 )
            return p1;
        else if( venta2 >= venta1 && venta2 >= venta3 && venta2 >= venta4 )
            return p2;
        else if( venta3 >= venta2 && venta3 >= venta1 && venta3 >= venta4 )
            return p3;
        else
            return p4;
    }

    /**
     * Retorna el producto menos vendido.
     * @return producto menos vendido de la tienda. producto != null.
     */
    public Producto darProductoMenosVendido( )
    {
        int venta1 = p1.darProductosVendidos( );
        int venta2 = p2.darProductosVendidos( );
        int venta3 = p3.darProductosVendidos( );
        int venta4 = p4.darProductosVendidos( );
        if( venta1 <= venta2 && venta1 <= venta3 && venta1 <= venta4 )
            return p1;
        else if( venta2 <= venta1 && venta2 <= venta3 && venta2 <= venta4 )
            return p2;
        else if( venta3 <= venta2 && venta3 <= venta1 && venta3 <= venta4 )
            return p3;
        else
            return p4;
    }

    /**
     * Retorna el promedio de las ventas.
     * @return valor promedio de las ventas en la tienda.
     */
    public double darPromedioVentas( )
    {
        double totalUnidadesVendidas = p1.darProductosVendidos( ) + p2.darProductosVendidos( ) + p3.darProductosVendidos( ) + p4.darProductosVendidos( );
        if( totalUnidadesVendidas == 0 )
            return 0.0;
        else
            return dineroEnCaja / totalUnidadesVendidas;
    }

    /**
     * Retorna el producto 1.
     * @return producto 1 de la tienda.
     */
    public Producto darProducto1( )
    {
        return p1;
    }

    /**
     * Retorna el producto 2.
     * @return producto 2 de la tienda.
     */
    public Producto darProducto2( )
    {
        return p2;
    }

    /**
     * Retorna el producto 3.
     * @return producto 3 de la tienda.
     */
    public Producto darProducto3( )
    {
        return p3;
    }

    /**
     * Retorna el producto 4.
     * @return producto 4 de la tienda.
     */
    public Producto darProducto4( )
    {
        return p4;
    }

    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1 del ejercicio
     * @return resultado
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión 2 del ejercicio.
     * @return resultado.
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}