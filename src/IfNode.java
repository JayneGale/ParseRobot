public class IfNode implements RobotProgramNode {
//    a Block RobotProgN and a
//    Condition that returns a boolean

    BlockNode block;
    CondNode cond;

    public String toString() {
        return "IfNode block " + block.toString() + " and condition is " + cond.toString();
    }
    public void execute(Robot robot) {
        int result = cond.eval(robot);
        if (result < 0 || result > 1) {System.out.println("WhileNode cond.eval not 0 or 1 " +  result); }
        boolean bool = result >= 0;
        if(bool) {
            if (block != null) {
                block.execute(robot);
            }
        }
        System.out.println("IfNode condition " + cond.toString() + " cond.eval " +  result);
    }


}

