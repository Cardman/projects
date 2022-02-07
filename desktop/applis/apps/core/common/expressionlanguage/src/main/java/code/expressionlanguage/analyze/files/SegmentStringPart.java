package code.expressionlanguage.analyze.files;

public final class SegmentStringPart extends AbsSegmentColorPart {
    private final SegmentStringType strType;

    public SegmentStringPart(int _begin, int _end, SegmentStringType _strType) {
        super(_begin, _end);
        strType = _strType;
    }

    public SegmentStringType getStrType() {
        return strType;
    }
}
