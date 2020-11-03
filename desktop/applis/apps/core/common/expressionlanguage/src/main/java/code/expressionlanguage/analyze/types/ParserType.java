package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.ArrayResult;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.types.KindPartType;
import code.util.*;
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
            if (curChar_ == Templates.LT) {
                addDot_ = false;
                id_.delete(0, id_.length());
                indexes_.add(i_);
                count_++;
                i_++;
                continue;
            }
            if (curChar_ == Templates.GT) {
                if (count_ == 0) {
                    return null;
                }
                addDot_ = true;
                id_.delete(0, id_.length());
                indexes_.add(i_);
                count_--;
                i_++;
                continue;
            }
            if (curChar_ == Templates.COMMA) {
                if (count_ == 0) {
                    return null;
                }
                addDot_ = false;
                id_.delete(0, id_.length());
                indexes_.add(i_);
                i_++;
                continue;
            }
            if (curChar_ == Templates.SEP_CLASS_CHAR) {
                if (StringExpUtil.nextCharIs(_input, i_ + 1, len_, Templates.SEP_CLASS_CHAR)) {
                    indexes_.add(i_);
                    id_.delete(0,id_.length());
                    i_+=2;
                    continue;
                }
                String tr_ = StringExpUtil.removeDottedSpaces(id_.toString());
                if (!StringUtil.contains(_pkg,tr_)) {
                    addDot_ = true;
                }
                if (addDot_) {
                    indexes_.add(i_);
                    id_.delete(0,id_.length());
                } else {
                    id_.append(curChar_);
                }
            } else {
                if (curChar_ != '?' && curChar_ != '!') {
                    id_.append(curChar_);
                }
            }
            i_++;
        }
        if (count_ > 0) {
            return null;
        }
        return indexes_;
    }

    public static AnalyzingType analyzeLocal(int _offset, String _string, Ints _indexes) {
        AnalyzingType a_ = new AnalyzingType();
        a_.getIndexes().addAllElts(_indexes);
        if (StringExpUtil.isTypeLeafPart(_string.trim())) {
            a_.setKind(KindPartType.TYPE_NAME);
            a_.setupValue(_string);
            return a_;
        }
        return analyzeOther(_offset, _string, _indexes, a_);
    }

    public static AnalyzingType analyzeLocalId(int _offset, String _string, Ints _indexes) {
        AnalyzingType a_ = new AnalyzingType();
        a_.getIndexes().addAllElts(_indexes);
        if (StringExpUtil.isTypeLeafPartExec(_string.trim())) {
            a_.setKind(KindPartType.TYPE_NAME);
            a_.setupValue(_string);
            return a_;
        }
        return analyzeOther(_offset, _string, _indexes, a_);
    }

    private static AnalyzingType analyzeOther(int _offset, String _string, Ints _indexes, AnalyzingType _a) {
        IntTreeMap<String> values_ = _a.getValues();
        if (_string.trim().isEmpty()) {
            values_.addEntry((int)IndexConstants.FIRST_INDEX, _string);
            _a.setError(true);
            return _a;
        }
        if (StringUtil.quickEq(_string.trim(), Templates.SUB_TYPE)) {
            _a.setKind(KindPartType.EMPTY_WILD_CARD);
            _a.setupValue(_string);
            return _a;
        }
        if (_string.trim().startsWith(Templates.SUB_TYPE)) {
            _a.setPrio(WILD_CARD_PRIO);
            _a.setupWildCardValues(Templates.SUB_TYPE, _string);
            return _a;
        }
        if (_string.trim().startsWith(Templates.SUP_TYPE)) {
            if (StringUtil.quickEq(_string.trim(), Templates.SUP_TYPE)) {
                _a.setError(true);
            }
            _a.setPrio(WILD_CARD_PRIO);
            _a.setupWildCardValues(Templates.SUP_TYPE, _string);
            return _a;
        }
        IntTreeMap<String> operators_ = _a.getOperators();
        ArrayResult res_ = StringExpUtil.tryGetArray(_string, values_,operators_);
        if (res_ != ArrayResult.NONE) {
            if (res_ == ArrayResult.ERROR) {
                values_.addEntry((int) IndexConstants.FIRST_INDEX, _string);
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
            char curChar_ = _string.charAt(i_);
            if (!_indexes.containsObj((long)i_+_offset)) {
                i_++;
                continue;
            }
            if (curChar_ == Templates.LT) {
                if (count_== 0 && prio_ == TMP_PRIO) {
                    operators_.clear();
                    operators_.addEntry(i_,Templates.TEMPLATE_BEGIN);
                }
                count_++;
            }
            if (curChar_ == Templates.COMMA && count_ == 1 && prio_ == TMP_PRIO) {
                operators_.addEntry(i_, Templates.TEMPLATE_SEP);
            }
            if (curChar_ == Templates.GT) {
                count_--;
                if (count_ == 0 && prio_ == TMP_PRIO) {
                    operators_.addEntry(i_,Templates.TEMPLATE_END);
                }
            }
            if (count_ == 0) {
                if (curChar_ == Templates.SEP_CLASS_CHAR) {
                    if (prio_ > INT_PRIO) {
                        operators_.clear();
                        prio_ = INT_PRIO;
                    }
                    if (StringExpUtil.nextCharIs(_string, i_ + 1, len_, Templates.SEP_CLASS_CHAR)) {
                        operators_.addEntry(i_,"..");
                    } else {
                        operators_.addEntry(i_,".");
                    }
                }
            }
            i_++;
        }
        if (operators_.isEmpty()) {
            if (isTypeLeaf(_string)) {
                _a.setKind(KindPartType.TYPE_NAME);
                _a.setupValue(_string);
                return _a;
            }
        }
        _a.setPrio(prio_);
        _a.setupValues(_string);
        return _a;
    }
    private static boolean isTypeLeaf(String _string) {
        for (String p : StringUtil.splitChars(_string, Templates.SEP_CLASS_CHAR)) {
            if (!StringExpUtil.isTypeLeafPart(p.trim())) {
                return false;
            }
        }
        return true;
    }

}
