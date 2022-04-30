package code.expressionlanguage.analyze.files;

import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ResultParsedAnnots {
    private Ints annotationsIndexes = new Ints();
    private StringList annotations = new StringList();
    private CustList<SegmentStringPart> parts = new CustList<SegmentStringPart>();

    public void set(ParsedAnnotations _par) {
        annotations = _par.getAnnotations();
        annotationsIndexes = _par.getAnnotationsIndexes();
        parts = _par.getParts();
    }
    public StringList getAnnotations() {
        return annotations;
    }

    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public CustList<SegmentStringPart> getParts() {
        return parts;
    }
}
