import java.util.ArrayList;


    interface RobotNode {
        public int eval();
        public String toString();
    }
//Operation nodes
class AddNode implements RobotNode {
    private RobotNode left, right;
    public AddNode(RobotNode lt, RobotNode rt) {
        left = lt; right = rt;
    }
    public String toString(){
        return  "add("+left+","+right+")";
    }
    public int eval() {
        int result = 0;
        result = left.eval() + right.eval();
    return result;
    }
    }
//    class AddNode1 implements RobotNode {
//        final ArrayList<RobotNode> children;
//        public AddNode(ArrayList<RobotNode> chn){
//            children = chn; }
//        public String toString() {
//    //            copied from class notes
//    //            String result = "[";
//    //            for (RobotNode n : children){result += n.toString();}
//    //             return result + "]";
//    //            this is terrible code assumes we know the positions
//            return children.get(2) + " + " + children.get(4);
//        }
//        }
//    }
class SubNode implements RobotNode {
    final ArrayList<RobotNode> children;
    public SubNode(ArrayList<RobotNode> chn){
        children = chn; }
    public String toString() {
//            copied from class notes
        return children.get(2) + " - " + children.get(4);
    }
    public int eval() {
        int result = 0;
        for (RobotNode n : children){result -= n.eval();}
        return result;
    }
}

class MulNode implements RobotNode {
    final ArrayList<RobotNode> children;
    public MulNode(ArrayList<RobotNode> chn){
        children = chn; }
    public String toString() {
        return children.get(2) + " * " + children.get(4);
    }
    public int eval() {
        int result = 0;
        for (RobotNode n : children){result *= n.eval();}
        return result;
    }
}
class DivNode implements RobotNode {
    final ArrayList<RobotNode> children;
    public DivNode(ArrayList<RobotNode> chn){
        children = chn; }
    public String toString() {
        return children.get(2) + " / " + children.get(4);
    }
    public int eval() {
        int result = 0;
        for (RobotNode n : children) {
            try {
                result /= n.eval();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

// Types of Node Number (for Division or Multiplication) or Term (for Addition and Subtraction)
class NumNode implements RobotNode {
    final int value;
    public NumNode(int v){ value = v; }
    public String toString() { return value + ""; }
    public int eval() { return this.value; }
}

// a Term is an expression so might be 3*5+2 3*5 is one term and 2 is the other term
//class TermNode implements RobotNode {
//    final int value;
//    public TermNode(int v){ value = v; }
//    public String toString() { return value + ""; }
//    public int eval() { return this.value; }
//}

//class ExprNode implements RobotNode {
//    final RobotNode child;
//    public ExprNode(RobotNode c) { this.child = c; }
//    public String toString() { return "[" + this.child + "]"; }
//    public int eval() { return this.child.eval(); }
//}

class ActNode implements RobotNode {
    final String value;
    public ActNode(String v){ value = v; }
    public String toString() { return value; }
    public int eval() { return 0; }
}

