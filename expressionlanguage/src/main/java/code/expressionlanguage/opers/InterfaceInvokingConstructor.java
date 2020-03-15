package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadConstructorCall;
import code.expressionlanguage.errors.custom.BadInheritedClass;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.Line;
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
        String clCurName_ = _conf.getGlobalClass();
        String cl_ = getMethodName();
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        cl_ = _conf.resolveAccessibleIdType(cl_.indexOf(PAR_LEFT)+1,cl_);
        if (!(_conf.getClasses().getClassBody(cl_) instanceof InterfaceBlock)) {
            BadConstructorCall call_ = new BadConstructorCall();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.addError(call_);
            return null;
        }
        String superClass_ = Templates.getFullTypeByBases(clCurName_, cl_, _conf);
        if (superClass_ == null) {
            BadConstructorCall call_ = new BadConstructorCall();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
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
            BadConstructorCall call_ = new BadConstructorCall();
            call_.setFileName(curLine_.getFile().getFileName());
            call_.setIndexFile(curLine_.getExpressionOffset());
            call_.setLocalOffset(getFullIndexInEl()+ curLine_.getExpressionOffset());
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
                        BadConstructorCall call_ = new BadConstructorCall();
                        call_.setFileName(curLine_.getFile().getFileName());
                        call_.setIndexFile(curLine_.getExpressionOffset());
                        call_.setLocalOffset(getFullIndexInEl()+ curLine_.getExpressionOffset());
                        _conf.addError(call_);
                    } else {
                        ExecOperationNode root_ = ((Line)f_).getExp().last();
                        if (!(root_ instanceof ExecAbstractInvokingConstructor)) {
                            //error
                            BadConstructorCall call_ = new BadConstructorCall();
                            call_.setFileName(curLine_.getFile().getFileName());
                            call_.setIndexFile(curLine_.getExpressionOffset());
                            call_.setLocalOffset(getFullIndexInEl()+ curLine_.getExpressionOffset());
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
            if (PrimitiveTypeUtil.canBeUseAsArgument(_cl, sup_, _conf)) {
                BadInheritedClass undef_;
                undef_ = new BadInheritedClass();
                undef_.setClassName(_cl);
                undef_.setFileName(_n.getFile().getFileName());
                undef_.setIndexFile(0);
                _conf.addError(undef_);
            }
        }
    }

}
