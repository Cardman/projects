package code.formathtml.errors;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.stds.ErrorCat;
import code.expressionlanguage.errors.stds.StdWordError;
import code.formathtml.Configuration;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class RendKeyWords {
    public static final String TAG_TEXTAREA="TagTextarea";
    public static final String TAG_SELECT="TagSelect";
    public static final String TAG_FORM="TagForm";
    public static final String TAG_FIELD="TagField";
    public static final String TAG_IMPORT="TagImport";
    public static final String TAG_A="TagAnchor";
    public static final String TAG_PARAM="TagParam";
    public static final String TAG_INPUT="TagInput";
    public static final String TAG_SUBMIT="TagSubmit";
    public static final String TAG_IMG="TagImg";
    public static final String TAG_SPAN="TagSpan";
    public static final String TAG_SET="TagSet";
    public static final String TAG_H6="TagHSix";
    public static final String TAG_DIV="TagDiv";
    public static final String TAG_BR="TagBr";
    public static final String TAG_H1="TagHOne";
    public static final String TAG_TH="TagTh";
    public static final String TAG_H5="TagHFive";
    public static final String TAG_OPTION="TagOption";
    public static final String TAG_MAP="TagMap";
    public static final String TAG_HEAD="TagHead";
    public static final String TAG_H2="TagHTwo";
    public static final String TAG_HR="TagHr";
    public static final String TAG_STYLE="TagStyle";
    public static final String TAG_LINK="TagLink";
    public static final String TAG_BODY="TagBody";
    public static final String TAG_OL="TagOl";
    public static final String TAG_H4="TagHFour";
    public static final String TAG_H3="TagHThree";
    public static final String TAG_P="TagPar";
    public static final String TAG_B="TagBold";
    public static final String TAG_PRE="TagPre";
    public static final String TAG_TABLE="TagTable";
    public static final String TAG_TD="TagTd";
    public static final String TAG_UL="TagUl";
    public static final String TAG_TR="TagTr";
    public static final String TAG_LI="TagLi";
    public static final String TAG_I="TagItalic";
    public static final String TAG_CONTINUE="TagContinue";
    public static final String TAG_DEFAULT="TagDefault";
    public static final String TAG_PACKAGE="TagPackage";
    public static final String TAG_FINALLY="TagFinally";
    public static final String TAG_CLASS="TagClass";
    public static final String TAG_IF="TagIf";
    public static final String TAG_FOR="TagFor";
    public static final String TAG_RETURN="TagReturn";
    public static final String TAG_ELSEIF="TagElseif";
    public static final String TAG_DO="TagDo";
    public static final String TAG_TRY="TagTry";
    public static final String TAG_WHILE="TagWhile";
    public static final String TAG_ELSE="TagElse";
    public static final String TAG_SWITCH="TagSwitch";
    public static final String TAG_CATCH="TagCatch";
    public static final String TAG_CASE="TagCase";
    public static final String TAG_BREAK="TagBreak";
    public static final String TAG_THROW="TagThrow";
    public static final String TAG_CAPTION="TagCaption";
    public static final String TAG_MESSAGE="TagMessage";

    public static final String ATTR_TYPE="AttrType";
    public static final String ATTR_MULTIPLE="AttrMultiple";
    public static final String ATTR_CLASSNAME="AttrClassName";
    public static final String ATTR_CONVERTVALUE="AttrConvertValue";
    public static final String ATTR_KEEPFIELDS="AttrKeepFields";
    public static final String ATTR_KEYCLASSNAME="AttrKeyClassName";
    public static final String ATTR_VALUEMESSAGE="AttrValueMessage";
    public static final String ATTR_CONVERTFIELDVALUE="AttrConvertFieldValue";
    public static final String ATTR_VARCLASSNAME="AttrVarClassName";
    public static final String ATTR_CONVERTFIELD="AttrConvertField";
    public static final String ATTR_ESCAPEDAMP="AttrEscapedAmp";
    public static final String ATTR_INDEXCLASSNAME="AttrIndexClassName";
    public static final String ATTR_CONDITION="AttrCondition";
    public static final String ATTR_LIST="AttrList";
    public static final String ATTR_VALUE="AttrValue";
    public static final String ATTR_MAP="AttrMap";
    public static final String ATTR_VAR="AttrVar";
    public static final String ATTR_FROM="AttrFrom";
    public static final String ATTR_STEP="AttrStep";
    public static final String ATTR_TO="AttrTo";
    public static final String ATTR_EQ="AttrEq";
    public static final String ATTR_KEY="AttrKey";
    public static final String ATTR_INIT="AttrInit";
    public static final String ATTR_TITLE="AttrTitle";
    public static final String ATTR_QUOTED="AttrQuoted";
    public static final String ATTR_ESCAPED="AttrEscaped";
    public static final String ATTR_NAME="AttrName";
    public static final String ATTR_DEFAULT="AttrDefault";
    public static final String ATTR_PREPARE="AttrPrepare";
    public static final String ATTR_PARAM="AttrParam";
    public static final String ATTR_CHECKED="AttrChecked";
    public static final String ATTR_ID="AttrId";
    public static final String ATTR_ALIAS="AttrAlias";
    public static final String ATTR_COMMAND="AttrCommand";
    public static final String ATTR_VARVALUE="AttrVarValue";
    public static final String ATTR_GROUPID="AttrGroupId";
    public static final String ATTR_VALIDATOR="AttrValidator";
    public static final String ATTR_FORM="AttrForm";
    public static final String ATTR_FOR="AttrFor";
    public static final String ATTR_PAGE="AttrPage";
    public static final String ATTR_N_I="AttrNi";
    public static final String ATTR_ACTION="AttrAction";
    public static final String ATTR_N_F="AttrNf";
    public static final String ATTR_CONVERT="AttrConvert";
    public static final String ATTR_BEAN="AttrBean";
    public static final String ATTR_SELECTED="AttrSelected";
    public static final String ATTR_LABEL="AttrLabel";
    public static final String ATTR_MESSAGE="AttrMessage";
    public static final String ATTR_HREF="AttrHref";
    public static final String ATTR_N_A="AttrNa";
    public static final String ATTR_CLASS="AttrClass";
    public static final String ATTR_REL="AttrRel";
    public static final String ATTR_SRC="AttrSrc";
    public static final String ATTR_WIDTH="AttrWidth";
    public static final String ATTR_COLS="AttrCols";
    public static final String ATTR_DELAY="AttrDelay";
    public static final String ATTR_ROWS="AttrRows";
    public static final String VALUE_RANGE="ValueRange";
    public static final String VALUE_TEXT="ValueText";
    public static final String VALUE_RADIO="ValueRadio";
    public static final String VALUE_CHECKBOX="ValueCheckbox";
    public static final String VALUE_NUMBER="ValueNumber";
    public static final String VALUE_SUBMIT="ValueSubmit";
    public static final String VALUE_LICIRCLE="ValueLiCircle";
    public static final String VALUE_LIDISK="ValueLiDisk";
    public static final String VALUE_LINB="ValueLiNb";
    public static final String VALUE_LIMINLAT="ValueLiMinLat";
    public static final String VALUE_LISQUARE="ValueLiSquare";
    public static final String VALUE_LIRECT="ValueLiRect";
    public static final String VALUE_LIMAJLET="ValueLiMajLet";
    public static final String VALUE_LIMINLET="ValueLiMinLet";
    public static final String VALUE_STYLE="ValueStyle";
    public static final String VALUE_LIMAJLAT="ValueLiMajLat";
    public static final String STYLE_ATTR_BACKGROUND="StyleAttrBackground";
    public static final String STYLE_ATTR_BORDER="StyleAttrBorder";
    public static final String STYLE_ATTR_FONTFAM="StyleAttrFontFam";
    public static final String STYLE_ATTR_FONTSIZE="StyleAttrFontSize";
    public static final String STYLE_ATTR_COLOR="StyleAttrColor";
    public static final String STYLE_VALUE_CYAN="StyleValueCyan";
    public static final String STYLE_VALUE_BLUE="StyleValueBlue";
    public static final String STYLE_VALUE_YELLOW="StyleValueYellow";
    public static final String STYLE_VALUE_MAGENTA="StyleValueMagenta";
    public static final String STYLE_VALUE_GREEN="StyleValueGreen";
    public static final String STYLE_VALUE_GREY="StyleValueGrey";
    public static final String STYLE_VALUE_BLACK="StyleValueBlack";
    public static final String STYLE_VALUE_WHITE="StyleValueWhite";
    public static final String STYLE_VALUE_RED="StyleValueRed";
    public static final String STYLE_VALUE_RGB="StyleValueRgb";
    public static final String STYLE_UNIT_SOLID="StyleUnitSolid";
    public static final String STYLE_UNIT_EM="StyleUnitEm";
    public static final String STYLE_UNIT_PX="StyleUnitPx";

    private String keyWordFor = "for";
    private String keyWordWhile = "while";
    private String keyWordDo = "do";

    private String keyWordIf = "if";
    private String keyWordElse = "else";
    private String keyWordElseif = "elseif";

    private String keyWordTry = "try";
    private String keyWordFinally = "finally";
    private String keyWordCatch = "catch";

    private String keyWordSwitch = "switch";
    private String keyWordCase = "case";
    private String keyWordDefault = "default";

    private String keyWordReturn = "return";
    private String keyWordThrow = "throw";
    private String keyWordBreak = "break";
    private String keyWordContinue = "continue";

    private String keyWordSet="set";
    private String keyWordImport="import";
    private String keyWordPackage="package";
    private String keyWordClass="class";
    private String keyWordField="field";
    private String keyWordForm="form";
    private String keyWordSubmit="submit";
    private String keyWordImg="img";
    private String keyWordSelect="select";
    private String keyWordMessage="message";
    private String keyWordAnchor="a";
    private String keyWordParam="param";
    private String keyWordInput="input";
    private String keyWordTextarea="textarea";
    private String keyWordSpan="span";
    private String keyWordLink="link";
    private String keyWordStyle="style";
    private String keyWordBody="body";
    private String keyWordHead="head";
    private String keyWordMap="map";
    private String keyWordLi="li";
    private String keyWordOl="ol";
    private String keyWordUl="ul";
    private String keyWordBold="b";
    private String keyWordItalic="i";
    private String keyWordPre="pre";
    private String keyWordHOne="h1";
    private String keyWordHTwo="h2";
    private String keyWordHThree="h3";
    private String keyWordHFour="h4";
    private String keyWordHFive="h5";
    private String keyWordHSix="h6";
    private String keyWordBr="br";
    private String keyWordHr="hr";
    private String keyWordPar="p";
    private String keyWordTable="table";
    private String keyWordCaption="caption";
    private String keyWordTd="td";
    private String keyWordTh="th";
    private String keyWordTr="tr";
    private String keyWordDiv="div";
    private String keyWordOption="option";

    private String attrList="list";
    private String attrMap="map";
    private String attrVar="var";
    private String attrKey="key";
    private String attrValue="value";
    private String attrFrom="from";
    private String attrInit="init";
    private String attrStep="step";
    private String attrTo="to";
    private String attrEq="eq";
    private String attrCondition="condition";
    private String attrClassName="className";
    private String attrKeyClassName="keyClassName";
    private String attrVarClassName="varClassName";
    private String attrIndexClassName="indexClassName";
    private String attrAlias="alias";
    private String attrLabel="label";
    private String attrBean="bean";

    private String attrType="type";
    private String attrChecked="checked";
    private String attrSelected="selected";
    private String attrMultiple="multiple";

    private String valueText="text";
    private String valueCheckbox="checkbox";
    private String valueRadio="radio";
    private String valueNumber="number";
    private String valueRange="range";
    private String valueSubmit="submit";

    private String attrNf="n-f";
    private String attrNa="n-a";
    private String attrNi="n-i";

    private String attrParam = "param";

    private String attrName = "name";
    private String attrHref = "href";
    private String attrCommand = "command";
    private String attrAction = "action";
    private String attrTitle = "title";
    private String attrFor = "for";
    private String attrMessage = "message";
    private String attrQuoted = "quoted";
    private String attrEscaped = "escaped";
    private String attrEscapedAmp = "escapedamp";
    private String attrConvert = "convert";
    private String attrConvertValue = "convertValue";
    private String attrConvertField = "convertField";
    private String attrConvertFieldValue = "convertFieldValue";
    private String attrDefault = "default";
    private String attrVarValue = "varValue";
    private String attrPrepare = "prepare";
    private String attrForm = "form";
    private String attrValueMessage = "valueMessage";
    private String attrId = "id";
    private String attrGroupId = "groupId";
    private String attrValidator = "validator";
    private String attrPage = "page";
    private String attrKeepFields = "keepfields";
    private String attrSrc = "src";
    private String attrRows = "rows";
    private String attrCols = "cols";

    private String attrClass="class";
    private String attrWidth="width";
    private String attrDelay="delay";
    private String attrRel="rel";

    private String valueStyle="stylesheet";

    private String valueLiNb="1";
    private String valueLiMinLet="a";
    private String valueLiMajLet="A";
    private String valueLiMinLat="i";
    private String valueLiMajLat="I";
    private String valueLiCircle="circle";
    private String valueLiDisk="disc";
    private String valueLiRect="rect";
    private String valueLiSquare="square";


    private String styleAttrFontFam="font-family";
    private String styleAttrFontSize="font-size";
    private String styleAttrColor="color";
    private String styleAttrBackground="background";
    private String styleAttrBorder="border";

    private String styleValueRgb="rgb";
    private String styleValueRed="red";
    private String styleValueGreen="green";
    private String styleValueBlue="blue";
    private String styleValueYellow="yellow";
    private String styleValueCyan="cyan";
    private String styleValueMagenta="magenta";
    private String styleValueBlack="black";
    private String styleValueGrey="grey";
    private String styleValueWhite="white";
    private String styleUnitPx="px";
    private String styleUnitEm="em";
    private String styleUnitSolid="solid";
    public void validateTagContents(Configuration _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getContext().getAnalyzing().getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyWord(),key_));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                    _cont.addStdError(err_);
                    break;
                }
            }
        }
    }
    public void validateDuplicates(Configuration _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getContext().getAnalyzing().getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateKeyWord(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_KEY_WORD);
                _cont.addStdError(err_);
            }
        }
    }
    public void validateAttrContents(Configuration _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getContext().getAnalyzing().getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyWord(),key_));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.addStdError(err_);
                continue;
            }
            if (!StringList.quickEq(key_,ATTR_PARAM)
                    &&keyWordValue_.startsWith(attrParam)) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateKeyWord(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_KEY_WORD);
                _cont.addStdError(err_);
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)&&c!='-') {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                    _cont.addStdError(err_);
                    break;
                }
            }
        }
    }
    public void validateValueContents(Configuration _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getContext().getAnalyzing().getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyWord(),key_));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.addStdError(err_);
            }
        }
    }
    public void validateStyleValueContents(Configuration _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getContext().getAnalyzing().getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyWord(),key_));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                    _cont.addStdError(err_);
                    break;
                }
            }
        }
    }
    public void validateStyleUnitContents(Configuration _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getContext().getAnalyzing().getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String keyWordValue_ = e.getValue();
            if (keyWordValue_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyWord(),key_));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.addStdError(err_);
                continue;
            }
            for (char c: keyWordValue_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordChar(),keyWordValue_,Character.toString(c)));
                    err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                    _cont.addStdError(err_);
                    break;
                }
            }
            if (StringExpUtil.isDigit(keyWordValue_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirst(),keyWordValue_,Character.toString(keyWordValue_.charAt(0))));
                err_.setErrCat(ErrorCat.WRITE_KEY_WORD);
                _cont.addStdError(err_);
            }
        }
    }
    public StringMap<String> allTags() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(TAG_SELECT,keyWordSelect);
        keyWords_.addEntry(TAG_TEXTAREA,keyWordTextarea);
        keyWords_.addEntry(TAG_ELSE,keyWordElse);
        keyWords_.addEntry(TAG_CASE,keyWordCase);
        keyWords_.addEntry(TAG_SWITCH,keyWordSwitch);
        keyWords_.addEntry(TAG_DO,keyWordDo);
        keyWords_.addEntry(TAG_TRY,keyWordTry);
        keyWords_.addEntry(TAG_WHILE,keyWordWhile);
        keyWords_.addEntry(TAG_ELSEIF,keyWordElseif);
        keyWords_.addEntry(TAG_BREAK,keyWordBreak);
        keyWords_.addEntry(TAG_THROW,keyWordThrow);
        keyWords_.addEntry(TAG_CATCH,keyWordCatch);
        keyWords_.addEntry(TAG_CAPTION,keyWordCaption);
        keyWords_.addEntry(TAG_MESSAGE,keyWordMessage);
        keyWords_.addEntry(TAG_OL,keyWordOl);
        keyWords_.addEntry(TAG_HEAD,keyWordHead);
        keyWords_.addEntry(TAG_UL,keyWordUl);
        keyWords_.addEntry(TAG_IMPORT,keyWordImport);
        keyWords_.addEntry(TAG_B,keyWordBold);
        keyWords_.addEntry(TAG_FIELD,keyWordField);
        keyWords_.addEntry(TAG_SET,keyWordSet);
        keyWords_.addEntry(TAG_FORM,keyWordForm);
        keyWords_.addEntry(TAG_SPAN,keyWordSpan);
        keyWords_.addEntry(TAG_STYLE,keyWordStyle);
        keyWords_.addEntry(TAG_BODY,keyWordBody);
        keyWords_.addEntry(TAG_MAP,keyWordMap);
        keyWords_.addEntry(TAG_SUBMIT,keyWordSubmit);
        keyWords_.addEntry(TAG_IMG,keyWordImg);
        keyWords_.addEntry(TAG_PARAM,keyWordParam);
        keyWords_.addEntry(TAG_INPUT,keyWordInput);
        keyWords_.addEntry(TAG_A,keyWordAnchor);
        keyWords_.addEntry(TAG_LINK,keyWordLink);
        keyWords_.addEntry(TAG_LI,keyWordLi);
        keyWords_.addEntry(TAG_H6,keyWordHSix);
        keyWords_.addEntry(TAG_BR,keyWordBr);
        keyWords_.addEntry(TAG_H2,keyWordHTwo);
        keyWords_.addEntry(TAG_H4,keyWordHFour);
        keyWords_.addEntry(TAG_TR,keyWordTr);
        keyWords_.addEntry(TAG_TD,keyWordTd);
        keyWords_.addEntry(TAG_H1,keyWordHOne);
        keyWords_.addEntry(TAG_DIV,keyWordDiv);
        keyWords_.addEntry(TAG_OPTION,keyWordOption);
        keyWords_.addEntry(TAG_TH,keyWordTh);
        keyWords_.addEntry(TAG_TABLE,keyWordTable);
        keyWords_.addEntry(TAG_HR,keyWordHr);
        keyWords_.addEntry(TAG_P,keyWordPar);
        keyWords_.addEntry(TAG_I,keyWordItalic);
        keyWords_.addEntry(TAG_H3,keyWordHThree);
        keyWords_.addEntry(TAG_H5,keyWordHFive);
        keyWords_.addEntry(TAG_PRE,keyWordPre);
        keyWords_.addEntry(TAG_FINALLY,keyWordFinally);
        keyWords_.addEntry(TAG_DEFAULT,keyWordDefault);
        keyWords_.addEntry(TAG_PACKAGE,keyWordPackage);
        keyWords_.addEntry(TAG_CONTINUE,keyWordContinue);
        keyWords_.addEntry(TAG_CLASS,keyWordClass);
        keyWords_.addEntry(TAG_FOR,keyWordFor);
        keyWords_.addEntry(TAG_IF,keyWordIf);
        keyWords_.addEntry(TAG_RETURN,keyWordReturn);
        return keyWords_;
    }
    public StringMap<String> allAttrs() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(ATTR_CLASSNAME,attrClassName);
        keyWords_.addEntry(ATTR_MULTIPLE,attrMultiple);
        keyWords_.addEntry(ATTR_TYPE,attrType);
        keyWords_.addEntry(ATTR_INDEXCLASSNAME,attrIndexClassName);
        keyWords_.addEntry(ATTR_CONVERTFIELD,attrConvertField);
        keyWords_.addEntry(ATTR_CONVERTFIELDVALUE,attrConvertFieldValue);
        keyWords_.addEntry(ATTR_VALUEMESSAGE,attrValueMessage);
        keyWords_.addEntry(ATTR_ESCAPEDAMP,attrEscapedAmp);
        keyWords_.addEntry(ATTR_KEYCLASSNAME,attrKeyClassName);
        keyWords_.addEntry(ATTR_VARCLASSNAME,attrVarClassName);
        keyWords_.addEntry(ATTR_CONVERTVALUE,attrConvertValue);
        keyWords_.addEntry(ATTR_KEEPFIELDS,attrKeepFields);
        keyWords_.addEntry(ATTR_TITLE,attrTitle);
        keyWords_.addEntry(ATTR_FOR,attrFor);
        keyWords_.addEntry(ATTR_FROM,attrFrom);
        keyWords_.addEntry(ATTR_SELECTED,attrSelected);
        keyWords_.addEntry(ATTR_N_A,attrNa);
        keyWords_.addEntry(ATTR_TO,attrTo);
        keyWords_.addEntry(ATTR_STEP,attrStep);
        keyWords_.addEntry(ATTR_INIT,attrInit);
        keyWords_.addEntry(ATTR_VALUE,attrValue);
        keyWords_.addEntry(ATTR_COMMAND,attrCommand);
        keyWords_.addEntry(ATTR_CONDITION,attrCondition);
        keyWords_.addEntry(ATTR_HREF,attrHref);
        keyWords_.addEntry(ATTR_MESSAGE,attrMessage);
        keyWords_.addEntry(ATTR_CHECKED,attrChecked);
        keyWords_.addEntry(ATTR_ACTION,attrAction);
        keyWords_.addEntry(ATTR_EQ,attrEq);
        keyWords_.addEntry(ATTR_KEY,attrKey);
        keyWords_.addEntry(ATTR_LABEL,attrLabel);
        keyWords_.addEntry(ATTR_BEAN,attrBean);
        keyWords_.addEntry(ATTR_N_I,attrNi);
        keyWords_.addEntry(ATTR_N_F,attrNf);
        keyWords_.addEntry(ATTR_PARAM,attrParam);
        keyWords_.addEntry(ATTR_ALIAS,attrAlias);
        keyWords_.addEntry(ATTR_QUOTED,attrQuoted);
        keyWords_.addEntry(ATTR_NAME,attrName);
        keyWords_.addEntry(ATTR_MAP,attrMap);
        keyWords_.addEntry(ATTR_VAR,attrVar);
        keyWords_.addEntry(ATTR_LIST,attrList);
        keyWords_.addEntry(ATTR_PREPARE,attrPrepare);
        keyWords_.addEntry(ATTR_FORM,attrForm);
        keyWords_.addEntry(ATTR_VARVALUE,attrVarValue);
        keyWords_.addEntry(ATTR_DELAY,attrDelay);
        keyWords_.addEntry(ATTR_ESCAPED,attrEscaped);
        keyWords_.addEntry(ATTR_PAGE,attrPage);
        keyWords_.addEntry(ATTR_WIDTH,attrWidth);
        keyWords_.addEntry(ATTR_SRC,attrSrc);
        keyWords_.addEntry(ATTR_VALIDATOR,attrValidator);
        keyWords_.addEntry(ATTR_GROUPID,attrGroupId);
        keyWords_.addEntry(ATTR_CONVERT,attrConvert);
        keyWords_.addEntry(ATTR_ROWS,attrRows);
        keyWords_.addEntry(ATTR_ID,attrId);
        keyWords_.addEntry(ATTR_COLS,attrCols);
        keyWords_.addEntry(ATTR_DEFAULT,attrDefault);
        keyWords_.addEntry(ATTR_CLASS,attrClass);
        keyWords_.addEntry(ATTR_REL,attrRel);
        return keyWords_;
    }

    public StringMap<String> allValues() {
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(VALUE_RANGE,valueRange.trim());
        keyWords_.addEntry(VALUE_TEXT,valueText.trim());
        keyWords_.addEntry(VALUE_CHECKBOX,valueCheckbox.trim());
        keyWords_.addEntry(VALUE_NUMBER,valueNumber.trim());
        keyWords_.addEntry(VALUE_RADIO,valueRadio.trim());
        keyWords_.addEntry(VALUE_SUBMIT,valueSubmit.trim());
        keyWords_.addEntry(VALUE_STYLE,valueStyle.trim());
        keyWords_.addEntry(VALUE_LIDISK,valueLiDisk.trim());
        keyWords_.addEntry(VALUE_LIMINLET,valueLiMinLet.trim());
        keyWords_.addEntry(VALUE_LISQUARE,valueLiSquare.trim());
        keyWords_.addEntry(VALUE_LINB,valueLiNb.trim());
        keyWords_.addEntry(VALUE_LIMAJLET,valueLiMajLet.trim());
        keyWords_.addEntry(VALUE_LIMINLAT,valueLiMinLat.trim());
        keyWords_.addEntry(VALUE_LIMAJLAT,valueLiMajLat.trim());
        keyWords_.addEntry(VALUE_LICIRCLE,valueLiCircle.trim());
        keyWords_.addEntry(VALUE_LIRECT,valueLiRect.trim());
        return keyWords_;
    }
    public StringMap<String> allStyleAttrs(){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(STYLE_ATTR_BORDER,styleAttrBorder);
        keyWords_.addEntry(STYLE_ATTR_FONTFAM,styleAttrFontFam);
        keyWords_.addEntry(STYLE_ATTR_FONTSIZE,styleAttrFontSize);
        keyWords_.addEntry(STYLE_ATTR_COLOR,styleAttrColor);
        keyWords_.addEntry(STYLE_ATTR_BACKGROUND,styleAttrBackground);
        return keyWords_;
    }
    public StringMap<String> allStyleValues(){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(STYLE_VALUE_GREY,styleValueGrey.trim());
        keyWords_.addEntry(STYLE_VALUE_MAGENTA,styleValueMagenta.trim());
        keyWords_.addEntry(STYLE_VALUE_GREEN,styleValueGreen.trim());
        keyWords_.addEntry(STYLE_VALUE_CYAN,styleValueCyan.trim());
        keyWords_.addEntry(STYLE_VALUE_YELLOW,styleValueYellow.trim());
        keyWords_.addEntry(STYLE_VALUE_WHITE,styleValueWhite.trim());
        keyWords_.addEntry(STYLE_VALUE_BLUE,styleValueBlue.trim());
        keyWords_.addEntry(STYLE_VALUE_BLACK,styleValueBlack.trim());
        keyWords_.addEntry(STYLE_VALUE_RGB,styleValueRgb.trim());
        keyWords_.addEntry(STYLE_VALUE_RED,styleValueRed.trim());
        return keyWords_;
    }
    public StringMap<String> allStyleUnits(){
        StringMap<String> keyWords_ = new StringMap<String>();
        keyWords_.addEntry(STYLE_UNIT_SOLID,styleUnitSolid);
        keyWords_.addEntry(STYLE_UNIT_PX,styleUnitPx);
        keyWords_.addEntry(STYLE_UNIT_EM,styleUnitEm);
        return keyWords_;
    }
    public String getKeyWordFor() {
        return keyWordFor;
    }
    public void setKeyWordFor(String _keyWordFor) {
        keyWordFor = _keyWordFor;
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

    public String getKeyWordSet() {
        return keyWordSet;
    }

    public void setKeyWordSet(String keyWordSet) {
        this.keyWordSet = keyWordSet;
    }

    public String getKeyWordImport() {
        return keyWordImport;
    }

    public void setKeyWordImport(String keyWordImport) {
        this.keyWordImport = keyWordImport;
    }

    public String getKeyWordPackage() {
        return keyWordPackage;
    }

    public void setKeyWordPackage(String keyWordPackage) {
        this.keyWordPackage = keyWordPackage;
    }

    public String getKeyWordClass() {
        return keyWordClass;
    }

    public void setKeyWordClass(String keyWordClass) {
        this.keyWordClass = keyWordClass;
    }

    public String getKeyWordField() {
        return keyWordField;
    }

    public void setKeyWordField(String keyWordField) {
        this.keyWordField = keyWordField;
    }

    public String getKeyWordForm() {
        return keyWordForm;
    }

    public void setKeyWordForm(String keyWordForm) {
        this.keyWordForm = keyWordForm;
    }

    public String getKeyWordSubmit() {
        return keyWordSubmit;
    }

    public void setKeyWordSubmit(String keyWordSubmit) {
        this.keyWordSubmit = keyWordSubmit;
    }

    public String getKeyWordImg() {
        return keyWordImg;
    }

    public void setKeyWordImg(String keyWordImg) {
        this.keyWordImg = keyWordImg;
    }

    public String getKeyWordSelect() {
        return keyWordSelect;
    }

    public void setKeyWordSelect(String keyWordSelect) {
        this.keyWordSelect = keyWordSelect;
    }

    public String getKeyWordMessage() {
        return keyWordMessage;
    }

    public void setKeyWordMessage(String keyWordMessage) {
        this.keyWordMessage = keyWordMessage;
    }

    public String getKeyWordAnchor() {
        return keyWordAnchor;
    }

    public void setKeyWordAnchor(String keyWordAnchor) {
        this.keyWordAnchor = keyWordAnchor;
    }

    public String getKeyWordParam() {
        return keyWordParam;
    }

    public void setKeyWordParam(String keyWordParam) {
        this.keyWordParam = keyWordParam;
    }

    public String getKeyWordInput() {
        return keyWordInput;
    }

    public void setKeyWordInput(String keyWordInput) {
        this.keyWordInput = keyWordInput;
    }

    public String getKeyWordTextarea() {
        return keyWordTextarea;
    }

    public void setKeyWordTextarea(String keyWordTextarea) {
        this.keyWordTextarea = keyWordTextarea;
    }

    public String getKeyWordSpan() {
        return keyWordSpan;
    }

    public void setKeyWordSpan(String keyWordSpan) {
        this.keyWordSpan = keyWordSpan;
    }

    public String getKeyWordLink() {
        return keyWordLink;
    }

    public void setKeyWordLink(String keyWordLink) {
        this.keyWordLink = keyWordLink;
    }

    public String getKeyWordStyle() {
        return keyWordStyle;
    }

    public void setKeyWordStyle(String keyWordStyle) {
        this.keyWordStyle = keyWordStyle;
    }

    public String getKeyWordBody() {
        return keyWordBody;
    }

    public void setKeyWordBody(String keyWordBody) {
        this.keyWordBody = keyWordBody;
    }

    public String getKeyWordHead() {
        return keyWordHead;
    }

    public void setKeyWordHead(String keyWordHead) {
        this.keyWordHead = keyWordHead;
    }

    public String getKeyWordMap() {
        return keyWordMap;
    }

    public void setKeyWordMap(String keyWordMap) {
        this.keyWordMap = keyWordMap;
    }

    public String getKeyWordLi() {
        return keyWordLi;
    }

    public void setKeyWordLi(String keyWordLi) {
        this.keyWordLi = keyWordLi;
    }

    public String getKeyWordOl() {
        return keyWordOl;
    }

    public void setKeyWordOl(String keyWordOl) {
        this.keyWordOl = keyWordOl;
    }

    public String getKeyWordUl() {
        return keyWordUl;
    }

    public void setKeyWordUl(String keyWordUl) {
        this.keyWordUl = keyWordUl;
    }

    public String getKeyWordBold() {
        return keyWordBold;
    }

    public void setKeyWordBold(String keyWordBold) {
        this.keyWordBold = keyWordBold;
    }

    public String getKeyWordItalic() {
        return keyWordItalic;
    }

    public void setKeyWordItalic(String keyWordItalic) {
        this.keyWordItalic = keyWordItalic;
    }

    public String getKeyWordPre() {
        return keyWordPre;
    }

    public void setKeyWordPre(String keyWordPre) {
        this.keyWordPre = keyWordPre;
    }

    public String getKeyWordHOne() {
        return keyWordHOne;
    }

    public void setKeyWordHOne(String keyWordHOne) {
        this.keyWordHOne = keyWordHOne;
    }

    public String getKeyWordHTwo() {
        return keyWordHTwo;
    }

    public void setKeyWordHTwo(String keyWordHTwo) {
        this.keyWordHTwo = keyWordHTwo;
    }

    public String getKeyWordHThree() {
        return keyWordHThree;
    }

    public void setKeyWordHThree(String keyWordHThree) {
        this.keyWordHThree = keyWordHThree;
    }

    public String getKeyWordHFour() {
        return keyWordHFour;
    }

    public void setKeyWordHFour(String keyWordHFour) {
        this.keyWordHFour = keyWordHFour;
    }

    public String getKeyWordHFive() {
        return keyWordHFive;
    }

    public void setKeyWordHFive(String keyWordHFive) {
        this.keyWordHFive = keyWordHFive;
    }

    public String getKeyWordHSix() {
        return keyWordHSix;
    }

    public void setKeyWordHSix(String keyWordHSix) {
        this.keyWordHSix = keyWordHSix;
    }

    public String getKeyWordBr() {
        return keyWordBr;
    }

    public void setKeyWordBr(String keyWordBr) {
        this.keyWordBr = keyWordBr;
    }

    public String getKeyWordHr() {
        return keyWordHr;
    }

    public void setKeyWordHr(String keyWordHr) {
        this.keyWordHr = keyWordHr;
    }

    public String getKeyWordPar() {
        return keyWordPar;
    }

    public void setKeyWordPar(String keyWordPar) {
        this.keyWordPar = keyWordPar;
    }

    public String getKeyWordTable() {
        return keyWordTable;
    }

    public void setKeyWordTable(String keyWordTable) {
        this.keyWordTable = keyWordTable;
    }

    public String getKeyWordCaption() {
        return keyWordCaption;
    }

    public void setKeyWordCaption(String keyWordCaption) {
        this.keyWordCaption = keyWordCaption;
    }

    public String getKeyWordTd() {
        return keyWordTd;
    }

    public void setKeyWordTd(String keyWordTd) {
        this.keyWordTd = keyWordTd;
    }

    public String getKeyWordTh() {
        return keyWordTh;
    }

    public void setKeyWordTh(String keyWordTh) {
        this.keyWordTh = keyWordTh;
    }

    public String getKeyWordTr() {
        return keyWordTr;
    }

    public void setKeyWordTr(String keyWordTr) {
        this.keyWordTr = keyWordTr;
    }

    public String getKeyWordDiv() {
        return keyWordDiv;
    }

    public void setKeyWordDiv(String keyWordDiv) {
        this.keyWordDiv = keyWordDiv;
    }

    public String getKeyWordOption() {
        return keyWordOption;
    }

    public void setKeyWordOption(String keyWordOptions) {
        this.keyWordOption = keyWordOptions;
    }

    public String getAttrList() {
        return attrList;
    }

    public void setAttrList(String attrList) {
        this.attrList = attrList;
    }

    public String getAttrMap() {
        return attrMap;
    }

    public void setAttrMap(String attrMap) {
        this.attrMap = attrMap;
    }

    public String getAttrVar() {
        return attrVar;
    }

    public void setAttrVar(String attrVar) {
        this.attrVar = attrVar;
    }

    public String getAttrKey() {
        return attrKey;
    }

    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrFrom() {
        return attrFrom;
    }

    public void setAttrFrom(String attrFrom) {
        this.attrFrom = attrFrom;
    }

    public String getAttrInit() {
        return attrInit;
    }

    public void setAttrInit(String attrInit) {
        this.attrInit = attrInit;
    }

    public String getAttrStep() {
        return attrStep;
    }

    public void setAttrStep(String attrStep) {
        this.attrStep = attrStep;
    }

    public String getAttrTo() {
        return attrTo;
    }

    public void setAttrTo(String attrTo) {
        this.attrTo = attrTo;
    }

    public String getAttrEq() {
        return attrEq;
    }

    public void setAttrEq(String attrEq) {
        this.attrEq = attrEq;
    }

    public String getAttrCondition() {
        return attrCondition;
    }

    public void setAttrCondition(String attrCondition) {
        this.attrCondition = attrCondition;
    }

    public String getAttrClassName() {
        return attrClassName;
    }

    public void setAttrClassName(String attrClassName) {
        this.attrClassName = attrClassName;
    }

    public String getAttrKeyClassName() {
        return attrKeyClassName;
    }

    public void setAttrKeyClassName(String attrKeyClassName) {
        this.attrKeyClassName = attrKeyClassName;
    }

    public String getAttrVarClassName() {
        return attrVarClassName;
    }

    public void setAttrVarClassName(String attrVarClassName) {
        this.attrVarClassName = attrVarClassName;
    }

    public String getAttrIndexClassName() {
        return attrIndexClassName;
    }

    public void setAttrIndexClassName(String attrIndexClassName) {
        this.attrIndexClassName = attrIndexClassName;
    }

    public String getAttrAlias() {
        return attrAlias;
    }

    public void setAttrAlias(String attrAlias) {
        this.attrAlias = attrAlias;
    }

    public String getAttrLabel() {
        return attrLabel;
    }

    public void setAttrLabel(String attrLabel) {
        this.attrLabel = attrLabel;
    }

    public String getAttrBean() {
        return attrBean;
    }

    public void setAttrBean(String attrBean) {
        this.attrBean = attrBean;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrChecked() {
        return attrChecked;
    }

    public void setAttrChecked(String attrChecked) {
        this.attrChecked = attrChecked;
    }

    public String getAttrSelected() {
        return attrSelected;
    }

    public void setAttrSelected(String attrSelected) {
        this.attrSelected = attrSelected;
    }

    public String getAttrMultiple() {
        return attrMultiple;
    }

    public void setAttrMultiple(String attrMultiple) {
        this.attrMultiple = attrMultiple;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public String getValueCheckbox() {
        return valueCheckbox;
    }

    public void setValueCheckbox(String valueCheckbox) {
        this.valueCheckbox = valueCheckbox;
    }

    public String getValueRadio() {
        return valueRadio;
    }

    public void setValueRadio(String valueRadio) {
        this.valueRadio = valueRadio;
    }

    public String getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(String valueNumber) {
        this.valueNumber = valueNumber;
    }

    public String getValueRange() {
        return valueRange;
    }

    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }

    public String getValueSubmit() {
        return valueSubmit;
    }

    public void setValueSubmit(String valueSubmit) {
        this.valueSubmit = valueSubmit;
    }

    public String getAttrNf() {
        return attrNf;
    }

    public void setAttrNf(String attrNf) {
        this.attrNf = attrNf;
    }

    public String getAttrNa() {
        return attrNa;
    }

    public void setAttrNa(String attrNa) {
        this.attrNa = attrNa;
    }

    public String getAttrNi() {
        return attrNi;
    }

    public void setAttrNi(String attrNi) {
        this.attrNi = attrNi;
    }

    public String getAttrParam() {
        return attrParam;
    }

    public void setAttrParam(String attrParam) {
        this.attrParam = attrParam;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrHref() {
        return attrHref;
    }

    public void setAttrHref(String attrHref) {
        this.attrHref = attrHref;
    }

    public String getAttrCommand() {
        return attrCommand;
    }

    public void setAttrCommand(String attrCommand) {
        this.attrCommand = attrCommand;
    }

    public String getAttrAction() {
        return attrAction;
    }

    public void setAttrAction(String attrAction) {
        this.attrAction = attrAction;
    }

    public String getAttrTitle() {
        return attrTitle;
    }

    public void setAttrTitle(String attrTitle) {
        this.attrTitle = attrTitle;
    }

    public String getAttrFor() {
        return attrFor;
    }

    public void setAttrFor(String attrFor) {
        this.attrFor = attrFor;
    }

    public String getAttrMessage() {
        return attrMessage;
    }

    public void setAttrMessage(String attrMessage) {
        this.attrMessage = attrMessage;
    }

    public String getAttrQuoted() {
        return attrQuoted;
    }

    public void setAttrQuoted(String attrQuoted) {
        this.attrQuoted = attrQuoted;
    }

    public String getAttrEscaped() {
        return attrEscaped;
    }

    public void setAttrEscaped(String attrEscaped) {
        this.attrEscaped = attrEscaped;
    }

    public String getAttrEscapedAmp() {
        return attrEscapedAmp;
    }

    public void setAttrEscapedAmp(String attrEscapedAmp) {
        this.attrEscapedAmp = attrEscapedAmp;
    }

    public String getAttrConvert() {
        return attrConvert;
    }

    public void setAttrConvert(String attrConvert) {
        this.attrConvert = attrConvert;
    }

    public String getAttrConvertValue() {
        return attrConvertValue;
    }

    public void setAttrConvertValue(String attrConvertValue) {
        this.attrConvertValue = attrConvertValue;
    }

    public String getAttrConvertField() {
        return attrConvertField;
    }

    public void setAttrConvertField(String attrConvertField) {
        this.attrConvertField = attrConvertField;
    }

    public String getAttrConvertFieldValue() {
        return attrConvertFieldValue;
    }

    public void setAttrConvertFieldValue(String attrConvertFieldValue) {
        this.attrConvertFieldValue = attrConvertFieldValue;
    }

    public String getAttrDefault() {
        return attrDefault;
    }

    public void setAttrDefault(String attrDefault) {
        this.attrDefault = attrDefault;
    }

    public String getAttrVarValue() {
        return attrVarValue;
    }

    public void setAttrVarValue(String attrVarValue) {
        this.attrVarValue = attrVarValue;
    }

    public String getAttrPrepare() {
        return attrPrepare;
    }

    public void setAttrPrepare(String attrPrepare) {
        this.attrPrepare = attrPrepare;
    }

    public String getAttrForm() {
        return attrForm;
    }

    public void setAttrForm(String attrForm) {
        this.attrForm = attrForm;
    }

    public String getAttrValueMessage() {
        return attrValueMessage;
    }

    public void setAttrValueMessage(String attrValueMessage) {
        this.attrValueMessage = attrValueMessage;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrGroupId() {
        return attrGroupId;
    }

    public void setAttrGroupId(String attrGroupId) {
        this.attrGroupId = attrGroupId;
    }

    public String getAttrValidator() {
        return attrValidator;
    }

    public void setAttrValidator(String attrValidator) {
        this.attrValidator = attrValidator;
    }

    public String getAttrPage() {
        return attrPage;
    }

    public void setAttrPage(String attrPage) {
        this.attrPage = attrPage;
    }

    public String getAttrKeepFields() {
        return attrKeepFields;
    }

    public void setAttrKeepFields(String attrKeepFields) {
        this.attrKeepFields = attrKeepFields;
    }

    public String getAttrSrc() {
        return attrSrc;
    }

    public void setAttrSrc(String attrSrc) {
        this.attrSrc = attrSrc;
    }

    public String getAttrRows() {
        return attrRows;
    }

    public void setAttrRows(String attrRows) {
        this.attrRows = attrRows;
    }

    public String getAttrCols() {
        return attrCols;
    }

    public void setAttrCols(String attrCols) {
        this.attrCols = attrCols;
    }

    public String getAttrClass() {
        return attrClass;
    }

    public void setAttrClass(String attrClass) {
        this.attrClass = attrClass;
    }

    public String getAttrWidth() {
        return attrWidth;
    }

    public void setAttrWidth(String attrWidth) {
        this.attrWidth = attrWidth;
    }

    public String getAttrDelay() {
        return attrDelay;
    }

    public void setAttrDelay(String attrDelay) {
        this.attrDelay = attrDelay;
    }

    public String getAttrRel() {
        return attrRel;
    }

    public void setAttrRel(String attrRel) {
        this.attrRel = attrRel;
    }

    public String getValueStyle() {
        return valueStyle;
    }

    public void setValueStyle(String valueStyle) {
        this.valueStyle = valueStyle;
    }

    public String getValueLiNb() {
        return valueLiNb;
    }

    public void setValueLiNb(String valueLiNb) {
        this.valueLiNb = valueLiNb;
    }

    public String getValueLiMinLet() {
        return valueLiMinLet;
    }

    public void setValueLiMinLet(String valueLiMinLet) {
        this.valueLiMinLet = valueLiMinLet;
    }

    public String getValueLiMajLet() {
        return valueLiMajLet;
    }

    public void setValueLiMajLet(String valueLiMajLet) {
        this.valueLiMajLet = valueLiMajLet;
    }

    public String getValueLiMinLat() {
        return valueLiMinLat;
    }

    public void setValueLiMinLat(String valueLiMinLat) {
        this.valueLiMinLat = valueLiMinLat;
    }

    public String getValueLiMajLat() {
        return valueLiMajLat;
    }

    public void setValueLiMajLat(String valueLiMajLat) {
        this.valueLiMajLat = valueLiMajLat;
    }

    public String getValueLiCircle() {
        return valueLiCircle;
    }

    public void setValueLiCircle(String valueLiCircle) {
        this.valueLiCircle = valueLiCircle;
    }

    public String getValueLiDisk() {
        return valueLiDisk;
    }

    public void setValueLiDisk(String valueLiDisk) {
        this.valueLiDisk = valueLiDisk;
    }

    public String getValueLiRect() {
        return valueLiRect;
    }

    public void setValueLiRect(String valueLiRect) {
        this.valueLiRect = valueLiRect;
    }

    public String getValueLiSquare() {
        return valueLiSquare;
    }

    public void setValueLiSquare(String valueLiSquare) {
        this.valueLiSquare = valueLiSquare;
    }

    public String getStyleAttrFontFam() {
        return styleAttrFontFam;
    }

    public void setStyleAttrFontFam(String styleAttrFontFam) {
        this.styleAttrFontFam = styleAttrFontFam;
    }

    public String getStyleAttrFontSize() {
        return styleAttrFontSize;
    }

    public void setStyleAttrFontSize(String styleAttrFontSize) {
        this.styleAttrFontSize = styleAttrFontSize;
    }

    public String getStyleAttrColor() {
        return styleAttrColor;
    }

    public void setStyleAttrColor(String styleAttrColor) {
        this.styleAttrColor = styleAttrColor;
    }

    public String getStyleAttrBackground() {
        return styleAttrBackground;
    }

    public void setStyleAttrBackground(String styleAttrBackground) {
        this.styleAttrBackground = styleAttrBackground;
    }

    public String getStyleAttrBorder() {
        return styleAttrBorder;
    }

    public void setStyleAttrBorder(String styleAttrBorder) {
        this.styleAttrBorder = styleAttrBorder;
    }

    public String getStyleValueRgb() {
        return styleValueRgb;
    }

    public void setStyleValueRgb(String styleValueRgb) {
        this.styleValueRgb = styleValueRgb;
    }

    public String getStyleValueRed() {
        return styleValueRed;
    }

    public void setStyleValueRed(String styleValueRed) {
        this.styleValueRed = styleValueRed;
    }

    public String getStyleValueGreen() {
        return styleValueGreen;
    }

    public void setStyleValueGreen(String styleValueGreen) {
        this.styleValueGreen = styleValueGreen;
    }

    public String getStyleValueBlue() {
        return styleValueBlue;
    }

    public void setStyleValueBlue(String styleValueBlue) {
        this.styleValueBlue = styleValueBlue;
    }

    public String getStyleValueYellow() {
        return styleValueYellow;
    }

    public void setStyleValueYellow(String styleValueYellow) {
        this.styleValueYellow = styleValueYellow;
    }

    public String getStyleValueCyan() {
        return styleValueCyan;
    }

    public void setStyleValueCyan(String styleValueCyan) {
        this.styleValueCyan = styleValueCyan;
    }

    public String getStyleValueMagenta() {
        return styleValueMagenta;
    }

    public void setStyleValueMagenta(String styleValueMagenta) {
        this.styleValueMagenta = styleValueMagenta;
    }

    public String getStyleValueBlack() {
        return styleValueBlack;
    }

    public void setStyleValueBlack(String styleValueBlack) {
        this.styleValueBlack = styleValueBlack;
    }

    public String getStyleValueGrey() {
        return styleValueGrey;
    }

    public void setStyleValueGrey(String styleValueGrey) {
        this.styleValueGrey = styleValueGrey;
    }

    public String getStyleValueWhite() {
        return styleValueWhite;
    }

    public void setStyleValueWhite(String styleValueWhite) {
        this.styleValueWhite = styleValueWhite;
    }

    public String getStyleUnitPx() {
        return styleUnitPx;
    }

    public void setStyleUnitPx(String styleUnitPx) {
        this.styleUnitPx = styleUnitPx;
    }

    public String getStyleUnitEm() {
        return styleUnitEm;
    }

    public void setStyleUnitEm(String styleUnitEm) {
        this.styleUnitEm = styleUnitEm;
    }

    public String getStyleUnitSolid() {
        return styleUnitSolid;
    }

    public void setStyleUnitSolid(String styleUnitSolid) {
        this.styleUnitSolid = styleUnitSolid;
    }
}
