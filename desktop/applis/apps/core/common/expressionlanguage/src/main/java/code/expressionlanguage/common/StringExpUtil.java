package code.expressionlanguage.common;

import code.maths.litteralcom.MathExpUtil;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class StringExpUtil {
    public static final String ARR_CLASS = "[";
    public static final char ARR_BEG = '[';
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char SEP_CLASS_CHAR = '.';
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";
    public static final char PREFIX_VAR_TYPE_CHAR = '#';
    public static final String INNER_TYPE = "..";

    public static final char LT = '<';

    public static final char GT = '>';

    public static final char COMMA = ',';
    public static final char EXTENDS_DEF = ':';
    public static final char SEP_BOUNDS = '&';

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
        StackType stType_ = new StackType();
        StringBuilder out_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            stType_.loopSepComma(types_,out_,curChar_);
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
            if (p.startsWith(SUB_TYPE)) {
                list_.add(p);
            }
            if (p.startsWith(SUP_TYPE)) {
                list_.add(p);
            }
        }
        return list_;
    }
    public static String getId(String _type) {
        return getIdFromAllTypes(getQuickComponentBase(_type));
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
        StackType stType_ = new StackType();
        StringBuilder out_ = new StringBuilder();
        StringBuilder id_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            stType_.loopAllTypes(types_,id_,out_,curChar_);
            i_++;
        }
        types_.add(0, id_.toString());
        return types_;
    }

    public static StringList getAllPartInnerTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        StackType stType_ = new StackType();
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (stType_.loopAllPartInnerTypes(_type,i_,types_,out_,curChar_)) {
                i_++;
            }
            i_++;
        }
        types_.add(out_.toString());
        return types_;
    }

    public static String getWildCardFormattedTypeReturn(String _type, StringMap<String> _varTypes) {
        AbstractReplacingType repl_ = new ReturnReplacingType();
        return repl_.formattedType(_type,_varTypes);
    }

    public static String getWildCardFormattedTypeParam(String _objType, String _type, StringMap<String> _varTypes) {
        AbstractReplacingType repl_ = new ParamReplacingType(_objType);
        return repl_.formattedType(_type,_varTypes);
    }

    public static String getQuickFormattedType(String _type, StringMap<String> _varTypes) {
        AbstractReplacingType repl_ = new QuickReplacingType();
        return repl_.formattedType(_type,_varTypes);
    }

    public static String getFormattedType(String _type, StringMap<String> _varTypes) {
        AbstractReplacingType repl_ = new TryReplacingType();
        return repl_.formattedType(_type,_varTypes);
    }

    public static String getReflectFormattedType(String _type, StringMap<String> _varTypes) {
        AbstractReplacingType repl_ = new ReflectReplacingType();
        return repl_.formattedType(_type,_varTypes);
    }

    public static StringMap<String> getVarTypes(StringList _paramTypesValues, String _className){
        StringList types_ = StringExpUtil.getAllTypes(_className);
        return getVarTypes(_paramTypesValues,types_);
    }
    public static StringMap<String> getVarTypes(StringList _paramTypesValues, StringList _typesIn){
        StringMap<String> varTypes_ = new StringMap<String>();
        int i_ = IndexConstants.FIRST_INDEX;
        for (String t: _paramTypesValues) {
            i_++;
            if (!_typesIn.isValidIndex(i_)) {
                return varTypes_;
            }
            String arg_ = _typesIn.get(i_);
            varTypes_.put(t, arg_);
        }
        return varTypes_;
    }
    /** Splits by double dots the input string into parts<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "int..long" => ["int","long"]<br/>
     Sample 3: "Pair&lt;int..long&gt;" => ["Pair&lt;int..long&gt;"]<br/>
     */
    public static StringList getAllInnerTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        StackType stType_ = new StackType();
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (stType_.loopAllInnerTypes(_type,i_,types_,out_,curChar_)) {
                i_++;
            }
            i_++;
        }
        types_.add(out_.toString());
        return types_;
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

    public static boolean startsWith(CharSequence _string,char _char) {
        return _string.length()>0&&_string.charAt(0)==_char;
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
        if(isUn(_op)) {
            return true;
        }
        return isBin(_op);
    }
    public static boolean isUn(String _op) {
        if(isUnOther(_op)) {
            return true;
        }
        return isUnNum(_op);
    }
    public static boolean isBin(String _op) {
        if(isBinNum(_op)) {
            return true;
        }
        if(isLogical(_op)) {
            return true;
        }
        if(isBitwise(_op)) {
            return true;
        }
        if(isEq(_op)) {
            return true;
        }
        if(isCmp(_op)) {
            return true;
        }
        return isShiftOper(_op);
    }
    public static boolean isUnNum(String _op) {
        if(StringUtil.quickEq(_op, "+")) {
            return true;
        }
        return StringUtil.quickEq(_op, "-");
    }
    public static boolean isUnOther(String _op) {
        if(StringUtil.quickEq(_op, "!")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "~")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "++")) {
            return true;
        }
        return StringUtil.quickEq(_op, "--");
    }
    public static boolean isBinNum(String _op) {
        if(StringUtil.quickEq(_op, "+")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "-")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "*")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "/")) {
            return true;
        }
        return StringUtil.quickEq(_op, "%");
    }
    public static boolean isEq(String _op) {
        return StringUtil.quickEq(_op, "==") || StringUtil.quickEq(_op, "!=");
    }
    public static boolean isCmp(String _op) {
        if(StringUtil.quickEq(_op, "<=")) {
            return true;
        }
        if(StringUtil.quickEq(_op, ">")) {
            return true;
        }
        if(StringUtil.quickEq(_op, ">=")) {
            return true;
        }
        return StringUtil.quickEq(_op, "<");
    }
    public static boolean isBitwise(String _op) {
        if(StringUtil.quickEq(_op, "&")) {
            return true;
        }
        if(StringUtil.quickEq(_op, "|")) {
            return true;
        }
        return StringUtil.quickEq(_op, "^");
    }
    public static boolean isLogical(String _op) {
        return StringUtil.quickEq(_op, "&&") || StringUtil.quickEq(_op, "||");
    }
    public static boolean isShiftOper(String _op) {
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
        return StringUtil.quickEq(_op, ">>>>");
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
            if (c == '*' || c == '+') {
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

    public static boolean commonCorrectType(String _genericClass, String _fct, Ints _rep) {
        String idCl_ = StringExpUtil.getIdFromAllTypes(_genericClass);
        String compo_ = StringExpUtil.getQuickComponentBaseType(idCl_).getComponent();
        StringList inners_ = getAllInnerTypes(_genericClass);
        int len_ = inners_.size();
        if (!StringUtil.quickEq(compo_, _fct)) {
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
        int bk_ = getBackPrintChar(_string, _string.length());
        if (!nextCharIs(_string, bk_, _string.length(), ']')) {
            return errOrNone(bk_);
        }
        int bkTwo_ = getBackPrintChar(_string, bk_);
        if (!nextCharIs(_string, bkTwo_, _string.length(), '[')) {
            return errOrNone(bkTwo_);
        }
        return errorOrOk(_string, _values, _operators, bkTwo_);
    }

    private static ArrayResult errOrNone(int _bk) {
        if (_bk < 0) {
            return ArrayResult.ERROR;
        }
        return ArrayResult.NONE;
    }

    private static ArrayResult errorOrOk(String _string, StrTypes _values, StrTypes _operators, int _j) {
        String str_ = _string.substring(0, _j);
        int last_ = StringUtil.getLastPrintableCharIndex(str_);
        if (last_ < 0) {
            return ArrayResult.ERROR;
        }
        _values.addEntry(IndexConstants.FIRST_INDEX, str_);
        _operators.addEntry(_j, _string.substring(_j));
        return ArrayResult.OK;
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
                int r_ = NumberUtil.mod(63,q_);
                int m_ = NumberUtil.quot(63, q_);
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
            StringBuilder str_ = base(-(_i+1), radix_);
            str_.insert(0,"-");
            int last_ = str_.length() - 1;
            str_.setCharAt(last_, (char) (str_.charAt(last_)+1));
            return str_.toString();
        }
        if (_i >= 0) {
            return base(_i, radix_).toString();
        }
        StringBuilder str_ = base(-_i, radix_);
        str_.insert(0,"-");
        return str_.toString();
    }

    public static String toLongGeneHex(long _i) {
        return baseHex(_i,16);
    }

    public static String toLongGeneOct(long _i) {
        return baseOct(_i,'0' + '1',22);
    }

    public static String toLongGeneBin(long _i) {
        return baseTwo(_i, 64);
    }

    private static StringBuilder base(long _i, int _base) {
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
        return baseHex(_i, 8);
    }

    public static String toGeneOct(int _i) {
        return baseOct(_i,'0'+'3', 11);
    }

    public static String toGeneBin(int _i) {
        return baseTwo(_i, 32);
    }

    public static String toShortGeneHex(int _i) {
        return baseHex(_i, 4);
    }

    public static String toShortGeneOct(int _i) {
        return baseOct(_i,'0'+'1', 6);
    }

    public static String toShortGeneBin(short _i) {
        return baseTwo(_i, 16);
    }

    public static String toByteGeneHex(int _i) {
        return baseHex(_i, 2);
    }

    private static String baseHex(long _i, int _b) {
        if (_i >= 0) {
            return base(_i, 16).toString();
        }
        StringBuilder str_ = base(-_i - 1, 16);
        int len_ = str_.length();
        int nbZeros_ = _b - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < _b; i++) {
            char c_ = str_.charAt(i);
            if (c_ >= '6' &&c_ <= '9') {
                str_.setCharAt(i,(char)('6' + '9'-c_));
                continue;
            }
            str_.setCharAt(i,(char)('a' + '5'-c_));
        }
        return str_.toString();
    }

    public static String toByteGeneOct(int _i) {
        return baseOct(_i, '0' + '3', 3);
    }

    private static String baseOct(long _i, int _sum, int _b) {
        if (_i >= 0) {
            return base(_i, 8).toString();
        }
        StringBuilder str_ = base(-_i - 1, 8);
        int len_ = str_.length();
        int nbZeros_ = _b - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        char c_ = str_.charAt(0);
        str_.setCharAt(0,(char)(_sum-c_));
        for (int i = 1; i < _b; i++) {
            c_ = str_.charAt(i);
            str_.setCharAt(i,(char)('0' + '7'-c_));
        }
        return str_.toString();
    }

    public static String toByteGeneBin(byte _i) {
        return baseTwo(_i, 8);
    }

    private static String baseTwo(long _i, int _b) {
        if (_i >= 0) {
            return base(_i, 2).toString();
        }
        StringBuilder str_ = base(-_i - 1, 2);
        int len_ = str_.length();
        int nbZeros_ = _b - len_;
        for (int i = 0; i < nbZeros_; i++) {
            str_.insert(0,'0');
        }
        for (int i = 0; i < _b; i++) {
            char c_ = str_.charAt(i);
            str_.setCharAt(i,(char)('0' + '1' -c_));
        }
        return str_.toString();
    }

    private static String toSingleChar(int _i) {
        if (_i < 10) {
            return Long.toString(_i);
        }
        return Character.toString((char)(_i+'a'-10));
    }

    public static StringList getDollarWordSeparators(String _str) {
        return new AdvWordSplit().loop(_str);
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

}
