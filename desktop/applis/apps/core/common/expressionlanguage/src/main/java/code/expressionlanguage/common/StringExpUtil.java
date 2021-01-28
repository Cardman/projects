package code.expressionlanguage.common;

import code.expressionlanguage.inherits.MappingPairs;
import code.expressionlanguage.inherits.Matching;
import code.expressionlanguage.inherits.MatchingEnum;

import code.expressionlanguage.inherits.Templates;
import code.maths.litteral.MathExpUtil;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class StringExpUtil {
    public static final String ARR_CLASS = "[";
    public static final char ARR_BEG = '[';
    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char SEP_CLASS_CHAR = '.';
    public static final char SUB_TYPE_CHAR = '?';
    public static final char SUP_TYPE_CHAR = '!';
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";
    public static final char PREFIX_VAR_TYPE_CHAR = '#';
    public static final String INNER_TYPE = "..";

    public static final char LT = '<';

    public static final char GT = '>';

    public static final char COMMA = ',';

    public static final int LETTER_SENS_LOWER_CASE = 1;
    public static final int LETTER_SENS_UPPER_CASE = 2;
    public static final int LETTER_INSENS_LOWER_CASE = 3;
    public static final int LETTER_INSENS_UPPER_CASE = 4;
    public static final int LETTER_SENS_NO_CASE = 5;
    public static final int LETTER_SEMI_SENS_NO_CASE = 6;
    public static final int LETTER_INSENS_NO_CASE_DEF_DIR = 7;
    public static final int LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS = 8;
    public static final int LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT = 9;
    public static final int DIGIT = 10;
    public static final int DIGIT_OTHER = 11;
    public static final int ID_SEP= 12;
    public static final int CURRENCY= 13;
    public static final int SENS_OTHER_LETTER= 14;
    public static final int ROMAN_DIGIT = 15;
    public static final int DEL_LEFT = 16;
    public static final int DEL_RIGHT = 17;
    public static final int OPERATOR_LANGUAGE = 18;
    public static final int OPERATOR_SPEC = 19;
    public static final int PUNCTUATION = 20;
    public static final int QUOTES = 21;
    public static final int ESCAPE = 22;
    public static final int MATH = 23;
    public static final int MODIFIER = 24;
    public static final int SYMBOL_MODIFIER = 25;
    public static final int SYMBOL_OTHER = 26;
    public static final int PUNCTUATION_CONNECTOR = 27;
    public static final int PUNCTUATION_BOUND = 28;
    public static final int PUNCTUATION_QUOTE = 29;
    public static final int PUNCTUATION_OTHER = 30;
    public static final int LETTERS_DIGITS_OTHER = 31;
    public static final int SEPARATOR = 32;
    public static final int SPACE = 33;
    public static final int SPACE_OTHER = 34;
    public static final int OTHER = 35;
    public static final int UNASSIGNED = 36;
    private StringExpUtil() {
    }

    /** Splits by comma the input string into parts<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "int,long" => ["int","long"]<br/>
     Sample 3: "Pair&lt;int,long&gt;" => ["Pair&lt;int,long&gt;"]<br/>
     Sample 4: "Solo&lt;int&gt;,&lt;,List&lt;long&gt;" => ["Solo&lt;int&gt;","&lt;","List&lt;long&gt;"]<br/>
     */
    public static StringList getAllSepCommaTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        int count_ = 0;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (count_ > 0) {
                if (curChar_ == LT) {
                    count_++;
                }
                if (curChar_ == GT) {
                    count_--;
                }
                out_.append(curChar_);
                i_++;
                continue;
            }
            if (curChar_ == LT) {
                out_.append(curChar_);
                if (out_.toString().trim().charAt(0) != LT) {
                    count_++;
                }
                i_++;
                continue;
            }
            if (curChar_ == COMMA) {
                types_.add(out_.toString());
                out_.delete(0, out_.length());
            } else {
                out_.append(curChar_);
            }
            i_++;
        }
        types_.add(out_.toString());
        return types_;
    }

    public static boolean isWildCard(String _type) {
        return !getWildCards(_type).isEmpty();
    }
    public static StringList getWildCards(String _type) {
        StringList list_ = new StringList();
        for (String p: getAllTypes(_type).mid(1)) {
            if (p.startsWith(Templates.SUB_TYPE)) {
                list_.add(p);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                list_.add(p);
            }
        }
        return list_;
    }
    /** Returns the id of a type<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "Pair&lt;int,long&gt;" => ["Pair"]<br/>
     Sample 3: "Solo&lt;Pair&lt;int,long&gt;&gt;" => ["Solo"]<br/>
     */
    public static String getIdFromAllTypes(String _type) {
        return getAllTypes(_type).first();
    }
    /** Splits by comma the input string into parts<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "Pair&lt;int,long&gt;" => ["Pair","int","long"]<br/>
     Sample 3: "Solo&lt;Pair&lt;int,long&gt;&gt;" => ["Solo","Pair&lt;int,long&gt;"]<br/>
     */
    public static StringList getAllTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        StringBuilder id_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        int count_ = 0;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (count_ > 0) {
                if (curChar_ == LT) {
                    count_++;
                }
                if (curChar_ == GT) {
                    count_--;
                }
                if (count_ == 1 && curChar_ == COMMA) {
                    types_.add(out_.toString());
                    out_.delete(0, out_.length());
                    i_++;
                    continue;
                }
                if (count_ == 0) {
                    //curChar_ == Templates.GT
                    types_.add(out_.toString());
                    out_.delete(0, out_.length());
                    i_++;
                    continue;
                }
                out_.append(curChar_);
                i_++;
                continue;
            }
            if (curChar_ == LT) {
                count_++;
                i_++;
                continue;
            }
            id_.append(curChar_);
            i_++;
        }
        types_.add(0, id_.toString());
        return types_;
    }

    public static StringList getAllPartInnerTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        int count_ = 0;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (count_ > 0) {
                if (curChar_ == LT) {
                    count_++;
                }
                if (curChar_ == GT) {
                    count_--;
                }
                out_.append(curChar_);
                i_++;
                continue;
            }
            if (curChar_ == LT) {
                out_.append(curChar_);
                count_++;
                i_++;
                continue;
            }
            if (curChar_ == '-') {
                types_.add(out_.toString());
                types_.add("-");
                out_.delete(0, out_.length());
                i_++;
                continue;
            }
            if (_type.startsWith(INNER_TYPE,i_)) {
                types_.add(out_.toString());
                types_.add(INNER_TYPE);
                out_.delete(0, out_.length());
                i_++;
            } else {
                out_.append(curChar_);
            }
            i_++;
        }
        types_.add(out_.toString());
        return types_;
    }

    public static String getWildCardFormattedTypeReturn(String _type, StringMap<String> _varTypes) {
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            char ch_ = _type.charAt(i);
            if (ch_ == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(ch_);
                continue;
            }
            if (isDollarWordChar(ch_)) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            String value_ = _varTypes.getVal(sub_);
            if (value_ != null) {
                int max_ = str_.length() -1;
                int j_ = getMaxIndex(str_, max_);
                if (StringUtil.quickEq(value_, SUB_TYPE)) {
                    if (isSubOrSubChar(str_, j_)) {
                        j_--;
                    }
                    str_.delete(j_+1, max_+1);
                    str_.append(SUB_TYPE);
                    str_.append(ch_);
                    var_ = false;
                    continue;

                }
                if (value_.startsWith(SUB_TYPE)) {
                    String bound_= value_.substring(SUB_TYPE.length());
                    if (isSupChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(SUB_TYPE);
                        str_.append(ch_);
                        var_ = false;
                        continue;
                    }
                    if (isNotChar(str_, j_, SUB_TYPE_CHAR)) {
                        str_.insert(j_ +1, SUB_TYPE);
                    }
                    str_.append(bound_);
                    str_.append(ch_);
                    var_ = false;
                    continue;

                }
                if (value_.startsWith(SUP_TYPE)) {
                    String bound_= value_.substring(SUP_TYPE.length());
                    if (isSubChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(SUB_TYPE);
                        str_.append(ch_);
                        var_ = false;
                        continue;
                    }
                    if (isNotChar(str_, j_, SUP_TYPE_CHAR)) {
                        str_.insert(j_ +1, SUP_TYPE);
                    }
                    str_.append(bound_);
                    str_.append(ch_);
                    var_ = false;
                    continue;

                }
                str_.append(value_);
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(ch_);
            var_ = false;
        }
        return str_.toString();
    }

    public static String getQuickFormattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            char ch_ = _type.charAt(i);
            if (ch_ == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(ch_);
                continue;
            }
            if (isDollarWordChar(ch_)) {
                continue;
            }
            quickReplaceType(str_,_type,_varTypes,diese_,i);
            str_.append(ch_);
            var_ = false;
        }
        if (var_) {
            quickReplaceType(str_,_type,_varTypes,diese_,len_);
        }
        return str_.toString();
    }
    private static void quickReplaceType(StringBuilder _str, String _type, StringMap<String> _varTypes, int _diese, int _max) {
        String sub_ = _type.substring(_diese+1, _max);
        String value_ = _varTypes.getVal(sub_);
        if (value_ == null) {
            _str.append(sub_);
            return;
        }
        _str.append(value_);
    }

    public static String getQuickFormattedTypeKeep(String _type, StringMap<String> _varTypes) {
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            char ch_ = _type.charAt(i);
            if (ch_ == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(ch_);
                continue;
            }
            if (isDollarWordChar(ch_)) {
                continue;
            }
            quickReplaceTypeKeep(str_,_type,_varTypes,diese_,i);
            str_.append(ch_);
            var_ = false;
        }
        if (var_) {
            quickReplaceTypeKeep(str_,_type,_varTypes,diese_,len_);
        }
        return str_.toString();
    }
    private static void quickReplaceTypeKeep(StringBuilder _str, String _type, StringMap<String> _varTypes, int _diese, int _max) {
        String sub_ = _type.substring(_diese+1, _max);
        String value_ = _varTypes.getVal(sub_);
        if (value_ == null) {
            _str.append('#');
            _str.append(sub_);
            return;
        }
        _str.append(value_);
    }
    public static String getFormattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            char ch_ = _type.charAt(i);
            if (ch_ == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(ch_);
                continue;
            }
            if (isDollarWordChar(ch_)) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            String val_ = _varTypes.getVal(sub_);
            if (val_ != null) {
                tryReplaceType(str_, val_);
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(ch_);
            var_ = false;
        }
        if (var_) {
            String sub_ = _type.substring(diese_+1);
            String val_ = _varTypes.getVal(sub_);
            if (val_ != null) {
                tryReplaceType(str_, val_);
            } else {
                sub_ = _type.substring(diese_);
                str_.append(sub_);
            }
        }
        return str_.toString();
    }
    private static void tryReplaceType(StringBuilder _str, String _value) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        if (_value.startsWith(SUB_TYPE)) {
            if (isSubOrSubChar(_str,j_)) {
                _str.append(_value.substring(SUB_TYPE.length()));
            } else {
                _str.insert(j_ +1, SUB_TYPE);
                _str.append(_value.substring(SUB_TYPE.length()));
            }
        } else if (_value.startsWith(SUP_TYPE)) {
            if (isSubOrSubChar(_str,j_)) {
                _str.append(_value.substring(SUP_TYPE.length()));
            } else {
                _str.insert(j_ + 1, SUP_TYPE);
                _str.append(_value.substring(SUP_TYPE.length()));
            }
        } else {
            _str.append(_value);
        }
    }
    public static String getReflectFormattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            char ch_ = _type.charAt(i);
            if (ch_ == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(ch_);
                continue;
            }
            if (isDollarWordChar(ch_)) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            String value_ = _varTypes.getVal(sub_);
            if (value_ != null) {
                replaceReflectedType(str_, value_);
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(ch_);
            var_ = false;
        }
        if (var_) {
            String sub_ = _type.substring(diese_+1);
            String value_ = _varTypes.getVal(sub_);
            if (value_ != null) {
                replaceReflectedType(str_, value_);
            } else {
                sub_ = _type.substring(diese_);
                str_.append(sub_);
            }
        }
        return str_.toString();
    }

    private static void replaceReflectedType(StringBuilder _str, String _value) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        if (StringUtil.quickEq(_value,SUB_TYPE)) {
            _str.delete(j_+1,_str.length());
            _str.append(_value);
        } else if (_value.startsWith(SUB_TYPE)) {
            if (isNotChar(_str,j_,SUB_TYPE_CHAR) && isNotChar(_str,j_,SUP_TYPE_CHAR)) {
                _str.insert(j_ +1, SUB_TYPE);
            }
            _str.append(_value.substring(SUB_TYPE.length()));
        } else if (_value.startsWith(SUP_TYPE)) {
            if (isNotChar(_str,j_,SUB_TYPE_CHAR) && isNotChar(_str,j_,SUP_TYPE_CHAR)) {
                _str.insert(j_ +1, SUP_TYPE);
            }
            _str.append(_value.substring(SUP_TYPE.length()));
        } else {
            _str.append(_value);
        }
    }

    public static String getWildCardFormattedTypeParam(String _objType, String _type, StringMap<String> _varTypes) {
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            char ch_ = _type.charAt(i);
            if (ch_ == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(ch_);
                continue;
            }
            if (isDollarWordChar(ch_)) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            String value_ = _varTypes.getVal(sub_);
            if (value_ != null) {
                int max_ = str_.length() -1;
                int j_ = getMaxIndex(str_, max_);
                if (StringUtil.quickEq(value_, SUB_TYPE)) {
                    if (isSupChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(_objType);
                        str_.append(ch_);
                        var_ = false;
                        continue;
                    }
                    return "";
                }
                if (value_.startsWith(SUB_TYPE)) {
                    String bound_= value_.substring(SUB_TYPE.length());
                    if (isSupChar(str_, j_)) {
                        str_.append(bound_);
                        str_.append(ch_);
                        var_ = false;
                        continue;
                    }
                    return "";
                }
                if (value_.startsWith(SUP_TYPE)) {
                    String bound_= value_.substring(SUP_TYPE.length());
                    if (isSubChar(str_, j_)) {
                        str_.append(bound_);
                        str_.append(ch_);
                        var_ = false;
                        continue;
                    }
                    if (isSupChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(_objType);
                        str_.append(ch_);
                        var_ = false;
                        continue;
                    }
                    return "";
                }
                str_.append(value_);
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(ch_);
            var_ = false;
        }
        return str_.toString();
    }

    private static int getMaxIndex(StringBuilder _str, int _max) {
        int j_ = _max;
        while (j_ >= 0) {
            if (_str.charAt(j_) != ARR_BEG) {
                break;
            }
            j_--;
        }
        return j_;
    }

    private static boolean isNotChar(StringBuilder _str, int _j, char _subTypeChar) {
        return _j >= 0 && _str.charAt(_j) != _subTypeChar;
    }

    private static boolean isSubOrSubChar(StringBuilder _str, int _j) {
        return isSubChar(_str,_j) || isSupChar(_str, _j);
    }

    private static boolean isSubChar(StringBuilder _str, int _j) {
        return _j >= 0 && _str.charAt(_j) == SUB_TYPE_CHAR;
    }

    private static boolean isSupChar(StringBuilder _str, int _j) {
        return _j >= 0 && _str.charAt(_j) == SUP_TYPE_CHAR;
    }

    /** Splits by double dots the input string into parts<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "int..long" => ["int","long"]<br/>
     Sample 3: "Pair&lt;int..long&gt;" => ["Pair&lt;int..long&gt;"]<br/>
     */
    public static StringList getAllInnerTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        int count_ = 0;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (count_ > 0) {
                if (curChar_ == LT) {
                    count_++;
                }
                if (curChar_ == GT) {
                    count_--;
                }
                out_.append(curChar_);
                i_++;
                continue;
            }
            if (curChar_ == LT) {
                out_.append(curChar_);
                count_++;
                i_++;
                continue;
            }
            if (curChar_ == '-') {
                types_.add(out_.toString());
                out_.delete(0, out_.length());
                i_++;
                continue;
            }
            if (_type.startsWith(INNER_TYPE,i_)) {
                types_.add(out_.toString());
                out_.delete(0, out_.length());
                i_++;
            } else {
                out_.append(curChar_);
            }
            i_++;
        }
        types_.add(out_.toString());
        return types_;
    }
    public static MappingPairs getMappingFctPairs(DimComp _dArg, DimComp _dParam, String _baseArrayParam, String _aliasObject) {
        if (StringUtil.quickEq(_baseArrayParam, _aliasObject)) {
            int dim_ = _dArg.getDim();
            if (dim_ >= _dParam.getDim()) {
                return new MappingPairs();
            }
        }
        return null;
    }

    /**arg - param*/
    public static MappingPairs newMappingPairsFct(StringList _args, StringList _params, String _objectType) {
        int len_ = _params.size();
        if (_args.size() != len_) {
            return null;
        }
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
        int argCall_ = len_ - 1;
        for (int i = IndexConstants.SECOND_INDEX; i < len_; i++) {
            String arg_ = _args.get(i);
            String param_ = _params.get(i);
            if (param_.startsWith("~")) {
                if (arg_.startsWith("~")) {
                    Matching match_ = new Matching();
                    match_.setArg(arg_.substring(1));
                    match_.setParam(param_.substring(1));
                    match_.setMatchEq(MatchingEnum.EQ);
                    pairsArgParam_.add(match_);
                    continue;
                }
                return null;
            }
            if (arg_.startsWith("~")) {
                if (StringUtil.quickEq(param_, SUB_TYPE)) {
                    continue;
                }
                return null;
            }
            if (i < argCall_) {
                if (StringUtil.quickEq(arg_, SUB_TYPE)) {
                    if (StringUtil.quickEq(param_, SUB_TYPE)) {
                        continue;
                    }
                    return null;
                }
                if (StringUtil.quickEq(param_, SUB_TYPE)) {
                    continue;
                }
                Matching match_ = new Matching();
                match_.setArg(arg_);
                match_.setParam(param_);
                match_.setMatchEq(MatchingEnum.SUP);
                pairsArgParam_.add(match_);
                continue;
            }
            boolean add_ = true;
            if (StringUtil.quickEq(arg_, SUB_TYPE)) {
                if (!StringUtil.quickEq(param_, SUB_TYPE)) {
                    return null;
                }
                add_ = false;
            }
            if (StringUtil.quickEq(param_, SUB_TYPE)) {
                add_ = false;
            }
            if (!StringUtil.quickEq(param_, _objectType) && add_) {
                Matching match_ = new Matching();
                match_.setArg(arg_);
                match_.setParam(param_);
                match_.setMatchEq(MatchingEnum.SUB);
                pairsArgParam_.add(match_);
            }
        }
        MappingPairs m_ = new MappingPairs();
        m_.setPairsArgParam(pairsArgParam_);
        return m_;
    }
    /**arg - param*/
    public static MappingPairs newMappingPairs(String _generic, StringList _params) {
        StringList foundSuperClass_ = getAllTypes(_generic);
        int len_ = foundSuperClass_.size();
        if (_params.size() != len_) {
            return null;
        }
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
        for (int i = IndexConstants.SECOND_INDEX; i < len_; i++) {
            Matching match_ = new Matching();
            String arg_ = foundSuperClass_.get(i);
            String param_ = _params.get(i);
            if (StringUtil.quickEq(arg_, SUB_TYPE)) {
                if (StringUtil.quickEq(param_, SUB_TYPE)) {
                    continue;
                }
                return null;
            }
            if (StringUtil.quickEq(param_, SUB_TYPE)) {
                continue;
            }
            if (!isBound(param_)) {
                if (isBound(arg_)) {
                    return null;
                }
                match_.setArg(arg_);
                match_.setParam(param_);
                pairsArgParam_.add(match_);
                continue;
            }
            if (!isBound(arg_)) {
                if (param_.startsWith(SUP_TYPE)) {
                    match_.setArg(arg_);
                    match_.setParam(param_.substring(SUP_TYPE.length()));
                    match_.setMatchEq(MatchingEnum.SUP);
                    pairsArgParam_.add(match_);
                    continue;
                }
                match_.setArg(arg_);
                match_.setParam(param_.substring(SUB_TYPE.length()));
                match_.setMatchEq(MatchingEnum.SUB);
                pairsArgParam_.add(match_);
                continue;
            }
            if (arg_.startsWith(SUP_TYPE)) {
                if (param_.startsWith(SUB_TYPE)) {
                    return null;
                }
                match_.setArg(arg_.substring(SUP_TYPE.length()));
                match_.setParam(param_.substring(SUP_TYPE.length()));
                match_.setMatchEq(MatchingEnum.SUP);
                pairsArgParam_.add(match_);
                continue;
            }
            if (param_.startsWith(SUP_TYPE)) {
                return null;
            }
            match_.setArg(arg_.substring(SUB_TYPE.length()));
            match_.setParam(param_.substring(SUB_TYPE.length()));
            match_.setMatchEq(MatchingEnum.SUB);
            pairsArgParam_.add(match_);
        }
        MappingPairs m_ = new MappingPairs();
        m_.setPairsArgParam(pairsArgParam_);
        return m_;
    }

    private static boolean isBound(String _type) {
        return _type.startsWith(SUP_TYPE) || _type.startsWith(SUB_TYPE);
    }

    public static boolean nextCharIs(String _str, int _i, int _len, char _value) {
        if (_i < 0) {
            return false;
        }
        if (_i >= Math.min(_len,_str.length())) {
            return false;
        }
        return _str.charAt(_i) == _value;
    }

    public static int nextPrintCharIs(int _j, int _len, String _string, char _ch) {
        int j_ = nextPrintChar(_j,_len,_string);
        if (j_ < 0) {
            return -1;
        }
        if (_string.charAt(j_) != _ch) {
            return -1;
        }
        return j_;
    }

    public static boolean isDigit(char _char) {
        return _char >= '0' && _char <= '9';
    }

    public static int getBackPrintChar(String _string, int _i) {
        int bk_ = _i - 1;
        while (bk_ >= 0) {
            if (!StringUtil.isWhitespace(_string.charAt(bk_))) {
                break;
            }
            bk_--;
        }
        return bk_;
    }

    public static int nextPrintChar(int _j, int _len, String _string) {
        if (_j < 0) {
            return _j;
        }
        int j_ = _j;
        int len_ = Math.min(_len,_string.length());
        while (j_ < len_) {
            char ch_ = _string.charAt(j_);
            if (!StringUtil.isWhitespace(ch_)) {
                break;
            }
            j_++;
        }
        if (j_ >= len_) {
            return -1;
        }
        return j_;
    }
    public static boolean isOper(String _op) {
        if(StringUtil.quickEq(_op, "+")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "-")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "!")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "*")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "/")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "%")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "==")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "!=")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "<=")) {
            return true;
        }
        if(StringUtil.quickEq(_op, ">")) {
            return true;
        }
        if(StringUtil.quickEq(_op, ">=")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "<")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "&")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "|")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "&&")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "||")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "^")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "~")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "<<")) {
            return true;
        }
        if(StringUtil.quickEq(_op, ">>")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "<<<")) {
            return true;
        }
        if(StringUtil.quickEq(_op, ">>>")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "<<<<")) {
            return true;
        }
        if(StringUtil.quickEq(_op, ">>>>")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "++")) {
            return true;
        }
        return StringUtil.quickEq(_op, "--");
    }
    public static boolean startsWithArobaseKeyWord(String _found, String _keyWord) {
        return startsWithArobaseKeyWord(_found,0,_keyWord);
    }

    public static boolean startsWithArobaseKeyWord(String _found, int _start, String _keyWord) {
        if (!_found.startsWith("@",_start)) {
            return false;
        }
        return startsWithKeyWord(_found,_start+1,_keyWord);
    }
    public static boolean startsWithKeyWord(String _found, String _keyWord) {
        return startsWithKeyWord(_found,0,_keyWord);
    }

    public static boolean startsWithKeyWord(String _found, int _start, String _keyWord) {
        if (!_found.startsWith(_keyWord,_start)) {
            return false;
        }
        int offset_ = _keyWord.length() + _start;
        if (offset_ >= _found.length()) {
            return true;
        }
        char first_ = _found.charAt(offset_);
        return !StringExpUtil.isTypeLeafChar(first_);
    }

    public static boolean isTypeLeafExec(String _string) {
        for (String p : StringUtil.splitChars(_string, SEP_CLASS_CHAR)) {
            if (!isTypeLeafPartExec(p.trim())) {
                return false;
            }
        }
        return true;
    }
    public static boolean isTypeLeafPart(String _string) {
        if (_string.trim().isEmpty()) {
            return false;
        }
        for (char c: _string.toCharArray()) {
            if (!isTypeLeafChar(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isTypeLeafPartExec(String _string) {
        if (_string.trim().isEmpty()) {
            return false;
        }
        for (char c: _string.toCharArray()) {
            if (c == '*') {
                continue;
            }
            if (c == '+') {
                continue;
            }
            if (!isTypeLeafChar(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isTypeLeafChar(char _ch) {
        if (isDollarWordChar(_ch)) {
            return true;
        }
        return _ch == PREFIX_VAR_TYPE_CHAR;
    }

    public static String removeDottedSpaces(String _type) {
        StringBuilder b_ = new StringBuilder();
        for (String q: StringUtil.splitCharSep(_type, SEP_CLASS_CHAR)) {
            b_.append(q.trim());
        }
        return b_.toString();
    }

    public static String getPrettyArrayType(String _className, int _nb) {
        String cl_ = _className;
        for (int i = IndexConstants.FIRST_INDEX; i < _nb; i++) {
            cl_ = getPrettyArrayType(cl_);
        }
        return cl_;
    }
    public static String getPrettyArrayType(String _className) {
        return StringUtil.concat(ARR_CLASS,_className);
    }
    public static String getQuickComponentBase(String _className) {
        return getQuickComponentBaseType(_className).getComponent();
    }
    /**Custom classes*/
    public static DimComp getQuickComponentBaseType(String _className) {
        int count_ = countPrefix(_className, ARR_BEG);
        return new DimComp(count_, _className.substring(count_));
    }

    public static String getQuickComponentType(String _className) {
        return getQuickComponentType(_className,1);
    }
    public static String getQuickComponentType(String _className, int _nb) {
        String className_ = _className;
        for (int i = 0; i < _nb; i++) {
            if (!className_.startsWith(ARR_CLASS)) {
                return null;
            }
            className_ = className_.substring(1);
        }
        return className_;
    }
    public static String skipPrefix(String _varRef) {
        int count_ = countPrefix(_varRef, '#');
        return _varRef.substring(count_);
    }

    public static int countPrefix(String _varRef, char _prefChar) {
        int count_ = 0;
        int len_ = _varRef.length();
        for (int i = 0; i < len_; i++) {
            if (_varRef.charAt(i) != _prefChar) {
                break;
            }
            count_++;
        }
        return count_;
    }

    public static boolean isIndexerOrInexist(String _nameLoc) {
        return _nameLoc.trim().isEmpty() || _nameLoc.startsWith("[]");
    }
    public static ExtractedParts tryToExtract(String _string, char _first, char _last) {
        int index_ = _string.indexOf(_first);
        if (index_ < 0) {
            return new ExtractedParts();
        }
        ExtractedParts e_ = new ExtractedParts();
        e_.setFirst(_string.substring(0,index_));
        int last_ = _string.lastIndexOf(_last);
        int afterIndex_ = index_ + 1;
        if (afterIndex_ > last_) {
            return e_;
        }
        e_.setSecond(_string.substring(afterIndex_,last_));
        return e_;
    }
    public static boolean noDel(String _str) {
        return _str.indexOf('(') < 0 && _str.indexOf('{') < 0 && _str.indexOf('[') < 0
                &&_str.indexOf(')') < 0 && _str.indexOf('}') < 0 && _str.indexOf(']') < 0;
    }
    public static int getOffset(String _str) {
        return Math.max(0, StringUtil.getFirstPrintableCharIndex(_str));
    }

    public static boolean commonCorrectType(String _genericClass, String _compo, String _fct, Ints _rep) {
        StringList inners_ = getAllInnerTypes(_genericClass);
        int len_ = inners_.size();
        if (!StringUtil.quickEq(_compo, _fct)) {
            if (len_ != _rep.size()) {
                return false;
            }
            for (int i = 0; i < len_; i++) {
                String i_ = inners_.get(i);
                int req_ = _rep.get(i);
                StringList params_ = getAllTypes(i_);
                int nbParams_ = params_.size() - 1;
                if (req_ != nbParams_) {
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayResult tryGetArray(String _string, StrTypes _values, StrTypes _operators) {
        int j_ = _string.length()-1;
        boolean arr_ = true;
        while (true) {
            char locChar_ = _string.charAt(j_);
            if (StringUtil.isWhitespace(locChar_)) {
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
                if (StringUtil.isWhitespace(locChar_)) {
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
                String str_ = _string.substring(0, j_);
                int last_ = StringUtil.getLastPrintableCharIndex(str_);
                if (last_ < 0) {
                    return ArrayResult.ERROR;
                }
                _values.addEntry(IndexConstants.FIRST_INDEX, str_);
                _operators.addEntry(last_, _string.substring(j_));
                return ArrayResult.OK;
            }
            return ArrayResult.ERROR;
        }
        return ArrayResult.NONE;
    }

    public static boolean customCast(String _type) {
        if (isWildCard(_type)) {
            return false;
        }
        if (_type.startsWith("#")) {
            return false;
        }
        return !_type.startsWith("[");
    }

    public static String toLongRadix(long _i, int _radix) {
        int radix_ = _radix;
        if (radix_ < 2 || radix_ > 36) {
            radix_ = 10;
        }
        if (_i == Long.MIN_VALUE) {
            int p_ = 1;
            int q_ = 0;
            while (p_ < radix_) {
                q_++;
                p_ = p_ * 2;
            }
            if (p_ == radix_) {
                int r_ = 63 % q_;
                int m_ = 63 / q_;
                int ch_ = 1;
                for (int i = 0; i < r_; i++) {
                    ch_ *= 2;
                }
                StringBuilder str_ = new StringBuilder("-");
                str_.append(ch_);
                for (int i = 0; i < m_; i++) {
                    str_.append(0);
                }
                return str_.toString();
            }
            StringBuilder str_ = toLongBase(-(_i+1), radix_);
            str_.insert(0,"-");
            int last_ = str_.length() - 1;
            str_.setCharAt(last_, (char) (str_.charAt(last_)+1));
            return str_.toString();
        }
        if (_i >= 0) {
            return toLongBase(_i, radix_).toString();
        }
        StringBuilder str_ = toLongBase(-_i, radix_);
        str_.insert(0,"-");
        return str_.toString();
    }

    public static String toLongGeneHex(long _i) {
        if (_i >= 0) {
            return toLongBase(_i, 16).toString();
        }
        StringBuilder str_ = toLongBase(-_i - 1, 16);
        int len_ = str_.length();
        int nbZeros_ = 16 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < 16; i++) {
            char c_ = str_.charAt(i);
            if (c_ >= '6' &&c_ <= '9') {
                int s_ = '6' + '9';
                str_.setCharAt(i,(char)(s_-c_));
                continue;
            }
            int s_ = 'a' + '5';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    public static String toLongGeneOct(long _i) {
        if (_i >= 0) {
            return toLongBase(_i, 8).toString();
        }
        StringBuilder str_ = toLongBase(-_i - 1, 8);
        int len_ = str_.length();
        int nbZeros_ = 22 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        char c_ = str_.charAt(0);
        int s_ = '0' + '1';
        str_.setCharAt(0,(char)(s_-c_));
        for (int i = 1; i < 22; i++) {
            c_ = str_.charAt(i);
            s_ = '0' + '7';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    public static String toLongGeneBin(long _i) {
        if (_i >= 0) {
            return toLongBase(_i, 2).toString();
        }
        StringBuilder str_ = toLongBase(-_i - 1, 2);
        int len_ = str_.length();
        int nbZeros_ = 64 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < 64; i++) {
            char c_ = str_.charAt(i);
            int s_ = '0' + '1';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    private static StringBuilder toLongBase(long _i, int _base) {
        StringBuilder str_ = new StringBuilder();
        if (_i == 0) {
            str_.append(0);
            return str_;
        }
        long q_ = _i;
        while (q_ > 0) {
            int r_ = (int)(q_ % _base);
            str_.insert(0, toSingleChar(r_));
            q_ = q_ / _base;
        }
        return str_;
    }
    public static String toGeneHex(int _i) {
        if (_i >= 0) {
            return toBase(_i, 16).toString();
        }
        StringBuilder str_ = toBase(-_i - 1, 16);
        int len_ = str_.length();
        int nbZeros_ = 8 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < 8; i++) {
            char c_ = str_.charAt(i);
            if (c_ >= '6' &&c_ <= '9') {
                int s_ = '6' + '9';
                str_.setCharAt(i,(char)(s_-c_));
                continue;
            }
            int s_ = 'a' + '5';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    public static String toGeneOct(int _i) {
        if (_i >= 0) {
            return toBase(_i, 8).toString();
        }
        StringBuilder str_ = toBase(-_i - 1, 8);
        int len_ = str_.length();
        int nbZeros_ = 11 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        char c_ = str_.charAt(0);
        int s_ = '0' + '3';
        str_.setCharAt(0,(char)(s_-c_));
        for (int i = 1; i < 11; i++) {
            c_ = str_.charAt(i);
            s_ = '0' + '7';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    public static String toGeneBin(int _i) {
        if (_i >= 0) {
            return toBase(_i, 2).toString();
        }
        StringBuilder str_ = toBase(-_i - 1, 2);
        int len_ = str_.length();
        int nbZeros_ = 32 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < 32; i++) {
            char c_ = str_.charAt(i);
            int s_ = '0' + '1';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    private static StringBuilder toBase(int _i, int _base) {
        StringBuilder str_ = new StringBuilder();
        if (_i == 0) {
            str_.append(0);
            return str_;
        }
        int q_ = _i;
        while (q_ > 0) {
            int r_ = q_ % _base;
            str_.insert(0, toSingleChar(r_));
            q_ = q_ / _base;
        }
        return str_;
    }

    public static String toShortGeneHex(short _i) {
        if (_i >= 0) {
            return toShortBase(_i, 16).toString();
        }
        StringBuilder str_ = toShortBase((short) (-_i - 1), 16);
        int len_ = str_.length();
        int nbZeros_ = 4 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < 4; i++) {
            char c_ = str_.charAt(i);
            if (c_ >= '6' &&c_ <= '9') {
                int s_ = '6' + '9';
                str_.setCharAt(i,(char)(s_-c_));
                continue;
            }
            int s_ = 'a' + '5';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    public static String toShortGeneOct(short _i) {
        if (_i >= 0) {
            return toShortBase(_i, 8).toString();
        }
        StringBuilder str_ = toShortBase((short) (-_i - 1), 8);
        int len_ = str_.length();
        int nbZeros_ = 6 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        char c_ = str_.charAt(0);
        int s_ = '0' + '1';
        str_.setCharAt(0,(char)(s_-c_));
        for (int i = 1; i < 6; i++) {
            c_ = str_.charAt(i);
            s_ = '0' + '7';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    public static String toShortGeneBin(short _i) {
        if (_i >= 0) {
            return toShortBase(_i, 2).toString();
        }
        StringBuilder str_ = toShortBase((short) (-_i - 1), 2);
        int len_ = str_.length();
        int nbZeros_ = 16 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < 16; i++) {
            char c_ = str_.charAt(i);
            int s_ = '0' + '1';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    private static StringBuilder toShortBase(short _i, int _base) {
        StringBuilder str_ = new StringBuilder();
        if (_i == 0) {
            str_.append(0);
            return str_;
        }
        int q_ = _i;
        while (q_ > 0) {
            int r_ = q_ % _base;
            str_.insert(0, toSingleChar(r_));
            q_ = q_ / _base;
        }
        return str_;
    }

    public static String toByteGeneHex(byte _i) {
        if (_i >= 0) {
            return toByteBase(_i, 16).toString();
        }
        StringBuilder str_ = toByteBase((byte) (-_i - 1), 16);
        int len_ = str_.length();
        int nbZeros_ = 2 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < 2; i++) {
            char c_ = str_.charAt(i);
            if (c_ >= '6' &&c_ <= '9') {
                int s_ = '6' + '9';
                str_.setCharAt(i,(char)(s_-c_));
                continue;
            }
            int s_ = 'a' + '5';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    public static String toByteGeneOct(byte _i) {
        if (_i >= 0) {
            return toByteBase(_i, 8).toString();
        }
        StringBuilder str_ = toByteBase((byte) (-_i - 1), 8);
        int len_ = str_.length();
        int nbZeros_ = 3 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        char c_ = str_.charAt(0);
        int s_ = '0' + '3';
        str_.setCharAt(0,(char)(s_-c_));
        for (int i = 1; i < 3; i++) {
            c_ = str_.charAt(i);
            s_ = '0' + '7';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    public static String toByteGeneBin(byte _i) {
        if (_i >= 0) {
            return toByteBase(_i, 2).toString();
        }
        StringBuilder str_ = toByteBase((byte) (-_i - 1), 2);
        int len_ = str_.length();
        int nbZeros_ = 8 - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < 8; i++) {
            char c_ = str_.charAt(i);
            int s_ = '0' + '1';
            str_.setCharAt(i,(char)(s_-c_));
        }
        return str_.toString();
    }

    private static StringBuilder toByteBase(byte _i, int _base) {
        StringBuilder str_ = new StringBuilder();
        if (_i == 0) {
            str_.append(0);
            return str_;
        }
        int q_ = _i;
        while (q_ > 0) {
            int r_ = q_ % _base;
            str_.insert(0, toSingleChar(r_));
            q_ = q_ / _base;
        }
        return str_;
    }
    private static String toSingleChar(int _i) {
        if (_i < 10) {
            return Long.toString(_i);
        }
        return Character.toString((char)(_i+'a'-10));
    }

    public static StringList getDollarWordSeparators(String _str) {
        if (_str.length() == 0) {
            return new StringList();
        }
        StringList ret_ = new StringList();
        boolean wasWordChar_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        StringBuilder str_ = new StringBuilder();
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(_str);
                return ret_;
            }
            if (isDollarWordChar(_str.charAt(i_))) {
                str_.append(_str, IndexConstants.FIRST_INDEX, i_);
                break;
            }
            i_++;
        }
        while (true) {
            if (i_ >= _str.length()) {
                ret_.add(str_.toString());
                break;
            }
            char char_ = _str.charAt(i_);
            if (isDollarWordChar(char_)) {
                if (!wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = true;
                }
            } else {
                if (wasWordChar_) {
                    ret_.add(str_.toString());
                    str_ = new StringBuilder();
                    wasWordChar_ = false;
                }
            }
            str_.append(char_);
            i_++;
        }
        return ret_;
    }

    public static boolean isDollarWord(String _string) {
        if (_string.isEmpty()) {
            return false;
        }
        for (char c : _string.toCharArray()) {
            if (!isDollarWordChar(c)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isDollarWordChar(char _char) {
        if (_char == '$') {
            return true;
        }
        return MathExpUtil.isWordChar(_char);
    }

    public static String toLowerCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            str_.append(toLowerCase(_string.charAt(i)));
        }
        return str_.toString();
    }

    public static String toUpperCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            str_.append(toUpperCase(_string.charAt(i)));
        }
        return str_.toString();
    }

    public static int digit(char _ch,int _radix) {
        if (_radix < 2) {
            return -1;
        }
        if (_radix > 36) {
            return -1;
        }
        int digit_ = -1;
        if (MathExpUtil.isDigit(_ch)) {
            digit_ = _ch - '0';
        }
        if (_ch >= 'a' && _ch <= 'z') {
            digit_ = _ch - 'a' + 10;
        }
        if (_ch >= 'A' && _ch <= 'Z') {
            digit_ = _ch - 'A' + 10;
        }
        if (digit_ < _radix) {
            return digit_;
        }
        return -1;
    }

    public static char forDigit(int _digit, int _radix) {
        if (_radix < 2) {
            return 0;
        }
        if (_radix > 36) {
            return 0;
        }
        if (_digit < 0) {
            return 0;
        }
        if (_digit >= _radix) {
            return 0;
        }
        if (_digit < 10) {
            return (char) ('0'+_digit);
        }
        return (char)('a'+_digit-10);
    }

    public static boolean isLetterOrDigit(char _ch) {
        if (_ch < '0') {
            return false;
        }
        if (_ch <= '9') {
            return true;
        }
        if (_ch < 'A') {
            return false;
        }
        if (_ch <= 'Z') {
            return true;
        }
        if (_ch < 'a') {
            return false;
        }
        if (_ch <= 'z') {
            return true;
        }
        if (_ch < 170) {
            return false;
        }
        if (_ch < 192) {
            return _ch == 170 || _ch == 181 || _ch == 186;
        }
        if (_ch < 706) {
            return isSupplLetter(_ch);
        }
        if (isOtherLett(_ch)) {
            return true;
        }
        return isOtherDigit(_ch);
    }

    public static boolean isLetter(char _ch) {
        if (_ch < 'A') {
            return false;
        }
        if (_ch <= 'Z') {
            return true;
        }
        if (_ch < 'a') {
            return false;
        }
        if (_ch <= 'z') {
            return true;
        }
        if (_ch < 170) {
            return false;
        }
        if (_ch < 192) {
            return _ch == 170 || _ch == 181 || _ch == 186;
        }
        if (_ch < 706) {
            return isSupplLetter(_ch);
        }
        return isOtherLett(_ch);
    }

    private static boolean isOtherLett(char _ch) {
        if (_ch >= 19968 && _ch < 40909) {
            return true;
        }
        if (_ch >= 44032 && _ch < 55204) {
            return true;
        }
        if (_ch >= 13312 && _ch < 19894) {
            return true;
        }
        if (_ch >= 40960 && _ch < 42125) {
            return true;
        }
        if (_ch >= 5121 && _ch < 5741) {
            return true;
        }
        if (_ch >= 63744) {
            return isGreatLetter(_ch);
        }
        if (_ch >= 1015 && _ch < 1154) {
            return true;
        }
        if (_ch >= 1162 && _ch < 1320) {
            return true;
        }
        if (_ch >= 1649 && _ch < 1748) {
            return true;
        }
        if (_ch >= 4348 && _ch < 4681) {
            return true;
        }
        if (_ch >= 7680 && _ch < 7958) {
            return true;
        }
        if (_ch >= 7424 && _ch < 7616) {
            return true;
        }
        if (_ch >= 11360 && _ch < 11493) {
            return true;
        }
        if (_ch >= 12353 && _ch < 12439) {
            return true;
        }
        if (_ch >= 12449 && _ch < 12539) {
            return true;
        }
        if (_ch >= 12593 && _ch < 12687) {
            return true;
        }
        if (_ch >= 42240 && _ch < 42509) {
            return true;
        }
        if (_ch >= 42786 && _ch < 42889) {
            return true;
        }
        if (_ch >= 42656 && _ch < 42726) {
            return true;
        }
        if (_ch >= 43072 && _ch < 43124) {
            return true;
        }
        if (_ch >= 43138 && _ch < 43188) {
            return true;
        }
        if (_ch >= 11568 && _ch < 11624) {
            return true;
        }
        if (_ch >= 931 && _ch < 1014) {
            return true;
        }
        if (_ch >= 1869 && _ch < 1958) {
            return true;
        }
        if (_ch >= 2308 && _ch < 2362) {
            return true;
        }
        if (_ch >= 4824 && _ch < 4881) {
            return true;
        }
        if (_ch >= 4888 && _ch < 4955) {
            return true;
        }
        if (_ch >= 5024 && _ch < 5109) {
            return true;
        }
        if (_ch >= 5792 && _ch < 5867) {
            return true;
        }
        if (_ch >= 6176 && _ch < 6264) {
            return true;
        }
        if (_ch >= 6320 && _ch < 6390) {
            return true;
        }
        if (_ch >= 6016 && _ch < 6068) {
            return true;
        }
        if (_ch >= 6688 && _ch < 6741) {
            return true;
        }
        if (_ch >= 8064 && _ch < 8117) {
            return true;
        }
        if (_ch >= 55243 && _ch < 55292) {
            return true;
        }
        if (_ch >= 55216 && _ch < 55239) {
            return true;
        }
        if (_ch >= 42192 && _ch < 42238) {
            return true;
        }
        if (_ch >= 42560 && _ch < 42607) {
            return true;
        }
        if (_ch >= 42623 && _ch < 42648) {
            return true;
        }
        if (_ch >= 43020 && _ch < 43043) {
            return true;
        }
        if (_ch >= 43274 && _ch < 43302) {
            return true;
        }
        if (_ch >= 43312 && _ch < 43335) {
            return true;
        }
        if (_ch >= 43360 && _ch < 43389) {
            return true;
        }
        if (_ch >= 43396 && _ch < 43443) {
            return true;
        }
        if (_ch >= 43520 && _ch < 43561) {
            return true;
        }
        if (_ch >= 43616 && _ch < 43639) {
            return true;
        }
        if (_ch >= 43648 && _ch < 43696) {
            return true;
        }
        if (_ch >= 43968 && _ch < 44003) {
            return true;
        }
        if (_ch >= 710 && _ch < 722) {
            return true;
        }
        if (_ch >= 736 && _ch < 741) {
            return true;
        }
        if (_ch >= 880 && _ch < 885) {
            return true;
        }
        if (_ch >= 886 && _ch < 888) {
            return true;
        }
        if (_ch >= 890 && _ch < 894) {
            return true;
        }
        if (_ch >= 904 && _ch < 907) {
            return true;
        }
        if (_ch >= 1520 && _ch < 1523) {
            return true;
        }
        if (_ch >= 1646 && _ch < 1648) {
            return true;
        }
        if (_ch >= 1765 && _ch < 1767) {
            return true;
        }
        if (_ch >= 1774 && _ch < 1776) {
            return true;
        }
        if (_ch >= 1786 && _ch < 1789) {
            return true;
        }
        if (_ch >= 2036 && _ch < 2038) {
            return true;
        }
        if (_ch >= 2210 && _ch < 2221) {
            return true;
        }
        if (_ch >= 2392 && _ch < 2402) {
            return true;
        }
        if (_ch >= 2417 && _ch < 2424) {
            return true;
        }
        if (_ch >= 2425 && _ch < 2432) {
            return true;
        }
        if (_ch >= 2437 && _ch < 2445) {
            return true;
        }
        if (_ch >= 2447 && _ch < 2449) {
            return true;
        }
        if (_ch >= 2474 && _ch < 2481) {
            return true;
        }
        if (_ch >= 2486 && _ch < 2490) {
            return true;
        }
        if (_ch >= 2524 && _ch < 2526) {
            return true;
        }
        if (_ch >= 2527 && _ch < 2530) {
            return true;
        }
        if (_ch >= 2544 && _ch < 2546) {
            return true;
        }
        if (_ch >= 2565 && _ch < 2571) {
            return true;
        }
        if (_ch >= 2575 && _ch < 2577) {
            return true;
        }
        if (_ch >= 2602 && _ch < 2609) {
            return true;
        }
        if (_ch >= 2610 && _ch < 2612) {
            return true;
        }
        if (_ch >= 2613 && _ch < 2615) {
            return true;
        }
        if (_ch >= 2616 && _ch < 2618) {
            return true;
        }
        if (_ch >= 2649 && _ch < 2653) {
            return true;
        }
        if (_ch >= 2674 && _ch < 2677) {
            return true;
        }
        if (_ch >= 2693 && _ch < 2702) {
            return true;
        }
        if (_ch >= 2703 && _ch < 2706) {
            return true;
        }
        if (_ch >= 2730 && _ch < 2737) {
            return true;
        }
        if (_ch >= 2738 && _ch < 2740) {
            return true;
        }
        if (_ch >= 2741 && _ch < 2746) {
            return true;
        }
        if (_ch >= 2784 && _ch < 2786) {
            return true;
        }
        if (_ch >= 2821 && _ch < 2829) {
            return true;
        }
        if (_ch >= 2831 && _ch < 2833) {
            return true;
        }
        if (_ch >= 2858 && _ch < 2865) {
            return true;
        }
        if (_ch >= 2866 && _ch < 2868) {
            return true;
        }
        if (_ch >= 2869 && _ch < 2874) {
            return true;
        }
        if (_ch >= 2908 && _ch < 2910) {
            return true;
        }
        if (_ch >= 2911 && _ch < 2914) {
            return true;
        }
        if (_ch >= 2949 && _ch < 2955) {
            return true;
        }
        if (_ch >= 2958 && _ch < 2961) {
            return true;
        }
        if (_ch >= 2962 && _ch < 2966) {
            return true;
        }
        if (_ch >= 2969 && _ch < 2971) {
            return true;
        }
        if (_ch >= 2974 && _ch < 2976) {
            return true;
        }
        if (_ch >= 2979 && _ch < 2981) {
            return true;
        }
        if (_ch >= 2984 && _ch < 2987) {
            return true;
        }
        if (_ch >= 2990 && _ch < 3002) {
            return true;
        }
        if (_ch >= 3077 && _ch < 3085) {
            return true;
        }
        if (_ch >= 3086 && _ch < 3089) {
            return true;
        }
        if (_ch >= 3114 && _ch < 3124) {
            return true;
        }
        if (_ch >= 3125 && _ch < 3130) {
            return true;
        }
        if (_ch >= 3160 && _ch < 3162) {
            return true;
        }
        if (_ch >= 3168 && _ch < 3170) {
            return true;
        }
        if (_ch >= 3205 && _ch < 3213) {
            return true;
        }
        if (_ch >= 3214 && _ch < 3217) {
            return true;
        }
        if (_ch >= 3242 && _ch < 3252) {
            return true;
        }
        if (_ch >= 3253 && _ch < 3258) {
            return true;
        }
        if (_ch >= 3296 && _ch < 3298) {
            return true;
        }
        if (_ch >= 3313 && _ch < 3315) {
            return true;
        }
        if (_ch >= 3333 && _ch < 3341) {
            return true;
        }
        if (_ch >= 3342 && _ch < 3345) {
            return true;
        }
        if (_ch >= 3424 && _ch < 3426) {
            return true;
        }
        if (_ch >= 3450 && _ch < 3456) {
            return true;
        }
        if (_ch >= 3461 && _ch < 3479) {
            return true;
        }
        if (_ch >= 3507 && _ch < 3516) {
            return true;
        }
        if (_ch >= 3520 && _ch < 3527) {
            return true;
        }
        if (_ch >= 3634 && _ch < 3636) {
            return true;
        }
        if (_ch >= 3648 && _ch < 3655) {
            return true;
        }
        if (_ch >= 3713 && _ch < 3715) {
            return true;
        }
        if (_ch >= 3719 && _ch < 3721) {
            return true;
        }
        if (_ch >= 3732 && _ch < 3736) {
            return true;
        }
        if (_ch >= 3737 && _ch < 3744) {
            return true;
        }
        if (_ch >= 3745 && _ch < 3748) {
            return true;
        }
        if (_ch >= 3754 && _ch < 3756) {
            return true;
        }
        if (_ch >= 3757 && _ch < 3761) {
            return true;
        }
        if (_ch >= 3762 && _ch < 3764) {
            return true;
        }
        if (_ch >= 3776 && _ch < 3781) {
            return true;
        }
        if (_ch >= 3804 && _ch < 3808) {
            return true;
        }
        if (_ch >= 3904 && _ch < 3912) {
            return true;
        }
        if (_ch >= 3976 && _ch < 3981) {
            return true;
        }
        if (_ch >= 4176 && _ch < 4182) {
            return true;
        }
        if (_ch >= 4186 && _ch < 4190) {
            return true;
        }
        if (_ch >= 4197 && _ch < 4199) {
            return true;
        }
        if (_ch >= 4206 && _ch < 4209) {
            return true;
        }
        if (_ch >= 4213 && _ch < 4226) {
            return true;
        }
        if (_ch >= 4682 && _ch < 4686) {
            return true;
        }
        if (_ch >= 4688 && _ch < 4695) {
            return true;
        }
        if (_ch >= 4698 && _ch < 4702) {
            return true;
        }
        if (_ch >= 4746 && _ch < 4750) {
            return true;
        }
        if (_ch >= 4786 && _ch < 4790) {
            return true;
        }
        if (_ch >= 4792 && _ch < 4799) {
            return true;
        }
        if (_ch >= 4802 && _ch < 4806) {
            return true;
        }
        if (_ch >= 4808 && _ch < 4823) {
            return true;
        }
        if (_ch >= 4882 && _ch < 4886) {
            return true;
        }
        if (_ch >= 4992 && _ch < 5008) {
            return true;
        }
        if (_ch >= 5743 && _ch < 5760) {
            return true;
        }
        if (_ch >= 5888 && _ch < 5901) {
            return true;
        }
        if (_ch >= 5902 && _ch < 5906) {
            return true;
        }
        if (_ch >= 5920 && _ch < 5938) {
            return true;
        }
        if (_ch >= 5952 && _ch < 5970) {
            return true;
        }
        if (_ch >= 5984 && _ch < 5997) {
            return true;
        }
        if (_ch >= 5998 && _ch < 6001) {
            return true;
        }
        if (_ch >= 6512 && _ch < 6517) {
            return true;
        }
        if (_ch >= 6593 && _ch < 6600) {
            return true;
        }
        if (_ch >= 6981 && _ch < 6988) {
            return true;
        }
        if (_ch >= 7086 && _ch < 7088) {
            return true;
        }
        if (_ch >= 7245 && _ch < 7248) {
            return true;
        }
        if (_ch >= 7401 && _ch < 7405) {
            return true;
        }
        if (_ch >= 7406 && _ch < 7410) {
            return true;
        }
        if (_ch >= 7413 && _ch < 7415) {
            return true;
        }
        if (_ch >= 7960 && _ch < 7966) {
            return true;
        }
        if (_ch >= 8008 && _ch < 8014) {
            return true;
        }
        if (_ch >= 8016 && _ch < 8024) {
            return true;
        }
        if (_ch >= 8118 && _ch < 8125) {
            return true;
        }
        if (_ch >= 8130 && _ch < 8133) {
            return true;
        }
        if (_ch >= 8134 && _ch < 8141) {
            return true;
        }
        if (_ch >= 8144 && _ch < 8148) {
            return true;
        }
        if (_ch >= 8150 && _ch < 8156) {
            return true;
        }
        if (_ch >= 8160 && _ch < 8173) {
            return true;
        }
        if (_ch >= 8178 && _ch < 8181) {
            return true;
        }
        if (_ch >= 8182 && _ch < 8189) {
            return true;
        }
        if (_ch >= 8336 && _ch < 8349) {
            return true;
        }
        if (_ch >= 8458 && _ch < 8468) {
            return true;
        }
        if (_ch >= 8473 && _ch < 8478) {
            return true;
        }
        if (_ch >= 8490 && _ch < 8494) {
            return true;
        }
        if (_ch >= 8495 && _ch < 8506) {
            return true;
        }
        if (_ch >= 8508 && _ch < 8512) {
            return true;
        }
        if (_ch >= 8517 && _ch < 8522) {
            return true;
        }
        if (_ch >= 8579 && _ch < 8581) {
            return true;
        }
        if (_ch >= 11499 && _ch < 11503) {
            return true;
        }
        if (_ch >= 11506 && _ch < 11508) {
            return true;
        }
        if (_ch >= 11680 && _ch < 11687) {
            return true;
        }
        if (_ch >= 11688 && _ch < 11695) {
            return true;
        }
        if (_ch >= 11696 && _ch < 11703) {
            return true;
        }
        if (_ch >= 11704 && _ch < 11711) {
            return true;
        }
        if (_ch >= 11712 && _ch < 11719) {
            return true;
        }
        if (_ch >= 11720 && _ch < 11727) {
            return true;
        }
        if (_ch >= 11728 && _ch < 11735) {
            return true;
        }
        if (_ch >= 11736 && _ch < 11743) {
            return true;
        }
        if (_ch >= 12293 && _ch < 12295) {
            return true;
        }
        if (_ch >= 12337 && _ch < 12342) {
            return true;
        }
        if (_ch >= 12347 && _ch < 12349) {
            return true;
        }
        if (_ch >= 12445 && _ch < 12448) {
            return true;
        }
        if (_ch >= 12540 && _ch < 12544) {
            return true;
        }
        if (_ch >= 12784 && _ch < 12800) {
            return true;
        }
        if (_ch >= 910 && _ch < 930) {
            return true;
        }
        if (_ch >= 1329 && _ch < 1367) {
            return true;
        }
        if (_ch >= 1377 && _ch < 1416) {
            return true;
        }
        if (_ch >= 1488 && _ch < 1515) {
            return true;
        }
        if (_ch >= 1568 && _ch < 1611) {
            return true;
        }
        if (_ch >= 1810 && _ch < 1840) {
            return true;
        }
        if (_ch >= 1994 && _ch < 2027) {
            return true;
        }
        if (_ch >= 2048 && _ch < 2070) {
            return true;
        }
        if (_ch >= 2112 && _ch < 2137) {
            return true;
        }
        if (_ch >= 2451 && _ch < 2473) {
            return true;
        }
        if (_ch >= 2579 && _ch < 2601) {
            return true;
        }
        if (_ch >= 2707 && _ch < 2729) {
            return true;
        }
        if (_ch >= 2835 && _ch < 2857) {
            return true;
        }
        if (_ch >= 3090 && _ch < 3113) {
            return true;
        }
        if (_ch >= 3218 && _ch < 3241) {
            return true;
        }
        if (_ch >= 3346 && _ch < 3387) {
            return true;
        }
        if (_ch >= 3482 && _ch < 3506) {
            return true;
        }
        if (_ch >= 3585 && _ch < 3633) {
            return true;
        }
        if (_ch >= 3913 && _ch < 3949) {
            return true;
        }
        if (_ch >= 4096 && _ch < 4139) {
            return true;
        }
        if (_ch >= 4256 && _ch < 4294) {
            return true;
        }
        if (_ch >= 4304 && _ch < 4347) {
            return true;
        }
        if (_ch >= 4704 && _ch < 4745) {
            return true;
        }
        if (_ch >= 4752 && _ch < 4785) {
            return true;
        }
        if (_ch >= 5761 && _ch < 5787) {
            return true;
        }
        if (_ch >= 6272 && _ch < 6313) {
            return true;
        }
        if (_ch >= 6400 && _ch < 6429) {
            return true;
        }
        if (_ch >= 6480 && _ch < 6510) {
            return true;
        }
        if (_ch >= 6528 && _ch < 6572) {
            return true;
        }
        if (_ch >= 6656 && _ch < 6679) {
            return true;
        }
        if (_ch >= 6917 && _ch < 6964) {
            return true;
        }
        if (_ch >= 7043 && _ch < 7073) {
            return true;
        }
        if (_ch >= 7098 && _ch < 7142) {
            return true;
        }
        if (_ch >= 7168 && _ch < 7204) {
            return true;
        }
        if (_ch >= 7258 && _ch < 7294) {
            return true;
        }
        if (_ch >= 7968 && _ch < 8006) {
            return true;
        }
        if (_ch >= 8031 && _ch < 8062) {
            return true;
        }
        if (_ch >= 11264 && _ch < 11311) {
            return true;
        }
        if (_ch >= 11312 && _ch < 11359) {
            return true;
        }
        if (_ch >= 11520 && _ch < 11558) {
            return true;
        }
        if (_ch >= 11648 && _ch < 11671) {
            return true;
        }
        if (_ch >= 12549 && _ch < 12590) {
            return true;
        }
        if (_ch >= 12704 && _ch < 12731) {
            return true;
        }
        if (_ch >= 42512 && _ch < 42528) {
            return true;
        }
        if (_ch >= 42538 && _ch < 42540) {
            return true;
        }
        if (_ch >= 42775 && _ch < 42784) {
            return true;
        }
        if (_ch >= 42891 && _ch < 42895) {
            return true;
        }
        if (_ch >= 42896 && _ch < 42900) {
            return true;
        }
        if (_ch >= 42912 && _ch < 42923) {
            return true;
        }
        if (_ch >= 43000 && _ch < 43010) {
            return true;
        }
        if (_ch >= 43011 && _ch < 43014) {
            return true;
        }
        if (_ch >= 43015 && _ch < 43019) {
            return true;
        }
        if (_ch >= 43250 && _ch < 43256) {
            return true;
        }
        if (_ch >= 43584 && _ch < 43587) {
            return true;
        }
        if (_ch >= 43588 && _ch < 43596) {
            return true;
        }
        if (_ch >= 43701 && _ch < 43703) {
            return true;
        }
        if (_ch >= 43705 && _ch < 43710) {
            return true;
        }
        if (_ch >= 43739 && _ch < 43742) {
            return true;
        }
        if (_ch >= 43744 && _ch < 43755) {
            return true;
        }
        if (_ch >= 43762 && _ch < 43765) {
            return true;
        }
        if (_ch >= 43777 && _ch < 43783) {
            return true;
        }
        if (_ch >= 43785 && _ch < 43791) {
            return true;
        }
        if (_ch >= 43793 && _ch < 43799) {
            return true;
        }
        if (_ch >= 43808 && _ch < 43815) {
            return true;
        }
        if (_ch >= 43816 && _ch < 43823) {
            return true;
        }
        for (int v:NumberUtil.wrapIntArray(
                748,
                750,
                902,
                908,
                1369,
                1749,
                1791,
                1808,
                1969,
                2042,
                2074,
                2084,
                2088,
                2208,
                2365,
                2384,
                2482,
                2493,
                2510,
                2654,
                2749,
                2768,
                2877,
                2929,
                2947,
                2972,
                3024,
                3133,
                3261,
                3294,
                3389,
                3406,
                3517,
                3716,
                3722,
                3725,
                3749,
                3751,
                3773,
                3782,
                3840,
                4159,
                4193,
                4238,
                4295,
                4301,
                4696,
                4800,
                6103,
                6108,
                6314,
                6823,
                8025,
                8027,
                8029,
                8126,
                8305,
                8319,
                8450,
                8455,
                8469,
                8484,
                8486,
                8488,
                8526,
                11559,
                11565,
                11631,
                11823,
                43259,
                43471,
                43642,
                43697,
                43712,
                43714
        )) {
            if (v == _ch) {
                return true;
            }
        }
        return false;
    }

    private static boolean isGreatLetter(char _ch) {
        if (_ch < 64110) {
            return true;
        }
        if (_ch >= 64467 && _ch < 64830) {
            return true;
        }
        if (_ch >= 64112 && _ch < 64218) {
            return true;
        }
        if (_ch >= 64326 && _ch < 64434) {
            return true;
        }
        if (_ch >= 65142 && _ch < 65277) {
            return true;
        }
        if (_ch >= 65382 && _ch < 65471) {
            return true;
        }
        if (_ch >= 64848 && _ch < 64912) {
            return true;
        }
        if (_ch >= 64914 && _ch < 64968) {
            return true;
        }
        if (_ch >= 65313 && _ch < 65339) {
            return true;
        }
        if (_ch >= 65345 && _ch < 65371) {
            return true;
        }
        if (_ch >= 64287 && _ch < 64297) {
            return true;
        }
        if (_ch >= 64298 && _ch < 64311) {
            return true;
        }
        if (_ch >= 65008 && _ch < 65020) {
            return true;
        }
        if (_ch >= 64256 && _ch < 64263) {
            return true;
        }
        if (_ch >= 64275 && _ch < 64280) {
            return true;
        }
        if (_ch >= 64312 && _ch < 64317) {
            return true;
        }
        if (_ch >= 65136 && _ch < 65141) {
            return true;
        }
        if (_ch >= 65474 && _ch < 65480) {
            return true;
        }
        if (_ch >= 65482 && _ch < 65488) {
            return true;
        }
        if (_ch >= 65490 && _ch < 65496) {
            return true;
        }
        if (_ch >= 64320 && _ch < 64325) {
            return _ch != 64322;
        }
        if (_ch >= 65498 && _ch < 65501) {
            return true;
        }
        return _ch == 64285 || _ch == 64318;
    }

    private static boolean isSupplLetter(char _ch) {
        if (_ch == 215) {
            return false;
        }
        return _ch != 247;
    }

    public static boolean isLowerCase(char _string) {
        if (isUnassigned(_string)) {
            return false;
        }
        if (isOtherSpace(_string)) {
            return false;
        }
        if (MathExpUtil.isDigit(_string)) {
            return false;
        }
        if (isOtherDigit(_string)) {
            return false;
        }
        if (_string == 453 || _string == 456 || _string == 459 || _string == 498) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(426,627,609,638,704,42800,8150,8166,
                8182,8462,8508,8134,8146,8118,7836)) {
            if (_string >= i && _string <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(620,8162,43000)) {
            if (_string >= i && _string <= i + 2) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(604,8518)) {
            if (_string >= i && _string <= i + 3) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(736,7830,64275)) {
            if (_string >= i && _string <= i + 4) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(42864)) {
            if (_string >= i && _string <= i + 8) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(564)) {
            if (_string >= i && _string <= i + 5) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(630,641,11383,64256)) {
            if (_string >= i && _string <= i + 6) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(8336)) {
            if (_string >= i && _string <= i + 12) {
                return true;
            }
        }
        if (_string == 42894 || _string == 11492) {
            return true;
        }
        if (_string == 11377 || _string == 11380) {
            return true;
        }
        if (_string == 170 || _string == 186 || _string == 8305) {
            return true;
        }
        if (_string == 8500 || _string == 8505 || _string == 8495) {
            return true;
        }
        if (_string == 8467 || _string == 8458 || _string == 8319) {
            return true;
        }
        if (_string == 8180 || _string == 8178 || _string == 8132) {
            return true;
        }
        if (_string == 8114 || _string == 8116 || _string == 8130) {
            return true;
        }
        if (_string == 8022 || _string == 8020 || _string == 8018) {
            return true;
        }
        if (_string == 8016 || _string == 7839) {
            return true;
        }
        if (_string == 223 || _string == 312) {
            return true;
        }
        if (_string == 329 || _string == 397) {
            return true;
        }
        if (_string >= 7424 && _string <= 7615) {
            return true;
        }
        if (_string == 411 || _string == 442) {
            return true;
        }
        if (_string == 446 || _string == 496) {
            return true;
        }
        if (_string == 545 || _string == 597) {
            return true;
        }
        if (_string == 600 || _string == 602) {
            return true;
        }
        if (_string >= 612 && _string <= 624) {
            return true;
        }
        if (_string == 660) {
            return false;
        }
        if (_string >= 653 && _string <= 696) {
            return true;
        }
        if (_string == 890 || _string == 912 || _string == 944) {
            return true;
        }
        if (_string == 1011 || _string == 1020 || _string == 1415) {
            return true;
        }
        if (toLowerCaseIntCheck(_string) == _string) {
            return toUpperCaseIntCheckLow(_string)!=_string;
        }
        return false;
    }

    public static boolean isUpperCase(char _string) {
        if (isUnassigned(_string)) {
            return false;
        }
        if (isOtherSpace(_string)) {
            return false;
        }
        if (MathExpUtil.isDigit(_string)) {
            return false;
        }
        if (isOtherDigit(_string)) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(8072,8088,8104)) {
            if (_string >= i && _string <= i + 7) {
                return false;
            }
        }
        for (int i: NumberUtil.wrapIntArray(978,8459,8464)) {
            if (_string >= i && _string <= i + 2) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(8492,8496,8510)) {
            if (_string >= i && _string <= i + 1) {
                return true;
            }
        }
        if (_string == 8124) {
            return false;
        }
        if (_string == 8140) {
            return false;
        }
        if (_string == 8188) {
            return false;
        }
        if (_string == 8450 || _string == 8484) {
            return true;
        }
        if (_string == 8455 || _string == 8488) {
            return true;
        }
        if (_string == 8469 || _string == 8499 || _string == 8517) {
            return true;
        }
        if (_string >= 8473 && _string <= 8477) {
            return true;
        }
        return toUpperCaseIntCheck(_string)==_string&&toLowerCaseIntCheckUpp(_string)!=_string;
    }

    public static char toLowerCase(char _string) {
        return (char) toLowerCaseInt(_string);
    }

    public static char toUpperCase(char _string) {
        return (char) toUpperCaseInt(_string);
    }

    public static byte getDirectionality(char _ch) {
        int type_ = getCustomType(_ch);
        return (byte)generalDir(_ch, type_);
    }

    public static byte getType(char _ch) {
        int type_ = getCustomType(_ch);
        return (byte)procGene(_ch, type_);
    }

    public static int getCustomType(char _ch) {
        if (isLetter(_ch)) {
            if (isLowerCase(_ch)) {
                if (toUpperCase(_ch) != _ch) {
                    return LETTER_SENS_LOWER_CASE;
                }
                return LETTER_INSENS_LOWER_CASE;
            }
            if (isUpperCase(_ch)) {
                if (toLowerCase(_ch) != _ch) {
                    return LETTER_SENS_UPPER_CASE;
                }
                return LETTER_INSENS_UPPER_CASE;
            }
            if (_ch == 453 || _ch == 456 || _ch == 459 || _ch == 498) {
                return LETTER_SENS_NO_CASE;
            }
            if (_ch >= 8072 && _ch <= 8079) {
                return LETTER_SEMI_SENS_NO_CASE;
            }
            if (_ch >= 8088 && _ch <= 8095) {
                return LETTER_SEMI_SENS_NO_CASE;
            }
            if (_ch >= 8104 && _ch <= 8111) {
                return LETTER_SEMI_SENS_NO_CASE;
            }
            if (_ch == 8124 || _ch == 8140 || _ch == 8188) {
                return LETTER_SEMI_SENS_NO_CASE;
            }
            if (_ch == 6103) {
                return LETTER_INSENS_NO_CASE_DEF_DIR;
            }
            if (_ch == 43259) {
                return LETTER_INSENS_NO_CASE_DEF_DIR;
            }
            if (_ch >= 1488 && _ch<=2220) {
                return LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT;
            }
            if (_ch >= 64285 && _ch<=65276) {
                return LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT;
            }
            if (_ch >= 699 && _ch<=703) {
                return LETTER_INSENS_NO_CASE_DEF_DIR;
            }
            if (_ch >= 697 && _ch<=719) {
                return LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS;
            }
            if (_ch == 11823 || _ch == 884|| _ch == 748) {
                return LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS;
            }
            if (_ch >= 42656 && _ch<=42725) {
                return LETTER_INSENS_NO_CASE_DEF_DIR;
            }
            if (_ch >= 42623 && _ch<=42888) {
                return LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS;
            }
            return LETTER_INSENS_NO_CASE_DEF_DIR;
        }
        if (MathExpUtil.isDigit(_ch)) {
            return DIGIT;
        }
        if (isOtherDigit(_ch)) {
            return DIGIT_OTHER;
        }
        if (_ch == '_' || _ch == 160) {
            return ID_SEP;
        }
        if (isCurrencyChar(_ch)) {
            return CURRENCY;
        }
        if (isSensibleOtherLetter(_ch)) {
            return SENS_OTHER_LETTER;
        }
        if (isRomanDigits(_ch)) {
            return ROMAN_DIGIT;
        }
        if (_ch == '(' || _ch == '[' || _ch == '{') {
            return DEL_LEFT;
        }
        if (_ch == ')' || _ch == ']' || _ch == '}') {
            return DEL_RIGHT;
        }
        if (_ch == '!' || _ch == '+' || _ch == '-' ||
                _ch == '*' || _ch == '%' || _ch == '/'||
                _ch == '#' || _ch == '&' || _ch == '|'||
                _ch == '^' || _ch == '?' || _ch == ':'||
                _ch == '<' || _ch == '=' || _ch == '>'||
                _ch == '~' || _ch == '@') {
            return OPERATOR_LANGUAGE;
        }
        if (_ch == ',' || _ch == '.') {
            return OPERATOR_SPEC;
        }
        if (_ch == ';') {
            return PUNCTUATION;
        }
        if (_ch == '\'' || _ch == '`' || _ch == '"') {
            return QUOTES;
        }
        if (_ch == '\\') {
            return ESCAPE;
        }
        if (isOtherMathSymbol(_ch)) {
            return MATH;
        }
        if (_ch == 166) {
            return MODIFIER;
        }
        for (int i: NumberUtil.wrapIntArray(168,184)) {
            if (_ch >= i && _ch <= i + 1) {
                return MODIFIER;
            }
        }
        for (int i: NumberUtil.wrapIntArray(173)) {
            if (_ch >= i && _ch <= i + 3) {
                return MODIFIER;
            }
        }
        for (int i: NumberUtil.wrapIntArray(178,188)) {
            if (_ch >= i && _ch <= i + 2) {
                return MODIFIER;
            }
        }
        if (isOtherSymbol(_ch)) {
            if (isModifierSymbol(_ch)) {
                return SYMBOL_MODIFIER;
            }
            return SYMBOL_OTHER;
        }
        if (isOtherPonctuation(_ch)) {
            if (isConnector(_ch)) {
                return PUNCTUATION_CONNECTOR;
            }
            if (isBoundPunct(_ch)) {
                return PUNCTUATION_BOUND;
            }
            if (isQuotePunct(_ch)) {
                return PUNCTUATION_QUOTE;
            }
            return PUNCTUATION_OTHER;
        }
        if (isOtherLettersDigits(_ch)) {
            return LETTERS_DIGITS_OTHER;
        }
        if (isOtherModifier(_ch)) {
            return SEPARATOR;
        }
        if (_ch <= ' ') {
            if (_ch == ' ') {
                return SPACE;
            }
            if (_ch == '\n') {
                return SPACE;
            }
            if (_ch == '\t') {
                return SPACE;
            }
            return SPACE_OTHER;
        }
        if (isOtherSpace(_ch)) {
            return SPACE_OTHER;
        }
        if (!isUnassigned(_ch)) {
            return OTHER;
        }
        return UNASSIGNED;
    }

    private static int generalDir(char _ch, int _type) {
        if (_type <= LETTER_INSENS_NO_CASE_DEF_DIR) {
            return 0;
        }
        if (_type == LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS) {
            return 13;
        }
        if (_type == LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT) {
            if (_ch <= 1522) {
                return 1;
            }
            if (_ch >= 1994 && _ch <= 2136) {
                return 1;
            }
            if (_ch >= 64285 && _ch <= 64335) {
                return 1;
            }
            return 2;
        }
        if (_type == DIGIT) {
            return 3;
        }
        if (_type == DIGIT_OTHER) {
            if (_ch <= 1641) {
                return 6;
            }
            if (_ch <= 1785) {
                return 3;
            }
            if (_ch <= 1993) {
                return 1;
            }
            if (_ch >= 65296) {
                return 3;
            }
            return 0;
        }
        if (_type == ID_SEP) {
            if (_ch == 160) {
                return 7;
            }
            return 13;
        }
        if (_type == CURRENCY) {
            if (_ch == 1547) {
                return 2;
            }
            if (_ch == 65020) {
                return 2;
            }
            return 5;
        }
        if (_type == SENS_OTHER_LETTER) {
            return 0;
        }
        if (_type == ROMAN_DIGIT) {
            return 0;
        }
        if (_type == DEL_LEFT) {
            return 13;
        }
        if (_type == DEL_RIGHT) {
            return 13;
        }
        return generalDirOther(_ch, _type);
    }

    private static int generalDirOther(char _ch, int _type) {
        if (_type == OPERATOR_LANGUAGE) {
            if (_ch == 33) {
                return 13;
            }
            if (_ch <= 37) {
                return 5;
            }
            if (_ch <= 42) {
                return 13;
            }
            if (_ch <= 45) {
                return 4;
            }
            if (_ch <= 58) {
                return 7;
            }
            return 13;
        }
        if (_type == OPERATOR_SPEC) {
            return 7;
        }
        if (_type == PUNCTUATION) {
            return 13;
        }
        if (_type == QUOTES) {
            return 13;
        }
        if (_type == ESCAPE) {
            return 13;
        }
        if (_type == MATH) {
            return dirOtherPrintSix(_ch);
        }
        if (_type == MODIFIER) {
            return dirOtherPrintFive(_ch);
        }
        if (_type == SYMBOL_MODIFIER) {
            return dirOtherPrintFour(_ch);
        }
        if (_type == SYMBOL_OTHER) {
            return dirOtherPrintTwo(_ch);
        }
        if (_type == PUNCTUATION_CONNECTOR) {
            return dirOtherPrintThree(_ch);
        }
        if (_type == PUNCTUATION_BOUND) {
            return 13;
        }
        if (_type == PUNCTUATION_QUOTE) {
            return 13;
        }
        if (_type == PUNCTUATION_OTHER) {
            return dirOtherPrint(_ch);
        }
        if (_type == LETTERS_DIGITS_OTHER) {
            return dirLettersDigitsOther(_ch);
        }
        if (_type == SEPARATOR) {
            return dirSeparator(_ch);
        }
        if (_type == SPACE) {
            return dirSpace(_ch);
        }
        if (_type == SPACE_OTHER) {
            return dirOtherSpace(_ch);
        }
        if (_type == OTHER) {
            return dirOther(_ch);
        }
        return -1;
    }

    private static int dirSpace(char _ch) {
        if (_ch == 32) {
            return 12;
        }
        if (_ch == 10) {
            return 10;
        }
        return 11;
    }

    private static int dirOtherSpace(char _ch) {
        if (_ch == 11) {
            return 11;
        }
        if (_ch == 12) {
            return 12;
        }
        if (_ch == 13) {
            return 10;
        }
        if (_ch == 31) {
            return 11;
        }
        if (_ch == 133) {
            return 10;
        }
        if (_ch == 8239) {
            return 7;
        }
        if (_ch <= 27) {
            return 9;
        }
        if (_ch <= 30) {
            return 10;
        }
        if (_ch <= 159) {
            return 9;
        }
        return 12;
    }

    private static int dirOtherPrintSix(char _ch) {
        if (_ch == 172) {
            return 13;
        }
        if (_ch == 177) {
            return 5;
        }
        if (_ch == 1544) {
            return 2;
        }
        if (_ch == 8260) {
            return 7;
        }
        if (_ch == 8274) {
            return 13;
        }
        if (_ch == 8316) {
            return 13;
        }
        if (_ch == 8722) {
            return 4;
        }
        if (_ch == 8723) {
            return 5;
        }
        if (_ch == 65291) {
            return 4;
        }
        if (_ch <= 1543) {
            return 13;
        }
        if (_ch <= 8331) {
            return 4;
        }
        if (_ch <= 11084) {
            return 13;
        }
        if (_ch <= 65122) {
            return 4;
        }
        return 13;
    }

    private static int dirOtherPrintFive(char _ch) {
        if (_ch == 173) {
            return 9;
        }
        if (_ch == 176) {
            return 5;
        }
        if (_ch == 185) {
            return 3;
        }
        if (_ch <= 175) {
            return 13;
        }
        if (_ch <= 179) {
            return 3;
        }
        return 13;
    }

    private static int dirOtherPrintFour(char _ch) {
        if (_ch >= 42889 && _ch <= 42890) {
            return 0;
        }
        if (_ch >= 64434 && _ch <= 64449) {
            return 2;
        }
        return 13;
    }

    private static int dirOtherPrintThree(char _ch) {
        if (_ch == 1418) {
            return 13;
        }
        if (_ch == 1470) {
            return 1;
        }
        if (_ch <= 65112) {
            return 13;
        }
        if (_ch <= 65293) {
            return 4;
        }
        return 13;
    }

    private static int dirOtherPrintTwo(char _ch) {
        if (_ch == 1154) {
            return 0;
        }
        if (_ch == 8527) {
            return 0;
        }
        if (_ch == 9900) {
            return 0;
        }
        if (_ch == 9109) {
            return 0;
        }
        if (_ch == 12880) {
            return 13;
        }
        if (_ch == 8494) {
            return 5;
        }
        if (_ch == 43065) {
            return 5;
        }
        if (_ch <= 1769) {
            return 13;
        }
        if (_ch <= 1790) {
            return 2;
        }
        if (_ch >= 2554 && _ch <= 2928) {
            return 0;
        }
        if (_ch >= 3059 && _ch <= 3066) {
            return 13;
        }
        if (_ch >= 3199 && _ch <= 4255) {
            return 0;
        }
        if (_ch >= 5008 && _ch <= 6655) {
            return 13;
        }
        if (_ch >= 7009 && _ch <= 7036) {
            return 0;
        }
        if (_ch >= 8448 && _ch <= 9013) {
            return 13;
        }
        if (_ch >= 9014 && _ch <= 9082) {
            return 0;
        }
        if (_ch >= 9083 && _ch <= 9290) {
            return 13;
        }
        if (_ch >= 9372 && _ch <= 9397) {
            return 0;
        }
        if (_ch >= 9472 && _ch <= 10175) {
            return 13;
        }
        if (_ch >= 10240 && _ch <= 10495) {
            return 0;
        }
        if (_ch >= 11008 && _ch <= 12351) {
            return 13;
        }
        if (_ch >= 12688 && _ch <= 12703) {
            return 0;
        }
        if (_ch >= 12736 && _ch <= 12771) {
            return 13;
        }
        if (_ch >= 12800 && _ch <= 12828) {
            return 0;
        }
        if (_ch >= 12829 && _ch <= 12830) {
            return 13;
        }
        if (_ch >= 12842 && _ch <= 12923) {
            return 0;
        }
        if (_ch >= 12924 && _ch <= 12926) {
            return 13;
        }
        if (_ch >= 12927 && _ch <= 13003) {
            return 0;
        }
        if (_ch >= 13004 && _ch <= 13007) {
            return 13;
        }
        if (_ch >= 13008 && _ch <= 13174) {
            return 0;
        }
        if (_ch >= 13175 && _ch <= 13178) {
            return 13;
        }
        if (_ch >= 13179 && _ch <= 13277) {
            return 0;
        }
        if (_ch >= 13278 && _ch <= 13279) {
            return 13;
        }
        if (_ch >= 13280 && _ch <= 13310) {
            return 0;
        }
        if (_ch >= 13311 && _ch <= 43051) {
            return 13;
        }
        if (_ch >= 43062 && _ch <= 43641) {
            return 0;
        }
        return 13;
    }

    private static int dirOtherPrint(char _ch) {
        if (_ch == 1548) {
            return 7;
        }
        if (_ch == 1642) {
            return 5;
        }
        if (_ch == 1645) {
            return 2;
        }
        if (_ch == 1748) {
            return 2;
        }
        if (_ch == 11632) {
            return 0;
        }
        if (_ch == 65104) {
            return 7;
        }
        if (_ch == 65105) {
            return 13;
        }
        if (_ch == 65106) {
            return 7;
        }
        if (_ch == 65108) {
            return 13;
        }
        if (_ch == 65109) {
            return 7;
        }
        if (_ch == 65119) {
            return 5;
        }
        if (_ch == 65130) {
            return 5;
        }
        if (_ch <= 903) {
            return 13;
        }
        if (_ch <= 1417) {
            return 0;
        }
        if (_ch <= 1524) {
            return 1;
        }
        if (_ch <= 1546) {
            return 5;
        }
        if (_ch <= 1567) {
            return 2;
        }
        if (_ch <= 1644) {
            return 6;
        }
        if (_ch <= 1805) {
            return 2;
        }
        if (_ch <= 2041) {
            return 13;
        }
        if (_ch <= 2142) {
            return 1;
        }
        if (_ch <= 6106) {
            return 0;
        }
        if (_ch <= 6469) {
            return 13;
        }
        if (_ch <= 7379) {
            return 0;
        }
        if (_ch <= 8231) {
            return 13;
        }
        if (_ch <= 8244) {
            return 5;
        }
        if (_ch <= 12539) {
            return 13;
        }
        if (_ch <= 42239) {
            return 0;
        }
        if (_ch <= 42622) {
            return 13;
        }
        if (_ch <= 42743) {
            return 0;
        }
        if (_ch <= 43127) {
            return 13;
        }
        if (_ch <= 44011) {
            return 0;
        }
        if (_ch <= 65282) {
            return 13;
        }
        if (_ch <= 65285) {
            return 5;
        }
        if (_ch <= 65290) {
            return 13;
        }
        if (_ch <= 65306) {
            return 7;
        }
        return 13;
    }
    private static int dirSeparator(char _ch) {
        if (_ch == 8206){
            return 0;
        }
        if (_ch == 1757){
            return 6;
        }
        if (_ch == 1807){
            return 2;
        }
        if (_ch == 8207){
            return 1;
        }
        if (_ch == 8234){
            return 14;
        }
        if (_ch == 8235){
            return 16;
        }
        if (_ch == 8236){
            return 18;
        }
        if (_ch == 8237){
            return 15;
        }
        if (_ch == 8238){
            return 17;
        }
        if (_ch == 65279){
            return 9;
        }
        if (_ch <= 1540) {
            return 6;
        }
        if (_ch <= 8303) {
            return 9;
        }
        return 13;
    }
    private static int dirLettersDigitsOther(char _ch) {
        if (_ch == 11517) {
            return 13;
        }

        if (_ch <= 3075) {
            return 0;
        }
        if (_ch <= 3198) {
            return 13;
        }
        if (_ch <= 5872) {
            return 0;
        }
        if (_ch <= 6137) {
            return 13;
        }
        if (_ch <= 6618) {
            return 0;
        }
        if (_ch <= 8329) {
            return 3;
        }
        if (_ch <= 8543) {
            return 13;
        }
        if (_ch <= 8584) {
            return 0;
        }
        if (_ch <= 9351) {
            return 13;
        }
        if (_ch <= 9371) {
            return 3;
        }
        if (_ch <= 10131) {
            return 13;
        }
        if (_ch <= 12879) {
            return 0;
        }
        if (_ch <= 12895) {
            return 13;
        }
        if (_ch <= 12937) {
            return 0;
        }
        if (_ch <= 12991) {
            return 13;
        }
        return 0;
    }
    private static int dirOther(char _ch) {
        if (_ch == 2307){
            return 0;
        }
        if (_ch == 2363){
            return 0;
        }
        if (_ch == 2519){
            return 0;
        }
        if (_ch == 2563){
            return 0;
        }
        if (_ch == 2691){
            return 0;
        }
        if (_ch == 2878){
            return 0;
        }
        if (_ch == 2880){
            return 0;
        }
        if (_ch == 2903){
            return 0;
        }
        if (_ch == 3415){
            return 0;
        }
        if (_ch == 3967){
            return 0;
        }
        if (_ch == 4145){
            return 0;
        }
        if (_ch == 4152){
            return 0;
        }
        if (_ch == 6070){
            return 0;
        }
        if (_ch == 6743){
            return 0;
        }
        if (_ch == 6753){
            return 0;
        }
        if (_ch == 6916){
            return 0;
        }
        if (_ch == 6965){
            return 0;
        }
        if (_ch == 6971){
            return 0;
        }
        if (_ch == 7082){
            return 0;
        }
        if (_ch == 7143){
            return 0;
        }
        if (_ch == 7150){
            return 0;
        }
        if (_ch == 7393){
            return 0;
        }
        if (_ch == 43395){
            return 0;
        }
        if (_ch == 43755){
            return 0;
        }
        if (_ch == 2362){
            return 8;
        }
        if (_ch == 2364){
            return 8;
        }
        if (_ch == 2381){
            return 8;
        }
        if (_ch == 2492){
            return 8;
        }
        if (_ch == 2509){
            return 8;
        }
        if (_ch == 2620){
            return 8;
        }
        if (_ch == 2748){
            return 8;
        }
        if (_ch == 2876){
            return 8;
        }
        if (_ch == 2879){
            return 8;
        }
        if (_ch == 2946){
            return 8;
        }
        if (_ch == 3008){
            return 8;
        }
        if (_ch == 3021){
            return 8;
        }
        if (_ch == 3260){
            return 8;
        }
        if (_ch == 3405){
            return 8;
        }
        if (_ch == 3530){
            return 8;
        }
        if (_ch == 4237){
            return 8;
        }
        if (_ch == 6086){
            return 8;
        }
        if (_ch == 6450){
            return 8;
        }
        if (_ch == 6742){
            return 8;
        }
        if (_ch == 6754){
            return 8;
        }
        if (_ch == 6964){
            return 8;
        }
        if (_ch == 6972){
            return 8;
        }
        if (_ch == 6978){
            return 8;
        }
        if (_ch == 7083){
            return 8;
        }
        if (_ch == 7142){
            return 8;
        }
        if (_ch == 7149){
            return 8;
        }
        if (_ch == 43443){
            return 8;
        }
        if (_ch == 43452){
            return 8;
        }
        if (_ch == 43766){
            return 8;
        }
        if (_ch == 44005){
            return 8;
        }
        if (_ch == 44008){
            return 8;
        }
        if (_ch == 44013){
            return 8;
        }
        if (_ch == 8232){
            return 12;
        }
        if (_ch == 8233){
            return 10;
        }
        if (_ch <= 1756) {
            return 8;
        }
        if (_ch <= 1773) {
            return 8;
        }
        if (_ch <= 2306) {
            return 8;
        }
        if (_ch <= 2368) {
            return 0;
        }
        if (_ch <= 2376) {
            return 8;
        }
        if (_ch <= 2380) {
            return 0;
        }
        if (_ch <= 2383) {
            return 0;
        }
        if (_ch <= 2433) {
            return 8;
        }
        if (_ch <= 2435) {
            return 0;
        }
        if (_ch <= 2496) {
            return 0;
        }
        if (_ch <= 2500) {
            return 8;
        }
        if (_ch <= 2508) {
            return 0;
        }
        if (_ch <= 2562) {
            return 8;
        }
        if (_ch <= 2624) {
            return 0;
        }
        if (_ch <= 2690) {
            return 8;
        }
        if (_ch <= 2752) {
            return 0;
        }
        if (_ch <= 2760) {
            return 8;
        }
        if (_ch <= 2764) {
            return 0;
        }
        if (_ch <= 2817) {
            return 8;
        }
        if (_ch <= 2819) {
            return 0;
        }
        if (_ch <= 2884) {
            return 8;
        }
        if (_ch <= 2892) {
            return 0;
        }
        if (_ch <= 2902) {
            return 8;
        }
        if (_ch <= 2915) {
            return 8;
        }
        if (_ch <= 3075) {
            return 0;
        }
        if (_ch <= 3136) {
            return 8;
        }
        if (_ch <= 3140) {
            return 0;
        }
        if (_ch <= 3171) {
            return 8;
        }
        if (_ch <= 3203) {
            return 0;
        }
        if (_ch <= 3275) {
            return 0;
        }
        if (_ch <= 3277) {
            return 8;
        }
        if (_ch <= 3286) {
            return 0;
        }
        if (_ch <= 3299) {
            return 8;
        }
        if (_ch <= 3392) {
            return 0;
        }
        if (_ch <= 3396) {
            return 8;
        }
        if (_ch <= 3404) {
            return 0;
        }
        if (_ch <= 3427) {
            return 8;
        }
        if (_ch <= 3459) {
            return 0;
        }
        if (_ch <= 3537) {
            return 0;
        }
        if (_ch <= 3542) {
            return 8;
        }
        if (_ch <= 3571) {
            return 0;
        }
        if (_ch <= 3897) {
            return 8;
        }
        if (_ch <= 3903) {
            return 0;
        }
        if (_ch <= 3966) {
            return 8;
        }
        if (_ch <= 4038) {
            return 8;
        }
        if (_ch <= 4140) {
            return 0;
        }
        if (_ch <= 4154) {
            return 8;
        }
        if (_ch <= 4156) {
            return 0;
        }
        if (_ch <= 4158) {
            return 8;
        }
        if (_ch <= 4183) {
            return 0;
        }
        if (_ch <= 4192) {
            return 8;
        }
        if (_ch <= 4205) {
            return 0;
        }
        if (_ch <= 4226) {
            return 8;
        }
        if (_ch <= 4228) {
            return 0;
        }
        if (_ch <= 4230) {
            return 8;
        }
        if (_ch <= 4252) {
            return 0;
        }
        if (_ch <= 6069) {
            return 8;
        }
        if (_ch <= 6077) {
            return 8;
        }
        if (_ch <= 6085) {
            return 0;
        }
        if (_ch <= 6088) {
            return 0;
        }
        if (_ch <= 6434) {
            return 8;
        }
        if (_ch <= 6438) {
            return 0;
        }
        if (_ch <= 6440) {
            return 8;
        }
        if (_ch <= 6449) {
            return 0;
        }
        if (_ch <= 6456) {
            return 0;
        }
        if (_ch <= 6459) {
            return 8;
        }
        if (_ch <= 6618) {
            return 0;
        }
        if (_ch <= 6680) {
            return 8;
        }
        if (_ch <= 6741) {
            return 0;
        }
        if (_ch <= 6752) {
            return 8;
        }
        if (_ch <= 6756) {
            return 0;
        }
        if (_ch <= 6764) {
            return 8;
        }
        if (_ch <= 6770) {
            return 0;
        }
        if (_ch <= 6915) {
            return 8;
        }
        if (_ch <= 6970) {
            return 8;
        }
        if (_ch <= 6977) {
            return 0;
        }
        if (_ch <= 6980) {
            return 0;
        }
        if (_ch <= 7041) {
            return 8;
        }
        if (_ch <= 7073) {
            return 0;
        }
        if (_ch <= 7077) {
            return 8;
        }
        if (_ch <= 7079) {
            return 0;
        }
        if (_ch <= 7081) {
            return 8;
        }
        if (_ch <= 7085) {
            return 0;
        }
        if (_ch <= 7145) {
            return 8;
        }
        if (_ch <= 7148) {
            return 0;
        }
        if (_ch <= 7153) {
            return 8;
        }
        if (_ch <= 7211) {
            return 0;
        }
        if (_ch <= 7219) {
            return 8;
        }
        if (_ch <= 7221) {
            return 0;
        }
        if (_ch <= 7392) {
            return 8;
        }
        if (_ch <= 7405) {
            return 8;
        }
        if (_ch <= 7411) {
            return 0;
        }
        if (_ch <= 12333) {
            return 8;
        }
        if (_ch <= 12346) {
            return 0;
        }
        if (_ch <= 43019) {
            return 8;
        }
        if (_ch <= 43044) {
            return 0;
        }
        if (_ch <= 43046) {
            return 8;
        }
        if (_ch <= 43203) {
            return 0;
        }
        if (_ch <= 43345) {
            return 8;
        }
        if (_ch <= 43347) {
            return 0;
        }
        if (_ch <= 43394) {
            return 8;
        }
        if (_ch <= 43445) {
            return 0;
        }
        if (_ch <= 43449) {
            return 8;
        }
        if (_ch <= 43451) {
            return 0;
        }
        if (_ch <= 43456) {
            return 0;
        }
        if (_ch <= 43566) {
            return 8;
        }
        if (_ch <= 43568) {
            return 0;
        }
        if (_ch <= 43570) {
            return 8;
        }
        if (_ch <= 43572) {
            return 0;
        }
        if (_ch <= 43596) {
            return 8;
        }
        if (_ch <= 43643) {
            return 0;
        }
        if (_ch <= 43713) {
            return 8;
        }
        if (_ch <= 43757) {
            return 8;
        }
        if (_ch <= 63743) {
            return 0;
        }
        return 8;
    }

    private static boolean isOtherDigit(char _ch) {
        for (int i: NumberUtil.wrapIntArray(1632,1776,1984,2406,2534,2662,
                2790,2918,3046,3174,3302,3430,3664,3792,3872,4160,4240,6112,6160,
                6470,6608,6784,6800,6992,7088,7232,7248,42528,43216,43264,43472,
                43600,44016,65296)) {
            if (_ch >= i && _ch <= i + 9) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOtherPonctuation(char _ch) {
        if (_ch == 161) {
            return true;
        }
        if (_ch == 167) {
            return true;
        }
        if (_ch == 171) {
            return true;
        }
        if (_ch == 187) {
            return true;
        }
        if (_ch == 191) {
            return true;
        }
        if (_ch == 894) {
            return true;
        }
        if (_ch == 903) {
            return true;
        }
        if (_ch >= 1370&& _ch <= 1375) {
            return true;
        }
        if (_ch >= 1642&& _ch <= 1645) {
            return true;
        }
        if (_ch >= 1792&& _ch <= 1805) {
            return true;
        }
        if (_ch >= 2039&& _ch <= 2041) {
            return true;
        }
        if (_ch >= 2096&& _ch <= 2110) {
            return true;
        }
        if (_ch >= 3844&& _ch <= 3858) {
            return true;
        }
        if (_ch >= 3898&& _ch <= 3901) {
            return true;
        }
        if (_ch >= 4048&& _ch <= 4052) {
            return true;
        }
        if (_ch >= 4170&& _ch <= 4175) {
            return true;
        }
        if (_ch >= 4960&& _ch <= 4968) {
            return true;
        }
        if (_ch >= 5867&& _ch <= 5869) {
            return true;
        }
        if (_ch >= 6100&& _ch <= 6106) {
            return true;
        }
        if (_ch >= 6144&& _ch <= 6154) {
            return true;
        }
        if (_ch >= 6818&& _ch <= 6822) {
            return true;
        }
        if (_ch >= 6824&& _ch <= 6829) {
            return true;
        }
        if (_ch >= 7002&& _ch <= 7008) {
            return true;
        }
        if (_ch >= 7164&& _ch <= 7167) {
            return true;
        }
        if (_ch >= 7227&& _ch <= 7231) {
            return true;
        }
        if (_ch >= 7360&& _ch <= 7367) {
            return true;
        }
        if (_ch >= 8208&& _ch <= 8231) {
            return true;
        }
        if (_ch >= 8240&& _ch <= 8259) {
            return true;
        }
        if (_ch >= 8261&& _ch <= 8273) {
            return true;
        }
        if (_ch >= 8275&& _ch <= 8286) {
            return true;
        }
        if (_ch >= 10088&& _ch <= 10101) {
            return true;
        }
        if (_ch >= 10214&& _ch <= 10223) {
            return true;
        }
        if (_ch >= 10627&& _ch <= 10648) {
            return true;
        }
        if (_ch >= 10712&& _ch <= 10715) {
            return true;
        }
        if (_ch >= 11513&& _ch <= 11516) {
            return true;
        }
        if (_ch >= 11776&& _ch <= 11822) {
            return true;
        }
        if (_ch >= 11824&& _ch <= 11835) {
            return true;
        }
        if (_ch >= 12289&& _ch <= 12291) {
            return true;
        }
        if (_ch >= 12296&& _ch <= 12305) {
            return true;
        }
        if (_ch >= 12308&& _ch <= 12319) {
            return true;
        }
        if (_ch >= 42738&& _ch <= 42743) {
            return true;
        }
        if (_ch >= 43124&& _ch <= 43127) {
            return true;
        }
        if (_ch >= 43457&& _ch <= 43469) {
            return true;
        }
        if (_ch >= 43612&& _ch <= 43615) {
            return true;
        }
        if (_ch >= 65040&& _ch <= 65049) {
            return true;
        }
        if (_ch >= 65072&& _ch <= 65106) {
            return true;
        }
        if (_ch >= 65108&& _ch <= 65123) {
            return true;
        }
        if (_ch >= 65281&& _ch <= 65283) {
            return true;
        }
        if (_ch >= 65285&& _ch <= 65290) {
            return true;
        }
        if (_ch >= 65292&& _ch <= 65295) {
            return true;
        }
        if (_ch >= 65339&& _ch <= 65341) {
            return true;
        }
        if (_ch >= 65375&& _ch <= 65381) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(182,1417,1523,1545,
                1548,1566,2404,3674,4057,5741,5787,5941,6468,6686,6816,7294,8317,8333,9001,10181,
                10748,11518,42238,42510,43214,43256,43310,43486,43258,43742,43760,64830,65130,65306,65311)) {
            if (_ch >= i && _ch <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(1470,1472,1475,1478,1563,1748,
                2142,2416,2800,3572,3663,3860,3973,4347,
                5120,7379,11632,12336,12349,12448,12539,
                42509,42611,42622,43359,44011,65128,65343,65371,65373)) {
            if (_ch == i) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOtherSymbol(char _ch) {
        if (_ch >= 706&& _ch <= 709) {
            return true;
        }
        if (_ch >= 722&& _ch <= 735) {
            return true;
        }
        if (_ch >= 741&& _ch <= 747) {
            return true;
        }
        if (_ch >= 751&& _ch <= 767) {
            return true;
        }
        if (_ch >= 3059&& _ch <= 3064) {
            return true;
        }
        if (_ch >= 3841&& _ch <= 3843) {
            return true;
        }
        if (_ch >= 3861&& _ch <= 3863) {
            return true;
        }
        if (_ch >= 3866&& _ch <= 3871) {
            return true;
        }
        if (_ch >= 4030&& _ch <= 4037) {
            return true;
        }
        if (_ch >= 4039&& _ch <= 4044) {
            return true;
        }
        if (_ch >= 4053&& _ch <= 4056) {
            return true;
        }
        if (_ch >= 5008&& _ch <= 5017) {
            return true;
        }
        if (_ch >= 6622&& _ch <= 6655) {
            return true;
        }
        if (_ch >= 7009&& _ch <= 7018) {
            return true;
        }
        if (_ch >= 7028&& _ch <= 7036) {
            return true;
        }
        if (_ch >= 8127&& _ch <= 8129) {
            return true;
        }
        if (_ch >= 8141&& _ch <= 8143) {
            return true;
        }
        if (_ch >= 8157&& _ch <= 8159) {
            return true;
        }
        if (_ch >= 8173&& _ch <= 8175) {
            return true;
        }
        if (_ch >= 8451&& _ch <= 8454) {
            return true;
        }
        if (_ch >= 8478&& _ch <= 8483) {
            return true;
        }
        if (_ch >= 8598&& _ch <= 8601) {
            return true;
        }
        if (_ch >= 9140&& _ch <= 9179) {
            return true;
        }
        if (_ch >= 9186&& _ch <= 9203) {
            return true;
        }
        if (_ch >= 9216&& _ch <= 9254) {
            return true;
        }
        if (_ch >= 9280&& _ch <= 9290) {
            return true;
        }
        if (_ch >= 8604&& _ch <= 8607) {
            return true;
        }
        if (_ch >= 8615&& _ch <= 8621) {
            return true;
        }
        if (_ch >= 8623&& _ch <= 8653) {
            return true;
        }
        if (_ch >= 8661&& _ch <= 8691) {
            return true;
        }
        if (_ch >= 8960&& _ch <= 8967) {
            return true;
        }
        if (_ch >= 8972&& _ch <= 8991) {
            return true;
        }
        if (_ch >= 8994&& _ch <= 9000) {
            return true;
        }
        if (_ch >= 9003&& _ch <= 9083) {
            return true;
        }
        if (_ch >= 9085&& _ch <= 9114) {
            return true;
        }
        if (_ch >= 9372&& _ch <= 9449) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(9655,9665,9839,9984)) {
            if (_ch == i) {
                return false;
            }
        }
        if (_ch >= 12246&& _ch <= 12271) {
            return false;
        }
        if (_ch >= 9472&& _ch <= 10087) {
            return true;
        }
        if (_ch >= 10132&& _ch <= 10175) {
            return true;
        }
        if (_ch >= 10240&& _ch <= 10495) {
            return true;
        }
        if (_ch >= 11008&& _ch <= 11055) {
            return true;
        }
        if (_ch >= 11088&& _ch <= 11097) {
            return true;
        }
        if (_ch >= 11493&& _ch <= 11498) {
            return true;
        }
        if (_ch >= 11904&& _ch <= 11929) {
            return true;
        }
        if (_ch >= 11931&& _ch <= 12019) {
            return true;
        }
        if (_ch >= 12032&& _ch <= 12283) {
            return true;
        }
        if (_ch >= 12694&& _ch <= 12703) {
            return true;
        }
        if (_ch >= 12736&& _ch <= 12771) {
            return true;
        }
        if (_ch >= 12800&& _ch <= 12830) {
            return true;
        }
        if (_ch >= 12842&& _ch <= 12871) {
            return true;
        }
        if (_ch >= 12896&& _ch <= 12927) {
            return true;
        }
        if (_ch >= 12938&& _ch <= 12976) {
            return true;
        }
        if (_ch >= 12992&& _ch <= 13054) {
            return true;
        }
        if (_ch >= 13056&& _ch <= 13311) {
            return true;
        }
        if (_ch >= 19904&& _ch <= 19967) {
            return true;
        }
        if (_ch >= 42128&& _ch <= 42182) {
            return true;
        }
        if (_ch >= 42752&& _ch <= 42774) {
            return true;
        }
        if (_ch >= 43048&& _ch <= 43051) {
            return true;
        }
        if (_ch >= 43639&& _ch <= 43641) {
            return true;
        }
        if (_ch >= 64434&& _ch <= 64449) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(900,1550,1789,
                4046,4254,8189,8448,8470,8456,8506,8597,8524,8609,8612,8656,
                11077,12306,12342,12350,12443,12688,42784,42889,43062,
                65507,65517,65532)) {
            if (_ch >= i && _ch <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(749,885,1154,1758,1769,
                2038,2554,2928,3066,3199,3449,3859,3892,
                3894,3896,6464,8125,8468,8485,8487,8489,
                8494,8522,8527,8659,12292,12320,12880,43065,65021,65342,
                65344,65512)) {
            if (_ch == i) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCurrencyChar(char _ch) {
        if (_ch >= 162&& _ch <= 165) {
            return true;
        }
        if (_ch >= 8352&& _ch <= 8378) {
            return true;
        }
        for (int i: NumberUtil.wrapIntArray(2546,65504,65509)) {
            if (_ch >= i && _ch <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(36,1423,1547,2555,2801,
                3065,3647,6107,
                43064,65020,65129,65284)) {
            if (_ch == i) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOtherLettersDigits(char _ch) {
        if (_ch >= 12690 && _ch <= 12730) {
            return true;
        }
        if (_ch >= 12832 && _ch <= 12991) {
            return true;
        }
        if (_ch >= 43056 && _ch <= 43061) {
            return true;
        }
        if (_ch == 8585) {
            return true;
        }
        if (_ch >= 9312 && _ch <= 9983) {
            return true;
        }
        if (_ch >= 9985 && _ch <= 10131) {
            return true;
        }
        if (_ch >= 2548 && _ch <= 2553) {
            return true;
        }
        if (_ch >= 2930 && _ch <= 2935) {
            return true;
        }
        if (_ch >= 3056 && _ch <= 3058) {
            return true;
        }
        if (_ch >= 3192 && _ch <= 3198) {
            return true;
        }
        if (_ch >= 3440 && _ch <= 3445) {
            return true;
        }
        if (_ch >= 3882 && _ch <= 3891) {
            return true;
        }
        if (_ch >= 4969 && _ch <= 4988) {
            return true;
        }
        if (_ch >= 11517 && _ch <= 11517) {
            return true;
        }
        if (_ch >= 8528 && _ch <= 8543) {
            return true;
        }
        if (_ch >= 8304 && _ch <= 8305) {
            return true;
        }
        if (_ch >= 8308 && _ch <= 8329) {
            return true;
        }
        if (_ch >= 6128 && _ch <= 6137) {
            return true;
        }
        if (_ch >= 6618 && _ch <= 6618) {
            return true;
        }
        if (_ch >= 8576 && _ch <= 8584) {
            return true;
        }
        if (_ch >= 12295 && _ch <= 12329) {
            return true;
        }
        if (_ch >= 42726 && _ch <= 42735) {
            return true;
        }
        if (_ch >= 12344 && _ch <= 12346) {
            return true;
        }
        return _ch >= 5870 && _ch <= 5872;
    }
    private static boolean isOtherModifier(char _ch) {
        if (_ch == 1757 || _ch == 1807) {
            return true;
        }
        if (_ch >= 1536 && _ch <= 1540) {
            return true;
        }
        if (_ch >= 8203 && _ch <= 8207) {
            return true;
        }
        if (_ch >= 8234&&_ch<=8238) {
            return true;
        }
        if (_ch >= 8288&&_ch<=8292) {
            return true;
        }
        if (_ch >= 8298&&_ch<=8303) {
            return true;
        }
        if (_ch >= 65529&&_ch<=65531) {
            return true;
        }
        return _ch >= 65279 && _ch <= 65279;
    }
    private static boolean isOtherSpace(char _ch) {
        if (_ch == 160) {
            return true;
        }
        if (_ch == 5760) {
            return true;
        }
        if (_ch == 6158) {
            return true;
        }
        if (_ch == 12288) {
            return true;
        }
        if (_ch == 8239) {
            return true;
        }
        if (_ch == 8287) {
            return true;
        }
        if (_ch >= 127 && _ch <= 159) {
            return true;
        }
        return _ch >= 8192 && _ch <= 8202;
    }

    private static boolean isUnassigned(char _ch) {
        if (_ch >= 888 && _ch <= 889) {
            return true;
        }
        if (_ch >= 895 && _ch <= 899) {
            return true;
        }
        if (_ch == 907) {
            return true;
        }
        if (_ch == 909) {
            return true;
        }
        if (_ch == 930) {
            return true;
        }
        if (_ch >= 1320 && _ch <= 1328) {
            return true;
        }
        if (_ch >= 1367 && _ch <= 1368) {
            return true;
        }
        if (_ch == 1376) {
            return true;
        }
        if (_ch == 1416) {
            return true;
        }
        if (_ch >= 1419 && _ch <= 1422) {
            return true;
        }
        if (_ch == 1424) {
            return true;
        }
        if (_ch >= 1480 && _ch <= 1487) {
            return true;
        }
        if (_ch >= 1515 && _ch <= 1519) {
            return true;
        }
        if (_ch >= 1525 && _ch <= 1535) {
            return true;
        }
        if (_ch == 1541) {
            return true;
        }
        if (_ch >= 1564 && _ch <= 1565) {
            return true;
        }
        if (_ch == 1806) {
            return true;
        }
        if (_ch >= 1867 && _ch <= 1868) {
            return true;
        }
        if (_ch >= 1970 && _ch <= 1983) {
            return true;
        }
        if (_ch >= 2043 && _ch <= 2047) {
            return true;
        }
        if (_ch >= 2094 && _ch <= 2095) {
            return true;
        }
        if (_ch == 2111) {
            return true;
        }
        if (_ch >= 2140 && _ch <= 2141) {
            return true;
        }
        if (_ch >= 2143 && _ch <= 2207) {
            return true;
        }
        if (_ch == 2209) {
            return true;
        }
        if (_ch >= 2221 && _ch <= 2275) {
            return true;
        }
        if (_ch == 2303) {
            return true;
        }
        if (_ch == 2424) {
            return true;
        }
        if (_ch == 2432) {
            return true;
        }
        if (_ch == 2436) {
            return true;
        }
        if (_ch >= 2445 && _ch <= 2446) {
            return true;
        }
        if (_ch >= 2449 && _ch <= 2450) {
            return true;
        }
        if (_ch == 2473) {
            return true;
        }
        if (_ch == 2481) {
            return true;
        }
        if (_ch >= 2483 && _ch <= 2485) {
            return true;
        }
        if (_ch >= 2490 && _ch <= 2491) {
            return true;
        }
        if (_ch >= 2501 && _ch <= 2502) {
            return true;
        }
        if (_ch >= 2505 && _ch <= 2506) {
            return true;
        }
        if (_ch >= 2511 && _ch <= 2518) {
            return true;
        }
        if (_ch >= 2520 && _ch <= 2523) {
            return true;
        }
        if (_ch == 2526) {
            return true;
        }
        if (_ch >= 2532 && _ch <= 2533) {
            return true;
        }
        if (_ch >= 2556 && _ch <= 2560) {
            return true;
        }
        if (_ch == 2564) {
            return true;
        }
        if (_ch >= 2571 && _ch <= 2574) {
            return true;
        }
        if (_ch >= 2577 && _ch <= 2578) {
            return true;
        }
        if (_ch == 2601) {
            return true;
        }
        if (_ch == 2609) {
            return true;
        }
        if (_ch == 2612) {
            return true;
        }
        if (_ch == 2615) {
            return true;
        }
        if (_ch >= 2618 && _ch <= 2619) {
            return true;
        }
        if (_ch == 2621) {
            return true;
        }
        if (_ch >= 2627 && _ch <= 2630) {
            return true;
        }
        if (_ch >= 2633 && _ch <= 2634) {
            return true;
        }
        if (_ch >= 2638 && _ch <= 2640) {
            return true;
        }
        if (_ch >= 2642 && _ch <= 2648) {
            return true;
        }
        if (_ch == 2653) {
            return true;
        }
        if (_ch >= 2655 && _ch <= 2661) {
            return true;
        }
        if (_ch >= 2678 && _ch <= 2688) {
            return true;
        }
        if (_ch == 2692) {
            return true;
        }
        if (_ch == 2702) {
            return true;
        }
        if (_ch == 2706) {
            return true;
        }
        if (_ch == 2729) {
            return true;
        }
        if (_ch == 2737) {
            return true;
        }
        if (_ch == 2740) {
            return true;
        }
        if (_ch >= 2746 && _ch <= 2747) {
            return true;
        }
        if (_ch == 2758) {
            return true;
        }
        if (_ch == 2762) {
            return true;
        }
        if (_ch >= 2766 && _ch <= 2767) {
            return true;
        }
        if (_ch >= 2769 && _ch <= 2783) {
            return true;
        }
        if (_ch >= 2788 && _ch <= 2789) {
            return true;
        }
        if (_ch >= 2802 && _ch <= 2816) {
            return true;
        }
        if (_ch == 2820) {
            return true;
        }
        if (_ch >= 2829 && _ch <= 2830) {
            return true;
        }
        if (_ch >= 2833 && _ch <= 2834) {
            return true;
        }
        if (_ch == 2857) {
            return true;
        }
        if (_ch == 2865) {
            return true;
        }
        if (_ch == 2868) {
            return true;
        }
        if (_ch >= 2874 && _ch <= 2875) {
            return true;
        }
        if (_ch >= 2885 && _ch <= 2886) {
            return true;
        }
        if (_ch >= 2889 && _ch <= 2890) {
            return true;
        }
        if (_ch >= 2894 && _ch <= 2901) {
            return true;
        }
        if (_ch >= 2904 && _ch <= 2907) {
            return true;
        }
        if (_ch == 2910) {
            return true;
        }
        if (_ch >= 2916 && _ch <= 2917) {
            return true;
        }
        if (_ch >= 2936 && _ch <= 2945) {
            return true;
        }
        if (_ch == 2948) {
            return true;
        }
        if (_ch >= 2955 && _ch <= 2957) {
            return true;
        }
        if (_ch == 2961) {
            return true;
        }
        if (_ch >= 2966 && _ch <= 2968) {
            return true;
        }
        if (_ch == 2971) {
            return true;
        }
        if (_ch == 2973) {
            return true;
        }
        if (_ch >= 2976 && _ch <= 2978) {
            return true;
        }
        if (_ch >= 2981 && _ch <= 2983) {
            return true;
        }
        if (_ch >= 2987 && _ch <= 2989) {
            return true;
        }
        if (_ch >= 3002 && _ch <= 3005) {
            return true;
        }
        if (_ch >= 3011 && _ch <= 3013) {
            return true;
        }
        if (_ch == 3017) {
            return true;
        }
        if (_ch >= 3022 && _ch <= 3023) {
            return true;
        }
        if (_ch >= 3025 && _ch <= 3030) {
            return true;
        }
        if (_ch >= 3032 && _ch <= 3045) {
            return true;
        }
        if (_ch >= 3067 && _ch <= 3072) {
            return true;
        }
        if (_ch == 3076) {
            return true;
        }
        if (_ch == 3085) {
            return true;
        }
        if (_ch == 3089) {
            return true;
        }
        if (_ch == 3113) {
            return true;
        }
        if (_ch == 3124) {
            return true;
        }
        if (_ch >= 3130 && _ch <= 3132) {
            return true;
        }
        if (_ch == 3141) {
            return true;
        }
        if (_ch == 3145) {
            return true;
        }
        if (_ch >= 3150 && _ch <= 3156) {
            return true;
        }
        if (_ch == 3159) {
            return true;
        }
        if (_ch >= 3162 && _ch <= 3167) {
            return true;
        }
        if (_ch >= 3172 && _ch <= 3173) {
            return true;
        }
        if (_ch >= 3184 && _ch <= 3191) {
            return true;
        }
        if (_ch >= 3200 && _ch <= 3201) {
            return true;
        }
        if (_ch == 3204) {
            return true;
        }
        if (_ch == 3213) {
            return true;
        }
        if (_ch == 3217) {
            return true;
        }
        if (_ch == 3241) {
            return true;
        }
        if (_ch == 3252) {
            return true;
        }
        if (_ch >= 3258 && _ch <= 3259) {
            return true;
        }
        if (_ch == 3269) {
            return true;
        }
        if (_ch == 3273) {
            return true;
        }
        if (_ch >= 3278 && _ch <= 3284) {
            return true;
        }
        if (_ch >= 3287 && _ch <= 3293) {
            return true;
        }
        if (_ch == 3295) {
            return true;
        }
        if (_ch >= 3300 && _ch <= 3301) {
            return true;
        }
        if (_ch == 3312) {
            return true;
        }
        if (_ch >= 3315 && _ch <= 3329) {
            return true;
        }
        if (_ch == 3332) {
            return true;
        }
        if (_ch == 3341) {
            return true;
        }
        if (_ch == 3345) {
            return true;
        }
        if (_ch >= 3387 && _ch <= 3388) {
            return true;
        }
        if (_ch == 3397) {
            return true;
        }
        if (_ch == 3401) {
            return true;
        }
        if (_ch >= 3407 && _ch <= 3414) {
            return true;
        }
        if (_ch >= 3416 && _ch <= 3423) {
            return true;
        }
        if (_ch >= 3428 && _ch <= 3429) {
            return true;
        }
        if (_ch >= 3446 && _ch <= 3448) {
            return true;
        }
        if (_ch >= 3456 && _ch <= 3457) {
            return true;
        }
        if (_ch == 3460) {
            return true;
        }
        if (_ch >= 3479 && _ch <= 3481) {
            return true;
        }
        if (_ch == 3506) {
            return true;
        }
        if (_ch == 3516) {
            return true;
        }
        if (_ch >= 3518 && _ch <= 3519) {
            return true;
        }
        if (_ch >= 3527 && _ch <= 3529) {
            return true;
        }
        if (_ch >= 3531 && _ch <= 3534) {
            return true;
        }
        if (_ch == 3541) {
            return true;
        }
        if (_ch == 3543) {
            return true;
        }
        if (_ch >= 3552 && _ch <= 3569) {
            return true;
        }
        if (_ch >= 3573 && _ch <= 3584) {
            return true;
        }
        if (_ch >= 3643 && _ch <= 3646) {
            return true;
        }
        if (_ch >= 3676 && _ch <= 3712) {
            return true;
        }
        if (_ch == 3715) {
            return true;
        }
        if (_ch >= 3717 && _ch <= 3718) {
            return true;
        }
        if (_ch == 3721) {
            return true;
        }
        if (_ch >= 3723 && _ch <= 3724) {
            return true;
        }
        if (_ch >= 3726 && _ch <= 3731) {
            return true;
        }
        if (_ch == 3736) {
            return true;
        }
        if (_ch == 3744) {
            return true;
        }
        if (_ch == 3748) {
            return true;
        }
        if (_ch == 3750) {
            return true;
        }
        if (_ch >= 3752 && _ch <= 3753) {
            return true;
        }
        if (_ch == 3756) {
            return true;
        }
        if (_ch == 3770) {
            return true;
        }
        if (_ch >= 3774 && _ch <= 3775) {
            return true;
        }
        if (_ch == 3781) {
            return true;
        }
        if (_ch == 3783) {
            return true;
        }
        if (_ch >= 3790 && _ch <= 3791) {
            return true;
        }
        if (_ch >= 3802 && _ch <= 3803) {
            return true;
        }
        if (_ch >= 3808 && _ch <= 3839) {
            return true;
        }
        if (_ch == 3912) {
            return true;
        }
        if (_ch >= 3949 && _ch <= 3952) {
            return true;
        }
        if (_ch == 3992) {
            return true;
        }
        if (_ch == 4029) {
            return true;
        }
        if (_ch == 4045) {
            return true;
        }
        if (_ch >= 4059 && _ch <= 4095) {
            return true;
        }
        if (_ch == 4294) {
            return true;
        }
        if (_ch >= 4296 && _ch <= 4300) {
            return true;
        }
        if (_ch >= 4302 && _ch <= 4303) {
            return true;
        }
        if (_ch == 4681) {
            return true;
        }
        if (_ch >= 4686 && _ch <= 4687) {
            return true;
        }
        if (_ch == 4695) {
            return true;
        }
        if (_ch == 4697) {
            return true;
        }
        if (_ch >= 4702 && _ch <= 4703) {
            return true;
        }
        if (_ch == 4745) {
            return true;
        }
        if (_ch >= 4750 && _ch <= 4751) {
            return true;
        }
        if (_ch == 4785) {
            return true;
        }
        if (_ch >= 4790 && _ch <= 4791) {
            return true;
        }
        if (_ch == 4799) {
            return true;
        }
        if (_ch == 4801) {
            return true;
        }
        if (_ch >= 4806 && _ch <= 4807) {
            return true;
        }
        if (_ch == 4823) {
            return true;
        }
        if (_ch == 4881) {
            return true;
        }
        if (_ch >= 4886 && _ch <= 4887) {
            return true;
        }
        if (_ch >= 4955 && _ch <= 4956) {
            return true;
        }
        if (_ch >= 4989 && _ch <= 4991) {
            return true;
        }
        if (_ch >= 5018 && _ch <= 5023) {
            return true;
        }
        if (_ch >= 5109 && _ch <= 5119) {
            return true;
        }
        if (_ch >= 5789 && _ch <= 5791) {
            return true;
        }
        if (_ch >= 5873 && _ch <= 5887) {
            return true;
        }
        if (_ch == 5901) {
            return true;
        }
        if (_ch >= 5909 && _ch <= 5919) {
            return true;
        }
        if (_ch >= 5943 && _ch <= 5951) {
            return true;
        }
        if (_ch >= 5972 && _ch <= 5983) {
            return true;
        }
        if (_ch == 5997) {
            return true;
        }
        if (_ch == 6001) {
            return true;
        }
        if (_ch >= 6004 && _ch <= 6015) {
            return true;
        }
        if (_ch >= 6110 && _ch <= 6111) {
            return true;
        }
        if (_ch >= 6122 && _ch <= 6127) {
            return true;
        }
        if (_ch >= 6138 && _ch <= 6143) {
            return true;
        }
        if (_ch == 6159) {
            return true;
        }
        if (_ch >= 6170 && _ch <= 6175) {
            return true;
        }
        if (_ch >= 6264 && _ch <= 6271) {
            return true;
        }
        if (_ch >= 6315 && _ch <= 6319) {
            return true;
        }
        if (_ch >= 6390 && _ch <= 6399) {
            return true;
        }
        if (_ch >= 6429 && _ch <= 6431) {
            return true;
        }
        if (_ch >= 6444 && _ch <= 6447) {
            return true;
        }
        if (_ch >= 6460 && _ch <= 6463) {
            return true;
        }
        if (_ch >= 6465 && _ch <= 6467) {
            return true;
        }
        if (_ch >= 6510 && _ch <= 6511) {
            return true;
        }
        if (_ch >= 6517 && _ch <= 6527) {
            return true;
        }
        if (_ch >= 6572 && _ch <= 6575) {
            return true;
        }
        if (_ch >= 6602 && _ch <= 6607) {
            return true;
        }
        if (_ch >= 6619 && _ch <= 6621) {
            return true;
        }
        if (_ch >= 6684 && _ch <= 6685) {
            return true;
        }
        if (_ch == 6751) {
            return true;
        }
        if (_ch >= 6781 && _ch <= 6782) {
            return true;
        }
        if (_ch >= 6794 && _ch <= 6799) {
            return true;
        }
        if (_ch >= 6810 && _ch <= 6815) {
            return true;
        }
        if (_ch >= 6830 && _ch <= 6911) {
            return true;
        }
        if (_ch >= 6988 && _ch <= 6991) {
            return true;
        }
        if (_ch >= 7037 && _ch <= 7039) {
            return true;
        }
        if (_ch >= 7156 && _ch <= 7163) {
            return true;
        }
        if (_ch >= 7224 && _ch <= 7226) {
            return true;
        }
        if (_ch >= 7242 && _ch <= 7244) {
            return true;
        }
        if (_ch >= 7296 && _ch <= 7359) {
            return true;
        }
        if (_ch >= 7368 && _ch <= 7375) {
            return true;
        }
        if (_ch >= 7415 && _ch <= 7423) {
            return true;
        }
        if (_ch >= 7655 && _ch <= 7675) {
            return true;
        }
        if (_ch >= 7958 && _ch <= 7959) {
            return true;
        }
        if (_ch >= 7966 && _ch <= 7967) {
            return true;
        }
        if (_ch >= 8006 && _ch <= 8007) {
            return true;
        }
        if (_ch >= 8014 && _ch <= 8015) {
            return true;
        }
        if (_ch == 8024) {
            return true;
        }
        if (_ch == 8026) {
            return true;
        }
        if (_ch == 8028) {
            return true;
        }
        if (_ch == 8030) {
            return true;
        }
        if (_ch >= 8062 && _ch <= 8063) {
            return true;
        }
        if (_ch == 8117) {
            return true;
        }
        if (_ch == 8133) {
            return true;
        }
        if (_ch >= 8148 && _ch <= 8149) {
            return true;
        }
        if (_ch == 8156) {
            return true;
        }
        if (_ch >= 8176 && _ch <= 8177) {
            return true;
        }
        if (_ch == 8181) {
            return true;
        }
        if (_ch == 8191) {
            return true;
        }
        if (_ch >= 8293 && _ch <= 8297) {
            return true;
        }
        if (_ch >= 8306 && _ch <= 8307) {
            return true;
        }
        if (_ch == 8335) {
            return true;
        }
        if (_ch >= 8349 && _ch <= 8351) {
            return true;
        }
        if (_ch >= 8379 && _ch <= 8399) {
            return true;
        }
        if (_ch >= 8433 && _ch <= 8447) {
            return true;
        }
        if (_ch >= 8586 && _ch <= 8591) {
            return true;
        }
        if (_ch >= 9204 && _ch <= 9215) {
            return true;
        }
        if (_ch >= 9255 && _ch <= 9279) {
            return true;
        }
        if (_ch >= 9291 && _ch <= 9311) {
            return true;
        }
        if (_ch == 9984) {
            return true;
        }
        if (_ch >= 11085 && _ch <= 11087) {
            return true;
        }
        if (_ch >= 11098 && _ch <= 11263) {
            return true;
        }
        if (_ch == 11311) {
            return true;
        }
        if (_ch == 11359) {
            return true;
        }
        if (_ch >= 11508 && _ch <= 11512) {
            return true;
        }
        if (_ch == 11558) {
            return true;
        }
        if (_ch >= 11560 && _ch <= 11564) {
            return true;
        }
        if (_ch >= 11566 && _ch <= 11567) {
            return true;
        }
        if (_ch >= 11624 && _ch <= 11630) {
            return true;
        }
        if (_ch >= 11633 && _ch <= 11646) {
            return true;
        }
        if (_ch >= 11671 && _ch <= 11679) {
            return true;
        }
        if (_ch == 11687) {
            return true;
        }
        if (_ch == 11695) {
            return true;
        }
        if (_ch == 11703) {
            return true;
        }
        if (_ch == 11711) {
            return true;
        }
        if (_ch == 11719) {
            return true;
        }
        if (_ch == 11727) {
            return true;
        }
        if (_ch == 11735) {
            return true;
        }
        if (_ch == 11743) {
            return true;
        }
        if (_ch >= 11836 && _ch <= 11903) {
            return true;
        }
        if (_ch == 11930) {
            return true;
        }
        if (_ch >= 12020 && _ch <= 12031) {
            return true;
        }
        if (_ch >= 12246 && _ch <= 12271) {
            return true;
        }
        if (_ch >= 12284 && _ch <= 12287) {
            return true;
        }
        if (_ch == 12352) {
            return true;
        }
        if (_ch >= 12439 && _ch <= 12440) {
            return true;
        }
        if (_ch >= 12544 && _ch <= 12548) {
            return true;
        }
        if (_ch >= 12590 && _ch <= 12592) {
            return true;
        }
        if (_ch == 12687) {
            return true;
        }
        if (_ch >= 12731 && _ch <= 12735) {
            return true;
        }
        if (_ch >= 12772 && _ch <= 12783) {
            return true;
        }
        if (_ch == 12831) {
            return true;
        }
        if (_ch == 13055) {
            return true;
        }
        if (_ch >= 19894 && _ch <= 19903) {
            return true;
        }
        if (_ch >= 40909 && _ch <= 40959) {
            return true;
        }
        if (_ch >= 42125 && _ch <= 42127) {
            return true;
        }
        if (_ch >= 42183 && _ch <= 42191) {
            return true;
        }
        if (_ch >= 42540 && _ch <= 42559) {
            return true;
        }
        if (_ch >= 42648 && _ch <= 42654) {
            return true;
        }
        if (_ch >= 42744 && _ch <= 42751) {
            return true;
        }
        if (_ch == 42895) {
            return true;
        }
        if (_ch >= 42900 && _ch <= 42911) {
            return true;
        }
        if (_ch >= 42923 && _ch <= 42999) {
            return true;
        }
        if (_ch >= 43052 && _ch <= 43055) {
            return true;
        }
        if (_ch >= 43066 && _ch <= 43071) {
            return true;
        }
        if (_ch >= 43128 && _ch <= 43135) {
            return true;
        }
        if (_ch >= 43205 && _ch <= 43213) {
            return true;
        }
        if (_ch >= 43226 && _ch <= 43231) {
            return true;
        }
        if (_ch >= 43260 && _ch <= 43263) {
            return true;
        }
        if (_ch >= 43348 && _ch <= 43358) {
            return true;
        }
        if (_ch >= 43389 && _ch <= 43391) {
            return true;
        }
        if (_ch == 43470) {
            return true;
        }
        if (_ch >= 43482 && _ch <= 43485) {
            return true;
        }
        if (_ch >= 43488 && _ch <= 43519) {
            return true;
        }
        if (_ch >= 43575 && _ch <= 43583) {
            return true;
        }
        if (_ch >= 43598 && _ch <= 43599) {
            return true;
        }
        if (_ch >= 43610 && _ch <= 43611) {
            return true;
        }
        if (_ch >= 43644 && _ch <= 43647) {
            return true;
        }
        if (_ch >= 43715 && _ch <= 43738) {
            return true;
        }
        if (_ch >= 43767 && _ch <= 43776) {
            return true;
        }
        if (_ch >= 43783 && _ch <= 43784) {
            return true;
        }
        if (_ch >= 43791 && _ch <= 43792) {
            return true;
        }
        if (_ch >= 43799 && _ch <= 43807) {
            return true;
        }
        if (_ch == 43815) {
            return true;
        }
        if (_ch >= 43823 && _ch <= 43967) {
            return true;
        }
        if (_ch >= 44014 && _ch <= 44015) {
            return true;
        }
        if (_ch >= 44026 && _ch <= 44031) {
            return true;
        }
        if (_ch >= 55204 && _ch <= 55215) {
            return true;
        }
        if (_ch >= 55239 && _ch <= 55242) {
            return true;
        }
        if (_ch >= 55292 && _ch <= 55295) {
            return true;
        }
        if (_ch >= 64110 && _ch <= 64111) {
            return true;
        }
        if (_ch >= 64218 && _ch <= 64255) {
            return true;
        }
        if (_ch >= 64263 && _ch <= 64274) {
            return true;
        }
        if (_ch >= 64280 && _ch <= 64284) {
            return true;
        }
        if (_ch == 64311) {
            return true;
        }
        if (_ch == 64317) {
            return true;
        }
        if (_ch == 64319) {
            return true;
        }
        if (_ch == 64322) {
            return true;
        }
        if (_ch == 64325) {
            return true;
        }
        if (_ch >= 64450 && _ch <= 64466) {
            return true;
        }
        if (_ch >= 64832 && _ch <= 64847) {
            return true;
        }
        if (_ch >= 64912 && _ch <= 64913) {
            return true;
        }
        if (_ch >= 64968 && _ch <= 65007) {
            return true;
        }
        if (_ch >= 65022 && _ch <= 65023) {
            return true;
        }
        if (_ch >= 65050 && _ch <= 65055) {
            return true;
        }
        if (_ch >= 65063 && _ch <= 65071) {
            return true;
        }
        if (_ch == 65107) {
            return true;
        }
        if (_ch == 65127) {
            return true;
        }
        if (_ch >= 65132 && _ch <= 65135) {
            return true;
        }
        if (_ch == 65141) {
            return true;
        }
        if (_ch >= 65277 && _ch <= 65278) {
            return true;
        }
        if (_ch == 65280) {
            return true;
        }
        if (_ch >= 65471 && _ch <= 65473) {
            return true;
        }
        if (_ch >= 65480 && _ch <= 65481) {
            return true;
        }
        if (_ch >= 65488 && _ch <= 65489) {
            return true;
        }
        if (_ch >= 65496 && _ch <= 65497) {
            return true;
        }
        if (_ch >= 65501 && _ch <= 65503) {
            return true;
        }
        if (_ch == 65511) {
            return true;
        }
        if (_ch >= 65519 && _ch <= 65528) {
            return true;
        }
        return _ch >= 65534;
    }

    private static boolean isRomanDigits(char _ch) {
        return _ch >= 8544 && _ch <= 8575;
    }

    private static boolean isSensibleOtherLetter(char _ch) {
        return _ch >= 9398 && _ch <= 9449;
    }

    private static boolean isQuotePunct(char _ch) {
        if (_ch >= 8216 && _ch <= 8217) {
            return true;
        }
        if (_ch >= 8219 && _ch <= 8221) {
            return true;
        }
        if (_ch == 8223) {
            return true;
        }
        if (_ch == 171 || _ch == 187) {
            return true;
        }
        if (_ch >= 8249 && _ch <= 8250) {
            return true;
        }
        if (_ch >= 11778 && _ch <= 11781) {
            return true;
        }
        if (_ch >= 11785 && _ch <= 11786) {
            return true;
        }
        if (_ch >= 11788 && _ch <= 11789) {
            return true;
        }
        if (_ch >= 11804 && _ch <= 11805) {
            return true;
        }
        return _ch >= 11808 && _ch <= 11809;
    }

    private static boolean isBoundPunct(char _ch) {
        if (_ch >= 3898 && _ch <= 3901) {
            return true;
        }
        if (_ch >= 5787 && _ch <= 5788) {
            return true;
        }
        if (_ch == 8218 || _ch == 8222) {
            return true;
        }
        if (_ch >= 8261 && _ch <= 8262) {
            return true;
        }
        if (_ch >= 8287 && _ch <= 9002) {
            return true;
        }
        if (_ch >= 10088 && _ch <= 10749) {
            return true;
        }
        if (_ch >= 11810 && _ch <= 11817) {
            return true;
        }
        if (_ch >= 12296 && _ch <= 12319) {
            return true;
        }
        if (_ch >= 64830 && _ch <= 65039) {
            return true;
        }
        if (_ch >= 65047 && _ch <= 65048) {
            return true;
        }
        if (_ch >= 65073 && _ch <= 65092) {
            return true;
        }
        if (_ch >= 65095 && _ch <= 65096) {
            return true;
        }
        if (_ch >= 65113 && _ch <= 65118) {
            return true;
        }
        if (_ch >= 65288 && _ch <= 65289) {
            return true;
        }
        if (_ch >= 65313 && _ch <= 65339) {
            return true;
        }
        if (_ch >= 65341 && _ch <= 65376) {
            return true;
        }
        return _ch >= 65378 && _ch <= 65379;
    }

    private static boolean isConnector(char _ch) {
        if (_ch >= 1418 && _ch <= 1471) {
            return true;
        }
        if (_ch == 5120 || _ch == 6150 || _ch == 8276
                || _ch == 11802|| _ch == 12316|| _ch == 12448 || _ch == 12336
                || _ch == 65123 || _ch == 65112 ||_ch==65293 || _ch==65343) {
            return true;
        }
        if (_ch >= 8208 && _ch <= 8213) {
            return true;
        }
        if (_ch >= 8255 && _ch <= 8256) {
            return true;
        }
        if (_ch >= 11799 && _ch <= 11799) {
            return true;
        }
        if (_ch >= 11834 && _ch <= 11835) {
            return true;
        }
        if (_ch >= 65073 && _ch <= 65076) {
            return true;
        }
        return _ch >= 65101 && _ch <= 65103;
    }

    private static boolean isModifierSymbol(char _ch) {
        if (_ch <= 901) {
            return true;
        }
        if (_ch >= 8125 && _ch <= 8190) {
            return true;
        }
        if (_ch >= 12443 && _ch <= 12444) {
            return true;
        }
        if (_ch >= 42752 && _ch <= 42890) {
            return true;
        }
        if (_ch >= 64434 && _ch <= 65020) {
            return true;
        }
        return _ch >= 65022 && _ch <= 65507;
    }

    private static int procGene(char _ch, int _type) {
        if (_type == LETTER_SENS_LOWER_CASE) {
            return 2;
        }
        if (_type == LETTER_SENS_UPPER_CASE) {
            return 1;
        }
        if (_type == LETTER_INSENS_LOWER_CASE) {
            return processLetter(_ch);
        }
        if (_type == LETTER_INSENS_UPPER_CASE) {
            return 1;
        }
        if (_type == LETTER_SENS_NO_CASE) {
            return 3;
        }
        if (_type == LETTER_SEMI_SENS_NO_CASE) {
            return 3;
        }
        if (_type == LETTER_INSENS_NO_CASE_DEF_DIR) {
            return processOtherLetter(_ch);
        }
        if (_type == LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS) {
            return 4;
        }
        if (_type == LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT) {
            return processOtherLetterTwo(_ch);
        }
        if (_type == DIGIT) {
            return 9;
        }
        if (_type == DIGIT_OTHER) {
            return 9;
        }
        if (_type == ID_SEP) {
            if (_ch == 160) {
                return 12;
            }
            return 23;
        }
        return processGeneralSymbol(_ch, _type);
    }

    private static int processGeneralSymbol(char _ch, int _type) {
        if (_type == CURRENCY) {
            return 26;
        }
        if (_type == SENS_OTHER_LETTER) {
            return 28;
        }
        if (_type == ROMAN_DIGIT) {
            return 10;
        }
        if (_type == DEL_LEFT) {
            return 21;
        }
        if (_type == DEL_RIGHT) {
            return 22;
        }
        if (_type == OPERATOR_LANGUAGE) {
            return processOperators(_ch);
        }
        if (_type == OPERATOR_SPEC) {
            return 24;
        }
        if (_type == PUNCTUATION) {
            return 24;
        }
        if (_type == QUOTES) {
            return processQuote(_ch);
        }
        if (_type == ESCAPE) {
            return 24;
        }
        if (_type == MATH) {
            return 25;
        }
        if (_type == MODIFIER) {
            return processModifier(_ch);
        }
        if (_type == SYMBOL_MODIFIER) {
            return 27;
        }
        if (_type == SYMBOL_OTHER) {
            return 28;
        }
        if (_type == PUNCTUATION_CONNECTOR) {
            return processPunct(_ch);
        }
        if (_type == PUNCTUATION_BOUND) {
            return processPunctTwo(_ch);
        }
        if (_type == PUNCTUATION_QUOTE) {
            return processPunctThree(_ch);
        }
        if (_type == PUNCTUATION_OTHER) {
            return 24;
        }
        if (_type == LETTERS_DIGITS_OTHER) {
            return processLettersDigitsOther(_ch);
        }
        if (_type == SEPARATOR) {
            return 16;
        }
        return processDefault(_ch, _type);
    }

    private static int processDefault(char _ch, int _type) {
        if (_type == SPACE) {
            if (_ch <= 10) {
                return 15;
            }
            return 12;
        }
        if (_type == SPACE_OTHER) {
            if (_ch <= 159) {
                return 15;
            }
            return 12;
        }
        if (_type == OTHER) {
            return processOther(_ch);
        }
        return 0;
    }

    private static int processPunctThree(char _ch) {
        if (_ch == 171) {
            return 29;
        }
        if (_ch == 8223) {
            return 29;
        }
        if (_ch == 187) {
            return 30;
        }
        if (_ch == 8221) {
            return 30;
        }
        if (_ch <= 8217) {
            if (_ch % 2 == 1) {
                return 30;
            }
            return 29;
        }
        if (_ch <= 8220) {
            return 29;
        }
        if (_ch <= 11777) {
            if (_ch % 2 == 1) {
                return 29;
            }
            return 30;
        }
        if (_ch <= 11781) {
            if (_ch % 2 == 1) {
                return 30;
            }
            return 29;
        }
        if (_ch <= 11786) {
            if (_ch % 2 == 1) {
                return 29;
            }
            return 30;
        }
        if (_ch % 2 == 1) {
            return 30;
        }
        return 29;
    }

    private static int processPunctTwo(char _ch) {
        if (_ch <= 4000) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 6000) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 8261) {
            return 21;
        }
        if (_ch <= 10000) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 10180) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 10182) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 10223) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 10711) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 12315) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 12318) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 12319) {
            return 22;
        }
        if (_ch <= 64831) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch <= 65118) {
            if (_ch % 2 == 1) {
                return 21;
            }
            return 22;
        }
        if (_ch <= 65289) {
            if (_ch % 2 == 1) {
                return 22;
            }
            return 21;
        }
        if (_ch == 65339) {
            return 21;
        }
        if (_ch == 65341) {
            return 22;
        }
        if (_ch == 65371) {
            return 21;
        }
        if (_ch == 65373) {
            return 22;
        }
        if (_ch == 65375) {
            return 21;
        }
        if (_ch == 65376) {
            return 22;
        }
        if (_ch == 65378) {
            return 21;
        }
        return 22;
    }

    private static int processPunct(char _ch) {
        if (_ch <= 8213) {
            return 20;
        }
        if (_ch <= 8276) {
            return 23;
        }
        if (_ch <= 65074) {
            return 20;
        }
        if (_ch <= 65103) {
            return 23;
        }
        if (_ch <= 65293) {
            return 20;
        }
        return 23;
    }

    private static int processModifier(char _ch) {
        if (_ch == 173) {
            return 16;
        }
        if (_ch == 166) {
            return 28;
        }
        if (_ch == 168) {
            return 27;
        }
        if (_ch == 175) {
            return 27;
        }
        if (_ch == 180) {
            return 27;
        }
        if (_ch == 184) {
            return 27;
        }
        if (_ch <= 176) {
            return 28;
        }
        return 11;
    }

    private static int processQuote(char _ch) {
        if (_ch <= 64) {
            return 24;
        }
        return 27;
    }

    private static int processOperators(char _ch) {
        if (_ch == 45) {
            return 20;
        }
        if (_ch == 94) {
            return 27;
        }
        if (_ch == 43) {
            return 25;
        }
        if (_ch <= 58) {
            return 24;
        }
        if (_ch <= 62) {
            return 25;
        }
        if (_ch <= 64) {
            return 24;
        }
        return 25;
    }

    private static int processOtherLetterTwo(char _ch) {
        if (_ch == 1600) {
            return 4;
        }
        if (_ch == 1765) {
            return 4;
        }
        if (_ch == 1766) {
            return 4;
        }
        if (_ch == 2036) {
            return 4;
        }
        if (_ch == 2037) {
            return 4;
        }
        if (_ch == 2042) {
            return 4;
        }
        if (_ch == 2074) {
            return 4;
        }
        if (_ch == 2084) {
            return 4;
        }
        if (_ch == 2088) {
            return 4;
        }
        return 5;
    }

    private static int processOtherLetter(char _ch) {
        if (_ch == 1369) {
            return 4;
        }
        if (_ch == 2417) {
            return 4;
        }
        if (_ch == 3654) {
            return 4;
        }
        if (_ch == 3782) {
            return 4;
        }
        if (_ch == 4348) {
            return 4;
        }
        if (_ch == 6103) {
            return 4;
        }
        if (_ch == 6211) {
            return 4;
        }
        if (_ch == 6823) {
            return 4;
        }
        if (_ch == 11631) {
            return 4;
        }
        if (_ch == 12293) {
            return 4;
        }
        if (_ch == 40981) {
            return 4;
        }
        if (_ch == 42508) {
            return 4;
        }
        if (_ch == 43741) {
            return 4;
        }
        if (_ch == 43471) {
            return 4;
        }
        if (_ch == 43632) {
            return 4;
        }
        if (_ch == 43763) {
            return 4;
        }
        if (_ch == 43764) {
            return 4;
        }
        if (_ch == 65392) {
            return 4;
        }
        if (_ch == 65438) {
            return 4;
        }
        if (_ch == 65439) {
            return 4;
        }
        if (_ch>=7288&&_ch <= 7293) {
            return 4;
        }
        if (_ch>=12337&&_ch <= 12347) {
            return 4;
        }
        if (_ch>=12445&&_ch <= 12446) {
            return 4;
        }
        if (_ch>=12539&&_ch <= 12542) {
            return 4;
        }
        if (_ch>=42232&&_ch <= 42239) {
            return 4;
        }
        if (_ch>=699&&_ch <= 750) {
            return 4;
        }
        return 5;
    }

    private static int processLetter(char _ch) {
        if (_ch <= 186) {
            return 5;
        }
        if (_ch <= 687) {
            return 2;
        }
        if (_ch <= 890) {
            return 4;
        }
        if (_ch <= 7467) {
            return 2;
        }
        if (_ch <= 7530) {
            return 4;
        }
        if (_ch <= 7543) {
            return 2;
        }
        if (_ch <= 7544) {
            return 4;
        }
        if (_ch <= 7578) {
            return 2;
        }
        if (_ch <= 7615) {
            return 4;
        }
        if (_ch <= 8183) {
            return 2;
        }
        if (_ch <= 8348) {
            return 4;
        }
        if (_ch <= 11387) {
            return 2;
        }
        if (_ch <= 11389) {
            return 4;
        }
        if (_ch <= 42801) {
            return 2;
        }
        if (_ch <= 42864) {
            return 4;
        }
        if (_ch <= 42894) {
            return 2;
        }
        if (_ch <= 43001) {
            return 4;
        }
        return 2;
    }

    private static int processLettersDigitsOther(char _ch) {
        if (_ch >= 12690 && _ch <= 12991) {
            return 11;
        }
        if (_ch >= 43056) {
            return 11;
        }
        if (_ch >= 8585 && _ch <= 10131) {
            return 11;
        }
        if (_ch <= 4988) {
            return 11;
        }
        if (_ch >= 11517 && _ch <= 11517) {
            return 11;
        }
        if (_ch >= 8528 && _ch <= 8543) {
            return 11;
        }
        if (_ch >= 8304 && _ch <= 8329) {
            return 11;
        }
        if (_ch >= 6128 && _ch <= 6137) {
            return 11;
        }
        if (_ch >= 6618 && _ch <= 6618) {
            return 11;
        }
        return 10;
    }
    private static int processOther(char _ch) {
        if (_ch == 8232) {
            return 13;
        }
        if (_ch == 8233) {
            return 14;
        }
        if (_ch >= 57344 && _ch <= 63743) {
            return 18;
        }
        if (_ch >= 55296 && _ch <= 57343) {
            return 19;
        }
        if (_ch >= 44013) {
            return 6;
        }
        if (_ch >= 8413 && _ch <= 8416) {
            return 7;
        }
        if (_ch >= 8418 && _ch <= 8420) {
            return 7;
        }
        if (_ch >= 42608 && _ch <= 42610) {
            return 7;
        }
        if (_ch <= 1159) {
            return 6;
        }
        if (_ch <= 1161) {
            return 7;
        }
        if (_ch <= 2306) {
            return 6;
        }
        if (_ch >= 3633 && _ch <= 3897) {
            return 6;
        }
        if (_ch >= 3953 && _ch <= 3966) {
            return 6;
        }
        if (_ch >= 43204 && _ch <= 43345) {
            return 6;
        }
        if (_ch >= 7412 && _ch <= 12333) {
            return 6;
        }
        if (_ch >= 12441 && _ch <= 43019) {
            return 6;
        }
        if (_ch >= 7222 && _ch <= 7392) {
            return 6;
        }
        if (_ch >= 7394 && _ch <= 7405) {
            return 6;
        }
        if (_ch >= 3968 && _ch <= 4038) {
            return 6;
        }
        if (_ch >= 4253 && _ch <= 6069) {
            return 6;
        }
        if (_ch >= 6071 && _ch <= 6077) {
            return 6;
        }
        if (_ch >= 6089 && _ch <= 6434) {
            return 6;
        }
        if (_ch >= 2369 && _ch <= 2376) {
            return 6;
        }
        if (_ch >= 2385 && _ch <= 2433) {
            return 6;
        }
        if (_ch >= 2625 && _ch <= 2690) {
            return 6;
        }
        if (_ch >= 3142 && _ch <= 3171) {
            return 6;
        }
        if (_ch >= 6744 && _ch <= 6752) {
            return 6;
        }
        if (_ch >= 6771 && _ch <= 6915) {
            return 6;
        }
        if (_ch >= 43047 && _ch <= 43347) {
            return 8;
        }
        if (_ch >= 4239 && _ch <= 6085) {
            return 8;
        }
        if (_ch >= 2366 && _ch <= 2380) {
            return 8;
        }
        if (_ch >= 7220 && _ch <= 43044) {
            return 8;
        }
        if (_ch >= 7212 && _ch <= 43394) {
            return 6;
        }
        if (_ch >= 7154 && _ch <= 43395) {
            return 8;
        }
        if (_ch >= 7151 && _ch <= 43443) {
            return 6;
        }
        if (_ch >= 7150 && _ch <= 43445) {
            return 8;
        }
        if (_ch >= 7149 && _ch <= 43449) {
            return 6;
        }
        if (_ch >= 7146 && _ch <= 43451) {
            return 8;
        }
        if (_ch >= 7144 && _ch <= 43452) {
            return 6;
        }
        if (_ch >= 7143 && _ch <= 43456) {
            return 8;
        }
        if (_ch >= 7142 && _ch <= 43566) {
            return 6;
        }
        if (_ch >= 7084 && _ch <= 43568) {
            return 8;
        }
        if (_ch >= 7083 && _ch <= 43570) {
            return 6;
        }
        if (_ch >= 7082 && _ch <= 43572) {
            return 8;
        }
        if (_ch >= 7080 && _ch <= 43596) {
            return 6;
        }
        if (_ch >= 7078 && _ch <= 43643) {
            return 8;
        }
        if (_ch >= 7074 && _ch <= 43713) {
            return 6;
        }
        if (_ch >= 7042 && _ch <= 43755) {
            return 8;
        }
        if (_ch >= 7019 && _ch <= 43757) {
            return 6;
        }
        if (_ch >= 6979 && _ch <= 43765) {
            return 8;
        }
        if (_ch >= 6757 && _ch <= 6764) {
            return 6;
        }
        if (_ch >= 6966 && _ch <= 6970) {
            return 6;
        }
        if (_ch >= 4141 && _ch <= 4144) {
            return 6;
        }
        if (_ch >= 4146 && _ch <= 4151) {
            return 6;
        }
        if (_ch >= 4153 && _ch <= 4154) {
            return 6;
        }
        if (_ch >= 4157 && _ch <= 4158) {
            return 6;
        }
        if (_ch >= 4184 && _ch <= 4192) {
            return 6;
        }
        if (_ch >= 4209 && _ch <= 4226) {
            return 6;
        }
        if (_ch >= 4229 && _ch <= 4230) {
            return 6;
        }
        if (_ch >= 4237 && _ch <= 6086) {
            return 6;
        }
        if (_ch >= 6439 && _ch <= 6440) {
            return 6;
        }
        if (_ch >= 6450 && _ch <= 6450) {
            return 6;
        }
        if (_ch >= 6457 && _ch <= 6459) {
            return 6;
        }
        if (_ch >= 3544 && _ch <= 6601) {
            return 8;
        }
        if (_ch >= 3538 && _ch <= 6680) {
            return 6;
        }
        if (_ch >= 3535 && _ch <= 6741) {
            return 8;
        }
        if (_ch >= 3393 && _ch <= 3396) {
            return 6;
        }
        if (_ch >= 3330 && _ch <= 3404) {
            return 8;
        }
        if (_ch >= 2753 && _ch <= 2760) {
            return 6;
        }
        if (_ch >= 2750 && _ch <= 2764) {
            return 8;
        }
        if (_ch >= 2748 && _ch <= 2817) {
            return 6;
        }
        if (_ch >= 2622 && _ch <= 2819) {
            return 8;
        }
        if (_ch >= 2530 && _ch <= 2562) {
            return 6;
        }
        if (_ch >= 2519 && _ch <= 2563) {
            return 8;
        }
        if (_ch >= 2509 && _ch <= 2876) {
            return 6;
        }
        if (_ch >= 2503 && _ch <= 2878) {
            return 8;
        }
        if (_ch >= 3298 && _ch <= 3405) {
            return 6;
        }
        if (_ch >= 3285 && _ch <= 3415) {
            return 8;
        }
        if (_ch >= 3276 && _ch <= 3427) {
            return 6;
        }
        if (_ch >= 3271 && _ch <= 3459) {
            return 8;
        }
        if (_ch >= 2887 && _ch <= 2892) {
            return 8;
        }
        if (_ch >= 2881 && _ch <= 2902) {
            return 6;
        }
        if (_ch >= 2880 && _ch <= 2903) {
            return 8;
        }
        if (_ch >= 2497 && _ch <= 2946) {
            return 6;
        }
        if (_ch >= 6755 && _ch <= 6916) {
            return 8;
        }
        if (_ch >= 6754 && _ch <= 6964) {
            return 6;
        }
        if (_ch == 2362) {
            return 6;
        }
        if (_ch == 2492) {
            return 6;
        }
        if (_ch == 3008) {
            return 6;
        }
        if (_ch == 3021) {
            return 6;
        }
        if (_ch >= 2382 && _ch <= 3075) {
            return 8;
        }
        if (_ch >= 2364 && _ch <= 3136) {
            return 6;
        }
        if (_ch >= 3270 && _ch <= 6742) {
            return 6;
        }
        if (_ch >= 3264 && _ch <= 6971) {
            return 8;
        }
        if (_ch == 3260) {
            return 6;
        }
        if (_ch == 44008) {
            return 6;
        }
        if (_ch == 44005) {
            return 6;
        }
        if (_ch >= 6973 && _ch <= 6977) {
            return 8;
        }
        if (_ch >= 3263 && _ch <= 43766) {
            return 6;
        }
        return 8;
    }

    private static boolean isOtherMathSymbol(char _string) {
        if (_string < 128) {
            return false;
        }
        for (int i: NumberUtil.wrapIntArray(8602,8654)) {
            if (_string >= i && _string <= i + 1) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(1542,8314,8330)) {
            if (_string >= i && _string <= i + 2) {
                return true;
            }
        }
        for (int i: NumberUtil.wrapIntArray(8512,8592)) {
            if (_string >= i && _string <= i + 4) {
                return true;
            }
        }
        if (_string == 172) {
            return true;
        }
        if (_string == 177) {
            return true;
        }
        if (_string == 215) {
            return true;
        }
        if (_string == 247) {
            return true;
        }
        if (_string == 1014 || _string == 8611) {
            return true;
        }
        if (_string == 8260 || _string == 8614) {
            return true;
        }
        if (_string == 8274 || _string == 8622) {
            return true;
        }
        if (_string == 8472 || _string == 8660 || _string == 8658) {
            return true;
        }
        if (_string == 8523 ||_string ==9655 ||_string ==9665) {
            return true;
        }
        if (_string == 8608 || _string == 9839 || _string == 9084) {
            return true;
        }
        if (_string >= 9115&&_string<=9139) {
            return true;
        }
        if (_string >= 9180&&_string<=9185) {
            return true;
        }
        if (_string >= 9720&&_string<=9727) {
            return true;
        }
        if (_string >= 8968&&_string<=8971) {
            return true;
        }
        if (_string >= 8960&&_string<=8991) {
            return false;
        }
        if (_string >= 8692&&_string<=8993) {
            return true;
        }
        if (_string >= 10176&&_string<=10180) {
            return true;
        }
        if (_string >= 10183&&_string<=10213) {
            return true;
        }
        if (_string >= 10224&&_string<=10239) {
            return true;
        }
        if (_string >= 10627&&_string<=10648) {
            return false;
        }
        if (_string >= 10716&&_string<=10747) {
            return true;
        }
        if (_string >= 10712&&_string<=10749) {
            return false;
        }
        if (_string >= 11056&&_string<=11076) {
            return true;
        }
        if (_string >= 11008&&_string<=11078) {
            return false;
        }
        if (_string >= 10496&&_string<=11084) {
            return true;
        }
        if (_string >= 65513&&_string<=65516) {
            return true;
        }
        if (_string >= 65308&&_string<=65310) {
            return true;
        }
        if (_string >= 65124&&_string<=65126) {
            return true;
        }
        if (_string == 64297) {
            return true;
        }
        if (_string == 65122) {
            return true;
        }
        if (_string == 65291) {
            return true;
        }
        if (_string == 65372) {
            return true;
        }
        if (_string == 65374) {
            return true;
        }
        return _string == 65506;
    }

    private static int toLowerCaseInt(char _ch) {
        if (_ch == 453) {
            return _ch + 1;
        }
        if (_ch == 456) {
            return _ch + 1;
        }
        if (isUnassigned(_ch)) {
            return _ch;
        }
        if (isOtherSpace(_ch)) {
            return _ch;
        }
        if (MathExpUtil.isDigit(_ch)) {
            return _ch;
        }
        if (isOtherDigit(_ch)) {
            return _ch;
        }
        return toLowerCheckDefFive(_ch);
    }

    private static int toLowerCheckDefFive(char _ch) {
        if (isRomanDigits(_ch)) {
            if (_ch >= 8544 + 16) {
                return _ch;
            }
            return _ch+16;
        }
        if (isSensibleOtherLetter(_ch)) {
            if (_ch >= 9398 + 26) {
                return _ch;
            }
            return _ch+26;
        }
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        return toLowerCheckDefFour(_ch);
    }

    private static int toLowerCheckDefFour(char _ch) {
        if (_ch == 391) {
            return _ch + 1;
        }
        if (_ch == 395) {
            return _ch + 1;
        }
        if (_ch == 401) {
            return _ch + 1;
        }
        if (_ch == 408) {
            return _ch + 1;
        }
        if (_ch == 423) {
            return _ch + 1;
        }
        if (_ch == 428) {
            return _ch + 1;
        }
        if (_ch == 431) {
            return _ch + 1;
        }
        if (_ch == 440) {
            return _ch + 1;
        }
        if (_ch == 444) {
            return _ch + 1;
        }
        return toLowerCheckDefThree(_ch);
    }

    private static int toLowerCheckDefTwo(char _ch) {
        if (_ch % 2 == 0) {
            if (_ch >= 256 && _ch <= 302) {
                return _ch + 1;
            }
            if (_ch >= 306 && _ch <= 310) {
                return _ch + 1;
            }
            if (_ch >= 330 && _ch <= 374) {
                return _ch + 1;
            }
            if (_ch >= 386 && _ch <= 388) {
                return _ch + 1;
            }
            if (_ch >= 416 && _ch <= 420) {
                return _ch + 1;
            }
            if (_ch >= 478 && _ch <= 494) {
                return _ch + 1;
            }
            if (_ch >= 498 && _ch <= 500) {
                return _ch + 1;
            }
            if (_ch >= 504 && _ch <= 542) {
                return _ch + 1;
            }
            if (_ch >= 546 && _ch <= 562) {
                return _ch + 1;
            }
            if (_ch >= 582 && _ch <= 590) {
                return _ch + 1;
            }
            if (_ch >= 880 && _ch <= 882) {
                return _ch + 1;
            }
            if (_ch >= 984 && _ch <= 1006) {
                return _ch + 1;
            }
            if (_ch >= 1120 && _ch <= 1152) {
                return _ch + 1;
            }
            if (_ch >= 1162 && _ch <= 1214) {
                return _ch + 1;
            }
            if (_ch >= 1232 && _ch <= 1318) {
                return _ch + 1;
            }
            if (_ch >= 7680 && _ch <= 7828) {
                return _ch + 1;
            }
            if (_ch >= 7840 && _ch <= 7934) {
                return _ch + 1;
            }
            if (_ch >= 11392 && _ch <= 11490) {
                return _ch + 1;
            }
            if (_ch >= 42560 && _ch <= 42604) {
                return _ch + 1;
            }
            if (_ch >= 42624 && _ch <= 42646) {
                return _ch + 1;
            }
            if (_ch >= 42786 && _ch <= 42798) {
                return _ch + 1;
            }
            if (_ch >= 42802 && _ch <= 42862) {
                return _ch + 1;
            }
            if (_ch >= 42878 && _ch <= 42886) {
                return _ch + 1;
            }
            if (_ch >= 42896 && _ch <= 42898) {
                return _ch + 1;
            }
            if (_ch >= 42912 && _ch <= 42920) {
                return _ch + 1;
            }
        } else {
            if (_ch >= 313 && _ch <= 327) {
                return _ch + 1;
            }
            if (_ch >= 377 && _ch <= 381) {
                return _ch + 1;
            }
            if (_ch >= 435 && _ch <= 437) {
                return _ch + 1;
            }
            if (_ch >= 459 && _ch <= 475) {
                return _ch + 1;
            }
            if (_ch >= 1217 && _ch <= 1229) {
                return _ch + 1;
            }
            if (_ch >= 11367 && _ch <= 11371) {
                return _ch + 1;
            }
            if (_ch >= 11499 && _ch <= 11501) {
                return _ch + 1;
            }
            if (_ch >= 42873 && _ch <= 42875) {
                return _ch + 1;
            }
        }

        return toLowerCheckDef(_ch);
    }

    private static int toLowerCaseIntCheckUpp(char _ch) {
        if (isRomanDigits(_ch)) {
            return _ch+16;
        }
        if (isSensibleOtherLetter(_ch)) {
            return _ch+26;
        }
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 391) {
            return _ch + 1;
        }
        if (_ch == 395) {
            return _ch + 1;
        }
        if (_ch == 401) {
            return _ch + 1;
        }
        if (_ch == 408) {
            return _ch + 1;
        }
        if (_ch == 423) {
            return _ch + 1;
        }
        if (_ch == 428) {
            return _ch + 1;
        }
        if (_ch == 431) {
            return _ch + 1;
        }
        if (_ch == 440) {
            return _ch + 1;
        }
        if (_ch == 444) {
            return _ch + 1;
        }
        if (_ch == 571) {
            return _ch + 1;
        }
        if (_ch == 577) {
            return _ch + 1;
        }
        if (_ch == 886) {
            return _ch + 1;
        }
        if (_ch == 1015) {
            return _ch + 1;
        }
        if (_ch == 1018) {
            return _ch + 1;
        }
        if (_ch == 8579) {
            return _ch + 1;
        }
        if (_ch == 11360) {
            return _ch + 1;
        }
        if (_ch == 11378) {
            return _ch + 1;
        }
        if (_ch == 11381) {
            return _ch + 1;
        }
        if (_ch == 11506) {
            return _ch + 1;
        }
        if (_ch == 42891) {
            return _ch + 1;
        }
        if (_ch == 304) {
            return _ch -199;
        }
        if (_ch == 376) {
            return _ch -121;
        }
        if (_ch == 385) {
            return _ch + 210;
        }
        if (_ch == 390) {
            return _ch + 206;
        }
        if (_ch == 398) {
            return _ch + 79;
        }
        if (_ch == 399) {
            return _ch + 202;
        }
        if (_ch == 400) {
            return _ch + 203;
        }
        if (_ch == 403) {
            return _ch + 205;
        }
        if (_ch == 404) {
            return _ch + 207;
        }
        if (_ch == 406) {
            return _ch + 211;
        }
        if (_ch == 407) {
            return _ch + 209;
        }
        if (_ch == 412) {
            return _ch + 211;
        }
        if (_ch == 413) {
            return _ch + 213;
        }
        if (_ch == 415) {
            return _ch + 214;
        }
        if (_ch == 422) {
            return _ch + 218;
        }
        if (_ch == 425) {
            return _ch + 218;
        }
        if (_ch == 430) {
            return _ch + 218;
        }
        if (_ch == 439) {
            return _ch + 219;
        }
        if (_ch == 452) {
            return _ch + 2;
        }
        if (_ch == 455) {
            return _ch + 2;
        }
        if (_ch == 458) {
            return _ch + 2;
        }
        if (_ch == 497) {
            return _ch + 2;
        }
        if (_ch == 502) {
            return _ch -97;
        }
        if (_ch == 503) {
            return _ch -56;
        }
        if (_ch == 544) {
            return _ch -130;
        }
        if (_ch == 570) {
            return _ch + 10795;
        }
        if (_ch == 573) {
            return _ch -163;
        }
        if (_ch == 574) {
            return _ch + 10792;
        }
        if (_ch == 579) {
            return _ch -195;
        }
        if (_ch == 580) {
            return _ch + 69;
        }
        if (_ch == 581) {
            return _ch + 71;
        }
        if (_ch == 902) {
            return _ch + 38;
        }
        if (_ch == 908) {
            return _ch + 64;
        }
        if (_ch == 975) {
            return _ch + 8;
        }
        if (_ch == 1012) {
            return _ch -60;
        }
        if (_ch == 1017) {
            return _ch -7;
        }
        if (_ch == 1216) {
            return _ch + 15;
        }
        if (_ch == 4295) {
            return _ch + 7264;
        }
        if (_ch == 4301) {
            return _ch + 7264;
        }
        if (_ch == 7838) {
            return _ch -7615;
        }
        if (_ch == 8025) {
            return _ch -8;
        }
        if (_ch == 8027) {
            return _ch -8;
        }
        if (_ch == 8029) {
            return _ch -8;
        }
        if (_ch == 8031) {
            return _ch -8;
        }
        if (_ch == 8172) {
            return _ch -7;
        }
        if (_ch == 8486) {
            return _ch -7517;
        }
        if (_ch == 8490) {
            return _ch -8383;
        }
        if (_ch == 8491) {
            return _ch -8262;
        }
        if (_ch == 8498) {
            return _ch + 28;
        }
        if (_ch == 11362) {
            return _ch -10743;
        }
        if (_ch == 11363) {
            return _ch -3814;
        }
        if (_ch == 11364) {
            return _ch -10727;
        }
        if (_ch == 11373) {
            return _ch -10780;
        }
        if (_ch == 11374) {
            return _ch -10749;
        }
        if (_ch == 11375) {
            return _ch -10783;
        }
        if (_ch == 11376) {
            return _ch -10782;
        }
        if (_ch == 42877) {
            return _ch -35332;
        }
        if (_ch == 42893) {
            return _ch -42280;
        }
        if (_ch == 42922) {
            return _ch -42308;
        }
        return toLowerCaseDefaultSeven(_ch);
    }

    private static int toLowerCaseDefaultSeven(char _ch) {
        if (_ch % 2 == 0) {
            if (_ch >= 256 && _ch <= 302) {
                return _ch + 1;
            }
            if (_ch >= 306 && _ch <= 310) {
                return _ch + 1;
            }
            if (_ch >= 330 && _ch <= 374) {
                return _ch + 1;
            }
            if (_ch >= 386 && _ch <= 388) {
                return _ch + 1;
            }
            if (_ch >= 416 && _ch <= 420) {
                return _ch + 1;
            }
            if (_ch >= 478 && _ch <= 494) {
                return _ch + 1;
            }
            if (_ch >= 498 && _ch <= 500) {
                return _ch + 1;
            }
            if (_ch >= 504 && _ch <= 542) {
                return _ch + 1;
            }
            if (_ch >= 546 && _ch <= 562) {
                return _ch + 1;
            }
            if (_ch >= 582 && _ch <= 590) {
                return _ch + 1;
            }
            if (_ch >= 880 && _ch <= 882) {
                return _ch + 1;
            }
            if (_ch >= 984 && _ch <= 1006) {
                return _ch + 1;
            }
            if (_ch >= 1120 && _ch <= 1152) {
                return _ch + 1;
            }
            if (_ch >= 1162 && _ch <= 1214) {
                return _ch + 1;
            }
            if (_ch >= 1232 && _ch <= 1318) {
                return _ch + 1;
            }
            if (_ch >= 7680 && _ch <= 7828) {
                return _ch + 1;
            }
            if (_ch >= 7840 && _ch <= 7934) {
                return _ch + 1;
            }
            if (_ch >= 11392 && _ch <= 11490) {
                return _ch + 1;
            }
            if (_ch >= 42560 && _ch <= 42604) {
                return _ch + 1;
            }
            if (_ch >= 42624 && _ch <= 42646) {
                return _ch + 1;
            }
            if (_ch >= 42786 && _ch <= 42798) {
                return _ch + 1;
            }
            if (_ch >= 42802 && _ch <= 42862) {
                return _ch + 1;
            }
            if (_ch >= 42878 && _ch <= 42886) {
                return _ch + 1;
            }
            if (_ch >= 42896 && _ch <= 42898) {
                return _ch + 1;
            }
            if (_ch >= 42912 && _ch <= 42920) {
                return _ch + 1;
            }
        } else {
            if (_ch >= 313 && _ch <= 327) {
                return _ch + 1;
            }
            if (_ch >= 377 && _ch <= 381) {
                return _ch + 1;
            }
            if (_ch >= 435 && _ch <= 437) {
                return _ch + 1;
            }
            if (_ch >= 459 && _ch <= 475) {
                return _ch + 1;
            }
            if (_ch >= 1217 && _ch <= 1229) {
                return _ch + 1;
            }
            if (_ch >= 11367 && _ch <= 11371) {
                return _ch + 1;
            }
            if (_ch >= 11499 && _ch <= 11501) {
                return _ch + 1;
            }
            if (_ch >= 42873 && _ch <= 42875) {
                return _ch + 1;
            }
        }

        return toLowerCaseDefaultSix(_ch);
    }

    private static int toLowerCaseDefaultSix(char _ch) {
        if (_ch >= 65 && _ch <= 90) {
            return _ch + 32;
        }
        if (_ch >= 192 && _ch <= 214) {
            return _ch + 32;
        }
        if (_ch >= 216 && _ch <= 222) {
            return _ch + 32;
        }
        if (_ch >= 393 && _ch <= 394) {
            return _ch + 205;
        }
        if (_ch >= 433 && _ch <= 434) {
            return _ch + 217;
        }
        if (_ch >= 904 && _ch <= 906) {
            return _ch + 37;
        }
        if (_ch >= 910 && _ch <= 911) {
            return _ch + 63;
        }
        if (_ch >= 913 && _ch <= 929) {
            return _ch + 32;
        }
        if (_ch >= 931 && _ch <= 939) {
            return _ch + 32;
        }
        if (_ch >= 1021 && _ch <= 1023) {
            return _ch -130;
        }
        if (_ch >= 1024 && _ch <= 1039) {
            return _ch + 80;
        }
        if (_ch >= 1040 && _ch <= 1071) {
            return _ch + 32;
        }
        if (_ch >= 1329 && _ch <= 1366) {
            return _ch + 48;
        }
        if (_ch >= 4256 && _ch <= 4293) {
            return _ch + 7264;
        }
        if (_ch >= 7944 && _ch <= 7951) {
            return _ch -8;
        }
        if (_ch >= 7960 && _ch <= 7965) {
            return _ch -8;
        }
        if (_ch >= 7976 && _ch <= 7983) {
            return _ch -8;
        }
        if (_ch >= 7992 && _ch <= 7999) {
            return _ch -8;
        }
        if (_ch >= 8008 && _ch <= 8013) {
            return _ch -8;
        }
        if (_ch >= 8040 && _ch <= 8047) {
            return _ch -8;
        }
        if (_ch >= 8120 && _ch <= 8121) {
            return _ch -8;
        }
        if (_ch >= 8122 && _ch <= 8123) {
            return _ch -74;
        }
        if (_ch >= 8136 && _ch <= 8139) {
            return _ch -86;
        }
        if (_ch >= 8152 && _ch <= 8153) {
            return _ch -8;
        }
        if (_ch >= 8154 && _ch <= 8155) {
            return _ch -100;
        }
        if (_ch >= 8168 && _ch <= 8169) {
            return _ch -8;
        }
        if (_ch >= 8170 && _ch <= 8171) {
            return _ch -112;
        }
        if (_ch >= 8184 && _ch <= 8185) {
            return _ch -128;
        }
        if (_ch >= 8186 && _ch <= 8187) {
            return _ch -126;
        }
        if (_ch >= 11264 && _ch <= 11310) {
            return _ch + 48;
        }
        if (_ch >= 11390 && _ch <= 11391) {
            return _ch -10815;
        }
        if (_ch >= 65313 && _ch <= 65338) {
            return _ch + 32;
        }
        return _ch;
    }

    private static int toLowerCaseIntCheck(char _ch) {
        return toLowerCheckDefFive(_ch);
    }

    private static int toLowerCheckDefThree(char _ch) {
        if (_ch == 571) {
            return _ch + 1;
        }
        if (_ch == 577) {
            return _ch + 1;
        }
        if (_ch == 886) {
            return _ch + 1;
        }
        if (_ch == 1015) {
            return _ch + 1;
        }
        if (_ch == 1018) {
            return _ch + 1;
        }
        if (_ch == 8579) {
            return _ch + 1;
        }
        if (_ch == 11360) {
            return _ch + 1;
        }
        if (_ch == 11378) {
            return _ch + 1;
        }
        if (_ch == 11381) {
            return _ch + 1;
        }
        if (_ch == 11506) {
            return _ch + 1;
        }
        if (_ch == 42891) {
            return _ch + 1;
        }
        if (_ch == 304) {
            return _ch -199;
        }
        if (_ch == 376) {
            return _ch -121;
        }
        if (_ch == 385) {
            return _ch + 210;
        }
        if (_ch == 390) {
            return _ch + 206;
        }
        if (_ch == 398) {
            return _ch + 79;
        }
        if (_ch == 399) {
            return _ch + 202;
        }
        if (_ch == 400) {
            return _ch + 203;
        }
        if (_ch == 403) {
            return _ch + 205;
        }
        if (_ch == 404) {
            return _ch + 207;
        }
        if (_ch == 406) {
            return _ch + 211;
        }
        if (_ch == 407) {
            return _ch + 209;
        }
        if (_ch == 412) {
            return _ch + 211;
        }
        if (_ch == 413) {
            return _ch + 213;
        }
        if (_ch == 415) {
            return _ch + 214;
        }
        if (_ch == 422) {
            return _ch + 218;
        }
        if (_ch == 425) {
            return _ch + 218;
        }
        if (_ch == 430) {
            return _ch + 218;
        }
        if (_ch == 439) {
            return _ch + 219;
        }
        if (_ch == 452) {
            return _ch + 2;
        }
        if (_ch == 455) {
            return _ch + 2;
        }
        if (_ch == 458) {
            return _ch + 2;
        }
        if (_ch == 497) {
            return _ch + 2;
        }
        if (_ch == 502) {
            return _ch -97;
        }
        if (_ch == 503) {
            return _ch -56;
        }
        if (_ch == 544) {
            return _ch -130;
        }
        if (_ch == 570) {
            return _ch + 10795;
        }
        if (_ch == 573) {
            return _ch -163;
        }
        if (_ch == 574) {
            return _ch + 10792;
        }
        if (_ch == 579) {
            return _ch -195;
        }
        if (_ch == 580) {
            return _ch + 69;
        }
        if (_ch == 581) {
            return _ch + 71;
        }
        if (_ch == 902) {
            return _ch + 38;
        }
        if (_ch == 908) {
            return _ch + 64;
        }
        if (_ch == 975) {
            return _ch + 8;
        }
        if (_ch == 1012) {
            return _ch -60;
        }
        if (_ch == 1017) {
            return _ch -7;
        }
        if (_ch == 1216) {
            return _ch + 15;
        }
        if (_ch == 4295) {
            return _ch + 7264;
        }
        if (_ch == 4301) {
            return _ch + 7264;
        }
        if (_ch == 7838) {
            return _ch -7615;
        }
        if (_ch == 8025) {
            return _ch -8;
        }
        if (_ch == 8027) {
            return _ch -8;
        }
        if (_ch == 8029) {
            return _ch -8;
        }
        if (_ch == 8031) {
            return _ch -8;
        }
        if (_ch == 8124) {
            return _ch -9;
        }
        if (_ch == 8140) {
            return _ch -9;
        }
        if (_ch == 8172) {
            return _ch -7;
        }
        if (_ch == 8188) {
            return _ch -9;
        }
        if (_ch == 8486) {
            return _ch -7517;
        }
        if (_ch == 8490) {
            return _ch -8383;
        }
        if (_ch == 8491) {
            return _ch -8262;
        }
        if (_ch == 8498) {
            return _ch + 28;
        }
        if (_ch == 11362) {
            return _ch -10743;
        }
        if (_ch == 11363) {
            return _ch -3814;
        }
        if (_ch == 11364) {
            return _ch -10727;
        }
        if (_ch == 11373) {
            return _ch -10780;
        }
        if (_ch == 11374) {
            return _ch -10749;
        }
        if (_ch == 11375) {
            return _ch -10783;
        }
        if (_ch == 11376) {
            return _ch -10782;
        }
        if (_ch == 42877) {
            return _ch -35332;
        }
        if (_ch == 42893) {
            return _ch -42280;
        }
        if (_ch == 42922) {
            return _ch -42308;
        }
        return toLowerCheckDefTwo(_ch);
    }

    private static int toLowerCheckDef(char _ch) {
        if (_ch >= 65 && _ch <= 90) {
            return _ch + 32;
        }
        if (_ch >= 192 && _ch <= 214) {
            return _ch + 32;
        }
        if (_ch >= 216 && _ch <= 222) {
            return _ch + 32;
        }
        if (_ch >= 393 && _ch <= 394) {
            return _ch + 205;
        }
        if (_ch >= 433 && _ch <= 434) {
            return _ch + 217;
        }
        if (_ch >= 904 && _ch <= 906) {
            return _ch + 37;
        }
        if (_ch >= 910 && _ch <= 911) {
            return _ch + 63;
        }
        if (_ch >= 913 && _ch <= 929) {
            return _ch + 32;
        }
        if (_ch >= 931 && _ch <= 939) {
            return _ch + 32;
        }
        if (_ch >= 1021 && _ch <= 1023) {
            return _ch -130;
        }
        if (_ch >= 1024 && _ch <= 1039) {
            return _ch + 80;
        }
        if (_ch >= 1040 && _ch <= 1071) {
            return _ch + 32;
        }
        if (_ch >= 1329 && _ch <= 1366) {
            return _ch + 48;
        }
        if (_ch >= 4256 && _ch <= 4293) {
            return _ch + 7264;
        }
        if (_ch >= 7944 && _ch <= 7951) {
            return _ch -8;
        }
        if (_ch >= 7960 && _ch <= 7965) {
            return _ch -8;
        }
        if (_ch >= 7976 && _ch <= 7983) {
            return _ch -8;
        }
        if (_ch >= 7992 && _ch <= 7999) {
            return _ch -8;
        }
        if (_ch >= 8008 && _ch <= 8013) {
            return _ch -8;
        }
        if (_ch >= 8040 && _ch <= 8047) {
            return _ch -8;
        }
        if (_ch >= 8072 && _ch <= 8079) {
            return _ch -8;
        }
        if (_ch >= 8088 && _ch <= 8095) {
            return _ch -8;
        }
        if (_ch >= 8104 && _ch <= 8111) {
            return _ch -8;
        }
        if (_ch >= 8120 && _ch <= 8121) {
            return _ch -8;
        }
        if (_ch >= 8122 && _ch <= 8123) {
            return _ch -74;
        }
        if (_ch >= 8136 && _ch <= 8139) {
            return _ch -86;
        }
        if (_ch >= 8152 && _ch <= 8153) {
            return _ch -8;
        }
        if (_ch >= 8154 && _ch <= 8155) {
            return _ch -100;
        }
        if (_ch >= 8168 && _ch <= 8169) {
            return _ch -8;
        }
        if (_ch >= 8170 && _ch <= 8171) {
            return _ch -112;
        }
        if (_ch >= 8184 && _ch <= 8185) {
            return _ch -128;
        }
        if (_ch >= 8186 && _ch <= 8187) {
            return _ch -126;
        }
        if (_ch >= 11264 && _ch <= 11310) {
            return _ch + 48;
        }
        if (_ch >= 11390 && _ch <= 11391) {
            return _ch -10815;
        }
        if (_ch >= 65313 && _ch <= 65338) {
            return _ch + 32;
        }
        return _ch;
    }

    private static int toUpperCaseInt(char _ch) {
        if (isUnassigned(_ch)) {
            return _ch;
        }
        if (isOtherSpace(_ch)) {
            return _ch;
        }
        if (MathExpUtil.isDigit(_ch)) {
            return _ch;
        }
        if (isOtherDigit(_ch)) {
            return _ch;
        }
        if (isRomanDigits(_ch)) {
            if (_ch >= 8544 + 16) {
                return _ch-16;
            }
            return _ch;
        }
        if (isSensibleOtherLetter(_ch)) {
            if (_ch >= 9398 + 26) {
                return _ch-26;
            }
            return _ch;
        }
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 383) {
            return 83;
        }
        if (_ch == 410) {
            return 573;
        }
        if (_ch == 457) {
            return 455;
        }
        if (_ch == 454) {
            return 452;
        }
        if (_ch == 460) {
            return 458;
        }
        if (_ch == 477) {
            return 398;
        }
        if (_ch == 499) {
            return 497;
        }
        if (_ch == 592) {
            return 11375;
        }
        if (_ch == 1008) {
            return 922;
        }
        if (_ch == 1231) {
            return 1216;
        }
        if (_ch == 7936) {
            return 7944;
        }
        if (_ch == 392) {
            return _ch -1;
        }
        if (_ch == 396) {
            return _ch -1;
        }
        if (_ch == 402) {
            return _ch -1;
        }
        if (_ch == 409) {
            return _ch -1;
        }
        if (_ch == 424) {
            return _ch -1;
        }
        if (_ch == 429) {
            return _ch -1;
        }
        if (_ch == 432) {
            return _ch -1;
        }
        if (_ch == 441) {
            return _ch -1;
        }
        if (_ch == 445) {
            return _ch -1;
        }
        if (_ch == 453) {
            return _ch -1;
        }
        if (_ch == 456) {
            return _ch -1;
        }
        return toUpperCaseDefThree(_ch);
    }

    private static int toUpperCaseDefThree(char _ch) {
        if (_ch == 613) {
            return _ch + 42280;
        }
        if (_ch == 614) {
            return _ch + 42308;
        }
        if (_ch == 616) {
            return _ch -209;
        }
        if (_ch == 617) {
            return _ch -211;
        }
        if (_ch == 619) {
            return _ch + 10743;
        }
        if (_ch == 623) {
            return _ch -211;
        }
        if (_ch == 643) {
            return _ch -218;
        }
        if (_ch == 658) {
            return _ch -219;
        }
        if (_ch == 7545) {
            return _ch + 35332;
        }
        if (_ch == 7549) {
            return _ch + 3814;
        }
        if (_ch == 459) {
            return _ch -1;
        }
        if (_ch == 498) {
            return _ch -1;
        }
        return toUpperCaseDefFour(_ch);
    }

    private static int toUpperCaseIntCheck(char _ch) {
        if (isRomanDigits(_ch)) {
            if (_ch >= 8544 + 16) {
                return _ch-16;
            }
            return _ch;
        }
        if (isSensibleOtherLetter(_ch)) {
            if (_ch >= 9398 + 26) {
                return _ch-26;
            }
            return _ch;
        }
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 383) {
            return 83;
        }
        if (_ch == 410) {
            return 573;
        }
        if (_ch == 457) {
            return 455;
        }
        if (_ch == 454) {
            return 452;
        }
        if (_ch == 460) {
            return 458;
        }
        if (_ch == 477) {
            return 398;
        }
        if (_ch == 499) {
            return 497;
        }
        if (_ch == 592) {
            return 11375;
        }
        if (_ch == 1008) {
            return 922;
        }
        if (_ch == 1231) {
            return 1216;
        }
        if (_ch == 7936) {
            return 7944;
        }
        if (_ch == 392) {
            return _ch -1;
        }
        if (_ch == 396) {
            return _ch -1;
        }
        if (_ch == 402) {
            return _ch -1;
        }
        if (_ch == 409) {
            return _ch -1;
        }
        if (_ch == 424) {
            return _ch -1;
        }
        if (_ch == 429) {
            return _ch -1;
        }
        if (_ch == 432) {
            return _ch -1;
        }
        if (_ch == 441) {
            return _ch -1;
        }
        if (_ch == 445) {
            return _ch -1;
        }
        if (_ch == 453) {
            return _ch -1;
        }
        if (_ch == 456) {
            return _ch -1;
        }
        return toUpperCaseDefThree(_ch);
    }

    private static int toUpperCaseDefTwo(char _ch) {
        if (_ch % 2 == 0) {
            if (_ch >= 314 && _ch <= 328) {
                return _ch -1;
            }
            if (_ch >= 378 && _ch <= 382) {
                return _ch -1;
            }
            if (_ch >= 436 && _ch <= 438) {
                return _ch -1;
            }
            if (_ch >= 462 && _ch <= 476) {
                return _ch -1;
            }
            if (_ch >= 1218 && _ch <= 1230) {
                return _ch -1;
            }
            if (_ch >= 11368 && _ch <= 11372) {
                return _ch -1;
            }
            if (_ch >= 11500 && _ch <= 11502) {
                return _ch -1;
            }
            if (_ch >= 42874 && _ch <= 42876) {
                return _ch -1;
            }
        } else {
            if (_ch >= 257 && _ch <= 303) {
                return _ch -1;
            }
            if (_ch >= 307 && _ch <= 311) {
                return _ch -1;
            }
            if (_ch >= 331 && _ch <= 375) {
                return _ch -1;
            }
            if (_ch >= 387 && _ch <= 389) {
                return _ch -1;
            }
            if (_ch >= 417 && _ch <= 421) {
                return _ch -1;
            }
            if (_ch >= 479 && _ch <= 495) {
                return _ch -1;
            }
            if (_ch >= 505 && _ch <= 543) {
                return _ch -1;
            }
            if (_ch >= 547 && _ch <= 563) {
                return _ch -1;
            }
            if (_ch >= 583 && _ch <= 591) {
                return _ch -1;
            }
            if (_ch >= 881 && _ch <= 883) {
                return _ch -1;
            }
            if (_ch >= 985 && _ch <= 1007) {
                return _ch -1;
            }
            if (_ch >= 1121 && _ch <= 1153) {
                return _ch -1;
            }
            if (_ch >= 1163 && _ch <= 1215) {
                return _ch -1;
            }
            if (_ch >= 1233 && _ch <= 1319) {
                return _ch -1;
            }
            if (_ch >= 7681 && _ch <= 7829) {
                return _ch -1;
            }
            if (_ch >= 7841 && _ch <= 7935) {
                return _ch -1;
            }
            if (_ch >= 11393 && _ch <= 11491) {
                return _ch -1;
            }
            if (_ch >= 42561 && _ch <= 42605) {
                return _ch -1;
            }
            if (_ch >= 42625 && _ch <= 42647) {
                return _ch -1;
            }
            if (_ch >= 42787 && _ch <= 42799) {
                return _ch -1;
            }
            if (_ch >= 42803 && _ch <= 42863) {
                return _ch -1;
            }
            if (_ch >= 42879 && _ch <= 42887) {
                return _ch -1;
            }
            if (_ch >= 42897 && _ch <= 42899) {
                return _ch -1;
            }
            if (_ch >= 42913 && _ch <= 42921) {
                return _ch -1;
            }
        }
        return toUpperCaseDef(_ch);
    }

    private static int toUpperCaseIntCheckLow(char _ch) {
        if (isRomanDigits(_ch)) {
            return _ch-16;
        }
        if (isSensibleOtherLetter(_ch)) {
            return _ch-26;
        }
        if (isOtherMathSymbol(_ch)) {
            return _ch;
        }
        if (_ch == 383) {
            return 83;
        }
        if (_ch == 410) {
            return 573;
        }
        if (_ch == 457) {
            return 455;
        }
        if (_ch == 454) {
            return 452;
        }
        if (_ch == 460) {
            return 458;
        }
        if (_ch == 477) {
            return 398;
        }
        if (_ch == 499) {
            return 497;
        }
        if (_ch == 592) {
            return 11375;
        }
        if (_ch == 1008) {
            return 922;
        }
        if (_ch == 1231) {
            return 1216;
        }
        if (_ch == 7936) {
            return 7944;
        }
        if (_ch == 392) {
            return _ch -1;
        }
        if (_ch == 396) {
            return _ch -1;
        }
        if (_ch == 402) {
            return _ch -1;
        }
        if (_ch == 409) {
            return _ch -1;
        }
        if (_ch == 424) {
            return _ch -1;
        }
        if (_ch == 429) {
            return _ch -1;
        }
        if (_ch == 432) {
            return _ch -1;
        }
        if (_ch == 441) {
            return _ch -1;
        }
        if (_ch == 445) {
            return _ch -1;
        }
        return toUpperCaseDefFour(_ch);
    }

    private static int toUpperCaseDefFour(char _ch) {
        if (_ch == 501) {
            return _ch -1;
        }
        if (_ch == 572) {
            return _ch -1;
        }
        if (_ch == 578) {
            return _ch -1;
        }
        if (_ch == 887) {
            return _ch -1;
        }
        if (_ch == 1016) {
            return _ch -1;
        }
        if (_ch == 1019) {
            return _ch -1;
        }
        if (_ch == 8580) {
            return _ch -1;
        }
        if (_ch == 11361) {
            return _ch -1;
        }
        if (_ch == 11379) {
            return _ch -1;
        }
        if (_ch == 11382) {
            return _ch -1;
        }
        if (_ch == 11507) {
            return _ch -1;
        }
        if (_ch == 42892) {
            return _ch -1;
        }
        if (_ch == 181) {
            return _ch + 743;
        }
        if (_ch == 255) {
            return _ch + 121;
        }
        if (_ch == 305) {
            return _ch -232;
        }
        if (_ch == 384) {
            return _ch + 195;
        }
        if (_ch == 405) {
            return _ch + 97;
        }
        if (_ch == 414) {
            return _ch + 130;
        }
        if (_ch == 447) {
            return _ch + 56;
        }
        if (_ch == 593) {
            return _ch + 10780;
        }
        if (_ch == 594) {
            return _ch + 10782;
        }
        if (_ch == 595) {
            return _ch -210;
        }
        if (_ch == 596) {
            return _ch -206;
        }
        if (_ch == 601) {
            return _ch -202;
        }
        if (_ch == 603) {
            return _ch -203;
        }
        if (_ch == 608) {
            return _ch -205;
        }
        if (_ch == 611) {
            return _ch -207;
        }
        if (_ch == 625) {
            return _ch + 10749;
        }
        if (_ch == 626) {
            return _ch -213;
        }
        if (_ch == 629) {
            return _ch -214;
        }
        if (_ch == 637) {
            return _ch + 10727;
        }
        if (_ch == 640) {
            return _ch -218;
        }
        if (_ch == 648) {
            return _ch -218;
        }
        if (_ch == 649) {
            return _ch -69;
        }
        if (_ch == 652) {
            return _ch -71;
        }
        if (_ch == 837) {
            return _ch + 84;
        }
        if (_ch == 940) {
            return _ch -38;
        }
        if (_ch == 962) {
            return _ch -31;
        }
        if (_ch == 972) {
            return _ch -64;
        }
        if (_ch == 976) {
            return _ch -62;
        }
        if (_ch == 977) {
            return _ch -57;
        }
        if (_ch == 981) {
            return _ch -47;
        }
        if (_ch == 982) {
            return _ch -54;
        }
        if (_ch == 983) {
            return _ch -8;
        }
        if (_ch == 1009) {
            return _ch -80;
        }
        if (_ch == 1010) {
            return _ch + 7;
        }
        if (_ch == 1013) {
            return _ch -96;
        }
        if (_ch == 7835) {
            return _ch -59;
        }
        if (_ch == 8017) {
            return _ch + 8;
        }
        if (_ch == 8019) {
            return _ch + 8;
        }
        if (_ch == 8021) {
            return _ch + 8;
        }
        if (_ch == 8023) {
            return _ch + 8;
        }
        if (_ch == 8115) {
            return _ch + 9;
        }
        if (_ch == 8126) {
            return _ch -7205;
        }
        if (_ch == 8131) {
            return _ch + 9;
        }
        if (_ch == 8165) {
            return _ch + 7;
        }
        if (_ch == 8179) {
            return _ch + 9;
        }
        if (_ch == 8526) {
            return _ch -28;
        }
        if (_ch == 11365) {
            return _ch -10795;
        }
        if (_ch == 11366) {
            return _ch -10792;
        }
        if (_ch == 11559) {
            return _ch -7264;
        }
        if (_ch == 11565) {
            return _ch -7264;
        }
        return toUpperCaseDefTwo(_ch);
    }

    private static int toUpperCaseDef(char _ch) {
        if (_ch >= 97 && _ch <= 122) {
            return _ch -32;
        }
        if (_ch >= 224 && _ch <= 246) {
            return _ch -32;
        }
        if (_ch >= 248 && _ch <= 254) {
            return _ch -32;
        }
        if (_ch >= 575 && _ch <= 576) {
            return _ch + 10815;
        }
        if (_ch >= 598 && _ch <= 599) {
            return _ch -205;
        }
        if (_ch >= 650 && _ch <= 651) {
            return _ch -217;
        }
        if (_ch >= 891 && _ch <= 893) {
            return _ch + 130;
        }
        if (_ch >= 941 && _ch <= 943) {
            return _ch -37;
        }
        if (_ch >= 945 && _ch <= 961) {
            return _ch -32;
        }
        if (_ch >= 963 && _ch <= 971) {
            return _ch -32;
        }
        if (_ch >= 973 && _ch <= 974) {
            return _ch -63;
        }
        if (_ch >= 1072 && _ch <= 1103) {
            return _ch -32;
        }
        if (_ch >= 1104 && _ch <= 1119) {
            return _ch -80;
        }
        if (_ch >= 1377 && _ch <= 1414) {
            return _ch -48;
        }
        if (_ch >= 7937 && _ch <= 7943) {
            return _ch + 8;
        }
        if (_ch >= 7952 && _ch <= 7957) {
            return _ch + 8;
        }
        if (_ch >= 7968 && _ch <= 7975) {
            return _ch + 8;
        }
        if (_ch >= 7984 && _ch <= 7991) {
            return _ch + 8;
        }
        if (_ch >= 8000 && _ch <= 8005) {
            return _ch + 8;
        }
        if (_ch >= 8032 && _ch <= 8039) {
            return _ch + 8;
        }
        if (_ch >= 8048 && _ch <= 8049) {
            return _ch + 74;
        }
        if (_ch >= 8050 && _ch <= 8053) {
            return _ch + 86;
        }
        if (_ch >= 8054 && _ch <= 8055) {
            return _ch + 100;
        }
        if (_ch >= 8056 && _ch <= 8057) {
            return _ch + 128;
        }
        if (_ch >= 8058 && _ch <= 8059) {
            return _ch + 112;
        }
        if (_ch >= 8060 && _ch <= 8061) {
            return _ch + 126;
        }
        if (_ch >= 8064 && _ch <= 8071) {
            return _ch + 8;
        }
        if (_ch >= 8080 && _ch <= 8087) {
            return _ch + 8;
        }
        if (_ch >= 8096 && _ch <= 8103) {
            return _ch + 8;
        }
        if (_ch >= 8112 && _ch <= 8113) {
            return _ch + 8;
        }
        if (_ch >= 8144 && _ch <= 8145) {
            return _ch + 8;
        }
        if (_ch >= 8160 && _ch <= 8161) {
            return _ch + 8;
        }
        if (_ch >= 11312 && _ch <= 11358) {
            return _ch -48;
        }
        if (_ch >= 11520 && _ch <= 11557) {
            return _ch -7264;
        }
        if (_ch >= 65345 && _ch <= 65370) {
            return _ch -32;
        }
        return _ch;
    }

}
