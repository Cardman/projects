package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class ResultExpressionBlock {
    private final SrcFileLocation caller;
    private final AbsBk block;
    private final AnnotationKind annotationKind;
    private final ResultExpression res;
    private final int begin;
    private final int end;

    public ResultExpressionBlock(SrcFileLocation _c,AbsBk _b, ResultExpression _r) {
        this(_c,_b,_r,AnnotationKind.NONE);
    }

    public ResultExpressionBlock(SrcFileLocation _c,AbsBk _b, ResultExpression _r, int _beg, int _end) {
        this(_c,_b,_r, AnnotationKind.NONE,_beg,_end);
    }

    public ResultExpressionBlock(SrcFileLocation _c,AbsBk _b, ResultExpression _r, AnnotationKind _k) {
        this(_c,_b,_r, _k,-1,-1);
    }

    public ResultExpressionBlock(SrcFileLocation _c,AbsBk _b, ResultExpression _r, AnnotationKind _k, int _beg, int _end) {
        this.caller = _c;
        this.block = _b;
        this.annotationKind = _k;
        this.res = _r;
        this.begin = _beg;
        this.end = _end;
    }

    public AnnotationKind getAnnotationKind() {
        return annotationKind;
    }

    public SrcFileLocation getCaller() {
        return caller;
    }

    public ResultExpression getRes() {
        return res;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public AbsBk getBlock() {
        return block;
    }
}
