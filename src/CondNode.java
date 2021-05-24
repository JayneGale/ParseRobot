public abstract class CondNode implements RobotValueNode {

public Boolean condBool;
public RelOpType relOpType;
RobotValueNode exp1Node;
RobotValueNode exp2Node;
public Robot robot;

public int getExpVal(RobotValueNode e) {
        int exp = e.eval(robot);
        return exp;
    }
//public void setExp2(ExpNode e) {
//        exp2 = e.getResult();
//    }

public boolean evaluateCond(RelOpType reloptype, RobotValueNode exp1Node, RobotValueNode exp2Node){
        this.relOpType = reloptype;
        int exp1 = getExpVal(exp1Node);
        int exp2 = getExpVal(exp2Node);
        boolean result = false;
        switch (relOpType) {
            case lt: result = (exp1 < exp2) ; break;
            case gt: result = (exp1 > exp2) ; break;
            case eq: result = (exp1 == exp2); break;
            default: System.out.println("relOpType not found "); break;
        }
        return result;
    }

//Stage 4    COND  ::= "and" "(" COND "," COND ")" | "or" "(" COND "," COND ")" | "not" "(" COND ")"  |
//    RELOP "(" EXP "," EXP ")

//Stage 2    COND  ::= RELOP "(" SEN "," NUM ")"
//    Stage 2 implement RELOP "(" EXP "," EXP ")"

    public Boolean getCondBool() {
        return condBool;
    }
    public String toString() {
            return ("CondNode : boolResult " + condBool);
    }

    public int eval(Robot robot){
    boolean condition = evaluateCond(relOpType, exp1Node, exp2Node);
    System.out.println("eval Cond " + relOpType + " exp1 " + exp1Node.toString() + " exp2 " + exp2Node.toString());
    int result;
    if(condition) result = 1; //true
    else result = 0;/*false*/
    return result;
//    infinite loop
        //		evaluate the CondNode elements to execute actually I need a different ActNode that is a RobotProgrammeNode
    }
}

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
