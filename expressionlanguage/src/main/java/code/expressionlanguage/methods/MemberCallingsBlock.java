package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.methods.util.DeadCodeMethod;
import code.expressionlanguage.methods.util.MissingReturnMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class MemberCallingsBlock extends BracedBlock implements FunctionBlock {

    public MemberCallingsBlock(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    MemberCallingsBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public final void buildFctInstructions(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (!(getParent() instanceof RootBlock)) {
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        Block firstChild_ = getFirstChild();
        if (firstChild_ == null) {
            if (this instanceof MethodBlock) {
                MethodBlock method_ = (MethodBlock)this;
                LgNames stds_ = _cont.getStandards();
                if (!StringList.quickEq(method_.getReturnType(stds_), stds_.getAliasVoid())) {
                    if (!method_.isAbstractMethod()) {
                        //error
                        MissingReturnMethod miss_ = new MissingReturnMethod();
                        miss_.setRc(method_.getRowCol(0, method_.getOffset().getOffsetTrim()));
                        miss_.setFileName(getFile().getFileName());
                        miss_.setId(method_.getSignature());
                        miss_.setReturning(method_.getReturnType(stds_));
                        _cont.getClasses().getErrorsDet().add(miss_);
                    }
                }
            } else if (this instanceof ConstructorBlock) {
                AssignedVariablesBlock glAss_ = page_.getAssignedVariables();
                AssignedVariables varsAss_ = glAss_.getFinalVariablesGlobal();
                ObjectMap<ClassField,Assignment> as_ = varsAss_.getFieldsRoot();
                for (EntryCust<ClassField, Assignment> a: as_.entryList()) {
                    if (!a.getValue().isAssignedAfter()) {
                        //field error
                    }
                }
            }
            return;
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        if (!isStaticContext()) {
            String globalClass_ = page_.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(globalClass_).first();
            for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypes()) {
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnalyzingEl anEl_ = new AnalyzingEl(mapping_);
        anEl_.setRoot(this);
        Block en_ = this;
        CustList<BracedBlock> parents_ = anEl_.getParents();
        CustList<BreakableBlock> parentsBreakables_ = anEl_.getParentsBreakables();
        CustList<Loop> parentsContinuable_ = anEl_.getParentsContinuables();
        while (true) {
            en_.setAssignmentBefore(_cont, anEl_);
            en_.reach(_cont, anEl_);
            if (!anEl_.isReachable(en_)) {
                //error
                DeadCodeMethod deadCode_ = new DeadCodeMethod();
                deadCode_.setFileName(getFile().getFileName());
                deadCode_.setRc(en_.getRowCol(0, en_.getOffset().getOffsetTrim()));
                if (this instanceof Returnable) {
                    deadCode_.setId(((Returnable)this).getSignature());
                } else {
                    deadCode_.setId(EMPTY_STRING);
                }
                _cont.getClasses().getErrorsDet().add(deadCode_);
            }
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                _cont.getAnalyzing().initLocalVars();
                if (en_ instanceof BreakableBlock) {
                    parentsBreakables_.add((BreakableBlock) en_);
                }
                if (en_ instanceof Loop) {
                    parentsContinuable_.add((Loop) en_);
                }
                parents_.add((BracedBlock) en_);
            }
            if (en_ != anEl_.getRoot()) {
                tryCheckBlocksTree(en_, _cont);
                tryBuildExpressionLanguage(en_, _cont);
                tryCheckConstCall(en_, _cont);
            }
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            en_.abrupt(_cont, anEl_);
            if (en_ instanceof BracedBlock) {
                ((BracedBlock)en_).abruptGroup(_cont, anEl_);
            }
            
            en_.setAssignmentAfter(_cont, anEl_);
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                BracedBlock par_;
                par_ = en_.getParent();
                par_.abrupt(_cont, anEl_);
                par_.abruptGroup(_cont, anEl_);
                par_.setAssignmentAfter(_cont, anEl_);
                if (par_ == this) {
                    if (par_ instanceof MethodBlock) {
                        MethodBlock method_ = (MethodBlock)par_;
                        LgNames stds_ = _cont.getStandards();
                        if (!StringList.quickEq(method_.getReturnType(stds_), stds_.getAliasVoid())) {
                            if (anEl_.canCompleteNormally(par_)) {
                                //error
                                MissingReturnMethod miss_ = new MissingReturnMethod();
                                miss_.setRc(method_.getRowCol(0, method_.getOffset().getOffsetTrim()));
                                miss_.setFileName(getFile().getFileName());
                                miss_.setId(method_.getSignature());
                                miss_.setReturning(method_.getReturnType(stds_));
                                _cont.getClasses().getErrorsDet().add(miss_);
                            }
                        }
                    }
                    return;
                }
                parents_.removeLast();
                if (par_ instanceof BreakableBlock) {
                    parentsBreakables_.removeLast();
                }
                if (par_ instanceof Loop) {
                    parentsContinuable_.removeLast();
                }
                page_.removeLocalVars();
                if (par_ instanceof ForLoop) {
                    String var_ = ((ForLoop)par_).getVariableName();
                    page_.getVars().removeKey(var_);
                }
                if (par_ instanceof CatchEval) {
                    String var_ = ((CatchEval)par_).getVariableName();
                    page_.getCatchVars().removeKey(var_);
                }
                en_ = par_;
            }
        }
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }
    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        BracedBlock br_ = getParent();
        if (prev_ == null || _anEl.getRoot() == this) {
            if (_anEl.getRoot() == this || _anEl.isReachable(br_)) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
        } else {
            super.reach(_an, _anEl);
        }
    }
}
