// defines the fact production
// could be an id, num, or expr

public abstract class NodeFact extends Node {
    private int negCount;
    public void incrementNegCount() {
        negCount++;
    }

    public int getNegCount() {
        return negCount;
    }

    public void initNodeFact() {negCount = 0;}
}

