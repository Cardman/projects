package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;

public final class VarargOperation extends LeafOperation {

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
    public void analyze(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl() + offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (m_ == null ||!m_.isCallMethodCtor()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_conf.getAnalysisMessages().getUnexpectedLeaf(),
                    _conf.getKeyWords().getKeyWordVararg());
            _conf.getAnalyzing().getLocalizer().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        if (!isFirstChildInParent()) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            varg_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            varg_.buildError(_conf.getAnalysisMessages().getUnexpectedLeaf(),
                    _conf.getKeyWords().getKeyWordVararg());
            _conf.getAnalyzing().getLocalizer().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        int afterLeftPar_ = className.indexOf(PAR_LEFT) + 1;
        String str_ = className.substring(afterLeftPar_, className.lastIndexOf(PAR_RIGHT));
        int off_ = StringList.getFirstPrintableCharIndex(str_);
        str_ = ResolvingImportTypes.resolveCorrectTypeAccessible(_conf,afterLeftPar_+off_,str_);
        partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        setResultClass(new ClassArgumentMatching(str_));
        className = str_;
        setSimpleArgument(new Argument());
    }

    public String getClassName() {
        return className;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
