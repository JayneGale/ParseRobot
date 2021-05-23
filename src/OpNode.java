public class OpNode implements RobotValueNode {
// Stage 2   OP    ::= "add" | "sub" | "mul" | "div"
// Stage 4   OP    ::= "+" | "-" | "*" | "/"

    private Optype thisOpType;
    private RobotValueNode leftNum, rightNum;
    Robot robot;

    public OpNode(Optype optype, RobotValueNode lt, RobotValueNode rt){
        this.thisOpType = optype;
        leftNum = lt;
        rightNum = rt;
    }
    int num1 = leftNum.eval(robot);
    int num2 = rightNum.eval(robot);
    int result;

    public Optype setOptype(Optype opType) {
        thisOpType = opType;
        return thisOpType;
    }

    public int eval(Robot robot) {
//    public int calculate(int num1, int num2) {
        switch (thisOpType) {
            case add:
            case plus:
                result = (num1 + num2);
                break;
            case sub:
            case minus:
                result = (num1 - num2);
                break;
            case mul:
            case times:
                result = (num1 * num2);
                break;
            case div:
            case divide:
                result = (int) (num1 / (double) num2);
                break;
            default:
                result = 0;
                System.out.println("OpType not found ");
                break;
        }
        return result;
    }

    public void execute(Robot robot) {
        switch (thisOpType) {
            case add:
            case plus:
                result = (num1 + num2);
                break;
            case sub:
            case minus:
                result = (num1 - num2);
                break;
            case mul:
            case times:
                result = (num1 * num2);
                break;
            case div:
            case divide:
                if(num2 == 0){System.err.println("zero divisor" + num2);
                result = 0;
                }
                else{
                    double num2d = num2;
                    result = (int) Math.ceil(num1 / num2d);
                } break;
            default:
                System.out.println("OpType not found ");
                break;
        }
    }

    public String toString() {
        switch (thisOpType) {
            case add:
                return leftNum + " add " + rightNum + " is " + result;
            case sub:
                return leftNum + " sub " + rightNum + " is " + result;
            case mul:
                return leftNum + " mul " + rightNum + " is " + result;
            case div:
                return leftNum + " div " + rightNum + " is " + result;
            case plus:
                return leftNum + " + " + rightNum + " is " + result;
            case minus:
                return leftNum + " - " + rightNum + " is " + result;
            case times:
                return leftNum + " * " + rightNum + " is " + result;
            case divide:
                return leftNum + " / " + rightNum + " is " + result;
            default:
                return ("OpType enum not found ");
        }

    }
}
