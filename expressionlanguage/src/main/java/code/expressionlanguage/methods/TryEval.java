package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.StringMap;

public final class TryEval extends BracedStack implements Eval, IncrCurrentGroup {

    public TryEval(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public TryEval(ContextEl _importingPage, int _indexChild, BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
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

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        Block next_ = getNextSibling();
        boolean existCatch_ = false;
        while (next_ != null) {
            if (next_ instanceof FinallyEval) {
                existCatch_ = true;
                break;
            }
            existCatch_ = next_ instanceof CatchEval;
            break;
        }
        if (!existCatch_ || getFirstChild() == null) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
        AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
        int index_ = 0;
        String boolStd_ = _cont.getStandards().getAliasBoolean();
        for (EntryCust<ClassField,AssignmentBefore> e: ass_.getFieldsRootBefore().entryList()) {
            ClassField key_ = e.getKey();
            String classNameDecl_ = key_.getClassName();
            ClassMetaInfo custClass_;
            custClass_ = _cont.getClassMetaInfo(classNameDecl_);
            String type_ = custClass_.getFields().getVal(key_.getFieldName()).getType();
            boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolStd_, type_, _cont);
            ass_.getFieldsRoot().put(key_, e.getValue().assignAfter(isBool_));
        }
        for (StringMap<AssignmentBefore> s: ass_.getVariablesRootBefore()) {
            StringMap<Assignment> vars_ = new StringMap<Assignment>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                String key_ = e.getKey();
                LocalVariable lc_ = _cont.getLocalVariables().get(index_).getVal(key_);
                String type_ = lc_.getClassName();
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolStd_, type_, _cont);
                vars_.put(e.getKey(), e.getValue().assignAfter(isBool_));
            }
            index_++;
            ass_.getVariablesRoot().add(vars_);
        }
    }
    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof CatchEval || next_ instanceof FinallyEval;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
    }

    @Override
    public String getTagName() {
        return TAG_TRY;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        Block n_ = getNextSibling();
        int index_ = getIndexGroup();
        TryBlockStack tryStack_ = new TryBlockStack();
        while (n_ != null) {
            if (n_.getIndexGroup() != index_) {
                break;
            }
            tryStack_.getCatchBlocks().add((BracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        tryStack_.setBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }
}
