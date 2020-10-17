package code.expressionlanguage.common;

import code.expressionlanguage.inherits.MappingPairs;
import code.expressionlanguage.inherits.Matching;
import code.expressionlanguage.inherits.MatchingEnum;

import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
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
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringUtil.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                String value_ = _varTypes.getVal(sub_);
                int max_ = str_.length() -1;
                int j_ = getMaxIndex(str_, max_);
                if (StringUtil.quickEq(value_, SUB_TYPE)) {
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
            if (StringUtil.isDollarWordChar(_type.charAt(i))) {
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
            if (StringUtil.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                tryReplaceType(_varTypes, str_, sub_);
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
                tryReplaceType(_varTypes, str_, sub_);
            } else {
                sub_ = _type.substring(diese_);
                str_.append(sub_);
            }
        }
        return str_.toString();
    }
    private static void tryReplaceType(StringMap<String> _varTypes, StringBuilder _str, String _sub) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        String value_ = _varTypes.getVal(_sub);
        if (value_.startsWith(SUB_TYPE)) {
            if (isSubOrSubChar(_str,j_)) {
                _str.append(value_.substring(SUB_TYPE.length()));
            } else {
                _str.insert(j_ +1, SUB_TYPE);
                _str.append(value_.substring(SUB_TYPE.length()));
            }
        } else if (value_.startsWith(SUP_TYPE)) {
            if (isSubOrSubChar(_str,j_)) {
                _str.append(value_.substring(SUP_TYPE.length()));
            } else {
                _str.insert(j_ + 1, SUP_TYPE);
                _str.append(value_.substring(SUP_TYPE.length()));
            }
        } else {
            _str.append(value_);
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
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringUtil.isDollarWordChar(_type.charAt(i))) {
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
        if (StringUtil.quickEq(value_,SUB_TYPE)) {
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
            if (StringUtil.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                String value_ = _varTypes.getVal(sub_);
                int max_ = str_.length() -1;
                int j_ = getMaxIndex(str_, max_);
                if (StringUtil.quickEq(value_, SUB_TYPE)) {
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
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
        int len_ = _params.size();
        int argCall_ = len_ - 1;
        for (int i = IndexConstants.SECOND_INDEX; i < argCall_; i++) {
            String arg_ = _args.get(i);
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
            Matching match_ = new Matching();
            match_.setArg(arg_);
            match_.setParam(param_);
            match_.setMatchEq(MatchingEnum.SUP);
            pairsArgParam_.add(match_);
        }
        String a_ = _args.last();
        String p_ = _params.last();
        boolean add_ = true;
        if (StringUtil.quickEq(a_, SUB_TYPE)) {
            if (!StringUtil.quickEq(p_, SUB_TYPE)) {
                return null;
            }
            add_ = false;
        }
        if (StringUtil.quickEq(p_, SUB_TYPE)) {
            add_ = false;
        }
        if (!StringUtil.quickEq(p_, _objectType) && add_) {
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
        if (_j < 0) {
            return _j;
        }
        int j_ = _j;
        while (j_ < _len) {
            char ch_ = _string.charAt(j_);
            if (!Character.isWhitespace(ch_)) {
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

    public static int countPrefix(String _varRef) {
        int count_ = 0;
        int len_ = _varRef.length();
        for (int i = 0; i < len_; i++) {
            if (_varRef.charAt(i) != '#') {
                break;
            }
            count_++;
        }
        return count_;
    }
    public static String skipPrefix(String _varRef) {
        int count_ = 0;
        int len_ = _varRef.length();
        for (int i = 0; i < len_; i++) {
            if (_varRef.charAt(i) != '#') {
                break;
            }
            count_++;
        }
        return _varRef.substring(count_);
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
        if (StringUtil.isDollarWordChar(_ch)) {
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
        if (last_ <= index_) {
            return e_;
        }
        e_.setSecond(_string.substring(index_+1,last_));
        return e_;
    }
    public static int getOffset(String _str) {
        return Math.max(0, StringUtil.getFirstPrintableCharIndex(_str));
    }

    public static ArrayResult tryGetArray(String _string, IntTreeMap<String> _values, IntTreeMap<String> _operators) {
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
                String str_ = _string.substring(0, j_);
                int last_ = StringUtil.getLastPrintableCharIndex(str_);
                if (last_ < 0) {
                    return ArrayResult.ERROR;
                }
                _values.put((int) IndexConstants.FIRST_INDEX, str_);
                _operators.put(last_, _string.substring(j_));
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
            return Integer.toString(_i);
        }
        return Character.toString((char)(_i+'a'-10));
    }
}
