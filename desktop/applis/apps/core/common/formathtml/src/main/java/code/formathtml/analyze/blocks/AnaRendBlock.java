package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.*;
import code.sml.util.ResourcesMessagesUtil;
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
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private static final String LINE_RETURN = "\n";
    private static final String TAB = "\t";
    private static final String BEFORE_LINE_RETURN = "\r\n";
    private AnaRendParentBlock parent;

    private AnaRendBlock nextSibling;

    private AnaRendBlock previousSibling;

    private final int offset;

    private StringMap<IntTreeMap<Integer>> escapedChars = new StringMap<IntTreeMap<Integer>>();
    private StringMap<AttributePart> attributeDelimiters = new StringMap<AttributePart>();

    AnaRendBlock(int _offset) {
        offset = _offset;
    }

    public static AnaRendDocumentBlock newRendDocumentBlock(String _prefix, Document _doc, String _docText, PrimitiveTypes _primTypes, String _currentUrl, RendKeyWords _rendKeyWords) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        int indexGlobal_ = _docText.indexOf(LT_BEGIN_TAG)+1;
        AnaRendDocumentBlock out_ = new AnaRendDocumentBlock(documentElement_,_docText,0, _currentUrl);
        AnaRendBlock curWrite_ = newRendBlockEsc(indexGlobal_,out_, _prefix, curNode_,_docText, _primTypes, _rendKeyWords);
        out_.appendChild(curWrite_);
        while (curWrite_ != null) {
            Node firstChild_ = curNode_.getFirstChild();
            if (firstChild_ != null) {
                indexGlobal_ = indexOfBeginNode(firstChild_, _docText, indexGlobal_);
                AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,(AnaRendParentBlock) curWrite_, _prefix, firstChild_,_docText, _primTypes, _rendKeyWords);
                ((AnaRendParentBlock) curWrite_).appendChild(rendBlock_);
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
                    AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,par_, _prefix, nextSibling_,_docText, _primTypes, _rendKeyWords);
                    par_.appendChild(rendBlock_);
                    curWrite_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = curNode_.getParentNode();
                if (parentNode_ == null || parentNode_ == documentElement_) {
                    curWrite_ = null;
                    break;
                }
                curWrite_ = par_;
                curNode_ = parentNode_;
            }
        }
        return out_;
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
        if (_elt instanceof Text) {
            int endHeader_ = _docText.indexOf(LT_BEGIN_TAG, _begin);
            AttributePart attrPart_ = new AttributePart();
            attrPart_.setBegin(_begin);
            attrPart_.setEnd(endHeader_);
            IntTreeMap<Integer> esc_ = getIndexesSpecChars(_docText, false, attrPart_, _begin);
            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
            infos_.addEntry(EMPTY_STRING,esc_);
            bl_.escapedChars = infos_;
        } else {
            Element elt_ = (Element) _elt;
            String tagName_ = elt_.getTagName();
            int endHeader_ = _docText.indexOf(GT_TAG, _begin);
            int beginHeader_ = _begin + tagName_.length();
            StringMap<AttributePart> attr_;
            attr_ = getAttributes(_docText, beginHeader_, endHeader_);
            bl_.attributeDelimiters = attr_;
            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
            for (EntryCust<String, AttributePart> e: attr_.entryList()) {
                infos_.put(e.getKey(), getIndexesSpecChars(_docText, true, e.getValue(), _begin));
            }
            bl_.escapedChars = infos_;
        }
        return bl_;
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
    private static IntTreeMap< Integer> getIndexesSpecChars(String _html, boolean _realAttr, AttributePart _att, int _beginNode) {
        int begin_ = _att.getBegin();
        int end_ = _att.getEnd();
        int i_ = begin_;
        int delta_ = 0;
        if (_realAttr) {
            delta_ = begin_ - _beginNode;
        }
        IntTreeMap< Integer> indexes_;
        indexes_ = new IntTreeMap< Integer>();
        while (i_ < end_) {
            if (_html.charAt(i_) == ENCODED) {
                int beginEscaped_ = i_;
                i_++;
                while (i_ < _html.length()&&_html.charAt(i_) != END_ESCAPED) {
                    i_++;
                }
                indexes_.put(beginEscaped_ - _beginNode - delta_, i_ - beginEscaped_);
            }
            i_++;
        }
        return indexes_;
    }
    private static StringMap<AttributePart> getAttributes(String _html, int _from, int _to) {
        return DocumentAttribute.getAttributes(_html, _from, _to);
    }
    private static int indexOfBeginNode(Node _node, String _html, int _from) {
        if (_node instanceof Element) {
            return _html.indexOf(StringUtil.concat(Character.toString(LT_BEGIN_TAG),((Element) _node).getTagName()), _from) + 1;
        }
        int indexText_ = _html.indexOf(GT_TAG, _from);
        while (_html.charAt(indexText_ + 1) == LT_BEGIN_TAG) {
            indexText_ = _html.indexOf(GT_TAG, indexText_ + 1);
        }
        return indexText_ + 1;
    }

    static StringMap<String> getPre(String _value, int _offset, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        StringList elts_ = StringUtil.splitStrings(_value, COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(var_, _analyzingDoc);
        if (fileName_ == null) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_analyzingDoc.getFileName());
            badEl_.setIndexFile(_offset);
            badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getInexistantKey(),
                    var_);
            AnalyzingDoc.addError(badEl_, _analyzingDoc, _page);
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
                badEl_.setFileName(_analyzingDoc.getFileName());
                badEl_.setIndexFile(_offset);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        " ",
                        Long.toString(index_),
                        cont_);
                AnalyzingDoc.addError(badEl_, _analyzingDoc, _page);
                return new StringMap<String>();
            }
            StringMap<String> messages_ = getMessages(cont_);
            String key_ = elts_.last();
            String format_ = getQuickFormat(messages_, key_);
            if (format_ == null) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_analyzingDoc.getFileName());
                badEl_.setIndexFile(_offset);
                badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getInexistantKey(),
                        key_);
                AnalyzingDoc.addError(badEl_, _analyzingDoc, _page);
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

    private static OffsetStringInfo newOffsetStringInfo(Element _elt, String _key, StringMap<AttributePart> _attr) {
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
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,_loc,_relative);
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

    public abstract void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page);
    private static OffsetBooleanInfo newOffsetBooleanInfo(Element _elt, String _key) {
        return new OffsetBooleanInfo(0,_elt.hasAttribute(_key));
    }
    protected static void tryBuildExpressionLanguage(AnaRendBlock _block, AnaRendDocumentBlock _doc, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        _block.buildExpressionLanguage(_doc, _analyzingDoc, _page);
    }

    protected void setEscapedChars(StringMap<IntTreeMap<Integer>> _escapedChars) {
        escapedChars = _escapedChars;
    }
    protected static String getProperty(String _key, AnalyzingDoc _anaDoc) {
        return _anaDoc.getProperties().getVal(_key);
    }
    public StringMap<IntTreeMap<Integer>> getEscapedChars() {
        return escapedChars;
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
