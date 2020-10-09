package code.expressionlanguage.analyze.instr;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.common.NumberInfos;
import code.expressionlanguage.common.StringInfo;
import code.expressionlanguage.analyze.files.ParsedFctHeader;
import code.util.CustList;
import code.util.*;
import code.util.Ints;
import code.util.core.IndexConstants;

public final class OperationsSequence {
    private static final char DOT_VAR = '.';
    private static final char ARR = '[';
    private static final char PAR = '(';
    private static final char ARR_ANNOT = '{';
    private ConstType constType = ConstType.NOTHING;

    private NumberInfos nbInfos;
    private StringInfo strInfo;

    private String fctName = "";

    private boolean leftParFirstOperator;

    private int priority;

    private IntTreeMap<String> values;

    private IntTreeMap<String> operators;

    private Delimiters delimiter;

    private int offset;
    private int delta;
    private boolean errorDot;

    private boolean instanceTest;

    private String extractType = "";

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private int countArrays;
    private Ints errorParts = new Ints();
    private boolean instance;
    private ParsedFctHeader results;
    private Block block;
    private int length;
    public void setValue(String _string, int _offset) {
        values = new IntTreeMap<String>();
        values.put((int)IndexConstants.FIRST_INDEX, _string);
        offset = _offset;
    }

    public void setupValues(String _string, boolean _is, boolean _instance, Ints _nb) {
        values = new IntTreeMap<String>();
        instance = _instance;
        if (operators.isEmpty()) {
            values.put((int) IndexConstants.FIRST_INDEX, _string);
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
                values.put(beginValuePart_, str_);
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
                    values.put(beginValuePart_, str_);
                    i_++;
                }
                return;
            }
            if (op_.charAt(0) != DOT_VAR) {
                if (priority == ElResolver.FCT_OPER_PRIO) {
                    if (op_.charAt(0) != '?') {
                        int afterLastPar_ = operators.lastKey()+1;
                        StringBuilder filter_ = new StringBuilder(_string);
                        for (int i : _nb.getReverse()) {
                            if (i < afterLastPar_) {
                                break;
                            }
                            filter_.deleteCharAt(i);
                            countArrays++;
                        }
                        countArrays/=2;
                        if (op_.charAt(0) == ARR && _instance) {
                            initArrayDim_ = true;
                        }
                        if (!filter_.substring(afterLastPar_).trim().isEmpty()) {
                            if (!instance) {
                                operators.clear();
                                operators.put(afterLastPar_,"");
                                return;
                            }
                            if (block == null) {
                                values.put((int) IndexConstants.FIRST_INDEX, _string);
                                constType = ConstType.ERROR_INST;
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
            values.put(beginValuePart_, str_);
            int i_ = IndexConstants.SECOND_INDEX;
            int nbKeys_ = operators.size();
            while (i_ < nbKeys_) {
                beginValuePart_ = endValuePart_ + operators.getValue(i_-1).length();
                endValuePart_ = operators.getKey(i_);
                str_ = _string.substring(beginValuePart_, endValuePart_);
                values.put(beginValuePart_, str_);
                i_++;
            }
            beginValuePart_ = endValuePart_ + operators.lastValue().length();
            str_ = _string.substring(beginValuePart_);
            values.put(beginValuePart_, str_);
            return;
        }
        if (priority == ElResolver.POST_INCR_PRIO) {
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.put((int) IndexConstants.FIRST_INDEX, str_);
            beginValuePart_ = endValuePart_ + operators.firstValue().length();
            str_ = _string.substring(beginValuePart_);
            addValueIfNotEmpty(beginValuePart_, str_);
            return;
        }
        if (_is && priority == ElResolver.CMP_PRIO) {
            //instanceof operator
            instanceTest = true;
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.put((int) IndexConstants.FIRST_INDEX, str_);
            beginValuePart_ = endValuePart_ + operators.firstValue().length();
            str_ = _string.substring(beginValuePart_);
            addValueIfNotEmpty(beginValuePart_, str_);
            return;
        }
        if (priority != ElResolver.UNARY_PRIO && !(fctName.trim().isEmpty() && isLeftParFirstOperator())) {
            //not unary priority, not identity priority
            str_ = _string.substring(beginValuePart_, endValuePart_);
            values.put(beginValuePart_, str_);
        }
        if (pureDot_) {
            beginValuePart_ = endValuePart_ + operators.lastValue().length();
            str_ = _string.substring(beginValuePart_);
            values.put(beginValuePart_, str_);
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
                values.put(beginValuePart_, str_);
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
                values.put(beginValuePart_, str_);
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
            values.put(beginValuePart_, str_);
            i_++;
        }
        beginValuePart_ = endValuePart_ + operators.lastValue().length();
        str_ = _string.substring(beginValuePart_);
        values.put(beginValuePart_, str_);
    }

    private void addValueIfNotEmpty(int _beginValuePart,
                                    String _str) {
        if (_str.trim().isEmpty()) {
            return;
        }
        values.put(_beginValuePart, _str);
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

    public String getFctName() {
        return fctName;
    }

    public void setFctName(String _fctName) {
        fctName = _fctName;
    }

    public boolean isLeftParFirstOperator() {
        return leftParFirstOperator;
    }

    public void setLeftParFirstOperator(boolean _leftParFirstOperator) {
        leftParFirstOperator = _leftParFirstOperator;
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

    public IntTreeMap< String> getValues() {
        return values;
    }

    public IntTreeMap< String> getOperators() {
        return operators;
    }

    public void setOperators(IntTreeMap< String> _operators) {
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

    public String getExtractType() {
        return extractType;
    }

    public void setExtractType(String _extractType) {
        extractType = _extractType;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public void setPartOffsets(CustList<PartOffset> _partOffsets) {
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

    public ParsedFctHeader getResults() {
        return results;
    }

    public void setResults(ParsedFctHeader results) {
        this.results = results;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block _block) {
        block = _block;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
