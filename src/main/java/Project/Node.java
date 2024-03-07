package project;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> children;
    private Node parent;
    private GameState state;
    private int action;
    private double reward;
    private int visits;


    public Node(Node parent, GameState state, int action) {
        this.children = new ArrayList<>();
        this.parent = parent;
        this.state = state;
        this.action = action;
        this.reward = 0;
        this.visits = 0;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    public GameState getState() {
        return state;
    }

    public int getAction() {
        return action;
    }

    public double getReward() {
        return reward;
    }

    public int getVisits() {
        return visits;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public void addVisits() {
        visits++;
    }

    public void addReward(int result) {
        reward += result;
    }
}
