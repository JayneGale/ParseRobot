public interface RobotBoolNode {
        LogicalOp SetlogicalOpType(LogicalOp logicalOpType);
        boolean evalBool(Robot robot);
        String toString();
    }

