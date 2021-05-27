public class MoveNode implements RobotProgramNode {
    //	call the move method in Robot
    public RobotValueNode numMoves = new NumNode(1);
    public RobotValueNode numWaits = new NumNode(1);
    public ActionType moveType;

    //	create an enum with the different moveActions
    public void setMoveType(ActionType moveType) {
        this.moveType = moveType;
    }

    public ActionType getMoveType(ActionType moveType) {
        this.moveType = moveType;
        return moveType;
    }

    public void setNumMoves(RobotValueNode numMoves) {
        this.numMoves = numMoves;
    }
    public RobotValueNode getNumMoves() {
        return numMoves;
    }

    public void setNumWaits(RobotValueNode numWaits) {
        this.numWaits = numWaits;
    }
    public RobotValueNode getNumWaits() {
        return numWaits;
    }

    public void execute(Robot robot) {
        int n = 0;
        System.out.println("moveNode type " + moveType.toString());
        switch (moveType) {
            case move: n = getNumMoves().eval(robot);
                if (n > 0) { for (int i = 0; i < n; i++) {
                    robot.move();}
                } else {System.err.println("numMoves<=0"); }
                break;
            case turnL: robot.turnLeft(); break;
            case turnR: robot.turnRight(); break;
            case turnAround: robot.turnAround();break;
            case takeFuel: robot.takeFuel();break;
            case shieldOn: if(!robot.isShieldOn()) robot.setShield(true); break;
            case shieldOff: if(robot.isShieldOn()) robot.setShield(false); break;
            case wait:
                n = getNumWaits().eval(robot);
                System.out.println("numWaits " + n);
                if (n > 0) { for (int i = 0; i < n; i++) {
                    robot.idleWait(); }
                } else { System.err.println("numWaits<=0"); }
                break;
        }
    }
    public String toString() {
        switch (moveType) {
            case move: { return "move: numMoves " + getNumMoves().toString(); }
            case turnL: {return "turnL "; }
            case turnR: {return "turnR "; }
            case turnAround: { return "turnAround "; }
            case takeFuel: { return "takeFuel "; }
            case shieldOn: { return "shieldOn "; }
            case shieldOff: { return "shieldOff "; }
            case wait: { return "idleWait: numWaits" + getNumWaits().toString(); }
            default: System.out.println("moveType not found "); return ("need to set moveType enum ");}
        }
    }





