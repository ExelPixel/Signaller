package signaller.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Block {
    
    private final Integer id;
    private final int stationId;
    private final int routeId;
    private Map<Block, String> neighbors; //LEFT, RIGHT, UPRIGHT, UPLEFT / Block object
    private boolean occupied = false;

    public Block(int id, int routeId, int stationId) {
        this.id = Objects.requireNonNull(id);
        this.routeId = Objects.requireNonNull(routeId);
        this.stationId = stationId;
        this.neighbors = new HashMap<Block, String>();
    }

    public void addNeighbor(Block nextBlock, String nextDirection) {
        neighbors.put(nextBlock, nextDirection);
    }

    public Integer getId() {
        return id;
    }

    public Map<Block, String> getNeighbors() {
        return neighbors;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
