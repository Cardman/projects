package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendForm extends NatAnaRendElement {
    private CustList<NatOperationNode> roots;
    private NatOperationNode root;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    NatAnaRendForm(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrAction());
        roots = new CustList<NatOperationNode>();
        String href_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        NatResultText r_ = new NatResultText();
        if (href_.startsWith(AnaRendBlockHelp.CALL_METHOD)) {
            String lk_ = href_.substring(1);
            r_.buildAna(lk_, _anaDoc, _page);
            texts = r_.getTexts();
            roots = r_.getOpExpRoot();
            StringList formArg_ = new StringList();
            varNames = new StringList();
            String pref_ = r_.quickRender(lk_, formArg_);
            pref_ = StringUtil.concat(pref_, AnaRendBlock.LEFT_PAR,AnaRendBlock.RIGHT_PAR);
            root = NatRenderAnalysis.getRootAnalyzedOperations(pref_, 0, _anaDoc, _page);
        }
    }

    public StringList getTexts() {
        return texts;
    }

    public NatOperationNode getRoot() {
        return root;
    }

    public StringList getVarNames() {
        return varNames;
    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }
}
