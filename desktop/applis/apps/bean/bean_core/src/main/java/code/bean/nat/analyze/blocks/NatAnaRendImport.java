package code.bean.nat.analyze.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.errors.RendKeyWords;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendImport extends NatAnaRendLeaf implements NatRendBuildEl {
    private final Element elt;

    private CustList<NatOperationNode> roots;

    private StringList texts = new StringList();

    private final AbstractNatImpLgNames natImpLgNames;

    private final CustList<NatOperationNode> fields = new CustList<NatOperationNode>();
    NatAnaRendImport(Element _elt, AbstractNatImpLgNames _natImpLgNames) {
        super();
        elt = _elt;
        natImpLgNames = _natImpLgNames;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatResultText res_ = new NatResultText();
        RendKeyWords rendKeyWords_ = _anaDoc.getRendKeyWords();
        String pageName_ = elt.getAttribute(rendKeyWords_.getAttrPage());
        res_.buildAna(pageName_, _anaDoc, _page);
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
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

    public StringList getTexts() {
        return texts;
    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }
}
