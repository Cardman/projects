package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadInheritedClass;
import code.expressionlanguage.errors.custom.UnassignedFinalField;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.*;
import code.util.*;

public final class ConstructorBlock extends NamedFunctionBlock implements GeneConstructor,ReturnableWithSignature {

    private ConstructorId constIdSameClass;

    private boolean implicitCallSuper;

    private int leftPar;

    public ConstructorBlock(OffsetAccessInfo _access,
                            OffsetStringInfo _retType, OffsetStringInfo _fctName,
                            StringList _paramTypes, Ints _paramTypesOffset,
                            StringList _paramNames, Ints _paramNamesOffset, int _leftPar,OffsetsBlock _offset) {
        super(_access, _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset);
        leftPar = _leftPar;
    }

    @Override
    public String getSignature(Analyzable _ana) {
        return getId().getSignature(_ana);
    }

    @Override
    public ConstructorId getId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getFullName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }

    public ConstructorId getGenericId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getGenericString();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
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
            return;
        }
        Line l_ = (Line) first_;
        if (l_.isCallInts()) {
            implicitCallSuper = true;
        }
        if (l_.isCallSuper() || l_.isCallInts()) {
            return;
        }
        if (l_.isCallThis()) {
            constIdSameClass = l_.getConstId();
            return;
        }
        implicitCallSuper = true;
    }

    public boolean implicitConstr() {
        return implicitCallSuper;
    }

    public ConstructorId getConstIdSameClass() {
        return constIdSameClass;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }
    @Override
    public void buildImportedReturnTypes(Analyzable _stds) {
        String void_ = _stds.getStandards().getAliasVoid();
        setImportedReturnType(void_);
    }

    @Override
    public String getName() {
        return EMPTY_STRING;
    }

    @Override
    public GeneType belong() {
        return (RootBlock) getParent();
    }

    public String getDeclaringType() {
        RootBlock clBlock_ = (RootBlock) getParent();
        return clBlock_.getFullName();
    }

    @Override
    public void setAssignmentAfterCallReadOnly(Analyzable _an, AnalyzingEl _anEl) {
        checkInterfaces(_an);
    }

    private void checkInterfaces(Analyzable _an) {
        Block firstChild_ = getFirstChild();
        StringList ints_ = new StringList();
        StringList filteredCtor_ = _an.getContextEl().getNeedInterfaces();
        boolean checkThis_ = false;
        while (firstChild_ != null) {
            if (!(firstChild_ instanceof Line)) {
                break;
            }
            Line l_ = (Line) firstChild_;
            if (l_.isCallThis()) {
                checkThis_ = true;
                break;
            }
            if (l_.isCallInts()) {
                ints_.add(l_.getCalledInterface());
            }
            firstChild_ = firstChild_.getNextSibling();
        }
        if (!checkThis_) {
            if (!StringList.equalsSet(filteredCtor_, ints_)) {
                BadInheritedClass undef_;
                undef_ = new BadInheritedClass();
                undef_.setClassName(((RootBlock) getParent()).getFullName());
                undef_.setFileName(getFile().getFileName());
                undef_.setIndexFile(0);
                _an.getClasses().addError(undef_);
            }
        } else {
            if (!ints_.isEmpty()) {
                BadInheritedClass undef_;
                undef_ = new BadInheritedClass();
                undef_.setClassName(((RootBlock) getParent()).getFullName());
                undef_.setFileName(getFile().getFileName());
                undef_.setIndexFile(0);
                _an.getClasses().addError(undef_);
            }
        }
    }

    @Override
    public void setAssignmentAfterCall(Analyzable _an, AnalyzingEl _anEl) {
        setAssignmentAfter(_an,_anEl);
        checkInterfaces(_an);
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        for (EntryCust<ReturnMethod, StringMap<SimpleAssignment>> r: _anEl.getAssignments().entryList()) {
            for (EntryCust<String, SimpleAssignment> f: r.getValue().entryList()) {
                checkAssignments(_an, f,false);
            }
        }
        if (_anEl.canCompleteNormally(this)) {
            AssignedVariables assTar_ = id_.getVal(this);
            for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                checkAssignments(_an, f,true);
            }
        } else {
            AssignedVariables assTar_ = id_.getVal(this);
            for (EntryCust<String, SimpleAssignment> f: assTar_.getFieldsRoot().entryList()) {
                String name_ = f.getKey();
                SimpleAssignment a_ = f.getValue();
                if (a_.isAssignedAfter()) {
                    _an.getAnalyzing().getInitFieldsCtors().add(name_);
                }
            }
        }
    }

    private void checkAssignments(Analyzable _an, EntryCust<String, SimpleAssignment> _pair, boolean _add) {
        String cl_ = Templates.getIdFromAllTypes(_an.getGlobalClass());
        String name_ = _pair.getKey();
        ClassField key_ = new ClassField(cl_, name_);
        FieldInfo finfo_ = _an.getFieldInfo(key_);
        if (!finfo_.isFinalField()) {
            return;
        }
        SimpleAssignment a_ = _pair.getValue();
        if (!a_.isAssignedAfter()) {
            //error
            UnassignedFinalField un_ = new UnassignedFinalField(key_);
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _an.getClasses().addError(un_);
        } else if (_add){
            _an.getAnalyzing().getInitFieldsCtors().add(name_);
        }
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_cont,_parts);
        int begName_ = getNameOffset();
        _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
        _parts.add(new PartOffset("</a>",leftPar));
        int len_ = getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            buildAnnotationsReport(i,_cont,_parts);
            _parts.addAllElts(getPartOffsetsParams().get(i));
            Integer off_ = getParametersNamesOffset().get(i);
            String param_ = getParametersNames().get(i);
            _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            _parts.add(new PartOffset("</a>",off_+param_.length()));
            _cont.getCoverage().getParamVars().put(param_,off_);
        }
    }
}
