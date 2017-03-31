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

package uniandes.cupi2.tienda.interfaz;

import java.awt.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Presenta los cálculos de las estadísticas
 */
public class PanelCalculos extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /** Etiqueta para los ingresos */
    private JLabel labIngresos;
    /** Etiqueta para el producto mas vendido */
    private JLabel labMasVendido;
    /** Etiqueta para el producto menos vendido */
    private JLabel labMenosVendido;
    /** Etiqueta para el promedio */
    private JLabel labPromedio;
    /** Campo de texto para los ingresos */
    private JTextField txtIngresos;
    /** Campo de texto para el producto más vendido */
    private JTextField txtMasVendido;
    /** Campo de texto para el producto menos vendido */
    private JTextField txtMenosVendido;
    /** Campo de texto para el promedio */
    private JTextField txtPromedio;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    /**
     * Construye un panel para visualizar los datos
     */
    public PanelCalculos( )
    {
        // Creación e inicialización de los elementos de la interfaz
        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Cálculos" ) ) );
        GridBagLayout gridbag = new GridBagLayout( );
        setLayout( gridbag );

        labIngresos = new JLabel( "Ingresos " );
        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( labIngresos, gbc );

        txtIngresos = new JTextField( 15 );
        gbc = new GridBagConstraints( 1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtIngresos, gbc );
        txtIngresos.setEnabled( false );
        txtIngresos.setBackground( Color.BLACK );
        txtIngresos.setDisabledTextColor( Color.WHITE );

        labMasVendido = new JLabel( "Producto más vendido " );
        gbc = new GridBagConstraints( 0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( labMasVendido, gbc );

        txtMasVendido = new JTextField( 15 );
        gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtMasVendido, gbc );
        txtMasVendido.setEnabled( false );
        txtMasVendido.setBackground( Color.BLACK );
        txtMasVendido.setDisabledTextColor( Color.WHITE );

        labMenosVendido = new JLabel( "Producto menos vendido " );
        gbc = new GridBagConstraints( 0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( labMenosVendido, gbc );

        txtMenosVendido = new JTextField( 15 );
        gbc = new GridBagConstraints( 1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtMenosVendido, gbc );
        txtMenosVendido.setEnabled( false );
        txtMenosVendido.setBackground( Color.BLACK );
        txtMenosVendido.setDisabledTextColor( Color.WHITE );

        labPromedio = new JLabel( "Promedio " );
        gbc = new GridBagConstraints( 0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( labPromedio, gbc );

        txtPromedio = new JTextField( 15 );
        gbc = new GridBagConstraints( 1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtPromedio, gbc );
        txtPromedio.setEnabled( false );
        txtPromedio.setBackground( Color.BLACK );
        txtPromedio.setDisabledTextColor( Color.WHITE );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Actualiza los valores de los campos de estadísticas con los valores especificados. <br>
     * <b>post: </b>Los campos quedan actualizados.
     * @param ingresos Ingresos de la tienda.
     * @param mas Nombre del producto mas vendido. mas != null.
     * @param menos Nombre producto menos vendido. menos != null.
     * @param promedio Promedio de ventas de la tienda
     */
    public void refrescarEstadisticas( double ingresos, String mas, String menos, double promedio )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$ ###,###.##" );
        txtIngresos.setText( df.format( ingresos ) );
        txtMasVendido.setText( mas );
        txtMenosVendido.setText( menos );
        txtPromedio.setText( df.format( promedio ) );
    }
}