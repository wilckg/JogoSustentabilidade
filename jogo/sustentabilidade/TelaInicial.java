package jogo.sustentabilidade;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


		
		public class TelaInicial  extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6892967292776534254L;
	private Image imagemInicial;
	
	public TelaInicial(){
		ImageIcon referencia = new ImageIcon(getClass().getResource("/res/gari.jpg"));
		imagemInicial = referencia.getImage();	
	}
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(imagemInicial, 0, 0, null);
		g.dispose();
	}

}
