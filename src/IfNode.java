public class IfNode implements RobotProgramNode {
    private RobotProgramNode block = new BlockNode();
    private CondNode cond = new CondNode();
    public boolean ifBool;

    public String toString() {
        return "If node block " + block.toString() + " and condition is " + cond.getCondBool();
    }

    public RobotProgramNode SetIfBlock(RobotProgramNode block){
        this.block = block;
        return block;
    }

    public Boolean setIfBool(CondNode cond){
        boolean result = cond.getCondBool();
        return result;
    }

    public Boolean getIfBool(){
        return ifBool;
    }

    //    add a Block RobotProgN and a
//    Condition that returns a boolean

    public void execute(Robot robot) {
        if(cond.getCondBool()) {
            if (block != null) {
                block.execute(robot);
            }
        }
    }
}

