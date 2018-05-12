package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.UnassignedFinalField;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.Numbers;
import code.util.ObjectMap;
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
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }
    @Override
    public ConstructorId getGenericId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getGenericString();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }
    public ConstructorId getFormattedId(String _genericClass, ContextEl _classes) {
        String name_ = Templates.format(_genericClass, getName(), _classes);
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(_genericClass, n_, _classes);
            pTypes_.add(formatted_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }
    public void setupInstancingStep(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
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
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        for (EntryCust<ReturnMehod, ObjectMap<ClassField, SimpleAssignment>> r: _anEl.getAssignments().entryList()) {
            for (EntryCust<ClassField, SimpleAssignment> f: r.getValue().entryList()) {
                ClassField key_ = f.getKey();
                ClassMetaInfo cl_ = _an.getClassMetaInfo(key_.getClassName());
                FieldMetaInfo finfo_ = cl_.getFields().getVal(key_.getFieldName());
                if (!finfo_.isFinalField()) {
                    continue;
                }
                if (finfo_.isStaticField()) {
                    continue;
                }
                SimpleAssignment a_ = f.getValue();
                if (!a_.isAssignedAfter()) {
                    //error
                    UnassignedFinalField un_ = new UnassignedFinalField(key_);
                    un_.setFileName(getFile().getFileName());
                    un_.setRc(getRowCol(0,getOffset().getOffsetTrim()));
                    _an.getClasses().getErrorsDet().add(un_);
                }
            }
        }
        if (_anEl.canCompleteNormally(this)) {
            AssignedVariables assTar_ = id_.getVal(this);
            for (EntryCust<ClassField, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                ClassField key_ = f.getKey();
                ClassMetaInfo cl_ = _an.getClassMetaInfo(key_.getClassName());
                FieldMetaInfo finfo_ = cl_.getFields().getVal(key_.getFieldName());
                if (!finfo_.isFinalField()) {
                    continue;
                }
                if (finfo_.isStaticField()) {
                    continue;
                }
                SimpleAssignment a_ = f.getValue();
                if (!a_.isAssignedAfter()) {
                    //error
                    UnassignedFinalField un_ = new UnassignedFinalField(key_);
                    un_.setFileName(getFile().getFileName());
                    un_.setRc(getRowCol(0,getOffset().getOffsetTrim()));
                    _an.getClasses().getErrorsDet().add(un_);
                }
            }
        }
    }
}
