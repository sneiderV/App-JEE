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
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.tienda.test;

import junit.framework.*;
import uniandes.cupi2.tienda.mundo.*;

/**
 * Clase de prueba para un producto
 */
public class ProductoTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Producto representado */
    private Producto producto;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    /**
     * Se construye y se inicializa un nuevo producto
     */
    private void setupEscenario1( )
    {
        int tipo = Producto.DROGUERIA;
        String nombre = "Ítem";
        double valorUnitario = 200.0;
        int cantidadBodega = 10;
        int cantidadMinima = 4;

        // Creación del producto para el escenario 1 de pruebas
        producto = new Producto( tipo, nombre, valorUnitario, cantidadBodega, cantidadMinima );
    }

    /**
     * Se construye y se inicializa un nuevo producto, y se realiza una venta sobre éste
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );
        producto.vender( 7 );
    }

    /**
     * Prueba que los datos básicos de un producto se recuperen adecuadamente
     */
    public void testTraerDatos( )
    {
        setupEscenario1( );

        // Verifica la recuperación de la información
        assertEquals( "Ítem", producto.darNombre( ) );
        assertEquals( Producto.DROGUERIA, producto.darTipo( ) );
        assertEquals( 10, producto.darCantidadBodega( ) );
        assertEquals( 4, producto.darCantidadMinima( ) );
        assertEquals( "valor unitario", 200.0, producto.darValorUnitario( ), 0.0 );
        assertEquals( "IVA", 0.12, producto.darIVA( ), 0.0 );
        assertEquals( "precio final", 200.0 * 1.12, producto.calcularPrecioFinal( ), 0.0 );
    }

    /**
     * Prueba para verificar que el proceso de venta este disminuyendo de forma adecuada la cantidad de producto en la bodega
     */
    public void testVenta( )
    {
        setupEscenario1( );
        int venta1 = producto.vender( 7 );
        assertEquals( venta1, 7 );
        assertEquals( producto.darProductosVendidos( ), 7 );
        int venta2 = producto.vender( 4 );
        assertEquals( venta2, 3 );
        assertEquals( producto.darProductosVendidos( ), 10 );
    }

    /**
     * Prueba para verificar que la cantidad de elementos se aumenta adecuadamente cuando se realiza un pedido
     */
    public void testPedido( )
    {
        setupEscenario2( );
        assertEquals( producto.darCantidadBodega( ), 3 );
        assertEquals( producto.esNecesarioHacerPedido( ), true );
        assertEquals( producto.darCantidadBodega( ) < producto.darCantidadMinima( ), true );
        producto.hacerPedido( 20 );
        assertEquals( producto.darCantidadBodega( ), 23 );
    }
}