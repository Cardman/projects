package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.bean.nat.analyze.NatRenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendBuildEl;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.Element;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NatAnaRendMessage extends AnaRendParentBlock implements AnaRendBuildEl {

    private final Element elt;
    private CustList<NatOperationNode> roots;

    private StringMap<String> preformatted;
    private final StringMap<CustList<NatOperationNode>> callsRoots = new StringMap<CustList<NatOperationNode>>();
    private final StringList args = new StringList();
    private StringList varNames = new StringList();

    NatAnaRendMessage(Element _elt, int _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        roots = new CustList<NatOperationNode>();
        String value_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        preformatted = AnaRendBlockHelp.getPre(value_, _anaDoc);
        for (Element n: elt.getChildElements()) {
            String attribute_ = n.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
            args.add(AnaRendBlockHelp.EMPTY_STRING);
            roots.add(NatRenderAnalysis.getRootAnalyzedOperations(attribute_, 0, _anaDoc, _page));
        }
        //if (!element_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
        String lt_ = Character.toString(AnaRendBlockHelp.LT_BEGIN_TAG);
        String gt_ = Character.toString(AnaRendBlockHelp.GT_TAG);
        int l_ = roots.size();
        StringList formArg_ = new StringList();
        StringList varNames_ = new StringList();
        for (int i = 0; i< l_; i++) {
            String varLoc_ = AnaRendBlockHelp.lookForVar(varNames_);
            varNames_.add(varLoc_);
        }
        varNames = varNames_;
        for (String v:varNames_) {
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(_page.getAliasPrimInteger());
            _page.getInfosVars().addEntry(v,lv_);
            formArg_.add(StringUtil.concat(AnaRendBlock.LEFT_PAR, v,AnaRendBlock.RIGHT_PAR));
        }
        for (EntryCust<String,String> e: preformatted.entryList()) {
            String preRend_;
            String concat_ = StringUtil.concat(lt_,AnaRendBlockHelp.TMP_BLOCK_TAG,gt_,e.getValue(),AnaRendBlockHelp.LT_END_TAG,AnaRendBlockHelp.TMP_BLOCK_TAG,gt_);
            preRend_=StringUtil.simpleStringsFormat(concat_, formArg_);
            DocumentResult res2_ = DocumentBuilder.parseSaxNotNullRowCol(preRend_);
            Document docLoc2_ = res2_.getDocument();
            CustList<NatOperationNode> callExpsLoc_ = new CustList<NatOperationNode>();
            for (Element a: docLoc2_.getElementsByTagName(_anaDoc.getRendKeyWords().getKeyWordAnchor())){
                String href_ = a.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
                if (href_.startsWith(AnaRendBlockHelp.CALL_METHOD)) {
                    if (href_.indexOf('(') == IndexConstants.INDEX_NOT_FOUND_ELT) {
                        href_ = StringUtil.concat(href_,AnaRendBlock.LEFT_PAR,AnaRendBlock.RIGHT_PAR);
                    }
                    callExpsLoc_.add(NatRenderAnalysis.getRootAnalyzedOperations(href_, 1, _anaDoc, _page));
                } else {
                    callExpsLoc_.add(null);
                }
            }
            callsRoots.addEntry(e.getKey(),callExpsLoc_);
        }
        for (String v:varNames_) {
            _page.getInfosVars().removeKey(v);
        }

    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }

    public StringMap<CustList<NatOperationNode>> getCallsRoots() {
        return callsRoots;
    }

    public StringList getVarNames() {
        return varNames;
    }

    public StringList getArgs() {
        return args;
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

}
