package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.stds.ErrorCat;
import code.expressionlanguage.errors.stds.StdWordError;
import code.util.StringList;
import code.util.StringMap;

public final class KeyWords {
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

    private String keyWordReturn = "$return";
    private String keyWordThrow = "$throw";
    private String keyWordBreak = "$break";
    private String keyWordContinue = "$continue";
    private String keyWordOperator = "$operator";

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
    private String keyWordNbSufBytePrim = "b";
    private String keyWordNbSufByte = "B";
    private String keyWordNbHex = "x";
    private String keyWordNbBin = "b";
    
    private String keyWordCast = "$";
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
    public void validateKeyWordContents(ContextEl _cont, StringList _list) {
        for (String k: _list) {
            if (k.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage("empty word");
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            for (char c: k.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("not word char ", Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                    _cont.getClasses().addStdError(err_);
                    break;
                }
            }
            if (ContextEl.isDigit(k.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("digit ", Character.toString(k.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validateKeyWordDuplicates(ContextEl _cont, StringList _list) {
        StringList keyWords_ = new StringList(_list);
        int size_ = keyWords_.size();
        keyWords_.removeDuplicates();
        if (size_ != keyWords_.size()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("duplicate key words ",_list.display()));
            err_.setErrCat(ErrorCat.DUPLICATE_KEY_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    public void validateEscapingsContents(ContextEl _cont, StringList _list) {
        for (String k: _list) {
            if (k.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage("empty word");
                err_.setErrCat(ErrorCat.WRITE_STRING_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            for (char c: k.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("not word char ", Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_STRING_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }
    public void validateEscapingsDuplicates(ContextEl _cont, StringList _list) {
        StringList keyWords_ = new StringList(_list);
        int size_ = keyWords_.size();
        keyWords_.removeDuplicates();
        if (size_ != keyWords_.size()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("duplicate key words ",_list.display()));
            err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
            _cont.getClasses().addStdError(err_);
        }
        size_ = keyWords_.size();
        for (int i = 0; i < size_; i++) {
           String first_ = keyWords_.get(i);
           for (int j = 0; j < size_; j++) {
               if (i == j) {
                  continue;
               }
               String second_ = keyWords_.get(j);
               if (first_.startsWith(second_)) {
                  StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat(first_," starts with ",second_));
                    err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                    _cont.getClasses().addStdError(err_);
               }
               if (second_.startsWith(first_)) {
                  StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat(second_," starts with ",first_));
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
        for (String k: _list) {
            if (StringList.quickEq(k, keyWordEscUnicode)) {
                continue;
            }
            if (k.isEmpty()) {
               //already error
               continue;
            }
            if (firstUnicode_ == k.charAt(0)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("starting key word ",k," with "+keyWordEscUnicode));
                err_.setErrCat(ErrorCat.DUPLICATE_STRING_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validateNbWordContents(ContextEl _cont, StringList _list) {
        for (String k: _list) {
            if (k.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage("empty word");
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            for (char c: k.toCharArray()) {
                if (c == '_') {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("undescore ", k));
                    err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.concat("not word char ", Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
            if (!Character.isLetter(k.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("digit ", Character.toString(k.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validateNbWordDuplicates(ContextEl _cont, StringList _list) {
        StringList keyWords_ = new StringList(_list);
        int size_ = keyWords_.size();
        keyWords_.removeDuplicates();
        if (size_ != keyWords_.size()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("duplicate key words ",_list.display()));
            err_.setErrCat(ErrorCat.DUPLICATE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    public void validateBinarySeparators(ContextEl _cont) {
        validateExpBin(_cont);
        validatePreBin(_cont);
        validatePreHex(_cont);
    }
    public void validateStartsPrefixesDuplicates(ContextEl _cont) {
        if (keyWordNbBin.startsWith(keyWordNbHex)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat(keyWordNbBin," starts with ",keyWordNbHex));
            err_.setErrCat(ErrorCat.DUPLICATE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (keyWordNbHex.startsWith(keyWordNbBin)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat(keyWordNbHex," starts with ",keyWordNbBin));
            err_.setErrCat(ErrorCat.DUPLICATE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    private void validatePreHex(ContextEl _cont) {
        if (keyWordNbHex.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("empty hexadecimal prefix"));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
            return;
        }
        for (char c: keyWordNbHex.toCharArray()) {
            if (!StringList.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("illegal character ",Character.toString(c)," ",keyWordNbHex));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (ContextEl.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("illegal character ",Character.toString(c)," ",keyWordNbHex));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
        char firstChar_ = keyWordNbHex.charAt(0);
        if (!Character.isLetter(firstChar_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("illegal character ",Character.toString(firstChar_)," ",keyWordNbHex));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (firstChar_ >= 'A' && firstChar_ <= 'F') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("illegal character ",Character.toString(firstChar_)," ",keyWordNbHex));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (firstChar_ >= 'a' && firstChar_ <= 'f') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("illegal character ",Character.toString(firstChar_)," ",keyWordNbHex));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    private void validatePreBin(ContextEl _cont) {
        if (keyWordNbBin.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("empty binary prefix"));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
            return;
        }
        for (char c: keyWordNbBin.toCharArray()) {
            if (!StringList.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("illegal character ",Character.toString(c)," ",keyWordNbBin));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (c == '_') {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("illegal character ",Character.toString(c)," ",keyWordNbBin));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (ContextEl.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("illegal character ",Character.toString(c)," ",keyWordNbBin));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
        if (!Character.isLetter(keyWordNbBin.charAt(0))) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("illegal character ",Character.toString(keyWordNbBin.charAt(0))," ",keyWordNbBin));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    private void validateExpBin(ContextEl _cont) {
        if (keyWordNbExpBin.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("empty binary exp"));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
            return;
        }
        for (char c: keyWordNbExpBin.toCharArray()) {
            if (c == '_') {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("illegal character ",Character.toString(c)," ",keyWordNbExpBin));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (!StringList.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("illegal character ",Character.toString(c)," ",keyWordNbExpBin));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
            if (ContextEl.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.concat("illegal character ",Character.toString(c)," ",keyWordNbExpBin));
                err_.setErrCat(ErrorCat.WRITE_NB_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
        char firstExpBin_ = keyWordNbExpBin.charAt(0);
        if (!Character.isLetter(firstExpBin_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("illegal first character ",Character.toString(firstExpBin_)," ",keyWordNbExpBin));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (firstExpBin_ >= 'A' && firstExpBin_ <= 'F') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("illegal first character ",Character.toString(firstExpBin_)," ",keyWordNbExpBin));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
        if (firstExpBin_ >= 'a' && firstExpBin_ <= 'f') {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.concat("illegal first character ",Character.toString(firstExpBin_)," ",keyWordNbExpBin));
            err_.setErrCat(ErrorCat.WRITE_NB_WORD);
            _cont.getClasses().addStdError(err_);
        }
    }
    public StringList allKeyWords() {
        StringList keyWords_ = new StringList();
        keyWords_.add(keyWordValue);
        keyWords_.add(keyWordVar);
        keyWords_.add(keyWordInterfaces);
        keyWords_.add(keyWordPublic);
        keyWords_.add(keyWordPackage);
        keyWords_.add(keyWordProtected);
        keyWords_.add(keyWordPrivate);
        keyWords_.add(keyWordInterface);
        keyWords_.add(keyWordAnnotation);
        keyWords_.add(keyWordClass);
        keyWords_.add(keyWordEnum);
        keyWords_.add(keyWordStatic);
        keyWords_.add(keyWordAbstract);
        keyWords_.add(keyWordFinal);
        keyWords_.add(keyWordNormal);
        keyWords_.add(keyWordIter);
        keyWords_.add(keyWordFor);
        keyWords_.add(keyWordForeach);
        keyWords_.add(keyWordWhile);
        keyWords_.add(keyWordDo);
        keyWords_.add(keyWordIf);
        keyWords_.add(keyWordElse);
        keyWords_.add(keyWordElseif);
        keyWords_.add(keyWordTry);
        keyWords_.add(keyWordFinally);
        keyWords_.add(keyWordCatch);
        keyWords_.add(keyWordSwitch);
        keyWords_.add(keyWordCase);
        keyWords_.add(keyWordDefault);
        keyWords_.add(keyWordReturn);
        keyWords_.add(keyWordThrow);
        keyWords_.add(keyWordBreak);
        keyWords_.add(keyWordContinue);
        keyWords_.add(keyWordOperator);
        keyWords_.add(keyWordCast);
        keyWords_.add(keyWordClasschoice);
        keyWords_.add(keyWordIntern);
        keyWords_.add(keyWordNew);
        keyWords_.add(keyWordSuper);
        keyWords_.add(keyWordSuperaccess);
        keyWords_.add(keyWordThisaccess);
        keyWords_.add(keyWordVararg);
        keyWords_.add(keyWordFirstopt);
        keyWords_.add(keyWordBool);
        keyWords_.add(keyWordInstanceof);
        keyWords_.add(keyWordValueOf);
        keyWords_.add(keyWordValues);
        keyWords_.add(keyWordThis);
        keyWords_.add(keyWordThat);
        keyWords_.add(keyWordLambda);
        keyWords_.add(keyWordId);
        keyWords_.add(keyWordNull);
        keyWords_.add(keyWordTrue);
        keyWords_.add(keyWordFalse);
        return keyWords_;
    }
    public StringList allEscapings() {
        StringList keyWords_ = new StringList();
        keyWords_.add(keyWordEscUnicode);
        keyWords_.add(keyWordEscForm);
        keyWords_.add(keyWordEscLine);
        keyWords_.add(keyWordEscFeed);
        keyWords_.add(keyWordEscTab);
        keyWords_.add(keyWordEscBound);
        return keyWords_;
    }
    public StringList allNbWords(String... _othersWords) {
        StringList keyWords_ = new StringList();
        for (String o: _othersWords) {
            keyWords_.add(o);
        }
        keyWords_.add(keyWordNbSufDoublePrim);
        keyWords_.add(keyWordNbSufDouble);
        keyWords_.add(keyWordNbSufFloatPrim);
        keyWords_.add(keyWordNbSufFloat);
        keyWords_.add(keyWordNbSufLongPrim);
        keyWords_.add(keyWordNbSufLong);
        keyWords_.add(keyWordNbSufIntegerPrim);
        keyWords_.add(keyWordNbSufInteger);
        keyWords_.add(keyWordNbSufCharacterPrim);
        keyWords_.add(keyWordNbSufCharacter);
        keyWords_.add(keyWordNbSufShortPrim);
        keyWords_.add(keyWordNbSufShort);
        keyWords_.add(keyWordNbSufBytePrim);
        keyWords_.add(keyWordNbSufByte);
        return keyWords_;
    }
    public boolean isKeyWordNotVar(String _word) {
        if (StringList.quickEq(_word, keyWordVar)) {
            return false;
        }
        if (StringList.quickEq(_word, keyWordValue)) {
            return false;
        }
        return isKeyWord(_word);
    }
    public boolean isKeyWord(String _word) {
        StringList keyWords_ = allKeyWords();
        return StringList.contains(keyWords_, _word);
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
        StringList keyWords_ = allNbWords();
        String sub_ = _string.substring(_from);
        StringList list_ = new StringList();
        for (String k: keyWords_) {
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
