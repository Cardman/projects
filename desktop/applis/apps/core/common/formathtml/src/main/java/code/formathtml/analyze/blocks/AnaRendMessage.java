package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.sml.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class AnaRendMessage extends AnaRendParentBlock implements AnaRendBuildEl {

    private final Element elt;
    private CustList<OperationNode> roots;

    private StringMap<String> preformatted;
    private final CustList<BoolVal> quoted = new CustList<BoolVal>();
    private final CustList<BoolVal> escaped = new CustList<BoolVal>();
    private final StringMap<CustList<OperationNode>> callsRoots = new StringMap<CustList<OperationNode>>();
    private final StringList args = new StringList();
    private final StringMap<Document> locDoc = new StringMap<Document>();
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
            if (e.getRead().hasAttribute(_anaDoc.getRendKeyWords().getAttrQuoted())) {
                String attribute_ = e.getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
                quoted.add(BoolVal.TRUE);
                escapeQuoted(_anaDoc, e, attribute_);
                roots.add(null);
                continue;
            }
            args.add(EMPTY_STRING);
            quoted.add(BoolVal.FALSE);
            escape(_anaDoc, e);
            ResultExpression res_ = resultExpressionList.get(index_);
            _page.setSumOffset(res_.getSumOffset());
            _page.zeroOffset();
            roots.add(RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,res_));
            index_++;
        }
        //if (!element_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
        if (!elt.getAttribute(_anaDoc.getRendKeyWords().getAttrEscaped()).isEmpty()) {
            return;
        }
        String lt_ = Character.toString(LT_BEGIN_TAG);
        String gt_ = Character.toString(GT_TAG);
        StringList formArg_ = new StringList();
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

    }

    private void escape(AnalyzingDoc _anaDoc, AnaRendElement _e) {
        if (_e.getRead().hasAttribute(_anaDoc.getRendKeyWords().getAttrEscaped())) {
            escaped.add(BoolVal.TRUE);
        } else {
            escaped.add(BoolVal.FALSE);
        }
    }

    private void escapeQuoted(AnalyzingDoc _anaDoc, AnaRendElement _e, String _attr) {
        if (_e.getRead().hasAttribute(_anaDoc.getRendKeyWords().getAttrEscaped())) {
            args.add(escapeParam(_attr));
            escaped.add(BoolVal.TRUE);
        } else {
            args.add(_attr);
            escaped.add(BoolVal.FALSE);
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

    public CustList<BoolVal> getEscaped() {
        return escaped;
    }

    public CustList<BoolVal> getQuoted() {
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
