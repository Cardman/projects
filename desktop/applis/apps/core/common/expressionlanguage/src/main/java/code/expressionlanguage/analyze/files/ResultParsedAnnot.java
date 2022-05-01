package code.expressionlanguage.analyze.files;

import code.util.CustList;

public final class ResultParsedAnnot {
    private int index;
    private String annotation = "";
    private CustList<SegmentStringPart> parts = new CustList<SegmentStringPart>();

    public void set(int _offset, String _annot,CustList<SegmentStringPart> _parts) {
        index = _offset;
        annotation = _annot;
        parts = new CustList<SegmentStringPart>(_parts);
    }
    public String getAnnotation() {
        return annotation;
    }

    public int getIndex() {
        return index;
    }

    public CustList<SegmentStringPart> getParts() {
        return parts;
    }
}
