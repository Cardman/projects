package code.expressionlanguage.analyze.files;

public abstract class AbsSegmentColorPart {
    private final int begin;
    private final int end;

    protected AbsSegmentColorPart(int _begin, int _end) {
        this.begin = _begin;
        this.end = _end;
    }

    public int getEnd() {
        return end;
    }

    public int getBegin() {
        return begin;
    }
}
