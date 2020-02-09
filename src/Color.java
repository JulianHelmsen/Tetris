
public enum Color {

	RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255), YELLOW(255, 255, 0), PURPLE(200, 50, 245), ORANGE(235, 158, 50);

	private java.awt.Color color;

	Color(int r, int g, int b) {
		this.color = new java.awt.Color(r, g, b);
	}

	public java.awt.Color getColor() {
		return this.color;
	}
}