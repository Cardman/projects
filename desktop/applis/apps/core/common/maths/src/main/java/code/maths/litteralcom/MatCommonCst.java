package code.maths.litteralcom;

public final class MatCommonCst {
    public static final int BAD_PRIO = -1;
    public static final int OR_PRIO = 1;
    public static final int AND_PRIO = 2;
    public static final int EQ_PRIO = 3;
    public static final int CMP_PRIO = 4;
    public static final int ADD_PRIO = 5;
    public static final int MULT_PRIO = 6;
    public static final int UNARY_PRIO = 7;
    public static final int FCT_OPER_PRIO = 8;
    public static final char DOT = '.';
    public static final char PAR_LEFT = '(';
    public static final char PAR_RIGHT = ')';
    public static final char SEP_ARG = ',';
    public static final char NEG_BOOL_CHAR = '!';
    public static final char MULT_CHAR= '*';
    public static final char DIV_CHAR= ':';
    public static final char PLUS_CHAR= '+';
    public static final char MINUS_CHAR = '-';
    public static final char LOWER_CHAR = '<';
    public static final char GREATER_CHAR = '>';
    public static final char EQ_CHAR = '=';
    public static final char AND_CHAR = '&';
    public static final char OR_CHAR = '|';
    private MatCommonCst(){}
    public static int getPrio(char _curChar) {
        int prioOpMult_ = 0;
        if (_curChar == MINUS_CHAR || _curChar == PLUS_CHAR) {
            prioOpMult_ = ADD_PRIO;
        } else if (_curChar == MULT_CHAR || _curChar == DIV_CHAR) {
            prioOpMult_ = MULT_PRIO;
        } else if (_curChar == AND_CHAR) {
            prioOpMult_ = AND_PRIO;
        } else {
            if (_curChar == OR_CHAR) {
                prioOpMult_ = OR_PRIO;
            }
        }
        return prioOpMult_;
    }
}
