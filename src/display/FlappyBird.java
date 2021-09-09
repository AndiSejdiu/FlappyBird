package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
	public static String name = "FlappyBird";
	public static final int WIDTH=600;
	public static final int HEIGHT=550;
	Timer t = new Timer(5, this);
	double y = 200;
	double g = 0.7;
	double v = 0;
	int speed = 1;
	boolean Loja = true;
	int A[] = new int[4];
	int B[] = { 460, 620, 780, 940 };
	int count = 0;
	Image image = Toolkit.getDefaultToolkit().getImage("res/png.png");
	Image image1 = Toolkit.getDefaultToolkit().getImage("res/png1.png");
	Image image2 =   Toolkit.getDefaultToolkit().getImage("res/png2.png");
	Image image3 = Toolkit.getDefaultToolkit().getImage("res/png3.png");
	Image image4 = Toolkit.getDefaultToolkit().getImage("res/png4.png");
	Image Backround = Toolkit.getDefaultToolkit().getImage("res/Backround.png");

	public FlappyBird() {
		Dimension size=new Dimension(WIDTH,HEIGHT);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		addKeyListener(this);
		setFocusable(true);
		for (int i = 0; i < 4; i++) {
			A[i] = 120 + (int) (Math.random() * 1000) % 220;

		}
	}

	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		gr.setColor(new Color(0, 102, 51));
		Graphics2D g2 = (Graphics2D) gr;

		g2.drawImage(Backround, 0, 0, this);

		for (int i = 0; i < 4; i++) {
			g2.drawImage(image3, B[i] - speed, A[i] - 345, this);
			g2.drawImage(image4, B[i] - speed, A[i] + 100, this);
		}

		if (v > -1)
			g2.drawImage(image1, 100, (int) y, this);
		else if (v == -1)
			g2.drawImage(image, 100, (int) y, this);
		else
			g2.drawImage(image2, 100, (int) y, this);
		g2.setFont(new Font("Verdana", 1, 30));
		gr.setColor(Color.BLACK);
		g2.drawString("" + count, 0, 30);
	}

	public void actionPerformed(ActionEvent e) {
		if (Loja == true) {
			repaint();
			v += g;
			y += v;
			speed += 2;
			for (int i = 0; i < 4; i++) {
				if (B[i] - speed + 40 < 0) {
					B[i] += 650;
					A[i] = 100 + (int) (Math.random() * 1000) % 200;
				}
				if (((B[i] - speed <= 160 && B[i] - speed >= 110) && (y < A[i] - 5 || y > A[i] + 65)) || y > 515) {
					Loja = false;
				}
				if (B[i] - speed == 129) {
					count++;
				}  
			}
		}
	}


	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if ((code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE) && Loja == true) {
			v=-8;
		}
		if (code == KeyEvent.VK_SPACE) {
			t.start();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		FlappyBird game = new FlappyBird();
		frame.add(game);
		frame.pack();

		frame.setTitle(name);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
