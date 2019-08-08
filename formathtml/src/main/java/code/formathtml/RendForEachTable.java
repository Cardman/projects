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
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.BeanNatLgNames;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class RendForEachTable extends RendParentBlock implements RendLoop, RendWithEl,RendReducableOperations {

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

    private CustList<RendDynOperationNode> opList;

    RendForEachTable(Configuration _importingPage,
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
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
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

    public int getLabelOffset() {
        return labelOffset;
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
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getCustomType(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
        putVariable(_cont);
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opList.last();
        opList = RenderExpUtil.getReducedNodes(r_);
    }

    private StringList getCustomType(StringList _names, Configuration _context) {
        StringList out_ = new StringList();
        BeanLgNames stds_ = _context.getAdvStandards();
        if (!(stds_ instanceof BeanCustLgNames)) {
            return _names;
        }
        for (String f : _names) {
            String iterable_ = stds_.getAliasIterableTable();
            String type_ = Templates.getFullTypeByBases(f, iterable_, _context);
            if (type_ != null) {
                out_.add(type_);
            }
        }
        out_.removeDuplicates();
        return out_;
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
        if (_cont.getAnalyzing().containsVar(variableNameFirst)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameFirst);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffsetFirst);
            _cont.getClasses().addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableNameFirst)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameFirst);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffsetFirst);
            _cont.getClasses().addError(d_);
        }
        if (!_cont.isValidSingleToken(variableNameFirst)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(_cont.getCurrentFileName());
            b_.setIndexFile(variableNameOffsetFirst);
            b_.setVarName(variableNameFirst);
            _cont.getClasses().addError(b_);
        }
        if (_cont.getAnalyzing().containsVar(variableNameSecond)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameSecond);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffsetSecond);
            _cont.getClasses().addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableNameSecond)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameSecond);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffsetSecond);
            _cont.getClasses().addError(d_);
        }
        if (!_cont.isValidSingleToken(variableNameSecond)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(_cont.getCurrentFileName());
            b_.setIndexFile(variableNameOffsetSecond);
            b_.setVarName(variableNameSecond);
            _cont.getClasses().addError(b_);
        }
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffsetFirst);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
            importedClassNameFirst = _cont.resolveCorrectType(classNameFirst);
        } else {
            importedClassNameFirst = "";
        }
        page_.setGlobalOffset(classNameOffsetSecond);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
            importedClassNameSecond = _cont.resolveCorrectType(classNameSecond);
        } else {
            importedClassNameSecond = "";
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_MAP);
        boolean static_ = _doc.isStaticContext();
        opList = RenderExpUtil.getAnalyzedOperations(expression, 0, _cont, Calculation.staticCalculation(static_));
    }

    public void checkIterableCandidates(StringList _types, Configuration _cont) {
        if (_types.size() == 1) {
            if (!(_cont.getStandards() instanceof BeanCustLgNames)) {
                return;
            }
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = Templates.getAllTypes(type_).get(1);
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
                importedClassNameFirst = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameFirst);
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
            mapping_ = new Mapping();
            paramArg_ = Templates.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
                importedClassNameSecond = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameSecond);
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
            mapping_.setParam(_cont.getStandards().getAliasIterableTable());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(expressionOffset);
            _cont.getClasses().addError(cast_);
        }
    }

    public void putVariable(Configuration _cont) {
        if (StringList.quickEq(variableNameFirst, variableNameSecond)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameSecond);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffsetSecond);
            _cont.getClasses().addError(d_);
        }
        LoopVariable lv_ = new LoopVariable();
        if (!importedClassNameFirst.isEmpty()) {
            lv_.setClassName(importedClassNameFirst);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableNameFirst, lv_);
        lv_ = new LoopVariable();
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

    public String getExpression() {
        return expression;
    }

    public String getImportedClassNameFirst() {
        return importedClassNameFirst;
    }

    public String getImportedClassNameSecond() {
        return importedClassNameSecond;
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
        Struct iterStr_;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        Argument arg_ = iteratorMultTable(its_,_cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        iterStr_ = arg_.getStruct();
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setIndex(-1);
        l_.setFinished(false);
        l_.setBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setClassName(importedClassNameFirst);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setContainer(its_);
        varsLoop_.put(variableNameFirst, lv_);
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setClassName(importedClassNameSecond);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setContainer(its_);
        varsLoop_.put(variableNameSecond, lv_);
        processLastElementLoop(_cont);
    }

    Struct processLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(ATTRIBUTE_MAP);
        Argument arg_ = RenderExpUtil.calculateReuse(opList,_conf);
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
    public void exitStack(Configuration _conf) {
        processLastElementLoop(_conf);
    }

    @Override
    public void removeVarAndLoop(ImportingPage _ip) {
        super.removeVarAndLoop(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableNameFirst);
        v_.removeKey(variableNameSecond);
    }

    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        rw_.setRead(forLoopLoc_);
        Boolean has_ = iteratorHasNext(_conf);
        if (has_ == null) {
            return;
        }
        boolean hasNext_ = has_;
        if (hasNext_) {
            incrementLoop(_conf, l_, vars_);
        } else {
            l_.setFinished(true);
        }
    }
    public void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        Struct iterator_ = _l.getStructIterator();
        ImportingPage call_ = _conf.getLastPage();
        Argument nextPair_ = nextPair(iterator_,_conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        Struct value_ = nextPair_.getStruct();
        Argument arg_ = first(value_,_conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        if (!Templates.checkObject(importedClassNameFirst, arg_, _conf)) {
            return;
        }
        LoopVariable lv_ = _vars.getVal(variableNameFirst);
        lv_.setStruct(arg_.getStruct());
        lv_.setIndex(lv_.getIndex() + 1);
        arg_ = second(value_,_conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        if (!Templates.checkObject(importedClassNameSecond, arg_, _conf)) {
            return;
        }
        lv_ = _vars.getVal(variableNameSecond);
        lv_.setStruct(arg_.getStruct());
        lv_.setIndex(lv_.getIndex() + 1);
        call_.getRendReadWrite().setRead(getFirstChild());
    }
    private Boolean iteratorHasNext(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        Struct strIter_ = l_.getStructIterator();
        Argument arg_ = hasNextPair(strIter_,_conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return null;
        }
        return ((BooleanStruct) arg_.getStruct()).getInstance();
    }
}
