public class RelOpNode implements RobotProgramNode {        //	calls the relevant Sensory methods in Robot
    public RelOpType relOpType;

    //	create an enum with the different relative operations types
    public void setRelOpType(RelOpType relOpType) {
        this.relOpType = relOpType;
    }

    int inNum = 0;
    int inSenValue;
    boolean relOpResult = false;


    public void setNum(int inSenValue, int inNum) {
        this.inSenValue = inSenValue;
        this.inNum = inNum;
    }
    public boolean getNum() {
        this.relOpResult = relOpResult;
        return relOpResult;
    }

    public void execute(Robot robot) {
        switch (relOpType) {
            case lt: relOpResult = (inSenValue < inNum) ; break;
            case gt: relOpResult = (inSenValue > inNum) ; break;
            case eq: relOpResult = (inSenValue == inNum); break;
            default: System.out.println("relOpType not found "); break;
        }
    }
        public String toString() {
        switch (relOpType) {
            case lt: return inSenValue  + " lt " + inNum + " is " + relOpResult;
            case gt: return inSenValue  + " gt " + inNum + " is " + relOpResult;
            case eq:return inSenValue  + " eq " + inNum + " is " + relOpResult;
            default: return ("relOpType enum not found ");
        }
    }

}
