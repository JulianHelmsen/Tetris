import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.HashSet;

public class Window implements MouseListener, KeyListener {

	private JFrame jframe;

	private HashSet<Integer> pressedKeys = new HashSet<Integer>();

	public Window(String title, int width, int height) {
		this.jframe = new JFrame();
		this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jframe.setSize(width, height);
		this.jframe.setTitle(title);
		this.jframe.add(new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Main.draw((Graphics2D) g, this.getBounds());
			}
		});
		this.jframe.addMouseListener(this);
		this.jframe.addKeyListener(this);
		this.jframe.setLocationRelativeTo(null);
		this.jframe.setVisible(true);
	}

	public void repaint() {
		this.jframe.repaint();
	}

	public int getWidth() {
		return this.jframe.getWidth();
	}

	public int getHeight() {
		return this.jframe.getHeight();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.pressedKeys.add(e.getKeyCode());
		Main.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.pressedKeys.remove(e.getKeyCode());
	}

	public boolean isKeyPressed(int keycode) {
		return this.pressedKeys.contains(keycode);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Main.mousePressed(e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}