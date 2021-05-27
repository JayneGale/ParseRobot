public class NumNode implements RobotValueNode {
//Stage 2    NUM   ::= "-?[1-9][0-9]*|0"

    int number;

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

    public int setNum(int value) {
        number = value;
        return value;
    }
    public void execute(Robot robot){ eval(robot);
    }
}


