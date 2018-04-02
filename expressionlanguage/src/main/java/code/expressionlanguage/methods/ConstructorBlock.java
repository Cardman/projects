package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;

public final class ConstructorBlock extends NamedFunctionBlock implements GeneConstructor {

    private InstancingStep instancing;
    private ConstructorId constIdSameClass;

    private boolean implicitCallSuper;

    private StringList interfaces;
    private Numbers<Integer> interfacesOffest;

    public ConstructorBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public ConstructorBlock(ContextEl _importingPage,
            int _indexChild, BracedBlock _m,
            StringList _interfaces,
            Numbers<Integer> _interfacesOffest,
            OffsetAccessInfo _access,
            OffsetStringInfo _retType, OffsetStringInfo _fctName,
            StringList _paramTypes, Numbers<Integer> _paramTypesOffset,
            StringList _paramNames, Numbers<Integer> _paramNamesOffset, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _access, _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset);
        interfaces = _interfaces;
        interfacesOffest = _interfacesOffest;
    }

    public StringList getInterfaces() {
        return interfaces;
    }

    public Numbers<Integer> getInterfacesOffest() {
        return interfacesOffest;
    }

    @Override
    public String getSignature() {
        return getId().getSignature();
    }

    @Override
    public ConstructorId getId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getFullName();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(new ClassName(n_, i + 1 == len_ && isVarargs()));
        }
        return new ConstructorId(name_, pTypes_);
    }
    @Override
    public ConstructorId getGenericId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getGenericString();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(new ClassName(n_, i + 1 == len_ && isVarargs()));
        }
        return new ConstructorId(name_, pTypes_);
    }
    public ConstructorId getFormattedId(String _genericClass, ContextEl _classes) {
        String name_ = Templates.format(_genericClass, getName(), _classes);
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(_genericClass, n_, _classes);
            pTypes_.add(new ClassName(formatted_, i + 1 == len_ && isVarargs()));
        }
        return new ConstructorId(name_, pTypes_);
    }
    public void setupInstancingStep(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
        page_.setOffset(0);
        Block first_ = getFirstChild();
        if (!(first_ instanceof Line)) {
            implicitCallSuper = true;
            instancing = InstancingStep.USING_SUPER;
            return;
        }
        Line l_ = (Line) first_;
        if (l_.isCallSuper()) {
            instancing = InstancingStep.USING_SUPER;
            return;
        }
        if (l_.isCallThis()) {
            constIdSameClass = l_.getConstId();
            instancing = InstancingStep.USING_THIS;
            return;
        }
        implicitCallSuper = true;
        instancing = InstancingStep.USING_SUPER;
    }
    public boolean superConstr() {
        return instancing == InstancingStep.USING_SUPER || implicitConstr();
    }

    public boolean implicitConstr() {
        return implicitCallSuper;
    }

    public boolean hasFirstBlockAfterSuperConstr() {
        if (!superConstr()) {
            return false;
        }
        return getFirstBlockAfterOtherConstr() != null;
    }

    public Block getFirstBlockAfterOtherConstr() {
        Block ch_ = getFirstChild();
        if (implicitConstr()) {
            return ch_;
        }
        //the constructor has a line as first block which call an other constructor
        return ch_.getNextSibling();
    }

    public ConstructorId getConstIdSameClass() {
        return constIdSameClass;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public boolean isStaticContext() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_CONSTRUCTOR;
    }

    @Override
    public String getReturnType(LgNames _stds) {
        return _stds.getAliasVoid();
    }

    @Override
    public String getName() {
        return EMPTY_STRING;
    }

    @Override
    public RootBlock belong() {
        return (RootBlock) getParent();
    }

    @Override
    public String getDeclaringType() {
        RootBlock clBlock_ = (RootBlock) getParent();
        return clBlock_.getFullName();
    }
}
