package signaller.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Block {
    
    private final int id;
    private final int stationId;
    private final int routeId;
    private Map<Block, String> neighbors; //LEFT, RIGHT, UPRIGHT, UPLEFT / Block object
    private boolean occupied = false;
    private String nextDirection;

    public Block(int id, int routeId, int stationId, String direction) {
        this.id = Objects.requireNonNull(id);
        this.routeId = Objects.requireNonNull(routeId);
        this.stationId = stationId;
        this.nextDirection = direction;
        this.neighbors = new HashMap<Block, String>();
    }

    public void addNeighbor(Block nextBlock) {
        neighbors.put(nextBlock, nextDirection);
    }

    public int getId() {
        return id;
    }

    public String getNextDirection() {
        return nextDirection;
    }
}
