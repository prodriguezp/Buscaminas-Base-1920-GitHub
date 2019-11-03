import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Utilidad {

	public static ArrayList<Jugador> getTop() {
		ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:jugadores.db");
			Statement st = connection.createStatement();			
			ResultSet rs = st.executeQuery("SELECT * FROM JUGADORES ORDER BY PUNTUACION DESC LIMIT 3");
			
			while(rs.next()) {
				Jugador j = new Jugador(rs.getString(2), rs.getInt(3));
				jugadores.add(j);
			}		
			rs.close();
			st.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return jugadores;
	}

	public static void añadir(String nombreJugador, int puntuacion) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:jugadores.db");
			String sql = "INSERT INTO jugadores(nombre,puntuacion) VALUES('"+nombreJugador+"',"+puntuacion+")";
			Statement st = connection.createStatement();
			int filasAfectadas = st.executeUpdate(sql);
			System.out.println("Filas afectada "+filasAfectadas);
			st.close();
			connection.close();
		}catch (Exception e) {
			System.out.println("Error al añadir");
		}	
		
	}

	

}
