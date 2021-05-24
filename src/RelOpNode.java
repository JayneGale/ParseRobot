public class RelOpNode implements RobotValueNode {        //	defines the relevant relative operations methods in a Condition

    public RelOpType relOpType;
    public int exp1;
    public int exp2;

    public Optype setOptype(Optype optype) {
        return null;
    }
    public String toString() {
        return switch (relOpType) {
            case lt -> " lt ";
            case gt -> " gt ";
            case eq -> " eq ";
            default -> ("relOpType enum not found ");
        };
    }
        public int eval(Robot robot) {
        return 16;
//        switch (relOpType) {
//            case lt: relOpBool = (exp2 < exp1) ; break;
//            case gt: relOpBool = (exp2 > exp1) ; break;
//            case eq: relOpBool = (exp2 == exp1); break;
//            default: System.out.println("relOpType not found "); break;
        }
}

//    //	create an enum with the different relative operations types
//    public void setRelOpType(RelOpType relOpType) {
//        this.relOpType = relOpType;
//    }
//    public RelOpType getRelOpType() {
//        this.relOpType = relOpType;
//        return relOpType;
//    }

//    public void execute(Robot robot) {
//        execute(robot);
//        switch (relOpType) {
//            case lt: relOpBool = (exp2 < exp1) ; break;
//            case gt: relOpBool = (exp2 > exp1) ; break;
//            case eq: relOpBool = (exp2 == exp1); break;
//            default: System.out.println("relOpType not found "); break;
//        }
//        setBool(relOpBool);
//    }
//            switch (relOpType) {
//                case lt: return exp2 + " lt " + exp1 + " is " + getBool(relOpBool);
//                case gt: return exp2 + " gt " + exp1 + " is " + getBool(relOpBool);
//                case eq:return exp2 + " eq " + exp1 + " is " + getBool(relOpBool);
//                default: return ("relOpType enum not found ");
//            }


