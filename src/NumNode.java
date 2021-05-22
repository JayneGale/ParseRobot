public class NumNode implements RobotProgramNode {
    private int number;
    public NumNode(int number){
        this.number = number;
    }
    public void execute(Robot robot){ System.out.println("set num to " + number);}
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

