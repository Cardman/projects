package code.expressionlanguage.linkage;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.coverage.BooleanCoverageResult;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.coverage.NullBooleanCoverageResult;
import code.expressionlanguage.exec.coverage.NullCoverageResult;
import code.expressionlanguage.exec.coverage.StandardCoverageResult;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.DisplayedStrings;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class LinkageUtil {

    private LinkageUtil(){
    }
    public static StringMap<String> errors(AnalyzedPageEl _analyzing) {
        StringMap<String> files_ = new StringMap<String>();
        KeyWords keyWords_ = _analyzing.getKeyWords();
        boolean implicit_ = _analyzing.isImplicit();
        DisplayedStrings displayedStrings_ = _analyzing.getDisplayedStrings();
        StringList toStringOwners_ = _analyzing.getToStringOwners();
        for (FileBlock f: _analyzing.getErrors().getFiles()) {
            if (f.isPredefined()) {
                continue;
            }
            String value_ = f.getContent();
            String fileExp_ = f.getFileName() + ".html";
            CustList<PartOffset> listStr_ = processError(toStringOwners_,f,fileExp_, keyWords_, displayedStrings_, implicit_);
            StringBuilder xml_ = build(f, value_, listStr_);
            String rel_ = relativize(fileExp_,"css/style.css");
            String cssPart_ = "<head>" +encode(_analyzing.isEncodeHeader())+
                    "<link href=\""+rel_+"\" rel=\"stylesheet\" type=\"text/css\"/>" +
                    "</head>";
            files_.addEntry(fileExp_,"<html>"+cssPart_+"<body><pre><span class=\"t\">"+xml_+"</span></pre></body></html>");
        }
        String cssContent_ = ".e{background-color:red;}\n";
        cssContent_ += ".s{color:blue;}\n";
        cssContent_ += ".c{color:grey;background-color:white;}\n";
        cssContent_ += ".i{color:red;}\n";
        cssContent_ += ".t{background-color:white;}\n";
        files_.addEntry("css/style.css",cssContent_);
        return files_;
    }

    public static StringMap<String> export(ContextEl _cont) {
        StringMap<String> files_ = new StringMap<String>();
        Coverage cov_ = _cont.getCoverage();
        KeyWords keyWords_ = cov_.getKeyWords();
        LgNames standards_ = _cont.getStandards();
        CustList<RootBlock> refFoundTypes_ = cov_.getRefFoundTypes();
        CustList<OperatorBlock> operators_ = cov_.getRefOperators();
        StringList toStringOwners_ = cov_.getToStringOwners();
        for (FileBlock f: cov_.getFiles()) {
            if (f.isPredefined()) {
                continue;
            }
            String value_ = f.getContent();
            String fileExp_ = f.getFileName() + ".html";
            CustList<PartOffset> listStr_ = processReport(toStringOwners_,f,fileExp_, cov_, keyWords_, standards_);
            StringBuilder xml_ = build(f, value_, listStr_);
            String rel_ = relativize(fileExp_,"css/style.css");
            String cssPart_ = "<head>" +encode(cov_.isDisplayEncode())+
                    "<link href=\""+rel_+"\" rel=\"stylesheet\" type=\"text/css\"/>" +
                    "</head>";
            files_.addEntry(fileExp_,"<html>"+cssPart_+"<body><pre><span class=\"t\">"+xml_+"</span></pre></body></html>");
        }
        IdMap<RootBlock,String> types_ = new IdMap<RootBlock,String>();
        for (RootBlock t: refFoundTypes_) {
            if (t.getFile().isPredefined()) {
                continue;
            }
            int countCall_ = 0;
            int count_ = 0;
            for (Block b: ClassesUtil.getDirectChildren(t)) {
                if (b instanceof AnnotationMethodBlock) {
                    countCall_++;
                    count_++;
                    continue;
                }
                if (b instanceof MemberCallingsBlock) {
                    if (cov_.getFctRes((MemberCallingsBlock) b).isCalled()) {
                        countCall_++;
                    }
                    count_++;
                }
            }
            types_.addEntry(t,countCall_+"/"+count_);
        }
        int calledOps_ = 0;
        for (OperatorBlock b: operators_) {
            if (cov_.getFctRes(b).isCalled()) {
                calledOps_++;
            }
        }
        String callTable_ = "calls.html";
        StringBuilder table_ = new StringBuilder("<html>");
        table_.append("<head>");
        table_.append(encode(cov_.isDisplayEncode()));
        table_.append("</head>");
        table_.append("<body>");
        table_.append("<table>");
        table_.append("<tr>");
        table_.append("<td>");
        table_.append("</td>");
        table_.append("<td>");
        table_.append(calledOps_);
        table_.append("/");
        table_.append(operators_.size());
        table_.append("</td>");
        table_.append("</tr>");
        for (EntryCust<RootBlock,String> e: types_.entryList()) {
            table_.append("<tr>");
            table_.append("<td>");
            table_.append(e.getKey().getFullName());
            table_.append("</td>");
            table_.append("<td>");
            table_.append(e.getValue());
            table_.append("</td>");
            table_.append("</tr>");
        }
        table_.append("</table>");
        table_.append("</body>");
        table_.append("</html>");
        files_.addEntry(callTable_,table_.toString());
        String cssContent_ = ".f{background-color:green;}\n";
        cssContent_ += ".g{background-color:lightgreen;}\n";
        cssContent_ += ".p{background-color:yellow;}\n";
        cssContent_ += ".q{background-color:lightyellow;}\n";
        cssContent_ += ".n{background-color:red;}\n";
        cssContent_ += ".t{background-color:white;}\n";
        cssContent_ += ".s{color:#0080ff;}\n";
        cssContent_ += ".c{color:grey;background-color:white;}\n";
        cssContent_ += ".i{color:red;}\n";
        cssContent_ += ".f2{background-color:darkblue;}\n";
        cssContent_ += ".g2{background-color:blue;}\n";
        cssContent_ += ".p2{background-color:gold;}\n";
        cssContent_ += ".q2{background-color:orange;}\n";
        cssContent_ += ".n2{background-color:silver;}\n";
        files_.addEntry("css/style.css",cssContent_);
        return files_;
    }

    private static String encode(boolean _encode) {
        if (_encode) {
            return "<meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/>";
        }
        return "";
    }
    private static StringBuilder build(FileBlock _fileBlock, String _value, CustList<PartOffset> _listStr) {
        String value_ = _value;
        if (_value.isEmpty()) {
            value_ = " ";
        }
        StringBuilder xml_ = new StringBuilder(value_.length());
        int i_ = value_.length() - 1;
        for (PartOffset t:_listStr.getReverse()) {
            String part_ = t.getPart();
            if (part_.isEmpty()) {
                continue;
            }
            int off_ = t.getOffset();
            while (i_ >= off_) {
                char ch_ = value_.charAt(i_);
                insertTr(_fileBlock,xml_, ch_,i_);
                i_--;
            }
            xml_.insert(0, part_);
        }
        while (i_ >= 0) {
            char ch_ = value_.charAt(i_);
            insertTr(_fileBlock,xml_, ch_,i_);
            i_--;
        }
        return xml_;
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
        if (_ch > 126) {
            return "&#"+(int)_ch+";";
        }
        if (_ch < ' ') {
            return processSpace(_ch);
        }
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

    private static String processSpace(char _ch) {
        if (_ch == '\n') {
            return "\n";
        }
        if (_ch == '\t') {
            return "\t";
        }
        return " ";
    }

    private static CustList<PartOffset> processError(StringList _toStringOwers, FileBlock _ex, String _fileExp, KeyWords _keyWords, DisplayedStrings _displayedStrings, boolean _implicit){
        CustList<PartOffset> list_ = new CustList<PartOffset>();
        VariablesOffsets vars_ = new VariablesOffsets();
        vars_.getStack().add(new LinkageStackElement());
        vars_.setKeyWords(_keyWords);
        vars_.setImplicit(_implicit);
        vars_.setDisplayedStrings(_displayedStrings);
        vars_.setToStringOwners(_toStringOwers);
        vars_.setCurrentFileName(_fileExp);
        if (_ex.getFirstChild() == null || !_ex.getErrorsFiles().getLi().isEmpty()) {
            processFileBlockError(_ex,list_);
            return list_;
        }
        Block child_ = _ex;
        while (child_ != null) {
            vars_.getStack().last().setBlock(child_);
            if (child_ instanceof FileBlock) {
                processFileBlockReport((FileBlock)child_,list_);
            }
            if (child_ instanceof RootBlock || child_ instanceof OperatorBlock) {
                processGlobalRootBlockError((BracedBlock) child_, list_);
                if (!((BracedBlock) child_).getGlobalErrorsPars().getLi().isEmpty()) {
                    child_ = nextSkip(child_, _ex);
                    continue;
                }
            }
            if (vars_.getStack().last().getCurrent() == null) {
                if (!(child_ instanceof NamedFunctionBlock)&&!(child_ instanceof InfoBlock)
                        &&!(child_ instanceof Line)&&!(child_ instanceof DeclareVariable)
                        &&!(child_ instanceof EmptyInstruction)&&!(child_ instanceof RootBlock)&&!isImplicitReturn(child_)) {
                    if (!child_.getErrorsBlock().isEmpty()) {
                        String err_ = StringUtil.join(child_.getErrorsBlock(),"\n\n");
                        int off_ = child_.getBegin();
                        int l_ = child_.getLengthHeader();
                        list_.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",off_));
                        list_.add(new PartOffset("</a>",off_+l_));
                    }
                }
            }
            if (child_ instanceof RootBlock) {
                if (child_ instanceof InnerElementBlock) {
                    processInnerElementBlockError(vars_,(InnerElementBlock)child_, list_);
                } else {
                    processRootBlockError(vars_, (RootBlock) child_, list_);
                }
            }
            if (child_ instanceof InternOverrideBlock) {
                processInternOverrideBlock(vars_,list_, (InternOverrideBlock) child_);
            }
            if (child_ instanceof OperatorBlock) {
                processOverridableBlockError(vars_,(OperatorBlock)child_, list_);
            }
            if (child_ instanceof ConstructorBlock) {
                processConstructorBlockError(vars_,(ConstructorBlock)child_, list_);
            }
            if (child_ instanceof OverridableBlock) {
                processOverridableBlockError(vars_,(OverridableBlock)child_, list_);
            }
            if (child_ instanceof AnnotationMethodBlock) {
                processAnnotationMethodBlockError(vars_,(AnnotationMethodBlock)child_, list_);
            }
            if (child_ instanceof ElementBlock) {
                processElementBlockError(vars_,(ElementBlock)child_, list_);
            }
            if (child_ instanceof FieldBlock) {
                processFieldBlockError(vars_,(FieldBlock)child_, list_);
            }
            if (child_ instanceof WhileCondition) {
                processWhileConditionError(vars_,(WhileCondition)child_, list_);
            }
            if (child_ instanceof IfCondition) {
                processIfConditionError(vars_,(IfCondition)child_, list_);
            }
            if (child_ instanceof ElseIfCondition) {
                processConditionError((ElseIfCondition)child_, vars_, list_);
                processTestCondition(vars_, (ElseIfCondition)child_, list_);
            }
            if (child_ instanceof DoBlock) {
                processDoBlockError(vars_, (DoBlock)child_,list_);
            }
            if (child_ instanceof DoWhileCondition) {
                processConditionError((DoWhileCondition)child_, vars_, list_);
                processTestCondition(vars_, (DoWhileCondition)child_, list_);
            }
            if (child_ instanceof SwitchBlock) {
                processSwitchBlockError(vars_,(SwitchBlock)child_, list_);
            }
            if (child_ instanceof CaseCondition) {
                processCaseConditionError(vars_,(CaseCondition)child_, list_);
            }
            if (child_ instanceof DefaultCondition) {
                processDefaultConditionError((DefaultCondition)child_, list_);
            }
            if (child_ instanceof TryEval) {
                processTryEvalError(vars_, (TryEval)child_,list_);
            }
            if (child_ instanceof CatchEval) {
                processCatchEvalError((CatchEval)child_, list_);
            }
            if (child_ instanceof DeclareVariable) {
                processDeclareVariableError(vars_,(DeclareVariable)child_, list_);
            }
            if (child_ instanceof Line) {
                processLineError(vars_,(Line)child_, list_);
            }
            if (child_ instanceof ForIterativeLoop) {
                processForIterativeLoopError(vars_,(ForIterativeLoop)child_, list_);
            }
            if (child_ instanceof ForEachLoop) {
                processForEachLoopError(vars_,(ForEachLoop)child_, list_);
            }
            if (child_ instanceof ForEachTable) {
                processForEachTableError(vars_,(ForEachTable)child_, list_);
            }
            if (child_ instanceof ForMutableIterativeLoop) {
                processForMutableIterativeLoopError(vars_,(ForMutableIterativeLoop)child_, list_);
            }
            if (child_ instanceof ReturnMethod) {
                processReturnMethodError(vars_,(ReturnMethod)child_, list_);
            }
            if (child_ instanceof Throwing) {
                processThrowingError(vars_,(Throwing)child_, list_);
            }
            if (child_ instanceof BreakBlock) {
                processBreakBlockError((BreakBlock)child_,list_);
            }
            if (child_ instanceof ContinueBlock) {
                processContinueBlockError((ContinueBlock)child_,list_);
            }
            if (vars_.getState() != null) {
                vars_.getStack().add(vars_.getState());
                vars_.setState(null);
                child_ = vars_.getStack().last().getBlock();
                continue;
            }
            if (child_ instanceof Line
                    || child_ instanceof NamedFunctionBlock
                    || child_ instanceof InfoBlock) {
                if (!child_.getErrorsBlock().isEmpty()) {
                    String err_ = StringUtil.join(child_.getErrorsBlock(),"\n\n");
                    int off_ = child_.getBegin();
                    int l_ = child_.getLengthHeader();
                    list_.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",off_));
                    list_.add(new PartOffset("</a>",off_+l_));
                }
            }
            child_ = next(child_, _ex);
            if (child_ == null) {
                int indexEnd_ = vars_.getStack().last().getIndexEnd();
                vars_.getStack().removeQuicklyLast();
                if (!vars_.getStack().isEmpty()) {
                    list_.add(new PartOffset("</span>",indexEnd_));
                    child_ = vars_.getStack().last().getBlock();
                }
            }
        }
        return list_;
    }
    private static void processFileBlockError(FileBlock _cond, CustList<PartOffset> _parts) {
        for (GraphicErrorInterpret g: _cond.getErrorsFiles().getLi()) {
            int index_ = g.getIndexFile();
            _parts.add(new PartOffset("<a title=\""+g.getBuiltError()+"\" class=\"e\">", index_));
            _parts.add(new PartOffset("</a>", index_+ g.getLength()));
        }
    }
    private static void processGlobalRootBlockError(BracedBlock _cond, CustList<PartOffset> _parts) {
        for (GraphicErrorInterpret g: _cond.getGlobalErrorsPars().getLi()) {
            int index_ = g.getIndexFile();
            _parts.add(new PartOffset("<a title=\""+g.getBuiltError()+"\" class=\"e\">", index_));
            _parts.add(new PartOffset("</a>", index_+ g.getLength()));
        }
    }
    private static CustList<PartOffset> processReport(StringList _toStringOwers, FileBlock _ex, String _fileExp, Coverage _coverage, KeyWords _keyWords, LgNames _standards){
        CustList<PartOffset> list_ = new CustList<PartOffset>();
        VariablesOffsets vars_ = new VariablesOffsets();
        vars_.getStack().add(new LinkageStackElement());
        vars_.setKeyWords(_keyWords);
        vars_.setImplicit(_coverage.isImplicit());
        vars_.setDisplayedStrings(_standards.getDisplayedStrings());
        vars_.setToStringOwners(_toStringOwers);
        vars_.setCurrentFileName(_fileExp);
        Block child_ = _ex;
        vars_.getStack().last().setBlock(child_);
        while (child_ != null) {
            vars_.getStack().last().setBlock(child_);
            if (child_ instanceof IfCondition) {
                processIfConditionReport(vars_,(IfCondition)child_, list_, _coverage);
            }
            if (child_ instanceof ElseIfCondition) {
                processElseIfConditionReport(vars_,(ElseIfCondition)child_, list_, _coverage);
            }
            if (child_ instanceof WhileCondition) {
                processWhileConditionReport(vars_,(WhileCondition)child_, list_, _coverage);
            }
            if (child_ instanceof DoWhileCondition) {
                processDoWhileConditionReport(vars_,(DoWhileCondition)child_, list_, _coverage);
            }
            if (child_ instanceof DoBlock) {
                processDoBlockReport(vars_,(DoBlock)child_,list_);
            }
            if (child_ instanceof ForMutableIterativeLoop) {
                processForMutableIterativeLoopReport(vars_,(ForMutableIterativeLoop)child_, list_, _coverage);
            }
            if (child_ instanceof SwitchBlock) {
                processSwitchBlockReport(vars_,(SwitchBlock)child_, list_, _coverage);
            }
            if (child_ instanceof CaseCondition) {
                processCaseConditionReport(vars_,(CaseCondition)child_, list_, _coverage);
            }
            if (child_ instanceof DefaultCondition) {
                processDefaultConditionReport(vars_,(DefaultCondition)child_, list_, _coverage);
            }
            if (child_ instanceof TryEval) {
                processTryEvalReport(vars_,(TryEval)child_,list_);
            }
            if (child_ instanceof CatchEval) {
                processCatchEvalReport(vars_,(CatchEval)child_, list_, _coverage);
            }
            if (child_ instanceof NullCatchEval) {
                processAbstractCatchEvalReport(vars_,(NullCatchEval)child_, list_, _coverage);
            }
            if (child_ instanceof DeclareVariable) {
                processDeclareVariableReport(vars_,(DeclareVariable)child_, list_);
            }
            if (child_ instanceof Line) {
                processLineReport(vars_,(Line)child_, list_, _coverage);
            }
            if (child_ instanceof ReturnMethod) {
                processReturnMethodReport(vars_,(ReturnMethod)child_, list_, _coverage);
            }
            if (child_ instanceof Throwing) {
                processThrowingReport(vars_,(Throwing)child_, list_, _coverage);
            }
            if (child_ instanceof BreakBlock) {
                processBreakBlockReport((BreakBlock)child_,list_);
            }
            if (child_ instanceof ContinueBlock) {
                processContinueBlockReport((ContinueBlock)child_,list_);
            }
            if (child_ instanceof ForIterativeLoop) {
                processForIterativeLoopReport(vars_,(ForIterativeLoop)child_, list_, _coverage);
            }
            if (child_ instanceof ForEachLoop) {
                processForEachLoopReport(vars_,(ForEachLoop)child_, list_, _coverage);
            }
            if (child_ instanceof ForEachTable) {
                processForEachTableReport(vars_,(ForEachTable)child_, list_, _coverage);
            }
            if (child_ instanceof ElementBlock) {
                processElementBlockReport(vars_,(ElementBlock)child_, list_, _coverage);
            }
            if (child_ instanceof FieldBlock) {
                processFieldBlockReport(vars_,(FieldBlock)child_, list_, _coverage);
            }
            if (child_ instanceof ConstructorBlock) {
                processConstructorBlockReport(vars_,(ConstructorBlock)child_, list_, _coverage);
            }
            if (child_ instanceof OverridableBlock) {
                processOverridableBlockReport(vars_,(OverridableBlock)child_, list_, _coverage);
            }
            if (child_ instanceof InternOverrideBlock) {
                processInternOverrideBlock(vars_,list_, (InternOverrideBlock) child_);
            }
            if (child_ instanceof AnnotationMethodBlock) {
                processAnnotationMethodBlockReport(vars_,(AnnotationMethodBlock)child_, list_, _coverage);
            }
            if (child_ instanceof OperatorBlock) {
                processOverridableBlockReport(vars_,(OperatorBlock)child_, list_, _coverage);
            }
            if (child_ instanceof RootBlock) {
                if (child_ instanceof InnerElementBlock) {
                    processInnerElementBlockReport(vars_,(InnerElementBlock)child_, list_, _coverage);
                } else {
                    processRootBlockReport(vars_,(RootBlock)child_, list_, _coverage);
                }
            }
            if (child_ instanceof FileBlock) {
                processFileBlockReport((FileBlock)child_,list_);
            }
            if (vars_.getState() != null) {
                vars_.getStack().add(vars_.getState());
                vars_.setState(null);
                child_ = vars_.getStack().last().getBlock();
                continue;
            }
            child_ = next(child_, _ex);
            if (child_ == null) {
                int indexEnd_ = vars_.getStack().last().getIndexEnd();
                vars_.getStack().removeQuicklyLast();
                if (!vars_.getStack().isEmpty()) {
                    list_.add(new PartOffset("</span>",indexEnd_));
                    child_ = vars_.getStack().last().getBlock();
                }
            }
        }
        return list_;
    }

    private static Block next(Block _current, Block _ex) {
        Block firstChild_ = _current.getFirstChild();
        if (firstChild_ != null) {
            return firstChild_;
        }
        return nextSkip(_current,_ex);
    }
    private static Block nextSkip(Block _current, Block _ex) {
        Block child_ = _current;
        while (true) {
            Block nextSibling_ = child_.getNextSibling();
            if (nextSibling_ != null) {
                return nextSibling_;
            }
            BracedBlock parent_ = child_.getParent();
            if (parent_ == _ex || parent_ == null) {
                return null;
            }
            child_ = parent_;
        }
    }

    private static void processInternOverrideBlock(VariablesOffsets _vars,CustList<PartOffset> _parts, InternOverrideBlock _child) {
        for (PartOffsetsClassMethodIdList l: _child.getAllPartsTypes()) {
            _parts.addAllElts(l.getTypes());
            for (PartOffsetsClassMethodId p:l.getOverrides()) {
                _parts.addAllElts(p.getTypes());
                ClassMethodId id_ = p.getId();
                if (id_ != null) {
                    int rc_ = p.getBegin();
                    int len_ = p.getLength();
                    CustList<PartOffset> partMethod_ = new CustList<PartOffset>();
                    StringList l_ = new StringList();
                    LinkageUtil.addParts(_vars.getDisplayedStrings(), _child.getFile().getRenderFileName(),p.getFct(),rc_,len_, l_,l_,partMethod_);
                    _parts.addAllElts(partMethod_);
                }
                _parts.addAllElts(p.getSuperTypes());
            }
        }
    }

    private static void processIfConditionReport(VariablesOffsets _vars, IfCondition _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getStack().last().getCurrent() == null) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond);
            String tag_;
            if (result_.isFullCovered()) {
                tag_ = "<span class=\"f\">";
            } else if (result_.isPartialCovered()) {
                tag_ = "<span class=\"p\">";
            } else {
                tag_ = "<span class=\"n\">";
            }
            int off_ = _cond.getOffset().getOffsetTrim();
            _parts.add(new PartOffset(tag_, off_));
            tag_ = "</span>";
            _parts.add(new PartOffset(tag_, off_ + _vars.getKeyWords().getKeyWordIf().length()));
        }
        processConditionReport(_cond,_vars, _parts, _cov);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars, _cond, _parts);
    }
    private static void processIfConditionError(VariablesOffsets _vars, IfCondition _cond, CustList<PartOffset> _parts) {
        processConditionError(_cond, _vars, _parts);
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars, _cond, _parts);
    }

    private static void processWhileConditionError(VariablesOffsets _vars, WhileCondition _cond, CustList<PartOffset> _parts) {
        processConditionError(_cond, _vars, _parts);
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars, _cond, _parts);
    }
    private static void processElseIfConditionReport(VariablesOffsets _vars, ElseIfCondition _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getStack().last().getCurrent() == null) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond);
            String tag_;
            if (result_.isFullCovered()) {
                tag_ = "<span class=\"f\">";
            } else if (result_.isPartialCovered()) {
                tag_ = "<span class=\"p\">";
            } else {
                tag_ = "<span class=\"n\">";
            }
            int off_ = _cond.getOffset().getOffsetTrim();
            _parts.add(new PartOffset(tag_, off_));
            tag_ = "</span>";
            _parts.add(new PartOffset(tag_, off_ + _cond.getDelta()));
        }
        processConditionReport(_cond,_vars, _parts, _cov);
        processTestCondition(_vars, _cond, _parts);
    }
    private static void processWhileConditionReport(VariablesOffsets _vars, WhileCondition _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getStack().last().getCurrent() == null) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond);
            String tag_;
            if (result_.isFullCovered()) {
                tag_ = "<span class=\"f\">";
            } else if (result_.isPartialCovered()) {
                tag_ = "<span class=\"p\">";
            } else {
                tag_ = "<span class=\"n\">";
            }
            int off_ = _cond.getOffset().getOffsetTrim();
            _parts.add(new PartOffset(tag_, off_));
            tag_ = "</span>";
            _parts.add(new PartOffset(tag_, off_ + _vars.getKeyWords().getKeyWordWhile().length()));
        }
        processConditionReport(_cond,_vars, _parts, _cov);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars, _cond, _parts);
    }

    private static void processTestCondition(VariablesOffsets _vars, Condition _cond, CustList<PartOffset> _parts) {
        if (_vars.getState() != null) {
            return;
        }
        AnaTypeFct function_ = _cond.getFunction();
        if (function_ != null) {
            StringList list_ = new StringList();
            addParts(_vars, _vars.getCurrentFileName(), function_,_cond.getTestOffset(),1, list_,list_,_parts);
        }
        if (_vars.isImplicit()) {
            function_ = _cond.getFunctionImpl();
            int off_ = _cond.getTestOffset();
            if (function_ != null) {
                StringList list_ = new StringList();
                addParts(_vars, _vars.getCurrentFileName(), function_,off_,1, list_,list_,_parts);
            }
        }


    }
    private static void processForMutableIterativeLoopReport(VariablesOffsets _vars, ForMutableIterativeLoop _cond, CustList<PartOffset> _parts, Coverage _cov) {
        OperationNode rootExp_ = _cond.getRootExp();
        if (_vars.getStack().last().getCurrent() == null) {
            if (rootExp_ != null) {
                AbstractCoverageResult result_ = _cov.getCoversConditionsForMutable(_cond);
                String tag_;
                if (result_.isFullCovered()) {
                    tag_ = "<span class=\"f\">";
                } else if (result_.isPartialCovered()) {
                    tag_ = "<span class=\"p\">";
                } else {
                    tag_ = "<span class=\"n\">";
                }
                int off_ = _cond.getOffset().getOffsetTrim();
                _parts.add(new PartOffset(tag_, off_));
                tag_ = "</span>";
                _parts.add(new PartOffset(tag_, off_ + _vars.getKeyWords().getKeyWordFor().length()));
            }
            appendVars(_vars,_cond, _parts);
        }
        OperationNode rootInit_ = _cond.getRootInit();
        if (_vars.getStack().last().getIndexLoop() == 0) {
            if (rootInit_ != null) {
                int off_ = _cond.getInitOffset();
                int offsetEndBlock_ = off_ + _cond.getInit().length();
                buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, rootInit_, 0, 0, "", false);
                if (_vars.getState() != null) {
                    return;
                }
            }
            _vars.getStack().last().setIndexLoop(1);
        }
        if (_vars.getStack().last().getIndexLoop() == 1) {
            if (rootExp_ != null) {
                int off_ = _cond.getExpressionOffset();
                int offsetEndBlock_ = off_ + _cond.getExpression().length();
                buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, rootExp_, 1, 0, "", false);
                if (_vars.getState() != null) {
                    return;
                }
            }
            _vars.getStack().last().setIndexLoop(2);
        }
        OperationNode rootStep_ = _cond.getRootStep();
        if (rootStep_ != null) {
            int off_ = _cond.getStepOffset();
            int offsetEndBlock_ = off_ + _cond.getStep().length();
            buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, rootStep_, 2, 0, "", false);
            if (_vars.getState() != null) {
                return;
            }
        }
        _vars.getStack().last().setIndexLoop(0);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars,_cond, _parts);
    }
    private static void processForMutableIterativeLoopError(VariablesOffsets _vars, ForMutableIterativeLoop _cond, CustList<PartOffset> _parts) {
        if (_vars.getStack().last().getCurrent() == null) {
            appendVars(_vars,_cond, _parts);
        }
        if (_vars.getStack().last().getIndexLoop() == 0) {
            OperationNode rootInit_ = _cond.getRootInit();
            if (rootInit_ != null) {
                int off_ = _cond.getInitOffset();
                buildErrorReport(_vars, _cond, -1, _parts, 0, off_, rootInit_, 0, 0, "");
                if (_vars.getState() != null) {
                    return;
                }
            }
            _vars.getStack().last().setIndexLoop(1);
        }
        if (_vars.getStack().last().getIndexLoop() == 1) {
            OperationNode rootExp_ = _cond.getRootExp();
            if (rootExp_ != null) {
                int off_ = _cond.getExpressionOffset();
                buildErrorReport(_vars, _cond, -1, _parts, 0, off_, rootExp_, 1, 0, "");
                if (_vars.getState() != null) {
                    return;
                }
            }
            _vars.getStack().last().setIndexLoop(2);
        }
        OperationNode rootStep_ = _cond.getRootStep();
        if (rootStep_ != null) {
            int off_ = _cond.getStepOffset();
            buildErrorReport(_vars, _cond, -1, _parts, 0, off_, rootStep_, 2, 0, "");
            if (_vars.getState() != null) {
                return;
            }
        }
        _vars.getStack().last().setIndexLoop(0);
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars,_cond, _parts);
    }

    private static void processTestCondition(VariablesOffsets _vars, ForMutableIterativeLoop _cond, CustList<PartOffset> _parts) {
        AnaTypeFct function_ = _cond.getFunction();
        if (function_ != null) {
            StringList list_ = new StringList();
            addParts(_vars, _vars.getCurrentFileName(), function_,_cond.getTestOffset(),1, list_,list_,_parts);
        }
        function_ = _cond.getFunctionImpl();
        if (_vars.isImplicit()&&function_ != null) {
            StringList list_ = new StringList();
            addParts(_vars, _vars.getCurrentFileName(), function_,_cond.getTestOffset(),1, list_,list_,_parts);
        }
    }
    private static void appendVars(VariablesOffsets _vars, ForMutableIterativeLoop _cond, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        String clName_ = _cond.getClassName().trim();
        String errInf_ = _cond.getErrInf();
        String importedClassName_ = _cond.getImportedClassName();
        int classNameOffset_ = _cond.getClassNameOffset();
        CustList<PartOffset> partOffsets_ = _cond.getPartOffsets();
        procInferVar(_parts, keyWordVar_, clName_, errInf_, importedClassName_, classNameOffset_, partOffsets_);
    }

    private static void processSwitchBlockReport(VariablesOffsets _vars, SwitchBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getStack().last().getCurrent() == null) {
            int full_ = 0;
            int count_ = 0;
            for (StandardCoverageResult e : _cov.getCoverSwitchs(_cond).values()) {
                count_ += e.getCovered();
                full_ += e.getFull();
            }
            StandardCoverageResult noDef_ = _cov.getCoverNoDefSwitchs(_cond);
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
            _parts.add(new PartOffset(tag_ + "<a title=\"" + count_ + "/" + full_ + "\">", off_));
            tag_ = "</span>";
            _parts.add(new PartOffset("</a>" + tag_, off_ + _vars.getKeyWords().getKeyWordSwitch().length()));
        }
        int off_ = _cond.getValueOffset();
        int offsetEndBlock_ = off_ + _cond.getValue().length();
        OperationNode root_ = _cond.getRoot();
        buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, root_, 0, 0, "", false);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processSwitchBlockError(VariablesOffsets _vars, SwitchBlock _cond, CustList<PartOffset> _parts) {
        int off_ = _cond.getValueOffset();
        if (_vars.getStack().last().getCurrent() == null) {
            if (!_cond.getErr().isEmpty()) {
                _parts.add(new PartOffset("<a title=\"" + _cond.getErr() + "\" class=\"e\">", off_));
                _parts.add(new PartOffset("</a>", off_ + 1));
            }
        }
        OperationNode root_ = _cond.getRoot();
        buildErrorReport(_vars, _cond, -1, _parts, 0, off_, root_, 0, 0, "");
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processCaseConditionReport(VariablesOffsets _vars, CaseCondition _cond, CustList<PartOffset> _parts, Coverage _cov) {
        SwitchBlock parent_ = _cond.getSwitchParent();
        AbstractCoverageResult result_ = _cov.getCoverSwitchs(parent_,_cond);
        int off_ = _cond.getValueOffset();
        String tag_ = getCaseDefaultTag(result_);
        if (_vars.getStack().last().getCurrent() == null) {
            _parts.add(new PartOffset(tag_, off_));
        }
        if (!_cond.getImportedType().isEmpty()) {
            _parts.addAllElts(_cond.getPartOffsets());
            String variableName_ = _cond.getVariableName();
            int variableOffset_ = _cond.getVariableOffset();
            tag_ = "<a name=\"m"+variableOffset_+"\">";
            _parts.add(new PartOffset(tag_,variableOffset_));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,variableOffset_+ variableName_.trim().length()));
        } else if (_cond.isBuiltEnum()) {
            int delta_ = _cond.getFieldNameOffset();
            String typeEnum_ = _cond.getTypeEnum();
            String currentFileName_ = _vars.getCurrentFileName();
            updateFieldAnchor(_cond.getEnumBlock(), new StringList(),_parts,new ClassField(typeEnum_,_cond.getValue().trim()),off_,Math.max(1, _cond.getValue().length()),currentFileName_,delta_);
        } else {
            int offsetEndBlock_ = off_ + _cond.getValue().length();
            OperationNode root_ = _cond.getRoot();
            buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, root_, 0, 0, "", false);
        }
        if (_vars.getState() == null) {
            tag_ = "</span>";
            _parts.add(new PartOffset(tag_,off_+ _cond.getValue().length()));
        }
    }

    private static String getCaseDefaultTag(AbstractCoverageResult _result) {
        String tag_;
        if (_result.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        return tag_;
    }

    private static void processCaseConditionError(VariablesOffsets _vars, CaseCondition _cond, CustList<PartOffset> _parts) {
        int off_;
        if (!_cond.getImportedType().isEmpty()) {
            _parts.addAllElts(_cond.getPartOffsets());
            String variableName_ = _cond.getVariableName();
            int variableOffset_ = _cond.getVariableOffset();
            if (!variableName_.isEmpty()) {
                StringList errs_ = _cond.getNameErrors();
                if (!errs_.isEmpty()) {
                    String err_ = transform(StringUtil.join(errs_,"\n\n"));
                    String tag_ = "<a name=\"m"+variableOffset_ +"\" title=\""+err_+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_, variableOffset_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, variableOffset_ + variableName_.trim().length()));
                } else {
                    String tag_ = "<a name=\"m"+variableOffset_+"\">";
                    _parts.add(new PartOffset(tag_,variableOffset_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_,variableOffset_+ variableName_.trim().length()));
                }
            }
        } else if (_cond.isBuiltEnum()) {
            off_ = _cond.getValueOffset();
            String typeEnum_ = _cond.getTypeEnum();
            int delta_ = _cond.getFieldNameOffset();
            String currentFileName_ = _vars.getCurrentFileName();
            updateFieldAnchor(_cond.getEnumBlock(), new StringList(),_parts,new ClassField(typeEnum_,_cond.getValue().trim()),off_,Math.max(1, _cond.getValue().length()),currentFileName_,delta_);
        } else {
            off_ = _cond.getValueOffset();
            OperationNode root_ = _cond.getRoot();
            buildErrorReport(_vars, _cond, -1, _parts, 0, off_, root_, 0, 0, "");
        }
    }

    private static void processDefaultConditionReport(VariablesOffsets _vars, DefaultCondition _cond, CustList<PartOffset> _parts, Coverage _cov) {
        SwitchBlock parent_ = _cond.getSwitchParent();
        AbstractCoverageResult result_ = _cov.getCoverSwitchs(parent_,_cond);
        String tag_ = getCaseDefaultTag(result_);
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _vars.getKeyWords().getKeyWordDefault().length()));
        if (!_cond.getVariableName().trim().isEmpty()) {
            off_ = _cond.getVariableOffset();
            tag_ = "<a title=\""+transform(_cond.getInstanceTest())+"\" name=\"m"+off_+"\">";
            _parts.add(new PartOffset(tag_,off_));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,off_+_cond.getVariableName().length()));
        }
    }

    private static void processDefaultConditionError(DefaultCondition _cond, CustList<PartOffset> _parts) {
        if (!_cond.getVariableName().trim().isEmpty()) {
            StringList errs_ = _cond.getNameErrors();
            if (!errs_.isEmpty()) {
                String err_ = transform(StringUtil.join(errs_,"\n\n"));
                int off_ = _cond.getVariableOffset();
                String tag_ = "<a name=\"m"+off_ +"\" title=\""+err_+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_, off_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, off_ + _cond.getVariableName().trim().length()));
            } else {
                int off_ = _cond.getVariableOffset();
                String tag_ = "<a title=\""+transform(_cond.getInstanceTest())+"\" name=\"m"+off_+"\">";
                _parts.add(new PartOffset(tag_,off_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,off_+_cond.getVariableName().length()));
            }
        }
    }

    private static void processDoBlockReport(VariablesOffsets _vars,DoBlock _cond, CustList<PartOffset> _parts) {
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void processDoBlockError(VariablesOffsets _vars,DoBlock _cond, CustList<PartOffset> _parts) {
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processDoWhileConditionReport(VariablesOffsets _vars, DoWhileCondition _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getStack().last().getCurrent() == null) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond);
            String tag_;
            if (result_.isFullCovered()) {
                tag_ = "<span class=\"f\">";
            } else if (result_.isPartialCovered()) {
                tag_ = "<span class=\"p\">";
            } else {
                tag_ = "<span class=\"n\">";
            }
            int off_ = _cond.getOffset().getOffsetTrim();
            _parts.add(new PartOffset(tag_, off_));
            tag_ = "</span>";
            _parts.add(new PartOffset(tag_, off_ + _vars.getKeyWords().getKeyWordWhile().length()));
        }
        processConditionReport(_cond,_vars, _parts, _cov);
        processTestCondition(_vars, _cond, _parts);
    }
    private static void processTryEvalReport(VariablesOffsets _vars,TryEval _cond, CustList<PartOffset> _parts) {
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processTryEvalError(VariablesOffsets _vars,TryEval _cond, CustList<PartOffset> _parts) {
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processCatchEvalReport(VariablesOffsets _vars, CatchEval _cond, CustList<PartOffset> _parts, Coverage _cov) {
        processAbstractCatchEvalReport(_vars,_cond, _parts, _cov);
        _parts.addAllElts(_cond.getPartOffsets());
        String tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\">";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
    }
    private static void processCatchEvalError(CatchEval _cond, CustList<PartOffset> _parts) {
        _parts.addAllElts(_cond.getPartOffsets());
        StringList errs_ = _cond.getNameErrors();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringUtil.join(errs_,"\n\n"));
            String tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\" title=\""+err_+"\" class=\"e\">";
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
    private static void processAbstractCatchEvalReport(VariablesOffsets _vars, AbstractCatchEval _cond, CustList<PartOffset> _parts, Coverage _cov) {
        String tag_;
        if (_cov.getCatches(_cond)) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = _cond.getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _vars.getKeyWords().getKeyWordCatch().length()));
    }
    private static void processDeclareVariableReport(VariablesOffsets _vars, DeclareVariable _cond, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(_cond.getClassName().trim(), keyWordVar_)) {
            String tag_ = "<b title=\""+transform(_cond.getImportedClassName())+"\">";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset() + keyWordVar_.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsets());
        }
    }
    private static void processDeclareVariableError(VariablesOffsets _vars, DeclareVariable _cond, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        String clName_ = _cond.getClassName().trim();
        String errInf_ = _cond.getErrInf();
        String importedClassName_ = _cond.getImportedClassName();
        int classNameOffset_ = _cond.getClassNameOffset();
        CustList<PartOffset> partOffsets_ = _cond.getPartOffsets();
        procInferVar(_parts, keyWordVar_, clName_, errInf_, importedClassName_, classNameOffset_, partOffsets_);
    }

    private static void procInferVar(CustList<PartOffset> _parts, String _keyWordVar, String _clName, String _errInf, String _importedClassName, int _classNameOffset, CustList<PartOffset> _partOffsets) {
        if (StringUtil.quickEq(_clName, _keyWordVar)) {
            String tag_;
            if (_errInf.isEmpty()) {
                tag_ = "<b title=\"" + transform(_importedClassName) + "\">";
                _parts.add(new PartOffset(tag_, _classNameOffset));
                tag_ = "</b>";
            } else {
                tag_ = "<a title=\"" + transform(_errInf) + "\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _classNameOffset));
                tag_ = "</a>";
            }
            _parts.add(new PartOffset(tag_, _classNameOffset + _keyWordVar.length()));
        } else {
            _parts.addAllElts(_partOffsets);
        }
    }

    private static void processLineReport(VariablesOffsets _vars, Line _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int blOffset_ = _cond.getExpressionOffset();
        int endBl_ = blOffset_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, blOffset_, endBl_, root_, 0, 0, "", false);
    }
    private static void processLineError(VariablesOffsets _vars, Line _cond, CustList<PartOffset> _parts) {
        int blOffset_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildErrorReport(_vars, _cond, -1, _parts, 0, blOffset_, root_, 0, 0, "");
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
            String err_ = transform(StringUtil.join(errs_,"\n\n"));
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
            String err_ = transform(StringUtil.join(errs_,"\n\n"));
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
    private static void processReturnMethodReport(VariablesOffsets _vars, ReturnMethod _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_cond.isEmpty()) {
            return;
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, root_, 0, 0, "", false);
    }

    private static void processReturnMethodError(VariablesOffsets _vars, ReturnMethod _cond, CustList<PartOffset> _parts) {
        if (_cond.isEmpty()) {
            return;
        }
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildErrorReport(_vars, _cond, -1, _parts, 0, off_, root_, 0, 0, "");
    }
    private static void processThrowingReport(VariablesOffsets _vars, Throwing _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, root_, 0, 0, "", false);
    }

    private static void processThrowingError(VariablesOffsets _vars, Throwing _cond, CustList<PartOffset> _parts) {
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildErrorReport(_vars, _cond, -1, _parts, 0, off_, root_, 0, 0, "");
    }
    private static void processForIterativeLoopReport(VariablesOffsets _vars, ForIterativeLoop _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getStack().last().getCurrent() == null) {
            AbstractCoverageResult result_ = _cov.getCoverLoops(_cond);
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
            _parts.add(new PartOffset(tag_,off_+ _vars.getKeyWords().getKeyWordIter().length()));
            tag_ = "<a name=\"m"+ _cond.getVariableNameOffset() +"\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        int off_ = _cond.getInitOffset();
        int offsetEndBlock_ = off_ + _cond.getInit().length();
        if (_vars.getStack().last().getIndexLoop() == 0) {
            OperationNode rootInit_ = _cond.getRootInit();
            buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, rootInit_, 0, 0, "", false);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexLoop(1);
        }
        off_ = _cond.getExpressionOffset();
        offsetEndBlock_ = off_ + _cond.getExpression().length();
        if (_vars.getStack().last().getIndexLoop() == 1) {
            OperationNode rootExp_ = _cond.getRootExp();
            buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, rootExp_, 1, 0, "", false);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexLoop(2);
        }
        off_ = _cond.getStepOffset();
        offsetEndBlock_ = off_ + _cond.getStep().length();
        OperationNode rootStep_ = _cond.getRootStep();
        buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, rootStep_, 2, 0, "", false);
        if (_vars.getState() != null) {
            return;
        }
        _vars.getStack().last().setIndexLoop(0);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForIterativeLoopError(VariablesOffsets _vars, ForIterativeLoop _cond, CustList<PartOffset> _parts) {
        if (_vars.getStack().last().getCurrent() == null) {
            StringList errs_ = _cond.getNameErrors();
            if (!errs_.isEmpty()) {
                String err_ = transform(StringUtil.join(errs_, "\n\n"));
                String tag_;
                tag_ = "<a title=\"" + err_ + "\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            } else {
                String tag_;
                tag_ = "<a name=\"m" + _cond.getVariableNameOffset() + "\">";
                _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            }
        }
        int off_ = _cond.getInitOffset();
        if (_vars.getStack().last().getIndexLoop() == 0) {
            OperationNode rootInit_ = _cond.getRootInit();
            buildErrorReport(_vars, _cond, -1, _parts, 0, off_, rootInit_, 0, 0, "");
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexLoop(1);
        }
        off_ = _cond.getExpressionOffset();
        if (_vars.getStack().last().getIndexLoop() == 1) {
            OperationNode rootExp_ = _cond.getRootExp();
            buildErrorReport(_vars, _cond, -1, _parts, 0, off_, rootExp_, 1, 0, "");
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexLoop(2);
        }
        off_ = _cond.getStepOffset();
        OperationNode rootStep_ = _cond.getRootStep();
        buildErrorReport(_vars, _cond, -1, _parts, 0, off_, rootStep_, 2, 0, "");
        if (_vars.getState() != null) {
            return;
        }
        _vars.getStack().last().setIndexLoop(0);
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForEachLoopReport(VariablesOffsets _vars, ForEachLoop _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getStack().last().getCurrent() == null) {
            AbstractCoverageResult result_ = _cov.getCoverLoops(_cond);
            String tagCov_;
            if (result_.isFullCovered()) {
                tagCov_ = "<span class=\"f\">";
            } else if (result_.isPartialCovered()) {
                tagCov_ = "<span class=\"p\">";
            } else {
                tagCov_ = "<span class=\"n\">";
            }
            int off_ = _cond.getOffset().getOffsetTrim();
            _parts.add(new PartOffset(tagCov_, off_));
            appendVars(_vars,_cond, _parts);
            String tag_;
            tag_ = "<a name=\"m" + _cond.getVariableNameOffset() + "\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            tag_ = "</span>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, root_, 0, 0, "", false);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForEachLoopError(VariablesOffsets _vars, ForEachLoop _cond, CustList<PartOffset> _parts) {
        if (_vars.getStack().last().getCurrent() == null) {
            appendVars(_vars,_cond, _parts);
            StringList errs_ = _cond.getNameErrors();
            if (!errs_.isEmpty()) {
                String err_ = transform(StringUtil.join(errs_,"\n\n"));
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
                String err_ = transform(StringUtil.join(_cond.getSepErrors(),"\n\n"));
                String tag_;
                tag_ = "<a title=\""+err_+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getSepOffset()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getSepOffset() + 1));
            }
        }
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildErrorReport(_vars, _cond, -1, _parts, 0, off_, root_, 0, 0, "");
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendVars(VariablesOffsets _vars, ForEachLoop _cond, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(_cond.getClassName().trim(), keyWordVar_)) {
            String tag_;
            tag_ = "<b title=\""+transform(_cond.getImportedClassName())+"\">";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset() + keyWordVar_.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsets());
        }
    }

    private static void processForEachTableReport(VariablesOffsets _vars, ForEachTable _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getStack().last().getCurrent() == null) {
            AbstractCoverageResult result_ = _cov.getCoverLoops(_cond);
            String tagCov_;
            if (result_.isFullCovered()) {
                tagCov_ = "<span class=\"f\">";
            } else if (result_.isPartialCovered()) {
                tagCov_ = "<span class=\"p\">";
            } else {
                tagCov_ = "<span class=\"n\">";
            }
            int off_ = _cond.getOffset().getOffsetTrim();
            _parts.add(new PartOffset(tagCov_, off_));
            KeyWords keyWords_ = _vars.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            appendFirstVar(_cond, _parts, keyWordVar_);
            String tagVar_;
            tagVar_ = "<a name=\"m" + _cond.getVariableNameOffsetFirst() + "\">";
            _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst()));
            tagVar_ = "</a>";
            _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
            appendSecondVar(_cond, _parts, keyWordVar_);
            String tag_;
            tag_ = "<a name=\"m" + _cond.getVariableNameOffsetSecond() + "\">";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond()));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
            tag_ = "</span>";
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, root_, 0, 0, "", false);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendSecondVar(ForEachTable _cond, CustList<PartOffset> _parts, String _keyWordVar) {
        if (StringUtil.quickEq(_cond.getClassNameSecond().trim(), _keyWordVar)) {
            String tag_;
            tag_ = "<b title=\""+transform(_cond.getImportedClassNameSecond())+"\">";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetSecond()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetSecond() + _keyWordVar.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsetsSecond());
        }
    }

    private static void processForEachTableError(VariablesOffsets _vars, ForEachTable _cond, CustList<PartOffset> _parts) {
        if (_vars.getStack().last().getCurrent() == null) {
            KeyWords keyWords_ = _vars.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            appendFirstVar(_cond, _parts, keyWordVar_);
            StringList errs_ = _cond.getNameErrorsFirst();
            if (!errs_.isEmpty()) {
                String err_ = transform(StringUtil.join(errs_, "\n\n"));
                String tagVar_;
                tagVar_ = "<a title=\"" + err_ + "\" class=\"e\">";
                _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst()));
                tagVar_ = "</a>";
                _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
            } else {
                String tagVar_;
                tagVar_ = "<a name=\"m" + _cond.getVariableNameOffsetFirst() + "\">";
                _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst()));
                tagVar_ = "</a>";
                _parts.add(new PartOffset(tagVar_, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
            }
            appendSecondVar(_cond, _parts, keyWordVar_);
            errs_ = _cond.getNameErrorsSecond();
            if (!errs_.isEmpty()) {
                String err_ = transform(StringUtil.join(errs_, "\n\n"));
                String tag_;
                tag_ = "<a title=\"" + err_ + "\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
            } else {
                String tag_;
                tag_ = "<a name=\"m" + _cond.getVariableNameOffsetSecond() + "\">";
                _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
            }
            if (!_cond.getSepErrors().isEmpty()) {
                String err_ = transform(StringUtil.join(_cond.getSepErrors(), "\n\n"));
                String tag_;
                tag_ = "<a title=\"" + err_ + "\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getSepOffset()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getSepOffset() + 1));
            }
        }
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildErrorReport(_vars, _cond, -1, _parts, 0, off_, root_, 0, 0, "");
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendFirstVar(ForEachTable _cond, CustList<PartOffset> _parts, String _keyWordVar) {
        if (StringUtil.quickEq(_cond.getClassNameFirst().trim(), _keyWordVar)) {
            String tag_;
            tag_ = "<b title=\""+transform(_cond.getImportedClassNameFirst())+"\">";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetFirst()));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetFirst() + _keyWordVar.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsetsFirst());
        }
    }

    private static void processElementBlockReport(VariablesOffsets _vars, ElementBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        AbstractInstancingOperation inst_ = (AbstractInstancingOperation) _cond.getRoot().getFirstChild().getNextSibling();
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        String uniqueFieldName_ = _cond.getUniqueFieldName();
        if (k_ == -1) {
            int len_ = _cond.getAnnotationsIndexes().size();
            int j_ = _vars.getStack().last().getIndexAnnotation();
            for (int i = j_; i < len_; i++) {
                int begin_ = _cond.getAnnotationsIndexes().get(i);
                int end_ = begin_ + _cond.getAnnotations().get(i).trim().length();
                OperationNode root_ = _cond.getRoots().get(i);
                buildCoverageReport(_vars, _cond, -1, _parts, _cov, i, begin_, end_, root_, 0, 0, "", true);
                if (_vars.getState() != null) {
                    return;
                }
                _vars.getStack().last().setIndexAnnotation(i+1);
            }
            _vars.getStack().last().setIndexAnnotation(0);
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = 0;
            String fileName_ = _vars.getCurrentFileName();
            AnaTypeFct ctor_ = inst_.getConstructor();
            if (ctor_ != null) {
                StringList list_ = new StringList();
                int fieldNameOffest_ = _cond.getFieldNameOffest();
                addParts(_vars, fileName_, ctor_, fieldNameOffest_, uniqueFieldName_.length(), list_, list_, _parts, fieldNameOffest_);
            } else {
                String tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() + uniqueFieldName_.length()));
            }
            _parts.addAllElts(_cond.getTypePartOffsets());
        }
        int blOffset_ = _cond.getValueOffest();
        int endBl_ = _cond.getValueOffest() + _cond.getValue().length();
        int tr_ = _cond.getTrOffset() - 1;
        buildCoverageReport(_vars, _cond, k_, _parts, _cov, 0, blOffset_, endBl_, inst_, 0, tr_, uniqueFieldName_, false);
        if (_vars.getState() != null) {
            return;
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }

    private static void processElementBlockError(VariablesOffsets _vars, ElementBlock _cond, CustList<PartOffset> _parts) {
        OperationNode firstChild_ = _cond.getRoot().getFirstChild();
        OperationNode next_ = null;
        if (firstChild_ != null) {
            next_ = firstChild_.getNextSibling();
        }
        AbstractInstancingOperation inst_ = null;
        if (next_ instanceof AbstractInstancingOperation) {
            inst_ = (AbstractInstancingOperation) next_;
        }
        String uniqueFieldName_ = _cond.getUniqueFieldName();
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        if (k_ == -1) {
            int len_ = _cond.getAnnotationsIndexes().size();
            int j_ = _vars.getStack().last().getIndexAnnotation();
            for (int i = j_; i < len_; i++) {
                int begin_ = _cond.getAnnotationsIndexes().get(i);
                OperationNode root_ = _cond.getRoots().get(i);
                buildErrorReport(_vars, _cond, -1, _parts, i, begin_, root_, 0, 0, "");
                if (_vars.getState() != null) {
                    return;
                }
                _vars.getStack().last().setIndexAnnotation(i+1);
            }
            _vars.getStack().last().setIndexAnnotation(0);
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = 0;
            String fileName_ = _vars.getCurrentFileName();
            StringList list_ = new StringList(_cond.getNameErrors());
            if (uniqueFieldName_.trim().isEmpty()) {
                String err_ = getLineErr(list_);
                String tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\" title=\"" + err_ + "\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() + 1));
                _vars.getStack().last().setIndexAnnotationGroup(-1);
                return;
            }
            if (inst_ == null) {
                list_.addAllElts(_cond.getRoot().getErrs());
                String err_ = getLineErr(list_);
                String tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\" title=\"" + err_ + "\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() + uniqueFieldName_.trim().length()));
                _vars.getStack().last().setIndexAnnotationGroup(-1);
                return;
            }
            list_.addAllElts(inst_.getErrs());
            String err_ = getLineErr(list_);
            AnaTypeFct ctor_ = inst_.getConstructor();
            if (ctor_ != null) {
                int fieldNameOffest_ = _cond.getFieldNameOffest();
                addParts(_vars, fileName_, ctor_, fieldNameOffest_, uniqueFieldName_.length(), list_, list_, _parts, fieldNameOffest_);
            } else {
                String tag_;
                if (!list_.isEmpty()) {
                    tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\" title=\"" + err_ + "\" class=\"e\">";
                } else {
                    tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\">";
                }
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() + uniqueFieldName_.length()));
            }
            _parts.addAllElts(_cond.getTypePartOffsets());
        }
        int blOffset_ = _cond.getValueOffest();
        int tr_ = _cond.getTrOffset() - 1;
        buildErrorReport(_vars, _cond, k_, _parts, 0, blOffset_, inst_, 0, tr_, uniqueFieldName_);
        if (_vars.getState() != null) {
            return;
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }
    private static void processFieldBlockReport(VariablesOffsets _vars, FieldBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotField(_vars, _cond, _parts, _cov);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = _vars.getStack().last().getIndexAnnotationGroup();
            _parts.addAllElts(_cond.getTypePartOffsets());
        }
        int blOffset_ = _cond.getValueOffset();
        int endBl_ = blOffset_ + _cond.getValue().length();
        OperationNode root_ = _cond.getRoot();
        buildCoverageReport(_vars, _cond, k_, _parts, _cov, 0, blOffset_, endBl_, root_, 0, 0, "", false);
        if (_vars.getState() != null) {
            return;
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }
    private static void processFieldBlockError(VariablesOffsets _vars, FieldBlock _cond, CustList<PartOffset> _parts) {
        int blOffset_ = _cond.getValueOffset();
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotFieldErr(_vars, _cond, _parts);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = _vars.getStack().last().getIndexAnnotationGroup();
            _parts.addAllElts(_cond.getTypePartOffsets());
            StringList errs_ = _cond.getNameRetErrors();
            if (!errs_.isEmpty()) {
                String err_ = StringUtil.join(errs_, "\n\n");
                if (_cond.getValue().trim().isEmpty()) {
                    blOffset_ = _cond.getClassNameOffset() + _cond.getClassName().length();
                }
                _parts.add(new PartOffset("<a title=\"" + err_ + "\" class=\"e\">", blOffset_));
                int endBl_ = blOffset_ + Math.max(1, _cond.getValue().length());
                _parts.add(new PartOffset("</a>", endBl_));
                _vars.getStack().last().setIndexAnnotationGroup(-1);
                return;
            }
        }
        OperationNode root_ = _cond.getRoot();
        buildErrorReport(_vars, _cond, k_, _parts, 0, blOffset_, root_, 0, 0, "");
        if (_vars.getState() != null) {
            return;
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }

    private static void buildAnnotField(VariablesOffsets _vars, FieldBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int len_ = _cond.getAnnotationsIndexes().size();
        int j_ = _vars.getStack().last().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            int end_ = begin_ + _cond.getAnnotations().get(i).trim().length();
            OperationNode root_ = _cond.getRoots().get(i);
            buildCoverageReport(_vars, _cond, -1, _parts, _cov, i, begin_, end_, root_, 0, 0, "", true);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotation(i+1);
        }
        _vars.getStack().last().setIndexAnnotation(0);
    }
    private static void buildAnnotFieldErr(VariablesOffsets _vars, FieldBlock _cond, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        int j_ = _vars.getStack().last().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            OperationNode root_ = _cond.getRoots().get(i);
            buildErrorReport(_vars, _cond, -1, _parts, i, begin_, root_, 0, 0, "");
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotation(i+1);
        }
        _vars.getStack().last().setIndexAnnotation(0);
    }
    private static void processConstructorBlockReport(VariablesOffsets _vars, ConstructorBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsReport(_vars, _cond, _parts, _cov);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = _vars.getStack().last().getIndexAnnotationGroup();
            int begName_ = _cond.getNameOffset();
            _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
            _parts.add(new PartOffset("</a>", _cond.getLeftPar()));
        }
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = k_; i < len_; i++) {
            buildAnnotationsReport(_vars,_cond,i, _parts, _cov);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(i+1);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            _parts.add(new PartOffset("</a>",off_+param_.length()));
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }
    private static void processConstructorBlockError(VariablesOffsets _vars, ConstructorBlock _cond, CustList<PartOffset> _parts) {
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsError(_vars, _cond, _parts);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = _vars.getStack().last().getIndexAnnotationGroup();
            int begName_ = _cond.getNameOffset();
            StringList errsName_ = _cond.getNameErrors();
            if (errsName_.isEmpty()) {
                _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
            } else {
                String err_ = transform(StringUtil.join(errsName_,"\n\n"));
                _parts.add(new PartOffset("<a name=\"m"+begName_+"\" title=\""+err_+"\" class=\"e\">",begName_));
            }
            _parts.add(new PartOffset("</a>", _cond.getLeftPar()));
        }
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = k_; i < len_; i++) {
            buildAnnotationsError(_vars,_cond,i, _parts);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(i+1);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            StringList errs_ = _cond.getParamErrors().get(i);
            if (errs_.isEmpty()) {
                _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            } else {
                String err_ = transform(StringUtil.join(errs_,"\n\n"));
                _parts.add(new PartOffset("<a name=\"m"+off_+"\" title=\""+err_+"\" class=\"e\">",off_));
            }
            _parts.add(new PartOffset("</a>",off_+param_.length()));
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }
    private static void processOverridableBlockReport(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsReport(_vars,_cond, _parts, _cov);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(0);
        }
        int begName_ = _cond.getNameOffset();
        if (!(_cond instanceof OverridableBlock) || ((OverridableBlock)_cond).getKind() == MethodKind.OPERATOR) {
            if (k_ == -1) {
                addNameParts(_cond, _parts, begName_, _cond.getName().length());
                if (_cond instanceof OperatorBlock) {
                    OperatorBlock op_ = (OperatorBlock) _cond;
                    int lenImp_ = op_.getImports().size();
                    for (int i = 0; i < lenImp_; i++) {
                        _parts.add(new PartOffset("<span class=\"i\">", op_.getImportsOffset().get(i)));
                        _parts.add(new PartOffset("</span>", op_.getImportsOffset().get(i) + op_.getImports().get(i).length()));
                    }
                }
                _parts.addAllElts(_cond.getPartOffsetsReturn());
            }
            refParams(_vars,_cond, _parts, _cov);
            return;
        }
        if (k_ == -1) {
            _parts.addAllElts(_cond.getPartOffsetsReturn());
        }
        OverridableBlock m_ = (OverridableBlock) _cond;
        if (m_.getKind() == MethodKind.GET_INDEX) {
            if (k_ == -1) {
                addNameParts(_cond, _parts, begName_, _vars.getKeyWords().getKeyWordThis().length());
            }
            refParams(_vars,_cond, _parts, _cov);
            return;
        }
        if (m_.getKind() == MethodKind.SET_INDEX) {
            if (k_ == -1) {
                addNameParts(_cond, _parts, begName_, _vars.getKeyWords().getKeyWordThis().length());
            }
            refParams(_vars,_cond, _parts, _cov);
            return;
        }
        if (k_ == -1) {
            addNameParts(_cond, _parts, begName_, _cond.getName().length());
        }
        refParams(_vars,_cond, _parts, _cov);
        if (_vars.getState() != null) {
            return;
        }
        processOverridableRedef(_vars,m_,_parts);
    }

    private static void processAnonymousFctReport(int _begin, AnonymousFunctionBlock _cond, CustList<PartOffset> _parts) {
        int begName_ = _cond.getNameOffset();
        _parts.add(new PartOffset("<span class=\"t\">", _begin));
        refParams(_cond, _parts);
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        addNameParts(_cond,_parts, begName_, 2);
    }


    private static void processOverridableBlockError(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts) {
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsError(_vars,_cond, _parts);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(0);
        }
        int begName_ = _cond.getNameOffset();
        if (!(_cond instanceof OverridableBlock) || ((OverridableBlock)_cond).getKind() == MethodKind.OPERATOR) {
            if (k_ == -1) {
                addNameParts(_cond, _parts, begName_, _cond.getName().length());
                if (_cond instanceof OperatorBlock) {
                    OperatorBlock op_ = (OperatorBlock) _cond;
                    int lenImp_ = op_.getImports().size();
                    for (int i = 0; i < lenImp_; i++) {
                        _parts.add(new PartOffset("<span class=\"i\">", op_.getImportsOffset().get(i)));
                        _parts.add(new PartOffset("</span>", op_.getImportsOffset().get(i) + op_.getImports().get(i).length()));
                    }
                }
                _parts.addAllElts(_cond.getPartOffsetsReturn());
            }
            refParamsError(_vars,_cond, _parts);
            return;
        }
        if (k_ == -1) {
            _parts.addAllElts(_cond.getPartOffsetsReturn());
        }
        OverridableBlock m_ = (OverridableBlock) _cond;
        if (m_.getKind() == MethodKind.GET_INDEX) {
            if (k_ == -1) {
                addNameParts(_cond, _parts, begName_, _vars.getKeyWords().getKeyWordThis().length());
            }
            refParamsError(_vars,_cond, _parts);
            return;
        }
        if (m_.getKind() == MethodKind.SET_INDEX) {
            if (k_ == -1) {
                addNameParts(_cond, _parts, begName_, _vars.getKeyWords().getKeyWordThis().length());
            }
            refParamsError(_vars,_cond, _parts);
            return;
        }
        if (k_ == -1) {
            addNameParts(_cond, _parts, begName_, _cond.getName().length());
        }
        refParamsError(_vars,_cond, _parts);
        if (_vars.getState() != null) {
            return;
        }
        processOverridableRedef(_vars,m_,_parts);
    }

    private static void processOverridableRedef(VariablesOffsets _vars, OverridableBlock _cond, CustList<PartOffset> _parts) {
        for (PartOffsetsClassMethodId p:_cond.getAllInternTypesParts()) {
            _parts.addAllElts(p.getTypes());
            ClassMethodId id_ = p.getId();
            if (id_ != null) {
                int rc_ = p.getBegin();
                int len_ = p.getLength();
                CustList<PartOffset> partMethod_ = new CustList<PartOffset>();
                StringList l_ = new StringList();
                LinkageUtil.addParts(_vars.getDisplayedStrings(), _cond.getFile().getRenderFileName(),p.getFct(),rc_,len_, l_,l_,partMethod_);
                _parts.addAllElts(partMethod_);
            }
            _parts.addAllElts(p.getSuperTypes());
        }
    }
    private static void processAnonymousFctBlockError(int _begin, AnonymousFunctionBlock _cond, CustList<PartOffset> _parts) {
        int begName_ = _cond.getNameOffset();
        _parts.add(new PartOffset("<span class=\"t\">", _begin));
        refParamsError(_cond, _parts);
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        StringList errs_ = new StringList();
        Block child_ = _cond.getFirstChild();
        if (isImplicitReturn(child_)){
            errs_.addAllElts(child_.getErrorsBlock());
        }
        addNameParts(errs_,_cond,_parts, begName_, 2);
    }

    private static void processAnnotationMethodBlockReport(VariablesOffsets _vars, AnnotationMethodBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsReport(_vars,_cond, _parts, _cov);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = _vars.getStack().last().getIndexAnnotationGroup();
            _parts.addAllElts(_cond.getPartOffsetsReturn());
            int begName_ = _cond.getNameOffset();
            addNameParts(_cond,_parts, begName_, _cond.getName().length());
        }
        OperationNode root_ = _cond.getRoot();
        if (root_ != null) {
            int blOffset_ = _cond.getDefaultValueOffset();
            int endBl_ = blOffset_ + _cond.getDefaultValue().length();
            buildCoverageReport(_vars, _cond, k_, _parts, _cov, 0, blOffset_, endBl_, root_, 0, 0, "", false);
            if (_vars.getState() != null) {
                return;
            }
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }

    private static void processAnnotationMethodBlockError(VariablesOffsets _vars, AnnotationMethodBlock _cond, CustList<PartOffset> _parts) {
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsError(_vars,_cond, _parts);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = 0;
            _parts.addAllElts(_cond.getPartOffsetsReturn());
            if (_cond.getName().trim().isEmpty()) {
                int begName_ = _cond.getNameOffset()+ _cond.getRightPar();
                addNameParts(_cond, _parts, begName_,0);
            } else {
                int begName_ = _cond.getNameOffset();
                addNameParts(_cond, _parts, begName_, _cond.getName().length());
            }
        }
        OperationNode root_ = _cond.getRoot();
        if (root_ != null) {
            int blOffset_ = _cond.getDefaultValueOffset();
            buildErrorReport(_vars, _cond, k_, _parts, 0, blOffset_, root_, 0, 0, "");
            if (_vars.getState() != null) {
                return;
            }
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }

    private static void addNameParts(NamedFunctionBlock _named,CustList<PartOffset> _parts, int _begName, int _len) {
        addNameParts(new StringList(),_named,_parts,_begName,_len);
    }
    private static void addNameParts(StringList _list,NamedFunctionBlock _named,CustList<PartOffset> _parts, int _begName, int _len) {
        StringList errs_ = new StringList(_named.getNameErrors());
        errs_.addAllElts(_list);
        if (errs_.isEmpty()) {
            int endName_ = _begName + _len;
            _parts.add(new PartOffset("<a name=\"m"+_begName+"\">",_begName));
            _parts.add(new PartOffset("</a>",endName_));
            return;
        }
        String err_ = transform(StringUtil.join(errs_,"\n\n"));
        int endName_ = _begName + Math.max(_len,1);
        _parts.add(new PartOffset("<a name=\"m"+_begName+"\" title=\""+err_+"\" class=\"e\">",_begName));
        _parts.add(new PartOffset("</a>",endName_));
    }
    private static void processInnerElementBlockReport(VariablesOffsets _vars, InnerElementBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        AbstractInstancingOperation inst_ = (AbstractInstancingOperation) _cond.getRoot().getFirstChild().getNextSibling();
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        String uniqueFieldName_ = _cond.getUniqueFieldName();
        if (k_ == -1) {
            processAnnotationReport(_vars, _cond, _parts, _cov);
            if (_vars.getState() != null) {
                return;
            }
            String fileName_ = _vars.getCurrentFileName();
            AnaTypeFct ctor_ = inst_.getConstructor();

            if (ctor_ != null) {
                StringList list_ = new StringList();
                int fieldNameOffest_ = _cond.getFieldNameOffest();
                addParts(_vars, fileName_, ctor_, fieldNameOffest_, uniqueFieldName_.length(), list_, list_, _parts, fieldNameOffest_);
            } else {
                String tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() + uniqueFieldName_.length()));
            }
            _parts.addAllElts(_cond.getTypePartOffsets());
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = 0;
        }
        int blOffset_ = _cond.getValueOffest();
        int endBl_ = _cond.getValueOffest() + _cond.getValue().length();
        int tr_ = _cond.getTrOffset() - 1;
        buildCoverageReport(_vars, _cond, k_, _parts, _cov, 0, blOffset_, endBl_, inst_, 0, tr_, uniqueFieldName_, false);
        if (_vars.getState() != null) {
            return;
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }

    private static void processInnerElementBlockError(VariablesOffsets _vars, InnerElementBlock _cond, CustList<PartOffset> _parts) {
        OperationNode firstChild_ = _cond.getRoot().getFirstChild();
        StringList errs_ = new StringList();
        OperationNode next_ = null;
        if (firstChild_ != null) {
            errs_.addAllElts(firstChild_.getErrs());
            next_ = firstChild_.getNextSibling();
        }
        AbstractInstancingOperation inst_ = null;
        if (next_ != null) {
            errs_.addAllElts(next_.getErrs());
        }
        if (next_ instanceof AbstractInstancingOperation) {
            inst_ = (AbstractInstancingOperation) next_;
        }
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        String uniqueFieldName_ = _cond.getUniqueFieldName();
        if (k_ == -1) {
            processAnnotationError(_vars, _cond, _parts);
            if (_vars.getState() != null) {
                return;
            }
            String fileName_ = _vars.getCurrentFileName();
            StringList list_ = new StringList(_cond.getNameErrors());
            if (uniqueFieldName_.trim().isEmpty()) {
                String err_ = getLineErr(list_);
                String tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\" title=\"" + err_ + "\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() + 1));
                return;
            }
            if (inst_ == null) {
                errs_.addAllElts(_cond.getRoot().getErrs());
                list_.addAllElts(errs_);
                String err_ = getLineErr(list_);
                String tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\" title=\"" + err_ + "\" class=\"e\">";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() + uniqueFieldName_.trim().length()));
                return;
            }
            list_.addAllElts(inst_.getErrs());
            String err_ = getLineErr(list_);
            AnaTypeFct ctor_ = inst_.getConstructor();
            if (ctor_ != null) {
                int fieldNameOffest_ = _cond.getFieldNameOffest();
                addParts(_vars, fileName_, ctor_, fieldNameOffest_, uniqueFieldName_.length(), list_, list_, _parts, fieldNameOffest_);
            } else {
                String tag_;
                if (!list_.isEmpty()) {
                    tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\" title=\"" + err_ + "\" class=\"e\">";
                } else {
                    tag_ = "<a name=\"m" + _cond.getFieldNameOffest() + "\">";
                }
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest() + uniqueFieldName_.length()));
            }
            _parts.addAllElts(_cond.getTypePartOffsets());
            _vars.getStack().last().setIndexAnnotationGroup(0);
            k_ = 0;
        }
        int blOffset_ = _cond.getValueOffest();
        int tr_ = _cond.getTrOffset() - 1;
        buildErrorReport(_vars, _cond, k_, _parts, 0, blOffset_, inst_, 0, tr_, uniqueFieldName_);
        if (_vars.getState() != null) {
            return;
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }

    private static String getLineErr(StringList _list) {
        String err_="";
        if (!_list.isEmpty()) {
            err_ = LinkageUtil.transform(StringUtil.join(_list,"\n\n"));
        }
        return err_;
    }

    private static void processRootBlockReport(VariablesOffsets _vars, RootBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_cond instanceof AnonymousTypeBlock) {
            _parts.add(new PartOffset("<span class=\"t\">", _cond.getBegin()));
        }
        processAnnotationReport(_vars,_cond, _parts, _cov);
        if (_vars.getState() != null) {
            return;
        }
        int len_ = _cond.getImports().size();
        for (int i = 0; i < len_; i++) {
            _parts.add(new PartOffset("<span class=\"i\">", _cond.getImportsOffset().get(i)));
            _parts.add(new PartOffset("</span>", _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
        processInterfaceInit(_cond, _parts);
        int idRowCol_ = _cond.getIdRowCol();
        _parts.add(new PartOffset("<a name=\"m"+ idRowCol_ +"\">", idRowCol_));
        _parts.add(new PartOffset("</a>", idRowCol_ + _cond.getNameLength()));
        for (PartOffset p: _cond.getConstraintsParts()) {
            _parts.add(p);
        }

        for (PartOffset p: _cond.getSuperTypesParts()) {
            _parts.add(p);
        }
    }

    private static void processRootBlockError(VariablesOffsets _vars, RootBlock _cond, CustList<PartOffset> _parts) {
        if (_cond instanceof AnonymousTypeBlock) {
            _parts.add(new PartOffset("<span class=\"t\">", _cond.getBegin()));
        }
        processAnnotationError(_vars,_cond, _parts);
        if (_vars.getState() != null) {
            return;
        }
        if (!_cond.getErrorsBlock().isEmpty()) {
            StringList listCat_ = _cond.getErrorsBlock();
            _parts.add(new PartOffset("<a title=\""+ LinkageUtil.transform(StringUtil.join(listCat_,"\n\n"))+"\" class=\"e\">", _cond.getBegin()));
            _parts.add(new PartOffset("</a>", _cond.getBegin() + _cond.getLengthHeader()));

        }
        int len_ = _cond.getImports().size();
        for (int i = 0; i < len_; i++) {
            _parts.add(new PartOffset("<span class=\"i\">", _cond.getImportsOffset().get(i)));
            _parts.add(new PartOffset("</span>", _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
        processInterfaceInit(_cond, _parts);
        int nameLength_ = _cond.getNameLength();
        if (nameLength_ > 0) {
            StringList list_ = _cond.getNameErrors();
            int idRowCol_ = _cond.getIdRowCol();
            if (!list_.isEmpty()) {
                _parts.add(new PartOffset("<a name=\"m"+ idRowCol_ +"\" title=\""+ LinkageUtil.transform(StringUtil.join(list_,"\n\n"))+"\" class=\"e\">", idRowCol_));
            } else {
                _parts.add(new PartOffset("<a name=\"m"+ idRowCol_ +"\">", idRowCol_));
            }
            _parts.add(new PartOffset("</a>", idRowCol_ + nameLength_));
        }
        for (PartOffset p: _cond.getConstraintsParts()) {
            _parts.add(p);
        }

        for (PartOffset p: _cond.getSuperTypesParts()) {
            _parts.add(p);
        }
    }

    private static void processInterfaceInit(RootBlock _cond, CustList<PartOffset> _parts) {
        if (_cond instanceof AnonymousTypeBlock) {
            return;
        }
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
    private static void processAnnotationReport(VariablesOffsets _vars, RootBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int len_ = _cond.getAnnotationsIndexes().size();
        int j_ = _vars.getStack().last().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            int end_ = begin_ + _cond.getAnnotations().get(i).trim().length();
            OperationNode root_ = _cond.getRoots().get(i);
            buildCoverageReport(_vars, _cond, -1, _parts, _cov, i, begin_, end_, root_, 0, 0, "", true);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotation(i+1);
        }
        _vars.getStack().last().setIndexAnnotation(0);
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int len_ = _cond.getAnnotationsIndexes().size();
        int j_ = _vars.getStack().last().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            int end_ = begin_ + _cond.getAnnotations().get(i).trim().length();
            OperationNode root_ = _cond.getRoots().get(i);
            buildCoverageReport(_vars, _cond, -1, _parts, _cov, i, begin_, end_, root_, 0, 0, "", true);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotation(i+1);
        }
        _vars.getStack().last().setIndexAnnotation(0);
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars, NamedFunctionBlock _cond, int _index, CustList<PartOffset> _parts, Coverage _cov) {
        int len_ = _cond.getAnnotationsIndexesParams().get(_index).size();
        StringList list_ = _cond.getAnnotationsParams().get(_index);
        int j_ = _vars.getStack().last().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexesParams().get(_index).get(i);
            int end_ = begin_ + list_.get(i).trim().length();
            OperationNode root_ = _cond.getRootsList().get(_index).get(i);
            buildCoverageReport(_vars, _cond, _index, _parts, _cov, i, begin_, end_, root_, 0, 0, "", true);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotation(i+1);
        }
        _vars.getStack().last().setIndexAnnotation(0);
        _vars.getStack().last().setIndexAnnotationGroup(_index+1);
    }

    private static void processAnnotationError(VariablesOffsets _vars, RootBlock _cond, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        int j_ = _vars.getStack().last().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            OperationNode root_ = _cond.getRoots().get(i);
            buildErrorReport(_vars, _cond, -1, _parts, i, begin_, root_, 0, 0, "");
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotation(i+1);
        }
        _vars.getStack().last().setIndexAnnotation(0);
    }
    private static void buildAnnotationsError(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexes().size();
        int j_ = _vars.getStack().last().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexes().get(i);
            OperationNode root_ = _cond.getRoots().get(i);
            buildErrorReport(_vars, _cond, -1, _parts, i, begin_, root_, 0, 0, "");
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotation(i+1);
        }
        _vars.getStack().last().setIndexAnnotation(0);
    }
    private static void buildAnnotationsError(VariablesOffsets _vars, NamedFunctionBlock _cond, int _index, CustList<PartOffset> _parts) {
        int len_ = _cond.getAnnotationsIndexesParams().get(_index).size();
        int j_ = _vars.getStack().last().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            int begin_ = _cond.getAnnotationsIndexesParams().get(_index).get(i);
            OperationNode root_ = _cond.getRootsList().get(_index).get(i);
            buildErrorReport(_vars, _cond, _index, _parts, i, begin_, root_, 0, 0, "");
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotation(i+1);
        }
        _vars.getStack().last().setIndexAnnotation(0);
        _vars.getStack().last().setIndexAnnotationGroup(_index+1);
    }
    private static void refParams(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int len_ = _cond.getParametersNamesOffset().size();
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        for (int i = k_; i < len_; i++) {
            buildAnnotationsReport(_vars,_cond,i, _parts, _cov);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(i+1);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            _parts.add(new PartOffset("</a>",off_+param_.length()));
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }

    private static void refParams(AnonymousFunctionBlock _cond, CustList<PartOffset> _parts) {
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            _parts.add(new PartOffset("</a>",off_+param_.length()));
        }
    }

    private static void refParamsError(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts) {
        int len_ = _cond.getParametersNamesOffset().size();
        int k_ = _vars.getStack().last().getIndexAnnotationGroup();
        for (int i = k_; i < len_; i++) {
            buildAnnotationsError(_vars,_cond,i, _parts);
            if (_vars.getState() != null) {
                return;
            }
            _vars.getStack().last().setIndexAnnotationGroup(i+1);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            StringList errs_ = _cond.getParamErrors().get(i);
            if (errs_.isEmpty()) {
                _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
                _parts.add(new PartOffset("</a>",off_+param_.length()));
            } else {
                String err_ = transform(StringUtil.join(errs_,"\n\n"));
                _parts.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",off_));
                _parts.add(new PartOffset("</a>",off_+Math.max(1,param_.length())));
            }
        }
        _vars.getStack().last().setIndexAnnotationGroup(-1);
    }

    private static void refParamsError(AnonymousFunctionBlock _cond, CustList<PartOffset> _parts) {
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            StringList errs_ = _cond.getParamErrors().get(i);
            if (errs_.isEmpty()) {
                _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
                _parts.add(new PartOffset("</a>",off_+param_.length()));
            } else {
                String err_ = transform(StringUtil.join(errs_,"\n\n"));
                _parts.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",off_));
                _parts.add(new PartOffset("</a>",off_+Math.max(1,param_.length())));
            }
        }
    }
    private static void processConditionReport(Condition _cond, VariablesOffsets _vars, CustList<PartOffset> _parts, Coverage _cov) {
        int off_ =  _cond.getConditionOffset();
        int offsetEndBlock_ = off_ + _cond.getCondition().length();
        OperationNode root_ = _cond.getRoot();
        buildCoverageReport(_vars, _cond, -1, _parts, _cov, 0, off_, offsetEndBlock_, root_, 0, 0, "", false);
    }
    private static void processConditionError(Condition _cond, VariablesOffsets _vars, CustList<PartOffset> _parts) {
        int off_ =  _cond.getConditionOffset();
        if (_vars.getStack().last().getCurrent() == null) {
            if (!_cond.getErr().isEmpty()) {
                _parts.add(new PartOffset("<a title=\"" + _cond.getErr() + "\" class=\"e\">", off_));
                _parts.add(new PartOffset("</a>", off_ + 1));
            }
        }
        OperationNode root_ = _cond.getRoot();
        buildErrorReport(_vars, _cond, -1, _parts, 0, off_, root_, 0, 0, "");
    }

    private static void buildCoverageReport(VariablesOffsets _vars, Block _cond, int _index, CustList<PartOffset> _parts, Coverage _cov, int _indexAnnotation, int _begin, int _end, OperationNode _root, int _indexLoop, int _tr, String _field, boolean _annot) {
        OperationNode current_ = getCurrent(_vars, _root);
        buildCoverageReport(_indexLoop, _index, _indexAnnotation, _vars, _begin, _cond, current_, _root, _end, _parts, _tr, _field, _annot, _cov);
    }

    private static void buildErrorReport(VariablesOffsets _vars, Block _cond, int _index, CustList<PartOffset> _parts, int _indexAnnotation, int _begin, OperationNode _root, int _indexLoop, int _tr, String _field) {
        OperationNode current_ = getCurrent(_vars, _root);
        buildErrorReport(_indexLoop, _index, _indexAnnotation, _vars, _begin, _cond, current_, _root, _parts, _tr, _field);
    }

    private static OperationNode getCurrent(VariablesOffsets _vars,OperationNode _root) {
        OperationNode current_ = _vars.getStack().last().getCurrent();
        if (current_ == null) {
            return _root;
        }
        return current_;
    }

    private static void buildCoverageReport(int _indexLoop, int _indexAnnotationGroup, int _indexAnnotation, VariablesOffsets _vars, int _offsetBlock,
                                            Block _block,
                                            OperationNode _from, OperationNode _root,
                                            int _endBlock,
                                            CustList<PartOffset> _parts, int _tr, String _fieldName, boolean _annotation, Coverage _cov) {
        int sum_ = _tr + _offsetBlock - _fieldName.length();
        String currentFileName_ = _vars.getCurrentFileName();
        boolean addCover_ = !(_block instanceof CaseCondition);
        OperationNode val_ = _from;
        while (true) {
            if (!_vars.getVisited().containsObj(val_)) {
                AbstractCoverageResult result_ = getCovers(_block, val_, _cov, _annotation, _indexAnnotationGroup, _indexAnnotation);
                getBeginOpReport(_block, _parts, _fieldName, _root, val_, sum_, addCover_, result_, _cov, _annotation, _indexAnnotationGroup, _indexAnnotation);
                leftReport(_vars, _block,sum_,val_, result_,_parts, currentFileName_);
                OperationNode firstChildOp_ = val_.getFirstChild();
                if (firstChildOp_ != null) {
                    val_ = firstChildOp_;
                    continue;
                }
                if (val_ instanceof AnonymousInstancingOperation) {
                    LinkageStackElement state_ = new LinkageStackElement();
                    AnonymousTypeBlock block_ = ((AnonymousInstancingOperation) val_).getBlock();
                    state_.setBlock(block_);
                    state_.setIndexEnd(block_.getIndexEnd());
                    _vars.setState(state_);
                    _vars.getStack().last().setCurrent(val_);
                    _vars.getStack().last().setBlock(_block);
                    _vars.getStack().last().setIndexLoop(_indexLoop);
                    _vars.getStack().last().setIndexAnnotation(_indexAnnotation);
                    _vars.getStack().last().setIndexAnnotationGroup(_indexAnnotationGroup);
                    _vars.getVisited().add(val_);
                    break;
                }
                if (val_ instanceof AnonymousLambdaOperation) {
                    LinkageStackElement state_ = new LinkageStackElement();
                    AnonymousFunctionBlock block_ = ((AnonymousLambdaOperation) val_).getBlock();
                    state_.setBlock(block_);
                    state_.setIndexEnd(block_.getIndexEnd());
                    _vars.setState(state_);
                    _vars.getStack().last().setCurrent(val_);
                    _vars.getStack().last().setBlock(_block);
                    _vars.getStack().last().setIndexLoop(_indexLoop);
                    _vars.getStack().last().setIndexAnnotation(_indexAnnotation);
                    _vars.getStack().last().setIndexAnnotationGroup(_indexAnnotationGroup);
                    _vars.getVisited().add(val_);
                    break;
                }
            }
            boolean stopOp_ = false;
            while (true) {
                MethodOperation parent_ = val_.getParent();
                if (parent_ == null) {
                    stopOp_ = true;
                    getEnd(_parts, _fieldName, addCover_, _endBlock + _tr);
                    _vars.getStack().last().setCurrent(null);
                    break;
                }
                int offsetEnd_ = getOffsetEnd(sum_, val_, parent_);
                processImplicit(_vars,currentFileName_,offsetEnd_,val_, parent_,_parts);
                String tag_ = getEndTag(addCover_, val_, _root, _fieldName);
                OperationNode nextSiblingOp_ = val_.getNextSibling();
                _parts.add(new PartOffset(tag_,offsetEnd_));
                processUnaryRightOperations(_vars, currentFileName_, sum_, offsetEnd_, val_,parent_, _parts);
                if (nextSiblingOp_ != null) {
                    middleReport(_vars,currentFileName_,_block, offsetEnd_,val_,nextSiblingOp_,
                            parent_,_parts, _cov, _annotation, _indexAnnotationGroup, _indexAnnotation);
                    val_=nextSiblingOp_;
                    break;
                }
                if (parent_ instanceof AnonymousInstancingOperation) {
                    LinkageStackElement state_ = new LinkageStackElement();
                    AnonymousTypeBlock block_ = ((AnonymousInstancingOperation) parent_).getBlock();
                    state_.setBlock(block_);
                    state_.setIndexEnd(block_.getIndexEnd());
                    _vars.setState(state_);
                    _vars.getStack().last().setCurrent(parent_);
                    _vars.getStack().last().setBlock(_block);
                    _vars.getStack().last().setIndexLoop(_indexLoop);
                    _vars.getStack().last().setIndexAnnotation(_indexAnnotation);
                    _vars.getStack().last().setIndexAnnotationGroup(_indexAnnotationGroup);
                    _vars.getVisited().add(parent_);
                    stopOp_ = true;
                    break;
                }
                boolean st_ = end(_vars, parent_, currentFileName_, offsetEnd_, _parts, _root);
                if (st_) {
                    stopOp_ = true;
                }
                if (stopOp_) {
                    getEnd(_parts, _fieldName, addCover_, _endBlock + _tr);
                    _vars.getStack().last().setCurrent(null);
                    break;
                }
                val_ = parent_;
            }
            if (stopOp_) {
                break;
            }
        }
    }
    private static void buildErrorReport(int _indexLoop, int _indexAnnotationGroup, int _indexAnnotation, VariablesOffsets _vars, int _offsetBlock,
                                         Block _block,
                                         OperationNode _from, OperationNode _root,
                                         CustList<PartOffset> _parts, int _tr, String _fieldName) {
        if (_from == null) {
            return;
        }
        int sum_ = _tr + _offsetBlock - _fieldName.length();
        String currentFileName_ = _vars.getCurrentFileName();
        OperationNode val_ = _from;
        while (true) {
            if (!_vars.getVisited().containsObj(val_)) {
                leftError(_vars, _block,sum_,val_, _parts, currentFileName_);
                OperationNode firstChildOp_ = val_.getFirstChild();
                if (firstChildOp_ != null) {
                    val_ = firstChildOp_;
                    continue;
                }
                if (val_ instanceof AnonymousInstancingOperation) {
                    LinkageStackElement state_ = new LinkageStackElement();
                    AnonymousTypeBlock block_ = ((AnonymousInstancingOperation) val_).getBlock();
                    state_.setBlock(block_);
                    state_.setIndexEnd(block_.getIndexEnd());
                    _vars.setState(state_);
                    _vars.getStack().last().setCurrent(val_);
                    _vars.getStack().last().setBlock(_block);
                    _vars.getStack().last().setIndexLoop(_indexLoop);
                    _vars.getStack().last().setIndexAnnotation(_indexAnnotation);
                    _vars.getStack().last().setIndexAnnotationGroup(_indexAnnotationGroup);
                    _vars.getVisited().add(val_);
                    break;
                }
                if (val_ instanceof AnonymousLambdaOperation) {
                    LinkageStackElement state_ = new LinkageStackElement();
                    AnonymousFunctionBlock block_ = ((AnonymousLambdaOperation) val_).getBlock();
                    state_.setBlock(block_);
                    state_.setIndexEnd(block_.getIndexEnd());
                    _vars.setState(state_);
                    _vars.getStack().last().setCurrent(val_);
                    _vars.getStack().last().setBlock(_block);
                    _vars.getStack().last().setIndexLoop(_indexLoop);
                    _vars.getStack().last().setIndexAnnotation(_indexAnnotation);
                    _vars.getStack().last().setIndexAnnotationGroup(_indexAnnotationGroup);
                    _vars.getVisited().add(val_);
                    break;
                }
            }
            boolean stopOp_ = false;
            while (true) {
                MethodOperation parent_ = val_.getParent();
                if (parent_ == null) {
                    stopOp_ = true;
                    _vars.getStack().last().setCurrent(null);
                    break;
                }
                int offsetEnd_ = getOffsetEnd(sum_, val_, parent_);
                processImplicit(_vars,currentFileName_,offsetEnd_,val_, parent_,_parts);
                OperationNode nextSiblingOp_ = val_.getNextSibling();
                processUnaryRightOperations(_vars, currentFileName_, sum_, offsetEnd_, val_,parent_, _parts);
                if (nextSiblingOp_ != null) {
                    middleError(_vars,currentFileName_, offsetEnd_,val_,nextSiblingOp_,
                            parent_,_parts);
                    val_=nextSiblingOp_;
                    break;
                }
                if (parent_ instanceof AnonymousInstancingOperation) {
                    LinkageStackElement state_ = new LinkageStackElement();
                    AnonymousTypeBlock block_ = ((AnonymousInstancingOperation) parent_).getBlock();
                    state_.setBlock(block_);
                    state_.setIndexEnd(block_.getIndexEnd());
                    _vars.setState(state_);
                    _vars.getStack().last().setCurrent(parent_);
                    _vars.getStack().last().setBlock(_block);
                    _vars.getStack().last().setIndexLoop(_indexLoop);
                    _vars.getStack().last().setIndexAnnotation(_indexAnnotation);
                    _vars.getStack().last().setIndexAnnotationGroup(_indexAnnotationGroup);
                    _vars.getVisited().add(parent_);
                    stopOp_ = true;
                    break;
                }
                boolean st_ = end(_vars, parent_, currentFileName_, offsetEnd_, _parts, _root);
                if (st_) {
                    stopOp_ = true;
                }
                if (stopOp_) {
                    _vars.getStack().last().setCurrent(null);
                    break;
                }
                val_ = parent_;
            }
            if (stopOp_) {
                break;
            }
        }
    }

    private static boolean end(VariablesOffsets _vars, MethodOperation _parent, String _currentFileName, int _offsetEnd, CustList<PartOffset> _parts, OperationNode _r) {
        boolean stopOp_ = false;
        right(_vars,_currentFileName, _offsetEnd, _parent,_parts);
        if (_parent == _r) {
            stopOp_ = true;
        }
        return stopOp_;
    }
    private static void getBeginOpReport(Block _block, CustList<PartOffset> _parts, String _fieldName, OperationNode _root, OperationNode _curOp, int _sum, boolean _addCover, AbstractCoverageResult _result, Coverage _cov, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        if (addTag(_fieldName, _root, _curOp, _addCover)) {
            String tag_ = getBeginReport(_block, _root, _curOp, _result, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            _parts.add(new PartOffset(tag_,_sum + _curOp.getIndexInEl()));
        }
    }

    private static void getEnd(CustList<PartOffset> _parts, String _fieldName, boolean _addCover, int _offset) {
        String tag_ = "</span>";
        if (_addCover && _fieldName.isEmpty()) {
            _parts.add(new PartOffset(tag_, _offset));
        }
    }

    private static String getEndTag(boolean _addCover, OperationNode _val, OperationNode _root, String _fieldName) {
        String tag_;
        if (addTag(_fieldName,_root,_val,_addCover)) {
            tag_ = "</span>";
        } else {
            tag_ = "";
        }
        return tag_;
    }

    private static boolean addTag(String _fieldName, OperationNode _root, OperationNode _curOp, boolean _addCover) {
        return (_curOp != _root || _fieldName.isEmpty())&&_addCover;
    }

    private static int getOffsetEnd(int _sum, OperationNode _val, MethodOperation _parent) {
        int indexChild_ = _val.getIndexChild();
        IntTreeMap<String> children_ = _parent.getChildren();
        return _sum + _val.getIndexInEl() + children_.getValue(indexChild_).length();
    }

    private static String getBeginReport(Block _block, OperationNode _root, OperationNode _val, AbstractCoverageResult _result, Coverage _cov, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        String tag_;
        String full_;
        String fullInit_;
        String partial_;
        String partialInit_;
        String none_;
        if (_annot || _block instanceof AnnotationMethodBlock) {
            full_ = "f2";
            fullInit_ = "g2";
            partial_ = "p2";
            partialInit_ = "q2";
            none_ = "n2";
        } else {
            full_ = "f";
            fullInit_ = "g";
            partial_ = "p";
            partialInit_ = "q";
            none_ = "n";
        }
        if (_val.getArgument() != null) {
            OperationNode par_ = _val;
            OperationNode before_ = _val;
            while (par_ != _root) {
                if (par_.getArgument() == null) {
                    break;
                }
                before_ = par_;
                par_ = par_.getParent();
            }
            MethodOperation parentBefore_ = before_.getParent();
            if (parentBefore_ == null){
                AbstractCoverageResult resultPar_ = getCovers(_block, par_, _cov, _annot, _indexAnnotGroup, _indexAnnot);
                if (resultPar_.isPartialCovered()) {
                    tag_ = getFullInitReport(resultPar_, fullInit_, full_);
                    return tag_;
                }
                tag_ = "<span class=\""+none_+"\">";
                return tag_;
            }
            if (before_.getIndexChild() > 0) {
                Argument firstArg_ = parentBefore_.getFirstChild().getArgument();
                if (firstArg_ == null) {
                    if (parentBefore_ instanceof QuickOperation) {
                        par_ = before_;
                    } else if (parentBefore_ instanceof AbstractTernaryOperation) {
                        par_ = before_;
                    } else if (parentBefore_ instanceof NullSafeOperation) {
                        par_ = before_;
                    } else if (parentBefore_ instanceof CompoundAffectationOperation) {
                        CompoundAffectationOperation p_ = (CompoundAffectationOperation) parentBefore_;
                        if (StringUtil.quickEq(p_.getOper(),"&&=")
                                || StringUtil.quickEq(p_.getOper(),"&&&=")
                                || StringUtil.quickEq(p_.getOper(),"||=")
                                || StringUtil.quickEq(p_.getOper(),"|||=")
                                || StringUtil.quickEq(p_.getOper(),"??=")
                                || StringUtil.quickEq(p_.getOper(),"???=")) {
                            par_ = before_;
                        }
                    }
                    AbstractCoverageResult resultPar_ = getCovers(_block, par_, _cov, _annot, _indexAnnotGroup, _indexAnnot);
                    if (resultPar_.isPartialCovered()) {
                        tag_ = getFullInitReport(resultPar_, fullInit_, full_);
                        return tag_;
                    }
                    tag_ = "<span class=\""+none_+"\">";
                    return tag_;
                }
                if (parentBefore_ instanceof OrOperation) {
                    if (BooleanStruct.isTrue(Argument.getNullableValue(firstArg_).getStruct())) {
                        tag_ = "<span class=\""+none_+"\">";
                        return tag_;
                    }
                }
                if (parentBefore_ instanceof AndOperation) {
                    if (BooleanStruct.isFalse(Argument.getNullableValue(firstArg_).getStruct())) {
                        tag_ = "<span class=\""+none_+"\">";
                        return tag_;
                    }
                }
                if (parentBefore_ instanceof NullSafeOperation) {
                    if (!Argument.getNullableValue(firstArg_).isNull()) {
                        tag_ = "<span class=\""+none_+"\">";
                        return tag_;
                    }
                }
                if (parentBefore_ instanceof AbstractTernaryOperation) {
                    if (BooleanStruct.isTrue(Argument.getNullableValue(firstArg_).getStruct())) {
                        if (before_.getIndexChild() == 2) {
                            tag_ = "<span class=\""+none_+"\">";
                            return tag_;
                        }
                    }
                    if (BooleanStruct.isFalse(Argument.getNullableValue(firstArg_).getStruct())) {
                        if (before_.getIndexChild() == 1) {
                            tag_ = "<span class=\""+none_+"\">";
                            return tag_;
                        }
                    }
                }
            }
            AbstractCoverageResult resultPar_ = getCovers(_block, par_, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            if (resultPar_.isPartialCovered()) {
                tag_ = getFullInitReport(resultPar_, fullInit_, full_);
                return tag_;
            }
            tag_ = "<span class=\""+none_+"\">";
            return tag_;
        }
        if (_result.isFullCovered()) {
            tag_ = getFullInitReport(_result, fullInit_, full_);
            return tag_;
        }
        if (_result.isPartialCovered()) {
            return getPartialInitReport(_val, _result, fullInit_, full_, partialInit_, partial_);
        }
        tag_ = "<span class=\""+none_+"\">";
        return tag_;
    }

    private static String getPartialInitReport(OperationNode _val, AbstractCoverageResult _result, String _fullInit, String _full, String _partialInit, String _partial) {
        String tag_;
        if (_val instanceof AffectationOperation && _val.getFirstChild().getNextSibling().getArgument() != null) {
            tag_ = getFullInitReport(_result, _fullInit, _full);
            return tag_;
        }
        if (_result.isInit()) {
            tag_ = "<span class=\""+ _partialInit +"\">";
            return tag_;
        }
        tag_ = "<span class=\""+ _partial +"\">";
        return tag_;
    }

    private static String getFullInitReport(AbstractCoverageResult _resultPar, String _fullInit, String _full) {
        String tag_;
        if (_resultPar.isInit()) {
            tag_ = "<span class=\""+ _fullInit +"\">";
        } else {
            tag_ = "<span class=\""+ _full +"\">";
        }
        return tag_;
    }

    private static void leftReport(VariablesOffsets _vars,
                                   Block _block,
                                   int _sum,
                                   OperationNode _val,
                                   AbstractCoverageResult _result,
                                   CustList<PartOffset> _parts, String _currentFileName) {
        MethodOperation par_ = _val.getParent();
        if (par_ == null&&_vars.isImplicit()) {
            int s_ = _sum + _val.getIndexInEl();
            _parts.add(mergeParts(_vars,_currentFileName,_val.getResultClass().getFunction(),s_,new StringList(),new StringList()));
        }
        if (!(par_ instanceof TernaryOperation)&&!(par_ instanceof RefTernaryOperation)) {
            if (leftOperNotUnary(par_) || par_ instanceof UnaryOperation || par_ instanceof UnaryBooleanOperation || par_ instanceof UnaryBinOperation||par_ instanceof SemiAffectationOperation&&!((SemiAffectationOperation)par_).isPost()) {
                if (_vars.isImplicit()) {
                    int off_ = _sum + _val.getIndexInEl();
                    _parts.add(mergeParts(_vars, _currentFileName, _val.getResultClass().getFunction(), off_, new StringList(), new StringList()));
                }
            }
        }
        if (!(par_ instanceof ShortTernaryOperation)&&!(par_ instanceof RefShortTernaryOperation)&&middleOper(par_)) {
            int indexChild_ = _val.getIndexChild();
            if (_vars.isImplicit() && indexChild_ > 0) {
                int off_ = _sum + _val.getIndexInEl();
                _parts.add(mergeParts(_vars,_currentFileName,_val.getResultClass().getFunction(),off_,new StringList(),new StringList()));
            }
        }
        processNamedFct(_vars, _currentFileName, _sum, _val, _parts);
        processVariables(_sum, _val, _parts);
        processConstants(_sum, _val, _parts);
        processAssociation(_vars, _sum, _val, _parts, _currentFileName);
        processFieldsReport(_block, _sum, _val, _parts, _currentFileName);
        processInstances(_vars, _currentFileName, _sum, _val, _parts);
        processAnonLambaReport(_sum, _val, _parts);
        processLamba(_vars, _sum, _val, _parts, _currentFileName);
        processLeafType(_vars, _sum,_val, _parts);
        processDynamicCall(_sum, _val, _parts);
        processRichHeader(_vars, _currentFileName, _sum, _val, _parts);
        processUnaryLeftOperationsCoversReport(_vars, _sum, _val, _result, _parts);
        processUnaryLeftOperationsLinks(_vars, _currentFileName, _sum, _val, _parts);
        processLeftIndexer(_vars, _currentFileName, _sum, _val, _parts);
        processArrLength(_sum, _val, _parts);
    }

    private static void leftError(VariablesOffsets _vars,
                                  Block _block,
                                  int _sum,
                                  OperationNode _val,
                                  CustList<PartOffset> _parts, String _currentFileName) {
        MethodOperation par_ = _val.getParent();
        int indexChild_ = _val.getIndexChild();
        if (par_ == null) {
            StringList errEmpt_ = new StringList();
            MethodOperation.processEmptyError(_val,errEmpt_);
            if (!errEmpt_.isEmpty()) {
                int off_ = _val.getOperations().getOffset();
                int begin_ = _sum + off_ + _val.getIndexInEl();
                processNullParent(_vars, _val, _parts, _currentFileName, begin_);
                _parts.add(new PartOffset("<a title=\""+transform(StringUtil.join(errEmpt_,"\n\n"))+"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+_val.getOperations().getDelimiter().getLength()));
            } else {
                int s_ = _sum + _val.getIndexInEl();
                processNullParent(_vars, _val, _parts, _currentFileName, s_);
            }
        } else {
            StringList l_ = new StringList();
            MethodOperation.processEmptyError(_val,l_);
            IntTreeMap<String> operators_ =  par_.getOperations().getOperators();
            if (leftOperNotUnary(par_)&& !(indexChild_ == 0 && par_ instanceof ArrOperation)) {
                if (!l_.isEmpty()) {
                    int s_ = _sum + par_.getIndexInEl() + operators_.getKey(indexChild_);
                    int len_ = operators_.getValue(indexChild_).length();
                    _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(l_,"\n\n")) +"\" class=\"e\">",s_));
                    _parts.add(new PartOffset("</a>",s_+Math.max(len_,1)));
                } else {
                    appendPossibleParts(_parts, par_, indexChild_);
                }
            } else if (par_ instanceof IdOperation||par_ instanceof ArrOperation) {
                appendPossibleParts(_parts, par_, indexChild_);
            }
        }
        if (!(par_ instanceof TernaryOperation)&&!(par_ instanceof RefTernaryOperation)) {
            if (leftOperNotUnary(par_) || par_ instanceof UnaryOperation || par_ instanceof UnaryBooleanOperation || par_ instanceof UnaryBinOperation||par_ instanceof SemiAffectationOperation&&!((SemiAffectationOperation)par_).isPost()) {
                IntTreeMap<String> operators_ = par_.getOperations().getOperators();
                if (_vars.isImplicit()) {
                    int s_ = _sum + par_.getIndexInEl() + operators_.getKey(indexChild_);
                    int len_ = operators_.getValue(indexChild_).length();
                    int off_ = s_ + Math.max(len_, 1);
                    _parts.add(mergeParts(_vars, _currentFileName, _val.getResultClass().getFunction(), off_, new StringList(), new StringList()));
                }
            }
        }
        if (!(par_ instanceof ShortTernaryOperation)&&!(par_ instanceof RefShortTernaryOperation)&&middleOper(par_)) {
            if (_vars.isImplicit() && indexChild_ > 0) {
                IntTreeMap<String> operators_ =  par_.getOperations().getOperators();
                int s_ = _sum + par_.getIndexInEl() + operators_.getKey(indexChild_-1);
                int len_ = operators_.getValue(indexChild_-1).length();
                int off_ = s_+Math.max(len_,1);
                _parts.add(mergeParts(_vars,_currentFileName,_val.getResultClass().getFunction(),off_,new StringList(),new StringList()));
            }
        }
        if (_val instanceof BadTernaryOperation) {
            BadTernaryOperation b_ = (BadTernaryOperation) _val;
            _parts.addAllElts(b_.getPartOffsetsEnd());
        }
        if (_val instanceof InferArrayInstancing) {
            InferArrayInstancing i_ = (InferArrayInstancing) _val;
            _parts.addAllElts(i_.getPartOffsetsErr());
        }
        processNamedFct(_vars, _currentFileName, _sum, _val, _parts);
        processVariables(_sum, _val, _parts);
        processConstants(_sum, _val, _parts);
        processAssociation(_vars, _sum, _val, _parts, _currentFileName);
        processFieldsError(_block, _sum, _val, _parts, _currentFileName);
        processInstances(_vars, _currentFileName, _sum, _val, _parts);
        processAnonLambaError(_sum, _val, _parts);
        processLamba(_vars, _sum, _val, _parts, _currentFileName);
        processLeafType(_vars, _sum,_val, _parts);
        processDynamicCall(_sum, _val, _parts);
        processRichHeader(_vars, _currentFileName, _sum, _val, _parts);
        processUnaryLeftOperationsLinks(_vars, _currentFileName, _sum, _val, _parts);
        processLeftIndexer(_vars, _currentFileName, _sum, _val, _parts);
        if (_val instanceof AnnotationInstanceOperation&&_val.getFirstChild() == null) {
            _parts.addAllElts(((AnnotationInstanceOperation)_val).getPartOffsetsEnd());
        }
        processArrLength(_sum, _val, _parts);
        if (_val.getParent() instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) _val.getParent();
            if (!c_.getSepErr().isEmpty()&&c_.getIndexCh() == indexChild_) {
                String tag_ = "<a title=\""+transform(c_.getSepErr())+"\" class=\"e\">";
                int rightPar_ = c_.getOperations().getOperators().getKey(c_.getIndexCh()+1);
                int beg_ = _sum + c_.getIndexInEl() + rightPar_;
                _parts.add(new PartOffset(tag_, beg_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,beg_+1));
            }
        }
    }

    private static void processNullParent(VariablesOffsets _vars, OperationNode _val, CustList<PartOffset> _parts, String _currentFileName, int _off) {
        if (_vars.isImplicit()) {
            _parts.add(mergeParts(_vars, _currentFileName, _val.getResultClass().getFunction(), _off, new StringList(), new StringList()));
        }
    }

    private static void appendPossibleParts(CustList<PartOffset> _parts, MethodOperation _par, int _indexChild) {
        CustList<CustList<PartOffset>> partOffsetsChildren_ = _par.getPartOffsetsChildren();
        if (partOffsetsChildren_.isValidIndex(_indexChild)) {
            _parts.addAllElts(partOffsetsChildren_.get(_indexChild));
        }
    }

    private static void processArrLength(int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof ArrayFieldOperation) {
            ArrayFieldOperation aField_ = (ArrayFieldOperation) _val;
            OperationsSequence op_ = aField_.getOperations();
            int relativeOff_ = op_.getOffset();
            String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
            String str_ = originalStr_.trim();
            int begin_ = _sum + relativeOff_ + _val.getIndexInEl();
            if (!aField_.getErrs().isEmpty()) {
                _parts.add(new PartOffset("<a title=\""+transform(StringUtil.join(aField_.getErrs(),"\n\n"))+"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+str_.length()));
            } else {
                _parts.add(new PartOffset("<b>",begin_));
                _parts.add(new PartOffset("</b>",begin_+str_.length()));
            }
        }
    }

    private static void processConstants(int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof ConstantOperation) {
            int off_ = _val.getOperations().getOffset();
            int begCst_ = _sum + off_ + _val.getIndexInEl();
            if (_val.getOperations().getConstType() == ConstType.STRING) {
                String tag_ = "<span class=\"s\">";
                _parts.add(new PartOffset(tag_, begCst_));
                if (!_val.getErrs().isEmpty()) {
                    tag_ = "<a title=\""+transform(StringUtil.join(_val.getErrs(),"\n\n"))+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_, begCst_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, begCst_ + ((ConstantOperation)_val).getLength()));
                }
                tag_ = "</span>";
                _parts.add(new PartOffset(tag_, begCst_ + ((ConstantOperation)_val).getLength()));
            }
            if (_val.getOperations().getConstType() == ConstType.CHARACTER) {
                String tag_ = "<span class=\"s\">";
                _parts.add(new PartOffset(tag_, begCst_));
                if (!_val.getErrs().isEmpty()) {
                    tag_ = "<a title=\""+transform(StringUtil.join(_val.getErrs(),"\n\n"))+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_, begCst_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, begCst_ + ((ConstantOperation)_val).getLength()));
                }
                tag_ = "</span>";
                _parts.add(new PartOffset(tag_, begCst_ + ((ConstantOperation)_val).getLength()));
            }
            if (_val.getOperations().getConstType() == ConstType.NUMBER) {
                if (!_val.getErrs().isEmpty()) {
                    String tag_ = "<a title=\""+transform(StringUtil.join(_val.getErrs(),"\n\n"))+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_, begCst_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, begCst_ +((ConstantOperation)_val).getNbLength()));
                }
            }
        }
    }

    private static void processDynamicCall(int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof CallDynMethodOperation) {
            int length_ = ((CallDynMethodOperation) _val).getFctName().length();
            String tag_;
            int begFct_ = _sum + _val.getIndexInEl();
            if (_val.getErrs().isEmpty()) {
                tag_ = "<b>";
                _parts.add(new PartOffset(tag_, begFct_));
                tag_ = "</b>";
            } else {
                tag_ = "<a title=\"" + transform(StringUtil.join(_val.getErrs(), "\n\n")) + "\" class=\"e\">";
                _parts.add(new PartOffset(tag_, begFct_));
                tag_ = "</a>";
            }
            _parts.add(new PartOffset(tag_,begFct_+ length_));
            if (((CallDynMethodOperation) _val).isNoNeed()) {
                tag_ = "<a title=\""+transform(((CallDynMethodOperation) _val).getSepErr())+"\" class=\"e\">";
                int leftPar_ = _val.getOperations().getOperators().firstKey();
                int beg_ = _sum + _val.getIndexInEl() + leftPar_;
                _parts.add(new PartOffset(tag_, beg_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,beg_+1));
            }
            if (_val.getFirstChild() == null&&!((CallDynMethodOperation) _val).getSepErr().isEmpty()) {
                tag_ = "<a title=\""+transform(((CallDynMethodOperation) _val).getSepErr())+"\" class=\"e\">";
                int rightPar_ = _val.getOperations().getOperators().lastKey();
                int beg_ = _sum + _val.getIndexInEl() + rightPar_;
                _parts.add(new PartOffset(tag_, beg_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,beg_+1));
            }
        }
    }

    private static void processLeftIndexer(VariablesOffsets _vars, String _currentFileName, int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof ArrOperation) {
            ArrOperation par_ = (ArrOperation) _val;
            AnaTypeFct function_;
            if (par_.getArrContent().isVariable()) {
                function_ = par_.getFunctionSet();
            } else {
                function_ = par_.getFunctionGet();
            }
            int beg_ = _sum + _val.getIndexInEl();
            if (function_ != null) {
                addParts(_vars, _currentFileName, function_, beg_,1,_val.getErrs(),_val.getErrs(),_parts);
            } else if (!_val.getErrs().isEmpty()) {
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">", beg_));
                _parts.add(new PartOffset("</a>", beg_ +1));
            }
        }
    }


    private static void processAssociation(VariablesOffsets _vars, int _sum, OperationNode _val, CustList<PartOffset> _parts, String _currentFileName) {
        if (_val instanceof AssocationOperation) {
            AssocationOperation a_ = (AssocationOperation) _val;
            String fieldName_ = a_.getFieldName();
            int delta_ = a_.getSum();
            addParts(_vars, _currentFileName,
                    a_.getFunction(),
                    _sum +delta_+ _val.getIndexInEl(),fieldName_.length(),_val.getErrs(),_val.getErrs(),_parts
            );
            if (!a_.getErrAff().isEmpty()) {
                int begin_ = _sum + a_.getOffEq() + _val.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+transform(a_.getErrAff())+"\" class=\"e\">", begin_));
                _parts.add(new PartOffset("</a>", begin_+1));
            }
        }
    }

    private static void processNamedFct(VariablesOffsets _vars, String _currentFileName, int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof FctOperation||_val instanceof ChoiceFctOperation ||_val instanceof SuperFctOperation) {
            if (_val instanceof FctOperation) {
                _parts.addAllElts(((FctOperation)_val).getPartOffsets());
                int delta_ = ((FctOperation) _val).getDelta();
                int l_ = ((FctOperation) _val).getLengthMethod();
                int begFct_ = _sum + delta_ + _val.getIndexInEl();
                if (((FctOperation)_val).isClonedMethod()&&_val.getErrs().isEmpty()) {
                    String tag_;
                    tag_ = "<b>";
                    _parts.add(new PartOffset(tag_, begFct_));
                    tag_ = "</b>";
                    _parts.add(new PartOffset(tag_, begFct_ +l_));
                } else {
                    AnaTypeFct function_ = ((FctOperation) _val).getFunction();
                    addParts(_vars, _currentFileName, function_,
                            begFct_, l_,
                            _val.getErrs(),_val.getErrs(), _parts);
                }
            }
            if (_val instanceof ChoiceFctOperation) {
                _parts.addAllElts(((ChoiceFctOperation)_val).getPartOffsets());
                int delta_ = ((ChoiceFctOperation) _val).getDelta();
                int l_ = ((ChoiceFctOperation) _val).getLengthMethod();
                AnaTypeFct function_ = ((ChoiceFctOperation) _val).getFunction();
                addParts(_vars, _currentFileName, function_,
                        _sum +delta_+ _val.getIndexInEl(),l_,
                        _val.getErrs(),_val.getErrs(),_parts);
            }
            if (_val instanceof SuperFctOperation) {
                _parts.addAllElts(((SuperFctOperation)_val).getPartOffsets());
                int delta_ = ((SuperFctOperation) _val).getDelta();
                int l_ = ((SuperFctOperation) _val).getLengthMethod();
                AnaTypeFct function_ = ((SuperFctOperation) _val).getFunction();
                addParts(_vars, _currentFileName, function_,
                        _sum +delta_+ _val.getIndexInEl(),l_,
                        _val.getErrs(),_val.getErrs(),_parts);
            }
        }
        if (_val instanceof AnnotationInstanceOperation) {
            _parts.addAllElts(((AnnotationInstanceOperation)_val).getPartOffsetsErr());
            _parts.addAllElts(((AnnotationInstanceOperation)_val).getPartOffsets());
            _parts.addAllElts(((AnnotationInstanceOperation)_val).getPartOffsetsErrPar());
        }
    }

    private static void processFieldsReport(Block _block, int _sum, OperationNode _val, CustList<PartOffset> _parts, String _currentFileName) {
        if (_val instanceof SettableAbstractFieldOperation) {
            int delta_ = ((SettableAbstractFieldOperation) _val).getOff();
            int begin_ = _sum + delta_ + _val.getIndexInEl() + ((SettableAbstractFieldOperation) _val).getDelta();
            RootBlock fieldType_ = ((SettableAbstractFieldOperation) _val).getFieldType();
            if (_block instanceof FieldBlock && ElUtil.isDeclaringField(_val)) {
                int idValueOffset_ = ((SettableAbstractFieldOperation)_val).getValueOffset();
                String tag_ = "<a name=\"m"+idValueOffset_+"\">";
                _parts.add(new PartOffset(tag_,begin_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,begin_+((SettableAbstractFieldOperation) _val).getFieldNameLength()));
            } else {
                int id_ = ((SettableAbstractFieldOperation)_val).getValueOffset();
                _parts.addAllElts(((SettableAbstractFieldOperation) _val).getPartOffsets());
                ClassField c_ = ((SettableAbstractFieldOperation)_val).getFieldIdReadOnly();
                updateFieldAnchor(fieldType_, _val.getErrs(),_parts,c_, begin_,((SettableAbstractFieldOperation) _val).getFieldNameLength(), _currentFileName,id_);
            }
        }
    }

    private static void processFieldsError(Block _block, int _sum, OperationNode _val, CustList<PartOffset> _parts, String _currentFileName) {
        if (_val instanceof SettableAbstractFieldOperation) {
            int indexBlock_ = ((SettableAbstractFieldOperation) _val).getIndexBlock();
            int delta_ = ((SettableAbstractFieldOperation) _val).getOff();
            RootBlock fieldType_ = ((SettableAbstractFieldOperation) _val).getFieldType();
            int begin_ = _sum + delta_ + _val.getIndexInEl() + ((SettableAbstractFieldOperation) _val).getDelta();
            if (_block instanceof FieldBlock && ElUtil.isDeclaringField(_val)) {
                StringList errs_ = ((FieldBlock) _block).getNameErrorsFields().get(indexBlock_);
                int idValueOffset_ = ((SettableAbstractFieldOperation)_val).getValueOffset();
                int id_ = ((FieldBlock) _block).getValuesOffset().indexOf(idValueOffset_);
                StringList errCst_ = new StringList();
                if (id_ > -1) {
                    errCst_.addAllElts(((FieldBlock) _block).getCstErrorsFields().get(id_));
                }
                if (errs_.isEmpty()) {
                    if (errCst_.isEmpty()) {
                        String tag_ = "<a name=\"m"+idValueOffset_+"\">";
                        _parts.add(new PartOffset(tag_,begin_));
                    } else {
                        String err_ = StringUtil.join(errCst_,"\n\n");
                        String tag_ = "<a name=\"m"+idValueOffset_+"\" title=\""+err_+"\" class=\"e\">";
                        _parts.add(new PartOffset(tag_,begin_));
                    }
                } else {
                    String err_ = StringUtil.join(errs_,"\n\n");
                    String tag_ = "<a title=\""+err_+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_,begin_));
                }
                String tag_ = "</a>";
                _parts.add(new PartOffset(tag_,begin_+((SettableAbstractFieldOperation) _val).getFieldNameLength()));
            } else {
                int id_ = ((SettableAbstractFieldOperation)_val).getValueOffset();
                _parts.addAllElts(((SettableAbstractFieldOperation) _val).getPartOffsets());
                ClassField c_ = ((SettableAbstractFieldOperation)_val).getFieldIdReadOnly();
                updateFieldAnchor(fieldType_, _val.getErrs(),_parts,c_, begin_,((SettableAbstractFieldOperation) _val).getFieldNameLength(), _currentFileName,id_);
            }
        }
    }
    private static void processVariables(int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof RefVariableOperation) {
            String varName_ = ((RefVariableOperation) _val).getRealVariableName();
            int delta_ = ((RefVariableOperation) _val).getOff();
            int id_ = ((RefVariableOperation) _val).getRef();
            int begVar_ = delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            if (((RefVariableOperation) _val).isDeclare()) {
                StringList errs_ = ((RefVariableOperation) _val).getNameErrors();
                if (!errs_.isEmpty()) {
                    String err_ = transform(StringUtil.join(errs_,"\n\n"));
                    String tag_ = "<a title=\""+err_+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_, begVar_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, endVar_));
                } else {
                    String tag_ = "<a name=\"m"+ id_ +"\">";
                    _parts.add(new PartOffset(tag_, begVar_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, endVar_));
                }

            } else {
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_, begVar_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, endVar_));
            }
        }
        if (_val instanceof VariableOperation) {
            String varName_ = ((VariableOperation) _val).getRealVariableName();
            int delta_ = ((VariableOperation) _val).getOff();
            int id_ = ((VariableOperation) _val).getRef();
            int begVar_ = delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            if (((VariableOperation) _val).isDeclare()) {
                StringList errs_ = ((VariableOperation) _val).getNameErrors();
                if (!errs_.isEmpty()) {
                    String err_ = transform(StringUtil.join(errs_,"\n\n"));
                    String tag_ = "<a title=\""+err_+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_, begVar_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, endVar_));
                } else {
                    String tag_ = "<a name=\"m"+ id_ +"\">";
                    _parts.add(new PartOffset(tag_, begVar_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, endVar_));
                }

            } else {
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_, begVar_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, endVar_));
            }
        }
        if (_val instanceof MutableLoopVariableOperation) {
            String varName_ = ((MutableLoopVariableOperation) _val).getRealVariableName();
            int delta_ = ((MutableLoopVariableOperation) _val).getOff();
            int id_ = ((MutableLoopVariableOperation) _val).getRef();
            int begVar_ = delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            if (((MutableLoopVariableOperation) _val).isDeclare()) {
                StringList errs_ = ((MutableLoopVariableOperation) _val).getNameErrors();
                if (!errs_.isEmpty()) {
                    String err_ = transform(StringUtil.join(errs_,"\n\n"));
                    String tag_ = "<a title=\""+err_+"\" class=\"e\">";
                    _parts.add(new PartOffset(tag_, begVar_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, endVar_));
                } else {
                    String tag_ = "<a name=\"m"+ id_ +"\">";
                    _parts.add(new PartOffset(tag_, begVar_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, endVar_));
                }

            } else {
                String tag_ = "<a href=\"#m"+id_+"\">";
                _parts.add(new PartOffset(tag_, begVar_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, endVar_));
            }
        }
        if (_val instanceof RefParamOperation) {
            String varName_ = ((RefParamOperation) _val).getRealVariableName();
            int delta_ = ((RefParamOperation) _val).getOff();
            int id_ = ((RefParamOperation) _val).getRef();
            String tag_ = "<a href=\"#m" + id_ + "\">";
            int begVar_ = delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            _parts.add(new PartOffset(tag_, begVar_));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_, endVar_));
        }
        if (_val instanceof FinalVariableOperation) {
            String varName_ = ((FinalVariableOperation) _val).getRealVariableName();
            int delta_ = ((FinalVariableOperation) _val).getOff();
            int deltaLoc_ = ((FinalVariableOperation)_val).getDelta();
            int begVar_ = deltaLoc_ + delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            if (!_val.getErrs().isEmpty()) {
                String err_ = transform(StringUtil.join(_val.getErrs(),"\n\n"));
                String tag_ = "<a title=\""+err_+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_, begVar_));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_, endVar_));
            } else {
                if (((FinalVariableOperation) _val).isKeyWord()) {
                    String tag_ = "<b>";
                    _parts.add(new PartOffset(tag_, begVar_));
                    tag_ = "</b>";
                    _parts.add(new PartOffset(tag_, endVar_));
                } else {
                    int id_ = ((FinalVariableOperation) _val).getRef();
                    String tag_ = "<a href=\"#m" + id_ + "\">";
                    _parts.add(new PartOffset(tag_, begVar_));
                    tag_ = "</a>";
                    _parts.add(new PartOffset(tag_, endVar_));
                }
            }
        }
    }

    private static void processInstances(VariablesOffsets _vars, String _currentFileName, int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof AbstractInstancingOperation) {
            AnaTypeFct constructor_ = ((AbstractInstancingOperation) _val).getConstructor();
            AbstractInstancingOperation inst_ = (AbstractInstancingOperation) _val;
            if (!(inst_ instanceof StandardInstancingOperation)||!((StandardInstancingOperation)inst_).isHasFieldName()) {
                int offsetNew_ = StringUtil.getFirstPrintableCharIndex(inst_.getMethodName());
                addParts(_vars, _currentFileName, constructor_,
                        offsetNew_+_sum + _val.getIndexInEl(),_vars.getKeyWords().getKeyWordNew().length(),
                        _val.getErrs(),_val.getErrs(),_parts);
                if (inst_ instanceof AnonymousInstancingOperation) {
                    _parts.addAllElts(((AnonymousInstancingOperation)inst_).getBlock().getPartsStaticInitInterfacesOffset());
                }
                _parts.addAllElts(inst_.getPartOffsets());
            }
        }
        if (_val instanceof DimensionArrayInstancing) {
            _parts.addAllElts(((DimensionArrayInstancing)_val).getPartOffsets());
        }
        if (_val instanceof ElementArrayInstancing) {
            _parts.addAllElts(((ElementArrayInstancing)_val).getPartOffsets());
            _parts.addAllElts(((ElementArrayInstancing)_val).getPartOffsetsErr());
        }
    }

    private static void processLeafType(VariablesOffsets _vars, int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof IdFctOperation) {
            _parts.addAllElts(((IdFctOperation)_val).getPartOffsets());
        }
        if (_val instanceof VarargOperation) {
            _parts.addAllElts(((VarargOperation)_val).getPartOffsets());
        }
        if (_val instanceof ForwardOperation) {
            ForwardOperation f_ = (ForwardOperation) _val;
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ f_.getLength()));
            }
            _parts.addAllElts(((ForwardOperation)_val).getPartOffsets());
        }
        if (_val instanceof DefaultValueOperation) {
            _parts.addAllElts(((DefaultValueOperation)_val).getPartOffsets());
        }
        if (_val instanceof StaticInfoOperation) {
            _parts.addAllElts(((StaticInfoOperation)_val).getPartOffsets());
        }
        if (_val instanceof StaticAccessOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _vars.getKeyWords().getKeyWordStatic().length()));
            }
            _parts.addAllElts(((StaticAccessOperation)_val).getPartOffsets());
        }
        if (_val instanceof StaticCallAccessOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _vars.getKeyWords().getKeyWordStaticCall().length()));
            }
            _parts.addAllElts(((StaticCallAccessOperation)_val).getPartOffsets());
        }
        if (_val instanceof ThisOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _vars.getKeyWords().getKeyWordThis().length()));
            }
        }
    }

    private static void processAnonLambaReport(int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (!(_val instanceof AnonymousLambdaOperation)) {
            return;
        }
        AnonymousLambdaOperation v_ = (AnonymousLambdaOperation)_val;
        int begin_ = _sum + _val.getIndexInEl();
        processAnonymousFctReport(begin_,v_.getBlock(), _parts);
    }

    private static void processAnonLambaError(int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (!(_val instanceof AnonymousLambdaOperation)) {
            return;
        }
        AnonymousLambdaOperation v_ = (AnonymousLambdaOperation)_val;
        int begin_ = _sum + _val.getIndexInEl();
        processAnonymousFctBlockError(begin_,v_.getBlock(), _parts);
    }
    private static void processLamba(VariablesOffsets _vars, int _sum, OperationNode _val, CustList<PartOffset> _parts, String _currentFileName) {
        if (!(_val instanceof LambdaOperation)) {
            return;
        }
        ClassField fieldId_ = ((LambdaOperation) _val).getFieldId();
        AnaTypeFct function_ = ((LambdaOperation) _val).getFunction();
        RootBlock fieldType_ = ((LambdaOperation) _val).getFieldType();
        int off_ = ((LambdaOperation)_val).getClassNameOffset();
        int beginLambda_ = off_ + _sum + _val.getIndexInEl();
        int lambdaLen_ = _vars.getKeyWords().getKeyWordLambda().length();
        if (function_ != null) {
            addParts(_vars, _currentFileName, function_,
                    beginLambda_, lambdaLen_,
                    _val.getErrs(),_val.getErrs(),_parts);
        } else {
            if (fieldId_ != null) {
                updateFieldAnchor(fieldType_, _val.getErrs(),_parts,fieldId_, beginLambda_, lambdaLen_, _currentFileName,((LambdaOperation) _val).getValueOffset());
            } else if (!_val.getErrs().isEmpty()) {
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">", beginLambda_));
                _parts.add(new PartOffset("</a>", beginLambda_ + lambdaLen_));
            }
        }
        _parts.addAllElts(((LambdaOperation)_val).getPartOffsets());
        _parts.addAllElts(((LambdaOperation)_val).getPartOffsetsEnd());
        if (((LambdaOperation)_val).isRecordType()) {
            int len_ = ((LambdaOperation)_val).getNamed().size();
            for (int i = 0; i < len_; i++) {
                int ref_ = ((LambdaOperation) _val).getRefs().get(i);
                if (ref_ < 0) {
                    continue;
                }
                String name_ = ((LambdaOperation) _val).getNamed().get(i);
                updateFieldAnchor(fieldType_,new StringList(),_parts,
                        new ClassField(fieldType_.getFullName(),name_),
                        beginLambda_+((LambdaOperation) _val).getOffsets().get(i),
                        name_.length(),_currentFileName,ref_
                );
            }
        }
    }

    private static void processRichHeader(VariablesOffsets _vars, String _currentFileName, int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof EnumValueOfOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _vars.getKeyWords().getKeyWordValueOf().length()));
            }
            _parts.addAllElts(((EnumValueOfOperation)_val).getPartOffsets());
        }
        if (_val instanceof ValuesOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _vars.getKeyWords().getKeyWordValues().length()));
            }
            _parts.addAllElts(((ValuesOperation)_val).getPartOffsets());
        }
        if (_val instanceof AbstractInvokingConstructor) {
            if (_val instanceof InterfaceInvokingConstructor) {
                addParts(_vars, _currentFileName, ((AbstractInvokingConstructor)_val).getConstructor(),
                        _sum + _val.getIndexInEl(),_vars.getKeyWords().getKeyWordInterfaces().length(),
                        _val.getErrs(),_val.getErrs(),_parts);
                _parts.addAllElts(((InterfaceInvokingConstructor)_val).getPartOffsets());
            } else if (_val instanceof InterfaceFctConstructor) {
                addParts(_vars, _currentFileName, ((AbstractInvokingConstructor)_val).getConstructor(),
                        _sum + _val.getIndexInEl(),_vars.getKeyWords().getKeyWordInterfaces().length(),
                        _val.getErrs(),_val.getErrs(),_parts);
                _parts.addAllElts(((InterfaceFctConstructor)_val).getPartOffsets());
            } else{
                addParts(_vars, _currentFileName, ((AbstractInvokingConstructor)_val).getConstructor(),
                        _sum + _val.getIndexInEl(), ((AbstractInvokingConstructor) _val).getOffsetOper(),
                        _val.getErrs(),_val.getErrs(),_parts);
            }
        }
        if (_val instanceof ExplicitOperatorOperation) {
            ExplicitOperatorOperation par_ = (ExplicitOperatorOperation) _val;
            addParts(_vars, _currentFileName, par_.getFunction(),
                    _sum + _val.getIndexInEl(),_vars.getKeyWords().getKeyWordOperator().length(),
                    _val.getErrs(),_val.getErrs(),_parts);
            _parts.addAllElts(par_.getPartOffsets());
        }
    }

    private static void processUnaryLeftOperationsCoversReport(VariablesOffsets _vars, int _sum, OperationNode _val, AbstractCoverageResult _result, CustList<PartOffset> _parts) {
        if (_val instanceof UnaryBooleanOperation && ((UnaryBooleanOperation)_val).getFunction() == null && _result.isStrictPartialCovered()) {
            int offsetOp_ = _val.getOperations().getOperators().firstKey();
            safeReport(_vars,_result, _sum + _val.getIndexInEl() + offsetOp_,_parts,1);
        }
    }

    private static void processUnaryLeftOperationsLinks(VariablesOffsets _vars, String _currentFileName, int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof SymbolOperation && _val.getFirstChild().getNextSibling() == null) {
            SymbolOperation par_ = (SymbolOperation) _val;
            AnaTypeFct function_ = par_.getFunction();
            if (function_ != null) {
                addParts(_vars, _currentFileName, function_,
                        _sum + _val.getIndexInEl() + par_.getOpOffset(), function_.getFunction().getName().length(),
                        _val.getErrs(),_val.getErrs(),_parts);
            } else if (!_val.getErrs().isEmpty()){
                int i_ = _sum + _val.getIndexInEl() + par_.getOpOffset();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",i_));
                _parts.add(new PartOffset("</a>",i_+1));
            }
        }
        if (_val instanceof CastOperation) {
            int l_;
            int i_ = _sum + _val.getIndexInEl();
            if (((CastOperation)_val).isFound()) {
                i_ += ((CastOperation)_val).getOffset();
                l_ = 1;
            } else {
                l_ = _vars.getKeyWords().getKeyWordCast().length();
            }
            if (!_val.getErrs().isEmpty()) {
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",i_));
                _parts.add(new PartOffset("</a>",i_+l_));
            }
            _parts.addAllElts(((CastOperation)_val).getPartOffsets());
        }
        if (_val instanceof ExplicitOperation) {
            int offsetOp_ = _val.getOperations().getOperators().firstKey();
            AnaTypeFct function_ = ((ExplicitOperation) _val).getFunction();
            addParts(_vars, _currentFileName, function_,
                    offsetOp_+_sum + _val.getIndexInEl(),_vars.getKeyWords().getKeyWordExplicit().length(),
                    _val.getErrs(),_val.getErrs(),
                    _parts);
            _parts.addAllElts(((ExplicitOperation)_val).getPartOffsets());
        }
        if (_val instanceof ImplicitOperation) {
            int offsetOp_ = _val.getOperations().getOperators().firstKey();
            AnaTypeFct function_ = ((ImplicitOperation) _val).getFunction();
            addParts(_vars, _currentFileName, function_,
                    offsetOp_+_sum + _val.getIndexInEl(),_vars.getKeyWords().getKeyWordCast().length(),
                    _val.getErrs(),_val.getErrs(),
                    _parts);
            _parts.addAllElts(((ImplicitOperation)_val).getPartOffsets());
        }
        if (_val instanceof SemiAffectationOperation) {
            SemiAffectationOperation par_ = (SemiAffectationOperation) _val;
            int offsetOp_ = par_.getOpOffset();
            if(!par_.isPost()) {
                boolean err_ = true;
                AnaTypeFct function_ = par_.getFunction();
                if (function_ != null) {
                    addParts(_vars, _currentFileName, function_,
                            _sum + _val.getIndexInEl()+offsetOp_,1,
                            _val.getErrs(),_val.getErrs(),_parts);
                    err_ = false;
                }
                SettableElResult settable_ = par_.getSettable();
                if (settable_ instanceof ArrOperation && ((ArrOperation) settable_).getFunctionSet() != null) {
                    ArrOperation parArr_ = (ArrOperation) settable_;
                    addParts(_vars, _currentFileName, parArr_.getFunctionSet(),
                            _sum + _val.getIndexInEl()+offsetOp_+1,1,
                            _val.getErrs(),_val.getErrs(),_parts);
                    err_ = false;
                }
                if (err_) {
                    if (!par_.getErrs().isEmpty()) {
                        int begin_ = par_.getOpOffset()+_sum + par_.getIndexInEl();
                        _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(par_.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                        _parts.add(new PartOffset("</a>",begin_+ 2));
                    }
                }
                OperationNode ch_ = null;
                if (settable_ instanceof OperationNode) {
                    ch_ = par_.getFirstChild();
                }
                if (ch_ != null) {
                    if (_vars.isImplicit()) {
                        int begin_ = par_.getOpOffset() + _sum + par_.getIndexInEl()+2;
                        _parts.add(mergeParts(_vars, _currentFileName, par_.getFunctionFrom(), begin_, new StringList(), new StringList()));
                        _parts.add(mergeParts(_vars, _currentFileName, par_.getFunctionTo(), begin_, new StringList(), new StringList()));
                    }
                }
            }
        }
        if (_val instanceof IdOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((IdOperation)_val).getDelta();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ 1));
            }
        }
        if (_val instanceof FirstOptOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((FirstOptOperation)_val).getDelta();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _vars.getKeyWords().getKeyWordFirstopt().length()));
            }
        }
        if (_val instanceof WrappOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((WrappOperation)_val).getDelta();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _vars.getKeyWords().getKeyWordThat().length()));
            }
        }
        if (_val instanceof NamedArgumentOperation) {
            NamedArgumentOperation n_ = (NamedArgumentOperation) _val;
            int firstOff_ = n_.getOffsetTr();
            if (n_.getField() != null) {
                int begin_ = _sum + _val.getIndexInEl()+firstOff_;
                updateFieldAnchor(n_.getField(),n_.getErrs(),_parts,
                        new ClassField(n_.getField().getFullName(),n_.getName()),
                        begin_,n_.getName().length(),_currentFileName,n_.getRef());
                return;
            }
            CustList<NamedFunctionBlock> customMethods_ = n_.getCustomMethod();
            int refOne_ = -1;
            int refTwo_ = -1;
            String relFileOne_ = "";
            String relFileTwo_ = "";
            int i_ = 0;
            CustList<NamedFunctionBlock> cust_ = new CustList<NamedFunctionBlock>();
            CustList<NamedFunctionBlock> filterSet_ = new CustList<NamedFunctionBlock>();
            CustList<NamedFunctionBlock> filterGet_ = new CustList<NamedFunctionBlock>();
            for (NamedFunctionBlock n:customMethods_) {
                FileBlock file_ = n.getFile();
                Ints offs_ = new Ints();
                if (!file_.isPredefined()) {
                    offs_ = n.getParametersNamesOffset();
                }
                if (offs_.isValidIndex(n_.getIndex())) {
                    if (n instanceof OverridableBlock) {
                        OverridableBlock ov_ = (OverridableBlock) n;
                        if (ov_.getKind() == MethodKind.GET_INDEX) {
                            filterGet_.add(n);
                        }
                        if (ov_.getKind() == MethodKind.SET_INDEX) {
                            filterSet_.add(n);
                        }
                    }
                    cust_.add(n);
                }
            }
            if (_val.getParent() instanceof ArrOperation) {
                ArrOperation arr_ = (ArrOperation) _val.getParent();
                if (arr_.getArrContent().isVariable()) {
                    cust_ = filterSet_;
                } else if (!arr_.isGetAndSet()) {
                    cust_ = filterGet_;
                }
            }
            for (NamedFunctionBlock n:cust_) {
                FileBlock file_ = n.getFile();
                Ints offs_ = n.getParametersNamesOffset();
                if (i_ == 0) {
                    relFileOne_ = file_.getRenderFileName();
                    refOne_ = offs_.get(n_.getIndex());
                } else {
                    relFileTwo_ = file_.getRenderFileName();
                    refTwo_ = offs_.get(n_.getIndex());
                }
                i_++;
            }
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl()+firstOff_;
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ n_.getName().length()));
            } else {
                if (refOne_ != -1){
                    int begin_ = _sum + _val.getIndexInEl()+firstOff_;
                    String rel_ = relativize(_currentFileName, relFileOne_ + "#m" + refOne_);
                    _parts.add(new PartOffset("<a href=\""+rel_ +"\">",begin_));
                    _parts.add(new PartOffset("</a>",begin_+ n_.getName().length()));
                }
                if (refTwo_ != -1){
                    IntTreeMap<String> vs_ = _val.getOperations().getOperators();
                    int begin_ = _sum + _val.getIndexInEl()+vs_.firstKey();
                    String rel_ = relativize(_currentFileName, relFileTwo_ + "#m" + refTwo_);
                    _parts.add(new PartOffset("<a href=\""+rel_ +"\">",begin_));
                    _parts.add(new PartOffset("</a>",begin_+ vs_.firstValue().length()));
                }
            }
        }
        if (_val instanceof DefaultOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((DefaultOperation)_val).getDelta();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_val.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+ _vars.getKeyWords().getKeyWordDefault().length()));
            }
        }
        if (_val instanceof TernaryOperation) {
            TernaryOperation t_ = (TernaryOperation) _val;
            AnaTypeFct testFct_ = t_.getTestFct();
            if (testFct_ != null) {
                StringList l_ = new StringList();
                int begin_ = _sum + _val.getIndexInEl();
                addParts(_vars, _currentFileName, testFct_,begin_,_vars.getKeyWords().getKeyWordBool().length(),l_,l_,_parts);
            }
            testFct_ = t_.getImplFct();
            if (_vars.isImplicit()&&testFct_ != null) {
                StringList l_ = new StringList();
                int begin_ = _sum + _val.getIndexInEl();
                addParts(_vars, _currentFileName, testFct_,begin_,_vars.getKeyWords().getKeyWordBool().length(),l_,l_,_parts);
            }
        }
        if (_val instanceof RefTernaryOperation) {
            RefTernaryOperation t_ = (RefTernaryOperation) _val;
            AnaTypeFct testFct_ = t_.getTestFct();
            boolean addedErr_ = false;
            if (testFct_ != null) {
                StringList l_ = t_.getChildrenErrors();
                int begin_ = _sum + _val.getIndexInEl();
                addParts(_vars, _currentFileName, testFct_,begin_,_vars.getKeyWords().getKeyWordBool().length(),l_,l_,_parts);
                addedErr_ = true;
            }
            testFct_ = t_.getImplFct();
            if (_vars.isImplicit()&&testFct_ != null) {
                StringList l_ = t_.getChildrenErrors();
                int begin_ = _sum + _val.getIndexInEl();
                addParts(_vars, _currentFileName, testFct_,begin_,_vars.getKeyWords().getKeyWordBool().length(),l_,l_,_parts);
                addedErr_ = true;
            }
            if (!addedErr_&&!t_.getChildrenErrors().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(t_.getChildrenErrors(),"\n\n")) +"\" class=\"e\">", begin_));
                _parts.add(new PartOffset("</a>", begin_ +_vars.getKeyWords().getKeyWordBool().length()));
            }
        }
    }

    private static void middleReport(VariablesOffsets _vars,
                                     String _currentFileName,
                                     Block _block,
                                     int _offsetEnd,
                                     OperationNode _curOp,
                                     OperationNode _nextSiblingOp,
                                     MethodOperation _parent,
                                     CustList<PartOffset> _parts, Coverage _cov, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        if (_parent instanceof ShortTernaryOperation) {
            ShortTernaryOperation sh_ = (ShortTernaryOperation) _parent;
            int index_ = _curOp.getIndexChild();
            AnaTypeFct testFct_ = null;
            if (_vars.isImplicit() && index_ == 0) {
                testFct_ = sh_.getImplFct();
            }
            if (index_ == 1) {
                testFct_ = sh_.getTestFct();
            }
            if (testFct_ != null) {
                StringList l_ = new StringList();
                addParts(_vars, _currentFileName, testFct_,_offsetEnd,1,l_,l_,_parts);
            }
        }
        if (_parent instanceof RefShortTernaryOperation) {
            RefShortTernaryOperation sh_ = (RefShortTernaryOperation) _parent;
            int index_ = _curOp.getIndexChild();
            AnaTypeFct testFct_ = null;
            if (_vars.isImplicit() && index_ == 0) {
                testFct_ = sh_.getImplFct();
            }
            if (index_ == 1) {
                testFct_ = sh_.getTestFct();
            }
            if (testFct_ != null) {
                StringList l_ = new StringList();
                addParts(_vars, _currentFileName, testFct_,_offsetEnd,1,l_,l_,_parts);
            }
        }
        processCustomOperator(_vars, _currentFileName, _offsetEnd,_curOp,_nextSiblingOp, _parent, _parts);
        processCompoundAffLeftOpReport(_vars, _block, _currentFileName, _offsetEnd, _curOp,_nextSiblingOp, _parent, _parts, _cov, _annot, _indexAnnotGroup, _indexAnnot);
        processCompoundAffRightOp(_vars, _currentFileName, _offsetEnd, _parent, _parts);

        processCompareReport(_vars, _block, _offsetEnd, _parent, _parts, _cov, _annot, _indexAnnotGroup, _indexAnnot);
        processDotSafeReport(_vars, _block, _offsetEnd, _curOp, _parent, _parts, _cov, _annot, _indexAnnotGroup, _indexAnnot);
        processNullSafeReport(_vars, _block, _offsetEnd, _curOp, _nextSiblingOp, _parent, _parts, _cov, _annot, _indexAnnotGroup, _indexAnnot);
        processLogicAndOrOperationReport(_vars, _currentFileName,_block, _offsetEnd, _curOp, _nextSiblingOp, _parent, _parts, _cov, _annot, _indexAnnotGroup, _indexAnnot);
    }

    private static void middleError(VariablesOffsets _vars,
                                    String _currentFileName,
                                    int _offsetEnd,
                                    OperationNode _curOp,
                                    OperationNode _nextSiblingOp,
                                    MethodOperation _parent,
                                    CustList<PartOffset> _parts) {
        StringList l_ = new StringList();
        MethodOperation.processEmptyError(_curOp,l_);
        MethodOperation.processEmptyError(_nextSiblingOp,l_);
        if (middleOper(_parent)) {
            int index_ = _curOp.getIndexChild();
            IntTreeMap<String> operators_ =  _parent.getOperations().getOperators();
            AnaTypeFct testFct_ = null;
            if (_vars.isImplicit()&&_parent instanceof ShortTernaryOperation && index_ == 0) {
                ShortTernaryOperation sh_ = (ShortTernaryOperation) _parent;
                testFct_ = sh_.getImplFct();
            }
            if (_parent instanceof ShortTernaryOperation && index_ == 1) {
                ShortTernaryOperation sh_ = (ShortTernaryOperation) _parent;
                testFct_ = sh_.getTestFct();
            }
            if (_vars.isImplicit()&&_parent instanceof RefShortTernaryOperation && index_ == 0) {
                RefShortTernaryOperation sh_ = (RefShortTernaryOperation) _parent;
                testFct_ = sh_.getImplFct();
            }
            if (_parent instanceof RefShortTernaryOperation && index_ == 1) {
                RefShortTernaryOperation sh_ = (RefShortTernaryOperation) _parent;
                testFct_ = sh_.getTestFct();
                l_.addAllElts(sh_.getChildrenErrors());
            }
            if (testFct_ != null) {
                addParts(_vars, _currentFileName, testFct_,_offsetEnd,1,l_,l_,_parts);
            } else if (!l_.isEmpty()) {
                int len_ = operators_.getValue(index_).length();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(l_,"\n\n")) +"\" class=\"e\">", _offsetEnd));
                _parts.add(new PartOffset("</a>", _offsetEnd +Math.max(len_,1)));
            } else {
                appendPossibleParts(_parts, _parent, index_);
            }
        }
        if (l_.isEmpty()&&_curOp.getIndexChild() == 0 &&_parent instanceof QuickOperation) {
            QuickOperation q_ = (QuickOperation) _parent;
            StringList errs_ = q_.getErrFirst();
            AnaTypeFct functionTest_ = q_.getFunctionTest();
            StringList title_ = new StringList();
            addParts(_vars, _currentFileName, functionTest_,_offsetEnd,1, errs_,title_,_parts);
            errs_ = q_.getErrSecond();
            AnaTypeFct function_ = q_.getFunction();
            addParts(_vars, _currentFileName, function_,_offsetEnd+1,1, errs_,title_,_parts);
            if (_vars.isImplicit()) {
                _parts.add(mergeParts(_vars,_currentFileName,q_.getConvert(),_offsetEnd+2,new StringList(),new StringList()));
            }
        }
        processCustomOperator(_vars, _currentFileName, _offsetEnd,_curOp,_nextSiblingOp, _parent, _parts);
        processCompoundAffLeftOpError(_vars, _currentFileName, _offsetEnd, _nextSiblingOp, _parent, _parts);
        processCompoundAffRightOp(_vars, _currentFileName, _offsetEnd, _parent, _parts);
    }
    private static void processImplicit(VariablesOffsets _vars,
                                        String _currentFileName,
                                        int _off,
                                        OperationNode _curOp,
                                        MethodOperation _parent,
                                        CustList<PartOffset> _parts) {
        if (!(_parent instanceof ShortTernaryOperation)&&!(_parent instanceof RefShortTernaryOperation)&&middleOper(_parent)||_parent instanceof SemiAffectationOperation&&((SemiAffectationOperation)_parent).isPost()) {
            if (_vars.isImplicit()&&_curOp.getIndexChild() == 0) {
                _parts.add(mergeParts(_vars,_currentFileName,_curOp.getResultClass().getFunction(),_off,new StringList(),new StringList()));
            }
        }
    }
    private static void processCustomOperator(VariablesOffsets _vars, String _currentFileName, int _offsetEnd, OperationNode _curOp,
                                              OperationNode _nextSiblingOp,MethodOperation _parentOp, CustList<PartOffset> _parts) {
        if (_parentOp instanceof MiddleSymbolOperation) {
            MiddleSymbolOperation par_ = (MiddleSymbolOperation) _parentOp;
            AnaTypeFct function_ = par_.getFunction();
            if (function_ != null) {
                addParts(_vars, _currentFileName, function_,_offsetEnd,par_.getOp().length(),_parentOp.getErrs(),_parentOp.getErrs(),_parts);
            } else if (_parentOp instanceof AddOperation && ((AddOperation)_parentOp).isCatString() && canCallToString(_vars, _curOp, _nextSiblingOp)) {
                String tag_ = "<i>";
                _parts.add(new PartOffset(tag_, _offsetEnd));
                tag_ = "</i>";
                _parts.add(new PartOffset(tag_, _offsetEnd + 1));
            }

        }
    }

    private static boolean canCallToString(VariablesOffsets _vars, OperationNode _curOp, OperationNode _nextSiblingOp) {
        return canCallToString(_vars,_curOp.getResultClass()) || canCallToString(_vars,_nextSiblingOp.getResultClass());
    }
    private static void processDotSafeReport(VariablesOffsets _vars, Block _block, int _offsetEnd, OperationNode _curOp, MethodOperation _parentOp, CustList<PartOffset> _parts, Coverage _cov, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        if (_parentOp instanceof SafeDotOperation) {
            AbstractCoverageResult resultFirst_ = getCovers(_block, _curOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            safeReport(_vars, resultFirst_, _offsetEnd,_parts, 1);
        }
    }
    private static void processNullSafeReport(VariablesOffsets _vars, Block _block, int _offsetEnd, OperationNode _curOp, OperationNode _nextSiblingOp, MethodOperation _parentOp, CustList<PartOffset> _parts, Coverage _cov, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        if (_parentOp instanceof NullSafeOperation) {
            AbstractCoverageResult resultFirst_ = getCovers(_block, _curOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            AbstractCoverageResult resultLast_ = getCovers(_block, _nextSiblingOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            safeReport(_vars, resultFirst_, _offsetEnd,_parts, 1);
            safeReport(_vars, resultLast_, _offsetEnd+1,_parts, 1);
        }
    }

    private static void processCompareReport(VariablesOffsets _vars, Block _block, int _offsetEnd,
                                             MethodOperation _parent, CustList<PartOffset> _parts, Coverage _cov, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        if (isWideCmp(_parent)) {

            int length_ = ((MiddleSymbolOperation)_parent) .getOp().length();
            if (((SymbolOperation)_parent).getFunction() == null) {
                AbstractCoverageResult resultLoc_ = getCovers(_block, _parent, _cov, _annot, _indexAnnotGroup, _indexAnnot);
                safeReport(_vars, resultLoc_, _offsetEnd,_parts,length_);
            }
        }
    }

    private static boolean isWideCmp(MethodOperation _parentOp) {
        return _parentOp instanceof EqOperation || _parentOp instanceof CmpOperation;
    }

    private static void processCompoundAffLeftOpError(VariablesOffsets _vars, String _currentFileName, int _offsetEnd, OperationNode _nextSiblingOp, MethodOperation _parentOp, CustList<PartOffset> _parts) {
        if (!(_parentOp instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) _parentOp;
        AnaTypeFct function_ = par_.getFunction();
        int opDelta_ = par_.getOper().length() - 1;
        AnaTypeFct functionTest_ = par_.getFunctionTest();
        int begin_ = _offsetEnd;
        int len_ = opDelta_;
        if (functionTest_ != null) {
            addParts(_vars, _currentFileName, functionTest_,begin_,1, _parentOp.getErrs(),_parentOp.getErrs(),_parts);
            begin_++;
            len_--;
        }
        if (function_ != null) {
            addParts(_vars, _currentFileName, function_,begin_,len_,_parentOp.getErrs(),_parentOp.getErrs(),_parts);
        } else if (hasToCallStringConver(_vars, _nextSiblingOp)){
            String tag_ = "<i>";
            _parts.add(new PartOffset(tag_, begin_));
            tag_ = "</i>";
            _parts.add(new PartOffset(tag_,begin_+len_));
        }
    }

    private static boolean hasToCallStringConver(VariablesOffsets _vars, OperationNode _nextSiblingOp) {
        return _nextSiblingOp.getResultClass().isConvertToString() && canCallToString(_vars,_nextSiblingOp.getResultClass());
    }

    private static void processCompoundAffLeftOpReport(VariablesOffsets _vars, Block _block, String _currentFileName, int _offsetEnd, OperationNode _curOp, OperationNode _nextSiblingOp, MethodOperation _parentOp, CustList<PartOffset> _parts, Coverage _cov, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        if (!(_parentOp instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) _parentOp;
        AnaTypeFct function_ = par_.getFunction();
        int opDelta_ = par_.getOper().length() - 1;
        AnaTypeFct functionTest_ = par_.getFunctionTest();
        int begin_ = _offsetEnd;
        int len_ = opDelta_;
        if (functionTest_ != null) {
            StringList title_ = new StringList();
            AbstractCoverageResult resultFirst_ = getCovers(_block, _curOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            title_.addAllElts(getCoversFoundReport(_vars, resultFirst_));
            addParts(_vars, _currentFileName, functionTest_,begin_,1, _parentOp.getErrs(),title_,_parts);
            begin_++;
            len_--;
            if (StringUtil.quickEq(par_.getOper(),"&&&=")
                    || StringUtil.quickEq(par_.getOper(),"|||=")){
                begin_++;
                len_--;
            }
        } else if (StringUtil.quickEq(par_.getOper(),"&&=")
                || StringUtil.quickEq(par_.getOper(),"||=")){
            AbstractCoverageResult resultFirst_ = getCovers(_block, _curOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            safeReport(_vars, resultFirst_, begin_,_parts,1);
            begin_++;
            len_--;
        } else if (StringUtil.quickEq(par_.getOper(),"&&&=")
                || StringUtil.quickEq(par_.getOper(),"|||=")){
            AbstractCoverageResult resultFirst_ = getCovers(_block, _curOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            safeReport(_vars, resultFirst_, begin_,_parts,1);
            begin_+=2;
            len_-=2;
        } else if (StringUtil.quickEq(par_.getOper(),"??=")){
            AbstractCoverageResult resultFirst_ = getCovers(_block, _curOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            safeReport(_vars, resultFirst_, begin_,_parts,1);
            begin_++;
            len_--;
        } else if (StringUtil.quickEq(par_.getOper(),"???=")){
            AbstractCoverageResult resultFirst_ = getCovers(_block, _curOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            safeReport(_vars, resultFirst_, begin_,_parts,1);
            begin_+=2;
            len_-=2;
        }
        if (function_ != null) {
            addParts(_vars, _currentFileName, function_,begin_,len_,_parentOp.getErrs(),_parentOp.getErrs(),_parts);
        } else if (hasToCallStringConver(_vars, _nextSiblingOp)){
            String tag_ = "<i>";
            _parts.add(new PartOffset(tag_, begin_));
            tag_ = "</i>";
            _parts.add(new PartOffset(tag_,begin_+len_));
        } else if (StringUtil.quickEq(par_.getOper(),"??=") || StringUtil.quickEq(par_.getOper(),"???=")){
            AbstractCoverageResult resultLast_ = getCovers(_block, _nextSiblingOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
            safeReport(_vars, resultLast_, begin_,_parts, 1);
        } else {
            if (par_.isRightBool()) {
                AbstractCoverageResult resultLast_ = getCovers(_block, _nextSiblingOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
                safeReport(_vars, resultLast_, begin_,_parts,1);
            }
        }
    }
    private static void processCompoundAffRightOp(VariablesOffsets _vars, String _currentFileName, int _offsetEnd, MethodOperation _parentOp, CustList<PartOffset> _parts) {
        if (!(_parentOp instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) _parentOp;
        int opDelta_ = par_.getOper().length() - 1;
        SettableElResult settable_ = par_.getSettable();
        if (settable_ instanceof ArrOperation) {
            addParts(_vars, _currentFileName, ((ArrOperation) settable_).getFunctionSet(),opDelta_+_offsetEnd,1,_parentOp.getErrs(),_parentOp.getErrs(),_parts);
        } else {
            addParts(_vars, _currentFileName, null,opDelta_+_offsetEnd,1,_parentOp.getErrs(),_parentOp.getErrs(),_parts);
        }
        if (_vars.isImplicit()) {
            _parts.add(mergeParts(_vars,_currentFileName,par_.getFunctionImpl(),opDelta_+_offsetEnd+1,new StringList(),new StringList()));
        }
    }
    private static void processLogicAndOrOperationReport(VariablesOffsets _vars, String _currentFileName, Block _block, int _offsetEnd, OperationNode _curOp, OperationNode _nextSiblingOp, MethodOperation _parentOp, CustList<PartOffset> _parts, Coverage _cov, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        if (!(_parentOp instanceof QuickOperation)) {
            return;
        }
        QuickOperation q_ = (QuickOperation) _parentOp;
        AbstractCoverageResult resultFirst_ = getCovers(_block, _curOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
        AbstractCoverageResult resultLast_ = getCovers(_block, _nextSiblingOp, _cov, _annot, _indexAnnotGroup, _indexAnnot);
        StringList errs_ = q_.getErrs();
        AnaTypeFct functionTest_ = q_.getFunctionTest();
        if (functionTest_ != null) {
            StringList title_ = new StringList();
            title_.addAllElts(getCoversFoundReport(_vars, resultFirst_));
            addParts(_vars, _currentFileName, functionTest_,_offsetEnd,1, errs_,title_,_parts);
        } else {
            safeReport(_vars, resultFirst_, _offsetEnd,_parts,1);
        }
        AnaTypeFct function_ = q_.getFunction();
        if (function_ != null) {
            StringList title_ = new StringList();
            title_.addAllElts(getCoversFoundReport(_vars, resultLast_));
            addParts(_vars, _currentFileName, function_,_offsetEnd+1,1, errs_,title_,_parts);
        } else {
            safeReport(_vars, resultLast_, _offsetEnd+1,_parts,1);
        }
        if (_vars.isImplicit()) {
            _parts.add(mergeParts(_vars,_currentFileName,q_.getConvert(),_offsetEnd+2,new StringList(),new StringList()));
        }
    }

    private static void right(VariablesOffsets _vars,
                              String _currentFileName,
                              int _offsetEnd,
                              MethodOperation _parent,
                              CustList<PartOffset> _parts) {
        processRightIndexer(_vars, _currentFileName, _offsetEnd, _parent, _parts);
        if (_parent instanceof AnnotationInstanceOperation
                || _parent instanceof IdOperation) {
            _parts.addAllElts(_parent.getPartOffsetsEnd());
        }
    }

    private static void processRightIndexer(VariablesOffsets _vars, String _currentFileName, int _offsetEnd, MethodOperation _parentOp, CustList<PartOffset> _parts) {
        if (_parentOp instanceof ArrOperation) {
            ArrOperation par_ = (ArrOperation) _parentOp;
            AnaTypeFct function_;
            if (par_.getArrContent().isVariable()) {
                function_ = par_.getFunctionSet();
            } else {
                function_ = par_.getFunctionGet();
            }
            if (function_ != null) {
                addParts(_vars, _currentFileName, function_,_offsetEnd,1,new StringList(),new StringList(),_parts);
            } else {
                String er_ = par_.getNbErr();
                if (!er_.isEmpty()) {
                    addParts(_vars, _currentFileName, null,_offsetEnd,1,new StringList(er_),new StringList(er_),_parts);
                }
            }
        }
    }

    private static void processUnaryRightOperations(VariablesOffsets _vars,
                                                    String _currentFileName, int _sum, int _offsetEnd, OperationNode _curOp, MethodOperation _parent, CustList<PartOffset> _parts) {
        if (_curOp.getIndexChild() == 0 &&_parent instanceof InstanceOfOperation) {
            if (!_parent.getErrs().isEmpty()) {
                int begin_ = ((InstanceOfOperation)_parent).getOffset()+_sum + _parent.getIndexInEl();
                _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_parent.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                _parts.add(new PartOffset("</a>",begin_+_vars.getKeyWords().getKeyWordInstanceof().length()));
            }
            _parts.addAllElts(((InstanceOfOperation)_parent).getPartOffsets());
        }
        processPostIncr(_vars, _currentFileName, _sum, _offsetEnd, _curOp,_parent, _parts);
    }

    private static void processPostIncr(VariablesOffsets _vars, String _currentFileName, int _sum, int _offsetEnd, OperationNode _curOp, MethodOperation _parent, CustList<PartOffset> _parts) {
        if (_curOp.getIndexChild() == 0&&_parent instanceof SemiAffectationOperation) {
            SemiAffectationOperation par_ = (SemiAffectationOperation) _parent;
            if(par_.isPost()) {
                if (_vars.isImplicit()) {
                    _parts.add(mergeParts(_vars, _currentFileName, par_.getFunctionFrom(), _offsetEnd, new StringList(), new StringList()));
                    _parts.add(mergeParts(_vars, _currentFileName, par_.getFunctionTo(), _offsetEnd, new StringList(), new StringList()));
                }
                boolean err_ = true;
                AnaTypeFct function_ = par_.getFunction();
                if (function_ != null) {
                    addParts(_vars, _currentFileName, function_,_offsetEnd,1,_parent.getErrs(),_parent.getErrs(),_parts);
                    err_ = false;
                }
                SettableElResult settable_ = par_.getSettable();
                if (settable_ instanceof ArrOperation && ((ArrOperation) settable_).getFunctionSet() != null) {
                    ArrOperation parArr_ = (ArrOperation) settable_;
                    addParts(_vars, _currentFileName, parArr_.getFunctionSet(),_offsetEnd+1,1,_parent.getErrs(),_parent.getErrs(),_parts);
                    err_ = false;
                }
                if (err_) {
                    if (!_parent.getErrs().isEmpty()) {
                        int begin_ = ((SemiAffectationOperation)_parent).getOpOffset()+_sum + _parent.getIndexInEl();
                        _parts.add(new PartOffset("<a title=\""+LinkageUtil.transform(StringUtil.join(_parent.getErrs(),"\n\n")) +"\" class=\"e\">",begin_));
                        _parts.add(new PartOffset("</a>",begin_+2));
                    }
                }
            }
        }
    }

    private static void safeReport(VariablesOffsets _vars, AbstractCoverageResult _res, int _offsetEnd, CustList<PartOffset> _parts, int _delta) {
        StringList founds_ = getCoversFoundReport(_vars,_res);
        if (founds_.isEmpty()) {
            return;
        }
        basicValue(_offsetEnd, _parts, _delta, founds_);
    }

    private static StringList getCoversFoundReport(VariablesOffsets _vars, AbstractCoverageResult _res) {
        StringList founds_ = new StringList();
        if (_res.isStrictPartialCovered()) {
            if (_res instanceof BooleanCoverageResult) {
                founds_ = getCoversLogicalAndOrReport(_vars,(BooleanCoverageResult)_res);
            }
            if (_res instanceof NullCoverageResult) {
                founds_ = getCoversNullSafeReport(_vars,(NullCoverageResult)_res);
            }
            if (_res instanceof NullBooleanCoverageResult){
                founds_ = getCoversNullBooleanSafeReport(_vars,(NullBooleanCoverageResult)_res);
            }
        }
        return founds_;
    }

    private static StringList getCoversLogicalAndOrReport(VariablesOffsets _vars, BooleanCoverageResult _res) {
        StringList founds_ = new StringList();
        if (_res.isCoverTrue()) {
            founds_.add(_vars.getDisplayedStrings().getTrueString());
        }
        if (_res.isCoverFalse()) {
            founds_.add(_vars.getDisplayedStrings().getFalseString());
        }
        return founds_;
    }

    private static StringList getCoversNullBooleanSafeReport(VariablesOffsets _vars, NullBooleanCoverageResult _res) {
        StringList founds_ = new StringList();
        if (_res.isCoverNull()) {
            founds_.add(_vars.getDisplayedStrings().getNullCoverString());
        }
        if (_res.isCoverTrue()) {
            founds_.add(_vars.getDisplayedStrings().getTrueString());
        }
        if (_res.isCoverFalse()) {
            founds_.add(_vars.getDisplayedStrings().getFalseString());
        }
        return founds_;
    }

    private static StringList getCoversNullSafeReport(VariablesOffsets _vars, NullCoverageResult _res) {
        StringList founds_ = new StringList();
        if (_res.isCoverNull()) {
            founds_.add(_vars.getDisplayedStrings().getNullCoverString());
        }
        if (_res.isCoverNotNull()) {
            founds_.add(_vars.getDisplayedStrings().getNotNullCoverString());
        }
        return founds_;
    }

    private static void basicValue(int _offsetEnd, CustList<PartOffset> _parts, int _delta, StringList _founds) {
        String tag_ = "<a title=\""+ transform(StringUtil.join(_founds,","))+"\">";
        _parts.add(new PartOffset(tag_, _offsetEnd));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,_offsetEnd+ _delta));
    }
    private static PartOffset mergeParts(VariablesOffsets _vars, String _currentFileName,
                                         AnaTypeFct _id, int _begin,
                                         StringList _errors,
                                         StringList _title) {
        CustList<PartOffset> parts_ = new CustList<PartOffset>();
        addParts(_vars,_currentFileName,_id,_begin,1,_errors,_title,parts_);
        if (parts_.isEmpty()) {
            return new PartOffset("",_begin);
        }
        return new PartOffset(parts_.first().getPart()+" "+parts_.last().getPart(),_begin);
    }
    private static void addParts(VariablesOffsets _vars, String _currentFileName,
                                 AnaTypeFct _id, int _begin, int _length,
                                 StringList _errors,
                                 StringList _title,
                                 CustList<PartOffset> _parts) {
        addParts(_vars, _currentFileName,
                _id, _begin, _length,
        _errors,
        _title,
        _parts,-1);
    }

    private static void addParts(DisplayedStrings _vars, String _currentFileName, AnaTypeFct _id,
                                 int _begin, int _length,
                                 StringList _errors,
                                 StringList _title,
                                 CustList<PartOffset> _parts) {
        addParts(_vars, _currentFileName, _id,_begin,_length,_errors,_title,_parts, -1);
    }

    private static void addParts(VariablesOffsets _vars, String _currentFileName,
                                 AnaTypeFct _id, int _begin, int _length,
                                 StringList _errors,
                                 StringList _title,
                                 CustList<PartOffset> _parts, int _name) {
        DisplayedStrings dis_ = _vars.getDisplayedStrings();
        addParts(dis_, _currentFileName, _id, _begin, _length, _errors, _title, _parts, _name);
    }

    private static void addParts(DisplayedStrings _dis, String _currentFileName, AnaTypeFct _id,
                                 int _begin, int _length, StringList _errors, StringList _title, CustList<PartOffset> _parts, int _name) {
        String rel_ = getRelativize(_currentFileName, _id);
        if (rel_.isEmpty()) {
            if (!_errors.isEmpty()) {
                String tag_;
                tag_ = "<a title=\""+ transform(StringUtil.join(_errors,"\n\n"))+"\" class=\"e\">";
                _parts.add(new PartOffset(tag_,_begin));
                tag_ = "</a>";
                _parts.add(new PartOffset(tag_,_begin+_length));
            }
            return;
        }
        NamedFunctionBlock function_ = _id.getFunction();
        String tag_;
        if (function_ instanceof OperatorBlock) {
            tag_ = "<a"+name(_name)+"title=\""+ merge(_title,function_.getSignature(_dis))+"\" href=\""+rel_+"\""+classErr(_errors)+">";
        } else {
            String cl_ = _id.getType().getFullName();
            tag_ = "<a"+name(_name)+"title=\""+ merge(_title,cl_ +"."+ function_.getSignature(_dis))+"\" href=\""+rel_+"\""+classErr(_errors)+">";
        }
        _parts.add(new PartOffset(tag_,_begin));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,_begin+_length));
    }

    private static String merge(StringList _errors, String _link) {
        StringList list_ = new StringList();
        list_.addAllElts(_errors);
        list_.add(_link);
        return transform(StringUtil.join(list_,"\n\n"));
    }

    private static String name(int _name) {
        if (_name < 0) {
            return " ";
        }
        return " name=\"m"+_name+"\" ";
    }
    private static String classErr(StringList _errors) {
        if (_errors.isEmpty()) {
            return "";
        }
        return " class=\"e\"";
    }

    private static String getRelativize(String _currentFileName, AnaTypeFct _id) {
        if (_id == null || _id.getFunction() == null) {
            return "";
        }
        NamedFunctionBlock function_ = _id.getFunction();
        if (function_ instanceof OperatorBlock) {
            String file_ = function_.getFile().getRenderFileName();
            return relativize(_currentFileName, file_ + "#m" + function_.getNameOffset());
        }
        if (!ContextUtil.isFromCustFile(_id.getType())) {
            return "";
        }
        return relativize(_currentFileName, function_.getFile().getRenderFileName() + "#m" + function_.getNameOffset());
    }

    private static AbstractCoverageResult getCovers(Block _block, OperationNode _oper, Coverage _cov, boolean _annot, int _indexAnnotGroup, int _indexAnnot) {
        return _cov.getCovers(_block,_oper, _annot, _indexAnnotGroup, _indexAnnot);
    }

    private static void updateFieldAnchor(RootBlock _type, StringList _errs, CustList<PartOffset> _parts, ClassField _id, int _begin, int _length, String _currentFileName, int _offset) {
        String className_ = _id.getClassName();
        className_ = StringExpUtil.getIdFromAllTypes(className_);
        if (!ContextUtil.isFromCustFile(_type)) {
            if (!_errs.isEmpty()) {
                String err_ = transform(StringUtil.join(_errs,"\n\n"));
                _parts.add(new PartOffset("<a title=\""+err_+"\" class=\"e\">",_begin));
                _parts.add(new PartOffset("</a>",_begin+_length));
            }
            return;
        }
        String file_ = _type.getFile().getRenderFileName();
        String rel_ = relativize(_currentFileName,file_+"#m"+_offset);
        String tag_ = "<a title=\""+merge(_errs,className_ +"."+ _id.getFieldName())+"\" href=\""+rel_+"\""+classErr(_errs)+">";
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
        int count_ = StringUtil.indexesOfChar(_currentFile,'/').size() - countCommon_;
        for (int i = 0; i < count_; i++) {
            b_.append("../");
        }
        return b_.append(relFile_).toString();
    }
    public static String transform(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (c > 126) {
                str_.append("&#");
                str_.append((int)c);
                str_.append(";");
            } else if (c == '<') {
                str_.append("&lt;");
            } else if (c == '>') {
                str_.append("&gt;");
            } else if (c == '&') {
                str_.append("&amp;");
            } else if (c == '\"') {
                str_.append("&quot;");
            } else if (c < ' ') {
                str_.append(processSpace(c));
            } else {
                str_.append(c);
            }
        }
        return str_.toString();
    }
    private static boolean canCallToString(VariablesOffsets _vars, AnaClassArgumentMatching _type) {
        if (_type.isArray()) {
            return false;
        }
        for (String s: _type.getNames()) {
            String id_ = StringExpUtil.getIdFromAllTypes(s);
            if (StringUtil.contains(_vars.getToStringOwners(),id_)) {
                return true;
            }
        }
        return false;
    }

    private static void refLabel(VariablesOffsets _vars, CustList<PartOffset> _parts, String _label, int _offset) {
        if (_label.isEmpty()) {
            return;
        }
        if (_vars.getState() != null) {
            return;
        }
        _parts.add(new PartOffset("<a name=\"m"+_offset+"\">",_offset));
        _parts.add(new PartOffset("</a>",_offset+_label.length()));
    }

    private static void refLabelError(VariablesOffsets _vars, Block _bl, CustList<PartOffset> _parts, String _label, int _offset) {
        if (_label.isEmpty()) {
            return;
        }
        if (_vars.getState() != null) {
            return;
        }
        StringList errs_ = _bl.getErrorsLabels();
        if (!errs_.isEmpty()) {
            String err_ = transform(StringUtil.join(errs_,"\n\n"));
            _parts.add(new PartOffset("<a name=\"m"+_offset+"\" title=\""+err_+"\" class=\"e\">",_offset));
        } else {
            _parts.add(new PartOffset("<a name=\"m"+_offset+"\">",_offset));
        }
        _parts.add(new PartOffset("</a>",_offset+_label.length()));
    }
    private static boolean leftOperNotUnary(OperationNode _op) {
        if (_op instanceof TernaryOperation) {
            return true;
        }
        if (_op instanceof RefTernaryOperation) {
            return true;
        }
        if (_op instanceof BadTernaryOperation) {
            return true;
        }
        return _op instanceof InvokingOperation;
    }
    private static boolean middleOper(OperationNode _op) {
        if (_op instanceof NumericOperation) {
            return true;
        }
        if (_op instanceof ShortTernaryOperation) {
            return true;
        }
        if (_op instanceof RefShortTernaryOperation) {
            return true;
        }
        if (_op instanceof AbstractDotOperation) {
            if (!_op.getOperations().getOperators().firstValue().isEmpty()) {
                return true;
            }
        }
        if (_op instanceof AffectationOperation) {
            return true;
        }
        if (_op instanceof CmpOperation) {
            return true;
        }
        if (_op instanceof EqOperation) {
            return true;
        }
        if (_op instanceof CompoundAffectationOperation) {
            return true;
        }
        if (_op instanceof DeclaringOperation) {
            return true;
        }
        if (_op instanceof NullSafeOperation) {
            return true;
        }
        return _op instanceof QuickOperation;
    }


    private static boolean isImplicitReturn(Block _ret) {
        if (!(_ret instanceof ReturnMethod)) {
            return false;
        }
        return ((ReturnMethod)_ret).isImplicit();
    }
}
