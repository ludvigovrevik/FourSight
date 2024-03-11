package project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MCTS {
    private double c;
    private Random random = new Random();

    public MCTS() {
        c = 1.43;
    }

    public double UCB(Node node) {
        if (node.getVisits() == 0) {
            return 10000000.0;
        }
        return (node.getReward() / node.getVisits())
                + this.c * (Math.log(node.getParent().getVisits()) / node.getVisits());
    }

    public Node select(Node node) {
        // System.out.println("Selecting node");
        double highestUCB = -10000000;
        Node bestNode = null; // Initialize bestNode variable
        Node currentNode = node;

        while (currentNode.hasChildren()) {
            for (Node child : currentNode.getChildren()) {
                double currentUCB = this.UCB(child);
                if (currentUCB > highestUCB) {
                    highestUCB = currentUCB;
                    bestNode = child;
                }
            }
            currentNode = bestNode;
            highestUCB = -10000000;
        }
        return currentNode;
    }

    public void expand(Node node) {
        // System.out.println("Expanding node");
        List<Integer> legalActions = node.getState().legalActions();
        for (Integer action : legalActions) {
            GameState newState = node.getState().clone();
            newState.applyAction(action);
            node.addChild(new Node(node, newState, action));
        }
    }

    public int simulate(Node node) {
        Player current = node.getState().getCurrentPlayer();
        GameState simulationState = node.getState().clone();
        while (!simulationState.isTerminal()) {
            List<Integer> legalActions = simulationState.legalActions();
            int action = legalActions.get(random.nextInt(legalActions.size()));
            simulationState = simulationState.applyAction(action);
        }
        return simulationState.getResult(current);
    }

    public void backpropagate(Node node, int result) {
        // System.out.println("Backpropagating node");
        node.addVisits();
        if (node.getParent() != null) {
            node.addReward(result);
            this.backpropagate(node.getParent(), -(result));
        }
    }

    public int getBestAction(Node root_node) {
        return Collections.max(root_node.getChildren(), // Get the list of children nodes
                Comparator.comparingInt(Node::getVisits) // Use node visits as the comparison key
        ).getAction(); // Get the action associated with the node with the most visits
    }

    public int runSimulation(GameState state) {
        Node root_node = new Node(null, state, -1);
        for (int i = 0; i < 1000; i++) {
            int winner = 0;
            Node node = this.select(root_node); // selects the node with the highest UCB value
            if (!node.getState().isTerminal() && !node.hasChildren()) { // if the node is a leafnode
                this.expand(node); // creates all of its children
                // returns the winner of the simulation
                winner = this.simulate(node);
            } else { // if it's a terminal node or doesn't have children
                Player player = node.getParent().getState().getCurrentPlayer(); // the player who placed the last piece
                if (player == null) {
                    System.out.println("player is null for some reason");
                }
                winner = -(node.getState().getResult(player)); // siste node, getResult(spiller som satte brikken)
            }
            this.backpropagate(node, winner);
        }

        for (Node child : root_node.getChildren()) {
            System.out.println(
                    "Action: " + child.getAction() + " Visits: " + child.getVisits() + " Reward: " + child.getReward());
        }
        return getBestAction(root_node);
    }

    public static void main(String[] args) {
        FirePaaRadEnv firePaaRad = new FirePaaRadEnv("Ludvig", "Thomas");
        MCTS mcts = new MCTS();
        GameState initialState = new GameState(firePaaRad);

        int bestAction = mcts.runSimulation(initialState);
        System.out.println("Best action is: " + bestAction);
        firePaaRad.putPiece(bestAction);
    }
}
