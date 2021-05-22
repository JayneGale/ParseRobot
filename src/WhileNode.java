import java.util.ArrayList;

public class WhileNode implements RobotProgramNode {

    CondNode cond;
    RobotProgramNode block;

    public String toString() {
        return "While node ";
    }

    public void execute(Robot robot) {
        while(cond.condBool){
            if(block != null) {
                block.execute(robot);
            }
        }
    }
}

