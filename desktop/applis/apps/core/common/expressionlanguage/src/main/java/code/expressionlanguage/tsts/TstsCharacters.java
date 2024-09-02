package code.expressionlanguage.tsts;

import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class TstsCharacters {

    private TstsCharacters() {
    }

    public static CustList<StringList> annotationsParams(CustList<ResultParsedAnnots> _list) {
        CustList<StringList> ls_ = new CustList<StringList>();
        for (ResultParsedAnnots r: _list) {
            ls_.add(getAnnotations(r));
        }
        return ls_;
    }

    public static StringList getAnnotations(ResultParsedAnnots _p) {
        return annotations(_p.getAnnotations());
    }

    public static StringList annotations(CustList<ResultParsedAnnot> _list) {
        StringList ls_ = new StringList();
        for (ResultParsedAnnot i: _list) {
            ls_.add(i.getAnnotation());
        }
        return ls_;
    }

    public static CustList<Ints> annotationsIndexesParams(CustList<ResultParsedAnnots> _list) {
        CustList<Ints> ls_ = new CustList<Ints>();
        for (ResultParsedAnnots r: _list) {
            ls_.add(annotationsIndexes(r));
        }
        return ls_;
    }

    public static Ints annotationsIndexes(ResultParsedAnnots _p) {
        return annotationsIndexes(_p.getAnnotations());
    }

    public static Ints annotationsIndexes(CustList<ResultParsedAnnot> _p) {
        Ints ls_ = new Ints();
        for (ResultParsedAnnot i: _p) {
            ls_.add(i.getIndex());
        }
        return ls_;
    }

}
