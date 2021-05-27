public class CompoundCondNode implements RobotBoolNode {
    RobotBoolNode leftCond;
    RobotBoolNode rightCond;
    LogicalOp logicalOp;
//    Todo up to here
    public CompoundCondNode( LogicalOp logicalOp, RobotBoolNode leftCond, RobotBoolNode rightCond){
        this.logicalOp = logicalOp;
        this.leftCond = leftCond;
        this.rightCond = rightCond;
    }

    public LogicalOp SetlogicalOpType(LogicalOp logicalop) {
        this.logicalOp = logicalop;
        return logicalop; }

    public boolean evalBool(Robot robot) {
        return evalSubConds(logicalOp, leftCond, rightCond, robot); }

    public RobotBoolNode setLeftCond(RobotBoolNode leftCond) {
        this.leftCond = leftCond;
        return leftCond; }

    public RobotBoolNode setRightCond(RobotBoolNode rightCond) {
        this.rightCond = rightCond;
        return rightCond; }

    public boolean evalSubConds(LogicalOp logicalOp, RobotBoolNode leftCond, RobotBoolNode rightCond, Robot robot) {
        boolean result = false;
        boolean lt = leftCond.evalBool(robot);
        switch (logicalOp) {
            case and:
                result = lt && rightCond.evalBool(robot);
                System.out.println("add " + lt + rightCond.evalBool(robot));
            case or:
                result = lt || rightCond.evalBool(robot);
                System.out.println("or " + lt + rightCond.evalBool(robot));
            case not:
                result = !lt;
                System.out.println("not" + lt);
            case relop:
                result = lt;
        }
        return result;
    }
}
