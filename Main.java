package cuarentena;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Conector instancia = Conector.getInstancia();
		try {
			ArrayList<String> listPaciente = instancia.getPaciente();
			for (String nombres : listPaciente) {
				System.out.println(nombres);
			}
		} catch (SQLException exep) {
			exep.printStackTrace();
		}
		try {
			ArrayList<String> listDoctorConsultorio = instancia.getDoctorConsultorio();
			for (String elemento : listDoctorConsultorio) {
				System.out.print(elemento);
				System.out.print(" ");
				int cont;
        cont++:
				if (cont % 3 == 0) {
					System.out.println();
				}
			}
		} catch (SQLException exep) {
			exep.printStackTrace();
		}

	}

}
