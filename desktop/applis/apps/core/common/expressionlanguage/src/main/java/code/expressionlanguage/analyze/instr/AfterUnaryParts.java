package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.AnonymousResult;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.options.KeyWords;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.Ints;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

final class AfterUnaryParts {
    private static final String EMPTY_STRING = "";

    private final StrTypes operators = new StrTypes();
    private int parsBrackets;
    private int prio = ElResolver.FCT_OPER_PRIO;
    private String extracted = "";
    private AnaResultPartType partsOffs = new AnaResultPartType();
    private int index;

    private boolean enPars = true;
    private boolean instOf;
    private String fctName = EMPTY_STRING;
    private boolean enabledId;
    private boolean instance;
    private boolean instanceStrict;
    private boolean switchStrict;
    private boolean errorDot;
    private final Ints laterIndexesDouble = new Ints();
    private final ExpPartDelimiters del;
    private AbsBk block;
    private int length;
    private int deltaAnnot;
    private String retSwitch = "";
    private CustList<AnonymousResult> anonymousResults = new CustList<AnonymousResult>();

    AfterUnaryParts(int _offset, String _string, ExpPartDelimiters _del, Delimiters _d) {
        del = _del;
        int firstPrintChar_ = del.getFirstPrintIndex();
        int lastPrintChar_ = del.getLastPrintIndex();
        boolean preIncr_ = (_string.charAt(firstPrintChar_) == ElResolver.MINUS_CHAR || _string.charAt(firstPrintChar_) == ElResolver.PLUS_CHAR) && firstPrintChar_ + 1 < _string.length() && _string.charAt(firstPrintChar_) == _string.charAt(firstPrintChar_ + 1);
        if (preIncr_) {
            prio = ElResolver.UNARY_PRIO;
            String ch_ = Character.toString(_string.charAt(firstPrintChar_));
            operators.addEntry(firstPrintChar_, StringUtil.concat(EMPTY_STRING,ch_,ch_));
            index = incrementUnary(_string, firstPrintChar_ + 2, lastPrintChar_, _offset, _d);
            return;
        }
        if (_string.charAt(firstPrintChar_) == ElResolver.MINUS_CHAR || _string.charAt(firstPrintChar_) == ElResolver.PLUS_CHAR
                || _string.charAt(firstPrintChar_) == ElResolver.NEG_BOOL_CHAR || _string.charAt(firstPrintChar_) == ElResolver.NEG_BOOL || _string.charAt(firstPrintChar_) == ElResolver.MULT_CHAR) {
            prio = ElResolver.UNARY_PRIO;
            operators.addEntry(firstPrintChar_, Character.toString(_string.charAt(firstPrintChar_)));
            index = incrementUnary(_string, firstPrintChar_ + 1, lastPrintChar_, _offset, _d);
            return;
        }
        if (_d.getDelCast().contains((long)firstPrintChar_ + _offset)) {
            prio = ElResolver.UNARY_PRIO;
            int min_ = _d.getDelCast().indexOfNb((long)firstPrintChar_ + _offset);
            int max_ = _d.getDelCast().get(min_ + 1) - _offset;
            operators.addEntry(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            int ext_ = min_ / 2;
            extracted = _d.getDelCastExtract().get(ext_);
            partsOffs = _d.getCastParts().get(ext_);
            index = incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
            return;
        }
        if (_d.getDelExplicit().contains((long)firstPrintChar_ + _offset)) {
            prio = ElResolver.UNARY_PRIO;
            int min_ = _d.getDelExplicit().indexOfNb((long)firstPrintChar_ + _offset);
            int max_ = _d.getDelExplicit().get(min_ + 1) - _offset;
            operators.addEntry(firstPrintChar_, _string.substring(firstPrintChar_, max_ + 1));
            index = incrementUnary(_string, firstPrintChar_, lastPrintChar_, _offset, _d);
            return;
        }
        index = firstPrintChar_;
    }
    void setInstance(int _offset, String _string, Delimiters _d, AnalyzedPageEl _page) {
        anonymousResults = _page.getCurrentAnonymousResults();
        int firstPrintChar_ = del.getFirstPrintIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordNew_ = keyWords_.getKeyWordNew();
        String keyWordSwitch_ = keyWords_.getKeyWordSwitch();
        if (StringExpUtil.startsWithKeyWord(_string,firstPrintChar_, keyWordNew_)) {
            int index_ = DefaultProcessKeyWord.skipWhiteSpace(_string,firstPrintChar_ + keyWordNew_.length());
            int next_ = index_;
            if (StringExpUtil.nextCharIs(_string,index_,_string.length(),ElResolver.ANN_ARR_LEFT)) {
                next_ = DefaultProcessKeyWord.skipWhiteSpace(_string,index_+1);
            }
            if (StringExpUtil.nextCharIs(_string,next_,_string.length(),ElResolver.ANN_ARR_RIGHT)) {
                next_ = DefaultProcessKeyWord.skipWhiteSpace(_string,next_+1);
            }
            int ind_ = _d.getDelAnnotNew().indexOfNb((long) _offset + next_);
            if (ind_ >= 0) {
                deltaAnnot = _d.getDelAnnotNew().get(ind_ + 1) - _d.getDelAnnotNew().get(ind_);
            }
            instance = true;
            instanceStrict = true;
        }
        if (StringExpUtil.startsWithKeyWord(_string,firstPrintChar_, keyWordSwitch_)) {
            switchStrict = true;
        }
        if (_string.charAt(firstPrintChar_) == ElResolver.ANN_ARR_LEFT) {
            instance = true;
        }
    }
    private int nextForAnon(int _offset, int _sum) {
        block = null;
        if (instanceStrict) {
            for (AnonymousResult a: anonymousResults) {
                if (a.getIndex() == _sum) {
                    block = a.getType();
                    length = a.getLength();
                    return a.getUntil() - _offset + 1;
                }
            }
        }
        if (switchStrict) {
            for (AnonymousResult a: anonymousResults) {
                if (a.getIndex() == _sum) {
                    block = a.getType();
                    length = a.getLength();
                    retSwitch = a.getRetSwitch();
                    return a.getUntil() - _offset + 1;
                }
            }
        }
        return index;
    }
    void setState(int _offset, String _string, Delimiters _d) {
        char curChar_ = _string.charAt(index);
        int old_ = index;
        int currentSum_ = index + _offset;
        int nex_ = nextForAnon(_offset, currentSum_);
        if (nex_ > old_) {
            index = nex_;
            return;
        }
        if (_d.getDimsAddonIndexes().containsObj(currentSum_)) {
            laterIndexesDouble.add(index);
            index++;
            return;
        }
        if (_d.getNamedArgs().containsObj(currentSum_)) {
            namedArg(curChar_);
            return;
        }
        if (prio == ElResolver.NAME_PRIO) {
            enPars = false;
            enabledId = false;
            index++;
            return;
        }
        OperatorOffsetLength cont_ = contained(currentSum_, _d);
        if (cont_ == null) {
            index++;
            return;
        }
        if (curChar_ == ElResolver.PAR_LEFT) {
            parLeft(_string);
            return;
        }
        if (curChar_ == ElResolver.SEP_ARG && parsBrackets > 0) {
            commaSepFct();
            return;
        }
        if (curChar_ == ElResolver.PAR_RIGHT) {
            parRight();
            return;
        }
        if (curChar_ == ElResolver.ANN_ARR_LEFT) {
            annArrLeft(_string);
            return;
        }
        if (curChar_ == ElResolver.ANN_ARR_RIGHT) {
            annArrRight();
            return;
        }
        if (curChar_ == ElResolver.ARR_LEFT) {
            arrLeft(_string);
            return;
        }
        if (curChar_ == ElResolver.ARR_RIGHT) {
            arrRight();
            return;
        }
        if (curChar_ == ElResolver.DOT_VAR) {
            dot();
            return;
        }
        lowerFct(cont_.getBeginDel(), _string.substring(index , index + cont_.getLength()));
    }
    private OperatorOffsetLength contained(int _index, Delimiters _d) {
        for (OperatorOffsetLength o: _d.getAllowedOperatorsIndexes()) {
            if (o.getOffset() == _index) {
                return o;
            }
        }
        return null;
    }

    private void dot() {
        if (parsBrackets == 0) {
            boolean clearOperators_ = false;
            boolean foundOperator_ = false;
            if (prio == ElResolver.FCT_OPER_PRIO) {
                clearOperators_ = true;
                foundOperator_ = true;
            }
            addPossibleNumOp(Character.toString(ElResolver.DOT_VAR), clearOperators_, foundOperator_);
        } else {
            index++;
        }
    }

    private void lowerFct(int _beginDel, String _oper) {
        if (matchChar(_oper, ElResolver.BEGIN_TERNARY) && _beginDel > 0) {
            beginTernary();
            return;
        }
        if (matchChar(_oper,ElResolver.END_TERNARY)) {
            endTernary();
            return;
        }
        if (matchChar(_oper,ElResolver.SEP_ARG)) {
            declPrio();
            return;
        }
        if (parsBrackets > 0 || prio == ElResolver.DECL_PRIO) {
            index++;
            return;
        }
        if (matchChar(_oper,ElResolver.BEGIN_TERNARY)) {
            safeDot(_oper);
            fctName = EMPTY_STRING;
            parsBrackets++;
            index++;
            return;
        }
        if (matchChars(_oper, ElResolver.BEGIN_TERNARY, ElResolver.DOT_VAR)) {
            safeDot(_oper);
            return;
        }
        addNumOperators(_oper);
    }

    private void namedArg(char _curChar) {
        if (parsBrackets > 0) {
            index++;
            return;
        }
        prio = ElResolver.NAME_PRIO;
        if (operators.isEmpty()) {
            operators.addEntry(index, Character.toString(_curChar));
        }
        enPars = false;
        enabledId = false;
        index++;
    }

    private void safeDot(String _oper) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        if (prio == ElResolver.FCT_OPER_PRIO) {
            clearOperators_ = true;
            foundOperator_ = true;
        }
        addPossibleNumOp(_oper, clearOperators_, foundOperator_);
    }

    private void beginTernary() {
        if (parsBrackets == 0 && prio > ElResolver.TERNARY_PRIO) {
            operators.clear();
            operators.addEntry(index, Character.toString(ElResolver.BEGIN_TERNARY));
        }
        parsBrackets++;
        index++;
    }

    private void endTernary() {
        parsBrackets--;
        if (parsBrackets == 0 && prio > ElResolver.TERNARY_PRIO) {
            operators.addEntry(index, Character.toString(ElResolver.END_TERNARY));
            enPars = false;
            enabledId = true;
            prio = ElResolver.TERNARY_PRIO;
        }
        index++;
    }

    private void parLeft(String _string) {
        if (parsBrackets == 0 && prio == ElResolver.FCT_OPER_PRIO) {
            if (enPars) {
                fctName = _string.substring(IndexConstants.FIRST_INDEX, index);
                operators.addEntry(index, Character.toString(ElResolver.PAR_LEFT));
            } else if (enabledId) {
                instance = false;
                instanceStrict = false;
                operators.clear();
                errorDot = true;
                operators.addEntry(index, EMPTY_STRING);
            }
        }
        parsBrackets++;
        index++;
    }

    private void commaSepFct() {
        if (parsBrackets == 1 && prio == ElResolver.FCT_OPER_PRIO && enPars) {
            addCommaOperIfNotEmpty(operators, index,ElResolver.PAR_LEFT,ElResolver.ARR_LEFT,ElResolver.ANN_ARR_LEFT);
        }
        index++;
    }

    private void declPrio() {
        if (prio > ElResolver.DECL_PRIO) {
            operators.clear();
        }
        instance = false;
        instanceStrict = false;
        enabledId = false;
        enPars = false;
        operators.addEntry(index, Character.toString(ElResolver.SEP_ARG));
        prio = ElResolver.DECL_PRIO;
        index++;
    }

    private void parRight() {
        parsBrackets--;
        if (parsBrackets == 0 && prio == ElResolver.FCT_OPER_PRIO && enPars) {
            addOperIfNotEmpty(operators, index, ElResolver.PAR_LEFT,ElResolver.PAR_RIGHT);
            enPars = false;
            enabledId = true;
        }
        index++;
    }

    private void annArrRight() {
        parsBrackets--;
        if (parsBrackets == 0 && prio == ElResolver.FCT_OPER_PRIO) {
            addOperIfNotEmpty(operators, index, ElResolver.ANN_ARR_LEFT,ElResolver.ANN_ARR_RIGHT);
            enPars = false;
            enabledId = true;
        }
        index++;
    }

    private void annArrLeft(String _string) {
        if (parsBrackets == 0 && ElResolver.FCT_OPER_PRIO == prio) {
            if (instance) {
                fctName = _string.substring(IndexConstants.FIRST_INDEX, index);
                if (operators.isEmpty()) {
                    operators.addEntry(index, Character.toString(ElResolver.ANN_ARR_LEFT));
                }
            } else {
                fctName = EMPTY_STRING;
                operators.clear();
                operators.addEntry(index, EMPTY_STRING);
                errorDot = true;
            }
        }
        parsBrackets++;
        index++;
    }

    private void arrRight() {
        parsBrackets--;
        if (parsBrackets == 0 && prio == ElResolver.FCT_OPER_PRIO) {
            addOperIfNotEmpty(operators, index, ElResolver.ARR_LEFT,ElResolver.ARR_RIGHT);
            enPars = false;
            enabledId = true;
        }
        index++;
    }

    private void arrLeft(String _string) {
        if (parsBrackets == 0 && ElResolver.FCT_OPER_PRIO == prio) {

            if (instance) {
                arrInstance(_string);
            } else {
                arrAccess();
            }
        }
        parsBrackets++;
        index++;
    }

    private void arrAccess() {
        int firstPrintChar_ = del.getFirstPrintIndex();
        fctName = EMPTY_STRING;
        operators.clear();
        if (firstPrintChar_ == index) {
            operators.addEntry(index, Character.toString(ElResolver.ARR_LEFT));
        } else {
            operators.addEntry(index, EMPTY_STRING);
        }
    }

    private void arrInstance(String _string) {
        if (operators.isEmpty()) {
            fctName = _string.substring(IndexConstants.FIRST_INDEX, index);
            operators.addEntry(index, Character.toString(ElResolver.ARR_LEFT));
        } else {
            String op_ = operators.firstValue();
            if (matchChar(op_, ElResolver.ARR_LEFT)) {
                operators.addEntry(index, Character.toString(ElResolver.ARR_LEFT));
            } else {
                fctName = EMPTY_STRING;
                instance = false;
                instanceStrict = false;
                operators.clear();
                operators.addEntry(index, EMPTY_STRING);
            }
        }
    }

    private void addNumOperators(String _oper) {
        if (matchChars(_oper,ElResolver.EQ_CHAR,ElResolver.EQ_CHAR) || matchChars(_oper,ElResolver.NEG_BOOL_CHAR,ElResolver.EQ_CHAR) || matchChar(_oper,ElResolver.NEG_BOOL_CHAR)) {
            eq(_oper);
            return;
        }
        if (matchChars(_oper, ElResolver.LOWER_CHAR, ElResolver.LOWER_CHAR) || matchChars(_oper, ElResolver.GREATER_CHAR, ElResolver.GREATER_CHAR)
                || matchThreeChars(_oper, ElResolver.LOWER_CHAR, ElResolver.LOWER_CHAR) || matchThreeChars(_oper, ElResolver.GREATER_CHAR, ElResolver.GREATER_CHAR)
                || matchFoursChars(_oper, ElResolver.LOWER_CHAR, ElResolver.LOWER_CHAR) || matchFoursChars(_oper, ElResolver.GREATER_CHAR, ElResolver.GREATER_CHAR)) {
            shiftOrRotate(_oper);
            return;
        }
        if (matchChar(_oper,ElResolver.LOWER_CHAR) || matchChar(_oper,ElResolver.GREATER_CHAR) || matchChars(_oper,ElResolver.LOWER_CHAR,ElResolver.EQ_CHAR) || matchChars(_oper,ElResolver.GREATER_CHAR,ElResolver.EQ_CHAR)) {
            lowerOrGreater(_oper);
            return;
        }
        if (matchChars(_oper,ElResolver.PLUS_CHAR,ElResolver.PLUS_CHAR) || matchChars(_oper,ElResolver.MINUS_CHAR,ElResolver.MINUS_CHAR)) {
            incrDecrPost(_oper);
            return;
        }
        if (matchChars(_oper,ElResolver.AND_CHAR,ElResolver.AND_CHAR)){
            logicalAndOr(_oper,ElResolver.AND_PRIO);
            return;
        }
        if (matchChars(_oper,ElResolver.OR_CHAR,ElResolver.OR_CHAR)){
            logicalAndOr(_oper,ElResolver.OR_PRIO);
            return;
        }
        if (matchChars(_oper,ElResolver.BEGIN_TERNARY,ElResolver.BEGIN_TERNARY)){
            nullSafe(_oper);
            return;
        }
        if (matchThreeChars(_oper,ElResolver.BEGIN_TERNARY, ElResolver.BEGIN_TERNARY)){
            range(_oper);
            return;
        }
        if (_oper.charAt(_oper.length() - 1) == ElResolver.EQ_CHAR) {
            addAffNumOp(_oper);
            return;
        }
        if (matchChar(_oper, ElResolver.MINUS_CHAR) || matchChar(_oper, ElResolver.PLUS_CHAR) || matchChar(_oper, ElResolver.MULT_CHAR)
           || matchChar(_oper, ElResolver.DIV_CHAR) || matchChar(_oper, ElResolver.MOD_CHAR) || matchChar(_oper, ElResolver.AND_CHAR)
                || matchChar(_oper, ElResolver.OR_CHAR) || matchChar(_oper, ElResolver.XOR_CHAR)) {
            changeSingleCharPrioAddPossible(_oper);
            return;
        }
        instaOf(_oper);
    }

    private static boolean matchFoursChars(String _oper, char _ch, char _chFourth) {
        return _oper.length() == 4&& _oper.charAt(0) == _ch && _oper.charAt(3) == _chFourth;
    }

    private static boolean matchThreeChars(String _oper, char _ch, char _chThird) {
        return _oper.length() == 3&& _oper.charAt(0) == _ch&& _oper.charAt(2) == _chThird;
    }

    private static boolean matchChars(String _oper, char _ch, char _chSec) {
        return _oper.length() == 2 && _oper.charAt(0) == _ch && _oper.charAt(1) == _chSec;
    }

    private static boolean matchChar(String _oper, char _ch) {
        return _oper.length() == 1 && _oper.charAt(0) == _ch;
    }

    private void instaOf(String _op) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        instOf = true;
        if (prio > ElResolver.CMP_PRIO) {
            clearOperators_ = true;
            prio = ElResolver.CMP_PRIO;
        }
        if (prio == ElResolver.CMP_PRIO) {
            foundOperator_ = true;
        }
        addPossibleNumOp(_op, clearOperators_, foundOperator_);
    }

    private void eq(String _operator) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        if (prio > ElResolver.EQ_PRIO) {
            prio = ElResolver.EQ_PRIO;
        }
        if (prio == ElResolver.EQ_PRIO) {
            clearOperators_ = true;
            foundOperator_ = true;
        }
        addPossibleNumOp(_operator, clearOperators_, foundOperator_);

    }

    private void lowerOrGreater(String _operator) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        if (prio > ElResolver.CMP_PRIO) {
            clearOperators_ = true;
            prio = ElResolver.CMP_PRIO;
        }
        if (prio == ElResolver.CMP_PRIO) {
            //Event if there is a previous operator, add the current operator
            //Sample: 1<2<3 produces an error
            foundOperator_ = true;
        }
        addPossibleNumOp(_operator, clearOperators_, foundOperator_);
    }

    private void shiftOrRotate(String _oper) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        if (prio > ElResolver.SHIFT_PRIO) {
            prio = ElResolver.SHIFT_PRIO;
        }
        if (prio == ElResolver.SHIFT_PRIO) {
            clearOperators_ = true;
            foundOperator_ = true;
        }
        addPossibleNumOp(_oper, clearOperators_, foundOperator_);
    }

    private void incrDecrPost(String _oper) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        if (prio > ElResolver.POST_INCR_PRIO) {
            clearOperators_ = true;
            prio = ElResolver.POST_INCR_PRIO;
            foundOperator_ = true;
        }
        addPossibleNumOp(_oper, clearOperators_, foundOperator_);
    }

    private void logicalAndOr(String _oper, int _prioOpMult) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        if (prio > _prioOpMult) {
            prio = _prioOpMult;
        }
        if (prio == _prioOpMult) {
            clearOperators_ = true;
            foundOperator_ = true;
        }
        addPossibleNumOp(_oper, clearOperators_, foundOperator_);
    }

    private boolean chgPrioAff() {
        if (prio > ElResolver.AFF_PRIO && prio != ElResolver.TERNARY_PRIO && prio != ElResolver.NULL_SAFE_PRIO) {
            prio = ElResolver.AFF_PRIO;
            return true;
        }
        return false;
    }

    private void range(String _oper) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        if (prio > ElResolver.RANGE) {
            clearOperators_ = true;
            prio = ElResolver.RANGE;
        }
        if (prio == ElResolver.RANGE) {
            //Event if there is a previous operator, add the current operator
            //Sample: 1<2<3 produces an error
            foundOperator_ = true;
        }
        addPossibleNumOp(_oper, clearOperators_, foundOperator_);
    }

    private void nullSafe(String _oper) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        if (prio > ElResolver.NULL_SAFE_PRIO) {
            prio = ElResolver.NULL_SAFE_PRIO;
            clearOperators_ = true;
            foundOperator_ = true;
        }
        addPossibleNumOp(_oper, clearOperators_, foundOperator_);
    }

    private void changeSingleCharPrioAddPossible(String _op) {
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        int prioOpMult_ = getSingleCharPrio(_op.charAt(0));
        if (prio > prioOpMult_) {
            prio = prioOpMult_;
        }
        if (prio == prioOpMult_) {
            clearOperators_ = true;
            foundOperator_ = true;
        }
        addPossibleNumOp(_op, clearOperators_, foundOperator_);
    }

    private void addAffNumOp(String _oper) {
        boolean foundOperator_ = chgPrioAff();
        addPossibleNumOp(_oper, foundOperator_, foundOperator_);
    }

    private void addPossibleNumOp(String _op, boolean _clearOpers, boolean _foundOper) {
        if (_foundOper) {
            if (_clearOpers) {
                operators.clear();
            }
            fctName = EMPTY_STRING;
            instance = false;
            instanceStrict = false;
            enPars = false;
            enabledId = false;
            operators.addEntry(index,_op);
        }
        index += _op.length();
    }

    private static int getSingleCharPrio(char _oper) {
        if (_oper == ElResolver.MINUS_CHAR || _oper == ElResolver.PLUS_CHAR) {
            return ElResolver.ADD_PRIO;
        }
        if (_oper == ElResolver.MULT_CHAR || _oper == ElResolver.DIV_CHAR || _oper == ElResolver.MOD_CHAR) {
            return ElResolver.MULT_PRIO;
        }
        if (_oper == ElResolver.AND_CHAR) {
            return ElResolver.BIT_AND_PRIO;
        }
        if (_oper == ElResolver.OR_CHAR) {
            return ElResolver.BIT_OR_PRIO;
        }
        return ElResolver.BIT_XOR_PRIO;
    }

    private static int incrementUnary(String _string, int _from, int _to, int _offset, Delimiters _d) {
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (ch_ == ElResolver.MINUS_CHAR || ch_ == ElResolver.NEG_BOOL || ch_ == ElResolver.PLUS_CHAR || ch_ == ElResolver.NEG_BOOL_CHAR || ch_ == ElResolver.MULT_CHAR || StringUtil.isWhitespace(ch_)) {
                j_++;
            } else {
                int sum_ = _offset + j_;
                int indexCast_ = _d.getDelCast().indexOfNb(sum_);
                if (indexCast_ > -1) {
                    int next_ = _d.getDelCast().get(indexCast_ + 1) - _offset;
                    j_ = next_ + 1;
                } else {
                    indexCast_ = _d.getDelExplicit().indexOfNb(sum_);
                    if (indexCast_ > -1) {
                        int next_ = _d.getDelExplicit().get(indexCast_ + 1) - _offset;
                        j_ = next_ + 1;
                    } else {
                        break;
                    }
                }
            }
        }
        return j_;
    }

    private static void addCommaOperIfNotEmpty(StrTypes _operators, int _i, char... _open) {
        String oper_ = _operators.firstValue();
        int len_ = oper_.length();
        for (char c: _open) {
            if (StringExpUtil.nextCharIs(oper_,0, len_,c)) {
                _operators.addEntry(_i, Character.toString(ElResolver.SEP_ARG));
                break;
            }
        }
    }


    private static void addOperIfNotEmpty(StrTypes _operators, int _i, char _open, char _op) {
        String oper_ = _operators.firstValue();
        if (StringExpUtil.nextCharIs(oper_,0, oper_.length(),_open)) {
            _operators.addEntry(_i, Character.toString(_op));
        }
    }

    boolean isErrorDot() {
        return errorDot;
    }

    int getIndex() {
        return index;
    }

    AnaResultPartType getPartsOffs() {
        return partsOffs;
    }

    int getPrio() {
        return prio;
    }

    StrTypes getOperators() {
        return operators;
    }

    String getExtracted() {
        return extracted;
    }

    Ints getLaterIndexesDouble() {
        return laterIndexesDouble;
    }

    String getFctName() {
        return fctName;
    }

    boolean isInstance() {
        return instance;
    }

    boolean isInstOf() {
        return instOf;
    }

    AbsBk getBlock() {
        return block;
    }

    int getLength() {
        return length;
    }

    int getDeltaAnnot() {
        return deltaAnnot;
    }

    String getRetSwitch() {
        return retSwitch;
    }
}
