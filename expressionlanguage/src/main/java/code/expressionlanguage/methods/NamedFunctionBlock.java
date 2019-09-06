package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.MissingReturnMethod;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;
import code.util.StringList;

public abstract class NamedFunctionBlock extends MemberCallingsBlock implements Returnable {

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
        super.buildAnnotations(_context);
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
                Calculation c_ = Calculation.staticCalculation(true);
                annotation_.add(ElUtil.getAnalyzedOperations(list_.get(i), _context, c_));
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
        super.reduce(_context);
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
    public void setAssignmentAfterCall(Analyzable _an, AnalyzingEl _anEl) {
        if (!_an.getContextEl().getOptions().isReadOnly()) {
            setAssignmentAfter(_an,_anEl);
        }
        LgNames stds_ = _an.getStandards();
        if (!StringList.quickEq(getImportedReturnType(), stds_.getAliasVoid())) {
            if (_anEl.canCompleteNormally(this)) {
                //error
                MissingReturnMethod miss_ = new MissingReturnMethod();
                miss_.setIndexFile(getOffset().getOffsetTrim());
                miss_.setFileName(getFile().getFileName());
                miss_.setId(getPseudoSignature(_an));
                miss_.setReturning(getImportedReturnType());
                _an.getClasses().addError(miss_);
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

    public final void buildImportedTypes(Analyzable _stds) {
        StringList params_ = new StringList();
        int i_ = 0;
        _stds.getAnalyzing().setCurrentBlock(this);
        _stds.getAnalyzing().setCurrentFct(this);
        for (String p: parametersTypes) {
            CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
            _stds.getAnalyzing().setGlobalOffset(parametersTypesOffset.get(i_));
            _stds.getAnalyzing().setOffset(0);
            params_.add(_stds.resolveCorrectType(p));
            partOffsets_.addAllElts(_stds.getContextEl().getCoverage().getCurrentParts());
            partOffsetsParams.add(partOffsets_);
            i_++;
        }
        importedParametersTypes.clear();
        importedParametersTypes.addAllElts(params_);
        buildImportedReturnTypes(_stds);
    }

    public void buildImportedReturnTypes(Analyzable _stds) {
        String void_ = _stds.getStandards().getAliasVoid();
        if (StringList.quickEq(returnType.trim(), void_)) {
            importedReturnType = void_;
            return;
        }
        _stds.getAnalyzing().setCurrentBlock(this);
        _stds.getAnalyzing().setGlobalOffset(returnTypeOffset);
        _stds.getAnalyzing().setOffset(0);
        importedReturnType = _stds.resolveCorrectType(returnType);
        partOffsetsReturn.addAllElts(_stds.getContextEl().getCoverage().getCurrentParts());
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

    @Override
    public final AccessEnum getAccess() {
        return access;
    }
    @Override
    public void setAssignmentBeforeCall(Analyzable _an, AnalyzingEl _anEl) {
        AssignedVariables ass_;
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        ass_ = _an.getContextEl().getAssignedVariables().getFinalVariablesGlobal();
        id_.put(this, ass_);
    }

    public StringList getImportedParametersTypes() {
        return importedParametersTypes;
    }

    @Override
    public String getImportedReturnType() {
        return importedReturnType;
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
