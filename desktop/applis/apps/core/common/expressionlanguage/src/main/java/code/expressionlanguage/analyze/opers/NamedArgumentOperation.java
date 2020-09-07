package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.stds.LgNames;
import code.util.IntTreeMap;
import code.util.StringList;

public final class NamedArgumentOperation extends AbstractUnaryOperation {

    private int offset;
    private int offsetTr;
    private int index = -1;
    private String name;
    private NamedFunctionBlock customMethod;
    public NamedArgumentOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        offset = vs_.firstKey();
        String first_ = vs_.firstValue();
        name = first_.trim();
        offsetTr = StringList.getFirstPrintableCharIndex(first_);
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (isNotChildOfCall(m_)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_conf.getAnalysisMessages().getDuplicatedParamName(),
                    name);
            _conf.getAnalyzing().getLocalizer().addError(varg_);
            getErrs().add(varg_.getBuiltError());
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        OperationNode child_ = getFirstChild();
        setResultClass(ClassArgumentMatching.copy(child_.getResultClass()));
    }

    public int getOffset() {
        return offset;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public NamedFunctionBlock getCustomMethod() {
        return customMethod;
    }

    public void setCustomMethod(NamedFunctionBlock customMethod) {
        this.customMethod = customMethod;
    }

    public String getName() {
        return name;
    }

    public int getOffsetTr() {
        return offsetTr;
    }
}
