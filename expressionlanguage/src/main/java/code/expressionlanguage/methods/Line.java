package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class Line extends Leaf implements StackableBlock {

    private static final char EXTERN_CLASS = '$';
    private static final String SUPER_ACCESS = "super";
    private static final String CURRENT = "this";
    private static final char PAR_LEFT = '(';

    private final String expression;

    private int expressionOffset;

    private CustList<OperationNode> opExp;
    private boolean callSuper;

    private boolean callThis;

    public Line(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
    }

    public Line(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
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
    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getRightEl() {
        return new ExpressionLanguage(opExp);
    }

    @Override
    boolean canCallSuperThis() {
        if (!(getParent() instanceof ConstructorBlock)) {
            return false;
        }
        if (getParent().getFirstChild() != this) {
            return false;
        }
        return true;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        boolean stBlock_ = f_.isStaticContext();
        callSuper = expression.trim().startsWith(StringList.concat(String.valueOf(EXTERN_CLASS),SUPER_ACCESS,String.valueOf(PAR_LEFT)));
        callThis = expression.trim().startsWith(StringList.concat(String.valueOf(EXTERN_CLASS),CURRENT,String.valueOf(PAR_LEFT)));
        boolean st_ = stBlock_ || callSuper || callThis;
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.setRootAffect(true);
        Block previous_ = getPreviousSibling();
        opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(st_, stBlock_));
        if (previous_ instanceof DeclareVariable) {
            DeclareVariable dc_ = (DeclareVariable) previous_;
            if (dc_.isMerged()) {
                LocalVariable lv_ = new LocalVariable();
                String clName_ = dc_.getClassName();
                lv_.setClassName(clName_);
                lv_.setFinalVariable(dc_.isFinalVariable());
                String varName_ = dc_.getVariableName();
                _cont.putLocalVar(varName_, lv_);
                String boolStd_ = _cont.getStandards().getAliasBoolean();
                AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
                AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolStd_, clName_, _cont);
                ass_.getVariablesRoot().last().put(varName_,Assignment.assign(isBool_, true, false));
            }
        }
        _cont.setMerged(false);
        _cont.setFinalVariable(false);
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
    }

    public ConstructorId getConstId() {
        return opExp.last().getConstId();
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
        AnalyzedPageEl p_ = _cont.getAnalyzing();
        p_.setGlobalOffset(expressionOffset);
        if (!canCallSuperThis()) {
            for (OperationNode o: opExp) {
                if (o.isSuperThis()) {
                    int off_ = o.getFullIndexInEl();
                    p_.setOffset(off_);
                    BadConstructorCall call_ = new BadConstructorCall();
                    call_.setFileName(getFile().getFileName());
                    call_.setRc(getRowCol(0, expressionOffset));
                    call_.setLocalOffset(getRowCol(o.getFullIndexInEl(), expressionOffset));
                    _cont.getClasses().getErrorsDet().add(call_);
                }
            }
        } else {
            for (OperationNode o: opExp) {
                if (o == opExp.last()) {
                    continue;
                }
                if (o.isSuperThis()) {
                    int off_ = o.getFullIndexInEl();
                    p_.setOffset(off_);
                    BadConstructorCall call_ = new BadConstructorCall();
                    call_.setFileName(getFile().getFileName());
                    call_.setRc(getRowCol(0, expressionOffset));
                    call_.setLocalOffset(getRowCol(o.getFullIndexInEl(), expressionOffset));
                    _cont.getClasses().getErrorsDet().add(call_);
                }
            }
        }
    }

    public boolean isCallSuper() {
        return callSuper;
    }

    public boolean isCallThis() {
        return callThis;
    }

    @Override
    public String getTagName() {
        return TAG_LINE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        if (isCallSuper()) {
            String curClass_ = ip_.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(curClass_).first();
            String instClass_ = ip_.getGlobalArgument().getObjectClassName(_cont);
            String formatted_ = Templates.getFullTypeByBases(instClass_, curClassBase_, _cont);
            ClassMetaInfo meta_ = _cont.getClasses().getClassMetaInfo(curClassBase_, _cont);
            String superClass_ = meta_.getSuperClass();
            String baseSuperClass_ = StringList.getAllTypes(superClass_).first();
            if (ip_.getCallingConstr().getCalledConstructors().containsObj(baseSuperClass_)) {
                ConstructorBlock ctor_ = (ConstructorBlock) getFunction();
                for (String i: ctor_.getInterfaces()) {
                    String t_ = StringList.removeAllSpaces(i);
                    if (!ip_.getIntializedInterfaces().containsStr(t_)) {
                        ip_.getIntializedInterfaces().add(t_);
                        ConstructorId super_ = new ConstructorId(baseSuperClass_, new StringList(), false);
                        StringList called_ = ip_.getCallingConstr().getCalledConstructors();
                        Argument global_ = ip_.getGlobalArgument();
                        String generic_ = Templates.getFullTypeByBases(formatted_, t_, _cont);
                        _cont.setCallCtor(new CustomFoundConstructor(generic_, EMPTY_STRING, -1, called_, super_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER));
                        return;
                    }
                }
                if (!ip_.getCallingConstr().isFirstField()) {
                    ip_.getCallingConstr().setFirstField(true);
                    RootBlock class_ = _cont.getClasses().getClassBody(curClassBase_);
                    Block firstChild_ = class_.getFirstChild();
                    ip_.getReadWrite().setBlock(firstChild_);
                    return;
                }
                processBlock(_cont);
                return;
            }
        }
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont ,this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
        el_.calculateMember(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        processBlock(_cont);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return getRightEl();
    }
}
