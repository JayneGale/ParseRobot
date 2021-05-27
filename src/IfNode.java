public class IfNode implements RobotProgramNode {
    //    condition that returns a boolean from
    //    block RobotProgN
    //    elseBlock RobotProgNode
    CondNode cond;
    BlockNode block;
    BlockNode elseBlock;

    public String toString() {
        return "IfNode block " + block.toString() + " and condition is " + cond.toString();
    }
//    result = 0 false and result 1 = true
    public void execute(Robot robot) {
        int result = cond.eval(robot);
        if (result < 0 || result > 1) {System.out.println("IfNode cond.eval not 0 or 1 " +  result); }
        boolean bool = result >= 1;
        if (bool) {
            if (block != null) {
                block.execute(robot);
                System.out.println("IfNode bool " + cond.toString() + " cond.eval " +  " if blocksize " + result + block.blockList.size() +  " else blocksize " + elseBlock.blockList.size());
            }
        }
        else {
            if (elseBlock != null) {
                elseBlock.execute(robot);
                System.out.println("IfNode bool " + cond.toString() + " cond.eval " +  " if blocksize " + result + block.blockList.size() +  " else blocksize " + elseBlock.blockList.size());
            }
        }
    }

}

