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
//region PATTERNS

	// Symbols
	private static final  Pattern OPENPAREN = Pattern.compile("\\(");
	private static final  Pattern CLOSEPAREN = Pattern.compile("\\)");
	private static final  Pattern OPENBRACE = Pattern.compile("\\{");
	private static final  Pattern CLOSEBRACE = Pattern.compile("\\}");
	private static final Pattern SEMIC =  Pattern.compile(";");

	// Operator patterns
	private static final Pattern NUMPAT = Pattern.compile("-?\\d+"); // ("-?(0|[1-9][0-9]*)");
	private static final  Pattern ADDPAT = Pattern.compile("\\+");
	private static final  Pattern SUBPAT = Pattern.compile("-");
	private static final  Pattern MULPAT = Pattern.compile("\\*");
	private static final  Pattern DIVPAT = Pattern.compile("/");


	// Robot action patterns
	private static final  Pattern MOVEPAT = Pattern.compile("move");
	private static final  Pattern TURN_L = Pattern.compile("turnL");
	private static final  Pattern TURN_R = Pattern.compile("turnR");
	private static final  Pattern TURN_AR = Pattern.compile("turnAround");
	private static final  Pattern WAIT = Pattern.compile("wait");
	private static final  Pattern TAKEFUEL = Pattern.compile("takeFuel");
	private static final  Pattern SHIELDON = Pattern.compile("shieldOn");
	private static final  Pattern SHIELDOFF = Pattern.compile("shieldOff");
	private static final  Pattern SHIELDGEN= Pattern.compile("shieldOn|shieldOff");

	//  Sense patterns
	private static final  Pattern FUEL = Pattern.compile("fuel");
	private static final  Pattern FUELLEFT = Pattern.compile("fuelLeft");
	private static final  Pattern OPP_LR = Pattern.compile("oppLR");
	private static final  Pattern OPP_FB = Pattern.compile("oppFB");
	private static final  Pattern NUM_BARRELS = Pattern.compile("numBarrels");
	private static final  Pattern CLOSE_BARRELS = Pattern.compile("closestBarrelLR");
	private static final  Pattern BARRELLR = Pattern.compile("barrelLR");
	private static final  Pattern BARRELFB = Pattern.compile("barrelFB");
	private static final  Pattern WALLDIST = Pattern.compile("wallDist");

// Statement patterns
	private static final  Pattern IF_PAT = Pattern.compile("if");
	private static final  Pattern WHILE_PAT = Pattern.compile("while");
	private static final  Pattern LOOP_PAT = Pattern.compile("loop");
	private static final  Pattern ACT_PAT = Pattern.compile("move|turnL|turnR|turnAround|wait|takeFuel|shieldOn|shieldOff");

//	Pattern numPat = Pattern.compile( "[-+]?(\\d+([.]\\d*)?|[.]\\d+)");
//	Pattern opPat = Pattern.compile("add|sub|mul|div");
//	Pattern openPat = Pattern.compile("\\(");
//	Pattern commaPat = Pattern.compile(",");
//	Pattern closePat = Pattern.compile("\\)");

	//	endregion

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
//TODO the parse methods are here
	// THE PARSER GOES HERE
	static RobotProgramNode parseProgram(Scanner s) {
		ArrayList<RobotProgramNode> nodeTree = new ArrayList<>();
//		use a constructor to return the nodeTree
//		 scan for a
//		 statement (loop, if, while etc),
//		 relative operation (gt, lt, equals)
//		 variable (var)
//		 operation (add (terms), multiply(factors), etc)
//		 term factor or number
//		 action (move turn_l etc)
		while (s.hasNext()) {
			if (s.hasNext(MOVEPAT) || s.hasNext(TURN_L) || s.hasNext(TURN_R) || s.hasNext(TURN_AR) || s.hasNext(WAIT)
					|| s.hasNext(TAKEFUEL) || s.hasNext(SHIELDON) || s.hasNext(SHIELDOFF)
					|| s.hasNext(IF_PAT) || s.hasNext(WHILE_PAT) || s.hasNext(LOOP_PAT)
//					|| s.hasNext(OPENBRACE) || s.hasNext(CLOSEBRACE) || s.hasNext(OPENPAREN) || s.hasNext(CLOSEPAREN)
					|| s.hasNext(NUMPAT) || s.hasNext(ADDPAT) || s.hasNext(SUBPAT) || s.hasNext(MULPAT) || s.hasNext(DIVPAT)) {
				nodeTree.add(parseStatement(s));
			} else {
				fail("unknown or missing expression ", s);
			}
		}

		if (nodeTree != null) {
			for (RobotProgramNode n : nodeTree)
				System.out.println("nodeTree " + n.toString());
		}

		ProgramNode newP = new ProgramNode();
		for(RobotProgramNode n : nodeTree){
			newP.addToArray(n);
		}
		return newP;
	}
// parse STATEMENTS:
//	region PARSE STATEMENTS: STMT IF WHILE LOOP BLOCK COND
	static RobotProgramNode parseStatement (Scanner s){
		if(!s.hasNext()) {fail("Empty expression", s);}
		System.out.println("Stmt started: " + s.hasNext());

//		STMT  ::= ACT ";" | LOOP | IF | WHILE | ASSGN ";"
		//			if (s.hasNext(MOVEPAT) || s.hasNext(TURN_L) || s.hasNext(TURN_R) || s.hasNext(TURN_AR) || s.hasNext(WAIT)
//					|| s.hasNext(TAKEFUEL) || s.hasNext(SHIELDON) || s.hasNext(SHIELDOFF)) {

		if (s.hasNext(ACT_PAT)) {
			return parseAct(s);
		} else if (s.hasNext(LOOP_PAT)){
			return parseLoop(s);
		} else if (s.hasNext(IF_PAT)){
			return parseIf(s);
		} else if (s.hasNext(WHILE_PAT)){
			return parseWhile(s);
//		if (s.hasNext(ASSGN)){ return parseAssignVar(s);}
		} else if (s.hasNext(ADDPAT) || s.hasNext(SUBPAT)) {
			return parseTerms(s);
		} else if (s.hasNext(MULPAT) || s.hasNext(DIVPAT)) {
			return parseFactor(s);
		} else {
			fail("unknown or missing expression in parseStatement ", s);
		}
		return new StatementNode();
	}

	private static RobotProgramNode parseIf(Scanner s) {
		System.out.println("reached IF statement " + s.hasNext());
//		IF    ::= "if" "(" COND ")" BLOCK [ "elif"  "(" COND ")"  BLOCK ]* [ "else" BLOCK ]
		return null;
	}
	private static RobotProgramNode parseWhile(Scanner s) {
		if (!s.hasNext()) {
			fail("Empty expression", s);
		}
		System.out.println("WHILE statement started " + s.hasNext());
		require(WHILE_PAT, "no while ", s);
		if (s.hasNext(OPENPAREN)) {
//		WHILE ::= "while" "(" COND ")" BLOCK
			return parseCond(s);
		}
		if (s.hasNext(OPENBRACE)){
			return parseBlock(s);
		}
		fail ("unknown or missing braces or brackets in While ", s);
		return new WhileNode();
	}

	private static RobotProgramNode parseLoop(Scanner s) {
		// TODO 			parseloop has to return an array list?
//		While not )
//		addtolist (Prog(s))
//				Addtolist
//	its now an end }
//	when do I require the }

		if(!s.hasNext()) {fail("Empty expression on loop ", s);}
		System.out.println("Loop started " + s.hasNext());
		require(LOOP_PAT, "no loop ", s);
//		LOOP  ::= "loop" BLOCK
		if (s.hasNext(OPENBRACE)) {
			RobotProgramNode b = parseBlock(s);
			System.out.println("loop has b: " + b.toString());
		}
//		require(CLOSEBRACE, "no close brace on loop ", s);
		return new LoopNode();
//		if (s.hasNext(IF_PAT) || s.hasNext(WHILE_PAT) || s.hasNext(LOOP_PAT)) {
//		   require(CLOSEBRACE, "no close brace on loop ", s);
//		   return parseStatement(s);}
//		While not )
//		addtoBlock (Prog(s))
//				Addtolist
//	its now an end }
	}
	private static RobotProgramNode parseBlock(Scanner s) {
		if(!s.hasNext()) {fail("Empty expression on block ", s);}
		System.out.println("Block started " + s.hasNext());
//		BLOCK ::= "{" STMT+ "}"
		require(OPENBRACE, "no open brace on block ", s);
		if(s.hasNext(CLOSEBRACE)) {fail("nothing between the block braces ", s);}
		ArrayList<RobotProgramNode> loopTree = new ArrayList<>();
		BlockNode newL = new BlockNode();

		while(s.hasNext(ACT_PAT) || s.hasNext(IF_PAT) || s.hasNext(WHILE_PAT) || s.hasNext(LOOP_PAT)) {
			RobotProgramNode b = parseStatement(s);
			loopTree.add(b);
			}
			require(CLOSEBRACE, "no close brace on block ", s);
		if (loopTree != null) {
			for (RobotProgramNode n : loopTree)
				newL.addToBlock(n);
				System.out.println("loopTree " + n.toString());
		}

		System.out.println("NewL.size"  + loopTree.size());
		return newL;
	}

	private static RobotProgramNode parseCond(Scanner s) {
		if(!s.hasNext()) {fail("Empty expression on parssCond ", s);}
		System.out.println("Cond started " + s.hasNext());
		require(OPENPAREN, "no open bracket on block ", s);
		if(s.hasNext(CLOSEPAREN)) {fail("no condition between brackets ", s);}
//		TODO write the actual Condition parse - how to recognise a RELOP COND or EXP
		if(s.hasNext(""))
//			number
//		COND  ::= RELOP "(" EXP "," EXP ")"  | and ( COND, COND ) | or ( COND, COND )  | not ( COND )
//		RELOP ::= "lt" | "gt" | "eq"
		require(CLOSEPAREN, "no close bracket on Cond ", s);
		return new CondNode();
	}
// endregion

//	NEED an expression node and two layers or OPNodes
//	EXP   ::= NUM | SEN | VAR | OP "(" EXP "," EXP ")"

//Will need a SENSE node
//	SEN   ::= "fuelLeft" | "oppLR" | "oppFB" | "numBarrels" |
//			"barrelLR" [ "(" EXP ")" ] | "barrelFB" [ "(" EXP ")" ] | "wallDist"

//	OP   ::= "add" | "sub"
//	OP2   ::= "mul" | "div"

	// ACTIONS are here
//	region parse ACTIONS : MOVE TURNL TURNR TURNAR WAIT TAKEFUEL SHIELD
	static RobotProgramNode parseAct(Scanner s){
		RobotProgramNode child = null;
		RobotProgramNode prog = new ProgramNode();
		if(!s.hasNext()) {fail("Empty expression", s);}
//		ACT   ::= "move" [ "(" EXP ")" ] | "turnL" | "turnR" | "turnAround" |
//				"shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXP ")" ]
		if (s.hasNext(MOVEPAT)){ return parseMove(s);}
		if (s.hasNext(TURN_L)){ return parseTurnL(s);}
		if (s.hasNext(TURN_R)){ return parseTurnR(s);}
		if (s.hasNext(TURN_AR)){ return parseTurnAR(s);}
		if (s.hasNext(SHIELDON)){ return parseShield(s);}
		if (s.hasNext(SHIELDOFF)){ return parseShield(s);}
		if (s.hasNext(TAKEFUEL)){ return parseTakeFuel(s);}
		if (s.hasNext(WAIT)){ return parseWait(s);}

		fail ("unknown or missing expression ", s);
		return new ActNode();
		}

	public static RobotProgramNode parseMove(Scanner s){
		RobotProgramNode move1 = new MoveNode();
		move1.toString();
		if(!s.hasNext()) {fail("Empty expression", s);}
		System.out.println("Move started" + s.hasNext());
		require(MOVEPAT, "no move ", s);
//		else if(s.hasNext("\\(")){
//		// find out if the term inside the brackets is valid and evaluate it
//		require and element and a close bracket
//			return move2(term);
//		}
		require (SEMIC, "missing semicolon on move ", s);
		return new MoveNode();
	}
	private static RobotProgramNode parseTurnL(Scanner s) {
		RobotProgramNode turn_L = new TurnLNode();
		if(!s.hasNext()) {fail("Empty expression", s);}
		System.out.println("TurnL started" + s.hasNext());
		require(TURN_L, "no turnL ", s);
		require (SEMIC, "missing semicolon on turnL ", s);
		return new TurnLNode();
	}
	private static RobotProgramNode parseTurnR(Scanner s) {
		RobotProgramNode turn_R = new TurnRNode();
		if(!s.hasNext()) {fail("Empty expression", s);}
		System.out.println("TurnR started " + s.hasNext());
		require(TURN_R, "no turnR ", s);
		require (SEMIC, "missing semicolon on turn_R ", s);
		return new TurnRNode();
	}
	private static RobotProgramNode parseTurnAR(Scanner s) {
		RobotProgramNode turn_AR = new TurnARNode();
		if(!s.hasNext()) {fail("Empty expression", s); }
		System.out.println("TurnAR started " + s.hasNext());
		require(TURN_AR, "no turnAR ", s);
		require (SEMIC, "missing semicolon after turnAround ", s);
		return new TurnARNode();
	}
	private static RobotProgramNode parseWait(Scanner s) {
		RobotProgramNode wait = new WaitNode();
		if(!s.hasNext()) {fail("Empty expression", s);}
		System.out.println("Wait started " + s.hasNext());
		require(WAIT, "no wait ", s);
		require (SEMIC, "missing semicolon after wait ", s);
		return wait;
	}
	private static RobotProgramNode parseTakeFuel(Scanner s) {
		RobotProgramNode takeFuel = new TakeFuelNode();
		if(!s.hasNext()) {fail("Empty expression", s);}
		System.out.println("TakeFule started " + s.hasNext());
		require(TAKEFUEL, "no wait ", s);
		require (SEMIC, "missing semicolon after takeFuel ", s);
		return takeFuel;
	}

	private static RobotProgramNode parseShield(Scanner s) {
		boolean shieldState = false;
		RobotProgramNode shieldNode = new ShieldNode();
		return shieldNode;
	}
//endregion

 //	region parse EXPRESSIONS NUMBERS TERMS FACTORS
	private static RobotProgramNode parseNum(Scanner s) {
		return null;
	}

	private static RobotProgramNode parseTerms(Scanner s) {
		return null;
	}
	private static RobotProgramNode parseFactor(Scanner s) {
		return null;
	}

//endregion

	// utility methods for the parser
//region ACTION NODE CLASSES: MOVE TURN WAIT FUEL SHIELD

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
//ACTION NODEs: MOVE TURN WAIT FUEL SHIELD
class MoveNode implements RobotProgramNode {
	//	call the move method in Robot
	public String toString(){
		return  "move the robot ";
	}
	public void execute(Robot robot) {
		robot.move();
	}
}
class TurnLNode implements RobotProgramNode {
	//	call the turLn method in Robot
	public String toString() {
		return "make robot turnL " ;
	}

	public void execute(Robot robot) {
		robot.turnLeft();
	}
}
class TurnRNode implements RobotProgramNode {
	//	call the turnR method in Robot
	public String toString() {
		return "make robot turnR " ;
	}
	public void execute(Robot robot) {
		robot.turnRight();
	}
}
class TurnARNode implements RobotProgramNode {
	//	call the turnAround method in Robot
	public String toString() {
		return "turnAR " ;
	}
	public void execute(Robot robot) {
		robot.turnAround();
	}
}
class WaitNode implements RobotProgramNode {
	//	call the wait method in Robot
	public String toString() {
		return "wait " ;
	}
	public void execute(Robot robot) {
		robot.idleWait();
	}
}
class TakeFuelNode implements RobotProgramNode {
	public String toString() {
		return "takeFuel ";
	}
	public void execute(Robot robot) {
		robot.takeFuel();
	}
}
class ShieldNode implements RobotProgramNode{
	//	if(shieldState);
	public String toString() {
		return "Shield node " ;
	}
	public void execute(Robot robot){
		boolean shieldState = false;
		robot.setShield(shieldState);
	}
//		execute(st.execute(robot));
//		call the Programme elements to execute actually I need a an array or list of elements in the programme
}

// Statement NODES: LOOP IF WHILE COND BLOCK
class ActNode implements RobotProgramNode {
	//	for now, one action, there are likely several so need an array of actNodes and a for act in Actnodes on the toString and execute methods
	ActNode st = this.st;
	public String toString() {
		return "Act node " + this.st;
	}
	public void execute(Robot robot) {
//		ActNode has no direct execute method, it just calls the Acts directly
	}
}
class LoopNode implements RobotProgramNode{
	ArrayList<RobotProgramNode> loopTree = new ArrayList<>();

//	ArrayList<RobotProgramNode> nodeTree = new ArrayList<>();
//	public void addToArray(RobotProgramNode r) {
//		nodeTree.add(r);
//	}
////	public String toString() {
//		return "Node tree size: " + nodeTree.size();
//	}
//
//	//		call all the Programme elements to execute in order
	public void addToBlock(RobotProgramNode b) {
		loopTree.add(b);
	}
	public String toString() {
		return "loop block size " + loopTree.size();
	}
	public void execute(Robot robot) {
//		Loop node endlessly calls its Block of Actions/Other Expressions
// run thru to block list and execute each in turn
		while (true){
			for (RobotProgramNode n : loopTree){
				n.execute(robot);
			}
//			Todo add the addtoBlock method here instead of loopNode
		}
//			Arraylist of robot statements
	}
}
class WhileNode implements RobotProgramNode {
	public String toString() {
		return "While node ";
	}

	public void execute(Robot robot) {
//		execute(robot);
	}
}
class CondNode implements RobotProgramNode{
	//	for now, one action, there are likely several so need an array of actNodes and a for act in Actnodes on the toString and execute methods
	public String toString() {
		return "Cond node " ;
	}
	public void execute(Robot robot){
//		execute(act.execute(robot));
//		evaluate the CondNode elements to execute actually I need a different ActNode that is a RobotProgrammeNode
	}
}
class BlockNode implements RobotProgramNode{
	public String toString() {
		return "block node " ;
	}
	public void execute(Robot robot){
//		execute(act.execute(robot));
//		call the BlockNode elements to execute actually I need a different ActNode that is a RobotProgrammeNode
	}
}

//TOP LEVEL Nodes PROG STMT ACT
class ProgramNode implements RobotProgramNode {
	ArrayList<RobotProgramNode> nodeTree = new ArrayList<>();

	public void addToArray(RobotProgramNode r) {
		nodeTree.add(r);
	}

	public String toString() {
		return "Node tree size: " + nodeTree.size();
	}

	//		call all the Programme elements to execute in order
	public void execute(Robot robot) {
		for (RobotProgramNode n : nodeTree) {
			n.execute(robot);
		}
	}
}
class StatementNode implements RobotProgramNode {
	ArrayList<RobotProgramNode> StatTree = new ArrayList<>();
	public String toString() {
		return "Statement node ";
	}
	public void execute(Robot robot) {
//		robot.turnAround(); empty for Statement Node
//		call the Programme elements to execute  I need a an array or list of elements in the programme
	}
}

//	example from Lectures of List with children
//class AddNode implements Node {
//	final ArrayList<Node> children;
//	public AddNode(ArrayList<Node> chn){
//		children = chn; }
//	public String toString() {
//		String result = "[";
//		for (Node n : children){result += n.toString();}
//		return result + "]";

//		execute(st.execute(robot));
//		call the Programme elements to execute actually I need a an array or list of elements in the programme

//endregion
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
