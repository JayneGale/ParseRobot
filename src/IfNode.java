public class IfNode implements RobotProgramNode {
    private BlockNode block;
    private CondNode cond;

    public String toString() {
        return "If node block " + block.toString() + " and condition is " + cond.getCondBool();
    }

    public RobotProgramNode addIfBlock(BlockNode block){
        this.block = block;
        return block;
    }

        public Boolean ifCondBool(CondNode cond){
        //        what do I put here? - has to have done the calculation and returned the
        boolean result = cond.getCondBool();
        return result;
    }
    //    add a Block RobotProgN and a
//    Condition that returns a boolean

    public void execute(Robot robot) {
//        if(cond) execute Block

        //		execute(robot);
    }
}

