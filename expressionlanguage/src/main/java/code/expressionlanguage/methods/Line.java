package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractInstancingPageEl;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.InterfaceInvokingConstructor;
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
    private static final String INTERFACES = "interfaces";
    private static final char PAR_LEFT = '(';

    private final String expression;

    private int expressionOffset;

    private CustList<OperationNode> opExp;
    private boolean callSuper;
    private boolean callInts;

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
        callInts = expression.trim().startsWith(StringList.concat(String.valueOf(EXTERN_CLASS),INTERFACES,String.valueOf(PAR_LEFT)));
        boolean st_ = stBlock_ || callSuper || callThis || callInts;
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
                AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
                AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
                ass_.getVariablesRoot().last().put(varName_,Assignment.assignClassic(true, false));
            }
        }
        _cont.setMerged(false);
        _cont.setFinalVariable(false);
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
    }

    public CustList<OperationNode> getExp() {
        return opExp;
    }

    public ConstructorId getConstId() {
        return opExp.last().getConstId();
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    public boolean isCallSuper() {
        return callSuper;
    }

    public String getCalledInterface() {
        OperationNode last_ = opExp.last();
        if (!(last_ instanceof InterfaceInvokingConstructor)) {
            return "";
        }
        InterfaceInvokingConstructor int_ = (InterfaceInvokingConstructor) last_;
        String cl_ = int_.getMethodName();
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        return cl_;
    }
    public boolean isCallInts() {
        return callInts;    
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
        AbstractPageEl ip_ = _cont.getLastPage();
        if (isCallSuper()) {
            AbstractInstancingPageEl inst_ = (AbstractInstancingPageEl)ip_;
            String curClass_ = inst_.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(curClass_).first();
            String instClass_ = inst_.getGlobalArgument().getObjectClassName(_cont);
            String formatted_ = Templates.getFullTypeByBases(instClass_, curClassBase_, _cont);
            ClassMetaInfo meta_ = _cont.getClasses().getClassMetaInfo(curClassBase_, _cont);
            String superClass_ = meta_.getSuperClass();
            String baseSuperClass_ = StringList.getAllTypes(superClass_).first();
            if (inst_.getCalledConstructors().containsObj(baseSuperClass_)) {
                ConstructorBlock ctor_ = (ConstructorBlock) getFunction();
                for (String i: ctor_.getInterfaces()) {
                    String t_ = StringList.removeAllSpaces(i);
                    if (!inst_.getIntializedInterfaces().containsStr(t_)) {
                        inst_.getIntializedInterfaces().add(t_);
                        ConstructorId super_ = new ConstructorId(baseSuperClass_, new StringList(), false);
                        Argument global_ = inst_.getGlobalArgument();
                        String generic_ = Templates.getFullTypeByBases(formatted_, t_, _cont);
                        _cont.setCallCtor(new CustomFoundConstructor(generic_, EMPTY_STRING, -1, super_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER));
                        return;
                    }
                }
                if (!inst_.isFirstField()) {
                    inst_.setFirstField(true);
                    RootBlock class_ = _cont.getClasses().getClassBody(curClassBase_);
                    Block firstChild_ = class_.getFirstChild();
                    ip_.getReadWrite().setBlock(firstChild_);
                    return;
                }
                processBlock(_cont);
                return;
            }
        }
        String int_ = getCalledInterface();
        if (!int_.isEmpty()) {
            AbstractInstancingPageEl inst_ = (AbstractInstancingPageEl)ip_;
            String curClass_ = inst_.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(curClass_).first();
            if (inst_.getCalledConstructors().containsObj(int_)) {
                if (!inst_.isFirstField()) {
                    inst_.setFirstField(true);
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
