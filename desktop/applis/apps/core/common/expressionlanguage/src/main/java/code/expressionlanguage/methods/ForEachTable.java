package code.expressionlanguage.methods;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.exec.blocks.ExecForEachTable;
import code.expressionlanguage.errors.custom.*;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
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
    private CustList<PartOffset> partOffsetsFirst = new CustList<PartOffset>();

    private CustList<PartOffset> partOffsetsSecond = new CustList<PartOffset>();

    public ForEachTable(ContextEl _importingPage,
                        OffsetStringInfo _className, OffsetStringInfo _variable,
                        OffsetStringInfo _classNameSec, OffsetStringInfo _variableSec,
                        OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
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
            classIndex_ = _importingPage.getStandards().getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
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
        ExecOperationNode l_ = op_.last();
        argument = l_.getArgument();
        checkMatchs(_cont, l_.getResultClass());
        processVariables(_cont);
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecForEachTable exec_ = new ExecForEachTable(getOffset(),label,labelOffset,classNameFirst,importedClassNameFirst,classNameOffsetFirst,
                classNameSecond,importedClassNameSecond,classNameOffsetSecond,
                importedClassIndexName,variableNameFirst,variableNameOffsetFirst,
                variableNameSecond,variableNameOffsetSecond,expression,expressionOffset,op_, partOffsetsFirst, partOffsetsSecond);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);

    }

    private void checkMatchs(ContextEl _cont, ClassArgumentMatching _el) {
        if (Argument.isNullValue(argument)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_cont.getCurrentFileName());
            static_.setIndexFile(_cont.getCurrentLocationIndex());
            //separator char
            static_.buildError(_cont.getAnalysisMessages().getNullValue(),
                    _cont.getStandards().getAliasNullPe());
            _cont.addError(static_);
        } else {
            StringList names_ = _el.getNames();
            StringList out_ = getCustomType(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
    }
    private StringList getCustomType(StringList _names, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        return stds_.getCustomTableType(_names,_context,"","").getClassName();
    }

    public String getClassIndexName() {
        return classIndexName;
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
        if (_cont.getAnalyzing().containsVar(variableNameFirst)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetFirst);
            //first variable name
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameFirst);
            _cont.addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableNameFirst)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetFirst);
            //first variable name
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameFirst);
            _cont.addError(d_);
        }
        if (!_cont.isValidSingleToken(variableNameFirst)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffsetFirst);
            //first variable name
            b_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameFirst);
            _cont.addError(b_);
        }
        if (_cont.getAnalyzing().containsVar(variableNameSecond)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetSecond);
            //second variable name
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameSecond);
            _cont.addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableNameSecond)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetSecond);
            //second variable name
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameSecond);
            _cont.addError(d_);
        }
        if (!_cont.isValidSingleToken(variableNameSecond)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffsetSecond);
            //second variable name
            b_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameSecond);
            _cont.addError(b_);
        }
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffsetFirst);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
            importedClassNameFirst = ResolvingImportTypes.resolveCorrectType(_cont,classNameFirst);
            partOffsetsFirst.addAllElts(_cont.getCoverage().getCurrentParts());
        } else {
            importedClassNameFirst = "";
        }
        page_.setGlobalOffset(classNameOffsetSecond);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
            importedClassNameSecond = ResolvingImportTypes.resolveCorrectType(_cont,classNameSecond);
            partOffsetsSecond.addAllElts(_cont.getCoverage().getCurrentParts());
        } else {
            importedClassNameSecond = "";
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        _cont.getCoverage().putBlockOperationsLoops(_cont,this);
        return static_;
    }

    public void checkIterableCandidates(StringList _types,ContextEl _cont) {
        if (_types.onlyOneElt()) {
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = Templates.getAllTypes(type_).get(1);
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
                importedClassNameFirst = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameFirst);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char before expression
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameFirst);
                    _cont.addError(cast_);
                }
            }
            mapping_ = new Mapping();
            paramArg_ = Templates.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
                importedClassNameSecond = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameSecond);
                StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(getFile().getFileName());
                    cast_.setIndexFile(expressionOffset);
                    //separator char before expression
                    cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                            paramArg_,
                            importedClassNameSecond);
                    _cont.addError(cast_);
                }
            }
        } else {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(_cont.getStandards().getAliasObject());
            mapping_.setParam(_cont.getStandards().getAliasIterableTable());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(expressionOffset);
            //separator char before expression
            cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                    _cont.getStandards().getAliasObject(),
                    _cont.getStandards().getAliasIterableTable());
            _cont.addError(cast_);
        }
    }

    private void processVariables(ContextEl _cont) {
        if (StringList.quickEq(variableNameFirst, variableNameSecond)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffsetSecond);
            //second variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableNameFirst);
            _cont.addError(d_);
        }
        AnaLoopVariable lv_ = new AnaLoopVariable();
        if (!importedClassNameFirst.isEmpty()) {
            lv_.setClassName(importedClassNameFirst);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableNameFirst, lv_);
        lv_ = new AnaLoopVariable();
        if (!importedClassNameSecond.isEmpty()) {
            lv_.setClassName(importedClassNameSecond);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableNameSecond, lv_);
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


}
