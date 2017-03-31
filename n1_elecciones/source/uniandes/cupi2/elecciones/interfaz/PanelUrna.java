/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelUrna.java 445 2007-06-25 16:30:28Z camil-ji $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_elecciones
 * Autor: Carlos Andr�s Vega - 29-ene-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.elecciones.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.elecciones.mundo.Urna;

/**
 * Panel que contiene la informaci�n de la urna de votos en las elecciones cupi2
 */
@SuppressWarnings("serial")
public class PanelUrna extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta total votos en urna.
     */
    private JLabel etiquetaTotalVotos;

    /**
     * Etiqueta Promedio costo de campa�a.
     */
    private JLabel etiquetaPromedioCostoCampanha;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     */
    public PanelUrna( )
    {
        setBorder( new TitledBorder( "Totales" ) );
        setLayout( new GridLayout( 2, 1 ) );

        // Etiqueta total votos
        etiquetaTotalVotos = new JLabel( "Total Votos: " );
        etiquetaTotalVotos.setHorizontalAlignment( JLabel.CENTER );
        etiquetaTotalVotos.setForeground( Color.BLUE.darker( ) );
        etiquetaTotalVotos.setFont( new Font( "Tahoma", Font.BOLD, 24 ) );

        // Etiqueta total costo de campa�a
        etiquetaPromedioCostoCampanha = new JLabel( "Costo Promedio Campa�a: $" );
        etiquetaPromedioCostoCampanha.setHorizontalAlignment( JLabel.CENTER );
        etiquetaPromedioCostoCampanha.setForeground( Color.BLUE.darker( ) );
        etiquetaPromedioCostoCampanha.setFont( new Font( "Tahoma", Font.BOLD, 24 ) );

        add( etiquetaTotalVotos );
        add( etiquetaPromedioCostoCampanha );
        setPreferredSize( new Dimension( 78, 78 ) );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la informaci�n
     * @param urna Urna de la cual se va a mostrar la informaci�n. urna != null.
     */
    public void actualizar( Urna urna )
    {
        etiquetaTotalVotos.setText( "Total Votos: " + urna.calcularTotalVotos( ) );
        etiquetaPromedioCostoCampanha.setText( "Costo Promedio Campa�a: $" + formatearValorReal( urna.calcularCostoPromedioCampanha( ) ) );

    }

    /**
     * Formatea un valor num�rico real para presentar en la interfaz <br>
     * @param valor El valor num�rico a ser formateado
     * @return Cadena con el valor formateado con puntos y signos.
     */
    private String formatearValorReal( double valor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( " ###,###.##" );
        df.setMinimumFractionDigits( 2 );
        return df.format( valor );
    }

}