package code.expressionlanguage.analyze.syntax;

public final class LambdaDynFilterCall {
    private String lambda="";
    private SrcFileLocation calleeRef;

    public String getLambda() {
        return lambda;
    }

    public void setLambda(String _l) {
        this.lambda = _l;
    }

    public SrcFileLocation getCalleeRef() {
        return calleeRef;
    }

    public void setCalleeRef(SrcFileLocation _c) {
        this.calleeRef = _c;
    }
}
