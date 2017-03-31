/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel con las operaciones de la aplicaci�n
 */
public class PanelOperaciones extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /** Constante para vender producto en action performed */
    private final static String VENDER_PRODUCTO = "VENDER PRODUCTO";
    /** Constante para pedir producto en action performed */
    private final static String PEDIR_PRODUCTO = "PEDIR PRODUCTO";
    /** Constante para realizar la OPCION_1 en action performed */
    private final static String OPCION_1 = "OPCION_1";
    /** Constante para realizar la OPCION_2 en action performed */
    private final static String OPCION_2 = "OPCION_2";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Ventana principal de la aplicaci�n */
    private InterfazTienda principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /** Bot�n que tiene asignada la funci�n de realizar una venta */
    private JButton botonVenderProducto;
    /** Bot�n que tiene asignada la funci�n de realizar una pedido */
    private JButton botonPedirProducto;
    /** Bot�n que tiene asignada la funci�n para realizar la OPCION_1 */
    private JButton opcion1;
    /** Bot�n que tiene asignada la funci�n de realizar la OPCION_2 */
    private JButton opcion2;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel de operaciones.
     * @param interfaz Ventana principal de la aplicaci�n. interfaz != null.
     */
    public PanelOperaciones( InterfazTienda interfaz )
    {
        principal = interfaz;

        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Operaciones" ) ) );
        GridBagLayout gridbag = new GridBagLayout( );
        setLayout( gridbag );

        // Creaci�n e inicializaci�n de los elementos gr�ficos

        botonVenderProducto = new JButton( );
        botonVenderProducto.setText( "Vender Producto" );
        botonVenderProducto.setActionCommand( VENDER_PRODUCTO );
        botonVenderProducto.addActionListener( this );
        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( botonVenderProducto, gbc );

        botonPedirProducto = new JButton( );
        botonPedirProducto.setText( "Pedir Producto" );
        botonPedirProducto.setActionCommand( PEDIR_PRODUCTO );
        botonPedirProducto.addActionListener( this );
        gbc = new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( botonPedirProducto, gbc );

        opcion1 = new JButton( );
        opcion1.setText( "Opci�n 1" );
        opcion1.setActionCommand( OPCION_1 );
        opcion1.addActionListener( this );
        gbc = new GridBagConstraints( 1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( opcion1, gbc );

        opcion2 = new JButton( );
        opcion2.setText( "Opci�n 2" );
        opcion2.setActionCommand( OPCION_2 );
        opcion2.addActionListener( this );
        gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( opcion2, gbc );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Este m�todo ejecuta las acciones adecuadas seg�n el bot�n que haya sido presionado.
     * @param evento evento generado por el usuario
     */
    public void actionPerformed( ActionEvent evento )
    {
        String command = evento.getActionCommand( );
        if( command.equals( VENDER_PRODUCTO ) )
        {
            // Evento: Bot�n de vender un producto
            String strNombreProducto = ( String )JOptionPane.showInputDialog( this, "Seleccione el producto", "Producto", JOptionPane.INFORMATION_MESSAGE, null, principal.darNombreProductos( ), null );
            if( strNombreProducto != null )
            {
                String strCantidad = JOptionPane.showInputDialog( this, "Introduzca la cantidad" );
                if( strCantidad != null )
                {
                    try
                    {
                        int nCantidad = Integer.parseInt( strCantidad );
                        principal.venderProducto( strNombreProducto, nCantidad );
                    }
                    catch( NumberFormatException e )
                    {
                        JOptionPane.showMessageDialog( this, "Error en el formato de la cantidad", "Error", JOptionPane.ERROR_MESSAGE );

                    }
                }
            }
        }
        else if( command.equals( PEDIR_PRODUCTO ) )
        {
            // Evento: Bot�n de hacer un pedido de un producto
            String strNombreProducto = ( String )JOptionPane.showInputDialog( this, "Seleccione el producto", "Producto", JOptionPane.INFORMATION_MESSAGE, null, principal.darNombreProductos( ), null );
            if( strNombreProducto != null )
            {
                String strCantidad = JOptionPane.showInputDialog( this, "Introduzca la cantidad" );
                if( strCantidad != null )
                {
                    try
                    {
                        int nCantidad = Integer.parseInt( strCantidad );
                        principal.realizarPedido( strNombreProducto, nCantidad );
                    }
                    catch( NumberFormatException e )
                    {
                        JOptionPane.showMessageDialog( this, "Error en el formato de la cantidad", "Error", JOptionPane.ERROR_MESSAGE );

                    }
                }
            }
        }
        else if( command.equals( OPCION_1 ) )
        {
            // Evento: Extensi�n 1 del programa
            principal.reqFuncOpcion1( );
        }
        else if( command.equals( OPCION_2 ) )
        {
            // Evento: Extensi�n 2 del programa
            principal.reqFuncOpcion2( );
        }
    }
}