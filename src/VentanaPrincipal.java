import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Clase VentanaPrincipal que guarda todos los componentes de la interzaz del buscaminas.
 * El boton de Go! empieza la partida
 * Todos los botones al pulsar se convierte en JLabel, a excepcion de las minas.
 * Los listener se incializan en este metodo  {@link #inicializarListeners()}
 * Codigo de incializar
 * @author Pedro Luis
 *
 */
public class VentanaPrincipal {


	//La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;
	JPanel panelImagen;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;
	
	//Todos los botones se meten en un panel independiente.
	//Hacemos esto para que podamos cambiar después los componentes por otros
	JPanel [][] panelesJuego;
	JButton [][] botonesJuego;
	
	//Correspondencia de colores para las minas:
	Color correspondenciaColores [] = {Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED};
	
	JButton botonEmpezar;
	JTextField pantallaPuntuacion;
	
	
	//LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;
	
	
	//Constructor, marca el tamaño y el cierre del frame
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
	}
	
	/**
	 * Metodo que crea todos los componentes de la interzaf y los posiciona en el JFrame
	 */
	public void inicializarComponentes(){
		
		//Definimos el layout:
		ventana.setLayout(new GridBagLayout());
		
		//Inicializamos componentes
		panelImagen = new JPanel();
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1,1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1,1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10,10));
		
		
		botonEmpezar = new JButton("Go!");
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Bordes y colores:
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));
		
			
		//Colocamos los componentes:
		//AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		//VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		//AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		//ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);
		
		//Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1,1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}
		
		//Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}
		
		//BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);
		
	}
	
	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el programa
	 */
	public void inicializarListeners(){
		botonEmpezar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < botonesJuego.length; i++) {
					for (int j = 0; j < botonesJuego[i].length; j++) {
						panelesJuego[i][j].removeAll();
						pantallaPuntuacion.setText("0");
						botonesJuego[i][j].setEnabled(true);
						botonesJuego[i][j].setText("-");
						panelesJuego[i][j].add(botonesJuego[i][j]);						
					}
				}
				juego.nombreJugador = JOptionPane.showInputDialog("Introduce tu nombre: ");
				refrescarPantalla();
				juego.inicializarPartida();
				juego.depurarTablero();
				
			}
		});
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego [i][j].addActionListener(new ActionBoton(this,i,j));
			}
		}
		
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego [i][j].addMouseListener(new ClickDerecho(i, j, this));
			}
		}
	}
	
	
	/**
	 * Pinta en la pantalla el número de minas que hay alrededor de la celda
	 * Saca el botón que haya en la celda determinada y añade un JLabel centrado y no editable con el número de minas alrededor.
	 * Se pinta el color del texto según la siguiente correspondecia (consultar la variable correspondeciaColor):
	 * - 0 : negro
	 * - 1 : cyan
	 * - 2 : verde
	 * - 3 : naranja
	 * - 4 ó más : rojo 
	 * @param i: posición vertical de la celda.
	 * @param j: posición horizontal de la celda.
	 * 
	 */
	public void mostrarNumMinasAlrededor(int i , int j) {	
		cambirBotonporLabel(i, j);	
		if(juego.tablero[i][j]==0) {
			int inicioI = i-1;		
			int inicioJ = j-1;
			int finalI = i+1;
			int finalj = j+1;
			
			if(inicioI<0) { inicioI=0;}
			if(inicioJ<0) { inicioJ=0;}
			
			if(finalI>(juego.LADO_TABLERO-1)) { finalI=juego.LADO_TABLERO-1;}
			if(finalj>(juego.LADO_TABLERO-1)) { finalj=juego.LADO_TABLERO-1;}
			
			for (int k = inicioI; k <= finalI; k++) {
				for (int k2 = inicioJ; k2 <= finalj; k2++) {
					if(panelesJuego[k][k2].getComponent(0) instanceof JButton) {
						botonesJuego[k][k2].doClick();
					}
					
				}
			}
		}
		
		refrescarPantalla();
	}
	public void cambirBotonporLabel(int i, int j) {	
		panelesJuego[i][j].removeAll();
		JLabel label = new JLabel(Integer.toString(juego.getMinasAlrededor(i, j)));
		label.setForeground(correspondenciaColores[juego.tablero[i][j]]);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panelesJuego[i][j].add(label);	
	}
	
	/**
	 * Muestra una ventana que indica el fin del juego
	 * @param porExplosion : Un booleano que indica si es final del juego porque ha explotado una mina (true) o bien porque hemos desactivado todas (false) 
	 * Todos los botones se desactivan excepto el de volver a iniciar el juego.
	 */
	public void mostrarFinJuego(boolean porExplosion) {
		String cadenaMensaje;		
		Utilidad.a�adir(juego.nombreJugador,juego.getPuntuacion());
		ArrayList<Jugador> jugadores = Utilidad.getTop();	
		if(porExplosion) {
			cadenaMensaje="Has caido en una mina, perdistes! \n"
					+ "Puntuaci�n: "+juego.getPuntuacion();
			jugadores = Utilidad.getTop();	
			
			
		}
		else {
			cadenaMensaje="Ganastes! \n" + 
					"Puntuaci�n: "+juego.getPuntuacion()+"\n";
			for (int i = 0; i < jugadores.size(); i++) {
				cadenaMensaje+=(i+1)+"�-- "+jugadores.get(i).getNombre()+" = "+jugadores.get(i).getPuntuaje()+"\n";
			}
		}
		
		
		JOptionPane.showMessageDialog(null, cadenaMensaje);						
		descativarBotones();
		
	}

	private void descativarBotones() {		
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j].setEnabled(false);
			}
		}
	}
	/**
	 * Método que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		juego.aumentarPunto();
		pantallaPuntuacion.setText(Integer.toString(juego.getPuntuacion()));
		//refrescarPantalla();
	}
	
	/**
	 * Método para refrescar la pantalla
	 */
	public void refrescarPantalla(){
		ventana.revalidate(); 
		ventana.repaint();
	}

	/**
	 * Método que devuelve el control del juego de una ventana
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método para inicializar el programa
	 */
	public void inicializar(){
		//IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();	
		inicializarListeners();		
	}



}
