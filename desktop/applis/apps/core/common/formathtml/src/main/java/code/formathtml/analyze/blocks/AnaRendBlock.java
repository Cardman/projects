package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzedBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.formathtml.*;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.*;
import code.sml.util.ResourcesMessagesUtil;
import code.util.*;

public abstract class AnaRendBlock implements AnalyzedBlock {
    static final String CALL_METHOD = "$";
    public static final String COMMA = ",";
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

    public static final String AND_ERR = "&";
    static final String OR_ERR = "|";
    public static final String LEFT_PAR = "(";
    public static final String RIGHT_PAR = ")";
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

    private OffsetsBlock offset;

    private StringMap<IntTreeMap<Integer>> escapedChars = new StringMap<IntTreeMap<Integer>>();
    private StringMap<AttributePart> attributeDelimiters = new StringMap<AttributePart>();

    AnaRendBlock(OffsetsBlock _offset) {
        offset = _offset;
    }

    public static AnaRendDocumentBlock newRendDocumentBlock(Configuration _conf, String _prefix, Document _doc, String _docText, PrimitiveTypes _primTypes) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        int indexGlobal_ = _docText.indexOf(LT_BEGIN_TAG)+1;
        AnaRendDocumentBlock out_ = new AnaRendDocumentBlock(documentElement_,_docText,new OffsetsBlock(),_conf.getCurrentUrl());
        AnaRendBlock curWrite_ = newRendBlockEsc(indexGlobal_,out_, _conf, _prefix, curNode_,_docText, _primTypes);
        out_.appendChild(curWrite_);
        while (curWrite_ != null) {
            MutableNode firstChild_ = curNode_.getFirstChild();
            if (firstChild_ != null) {
                indexGlobal_ = indexOfBeginNode(firstChild_, _docText, indexGlobal_);
                AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,(AnaRendParentBlock) curWrite_, _conf, _prefix, firstChild_,_docText, _primTypes);
                ((AnaRendParentBlock) curWrite_).appendChild(rendBlock_);
                curWrite_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            while (true) {
                MutableNode nextSibling_ = curNode_.getNextSibling();
                AnaRendParentBlock par_ = curWrite_.getParent();
                if (nextSibling_ != null) {
                    indexGlobal_ = indexOfBeginNode(nextSibling_, _docText, indexGlobal_);
                    AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,par_, _conf, _prefix, nextSibling_,_docText, _primTypes);
                    par_.appendChild(rendBlock_);
                    curWrite_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = curNode_.getParentNode();
                if (parentNode_ == null) {
                    curWrite_ = null;
                    break;
                }
                if (parentNode_ == documentElement_) {
                    curWrite_ = null;
                    break;
                }
                curWrite_ = par_;
                curNode_ = parentNode_;
            }
        }
        return out_;
    }

    private static AnaRendBlock newRendBlockEsc(int _begin, AnaRendParentBlock _curParent, Configuration _conf, String _prefix, Node _elt, String _docText, PrimitiveTypes _primTypes) {
        AnaRendBlock bl_ = newRendBlock(_begin, _curParent, _conf, _prefix, _elt, _docText, _primTypes);
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
    private static AnaRendBlock newRendBlock(int _begin, AnaRendParentBlock _curParent, Configuration _conf, String _prefix, Node _elt, String _docText, PrimitiveTypes _primTypes) {
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            if (t_.getTextContent().trim().isEmpty()) {
                return new AnaRendEmptyText(new OffsetStringInfo(_begin,t_.getTextContent()),new OffsetsBlock(_begin,_begin));
            }
            return new AnaRendText(new OffsetStringInfo(_begin,t_.getTextContent()),new OffsetsBlock(_begin,_begin));
        }
        Element elt_ = (Element) _elt;
        String tagName_ = elt_.getTagName();
        int endHeader_ = _docText.indexOf(GT_TAG, _begin);
        int beginHeader_ = _begin + tagName_.length();
        StringMap<AttributePart> attr_;
        attr_ = getAttributes(_docText, beginHeader_, endHeader_);
        RendKeyWords rendKeyWords_ = _conf.getRendKeyWords();
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordFor()))) {
            if (elt_.hasAttribute(rendKeyWords_.getAttrList())) {
                return new AnaRendForEachLoop(
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrVar(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrList(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                        new OffsetsBlock(_begin,_begin), _primTypes
                );
            }
            if (elt_.hasAttribute(rendKeyWords_.getAttrMap())) {
                return new AnaRendForEachTable(
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrKeyClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrKey(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrVarClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrMap(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                        new OffsetsBlock(_begin,_begin), _primTypes
                );
            }
            if (elt_.hasAttribute(rendKeyWords_.getAttrVar())) {
                return new AnaRendForIterativeLoop(
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrVar(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrFrom(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrTo(), attr_),
                        newOffsetBooleanInfo(elt_,rendKeyWords_.getAttrEq()),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrStep(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                        new OffsetsBlock(_begin,_begin), _primTypes
                );
            }
            return new AnaRendForMutableIterativeLoop(
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrInit(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrStep(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrIndexClassName(), attr_)
                    ,newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin), _primTypes);
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordWhile()))) {
            MutableNode previousSibling_ = elt_.getPreviousSibling();
            if (previousSibling_ instanceof Text && previousSibling_.getTextContent().trim().isEmpty()) {
                previousSibling_ = previousSibling_.getPreviousSibling();
            }
            if (previousSibling_ instanceof Element
                    && StringList.quickEq(((Element) previousSibling_).getTagName(),StringList.concat(_prefix,rendKeyWords_.getKeyWordDo()))) {
                return new AnaRendDoWhileCondition(newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                        new OffsetsBlock(_begin,_begin));
            }
            return new AnaRendWhileCondition(newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordDo()))) {
            return new AnaRendDoBlock(newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordReturn()))) {
            return new AnaRendReturnMehod(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordBreak()))) {
            return new AnaRendBreakBlock(newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordContinue()))) {
            return new AnaRendContinueBlock(newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordThrow()))) {
            return new AnaRendThrowing(newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordSet()))) {
            if (elt_.hasAttribute(rendKeyWords_.getAttrClassName())) {
                _curParent.appendChild(new AnaRendDeclareVariable(newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                        new OffsetsBlock(_begin,_begin)));
            }
            return new AnaRendLine(newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordIf()))) {
            return new AnaRendIfCondition(newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordElseif()))) {
            return new AnaRendElseIfCondition(newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordElse()))) {
            return new AnaRendElseCondition(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordTry()))) {
            return new AnaRendTryEval(newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordCatch()))) {
            if (elt_.hasAttribute(rendKeyWords_.getAttrClassName())) {
                return new AnaRendCatchEval(newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrVar(), attr_),
                        new OffsetsBlock(_begin,_begin));
            }
            return new AnaRendNullCatchEval(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordFinally()))) {
            return new AnaRendFinallyEval(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordSwitch()))) {
            return new AnaRendSwitchBlock(newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordCase()))) {
            return new AnaRendCaseCondition(newOffsetStringInfo(elt_, rendKeyWords_.getAttrClassName(), attr_),
                    newOffsetStringInfo(elt_, rendKeyWords_.getAttrVar(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordDefault()))) {
            return new AnaRendDefaultCondition(newOffsetStringInfo(elt_, rendKeyWords_.getAttrVar(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordImport()))) {
            return new AnaRendImport(elt_,newOffsetStringInfo(elt_,rendKeyWords_.getAttrPage(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordSubmit()))) {
            return new AnaRendSubmit(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordAnchor())) {
            return new AnaRendAnchor(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordImg())) {
            return new AnaRendImg(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordLink())) {
            return new AnaRendLink(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordStyle())) {
            return new AnaRendStyle(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordImg()))) {
            return new AnaRendEscImg(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordPackage()))) {
            return new AnaRendPackage(newOffsetStringInfo(elt_,rendKeyWords_.getAttrName(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordForm())) {
            return new AnaRendForm(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordForm()))) {
            return new AnaRendImportForm(newOffsetStringInfo(elt_,rendKeyWords_.getAttrForm(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordClass()))) {
            return new AnaRendClass(newOffsetStringInfo(elt_,rendKeyWords_.getAttrName(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordField()))) {
            return new AnaRendField(newOffsetStringInfo(elt_,rendKeyWords_.getAttrPrepare(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordMessage()))) {
            return new AnaRendMessage(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordSelect()))) {
            return new AnaRendSelect(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordInput())) {
            if (StringList.quickEq(elt_.getAttribute(rendKeyWords_.getAttrType()),rendKeyWords_.getValueRadio())) {
                return new AnaRendRadio(elt_,new OffsetsBlock(_begin,_begin));
            }
            return new AnaRendStdInput(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordTextarea())) {
            return new AnaRendTextArea(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordSpan())) {
            if (!elt_.getAttribute(StringList.concat(_conf.getPrefix(),rendKeyWords_.getAttrFor())).isEmpty()) {
                return new AnaRendSpan(elt_,new OffsetsBlock(_begin,_begin));
            }
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordAnchor()))) {
            return new AnaRendTitledAnchor(elt_,new OffsetsBlock(_begin,_begin));
        }
        return new AnaRendStdElement(elt_,new OffsetsBlock(_begin,_begin));
    }
    static String inferOrObject(String _type, AnalyzedPageEl _page) {
        String t_ = _type;
        if (StringList.quickEq(_type, _page.getKeyWords().getKeyWordVar())) {
            t_ = _page.getAliasObject();
        }
        return t_;
    }
    static String getCssHref(Configuration _cont,Element _link) {
        if (!StringList.quickEq(_link.getAttribute(_cont.getRendKeyWords().getAttrRel()),_cont.getRendKeyWords().getValueStyle())) {
            return null;
        }
        if (!_link.hasAttribute(_cont.getRendKeyWords().getAttrHref())){
            return null;
        }
        return _link.getAttribute(_cont.getRendKeyWords().getAttrHref());
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
        StringMap<AttributePart> attributes_;
        attributes_ = new StringMap<AttributePart>();
        StringBuilder str_ = new StringBuilder();
        int beginToken_ = _from;
        int delimiter_ = -1;
        for (int i = _from; i < _to; i++) {
            char ch_ = _html.charAt(i);
            if (delimiter_ == -1) {
                if (ch_ == APOS) {
                    delimiter_ = ch_;
                    beginToken_ = i + 1;
                } else if (ch_ == QUOT) {
                    delimiter_ = ch_;
                    beginToken_ = i + 1;
                }
            } else {
                if (ch_ == delimiter_) {
                    AttributePart attrPart_ = new AttributePart();
                    attrPart_.setBegin(beginToken_);
                    attrPart_.setEnd(i);
                    attributes_.put(str_.toString(), attrPart_);
                    str_ = new StringBuilder();
                    delimiter_ = -1;
                    continue;
                }
            }
            if (delimiter_ == -1) {
                if (Character.isWhitespace(ch_) || ch_ == EQUALS) {
                    continue;
                }
                str_.append(ch_);
            }
        }
        return attributes_;
    }
    private static int indexOfBeginNode(Node _node, String _html, int _from) {
        if (_node instanceof Element) {
            return _html.indexOf(StringList.concat(String.valueOf(LT_BEGIN_TAG),((Element) _node).getTagName()), _from) + 1;
        }
        int indexText_ = _html.indexOf(GT_TAG, _from);
        while (_html.charAt(indexText_ + 1) == LT_BEGIN_TAG) {
            indexText_ = _html.indexOf(GT_TAG, indexText_ + 1);
        }
        return indexText_ + 1;
    }

    static StringMap<String> getPre(Configuration _cont, String _value, int _offset, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        StringList elts_ = StringList.splitStrings(_value, COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(_cont, var_);
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
            StringMap<String> files_ = _cont.getFiles();
            String content_ = tryGetContent(_cont, l, fileName_, files_);
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
                        Integer.toString(index_),
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
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), StringList.concat(quote_,String.valueOf(LEFT_EL),quote_));
        rep_.put(String.valueOf(RIGHT_EL), StringList.concat(quote_,String.valueOf(RIGHT_EL),quote_));
        rep_.put(String.valueOf(QUOTE), StringList.concat(quote_,quote_));
        return StringList.replaceMultiple(_arg, rep_);
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

    static void removeUseLess(Configuration _cont,Element _read, StringList _list) {
        int i_ = CustList.FIRST_INDEX;
        while (_read.hasAttribute(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            _list.removeAllString(StringList.concat(_cont.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            i_++;
        }
    }

    public static String getQuickFormat(StringMap<String> _messages, String _key) {
        return _messages.getVal(_key);
    }

    public static String tryGetContent(Configuration _conf, String _loc, String _relative, StringMap<String> _files) {
        String folder_ = _conf.getMessagesFolder();
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,_loc,_relative);
        return getContentFile(_files, fileName_);
    }

    public static int indexCorrectMessages(String _content) {
        if (_content == null) {
            return 0;
        }
        int line_ = CustList.FIRST_INDEX;
        for (String l: StringList.splitStrings(_content, BEFORE_LINE_RETURN, LINE_RETURN)) {
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
        for (String l: StringList.splitStrings(_content, BEFORE_LINE_RETURN, LINE_RETURN)) {
            if (l.isEmpty()) {
                continue;
            }
            if (l.startsWith(TAB)) {
                String text_ = messages_.getVal(lastKey_);
                if (text_ != null) {
                    text_ = StringList.concat(text_,l.substring(1));
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
            if (StringList.quickEq(e.getKey(),_fileName)) {
                content_ = e.getValue();
                break;
            }
        }
        return content_;
    }

    public abstract void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page);
    private static OffsetBooleanInfo newOffsetBooleanInfo(Element _elt, String _key) {
        return new OffsetBooleanInfo(0,_elt.hasAttribute(_key));
    }
    protected static void tryBuildExpressionLanguage(AnaRendBlock _block, Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page) {
        _block.buildExpressionLanguage(_cont,_doc, _analyzingDoc, _page);
    }

    protected void setEscapedChars(StringMap<IntTreeMap<Integer>> _escapedChars) {
        escapedChars = _escapedChars;
    }
    protected static String getProperty(Configuration _conf, String _key) {
        return _conf.getProperties().getVal(_key);
    }
    public StringMap<IntTreeMap<Integer>> getEscapedChars() {
        return escapedChars;
    }
    public final OffsetsBlock getOffset() {
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

    public void setParent(AnaRendParentBlock parent) {
        this.parent = parent;
    }

    public static String lookForVar(StringList _varNames, AnalyzedPageEl _page) {
        String varLoc_ = TMP_LOC;
        int indexLoc_ = 0;
        while (!ContextUtil.isNotVar(varLoc_, _page) || StringList.contains(_varNames,varLoc_)) {
            varLoc_ = StringList.concatNbs(TMP_LOC,indexLoc_);
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
    public int getAttributeDelimiter(String _type) {
        AttributePart del_ = getAttributeDelimiters().getVal(_type);
        if (del_ == null) {
            return getOffset().getOffsetTrim();
        }
        return del_.getBegin();
    }
    public StringMap<AttributePart> getAttributeDelimiters() {
        return attributeDelimiters;
    }

}
