public class RelOpNode implements RobotProgramNode {        //	calls the relevant Sensory methods in Robot
    public RelOpType relOpType;

    //	create an enum with the different relative operations types
    public void setRelOpType(RelOpType relOpType) {
        this.relOpType = relOpType;
    }

    int inNum;
    int inSenValue;
    boolean out;
    public int outNum;

    public void setNum(int inSenValue, int inNum) {
        this.inSenValue = inSenValue;
        this.inNum = inNum;
    }
    public boolean getNum() {
        this.out = out;
        return out;
    }

    public void execute(Robot robot) {
        switch (relOpType) {
            case lt: out = (inSenValue < inNum) ; break;
            case gt: out = (inSenValue > inNum) ; break;
            case eq: out = (inSenValue == inNum); break;
            default: System.out.println("relOpType not found "); break;
        }
    }
        public String toString() {
        switch (relOpType) {
            case lt: return inSenValue  + " lt " + inNum + " is " + out;
            case gt: return inSenValue  + " gt " + inNum + " is " + out;
            case eq:return inSenValue  + " eq " + inNum + " is " + out;
            default: return ("relOpType enum not found ");
        }
    }

}
