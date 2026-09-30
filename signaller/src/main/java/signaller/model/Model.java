package signaller.model;

import java.util.HashSet;

public class Model {
    
    private boolean debug;
    private HashSet<Block> blockSet;
    private Block firstBlock;

    private final BlockGenerator blockGen;

    public Model() {
        debug = false;
        blockSet = new HashSet<>();
        blockGen = new BlockGenerator();
    }

    public void initializeRailway() {
        blockSet = blockGen.generateBlocks(500);
        firstBlock = blockGen.getFirstBlock();

        if (debug) {
            for (Block block : blockSet) {
            System.out.println(block.getId());
            }
        }
    }

    public HashSet<Block> getBlockset() {
        return blockSet;
    }

    public Block getFirstBlock() {
        return firstBlock;
    }

    public void changeDebugMode() {
        if (debug == false) {
            debug = true;
        } else {
            debug = false;
        }
    }
}
