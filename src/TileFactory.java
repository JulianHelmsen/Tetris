

public class TileFactory {

	public static Tile getRandomTile() {
		int tileId = (int) (Math.random() * 5);
		return getTile(tileId);
	}

	public static Tile getTile(int tileId) {
		Tile tile = null;
		switch(tileId) {
			case 0:
				tile = new Tile(2, 2);
				tile.setCenter(0.5f, 0.5f);
				tile.enableBlock(0, 0);
				tile.enableBlock(1, 0);
				tile.enableBlock(0, 1);
				tile.enableBlock(1, 1);
				tile.setColor(Color.YELLOW);
				break;
			case 1:
				tile = new Tile(4, 1);
				tile.setCenter(1.5f, 0.5f);
				tile.enableBlock(0, 0);
				tile.enableBlock(1, 0);
				tile.enableBlock(2, 0);
				tile.enableBlock(3, 0);
				tile.setColor(Color.BLUE);
				break;
			case 2:
				tile = new Tile(3, 2);
				tile.setCenter(1f, 0.0f);
				tile.enableBlock(0, 0);
				tile.enableBlock(1, 0);
				tile.enableBlock(2, 0);
				tile.enableBlock(1, 1);
				tile.setColor(Color.PURPLE);
				break;
			case 3:
				tile = new Tile(2, 3);
				tile.setCenter(0, 1f);
				tile.enableBlock(0, 0);
				tile.enableBlock(0, 1);
				tile.enableBlock(0, 2);
				tile.enableBlock(1, 2);
				tile.setColor(Color.ORANGE);
				break;
			case 4:
				tile = new Tile(2, 3);
				tile.setCenter(0f, 1f);
				tile.enableBlock(0, 0);
				tile.enableBlock(0, 1);
				tile.enableBlock(1, 1);
				tile.enableBlock(1, 2);
				tile.setColor(Color.GREEN);
				break;
		}
		return tile;
	}
}