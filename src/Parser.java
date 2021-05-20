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
				File f = chooser.getSelectedFile();
				System.out.println("Parsing '" + f.getName() + "'");
				RobotProgramNode prog = parseFile(chooser.getSelectedFile());
				System.out.println("Parsing completed");
				if (prog != null) {
					System.out.println("Program: \n" + prog );
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
	private static final  Pattern CLOSEBRACE = Pattern.compile("}");
	private static final Pattern SEMIC =  Pattern.compile(";");

	// Operator patterns
//	Numbers: options Pattern.compile("-?\\d+"); // ("-?(0|[1-9][0-9]*)"); ( "[-+]?(\\d+([.]\\d*)?|[.]\\d+)"); // for robot game only need integers

	private static final Pattern NUMPAT = Pattern.compile("-?[0-9]+");
	private static final  Pattern ADDPAT = Pattern.compile("add");
	private static final  Pattern SUBPAT = Pattern.compile("sub");
	private static final  Pattern MULPAT = Pattern.compile("mul");
	private static final  Pattern DIVPAT = Pattern.compile("div");

	//	OP   ::= "add" | "sub" | "mul" | "div"
	private static final Pattern OP_PAT = Pattern.compile("add|sub|mul|div");

	//	RELOP ::= "lt" | "gt" | "eq"
	private static final Pattern RELOP_PAT = Pattern.compile("lt|gt|eq");
	private static final  Pattern VAR_PAT = Pattern.compile("\\$[A-Za-z][A-Za-z0-9]*");

	// Robot action patterns
//	ACT   ::= "move" [ "(" EXP ")" ] | "turnL" | "turnR" | "turnAround" |
//			"shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXP ")" ]
	private static final  Pattern MOVEPAT = Pattern.compile("move");
	private static final  Pattern TURN_L = Pattern.compile("turnL");
	private static final  Pattern TURN_R = Pattern.compile("turnR");
	private static final  Pattern TURN_AR = Pattern.compile("turnAround");
	private static final  Pattern WAIT = Pattern.compile("wait");
	private static final  Pattern TAKEFUEL = Pattern.compile("takeFuel");
	private static final  Pattern SHIELDON = Pattern.compile("shieldOn");
	private static final  Pattern SHIELDOFF = Pattern.compile("shieldOff");
//	private static final  Pattern SHIELDGEN= Pattern.compile("shieldOn|shieldOff");

	//  Sense patterns
	private static final  Pattern FUELLEFT = Pattern.compile("fuelLeft");
	private static final  Pattern OPP_LR = Pattern.compile("oppLR");
	private static final  Pattern OPP_FB = Pattern.compile("oppFB");
	private static final  Pattern NUM_BARRELS = Pattern.compile("numBarrels");
	private static final  Pattern BARRELLR = Pattern.compile("barrelLR");
	private static final  Pattern BARRELFB = Pattern.compile("barrelFB");
	private static final  Pattern WALLDIST = Pattern.compile("wallDist");
//	Todo add EXP option to barrelLR and barrelFB

// Statement patterns
	private static final  Pattern IF_PAT = Pattern.compile("if");
	private static final  Pattern ELSE_PAT = Pattern.compile("else");
	private static final  Pattern WHILE_PAT = Pattern.compile("while");
	private static final  Pattern LOOP_PAT = Pattern.compile("loop");


// General
	//	ACT   ::= "move" [ "(" EXP ")" ] | "turnL" | "turnR" | "turnAround" |
	//			"shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXP ")" ]
	private static final  Pattern ACT_PAT = Pattern.compile("move|turnL|turnR|turnAround|wait|takeFuel|shieldOn|shieldOff");
	private static final Pattern SEN_PAT = Pattern.compile("fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist");

	//	EXP   ::= NUM | SEN | VAR | OP "(" EXP "," EXP ")"
	// 	EXPPAT = NUMPAT + SENPAT + VARPAT + OP_PAT
	private static final Pattern EXP_PAT = Pattern.compile("-?[0-9]+|fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist|add|sub|mul|div|lt|gt|eq|and|or|not|\\$[A-Za-z][A-Za-z0-9]*");

	//	COND ::= RELOP "(" EXP "," EXP ")"  | and ( COND, COND ) | or ( COND, COND )  | not ( COND )
	//	COND_PAT = RELOP plus (and, or, not)
	private static final Pattern COND_PAT = Pattern.compile("lt|gt|eq|and|or|not");

	//	ASSGN ::= VAR "=" EXP
	//	ASSGN_PAT = Pattern.compile("\\$[A-Za-z][A-Za-z0-9]*");
	//	Todo Create any ASSIGN patterns when I get to Variables

//  Pattern EXPR_PAT = Pattern.compile("/|\\*|-|\\+|-?\\d+");

	//	endregion

	/**
	 * PROG ::= STMT+
	 */
//Parse methods are here
	// THE PARSER GOES HERE
	static RobotProgramNode parseProgram(Scanner s) {
		ArrayList<RobotProgramNode> nodeTree = new ArrayList<>();

//		 scan for a
//		 statement (action, loop, if, while )

//		 relative operation (gt, lt, equals)
//		 variable (var)
//		 operation (add (terms), multiply(factors), etc)
//		 term factor or number
//		 action (move turn_l etc)

		while (s.hasNext()) {
			if (s.hasNext(ACT_PAT) || s.hasNext(IF_PAT) || s.hasNext(WHILE_PAT) || s.hasNext(LOOP_PAT)
					|| s.hasNext(NUMPAT) || s.hasNext(EXP_PAT)) {
				nodeTree.add(parseStatement(s));
			} else {
				fail("parseProgram unknown or missing expression ", s);
			}
		}

		ProgramNode newP = new ProgramNode();
		if (nodeTree != null) {
			System.out.println("183 parseProgram, nodeTree contents: ");
			for (RobotProgramNode n : nodeTree) {
				System.out.println(" - " + n.toString());
				newP.addToArray(n);
			}
		}
		return newP;
	}
// parse STATEMENTS:
//	region PARSE STATEMENTS: STMT IF WHILE LOOP BLOCK COND
	static RobotProgramNode parseStatement (Scanner s){
		ArrayList<RobotProgramNode> children =  new ArrayList<>();
		if(!s.hasNext()) {fail("Empty expression", s);}
		System.out.println("181 parseStatement started");

//		STMT  ::= ACT ";" | LOOP | IF | WHILE | ASSGN ";"
		if (s.hasNext(ACT_PAT)) {
			return parseAct(s);
		} else if (s.hasNext(LOOP_PAT)){
			return parseLoop(s);
		} else if (s.hasNext(IF_PAT)){
			return parseIf(s);
		} else if (s.hasNext(WHILE_PAT)) {
			return parseWhile(s);
//		} else if (s.hasNext(EXP_PAT)){
//			return parseAssignVar(s);
		} else {
			fail("parseStatement unknown or missing expression ", s);
		}
		return new StatementNode();
	}

	private static RobotProgramNode parseLoop(Scanner s) {
//		While not )
//		addtolist (Prog(s))
//				Addtolist
//	its now an end }
//	when do I require the }

		if(!s.hasNext()) {fail("Empty expression on loop ", s);}
		System.out.println("230 parseLoop started");
		require(LOOP_PAT, "no loop ", s);
//		LOOP  ::= "loop" BLOCK
		BlockNode block = new BlockNode();
		if (s.hasNext(OPENBRACE)) {
			return parseBlock(s);
//			System.out.println("loop has b: " + b.toString());
		}
		return new LoopNode();
//		While not )
//		addtoBlock (Prog(s))
//				Addtolist
//	its now an end }
	}
	private static BlockNode parseBlock(Scanner s) {
		if(!s.hasNext()) {fail("Empty expression on block ", s);}
//		System.out.println("Block started " + s.hasNext());
//		BLOCK ::= "{" STMT+ "}"
		require(OPENBRACE, "no open brace on block ", s);
		if(s.hasNext(CLOSEBRACE)) {fail("nothing between the block braces ", s);}
		ArrayList<RobotProgramNode> blockTree = new ArrayList<>();
		BlockNode newBL = new BlockNode();
		while(s.hasNext(ACT_PAT) || s.hasNext(LOOP_PAT) || s.hasNext(IF_PAT) || s.hasNext(WHILE_PAT))   {
			RobotProgramNode b = parseStatement(s);
			blockTree.add(b);
			System.out.println("244 parseBlock loopTree actions: " + b.toString());
		}
		require(CLOSEBRACE, "246 no close brace on block ", s);
		if (blockTree != null) {
			for (RobotProgramNode n : blockTree) {
				newBL.addToBlock(n);
				System.out.println("250 parseBlock blockTree toString " + n.toString());
			}
		}
		System.out.println("253 parseBlock return newBL blockTree.size: "  + blockTree.size() + " newBL size: " + newBL.blockList.size());
		return newBL;
	}

	private static RobotProgramNode parseIf(Scanner s) {
		if (!s.hasNext()) {	fail("Empty expression", s); }
		System.out.println("214 parseIf started ");
//Stage 2		IF    ::= "if" "(" COND ")" BLOCK [ "else" BLOCK ]
//Stage 4		IF    ::= "if" "(" COND ")" BLOCK [ "elif"  "(" COND ")"  BLOCK ]* [ "else" BLOCK ]
		require(IF_PAT, "no if ", s);
		IfNode ifNode = new IfNode();
		CondNode condNode = new CondNode();
		BlockNode block = new BlockNode();
		boolean ifResult = false;
		if (s.hasNext(OPENPAREN)) {condNode = parseCond(s); ifNode.setIfBool(condNode); ifResult = ifNode.getIfBool() ;}
		else fail ("266 parseWhile no Cond following If ", s);
		if (s.hasNext(OPENBRACE)){block = parseBlock(s); ifNode.SetIfBlock(block);}
		else fail ("parseIf no block following or unknown expression ", s);
		if(s.hasNext(ELSE_PAT)){
			//		todo read up on restofif and create an else node/ parseElse
		};
		return ifNode;
	}

	private static RobotProgramNode parseRestOFIf(Scanner s) {
		return new IfNode();
	}

	private static RobotProgramNode parseWhile(Scanner s) {
		if (!s.hasNext()) {	fail("Empty expression", s); }
		System.out.println("274 parseWhile started " + s.hasNext());
		//		WHILE ::= "while" "(" COND ")" BLOCK (= {})
		require(WHILE_PAT, "no while ", s);
		RobotProgramNode whileNode = new WhileNode();
		if (s.hasNext(OPENPAREN)) {parseCond(s);}
		else fail ("parseWhile no Cond following While ", s);
//		require(CLOSEPAREN, "279 missing close brace on While ", s);
		if (s.hasNext(OPENBRACE)){parseBlock(s); }
		else fail ("parseWhile no block following or unknown expression ", s);
		return whileNode;
	}

	private static CondNode parseCond(Scanner s) {
//	    true or false RobotBool

		if(!s.hasNext()) {fail("Empty expression on parsCond ", s);}
		System.out.println("Cond started " + s.hasNext());
		require(OPENPAREN, "no open bracket on condition ", s);
		if(s.hasNext(CLOSEPAREN)) {fail("no condition between brackets ", s);}
		CondNode condNode = new CondNode();
		if(s.hasNext(COND_PAT)) parseRelOp(s);
		else fail ("293 parseCond: no RelOp following open paren ", s);
//		Stage 1 COND  ::= RELOP "(" SEN "," NUM ")
//		COND  ::= RELOP "(" EXP "," EXP ")"  | and ( COND, COND ) | or ( COND, COND )  | not ( COND )
//		RELOP ::= "lt" | "gt" | "eq"
		require(CLOSEPAREN, "298 no close paren on Cond ", s);
		return condNode;
	}
	private static RobotProgramNode parseSen(Scanner s) {
		if(!s.hasNext()) {fail("Empty expression on parseSen ", s);}
		System.out.println("Sen started ");
		SenNode newSen = new SenNode();
		//		require(OPENPAREN, "no open Paren on Sen", s);
		//	SEN   ::= "fuelLeft" | "oppLR" | "oppFB" | "numBarrels" |
		//			"barrelLR" [ "(" EXP ")" ] | "barrelFB" [ "(" EXP ")" ] | "wallDist"
		if (s.hasNext(FUELLEFT)) { newSen.setSenType(SenType.fuelLeft);
				newSen.setNum(1);
				require(FUELLEFT, "no token for fuelleft ", s);}
		//			return parseMove(s);}
		else if (s.hasNext(OPP_LR)) { newSen.setSenType(SenType.oppLR);
//			newSen.setNum(1);
			require(OPP_LR, "no token for OPP_LR ", s);}
		else if (s.hasNext(OPP_FB)) { newSen.setSenType(SenType.oppFB);
			newSen.getNum();
			require(OPP_FB, "no token for OPP_FB ", s);}
		else if (s.hasNext(NUM_BARRELS)) { newSen.setSenType(SenType.numBarrels);
			newSen.setNum(1); newSen.getNum();
			require(NUM_BARRELS, "no token for numBarrels ", s);}
		else if (s.hasNext(BARRELLR)) { newSen.setSenType(SenType.barrelLR);
			newSen.setNum(1);
			require(BARRELLR, "no token for barrelLR ", s);}
		else if (s.hasNext(BARRELFB)) { newSen.setSenType(SenType.barrelFB);
			newSen.setNum(1);
			require(BARRELFB, "no token for barrelFB ", s);}
		else if (s.hasNext(WALLDIST)) { newSen.setSenType(SenType.wallDist);
			newSen.setNum(1);
			require(WALLDIST, "no token for wallDist ", s);}
		else fail ("parseSen unknown or missing senType ", s);
		return newSen;
	}

	private static RobotProgramNode parseOp(Scanner s) {
//	OP   ::= "add" | "sub" | "mul" | "div"
		if(!s.hasNext()) {fail("Empty expression on parseSen ", s);}
		RobotProgramNode leftNum = null;
		RobotProgramNode rightNum = null;
		OpNode newOp = new OpNode(leftNum,rightNum);
//		RobotProgramNode exp = new ExpNode();
		if (s.hasNext(ADDPAT)) { newOp.setOpType(Optype.add);
			require(ADDPAT, "ParseOp no token for add ", s); }
		else if (s.hasNext(SUBPAT)) { newOp.setOpType(Optype.add);
			require(SUBPAT, "ParseOp no token for sub ", s);}
		else if (s.hasNext(MULPAT)) { newOp.setOpType(Optype.mul);
			require(MULPAT, "ParseOp no token for mul ", s);}
		else if (s.hasNext(DIVPAT)) { newOp.setOpType(Optype.div);
			require(DIVPAT, "ParseOp no token for div ", s);}
		else fail ("ParseOp unrecognised operator ", s);
			require(OPENPAREN, "ParseOp missing open paren ", s);
		leftNum = parseExpression(s);
//		RobotProgramNode sen = parseSen(s);
		System.out.println("352 ParseOp parse first exp  " + leftNum.toString());
		require(",", "parseOp missing comma after first exp ", s);
		rightNum = parseExpression(s);
		System.out.println("355 ParseOp second expression " + rightNum.toString() );
		require(CLOSEPAREN, "ParseOP missing close paren after exp2 ", s);
// TODO how do I get the two numbers from each numNode and calculate the value for the OpNode
//		newOp.calculate(leftNum, rightNum);
		Optype thisOp = newOp.getOpType();
		int result = newOp.result;
		System.out.println("360 ParseOp result num 1= " + leftNum + " op= " + thisOp.toString() + " rightNum= " + rightNum + " result= " + result);
		return newOp;
	}
	private static RobotProgramNode parseRelOp(Scanner s) {
		if (!s.hasNext()) { fail("Empty expression on parseRelop ", s); }
		if (s.hasNext(CLOSEPAREN)) { fail("Empty braces on parseRelop ", s); }
		System.out.println("ParseRelOp starts ");
		//	RELOP ::= "lt" | "gt" | "eq"
		RelOpNode relOp = new RelOpNode();
		RobotProgramNode e1 = new ExpNode();
		RobotProgramNode e2 = new ExpNode();
		if (s.hasNext("lt")) { relOp.setRelOpType(RelOpType.lt); System.out.println("RelOp " + relOp.toString()); require("lt", "missing lt in lt ", s);}
		else if (s.hasNext("gt")) { relOp.setRelOpType(RelOpType.gt); System.out.println("RelOp " + relOp.toString()); require("gt", "missing gt in lt ", s);}
		else if (s.hasNext("eq")) { relOp.setRelOpType(RelOpType.eq); System.out.println("RelOp " + relOp.toString()); require("eq", "missing eq in lt ", s);}
//		if (s.hasNext(OPENPAREN)) relOp.inSenValue = parseSen(s);
		require(OPENPAREN, "no open paren on relOp", s);
		if(s.hasNext(EXP_PAT)) {
			e1 = parseExpression(s);
			System.out.println("377 ParseExp result " + e1.toString());
		}
//		if(s.hasNext(SEN_PAT)){
//			RobotProgramNode sen = parseSen(s);
//			System.out.println("ParseSen result " + sen.toString());
//		}
//		else if (s.hasNext(NUMPAT)) {
//			RobotProgramNode num = parseNum(s);
//			System.out.println("ParseSen result " + num.toString());
//		}
		else fail("383 no expression on first relOp num ", s);
		require(",", "missing comma in reLop after first expression", s);
		e2 = parseExpression(s);
//		relOp.setNum(e1.getValue, e2,getValue, relOptype)
//		boolean result = relOp.calcBool(relOp.getRelOpType(), e1.getNum, e2.getNum);
//		relOp.setBool(result)
		System.out.println("354 ParseExp result " + e2.toString() );
		require(CLOSEPAREN, "missing close paren on relOP after Num ", s);
		return relOp;
		}

	private static RobotProgramNode parseExpression(Scanner s) {
//	EXP   ::= NUM | SEN | VAR | OP "(" EXP "," EXP ")"
		if (!s.hasNext()) { fail("empty string", s); }
		System.out.println("parseExp started ");
//		EXP   ::= NUM | SEN | OP "(" EXP "," EXP ")"
		if(s.hasNext(NUMPAT)){System.out.println("Exp returns Num ");  return parseNum(s);}
		else if(s.hasNext(SEN_PAT)){System.out.println("Exp returns Sen ");  return parseSen(s);}
		else if(s.hasNext(OP_PAT)){System.out.println("Exp returns Op ");  return parseOp(s);}
		//		ExpNode expNode = new ExpNode();
//		if(s.hasNext(NUMPAT)){expNode.setExpType(ExpType.num);System.out.println("Exp Num " + expNode.toString() + " result " + expNode.getResult());parseNum(s);}
//		else if(s.hasNext(SEN_PAT)){expNode.setExpType(ExpType.sen);System.out.println("Exp Sen " + expNode.toString()+ " result " + expNode.getResult()); parseSen(s);}
//		else if(s.hasNext(OP_PAT)){expNode.setExpType(ExpType.op);System.out.println("Exp op " + expNode.toString() +  " result " + expNode.getResult()); parseOp(s);}

//		else if(s.hasNext(MULPAT)){System.out.println("Factor operator multiply " + expNode.toString()); return parseNum(s);}
//		else if(s.hasNext(DIVPAT)){System.out.println("Factor operator divide " + expNode.toString()); return parseNum(s);}
//		else if(s.hasNext(ADDPAT)|s.hasNext(SUBPAT)){System.out.println("Exp " + expNode.toString()); parseTerms(s);}
//		else if(s.hasNext(MULPAT)| s.hasNext(DIVPAT) | s.hasNext(OPENPAREN)){System.out.println("Exp " + expNode.toString()); parseFactors(s);}
//		else if(s.hasNext(OPENPAREN)){expNode.setExpType(ExpType.openParen); System.out.println("Factor infix add " + expNode.toString()); parseExpression(s);}
		else fail("p374 parseExp Unrecognised expression ", s);
//		value = Integer.parseInt(s.next());
		return null;
	}
		private static RobotProgramNode parseNum (Scanner s){
//			System.out.println("parseNum starts ");
			if (!s.hasNext()) { fail("Empty expression", s); }
//			if(!s.hasNext(NUMPAT)){fail ("expecting an integer", s);}
			int value = requireInt(NUMPAT, "missing integer pattern ", s);
			System.out.println("parseNum value: " + value);
			return new NumNode(value);
		}

//	private static RobotProgramNode parseTerms(Scanner s) {
//		if (!s.hasNext()) { fail("Empty expression", s); }
//		NumNode termNode = new NumNode();
//		if(s.hasNext(ADDPAT)){System.out.println("Term operator add " + termNode.toString()); parseNum(s);}
//		else if(s.hasNext(SUBPAT)){System.out.println("Term operator sub " + termNode.toString()); parseNum(s);}
//		if(s.hasNext(MULPAT)){System.out.println("Factor operator multiply " + termNode.toString()); parseNum(s);}
//		if(s.hasNext(DIVPAT)){System.out.println("Factor operator divide " + termNode.toString()); parseNum(s);}
//		if(s.hasNext(OPENPAREN)){System.out.println("Factor infix add " + termNode.toString()); parseNum(s);}
//		else fail("parseTerms Unrecognised expression ", s);
//		return termNode;
//	}
//	private static RobotProgramNode parseFactors(Scanner s) {
//		if (!s.hasNext()) { fail("Empty expression", s); }
//		NumNode factorNode = new NumNode();
//		if(s.hasNext(MULPAT)){System.out.println("Factor operator multiply " + factorNode.toString()); parseNum(s);}
//		if(s.hasNext(DIVPAT)){System.out.println("Factor operator divide " + factorNode.toString()); parseNum(s);}
//		if(s.hasNext(OPENPAREN)){System.out.println("Factor infix add " + factorNode.toString()); parseNum(s);}
//		else fail("parseFactors Unrecognised expression ", s);
//
//		return factorNode;
//	}

// endregion

	// ACTIONS are here
//	region parse ACTIONS : MOVE TURNL TURNR TURNAR WAIT TAKEFUEL SHIELD
	static RobotProgramNode parseAct(Scanner s){
		if(!s.hasNext()) {fail("Empty expression", s);}
//		ACT   ::= "move" [ "(" EXP ")" ] | "turnL" | "turnR" | "turnAround" |
//				"shieldOn" | "shieldOff" | "takeFuel" | "wait" [ "(" EXP ")" ]
		// 		TODO currently move has no EXP option add restOfWait restOfMove EXP option to wait and move
		MoveNode newMove = new MoveNode();
		if (s.hasNext(MOVEPAT)) {
			newMove.setMoveType(ActionType.move);
			newMove.setNumMoves(1);
			require(MOVEPAT, "no move ", s);}
//			return parseMove(s);}
		else if (s.hasNext(TURN_L)){
			newMove.setMoveType(ActionType.turnL);
			require(TURN_L, "no turnL ", s);
		}
		else if (s.hasNext(TURN_R)){
			newMove.setMoveType(ActionType.turnR);
			require(TURN_R, "no turnR ", s);
		}
		else if (s.hasNext(TURN_AR)){
			newMove.setMoveType(ActionType.turnAround);
			require(TURN_AR, "no turnAround ", s);
		}
		else if(s.hasNext(SHIELDON)) {
			newMove.setMoveType(ActionType.shieldOn);
			require(SHIELDON, "no ShieldOn ", s);
		}
		else if(s.hasNext(SHIELDOFF)) {
			newMove.setMoveType(ActionType.shieldOff);
			require(SHIELDOFF, "no ShieldOff ", s);
		}
		else if (s.hasNext(TAKEFUEL)){
			newMove.setMoveType(ActionType.takeFuel);
			System.out.println("TakeFuel started");
			require(TAKEFUEL, "no wait ", s);
		}
		else if (s.hasNext(WAIT)){
			newMove.setMoveType(ActionType.wait);
			newMove.setNumWaits(1);
			require(WAIT, "no wait ", s);
		}
		else fail ("parseAct unknown or missing moveType ", s);
		require(SEMIC, "parseAct missing semicolon on: " + newMove.moveType, s);
		return newMove;}

//	I replaced all the individual nodes with one MoveNode with enum states
//	public static RobotProgramNode parseMove(Scanner s){
//		MoveNode newMove = new MoveNode();
//		newMove.setMoveType(ActionType.move);
//		newMove.setNumMoves(1);
//		if(!s.hasNext()) {fail("Empty expression", s);}
//		System.out.println("303 Move started");
//		require(MOVEPAT, "no move ", s);
////		newMove.setMoveType(newMove.move);
////		allow second type of move which holds an expression - move2
////		restOfMove = "" | "//("
//		int numMoves = 0;
//		for (int i = 0;i < numMoves; i++)
//			return newMove;
////		else if(s.hasNext("\\(")){
////		// find out if the term inside the brackets is valid and evaluate it
////		require and element and a close bracket
////			return move2(term);
////		}
//		require (SEMIC, "parseMove missing semicolon on move ", s);
//		return newMove;
//	}

//	region parse ASSIGNVAR, VAR, EXPRESSION, SENSES, OPERATIONS, RELATIVE OPS, TERMS, FACTORS, NUMBER

// TRY two layers of OPNodes
//	OP   ::= "add" | "sub"
//	OP2   ::= "mul" | "div"

	private static RobotProgramNode parseAssignVar(Scanner s) {
//	ASSGN ::= VAR "=" EXP
	 return null;
 }

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

	private static RobotProgramNode parseVar(Scanner s) {
//	VAR   ::= "\\$[A-Za-z][A-Za-z0-9]*"
		return null;
	}

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

//endregion

	// utility methods for the parser FAIL REQUIRE REQUIRE_INT checkFor
//region FAIL REQUIRE REQUIRE_INT checkFor

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
//NODE CLASSES

//region TERMINAL ACTION NODES: MOVE TURN WAIT FUEL SHIELD


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
