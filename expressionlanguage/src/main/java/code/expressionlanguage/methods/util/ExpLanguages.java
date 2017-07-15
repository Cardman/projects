package code.expressionlanguage.methods.util;
import code.expressionlanguage.opers.ExpressionLanguage;

public final class ExpLanguages {

    private final ExpressionLanguage left;

    private final ExpressionLanguage right;

    public ExpLanguages(ExpressionLanguage _left, ExpressionLanguage _right) {
        left = _left;
        right = _right;
    }

    public ExpressionLanguage getLeft() {
        return left;
    }

    public ExpressionLanguage getRight() {
        return right;
    }
}
