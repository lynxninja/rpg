package rpg.renderengine;
import rpg.dictionaries.*;
import rpg.*;

public class World {

    private int wXWidth = 16;
    private int wYHeight = 16;
    private String wName = "";
    public Character[][] wGeometry = new Character[wXWidth][wYHeight];;
    private Actor[][] wActors = new Actor[wXWidth][wYHeight];

    public World(String name) {
        wName = name;
        generateRandomWorld();
    }

    private void generateRandomWorld() {
        // Generate geometry
        for (int i = 0; i < wGeometry.length; i++) {
            for (int j = 0; j < wGeometry[i].length; j++) {
                if (Math.round(Math.random() * 10) > 8) {
                    wGeometry[i][j] = CharDefinitions.cWater;
                } else {
                	wGeometry[i][j] = CharDefinitions.cGround;
                }
            }
        }

        
        // 2 to 5 enemies
        int enemiesToGenerate = (int) ((Math.random() * 4) + 2);

        for (int i = 0; i < enemiesToGenerate; i++) {
        	while (true) {
				int actorX = (int) (Math.random() * wXWidth);
				int actorY = (int) (Math.random() * wYHeight);
				
				// Don't try to put two enemies on the same tile
				if (wActors[actorX][actorY] != null) {
					continue;
				} else {
					wActors[actorX][actorY] = EnemyDict.GoblinEnemy;
					break;
				}
        	}
        }
    }

    public Character[][] getGeometry() {
        return wGeometry;
    }

    public Actor[][] getActors() {
        return wActors;
    }

    public Character queryWorld(int x, int y) {
        return wGeometry[x][y];
    }

    public boolean queryInBound(int x, int y) {
        if (x > wXWidth || x < wXWidth) {
            return false;
        } else {
            if (y > wYHeight || y < wYHeight) {
                return false;
            } else {
                return true;
            }
        }
    }

    public int getWidth() {
        return wXWidth;
    }

    public int getHeight() {
        return wYHeight;
    }

    public String getName() {
        return wName;
    }

}