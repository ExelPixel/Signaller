package signaller;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class BlockGenerator {
    
    private HashSet<Integer> ids;
    private final List<String> directions;

    public BlockGenerator() {
        ids = new HashSet<>();
        directions = List.of("LEFT", "UPLEFT", "UP", "UPRIGHT", "RIGHT", "DOWNRIGHT", "DOWN", "DOWNLEFT");
    }

    public HashSet<Block> generateBlocks(int amount) {
        HashSet<Block> blocks = new HashSet<>();
        Random rand = new Random();
        Block previousBlock = new Block(getValidId(rand), 0, 0, "RIGHT");;
        
        for (int i = 0; i < amount ; i++) {
            Block nextBlock = new Block(getValidId(rand), 0, 0, directions.get(rand.nextInt(8)));
            previousBlock.addNeighbor(nextBlock);
            blocks.add(nextBlock);
            previousBlock = nextBlock;
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


    private int getValidId(Random rand) {
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
