package jogo.sustentabilidade;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase3 extends JPanel implements ActionListener {

	private static final long serialVersionUID = -5079021684583630134L;

	private Image fundo;

	private Nave nave;

	private Timer timer;

	private List<Inimigo> inimigos;

	private boolean emJogo;

	private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },
			{ 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, {670, 40}, {680, 30} };

	public Fase3() {
		setSize(1200, 650);
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());

		ImageIcon referencia = new ImageIcon(Main.class.getResource("/res/latas-luminaria-reciclada.jpg"));
		fundo = referencia.getImage();
		
		nave = new Nave();

		emJogo = true;

		inicializarInimigos();

		timer = new Timer(6, this);
		timer.start();
	}

	public void inicializarInimigos() {
		inimigos = new ArrayList<>();

		for (int i = 0; i < coordenadas.length; i++) {
			inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);

		if (emJogo) {
			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

			List<Missel> misseis = nave.getMisseis();

			for (int i = 0; i < misseis.size(); i++) {
				Missel m = (Missel) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int i = 0; i < inimigos.size(); i++) {
				Inimigo in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}

			graficos.setColor(Color.white);
			graficos.drawString("Inimigos: " + inimigos.size(), 5, 25);

		} else {
			ImageIcon fimJogo = new ImageIcon(Main.class.getResource("/res/game_over.jpg"));

			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (inimigos.size() == 0) {
			emJogo = false;
		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {
			Missel m = (Missel) misseis.get(i);

			if (m.isVisible()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}
		}

		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo in = inimigos.get(i);

			if (in.isVisible()) {
				in.mexer();
			} else {
				inimigos.remove(i);
			}
		}

		nave.mexer();
		checarColisoes();
		repaint();
	}

	public void checarColisoes() {
		Rectangle formaNave = nave.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMissel;

		for (int i = 0; i < inimigos.size(); i++) {

			Inimigo tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaNave.intersects(formaInimigo)) {
				nave.setVisivel(false);
				tempInimigo.setVisible(false);
				emJogo = false;
			}
		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {
			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {
				Inimigo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaMissel.intersects(formaInimigo)) {
					tempInimigo.setVisible(false);
					tempMissel.setVisible(false);
				}
			}
		}
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				emJogo = true;
				nave = new Nave();
				inicializarInimigos();
			}

			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}
	}
}
