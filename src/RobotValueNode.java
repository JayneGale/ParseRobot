import java.util.ArrayList;

    interface RobotValueNode {
        Optype setOptype(Optype optype);
        int eval(Robot robot);
        String toString();
    }

//    This is for all Expressions to return an integer
//    maybe later double or other number

//EXP   ::= NUM | SEN | OP "(" EXP "," EXP ")"

//Operation nodes
class AddValueNode implements RobotValueNode {
    private RobotValueNode left, right;
    private Optype thisOptype = Optype.add;

    public AddValueNode(RobotValueNode lt, RobotValueNode rt) {
        left = lt; right = rt;
    }
    public String toString(){
        return  "add("+left+","+right+")";
    }
    public Optype setOptype(Optype optype){
        thisOptype = optype;
        return thisOptype;
    };

    public int eval(Robot robot) {
        int result = 0;
        result = left.eval(robot) + right.eval(robot);
        return result;
    }
    }
class OpValueNode implements RobotValueNode {
    private RobotValueNode left, right;
    private Optype thisOptype = Optype.add;

    public OpValueNode(RobotValueNode lt, RobotValueNode rt) {
        left = lt;
        right = rt;
    }

    public Optype setOptype(Optype optype) {
        thisOptype = optype;
        return thisOptype;
    }

    ;

    public String toString() {
        return "add(" + left + "," + right + ")";
    }

    public int eval(Robot robot) {
        {
            int result = 0;
            result = left.eval(robot) + right.eval(robot);
            return result;
        }
    }
}

//    class AddNode implements RobotNode {
//        final ArrayList<RobotNode> children;
//        public AddNode(ArrayList<RobotNode> chn){
//            children = chn; }
//
//    @Override
//    public int eval() {
//        return 0;
//    }
//
//    public String toString() {
//    //            copied from class notes
//    //            String result = "[";
//    //            for (RobotNode n : children){result += n.toString();}
//    //             return result + "]";
//    //            this is terrible code assumes we know the index positions
//            return children.get(2) + " + " + children.get(4);
//        }
//        }

//class SubValueNode implements RobotValueNode {
//    final ArrayList<RobotValueNode> children;
//    public SubValueNode(ArrayList<RobotValueNode> chn){
//        children = chn; }
//    public String toString() {
////            copied from class notes
//        return children.get(2) + " - " + children.get(4);
//    }
//    public int eval() {
//        int result = 0;
//        for (RobotValueNode n : children){result -= n.eval();}
//        return result;
//    }
//}
//
//class MulValueNode implements RobotValueNode {
//    final ArrayList<RobotValueNode> children;
//    public MulValueNode(ArrayList<RobotValueNode> chn){
//        children = chn; }
//    public String toString() {
//        return children.get(2) + " * " + children.get(4);
//    }
//    public int eval() {
//        int result = 0;
//        for (RobotValueNode n : children){result *= n.eval();}
//        return result;
//    }
//}
//class DivValueNode implements RobotValueNode {
//    final ArrayList<RobotValueNode> children;
//    public DivValueNode(ArrayList<RobotValueNode> chn){
//        children = chn; }
//    public String toString() {
//        return children.get(2) + " / " + children.get(4);
//    }
//    public int eval() {
//        int result = 0;
//        for (RobotValueNode n : children) {
//            try {
//                result /= n.eval();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//}

// Types of Node Number (for Division or Multiplication) or Term (for Addition and Subtraction)

// a Term is an expression so might be 3*5+2 3*5 is one term and 2 is the other term
//class TermNode implements RobotNode {
//    final int value;
//    public TermNode(int v){ value = v; }
//    public String toString() { return value + ""; }
//    public int eval() { return this.value; }
//}


//class ActNode implements RobotNode {
//    final String value;
//    public ActNode(String v){ value = v; }
//    public String toString() { return value; }
//    public int eval() { return 0; }
//
//    public Robot execute(Robot robot) {
////        do these actions
//        return null;
//    }
//}

