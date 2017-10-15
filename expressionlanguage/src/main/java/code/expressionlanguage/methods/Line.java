package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.CustomFoundConstructorException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class Line extends Leaf implements StackableBlock {

    private static final char EXTERN_CLASS = '^';
    private static final String SUPER_ACCESS = "super";
    private static final String CURRENT = "this";
    private static final char PAR_LEFT = '(';

    private final String expression;

    private CustList<OperationNode> opExp;
    private boolean callSuper;

    private boolean callThis;

    public Line(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
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
        callSuper = expression.trim().startsWith(EXTERN_CLASS+SUPER_ACCESS+PAR_LEFT);
        callThis = expression.trim().startsWith(EXTERN_CLASS+CURRENT+PAR_LEFT);
        boolean st_ = stBlock_ || callSuper || callThis;
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        page_.setOffset(0);
        opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(st_, stBlock_));
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
        PageEl p_ = _cont.getLastPage();
        p_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        if (!canCallSuperThis()) {
            for (OperationNode o: opExp) {
                if (o.isSuperThis()) {
                    int off_ = o.getFullIndexInEl();
                    p_.setOffset(off_);
                    throw new BadConstructorCall(_cont.joinPages());
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
                    throw new BadConstructorCall(_cont.joinPages());
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
            ClassMetaInfo meta_ = _cont.getClasses().getClassMetaInfo(curClassBase_);
            String superClass_ = meta_.getSuperClass();
            String baseSuperClass_ = StringList.getAllTypes(superClass_).first();
            if (ip_.getCallingConstr().getCalledConstructors().containsObj(baseSuperClass_)) {
                UniqueRootedBlock root_ = (UniqueRootedBlock) _cont.getClasses().getClassBody(curClassBase_);
                for (String i: root_.getAllNeededSortedInterfaces()) {
                    if (!ip_.getIntializedInterfaces().containsStr(i)) {
                        ip_.getIntializedInterfaces().add(i);
                        ConstructorId super_ = new ConstructorId(baseSuperClass_, new EqList<ClassName>());
                        StringList called_ = ip_.getCallingConstr().getCalledConstructors();
                        Argument global_ = ip_.getGlobalArgument();
                        String generic_ = Templates.getGenericTypeByBases(curClass_, i, _cont.getClasses());
                        throw new CustomFoundConstructorException(generic_, EMPTY_STRING, called_, super_, global_, new CustList<Argument>(), InstancingStep.USING_SUPER);
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
        ip_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getRightEl());
        el_.calculateMember(_cont);
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        processBlock(_cont);
    }
}
