package code.expressionlanguage.exec;

public final class ExpressionLanguageBp {
    private final ExpressionLanguage expressionLanguage;
    private final int diff;

    public ExpressionLanguageBp(ExpressionLanguage _exp, int _f) {
        this.expressionLanguage = _exp;
        this.diff = _f;
    }

    public ExpressionLanguage getExpressionLanguage() {
        return expressionLanguage;
    }

    public int getDiff() {
        return diff;
    }
}
