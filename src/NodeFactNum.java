//defines and implements the fact production

public class NodeFactNum extends NodeFact {

    private String num;

    //constructor
    public NodeFactNum(String num) {
	this.num=num;
	this.initNodeFact();
    }

    public double eval(Environment env) throws EvalException {
	return this.doNeg();
    }

    private double doNeg() {
        double num = Integer.parseInt(this.num);
        for (int i = 0; i < this.getNegCount();i++) {
            num = num * -1;
        }
        return num;
    }

}
