//defines and implements the fact production

public class NodeFactNum extends NodeFact {

    private String num;

    //constructor
    public NodeFactNum(String num) {
	this.num=num;
    }

    public int eval(Environment env) throws EvalException {
	return Integer.parseInt(num);
    }

}
