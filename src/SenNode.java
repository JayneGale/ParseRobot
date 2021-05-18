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

    int inNum;
    public int outNum;

    public int setNum(int inNum) {
        this.inNum = inNum;
        return inNum;
    }
    public int getNum() {
        this.outNum = outNum;
        return outNum;
    }

    public void execute(Robot robot) {
        switch (senType) {
            case fuelLeft: outNum = robot.getFuel(); break;
            case oppLR: outNum = robot.getOpponentLR(); break;
            case oppFB: outNum = robot.getOpponentFB();break;
            case numBarrels: outNum = robot.numBarrels();break;
            case barrelLR: outNum = robot.getBarrelLR(inNum);break;
            case barrelFB: outNum = robot.getBarrelFB(inNum);break;
            case wallDist: outNum = robot.getDistanceToWall(); break;
            default: System.out.println("SenType not found "); break;
        }
    }
    public String toString() {
        switch (senType) {
            case fuelLeft: return "fuelLeft " + outNum;
            case oppLR: return "oppLR " + outNum;
            case oppFB:return "oppFB " + outNum;
            case numBarrels: return "numBarrels " + outNum;
            case barrelLR: return "barrelLR " + outNum;
            case barrelFB: return "barrelFB " + outNum;
            case wallDist: return "wallDist " + outNum;
            default: return ("senType enum not found ");
        }
    }

}
