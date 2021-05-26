import java.util.ArrayList;

public class BlockNode implements RobotProgramNode{
    public ArrayList<RobotProgramNode> blockList = new ArrayList<>();
    public void addToBlock(RobotProgramNode b) {
        blockList.add(b);
    }

    public String toString() {
        return ("BlockNode: blockList size " + blockList.size());
    }
    //		call the BlockNode elements to execute
    public void execute(Robot robot) {
        if (blockList != null) {
                for (RobotProgramNode b : blockList) {
                    b.execute(robot);
                }
        }
    }
}

