package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.*;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class AnaRendBlock {
    public static final String COMMA = ",";
    public static final String AND_ERR = "&";
    public static final String LEFT_PAR = "(";
    public static final String RIGHT_PAR = ")";
    static final String CALL_METHOD = "$";
    static final String EMPTY_STRING = "";
    static final char RIGHT_EL = '}';
    static final char LEFT_EL = '{';
    static final char QUOTE = 39;
    static final String TMP_BLOCK_TAG = "tmp";
    static final String LT_END_TAG = "</";
    static final char GT_TAG = '>';
    static final char LT_BEGIN_TAG = '<';

    static final String DOT = ".";
    static final String TMP_LOC = "tmpLoc";

    static final String OR_ERR = "|";
    static final String ZERO = "0";
    static final String STR = "\"";

    private static final char END_ESCAPED = ';';
    private static final char ENCODED = '&';
    private static final char EQUALS = '=';
    private static final String LINE_RETURN = "\n";
    private static final String TAB = "\t";
    private static final String BEFORE_LINE_RETURN = "\r\n";
    private AnaRendParentBlock parent;

    private AnaRendBlock nextSibling;

    private AnaRendBlock previousSibling;

    private final int offset;
    private int endHeader;

    private StringMap<AttributePart> attributeDelimiters = new StringMap<AttributePart>();

    AnaRendBlock(int _offset) {
        offset = _offset;
    }

    public static AnaRendDocumentBlock newRendDocumentBlock(String _prefix, Document _doc, String _docText, PrimitiveTypes _primTypes, String _currentUrl, AnalyzingDoc _anaDoc) {
        RendKeyWords rend_ = _anaDoc.getRendKeyWords();
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        AnaRendDocumentBlock out_ = new AnaRendDocumentBlock(documentElement_,_docText,0, _currentUrl,_anaDoc.getEncoded());
        int indexGlobal_ = indexOfBeginNode(curNode_, _docText, 0);
        AnaRendBlock curWrite_ = newRendBlockEsc(indexGlobal_,out_, _prefix, curNode_,_docText, _primTypes, rend_);
        out_.appendChild(curWrite_);
        indexGlobal_ = curWrite_.endHeader;
        while (curWrite_ != null) {
            Node firstChild_ = curNode_.getFirstChild();
            if (curWrite_ instanceof AnaRendParentBlock&&firstChild_ != null) {
                indexGlobal_ = indexOfBeginNode(firstChild_, _docText, indexGlobal_);
                AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,(AnaRendParentBlock) curWrite_, _prefix, firstChild_,_docText, _primTypes, rend_);
                appendChild((AnaRendParentBlock) curWrite_,rendBlock_);
                indexGlobal_ = rendBlock_.endHeader;
                curWrite_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            tryAppendEmptyBlock(curWrite_);
            while (true) {
                Node nextSibling_ = curNode_.getNextSibling();
                AnaRendParentBlock par_ = curWrite_.getParent();
                if (nextSibling_ != null) {
                    indexGlobal_ = indexOfBeginNode(nextSibling_, _docText, indexGlobal_);
                    AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,par_, _prefix, nextSibling_,_docText, _primTypes, rend_);
                    appendChild(par_,rendBlock_);
                    indexGlobal_ = rendBlock_.endHeader;
                    curWrite_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = curNode_.getParentNode();
                if (parentNode_ == null || parentNode_ == documentElement_) {
                    curWrite_ = null;
                    break;
                }
                indexGlobal_ = _docText.indexOf(LT_BEGIN_TAG,indexGlobal_)+2;
                curWrite_ = par_;
                curNode_ = parentNode_;
            }
        }
        return out_;
    }
    private static void appendChild(AnaRendParentBlock _par, AnaRendBlock _child) {
        _par.appendChild(_child);
        if (_child instanceof AnaRendElement&&_par instanceof AnaRendMessage) {
            ((AnaRendMessage)_par).getChildren().add((AnaRendElement) _child);
        }
    }

    private static void tryAppendEmptyBlock(AnaRendBlock _curWrite) {
        if (_curWrite instanceof AnaRendParentBlock) {
            int off_ = _curWrite.getOffset();
            AnaRendEmptyInstruction empty_ = new AnaRendEmptyInstruction(off_);
            ((AnaRendParentBlock) _curWrite).appendChild(empty_);
        }
    }

    private static AnaRendBlock newRendBlockEsc(int _begin, AnaRendParentBlock _curParent, String _prefix, Node _elt, String _docText, PrimitiveTypes _primTypes, RendKeyWords _rendKeyWords) {
        AnaRendBlock bl_ = newRendBlock(_begin, _curParent, _prefix, _elt, _docText, _primTypes, _rendKeyWords);
        if (_elt instanceof Element) {
            Element elt_ = (Element) _elt;
            String tagName_ = elt_.getTagName();
            int endHeader_ = _docText.indexOf(GT_TAG, _begin);
            int beginHeader_ = _begin + tagName_.length();
            StringMap<AttributePart> attr_;
            attr_ = getAttributes(_docText, beginHeader_, endHeader_);
            bl_.attributeDelimiters = attr_;
            bl_.endHeader = endHeader_;
            if (!StringExpUtil.nextCharIs(_docText,endHeader_-1,_docText.length(),'/') &&_docText.startsWith("></"+tagName_+">",endHeader_)) {
                bl_.endHeader += ("</"+tagName_+">").length();
            }
        } else {
            bl_.endHeader = _docText.indexOf(LT_BEGIN_TAG, _begin);
        }
        return bl_;
    }

    public int getEndHeader() {
        return endHeader;
    }

    private static AnaRendBlock newRendBlock(int _begin, AnaRendParentBlock _curParent, String _prefix, Node _elt, String _docText, PrimitiveTypes _primTypes, RendKeyWords _rendKeyWords) {
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            if (t_.getTextContent().trim().isEmpty()) {
                return new AnaRendEmptyText(new OffsetStringInfo(_begin,t_.getTextContent()),_begin);
            }
            return new AnaRendText(new OffsetStringInfo(_begin,t_.getTextContent()),_begin);
        }
        Element elt_ = (Element) _elt;
        String tagName_ = elt_.getTagName();
        int endHeader_ = _docText.indexOf(GT_TAG, _begin);
        int beginHeader_ = _begin + tagName_.length();
        StringMap<AttributePart> attr_;
        attr_ = getAttributes(_docText, beginHeader_, endHeader_);
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordFor()))) {
            if (elt_.hasAttribute(_rendKeyWords.getAttrList())) {
                return new AnaRendForEachLoop(
                        newOffsetBooleanInfo(elt_,_rendKeyWords.getAttrHref()),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrVar(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrList(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                        _begin, _primTypes
                );
            }
            if (elt_.hasAttribute(_rendKeyWords.getAttrMap())) {
                return new AnaRendForEachTable(
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrKeyClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrKey(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrVarClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrValue(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrMap(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                        _begin, _primTypes
                );
            }
            if (elt_.hasAttribute(_rendKeyWords.getAttrVar())) {
                return new AnaRendForIterativeLoop(
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrVar(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrFrom(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrTo(), attr_),
                        newOffsetBooleanInfo(elt_, _rendKeyWords.getAttrEq()),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrStep(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                        _begin, _primTypes
                );
            }
            return new AnaRendForMutableIterativeLoop(
                    newOffsetBooleanInfo(elt_,_rendKeyWords.getAttrHref()),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrClassName(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrInit(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrStep(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrIndexClassName(), attr_)
                    ,newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                    _begin, _primTypes);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordWhile()))) {
            Node previousSibling_ = elt_.getPreviousSibling();
            if (previousSibling_ instanceof Text && previousSibling_.getTextContent().trim().isEmpty()) {
                previousSibling_ = previousSibling_.getPreviousSibling();
            }
            if (previousSibling_ instanceof Element
                    && StringUtil.quickEq(((Element) previousSibling_).getTagName(), StringUtil.concat(_prefix, _rendKeyWords.getKeyWordDo()))) {
                return new AnaRendDoWhileCondition(newOffsetStringInfo(elt_, _rendKeyWords.getAttrCondition(), attr_),
                        _begin);
            }
            return new AnaRendWhileCondition(newOffsetStringInfo(elt_, _rendKeyWords.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordDo()))) {
            return new AnaRendDoBlock(newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordReturn()))) {
            return new AnaRendReturnMehod(_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordBreak()))) {
            return new AnaRendBreakBlock(newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordContinue()))) {
            return new AnaRendContinueBlock(newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordThrow()))) {
            return new AnaRendThrowing(newOffsetStringInfo(elt_, _rendKeyWords.getAttrValue(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSet()))) {
            if (elt_.hasAttribute(_rendKeyWords.getAttrClassName())) {
                _curParent.appendChild(new AnaRendDeclareVariable(newOffsetBooleanInfo(elt_,_rendKeyWords.getAttrHref()),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrClassName(), attr_),
                        _begin));
            }
            return new AnaRendLine(newOffsetStringInfo(elt_, _rendKeyWords.getAttrValue(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordIf()))) {
            return new AnaRendIfCondition(newOffsetStringInfo(elt_, _rendKeyWords.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordElseif()))) {
            return new AnaRendElseIfCondition(newOffsetStringInfo(elt_, _rendKeyWords.getAttrCondition(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordElse()))) {
            return new AnaRendElseCondition(_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordTry()))) {
            return new AnaRendTryEval(newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordCatch()))) {
            if (elt_.hasAttribute(_rendKeyWords.getAttrClassName())) {
                return new AnaRendCatchEval(newOffsetStringInfo(elt_, _rendKeyWords.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrVar(), attr_),
                        _begin);
            }
            return new AnaRendNullCatchEval(_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordFinally()))) {
            return new AnaRendFinallyEval(_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSwitch()))) {
            return new AnaRendSwitchBlock(newOffsetStringInfo(elt_, _rendKeyWords.getAttrValue(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordCase()))) {
            return new AnaRendCaseCondition(newOffsetStringInfo(elt_, _rendKeyWords.getAttrClassName(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrVar(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrValue(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordDefault()))) {
            return new AnaRendDefaultCondition(newOffsetStringInfo(elt_, _rendKeyWords.getAttrVar(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImport()))) {
            return new AnaRendImport(elt_,newOffsetStringInfo(elt_, _rendKeyWords.getAttrPage(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSubmit()))) {
            return new AnaRendSubmit(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordAnchor())) {
            return new AnaRendAnchor(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordImg())) {
            return new AnaRendImg(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordLink())) {
            return new AnaRendLink(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordStyle())) {
            return new AnaRendStyle(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImg()))) {
            return new AnaRendEscImg(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordPackage()))) {
            return new AnaRendPackage(newOffsetStringInfo(elt_, _rendKeyWords.getAttrName(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordForm())) {
            return new AnaRendForm(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordForm()))) {
            return new AnaRendImportForm(newOffsetStringInfo(elt_, _rendKeyWords.getAttrForm(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordClass()))) {
            return new AnaRendClass(newOffsetStringInfo(elt_, _rendKeyWords.getAttrName(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordField()))) {
            return new AnaRendField(newOffsetStringInfo(elt_, _rendKeyWords.getAttrPrepare(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordMessage()))) {
            return new AnaRendMessage(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSelect()))) {
            return new AnaRendSelect(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordInput())) {
            if (StringUtil.quickEq(elt_.getAttribute(_rendKeyWords.getAttrType()), _rendKeyWords.getValueRadio())) {
                return new AnaRendRadio(elt_,_begin);
            }
            return new AnaRendStdInput(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordTextarea())) {
            return new AnaRendTextArea(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordSpan())) {
            if (!elt_.getAttribute(StringUtil.concat(_prefix, _rendKeyWords.getAttrFor())).isEmpty()) {
                return new AnaRendSpan(elt_,_begin);
            }
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordAnchor()))) {
            return new AnaRendTitledAnchor(elt_,_begin);
        }
        return new AnaRendStdElement(elt_,_begin);
    }
    static String inferOrObject(String _type, AnalyzedPageEl _page) {
        String t_ = _type;
        if (StringUtil.quickEq(_type, _page.getKeyWords().getKeyWordVar())) {
            t_ = _page.getAliasObject();
        }
        return t_;
    }
    static String getCssHref(Element _link, RendKeyWords _rendKeyWords) {
        if (!StringUtil.quickEq(_link.getAttribute(_rendKeyWords.getAttrRel()), _rendKeyWords.getValueStyle())) {
            return null;
        }
        if (!_link.hasAttribute(_rendKeyWords.getAttrHref())){
            return null;
        }
        return _link.getAttribute(_rendKeyWords.getAttrHref());
    }
    public static IntTreeMap< Integer> getIndexesSpecChars(String _html, CustList<EncodedChar> _chars) {
        int begin_ = 0;
        int end_ = _html.length();
        int i_ = begin_;
        IntTreeMap< Integer> indexes_;
        indexes_ = new IntTreeMap< Integer>();
        while (i_ < end_) {
            if (_html.charAt(i_) != ENCODED) {
                i_++;
                continue;
            }
            int beginEscaped_ = i_;
            i_++;
            while (i_ < end_&&_html.charAt(i_) != END_ESCAPED) {
                i_++;
            }
            if (i_ < end_) {
                for (EncodedChar e: _chars) {
                    if (addEscaped(_html, i_, beginEscaped_, e)) {
                        indexes_.put(beginEscaped_, i_ - beginEscaped_);
                        break;
                    }
                }
            }
            i_++;
        }
        return indexes_;
    }

    private static boolean addEscaped(String _html, int _i, int _beginEscaped, EncodedChar _e) {
        return _html.charAt(_beginEscaped + 1) == '#' || StringUtil.quickEq(_html.substring(_beginEscaped, _i + 1), _e.getKey());
    }

    private static StringMap<AttributePart> getAttributes(String _html, int _from, int _to) {
        return DocumentAttribute.getAttributes(_html, _from, _to);
    }
    public static int indexOfBeginNode(Node _node, String _html, int _from) {
        if (_node instanceof Element) {
            return _html.indexOf(LT_BEGIN_TAG, _from) + 1;
        }
        return _html.indexOf(GT_TAG, _from) + 1;
    }

    static StringMap<String> getPre(String _value, int _offset, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        StringList elts_ = StringUtil.splitStrings(_value, COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(var_, _analyzingDoc);
        if (fileName_ == null) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_offset);
            badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getInexistantKey(),
                    var_);
            AnalyzingDoc.addError(badEl_, _page);
            return new StringMap<String>();
        }
        StringMap<String> pres_ = new StringMap<String>();
        for (String l: _analyzingDoc.getLanguages()) {
            StringMap<String> files_ = _analyzingDoc.getFiles();
            String content_ = tryGetContent(l, fileName_, files_, _analyzingDoc);
            int index_ = indexCorrectMessages(content_);
            String cont_ = content_;
            if (cont_ == null) {
                cont_ = EMPTY_STRING;
            }
            if (index_ >= 0) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(_offset);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        " ",
                        Long.toString(index_),
                        cont_);
                AnalyzingDoc.addError(badEl_, _page);
                return new StringMap<String>();
            }
            StringMap<String> messages_ = getMessages(cont_);
            String key_ = elts_.last();
            String format_ = getQuickFormat(messages_, key_);
            if (format_ == null) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(_offset);
                badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getInexistantKey(),
                        key_);
                AnalyzingDoc.addError(badEl_, _page);
                return new StringMap<String>();
            }
            pres_.addEntry(l,format_);
        }
        return pres_;
    }
    protected static String escapeParam(String _arg) {
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = Character.toString(QUOTE);
        rep_.put(Character.toString(LEFT_EL), StringUtil.concat(quote_,Character.toString(LEFT_EL),quote_));
        rep_.put(Character.toString(RIGHT_EL), StringUtil.concat(quote_,Character.toString(RIGHT_EL),quote_));
        rep_.put(Character.toString(QUOTE), StringUtil.concat(quote_,quote_));
        return StringUtil.replaceMultiple(_arg, rep_);
    }

    public static OffsetStringInfo newOffsetStringInfo(Element _elt, String _key, StringMap<AttributePart> _attr) {
        AttributePart val_ = _attr.getVal(_key);
        int begin_;
        if (val_ == null) {
            begin_ = 0;
        } else {
            begin_ = val_.getBegin();
        }
        return new OffsetStringInfo(begin_,_elt.getAttribute(_key));
    }

    static void removeUseLess(Element _read, StringList _list, RendKeyWords _rendKeyWords) {
        int i_ = IndexConstants.FIRST_INDEX;
        while (_read.hasAttribute(StringUtil.concat(_rendKeyWords.getAttrParam(),Long.toString(i_)))) {
            _list.removeAllString(StringUtil.concat(_rendKeyWords.getAttrParam(),Long.toString(i_)));
            i_++;
        }
    }

    public static String getQuickFormat(StringMap<String> _messages, String _key) {
        return _messages.getVal(_key);
    }

    public static String tryGetContent(String _loc, String _relative, StringMap<String> _files, AnalyzingDoc _anaDoc) {
        String folder_ = _anaDoc.getMessagesFolder();
        String fileName_ = folder_+'/'+_loc+'/'+_relative;
        return getContentFile(_files, fileName_);
    }

    public static int indexCorrectMessages(String _content) {
        if (_content == null) {
            return 0;
        }
        int line_ = IndexConstants.FIRST_INDEX;
        for (String l: StringUtil.splitStrings(_content, BEFORE_LINE_RETURN, LINE_RETURN)) {
            line_++;
            if (l.isEmpty()) {
                continue;
            }
            if (!l.startsWith(TAB)) {
                int indexSep_ = l.indexOf(EQUALS);
                if (indexSep_ < 0) {
                    return line_;
                }
            }
        }
        return -1;
    }

    public static StringMap<String> getMessages(String _content) {
        String lastKey_ = EMPTY_STRING;
        StringMap<String> messages_ = new StringMap<String>();
        for (String l: StringUtil.splitStrings(_content, BEFORE_LINE_RETURN, LINE_RETURN)) {
            if (l.isEmpty()) {
                continue;
            }
            if (l.startsWith(TAB)) {
                String text_ = messages_.getVal(lastKey_);
                if (text_ != null) {
                    text_ = StringUtil.concat(text_,l.substring(1));
                    messages_.put(lastKey_, text_);
                }
            } else {
                int indexSep_ = l.indexOf(EQUALS);
                lastKey_ = l.substring(0,indexSep_);
                messages_.put(lastKey_, l.substring(indexSep_+1));
            }
        }
        return messages_;
    }

    private static String getContentFile(StringMap<String> _files, String _fileName) {
        String content_ = null;
        for (EntryCust<String, String> e: _files.entryList()) {
            if (StringUtil.quickEq(e.getKey(),_fileName)) {
                content_ = e.getValue();
                break;
            }
        }
        return content_;
    }

    private static OffsetBooleanInfo newOffsetBooleanInfo(Element _elt, String _key) {
        return new OffsetBooleanInfo(0,_elt.hasAttribute(_key));
    }

    protected static String getProperty(String _key, AnalyzingDoc _anaDoc) {
        return _anaDoc.getProperties().getVal(_key);
    }
    public final int getOffset() {
        return offset;
    }

    public final AnaRendBlock getPreviousSibling() {
        return previousSibling;
    }
    public abstract AnaRendBlock getFirstChild();

    public final AnaRendBlock getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(AnaRendBlock _nextSibling) {
        nextSibling = _nextSibling;
    }
    final void setPreviousSibling(AnaRendBlock _previousSibling) {
        previousSibling = _previousSibling;
    }

    public final AnaRendParentBlock getParent() {
        return parent;
    }

    public void setParent(AnaRendParentBlock _parent) {
        this.parent = _parent;
    }

    public static String lookForVar(StringList _varNames, AnalyzedPageEl _page) {
        String varLoc_ = TMP_LOC;
        int indexLoc_ = 0;
        while (!ContextUtil.isNotVar(varLoc_, _page) || StringUtil.contains(_varNames,varLoc_)) {
            varLoc_ = StringUtil.concatNbs(TMP_LOC,indexLoc_);
            indexLoc_++;
        }
        return varLoc_;
    }
    public static void checkVars(int _off,StringList _varNames, OperationNode _root, AnalyzedPageEl _page, AnalyzingDoc _anaDoc) {
        if (!(_root instanceof AbstractCallFctOperation)) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_off);
            badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadDocument(),
                    "");
            AnalyzingDoc.addError(badEl_, _page);
        } else {
            InvokingOperation inv_ = (InvokingOperation) _root;
            for (OperationNode o: inv_.getChildrenNodes()) {
                if (!(o instanceof IdOperation)||!((IdOperation)o).isStandard()||!(o.getFirstChild() instanceof VariableOperationUse)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(_off);
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadDocument(),
                            "");
                    AnalyzingDoc.addError(badEl_, _page);
                } else {
                    VariableOperationUse u_ = (VariableOperationUse) o.getFirstChild();
                    if (!StringUtil.contains(_varNames,u_.getRealVariableName())) {
                        FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                        badEl_.setFile(_page.getCurrentFile());
                        badEl_.setIndexFile(_off);
                        badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadDocument(),
                                "");
                        AnalyzingDoc.addError(badEl_, _page);
                    }
                }
            }
        }
    }

    public static CustList<AnaRendBlock> getDirectChildren(AnaRendBlock _block) {
        CustList<AnaRendBlock> l_ = new CustList<AnaRendBlock>();
        AnaRendBlock child_ = _block.getFirstChild();
        while (child_ != null) {
            l_.add(child_);
            child_ = child_.getNextSibling();
        }
        return l_;
    }
    protected static boolean isPossibleEmpty(AnaRendBlock _bl) {
        return _bl instanceof AnaRendEmptyInstruction || _bl instanceof AnaRendEmptyText;
    }
    public int getAttributeDelimiter(String _type) {
        AttributePart del_ = getAttributeDelimiters().getVal(_type);
        if (del_ == null) {
            return getOffset();
        }
        return del_.getBegin();
    }
    public StringMap<AttributePart> getAttributeDelimiters() {
        return attributeDelimiters;
    }

}
