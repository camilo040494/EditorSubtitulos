package interfaz;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import editor.ClaseEditor;

public class InterfazPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
//		editor = new SubEditor();
		editor = new SubEditorJavaRegrex();
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
				System.out.println("OcurriÃ³ el siguiente error:");
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
		case 'á':
			return "a";
		case 'é':
			return "e";
		case 'í':
			return "i";
		case 'ó':
			return "o";
		case 'ú':
			return "u";
		case 'Á':
			return "A";
		case 'É':
			return "E";
		case 'Í':
			return "I";
		case 'Ó':
			return "O";
		case 'Ú':
			return "U";
		case 'ñ':
			return "ni";
		case 'Ñ':
			return "Ni";
		case '¿':
			return "";
		case '¡':
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
		temp.put("á", "a");
		temp.put("é", "e");
		temp.put("í", "i");
		temp.put("ó", "o");
		temp.put("ú", "u");
		temp.put("Á", "A");
		temp.put("É", "E");
		temp.put("Í", "I");
		temp.put("Ó", "O");
		temp.put("Ú", "U");
		temp.put("ñ", "ni");
		temp.put("Ñ", "Ni");
		temp.put("¿", "");
		temp.put("¡", "");
		return temp;
	}
	
	@Override
	protected void limpiar(){
		FileInputStream archivo = null;
		try {
			File ruta = new File(nombreArchivo);
			archivo = new FileInputStream(ruta);
			System.out.println(ruta);
			InputStreamReader conexion = new InputStreamReader(archivo,"ISO-8859-1");
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
			
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(ruta),"ISO-8859-1");
			writer.write(input);
			writer.close();

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
		}
	}
}class SubEditorJavaRegrex extends ClaseEditor{
	
	private static HashMap<Pattern, String> map = inicializarMap();

	private static HashMap<Pattern, String> inicializarMap() {
		HashMap<Pattern, String> temp = new HashMap<Pattern, String>();
		temp.put(Pattern.compile("(.*)á(.*)", Pattern.DOTALL), "$1a$2");
		temp.put(Pattern.compile("(.*)é(.*)", Pattern.DOTALL), "$1e$2");
		temp.put(Pattern.compile("(.*)í(.*)", Pattern.DOTALL), "$1i$2");
		temp.put(Pattern.compile("(.*)ó(.*)", Pattern.DOTALL), "$1o$2");
		temp.put(Pattern.compile("(.*)ú(.*)", Pattern.DOTALL), "$1u$2");
		temp.put(Pattern.compile("(.*)Á(.*)", Pattern.DOTALL), "$1A$2");
		temp.put(Pattern.compile("(.*)É(.*)", Pattern.DOTALL), "$1E$2");
		temp.put(Pattern.compile("(.*)Í(.*)", Pattern.DOTALL), "$1I$2");
		temp.put(Pattern.compile("(.*)Ó(.*)", Pattern.DOTALL), "$1O$2");
		temp.put(Pattern.compile("(.*)Ú(.*)", Pattern.DOTALL), "$1U$2");
		temp.put(Pattern.compile("(.*)ñ(.*)", Pattern.DOTALL), "$1ni$2");
		temp.put(Pattern.compile("(.*)Ñ(.*)", Pattern.DOTALL), "$1Ni$2");
		temp.put(Pattern.compile("(.*)¿(.*)", Pattern.DOTALL), "$1$2");
		temp.put(Pattern.compile("(.*)¡(.*)", Pattern.DOTALL), "$1$2");
		return temp;
	}
	
	private String regrexFunction(String input) {
		String retorno = input;
		for (Pattern jRegrex : map.keySet()) {
			Matcher matcher = jRegrex.matcher(retorno);
			retorno = matcher.replaceAll(map.get(jRegrex));
		}
		return retorno;
	}
	
	@Override
	protected void limpiar(){
		FileInputStream archivo = null;
		try {
			File ruta = new File(nombreArchivo);
			archivo = new FileInputStream(ruta);
			System.out.println(ruta);
			InputStreamReader conexion = new InputStreamReader(archivo,"ISO-8859-1");
			BufferedReader lector = new BufferedReader(conexion);

			String linea;
			// http://chuwiki.chuidiang.org/index.php?title=Lectura_y_Escritura_de_Ficheros_en_Java
			String input = "";
			while ((linea = lector.readLine()) != null)
				input += linea.trim() + System.lineSeparator();
			
			input = regrexFunction(input);

			archivo.close();

			System.out.println(input);
			
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(ruta),"ISO-8859-1");
			writer.write(input);
			writer.close();

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
		}
	}
}
