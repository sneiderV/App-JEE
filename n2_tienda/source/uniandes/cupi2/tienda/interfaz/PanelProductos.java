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

package uniandes.cupi2.tienda.interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel en el que se muestra el estado de los productos de la tienda
 */
public class PanelProductos extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos de Interfaz
    //-----------------------------------------------------------------

    /** Etiqueta de producto para la matriz de productos */
    private JLabel labProducto;
    /** Etiqueta de cantidad para la matriz de productos */
    private JLabel labCantidad;
    /** Etiqueta de IVA para la matriz de productos */
    private JLabel labIVA;
    /** Etiqueta de precio para la matriz de productos */
    private JLabel labPrecio;
    /** Etiqueta de pedido para la matriz de productos */
    private JLabel labPedido;
    /** Campo de texto para el nombre del producto1 */
    private JTextField txtProducto1;
    /** Campo de texto para el nombre del producto2 */
    private JTextField txtProducto2;
    /** Campo de texto para el nombre del producto3 */
    private JTextField txtProducto3;
    /** Campo de texto para el nombre del producto4 */
    private JTextField txtProducto4;
    /** Campo de texto para la cantidad de producto1 en bodega */
    private JTextField txtCantidad1;
    /** Campo de texto para la cantidad de producto2 en bodega */
    private JTextField txtCantidad2;
    /** Campo de texto para la cantidad de producto3 en bodega */
    private JTextField txtCantidad3;
    /** Campo de texto para la cantidad de producto4 en bodega */
    private JTextField txtCantidad4;
    /** Campo de texto para el IVA del producto1 */
    private JTextField txtIVA1;
    /** Campo de texto para el IVA del producto2 */
    private JTextField txtIVA2;
    /** Campo de texto para el IVA del producto3 */
    private JTextField txtIVA3;
    /** Campo de texto para el IVA del producto4 */
    private JTextField txtIVA4;
    /** Campo de texto para el precio del producto1 */
    private JTextField txtPrecio1;
    /** Campo de texto para el precio del producto2 */
    private JTextField txtPrecio2;
    /** Campo de texto para el precio del producto3 */
    private JTextField txtPrecio3;
    /** Campo de texto para el precio del producto4 */
    private JTextField txtPrecio4;
    /** Campo de texto que indica si se debe hacer pedido del producto1 */
    private JTextField txtPedir1;
    /** Campo de texto que indica si se debe hacer pedido del producto2 */
    private JTextField txtPedir2;
    /** Campo de texto que indica si se debe hacer pedido del producto3 */
    private JTextField txtPedir3;
    /** Campo de texto que indica si se debe hacer pedido del producto4 */
    private JTextField txtPedir4;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    /**
     * Crea e inicializa el panel para la visualización de los productos.
     */
    public PanelProductos( )
    {
        GridBagLayout gridbag = new GridBagLayout( );
        setLayout( gridbag );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Productos" ) ) );

        //Creación de los elementos de la interfaz
        labProducto = new JLabel( "Producto " );
        labCantidad = new JLabel( "Cantidad " );
        labIVA = new JLabel( "IVA " );
        labPrecio = new JLabel( "Precio " );
        labPedido = new JLabel( "Pedido " );
        txtProducto1 = new JTextField( 8 );
        txtProducto2 = new JTextField( 8 );
        txtProducto3 = new JTextField( 8 );
        txtProducto4 = new JTextField( 8 );
        txtCantidad1 = new JTextField( 3 );
        txtCantidad2 = new JTextField( 3 );
        txtCantidad3 = new JTextField( 3 );
        txtCantidad4 = new JTextField( 3 );
        txtIVA1 = new JTextField( 4 );
        txtIVA2 = new JTextField( 4 );
        txtIVA3 = new JTextField( 4 );
        txtIVA4 = new JTextField( 4 );
        txtPrecio1 = new JTextField( 4 );
        txtPrecio2 = new JTextField( 4 );
        txtPrecio3 = new JTextField( 4 );
        txtPrecio4 = new JTextField( 4 );
        txtPedir1 = new JTextField( 4 );
        txtPedir2 = new JTextField( 4 );
        txtPedir3 = new JTextField( 4 );
        txtPedir4 = new JTextField( 4 );

        //Inicialización de los elementos de la interfaz

        //Ubica las etiquetas de titulo para la matriz
        GridBagConstraints gbc;
        gbc = new GridBagConstraints( 1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( labProducto, gbc );
        gbc = new GridBagConstraints( 2, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( labCantidad, gbc );
        gbc = new GridBagConstraints( 3, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( labIVA, gbc );
        gbc = new GridBagConstraints( 4, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( labPrecio, gbc );
        gbc = new GridBagConstraints( 5, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( labPedido, gbc );

        //Ubica los nombres de los productos en sus posiciones
        gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtProducto1, gbc );
        txtProducto1.setEnabled( false );
        txtProducto1.setBackground( Color.BLACK );
        txtProducto1.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 1, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtProducto2, gbc );
        txtProducto2.setEnabled( false );
        txtProducto2.setBackground( Color.BLACK );
        txtProducto2.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 1, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtProducto3, gbc );
        txtProducto3.setEnabled( false );
        txtProducto3.setBackground( Color.BLACK );
        txtProducto3.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 1, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtProducto4, gbc );
        txtProducto4.setEnabled( false );
        txtProducto4.setBackground( Color.BLACK );
        txtProducto4.setDisabledTextColor( Color.WHITE );

        //Ubica las cantidades de los productos en sus posiciones
        gbc = new GridBagConstraints( 2, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtCantidad1, gbc );
        txtCantidad1.setEnabled( false );
        txtCantidad1.setBackground( Color.BLACK );
        txtCantidad1.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 2, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtCantidad2, gbc );
        txtCantidad2.setEnabled( false );
        txtCantidad2.setBackground( Color.BLACK );
        txtCantidad2.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 2, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtCantidad3, gbc );
        txtCantidad3.setEnabled( false );
        txtCantidad3.setBackground( Color.BLACK );
        txtCantidad3.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 2, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtCantidad4, gbc );
        txtCantidad4.setEnabled( false );
        txtCantidad4.setBackground( Color.BLACK );
        txtCantidad4.setDisabledTextColor( Color.WHITE );

        //Ubica los valores de IVA de los productos en sus posiciones
        gbc = new GridBagConstraints( 3, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtIVA1, gbc );
        txtIVA1.setEnabled( false );
        txtIVA1.setBackground( Color.BLACK );
        txtIVA1.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 3, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtIVA2, gbc );
        txtIVA2.setEnabled( false );
        txtIVA2.setBackground( Color.BLACK );
        txtIVA2.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 3, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtIVA3, gbc );
        txtIVA3.setEnabled( false );
        txtIVA3.setBackground( Color.BLACK );
        txtIVA3.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 3, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtIVA4, gbc );
        txtIVA4.setEnabled( false );
        txtIVA4.setBackground( Color.BLACK );
        txtIVA4.setDisabledTextColor( Color.WHITE );

        //Ubica los precios de los productos en sus posiciones
        gbc = new GridBagConstraints( 4, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtPrecio1, gbc );
        txtPrecio1.setEnabled( false );
        txtPrecio1.setBackground( Color.BLACK );
        txtPrecio1.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 4, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtPrecio2, gbc );
        txtPrecio2.setEnabled( false );
        txtPrecio2.setBackground( Color.BLACK );
        txtPrecio2.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 4, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtPrecio3, gbc );
        txtPrecio3.setEnabled( false );
        txtPrecio3.setBackground( Color.BLACK );
        txtPrecio3.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 4, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtPrecio4, gbc );
        txtPrecio4.setEnabled( false );
        txtPrecio4.setBackground( Color.BLACK );
        txtPrecio4.setDisabledTextColor( Color.WHITE );

        //Ubica las etiquetas de pedido de los productos en sus posiciones
        gbc = new GridBagConstraints( 5, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtPedir1, gbc );
        txtPedir1.setEnabled( false );
        txtPedir1.setBackground( Color.BLACK );
        txtPedir1.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 5, 2, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtPedir2, gbc );
        txtPedir2.setEnabled( false );
        txtPedir2.setBackground( Color.BLACK );
        txtPedir2.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 5, 3, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtPedir3, gbc );
        txtPedir3.setEnabled( false );
        txtPedir3.setBackground( Color.BLACK );
        txtPedir3.setDisabledTextColor( Color.WHITE );
        gbc = new GridBagConstraints( 5, 4, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtPedir4, gbc );
        txtPedir4.setEnabled( false );
        txtPedir4.setBackground( Color.BLACK );
        txtPedir4.setDisabledTextColor( Color.WHITE );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Actualiza los valores de los campos de nombre de los productos con los valores especificados. <br>
     * <b>post: </b>Los campos quedan actualizados.
     * @param nombre1 Nombre del producto1. nombre1 != null.
     * @param nombre2 Nombre del producto2. nombre2 != null.
     * @param nombre3 Nombre del producto3. nombre3 != null.
     * @param nombre4 Nombre del producto4. nombre4 != null.
     */
    public void refrescarNombres( String nombre1, String nombre2, String nombre3, String nombre4 )
    {
        txtProducto1.setText( nombre1 );
        txtProducto2.setText( nombre2 );
        txtProducto3.setText( nombre3 );
        txtProducto4.setText( nombre4 );
    }

    /**
     * Actualiza los valores de los campos de cantidad de los productos con los valores especificados. <br>
     * <b>post: </b>Los campos quedan actualizados.
     * @param can1 Cantidad del producto1.
     * @param can2 Cantidad del producto2.
     * @param can3 Cantidad del producto3.
     * @param can4 Cantidad del producto4.
     */
    public void refrescarCantidades( int can1, int can2, int can3, int can4 )
    {
        txtCantidad1.setText( "" + can1 );
        txtCantidad2.setText( "" + can2 );
        txtCantidad3.setText( "" + can3 );
        txtCantidad4.setText( "" + can4 );
    }

    /**
     * Actualiza los valores de los campos de IVA de los productos con los valores especificados. <br>
     * <b>post: </b>Los campos quedan actualizados
     * @param iva1 IVA del producto1.
     * @param iva2 IVA del producto2.
     * @param iva3 IVA del producto3.
     * @param iva4 IVA del producto4.
     */
    public void refrescarIVA( double iva1, double iva2, double iva3, double iva4 )
    {
        txtIVA1.setText( ( iva1 * 100 ) + "%" );
        txtIVA2.setText( ( iva2 * 100 ) + "%" );
        txtIVA3.setText( ( iva3 * 100 ) + "%" );
        txtIVA4.setText( ( iva4 * 100 ) + "%" );
    }

    /**
     * Actualiza los valores de los campos de precio de los productos con los valores especificados. <br>
     * <b>post: </b>Los campos quedan actualizados.
     * @param precio1 Precio del producto1.
     * @param precio2 Precio del producto2.
     * @param precio3 Precio del producto3
     * @param precio4 Precio del producto4.
     */
    public void refrescarPrecios( double precio1, double precio2, double precio3, double precio4 )
    {
        txtPrecio1.setText( "$" + precio1 );
        txtPrecio2.setText( "$" + precio2 );
        txtPrecio3.setText( "$" + precio3 );
        txtPrecio4.setText( "$" + precio4 );
    }

    /**
     * Actualiza los valores de los campos de pedido de los productos con los valores especificados. <br>
     * <b>post: </b>Los campos quedan actualizados.
     * @param pedido1 Pedido del producto1.
     * @param pedido2 Pedido del producto2.
     * @param pedido3 Pedido del producto3.
     * @param pedido4 Pedido del producto4.
     */
    public void refrescarPedidos( boolean pedido1, boolean pedido2, boolean pedido3, boolean pedido4 )
    {
        if( pedido1 )
            txtPedir1.setText( "SI" );
        else
            txtPedir1.setText( "NO" );
        if( pedido2 )
            txtPedir2.setText( "SI" );
        else
            txtPedir2.setText( "NO" );
        if( pedido3 )
            txtPedir3.setText( "SI" );
        else
            txtPedir3.setText( "NO" );
        if( pedido4 )
            txtPedir4.setText( "SI" );
        else
            txtPedir4.setText( "NO" );
    }

}