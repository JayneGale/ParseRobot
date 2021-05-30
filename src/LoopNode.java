public class LoopNode implements RobotProgramNode{
    BlockNode block;

    public String toString() {
        return "LoopNode" ;
    }
    public void execute(Robot robot) {
        //		Loop node endlessly calls its Block of Actions/Other Expressions
        // run thru to block list and execute each in turn
        boolean isTrue = true;
        while(isTrue){
            System.out.println("LoopNode started");

            if(block != null) {
                System.out.println("LoopNode  " +  block.toString());
                block.execute(robot);
            }
        }
    }
}


