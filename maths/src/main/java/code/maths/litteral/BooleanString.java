package code.maths.litteral;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Displayable;
import code.util.ints.NumericableString;

final class BooleanString implements NumericableString<Boolean>, Displayable {

    static final char TRUE_CHAR = 'V';

    static final String TRUE = String.valueOf(TRUE_CHAR);

    static final char FALSE_CHAR = 'F';

    static final String FALSE = String.valueOf(FALSE_CHAR);

    static final String LEFT_PAR = NumericString.LEFT_PAR;

    static final String RIGHT_PAR = NumericString.RIGHT_PAR;

    private static final String LOWER = "<";

    private static final String GREATER = ">";

    private static final String EQUALS = "=";

    private static final String LOWER_EQ = "<=";

    private static final String GREATER_EQ = ">=";

    private static final String DIFF = "!=";

    private static final char OR_OP_CHAR = '|';

    private static final char AND_OP_CHAR = '&';

    private static final String NEGATION ="!";

    private static final String EMPTY_STRING = "";

    private static final boolean TRUE_CODE = true;

    private static final boolean FALSE_CODE = false;

    private boolean code = FALSE_CODE;

    private StringBuilder booleanString;

    BooleanString(String _chaineBooleenne){
        booleanString = new StringBuilder(_chaineBooleenne);
    }

    BooleanString(String _chaineBooleenne, StringMap<String> _vars){
        if (_vars.isEmpty()) {
            booleanString = new StringBuilder(_chaineBooleenne);
        } else {
            booleanString = new StringBuilder(StringList.replaceWordsJoin(_chaineBooleenne, _vars));
        }
    }

    @Override
    public void evaluateExp(boolean _checkSyntax){
        StringList elts_ = StringList.splitStringsSep(booleanString.toString(), LOWER, LOWER_EQ, GREATER, GREATER_EQ, EQUALS, DIFF, String.valueOf(AND_OP_CHAR), String.valueOf(OR_OP_CHAR), NEGATION);
        int index_ = CustList.INDEX_NOT_FOUND_ELT;
        for (String e: elts_) {
            index_ ++;
            if (e.isEmpty()) {
                continue;
            }
            if (StringList.quickEq(e,LOWER)) {
                continue;
            }
            if (StringList.quickEq(e,LOWER_EQ)) {
                continue;
            }
            if (StringList.quickEq(e,GREATER)) {
                continue;
            }
            if (StringList.quickEq(e,GREATER_EQ)) {
                continue;
            }
            if (StringList.quickEq(e,DIFF)) {
                continue;
            }
            if (StringList.quickEq(e,EQUALS)) {
                continue;
            }
            if (StringList.quickEq(e,String.valueOf(AND_OP_CHAR))) {
                continue;
            }
            if (StringList.quickEq(e,String.valueOf(OR_OP_CHAR))) {
                continue;
            }
            if (StringList.quickEq(e,NEGATION)) {
                continue;
            }
            boolean pureBoolean_ = true;
            for (char c: e.toCharArray()) {
                if (c == StringList.LEFT_PAR) {
                    continue;
                }
                if (c == StringList.RIGHT_PAR) {
                    continue;
                }
                if (c == TRUE_CHAR) {
                    continue;
                }
                if (c == FALSE_CHAR) {
                    continue;
                }
                pureBoolean_ = false;
                break;
            }
            if (pureBoolean_) {
                continue;
            }
            NumericString num_ = new NumericString(e);
            num_.evaluateExp(_checkSyntax);
            if (num_.isError()) {
                return;
            }
            elts_.set(index_, num_.display());
        }
        booleanString = new StringBuilder(elts_.join(EMPTY_STRING));
        if (booleanString.length() == 0) {
            return;
        }
        index_ = CustList.FIRST_INDEX;
        while (index_ < booleanString.length()) {
            String nbOne_;
            CaughtStringIndex c_;
            c_ = tryGetRateNumber(index_);
            if (!c_.isValid()) {
                index_ = c_.getIndex();
                continue;
            }
            nbOne_ = c_.getToken();
            int next_ = c_.getIndex();
            if (next_ + 1 >= booleanString.length()) {
                break;
            }
            String op_ = EMPTY_STRING;
            if (startsWith(LOWER_EQ, next_)) {
                op_ = LOWER_EQ;
            } else if (startsWith(GREATER_EQ, next_)) {
                op_ = GREATER_EQ;
            } else if (startsWith(LOWER, next_)) {
                op_ = LOWER;
            } else if (startsWith(GREATER, next_)) {
                op_ = GREATER;
            } else if (startsWith(EQUALS, next_)) {
                op_ = EQUALS;
            } else if (startsWith(DIFF, next_)) {
                op_ = DIFF;
            }
            if (op_.isEmpty()) {
                index_ = next_;
                continue;
            }
            String nbTwo_;
            c_ = tryGetRateNumber(next_ + op_.length());
            if (!c_.isValid()) {
                index_ = c_.getIndex();
                continue;
            }
            nbTwo_ = c_.getToken();
            String res_ = comparisons(_checkSyntax, op_, nbOne_, nbTwo_);
            booleanString.replace(index_, c_.getIndex(), res_);
            index_ ++;
        }
        boolean modif_ = true;
        while (modif_) {
            modif_ = false;
            calculateArgs();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            removeParenthesesAroundOps();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            calculateNegation();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            calculateGreatPriority();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
            calculateLowPriority();
            if (code == TRUE_CODE) {
                modif_ = true;
            }
        }
    }

    private boolean startsWith(String _subString, int _index) {
//        int len_ = booleanString.length();
        int lenSub_ = _subString.length();
//        if (_index + lenSub_ > len_) {
//            return false;
//        }
        //i < lenSub_ => _index + i < _index + lenSub_
        //_index + i < _index + lenSub_ && _index + lenSub_ <= len_
        //=> _index + i < len_ => _index + i is always a valid index
        for (int i = 0; i < lenSub_; i++) {
            if (booleanString.charAt(_index + i) != _subString.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    void calculateArgs() {
        int index_ = CustList.FIRST_INDEX;
        boolean foundBeginChar_ = false;
        code=FALSE_CODE;
        while(index_ < booleanString.length()){
            if (foundBeginChar_) {
                String nbOne_;
                String nbTwo_;
                if (!isConstValue(index_)) {
                    foundBeginChar_ = false;
                    continue;
                }
                nbOne_ = String.valueOf(booleanString.charAt(index_));
                int next_ = index_ + 1;
                if (next_ >= booleanString.length()) {
                    return;
                }
                char opChar_ = booleanString.charAt(next_);
                char opStr_;
                if(opChar_ != OR_OP_CHAR){
                    if(opChar_ != AND_OP_CHAR){
                        index_ = next_ + 1;
                        foundBeginChar_ = false;
                        continue;
//                        if(opChar_ != EQUALS_CHAR){
//                            if (booleanString.indexOf(DIFF, next_) != next_) {
//                                index_ = next_ + 1;
//                                foundBeginChar_ = false;
//                                continue;
//                            } else {
//                                opStr_ = DIFF;
//                            }
//                        } else {
//                            opStr_ = EQUALS;
//                        }
                    }
                    opStr_ = AND_OP_CHAR;
                } else {
                    opStr_ = OR_OP_CHAR;
                }
                next_ ++;
                if (!isConstValue(next_)) {
                    foundBeginChar_ = false;
                    continue;
                }
                nbTwo_ = String.valueOf(booleanString.charAt(next_));
                next_ ++;
                if (next_ >= booleanString.length()) {
                    String res_ = operators(opStr_, nbOne_, nbTwo_);
                    booleanString.replace(index_, booleanString.length(), res_);
                    code=TRUE_CODE;
                    return;
                }
                if (booleanString.charAt(next_) != StringList.RIGHT_PAR) {
                    index_ = next_;
                    foundBeginChar_ = false;
                    continue;
                }
                String res_ = operators(opStr_, nbOne_, nbTwo_);
                booleanString.replace(index_, next_, res_);
                foundBeginChar_ = false;
                index_ += res_.length();
                code=TRUE_CODE;
                continue;
            }
            if (booleanString.charAt(index_) == StringList.LEFT_PAR) {
                foundBeginChar_ = true;
            }
            index_++;
        }
    }

    void removeParenthesesAroundOps() {
        code=FALSE_CODE;
        int index_ = CustList.FIRST_INDEX;
        int len_ = booleanString.length();
//        if (index_ >= len_) {
//            return;
//        }
        if (booleanString.charAt(index_) == StringList.LEFT_PAR) {
            if (isConstValue(index_ + 1)) {
                int next_ = index_ + 2;
                if (next_ >= len_) {
                    return;
                }
                if (booleanString.charAt(next_) == StringList.RIGHT_PAR) {
                    code=TRUE_CODE;
                    booleanString.deleteCharAt(next_);
                    booleanString.deleteCharAt(CustList.FIRST_INDEX);
                }
            }
        }
        boolean foundBeginChar_ = false;
        while(index_ < booleanString.length()){
            if (foundBeginChar_) {
                if (!isConstValue(index_)) {
                    foundBeginChar_ = false;
                    continue;
                }
                int next_ = index_ + 1;
                if (next_ >= len_) {
                    return;
                }
                if (booleanString.charAt(next_) == StringList.RIGHT_PAR) {
                    code=TRUE_CODE;
                    booleanString.deleteCharAt(next_);
                    booleanString.deleteCharAt(index_ - 1);
                    index_ ++;
                    foundBeginChar_ = false;
                    continue;
                }
                index_ = next_;
                foundBeginChar_ = false;
                continue;
            }
            if (booleanString.charAt(index_) == StringList.LEFT_PAR){
                foundBeginChar_ = true;
            }
            index_ ++;
        }
    }

    void calculateNegation() {
        code=FALSE_CODE;
        int index_ = CustList.FIRST_INDEX;
//        int len_ = booleanString.length();
//        if (index_ >= len_) {
//            return;
//        }
        boolean foundBeginChar_ = false;
        while(index_ < booleanString.length()){
            if (foundBeginChar_) {
                if (!isConstValue(index_)) {
                    foundBeginChar_ = false;
                    continue;
                }
                code=TRUE_CODE;
                if (booleanString.charAt(index_) == TRUE_CHAR) {
                    booleanString.replace(index_ - 1, index_ + 1, FALSE);
                } else {
                    booleanString.replace(index_ - 1, index_ + 1, TRUE);
                }
                foundBeginChar_ = false;
                continue;
            }
            if (booleanString.charAt(index_) == NEGATION.charAt(CustList.FIRST_INDEX)){
                foundBeginChar_ = true;
            }
            index_ ++;
        }
    }

    void calculateLowPriority() {
        int index_ = CustList.FIRST_INDEX;
        boolean foundBeginChar_ = false;
        code=FALSE_CODE;
        while (true) {
            if (!isConstValue(index_)) {
                break;
            }
            String nbOne_ = String.valueOf(booleanString.charAt(index_));
            int next_ = index_ + 1;
            if (next_ >= booleanString.length()) {
                return;
            }
            char opChar_ = booleanString.charAt(next_);
            if (opChar_ != OR_OP_CHAR) {
                break;
            }
            if (next_ + 1 >= booleanString.length()) {
                return;
            }
            if (!isConstValue(next_ + 1)) {
                break;
            }
            String nbTwo_ = String.valueOf(booleanString.charAt(next_ + 1));
            next_ ++;
            next_ ++;
            if (next_ >= booleanString.length()) {
                String res_ = operators(OR_OP_CHAR, nbOne_, nbTwo_);
                booleanString.replace(CustList.FIRST_INDEX, booleanString.length(), res_);
                code = TRUE_CODE;
                return;
            }
            if (booleanString.charAt(next_) != OR_OP_CHAR) {
                break;
            }
            String res_ = operators(OR_OP_CHAR, nbOne_, nbTwo_);
            booleanString.replace(CustList.FIRST_INDEX, next_, res_);
            code = TRUE_CODE;
        }
        while(index_ < booleanString.length()){
            if (foundBeginChar_) {
                if (!isConstValue(index_)) {
                    foundBeginChar_ = false;
                    continue;
                }
                String nbOne_ = String.valueOf(booleanString.charAt(index_));
                int next_ = index_ + 1;
                if (next_ >= booleanString.length()) {
                    return;
                }
                char opChar_ = booleanString.charAt(next_);
                if (opChar_ != OR_OP_CHAR) {
                    foundBeginChar_ = false;
                    continue;
                }
                if (next_ + 1 >= booleanString.length()) {
                    return;
                }
                if (!isConstValue(next_ + 1)) {
                    foundBeginChar_ = false;
                    continue;
                }
                String nbTwo_ = String.valueOf(booleanString.charAt(next_ + 1));
                next_ ++;
                next_ ++;
                if (next_ >= booleanString.length()) {
                    String res_ = operators(OR_OP_CHAR, nbOne_, nbTwo_);
                    booleanString.replace(index_, booleanString.length(), res_);
                    code = TRUE_CODE;
                    return;
                }
                if (booleanString.charAt(next_) != OR_OP_CHAR) {
                    foundBeginChar_ = false;
                    continue;
                }
                String res_ = operators(OR_OP_CHAR, nbOne_, nbTwo_);
                booleanString.replace(index_, next_, res_);
                code = TRUE_CODE;
                continue;
            }
            if (booleanString.charAt(index_) == StringList.LEFT_PAR) {
                foundBeginChar_ = true;
            }
            index_++;
        }
    }

    void calculateGreatPriority() {
        int index_ = CustList.FIRST_INDEX;
        boolean foundBeginChar_ = false;
        code=FALSE_CODE;
        while (true) {
            if (!isConstValue(index_)) {
                break;
            }
            String nbOne_ = String.valueOf(booleanString.charAt(index_));
            int next_ = index_ + 1;
            if (next_ >= booleanString.length()) {
                return;
            }
            char opChar_ = booleanString.charAt(next_);
            if (opChar_ != AND_OP_CHAR) {
                break;
            }
            if (next_ + 1 >= booleanString.length()) {
                return;
            }
            if (!isConstValue(next_ + 1)) {
                break;
            }
            String nbTwo_ = String.valueOf(booleanString.charAt(next_ + 1));
            next_ ++;
            next_ ++;
            if (next_ >= booleanString.length()) {
                String res_ = operators(AND_OP_CHAR, nbOne_, nbTwo_);
                booleanString.replace(CustList.FIRST_INDEX, booleanString.length(), res_);
                code = TRUE_CODE;
                return;
            }
            String res_ = operators(AND_OP_CHAR, nbOne_, nbTwo_);
            booleanString.replace(CustList.FIRST_INDEX, next_, res_);
            code = TRUE_CODE;
        }
        while(index_ < booleanString.length()){
            if (foundBeginChar_) {
                if (!isConstValue(index_)) {
                    foundBeginChar_ = false;
                    continue;
                }
                String nbOne_ = String.valueOf(booleanString.charAt(index_));
                int next_ = index_ + 1;
                if (next_ >= booleanString.length()) {
                    return;
                }
                char opChar_ = booleanString.charAt(next_);
                if (opChar_ != AND_OP_CHAR) {
                    foundBeginChar_ = false;
                    continue;
                }
                if (next_ + 1 >= booleanString.length()) {
                    return;
                }
                if (!isConstValue(next_ + 1)) {
                    foundBeginChar_ = false;
                    continue;
                }
                String nbTwo_ = String.valueOf(booleanString.charAt(next_ + 1));
                next_ ++;
                next_ ++;
                if (next_ >= booleanString.length()) {
                    String res_ = operators(AND_OP_CHAR, nbOne_, nbTwo_);
                    booleanString.replace(index_, booleanString.length(), res_);
                    code = TRUE_CODE;
                    return;
                }
                String res_ = operators(AND_OP_CHAR, nbOne_, nbTwo_);
                booleanString.replace(index_, next_, res_);
                code = TRUE_CODE;
                continue;
            }
            if (booleanString.charAt(index_) == StringList.LEFT_PAR) {
                foundBeginChar_ = true;
            } else if (booleanString.charAt(index_) == OR_OP_CHAR) {
                foundBeginChar_ = true;
            }
            index_++;
        }
    }

    private static String comparisons(boolean _checkSyntax, String _op, String _nbOne, String _nbTwo){
        String res_ = EMPTY_STRING;
        if(StringList.quickEq(_op,LOWER)){
            Rate rateOne_ = NumericString.deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_ = NumericString.deleteZeroDivider(_checkSyntax, _nbTwo);
            if(Rate.strLower(rateOne_, rateTwo_)){
                res_=TRUE;
            }else{
                res_=FALSE;
            }
        }
        if(StringList.quickEq(_op,GREATER)){
            Rate rateOne_ = NumericString.deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_ = NumericString.deleteZeroDivider(_checkSyntax, _nbTwo);
            if(Rate.strGreater(rateOne_, rateTwo_)){
                res_=TRUE;
            }else{
                res_=FALSE;
            }
        }
        if(StringList.quickEq(_op,LOWER_EQ)){
            Rate rateOne_ = NumericString.deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_ = NumericString.deleteZeroDivider(_checkSyntax, _nbTwo);
            if(Rate.lowerEq(rateOne_, rateTwo_)){
                res_=TRUE;
            }else{
                res_=FALSE;
            }
        }
        if(StringList.quickEq(_op,GREATER_EQ)){
            Rate rateOne_ = NumericString.deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_ = NumericString.deleteZeroDivider(_checkSyntax, _nbTwo);
            if(Rate.greaterEq(rateOne_, rateTwo_)){
                res_=TRUE;
            }else{
                res_=FALSE;
            }
        }
        if(StringList.quickEq(_op,EQUALS)){
            Rate rateOne_ = NumericString.deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_ = NumericString.deleteZeroDivider(_checkSyntax, _nbTwo);
            if(Rate.eq(rateOne_, rateTwo_)){
                res_=TRUE;
            }else{
                res_=FALSE;
            }
        }
        if(StringList.quickEq(_op,DIFF)){
            Rate rateOne_ = NumericString.deleteZeroDivider(_checkSyntax, _nbOne);
            Rate rateTwo_ = NumericString.deleteZeroDivider(_checkSyntax, _nbTwo);
            if(!Rate.eq(rateOne_, rateTwo_)){
                res_=TRUE;
            }else{
                res_=FALSE;
            }
        }
        return res_;
    }

    private static String operators(char _op, String _nbOne, String _nbTwo) {
        String res_ = EMPTY_STRING;
        if(_op == AND_OP_CHAR){
            if(StringList.quickEq(_nbOne,TRUE)&&StringList.quickEq(_nbTwo,TRUE)){
                res_=TRUE;
            }else{
                res_=FALSE;
            }
        }
        if(_op == OR_OP_CHAR){
            if(StringList.quickEq(_nbOne,TRUE)||StringList.quickEq(_nbTwo,TRUE)){
                res_=TRUE;
            }else{
                res_=FALSE;
            }
        }
        return res_;
    }

    private boolean isConstValue(int _index) {
        if (_index < CustList.FIRST_INDEX || _index >= booleanString.length()) {
            return false;
        }
        if (booleanString.charAt(_index) == TRUE_CHAR) {
            return true;
        }
        if (booleanString.charAt(_index) == FALSE_CHAR) {
            return true;
        }
        return false;
    }

    private CaughtStringIndex tryGetRateNumber(int _index) {
        StringBuilder str_ = new StringBuilder();
        int len_ = booleanString.length();
        int index_ = _index;
        if (index_ >= len_) {
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        if (booleanString.charAt(index_) == Rate.MINUS_CHAR) {
            str_.append(booleanString.charAt(index_));
            index_ ++;
        }
        if (index_ >= len_) {
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        if (!Character.isDigit(booleanString.charAt(index_))) {
            str_.append(booleanString.charAt(index_));
            index_ ++;
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        //_input.charAt(index_) is digit
        while (true) {
            if (index_ >= len_) {
                return new CaughtStringIndex(str_.toString(), index_, true);
            }
            if (!Character.isDigit(booleanString.charAt(index_))) {
                if (booleanString.charAt(index_) != Rate.SEP_NUM_DEN_CHAR) {
                    return new CaughtStringIndex(str_.toString(), index_, true);
                }
                break;
            }
            str_.append(booleanString.charAt(index_));
            index_++;
        }
        //_input.charAt(index_) is rate separator
        if (index_ + 1 >= len_) {
            return new CaughtStringIndex(str_.toString(), index_ + 1, false);
        }
        str_.append(booleanString.charAt(index_));
        index_ ++;
        if (!Character.isDigit(booleanString.charAt(index_))) {
            str_.append(booleanString.charAt(index_));
            index_ ++;
            return new CaughtStringIndex(str_.toString(), index_, false);
        }
        while (true) {
            if (index_ >= len_) {
                return new CaughtStringIndex(str_.toString(), index_, true);
            }
            if (!Character.isDigit(booleanString.charAt(index_))) {
                return new CaughtStringIndex(str_.toString(), index_, true);
            }
            str_.append(booleanString.charAt(index_));
            index_++;
        }
    }

    @Override
    public Boolean getResult() {
        if (StringList.quickEq(booleanString.toString(),TRUE)) {
            return true;
        }
        if (StringList.quickEq(booleanString.toString(),FALSE)) {
            return false;
        }
        return null;
    }

    static boolean evaluate(String _booleanString, StringMap<String> _variables, boolean _default) {
        return evaluate(_booleanString, _variables, false, _default);
    }

    static boolean evaluate(String _booleanString, StringMap<String> _variables, boolean _checkSyntax, boolean _default) {
        try {
            BooleanString num_ = new BooleanString(_booleanString, _variables);
            num_.evaluateExp(_checkSyntax);
            return num_.getResult();
        } catch (RuntimeException _0) {
            return _default;
        }
    }

    @Override
    public boolean isValid() {
        if (StringList.quickEq(booleanString.toString(),TRUE)) {
            return true;
        }
        if (StringList.quickEq(booleanString.toString(),FALSE)) {
            return true;
        }
        return false;
    }

    @Override
    public String display() {
        return booleanString.toString();
    }

    @Override
    public String beforeEvaluated() {
        return booleanString.toString();
    }
}
