package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.opers.ReductibleOperable;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;

public final class DefaultValueOperation extends LeafOperation implements ReductibleOperable {

    private String className;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    DefaultValueOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        int afterLeftPar_ = str_.indexOf(PAR_LEFT) + 1;
        String realCl_ = str_.substring(afterLeftPar_, str_.lastIndexOf(PAR_RIGHT));
        int offLoc_ = StringList.getFirstPrintableCharIndex(realCl_);
        String classStr_;
        classStr_ = ResolvingImportTypes.resolveCorrectType(_conf, afterLeftPar_ + offLoc_, realCl_);
        partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        className = classStr_;
        setResultClass(new ClassArgumentMatching(className));
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        setArg(this, _conf, className);
    }
    private static void setArg(OperationNode _current, ContextEl _conf, String _className) {
        if (_className.contains(AnaTemplates.PREFIX_VAR_TYPE)) {
            return;
        }
        Argument a_ = new Argument(PrimitiveTypeUtil.defaultValue(_className,_conf.getAnalyzing().getStandards()));
        _current.setSimpleArgumentAna(a_, _conf);
    }

    public String getClassName() {
        return className;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
