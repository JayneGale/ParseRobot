import java.util.ArrayList;

public class StatementNode implements RobotProgramNode {
    ArrayList<RobotProgramNode> StatTree = new ArrayList<>();
    public String toString() {
        return "Statement node ";
    }
    public void execute(Robot robot) {
        //		robot.turnAround(); empty for Statement Node
        //		call the Programme elements to execute  I need a an array or list of elements in the programme
    }
}
