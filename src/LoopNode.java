import java.util.ArrayList;

public class LoopNode implements RobotProgramNode{
//    ArrayList<RobotProgramNode> loopTree = new ArrayList<>();
//
//    public void addToBlock(RobotProgramNode b) {
//        loopTree.add(b);
//    }
    public String toString() {
//        return "LoopNode: loopTree size " + loopTree.size();
                return "LoopNode: loopTree without size ";
    }
    public void execute(Robot robot) {
        //		Loop node endlessly calls its Block of Actions/Other Expressions
        // run thru to block list and execute each in turn
        while (true) {
            execute(robot);
            //			Arraylist of robot statements
//            for (RobotProgramNode n : loopTree) {
//                n.execute(robot);
//            }
        }
    }
}
