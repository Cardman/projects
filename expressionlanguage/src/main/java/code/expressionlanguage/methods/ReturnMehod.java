package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.exceptions.BadReturnException;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class ReturnMehod extends Leaf implements CallingFinally {

    private final String expression;

    private int expressionOffset;

    private CustList<OperationNode> opRet;

    public ReturnMehod(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
        setExitable(true);
        setStoppable(true);
    }

    public ReturnMehod(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _expression, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        setExitable(true);
        setStoppable(true);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof FunctionBlock) {
                return;
            }
            b_ = b_.getParent();
        }
        throw new BadReturnException(_cont.joinPages());
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        return tr_;
    }
    public boolean isEmpty() {
        return expression.trim().isEmpty();
    }

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getElRet() {
        return new ExpressionLanguage(opRet);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        if (getNextSibling() != null) {
            throw new BadReturnException(_cont.joinPages());
        }
        LgNames stds_ = _cont.getStandards();
        String retType_ = stds_.getAliasVoid();
        BracedBlock par_ = getParent();
        while (par_ != null) {
            if (par_ instanceof Returnable) {
                Returnable meth_ = null;
                meth_ = (Returnable) par_;
                retType_ = meth_.getReturnType(stds_);
                break;
            }
            par_ = par_.getParent();
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        if (StringList.quickEq(retType_, stds_.getAliasVoid())) {
            if (!isEmpty()) {
                throw new BadReturnException(_cont.joinPages());
            }
        } else {
            opRet = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
            StringMap<StringList> vars_ = new StringMap<StringList>();
            if (!f_.isStaticContext()) {
                String globalClass_ = page_.getGlobalClass();
                String curClassBase_ = StringList.getAllTypes(globalClass_).first();
                for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypes()) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(opRet.last().getResultClass().getName());
            mapping_.setParam(retType_);
            if (!Templates.isGenericCorrect(mapping_, _cont)) {
                throw new DynamicCastClassException(_cont.joinPages());
            }
        }
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
        if (opRet == null) {
            return;
        }
        PageEl p_ = _cont.getLastPage();
        p_.setGlobalOffset(expressionOffset);
        for (OperationNode o: opRet) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_RETURN;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        if (!isEmpty()) {
            ip_.setOffset(0);
            ip_.setGlobalOffset(expressionOffset);
            ExpressionLanguage el_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getElRet());
            Argument arg_ = el_.calculateMember(_cont);
            el_.setCurrentOper(null);
            ip_.clearCurrentEls();
            LgNames stds_ = _cont.getStandards();
            String retType_ = stds_.getAliasVoid();
            BracedBlock par_ = getParent();
            while (par_ != null) {
                if (par_ instanceof Returnable) {
                    Returnable meth_ = null;
                    meth_ = (Returnable) par_;
                    retType_ = meth_.getReturnType(stds_);
                    break;
                }
                par_ = par_.getParent();
            }
            retType_ = _cont.getLastPage().formatVarType(retType_, _cont);
            if (PrimitiveTypeUtil.primitiveTypeNullObject(retType_, arg_.getStruct(), _cont)) {
                String null_;
                null_ = stds_.getAliasNullPe();
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()),null_));
            }
            if (!arg_.isNull()) {
                Mapping map_ = new Mapping();
                String rightClass_ = arg_.getObjectClassName(_cont);
                map_.setArg(rightClass_);
                map_.setParam(retType_);
                if (!Templates.isCorrect(map_, _cont)) {
                    String cast_;
                    cast_ = stds_.getAliasCast();
                    throw new InvokeException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,retType_,RETURN_LINE,_cont.joinPages())),cast_));
                }
            }
            if (arg_.getStruct() instanceof NumberStruct || arg_.getStruct() instanceof CharStruct) {
                ClassArgumentMatching resCl_ = new ClassArgumentMatching(retType_);
                arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, arg_.getStruct(), _cont));
            }
            _cont.getLastPage().setReturnedArgument(arg_);
        } else {
            FunctionBlock f_ = getFunction();
            if (f_ instanceof MethodBlock) {
                Argument void_ = Argument.createVoid();
                _cont.getLastPage().setReturnedArgument(void_);
            } else if (f_ instanceof ConstructorBlock) {
                _cont.getLastPage().setArgumentForConstructor();
            }
        }
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        FunctionBlock f_ = getFunction();
        PageEl ip_ = _conf.getLastPage();
        while (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            ip_.setFinallyToProcess(false);
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((TryBlockStack)bl_).setCalling(this);
                return;
            }
        }
        if (!(f_ instanceof AloneBlock)) {
            ip_.setNullReadWrite();
            return;
        }
        Block bn_ = ((AloneBlock)f_).getNextSibling();
        ReadWrite rw_ = ip_.getReadWrite();
        if (bn_ != null) {
            rw_.setBlock(bn_);
            return;
        }
        ConstructorBlock ctor_ = ip_.getCallingConstr().getUsedConstructor();
        Block initBlock_ = null;
        if (ctor_ != null) {
            initBlock_ = ctor_.getFirstChild();
        }
        if (initBlock_ != null) {
            ip_.getCallingConstr().setInitializedFields(true);
            rw_.setBlock(initBlock_);
            return;
        }
        ip_.setNullReadWrite();
    }
}
