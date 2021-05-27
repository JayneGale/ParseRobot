    interface RobotValueNode {
        Optype setOptype(Optype optype);
        int eval(Robot robot);
        String toString();
    }

//    This is for all Expressions to return an integer
//    maybe later double or other number

//EXP   ::= NUM | SEN | OP "(" EXP "," EXP ")"

