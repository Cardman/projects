package code.expressionlanguage.types;

import code.expressionlanguage.Templates;
import code.expressionlanguage.options.Options;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;

public final class ParserType {

    static final int WILD_CARD_PRIO = 1;
    static final int ARR_PRIO = 2;
    static final int INT_PRIO = 3;
    static final int TMP_PRIO = 4;
    public static Numbers<Integer> getIndexes(String _input, Options _options) {
        int count_ = 0;
        int len_ = _input.length();
        int i_ = 0;
        Numbers<Integer> indexes_ = new Numbers<Integer>();
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
                if (i_ + 1 < len_ && _input.charAt(i_ + 1) == Templates.SEP_CLASS_CHAR) {
                    indexes_.add(i_);
                    i_++;
                }
            }
            i_++;
        }
        if (count_ > 0) {
            return null;
        }
        return indexes_;
    }
    public static Numbers<Integer> getIndexesExec(String _input) {
        int count_ = 0;
        int len_ = _input.length();
        int i_ = 0;
        Numbers<Integer> indexes_ = new Numbers<Integer>();
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
                if (i_ + 1 < len_ && _input.charAt(i_ + 1) == Templates.SEP_CLASS_CHAR) {
                    indexes_.add(i_);
                    i_++;
                }
            }
            i_++;
        }
        if (count_ > 0) {
            return null;
        }
        return indexes_;
    }
    public static AnalyzingType analyzeLocal(int _offset, String _string, Numbers<Integer> _indexes, Options _options) {
        AnalyzingType a_ = new AnalyzingType();
        a_.getIndexes().addAllElts(_indexes);
        if (_string.trim().isEmpty()) {
            a_.getValues().put((int)CustList.FIRST_INDEX, _string);
            a_.setError(true);
            return a_;
        }
        if (isVar(_string)) {
            a_.setKind(KindPartType.VARIABLE);
            a_.setupValue(_string, _offset, _options);
            return a_;
        }
        if (isTypeLeaf(_string)) {
            a_.setKind(KindPartType.TYPE_NAME);
            a_.setupValue(_string, _offset, _options);
            return a_;
        }
        if (StringList.quickEq(_string.trim(), Templates.SUB_TYPE)) {
            a_.setKind(KindPartType.EMPTY_WILD_CARD);
            a_.setupValue(_string, _offset, _options);
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
        int j_ = _string.length()-1;
        boolean arr_ = true;
        while (j_ >= 0) {
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
                a_.setPrio(ARR_PRIO);
                int last_ = StringList.getLastPrintableCharIndex(_string.substring(0, j_));
                if (last_ < 0) {
                    a_.getValues().put((int)CustList.FIRST_INDEX, _string);
                    a_.setError(true);
                    return a_;
                }
                String str_ = _string.substring(0, j_);
                a_.getValues().put((int)CustList.FIRST_INDEX, str_);
                a_.getOperators().put(last_, "[]");
                return a_;
            }
            a_.getValues().put((int)CustList.FIRST_INDEX, _string);
            a_.setError(true);
            return a_;
        }
        int count_ = 0;
        int len_ = _string.length();
        int i_ = 0;
        int prio_ = TMP_PRIO;
        NatTreeMap<Integer,String> operators_;
        operators_ = new NatTreeMap<Integer,String>();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (!_indexes.containsObj(i_+_offset)) {
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
            if (curChar_ == Templates.SEP_CLASS_CHAR && count_ == 0) {
                if (prio_ > INT_PRIO) {
                    operators_.clear();
                    prio_ = INT_PRIO;
                }
                operators_.put(i_,Templates.INNER_TYPE);
            }
            i_++;
        }
        a_.getOperators().putAllMap(operators_);
        a_.setPrio(prio_);
        a_.setupValues(_string, _options);
        return a_;
    }
    public static AnalyzingType analyzeLocalExec(int _offset, String _string, Numbers<Integer> _indexes) {
        AnalyzingType a_ = new AnalyzingType();
        a_.getIndexes().addAllElts(_indexes);
        if (_string.trim().isEmpty()) {
            a_.getValues().put((int)CustList.FIRST_INDEX, _string);
            a_.setError(true);
            return a_;
        }
        if (isVar(_string)) {
            a_.setKind(KindPartType.VARIABLE);
            a_.setupValueExec(_string, _offset);
            return a_;
        }
        if (isTypeLeaf(_string)) {
            a_.setKind(KindPartType.TYPE_NAME);
            a_.setupValueExec(_string, _offset);
            return a_;
        }
        if (StringList.quickEq(_string.trim(), Templates.SUB_TYPE)) {
            a_.setKind(KindPartType.EMPTY_WILD_CARD);
            a_.setupValueExec(_string, _offset);
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
        int j_ = _string.length()-1;
        boolean arr_ = true;
        while (j_ >= 0) {
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
                a_.setPrio(ARR_PRIO);
                int last_ = StringList.getLastPrintableCharIndex(_string.substring(0, j_));
                if (last_ < 0) {
                    a_.getValues().put((int)CustList.FIRST_INDEX, _string);
                    a_.setError(true);
                    return a_;
                }
                String str_ = _string.substring(0, j_);
                a_.getValues().put((int)CustList.FIRST_INDEX, str_);
                a_.getOperators().put(last_, "[]");
                return a_;
            }
            a_.getValues().put((int)CustList.FIRST_INDEX, _string);
            a_.setError(true);
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
        NatTreeMap<Integer,String> operators_;
        operators_ = new NatTreeMap<Integer,String>();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (!_indexes.containsObj(i_+_offset)) {
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
            if (curChar_ == Templates.SEP_CLASS_CHAR && count_ == 0) {
                if (prio_ > INT_PRIO) {
                    operators_.clear();
                    prio_ = INT_PRIO;
                }
                operators_.put(i_,Templates.INNER_TYPE);
            }
            i_++;
        }
        a_.getOperators().putAllMap(operators_);
        a_.setPrio(prio_);
        a_.setupValuesExec(_string);
        return a_;
    }
    private static boolean isVar(String _string) {
        String tr_ = _string.trim();
        if (!tr_.startsWith(Templates.PREFIX_VAR_TYPE)) {
            return false;
        }
        tr_ = tr_.substring(Templates.PREFIX_VAR_TYPE.length());
        return isTypeLeaf(tr_);
    }
    private static boolean isTypeLeaf(String _string) {
        if (_string.trim().isEmpty()) {
            return false;
        }
        for (String p : StringList.splitChars(_string, Templates.SEP_CLASS_CHAR)) {
            if (!isTypeLeafPart(p.trim())) {
                return false;
            }
        }
        return true;
    }
    private static boolean isTypeLeafPart(String _string) {
        if (_string.trim().isEmpty()) {
            return false;
        }
        for (char c: _string.toCharArray()) {
            if (StringList.isDollarWordChar(c)) {
                continue;
            }
            if (c == Templates.PREFIX_VAR_TYPE_CHAR) {
                continue;
            }
            return false;
        }
        return true;
    }
}
