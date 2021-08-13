package code.bean.nat.analyze.blocks;

import code.bean.nat.BeanNatLgNames;
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

    private AnaRendBlockHelp() {
    }

    public static StringMap<String> getPre(String _value, AnalyzingDoc _analyzingDoc) {
        StringList elts_ = StringUtil.splitStrings(_value, AnaRendBlock.COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(var_, _analyzingDoc);
        StringMap<String> pres_ = new StringMap<String>();
        for (String l: _analyzingDoc.getLanguages()) {
            StringMap<String> files_ = _analyzingDoc.getFiles();
            String content_ = tryGetContent(l, fileName_, files_, _analyzingDoc);
            StringMap<String> messages_ = AnaRendBlock.getMessages(content_);
            String key_ = elts_.last();
            String format_ = messages_.getVal(key_);
            pres_.addEntry(l,format_);
        }
        return pres_;
    }

    static String getProperty(String _key, AnalyzingDoc _anaDoc) {
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
            while (curWrite_ != null) {
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
                Element parentNode_ = tryGetParent(documentElement_, curNode_);
                if (parentNode_ == null) {
                    curWrite_ = null;
                } else {
                    curWrite_ = par_;
                    curNode_ = parentNode_;
                }

            }
        }
        return out_;
    }

    private static Element tryGetParent(Element _documentElement, Node _curNode) {
        Element parentNode_ = _curNode.getParentNode();
        if (parentNode_ == _documentElement) {
            parentNode_ = null;
        }
        return parentNode_;
    }

    private static void tryAppendEmptyBlock(AnaRendBlock _curWrite) {
        if (_curWrite instanceof AnaRendParentBlock) {
            int off_ = _curWrite.getOffset();
            NatAnaRendEmptyInstruction empty_ = new NatAnaRendEmptyInstruction(off_);
            ((AnaRendParentBlock) _curWrite).appendChild(empty_);
        }
    }

    private static AnaRendBlock newRendBlockEsc(int _begin, String _prefix, Node _elt, String _docText, RendKeyWords _rendKeyWords, BeanNatLgNames _caller) {
        AnaRendBlock bl_;
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            bl_ = new NatAnaRendText(new OffsetStringInfo(_begin, t_.getTextContent()), _begin);
            int endHeader_ = _docText.indexOf(LT_BEGIN_TAG, _begin);
            AttributePart attrPart_ = new AttributePart();
            attrPart_.setBegin(_begin);
            attrPart_.setEnd(endHeader_);
            IntTreeMap<Integer> esc_ = AnaRendBlock.getIndexesSpecChars(_docText, false, attrPart_, _begin);
            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
            infos_.addEntry(EMPTY_STRING, esc_);
            bl_.setEscapedChars(infos_);
        } else {
            Element elt_ = (Element) _elt;
            StringMap<AttributePart> attributes_ = getAttributes(_docText, _begin + elt_.getTagName().length(), _docText.indexOf(GT_TAG, _begin));
            bl_ = element(_begin, _prefix, (Element) _elt, _rendKeyWords, _caller, attributes_);
            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
            for (EntryCust<String, AttributePart> e : attributes_.entryList()) {
                infos_.put(e.getKey(), AnaRendBlock.getIndexesSpecChars(_docText, true, e.getValue(), _begin));
            }
            bl_.setEscapedChars(infos_);
        }
        return bl_;
    }

    private static AnaRendParentBlock element(int _begin, String _prefix, Element _elt, RendKeyWords _rendKeyWords, BeanNatLgNames _caller, StringMap<AttributePart> _attributes) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordFor()))) {
            return collection(_begin, _rendKeyWords, _caller, _elt, _attributes);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordIf()))) {
            return new NatAnaRendIfCondition(newOffsetStringInfo(_elt, _rendKeyWords.getAttrCondition(), _attributes),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attributes), _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordElseif()))) {
            return new NatAnaRendElseIfCondition(newOffsetStringInfo(_elt, _rendKeyWords.getAttrCondition(), _attributes),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordElse()))) {
            return new NatAnaRendElseCondition(_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImport()))) {
            return new NatAnaRendImport(_elt,newOffsetStringInfo(_elt, _rendKeyWords.getAttrPage(), _attributes), _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSubmit()))) {
            return new NatAnaRendSubmit(_elt, _begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordAnchor())) {
            return new NatAnaRendAnchor(_elt, _begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordImg())) {
            return new NatAnaRendImg(_elt, _begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordLink())) {
            return new NatAnaRendLink(_elt, _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImg()))) {
            return new NatAnaRendEscImg(_elt, _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordPackage()))) {
            return new NatAnaRendPackage(newOffsetStringInfo(_elt, _rendKeyWords.getAttrName(), _attributes),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordForm())) {
            return new NatAnaRendForm(_elt, _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordClass()))) {
            return new NatAnaRendClass(newOffsetStringInfo(_elt, _rendKeyWords.getAttrName(), _attributes), _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordField()))) {
            return new NatAnaRendField(newOffsetStringInfo(_elt, _rendKeyWords.getAttrPrepare(), _attributes), _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordMessage()))) {
            return new NatAnaRendMessage(_elt, _begin);
        }
        return input(_begin, _prefix, _rendKeyWords, _elt);
    }

    private static AnaRendParentBlock collection(int _begin, RendKeyWords _rendKeyWords, BeanNatLgNames _caller, Element _elt, StringMap<AttributePart> _attr) {
        if (_elt.hasAttribute(_rendKeyWords.getAttrList())) {
            return new NatAnaRendForEachLoop(
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrClassName(), _attr),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrVar(), _attr),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrList(), _attr),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attr),
                    _begin, _caller
            );
        }
        return new NatAnaRendForEachTable(
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrKeyClassName(), _attr),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrKey(), _attr),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrVarClassName(), _attr),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrValue(), _attr),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrMap(), _attr),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrLabel(), _attr),
                _begin
        );
    }

    private static AnaRendParentBlock input(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSelect()))) {
            return new NatAnaRendSelect(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordInput())) {
            if (StringUtil.quickEq(_elt.getAttribute(_rendKeyWords.getAttrType()), _rendKeyWords.getValueRadio())) {
                return new NatAnaRendRadio(_elt,_begin);
            }
            return new NatAnaRendStdInput(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordSpan()) && !_elt.getAttribute(StringUtil.concat(_prefix, _rendKeyWords.getAttrFor())).isEmpty()) {
            return new NatAnaRendSpan(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordAnchor()))) {
            return new NatAnaRendTitledAnchor(_elt,_begin);
        }
        return new NatAnaRendStdElement(_elt,_begin);
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
        return _files.getVal(fileName_);
    }

    static String getCssHref(Element _link, RendKeyWords _rendKeyWords) {
        return _link.getAttribute(_rendKeyWords.getAttrHref());
    }

}
