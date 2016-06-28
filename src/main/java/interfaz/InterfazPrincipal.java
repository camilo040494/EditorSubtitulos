package interfaz;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import editor.ClaseEditor;

public class InterfazPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private SubEditorAddingEdition editor;
	private ClaseEditor editor;
	private PanelDatos newPanel;

	public InterfazPrincipal() {
		setTitle("Ordenar canciones aleatorias");
		setSize(800, 100);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
//		editor = new SubEditorAddingEdition();
		editor = new SubEditor();
		newPanel = new PanelDatos(this);
		add(newPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		InterfazPrincipal interfaz = new InterfazPrincipal();
		interfaz.setVisible(true);
	}

	public String elegirArchivo() {
		String ruta = editor.darRuta();
		editor.setNombreArchivo(ruta);
		return ruta;
	}

	public void limpiar() {
		editor.limpiarSubtitulos();
	}

}
class SubEditorAddingEdition extends ClaseEditor{
	
	@Override
	protected void limpiar(){
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
		}

	private String reemplazarChar(char charAt) {
		switch (charAt) {
		case '�':
			return "a";
		case '�':
			return "e";
		case '�':
			return "i";
		case '�':
			return "o";
		case '�':
			return "u";
		case '�':
			return "A";
		case '�':
			return "E";
		case '�':
			return "I";
		case '�':
			return "O";
		case '�':
			return "U";
		case '�':
			return "ni";
		case '�':
			return "Ni";
		case '�':
			return "";
		case '�':
			return "";
		default:
			return charAt+"";
		}
	}

}
class SubEditor extends ClaseEditor{
	
	private static HashMap<String, String> map = inicializarMap();

	private static HashMap<String, String> inicializarMap() {
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put("�", "a");
		temp.put("�", "e");
		temp.put("�", "i");
		temp.put("�", "o");
		temp.put("�", "u");
		temp.put("�", "A");
		temp.put("�", "E");
		temp.put("�", "I");
		temp.put("�", "O");
		temp.put("�", "U");
		temp.put("�", "ni");
		temp.put("�", "Ni");
		temp.put("�", "");
		temp.put("�", "");
		return temp;
	}
	
	@Override
	protected void limpiar(){
		FileWriter fichero = null;
		FileInputStream archivo = null;
		try {
			File ruta = new File(nombreArchivo);
			archivo = new FileInputStream(ruta);
			System.out.println(ruta);
			InputStreamReader conexion = new InputStreamReader(archivo);
			BufferedReader lector = new BufferedReader(conexion);

			String linea;
			// http://chuwiki.chuidiang.org/index.php?title=Lectura_y_Escritura_de_Ficheros_en_Java
			String input = "";
			while ((linea = lector.readLine()) != null)
				input += linea.trim() + System.lineSeparator();

			for (String key : map.keySet()) {
				input = input.replace(key, map.get(key));
			}

			archivo.close();

			System.out.println(input);

			FileOutputStream os = new FileOutputStream(ruta);
			os.write(input.getBytes());
			os.close();

		} catch (java.io.IOException e) {
			System.out.println("Ocurrio el siguiente error:");
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
	}
}