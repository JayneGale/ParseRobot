import java.util.ArrayList;

public class ProgramNode implements RobotProgramNode {
    ArrayList<RobotProgramNode> nodeTree = new ArrayList<>();
    public void addToArray(RobotProgramNode r) {
        nodeTree.add(r);
    }
    public String toString() {
        return "Node tree size: " + nodeTree.size();
    }

    //		call all the Programme elements to execute in order
    public void execute(Robot robot) {
        for (RobotProgramNode n : nodeTree) {
            n.execute(robot);
        }
    }
}

