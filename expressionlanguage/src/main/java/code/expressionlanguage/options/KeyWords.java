package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.stds.ErrorCat;
import code.expressionlanguage.errors.stds.StdWordError;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class KeyWords {
    public static final String CONTINUE = "Continue";
    public static final String INSTANCEOF = "Instanceof";
    public static final String INTERFACE = "Interface";
    public static final String ABSTRACT = "Abstract";
    public static final String ELSEIF = "Elseif";
    public static final String CAST = "Cast";
    public static final String EXPLICIT = "Explicit";
    public static final String FOR = "For";
    public static final String VAR = "Var";
    public static final String STATIC = "Static";
    public static final String STATIC_CALL = "StaticCall";
    public static final String NULL = "Null";
    public static final String CLASS = "Class";
    public static final String FALSE = "False";
    public static final String FINAL = "Final";
    public static final String BREAK = "Break";
    public static final String IF = "If";
    public static final String NEW = "New";
    public static final String WHILE = "While";
    public static final String RETURN = "Return";
    public static final String TRUE = "True";
    public static final String PUBLIC = "Public";
    public static final String PRIVATE = "Private";
    public static final String ANNOTATION = "Annotation";
    public static final String TO_STRING = "ToString";
    public static final String NB_SUF_BYTE_PRIM = "NbSufBytePrim";
    public static final String NB_SUF_BYTE = "NbSufByte";
    public static final String NB_SUF_SHORT_PRIM = "NbSufShortPrim";
    public static final String NB_SUF_SHORT = "NbSufShort";
    public static final String NB_SUF_CHARACTER_PRIM = "NbSufCharacterPrim";
    public static final String NB_SUF_CHARACTER = "NbSufCharacter";
    public static final String NB_SUF_INTEGER_PRIM = "NbSufIntegerPrim";
    public static final String NB_SUF_INTEGER = "NbSufInteger";
    public static final String NB_SUF_LONG_PRIM = "NbSufLongPrim";
    public static final String NB_SUF_LONG = "NbSufLong";
    public static final String NB_SUF_FLOAT_PRIM = "NbSufFloatPrim";
    public static final String NB_SUF_FLOAT = "NbSufFloat";
    public static final String NB_SUF_DOUBLE_PRIM = "NbSufDoublePrim";
    public static final String NB_SUF_DOUBLE = "NbSufDouble";
    public static final String ITER = "Iter";
    public static final String VALUE = "Value";
    public static final String ELSE = "Else";
    public static final String CATCH = "Catch";
    public static final String THROW = "Throw";
    public static final String TRY = "Try";
    public static final String THIS = "This";
    public static final String SUPER = "Super";
    public static final String CASE = "Case";
    public static final String DO = "Do";
    public static final String ENUM = "Enum";
    public static final String SWITCH = "Switch";
    public static final String INTERN = "Intern";
    public static final String NORMAL = "Normal";
    public static final String ESC_TAB = "EscTab";
    public static final String NB_HEX = "NbHex";
    public static final String NB_HEX_END = "NbHexEnd";
    public static final String NB_BIN = "NbBin";
    public static final String THAT = "That";
    public static final String BOOL = "Bool";
    public static final String VALUES = "Values";
    public static final String LAMBDA = "Lambda";
    public static final String VARARG = "Vararg";
    public static final String ID = "Id";
    public static final String FOREACH = "Foreach";
    public static final String NB_EXP_BIN = "NbExpBin";
    public static final String CLASSCHOICE = "Classchoice";
    public static final String FIRSTOPT = "Firstopt";
    public static final String PACKAGE = "Package";
    public static final String FINALLY = "Finally";
    public static final String ESC_UNICODE = "EscUnicode";
    public static final String THISACCESS = "Thisaccess";
    public static final String VALUE_OF = "ValueOf";
    public static final String DEFAULT_VALUE = "DefaultValue";
    public static final String ESC_LINE = "EscLine";
    public static final String OPERATOR = "Operator";
    public static final String INTERFACES = "Interfaces";
    public static final String SUPERACCESS = "Superaccess";
    public static final String ESC_BOUND = "EscBound";
    public static final String ESC_FORM = "EscForm";
    public static final String ESC_FEED = "EscFeed";
    public static final String NB_EXP_DEC = "NbExpDec";
    public static final String PROTECTED = "Protected";
    public static final String DEFAULT = "Default";
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
    public void validateKeyWordContents(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyWord(),key_));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                    _cont.getClasses().addStdError(err_);
                    break;
                }
            }
            if (ContextEl.isDigit(keyWordValue_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirst(),keyWordValue_,Character.toString(keyWordValue_.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validateKeyWordDuplicates(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateKeyWord(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_KEY_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validateEscapingsContents(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyString(),key_));
                err_.setErrCat(ErrorCat.WRITE_STRING_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_, Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_STRING_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }
    public void validateEscapingsDuplicates(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateStringWord(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
        int size_ = keyWords_.size();
        for (int i = 0; i < size_; i++) {
           String first_ = keyWords_.get(i);
           for (int j = 0; j < size_; j++) {
               String second_ = keyWords_.get(j);
               if (StringList.quickEq(first_,second_)) {
                   //already error or i == j
                   continue;
               }
               if (first_.startsWith(second_)) {
                  StdWordError err_ = new StdWordError();
                   err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateStarting(),first_,second_));
                   err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                    _cont.getClasses().addStdError(err_);
               }
               if (second_.startsWith(first_)) {
                  StdWordError err_ = new StdWordError();
                   err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateStarting(),second_,first_));
                   err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                    _cont.getClasses().addStdError(err_);
               }
            }
        }
        if (keyWordEscUnicode.isEmpty()) {
           //already error
           return;
        }
        char firstUnicode_ = keyWordEscUnicode.charAt(0);
        for (String k: _list.values()) {
            if (StringList.quickEq(k, keyWordEscUnicode)) {
                continue;
            }
            if (k.isEmpty()) {
               //already error
               continue;
            }
            if (firstUnicode_ == k.charAt(0)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateStartingUni(),k,Character.toString(firstUnicode_)));
                err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validateNbWordContents(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyNb(),key_));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (c == '_') {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
            if (!Character.isLetter(keyWordValue_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirst(),keyWordValue_,Character.toString(keyWordValue_.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validateNbWordDuplicates(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateNumberWord(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validateBinarySeparators(ContextEl _cont) {
        validateExpBin(_cont);
        validatePreBin(keyWordNbBin,_cont);
        validatePreBin(keyWordNbHex,_cont);
        validateHexEnd(_cont);
    }
    public void validateStartsPrefixesDuplicates(ContextEl _cont) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        if (keyWordNbBin.startsWith(keyWordNbHex)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateStartingNb(),keyWordNbBin,keyWordNbHex));
            err_.setErrCat(ErrorCat.DUPLICATE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (keyWordNbHex.startsWith(keyWordNbBin)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateStartingNb(),keyWordNbHex,keyWordNbBin));
            err_.setErrCat(ErrorCat.DUPLICATE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    private void validateHexEnd(ContextEl _cont) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        if (keyWordNbHexEnd.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(a_.getEmptyPreHex());
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
            return;
        }
        for (char c: keyWordNbHexEnd.toCharArray()) {
            if (!StringList.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalChar(),keyWordNbHex,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (ContextEl.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalChar(),keyWordNbHex,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
        char firstChar_ = keyWordNbHexEnd.charAt(0);
        if (!Character.isLetter(firstChar_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbHex,Character.toString(firstChar_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (firstChar_ >= 'A' && firstChar_ <= 'F') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbHex,Character.toString(firstChar_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (firstChar_ >= 'a' && firstChar_ <= 'f') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbHex,Character.toString(firstChar_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    private static void validatePreBin(String _sep, ContextEl _cont) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        if (_sep.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(a_.getEmptyPreBin());
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
            return;
        }
        for (char c: _sep.toCharArray()) {
            if (!StringList.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalChar(),_sep,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (c == '_') {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalChar(),_sep,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (ContextEl.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalChar(),_sep,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
        if (!Character.isLetter(_sep.charAt(0))) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalFirstChar(),_sep,Character.toString(_sep.charAt(0))));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    private void validateExpBin(ContextEl _cont) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        if (keyWordNbExpBin.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(a_.getEmptyBinExp());
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
            return;
        }
        for (char c: keyWordNbExpBin.toCharArray()) {
            if (c == '_') {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalChar(),keyWordNbExpBin,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (!StringList.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalChar(),keyWordNbExpBin,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (ContextEl.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalChar(),keyWordNbExpBin,Character.toString(c)));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
        char firstExpBin_ = keyWordNbExpBin.charAt(0);
        if (!Character.isLetter(firstExpBin_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbExpBin,Character.toString(firstExpBin_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (firstExpBin_ >= 'A' && firstExpBin_ <= 'F') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbExpBin,Character.toString(firstExpBin_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (firstExpBin_ >= 'a' && firstExpBin_ <= 'f') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbExpBin,Character.toString(firstExpBin_)));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
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
        if (StringList.quickEq(_word, keyWordVar)) {
            return false;
        }
        if (StringList.quickEq(_word, keyWordValue)) {
            return false;
        }
        if (StringList.quickEq(_word, keyWordToString)) {
            return false;
        }
        return isKeyWord(_word);
    }
    public boolean isKeyWord(String _word) {
        return StringList.contains(allKeyWords().values(), _word);
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
        String sub_ = _string.substring(_from);
        StringList list_ = new StringList();
        for (String k: keyWords_.values()) {
            if (ContextEl.startsWithKeyWord(sub_, k)) {
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
    
}
