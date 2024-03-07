package project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MCTS {
    private double c;
    private Random random = new Random();

    public MCTS() {
        c = 1.41;
    }

    public double UCB(Node node) {
        if (node.getVisits() == 0) {
            return 100000.0;
        }
        return (node.getReward() / node.getVisits())
                + this.c * (Math.log(node.getParent().getVisits()) / node.getVisits());
    }

    public Node select(Node node) {
        // System.out.println("Selecting node");
        double highestUCB = -100000;
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
            highestUCB = -10000;
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

    public Player simulate(Node node) {
        // returns if it's the winner from the simulation or not
        Player current = node.getState().getCurrentPlayer();
        GameState simulationState = node.getState().clone();
        while (!simulationState.isTerminal()) {
            // simuler neste steg,
            List<Integer> legalActions = simulationState.legalActions();
            int action = legalActions.get(random.nextInt(legalActions.size()));
            simulationState = simulationState.applyAction(action);
        }
        return simulationState.getResult(current);
    }

    // result is should be the same as I would get with this method in python return
    // simulation_state.returns()[(node.state.current_player() + 1) & 1]
    public void backpropagate(Node node, int result) {
        // System.out.println("Backpropagating node");
        node.addVisits();
        if (node.getParent() != null) {
            node.addReward(result);
            this.backpropagate(node.getParent(), -result);
        }
    }

    public int getBestAction(Node root_node) {
        return Collections.max(root_node.getChildren(), // Get the list of children nodes
                Comparator.comparingInt(Node::getVisits) // Use node visits as the comparison key
        ).getAction(); // Get the action associated with the node with the most visits
    }

    public int getResult(Player winner, Player root) {
        if (winner == null) {
            return 0;
        } if (winner.equals(root)) {
            return 1;
        } else {return -1;}
    }

    public int runSimulation(GameState state) {
        Node root_node = new Node(null, state, 0);
        for (int i = 0; i < 10000; i++) {
            Player winner = null;
            Node node = this.select(root_node);
            if (!node.getState().isTerminal() && !node.hasChildren()) {
                this.expand(node); // creates all of its children
                // returns if it's the winner from the simulation or not
                winner = this.simulate(node);
            } else { // if it's a terminal node or doesn't have children
                Player player = node.getState().getResult();
                if (player == null) {
                    System.out.println("Player is none for some reason");
                }
                winner = player;
            }
            int result = this.getResult(winner, root_node.getState().getCurrentPlayer());
            this.backpropagate(node, result);
        }

        for (Node child : root_node.getChildren()) {
            System.out.println("Action: " + child.getAction() + " Visits: " + child.getVisits() + " Reward: " + child.getReward());
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
        
        bestAction = mcts.runSimulation(initialState);
        System.out.println("Best action is: " + bestAction);
        firePaaRad.putPiece(bestAction);
        bestAction = mcts.runSimulation(initialState);
        System.out.println("Best action is: " + bestAction);
        firePaaRad.putPiece(bestAction);
        bestAction = mcts.runSimulation(initialState);
        System.out.println("Best action is: " + bestAction);
        firePaaRad.putPiece(bestAction);
        bestAction = mcts.runSimulation(initialState);
        System.out.println("Best action is: " + bestAction);
        firePaaRad.putPiece(bestAction);
        bestAction = mcts.runSimulation(initialState);
        System.out.println("Best action is: " + bestAction);
        firePaaRad.putPiece(bestAction);
        bestAction = mcts.runSimulation(initialState);
        System.out.println("Best action is: " + bestAction);
        firePaaRad.putPiece(bestAction);
        bestAction = mcts.runSimulation(initialState);
        System.out.println("Best action is: " + bestAction);
        firePaaRad.putPiece(bestAction);
        bestAction = mcts.runSimulation(initialState);
        System.out.println("Best action is: " + bestAction);
        firePaaRad.putPiece(bestAction);

        firePaaRad.PrintBoard();
    }
}
