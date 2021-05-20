public class RelOpNode implements RobotProgramNode {        //	calls the relevant Sensory methods in Robot
    public RelOpType relOpType;

    //	create an enum with the different relative operations types
    public void setRelOpType(RelOpType relOpType) {
        this.relOpType = relOpType;
    }
    public RelOpType getRelOpType() {
        this.relOpType = relOpType;
        return relOpType;
    }

    int exp1 = 0;
    int exp2;
    boolean relOpBool = false;

//    public void setNum(int exp1, int exp2) {
//        this.exp2 = exp1;
//        this.exp1 = exp2;
//    }
//    public void setBool(boolean relOpResult) {
//        this.relOpBool = relOpResult;
//    }
//    public boolean getBool(Boolean relOpBool) {
//        this.relOpBool = relOpBool;
//        return relOpBool;
//    }

    public void execute(Robot robot) {
        execute(robot);
//        switch (relOpType) {
//            case lt: relOpBool = (exp2 < exp1) ; break;
//            case gt: relOpBool = (exp2 > exp1) ; break;
//            case eq: relOpBool = (exp2 == exp1); break;
//            default: System.out.println("relOpType not found "); break;
//        }
//        setBool(relOpBool);
    }
        public String toString() {
        switch (relOpType) {
            case lt: return " lt " ;
            case gt: return  " gt " ;
            case eq: return  " eq " ;
            default: return ("relOpType enum not found ");
        }
//            switch (relOpType) {
//                case lt: return exp2 + " lt " + exp1 + " is " + getBool(relOpBool);
//                case gt: return exp2 + " gt " + exp1 + " is " + getBool(relOpBool);
//                case eq:return exp2 + " eq " + exp1 + " is " + getBool(relOpBool);
//                default: return ("relOpType enum not found ");
//            }

        }

}
