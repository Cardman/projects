package code.bean.nat.analyze.blocks;

import code.sml.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class NatAnaRendElementSpec extends NatAnaRendParentBlock implements NatRendBuildEl {
    private final Element read;
    private final StringMap<NatResultText> attributes = new StringMap<NatResultText>();
    private final StringMap<NatResultText> attributesText = new StringMap<NatResultText>();

    NatAnaRendElementSpec(Element _elt) {
        read = _elt;
    }

    public static NatResultTextForm buildAnchor(Element _read, StringList _list, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _list.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrHref());
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrCommand()));
        String href_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrCommand()));
        NatResultTextForm r_ = new NatResultTextForm();
        r_.setOpExpRoot(new CustList<NatOperationNode>());
        String lk_ = href_.substring(1);
        r_.buildAna(lk_, _anaDoc, _page);
        CustList<NatOperationNode> opExpRoot_ = r_.getOpExpRoot();
        int l_ = opExpRoot_.size();
        StringList formArg_ = new StringList();
        StringList varNames_ = new StringList();
        for (int i = 0; i< l_; i++) {
            String varLoc_ = lookForVar(varNames_);
            varNames_.add(varLoc_);
        }
        r_.setVarNames(varNames_);
        int i_ = 0;
        for (String v:varNames_) {
            _page.getInfosVars().addEntry(v,opExpRoot_.get(i_).getNames());
            formArg_.add(StringUtil.concat(AnaRendBlockHelp.LEFT_PAR, v,AnaRendBlockHelp.RIGHT_PAR));
            i_++;
        }
        String pref_ = r_.quickRender(lk_, formArg_);
        int left_ = lk_.indexOf('(');
        String right_ = lk_.substring(0, left_);
        String bean_ = _read.getOwnerDocument().getDocumentElement().getAttribute(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrBean()));
        String tmp_ = bean_+'.'+right_;
        r_.setOpExpAnchorRoot(NatRenderAnalysis.getRootAnalyzedOperations(pref_, 0, _anaDoc, _page));
        _read.setAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrCommand()),tmp_);
        for (String v:varNames_) {
            _page.getInfosVars().removeKey(v);
        }
        return r_;
    }

    public static String lookForVar(StringList _varNames) {
        String varLoc_ = AnaRendBlockHelp.TMP_LOC;
        int indexLoc_ = 0;
        while (StringUtil.contains(_varNames,varLoc_)) {
            varLoc_ = StringUtil.concatNbs(AnaRendBlockHelp.TMP_LOC,indexLoc_);
            indexLoc_++;
        }
        return varLoc_;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String prefixWrite_ = _anaDoc.getPrefix();
        StringList attributesNames_ = NatAnaRendElement.buildAttrNames(_anaDoc, read);
        String id_ = read.getAttribute(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrId());
        if (!id_.isEmpty()) {
            NatResultTextForm r_ = new NatResultTextForm();
            r_.buildAna(id_, _anaDoc, _page);
            attributesText.put(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrId(),r_);
        }
        String prefGr_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrGroupId());
        attributesNames_.removeAllString(prefGr_);
        String groupId_ = read.getAttribute(prefGr_);
        if (!groupId_.isEmpty()) {
            NatResultTextForm r_ = new NatResultTextForm();
            r_.buildIdAna(groupId_, _anaDoc, _page);
            attributesText.put(prefGr_,r_);
        }
        if (this instanceof NatAnaRendAnchor) {
            ((NatAnaRendAnchor)this).anchor(read,attributesNames_, _anaDoc, _page);
        } else if (this instanceof NatAnaRendForm) {
            ((NatAnaRendForm)this).form(read,attributesNames_, _anaDoc, _page);
        } else if (this instanceof NatAnaRendInput) {
            ((NatAnaRendInput)this).input(read,attributesNames_, _anaDoc, _page);
//        } else if (this instanceof NatAnaRendSpan) {
//            ((NatAnaRendSpan)this).span(read,attributesNames_, _anaDoc, _page);
        } else if (this instanceof NatAnaRendSubmit) {
            ((NatAnaRendSubmit)this).submit(read,attributesNames_, _anaDoc);
        } else if (this instanceof NatAnaRendTitledAnchor){
            ((NatAnaRendTitledAnchor)this).titled(read,attributesNames_, _anaDoc, _page);
        }
        for (String a: attributesNames_) {
            String attr_ = read.getAttribute(a);
            NatResultText r_ = new NatResultTextForm();
            r_.buildIdAna(attr_, _anaDoc, _page);
            attributes.addEntry(a,r_);
        }
    }

    public final Element getRead() {
        return read;
    }

    public StringMap<NatResultText> getAttributes() {
        return attributes;
    }

    public StringMap<NatResultText> getAttributesText() {
        return attributesText;
    }
}
