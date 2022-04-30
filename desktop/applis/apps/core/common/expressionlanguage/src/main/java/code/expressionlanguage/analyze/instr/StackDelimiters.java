package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class StackDelimiters {
    private final Ints callings = new Ints();
    private final Ints namedArgs = new Ints();
    private final Ints indexesNew = new Ints();
    private final Ints indexesSwitch = new Ints();
    private final CustList<ResultParsedAnnots> annotations = new CustList<ResultParsedAnnots>();
    private final CustList<ResultParsedAnnots> annotationsEnd = new CustList<ResultParsedAnnots>();
    private final CustList<ResultParsedAnnots> annotationsSw = new CustList<ResultParsedAnnots>();
    private final CustList<ResultParsedAnnots> annotationsEndSw = new CustList<ResultParsedAnnots>();
    private final StringList retSwitchList = new StringList();
    private final CustList<ResultParsedAnnots> annotationsSwPar = new CustList<ResultParsedAnnots>();
    private final CustList<ResultParsedAnnots> annotationsEndSwPar = new CustList<ResultParsedAnnots>();
    private final StringList stringsNew = new StringList();
    private final StringList stringsSwitch = new StringList();
    private final StringList retSwitch = new StringList();
    private final Ints indexesNewEnd = new Ints();
    private final Ints indexesSwitchEnd = new Ints();
    private final StringList stringsNewEnd = new StringList();
    private final StringList stringsSwitchEnd = new StringList();
    private final Ints annotDelNew = new Ints();
    private final Ints annotDelSwitch = new Ints();

    public Ints getCallings() {
        return callings;
    }

    public Ints getNamedArgs() {
        return namedArgs;
    }

    public Ints getIndexesNew() {
        return indexesNew;
    }

    public CustList<ResultParsedAnnots> getAnnotations() {
        return annotations;
    }

    public CustList<ResultParsedAnnots> getAnnotationsEnd() {
        return annotationsEnd;
    }

    public CustList<ResultParsedAnnots> getAnnotationsSw() {
        return annotationsSw;
    }

    public CustList<ResultParsedAnnots> getAnnotationsEndSw() {
        return annotationsEndSw;
    }

    public StringList getRetSwitchList() {
        return retSwitchList;
    }

    public CustList<ResultParsedAnnots> getAnnotationsSwPar() {
        return annotationsSwPar;
    }

    public CustList<ResultParsedAnnots> getAnnotationsEndSwPar() {
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

    public Ints getAnnotDelNew() {
        return annotDelNew;
    }

    public Ints getAnnotDelSwitch() {
        return annotDelSwitch;
    }

    public StringList getRetSwitch() {
        return retSwitch;
    }
}
