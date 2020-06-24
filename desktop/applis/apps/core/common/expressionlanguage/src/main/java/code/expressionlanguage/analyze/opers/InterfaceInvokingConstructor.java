package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.BracedBlock;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecInterfaceBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;

import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;

public final class InterfaceInvokingConstructor extends AbstractInvokingConstructor {

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public InterfaceInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(ContextEl _conf) {
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
            _conf.addError(call_);
            return null;
        }
        String clCurName_ = _conf.getAnalyzing().getGlobalClass();
        String superClass_ = Templates.getOverridingFullTypeByBases(clCurName_, cl_, _conf);
        if (superClass_.isEmpty()) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _conf.addError(call_);
            return null;
        }
        return new ClassArgumentMatching(superClass_);
    }

    @Override
    void checkPosition(ContextEl _conf) {
        Block curBlock_ = _conf.getAnalyzing().getCurrentBlock();
        Line curLine_ = (Line)curBlock_;
        BracedBlock br_ = curBlock_.getParent();
        if (br_.getParent() instanceof InterfaceBlock) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(curLine_.getFile().getFileName());
            call_.setIndexFile(getFullIndexInEl()+ curLine_.getExpressionOffset());
            //key word len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorIntNotFromInt());
            _conf.addError(call_);
        }
        Block f_ = br_.getFirstChild();
        if (f_ != curBlock_) {
            StringList previousInts_ = new StringList();
            if (f_ instanceof Line){
                ConstructorId cid_ = ((Line)f_).getCallInts();
                if (cid_ != null) {
                    String cl_ = cid_.getName();
                    cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                    previousInts_.add(cl_);
                }
            }
            while (true) {
                Block n_ = f_.getNextSibling();
                if (n_ == curBlock_) {
                    if (!(f_ instanceof Line)) {
                        //error
                        FoundErrorInterpret call_ = new FoundErrorInterpret();
                        call_.setFileName(curLine_.getFile().getFileName());
                        call_.setIndexFile(getFullIndexInEl()+ curLine_.getExpressionOffset());
                        //key word len
                        call_.buildError(_conf.getAnalysisMessages().getCallCtorIntAfterSuperThis());
                        _conf.addError(call_);
                    } else {
                        if (!((Line)f_).isCallFromCtorToCtor()) {
                            //error
                            FoundErrorInterpret call_ = new FoundErrorInterpret();
                            call_.setFileName(curLine_.getFile().getFileName());
                            call_.setIndexFile(getFullIndexInEl()+ curLine_.getExpressionOffset());
                            //key word len
                            call_.buildError(_conf.getAnalysisMessages().getCallCtorIntAfterSuperThis());
                            _conf.addError(call_);
                        }
                    }
                    break;
                }
                if (n_ instanceof Line){
                    ConstructorId cid_ = ((Line)n_).getCallInts();
                    if (cid_ != null) {
                        String cl_ = cid_.getName();
                        cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                        checkInherits(_conf, previousInts_, n_, cl_);
                        previousInts_.add(cl_);
                    }
                }
                f_ = n_;
            }
            ConstructorId cid_ = getConstId();
            if (cid_ != null) {
                String cl_ = cid_.getName();
                cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                checkInherits(_conf, previousInts_, curBlock_, cl_);
            }
        }
    
    }

    private static void checkInherits(ContextEl _conf, StringList _previousInts, Block _n, String _cl) {
        if (!_previousInts.isEmpty()) {
            String sup_ = _previousInts.last();
            ExecRootBlock supType_ = _conf.getClasses().getClassBody(sup_);
            if (supType_.isSubTypeOf(_cl,_conf)) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(_n.getFile().getFileName());
                undef_.setIndexFile(0);
                //current type len
                undef_.buildError(_conf.getAnalysisMessages().getCallCtorIntInherits(),
                        sup_,
                        _cl
                );
                _conf.addError(undef_);
            }
        }
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
