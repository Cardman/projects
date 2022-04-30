package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class InputTypeCreation {

    private int nextIndex;
    private int nextIndexBef;
    private int offset;

    private OuterBlockEnum type;
    private FileBlock file;
    private final Ints badIndexes = new Ints();
    private String generatedId="";
    private ResultParsedAnnots annotations = new ResultParsedAnnots();
    private CustList<ResultParsedAnnots> annotationsParams = new CustList<ResultParsedAnnots>();
    private CustList<SegmentStringPart> stringParts = new CustList<SegmentStringPart>();

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

    public ResultParsedAnnots getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ResultParsedAnnots _annotations) {
        this.annotations = _annotations;
    }

    public CustList<ResultParsedAnnots> getAnnotationsParams() {
        return annotationsParams;
    }

    public void setAnnotationsParams(CustList<ResultParsedAnnots> _annotationsParams) {
        this.annotationsParams = _annotationsParams;
    }

    public CustList<SegmentStringPart> getStringParts() {
        return stringParts;
    }

    public void setStringParts(CustList<SegmentStringPart> _strPart) {
        this.stringParts = _strPart;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _off) {
        this.offset = _off;
    }
}
