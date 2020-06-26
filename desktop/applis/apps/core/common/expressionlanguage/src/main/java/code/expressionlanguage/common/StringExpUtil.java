package code.expressionlanguage.common;

import code.expressionlanguage.inherits.MappingPairs;
import code.expressionlanguage.inherits.Matching;
import code.expressionlanguage.inherits.MatchingEnum;
import code.expressionlanguage.inherits.ClassArgumentMatching;

import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class StringExpUtil {
    public static final String ARR_CLASS = "[";
    public static final char ARR_BEG = '[';
    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char SEP_CLASS_CHAR = '.';
    public static final String PREFIX_VAR_TYPE = "#";
    public static final char SUB_TYPE_CHAR = '?';
    public static final char SUP_TYPE_CHAR = '!';
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";
    public static final char PREFIX_VAR_TYPE_CHAR = '#';
    public static final String INNER_TYPE = "..";

    public static final char LT = '<';

    public static final char GT = '>';

    public static final char COMMA = ',';
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
        int i_ = CustList.FIRST_INDEX;
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
        int i_ = CustList.FIRST_INDEX;
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
        int i_ = CustList.FIRST_INDEX;
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
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                String value_ = _varTypes.getVal(sub_);
                int max_ = str_.length() -1;
                int j_ = getMaxIndex(str_, max_);
                if (StringList.quickEq(value_, SUB_TYPE)) {
                    if (isSubOrSubChar(str_, j_)) {
                        j_--;
                    }
                    str_.delete(j_+1, max_+1);
                    str_.append(SUB_TYPE);
                    str_.append(_type.charAt(i));
                    var_ = false;
                    continue;

                }
                if (value_.startsWith(SUB_TYPE)) {
                    String bound_= value_.substring(SUB_TYPE.length());
                    if (isSupChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(SUB_TYPE);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    if (isNotChar(str_, j_, SUB_TYPE_CHAR)) {
                        str_.insert(j_ +1, SUB_TYPE);
                    }
                    str_.append(bound_);
                    str_.append(_type.charAt(i));
                    var_ = false;
                    continue;

                }
                if (value_.startsWith(SUP_TYPE)) {
                    String bound_= value_.substring(SUP_TYPE.length());
                    if (isSubChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(SUB_TYPE);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    if (isNotChar(str_, j_, SUP_TYPE_CHAR)) {
                        str_.insert(j_ +1, SUP_TYPE);
                    }
                    str_.append(bound_);
                    str_.append(_type.charAt(i));
                    var_ = false;
                    continue;

                }
                str_.append(value_);
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(_type.charAt(i));
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
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            quickReplaceType(str_,_type,_varTypes,diese_,i);
            str_.append(_type.charAt(i));
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
    public static String getFormattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                if (!tryReplaceType(_varTypes, str_, sub_)) {
                    return "";
                }
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(_type.charAt(i));
            var_ = false;
        }
        if (var_) {
            String sub_ = _type.substring(diese_+1);
            if (_varTypes.contains(sub_)) {
                if (!tryReplaceType(_varTypes, str_, sub_)) {
                    return "";
                }
            } else {
                sub_ = _type.substring(diese_);
                str_.append(sub_);
            }
        }
        return str_.toString();
    }
    private static boolean tryReplaceType(StringMap<String> _varTypes, StringBuilder _str, String _sub) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        String value_ = _varTypes.getVal(_sub);
        if (isSubOrSubChar(_str,j_)) {
            return false;
        }
        if (value_.startsWith(SUB_TYPE)) {
            _str.insert(j_ +1, SUB_TYPE);
            _str.append(value_.substring(SUB_TYPE.length()));
        } else if (value_.startsWith(SUP_TYPE)) {
            _str.insert(j_ +1, SUP_TYPE);
            _str.append(value_.substring(SUP_TYPE.length()));
        } else {
            _str.append(value_);
        }
        return true;
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
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                replaceReflectedType(_varTypes, str_, sub_);
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(_type.charAt(i));
            var_ = false;
        }
        if (var_) {
            String sub_ = _type.substring(diese_+1);
            if (_varTypes.contains(sub_)) {
                replaceReflectedType(_varTypes, str_, sub_);
            } else {
                sub_ = _type.substring(diese_);
                str_.append(sub_);
            }
        }
        return str_.toString();
    }

    static void replaceReflectedType(StringMap<String> _varTypes, StringBuilder _str, String _sub) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        String value_ = _varTypes.getVal(_sub);
        if (StringList.quickEq(value_,SUB_TYPE)) {
            _str.delete(j_+1,_str.length());
            _str.append(value_);
        } else if (value_.startsWith(SUB_TYPE)) {
            if (isNotChar(_str,j_,SUB_TYPE_CHAR) && isNotChar(_str,j_,SUP_TYPE_CHAR)) {
                _str.insert(j_ +1, SUB_TYPE);
            }
            _str.append(value_.substring(SUB_TYPE.length()));
        } else if (value_.startsWith(SUP_TYPE)) {
            if (isNotChar(_str,j_,SUB_TYPE_CHAR) && isNotChar(_str,j_,SUP_TYPE_CHAR)) {
                _str.insert(j_ +1, SUP_TYPE);
            }
            _str.append(value_.substring(SUP_TYPE.length()));
        } else {
            _str.append(value_);
        }
    }

    public static String getWildCardFormattedTypeParam(String _objType, String _type, StringMap<String> _varTypes) {
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                String value_ = _varTypes.getVal(sub_);
                int max_ = str_.length() -1;
                int j_ = getMaxIndex(str_, max_);
                if (StringList.quickEq(value_, SUB_TYPE)) {
                    if (isSupChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(_objType);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    return "";
                }
                if (value_.startsWith(SUB_TYPE)) {
                    String bound_= value_.substring(SUB_TYPE.length());
                    if (isSupChar(str_, j_)) {
                        str_.append(bound_);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    return "";
                }
                if (value_.startsWith(SUP_TYPE)) {
                    String bound_= value_.substring(SUP_TYPE.length());
                    if (isSubChar(str_, j_)) {
                        str_.append(bound_);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    if (isSupChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(_objType);
                        str_.append(_type.charAt(i));
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
            str_.append(_type.charAt(i));
            var_ = false;
        }
        return str_.toString();
    }

    static int getMaxIndex(StringBuilder _str, int _max) {
        int j_ = _max;
        while (j_ >= 0) {
            if (_str.charAt(j_) != ARR_BEG) {
                break;
            }
            j_--;
        }
        return j_;
    }

    static boolean isNotChar(StringBuilder _str, int _j, char _subTypeChar) {
        return _j >= 0 && _str.charAt(_j) != _subTypeChar;
    }

    static boolean isSubOrSubChar(StringBuilder _str, int _j) {
        return isSubChar(_str,_j) || isSupChar(_str, _j);
    }

    static boolean isSubChar(StringBuilder _str, int _j) {
        return _j >= 0 && _str.charAt(_j) == SUB_TYPE_CHAR;
    }

    static boolean isSupChar(StringBuilder _str, int _j) {
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
        int i_ = CustList.FIRST_INDEX;
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
        if (StringList.quickEq(_baseArrayParam, _aliasObject)) {
            int dim_ = _dArg.getDim();
            if (dim_ >= _dParam.getDim()) {
                return new MappingPairs();
            }
        }
        return null;
    }

    /**arg - param*/
    public static MappingPairs newMappingPairsFct(StringList _args, StringList _params, String _objectType) {
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
        int len_ = _params.size();
        int argCall_ = len_ - 1;
        for (int i = CustList.SECOND_INDEX; i < argCall_; i++) {
            String arg_ = _args.get(i);
            String param_ = _params.get(i);
            if (StringList.quickEq(arg_, SUB_TYPE)) {
                if (StringList.quickEq(param_, SUB_TYPE)) {
                    continue;
                }
                return null;
            }
            if (StringList.quickEq(param_, SUB_TYPE)) {
                continue;
            }
            Matching match_ = new Matching();
            match_.setArg(arg_);
            match_.setParam(param_);
            match_.setMatchEq(MatchingEnum.SUP);
            pairsArgParam_.add(match_);
        }
        String a_ = _args.last();
        String p_ = _params.last();
        boolean add_ = true;
        if (StringList.quickEq(a_, SUB_TYPE)) {
            if (!StringList.quickEq(p_, SUB_TYPE)) {
                return null;
            }
            add_ = false;
        }
        if (StringList.quickEq(p_, SUB_TYPE)) {
            add_ = false;
        }
        if (!StringList.quickEq(p_, _objectType) && add_) {
            Matching match_ = new Matching();
            match_.setArg(a_);
            match_.setParam(p_);
            match_.setMatchEq(MatchingEnum.SUB);
            pairsArgParam_.add(match_);
        }
        MappingPairs m_ = new MappingPairs();
        m_.setPairsArgParam(pairsArgParam_);
        return m_;
    }
    /**arg - param*/
    public static MappingPairs newMappingPairs(String _generic, StringList _params) {
        int len_;
        StringList foundSuperClass_ = getAllTypes(_generic);
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
        len_ = foundSuperClass_.size();
        if (_params.size() != len_) {
            return null;
        }
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            Matching match_ = new Matching();
            String arg_ = foundSuperClass_.get(i);
            String param_ = _params.get(i);
            if (StringList.quickEq(arg_, SUB_TYPE)) {
                if (StringList.quickEq(param_, SUB_TYPE)) {
                    continue;
                }
                return null;
            }
            if (StringList.quickEq(param_, SUB_TYPE)) {
                continue;
            }
            if (arg_.startsWith(SUP_TYPE)) {
                if (param_.startsWith(SUB_TYPE)) {
                    return null;
                }
            }
            if (arg_.startsWith(SUB_TYPE)) {
                if (param_.startsWith(SUP_TYPE)) {
                    return null;
                }
            }
            boolean eqParam_ = !param_.startsWith(SUP_TYPE) && !param_.startsWith(SUB_TYPE);
            boolean eqArg_ = !arg_.startsWith(SUP_TYPE) && !arg_.startsWith(SUB_TYPE);
            if (eqParam_) {
                if (arg_.startsWith(SUB_TYPE)) {
                    return null;
                }
                if (arg_.startsWith(SUP_TYPE)) {
                    return null;
                }
                match_.setArg(arg_);
                match_.setParam(param_);
                pairsArgParam_.add(match_);
                continue;
            }
            if (eqArg_) {
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
                match_.setArg(arg_.substring(SUP_TYPE.length()));
                match_.setParam(param_.substring(SUP_TYPE.length()));
                match_.setMatchEq(MatchingEnum.SUP);
                pairsArgParam_.add(match_);
                continue;
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

    public static boolean nextCharIs(String _str, int _i, int _len, char _value) {
        if (_i < 0) {
            return false;
        }
        if (_i >= _len) {
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

    public static int nextPrintChar(int _j, int _len, String _string) {
        int j_ = _j;
        while (j_ < _len) {
            if (!Character.isWhitespace(_string.charAt(j_))) {
                break;
            }
            j_++;
        }
        if (j_ >= _len) {
            return -1;
        }
        return j_;
    }
    public static boolean isOper(String _op) {
        if(StringList.quickEq(_op, "+")) {
            return true;
        }
        if(StringList.quickEq(_op, "-")) {
            return true;
        }
        if(StringList.quickEq(_op, "!")) {
            return true;
        }
        if(StringList.quickEq(_op, "*")) {
            return true;
        }
        if(StringList.quickEq(_op, "/")) {
            return true;
        }
        if(StringList.quickEq(_op, "%")) {
            return true;
        }
        if(StringList.quickEq(_op, "==")) {
            return true;
        }
        if(StringList.quickEq(_op, "!=")) {
            return true;
        }
        if(StringList.quickEq(_op, "<=")) {
            return true;
        }
        if(StringList.quickEq(_op, ">")) {
            return true;
        }
        if(StringList.quickEq(_op, ">=")) {
            return true;
        }
        if(StringList.quickEq(_op, "<")) {
            return true;
        }
        if(StringList.quickEq(_op, "&")) {
            return true;
        }
        if(StringList.quickEq(_op, "|")) {
            return true;
        }
        if(StringList.quickEq(_op, "^")) {
            return true;
        }
        if(StringList.quickEq(_op, "~")) {
            return true;
        }
        if(StringList.quickEq(_op, "<<")) {
            return true;
        }
        if(StringList.quickEq(_op, ">>")) {
            return true;
        }
        if(StringList.quickEq(_op, "<<<")) {
            return true;
        }
        if(StringList.quickEq(_op, ">>>")) {
            return true;
        }
        if(StringList.quickEq(_op, "<<<<")) {
            return true;
        }
        if(StringList.quickEq(_op, ">>>>")) {
            return true;
        }
        if(StringList.quickEq(_op, "++")) {
            return true;
        }
        return StringList.quickEq(_op, "--");
    }

    public static boolean startsWithKeyWord(String _found, String _keyWord) {
        return startsWithKeyWord(_found,0,_keyWord);
    }

    public static boolean startsWithKeyWord(String _found, int _start, String _keyWord) {
        if (!_found.startsWith(_keyWord,_start)) {
            return false;
        }
        if (_found.length() == _keyWord.length()+_start) {
            return true;
        }
        char first_ = _found.charAt(_keyWord.length()+_start);
        return !StringExpUtil.isTypeLeafChar(first_);
    }
    public static boolean isVar(String _string) {
        String tr_ = _string.trim();
        if (!tr_.startsWith(PREFIX_VAR_TYPE)) {
            return false;
        }
        tr_ = tr_.substring(PREFIX_VAR_TYPE.length());
        return isTypeLeaf(tr_);
    }
    public static boolean isTypeLeaf(String _string) {
        if (_string.trim().isEmpty()) {
            return false;
        }
        for (String p : StringList.splitChars(_string, SEP_CLASS_CHAR)) {
            if (!isTypeLeafPart(p.trim())) {
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
    public static boolean isTypeLeafChar(char _ch) {
        if (StringList.isDollarWordChar(_ch)) {
            return true;
        }
        return _ch == PREFIX_VAR_TYPE_CHAR;
    }

    public static String removeDottedSpaces(String _type) {
        StringBuilder b_ = new StringBuilder();
        for (String q: StringList.splitCharSep(_type, SEP_CLASS_CHAR)) {
            b_.append(q.trim());
        }
        return b_.toString();
    }

    public static String getPrettyArrayType(String _className, int _nb) {
        String cl_ = _className;
        for (int i = CustList.FIRST_INDEX; i < _nb; i++) {
            cl_ = getPrettyArrayType(cl_);
        }
        return cl_;
    }
    public static String getPrettyArrayType(String _className) {
        return StringList.concat(ARR_CLASS,_className);
    }
    /**Custom classes*/
    public static DimComp getQuickComponentBaseType(String _className) {
        int d_ = 0;
        String comp_ = getQuickComponentType(_className);
        if (comp_ == null) {
            return new DimComp(d_, _className);
        }
        d_++;
        while (true) {
            String res_ = getQuickComponentType(comp_);
            if (res_ == null) {
                return new DimComp(d_, comp_);
            }
            d_++;
            comp_ = res_;
        }
    }

    public static ClassArgumentMatching getQuickComponentType(ClassArgumentMatching _className) {
        StringList cl_ = new StringList();
        for (String c: _className.getNames()) {
            String res_ = getQuickComponentType(c);
            if (res_ == null) {
                continue;
            }
            cl_.add(res_);
        }
        return new ClassArgumentMatching(cl_);
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
}
