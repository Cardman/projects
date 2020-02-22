package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.ReturnableValuePageEl;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.AbruptCallingFinally;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.util.*;

public final class ReturnMehod extends AbruptBlock implements CallingFinally, WithNotEmptyEl  {

    private final String expression;

    private int expressionOffset;

    private CustList<ExecOperationNode> opRet;

    public ReturnMehod(OffsetStringInfo _expression, OffsetsBlock _offset) {
        super(_offset);
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
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        String retType_ = processReturnValue(_cont);
        if (retType_.isEmpty()) {
            return;
        }
        MethodAccessKind stCtx_ = f_.getStaticContext();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opRet = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(stCtx_));
        checkTypes(_cont, retType_);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        String retType_ = processReturnValue(_cont);
        if (retType_.isEmpty()) {
            return;
        }
        MethodAccessKind stCtx_ = f_.getStaticContext();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opRet = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(stCtx_));
        checkTypes(_cont, retType_);
    }

    private String processReturnValue(ContextEl _cont) {
        LgNames stds_ = _cont.getStandards();
        String retType_ = stds_.getAliasVoid();
        BracedBlock par_ = getParent();
        while (par_ != null) {
            if (par_ instanceof Returnable) {
                Returnable meth_;
                meth_ = (Returnable) par_;
                retType_ = meth_.getImportedReturnType();
                break;
            }
            par_ = par_.getParent();
        }
        if (StringList.quickEq(retType_, stds_.getAliasVoid())) {
            if (isEmpty()) {
                return EMPTY_STRING;
            }
        }
        return retType_;
    }
    private void checkTypes(ContextEl _cont, String _retType) {
        LgNames stds_ = _cont.getStandards();
        StringMap<StringList> vars_ = _cont.getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(opRet.last().getResultClass());
        mapping_.setParam(_retType);
        if (StringList.quickEq(_retType, stds_.getAliasVoid())) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            _cont.getClasses().addError(cast_);
            return;
        }
        if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            _cont.getClasses().addError(cast_);
        }
        if (PrimitiveTypeUtil.isPrimitive(_retType, _cont)) {
            opRet.last().getResultClass().setUnwrapObject(_retType);
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
        AssignedVariables vars_ = _an.getContextEl().getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<SimpleAssignment> ass_;
        ass_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, SimpleAssignment> e: vars_.getFieldsRoot().entryList()) {
            ass_.put(e.getKey(), e.getValue().assign());
        }
        _anEl.getAssignments().put(this, ass_);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        if (isEmpty()) {
            return;
        }
        int off_ = getExpressionOffset();
        int offsetEndBlock_ = off_ + getExpression().length();
        ElUtil.buildCoverageReport(_cont,off_,this,getOpRet(),offsetEndBlock_,_parts);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (!isEmpty()) {
            ip_.setOffset(0);
            ip_.setGlobalOffset(expressionOffset);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            Argument arg_ = ElUtil.tryToCalculate(_cont,el_,0);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
            String type_ = processReturnValue(_cont);
            type_ = ip_.formatVarType(type_,_cont);
            if (!Templates.checkObject(type_,arg_,_cont)) {
                return;
            }
            ((ReturnableValuePageEl) _cont.getLastPage()).setReturnedArgument(arg_);
        }
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        while (ip_.hasBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.setNullReadWrite();
    }

    @Override
    public AbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new AbruptCallingFinally(this);
    }
    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getElRet();
    }
}
