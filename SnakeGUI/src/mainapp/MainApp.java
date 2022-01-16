package mainapp;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.Manzana;
import models.Serpiente;
import models.TableroJuego;
import ui.ControlTeclado;
import ui.MyButtonListener;
import ui.MySnakeFrame;
import ui.VentanaConfigSize;
import ui.VentanaDificultad;

public class MainApp {

	public static void main(String[] args) throws InterruptedException {
		// TODO code application logic here

		double contador;
		double velocidad = 1;
		MySnakeFrame frame;
		JPanel mainPanel;
		TableroJuego tablero;
		JPanel botonera;
		JLabel puntos;
		JLabel puntosNum;
		JButton start;
		JButton pause;
		ControlTeclado miControlador;
		

		// 1. Crear el frame.
		frame = new MySnakeFrame();

		// asignamos el tamaño a nuestra ventana, y hacemos que se cierre cuando nos
		// pulsan
		// la X de cerrar la ventana
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Ahora creamos los componentes y los ponemos en la frame (ventana).

		// El panel de fondo. Rellena el frame, y sirve de contenedor del tablero y de
		// la botonera.
		mainPanel = new JPanel(new BorderLayout());

		// Ahora creamos el tablero. Recordamos: no deja de ser un panel un poquito
		// "especial"
		tablero = new TableroJuego();

		// Les damos las propiedades a nuestro tablero. Su color, tamaño y borde
		tablero.setBorder(BorderFactory.createLineBorder(Color.black));
		tablero.setBackground(new java.awt.Color(25, 25, 25));
		tablero.setSize(600, 400);

		// Le damos un enlace al tablero para que sepa quién es su frame (ventana) y así
		// sepa
		// quién contiene la serpiente y quién controla el juego...
		tablero.setSnakeFrame(frame);

		// Ahora el turno de la botonera. Tendrá los dos botones y las etiquetas de
		// texto
		botonera = new JPanel();
		botonera.setBorder(BorderFactory.createLineBorder(Color.black));
		botonera.setBackground(new java.awt.Color(150, 150, 150));

		// Ahora definimos las dos etiquetas para los puntos.
		puntos = new JLabel();
		puntos.setText("Puntos: ");
		puntos.setBackground(new java.awt.Color(190, 190, 190));

		puntosNum = new JLabel();
		puntosNum.setText("0");
		puntosNum.setBackground(new java.awt.Color(190, 190, 190));

		// turno de los botones de empezar y pausar/continuar
		start = new JButton();
		start.setSize(50, 20);
		start.setText("Start");
		start.addActionListener(new MyButtonListener(frame, tablero));

		pause = new JButton();
		pause.setSize(50, 20);
		pause.setText("Pause");
		pause.addActionListener(new MyButtonListener(frame, tablero));

		// Preparamos el control del teclado
		miControlador = new ControlTeclado();
		miControlador.setSnakeFrame(frame); // le damos al controlador de teclado un enlace el frame principal
		tablero.addKeyListener(miControlador); // le decimos al tablero que el teclado es cosa de nuestro controlador
		tablero.setFocusable(true); // permitimos que el tablero pueda coger el foco.

		// Añadimos los componentes uno a uno, cada uno en su contenedor, y al final el
		// panel principal
		// se añade al frame principal.
		botonera.add(start);
		botonera.add(pause);
		botonera.add(puntos);
		botonera.add(puntosNum);

		mainPanel.add(botonera, BorderLayout.PAGE_END);
		mainPanel.add(tablero, BorderLayout.CENTER);
		frame.add(mainPanel);

		frame.setVisible(true); // activamos la ventana principal para que sea "pintable"
		
		// Ventana de selector de tama�o
		new VentanaConfigSize();

		// Ventana de dificultad
		new VentanaDificultad();

		contador = 0; // nuestro control de los pasos del tiempo. Cada vez que contador cuenta un
						// paso, pasan 10ms
		
		new Manzana(100, 100, 20);

		while (true) { // por siempre jamás (hasta que nos cierren la ventana) estamos controlando el
						// juego.
			
			frame.setSize(VentanaConfigSize.getX()+20,VentanaConfigSize.getY()+80);
			tablero.setSize(VentanaConfigSize.getX(),VentanaConfigSize.getY());
			
			deParedAPared();
			
			switch (VentanaDificultad.getDificultad()) {
			case 1:
				velocidad = 1;
				break;
			case 2:
				velocidad = 10;
				break;
			case 3:
				velocidad = 15;
				break;
			case 4:
				velocidad = 20;
				break;
			}

			// actualizamos el estado del juego
			if (contador % 20 == 0) { // cada 200ms nos movemos o crecemos...
				if ((Serpiente.listaCuadrados.get(0).getX() == Manzana.getPosX()) && (Serpiente.listaCuadrados.get(0).getY() == Manzana.getPosY())) { // Cada 600ms crecemos y reseteamos el contador
					contador = 0;
					frame.tocaCrecer();
					// hemos crecido... actualizamos puntos.
					puntosNum.setText(Integer.toString(frame.getSerpiente().getPuntos()));
					MySnakeFrame.manzana.setPosX((int) ((Math.random()*(VentanaConfigSize.getX())/20))*20);
					MySnakeFrame.manzana.setPosY((int) ((Math.random()*(VentanaConfigSize.getY())/20))*20);
				} else { // a los 200 y 400 ms nos movemos...
					contador = contador + velocidad;
					frame.tocaMoverse();
				}
				frame.comprobarEstado(); // comprobamos si hemos muerto o no.

			} else { // Cada vez que no hay que moverse o crecer, simplemente contamos...
				contador++;
			}

			// hemos terminado?? mostramos msg
			if (frame.mostrarFin()) {
				JOptionPane.showMessageDialog(frame,
						"Se acabo vaquero, has conseguido " + puntosNum.getText() + " puntos");
			}

			// Repintamos
			tablero.repaint();

			// Esperamos para dar tiempo al thread de repintado a pintar.
			Thread.sleep(10);

		}
	}
	
	public static void deParedAPared() {
		if (Serpiente.listaCuadrados.get(0).getX() < 0) {
			Serpiente.listaCuadrados.get(0).setPosX(VentanaConfigSize.getX());
		} else if (Serpiente.listaCuadrados.get(0).getX() > VentanaConfigSize.getX()) {
			Serpiente.listaCuadrados.get(0).setPosX(0);
		} else if (Serpiente.listaCuadrados.get(0).getY() < 0) {
			Serpiente.listaCuadrados.get(0).setPosY(VentanaConfigSize.getY());
		} else if (Serpiente.listaCuadrados.get(0).getY() > VentanaConfigSize.getY()) {
			Serpiente.listaCuadrados.get(0).setPosY(0);
		}
	}

}
