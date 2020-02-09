import java.awt.Graphics2D;
import java.awt.Point;

public class Tile {

	private float centerX, centerY;
	private int posX, posY;

	private boolean[][] blocks;
	private Color color;

	private int rotation = 0;

	public Tile(int width, int height) {
		this.blocks = new boolean[width][height];
	}

	public void enableBlock(int x, int y) {
		this.blocks[x][y] = true;
	}

	public void setCenter(float x, float y) {
		this.centerX = x;
		this.centerY = y;
	}

	public void draw(Graphics2D g2d, int cellSize) {
		g2d.setColor(this.color.getColor());

		for(int x = 0; x < this.blocks.length; x++) {
			for(int y = 0; y < this.blocks[x].length; y++) {
				if(!this.blocks[x][y]) continue;
				Point pos = this.getTransformedPosition(x, y);
				g2d.fillRect(pos.x * cellSize, pos.y * cellSize, cellSize, cellSize);
			}
		}
	}

	public Point getTransformedPosition(int x, int y) {
		final double relativeX = x - this.centerX;
		final double relativeY = y - this.centerY;

		final double destX = relativeX * Math.cos(this.getRotation()) - relativeY * Math.sin(this.getRotation());
		final double destY = relativeX * Math.sin(this.getRotation()) + relativeY * Math.cos(this.getRotation());
		return new Point((int) Math.round(destX - this.centerX + this.posX), (int) Math.round(destY - this.centerY + this.posY));
	}

	public void setPosition(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void rotate(int dir) {
		this.rotation += dir;
		if(this.rotation < 0) {
			this.rotation += 4;
		}else if(this.rotation > 3) {
			this.rotation = 0;
		}
	}

	public float getRotation() {
		return (float) Math.PI / 2 * this.rotation;
	}

	public boolean canRotate(Color[][] grid, int rotation) {
		this.rotation += rotation;
		for(int x = 0; x < this.blocks.length; x++) {
			for(int y = 0; y < this.blocks[x].length; y++) {
				if(!this.blocks[x][y]) continue;
				Point pos = this.getTransformedPosition(x, y);
				if(pos.x < 0 || pos.x >= grid.length || pos.y >= grid[0].length || (pos.y >= 0 && grid[pos.x][pos.y] != null)) {
					this.rotation -= rotation;
					return false;
				}
			}
		}
		this.rotation -= rotation;

		return true;
	}

	public boolean canMove(Color[][] grid, int xDir, int yDir) {
		for(int x = 0; x < this.blocks.length; x++) {
			for(int y = 0; y < this.blocks[x].length; y++) {
				if(!this.blocks[x][y]) continue;
				Point pos = this.getTransformedPosition(x, y);
				pos.x += xDir;
				pos.y += yDir;
				if(pos.x < 0 || pos.x >= grid.length || pos.y >= grid[0].length || (pos.y >= 0 && grid[pos.x][pos.y] != null)) {
					return false;
				}
			}
		}
		return true;
	}

	public void translate(int x, int y) {
		this.posX += x;
		this.posY += y;
	}

	public boolean solidify(Color[][] grid) {
		boolean gameOver = false;
		for(int x = 0; x < this.blocks.length; x++) {
			for(int y = 0; y < this.blocks[x].length; y++) {
				if(!this.blocks[x][y]) continue;
				Point pos = this.getTransformedPosition(x, y);
				if(pos.y < 0) {
					gameOver = true;
				}else{
					grid[pos.x][pos.y] = this.color;
				}
			}
		}
		return gameOver;
	}

}