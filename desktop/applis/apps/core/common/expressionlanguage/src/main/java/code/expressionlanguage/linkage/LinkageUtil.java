package code.expressionlanguage.linkage;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.coverage.*;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.options.KeyWords;
import code.util.*;

public final class LinkageUtil {

    private LinkageUtil(){
    }
    public static StringMap<String> errors(ContextEl _cont) {
        StringMap<String> files_ = new StringMap<String>();
        for (FileBlock f: _cont.getAnalyzing().getErrors().getFiles()) {
            if (f.isPredefined()) {
                continue;
            }
            String value_ = _cont.getClasses().getResources().getVal(f.getFileName());
            String fileExp_ = f.getFileName() + ".html";
            CustList<PartOffset> listStr_ = processError(f,fileExp_,_cont);
            StringBuilder xml_ = build(f, value_, listStr_);
            String rel_ = relativize(fileExp_,"css/style.css");
            String cssPart_ = "<head>" +
                    "<link href=\""+rel_+"\" rel=\"stylesheet\" type=\"text/css\"/>" +
                    "</head>";
            files_.addEntry(fileExp_,"<html>"+cssPart_+"<body><pre>"+xml_+"</pre></body></html>");
        }
        String cssContent_ = ".e{background-color:red;}\n";
        cssContent_ += ".s{color:blue;}\n";
        cssContent_ += ".c{color:grey;}\n";
        cssContent_ += ".i{color:red;}\n";
        files_.addEntry("css/style.css",cssContent_);
        return files_;
    }

    private static StringBuilder build(FileBlock f, String _value, CustList<PartOffset> listStr_) {
        String value_ = _value;
        if (_value.isEmpty()) {
            value_ = " ";
        }
        StringBuilder xml_ = new StringBuilder(value_.length());
        int i_ = value_.length() - 1;
        for (PartOffset t:listStr_.getReverse()) {
            String part_ = t.getPart();
            if (part_.isEmpty()) {
                continue;
            }
            int off_ = t.getOffset();
            while (i_ >= off_) {
                char ch_ = value_.charAt(i_);
                insertTr(f,xml_, ch_,i_);
                i_--;
            }
            xml_.insert(0, part_);
        }
        while (i_ >= 0) {
            char ch_ = value_.charAt(i_);
            insertTr(f,xml_, ch_,i_);
            i_--;
        }
        return xml_;
    }

    public static StringMap<String> export(ContextEl _cont) {
        StringMap<String> files_ = new StringMap<String>();
        Coverage cov_ = _cont.getCoverage();
        for (FileBlock f: cov_.getFiles()) {
            if (f.isPredefined()) {
                continue;
            }
            String value_ = _cont.getClasses().getResources().getVal(f.getFileName());
            String fileExp_ = f.getFileName() + ".html";
            CustList<PartOffset> listStr_ = processReport(f,fileExp_,_cont);
            StringBuilder xml_ = build(f, value_, listStr_);
            String rel_ = relativize(fileExp_,"css/style.css");
            String cssPart_ = "<head>" +
                    "<link href=\""+rel_+"\" rel=\"stylesheet\" type=\"text/css\"/>" +
                    "</head>";
            files_.addEntry(fileExp_,"<html>"+cssPart_+"<body><pre>"+xml_+"</pre></body></html>");
        }
        String cssContent_ = ".f{background-color:green;}\n";
        cssContent_ += ".g{background-color:lightgreen;}\n";
        cssContent_ += ".p{background-color:yellow;}\n";
        cssContent_ += ".q{background-color:lightyellow;}\n";
        cssContent_ += ".n{background-color:red;}\n";
        cssContent_ += ".s{color:blue;}\n";
        cssContent_ += ".c{color:grey;}\n";
        cssContent_ += ".i{color:red;}\n";
        files_.addEntry("css/style.css",cssContent_);
        return files_;
    }

    private static void insertTr(FileBlock _ex,StringBuilder _xml, char _ch, int _index) {
        String tr_ = transformText(_ch);
        if (_ex.getBeginComments().containsObj(_index)) {
            _xml.insert(0, tr_);
            _xml.insert(0, "<span class=\"c\">");
            return;
        }
        if (_ex.getEndComments().containsObj(_index)) {
            _xml.insert(0, "</span>");
            _xml.insert(0, tr_);
            return;
        }
        _xml.insert(0, tr_);
    }
    private static String transformText(char _ch) {
        if (_ch == '<') {
            return("&lt;");
        }
        if (_ch == '>') {
            return("&gt;");
        }
        if (_ch == '&') {
            return("&amp;");
        }
        return Character.toString(_ch);
    }
    private static CustList<PartOffset> processError(FileBlock _ex, String _fileExp,ContextEl _cont){
        CustList<PartOffset> list_ = new CustList<PartOffset>();
        VariablesOffsets vars_ = new VariablesOffsets();
        vars_.setCurrentFileName(_fileExp);
        Block child_ = _ex;
        while (true) {
            if (child_ instanceof FileBlock) {
                processFileBlockError((FileBlock)child_,list_);
            }
            if (child_.getFile().getErrorsFiles().isEmpty()) {
                if (child_ instanceof FileBlock) {
                    processFileBlockReport((FileBlock)child_,list_);
                }
                if (child_ instanceof RootBlock) {
                    processGlobalRootBlockError((RootBlock) child_, list_);
                }
                Block parType_ = child_.getOuter();
                if (parType_.getBadIndexes().isEmpty()) {
                    if (child_.isReachableError()) {
                        if (!(child_ instanceof Line)&&!(child_ instanceof DeclareVariable)&&!(child_ instanceof EmptyInstruction)&&!(child_ instanceof RootBlock)) {
                            String err_ = StringList.join(child_.getErrorsBlock(),"\n\n");
                            int off_ = child_.getBegin();
                            int l_ = child_.getLengthHeader();
                            list_.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",off_));
                            list_.add(new PartOffset("</a>",off_+l_));
                        }
                    }
                    if (child_ instanceof RootBlock) {
                        if (child_ instanceof InnerElementBlock) {
                            processInnerElementBlockError(vars_,(InnerElementBlock)child_,_cont,list_);
                        } else {
                            processRootBlockError(vars_, (RootBlock) child_, _cont, list_);
                        }
                    }
                    if (child_ instanceof OperatorBlock) {
                        processOperatorBlockError(vars_,(OperatorBlock)child_,_cont,list_);
                    }
                    if (child_ instanceof ConstructorBlock) {
                        processConstructorBlockError(vars_,(ConstructorBlock)child_,_cont,list_);
                    }
                    if (child_ instanceof OverridableBlock) {
                        processOverridableBlockError(vars_,(OverridableBlock)child_,_cont,list_);
                    }
                    if (child_ instanceof AnnotationMethodBlock) {
                        processAnnotationMethodBlockError(vars_,(AnnotationMethodBlock)child_,_cont,list_);
                    }
                    if (child_ instanceof ElementBlock) {
                        processElementBlockError(vars_,(ElementBlock)child_,_cont,list_);
                    }
                    if (child_ instanceof FieldBlock) {
                        processFieldBlockError(vars_,(FieldBlock)child_,_cont,list_);
                    }
                    if (child_ instanceof WhileCondition) {
                        processWhileConditionError(vars_,(WhileCondition)child_,_cont,list_);
                    }
                    if (child_ instanceof IfCondition) {
                        processIfConditionError(vars_,(IfCondition)child_,_cont,list_);
                    }
                    if (child_ instanceof ElseIfCondition) {
                        processConditionError((ElseIfCondition)child_, vars_,_cont,list_);
                    }
                    if (child_ instanceof DoBlock) {
                        processDoBlockError((DoBlock)child_,list_);
                    }
                    if (child_ instanceof DoWhileCondition) {
                        processConditionError((DoWhileCondition)child_, vars_,_cont,list_);
                    }
                    if (child_ instanceof SwitchBlock) {
                        processSwitchBlockError(vars_,(SwitchBlock)child_,_cont,list_);
                    }
                    if (child_ instanceof CaseCondition) {
                        processCaseConditionError(vars_,(CaseCondition)child_,_cont,list_);
                    }
                    if (child_ instanceof TryEval) {
                        processTryEvalError((TryEval)child_,list_);
                    }
                    if (child_ instanceof CatchEval) {
                        processCatchEvalError(vars_,(CatchEval)child_,_cont,list_);
                    }
                    if (child_ instanceof DeclareVariable) {
                        processDeclareVariableError((DeclareVariable)child_,_cont,list_);
                    }
                    if (child_ instanceof Line) {
                        processLineError(vars_,(Line)child_,_cont,list_);
                    }
                    if (child_ instanceof ForIterativeLoop) {
                        processForIterativeLoopError(vars_,(ForIterativeLoop)child_,_cont,list_);
                    }
                    if (child_ instanceof ForEachLoop) {
                        processForEachLoopError(vars_,(ForEachLoop)child_,_cont,list_);
                    }
                    if (child_ instanceof ForEachTable) {
                        processForEachTableError(vars_,(ForEachTable)child_,_cont,list_);
                    }
                    if (child_ instanceof ForMutableIterativeLoop) {
                        processForMutableIterativeLoopError(vars_,(ForMutableIterativeLoop)child_,_cont,list_);
                    }
                    if (child_ instanceof ReturnMethod) {
                        processReturnMethodError(vars_,(ReturnMethod)child_,_cont,list_);
                    }
                    if (child_ instanceof Throwing) {
                        processThrowingError(vars_,(Throwing)child_,_cont,list_);
                    }
                    if (child_ instanceof BreakBlock) {
                        processBreakBlockError((BreakBlock)child_,list_);
                    }
                    if (child_ instanceof ContinueBlock) {
                        processContinueBlockError((ContinueBlock)child_,list_);
                    }
                    if (child_.isReachableError()) {
                        if (child_ instanceof Line) {
                            String err_ = StringList.join(child_.getErrorsBlock(),"\n\n");
                            int off_ = child_.getBegin();
                            int l_ = child_.getLengthHeader();
                            list_.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",off_));
                            list_.add(new PartOffset("</a>",off_+l_));
                        }
                    }
                }
            }
            Block firstChild_ = child_.getFirstChild();
            if (firstChild_ != null) {
                child_ = firstChild_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                Block nextSibling_ = child_.getNextSibling();
                if (nextSibling_ != null) {
                    child_ = nextSibling_;
                    break;
                }
                BracedBlock parent_ = child_.getParent();
                if (parent_ == _ex || parent_ == null) {
                    stop_ = true;
                    break;
                }
                child_ = parent_;
            }
            if (stop_) {
                break;
            }
        }
        return list_;
    }

    private static void processFileBlockError(FileBlock _cond, CustList<PartOffset> _parts) {
        for (GraphicErrorInterpret g: _cond.getErrorsFiles()) {
            int index_ = g.getIndexFile();
            _parts.add(new PartOffset("<a title=\""+g.getBuiltError()+"\" class=\"e\">", index_));
            _parts.add(new PartOffset("</a>", index_+ g.getLength()));
        }
    }
    private static void processGlobalRootBlockError(RootBlock _cond, CustList<PartOffset> _parts) {
        for (GraphicErrorInterpret g: _cond.getGlobalErrorsPars()) {
            int index_ = g.getIndexFile();
            _parts.add(new PartOffset("<a title=\""+g.getBuiltError()+"\" class=\"e\">", index_));
            _parts.add(new PartOffset("</a>", index_+ g.getLength()));
        }
    }
    private static CustList<PartOffset> processReport(FileBlock _ex,String _fileExp,ContextEl _cont){
        CustList<PartOffset> list_ = new CustList<PartOffset>();
        VariablesOffsets vars_ = new VariablesOffsets();
        vars_.setCurrentFileName(_fileExp);
        Block child_ = _ex;
        while (true) {
            if (child_ instanceof IfCondition) {
                processIfConditionReport(vars_,(IfCondition)child_,_cont,list_);
            }
            if (child_ instanceof ElseIfCondition) {
                processElseIfConditionReport(vars_,(ElseIfCondition)child_,_cont,list_);
            }
            if (child_ instanceof WhileCondition) {
                processWhileConditionReport(vars_,(WhileCondition)child_,_cont,list_);
            }
            if (child_ instanceof DoWhileCondition) {
                processDoWhileConditionReport(vars_,(DoWhileCondition)child_,_cont,list_);
            }
            if (child_ instanceof DoBlock) {
                processDoBlockReport((DoBlock)child_,list_);
            }
            if (child_ instanceof ForMutableIterativeLoop) {
                processForMutableIterativeLoopReport(vars_,(ForMutableIterativeLoop)child_,_cont,list_);
            }
            if (child_ instanceof SwitchBlock) {
                processSwitchBlockReport(vars_,(SwitchBlock)child_,_cont,list_);
            }
            if (child_ instanceof CaseCondition) {
                processCaseConditionReport(vars_,(CaseCondition)child_,_cont,list_);
            }
            if (child_ instanceof DefaultCondition) {
                processDefaultConditionReport((DefaultCondition)child_,_cont,list_);
            }
            if (child_ instanceof TryEval) {
                processTryEvalReport((TryEval)child_,list_);
            }
            if (child_ instanceof CatchEval) {
                processCatchEvalReport(vars_,(CatchEval)child_,_cont,list_);
            }
            if (child_ instanceof NullCatchEval) {
                processAbstractCatchEvalReport((NullCatchEval)child_,_cont,list_);
            }
            if (child_ instanceof DeclareVariable) {
                processDeclareVariableReport((DeclareVariable)child_,_cont,list_);
            }
            if (child_ instanceof Line) {
                processLineReport(vars_,(Line)child_,_cont,list_);
            }
            if (child_ instanceof ReturnMethod) {
                processReturnMethodReport(vars_,(ReturnMethod)child_,_cont,list_);
            }
            if (child_ instanceof Throwing) {
                processThrowingReport(vars_,(Throwing)child_,_cont,list_);
            }
            if (child_ instanceof BreakBlock) {
                processBreakBlockReport((BreakBlock)child_,list_);
            }
            if (child_ instanceof ContinueBlock) {
                processContinueBlockReport((ContinueBlock)child_,list_);
            }
            if (child_ instanceof ForIterativeLoop) {
                processForIterativeLoopReport(vars_,(ForIterativeLoop)child_,_cont,list_);
            }
            if (child_ instanceof ForEachLoop) {
                processForEachLoopReport(vars_,(ForEachLoop)child_,_cont,list_);
            }
            if (child_ instanceof ForEachTable) {
                processForEachTableReport(vars_,(ForEachTable)child_,_cont,list_);
            }
            if (child_ instanceof ElementBlock) {
                processElementBlockReport(vars_,(ElementBlock)child_,_cont,list_);
            }
            if (child_ instanceof FieldBlock) {
                processFieldBlockReport(vars_,(FieldBlock)child_,_cont,list_);
            }
            if (child_ instanceof ConstructorBlock) {
                processConstructorBlockReport(vars_,(ConstructorBlock)child_,_cont,list_);
            }
            if (child_ instanceof OverridableBlock) {
                processOverridableBlockReport(vars_,(OverridableBlock)child_,_cont,list_);
            }
            if (child_ instanceof AnnotationMethodBlock) {
                processAnnotationMethodBlockReport(vars_,(AnnotationMethodBlock)child_,_cont,list_);
            }
            if (child_ instanceof OperatorBlock) {
                processOperatorBlockReport(vars_,(OperatorBlock)child_,_cont,list_);
            }
            if (child_ instanceof RootBlock) {
                if (child_ instanceof InnerElementBlock) {
                    processInnerElementBlockReport(vars_,(InnerElementBlock)child_,_cont,list_);
                } else {
                    processRootBlockReport(vars_,(RootBlock)child_,_cont,list_);
                }
            }
            if (child_ instanceof FileBlock) {
                processFileBlockReport((FileBlock)child_,list_);
            }
            Block firstChild_ = child_.getFirstChild();
            if (firstChild_ != null) {
                child_ = firstChild_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                Block nextSibling_ = child_.getNextSibling();
                if (nextSibling_ != null) {
                    child_ = nextSibling_;
                    break;
                }
                BracedBlock parent_ = child_.getParent();
                if (parent_ == _ex) {
                    stop_ = true;
                    break;
                }
                child_ = parent_;
            }
            if (stop_) {
                break;
            }
        }
        return list_;
    }
    private static void processIfConditionReport(VariablesOffsets _vars,IfCondition _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoversConditions(_cond);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordIf().length()));
        processConditionReport(_cond,_vars,_cont,_parts);
        refLabel(_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processIfConditionError(VariablesOffsets _vars,IfCondition _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        processConditionError(_cond, _vars, _cont, _parts);
        refLabelError(_cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processWhileConditionError(VariablesOffsets _vars,WhileCondition _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        processConditionError(_cond, _vars, _cont, _parts);
        refLabelError(_cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processElseIfConditionReport(VariablesOffsets _vars,ElseIfCondition _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoversConditions(_cond);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cond.getDelta()));
        processConditionReport(_cond,_vars,_cont,_parts);
    }
    private static void processWhileConditionReport(VariablesOffsets _vars,WhileCondition _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoversConditions(_cond);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordWhile().length()));
        processConditionReport(_cond,_vars,_cont,_parts);
        refLabel(_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForMutableIterativeLoopReport(VariablesOffsets _vars,ForMutableIterativeLoop _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        if (_cond.getRootExp() != null) {
            AbstractCoverageResult result_ = _cont.getCoverage().getCoversConditions(_cond);
            String tag_;
            if (result_.isFullCovered()) {
                tag_ = "<span class=\"f\">";
            } else if (result_.isPartialCovered()) {
                tag_ = "<span class=\"p\">";
            } else {
                tag_ = "<span class=\"n\">";
            }
            int off_ = _cond.getOffset().getOffsetTrim();
            _parts.add(new PartOffset(tag_,off_));
            tag_ = "</span>";
            _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordFor().length()));
        }
        appendVars(_cond, _cont, _parts);
        if (_cond.getRootInit() != null) {
            _vars.setPossibleDeclareLoopVars(true);
            int off_ = _cond.getInitOffset();
            int offsetEndBlock_ = off_ + _cond.getInit().length();
            buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRootInit(),offsetEndBlock_,_parts);
            _vars.setPossibleDeclareLoopVars(false);
        }
        if (_cond.getRootExp() != null) {
            int off_ = _cond.getExpressionOffset();
            int offsetEndBlock_ = off_ + _cond.getExpression().length();
            buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRootExp(),offsetEndBlock_,_parts);
        }
        if (_cond.getRootStep() != null) {
            int off_ = _cond.getStepOffset();
            int offsetEndBlock_ = off_ + _cond.getStep().length();
            buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRootStep(),offsetEndBlock_,_parts);
        }
        refLabel(_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForMutableIterativeLoopError(VariablesOffsets _vars,ForMutableIterativeLoop _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        appendVars(_cond, _cont, _parts);
        if (_cond.getRootInit() != null) {
            _vars.setPossibleDeclareLoopVars(true);
            int off_ = _cond.getInitOffset();
            buildErrorReport(_cont,_vars,off_,_cond,_cond.getRootInit(),_parts);
            _vars.setPossibleDeclareLoopVars(false);
        }
        if (_cond.getRootExp() != null) {
            int off_ = _cond.getExpressionOffset();
            buildErrorReport(_cont,_vars,off_,_cond,_cond.getRootExp(),_parts);
        }
        if (_cond.getRootStep() != null) {
            int off_ = _cond.getStepOffset();
            buildErrorReport(_cont,_vars,off_,_cond,_cond.getRootStep(),_parts);
        }
        refLabelError(_cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendVars(ForMutableIterativeLoop _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        String clName_ = _cond.getClassName().trim();
        String errInf_ = _cond.getErrInf();
        String importedClassName_ = _cond.getImportedClassName();
        int classNameOffset_ = _cond.getClassNameOffset();
        CustList<PartOffset> partOffsets_ = _cond.getPartOffsets();
        procInferVar(_parts, keyWordVar_, clName_, errInf_, importedClassName_, classNameOffset_, partOffsets_);
    }

    private static void processSwitchBlockReport(VariablesOffsets _vars,SwitchBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int full_ = 0;
        int count_ = 0;
        for (StandardCoverageResult e: _cont.getCoverage().getCoverSwitchs(_cond).values()) {
            count_ += e.getCovered();
            full_ += e.getFull();
        }
        StandardCoverageResult noDef_ = _cont.getCoverage().getCoverNoDefSwitchs(_cond);
        if (noDef_ != null) {
            count_ += noDef_.getCovered();
            full_ += noDef_.getFull();
        }
        String tag_;
        if (count_ == full_) {
            tag_ = "<span class=\"f\">";
        } else if (count_ > 0) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_+"<a title=\""+count_+"/"+full_+"\">",off_));
        tag_ = "</span>";
        _parts.add(new PartOffset("</a>"+tag_,off_+ _cont.getKeyWords().getKeyWordSwitch().length()));
        off_ = _cond.getValueOffset();
        int offsetEndBlock_ = off_ + _cond.getValue().length();
        buildCoverageReport(_cont,_vars,off_,_cond, _cond.getRoot(),offsetEndBlock_,_parts);
        refLabel(_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processSwitchBlockError(VariablesOffsets _vars,SwitchBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int off_ = _cond.getValueOffset();
        if (!_cond.getErr().isEmpty()) {
            _parts.add(new PartOffset("<a title=\""+_cond.getErr()+"\" class=\"e\">",off_));
            _parts.add(new PartOffset("</a>",off_+1));
        }
        buildErrorReport(_cont,_vars,off_,_cond, _cond.getRoot(),_parts);
        refLabelError(_cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processCaseConditionReport(VariablesOffsets _vars,CaseCondition _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        BracedBlock parent_ = _cond.getParent();
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverSwitchs(parent_,_cond);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getValueOffset();
        _parts.add(new PartOffset(tag_,off_));
        if (_cond.isBuiltEnum()) {
            GeneType type_ = _cont.getClassBody(_cond.getTypeEnum());
            int delta_ = getDelta(_cond, (ExecBlock) type_);
            String file_ = ((ExecRootBlock) type_).getFile().getRenderFileName();
            String currentFileName_ = _vars.getCurrentFileName();
            String rel_ = relativize(currentFileName_,file_+"#m"+delta_);
            tag_ = "<a title=\""+transform(_cond.getTypeEnum() +"."+ _cond.getValue())+"\" href=\""+rel_+"\">";
            _parts.add(new PartOffset(tag_,off_));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,off_+ _cond.getValue().length()));
        } else {
            int offsetEndBlock_ = off_ + _cond.getValue().length();
            buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRoot(),offsetEndBlock_,_parts);
        }
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cond.getValue().length()));
    }
    private static void processCaseConditionError(VariablesOffsets _vars,CaseCondition _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int off_;
        if (_cond.isBuiltEnum()) {
            off_ = _cond.getValueOffset();
            GeneType type_ = _cont.getClassBody(_cond.getTypeEnum());
            int delta_ = getDelta(_cond, (ExecBlock) type_);
            if (delta_ < 0) {
                String tag_ = "<a title=\""+transform(StringList.join(_cond.getEmptErrs(),"\n\n"))+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_,off_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,off_+Math.max(1, _cond.getValue().length())));
                return;
            }
            String file_ = ((ExecRootBlock) type_).getFile().getRenderFileName();
            String currentFileName_ = _vars.getCurrentFileName();
            String rel_ = relativize(currentFileName_,file_+"#m"+delta_);
            String tag_ = "<a title=\""+transform(_cond.getTypeEnum() +"."+ _cond.getValue())+"\" href=\""+rel_+"\">";
            _parts.add(new PartOffset(tag_,off_));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,off_+ _cond.getValue().length()));
        } else {
            off_ = _cond.getValueOffset();
            buildErrorReport(_cont,_vars,off_,_cond,_cond.getRoot(),_parts);
        }
    }

    private static int getDelta(CaseCondition _cond, ExecBlock type_) {
        int delta_ = -1;
        for (ExecBlock b: ExecBlock.getDirectChildren(type_)) {
            if (!(b instanceof ExecInnerTypeOrElement)) {
                continue;
            }
            ExecInnerTypeOrElement f_ = (ExecInnerTypeOrElement)b;
            if (!StringList.quickEq(f_.getUniqueFieldName(), _cond.getValue())) {
                continue;
            }
            delta_ = f_.getFieldNameOffset();
        }
        return delta_;
    }

    private static void processDefaultConditionReport(DefaultCondition _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        BracedBlock parent_ = _cond.getParent();
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverSwitchs(parent_,_cond);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordDefault().length()));
    }

    private static void processDoBlockReport(DoBlock _cond, CustList<PartOffset> _parts) {
        refLabel(_parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void processDoBlockError(DoBlock _cond, CustList<PartOffset> _parts) {
        refLabelError(_cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processDoWhileConditionReport(VariablesOffsets _vars,DoWhileCondition _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoversConditions(_cond);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordWhile().length()));
        processConditionReport(_cond,_vars,_cont,_parts);
    }
    private static void processTryEvalReport(TryEval _cond, CustList<PartOffset> _parts) {
        refLabel(_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processTryEvalError(TryEval _cond, CustList<PartOffset> _parts) {
        refLabelError(_cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processCatchEvalReport(VariablesOffsets _vars,CatchEval _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        processAbstractCatchEvalReport(_cond,_cont,_parts);
        _parts.addAllElts(_cond.getPartOffsets());
        String tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
    }
    private static void processCatchEvalError(VariablesOffsets _vars,CatchEval _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        _parts.addAllElts(_cond.getPartOffsets());
        StringList errs_ = _cond.getNameErrors();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringList.join(errs_,"\n\n"));
            String tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\" title=\""+err_+"\" class=\"e\"\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            return;
        }
        String tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
    }
    private static void processAbstractCatchEvalReport(AbstractCatchEval _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        String tag_;
        if (_cont.getCoverage().getCatches(_cond)) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordCatch().length()));
    }
    private static void processDeclareVariableReport(DeclareVariable _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(_cond.getClassName().trim(), keyWordVar_)) {
            String tag_ = "<b title=\""+transform(_cond.getImportedClassName())+"\">";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset() + keyWordVar_.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsets());
        }
    }
    private static void processDeclareVariableError(DeclareVariable _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        String clName_ = _cond.getClassName().trim();
        String errInf_ = _cond.getErrInf();
        String importedClassName_ = _cond.getImportedClassName();
        int classNameOffset_ = _cond.getClassNameOffset();
        CustList<PartOffset> partOffsets_ = _cond.getPartOffsets();
        procInferVar(_parts, keyWordVar_, clName_, errInf_, importedClassName_, classNameOffset_, partOffsets_);
    }

    private static void procInferVar(CustList<PartOffset> _parts, String keyWordVar_, String clName_, String errInf_, String importedClassName_, int classNameOffset_, CustList<PartOffset> partOffsets_) {
        if (StringList.quickEq(clName_, keyWordVar_)) {
            if (errInf_.isEmpty()) {
                String tag_ = "<b title=\""+transform(importedClassName_)+"\">";
                _parts.add(new PartOffset(tag_, classNameOffset_));
                tag_ = "</b>";
                _parts.add(new PartOffset(tag_, classNameOffset_ + keyWordVar_.length()));
            } else {
                String tag_ = "<a title=\""+transform(errInf_)+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_, classNameOffset_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, classNameOffset_ + keyWordVar_.length()));
            }
        } else {
            _parts.addAllElts(partOffsets_);
        }
    }

    private static void processLineReport(VariablesOffsets _vars,Line _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int blOffset_ = _cond.getExpressionOffset();
        int endBl_ = blOffset_ + _cond.getExpression().length();
        buildCoverageReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),endBl_,_parts);
    }
    private static void processLineError(VariablesOffsets _vars,Line _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int blOffset_ = _cond.getExpressionOffset();
        buildErrorReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),_parts);
    }
    private static void processBreakBlockReport(BreakBlock _cond, CustList<PartOffset> _parts) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        String tag_ = "<a href=\"#"+ _cond.getLabelOffsetRef() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getLabelOffset()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processBreakBlockError(BreakBlock _cond, CustList<PartOffset> _parts) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        StringList errs_ = _cond.getErrorsRefLabels();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringList.join(errs_,"\n\n"));
            String tag_ = "<a title=\""+ err_ +"\" class=\"e\">";
            _parts.add(new PartOffset(tag_, _cond.getLabelOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getLabelOffset() +_cond.getLabel().length()));
            return;
        }
        String tag_ = "<a href=\"#"+ _cond.getLabelOffsetRef() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getLabelOffset()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processContinueBlockReport(ContinueBlock _cond, CustList<PartOffset> _parts) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        String tag_ = "<a href=\"#"+ _cond.getLabelOffsetRef() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getLabelOffset()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processContinueBlockError(ContinueBlock _cond, CustList<PartOffset> _parts) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        StringList errs_ = _cond.getErrorsRefLabels();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringList.join(errs_,"\n\n"));
            String tag_ = "<a title=\""+ err_ +"\" class=\"e\">";
            _parts.add(new PartOffset(tag_, _cond.getLabelOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getLabelOffset() +_cond.getLabel().length()));
            return;
        }
        String tag_ = "<a href=\"#"+ _cond.getLabelOffsetRef() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getLabelOffset()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processReturnMethodReport(VariablesOffsets _vars,ReturnMethod _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        if (_cond.isEmpty()) {
            return;
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRoot(),offsetEndBlock_,_parts);
    }
    private static void processReturnMethodError(VariablesOffsets _vars,ReturnMethod _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        if (_cond.isEmpty()) {
            return;
        }
        int off_ = _cond.getExpressionOffset();
        buildErrorReport(_cont,_vars,off_,_cond,_cond.getRoot(),_parts);
    }
    private static void processThrowingReport(VariablesOffsets _vars,Throwing _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRoot(),offsetEndBlock_,_parts);
    }
    private static void processThrowingError(VariablesOffsets _vars,Throwing _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int off_ = _cond.getExpressionOffset();
        buildErrorReport(_cont,_vars,off_,_cond,_cond.getRoot(),_parts);
    }
    private static void processForIterativeLoopReport(VariablesOffsets _vars,ForIterativeLoop _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverLoops(_cond);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordIter().length()));
        tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        off_ = _cond.getInitOffset();
        int offsetEndBlock_ = off_ + _cond.getInit().length();
        buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRootInit(),offsetEndBlock_,_parts);
        off_ = _cond.getExpressionOffset();
        offsetEndBlock_ = off_ + _cond.getExpression().length();
        buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRootExp(),offsetEndBlock_,_parts);
        off_ = _cond.getStepOffset();
        offsetEndBlock_ = off_ + _cond.getStep().length();
        buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRootStep(),offsetEndBlock_,_parts);
        refLabel(_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForIterativeLoopError(VariablesOffsets _vars,ForIterativeLoop _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        StringList errs_ = _cond.getNameErrors();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringList.join(errs_,"\n\n"));
            String tag_;
            tag_ = "<a title=\""+err_+"\" class=\"e\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        } else {
            String tag_;
            tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        int off_ = _cond.getInitOffset();
        buildErrorReport(_cont,_vars,off_,_cond,_cond.getRootInit(),_parts);
        off_ = _cond.getExpressionOffset();
        buildErrorReport(_cont,_vars,off_,_cond,_cond.getRootExp(),_parts);
        off_ = _cond.getStepOffset();
        buildErrorReport(_cont,_vars,off_,_cond,_cond.getRootStep(),_parts);
        refLabelError(_cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForEachLoopReport(VariablesOffsets _vars,ForEachLoop _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverLoops(_cond);
        String tagCov_;
        if (result_.isFullCovered()) {
            tagCov_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tagCov_ = "<span class=\"p\">";
        } else {
            tagCov_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tagCov_,off_));
        appendVars(_cond, _cont, _parts);
        String tag_;
        tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRoot(),offsetEndBlock_,_parts);
        refLabel(_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForEachLoopError(VariablesOffsets _vars,ForEachLoop _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        appendVars(_cond, _cont, _parts);
        StringList errs_ = _cond.getNameErrors();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringList.join(errs_,"\n\n"));
            String tag_;
            tag_ = "<a title=\""+err_+"\" class=\"e\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        } else {
            String tag_;
            tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        if (!_cond.getSepErrors().isEmpty()) {
            String err_ = transform(StringList.join(_cond.getSepErrors(),"\n\n"));
            String tag_;
            tag_ = "<a title=\""+err_+"\" class=\"e\">";
            _parts.add(new PartOffset(tag_, _cond.getSepOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getSepOffset() + 1));
        }
        int off_ = _cond.getExpressionOffset();
        buildErrorReport(_cont,_vars,off_,_cond,_cond.getRoot(),_parts);
        refLabelError(_cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendVars(ForEachLoop _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(_cond.getClassName().trim(), keyWordVar_)) {
            String tag_;
            tag_ = "<b title=\""+transform(_cond.getImportedClassName())+"\">";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset() + keyWordVar_.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsets());
        }
    }

    private static void processForEachTableReport(VariablesOffsets _vars,ForEachTable _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverLoops(_cond);
        String tagCov_;
        if (result_.isFullCovered()) {
            tagCov_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tagCov_ = "<span class=\"p\">";
        } else {
            tagCov_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tagCov_,off_));
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        appendFirstVar(_cond, _parts, keyWordVar_);
        String tagVar_;
        tagVar_ = "<a name=\"m"+ _cond.getVariableNameOffsetFirst() +"\">";
        _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst()));
        tagVar_ = "</a>";
        _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
        appendSecondVar(_cond, _parts, keyWordVar_);
        String tag_;
        tag_ = "<a name=\"m"+ _cond.getVariableNameOffsetSecond() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
        off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRoot(),offsetEndBlock_,_parts);
        refLabel(_parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendSecondVar(ForEachTable _cond, CustList<PartOffset> _parts, String keyWordVar_) {
        if (StringList.quickEq(_cond.getClassNameSecond().trim(), keyWordVar_)) {
            String tag_;
            tag_ = "<b title=\""+transform(_cond.getImportedClassNameSecond())+"\">";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetSecond()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetSecond() + keyWordVar_.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsetsSecond());
        }
    }

    private static void processForEachTableError(VariablesOffsets _vars,ForEachTable _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        appendFirstVar(_cond, _parts, keyWordVar_);
        StringList errs_ = _cond.getNameErrorsFirst();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringList.join(errs_,"\n\n"));
            String tagVar_;
            tagVar_ = "<a title=\""+err_+"\" class=\"e\"\">";
            _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst()));
            tagVar_ = "</a>";
            _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
        } else {
            String tagVar_;
            tagVar_ = "<a name=\"m"+ _cond.getVariableNameOffsetFirst() +"\">";
            _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst()));
            tagVar_ = "</a>";
            _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
        }
        appendSecondVar(_cond, _parts, keyWordVar_);
        errs_ = _cond.getNameErrorsSecond();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringList.join(errs_,"\n\n"));
            String tag_;
            tag_ = "<a title=\""+err_+"\" class=\"e\"\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
        } else {
            String tag_;
            tag_ = "<a name=\"m"+ _cond.getVariableNameOffsetSecond() +"\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
        }
        if (!_cond.getSepErrors().isEmpty()) {
            String err_ = transform(StringList.join(_cond.getSepErrors(),"\n\n"));
            String tag_;
            tag_ = "<a title=\""+err_+"\" class=\"e\">";
            _parts.add(new PartOffset(tag_, _cond.getSepOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getSepOffset() + 1));
        }
        int off_ = _cond.getExpressionOffset();
        buildErrorReport(_cont,_vars,off_,_cond,_cond.getRoot(),_parts);
        refLabelError(_cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendFirstVar(ForEachTable _cond, CustList<PartOffset> _parts, String keyWordVar_) {
        if (StringList.quickEq(_cond.getClassNameFirst().trim(), keyWordVar_)) {
            String tag_;
            tag_ = "<b title=\""+transform(_cond.getImportedClassNameFirst())+"\">";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetFirst()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetFirst() + keyWordVar_.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsetsFirst());
        }
    }

    private static void processElementBlockReport(VariablesOffsets _vars,ElementBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            int end_ = begin_ + _cond.getAnnotations().get(i).length();
            buildCoverageReport(_cont,_vars,begin_,_cond,_cond.getRoots().get(i),end_,_parts,0,"",true);
        }
        AffectationOperation root_ = (AffectationOperation) _cond.getRoot();
        StandardInstancingOperation inst_ = (StandardInstancingOperation) root_.getFirstChild().getNextSibling();
        String cl_ = inst_.getClassName();
        cl_ = StringExpUtil.getIdFromAllTypes(cl_);
        ConstructorId c_ = inst_.getConstId();
        GeneType type_ = _cont.getClassBody(cl_);
        String file_ = ((ExecRootBlock) type_).getFile().getRenderFileName();
        String fileName_ = _vars.getCurrentFileName();
        CustList<ExecConstructorBlock> ctors_ = ExecBlock.getConstructorBodiesById(_cont, cl_, c_);
        if (!ctors_.isEmpty()) {
            ExecConstructorBlock ctor_ = ctors_.first();
            String rel_ = relativize(fileName_,file_+"#m"+ctor_.getNameOffset());
            String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\" title=\""+ transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\">";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
             tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() +_cond.getUniqueFieldName().length()));
        } else {
            String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\">";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() +_cond.getUniqueFieldName().length()));
        }
        _parts.addAllElts(_cond.getTypePartOffsets());
        int blOffset_ = _cond.getValueOffest();
        int endBl_ = _cond.getValueOffest() + _cond.getValue().length();
        buildCoverageReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),endBl_,_parts, _cond.getTrOffset() -1,_cond.getUniqueFieldName(),false);
    }

    private static void processElementBlockError(VariablesOffsets _vars,ElementBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            buildErrorReport(_cont,_vars,begin_,_cond,_cond.getRoots().get(i),_parts);
        }
        AffectationOperation root_ = (AffectationOperation) _cond.getRoot();
        StandardInstancingOperation inst_ = (StandardInstancingOperation) root_.getFirstChild().getNextSibling();
        String cl_ = inst_.getClassName();
        cl_ = StringExpUtil.getIdFromAllTypes(cl_);
        ConstructorId c_ = inst_.getConstId();
        GeneType type_ = _cont.getClassBody(cl_);
        String fileName_ = _vars.getCurrentFileName();
        StringList list_ = new StringList(_cond.getNameErrors());
        list_.addAllElts(inst_.getErrs());
        String err_="";
        if (!list_.isEmpty()) {
            err_ = LinkageUtil.transform(StringList.join(list_,"\n\n"));
        }
        CustList<ExecConstructorBlock> ctors_ = ExecBlock.getConstructorBodiesById(_cont, cl_, c_);
        if (!ctors_.isEmpty()) {
            String file_ = ((ExecRootBlock) type_).getFile().getRenderFileName();
            ExecConstructorBlock ctor_ = ctors_.first();
            String rel_ = relativize(fileName_,file_+"#m"+ctor_.getNameOffset());
            if (!list_.isEmpty()) {
                String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\" title=\""+err_+"\n\n"+ transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            } else {
                String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\" title=\""+ transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            }
            String tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() +_cond.getUniqueFieldName().length()));
        } else {
            if (!list_.isEmpty()) {
                String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\" title=\""+err_+"\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            } else {
                String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            }
            String tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() +_cond.getUniqueFieldName().length()));
        }
        _parts.addAllElts(_cond.getTypePartOffsets());
        int blOffset_ = _cond.getValueOffest();
        buildErrorReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),_parts, _cond.getTrOffset() -1,_cond.getUniqueFieldName());
    }
    private static void processFieldBlockReport(VariablesOffsets _vars,FieldBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotField(_vars, _cond, _cont, _parts);
        _parts.addAllElts(_cond.getTypePartOffsets());
        int blOffset_ = _cond.getValueOffset();
        int endBl_ = blOffset_ + _cond.getValue().length();
        buildCoverageReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),endBl_,_parts);
    }
    private static void processFieldBlockError(VariablesOffsets _vars,FieldBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotField(_vars, _cond, _cont, _parts);
        _parts.addAllElts(_cond.getTypePartOffsets());
        StringList errs_ = _cond.getNameRetErrors();
        int blOffset_ = _cond.getValueOffset();
        if (!errs_.isEmpty()) {
            String err_ = StringList.join(errs_,"\n\n");
            if (_cond.getValue().trim().isEmpty()) {
                blOffset_ = _cond.getClassNameOffset() + _cond.getClassName().length();
            }
            _parts.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",blOffset_));
            int endBl_ = blOffset_ + Math.max(1,_cond.getValue().length());
            _parts.add(new PartOffset("</a>",endBl_));
            return;
        }
        buildErrorReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),_parts);
    }

    private static void buildAnnotField(VariablesOffsets _vars, FieldBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            int end_ = begin_ + _cond.getAnnotations().get(i).length();
            buildCoverageReport(_cont,_vars,begin_,_cond,_cond.getRoots().get(i),end_,_parts,0,"",true);
        }
    }

    private static void processConstructorBlockReport(VariablesOffsets _vars,ConstructorBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_vars,_cond,_cont,_parts);
        int begName_ = _cond.getNameOffset();
        _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
        _parts.add(new PartOffset("</a>", _cond.getLeftPar()));
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            buildAnnotationsReport(_vars,_cond,i,_cont,_parts);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            _parts.add(new PartOffset("</a>",off_+param_.length()));
        }
    }
    private static void processConstructorBlockError(VariablesOffsets _vars,ConstructorBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_vars,_cond,_cont,_parts);
        int begName_ = _cond.getNameOffset();
        StringList errsName_ = _cond.getNameErrors();
        if (errsName_.isEmpty()) {
            _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
            _parts.add(new PartOffset("</a>", _cond.getLeftPar()));
        } else {
            String err_ = transform(StringList.join(errsName_,"\n\n"));
            _parts.add(new PartOffset("<a name=\"m"+begName_+"\" title=\""+err_+"\" class=\"e\">",begName_));
            _parts.add(new PartOffset("</a>", _cond.getLeftPar()));
        }
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            buildAnnotationsError(_vars,_cond,i,_cont,_parts);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            StringList errs_ = _cond.getParamErrors().get(i);
            if (errs_.isEmpty()) {
                _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
                _parts.add(new PartOffset("</a>",off_+param_.length()));
            } else {
                String err_ = transform(StringList.join(errs_,"\n\n"));
                _parts.add(new PartOffset("<a name=\"m"+off_+"\" title=\""+err_+"\" class=\"e\">",off_));
                _parts.add(new PartOffset("</a>",off_+param_.length()));
            }
        }
    }
    private static void processOverridableBlockReport(VariablesOffsets _vars,OverridableBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_vars,_cond,_cont,_parts);
        int begName_ = _cond.getNameOffset();
        if ( _cond.getKind() == MethodKind.OPERATOR) {
            addNameParts(_cond,_parts, begName_, _cond.getName().length());
            _parts.addAllElts( _cond.getPartOffsetsReturn());
            refParams(_vars,_cond,_cont, _parts);
            return;
        }
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        if (_cond.getKind() == MethodKind.GET_INDEX) {
            addNameParts(_cond,_parts, begName_, _cont.getKeyWords().getKeyWordThis().length());
            refParams(_vars,_cond,_cont, _parts);
            return;
        }
        if (_cond.getKind() == MethodKind.SET_INDEX) {
            addNameParts(_cond,_parts, begName_, _cont.getKeyWords().getKeyWordThis().length());
            refParams(_vars,_cond,_cont, _parts);
            return;
        }
        addNameParts(_cond,_parts, begName_, _cond.getName().length());
        refParams(_vars,_cond,_cont, _parts);
    }


    private static void processOverridableBlockError(VariablesOffsets _vars,OverridableBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_vars,_cond,_cont,_parts);
        int begName_ = _cond.getNameOffset();
        if ( _cond.getKind() == MethodKind.OPERATOR) {
            addNameParts(_cond,_parts, begName_, _cond.getName().length());
            _parts.addAllElts( _cond.getPartOffsetsReturn());
            refParamsError(_vars,_cond,_cont, _parts);
            return;
        }
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        if (_cond.getKind() == MethodKind.GET_INDEX) {
            addNameParts(_cond,_parts, begName_, _cont.getKeyWords().getKeyWordThis().length());
            refParamsError(_vars,_cond,_cont, _parts);
            return;
        }
        if (_cond.getKind() == MethodKind.SET_INDEX) {
            addNameParts(_cond,_parts, begName_, _cont.getKeyWords().getKeyWordThis().length());
            refParamsError(_vars,_cond,_cont, _parts);
            return;
        }
        addNameParts(_cond,_parts, begName_, _cond.getName().length());
        refParamsError(_vars,_cond,_cont, _parts);
    }

    private static void processAnnotationMethodBlockReport(VariablesOffsets _vars,AnnotationMethodBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_vars,_cond,_cont,_parts);
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        int begName_ = _cond.getNameOffset();
        addNameParts(_cond,_parts, begName_, _cond.getName().length());
        if (_cond.getRoot() != null) {
            int blOffset_ = _cond.getDefaultValueOffset();
            int endBl_ = blOffset_ + _cond.getDefaultValue().length();
            buildCoverageReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),endBl_,_parts);
        }
    }

    private static void processAnnotationMethodBlockError(VariablesOffsets _vars,AnnotationMethodBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsError(_vars,_cond,_cont,_parts);
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        if (_cond.getName().trim().isEmpty()) {
            int begName_ = _cond.getNameOffset()+ _cond.getRightPar();
            addNameParts(_cond, _parts, begName_,0);
        } else {
            int begName_ = _cond.getNameOffset();
            addNameParts(_cond, _parts, begName_, _cond.getName().length());
        }
        if (_cond.getRoot() != null) {
            int blOffset_ = _cond.getDefaultValueOffset();
            buildErrorReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),_parts);
        }
    }
    private static void processOperatorBlockReport(VariablesOffsets _vars,OperatorBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_vars,_cond,_cont,_parts);
        int begName_ = _cond.getNameOffset();
        addNameParts(_cond,_parts, begName_, _cond.getName().length());
        int lenImp_ = _cond.getImports().size();
        for (int i = 0; i < lenImp_; i++) {
            _parts.add(new PartOffset("<span class=\"i\">", _cond.getImportsOffset().get(i)));
            _parts.add(new PartOffset("</span>", _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            buildAnnotationsReport(_vars,_cond,i,_cont,_parts);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            _parts.add(new PartOffset("</a>",off_+param_.length()));
        }
    }
    private static void processOperatorBlockError(VariablesOffsets _vars,OperatorBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsError(_vars,_cond,_cont,_parts);
        int begName_ = _cond.getNameOffset();
        addNameParts(_cond,_parts, begName_, _cond.getName().length());
        int lenImp_ = _cond.getImports().size();
        for (int i = 0; i < lenImp_; i++) {
            _parts.add(new PartOffset("<span class=\"i\">", _cond.getImportsOffset().get(i)));
            _parts.add(new PartOffset("</span>", _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            buildAnnotationsError(_vars,_cond,i,_cont,_parts);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            StringList errs_ = _cond.getParamErrors().get(i);
            if (errs_.isEmpty()) {
                _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
                _parts.add(new PartOffset("</a>",off_+param_.length()));
            } else {
                String err_ = transform(StringList.join(errs_,"\n\n"));
                _parts.add(new PartOffset("<a name=\"m"+off_+"\" title=\""+err_+"\" class=\"e\">",off_));
                _parts.add(new PartOffset("</a>",off_+Math.max(param_.length(),1)));
            }
        }
    }
    private static void addNameParts(NamedFunctionBlock _named,CustList<PartOffset> _parts, int begName_, int _len) {
        StringList errs_ = _named.getNameErrors();
        if (errs_.isEmpty()) {
            int endName_ = begName_ + _len;
            _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
            _parts.add(new PartOffset("</a>",endName_));
            return;
        }
        String err_ = transform(StringList.join(errs_,"\n\n"));
        int endName_ = begName_ + Math.max(_len,1);
        _parts.add(new PartOffset("<a name=\"m"+begName_+"\" title=\""+err_+"\" class=\"e\">",begName_));
        _parts.add(new PartOffset("</a>",endName_));
    }
    private static void processInnerElementBlockReport(VariablesOffsets _vars, InnerElementBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        processAnnotationReport(_vars,_cond,_cont,_parts);
        AffectationOperation root_ = (AffectationOperation) _cond.getRoot();
        StandardInstancingOperation inst_ = (StandardInstancingOperation) root_.getFirstChild().getNextSibling();
        String cl_ = inst_.getClassName();
        cl_ = StringExpUtil.getIdFromAllTypes(cl_);
        ConstructorId c_ = inst_.getConstId();
        GeneType type_ = _cont.getClassBody(cl_);
        String file_ = ((ExecRootBlock) type_).getFile().getFileName();
        String fileName_ = _vars.getCurrentFileName();
        CustList<ExecConstructorBlock> ctors_ = ExecBlock.getConstructorBodiesById(_cont, cl_, c_);

        if (!ctors_.isEmpty()) {
            ExecConstructorBlock ctor_ = ctors_.first();
            String rel_ = relativize(fileName_,file_+".html#m"+ctor_.getNameOffset());
            String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\" title=\""+ transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\">";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() +_cond.getUniqueFieldName().length()));
        } else {
            String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\">";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() +_cond.getUniqueFieldName().length()));
        }
        _parts.addAllElts(_cond.getTypePartOffsets());
        int blOffset_ = _cond.getValueOffest();
        int endBl_ = _cond.getValueOffest() + _cond.getValue().length();
        buildCoverageReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),endBl_,_parts, _cond.getTrOffset() -1,_cond.getUniqueFieldName(),false);
    }
    private static void processInnerElementBlockError(VariablesOffsets _vars,InnerElementBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        processAnnotationError(_vars,_cond,_cont,_parts);
        AffectationOperation root_ = (AffectationOperation) _cond.getRoot();
        StandardInstancingOperation inst_ = (StandardInstancingOperation) root_.getFirstChild().getNextSibling();
        String cl_ = inst_.getClassName();
        cl_ = StringExpUtil.getIdFromAllTypes(cl_);
        ConstructorId c_ = inst_.getConstId();
        GeneType type_ = _cont.getClassBody(cl_);
        String fileName_ = _vars.getCurrentFileName();
        CustList<ExecConstructorBlock> ctors_ = ExecBlock.getConstructorBodiesById(_cont, cl_, c_);
        StringList list_ = new StringList(_cond.getNameErrors());
        list_.addAllElts(inst_.getErrs());
        String err_="";
        if (!list_.isEmpty()) {
            err_ = LinkageUtil.transform(StringList.join(list_,"\n\n"));
        }

        if (!ctors_.isEmpty()) {
            String file_ = ((ExecRootBlock) type_).getFile().getFileName();
            ExecConstructorBlock ctor_ = ctors_.first();
            String rel_ = relativize(fileName_,file_+".html#m"+ctor_.getNameOffset());
            if (!list_.isEmpty()) {
                String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\" title=\""+err_+"\n\n"+ transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            } else {
                String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\" title=\""+ transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            }
            String tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() +_cond.getUniqueFieldName().length()));
        } else {
            if (!list_.isEmpty()) {
                String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\" title=\""+err_+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            } else {
                String tag_ = "<a name=\"m"+ _cond.getFieldNameOffest() +"\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
            }
            String tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() +_cond.getUniqueFieldName().length()));
        }
        _parts.addAllElts(_cond.getTypePartOffsets());
        int blOffset_ = _cond.getValueOffest();
        buildErrorReport(_cont,_vars,blOffset_,_cond,_cond.getRoot(),_parts,_cond.getTrOffset() -1,_cond.getUniqueFieldName());
    }

    private static void processRootBlockReport(VariablesOffsets _vars,RootBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        processAnnotationReport(_vars,_cond,_cont, _parts);
        int len_ = _cond.getImports().size();
        for (int i = 0; i < len_; i++) {
            _parts.add(new PartOffset("<span class=\"i\">", _cond.getImportsOffset().get(i)));
            _parts.add(new PartOffset("</span>", _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
        processInterfaceInit(_cond, _parts);
        _parts.add(new PartOffset("<a name=\"m"+ _cond.getIdRowCol() +"\">", _cond.getIdRowCol()));
        _parts.add(new PartOffset("</a>", _cond.getIdRowCol() + _cond.getNameLength()));
        for (PartOffset p: _cond.getConstraintsParts()) {
            _parts.add(p);
        }

        for (PartOffset p: _cond.getSuperTypesParts()) {
            _parts.add(p);
        }
    }

    private static void processRootBlockError(VariablesOffsets _vars,RootBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        processAnnotationError(_vars,_cond,_cont, _parts);
        if (_cond.isReachableError()) {
            StringList listCat_ = _cond.getErrorsBlock();
            _parts.add(new PartOffset("<a title=\""+ LinkageUtil.transform(StringList.join(listCat_,"\n\n"))+"\" class=\"e\">", _cond.getBegin()));
            _parts.add(new PartOffset("</a>", _cond.getBegin() + _cond.getLengthHeader()));

        }
        int len_ = _cond.getImports().size();
        for (int i = 0; i < len_; i++) {
            _parts.add(new PartOffset("<span class=\"i\">", _cond.getImportsOffset().get(i)));
            _parts.add(new PartOffset("</span>", _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
        processInterfaceInit(_cond, _parts);
        StringList list_ = _cond.getNameErrors();
        if (!list_.isEmpty()) {
            _parts.add(new PartOffset("<a name=\"m"+ _cond.getIdRowCol() +"\" title=\""+ LinkageUtil.transform(StringList.join(list_,"\n\n"))+"\" class=\"e\">", _cond.getIdRowCol()));
        } else {
            _parts.add(new PartOffset("<a name=\"m"+ _cond.getIdRowCol() +"\">", _cond.getIdRowCol()));
        }
        _parts.add(new PartOffset("</a>", _cond.getIdRowCol() + _cond.getNameLength()));
        for (PartOffset p: _cond.getConstraintsParts()) {
            _parts.add(p);
        }

        for (PartOffset p: _cond.getSuperTypesParts()) {
            _parts.add(p);
        }
    }

    private static void processInterfaceInit(RootBlock _cond, CustList<PartOffset> _parts) {
        for (PartOffset p: _cond.getPartsStaticInitInterfacesOffset()) {
            _parts.add(p);
        }
    }

    private static void processFileBlockReport(FileBlock _cond, CustList<PartOffset> _parts) {
        int len_ = _cond.getImports().size();
        for (int i = 0; i < len_; i++) {
            _parts.add(new PartOffset("<span class=\"i\">", _cond.getImportsOffset().get(i)));
            _parts.add(new PartOffset("</span>", _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
    }
    private static void processAnnotationReport(VariablesOffsets _vars,RootBlock _cond,ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            int end_ = begin_ + _cond.getAnnotations().get(i).length();
            buildCoverageReport(_cont,_vars,begin_,_cond,_cond.getRoots().get(i),end_,_parts,0,"",true);
        }
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars,NamedFunctionBlock _cond,ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            int end_ = begin_ + _cond.getAnnotations().get(i).length();
            buildCoverageReport(_cont,_vars,begin_,_cond,_cond.getRoots().get(i),end_,_parts,0,"",true);
        }
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars,NamedFunctionBlock _cond,int _index, ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexesParams().get(_index).size();
        StringList list_ = _cond.getAnnotationsParams().get(_index);
        for (int i = 0; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexesParams().get(_index).get(i);
            int end_ = begin_ + list_.get(i).length();
            buildCoverageReport(_cont,_vars,begin_,_cond,_cond.getRootsList().get(_index).get(i),end_,_parts,0,"",true);
        }
    }
    private static void processAnnotationError(VariablesOffsets _vars,RootBlock _cond,ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            buildErrorReport(_cont,_vars,begin_,_cond,_cond.getRoots().get(i),_parts);
        }
    }
    private static void buildAnnotationsError(VariablesOffsets _vars,NamedFunctionBlock _cond,ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        for (int i = 0; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            buildErrorReport(_cont,_vars,begin_,_cond,_cond.getRoots().get(i),_parts);
        }
    }
    private static void buildAnnotationsError(VariablesOffsets _vars,NamedFunctionBlock _cond,int _index, ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexesParams().get(_index).size();
        for (int i = 0; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexesParams().get(_index).get(i);
            buildErrorReport(_cont,_vars,begin_,_cond,_cond.getRootsList().get(_index).get(i),_parts);
        }
    }
    private static void refParams(VariablesOffsets _vars,OverridableBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            buildAnnotationsReport(_vars,_cond,i,_cont,_parts);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            _parts.add(new PartOffset("</a>",off_+param_.length()));
        }
    }

    private static void refParamsError(VariablesOffsets _vars,OverridableBlock _cond, ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            buildAnnotationsError(_vars,_cond,i,_cont,_parts);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            StringList errs_ = _cond.getParamErrors().get(i);
            if (errs_.isEmpty()) {
                _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
                _parts.add(new PartOffset("</a>",off_+param_.length()));
            } else {
                String err_ = transform(StringList.join(errs_,"\n\n"));
                _parts.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",off_));
                _parts.add(new PartOffset("</a>",off_+Math.max(1,param_.length())));
            }
        }
    }
    private static void processConditionReport(Condition _cond, VariablesOffsets _vars,ContextEl _cont, CustList<PartOffset> _parts) {
        int off_ =  _cond.getConditionOffset();
        int offsetEndBlock_ = off_ + _cond.getCondition().length();
        buildCoverageReport(_cont,_vars,off_,_cond,_cond.getRoot(),offsetEndBlock_,_parts);
    }
    private static void processConditionError(Condition _cond, VariablesOffsets _vars,ContextEl _cont, CustList<PartOffset> _parts) {
        int off_ =  _cond.getConditionOffset();
        if (!_cond.getErr().isEmpty()) {
            _parts.add(new PartOffset("<a title=\""+_cond.getErr()+"\" class=\"e\">",off_));
            _parts.add(new PartOffset("</a>",off_+1));
        }
        buildErrorReport(_cont,_vars,off_,_cond,_cond.getRoot(), _parts);
    }

    public static void buildErrorReport(ContextEl _cont, VariablesOffsets _vars, int _offsetBlock,
                                        Block _block,
                                        OperationNode _nodes,
                                        CustList<PartOffset> _parts) {
        buildErrorReport(_cont,_vars,_offsetBlock,_block,_nodes,_parts,0,"");
    }

    public static void buildCoverageReport(ContextEl _cont, VariablesOffsets _vars,int _offsetBlock,
                                           Block _block,
                                           OperationNode _nodes,
                                           int _endBlock,
                                           CustList<PartOffset> _parts) {
        buildCoverageReport(_cont,_vars,_offsetBlock,_block,_nodes,_endBlock,_parts,0,"",false);
    }

    public static void buildCoverageReport(ContextEl _cont, VariablesOffsets _vars,int _offsetBlock,
                                           Block _block,
                                           OperationNode _root,
                                           int _endBlock,
                                           CustList<PartOffset> _parts, int _tr, String _fieldName, boolean _annotation) {
        OperationNode root_ = adjust(_root,_fieldName);
        int sum_ = _tr + _offsetBlock - _fieldName.length();
        String currentFileName_ = _vars.getCurrentFileName();
        boolean addCover_ = !(_block instanceof CaseCondition) && !(_block instanceof AnnotationMethodBlock) && !_annotation;
        OperationNode r_ = root_;
        OperationNode val_ = root_;
        while (true) {
            AbstractCoverageResult result_ = getCovers(_cont, _block, val_);
            getBeginOp(_cont, _block, _parts, _fieldName, r_, val_, sum_, addCover_, val_, result_);
            left(_cont,_vars,currentFileName_,_offsetBlock,_block,sum_,val_, result_,_parts, currentFileName_);
            OperationNode firstChildOp_ = val_.getFirstChild();
            if (firstChildOp_ != null) {
                val_ = firstChildOp_;
                continue;
            }
            boolean stopOp_ = false;
            while (true) {
                MethodOperation parent_ = val_.getParent();
                int offsetEnd_ = getOffsetEnd(sum_, val_, parent_);
                String tag_ = getEndTag(addCover_, val_);
                OperationNode nextSiblingOp_ = val_.getNextSibling();
                _parts.add(new PartOffset(tag_,offsetEnd_));
                processUnaryRightOperations(_cont, currentFileName_, sum_, offsetEnd_, val_,parent_, _parts);
                if (nextSiblingOp_ != null) {
                    middle(_cont,currentFileName_,_block, offsetEnd_,val_,nextSiblingOp_,
                            parent_,_parts);
                    val_=nextSiblingOp_;
                    break;
                }
                boolean st_ = end(val_,parent_, _cont, currentFileName_, offsetEnd_, _parts, r_);
                if (st_) {
                    stopOp_ = true;
                }
                if (stopOp_) {
                    getEnd(_endBlock, _parts, _tr, _fieldName, addCover_);
                    break;
                }
                val_ = parent_;
            }
            if (stopOp_) {
                break;
            }
        }
    }
    public static void buildErrorReport(ContextEl _cont, VariablesOffsets _vars, int _offsetBlock,
                                        Block _block,
                                        OperationNode _root,
                                        CustList<PartOffset> _parts, int _tr, String _fieldName) {
        if (_root == null) {
            return;
        }
        OperationNode root_ = adjust(_root,_fieldName);
        int sum_ = _tr + _offsetBlock - _fieldName.length();
        String currentFileName_ = _vars.getCurrentFileName();
        OperationNode r_ = root_;
        OperationNode val_ = root_;
        while (true) {
            leftError(_cont,_vars,currentFileName_,_offsetBlock,_block,sum_,val_, _parts, currentFileName_);
            OperationNode firstChildOp_ = val_.getFirstChild();
            if (firstChildOp_ != null) {
                val_ = firstChildOp_;
                continue;
            }
            boolean stopOp_ = false;
            while (true) {
                MethodOperation parent_ = val_.getParent();
                int offsetEnd_ = getOffsetEnd(sum_, val_, parent_);
                OperationNode nextSiblingOp_ = val_.getNextSibling();
                processUnaryRightOperations(_cont, currentFileName_, sum_, offsetEnd_, val_,parent_, _parts);
                if (nextSiblingOp_ != null) {
                    middleError(_cont,currentFileName_, offsetEnd_,val_,nextSiblingOp_,
                            parent_,_parts);
                    val_=nextSiblingOp_;
                    break;
                }
                boolean st_ = end(val_,parent_, _cont, currentFileName_, offsetEnd_, _parts, r_);
                if (st_) {
                    stopOp_ = true;
                }
                if (stopOp_) {
                    break;
                }
                val_ = parent_;
            }
            if (stopOp_) {
                break;
            }
        }
    }
    private static OperationNode adjust(OperationNode _root, String _fieldName) {
        OperationNode root_ = _root;
        if (!_fieldName.isEmpty()) {
            root_ = root_.getFirstChild().getNextSibling();
        }
        return root_;
    }
    private static boolean end(OperationNode _cur,MethodOperation parent_, ContextEl _cont, String currentFileName_, int offsetEnd_, CustList<PartOffset> _parts, OperationNode r_) {
        boolean stopOp_ = false;
        if (parent_ == null) {
            stopOp_ = true;
        } else {
            right(_cont,currentFileName_, offsetEnd_, parent_,_parts);
            if (parent_ == r_) {
                stopOp_ = true;
            }
        }
        return stopOp_;
    }
    private static void getBeginOp(ContextEl _cont, Block _block, CustList<PartOffset> _parts, String _fieldName, OperationNode root_, OperationNode curOp_, int sum_, boolean addCover_, OperationNode val_, AbstractCoverageResult result_) {
        if (curOp_ != root_ || _fieldName.isEmpty()) {
            String tag_ = getBegin(_cont, _block, root_, curOp_, addCover_, val_, result_);
            _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
        }
    }

    private static void getEnd(int _endBlock, CustList<PartOffset> _parts, int _tr, String _fieldName, boolean addCover_) {
        String tag_ = "</span>";
        if (addCover_ && _fieldName.isEmpty()) {
            _parts.add(new PartOffset(tag_,_endBlock+_tr));
        }
    }

    private static String getEndTag(boolean addCover_, OperationNode val_) {
        String tag_;
        if (!addCover_ || val_ instanceof StaticInitOperation || val_.getParent() == null) {
            tag_ = "";
        } else {
            tag_ = "</span>";
        }
        return tag_;
    }

    private static String getBegin(ContextEl _cont, Block _block, OperationNode root_, OperationNode curOp_, boolean addCover_, OperationNode val_, AbstractCoverageResult result_) {
        String tag_;
        if (!addCover_ ||val_ instanceof StaticInitOperation) {
            tag_ = "";
            return tag_;
        }
        if (val_.getArgument() != null) {
            OperationNode par_ = curOp_;
            while (par_ != root_) {
                if (par_.getArgument() == null) {
                    break;
                }
                par_ = par_.getParent();
            }
            AbstractCoverageResult resultPar_ = getCovers(_cont, _block, par_);
            if (resultPar_.isPartialCovered()) {
                tag_ = getFullInit(resultPar_);
                return tag_;
            }
            tag_ = "<span class=\"n\">";
            return tag_;
        }
        if (result_.isFullCovered()) {
            tag_ = getFullInit(result_);
            return tag_;
        }
        if (result_.isPartialCovered()) {
            return getPartialInit(val_, result_);
        }
        tag_ = "<span class=\"n\">";
        return tag_;
    }

    private static String getPartialInit(OperationNode val_, AbstractCoverageResult result_) {
        String tag_;
        if (val_ instanceof AffectationOperation && val_.getFirstChild().getNextSibling().getArgument() != null) {
            tag_ = getFullInit(result_);
            return tag_;
        }
        if (result_.isInit()) {
            tag_ = "<span class=\"q\">";
            return tag_;
        }
        tag_ = "<span class=\"p\">";
        return tag_;
    }

    private static void left(ContextEl _cont,
                             VariablesOffsets _vars,
                             String currentFileName_,
                             int _offsetBlock,
                             Block _block,
                             int sum_,
                             OperationNode val_,
                             AbstractCoverageResult result_,
                             CustList<PartOffset> _parts, String _currentFileName) {
        processNamedFct(_cont, currentFileName_, sum_, val_, _parts);
        processVariables(_cont, _vars,_offsetBlock, _block, sum_, val_, _parts);
        processConstants(sum_, val_, _parts);
        processIndexerValue(_cont, sum_, val_, _parts);
        processAssociation(_cont, sum_, val_, _parts, _currentFileName);
        processFields(_cont, _block, sum_, val_, _parts, _currentFileName);
        processInstances(_cont, currentFileName_, sum_, val_, _parts);
        processLamba(_cont, currentFileName_, sum_, val_, _parts, _currentFileName);
        processLeafType(val_, _parts);
        processDynamicCall(_cont, sum_, val_, _parts);
        processRichHeader(_cont, currentFileName_, sum_, val_, _parts);
        processUnaryLeftOperationsCovers(_cont, sum_, val_, result_, _parts);
        processUnaryLeftOperationsLinks(_cont, currentFileName_, sum_, val_, _parts);
        processLeftIndexer(_cont, currentFileName_, sum_, val_, _parts);
        processArrLength(sum_, val_, _parts);
    }

    private static void leftError(ContextEl _cont,
                                  VariablesOffsets _vars,
                                  String currentFileName_,
                                  int _offsetBlock,
                                  Block _block,
                                  int sum_,
                                  OperationNode val_,
                                  CustList<PartOffset> _parts, String _currentFileName) {
        MethodOperation par_ = val_.getParent();
        if (par_ == null) {
            StringList errEmpt_ = new StringList();
            MethodOperation.processEmptyError(val_,errEmpt_);
            if (!errEmpt_.isEmpty()) {
                int off_ = val_.getOperations().getOffset();
                int begin_ = sum_ + off_ + val_.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+transform(StringList.join(errEmpt_,"\n\n"))+"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+val_.getOperations().getDelimiter().getLength()));
            }
        } else {
            StringList l_ = new StringList();
            if (MethodOperation.isEmptyError(val_)) {
                l_ = val_.getErrs();
            }
            if (!(par_ instanceof AbstractUnaryOperation)
                    && !(val_.getIndexChild() == 0 && par_ instanceof ArrOperation)
                    && !(par_ instanceof AbstractDotOperation && par_.getOperations().getOperators().firstValue().isEmpty())
                    && !l_.isEmpty()) {
                int index_ = val_.getIndexChild();
                IntTreeMap<String> operators_ =  par_.getOperations().getOperators();
                IntTreeMap<String> values_ =  par_.getOperations().getValues();
                int s_;
                int len_;
                if (operators_.firstKey() > values_.firstKey()) {
                    s_ = sum_ + par_.getIndexInEl() + operators_.getKey(index_-1);
                    len_ = operators_.getValue(index_-1).length();
                } else {
                    s_ = sum_ + par_.getIndexInEl() + operators_.getKey(index_);
                    len_ = operators_.getValue(index_).length();
                }
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(l_,"\n\n")) +"\" class=\"e\">",s_));
                _parts.add(new PartOffset("</a>",s_+Math.max(len_,1)));
            } else {
                CustList<CustList<PartOffset>> partOffsetsChildren_ = par_.getPartOffsetsChildren();
                if (partOffsetsChildren_.isValidIndex(val_.getIndexChild())) {
                    _parts.addAllElts(partOffsetsChildren_.get(val_.getIndexChild()));
                }
            }
        }
        if (val_ instanceof BadTernaryOperation) {
            BadTernaryOperation b_ = (BadTernaryOperation) val_;
            _parts.addAllElts(b_.getPartOffsetsEnd());
        }
        if (val_ instanceof InferArrayInstancing) {
            InferArrayInstancing i_ = (InferArrayInstancing) val_;
            _parts.addAllElts(i_.getPartOffsetsErr());
        }
        processNamedFct(_cont, currentFileName_, sum_, val_, _parts);
        processVariables(_cont, _vars,_offsetBlock, _block, sum_, val_, _parts);
        processConstants(sum_, val_, _parts);
        processIndexerValue(_cont, sum_, val_, _parts);
        processAssociation(_cont, sum_, val_, _parts, _currentFileName);
        processFields(_cont, _block, sum_, val_, _parts, _currentFileName);
        processInstances(_cont, currentFileName_, sum_, val_, _parts);
        processLamba(_cont, currentFileName_, sum_, val_, _parts, _currentFileName);
        processLeafType(val_, _parts);
        processDynamicCall(_cont, sum_, val_, _parts);
        processRichHeader(_cont, currentFileName_, sum_, val_, _parts);
        processUnaryLeftOperationsLinks(_cont, currentFileName_, sum_, val_, _parts);
        processLeftIndexer(_cont, currentFileName_, sum_, val_, _parts);
        if (val_ instanceof AnnotationInstanceOperation&&val_.getFirstChild() == null) {
            _parts.addAllElts(((AnnotationInstanceOperation)val_).getPartOffsetsEnd());
        }
        processArrLength(sum_, val_, _parts);
        if (val_.getParent() instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) val_.getParent();
            if (!c_.getSepErr().isEmpty()&&c_.getIndexCh() == val_.getIndexChild()) {
                String tag_ = "<a title=\""+transform(c_.getSepErr())+"\" class=\"e\">";
                int rightPar_ = c_.getOperations().getOperators().getKey(c_.getIndexCh()+1);
                _parts.add(new PartOffset(tag_,sum_ + c_.getIndexInEl()+rightPar_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,sum_ + c_.getIndexInEl()+rightPar_+1));
            }
        }
    }

    private static void processArrLength(int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof ArrayFieldOperation) {
            ArrayFieldOperation aField_ = (ArrayFieldOperation) val_;
            OperationsSequence op_ = aField_.getOperations();
            int relativeOff_ = op_.getOffset();
            String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            int begin_ = sum_ + relativeOff_ + val_.getIndexInEl();
            if (!aField_.getErrs().isEmpty()) {
                _parts.add(new PartOffset("<a title=\""+transform(StringList.join(aField_.getErrs(),"\n\n"))+"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+str_.length()));
            } else {
                _parts.add(new PartOffset("<b>",begin_));
                _parts.add(new PartOffset("</b>",begin_+str_.length()));
            }
        }
    }

    private static void processConstants(int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof ConstantOperation) {
            if (val_.getOperations().getConstType() == ConstType.STRING) {
                int off_ = val_.getOperations().getOffset();
                String tag_ = "<span class=\"s\">";
                int begin_ = sum_ + off_ + val_.getIndexInEl();
                _parts.add(new PartOffset(tag_, begin_));
                tag_ = "</span>";
                _parts.add(new PartOffset(tag_,begin_+ ((ConstantOperation)val_).getLength()));
            }
            if (val_.getOperations().getConstType() == ConstType.CHARACTER) {
                int off_ = val_.getOperations().getOffset();
                String tag_ = "<span class=\"s\">";
                int begin_ = sum_ + off_ + val_.getIndexInEl();
                _parts.add(new PartOffset(tag_,begin_));
                tag_ = "</span>";
                _parts.add(new PartOffset(tag_,begin_+ ((ConstantOperation)val_).getLength()));
            }
        }
    }

    private static void processDynamicCall(ContextEl _cont, int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof CallDynMethodOperation) {
            if (val_.getErrs().isEmpty()) {
                String tag_ = "<b>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
                tag_ = "</b>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+_cont.getStandards().getAliasCall().length()));
            } else {
                String fctName_ = val_.getOperations().getFctName().trim();
                String tag_ = "<a title=\""+transform(StringList.join(val_.getErrs(),"\n\n"))+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+fctName_.length()));
            }
            if (((CallDynMethodOperation) val_).isNoNeed()) {
                String tag_ = "<a title=\""+transform(((CallDynMethodOperation) val_).getSepErr())+"\" class=\"e\">";
                int leftPar_ = val_.getOperations().getOperators().firstKey();
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+leftPar_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+leftPar_+1));
            }
            if (val_.getFirstChild() == null&&!((CallDynMethodOperation) val_).getSepErr().isEmpty()) {
                String tag_ = "<a title=\""+transform(((CallDynMethodOperation) val_).getSepErr())+"\" class=\"e\">";
                int rightPar_ = val_.getOperations().getOperators().lastKey();
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+rightPar_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+rightPar_+1));
            }
        }
    }

    private static void processLeftIndexer(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof ArrOperation) {
            ArrOperation par_ = (ArrOperation) val_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            if (classMethodId_ != null) {
                String className_ = classMethodId_.getClassName();
                className_ = StringExpUtil.getIdFromAllTypes(className_);
                MethodId methodId_ = classMethodId_.getConstraints();
                MethodId id_;
                if (par_.isVariable()) {
                    id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                } else {
                    id_ = new MethodId(MethodAccessKind.INSTANCE,"[]",methodId_.getParametersTypes(),methodId_.isVararg());
                }
                int offsetEnd_ = sum_ + val_.getIndexInEl();
                addParts(_cont,currentFileName_,className_,id_,offsetEnd_,1,val_.getErrs(),_parts);
            } else if (!val_.getErrs().isEmpty()) {
                int i_ = sum_ + val_.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(val_.getErrs(),"\n\n")) +"\" class=\"e\">",i_));
                _parts.add(new PartOffset("</a>",i_+1));
            }
        }
    }


    private static void processAssociation(ContextEl _cont, int sum_, OperationNode val_, CustList<PartOffset> _parts, String _currentFileName) {
        if (val_ instanceof AssocationOperation) {
            AssocationOperation a_ = (AssocationOperation) val_;
            String fieldName_ = a_.getFieldName();
            String annotation_ = a_.getAnnotation();
            ClassField c_ = new ClassField(annotation_,fieldName_);
            int delta_ = a_.getSum();
            String className_ = c_.getClassName();
            className_ = StringExpUtil.getIdFromAllTypes(className_);
            addParts(_cont, _currentFileName,className_,
                    new MethodId(MethodAccessKind.INSTANCE,c_.getFieldName(),new StringList()),
                    sum_ +delta_+ val_.getIndexInEl(),fieldName_.length(),val_.getErrs(),_parts
            );
            if (!a_.getErrAff().isEmpty()) {
                int begin_ = sum_ + a_.getOffEq() + val_.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+transform(a_.getErrAff())+"\" class=\"e\">", begin_));
                _parts.add(new PartOffset("</a>", begin_+1));
            }
        }
    }

    private static void processIndexerValue(ContextEl _cont, int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof ValueOperation) {
            int delta_ = ((ValueOperation) val_).getOff();
            String tag_ = "<b>";
            _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+_cont.getKeyWords().getKeyWordValue().length()));
        }
    }

    private static void processNamedFct(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof FctOperation||val_ instanceof ChoiceFctOperation ||val_ instanceof SuperFctOperation) {
            if (val_ instanceof FctOperation) {
                _parts.addAllElts(((FctOperation)val_).getPartOffsets());
                int delta_ = ((FctOperation) val_).getDelta();
                ClassMethodId classMethodId_ = ((FctOperation) val_).getClassMethodId();
                int l_ = ((FctOperation) val_).getLengthMethod();
                if (classMethodId_ == null) {
                    addParts(_cont,currentFileName_,"",null,
                            sum_ +delta_+ val_.getIndexInEl(),l_,
                            val_.getErrs(),_parts);
                    return;
                }
                if (((FctOperation)val_).isClonedMethod()&&val_.getErrs().isEmpty()) {
                    int begin_ = sum_ +delta_+ val_.getIndexInEl();
                    String tag_;
                    tag_ = "<b>";
                    _parts.add(new PartOffset(tag_,begin_));
                    tag_ = "</b>";
                    _parts.add(new PartOffset(tag_,begin_+l_));
                } else {
                    String className_ = classMethodId_.getClassName();
                    className_ = StringExpUtil.getIdFromAllTypes(className_);
                    MethodId id_ = classMethodId_.getConstraints();
                    addParts(_cont, currentFileName_, className_, id_,
                            sum_ + delta_ + val_.getIndexInEl(), l_,
                            val_.getErrs(), _parts);
                }
            }
            if (val_ instanceof ChoiceFctOperation) {
                _parts.addAllElts(((ChoiceFctOperation)val_).getPartOffsets());
                int delta_ = ((ChoiceFctOperation) val_).getDelta();
                ClassMethodId classMethodId_ = ((ChoiceFctOperation) val_).getClassMethodId();
                int l_ = ((ChoiceFctOperation) val_).getLengthMethod();
                if (classMethodId_ == null) {
                    addParts(_cont,currentFileName_,"",null,
                            sum_ +delta_+ val_.getIndexInEl(),l_,
                            val_.getErrs(),_parts);
                    return;
                }
                String className_ = classMethodId_.getClassName();
                className_ = StringExpUtil.getIdFromAllTypes(className_);
                MethodId id_ = classMethodId_.getConstraints();
                addParts(_cont,currentFileName_,className_,id_,
                        sum_ +delta_+ val_.getIndexInEl(),l_,
                        val_.getErrs(),_parts);
            }
            if (val_ instanceof SuperFctOperation) {
                _parts.addAllElts(((SuperFctOperation)val_).getPartOffsets());
                int delta_ = ((SuperFctOperation) val_).getDelta();
                ClassMethodId classMethodId_ = ((SuperFctOperation) val_).getClassMethodId();
                int l_ = ((SuperFctOperation) val_).getLengthMethod();
                if (classMethodId_ == null) {
                    addParts(_cont,currentFileName_,"",null,
                            sum_ +delta_+ val_.getIndexInEl(),l_,
                            val_.getErrs(),_parts);
                    return;
                }
                String className_ = classMethodId_.getClassName();
                className_ = StringExpUtil.getIdFromAllTypes(className_);
                MethodId id_ = classMethodId_.getConstraints();
                addParts(_cont,currentFileName_,className_,id_,
                        sum_ +delta_+ val_.getIndexInEl(),l_,
                        val_.getErrs(),_parts);
            }
        }
        if (val_ instanceof AnnotationInstanceOperation) {
            _parts.addAllElts(((AnnotationInstanceOperation)val_).getPartOffsetsErr());
            _parts.addAllElts(((AnnotationInstanceOperation)val_).getPartOffsets());
            _parts.addAllElts(((AnnotationInstanceOperation)val_).getPartOffsetsErrPar());
        }
    }

    private static void processFields(ContextEl _cont, Block _block, int sum_, OperationNode val_, CustList<PartOffset> _parts, String _currentFileName) {
        if (val_ instanceof SettableAbstractFieldOperation) {
            int id_ = ((SettableAbstractFieldOperation)val_).getValueOffset();
            if (_block instanceof FieldBlock && ElUtil.isDeclaringVariable(val_)) {
                StringList errs_ = ((FieldBlock) _block).getAllNameErrors();
                int d_ = ((SettableAbstractFieldOperation)val_).getDelta();
                if (errs_.isEmpty()) {
                    String tag_ = "<a name=\"m"+id_+"\">";
                    _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+d_));
                } else {
                    String err_ = StringList.join(errs_,"\n\n");
                    String tag_ = "<a title=\""+err_+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+d_));
                }
                String tag_ = "</a>";
                _parts.add(new PartOffset(tag_,sum_ + val_.getIndexInEl()+d_+((SettableAbstractFieldOperation) val_).getFieldNameLength()));
            } else {
                _parts.addAllElts(((SettableAbstractFieldOperation) val_).getPartOffsets());
                ClassField c_ = ((SettableAbstractFieldOperation)val_).getFieldIdReadOnly();
                int delta_ = ((SettableAbstractFieldOperation) val_).getOff();
                updateFieldAnchor(_cont,val_.getErrs(),_parts,c_,sum_ +delta_+ val_.getIndexInEl() + ((SettableAbstractFieldOperation)val_).getDelta(),((SettableAbstractFieldOperation) val_).getFieldNameLength(), _currentFileName,id_);
            }
        }
    }

    private static void processVariables(ContextEl _cont, VariablesOffsets _vars, int _offsetBlock, Block _block, int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof VariableOperation) {
            String varName_ = ((VariableOperation) val_).getVariableName();
            int delta_ = ((VariableOperation) val_).getOff();
            if (_block instanceof Line && _block.getPreviousSibling() instanceof DeclareVariable && ElUtil.isDeclaringVariable(val_)) {
                StringList errs_ = ((VariableOperation) val_).getNameErrors();
                int id_ = ((VariableOperation) val_).getRef();
                if (!errs_.isEmpty()) {
                    String err_ = transform(StringList.join(errs_,"\n\n"));
                    String tag_ = "<a title=\""+err_+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                } else {
                    String tag_ = "<a name=\"m"+ id_ +"\">";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                }

            } else {
                int id_ = ((VariableOperation) val_).getRef();
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
            }
        }
        if (val_ instanceof MutableLoopVariableOperation) {
            String varName_ = ((MutableLoopVariableOperation) val_).getVariableName();
            int delta_ = ((MutableLoopVariableOperation) val_).getOff();
            if (_block instanceof ForMutableIterativeLoop && _vars.isPossibleDeclareLoopVars() && ElUtil.isDeclaringVariable(val_)) {
                int id_ = ((MutableLoopVariableOperation) val_).getRef();
                StringList errs_ = ((MutableLoopVariableOperation) val_).getNameErrors();
                if (!errs_.isEmpty()) {
                    String err_ = transform(StringList.join(errs_,"\n\n"));
                    String tag_ = "<a title=\""+err_+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                } else {
                    String tag_ = "<a name=\"m"+ id_ +"\">";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
                }

            } else {
                int id_ = ((MutableLoopVariableOperation) val_).getRef();
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
            }
        }
        if (val_ instanceof FinalVariableOperation) {
            String varName_ = ((FinalVariableOperation) val_).getVariableName();
            int delta_ = ((FinalVariableOperation) val_).getOff();
            ConstType type_ = ((FinalVariableOperation) val_).getType();
            if (type_ == ConstType.LOOP_INDEX) {
                int deltaLoc_ = ((FinalVariableOperation)val_).getDelta();
                int id_ = ((FinalVariableOperation) val_).getRef();
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_,deltaLoc_+delta_+sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,deltaLoc_+delta_+sum_ + val_.getIndexInEl()+varName_.length()));
            } else {
                int id_ = ((FinalVariableOperation) val_).getRef();
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,delta_+sum_ + val_.getIndexInEl()+varName_.length()));
            }
        }
    }

    private static void processInstances(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof StandardInstancingOperation) {
            String cl_ = ((StandardInstancingOperation)val_).getClassName();
            cl_ = StringExpUtil.getIdFromAllTypes(cl_);
            ConstructorId c_ = ((StandardInstancingOperation)val_).getConstId();
            StandardInstancingOperation inst_ = (StandardInstancingOperation) val_;
            if (inst_.getFieldName().isEmpty()) {
                int offsetNew_ =StringList.getFirstPrintableCharIndex(inst_.getMethodName());
                addParts(_cont,currentFileName_,cl_,c_,
                        offsetNew_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordNew().length(),
                        val_.getErrs(),_parts);
            }
            _parts.addAllElts(inst_.getPartOffsets());
        }
        if (val_ instanceof DimensionArrayInstancing) {
            _parts.addAllElts(((DimensionArrayInstancing)val_).getPartOffsets());
        }
        if (val_ instanceof ElementArrayInstancing) {
            _parts.addAllElts(((ElementArrayInstancing)val_).getPartOffsets());
            _parts.addAllElts(((ElementArrayInstancing)val_).getPartOffsetsErr());
        }
    }

    private static void processLeafType(OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof IdFctOperation) {
            _parts.addAllElts(((IdFctOperation)val_).getPartOffsets());
        }
        if (val_ instanceof VarargOperation) {
            _parts.addAllElts(((VarargOperation)val_).getPartOffsets());
        }
        if (val_ instanceof ForwardOperation) {
            _parts.addAllElts(((ForwardOperation)val_).getPartOffsets());
        }
        if (val_ instanceof DefaultValueOperation) {
            _parts.addAllElts(((DefaultValueOperation)val_).getPartOffsets());
        }
        if (val_ instanceof StaticInfoOperation) {
            _parts.addAllElts(((StaticInfoOperation)val_).getPartOffsets());
        }
        if (val_ instanceof StaticAccessOperation) {
            _parts.addAllElts(((StaticAccessOperation)val_).getPartOffsets());
        }
        if (val_ instanceof StaticCallAccessOperation) {
            _parts.addAllElts(((StaticCallAccessOperation)val_).getPartOffsets());
        }
    }

    private static void processLamba(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, CustList<PartOffset> _parts, String _currentFileName) {
        if (!(val_ instanceof LambdaOperation)) {
            return;
        }
        ClassMethodId classMethodId_ = ((LambdaOperation) val_).getMethod();
        ConstructorId realId_ = ((LambdaOperation) val_).getRealId();
        ClassField fieldId_ = ((LambdaOperation) val_).getFieldId();
        int off_ = ((LambdaOperation)val_).getClassNameOffset();
        if (classMethodId_ != null) {
            String className_ = classMethodId_.getClassName();
            className_ = StringExpUtil.getIdFromAllTypes(className_);
            MethodId id_ = classMethodId_.getConstraints();
            addParts(_cont,currentFileName_,className_,id_,
                    off_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordLambda().length(),
                    val_.getErrs(),_parts);
        } else if (realId_ != null) {
            String cl_ = ((LambdaOperation) val_).getFoundClass();
            cl_ = StringExpUtil.getIdFromAllTypes(cl_);
            addParts(_cont,currentFileName_,cl_,realId_,
                    off_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordLambda().length(),
                    val_.getErrs(),_parts);
        } else {
            updateFieldAnchor(_cont,val_.getErrs(),_parts,fieldId_,off_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordLambda().length(), _currentFileName,((LambdaOperation) val_).getValueOffset());
        }
        _parts.addAllElts(((LambdaOperation)val_).getPartOffsets());
    }

    private static void processRichHeader(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof EnumValueOfOperation) {
            if (!val_.getErrs().isEmpty()) {
                int begin_ = sum_ + val_.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(val_.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _cont.getKeyWords().getKeyWordValueOf().length()));
            }
            _parts.addAllElts(((EnumValueOfOperation)val_).getPartOffsets());
        }
        if (val_ instanceof ValuesOperation) {
            _parts.addAllElts(((ValuesOperation)val_).getPartOffsets());
        }
        if (val_ instanceof AbstractInvokingConstructor) {
            if (val_ instanceof InterfaceInvokingConstructor) {
                ConstructorId c_ = ((AbstractInvokingConstructor)val_).getConstId();
                if (c_ == null) {
                    addParts(_cont,currentFileName_,"",null,
                            sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordInterfaces().length(),
                            val_.getErrs(),_parts);
                } else {
                    String cl_ = c_.getName();
                    cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                    addParts(_cont,currentFileName_,cl_,c_,
                            sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordInterfaces().length(),
                            val_.getErrs(),_parts);
                }
                _parts.addAllElts(((InterfaceInvokingConstructor)val_).getPartOffsets());
            } else if (val_ instanceof InterfaceFctConstructor) {
                ConstructorId c_ = ((AbstractInvokingConstructor)val_).getConstId();
                if (c_ == null) {
                    addParts(_cont,currentFileName_,"",null,
                            sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordInterfaces().length(),
                            val_.getErrs(),_parts);
                } else {
                    String cl_ = c_.getName();
                    cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                    addParts(_cont,currentFileName_,cl_,c_,
                            sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordInterfaces().length(),
                            val_.getErrs(),_parts);
                }
                _parts.addAllElts(((InterfaceFctConstructor)val_).getPartOffsets());
            } else{
                ConstructorId c_ = ((AbstractInvokingConstructor) val_).getConstId();
                if (c_ == null) {
                    addParts(_cont, currentFileName_, "", null,
                            sum_ + val_.getIndexInEl(), ((AbstractInvokingConstructor) val_).getOffsetOper(),
                            val_.getErrs(),_parts);
                } else {
                    String cl_ = c_.getName();
                    cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                    addParts(_cont, currentFileName_, cl_, c_,
                            sum_ + val_.getIndexInEl(), ((AbstractInvokingConstructor) val_).getOffsetOper(),
                            val_.getErrs(),_parts);
                }
            }
        }
        if (val_ instanceof ExplicitOperatorOperation) {
            ExplicitOperatorOperation par_ = (ExplicitOperatorOperation) val_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            MethodId id_ = classMethodId_.getConstraints();
            addParts(_cont,currentFileName_,classMethodId_.getClassName(),id_,
                    sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordOperator().length(),
                    val_.getErrs(),_parts);
            _parts.addAllElts(par_.getPartOffsets());
        }
    }

    private static void processUnaryLeftOperationsCovers(ContextEl _cont, int sum_, OperationNode val_, AbstractCoverageResult result_, CustList<PartOffset> _parts) {
        if (val_ instanceof UnaryBooleanOperation && ((UnaryBooleanOperation)val_).getClassMethodId() == null && result_.isStrictPartialCovered()) {
            int offsetOp_ = val_.getOperations().getOperators().firstKey();
            safe(result_,_cont,sum_ + val_.getIndexInEl() + offsetOp_,_parts,1);
        }
    }

    private static void processUnaryLeftOperationsLinks(ContextEl _cont, String currentFileName_, int sum_, OperationNode val_, CustList<PartOffset> _parts) {
        if (val_ instanceof SymbolOperation && val_.getFirstChild().getNextSibling() == null) {
            SymbolOperation par_ = (SymbolOperation) val_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            if (classMethodId_ != null) {
                MethodId id_ = classMethodId_.getConstraints();
                addParts(_cont, currentFileName_, classMethodId_.getClassName(), id_,
                        sum_ + val_.getIndexInEl() + par_.getOpOffset(), id_.getName().length(),
                        val_.getErrs(),_parts);
            }
        }
        if (val_ instanceof CastOperation) {
            int l_;
            int i_ = sum_ + val_.getIndexInEl();
            if (((CastOperation)val_).isFound()) {
                i_ += ((CastOperation)val_).getOffset();
                l_ = 1;
            } else {
                l_ = _cont.getKeyWords().getKeyWordCast().length();
            }
            if (!val_.getErrs().isEmpty()) {
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(val_.getErrs(),"\n\n")) +"\" class=\"e\">",i_));
                _parts.add(new PartOffset("</a>",i_+l_));
            }
            _parts.addAllElts(((CastOperation)val_).getPartOffsets());
        }
        if (val_ instanceof ExplicitOperation) {
            String className_ = ((ExplicitOperation) val_).getClassName();
            int offsetOp_ = val_.getOperations().getOperators().firstKey();
            MethodId castId_ = ((ExplicitOperation) val_).getCastOpId();
            addParts(_cont,currentFileName_,StringExpUtil.getIdFromAllTypes(className_),castId_,
                    offsetOp_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordExplicit().length(),
                    val_.getErrs(),
                    _parts);
            _parts.addAllElts(((ExplicitOperation)val_).getPartOffsets());
        }
        if (val_ instanceof ImplicitOperation) {
            String className_ = ((ImplicitOperation) val_).getClassName();
            int offsetOp_ = val_.getOperations().getOperators().firstKey();
            MethodId castId_ = ((ImplicitOperation) val_).getCastOpId();
            addParts(_cont,currentFileName_,StringExpUtil.getIdFromAllTypes(className_),castId_,
                    offsetOp_+sum_ + val_.getIndexInEl(),_cont.getKeyWords().getKeyWordCast().length(),
                    val_.getErrs(),
                    _parts);
            _parts.addAllElts(((ImplicitOperation)val_).getPartOffsets());
        }
        if (val_ instanceof SemiAffectationOperation) {
            SemiAffectationOperation par_ = (SemiAffectationOperation) val_;
            int offsetOp_ = val_.getOperations().getOperators().firstKey();
            if(!par_.isPost()) {
                boolean err_ = true;
                ClassMethodId classMethodId_ = par_.getClassMethodId();
                if (classMethodId_ != null) {
                    MethodId id_ = classMethodId_.getConstraints();
                    addParts(_cont,currentFileName_,"",id_,
                            sum_ + val_.getIndexInEl()+offsetOp_,1,
                            val_.getErrs(),_parts);
                    err_ = false;
                }
                SettableElResult settable_ = par_.getSettable();
                if (settable_ instanceof ArrOperation && ((ArrOperation) settable_).getClassMethodId() != null) {
                    ArrOperation parArr_ = (ArrOperation) par_.getSettable();
                    ClassMethodId classMethodIdArr_ = parArr_.getClassMethodId();
                    String className_ = classMethodIdArr_.getClassName();
                    className_ = StringExpUtil.getIdFromAllTypes(className_);
                    MethodId methodId_ = classMethodIdArr_.getConstraints();
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                    addParts(_cont,currentFileName_,className_,id_,
                            sum_ + val_.getIndexInEl()+offsetOp_+1,1,
                            val_.getErrs(),_parts);
                    err_ = false;
                }
                if (err_) {
                    if (!par_.getErrs().isEmpty()) {
                        int begin_ = par_.getOpOffset()+sum_ + par_.getIndexInEl();
                        _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(par_.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                        _parts.add(new PartOffset("</a>",begin_+ 2));
                    }
                }
            }
        }
        if (val_ instanceof IdOperation) {
            if (!val_.getErrs().isEmpty()) {
                int begin_ = sum_ + val_.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(val_.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ 1));
            }
        }
        if (val_ instanceof FirstOptOperation) {
            if (!val_.getErrs().isEmpty()) {
                int begin_ = sum_ + val_.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(val_.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _cont.getKeyWords().getKeyWordFirstopt().length()));
            }
        }
        if (val_ instanceof DefaultOperation) {
            if (!val_.getErrs().isEmpty()) {
                int begin_ = sum_ + val_.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(val_.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _cont.getKeyWords().getKeyWordDefault().length()));
            }
        }
    }

    private static int getOffsetEnd(int sum_, OperationNode val_, MethodOperation parent_) {
        int offsetEnd_ = 0;
        if (parent_ != null) {
            int indexChild_ = val_.getIndexChild();
            if (parent_ instanceof StandardInstancingOperation && parent_.getFirstChild() instanceof StaticInitOperation) {
                indexChild_--;
            }
            IntTreeMap<String> children_ = parent_.getChildren();
            if (indexChild_ > -1) {
                offsetEnd_ = sum_ + val_.getIndexInEl() + children_.getValue(indexChild_).length();
            } else {
                offsetEnd_ = sum_ + val_.getIndexInEl();
            }
        }
        return offsetEnd_;
    }

    private static void middle(ContextEl _cont,
                               String currentFileName_,
                               Block _block,
                               int offsetEnd_,
                               OperationNode curOp_,
                               OperationNode nextSiblingOp_,
                               MethodOperation parent_,
                               CustList<PartOffset> _parts) {
        processCat(_cont, offsetEnd_, curOp_, nextSiblingOp_, parent_, _parts);
        processCustomOperator(_cont, currentFileName_, offsetEnd_, parent_, _parts);
        processCompoundAffLeftOp(_cont, currentFileName_, offsetEnd_, nextSiblingOp_, parent_, _parts);
        processCompoundAffCover(_cont, _block, offsetEnd_, nextSiblingOp_, parent_, _parts);
        processCompoundAffRightOp(_cont, currentFileName_, offsetEnd_, parent_, _parts);

        processCompare(_cont, _block, offsetEnd_, parent_, _parts);
        processDotSafe(_cont, _block, offsetEnd_, curOp_, nextSiblingOp_, parent_, _parts);
        processNullSafe(_cont, _block, offsetEnd_, curOp_, nextSiblingOp_, parent_, _parts);
        processLogicAndOrOperation(_cont, _block, offsetEnd_, curOp_, nextSiblingOp_, parent_, _parts);
    }

    private static void middleError(ContextEl _cont,
                                    String currentFileName_,
                                    int offsetEnd_,
                                    OperationNode curOp_,
                                    OperationNode nextSiblingOp_,
                                    MethodOperation parent_,
                                    CustList<PartOffset> _parts) {
        if (curOp_.getIndexChild() == 0 && parent_ instanceof AbstractTernaryOperation) {
            AbstractTernaryOperation ab_ = (AbstractTernaryOperation) parent_;
            _parts.addAllElts(ab_.getPartOffsetsEnd());
        }
        processCat(_cont, offsetEnd_, curOp_, nextSiblingOp_, parent_, _parts);
        processCustomOperator(_cont, currentFileName_, offsetEnd_, parent_, _parts);
        processCompoundAffLeftOp(_cont, currentFileName_, offsetEnd_, nextSiblingOp_, parent_, _parts);
        processCompoundAffRightOp(_cont, currentFileName_, offsetEnd_, parent_, _parts);
    }
    private static void processCustomOperator(ContextEl _cont, String currentFileName_, int offsetEnd_, MethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof SymbolOperation) {
            SymbolOperation par_ = (SymbolOperation) parentOp_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            if (classMethodId_ != null) {
                MethodId id_ = classMethodId_.getConstraints();
                addParts(_cont,currentFileName_,classMethodId_.getClassName(),id_,offsetEnd_,id_.getName().length(),parentOp_.getErrs(),_parts);
            }
        }
    }

    private static void processCat(ContextEl _cont, int offsetEnd_, OperationNode curOp_, OperationNode nextSiblingOp_, MethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof AddOperation && ((AddOperation)parentOp_).isCatString() && canCallToString(_cont, curOp_, nextSiblingOp_)) {
            String tag_ = "<i>";
            _parts.add(new PartOffset(tag_, offsetEnd_));
            tag_ = "</i>";
            _parts.add(new PartOffset(tag_, offsetEnd_ + 1));
        }
    }

    private static boolean canCallToString(ContextEl _cont, OperationNode curOp_, OperationNode nextSiblingOp_) {
        return canCallToString(curOp_.getResultClass(),_cont) || canCallToString(nextSiblingOp_.getResultClass(),_cont);
    }
    private static void processDotSafe(ContextEl _cont, Block _block, int offsetEnd_, OperationNode curOp_, OperationNode nextSiblingOp_, MethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof SafeDotOperation) {
            AbstractCoverageResult resultFirst_ = getCovers(_cont, _block, curOp_);
            safe(resultFirst_,_cont,offsetEnd_,_parts, 1);
        }
    }
    private static void processNullSafe(ContextEl _cont, Block _block, int offsetEnd_, OperationNode curOp_, OperationNode nextSiblingOp_, MethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof NullSafeOperation) {
            AbstractCoverageResult resultFirst_ = getCovers(_cont, _block, curOp_);
            AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_);
            safe(resultFirst_,_cont,offsetEnd_,_parts, 1);
            safe(resultLast_,_cont,offsetEnd_+1,_parts, 1);
        }
    }

    private static void processCompare(ContextEl _cont, Block _block, int offsetEnd_, MethodOperation parent_, CustList<PartOffset> _parts) {
        if (parent_ instanceof EqOperation || parent_ instanceof CmpOperation) {
            if (((SymbolOperation)parent_).getClassMethodId() == null) {
                int length_ = ((MiddleSymbolOperation)parent_) .getOp().length();
                AbstractCoverageResult resultLoc_ = getCovers(_cont, _block, parent_);
                safe(resultLoc_,_cont,offsetEnd_,_parts,length_);
            }
        }
    }

    private static void processCompoundAffLeftOp(ContextEl _cont, String currentFileName_, int offsetEnd_, OperationNode nextSiblingOp_, MethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (!(parentOp_ instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) parentOp_;
        ClassMethodId classMethodId_ = par_.getClassMethodId();
        int opDelta_ = par_.getOper().length() - 1;
        if (classMethodId_ != null) {
            MethodId id_ = classMethodId_.getConstraints();
            addParts(_cont,currentFileName_,classMethodId_.getClassName(),id_,offsetEnd_,opDelta_,parentOp_.getErrs(),_parts);
        } else if (nextSiblingOp_.getResultClass().isConvertToString() && canCallToString(nextSiblingOp_.getResultClass(),_cont)){
            String tag_ = "<i>";
            _parts.add(new PartOffset(tag_, offsetEnd_));
            tag_ = "</i>";
            _parts.add(new PartOffset(tag_,offsetEnd_+opDelta_));
        }
    }
    private static void processCompoundAffCover(ContextEl _cont, Block _block, int offsetEnd_, OperationNode nextSiblingOp_, MethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (!(parentOp_ instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) parentOp_;
        ClassMethodId classMethodId_ = par_.getClassMethodId();
        if (classMethodId_ != null) {
            return;
        }
        if (nextSiblingOp_.getResultClass().isConvertToString() && canCallToString(nextSiblingOp_.getResultClass(),_cont)){
            return;
        }
        int opDelta_ = par_.getOper().length() - 1;
        if (StringList.quickEq(par_.getOper(),"??=")){
            AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_);
            safe(resultLast_,_cont,offsetEnd_,_parts, opDelta_);
        } else {
            String b_ = _cont.getStandards().getAliasPrimBoolean();
            if (nextSiblingOp_.getResultClass().matchClass(b_)) {
                AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_);
                safe(resultLast_,_cont,offsetEnd_,_parts,opDelta_);
            }
        }
    }
    private static void processCompoundAffRightOp(ContextEl _cont, String currentFileName_, int offsetEnd_, MethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (!(parentOp_ instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) parentOp_;
        int opDelta_ = par_.getOper().length() - 1;
        SettableElResult settable_ = par_.getSettable();
        if (settable_ instanceof ArrOperation && ((ArrOperation) settable_).getClassMethodId() != null) {
            ArrOperation parArr_ = (ArrOperation) settable_;
            ClassMethodId classMethodIdArr_ = parArr_.getClassMethodId();
            String className_ = classMethodIdArr_.getClassName();
            className_ = StringExpUtil.getIdFromAllTypes(className_);
            MethodId methodId_ = classMethodIdArr_.getConstraints();
            MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
            addParts(_cont,currentFileName_,className_,id_,opDelta_+offsetEnd_,1,parentOp_.getErrs(),_parts);
        }
    }
    private static void processLogicAndOrOperation(ContextEl _cont, Block _block, int offsetEnd_, OperationNode curOp_, OperationNode nextSiblingOp_, MethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (!(parentOp_ instanceof QuickOperation)) {
            return;
        }
        AbstractCoverageResult resultFirst_ = getCovers(_cont, _block, curOp_);
        AbstractCoverageResult resultLast_ = getCovers(_cont, _block, nextSiblingOp_);
        safe(resultFirst_,_cont,offsetEnd_,_parts,1);
        safe(resultLast_,_cont,offsetEnd_+1,_parts,1);
    }

    private static void right(ContextEl _cont,
                              String currentFileName_,
                              int offsetEnd_,
                              MethodOperation parent_,
                              CustList<PartOffset> _parts) {
        processRightIndexer(_cont, currentFileName_, offsetEnd_, parent_, _parts);
        if (parent_ instanceof AnnotationInstanceOperation
                || parent_ instanceof IdOperation) {
            _parts.addAllElts(parent_.getPartOffsetsEnd());
        }
    }

    private static void processRightIndexer(ContextEl _cont, String currentFileName_, int offsetEnd_, MethodOperation parentOp_, CustList<PartOffset> _parts) {
        if (parentOp_ instanceof ArrOperation) {
            ArrOperation par_ = (ArrOperation) parentOp_;
            ClassMethodId classMethodId_ = par_.getClassMethodId();
            if (classMethodId_ != null) {
                String className_ = classMethodId_.getClassName();
                className_ = StringExpUtil.getIdFromAllTypes(className_);
                MethodId methodId_ = classMethodId_.getConstraints();
                MethodId id_;
                if (par_.isVariable()) {
                    id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                } else {
                    id_ = new MethodId(MethodAccessKind.INSTANCE,"[]",methodId_.getParametersTypes(),methodId_.isVararg());
                }
                addParts(_cont,currentFileName_,className_,id_,offsetEnd_,1,new StringList(),_parts);
            } else {
                String er_ = par_.getNbErr();
                if (!er_.isEmpty()) {
                    addParts(_cont,currentFileName_,"",null,offsetEnd_,1,new StringList(er_),_parts);
                }
            }
        }
    }

    private static void processUnaryRightOperations(ContextEl _cont, String currentFileName_, int sum_, int offsetEnd_, OperationNode curOp_,MethodOperation parent_, CustList<PartOffset> _parts) {
        if (curOp_.getIndexChild() == 0 &&parent_ instanceof InstanceOfOperation) {
            if (!parent_.getErrs().isEmpty()) {
                int begin_ = ((InstanceOfOperation)parent_).getOffset()+sum_ + parent_.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(parent_.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+_cont.getKeyWords().getKeyWordInstanceof().length()));
            }
            _parts.addAllElts(((InstanceOfOperation)parent_).getPartOffsets());
        }
        processPostIncr(_cont, currentFileName_, sum_, offsetEnd_, curOp_,parent_, _parts);
    }

    private static void processPostIncr(ContextEl _cont, String currentFileName_, int sum_, int offsetEnd_,  OperationNode curOp_,MethodOperation parent_, CustList<PartOffset> _parts) {
        if (curOp_.getIndexChild() == 0&&parent_ instanceof SemiAffectationOperation) {
            SemiAffectationOperation par_ = (SemiAffectationOperation) parent_;
            if(par_.isPost()) {
                boolean err_ = true;
                ClassMethodId classMethodId_ = par_.getClassMethodId();
                if (classMethodId_ != null) {
                    MethodId id_ = classMethodId_.getConstraints();
                    addParts(_cont,currentFileName_,classMethodId_.getClassName(),id_,offsetEnd_,1,parent_.getErrs(),_parts);
                    err_ = false;
                }
                SettableElResult settable_ = par_.getSettable();
                if (settable_ instanceof ArrOperation && ((ArrOperation) settable_).getClassMethodId() != null) {
                    ArrOperation parArr_ = (ArrOperation) par_.getSettable();
                    ClassMethodId classMethodIdArr_ = parArr_.getClassMethodId();
                    String className_ = classMethodIdArr_.getClassName();
                    className_ = StringExpUtil.getIdFromAllTypes(className_);
                    MethodId methodId_ = classMethodIdArr_.getConstraints();
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
                    addParts(_cont,currentFileName_,className_,id_,offsetEnd_+1,1,parent_.getErrs(),_parts);
                    err_ = false;
                }
                if (err_) {
                    if (!parent_.getErrs().isEmpty()) {
                        int begin_ = ((SemiAffectationOperation)parent_).getOpOffset()+sum_ + parent_.getIndexInEl();
                        _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringList.join(parent_.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                        _parts.add(new PartOffset("</a>",begin_+2));
                    }
                }
            }
        }
    }

    private static void safe(AbstractCoverageResult _res, ContextEl _cont, int offsetEnd_, CustList<PartOffset> _parts, int _delta) {
        if (!_res.isStrictPartialCovered()) {
            return;
        }
        if (_res instanceof BooleanCoverageResult) {
            logicalAndOr((BooleanCoverageResult) _res,_cont,offsetEnd_,_parts,_delta);
        }
        if (_res instanceof NullCoverageResult) {
            nullSafe((NullCoverageResult)_res,_cont,offsetEnd_,_parts, _delta);
        }
        if (_res instanceof NullBooleanCoverageResult){
            nullBooleanSafe((NullBooleanCoverageResult)_res,_cont,offsetEnd_,_parts, _delta);
        }
    }

    private static void logicalAndOr(BooleanCoverageResult _res, ContextEl _cont,
                                     int offsetEnd_, CustList<PartOffset> _parts, int _delta) {
        StringList founds_ = new StringList();
        if (_res.isCoverTrue()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getTrueString());
        }
        if (_res.isCoverFalse()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getFalseString());
        }
        String tag_ = "<a title=\""+ transform(StringList.join(founds_, ","))+"\">";
        _parts.add(new PartOffset(tag_, offsetEnd_));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,offsetEnd_+_delta));
    }

    private static void nullBooleanSafe(NullBooleanCoverageResult _res, ContextEl _cont, int offsetEnd_, CustList<PartOffset> _parts, int _delta) {
        StringList founds_ = new StringList();
        if (_res.isCoverNull()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getNullCoverString());
        }
        if (_res.isCoverTrue()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getTrueString());
        }
        if (_res.isCoverFalse()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getFalseString());
        }
        String tag_ = "<a title=\"" + transform(StringList.join(founds_, ",")) + "\">";
        _parts.add(new PartOffset(tag_, offsetEnd_));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, offsetEnd_ + _delta));
    }
    private static void nullSafe(NullCoverageResult _res, ContextEl _cont, int offsetEnd_, CustList<PartOffset> _parts, int _delta) {
        StringList founds_ = new StringList();
        if (_res.isCoverNull()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getNullCoverString());
        }
        if (_res.isCoverNotNull()) {
            founds_.add(_cont.getStandards().getDisplayedStrings().getNotNullCoverString());
        }
        String tag_ = "<a title=\""+ transform(StringList.join(founds_,","))+"\">";
        _parts.add(new PartOffset(tag_, offsetEnd_));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,offsetEnd_+ _delta));
    }
    private static String getFullInit(AbstractCoverageResult _resultPar) {
        String tag_;
        if (_resultPar.isInit()) {
            tag_ = "<span class=\"g\">";
        } else {
            tag_ = "<span class=\"f\">";
        }
        return tag_;
    }

    private static void addParts(ContextEl _cont, String _currentFileName, String _className,
                                 Identifiable _id, int _begin, int _length,
                                 StringList _errors,
                                 CustList<PartOffset> _parts) {
        if (_id == null) {
            if (!_errors.isEmpty()) {
                String tag_;
                tag_ = "<a title=\""+ transform(StringList.join(_errors,"\n\n"))+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_,_begin));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,_begin+_length));
            }
            return;
        }
        String cl_ = StringExpUtil.getIdFromAllTypes(_className);
        String rel_ = getRelativize(_cont, _currentFileName, _className, _id);
        if (rel_.isEmpty()) {
            if (!_errors.isEmpty()) {
                String tag_;
                tag_ = "<a title=\""+ transform(StringList.join(_errors,"\n\n"))+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_,_begin));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,_begin+_length));
            }
            return;
        }
        String tag_;
        if (_id instanceof MethodId) {
            if (!StringList.isDollarWord(_id.getName()) && !_id.getName().startsWith("[]")) {
                tag_ = "<a title=\""+ merge(_errors,_id.getSignature(_cont))+"\" href=\""+rel_+"\""+classErr(_errors)+">";
            } else {
                tag_ = "<a title=\""+ merge(_errors,cl_ +"."+ _id.getSignature(_cont))+"\" href=\""+rel_+"\""+classErr(_errors)+">";
            }
        } else {
            tag_ = "<a title=\""+ merge(_errors,cl_ +"."+ _id.getSignature(_cont))+"\" href=\""+rel_+"\""+classErr(_errors)+">";
        }
        _parts.add(new PartOffset(tag_,_begin));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,_begin+_length));
    }
    private static String merge(StringList _errors, String _link) {
        StringList list_ = new StringList();
        list_.addAllElts(_errors);
        list_.add(_link);
        return transform(StringList.join(list_,"\n\n"));
    }

    private static String classErr(StringList _errors) {
        if (_errors.isEmpty()) {
            return "";
        }
        return " class=\"e\"";
    }
    private static String getRelativize(ContextEl _cont, String _currentFileName, String _className, Identifiable _id) {
        String rel_;
        String cl_ = StringExpUtil.getIdFromAllTypes(_className);
        GeneType type_ = _cont.getClassBody(cl_);
        if (!isFromCustFile(type_)) {
            if (_id instanceof MethodId) {
                CustList<ExecNamedFunctionBlock> opers_ = ExecBlock.getOperatorsBodiesById(_cont, (MethodId) _id);
                if (!opers_.isEmpty()) {
                    ExecNamedFunctionBlock operator_ = opers_.first();
                    String file_ = operator_.getFile().getRenderFileName();
                    rel_ = relativize(_currentFileName, file_ + "#m" + operator_.getNameOffset());
                    return rel_;
                }
            }
            return "";
        }
        if (_id instanceof MethodId){
            ExecNamedFunctionBlock method_;
            CustList<ExecNamedFunctionBlock> methods_ = ExecBlock.getMethodBodiesById(_cont, _className, (MethodId) _id);
            if (methods_.isEmpty()) {
                return "";
            }
            method_ = methods_.first();
            rel_ = relativize(_currentFileName, method_.getFile().getRenderFileName() + "#m" + method_.getNameOffset());
            return rel_;
        }
        CustList<ExecConstructorBlock> ctors_ = ExecBlock.getConstructorBodiesById(_cont, _className, (ConstructorId) _id);
        if (ctors_.isEmpty()) {
            return "";
        }
        ExecConstructorBlock ctor_ = ctors_.first();
        rel_ = relativize(_currentFileName, ctor_.getFile().getRenderFileName() + "#m" + ctor_.getNameOffset());
        return rel_;
    }

    private static AbstractCoverageResult getCovers(ContextEl _cont, Block _block, OperationNode _oper) {
        IdMap<OperationNode, AbstractCoverageResult> map_ = _cont.getCoverage().getCovers().getVal(_block);
        if (map_ == null) {
            map_ = new IdMap<OperationNode, AbstractCoverageResult>();
        }
        AbstractCoverageResult res_ = map_.getVal(_oper);
        if (res_ == null) {
            res_ = new StandardCoverageResult();
            res_.fullCover();
        }
        return res_;
    }

    private static void updateFieldAnchor(ContextEl _cont, StringList _errs, CustList<PartOffset> _parts, ClassField _id, int _begin, int _length, String _currentFileName, int _offset) {
        String className_ = _id.getClassName();
        className_ = StringExpUtil.getIdFromAllTypes(className_);
        GeneType type_ = _cont.getClassBody(className_);
        if (!isFromCustFile(type_)) {
            if (!_errs.isEmpty()) {
                String err_ = transform(StringList.join(_errs,"\n\n"));
                _parts.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",_begin));
                _parts.add(new PartOffset("</a>",_begin+_length));
            }
            return;
        }
        String file_ = ((ExecRootBlock) type_).getFile().getRenderFileName();
        String rel_ = relativize(_currentFileName,file_+"#m"+_offset);
        String tag_ = "<a title=\""+transform(className_ +"."+ _id.getFieldName())+"\" href=\""+rel_+"\">";
        _parts.add(new PartOffset(tag_,_begin));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,_begin+_length));
    }
    public static String relativize(String _currentFile,String _refFile) {
        int diffFirst_ = -1;
        int countCommon_ = 0;
        boolean finished_ = true;
        int len_ = Math.min(_currentFile.length(),_refFile.length());
        for (int i =0; i < len_; i++) {
            char curChar_ = _currentFile.charAt(i);
            char relChar_ = _refFile.charAt(i);
            if (curChar_ != relChar_) {
                finished_ = false;
                break;
            }
            if(curChar_ == '/') {
                diffFirst_ = i;
                countCommon_++;
            }
        }
        if (finished_) {
            return _refFile.substring(len_);
        }
        String relFile_ = _refFile.substring(diffFirst_ + 1);
        if (_currentFile.indexOf('/',diffFirst_+1) < 0) {
            return relFile_;
        }
        StringBuilder b_ = new StringBuilder();
        int count_ = StringList.indexesOfChar(_currentFile,'/').size() - countCommon_;
        for (int i = 0; i < count_; i++) {
            b_.append("../");
        }
        return b_.append(relFile_).toString();
    }
    public static String transform(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (c == '<') {
                str_.append("&lt;");
            } else if (c == '>') {
                str_.append("&gt;");
            } else if (c == '&') {
                str_.append("&amp;");
            } else if (c == '\"') {
                str_.append("&quot;");
            } else {
                str_.append(c);
            }
        }
        return str_.toString();
    }
    private static boolean canCallToString(ClassArgumentMatching _type, ContextEl _cont) {
        if (_type.matchClass(_cont.getStandards().getAliasObject())) {
            return true;
        }
        if (_type.isArray()) {
            return false;
        }
        for (String s: _type.getNames()) {
            String id_ = StringExpUtil.getIdFromAllTypes(s);
            GeneType cl_ = _cont.getClassBody(id_);
            if (!(cl_ instanceof ExecRootBlock)) {
                continue;
            }
            if (cl_ instanceof ExecAnnotationBlock) {
                continue;
            }
            if (ExecOperationNode.tryGetDeclaredToString(_cont,s).isFoundMethod()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFromCustFile(GeneType _g) {
        if (!(_g instanceof ExecRootBlock)) {
            return false;
        }
        return !((ExecRootBlock)_g).getFile().isPredefined();
    }

    private static void refLabel(CustList<PartOffset> _parts, String _label, int _offset) {
        if (_label.isEmpty()) {
            return;
        }
        _parts.add(new PartOffset("<a name=\"m"+_offset+"\">",_offset));
        _parts.add(new PartOffset("</a>",_offset+_label.length()));
    }

    private static void refLabelError(Block _bl, CustList<PartOffset> _parts, String _label, int _offset) {
        if (_label.isEmpty()) {
            return;
        }
        StringList errs_ = _bl.getErrorsLabels();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringList.join(errs_,"\n\n"));
            _parts.add(new PartOffset("<a name=\"m"+_offset+"\" title=\""+err_+"\" class=\"e\">",_offset));
            _parts.add(new PartOffset("</a>",_offset+_label.length()));
        } else {
            _parts.add(new PartOffset("<a name=\"m"+_offset+"\">",_offset));
            _parts.add(new PartOffset("</a>",_offset+_label.length()));
        }
    }
}
