public class NumNode implements RobotProgramNode {
    public int num = 0;
//    NUM   ::= "-?[1-9][0-9]*|0"

    public void setNum (int num) {
        this.num = num;
    }
    public int getNum () {
        this.num = num;
        return num;
    }

    public void execute(Robot robot)
    {setNum(num);
    System.out.println("set num to " + num);}
    public String toString() {
        return "numNode number " + num;
    }
}
