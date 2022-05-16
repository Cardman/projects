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

    private static final String ARR = "[";
    private static final String ANN_ARR = "{";

    private static final char NEG_BOOL_CHAR = '!';

    private static final char PLUS_CHAR = '+';

    private static final char MINUS_CHAR = '-';

    private static final char NEG_BOOL = '~';
    private static final char BEGIN_TERNARY = '?';
    private static final char END_TERNARY = ':';
    private static final char ARR_LEFT = '[';
    private static final char ARR_RIGHT = ']';
    private static final char ANN_ARR_LEFT = '{';
    private static final char ANN_ARR_RIGHT = '}';
    private static final char PAR_LEFT = '(';
    private static final char PAR_RIGHT = ')';
    private static final char SEP_ARG = ',';
    private static final char DOT_VAR = '.';

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
        boolean preIncr_ = (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR) && firstPrintChar_ + 1 < _string.length() && _string.charAt(firstPrintChar_) == _string.charAt(firstPrintChar_ + 1);
        if (preIncr_) {
            prio = ElResolver.UNARY_PRIO;
            String ch_ = Character.toString(_string.charAt(firstPrintChar_));
            operators.addEntry(firstPrintChar_, StringUtil.concat(EMPTY_STRING,ch_,ch_));
            index = incrementUnary(_string, firstPrintChar_ + 2, lastPrintChar_, _offset, _d);
            return;
        }
        if (_string.charAt(firstPrintChar_) == MINUS_CHAR || _string.charAt(firstPrintChar_) == PLUS_CHAR
                || _string.charAt(firstPrintChar_) == NEG_BOOL_CHAR || _string.charAt(firstPrintChar_) == NEG_BOOL || _string.charAt(firstPrintChar_) == '*') {
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
            if (StringExpUtil.nextCharIs(_string,index_,_string.length(),'{')) {
                next_ = DefaultProcessKeyWord.skipWhiteSpace(_string,index_+1);
            }
            if (StringExpUtil.nextCharIs(_string,next_,_string.length(),'}')) {
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
        if (_string.charAt(firstPrintChar_) == ANN_ARR_LEFT) {
            instance = true;
        }
    }
    private int nextForAnon(int _offset) {
        block = null;
        if (instanceStrict) {
            for (AnonymousResult a: anonymousResults) {
                if (a.getIndex() == index + _offset) {
                    block = a.getType();
                    length = a.getLength();
                    return a.getUntil() - _offset + 1;
                }
            }
        }
        if (switchStrict) {
            for (AnonymousResult a: anonymousResults) {
                if (a.getIndex() == index + _offset) {
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
        int nex_ = nextForAnon(_offset);
        if (nex_ > old_) {
            index = nex_;
            return;
        }
        if (_d.getDimsAddonIndexes().containsObj((long)index+_offset)) {
            laterIndexesDouble.add(index);
            index++;
            return;
        }
        if (_d.getNamedArgs().containsObj((long)index+_offset)) {
            namedArg(curChar_);
            return;
        }
        if (prio == ElResolver.NAME_PRIO) {
            enPars = false;
            enabledId = false;
            index++;
            return;
        }
        OperatorOffsetLength cont_ = contained((long) index + _offset, _d);
        if (cont_ == null) {
            index++;
            return;
        }
        if (curChar_ == PAR_LEFT) {
            parLeft(_string);
            return;
        }
        if (curChar_ == SEP_ARG && parsBrackets > 0) {
            commaSepFct();
            return;
        }
        if (curChar_ == PAR_RIGHT) {
            parRight();
            return;
        }
        if (curChar_ == ANN_ARR_LEFT) {
            annArrLeft(_string);
            return;
        }
        if (curChar_ == ANN_ARR_RIGHT) {
            annArrRight();
            return;
        }
        if (curChar_ == ARR_LEFT) {
            arrLeft(_string);
            return;
        }
        if (curChar_ == ARR_RIGHT) {
            arrRight();
            return;
        }
        if (curChar_ == DOT_VAR) {
            dot();
            return;
        }
        lowerFct(_offset, _string, _d, _string.substring(index , index + cont_.getLength()));
    }
    private OperatorOffsetLength contained(long _index, Delimiters _d) {
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
            addPossibleNumOp(".", clearOperators_, foundOperator_);
        } else {
            index++;
        }
    }

    private void lowerFct(int _offset, String _string, Delimiters _d, String _oper) {
        if (_oper.startsWith("?")) {
            int lastPrintChar_ = del.getLastPrintIndex();
            int len_ = lastPrintChar_ + 1;
            if (StringUtil.quickEq(_oper,"?")&&!StringExpUtil.nextCharIs(_string,index+1,len_,'[')) {
                beginTernary();
                return;
            }
            if (parsBrackets == 0&&(StringUtil.quickEq(_oper,"?")||StringUtil.quickEq(_oper,"?."))&&prio != ElResolver.DECL_PRIO) {
                safeDot(_oper);
                return;
            }
        }
        if (StringUtil.quickEq(_oper,":")) {
            endTernary();
            return;
        }
        if (StringUtil.quickEq(_oper,",")) {
            declPrio();
            return;
        }
        if (parsBrackets > 0 || prio == ElResolver.DECL_PRIO) {
            index++;
            return;
        }
        addNumOperators(_offset, _string, _d, _oper);
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
            operators.addEntry(index, Character.toString(AfterUnaryParts.BEGIN_TERNARY));
        }
        parsBrackets++;
        index++;
    }

    private void endTernary() {
        parsBrackets--;
        if (parsBrackets == 0 && prio > ElResolver.TERNARY_PRIO) {
            operators.addEntry(index, Character.toString(AfterUnaryParts.END_TERNARY));
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
                operators.addEntry(index, Character.toString(PAR_LEFT));
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
            addCommaOperIfNotEmpty(operators, index,PAR_LEFT,ARR_LEFT,ANN_ARR_LEFT);
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
        operators.addEntry(index, Character.toString(SEP_ARG));
        prio = ElResolver.DECL_PRIO;
        index++;
    }

    private void parRight() {
        parsBrackets--;
        if (parsBrackets == 0 && prio == ElResolver.FCT_OPER_PRIO && enPars) {
            addOperIfNotEmpty(operators, index, PAR_LEFT,PAR_RIGHT);
            enPars = false;
            enabledId = true;
        }
        index++;
    }

    private void annArrRight() {
        parsBrackets--;
        if (parsBrackets == 0 && prio == ElResolver.FCT_OPER_PRIO) {
            addOperIfNotEmpty(operators, index, ANN_ARR_LEFT,ANN_ARR_RIGHT);
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
                    operators.addEntry(index, ANN_ARR);
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
            addOperIfNotEmpty(operators, index, ARR_LEFT,ARR_RIGHT);
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
                arrAccess(_string);
            }
        }
        parsBrackets++;
        index++;
    }

    private void arrAccess(String _string) {
        int firstPrintChar_ = del.getFirstPrintIndex();
        fctName = EMPTY_STRING;
        if (firstPrintChar_ == index) {
            operators.clear();
            operators.addEntry(index, ARR);
        } else {
            if (_string.charAt(index - 1) != '?') {
                operators.clear();
                operators.addEntry(index, EMPTY_STRING);
            }
        }
    }

    private void arrInstance(String _string) {
        if (operators.isEmpty()) {
            fctName = _string.substring(IndexConstants.FIRST_INDEX, index);
            operators.addEntry(index, ARR);
        } else {
            String op_ = operators.firstValue();
            if (StringUtil.quickEq(op_, ARR)) {
                operators.addEntry(index, ARR);
            } else {
                fctName = EMPTY_STRING;
                instance = false;
                instanceStrict = false;
                operators.clear();
                operators.addEntry(index, EMPTY_STRING);
            }
        }
    }

    private void addNumOperators(int _offset, String _string, Delimiters _d, String _oper) {
        int min_ = _d.getDelInstanceof().indexOfNb((long)index+_offset);
        if (isPairPositive(min_)) {
            instaOf(_offset, _string, _d);
            return;
        }
        if (StringUtil.quickEq("==", _oper) || StringUtil.quickEq("!=", _oper) || StringUtil.quickEq("!", _oper)) {
            eq(_oper);
            return;
        }
        if (StringUtil.quickEq("<<", _oper) || StringUtil.quickEq(">>", _oper) || StringUtil.quickEq("<<<", _oper) || StringUtil.quickEq(">>>", _oper) || StringUtil.quickEq("<<<<", _oper) || StringUtil.quickEq(">>>>", _oper)) {
            shiftOrRotate(_oper);
            return;
        }
        if (StringUtil.quickEq("<", _oper) || StringUtil.quickEq(">", _oper) || StringUtil.quickEq("<=", _oper) || StringUtil.quickEq(">=", _oper)) {
            lowerOrGreater(_oper);
            return;
        }
        if (StringUtil.quickEq("++", _oper) || StringUtil.quickEq("--", _oper)) {
            incrDecrPost(_oper);
            return;
        }
        if (StringUtil.quickEq("&&", _oper)){
            logicalAndOr(_oper,ElResolver.AND_PRIO);
            return;
        }
        if (StringUtil.quickEq("||", _oper)){
            logicalAndOr(_oper,ElResolver.OR_PRIO);
            return;
        }
        if (StringUtil.quickEq("??", _oper)){
            nullSafe(_oper);
            return;
        }
        if (StringUtil.quickEq("???", _oper)){
            range(_oper);
            return;
        }
        if (_oper.endsWith("=")) {
            addAffNumOp(_oper);
            return;
        }
        changeSingleCharPrioAddPossible(_oper);
    }

    private void instaOf(int _offset, String _string, Delimiters _d) {
        int min_ = _d.getDelInstanceof().indexOfNb((long)index+_offset);
        boolean clearOperators_ = false;
        boolean foundOperator_ = false;
        int next_ = _d.getDelInstanceof().get(min_ +1) - _offset;
        instOf = true;
        if (prio > ElResolver.CMP_PRIO) {
            clearOperators_ = true;
            prio = ElResolver.CMP_PRIO;
        }
        if (prio == ElResolver.CMP_PRIO) {
            foundOperator_ = true;
        }
        String op_ = _string.substring(index, next_);
        addPossibleNumOp(op_, clearOperators_, foundOperator_);
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
        int prioOpMult_ = getSingleCharPrio(_op);
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

    private static boolean isPairPositive(int _nb) {
        return _nb >= 0 && _nb % 2 == 0;
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

    private static int getSingleCharPrio(String _oper) {
        if (StringUtil.quickEq(_oper,"-") || StringUtil.quickEq(_oper,"+")) {
            return ElResolver.ADD_PRIO;
        }
        if (StringUtil.quickEq(_oper,"*") || StringUtil.quickEq(_oper,"/") || StringUtil.quickEq(_oper,"%")) {
            return ElResolver.MULT_PRIO;
        }
        if (StringUtil.quickEq(_oper,"&")) {
            return ElResolver.BIT_AND_PRIO;
        }
        if (StringUtil.quickEq(_oper,"|")) {
            return ElResolver.BIT_OR_PRIO;
        }
        return ElResolver.BIT_XOR_PRIO;
    }

    private static int incrementUnary(String _string, int _from, int _to, int _offset, Delimiters _d) {
        int j_ = _from;
        while (j_ <= _to) {
            char ch_ = _string.charAt(j_);
            if (ch_ == MINUS_CHAR || ch_ == NEG_BOOL || ch_ == PLUS_CHAR || ch_ == NEG_BOOL_CHAR || ch_ == '*' || StringUtil.isWhitespace(ch_)) {
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
                _operators.addEntry(_i, Character.toString(SEP_ARG));
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
