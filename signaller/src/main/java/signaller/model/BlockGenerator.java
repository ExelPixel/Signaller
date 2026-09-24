package signaller.model;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class BlockGenerator {
    
    private HashSet<Integer> ids;
    private final Random rand;
    private final List<String> directions;

    public BlockGenerator() {
        ids = new HashSet<>();
        rand = new Random();
        directions = List.of("LEFT", "UPLEFT", "UP", "UPRIGHT", "RIGHT", "DOWNRIGHT", "DOWN", "DOWNLEFT");
    }

    public HashSet<Block> generateBlocks(int amount) {
        HashSet<Block> blocks = new HashSet<>();
        String prevDirection = "RIGHT";
        String nextDirection = "";
        Block previousBlock = new Block(getValidId(), 0, 0, prevDirection);;

        for (int i = 0; i < amount ; i++) {
            nextDirection = getNextDirection(prevDirection);
            Block nextBlock = new Block(getValidId(), 0, 0, nextDirection);
            previousBlock.addNeighbor(nextBlock);
            blocks.add(nextBlock);
            previousBlock = nextBlock;
            prevDirection = nextDirection;
        }
        return blocks;
    }

    private void buildStraightRoute(int amount) {
        // TODO
    }

    private void buildPassingLoopStation() {
        // TODO
    }

    private void buildStation() {
        // TODO
    }

    private String getNextDirection(String prevDirection) {
        int currentIndex = directions.indexOf(prevDirection);
        String direction;
        int randIndex;
        
        while (true) {
            randIndex = rand.nextInt(8);
            if (randIndex == currentIndex) {
                continue;
            } else {
                break;
            }
        }

        if (randIndex > currentIndex) {
            direction = directions.get(currentIndex + 1);
        } else {
            direction = directions.get(currentIndex - 1);
        }
        return direction;
    }

    private int getValidId() {
        while (true) {
            int id = rand.nextInt(10000);
            if (!ids.contains(id)) {
                ids.add(id);
                return id;
            } else {
                continue;
            }
        }
    }
}
