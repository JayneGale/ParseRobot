public class CondNode implements RobotProgramNode{

public Boolean condBool = false;
public RelOpType relOpType = null;

public void setRelOpType(RelOpNode r) {
//    ToDo make parseRelop return a relopNode
        relOpType = r.relOpType;
    }

public int exp1 = 0;
public int exp2 = 0;
public void setExp1(ExpNode e) {
//    ToDo make parseExp return a ExpNode
        exp1 = e.getResult();
    }
public void setExp2(ExpNode e) {
//    ToDo make parseExp return a ExpNode
        exp2 = e.getResult();
    }

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

    public void setCondBool(RelOpNode relOp) {
        condBool = relOp.relOpBool;
    }
    public Boolean getCondBool() {
        return condBool;
    }

    public String toString() {
            return ("CondNode : boolResult " + condBool);
    }
    public void execute(Robot robot){
      		execute(robot);
        //		evaluate the CondNode elements to execute actually I need a different ActNode that is a RobotProgrammeNode
    }
}

