/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_tienda 
 * Autor: Katalina Marcos - Nov 24, 2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.tienda.test;

import junit.framework.TestCase;
import uniandes.cupi2.tienda.mundo.Producto;
import uniandes.cupi2.tienda.mundo.Tienda;

/**
 * Clase de prueba de la tienda
 */
public class TiendaTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Producto 1 de prueba
     */
    private Producto producto1;
    /**
     * Producto 2 de prueba
     */
    private Producto producto2;
    /**
     * Producto 3 de prueba
     */
    private Producto producto3;
    /**
     * Producto 4 de prueba
     */
    private Producto producto4;
    /**
     * Tienda de prueba
     */
    private Tienda tienda;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Crea una tienda con cuatro producto, dos de ellos de DROGUERIA, uno de PAPELERIA y uno de SUPERMERCADO.
     */
    private void setupEscenario1( )
    {
        producto1 = new Producto( Producto.DROGUERIA, "aspirina", 200, 1000, 400 );
        producto2 = new Producto( Producto.PAPELERIA, "cuaderno", 6000, 80, 20 );
        producto3 = new Producto( Producto.DROGUERIA, "alcohol", 200, 1000, 400 );
        producto4 = new Producto( Producto.SUPERMERCADO, "leche", 1200, 100, 30 );

        tienda = new Tienda( producto1, producto2, producto3, producto4 );
    }

    /**
     * Vende los cuatro productos de la tienda. Las unidades vendidas son: <br>
     * producto 1 = 10 producto 2 = 5 producto 3 = 2 producto 4 = 1
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );

        tienda.venderProducto( producto1.darNombre( ), 10 );
        tienda.venderProducto( producto2.darNombre( ), 5 );
        tienda.venderProducto( producto3.darNombre( ), 2 );
        tienda.venderProducto( producto4.darNombre( ), 1 );
    }

    /**
     * Prueba la correcta creación de la tienda.
     */
    public void testCrearTienda( )
    {
        //Prepara los datos de prueba
        setupEscenario1( );

        //Debe tener 4 productos
        assertNotNull( tienda.darProducto1( ) );
        assertNotNull( tienda.darProducto2( ) );
        assertNotNull( tienda.darProducto3( ) );
        assertNotNull( tienda.darProducto4( ) );

        //Como no se ha vendido nada no hay dinero
        assertEquals( "No hay ganancias", 0.0, tienda.darGananciasTotales( ), 0.0 );
        assertEquals( "No hay ventas", 0.0, tienda.darPromedioVentas( ), 0.0 );

        //El primer producto es de droguería
        assertEquals( Producto.DROGUERIA, tienda.darProducto1( ).darTipo( ) );
    }

    /**
     * Prueba la venta de un producto que si existe en la tienda.
     */
    public void testVenderProductoExistente( )
    {
        //Prepara los datos de prueba
        setupEscenario1( );

        //Se vende una unidad de un producto que existe en la tienda
        int vendidos = tienda.venderProducto( producto1.darNombre( ), 1 );
        assertEquals( 1, vendidos );

        //Se valida lo que implica la venta de esta unidad
        double venta = producto1.calcularPrecioFinal( );
        assertEquals( "Se ha vendido un producto", venta, tienda.darGananciasTotales( ), 0.0 );
        assertEquals( "Se ha vendido un producto", venta, tienda.darPromedioVentas( ), 0.0 );
        assertEquals( producto1.darNombre( ), tienda.darProductoMasVendido( ).darNombre( ) );

        //Se venda una unidad de otros dos de los productos
        vendidos = tienda.venderProducto( producto2.darNombre( ), 1 );
        assertEquals( 1, vendidos );
        vendidos = tienda.venderProducto( producto3.darNombre( ), 1 );
        assertEquals( 1, vendidos );

        //Se valida lo que implica la venta de estas unidades
        venta = producto1.calcularPrecioFinal( ) + producto2.calcularPrecioFinal( ) + producto3.calcularPrecioFinal( );
        assertEquals( "Se han vendido tres producto", venta, tienda.darGananciasTotales( ), 0.0 );
        assertEquals( "Se han vendido tres producto", venta / 3, tienda.darPromedioVentas( ), 0.0 );
        assertEquals( producto4.darNombre( ), tienda.darProductoMenosVendido( ).darNombre( ) );

    }

    /**
     * Prueba la venta de un producto que no existe
     */
    public void testVenderProductoNoExistente( )
    {
        //Prepara los datos de prueba
        setupEscenario1( );

        //No debe darse ninguna venta
        int vendidos = tienda.venderProducto( "inexistente", 1 );
        assertEquals( 0, vendidos );

        //Como no se ha vendido nada no hay dinero
        assertEquals( "No hay ganancias", 0.0, tienda.darGananciasTotales( ), 0.0 );
        assertEquals( "No hay ventas", 0.0, tienda.darPromedioVentas( ), 0.0 );
    }

    /**
     * Prueba la venta de un producto al que le faltan unidades
     */
    public void testProductoFaltante( )
    {
        //Prepara los datos de prueba
        setupEscenario1( );

        //Se venden más unidades de un producto que las que hay en la tienda
        int unidades = producto1.darCantidadBodega( );
        int vendidos = tienda.venderProducto( producto1.darNombre( ), unidades + 1 );
        assertEquals( unidades, vendidos );

        //Se valida lo que implica la venta de esta unidad
        double venta = producto1.calcularPrecioFinal( ) * unidades;
        assertEquals( "Se ha vendido un producto", venta, tienda.darGananciasTotales( ), 0.0 );
        assertEquals( "Se ha vendido un producto", venta / unidades, tienda.darPromedioVentas( ), 0.0 );
        assertEquals( producto1.darNombre( ), tienda.darProductoMasVendido( ).darNombre( ) );
    }

    /**
     * Prueba realizar un pedido de un producto que requiere unidades
     */
    public void testRealizarPedidoValido( )
    {
        //Prepara los datos de prueba
        setupEscenario1( );

        //Vende todas las unidades de un producto
        int unidades = producto1.darCantidadBodega( );
        int vendidos = tienda.venderProducto( producto1.darNombre( ), unidades );
        assertEquals( unidades, vendidos );

        //solicita un pedido para el producto que ya no tiene existencias
        int minimas = producto1.darCantidadMinima( );
        boolean pedido = tienda.realizarPedido( producto1.darNombre( ), minimas );
        if( !pedido )
        {
            fail( "el pedido debió realizarse" );
        }

        //Ahora el producto debe tener el número mínimo unidades en bodega
        assertEquals( minimas, tienda.darProducto1( ).darCantidadMinima( ) );
    }

    /**
     * Prueba realizar un pedido de un producto que no requiere unidades aún
     */
    public void testRealizarPedidoInvalido( )
    {
        //Prepara los datos de prueba
        setupEscenario1( );

        int bodega = tienda.darProducto1( ).darCantidadBodega( );
        int minimas = tienda.darProducto1( ).darCantidadMinima( );

        //Se asegura de que haya el mínimo de unidades
        if( bodega < minimas )
        {
            boolean pedido = tienda.realizarPedido( producto1.darNombre( ), minimas - bodega );
            if( !pedido )
                fail( "Debió hacerse el pedido para alcanzar el número mínimo de unidades" );
        }

        //Solicita un pedido pero no es válido porque ya hay el mínimo de
        // unidades
        boolean pedido = tienda.realizarPedido( producto1.darNombre( ), 1 );
        if( pedido )
            fail( "El pedido no debió realizarse porque ya estaba el mínimo de unidades" );
    }

    /**
     * Prueba el cálculo de todas las estadísticas
     */
    public void testCalculoEstadisticas( )
    {
        //Prepara los datos de prueba
        setupEscenario2( );

        //Del producto 1 se vendieron 10 unidades, del 2 5 unidades, del 3 2
        // unidades y del 4 1 unidad.
        double venta1 = producto1.calcularPrecioFinal( ) * 10;
        double venta2 = producto2.calcularPrecioFinal( ) * 5;
        double venta3 = producto3.calcularPrecioFinal( ) * 2;
        double venta4 = producto4.calcularPrecioFinal( ) * 1;
        double ventaTotal = venta1 + venta2 + venta3 + venta4;

        //Verifica las estadísticas
        assertEquals( "Ventas totales de 2 productos", ventaTotal, tienda.darGananciasTotales( ), 0.0 );
        assertEquals( "Promedio de ventas de 18 productos", ( ventaTotal / 18 ), tienda.darPromedioVentas( ), 0.0 );
        assertEquals( producto1.darNombre( ), tienda.darProductoMasVendido( ).darNombre( ) );
        assertEquals( producto4.darNombre( ), tienda.darProductoMenosVendido( ).darNombre( ) );
    }
}
