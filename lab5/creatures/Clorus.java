package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Spike
 * @Description
 * @Date 2023/6/19
 */
public class Clorus extends Creature {
    private int energy;
    Color color = new Color(34,0,231);

    /**
     * Creates a creature with the name N. The intention is that this
     * name should be shared between all creatures of the same type.
     *
     *
     */
    public Clorus() {
        super("clorus");
        energy = 0;
    }

    public Clorus(int energy){
        super("colrus");
        this.energy = energy;
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Creature replicate() {
        energy = energy / 2;
        return new Clorus(energy);
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> emptyNeighbors = new ArrayList<>();
        List<Direction> plipNeighbors = new ArrayList<>();
        Random random = new Random();
        for (Direction direction : neighbors.keySet()){
            Occupant occupant = neighbors.get(direction);
            if (occupant instanceof Empty){
                emptyNeighbors.add(direction);
            }
            if (occupant instanceof Plip){
                plipNeighbors.add(direction);
            }
        }
        if (emptyNeighbors.size() == 0){
            return new Action(Action.ActionType.STAY);
        }
        if (!plipNeighbors.isEmpty()){
            int i = random.nextInt(plipNeighbors.size());
            Direction direction = plipNeighbors.get(i);
            return new Action(Action.ActionType.ATTACK,direction);
        }
        if (energy >= 1){
            int i = random.nextInt(emptyNeighbors.size());
            Direction direction = emptyNeighbors.get(i);
            return new Action(Action.ActionType.REPLICATE,direction);
        }
        int i = random.nextInt(emptyNeighbors.size());
        Direction direction = emptyNeighbors.get(i);
        return new Action(Action.ActionType.MOVE,direction);
    }

    @Override
    public Color color() {
        return color;
    }
}
