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
 * Autor: Pablo Barvo 23/08/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.tienda.interfaz;

import java.awt.*;

import javax.swing.*;

import uniandes.cupi2.tienda.mundo.*;

/**
 * Es la ventana principal del programa de la tienda
 */
public class InterfazTienda extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Representa la Tienda con la que se van a hacer las operaciones */
    private Tienda tienda;
    /** Panel donde se visualiza el estado de los productos de la tienda */
    private PanelProductos panelArriba;
    /** Panel que contiene los botones para realizar acciones en el mundo */
    private PanelOperaciones panelMitad;
    /** Panel para visualizar las estadísticas de la tienda */
    private PanelCalculos panelAbajo;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una nueva interfaz para la tienda. <br>
     * <b>post: </b> inicializa una tienda nueva con 4 productos y organiza la interfaz.
     */
    public InterfazTienda( )
    {
        // Crea los 4 productos de la tienda
        Producto p1 = new Producto( Producto.PAPELERIA, "lápiz", 550.0, 18, 5 );
        Producto p2 = new Producto( Producto.DROGUERIA, "aspirina", 109.5, 25, 8 );
        Producto p3 = new Producto( Producto.PAPELERIA, "borrador", 207.3, 30, 10 );
        Producto p4 = new Producto( Producto.SUPERMERCADO, "pan", 150.0, 15, 20 );
        // Crea la tienda con sus 4 productos
        tienda = new Tienda( p1, p2, p3, p4 );
        // Define la estructura de la visualización
        setTitle( "Latinoamericana: Tienda" );
        panelArriba = new PanelProductos( );
        panelMitad = new PanelOperaciones( this );
        panelAbajo = new PanelCalculos( );
        getContentPane( ).setLayout( new BorderLayout( ) );
        getContentPane( ).add( panelArriba, BorderLayout.NORTH );
        getContentPane( ).add( panelMitad, BorderLayout.CENTER );
        getContentPane( ).add( panelAbajo, BorderLayout.SOUTH );
        setSize( 390, 470 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        refrescarInformacion( );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Este método se encarga de actualizar los datos de la tienda. <br>
     * <b>post: </b> Los campos en la interfaz quedan actualizados con los nuevos valores de la tienda.
     */
    public void refrescarInformacion( )
    {
        // Toma en variables temporales los 4 productos de la tienda, para facilitar los llamados de los metodos de refresco
        Producto producto1 = tienda.darProducto1( );
        Producto producto2 = tienda.darProducto2( );
        Producto producto3 = tienda.darProducto3( );
        Producto producto4 = tienda.darProducto4( );

        // Pide al panelArriba que actualice el nombre de los 4 productos
        panelArriba.refrescarNombres( producto1.darNombre( ), producto2.darNombre( ), producto3.darNombre( ), producto4.darNombre( ) );

        // Pide al panelArriba que actualice la cantidad que hay en bodega de cada uno de los productos
        panelArriba.refrescarCantidades( producto1.darCantidadBodega( ), producto2.darCantidadBodega( ), producto3.darCantidadBodega( ), producto4.darCantidadBodega( ) );

        // Pide al panelArriba que actualice los tipos de los productos
        panelArriba.refrescarIVA( producto1.darIVA( ), producto2.darIVA( ), producto3.darIVA( ), producto4.darIVA( ) );

        // Pide al panelArriba que actualice los precios de los productos
        panelArriba.refrescarPrecios( producto1.darValorUnitario( ), producto2.darValorUnitario( ), producto3.darValorUnitario( ), producto4.darValorUnitario( ) );

        // Pide al panelArriba que actualice la información sobre si se debe realizar pedido
        panelArriba.refrescarPedidos( producto1.esNecesarioHacerPedido( ), producto2.esNecesarioHacerPedido( ), producto3.esNecesarioHacerPedido( ), producto4.esNecesarioHacerPedido( ) );

        // Pide al panelAbajo que actualice las estadísticas de la tienda
        panelAbajo.refrescarEstadisticas( tienda.darGananciasTotales( ), tienda.darProductoMasVendido( ).darNombre( ), tienda.darProductoMenosVendido( ).darNombre( ), tienda.darPromedioVentas( ) );
    }

    /**
     * Devuelve un arreglo con los nombres de los productos de la tienda.
     * @return arreglo con nombres de los productos. arreglo !=null.
     */
    public String[] darNombreProductos( )
    {
        return new String[]{ tienda.darProducto1( ).darNombre( ), tienda.darProducto2( ).darNombre( ), tienda.darProducto3( ).darNombre( ), tienda.darProducto4( ).darNombre( ) };
    }

    //-----------------------------------------------------------------
    // Requerimientos funcionales
    //-----------------------------------------------------------------

    /**
     * Vende el producto con el nombre especificado.
     * @param nombreProducto El nombre del producto. nombreProducto != null.
     * @param cantidad La cantidad de unidades a vender. cantidad >= 0.
     */
    public void venderProducto( String nombreProducto, int cantidad )
    {
        int ventaEfectiva = tienda.venderProducto( nombreProducto, cantidad );
        refrescarInformacion( );
        JOptionPane.showMessageDialog( panelMitad, "Vendidas " + ventaEfectiva + " unidades de: " + nombreProducto );
    }

    /**
     * Incrementa el número de productos en bodega, si es válido hacer el pedido. <br>
     * <b>post: </b> si es válido hacer un pedido, se aumenta la cantidad del producto en bodega.
     * @param nombreProducto Nombre del producto para realizar pedido. nombreProducto != null.
     * @param cantidad Cantidad de unidades que se desean pedir. cantidad >= 0;
     */
    public void realizarPedido( String nombreProducto, int cantidad )
    {
        if( tienda.realizarPedido( nombreProducto, cantidad ) )
        {
            JOptionPane.showMessageDialog( panelMitad, "Pedidas " + cantidad + " unidades de: " + nombreProducto );
            refrescarInformacion( );
        }
        else
            JOptionPane.showMessageDialog( panelMitad, "No fue posible hacer el pedido de: " + nombreProducto );
    }

    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Punto de extensión 1.
     */
    public void reqFuncOpcion1( )
    {
        String respuesta = tienda.metodo1( );
        refrescarInformacion( );
        JOptionPane.showMessageDialog( panelMitad, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Punto de extensión 2.
     */
    public void reqFuncOpcion2( )
    {
        String respuesta = tienda.metodo2( );
        refrescarInformacion( );
        JOptionPane.showMessageDialog( panelMitad, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    //-----------------------------------------------------------------
    // Ejecución
    //-----------------------------------------------------------------

    /**
     * Crea y ejecuta una instancia de la ventana principal.
     * @param args argumentos del programa, pasados en la línea de comandos. No se require ninguno.
     */
    public static void main( String[] args )
    {

        // Crea la ventana de la interfaz
        InterfazTienda ventana = new InterfazTienda( );
        ventana.setVisible( true );
    }
}