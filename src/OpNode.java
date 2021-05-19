public class OpNode implements RobotProgramNode {
// Stage 2   OP    ::= "add" | "sub" | "mul" | "div"
// Stage 4   OP    ::= "+" | "-" | "*" | "/"

    Optype opType;
    int num1;
    int num2;
    int result;

    public void setOpType(Optype opType) {
        this.opType = opType;
    }

    public Optype getOpType() {
        return this.opType;
    }

    public int calculate(int num1, int num2) {
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

    public void execute(Robot robot) {
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
        switch (opType) {
            case add:
                return num1 + " add " + num2 + " is " + result;
            case sub:
                return num1 + " sub " + num2 + " is " + result;
            case mul:
                return num1 + " mul " + num2 + " is " + result;
            case div:
                return num1 + " div " + num2 + " is " + result;
            case plus:
                return num1 + " + " + num2 + " is " + result;
            case minus:
                return num1 + " - " + num2 + " is " + result;
            case times:
                return num1 + " * " + num2 + " is " + result;
            case divide:
                return num1 + " / " + num2 + " is " + result;
            default:
                return ("OpType enum not found ");
        }

    }
}
