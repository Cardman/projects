package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;

public final class AnnotationMethodBlock extends NamedFunctionBlock implements
        GeneCustStaticMethod {

    private String defaultValue;
    private OperationNode root;
    private int defaultValueOffset;
    private int rightPar;
    private boolean ko;

    public AnnotationMethodBlock(OffsetStringInfo _retType, OffsetStringInfo _fctName,
                                 OffsetStringInfo _defaultValue,
                                 OffsetsBlock _offset, int _rightPar) {
        super(new OffsetAccessInfo(0, AccessEnum.PUBLIC),
                _retType, _fctName,
                new StringList(), new Ints(), new StringList(), new Ints(),
                _offset);
        defaultValue = _defaultValue.getInfo();
        defaultValueOffset = _defaultValue.getOffset();
        rightPar = _rightPar;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return getId().getSignature(_page);
    }

    @Override
    public MethodId getId() {
        return new MethodId(MethodAccessKind.INSTANCE, getName(), new StringList(), false);
    }

    @Override
    public void buildImportedReturnTypes(AnalyzedPageEl _page) {
        super.buildImportedReturnTypes(_page);
        LgNames stds_ = _page.getStandards();
        String string_ = stds_.getAliasString();
        String class_ = stds_.getAliasClassType();
        String itype_ = getImportedReturnType();
        String type_ = itype_;
        String ctype_ = StringExpUtil.getQuickComponentType(type_);
        if (ctype_ != null) {
            type_ = ctype_;
        }
        if (AnaTypeUtil.isPrimitiveOrWrapper(type_, _page)) {
            return;
        }
        RootBlock r_ = _page.getAnaClassBody(type_);
        if (r_ instanceof AnnotationBlock) {
            return;
        }
        if (r_ instanceof EnumBlock) {
            return;
        }
        if (StringList.quickEq(type_, string_)) {
            return;
        }
        if (StringList.quickEq(type_, class_)) {
            return;
        }
        FoundErrorInterpret cast_ = new FoundErrorInterpret();
        cast_.setFileName(_page.getLocalizer().getCurrentFileName());
        cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //return type len
        cast_.buildError(_page.getAnalysisMessages().getUnexpectedRetType(),
                itype_,getSignature(_page));
        _page.addLocError(cast_);
        addNameErrors(cast_);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public int getDefaultValueOffset() {
        return defaultValueOffset;
    }

    @Override
    public boolean isStaticMethod() {
        return false;
    }

    public boolean isFinalMethod() {
        return false;
    }

    public boolean isAbstractMethod() {
        return true;
    }

    public boolean isNormalMethod() {
        return false;
    }

    public void buildExpressionLanguage(AnalyzedPageEl _page) {
        if (defaultValue.trim().isEmpty()) {
            return;
        }
        _page.setGlobalOffset(defaultValueOffset);
        _page.setOffset(0);
        root = ElUtil.getRootAnalyzedOperationsReadOnly(defaultValue, Calculation.staticCalculation(MethodAccessKind.STATIC), _page);
        String import_ = getImportedReturnType();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnaClassArgumentMatching arg_ = root.getResultClass();
        mapping_.setArg(arg_);
        mapping_.setParam(import_);
        if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(defaultValueOffset);
            //parentheses
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(arg_.getNames(),"&"),
                    import_);
            _page.addLocError(cast_);
            addNameErrors(cast_);
        }
        if (AnaTypeUtil.isPrimitive(getImportedReturnType(), _page)) {
            root.getResultClass().setUnwrapObject(getImportedReturnType(), _page.getStandards());
        }
        ReachOperationUtil.tryCalculate(root, _page);
    }

    public void fwd(ExecAnnotationMethodBlock _exec, AnalyzedPageEl _page) {
        if (root == null) {
            _exec.setOpValue(new CustList<ExecOperationNode>());
            return;
        }
        _page.getCoverage().putBlockOperationsField(_page, this);
        CustList<ExecOperationNode> ops_ = ForwardInfos.getExecutableNodes(_page, root);
        _exec.setOpValue(ops_);
//        if (AnaTypeUtil.isPrimitive(getImportedReturnType(), _page)) {
//            ops_.last().getResultClass().setUnwrapObject(getImportedReturnType(), _page.getStandards());
//        }
    }


    public OperationNode getRoot() {
        return root;
    }

    public void setKo() {
        ko = true;
    }

    public boolean isKo() {
        return ko;
    }

    public int getRightPar() {
        return rightPar;
    }
}
