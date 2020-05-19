package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.exec.ExecAbstractInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecInterfaceInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.util.StringList;

public final class InterfaceInvokingConstructor extends AbstractInvokingConstructor {

    public InterfaceInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(ContextEl _conf) {
        String cl_ = getMethodName();
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        cl_ = ResolvingImportTypes.resolveAccessibleIdType(_conf,cl_.indexOf(PAR_LEFT)+1,cl_);
        if (!(_conf.getClasses().getClassBody(cl_) instanceof InterfaceBlock)) {
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
                ExecOperationNode root_ = ((Line)f_).getExp().last();
                if (root_ instanceof ExecInterfaceInvokingConstructor) {
                    ExecAbstractInvokingConstructor ctor_ = (ExecAbstractInvokingConstructor) root_;
                    ConstructorId cid_ = ctor_.getConstId();
                    if (cid_ != null) {
                        String cl_ = cid_.getName();
                        cl_ = Templates.getIdFromAllTypes(cl_);
                        previousInts_.add(cl_);
                    }
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
                        ExecOperationNode root_ = ((Line)f_).getExp().last();
                        if (!(root_ instanceof ExecAbstractInvokingConstructor)) {
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
                    ExecOperationNode root_ = ((Line)n_).getExp().last();
                    if (root_ instanceof ExecInterfaceInvokingConstructor) {
                        ExecAbstractInvokingConstructor ctor_ = (ExecAbstractInvokingConstructor) root_;
                        ConstructorId cid_ = ctor_.getConstId();
                        if (cid_ != null) {
                            String cl_ = cid_.getName();
                            cl_ = Templates.getIdFromAllTypes(cl_);
                            checkInherits(_conf, previousInts_, n_, cl_);
                            previousInts_.add(cl_);
                        }
                    }
                }
                f_ = n_;
            }
            ConstructorId cid_ = getConstId();
            if (cid_ != null) {
                String cl_ = cid_.getName();
                cl_ = Templates.getIdFromAllTypes(cl_);
                checkInherits(_conf, previousInts_, curBlock_, cl_);
            }
        }
    
    }

    private static void checkInherits(ContextEl _conf, StringList _previousInts, Block _n, String _cl) {
        if (!_previousInts.isEmpty()) {
            String sup_ = _previousInts.last();
            RootBlock supType_ = _conf.getClasses().getClassBody(sup_);
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

}
