public class WhileNode implements RobotProgramNode {
//    a Block RobotProgN and a
//    Condition that returns a boolean

    CondNode cond;
    BlockNode block;

    public String toString() {
        return "While node ";
    }

    public void execute(Robot robot) {
        int result = cond.eval(robot);
        if(result < 0 || result > 1){System.out.println("WhileNode cond.eval not 0 or 1 " +  result);}
        // cond.eval returns an int: 0 for false and 1 for true (sorry I'll switch to bool if I get time)
        boolean bool = result >= 1;
        while(bool){
            if(block != null) {
                block.execute(robot);
                bool = cond.eval(robot) >= 1;
            }
        }
        System.out.println("WhileNode condition " + cond.toString() + " cond.eval " +  result + block.blockList);
    }
}

