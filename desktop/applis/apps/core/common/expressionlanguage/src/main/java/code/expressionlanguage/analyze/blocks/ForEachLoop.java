package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecForEachLoop;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.IterableAnalysisResult;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class ForEachLoop extends BracedBlock implements ForLoop,ImportForEachLoop {

    private String label;
    private int labelOffset;

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private final String classIndexName;
    private String importedClassIndexName;
    private int classIndexNameOffset;

    private final String variableName;

    private int variableNameOffset;

    private final String expression;

    private int expressionOffset;

    private Argument argument;
    private OperationNode root;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public ForEachLoop(ContextEl _importingPage,
                       OffsetStringInfo _className, OffsetStringInfo _variable,
                       OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
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

    private MethodAccessKind processVarTypes(ContextEl _cont) {
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont,classIndexName);
        if (!PrimitiveTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_cont.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _cont.addError(cast_);
        }
        if (_cont.getAnalyzing().containsVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (!ContextUtil.isValidSingleToken(_cont,variableName)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffset);
            //variable name len
            b_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(b_);
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (!StringList.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,className);
            partOffsets.addAllElts(_cont.getCoverage().getCurrentParts());
        } else {
            importedClassName = "";
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        _cont.getCoverage().putBlockOperationsLoops(_cont,this);
        return static_;
    }

    public void inferArrayClass(ContextEl _cont, ClassArgumentMatching _elt) {
        ClassArgumentMatching compo_ = StringExpUtil.getQuickComponentType(_elt);
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_) && compo_.getNames().onlyOneElt()) {
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
                cast_.buildError(_cont.getAnalysisMessages().getUnknownType(),
                        className.trim());
                _cont.addError(cast_);
            } else {
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(compo_.getNames(),"&"),
                            importedClassName);
                    _cont.addError(cast_);
                }
            }
        }
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        MethodAccessKind static_ = processVarTypes(_cont);
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(static_));
        root = _cont.getCoverage().getCurrentRoot();
        ExecOperationNode l_ = op_.last();
        argument = l_.getArgument();
        checkMatchs(_cont, l_.getResultClass());
        processVariable(_cont);
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecForEachLoop exec_ = new ExecForEachLoop(getOffset(),label, importedClassName,
                importedClassIndexName,variableName,variableNameOffset, expressionOffset,op_);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

    private void checkMatchs(ContextEl _cont, ClassArgumentMatching _elt) {
        if (Argument.isNullValue(argument)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_cont.getCurrentFileName());
            static_.setIndexFile(_cont.getCurrentLocationIndex());
            //separator char
            static_.buildError(_cont.getAnalysisMessages().getNullValue(),
                    _cont.getStandards().getAliasNullPe());
            _cont.addError(static_);
        } else {
            if (_elt.isArray()) {
                inferArrayClass(_cont, _elt);
            } else {
                StringList names_ = _elt.getNames();
                StringList out_ = getInferredIterable(names_, _cont);
                checkIterableCandidates(out_, _cont);
            }
        }
    }

    public StringList getInferredIterable(StringList _types, ContextEl _cont) {
        IterableAnalysisResult it_ = _cont.getStandards().getCustomType(_types,"", _cont);
        return it_.getClassName();
    }

    public void checkIterableCandidates(StringList _types,ContextEl _cont) {
        if (_types.onlyOneElt()) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = StringExpUtil.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassName);
                    _cont.addError(cast_);
                }
            }
        } else {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            //separator char
            cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                    _cont.getStandards().getAliasObject(),
                    _cont.getStandards().getAliasIterable());
            _cont.addError(cast_);
        }
    }

    private void processVariable(ContextEl _cont) {
        AnaLoopVariable lv_ = new AnaLoopVariable();
        if (!importedClassName.isEmpty()) {
            lv_.setClassName(importedClassName);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    public OperationNode getRoot() {
        return root;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
