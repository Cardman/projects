package code.expressionlanguage.analyze.files;

public final class SegmentColorPart extends AbsSegmentColorPart {
    private final SegmentType type;

    public SegmentColorPart(int _begin, int _end, SegmentType _type) {
        super(_begin, _end);
        type = _type;
    }

    public SegmentType getType() {
        return type;
    }
}
