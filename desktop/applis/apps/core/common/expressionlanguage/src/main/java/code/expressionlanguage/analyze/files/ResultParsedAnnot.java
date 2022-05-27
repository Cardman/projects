package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.util.CustList;

public final class ResultParsedAnnot {
    private int index;
    private String annotation = "";
    private CustList<SegmentStringPart> parts = new CustList<SegmentStringPart>();
    private ResultExpression res;

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

    public ResultExpression getRes() {
        return res;
    }

    public void setRes(ResultExpression _r) {
        this.res = _r;
    }
}
