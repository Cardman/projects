package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.Element;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendMessage extends AnaRendParentBlock implements AnaRendBuildEl {

    private final Element elt;
    private CustList<OperationNode> roots;

    private StringMap<String> preformatted;
    private final CustList<Boolean> quoted = new CustList<Boolean>();
    private final CustList<Boolean> escaped = new CustList<Boolean>();
    private final StringMap<CustList<OperationNode>> callsRoots = new StringMap<CustList<OperationNode>>();
    private final StringList args = new StringList();
    private final StringMap<Document> locDoc = new StringMap<Document>();
    private StringList varNames = new StringList();

    AnaRendMessage(Element _elt, int _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        roots = new CustList<OperationNode>();
        String value_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        int offMessage_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrValue());
        preformatted = getPre(value_,offMessage_, _anaDoc, _page);
        if (preformatted.isEmpty()) {
            return;
        }
        for (Element n: elt.getChildElements()) {
            String attribute_ = n.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
            if (n.hasAttribute(_anaDoc.getRendKeyWords().getAttrQuoted())) {
                quoted.add(true);
                if (n.hasAttribute(_anaDoc.getRendKeyWords().getAttrEscaped())) {
                    args.add(escapeParam(attribute_));
                    escaped.add(true);
                } else {
                    args.add(attribute_);
                    escaped.add(false);
                }
                roots.add(null);
                continue;
            }
            args.add(EMPTY_STRING);
            quoted.add(false);
            if (n.hasAttribute(_anaDoc.getRendKeyWords().getAttrEscaped())) {
                escaped.add(true);
            } else {
                escaped.add(false);
            }
            roots.add(RenderAnalysis.getRootAnalyzedOperations(attribute_, 0, _anaDoc, _page));
        }
        //if (!element_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
        if (elt.getAttribute(_anaDoc.getRendKeyWords().getAttrEscaped()).isEmpty()) {
            String lt_ = Character.toString(LT_BEGIN_TAG);
            String gt_ = Character.toString(GT_TAG);
            int l_ = roots.size();
            StringList formArg_ = new StringList();
            StringList varNames_ = new StringList();
            for (int i = 0; i< l_; i++) {
                String varLoc_ = lookForVar(varNames_, _page);
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
                String concat_ = StringUtil.concat(lt_,TMP_BLOCK_TAG,gt_,e.getValue(),LT_END_TAG,TMP_BLOCK_TAG,gt_);
                preRend_=StringUtil.simpleStringsFormat(concat_, formArg_);
                DocumentResult res2_ = DocumentBuilder.parseSaxNotNullRowCol(preRend_);
                Document docLoc2_ = res2_.getDocument();
                CustList<OperationNode> callExpsLoc_ = new CustList<OperationNode>();
                if (docLoc2_ != null) {
                    for (Element a: docLoc2_.getElementsByTagName(_anaDoc.getRendKeyWords().getKeyWordAnchor())){
                        String href_ = a.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
                        if (href_.startsWith(CALL_METHOD)) {
                            if (href_.indexOf('(') == IndexConstants.INDEX_NOT_FOUND_ELT) {
                                href_ = StringUtil.concat(href_,AnaRendBlock.LEFT_PAR,AnaRendBlock.RIGHT_PAR);
                            }
                            callExpsLoc_.add(RenderAnalysis.getRootAnalyzedOperations(href_, 1, _anaDoc, _page));
                        } else {
                            callExpsLoc_.add(null);
                        }
                    }
                }
                DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
                Document docLoc_ = res_.getDocument();
                if (docLoc_ == null) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(offMessage_);
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadDocument(),
                            res_.getLocation().display());
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
                callsRoots.addEntry(e.getKey(),callExpsLoc_);
                locDoc.addEntry(e.getKey(),docLoc_);
            }
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }

        }
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public StringMap<CustList<OperationNode>> getCallsRoots() {
        return callsRoots;
    }

    public StringList getVarNames() {
        return varNames;
    }

    public CustList<Boolean> getEscaped() {
        return escaped;
    }

    public CustList<Boolean> getQuoted() {
        return quoted;
    }

    public StringList getArgs() {
        return args;
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

    public Element getElt() {
        return elt;
    }

    public StringMap<Document> getLocDoc() {
        return locDoc;
    }
}
