package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.NatTreeMap;

public final class Line extends Leaf implements StackableBlock {

    private final String expression;

    private CustList<OperationNode> opExp;
    
    private boolean callSuper;

    private boolean callThis;

//    private ExpressionLanguage rightEl;

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
//        return new ExpressionLanguage(expression, _cont, true, new Calculation(StepCalculation.RIGHT));
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
        boolean st_ = f_.isStaticContext() || callSuper || callThis;
        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
//        page_.setLookForAttrValue(true);
        page_.setOffset(0);
//        opExp = ElUtil.getAnalyzedOperations(expression, _cont, st_);
        opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(st_));
        OperationNode r_ = opExp.last();
        if (r_.isSuperConstructorCall()) {
            callSuper = true;
        }
        if (r_.isOtherConstructorClass()) {
            callThis = true;
        }
//        new ExpressionLanguage(expression, _cont, true, new Calculation(StepCalculation.RIGHT));
//        removeLocalVariablesFromParent();
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
//        p_.setProcessingNode(getAssociateElement());
//        p_.setLookForAttrValue(true);
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
//            String curClass_ = ip_.getGlobalArgument().getArgClassName();
            String curClass_ = ip_.getGlobalClass();
            ClassMetaInfo meta_ = _cont.getClasses().getClassMetaInfo(curClass_);
            String superClass_ = meta_.getSuperClass();
            if (ip_.getCallingConstr().getCalledConstructors().containsObj(superClass_)) {
//                if (!ip_.getCallingConstr().isInitializedFields())
                if (!ip_.getCallingConstr().isFirstField()) {
                    ip_.getCallingConstr().setFirstField(true);
                    RootedBlock class_ = _cont.getClasses().getClassBody(curClass_);
                    Block firstChild_ = class_.getFirstChild();
                    ip_.getReadWrite().setBlock(firstChild_);
                    return;
                }
                processBlock(_cont);
                return;
            }
        }
//        if (isCallThis()) {
//            String curClass_ = ip_.getGlobalArgument().getArgClassName();
//            if (ip_.getCallingConstr().getCalledConstructors().containsObj(curClass_)) {
//                if (!ip_.getCallingConstr().isInitializedFields()) {
//                    ClassBlock class_ = _cont.getClasses().getClassBody(curClass_);
//                    Block firstChild_ = class_.getFirstChild();
//                    ip_.getReadWrite().setBlock(firstChild_);
//                    return;
//                }
//                processBlock(_cont, ip_);
//                return;
//            }
//        }
        ip_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
//        ip_.setLookForAttrValue(true);
        ip_.setOffset(0);
        ExpressionLanguage el_;
        if (!ip_.getCurrentEls().isEmpty()) {
            el_ = ip_.getCurrentEls().last();
        } else {
            el_ = getRightEl();
            ip_.setCurrentBlock(this);
            ip_.setCurrentEls(new CustList<ExpressionLanguage>(el_));
        }
//        ExpressionLanguage el_ = l_.getRightEl(_conf);
        el_.calculateMember(_cont);
        el_.setCurrentOper(null);
        ip_.getCurrentEls().clear();
//        if (isCallSuper()) {
//            String curClass_ = ip_.getGlobalArgument().getArgClassName();
////            ip_.getCallingConstr().getCalledConstructors().add(curClass_);
//            ClassBlock class_ = _cont.getClasses().getClassBody(curClass_);
//            Block firstChild_ = class_.getFirstChild();
//            ip_.getReadWrite().setBlock(firstChild_);
//            return;
////            String curClass_ = ip_.getGlobalArgument().getArgClassName();
////            ClassMetaInfo meta_ = _cont.getClasses().getClassMetaInfo(curClass_);
////            String superClass_ = meta_.getSuperClass();
////            ip_.getCallingConstr().getCalledConstructors().add(superClass_);
//        }
//        if (isCallThis()) {
//            String curClass_ = ip_.getGlobalArgument().getArgClassName();
//            ip_.getCallingConstr().getCalledConstructors().add(curClass_);
//        }
        processBlock(_cont);
    }
}
