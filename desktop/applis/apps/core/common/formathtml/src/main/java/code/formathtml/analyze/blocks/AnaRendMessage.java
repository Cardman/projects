package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.*;
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
    private final CustList<ResultExpression> resultExpressionList = new CustList<ResultExpression>();
    private final CustList<AnaRendElement> children = new CustList<AnaRendElement>();


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
        int index_ = 0;
        for (AnaRendElement e: children) {
            int attributeDelimiter_ = e.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrValue());
            String attribute_ = e.getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
            if (e.getRead().hasAttribute(_anaDoc.getRendKeyWords().getAttrQuoted())) {
                quoted.add(true);
                if (e.getRead().hasAttribute(_anaDoc.getRendKeyWords().getAttrEscaped())) {
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
            if (e.getRead().hasAttribute(_anaDoc.getRendKeyWords().getAttrEscaped())) {
                escaped.add(true);
            } else {
                escaped.add(false);
            }
            ResultExpression res_ = resultExpressionList.get(index_);
            _page.setGlobalOffset(attributeDelimiter_);
            _page.zeroOffset();
            roots.add(RenderAnalysis.getRootAnalyzedOperations(attribute_, 0, _anaDoc, _page,res_));
            index_++;
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
                    ElementList anc_ = docLoc2_.getElementsByTagName(_anaDoc.getRendKeyWords().getKeyWordAnchor());
                    int nb_ = anc_.size();
                    for (int i = 0; i < nb_; i++) {
                        callExpsLoc_.add(null);
                    }
                }
                DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(concat_);
                Document docLoc_ = res_.getDocument();
                if (docLoc_ == null) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(offMessage_);
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadDocument(),
                            res_.getLocation().display());
                    AnalyzingDoc.addError(badEl_, _page);
                }
                callsRoots.addEntry(e.getKey(),callExpsLoc_);
                locDoc.addEntry(e.getKey(),docLoc_);
            }
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }

        }
    }

    public CustList<ResultExpression> getResultExpressionList() {
        return resultExpressionList;
    }

    public CustList<AnaRendElement> getChildren() {
        return children;
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
