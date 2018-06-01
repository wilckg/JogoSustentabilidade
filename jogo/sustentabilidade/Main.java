package jogo.sustentabilidade;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Main extends JFrame {
	
	URL som = Main.class.getResource("/res/Chrono.wav");
	AudioClip Som = Applet.newAudioClip(som);
	/**
	 * Serial gerado automaticamente apenas para efeito de serialização
	 */
	private static final long serialVersionUID = 1L;

	public Main() {
		construirMenuBar();
		construirFase();
		configurarTela();
	}

	public static void main(String[] args) {
		new Main();
		Main tocar = new Main();
		tocar.Som.play();
	}

	private JMenuBar construirMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBorder(new LineBorder(Color.red));
		JMenu menu = new JMenu("Opções");
		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Desenvolvido para a APS do 3º semestre da Matéria de LPOO - Versão 0.1 2018",
								"Informações", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		menu.add(sobre);
		menu.add(new JSeparator());
		menu.add(sair);
		menuBar.add(menu);
		setJMenuBar(menuBar);

		return menuBar;
	}

	private JPanel construirFase() {
		Fase fase = new Fase();
		add(fase);
		return fase;
	}

	private JPanel construirFase2() {
		Fase2 fase2 = new Fase2();
		add(fase2);
		return fase2;
	}

	private JPanel construirFase3() {
		Fase3 fase3 = new Fase3();
		add(fase3);
		return fase3;
	}

	
	private void configurarTela() {
		setSize(1100, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Coletor de Lixo");
	}
}
