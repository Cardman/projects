package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatRenderAnalysis;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.sml.Element;
import code.sml.NatAnalyzingDoc;
import code.util.StringList;

public final class NatAnaRendEscImg extends NatAnaRendElement {

    private NatOperationNode root;
    public NatAnaRendEscImg(Element _elt, AbstractNatBlockBuilder _builder) {
        super(_elt,_builder);
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        StringList attributesNames_ = buildAttrNames(_anaDoc, getRead());
        attributesNames_.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrSrc());
        root = NatRenderAnalysis.getRootAnalyzedOperations(getRead().getAttribute(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrSrc()),0,_anaDoc,_page);
        attributes(_anaDoc, _page, attributesNames_);
    }

    public NatOperationNode getRoot() {
        return root;
    }
}
