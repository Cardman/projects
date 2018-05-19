package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class ReturnMehod extends AbruptBlock implements CallingFinally  {

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
        UnexpectedTagName un_ = new UnexpectedTagName();
        un_.setFileName(getFile().getFileName());
        un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
        _cont.getClasses().getErrorsDet().add(un_);
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
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
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
            if (isEmpty()) {
                return;
            }
        }
        _cont.setRootAffect(false);
        opRet = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opRet.isEmpty()) {
            return;
        }
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
        if (StringList.quickEq(retType_, stds_.getAliasVoid())) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, expressionOffset));
            _cont.getClasses().getErrorsDet().add(cast_);
            return;
        }
        if (!Templates.isGenericCorrect(mapping_, _cont)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, expressionOffset));
            _cont.getClasses().getErrorsDet().add(cast_);
        }
//        if (PrimitiveTypeUtil.isPrimitive(retType_, _cont)) {
//            opRet.last().getResultClass().setUnwrapObject(retType_);
//        }
    }

    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        super.abrupt(_an, _anEl);
        BracedBlock par_ = getParent();
        IdList<BracedBlock> pars_ = new IdList<BracedBlock>();
        BracedBlock a_;
        if (_anEl.getParentsReturnables().isEmpty()) {
            a_ = _anEl.getRoot();
        } else {
            a_ = (BracedBlock) _anEl.getParentsReturnables().last();
        }
        while (par_ != a_) {
            pars_.add(par_);
            par_ = par_.getParent();
        }
        if (a_ instanceof Eval) {
            IdMap<ReturnMehod, Eval> breakables_ = _anEl.getReturnables();
            IdMap<ReturnMehod, IdMap<Eval, IdList<BracedBlock>>> breakablesAncestors_ = _anEl.getReturnablesAncestors();
            IdMap<Eval, IdList<BracedBlock>> id_;
            id_ = new IdMap<Eval, IdList<BracedBlock>>();
            id_.put((Eval) a_, pars_);
            breakablesAncestors_.put(this, id_);
            breakables_.put(this, (Eval) a_);
        } else {
            IdMap<ReturnMehod, MemberCallingsBlock> breakables_ = _anEl.getReturnablesCallings();
            IdMap<ReturnMehod, IdMap<MemberCallingsBlock, IdList<BracedBlock>>> breakablesAncestors_ = _anEl.getReturnablesAncestorsCallings();
            IdMap<MemberCallingsBlock, IdList<BracedBlock>> id_;
            id_ = new IdMap<MemberCallingsBlock, IdList<BracedBlock>>();
            id_.put((MemberCallingsBlock) a_, pars_);
            breakablesAncestors_.put(this, id_);
            breakables_.put(this, (MemberCallingsBlock) a_);
        }
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        if (isEmpty()) {
            super.setAssignmentAfter(_an, _anEl);
        }
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        ObjectMap<ClassField, SimpleAssignment> ass_;
        ass_ = new ObjectMap<ClassField, SimpleAssignment>();
        for (EntryCust<ClassField, SimpleAssignment> e: vars_.getFieldsRoot().entryList()) {
            ass_.put(e.getKey(), e.getValue().assign());
        }
        _anEl.getAssignments().put(this, ass_);
    }
    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_RETURN;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (!isEmpty()) {
            ip_.setOffset(0);
            ip_.setGlobalOffset(expressionOffset);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
            Argument arg_ = el_.calculateMember(_cont);
            if (_cont.callsOrException()) {
                return;
            }
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
                _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),null_));
                return;
            }
            if (!arg_.isNull()) {
                Mapping map_ = new Mapping();
                String rightClass_ = arg_.getObjectClassName(_cont);
                map_.setArg(rightClass_);
                map_.setParam(retType_);
                if (!Templates.isCorrect(map_, _cont)) {
                    String cast_;
                    cast_ = stds_.getAliasCast();
                    _cont.setException(new StdStruct(new CustomError(StringList.concat(rightClass_,RETURN_LINE,retType_,RETURN_LINE,_cont.joinPages())),cast_));
                    return;
                }
            }
            if (arg_.getStruct() instanceof NumberStruct || arg_.getStruct() instanceof CharStruct) {
                ClassArgumentMatching resCl_ = new ClassArgumentMatching(retType_);
                arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, arg_.getStruct(), _cont));
            }
            _cont.getLastPage().setReturnedArgument(arg_);
        } else {
            _cont.getLastPage().setReturnedArgument();
        }
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        while (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            ip_.setFinallyToProcess(false);
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((TryBlockStack)bl_).setCalling(this);
                return;
            }
        }
        ip_.postReturn(_conf);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        if (!isEmpty()) {
            return getElRet();
        }
        return null;
    }
}
