package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultAnnotationAnalysis;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.files.ParsedFctHeaderResult;
import code.expressionlanguage.analyze.opers.AnnotationInstanceArobaseOperation;
import code.expressionlanguage.analyze.opers.MethodOperation;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.NumberInfos;
import code.expressionlanguage.options.KeyWords;
import code.maths.litteralcom.StrTypes;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class OperationsSequence {
    private static final char DOT_VAR = '.';
    private static final char ARR = '[';
    private static final char PAR = '(';
    private static final char ARR_ANNOT = '{';
    private ConstType constType = ConstType.NOTHING;
    private InfoBlock declaringField;
    private StringList errors = new StringList();

    private NumberInfos nbInfos;
    private TextBlockInfo strInfo;

    private String fctName = "";

    private int priority;

    private StrTypes values;

    private StrTypes operators;

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
    private int deltaAnnot;
    private String retSwitch = "";
    private int argOffset;
    private String clName = "";
    private boolean implMiddle;
    private boolean ternaryOp;
    private boolean call;
    private boolean array;
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
            firstOpt(_string);
            possRight(_string);
            return;
        }
        if (_is && priority == ElResolver.CMP_PRIO) {
            //instanceof operator
            instanceTest = true;
            firstOpt(_string);
            possRight(_string);
            return;
        }
        if (priority == ElResolver.FCT_OPER_PRIO) {
            fctPrio(_string, _instance, _nb);
            return;
        }
        notUnary(_string);
        defOperators(_string);
    }

    private void possRight(String _string) {
        int endValuePart_ = operators.firstKey();
        int beginValuePart_ = endValuePart_ + operators.firstValue().length();
        String str_ = _string.substring(beginValuePart_);
        addValueIfNotEmpty(beginValuePart_, str_);
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
            firstOpt(_string);
            args(_string);
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
            firstOpt(_string);
            arrDim(_string);
            return;
        }
        firstOpt(_string);
        args(_string);
    }

    private void pureDot(String _string) {
        firstOpt(_string);
        lastArg(_string);
    }

    private void notUnary(String _string) {
        if (priority != ElResolver.UNARY_PRIO) {
            //not unary priority, not identity priority
            firstOpt(_string);
        }
    }

    private void firstOpt(String _string) {
        String str_ = StrTypes.firstPartNotUnary(_string,operators,values);
        offset = operators.firstKey();
        if (getPriority() == ElResolver.NAME_PRIO || getPriority() == ElResolver.AFF_PRIO) {
            fctName = str_;
        }
    }

    private void countArr(Ints _nb, StringBuilder _filter) {
        int afterLastPar_ = operators.lastKey()+1;
        for (int i : _nb.getReverse()) {
            if (i < afterLastPar_) {
                break;
            }
            _filter.deleteCharAt(i);
            countArrays++;
        }
        countArrays/=2;
    }

    private void errOpers(String _string, int _afterLastPar) {
        if (!instance) {
            operators.clear();
            operators.addEntry(_afterLastPar, "");
            return;
        }
        values.addEntry(IndexConstants.FIRST_INDEX, _string);
        constType = ConstType.ERROR;
    }

    private void braceArr(String _string) {
        firstOpt(_string);
        args(_string);
    }

    private void declOp(String _string) {
        firstOpt(_string);
        argsLoop(_string);
        lastArg(_string);
    }

    private void arrDim(String _string) {
        int i_ = IndexConstants.SECOND_INDEX;
        int nbKeys_ = operators.size();
        while (i_ < nbKeys_) {
            int beginValuePart_ = operators.getKey(i_ - 1) + operators.getValue(i_ - 1).length();
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
            String str_ = _string.substring(beginValuePart_, endValuePart_);
            values.addEntry(beginValuePart_, str_);
            i_++;
            i_++;
        }
    }

    private void args(String _string) {
        int nbKeys_ = operators.size();
        if (nbKeys_ == 2) {
            StrTypes.addNotEmpty(_string,operators,values);
            return;
        }
        argsLoop(_string);
    }
    private void argsLoop(String _string) {
        StrTypes.loopArgs(_string,operators,values);
    }

    private void defOperators(String _string) {
        argsLoop(_string);
        lastArg(_string);
    }
    private void lastArg(String _string) {
        StrTypes.lastPart(_string,operators,values);
    }

    private void addValueIfNotEmpty(int _beginValuePart,
                                    String _str) {
        StrTypes.addNotEmpty(_beginValuePart,_str,values);
    }

    private void setImplMiddle() {
        StrTypes vs_ = getValues();
        if ((vs_.size() == 2 || vs_.size() == 3) && vs_.getValue(1).trim().isEmpty()) {
            vs_.remove(1);
            implMiddle = true;
        }
    }

    private void removeFirst() {
        StrTypes vs_ = getValues();
        vs_.remove(0);
    }

    public void adjust(MethodOperation _m, AnalyzedPageEl _page) {
        ConstType ct_ = getConstType();
        if (ct_ == ConstType.ERROR) {
            return;
        }
//        if (getOperators().isEmpty()) {
//            return;
//        }
        if (getPriority() == ElResolver.NAME_PRIO) {
            removeFirst();
            return;
        }
        if (getPriority() == ElResolver.TERNARY_PRIO) {
            ternaryOp = true;
            return;
        }
        if (getPriority() == ElResolver.RANGE) {
            setImplMiddle();
            return;
        }
        if (getPriority() == ElResolver.FCT_OPER_PRIO) {
            fctAdjust(_m, _page);
            return;
        }
        if (getPriority() == ElResolver.AFF_PRIO&&_m instanceof AnnotationInstanceArobaseOperation) {
            removeFirst();
        }
    }

    private void fctAdjust(MethodOperation _m, AnalyzedPageEl _page) {
        if (DefaultAnnotationAnalysis.isAnnotAnalysis(_page, _m,this)) {
            removeFirst();
            return;
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordBool_ = keyWords_.getKeyWordBool();
        String fctName_ = fctName.trim();
        if (fctName_.startsWith("@")) {
            removeFirst();
            return;
        }
        int ternary_ = 0;
        if (StringUtil.quickEq(fctName_, keyWordBool_)) {
            ternary_ = getValues().size()-1;
        }
        if (ternary_ == 3) {
            ternaryOp = true;
            removeFirst();
            return;
        }
        setupCall();
        if (isCallDbArray()) {
            removeFirst();
            String keyWordValueOf_ = keyWords_.getKeyWordValueOf();
            if (StringUtil.quickEq(fctName_, keyWordValueOf_)) {
                StrTypes values_ = getValues();
                clName = StrTypes.value(values_, 0);
                argOffset = StrTypes.offset(values_, 0);
                if (!values_.isEmpty()) {
                    values_.remove(0);
                }
            }
            return;
        }
        setupArray();
        if (isArray()) {
            removeFirst();
        }
    }
    private void setupCall() {
        String str_ = operators.firstValue();
        if (str_.isEmpty()) {
            return;
        }
        call = str_.charAt(0) == PAR;
    }
    private void setupArray() {
        String str_ = operators.firstValue();
        if (str_.isEmpty()) {
            return;
        }
        array = str_.charAt(0) == ARR;
    }

    public boolean isImplMiddle() {
        return implMiddle;
    }

    public boolean isTernaryOp() {
        return ternaryOp;
    }

    public int getArgOffset() {
        return argOffset;
    }

    public String getClName() {
        return clName;
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
        return call;
    }

    public boolean isInstance() {
        return instance;
    }

    public boolean isArray() {
        return array;
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

    public void initValues() {
        values = new StrTypes();
    }

    public StrTypes getOperators() {
        return operators;
    }

    public void setOperators(StrTypes _operators) {
        operators = _operators;
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

    public int getDeltaAnnot() {
        return deltaAnnot;
    }

    public void setDeltaAnnot(int _d) {
        this.deltaAnnot = _d;
    }

    public String getRetSwitch() {
        return retSwitch;
    }

    public void setRetSwitch(String _r) {
        this.retSwitch = _r;
    }
}
