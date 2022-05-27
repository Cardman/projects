package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;

public final class ResultParsedAnnots {
    private CustList<ResultParsedAnnot> annotations = new CustList<ResultParsedAnnot>();
    private CustList<SegmentStringPart> parts = new CustList<SegmentStringPart>();
    private CustList<OperationNode> roots = new CustList<OperationNode>();

    public void set(ParsedAnnotations _par) {
        annotations = _par.getRetAnnots();
        parts = _par.getAllParts();
    }
    public void buildAnnotations(AnalyzedPageEl _page) {
        roots = new CustList<OperationNode>();
        int len_ = annotations.size();
        for (int i = 0; i < len_; i++) {
            ResultExpression res_ = annotations.get(i).getRes();
            _page.setSumOffset(res_.getSumOffset());
            _page.zeroOffset();
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(res_, c_, _page);
            ReachOperationUtil.tryCalculate(r_, _page);
            roots.add(r_);
        }
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public CustList<ResultParsedAnnot> getAnnotations() {
        return annotations;
    }

    public CustList<SegmentStringPart> getParts() {
        return parts;
    }
}
