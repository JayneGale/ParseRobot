import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;
import javax.swing.JFileChooser;

/**
 * The parser and interpreter. The top level parse function, a main method for
 * testing, and several utility methods are provided. You need to implement
 * parseProgram and all the rest of the parser.
 */
public class Parser {

	/**
	 * Top level parse method, called by the World
	 */
	static RobotProgramNode parseFile(File code) {
		Scanner scan = null;
		try {
			scan = new Scanner(code);

			// the only time tokens can be next to each other is
			// when one of them is one of (){},;
			scan.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");

			RobotProgramNode n = parseProgram(scan); // You need to implement this!!!
//TODO implement parseProgram(scan) in RobotProgramNode
			scan.close();
			return n;
		} catch (FileNotFoundException e) {
			System.out.println("Robot program source file not found");
		} catch (ParserFailureException e) {
			System.out.println("Parser error:");
			System.out.println(e.getMessage());
			scan.close();
		}
		return null;
	}

	/** For testing the parser without requiring the world */

	public static void main(String[] args) {
		if (args.length > 0) {
			for (String arg : args) {
				File f = new File(arg);
				if (f.exists()) {
					System.out.println("Parsing '" + f + "'");
					RobotProgramNode prog = parseFile(f);
					System.out.println("Parsing completed ");
					if (prog != null) {
						System.out.println("================\nProgram:");
						System.out.println(prog);
					}
					System.out.println("=================");
				} else {
					System.out.println("Can't find file '" + f + "'");
				}
			}
		} else {
			while (true) {
				JFileChooser chooser = new JFileChooser(".");// System.getProperty("user.dir"));
				int res = chooser.showOpenDialog(null);
				if (res != JFileChooser.APPROVE_OPTION) {
					break;
				}
				RobotProgramNode prog = parseFile(chooser.getSelectedFile());
				System.out.println("Parsing completed");
				if (prog != null) {
					System.out.println("Program: \n" + prog);
				}
				System.out.println("=================");
			}
		}
		System.out.println("Done");
	}

	// Useful Patterns

	private static final Pattern NUMPAT = Pattern.compile("-?\\d+"); // ("-?(0|[1-9][0-9]*)");

	private static final  Pattern OPENPAREN = Pattern.compile("\\(");
	private static final  Pattern CLOSEPAREN = Pattern.compile("\\)");
	private static final  Pattern OPENBRACE = Pattern.compile("\\{");
	private static final  Pattern CLOSEBRACE = Pattern.compile("\\}");
// Operator patterns
	private static final  Pattern ADDPAT = Pattern.compile("\\+");
	private static final  Pattern SUBPAT = Pattern.compile("-");
	private static final  Pattern MULPAT = Pattern.compile("\\*");
	private static final  Pattern DIVPAT = Pattern.compile("/");
// Robot action patterns
	private static final  Pattern TURN_L = Pattern.compile("turnL");
	private static final  Pattern MOVEPAT = Pattern.compile("move");
	private static final  Pattern TURN_R = Pattern.compile("turnR");
	private static final  Pattern TURN_AR = Pattern.compile("turnAround");

	private static final  Pattern TAKEFUEL = Pattern.compile("takeFuel");
	private static final  Pattern FUEL = Pattern.compile("fuel");
	private static final  Pattern FUELLEFT = Pattern.compile("fuelLeft");

	private static final  Pattern OPP_LR = Pattern.compile("oppLR");
	private static final  Pattern OPP_FB = Pattern.compile("oppFB");
	private static final  Pattern SHIELDON = Pattern.compile("shieldOn");
	private static final  Pattern SHIELDOFF = Pattern.compile("shieldOff");
	private static final  Pattern BARRELS = Pattern.compile("numBarrels");
	private static final  Pattern CLOSE_BARRELS = Pattern.compile("closestBarrelLR");
	private static final  Pattern BARRELLR = Pattern.compile("barrelLR");


	private static final  Pattern LOOP_PAT = Pattern.compile("loop");
	private static final  Pattern WAIT = Pattern.compile("wait");
	private static final  Pattern WHILE_PAT = Pattern.compile("while");
	private static final  Pattern IF_PAT = Pattern.compile("if");

// TODO up to here - there may be more patterns to add

//	Pattern numPat = Pattern.compile( "[-+]?(\\d+([.]\\d*)?|[.]\\d+)");
//	Pattern addPat = Pattern.compile("add");
//	Pattern subPat = Pattern.compile("sub");
//	Pattern mulPat = Pattern.compile("mul");
//	Pattern divPat = Pattern.compile("div");
//	Pattern opPat = Pattern.compile("add|sub|mul|div");
//	Pattern openPat = Pattern.compile("\\(");
//	Pattern commaPat = Pattern.compile(",");
//	Pattern closePat = Pattern.compile("\\)");
//public Node parseExpr(Scanner s) {
//	Node n;
//	if (!s.hasNext()) { return false; }
//	if (s.hasNext(numPat)) { return parseNumber(s); }
//	if (s.hasNext(addPat)) { return parseAdd(s); }
//	if (s.hasNext(subPat)) { return parseSub(s); }
//	if (s.hasNext(mulPat)) { return parseMul(s); }
//	if (s.hasNext(divPat)) { return parseDiv(s); }
//	return false;
//}
	/**
	 * PROG ::= STMT+
	 */

	// THE PARSER GOES HERE
	static RobotProgramNode parseProgram(Scanner s) {
		RobotProgramNode node = null;
		ArrayList<RobotProgramNode> nodeTree = new ArrayList<>();

//		 scan for
//		 a statement (loop, if, while etc),
//		 a variable (var)
//		 an operation (add, multiply, etc)
//		 a term or number
//		 an action (move turn_l etc)
		if(s.hasNext(MOVEPAT) || s.hasNext(TURN_L)|| s.hasNext(TURN_R)|| s.hasNext(TURN_AR)){return parseAct(s);}
		if(s.hasNext(IF_PAT) || s.hasNext(WHILE_PAT)|| s.hasNext(LOOP_PAT)){return parseStatement(s);}
		if(s.hasNext(ADDPAT) || s.hasNext(SUBPAT)){return parseTerms(s);}
		if(s.hasNext(MULPAT) || s.hasNext(DIVPAT)){return parseNum(s);}
		return node;
		}


	static RobotProgramNode parseStatement (Scanner s){
		if(!s.hasNext()) {fail("Empty expression", s);}
		if (s.hasNext(LOOP_PAT)){ return parseLoop(s);}
//		if (s.hasNext(TURN_L)){ return parseTurnL(s);}
//		if (s.hasNext(TURN_R)){ return parseTurnR(s);}
//		if (s.hasNext(TURN_AR)){ return parseTurnAround(s);}
		fail ("unknown or missing expression", s);
		return null;
	}

	private static RobotProgramNode parseLoop(Scanner s) {
		return null;
	}

	static RobotProgramNode parseAct(Scanner s){
		RobotProgramNode child = null;
		if(!s.hasNext()) {fail("Empty expression", s);}
		if (s.hasNext(MOVEPAT)){ return parseMove(s);}
//		if (s.hasNext(TURN_L)){ return parseTurnL(s);}
//		if (s.hasNext(TURN_R)){ return parseTurnR(s);}
//		if (s.hasNext(TURN_AR)){ return parseTurnAround(s);}
		fail ("unknown or missing expression", s);
		return child;
		}

	public static RobotProgramNode parseMove(Scanner s){
		RobotProgramNode move1 = new MoveNode();
		if(!s.hasNext()) {fail("Empty expression", s);}
		else if (s.hasNext(";")){
			return move1;
		//				move the robot; which robot? ?? what do I return to actually move it?
		}
//		else if(s.hasNext("\\(")){
//		// find out if the term inside the brackets is valid and evaluate it
//		require and element and a close bracket
//			return move2(term);
//		}
		else {fail("not a move node", s);
		}
		return null;
	}
	private static RobotProgramNode parseNum(Scanner s) {
		return null;
	}

	private static RobotProgramNode parseTerms(Scanner s) {
		return null;
	}


	// utility methods for the parser

	/**
	 * Report a failure in the parser.
	 */
	static void fail(String message, Scanner s) {
		String msg = message + "\n   @ ...";
		for (int i = 0; i < 5 && s.hasNext(); i++) {
			msg += " " + s.next();
		}
		throw new ParserFailureException(msg + "...");
	}

	/**
	 * Requires that the next token matches a pattern if it matches, it consumes
	 * and returns the token, if not, it throws an exception with an error
	 * message
	 */
	static String require(String p, String message, Scanner s) {
		if (s.hasNext(p)) {
			return s.next();
		}
		fail(message, s);
		return null;
	}

	static String require(Pattern p, String message, Scanner s) {
		if (s.hasNext(p)) {
			return s.next();
		}
		fail(message, s);
		return null;
	}

	/**
	 * Requires that the next token matches a pattern (which should only match a
	 * number) if it matches, it consumes and returns the token as an integer if
	 * not, it throws an exception with an error message
	 */
	static int requireInt(String p, String message, Scanner s) {
		if (s.hasNext(p) && s.hasNextInt()) {
			return s.nextInt();
		}
		fail(message, s);
		return -1;
	}

	static int requireInt(Pattern p, String message, Scanner s) {
		if (s.hasNext(p) && s.hasNextInt()) {
			return s.nextInt();
		}
		fail(message, s);
		return -1;
	}

	/**
	 * Checks whether the next token in the scanner matches the specified
	 * pattern, if so, consumes the token and return true. Otherwise returns
	 * false without consuming anything.
	 */
	static boolean checkFor(String p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

	static boolean checkFor(Pattern p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

}

// You could add the node classes here, as long as they are not declared public (or private)
//TODO add node classes here
class MoveNode implements RobotProgramNode {
	//	call the move method in Robot
	public String toString(Robot robot){
		return  "move " + robot.toString();
	}
	public Robot execute(Robot robot) {
		robot.move();
		return robot;
	}
}

class TurnLNode implements RobotProgramNode {
	//	call the turn method in Robot
	public String toString(Robot robot) {
		return "turnL " + robot.toString();
	}

	public Robot execute(Robot robot) {
		robot.turnLeft();
		return robot;
	}
}

class TurnRNode implements RobotProgramNode {
	//	call the turn method in Robot
	public String toString(Robot robot) {
		return "turnR " + robot.toString();
	}

	public Robot execute(Robot robot) {
		robot.turnRight();
		return robot;
	}
}

class LoopNode implements RobotProgramNode{
	BlockNode  block = this.block;
	String toString(Robot robot) {
		return "loop " + this.block;
	}
	public Robot execute(Robot robot){
		execute(block.execute(robot));
		return null;
	}
}

class BlockNode implements RobotProgramNode{
	//	for now, one action, there are likely several so need an array of actNodes and a for act in Actnodes on the toString and execute methods
	ActNode act = this.act;
	String toString(Robot robot) {
		return "block node " + this.act;
	}
	public Robot execute(Robot robot){
		execute(act.execute(robot));
//		call the BlockNode elements to execute actually I need a different ActNode that is a RobotProgrammeNode
		return null;
	}

}
class PROGNode implements RobotProgramNode{
	//	for now, one action, there are likely several so need an array of actNodes and a for act in Actnodes on the toString and execute methods
	STATNode st = this.st;
	String toString(Robot robot) {
		return "block node " + this.st;
	}
	public Robot execute(Robot robot){
		execute(st.execute(robot));
//		call the Programme elements to execute actually I need a an array or list of elements in the programme
		return null;
	}

}
class STATNode implements RobotProgramNode{
	//	for now, one action, there are likely several so need an array of actNodes and a for act in Actnodes on the toString and execute methods

	STATNode st = this.st;
	String toString(Robot robot) {
		return "block node " + this.st;
	}
	public Robot execute(Robot robot){
		execute(st.execute(robot));
//		call the Programme elements to execute actually I need a an array or list of elements in the programme
		return null;
	}
}
class ACTNode implements RobotProgramNode{
	//	for now, one action, there are likely several so need an array of actNodes and a for act in Actnodes on the toString and execute methods
	STATNode st = this.st;

	String toString(Robot robot) {
		return "block node " + this.st;
	}
	public Robot execute(Robot robot){
		execute(st.execute(robot));
//		call the Programme elements to execute actually I need a an array or list of elements in the programme
		return null;
	}
}

//		LOOP and BLOCK rules will need to construct the string out of their components.
//	The execute method for LoopNode will not call methods on the robot directly, but will repeatedly call the execute method of the BlockNode that it contains. Similarly, the BlockNode will need to call the execute method of each of its components in turn.
//		The node classes should also have a toString method which returns a textual representation of the node. The nodes corresponding to the PROG, STMT, LOOP and BLOCK rules will need to construct the string out of their components. For example, the LoopNode class might have the following method (assuming that block is a field containing the BlockNode that is contained in the LoopNode):
//		1
//public String toString() {
//		2
//		return "loop" + this.block;
//		3
//		}
//		You will also need to create a parse... method for each of the rules, which takes the scanner, and returns a RobotProgramNod
