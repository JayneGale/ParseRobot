public class CondNode implements RobotProgramNode{
//    public ArrayList<RobotProgramNode> blockList = new ArrayList<>();
//    //		call the BlockNode elements to execute
//    public void addToBlock(RobotProgramNode b) {
//        blockList.add(b);
//    }
//    public void execute(Robot robot) {
//        if (blockList != null) {
//            while (true) {
//                for (RobotProgramNode b : blockList) {
//                    b.execute(robot);
//                }
//            }
//        }
//    }
    public Boolean condBool = false;

    public void setCondBool(RelOpNode relOp) {
        condBool = relOp.relOpBool;
    }
    public Boolean getCondBool() {
        return condBool;
    }

    public String toString() {
            return ("CondNode : boolResult " + condBool);
    }
    public void execute(Robot robot){
//        		execute(robot);
        //		evaluate the CondNode elements to execute actually I need a different ActNode that is a RobotProgrammeNode
    }
}

