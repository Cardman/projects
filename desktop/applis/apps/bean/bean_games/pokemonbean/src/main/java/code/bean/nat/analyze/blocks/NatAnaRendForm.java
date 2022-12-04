package code.bean.nat.analyze.blocks;

import code.sml.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendForm extends NatAnaRendElementSpec {
    private NatOperationNode root;

    private StringList varNames = new StringList();
    public NatAnaRendForm(Element _elt) {
        super(_elt);
    }

    void form(Element _read, StringList _list, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrCommand()));
        _list.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrAction());
        String href_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrCommand()));
        String lk_ = href_.substring(1);
        varNames = new StringList();
        String pref_ = StringUtil.concat(lk_, AnaRendBlockHelp.LEFT_PAR,AnaRendBlockHelp.RIGHT_PAR);
        String bean_ = _read.getOwnerDocument().getDocumentElement().getAttribute(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrBean()));
        root = NatRenderAnalysis.getRootAnalyzedOperations(pref_, 0, _anaDoc, _page);
        String tmp_ = bean_+'.'+lk_;
        _read.setAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrCommand()),tmp_);
    }

    public NatOperationNode getRoot() {
        return root;
    }

    public StringList getVarNames() {
        return varNames;
    }

}
