package code.bean.nat.analyze.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.sml.NatAnalyzingDoc;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.sml.*;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;
import code.util.opers.*;

public final class AnaRendBlockHelp {
    public static final String LEFT_PAR = "(";
    public static final String RIGHT_PAR = ")";
    public static final String COMMA = ",";
    static final String TMP_LOC = "_";
    static final String EMPTY_STRING = "";

    private AnaRendBlockHelp() {
    }

    public static void buildFctInstructions(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page, StringMap<String> _beansInfosBefore) {
        _doc.setBeanName(_doc.getElt().getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrBean())));
        String clName_ = _beansInfosBefore.getVal(_doc.getBeanName());
        _page.setGlobalType(clName_);
        loop(_doc, _anaDoc, _page);
    }

    public static void loop(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
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

    private static void removeAllVars(NatAnaRendParentBlock _par, StringMap<String> _infosVars, StringMap<String> _loopsVars) {
        if (_par instanceof NatAnaRendForEachLoop) {
            ((NatAnaRendForEachLoop)_par).removeVars(_infosVars, _loopsVars);
        }
        if (_par instanceof NatAnaRendForEachTable) {
            ((NatAnaRendForEachTable)_par).removeVars(_infosVars, _loopsVars);
        }
    }
    public static StringMap<String> getPre(String _value, NatAnalyzingDoc _analyzingDoc) {
        StringList elts_ = StringUtil.splitStrings(_value, COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(var_, _analyzingDoc);
        StringMap<String> pres_ = new StringMap<String>();
        for (String l: _analyzingDoc.getLanguages()) {
            StringMap<TranslationsFile> files_ = _analyzingDoc.getApplis().getVal(l).getMapping();
            TranslationsFile content_ = tryGetContent(fileName_, files_);
            String key_ = elts_.last();
            String format_ = content_.getMapping().getVal(key_);
            pres_.addEntry(l,format_);
        }
        return pres_;
    }

    public static TranslationsFile file(String _content) {
        TranslationsFile t_ = new TranslationsFile();
        for (EntryCust<String,String> e: MessagesUtil.getMessages(_content).entryList()) {
            t_.add(e.getKey(),e.getValue());
        }
        return t_;
    }
    static String getProperty(String _key, NatAnalyzingDoc _anaDoc) {
        return _anaDoc.getProperties().getVal(_key);
    }

    public static NatAnaRendDocumentBlock newRendDocumentBlock(String _prefix, Document _doc, RendKeyWordsGroup _rendKeyWords, BeanNatCommonLgNames _caller, AbstractNatBlockBuilder _builder) {
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

    private static NatAnaRendBlock newRendBlockEsc(String _prefix, Node _elt, RendKeyWordsGroup _rendKeyWords, BeanNatCommonLgNames _caller, AbstractNatBlockBuilder _builder) {
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

    private static NatAnaRendBlock element(String _prefix, Element _elt, RendKeyWordsGroup _rendKeyWords, BeanNatCommonLgNames _caller, AbstractNatBlockBuilder _builder) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordFor()))) {
            return collection(_rendKeyWords, _caller, _elt);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordIf()))) {
            return new NatAnaRendIfCondition(newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrCondition())
            );
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordElseif()))) {
            return new NatAnaRendElseIfCondition(newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrCondition())
            );
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordElse()))) {
            return new NatAnaRendParentBlock(true);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordsTags().getKeyWordImg())) {
            return new NatAnaRendImg(_elt,_builder);
        }
        if (StringUtil.quickEq(tagName_, _rendKeyWords.getKeyWordsTags().getKeyWordLink())) {
            return new NatAnaRendLink(_elt,_builder);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordImg()))) {
            return new NatAnaRendEscImg(_elt,_builder);
        }
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordMessage()))) {
            return new NatAnaRendMessage(_elt);
        }
        return input(_prefix, _rendKeyWords, _elt, _builder,_caller);
    }

    private static NatAnaRendParentBlock collection(RendKeyWordsGroup _rendKeyWords, BeanNatCommonLgNames _caller, Element _elt) {
        if (_elt.hasAttribute(_rendKeyWords.getKeyWordsAttrs().getAttrList())) {
            return new NatAnaRendForEachLoop(
                    newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrClassName()),
                    newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrVar()),
                    newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrList()),
                    _caller
            );
        }
        return new NatAnaRendForEachTable(
                newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrKeyClassName()),
                newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrKey()),
                newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrVarClassName()),
                newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrValue()),
                newOffsetStringInfo(_elt, _rendKeyWords.getKeyWordsAttrs().getAttrMap())
        );
    }

    private static NatAnaRendBlock input(String _prefix, RendKeyWordsGroup _rendKeyWords, Element _elt, AbstractNatBlockBuilder _builder, BeanNatCommonLgNames _caller) {
        String tagName_ = _elt.getTagName();
        if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordImport()))) {
            return new NatAnaRendImport(_elt, _caller);
        }
        return _builder.defBlock(_prefix, _rendKeyWords, _elt);
    }

    private static String newOffsetStringInfo(Element _elt, String _key) {
        return _elt.getAttribute(_key);
    }

    public static TranslationsFile tryGetContent(String _relative, StringMap<TranslationsFile> _files) {
        return _files.getVal(_relative);
    }
    static String getCssHref(Element _link, RendKeyWordsGroup _rendKeyWords) {
        return _link.getAttribute(_rendKeyWords.getKeyWordsAttrs().getAttrHref());
    }

}
