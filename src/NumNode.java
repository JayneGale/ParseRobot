public class NumNode implements RobotProgramNode {
    public int num = 1;
    public void setNum (int num) {
        this.num = num;
    }
    public void execute(Robot robot) {
            }
    public String toString() {
        return "numNode number " + num;
    }
}
