package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.*;
import code.util.Ints;
import code.util.StringList;

public final class ParserType {

    public static final int WILD_CARD_PRIO = 1;
    public static final int ARR_PRIO = 2;
    public static final int INT_PRIO = 3;
    public static final int TMP_PRIO = 4;
    private ParserType(){}

    public static Ints getIndexes(String _input, ContextEl _an) {
        return getIndexes(_input,_an.getAnalyzing().getPackagesFound());
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
                if (nextCharIsDot(_input, len_, i_)) {
                    indexes_.add(i_);
                    id_.delete(0,id_.length());
                    i_+=2;
                    continue;
                }
                String tr_ = StringExpUtil.removeDottedSpaces(id_.toString());
                if (!StringList.contains(_pkg,tr_)) {
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

    public static Ints getIndexesExec(String _input) {
        return getDoubleDotIndexes(_input);
    }

    private static Ints getDoubleDotIndexes(String _input) {
        int count_ = 0;
        int len_ = _input.length();
        int i_ = 0;
        Ints indexes_ = new Ints();
        while (i_ < len_) {
            char curChar_ = _input.charAt(i_);
            if (curChar_ == Templates.LT) {
                indexes_.add(i_);
                count_++;
            }
            if (curChar_ == Templates.GT) {
                if (count_ == 0) {
                    return null;
                }
                indexes_.add(i_);
                count_--;
            }
            if (curChar_ == Templates.COMMA) {
                if (count_ == 0) {
                    return null;
                }
                indexes_.add(i_);
            }
            if (curChar_ == Templates.SEP_CLASS_CHAR) {
                if (nextCharIsDot(_input, len_, i_)) {
                    indexes_.add(i_);
                    i_++;
                }
            } else if (curChar_ == '-') {
                indexes_.add(i_);
            }
            i_++;
        }
        if (count_ > 0) {
            return null;
        }
        return indexes_;
    }

    private static boolean nextCharIsDot(String _input, int _len, int _i) {
        return _i + 1 < _len && _input.charAt(_i + 1) == Templates.SEP_CLASS_CHAR;
    }

    public static AnalyzingType analyzeLocal(int _offset, String _string, Ints _indexes) {
        AnalyzingType a_ = new AnalyzingType();
        a_.getIndexes().addAllElts(_indexes);
        if (_string.trim().isEmpty()) {
            a_.getValues().put((int)CustList.FIRST_INDEX, _string);
            a_.setError(true);
            return a_;
        }
        if (StringExpUtil.isVar(_string)) {
            a_.setKind(KindPartType.VARIABLE);
            a_.setupValue(_string);
            return a_;
        }
        if (StringExpUtil.isTypeLeafPart(_string.trim())) {
            a_.setKind(KindPartType.TYPE_NAME);
            a_.setupValue(_string);
            return a_;
        }
        if (StringList.quickEq(_string.trim(), Templates.SUB_TYPE)) {
            a_.setKind(KindPartType.EMPTY_WILD_CARD);
            a_.setupValue(_string);
            return a_;
        }
        if (_string.trim().startsWith(Templates.SUB_TYPE)) {
            a_.setPrio(WILD_CARD_PRIO);
            a_.setupWildCardValues(Templates.SUB_TYPE, _string);
            return a_;
        }
        if (_string.trim().startsWith(Templates.SUP_TYPE)) {
            if (StringList.quickEq(_string.trim(), Templates.SUP_TYPE)) {
                a_.setError(true);
            }
            a_.setPrio(WILD_CARD_PRIO);
            a_.setupWildCardValues(Templates.SUP_TYPE, _string);
            return a_;
        }
        if (tryGetArray(a_, _string)) {
            return a_;
        }
        int count_ = 0;
        int len_ = _string.length();
        int i_ = 0;
        int prio_ = TMP_PRIO;
        IntTreeMap<String> operators_;
        operators_ = new IntTreeMap<String>();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (!_indexes.containsObj((long)i_+_offset)) {
                i_++;
                continue;
            }
            if (curChar_ == Templates.LT) {
                if (count_== 0 && prio_ == TMP_PRIO) {
                    operators_.clear();
                    operators_.put(i_,Templates.TEMPLATE_BEGIN);
                }
                count_++;
            }
            if (curChar_ == Templates.COMMA && count_ == 1 && prio_ == TMP_PRIO) {
                operators_.put(i_, Templates.TEMPLATE_SEP);
            }
            if (curChar_ == Templates.GT) {
                count_--;
                if (count_ == 0 && prio_ == TMP_PRIO) {
                    operators_.put(i_,Templates.TEMPLATE_END);
                }
            }
            if (count_ == 0) {
                if (curChar_ == Templates.SEP_CLASS_CHAR) {
                    if (prio_ > INT_PRIO) {
                        operators_.clear();
                        prio_ = INT_PRIO;
                    }
                    if (nextCharIsDot(_string, len_, i_)) {
                        operators_.put(i_,"..");
                    } else {
                        operators_.put(i_,".");
                    }
                }
            }
            i_++;
        }
        if (operators_.isEmpty()) {
            if (StringExpUtil.isTypeLeaf(_string)) {
                a_.setKind(KindPartType.TYPE_NAME);
                a_.setupValue(_string);
                return a_;
            }
        }
        a_.getOperators().putAllMap(operators_);
        a_.setPrio(prio_);
        a_.setupValues(_string);
        return a_;
    }

    public static AnalyzingType analyzeLocalExec(int _offset, String _string, Ints _indexes) {
        AnalyzingType a_ = new AnalyzingType();
        a_.getIndexes().addAllElts(_indexes);
        if (_string.trim().isEmpty()) {
            a_.getValues().put((int)CustList.FIRST_INDEX, _string);
            a_.setError(true);
            return a_;
        }
        if (StringExpUtil.isVar(_string)) {
            a_.setKind(KindPartType.VARIABLE);
            a_.setupValueExec(_string);
            return a_;
        }
        if (StringExpUtil.isTypeLeaf(_string)) {
            a_.setKind(KindPartType.TYPE_NAME);
            a_.setupValueExec(_string);
            return a_;
        }
        if (StringList.quickEq(_string.trim(), Templates.SUB_TYPE)) {
            a_.setKind(KindPartType.EMPTY_WILD_CARD);
            a_.setupValueExec(_string);
            return a_;
        }
        if (_string.trim().startsWith(Templates.SUB_TYPE)) {
            a_.setPrio(WILD_CARD_PRIO);
            a_.setupWildCardValues(Templates.SUB_TYPE, _string);
            return a_;
        }
        if (_string.trim().startsWith(Templates.SUP_TYPE)) {
            if (StringList.quickEq(_string.trim(), Templates.SUP_TYPE)) {
                a_.setError(true);
            }
            a_.setPrio(WILD_CARD_PRIO);
            a_.setupWildCardValues(Templates.SUP_TYPE, _string);
            return a_;
        }
        if (tryGetArray(a_, _string)) {
            return a_;
        }
        if (_string.trim().startsWith(Templates.ARR_BEG_STRING)) {
            a_.setPrio(ARR_PRIO);
            a_.setupArrayValuesExec(_string);
            return a_;
        }
        int count_ = 0;
        int len_ = _string.length();
        int i_ = 0;
        int prio_ = TMP_PRIO;
        IntTreeMap<String> operators_;
        operators_ = new IntTreeMap<String>();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (!_indexes.containsObj((long)i_+_offset)) {
                i_++;
                continue;
            }
            if (curChar_ == Templates.LT) {
                if (count_== 0 && prio_ == TMP_PRIO) {
                    operators_.clear();
                    operators_.put(i_,Templates.TEMPLATE_BEGIN);
                }
                count_++;
            }
            if (curChar_ == Templates.COMMA && count_ == 1 && prio_ == TMP_PRIO) {
                operators_.put(i_, Templates.TEMPLATE_SEP);
            }
            if (curChar_ == Templates.GT) {
                count_--;
                if (count_ == 0 && prio_ == TMP_PRIO) {
                    operators_.put(i_,Templates.TEMPLATE_END);
                }
            }
            if (count_ == 0) {
                if (curChar_ == Templates.SEP_CLASS_CHAR || curChar_ == '-') {
                    if (prio_ > INT_PRIO) {
                        operators_.clear();
                        prio_ = INT_PRIO;
                    }
                    if (curChar_ == Templates.SEP_CLASS_CHAR){
                        operators_.put(i_,Templates.INNER_TYPE);
                    } else {
                        operators_.put(i_,"-");
                    }
                }
            }
            i_++;
        }
        a_.getOperators().putAllMap(operators_);
        a_.setPrio(prio_);
        a_.setupValuesExec(_string);
        return a_;
    }
    private static boolean tryGetArray(AnalyzingType _a,String _string) {
        int j_ = _string.length()-1;
        boolean arr_ = true;
        while (true) {
            char locChar_ = _string.charAt(j_);
            if (Character.isWhitespace(locChar_)) {
                j_--;
                continue;
            }
            if (locChar_ != ']') {
                arr_ = false;
            }
            break;
        }
        if (arr_) {
            j_--;
            while (j_ >= 0) {
                char locChar_ = _string.charAt(j_);
                if (Character.isWhitespace(locChar_)) {
                    j_--;
                    continue;
                }
                if (locChar_ != '[') {
                    arr_ = false;
                }
                break;
            }
        }
        if (arr_) {
            if (j_ >= 0) {
                _a.setPrio(ARR_PRIO);
                int last_ = StringList.getLastPrintableCharIndex(_string.substring(0, j_));
                if (last_ < 0) {
                    _a.getValues().put((int)CustList.FIRST_INDEX, _string);
                    _a.setError(true);
                    return true;
                }
                String str_ = _string.substring(0, j_);
                _a.getValues().put((int)CustList.FIRST_INDEX, str_);
                _a.getOperators().put(last_, "[]");
                return true;
            }
            _a.getValues().put((int)CustList.FIRST_INDEX, _string);
            _a.setError(true);
            return true;
        }
        return false;
    }

}
