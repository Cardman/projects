package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.VarargError;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class VarargOperation extends LeafOperation implements VarargOperable {

    private String className;
    private int offset;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public VarargOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        offset = _op.getValues().firstKey();
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl() + offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (m_ == null ||!m_.isCallMethodCtor()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(VAR_ARG);
            _conf.addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        if (!isFirstChild()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(VAR_ARG);
            _conf.addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        int afterLeftPar_ = className.indexOf(PAR_LEFT) + 1;
        String str_ = className.substring(afterLeftPar_, className.lastIndexOf(PAR_RIGHT));
        int off_ = StringList.getFirstPrintableCharIndex(str_);
        str_ = _conf.resolveCorrectType(afterLeftPar_+off_,str_);
        partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
        setResultClass(new ClassArgumentMatching(str_));
        className = str_;
        setSimpleArgument(new Argument());
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
