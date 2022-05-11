package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnonymousResult;
import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.expressionlanguage.analyze.instr.NumberInfosOutput;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;
import code.util.Ints;

public final class ResultExpression {
    private String analyzedString = "";
    private int sumOffset;
    private final CustList<AnonymousResult> anonymousResults = new CustList<AnonymousResult>();
    private final CustList<SegmentStringPart> parts = new CustList<SegmentStringPart>();
    private CustList<SegmentStringPart> partsAbs = new CustList<SegmentStringPart>();
    private final CustList<NumberInfosOutput> numbers = new CustList<NumberInfosOutput>();
    private OperationNode root;

    private final Ints annotDelNew = new Ints();
    private final Ints annotDelSwitch = new Ints();

    public String getAnalyzedString() {
        return analyzedString;
    }

    public void setAnalyzedString(String _a) {
        this.analyzedString = _a;
    }

    public int getSumOffset() {
        return sumOffset;
    }

    public void setSumOffset(int _s) {
        this.sumOffset = _s;
    }

    public CustList<AnonymousResult> getAnonymousResults() {
        return anonymousResults;
    }

    public CustList<SegmentStringPart> getParts() {
        return parts;
    }

    public CustList<NumberInfosOutput> getNumbers() {
        return numbers;
    }

    public CustList<SegmentStringPart> getPartsAbs() {
        return partsAbs;
    }

    public void partsAbsol(CustList<SegmentStringPart> _p) {
        this.partsAbs = new CustList<SegmentStringPart>(_p);
    }

    public OperationNode getRoot() {
        return root;
    }

    public void setRoot(OperationNode _root) {
        this.root = _root;
    }

    public Ints getAnnotDelNew() {
        return annotDelNew;
    }

    public Ints getAnnotDelSwitch() {
        return annotDelSwitch;
    }

}
