package cuarentena;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Conector {

	// atributos

	private static Connection con;
	private static Conector instance = null;

	private Conector() {

	}

	private synchronized static void crearInstancia() {
		if (instance == null) {
			INSTANCE = new Conector();
			crearConexion();
		}
	}

	public static Conector getInstancia() {
		if (instance == null) {
			crearInstancia();
		}
		return instance;
	}

	private static void crearConexion() {
		String host = "127.0.0.1";
		String user = "Joel";
		String password = "Totololo";
		String dataBase = "cuarentena";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String urlConexion = "jdbc:mysql://" + host + "/" + dataBase + "?" + "user=" + user + "&password="
					+ password;
			con = DriverManager.getConnection(urlConexion);
			System.out.println("Good job");
			System.out.println();
		} catch (Exception exep) {

		}

	}

	public ArrayList<String> getPaciente() throws SQLException {
		ArrayList<String> listPaciente = new ArrayList<String>();
		PreparedStatement ps = con
				.prepareStatement("Select * from paciente inner join persona where paciente.personaID = persona.CI ");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			listPaciente.add(rs.getString("nombres"));
		}
		rs.close();
		return listPaciente;
	}

	public ArrayList<String> getDoctorConsultorio() throws SQLException {
		ArrayList<String> listDoctorConsultorio = new ArrayList<String>();
		PreparedStatement ps = con.prepareStatement(
				"select * from persona inner join doctor on persona.CI = doctor.personaID inner join consultorio on doctor.consultorioID = consultorio.ID");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			listDoctorConsultorio.add(rs.getString("nro"));
			listDoctorConsultorio.add(rs.getString("nombres"));
			listDoctorConsultorio.add(rs.getString("apellidos"));
		}
		rs.close();
		return listDoctorConsultorio;
	}
}
