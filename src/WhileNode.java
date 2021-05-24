public class WhileNode implements RobotProgramNode {

    CondNode cond;
    public BlockNode block;

    public String toString() {
        return "While node ";
    }

    public void execute(Robot robot) {
        int result = cond.eval(robot);
        boolean bool;
        if(result == 0 || result == 1){
            bool = (result == 0) ? false : true;}
        else System.out.println("WhileNode cond.eval not 0 or 1 " +  result); bool = false;
        while(bool){
            if(block != null) {
                block.execute(robot);
            }
        }
        System.out.println("IFNode condition " + cond.toString() + " cond.eval " +  result);
    }
}

