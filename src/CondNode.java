public class CondNode implements RobotBoolNode {
    //      Stage 1    COND  ::= RELOP "(" SEN "," NUM ")"
//      Stage 2 generalise to  RELOP "(" EXP "," EXP ")"
//      Stage 2 further generalise
//          COND  ::= "and" "(" COND "," COND ")" | "or" "(" COND "," COND ")" | "not" "(" COND ")"  |
//          RELOP "(" EXP "," EXP ")
    RelOpType relOpType;
    RobotValueNode exp1Node;
    RobotValueNode exp2Node;
    LogicalOp logicalOp = LogicalOp.relop;
    // hard coded at first to debug switch to bool

    public LogicalOp SetlogicalOpType(LogicalOp logicalOpType) {
        return logicalOpType;
    }

    public int getExpVal(RobotValueNode e, Robot robot) {
        return e.eval(robot);
    }

    public boolean evalBool(Robot robot) {
        System.out.println("Cond eval: Is exp1 " + exp1Node.toString() + " " + relOpType + " exp2 " + exp2Node.toString() + " true? " + evaluateCond(relOpType, exp1Node, exp2Node, robot));
        return evaluateCond(relOpType, exp1Node, exp2Node, robot);
    }

    public boolean evaluateCond(RelOpType relOpType, RobotValueNode exp1Node, RobotValueNode exp2Node, Robot robot) {
        int exp1 = getExpVal(exp1Node, robot);
        int exp2 = getExpVal(exp2Node, robot);
        boolean isTrue = false;
        switch (relOpType) {
            case lt:
                isTrue = (exp1 < exp2);
                break;
            case gt:
                isTrue = (exp1 > exp2);
                break;
            case eq:
                isTrue = (exp1 == exp2);
                break;
            default:
                System.out.println("CondNode error: relOpType not found ");
                break;
        }
        return isTrue;
    }

    public String toString() {
        return ("CondNode : isTrue? " + exp1Node.toString() + " " + relOpType + " " + exp2Node.toString());
    }
}

//Stage 4    COND  ::= "and" "(" COND "," COND ")" | "or" "(" COND "," COND ")" | "not" "(" COND ")"  |
//    RELOP "(" EXP "," EXP ")

//     condition nodes (Cond, LessThan, etc) are a different type from RobotProgramNode
//     since they do not need an execute method, but
//     instead need an
//     - evaluate method which takes a robot as an argument and returns a boolean value.
//     You will need to define an interface type for this category of node.

//    A condition node evaluates the whole expression so it needs 3 elements
//    - the operator (initially gt lt or eq)
//    and the integer values of the left and right expressions exp 1 and exp 2
//    if the expression type is
//    num, = the final number of a NumNode = int
//    sen, = current sen reading (in execute) = int
//    op = result of the operation (add, sub etc) = int
//    ie exp always has to return an int
