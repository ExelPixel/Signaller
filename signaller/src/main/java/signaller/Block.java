package signaller;

import java.util.Map;
import java.util.Objects;

public class Block {
    
    private final int id;
    private final int stationId;
    private final int routeId;
    private Map<String, Block> neighbors; //LEFT, RIGHT, UPRIGHT, UPLEFT / Block object
    private boolean occupied = false;
    //private String signal; //RED, YELLOW, GREEN - example
    private String nextDirection;

    public Block(int id, int routeId, int stationId, String direction) {
        this.id = Objects.requireNonNull(id);
        this.routeId = Objects.requireNonNull(routeId);
        this.stationId = stationId;
        this.nextDirection = direction;
    }

    public void addNeighbor(Block nextBlock) {
        neighbors.put(nextDirection, nextBlock);
    }

    public int getId() {
        return id;
    }
}
