package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaConfigSize {

	private static int x;
	private static int y;
	private JFrame frmConfigSize;
	private JLabel lblConfigSize;
	private JLabel lblRecomendacion;
	private JTextField tfCoordX;
	private JTextField tfCoordY;
	private JButton btnConfirmar;

		/**
	 * Create the application.
	 */
	public VentanaConfigSize() {
		initialize();
		this.frmConfigSize.setVisible(true);
	}

	public void initialize() {
		frmConfigSize = new JFrame();
		configureUIComponents();
		configureListeners();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void configureUIComponents() {
		frmConfigSize = new JFrame();
		frmConfigSize.setTitle("Configurar tama\u00F1o");
		frmConfigSize.setBounds(100, 100, 450, 300);
		frmConfigSize.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConfigSize.getContentPane().setLayout(null);
		
		lblConfigSize = new JLabel("Configura el tama\u00F1o");
		lblConfigSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfigSize.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblConfigSize.setBounds(10, 11, 414, 29);
		frmConfigSize.getContentPane().add(lblConfigSize);
		
		lblRecomendacion = new JLabel("(Tama\u00F1o recomendado: 600x600)");
		lblRecomendacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecomendacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRecomendacion.setBounds(10, 51, 414, 29);
		frmConfigSize.getContentPane().add(lblRecomendacion);
		
		tfCoordX = new JTextField();
		tfCoordX.setBounds(75, 118, 86, 20);
		frmConfigSize.getContentPane().add(tfCoordX);
		tfCoordX.setColumns(10);
		
		tfCoordY = new JTextField();
		tfCoordY.setColumns(10);
		tfCoordY.setBounds(281, 118, 86, 20);
		frmConfigSize.getContentPane().add(tfCoordY);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(175, 186, 89, 23);
		frmConfigSize.getContentPane().add(btnConfirmar);
	}
	
	public void configureListeners() {
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x = Integer.parseInt(tfCoordX.getText());
				y = Integer.parseInt(tfCoordY.getText());
				frmConfigSize.dispose();
			}
		});
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}
	
	
}
