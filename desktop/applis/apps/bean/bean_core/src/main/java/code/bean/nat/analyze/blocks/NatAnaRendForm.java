package code.bean.nat.analyze.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendForm extends NatAnaRendElement {
    private NatOperationNode root;

    private StringList varNames = new StringList();
    NatAnaRendForm(Element _elt) {
        super(_elt);
    }

    void form(Element _read, StringList _list, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrAction());
        String href_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        NatResultText r_ = new NatResultText();
        String lk_ = href_.substring(1);
        r_.buildAna(lk_, _anaDoc, _page);
        StringList formArg_ = new StringList();
        varNames = new StringList();
        String pref_ = r_.quickRender(lk_, formArg_);
        pref_ = StringUtil.concat(pref_, AnaRendBlock.LEFT_PAR,AnaRendBlock.RIGHT_PAR);
        String bean_ = _read.getOwnerDocument().getDocumentElement().getAttribute(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrBean()));
        root = NatRenderAnalysis.getRootAnalyzedOperations(pref_, 0, _anaDoc, _page);
        String tmp_ = bean_+'.'+BeanNatCommonLgNames.methName(pref_);
        _read.setAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()),tmp_);
    }

    public NatOperationNode getRoot() {
        return root;
    }

    public StringList getVarNames() {
        return varNames;
    }

}
