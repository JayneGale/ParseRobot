public class IfNode implements RobotProgramNode {
    public BlockNode block;
    public CondNode cond;

    public String toString() {
        return "IfNode block " + block.toString() + " and condition is " + cond.toString();
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
        System.out.println("IfNode condition " + cond.toString() + " cond.eval " +  result);
    }

//    a Block RobotProgN and a
//    Condition that returns a boolean

}

