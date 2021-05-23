public class NumNode implements RobotValueNode {
    private int number;

    public NumNode(int number){
        int thisNumber = number;
    }
// from notes:
//      class NumNode implements Node {
//        private int value;
//        public NumNode(int value) {
//            this.value = value;
//        }
        //    public AddValueNode(RobotValueNode lt, RobotValueNode rt) {
//        left = lt; right = rt;
//    }

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

//    public void setNum (int num) {
//        this.num = num;
//    }
//    public int getNum () {
//        this.num = num;
//        return num;
//    }

