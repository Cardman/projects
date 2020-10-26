package code.expressionlanguage.options;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.stds.ErrorCat;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class KeyWords {
    private static final String CONTINUE = "Continue";
    private static final String INSTANCEOF = "Instanceof";
    private static final String INTERFACE = "Interface";
    private static final String ABSTRACT = "Abstract";
    private static final String ELSEIF = "Elseif";
    private static final String CAST = "Cast";
    private static final String EXPLICIT = "Explicit";
    private static final String FOR = "For";
    private static final String VAR = "Var";
    private static final String STATIC = "Static";
    private static final String STATIC_CALL = "StaticCall";
    private static final String NULL = "Null";
    private static final String CLASS = "Class";
    private static final String FALSE = "False";
    private static final String FINAL = "Final";
    private static final String BREAK = "Break";
    private static final String IF = "If";
    private static final String NEW = "New";
    private static final String WHILE = "While";
    private static final String RETURN = "Return";
    private static final String TRUE = "True";
    private static final String PUBLIC = "Public";
    private static final String PRIVATE = "Private";
    private static final String ANNOTATION = "Annotation";
    private static final String TO_STRING = "ToString";
    private static final String NB_SUF_BYTE_PRIM = "NbSufBytePrim";
    private static final String NB_SUF_BYTE = "NbSufByte";
    private static final String NB_SUF_SHORT_PRIM = "NbSufShortPrim";
    private static final String NB_SUF_SHORT = "NbSufShort";
    private static final String NB_SUF_CHARACTER_PRIM = "NbSufCharacterPrim";
    private static final String NB_SUF_CHARACTER = "NbSufCharacter";
    private static final String NB_SUF_INTEGER_PRIM = "NbSufIntegerPrim";
    private static final String NB_SUF_INTEGER = "NbSufInteger";
    private static final String NB_SUF_LONG_PRIM = "NbSufLongPrim";
    private static final String NB_SUF_LONG = "NbSufLong";
    private static final String NB_SUF_FLOAT_PRIM = "NbSufFloatPrim";
    private static final String NB_SUF_FLOAT = "NbSufFloat";
    private static final String NB_SUF_DOUBLE_PRIM = "NbSufDoublePrim";
    private static final String NB_SUF_DOUBLE = "NbSufDouble";
    private static final String ITER = "Iter";
    private static final String VALUE = "Value";
    private static final String ELSE = "Else";
    private static final String CATCH = "Catch";
    private static final String THROW = "Throw";
    private static final String TRY = "Try";
    private static final String THIS = "This";
    private static final String SUPER = "Super";
    private static final String CASE = "Case";
    private static final String DO = "Do";
    private static final String ENUM = "Enum";
    private static final String SWITCH = "Switch";
    private static final String INTERN = "Intern";
    private static final String NORMAL = "Normal";
    private static final String ESC_TAB = "EscTab";
    private static final String NB_HEX = "NbHex";
    private static final String NB_HEX_END = "NbHexEnd";
    private static final String NB_BIN = "NbBin";
    private static final String THAT = "That";
    private static final String BOOL = "Bool";
    private static final String VALUES = "Values";
    private static final String LAMBDA = "Lambda";
    private static final String VARARG = "Vararg";
    private static final String ID = "Id";
    private static final String FOREACH = "Foreach";
    private static final String NB_EXP_BIN = "NbExpBin";
    private static final String CLASSCHOICE = "Classchoice";
    private static final String FIRSTOPT = "Firstopt";
    private static final String PACKAGE = "Package";
    private static final String FINALLY = "Finally";
    private static final String ESC_UNICODE = "EscUnicode";
    private static final String THISACCESS = "Thisaccess";
    private static final String VALUE_OF = "ValueOf";
    private static final String DEFAULT_VALUE = "DefaultValue";
    private static final String ESC_LINE = "EscLine";
    private static final String OPERATOR = "Operator";
    private static final String INTERFACES = "Interfaces";
    private static final String SUPERACCESS = "Superaccess";
    private static final String ESC_BOUND = "EscBound";
    private static final String ESC_FORM = "EscForm";
    private static final String ESC_FEED = "EscFeed";
    private static final String NB_EXP_DEC = "NbExpDec";
    private static final String PROTECTED = "Protected";
    private static final String DEFAULT = "Default";
    private static final String PARENT = "Parent";
    private String keyWordValue = "$value";
    private String keyWordVar = "$var";
    private String keyWordInterfaces = "$interfaces";
    private String keyWordPublic = "$public";
    private String keyWordPackage = "$package";
    private String keyWordProtected = "$protected";
    private String keyWordPrivate = "$private";
    private String keyWordInterface = "$interface";
    private String keyWordAnnotation = "$annotation";
    private String keyWordClass = "$class";
    private String keyWordEnum = "$enum";
    private String keyWordStatic = "$static";
    private String keyWordStaticCall = "$staticCall";
    private String keyWordAbstract = "$abstract";
    private String keyWordFinal = "$final";
    private String keyWordNormal = "$normal";

    private String keyWordIter = "$iter";
    private String keyWordFor = "$for";
    private String keyWordForeach = "$foreach";
    private String keyWordWhile = "$while";
    private String keyWordDo = "$do";

    private String keyWordIf = "$if";
    private String keyWordElse = "$else";
    private String keyWordElseif = "$elseif";

    private String keyWordTry = "$try";
    private String keyWordFinally = "$finally";

    private String keyWordCatch = "$catch";

    private String keyWordSwitch = "$switch";
    private String keyWordCase = "$case";
    private String keyWordDefault = "$default";
    private String keyWordDefaultValue = "$defaultValue";

    private String keyWordReturn = "$return";
    private String keyWordThrow = "$throw";
    private String keyWordBreak = "$break";
    private String keyWordContinue = "$continue";
    private String keyWordOperator = "$operator";
    private String keyWordToString = "$toString";

    private String keyWordEscUnicode = "u";
    private String keyWordEscForm = "f";
    private String keyWordEscLine = "n";
    private String keyWordEscFeed = "r";
    private String keyWordEscTab = "t";
    private String keyWordEscBound = "b";

    private String keyWordNbExpDec = "e";
    private String keyWordNbExpBin = "p";
    private String keyWordNbSufDoublePrim = "d";
    private String keyWordNbSufDouble = "D";
    private String keyWordNbSufFloatPrim = "f";
    private String keyWordNbSufFloat = "F";
    private String keyWordNbSufLongPrim = "l";
    private String keyWordNbSufLong = "L";
    private String keyWordNbSufIntegerPrim = "i";
    private String keyWordNbSufInteger = "I";
    private String keyWordNbSufCharacterPrim = "c";
    private String keyWordNbSufCharacter = "C";
    private String keyWordNbSufShortPrim = "s";
    private String keyWordNbSufShort = "S";
    private String keyWordNbSufBytePrim = "y";
    private String keyWordNbSufByte = "Y";
    private String keyWordNbHex = "x";
    private String keyWordNbHexEnd = "x";
    private String keyWordNbBin = "b";
    
    private String keyWordCast = "$";
    private String keyWordExplicit = "explicit";
    private String keyWordClasschoice = "$classchoice";
    private String keyWordIntern = "$intern";
    private String keyWordNew = "$new";
    private String keyWordSuper = "$super";
    private String keyWordSuperaccess = "$superaccess";
    private String keyWordThisaccess = "$thisaccess";
    private String keyWordVararg = "$vararg";
    private String keyWordFirstopt = "$firstopt";
    private String keyWordBool = "$bool";
    private String keyWordInstanceof = "$instanceof";
    private String keyWordValueOf = "$valueOf";
    private String keyWordValues = "$values";
    private String keyWordThis = "$this";
    private String keyWordThat = "$that";
    private String keyWordLambda = "$lambda";
    private String keyWordId = "$id";
    private String keyWordNull = "$null";
    private String keyWordTrue = "$true";
    private String keyWordFalse = "$false";
    private String keyWordParent = "$parent";
    public void validateKeyWordContents(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyWord(),key_));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _page.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                    _page.addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(keyWordValue_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirst(),keyWordValue_,Character.toString(keyWordValue_.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _page.addStdError(err_);
            }
        }
    }
    public void validateKeyWordDuplicates(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateKeyWord(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_KEY_WORD);
                _page.addStdError(err_);
            }
        }
    }
    public void validateEscapingsContents(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyString(),key_));
                err_.setErrCat(ErrorCat.WRITE_STRING_WORD);
                _page.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_, Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_STRING_WORD);
                    _page.addStdError(err_);
                }
            }
        }
    }
    public void validateEscapingsDuplicates(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStringWord(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                _page.addStdError(err_);
            }
        }
        int size_ = keyWords_.size();
        for (int i = 0; i < size_; i++) {
           String first_ = keyWords_.get(i);
           for (int j = 0; j < size_; j++) {
               String second_ = keyWords_.get(j);
               if (StringUtil.quickEq(first_,second_)) {
                   //already error or i == j
                   continue;
               }
               if (first_.startsWith(second_)) {
                  StdWordError err_ = new StdWordError();
                   err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStarting(),first_,second_));
                   err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                   _page.addStdError(err_);
               }
               if (second_.startsWith(first_)) {
                  StdWordError err_ = new StdWordError();
                   err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStarting(),second_,first_));
                   err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                   _page.addStdError(err_);
               }
            }
        }
        if (keyWordEscUnicode.isEmpty()) {
           //already error
           return;
        }
        char firstUnicode_ = keyWordEscUnicode.charAt(0);
        for (String k: _list.values()) {
            if (StringUtil.quickEq(k, keyWordEscUnicode)) {
                continue;
            }
            if (k.isEmpty()) {
               //already error
               continue;
            }
            if (firstUnicode_ == k.charAt(0)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStartingUni(),k,Character.toString(firstUnicode_)));
                err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                _page.addStdError(err_);
            }
        }
    }
    public void validateNbWordContents(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyNb(),key_));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (c == '_') {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                    _page.addStdError(err_);
                }
                if (!StringExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                    _page.addStdError(err_);
                }
            }
            if (!StringExpUtil.isLetter(keyWordValue_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirst(),keyWordValue_,Character.toString(keyWordValue_.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
            }
        }
    }
    public void validateNbWordDuplicates(StringMap<String> _list, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateNumberWord(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_NB_WORD);
                _page.addStdError(err_);
            }
        }
    }
    public void validateBinarySeparators(AnalyzedPageEl _page) {
        validateExpBin(_page);
        validatePreBin(keyWordNbBin, _page);
        validatePreBin(keyWordNbHex, _page);
        validateHexEnd(_page);
        for (EntryCust<String, String> s: allNbWords(new StringMap<String>()).entryList()) {
            for (EntryCust<String, String> p: allNbWordsBasic().entryList()) {
                validateStartsDuplicates(s.getValue(),p.getValue(), _page);
            }
        }
    }
    public void validateStartsPrefixesDuplicates(AnalyzedPageEl _page) {
        validateStartsDuplicates(keyWordNbBin,keyWordNbHex, _page);
    }
    private static void validateStartsDuplicates(String _first, String _second, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        if (_first.startsWith(_second)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStartingNb(),_first,_second));
            err_.setErrCat(ErrorCat.DUPLICATE_NB_WORD);
            _page.addStdError(err_);
        }
        if (_second.startsWith(_first)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStartingNb(),_second,_first));
            err_.setErrCat(ErrorCat.DUPLICATE_NB_WORD);
            _page.addStdError(err_);
        }
    }
    private void validateHexEnd(AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        if (keyWordNbHexEnd.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(a_.getEmptyPreHex());
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
            return;
        }
        for (char c: keyWordNbHexEnd.toCharArray()) {
            if (!StringExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbHex,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
            }
            if (StringExpUtil.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbHex,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
            }
        }
        char firstChar_ = keyWordNbHexEnd.charAt(0);
        if (!StringExpUtil.isLetter(firstChar_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbHex,Character.toString(firstChar_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
        }
        if (firstChar_ >= 'A' && firstChar_ <= 'F') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbHex,Character.toString(firstChar_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
        }
        if (firstChar_ >= 'a' && firstChar_ <= 'f') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbHex,Character.toString(firstChar_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
        }
    }
    private static void validatePreBin(String _sep, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        if (_sep.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(a_.getEmptyPreBin());
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
            return;
        }
        for (char c: _sep.toCharArray()) {
            if (!StringExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),_sep,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
            }
            if (c == '_') {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),_sep,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
            }
            if (StringExpUtil.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),_sep,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
            }
        }
        if (!StringExpUtil.isLetter(_sep.charAt(0))) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),_sep,Character.toString(_sep.charAt(0))));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
        }
    }
    private void validateExpBin(AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        if (keyWordNbExpBin.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(a_.getEmptyBinExp());
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
            return;
        }
        for (char c: keyWordNbExpBin.toCharArray()) {
            if (c == '_') {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbExpBin,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
            }
            if (!StringExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbExpBin,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
            }
            if (StringExpUtil.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbExpBin,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _page.addStdError(err_);
            }
        }
        char firstExpBin_ = keyWordNbExpBin.charAt(0);
        if (!StringExpUtil.isLetter(firstExpBin_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbExpBin,Character.toString(firstExpBin_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
        }
        if (firstExpBin_ >= 'A' && firstExpBin_ <= 'F') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbExpBin,Character.toString(firstExpBin_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
        }
        if (firstExpBin_ >= 'a' && firstExpBin_ <= 'f') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbExpBin,Character.toString(firstExpBin_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _page.addStdError(err_);
        }
    }
    public void build(StringMap<String> _util,StringMap<String> _cust) {
        setKeyWordContinue(LgNamesContent.get(_util, _cust, CONTINUE));
        setKeyWordInstanceof(LgNamesContent.get(_util, _cust, INSTANCEOF));
        setKeyWordInterface(LgNamesContent.get(_util, _cust, INTERFACE));
        setKeyWordAbstract(LgNamesContent.get(_util, _cust, ABSTRACT));
        setKeyWordElseif(LgNamesContent.get(_util, _cust, ELSEIF));
        setKeyWordCast(LgNamesContent.get(_util, _cust, CAST));
        setKeyWordExplicit(LgNamesContent.get(_util, _cust, EXPLICIT));
        setKeyWordFor(LgNamesContent.get(_util, _cust, FOR));
        setKeyWordVar(LgNamesContent.get(_util, _cust, VAR));
        setKeyWordStatic(LgNamesContent.get(_util, _cust, STATIC));
        setKeyWordStaticCall(LgNamesContent.get(_util, _cust, STATIC_CALL));
        setKeyWordNull(LgNamesContent.get(_util, _cust, NULL));
        setKeyWordClass(LgNamesContent.get(_util, _cust, CLASS));
        setKeyWordFalse(LgNamesContent.get(_util, _cust, FALSE));
        setKeyWordFinal(LgNamesContent.get(_util, _cust, FINAL));
        setKeyWordBreak(LgNamesContent.get(_util, _cust, BREAK));
        setKeyWordIf(LgNamesContent.get(_util, _cust, IF));
        setKeyWordNew(LgNamesContent.get(_util, _cust, NEW));
        setKeyWordWhile(LgNamesContent.get(_util, _cust, WHILE));
        setKeyWordReturn(LgNamesContent.get(_util, _cust, RETURN));
        setKeyWordTrue(LgNamesContent.get(_util, _cust, TRUE));
        setKeyWordPublic(LgNamesContent.get(_util, _cust, PUBLIC));
        setKeyWordPrivate(LgNamesContent.get(_util, _cust, PRIVATE));
        setKeyWordAnnotation(LgNamesContent.get(_util, _cust, ANNOTATION));
        setKeyWordToString(LgNamesContent.get(_util, _cust, TO_STRING));
        setKeyWordNbSufBytePrim(LgNamesContent.get(_util, _cust, NB_SUF_BYTE_PRIM));
        setKeyWordNbSufByte(LgNamesContent.get(_util, _cust, NB_SUF_BYTE));
        setKeyWordNbSufShortPrim(LgNamesContent.get(_util, _cust, NB_SUF_SHORT_PRIM));
        setKeyWordNbSufShort(LgNamesContent.get(_util, _cust, NB_SUF_SHORT));
        setKeyWordNbSufCharacterPrim(LgNamesContent.get(_util, _cust, NB_SUF_CHARACTER_PRIM));
        setKeyWordNbSufCharacter(LgNamesContent.get(_util, _cust, NB_SUF_CHARACTER));
        setKeyWordNbSufIntegerPrim(LgNamesContent.get(_util, _cust, NB_SUF_INTEGER_PRIM));
        setKeyWordNbSufInteger(LgNamesContent.get(_util, _cust, NB_SUF_INTEGER));
        setKeyWordNbSufLongPrim(LgNamesContent.get(_util, _cust, NB_SUF_LONG_PRIM));
        setKeyWordNbSufLong(LgNamesContent.get(_util, _cust, NB_SUF_LONG));
        setKeyWordNbSufFloatPrim(LgNamesContent.get(_util, _cust, NB_SUF_FLOAT_PRIM));
        setKeyWordNbSufFloat(LgNamesContent.get(_util, _cust, NB_SUF_FLOAT));
        setKeyWordNbSufDoublePrim(LgNamesContent.get(_util, _cust, NB_SUF_DOUBLE_PRIM));
        setKeyWordNbSufDouble(LgNamesContent.get(_util, _cust, NB_SUF_DOUBLE));
        setKeyWordIter(LgNamesContent.get(_util, _cust, ITER));
        setKeyWordValue(LgNamesContent.get(_util, _cust, VALUE));
        setKeyWordElse(LgNamesContent.get(_util, _cust, ELSE));
        setKeyWordCatch(LgNamesContent.get(_util, _cust, CATCH));
        setKeyWordThrow(LgNamesContent.get(_util, _cust, THROW));
        setKeyWordTry(LgNamesContent.get(_util, _cust, TRY));
        setKeyWordThis(LgNamesContent.get(_util, _cust, THIS));
        setKeyWordSuper(LgNamesContent.get(_util, _cust, SUPER));
        setKeyWordCase(LgNamesContent.get(_util, _cust, CASE));
        setKeyWordDo(LgNamesContent.get(_util, _cust, DO));
        setKeyWordEnum(LgNamesContent.get(_util, _cust, ENUM));
        setKeyWordSwitch(LgNamesContent.get(_util, _cust, SWITCH));
        setKeyWordIntern(LgNamesContent.get(_util, _cust, INTERN));
        setKeyWordNormal(LgNamesContent.get(_util, _cust, NORMAL));
        setKeyWordEscTab(LgNamesContent.get(_util, _cust, ESC_TAB));
        setKeyWordNbHex(LgNamesContent.get(_util, _cust, NB_HEX));
        setKeyWordNbHexEnd(LgNamesContent.get(_util, _cust, NB_HEX_END));
        setKeyWordNbBin(LgNamesContent.get(_util, _cust, NB_BIN));
        setKeyWordThat(LgNamesContent.get(_util, _cust, THAT));
        setKeyWordBool(LgNamesContent.get(_util, _cust, BOOL));
        setKeyWordValues(LgNamesContent.get(_util, _cust, VALUES));
        setKeyWordLambda(LgNamesContent.get(_util, _cust, LAMBDA));
        setKeyWordVararg(LgNamesContent.get(_util, _cust, VARARG));
        setKeyWordId(LgNamesContent.get(_util, _cust, ID));
        setKeyWordForeach(LgNamesContent.get(_util, _cust, FOREACH));
        setKeyWordNbExpBin(LgNamesContent.get(_util, _cust, NB_EXP_BIN));
        setKeyWordClasschoice(LgNamesContent.get(_util, _cust, CLASSCHOICE));
        setKeyWordFirstopt(LgNamesContent.get(_util, _cust, FIRSTOPT));
        setKeyWordPackage(LgNamesContent.get(_util, _cust, PACKAGE));
        setKeyWordFinally(LgNamesContent.get(_util, _cust, FINALLY));
        setKeyWordEscUnicode(LgNamesContent.get(_util, _cust, ESC_UNICODE));
        setKeyWordThisaccess(LgNamesContent.get(_util, _cust, THISACCESS));
        setKeyWordValueOf(LgNamesContent.get(_util, _cust, VALUE_OF));
        setKeyWordDefaultValue(LgNamesContent.get(_util, _cust, DEFAULT_VALUE));
        setKeyWordEscLine(LgNamesContent.get(_util, _cust, ESC_LINE));
        setKeyWordOperator(LgNamesContent.get(_util, _cust, OPERATOR));
        setKeyWordInterfaces(LgNamesContent.get(_util, _cust, INTERFACES));
        setKeyWordSuperaccess(LgNamesContent.get(_util, _cust, SUPERACCESS));
        setKeyWordEscBound(LgNamesContent.get(_util, _cust, ESC_BOUND));
        setKeyWordEscForm(LgNamesContent.get(_util, _cust, ESC_FORM));
        setKeyWordEscFeed(LgNamesContent.get(_util, _cust, ESC_FEED));
        setKeyWordNbExpDec(LgNamesContent.get(_util, _cust, NB_EXP_DEC));
        setKeyWordProtected(LgNamesContent.get(_util, _cust, PROTECTED));
        setKeyWordDefault(LgNamesContent.get(_util, _cust, DEFAULT));
        setKeyWordParent(LgNamesContent.get(_util, _cust, PARENT));
    }
    public StringMap<String> allKeyWords() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(VALUE,keyWordValue);
        keyWords_.addEntry(EXPLICIT,keyWordExplicit);
        keyWords_.addEntry(VAR,keyWordVar);
        keyWords_.addEntry(INTERFACES,keyWordInterfaces);
        keyWords_.addEntry(PUBLIC,keyWordPublic);
        keyWords_.addEntry(PACKAGE,keyWordPackage);
        keyWords_.addEntry(PROTECTED,keyWordProtected);
        keyWords_.addEntry(PRIVATE,keyWordPrivate);
        keyWords_.addEntry(INTERFACE,keyWordInterface);
        keyWords_.addEntry(ANNOTATION,keyWordAnnotation);
        keyWords_.addEntry(CLASS,keyWordClass);
        keyWords_.addEntry(ENUM,keyWordEnum);
        keyWords_.addEntry(STATIC,keyWordStatic);
        keyWords_.addEntry(STATIC_CALL,keyWordStaticCall);
        keyWords_.addEntry(ABSTRACT,keyWordAbstract);
        keyWords_.addEntry(FINAL,keyWordFinal);
        keyWords_.addEntry(NORMAL,keyWordNormal);
        keyWords_.addEntry(ITER,keyWordIter);
        keyWords_.addEntry(FOR,keyWordFor);
        keyWords_.addEntry(FOREACH,keyWordForeach);
        keyWords_.addEntry(WHILE,keyWordWhile);
        keyWords_.addEntry(DO,keyWordDo);
        keyWords_.addEntry(IF,keyWordIf);
        keyWords_.addEntry(ELSE,keyWordElse);
        keyWords_.addEntry(ELSEIF,keyWordElseif);
        keyWords_.addEntry(TRY,keyWordTry);
        keyWords_.addEntry(FINALLY,keyWordFinally);
        keyWords_.addEntry(CATCH,keyWordCatch);
        keyWords_.addEntry(SWITCH,keyWordSwitch);
        keyWords_.addEntry(CASE,keyWordCase);
        keyWords_.addEntry(DEFAULT,keyWordDefault);
        keyWords_.addEntry(DEFAULT_VALUE,keyWordDefaultValue);
        keyWords_.addEntry(RETURN,keyWordReturn);
        keyWords_.addEntry(THROW,keyWordThrow);
        keyWords_.addEntry(BREAK,keyWordBreak);
        keyWords_.addEntry(CONTINUE,keyWordContinue);
        keyWords_.addEntry(OPERATOR,keyWordOperator);
        keyWords_.addEntry(TO_STRING,keyWordToString);
        keyWords_.addEntry(CAST,keyWordCast);
        keyWords_.addEntry(CLASSCHOICE,keyWordClasschoice);
        keyWords_.addEntry(INTERN,keyWordIntern);
        keyWords_.addEntry(NEW,keyWordNew);
        keyWords_.addEntry(SUPER,keyWordSuper);
        keyWords_.addEntry(SUPERACCESS,keyWordSuperaccess);
        keyWords_.addEntry(THISACCESS,keyWordThisaccess);
        keyWords_.addEntry(VARARG,keyWordVararg);
        keyWords_.addEntry(FIRSTOPT,keyWordFirstopt);
        keyWords_.addEntry(BOOL,keyWordBool);
        keyWords_.addEntry(INSTANCEOF,keyWordInstanceof);
        keyWords_.addEntry(VALUE_OF,keyWordValueOf);
        keyWords_.addEntry(VALUES,keyWordValues);
        keyWords_.addEntry(THIS,keyWordThis);
        keyWords_.addEntry(THAT,keyWordThat);
        keyWords_.addEntry(LAMBDA,keyWordLambda);
        keyWords_.addEntry(ID,keyWordId);
        keyWords_.addEntry(NULL,keyWordNull);
        keyWords_.addEntry(TRUE,keyWordTrue);
        keyWords_.addEntry(FALSE,keyWordFalse);
        keyWords_.addEntry(PARENT,keyWordParent);
        return keyWords_;
    }
    public StringMap<String> allEscapings() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(ESC_UNICODE,keyWordEscUnicode);
        keyWords_.addEntry(ESC_FORM,keyWordEscForm);
        keyWords_.addEntry(ESC_LINE,keyWordEscLine);
        keyWords_.addEntry(ESC_FEED,keyWordEscFeed);
        keyWords_.addEntry(ESC_TAB,keyWordEscTab);
        keyWords_.addEntry(ESC_BOUND,keyWordEscBound);
        return keyWords_;
    }
    public StringMap<String> allNbWordsBasic() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(NB_EXP_DEC,keyWordNbExpDec);
        keyWords_.addEntry(NB_EXP_BIN,keyWordNbExpBin);
        keyWords_.addEntry(NB_HEX,keyWordNbHex);
        keyWords_.addEntry(NB_HEX_END,keyWordNbHexEnd);
        keyWords_.addEntry(NB_BIN,keyWordNbBin);
        return keyWords_;
    }
    public StringMap<String> allNbWordsDec() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(NB_EXP_DEC,keyWordNbExpDec);
        return keyWords_;
    }
    public StringMap<String> allNbWordsBin() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(NB_EXP_BIN,keyWordNbExpBin);
        keyWords_.addEntry(NB_HEX_END,keyWordNbHexEnd);
        return keyWords_;
    }
    public StringMap<String> allNbWordsPreBin() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(NB_HEX,keyWordNbHex);
        keyWords_.addEntry(NB_BIN,keyWordNbBin);
        return keyWords_;
    }
    public StringMap<String> allNbWords(StringMap<String> _othersWords) {
        StringMap<String> keyWords_ = new StringMap<String>();
        for (EntryCust<String,String> o: _othersWords.entryList()) {
            keyWords_.addEntry(o.getKey(),o.getValue());
        }
        keyWords_.addEntry(NB_SUF_DOUBLE_PRIM,keyWordNbSufDoublePrim);
        keyWords_.addEntry(NB_SUF_DOUBLE,keyWordNbSufDouble);
        keyWords_.addEntry(NB_SUF_FLOAT_PRIM,keyWordNbSufFloatPrim);
        keyWords_.addEntry(NB_SUF_FLOAT,keyWordNbSufFloat);
        keyWords_.addEntry(NB_SUF_LONG_PRIM,keyWordNbSufLongPrim);
        keyWords_.addEntry(NB_SUF_LONG,keyWordNbSufLong);
        keyWords_.addEntry(NB_SUF_INTEGER_PRIM,keyWordNbSufIntegerPrim);
        keyWords_.addEntry(NB_SUF_INTEGER,keyWordNbSufInteger);
        keyWords_.addEntry(NB_SUF_CHARACTER_PRIM,keyWordNbSufCharacterPrim);
        keyWords_.addEntry(NB_SUF_CHARACTER,keyWordNbSufCharacter);
        keyWords_.addEntry(NB_SUF_SHORT_PRIM,keyWordNbSufShortPrim);
        keyWords_.addEntry(NB_SUF_SHORT,keyWordNbSufShort);
        keyWords_.addEntry(NB_SUF_BYTE_PRIM,keyWordNbSufBytePrim);
        keyWords_.addEntry(NB_SUF_BYTE,keyWordNbSufByte);
        return keyWords_;
    }
    public boolean isKeyWordNotVar(String _word) {
        if (StringUtil.quickEq(_word, keyWordVar)) {
            return false;
        }
        if (StringUtil.quickEq(_word, keyWordValue)) {
            return false;
        }
        if (StringUtil.quickEq(_word, keyWordToString)) {
            return false;
        }
        return isKeyWord(_word);
    }
    public boolean isKeyWord(String _word) {
        return StringUtil.contains(allKeyWords().values(), _word);
    }
    public StringMap<Character> getSuffixes() {
        StringMap<Character> keyWords_ = new StringMap<Character>();
        keyWords_.put(keyWordNbSufDoublePrim,'d');
        keyWords_.put(keyWordNbSufDouble,'D');
        keyWords_.put(keyWordNbSufFloatPrim,'f');
        keyWords_.put(keyWordNbSufFloat,'F');
        keyWords_.put(keyWordNbSufLongPrim,'l');
        keyWords_.put(keyWordNbSufLong,'L');
        keyWords_.put(keyWordNbSufIntegerPrim,'i');
        keyWords_.put(keyWordNbSufInteger,'I');
        keyWords_.put(keyWordNbSufCharacterPrim,'c');
        keyWords_.put(keyWordNbSufCharacter,'C');
        keyWords_.put(keyWordNbSufShortPrim,'s');
        keyWords_.put(keyWordNbSufShort,'S');
        keyWords_.put(keyWordNbSufBytePrim,'b');
        keyWords_.put(keyWordNbSufByte,'B');
        return keyWords_;
    }
    public String getNbKeyWord(String _string, int _from) {
        StringMap<String> keyWords_ = allNbWords(new StringMap<String>());
        StringList list_ = new StringList();
        for (String k: keyWords_.values()) {
            if (StringExpUtil.startsWithKeyWord(_string,_from, k)) {
                list_.add(k);
            }
        }
        if (list_.isEmpty()) {
            return null;
        }
        return list_.first();
    }
    public String getEscKeyWord(String _string, int _from) {
        StringList keyWords_ = new StringList();
        keyWords_.add(keyWordEscBound);
        keyWords_.add(keyWordEscFeed);
        keyWords_.add(keyWordEscForm);
        keyWords_.add(keyWordEscLine);
        keyWords_.add(keyWordEscTab);
        String sub_ = _string.substring(_from);
        StringList list_ = new StringList();
        for (String k: keyWords_) {
            if (sub_.startsWith(k)) {
                list_.add(k);
            }
        }
        if (list_.isEmpty()) {
            return null;
        }
        return list_.first();
    }

    public String getKeyWordVar() {
        return keyWordVar;
    }
    public void setKeyWordVar(String _keyWordVar) {
        keyWordVar = _keyWordVar;
    }

    public String getKeyWordValue() {
        return keyWordValue;
    }

    public void setKeyWordValue(String _keyWordValue) {
        keyWordValue = _keyWordValue;
    }

    public String getKeyWordInterfaces() {
        return keyWordInterfaces;
    }
    public void setKeyWordInterfaces(String _keyWordInterfaces) {
        keyWordInterfaces = _keyWordInterfaces;
    }
    public String getKeyWordPublic() {
        return keyWordPublic;
    }
    public void setKeyWordPublic(String _keyWordPublic) {
        keyWordPublic = _keyWordPublic;
    }
    public String getKeyWordPackage() {
        return keyWordPackage;
    }
    public void setKeyWordPackage(String _keyWordPackage) {
        keyWordPackage = _keyWordPackage;
    }
    public String getKeyWordProtected() {
        return keyWordProtected;
    }
    public void setKeyWordProtected(String _keyWordProtected) {
        keyWordProtected = _keyWordProtected;
    }
    public String getKeyWordPrivate() {
        return keyWordPrivate;
    }
    public void setKeyWordPrivate(String _keyWordPrivate) {
        keyWordPrivate = _keyWordPrivate;
    }
    public String getKeyWordInterface() {
        return keyWordInterface;
    }
    public void setKeyWordInterface(String _keyWordInterface) {
        keyWordInterface = _keyWordInterface;
    }
    public String getKeyWordAnnotation() {
        return keyWordAnnotation;
    }
    public void setKeyWordAnnotation(String _keyWordAnnotation) {
        keyWordAnnotation = _keyWordAnnotation;
    }
    public String getKeyWordClass() {
        return keyWordClass;
    }
    public void setKeyWordClass(String _keyWordClass) {
        keyWordClass = _keyWordClass;
    }
    public String getKeyWordEnum() {
        return keyWordEnum;
    }
    public void setKeyWordEnum(String _keyWordEnum) {
        keyWordEnum = _keyWordEnum;
    }
    public String getKeyWordStatic() {
        return keyWordStatic;
    }
    public void setKeyWordStatic(String _keyWordStatic) {
        keyWordStatic = _keyWordStatic;
    }

    public String getKeyWordStaticCall() {
        return keyWordStaticCall;
    }

    public void setKeyWordStaticCall(String _keyWordStaticCall) {
        keyWordStaticCall = _keyWordStaticCall;
    }

    public String getKeyWordAbstract() {
        return keyWordAbstract;
    }
    public void setKeyWordAbstract(String _keyWordAbstract) {
        keyWordAbstract = _keyWordAbstract;
    }
    public String getKeyWordFinal() {
        return keyWordFinal;
    }
    public void setKeyWordFinal(String _keyWordFinal) {
        keyWordFinal = _keyWordFinal;
    }
    public String getKeyWordNormal() {
        return keyWordNormal;
    }
    public void setKeyWordNormal(String _keyWordNormal) {
        keyWordNormal = _keyWordNormal;
    }
    public String getKeyWordIter() {
        return keyWordIter;
    }
    public void setKeyWordIter(String _keyWordIter) {
        keyWordIter = _keyWordIter;
    }
    public String getKeyWordFor() {
        return keyWordFor;
    }
    public void setKeyWordFor(String _keyWordFor) {
        keyWordFor = _keyWordFor;
    }
    public String getKeyWordForeach() {
        return keyWordForeach;
    }
    public void setKeyWordForeach(String _keyWordForeach) {
        keyWordForeach = _keyWordForeach;
    }
    public String getKeyWordWhile() {
        return keyWordWhile;
    }
    public void setKeyWordWhile(String _keyWordWhile) {
        keyWordWhile = _keyWordWhile;
    }
    public String getKeyWordDo() {
        return keyWordDo;
    }
    public void setKeyWordDo(String _keyWordDo) {
        keyWordDo = _keyWordDo;
    }
    public String getKeyWordIf() {
        return keyWordIf;
    }
    public void setKeyWordIf(String _keyWordIf) {
        keyWordIf = _keyWordIf;
    }
    public String getKeyWordElse() {
        return keyWordElse;
    }
    public void setKeyWordElse(String _keyWordElse) {
        keyWordElse = _keyWordElse;
    }
    public String getKeyWordElseif() {
        return keyWordElseif;
    }
    public void setKeyWordElseif(String _keyWordElseif) {
        keyWordElseif = _keyWordElseif;
    }
    public String getKeyWordTry() {
        return keyWordTry;
    }
    public void setKeyWordTry(String _keyWordTry) {
        keyWordTry = _keyWordTry;
    }
    public String getKeyWordFinally() {
        return keyWordFinally;
    }
    public void setKeyWordFinally(String _keyWordFinally) {
        keyWordFinally = _keyWordFinally;
    }
    public String getKeyWordCatch() {
        return keyWordCatch;
    }
    public void setKeyWordCatch(String _keyWordCatch) {
        keyWordCatch = _keyWordCatch;
    }
    public String getKeyWordSwitch() {
        return keyWordSwitch;
    }
    public void setKeyWordSwitch(String _keyWordSwitch) {
        keyWordSwitch = _keyWordSwitch;
    }
    public String getKeyWordCase() {
        return keyWordCase;
    }
    public void setKeyWordCase(String _keyWordCase) {
        keyWordCase = _keyWordCase;
    }
    public String getKeyWordDefault() {
        return keyWordDefault;
    }
    public void setKeyWordDefault(String _keyWordDefault) {
        keyWordDefault = _keyWordDefault;
    }

    public String getKeyWordDefaultValue() {
        return keyWordDefaultValue;
    }

    public void setKeyWordDefaultValue(String _keyWordDefaultValue) {
        keyWordDefaultValue = _keyWordDefaultValue;
    }

    public String getKeyWordReturn() {
        return keyWordReturn;
    }
    public void setKeyWordReturn(String _keyWordReturn) {
        keyWordReturn = _keyWordReturn;
    }
    public String getKeyWordThrow() {
        return keyWordThrow;
    }
    public void setKeyWordThrow(String _keyWordThrow) {
        keyWordThrow = _keyWordThrow;
    }
    public String getKeyWordBreak() {
        return keyWordBreak;
    }
    public void setKeyWordBreak(String _keyWordBreak) {
        keyWordBreak = _keyWordBreak;
    }
    public String getKeyWordContinue() {
        return keyWordContinue;
    }
    public void setKeyWordContinue(String _keyWordContinue) {
        keyWordContinue = _keyWordContinue;
    }
    public String getKeyWordOperator() {
        return keyWordOperator;
    }
    public void setKeyWordOperator(String _keyWordOperator) {
        keyWordOperator = _keyWordOperator;
    }

    public String getKeyWordToString() {
        return keyWordToString;
    }

    public void setKeyWordToString(String _keyWordToString) {
        keyWordToString = _keyWordToString;
    }

    public String getKeyWordEscUnicode() {
        return keyWordEscUnicode;
    }
    public void setKeyWordEscUnicode(String _keyWordEscUnicode) {
        keyWordEscUnicode = _keyWordEscUnicode;
    }
    public String getKeyWordEscForm() {
        return keyWordEscForm;
    }
    public void setKeyWordEscForm(String _keyWordEscForm) {
        keyWordEscForm = _keyWordEscForm;
    }
    public String getKeyWordEscLine() {
        return keyWordEscLine;
    }
    public void setKeyWordEscLine(String _keyWordEscLine) {
        keyWordEscLine = _keyWordEscLine;
    }
    public String getKeyWordEscFeed() {
        return keyWordEscFeed;
    }
    public void setKeyWordEscFeed(String _keyWordEscFeed) {
        keyWordEscFeed = _keyWordEscFeed;
    }
    public String getKeyWordEscTab() {
        return keyWordEscTab;
    }
    public void setKeyWordEscTab(String _keyWordEscTab) {
        keyWordEscTab = _keyWordEscTab;
    }
    public String getKeyWordEscBound() {
        return keyWordEscBound;
    }
    public void setKeyWordEscBound(String _keyWordEscBound) {
        keyWordEscBound = _keyWordEscBound;
    }
    public String getKeyWordNbExpDec() {
        return keyWordNbExpDec;
    }
    public void setKeyWordNbExpDec(String _keyWordNbExpDec) {
        keyWordNbExpDec = _keyWordNbExpDec;
    }
    public String getKeyWordNbExpBin() {
        return keyWordNbExpBin;
    }
    public void setKeyWordNbExpBin(String _keyWordNbExpBin) {
        keyWordNbExpBin = _keyWordNbExpBin;
    }

    public void setKeyWordNbSufDoublePrim(String _keyWordNbSufDoublePrim) {
        keyWordNbSufDoublePrim = _keyWordNbSufDoublePrim;
    }

    public void setKeyWordNbSufDouble(String _keyWordNbSufDouble) {
        keyWordNbSufDouble = _keyWordNbSufDouble;
    }

    public void setKeyWordNbSufFloatPrim(String _keyWordNbSufFloatPrim) {
        keyWordNbSufFloatPrim = _keyWordNbSufFloatPrim;
    }

    public void setKeyWordNbSufFloat(String _keyWordNbSufFloat) {
        keyWordNbSufFloat = _keyWordNbSufFloat;
    }

    public void setKeyWordNbSufLongPrim(String _keyWordNbSufLongPrim) {
        keyWordNbSufLongPrim = _keyWordNbSufLongPrim;
    }

    public void setKeyWordNbSufLong(String _keyWordNbSufLong) {
        keyWordNbSufLong = _keyWordNbSufLong;
    }

    public void setKeyWordNbSufIntegerPrim(String _keyWordNbSufIntegerPrim) {
        keyWordNbSufIntegerPrim = _keyWordNbSufIntegerPrim;
    }

    public void setKeyWordNbSufInteger(String _keyWordNbSufInteger) {
        keyWordNbSufInteger = _keyWordNbSufInteger;
    }

    public void setKeyWordNbSufCharacterPrim(String _keyWordNbSufCharacterPrim) {
        keyWordNbSufCharacterPrim = _keyWordNbSufCharacterPrim;
    }

    public void setKeyWordNbSufCharacter(String _keyWordNbSufCharacter) {
        keyWordNbSufCharacter = _keyWordNbSufCharacter;
    }

    public void setKeyWordNbSufShortPrim(String _keyWordNbSufShortPrim) {
        keyWordNbSufShortPrim = _keyWordNbSufShortPrim;
    }

    public void setKeyWordNbSufShort(String _keyWordNbSufShort) {
        keyWordNbSufShort = _keyWordNbSufShort;
    }

    public void setKeyWordNbSufBytePrim(String _keyWordNbSufBytePrim) {
        keyWordNbSufBytePrim = _keyWordNbSufBytePrim;
    }

    public void setKeyWordNbSufByte(String _keyWordNbSufByte) {
        keyWordNbSufByte = _keyWordNbSufByte;
    }
    public String getKeyWordNbHex() {
        return keyWordNbHex;
    }
    public void setKeyWordNbHex(String _keyWordNbHex) {
        keyWordNbHex = _keyWordNbHex;
    }

    public String getKeyWordNbHexEnd() {
        return keyWordNbHexEnd;
    }

    public void setKeyWordNbHexEnd(String _keyWordNbHexEnd) {
        keyWordNbHexEnd = _keyWordNbHexEnd;
    }

    public String getKeyWordNbBin() {
        return keyWordNbBin;
    }
    public void setKeyWordNbBin(String _keyWordNbBin) {
        keyWordNbBin = _keyWordNbBin;
    }
    public String getKeyWordCast() {
        return keyWordCast;
    }
    public void setKeyWordCast(String _keyWordCast) {
        keyWordCast = _keyWordCast;
    }

    public String getKeyWordExplicit() {
        return keyWordExplicit;
    }

    public void setKeyWordExplicit(String _keyWordExplicit) {
        keyWordExplicit = _keyWordExplicit;
    }

    public String getKeyWordClasschoice() {
        return keyWordClasschoice;
    }
    public void setKeyWordClasschoice(String _keyWordClasschoice) {
        keyWordClasschoice = _keyWordClasschoice;
    }
    public String getKeyWordIntern() {
        return keyWordIntern;
    }
    public void setKeyWordIntern(String _keyWordIntern) {
        keyWordIntern = _keyWordIntern;
    }
    public String getKeyWordNew() {
        return keyWordNew;
    }
    public void setKeyWordNew(String _keyWordNew) {
        keyWordNew = _keyWordNew;
    }
    public String getKeyWordSuper() {
        return keyWordSuper;
    }
    public void setKeyWordSuper(String _keyWordSuper) {
        keyWordSuper = _keyWordSuper;
    }
    public String getKeyWordSuperaccess() {
        return keyWordSuperaccess;
    }
    public void setKeyWordSuperaccess(String _keyWordSuperaccess) {
        keyWordSuperaccess = _keyWordSuperaccess;
    }
    public String getKeyWordThisaccess() {
        return keyWordThisaccess;
    }
    public void setKeyWordThisaccess(String _keyWordThisaccess) {
        keyWordThisaccess = _keyWordThisaccess;
    }
    public String getKeyWordVararg() {
        return keyWordVararg;
    }
    public void setKeyWordVararg(String _keyWordVararg) {
        keyWordVararg = _keyWordVararg;
    }
    public String getKeyWordFirstopt() {
        return keyWordFirstopt;
    }
    public void setKeyWordFirstopt(String _keyWordFirstopt) {
        keyWordFirstopt = _keyWordFirstopt;
    }
    public String getKeyWordBool() {
        return keyWordBool;
    }
    public void setKeyWordBool(String _keyWordBool) {
        keyWordBool = _keyWordBool;
    }
    public String getKeyWordInstanceof() {
        return keyWordInstanceof;
    }
    public void setKeyWordInstanceof(String _keyWordInstanceof) {
        keyWordInstanceof = _keyWordInstanceof;
    }
    public String getKeyWordValueOf() {
        return keyWordValueOf;
    }
    public void setKeyWordValueOf(String _keyWordValueOf) {
        keyWordValueOf = _keyWordValueOf;
    }
    public String getKeyWordValues() {
        return keyWordValues;
    }
    public void setKeyWordValues(String _keyWordValues) {
        keyWordValues = _keyWordValues;
    }
    public String getKeyWordThis() {
        return keyWordThis;
    }
    public void setKeyWordThis(String _keyWordThis) {
        keyWordThis = _keyWordThis;
    }
    public String getKeyWordThat() {
        return keyWordThat;
    }
    public void setKeyWordThat(String _keyWordThat) {
        keyWordThat = _keyWordThat;
    }
    public String getKeyWordLambda() {
        return keyWordLambda;
    }
    public void setKeyWordLambda(String _keyWordLambda) {
        keyWordLambda = _keyWordLambda;
    }
    public String getKeyWordId() {
        return keyWordId;
    }
    public void setKeyWordId(String _keyWordId) {
        keyWordId = _keyWordId;
    }
    public String getKeyWordNull() {
        return keyWordNull;
    }
    public void setKeyWordNull(String _keyWordNull) {
        keyWordNull = _keyWordNull;
    }
    public String getKeyWordTrue() {
        return keyWordTrue;
    }
    public void setKeyWordTrue(String _keyWordTrue) {
        keyWordTrue = _keyWordTrue;
    }
    public String getKeyWordFalse() {
        return keyWordFalse;
    }
    public void setKeyWordFalse(String _keyWordFalse) {
        keyWordFalse = _keyWordFalse;
    }

    public String getKeyWordParent() {
        return keyWordParent;
    }

    public void setKeyWordParent(String _keyWordParent) {
        this.keyWordParent = _keyWordParent;
    }
}
