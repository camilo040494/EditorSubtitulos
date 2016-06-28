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
	
	protected String nombreArchivo;

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

	protected String errorMessage = "Archivo no válido";

	public abstract void limpiarSubtitulos();
	
}
