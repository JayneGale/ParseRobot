import java.util.ArrayList;

public class LoopNode implements RobotProgramNode{
    public String toString() {
        return "LoopNode: loopTree ";
    }
    public void execute(Robot robot) {
        //		Loop node endlessly calls its Block of Actions/Other Expressions
        // run thru to block list and execute each in turn
        while (true) {
            execute(robot);
        }
    }
}
