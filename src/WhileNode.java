public class WhileNode implements RobotProgramNode {
//    a Block RobotProgN and a
//    Condition that returns a boolean

    CondNode cond;
    BlockNode block;

    public String toString() {
        return "While node ";
    }

    public void execute(Robot robot) {
        boolean isTrue = cond.evalBool(robot);
        while(isTrue){
            if(block != null) {
                block.execute(robot);
                isTrue = cond.evalBool(robot);
            }
        }
        System.out.println("WhileNode condition " + cond.toString() + " cond.eval " +  isTrue + block.blockList);
    }
}

