package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.ArrayResult;
import code.maths.litteralcom.StrTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.types.KindPartType;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ParserType {

    public static final int WILD_CARD_PRIO = 1;
    public static final int ARR_PRIO = 2;
    public static final int INT_PRIO = 3;
    public static final int TMP_PRIO = 4;
    private ParserType(){}

    public static Ints getIndexes(String _input, AnalyzedPageEl _page) {
        return getIndexes(_input, _page.getPackagesFound());
    }
    public static Ints getIndexes(String _input, StringList _pkg) {
        int count_ = 0;
        int len_ = _input.length();
        int i_ = 0;
        Ints indexes_ = new Ints();
        boolean addDot_ = false;
        StringBuilder id_ = new StringBuilder();
        while (i_ < len_) {
            char curChar_ = _input.charAt(i_);
            if (curChar_ == StringExpUtil.LT) {
                addDot_ = false;
                id_.delete(0, id_.length());
                indexes_.add(i_);
                count_++;
                i_++;
            } else if (curChar_ == StringExpUtil.GT) {
                if (count_ == 0) {
                    return null;
                }
                addDot_ = true;
                id_.delete(0, id_.length());
                indexes_.add(i_);
                count_--;
                i_++;
            } else if (curChar_ == StringExpUtil.COMMA) {
                if (count_ == 0) {
                    return null;
                }
                addDot_ = false;
                id_.delete(0, id_.length());
                indexes_.add(i_);
                i_++;
            } else if (curChar_ == StringExpUtil.SEP_CLASS_CHAR&&StringExpUtil.nextCharIs(_input, i_ + 1, len_, StringExpUtil.SEP_CLASS_CHAR)) {
                indexes_.add(i_);
                id_.delete(0,id_.length());
                i_+=2;
            } else if (curChar_ == StringExpUtil.SEP_CLASS_CHAR) {
                addDot_ = addDot(_pkg, i_, indexes_, addDot_, id_, curChar_);
                i_++;
            } else {
                appendToId(id_, curChar_);
                i_++;
            }
        }
        return endCheck(count_, indexes_);
    }

    private static Ints endCheck(int _count, Ints _indexes) {
        if (_count > 0) {
            return null;
        }
        return _indexes;
    }

    private static boolean addDot(StringList _pkg, int _i, Ints _indexes, boolean _addDot, StringBuilder _id, char _ch) {
        boolean addDot_ = _addDot;
        String tr_ = StringExpUtil.removeDottedSpaces(_id.toString());
        if (!StringUtil.contains(_pkg, tr_)) {
            addDot_ = true;
        }
        if (addDot_) {
            _indexes.add(_i);
            _id.delete(0, _id.length());
        } else {
            _id.append(_ch);
        }
        return addDot_;
    }

    private static void appendToId(StringBuilder _id, char _ch) {
        if (_ch != '?' && _ch != '!' && _ch != '~') {
            _id.append(_ch);
        }
    }

    public static AnalyzingType analyzeLocal(int _offset, String _string, Ints _indexes) {
        AnalyzingType a_ = new AnalyzingType();
        if (StringExpUtil.isTypeLeafPart(_string.trim())) {
            a_.setKind(KindPartType.TYPE_NAME);
            a_.setupValue(_string);
            return a_;
        }
        return analyzeOther(_offset, _string, _indexes, a_);
    }

    public static AnalyzingType analyzeLocalId(int _offset, String _string, Ints _indexes) {
        AnalyzingType a_ = new AnalyzingType();
        if (StringExpUtil.isTypeLeafPartExec(_string.trim())) {
            a_.setKind(KindPartType.TYPE_NAME);
            a_.setupValue(_string);
            return a_;
        }
        return analyzeOther(_offset, _string, _indexes, a_);
    }

    private static AnalyzingType analyzeOther(int _offset, String _string, Ints _indexes, AnalyzingType _a) {
        StrTypes values_ = _a.getValues();
        if (_string.trim().isEmpty()) {
            values_.addEntry(IndexConstants.FIRST_INDEX, _string);
            _a.setError(true);
            return _a;
        }
        if (StringUtil.quickEq(_string.trim(), StringExpUtil.SUB_TYPE)) {
            _a.setKind(KindPartType.EMPTY_WILD_CARD);
            _a.setupValue(_string);
            return _a;
        }
        if (_string.trim().startsWith(StringExpUtil.SUB_TYPE)) {
            _a.setPrio(WILD_CARD_PRIO);
            _a.setupWildCardValues(StringExpUtil.SUB_TYPE, _string);
            return _a;
        }
        if (_string.trim().startsWith(StringExpUtil.SUP_TYPE)) {
            return strictUnary(_string, StringExpUtil.SUP_TYPE, _a);
        }
        if (_string.trim().startsWith("~")) {
            return strictUnary(_string, "~", _a);
        }
        StrTypes operators_ = _a.getOperators();
        ArrayResult res_ = StringExpUtil.tryGetArray(_string, values_,operators_);
        if (res_ != ArrayResult.NONE) {
            if (res_ == ArrayResult.ERROR) {
                values_.addEntry(IndexConstants.FIRST_INDEX, _string);
                _a.setError(true);
            } else {
                _a.setPrio(ARR_PRIO);
            }
            return _a;
        }
        int count_ = 0;
        int len_ = _string.length();
        int i_ = 0;
        int prio_ = TMP_PRIO;
        while (i_ < len_) {
            if (!_indexes.containsObj((long)i_+_offset)) {
                i_++;
                continue;
            }
            count_ = ltGt(_string,operators_, count_, i_, prio_);
            prio_ = dotOperator(_string, operators_, count_, i_, prio_);
            i_++;
        }
        if (operators_.isEmpty() && isTypeLeaf(_string)) {
            _a.setKind(KindPartType.TYPE_NAME);
            _a.setupValue(_string);
            return _a;
        }
        _a.setPrio(prio_);
        _a.setupValues(_string);
        return _a;
    }

    private static int ltGt(String _string, StrTypes _operators, int _count, int _i, int _prio) {
        char curChar_ = _string.charAt(_i);
        int count_ = _count;
        if (curChar_ == StringExpUtil.LT) {
            if (count_ == 0 && _prio == TMP_PRIO) {
                _operators.clear();
                _operators.addEntry(_i,StringExpUtil.TEMPLATE_BEGIN);
            }
            count_++;
        }
        if (curChar_ == StringExpUtil.COMMA && count_ == 1 && _prio == TMP_PRIO) {
            _operators.addEntry(_i, StringExpUtil.TEMPLATE_SEP);
        }
        if (curChar_ == StringExpUtil.GT) {
            count_--;
            if (count_ == 0 && _prio == TMP_PRIO) {
                _operators.addEntry(_i,StringExpUtil.TEMPLATE_END);
            }
        }
        return count_;
    }

    private static int dotOperator(String _string, StrTypes _operators, int _count, int _i, int _prio) {
        int prio_ = _prio;
        char curChar_ = _string.charAt(_i);
        if (_count == 0 && curChar_ == StringExpUtil.SEP_CLASS_CHAR) {
            if (prio_ > INT_PRIO) {
                _operators.clear();
                prio_ = INT_PRIO;
            }
            dottedType(_string, _operators, _i);
        }
        return prio_;
    }

    private static void dottedType(String _string, StrTypes _operators, int _i) {
        int len_ = _string.length();
        if (StringExpUtil.nextCharIs(_string, _i + 1, len_, StringExpUtil.SEP_CLASS_CHAR)) {
            _operators.addEntry(_i, "..");
        } else {
            _operators.addEntry(_i, ".");
        }
    }

    private static AnalyzingType strictUnary(String _string, String _oper, AnalyzingType _a) {
        if (StringUtil.quickEq(_string.trim(), _oper)) {
            _a.setError(true);
        }
        _a.setPrio(ParserType.WILD_CARD_PRIO);
        _a.setupWildCardValues(_oper, _string);
        return _a;
    }

    private static boolean isTypeLeaf(String _string) {
        for (String p : StringUtil.splitChars(_string, StringExpUtil.SEP_CLASS_CHAR)) {
            if (!StringExpUtil.isTypeLeafPart(p.trim())) {
                return false;
            }
        }
        return true;
    }

}
