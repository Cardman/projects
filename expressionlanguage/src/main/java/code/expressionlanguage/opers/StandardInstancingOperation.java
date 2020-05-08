package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.exec.PossibleIntermediateDottedOperable;
import code.expressionlanguage.opers.exec.StaticInitOperable;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class StandardInstancingOperation extends
        InvokingOperation implements PreAnalyzableOperation {

    private String methodName;

    private ConstructorId constId;

    private String className;

    private String fieldName = EMPTY_STRING;
    private int blockIndex = -1;

    private int naturalVararg = -1;

    private String lastType = EMPTY_STRING;
    private String typeInfer = EMPTY_STRING;
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private boolean newBefore = true;

    public StandardInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    public void setFieldName(String _fieldName) {
        fieldName = _fieldName;
    }

    @Override
    public void preAnalyze(Analyzable _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String afterNew_ = methodName.trim().substring(newKeyWord_.length());
        int j_ = afterNew_.indexOf("}");
        int delta_ = 0;
        if (j_ > -1) {
            afterNew_ = afterNew_.substring(j_+1);
            delta_ = j_+1;
            newBefore = false;
        }
        int local_ = StringList.getFirstPrintableCharIndex(afterNew_)+delta_;
        String className_ = afterNew_.trim();
        ClassArgumentMatching arg_ = getPreviousResultClass();
        StringMap<String> ownersMap_ = new StringMap<String>();
        StringMap<String> vars_ = new StringMap<String>();
        if (isIntermediateDottedOperation()) {
            if (className_.trim().startsWith("..")) {
                return;
            }
            if (arg_.isArray()) {
                return;
            }
            String idClass_ = Templates.getIdFromAllTypes(className_).trim();
            for (String o: arg_.getNames()) {
                boolean ok_ = true;
                for (String p: Templates.getAllTypes(o).mid(1)) {
                    if (p.startsWith(Templates.SUB_TYPE)) {
                        ok_ = false;
                    }
                    if (p.startsWith(Templates.SUP_TYPE)) {
                        ok_ = false;
                    }
                }
                if (!ok_) {
                    return;
                }
            }
            for (String o: arg_.getNames()) {
                StringList owners_ = TypeUtil.getGenericOwners(o, idClass_, _an);
                if (owners_.onlyOneElt()) {
                    ownersMap_.put(o, owners_.first());
                }
            }
            if (ownersMap_.size() != 1) {
                return;
            }
            String sup_ = ownersMap_.values().first();
            vars_ = Templates.getVarTypes(sup_,_an);
        }
        String inferForm_ = Templates.getInferForm(className_);
        if (inferForm_ == null) {
            return;
        }
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        String type_;
        int nbParentsInfer_ = 0;
        OperationNode current_;
        MethodOperation m_;
        if (!isIntermediateDottedOperation()) {
            int off_ = StringList.getFirstPrintableCharIndex(methodName);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _an);
            type_ = ResolvingImportTypes.resolveAccessibleIdTypeWithoutError(_an,newKeyWord_.length()+local_,inferForm_);
            partOffsets_.addAllElts(_an.getContextEl().getCoverage().getCurrentParts());
            if (type_.isEmpty()) {
                return;
            }
            current_ = this;
            m_ = getParent();
        } else {
            int off_ = StringList.getFirstPrintableCharIndex(methodName);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _an);
            String idClass_ = Templates.getIdFromAllTypes(className_).trim();
            String sup_ = ownersMap_.values().first();
            String id_ = Templates.getIdFromAllTypes(sup_);
            type_ = StringList.concat(id_,"..",idClass_);
            int begin_ = newKeyWord_.length()+local_;
            _an.appendParts(begin_,begin_+inferForm_.length(),type_,partOffsets_);
            current_ = getParent();
            m_ = current_.getParent();
        }
        while (m_ != null) {
            if (!(m_ instanceof ElementArrayInstancing) && !(m_ instanceof InferArrayInstancing)) {
                if (m_ instanceof IdOperation) {
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                if (m_ instanceof AbstractTernaryOperation) {
                    if (m_.getFirstChild() == current_) {
                        break;
                    }
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                break;
            }
            nbParentsInfer_++;
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
        String typeAff_ = EMPTY_STRING;
        AnalyzedBlock cur_ = _an.getCurrentAnaBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            FunctionBlock f_ = _an.getAnalyzing().getCurrentFct();
            if (f_ instanceof NamedFunctionBlock) {
                NamedFunctionBlock n_ = (NamedFunctionBlock) f_;
                String ret_ = n_.getImportedReturnType();
                String void_ = _an.getStandards().getAliasVoid();
                if (!StringList.quickEq(ret_, void_)) {
                    typeAff_ = ret_;
                }
            }
        } else if (m_ == null && cur_ instanceof ImportForEachLoop) {
            ImportForEachLoop i_ = (ImportForEachLoop) cur_;
            typeAff_ = i_.getImportedClassName();
            if (!typeAff_.isEmpty()) {
                String iter_ = _an.getStandards().getAliasIterable();
                typeAff_ = StringList.concat(iter_,Templates.TEMPLATE_BEGIN,typeAff_,Templates.TEMPLATE_END);
            }
        } else if (m_ == null && cur_ instanceof ImportForEachTable) {
            ImportForEachTable i_ = (ImportForEachTable) cur_;
            String typeAffOne_ = i_.getImportedClassNameFirst();
            String typeAffTwo_ = i_.getImportedClassNameSecond();
            if (!typeAffOne_.isEmpty() && !typeAffTwo_.isEmpty()) {
                String iter_ = _an.getStandards().getAliasIterableTable();
                typeAff_ = StringList.concat(iter_,Templates.TEMPLATE_BEGIN,typeAffOne_,Templates.TEMPLATE_SEP,typeAffTwo_,Templates.TEMPLATE_END);
            }
        } else if (m_ instanceof CastOperation) {
            CastOperation c_ = (CastOperation) m_;
            typeAff_ = c_.getClassName();
        } else if (m_ instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) m_;
            SettableElResult s_ = AffectationOperation.tryGetSettable(a_);
            if (s_ != null) {
                ClassArgumentMatching c_ = s_.getResultClass();
                typeAff_ = c_.getSingleNameOrEmpty();
            }
        }
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (typeAff_.isEmpty() || StringList.quickEq(typeAff_, keyWordVar_)) {
            return;
        }
        String cp_ = PrimitiveTypeUtil.getQuickComponentType(typeAff_, nbParentsInfer_);
        if (cp_ == null) {
            return;
        }
        String infer_ = Templates.tryInfer(type_,vars_, cp_, _an);
        if (infer_ == null) {
            return;
        }
        partOffsets.addAllElts(partOffsets_);
        int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
        int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
        _an.appendTitleParts(begin_,end_,infer_,partOffsets);
        typeInfer = infer_;
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        className = _conf.getStandards().getAliasObject();
        KeyWords keyWords_ = _conf.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String realClassName_ = methodName.trim().substring(newKeyWord_.length());
        int j_ = realClassName_.indexOf("}");
        if (j_ > -1) {
            realClassName_ = realClassName_.substring(j_+1);
            off_ += j_+1;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(filter_, _conf);
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_conf.getStaticContext());
            if (!typeInfer.isEmpty()) {
                realClassName_ = typeInfer;
            } else if (fieldName.isEmpty()) {
                int local_ = StringList.getFirstPrintableCharIndex(realClassName_);
                realClassName_ = ResolvingImportTypes.resolveCorrectType(_conf,newKeyWord_.length()+local_,realClassName_);
                partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
            } else {
                realClassName_ = realClassName_.trim();
            }
            String base_ = Templates.getIdFromAllTypes(realClassName_);
            GeneType g_ = _conf.getClassBody(base_);
            if (g_ != null && !g_.withoutInstance()) {
                String glClass_ = _conf.getGlobalClass();
                StringList parts_ = Templates.getAllInnerTypes(realClassName_);
                String outer_ = StringList.join(parts_.mid(0, parts_.size() - 1),"..");
                if (isStaticAccess() != MethodAccessKind.INSTANCE) {
                    FoundErrorInterpret static_ = new FoundErrorInterpret();
                    static_.setFileName(_conf.getCurrentFileName());
                    static_.setIndexFile(_conf.getCurrentLocationIndex());
                    //original type len
                    static_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorUnknown(),
                            realClassName_);
                    _conf.addError(static_);
                } else {
                    StringMap<StringList> vars_ = _conf.getCurrentConstraints();
                    Mapping m_ = new Mapping();
                    m_.setArg(glClass_);
                    m_.setParam(outer_);
                    m_.setMapping(vars_);
                    if (!Templates.isCorrectOrNumbers(m_, _conf)){
                        FoundErrorInterpret static_ = new FoundErrorInterpret();
                        static_.setFileName(_conf.getCurrentFileName());
                        static_.setIndexFile(_conf.getCurrentLocationIndex());
                        //original type len
                        static_.buildError(_conf.getContextEl().getAnalysisMessages().getBadImplicitCast(),
                                glClass_,
                                outer_);
                        _conf.addError(static_);
                    }
                }
            }
            analyzeCtor(_conf, realClassName_, firstArgs_);
            return;
        }
        if (!typeInfer.isEmpty()) {
            analyzeCtor(_conf, typeInfer, firstArgs_);
            return;
        }
        int offset_ = StringList.getFirstPrintableCharIndex(realClassName_);
        realClassName_ = realClassName_.trim();
        if (realClassName_.startsWith("..")) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            //original type len
            static_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorUnknown(),
                    realClassName_);
            _conf.addError(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        ClassArgumentMatching arg_ = getPreviousResultClass();
        if (arg_.isArray()) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            //key word len
            static_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorUnknown(),
                    realClassName_);
            _conf.addError(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        StringMap<String> ownersMap_ = new StringMap<String>();
        String idClass_ = Templates.getIdFromAllTypes(realClassName_);
        offset_ += idClass_.length() + 1;
        for (String o: arg_.getNames()) {
            boolean ok_ = true;
            for (String p: Templates.getAllTypes(o).mid(1)) {
                if (p.startsWith(Templates.SUB_TYPE)) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setIndexFile(_conf.getCurrentLocationIndex());
                    //key word len
                    call_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorBound(),
                            p,
                            o);
                    _conf.addError(call_);
                    ok_ = false;
                }
                if (p.startsWith(Templates.SUP_TYPE)) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_conf.getCurrentFileName());
                    call_.setIndexFile(_conf.getCurrentLocationIndex());
                    //key word len
                    call_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorBound(),
                            p,
                            o);
                    _conf.addError(call_);
                    ok_ = false;
                }
            }
            if (!ok_) {
                LgNames stds_ = _conf.getStandards();
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
        }
        for (String o: arg_.getNames()) {
            StringList owners_ = TypeUtil.getGenericOwners(o, idClass_, _conf);
            if (owners_.onlyOneElt()) {
                ownersMap_.put(o, owners_.first());
            }
        }
        if (ownersMap_.size() != 1) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            //idClass_ len
            static_.buildError(_conf.getContextEl().getAnalysisMessages().getNotResolvedOwner(),
                    idClass_
            );
            _conf.addError(static_);
            LgNames stds_ = _conf.getStandards();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String sup_ = ownersMap_.values().first();
        StringList partsArgs_ = new StringList();
        for (String a: Templates.getAllTypes(realClassName_).mid(1)) {
            int loc_ = StringList.getFirstPrintableCharIndex(a);
            partsArgs_.add(ResolvingImportTypes.resolveCorrectType(_conf,offset_+loc_,a));
            partOffsets.addAllElts(_conf.getContextEl().getCoverage().getCurrentParts());
            offset_ += a.length() + 1;
        }
        if (partsArgs_.isEmpty()) {
            realClassName_ = StringList.concat(sup_,"..",idClass_);
        } else {
            realClassName_ = StringList.concat(sup_,"..",idClass_,"<", StringList.join(partsArgs_, ","),">");
        }
        StringMap<StringList> vars_ = _conf.getCurrentConstraints();
        if (!Templates.isCorrectTemplateAll(realClassName_, vars_, _conf)) {
            int rc_ = _conf.getCurrentLocationIndex();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setIndexFile(rc_);
            //original type len
            un_.buildError(_conf.getContextEl().getAnalysisMessages().getBadParamerizedType(),
                    realClassName_);
            _conf.addError(un_);
            realClassName_ = _conf.getStandards().getAliasObject();
        }
        analyzeCtor(_conf, realClassName_, firstArgs_);
    }
    private void analyzeCtor(Analyzable _conf, String _realClassName, CustList<ClassArgumentMatching> _firstArgs) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        LgNames stds_ = _conf.getStandards();
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        String base_ = Templates.getIdFromAllTypes(_realClassName);
        GeneType g_ = _conf.getClassBody(base_);
        if (g_ == null) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorUnknown(),
                    _realClassName);
            _conf.addError(call_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        OperationNode possibleInit_ = getFirstChild();
        if (possibleInit_ instanceof StaticInitOperation) {
            StaticInitOperation st_ = (StaticInitOperation) possibleInit_;
            if (!isIntermediateDottedOperation()) {
                boolean staticType_ = g_.isStaticType();
                st_.setInit(_conf,base_,staticType_);
            }
        }
        for (String p:Templates.getAllTypes(_realClassName).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                //part type len
                call_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorBound(),
                        p,
                        _realClassName);
                _conf.addError(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_conf.getCurrentFileName());
                call_.setIndexFile(_conf.getCurrentLocationIndex());
                //part type len
                call_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorBound(),
                        p,
                        _realClassName);
                _conf.addError(call_);
            }
        }
        if (ContextEl.isAbstractType(g_) && !ContextEl.isEnumType(g_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getCurrentFileName());
            call_.setIndexFile(_conf.getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getContextEl().getAnalysisMessages().getIllegalCtorAbstract(),
                    base_);
            _conf.addError(call_);
            setResultClass(new ClassArgumentMatching(_realClassName));
            return;
        }
        blockIndex = _conf.getCurrentChildTypeIndex(this,g_,fieldName,_realClassName);
        if (blockIndex < -1) {
            return;
        }
        ConstructorId feed_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            boolean vararg_ = id_.getConstraints().isVararg();
            StringList params_ = id_.getConstraints().getParametersTypes();
            feed_ = new ConstructorId(idClass_, params_, vararg_);
        }
        ConstrustorIdVarArg ctorRes_ = getDeclaredCustConstructor(_conf, varargOnly_, new ClassArgumentMatching(_realClassName),g_, feed_, ClassArgumentMatching.toArgArray(_firstArgs));
        if (ctorRes_.getRealId() == null) {
            setResultClass(new ClassArgumentMatching(_realClassName));
            return;
        }
        constId = ctorRes_.getRealId();
        className = ctorRes_.getConstId().getName();
        if (ctorRes_.isVarArgToCall()) {
            naturalVararg = constId.getParametersTypes().size() - 1;
            lastType = constId.getParametersTypes().last();
        }
        unwrapArgsFct(filter_, constId, naturalVararg, lastType, _firstArgs, _conf);
        setResultClass(new ClassArgumentMatching(_realClassName));
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        tryGetArg(this,_conf,naturalVararg, constId,lastType);
    }

    public static void tryGetArg(PossibleIntermediateDottedOperable _current, Analyzable _conf,
                                 int _naturalVararg, ConstructorId _constId, String _lastType) {
        CustList<Operable> chidren_ = ((ParentOperable)_current).getChildrenOperable();
        CustList<Argument> arguments_ = new CustList<Argument>();
        CustList<Operable> filter_ = new CustList<Operable>();
        for (Operable o: chidren_) {
            if (o instanceof StaticInitOperable) {
                continue;
            }
            arguments_.add(o.getArgument());
            filter_.add(o);
        }
        if (_constId == null) {
            return;
        }
        CustList<Argument> firstArgs_ = quickListArguments(filter_, _naturalVararg, _lastType, arguments_, _conf);
        ResultErrorStd res_ = LgNames.newInstanceStd(_conf, _constId, Argument.toArgArray(firstArgs_));
        Struct out_ = res_.getResult();
        if (out_ == null) {
            return;
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(out_);
        _current.setSimpleArgumentAna(arg_, _conf);
    }

    public String getMethodName() {
        return methodName;
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public String getClassName() {
        return className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getBlockIndex() {
        return blockIndex;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public boolean isNewBefore() {
        return newBefore;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
