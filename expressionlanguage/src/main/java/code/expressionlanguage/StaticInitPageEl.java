package code.expressionlanguage;

import code.expressionlanguage.methods.AloneBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.methods.InstanceBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.Returnable;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.StackableBlock;
import code.expressionlanguage.methods.StaticBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.WithEl;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.util.StringList;

public final class StaticInitPageEl extends AbstractPageEl {

    @Override
    public boolean checkCondition(ContextEl _context) {
        Classes classes_ = _context.getClasses();

        String curClass_ = getGlobalClass();
        String curClassBase_ = StringList.getAllTypes(curClass_).first();
        RootBlock root_ =  classes_.getClassBody(curClassBase_);
        if (root_ instanceof UniqueRootedBlock) {
            String superClass_ = ((UniqueRootedBlock) root_).getSuperClass(_context);
            if (classes_.getClassBody(superClass_) != null) {
                InitClassState res_ = classes_.getLocks().getState(_context, superClass_);
                if (res_ == InitClassState.NOT_YET) {
                    _context.setInitClass(new NotInitializedClass(superClass_));
                    return false;
                }
                if (res_ == InitClassState.ERROR) {
                    CausingErrorStruct causing_ = new CausingErrorStruct(superClass_);
                    _context.setException(causing_);
                    return false;
                }
            }
        }
        for (String i: root_.getStaticInitInterfaces()) {
            String t_ = StringList.removeAllSpaces(i);
            InitClassState res_ = classes_.getLocks().getState(_context, t_);
            if (res_ == InitClassState.NOT_YET) {
                _context.setInitClass(new NotInitializedClass(t_));
                return false;
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(t_);
                _context.setException(causing_);
                return false;
            }
        }
        return true;
    }
    @Override
    public void tryProcessEl(ContextEl _context) {
        ReadWrite rw_ = getReadWrite();
        Block en_ = rw_.getBlock();
        if (en_ instanceof WithEl) {
            setCurrentBlock(en_);
            ((WithEl)en_).processEl(_context);
            return;
        }
        if (en_ instanceof Returnable) {
            en_.processBlock(_context);
            return;
        }
        if (en_ instanceof StaticBlock) {
            rw_.setBlock(en_.getFirstChild());
            return;
        }
        if (en_ instanceof InstanceBlock) {
            en_.processBlock(_context);
            return;
        }
        endRoot(_context);
    }
    @Override
    public ParentStackBlock getNextBlock(Block _bl,ContextEl _conf) {
        ParentStackBlock parElt_;
        Block nextSibling_ = _bl.getNextSibling();
        if (nextSibling_ != null) {
            parElt_ = new ParentStackBlock(null);
        } else if (_bl instanceof StackableBlock) {
            BracedBlock n_ = _bl.getParent();
            //n_ != null because strictly in class
            Block root_ = getBlockRoot();
            if (!noBlock()) {
                parElt_ =  new ParentStackBlock(n_);
            } else if (n_ == root_) {
                //directly at the root => last element in the block root
                parElt_ = null;
            } else {
                Block next_ = n_.getNextSibling();
                if (next_ != null) {
                    parElt_ = new ParentStackBlock(null);
                } else {
                    parElt_ = null;
                }
            }
        } else {
            parElt_ = null;
        }
        return parElt_;
    }
    @Override
    public void setReturnedArgument() {
    }

    @Override
    public void postBlock(ContextEl _context) {
        String curClass_ = getGlobalClass();
        String curClassBase_ = StringList.getAllTypes(curClass_).first();
        _context.getClasses().getLocks().successClass(_context, curClassBase_);
        setNullReadWrite();
    }

    @Override
    public void endRoot(ContextEl _context) {
        Classes classes_ = _context.getClasses();
        String curClass_ = getGlobalClass();
        String curClassBase_ = StringList.getAllTypes(curClass_).first();
        classes_.getLocks().successClass(_context, curClassBase_);
        setNullReadWrite();
    }
    @Override
    public void postReturn(ContextEl _context) {
        Block bl_ = getCurrentBlock();
        FunctionBlock f_ = bl_.getFunction();
        if (!(f_ instanceof AloneBlock)) {
            setNullReadWrite();
            return;
        }
        Block bn_ = ((AloneBlock)f_).getNextSibling();
        ReadWrite rw_ = getReadWrite();
        if (bn_ != null) {
            rw_.setBlock(bn_);
            return;
        }
        setNullReadWrite();
    }

}
