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
 * Esta clase representa un producto
 */
public class Producto
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    /**
     * Constante para modelar la característica de tipo de producto de PAPELERIA
     */
    final public static int PAPELERIA = 1;

    /**
     * Constante para modelar la característica de tipo de producto de SUPERMERCADO
     */
    final public static int SUPERMERCADO = 2;

    /**
     * Constante para modelar la característica de tipo de producto de DROGUERIA
     */
    final public static int DROGUERIA = 3;

    /**
     * Constante que representa el impuesto asociado al tipo PAPELERIA
     */
    final private static double IVA_PAPEL = 0.16;

    /**
     * Constante que representa el impuesto asociado al tipo FAMACIA
     */
    final private static double IVA_FARMACIA = 0.12;

    /**
     * Constante que representa el impuesto asociado al tipo MERCADO
     */
    final private static double IVA_MERCADO = 0.04;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Nombre del producto
     */
    private String nombre;

    /**
     * Tipo del producto: PAPELERIA, SUPERMERCADO, DROGUERIA
     */
    private int tipo;

    /**
     * Valor unitario del producto
     */
    private double valorUnitario;

    /**
     * Cantidad actual en la bodega del producto
     */
    private int cantidadBodega;

    /**
     * Cantidad mínima que debe haber en bodega para poder hacer un pedido
     */
    private int cantidadMinima;

    /**
     * Total de unidades vendidas del producto
     */
    private int totalProductosVendidos;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un producto con la información básica.
     * @param tipoP Tipo del nuevo producto.tipoP pertenece a {PAPELERIA, SUPERMERCADO, DROGUERIA}.
     * @param nombreP Nombre del nuevo producto. nombreP != null.
     * @param valorUnitarioP Valor unitario del nuevo Producto. valorUnitarioP >= 0.
     * @param cantidadBodegaP Cantidad inicial en la bodega. cantidadBodegaP >= 0.
     * @param cantidadMinimaP Cantidad mínima que debe haber en bodega. cantidadMinimaP >= 0.
     */
    public Producto( int tipoP, String nombreP, double valorUnitarioP, int cantidadBodegaP, int cantidadMinimaP )
    {
        tipo = tipoP;
        nombre = nombreP;
        valorUnitario = valorUnitarioP;
        cantidadBodega = cantidadBodegaP;
        cantidadMinima = cantidadMinimaP;
        totalProductosVendidos = 0;
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el nombre del producto.
     * @return nombre.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el tipo del producto.
     * @return tipo.
     */
    public int darTipo( )
    {
        return tipo;
    }

    /**
     * Retorna el valor unitario del producto.
     * @return valorUnitario.
     */
    public double darValorUnitario( )
    {
        return valorUnitario;
    }

    /**
     * Retorna la cantidad en bodega del producto.
     * @return cantidadBodega.
     */
    public int darCantidadBodega( )
    {
        return cantidadBodega;
    }

    /**
     * Retorna la cantidad mínima del producto.
     * @return cantidadMinima.
     */
    public int darCantidadMinima( )
    {
        return cantidadMinima;
    }

    /**
     * Retorna la cantidad de productos vendidos.
     * @return productosVendidos.
     */
    public int darProductosVendidos( )
    {
        return totalProductosVendidos;
    }

    /**
     * Realiza la venta del producto. <br>
     * <b>post: </b>Disminuye la cantidad de unidades en bodega del producto.
     * @param cantidad La cantidad a vender. cantidad >0.
     * @return la cantidad que realmente fue vendida, según la disponibilidad en la bodega. cantidad >=0.
     */
    public int vender( int cantidad )
    {
        if( cantidad > cantidadBodega )
            cantidad = cantidadBodega;
        totalProductosVendidos = totalProductosVendidos + cantidad;
        cantidadBodega = cantidadBodega - cantidad;
        return cantidad;
    }

    /**
     * Calcula el valor final del producto, incluyendo los impuestos.
     * @return precio de venta del producto al cliente.
     */
    public double calcularPrecioFinal( )
    {
        return valorUnitario * ( 1 + darIVA( ) );
    }

    /**
     * Calcula el porcentaje de impuestos que debe pagar el producto.
     * @return precio de venta del producto al cliente.
     */
    public double darIVA( )
    {
        switch( tipo )
        {
            case PAPELERIA:
                return IVA_PAPEL;
            case SUPERMERCADO:
                return IVA_MERCADO;
            case DROGUERIA:
                return IVA_FARMACIA;
            default:
                return 0;
        }
    }

    /**
     * Realiza el pedido del producto. <br>
     * <b>post: </b>Se aumenta la cantidad de unidades en bodega.
     * @param cantidad La cantidad de unidades del producto pedidas. cantidad >=0.
     */
    public void hacerPedido( int cantidad )
    {
        cantidadBodega += cantidad;
    }

    /**
     * Indica si es necesario hacer un pedido del producto.
     * @return retorna un booleano que indica si la cantidad en la bodega es menor que la mínima, si lo es es necesario hacer pedido.
     */
    public boolean esNecesarioHacerPedido( )
    {
        return cantidadBodega < cantidadMinima;
    }
}