package editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class ClaseEditor {
	
	private String nombreArchivo;

	public void setNombreArchivo(String nuevoNombre) {
		nombreArchivo = nuevoNombre;
	}

	public String darRuta() {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"SRT FILES", "srt");
		fc.setFileFilter(filter);
		File temp2 = fc.getSelectedFile();
		if (temp2 == null) {
			return errorMessage;
		}
		String direction = temp2.getAbsolutePath();
		return direction;
	}

	private String errorMessage = "Archivo no válido";

	public void limpiarSubtitulos() {
		if (nombreArchivo == null) {
			JOptionPane.showMessageDialog(null, "Debe elegir un archivo");
			return;
		} else if (nombreArchivo.equals(errorMessage)) {
			JOptionPane.showMessageDialog(null,
					"Extensi�n de archivo no v�lido");
			return;
		}
		FileWriter fichero = null;
		FileInputStream archivo = null;
		try {
			String path = System.getProperty("user.dir");
			System.out.println("ruta: " + path);
			path = nombreArchivo;
			File ruta = new File(nombreArchivo);
			archivo = new FileInputStream(ruta);
			System.out.println(ruta);
			InputStreamReader conexion = new InputStreamReader(archivo);
			BufferedReader lector = new BufferedReader(conexion);

			fichero = new FileWriter(nombreArchivo, true);
			PrintWriter pw = new PrintWriter(fichero);

			String linea;
			// http://chuwiki.chuidiang.org/index.php?title=Lectura_y_Escritura_de_Ficheros_en_Java
			pw.println("");
			ArrayList<String> subs = new ArrayList<String>();
			while ((linea = lector.readLine()) != null) {
				linea = linea.trim();
				StringBuilder write = new StringBuilder();

				for (int i = 0; i < linea.length(); i++) {
					write.append(reemplazarChar(linea.charAt(i)));
				}

				subs.add(write.toString());
			}

			for (String string : subs) {
				pw.println(string.toString());
			}

			archivo.close();
			fichero.close();
		} catch (java.io.IOException e) {
			System.out.println("Ocurrió el siguiente error:");
			e.printStackTrace();
		} finally {
			if (archivo != null) {
				try {
					archivo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fichero != null) {
				try {
					fichero.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("TERMINO");
	}

	protected abstract String reemplazarChar(char charAt);
	
}
