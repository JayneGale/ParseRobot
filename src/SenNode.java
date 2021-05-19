public class SenNode implements RobotProgramNode {
    //	calls the relevant Sensory methods in Robot
    public SenType senType;

    //	create an enum to set the different sense types
    public void setSenType(SenType senType) {
        this.senType = senType;
    }
    public SenType getSenType() {
        return this.senType;
    }

    int result;

    public int setNum(int result) {
        this.result = result;
        return result;
    }

    public int getNum() {
        this.result = result;
        return result;
    }
    int input;

    public void setInput(int input) {
        this.input = input;
    }
    public int getInput() {
        this.input = input;
        return input;
    }


    public void execute(Robot robot) {
        switch (senType) {
            case fuelLeft: result = robot.getFuel(); break;
            case oppLR: result = robot.getOpponentLR(); break;
            case oppFB: result = robot.getOpponentFB();break;
            case numBarrels: result = robot.numBarrels();break;
            case barrelLR: result = robot.getBarrelLR(getInput());break;
            case barrelFB: result = robot.getBarrelFB(getInput());break;
            case wallDist: result = robot.getDistanceToWall(); break;
            default: System.out.println("SenType not found "); break;
        }
        System.out.println("SenNode: set " + senType  + " to " + result);
        System.out.println("SenNode: " + toString());
        setNum(result);
    }
    public String toString() {
        switch (senType) {
            case fuelLeft: return "fuelLeft " + result;
            case oppLR: return "oppLR " + result;
            case oppFB:return "oppFB " + result;
            case numBarrels: return "numBarrels " + result;
            case barrelLR: return "barrelLR " + result;
            case barrelFB: return "barrelFB " + result;
            case wallDist: return "wallDist " + result;
            default: return ("senType enum not found ");
        }
    }

}
