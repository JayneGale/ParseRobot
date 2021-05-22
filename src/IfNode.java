public class IfNode implements RobotProgramNode {
    public BlockNode block;
    public CondNode cond;
//    public boolean ifBool;

    public String toString() {
        return "If node block " + block.toString() + " and condition is " + cond.getCondBool();
    }
    public void execute(Robot robot) {
        if(cond.getCondBool()) {
            if (block != null) {
                block.execute(robot);
            }
        }
    }

//    public RobotProgramNode SetIfBlock(BlockNode block){
//        this.block = block;
//        return block;
//    }
//
//    public Boolean setIfBool(CondNode cond){
//        return cond.getCondBool();
//    }

//    public Boolean getIfBool(){
//        return ifBool;
//    }

    //    add a Block RobotProgN and a
//    Condition that returns a boolean

}

