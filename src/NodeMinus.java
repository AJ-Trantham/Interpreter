//defines the minus rule of the fact production

public class NodeMinus extends NodeFact {

    private NodeFact fact;

    public NodeMinus(NodeFact fact) {
        this.fact=fact;
    }

    public double eval(Environment env) throws EvalException {
        return -fact.eval(env);
    }


}

