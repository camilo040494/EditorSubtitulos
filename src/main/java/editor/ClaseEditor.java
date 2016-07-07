package editor;

import java.io.File;

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
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"SRT FILES", "srt");
		fc.setFileFilter(filter);
		fc.showOpenDialog(null);
		File temp2 = fc.getSelectedFile();
		if (temp2 == null) {
			return errorMessage;
		}
		String direction = temp2.getAbsolutePath();
		return direction;
	}

	protected String errorMessage = "Archivo no válido";

	public void limpiarSubtitulos(){
		if (nombreArchivo == null) {
			JOptionPane.showMessageDialog(null, "Debe elegir un archivo");
			return;
		} else if (nombreArchivo.equals(errorMessage)) {
			JOptionPane.showMessageDialog(null,
					"Extensión de archivo no válido");
			return;
		}
		limpiar();
		System.out.println("TERMINO");
	}

	protected abstract void limpiar();
	
}
