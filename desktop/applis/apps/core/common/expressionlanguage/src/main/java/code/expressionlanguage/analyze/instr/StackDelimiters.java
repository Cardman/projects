package code.expressionlanguage.analyze.instr;

import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class StackDelimiters {
    private final Ints callings = new Ints();
    private final Ints indexesNew = new Ints();
    private final Ints indexesSwitch = new Ints();
    private final CustList<Ints> annotationsIndexes = new CustList<Ints>();
    private final CustList<StringList> annotations = new CustList<StringList>();
    private final CustList<Ints> annotationsIndexesEnd = new CustList<Ints>();
    private final CustList<StringList> annotationsEnd = new CustList<StringList>();
    private final CustList<Ints> annotationsIndexesSw = new CustList<Ints>();
    private final CustList<StringList> annotationsSw = new CustList<StringList>();
    private final CustList<Ints> annotationsIndexesEndSw = new CustList<Ints>();
    private final CustList<StringList> annotationsEndSw = new CustList<StringList>();
    private final CustList<Ints> annotationsIndexesSwPar = new CustList<Ints>();
    private final CustList<StringList> annotationsSwPar = new CustList<StringList>();
    private final CustList<Ints> annotationsIndexesEndSwPar = new CustList<Ints>();
    private final CustList<StringList> annotationsEndSwPar = new CustList<StringList>();
    private final StringList stringsNew = new StringList();
    private final StringList stringsSwitch = new StringList();
    private final Ints indexesNewEnd = new Ints();
    private final Ints indexesSwitchEnd = new Ints();
    private final StringList stringsNewEnd = new StringList();
    private final StringList stringsSwitchEnd = new StringList();

    public Ints getCallings() {
        return callings;
    }

    public Ints getIndexesNew() {
        return indexesNew;
    }

    public CustList<Ints> getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public CustList<StringList> getAnnotations() {
        return annotations;
    }

    public CustList<Ints> getAnnotationsIndexesEnd() {
        return annotationsIndexesEnd;
    }

    public CustList<StringList> getAnnotationsEnd() {
        return annotationsEnd;
    }

    public CustList<Ints> getAnnotationsIndexesSw() {
        return annotationsIndexesSw;
    }

    public CustList<StringList> getAnnotationsSw() {
        return annotationsSw;
    }

    public CustList<Ints> getAnnotationsIndexesEndSw() {
        return annotationsIndexesEndSw;
    }

    public CustList<StringList> getAnnotationsEndSw() {
        return annotationsEndSw;
    }

    public CustList<Ints> getAnnotationsIndexesSwPar() {
        return annotationsIndexesSwPar;
    }

    public CustList<StringList> getAnnotationsSwPar() {
        return annotationsSwPar;
    }

    public CustList<Ints> getAnnotationsIndexesEndSwPar() {
        return annotationsIndexesEndSwPar;
    }

    public CustList<StringList> getAnnotationsEndSwPar() {
        return annotationsEndSwPar;
    }

    public StringList getStringsNew() {
        return stringsNew;
    }

    public Ints getIndexesNewEnd() {
        return indexesNewEnd;
    }

    public StringList getStringsNewEnd() {
        return stringsNewEnd;
    }

    public Ints getIndexesSwitch() {
        return indexesSwitch;
    }

    public Ints getIndexesSwitchEnd() {
        return indexesSwitchEnd;
    }

    public StringList getStringsSwitch() {
        return stringsSwitch;
    }

    public StringList getStringsSwitchEnd() {
        return stringsSwitchEnd;
    }
}
