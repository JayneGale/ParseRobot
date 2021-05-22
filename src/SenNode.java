public class SenNode implements RobotProgramNode {
    //	calls the relevant Sensory methods in Robot

    //	an enum for the different sense types
    public SenType senType;
    public int result;

    public void setNum(int result) {
        this.result = result;
    }

    public int getNum() {
        this.result = result;
        return result;
    }
//    this is for getbarrelFB and LR which gets the distance to the nth barrel away
//    just for this phase let's set it to 1
    int n = 1;
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
        setNum(result);
        System.out.println("SenNode: " + senType  + " = " + result);
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
            default -> ("senType enum not found ");
        };
    }

}
//    public void setSenType(SenType senType) {
//        this.senType = senType;
//    }
//    public SenType getSenType() {
//        return this.senType;
//    }
