package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.common.Matching;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class InvokingOperation extends MethodOperation implements PossibleIntermediateDotted {
    private AnaClassArgumentMatching previousResultClass;
    private MethodAccessKind staticAccess;
    private boolean intermediate;

    public InvokingOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        previousResultClass = new AnaClassArgumentMatching(EMPTY_STRING);
    }

    @Override
    final void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
    }

    NameParametersFilter buildFilter(AnalyzedPageEl _page) {
        StaticCallAccessOperation st_ = null;
        if (getParent() instanceof AbstractDotOperation) {
            OperationNode firstChild_ = getParent().getFirstChild();
            if (firstChild_ instanceof StaticCallAccessOperation) {
                st_ = (StaticCallAccessOperation) firstChild_;
            }
        }
        NameParametersFilter out_ = buildQuickFilter(_page,this);
        out_.setFormattedFilter(getFormattedFilter(_page, this));
        out_.setStaticCallOp(st_);
        buildFilter(out_, _page);
        out_.setOk(out_.getParameterFilterErr().isEmpty());
        return out_;
    }

    static FormattedFilter getFormattedFilter(AnalyzedPageEl _page, OperationNode _op) {
        FormattedFilter f_ = new FormattedFilter();
        f_.setStCall(getStCall(_op));
        f_.setReturnType(getFormattedReturnType(_op,_page));
        return f_;
    }
    static String getFormattedReturnType(OperationNode _op,AnalyzedPageEl _page) {
        boolean apply_ = false;
        if (_op.getParent() instanceof AbstractDotOperation) {
            OperationNode firstChild_ = _op.getParent().getFirstChild();
            if (firstChild_ instanceof StaticCallAccessOperation) {
                apply_ = applyMatching(_op);
            }
        }
        String typeAff_ = EMPTY_STRING;
        if (apply_) {
            OperationNode parentMatching_ = getParentMatching(_op);
            AbsBk cur_ = _page.getCurrentBlock();
            if (parentMatching_ == null &&cur_ instanceof ReturnMethod) {
                typeAff_ = tryGetRetType(_page);
            } else {
                typeAff_ = tryGetTypeAff(parentMatching_, 1);
            }
        }
        return typeAff_;
    }
    static String getStCall(OperationNode _op) {
        if (_op.getParent() instanceof AbstractDotOperation) {
            OperationNode firstChild_ = _op.getParent().getFirstChild();
            if (firstChild_ instanceof StaticCallAccessOperation) {
                StaticCallAccessOperation st_ = (StaticCallAccessOperation) firstChild_;
                return st_.getStCall();
            }
        }
        return "";
    }
    private static void buildFilter(NameParametersFilter _filter, AnalyzedPageEl _page) {
        for (NamedArgumentOperation o: _filter.getParameterFilterErr()) {
            String name_ = o.getName();
            o.setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+ o.getOffsetTr(), _page);
            FoundErrorInterpret b_;
            b_ = new FoundErrorInterpret();
            b_.setFileName(_page.getLocalizer().getCurrentFileName());
            b_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //param name len
            b_.buildError(_page.getAnalysisMessages().getDuplicatedParamName(),
                    name_);
            _page.getLocalizer().addError(b_);
            o.addErr(b_.getBuiltError());
        }
    }
    static NameParametersFilter buildQuickFilter(AnalyzedPageEl _page,MethodOperation _par) {
        NameParametersFilter out_ = new NameParametersFilter();
        CustList<OperationNode> childrenNodes_ = _par.getChildrenNodes();
        CustList<NamedArgumentOperation> filter_ = out_.getParameterFilter();
        CustList<NamedArgumentOperation> filterErr_ = out_.getParameterFilterErr();
        CustList<OperationNode> positionalArgs_ = out_.getPositional();
        StringList names_ = new StringList();
        int delta_ = getDeltaCount(_par.getFirstChild());
        boolean ok_ = true;
        for (OperationNode o: childrenNodes_) {
            if (o instanceof NamedArgumentOperation) {
                String name_ = ((NamedArgumentOperation) o).getName();
                OperationNode next_ = o.getNextSibling();
                if (StringUtil.contains(names_,name_) || !(next_ instanceof NamedArgumentOperation)&& next_ != null) {
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
                        || o instanceof IdFctOperation)) {
                    positionalArgs_.add(o);
                }
            }
        }
        out_.setOk(ok_);
        out_.setFormattedFilter(getFormattedFilter(_page,_par));
        return out_;
    }
    static String getVarargParam(CustList<OperationNode> _children) {
        if (!_children.isEmpty() && _children.first() instanceof VarargOperation) {
            return ((VarargOperation)_children.first()).getClassName();
        }
        return "";
    }

    protected static String tryGetRetType(AnalyzedPageEl _page) {
        FunctionBlock f_ = _page.getCurrentFct();
        if (f_ instanceof NamedFunctionBlock) {
            NamedFunctionBlock n_ = (NamedFunctionBlock) f_;
            String ret_ = n_.getImportedReturnType();
            String void_ = _page.getAliasVoid();
            if (!StringUtil.quickEq(ret_, void_)) {
                return ret_;
            }
        }
        if (f_ instanceof SwitchMethodBlock) {
            SwitchMethodBlock n_ = (SwitchMethodBlock) f_;
            return n_.getRetType();
        }
        return EMPTY_STRING;
    }
    protected static String tryGetTypeAff(OperationNode _m, int _indexChild) {
        if (_m instanceof CastOperation) {
            CastOperation c_ = (CastOperation) _m;
            return c_.getClassName();
        } else if (_m instanceof WrappOperation) {
            return _m.getResultClass().getSingleNameOrEmpty();
        } else if (_m instanceof AffectationOperation) {
            AffectationOperation a_ = (AffectationOperation) _m;
            SettableElResult s_ = null;
            if (_indexChild > 0) {
                s_ = AffectationOperation.tryGetSettable(a_);
            }
            if (s_ != null) {
                AnaClassArgumentMatching c_ = s_.getResultClass();
                return c_.getSingleNameOrEmpty();
            }
        }
        return EMPTY_STRING;
    }

    protected static int getDeltaCount(OperationNode _firstChild) {
        int deltaCount_ = 0;
        if (_firstChild instanceof IdFctOperation || _firstChild instanceof VarargOperation) {
            deltaCount_++;
        }
        return deltaCount_;
    }

    protected static int getDeltaCount(boolean _list,OperationNode _firstChild) {
        if (_list) {
            return 0;
        }
        int deltaCount_ = 0;
        if (_firstChild instanceof IdFctOperation || _firstChild instanceof VarargOperation) {
            deltaCount_++;
        }
        return deltaCount_;
    }

    protected static void tryGetCtors(String _typeInfer, CustList<ConstructorInfo> _ctors, AnalyzedPageEl _page, AnaGeneType _anaGeneType) {
        String base_ = StringExpUtil.getIdFromAllTypes(_typeInfer);
        if (_anaGeneType instanceof StandardType) {
            for (StandardConstructor e: ((StandardType)_anaGeneType).getConstructors()) {
                ConstructorId ctor_ = e.getId().copy(base_);
                ConstructorInfo mloc_ = new ConstructorInfo();
                initCtorInfo((StandardType)_anaGeneType,_typeInfer,e,ctor_,mloc_);
                mloc_.format(_page);
                _ctors.add(mloc_);
            }
        }
        if (_anaGeneType instanceof RootBlock) {
            for (ConstructorBlock e: ((RootBlock)_anaGeneType).getConstructorBlocks()) {
                ConstructorId ctor_ = e.getId().copy(base_);
                if (excludeCust((RootBlock) _anaGeneType, null,-1, e, _page)) {
                    continue;
                }
                ConstructorInfo mloc_ = new ConstructorInfo();
                initCtorInfo((RootBlock) _anaGeneType,_typeInfer,e,ctor_,mloc_);
                mloc_.format(_page);
                _ctors.add(mloc_);
            }
        }

    }
    protected static String tryParamFormat(NameParametersFilter _filter, Parametrable _param, String _name, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        if (!isValidNameIndex(_filter,_param,_name)) {
            return null;
        }
        int ind_ = StringUtil.indexOf(_param.getParametersNames(), _name);
        return tryFormat(_param, ind_, _nbParentsInfer, _type, _vars, _page);
    }
    protected static StringList tryParamFormatFct(NameParametersFilter _filter, Parametrable _param, String _name, int _nbParentsInfer, String _type, String _full,StringMap<String> _vars, AnalyzedPageEl _page) {
        if (!isValidNameIndex(_filter,_param,_name)) {
            return new StringList();
        }
        int ind_ = StringUtil.indexOf(_param.getParametersNames(), _name);
        return tryFormatFct(_param, ind_, _nbParentsInfer, _type, _full, _vars, _page);
    }
    protected static StringList tryParamFormatFctRef(NameParametersFilter _filter, Parametrable _param, String _name, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        if (!isValidNameIndex(_filter,_param,_name)) {
            return new StringList();
        }
        int ind_ = StringUtil.indexOf(_param.getParametersNames(), _name);
        return tryFormatFctRef(_param, ind_, _nbParentsInfer, _type, _vars, _page);
    }
    protected static boolean isValidNameIndex(NameParametersFilter _filter, Parametrable _param, String _name) {
        int ind_ = StringUtil.indexOf(_param.getParametersNames(), _name);
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
        int ind_ = StringUtil.indexOf(_param.getParametersNames(), _name);
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
    public static void tryInfer(OperationNode _current, AnalyzedPageEl _page) {
        AnaClassArgumentMatching arg_ = _current.getResultClass();
        MethodOperation m_ = _current.getParent();
        boolean list_ = false;
        if (m_ instanceof ArgumentListInstancing){
            list_ = true;
            m_ = m_.getParent().getParent();
        }
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String name_ = n_.getName();
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                RetrieveMethod f_ = (RetrieveMethod) call_;
                NameParametersFilter filter_ = buildQuickFilter(_page,call_);
                CustList<OperationNode> positional_ = filter_.getPositional();
                int posSize_ = positional_.size();
                CustList<NamedArgumentOperation> namedPrev_ = filter_.getParameterFilter();
                int nbNames_ = namedPrev_.size()-1;
                String returnType_ = emptIfVar(_page, filter_.getReturnType());
                if (StringUtil.quickEq(filter_.getStaticCall(),"<>")) {
                    CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                    int len_ = methodInfos_.size();
                    for (int i = 0; i < len_; i++) {
                        int gr_ = methodInfos_.get(i).size();
                        for (int j = 0; j < gr_; j++) {
                            MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                            if (!isValidNameIndex(filter_,methodInfo_,name_)) {
                                continue;
                            }
                            if (methodInfo_.getConstraints().getKind() != MethodAccessKind.STATIC_CALL) {
                                continue;
                            }
                            CustList<Matching> cts_ = new CustList<Matching>();
                            for (int c = 0; c < posSize_; c++) {
                                cts_.addAllElts(
                                        AnaTemplates.tryInferMethodByOneArgList(methodInfo_.getClassName(), c, methodInfo_.getConstraints(),
                                                methodInfo_.getClassName(),
                                                _page.getCurrentConstraints().getCurrentConstraints(),
                                                positional_.get(c).getResultClass(), methodInfo_.getOriginalReturnType(), returnType_, _page)
                                );
                            }
                            for (int c = 0; c < nbNames_; c++) {
                                NamedArgumentOperation calcName_ = namedPrev_.get(c);
                                String namePr_ = calcName_.getName();
                                int ind_ = StringUtil.indexOf(methodInfo_.getParametersNames(), namePr_);
                                cts_.addAllElts(
                                        AnaTemplates.tryInferMethodByOneArgList(methodInfo_.getClassName(), ind_, methodInfo_.getConstraints(),
                                                methodInfo_.getClassName(),
                                                _page.getCurrentConstraints().getCurrentConstraints(),
                                                calcName_.getResultClass(), methodInfo_.getOriginalReturnType(), returnType_, _page)
                                );
                            }
                            int ind_ = StringUtil.indexOf(methodInfo_.getParametersNames(), name_);
                            String infer_ = AnaTemplates.tryInferMethodByOneArg(methodInfo_.getClassName(), ind_, methodInfo_.getConstraints(),
                                    methodInfo_.getClassName(),
                                    _page.getCurrentConstraints().getCurrentConstraints(),
                                    arg_, methodInfo_.getOriginalReturnType(), returnType_, _page);
                            tryReformat(_page, methodInfo_, infer_);
                        }
                    }
                } else {
                    CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                    int len_ = methodInfos_.size();
                    for (int i = 0; i < len_; i++) {
                        int gr_ = methodInfos_.get(i).size();
                        for (int j = 0; j < gr_; j++) {
                            MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                            if (!isValidNameIndex(filter_,methodInfo_,name_)) {
                                continue;
                            }
                            if (methodInfo_.getConstraints().getKind() != MethodAccessKind.STATIC_CALL) {
                                continue;
                            }
                            CustList<Matching> cts_ = new CustList<Matching>();
                            for (int c = 0; c < posSize_; c++) {
                                cts_.addAllElts(
                                        AnaTemplates.tryInferMethodByOneArgList(methodInfo_.getClassName(), c, methodInfo_.getConstraints(),
                                                filter_.getStaticCall(),
                                                _page.getCurrentConstraints().getCurrentConstraints(),
                                                positional_.get(c).getResultClass(), methodInfo_.getOriginalReturnType(), returnType_, _page)
                                );
                            }
                            for (int c = 0; c < nbNames_; c++) {
                                NamedArgumentOperation calcName_ = namedPrev_.get(c);
                                String namePr_ = calcName_.getName();
                                int ind_ = StringUtil.indexOf(methodInfo_.getParametersNames(), namePr_);
                                cts_.addAllElts(
                                        AnaTemplates.tryInferMethodByOneArgList(methodInfo_.getClassName(), ind_, methodInfo_.getConstraints(),
                                                filter_.getStaticCall(),
                                                _page.getCurrentConstraints().getCurrentConstraints(),
                                                calcName_.getResultClass(), methodInfo_.getOriginalReturnType(), returnType_, _page)
                                );
                            }
                            int ind_ = StringUtil.indexOf(methodInfo_.getParametersNames(), name_);
                            String infer_ = AnaTemplates.tryInferMethodByOneArg(methodInfo_.getClassName(), ind_, methodInfo_.getConstraints(),
                                    filter_.getStaticCall(),
                                    _page.getCurrentConstraints().getCurrentConstraints(),
                                    arg_, methodInfo_.getOriginalReturnType(), returnType_, _page);
                            tryReformat(_page, methodInfo_, infer_);
                        }
                    }
                }

            }
            return;
        }
        if (m_ instanceof RetrieveMethod){
            CustList<OperationNode> childrenNodes_ = m_.getChildrenNodes();
            RetrieveMethod f_ = (RetrieveMethod) m_;
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = getDeltaCount(list_,firstChild_);
            int indexChild_ = _current.getIndexChild()-deltaCount_;
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            FormattedFilter filter_ = getFormattedFilter(_page, m_);
            String returnType_ = emptIfVar(_page, filter_.getReturnType());
            int nbCh_ = Math.min(childrenNodes_.size()-1,indexChild_);
            if (StringUtil.quickEq(filter_.getStCall(),"<>")) {
                for (int i = 0; i < len_; i++) {
                    int gr_ = methodInfos_.get(i).size();
                    for (int j = 0; j < gr_; j++) {
                        MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                        if (methodInfo_.getConstraints().getKind() != MethodAccessKind.STATIC_CALL) {
                            continue;
                        }
                        CustList<Matching> cts_ = new CustList<Matching>();
                        for (int c = 0; c < nbCh_; c++) {
                            cts_.addAllElts(
                                    AnaTemplates.tryInferMethodByOneArgList(methodInfo_.getClassName(), c, methodInfo_.getConstraints(),
                                            methodInfo_.getClassName(),
                                            _page.getCurrentConstraints().getCurrentConstraints(),
                                            childrenNodes_.get(c).getResultClass(), methodInfo_.getOriginalReturnType(), returnType_, _page)
                            );
                        }
                        cts_.addAllElts(
                                AnaTemplates.tryInferMethodByOneArgList(methodInfo_.getClassName(), indexChild_, methodInfo_.getConstraints(),
                                        methodInfo_.getClassName(),
                                        _page.getCurrentConstraints().getCurrentConstraints(),
                                        arg_, methodInfo_.getOriginalReturnType(), returnType_, _page)
                        );
                        String infer_ = AnaTemplates.tryInferMethodByOneArg(cts_,methodInfo_.getClassName(),
                                _page.getCurrentConstraints().getCurrentConstraints(), _page);
                        tryReformat(_page, methodInfo_, infer_);
                    }
                }
            } else {
                for (int i = 0; i < len_; i++) {
                    int gr_ = methodInfos_.get(i).size();
                    for (int j = 0; j < gr_; j++) {
                        MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                        if (methodInfo_.getConstraints().getKind() != MethodAccessKind.STATIC_CALL) {
                            continue;
                        }
                        CustList<Matching> cts_ = new CustList<Matching>();
                        for (int c = 0; c < nbCh_; c++) {
                            cts_.addAllElts(
                                    AnaTemplates.tryInferMethodByOneArgList(methodInfo_.getClassName(), c, methodInfo_.getConstraints(),
                                            filter_.getStCall(),
                                            _page.getCurrentConstraints().getCurrentConstraints(),
                                            childrenNodes_.get(c).getResultClass(), methodInfo_.getOriginalReturnType(), returnType_, _page)
                            );
                        }
                        cts_.addAllElts(
                                AnaTemplates.tryInferMethodByOneArgList(methodInfo_.getClassName(), indexChild_, methodInfo_.getConstraints(),
                                        filter_.getStCall(),
                                        _page.getCurrentConstraints().getCurrentConstraints(),
                                        arg_, methodInfo_.getOriginalReturnType(), returnType_, _page)
                        );
                        String infer_ = AnaTemplates.tryInferMethodByOneArg(cts_,
                                filter_.getStCall(),
                                _page.getCurrentConstraints().getCurrentConstraints(),
                                _page);
                        tryReformat(_page, methodInfo_, infer_);
                    }
                }
            }

        }
    }

    private static void tryReformat(AnalyzedPageEl _page, MethodInfo _methodInfo, String _infer) {
        if (!_infer.isEmpty()) {
            _methodInfo.setFormattedFilter(new FormattedFilter());
            _methodInfo.reformat(_infer, _page);
        }
    }

    private static String emptIfVar(AnalyzedPageEl _page, String _returnType) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(_returnType, keyWordVar_)) {
            return "";
        }
        return _returnType;
    }

    protected static String tryFormat(Parametrable _param, int _indexChild, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        String cp_ = tryGetParamDim(_param, _indexChild, _nbParentsInfer);
        if (cp_ == null) {
            return null;
        }
        return tryInferOrImplicit(_type, _vars, _page, cp_);
    }
    protected static StringList tryFormatFct(Parametrable _param, int _indexChild, int _nbParentsInfer, String _type,String _full, StringMap<String> _vars, AnalyzedPageEl _page) {
        String cp_ = tryGetParamDim(_param, _indexChild, _nbParentsInfer);
        if (cp_ == null) {
            return new StringList();
        }
        return tryInferOrImplicitFct(_type, _full, _vars, _page, cp_);
    }

    protected static StringList tryInferOrImplicitFct(String _type, String _full, StringMap<String> _vars, AnalyzedPageEl _page, String _cp) {
        String inferred_ = AnaTemplates.tryInfer(_type, _vars, _cp, _page);
        if (inferred_ != null) {
            return new StringList(inferred_);
        }
        return AnaTemplates.tryGetDeclaredImplicitCastFct(_cp, _vars, _full, _page, _page.getCurrentConstraints().getCurrentConstraints());
    }

    protected static StringList tryFormatFctRef(Parametrable _param, int _indexChild, int _nbParentsInfer, String _type, StringMap<String> _vars, AnalyzedPageEl _page) {
        String cp_ = tryGetParamDim(_param, _indexChild, _nbParentsInfer);
        if (cp_ == null) {
            return new StringList();
        }
        return tryInferOrImplicitFctRef(_type, _vars, _page, cp_);
    }

    protected static StringList tryInferOrImplicitFctRef(String _type, StringMap<String> _vars, AnalyzedPageEl _page, String _cp) {
        return tryInferOrImplicitFct(_type, _type, _vars, _page, _cp);
    }

    protected static String tryInferOrImplicit(String _type, StringMap<String> _vars, AnalyzedPageEl _page, String _cp) {
        String inferred_ = AnaTemplates.tryInfer(_type, _vars, _cp, _page);
        if (inferred_ != null) {
            return inferred_;
        }
        return AnaTemplates.tryGetDeclaredImplicitCast(_cp,_vars,_type,_page,_page.getCurrentConstraints().getCurrentConstraints());
    }

    protected static String tryGetParamDim(Parametrable _param, int _indexChild, int _nbParentsInfer) {
        String parametersType_ = tryGetParam(_param, _indexChild);
        if (parametersType_ == null) {
            return null;
        }
        boolean applyTwo_ = applyArrayOrElement(_param, _indexChild);
        String cp_ = StringExpUtil.getQuickComponentType(parametersType_, _nbParentsInfer);
        if (!applyTwo_) {
            if (isNotCorrectDim(cp_)) {
                return null;
            }
        } else {
            if (isNotCorrectDim(cp_)) {
                String cpTwo_ = StringExpUtil.getQuickComponentType(parametersType_, _nbParentsInfer -1);
                if (isNotCorrectDim(cpTwo_)) {
                    return null;
                }
                cp_ = cpTwo_;
            }
        }
        return cp_;
    }

    protected static String tryGetParamAny(Parametrable _param, int _indexChild, int _nbParentsInfer) {
        String parametersType_ = tryGetParam(_param, _indexChild);
        if (parametersType_ == null) {
            return null;
        }
        boolean applyTwo_ = applyArrayOrElement(_param, _indexChild);
        String cp_ = StringExpUtil.getQuickComponentType(parametersType_, _nbParentsInfer);
        if (applyTwo_) {
            if (cp_ == null) {
                cp_ = StringExpUtil.getQuickComponentType(parametersType_, _nbParentsInfer -1);
            }
        }
        return cp_;
    }

    protected static String tryGetParam(Parametrable _param, int _indexChild) {
        String parametersType_;
        Identifiable idMethod_ = _param.getGeneFormatted();
        if (!idMethod_.isVararg()) {
            if (idMethod_.getParametersTypesLength() <= _indexChild) {
                return null;
            }
            parametersType_ = idMethod_.getParametersType(_indexChild);
        } else {
            if (idMethod_.getParametersTypesLength() <= _indexChild) {
                parametersType_ = idMethod_.getParametersTypes().last();
            } else {
                parametersType_ = idMethod_.getParametersType(_indexChild);
            }
        }
        return parametersType_;
    }
    protected static boolean applyArrayOrElement(Parametrable _param, int _indexChild) {
        Identifiable idMethod_ = _param.getGeneFormatted();
        if (!idMethod_.isVararg()) {
            return false;
        } else {
            if (idMethod_.getParametersTypesLength() <= _indexChild) {
                return false;
            } else {
                return _indexChild + 1 == idMethod_.getParametersTypesLength();
            }
        }
    }

    protected void filterByNameReturnType(AnalyzedPageEl _page, String _trimMeth, CustList<CustList<MethodInfo>> _methodInfos) {
        boolean apply_ = applyMatching(this);
        String stCall_ = getStCall(this);
        filterByNameReturnType(_trimMeth, apply_, _methodInfos, _page, stCall_, getParentMatching(this));
    }

    private static boolean applyMatching(OperationNode _op) {
        boolean apply_ = false;
        OperationNode cur_ = _op;
        OperationNode curPar_ = _op.getParent();
        if (curPar_ instanceof AbstractDotOperation) {
            if (_op.getIndexChild() > 0) {
                if (curPar_.getParent() == null) {
                    apply_ = true;
                } else {
                    cur_ = cur_.getParent();
                    curPar_ = curPar_.getParent();
                }
            }
        } else if (curPar_ instanceof WrappOperation){
            apply_ = true;
        } else if (curPar_ == null){
            apply_ = true;
        }
        while (curPar_ instanceof IdOperation && curPar_.getOperations().getValues().size() <= 1) {
            curPar_ = curPar_.getParent();
            cur_ = cur_.getParent();
        }
        if (curPar_ instanceof AffectationOperation) {
            if (cur_.getIndexChild() > 0) {
                apply_ = true;
            }
        }
        return apply_;
    }
    protected static OperationNode getParentMatching(OperationNode _op) {
        OperationNode cur_ = _op;
        OperationNode curPar_ = _op.getParent();
        if (curPar_ instanceof AbstractDotOperation) {
            if (_op.getIndexChild() > 0) {
                if (curPar_.getParent() == null) {
                    return null;
                } else {
                    cur_ = cur_.getParent();
                    curPar_ = curPar_.getParent();
                }
            }
        } else if (curPar_ instanceof WrappOperation){
            return curPar_;
        } else if (curPar_ == null){
            return null;
        }
        while (curPar_ instanceof IdOperation && curPar_.getOperations().getValues().size() <= 1) {
            curPar_ = curPar_.getParent();
            cur_ = cur_.getParent();
        }
        if (curPar_ instanceof AffectationOperation) {
            if (cur_.getIndexChild() > 0) {
                return curPar_;
            }
        }
        return null;
    }
    private static void filterByNameReturnType(String _trimMeth, boolean _apply, CustList<CustList<MethodInfo>> _methodInfos, AnalyzedPageEl _page, String _stCall,OperationNode _parentMatching) {
        int len_ = _methodInfos.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = _methodInfos.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = _methodInfos.get(i).get(j);
                if (!StringUtil.quickEq(methodInfo_.getConstraints().getName(),_trimMeth)) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            _methodInfos.set(i, newList_);
        }
        filterByReturnType(_stCall,_apply, _methodInfos, _page, _parentMatching);
    }

    protected static void filterByReturnType(String _stCall,boolean _apply, CustList<CustList<MethodInfo>> _methodInfos, AnalyzedPageEl _page, OperationNode _parentMatching) {
        String typeAff_ = EMPTY_STRING;
        AbsBk cur_ = _page.getCurrentBlock();
        if (_apply) {
            if (_parentMatching == null &&cur_ instanceof ReturnMethod) {
                typeAff_ = tryGetRetType(_page);
            } else {
                typeAff_ = tryGetTypeAff(_parentMatching, 1);
            }
        }
        filterByReturnType(_stCall,typeAff_, _methodInfos, _page);
    }

    private static void filterByReturnType(String _stCall,String _typeAff, CustList<CustList<MethodInfo>> _methodInfos, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (isUndefined(_typeAff, keyWordVar_)) {
            return;
        }
        int len_ = _methodInfos.size();
        Mapping mapping_ = new Mapping();
        mapping_.setParam(_typeAff);
        StringMap<StringList> currVars_ = _page.getCurrentConstraints().getCurrentConstraints();
        mapping_.setMapping(currVars_);
        for (int i = 0; i < len_; i++) {
            int gr_ = _methodInfos.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = _methodInfos.get(i).get(j);
                boolean filter_ = true;
                if (methodInfo_.getConstraints().getKind() == MethodAccessKind.STATIC_CALL) {
                    CustList<Matching> cts_ = AnaTemplates.tryInferMethodByOneArgList(methodInfo_.getClassName(), -1, methodInfo_.getConstraints(),
                            _stCall,
                            _page.getCurrentConstraints().getCurrentConstraints(),
                            new AnaClassArgumentMatching(""), methodInfo_.getOriginalReturnType(), _typeAff, _page);
                    String infer_ = AnaTemplates.tryInferMethodByOneArg(cts_,
                            _stCall,
                            _page.getCurrentConstraints().getCurrentConstraints(),
                            _page);
                    tryReformat(_page, methodInfo_, infer_);
                    filter_ = _stCall.isEmpty()
                            ||
                            !infer_.isEmpty();
                }
                if (filter_) {
                    String returnType_ = methodInfo_.getReturnType();
                    mapping_.setArg(returnType_);
                    if (methodInfo_.getConstraints().isRetRef()) {
                        if (!StringUtil.quickEq(returnType_,_typeAff)) {
                            continue;
                        }
                    } else {
                        if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                            ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_typeAff, new AnaClassArgumentMatching(returnType_), _page);
                            if (!res_.isFoundMethod()) {
                                continue;
                            }
                        }
                    }
                }
                newList_.add(methodInfo_);
            }
            _methodInfos.set(i, newList_);
        }
    }

    protected static ClassMethodId getTrueFalse(ClassMethodId _feedBase, AnalyzedPageEl _page) {
        ClassMethodId f_ = _feedBase;
        if (f_ != null) {
            MethodId constraints_ = f_.getConstraints();
            String name_ = constraints_.getName();
            String className_ = f_.getClassName();
            StringList parametersTypes_ = constraints_.getParametersTypes();
            parametersTypes_.add(0, _page.getAliasPrimBoolean());
            f_ = new ClassMethodId(className_,new MethodId(MethodAccessKind.STATIC,name_,parametersTypes_));
        }
        return f_;
    }
    public static boolean isTrueFalseKeyWord(String _trimMeth, AnalyzedPageEl _page) {
        return StringUtil.quickEq(_trimMeth, _page.getKeyWords().getKeyWordTrue())
                || StringUtil.quickEq(_trimMeth, _page.getKeyWords().getKeyWordFalse());
    }
    protected static boolean isNotCorrectDim(String _cp) {
        return _cp == null||_cp.startsWith("[");
    }

    protected static boolean isUndefined(String _typeAff, String _keyWordVar) {
        return _typeAff.isEmpty() || StringUtil.quickEq(_typeAff, _keyWordVar);
    }

    static StringList getBounds(String _cl, AnalyzedPageEl _page) {
        String objectClassName_ = _page.getAliasObject();
        StringList bounds_ = new StringList();
        if (_cl.startsWith(AnaInherits.PREFIX_VAR_TYPE)) {
            AnaGeneType gl_ = _page.getGlobalType();
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

    static void unwrapArgsFct(Identifiable _id, int _natvararg, String _lasttype, CustList<OperationNode> _args, AnalyzedPageEl _page) {
        if (_natvararg > -1) {
            int lenCh_ = _args.size();
            for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
                OperationNode a_ = _args.get(i);
                if (i >= _natvararg) {
                    if (AnaTypeUtil.isPrimitive(_lasttype, _page)) {
                        a_.getResultClass().setUnwrapObject(_lasttype, _page.getPrimitiveTypes());
                    }
                } else {
                    String param_ = _id.getParametersTypes().get(i);
                    if (AnaTypeUtil.isPrimitive(param_, _page)) {
                        a_.getResultClass().setUnwrapObject(param_, _page.getPrimitiveTypes());
                    }
                }
            }
        } else {
            int lenCh_ = _args.size();
            for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
                OperationNode a_ = _args.get(i);
                String param_ = _id.getParametersTypes().get(i);
                if (i + 1 == lenCh_ && _id.isVararg()) {
                    param_ = StringExpUtil.getPrettyArrayType(param_);
                }
                if (AnaTypeUtil.isPrimitive(param_, _page)) {
                    a_.getResultClass().setUnwrapObject(param_, _page.getPrimitiveTypes());
                }
            }
        }
    }
    final int lookOnlyForVarArg() {
        OperationNode first_ = getFirstChild();
        int from_ = 1;
        if (first_ == null) {
            return -1;
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
        if (!(first_ instanceof IdFctOperation)) {
            return null;
        }
        return ((IdFctOperation)first_).getMethod();
    }

    protected void buildErrNotFoundStd(MethodAccessKind _staticContext, String _name, NameParametersFilter _filter, AnalyzedPageEl _page) {
        StringList classesNames_ = new StringList();
        for (OperationNode c: _filter.getPositional()) {
            classesNames_.add(StringUtil.join(c.getResultClass().getNames(), ExportCst.JOIN_TYPES));
        }
        for (NamedArgumentOperation c: _filter.getParameterFilter()) {
            classesNames_.add(StringUtil.join(c.getResultClass().getNames(), ExportCst.JOIN_TYPES));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //_name len
        undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(_staticContext, _name, classesNames_).getSignature(_page));
        _page.getLocalizer().addError(undefined_);
        addErr(undefined_.getBuiltError());
    }

    protected void buildErrNotFoundTrueFalse(MethodAccessKind _staticContext, String _name, AnalyzedPageEl _page, AnaClassArgumentMatching[] _argsClass) {
        StringList classesNames_ = new StringList();
        for (AnaClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringUtil.join(c.getNames(), ExportCst.JOIN_TYPES));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //_name len
        undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(_staticContext, _name, classesNames_).getSignature(_page));
        _page.getLocalizer().addError(undefined_);
        addErr(undefined_.getBuiltError());
    }

    protected void buildCtorError(NameParametersFilter _filter, AnalyzedPageEl _page, String _clCurName) {
        StringList classesNames_ = new StringList();
        for (OperationNode c: _filter.getPositional()) {
            classesNames_.add(StringUtil.join(c.getResultClass().getNames(), ExportCst.JOIN_TYPES));
        }
        for (NamedArgumentOperation c: _filter.getParameterFilter()) {
            classesNames_.add(StringUtil.join(c.getResultClass().getNames(), ExportCst.JOIN_TYPES));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
        undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //key word len
        undefined_.buildError(_page.getAnalysisMessages().getUndefinedCtor(),
                new ConstructorId(_clCurName, classesNames_, false).getSignature(_page));
        _page.getLocalizer().addError(undefined_);
        addErr(undefined_.getBuiltError());
    }
    @Override
    public final void setIntermediateDotted() {
        intermediate = true;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    public final AnaClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        previousResultClass = _previousResultClass;
        staticAccess = _staticAccess;
    }

    public final MethodAccessKind isStaticAccess() {
        return staticAccess;
    }

    public final void setStaticAccess(MethodAccessKind _staticAccess) {
        staticAccess = _staticAccess;
    }

}
