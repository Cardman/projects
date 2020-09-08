package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.blocks.AnalyzedBlock;
import code.expressionlanguage.analyze.blocks.FunctionBlock;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.ReturnMethod;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class InvokingOperation extends MethodOperation implements PossibleIntermediateDotted {
    private ClassArgumentMatching previousResultClass;
    private MethodAccessKind staticAccess;
    private boolean intermediate;

    private Argument previousArgument;

    public InvokingOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        previousResultClass = new ClassArgumentMatching(EMPTY_STRING);
    }

    @Override
    final void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    NameParametersFilter buildFilter(ContextEl _conf) {
        NameParametersFilter out_ = buildQuickFilter(this);
        buildFilter(out_,_conf);
        out_.setOk(out_.getParameterFilterErr().isEmpty());
        return out_;
    }
    private static void buildFilter(NameParametersFilter _filter, ContextEl _conf) {
        for (NamedArgumentOperation o: _filter.getParameterFilterErr()) {
            String name_ = o.getName();
            o.setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+ o.getOffset(), _conf);
            FoundErrorInterpret b_;
            b_ = new FoundErrorInterpret();
            b_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            b_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //param name len
            b_.buildError(_conf.getAnalysisMessages().getDuplicatedParamName(),
                    name_);
            _conf.addError(b_);
            o.getErrs().add(b_.getBuiltError());
        }
    }
    static NameParametersFilter buildQuickFilter(MethodOperation _par) {
        NameParametersFilter out_ = new NameParametersFilter();
        CustList<OperationNode> childrenNodes_ = _par.getChildrenNodes();
        CustList<NamedArgumentOperation> filter_ = out_.getParameterFilter();
        CustList<NamedArgumentOperation> filterErr_ = out_.getParameterFilterErr();
        CustList<ClassArgumentMatching> positionalArgs_ = out_.getPositional();
        StringList names_ = new StringList();
        int delta_ = getDeltaCount(_par.getFirstChild());
        boolean ok_ = true;
        for (OperationNode o: childrenNodes_) {
            if (o instanceof NamedArgumentOperation) {
                String name_ = ((NamedArgumentOperation) o).getName();
                OperationNode next_ = o.getNextSibling();
                if (StringList.contains(names_,name_) || !(next_ instanceof NamedArgumentOperation)&& next_ != null) {
                    ok_ = false;
                    filterErr_.add(((NamedArgumentOperation) o));
                }
                names_.add(name_);
                filter_.add(((NamedArgumentOperation) o));
                if (out_.getIndex() == -1) {
                    out_.setIndex(o.getIndexChild()+delta_);
                }
            } else {
                if (!(o instanceof VarargOperation
                        || o instanceof IdFctOperation
                        || o instanceof StaticInitOperation)) {
                    positionalArgs_.add(o.getResultClass());
                }
            }
        }
        out_.setOk(ok_);
        return out_;
    }
    static String getVarargParam(CustList<OperationNode> _children) {
        if (!_children.isEmpty() && _children.first() instanceof VarargOperation) {
            return ((VarargOperation)_children.first()).getClassName();
        }
        return "";
    }

    protected static String tryGetRetType(ContextEl _an) {
        FunctionBlock f_ = _an.getAnalyzing().getCurrentFct();
        if (f_ instanceof NamedFunctionBlock) {
            NamedFunctionBlock n_ = (NamedFunctionBlock) f_;
            String ret_ = n_.getImportedReturnType();
            String void_ = _an.getStandards().getAliasVoid();
            if (!StringList.quickEq(ret_, void_)) {
                return ret_;
            }
        }
        return EMPTY_STRING;
    }
    protected static String tryGetTypeAff(OperationNode _m) {
        if (_m instanceof CastOperation) {
            CastOperation c_ = (CastOperation) _m;
            return c_.getClassName();
        } else if (_m instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _m;
            SettableElResult s_ = AffectationOperation.tryGetSettable(a_);
            if (s_ != null) {
                ClassArgumentMatching c_ = s_.getResultClass();
                return c_.getSingleNameOrEmpty();
            }
        }
        return EMPTY_STRING;
    }

    protected static int getDeltaCount(OperationNode _firstChild) {
        int deltaCount_ = 0;
        OperationNode next_ = _firstChild;
        if (_firstChild instanceof StaticInitOperation){
            next_ = _firstChild.getNextSibling();
            deltaCount_ = 1;
        }
        if (next_ instanceof IdFctOperation || next_ instanceof VarargOperation) {
            deltaCount_++;
        }
        return deltaCount_;
    }

    protected static void tryGetCtors(ContextEl _an, String _typeInfer, CustList<ConstructorInfo> _ctors) {
        String base_ = StringExpUtil.getIdFromAllTypes(_typeInfer);
        AnaGeneType g_ = _an.getAnalyzing().getAnaGeneType(_an,base_);
        CustList<GeneConstructor> constructors_ = ContextUtil.getConstructorBodies(g_);
        for (GeneConstructor e: constructors_) {
            ConstructorId ctor_ = e.getId().copy(base_);
            if (exclude(g_,_an,null,-1, e)) {
                continue;
            }
            ParametersGroup pg_ = new ParametersGroup();
            ConstructorInfo mloc_ = new ConstructorInfo();
            mloc_.setConstraints(ctor_);
            mloc_.setParametersNames(e.getParametersNames());
            mloc_.setParameters(pg_);
            mloc_.setClassName(_typeInfer);
            mloc_.format(_an);
            _ctors.add(mloc_);
        }
    }
    protected static String tryParamFormat(NameParametersFilter _filter,Parametrable _param, String _name, int nbParentsInfer_, String type_, StringMap<String> vars_, ContextEl _an) {
        if (!isValidNameIndex(_filter,_param,_name)) {
            return null;
        }
        int ind_ = StringList.indexOf(_param.getParametersNames(), _name);
        return tryFormat(_param, ind_, nbParentsInfer_, type_, vars_, _an);
    }
    protected static boolean isValidNameIndex(NameParametersFilter _filter, Parametrable _param, String _name) {
        int ind_ = StringList.indexOf(_param.getParametersNames(), _name);
        StringList formattedParams_ = _param.getFormattedParams();
        if (!formattedParams_.isValidIndex(ind_)) {
            return false;
        }
        int lengthArgs_ = _filter.getPositional().size();
        return ind_ >= Math.min(lengthArgs_, _filter.getIndex());
    }
    protected static String tryParamVarargFormat(Parametrable _param, String _name) {
        if (!_param.isVararg()) {
            return null;
        }
        int ind_ = StringList.indexOf(_param.getParametersNames(), _name);
        StringList formattedParams_ = _param.getFormattedParams();
        if (!formattedParams_.isValidIndex(ind_)) {
            return null;
        }
        Identifiable idMethod_ = _param.getGeneFormatted();
        if (ind_ != idMethod_.getParametersTypesLength() - 1) {
            return null;
        }
        return StringExpUtil.getPrettyArrayType(idMethod_.getParametersTypes().last());
    }
    protected static String tryFormat(Parametrable _param, int indexChild_, int nbParentsInfer_, String type_, StringMap<String> vars_, ContextEl _an) {
        String parametersType_ = tryGetParam(_param,indexChild_);
        if (parametersType_ == null) {
            return null;
        }
        boolean applyTwo_ = applyArrayOrElement(_param, indexChild_);
        String cp_ = StringExpUtil.getQuickComponentType(parametersType_, nbParentsInfer_);
        if (!applyTwo_) {
            if (isNotCorrectDim(cp_)) {
                return null;
            }
        } else {
            if (isNotCorrectDim(cp_)) {
                String cpTwo_ = StringExpUtil.getQuickComponentType(parametersType_, nbParentsInfer_-1);
                if (isNotCorrectDim(cpTwo_)) {
                    return null;
                }
                cp_ = cpTwo_;
            }
        }
        return AnaTemplates.tryInfer(type_,vars_, cp_, _an);
    }
    protected static String tryGetParam(Parametrable _param, int indexChild_) {
        String parametersType_;
        Identifiable idMethod_ = _param.getGeneFormatted();
        if (!idMethod_.isVararg()) {
            if (idMethod_.getParametersTypesLength() <= indexChild_) {
                return null;
            }
            parametersType_ = idMethod_.getParametersType(indexChild_);
        } else {
            if (idMethod_.getParametersTypesLength() <= indexChild_) {
                parametersType_ = idMethod_.getParametersTypes().last();
            } else {
                parametersType_ = idMethod_.getParametersType(indexChild_);
            }
        }
        return parametersType_;
    }
    protected static boolean applyArrayOrElement(Parametrable _param, int indexChild_) {
        Identifiable idMethod_ = _param.getGeneFormatted();
        if (!idMethod_.isVararg()) {
            return false;
        } else {
            if (idMethod_.getParametersTypesLength() <= indexChild_) {
                return false;
            } else {
                return indexChild_ + 1 == idMethod_.getParametersTypesLength();
            }
        }
    }

    protected boolean applyMatching() {
        boolean apply_ = false;
        OperationNode curPar_ = getParent();
        if (curPar_ instanceof AbstractDotOperation) {
            if (getIndexChild() > 0) {
                if (curPar_.getParent() == null) {
                    apply_ = true;
                }
            }
        } else if (curPar_ == null){
            apply_ = true;
        }
        return apply_;
    }

    protected static void filterByNameReturnType(ContextEl _an, String trimMeth_, boolean apply_, CustList<CustList<MethodInfo>> _methodInfos) {
        int len_ = _methodInfos.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = _methodInfos.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = _methodInfos.get(i).get(j);
                if (!StringList.quickEq(methodInfo_.getConstraints().getName(),trimMeth_)) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            _methodInfos.set(i, newList_);
        }
        String typeAff_ = EMPTY_STRING;
        AnalyzedBlock cur_ = _an.getAnalyzing().getCurrentAnaBlock();
        if (apply_ && cur_ instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_an);
        }
        filterByReturnType(_an, typeAff_, _methodInfos);
    }

    protected static void filterByReturnType(ContextEl _an, String typeAff_, CustList<CustList<MethodInfo>> _methodInfos) {
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (isUndefined(typeAff_, keyWordVar_)) {
            return;
        }
        int len_ = _methodInfos.size();
        Mapping mapping_ = new Mapping();
        mapping_.setParam(typeAff_);
        StringMap<StringList> currVars_ = _an.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        mapping_.setMapping(currVars_);
        for (int i = 0; i < len_; i++) {
            int gr_ = _methodInfos.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = _methodInfos.get(i).get(j);
                String returnType_ = methodInfo_.getReturnType();
                mapping_.setArg(returnType_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_,_an)) {
                    ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_an, typeAff_, new ClassArgumentMatching(returnType_));
                    if (!res_.isFoundMethod()) {
                        continue;
                    }
                }
                newList_.add(methodInfo_);
            }
            _methodInfos.set(i, newList_);
        }
    }

    protected static ClassMethodId getTrueFalse(ContextEl _conf, ClassMethodId _feedBase) {
        ClassMethodId f_ = _feedBase;
        if (f_ != null) {
            MethodId constraints_ = f_.getConstraints();
            String name_ = constraints_.getName();
            String className_ = f_.getClassName();
            StringList parametersTypes_ = constraints_.getParametersTypes();
            parametersTypes_.add(0,_conf.getStandards().getAliasPrimBoolean());
            f_ = new ClassMethodId(className_,new MethodId(MethodAccessKind.STATIC,name_,parametersTypes_));
        }
        return f_;
    }
    public static boolean isTrueFalseKeyWord(ContextEl _an, String _trimMeth) {
        return StringList.quickEq(_trimMeth,_an.getKeyWords().getKeyWordTrue())
                ||StringList.quickEq(_trimMeth,_an.getKeyWords().getKeyWordFalse());
    }
    protected static boolean isNotCorrectDim(String cp_) {
        return cp_ == null||cp_.startsWith("[");
    }

    protected static boolean isUndefined(String typeAff_, String keyWordVar_) {
        return typeAff_.isEmpty() || StringList.quickEq(typeAff_, keyWordVar_);
    }
    private static CustList<OperationNode> filterOperands(CustList<OperationNode> _children) {
        CustList<OperationNode> firstArgs_ = new CustList<OperationNode>();
        for (OperationNode o: _children) {
            if (o instanceof IdFctOperation
                    || o instanceof VarargOperation) {
                continue;
            }
            firstArgs_.add(o);
        }
        return firstArgs_;
    }

    public static CustList<Argument> quickListArguments(CustList<OperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes) {
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            int lenCh_ = _children.size();
            int natVarArg_ = _natVararg;
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                if (_children.get(i) instanceof IdFctOperation
                        || _children.get(i) instanceof VarargOperation) {
                    natVarArg_++;
                    continue;
                }
                Argument a_ = _nodes.get(i);
                if (i >= natVarArg_) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
            }
            Argument argRem_ = new Argument();
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            ExecTemplates.setElements(optArgs_,str_);
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
            if (_children.get(i) instanceof IdFctOperation
                    || _children.get(i) instanceof VarargOperation) {
                continue;
            }
            Argument a_ = _nodes.get(i);
            firstArgs_.add(a_);
        }
        return firstArgs_;
    }
    static StringList getBounds(String _cl, ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String objectClassName_ = stds_.getAliasObject();
        StringList bounds_ = new StringList();
        if (_cl.startsWith(AnaTemplates.PREFIX_VAR_TYPE)) {
            String glClass_ = _conf.getAnalyzing().getGlobalClass();
            String curClassBase_ = StringExpUtil.getIdFromAllTypes(glClass_);
            AnaGeneType gl_ = _conf.getAnalyzing().getAnaGeneType(_conf,curClassBase_);
            StringMap<StringList> mapping_ = new StringMap<StringList>();
            for (TypeVar t: ContextUtil.getParamTypesMapValues(gl_)) {
                mapping_.put(t.getName(), t.getConstraints());
            }
            bounds_.addAllElts(Mapping.getAllUpperBounds(mapping_, _cl.substring(1), objectClassName_));
        } else {
            bounds_.add(_cl);
        }
        return bounds_;
    }

    static void unwrapArgsFct(CustList<OperationNode> _ch, Identifiable _id, int _natvararg, String _lasttype, CustList<ClassArgumentMatching> _args, ContextEl _conf) {
        if (_natvararg > -1) {
            int lenCh_ = _args.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                ClassArgumentMatching a_ = _args.get(i);
                if (i >= _natvararg) {
                    if (PrimitiveTypeUtil.isPrimitive(_lasttype, _conf)) {
                        a_.setUnwrapObject(_lasttype);
                    }
                } else {
                    String param_ = _id.getParametersTypes().get(i);
                    if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                        a_.setUnwrapObject(param_);
                    }
                }
            }
        } else {
            int lenCh_ = _args.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                ClassArgumentMatching a_ = _args.get(i);
                String param_ = _id.getParametersTypes().get(i);
                if (i + 1 == lenCh_ && _id.isVararg()) {
                    param_ = StringExpUtil.getPrettyArrayType(param_);
                }
                if (PrimitiveTypeUtil.isPrimitive(param_, _conf)) {
                    a_.setUnwrapObject(param_);
                }
            }
        }
        for (OperationNode o: filterOperands(_ch)) {
            o.cancelArgument();
        }
    }
    final int lookOnlyForVarArg() {
        OperationNode first_ = getFirstChild();
        int from_ = 1;
        if (first_ == null) {
            return -1;
        }
        if (first_ instanceof StaticInitOperation) {
            from_++;
            first_ = first_.getNextSibling();
            if (first_ == null) {
                return -1;
            }
        }
        if (!(first_ instanceof VarargOperation)&&!(first_ instanceof IdFctOperation)) {
            return -1;
        }
        CustList<OperationNode> ch_ = getChildrenNodes();
        int firstOpt_ = 0;
        boolean found_ = false;
        int len_ = ch_.size();
        for (int i = from_; i < len_;i++) {
            if (ch_.get(i) instanceof FirstOptOperation) {
                firstOpt_ = i + 1 - from_;
                found_ = true;
                break;
            }
        }
        if (!found_ && first_ instanceof IdFctOperation) {
            return -1;
        }
        return firstOpt_;
    }
    final ClassMethodIdAncestor lookOnlyForId() {
        OperationNode first_ = getFirstChild();
        if (first_ == null) {
            return null;
        }
        if (first_ instanceof StaticInitOperation) {
            first_ = first_.getNextSibling();
            if (first_ == null) {
                return null;
            }
        }
        if (!(first_ instanceof IdFctOperation)) {
            return null;
        }
        return ((IdFctOperation)first_).getMethod();
    }
    @Override
    public final void setIntermediateDotted() {
        intermediate = true;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        previousResultClass = _previousResultClass;
        staticAccess = _staticAccess;
    }

    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    public final MethodAccessKind isStaticAccess() {
        return staticAccess;
    }

    public final void setStaticAccess(MethodAccessKind _staticAccess) {
        staticAccess = _staticAccess;
    }

}
