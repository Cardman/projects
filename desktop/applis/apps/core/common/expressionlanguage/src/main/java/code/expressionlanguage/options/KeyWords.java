package code.expressionlanguage.options;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.NumberInfos;
import code.expressionlanguage.common.StringDataLetterUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.stds.StdWordError;
import code.expressionlanguage.stds.LgNamesContent;
import code.maths.litteralcom.MathExpUtil;
import code.sml.util.TranslationsFile;
import code.util.*;
import code.util.core.StringUtil;

public final class KeyWords {

    public static final String CLASS="0";
    public static final String VAR="1";
    public static final String TRUE="2";
    public static final String FALSE="3";
    public static final String ESC_UNICODE="4";
    public static final String ESC_FORM="5";
    public static final String ESC_LINE="6";
    public static final String ESC_FEED="7";
    public static final String ESC_SPACE="8";
    public static final String ESC_TAB="9";
    public static final String ESC_BOUND="10";
    public static final String NB_EXP_DEC="11";
    public static final String NB_EXP_BIN="12";
    public static final String NB_HEX="13";
    public static final String NB_HEX_END="14";
    public static final String NB_BIN="15";
    public static final String NB_SUF_DOUBLE_PRIM="16";
    public static final String NB_SUF_DOUBLE="17";
    public static final String NB_SUF_FLOAT_PRIM="18";
    public static final String NB_SUF_FLOAT="19";
    public static final String NB_SUF_LONG_PRIM="20";
    public static final String NB_SUF_LONG="21";
    public static final String NB_SUF_INTEGER_PRIM="22";
    public static final String NB_SUF_INTEGER="23";
    public static final String NB_SUF_CHARACTER_PRIM="24";
    public static final String NB_SUF_CHARACTER="25";
    public static final String NB_SUF_SHORT_PRIM="26";
    public static final String NB_SUF_SHORT="27";
    public static final String NB_SUF_BYTE_PRIM="28";
    public static final String NB_SUF_BYTE="29";
    public static final String NB_DIG0="30";
    public static final String NB_DIG1="31";
    public static final String NB_DIG2="32";
    public static final String NB_DIG3="33";
    public static final String NB_DIG4="34";
    public static final String NB_DIG5="35";
    public static final String IF="36";
    public static final String ELSE="37";
    public static final String ELSEIF="38";
    public static final String ITER="39";
    public static final String NEW="40";
    public static final String FOR="41";
    public static final String INTERFACE="42";
    public static final String STATIC="43";
    public static final String FINAL="44";
    public static final String ENUM="45";
    public static final String NULL="46";
    public static final String RETURN="47";
    public static final String THAT="48";
    public static final String STATIC_CALL="49";
    public static final String WHILE="50";
    public static final String BREAK="51";
    public static final String CONTINUE="52";
    public static final String LAMBDA="53";
    public static final String ABSTRACT="54";
    public static final String THIS="55";
    public static final String SUPER="56";
    public static final String INSTANCEOF="57";
    public static final String SWITCH="58";
    public static final String CASE="59";
    public static final String DEFAULT="60";
    public static final String ID="61";
    public static final String FIRSTOPT="62";
    public static final String VARARG="63";
    public static final String CLASSCHOICE="64";
    public static final String SUPERACCESS="65";
    public static final String THISACCESS="66";
    public static final String INTERN="67";
    public static final String VALUE="68";
    public static final String INTERFACES="69";
    public static final String OPERATOR="70";
    public static final String TO_STRING="71";
    public static final String EXPLICIT="72";
    public static final String CAST="73";
    public static final String DO="74";
    public static final String TRY="75";
    public static final String CATCH="76";
    public static final String FINALLY="77";
    public static final String DEFAULT_VALUE="78";
    public static final String BOOL="79";
    public static final String VALUE_OF="80";
    public static final String VALUES="81";
    public static final String PARENT="82";
    public static final String THROW="83";
    public static final String NORMAL="84";
    public static final String FOREACH="85";
    public static final String ANNOTATION="86";
    public static final String PUBLIC="87";
    public static final String PACKAGE="88";
    public static final String PROTECTED="89";
    public static final String PRIVATE="90";
    public static final String EXPONENT_REPLACE="E";
    public static final char EXPONENT_REPLACE_CH='E';
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
    private String keyWordTry = "$try";
    private String keyWordElse = "$else";
    private String keyWordElseif = "$elseif";

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
    private String keyWordEscSpace = "s";
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
    private String keyWordNbDig0 = "A";
    private String keyWordNbDig1 = "B";
    private String keyWordNbDig2 = "C";
    private String keyWordNbDig3 = "D";
    private String keyWordNbDig4 = "E";
    private String keyWordNbDig5 = "F";
    private String keyWordNbDig = "";

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

public static TranslationsFile en(){
    TranslationsFile en_=new TranslationsFile();
    en_.add(CLASS,"Class=class");
    en_.add(VAR,"Var=var");
    en_.add(TRUE,"True=true");
    en_.add(FALSE,"False=false");
    en_.add(ESC_UNICODE,"EscUnicode=u");
    en_.add(ESC_FORM,"EscForm=f");
    en_.add(ESC_LINE,"EscLine=n");
    en_.add(ESC_FEED,"EscFeed=r");
    en_.add(ESC_SPACE,"EscSpace=s");
    en_.add(ESC_TAB,"EscTab=t");
    en_.add(ESC_BOUND,"EscBound=b");
    en_.add(NB_EXP_DEC,"NbExpDec=e");
    en_.add(NB_EXP_BIN,"NbExpBin=p");
    en_.add(NB_HEX,"NbHex=x");
    en_.add(NB_HEX_END,"NbHexEnd=x");
    en_.add(NB_BIN,"NbBin=b");
    en_.add(NB_SUF_DOUBLE_PRIM,"NbSufDoublePrim=d");
    en_.add(NB_SUF_DOUBLE,"NbSufDouble=D");
    en_.add(NB_SUF_FLOAT_PRIM,"NbSufFloatPrim=f");
    en_.add(NB_SUF_FLOAT,"NbSufFloat=F");
    en_.add(NB_SUF_LONG_PRIM,"NbSufLongPrim=l");
    en_.add(NB_SUF_LONG,"NbSufLong=L");
    en_.add(NB_SUF_INTEGER_PRIM,"NbSufIntegerPrim=i");
    en_.add(NB_SUF_INTEGER,"NbSufInteger=I");
    en_.add(NB_SUF_CHARACTER_PRIM,"NbSufCharacterPrim=c");
    en_.add(NB_SUF_CHARACTER,"NbSufCharacter=C");
    en_.add(NB_SUF_SHORT_PRIM,"NbSufShortPrim=s");
    en_.add(NB_SUF_SHORT,"NbSufShort=S");
    en_.add(NB_SUF_BYTE_PRIM,"NbSufBytePrim=y");
    en_.add(NB_SUF_BYTE,"NbSufByte=Y");
    en_.add(NB_DIG0,"NbDig0=A");
    en_.add(NB_DIG1,"NbDig1=B");
    en_.add(NB_DIG2,"NbDig2=C");
    en_.add(NB_DIG3,"NbDig3=D");
    en_.add(NB_DIG4,"NbDig4=E");
    en_.add(NB_DIG5,"NbDig5=F");
    en_.add(IF,"If=if");
    en_.add(ELSE,"Else=else");
    en_.add(ELSEIF,"Elseif=elseif");
    en_.add(ITER,"Iter=iter");
    en_.add(NEW,"New=new");
    en_.add(FOR,"For=for");
    en_.add(INTERFACE,"Interface=interface");
    en_.add(STATIC,"Static=static");
    en_.add(FINAL,"Final=final");
    en_.add(ENUM,"Enum=enum");
    en_.add(NULL,"Null=null");
    en_.add(RETURN,"Return=return");
    en_.add(THAT,"That=that");
    en_.add(STATIC_CALL,"StaticCall=staticCall");
    en_.add(WHILE,"While=while");
    en_.add(BREAK,"Break=break");
    en_.add(CONTINUE,"Continue=continue");
    en_.add(LAMBDA,"Lambda=$lambda");
    en_.add(ABSTRACT,"Abstract=abstract");
    en_.add(THIS,"This=this");
    en_.add(SUPER,"Super=super");
    en_.add(INSTANCEOF,"Instanceof=instanceof");
    en_.add(SWITCH,"Switch=switch");
    en_.add(CASE,"Case=case");
    en_.add(DEFAULT,"Default=default");
    en_.add(ID,"Id=$id");
    en_.add(FIRSTOPT,"Firstopt=$firstopt");
    en_.add(VARARG,"Vararg=$vararg");
    en_.add(CLASSCHOICE,"Classchoice=classchoice");
    en_.add(SUPERACCESS,"Superaccess=superaccess");
    en_.add(THISACCESS,"Thisaccess=thisaccess");
    en_.add(INTERN,"Intern=$intern");
    en_.add(VALUE,"Value=value");
    en_.add(INTERFACES,"Interfaces=interfaces");
    en_.add(OPERATOR,"Operator=operator");
    en_.add(TO_STRING,"ToString=$toString");
    en_.add(EXPLICIT,"Explicit=explicit");
    en_.add(CAST,"Cast=$");
    en_.add(DO,"Do=do");
    en_.add(TRY,"Try=try");
    en_.add(CATCH,"Catch=catch");
    en_.add(FINALLY,"Finally=finally");
    en_.add(DEFAULT_VALUE,"DefaultValue=defaultValue");
    en_.add(BOOL,"Bool=bool");
    en_.add(VALUE_OF,"ValueOf=$valueOf");
    en_.add(VALUES,"Values=$values");
    en_.add(PARENT,"Parent=$parent");
    en_.add(THROW,"Throw=throw");
    en_.add(NORMAL,"Normal=normal");
    en_.add(FOREACH,"Foreach=foreach");
    en_.add(ANNOTATION,"Annotation=annotation");
    en_.add(PUBLIC,"Public=public");
    en_.add(PACKAGE,"Package=package");
    en_.add(PROTECTED,"Protected=protected");
    en_.add(PRIVATE,"Private=private");
    return en_;
}

public static TranslationsFile fr(){
    TranslationsFile fr_=new TranslationsFile();
    fr_.add(CLASS,"Class=classe");
    fr_.add(VAR,"Var=var");
    fr_.add(TRUE,"True=vrai");
    fr_.add(FALSE,"False=faux");
    fr_.add(ESC_UNICODE,"EscUnicode=u");
    fr_.add(ESC_FORM,"EscForm=f");
    fr_.add(ESC_LINE,"EscLine=n");
    fr_.add(ESC_FEED,"EscFeed=r");
    fr_.add(ESC_SPACE,"EscSpace=s");
    fr_.add(ESC_TAB,"EscTab=t");
    fr_.add(ESC_BOUND,"EscBound=b");
    fr_.add(NB_EXP_DEC,"NbExpDec=e");
    fr_.add(NB_EXP_BIN,"NbExpBin=p");
    fr_.add(NB_HEX,"NbHex=x");
    fr_.add(NB_HEX_END,"NbHexEnd=x");
    fr_.add(NB_BIN,"NbBin=b");
    fr_.add(NB_SUF_DOUBLE_PRIM,"NbSufDoublePrim=d");
    fr_.add(NB_SUF_DOUBLE,"NbSufDouble=D");
    fr_.add(NB_SUF_FLOAT_PRIM,"NbSufFloatPrim=f");
    fr_.add(NB_SUF_FLOAT,"NbSufFloat=F");
    fr_.add(NB_SUF_LONG_PRIM,"NbSufLongPrim=o8");
    fr_.add(NB_SUF_LONG,"NbSufLong=O8");
    fr_.add(NB_SUF_INTEGER_PRIM,"NbSufIntegerPrim=o4");
    fr_.add(NB_SUF_INTEGER,"NbSufInteger=O4");
    fr_.add(NB_SUF_CHARACTER_PRIM,"NbSufCharacterPrim=c");
    fr_.add(NB_SUF_CHARACTER,"NbSufCharacter=C");
    fr_.add(NB_SUF_SHORT_PRIM,"NbSufShortPrim=o2");
    fr_.add(NB_SUF_SHORT,"NbSufShort=O2");
    fr_.add(NB_SUF_BYTE_PRIM,"NbSufBytePrim=o");
    fr_.add(NB_SUF_BYTE,"NbSufByte=O");
    fr_.add(NB_DIG0,"NbDig0=A");
    fr_.add(NB_DIG1,"NbDig1=B");
    fr_.add(NB_DIG2,"NbDig2=C");
    fr_.add(NB_DIG3,"NbDig3=D");
    fr_.add(NB_DIG4,"NbDig4=E");
    fr_.add(NB_DIG5,"NbDig5=F");
    fr_.add(IF,"If=si");
    fr_.add(ELSE,"Else=sinon");
    fr_.add(ELSEIF,"Elseif=sinonsi");
    fr_.add(ITER,"Iter=iter");
    fr_.add(NEW,"New=nouveau");
    fr_.add(FOR,"For=pour");
    fr_.add(INTERFACE,"Interface=interface");
    fr_.add(STATIC,"Static=static");
    fr_.add(FINAL,"Final=final");
    fr_.add(ENUM,"Enum=enum");
    fr_.add(NULL,"Null=nul");
    fr_.add(RETURN,"Return=retour");
    fr_.add(THAT,"That=cela");
    fr_.add(STATIC_CALL,"StaticCall=staticAppel");
    fr_.add(WHILE,"While=tantque");
    fr_.add(BREAK,"Break=sortir");
    fr_.add(CONTINUE,"Continue=iterer");
    fr_.add(LAMBDA,"Lambda=$lambda");
    fr_.add(ABSTRACT,"Abstract=abstrait");
    fr_.add(THIS,"This=ceci");
    fr_.add(SUPER,"Super=super");
    fr_.add(INSTANCEOF,"Instanceof=instancede");
    fr_.add(SWITCH,"Switch=selon");
    fr_.add(CASE,"Case=cas");
    fr_.add(DEFAULT,"Default=autrement");
    fr_.add(ID,"Id=$id");
    fr_.add(FIRSTOPT,"Firstopt=$premieropt");
    fr_.add(VARARG,"Vararg=$vararg");
    fr_.add(CLASSCHOICE,"Classchoice=choixclasse");
    fr_.add(SUPERACCESS,"Superaccess=superacces");
    fr_.add(THISACCESS,"Thisaccess=cetacces");
    fr_.add(INTERN,"Intern=$interne");
    fr_.add(VALUE,"Value=valeur");
    fr_.add(INTERFACES,"Interfaces=interfaces");
    fr_.add(OPERATOR,"Operator=operateur");
    fr_.add(TO_STRING,"ToString=$chaine");
    fr_.add(EXPLICIT,"Explicit=explicite");
    fr_.add(CAST,"Cast=$");
    fr_.add(DO,"Do=faire");
    fr_.add(TRY,"Try=essai");
    fr_.add(CATCH,"Catch=capture");
    fr_.add(FINALLY,"Finally=finallement");
    fr_.add(DEFAULT_VALUE,"DefaultValue=valDefaut");
    fr_.add(BOOL,"Bool=bool");
    fr_.add(VALUE_OF,"ValueOf=$valeurDe");
    fr_.add(VALUES,"Values=$valeurs");
    fr_.add(PARENT,"Parent=$parent");
    fr_.add(THROW,"Throw=lancer");
    fr_.add(NORMAL,"Normal=normal");
    fr_.add(FOREACH,"Foreach=pourchaque");
    fr_.add(ANNOTATION,"Annotation=annotation");
    fr_.add(PUBLIC,"Public=public");
    fr_.add(PACKAGE,"Package=paquetage");
    fr_.add(PROTECTED,"Protected=protege");
    fr_.add(PRIVATE,"Private=prive");
    return fr_;
}

    public void validateKeyWordContents(StringMap<String> _list, AnalyzedPageEl _page) {
        for (EntryCust<String,String> e: _list.entryList()) {
            checkKeyWord(_page, e);
        }
    }

    private void checkKeyWord(AnalyzedPageEl _page, EntryCust<String, String> _e) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        String key_ = _e.getKey();
        String keyWordValue_ = _e.getValue();
        if (keyWordValue_.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyWord(),key_));
            _page.addStdError(err_);
            return;
        }
        checkKeyWordChars(_page, keyWordValue_);
        if (StringExpUtil.isDigit(keyWordValue_.charAt(0))) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirst(),keyWordValue_,Character.toString(keyWordValue_.charAt(0))));
            _page.addStdError(err_);
        }
    }

    private void checkKeyWordChars(AnalyzedPageEl _page, String _keyWordValue) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        for (char c: _keyWordValue.toCharArray()) {
            if (!MathExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(), _keyWordValue,Character.toString(c)));
                _page.addStdError(err_);
                break;
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
                _page.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!MathExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_, Character.toString(c)));
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
                _page.addStdError(err_);
            }
        }
        validateStarts(_page, keyWords_);
        if (keyWordEscUnicode.isEmpty()) {
           //already error
           return;
        }
        char firstUnicode_ = keyWordEscUnicode.charAt(0);
        for (String k: _list.values()) {
            if (StringUtil.quickEq(k, keyWordEscUnicode) || k.isEmpty()) {
                continue;
            }
            if (firstUnicode_ == k.charAt(0)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStartingUni(),k,Character.toString(firstUnicode_)));
                _page.addStdError(err_);
            }
        }
    }

    private void validateStarts(AnalyzedPageEl _page, StringList _keyWords) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        int size_ = _keyWords.size();
        for (int i = 0; i < size_; i++) {
           String first_ = _keyWords.get(i);
           for (int j = 0; j < size_; j++) {
               String second_ = _keyWords.get(j);
               if (StringUtil.quickEq(first_,second_)) {
                   //already error or i == j
                   continue;
               }
               if (first_.startsWith(second_)) {
                  StdWordError err_ = new StdWordError();
                   err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStarting(),first_,second_));
                   _page.addStdError(err_);
               }
               if (second_.startsWith(first_)) {
                  StdWordError err_ = new StdWordError();
                   err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStarting(),second_,first_));
                   _page.addStdError(err_);
               }
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
                _page.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (c == '_') {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    _page.addStdError(err_);
                }
                if (!MathExpUtil.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringUtil.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    _page.addStdError(err_);
                }
            }
            if (!StringDataLetterUtil.isLetter(keyWordValue_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirst(),keyWordValue_,Character.toString(keyWordValue_.charAt(0))));
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
                _page.addStdError(err_);
            }
        }
    }
    public void validateBinarySeparators(AnalyzedPageEl _page) {
        validateSupplDigits(_page);
        validateExpBin(_page);
        validatePreBin(keyWordNbBin, _page);
        validatePreBin(keyWordNbHex, _page);
        validateHexEnd(_page);
        for (EntryCust<String, String> s: allNbWords(_page.getMappingKeyWords(),new StringMap<String>()).entryList()) {
            for (EntryCust<String, String> p: allNbWordsBasic(_page.getMappingKeyWords()).entryList()) {
                validateStartsDuplicates(s.getValue(),p.getValue(), _page);
            }
        }
    }
    public void initSupplDigits() {
        CustList<String> supplDig_ = supplDig();
        Ints supplDigMin_ = new Ints();
        for (String p: supplDig_) {
            if (p.length() == 1 && StringDataLetterUtil.isLetter(p.charAt(0))) {
                supplDigMin_.add(NumParsers.toMinCase(p.charAt(0)));
            }
        }
        supplDigMin_.sort();
        int size_ = supplDigMin_.size();
        StringBuilder keyWordNbDigBuilder_ = new StringBuilder();
        for (int i = 0; i < size_; i++) {
            int va_ = supplDigMin_.get(i);
            keyWordNbDigBuilder_.append((char)va_);
        }
        keyWordNbDig = keyWordNbDigBuilder_.toString();
    }
    private void validateSupplDigits(AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        CustList<String> supplDig_ = supplDig();
        Ints supplDigMin_ = new Ints();
        for (String p: supplDig_) {
            if (p.length() != 1) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getEmptyNb(),p));
                _page.addStdError(err_);
            } else if (!StringDataLetterUtil.isLetter(p.charAt(0))){
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDigitFirst(),p,Character.toString(p.charAt(0))));
                _page.addStdError(err_);
            } else {
                supplDigMin_.add(NumParsers.toMinCase(p.charAt(0)));
            }
        }
        supplDigMin_.sort();
        int size_ = supplDigMin_.size();
        for (int i = 1; i < size_; i++) {
            int va_ = supplDigMin_.get(i);
            if (supplDigMin_.get(i - 1) >= va_) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateNumberWord(),Character.toString((char)va_)));
                _page.addStdError(err_);
            }
        }
    }

    private CustList<String> supplDig() {
        CustList<String> supplDig_ = new CustList<String>();
        supplDig_.add(keyWordNbDig0);
        supplDig_.add(keyWordNbDig1);
        supplDig_.add(keyWordNbDig2);
        supplDig_.add(keyWordNbDig3);
        supplDig_.add(keyWordNbDig4);
        supplDig_.add(keyWordNbDig5);
        return supplDig_;
    }

    public void validateStartsPrefixesDuplicates(AnalyzedPageEl _page) {
        validateStartsDuplicates(keyWordNbBin,keyWordNbHex, _page);
    }
    private static void validateStartsDuplicates(String _first, String _second, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        if (_first.startsWith(_second)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStartingNb(),_first,_second));
            _page.addStdError(err_);
        }
        if (_second.startsWith(_first)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getDuplicateStartingNb(),_second,_first));
            _page.addStdError(err_);
        }
    }
    private void validateHexEnd(AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        if (keyWordNbHexEnd.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(a_.getEmptyPreHex());
            _page.addStdError(err_);
            return;
        }
        for (char c: keyWordNbHexEnd.toCharArray()) {
            if (!MathExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbHex,Character.toString(c)));
                _page.addStdError(err_);
            }
            if (StringExpUtil.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbHex,Character.toString(c)));
                _page.addStdError(err_);
            }
        }
        char firstChar_ = keyWordNbHexEnd.charAt(0);
        if (!StringDataLetterUtil.isLetter(firstChar_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbHex,Character.toString(firstChar_)));
            _page.addStdError(err_);
        } else {
            int min_ = NumParsers.toMinCase(firstChar_);
            if (keyWordNbDig.indexOf(min_) >= 0) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbHex,Character.toString(firstChar_)));
                _page.addStdError(err_);
            }
        }
    }
    private static void validatePreBin(String _sep, AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        if (_sep.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(a_.getEmptyPreBin());
            _page.addStdError(err_);
            return;
        }
        for (char c: _sep.toCharArray()) {
            if (!MathExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),_sep,Character.toString(c)));
                _page.addStdError(err_);
            }
            if (c == '_') {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),_sep,Character.toString(c)));
                _page.addStdError(err_);
            }
            if (StringExpUtil.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),_sep,Character.toString(c)));
                _page.addStdError(err_);
            }
        }
        if (!StringDataLetterUtil.isLetter(_sep.charAt(0))) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),_sep,Character.toString(_sep.charAt(0))));
            _page.addStdError(err_);
        }
    }
    private void validateExpBin(AnalyzedPageEl _page) {
        AnalysisMessages a_ = _page.getAnalysisMessages();
        if (keyWordNbExpBin.isEmpty()) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(a_.getEmptyBinExp());
            _page.addStdError(err_);
            return;
        }
        for (char c: keyWordNbExpBin.toCharArray()) {
            if (c == '_') {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbExpBin,Character.toString(c)));
                _page.addStdError(err_);
            }
            if (!MathExpUtil.isDollarWordChar(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbExpBin,Character.toString(c)));
                _page.addStdError(err_);
            }
            if (StringExpUtil.isDigit(c)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalChar(),keyWordNbExpBin,Character.toString(c)));
                _page.addStdError(err_);
            }
        }
        char firstExpBin_ = keyWordNbExpBin.charAt(0);
        if (!StringDataLetterUtil.isLetter(firstExpBin_)) {
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbExpBin,Character.toString(firstExpBin_)));
            _page.addStdError(err_);
        } else {
            int min_ = NumParsers.toMinCase(firstExpBin_);
            if (keyWordNbDig.indexOf(min_) >= 0) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringUtil.simpleStringsFormat(a_.getIllegalFirstChar(),keyWordNbExpBin,Character.toString(firstExpBin_)));
                _page.addStdError(err_);
            }
        }
    }
    public void build(StringMap<String> _util,StringMap<String> _cust,StringMap<String> _mapping) {
        setKeyWordContinue(LgNamesContent.get(_util, _cust, _mapping.getVal(CONTINUE)));
        setKeyWordInstanceof(LgNamesContent.get(_util, _cust, _mapping.getVal(INSTANCEOF)));
        setKeyWordInterface(LgNamesContent.get(_util, _cust, _mapping.getVal(INTERFACE)));
        setKeyWordAbstract(LgNamesContent.get(_util, _cust, _mapping.getVal(ABSTRACT)));
        setKeyWordElseif(LgNamesContent.get(_util, _cust, _mapping.getVal(ELSEIF)));
        setKeyWordCast(LgNamesContent.get(_util, _cust, _mapping.getVal(CAST)));
        setKeyWordExplicit(LgNamesContent.get(_util, _cust, _mapping.getVal(EXPLICIT)));
        setKeyWordFor(LgNamesContent.get(_util, _cust, _mapping.getVal(FOR)));
        setKeyWordVar(LgNamesContent.get(_util, _cust, _mapping.getVal(VAR)));
        setKeyWordStatic(LgNamesContent.get(_util, _cust, _mapping.getVal(STATIC)));
        setKeyWordStaticCall(LgNamesContent.get(_util, _cust, _mapping.getVal(STATIC_CALL)));
        setKeyWordNull(LgNamesContent.get(_util, _cust, _mapping.getVal(NULL)));
        setKeyWordClass(LgNamesContent.get(_util, _cust, _mapping.getVal(CLASS)));
        setKeyWordFalse(LgNamesContent.get(_util, _cust, _mapping.getVal(FALSE)));
        setKeyWordFinal(LgNamesContent.get(_util, _cust, _mapping.getVal(FINAL)));
        setKeyWordBreak(LgNamesContent.get(_util, _cust, _mapping.getVal(BREAK)));
        setKeyWordIf(LgNamesContent.get(_util, _cust, _mapping.getVal(IF)));
        setKeyWordNew(LgNamesContent.get(_util, _cust, _mapping.getVal(NEW)));
        setKeyWordWhile(LgNamesContent.get(_util, _cust, _mapping.getVal(WHILE)));
        setKeyWordReturn(LgNamesContent.get(_util, _cust, _mapping.getVal(RETURN)));
        setKeyWordTrue(LgNamesContent.get(_util, _cust, _mapping.getVal(TRUE)));
        setKeyWordPublic(LgNamesContent.get(_util, _cust, _mapping.getVal(PUBLIC)));
        setKeyWordPrivate(LgNamesContent.get(_util, _cust, _mapping.getVal(PRIVATE)));
        setKeyWordAnnotation(LgNamesContent.get(_util, _cust, _mapping.getVal(ANNOTATION)));
        setKeyWordToString(LgNamesContent.get(_util, _cust, _mapping.getVal(TO_STRING)));
        setKeyWordNbSufBytePrim(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_BYTE_PRIM)));
        setKeyWordNbSufByte(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_BYTE)));
        setKeyWordNbSufShortPrim(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_SHORT_PRIM)));
        setKeyWordNbSufShort(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_SHORT)));
        setKeyWordNbSufCharacterPrim(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_CHARACTER_PRIM)));
        setKeyWordNbSufCharacter(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_CHARACTER)));
        setKeyWordNbSufIntegerPrim(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_INTEGER_PRIM)));
        setKeyWordNbSufInteger(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_INTEGER)));
        setKeyWordNbSufLongPrim(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_LONG_PRIM)));
        setKeyWordNbSufLong(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_LONG)));
        setKeyWordNbSufFloatPrim(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_FLOAT_PRIM)));
        setKeyWordNbSufFloat(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_FLOAT)));
        setKeyWordNbSufDoublePrim(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_DOUBLE_PRIM)));
        setKeyWordNbSufDouble(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_SUF_DOUBLE)));
        setKeyWordIter(LgNamesContent.get(_util, _cust, _mapping.getVal(ITER)));
        setKeyWordValue(LgNamesContent.get(_util, _cust, _mapping.getVal(VALUE)));
        setKeyWordElse(LgNamesContent.get(_util, _cust, _mapping.getVal(ELSE)));
        setKeyWordCatch(LgNamesContent.get(_util, _cust, _mapping.getVal(CATCH)));
        setKeyWordThrow(LgNamesContent.get(_util, _cust, _mapping.getVal(THROW)));
        setKeyWordTry(LgNamesContent.get(_util, _cust, _mapping.getVal(TRY)));
        setKeyWordThis(LgNamesContent.get(_util, _cust, _mapping.getVal(THIS)));
        setKeyWordSuper(LgNamesContent.get(_util, _cust, _mapping.getVal(SUPER)));
        setKeyWordCase(LgNamesContent.get(_util, _cust, _mapping.getVal(CASE)));
        setKeyWordDo(LgNamesContent.get(_util, _cust, _mapping.getVal(DO)));
        setKeyWordEnum(LgNamesContent.get(_util, _cust, _mapping.getVal(ENUM)));
        setKeyWordSwitch(LgNamesContent.get(_util, _cust, _mapping.getVal(SWITCH)));
        setKeyWordIntern(LgNamesContent.get(_util, _cust, _mapping.getVal(INTERN)));
        setKeyWordNormal(LgNamesContent.get(_util, _cust, _mapping.getVal(NORMAL)));
        setKeyWordEscSpace(LgNamesContent.get(_util, _cust, _mapping.getVal(ESC_SPACE)));
        setKeyWordEscTab(LgNamesContent.get(_util, _cust, _mapping.getVal(ESC_TAB)));
        setKeyWordNbHex(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_HEX)));
        setKeyWordNbHexEnd(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_HEX_END)));
        setKeyWordNbBin(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_BIN)));
        setKeyWordNbDig0(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_DIG0)));
        setKeyWordNbDig1(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_DIG1)));
        setKeyWordNbDig2(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_DIG2)));
        setKeyWordNbDig3(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_DIG3)));
        setKeyWordNbDig4(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_DIG4)));
        setKeyWordNbDig5(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_DIG5)));
        setKeyWordThat(LgNamesContent.get(_util, _cust, _mapping.getVal(THAT)));
        setKeyWordBool(LgNamesContent.get(_util, _cust, _mapping.getVal(BOOL)));
        setKeyWordValues(LgNamesContent.get(_util, _cust, _mapping.getVal(VALUES)));
        setKeyWordLambda(LgNamesContent.get(_util, _cust, _mapping.getVal(LAMBDA)));
        setKeyWordVararg(LgNamesContent.get(_util, _cust, _mapping.getVal(VARARG)));
        setKeyWordId(LgNamesContent.get(_util, _cust, _mapping.getVal(ID)));
        setKeyWordForeach(LgNamesContent.get(_util, _cust, _mapping.getVal(FOREACH)));
        setKeyWordNbExpBin(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_EXP_BIN)));
        setKeyWordClasschoice(LgNamesContent.get(_util, _cust, _mapping.getVal(CLASSCHOICE)));
        setKeyWordFirstopt(LgNamesContent.get(_util, _cust, _mapping.getVal(FIRSTOPT)));
        setKeyWordPackage(LgNamesContent.get(_util, _cust, _mapping.getVal(PACKAGE)));
        setKeyWordFinally(LgNamesContent.get(_util, _cust, _mapping.getVal(FINALLY)));
        setKeyWordEscUnicode(LgNamesContent.get(_util, _cust, _mapping.getVal(ESC_UNICODE)));
        setKeyWordThisaccess(LgNamesContent.get(_util, _cust, _mapping.getVal(THISACCESS)));
        setKeyWordValueOf(LgNamesContent.get(_util, _cust, _mapping.getVal(VALUE_OF)));
        setKeyWordDefaultValue(LgNamesContent.get(_util, _cust, _mapping.getVal(DEFAULT_VALUE)));
        setKeyWordEscLine(LgNamesContent.get(_util, _cust, _mapping.getVal(ESC_LINE)));
        setKeyWordOperator(LgNamesContent.get(_util, _cust, _mapping.getVal(OPERATOR)));
        setKeyWordInterfaces(LgNamesContent.get(_util, _cust, _mapping.getVal(INTERFACES)));
        setKeyWordSuperaccess(LgNamesContent.get(_util, _cust, _mapping.getVal(SUPERACCESS)));
        setKeyWordEscBound(LgNamesContent.get(_util, _cust, _mapping.getVal(ESC_BOUND)));
        setKeyWordEscForm(LgNamesContent.get(_util, _cust, _mapping.getVal(ESC_FORM)));
        setKeyWordEscFeed(LgNamesContent.get(_util, _cust, _mapping.getVal(ESC_FEED)));
        setKeyWordNbExpDec(LgNamesContent.get(_util, _cust, _mapping.getVal(NB_EXP_DEC)));
        setKeyWordProtected(LgNamesContent.get(_util, _cust, _mapping.getVal(PROTECTED)));
        setKeyWordDefault(LgNamesContent.get(_util, _cust, _mapping.getVal(DEFAULT)));
        setKeyWordParent(LgNamesContent.get(_util, _cust, _mapping.getVal(PARENT)));
    }
    public StringMap<String> allKeyWords() {
        return allKeyWords(mapping());
    }
    public StringMap<String> allKeyWords(StringMap<String> _mapping) {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(VALUE),keyWordValue);
        keyWords_.addEntry(_mapping.getVal(EXPLICIT),keyWordExplicit);
        keyWords_.addEntry(_mapping.getVal(VAR),keyWordVar);
        keyWords_.addEntry(_mapping.getVal(INTERFACES),keyWordInterfaces);
        keyWords_.addEntry(_mapping.getVal(PUBLIC),keyWordPublic);
        keyWords_.addEntry(_mapping.getVal(PACKAGE),keyWordPackage);
        keyWords_.addEntry(_mapping.getVal(PROTECTED),keyWordProtected);
        keyWords_.addEntry(_mapping.getVal(PRIVATE),keyWordPrivate);
        keyWords_.addEntry(_mapping.getVal(INTERFACE),keyWordInterface);
        keyWords_.addEntry(_mapping.getVal(ANNOTATION),keyWordAnnotation);
        keyWords_.addEntry(_mapping.getVal(CLASS),keyWordClass);
        keyWords_.addEntry(_mapping.getVal(ENUM),keyWordEnum);
        keyWords_.addEntry(_mapping.getVal(STATIC),keyWordStatic);
        keyWords_.addEntry(_mapping.getVal(STATIC_CALL),keyWordStaticCall);
        keyWords_.addEntry(_mapping.getVal(ABSTRACT),keyWordAbstract);
        keyWords_.addEntry(_mapping.getVal(FINAL),keyWordFinal);
        keyWords_.addEntry(_mapping.getVal(NORMAL),keyWordNormal);
        keyWords_.addEntry(_mapping.getVal(ITER),keyWordIter);
        keyWords_.addEntry(_mapping.getVal(FOR),keyWordFor);
        keyWords_.addEntry(_mapping.getVal(FOREACH),keyWordForeach);
        keyWords_.addEntry(_mapping.getVal(WHILE),keyWordWhile);
        keyWords_.addEntry(_mapping.getVal(DO),keyWordDo);
        keyWords_.addEntry(_mapping.getVal(IF),keyWordIf);
        keyWords_.addEntry(_mapping.getVal(ELSE),keyWordElse);
        keyWords_.addEntry(_mapping.getVal(ELSEIF),keyWordElseif);
        keyWords_.addEntry(_mapping.getVal(TRY),keyWordTry);
        keyWords_.addEntry(_mapping.getVal(FINALLY),keyWordFinally);
        keyWords_.addEntry(_mapping.getVal(CATCH),keyWordCatch);
        keyWords_.addEntry(_mapping.getVal(SWITCH),keyWordSwitch);
        keyWords_.addEntry(_mapping.getVal(CASE),keyWordCase);
        keyWords_.addEntry(_mapping.getVal(DEFAULT),keyWordDefault);
        keyWords_.addEntry(_mapping.getVal(DEFAULT_VALUE),keyWordDefaultValue);
        keyWords_.addEntry(_mapping.getVal(RETURN),keyWordReturn);
        keyWords_.addEntry(_mapping.getVal(THROW),keyWordThrow);
        keyWords_.addEntry(_mapping.getVal(BREAK),keyWordBreak);
        keyWords_.addEntry(_mapping.getVal(CONTINUE),keyWordContinue);
        keyWords_.addEntry(_mapping.getVal(OPERATOR),keyWordOperator);
        keyWords_.addEntry(_mapping.getVal(TO_STRING),keyWordToString);
        keyWords_.addEntry(_mapping.getVal(CAST),keyWordCast);
        keyWords_.addEntry(_mapping.getVal(CLASSCHOICE),keyWordClasschoice);
        keyWords_.addEntry(_mapping.getVal(INTERN),keyWordIntern);
        keyWords_.addEntry(_mapping.getVal(NEW),keyWordNew);
        keyWords_.addEntry(_mapping.getVal(SUPER),keyWordSuper);
        keyWords_.addEntry(_mapping.getVal(SUPERACCESS),keyWordSuperaccess);
        keyWords_.addEntry(_mapping.getVal(THISACCESS),keyWordThisaccess);
        keyWords_.addEntry(_mapping.getVal(VARARG),keyWordVararg);
        keyWords_.addEntry(_mapping.getVal(FIRSTOPT),keyWordFirstopt);
        keyWords_.addEntry(_mapping.getVal(BOOL),keyWordBool);
        keyWords_.addEntry(_mapping.getVal(INSTANCEOF),keyWordInstanceof);
        keyWords_.addEntry(_mapping.getVal(VALUE_OF),keyWordValueOf);
        keyWords_.addEntry(_mapping.getVal(VALUES),keyWordValues);
        keyWords_.addEntry(_mapping.getVal(THIS),keyWordThis);
        keyWords_.addEntry(_mapping.getVal(THAT),keyWordThat);
        keyWords_.addEntry(_mapping.getVal(LAMBDA),keyWordLambda);
        keyWords_.addEntry(_mapping.getVal(ID),keyWordId);
        keyWords_.addEntry(_mapping.getVal(NULL),keyWordNull);
        keyWords_.addEntry(_mapping.getVal(TRUE),keyWordTrue);
        keyWords_.addEntry(_mapping.getVal(FALSE),keyWordFalse);
        keyWords_.addEntry(_mapping.getVal(PARENT),keyWordParent);
        return keyWords_;
    }
    public StringList allKeyWordsValues() {
        StringList keyWords_ = new StringList();
        keyWords_.add(keyWordValue);
        keyWords_.add(keyWordExplicit);
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
        keyWords_.add(keyWordStaticCall);
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
        keyWords_.add(keyWordDefaultValue);
        keyWords_.add(keyWordReturn);
        keyWords_.add(keyWordThrow);
        keyWords_.add(keyWordBreak);
        keyWords_.add(keyWordContinue);
        keyWords_.add(keyWordOperator);
        keyWords_.add(keyWordToString);
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
        keyWords_.add(keyWordParent);
        return keyWords_;
    }
    public static StringMap<String> mapping() {
        StringMap<String> k_ = new StringMap<String>();
        k_.addEntry(CLASS,"Class");
        k_.addEntry(VAR,"Var");
        k_.addEntry(TRUE,"True");
        k_.addEntry(FALSE,"False");
        k_.addEntry(ESC_UNICODE,"EscUnicode");
        k_.addEntry(ESC_FORM,"EscForm");
        k_.addEntry(ESC_LINE,"EscLine");
        k_.addEntry(ESC_FEED,"EscFeed");
        k_.addEntry(ESC_SPACE,"EscSpace");
        k_.addEntry(ESC_TAB,"EscTab");
        k_.addEntry(ESC_BOUND,"EscBound");
        k_.addEntry(NB_EXP_DEC,"NbExpDec");
        k_.addEntry(NB_EXP_BIN,"NbExpBin");
        k_.addEntry(NB_HEX,"NbHex");
        k_.addEntry(NB_HEX_END,"NbHexEnd");
        k_.addEntry(NB_BIN,"NbBin");
        k_.addEntry(NB_SUF_DOUBLE_PRIM,"NbSufDoublePrim");
        k_.addEntry(NB_SUF_DOUBLE,"NbSufDouble");
        k_.addEntry(NB_SUF_FLOAT_PRIM,"NbSufFloatPrim");
        k_.addEntry(NB_SUF_FLOAT,"NbSufFloat");
        k_.addEntry(NB_SUF_LONG_PRIM,"NbSufLongPrim");
        k_.addEntry(NB_SUF_LONG,"NbSufLong");
        k_.addEntry(NB_SUF_INTEGER_PRIM,"NbSufIntegerPrim");
        k_.addEntry(NB_SUF_INTEGER,"NbSufInteger");
        k_.addEntry(NB_SUF_CHARACTER_PRIM,"NbSufCharacterPrim");
        k_.addEntry(NB_SUF_CHARACTER,"NbSufCharacter");
        k_.addEntry(NB_SUF_SHORT_PRIM,"NbSufShortPrim");
        k_.addEntry(NB_SUF_SHORT,"NbSufShort");
        k_.addEntry(NB_SUF_BYTE_PRIM,"NbSufBytePrim");
        k_.addEntry(NB_SUF_BYTE,"NbSufByte");
        k_.addEntry(NB_DIG0,"NbDig0");
        k_.addEntry(NB_DIG1,"NbDig1");
        k_.addEntry(NB_DIG2,"NbDig2");
        k_.addEntry(NB_DIG3,"NbDig3");
        k_.addEntry(NB_DIG4,"NbDig4");
        k_.addEntry(NB_DIG5,"NbDig5");
        k_.addEntry(IF,"If");
        k_.addEntry(ELSE,"Else");
        k_.addEntry(ELSEIF,"Elseif");
        k_.addEntry(ITER,"Iter");
        k_.addEntry(NEW,"New");
        k_.addEntry(FOR,"For");
        k_.addEntry(INTERFACE,"Interface");
        k_.addEntry(STATIC,"Static");
        k_.addEntry(FINAL,"Final");
        k_.addEntry(ENUM,"Enum");
        k_.addEntry(NULL,"Null");
        k_.addEntry(RETURN,"Return");
        k_.addEntry(THAT,"That");
        k_.addEntry(STATIC_CALL,"StaticCall");
        k_.addEntry(WHILE,"While");
        k_.addEntry(BREAK,"Break");
        k_.addEntry(CONTINUE,"Continue");
        k_.addEntry(LAMBDA,"Lambda");
        k_.addEntry(ABSTRACT,"Abstract");
        k_.addEntry(THIS,"This");
        k_.addEntry(SUPER,"Super");
        k_.addEntry(INSTANCEOF,"Instanceof");
        k_.addEntry(SWITCH,"Switch");
        k_.addEntry(CASE,"Case");
        k_.addEntry(DEFAULT,"Default");
        k_.addEntry(ID,"Id");
        k_.addEntry(FIRSTOPT,"Firstopt");
        k_.addEntry(VARARG,"Vararg");
        k_.addEntry(CLASSCHOICE,"Classchoice");
        k_.addEntry(SUPERACCESS,"Superaccess");
        k_.addEntry(THISACCESS,"Thisaccess");
        k_.addEntry(INTERN,"Intern");
        k_.addEntry(VALUE,"Value");
        k_.addEntry(INTERFACES,"Interfaces");
        k_.addEntry(OPERATOR,"Operator");
        k_.addEntry(TO_STRING,"ToString");
        k_.addEntry(EXPLICIT,"Explicit");
        k_.addEntry(CAST,"Cast");
        k_.addEntry(DO,"Do");
        k_.addEntry(TRY,"Try");
        k_.addEntry(CATCH,"Catch");
        k_.addEntry(FINALLY,"Finally");
        k_.addEntry(DEFAULT_VALUE,"DefaultValue");
        k_.addEntry(BOOL,"Bool");
        k_.addEntry(VALUE_OF,"ValueOf");
        k_.addEntry(VALUES,"Values");
        k_.addEntry(PARENT,"Parent");
        k_.addEntry(THROW,"Throw");
        k_.addEntry(NORMAL,"Normal");
        k_.addEntry(FOREACH,"Foreach");
        k_.addEntry(ANNOTATION,"Annotation");
        k_.addEntry(PUBLIC,"Public");
        k_.addEntry(PACKAGE,"Package");
        k_.addEntry(PROTECTED,"Protected");
        k_.addEntry(PRIVATE,"Private");
        return k_;
    }
    public StringMap<String> allEscapings() {
        return allEscapings(mapping());
    }
    public StringMap<String> allEscapings(StringMap<String> _mapping) {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(ESC_UNICODE),keyWordEscUnicode);
        keyWords_.addEntry(_mapping.getVal(ESC_FORM),keyWordEscForm);
        keyWords_.addEntry(_mapping.getVal(ESC_LINE),keyWordEscLine);
        keyWords_.addEntry(_mapping.getVal(ESC_FEED),keyWordEscFeed);
        keyWords_.addEntry(_mapping.getVal(ESC_SPACE),keyWordEscSpace);
        keyWords_.addEntry(_mapping.getVal(ESC_TAB),keyWordEscTab);
        keyWords_.addEntry(_mapping.getVal(ESC_BOUND),keyWordEscBound);
        return keyWords_;
    }
    public StringMap<String> allNbWordsBasic() {
        return allNbWordsBasic(mapping());
    }
    public StringMap<String> allNbWordsBasic(StringMap<String> _mapping) {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(NB_EXP_DEC),keyWordNbExpDec);
        keyWords_.addEntry(_mapping.getVal(NB_EXP_BIN),keyWordNbExpBin);
        keyWords_.addEntry(_mapping.getVal(NB_HEX),keyWordNbHex);
        keyWords_.addEntry(_mapping.getVal(NB_HEX_END),keyWordNbHexEnd);
        keyWords_.addEntry(_mapping.getVal(NB_BIN),keyWordNbBin);
        return keyWords_;
    }
    public StringMap<String> allNbWordsDec() {
        return allNbWordsDec(mapping());
    }
    public StringMap<String> allNbWordsDec(StringMap<String> _mapping) {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(NB_EXP_DEC),keyWordNbExpDec);
        return keyWords_;
    }
    public StringMap<String> allNbWordsBin() {
        return allNbWordsBin(mapping());
    }
    public StringMap<String> allNbWordsBin(StringMap<String> _mapping) {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(NB_EXP_BIN),keyWordNbExpBin);
        keyWords_.addEntry(_mapping.getVal(NB_HEX_END),keyWordNbHexEnd);
        return keyWords_;
    }

    public StringMap<String> allNbWordsPreBin(StringMap<String> _mapping) {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(_mapping.getVal(NB_HEX),keyWordNbHex);
        keyWords_.addEntry(_mapping.getVal(NB_BIN),keyWordNbBin);
        return keyWords_;
    }

    public String getKeyWordNbDig() {
        return keyWordNbDig;
    }

    public StringMap<String> allNbWords(StringMap<String> _othersWords) {
        return allNbWords(mapping(),_othersWords);
    }
    public StringMap<String> allNbWords(StringMap<String> _mapping,StringMap<String> _othersWords) {
        StringMap<String> keyWords_ = new StringMap<String>(_othersWords);
//        for (EntryCust<String,String> o: _othersWords.entryList()) {
//            keyWords_.addEntry(_mapping.getVal(o.getKey()),o.getValue());
//        }
        keyWords_.addEntry(_mapping.getVal(NB_SUF_DOUBLE_PRIM),keyWordNbSufDoublePrim);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_DOUBLE),keyWordNbSufDouble);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_FLOAT_PRIM),keyWordNbSufFloatPrim);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_FLOAT),keyWordNbSufFloat);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_LONG_PRIM),keyWordNbSufLongPrim);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_LONG),keyWordNbSufLong);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_INTEGER_PRIM),keyWordNbSufIntegerPrim);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_INTEGER),keyWordNbSufInteger);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_CHARACTER_PRIM),keyWordNbSufCharacterPrim);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_CHARACTER),keyWordNbSufCharacter);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_SHORT_PRIM),keyWordNbSufShortPrim);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_SHORT),keyWordNbSufShort);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_BYTE_PRIM),keyWordNbSufBytePrim);
        keyWords_.addEntry(_mapping.getVal(NB_SUF_BYTE),keyWordNbSufByte);
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
        return StringUtil.contains(allKeyWordsValues(), _word);
    }

    public SuffixedNumber getNbKeyWord(String _string, int _from) {
        CustList<String> keyWords_ = new CustList<String>();
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
        SuffixedNumbers suff_ = new SuffixedNumbers();
        suff_.addEntry(keyWordNbSufDoublePrim, NumberInfos.PRIM_DOUBLE);
        suff_.addEntry(keyWordNbSufDouble,NumberInfos.WRAP_DOUBLE);
        suff_.addEntry(keyWordNbSufFloatPrim,NumberInfos.PRIM_FLOAT);
        suff_.addEntry(keyWordNbSufFloat,NumberInfos.WRAP_FLOAT);
        suff_.addEntry(keyWordNbSufLongPrim,NumberInfos.PRIM_LONG);
        suff_.addEntry(keyWordNbSufLong,NumberInfos.WRAP_LONG);
        suff_.addEntry(keyWordNbSufIntegerPrim,NumberInfos.PRIM_INT);
        suff_.addEntry(keyWordNbSufInteger,NumberInfos.WRAP_INT);
        suff_.addEntry(keyWordNbSufCharacterPrim,NumberInfos.PRIM_CHAR);
        suff_.addEntry(keyWordNbSufCharacter,NumberInfos.WRAP_CHAR);
        suff_.addEntry(keyWordNbSufShortPrim,NumberInfos.PRIM_SHORT);
        suff_.addEntry(keyWordNbSufShort,NumberInfos.WRAP_SHORT);
        suff_.addEntry(keyWordNbSufBytePrim,NumberInfos.PRIM_BYTE);
        suff_.addEntry(keyWordNbSufByte,NumberInfos.WRAP_BYTE);
        CustList<SuffixedNumber> list_ = new CustList<SuffixedNumber>();
        int index_ = 0;
        for (String k: keyWords_) {
            if (StringExpUtil.startsWithKeyWord(_string,_from, k)) {
                list_.add(suff_.get(index_));
            }
            index_++;
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
        keyWords_.add(keyWordEscSpace);
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
    public void setKeyWordWhile(String _k) {
        keyWordWhile = _k;
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
    public void setKeyWordIf(String _k) {
        keyWordIf = _k;
    }
    public String getKeyWordElse() {
        return keyWordElse;
    }
    public void setKeyWordElse(String _k) {
        keyWordElse = _k;
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
    public void setKeyWordTry(String _k) {
        keyWordTry = _k;
    }
    public String getKeyWordFinally() {
        return keyWordFinally;
    }
    public void setKeyWordFinally(String _k) {
        keyWordFinally = _k;
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
    public void setKeyWordSwitch(String _k) {
        keyWordSwitch = _k;
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
    public void setKeyWordReturn(String _k) {
        keyWordReturn = _k;
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
    public void setKeyWordBreak(String _k) {
        keyWordBreak = _k;
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

    public String getKeyWordEscSpace() {
        return keyWordEscSpace;
    }

    public void setKeyWordEscSpace(String _keyWordEscSpace) {
        this.keyWordEscSpace = _keyWordEscSpace;
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

    public void setKeyWordNbDig0(String _keyWordNbDig0) {
        this.keyWordNbDig0 = _keyWordNbDig0;
    }

    public void setKeyWordNbDig1(String _keyWordNbDig1) {
        this.keyWordNbDig1 = _keyWordNbDig1;
    }

    public void setKeyWordNbDig2(String _keyWordNbDig2) {
        this.keyWordNbDig2 = _keyWordNbDig2;
    }

    public void setKeyWordNbDig3(String _keyWordNbDig3) {
        this.keyWordNbDig3 = _keyWordNbDig3;
    }

    public void setKeyWordNbDig4(String _keyWordNbDig4) {
        this.keyWordNbDig4 = _keyWordNbDig4;
    }

    public void setKeyWordNbDig5(String _keyWordNbDig5) {
        this.keyWordNbDig5 = _keyWordNbDig5;
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
