import java.util.ArrayList;

public class ExpNode implements RobotValueNode {
    public ExpType expType;

//    EXP   ::= NUM | SEN | OP "(" EXP "," EXP ")"
    public void setExpType(ExpType expType) {
        this.expType = expType;
    }
    int result = 0;
    public ExpType getExpType () {
        return this.expType;
    }

//        public void childType() {
//        switch (expType) {
//            case num:
//                result = NumNode(); break;

//    public int getResult() {
//        switch (expType) {
//            case num:
//                NumNode n = new NumNode();
////                result = n.getNum(); break;
//            case sen:
//                SenNode s = new SenNode();
//                result = s.getNum();
//                break;
//            case op:
//                OpNode o = new OpNode();
//                result = o.result;
//                break;
//            default:
//                System.out.println("relOpType not found ");
//                result = 0;
//                break;
//        }
//        return result;
//    }

    public void execute(Robot robot) {
//        switch (expType) {
//            case num -> result = num;
//            case sen -> result = result;
//            case op -> result = value;
//         TODO put stuff here
    }

    @Override
    public Optype setOptype(Optype optype) {
        return null;
    }

    @Override
    public int eval(Robot robot) {
        return 0;
    }

    public String toString() {
        return "ExpNode expType " + expType;
    }
}

