package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecInterfaceBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;

public final class InterfaceFctConstructor extends AbstractInvokingConstructor {
    private String className = EMPTY_STRING;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public InterfaceFctConstructor(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(ContextEl _conf) {
        int index_ = getIndexChild();
        if (index_ <= 0) {
            return null;
        }
        String cl_ = getMethodName();
        int leftPar_ = cl_.indexOf(PAR_LEFT) + 1;
        cl_ = cl_.substring(leftPar_, cl_.lastIndexOf(PAR_RIGHT));
        cl_ = ResolvingImportTypes.resolveAccessibleIdType(_conf, leftPar_,cl_);
        partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        if (!(_conf.getClasses().getClassBody(cl_) instanceof ExecInterfaceBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _conf.getAnalyzing().getLocalizer().addError(call_);
            return null;
        }
        OperationNode par_ = getParent();
        String className_ = EMPTY_STRING;
        if (par_.getParent() instanceof CastOperation) {
            className_ = ((CastOperation)par_.getParent()).getClassName();
        }
        className = className_;
        if (!ExplicitOperation.customCast(className_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _conf.getAnalyzing().getLocalizer().addError(call_);
            return null;
        }
        String idCl_ = StringExpUtil.getIdFromAllTypes(className_);
        ExecRootBlock sub_ = _conf.getClasses().getClassBody(idCl_);
        if (!(sub_ instanceof ExecInterfaceBlock)|| !sub_.isStaticType()) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _conf.getAnalyzing().getLocalizer().addError(call_);
            return null;
        }
        String superClass_ = Templates.getOverridingFullTypeByBases(className_, cl_, _conf);
        if (superClass_.isEmpty()) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _conf.getAnalyzing().getLocalizer().addError(call_);
            return null;
        }
        return new ClassArgumentMatching(superClass_);
    }

    @Override
    void checkPositionBasis(ContextEl _conf) {
        int index_ = getIndexChild();
        if (index_ <= 0) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            call_.setIndexFile(getFullIndexInEl());
            //key word len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorEnd());
            _conf.getAnalyzing().getLocalizer().addError(call_);
        }
    }

    public String getClassName() {
        return className;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
