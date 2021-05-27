public class OpNode implements RobotValueNode {
// Stage 2   OP    ::= "add" | "sub" | "mul" | "div"
// Stage 4   OP    ::= "+" | "-" | "*" | "/"

    Optype opType;
    RobotValueNode leftNode, rightNode;

    public OpNode(Optype optype, RobotValueNode lt, RobotValueNode rt){
        this.opType = optype;
        leftNode = lt;
        rightNode = rt;
    }

    public Optype setOptype(Optype opType) {
        opType = this.opType;
        return opType;
    }
    public int evalOp(Optype opType, RobotValueNode leftNode, RobotValueNode rightNode, Robot robot){
        int num1 = leftNode.eval(robot);
        int num2 = rightNode.eval(robot);
        int result;
        System.out.println("Cond eval: Is exp1 " + leftNode.toString() + " " + rightNode + " exp2 " + rightNode.toString());
        switch (opType) {
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

    public int eval(Robot robot) {
        return evalOp(opType, leftNode, rightNode, robot);
    }

    public String toString() {
        String num1 = leftNode.toString();
        String num2 = rightNode.toString();

            switch (opType) {
            case add:
                return num1 + " add " + num2;
            case sub:
                return num1 + " sub " + num2;
            case mul:
                return num1 + " mul " + num2;
            case div:
                return num1 + " div " + num2;
            case plus:
                return num1 + " + " + num2;
            case minus:
                return num1 + " - " + num2;
            case times:
                return num1 + " * " + num2;
            case divide:
                return num1 + " / " + num2;
            default:
                return ("OpType enum not found ");
        }
    }
}
