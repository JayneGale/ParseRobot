import java.util.ArrayList;

public class BlockNode implements RobotProgramNode{
    public ArrayList<RobotProgramNode> blockList = new ArrayList<>();
    public String toString() {
        return ("BlockNode: blockList size " + blockList.size());
    }
    //		call the BlockNode elements to execute
    public void addToBlock(RobotProgramNode b) {
        blockList.add(b);
    }
    public void execute(Robot robot) {
        if (blockList != null) {
            while (true) {
                for (RobotProgramNode b : blockList) {
                    b.execute(robot);
                }
            }
        }
    }
}
