package code.bean.nat.analyze.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.errors.RendKeyWords;
import code.sml.Element;
import code.util.CustList;
import code.util.core.StringUtil;

public final class NatAnaRendImport extends NatAnaRendParentBlock implements NatRendBuildEl {
    private final Element elt;

    private NatOperationNode roots;

    private final AbstractNatImpLgNames natImpLgNames;

    private final CustList<NatOperationNode> fields = new CustList<NatOperationNode>();
    NatAnaRendImport(Element _elt, AbstractNatImpLgNames _natImpLgNames) {
        super();
        elt = _elt;
        natImpLgNames = _natImpLgNames;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        RendKeyWords rendKeyWords_ = _anaDoc.getRendKeyWords();
        String pageName_ = elt.getAttribute(rendKeyWords_.getAttrPage());
        roots = NatRenderAnalysis.getRootAnalyzedOperations(pageName_,0, _anaDoc, _page);
        for (Element p: elt.getChildElements()) {
            String name_ = p.getAttribute(rendKeyWords_.getAttrName());
            for (Element c: p.getChildElements()) {
                String fullName_ = StringUtil.concat(name_, ".", c.getAttribute(rendKeyWords_.getAttrName()));
                for (Element f: c.getChildElements()) {
                    _anaDoc.setInternGlobalClass(fullName_);
                    String attribute_ = f.getAttribute(rendKeyWords_.getAttrPrepare());
                    if (!attribute_.isEmpty()) {
                        fields.add(NatRenderAnalysis.getRootAnalyzedOperations(attribute_, 0, _anaDoc, _page));
                        _anaDoc.setInternGlobalClass(AnaRendBlockHelp.EMPTY_STRING);
                    }
                }
            }
        }
    }

    public CustList<NatOperationNode> getFields() {
        return fields;
    }

    public AbstractNatImpLgNames getNatImpLgNames() {
        return natImpLgNames;
    }

    public NatOperationNode getRoots() {
        return roots;
    }
}
