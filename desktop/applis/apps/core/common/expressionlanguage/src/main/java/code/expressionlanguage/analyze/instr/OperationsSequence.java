package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.files.ParsedFctHeaderResult;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.NumberInfos;
import code.maths.litteralcom.StrTypes;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class OperationsSequence {
    private static final char DOT_VAR = '.';
    private static final char ARR = '[';
    private static final char PAR = '(';
    private static final char ARR_ANNOT = '{';
    private ConstType constType = ConstType.NOTHING;
    private InfoBlock declaringField;
    private StringList errors = new StringList();

    private NumberInfos nbInfos;
    private TextBlockInfo textInfo;
    private TextBlockInfo strInfo;

    private String fctName = "";

    private int priority;

    private StrTypes values;

    private StrTypes operators;

    private Delimiters delimiter;

    private int offset;
    private int delta;
    private boolean errorDot;

    private boolean instanceTest;

    private String extractType = "";
    private AnaGeneType extractStaticType;

    private AnaResultPartType partOffsets = new AnaResultPartType();
    private int countArrays;
    private final Ints errorParts = new Ints();
    private boolean instance;
    private ParsedFctHeaderResult results;
    private AbsBk block;
    private int length;
    public void setValue(String _string, int _offset) {
        values = new StrTypes();
        values.addEntry(IndexConstants.FIRST_INDEX, _string);
        offset = _offset;
    }

    public void setupValues(String _string, boolean _is, boolean _instance, Ints _nb) {
        values = new StrTypes();
        instance = _instance;
        if (operators.isEmpty()) {
            values.addEntry(IndexConstants.FIRST_INDEX, _string);
            constType = ConstType.ERROR;
            return;
        }
        if (priority == ElResolver.DECL_PRIO) {
            declOp(_string);
            return;
        }
        if (priority == ElResolver.POST_INCR_PRIO) {
            int beginValuePart_ = IndexConstants.FIRST_INDEX;
            int endValuePart_ = operators.firstKey();
            String str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(IndexConstants.FIRST_INDEX, str_);
            beginValuePart_ = endValuePart_ + operators.firstValue().length();
            str_ = _string.substring(beginValuePart_);
            addValueIfNotEmpty(beginValuePart_, str_);
            return;
        }
        if (_is && priority == ElResolver.CMP_PRIO) {
            //instanceof operator
            int beginValuePart_ = IndexConstants.FIRST_INDEX;
            instanceTest = true;
            int endValuePart_ = operators.firstKey();
            String str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(IndexConstants.FIRST_INDEX, str_);
            beginValuePart_ = endValuePart_ + operators.firstValue().length();
            str_ = _string.substring(beginValuePart_);
            addValueIfNotEmpty(beginValuePart_, str_);
            return;
        }
        if (priority == ElResolver.FCT_OPER_PRIO) {
            fctPrio(_string, _instance, _nb);
            return;
        }
        notUnary(_string);
        defOperators(_string);
    }

    private void fctPrio(String _string, boolean _instance, Ints _nb) {
        String op_ = operators.firstValue();
        if (op_.isEmpty()) {
            pureDot(_string);
            return;
        }
        char chOp_ = op_.charAt(0);
        if (chOp_ == ARR_ANNOT) {
            braceArr(_string);
            return;
        }
        if (chOp_ == DOT_VAR) {
            pureDot(_string);
            return;
        }
        if (chOp_ == '?') {
            pureDot(_string);
            return;
        }
        if (block != null) {
            notUnary(_string);
            fctPrio(_string);
            return;
        }
        int afterLastPar_ = operators.lastKey() + 1;
        StringBuilder filter_ = new StringBuilder(_string);
        boolean initArrayDim_ = false;
        if (chOp_ == ARR && _instance) {
            countArr(_nb, filter_);
            initArrayDim_ = true;
        }
        if (!filter_.substring(afterLastPar_).trim().isEmpty()) {
            errOpers(_string, afterLastPar_);
            return;
        }
        if (initArrayDim_) {
            notUnary(_string);
            arrDim(_string);
            return;
        }
        notUnary(_string);
        fctPrio(_string);
    }

    private void pureDot(String _string) {
        notUnary(_string);
        int endValuePart_ = operators.firstKey();
        int beginValuePart_ = endValuePart_ + operators.lastValue().length();
        String str_ = _string.substring(beginValuePart_);
        values.addEntry(beginValuePart_, str_);
    }

    private void notUnary(String _string) {
        if (priority != ElResolver.UNARY_PRIO) {
            //not unary priority, not identity priority
            int beginValuePart_ = IndexConstants.FIRST_INDEX;
            int endValuePart_ = operators.firstKey();
            String str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
        }
    }

    private void countArr(Ints _nb, StringBuilder filter_) {
        int afterLastPar_ = operators.lastKey()+1;
        for (int i : _nb.getReverse()) {
            if (i < afterLastPar_) {
                break;
            }
            filter_.deleteCharAt(i);
            countArrays++;
        }
        countArrays/=2;
    }

    private void errOpers(String _string, int afterLastPar_) {
        if (!instance) {
            operators.clear();
            operators.addEntry(afterLastPar_, "");
            return;
        }
        values.addEntry(IndexConstants.FIRST_INDEX, _string);
        constType = ConstType.ERROR;
    }

    private void braceArr(String _string) {
        int beginValuePart_ = IndexConstants.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_ = _string.substring(beginValuePart_, endValuePart_);
        values.addEntry(beginValuePart_, str_);
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = operators.size();
        if (nbKeys_ == 2) {
            beginValuePart_ = endValuePart_ + operators.getValue(0).length();
            endValuePart_ = operators.getKey(i_);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            addValueIfNotEmpty(beginValuePart_, str_);
            return;
        }
        while (i_ < nbKeys_) {
            beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
            endValuePart_ = operators.getKey(i_);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
            i_++;
        }
    }

    private void declOp(String _string) {
        int beginValuePart_ = IndexConstants.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_ = _string.substring(beginValuePart_, endValuePart_);
        values.addEntry(beginValuePart_, str_);
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
            endValuePart_ = operators.getKey(i_);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
            i_++;
        }
        beginValuePart_ = endValuePart_ + operators.lastValue().length();
        str_ = _string.substring(beginValuePart_);
        values.addEntry(beginValuePart_, str_);
    }

    private void arrDim(String _string) {
        int beginValuePart_;
        String str_;
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            beginValuePart_ = operators.getKey(i_ - 1) + operators.getValue(i_ - 1).length();
            int endValuePart_ = operators.getKey(i_);
            if (i_ + 1 < nbKeys_) {
                int b_ = operators.getKey(i_) + operators.getValue(i_).length();
                int e_ = operators.getKey(i_ + 1);
                String err_ = _string.substring(b_, e_);
                if (!err_.trim().isEmpty()) {
                    getErrorParts().add(b_);
                    constType = ConstType.ERROR;
                }
            }
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
            i_++;
            i_++;
        }
    }

    private void fctPrio(String _string) {
        int beginValuePart_;
        String str_;
        int endValuePart_ = operators.firstKey();
        if (operators.size() == 2) {
            beginValuePart_ = endValuePart_ + operators.firstValue().length();
            endValuePart_ = operators.getKey(IndexConstants.SECOND_INDEX);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            addValueIfNotEmpty(beginValuePart_, str_);
            return;
        }
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
            endValuePart_ = operators.getKey(i_);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
            i_++;
        }
    }

    private void defOperators(String _string) {
        int beginValuePart_;
        String str_;
        int endValuePart_ = operators.firstKey();
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
            endValuePart_ = operators.getKey(i_);
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
            i_++;
        }
        beginValuePart_ = endValuePart_ + operators.lastValue().length();
        str_ = _string.substring(beginValuePart_);
        values.addEntry(beginValuePart_, str_);
    }

    private void addValueIfNotEmpty(int _beginValuePart,
                                    String _str) {
        if (_str.trim().isEmpty()) {
            return;
        }
        values.addEntry(_beginValuePart, _str);
    }

    public boolean implMiddle() {
        StrTypes vs_ = getValues();
        if ((vs_.size() == 2 || vs_.size() == 3) && vs_.getValue(1).trim().isEmpty()) {
            vs_.remove(1);
            return true;
        }
        return false;
    }

    public void removeFirst() {
        StrTypes vs_ = getValues();
        vs_.remove(0);
    }
    public Ints getErrorParts() {
        return errorParts;
    }
    public int getCountArrays() {
        return countArrays;
    }

    public boolean isInstanceTest() {
        return instanceTest;
    }

    public ConstType getConstType() {
        return constType;
    }

    public void setConstType(ConstType _constType) {
        constType = _constType;
    }

    public InfoBlock getDeclaringField() {
        return declaringField;
    }

    public void setDeclaringField(InfoBlock _declaringField) {
        this.declaringField = _declaringField;
    }

    public StringList getErrors() {
        return errors;
    }

    public void setErrors(StringList _errors) {
        this.errors = _errors;
    }

    public String getFctName() {
        return fctName;
    }

    public void setFctName(String _fctName) {
        fctName = _fctName;
    }

    public boolean isCallDbArray() {
        return isCall() || instance;
    }

    public boolean isCall() {
        if (priority != ElResolver.FCT_OPER_PRIO) {
            return false;
        }
        String str_ = operators.firstValue();
        if (str_.isEmpty()) {
            return false;
        }
        return str_.charAt(0) == PAR;
    }

    public boolean isInstance() {
        return instance;
    }

    public boolean isArray() {
        if (priority != ElResolver.FCT_OPER_PRIO) {
            return false;
        }
        String str_ = operators.firstValue();
        if (str_.isEmpty()) {
            return false;
        }
        return str_.charAt(0) == ARR;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int _priority) {
        priority = _priority;
    }

    public StrTypes getValues() {
        return values;
    }

    public StrTypes getOperators() {
        return operators;
    }

    public void setOperators(StrTypes _operators) {
        operators = _operators;
    }

    public Delimiters getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(Delimiters _delimiter) {
        delimiter = _delimiter;
    }

    public int getOffset() {
        return offset;
    }

    public NumberInfos getNbInfos() {
        return nbInfos;
    }

    public void setNbInfos(NumberInfos _nbInfos) {
        nbInfos = _nbInfos;
    }

    public TextBlockInfo getStrInfo() {
        return strInfo;
    }

    public void setStrInfo(TextBlockInfo _strInfo) {
        strInfo = _strInfo;
    }

    public TextBlockInfo getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(TextBlockInfo _textInfo) {
        this.textInfo = _textInfo;
    }

    public String getExtractType() {
        return extractType;
    }

    public void setExtractType(String _extractType) {
        extractType = _extractType;
    }

    public AnaGeneType getExtractStaticType() {
        return extractStaticType;
    }

    public void setExtractStaticType(AnaGeneType _ext) {
        this.extractStaticType = _ext;
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    public void setPartOffsets(AnaResultPartType _partOffsets) {
        partOffsets = _partOffsets;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int _delta) {
        delta = _delta;
    }

    public boolean isErrorDot() {
        return errorDot;
    }

    public void setErrorDot(boolean _errorDot) {
        errorDot = _errorDot;
    }

    public ParsedFctHeaderResult getResults() {
        return results;
    }

    public void setResults(ParsedFctHeaderResult _results) {
        this.results = _results;
    }

    public AbsBk getBlock() {
        return block;
    }

    public void setBlock(AbsBk _block) {
        block = _block;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int _length) {
        this.length = _length;
    }
}
