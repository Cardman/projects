package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.*;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.util.*;
import code.util.core.StringUtil;

public final class ForEachLoop extends AbstractForLoop implements Loop,ImportForEachLoop {

    private final String label;
    private final int labelOffset;

    private final String className;

    private String importedClassName;

    private final int classNameOffset;

    private final String classIndexName;
    private String importedClassIndexName;
    private final int classIndexNameOffset;

    private final String variableName;

    private final int variableNameOffset;

    private final String expression;

    private final int sepOffset;
    private final int expressionOffset;

    private final ResultExpression res = new ResultExpression();

    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    private final StringList nameErrors = new StringList();
    private final StringList sepErrors = new StringList();
    private boolean okVar = true;
    private final boolean refVariable;

    public ForEachLoop(OffsetStringInfo _className, OffsetStringInfo _variable,
                       OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, int _sepOffset, AnalyzedPageEl _page, boolean _refVariable) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _page.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
        sepOffset = _sepOffset;
        refVariable = _refVariable;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public int getClassIndexNameOffset() {
        return classIndexNameOffset;
    }

    public int getVariableNameOffset() {
        return variableNameOffset;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public String getClassIndexName() {
        return classIndexName;
    }

    public String getClassName() {
        return className;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getExpression() {
        return expression;
    }


    @Override
    public String getImportedClassName() {
        return importedClassName;
    }

    private MethodAccessKind processVarTypes(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        importedClassIndexName = ResolvingTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_page.getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _page.addLocError(cast_);
            addErrorBlock(cast_.getBuiltError());
        }
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffset);
            //variable name len
            b_.setBuiltError(res_.getMessage());
            _page.addLocError(b_);
            nameErrors.add(b_.getBuiltError());
            okVar = false;
        }
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (!StringUtil.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = ResolvingTypes.resolveCorrectType(className, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
        } else {
            importedClassName = "";
        }
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        return f_.getStaticContext();
    }

    public void inferArrayClass(AnaClassArgumentMatching _elt, AnalyzedPageEl _page) {
        AnaClassArgumentMatching compo_ = AnaTypeUtil.getQuickComponentType(_elt);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(className.trim(), keyWordVar_) && compo_.getNames().onlyOneElt()) {
            importedClassName = compo_.getName();
        } else {
            Mapping mapping_ = new Mapping();
            if (importedClassName.isEmpty()) {
                mapping_.setArg("");
                mapping_.setParam("");
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(getFile().getFileName());
                cast_.setIndexFile(expressionOffset);
                //separator char
                cast_.buildError(_page.getAnalysisMessages().getUnknownType(),
                        className.trim());
                _page.addLocError(cast_);
                sepErrors.add(cast_.getBuiltError());
            } else {
                if (refVariable) {
                    if (!compo_.matchClass(importedClassName)) {
                        FoundErrorInterpret cast_ = new FoundErrorInterpret();
                        cast_.setFileName(getFile().getFileName());
                        cast_.setIndexFile(expressionOffset);
                        //separator char
                        cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                                StringUtil.join(compo_.getNames(),"&"),
                                importedClassName);
                        _page.addLocError(cast_);
                        sepErrors.add(cast_.getBuiltError());
                    }
                } else {
                    mapping_.setArg(compo_);
                    mapping_.setParam(importedClassName);
                    StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                    mapping_.setMapping(vars_);
                    if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                        FoundErrorInterpret cast_ = new FoundErrorInterpret();
                        cast_.setFileName(getFile().getFileName());
                        cast_.setIndexFile(expressionOffset);
                        //separator char
                        cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                                StringUtil.join(compo_.getNames(),"&"),
                                importedClassName);
                        _page.addLocError(cast_);
                        sepErrors.add(cast_.getBuiltError());
                    }
                }
            }
        }
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MethodAccessKind static_ = processVarTypes(_page);
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, expression, Calculation.staticCalculation(static_), _page));
//        root = _page.getCurrentRoot();
//        ExecOperationNode l_ = op_.last();
//        argument = l_.getArgument();
        checkMatchs(res.getRoot().getResultClass(), _page);
        if (okVar) {
            processVariable(_page);
        }
//        ExecForEachLoop exec_ = new ExecForEachLoop(getOffset(),label, importedClassName,
//                importedClassIndexName,variableName,variableNameOffset, expressionOffset,op_);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

    private void checkMatchs(AnaClassArgumentMatching _elt, AnalyzedPageEl _page) {
//        if (Argument.isNullValue(argument)) {
//            FoundErrorInterpret static_ = new FoundErrorInterpret();
//            static_.setFileName(_page.getCurrentBlock().getFile().getFileName());
//            static_.setIndexFile(_page.getTraceIndex());
//            //separator char
//            static_.buildError(_page.getAnalysisMessages().getNullValue(),
//                    _page.getStandards().getAliasNullPe());
//            _page.addLocError(static_);
//            sepErrors.add(static_.getBuiltError());
//        } else {
            if (_elt.isArray()) {
                inferArrayClass(_elt, _page);
            } else {
                StringList names_ = _elt.getNames();
                StringList out_ = getInferredIterable(names_, _page);
                checkIterableCandidates(out_, _page);
            }
//        }
    }

    public StringList getInferredIterable(StringList _types, AnalyzedPageEl _page) {
        IterableAnalysisResult it_ = _page.getForEachFetch().getCustomType(_types,"");
        return it_.getClassName();
    }

    public void checkIterableCandidates(StringList _types, AnalyzedPageEl _page) {
        if (!refVariable&&_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringUtil.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _page.getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _page.getAliasObject();
            }
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringUtil.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    _page.addLocError(cast_);
                    sepErrors.add(cast_.getBuiltError());
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            //separator char
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _page.getAliasObject(),
                    _page.getAliasIterable());
            _page.addLocError(cast_);
            sepErrors.add(cast_.getBuiltError());
        }
    }

    private void processVariable(AnalyzedPageEl _page) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        lv_.setRef(variableNameOffset);
        lv_.setIndexClassName(importedClassIndexName);
        _page.getLoopsVars().put(variableName, lv_);
        AnaLocalVariable lInfo_ = new AnaLocalVariable();
        if (!importedClassName.isEmpty()) {
            lInfo_.setClassName(importedClassName);
        } else {
            lInfo_.setClassName(_page.getAliasObject());
        }
        lInfo_.setRef(variableNameOffset);
        if (refVariable) {
            lInfo_.setConstType(ConstType.REF_LOC_VAR);
        } else {
            lInfo_.setConstType(ConstType.FIX_VAR);
        }
        _page.getInfosVars().put(variableName, lInfo_);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getLoopsVars().removeKey(variableName);
        _ip.getInfosVars().removeKey(variableName);
    }

    public ResultExpression getRes() {
        return res;
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public StringList getNameErrors() {
        return nameErrors;
    }

    public int getSepOffset() {
        return sepOffset;
    }

    public StringList getSepErrors() {
        return sepErrors;
    }

    public boolean isRefVariable() {
        return refVariable;
    }
}
