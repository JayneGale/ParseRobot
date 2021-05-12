/**
 * Interface for all nodes that can be executed,
 * including the top level program node
 */

interface RobotProgramNode {
	Robot execute(Robot robot);
	String toString();
}

