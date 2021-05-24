public class MoveNode implements RobotProgramNode {
    //	call the move method in Robot
    public int numMoves = 1;
    public int numWaits = 1;
    public ActionType moveType;

    //	create an enum with the different moveActions
    public void setMoveType(ActionType moveType) {
        this.moveType = moveType;
    }
    public ActionType getMoveType(ActionType moveType) {
        this.moveType = moveType;
        return moveType;
    }

    public void setNumMoves(int numMoves) {
        this.numMoves = numMoves;
    }
    public int getNumMoves() {
        return numMoves;
    }

    public void setNumWaits(int numWaits) {
        this.numWaits = numWaits;
    }
    public int getNumWaits() {
        return numWaits;
    }

    public void execute(Robot robot) {
        System.out.println("moveNode type " + moveType.toString());
        switch (moveType) {
            case move: numMoves= getNumMoves();
                if (numMoves > 0) { for (int i = 0; i < numMoves; i++) {
                    robot.move();}
                } else {System.err.println("numMoves<=0"); }
                break;
            case turnL: robot.turnLeft(); break;
            case turnR: robot.turnRight(); break;
            case turnAround: robot.turnAround();break;
            case takeFuel: robot.takeFuel();break;
            case shieldOn: robot.setShield(true);break;
            case shieldOff: robot.setShield(false); break;
            case wait: numWaits = getNumWaits();
                if (numWaits > 0) { for (int i = 0; i < numWaits; i++) {
                    robot.idleWait();}
                } else { System.err.println("numWaits<=0"); }
                break;
        }
    }
    public String toString() {
        switch (moveType) {
            case move: { return "move: numMoves " + getNumMoves(); }
            case turnL: {return "turnL "; }
            case turnR: {return "turnR "; }
            case turnAround: { return "turnAround "; }
            case takeFuel: { return "takeFuel "; }
            case shieldOn: { return "shieldOn "; }
            case shieldOff: { return "shieldOff "; }
            case wait: { return "idleWait: numWaits" + getNumWaits(); }
            default: System.out.println("moveType not found "); return ("need to set moveType enum ");}
        }
    }





