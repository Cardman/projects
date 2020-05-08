package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.EndCallValue;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.AnalyzedBlock;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.exec.*;
import code.formathtml.stacks.RendParentElement;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.*;
import code.sml.*;
import code.util.*;

public abstract class RendBlock implements AnalyzedBlock {
    static final String SPACE = " ";
    static final String RETURN_LINE = "\n";
    static final String CALL_METHOD = "$";
    static final String COMMA = ",";
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

    static final String AND_ERR = "&";
    static final String OR_ERR = "|";
    static final String LEFT_PAR = "(";
    static final String RIGHT_PAR = ")";
    static final String ZERO = "0";
    static final String STR = "\"";

    private static final char END_ESCAPED = ';';
    private static final char ENCODED = '&';
    private static final char EQUALS = '=';
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private RendParentBlock parent;

    private RendBlock nextSibling;

    private RendBlock previousSibling;

    private OffsetsBlock offset;

    private StringMap<IntTreeMap<Integer>> escapedChars = new StringMap<IntTreeMap<Integer>>();
    private StringMap<AttributePart> attributeDelimiters = new StringMap<AttributePart>();

    RendBlock(OffsetsBlock _offset) {
        offset = _offset;
    }

    static String inferOrObject(Configuration _an, String _type) {
        String t_ = _type;
        if (StringList.quickEq(_type,_an.getKeyWords().getKeyWordVar())) {
            t_ = _an.getStandards().getAliasObject();
        }
        return t_;
    }
    public static String getRes(RendDocumentBlock _rend, Configuration _conf) {
        _conf.initForms();
        String beanName_ = _rend.getBeanName();
        Struct bean_ = _conf.getBuiltBeans().getVal(beanName_);
        _conf.setMainBean(bean_);
        _conf.addPage(new ImportingPage());
        beforeDisplaying(bean_,_conf);
        _conf.removeLastPage();
        if (_conf.getContext().hasException()) {
            return EMPTY_STRING;
        }
        ImportingPage ip_ = new ImportingPage();
        int tabWidth_ = _conf.getTabWidth();
        ip_.setTabWidth(tabWidth_);
        ip_.setReadUrl(_conf.getCurrentUrl());
        ip_.setBeanName(beanName_);
        ip_.setFile(_rend.getFile());
        ip_.setPrefix(_conf.getPrefix());
        if (bean_ != null) {
            ip_.setGlobalArgumentStruct(bean_, _conf);
        }
        _conf.addPage(ip_);
        FullDocument doc_ = DocumentBuilder.newXmlDocument(tabWidth_);
        appendChild(doc_, doc_, _rend.getElt());
        RendReadWrite rw_ = new RendReadWrite();
        rw_.setConf(_conf);
        rw_.setRead(_rend.getFirstChild());
        ip_.setRoot(_rend);
        rw_.setWrite(doc_);
        rw_.setDocument(doc_);
        ip_.setRendReadWrite(rw_);
        while (true) {
            EndCallValue res_ = removeCall(_conf);
            if (res_ == EndCallValue.EXIT) {
                break;
            }
            if (res_ == EndCallValue.FORWARD) {
                continue;
            }
            processTags(_conf);
            if (_conf.getContext().hasException()) {
                _conf.getRendLocalThrowing().removeBlockFinally(_conf);
            }
            if (_conf.getContext().hasException()) {
                break;
            }
        }
        if (_conf.getContext().hasException()) {
            return EMPTY_STRING;
        }
        LongMap<LongTreeMap<NodeContainer>> containersMap_ = _conf.getContainersMap();
        LongMap<StringList> formatIdMap_ = _conf.getFormatIdMap();
        _conf.getHtmlPage().setContainers(containersMap_);
        _conf.getHtmlPage().setFormatIdMap(formatIdMap_);
        _conf.getHtmlPage().setCallsExps(_conf.getCallsExps());
        _conf.getHtmlPage().setAnchorsArgs(_conf.getAnchorsArgs());
        _conf.getHtmlPage().setAnchorsVars(_conf.getAnchorsVars());
        _conf.getHtmlPage().setAnchorsNames(_conf.getAnchorsNames());
        _conf.getHtmlPage().setConstAnchors(_conf.getConstAnchors());
        _conf.setBeanName(doc_.getDocumentElement().getAttribute(StringList.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean())));
        doc_.getDocumentElement().removeAttribute(StringList.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean()));
        doc_.getDocumentElement().removeAttribute(StringList.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrAlias()));
        _conf.setDocument(doc_);
        _conf.clearPages();
        return doc_.export();
    }

    private static EndCallValue removeCall(Configuration _context) {
        ImportingPage p_ = _context.getLastPage();
        if (p_.getRendReadWrite() == null) {
            _context.removeLastPage();
            if (_context.getImporting().isEmpty()) {
                return EndCallValue.EXIT;
            }
            return EndCallValue.FORWARD;
        }
        return EndCallValue.NEXT;
    }

    private static void processTags(Configuration _context) {
        ImportingPage ip_ = _context.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock en_ = rw_.getRead();
        ip_.setOffset(en_.getOffset().getOffsetTrim());
        tryProcessEl(_context);
    }

    private static void tryProcessEl(Configuration _context) {
        ImportingPage lastPage_ = _context.getLastPage();
        RendReadWrite rw_ = lastPage_.getRendReadWrite();
        RendBlock en_ = rw_.getRead();
        ((RendWithEl)en_).processEl(_context);
    }

    protected final void setParent(RendParentBlock _b) {
        parent = _b;
    }

    public static RendDocumentBlock newRendDocumentBlock(Configuration _conf, String _prefix, Document _doc, String _docText) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        int indexGlobal_ = _docText.indexOf(LT_BEGIN_TAG)+1;
        RendDocumentBlock out_ = new RendDocumentBlock(documentElement_,_docText,new OffsetsBlock(),_conf.getCurrentUrl());
        RendBlock curWrite_ = newRendBlockEsc(indexGlobal_,out_, _conf, _prefix, curNode_,_docText);
        out_.appendChild(curWrite_);
        while (curWrite_ != null) {
            MutableNode firstChild_ = curNode_.getFirstChild();
            if (firstChild_ != null) {
                indexGlobal_ = indexOfBeginNode(firstChild_, _docText, indexGlobal_);
                RendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,(RendParentBlock) curWrite_, _conf, _prefix, firstChild_,_docText);
                ((RendParentBlock) curWrite_).appendChild(rendBlock_);
                curWrite_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            while (true) {
                MutableNode nextSibling_ = curNode_.getNextSibling();
                RendParentBlock par_ = curWrite_.getParent();
                if (nextSibling_ != null) {
                    indexGlobal_ = indexOfBeginNode(nextSibling_, _docText, indexGlobal_);
                    RendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,par_, _conf, _prefix, nextSibling_,_docText);
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
                while (_html.charAt(i_) != END_ESCAPED) {
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
    private static RendBlock newRendBlockEsc(int _begin,RendParentBlock _curParent,Configuration _conf,String _prefix,Node _elt, String _docText) {
        RendBlock bl_ = newRendBlock(_begin, _curParent, _conf, _prefix, _elt, _docText);
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
    private static RendBlock newRendBlock(int _begin,RendParentBlock _curParent,Configuration _conf,String _prefix,Node _elt, String _docText) {
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            if (t_.getTextContent().trim().isEmpty()) {
                return new RendEmptyText(new OffsetStringInfo(_begin,t_.getTextContent()),new OffsetsBlock(_begin,_begin));
            }
            return new RendText(new OffsetStringInfo(_begin,t_.getTextContent()),new OffsetsBlock(_begin,_begin));
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
                return new RendForEachLoop(_conf,
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrVar(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrList(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                        new OffsetsBlock(_begin,_begin)
                );
            }
            if (elt_.hasAttribute(rendKeyWords_.getAttrMap())) {
                return new RendForEachTable(_conf,
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrKeyClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrKey(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrVarClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrMap(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                        new OffsetsBlock(_begin,_begin)
                );
            }
            if (elt_.hasAttribute(rendKeyWords_.getAttrVar())) {
                return new RendForIterativeLoop(_conf,
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrVar(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrFrom(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrTo(), attr_),
                        newOffsetBooleanInfo(elt_,rendKeyWords_.getAttrEq()),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrStep(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                        new OffsetsBlock(_begin,_begin)
                        );
            }
            return new RendForMutableIterativeLoop(_conf,
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrInit(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrStep(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrIndexClassName(), attr_)
                    ,newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordWhile()))) {
            MutableNode previousSibling_ = elt_.getPreviousSibling();
            if (previousSibling_ instanceof Text && previousSibling_.getTextContent().trim().isEmpty()) {
                previousSibling_ = previousSibling_.getPreviousSibling();
            }
            if (previousSibling_ instanceof Element
                    && StringList.quickEq(((Element) previousSibling_).getTagName(),StringList.concat(_prefix,rendKeyWords_.getKeyWordDo()))) {
                return new RendDoWhileCondition(newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                        new OffsetsBlock(_begin,_begin));
            }
            return new RendWhileCondition(newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordDo()))) {
            return new RendDoBlock(newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordReturn()))) {
            return new RendReturnMehod(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordBreak()))) {
            return new RendBreakBlock(newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordContinue()))) {
            return new RendContinueBlock(newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordThrow()))) {
            return new RendThrowing(newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordSet()))) {
            if (elt_.hasAttribute(rendKeyWords_.getAttrClassName())) {
                _curParent.appendChild(new RendDeclareVariable(newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                        new OffsetsBlock(_begin,_begin)));
            }
            return new RendLine(newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordIf()))) {
            return new RendIfCondition(newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordElseif()))) {
            return new RendElseIfCondition(newOffsetStringInfo(elt_,rendKeyWords_.getAttrCondition(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordElse()))) {
            return new RendElseCondition(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordTry()))) {
            return new RendTryEval(newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordCatch()))) {
            if (elt_.hasAttribute(rendKeyWords_.getAttrClassName())) {
                return new RendCatchEval(newOffsetStringInfo(elt_,rendKeyWords_.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_,rendKeyWords_.getAttrVar(), attr_),
                        new OffsetsBlock(_begin,_begin));
            }
            return new RendNullCatchEval(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordFinally()))) {
            return new RendFinallyEval(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordSwitch()))) {
            return new RendSwitchBlock(newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                    newOffsetStringInfo(elt_,rendKeyWords_.getAttrLabel(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordCase()))) {
            return new RendCaseCondition(newOffsetStringInfo(elt_,rendKeyWords_.getAttrValue(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordDefault()))) {
            return new RendDefaultCondition(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordImport()))) {
            return new RendImport(elt_,newOffsetStringInfo(elt_,rendKeyWords_.getAttrPage(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordSubmit()))) {
            return new RendSubmit(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordAnchor())) {
            return new RendAnchor(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordImg())) {
            return new RendImg(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordLink())) {
            return new RendLink(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordStyle())) {
            return new RendStyle(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordImg()))) {
            return new RendEscImg(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordPackage()))) {
            return new RendPackage(newOffsetStringInfo(elt_,rendKeyWords_.getAttrName(), attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordForm())) {
            return new RendForm(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordForm()))) {
            return new RendImportForm(newOffsetStringInfo(elt_,rendKeyWords_.getAttrForm(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordClass()))) {
            return new RendClass(newOffsetStringInfo(elt_,rendKeyWords_.getAttrName(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordField()))) {
            return new RendField(newOffsetStringInfo(elt_,rendKeyWords_.getAttrPrepare(), attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordMessage()))) {
            return new RendMessage(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordSelect()))) {
            return new RendSelect(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordInput())) {
            if (StringList.quickEq(elt_.getAttribute(rendKeyWords_.getAttrType()),rendKeyWords_.getValueRadio())) {
                return new RendRadio(elt_,new OffsetsBlock(_begin,_begin));
            }
            return new RendStdInput(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordTextarea())) {
            return new RendTextArea(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,rendKeyWords_.getKeyWordSpan())) {
            if (!elt_.getAttribute(StringList.concat(_conf.getPrefix(),rendKeyWords_.getAttrFor())).isEmpty()) {
                return new RendSpan(elt_,new OffsetsBlock(_begin,_begin));
            }
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,rendKeyWords_.getKeyWordAnchor()))) {
            return new RendTitledAnchor(elt_,new OffsetsBlock(_begin,_begin));
        }
        return new RendStdElement(elt_,new OffsetsBlock(_begin,_begin));
    }

    static StringMap<String> getPre(Configuration _cont, String _value, int _offset) {
        StringList elts_ = StringList.splitStrings(_value, COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(_cont, var_);
        if (fileName_ == null) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_cont.getCurrentFileName());
            badEl_.setIndexFile(_offset);
            badEl_.buildError(_cont.getRendAnalysisMessages().getInexistantKey(),
                    var_);
            _cont.addError(badEl_);
            return new StringMap<String>();
        }
        StringMap<String> pres_ = new StringMap<String>();
        AnalyzingDoc a_ = _cont.getAnalyzingDoc();
        for (String l: a_.getLanguages()) {
            StringMap<String> files_ = a_.getFiles();
            String content_ = RendExtractFromResources.tryGetContent(_cont, l, fileName_, files_);
            int index_ = RendExtractFromResources.indexCorrectMessages(content_);
            String cont_ = content_;
            if (cont_ == null) {
                cont_ = EMPTY_STRING;
            }
            if (index_ >= 0) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_offset);
                badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadExpression(),
                        ElUtil.possibleChar(index_,cont_),
                        Integer.toString(index_),
                        content_);
                _cont.addError(badEl_);
                return new StringMap<String>();
            }
            StringMap<String> messages_ = RendExtractFromResources.getMessages(content_);
            String key_ = elts_.last();
            String format_ = RendExtractFromResources.getQuickFormat(messages_, key_);
            if (format_ == null) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_offset);
                badEl_.buildError(_cont.getRendAnalysisMessages().getInexistantKey(),
                        key_);
                _cont.addError(badEl_);
                return new StringMap<String>();
            }
            pres_.addEntry(l,format_);
        }
        return pres_;
    }

    protected static void processLink(Configuration _cont, Element _nextWrite, Element _read, StringList _varNames, CustList<CustList<RendDynOperationNode>> _opExp, StringList _texts) {
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        _cont.getCallsExps().add(new CustList<RendDynOperationNode>());
        _cont.getConstAnchors().add(true);
        _cont.getAnchorsArgs().add(new StringList());
        _cont.getAnchorsVars().add(_varNames);
        if (!href_.startsWith(CALL_METHOD)) {
            if (_nextWrite.hasAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()))) {
                _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrHref(), EMPTY_STRING);
            }
            _cont.getAnchorsNames().add(EMPTY_STRING);
            incrAncNb(_cont, _nextWrite);
            return;
        }
        String render_ = ResultText.render(_opExp, _texts, _cont);
        if (_cont.getContext().hasException()) {
            _cont.getAnchorsNames().add(EMPTY_STRING);
            incrAncNb(_cont, _nextWrite);
            return;
        }
        _cont.getAnchorsNames().add(render_);
        String beanName_ = _cont.getLastPage().getBeanName();
        _nextWrite.setAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringList.concat(CALL_METHOD,beanName_,DOT,render_));
        _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrHref(), EMPTY_STRING);
        incrAncNb(_cont, _nextWrite);
    }

    protected static void incrAncNb(Configuration _cont, Element _nextEltWrite) {
        if (StringList.quickEq(_nextEltWrite.getTagName(), _cont.getRendKeyWords().getKeyWordAnchor())
                && (_nextEltWrite.hasAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()))
                || !_nextEltWrite.getAttribute(_cont.getRendKeyWords().getAttrHref()).isEmpty() )) {
            long currentAnchor_ = _cont.getIndexes().getAnchor();
            _nextEltWrite.setAttribute(_cont.getRendKeyWords().getAttrNa(), String.valueOf(currentAnchor_));
            currentAnchor_++;
            _cont.getIndexes().setAnchor(currentAnchor_);
        }
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

    protected static void appendText(String _fileContent, Document _ownerDocument, Element _eltStyle) {
        CustList<Node> chNode_ = _eltStyle.getChildNodes();
        if (chNode_.isEmpty()) {
            Text text_ = _ownerDocument.createTextNode(_fileContent);
            _eltStyle.appendChild(text_);
        } else {
            Text text_ = (Text) chNode_.last();
            text_.appendData(_fileContent);
        }
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
    public abstract void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc);
    private static OffsetBooleanInfo newOffsetBooleanInfo(Element _elt, String _key) {
        return new OffsetBooleanInfo(0,_elt.hasAttribute(_key));
    }
    protected static void tryBuildExpressionLanguage(RendBlock _block, Configuration _cont,RendDocumentBlock _doc) {
        _block.buildExpressionLanguage(_cont,_doc);
    }

    static void appendChild(Document _doc, Node _parent, Element _read) {
        Element currentNode_;
        if (_parent instanceof Document) {
            currentNode_ = _doc.createElement(_read.getTagName());
            setNormalAttributes(_read, currentNode_);
            ((Document)_parent).appendChild(currentNode_);
            return;
        }
        currentNode_ = _doc.createElement(_read.getTagName());
        setNormalAttributes(_read, currentNode_);
        ((MutableNode)_parent).appendChild(currentNode_);
    }

    private static void setNormalAttributes(Element _read, Element _write) {
        NamedNodeMap map_ = _read.getAttributes();
        int nbAttrs_ = map_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            Attr at_ = map_.item(i);
            String name_ = at_.getName();
            String value_ = at_.getValue();
            _write.setAttribute(name_, value_);
        }
    }

    protected static Argument iteratorMultTable(Struct _arg, Configuration _cont) {
        BeanLgNames stds_ = _cont.getAdvStandards();
        return stds_.iteratorMultTable(_arg,_cont);
    }
    protected static Argument hasNextPair(Struct _arg,Configuration _conf) {
        BeanLgNames stds_ = _conf.getAdvStandards();
        return stds_.hasNextPair(_arg,_conf);
    }
    protected static Argument nextPair(Struct _arg,Configuration _conf) {
        BeanLgNames stds_ = _conf.getAdvStandards();
        return stds_.nextPair(_arg,_conf);
    }
    protected static Argument first(Struct _arg,Configuration _conf) {
        BeanLgNames stds_ = _conf.getAdvStandards();
        return stds_.first(_arg,_conf);
    }
    protected static Argument second(Struct _arg,Configuration _conf) {
        BeanLgNames stds_ = _conf.getAdvStandards();
        return stds_.second(_arg,_conf);
    }
    protected static Argument iterator(Struct _arg,Configuration _cont) {
        BeanLgNames stds_ = _cont.getAdvStandards();
        return stds_.iterator(_arg,_cont);
    }
    protected static Argument hasNext(Struct _arg,Configuration _cont) {
        BeanLgNames stds_ = _cont.getAdvStandards();
        return stds_.hasNext(_arg,_cont);
    }
    protected static Argument next(Struct _arg,Configuration _cont) {
        BeanLgNames stds_ = _cont.getAdvStandards();
        return stds_.next(_arg,_cont);
    }
    protected static void beforeDisplaying(Struct _arg,Configuration _cont) {
        if (_arg == null) {
            return;
        }
        BeanLgNames stds_ = _cont.getAdvStandards();
        stds_.beforeDisplaying(_arg,_cont);
    }

    static String getStringKey(Configuration _conf, Struct _instance) {
        return _conf.getAdvStandards().getStringKey(_conf,_instance);
    }

    protected static Argument fetchName(Configuration _cont, Element _read, Element _write, FieldUpdates _f) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return Argument.createVoid();
        }
        Struct obj_;
        Struct currentField_;
        long found_ = -1;
        CustList<RendDynOperationNode> opsRead_ = _f.getOpsRead();
        CustList<RendDynOperationNode> opsWrite_ = _f.getOpsWrite();
        ClassField idField_ = _f.getIdField();
        String varName_ = _f.getVarName();
        IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(opsRead_, _cont);
        if (_cont.getContext().hasException()) {
            return Argument.createVoid();
        }
        RendDynOperationNode root_ = args_.lastKey();
        RendDynOperationNode res_;
        if (root_ instanceof RendIdOperation) {
            res_ = RendAffectationOperation.getIdOp((RendMethodOperation) root_);
        } else {
            res_ = root_;
        }
        RendSettableElResult settable_ = RendAffectationOperation.castDottedTo(res_);
        ArgumentsPair pair_ = args_.getValue(((RendSettableFieldOperation) settable_).getOrder());
        if (((RendSettableFieldOperation) settable_).isIntermediateDottedOperation()) {
            obj_ = pair_.getPreviousArgument().getStruct();
        } else {
            obj_ = _cont.getLastPage().getGlobalArgument().getStruct();
        }
        Argument arg_ = pair_.getArgument();
        CustList<LongTreeMap<NodeContainer>> stack_ = _cont.getContainersMapStack();
        if (stack_.isEmpty()) {
            return arg_;
        }
        for (EntryCust<Long, NodeContainer> e: stack_.last().entryList()) {
            if (!e.getValue().getStruct().sameReference(obj_)) {
                continue;
            }
            if (!e.getValue().getIdField().eq(idField_)) {
                continue;
            }
            found_ = e.getKey();
            break;
        }
        currentField_ = arg_.getStruct();
        if (found_ == -1) {
            long currentInput_ = _cont.getInputs().last();
            NodeContainer nodeCont_ = new NodeContainer();
            nodeCont_.setIdField(idField_);
            nodeCont_.setTypedStruct(currentField_);
            nodeCont_.setStruct(obj_);
            StringList strings_ = StringList.splitInTwo(varName_, varName_.indexOf(','));
            nodeCont_.setVarPrevName(StringList.removeChars(strings_.first(),','));
            nodeCont_.setVarName(StringList.removeChars(strings_.last(),','));
            nodeCont_.setOpsWrite(opsWrite_);
            nodeCont_.setOpsConvert(_f.getOpsConverter());
            nodeCont_.setVarNameConvert(_f.getVarNameConverter());
            nodeCont_.setArrayConverter(_f.isArrayConverter());
            nodeCont_.setBean(_cont.getLastPage().getGlobalArgument().getStruct());
            NodeInformations nodeInfos_ = nodeCont_.getNodeInformation();
            String id_ = _write.getAttribute(_cont.getRendKeyWords().getAttrId());
            if (id_.isEmpty()) {
                id_ = _write.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrGroupId()));
            }
            String class_ = _cont.getAdvStandards().getInputClass(_write,_cont);
            nodeInfos_.setValidator(_write.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator())));
            nodeInfos_.setId(id_);
            nodeInfos_.setInputClass(class_);
            stack_.last().put(currentInput_, nodeCont_);
            _cont.getIndexes().setNb(currentInput_);
            currentInput_++;
            _cont.getInputs().setLast(currentInput_);
        } else {
            _cont.getIndexes().setNb(found_);
        }
        _write.setAttribute(_cont.getRendKeyWords().getAttrNi(), String.valueOf(_cont.getIndexes().getNb()));
//        attributesNames_.removeAllString(NUMBER_INPUT);
        _write.setAttribute(_cont.getRendKeyWords().getAttrName(), StringList.concat(_cont.getLastPage().getBeanName(),DOT,name_));
        return arg_;
    }
    static CustList<RendDynOperationNode> reduceList(CustList<RendDynOperationNode> _list) {
        if (_list.isEmpty()) {
            return _list;
        }
        return RenderExpUtil.getReducedNodes(_list.last());
    }

    protected static void fetchValue(Configuration _cont, Element _read, Element _write, CustList<RendDynOperationNode> _ops, String _varNameConv,CustList<RendDynOperationNode> _opsConv) {
//        _conf.getLastPage().setProcessingAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VAR_VALUE));
//        _conf.getLastPage().setLookForAttrValue(true);
//        _conf.getLastPage().setOffset(0);
        if (_cont.getContext().hasException()) {
            return;
        }
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return;
        }
        if (_ops.isEmpty()) {
            return;
        }
        if (StringList.quickEq(_read.getTagName(),_cont.getRendKeyWords().getKeyWordInput())) {
            Argument o_ = RenderExpUtil.calculateReuse(_ops,_cont);
            if (_cont.getContext().hasException()) {
                return;
            }
            if (StringList.quickEq(_read.getAttribute(_cont.getRendKeyWords().getAttrType()),_cont.getRendKeyWords().getValueCheckbox())) {
                if (Argument.isTrueValue(o_)) {
                    _write.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
                } else {
                    _write.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
                }
            } else {
                o_ = convertField(_cont,o_,_varNameConv,_opsConv);
                if (_cont.getContext().hasException()) {
                    return;
                }
                String value_ = _cont.getAdvStandards().processString(o_, _cont);
                if (_cont.getContext().hasException()) {
                    return;
                }
                _write.setAttribute(_cont.getRendKeyWords().getAttrValue(), value_);
            }
        }
        if (StringList.quickEq(_read.getTagName(),_cont.getRendKeyWords().getKeyWordTextarea())) {
            Argument o_ = RenderExpUtil.calculateReuse(_ops,_cont);
            if (_cont.getContext().hasException()) {
                return;
            }
            o_ = convertField(_cont,o_,_varNameConv,_opsConv);
            if (_cont.getContext().hasException()) {
                return;
            }
            Document doc_ = _write.getOwnerDocument();
            String value_ = _cont.getAdvStandards().processString(o_, _cont);
            if (_cont.getContext().hasException()) {
                return;
            }
            Text text_ = doc_.createTextNode(value_);
            _write.appendChild(text_);
        }
        _write.removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()));
    }
    private static Argument convertField(Configuration _cont, Argument _o,String _varNameConv, CustList<RendDynOperationNode> _opsConv) {
        Argument o_ = _o;
        if (!_opsConv.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(o_.getStruct(),_cont.getStandards().getAliasObject());
            _cont.getLastPage().putLocalVar(_varNameConv, locVar_);
            Argument arg_ = RenderExpUtil.calculateReuse(_opsConv, _cont);
            _cont.getLastPage().removeLocalVar(_varNameConv);
            if (_cont.getContext().hasException()) {
                return Argument.createVoid();
            }
            o_ = arg_;
        }
        if (o_.getStruct() == NullStruct.NULL_VALUE) {
            o_.setStruct(new StringStruct(EMPTY_STRING));
        }
        return o_;
    }
    protected static String getProperty(Configuration _conf, String _key) {
        return _conf.getProperties().getVal(_key);
    }
    static String escapeParam(Configuration _conf, Argument _arg) {
        String str_ = _conf.getAdvStandards().processString(_arg,_conf);
        if (_conf.getContext().hasException()) {
            return str_;
        }
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), StringList.concat(quote_,String.valueOf(LEFT_EL),quote_));
        rep_.put(String.valueOf(RIGHT_EL), StringList.concat(quote_,String.valueOf(RIGHT_EL),quote_));
        rep_.put(String.valueOf(QUOTE), StringList.concat(quote_,quote_));
        return StringList.replaceMultiple(str_, rep_);
    }
    protected static String escapeParam(String _arg) {
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), StringList.concat(quote_,String.valueOf(LEFT_EL),quote_));
        rep_.put(String.valueOf(RIGHT_EL), StringList.concat(quote_,String.valueOf(RIGHT_EL),quote_));
        rep_.put(String.valueOf(QUOTE), StringList.concat(quote_,quote_));
        return StringList.replaceMultiple(_arg, rep_);
    }

    public static CustList<RendBlock> getDirectChildren(RendBlock _block) {
        CustList<RendBlock> l_ = new CustList<RendBlock>();
        RendBlock child_ = _block.getFirstChild();
        while (child_ != null) {
            l_.add(child_);
            child_ = child_.getNextSibling();
        }
        return l_;
    }
    public final OffsetsBlock getOffset() {
        return offset;
    }

    public final RendBlock getPreviousSibling() {
        return previousSibling;
    }
    public abstract RendBlock getFirstChild();

    public final RendBlock getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(RendBlock _nextSibling) {
        nextSibling = _nextSibling;
    }
    final void setPreviousSibling(RendBlock _previousSibling) {
        previousSibling = _previousSibling;
    }

    public final RendParentBlock getParent() {
        return parent;
    }

    protected static String lookForVar(Configuration _cont, StringList _varNames) {
        String varLoc_ = TMP_LOC;
        int indexLoc_ = 0;
        while (!_cont.getContext().isNotVar(varLoc_) || StringList.contains(_varNames,varLoc_)) {
            varLoc_ = StringList.concatNbs(varLoc_,indexLoc_);
            indexLoc_++;
        }
        return varLoc_;
    }
    public final void processBlockAndRemove(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_conf);
    }
    public final void processBlock(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendParentElement parElt_ = getNextBlock(ip_,this);
        if (parElt_ == null) {
            ip_.setNullRendReadWrite();
            return;
        }
        RendParentBlock par_ = parElt_.getElement();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (par_ == null) {
            RendBlock n_ = getNextSibling();
            rw_.setRead(n_);
            return;
        }
        if (par_ instanceof RendLoop) {
            par_.removeLocalVars(ip_);
        } else {
            par_.removeAllVars(ip_);
        }
        rw_.setRead(par_);
        par_.exitStack(_conf);
    }
    public static RendParentElement getNextBlock(ImportingPage _ip,RendBlock _bl) {
        RendParentElement parElt_;
        RendBlock nextSibling_ = _bl.getNextSibling();
        if (nextSibling_ != null) {
            parElt_ = new RendParentElement(null);
        } else {
            RendParentBlock n_ = _bl.getParent();
            //n_ != null because strictly in class
            if (n_ != _ip.getRoot()) {
                parElt_ =  new RendParentElement(n_);
            } else {
                //directly at the root => last element in the block root
                parElt_ = null;
            }
        }
        return parElt_;
    }

    protected void setEscapedChars(StringMap<IntTreeMap<Integer>> _escapedChars) {
        escapedChars = _escapedChars;
    }

    public StringMap<IntTreeMap<Integer>> getEscapedChars() {
        return escapedChars;
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
