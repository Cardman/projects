package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadConstructorCall;
import code.expressionlanguage.errors.custom.BadInheritedClass;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.Line;
import code.expressionlanguage.opers.exec.ExecAbstractInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecInterfaceInvokingConstructor;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.text.OperationsSequence;
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
        cl_ = _conf.resolveAccessibleIdType(cl_);
        if (!(_conf.getClasses().getClassBody(cl_) instanceof InterfaceBlock)) {
            BadConstructorCall call_ = new BadConstructorCall();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(call_);
            return null;
        }
        String superClass_ = Templates.getFullTypeByBases(clCurName_, cl_, _conf);
        if (superClass_ == null) {
            BadConstructorCall call_ = new BadConstructorCall();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(call_);
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
            _conf.getClasses().addError(call_);
        }
        Block f_ = br_.getFirstChild();
        if (f_ != curBlock_) {
            StringList previousInts_ = new StringList();
            if (f_ instanceof Line){
                if (!((Line)f_).getExp().isEmpty()) {
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
                        _conf.getClasses().addError(call_);
                    } else {
                        if (!((Line)f_).getExp().isEmpty()) {
                            //the case ((Line)f_).getExp().isEmpty() leads already to an error
                            ExecOperationNode root_ = ((Line)f_).getExp().last();
                            if (!(root_ instanceof ExecAbstractInvokingConstructor)) {
                                //error
                                BadConstructorCall call_ = new BadConstructorCall();
                                call_.setFileName(curLine_.getFile().getFileName());
                                call_.setIndexFile(curLine_.getExpressionOffset());
                                call_.setLocalOffset(getFullIndexInEl()+ curLine_.getExpressionOffset());
                                _conf.getClasses().addError(call_);
                            }
                        }
                    }
                    break;
                }
                if (n_ instanceof Line){
                    if (!((Line)n_).getExp().isEmpty()) {
                        ExecOperationNode root_ = ((Line)n_).getExp().last();
                        if (root_ instanceof ExecInterfaceInvokingConstructor) {
                            ExecAbstractInvokingConstructor ctor_ = (ExecAbstractInvokingConstructor) root_;
                            ConstructorId cid_ = ctor_.getConstId();
                            if (cid_ != null) {
                                String cl_ = cid_.getName();
                                cl_ = Templates.getIdFromAllTypes(cl_);
                                if (!previousInts_.isEmpty()) {
                                    String sup_ = previousInts_.last();
                                    if (PrimitiveTypeUtil.canBeUseAsArgument(false, cl_, sup_, _conf)) {
                                        BadInheritedClass undef_;
                                        undef_ = new BadInheritedClass();
                                        undef_.setClassName(cl_);
                                        undef_.setFileName(n_.getFile().getFileName());
                                        undef_.setIndexFile(0);
                                        _conf.getClasses().addError(undef_);
                                    }
                                }
                                previousInts_.add(cl_);
                            }
                        }
                    }
                }
                f_ = n_;
            }
            ConstructorId cid_ = getConstId();
            if (cid_ != null) {
                String cl_ = cid_.getName();
                cl_ = Templates.getIdFromAllTypes(cl_);
                if (!previousInts_.isEmpty()) {
                    String sup_ = previousInts_.last();
                    if (PrimitiveTypeUtil.canBeUseAsArgument(false, cl_, sup_, _conf)) {
                        BadInheritedClass undef_;
                        undef_ = new BadInheritedClass();
                        undef_.setClassName(cl_);
                        undef_.setFileName(curBlock_.getFile().getFileName());
                        undef_.setIndexFile(0);
                        _conf.getClasses().addError(undef_);
                    }
                }
            }
        }
    
    }

}
