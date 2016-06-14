package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelDatos extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String ARCHIVO = "Elegir Archivo";
	public final static String LIMPIAR = "Limpiar";
	
	private InterfazPrincipal interfaz;
	private JButton butLimpiar, butArchivo;
	private JTextField txtRutaArchivo;
	
	public PanelDatos(InterfazPrincipal principal){
		interfaz = principal;
		
		TitledBorder border = 
				BorderFactory.createTitledBorder("Canciones aleatorias");
		setBorder(border);
		setLayout(new GridLayout(2, 2));
		
		init();
		
		add(butArchivo);
		add(txtRutaArchivo);
		add(butLimpiar);
	}
	
	private void init() {
		//Botones
		butLimpiar = new JButton(LIMPIAR);
		butLimpiar.setActionCommand(LIMPIAR);
		butLimpiar.addActionListener(this);
		
		butArchivo = new JButton(ARCHIVO);
		butArchivo.setActionCommand(ARCHIVO);
		butArchivo.addActionListener(this);
				
		
		//TxtFields
		txtRutaArchivo = new JTextField();
		txtRutaArchivo.setEditable(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String evento = arg0.getActionCommand();
		if (evento.equals(ARCHIVO)) {
			String ruta = interfaz.elegirArchivo();
			txtRutaArchivo.setText(ruta);
		}else if (evento.equals(LIMPIAR)) {
			interfaz.limpiar();
		}

	}	
}
