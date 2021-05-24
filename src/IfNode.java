public class IfNode implements RobotProgramNode {
    public BlockNode block;
    public CondNode cond;
//    public boolean ifBool;

    public String toString() {
        return "If node block " + block.toString() + " and condition is " + cond.getCondBool();
    }
    public void execute(Robot robot) {
        int result = cond.eval(robot);
        boolean bool;
        if(result == 0 || result == 1){
            bool = (result == 0) ? false : true;}
        else System.out.println("IfNode cond.eval not 0 or 1 " +  result); bool = false;
        if(bool) {
            if (block != null) {
                block.execute(robot);
            }
        }
        System.out.println("IFNode condition " + cond.toString() + " cond.eval " +  result);
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

