package code.bean.nat.analyze.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.BeanNatLgNames;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.*;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.structs.BeanInfo;
import code.sml.*;
import code.sml.util.ResourcesMessagesUtil;
import code.util.CustList;
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

    public static void buildFctInstructions(AnaRendDocumentBlock _doc,AnalyzingDoc _anaDoc, NatAnalyzedCode _page, StringMap<BeanInfo> _beansInfosBefore) {
        _doc.setBeanName(_doc.getElt().getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean())));
        String clName_ = _beansInfosBefore.getVal(_doc.getBeanName()).getResolvedClassName();
        AnaFormattedRootBlock globalType_ = new AnaFormattedRootBlock((RootBlock) null, clName_);
        _page.setGlobalType(globalType_);
        loop(_doc, _anaDoc, _page);
    }

    public static void loop(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        AnaRendBlock enNat_ = _doc;
        while (true) {
            AnaRendBlock n_ = enNat_.getFirstChild();
            if (enNat_ instanceof NatRendBuildEl) {
                ((NatRendBuildEl)enNat_).buildExpressionLanguage(_doc, _anaDoc, _page);
            }
            if (n_ != null) {
                enNat_ = n_;
                continue;
            }
            while (true) {
                n_ = enNat_.getNextSibling();
                if (n_ != null) {
                    enNat_ = n_;
                    break;
                }
                AnaRendParentBlock par_;
                par_ = enNat_.getParent();
                removeAllVars(par_, _page.getInfosVars(), _page.getLoopsVars());
                if (par_ == _doc) {
                    return;
                }
                enNat_ = par_;
            }
        }
    }

    private static void removeAllVars(AnaRendParentBlock _par, StringMap<AnaLocalVariable> _infosVars, StringMap<AnaLoopVariable> _loopsVars) {
        if (_par instanceof NatAnaRendForEachLoop) {
            ((NatAnaRendForEachLoop)_par).removeVars(_infosVars, _loopsVars);
        }
        if (_par instanceof NatAnaRendForEachTable) {
            ((NatAnaRendForEachTable)_par).removeVars(_infosVars, _loopsVars);
        }
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

    public static AnaRendDocumentBlock newRendDocumentBlock(String _prefix, Document _doc, String _docText, String _currentUrl, RendKeyWords _rendKeyWords, BeanNatLgNames _caller, AbstractNatBlockBuilder _builder) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        int indexGlobal_ = _docText.indexOf(LT_BEGIN_TAG)+1;
        AnaRendDocumentBlock out_ = new AnaRendDocumentBlock(0,documentElement_,_docText,0, _currentUrl, new CustList<EncodedChar>());
        AnaRendBlock curWriteNat_ = newRendBlockEsc(indexGlobal_, _prefix, curNode_,_docText, _rendKeyWords, _caller, _builder);
        out_.appendChild(curWriteNat_);
        while (curWriteNat_ != null) {
            Node firstChild_ = curNode_.getFirstChild();
            if (curWriteNat_ instanceof AnaRendParentBlock &&firstChild_ != null) {
                indexGlobal_ = AnaRendBlock.indexOfBeginNode(firstChild_, _docText, indexGlobal_);
                AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_, _prefix, firstChild_,_docText, _rendKeyWords, _caller, _builder);
                ((AnaRendParentBlock) curWriteNat_).appendChild(rendBlock_);
                curWriteNat_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            tryAppendEmptyBlock(curWriteNat_);
            while (curWriteNat_ != null) {
                Node nextSibling_ = curNode_.getNextSibling();
                AnaRendParentBlock par_ = curWriteNat_.getParent();
                if (nextSibling_ != null) {
                    indexGlobal_ = AnaRendBlock.indexOfBeginNode(nextSibling_, _docText, indexGlobal_);
                    AnaRendBlock rendBlock_ = newRendBlockEsc(indexGlobal_, _prefix, nextSibling_,_docText, _rendKeyWords, _caller, _builder);
                    par_.appendChild(rendBlock_);
                    curWriteNat_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = tryGetParent(documentElement_, curNode_);
                if (parentNode_ == null) {
                    curWriteNat_ = null;
                } else {
                    indexGlobal_ = _docText.indexOf("</"+parentNode_.getTagName()+">",indexGlobal_)+2+parentNode_.getTagName().length()+2;
                    curWriteNat_ = par_;
                    curNode_ = parentNode_;
                }
            }
        }
        return out_;
    }

    public static Element tryGetParent(Element _documentElement, Node _curNode) {
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

    private static AnaRendBlock newRendBlockEsc(int _begin, String _prefix, Node _elt, String _docText, RendKeyWords _rendKeyWords, BeanNatLgNames _caller, AbstractNatBlockBuilder _builder) {
        AnaRendBlock bl_;
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            bl_ = new NatAnaRendText(new OffsetStringInfo(_begin, t_.getTextContent()), _begin);
//            int endHeader_ = _docText.indexOf(LT_BEGIN_TAG, _begin);
//            AttributePart attrPartNat_ = new AttributePart();
//            attrPartNat_.setBegin(_begin);
//            attrPartNat_.setEnd(endHeader_);
//            IntTreeMap<Integer> esc_ = AnaRendBlock.getIndexesSpecChars(_docText, false, attrPartNat_, _begin);
//            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
//            infos_.addEntry(EMPTY_STRING, esc_);
//            bl_.setEscapedChars(infos_);
        } else {
            Element elt_ = (Element) _elt;
            StringMap<AttributePart> attributes_ = getAttributes(_docText, _begin + elt_.getTagName().length(), _docText.indexOf(GT_TAG, _begin));
            bl_ = element(_begin, _prefix, (Element) _elt, _rendKeyWords, _caller, attributes_, _builder);
//            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
//            for (EntryCust<String, AttributePart> e : attributes_.entryList()) {
//                infos_.put(e.getKey(), AnaRendBlock.getIndexesSpecChars(_docText, true, e.getValue(), _begin));
//            }
//            bl_.setEscapedChars(infos_);
        }
        return bl_;
    }

    private static AnaRendParentBlock element(int _begin, String _prefix, Element _elt, RendKeyWords _rendKeyWords, BeanNatLgNames _caller, StringMap<AttributePart> _attributes, AbstractNatBlockBuilder _builder) {
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
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordForm())) {
            return new NatAnaRendForm(_elt, _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordMessage()))) {
            return new NatAnaRendMessage(_elt, _begin);
        }
        return input(_begin, _prefix, _rendKeyWords, _elt, _attributes, _builder);
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

    private static AnaRendParentBlock input(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attributes, AbstractNatBlockBuilder _builder) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSelect()))) {
            return new NatAnaRendSelect(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordInput())) {
            if (StringUtil.quickEq(_elt.getAttribute(_rendKeyWords.getAttrType()), _rendKeyWords.getValueRadio())) {
                return new NatAnaRendInput(_elt,_begin, true);
            }
            return new NatAnaRendInput(_elt,_begin, false);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordSpan()) && !_elt.getAttribute(StringUtil.concat(_prefix, _rendKeyWords.getAttrFor())).isEmpty()) {
            return new NatAnaRendSpan(_elt,_begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordAnchor()))) {
            return new NatAnaRendTitledAnchor(_elt,_begin);
        }
        return _builder.defBlock(_begin, _prefix, _rendKeyWords, _elt, _attributes);
    }

    public static AnaRendParentBlock defBlock(int _begin, String _prefix, RendKeyWords _rendKeyWords, Element _elt, StringMap<AttributePart> _attributes, AbstractNatImpLgNames _natImpLgNames) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImport()))) {
            return new NatAnaRendImport(_elt,newOffsetStringInfo(_elt, _rendKeyWords.getAttrPage(), _attributes), _begin, _natImpLgNames);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordPackage()))) {
            return new NatAnaRendPackage(newOffsetStringInfo(_elt, _rendKeyWords.getAttrName(), _attributes),
                    _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordClass()))) {
            return new NatAnaRendClass(newOffsetStringInfo(_elt, _rendKeyWords.getAttrName(), _attributes), _begin);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordField()))) {
            return new NatAnaRendField(newOffsetStringInfo(_elt, _rendKeyWords.getAttrPrepare(), _attributes), _begin);
        }
        return new NatAnaRendStdElement(_elt, _begin);
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
