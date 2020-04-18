package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.exec.ExecAbstractInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecInterfaceInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.StringList;

public final class InterfaceInvokingConstructor extends AbstractInvokingConstructor {

    public InterfaceInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(Analyzable _conf) {
        String cl_ = getMethodName();
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        cl_ = _conf.resolveAccessibleIdType(cl_.indexOf(PAR_LEFT)+1,cl_);
        if (!(_conf.getClasses().getClassBody(cl_) instanceof InterfaceBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getContextEl().getAnalysisMessages().getCallCtorIntFromSuperInt());
            _conf.addError(call_);
            return null;
        }
        String clCurName_ = _conf.getGlobalClass();
        String superClass_ = Templates.getFullTypeByBases(clCurName_, cl_, _conf);
        if (superClass_ == null) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getContextEl().getAnalysisMessages().getCallCtorIntFromSuperInt());
            _conf.addError(call_);
            return null;
        }
        return new ClassArgumentMatching(superClass_);
    }

    @Override
    void checkPosition(Analyzable _conf) {
        Block curBlock_ = _conf.getCurrentBlock();
        Line curLine_ = (Line)curBlock_;
        BracedBlock br_ = curBlock_.getParent();
        if (br_.getParent() instanceof InterfaceBlock) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(curLine_.getFile().getFileName());
            call_.setIndexFile(getFullIndexInEl()+ curLine_.getExpressionOffset());
            //key word len
            call_.buildError(_conf.getContextEl().getAnalysisMessages().getCallCtorIntNotFromInt());
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
                        call_.buildError(_conf.getContextEl().getAnalysisMessages().getCallCtorIntAfterSuperThis());
                        _conf.addError(call_);
                    } else {
                        ExecOperationNode root_ = ((Line)f_).getExp().last();
                        if (!(root_ instanceof ExecAbstractInvokingConstructor)) {
                            //error
                            FoundErrorInterpret call_ = new FoundErrorInterpret();
                            call_.setFileName(curLine_.getFile().getFileName());
                            call_.setIndexFile(getFullIndexInEl()+ curLine_.getExpressionOffset());
                            //key word len
                            call_.buildError(_conf.getContextEl().getAnalysisMessages().getCallCtorIntAfterSuperThis());
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

    private static void checkInherits(Analyzable _conf, StringList _previousInts, Block _n, String _cl) {
        if (!_previousInts.isEmpty()) {
            String sup_ = _previousInts.last();
            RootBlock supType_ = _conf.getContextEl().getClasses().getClassBody(sup_);
            if (supType_.isSubTypeOf(_cl,_conf)) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(_n.getFile().getFileName());
                undef_.setIndexFile(0);
                //current type len
                undef_.buildError(_conf.getContextEl().getAnalysisMessages().getCallCtorIntInherits(),
                        sup_,
                        _cl
                );
                _conf.addError(undef_);
            }
        }
    }

}
