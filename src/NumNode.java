public class NumNode implements RobotValueNode {
    private int number;

    public NumNode(int number){
        this.number = number;
    }

    public Optype setOptype(Optype optype) {
        return Optype.num;
    }

    public int eval(Robot robot) {
        return number;
    }

    public String toString() {
        return "numNode number " + number;
    }
}
//    NUM   ::= "-?[1-9][0-9]*|0"


