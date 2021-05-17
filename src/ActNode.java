public class ActNode implements RobotProgramNode {
    //	for now, one action, there are likely several so need an array of actNodes and a for act in Actnodes on the toString and execute methods
    public String toString() {
        return "Act node ";
    }
    public void execute(Robot robot) {
//		ActNode has no direct execute method, it just calls the Acts directly
    }
}
