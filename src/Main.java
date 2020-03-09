import java.awt.*;


public class Main {

	public static Window window = new Window("Tetris", 720 / 2, 720);
	private static final BasicStroke STROKE = new BasicStroke(3);

	private static boolean gameOver = true;

	private static Color[][] grid = new Color[10][20];

	private static Tile tile;

	public static void draw(Graphics2D g2d, Rectangle bounds) {
		if(gameOver) {

			g2d.setColor(java.awt.Color.BLACK);
			g2d.setFont(new Font("Cooper Black", Font.PLAIN, 24));
			java.awt.FontMetrics metrics = g2d.getFontMetrics();
			String message = "Press any key to restart";
			int x = (int) (bounds.getX() + (bounds.getWidth() - metrics.stringWidth(message)) / 2);
			int y = (int) (bounds.getY() + ((bounds.getHeight() / 4 * 3 - metrics.getHeight()) / 2 + metrics.getAscent()));
			
			g2d.drawString(message, x, y);
			return;
		}


		g2d.setStroke(STROKE);
		final int cellSize = Math.min((int) bounds.getWidth() / grid.length, (int) bounds.getHeight() / grid[0].length);

		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				if(grid[x][y] != null) {
					g2d.setColor(grid[x][y].getColor());
					g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
				}
				g2d.setColor(java.awt.Color.BLACK);
				g2d.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
			}
		}

		tile.draw(g2d, cellSize);
	}


	public static void keyPressed(int key) {
		if(gameOver) {
			restart();
		}else if(key == 'D') {
			if(tile.canMove(grid, 1, 0)) {
				tile.translate(1, 0);
			}
		}else if(key == 'A') {
			if(tile.canMove(grid, -1, 0)) {
				tile.translate(-1, 0);
			}
		}else if(key == 'W') {
			if(tile.canRotate(grid, 1)) {
				tile.rotate(1);
			}
		}else if(key == 'S') {
			if(tile.canRotate(grid, -1)) {
				tile.rotate(-1);
			}
		}
		window.repaint();
	}

	private static void replaceFullRows() {
		int down = 0;
		for(int y = grid[0].length - 1; y >= 0; y--) {
			boolean full = true;
			for(int x = 0; x < grid.length; x++) {
				if(grid[x][y] == null) {
					full = false;
				}else if(down != 0) {
					grid[x][y + down] = grid[x][y];
					grid[x][y] = null;
				}
			}

			if(full) {
				for(int x = 0; x < grid.length; x++) {
					grid[x][y] = null;
				}
				y++;
				down++;
			}
		}
	}

	private static void restart() {
		gameOver = false;
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				grid[x][y] = null;
			}
		}

		tile = TileFactory.getRandomTile();
		tile.setPosition(5, 0);
	}

	private static void run() {
		if(gameOver) return;
		if(tile.canMove(grid, 0, 1)) {
			tile.translate(0, 1);
		}else{
			if(tile.solidify(grid)) {
				gameOver = true;
			}else{
				replaceFullRows();
				tile = TileFactory.getRandomTile();
				tile.setPosition(5, 0);
			}
		}
	}


	public static void main(final String[] args) {
		

		while(true) {
			try{
				Thread.sleep(window.isKeyPressed(java.awt.event.KeyEvent.VK_SPACE) ? 100 : 800);
			}catch(Exception e) {}
			
			run();
			window.repaint();
		}
	}
}
