// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

import java.util.*;

public class Parser {

    private Scanner scanner;

	private void match(String s) throws SyntaxException {
	scanner.match(new Token(s));
    }

	/**
	 *
	 * @return
	 * @throws SyntaxException
	 */
	private Token curr() throws SyntaxException {
	return scanner.curr();
    }

	/**
	 * Gets the position of the scanner
	 * @return
	 */
	private int pos() {
	return scanner.pos();
    }

    // following methods parse particular grammatical production
	private NodeMulop parseMulop() throws SyntaxException {
	if (curr().equals(new Token("*"))) {
	    match("*");
	    return new NodeMulop(pos(),"*");
	}
	if (curr().equals(new Token("/"))) {
	    match("/");
	    return new NodeMulop(pos(),"/");
	}
	return null;
    }

	private NodeAddop parseAddop() throws SyntaxException {
	if (curr().equals(new Token("+"))) {
	    match("+");
	    return new NodeAddop(pos(),"+");
	}
	if (curr().equals(new Token("-"))) {
	    match("-");
	    return new NodeAddop(pos(),"-");
	}
	return null;
    }

    private NodeMinus parseMinus() throws SyntaxException {
		if (curr().equals(new Token("-"))) {
			match("-");
			NodeMinus minus=parseMinus();
			if (minus != null)
				return new NodeMinus(minus);
			NodeFact fact = parseFact();
			return new NodeMinus(fact);
		}
		return null;
	}

	private NodeFact parseFact() throws SyntaxException {
	if (curr().equals(new Token("("))) {
	    match("(");
	    NodeExpr expr=parseExpr();
	    match(")");
	    return new NodeFactExpr(expr);
	}
	if (curr().equals(new Token("id"))) {
	    Token id=curr();
	    match("id");
		return new NodeFactId(pos(),id.lex());
	}
	Token num=curr();
	match("num");
	return new NodeFactNum(num.lex());
    }

	private NodeTerm parseTerm() throws SyntaxException {
	NodeFact fact;
	fact=parseMinus();
	if (fact == null) {
		fact=parseFact();
	}
	NodeMulop mulop=parseMulop();
	if (mulop==null) {
				return new NodeTerm(fact, null, null);
	}
	NodeTerm term=parseTerm();
	term.append(new NodeTerm(fact,mulop,null));
	return term;
    }

    private NodeExpr parseExpr() throws SyntaxException {
	NodeTerm term=parseTerm();
	NodeAddop addop=parseAddop();
	if (addop==null)
	    return new NodeExpr(term,null,null);
	NodeExpr expr=parseExpr();
	expr.append(new NodeExpr(term,addop,null));
	return expr;
    }

    private NodeAssn parseAssn() throws SyntaxException {
	Token id=curr();
	match("id");
	match("=");
	NodeExpr expr=parseExpr();
	NodeAssn assn=new NodeAssn(id.lex(),expr);
	return assn;
    }

    private NodeStmt parseStmt() throws SyntaxException {
	NodeAssn assn=parseAssn();
	match(";");
	NodeStmt stmt=new NodeStmt(assn);
	return stmt;
    }

    public Node parse(String program) throws SyntaxException {
		NodeStmt stmt = new NodeStmt(new NodeAssn(" ", null));
	scanner=new Scanner(program);
	boolean parseStmt = scanner.next();
	// want to parse statement unless statement is empty
	if (parseStmt) {
		stmt=parseStmt();
	}
	match("EOF");
	return stmt;
    }

}
