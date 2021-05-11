/**
 * Interface for all nodes that can be executed,
 * including the top level program node
 */

interface RobotProgramNode {
	Robot execute(Robot robot);
	String toString();
}

class MoveNode implements RobotProgramNode {
//	call the move method in Robot
	public String toString(Robot robot){
		return  "move " + robot.toString();
	}
	public Robot execute(Robot robot) {
		robot.move();
		return null;
	}
}

class TurnLNode implements RobotProgramNode {
	//	call the turn method in Robot
	public String toString(Robot robot) {
		return "turnL " + robot.toString();
	}

	public Robot execute(Robot robot) {
		robot.turnLeft();
		return robot;
	}
}

class TurnRNode implements RobotProgramNode {
	//	call the turn method in Robot
	public String toString(Robot robot) {
		return "turnR " + robot.toString();
	}

	public Robot execute(Robot robot) {
		robot.turnRight();
		return robot;
	}
}

class LoopNode implements RobotProgramNode{
	public RobotProgramNode BlockNode;
	String toString(Robot robot) {
		return "loop node called";
	}
	public Robot execute(Robot robot){
		execute(BlockNode.execute(robot));
		return null;
	}
}

class BlockNode implements RobotProgramNode{
	String toString(Robot robot) {
		return "block node called";
	}
	public Robot execute(Robot robot){
//		call the BlockNode elements to execute
//		TODO up to here implementing the loop function
		return null;
	}

}
//	The execute method for LoopNode will not call methods on the robot directly, but will repeatedly call the execute method of the BlockNode that it contains. Similarly, the BlockNode will need to call the execute method of each of its components in turn.
//		The node classes should also have a toString method which returns a textual representation of the node. The nodes corresponding to the PROG, STMT, LOOP and BLOCK rules will need to construct the string out of their components. For example, the LoopNode class might have the following method (assuming that block is a field containing the BlockNode that is contained in the LoopNode):
//		1
//public String toString() {
//		2
//		return "loop" + this.block;
//		3
//		}
//		You will also need to create a parse... method for each of the rules, which takes the scanner, and returns a RobotProgramNod
