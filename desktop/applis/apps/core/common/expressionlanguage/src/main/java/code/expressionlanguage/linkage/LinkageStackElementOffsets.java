package code.expressionlanguage.linkage;

public final class LinkageStackElementOffsets {
    private final int tr;
    private final int trEnd;
    private final int beginBlock;
    private final int endBlock;

    public LinkageStackElementOffsets(int _begin, int _end) {
        this(0,0,_begin, _end);
    }

    public LinkageStackElementOffsets(int _tr, int _trEnd,int _begin, int _end) {
        this.tr = _tr;
        this.trEnd = _trEnd;
        this.beginBlock = _begin;
        this.endBlock = _end;
    }

    public int getBeginBlock() {
        return beginBlock;
    }

    public int getTr() {
        return tr;
    }

    public int getTrEnd() {
        return trEnd;
    }

    public int getEndBlock() {
        return endBlock;
    }

}
