package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.ParsedFctHeader;
import code.expressionlanguage.analyze.files.ParsedFctHeaderResult;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class NamedFunctionBlock extends MemberCallingsBlock implements AnnotableParametersBlock {
    private final StringList annotations = new StringList();

    private final Ints annotationsIndexes = new Ints();

    private String name;

    private final int nameOffset;

    private final StringList parametersTypes;

    private Ints parametersTypesOffset;

    private String returnType;

    private String importedReturnType = "";

    private final StringList importedParametersTypes;

    private int returnTypeOffset;

    private final StringList parametersNames;
    private final StringMap<AnaLocalVariable> usedParameters;

    private final CustList<BoolVal> parametersRef;
    private Ints parametersNamesOffset;

    private final AccessEnum access;

    private int accessOffset;

    private boolean varargs;
    private final CustList<StringList> annotationsParams = new CustList<StringList>();
    private final CustList<Ints> annotationsIndexesParams = new CustList<Ints>();

    private final CustList<AnaResultPartType> partOffsetsParams = new CustList<AnaResultPartType>();
    private AnaResultPartType partOffsetsReturn = new AnaResultPartType();

    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private final CustList<ResultExpression> resList = new CustList<ResultExpression>();
    private CustList<CustList<OperationNode>> rootsList = new CustList<CustList<OperationNode>>();
    private final CustList<CustList<ResultExpression>> resLists = new CustList<CustList<ResultExpression>>();

    private final StringList nameErrors = new StringList();
    private final CustList<StringList> paramErrors = new CustList<StringList>();
    private final CustList<StringList> paramWarns = new CustList<StringList>();
    private boolean retRef;
    private boolean usedRefMethod;

    protected NamedFunctionBlock(ParsedFctHeader _header, boolean _retRef, OffsetAccessInfo _access,
                              OffsetStringInfo _retType, OffsetStringInfo _fctName,
                              int _offset) {
        super(_offset);
        retRef = _retRef;
        importedParametersTypes = new StringList();
        name = _fctName.getInfo();
        nameOffset = _fctName.getOffset();
        parametersTypes = new StringList();
        parametersNames = new StringList();
        usedParameters = new StringMap<AnaLocalVariable>();
        parametersRef = new CustList<BoolVal>();
        varargs = setupParam(_header.getParametersType(),_header.getParametersName(), _header.getParametersRef());
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        returnType = _retType.getInfo();
        returnTypeOffset = _retType.getOffset();
        parametersTypesOffset = _header.getOffestsTypes();
        parametersNamesOffset = _header.getOffestsParams();
    }

    protected NamedFunctionBlock(int _fctName,
                              int _offset, AnalyzedPageEl _page) {
        super(_offset);
        retRef = false;
        importedParametersTypes = new StringList();
        name = _page.getKeyWords().getKeyWordLambda();
        nameOffset = _fctName;
        parametersTypes = new StringList();
        access = AccessEnum.PUBLIC;
        returnType = "";
        parametersNames = new StringList();
        usedParameters = new StringMap<AnaLocalVariable>();
        parametersRef = new CustList<BoolVal>();
    }

    public final boolean setupParam(StringList _paramTypes, StringList _paramNames, CustList<BoolVal> _refParams) {
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = _paramTypes.size();
        boolean varargs_ = false;
        while (i_ < len_) {
            String className_ = _paramTypes.get(i_);
            if (i_+1 == len_) {
                varargs_ = className_.endsWith(VARARG);
                if (varargs_) {
                    parametersTypes.add(className_.substring(IndexConstants.FIRST_INDEX, className_.length()-VARARG.length()));
                } else {
                    parametersTypes.add(className_);
                }
            } else {
                parametersTypes.add(className_);
            }
            parametersRef.add(_refParams.get(i_));
            i_++;
        }
        i_ = IndexConstants.FIRST_INDEX;
        while (i_ < len_) {
            parametersNames.add(_paramNames.get(i_));
            i_++;
        }
        return varargs_;
    }
    public void buildAnnotations(AnalyzedPageEl _page) {
        roots = new CustList<OperationNode>();
        int len_ = annotationsIndexes.size();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            _page.setGlobalOffset(begin_);
            _page.zeroOffset();
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(resList.get(i), annotations.get(i).trim(), c_, _page);
            ReachOperationUtil.tryCalculate(r_, _page);
            roots.add(r_);
        }
    }

    @Override
    public void buildAnnotationsParameters(AnalyzedPageEl _page) {
        int j_ = 0;
        rootsList = new CustList<CustList<OperationNode>>();
        for (Ints l: annotationsIndexesParams) {
            CustList<OperationNode> rootList_ = new CustList<OperationNode>();
            int len_ = l.size();
            StringList list_ = annotationsParams.get(j_);
            for (int i = 0; i < len_; i++) {
                int begin_ = l.get(i);
                _page.setGlobalOffset(begin_);
                _page.zeroOffset();
                Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
                OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(resLists.get(j_).get(i), list_.get(i).trim(), c_, _page);
                ReachOperationUtil.tryCalculate(r_, _page);
                rootList_.add(r_);
            }
            rootsList.add(rootList_);
            j_++;
        }
    }

    public Ints getParametersTypesOffset() {
        return parametersTypesOffset;
    }

    public Ints getParametersNamesOffset() {
        return parametersNamesOffset;
    }

    public int getNameOffset() {
        return nameOffset;
    }

    public int getAccessOffset() {
        return accessOffset;
    }

    public int getReturnTypeOffset() {
        return returnTypeOffset;
    }

    public String getName() {
        return name;
    }

    public void setIntenName(String _name) {
        this.name = StringUtil.concat(".",_name);
    }

    public final StringList getParametersTypes() {
        return new StringList(parametersTypes);
    }

    public void setVarargs(ParsedFctHeaderResult _header) {
        varargs = setupParam(_header.getParametersType(), _header.getParametersName(), _header.getParametersRef());
        parametersTypesOffset = _header.getOffestsTypes();
        parametersNamesOffset = _header.getOffestsParams();
        returnType = _header.getReturnType();
        retRef = _header.isRetRef();
        returnTypeOffset = _header.getReturnOffest();
    }

    public final void buildImportedTypes(AnalyzedPageEl _page) {
        _page.setCurrentBlock(this);
        _page.setCurrentFct(this);
        buildInternImportedTypes(_page);
    }

    public final void buildInternImportedTypes(AnalyzedPageEl _page) {
        StringList params_ = new StringList();
        int i_ = 0;
        for (String p: parametersTypes) {
            String res_ = buildInternParam(parametersTypesOffset.get(i_), p, _page);
            params_.add(res_);
            i_++;
        }
        importedParametersTypes.clear();
        importedParametersTypes.addAllElts(params_);
        buildImportedReturnTypes(_page);
    }
    public final String buildInternParam(int _offset, String _param, AnalyzedPageEl _page) {
        _page.setGlobalOffset(_offset);
        _page.zeroOffset();
        AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(_param, _page);
        String res_ = result_.getResult(_page);
        partOffsetsParams.add(result_);
        return res_;
    }

    public void buildImportedReturnTypes(AnalyzedPageEl _page) {
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(returnType.trim(), void_)) {
            importedReturnType = void_;
            return;
        }
        importedReturnType = buildInternRet(returnTypeOffset,returnType, _page);
    }
    public final String buildInternRet(int _offset, String _param, AnalyzedPageEl _page) {
        _page.setGlobalOffset(_offset);
        _page.zeroOffset();
        partOffsetsReturn = ResolvingTypes.resolveCorrectType(_param, _page);
        return partOffsetsReturn.getResult(_page);
    }

    void retRef(AnalyzedPageEl _page, MethodKind _kind) {
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(getImportedReturnType().trim(), void_) && isRetRef() && _kind != MethodKind.SET_INDEX) {
            int r_ = getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                    getSignature(_page),
                    _page.getAliasVoid());
            _page.addLocError(badMeth_);
            addNameErrors(badMeth_);
        }
    }
    public CustList<BoolVal> getParametersRef() {
        return parametersRef;
    }

    public abstract String getSignature(DisplayedStrings _page);
    public String getReturnType() {
        return returnType;
    }

    public final StringList getParametersNames() {
        return new StringList(parametersNames);
    }

    public StringMap<AnaLocalVariable> getUsedParameters() {
        return usedParameters;
    }

    public final boolean isVarargs() {
        return varargs;
    }

    public final AccessEnum getAccess() {
        return access;
    }

    public StringList getImportedParametersTypes() {
        return importedParametersTypes;
    }

    public String getImportedReturnType() {
        return importedReturnType;
    }

    public StringList getAnnotations() {
        return annotations;
    }

    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }
    public void setImportedReturnType(String _importedReturnType) {
        importedReturnType = _importedReturnType;
    }

    public CustList<StringList> getAnnotationsParams() {
        return annotationsParams;
    }

    public CustList<Ints> getAnnotationsIndexesParams() {
        return annotationsIndexesParams;
    }

    public CustList<AnaResultPartType> getPartOffsetsParams() {
        return partOffsetsParams;
    }

    public AnaResultPartType getPartOffsetsReturn() {
        return partOffsetsReturn;
    }

    public CustList<ResultExpression> getResList() {
        return resList;
    }

    public CustList<CustList<ResultExpression>> getResLists() {
        return resLists;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }

    public CustList<CustList<OperationNode>> getRootsList() {
        return rootsList;
    }

    public void addNameErrors(FoundErrorInterpret _error) {
        nameErrors.add(_error.getBuiltError());
    }

    public void addParamErrors() {
        paramErrors.add(new StringList());
    }
    public void addParamErrors(int _i,FoundErrorInterpret _error) {
        paramErrors.get(_i).add(_error.getBuiltError());
    }

    public void addParamWarns() {
        paramWarns.add(new StringList());
    }

    public StringList getNameErrors() {
        return nameErrors;
    }

    public CustList<StringList> getParamErrors() {
        return paramErrors;
    }

    public CustList<StringList> getParamWarns() {
        return paramWarns;
    }

    public boolean isRetRef() {
        return retRef;
    }

    public boolean isUsedRefMethod() {
        return usedRefMethod;
    }

    public void setUsedRefMethod(boolean _usedRefMethod) {
        this.usedRefMethod = _usedRefMethod;
    }
}
