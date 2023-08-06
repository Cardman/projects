package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.AbsSegmentColorPart;

public final class SegmentReadOnlyPart extends AbsSegmentColorPart {
    private final SyntaxRefEnum kind;
    public SegmentReadOnlyPart(int _begin, int _end, SyntaxRefEnum _k) {
        super(_begin, _end);
        this.kind = _k;
    }

    public SyntaxRefEnum getKind() {
        return kind;
    }
}
