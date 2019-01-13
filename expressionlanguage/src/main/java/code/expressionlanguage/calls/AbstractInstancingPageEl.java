package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CustomFoundConstructor;
import code.expressionlanguage.calls.util.InstancingStep;
import code.expressionlanguage.calls.util.NotInitializedFields;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.Line;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.WithEl;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.StringList;


public abstract class AbstractInstancingPageEl extends AbstractPageEl implements ReturnablePageEl,WithElPageEl {

    private boolean calledImplicitConstructor;

    private boolean firstField;

    public boolean isFirstField() {
        return firstField;
    }

    public void setFirstField(boolean _firstField) {
        firstField = _firstField;
    }

    @Override
    public void tryProcessEl(ContextEl _context) {
        ReadWrite rw_ = getReadWrite();
        Block en_ = rw_.getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context);
            return;
        }
        endRoot();
    }
    @Override
    public ParentStackBlock getNextBlock(Block _bl) {
        ParentStackBlock parElt_;
        Block nextSibling_ = _bl.getNextSibling();
        if (nextSibling_ != null) {
            parElt_ = new ParentStackBlock(null);
        } else {
            BracedBlock n_ = _bl.getParent();
            //n_ != null because strictly in class
            if (hasBlock()) {
                parElt_ =  new ParentStackBlock(n_);
            } else {
                //directly at the root => last element in the block root
                parElt_ = null;
            }
        }
        return parElt_;
    }
    @Override
    public final boolean checkCondition(ContextEl _context) {
        Classes classes_ = _context.getClasses();
        boolean implicitConstr_ = false;
        ConstructorBlock ctor_ = (ConstructorBlock) getBlockRoot();
        if (ctor_ == null) {
            implicitConstr_ = true;
        } else if (ctor_.implicitConstr()) {
            implicitConstr_ = true;
        }
        if (implicitConstr_) {
            String instClass_ = getGlobalArgument().getObjectClassName(_context);
            String curClass_ = getGlobalClass();
            String curClassBase_ = Templates.getIdFromAllTypes(curClass_);
            String formatted_ = Templates.getFullTypeByBases(instClass_, curClassBase_, _context);
            RootBlock class_ = classes_.getClassBody(curClassBase_);
            if (class_ instanceof UniqueRootedBlock) {
                UniqueRootedBlock root_ = (UniqueRootedBlock) class_;
                String id_ = root_.getImportedDirectGenericSuperClass();
                String superClassBase_ = Templates.getIdFromAllTypes(id_);
                String objectClassName_ = _context.getStandards().getAliasObject();
                if (!calledImplicitConstructor && !StringList.quickEq(superClassBase_, objectClassName_)) {
                    calledImplicitConstructor = true;
                    ConstructorId super_ = new ConstructorId(superClassBase_, new StringList(), false);
                    Argument global_ = getGlobalArgument();
                    String generic_ = Templates.getFullTypeByBases(formatted_, superClassBase_, _context);
                    _context.setCallCtor(new CustomFoundConstructor(generic_, EMPTY_STRING, -1, super_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER_IMPL));
                    return false;
                }
            }
            boolean initFields_ = false;
            Block bl_ = null;
            if (ctor_ != null) {
                bl_ = ctor_.getFirstChild();
            }
            if (!(bl_ instanceof Line)) {
                initFields_ = true;
            } else {
                Line l_ = (Line) bl_;
                if (!l_.isCallInts()) {
                    initFields_ = true;
                }
            }
            if (!firstField && initFields_) {
                firstField = true;
                Argument global_ = getGlobalArgument();
                _context.setInitFields(new NotInitializedFields(curClass_, global_));
                return false;
            }
        }
        return true;
    }

    private void exitFromConstructor() {
        setNullReadWrite();
    }

    @Override
    public void postBlock(ContextEl _context) {
        exitFromConstructor();
    }

    private void endRoot() {
        exitFromConstructor();
    }
    @Override
    public void postReturn() {
        setNullReadWrite();
    }
}
