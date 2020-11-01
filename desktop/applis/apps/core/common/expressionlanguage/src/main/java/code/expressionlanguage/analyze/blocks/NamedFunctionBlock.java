package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class NamedFunctionBlock extends MemberCallingsBlock implements AnnotableParametersBlock {
    private StringList annotations = new StringList();

    private Ints annotationsIndexes = new Ints();

    private String name;

    private int nameOffset;

    private final StringList parametersTypes;

    private Ints parametersTypesOffset;

    private String returnType;

    private String importedReturnType;

    private final StringList importedParametersTypes;

    private int returnTypeOffset;

    private final StringList parametersNames;

    private Ints parametersNamesOffset;

    private final AccessEnum access;

    private int accessOffset;

    private boolean varargs;
    private CustList<StringList> annotationsParams = new CustList<StringList>();
    private CustList<Ints> annotationsIndexesParams = new CustList<Ints>();

    private CustList<CustList<PartOffset>> partOffsetsParams = new CustList<CustList<PartOffset>>();
    private CustList<PartOffset> partOffsetsReturn = new CustList<PartOffset>();

    private CustList<OperationNode> roots = new CustList<OperationNode>();
    private CustList<CustList<OperationNode>> rootsList = new CustList<CustList<OperationNode>>();

    private final StringList nameErrors = new StringList();
    private final CustList<StringList> paramErrors = new CustList<StringList>();
    private int nameNumber;
    private boolean matchParamNames = true;

    public NamedFunctionBlock(OffsetAccessInfo _access,
                              OffsetStringInfo _retType, OffsetStringInfo _fctName,
                              StringList _paramTypes, Ints _paramTypesOffset,
                              StringList _paramNames, Ints _paramNamesOffset,
                              OffsetsBlock _offset) {
        super(_offset);
        importedParametersTypes = new StringList();
        name = _fctName.getInfo();
        nameOffset = _fctName.getOffset();
        parametersTypes = new StringList();
        parametersNames = new StringList();
        varargs = setupParam(_paramTypes,_paramNames);
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        returnType = _retType.getInfo();
        returnTypeOffset = _retType.getOffset();
        parametersTypesOffset = _paramTypesOffset;
        parametersNamesOffset = _paramNamesOffset;
    }

    public NamedFunctionBlock(int _fctName,
                              OffsetsBlock _offset, AnalyzedPageEl _page) {
        super(_offset);
        importedParametersTypes = new StringList();
        name = _page.getKeyWords().getKeyWordLambda();
        nameOffset = _fctName;
        parametersTypes = new StringList();
        access = AccessEnum.PUBLIC;
        returnType = "";
        parametersNames = new StringList();
    }

    public boolean setupParam(StringList _paramTypes,StringList _paramNames) {
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
            _page.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(annotations.get(i), c_, _page);
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
                _page.setOffset(0);
                Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
                OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(list_.get(i), c_, _page);
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

    public void setVarargs(StringList _paramTypes, Ints _paramTypesOffset,
                           StringList _paramNames, Ints _paramNamesOffset,
                           String _retType, int _retTypeOffset) {
        varargs = setupParam(_paramTypes,_paramNames);
        parametersTypesOffset = _paramTypesOffset;
        parametersNamesOffset = _paramNamesOffset;
        returnType = _retType;
        returnTypeOffset = _retTypeOffset;
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
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        _page.setGlobalOffset(_offset);
        _page.setOffset(0);
        String res_ = ResolvingImportTypes.resolveCorrectType(_param, _page);
        partOffsets_.addAllElts(_page.getCurrentParts());
        partOffsetsParams.add(partOffsets_);
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
        _page.setOffset(0);
        String res_ = ResolvingImportTypes.resolveCorrectType(_param, _page);
        partOffsetsReturn.addAllElts(_page.getCurrentParts());
        return res_;
    }
    public String getReturnType() {
        return returnType;
    }

    public final StringList getParametersNames() {
        return new StringList(parametersNames);
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

    public CustList<CustList<PartOffset>> getPartOffsetsParams() {
        return partOffsetsParams;
    }

    public CustList<PartOffset> getPartOffsetsReturn() {
        return partOffsetsReturn;
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
    public StringList getNameErrors() {
        return nameErrors;
    }

    public CustList<StringList> getParamErrors() {
        return paramErrors;
    }

    public int getNameNumber() {
        return nameNumber;
    }

    public void setNameNumber(int _nameNumber) {
        this.nameNumber = _nameNumber;
    }

    public boolean isMatchParamNames() {
        return matchParamNames;
    }

    public void setMatchParamNames(boolean _matchParamNames) {
        this.matchParamNames = _matchParamNames;
    }

}
