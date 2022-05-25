package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.formathtml.analyze.syntax.RendSplitExpressionUtil;
import code.formathtml.common.AdvFileEscapedCalc;
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

    private final StringMap<CustList<SegmentStringPart>> stringPartsElt = new StringMap<CustList<SegmentStringPart>>();
    private final CustList<SegmentStringPart> stringPartsText = new CustList<SegmentStringPart>();

    AnaRendBlock(int _offset) {
        offset = _offset;
    }

    public static AnaRendDocumentBlock newRendDocumentBlock(Document _doc, String _docText, AnalyzedPageEl _primTypes, AnalyzingDoc _anaDoc, AdvFileEscapedCalc _es, FileBlock _fileBl) {
        RendKeyWords rend_ = _anaDoc.getRendKeyWords();
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        AnaRendDocumentBlock out_ = new AnaRendDocumentBlock(documentElement_, 0, _es, _fileBl);
        int indexGlobal_ = indexOfBeginNode(curNode_, _docText, 0);
        AnaRendBlock curWrite_ = newRendBlockEsc(indexGlobal_,out_, _anaDoc.getPrefix(), curNode_,_docText, _primTypes, rend_);
        out_.appendChild(curWrite_);
        indexGlobal_ = curWrite_.endHeader;
        while (curWrite_ != null) {
            Node firstChild_ = curNode_.getFirstChild();
            if (curWrite_ instanceof AnaRendParentBlock&&firstChild_ != null) {
                indexGlobal_ = indexOfBeginNode(firstChild_, _docText, indexGlobal_);
                AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,(AnaRendParentBlock) curWrite_, _anaDoc.getPrefix(), firstChild_,_docText, _primTypes, rend_);
                appendChild((AnaRendParentBlock) curWrite_,rendBlock_);
                indexGlobal_ = rendBlock_.endHeader;
                curWrite_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            tryAppendEmptyBlock(curWrite_);
            while (curNode_ != null) {
                Node nextSibling_ = curNode_.getNextSibling();
                AnaRendParentBlock par_ = curWrite_.getParent();
                if (nextSibling_ != null) {
                    indexGlobal_ = indexOfBeginNode(nextSibling_, _docText, indexGlobal_);
                    AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,par_, _anaDoc.getPrefix(), nextSibling_,_docText, _primTypes, rend_);
                    appendChild(par_,rendBlock_);
                    indexGlobal_ = rendBlock_.endHeader;
                    curWrite_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = curNode_.getParentNode();
                if (parentNode_ == null || parentNode_ == documentElement_) {
                    curWrite_ = null;
                    curNode_ = null;
                } else {
                    indexGlobal_ = _docText.indexOf(LT_BEGIN_TAG, indexGlobal_) + 2;
                    curWrite_ = par_;
                    curNode_ = parentNode_;
                }
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

    public static void adjustMap(StringMap<StringMap<String>> _mes) {
        for (StringMap<String> m: _mes.values()) {
            adjust(m);
        }
    }
    public static void adjust(StringMap<String> _mes) {
        for (EntryCust<String,String> e: _mes.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(),true,true));
        }
    }

    private static AnaRendBlock newRendBlockEsc(int _begin, AnaRendParentBlock _curParent, String _prefix, Node _elt, String _docText, AnalyzedPageEl _primTypes, RendKeyWords _rendKeyWords) {
        AnaRendBlock bl_ = newRendBlock(_begin, _curParent, _prefix, _elt, _docText, _primTypes.getPrimTypes(), _rendKeyWords);
        if (_elt instanceof Element) {
            Element elt_ = (Element) _elt;
            String tagName_ = elt_.getTagName();
            int endHeader_ = _docText.indexOf(GT_TAG, _begin);
            int beginHeader_ = _begin + tagName_.length();
            StringMap<AttributePart> attr_;
            attr_ = getAttributes(_docText, beginHeader_, endHeader_);
            bl_.attributeDelimiters = attr_;
            bl_.endHeader = endHeader_;
            int len_ = attr_.size();
            for (int i = 0; i < len_; i++) {
                AttributePart dels_ = attr_.getValue(i);
                String attrValue_ = ((Element) _elt).getAttribute(attr_.getKey(i));
                StringComment str_ = new StringComment(attrValue_, _primTypes.getComments(),dels_.getBegin());
//                bl_.stringParts.addAllElts(str_.getStringParts());
                bl_.stringPartsElt.addEntry(attr_.getKey(i),str_.getStringParts());
            }
            if (!StringExpUtil.nextCharIs(_docText,endHeader_-1,_docText.length(),'/') &&_docText.startsWith("></"+tagName_+">",endHeader_)) {
                bl_.endHeader += ("</"+tagName_+">").length();
            }
        } else {
//            bl_.stringParts.addAllElts(RendSplitExpressionUtil.itText(_begin,_elt.getTextContent(),_primTypes).getStringParts());
            bl_.stringPartsText.addAllElts(RendSplitExpressionUtil.itText(_begin,_elt.getTextContent(),_primTypes).getStringParts());
            bl_.endHeader = _docText.indexOf(LT_BEGIN_TAG, _begin);
        }
        return bl_;
    }

    public CustList<SegmentStringPart> getStringPartsText() {
        return stringPartsText;
    }

    public CustList<SegmentStringPart> getStringPartsElt(String _attr) {
        CustList<SegmentStringPart> val_ = stringPartsElt.getVal(_attr);
        if (val_ == null) {
            return new CustList<SegmentStringPart>();
        }
        return val_;
    }

    public int getEndHeader() {
        return endHeader;
    }

    private static AnaRendBlock newRendBlock(int _begin, AnaRendParentBlock _curParent, String _prefix, Node _elt, String _docText, PrimitiveTypes _primTypes, RendKeyWords _rendKeyWords) {
        if (_elt instanceof Text) {
            return txt(_begin, (Text) _elt);
        }
        Element elt_ = (Element) _elt;
        String tagName_ = elt_.getTagName();
        int endHeader_ = _docText.indexOf(GT_TAG, _begin);
        int beginHeader_ = _begin + tagName_.length();
        StringMap<AttributePart> attr_ = getAttributes(_docText, beginHeader_, endHeader_);
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordFor()))) {
            return keyWordFor(_begin, _primTypes, _rendKeyWords, elt_, attr_);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordWhile()))) {
            return keyWordWhile(_begin, _prefix, _rendKeyWords, elt_, attr_);
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
            return line(_begin, _curParent, _rendKeyWords, elt_, attr_);
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
        return stdKeys(_begin, _prefix, _rendKeyWords, elt_, attr_);
    }

    private static AnaRendParentBlock stdKeys(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attr) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordTry()))) {
            return new AnaRendTryEval(newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attr),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordCatch()))) {
            return keyWordCatch(_begin, _rendKeyWords, _elt, _attr);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordFinally()))) {
            return new AnaRendFinallyEval(_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSwitch()))) {
            return new AnaRendSwitchBlock(newOffsetStringInfo(_elt, _rendKeyWords.getAttrValue(), _attr),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attr),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordCase()))) {
            return new AnaRendCaseCondition(newOffsetStringInfo(_elt, _rendKeyWords.getAttrClassName(), _attr),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrVar(), _attr),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrValue(), _attr),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordDefault()))) {
            return new AnaRendDefaultCondition(newOffsetStringInfo(_elt, _rendKeyWords.getAttrVar(), _attr),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImport()))) {
            return new AnaRendImport(_elt, newOffsetStringInfo(_elt, _rendKeyWords.getAttrPage(), _attr), _begin);
        }
        return std(_begin, _prefix, _rendKeyWords, _elt, _attr);
    }

    private static AnaRendAbstractCatchEval keyWordCatch(int _begin, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attr) {
        if (_elt.hasAttribute(_rendKeyWords.getAttrClassName())) {
            return new AnaRendCatchEval(newOffsetStringInfo(_elt, _rendKeyWords.getAttrClassName(), _attr),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrVar(), _attr),
                    _begin);
        }
        return new AnaRendNullCatchEval(_begin);
    }

    private static AnaRendLine line(int _begin, AnaRendParentBlock _curParent, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attr) {
        if (_elt.hasAttribute(_rendKeyWords.getAttrClassName())) {
            _curParent.appendChild(new AnaRendDeclareVariable(newOffsetBooleanInfo(_elt, _rendKeyWords.getAttrHref()),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrClassName(), _attr),
                    _begin));
        }
        return new AnaRendLine(newOffsetStringInfo(_elt, _rendKeyWords.getAttrValue(), _attr),
                _begin);
    }

    private static AnaRendParentBlock keyWordFor(int _begin, PrimitiveTypes _primTypes, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attr) {
        if (_elt.hasAttribute(_rendKeyWords.getAttrList())) {
            OffsetStringInfo lab_ = newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attr);
            OffsetStringInfo clNa_ = FileResolver.className(newOffsetStringInfo(_elt, _rendKeyWords.getAttrIndexClassName(), _attr),
                    _primTypes);
            return new AnaRendForEachLoop(
                    newOffsetBooleanInfo(_elt, _rendKeyWords.getAttrHref()),
                    new ListLoopExpressionContent(new OffsetClassVariableInfo(newOffsetStringInfo(_elt, _rendKeyWords.getAttrClassName(), _attr),
                            newOffsetStringInfo(_elt, _rendKeyWords.getAttrVar(), _attr)),
                            newOffsetStringInfo(_elt, _rendKeyWords.getAttrList(), _attr),
                            clNa_),
                    lab_,
                    _begin
            );
        }
        if (_elt.hasAttribute(_rendKeyWords.getAttrMap())) {
            OffsetStringInfo lab_ = newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attr);
            OffsetStringInfo clNa_ = FileResolver.className(newOffsetStringInfo(_elt, _rendKeyWords.getAttrIndexClassName(), _attr),
                    _primTypes);
            return new AnaRendForEachTable(
                    new TableLoopExpressionContent(
                            new OffsetClassVariableInfo(newOffsetStringInfo(_elt, _rendKeyWords.getAttrKeyClassName(), _attr),
                                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrKey(), _attr)),
                            new OffsetClassVariableInfo(newOffsetStringInfo(_elt, _rendKeyWords.getAttrVarClassName(), _attr),
                                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrValue(), _attr)),
                            newOffsetStringInfo(_elt, _rendKeyWords.getAttrMap(), _attr),
                            clNa_),
                    lab_,
                    _begin
            );
        }
        if (_elt.hasAttribute(_rendKeyWords.getAttrVar())) {
            OffsetStringInfo lab_ = newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attr);
            OffsetStringInfo clNa_ = FileResolver.className(newOffsetStringInfo(_elt, _rendKeyWords.getAttrIndexClassName(), _attr),
                    _primTypes);
            return new AnaRendForIterativeLoop(
                    new OneLoopExpressionsContent(new OffsetClassVariableInfo(newOffsetStringInfo(_elt, _rendKeyWords.getAttrClassName(), _attr),
                            newOffsetStringInfo(_elt, _rendKeyWords.getAttrVar(), _attr)),
                            newOffsetStringInfo(_elt, _rendKeyWords.getAttrFrom(), _attr),
                            newOffsetStringInfo(_elt, _rendKeyWords.getAttrTo(), _attr),
                            newOffsetStringInfo(_elt, _rendKeyWords.getAttrStep(), _attr), newOffsetBooleanInfo(_elt, _rendKeyWords.getAttrEq()),
                            clNa_),
                    lab_,
                    _begin
            );
        }
        OffsetStringInfo lab_ = newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attr);
        OffsetStringInfo bounds_ = FileResolver.className(newOffsetStringInfo(_elt, _rendKeyWords.getAttrIndexClassName(), _attr)
                , _primTypes);
        return new AnaRendForMutableIterativeLoop(
                new ManyLoopExpressionsContent(new OffsetFinalInfo(new OffsetBooleanInfo(0, false), newOffsetBooleanInfo(_elt, _rendKeyWords.getAttrHref()).isInfo()), newOffsetStringInfo(_elt, _rendKeyWords.getAttrClassName(), _attr),
                        newOffsetStringInfo(_elt, _rendKeyWords.getAttrInit(), _attr),
                        newOffsetStringInfo(_elt, _rendKeyWords.getAttrCondition(), _attr),
                        newOffsetStringInfo(_elt, _rendKeyWords.getAttrStep(), _attr), bounds_), lab_
                ,
                _begin);
    }

    private static AnaRendCondition keyWordWhile(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attr) {
        Node previousSibling_ = _elt.getPreviousSibling();
        if (previousSibling_ instanceof Text && previousSibling_.getTextContent().trim().isEmpty()) {
            previousSibling_ = previousSibling_.getPreviousSibling();
        }
        if (previousSibling_ instanceof Element
                && StringUtil.quickEq(((Element) previousSibling_).getTagName(), StringUtil.concat(_prefix, _rendKeyWords.getKeyWordDo()))) {
            return new AnaRendDoWhileCondition(newOffsetStringInfo(_elt, _rendKeyWords.getAttrCondition(), _attr),
                    _begin);
        }
        return new AnaRendWhileCondition(newOffsetStringInfo(_elt, _rendKeyWords.getAttrCondition(), _attr),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attr), _begin);
    }

    private static AnaRendParentBlock std(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attr) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSubmit()))) {
            return new AnaRendSubmit(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordAnchor())) {
            return new AnaRendAnchor(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordImg())) {
            return new AnaRendImg(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordLink())) {
            return new AnaRendLink(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordStyle())) {
            return new AnaRendStyle(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImg()))) {
            return new AnaRendEscImg(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordPackage()))) {
            return new AnaRendPackage(newOffsetStringInfo(_elt, _rendKeyWords.getAttrName(), _attr),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordForm())) {
            return new AnaRendForm(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordForm()))) {
            return new AnaRendImportForm(newOffsetStringInfo(_elt, _rendKeyWords.getAttrForm(), _attr),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordClass()))) {
            return new AnaRendClass(newOffsetStringInfo(_elt, _rendKeyWords.getAttrName(), _attr),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordField()))) {
            return new AnaRendField(newOffsetStringInfo(_elt, _rendKeyWords.getAttrPrepare(), _attr),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordMessage()))) {
            return new AnaRendMessage(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSelect()))) {
            return new AnaRendSelect(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordInput())) {
            return input(_begin, _rendKeyWords, _elt);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordTextarea())) {
            return new AnaRendTextArea(_elt,_begin);
        }
        return elt(_begin, _prefix, _rendKeyWords, _elt);
    }

    private static AnaRendElement elt(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt) {
        String tagName_ = _elt.getTagName();
        if (isSpan(_prefix, _rendKeyWords, _elt)) {
            return new AnaRendSpan(_elt, _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordAnchor()))) {
            return new AnaRendTitledAnchor(_elt, _begin);
        }
        return new AnaRendStdElement(_elt, _begin);
    }

    private static boolean isSpan(String _prefix, RendKeyWords _rendKeyWords, Element _elt) {
        String tagName_ = _elt.getTagName();
        return StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordSpan()) && !_elt.getAttribute(StringUtil.concat(_prefix, _rendKeyWords.getAttrFor())).isEmpty();
    }

    private static AnaRendInput input(int _begin, RendKeyWords _rendKeyWords, Element _elt) {
        if (StringUtil.quickEq(_elt.getAttribute(_rendKeyWords.getAttrType()), _rendKeyWords.getValueRadio())) {
            return new AnaRendRadio(_elt, _begin);
        }
        return new AnaRendStdInput(_elt, _begin);
    }

    private static AnaRendLeaf txt(int _begin, Text _elt) {
        if (_elt.getTextContent().trim().isEmpty()) {
            return new AnaRendEmptyText(new OffsetStringInfo(_begin, _elt.getTextContent()), _begin);
        }
        return new AnaRendText(new OffsetStringInfo(_begin, _elt.getTextContent()), _begin);
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

    public static StringMap<String> getPreQuick(String _value, AnalyzingDoc _analyzingDoc) {
        StringList elts_ = StringUtil.splitStrings(_value, COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(var_, _analyzingDoc);
        if (fileName_ == null) {
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
                return new StringMap<String>();
            }
            StringMap<String> messages_ = getMessages(cont_);
            String key_ = elts_.last();
            String format_ = getQuickFormat(messages_, key_);
            if (format_ == null) {
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

    public static String toSgn(ClassMethodIdReturn _res, AnalyzedPageEl _page) {
        AnaCallFctContent fctContent_ = new AnaCallFctContent("");
        if (_res == null) {
            return _page.getAliasObject();
        }
        fctContent_.update(_res);
        ClassMethodId id_ = fctContent_.getClassMethodId();
        return StringExpUtil.getIdFromAllTypes(id_.getClassName())+"."+id_.getConstraints().getSignature(_page.getDisplayedStrings());
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
        return getAttributeDelimiter(getAttributeDelimiters(),getOffset(),_type);
    }
    public static int getAttributeDelimiter(StringMap<AttributePart> _attrs, int _offset,String _type) {
        AttributePart del_ = _attrs.getVal(_type);
        if (del_ == null) {
            return _offset;
        }
        return del_.getBegin();
    }
    public StringMap<AttributePart> getAttributeDelimiters() {
        return attributeDelimiters;
    }

}
