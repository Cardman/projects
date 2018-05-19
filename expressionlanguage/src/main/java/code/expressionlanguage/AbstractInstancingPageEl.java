package code.expressionlanguage;

import code.expressionlanguage.methods.AloneBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.methods.Line;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.StringList;


public abstract class AbstractInstancingPageEl extends AbstractPageEl {

    private final StringList intializedInterfaces = new StringList();

    private Argument argument;

    private final StringList calledConstructors = new StringList();

    private boolean calledImplicitConstructor;

    private ConstructorBlock usedConstructor;
    private boolean initializedFields;

    private boolean firstField;

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _argument) {
        argument = _argument;
    }

    public StringList getCalledConstructors() {
        return calledConstructors;
    }

    public boolean isCalledImplicitConstructor() {
        return calledImplicitConstructor;
    }

    public void setCalledImplicitConstructor(boolean _calledImplicitConstructor) {
        calledImplicitConstructor = _calledImplicitConstructor;
    }

    public ConstructorBlock getUsedConstructor() {
        return usedConstructor;
    }

    public void setUsedConstructor(ConstructorBlock _usedConstructor) {
        usedConstructor = _usedConstructor;
    }

    public boolean isInitializedFields() {
        return initializedFields;
    }

    public void setInitializedFields(boolean _initializedFields) {
        initializedFields = _initializedFields;
    }

    public boolean isFirstField() {
        return firstField;
    }

    public void setFirstField(boolean _firstField) {
        firstField = _firstField;
    }

    public StringList getIntializedInterfaces() {
        return intializedInterfaces;
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        Classes classes_ = _context.getClasses();
        ReadWrite rw_ = getReadWrite();
        boolean implicitConstr_ = false;
        if (usedConstructor == null) {
            implicitConstr_ = true;
        } else if (usedConstructor.implicitConstr()) {
            implicitConstr_ = true;
        }
        if (implicitConstr_) {
            String instClass_ = getGlobalArgument().getObjectClassName(_context);
            String curClass_ = getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(curClass_).first();
            String formatted_ = Templates.getFullTypeByBases(instClass_, curClassBase_, _context);
            RootBlock class_ = classes_.getClassBody(curClassBase_);
            if (class_ instanceof UniqueRootedBlock) {
                UniqueRootedBlock root_ = (UniqueRootedBlock) class_;
                String superClassBase_ = root_.getSuperClass(_context);
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
            if (usedConstructor != null) {
                bl_ = usedConstructor.getFirstChild();
            }
            if (!(bl_ instanceof Line)) {
                initFields_ = true;
            } else {
                Line l_ = (Line) bl_;
                if (l_.getCalledInterface().isEmpty()) {
                    initFields_ = true;
                }
            }
            if (!firstField && initFields_) {
                Block first_ = class_.getFirstChild();
                firstField = true;
                rw_.setBlock(first_);
                return false;
            }
        }
        return true;
    }
    public abstract void setArgumentForConstructor();

    public void exitFromConstructor() {
        setArgumentForConstructor();
        setNullReadWrite();
    }

    @Override
    public void setReturnedArgument() {
        setArgumentForConstructor();
    }

    @Override
    public void postBlock(ContextEl _context) {
        if (initializedFields) {
            exitFromConstructor();
            return;
        }
        if (usedConstructor != null) {
            Block bl_ = usedConstructor.getFirstChild();
            if (bl_ != null) {
                initializedFields = true;
                getReadWrite().setBlock(bl_);
                return;
            }
        }
        exitFromConstructor();
    }
    @Override
    public Block getCurrentBlockRoot() {
        Block root_ = getBlockRoot();
        if (initializedFields) {
            root_ = usedConstructor;
        }
        return root_;
    }
    @Override
    public void endRoot(ContextEl _context) {
        exitFromConstructor();
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
        Block initBlock_ = null;
        if (usedConstructor != null) {
            initBlock_ = usedConstructor.getFirstChild();
        }
        if (initBlock_ != null) {
            initializedFields = true;
            rw_.setBlock(initBlock_);
            return;
        }
        setNullReadWrite();
    }
}
