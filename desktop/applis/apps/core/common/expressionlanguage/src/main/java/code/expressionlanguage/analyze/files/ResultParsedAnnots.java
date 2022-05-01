package code.expressionlanguage.analyze.files;

import code.util.CustList;

public final class ResultParsedAnnots {
    private CustList<ResultParsedAnnot> annotations = new CustList<ResultParsedAnnot>();
    private CustList<SegmentStringPart> parts = new CustList<SegmentStringPart>();

    public void set(ParsedAnnotations _par) {
        annotations = _par.getRetAnnots();
        parts = _par.getAllParts();
    }
    public CustList<ResultParsedAnnot> getAnnotations() {
        return annotations;
    }

    public CustList<SegmentStringPart> getParts() {
        return parts;
    }
}
