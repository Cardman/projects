package code.bean.nat.analyze.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.structs.BeanInfo;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.sml.Text;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendBlockHelp {
    static final String CALL_METHOD = "$";
    static final String TMP_LOC = "tmpLoc";
    static final String EMPTY_STRING = "";

    private AnaRendBlockHelp() {
    }

    public static void buildFctInstructions(NatAnaRendDocumentBlock _doc,AnalyzingDoc _anaDoc, NatAnalyzedCode _page, StringMap<BeanInfo> _beansInfosBefore) {
        _doc.setBeanName(_doc.getElt().getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean())));
        String clName_ = _beansInfosBefore.getVal(_doc.getBeanName()).getResolvedClassName();
        AnaFormattedRootBlock globalType_ = new AnaFormattedRootBlock((RootBlock) null, clName_);
        _page.setGlobalType(globalType_);
        loop(_doc, _anaDoc, _page);
    }

    public static void loop(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatAnaRendBlock enNat_ = _doc;
        while (true) {
            NatAnaRendBlock n_ = enNat_.getFirstChild();
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
                NatAnaRendParentBlock par_;
                par_ = enNat_.getParent();
                removeAllVars(par_, _page.getInfosVars(), _page.getLoopsVars());
                if (par_ == _doc) {
                    return;
                }
                enNat_ = par_;
            }
        }
    }

    private static void removeAllVars(NatAnaRendParentBlock _par, StringMap<AnaLocalVariable> _infosVars, StringMap<AnaLoopVariable> _loopsVars) {
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

    public static NatAnaRendDocumentBlock newRendDocumentBlock(String _prefix, Document _doc, RendKeyWords _rendKeyWords, BeanNatCommonLgNames _caller, AbstractNatBlockBuilder _builder) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        NatAnaRendDocumentBlock out_ = new NatAnaRendDocumentBlock(documentElement_);
        NatAnaRendBlock curWriteNat_ = newRendBlockEsc(_prefix, curNode_, _rendKeyWords, _caller, _builder);
        out_.appendChild(curWriteNat_);
        while (curWriteNat_ != null) {
            Node firstChild_ = curNode_.getFirstChild();
            if (curWriteNat_ instanceof NatAnaRendParentBlock &&firstChild_ != null) {
                NatAnaRendBlock rendBlock_ = newRendBlockEsc(_prefix, firstChild_, _rendKeyWords, _caller, _builder);
                ((NatAnaRendParentBlock) curWriteNat_).appendChild(rendBlock_);
                curWriteNat_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            tryAppendEmptyBlock(curWriteNat_);
            while (curWriteNat_ != null) {
                Node nextSibling_ = curNode_.getNextSibling();
                NatAnaRendParentBlock par_ = curWriteNat_.getParent();
                if (nextSibling_ != null) {
                    NatAnaRendBlock rendBlock_ = newRendBlockEsc(_prefix, nextSibling_, _rendKeyWords, _caller, _builder);
                    par_.appendChild(rendBlock_);
                    curWriteNat_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = tryGetParent(documentElement_, curNode_);
                if (parentNode_ == null) {
                    curWriteNat_ = null;
                } else {
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

    private static void tryAppendEmptyBlock(NatAnaRendBlock _curWrite) {
        if (_curWrite instanceof NatAnaRendParentBlock) {
            NatAnaRendEmptyInstruction empty_ = new NatAnaRendEmptyInstruction();
            ((NatAnaRendParentBlock) _curWrite).appendChild(empty_);
        }
    }

    private static NatAnaRendBlock newRendBlockEsc(String _prefix, Node _elt, RendKeyWords _rendKeyWords, BeanNatCommonLgNames _caller, AbstractNatBlockBuilder _builder) {
        NatAnaRendBlock bl_;
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            bl_ = new NatAnaRendText(t_.getTextContent());
//            int endHeader_ = _docText.indexOf(LT_BEGIN_TAG, _begin);
//            AttributePart attrPartNat_ = new AttributePart();
//            attrPartNat_.setBegin(_begin);
//            attrPartNat_.setEnd(endHeader_);
//            IntTreeMap<Integer> esc_ = AnaRendBlock.getIndexesSpecChars(_docText, false, attrPartNat_, _begin);
//            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
//            infos_.addEntry(EMPTY_STRING, esc_);
//            bl_.setEscapedChars(infos_);
        } else {
            bl_ = element(_prefix, (Element) _elt, _rendKeyWords, _caller, _builder);
//            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
//            for (EntryCust<String, AttributePart> e : attributes_.entryList()) {
//                infos_.put(e.getKey(), AnaRendBlock.getIndexesSpecChars(_docText, true, e.getValue(), _begin));
//            }
//            bl_.setEscapedChars(infos_);
        }
        return bl_;
    }

    private static NatAnaRendBlock element(String _prefix, Element _elt, RendKeyWords _rendKeyWords, BeanNatCommonLgNames _caller, AbstractNatBlockBuilder _builder) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordFor()))) {
            return collection(_rendKeyWords, _caller, _elt);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordIf()))) {
            return new NatAnaRendIfCondition(newOffsetStringInfo(_elt, _rendKeyWords.getAttrCondition())
            );
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordElseif()))) {
            return new NatAnaRendElseIfCondition(newOffsetStringInfo(_elt, _rendKeyWords.getAttrCondition())
            );
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordElse()))) {
            return new NatAnaRendElseCondition();
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSubmit()))) {
            return new NatAnaRendSubmit(_elt);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordAnchor())) {
            return new NatAnaRendAnchor(_elt);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordImg())) {
            return new NatAnaRendImg(_elt);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordLink())) {
            return new NatAnaRendLink(_elt);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImg()))) {
            return new NatAnaRendEscImg(_elt);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordForm())) {
            return new NatAnaRendForm(_elt);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordMessage()))) {
            return new NatAnaRendMessage(_elt);
        }
        return input(_prefix, _rendKeyWords, _elt, _builder);
    }

    private static NatAnaRendParentBlock collection(RendKeyWords _rendKeyWords, BeanNatCommonLgNames _caller, Element _elt) {
        if (_elt.hasAttribute(_rendKeyWords.getAttrList())) {
            return new NatAnaRendForEachLoop(
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrClassName()),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrVar()),
                    newOffsetStringInfo(_elt, _rendKeyWords.getAttrList()),
                    _caller
            );
        }
        return new NatAnaRendForEachTable(
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrKeyClassName()),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrKey()),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrVarClassName()),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrValue()),
                newOffsetStringInfo(_elt, _rendKeyWords.getAttrMap())
        );
    }

    private static NatAnaRendBlock input(String _prefix, RendKeyWords _rendKeyWords, Element _elt, AbstractNatBlockBuilder _builder) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordSelect()))) {
            return new NatAnaRendSelect(_elt);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordInput())) {
            if (StringUtil.quickEq(_elt.getAttribute(_rendKeyWords.getAttrType()), _rendKeyWords.getValueRadio())) {
                return new NatAnaRendInput(_elt, true);
            }
            return new NatAnaRendInput(_elt, false);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordSpan()) && !_elt.getAttribute(StringUtil.concat(_prefix, _rendKeyWords.getAttrFor())).isEmpty()) {
            return new NatAnaRendSpan(_elt);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordAnchor()))) {
            return new NatAnaRendTitledAnchor(_elt);
        }
        return _builder.defBlock(_prefix, _rendKeyWords, _elt);
    }

    public static NatAnaRendBlock defBlock(String _prefix, RendKeyWords _rendKeyWords, Element _elt, AbstractNatImpLgNames _natImpLgNames) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordImport()))) {
            return new NatAnaRendImport(_elt, _natImpLgNames);
        }
        return new NatAnaRendStdElement(_elt);
    }

    private static String newOffsetStringInfo(Element _elt, String _key) {
        return _elt.getAttribute(_key);
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
