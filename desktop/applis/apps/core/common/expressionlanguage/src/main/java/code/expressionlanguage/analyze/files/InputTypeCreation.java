package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class InputTypeCreation {

    private int nextIndex;
    private int nextIndexBef;

    private OuterBlockEnum type;
    private FileBlock file;
    private final Ints badIndexes = new Ints();
    private String generatedId="";
    private Ints annotationsIndexes = new Ints();
    private StringList annotations = new StringList();
    private CustList<Ints> annotationsIndexesParams = new CustList<Ints>();
    private CustList<StringList> annotationsParams = new CustList<StringList>();

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

    public int getNextIndexBef() {
        return nextIndexBef;
    }

    public void setNextIndexBef(int _nextIndexBef) {
        this.nextIndexBef = _nextIndexBef;
    }

    public OuterBlockEnum getType() {
        return type;
    }

    public void setType(OuterBlockEnum _type) {
        this.type = _type;
    }

    public FileBlock getFile() {
        return file;
    }
    public void setFile(FileBlock _file) {
        file = _file;
    }

    public Ints getBadIndexes() {
        return badIndexes;
    }

    public String getGeneratedId() {
        return generatedId;
    }

    public void generatedId(String _generated,String _defaultId) {
        StringList parts_ = StringExpUtil.getDollarWordSeparators(StringExpUtil.getIdFromAllTypes(_generated.trim()));
        if (parts_.isEmpty()) {
            generatedId = _defaultId;
        } else {
            generatedId = parts_.last().trim();
        }
    }

    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }

    public void setAnnotationsIndexes(Ints _annotationsIndexes) {
        this.annotationsIndexes = _annotationsIndexes;
    }

    public StringList getAnnotations() {
        return annotations;
    }

    public void setAnnotations(StringList _annotations) {
        this.annotations = _annotations;
    }

    public CustList<Ints> getAnnotationsIndexesParams() {
        return annotationsIndexesParams;
    }

    public void setAnnotationsIndexesParams(CustList<Ints> _annotationsIndexesParams) {
        this.annotationsIndexesParams = _annotationsIndexesParams;
    }

    public CustList<StringList> getAnnotationsParams() {
        return annotationsParams;
    }

    public void setAnnotationsParams(CustList<StringList> _annotationsParams) {
        this.annotationsParams = _annotationsParams;
    }
}
