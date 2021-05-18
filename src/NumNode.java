public class NumNode implements RobotProgramNode {
    public int num = 10;
    public void setNum (int num) {
        this.num = num;
    }
    public int getNum () {
        this.num = num;
        return num;
    }

    public void execute(Robot robot) {
            }
    public String toString() {
        return "numNode number " + num;
    }
}
