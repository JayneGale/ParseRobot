public class IfNode implements RobotProgramNode {
    //    condition that returns a boolean from
    //    block RobotProgN
    //    elseBlock RobotProgNode
    RobotBoolNode cond;
    BlockNode block;
    BlockNode elseBlock;

    public String toString() {
        return "IfNode block " + block.toString() + " and condition is " + cond.toString();
    }
//    result = 0 false and result 1 = true

    public void execute(Robot robot) {
        boolean isTrue = cond.evalBool(robot);
        if (isTrue) {
            if (block != null) {
                block.execute(robot);
                System.out.println("IfNode bool " + cond.toString() + " cond.eval " +  " if blocksize " + isTrue + block.blockList.size());
            }
        }
        else {
            if (elseBlock != null) {
                elseBlock.execute(robot);
                System.out.println("IfNode bool " + cond.toString() + " cond.eval " +  " else blocksize " + elseBlock.blockList.size());
            }
        }
    }

}

