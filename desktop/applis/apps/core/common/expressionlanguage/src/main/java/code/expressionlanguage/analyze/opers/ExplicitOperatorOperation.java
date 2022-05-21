package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvedIdType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class ExplicitOperatorOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,AbstractCallLeftOperation,SettableElResult {
    private final AnaCallFctContent callFctContent;
    private final AnaArrContent arrContent;

    private final int offsetOper;
    private String from;
    private AnaGeneType accType;

    private final ClassMethodIdMemberIdTypeFct conv = new ClassMethodIdMemberIdTypeFct();
    private AnaResultPartType partOffsets = new AnaResultPartType();
    private final CustList<AnaResultPartType> typesImpl = new CustList<AnaResultPartType>();
    private final CustList<AnaResultPartType> typesTest = new CustList<AnaResultPartType>();
    private String methodFound = EMPTY_STRING;
    private String syntheticOperator = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();
    private ClassMethodIdAncestor methodIdImpl;
    private ClassMethodIdAncestor methodIdTest;
    private StringList argsFirst = new StringList();
    private String opSearch = "";
    private boolean affect;
    private boolean post;
    private int affOffset;
    private int beginImpl;
    private int beginTest;
    private AnaTypeFct functionTest;
    private OperationNode foundChild;
    private int indexVar = -1;

    public ExplicitOperatorOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent(_op.getFctName());
        offsetOper = getOperators().firstKey();
        arrContent = new AnaArrContent();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        String cl_ = callFctContent.getMethodName();
        setStaticAccess(MethodAccessKind.STATIC_CALL);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        int firstRightPar_ = cl_.indexOf(PAR_RIGHT);
        int secondLeftPar_ = cl_.indexOf(PAR_LEFT, firstRightPar_);
        String clFirst_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, firstRightPar_);
        argsFirst = StringExpUtil.getAllSepCommaTypes(clFirst_);
        StringList args_ = argsFirst;
        from = "";
        String op_ = args_.first().trim();
        opSearch = op_;
        int next_ = StringExpUtil.nextPrintCharIs(firstRightPar_ + 1, cl_.length(), cl_, '=');
        if (next_ < 0) {
            next_ = StringExpUtil.nextPrintCharIs(firstRightPar_+1,cl_.length(),cl_,':');
            post = true;
        }
        affOffset = next_;
        affect = next_ >= 0;
        if (affect) {
            next_ = StringExpUtil.nextPrintChar(next_+1, cl_.length(), cl_);
        } else {
            affOffset = firstRightPar_;
            next_ = StringExpUtil.nextPrintChar(firstRightPar_ + 1, cl_.length(), cl_);
        }
        String nameTest_ = "";
        if (StringUtil.quickEq(op_, "&&&")) {
            opSearch = "&&";
        } else {
            if (StringUtil.quickEq(op_, "|||")) {
                opSearch = "||";
            }
        }
        if (StringUtil.quickEq(opSearch,"&&")) {
            nameTest_ = _page.getKeyWords().getKeyWordFalse();
        }
        if (StringUtil.quickEq(opSearch,"||")) {
            nameTest_ = _page.getKeyWords().getKeyWordTrue();
        }
        if (StringExpUtil.startsWithKeyWord(cl_,next_,_page.getKeyWords().getKeyWordCast())) {
            beginImpl = next_;
            int secondRightPar_ = cl_.indexOf(PAR_RIGHT, secondLeftPar_);
            implicitId(_page, cl_, secondLeftPar_, secondRightPar_);
            next_ = StringExpUtil.nextPrintChar(secondRightPar_+1, cl_.length(), cl_);
        }
        if (StringExpUtil.startsWithKeyWord(cl_,next_,_page.getKeyWords().getKeyWordExplicit())) {
            beginTest = next_;
            explicitId(_page, cl_, next_, nameTest_);
        }
        searchType(_page, args_);
        methodFound = op_;
        lookForMethodsInfos(_page);
    }

    private void lookForMethodsInfos(AnalyzedPageEl _page) {
        if (from.isEmpty()) {
            CustList<MethodInfo> ops_ = getOperators(isLvalue(), null, _page);
            methodInfos.add(ops_);
        } else {
            methodInfos = getDeclaredCustMethodByType(MethodAccessKind.STATIC_CALL, new StringList(from), opSearch, false, _page, new ScopeFilter(null, true, false, isLvalue(), _page.getGlobalClass()), getFormattedFilter(_page, this));
        }
        int len_ = methodInfos.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos.get(i).get(j);
                if (!StringUtil.quickEq(methodInfo_.getConstraints().getName(),opSearch)) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            methodInfos.set(i, newList_);
        }
        boolean apply_ = false;
        OperationNode curPar_ = getParent();
        if (curPar_ == null){
            apply_ = true;
        }
        filterByReturnType("",apply_,methodInfos, _page, getParentMatching(this));
    }

    private void searchType(AnalyzedPageEl _page, StringList _args) {
        if (_args.size() > 1) {
            int off_ = StringUtil.getFirstPrintableCharIndex(_args.get(1));
            String fromType_ = _args.get(1).trim();
            partOffsets = ResolvingTypes.resolveCorrectTypeAccessible(off_ + callFctContent.getMethodName().indexOf(',') + 1, fromType_, _page);
            from = partOffsets.getResult(_page);
            accType = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(from));
        }
    }

    private void explicitId(AnalyzedPageEl _page, String _cl, int _next, String _nameTest) {
        int thirdLeftPar_ = _cl.indexOf(PAR_LEFT, _next + _page.getKeyWords().getKeyWordExplicit().length());
        String clTest_ = _cl.substring(thirdLeftPar_+1, _cl.lastIndexOf(PAR_RIGHT));
        StringList argsTest_ = StringExpUtil.getAllSepCommaTypes(clTest_);
        int lenArgTest_ = argsTest_.size();
        if (lenArgTest_ == 2) {
            String firstFull_ = argsTest_.first();
            int off_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
            String fromType_ = firstFull_.trim();
            ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(off_ + thirdLeftPar_ + 1, fromType_, _page);
            String resName_ = resolvedIdType_.getFullName();
            typesTest.add(resolvedIdType_.getDels());
            if (!resName_.isEmpty()) {
                int offImpl_ = thirdLeftPar_+1;
                offImpl_ += argsTest_.first().length() + 1;
                String full_ = argsTest_.get(1);
                int loc_ = StringUtil.getFirstPrintableCharIndex(full_);
                String arg_ = full_.trim();
                String type_ = arg_;
                AnaResultPartType result_ = ResolvingTypes.resolveCorrectAccessibleType(offImpl_ + loc_, type_, resName_, _page);
                typesTest.add(result_);
                arg_ = result_.getResult(_page);
                StringList out_ = new StringList(_page.getAliasPrimBoolean(),arg_);
                CustList<BoolVal> ref_ = new CustList<BoolVal>(BoolVal.FALSE,BoolVal.FALSE);
                methodIdTest = new ClassMethodIdAncestor(resolvedIdType_.getGeneType(),new ClassMethodId(resName_,new MethodId(false, MethodAccessKind.STATIC, _nameTest, out_, ref_, false)),0);
            }
        } else {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFile(_page.getCurrentFile());
            badCall_.setIndexFile(_page);
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                    Long.toString(2),
                    Long.toString(lenArgTest_)
            );
            _page.getLocalizer().addError(badCall_);
            addErr(badCall_.getBuiltError());
        }
    }

    private void implicitId(AnalyzedPageEl _page, String _cl, int _secondLeftPar, int _secondRightPar) {
        String clImpl_ = _cl.substring(_secondLeftPar +1, _secondRightPar);
        StringList argsImpl_ = StringExpUtil.getAllSepCommaTypes(clImpl_);
        int lenArgImpl_ = argsImpl_.size();
        if (lenArgImpl_ == 3) {
            String firstFull_ = argsImpl_.first();
            int off_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
            String fromType_ = firstFull_.trim();
            ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(off_ + _secondLeftPar + 1, fromType_, _page);
            String resName_ = resolvedIdType_.getFullName();
            typesImpl.add(resolvedIdType_.getDels());
            if (!resName_.isEmpty()) {
                StringList out_ = new StringList();
                CustList<BoolVal> ref_ = new CustList<BoolVal>();
                int offImpl_ = _secondLeftPar +1;
                for (int i = 0; i < 1; i++) {
                    offImpl_ += argsImpl_.get(i).length() + 1;
                }
                for (int i = 1; i < lenArgImpl_; i++) {
                    String full_ = argsImpl_.get(i);
                    int loc_ = StringUtil.getFirstPrintableCharIndex(full_);
                    String arg_ = full_.trim();
                    String type_ = arg_;
                    AnaResultPartType result_ = ResolvingTypes.resolveCorrectAccessibleType(offImpl_ + loc_, type_, resName_, _page);
                    typesImpl.add(result_);
                    arg_ = result_.getResult(_page);
                    offImpl_ += argsImpl_.get(i).length() + 1;
                    out_.add(arg_);
                    ref_.add(BoolVal.FALSE);
                }
                methodIdImpl = new ClassMethodIdAncestor(resolvedIdType_.getGeneType(),new ClassMethodId(resName_,new MethodId(false, MethodAccessKind.STATIC, _page.getKeyWords().getKeyWordCast(), out_, ref_, false)),0);
            }
        } else {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFile(_page.getCurrentFile());
            badCall_.setIndexFile(_page);
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                    Long.toString(2),
                    Long.toString(lenArgImpl_)
            );
            _page.getLocalizer().addError(badCall_);
            addErr(badCall_.getBuiltError());
        }
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        if (argsFirst.size() > 2) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFile(_page.getCurrentFile());
            badCall_.setIndexFile(_page);
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                    Long.toString(2),
                    Long.toString(argsFirst.size())
            );
            _page.getLocalizer().addError(badCall_);
            addErr(badCall_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ClassMethodIdReturn found_ = findMethodWithName(_page,name_);
        if (found_ == null) {
            return;
        }
        CustList<OperationNode> all_ = name_.getAllOps();
        OperationNode next_ = operandTest(all_);
        if (affect) {
            if (next_ == null) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                //oper
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                        opSearch+"=");
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
                return;
            }
            SettableElResult settable_ = AffectationOperation.tryGetSettableArg(next_);
            if (!(settable_ instanceof OperationNode)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page);
                //oper
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                        opSearch+"=");
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
                return;
            }
            settable_.setVariable(false);
            checkSetter((OperationNode)settable_,_page);
            indexVar = next_.getIndexChild();
            CompoundAffectationOperation.checkFinal(this,_page,settable_);
            checkVariable(_page, settable_);
            tryGetTest(_page, next_);
            AnaClassArgumentMatching left_ = getSettableResClass(settable_);
            tryGetImplicit(_page, left_);
            return;
        }
        if ((StringUtil.quickEq(methodFound, "&&&") || StringUtil.quickEq(methodFound, "|||"))&&next_ != null) {
            tryGetTest(_page, next_);
            if (!syntheticOperator.isEmpty()) {
                tryGetImplicit(_page, next_.getResultClass());
            }
            return;
        }
        syntheticOperator = "";
    }

    private void checkVariable(AnalyzedPageEl _page, SettableElResult _settable) {
        if (_settable instanceof VariableOperationUse && ((VariableOperationUse) _settable).isFinalVariable()) {
            VariableOperationUse cst_ = (VariableOperationUse) _settable;
            setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //field name len
            un_.buildError(_page.getAnalysisMessages().getFinalField(),
                    cst_.getVariableName());
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
        }
    }

    private void tryGetImplicit(AnalyzedPageEl _page, AnaClassArgumentMatching _left) {
        if (methodIdImpl != null) {
            AnaClassArgumentMatching resultClass_ = getResultClass();
            CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>(_left);
            args_.add(resultClass_);
            AnaClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
            ClassMethodIdReturn resMethod_ = tryGetCast(methodIdImpl.getClassMethodId().getClassName(), methodIdImpl, argsClass_, _page, _page.getImplicitCastMethods(), _page.getImplicitIdCastMethods(), _page.getImplicitFromCastMethods());
            if (resMethod_ != null) {
                Mapping map_ = new Mapping();
                map_.setArg(resMethod_.getReturnType());
                map_.setParam(_left);
                if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(_page);
                    //oper len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            resMethod_.getReturnType(),
                            StringUtil.join(_left.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    addErr(cast_.getBuiltError());
                } else {
                    conv.infos(resMethod_);
                }
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(getResultClass().getNames(),ExportCst.JOIN_TYPES),
                        StringUtil.join(_left.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
            }
            setResultClass(AnaClassArgumentMatching.copy(_left, _page.getPrimitiveTypes()));
        } else {
            CompoundAffectationOperation.tryImplicit(this, _page, _left,conv);
        }
    }

    private void tryGetTest(AnalyzedPageEl _page, OperationNode _next) {
        ClassMethodIdReturn test_ = tryGetTest(_next,opSearch, _page, methodIdTest);
        if (test_ != null){
            foundChild = _next;
            _next.getResultClass().implicitInfosTest(test_);
            functionTest = test_.getPair();
        } else {
            syntheticOperator = "";
        }
    }

    private OperationNode operandTest(CustList<OperationNode> _all) {
        OperationNode next_ = null;
        for (OperationNode o: _all) {
            if (!(o instanceof NamedArgumentOperation) || ((NamedArgumentOperation) o).getIndex() == 0) {
                next_ = o;
                break;
            }
        }
        return next_;
    }

    static void checkSetter(OperationNode _op, AnalyzedPageEl _page) {
        if (!(_op instanceof ArrOperation)) {
            return;
        }
        ArrOperation arr_ = (ArrOperation) _op;
        if (arr_.isFromArray()) {
            return;
        }
        arr_.applySet(_page);
    }

    static AnaClassArgumentMatching getSettableResClass(SettableElResult _sett) {
        if (_sett instanceof ArrOperation&&!((ArrOperation)_sett).isFromArray()) {
            return new AnaClassArgumentMatching(((ArrOperation)_sett).getReturnSet());
        }
        return _sett.getResultClass();
    }
    private ClassMethodIdReturn findMethodWithName(AnalyzedPageEl _page, NameParametersFilter _name) {
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        ClassMethodIdAncestor feed_ = null;
        syntheticOperator = methodFound;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            MethodId mid_ = id_.getConstraints();
            MethodAccessKind static_;
            if (from.isEmpty()) {
                static_ = MethodId.getKind(MethodAccessKind.STATIC, mid_.getKind());
            } else {
                static_ = MethodId.getKind(MethodAccessKind.STATIC_CALL, mid_.getKind());
            }
            ClassMethodId classMethodId_ = new ClassMethodId(from, MethodId.to(static_, methodFound,mid_));
            feed_ = new ClassMethodIdAncestor(accType,classMethodId_,idMethod_.getAncestor());
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String varargParam_ = getVarargParam(chidren_);
        errIfWildCard(_page);
        ClassMethodIdReturn cust_ = tryFindOperator(_page, varargOnly_, varargParam_, feed_, _name);
        if (cust_ == null) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFile(_page.getCurrentFile());
            undefined_.setIndexFile(_page);
            //_name len
            StringList classesNames_ = new StringList();
            for (OperationNode c: _name.getAllOps()) {
                classesNames_.add(StringUtil.join(c.getResultClass().getNames(), ExportCst.JOIN_TYPES));
            }
            undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                    new MethodId(MethodAccessKind.STATIC, opSearch, classesNames_).getSignature(_page.getDisplayedStrings()));
            _page.getLocalizer().addError(undefined_);
            addErr(undefined_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return null;
        }
        setResultClass(new AnaClassArgumentMatching(cust_.getReturnType(), _page.getPrimitiveTypes()));
        callFctContent.update(cust_);
        return cust_;
    }

    private ClassMethodId possibleId(ClassMethodIdAncestor _idMethod) {
        ClassMethodId id_ = null;
        if (_idMethod != null) {
            id_ = _idMethod.getClassMethodId();
        }
        return id_;
    }

    private void errIfWildCard(AnalyzedPageEl _page) {
        if (StringExpUtil.isWildCard(from)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_page);
            badAccess_.setFile(_page.getCurrentFile());
            //type len
            badAccess_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    from);
            _page.getLocalizer().addError(badAccess_);
            addErr(badAccess_.getBuiltError());
        }
    }

    private ClassMethodIdReturn tryFindOperator(AnalyzedPageEl _page, int _varargOnly, String _varargParam, ClassMethodIdAncestor _id, NameParametersFilter _name) {
        ClassMethodIdReturn cust_;
        if (from.isEmpty()) {
            cust_ = getOperator(isLvalue(), possibleId(_id), _varargOnly, opSearch, _varargParam, _name, _page);
        } else {
            cust_ = tryGet(_varargOnly, opSearch, _varargParam, _name, _page, getDeclaredCustMethodByType(MethodAccessKind.STATIC_CALL, new StringList(from), opSearch, false, _name, _page, new ScopeFilter(_id, true, false, isLvalue(), _page.getGlobalClass())));
        }
        return cust_;
    }

    public OperationNode getFoundChild() {
        return foundChild;
    }

    public boolean isAffect() {
        return affect;
    }

    public int getOffsetOper() {
        return offsetOper;
    }

    public String getFrom() {
        return from;
    }

    public AnaGeneType getAccType() {
        return accType;
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    public CustList<AnaResultPartType> getTypesImpl() {
        return typesImpl;
    }

    public AnaTypeFct getFunctionTest() {
        return functionTest;
    }

    public ClassMethodIdAncestor getMethodIdImpl() {
        return methodIdImpl;
    }

    public ClassMethodIdAncestor getMethodIdTest() {
        return methodIdTest;
    }

    public String getSyntheticOperator() {
        return syntheticOperator;
    }

    public String getMethodFound() {
        return methodFound;
    }

    public CustList<CustList<MethodInfo>> getMethodInfos() {
        return methodInfos;
    }

    public boolean isPost() {
        return post;
    }

    public ClassMethodIdMemberIdTypeFct getConv() {
        return conv;
    }

    public AnaCallFctContent getCallFctContent() {
        return callFctContent;
    }

    public int getAffOffset() {
        return affOffset;
    }

    public int getBeginImpl() {
        return beginImpl;
    }

    public int getBeginTest() {
        return beginTest;
    }

    @Override
    public boolean isErrLeftValue() {
        return false;
    }

    @Override
    public AnaArrContent getArrContent() {
        return arrContent;
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

    public int getIndexVar() {
        return indexVar;
    }

    public CustList<AnaResultPartType> getTypesTest() {
        return typesTest;
    }
}
