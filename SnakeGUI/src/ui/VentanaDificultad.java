package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VentanaDificultad {

	private static int dificultad = 0;
	private JFrame frmDificultad;
	private JLabel lblDificultad;
	private JButton btnFacil;
	private JButton btnNormal;
	private JButton btnDificil;
	private JButton btnImposible;

	public VentanaDificultad() {
		initialize();
		this.frmDificultad.setVisible(true);
	}
	
	private void initialize() {
		frmDificultad = new JFrame();
		configureUIComponents();
		configureListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void configureUIComponents() {
		frmDificultad = new JFrame();
		frmDificultad.setTitle("Dificultad");
		frmDificultad.setBounds(100, 100, 450, 300);
		frmDificultad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDificultad.getContentPane().setLayout(null);
		
		lblDificultad = new JLabel("ELIGE DIFICULTAD");
		lblDificultad.setHorizontalAlignment(SwingConstants.CENTER);
		lblDificultad.setForeground(Color.BLACK);
		lblDificultad.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDificultad.setEnabled(false);
		lblDificultad.setBounds(78, 34, 276, 34);
		frmDificultad.getContentPane().add(lblDificultad);
		
		btnFacil = new JButton("Facil");
		btnFacil.setBounds(78, 126, 89, 23);
		frmDificultad.getContentPane().add(btnFacil);
		
		btnNormal = new JButton("Normal");
		btnNormal.setBounds(265, 126, 89, 23);
		frmDificultad.getContentPane().add(btnNormal);
		
		btnDificil = new JButton("Dificil");
		btnDificil.setBounds(78, 186, 89, 23);
		frmDificultad.getContentPane().add(btnDificil);
		
		btnImposible = new JButton("Imposible");
		btnImposible.setBounds(265, 186, 89, 23);
		frmDificultad.getContentPane().add(btnImposible);
	}

	/**
	 * Inputs de los botones.
	 */
	
	private void configureListeners() {
		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultad = 1;
				frmDificultad.dispose();
			}
		});
		
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultad = 2;
				frmDificultad.dispose();
			}
		});
		
		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultad = 3;
				frmDificultad.dispose();
			}
		});
		
		btnImposible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dificultad = 4;
				frmDificultad.dispose();
			}
		});
	}

	public static int getDificultad() {
		return dificultad;
	}
	
	
}
