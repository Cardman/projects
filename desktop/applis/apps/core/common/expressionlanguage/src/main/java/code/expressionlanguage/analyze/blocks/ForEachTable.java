package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecForEachTable;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class ForEachTable extends BracedBlock implements Loop,ImportForEachTable {

    private String label;
    private int labelOffset;

    private final String classNameFirst;

    private String importedClassNameFirst;

    private int classNameOffsetFirst;

    private final String classNameSecond;

    private String importedClassNameSecond;

    private int classNameOffsetSecond;

    private final String classIndexName;
    private String importedClassIndexName;
    private int classIndexNameOffset;

    private final String variableNameFirst;

    private int variableNameOffsetFirst;

    private final String variableNameSecond;

    private int variableNameOffsetSecond;

    private final String expression;

    private int expressionOffset;

    private Argument argument;
    private OperationNode root;
    private CustList<PartOffset> partOffsetsFirst = new CustList<PartOffset>();

    private CustList<PartOffset> partOffsetsSecond = new CustList<PartOffset>();

    private final StringList nameErrorsFirst = new StringList();
    private final StringList nameErrorsSecond = new StringList();

    private int sepOffset;
    private final StringList sepErrors = new StringList();
    private boolean okVarFirst = true;
    private boolean okVarSecond = true;

    public ForEachTable(ContextEl _importingPage,
                        OffsetStringInfo _className, OffsetStringInfo _variable,
                        OffsetStringInfo _classNameSec, OffsetStringInfo _variableSec,
                        OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, int _sepOffset) {
        super(_offset);
        classNameFirst = _className.getInfo();
        classNameOffsetFirst = _className.getOffset();
        variableNameFirst = _variable.getInfo();
        variableNameOffsetFirst = _variable.getOffset();
        classNameSecond = _classNameSec.getInfo();
        classNameOffsetSecond = _classNameSec.getOffset();
        variableNameSecond = _variableSec.getInfo();
        variableNameOffsetSecond = _variableSec.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getAnalyzing().getStandards().getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
        sepOffset = _sepOffset;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public int getLabelOffset() {
        return labelOffset;
    }


    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        MethodAccessKind static_ = processVarTypes(_cont);
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(static_));
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        root = page_.getCurrentRoot();
        ExecOperationNode l_ = op_.last();
        argument = l_.getArgument();
        checkMatchs(_cont, l_.getResultClass());
        processVariables(_cont);
        ExecForEachTable exec_ = new ExecForEachTable(getOffset(),label, importedClassNameFirst,
                importedClassNameSecond,
                importedClassIndexName,variableNameFirst,
                variableNameSecond, expressionOffset,op_);
        exec_.setFile(page_.getBlockToWrite().getFile());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        page_.getCoverage().putBlockOperations(_cont, exec_,this);

    }

    private void checkMatchs(ContextEl _cont, ClassArgumentMatching _el) {
        if (Argument.isNullValue(argument)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_cont.getCurrentFileName());
            static_.setIndexFile(_cont.getCurrentLocationIndex());
            //separator char
            static_.buildError(_cont.getAnalysisMessages().getNullValue(),
                    _cont.getAnalyzing().getStandards().getAliasNullPe());
            _cont.addError(static_);
            sepErrors.add(static_.getBuiltError());
        } else {
            StringList names_ = _el.getNames();
            StringList out_ = getCustomType(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
    }
    private StringList getCustomType(StringList _names, ContextEl _context) {
        LgNames stds_ = _context.getAnalyzing().getStandards();
        return stds_.getCustomTableType(_names,_context,"","").getClassName();
    }

    public String getClassIndexName() {
        return classIndexName;
    }

    private MethodAccessKind processVarTypes(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        MemberCallingsBlock f_ = page_.getCurrentFct();
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont,classIndexName);
        if (!AnaTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(page_.getStandards().getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_cont.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _cont.addError(cast_);
            setReachableError(true);
            getErrorsBlock().add(cast_.getBuiltError());
        }
        TokenErrorMessage resOne_ = ManageTokens.partVar(_cont).checkTokenVar(_cont, variableNameFirst);
        if (resOne_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffsetFirst);
            //first variable name
            b_.setBuiltError(resOne_.getMessage());
            _cont.addError(b_);
            nameErrorsFirst.add(b_.getBuiltError());
            okVarFirst = false;
        }
        TokenErrorMessage resTwo_ = ManageTokens.partVar(_cont).checkTokenVar(_cont, variableNameSecond);
        if (resTwo_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffsetSecond);
            //second variable name
            b_.setBuiltError(resTwo_.getMessage());
            _cont.addError(b_);
            nameErrorsSecond.add(b_.getBuiltError());
            okVarSecond = false;
        }
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        page_.setGlobalOffset(classNameOffsetFirst);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
            importedClassNameFirst = ResolvingImportTypes.resolveCorrectType(_cont,classNameFirst);
            partOffsetsFirst.addAllElts(page_.getCurrentParts());
        } else {
            importedClassNameFirst = "";
        }
        page_.setGlobalOffset(classNameOffsetSecond);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
            importedClassNameSecond = ResolvingImportTypes.resolveCorrectType(_cont,classNameSecond);
            partOffsetsSecond.addAllElts(page_.getCurrentParts());
        } else {
            importedClassNameSecond = "";
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        page_.getCoverage().putBlockOperationsLoops(_cont,this);
        return static_;
    }

    public void checkIterableCandidates(StringList _types,ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (_types.onlyOneElt()) {
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).get(1);
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = page_.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = page_.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
                importedClassNameFirst = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameFirst);
                StringMap<StringList> vars_ = page_.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char before expression
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameFirst);
                    _cont.addError(cast_);
                    sepErrors.add(cast_.getBuiltError());
                }
            }
            mapping_ = new Mapping();
            paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = page_.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = page_.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
                importedClassNameSecond = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameSecond);
                StringMap<StringList> vars_ = page_.getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char before expression
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameSecond);
                    _cont.addError(cast_);
                    sepErrors.add(cast_.getBuiltError());
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            //separator char before expression
            cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                    page_.getStandards().getAliasObject(),
                    page_.getStandards().getAliasIterableTable());
            _cont.addError(cast_);
            sepErrors.add(cast_.getBuiltError());
        }
    }

    private void processVariables(ContextEl _cont) {
        if (okVarFirst && okVarSecond) {
            if (StringList.quickEq(variableNameFirst, variableNameSecond)) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(getFile().getFileName());
                d_.setIndexFile(variableNameOffsetSecond);
                //second variable name len
                d_.buildError(_cont.getAnalysisMessages().getDuplicatedVariableName(),
                        variableNameFirst);
                _cont.addError(d_);
                nameErrorsSecond.add(d_.getBuiltError());
                return;
            }
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (okVarFirst) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setRef(variableNameOffsetFirst);
            lv_.setIndexClassName(importedClassIndexName);
            page_.getLoopsVars().put(variableNameFirst, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameFirst.isEmpty()) {
                lInfo_.setClassName(importedClassNameFirst);
            } else {
                lInfo_.setClassName(page_.getStandards().getAliasObject());
            }
            lInfo_.setRef(variableNameOffsetFirst);
            lInfo_.setConstType(ConstType.FIX_VAR);
            page_.getInfosVars().put(variableNameFirst, lInfo_);
        }
        if (okVarSecond) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setRef(variableNameOffsetSecond);
            lv_.setIndexClassName(importedClassIndexName);
            page_.getLoopsVars().put(variableNameSecond, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            if (!importedClassNameSecond.isEmpty()) {
                lInfo_.setClassName(importedClassNameSecond);
            } else {
                lInfo_.setClassName(page_.getStandards().getAliasObject());
            }
            lInfo_.setRef(variableNameOffsetSecond);
            lInfo_.setConstType(ConstType.FIX_VAR);
            page_.getInfosVars().put(variableNameSecond, lInfo_);
        }
    }


    public String getLabel() {
        return label;
    }

    public String getClassNameFirst() {
        return classNameFirst;
    }

    public String getVariableNameFirst() {
        return variableNameFirst;
    }

    public String getClassNameSecond() {
        return classNameSecond;
    }

    public String getVariableNameSecond() {
        return variableNameSecond;
    }

    public int getClassNameOffsetFirst() {
        return classNameOffsetFirst;
    }

    public int getClassNameOffsetSecond() {
        return classNameOffsetSecond;
    }

    public int getVariableNameOffsetFirst() {
        return variableNameOffsetFirst;
    }

    public int getVariableNameOffsetSecond() {
        return variableNameOffsetSecond;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String getImportedClassNameFirst() {
        return importedClassNameFirst;
    }

    @Override
    public String getImportedClassNameSecond() {
        return importedClassNameSecond;
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getLoopsVars().removeKey(variableNameFirst);
        _ip.getLoopsVars().removeKey(variableNameSecond);
        _ip.getInfosVars().removeKey(variableNameFirst);
        _ip.getInfosVars().removeKey(variableNameSecond);
    }
    public OperationNode getRoot() {
        return root;
    }

    public CustList<PartOffset> getPartOffsetsFirst() {
        return partOffsetsFirst;
    }

    public CustList<PartOffset> getPartOffsetsSecond() {
        return partOffsetsSecond;
    }

    public StringList getNameErrorsFirst() {
        return nameErrorsFirst;
    }

    public StringList getNameErrorsSecond() {
        return nameErrorsSecond;
    }

    public int getSepOffset() {
        return sepOffset;
    }

    public StringList getSepErrors() {
        return sepErrors;
    }
}
