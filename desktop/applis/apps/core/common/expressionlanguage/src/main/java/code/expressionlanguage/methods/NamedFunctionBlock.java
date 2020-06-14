package code.expressionlanguage.methods;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public abstract class NamedFunctionBlock extends MemberCallingsBlock implements Returnable,AnnotableBlock {
    private StringList annotations = new StringList();
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Ints annotationsIndexes = new Ints();

    private final String name;

    private int nameOffset;

    private final StringList parametersTypes;

    private Ints parametersTypesOffset;

    private final String returnType;

    private String importedReturnType;

    private final StringList importedParametersTypes;

    private int returnTypeOffset;

    private final StringList parametersNames;

    private Ints parametersNamesOffset;

    private final AccessEnum access;

    private int accessOffset;

    private final boolean varargs;
    private CustList<StringList> annotationsParams = new CustList<StringList>();
    private CustList<Ints> annotationsIndexesParams = new CustList<Ints>();
    private CustList<CustList<CustList<ExecOperationNode>>> annotationsOpsParams = new CustList<CustList<CustList<ExecOperationNode>>>();

    private CustList<CustList<PartOffset>> partOffsetsParams = new CustList<CustList<PartOffset>>();
    private CustList<PartOffset> partOffsetsReturn = new CustList<PartOffset>();

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
        int i_ = CustList.FIRST_INDEX;
        int len_ = _paramTypes.size();
        boolean varargs_ = false;
        while (i_ < len_) {
            String className_ = _paramTypes.get(i_);
            if (i_+1 == len_) {
                varargs_ = className_.endsWith(VARARG);
                if (varargs_) {
                    parametersTypes.add(className_.substring(CustList.FIRST_INDEX, className_.length()-VARARG.length()));
                } else {
                    parametersTypes.add(className_);
                }
            } else {
                parametersTypes.add(className_);
            }
            i_++;
        }
        varargs = varargs_;
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        returnType = _retType.getInfo();
        returnTypeOffset = _retType.getOffset();
        parametersNames = new StringList();
        i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            parametersNames.add(_paramNames.get(i_));
            i_++;
        }
        parametersTypesOffset = _paramTypesOffset;
        parametersNamesOffset = _paramNamesOffset;
    }


    @Override
    public void buildAnnotations(ContextEl _context) {
        buildAnnotationsBasic(_context);
        annotationsOpsParams = new CustList<CustList<CustList<ExecOperationNode>>>();
        int j_ = 0;
        for (Ints l: annotationsIndexesParams) {
            CustList<CustList<ExecOperationNode>> annotation_;
            annotation_ = new CustList<CustList<ExecOperationNode>>();
            int len_ = l.size();
            AnalyzedPageEl page_ = _context.getAnalyzing();
            StringList list_ = annotationsParams.get(j_);
            for (int i = 0; i < len_; i++) {
                int begin_ = l.get(i);
                page_.setGlobalOffset(begin_);
                page_.setOffset(0);
                Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
                annotation_.add(ElUtil.getAnalyzedOperationsReadOnly(list_.get(i), _context, c_));
            }
            annotationsOpsParams.add(annotation_);
            j_++;
        }
    }
    protected void buildAnnotationsReport(int _index,ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = annotationsIndexesParams.get(_index).size();
        StringList list_ = annotationsParams.get(_index);
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexesParams.get(_index).get(i);
            int end_ = begin_ + list_.get(i).length();
            ElUtil.buildCoverageReport(_cont,begin_,this,annotationsOpsParams.get(_index).get(i),end_,_parts,0,"",true);
        }
    }

    @Override
    public void reduce(ContextEl _context) {
        reduceBasic(_context);
        CustList<CustList<CustList<ExecOperationNode>>> annotationsOpsParams_;
        annotationsOpsParams_ = new CustList<CustList<CustList<ExecOperationNode>>>();
        for (CustList<CustList<ExecOperationNode>> l: annotationsOpsParams) {
            CustList<CustList<ExecOperationNode>> l_;
            l_ = new CustList<CustList<ExecOperationNode>>();
            for (CustList<ExecOperationNode> k: l) {
                ExecOperationNode o_ = k.last();
                l_.add(ElUtil.getReducedNodes(o_));
            }
            annotationsOpsParams_.add(l_);
        }
        annotationsOpsParams = annotationsOpsParams_;
    }

    @Override
    public void setAssignmentAfterCallReadOnly(ContextEl _an, AnalyzingEl _anEl) {
        checkReturnFct(_an, _anEl);
    }

    private void checkReturnFct(ContextEl _an, AnalyzingEl _anEl) {
        LgNames stds_ = _an.getStandards();
        if (!StringList.quickEq(getImportedReturnType(), stds_.getAliasVoid())) {
            if (_anEl.canCompleteNormally(this)) {
                //error
                FoundErrorInterpret miss_ = new FoundErrorInterpret();
                miss_.setIndexFile(getOffset().getOffsetTrim());
                miss_.setFileName(getFile().getFileName());
                //return type len
                miss_.buildError(_an.getAnalysisMessages().getMissingAbrupt(),
                        _an.getKeyWords().getKeyWordThrow(),
                        _an.getKeyWords().getKeyWordReturn(),
                        getPseudoSignature(_an));
                _an.addError(miss_);
            }
        }
    }

    public CustList<CustList<CustList<ExecOperationNode>>> getAnnotationsOpsParams() {
        return annotationsOpsParams;
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

    @Override
    public String getName() {
        return name;
    }

    public final StringList getParametersTypes() {
        return new StringList(parametersTypes);
    }

    public final void buildImportedTypes(ContextEl _stds) {
        StringList params_ = new StringList();
        int i_ = 0;
        AnalyzedPageEl page_ = _stds.getAnalyzing();
        page_.setCurrentBlock(this);
        page_.setCurrentAnaBlock(this);
        page_.setCurrentFct(this);
        for (String p: parametersTypes) {
            CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
            page_.setGlobalOffset(parametersTypesOffset.get(i_));
            page_.setOffset(0);
            params_.add(ResolvingImportTypes.resolveCorrectType(_stds,p));
            partOffsets_.addAllElts(_stds.getCoverage().getCurrentParts());
            partOffsetsParams.add(partOffsets_);
            i_++;
        }
        importedParametersTypes.clear();
        importedParametersTypes.addAllElts(params_);
        buildImportedReturnTypes(_stds);
    }

    public void buildImportedReturnTypes(ContextEl _stds) {
        String void_ = _stds.getStandards().getAliasVoid();
        if (StringList.quickEq(returnType.trim(), void_)) {
            importedReturnType = void_;
            return;
        }
        AnalyzedPageEl page_ = _stds.getAnalyzing();
        page_.setCurrentBlock(this);
        page_.setCurrentAnaBlock(this);
        page_.setGlobalOffset(returnTypeOffset);
        page_.setOffset(0);
        importedReturnType = ResolvingImportTypes.resolveCorrectType(_stds,returnType);
        partOffsetsReturn.addAllElts(_stds.getCoverage().getCurrentParts());
    }
    public String getReturnType() {
        return returnType;
    }

    @Override
    public final StringList getParametersNames() {
        return new StringList(parametersNames);
    }

    @Override
    public final boolean isVarargs() {
        return varargs;
    }

    public final AccessEnum getAccess() {
        return access;
    }

    public StringList getImportedParametersTypes() {
        return importedParametersTypes;
    }

    @Override
    public String getImportedReturnType() {
        return importedReturnType;
    }

    public void buildAnnotationsBasic(ContextEl _context) {
        annotationsOps = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            page_.setGlobalOffset(begin_);
            page_.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            annotationsOps.add(ElUtil.getAnalyzedOperationsReadOnly(annotations.get(i), _context, c_));
        }
    }

    protected void buildAnnotationsReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = annotationsIndexes.size();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            int end_ = begin_ + annotations.get(i).length();
            ElUtil.buildCoverageReport(_cont,begin_,this,annotationsOps.get(i),end_,_parts,0,"",true);
        }
    }

    public void reduceBasic(ContextEl _context) {
        CustList<CustList<ExecOperationNode>> annotationsOps_;
        annotationsOps_ = new CustList<CustList<ExecOperationNode>>();
        for (CustList<ExecOperationNode> a: annotationsOps) {
            ExecOperationNode r_ = a.last();
            annotationsOps_.add(ElUtil.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
    }
    @Override
    public StringList getAnnotations() {
        return annotations;
    }
    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }
    @Override
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
}
