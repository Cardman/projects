package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.BadVariableName;
import code.expressionlanguage.errors.custom.DuplicateVariable;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.IterableAnalysisResult;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendForEachLoop extends RendParentBlock implements RendLoop, RendReducableOperations {

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

    private CustList<RendDynOperationNode> opList;

    RendForEachLoop(Configuration _importingPage,
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
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
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
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opList.last();
        opList = ElRenderUtil.getReducedNodes(r_);
    }
    public String getImportedClassName() {
        return importedClassName;
    }
    public void buildEl(Configuration _cont,RendDocumentBlock _doc) {
        importedClassIndexName = _cont.resolveCorrectType(classIndexName);
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(importedClassIndexName, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(classIndexNameOffset);
            _cont.getClasses().addError(cast_);
        }
        if (_cont.getAnalyzing().containsVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffset);
            _cont.getClasses().addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffset);
            _cont.getClasses().addError(d_);
        }
        if (!_cont.isValidSingleToken(variableName)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(_cont.getCurrentFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setVarName(variableName);
            _cont.getClasses().addError(b_);
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (!StringList.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = _cont.resolveCorrectType(className);
        } else {
            importedClassName = "";
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_LIST);
        boolean static_ = _doc.isStaticContext();
        opList = ElRenderUtil.getAnalyzedOperations(expression,0, _cont, Calculation.staticCalculation(static_));
    }
    public void inferArrayClass(Configuration _cont) {
        RendDynOperationNode el_ = opList.last();
        ClassArgumentMatching compo_ = PrimitiveTypeUtil.getQuickComponentType(el_.getResultClass());
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_) && compo_.getNames().size() == 1) {
            importedClassName = compo_.getName();
        } else {
            Mapping mapping_ = new Mapping();
            if (importedClassName.isEmpty()) {
                mapping_.setArg("");
                mapping_.setParam("");
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_cont.getCurrentFileName());
                cast_.setIndexFile(expressionOffset);
                _cont.getClasses().addError(cast_);
            } else {
                mapping_.setArg(compo_);
                mapping_.setParam(importedClassName);
                StringMap<StringList> vars_ = _cont.getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_cont.getCurrentFileName());
                    cast_.setIndexFile(expressionOffset);
                    _cont.getClasses().addError(cast_);
                }
            }
        }
    }
    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        buildEl(_cont,_doc);
        RendDynOperationNode el_ = opList.last();
        Argument arg_ = el_.getArgument();
        if (Argument.isNullValue(arg_)) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_cont.getCurrentFileName());
            static_.setIndexFile(_cont.getCurrentLocationIndex());
            _cont.getClasses().addError(static_);
        } else if (el_.getResultClass().isArray()) {
            inferArrayClass(_cont);
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getInferredIterable(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
        putVariable(_cont);
    }
    public StringList getInferredIterable(StringList _types, Configuration _cont) {
        IterableAnalysisResult it_ = _cont.getStandards().getCustomType(_types, _cont.getContext());
        return it_.getClassName();
    }
    public void checkIterableCandidates(StringList _types,Configuration _cont) {
        if (_types.size() == 1) {
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = Templates.getAllTypes(type_).last();
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
                StringMap<StringList> vars_ = _cont.getCurrentConstraints();
                mapping_.setMapping(vars_);
                if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_cont.getCurrentFileName());
                    cast_.setIndexFile(expressionOffset);
                    _cont.getClasses().addError(cast_);
                }
            }
        } else {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(_cont.getStandards().getAliasObject());
            mapping_.setParam(_cont.getStandards().getAliasIterable());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(expressionOffset);
            _cont.getClasses().addError(cast_);
        }
    }
    public void putVariable(Configuration _cont) {
        LoopVariable lv_ = new LoopVariable();
        if (!importedClassName.isEmpty()) {
            lv_.setClassName(importedClassName);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            removeVarAndLoop(ip_);
            processBlock(_cont);
            return;
        }
        Struct its_ = processLoop(_cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        Struct iterStr_ = null;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        RendDynOperationNode el_ = opList.last();
        if (el_.getResultClass().isArray()) {
            ArrayStruct arr_ = (ArrayStruct)its_;
            length_ = arr_.getInstance().length;
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
            }
        } else {
            Argument arg_ = iterator(its_,_cont);
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return;
            }
            iterStr_ = arg_.getStruct();
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setClassName(importedClassName);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setContainer(its_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        processLastElementLoop(_cont);
    }

    Struct processLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(ATTRIBUTE_LIST);
        Argument arg_ = ElRenderUtil.calculateReuse(opList,_conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_ == NullStruct.NULL_VALUE) {
            String npe_ = _conf.getStandards().getAliasNullPe();
            _conf.setException(new ErrorStruct(_conf, npe_));
        }
        return ito_;

    }

    @Override
    public void exitStack(Configuration _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeVarAndLoop(ImportingPage _ip) {
        super.removeVarAndLoop(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableName);
    }


    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        rw_.setRead(forLoopLoc_);
        boolean hasNext_;
        if (l_.getStructIterator() != null) {
            Boolean has_ = iteratorHasNext(_conf);
            if (has_ == null) {
                return;
            }
            hasNext_ = has_;
        } else {
            hasNext_ = l_.hasNext();
        }

        if (hasNext_) {
            incrementLoop(_conf, l_, vars_);
        } else {
            l_.setFinished(true);
        }
    }

    private Boolean iteratorHasNext(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        Struct strIter_ = l_.getStructIterator();
        Argument arg_ = hasNext(strIter_,_conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return null;
        }
        return ((BooleanStruct) arg_.getStruct()).getInstance();
    }

    public void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        ImportingPage abs_ = _conf.getLastPage();

//        abs_.setGlobalOffset(variableNameOffset);
        LoopVariable lv_ = _vars.getVal(variableName);
        Struct iterator_ = _l.getStructIterator();
        Struct element_ = NullStruct.NULL_VALUE;
        Argument arg_ = Argument.createVoid();
        RendDynOperationNode el_ = opList.last();
        if (!el_.getResultClass().isArray()) {
            arg_ = next(iterator_,_conf);
        } else {
            Struct container_ = lv_.getContainer();
            LongStruct lg_ = new LongStruct(_l.getIndex());
            element_ = ExecInvokingOperation.getElement(container_, lg_, _conf);
        }
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        if (!el_.getResultClass().isArray()) {
            element_ = arg_.getStruct();
        } else {
            arg_ = new Argument(element_);
        }
        if (!Templates.checkObject(importedClassName, arg_, _conf)) {
            return;
        }
        lv_.setStruct(element_);
        lv_.setIndex(lv_.getIndex() + 1);
        abs_.getRendReadWrite().setRead(getFirstChild());
    }
}
