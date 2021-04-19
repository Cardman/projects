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
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.DisplayedStrings;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class LinkageUtil {

    public static final String BEGIN_HEAD = "<head>";
    public static final String END_HEAD = "</head>";
    public static final String BEGIN = "<html>";
    public static final String BEGIN_BODY = "<body>";
    public static final String BEGIN_BODY_PRE = BEGIN_BODY+"<pre>";
    public static final String END_BODY = "</body>";
    public static final String END_BODY_PRE = "</pre>"+END_BODY;
    public static final String END = "</html>";
    public static final String CSS = "css/style.css";
    public static final String TR = "<tr>";
    public static final String TD = "<td>";
    public static final String ETD = "</td>";
    public static final String ETR = "</tr>";
    public static final String LT = "&lt;";
    public static final String GT = "&gt;";
    public static final String AMP = "&amp;";
    public static final String QUOT = "&quot;";
    public static final String FULL = "f";
    public static final String PARTIAL = "p";
    public static final String NONE = "n";
    public static final String STRING = "s";
    public static final String FULL_INIT = "g";
    public static final String PARTIAL_INIT = "q";
    public static final String FULL_2 = "f2";
    public static final String FULL_INIT_2 = "g2";
    public static final String PARTIAL_2 = "p2";
    public static final String PARTIAL_INIT_2 = "q2";
    public static final String NONE_2 = "n2";
    public static final String TYPE = "t";
    public static final String IMPORT = "i";
    public static final String COMMENT = "c";
    public static final String EMPTY = "";
    public static final char QUOT_CHAR = '\"';
    public static final char LEFT_TAG = '<';
    public static final char RIGHT_TAG = '>';
    public static final char SPEC_CHAR = '&';
    public static final char SPACE_CH = ' ';
    public static final char SEP_DIR = '/';
    public static final String PARENT = "..";
    public static final char LINE_RET_CHAR = '\n';
    public static final char TAB_CHAR = '\t';
    public static final String LINE_RET = "\n";
    public static final String TAB = "\t";
    public static final String DEF_SPACE = " ";
    public static final String BEGIN_ENCODE = "&#";
    public static final String END_ENCODE = ";";

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
            String fileExp_ = f.getFileName() +ExportCst.EXT;
            CustList<PartOffset> listStr_ = processError(toStringOwners_,f,fileExp_, keyWords_, displayedStrings_, implicit_);
            StringBuilder xml_ = build(f, value_, listStr_);
            String rel_ = relativize(fileExp_, CSS);
            String cssPart_ = BEGIN_HEAD + encode(_analyzing.isEncodeHeader()) +
                    link(rel_) +
                    END_HEAD;
            files_.addEntry(fileExp_, BEGIN +cssPart_+ BEGIN_BODY_PRE +xml_+ END_BODY_PRE+END);
        }
        String cssContent_ = ".e{background-color:red;}\n";
        cssContent_ += ".w{background-color:yellow;}\n";
        cssContent_ += ".s{color:blue;}\n";
        cssContent_ += ".c{color:grey;background-color:white;}\n";
        cssContent_ += ".i{color:red;}\n";
        cssContent_ += ".t{background-color:white;}\n";
        files_.addEntry(CSS,cssContent_);
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
            String fileExp_ = f.getFileName() +ExportCst.EXT;
            CustList<PartOffset> listStr_ = processReport(toStringOwners_,f,fileExp_, cov_, keyWords_, standards_);
            StringBuilder xml_ = build(f, value_, listStr_);
            String rel_ = relativize(fileExp_, CSS);
            String cssPart_ = BEGIN_HEAD +encode(cov_.isDisplayEncode())+
                    link(rel_) +
                    END_HEAD;
            files_.addEntry(fileExp_, BEGIN +cssPart_+ BEGIN_BODY_PRE +xml_+END_BODY_PRE+END);
        }
        IdMap<RootBlock,String> types_ = new IdMap<RootBlock,String>();
        for (RootBlock t: refFoundTypes_) {
            if (t.getFile().isPredefined()) {
                continue;
            }
            int countCall_ = 0;
            int count_ = 0;
            for (AbsBk b: ClassesUtil.getDirectChildren(t)) {
                if (AbsBk.isAnnotBlock(b)) {
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
            types_.addEntry(t,countCall_+ExportCst.RATIO_COVERAGE+count_);
        }
        int calledOps_ = 0;
        for (OperatorBlock b: operators_) {
            if (cov_.getFctRes(b).isCalled()) {
                calledOps_++;
            }
        }
        String callTable_ = "calls"+ExportCst.EXT;
        StringBuilder table_ = new StringBuilder(BEGIN);
        table_.append(BEGIN_HEAD);
        table_.append(encode(cov_.isDisplayEncode()));
        table_.append(END_HEAD);
        table_.append(BEGIN_BODY);
        table_.append("<table>");
        table_.append(TR);
        table_.append(TD);
        table_.append(ETD);
        table_.append(TD);
        table_.append(calledOps_);
        table_.append(ExportCst.RATIO_COVERAGE);
        table_.append(operators_.size());
        table_.append(ETD);
        table_.append(ETR);
        for (EntryCust<RootBlock,String> e: types_.entryList()) {
            table_.append(TR);
            table_.append(TD);
            table_.append(e.getKey().getFullName());
            table_.append(ETD);
            table_.append(TD);
            table_.append(e.getValue());
            table_.append(ETD);
            table_.append(ETR);
        }
        table_.append("</table>");
        table_.append(END_BODY);
        table_.append(END);
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
        files_.addEntry(CSS,cssContent_);
        return files_;
    }

    private static String link(String _rel) {
        return "<link href=\"" + _rel + "\" rel=\"stylesheet\" type=\"text/css\"/>";
    }

    private static String encode(boolean _encode) {
        if (_encode) {
            return "<meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/>";
        }
        return EMPTY;
    }
    private static StringBuilder build(FileBlock _fileBlock, String _value, CustList<PartOffset> _listStr) {
        String value_ = _value;
        if (_value.isEmpty()) {
            value_ = DEF_SPACE;
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
            prepend(xml_, part_);
        }
        return xml_;
    }

    private static void insertTr(FileBlock _ex,StringBuilder _xml, char _ch, int _index) {
        String tr_ = transformText(_ch);
        if (_ex.getBeginComments().containsObj(_index)) {
            prepend(_xml, tr_);
            prepend(_xml, ExportCst.span(COMMENT));
            return;
        }
        if (_ex.getEndComments().containsObj(_index)) {
            prepend(_xml, ExportCst.END_SPAN);
            prepend(_xml, tr_);
            return;
        }
        prepend(_xml, tr_);
    }

    private static void prepend(StringBuilder _xml, String _tr) {
        _xml.insert(0, _tr);
    }

    private static String transformText(char _ch) {
        if (_ch > 126) {
            return escapeNum(_ch);
        }
        if (_ch < SPACE_CH) {
            return processSpace(_ch);
        }
        if (_ch == LEFT_TAG) {
            return LT;
        }
        if (_ch == RIGHT_TAG) {
            return GT;
        }
        if (_ch == SPEC_CHAR) {
            return AMP;
        }
        return Character.toString(_ch);
    }

    private static String processSpace(char _ch) {
        if (_ch == LINE_RET_CHAR) {
            return LINE_RET;
        }
        if (_ch == TAB_CHAR) {
            return TAB;
        }
        return DEF_SPACE;
    }

    private static CustList<PartOffset> processError(StringList _toStringOwers, FileBlock _ex, String _fileExp, KeyWords _keyWords, DisplayedStrings _displayedStrings, boolean _implicit){
        CustList<PartOffset> list_ = new CustList<PartOffset>();
        list_.add(new PartOffset(ExportCst.span(TYPE),0));
        VariablesOffsets vars_ = new VariablesOffsets();
        vars_.addStackElt(new LinkageStackElement(_ex.getLength()));
        vars_.setKeyWords(_keyWords);
        vars_.setImplicit(_implicit);
        vars_.setDisplayedStrings(_displayedStrings);
        vars_.setToStringOwners(_toStringOwers);
        vars_.setCurrentFileName(_fileExp);
        if (_ex.getFirstChild() == null || !_ex.getErrorsFiles().getLi().isEmpty()) {
            processFileBlockError(_ex,list_);
            list_.add(new PartOffset(ExportCst.END_SPAN,Math.max(1, _ex.getLength())));
            return list_;
        }
        AbsBk child_ = _ex;
        while (child_ != null) {
            child_ = loopErr(_ex, list_, vars_, child_);
        }
        return list_;
    }

    private static AbsBk loopErr(FileBlock _ex, CustList<PartOffset> _list, VariablesOffsets _vars, AbsBk _child) {
        _vars.getLastStackElt().setBlock(_child);
        processFileHeader(_list, _child);
        if (isOuterBlock(_child)) {
            processGlobalRootBlockError((BracedBlock) _child, _list);
            if (!((BracedBlock) _child).getGlobalErrorsPars().getLi().isEmpty()) {
                return nextSkip(_vars,_list,_child, _ex);
            }
        }
        processBlockError(_list, _vars, _child);
        if (_vars.goesToProcess()) {
            _vars.addStackElt();
            return _vars.getLastStackElt().getBlock();
        }
        processEndBlockError(_list, _child);
        return afterBlock(_ex, _list, _vars, _child);
    }

    private static void processFileHeader(CustList<PartOffset> _list, AbsBk _child) {
        if (_child instanceof FileBlock) {
            processFileBlockReport((FileBlock) _child, _list);
        }
    }

    private static boolean isOuterBlock(AbsBk _child) {
        return _child instanceof RootBlock || _child instanceof OperatorBlock;
    }

    private static void processEndBlockError(CustList<PartOffset> _list, AbsBk _child) {
        if (endHeaderBlock(_child)) {
            addErrBlock(_list, _child);
        }
    }

    private static boolean endHeaderBlock(AbsBk _child) {
        return _child instanceof Line
                || _child instanceof NamedFunctionBlock && !AbsBk.isAnonBlock(_child)
                || _child instanceof InfoBlock;
    }

    private static void processBlockError(CustList<PartOffset> _list, VariablesOffsets _vars, AbsBk _child) {
        if (_vars.getLastStackElt().noVisited()) {
            if (beginHeaderBlock(_child)) {
                addErrBlock(_list, _child);
            }
        }
        if (_child instanceof RootBlock) {
            if (_child instanceof InnerElementBlock) {
                processInnerElementBlockError(_vars,(InnerElementBlock) _child, _list);
            } else {
                processRootBlockError(_vars, (RootBlock) _child, _list);
            }
        }
        if (_child instanceof InternOverrideBlock) {
            processInternOverrideBlock(_vars, _list, (InternOverrideBlock) _child);
        }
        if (_child instanceof OperatorBlock) {
            processOverridableBlockError(_vars,(OperatorBlock) _child, _list);
        }
        if (_child instanceof ConstructorBlock) {
            processConstructorBlockError(_vars,(ConstructorBlock) _child, _list);
        }
        if (_child instanceof NamedCalledFunctionBlock) {
            processNamedCalledFunctionBlockError(_list, _vars, (NamedCalledFunctionBlock) _child);
        }
        if (_child instanceof SwitchMethodBlock) {
            processSwitchMethodError(_vars,(SwitchMethodBlock) _child, _list);
        }
        if (_child instanceof ElementBlock) {
            processElementBlockError(_vars,(ElementBlock) _child, _list);
        }
        if (_child instanceof FieldBlock) {
            processFieldBlockError(_vars,(FieldBlock) _child, _list);
        }
        if (_child instanceof WhileCondition) {
            processWhileConditionError(_vars,(WhileCondition) _child, _list);
        }
        if (_child instanceof IfCondition) {
            processIfConditionError(_vars,(IfCondition) _child, _list);
        }
        if (_child instanceof ElseIfCondition) {
            processConditionError((ElseIfCondition) _child, _vars, _list);
            processTestCondition(_vars, (ElseIfCondition) _child, _list);
        }
        if (_child instanceof DoBlock) {
            processDoBlockError(_vars, (DoBlock) _child, _list);
        }
        if (_child instanceof DoWhileCondition) {
            processConditionError((DoWhileCondition) _child, _vars, _list);
            processTestCondition(_vars, (DoWhileCondition) _child, _list);
        }
        if (_child instanceof SwitchBlock) {
            processSwitchBlockError(_vars,(SwitchBlock) _child, _list);
        }
        if (_child instanceof CaseCondition) {
            processCaseConditionError(_vars,(CaseCondition) _child, _list);
        }
        if (_child instanceof DefaultCondition) {
            processDefaultConditionError((DefaultCondition) _child, _list);
        }
        if (_child instanceof TryEval) {
            processTryEvalError(_vars, (TryEval) _child, _list);
        }
        if (_child instanceof CatchEval) {
            processCatchEvalError((CatchEval) _child, _list);
        }
        if (_child instanceof DeclareVariable) {
            processDeclareVariableError(_vars,(DeclareVariable) _child, _list);
        }
        if (_child instanceof Line) {
            processLineError(_vars,(Line) _child, _list);
        }
        if (_child instanceof ForIterativeLoop) {
            processForIterativeLoopError(_vars,(ForIterativeLoop) _child, _list);
        }
        if (_child instanceof ForEachLoop) {
            processForEachLoopError(_vars,(ForEachLoop) _child, _list);
        }
        if (_child instanceof ForEachTable) {
            processForEachTableError(_vars,(ForEachTable) _child, _list);
        }
        if (_child instanceof ForMutableIterativeLoop) {
            processForMutableIterativeLoopError(_vars,(ForMutableIterativeLoop) _child, _list);
        }
        if (_child instanceof ReturnMethod) {
            processReturnMethodError(_vars,(ReturnMethod) _child, _list);
        }
        if (_child instanceof Throwing) {
            processThrowingError(_vars,(Throwing) _child, _list);
        }
        if (_child instanceof BreakBlock) {
            processBreakBlockError((BreakBlock) _child, _list);
        }
        if (_child instanceof ContinueBlock) {
            processContinueBlockError((ContinueBlock) _child, _list);
        }
    }

    private static boolean beginHeaderBlock(AbsBk _child) {
        return !(_child instanceof AnnotableParametersBlock) && !(_child instanceof InfoBlock)
                && !(_child instanceof Line) && !(_child instanceof DeclareVariable)
                && !(_child instanceof EmptyInstruction) && !(_child instanceof RootBlock) && !isImplicitReturn(_child);
    }

    private static void addErrBlock(CustList<PartOffset> _list, AbsBk _child) {
        if (!_child.getErrorsBlock().isEmpty()) {
            String err_ = StringUtil.join(_child.getErrorsBlock(), ExportCst.JOIN_ERR);
            int off_ = _child.getBegin();
            int l_ = _child.getLengthHeader();
            _list.add(new PartOffset(ExportCst.anchorErr(err_), off_));
            _list.add(new PartOffset(ExportCst.END_ANCHOR, off_ + l_));
        }
    }

    private static void processNamedCalledFunctionBlockError(CustList<PartOffset> _list, VariablesOffsets _vars, NamedCalledFunctionBlock _child) {
        if (_child.getTypeCall() == NameCalledEnum.OVERRIDABLE) {
            processOverridableBlockError(_vars, _child, _list);
        }
        if (_child.getTypeCall() == NameCalledEnum.ANONYMOUS) {
            processAnonymousFctBlockError(_vars, _child, _list);
        }
        if (_child.getTypeCall() == NameCalledEnum.ANNOTATION) {
            processAnnotationMethodBlockError(_vars, _child, _list);
        }
    }

    private static void processFileBlockError(FileBlock _cond, CustList<PartOffset> _parts) {
        for (GraphicErrorInterpret g: _cond.getErrorsFiles().getLi()) {
            int index_ = g.getIndexFile();
            _parts.add(new PartOffset(ExportCst.anchorErr(g.getBuiltError()), index_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, index_+ g.getLength()));
        }
    }
    private static void processGlobalRootBlockError(BracedBlock _cond, CustList<PartOffset> _parts) {
        for (GraphicErrorInterpret g: _cond.getGlobalErrorsPars().getLi()) {
            int index_ = g.getIndexFile();
            _parts.add(new PartOffset(ExportCst.anchorErr(g.getBuiltError()), index_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, index_+ g.getLength()));
        }
    }
    private static CustList<PartOffset> processReport(StringList _toStringOwers, FileBlock _ex, String _fileExp, Coverage _coverage, KeyWords _keyWords, LgNames _standards){
        CustList<PartOffset> list_ = new CustList<PartOffset>();
        list_.add(new PartOffset(ExportCst.span(TYPE),0));
        VariablesOffsets vars_ = new VariablesOffsets();
        vars_.addStackElt(new LinkageStackElement(_ex.getLength()));
        vars_.setKeyWords(_keyWords);
        vars_.setImplicit(_coverage.isImplicit());
        vars_.setDisplayedStrings(_standards.getDisplayedStrings());
        vars_.setToStringOwners(_toStringOwers);
        vars_.setCurrentFileName(_fileExp);
        AbsBk child_ = _ex;
        vars_.getLastStackElt().setBlock(child_);
        while (child_ != null) {
            child_ = loopReport(_ex, _coverage, list_, vars_, child_);
        }
        return list_;
    }

    private static AbsBk loopReport(FileBlock _ex, Coverage _coverage, CustList<PartOffset> _list, VariablesOffsets _vars, AbsBk _child) {
        _vars.getLastStackElt().setBlock(_child);
        processBlockReport(_coverage, _list, _vars, _child);
        if (_vars.goesToProcess()) {
            _vars.addStackElt();
            return _vars.getLastStackElt().getBlock();
        }
        return afterBlock(_ex, _list, _vars, _child);
    }

    private static AbsBk afterBlock(FileBlock _ex, CustList<PartOffset> _list, VariablesOffsets _vars, AbsBk _child) {
        if (_vars.getLastStackElt().isStopVisit()) {
            _vars.removeLastStackElt();
            return redirect(_vars);
        }
        AbsBk child_ = next(_vars,_list,_child, _ex);
        if (child_ == null) {
            _vars.removeLastStackElt();
            child_ = redirect(_vars);
        }
        return child_;
    }

    private static void processBlockReport(Coverage _coverage, CustList<PartOffset> _list, VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof IfCondition) {
            processIfConditionReport(_vars,(IfCondition) _child, _list, _coverage);
        }
        if (_child instanceof ElseIfCondition) {
            processElseIfConditionReport(_vars,(ElseIfCondition) _child, _list, _coverage);
        }
        if (_child instanceof WhileCondition) {
            processWhileConditionReport(_vars,(WhileCondition) _child, _list, _coverage);
        }
        if (_child instanceof DoWhileCondition) {
            processDoWhileConditionReport(_vars,(DoWhileCondition) _child, _list, _coverage);
        }
        if (_child instanceof DoBlock) {
            processDoBlockReport(_vars,(DoBlock) _child, _list);
        }
        if (_child instanceof ForMutableIterativeLoop) {
            processForMutableIterativeLoopReport(_vars,(ForMutableIterativeLoop) _child, _list, _coverage);
        }
        if (_child instanceof SwitchBlock) {
            processSwitchBlockReport(_vars,(SwitchBlock) _child, _list, _coverage);
        }
        if (_child instanceof CaseCondition) {
            processCaseConditionReport(_vars,(CaseCondition) _child, _list, _coverage);
        }
        if (_child instanceof DefaultCondition) {
            processDefaultConditionReport(_vars,(DefaultCondition) _child, _list, _coverage);
        }
        if (_child instanceof TryEval) {
            processTryEvalReport(_vars,(TryEval) _child, _list);
        }
        if (_child instanceof CatchEval) {
            processCatchEvalReport(_vars,(CatchEval) _child, _list, _coverage);
        }
        if (_child instanceof NullCatchEval) {
            processAbstractCatchEvalReport(_vars,(NullCatchEval) _child, _list, _coverage);
        }
        if (_child instanceof DeclareVariable) {
            processDeclareVariableReport(_vars,(DeclareVariable) _child, _list);
        }
        if (_child instanceof Line) {
            processLineReport(_vars,(Line) _child, _list, _coverage);
        }
        if (_child instanceof ReturnMethod) {
            processReturnMethodReport(_vars,(ReturnMethod) _child, _list, _coverage);
        }
        if (_child instanceof Throwing) {
            processThrowingReport(_vars,(Throwing) _child, _list, _coverage);
        }
        if (_child instanceof BreakBlock) {
            processBreakBlockReport((BreakBlock) _child, _list);
        }
        if (_child instanceof ContinueBlock) {
            processContinueBlockReport((ContinueBlock) _child, _list);
        }
        if (_child instanceof ForIterativeLoop) {
            processForIterativeLoopReport(_vars,(ForIterativeLoop) _child, _list, _coverage);
        }
        if (_child instanceof ForEachLoop) {
            processForEachLoopReport(_vars,(ForEachLoop) _child, _list, _coverage);
        }
        if (_child instanceof ForEachTable) {
            processForEachTableReport(_vars,(ForEachTable) _child, _list, _coverage);
        }
        if (_child instanceof ElementBlock) {
            processElementBlockReport(_vars,(ElementBlock) _child, _list, _coverage);
        }
        if (_child instanceof FieldBlock) {
            processFieldBlockReport(_vars,(FieldBlock) _child, _list, _coverage);
        }
        if (_child instanceof ConstructorBlock) {
            processConstructorBlockReport(_vars,(ConstructorBlock) _child, _list, _coverage);
        }
        if (_child instanceof NamedCalledFunctionBlock) {
            processNamedCalledFunctionBlockReport(_coverage, _list, _vars, (NamedCalledFunctionBlock) _child);
        }
        if (_child instanceof InternOverrideBlock) {
            processInternOverrideBlock(_vars, _list, (InternOverrideBlock) _child);
        }
        if (_child instanceof SwitchMethodBlock) {
            processSwitchMethodReport(_vars,(SwitchMethodBlock) _child, _list, _coverage);
        }

        if (_child instanceof OperatorBlock) {
            processOverridableBlockReport(_vars,(OperatorBlock) _child, _list, _coverage);
        }
        if (_child instanceof RootBlock) {
            if (_child instanceof InnerElementBlock) {
                processInnerElementBlockReport(_vars,(InnerElementBlock) _child, _list, _coverage);
            } else {
                processRootBlockReport(_vars,(RootBlock) _child, _list, _coverage);
            }
        }
        processFileHeader(_list, _child);
    }

    private static void processNamedCalledFunctionBlockReport(Coverage _coverage, CustList<PartOffset> _list, VariablesOffsets _vars, NamedCalledFunctionBlock _child) {
        if (_child.getTypeCall() == NameCalledEnum.OVERRIDABLE) {
            processOverridableBlockReport(_vars, _child, _list, _coverage);
        }
        if (_child.getTypeCall() == NameCalledEnum.ANONYMOUS) {
            processAnonymousFctReport(_vars, _child, _list, _coverage);
        }
        if (_child.getTypeCall() == NameCalledEnum.ANNOTATION) {
            processAnnotationMethodBlockReport(_vars, _child, _list, _coverage);
        }
    }

    private static AbsBk redirect(VariablesOffsets _vars) {
        if (_vars.hasEltStack()) {
            return _vars.getLastStackElt().getBlock();
        }
        return null;
    }
    private static AbsBk next(VariablesOffsets _vars, CustList<PartOffset> _list,AbsBk _current, AbsBk _ex) {
        AbsBk firstChild_ = _current.getFirstChild();
        if (firstChild_ != null) {
            return firstChild_;
        }
        return nextSkip(_vars,_list,_current,_ex);
    }
    private static AbsBk nextSkip(VariablesOffsets _vars, CustList<PartOffset> _list,AbsBk _current, AbsBk _ex) {
        AbsBk child_ = _current;
        while (true) {
            AbsBk nextSibling_ = child_.getNextSibling();
            if (nextSibling_ != null) {
                return nextSibling_;
            }
            BracedBlock parent_ = child_.getParent();
            if (parent_ == _ex || parent_ == null) {
                int indexEnd_ = _vars.getLastStackElt().getIndexEnd();
                _list.add(new PartOffset(ExportCst.END_SPAN, indexEnd_));
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
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond);
            String tag_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _parts.add(new PartOffset(tag_, off_));
            _parts.add(new PartOffset(ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordIf().length()));
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
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond);
            String tag_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _parts.add(new PartOffset(tag_, off_));
            _parts.add(new PartOffset(ExportCst.END_SPAN, off_ + _cond.getDelta()));
        }
        processConditionReport(_cond,_vars, _parts, _cov);
        processTestCondition(_vars, _cond, _parts);
    }
    private static void processWhileConditionReport(VariablesOffsets _vars, WhileCondition _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond);
            String tag_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _parts.add(new PartOffset(tag_, off_));
            _parts.add(new PartOffset(ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordWhile().length()));
        }
        processConditionReport(_cond,_vars, _parts, _cov);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars, _cond, _parts);
    }

    private static void processTestCondition(VariablesOffsets _vars, ConditionBlock _cond, CustList<PartOffset> _parts) {
        if (_vars.goesToProcess()) {
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
        if (_vars.getLastStackElt().noVisited()) {
            if (rootExp_ != null) {
                AbstractCoverageResult result_ = _cov.getCoversConditionsForMutable(_cond);
                String tag_ = headCoverage(result_);
                int off_ = _cond.getOffset();
                _parts.add(new PartOffset(tag_, off_));
                _parts.add(new PartOffset(ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordFor().length()));
            }
            appendVars(_vars,_cond, _parts);
        }
        OperationNode rootInit_ = _cond.getRootInit();
        if (_vars.getLastStackElt().getIndexLoop() == 0) {
            if (rootInit_ != null) {
                int off_ = _cond.getInitOffset();
                int offsetEndBlock_ = off_ + _cond.getInit().length();
                LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1, 0);
                buildNormalReport(_vars, _parts, _cov, rootInit_, in_);
                if (_vars.goesToProcess()) {
                    return;
                }
            }
            _vars.getLastStackElt().setIndexLoop(1);
        }
        if (_vars.getLastStackElt().getIndexLoop() == 1) {
            if (rootExp_ != null) {
                int off_ = _cond.getExpressionOffset();
                int offsetEndBlock_ = off_ + _cond.getExpression().length();
                LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 1, -1, 0);
                buildNormalReport(_vars, _parts, _cov, rootExp_, in_);
                if (_vars.goesToProcess()) {
                    return;
                }
            }
            _vars.getLastStackElt().setIndexLoop(2);
        }
        OperationNode rootStep_ = _cond.getRootStep();
        if (rootStep_ != null) {
            int off_ = _cond.getStepOffset();
            int offsetEndBlock_ = off_ + _cond.getStep().length();
            LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 2, -1, 0);
            buildNormalReport(_vars, _parts, _cov, rootStep_, in_);
            if (_vars.goesToProcess()) {
                return;
            }
        }
        _vars.getLastStackElt().setIndexLoop(0);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars,_cond, _parts);
    }
    private static void processForMutableIterativeLoopError(VariablesOffsets _vars, ForMutableIterativeLoop _cond, CustList<PartOffset> _parts) {
        if (_vars.getLastStackElt().noVisited()) {
            appendVars(_vars,_cond, _parts);
        }
        if (_vars.getLastStackElt().getIndexLoop() == 0) {
            OperationNode rootInit_ = _cond.getRootInit();
            if (rootInit_ != null) {
                int off_ = _cond.getInitOffset();
                buildNormErr(_vars, _cond, _parts, -1, rootInit_, off_, 0);
                if (_vars.goesToProcess()) {
                    return;
                }
            }
            _vars.getLastStackElt().setIndexLoop(1);
        }
        if (_vars.getLastStackElt().getIndexLoop() == 1) {
            OperationNode rootExp_ = _cond.getRootExp();
            if (rootExp_ != null) {
                int off_ = _cond.getExpressionOffset();
                buildNormErr(_vars, _cond, _parts, -1, rootExp_, off_, 1);
                if (_vars.goesToProcess()) {
                    return;
                }
            }
            _vars.getLastStackElt().setIndexLoop(2);
        }
        OperationNode rootStep_ = _cond.getRootStep();
        if (rootStep_ != null) {
            int off_ = _cond.getStepOffset();
            buildNormErr(_vars, _cond, _parts, -1, rootStep_, off_, 2);
            if (_vars.goesToProcess()) {
                return;
            }
        }
        _vars.getLastStackElt().setIndexLoop(0);
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
        if (_vars.getLastStackElt().noVisited()) {
            int full_ = 0;
            int count_ = 0;
            for (AbstractCoverageResult e : _cov.getCoverSwitchs(_cond).values()) {
                count_ += e.getCovered();
                full_ += e.getFull();
            }
            AbstractCoverageResult noDef_ = _cov.getCoverNoDefSwitchs(_cond);
            if (noDef_ != null) {
                count_ += noDef_.getCovered();
                full_ += noDef_.getFull();
            }
            String tag_ = headCoverage(full_, count_);
            int off_ = _cond.getOffset();
            _parts.add(new PartOffset(tag_ + ExportCst.anchor(count_ + ExportCst.RATIO_COVERAGE + full_), off_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR + ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordSwitch().length()));
        }
        int off_ = _cond.getValueOffset();
        int offsetEndBlock_ = off_ + _cond.getValue().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1, 0);
        buildNormalReport(_vars, _parts, _cov, root_, in_);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processSwitchBlockError(VariablesOffsets _vars, SwitchBlock _cond, CustList<PartOffset> _parts) {
        int off_ = _cond.getValueOffset();
        if (_vars.getLastStackElt().noVisited() && !_cond.getErr().isEmpty()) {
            _parts.add(new PartOffset(ExportCst.anchorErr(_cond.getErr()), off_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, off_ + 1));
        }
        OperationNode root_ = _cond.getRoot();
        buildNormErr(_vars, _cond, _parts, -1, root_, off_, 0);
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processCaseConditionReport(VariablesOffsets _vars, CaseCondition _cond, CustList<PartOffset> _parts, Coverage _cov) {
        SwitchBlock parent_ = _cond.getSwitchParent();
        AbstractCoverageResult result_;
        if (parent_ != null) {
            result_ = _cov.getCoverSwitchs(parent_,_cond);
        } else {
            result_ = _cov.getCoverSwitchsMethod(_cond.getSwitchMethod(),_cond);
        }
        int off_ = _cond.getValueOffset();
        String tag_ = getCaseDefaultTag(result_);
        if (_vars.getLastStackElt().noVisited()) {
            _parts.add(new PartOffset(tag_, off_));
        }
        if (!_cond.getImportedType().isEmpty()) {
            _parts.addAllElts(_cond.getPartOffsets());
            String variableName_ = _cond.getVariableName();
            int variableOffset_ = _cond.getVariableOffset();
            _parts.add(new PartOffset(ExportCst.anchorName(variableOffset_),variableOffset_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR,variableOffset_+ variableName_.trim().length()));
        } else if (_cond.isBuiltEnum()) {
            int delta_ = _cond.getFieldNameOffset();
            String typeEnum_ = _cond.getTypeEnum();
            String currentFileName_ = _vars.getCurrentFileName();
            updateFieldAnchor(_cond.getEnumBlock(), new StringList(),_parts,new ClassField(typeEnum_,_cond.getValue().trim()),off_,Math.max(1, _cond.getValue().length()),currentFileName_,delta_);
        } else {
            int offsetEndBlock_ = off_ + _cond.getValue().length();
            OperationNode root_ = _cond.getRoot();
            LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1, 0);
            buildNormalReport(_vars, _parts, _cov, root_, in_);
        }
        if (!_vars.goesToProcess()) {
            _parts.add(new PartOffset(ExportCst.END_SPAN,off_+ _cond.getValue().length()));
        }
    }

    private static String getCaseDefaultTag(AbstractCoverageResult _result) {
        String tag_;
        if (_result.isFullCovered()) {
            tag_ = ExportCst.span(FULL);
        } else {
            tag_ = ExportCst.span(NONE);
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
                    _parts.add(new PartOffset(ExportCst.anchorNameErr(variableOffset_,StringUtil.join(errs_,ExportCst.JOIN_ERR)), variableOffset_));
                } else {
                    _parts.add(new PartOffset(ExportCst.anchorName(variableOffset_),variableOffset_));
                }
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, variableOffset_ + variableName_.trim().length()));
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
            buildNormErr(_vars, _cond, _parts, -1, root_, off_, 0);
        }
    }

    private static void processDefaultConditionReport(VariablesOffsets _vars, DefaultCondition _cond, CustList<PartOffset> _parts, Coverage _cov) {
        SwitchBlock parent_ = _cond.getSwitchParent();
        AbstractCoverageResult result_;
        if (parent_ != null) {
            result_ = _cov.getCoverSwitchs(parent_,_cond);
        } else {
            result_ = _cov.getCoverSwitchsMethod(_cond.getSwitchMethod(),_cond);
        }
        String tag_ = getCaseDefaultTag(result_);
        int off_ = _cond.getOffset();
        _parts.add(new PartOffset(tag_,off_));
        _parts.add(new PartOffset(ExportCst.END_SPAN,off_+ _vars.getKeyWords().getKeyWordDefault().length()));
        if (!_cond.getVariableName().trim().isEmpty()) {
            off_ = _cond.getVariableOffset();
            _parts.add(new PartOffset(ExportCst.anchorName(off_,_cond.getInstanceTest()),off_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR,off_+_cond.getVariableName().length()));
        }
    }

    private static void processDefaultConditionError(DefaultCondition _cond, CustList<PartOffset> _parts) {
        if (!_cond.getVariableName().trim().isEmpty()) {
            StringList errs_ = _cond.getNameErrors();
            if (!errs_.isEmpty()) {
                int off_ = _cond.getVariableOffset();
                String tag_ = ExportCst.anchorNameErr(off_ ,StringUtil.join(errs_,ExportCst.JOIN_ERR));
                _parts.add(new PartOffset(tag_, off_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, off_ + _cond.getVariableName().trim().length()));
            } else {
                int off_ = _cond.getVariableOffset();
                String tag_ = ExportCst.anchorName(off_,_cond.getInstanceTest());
                _parts.add(new PartOffset(tag_,off_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,off_+_cond.getVariableName().length()));
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
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond);
            String tag_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _parts.add(new PartOffset(tag_, off_));
            _parts.add(new PartOffset(ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordWhile().length()));
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
        String tag_ = ExportCst.anchorName(_cond.getVariableNameOffset());
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
    }
    private static void processCatchEvalError(CatchEval _cond, CustList<PartOffset> _parts) {
        _parts.addAllElts(_cond.getPartOffsets());
        StringList errs_ = _cond.getNameErrors();
        if (!errs_.isEmpty()) {
            String tag_ = ExportCst.anchorNameErr(_cond.getVariableNameOffset(),StringUtil.join(errs_,ExportCst.JOIN_ERR));
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            return;
        }
        String tag_ = ExportCst.anchorName(_cond.getVariableNameOffset());
        _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset()));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
    }
    private static void processAbstractCatchEvalReport(VariablesOffsets _vars, AbstractCatchEval _cond, CustList<PartOffset> _parts, Coverage _cov) {
        String tag_;
        if (_cov.getCatches(_cond)) {
            tag_ = ExportCst.span(FULL);
        } else {
            tag_ = ExportCst.span(NONE);
        }
        int off_ = _cond.getOffset();
        _parts.add(new PartOffset(tag_,off_));
        _parts.add(new PartOffset(ExportCst.END_SPAN,off_+ _vars.getKeyWords().getKeyWordCatch().length()));
    }
    private static void processDeclareVariableReport(VariablesOffsets _vars, DeclareVariable _cond, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(_cond.getClassName().trim(), keyWordVar_)) {
            String tag_ = ExportCst.bold(_cond.getImportedClassName());
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset()));
            _parts.add(new PartOffset(ExportCst.END_BOLD, _cond.getClassNameOffset() + keyWordVar_.length()));
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
                _parts.add(new PartOffset(ExportCst.bold(_importedClassName), _classNameOffset));
                tag_ = ExportCst.END_BOLD;
            } else {
                _parts.add(new PartOffset(ExportCst.anchorErr(_errInf), _classNameOffset));
                tag_ = ExportCst.END_ANCHOR;
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
        LinkageStackElementIn in_ = buildLinkageRep(_cond, blOffset_, endBl_, 0, -1, 0);
        buildNormalReport(_vars, _parts, _cov, root_, in_);
    }
    private static void processLineError(VariablesOffsets _vars, Line _cond, CustList<PartOffset> _parts) {
        int blOffset_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormErr(_vars, _cond, _parts, -1, root_, blOffset_, 0);
    }
    private static void processBreakBlockReport(BreakBlock _cond, CustList<PartOffset> _parts) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        _parts.add(new PartOffset(ExportCst.anchorRef(_cond.getLabelOffsetRef()), _cond.getLabelOffset()));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processBreakBlockError(BreakBlock _cond, CustList<PartOffset> _parts) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        StringList errs_ = _cond.getErrorsRefLabels();
        if (!errs_.isEmpty()) {
            _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), _cond.getLabelOffset()));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
            return;
        }
        _parts.add(new PartOffset(ExportCst.anchorRef(_cond.getLabelOffsetRef()), _cond.getLabelOffset()));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processContinueBlockReport(ContinueBlock _cond, CustList<PartOffset> _parts) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        _parts.add(new PartOffset(ExportCst.anchorRef(_cond.getLabelOffsetRef()), _cond.getLabelOffset()));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processContinueBlockError(ContinueBlock _cond, CustList<PartOffset> _parts) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        StringList errs_ = _cond.getErrorsRefLabels();
        if (!errs_.isEmpty()) {
            _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), _cond.getLabelOffset()));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
            return;
        }
        _parts.add(new PartOffset(ExportCst.anchorRef(_cond.getLabelOffsetRef()), _cond.getLabelOffset()));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processReturnMethodReport(VariablesOffsets _vars, ReturnMethod _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_cond.isEmpty()) {
            return;
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1, 0);
        buildNormalReport(_vars, _parts, _cov, root_, in_);
    }

    private static void processReturnMethodError(VariablesOffsets _vars, ReturnMethod _cond, CustList<PartOffset> _parts) {
        if (_cond.isEmpty()) {
            return;
        }
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormErr(_vars, _cond, _parts, -1, root_, off_, 0);
    }
    private static void processThrowingReport(VariablesOffsets _vars, Throwing _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1, 0);
        buildNormalReport(_vars, _parts, _cov, root_, in_);
    }

    private static void processThrowingError(VariablesOffsets _vars, Throwing _cond, CustList<PartOffset> _parts) {
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormErr(_vars, _cond, _parts, -1, root_, off_, 0);
    }
    private static void processForIterativeLoopReport(VariablesOffsets _vars, ForIterativeLoop _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoverLoops(_cond);
            String tag_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _parts.add(new PartOffset(tag_,off_));
            _parts.add(new PartOffset(ExportCst.END_SPAN,off_+ _vars.getKeyWords().getKeyWordIter().length()));
            _parts.add(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffset()), _cond.getVariableNameOffset()));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        int off_ = _cond.getInitOffset();
        int offsetEndBlock_ = off_ + _cond.getInit().length();
        if (_vars.getLastStackElt().getIndexLoop() == 0) {
            OperationNode rootInit_ = _cond.getRootInit();
            LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1, 0);
            buildNormalReport(_vars, _parts, _cov, rootInit_, in_);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexLoop(1);
        }
        off_ = _cond.getExpressionOffset();
        offsetEndBlock_ = off_ + _cond.getExpression().length();
        if (_vars.getLastStackElt().getIndexLoop() == 1) {
            OperationNode rootExp_ = _cond.getRootExp();
            LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 1, -1, 0);
            buildNormalReport(_vars, _parts, _cov, rootExp_, in_);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexLoop(2);
        }
        off_ = _cond.getStepOffset();
        offsetEndBlock_ = off_ + _cond.getStep().length();
        OperationNode rootStep_ = _cond.getRootStep();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 2, -1, 0);
        buildNormalReport(_vars, _parts, _cov, rootStep_, in_);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexLoop(0);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForIterativeLoopError(VariablesOffsets _vars, ForIterativeLoop _cond, CustList<PartOffset> _parts) {
        if (_vars.getLastStackElt().noVisited()) {
            StringList errs_ = _cond.getNameErrors();
            if (!errs_.isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_, ExportCst.JOIN_ERR)), _cond.getVariableNameOffset()));
            } else {
                _parts.add(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffset()), _cond.getVariableNameOffset()));
            }
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        int off_ = _cond.getInitOffset();
        if (_vars.getLastStackElt().getIndexLoop() == 0) {
            OperationNode rootInit_ = _cond.getRootInit();
            buildNormErr(_vars, _cond, _parts, -1, rootInit_, off_, 0);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexLoop(1);
        }
        off_ = _cond.getExpressionOffset();
        if (_vars.getLastStackElt().getIndexLoop() == 1) {
            OperationNode rootExp_ = _cond.getRootExp();
            buildNormErr(_vars, _cond, _parts, -1, rootExp_, off_, 1);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexLoop(2);
        }
        off_ = _cond.getStepOffset();
        OperationNode rootStep_ = _cond.getRootStep();
        buildNormErr(_vars, _cond, _parts, -1, rootStep_, off_, 2);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexLoop(0);
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForEachLoopReport(VariablesOffsets _vars, ForEachLoop _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoverLoops(_cond);
            String tagCov_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _parts.add(new PartOffset(tagCov_, off_));
            appendVars(_vars,_cond, _parts);
            String tag_;
            _parts.add(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffset()), _cond.getVariableNameOffset()));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            tag_ = ExportCst.END_SPAN;
            _parts.add(new PartOffset(tag_, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1, 0);
        buildNormalReport(_vars, _parts, _cov, root_, in_);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForEachLoopError(VariablesOffsets _vars, ForEachLoop _cond, CustList<PartOffset> _parts) {
        if (_vars.getLastStackElt().noVisited()) {
            appendVars(_vars,_cond, _parts);
            StringList errs_ = _cond.getNameErrors();
            if (!errs_.isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), _cond.getVariableNameOffset()));
            } else {
                _parts.add(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffset()), _cond.getVariableNameOffset()));
            }
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            if (!_cond.getSepErrors().isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_cond.getSepErrors(),ExportCst.JOIN_ERR)), _cond.getSepOffset()));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getSepOffset() + 1));
            }
        }
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormErr(_vars, _cond, _parts, -1, root_, off_, 0);
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendVars(VariablesOffsets _vars, ForEachLoop _cond, CustList<PartOffset> _parts) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(_cond.getClassName().trim(), keyWordVar_)) {
            String tag_;
            tag_ = ExportCst.bold(_cond.getImportedClassName());
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffset()));
            _parts.add(new PartOffset(ExportCst.END_BOLD, _cond.getClassNameOffset() + keyWordVar_.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsets());
        }
    }

    private static void processForEachTableReport(VariablesOffsets _vars, ForEachTable _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoverLoops(_cond);
            String tagCov_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _parts.add(new PartOffset(tagCov_, off_));
            KeyWords keyWords_ = _vars.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            appendFirstVar(_cond, _parts, keyWordVar_);
            _parts.add(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffsetFirst()), _cond.getVariableNameOffsetFirst()));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
            appendSecondVar(_cond, _parts, keyWordVar_);
            _parts.add(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffsetSecond()), _cond.getVariableNameOffsetSecond()));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
            _parts.add(new PartOffset(ExportCst.END_SPAN, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1, 0);
        buildNormalReport(_vars, _parts, _cov, root_, in_);
        refLabel(_vars, _parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendSecondVar(ForEachTable _cond, CustList<PartOffset> _parts, String _keyWordVar) {
        if (StringUtil.quickEq(_cond.getClassNameSecond().trim(), _keyWordVar)) {
            String tag_;
            tag_ = ExportCst.bold(_cond.getImportedClassNameSecond());
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetSecond()));
            _parts.add(new PartOffset(ExportCst.END_BOLD, _cond.getClassNameOffsetSecond() + _keyWordVar.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsetsSecond());
        }
    }

    private static void processForEachTableError(VariablesOffsets _vars, ForEachTable _cond, CustList<PartOffset> _parts) {
        if (_vars.getLastStackElt().noVisited()) {
            KeyWords keyWords_ = _vars.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            appendFirstVar(_cond, _parts, keyWordVar_);
            StringList errs_ = _cond.getNameErrorsFirst();
            if (!errs_.isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_, ExportCst.JOIN_ERR)), _cond.getVariableNameOffsetFirst()));
            } else {
                _parts.add(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffsetFirst()), _cond.getVariableNameOffsetFirst()));
            }
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
            appendSecondVar(_cond, _parts, keyWordVar_);
            errs_ = _cond.getNameErrorsSecond();
            if (!errs_.isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_, ExportCst.JOIN_ERR)), _cond.getVariableNameOffsetSecond()));
            } else {
                _parts.add(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffsetSecond()), _cond.getVariableNameOffsetSecond()));
            }
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
            if (!_cond.getSepErrors().isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_cond.getSepErrors(), ExportCst.JOIN_ERR)), _cond.getSepOffset()));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getSepOffset() + 1));
            }
        }
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormErr(_vars, _cond, _parts, -1, root_, off_, 0);
        refLabelError(_vars, _cond,_parts, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendFirstVar(ForEachTable _cond, CustList<PartOffset> _parts, String _keyWordVar) {
        if (StringUtil.quickEq(_cond.getClassNameFirst().trim(), _keyWordVar)) {
            String tag_;
            tag_ = ExportCst.bold(_cond.getImportedClassNameFirst());
            _parts.add(new PartOffset(tag_, _cond.getClassNameOffsetFirst()));
            _parts.add(new PartOffset(ExportCst.END_BOLD, _cond.getClassNameOffsetFirst() + _keyWordVar.length()));
        } else {
            _parts.addAllElts(_cond.getPartOffsetsFirst());
        }
    }

    private static void processElementBlockReport(VariablesOffsets _vars, ElementBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        AbstractInstancingOperation inst_ = (AbstractInstancingOperation) _cond.getRoot().getFirstChild().getNextSibling();
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        String uniqueFieldName_ = _cond.getUniqueFieldName();
        if (k_ == -1) {
            annotReport(_vars, _cond, _parts, _cov, _cond.getAnnotationsIndexes(), _cond.getAnnotations(), _cond.getRoots());
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = 0;
            nameIdReport(_vars, _parts, inst_, uniqueFieldName_, _cond.getFieldNameOffest());
            _parts.addAllElts(_cond.getTypePartOffsets());
        }
        int tr_ = _cond.diffTr(_vars.getKeyWords().getKeyWordNew());
        int blOffset_ = _cond.getValueOffest() + tr_;
        int endBl_ = _cond.getValueOffest() + _cond.getValue().length() + tr_;
        LinkageStackElementIn in_ = buildLinkageRep(_cond, blOffset_, endBl_, 0, k_, uniqueFieldName_.length());
        buildNormalReport(_vars, _parts, _cov, inst_, in_);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void nameIdReport(VariablesOffsets _vars, CustList<PartOffset> _parts, AbstractInstancingOperation _inst, String _uniqueFieldName, int _fieldNameOffest) {
        String fileName_ = _vars.getCurrentFileName();
        AnaTypeFct ctor_ = _inst.getConstructor();
        if (ctor_ != null) {
            StringList list_ = new StringList();
            addParts(_vars, fileName_, ctor_, _fieldNameOffest, _uniqueFieldName.length(), list_, list_, _parts, _fieldNameOffest);
        } else {
            _parts.add(new PartOffset(ExportCst.anchorName(_fieldNameOffest), _fieldNameOffest));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _fieldNameOffest + _uniqueFieldName.length()));
        }
    }

    private static void processElementBlockError(VariablesOffsets _vars, ElementBlock _cond, CustList<PartOffset> _parts) {
        OperationNode firstChild_ = _cond.getRoot().getFirstChild();
        StringList errs_ = new StringList();
        OperationNode next_ = fetchNext(firstChild_, errs_);
        addNextErrs(errs_, next_);
        AbstractInstancingOperation inst_ = asInstancing(next_);
        String uniqueFieldName_ = _cond.getUniqueFieldName();
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            annotError(_vars, _cond, _parts, _cond.getAnnotationsIndexes(), _cond.getRoots());
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = 0;
            StringList mergedErrs_ = new StringList(_cond.getNameErrors());
            if (uniqueFieldName_.trim().isEmpty()) {
                String err_ = getLineErr(mergedErrs_);
                String tag_ = ExportCst.anchorNameErr(_cond.getFieldNameOffest(), err_);
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getFieldNameOffest() + 1));
                _vars.getLastStackElt().setIndexAnnotationGroup(-1);
                return;
            }
            if (inst_ == null) {
                errs_.addAllElts(_cond.getRoot().getErrs());
                mergedErrs_.addAllElts(errs_);
                String err_ = getLineErr(mergedErrs_);
                String tag_ = ExportCst.anchorNameErr(_cond.getFieldNameOffest(), err_);
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getFieldNameOffest() + uniqueFieldName_.trim().length()));
                _vars.getLastStackElt().setIndexAnnotationGroup(-1);
                return;
            }
            mergedErrs_.addAllElts(inst_.getErrs());
            nameIdError(_vars, _parts, inst_, uniqueFieldName_, mergedErrs_, _cond.getFieldNameOffest());
            _parts.addAllElts(_cond.getTypePartOffsets());
        }
        int tr_ = _cond.diffTr(_vars.getKeyWords().getKeyWordNew());
        int begin_ = _cond.getValueOffest() + tr_;
        buildEnumEltError(_vars, _cond, _parts, inst_, k_, begin_);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void buildEnumEltError(VariablesOffsets _vars, AbsBk _cond, CustList<PartOffset> _parts, AbstractInstancingOperation _inst, int _k, int _begin) {
        buildNormalError(_vars, _cond, _parts, _k, _inst, _begin, 0);
    }

    private static AbstractInstancingOperation asInstancing(OperationNode _next) {
        AbstractInstancingOperation inst_ = null;
        if (_next instanceof AbstractInstancingOperation) {
            inst_ = (AbstractInstancingOperation) _next;
        }
        return inst_;
    }

    private static OperationNode fetchNext(OperationNode _firstChild, StringList _errs) {
        OperationNode next_ = null;
        if (_firstChild != null) {
            _errs.addAllElts(_firstChild.getErrs());
            next_ = _firstChild.getNextSibling();
        }
        return next_;
    }

    private static void nameIdError(VariablesOffsets _vars, CustList<PartOffset> _parts, AbstractInstancingOperation _inst, String _uniqueFieldName, StringList _errs, int _fieldNameOffest) {
        String fileName_ = _vars.getCurrentFileName();
        String err_ = getLineErr(_errs);
        AnaTypeFct ctor_ = _inst.getConstructor();
        if (ctor_ != null) {
            addParts(_vars, fileName_, ctor_, _fieldNameOffest, _uniqueFieldName.length(), _errs, _errs, _parts, _fieldNameOffest);
        } else {
            String tag_;
            if (!_errs.isEmpty()) {
                tag_ = ExportCst.anchorNameErr(_fieldNameOffest, err_);
            } else {
                tag_ = ExportCst.anchorName(_fieldNameOffest);
            }
            _parts.add(new PartOffset(tag_, _fieldNameOffest));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _fieldNameOffest + _uniqueFieldName.length()));
        }
    }

    private static void processFieldBlockReport(VariablesOffsets _vars, FieldBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotField(_vars, _cond, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            _parts.addAllElts(_cond.getTypePartOffsets());
        }
        int blOffset_ = _cond.getValueOffset();
        int endBl_ = blOffset_ + _cond.getValue().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, blOffset_, endBl_, 0, k_, 0);
        buildNormalReport(_vars, _parts, _cov, root_, in_);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }
    private static void processFieldBlockError(VariablesOffsets _vars, FieldBlock _cond, CustList<PartOffset> _parts) {
        int blOffset_ = _cond.getValueOffset();
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotFieldErr(_vars, _cond, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            _parts.addAllElts(_cond.getTypePartOffsets());
            StringList errs_ = _cond.getNameRetErrors();
            if (!errs_.isEmpty()) {
                String err_ = StringUtil.join(errs_, ExportCst.JOIN_ERR);
                if (_cond.getValue().trim().isEmpty()) {
                    blOffset_ = _cond.getClassNameOffset() + _cond.getClassName().length();
                }
                _parts.add(new PartOffset(ExportCst.anchorErr(err_), blOffset_));
                int endBl_ = blOffset_ + Math.max(1, _cond.getValue().length());
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, endBl_));
                _vars.getLastStackElt().setIndexAnnotationGroup(-1);
                return;
            }
        }
        OperationNode root_ = _cond.getRoot();
        buildNormErr(_vars, _cond, _parts, k_, root_, blOffset_, 0);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void buildAnnotField(VariablesOffsets _vars, FieldBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexes();
        StringList annotations_ = _cond.getAnnotations();
        CustList<OperationNode> roots_ = _cond.getRoots();
        annotReport(_vars, _cond, _parts, _cov, annotationsIndexes_, annotations_, roots_);
    }
    private static void buildAnnotFieldErr(VariablesOffsets _vars, FieldBlock _cond, CustList<PartOffset> _parts) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexes();
        CustList<OperationNode> roots_ = _cond.getRoots();
        annotError(_vars, _cond, _parts, annotationsIndexes_, roots_);
    }

    private static void annotError(VariablesOffsets _vars, AbsBk _cond, CustList<PartOffset> _parts, Ints _annotationsIndexes, CustList<OperationNode> _roots) {
        int len_ = _annotationsIndexes.size();
        int j_ = _vars.getLastStackElt().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            buildAnnotErr(_vars, _cond, -1, _parts, _annotationsIndexes, _roots, i);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotation(i+1);
        }
        _vars.getLastStackElt().setIndexAnnotation(0);
    }

    private static void processConstructorBlockReport(VariablesOffsets _vars, ConstructorBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsReport(_vars, _cond, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            int begName_ = _cond.getNameOffset();
            _parts.add(new PartOffset(ExportCst.anchorName(begName_),begName_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getLeftPar()));
        }
        refPar(_vars, _cond, _parts, _cov, k_);
    }
    private static void processConstructorBlockError(VariablesOffsets _vars, ConstructorBlock _cond, CustList<PartOffset> _parts) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsError(_vars, _cond, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            int begName_ = _cond.getNameOffset();
            StringList errsName_ = _cond.getNameErrors();
            if (errsName_.isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorName(begName_),begName_));
            } else {
                _parts.add(new PartOffset(ExportCst.anchorNameErr(begName_,StringUtil.join(errsName_,ExportCst.JOIN_ERR)),begName_));
            }
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getLeftPar()));
        }
        refParError(_vars,_cond,_parts,k_);
    }
    private static void processOverridableBlockReport(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsReport(_vars,_cond, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
        }
        int begName_ = _cond.getNameOffset();
        if (!AbsBk.isOverBlock(_cond) || ((NamedCalledFunctionBlock)_cond).getKind() == MethodKind.OPERATOR) {
            if (k_ == -1) {
                addNameParts(_cond, _parts, begName_, _cond.getName().length());
                appendOperImports(_cond, _parts);
                _parts.addAllElts(_cond.getPartOffsetsReturn());
            }
            refParams(_vars,_cond, _parts, _cov);
            return;
        }
        if (k_ == -1) {
            _parts.addAllElts(_cond.getPartOffsetsReturn());
        }
        NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) _cond;
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
        if (_vars.goesToProcess()) {
            return;
        }
        processOverridableRedef(_vars,m_,_parts);
    }

    private static void processAnonymousFctReport(VariablesOffsets _vars, NamedCalledFunctionBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (!_vars.getLastStackElt().isVisitedParams()) {
            refParams(_vars,_cond, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setVisitedParams(true);
        }
        buildAnnotationsReport(_vars,_cond, _parts, _cov);
        if (_vars.goesToProcess()) {
            return;
        }
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        int begName_ = _cond.getNameOffset();
        addNameParts(_cond,_parts, begName_, 2);
    }

    private static void processSwitchMethodReport(VariablesOffsets _vars, SwitchMethodBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getLastStackElt().isAnnotationMode()) {
            int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            if (k_ == -1) {
                buildAnnotationsReport(_vars,_cond, _parts, _cov);
                if (_vars.goesToProcess()) {
                    return;
                }
                _vars.getLastStackElt().setIndexAnnotationGroup(0);
            }
            refParams(_vars,_cond, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setStopVisit(true);
            return;
        }
        _parts.add(new PartOffset(ExportCst.span(TYPE), _cond.getBegin()));
    }


    private static void processOverridableBlockError(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsError(_vars,_cond, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
        }
        int begName_ = _cond.getNameOffset();
        if (!AbsBk.isOverBlock(_cond) || ((NamedCalledFunctionBlock)_cond).getKind() == MethodKind.OPERATOR) {
            if (k_ == -1) {
                addNameParts(_cond, _parts, begName_, _cond.getName().length());
                appendOperImports(_cond, _parts);
                _parts.addAllElts(_cond.getPartOffsetsReturn());
            }
            refParamsError(_vars,_cond, _parts);
            return;
        }
        if (k_ == -1) {
            _parts.addAllElts(_cond.getPartOffsetsReturn());
        }
        NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) _cond;
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
        if (_vars.goesToProcess()) {
            return;
        }
        processOverridableRedef(_vars,m_,_parts);
    }

    private static void appendOperImports(NamedFunctionBlock _cond, CustList<PartOffset> _parts) {
        if (_cond instanceof OperatorBlock) {
            OperatorBlock op_ = (OperatorBlock) _cond;
            int lenImp_ = op_.getImports().size();
            for (int i = 0; i < lenImp_; i++) {
                _parts.add(new PartOffset(ExportCst.span(IMPORT), op_.getImportsOffset().get(i)));
                _parts.add(new PartOffset(ExportCst.END_SPAN, op_.getImportsOffset().get(i) + op_.getImports().get(i).length()));
            }
        }
    }

    private static void processOverridableRedef(VariablesOffsets _vars, NamedCalledFunctionBlock _cond, CustList<PartOffset> _parts) {
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
    private static void processAnonymousFctBlockError(VariablesOffsets _vars, NamedCalledFunctionBlock _cond, CustList<PartOffset> _parts) {
        if (!_vars.getLastStackElt().isVisitedParams()) {
            refParamsError(_vars,_cond, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setVisitedParams(true);
        }
        buildAnnotationsError(_vars,_cond, _parts);
        if (_vars.goesToProcess()) {
            return;
        }
        _parts.addAllElts(_cond.getPartOffsetsReturn());
        StringList errs_ = new StringList(_cond.getErrorsBlock());
        AbsBk child_ = _cond.getFirstChild();
        if (isImplicitReturn(child_)){
            errs_.addAllElts(child_.getErrorsBlock());
        }
        int begName_ = _cond.getNameOffset();
        addNameParts(errs_,_cond,_parts, begName_, 2);
    }
    private static void processSwitchMethodError(VariablesOffsets _vars, SwitchMethodBlock _cond, CustList<PartOffset> _parts) {
        if (_vars.getLastStackElt().isAnnotationMode()) {
            int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            if (k_ == -1) {
                buildAnnotationsError(_vars,_cond, _parts);
                if (_vars.goesToProcess()) {
                    return;
                }
                _vars.getLastStackElt().setIndexAnnotationGroup(0);
            }
            refParamsError(_vars,_cond, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setStopVisit(true);
            return;
        }
        _parts.add(new PartOffset(ExportCst.span(TYPE), _cond.getBegin()));
    }

    private static void processAnnotationMethodBlockReport(VariablesOffsets _vars, NamedCalledFunctionBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsReport(_vars,_cond, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            _parts.addAllElts(_cond.getPartOffsetsReturn());
            int begName_ = _cond.getNameOffset();
            addNameParts(_cond,_parts, begName_, _cond.getName().length());
        }
        OperationNode root_ = _cond.getRoot();
        if (root_ != null) {
            int blOffset_ = _cond.getDefaultValueOffset();
            int endBl_ = blOffset_ + _cond.getDefaultValue().length();
            LinkageStackElementIn in_ = buildLinkageRep(_cond, blOffset_, endBl_, 0, k_, 0);
            buildNormalReport(_vars, _parts, _cov, root_, in_);
            if (_vars.goesToProcess()) {
                return;
            }
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void processAnnotationMethodBlockError(VariablesOffsets _vars, NamedCalledFunctionBlock _cond, CustList<PartOffset> _parts) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsError(_vars,_cond, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
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
            buildNormErr(_vars, _cond, _parts, k_, root_, blOffset_, 0);
            if (_vars.goesToProcess()) {
                return;
            }
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void buildNormErr(VariablesOffsets _vars, AbsBk _cond, CustList<PartOffset> _parts, int _k, OperationNode _root, int _begin, int _indexLoop) {
        buildNormalError(_vars, _cond, _parts, _k, _root, _begin, _indexLoop);
    }

    private static void buildNormalError(VariablesOffsets _vars, AbsBk _cond, CustList<PartOffset> _parts, int _k, OperationNode _root, int _begin, int _indexLoop) {
        LinkageStackElementIn in_ = buildLinkageErr(_cond, _k, 0, _indexLoop, _begin, -1);
        buildErrorReport(_vars, _parts, _root, in_);
    }

    private static void addNameParts(NamedFunctionBlock _named,CustList<PartOffset> _parts, int _begName, int _len) {
        addNameParts(new StringList(),_named,_parts,_begName,_len);
    }
    private static void addNameParts(StringList _list,NamedFunctionBlock _named,CustList<PartOffset> _parts, int _begName, int _len) {
        StringList errs_ = new StringList(_named.getNameErrors());
        errs_.addAllElts(_list);
        if (errs_.isEmpty()) {
            int endName_ = _begName + _len;
            _parts.add(new PartOffset(ExportCst.anchorName(_begName),_begName));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR,endName_));
            return;
        }
        int endName_ = _begName + Math.max(_len,1);
        _parts.add(new PartOffset(ExportCst.anchorNameErr(_begName,StringUtil.join(errs_,ExportCst.JOIN_ERR)),_begName));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR,endName_));
    }
    private static void processInnerElementBlockReport(VariablesOffsets _vars, InnerElementBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        AbstractInstancingOperation inst_ = (AbstractInstancingOperation) _cond.getRoot().getFirstChild().getNextSibling();
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        String uniqueFieldName_ = _cond.getUniqueFieldName();
        if (k_ == -1) {
            processAnnotationReport(_vars, _cond, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            nameIdReport(_vars, _parts, inst_, uniqueFieldName_, _cond.getFieldNameOffest());
            _parts.addAllElts(_cond.getTypePartOffsets());
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = 0;
        }
        int tr_ = _cond.diffTr(_vars.getKeyWords().getKeyWordNew());
        int blOffset_ = _cond.getValueOffest() + tr_;
        int endBl_ = _cond.getValueOffest() + _cond.getValue().length() + tr_;
        LinkageStackElementIn in_ = buildLinkageRep(_cond, blOffset_, endBl_, 0, k_, uniqueFieldName_.length());
        buildNormalReport(_vars, _parts, _cov, inst_, in_);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void processInnerElementBlockError(VariablesOffsets _vars, InnerElementBlock _cond, CustList<PartOffset> _parts) {
        OperationNode firstChild_ = _cond.getRoot().getFirstChild();
        StringList errs_ = new StringList();
        OperationNode next_ = fetchNext(firstChild_, errs_);
        addNextErrs(errs_, next_);
        AbstractInstancingOperation inst_ = asInstancing(next_);
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        String uniqueFieldName_ = _cond.getUniqueFieldName();
        if (k_ == -1) {
            processAnnotationError(_vars, _cond, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            StringList mergedErrs_ = new StringList(_cond.getNameErrors());
            if (uniqueFieldName_.trim().isEmpty()) {
                String err_ = getLineErr(mergedErrs_);
                String tag_ = ExportCst.anchorNameErr(_cond.getFieldNameOffest(), err_);
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getFieldNameOffest() + 1));
                return;
            }
            if (inst_ == null) {
                errs_.addAllElts(_cond.getRoot().getErrs());
                mergedErrs_.addAllElts(errs_);
                String err_ = getLineErr(mergedErrs_);
                String tag_ = ExportCst.anchorNameErr(_cond.getFieldNameOffest(), err_);
                _parts.add(new PartOffset(tag_, _cond.getFieldNameOffest()));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getFieldNameOffest() + uniqueFieldName_.trim().length()));
                return;
            }
            mergedErrs_.addAllElts(inst_.getErrs());
            nameIdError(_vars, _parts, inst_, uniqueFieldName_, mergedErrs_, _cond.getFieldNameOffest());
            _parts.addAllElts(_cond.getTypePartOffsets());
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = 0;
        }
        int tr_ = _cond.diffTr(_vars.getKeyWords().getKeyWordNew());
        int begin_ = _cond.getValueOffest() + tr_;
        buildEnumEltError(_vars, _cond, _parts, inst_, k_, begin_);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void addNextErrs(StringList _errs, OperationNode _next) {
        if (_next != null) {
            _errs.addAllElts(_next.getErrs());
        }
    }

    private static String getLineErr(StringList _list) {
        return StringUtil.join(_list,ExportCst.JOIN_ERR);
    }

    private static void processRootBlockReport(VariablesOffsets _vars, RootBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        if (_vars.getLastStackElt().isAnnotationMode()) {
            processAnnotationReport(_vars,_cond, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setStopVisit(true);
            _parts.addAllElts(_vars.getLastStackElt().getPartsAfter());
            return;
        }
        if (_cond instanceof AnonymousTypeBlock) {
            _parts.add(new PartOffset(ExportCst.span(TYPE), _cond.getBegin()));
        } else {
            processAnnotationReport(_vars, _cond, _parts, _cov);
        }
        if (_vars.goesToProcess()) {
            return;
        }
        appendImportPart(_cond, _parts);
        processInterfaceInit(_cond, _parts);
        int idRowCol_ = _cond.getIdRowCol();
        _parts.add(new PartOffset(ExportCst.anchorName(idRowCol_), idRowCol_));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR, idRowCol_ + _cond.getNameLength()));
        appendInhHeaders(_cond, _parts);
    }

    private static void appendInhHeaders(RootBlock _cond, CustList<PartOffset> _parts) {
        for (PartOffset p : _cond.getConstraintsParts()) {
            _parts.add(p);
        }

        for (PartOffset p : _cond.getSuperTypesParts()) {
            _parts.add(p);
        }
    }

    private static void appendImportPart(RootBlock _cond, CustList<PartOffset> _parts) {
        int len_ = _cond.getImports().size();
        for (int i = 0; i < len_; i++) {
            _parts.add(new PartOffset(ExportCst.span(IMPORT), _cond.getImportsOffset().get(i)));
            _parts.add(new PartOffset(ExportCst.END_SPAN, _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
    }

    private static void processRootBlockError(VariablesOffsets _vars, RootBlock _cond, CustList<PartOffset> _parts) {
        if (_vars.getLastStackElt().isAnnotationMode()) {
            processAnnotationError(_vars,_cond, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setStopVisit(true);
            _parts.addAllElts(_vars.getLastStackElt().getPartsAfter());
            return;
        }
        if (_cond instanceof AnonymousTypeBlock) {
            _parts.add(new PartOffset(ExportCst.span(TYPE), _cond.getBegin()));
        } else {
            processAnnotationError(_vars, _cond, _parts);
        }
        if (_vars.goesToProcess()) {
            return;
        }
        if (!(_cond instanceof RootErrorBlock)) {
            processRootHeaderError(_cond, _parts);
        }
        appendImportPart(_cond, _parts);
        processInterfaceInit(_cond, _parts);
        int nameLength_ = _cond.getNameLength();
        if (nameLength_ > 0) {
            StringList list_ = _cond.getNameErrors();
            int idRowCol_ = _cond.getIdRowCol();
            if (!list_.isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorNameErr(idRowCol_,StringUtil.join(list_,ExportCst.JOIN_ERR)), idRowCol_));
            } else {
                _parts.add(new PartOffset(ExportCst.anchorName(idRowCol_), idRowCol_));
            }
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, idRowCol_ + nameLength_));
        }
        appendInhHeaders(_cond, _parts);
        if ((_cond instanceof RootErrorBlock)) {
            processRootHeaderError(_cond, _parts);
        }
    }

    private static void processRootHeaderError(RootBlock _cond, CustList<PartOffset> _parts) {
        if (!_cond.getErrorsBlock().isEmpty()) {
            StringList listCat_ = _cond.getErrorsBlock();
            _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(listCat_, ExportCst.JOIN_ERR)), _cond.getBegin()));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, _cond.getBegin() + _cond.getLengthHeader()));
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
            _parts.add(new PartOffset(ExportCst.span(IMPORT), _cond.getImportsOffset().get(i)));
            _parts.add(new PartOffset(ExportCst.END_SPAN, _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
    }
    private static void processAnnotationReport(VariablesOffsets _vars, RootBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexes();
        StringList annotations_ = _cond.getAnnotations();
        CustList<OperationNode> roots_ = _cond.getRoots();
        annotReport(_vars, _cond, _parts, _cov, annotationsIndexes_, annotations_, roots_);
    }

    private static void annotReport(VariablesOffsets _vars, AbsBk _cond, CustList<PartOffset> _parts, Coverage _cov, Ints _annotationsIndexes, StringList _annotations, CustList<OperationNode> _roots) {
        int len_ = _annotationsIndexes.size();
        int j_ = _vars.getLastStackElt().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            LinkageStackElementIn in_ = buildAnnotLinkageReport(_cond, -1, _annotationsIndexes, _annotations, i);
            buildAnnotReport(_vars, _parts, _cov, _roots, i, in_);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotation(i + 1);
        }
        _vars.getLastStackElt().setIndexAnnotation(0);
    }

    private static void buildAnnotationsReport(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexes();
        StringList annotations_ = _cond.getAnnotations();
        CustList<OperationNode> roots_ = _cond.getRoots();
        annotReport(_vars, _cond, _parts, _cov, annotationsIndexes_, annotations_, roots_);
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars, NamedFunctionBlock _cond, int _index, CustList<PartOffset> _parts, Coverage _cov) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexesParams().get(_index);
        StringList annotations_ = _cond.getAnnotationsParams().get(_index);
        CustList<OperationNode> roots_ = _cond.getRootsList().get(_index);
        annotMethReport(_vars, _cond, _index, _parts, _cov, annotationsIndexes_, annotations_, roots_);
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars, SwitchMethodBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexes();
        StringList annotations_ = _cond.getAnnotations();
        CustList<OperationNode> roots_ = _cond.getRoots();
        annotReport(_vars, _cond, _parts, _cov, annotationsIndexes_, annotations_, roots_);
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars, SwitchMethodBlock _cond, int _index, CustList<PartOffset> _parts, Coverage _cov) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexesParams().get(_index);
        StringList annotations_ = _cond.getAnnotationsParams().get(_index);
        CustList<OperationNode> roots_ = _cond.getRootsList().get(_index);
        annotMethReport(_vars, _cond, _index, _parts, _cov, annotationsIndexes_, annotations_, roots_);
    }

    private static void annotMethReport(VariablesOffsets _vars, AbsBk _cond, int _index, CustList<PartOffset> _parts, Coverage _cov, Ints _annotationsIndexes, StringList _annotations, CustList<OperationNode> _roots) {
        int len_ = _annotationsIndexes.size();
        int j_ = _vars.getLastStackElt().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            LinkageStackElementIn in_ = buildAnnotLinkageReport(_cond, _index, _annotationsIndexes, _annotations, i);
            buildAnnotReport(_vars, _parts, _cov, _roots, i, in_);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotation(i + 1);
        }
        _vars.getLastStackElt().setIndexAnnotation(0);
        _vars.getLastStackElt().setIndexAnnotationGroup(_index + 1);
    }

    private static void buildAnnotReport(VariablesOffsets _vars, CustList<PartOffset> _parts, Coverage _cov, CustList<OperationNode> _roots, int _i, LinkageStackElementIn _in) {
        OperationNode root_ = _roots.get(_i);
        buildCoverageReport(_vars, _parts, _cov, root_, _in);
    }

    private static LinkageStackElementIn buildAnnotLinkageReport(AbsBk _cond, int _indexAnnotationGroup, Ints _annotationsIndexes, StringList _annotations, int _indexAnnotation) {
        int begin_ = _annotationsIndexes.get(_indexAnnotation);
        int end_ = begin_ + _annotations.get(_indexAnnotation).trim().length();
        LinkageStackElementIn in_ = new LinkageStackElementIn(_cond, 0, _indexAnnotationGroup, _indexAnnotation,_indexAnnotation);
        adjust(begin_, end_, 0, in_);
        return in_;
    }

    private static void processAnnotationError(VariablesOffsets _vars, RootBlock _cond, CustList<PartOffset> _parts) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexes();
        CustList<OperationNode> roots_ = _cond.getRoots();
        annotError(_vars, _cond, _parts, annotationsIndexes_, roots_);
    }
    private static void buildAnnotationsError(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexes();
        CustList<OperationNode> roots_ = _cond.getRoots();
        annotError(_vars, _cond, _parts, annotationsIndexes_, roots_);
    }
    private static void buildAnnotationsError(VariablesOffsets _vars, NamedFunctionBlock _cond, int _index, CustList<PartOffset> _parts) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexesParams().get(_index);
        CustList<OperationNode> roots_ = _cond.getRootsList().get(_index);
        annotMethError(_vars, _cond, _index, _parts, annotationsIndexes_, roots_);
    }

    private static void annotMethError(VariablesOffsets _vars, AbsBk _cond, int _index, CustList<PartOffset> _parts, Ints _annotationsIndexes, CustList<OperationNode> _roots) {
        int len_ = _annotationsIndexes.size();
        int j_ = _vars.getLastStackElt().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            buildAnnotErr(_vars, _cond, _index, _parts, _annotationsIndexes, _roots, i);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotation(i + 1);
        }
        _vars.getLastStackElt().setIndexAnnotation(0);
        _vars.getLastStackElt().setIndexAnnotationGroup(_index + 1);
    }

    private static void buildAnnotErr(VariablesOffsets _vars, AbsBk _cond, int _indexAnnotationGroup, CustList<PartOffset> _parts, Ints _annotationsIndexes, CustList<OperationNode> _roots, int _indexAnnotation) {
        int begin_ = _annotationsIndexes.get(_indexAnnotation);
        OperationNode root_ = _roots.get(_indexAnnotation);
        LinkageStackElementIn in_ = buildLinkageErr(_cond, _indexAnnotationGroup, _indexAnnotation, 0, begin_, _indexAnnotation);
        buildErrorReport(_vars, _parts, root_, in_);
    }

    private static void buildAnnotationsError(VariablesOffsets _vars, SwitchMethodBlock _cond, CustList<PartOffset> _parts) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexes();
        CustList<OperationNode> roots_ = _cond.getRoots();
        annotError(_vars, _cond, _parts, annotationsIndexes_, roots_);
    }
    private static void buildAnnotationsError(VariablesOffsets _vars, SwitchMethodBlock _cond, int _index, CustList<PartOffset> _parts) {
        Ints annotationsIndexes_ = _cond.getAnnotationsIndexesParams().get(_index);
        CustList<OperationNode> roots_ = _cond.getRootsList().get(_index);
        annotMethError(_vars, _cond, _index, _parts, annotationsIndexes_, roots_);
    }
    private static void refParams(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        refPar(_vars, _cond, _parts, _cov, k_);
    }

    private static void refPar(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts, Coverage _cov, int _k) {
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = _k; i < len_; i++) {
            buildAnnotationsReport(_vars,_cond,i, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(i+1);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            _parts.add(new PartOffset(ExportCst.anchorName(off_),off_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR,off_+param_.length()));
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }
    private static void refParams(VariablesOffsets _vars, SwitchMethodBlock _cond, CustList<PartOffset> _parts, Coverage _cov) {
        int len_ = 1;
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        for (int i = k_; i < len_; i++) {
            buildAnnotationsReport(_vars,_cond,i, _parts, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(i+1);
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void refParamsError(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        refParError(_vars, _cond, _parts, k_);
    }

    private static void refParError(VariablesOffsets _vars, NamedFunctionBlock _cond, CustList<PartOffset> _parts, int _k) {
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = _k; i < len_; i++) {
            buildAnnotationsError(_vars,_cond,i, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(i+1);
            _parts.addAllElts(_cond.getPartOffsetsParams().get(i));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            StringList errs_ = _cond.getParamErrors().get(i);
            if (errs_.isEmpty()) {
                StringList warns_ = _cond.getParamWarns().get(i);
                if (!warns_.isEmpty()) {
                    _parts.add(new PartOffset(ExportCst.anchorNameWar(off_,StringUtil.join(warns_,ExportCst.JOIN_ERR)),off_));
                } else {
                    _parts.add(new PartOffset(ExportCst.anchorName(off_),off_));
                }
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,off_+param_.length()));
            } else {
                _parts.add(new PartOffset(ExportCst.anchorNameErr(off_,StringUtil.join(errs_,ExportCst.JOIN_ERR)),off_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,off_+Math.max(1,param_.length())));
            }
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void refParamsError(VariablesOffsets _vars, SwitchMethodBlock _cond, CustList<PartOffset> _parts) {
        int len_ = 1;
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        for (int i = k_; i < len_; i++) {
            buildAnnotationsError(_vars,_cond,i, _parts);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(i+1);
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void processConditionReport(ConditionBlock _cond, VariablesOffsets _vars, CustList<PartOffset> _parts, Coverage _cov) {
        int off_ =  _cond.getConditionOffset();
        int offsetEndBlock_ = off_ + _cond.getCondition().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1, 0);
        buildNormalReport(_vars, _parts, _cov, root_, in_);
    }

    private static LinkageStackElementIn buildLinkageRep(AbsBk _cond, int _begin, int _end, int _indexLoop, int _indexAnnotationGroup, int _length) {
        LinkageStackElementIn in_ = new LinkageStackElementIn(_cond, _indexLoop, _indexAnnotationGroup, 0,-1);
        adjust(_begin, _end, _length, in_);
        return in_;
    }

    private static void buildNormalReport(VariablesOffsets _vars, CustList<PartOffset> _parts, Coverage _cov, OperationNode _root, LinkageStackElementIn _in) {
        buildCoverageReport(_vars, _parts, _cov, _root, _in);
    }

    private static void processConditionError(ConditionBlock _cond, VariablesOffsets _vars, CustList<PartOffset> _parts) {
        int off_ =  _cond.getConditionOffset();
        if (_vars.getLastStackElt().noVisited()) {
            if (!_cond.getErr().isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorErr(_cond.getErr()), off_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, off_ + 1));
            }
        }
        OperationNode root_ = _cond.getRoot();
        buildNormErr(_vars, _cond, _parts, -1, root_, off_, 0);
    }

    private static void adjust(int _begin, int _end, int _length, LinkageStackElementIn _in) {
        _in.offsets(_begin, _end, _length);
    }

    private static LinkageStackElementIn buildLinkageErr(AbsBk _cond, int _indexAnnotationGroup, int _indexAnnotation, int _indexLoop, int _begin, int _indexAnnotationLook) {
        LinkageStackElementIn in_ = new LinkageStackElementIn(_cond, _indexLoop, _indexAnnotationGroup, _indexAnnotation, _indexAnnotationLook);
        in_.offsets(_begin);
        return in_;
    }

    private static OperationNode getCurrent(VariablesOffsets _vars,OperationNode _root) {
        OperationNode current_ = _vars.getLastStackElt().getCurrent();
        if (current_ == null) {
            return _root;
        }
        return current_;
    }

    private static void buildCoverageReport(VariablesOffsets _vars, CustList<PartOffset> _parts, Coverage _cov, OperationNode _root, LinkageStackElementIn _in) {
        OperationNode current_ = getCurrent(_vars, _root);
        int sum_ = _in.getBeginBlock();
        int fieldLength_ = _in.getFieldLength();
        String currentFileName_ = _vars.getCurrentFileName();
        AbsBk bl_ = _in.getBlock();
        int indexAnnotationGroup_ = _in.getIndexAnnotationGroup();
        boolean addCover_ = !(bl_ instanceof CaseCondition);
        OperationNode val_ = current_;
        while (true) {
            if (!_vars.getVisited().containsObj(val_)) {
                AbstractCoverageResult result_ = getCovers(_in,bl_, val_, _cov, indexAnnotationGroup_);
                if (!_vars.getVisitedAnnotations().containsObj(val_)) {
                    getBeginOpReport(_in,bl_, _parts, fieldLength_, _root, val_, sum_, addCover_, result_, _cov, indexAnnotationGroup_);
                    leftReport(_vars, bl_,sum_,val_, _cov,result_, _parts, currentFileName_);
                }
                OperationNode visitRep_ = visit(_vars, val_, _parts, sum_, _in);
                if (_vars.goesToProcess()) {
                    return;
                }
                if (visitRep_ != null) {
                    val_ = visitRep_;
                    continue;
                }
            }
            OperationNode nextRep_ = loopReport(val_, _vars, _parts, _cov, _root, _in);
            if (nextRep_ == null) {
                return;
            }
            val_ = nextRep_;
        }
    }

    private static OperationNode loopReport(OperationNode _val, VariablesOffsets _vars, CustList<PartOffset> _parts, Coverage _cov, OperationNode _root, LinkageStackElementIn _in) {
        int sum_ = _in.getBeginBlock();
        int endBlock_ = _in.getEndBlock();
        int fieldLength_ = _in.getFieldLength();
        String currentFileName_ = _vars.getCurrentFileName();
        AbsBk bl_ = _in.getBlock();
        int indexAnnotationGroup_ = _in.getIndexAnnotationGroup();
        boolean addCover_ = !(bl_ instanceof CaseCondition);
        OperationNode val_ = _val;
        while (true) {
            MethodOperation parent_ = val_.getParent();
            OperationNode nextSiblingOp_ = val_.getNextSibling();
            if (parent_ != null && nextSiblingOp_ != null) {
                int offsetEnd_ = getOffsetEnd(sum_, val_, parent_);
                processImplicit(_vars,currentFileName_,offsetEnd_,val_, parent_, _parts);
                String tag_ = getEndTag(addCover_, val_, _root, fieldLength_);
                _parts.add(new PartOffset(tag_,offsetEnd_));
                processUnaryRightOperations(_vars, currentFileName_, sum_, offsetEnd_, val_,parent_, _parts);
                middleReport(_in,_vars,currentFileName_,bl_, offsetEnd_,val_,nextSiblingOp_,
                        parent_, _parts, _cov, indexAnnotationGroup_);
                val_=nextSiblingOp_;
                break;
            }
            if (parent_ == null) {
                getEnd(_parts, fieldLength_, addCover_, endBlock_);
                _vars.getLastStackElt().setNullCurrent();
                return null;
            }
            int offsetEnd_ = getOffsetEnd(sum_, val_, parent_);
            processImplicit(_vars,currentFileName_,offsetEnd_,val_, parent_, _parts);
            String tag_ = getEndTag(addCover_, val_, _root, fieldLength_);
            _parts.add(new PartOffset(tag_,offsetEnd_));
            processUnaryRightOperations(_vars, currentFileName_, sum_, offsetEnd_, val_,parent_, _parts);
            int st_ = end(_vars, parent_, currentFileName_, offsetEnd_, _parts, _root,_in);
            if (st_ > 0) {
                if (st_ == 1) {
                    getEnd(_parts, fieldLength_, addCover_, endBlock_);
                    _vars.getLastStackElt().setNullCurrent();
                }
                return null;
            }
            val_ = parent_;
        }
        return val_;
    }
    private static void setAnonState(VariablesOffsets _vars, NamedCalledFunctionBlock _block) {
        LinkageStackElement state_ = new LinkageStackElement(_block.getIndexEnd());
        state_.setBlock(_block);
        state_.setIndexAnnotationGroup(0);
        _vars.setState(state_);
    }

    private static void setInnerState(VariablesOffsets _vars, OperationNode _val) {
        _vars.setState(processInner(_val));
    }

    private static void setAnnotState(VariablesOffsets _vars, OperationNode _val) {
        _vars.setState(prepareAnnot(_val));
    }

    private static LinkageStackElement prepareAnnot(OperationNode _val) {
        if (_val instanceof AnonymousInstancingOperation) {
            AnonymousInstancingOperation val_ = (AnonymousInstancingOperation) _val;
            AnonymousTypeBlock block_ = val_.getBlock();
            LinkageStackElement state_ = new LinkageStackElement(true, block_.getIndexEnd());
            state_.setBlock(block_);
            state_.getPartsAfter().addAllElts(val_.getBlock().getPartsStaticInitInterfacesOffset());
            state_.getPartsAfter().addAllElts(val_.getPartOffsets());
            return state_;
        }
        SwitchOperation val_ = (SwitchOperation) _val;
        SwitchMethodBlock block_ = val_.getSwitchMethod();
        LinkageStackElement state_ = new LinkageStackElement(true, block_.getIndexEnd());
        state_.setBlock(block_);
        return state_;
    }

    private static boolean isInner(OperationNode _val) {
        return _val instanceof AnonymousInstancingOperation || _val instanceof SwitchOperation;
    }

    private static LinkageStackElement processInner(OperationNode _val) {
        if (_val instanceof AnonymousInstancingOperation) {
            AnonymousTypeBlock block_ = ((AnonymousInstancingOperation) _val).getBlock();
            LinkageStackElement state_ = new LinkageStackElement(block_.getIndexEnd());
            state_.setBlock(block_);
            return state_;
        }
        SwitchMethodBlock block_ = ((SwitchOperation) _val).getSwitchMethod();
        LinkageStackElement state_ = new LinkageStackElement(block_.getIndexEnd());
        state_.setBlock(block_);
        return state_;
    }

    private static void buildErrorReport(VariablesOffsets _vars, CustList<PartOffset> _parts, OperationNode _root, LinkageStackElementIn _in) {
        OperationNode current_ = getCurrent(_vars, _root);
        if (current_ == null) {
            return;
        }
        AbsBk bl_ = _in.getBlock();
        int sum_ = _in.getBeginBlock();
        String currentFileName_ = _vars.getCurrentFileName();
        OperationNode val_ = current_;
        while (true) {
            if (!_vars.getVisited().containsObj(val_)) {
                if (!_vars.getVisitedAnnotations().containsObj(val_)) {
                    leftError(_vars, bl_,sum_,val_, _parts, currentFileName_);
                }
                OperationNode visitErr_ = visit(_vars, val_, _parts, sum_, _in);
                if (_vars.goesToProcess()) {
                    return;
                }
                if (visitErr_ != null) {
                    val_ = visitErr_;
                    continue;
                }
            }
            OperationNode nextErr_ = loopError(val_, _vars, _parts, _root, _in);
            if (nextErr_ == null) {
                return;
            }
            val_ = nextErr_;
        }
    }

    private static OperationNode loopError(OperationNode _val, VariablesOffsets _vars, CustList<PartOffset> _parts, OperationNode _root, LinkageStackElementIn _in) {
        int sum_ = _in.getBeginBlock();
        String currentFileName_ = _vars.getCurrentFileName();
        OperationNode val_ = _val;
        while (true) {
            MethodOperation parent_ = val_.getParent();
            OperationNode nextSiblingOp_ = val_.getNextSibling();
            if (parent_ != null && nextSiblingOp_ != null) {
                int offsetEnd_ = getOffsetEnd(sum_, val_, parent_);
                processImplicit(_vars,currentFileName_,offsetEnd_,val_, parent_,_parts);
                processUnaryRightOperations(_vars, currentFileName_, sum_, offsetEnd_, val_,parent_, _parts);
                middleError(_vars,currentFileName_, offsetEnd_,val_,nextSiblingOp_,
                        parent_,_parts);
                val_=nextSiblingOp_;
                break;
            }
            if (parent_ == null) {
                _vars.getLastStackElt().setNullCurrent();
                return null;
            }
            int offsetEnd_ = getOffsetEnd(sum_, val_, parent_);
            processImplicit(_vars,currentFileName_,offsetEnd_,val_, parent_,_parts);
            processUnaryRightOperations(_vars, currentFileName_, sum_, offsetEnd_, val_,parent_, _parts);
            int st_ = end(_vars, parent_, currentFileName_, offsetEnd_, _parts, _root,_in);
            if (st_ > 0) {
                if (st_ == 1) {
                    _vars.getLastStackElt().setNullCurrent();
                }
                return null;
            }
            val_ = parent_;
        }
        return val_;
    }
    private static OperationNode visit(VariablesOffsets _vars, OperationNode _val, CustList<PartOffset> _parts, int _sum, LinkageStackElementIn _in) {
        if (_val instanceof AnonymousLambdaOperation) {
            NamedCalledFunctionBlock block_ = ((AnonymousLambdaOperation) _val).getBlock();
            setAnonState(_vars, block_);
            int begin_ = _sum + _val.getIndexInEl();
            _parts.add(new PartOffset(ExportCst.span(TYPE), begin_));
            _vars.getLastStackElt().element(_val, _in);
            _vars.getVisited().add(_val);
            return null;
        }
        if (isInner(_val) && !_vars.getVisitedAnnotations().containsObj(_val)) {
            setAnnotState(_vars, _val);
            _vars.getLastStackElt().element(_val, _in);
            _vars.getVisitedAnnotations().add(_val);
            return null;
        }
        OperationNode firstChildOp_ = _val.getFirstChild();
        if (firstChildOp_ != null) {
            return firstChildOp_;
        }
        if (isInner(_val)) {
            setInnerState(_vars, _val);
            _vars.getLastStackElt().element(_val, _in);
            _vars.getVisited().add(_val);
        }
        return null;
    }
    private static int end(VariablesOffsets _vars, MethodOperation _parent, String _currentFileName, int _offsetEnd, CustList<PartOffset> _parts, OperationNode _r, LinkageStackElementIn _in) {
        if (isInner(_parent)) {
            setInnerState(_vars, _parent);
            _vars.getLastStackElt().element(_parent, _in);
            _vars.getVisited().add(_parent);
            return 2;
        }
        right(_vars,_currentFileName, _offsetEnd, _parent,_parts);
        if (_parent == _r) {
            return 1;
        }
        return 0;
    }
    private static void getBeginOpReport(LinkageStackElementIn _in, AbsBk _block, CustList<PartOffset> _parts, int _fieldName, OperationNode _root, OperationNode _curOp, int _sum, boolean _addCover, AbstractCoverageResult _result, Coverage _cov, int _indexAnnotGroup) {
        if (addTag(_fieldName, _root, _curOp, _addCover)) {
            String tag_ = getBeginReport(_in,_block, _root, _curOp, _result, _cov, _indexAnnotGroup);
            _parts.add(new PartOffset(tag_,_sum + _curOp.getIndexInEl()));
        }
    }

    private static void getEnd(CustList<PartOffset> _parts, int _fieldName, boolean _addCover, int _offset) {
        if (_addCover && _fieldName == 0) {
            _parts.add(new PartOffset(ExportCst.END_SPAN, _offset));
        }
    }

    private static String getEndTag(boolean _addCover, OperationNode _val, OperationNode _root, int _fieldName) {
        String tag_;
        if (addTag(_fieldName,_root,_val,_addCover)) {
            tag_ = ExportCst.END_SPAN;
        } else {
            tag_ = EMPTY;
        }
        return tag_;
    }

    private static boolean addTag(int _fieldName, OperationNode _root, OperationNode _curOp, boolean _addCover) {
        return (_curOp != _root || _fieldName==0)&&_addCover;
    }

    private static int getOffsetEnd(int _sum, OperationNode _val, MethodOperation _parent) {
        int indexChild_ = _val.getIndexChild();
        StrTypes children_ = _parent.getChildren();
        return _sum + _val.getIndexInEl() + children_.getValue(indexChild_).length();
    }

    private static String getBeginReport(LinkageStackElementIn _in, AbsBk _block, OperationNode _root, OperationNode _val, AbstractCoverageResult _result, Coverage _cov, int _indexAnnotGroup) {
        String tag_;
        String full_;
        String fullInit_;
        String partial_;
        String partialInit_;
        String none_;
        if (_in.getIndexAnnotationLook() >= 0 || AbsBk.isAnnotBlock(_block)) {
            full_ = FULL_2;
            fullInit_ = FULL_INIT_2;
            partial_ = PARTIAL_2;
            partialInit_ = PARTIAL_INIT_2;
            none_ = NONE_2;
        } else {
            full_ = FULL;
            fullInit_ = FULL_INIT;
            partial_ = PARTIAL;
            partialInit_ = PARTIAL_INIT;
            none_ = NONE;
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
                AbstractCoverageResult resultPar_ = getCovers(_in,_block, par_, _cov, _indexAnnotGroup);
                if (resultPar_.isPartialCovered()) {
                    tag_ = getFullInitReport(resultPar_, fullInit_, full_);
                    return tag_;
                }
                tag_ = ExportCst.span(none_);
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
                        if (StringUtil.quickEq(p_.getOper(),AbsBk.AND_LOG_EQ)
                                || StringUtil.quickEq(p_.getOper(),AbsBk.AND_LOG_EQ_SHORT)
                                || StringUtil.quickEq(p_.getOper(),AbsBk.OR_LOG_EQ)
                                || StringUtil.quickEq(p_.getOper(),AbsBk.OR_LOG_EQ_SHORT)
                                || StringUtil.quickEq(p_.getOper(),AbsBk.NULL_EQ)
                                || StringUtil.quickEq(p_.getOper(),AbsBk.NULL_EQ_SHORT)) {
                            par_ = before_;
                        }
                    }
                    AbstractCoverageResult resultPar_ = getCovers(_in,_block, par_, _cov, _indexAnnotGroup);
                    if (resultPar_.isPartialCovered()) {
                        tag_ = getFullInitReport(resultPar_, fullInit_, full_);
                        return tag_;
                    }
                    tag_ = ExportCst.span(none_);
                    return tag_;
                }
                if (parentBefore_ instanceof OrOperation) {
                    if (BooleanStruct.isTrue(Argument.getNullableValue(firstArg_).getStruct())) {
                        tag_ = ExportCst.span(none_);
                        return tag_;
                    }
                }
                if (parentBefore_ instanceof AndOperation) {
                    if (BooleanStruct.isFalse(Argument.getNullableValue(firstArg_).getStruct())) {
                        tag_ = ExportCst.span(none_);
                        return tag_;
                    }
                }
                if (parentBefore_ instanceof NullSafeOperation) {
                    if (!Argument.getNullableValue(firstArg_).isNull()) {
                        tag_ = ExportCst.span(none_);
                        return tag_;
                    }
                }
                if (parentBefore_ instanceof AbstractTernaryOperation) {
                    if (BooleanStruct.isTrue(Argument.getNullableValue(firstArg_).getStruct())) {
                        if (before_.getIndexChild() == 2) {
                            tag_ = ExportCst.span(none_);
                            return tag_;
                        }
                    }
                    if (BooleanStruct.isFalse(Argument.getNullableValue(firstArg_).getStruct())) {
                        if (before_.getIndexChild() == 1) {
                            tag_ = ExportCst.span(none_);
                            return tag_;
                        }
                    }
                }
            }
            AbstractCoverageResult resultPar_ = getCovers(_in,_block, par_, _cov, _indexAnnotGroup);
            if (resultPar_.isPartialCovered()) {
                tag_ = getFullInitReport(resultPar_, fullInit_, full_);
                return tag_;
            }
            tag_ = ExportCst.span(none_);
            return tag_;
        }
        if (_result.isFullCovered()) {
            tag_ = getFullInitReport(_result, fullInit_, full_);
            return tag_;
        }
        if (_result.isPartialCovered()) {
            return getPartialInitReport(_val, _result, fullInit_, full_, partialInit_, partial_);
        }
        tag_ = ExportCst.span(none_);
        return tag_;
    }

    private static String getPartialInitReport(OperationNode _val, AbstractCoverageResult _result, String _fullInit, String _full, String _partialInit, String _partial) {
        String tag_;
        if (_val instanceof AffectationOperation && _val.getFirstChild().getNextSibling().getArgument() != null) {
            tag_ = getFullInitReport(_result, _fullInit, _full);
            return tag_;
        }
        if (_result.isInit()) {
            tag_ = ExportCst.span(_partialInit);
            return tag_;
        }
        tag_ = ExportCst.span(_partial);
        return tag_;
    }

    private static String getFullInitReport(AbstractCoverageResult _resultPar, String _fullInit, String _full) {
        String tag_;
        if (_resultPar.isInit()) {
            tag_ = ExportCst.span(_fullInit);
        } else {
            tag_ = ExportCst.span(_full);
        }
        return tag_;
    }

    private static void leftReport(VariablesOffsets _vars,
                                   AbsBk _block,
                                   int _sum,
                                   OperationNode _val,
                                   Coverage _cov,
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
        processLamba(_vars, _sum, _val, _parts, _currentFileName);
        processLeafType(_vars, _sum,_val, _parts);
        processDynamicCall(_sum, _val, _parts);
        processRichHeader(_vars, _currentFileName, _sum, _val, _parts);
        processUnaryLeftOperationsCoversReport(_vars, _sum, _val, _result, _parts);
        if (_val instanceof SwitchOperation) {
            SwitchMethodBlock switchMethod_ = ((SwitchOperation) _val).getSwitchMethod();
            int full_ = 0;
            int count_ = 0;
            for (AbstractCoverageResult e : _cov.getCoverSwitchsMethod(switchMethod_).values()) {
                count_ += e.getCovered();
                full_ += e.getFull();
            }
            String tag_ = headCoverage(full_, count_);
            int off_ = _sum + _val.getIndexInEl() + ((SwitchOperation)_val).getDelta();
            _parts.add(new PartOffset(tag_ + ExportCst.anchor(count_ + ExportCst.RATIO_COVERAGE + full_), off_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR + ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordSwitch().length()));
            _parts.addAllElts(((SwitchOperation)_val).getPartOffsets());
        }
        processUnaryLeftOperationsLinks(_vars, _currentFileName, _sum, _val, _parts);
        processLeftIndexer(_vars, _currentFileName, _sum, _val, _parts);
        processArrLength(_sum, _val, _parts);
    }

    private static String headCoverage(AbstractCoverageResult _result) {
        String tag_;
        if (_result.isFullCovered()) {
            tag_ = ExportCst.span(FULL);
        } else if (_result.isPartialCovered()) {
            tag_ = ExportCst.span(PARTIAL);
        } else {
            tag_ = ExportCst.span(NONE);
        }
        return tag_;
    }

    private static String headCoverage(int _full, int _count) {
        String tag_;
        if (_count == _full) {
            tag_ = ExportCst.span(FULL);
        } else if (_count > 0) {
            tag_ = ExportCst.span(PARTIAL);
        } else {
            tag_ = ExportCst.span(NONE);
        }
        return tag_;
    }

    private static void leftError(VariablesOffsets _vars,
                                  AbsBk _block,
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errEmpt_,ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+_val.getOperations().getDelimiter().getLength()));
            } else {
                int s_ = _sum + _val.getIndexInEl();
                processNullParent(_vars, _val, _parts, _currentFileName, s_);
            }
        } else {
            StringList l_ = new StringList();
            MethodOperation.processEmptyError(_val,l_);
            StrTypes operators_ =  par_.getOperations().getOperators();
            if (leftOperNotUnary(par_)&& !(indexChild_ == 0 && par_ instanceof ArrOperation)) {
                if (!l_.isEmpty()) {
                    int s_ = _sum + par_.getIndexInEl() + operators_.getKey(indexChild_);
                    int len_ = operators_.getValue(indexChild_).length();
                    _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(l_,ExportCst.JOIN_ERR)),s_));
                    _parts.add(new PartOffset(ExportCst.END_ANCHOR,s_+Math.max(len_,1)));
                } else {
                    appendPossibleParts(_parts, par_, indexChild_);
                }
            } else if (par_ instanceof IdOperation||par_ instanceof ArrOperation) {
                appendPossibleParts(_parts, par_, indexChild_);
            }
            if (!(par_ instanceof TernaryOperation)&&!(par_ instanceof RefTernaryOperation)) {
                if (leftOperNotUnary(par_) || par_ instanceof UnaryOperation || par_ instanceof UnaryBooleanOperation || par_ instanceof UnaryBinOperation||par_ instanceof SemiAffectationOperation&&!((SemiAffectationOperation)par_).isPost()) {
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
                    int s_ = _sum + par_.getIndexInEl() + operators_.getKey(indexChild_-1);
                    int len_ = operators_.getValue(indexChild_-1).length();
                    int off_ = s_+Math.max(len_,1);
                    _parts.add(mergeParts(_vars,_currentFileName,_val.getResultClass().getFunction(),off_,new StringList(),new StringList()));
                }
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
        processLamba(_vars, _sum, _val, _parts, _currentFileName);
        processLeafType(_vars, _sum,_val, _parts);
        processDynamicCall(_sum, _val, _parts);
        processRichHeader(_vars, _currentFileName, _sum, _val, _parts);
        if (_val instanceof SwitchOperation) {
            StringList all_ = new StringList(_val.getErrs());
            SwitchMethodBlock switchMethod_ = ((SwitchOperation) _val).getSwitchMethod();
            all_.addAllElts(switchMethod_.getErrorsBlock());
            if (!all_.isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((SwitchOperation)_val).getDelta();
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(all_,ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordSwitch().length()));
            }
            _parts.addAllElts(((SwitchOperation)_val).getPartOffsets());
        }
        processUnaryLeftOperationsLinks(_vars, _currentFileName, _sum, _val, _parts);
        processLeftIndexer(_vars, _currentFileName, _sum, _val, _parts);
        if (_val instanceof AnnotationInstanceOperation&&_val.getFirstChild() == null) {
            _parts.addAllElts(((AnnotationInstanceOperation)_val).getPartOffsetsEnd());
        }
        processArrLength(_sum, _val, _parts);
        if (_val.getParent() instanceof CallDynMethodOperation) {
            CallDynMethodOperation c_ = (CallDynMethodOperation) _val.getParent();
            if (!c_.getSepErr().isEmpty()&&c_.getIndexCh() == indexChild_) {
                String tag_ = ExportCst.anchorErr(c_.getSepErr());
                int rightPar_ = c_.getOperations().getOperators().getKey(c_.getIndexCh()+1);
                int beg_ = _sum + c_.getIndexInEl() + rightPar_;
                _parts.add(new PartOffset(tag_, beg_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,beg_+1));
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(aField_.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+str_.length()));
            } else {
                _parts.add(new PartOffset(ExportCst.HEAD_BOLD,begin_));
                _parts.add(new PartOffset(ExportCst.END_BOLD,begin_+str_.length()));
            }
        }
    }

    private static void processConstants(int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof ConstantOperation) {
            int off_ = _val.getOperations().getOffset();
            int begCst_ = _sum + off_ + _val.getIndexInEl();
            if (_val.getOperations().getConstType() == ConstType.TEXT_BLOCK) {
                _parts.add(new PartOffset(ExportCst.span(STRING), begCst_));
                if (!_val.getErrs().isEmpty()) {
                    String tag_ = ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR));
                    _parts.add(new PartOffset(tag_, begCst_));
                    _parts.add(new PartOffset(ExportCst.END_ANCHOR, begCst_ + ((ConstantOperation)_val).getBlockLength()));
                }
                _parts.add(new PartOffset(ExportCst.END_SPAN, begCst_ + ((ConstantOperation)_val).getBlockLength()));
            }
            if (_val.getOperations().getConstType() == ConstType.STRING) {
                _parts.add(new PartOffset(ExportCst.span(STRING), begCst_));
                if (!_val.getErrs().isEmpty()) {
                    String tag_ = ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR));
                    _parts.add(new PartOffset(tag_, begCst_));
                    _parts.add(new PartOffset(ExportCst.END_ANCHOR, begCst_ + ((ConstantOperation)_val).getLength()));
                }
                _parts.add(new PartOffset(ExportCst.END_SPAN, begCst_ + ((ConstantOperation)_val).getLength()));
            }
            if (_val.getOperations().getConstType() == ConstType.CHARACTER) {
                _parts.add(new PartOffset(ExportCst.span(STRING), begCst_));
                if (!_val.getErrs().isEmpty()) {
                    String tag_ = ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR));
                    _parts.add(new PartOffset(tag_, begCst_));
                    _parts.add(new PartOffset(ExportCst.END_ANCHOR, begCst_ + ((ConstantOperation)_val).getLength()));
                }
                _parts.add(new PartOffset(ExportCst.END_SPAN, begCst_ + ((ConstantOperation)_val).getLength()));
            }
            if (_val.getOperations().getConstType() == ConstType.NUMBER) {
                if (!_val.getErrs().isEmpty()) {
                    String tag_ = ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR));
                    _parts.add(new PartOffset(tag_, begCst_));
                    _parts.add(new PartOffset(ExportCst.END_ANCHOR, begCst_ +((ConstantOperation)_val).getNbLength()));
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
                tag_ = ExportCst.HEAD_BOLD;
                _parts.add(new PartOffset(tag_, begFct_));
                tag_ = ExportCst.END_BOLD;
            } else {
                tag_ = ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR));
                _parts.add(new PartOffset(tag_, begFct_));
                tag_ = ExportCst.END_ANCHOR;
            }
            _parts.add(new PartOffset(tag_,begFct_+ length_));
            if (((CallDynMethodOperation) _val).isNoNeed()) {
                tag_ = ExportCst.anchorErr(((CallDynMethodOperation) _val).getSepErr());
                int leftPar_ = _val.getOperations().getOperators().firstKey();
                int beg_ = _sum + _val.getIndexInEl() + leftPar_;
                _parts.add(new PartOffset(tag_, beg_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,beg_+1));
            }
            if (_val.getFirstChild() == null&&!((CallDynMethodOperation) _val).getSepErr().isEmpty()) {
                tag_ = ExportCst.anchorErr(((CallDynMethodOperation) _val).getSepErr());
                int rightPar_ = _val.getOperations().getOperators().lastKey();
                int beg_ = _sum + _val.getIndexInEl() + rightPar_;
                _parts.add(new PartOffset(tag_, beg_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,beg_+1));
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)), beg_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, beg_ +1));
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
                _parts.add(new PartOffset(ExportCst.anchorErr(a_.getErrAff()), begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, begin_+1));
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
                    _parts.add(new PartOffset(ExportCst.HEAD_BOLD, begFct_));
                    _parts.add(new PartOffset(ExportCst.END_BOLD, begFct_ +l_));
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

    private static void processFieldsReport(AbsBk _block, int _sum, OperationNode _val, CustList<PartOffset> _parts, String _currentFileName) {
        if (_val instanceof SettableAbstractFieldOperation) {
            int delta_ = ((SettableAbstractFieldOperation) _val).getOff();
            int begin_ = _sum + delta_ + _val.getIndexInEl() + ((SettableAbstractFieldOperation) _val).getDelta();
            RootBlock fieldType_ = ((SettableAbstractFieldOperation) _val).getFieldType();
            if (_block instanceof FieldBlock && ElUtil.isDeclaringField(_val)) {
                int idValueOffset_ = ((SettableAbstractFieldOperation)_val).getValueOffset();
                _parts.add(new PartOffset(ExportCst.anchorName(idValueOffset_),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+((SettableAbstractFieldOperation) _val).getFieldNameLength()));
            } else {
                int id_ = ((SettableAbstractFieldOperation)_val).getValueOffset();
                _parts.addAllElts(((SettableAbstractFieldOperation) _val).getPartOffsets());
                ClassField c_ = ((SettableAbstractFieldOperation)_val).getFieldIdReadOnly();
                updateFieldAnchor(fieldType_, _val.getErrs(),_parts,c_, begin_,((SettableAbstractFieldOperation) _val).getFieldNameLength(), _currentFileName,id_);
            }
        }
    }

    private static void processFieldsError(AbsBk _block, int _sum, OperationNode _val, CustList<PartOffset> _parts, String _currentFileName) {
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
                        _parts.add(new PartOffset(ExportCst.anchorName(idValueOffset_),begin_));
                    } else {
                        String err_ = StringUtil.join(errCst_,ExportCst.JOIN_ERR);
                        _parts.add(new PartOffset(ExportCst.anchorNameErr(idValueOffset_,err_),begin_));
                    }
                } else {
                    String err_ = StringUtil.join(errs_,ExportCst.JOIN_ERR);
                    _parts.add(new PartOffset(ExportCst.anchorErr(err_),begin_));
                }
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+((SettableAbstractFieldOperation) _val).getFieldNameLength()));
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
                    _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), begVar_));
                } else {
                    _parts.add(new PartOffset(ExportCst.anchorName(id_), begVar_));
                }

            } else {
                _parts.add(new PartOffset(ExportCst.anchorRef(id_), begVar_));
            }
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, endVar_));
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
                    _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), begVar_));
                } else {
                    _parts.add(new PartOffset(ExportCst.anchorName(id_), begVar_));
                }

            } else {
                _parts.add(new PartOffset(ExportCst.anchorRef(id_), begVar_));
            }
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, endVar_));
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
                    _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), begVar_));
                } else {
                    _parts.add(new PartOffset(ExportCst.anchorName(id_), begVar_));
                }

            } else {
                _parts.add(new PartOffset(ExportCst.anchorRef(id_), begVar_));
            }
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, endVar_));
        }
        if (_val instanceof RefParamOperation) {
            String varName_ = ((RefParamOperation) _val).getRealVariableName();
            int delta_ = ((RefParamOperation) _val).getOff();
            int id_ = ((RefParamOperation) _val).getRef();
            int begVar_ = delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            _parts.add(new PartOffset(ExportCst.anchorRef(id_), begVar_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, endVar_));
        }
        if (_val instanceof FinalVariableOperation) {
            String varName_ = ((FinalVariableOperation) _val).getRealVariableName();
            int delta_ = ((FinalVariableOperation) _val).getOff();
            int deltaLoc_ = ((FinalVariableOperation)_val).getDelta();
            int begVar_ = deltaLoc_ + delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            if (!_val.getErrs().isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)), begVar_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, endVar_));
            } else {
                if (((FinalVariableOperation) _val).isKeyWord()) {
                    _parts.add(new PartOffset(ExportCst.HEAD_BOLD, begVar_));
                    _parts.add(new PartOffset(ExportCst.END_BOLD, endVar_));
                } else {
                    int id_ = ((FinalVariableOperation) _val).getRef();
                    _parts.add(new PartOffset(ExportCst.anchorRef(id_), begVar_));
                    _parts.add(new PartOffset(ExportCst.END_ANCHOR, endVar_));
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
                if (inst_ instanceof StandardInstancingOperation){
                    _parts.addAllElts(inst_.getPartOffsets());
                }
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ f_.getLength()));
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordStatic().length()));
            }
            _parts.addAllElts(((StaticAccessOperation)_val).getPartOffsets());
        }
        if (_val instanceof StaticCallAccessOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordStaticCall().length()));
            }
            _parts.addAllElts(((StaticCallAccessOperation)_val).getPartOffsets());
            _parts.addAllElts(((StaticCallAccessOperation)_val).getStCallSolved());
        }
        if (_val instanceof ThisOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordThis().length()));
            }
        }
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)), beginLambda_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, beginLambda_ + lambdaLen_));
            }
        }
        _parts.addAllElts(((LambdaOperation)_val).getPartOffsets());
        if (((LambdaOperation)_val).getRecordType() >= 0) {
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordValueOf().length()));
            }
            _parts.addAllElts(((EnumValueOfOperation)_val).getPartOffsets());
        }
        if (_val instanceof ValuesOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl();
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordValues().length()));
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
        if (_val instanceof UnaryBooleanOperation && ((UnaryBooleanOperation)_val).getFct().getFunction() == null && _result.isStrictPartialCovered()) {
            int offsetOp_ = _val.getOperations().getOperators().firstKey();
            safeReport(_vars,_result, _sum + _val.getIndexInEl() + offsetOp_,_parts,1);
        }
    }

    private static void processUnaryLeftOperationsLinks(VariablesOffsets _vars, String _currentFileName, int _sum, OperationNode _val, CustList<PartOffset> _parts) {
        if (_val instanceof SymbolOperation && _val.getFirstChild().getNextSibling() == null) {
            SymbolOperation par_ = (SymbolOperation) _val;
            AnaTypeFct function_ = par_.getFct().getFunction();
            if (function_ != null) {
                addParts(_vars, _currentFileName, function_,
                        _sum + _val.getIndexInEl() + par_.getOpOffset(), function_.getFunction().getName().length(),
                        _val.getErrs(),_val.getErrs(),_parts);
            } else if (!_val.getErrs().isEmpty()){
                int i_ = _sum + _val.getIndexInEl() + par_.getOpOffset();
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),i_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,i_+1));
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),i_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,i_+l_));
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
                AnaTypeFct function_ = par_.getFct().getFunction();
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
                        _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(par_.getErrs(),ExportCst.JOIN_ERR)),begin_));
                        _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ 2));
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ 1));
            }
        }
        if (_val instanceof FirstOptOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((FirstOptOperation)_val).getDelta();
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordFirstopt().length()));
            }
        }
        if (_val instanceof WrappOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((WrappOperation)_val).getDelta();
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordThat().length()));
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
            String relFileOne_ = EMPTY;
            String relFileTwo_ = EMPTY;
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
                    if (AbsBk.isOverBlock(n)) {
                        NamedCalledFunctionBlock ov_ = (NamedCalledFunctionBlock) n;
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ n_.getName().length()));
            } else {
                if (refOne_ != -1){
                    int begin_ = _sum + _val.getIndexInEl()+firstOff_;
                    String rel_ = relativize(_currentFileName, ExportCst.href(relFileOne_, refOne_));
                    _parts.add(new PartOffset(ExportCst.anchorRef(rel_),begin_));
                    _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ n_.getName().length()));
                }
                if (refTwo_ != -1){
                    StrTypes vs_ = _val.getOperations().getOperators();
                    int begin_ = _sum + _val.getIndexInEl()+vs_.firstKey();
                    String rel_ = relativize(_currentFileName, ExportCst.href(relFileTwo_, refTwo_));
                    _parts.add(new PartOffset(ExportCst.anchorRef(rel_),begin_));
                    _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ vs_.firstValue().length()));
                }
            }
        }
        if (_val instanceof DefaultOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((DefaultOperation)_val).getDelta();
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordDefault().length()));
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(t_.getChildrenErrors(),ExportCst.JOIN_ERR)), begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, begin_ +_vars.getKeyWords().getKeyWordBool().length()));
            }
        }
    }

    private static void middleReport(LinkageStackElementIn _in, VariablesOffsets _vars,
                                     String _currentFileName,
                                     AbsBk _block,
                                     int _offsetEnd,
                                     OperationNode _curOp,
                                     OperationNode _nextSiblingOp,
                                     MethodOperation _parent,
                                     CustList<PartOffset> _parts, Coverage _cov, int _indexAnnotGroup) {
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
        processCompoundAffLeftOpReport(_in,_vars, _block, _currentFileName, _offsetEnd, _curOp,_nextSiblingOp, _parent, _parts, _cov, _indexAnnotGroup);
        processCompoundAffRightOp(_vars, _currentFileName, _offsetEnd, _parent, _parts);

        processCompareReport(_in,_vars, _block, _offsetEnd, _parent, _parts, _cov, _indexAnnotGroup);
        processDotSafeReport(_in,_vars, _block, _offsetEnd, _curOp, _parent, _parts, _cov, _indexAnnotGroup);
        processNullSafeReport(_in,_vars, _block, _offsetEnd, _curOp, _nextSiblingOp, _parent, _parts, _cov, _indexAnnotGroup);
        processLogicAndOrOperationReport(_in,_vars, _currentFileName,_block, _offsetEnd, _curOp, _nextSiblingOp, _parent, _parts, _cov, _indexAnnotGroup);
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
            StrTypes operators_ =  _parent.getOperations().getOperators();
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(l_,ExportCst.JOIN_ERR)), _offsetEnd));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR, _offsetEnd +Math.max(len_,1)));
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
            AnaTypeFct function_ = q_.getFct().getFunction();
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
            AnaTypeFct function_ = par_.getFct().getFunction();
            if (function_ != null) {
                addParts(_vars, _currentFileName, function_,_offsetEnd,par_.getOp().length(),_parentOp.getErrs(),_parentOp.getErrs(),_parts);
            } else if (_parentOp instanceof AddOperation && ((AddOperation)_parentOp).isCatString() && canCallToString(_vars, _curOp, _nextSiblingOp)) {
                _parts.add(new PartOffset(ExportCst.HEAD_ITALIC, _offsetEnd));
                _parts.add(new PartOffset(ExportCst.FOOT_ITALIC, _offsetEnd + 1));
            }

        }
    }

    private static boolean canCallToString(VariablesOffsets _vars, OperationNode _curOp, OperationNode _nextSiblingOp) {
        return canCallToString(_vars,_curOp.getResultClass()) || canCallToString(_vars,_nextSiblingOp.getResultClass());
    }
    private static void processDotSafeReport(LinkageStackElementIn _in, VariablesOffsets _vars, AbsBk _block, int _offsetEnd, OperationNode _curOp, MethodOperation _parentOp, CustList<PartOffset> _parts, Coverage _cov, int _indexAnnotGroup) {
        if (_parentOp instanceof SafeDotOperation) {
            AbstractCoverageResult resultFirst_ = getCovers(_in,_block, _curOp, _cov, _indexAnnotGroup);
            safeReport(_vars, resultFirst_, _offsetEnd,_parts, 1);
        }
    }
    private static void processNullSafeReport(LinkageStackElementIn _in, VariablesOffsets _vars, AbsBk _block, int _offsetEnd, OperationNode _curOp, OperationNode _nextSiblingOp, MethodOperation _parentOp, CustList<PartOffset> _parts, Coverage _cov, int _indexAnnotGroup) {
        if (_parentOp instanceof NullSafeOperation) {
            AbstractCoverageResult resultFirst_ = getCovers(_in,_block, _curOp, _cov, _indexAnnotGroup);
            AbstractCoverageResult resultLast_ = getCovers(_in,_block, _nextSiblingOp, _cov, _indexAnnotGroup);
            safeReport(_vars, resultFirst_, _offsetEnd,_parts, 1);
            safeReport(_vars, resultLast_, _offsetEnd+1,_parts, 1);
        }
    }

    private static void processCompareReport(LinkageStackElementIn _in, VariablesOffsets _vars, AbsBk _block, int _offsetEnd,
                                             MethodOperation _parent, CustList<PartOffset> _parts, Coverage _cov, int _indexAnnotGroup) {
        if (isWideCmp(_parent)) {

            int length_ = ((MiddleSymbolOperation)_parent) .getOp().length();
            if (((SymbolOperation)_parent).getFct().getFunction() == null) {
                AbstractCoverageResult resultLoc_ = getCovers(_in,_block, _parent, _cov, _indexAnnotGroup);
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
        AnaTypeFct function_ = par_.getFct().getFunction();
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
            _parts.add(new PartOffset(ExportCst.HEAD_ITALIC, begin_));
            _parts.add(new PartOffset(ExportCst.FOOT_ITALIC,begin_+len_));
        }
    }

    private static boolean hasToCallStringConver(VariablesOffsets _vars, OperationNode _nextSiblingOp) {
        return _nextSiblingOp.getResultClass().isConvertToString() && canCallToString(_vars,_nextSiblingOp.getResultClass());
    }

    private static void processCompoundAffLeftOpReport(LinkageStackElementIn _in, VariablesOffsets _vars, AbsBk _block, String _currentFileName, int _offsetEnd, OperationNode _curOp, OperationNode _nextSiblingOp, MethodOperation _parentOp, CustList<PartOffset> _parts, Coverage _cov, int _indexAnnotGroup) {
        if (!(_parentOp instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) _parentOp;
        AnaTypeFct function_ = par_.getFct().getFunction();
        int opDelta_ = par_.getOper().length() - 1;
        AnaTypeFct functionTest_ = par_.getFunctionTest();
        int begin_ = _offsetEnd;
        int len_ = opDelta_;
        if (functionTest_ != null) {
            StringList title_ = new StringList();
            AbstractCoverageResult resultFirst_ = getCovers(_in,_block, _curOp, _cov, _indexAnnotGroup);
            title_.addAllElts(getCoversFoundReport(_vars, resultFirst_));
            addParts(_vars, _currentFileName, functionTest_,begin_,1, _parentOp.getErrs(),title_,_parts);
            begin_++;
            len_--;
            if (StringUtil.quickEq(par_.getOper(),AbsBk.AND_LOG_EQ_SHORT)
                    || StringUtil.quickEq(par_.getOper(),AbsBk.OR_LOG_EQ_SHORT)){
                begin_++;
                len_--;
            }
        } else if (StringUtil.quickEq(par_.getOper(),AbsBk.AND_LOG_EQ)
                || StringUtil.quickEq(par_.getOper(),AbsBk.OR_LOG_EQ)){
            AbstractCoverageResult resultFirst_ = getCovers(_in,_block, _curOp, _cov, _indexAnnotGroup);
            safeReport(_vars, resultFirst_, begin_,_parts,1);
            begin_++;
            len_--;
        } else if (StringUtil.quickEq(par_.getOper(),AbsBk.AND_LOG_EQ_SHORT)
                || StringUtil.quickEq(par_.getOper(),AbsBk.OR_LOG_EQ_SHORT)){
            AbstractCoverageResult resultFirst_ = getCovers(_in,_block, _curOp, _cov, _indexAnnotGroup);
            safeReport(_vars, resultFirst_, begin_,_parts,1);
            begin_+=2;
            len_-=2;
        } else if (StringUtil.quickEq(par_.getOper(),AbsBk.NULL_EQ)){
            AbstractCoverageResult resultFirst_ = getCovers(_in,_block, _curOp, _cov, _indexAnnotGroup);
            safeReport(_vars, resultFirst_, begin_,_parts,1);
            begin_++;
            len_--;
        } else if (StringUtil.quickEq(par_.getOper(),AbsBk.NULL_EQ_SHORT)){
            AbstractCoverageResult resultFirst_ = getCovers(_in,_block, _curOp, _cov, _indexAnnotGroup);
            safeReport(_vars, resultFirst_, begin_,_parts,1);
            begin_+=2;
            len_-=2;
        }
        if (function_ != null) {
            addParts(_vars, _currentFileName, function_,begin_,len_,_parentOp.getErrs(),_parentOp.getErrs(),_parts);
        } else if (hasToCallStringConver(_vars, _nextSiblingOp)){
            _parts.add(new PartOffset(ExportCst.HEAD_ITALIC, begin_));
            _parts.add(new PartOffset(ExportCst.FOOT_ITALIC,begin_+len_));
        } else if (StringUtil.quickEq(par_.getOper(),AbsBk.NULL_EQ) || StringUtil.quickEq(par_.getOper(),AbsBk.NULL_EQ_SHORT)){
            AbstractCoverageResult resultLast_ = getCovers(_in,_block, _nextSiblingOp, _cov, _indexAnnotGroup);
            safeReport(_vars, resultLast_, begin_,_parts, 1);
        } else {
            if (par_.isRightBool()) {
                AbstractCoverageResult resultLast_ = getCovers(_in,_block, _nextSiblingOp, _cov, _indexAnnotGroup);
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
    private static void processLogicAndOrOperationReport(LinkageStackElementIn _in, VariablesOffsets _vars, String _currentFileName, AbsBk _block, int _offsetEnd, OperationNode _curOp, OperationNode _nextSiblingOp, MethodOperation _parentOp, CustList<PartOffset> _parts, Coverage _cov, int _indexAnnotGroup) {
        if (!(_parentOp instanceof QuickOperation)) {
            return;
        }
        QuickOperation q_ = (QuickOperation) _parentOp;
        AbstractCoverageResult resultFirst_ = getCovers(_in,_block, _curOp, _cov, _indexAnnotGroup);
        AbstractCoverageResult resultLast_ = getCovers(_in,_block, _nextSiblingOp, _cov, _indexAnnotGroup);
        StringList errs_ = q_.getErrs();
        AnaTypeFct functionTest_ = q_.getFunctionTest();
        if (functionTest_ != null) {
            StringList title_ = new StringList();
            title_.addAllElts(getCoversFoundReport(_vars, resultFirst_));
            addParts(_vars, _currentFileName, functionTest_,_offsetEnd,1, errs_,title_,_parts);
        } else {
            safeReport(_vars, resultFirst_, _offsetEnd,_parts,1);
        }
        AnaTypeFct function_ = q_.getFct().getFunction();
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_parent.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+_vars.getKeyWords().getKeyWordInstanceof().length()));
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
                AnaTypeFct function_ = par_.getFct().getFunction();
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
                        _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_parent.getErrs(),ExportCst.JOIN_ERR)),begin_));
                        _parts.add(new PartOffset(ExportCst.END_ANCHOR,begin_+2));
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
        return _res.getCoversFoundReport(_vars);
    }

    private static void basicValue(int _offsetEnd, CustList<PartOffset> _parts, int _delta, StringList _founds) {
        _parts.add(new PartOffset(ExportCst.anchor(StringUtil.join(_founds,ExportCst.FOUND_COVERAGE)), _offsetEnd));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR,_offsetEnd+ _delta));
    }
    private static PartOffset mergeParts(VariablesOffsets _vars, String _currentFileName,
                                         AnaTypeFct _id, int _begin,
                                         StringList _errors,
                                         StringList _title) {
        CustList<PartOffset> parts_ = new CustList<PartOffset>();
        addParts(_vars,_currentFileName,_id,_begin,1,_errors,_title,parts_);
        if (parts_.isEmpty()) {
            return new PartOffset(EMPTY,_begin);
        }
        return new PartOffset(parts_.first().getPart()+ExportCst.IMPLICIT+parts_.last().getPart(),_begin);
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
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_errors,ExportCst.JOIN_ERR)),_begin));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,_begin+_length));
            }
            return;
        }
        NamedFunctionBlock function_ = _id.getFunction();
        String tag_;
        if (function_ instanceof OperatorBlock) {
            tag_ = ExportCst.BEGIN_ANCHOR+name(_name)+ ExportCst.title(_title,function_.getSignature(_dis))+ExportCst.SEP_ATTR
                    +ExportCst.href(rel_)+classErr(_errors)+ExportCst.END;
        } else {
            String cl_ = _id.getType().getFullName();
            tag_ = ExportCst.BEGIN_ANCHOR+name(_name)+ ExportCst.title(_title,cl_ +ExportCst.SEP_TYPE_MEMBER+ function_.getSignature(_dis))+ExportCst.SEP_ATTR
                    +ExportCst.href(rel_)+classErr(_errors)+ExportCst.END;
        }
        _parts.add(new PartOffset(tag_,_begin));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR,_begin+_length));
    }

    private static String name(int _name) {
        if (_name < 0) {
            return ExportCst.SEP_ATTR;
        }
        return ExportCst.SEP_ATTR+ExportCst.name(_name)+ExportCst.SEP_ATTR;
    }
    private static String classErr(StringList _errors) {
        if (_errors.isEmpty()) {
            return EMPTY;
        }
        return ExportCst.SEP_ATTR_CLASS_ERR;
    }

    private static String getRelativize(String _currentFileName, AnaTypeFct _id) {
        if (_id == null || _id.getFunction() == null) {
            return EMPTY;
        }
        NamedFunctionBlock function_ = _id.getFunction();
        if (function_ instanceof OperatorBlock) {
            String file_ = function_.getFile().getRenderFileName();
            return relativize(_currentFileName, ExportCst.href(file_, function_.getNameOffset()));
        }
        if (!ContextUtil.isFromCustFile(_id.getType())) {
            return EMPTY;
        }
        return relativize(_currentFileName, ExportCst.href(function_.getFile().getRenderFileName(), function_.getNameOffset()));
    }

    private static AbstractCoverageResult getCovers(LinkageStackElementIn _in, AbsBk _block, OperationNode _oper, Coverage _cov, int _indexAnnotGroup) {
        return _cov.getCovers(_block,_oper, _indexAnnotGroup, _in.getIndexAnnotationLook());
    }

    private static void updateFieldAnchor(RootBlock _type, StringList _errs, CustList<PartOffset> _parts, ClassField _id, int _begin, int _length, String _currentFileName, int _offset) {
        if (!ContextUtil.isFromCustFile(_type)) {
            if (!_errs.isEmpty()) {
                _parts.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(_errs,ExportCst.JOIN_ERR)),_begin));
                _parts.add(new PartOffset(ExportCst.END_ANCHOR,_begin+_length));
            }
            return;
        }
        String file_ = _type.getFile().getRenderFileName();
        String rel_ = relativize(_currentFileName,ExportCst.href(file_,_offset));
        _parts.add(new PartOffset(ExportCst.BEGIN_ANCHOR+ExportCst.SEP_ATTR
                +ExportCst.title(_errs,StringExpUtil.getIdFromAllTypes(_id.getClassName()) +ExportCst.SEP_TYPE_MEMBER+ _id.getFieldName())+ExportCst.SEP_ATTR
                +ExportCst.href(rel_)+classErr(_errs)+ExportCst.END,_begin));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR,_begin+_length));
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
            if(curChar_ == SEP_DIR) {
                diffFirst_ = i;
                countCommon_++;
            }
        }
        if (finished_) {
            return _refFile.substring(len_);
        }
        String relFile_ = _refFile.substring(diffFirst_ + 1);
        if (_currentFile.indexOf(SEP_DIR,diffFirst_+1) < 0) {
            return relFile_;
        }
        StringBuilder b_ = new StringBuilder();
        int count_ = StringUtil.indexesOfChar(_currentFile, SEP_DIR).size() - countCommon_;
        for (int i = 0; i < count_; i++) {
            b_.append(PARENT).append(SEP_DIR);
        }
        return b_.append(relFile_).toString();
    }
    public static String transform(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            if (c > 126) {
                str_.append(escapeNum(c));
            } else if (c == LEFT_TAG) {
                str_.append(LT);
            } else if (c == RIGHT_TAG) {
                str_.append(GT);
            } else if (c == SPEC_CHAR) {
                str_.append(AMP);
            } else if (c == QUOT_CHAR) {
                str_.append(QUOT);
            } else if (c < SPACE_CH) {
                str_.append(processSpace(c));
            } else {
                str_.append(c);
            }
        }
        return str_.toString();
    }
    private static String escapeNum(char _ch) {
        return BEGIN_ENCODE +(int)_ch+ END_ENCODE;
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
        if (_vars.goesToProcess()) {
            return;
        }
        _parts.add(new PartOffset(ExportCst.anchorName(_offset),_offset));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR,_offset+_label.length()));
    }

    private static void refLabelError(VariablesOffsets _vars, AbsBk _bl, CustList<PartOffset> _parts, String _label, int _offset) {
        if (_label.isEmpty()) {
            return;
        }
        if (_vars.goesToProcess()) {
            return;
        }
        StringList errs_ = _bl.getErrorsLabels();
        if (!errs_.isEmpty()) {
            _parts.add(new PartOffset(ExportCst.anchorNameErr(_offset,StringUtil.join(errs_,ExportCst.JOIN_ERR)),_offset));
        } else {
            _parts.add(new PartOffset(ExportCst.anchorName(_offset),_offset));
        }
        _parts.add(new PartOffset(ExportCst.END_ANCHOR,_offset+_label.length()));
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
        if (_op instanceof AbstractDotOperation && !_op.getOperations().getOperators().firstValue().isEmpty()) {
            return true;
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


    private static boolean isImplicitReturn(AbsBk _ret) {
        if (!(_ret instanceof ReturnMethod)) {
            return false;
        }
        return ((ReturnMethod)_ret).isImplicit();
    }
}
