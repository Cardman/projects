package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.util.CustList;
import code.util.IdMap;
import code.util.Numbers;
import code.util.StringList;

public abstract class NamedFunctionBlock extends MemberCallingsBlock implements Returnable {

    private final String name;

    private int nameOffset;

    private final StringList parametersTypes;

    private Numbers<Integer> parametersTypesOffset;

    private final String returnType;

    private String importedReturnType;

    private final StringList importedParametersTypes;

    private int returnTypeOffset;

    private final StringList parametersNames;

    private Numbers<Integer> parametersNamesOffset;

    private final AccessEnum access;

    private int accessOffset;

    private final boolean varargs;
    private CustList<StringList> annotationsParams = new CustList<StringList>();
    private CustList<Numbers<Integer>> annotationsIndexesParams = new CustList<Numbers<Integer>>();
    private CustList<CustList<CustList<OperationNode>>> annotationsOpsParams = new CustList<CustList<CustList<OperationNode>>>();

    public NamedFunctionBlock(ContextEl _importingPage,
            BracedBlock _m,
            OffsetAccessInfo _access,
            OffsetStringInfo _retType, OffsetStringInfo _fctName,
            StringList _paramTypes, Numbers<Integer> _paramTypesOffset,
            StringList _paramNames, Numbers<Integer> _paramNamesOffset,
            OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
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
        annotationsOpsParams = new CustList<CustList<CustList<OperationNode>>>();
        for (StringList p: annotationsParams) {
            CustList<CustList<OperationNode>> annotation_;
            annotation_ = new CustList<CustList<OperationNode>>();
            for (String a:p) {
                Calculation c_ = Calculation.staticCalculation(true);
                annotation_.add(ElUtil.getAnalyzedOperations(a, _context, c_));
            }
            annotationsOpsParams.add(annotation_);
        }
    }
    @Override
    public void reduce(ContextEl _context) {
        super.reduce(_context);
        CustList<CustList<CustList<OperationNode>>> annotationsOpsParams_;
        annotationsOpsParams_ = new CustList<CustList<CustList<OperationNode>>>();
        for (CustList<CustList<OperationNode>> l: annotationsOpsParams) {
            CustList<CustList<OperationNode>> l_;
            l_ = new CustList<CustList<OperationNode>>();
            for (CustList<OperationNode> k: l) {
                OperationNode o_ = k.last();
                l_.add(ElUtil.getReducedNodes(o_));
            }
            annotationsOpsParams_.add(l_);
        }
        annotationsOpsParams = annotationsOpsParams_;
    }
    public CustList<CustList<CustList<OperationNode>>> getAnnotationsOpsParams() {
        return annotationsOpsParams;
    }
    public Numbers<Integer> getParametersTypesOffset() {
        return parametersTypesOffset;
    }

    public Numbers<Integer> getParametersNamesOffset() {
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
        int off_ = getOffset().getOffsetTrim();
        int i_ = 0;
        _stds.getAnalyzing().setCurrentBlock(this);
        _stds.getAnalyzing().setGlobalOffset(off_);
        for (String p: parametersTypes) {
            _stds.getAnalyzing().setOffset(parametersTypesOffset.get(i_));
            params_.add(_stds.resolveCorrectType(p));
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
        _stds.getAnalyzing().setOffset(returnTypeOffset);
        importedReturnType = _stds.resolveCorrectType(returnType);
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
    public void setAssignmentBefore(Analyzable _an, AnalyzingEl _anEl) {
        AssignedVariables ass_;
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        ass_ = _an.getAssignedVariables().getFinalVariablesGlobal();
        id_.put(this, ass_);
    }

    @Override
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

    public CustList<Numbers<Integer>> getAnnotationsIndexesParams() {
        return annotationsIndexesParams;
    }
}
