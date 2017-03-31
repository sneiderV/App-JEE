/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelCandidato.java 445 2007-06-25 16:30:28Z camil-ji $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_elecciones
 * Autor: Milena Vela - 01-dic-2006
 * Autor: Carlos Andrés Vega - 29-ene-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.elecciones.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.cupi2.elecciones.mundo.Candidato;

/**
 * Panel que contiene la información de un candidato.
 */
@SuppressWarnings("serial")
public class PanelCandidato extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando actualizar datos del candidato.
     */
    private static final String PORCENTAJE_VOTOS = "Porcentaje Votos";

    /**
     * Comando votar por el candidato.
     */
    private static final String VOTAR = "Votar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazElecciones principal;

    /**
     * Número del candidato.
     */
    private int numCandidato;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta nombre del candidato.
     */
    private JLabel etiquetaNombreCandidato;

    /**
     * Etiqueta apellido del candidato.
     */
    private JLabel etiquetaApellidoCandidato;

    /**
     * Etiqueta edad del candidato.
     */
    private JLabel etiquetaEdadCandidato;

    /**
     * Etiqueta documento del candidato.
     */
    private JLabel etiquetaPartidoPoliticoCandidato;

    /**
     * Etiqueta Costo de campaña del candidato.
     */
    private JLabel etiquetaCostoCampanhaCandidato;

    /**
     * Etiqueta número de votos del candidato.
     */
    private JLabel etiquetaNumeroVotos;

    /**
     * Botón Ingresar Nombre.
     */
    private JButton botonDarPorcentajeVotos;

    /**
     * Botón Votar.
     */
    private JButton botonVotar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param laPrincipal Ventana principal. laPrincipal != null.
     * @param nCandidato Número del candidato.
     */
    public PanelCandidato( InterfazElecciones laPrincipal, int nCandidato )
    {
        principal = laPrincipal;
        numCandidato = nCandidato;

        // Botón actualizar datos
        botonDarPorcentajeVotos = new JButton( PORCENTAJE_VOTOS );
        botonDarPorcentajeVotos.setPreferredSize( new Dimension( 160, 20 ) );
        botonDarPorcentajeVotos.setActionCommand( PORCENTAJE_VOTOS );
        botonDarPorcentajeVotos.addActionListener( this );

        botonVotar = new JButton( VOTAR );
        botonVotar.setPreferredSize( new Dimension( 160, 20 ) );
        botonVotar.setActionCommand( VOTAR );
        botonVotar.addActionListener( this );

        etiquetaNombreCandidato = new JLabel( "Nombre: " );
        etiquetaNombreCandidato.setHorizontalAlignment( JLabel.LEFT );

        etiquetaApellidoCandidato = new JLabel( "Apellido: " );
        etiquetaApellidoCandidato.setHorizontalAlignment( JLabel.LEFT );

        etiquetaEdadCandidato = new JLabel( "Edad: " );
        etiquetaEdadCandidato.setHorizontalAlignment( JLabel.LEFT );

        etiquetaPartidoPoliticoCandidato = new JLabel( "Partido Político: " );
        etiquetaPartidoPoliticoCandidato.setHorizontalAlignment( JLabel.LEFT );

        etiquetaCostoCampanhaCandidato = new JLabel( "Costo Campaña: $ " );
        etiquetaCostoCampanhaCandidato.setHorizontalAlignment( JLabel.LEFT );

        etiquetaNumeroVotos = new JLabel( "Numero de Votos: " );
        etiquetaNumeroVotos.setHorizontalAlignment( JLabel.CENTER );
        etiquetaNumeroVotos.setForeground( Color.RED.darker( ) );

        JPanel panelImagen = new JPanel( );
        JPanel panelInformacion = new JPanel( );

        setLayout( new java.awt.BorderLayout( ) );
        setBorder( javax.swing.BorderFactory.createTitledBorder( "Candidato" + numCandidato ) );

        add( panelImagen, java.awt.BorderLayout.CENTER );
        panelImagen.setLayout( new BorderLayout( ) );
        JLabel temp = new JLabel( );
        temp.setHorizontalAlignment( JLabel.CENTER );
        temp.setIcon( new ImageIcon( "data/candidato" + numCandidato + ".gif" ) );

        panelImagen.add( temp, BorderLayout.CENTER );

        panelInformacion.setLayout( new GridLayout( 8, 1 ) );

        panelInformacion.add( etiquetaNombreCandidato );

        panelInformacion.add( etiquetaApellidoCandidato );

        panelInformacion.add( etiquetaEdadCandidato );

        panelInformacion.add( etiquetaPartidoPoliticoCandidato );

        panelInformacion.add( etiquetaCostoCampanhaCandidato );

        panelInformacion.add( etiquetaNumeroVotos );

        panelInformacion.add( botonDarPorcentajeVotos );

        panelInformacion.add( botonVotar );

        add( panelInformacion, java.awt.BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la visualización de la información
     * @param candidato Candidato de donde se saca la información. candidato != null.
     */
    public void actualizar( Candidato candidato )
    {
        etiquetaNombreCandidato.setText( "Nombre: " + candidato.darNombre( ) );
        etiquetaApellidoCandidato.setText( "Apellido: " + candidato.darApellido( ) );
        etiquetaEdadCandidato.setText( "Edad: " + candidato.darEdad( ) );
        etiquetaPartidoPoliticoCandidato.setText( "Partido Político: " + String.valueOf( candidato.darPartidoPolitico( ) ) );
        etiquetaCostoCampanhaCandidato.setText( "Costo Campaña: $" + formatearValorReal( candidato.darCostoCampanha( ) ) );
        etiquetaNumeroVotos.setText( "Numero de Votos: " + candidato.darVotos( ) );

        if( principal.darTotalVotosUrna( ) == 0 )
            botonDarPorcentajeVotos.setEnabled( false );
        else
            botonDarPorcentajeVotos.setEnabled( true );

    }

    /**
     * Manejo de eventos del usuario
     * @param e Evento de usuario. e != null.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( VOTAR.equals( e.getActionCommand( ) ) )
        {
            principal.adicionarVotoCandidato( numCandidato );
        }
        else if( PORCENTAJE_VOTOS.equals( e.getActionCommand( ) ) )
        {
            principal.mostrarDialogoPorcentajeVotos( numCandidato );
        }

    }

    /**
     * Formatea un valor numérico real para presentar en la interfaz <br>
     * @param valor El valor numérico a ser formateado
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