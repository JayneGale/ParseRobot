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

    int inNum = 0;
    int inSenValue;
    boolean relOpBool = false;


    public void setNum(int inSenValue, int inNum) {
        this.inSenValue = inSenValue;
        this.inNum = inNum;
    }
    public boolean calcBool(RelOpType reloptype, int inSenValue, int inNum) {
        this.relOpType = reloptype;
        this.inSenValue = inSenValue;
        this.inNum = inNum;
        switch (relOpType) {
            case lt: relOpBool = (inSenValue < inNum) ; break;
            case gt: relOpBool = (inSenValue > inNum) ; break;
            case eq: relOpBool = (inSenValue == inNum); break;
            default: System.out.println("relOpType not found "); break;
        }
        return relOpBool;
    }
    public void setBool(boolean relOpResult) {
        this.relOpBool = relOpResult;
    }
    public boolean getBool(Boolean relOpBool) {
        this.relOpBool = relOpBool;
        return relOpBool;
    }

    public void execute(Robot robot) {
        switch (relOpType) {
            case lt: relOpBool = (inSenValue < inNum) ; break;
            case gt: relOpBool = (inSenValue > inNum) ; break;
            case eq: relOpBool = (inSenValue == inNum); break;
            default: System.out.println("relOpType not found "); break;
        }
        setBool(relOpBool);
    }
        public String toString() {
        switch (relOpType) {
            case lt: return inSenValue  + " lt " + inNum + " is " + getBool(relOpBool);
            case gt: return inSenValue  + " gt " + inNum + " is " + getBool(relOpBool);
            case eq:return inSenValue  + " eq " + inNum + " is " + getBool(relOpBool);
            default: return ("relOpType enum not found ");
        }
    }

}
