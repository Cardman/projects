package code.bean.nat.analyze.blocks;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.formathtml.errors.RendKeyWords;
import code.sml.*;
import code.sml.util.ResourcesMessagesUtil;
import code.util.EntryCust;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendBlockHelp {
    static final String CALL_METHOD = "$";
    static final String TMP_BLOCK_TAG = "tmp";
    static final String TMP_LOC = "tmpLoc";
    static final String EMPTY_STRING = "";
    static final char GT_TAG = '>';
    static final char LT_BEGIN_TAG = '<';
    static final String LT_END_TAG = "</";
    private static final String TAB = "\t";

    private AnaRendBlockHelp() {
    }

    static StringMap<String> getPre(String _value, AnalyzingDoc _analyzingDoc) {
        StringList elts_ = StringUtil.splitStrings(_value, AnaRendBlock.COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(var_, _analyzingDoc);
        StringMap<String> pres_ = new StringMap<String>();
        for (String l: _analyzingDoc.getLanguages()) {
            StringMap<String> files_ = _analyzingDoc.getFiles();
            String content_ = tryGetContent(l, fileName_, files_, _analyzingDoc);
            StringMap<String> messages_ = AnaRendBlock.getMessages(content_);
            String key_ = elts_.last();
            String format_ = getQuickFormat(messages_, key_);
            pres_.addEntry(l,format_);
        }
        return pres_;
    }

    protected static String getProperty(String _key, AnalyzingDoc _anaDoc) {
        return _anaDoc.getProperties().getVal(_key);
    }

    public static AnaRendDocumentBlock newRendDocumentBlock(String _prefix, Document _doc, String _docText, String _currentUrl, RendKeyWords _rendKeyWords, BeanNatLgNames _caller) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        int indexGlobal_ = _docText.indexOf(LT_BEGIN_TAG)+1;
        AnaRendDocumentBlock out_ = new AnaRendDocumentBlock(documentElement_,_docText,0, _currentUrl);
        AnaRendBlock curWrite_ = newRendBlockEsc(indexGlobal_, _prefix, curNode_,_docText, _rendKeyWords, _caller);
        out_.appendChild(curWrite_);
        while (curWrite_ != null) {
            Node firstChild_ = curNode_.getFirstChild();
            if (curWrite_ instanceof AnaRendParentBlock &&firstChild_ != null) {
                indexGlobal_ = AnaRendBlock.indexOfBeginNode(firstChild_, _docText, indexGlobal_);
                AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_, _prefix, firstChild_,_docText, _rendKeyWords, _caller);
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
                    indexGlobal_ = AnaRendBlock.indexOfBeginNode(nextSibling_, _docText, indexGlobal_);
                    AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_, _prefix, nextSibling_,_docText, _rendKeyWords, _caller);
                    par_.appendChild(rendBlock_);
                    curWrite_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = curNode_.getParentNode();
                if (parentNode_ == documentElement_) {
                    parentNode_ = null;
                }
                if (parentNode_ == null) {
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
            NatAnaRendEmptyInstruction empty_ = new NatAnaRendEmptyInstruction(off_);
            ((AnaRendParentBlock) _curWrite).appendChild(empty_);
        }
    }

    private static AnaRendBlock newRendBlockEsc(int _begin, String _prefix, Node _elt, String _docText, RendKeyWords _rendKeyWords, BeanNatLgNames _caller) {
        AnaRendBlock bl_ = newRendBlock(_begin, _prefix, _elt, _docText, _rendKeyWords, _caller);
        if (_elt instanceof Text) {
            int endHeader_ = _docText.indexOf(LT_BEGIN_TAG, _begin);
            AttributePart attrPart_ = new AttributePart();
            attrPart_.setBegin(_begin);
            attrPart_.setEnd(endHeader_);
            IntTreeMap<Integer> esc_ = AnaRendBlock.getIndexesSpecChars(_docText, false, attrPart_, _begin);
            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
            infos_.addEntry(EMPTY_STRING,esc_);
            bl_.setEscapedChars(infos_);
        } else {
            Element elt_ = (Element) _elt;
            String tagName_ = elt_.getTagName();
            int endHeader_ = _docText.indexOf(GT_TAG, _begin);
            int beginHeader_ = _begin + tagName_.length();
            StringMap<AttributePart> attr_;
            attr_ = getAttributes(_docText, beginHeader_, endHeader_);
            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
            for (EntryCust<String, AttributePart> e: attr_.entryList()) {
                infos_.put(e.getKey(), AnaRendBlock.getIndexesSpecChars(_docText, true, e.getValue(), _begin));
            }
            bl_.setEscapedChars(infos_);
        }
        return bl_;
    }

    private static AnaRendBlock newRendBlock(int _begin, String _prefix, Node _elt, String _docText, RendKeyWords _rendKeyWords, BeanNatLgNames _caller) {
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            return new NatAnaRendText(new OffsetStringInfo(_begin,t_.getTextContent()),_begin);
        }
        Element elt_ = (Element) _elt;
        String tagName_ = elt_.getTagName();
        int endHeader_ = _docText.indexOf(GT_TAG, _begin);
        int beginHeader_ = _begin + tagName_.length();
        StringMap<AttributePart> attr_;
        attr_ = getAttributes(_docText, beginHeader_, endHeader_);
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordFor()))) {
            if (elt_.hasAttribute(_rendKeyWords.getAttrList())) {
                return new NatAnaRendForEachLoop(
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrVar(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrList(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrIndexClassName(), attr_),
                        newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                        _begin, _caller
                );
            }
            return new NatAnaRendForEachTable(
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrKeyClassName(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrKey(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrVarClassName(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrValue(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrMap(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrIndexClassName(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),
                    _begin
            );
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordIf()))) {
            return new NatAnaRendIfCondition(newOffsetStringInfo(elt_, _rendKeyWords.getAttrCondition(), attr_),
                    newOffsetStringInfo(elt_, _rendKeyWords.getAttrLabel(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordElseif()))) {
            return new NatAnaRendElseIfCondition(newOffsetStringInfo(elt_, _rendKeyWords.getAttrCondition(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordElse()))) {
            return new NatAnaRendElseCondition(_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImport()))) {
            return new NatAnaRendImport(elt_,newOffsetStringInfo(elt_, _rendKeyWords.getAttrPage(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSubmit()))) {
            return new NatAnaRendSubmit(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordAnchor())) {
            return new NatAnaRendAnchor(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordImg())) {
            return new NatAnaRendImg(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordLink())) {
            return new NatAnaRendLink(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImg()))) {
            return new NatAnaRendEscImg(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordPackage()))) {
            return new NatAnaRendPackage(newOffsetStringInfo(elt_, _rendKeyWords.getAttrName(), attr_),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordForm())) {
            return new NatAnaRendForm(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordClass()))) {
            return new NatAnaRendClass(newOffsetStringInfo(elt_, _rendKeyWords.getAttrName(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordField()))) {
            return new NatAnaRendField(newOffsetStringInfo(elt_, _rendKeyWords.getAttrPrepare(), attr_),_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordMessage()))) {
            return new NatAnaRendMessage(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSelect()))) {
            return new NatAnaRendSelect(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordInput())) {
            if (StringUtil.quickEq(elt_.getAttribute(_rendKeyWords.getAttrType()), _rendKeyWords.getValueRadio())) {
                return new NatAnaRendRadio(elt_,_begin);
            }
            return new NatAnaRendStdInput(elt_,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordSpan())) {
            if (!elt_.getAttribute(StringUtil.concat(_prefix, _rendKeyWords.getAttrFor())).isEmpty()) {
                return new NatAnaRendSpan(elt_,_begin);
            }
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordAnchor()))) {
            return new NatAnaRendTitledAnchor(elt_,_begin);
        }
        return new NatAnaRendStdElement(elt_,_begin);
    }

    private static StringMap<AttributePart> getAttributes(String _html, int _from, int _to) {
        return DocumentAttribute.getAttributes(_html, _from, _to);
    }

    private static OffsetStringInfo newOffsetStringInfo(Element _elt, String _key, StringMap<AttributePart> _attr) {
        return AnaRendBlock.newOffsetStringInfo(_elt, _key, _attr);
    }

    public static String lookForVar(StringList _varNames) {
        String varLoc_ = TMP_LOC;
        int indexLoc_ = 0;
        while (StringUtil.contains(_varNames,varLoc_)) {
            varLoc_ = StringUtil.concatNbs(TMP_LOC,indexLoc_);
            indexLoc_++;
        }
        return varLoc_;
    }

    public static String tryGetContent(String _loc, String _relative, StringMap<String> _files, AnalyzingDoc _anaDoc) {
        String folder_ = _anaDoc.getMessagesFolder();
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,_loc,_relative);
        return getContentFile(_files, fileName_);
    }

    private static String getContentFile(StringMap<String> _files, String _fileName) {
        return _files.getVal(_fileName);
    }

    public static String getQuickFormat(StringMap<String> _messages, String _key) {
        return _messages.getVal(_key);
    }

    static String getCssHref(Element _link, RendKeyWords _rendKeyWords) {
        return _link.getAttribute(_rendKeyWords.getAttrHref());
    }

}
