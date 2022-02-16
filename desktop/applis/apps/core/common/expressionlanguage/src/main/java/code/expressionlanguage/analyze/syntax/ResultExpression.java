package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnonymousResult;
import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public final class ResultExpression {
    private CustList<AnonymousResult> anonymousResults = new CustList<AnonymousResult>();
    private CustList<SegmentStringPart> parts = new CustList<SegmentStringPart>();
    private OperationNode root;

    public CustList<AnonymousResult> getAnonymousResults() {
        return anonymousResults;
    }

    public void setAnonymousResults(CustList<AnonymousResult> _anonymousResults) {
        this.anonymousResults = _anonymousResults;
    }

    public CustList<SegmentStringPart> getParts() {
        return parts;
    }

    public void setParts(CustList<SegmentStringPart> _parts) {
        this.parts = _parts;
    }

    public OperationNode getRoot() {
        return root;
    }

    public void setRoot(OperationNode _root) {
        this.root = _root;
    }
}
