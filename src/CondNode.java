public class CondNode implements RobotProgramNode{

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

public Boolean condBool;
public RelOpType relOpType;
public int exp1 = 0;
public int exp2 = 0;
public Robot robot;

//public void setRelOpType(RelOpNode r) {
//        relOpType = r.relOpType;
//    }

//public void setExp1(ExpNode e) {
//        exp1 = e.getResult();
//    }
//public void setExp2(ExpNode e) {
//        exp2 = e.getResult();
//    }

public boolean evaluateCond(RelOpType reloptype, int exp1, int exp2){
        this.relOpType = reloptype;
        this.exp1 = exp1;
        this.exp2 = exp2;
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
    public void execute(Robot robot){
    System.out.println("eval Cond" + relOpType + exp2 + exp2);
    boolean condition = evaluateCond(relOpType, exp1, exp2);

        if(condition) execute(robot);
        //		evaluate the CondNode elements to execute actually I need a different ActNode that is a RobotProgrammeNode
    }
}

