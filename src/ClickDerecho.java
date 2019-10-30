import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickDerecho extends MouseAdapter{
	int i;
	int j;
	VentanaPrincipal marco;
	public ClickDerecho(int i, int j, VentanaPrincipal marco) {
		this.i = i;
		this.j = j;
		this.marco = marco;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		marco.botonesJuego[i][j].setText("|>");
		marco.botonesJuego[i][j].setEnabled(false);
	}		
}
