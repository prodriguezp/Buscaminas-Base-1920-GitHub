 import java.util.ArrayList;
import java.util.Random;

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posición guarda el número -1
 * Si no hay una mina, se guarda cuántas minas hay alrededor.
 * Almacena la puntuación de la partida
 * @author jesusredondogarcia
 * @version 1.0
 * @since 0.1
 *
 */
public class ControlJuego {
	String nombreJugador;
	
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	int [][] tablero;
	private int puntuacion;
	
	int casillasAbiertas = 0; 
	
	public ControlJuego() {
		//Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		
		//Inicializamos una nueva partida
		inicializarPartida();
	}
	
	/**
	 * Modificar puntuacion
	 */
	public void aumentarPunto() {
		puntuacion++;
	}
	
	/**Método para generar un nuevo tablero de partida:
	 * La estructura tablero debe existir. 
	 * Al final el tablero se habrá inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 	El resto de posiciones que no son minas guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		puntuacion = 0;
		int numMinas = 0;
		//TODO: Repartir minas e inicializar puntaci�n. Si hubiese un tablero anterior, lo pongo todo a cero para inicializarlo.
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if(a�adirMina() && numMinas<20) {
					numMinas++;
					tablero[i][j]=-1;
				}
				else {
					tablero[i][j]=0;
				}
			}
		}
		
		
		//Al final del m�todo hay que guardar el n�mero de minas para las casillas que no son mina:
		for (int i2 = 0; i2 < tablero.length; i2++) {
			for (int j2 = 0; j2 < tablero[i2].length; j2++) {
				if (tablero[i2][j2] != MINA){
					tablero[i2][j2] = calculoMinasAdjuntas(i2,j2);
				}
			}
		}
	}
	
	private boolean a�adirMina() {
		return Math.random() > 0.7;
	}


	/**Cálculo de las minas adjuntas: 
	 * Para calcular el número de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdrán LADO_TABLERO-1.
	 * Por lo tanto, como poco la i y la j valdrán 0.
	 * @param i: posición vertical de la casilla a rellenar
	 * @param j: posición horizontal de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j){
		int contadorMinas = 0;	
		/*
		 * Resumido 
		 * iInical Math.min(0, i-1)
		 * jInical Math.min(0, j-1)
		 * iFinal Math.max(LADO_TABLERO-1, i+1)
		 * jFinal Math.max(LADO_TABLERO-1, J+1)
		 */
		int inicioI = i-1;		
		int inicioJ = j-1;
		int finalI = i+1;
		int finalj = j+1;
		
		if(inicioI<0) { inicioI=0;}
		if(inicioJ<0) { inicioJ=0;}
		
		if(finalI>(LADO_TABLERO-1)) { finalI=LADO_TABLERO-1;}
		if(finalj>(LADO_TABLERO-1)) { finalj=LADO_TABLERO-1;}
		
		for (int k = inicioI; k <= finalI; k++) {
			for (int k2 = inicioJ; k2 <= finalj; k2++) {
					if((tablero[k][k2])==-1) {						
						contadorMinas++;
					}										
			}
		}
		
		return contadorMinas;
	}
	
	/**
	 * Método que nos permite 
	 * La casilla nunca debe haber sido abierta antes, no es controlado por el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){
		return false;
	}
	
	
	
	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	 **/
	public boolean esFinJuego(){
		return false;
	}
	
	
	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: "+puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * El tablero tiene que estar ya inicializado, por lo tanto no hace falta calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];
	}

	/**
	 * Método que devuelve la puntuación actual
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	
}
