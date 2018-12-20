package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.ForwardPageEl;
import code.expressionlanguage.calls.ReturnablePageEl;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class ReturnMehod extends AbruptBlock implements CallingFinally, WithNotEmptyEl  {

    private final String expression;

    private int expressionOffset;

    private CustList<ExecOperationNode> opRet;

    public ReturnMehod(ContextEl _importingPage,
            BracedBlock _m, OffsetStringInfo _expression, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
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

    public CustList<ExecOperationNode> getOpRet() {
        return opRet;
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
                retType_ = meth_.getImportedReturnType();
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
        if (f_ == null) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
            return;
        }
        opRet = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        StringMap<StringList> vars_ = new StringMap<StringList>();
        if (!f_.isStaticContext()) {
            String globalClass_ = page_.getGlobalClass();
            String curClassBase_ = Templates.getIdFromAllTypes(globalClass_);
            for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypesMapValues()) {
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(opRet.last().getResultClass());
        mapping_.setParam(retType_);
        if (StringList.quickEq(retType_, stds_.getAliasVoid())) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            _cont.getClasses().addError(cast_);
            return;
        }
        if (!Templates.isGenericCorrect(mapping_, _cont)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            _cont.getClasses().addError(cast_);
        }
        if (PrimitiveTypeUtil.isPrimitive(retType_, _cont)) {
            opRet.last().getResultClass().setUnwrapObject(retType_);
        }
    }

    @Override
    public void reduce(ContextEl _context) {
        if (opRet == null) {
            return;
        }
        ExecOperationNode r_ = opRet.last();
        opRet = ElUtil.getReducedNodes(r_);
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
        StringMap<SimpleAssignment> ass_;
        ass_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, SimpleAssignment> e: vars_.getFieldsRoot().entryList()) {
            ass_.put(e.getKey(), e.getValue().assign());
        }
        _anEl.getAssignments().put(this, ass_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (!isEmpty()) {
            ip_.setOffset(0);
            ip_.setGlobalOffset(expressionOffset);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            Argument arg_ = el_.calculateMember(_cont);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
            ((ForwardPageEl)_cont.getLastPage()).setReturnedArgument(arg_);
        } else {
            ((ReturnablePageEl) _cont.getLastPage()).setReturnedArgument();
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
        ((ReturnablePageEl) ip_).postReturn(_conf);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        if (!isEmpty()) {
            return getElRet();
        }
        return null;
    }
}
