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
    private StringInfo strInfo;

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
        String op_ = operators.firstValue();
        boolean pureDot_ = false;
        boolean initArrayDim_ = false;
        if (!op_.isEmpty()) {
            if (op_.charAt(0) == ARR_ANNOT) {
                int beginValuePart_ = IndexConstants.FIRST_INDEX;
                int endValuePart_ = operators.firstKey();
                String str_ = _string.substring(beginValuePart_, endValuePart_);
                values.addEntry(beginValuePart_, str_);
                int i_ = IndexConstants.SECOND_INDEX;
                int nbKeys_ = operators.size();
                if (nbKeys_ == 2) {
                    beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
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
                return;
            }
            if (op_.charAt(0) != DOT_VAR) {
                if (priority == ElResolver.FCT_OPER_PRIO) {
                    if (op_.charAt(0) != '?') {
                        if (block == null) {
                            int afterLastPar_ = operators.lastKey()+1;
                            StringBuilder filter_ = new StringBuilder(_string);
                            if (op_.charAt(0) == ARR && _instance) {
                                for (int i : _nb.getReverse()) {
                                    if (i < afterLastPar_) {
                                        break;
                                    }
                                    filter_.deleteCharAt(i);
                                    countArrays++;
                                }
                                countArrays/=2;
                                initArrayDim_ = true;
                            }
                            if (!filter_.substring(afterLastPar_).trim().isEmpty()) {
                                if (!instance) {
                                    operators.clear();
                                    operators.addEntry(afterLastPar_, "");
                                    return;
                                }
                                values.addEntry(IndexConstants.FIRST_INDEX, _string);
                                constType = ConstType.ERROR;
                                return;
                            }
                        }
                    } else {
                        pureDot_ = true;
                    }
                }
            } else {
                pureDot_ = true;
            }
        } else {
            pureDot_ = true;
        }
        int beginValuePart_ = IndexConstants.FIRST_INDEX;
        int endValuePart_ = operators.firstKey();
        String str_;
        if (priority == ElResolver.DECL_PRIO) {
            str_ = _string.substring(beginValuePart_, endValuePart_);
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
            return;
        }
        if (priority == ElResolver.POST_INCR_PRIO) {
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(IndexConstants.FIRST_INDEX, str_);
            beginValuePart_ = endValuePart_ + operators.firstValue().length();
            str_ = _string.substring(beginValuePart_);
            addValueIfNotEmpty(beginValuePart_, str_);
            return;
        }
        if (_is && priority == ElResolver.CMP_PRIO) {
            //instanceof operator
            instanceTest = true;
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(IndexConstants.FIRST_INDEX, str_);
            beginValuePart_ = endValuePart_ + operators.firstValue().length();
            str_ = _string.substring(beginValuePart_);
            addValueIfNotEmpty(beginValuePart_, str_);
            return;
        }
        if (priority != ElResolver.UNARY_PRIO) {
            //not unary priority, not identity priority
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
        }
        if (pureDot_) {
            beginValuePart_ = endValuePart_ + operators.lastValue().length();
            str_ = _string.substring(beginValuePart_);
            values.addEntry(beginValuePart_, str_);
            return;
        }
        if (initArrayDim_) {
            int i_ = IndexConstants.SECOND_INDEX;
            int nbKeys_ = operators.size();
            while (i_ < nbKeys_) {
                beginValuePart_ = operators.getKey(i_ - 1) + operators.getValue(i_ - 1).length();
                endValuePart_ = operators.getKey(i_);
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
            return;
        }
        if (priority == ElResolver.FCT_OPER_PRIO) {
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

    public StringInfo getStrInfo() {
        return strInfo;
    }

    public void setStrInfo(StringInfo _strInfo) {
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
