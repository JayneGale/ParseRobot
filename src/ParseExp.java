import java.util.*;

public class ParseExp {
//    public static void main(String[] a) {
//        if (a.length == 1) {
//            Scanner s = new Scanner(a[0]);
//            RobotNode n = parseExpNode(s);
//            System.out.println(n);
//            System.out.println("Evaluated to: " + n.eval());
//	    /*
//	    if (parseExp(s)) {
//		if (!s.hasNext()) {
//		    System.out.println("Successfully parsed the input: " + a[0]);
//		} else {
//		    System.out.println("While the start was great, the rest is still unparsed: " + a[0]);
//		}
//	    } else {
//		System.out.println("Failed to parse the input: " + a[0]);
//	    }
//	    */
//        } else {
//            System.out.println("Give me one argument and separate everything by spaces.");
//        }
//    }

    public static boolean parseExp(Scanner s) {
        if (s.hasNext("[-+]?[0-9]+")) { // number
            s.next(); // eat token
            return true;
        }
        if (s.hasNext("add") || s.hasNext("sub") || s.hasNext("mul") || s.hasNext("div")) {
            return parseOp(s);
        }
        return false;
    }

    public static boolean parseOp(Scanner s) {
        if (s.hasNext("add") || s.hasNext("sub") || s.hasNext("mul") || s.hasNext("div")) {
            s.next();
        } else {
            return false;
        }
        if (s.hasNext("\\(")) {
            s.next();
        } else {
            return false;
        }
        if (!parseExp(s)) {
            return false;
        }
        if (s.hasNext(",")) {
            s.next();
        } else {
            return false;
        }
        if (!parseExp(s)) {
            return false;
        }
        if (s.hasNext("\\)")) {
            s.next();
        } else {
            return false;
        }
        return true;
    }

    public static void fail(String errorMsg, Scanner s) {
        String msg = "Parse Error: " + errorMsg + " @... ";
        for (int i = 0; i < 5 && s.hasNext(); i++)
            msg += " " + s.next();

        throw new RuntimeException(msg);
    }
}
//    public static RobotNode parseExpNode(Scanner s) {
//        if (!s.hasNext()) 	{ fail("Empty expr",s); }
//        RobotNode child = null;
//        if (s.hasNext("-?\\d+"))	{ child = parseNum(s);}
//        else if (s.hasNext("add")) 	{ child = parseAdd(s); }
//        //	else if (s.hasNext("sub")) 	{ child = parseSub(s); }
//        //	else if (s.hasNext("mul")) 	{ child = parseMul(s); }
//        //	else if (s.hasNext("div")) 	{ child = parseDiv(s); }
//        else { fail("not an expression", s); }
//        return new ExprNode(child);
//    }

//    public static RobotNode parseNum(Scanner s) {
//        if (!s.hasNextInt()) {
//            fail("not an integer", s);
//        }
//        return new NumNodeV(s.nextInt());
//    }

    //    public Node parseNum(Scanner s){
//        if (!s.hasNext("[-+]?\\d+")){
//            fail("Expecting a number",s);
//        }
//        return new NumNode(s.nextInt(t));
//    }
//    }

