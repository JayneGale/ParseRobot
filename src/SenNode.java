public class SenNode implements RobotValueNode {
    //	calls the relevant Sensory methods in Robot
    //	an enum for the different sense types

    public SenNode(SenType senType){
        this.senType = senType;
    }
    SenType senType ;
    int result;

    public int eval(Robot robot) {
        execute(robot);
        return result;
    }
//	Todo add EXP option to barrelLR and barrelFB
//    this is for getbarrelFB and LR which gets the distance to the nth barrel away
//    just for this phase let's set it to 2
    int n = 2;
    public void setTargetBarrel(int input) {
        n = input;
    }
    public int getTargetBarrel() {
        return n;
    }

    public void execute(Robot robot) {
        switch (senType) {
            case fuelLeft -> result = robot.getFuel();
            case oppLR -> result = robot.getOpponentLR();
            case oppFB -> result = robot.getOpponentFB();
            case numBarrels -> result = robot.numBarrels();
            case barrelLR -> result = robot.getBarrelLR(getTargetBarrel());
            case barrelFB -> result = robot.getBarrelFB(getTargetBarrel());
            case wallDist -> result = robot.getDistanceToWall();
            default -> System.out.println("SenType not found ");
        }
//        result = eval(robot);
        System.out.println("SenNode: " + senType  + " = " + result);
    }

    public Optype setOptype(Optype optype) {
        return null;
    }

    public String toString() {
        return switch (senType) {
            case fuelLeft -> "fuelLeft " + result;
            case oppLR -> "oppLR " + result;
            case oppFB -> "oppFB " + result;
            case numBarrels -> "numBarrels " + result;
            case barrelLR -> "barrelLR " + result;
            case barrelFB -> "barrelFB " + result;
            case wallDist -> "wallDist " + result;
        };
    }
}
