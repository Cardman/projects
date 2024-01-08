package code.bean.nat.analyze.blocks;

import code.bean.nat.*;
import code.sml.NatAnalyzingDoc;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.sml.Element;
import code.sml.RendKeyWordsGroup;
import code.util.CustList;
import code.util.core.StringUtil;

public final class NatAnaRendImport extends NatAnaRendParentBlock implements NatRendBuildEl {
    private final Element elt;

    private NatOperationNode roots;

    private final BeanNatCommonLgNames natImpLgNames;

    private final CustList<NatOperationNode> fields = new CustList<NatOperationNode>();
    public NatAnaRendImport(Element _elt, BeanNatCommonLgNames _natImpLgNames) {
        elt = _elt;
        natImpLgNames = _natImpLgNames;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        RendKeyWordsGroup rendKeyWords_ = _anaDoc.getRendKeyWords();
        String pageName_ = elt.getAttribute(rendKeyWords_.getKeyWordsAttrs().getAttrPage());
        roots = NatRenderAnalysis.getRootAnalyzedOperations(pageName_,0, _anaDoc, _page);
        for (Element p: elt.getChildElements()) {
            String name_ = p.getAttribute(rendKeyWords_.getKeyWordsAttrs().getAttrName());
            for (Element c: p.getChildElements()) {
                String fullName_ = StringUtil.concat(name_, ".", c.getAttribute(rendKeyWords_.getKeyWordsAttrs().getAttrName()));
                for (Element f: c.getChildElements()) {
                    _anaDoc.setInternGlobalClass(fullName_);
                    String attribute_ = f.getAttribute(rendKeyWords_.getKeyWordsAttrs().getAttrPrepare());
                    fields.add(NatRenderAnalysis.getRootAnalyzedOperations(attribute_, 0, _anaDoc, _page));
                    _anaDoc.setInternGlobalClass(AnaRendBlockHelp.EMPTY_STRING);
                }
            }
        }
    }

    public CustList<NatOperationNode> getFields() {
        return fields;
    }

    public BeanNatCommonLgNames getNatImpLgNames() {
        return natImpLgNames;
    }

    public NatOperationNode getRoots() {
        return roots;
    }
}
