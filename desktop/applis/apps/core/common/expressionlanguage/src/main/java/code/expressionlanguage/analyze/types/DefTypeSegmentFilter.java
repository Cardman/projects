package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;

public final class DefTypeSegmentFilter implements AbsTypeSegmentFilter {
    private final int caret;

    public DefTypeSegmentFilter(int _c) {
        this.caret = _c;
    }

    @Override
    public boolean inRange(int _begin, int _end) {
        return ResultExpressionOperationNode.inRange(_begin,caret,_end);
    }
}
