package code.expressionlanguage.linkage;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.GraphicErrorInterpret;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodId;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodIdList;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ResolvedInstance;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.LinkagePartTypeUtil;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.coverage.SwitchCoverageResult;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.AnaElementContent;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.maths.litteralcom.IndexStrPart;
import code.maths.litteralcom.StrTypes;
import code.util.*;
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
        StringList randCodeOwners_ = _analyzing.getRandCodeOwners();
        for (FileBlock f: _analyzing.getErrors().getFiles()) {
            if (f.isPredefined()) {
                continue;
            }
            String value_ = f.getContent();
            String fileExp_ = f.getRenderFileName();
            VariablesOffsets listStr_ = processError(toStringOwners_,randCodeOwners_,f, keyWords_, displayedStrings_, implicit_);
            StringBuilder xml_ = build(f, value_, listStr_);
            String cssPart_ = BEGIN_HEAD + encode(_analyzing.isEncodeHeader()) +
                    link(f) +
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
        StringList randCodeOwners_ = cov_.getRandCodeOwners();
        for (FileBlock f: cov_.getFiles()) {
            if (f.isPredefined()) {
                continue;
            }
            String value_ = f.getContent();
            String fileExp_ = f.getRenderFileName();
            VariablesOffsets listStr_ = processReport(toStringOwners_,randCodeOwners_,f, cov_, keyWords_, standards_);
            StringBuilder xml_ = build(f, value_, listStr_);
            String cssPart_ = BEGIN_HEAD +encode(cov_.isDisplayEncode())+
                    link(f) +
                    END_HEAD;
            files_.addEntry(fileExp_, BEGIN +cssPart_+ BEGIN_BODY_PRE +xml_+END_BODY_PRE+END);
        }
        IdMap<RootBlock,String> types_ = new IdMap<RootBlock,String>();
        for (RootBlock t: refFoundTypes_) {
            if (t.getFile().isPredefined()) {
                continue;
            }
            String ratioCall_ = ratioCall(cov_, t);
            types_.addEntry(t, ratioCall_);
        }
        int calledOps_ = getCalledOps(cov_, operators_);
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
        buildTable(types_, table_);
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

    private static String ratioCall(Coverage _cov, RootBlock _type) {
        int countCall_ = 0;
        int count_ = 0;
        for (AbsBk b: ClassesUtil.getDirectChildren(_type)) {
            if (AbsBk.isAnnotBlock(b)) {
                countCall_++;
                count_++;
                continue;
            }
            if (b instanceof MemberCallingsBlock) {
                if (_cov.getFctRes((MemberCallingsBlock) b).isCalled()) {
                    countCall_++;
                }
                count_++;
            }
        }
        return countCall_ + ExportCst.RATIO_COVERAGE + count_;
    }

    private static int getCalledOps(Coverage _cov, CustList<OperatorBlock> _operators) {
        int calledOps_ = 0;
        for (OperatorBlock b: _operators) {
            if (_cov.getFctRes(b).isCalled()) {
                calledOps_++;
            }
        }
        return calledOps_;
    }

    private static void buildTable(IdMap<RootBlock, String> _types, StringBuilder _table) {
        for (EntryCust<RootBlock,String> e: _types.entryList()) {
            _table.append(TR);
            _table.append(TD);
            _table.append(e.getKey().getFullName());
            _table.append(ETD);
            _table.append(TD);
            _table.append(e.getValue());
            _table.append(ETD);
            _table.append(ETR);
        }
    }

    private static String link(FileBlock _file) {
        return "<link href=\"" + RelativePathUtil.relativize(_file, CSS) + "\" rel=\"stylesheet\" type=\"text/css\"/>";
    }

    private static String encode(boolean _encode) {
        if (_encode) {
            return "<meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/>";
        }
        return EMPTY;
    }
    private static StringBuilder build(FileBlock _fileBlock, String _value, VariablesOffsets _listStr) {
        String value_ = _value;
        if (_value.isEmpty()) {
            value_ = DEF_SPACE;
        }
        StringBuilder xml_ = new StringBuilder(value_.length());
        int i_ = value_.length() - 1;
        for (PartOffset t:_listStr.getParts()) {
            String part_ = t.getPart();
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

    private static VariablesOffsets processError(StringList _toStringOwers, StringList _randCodeOwners, FileBlock _ex, KeyWords _keyWords, DisplayedStrings _displayedStrings, boolean _implicit){
        VariablesOffsets vars_ = new VariablesOffsets();
        vars_.addPart(new PartOffset(ExportCst.span(TYPE),0));
        vars_.addStackElt(new LinkageStackElement(_ex.getLength()));
        vars_.setKeyWords(_keyWords);
        vars_.setImplicit(_implicit);
        vars_.setDisplayedStrings(_displayedStrings);
        vars_.setToStringOwners(_toStringOwers);
        vars_.setRandCodeOwners(_randCodeOwners);
        vars_.setCurrentFile(_ex);
        if (_ex.getFirstChild() == null || !_ex.getErrorsFiles().getLi().isEmpty()) {
            processFileBlockError(vars_,_ex);
            vars_.addPart(new PartOffset(ExportCst.END_SPAN,Math.max(1, _ex.getLength())));
            return vars_;
        }
        AbsBk child_ = _ex;
        while (child_ != null) {
            child_ = loopErr(_ex, vars_, child_);
        }
        return vars_;
    }

    private static AbsBk loopErr(FileBlock _ex, VariablesOffsets _vars, AbsBk _child) {
        _vars.getLastStackElt().setBlock(_child);
        processFileHeader(_vars, _child);
        if (isOuterBlock(_child)) {
            processGlobalRootBlockError(_vars,(BracedBlock) _child);
            if (!((BracedBlock) _child).getGlobalErrorsPars().getLi().isEmpty()) {
                return nextSkip(_vars, _child, _ex);
            }
        }
        processBlockError(_vars, _child);
        if (_vars.goesToProcess()) {
            _vars.addStackElt();
            return _vars.getLastStackElt().getBlock();
        }
        processEndBlockError(_vars, _child);
        return afterBlock(_ex, _vars, _child);
    }

    private static void processFileHeader(VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof FileBlock) {
            processFileBlockReport(_vars,(FileBlock) _child);
        }
    }

    private static boolean isOuterBlock(AbsBk _child) {
        return _child instanceof RootBlock || _child instanceof OperatorBlock;
    }

    private static void processEndBlockError(VariablesOffsets _vars, AbsBk _child) {
        if (endHeaderBlock(_child)) {
            addErrBlock(_vars, _child);
        }
    }

    private static boolean endHeaderBlock(AbsBk _child) {
        return _child instanceof Line
                || _child instanceof NamedFunctionBlock && !AbsBk.isAnonBlock(_child)
                || _child instanceof InfoBlock;
    }

    private static void processBlockError(VariablesOffsets _vars, AbsBk _child) {
        if (_vars.getLastStackElt().noVisited() && beginHeaderBlock(_child)) {
            addErrBlock(_vars, _child);
        }
        processTypeBlockError(_vars, _child);
        if (_child instanceof InternOverrideBlock) {
            processInternOverrideBlock(_vars, (InternOverrideBlock) _child);
        }
        if (_child instanceof OperatorBlock) {
            processOverridableBlockError(_vars,(OperatorBlock) _child);
        }
        if (_child instanceof ConstructorBlock) {
            processConstructorBlockError(_vars,(ConstructorBlock) _child);
        }
        if (_child instanceof NamedCalledFunctionBlock) {
            processNamedCalledFunctionBlockError(_vars, (NamedCalledFunctionBlock) _child);
        }
        if (_child instanceof SwitchMethodBlock) {
            processSwitchMethodError(_vars,(SwitchMethodBlock) _child);
        }
        if (_child instanceof ElementBlock) {
            processElementBlockError(_vars,(ElementBlock) _child);
        }
        if (_child instanceof FieldBlock) {
            processFieldBlockError(_vars,(FieldBlock) _child);
        }
        processDefaultExpError(_vars, _child);
        processAbruptBlockError(_vars, _child);
    }

    private static void processTypeBlockError(VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof RootBlock) {
            if (_child instanceof InnerElementBlock) {
                processInnerElementBlockError(_vars,(InnerElementBlock) _child);
            } else {
                processRootBlockError(_vars, (RootBlock) _child);
            }
        }
    }

    private static void processDefaultExpError(VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof WhileCondition) {
            processLabelledConditionError(_vars, (ConditionBlock) _child);
        }
        if (_child instanceof IfCondition) {
            processLabelledConditionError(_vars, (ConditionBlock) _child);
        }
        if (_child instanceof ElseIfCondition) {
            processConditionError((ElseIfCondition) _child, _vars);
            processTestCondition(_vars, (ElseIfCondition) _child);
        }
        if (_child instanceof DoBlock) {
            processDoBlockError(_vars, (DoBlock) _child);
        }
        if (_child instanceof DoWhileCondition) {
            processConditionError((DoWhileCondition) _child, _vars);
            processTestCondition(_vars, (DoWhileCondition) _child);
        }
        if (_child instanceof SwitchBlock) {
            processSwitchBlockError(_vars,(SwitchBlock) _child);
        }
        if (_child instanceof CaseCondition) {
            processCaseConditionError(_vars,(CaseCondition) _child);
        }
        if (_child instanceof DefaultCondition) {
            processDefaultConditionError(_vars,(DefaultCondition) _child);
        }
        if (_child instanceof TryEval) {
            processTryEvalError(_vars, (TryEval) _child);
        }
        if (_child instanceof CatchEval) {
            processCatchEvalError(_vars, (CatchEval) _child);
        }
        if (_child instanceof DeclareVariable) {
            processDeclareVariableError(_vars,(DeclareVariable) _child);
        }
        if (_child instanceof Line) {
            processLineError(_vars,(Line) _child);
        }
        processForLoopError(_vars, _child);
    }

    private static void processForLoopError(VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof ForIterativeLoop) {
            processForIterativeLoopError(_vars,(ForIterativeLoop) _child);
        }
        if (_child instanceof ForEachLoop) {
            processForEachLoopError(_vars,(ForEachLoop) _child);
        }
        if (_child instanceof ForEachTable) {
            processForEachTableError(_vars,(ForEachTable) _child);
        }
        if (_child instanceof ForMutableIterativeLoop) {
            processForMutableIterativeLoopError(_vars,(ForMutableIterativeLoop) _child);
        }
    }

    private static void processAbruptBlockError(VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof ReturnMethod) {
            processReturnMethodError(_vars,(ReturnMethod) _child);
        }
        if (_child instanceof Throwing) {
            processThrowingError(_vars,(Throwing) _child);
        }
        if (_child instanceof BreakBlock) {
            processBreakBlockError(_vars,(BreakBlock) _child);
        }
        if (_child instanceof ContinueBlock) {
            processContinueBlockError(_vars,(ContinueBlock) _child);
        }
    }

    private static boolean beginHeaderBlock(AbsBk _child) {
        return !(_child instanceof AnnotableParametersBlock) && !(_child instanceof InfoBlock)
                && !(_child instanceof Line) && !(_child instanceof DeclareVariable)
                && !(_child instanceof EmptyInstruction) && !(_child instanceof RootBlock) && !isImplicitReturn(_child);
    }

    private static void addErrBlock(VariablesOffsets _vars, AbsBk _child) {
        if (!_child.getErrorsBlock().isEmpty()) {
            String err_ = StringUtil.join(_child.getErrorsBlock(), ExportCst.JOIN_ERR);
            int off_ = _child.getBegin();
            int l_ = _child.getLengthHeader();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(err_), off_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, off_ + l_));
        }
    }

    private static void processNamedCalledFunctionBlockError(VariablesOffsets _vars, NamedCalledFunctionBlock _child) {
        if (_child.getTypeCall() == NameCalledEnum.OVERRIDABLE) {
            processOverridableBlockError(_vars, _child);
        }
        if (_child.getTypeCall() == NameCalledEnum.ANONYMOUS) {
            processAnonymousFctBlockError(_vars, _child);
        }
        if (_child.getTypeCall() == NameCalledEnum.ANNOTATION) {
            processAnnotationMethodBlockError(_vars, _child);
        }
    }

    private static void processFileBlockError(VariablesOffsets _vars, FileBlock _cond) {
        for (GraphicErrorInterpret g: _cond.getErrorsFiles().getLi()) {
            int index_ = g.getIndexFile();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(g.getBuiltError()), index_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, index_+ g.getLength()));
        }
    }
    private static void processGlobalRootBlockError(VariablesOffsets _vars, BracedBlock _cond) {
        for (GraphicErrorInterpret g: _cond.getGlobalErrorsPars().getLi()) {
            int index_ = g.getIndexFile();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(g.getBuiltError()), index_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, index_+ g.getLength()));
        }
    }
    private static VariablesOffsets processReport(StringList _toStringOwers, StringList _randCodeOwners, FileBlock _ex, Coverage _coverage, KeyWords _keyWords, LgNames _standards){
        VariablesOffsets vars_ = new VariablesOffsets();
        vars_.addPart(new PartOffset(ExportCst.span(TYPE),0));
        vars_.addStackElt(new LinkageStackElement(_ex.getLength()));
        vars_.setKeyWords(_keyWords);
        vars_.setImplicit(_coverage.isImplicit());
        vars_.setDisplayedStrings(_standards.getDisplayedStrings());
        vars_.setToStringOwners(_toStringOwers);
        vars_.setRandCodeOwners(_randCodeOwners);
        vars_.setCurrentFile(_ex);
        AbsBk child_ = _ex;
        vars_.getLastStackElt().setBlock(child_);
        while (child_ != null) {
            child_ = loopReport(_ex, _coverage, vars_, child_);
        }
        return vars_;
    }

    private static AbsBk loopReport(FileBlock _ex, Coverage _coverage, VariablesOffsets _vars, AbsBk _child) {
        _vars.getLastStackElt().setBlock(_child);
        processBlockReport(_coverage, _vars, _child);
        if (_vars.goesToProcess()) {
            _vars.addStackElt();
            return _vars.getLastStackElt().getBlock();
        }
        return afterBlock(_ex, _vars, _child);
    }

    private static AbsBk afterBlock(FileBlock _ex, VariablesOffsets _vars, AbsBk _child) {
        if (_vars.getLastStackElt().isStopVisit()) {
            _vars.removeLastStackElt();
            return redirect(_vars);
        }
        AbsBk child_ = next(_vars, _child, _ex);
        if (child_ == null) {
            _vars.removeLastStackElt();
            child_ = redirect(_vars);
        }
        return child_;
    }

    private static void processBlockReport(Coverage _coverage, VariablesOffsets _vars, AbsBk _child) {
        processExpReport(_coverage, _vars, _child);
        if (_child instanceof ElementBlock) {
            processElementBlockReport(_vars,(ElementBlock) _child, _coverage);
        }
        if (_child instanceof FieldBlock) {
            processFieldBlockReport(_vars,(FieldBlock) _child, _coverage);
        }
        if (_child instanceof ConstructorBlock) {
            processConstructorBlockReport(_vars,(ConstructorBlock) _child, _coverage);
        }
        if (_child instanceof NamedCalledFunctionBlock) {
            processNamedCalledFunctionBlockReport(_coverage, _vars, (NamedCalledFunctionBlock) _child);
        }
        if (_child instanceof InternOverrideBlock) {
            processInternOverrideBlock(_vars, (InternOverrideBlock) _child);
        }
        if (_child instanceof SwitchMethodBlock) {
            processSwitchMethodReport(_vars,(SwitchMethodBlock) _child, _coverage);
        }

        if (_child instanceof OperatorBlock) {
            processOverridableBlockReport(_vars,(OperatorBlock) _child, _coverage);
        }
        processTypeBlockReport(_coverage, _vars, _child);
        processFileHeader(_vars, _child);
    }

    private static void processTypeBlockReport(Coverage _coverage, VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof RootBlock) {
            if (_child instanceof InnerElementBlock) {
                processInnerElementBlockReport(_vars,(InnerElementBlock) _child, _coverage);
            } else {
                processRootBlockReport(_vars,(RootBlock) _child, _coverage);
            }
        }
    }

    private static void processExpReport(Coverage _coverage, VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof IfCondition) {
            processLabelledConditionReport(_vars, (ConditionBlock) _child, _coverage, _vars.getKeyWords().getKeyWordIf().length());
        }
        if (_child instanceof ElseIfCondition) {
            processElseIfConditionReport(_vars,(ElseIfCondition) _child, _coverage);
        }
        if (_child instanceof WhileCondition) {
            processLabelledConditionReport(_vars, (ConditionBlock) _child, _coverage, _vars.getKeyWords().getKeyWordWhile().length());
        }
        if (_child instanceof DoWhileCondition) {
            processDoWhileConditionReport(_vars,(DoWhileCondition) _child, _coverage);
        }
        if (_child instanceof DoBlock) {
            processDoBlockReport(_vars,(DoBlock) _child);
        }
        if (_child instanceof SwitchBlock) {
            processSwitchBlockReport(_vars,(SwitchBlock) _child, _coverage);
        }
        if (_child instanceof CaseCondition) {
            processCaseConditionReport(_vars,(CaseCondition) _child, _coverage);
        }
        if (_child instanceof DefaultCondition) {
            processDefaultConditionReport(_vars,(DefaultCondition) _child, _coverage);
        }
        if (_child instanceof TryEval) {
            processTryEvalReport(_vars,(TryEval) _child);
        }
        if (_child instanceof CatchEval) {
            processCatchEvalReport(_vars,(CatchEval) _child, _coverage);
        }
        if (_child instanceof NullCatchEval) {
            processAbstractCatchEvalReport(_vars,(NullCatchEval) _child, _coverage);
        }
        if (_child instanceof DeclareVariable) {
            processDeclareVariableReport(_vars,(DeclareVariable) _child);
        }
        if (_child instanceof Line) {
            processLineReport(_vars,(Line) _child, _coverage);
        }
        processAbruptBlockReport(_coverage, _vars, _child);
        processForLoopReport(_coverage, _vars, _child);
    }

    private static void processForLoopReport(Coverage _coverage, VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof ForMutableIterativeLoop) {
            processForMutableIterativeLoopReport(_vars,(ForMutableIterativeLoop) _child, _coverage);
        }
        if (_child instanceof ForIterativeLoop) {
            processForIterativeLoopReport(_vars,(ForIterativeLoop) _child, _coverage);
        }
        if (_child instanceof ForEachLoop) {
            processForEachLoopReport(_vars,(ForEachLoop) _child, _coverage);
        }
        if (_child instanceof ForEachTable) {
            processForEachTableReport(_vars,(ForEachTable) _child, _coverage);
        }
    }

    private static void processAbruptBlockReport(Coverage _coverage, VariablesOffsets _vars, AbsBk _child) {
        if (_child instanceof ReturnMethod) {
            processReturnMethodReport(_vars,(ReturnMethod) _child, _coverage);
        }
        if (_child instanceof Throwing) {
            processThrowingReport(_vars,(Throwing) _child, _coverage);
        }
        if (_child instanceof BreakBlock) {
            processBreakBlockReport(_vars,(BreakBlock) _child);
        }
        if (_child instanceof ContinueBlock) {
            processContinueBlockReport(_vars,(ContinueBlock) _child);
        }
    }

    private static void processNamedCalledFunctionBlockReport(Coverage _coverage, VariablesOffsets _vars, NamedCalledFunctionBlock _child) {
        if (_child.getTypeCall() == NameCalledEnum.OVERRIDABLE) {
            processOverridableBlockReport(_vars, _child, _coverage);
        }
        if (_child.getTypeCall() == NameCalledEnum.ANONYMOUS) {
            processAnonymousFctReport(_vars, _child, _coverage);
        }
        if (_child.getTypeCall() == NameCalledEnum.ANNOTATION) {
            processAnnotationMethodBlockReport(_vars, _child, _coverage);
        }
    }

    private static AbsBk redirect(VariablesOffsets _vars) {
        if (_vars.hasEltStack()) {
            return _vars.getLastStackElt().getBlock();
        }
        return null;
    }
    private static AbsBk next(VariablesOffsets _vars, AbsBk _current, AbsBk _ex) {
        AbsBk firstChild_ = _current.getFirstChild();
        if (firstChild_ != null) {
            return firstChild_;
        }
        return nextSkip(_vars, _current,_ex);
    }
    private static AbsBk nextSkip(VariablesOffsets _vars, AbsBk _current, AbsBk _ex) {
        AbsBk child_ = _current;
        while (true) {
            AbsBk nextSibling_ = child_.getNextSibling();
            if (nextSibling_ != null) {
                return nextSibling_;
            }
            BracedBlock parent_ = child_.getParent();
            if (parent_ == _ex || parent_ == null) {
                int indexEnd_ = _vars.getLastStackElt().getIndexEnd();
                _vars.addPart(new PartOffset(ExportCst.END_SPAN, indexEnd_));
                return null;
            }
            child_ = parent_;
        }
    }

    private static void processInternOverrideBlock(VariablesOffsets _vars, InternOverrideBlock _child) {
        for (PartOffsetsClassMethodIdList l: _child.getAllPartsTypes()) {
            addTypes(_vars, l.getTypes());
            _vars.addParts(convert(l.getInfo()));
            for (PartOffsetsClassMethodId p:l.getOverrides()) {
                tryAppendParts(_vars, p);
            }
        }
    }

    private static void tryAppendParts(VariablesOffsets _vars, PartOffsetsClassMethodId _p) {
        addTypes(_vars, _p.getTypes());
        ClassMethodId id_ = _p.getId();
        if (id_ != null) {
            int rc_ = _p.getBegin();
            int len_ = _p.getLength();
            CustList<PartOffset> partMethod_ = new CustList<PartOffset>();
            StringList l_ = new StringList();
            addParts(_vars, _p.getFct(), rc_, len_, l_, l_);
            _vars.addParts(partMethod_);
        }
        addTypes(_vars, _p.getSuperTypes());
        _vars.addParts(convert(_p.getInfo()));
    }

    private static void processLabelledConditionError(VariablesOffsets _vars, ConditionBlock _cond) {
        processConditionError(_cond, _vars);
        refLabelError(_vars, _cond, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars, _cond);
    }

    private static void processElseIfConditionReport(VariablesOffsets _vars, ElseIfCondition _cond, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond,_cond);
            String tag_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _vars.addPart(new PartOffset(tag_, off_));
            _vars.addPart(new PartOffset(ExportCst.END_SPAN, off_ + _cond.getDelta()));
        }
        processConditionReport(_cond,_vars, _cov);
        processTestCondition(_vars, _cond);
    }

    private static void processLabelledConditionReport(VariablesOffsets _vars, ConditionBlock _cond, Coverage _cov, int _length) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond,_cond);
            String tag_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _vars.addPart(new PartOffset(tag_, off_));
            _vars.addPart(new PartOffset(ExportCst.END_SPAN, off_ + _length));
        }
        processConditionReport(_cond,_vars, _cov);
        refLabel(_vars, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars, _cond);
    }

    private static void processTestCondition(VariablesOffsets _vars, ConditionBlock _cond) {
        if (_vars.goesToProcess()) {
            return;
        }
        AnaTypeFct function_ = _cond.getFunction();
        if (function_ != null) {
            StringList list_ = new StringList();
            addParts(_vars, function_,_cond.getTestOffset(),1, list_,list_);
        }
        if (_vars.isImplicit()) {
            function_ = _cond.getFunctionImpl();
            int off_ = _cond.getTestOffset();
            if (function_ != null) {
                StringList list_ = new StringList();
                addParts(_vars, function_,off_,1, list_,list_);
            }
        }


    }
    private static void processForMutableIterativeLoopReport(VariablesOffsets _vars, ForMutableIterativeLoop _cond, Coverage _cov) {
        OperationNode rootExp_ = _cond.getRootExp();
        headForMutableReport(_vars, _cond, _cov, rootExp_);
        OperationNode rootInit_ = _cond.getRootInit();
        if (_vars.getLastStackElt().getIndexLoop() == 0) {
            if (rootInit_ != null) {
                int off_ = _cond.getInitOffset();
                int offsetEndBlock_ = off_ + _cond.getInit().length();
                LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1);
                buildCoverageReport(_vars, _cov, rootInit_, in_);
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
                LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 1, -1);
                buildCoverageReport(_vars, _cov, rootExp_, in_);
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
            LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 2, -1);
            buildCoverageReport(_vars, _cov, rootStep_, in_);
            if (_vars.goesToProcess()) {
                return;
            }
        }
        _vars.getLastStackElt().setIndexLoop(0);
        refLabel(_vars, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars,_cond);
    }

    private static void headForMutableReport(VariablesOffsets _vars, ForMutableIterativeLoop _cond, Coverage _cov, OperationNode _rootExp) {
        if (_vars.getLastStackElt().noVisited()) {
            if (_rootExp != null) {
                AbstractCoverageResult result_ = _cov.getCoversConditions(_cond,_cond);
                String tag_ = headCoverage(result_);
                int off_ = _cond.getOffset();
                _vars.addPart(new PartOffset(tag_, off_));
                _vars.addPart(new PartOffset(ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordFor().length()));
            }
            appendVars(_vars, _cond);
        }
    }

    private static void processForMutableIterativeLoopError(VariablesOffsets _vars, ForMutableIterativeLoop _cond) {
        headForMutableError(_vars, _cond);
        if (_vars.getLastStackElt().getIndexLoop() == 0) {
            OperationNode rootInit_ = _cond.getRootInit();
            if (rootInit_ != null) {
                int off_ = _cond.getInitOffset();
                buildNormalError(_vars, _cond, -1, rootInit_, off_, 0, off_+_cond.getInit().length());
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
                buildNormalError(_vars, _cond, -1, rootExp_, off_, 1, off_+_cond.getExpression().length());
                if (_vars.goesToProcess()) {
                    return;
                }
            }
            _vars.getLastStackElt().setIndexLoop(2);
        }
        OperationNode rootStep_ = _cond.getRootStep();
        if (rootStep_ != null) {
            int off_ = _cond.getStepOffset();
            buildNormalError(_vars, _cond, -1, rootStep_, off_, 2, off_+_cond.getStep().length());
            if (_vars.goesToProcess()) {
                return;
            }
        }
        _vars.getLastStackElt().setIndexLoop(0);
        refLabelError(_vars, _cond, _cond.getLabel(), _cond.getLabelOffset());
        processTestCondition(_vars,_cond);
    }

    private static void headForMutableError(VariablesOffsets _vars, ForMutableIterativeLoop _cond) {
        if (_vars.getLastStackElt().noVisited()) {
            appendVars(_vars, _cond);
        }
    }

    private static void processTestCondition(VariablesOffsets _vars, ForMutableIterativeLoop _cond) {
        AnaTypeFct function_ = _cond.getFunction();
        if (function_ != null) {
            StringList list_ = new StringList();
            addParts(_vars, function_,_cond.getTestOffset(),1, list_,list_);
        }
        function_ = _cond.getFunctionImpl();
        if (_vars.isImplicit()&&function_ != null) {
            StringList list_ = new StringList();
            addParts(_vars, function_,_cond.getTestOffset(),1, list_,list_);
        }
    }
    private static void appendVars(VariablesOffsets _vars, ForMutableIterativeLoop _cond) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        String clName_ = _cond.getClassName().trim();
        String errInf_ = _cond.getErrInf();
        String importedClassName_ = _cond.getImportedClassName();
        int classNameOffset_ = _cond.getClassNameOffset();
        CustList<PartOffset> partOffsets_ = export(_cond.getPartOffsets());
        procInferVar(_vars, keyWordVar_, clName_, errInf_, importedClassName_, classNameOffset_, partOffsets_);
    }

    private static void processSwitchBlockReport(VariablesOffsets _vars, SwitchBlock _cond, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            String part_ = headCoverage(_cov, _cond);
            int off_ = _cond.getOffset();
            _vars.addPart(new PartOffset(part_, off_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR + ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordSwitch().length()));
        }
        int off_ = _cond.getValueOffset();
        int offsetEndBlock_ = off_ + _cond.getValue().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1);
        buildCoverageReport(_vars, _cov, root_, in_);
        refLabel(_vars, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static String headCoverage(Coverage _cov,MemberCallingsBlock _sw) {
        SwitchCoverageResult sw_ = _cov.coverSwitchsMethod(_sw);
        return headCoverage(sw_.noDefault(),sw_.getChildren());
    }

    private static String headCoverage(Coverage _cov,SwitchBlock _sw) {
        SwitchCoverageResult sw_ = _cov.coverSwitchs(_sw);
        return headCoverage(sw_.noDefault(),sw_.getChildren());
    }
    private static String headCoverage(AbstractCoverageResult _noDef, IdMap<ExecBlock, CustList<AbstractCoverageResult>> _coverSwitchs) {
        int full_ = 0;
        int count_ = 0;
        for (CustList<AbstractCoverageResult> e : _coverSwitchs.values()) {
            for (AbstractCoverageResult f: e) {
                count_ += f.getCovered();
                full_ += f.getFull();
            }
        }
        if (_noDef != null) {
            count_ += _noDef.getCovered();
            full_ += _noDef.getFull();
        }
        String tag_ = headCoverage(full_, count_);
        return tag_ + ExportCst.anchor(count_ + ExportCst.RATIO_COVERAGE + full_);
    }

    private static void processSwitchBlockError(VariablesOffsets _vars, SwitchBlock _cond) {
        int off_ = _cond.getValueOffset();
        if (_vars.getLastStackElt().noVisited() && !_cond.getErr().isEmpty()) {
            _vars.addPart(new PartOffset(ExportCst.anchorErr(_cond.getErr()), off_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, off_ + 1));
        }
        OperationNode root_ = _cond.getRoot();
        buildNormalError(_vars, _cond, -1, root_, off_, 0, off_+_cond.getValue().length());
        refLabelError(_vars, _cond, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processCaseConditionReport(VariablesOffsets _vars, CaseCondition _cond, Coverage _cov) {
        CustList<AbstractCoverageResult> result_ = _cov.getCoverSwitchs(_cond);
        String tag_ = getCaseDefaultTag(result_);
        if (_vars.getLastStackElt().noVisited()) {
            int off_ = _cond.getOffset();
            _vars.addPart(new PartOffset(tag_, off_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR+ExportCst.END_SPAN,off_+ _vars.getKeyWords().getKeyWordCase().length()));
        }
        EnumBlock enumBlock_ = _cond.getEnumBlock();
        if (enumBlock_ != null) {
            addEnumRef(_vars, _cond, enumBlock_);
        } else if (!_cond.getImportedType().isEmpty()) {
            if (_vars.getLastStackElt().noVisited()) {
                _vars.addParts(export(_cond.getPartOffsets()));
                String variableName_ = _cond.getVariableName();
                int variableOffset_ = _cond.getVariableOffset();
                _vars.addPart(new PartOffset(ExportCst.anchorName(variableOffset_),variableOffset_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,variableOffset_+ variableName_.trim().length()));
            }
            OperationNode root_ = _cond.getRoot();
            if (root_ != null) {
                int offsetEndBlock_ = _cond.getValueOffset() + _cond.getValue().length();
                LinkageStackElementIn in_ = buildLinkageRep(_cond, _cond.getConditionOffset(), offsetEndBlock_, 0, -1);
                buildCoverageReport(_vars, _cov, root_, in_);
            }
        } else {
            int offsetEndBlock_ = _cond.getValueOffset() + _cond.getValue().length();
            OperationNode root_ = _cond.getRoot();
            LinkageStackElementIn in_ = buildLinkageRep(_cond, _cond.getValueOffset(), offsetEndBlock_, 0, -1);
            buildCoverageReport(_vars, _cov, root_, in_);
        }
    }

    private static String getCaseDefaultTag(CustList<AbstractCoverageResult> _result) {
        int full_ = 0;
        int count_ = 0;
        CustList<String> indexes_ = new CustList<String>();
        int i_ = 0;
        for (AbstractCoverageResult c: _result) {
            count_ += c.getCovered();
            full_ += c.getFull();
            if (c.isFullCovered()) {
                indexes_.add(Integer.toString(i_));
            }
            i_++;
        }
        String supp_;
        if (!indexes_.isEmpty() && count_ < full_) {
            supp_ = LINE_RET+StringUtil.join(indexes_,ExportCst.FOUND_COVERAGE);
        } else {
            supp_ = "";
        }
        return headCoverage(full_,count_) + ExportCst.anchor(count_ + ExportCst.RATIO_COVERAGE + full_ +supp_);
    }

    private static void processCaseConditionError(VariablesOffsets _vars, CaseCondition _cond) {
        EnumBlock enumBlock_ = _cond.getEnumBlock();
        if (enumBlock_ != null) {
            addEnumRef(_vars, _cond, enumBlock_);
        } else if (!_cond.getImportedType().isEmpty()) {
            if (_vars.getLastStackElt().noVisited()) {
                _vars.addParts(export(_cond.getPartOffsets()));
                String variableName_ = _cond.getVariableName();
                int variableOffset_ = _cond.getVariableOffset();
                StringList errs_ = _cond.getNameErrors();
                if (!errs_.isEmpty()) {
                    _vars.addPart(new PartOffset(ExportCst.anchorNameErr(variableOffset_,StringUtil.join(errs_,ExportCst.JOIN_ERR)), variableOffset_));
                } else {
                    _vars.addPart(new PartOffset(ExportCst.anchorName(variableOffset_),variableOffset_));
                }
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, variableOffset_ + variableName_.trim().length()));
            }
            OperationNode root_ = _cond.getRoot();
            if (root_ != null) {
                buildNormalError(_vars, _cond, -1, root_, _cond.getConditionOffset(), 0, _cond.getValueOffset() + _cond.getValue().length());
            }
        } else {
            int off_ = _cond.getValueOffset();
            OperationNode root_ = _cond.getRoot();
            buildNormalError(_vars, _cond, -1, root_, off_, 0, off_ + _cond.getValue().length());
        }
    }

    private static void addEnumRef(VariablesOffsets _vars, CaseCondition _cond, EnumBlock _enumBlock) {
        StringList errs_ = new StringList();
        int off_ = _cond.getValueOffset();
        String typeEnum_ = _cond.getTypeEnum();
        for (IndexStrPart i : _cond.getOffsetsEnum().getValues()) {
            String part_ = i.getPart();
            int length_ = part_.length();
            int begin_ = i.getIndex() + off_;
            ClassField id_ = new ClassField(typeEnum_, part_);
            for (InnerTypeOrElement f : _enumBlock.getEnumBlocks()) {
                if (StringUtil.contains(f.getElements().getFieldName(), part_)) {
                    updateFieldAnchor(_vars, _enumBlock, errs_, id_, begin_, length_, f.getFieldNameOffset());
                    break;
                }
            }
        }
    }

    private static void processDefaultConditionReport(VariablesOffsets _vars, DefaultCondition _cond, Coverage _cov) {
        CustList<AbstractCoverageResult> result_ = _cov.getCoverSwitchs(_cond);
        String tag_ = getCaseDefaultTag(result_);
        int off_ = _cond.getOffset();
        _vars.addPart(new PartOffset(tag_,off_));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR+ExportCst.END_SPAN,off_+ _vars.getKeyWords().getKeyWordDefault().length()));
        if (!_cond.getVariableName().trim().isEmpty()) {
            off_ = _cond.getVariableOffset();
            _vars.addPart(new PartOffset(ExportCst.anchorName(off_,_cond.getInstanceTest()),off_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,off_+_cond.getVariableName().length()));
        }
    }

    private static void processDefaultConditionError(VariablesOffsets _vars, DefaultCondition _cond) {
        if (!_cond.getVariableName().trim().isEmpty()) {
            StringList errs_ = _cond.getNameErrors();
            if (!errs_.isEmpty()) {
                int off_ = _cond.getVariableOffset();
                String tag_ = ExportCst.anchorNameErr(off_ ,StringUtil.join(errs_,ExportCst.JOIN_ERR));
                _vars.addPart(new PartOffset(tag_, off_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, off_ + _cond.getVariableName().trim().length()));
            } else {
                int off_ = _cond.getVariableOffset();
                String tag_ = ExportCst.anchorName(off_,_cond.getInstanceTest());
                _vars.addPart(new PartOffset(tag_,off_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,off_+_cond.getVariableName().length()));
            }
        }
    }

    private static void processDoBlockReport(VariablesOffsets _vars, DoBlock _cond) {
        refLabel(_vars, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void processDoBlockError(VariablesOffsets _vars, DoBlock _cond) {
        refLabelError(_vars, _cond, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processDoWhileConditionReport(VariablesOffsets _vars, DoWhileCondition _cond, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoversConditions(_cond,_cond);
            String tag_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _vars.addPart(new PartOffset(tag_, off_));
            _vars.addPart(new PartOffset(ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordWhile().length()));
        }
        processConditionReport(_cond,_vars, _cov);
        processTestCondition(_vars, _cond);
    }
    private static void processTryEvalReport(VariablesOffsets _vars, TryEval _cond) {
        refLabel(_vars, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processTryEvalError(VariablesOffsets _vars, TryEval _cond) {
        refLabelError(_vars, _cond, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processCatchEvalReport(VariablesOffsets _vars, CatchEval _cond, Coverage _cov) {
        processAbstractCatchEvalReport(_vars,_cond, _cov);
        _vars.addParts(export(_cond.getPartOffsets()));
        String tag_ = ExportCst.anchorName(_cond.getVariableNameOffset());
        _vars.addPart(new PartOffset(tag_, _cond.getVariableNameOffset()));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
    }
    private static void processCatchEvalError(VariablesOffsets _vars, CatchEval _cond) {
        _vars.addParts(export(_cond.getPartOffsets()));
        StringList errs_ = _cond.getNameErrors();
        if (!errs_.isEmpty()) {
            String tag_ = ExportCst.anchorNameErr(_cond.getVariableNameOffset(),StringUtil.join(errs_,ExportCst.JOIN_ERR));
            _vars.addPart(new PartOffset(tag_, _cond.getVariableNameOffset()));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            return;
        }
        String tag_ = ExportCst.anchorName(_cond.getVariableNameOffset());
        _vars.addPart(new PartOffset(tag_, _cond.getVariableNameOffset()));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
    }
    private static void processAbstractCatchEvalReport(VariablesOffsets _vars, AbstractCatchEval _cond, Coverage _cov) {
        String tag_;
        if (_cov.getCatches(_cond)) {
            tag_ = ExportCst.span(FULL);
        } else {
            tag_ = ExportCst.span(NONE);
        }
        int off_ = _cond.getOffset();
        _vars.addPart(new PartOffset(tag_,off_));
        _vars.addPart(new PartOffset(ExportCst.END_SPAN,off_+ _vars.getKeyWords().getKeyWordCatch().length()));
    }
    private static void processDeclareVariableReport(VariablesOffsets _vars, DeclareVariable _cond) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(_cond.getClassName().trim(), keyWordVar_)) {
            String tag_ = ExportCst.bold(_cond.getImportedClassName());
            _vars.addPart(new PartOffset(tag_, _cond.getClassNameOffset()));
            _vars.addPart(new PartOffset(ExportCst.END_BOLD, _cond.getClassNameOffset() + keyWordVar_.length()));
        } else {
            _vars.addParts(export(_cond.getPartOffsets()));
        }
    }
    private static void processDeclareVariableError(VariablesOffsets _vars, DeclareVariable _cond) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        String clName_ = _cond.getClassName().trim();
        String errInf_ = _cond.getErrInf();
        String importedClassName_ = _cond.getImportedClassName();
        int classNameOffset_ = _cond.getClassNameOffset();
        CustList<PartOffset> partOffsets_ = export(_cond.getPartOffsets());
        procInferVar(_vars, keyWordVar_, clName_, errInf_, importedClassName_, classNameOffset_, partOffsets_);
    }

    private static void procInferVar(VariablesOffsets _vars, String _keyWordVar, String _clName, String _errInf, String _importedClassName, int _classNameOffset, CustList<PartOffset> _partOffsets) {
        if (StringUtil.quickEq(_clName, _keyWordVar)) {
            String tag_;
            if (_errInf.isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.bold(_importedClassName), _classNameOffset));
                tag_ = ExportCst.END_BOLD;
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(_errInf), _classNameOffset));
                tag_ = ExportCst.END_ANCHOR;
            }
            _vars.addPart(new PartOffset(tag_, _classNameOffset + _keyWordVar.length()));
        } else {
            _vars.addParts(_partOffsets);
        }
    }

    private static void processLineReport(VariablesOffsets _vars, Line _cond, Coverage _cov) {
        int blOffset_ = _cond.getExpressionOffset();
        int endBl_ = blOffset_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, blOffset_, endBl_, 0, -1);
        buildCoverageReport(_vars, _cov, root_, in_);
    }
    private static void processLineError(VariablesOffsets _vars, Line _cond) {
        int blOffset_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormalError(_vars, _cond, -1, root_, blOffset_, 0,blOffset_+_cond.getExpression().length());
    }
    private static void processBreakBlockReport(VariablesOffsets _vars, BreakBlock _cond) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        _vars.addPart(new PartOffset(ExportCst.anchorRef(_cond.getLabelOffsetRef()), _cond.getLabelOffset()));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processBreakBlockError(VariablesOffsets _vars, BreakBlock _cond) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        StringList errs_ = _cond.getErrorsRefLabels();
        if (!errs_.isEmpty()) {
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), _cond.getLabelOffset()));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
            return;
        }
        _vars.addPart(new PartOffset(ExportCst.anchorRef(_cond.getLabelOffsetRef()), _cond.getLabelOffset()));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processContinueBlockReport(VariablesOffsets _vars, ContinueBlock _cond) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        _vars.addPart(new PartOffset(ExportCst.anchorRef(_cond.getLabelOffsetRef()), _cond.getLabelOffset()));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processContinueBlockError(VariablesOffsets _vars, ContinueBlock _cond) {
        if (_cond.getLabel().isEmpty()) {
            return;
        }
        StringList errs_ = _cond.getErrorsRefLabels();
        if (!errs_.isEmpty()) {
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), _cond.getLabelOffset()));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
            return;
        }
        _vars.addPart(new PartOffset(ExportCst.anchorRef(_cond.getLabelOffsetRef()), _cond.getLabelOffset()));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getLabelOffset() +_cond.getLabel().length()));
    }
    private static void processReturnMethodReport(VariablesOffsets _vars, ReturnMethod _cond, Coverage _cov) {
        if (_cond.isEmpty()) {
            return;
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1);
        buildCoverageReport(_vars, _cov, root_, in_);
    }

    private static void processReturnMethodError(VariablesOffsets _vars, ReturnMethod _cond) {
        if (_cond.isEmpty()) {
            return;
        }
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormalError(_vars, _cond, -1, root_, off_, 0, off_ + _cond.getExpression().length());
    }
    private static void processThrowingReport(VariablesOffsets _vars, Throwing _cond, Coverage _cov) {
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1);
        buildCoverageReport(_vars, _cov, root_, in_);
    }

    private static void processThrowingError(VariablesOffsets _vars, Throwing _cond) {
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormalError(_vars, _cond, -1, root_, off_, 0, off_ + _cond.getExpression().length());
    }
    private static void processForIterativeLoopReport(VariablesOffsets _vars, ForIterativeLoop _cond, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoverLoops(_cond);
            String tag_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _vars.addPart(new PartOffset(tag_,off_));
            _vars.addPart(new PartOffset(ExportCst.END_SPAN,off_+ _vars.getKeyWords().getKeyWordIter().length()));
            _vars.addPart(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffset()), _cond.getVariableNameOffset()));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        int off_ = _cond.getInitOffset();
        int offsetEndBlock_ = off_ + _cond.getInit().length();
        if (_vars.getLastStackElt().getIndexLoop() == 0) {
            OperationNode rootInit_ = _cond.getRootInit();
            LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1);
            buildCoverageReport(_vars, _cov, rootInit_, in_);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexLoop(1);
        }
        off_ = _cond.getExpressionOffset();
        offsetEndBlock_ = off_ + _cond.getExpression().length();
        if (_vars.getLastStackElt().getIndexLoop() == 1) {
            OperationNode rootExp_ = _cond.getRootExp();
            LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 1, -1);
            buildCoverageReport(_vars, _cov, rootExp_, in_);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexLoop(2);
        }
        off_ = _cond.getStepOffset();
        offsetEndBlock_ = off_ + _cond.getStep().length();
        OperationNode rootStep_ = _cond.getRootStep();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 2, -1);
        buildCoverageReport(_vars, _cov, rootStep_, in_);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexLoop(0);
        refLabel(_vars, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForIterativeLoopError(VariablesOffsets _vars, ForIterativeLoop _cond) {
        if (_vars.getLastStackElt().noVisited()) {
            StringList errs_ = _cond.getNameErrors();
            if (!errs_.isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_, ExportCst.JOIN_ERR)), _cond.getVariableNameOffset()));
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffset()), _cond.getVariableNameOffset()));
            }
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        int off_ = _cond.getInitOffset();
        if (_vars.getLastStackElt().getIndexLoop() == 0) {
            OperationNode rootInit_ = _cond.getRootInit();
            buildNormalError(_vars, _cond, -1, rootInit_, off_, 0, off_ + _cond.getInit().length());
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexLoop(1);
        }
        off_ = _cond.getExpressionOffset();
        if (_vars.getLastStackElt().getIndexLoop() == 1) {
            OperationNode rootExp_ = _cond.getRootExp();
            buildNormalError(_vars, _cond, -1, rootExp_, off_, 1, off_ + _cond.getExpression().length());
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexLoop(2);
        }
        off_ = _cond.getStepOffset();
        OperationNode rootStep_ = _cond.getRootStep();
        buildNormalError(_vars, _cond, -1, rootStep_, off_, 2, off_ + _cond.getStep().length());
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexLoop(0);
        refLabelError(_vars, _cond, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForEachLoopReport(VariablesOffsets _vars, ForEachLoop _cond, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoverLoops(_cond);
            String tagCov_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _vars.addPart(new PartOffset(tagCov_, off_));
            appendVars(_vars,_cond);
            _vars.addPart(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffset()), _cond.getVariableNameOffset()));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            _vars.addPart(new PartOffset(ExportCst.END_SPAN, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1);
        buildCoverageReport(_vars, _cov, root_, in_);
        refLabel(_vars, _cond.getLabel(), _cond.getLabelOffset());
    }
    private static void processForEachLoopError(VariablesOffsets _vars, ForEachLoop _cond) {
        if (_vars.getLastStackElt().noVisited()) {
            appendVars(_vars,_cond);
            StringList errs_ = _cond.getNameErrors();
            if (!errs_.isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), _cond.getVariableNameOffset()));
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffset()), _cond.getVariableNameOffset()));
            }
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffset() + _cond.getVariableName().length()));
            if (!_cond.getSepErrors().isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_cond.getSepErrors(),ExportCst.JOIN_ERR)), _cond.getSepOffset()));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getSepOffset() + 1));
            }
        }
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormalError(_vars, _cond, -1, root_, off_, 0, off_ + _cond.getExpression().length());
        refLabelError(_vars, _cond, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendVars(VariablesOffsets _vars, ForEachLoop _cond) {
        KeyWords keyWords_ = _vars.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringUtil.quickEq(_cond.getClassName().trim(), keyWordVar_)) {
            String tag_ = ExportCst.bold(_cond.getImportedClassName());
            _vars.addPart(new PartOffset(tag_, _cond.getClassNameOffset()));
            _vars.addPart(new PartOffset(ExportCst.END_BOLD, _cond.getClassNameOffset() + keyWordVar_.length()));
        } else {
            _vars.addParts(export(_cond.getPartOffsets()));
        }
    }

    private static void processForEachTableReport(VariablesOffsets _vars, ForEachTable _cond, Coverage _cov) {
        if (_vars.getLastStackElt().noVisited()) {
            AbstractCoverageResult result_ = _cov.getCoverLoops(_cond);
            String tagCov_ = headCoverage(result_);
            int off_ = _cond.getOffset();
            _vars.addPart(new PartOffset(tagCov_, off_));
            KeyWords keyWords_ = _vars.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            appendFirstVar(_vars, _cond, keyWordVar_);
            _vars.addPart(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffsetFirst()), _cond.getVariableNameOffsetFirst()));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
            appendSecondVar(_vars, _cond, keyWordVar_);
            _vars.addPart(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffsetSecond()), _cond.getVariableNameOffsetSecond()));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
            _vars.addPart(new PartOffset(ExportCst.END_SPAN, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
        }
        int off_ = _cond.getExpressionOffset();
        int offsetEndBlock_ = off_ + _cond.getExpression().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1);
        buildCoverageReport(_vars, _cov, root_, in_);
        refLabel(_vars, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendSecondVar(VariablesOffsets _vars, ForEachTable _cond, String _keyWordVar) {
        if (StringUtil.quickEq(_cond.getClassNameSecond().trim(), _keyWordVar)) {
            String tag_ = ExportCst.bold(_cond.getImportedClassNameSecond());
            _vars.addPart(new PartOffset(tag_, _cond.getClassNameOffsetSecond()));
            _vars.addPart(new PartOffset(ExportCst.END_BOLD, _cond.getClassNameOffsetSecond() + _keyWordVar.length()));
        } else {
            _vars.addParts(export(_cond.getPartOffsetsSecond()));
        }
    }

    private static void processForEachTableError(VariablesOffsets _vars, ForEachTable _cond) {
        if (_vars.getLastStackElt().noVisited()) {
            KeyWords keyWords_ = _vars.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            appendFirstVar(_vars, _cond, keyWordVar_);
            StringList errs_ = _cond.getNameErrorsFirst();
            if (!errs_.isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_, ExportCst.JOIN_ERR)), _cond.getVariableNameOffsetFirst()));
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffsetFirst()), _cond.getVariableNameOffsetFirst()));
            }
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffsetFirst() + _cond.getVariableNameFirst().length()));
            appendSecondVar(_vars, _cond, keyWordVar_);
            errs_ = _cond.getNameErrorsSecond();
            if (!errs_.isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_, ExportCst.JOIN_ERR)), _cond.getVariableNameOffsetSecond()));
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorName(_cond.getVariableNameOffsetSecond()), _cond.getVariableNameOffsetSecond()));
            }
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getVariableNameOffsetSecond() + _cond.getVariableNameSecond().length()));
            if (!_cond.getSepErrors().isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_cond.getSepErrors(), ExportCst.JOIN_ERR)), _cond.getSepOffset()));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getSepOffset() + 1));
            }
        }
        int off_ = _cond.getExpressionOffset();
        OperationNode root_ = _cond.getRoot();
        buildNormalError(_vars, _cond, -1, root_, off_, 0, off_ + _cond.getExpression().length());
        refLabelError(_vars, _cond, _cond.getLabel(), _cond.getLabelOffset());
    }

    private static void appendFirstVar(VariablesOffsets _vars, ForEachTable _cond, String _keyWordVar) {
        if (StringUtil.quickEq(_cond.getClassNameFirst().trim(), _keyWordVar)) {
            String tag_ = ExportCst.bold(_cond.getImportedClassNameFirst());
            _vars.addPart(new PartOffset(tag_, _cond.getClassNameOffsetFirst()));
            _vars.addPart(new PartOffset(ExportCst.END_BOLD, _cond.getClassNameOffsetFirst() + _keyWordVar.length()));
        } else {
            _vars.addParts(export(_cond.getPartOffsetsFirst()));
        }
    }

    private static void processElementBlockReport(VariablesOffsets _vars, ElementBlock _cond, Coverage _cov) {
        processEnumEltReport(_vars, _cond.getAnnotations(),_cond.getElementContent(),_cond,_cond,_cov,_cond.getRes().getSumOffset());
    }

    private static void processEnumEltReport(VariablesOffsets _vars, ResultParsedAnnots _annot, AnaElementContent _content, AbsBk _cond, InnerTypeOrElement _inner, Coverage _cov, int _sumOffset) {
        AbstractInstancingOperation inst_ = (AbstractInstancingOperation) _inner.getRoot().getFirstChild().getNextSibling();
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            annotReport(_vars, _cond, _cov, _annot);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = 0;
        }
        int afterName_ = _content.getFieldNameOffest();
        int lastChar_ = _cond.getBegin();
        int diffBegin_ = afterName_ - _sumOffset - inst_.getIndexInEl();
        String value_ = _content.getValue();
        int endBl_ = _sumOffset + value_.length();
        int diffEnd_ = lastChar_ - endBl_;
        LinkageStackElementIn in_ = buildLinkageRep(diffBegin_,diffEnd_,_cond, _sumOffset, endBl_, 0, k_);
        buildCoverageReport(_vars, _cov, inst_, in_);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void processElementBlockError(VariablesOffsets _vars, ElementBlock _cond) {
        processElementBlockErr(_vars, _cond,_cond.getNameErrors(),_cond.getElementContent(),_cond,_cond.getAnnotations(),_cond.getRes().getSumOffset());
    }

    private static void processElementBlockErr(VariablesOffsets _vars, AbsBk _cond, StringList _errFields, AnaElementContent _content, InnerTypeOrElement _inn, ResultParsedAnnots _annotations, int _sumOffset) {
        OperationNode firstChild_ = _inn.getRoot().getFirstChild();
        StringList errs_ = new StringList();
        OperationNode next_ = fetchNext(firstChild_, errs_);
        addNextErrs(errs_, next_);
        AbstractInstancingOperation inst_ = asInstancing(next_);
        String uniqueFieldName_ = _inn.getUniqueFieldName();
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            annotError(_vars, _cond, _annotations);
            if (_vars.goesToProcess()) {
                return;
            }
            StringList mergedErrs_ = new StringList(_errFields);
            if (uniqueFieldName_.trim().isEmpty()) {
                String err_ = getLineErr(mergedErrs_);
                String tag_ = ExportCst.anchorNameErr(_content.getFieldNameOffest(), err_);
                _vars.addPart(new PartOffset(tag_, _content.getFieldNameOffest()));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _content.getFieldNameOffest() + 1));
                _vars.getLastStackElt().setIndexAnnotationGroup(-1);
                return;
            }
            if (inst_ == null) {
                errs_.addAllElts(_inn.getRoot().getErrs());
                mergedErrs_.addAllElts(errs_);
                String err_ = getLineErr(mergedErrs_);
                String tag_ = ExportCst.anchorNameErr(_content.getFieldNameOffest(), err_);
                _vars.addPart(new PartOffset(tag_, _content.getFieldNameOffest()));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _content.getFieldNameOffest() + uniqueFieldName_.trim().length()));
                _vars.getLastStackElt().setIndexAnnotationGroup(-1);
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = 0;
        }
        buildNormalError(_vars, _cond, k_, inst_, _sumOffset, 0, _sumOffset + _content.getValue().length());
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
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

    private static void nameId(VariablesOffsets _vars, AbstractInstancingOperation _inst, String _uniqueFieldName, StringList _errs, int _fieldNameOffest) {
        String err_ = getLineErr(_errs);
        AnaTypeFct ctor_ = _inst.getConstructor();
        if (ctor_ != null) {
            addParts(_vars, ctor_, _fieldNameOffest, _uniqueFieldName.length(), _errs, _errs, _fieldNameOffest);
        } else {
            String tag_;
            if (!_errs.isEmpty()) {
                tag_ = ExportCst.anchorNameErr(_fieldNameOffest, err_);
            } else {
                tag_ = ExportCst.anchorName(_fieldNameOffest);
            }
            _vars.addPart(new PartOffset(tag_, _fieldNameOffest));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _fieldNameOffest + _uniqueFieldName.length()));
        }
    }
    private static void processFieldBlockReport(VariablesOffsets _vars, FieldBlock _cond, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotField(_vars, _cond, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            _vars.addParts(export(_cond.getTypePartOffsets()));
        }
        int blOffset_ = _cond.getValueOffset();
        int endBl_ = blOffset_ + _cond.getValue().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, blOffset_, endBl_, 0, k_);
        buildCoverageReport(_vars, _cov, root_, in_);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }
    private static void processFieldBlockError(VariablesOffsets _vars, FieldBlock _cond) {
        int blOffset_ = _cond.getValueOffset();
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotFieldErr(_vars, _cond);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            _vars.addParts(export(_cond.getTypePartOffsets()));
            StringList errs_ = _cond.getNameRetErrors();
            if (!errs_.isEmpty()) {
                String err_ = StringUtil.join(errs_, ExportCst.JOIN_ERR);
                if (_cond.getValue().trim().isEmpty()) {
                    blOffset_ = _cond.getClassNameOffset() + _cond.getClassName().length();
                }
                _vars.addPart(new PartOffset(ExportCst.anchorErr(err_), blOffset_));
                int endBl_ = blOffset_ + Math.max(1, _cond.getValue().length());
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, endBl_));
                _vars.getLastStackElt().setIndexAnnotationGroup(-1);
                return;
            }
        }
        OperationNode root_ = _cond.getRoot();
        buildNormalError(_vars, _cond, k_, root_, blOffset_, 0, blOffset_+ _cond.getValue().length());
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void buildAnnotField(VariablesOffsets _vars, FieldBlock _cond, Coverage _cov) {
        ResultParsedAnnots annotations_ = _cond.getAnnotations();
        annotReport(_vars, _cond, _cov, annotations_);
    }
    private static void buildAnnotFieldErr(VariablesOffsets _vars, FieldBlock _cond) {
        annotError(_vars, _cond, _cond.getAnnotations());
    }

    private static void annotError(VariablesOffsets _vars, AbsBk _cond, ResultParsedAnnots _annotations) {
        int len_ = _annotations.getAnnotations().size();
        int j_ = _vars.getLastStackElt().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            buildAnnotErr(_vars, _cond, -1, i,_annotations);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotation(i+1);
        }
        _vars.getLastStackElt().setIndexAnnotation(0);
    }

    private static void processConstructorBlockReport(VariablesOffsets _vars, ConstructorBlock _cond, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsReport(_vars, _cond, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            int begName_ = _cond.getNameOffset();
            _vars.addPart(new PartOffset(ExportCst.anchorName(begName_),begName_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getLeftPar()));
        }
        refPar(_vars, _cond, _cov, k_);
    }
    private static void processConstructorBlockError(VariablesOffsets _vars, ConstructorBlock _cond) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsError(_vars, _cond);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            int begName_ = _cond.getNameOffset();
            StringList errsName_ = _cond.getNameErrors();
            if (errsName_.isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorName(begName_),begName_));
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorNameErr(begName_,StringUtil.join(errsName_,ExportCst.JOIN_ERR)),begName_));
            }
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getLeftPar()));
        }
        refParError(_vars,_cond, k_);
    }
    private static void processOverridableBlockReport(VariablesOffsets _vars, NamedFunctionBlock _cond, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsReport(_vars,_cond, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
        }
        int begName_ = _cond.getNameOffset();
        if (!AbsBk.isOverBlock(_cond) || ((NamedCalledFunctionBlock)_cond).getKind() == MethodKind.OPERATOR) {
            if (k_ == -1) {
                addNameParts(_vars,_cond, begName_, _cond.getName().length());
                appendOperImports(_vars,_cond);
                _vars.addParts(export(_cond.getPartOffsetsReturn()));
            }
            refParams(_vars,_cond, _cov);
            return;
        }
        processNamedOverridableBlockReport(_vars, _cond, _cov, k_, begName_);
    }

    private static void processNamedOverridableBlockReport(VariablesOffsets _vars, NamedFunctionBlock _cond, Coverage _cov, int _k, int _begName) {
        if (_k == -1) {
            _vars.addParts(export(_cond.getPartOffsetsReturn()));
        }
        NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) _cond;
        if (m_.getKind() == MethodKind.GET_INDEX) {
            if (_k == -1) {
                addNameParts(_vars,_cond, _begName, _vars.getKeyWords().getKeyWordThis().length());
            }
            refParams(_vars,_cond, _cov);
            return;
        }
        if (m_.getKind() == MethodKind.SET_INDEX) {
            if (_k == -1) {
                addNameParts(_vars,_cond, _begName, _vars.getKeyWords().getKeyWordThis().length());
            }
            refParams(_vars,_cond, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(_cond.getParametersNamesOffset().size());
            buildAnnotationsReportSupp(_vars,m_, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(-1);
            _vars.addParts(export(m_.getPartOffsetsReturnSetter()));
            return;
        }
        if (_k == -1) {
            addNameParts(_vars,_cond, _begName, _cond.getName().length());
        }
        refParams(_vars,_cond, _cov);
        if (_vars.goesToProcess()) {
            return;
        }
        processOverridableRedef(_vars,m_);
    }

    private static void processAnonymousFctReport(VariablesOffsets _vars, NamedCalledFunctionBlock _cond, Coverage _cov) {
        if (!_vars.getLastStackElt().isVisitedParams()) {
            refParams(_vars,_cond, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setVisitedParams(true);
        }
        buildAnnotationsReport(_vars,_cond, _cov);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.addParts(export(_cond.getPartOffsetsReturn()));
        int begName_ = _cond.getNameOffset();
        addNameParts(_vars,_cond, begName_, 2);
    }

    private static void processSwitchMethodReport(VariablesOffsets _vars, SwitchMethodBlock _cond, Coverage _cov) {
        if (_vars.getLastStackElt().isAnnotationMode()) {
            int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            if (k_ == -1) {
                buildAnnotationsReport(_vars,_cond, _cov);
                if (_vars.goesToProcess()) {
                    return;
                }
                _vars.getLastStackElt().setIndexAnnotationGroup(0);
            }
            refParams(_vars,_cond, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setStopVisit(true);
            return;
        }
        _vars.addPart(new PartOffset(ExportCst.span(TYPE), _cond.getBegin()));
    }


    private static void processOverridableBlockError(VariablesOffsets _vars, NamedFunctionBlock _cond) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsError(_vars,_cond);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
        }
        int begName_ = _cond.getNameOffset();
        if (!AbsBk.isOverBlock(_cond) || ((NamedCalledFunctionBlock)_cond).getKind() == MethodKind.OPERATOR) {
            if (k_ == -1) {
                addNameParts(_vars,_cond, begName_, _cond.getName().length());
                appendOperImports(_vars,_cond);
                _vars.addParts(export(_cond.getPartOffsetsReturn()));
            }
            refParamsError(_vars,_cond);
            return;
        }
        processNamedOverridableBlockError(_vars, _cond, k_, begName_);
    }

    private static void processNamedOverridableBlockError(VariablesOffsets _vars, NamedFunctionBlock _cond, int _k, int _begName) {
        if (_k == -1) {
            _vars.addParts(export(_cond.getPartOffsetsReturn()));
        }
        NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) _cond;
        if (m_.getKind() == MethodKind.GET_INDEX) {
            if (_k == -1) {
                addNameParts(_vars,_cond, _begName, _vars.getKeyWords().getKeyWordThis().length());
            }
            refParamsError(_vars,_cond);
            return;
        }
        if (m_.getKind() == MethodKind.SET_INDEX) {
            if (_k == -1) {
                addNameParts(_vars,_cond, _begName, _vars.getKeyWords().getKeyWordThis().length());
            }
            refParamsError(_vars,_cond);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(_cond.getParametersNamesOffset().size());
            buildAnnotationsErrorSupp(_vars,m_);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(-1);
            _vars.addParts(export(m_.getPartOffsetsReturnSetter()));
            return;
        }
        if (_k == -1) {
            addNameParts(_vars,_cond, _begName, _cond.getName().length());
        }
        refParamsError(_vars,_cond);
        if (_vars.goesToProcess()) {
            return;
        }
        processOverridableRedef(_vars,m_);
    }

    private static void appendOperImports(VariablesOffsets _vars, NamedFunctionBlock _cond) {
        if (_cond instanceof OperatorBlock) {
            OperatorBlock op_ = (OperatorBlock) _cond;
            int lenImp_ = op_.getImports().size();
            for (int i = 0; i < lenImp_; i++) {
                _vars.addPart(new PartOffset(ExportCst.span(IMPORT), op_.getImportsOffset().get(i)));
                _vars.addPart(new PartOffset(ExportCst.END_SPAN, op_.getImportsOffset().get(i) + op_.getImports().get(i).length()));
            }
        }
    }

    private static void processOverridableRedef(VariablesOffsets _vars, NamedCalledFunctionBlock _cond) {
        for (PartOffsetsClassMethodId p:_cond.getAllInternTypesParts()) {
            tryAppendParts(_vars, p);
        }
    }
    private static void processAnonymousFctBlockError(VariablesOffsets _vars, NamedCalledFunctionBlock _cond) {
        if (!_vars.getLastStackElt().isVisitedParams()) {
            refParamsError(_vars,_cond);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setVisitedParams(true);
        }
        buildAnnotationsError(_vars,_cond);
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.addParts(export(_cond.getPartOffsetsReturn()));
        StringList errs_ = new StringList(_cond.getErrorsBlock());
        AbsBk child_ = _cond.getFirstChild();
        if (isImplicitReturn(child_)){
            errs_.addAllElts(child_.getErrorsBlock());
        }
        int begName_ = _cond.getNameOffset();
        addNameParts(_vars,errs_,_cond, begName_, 2);
    }
    private static void processSwitchMethodError(VariablesOffsets _vars, SwitchMethodBlock _cond) {
        if (_vars.getLastStackElt().isAnnotationMode()) {
            int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            if (k_ == -1) {
                buildAnnotationsError(_vars,_cond);
                if (_vars.goesToProcess()) {
                    return;
                }
                _vars.getLastStackElt().setIndexAnnotationGroup(0);
            }
            refParamsError(_vars,_cond);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setStopVisit(true);
            return;
        }
        _vars.addPart(new PartOffset(ExportCst.span(TYPE), _cond.getBegin()));
    }

    private static void processAnnotationMethodBlockReport(VariablesOffsets _vars, NamedCalledFunctionBlock _cond, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsReport(_vars,_cond, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
            _vars.addParts(export(_cond.getPartOffsetsReturn()));
            int begName_ = _cond.getNameOffset();
            addNameParts(_vars,_cond, begName_, _cond.getName().length());
        }
        OperationNode root_ = _cond.getRoot();
        if (root_ != null) {
            int blOffset_ = _cond.getDefaultValueOffset();
            int endBl_ = blOffset_ + _cond.getDefaultValue().length();
            LinkageStackElementIn in_ = buildLinkageRep(_cond, blOffset_, endBl_, 0, k_);
            buildCoverageReport(_vars, _cov, root_, in_);
            if (_vars.goesToProcess()) {
                return;
            }
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void processAnnotationMethodBlockError(VariablesOffsets _vars, NamedCalledFunctionBlock _cond) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        if (k_ == -1) {
            buildAnnotationsError(_vars,_cond);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(0);
            k_ = 0;
            _vars.addParts(export(_cond.getPartOffsetsReturn()));
            if (_cond.getName().trim().isEmpty()) {
                int begName_ = _cond.getNameOffset()+ _cond.getRightPar();
                addNameParts(_vars,_cond, begName_,0);
            } else {
                int begName_ = _cond.getNameOffset();
                addNameParts(_vars,_cond, begName_, _cond.getName().length());
            }
        }
        OperationNode root_ = _cond.getRoot();
        if (root_ != null) {
            int blOffset_ = _cond.getDefaultValueOffset();
            buildNormalError(_vars, _cond, k_, root_, blOffset_, 0,blOffset_ + _cond.getDefaultValue().length());
            if (_vars.goesToProcess()) {
                return;
            }
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void buildNormalError(VariablesOffsets _vars, AbsBk _cond, int _k, OperationNode _root, int _begin, int _indexLoop, int _end) {
        LinkageStackElementIn in_ = buildLinkageErr(_cond, _k, -1,0, _indexLoop, -1, new LinkageStackElementOffsets(_begin, _end));
        buildErrorReport(_vars, _root, in_);
    }

    private static void addNameParts(VariablesOffsets _vars, NamedFunctionBlock _named, int _begName, int _len) {
        addNameParts(_vars,new StringList(),_named, _begName,_len);
    }
    private static void addNameParts(VariablesOffsets _vars, StringList _list, NamedFunctionBlock _named, int _begName, int _len) {
        StringList errs_ = new StringList(_named.getNameErrors());
        errs_.addAllElts(_list);
        if (errs_.isEmpty()) {
            int endName_ = _begName + _len;
            _vars.addPart(new PartOffset(ExportCst.anchorName(_begName),_begName));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,endName_));
            return;
        }
        int endName_ = _begName + Math.max(_len,1);
        _vars.addPart(new PartOffset(ExportCst.anchorNameErr(_begName,StringUtil.join(errs_,ExportCst.JOIN_ERR)),_begName));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,endName_));
    }
    private static void processInnerElementBlockReport(VariablesOffsets _vars, InnerElementBlock _cond, Coverage _cov) {
        processEnumEltReport(_vars, _cond.getAnnotations(),_cond.getElementContent(),_cond,_cond,_cov,_cond.getRes().getSumOffset());
    }

    private static void processInnerElementBlockError(VariablesOffsets _vars, InnerElementBlock _cond) {
        processElementBlockErr(_vars, _cond,_cond.getNameErrors(),_cond.getElementContent(),_cond,_cond.getAnnotations(),_cond.getRes().getSumOffset());
    }

    private static void addNextErrs(StringList _errs, OperationNode _next) {
        if (_next != null) {
            _errs.addAllElts(_next.getErrs());
        }
    }

    private static String getLineErr(StringList _list) {
        return StringUtil.join(_list,ExportCst.JOIN_ERR);
    }

    private static void processRootBlockReport(VariablesOffsets _vars, RootBlock _cond, Coverage _cov) {
        if (_vars.getLastStackElt().isAnnotationMode()) {
            processAnnotationReport(_vars,_cond, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setStopVisit(true);
            _vars.addParts(_vars.getLastStackElt().getPartsAfter());
            return;
        }
        if (_cond instanceof AnonymousTypeBlock) {
            _vars.addPart(new PartOffset(ExportCst.span(TYPE), _cond.getBegin()));
        } else {
            processAnnotationReport(_vars, _cond, _cov);
        }
        if (_vars.goesToProcess()) {
            return;
        }
        appendImportPart(_vars,_cond);
        processInterfaceInit(_vars,_cond);
        int idRowCol_ = _cond.getIdRowCol();
        _vars.addPart(new PartOffset(ExportCst.anchorName(idRowCol_), idRowCol_));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, idRowCol_ + _cond.getNameLength()));
        appendInhHeaders(_vars,_cond);
    }

    private static void appendInhHeaders(VariablesOffsets _vars, RootBlock _cond) {
        contraints(_vars,_cond);
        for (AnaResultPartType t: _cond.getResults()) {
            CustList<PartOffset> list_ = new CustList<PartOffset>();
            LinkagePartTypeUtil.processAnalyzeConstraintsRepParts(t, list_);
            _vars.addParts(list_);
        }
    }

    private static void contraints(VariablesOffsets _vars, RootBlock _s) {
        if (!(_s instanceof AnonymousTypeBlock)) {
            CustList<PartOffset> list_ = new CustList<PartOffset>();
            for (TypeVar t: _s.getParamTypes()) {
                varDef(t, list_);
                for (AnaResultPartType b: t.getResults()) {
                    LinkagePartTypeUtil.processAnalyzeConstraintsRepParts(b, list_);
                }
            }
            _vars.addParts(list_);
        }
    }
    private static void varDef(TypeVar _t, CustList<PartOffset> _constraintsParts) {
        if (_t.getErrors().isEmpty()) {
            _constraintsParts.add(new PartOffset(ExportCst.anchorName(_t.getOffset()),
                    _t.getOffset()));
        } else {
            String err_ = StringUtil.join(_t.getErrors(),ExportCst.JOIN_ERR);
            _constraintsParts.add(new PartOffset(ExportCst.anchorNameErr(_t.getOffset(),err_), _t.getOffset()));
        }
        _constraintsParts.add(new PartOffset(ExportCst.END_ANCHOR, _t.getOffset()+ _t.getLength()));
    }
    private static void appendImportPart(VariablesOffsets _vars, RootBlock _cond) {
        int len_ = _cond.getImports().size();
        for (int i = 0; i < len_; i++) {
            _vars.addPart(new PartOffset(ExportCst.span(IMPORT), _cond.getImportsOffset().get(i)));
            _vars.addPart(new PartOffset(ExportCst.END_SPAN, _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
    }

    private static void processRootBlockError(VariablesOffsets _vars, RootBlock _cond) {
        if (_vars.getLastStackElt().isAnnotationMode()) {
            processAnnotationError(_vars,_cond);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setStopVisit(true);
            _vars.addParts(_vars.getLastStackElt().getPartsAfter());
            return;
        }
        if (_cond instanceof AnonymousTypeBlock) {
            _vars.addPart(new PartOffset(ExportCst.span(TYPE), _cond.getBegin()));
        } else {
            processAnnotationError(_vars, _cond);
        }
        if (_vars.goesToProcess()) {
            return;
        }
        if (!(_cond instanceof RootErrorBlock)) {
            processRootHeaderError(_vars,_cond);
        }
        appendImportPart(_vars,_cond);
        processInterfaceInit(_vars,_cond);
        int nameLength_ = _cond.getNameLength();
        if (nameLength_ > 0) {
            StringList list_ = _cond.getNameErrors();
            int idRowCol_ = _cond.getIdRowCol();
            if (!list_.isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorNameErr(idRowCol_,StringUtil.join(list_,ExportCst.JOIN_ERR)), idRowCol_));
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorName(idRowCol_), idRowCol_));
            }
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, idRowCol_ + nameLength_));
        }
        appendInhHeaders(_vars,_cond);
        if ((_cond instanceof RootErrorBlock)) {
            processRootHeaderError(_vars,_cond);
        }
    }

    private static void processRootHeaderError(VariablesOffsets _vars, RootBlock _cond) {
        if (!_cond.getErrorsBlock().isEmpty()) {
            StringList listCat_ = _cond.getErrorsBlock();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(listCat_, ExportCst.JOIN_ERR)), _cond.getBegin()));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _cond.getBegin() + _cond.getLengthHeader()));
        }
    }

    private static void processInterfaceInit(VariablesOffsets _vars, RootBlock _cond) {
        if (_cond instanceof AnonymousTypeBlock) {
            return;
        }
        addTypes(_vars, _cond.getPartsStaticInitInterfacesOffset());
        addTypes(_vars, _cond.getPartsInstInitInterfacesOffset());
    }

    private static void processFileBlockReport(VariablesOffsets _vars, FileBlock _cond) {
        int len_ = _cond.getImports().size();
        for (int i = 0; i < len_; i++) {
            _vars.addPart(new PartOffset(ExportCst.span(IMPORT), _cond.getImportsOffset().get(i)));
            _vars.addPart(new PartOffset(ExportCst.END_SPAN, _cond.getImportsOffset().get(i)+ _cond.getImports().get(i).length()));
        }
    }
    private static void processAnnotationReport(VariablesOffsets _vars, RootBlock _cond, Coverage _cov) {
        ResultParsedAnnots annotations_ = _cond.getAnnotations();
        annotReport(_vars, _cond, _cov, annotations_);
    }

    private static void annotReport(VariablesOffsets _vars, AbsBk _cond, Coverage _cov, ResultParsedAnnots _annotations) {
        int len_ = _annotations.getAnnotations().size();
        int j_ = _vars.getLastStackElt().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            buildAnnotReport(_vars, _cond, _cov, _annotations, i, -1);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotation(i + 1);
        }
        _vars.getLastStackElt().setIndexAnnotation(0);
    }

    private static void buildAnnotReport(VariablesOffsets _vars, AbsBk _cond, Coverage _cov, ResultParsedAnnots _annotations, int _indexAnnotation, int _indexAnnotationGroup) {
        int begin_ = _annotations.getAnnotations().get(_indexAnnotation).getIndex();
        int end_ = begin_ + _annotations.getAnnotations().get(_indexAnnotation).getAnnotation().trim().length();
        LinkageStackElementIn in_ = buildLinkageErr(_cond, _indexAnnotationGroup, _indexAnnotationGroup, _indexAnnotation, 0, _indexAnnotation, new LinkageStackElementOffsets(0, 0, begin_, end_));
        buildCoverageReport(_vars, _cov, _annotations.getRoots().get(_indexAnnotation), in_);
    }

    private static void buildAnnotationsReport(VariablesOffsets _vars, NamedFunctionBlock _cond, Coverage _cov) {
        ResultParsedAnnots annotations_ = _cond.getAnnotations();
        annotReport(_vars, _cond, _cov, annotations_);
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars, NamedFunctionBlock _cond, int _index, Coverage _cov) {
        ResultParsedAnnots annotations_ = _cond.getAnnotationsParams().get(_index);
        annotMethReport(_vars, _cond, _cov, annotations_);
    }
    private static void buildAnnotationsReportSupp(VariablesOffsets _vars, NamedCalledFunctionBlock _cond, Coverage _cov) {
        ResultParsedAnnots annotations_ = _cond.getAnnotationsSupp();
        annotMethReport(_vars, _cond, _cov, annotations_);
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars, SwitchMethodBlock _cond, Coverage _cov) {
        ResultParsedAnnots annotations_ = _cond.getAnnotations();
        annotReport(_vars, _cond, _cov, annotations_);
    }
    private static void buildAnnotationsReport(VariablesOffsets _vars, SwitchMethodBlock _cond, int _index, Coverage _cov) {
        ResultParsedAnnots annotations_ = _cond.getAnnotationsParams().get(_index);
        annotMethReport(_vars, _cond, _cov, annotations_);
    }

    private static void annotMethReport(VariablesOffsets _vars, AbsBk _cond, Coverage _cov, ResultParsedAnnots _annotations) {
        int len_ = _annotations.getAnnotations().size();
        int index_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        int j_ = _vars.getLastStackElt().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            buildAnnotReport(_vars, _cond, _cov, _annotations, i, index_);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotation(i + 1);
        }
        _vars.getLastStackElt().setIndexAnnotation(0);
        _vars.getLastStackElt().setIndexAnnotationGroup(index_ + 1);
    }

    private static void processAnnotationError(VariablesOffsets _vars, RootBlock _cond) {
        annotError(_vars, _cond, _cond.getAnnotations());
    }
    private static void buildAnnotationsError(VariablesOffsets _vars, NamedFunctionBlock _cond) {
        annotError(_vars, _cond, _cond.getAnnotations());
    }
    private static void buildAnnotationsError(VariablesOffsets _vars, NamedFunctionBlock _cond, int _index) {
        annotMethError(_vars, _cond, _cond.getAnnotationsParams().get(_index));
    }
    private static void buildAnnotationsErrorSupp(VariablesOffsets _vars, NamedCalledFunctionBlock _cond) {
        annotMethError(_vars, _cond, _cond.getAnnotationsSupp());
    }

    private static void annotMethError(VariablesOffsets _vars, AbsBk _cond, ResultParsedAnnots _annotations) {
        int len_ = _annotations.getAnnotations().size();
        int index_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        int j_ = _vars.getLastStackElt().getIndexAnnotation();
        for (int i = j_; i < len_; i++) {
            buildAnnotErr(_vars, _cond, index_, i, _annotations);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotation(i + 1);
        }
        _vars.getLastStackElt().setIndexAnnotation(0);
        _vars.getLastStackElt().setIndexAnnotationGroup(index_ + 1);
    }

    private static void buildAnnotErr(VariablesOffsets _vars, AbsBk _cond, int _indexAnnotationGroup, int _indexAnnotation, ResultParsedAnnots _annotations) {
        int begin_ = _annotations.getAnnotations().get(_indexAnnotation).getIndex();
        int end_ = begin_ + _annotations.getAnnotations().get(_indexAnnotation).getAnnotation().trim().length();
        LinkageStackElementIn in_ = buildLinkageErr(_cond, _indexAnnotationGroup,_indexAnnotationGroup, _indexAnnotation, 0, _indexAnnotation, new LinkageStackElementOffsets(begin_, end_));
        buildErrorReport(_vars, _annotations.getRoots().get(_indexAnnotation), in_);
    }

    private static void buildAnnotationsError(VariablesOffsets _vars, SwitchMethodBlock _cond) {
        annotError(_vars, _cond, _cond.getAnnotations());
    }
    private static void buildAnnotationsError(VariablesOffsets _vars, SwitchMethodBlock _cond, int _index) {
        annotMethError(_vars, _cond, _cond.getAnnotationsParams().get(_index));
    }
    private static void refParams(VariablesOffsets _vars, NamedFunctionBlock _cond, Coverage _cov) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        refPar(_vars, _cond, _cov, k_);
    }

    private static void refPar(VariablesOffsets _vars, NamedFunctionBlock _cond, Coverage _cov, int _k) {
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = _k; i < len_; i++) {
            buildAnnotationsReport(_vars,_cond,i, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(i+1);
            _vars.addParts(export(_cond.getPartOffsetsParams().get(i)));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            _vars.addPart(new PartOffset(ExportCst.anchorName(off_),off_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,off_+param_.length()));
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }
    private static void refParams(VariablesOffsets _vars, SwitchMethodBlock _cond, Coverage _cov) {
        int len_ = 1;
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        for (int i = k_; i < len_; i++) {
            buildAnnotationsReport(_vars,_cond,i, _cov);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(i+1);
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void refParamsError(VariablesOffsets _vars, NamedFunctionBlock _cond) {
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        refParError(_vars, _cond, k_);
    }

    private static void refParError(VariablesOffsets _vars, NamedFunctionBlock _cond, int _k) {
        int len_ = _cond.getParametersNamesOffset().size();
        for (int i = _k; i < len_; i++) {
            buildAnnotationsError(_vars,_cond,i);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(i+1);
            _vars.addParts(export(_cond.getPartOffsetsParams().get(i)));
            Integer off_ = _cond.getParametersNamesOffset().get(i);
            String param_ = _cond.getParametersNames().get(i);
            StringList errs_ = _cond.getParamErrors().get(i);
            if (errs_.isEmpty()) {
                StringList warns_ = _cond.getParamWarns().get(i);
                if (!warns_.isEmpty()) {
                    _vars.addPart(new PartOffset(ExportCst.anchorNameWar(off_,StringUtil.join(warns_,ExportCst.JOIN_ERR)),off_));
                } else {
                    _vars.addPart(new PartOffset(ExportCst.anchorName(off_),off_));
                }
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,off_+param_.length()));
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorNameErr(off_,StringUtil.join(errs_,ExportCst.JOIN_ERR)),off_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,off_+Math.max(1,param_.length())));
            }
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void refParamsError(VariablesOffsets _vars, SwitchMethodBlock _cond) {
        int len_ = 1;
        int k_ = _vars.getLastStackElt().getIndexAnnotationGroup();
        for (int i = k_; i < len_; i++) {
            buildAnnotationsError(_vars,_cond,i);
            if (_vars.goesToProcess()) {
                return;
            }
            _vars.getLastStackElt().setIndexAnnotationGroup(i+1);
        }
        _vars.getLastStackElt().setIndexAnnotationGroup(-1);
    }

    private static void processConditionReport(ConditionBlock _cond, VariablesOffsets _vars, Coverage _cov) {
        int off_ =  _cond.getConditionOffset();
        int offsetEndBlock_ = off_ + _cond.getCondition().length();
        OperationNode root_ = _cond.getRoot();
        LinkageStackElementIn in_ = buildLinkageRep(_cond, off_, offsetEndBlock_, 0, -1);
        buildCoverageReport(_vars, _cov, root_, in_);
    }

    private static LinkageStackElementIn buildLinkageRep(AbsBk _cond, int _begin, int _end, int _indexLoop, int _indexAnnotationGroup) {
        return buildLinkageRep(0,0,_cond,_begin,_end,_indexLoop,_indexAnnotationGroup);
    }

    private static LinkageStackElementIn buildLinkageRep(int _tr, int _trEnd, AbsBk _cond, int _begin, int _end, int _indexLoop, int _indexAnnotationGroup) {
        return buildLinkageErr(_cond, _indexAnnotationGroup, -1, 0, _indexLoop, -1, new LinkageStackElementOffsets(_tr, _trEnd, _begin, _end));
    }

    private static void processConditionError(ConditionBlock _cond, VariablesOffsets _vars) {
        int off_ =  _cond.getConditionOffset();
        if (_vars.getLastStackElt().noVisited() && !_cond.getErr().isEmpty()) {
            _vars.addPart(new PartOffset(ExportCst.anchorErr(_cond.getErr()), off_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, off_ + 1));
        }
        OperationNode root_ = _cond.getRoot();
        buildNormalError(_vars, _cond, -1, root_, off_, 0,off_ + _cond.getCondition().length());
    }

    private static LinkageStackElementIn buildLinkageErr(AbsBk _cond, int _indexAnnotationGroup, int _indexAnnotationGroupLook, int _indexAnnotation, int _indexLoop, int _indexAnnotationLook, LinkageStackElementOffsets _offsets) {
        return new LinkageStackElementIn(_cond, _indexLoop, _indexAnnotationGroup,_indexAnnotationGroupLook, _indexAnnotation, _indexAnnotationLook,
                _offsets);
    }

    private static OperationNode getCurrent(VariablesOffsets _vars,OperationNode _root) {
        OperationNode current_ = _vars.getLastStackElt().getCurrent();
        if (current_ == null) {
            return _root;
        }
        return current_;
    }

    private static void buildCoverageReport(VariablesOffsets _vars, Coverage _cov, OperationNode _root, LinkageStackElementIn _in) {
        OperationNode val_ = getCurrent(_vars, _root);
        while (true) {
            if (!_vars.getVisited().containsObj(val_)) {
                if (!_vars.getVisitedAnnotations().containsObj(val_)) {
                    getBeginOpReport(_vars,_in, _root, val_, _cov);
                    leftReport(_in, _vars, val_, _cov);
                }
                OperationNode visitRep_ = visit(_vars, val_, _in);
                if (_vars.goesToProcess()) {
                    return;
                }
                if (visitRep_ != null) {
                    val_ = visitRep_;
                    continue;
                }
            }
            OperationNode nextRep_ = loopReport(val_, _vars, _cov, _root, _in);
            if (nextRep_ == null) {
                return;
            }
            val_ = nextRep_;
        }
    }

    private static OperationNode loopReport(OperationNode _val, VariablesOffsets _vars, Coverage _cov, OperationNode _root, LinkageStackElementIn _in) {
        OperationNode val_ = _val;
        while (true) {
            MethodOperation parent_ = val_.getParent();
            if (parent_ == null || val_ == _root) {
                getEnd(_vars, _in);
                _vars.getLastStackElt().setNullCurrent();
                return null;
            }
            processImplicit(_in,_vars, val_, parent_);
            getEndTag(_vars, _in, val_, parent_);
            processUnaryRightOperations(_in,_vars, val_,parent_);
            OperationNode nextSiblingOp_ = val_.getNextSibling();
            if (nextSiblingOp_ != null) {
                middleReport(_in,_vars, val_,nextSiblingOp_,
                        parent_, _cov);
                val_=nextSiblingOp_;
                break;
            }
            int st_ = end(_vars, parent_, _root,_in);
            if (st_ > 0) {
                if (st_ == 1) {
                    getEnd(_vars, _in);
                    _vars.getLastStackElt().setNullCurrent();
                }
                return null;
            }
            val_ = parent_;
        }
        return val_;
    }

    private static void getEndTag(VariablesOffsets _vars, LinkageStackElementIn _in, OperationNode _val, MethodOperation _parent) {
        if (_in.skipReportElement()) {
            return;
        }
        int sum_ = _in.getBeginBlock();
        int indexChild_ = _val.getIndexChild();
        StrTypes children_ = _parent.getChildren();
        int offsetEnd_ = sum_ + _val.getIndexInEl() + children_.getValue(indexChild_).length();
        _vars.addPart(new PartOffset(ExportCst.END_SPAN, offsetEnd_));
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
            for (AnaResultPartType a: val_.getBlock().getPartsStaticInitInterfacesOffset()) {
                state_.getPartsAfter().addAllElts(export(a));
            }
            state_.getPartsAfter().addAllElts(buildByInst(val_));
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

    private static void buildErrorReport(VariablesOffsets _vars, OperationNode _root, LinkageStackElementIn _in) {
        OperationNode current_ = getCurrent(_vars, _root);
        if (current_ == null) {
            return;
        }
        int sum_ = _in.getBeginBlock();
        OperationNode val_ = current_;
        while (true) {
            if (!_vars.getVisited().containsObj(val_)) {
                if (!_vars.getVisitedAnnotations().containsObj(val_)) {
                    leftError(_vars, sum_,val_,_in.getLen());
                }
                OperationNode visitErr_ = visit(_vars, val_, _in);
                if (_vars.goesToProcess()) {
                    return;
                }
                if (visitErr_ != null) {
                    val_ = visitErr_;
                    continue;
                }
            }
            OperationNode nextErr_ = loopError(val_, _vars, _root, _in);
            if (nextErr_ == null) {
                return;
            }
            val_ = nextErr_;
        }
    }

    private static OperationNode loopError(OperationNode _val, VariablesOffsets _vars, OperationNode _root, LinkageStackElementIn _in) {
        OperationNode val_ = _val;
        while (true) {
            MethodOperation parent_ = val_.getParent();
            if (parent_ == null || val_ == _root) {
                _vars.getLastStackElt().setNullCurrent();
                return null;
            }
            processImplicit(_in,_vars, val_, parent_);
            processUnaryRightOperations(_in,_vars, val_,parent_);
            OperationNode nextSiblingOp_ = val_.getNextSibling();
            if (nextSiblingOp_ != null) {
                middleError(_in,_vars, val_,nextSiblingOp_,
                        parent_);
                val_=nextSiblingOp_;
                break;
            }
            int st_ = end(_vars, parent_, _root,_in);
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
    private static OperationNode visit(VariablesOffsets _vars, OperationNode _val, LinkageStackElementIn _in) {
        if (_val instanceof AnonymousLambdaOperation) {
            NamedCalledFunctionBlock block_ = ((AnonymousLambdaOperation) _val).getBlock();
            setAnonState(_vars, block_);
            int begin_ = _in.getBeginBlock() + _val.getIndexInEl();
            _vars.addPart(new PartOffset(ExportCst.span(TYPE), begin_));
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
    private static int end(VariablesOffsets _vars, MethodOperation _parent, OperationNode _r, LinkageStackElementIn _in) {
        if (isInner(_parent)) {
            setInnerState(_vars, _parent);
            _vars.getLastStackElt().element(_parent, _in);
            _vars.getVisited().add(_parent);
            return 2;
        }
        right(_in,_vars, _parent);
        if (_parent == _r) {
            return 1;
        }
        return 0;
    }

    private static void getBeginOpReport(VariablesOffsets _vars, LinkageStackElementIn _in, OperationNode _root, OperationNode _curOp, Coverage _cov) {
        if (_in.skipReportElement()) {
            return;
        }
        AbstractCoverageResult result_ = getCovers(_in, _curOp, _cov);
        String tag_ = getBeginReport(_in, _root, _curOp, result_, _cov);
        int suppl_ = 0;
        if (_root == _curOp) {
            suppl_ = _in.getTr();
        }
        _vars.addPart(new PartOffset(tag_,_in.getBeginBlock() + _curOp.getIndexInEl()+suppl_));
    }

    private static void getEnd(VariablesOffsets _vars, LinkageStackElementIn _in) {
        if (_in.skipReportElement()) {
            return;
        }
        int endBlock_ = _in.getEndBlock();
        int suppl_ = _in.getTrEnd();
        _vars.addPart(new PartOffset(ExportCst.END_SPAN, endBlock_+suppl_));
    }

    private static String getBeginReport(LinkageStackElementIn _in, OperationNode _root, OperationNode _val, AbstractCoverageResult _result, Coverage _cov) {
        String full_;
        String fullInit_;
        String partial_;
        String partialInit_;
        String none_;
        if (_in.getIndexAnnotationLook() >= 0 || AbsBk.isAnnotBlock(_in.getBlock())) {
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
            return procCstCover(_in, _root, _val, _cov, full_, fullInit_, none_);
        }
        if (_result.isFullCovered()) {
            return getFullInitReport(_result, fullInit_, full_);
        }
        if (_result.isPartialCovered()) {
            return getPartialInitReport(_val, _result, fullInit_, full_, partialInit_, partial_);
        }
        return ExportCst.span(none_);
    }

    private static String procCstCover(LinkageStackElementIn _in, OperationNode _root, OperationNode _val, Coverage _cov, String _full, String _fullInit, String _none) {
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
            return covCst(_in, _cov, _full, _fullInit, _none, par_);
        }
        if (before_.getIndexChild() > 0) {
            Argument firstArg_ = parentBefore_.getFirstChild().getArgument();
            if (firstArg_ == null) {
                return covCst(_in, _cov, _full, _fullInit, _none, before_);
            }
            if (notCovered(before_,parentBefore_,firstArg_)) {
                return ExportCst.span(_none);
            }
        }
        return covCst(_in, _cov, _full, _fullInit, _none, par_);
    }

    private static boolean notCovered(OperationNode _before,MethodOperation _parentBefore, Argument _firstArg) {
        if (_parentBefore instanceof OrOperation && BooleanStruct.isTrue(Argument.getNullableValue(_firstArg).getStruct())) {
            return true;
        }
        if (_parentBefore instanceof AndOperation && BooleanStruct.isFalse(Argument.getNullableValue(_firstArg).getStruct())) {
            return true;
        }
        if (_parentBefore instanceof NullSafeOperation && !Argument.getNullableValue(_firstArg).isNull()) {
            return true;
        }
        if (_parentBefore instanceof AbstractTernaryOperation) {
            if (BooleanStruct.isTrue(Argument.getNullableValue(_firstArg).getStruct()) && _before.getIndexChild() == 2) {
                return true;
            }
            return BooleanStruct.isFalse(Argument.getNullableValue(_firstArg).getStruct()) && _before.getIndexChild() == 1;
        }
        return false;
    }
    private static String covCst(LinkageStackElementIn _in, Coverage _cov, String _full, String _fullInit, String _none, OperationNode _par) {
        AbstractCoverageResult resultPar_ = getCovers(_in, _par, _cov);
        if (resultPar_.isPartialCovered()) {
            return getFullInitReport(resultPar_, _fullInit, _full);
        }
        return ExportCst.span(_none);
    }

    private static String getPartialInitReport(OperationNode _val, AbstractCoverageResult _result, String _fullInit, String _full, String _partialInit, String _partial) {
        if (_val instanceof AffectationOperation && _val.getFirstChild().getNextSibling().getArgument() != null) {
            return getFullInitReport(_result, _fullInit, _full);
        }
        if (_result.isInit()) {
            return ExportCst.span(_partialInit);
        }
        return ExportCst.span(_partial);
    }

    private static String getFullInitReport(AbstractCoverageResult _resultPar, String _fullInit, String _full) {
        if (_resultPar.isInit()) {
            return ExportCst.span(_fullInit);
        }
        return ExportCst.span(_full);
    }

    private static void leftReport(LinkageStackElementIn _in, VariablesOffsets _vars,
                                   OperationNode _val,
                                   Coverage _cov) {
        MethodOperation par_ = _val.getParent();
        int sum_ = _in.getBeginBlock();
        if (par_ == null) {
            int s_ = sum_ + _val.getIndexInEl();
            tryAddMergedParts(_vars,_val.getResultClass().getFunction(), s_,new StringList(),new StringList());
        }
        if (leftOperNotFullTernary(par_)) {
            int off_ = sum_ + _val.getIndexInEl();
            tryAddMergedParts(_vars, _val.getResultClass().getFunction(), off_, new StringList(), new StringList());
        }
        if (middleOperNotShortTernary(par_)) {
            int indexChild_ = _val.getIndexChild();
            if (indexChild_ > 0) {
                int off_ = sum_ + _val.getIndexInEl();
                tryAddMergedParts(_vars,_val.getResultClass().getFunction(), off_,new StringList(),new StringList());
            }
        }
        processNamedFct(_vars, sum_, _val);
        processVariables(_vars,sum_, _val);
        processConstants(_vars,sum_, _val);
        processAssociation(_vars, sum_, _val);
        processFieldsReport(_vars, sum_, _val);
        processInstances(_vars, sum_, _val);
        processLamba(_vars, sum_, _val);
        processLeafType(_vars, sum_,_val);
        processDynamicCall(_vars,sum_, _val);
        processRichHeader(_vars, sum_, _val);
        explicitOperatorRep(_in,_vars, sum_, _val,_cov);
        processUnaryLeftOperationsCoversReport(_in,_vars, sum_, _val, _cov);
        if (_val instanceof SwitchOperation) {
            SwitchMethodBlock switchMethod_ = ((SwitchOperation) _val).getSwitchMethod();
//            int full_ = 0;
//            int count_ = 0;
//            for (CustList<AbstractCoverageResult> e : _cov.getCoverSwitchsMethod(switchMethod_).values()) {
//                for (AbstractCoverageResult f: e) {
//                    count_ += f.getCovered();
//                    full_ += f.getFull();
//                }
//            }
//            String tag_ = headCoverage(full_, count_);
            String part_ = headCoverage(_cov,switchMethod_);
//            part_ = tag_ + ExportCst.anchor(count_ + ExportCst.RATIO_COVERAGE + full_);
            int off_ = sum_ + _val.getIndexInEl() + ((SwitchOperation)_val).getOffsetFct();
            _vars.addPart(new PartOffset(part_, off_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR + ExportCst.END_SPAN, off_ + _vars.getKeyWords().getKeyWordSwitch().length()));
            _vars.addParts(export(((SwitchOperation)_val).getPartOffsets()));
        }
        processUnaryLeftOperationsLinks(_vars, sum_, _val);
        processLeftIndexer(_vars, sum_, _val);
        processArrLength(_vars,sum_, _val);
    }

    private static boolean middleOperNotShortTernary(MethodOperation _par) {
        return !(_par instanceof ShortTernaryOperation) && !(_par instanceof RefShortTernaryOperation) && middleOper(_par);
    }

    private static boolean leftOperNotFullTernary(MethodOperation _par) {
        return !(_par instanceof TernaryOperation) && !(_par instanceof RefTernaryOperation) && (leftOperNotUnary(_par) || _par instanceof UnaryOperation || _par instanceof UnaryBooleanOperation || _par instanceof UnaryBinOperation || _par instanceof SemiAffectationOperation && !((SemiAffectationOperation) _par).isPost());
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
                                  int _sum,
                                  OperationNode _val, int _len) {
        errorsBefore(_vars, _sum, _val,_len);
        if (_val instanceof BadTernaryOperation) {
            BadTernaryOperation b_ = (BadTernaryOperation) _val;
            _vars.addParts(convert(b_.getPartOffsetsEnd()));
        }
        if (_val instanceof InferArrayInstancing) {
            InferArrayInstancing i_ = (InferArrayInstancing) _val;
            _vars.addParts(convert(i_.getPartOffsetsErr()));
        }
        processNamedFct(_vars, _sum, _val);
        processVariables(_vars,_sum, _val);
        processConstants(_vars,_sum, _val);
        processAssociation(_vars, _sum, _val);
        processFieldsError(_vars, _sum, _val);
        processInstances(_vars, _sum, _val);
        processLamba(_vars, _sum, _val);
        processLeafType(_vars, _sum,_val);
        processDynamicCall(_vars,_sum, _val);
        processRichHeader(_vars, _sum, _val);
        explicitOperatorErr(_vars, _sum, _val);
        if (_val instanceof SwitchOperation) {
            StringList all_ = new StringList(_val.getErrs());
            SwitchMethodBlock switchMethod_ = ((SwitchOperation) _val).getSwitchMethod();
            all_.addAllElts(switchMethod_.getErrorsBlock());
            if (!all_.isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((SwitchOperation)_val).getOffsetFct();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(all_,ExportCst.JOIN_ERR)),begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordSwitch().length()));
            }
            _vars.addParts(export(((SwitchOperation)_val).getPartOffsets()));
        }
        processUnaryLeftOperationsLinks(_vars, _sum, _val);
        processLeftIndexer(_vars, _sum, _val);
        if (_val instanceof AnnotationInstanceOperation&&_val.getFirstChild() == null) {
            _vars.addParts(convert(((AnnotationInstanceOperation)_val).getPartOffsetsEnd()));
        }
        processArrLength(_vars,_sum, _val);
        errorsAfter(_vars,_sum, _val);
    }

    private static void errorsAfter(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val.getParent() instanceof CallDynMethodOperation) {
            int indexChild_ = _val.getIndexChild();
            CallDynMethodOperation c_ = (CallDynMethodOperation) _val.getParent();
            if (!c_.getSepErr().isEmpty()&&c_.getIndexCh() == indexChild_) {
                String tag_ = ExportCst.anchorErr(c_.getSepErr());
                int rightPar_ = c_.getOperators().getKey(c_.getIndexCh()+1);
                int beg_ = _sum + c_.getIndexInEl() + rightPar_;
                _vars.addPart(new PartOffset(tag_, beg_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,beg_+1));
            }
        }
    }

    private static void errorsBefore(VariablesOffsets _vars, int _sum, OperationNode _val, int _len) {
        MethodOperation par_ = _val.getParent();
        if (par_ == null) {
            errorAtRoot(_vars, _sum, _val,_len);
        } else {
            mergeErrorsToParent(_vars, _sum, _val);
        }
    }

    private static void mergeErrorsToParent(VariablesOffsets _vars, int _sum, OperationNode _val) {
        int indexChild_ = _val.getIndexChild();
        MethodOperation par_ = _val.getParent();
        StringList l_ = new StringList();
        MethodOperation.processEmptyError(_val,l_);
        if (leftOperNotUnary(par_)&& !(indexChild_ == 0 && par_ instanceof ArrOperation)) {
            if (!l_.isEmpty()) {
                StrTypes operators_ =  par_.getOperators();
                int s_ = _sum + par_.getIndexInEl() + operators_.getKey(indexChild_);
                int len_ = operators_.getValue(indexChild_).length();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(l_,ExportCst.JOIN_ERR)),s_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,s_+Math.max(len_,1)));
            } else {
                appendPossibleParts(_vars, par_, indexChild_);
            }
        } else if (par_ instanceof IdOperation|| par_ instanceof ArrOperation) {
            appendPossibleParts(_vars, par_, indexChild_);
        }
        if (leftOperNotFullTernary(par_)) {
            int off_ = _sum + _val.getIndexInEl();
            tryAddMergedParts(_vars, _val.getResultClass().getFunction(), off_, new StringList(), new StringList());
        }
        if (middleOperNotShortTernary(par_) && indexChild_ > 0) {
            int off_ = _sum + _val.getIndexInEl();
            tryAddMergedParts(_vars, _val.getResultClass().getFunction(), off_, new StringList(), new StringList());
        }
    }

    private static void errorAtRoot(VariablesOffsets _vars, int _sum, OperationNode _val, int _len) {
        StringList errEmpt_ = new StringList();
        MethodOperation.processEmptyError(_val,errEmpt_);
        if (!errEmpt_.isEmpty()) {
            int begin_ = _sum + _val.getIndexInEl();
            processNullParent(_vars, _val, begin_);
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(errEmpt_,ExportCst.JOIN_ERR)),begin_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ _len));
        } else {
            int s_ = _sum + _val.getIndexInEl();
            processNullParent(_vars, _val, s_);
        }
    }

    private static void processNullParent(VariablesOffsets _vars, OperationNode _val, int _off) {
        tryAddMergedParts(_vars, _val.getResultClass().getFunction(), _off, new StringList(), new StringList());
    }

    private static void tryAddMergedParts(VariablesOffsets _vars, AnaTypeFct _fct, int _begin, StringList _errors, StringList _title) {
        if (_vars.isImplicit()) {
            mergeParts(_vars, _fct, _begin, _errors, _title);
        }
    }
    private static void appendPossibleParts(VariablesOffsets _vars, MethodOperation _par, int _indexChild) {
        CustList<CustList<InfoErrorDto>> partOffsetsChildrenList_ = _par.getPartOffsetsChildrenList();
        if (partOffsetsChildrenList_.isValidIndex(_indexChild)) {
            for (InfoErrorDto i: partOffsetsChildrenList_.get(_indexChild)) {
                _vars.addParts(convert(i));
            }
            return;
        }
        CustList<InfoErrorDto> partOffsetsChildren_ = _par.getPartOffsetsChildren();
        if (partOffsetsChildren_.isValidIndex(_indexChild)) {
            _vars.addParts(convert(partOffsetsChildren_.get(_indexChild)));
        }
    }

    private static void processArrLength(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof ArrayFieldOperation) {
            ArrayFieldOperation aField_ = (ArrayFieldOperation) _val;
            int relativeOff_ = aField_.getOffset();
            String originalStr_ = aField_.getValue();
            String str_ = originalStr_.trim();
            int begin_ = _sum + relativeOff_ + _val.getIndexInEl();
            if (!aField_.getErrs().isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(aField_.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+str_.length()));
            } else {
                _vars.addPart(new PartOffset(ExportCst.HEAD_BOLD,begin_));
                _vars.addPart(new PartOffset(ExportCst.END_BOLD,begin_+str_.length()));
            }
        }
    }

    private static void processConstants(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof ConstantOperation) {
            int off_ = ((ConstantOperation)_val).getOffset();
            int begCst_ = _sum + off_ + _val.getIndexInEl();
            if (_val instanceof ConstantStrOperation) {
                _vars.addPart(new PartOffset(ExportCst.span(STRING), begCst_));
                messageCst(_vars,_val, begCst_, ((ConstantStrOperation)_val).getLen());
                _vars.addPart(new PartOffset(ExportCst.END_SPAN, begCst_ + ((ConstantStrOperation)_val).getLen()));
            }
            if (_val instanceof ConstantCharOperation) {
                _vars.addPart(new PartOffset(ExportCst.span(STRING), begCst_));
                messageCst(_vars,_val, begCst_, ((ConstantCharOperation)_val).getLen());
                _vars.addPart(new PartOffset(ExportCst.END_SPAN, begCst_ + ((ConstantCharOperation)_val).getLen()));
            }
            if (_val instanceof ConstantNbOperation) {
                messageCst(_vars,_val, begCst_, ((ConstantNbOperation) _val).getLen());
            }
        }
    }

    private static void messageCst(VariablesOffsets _vars, OperationNode _val, int _begCst, int _len) {
        if (!_val.getErrs().isEmpty()) {
            String tag_ = ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR));
            _vars.addPart(new PartOffset(tag_, _begCst));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, _begCst + _len));
        }
    }

    private static void processDynamicCall(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof CallDynMethodOperation) {
            int off_ = ((CallDynMethodOperation) _val).getOffsetFct();
            int length_ = ((CallDynMethodOperation) _val).getLenTrimFct();
            String tag_;
            int begFct_ = off_+_sum + _val.getIndexInEl();
            if (_val.getErrs().isEmpty()) {
                tag_ = ExportCst.HEAD_BOLD;
                _vars.addPart(new PartOffset(tag_, begFct_));
                tag_ = ExportCst.END_BOLD;
            } else {
                tag_ = ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR));
                _vars.addPart(new PartOffset(tag_, begFct_));
                tag_ = ExportCst.END_ANCHOR;
            }
            _vars.addPart(new PartOffset(tag_,begFct_+ length_));
            if (((CallDynMethodOperation) _val).isNoNeed()) {
                tag_ = ExportCst.anchorErr(((CallDynMethodOperation) _val).getSepErr());
                int leftPar_ = ((CallDynMethodOperation) _val).getOperators().firstKey();
                int beg_ = _sum + _val.getIndexInEl() + leftPar_;
                _vars.addPart(new PartOffset(tag_, beg_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,beg_+1));
            }
            if (_val.getFirstChild() == null&&!((CallDynMethodOperation) _val).getSepErr().isEmpty()) {
                tag_ = ExportCst.anchorErr(((CallDynMethodOperation) _val).getSepErr());
                int rightPar_ = ((CallDynMethodOperation) _val).getOperators().lastKey();
                int beg_ = _sum + _val.getIndexInEl() + rightPar_;
                _vars.addPart(new PartOffset(tag_, beg_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,beg_+1));
            }
        }
    }

    private static void processLeftIndexer(VariablesOffsets _vars, int _sum, OperationNode _val) {
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
                addParts(_vars, function_, beg_,1,_val.getErrs(),_val.getErrs());
            } else if (!_val.getErrs().isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)), beg_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, beg_ +1));
            }
        }
    }


    private static void processAssociation(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof AssocationOperation) {
            AssocationOperation a_ = (AssocationOperation) _val;
            int delta_ = a_.getOffsetFct();
            addParts(_vars,
                    a_.getFunction(),
                    _sum +delta_+ _val.getIndexInEl(),a_.getLenTrimFct(),_val.getErrs(),_val.getErrs()
            );
            if (!a_.getErrAff().isEmpty()) {
                int begin_ = _sum + a_.getOffEq() + _val.getIndexInEl();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(a_.getErrAff()), begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, begin_+1));
            }
        }
    }

    private static void processNamedFct(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof FctOperation||_val instanceof ChoiceFctOperation ||_val instanceof SuperFctOperation) {
            if (_val instanceof FctOperation) {
                _vars.addParts(export(((FctOperation)_val).getPartOffsets()));
                int delta_ = ((FctOperation) _val).getDelta();
                int l_ = ((FctOperation) _val).getLengthMethod();
                int begFct_ = _sum + delta_ + _val.getIndexInEl();
                if (((FctOperation)_val).isClonedMethod()&&_val.getErrs().isEmpty()) {
                    _vars.addPart(new PartOffset(ExportCst.HEAD_BOLD, begFct_));
                    _vars.addPart(new PartOffset(ExportCst.END_BOLD, begFct_ +l_));
                } else {
                    AnaTypeFct function_ = ((FctOperation) _val).getCallFctContent().getFunction();
                    addParts(_vars, function_,
                            begFct_, l_,
                            _val.getErrs(),_val.getErrs());
                }
            }
            if (_val instanceof ChoiceFctOperation) {
                _vars.addParts(export(((ChoiceFctOperation)_val).getPartOffsets()));
                int delta_ = ((ChoiceFctOperation) _val).getDelta();
                int l_ = ((ChoiceFctOperation) _val).getLengthMethod();
                AnaTypeFct function_ = ((ChoiceFctOperation) _val).getCallFctContent().getFunction();
                addParts(_vars, function_,
                        _sum +delta_+ _val.getIndexInEl(),l_,
                        _val.getErrs(),_val.getErrs());
            }
            if (_val instanceof SuperFctOperation) {
                _vars.addParts(export(((SuperFctOperation)_val).getPartOffsets()));
                int delta_ = ((SuperFctOperation) _val).getDelta();
                int l_ = ((SuperFctOperation) _val).getLengthMethod();
                AnaTypeFct function_ = ((SuperFctOperation) _val).getCallFctContent().getFunction();
                addParts(_vars, function_,
                        _sum +delta_+ _val.getIndexInEl(),l_,
                        _val.getErrs(),_val.getErrs());
            }
        }
        processAnnotations(_vars, _val);
    }

    private static void processAnnotations(VariablesOffsets _vars, OperationNode _val) {
        if (_val instanceof AnnotationInstanceOperation) {
            _vars.addParts(convert(((AnnotationInstanceOperation) _val).getPartOffsetsErr()));
            if (_val instanceof AnnotationInstanceArobaseOperation) {
                _vars.addParts(export(((AnnotationInstanceArobaseOperation) _val).getPartOffsets()));
            }
            _vars.addParts(convert(((AnnotationInstanceOperation) _val).getPartOffsetsErrPar()));
        }
    }

    private static void processFieldsReport(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof SettableAbstractFieldOperation) {
            if (_val instanceof DeclaredFieldOperation) {
                int begin_ = beginOff(_sum, _val);
                int idValueOffset_ = ((SettableAbstractFieldOperation)_val).getValueOffset();
                _vars.addPart(new PartOffset(ExportCst.anchorName(idValueOffset_),begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+((SettableAbstractFieldOperation) _val).getFieldNameLength()));
            }
            if (_val instanceof SettableFieldOperation) {
                addFieldRefParts(_vars, _val, _sum);
            }
        }
    }

    private static void processFieldsError(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (!(_val instanceof SettableAbstractFieldOperation)) {
            return;
        }
        if (_val instanceof DeclaredFieldOperation) {
            int begin_ = beginOff(_sum, _val);
            int idValueOffset_ = ((SettableAbstractFieldOperation)_val).getValueOffset();
            StringList errs_ = ((DeclaredFieldOperation) _val).getErrsField();
            if (errs_.isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorName(idValueOffset_),begin_));
            } else {
                String err_ = StringUtil.join(errs_,ExportCst.JOIN_ERR);
                _vars.addPart(new PartOffset(ExportCst.anchorErr(err_),begin_));
            }
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+((SettableAbstractFieldOperation) _val).getFieldNameLength()));
        }
        if (_val instanceof SettableFieldOperation) {
            addFieldRefParts(_vars, _val, _sum);
        }
    }

    private static int beginOff(int _sum, OperationNode _val) {
        int delta_ = ((SettableAbstractFieldOperation) _val).getOffset();
        return _sum + delta_ + _val.getIndexInEl() + ((SettableAbstractFieldOperation) _val).getDelta();
    }

    private static void addFieldRefParts(VariablesOffsets _vars, OperationNode _val, int _sum) {
        int begin_ = beginOff(_sum, _val);
        int idValueOffset_ = ((SettableAbstractFieldOperation)_val).getValueOffset();
        _vars.addParts(export(((SettableFieldOperation) _val).getPartOffsets()));
        updateFieldAnchor(_vars, ((SettableFieldOperation) _val).getFieldType(), _val.getErrs(), ((SettableFieldOperation) _val).getFieldIdReadOnly(), begin_, ((SettableAbstractFieldOperation) _val).getFieldNameLength(), idValueOffset_);
    }

    private static void processVariables(VariablesOffsets _vars, int _sum, OperationNode _val) {

        processVariable(_vars,_sum, _val);

        processFinalVariable(_vars,_sum, _val);
    }

    private static void processFinalVariable(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof FinalVariableOperation) {
            String varName_ = ((FinalVariableOperation) _val).getRealVariableName();
            int delta_ = ((FinalVariableOperation) _val).getOffset();
            int deltaLoc_ = ((FinalVariableOperation) _val).getDelta() + ((FinalVariableOperation) _val).getAfterOper();
            int begVar_ = deltaLoc_ + delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            if (!_val.getErrs().isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)), begVar_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, endVar_));
            } else {
                int id_ = ((FinalVariableOperation) _val).getRef();
                _vars.addPart(new PartOffset(ExportCst.anchorRef(id_), begVar_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, endVar_));
            }
        }
    }

    private static void processVariable(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof VariableOperation) {
            String varName_ = ((VariableOperation) _val).getRealVariableName();
            int delta_ = ((VariableOperation) _val).getOffset();
            int id_ = ((VariableOperation) _val).getRef();
            int begVar_ = delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            StringList errs_ = ((VariableOperation) _val).getNameErrors();
            if (!errs_.isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(errs_,ExportCst.JOIN_ERR)), begVar_));
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorName(id_), begVar_));
            }
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, endVar_));
        }
        if (_val instanceof VariableOperationUse) {
            String varName_ = ((VariableOperationUse) _val).getRealVariableName();
            int delta_ = ((VariableOperationUse) _val).getOffset();
            int id_ = ((VariableOperationUse) _val).getRef();
            int begVar_ = delta_ + _sum + _val.getIndexInEl();
            int endVar_ = begVar_ + varName_.length();
            if (((VariableOperationUse) _val).isKeyWord()) {
                _vars.addPart(new PartOffset(ExportCst.HEAD_BOLD, begVar_));
                _vars.addPart(new PartOffset(ExportCst.END_BOLD, endVar_));
            } else {
                _vars.addPart(new PartOffset(ExportCst.anchorRef(id_), begVar_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, endVar_));
            }
        }
    }

    private static void processInstances(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof AbstractInstancingOperation) {
            AbstractInstancingOperation inst_ = (AbstractInstancingOperation) _val;
            AnaTypeFct constructor_ = inst_.getConstructor();
            int offsetNew_ = inst_.getOffsetFct();
            int beginInst_ = offsetNew_ + _sum + _val.getIndexInEl();
            int lengthInst_ = _vars.getKeyWords().getKeyWordNew().length();
            StringList errs_ = _val.getErrs();
            if (!(inst_ instanceof StandardInstancingOperation)) {
                addParts(_vars, constructor_,
                        beginInst_, lengthInst_,
                        errs_, errs_);
            } else {
                StandardInstancingOperation instStd_ = (StandardInstancingOperation) inst_;
                InnerTypeOrElement innerElt_ = instStd_.getInnerElt();
                if (innerElt_ == null) {
                    addParts(_vars, constructor_,
                            beginInst_, lengthInst_,
                            errs_, errs_);
                    addTypes(_vars,instStd_.getPartsInstInitInterfaces());
                    _vars.addParts(buildByInst(inst_));
                } else {
                    StringList mergedErrs_ = new StringList(instStd_.getErrorsFields());
                    mergedErrs_.addAllElts(errs_);
                    nameId(_vars, inst_, innerElt_.getUniqueFieldName(), mergedErrs_, innerElt_.getFieldNameOffset());
                    addTypes(_vars, innerElt_.getElementContent().getPartOffsets());
                }
            }
        }
        if (_val instanceof DimensionArrayInstancing) {
            _vars.addParts(buildByInst(((DimensionArrayInstancing)_val).getPartOffsets(),((DimensionArrayInstancing)_val).getTypeInfer()));
        }
        if (_val instanceof ElementArrayInstancing) {
            _vars.addParts(buildByInst(((ElementArrayInstancing)_val).getPartOffsets(), ((ElementArrayInstancing)_val).getTypeInfer()));
            _vars.addParts(convert(((ElementArrayInstancing)_val).getPartOffsetsErr()));
        }
    }

    private static CustList<PartOffset> export(AnaResultPartType _analyzed) {
        CustList<PartOffset> out_ = new CustList<PartOffset>();
        LinkagePartTypeUtil.processAnalyzeConstraintsRepParts(_analyzed,out_);
        return out_;
    }
    private static CustList<PartOffset> buildByInst(AbstractInstancingOperation _inst) {
        return buildByInst(_inst.getResolvedInstance(), _inst.getTypeInfer());
    }

    private static CustList<PartOffset> buildByInst(ResolvedInstance _resolvedInstance, String _typeInfer) {
        CustList<PartOffset> out_ = new CustList<PartOffset>();
        AnaResultPartType result_ = _resolvedInstance.getResult();
        LinkagePartTypeUtil.processAnalyzeConstraintsRepParts(result_,out_);
        if (!_typeInfer.isEmpty() && _resolvedInstance.isInferred()) {
            appendTitleParts(_resolvedInstance.getLt(), _resolvedInstance.getGt(), _resolvedInstance.getInfer(),out_);
        } else {
            for (AnaResultPartType p: _resolvedInstance.getParts()) {
                LinkagePartTypeUtil.processAnalyzeConstraintsRepParts(p,out_);
            }
        }
        return out_;
    }

    private static void processLeafType(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof FunctFilterOperation) {
            addTypes(_vars, ((FunctFilterOperation) _val).getPartOffsets());
            _vars.addParts(convert(((FunctFilterOperation)_val).getPartOffsetsErr()));
        }
        if (_val instanceof IdFctOperation) {
            addTypes(_vars, ((IdFctOperation) _val).getPartOffsetsSet());
            _vars.addParts(convert(((IdFctOperation)_val).getPartOffsetsErrSet()));
        }
        if (_val instanceof ForwardOperation) {
            ForwardOperation f_ = (ForwardOperation) _val;
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + f_.getOffset();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ f_.getLength()));
            }
            _vars.addParts(export(((ForwardOperation)_val).getPartOffsets()));
        }
        if (_val instanceof DefaultValueOperation) {
            _vars.addParts(export(((DefaultValueOperation)_val).getPartOffsets()));
        }
        if (_val instanceof StaticInfoOperation) {
            _vars.addParts(export(((StaticInfoOperation)_val).getPartOffsets()));
        }
        if (_val instanceof StaticAccessOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((StaticAccessOperation)_val).getOffset();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordStatic().length()));
            }
            _vars.addParts(export(((StaticAccessOperation)_val).getPartOffsets()));
        }
        if (_val instanceof StaticCallAccessOperation) {
            if (!_val.getErrs().isEmpty()) {
                int begin_ = _sum + _val.getIndexInEl() + ((StaticCallAccessOperation)_val).getOffset();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordStaticCall().length()));
            }
            _vars.addParts(buildByInst(((StaticCallAccessOperation)_val).getPartOffsets(), ((StaticCallAccessOperation)_val).getStCall()));
        }
        if (_val instanceof ThisOperation && !_val.getErrs().isEmpty()) {
            int begin_ = _sum + _val.getIndexInEl() + ((ThisOperation)_val).getOffset();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR)), begin_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, begin_ + _vars.getKeyWords().getKeyWordThis().length()));
        }
    }

    private static void addTypes(VariablesOffsets _vars, CustList<AnaResultPartType> _partOffsets) {
        for (AnaResultPartType a: _partOffsets) {
            _vars.addParts(export(a));
        }
    }

    private static void processLamba(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (!(_val instanceof LambdaOperation)) {
            return;
        }
        ClassField fieldId_ = ((LambdaOperation) _val).getFieldId();
        AnaTypeFct function_ = ((LambdaOperation) _val).getFunction();
        RootBlock fieldType_ = ((LambdaOperation) _val).getFieldType();
        int off_ = ((LambdaOperation)_val).getOffset();
        int beginLambda_ = off_ + _sum + _val.getIndexInEl();
        int lambdaLen_ = _vars.getKeyWords().getKeyWordLambda().length();
        if (function_ != null) {
            addParts(_vars, function_,
                    beginLambda_, lambdaLen_,
                    _val.getErrs(),_val.getErrs());
        } else {
            if (fieldId_ != null) {
                updateFieldAnchor(_vars,fieldType_, _val.getErrs(), fieldId_, beginLambda_, lambdaLen_, ((LambdaOperation) _val).getValueOffset());
            } else if (!_val.getErrs().isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)), beginLambda_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, beginLambda_ + lambdaLen_));
            }
        }
        addTypes(_vars,((LambdaOperation)_val).getPartOffsetsBegin());
        _vars.addParts(convert(((LambdaOperation)_val).getPartOffsetsErrMiddle()));
        addTypes(_vars,((LambdaOperation)_val).getPartOffsetsPre());
        addTypes(_vars,((LambdaOperation)_val).getPartOffsets());
        _vars.addParts(convert(((LambdaOperation)_val).getPartOffsetsErrEnd()));
        if (((LambdaOperation)_val).getRecordType() >= 0) {
            CustList<AnaNamedFieldContent> namedFields_ = ((LambdaOperation) _val).getNamedFields();
            int len_ = namedFields_.size();
            for (int i = 0; i < len_; i++) {
                _vars.addParts(export(((LambdaOperation) _val).getPartOffsetsRec().get(i)));
                int ref_ = ((LambdaOperation) _val).getRefs().get(i);
                if (ref_ < 0) {
                    continue;
                }
                AnaNamedFieldContent naFi_ = namedFields_.get(i);
                String name_ = naFi_.getName();
                updateFieldAnchor(_vars,naFi_.getDeclaring(),new StringList(),
                        new ClassField(naFi_.getIdClass(),name_),
                        beginLambda_+((LambdaOperation) _val).getOffsets().get(i),
                        name_.length(), ref_
                );
            }
            addTypes(_vars, ((LambdaOperation) _val).getPartsInstInitInterfaces());
        }
    }

    private static void processRichHeader(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof EnumValueOfOperation) {
            if (!_val.getErrs().isEmpty()) {
                int off_ = ((EnumValueOfOperation)_val).getOffsetFct();
                int begin_ = _sum + _val.getIndexInEl() + off_;
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordValueOf().length()));
            }
            addTypes(_vars, ((EnumValueOfOperation)_val).getPartOffsets());
        }
        if (_val instanceof ValuesOperation) {
            if (!_val.getErrs().isEmpty()) {
                int off_ = ((ValuesOperation)_val).getOffset();
                int begin_ = _sum + _val.getIndexInEl() + off_;
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ _vars.getKeyWords().getKeyWordValues().length()));
            }
            addTypes(_vars, ((ValuesOperation)_val).getPartOffsets());
        }
        if (_val instanceof AbstractInvokingConstructor) {
            int off_ = ((AbstractInvokingConstructor)_val).getOffsetFct();
            if (_val instanceof InterfaceInvokingConstructor) {
                addParts(_vars, ((AbstractInvokingConstructor)_val).getConstructor(),
                        _sum + _val.getIndexInEl()+off_,_vars.getKeyWords().getKeyWordInterfaces().length(),
                        _val.getErrs(),_val.getErrs());
                addTypes(_vars,((InterfaceInvokingConstructor)_val).getPartOffsets());
            } else if (_val instanceof InterfaceFctConstructor) {
                addParts(_vars, ((AbstractInvokingConstructor)_val).getConstructor(),
                        _sum + _val.getIndexInEl()+off_,_vars.getKeyWords().getKeyWordInterfaces().length(),
                        _val.getErrs(),_val.getErrs());
                addTypes(_vars,((InterfaceFctConstructor)_val).getPartOffsets());
            } else{
                addParts(_vars, ((AbstractInvokingConstructor)_val).getConstructor(),
                        _sum + _val.getIndexInEl()+off_, ((AbstractInvokingConstructor) _val).getLenTrimFct(),
                        _val.getErrs(),_val.getErrs());
            }
        }
    }

    private static void explicitOperatorRep(LinkageStackElementIn _in, VariablesOffsets _vars, int _sum, OperationNode _val, Coverage _cov) {
        if (!(_val instanceof ExplicitOperatorOperation)) {
            return;
        }
        ExplicitOperatorOperation par_ = (ExplicitOperatorOperation) _val;
        OperationNode foundChild_ = par_.getFoundChild();
        StringList title_ = new StringList();
        if (foundChild_ != null) {
            title_.addAllElts(getCoversFoundReport(_in, _vars, foundChild_, _cov));
        }
        addParts(_vars, par_.getCallFctContent().getFunction(),
                _sum + _val.getIndexInEl(), _vars.getKeyWords().getKeyWordOperator().length(),
                new StringList(), title_);
        explicitOperatorCom(_vars, _sum, _val);
    }

    private static void explicitOperatorErr(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (!(_val instanceof ExplicitOperatorOperation)) {
            return;
        }
        ExplicitOperatorOperation par_ = (ExplicitOperatorOperation) _val;
        addParts(_vars, par_.getCallFctContent().getFunction(),
                par_.getOffsetFct()+_sum + _val.getIndexInEl(), _vars.getKeyWords().getKeyWordOperator().length(),
                _val.getErrs(), _val.getErrs());
        explicitOperatorCom(_vars, _sum, _val);
    }

    private static void explicitOperatorCom(VariablesOffsets _vars, int _sum, OperationNode _val) {
        ExplicitOperatorOperation par_ = (ExplicitOperatorOperation) _val;
        _vars.addParts(export(par_.getPartOffsets()));
        tryAddMergedParts(_vars,par_.getConv().getFunction(),par_.getAffOffset()+ _sum + _val.getIndexInEl(),new StringList(), new StringList());
        tryAddMergedParts(_vars,par_.getFunctionTest(),par_.getAffOffset()+ _sum + _val.getIndexInEl(),new StringList(), new StringList());
        if (par_.getMethodIdImpl() != null) {
            addParts(_vars, par_.getConv().getFunction(),
                    par_.getBeginImpl()+ _sum + _val.getIndexInEl(), _vars.getKeyWords().getKeyWordCast().length(),
                    new StringList(), new StringList());
            addTypes(_vars, par_.getTypesImpl());
        }
        if (par_.getMethodIdTest() != null) {
            addParts(_vars, par_.getFunctionTest(),
                    par_.getBeginTest()+ _sum + _val.getIndexInEl(), _vars.getKeyWords().getKeyWordExplicit().length(),
                    new StringList(), new StringList());
            addTypes(_vars, par_.getTypesTest());
        }
    }

    private static void processUnaryLeftOperationsCoversReport(LinkageStackElementIn _in, VariablesOffsets _vars, int _sum, OperationNode _val, Coverage _cov) {
        if (_val instanceof UnaryBooleanOperation && ((UnaryBooleanOperation)_val).getFct().getFunction() == null) {
            int offsetOp_ = ((UnaryBooleanOperation)_val).getOperators().firstKey();
            safeReport(_in,_vars,_val,_cov,_sum + _val.getIndexInEl() + offsetOp_, 1);
        }
    }

    private static void processUnaryLeftOperationsLinks(VariablesOffsets _vars, int _sum, OperationNode _val) {
        processUnarySymbol(_vars, _sum, _val);
        processCast(_vars, _sum, _val);
        processPreSemiAffectation(_vars, _sum, _val);
        processUnaryFct(_vars, _sum, _val);
        processNamedArgument(_vars, _sum, _val);
        processTernayFct(_vars, _sum, _val);
    }

    private static void processNamedArgument(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (!(_val instanceof NamedArgumentOperation)) {
            return;
        }
        NamedArgumentOperation n_ = (NamedArgumentOperation) _val;
        if (n_.isRecordBlock()) {
            addTypes(_vars,n_.getPartOffsets());
            int firstOff_ = n_.getOffsetTr();
            int begin_ = _sum + _val.getIndexInEl()+firstOff_;
            updateFieldAnchor(_vars,n_.getField(),n_.getErrs(),
                    n_.getIdField(),
                    begin_,n_.getFieldName().length(), n_.getRef());
            return;
        }
        processNamedArgument(_vars, _sum, n_);
    }

    private static void processNamedArgument(VariablesOffsets _vars, int _sum, NamedArgumentOperation _n) {
        int firstOff_ = _n.getOffsetTr();
        CustList<NamedFunctionBlock> customMethods_ = _n.getCustomMethod();
        String relOne_ = EMPTY;
        String relTwo_ = EMPTY;
        int i_ = 0;
        CustList<LinkedNamedArgParts> cust_ = new CustList<LinkedNamedArgParts>();
        CustList<LinkedNamedArgParts> filterSet_ = new CustList<LinkedNamedArgParts>();
        CustList<LinkedNamedArgParts> filterGet_ = new CustList<LinkedNamedArgParts>();
        feedFiltersNamedList(_n, customMethods_, cust_, filterSet_, filterGet_);
        if (_n.getParent() instanceof ArrOperation) {
            ArrOperation arr_ = (ArrOperation) _n.getParent();
            if (arr_.getArrContent().isVariable()) {
                cust_ = filterSet_;
            } else if (!arr_.isGetAndSet()) {
                cust_ = filterGet_;
            }
        }
        for (LinkedNamedArgParts n:cust_) {
            FileBlock file_ = n.getFile();
            int refO_ = n.getOffset();
            String rel_ = ExportCst.anchorRef(_vars.getCurrentFile(), file_, refO_);
            if (i_ == 0) {
                relOne_ = rel_;
            } else {
                relTwo_ = rel_;
            }
            i_++;
        }
        if (!_n.getErrs().isEmpty()) {
            int begin_ = _sum + _n.getIndexInEl()+ firstOff_;
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_n.getErrs(),ExportCst.JOIN_ERR)),begin_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ _n.getName().length()));
        } else {
            if (!relOne_.isEmpty()){
                int begin_ = _sum + _n.getIndexInEl()+ firstOff_;
                _vars.addPart(new PartOffset(relOne_,begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ _n.getName().length()));
            }
            if (!relTwo_.isEmpty()){
                StrTypes vs_ = _n.getOperators();
                int begin_ = _sum + _n.getIndexInEl()+vs_.firstKey();
                _vars.addPart(new PartOffset(relTwo_,begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+ vs_.firstValue().length()));
            }
        }
    }

    private static void feedFiltersNamedList(NamedArgumentOperation _namedArg, CustList<NamedFunctionBlock> _customMethods, CustList<LinkedNamedArgParts> _list, CustList<LinkedNamedArgParts> _filterSet, CustList<LinkedNamedArgParts> _filterGet) {
        int i_ = 0;
        for (NamedFunctionBlock n: _customMethods) {
            FileBlock file_ = n.getFile();
            Ints offs_ = new Ints();
            if (!file_.isPredefined()) {
                offs_ = n.getParametersNamesOffset();
            }
            int index_ = index(i_, n, _namedArg);
            if (offs_.isValidIndex(index_)) {
                int off_ = offs_.get(index_);
                LinkedNamedArgParts elt_ = new LinkedNamedArgParts(n.getFile(), off_);
                feedFiltersNamed(_filterSet, _filterGet, n, elt_);
                _list.add(elt_);
            }
            i_++;
        }
    }
    private static int index(int _i, NamedFunctionBlock _m, NamedArgumentOperation _namedArg) {
        if (is(_m,MethodKind.SET_INDEX)&&_i > 0) {
            return _namedArg.getIndexChild()-InvokingOperation.getDeltaCount(_namedArg.getParent().getFirstChild());
        }
        return _namedArg.getIndex();
    }

    private static void feedFiltersNamed(CustList<LinkedNamedArgParts> _filterSet, CustList<LinkedNamedArgParts> _filterGet, NamedFunctionBlock _named, LinkedNamedArgParts _elt) {
        if (is(_named,MethodKind.GET_INDEX)) {
            _filterGet.add(_elt);
        }
        if (is(_named,MethodKind.SET_INDEX)) {
            _filterSet.add(_elt);
        }
    }
    private static boolean is(NamedFunctionBlock _m, MethodKind _k) {
        return AbsBk.isOverBlock(_m)&&((NamedCalledFunctionBlock)_m).getKind() == _k;
    }

    private static void processTernayFct(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof TernaryOperation) {
            TernaryOperation t_ = (TernaryOperation) _val;
            AnaTypeFct testFct_ = t_.getTestFct();
            if (testFct_ != null) {
                StringList l_ = new StringList();
                int begin_ = t_.getOffsetFct()+_sum + _val.getIndexInEl();
                addParts(_vars, testFct_,begin_,_vars.getKeyWords().getKeyWordBool().length(),l_,l_);
            }
            testFct_ = t_.getImplFct();
            if (_vars.isImplicit()&&testFct_ != null) {
                StringList l_ = new StringList();
                int begin_ = t_.getOffsetFct()+_sum + _val.getIndexInEl();
                addParts(_vars, testFct_,begin_,_vars.getKeyWords().getKeyWordBool().length(),l_,l_);
            }
        }
        if (_val instanceof RefTernaryOperation) {
            RefTernaryOperation t_ = (RefTernaryOperation) _val;
            AnaTypeFct testFct_ = t_.getTestFct();
            boolean addedErr_ = false;
            if (testFct_ != null) {
                StringList l_ = t_.getChildrenErrors();
                int begin_ = t_.getOffsetFct()+_sum + _val.getIndexInEl();
                addParts(_vars, testFct_,begin_,_vars.getKeyWords().getKeyWordBool().length(),l_,l_);
                addedErr_ = true;
            }
            testFct_ = t_.getImplFct();
            if (_vars.isImplicit()&&testFct_ != null) {
                StringList l_ = t_.getChildrenErrors();
                int begin_ = t_.getOffsetFct()+_sum + _val.getIndexInEl();
                addParts(_vars, testFct_,begin_,_vars.getKeyWords().getKeyWordBool().length(),l_,l_);
                addedErr_ = true;
            }
            if (!addedErr_&&!t_.getChildrenErrors().isEmpty()) {
                int begin_ = t_.getOffsetFct()+_sum + _val.getIndexInEl();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(t_.getChildrenErrors(),ExportCst.JOIN_ERR)), begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, begin_ +_vars.getKeyWords().getKeyWordBool().length()));
            }
        }
    }

    private static void processUnaryFct(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof IdOperation && !_val.getErrs().isEmpty()) {
            int begin_ = _sum + _val.getIndexInEl() + ((IdOperation) _val).getDelta();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR)), begin_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, begin_ + 1));
        }
        if (_val instanceof FirstOptOperation && !_val.getErrs().isEmpty()) {
            int begin_ = _sum + _val.getIndexInEl() + ((FirstOptOperation) _val).getOffsetFct();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR)), begin_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, begin_ + _vars.getKeyWords().getKeyWordFirstopt().length()));
        }
        if (_val instanceof WrappOperation && !_val.getErrs().isEmpty()) {
            int begin_ = _sum + _val.getIndexInEl() + ((WrappOperation) _val).getOffsetFct();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR)), begin_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, begin_ + _vars.getKeyWords().getKeyWordThat().length()));
        }
        if (_val instanceof DefaultOperation && !_val.getErrs().isEmpty()) {
            int begin_ = _sum + _val.getIndexInEl() + ((DefaultOperation) _val).getOffsetFct();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(), ExportCst.JOIN_ERR)), begin_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, begin_ + _vars.getKeyWords().getKeyWordDefault().length()));
        }
    }

    private static void processPreSemiAffectation(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (!(_val instanceof SemiAffectationOperation)) {
            return;
        }
        SemiAffectationOperation par_ = (SemiAffectationOperation) _val;
        int offsetOp_ = par_.getOpOffset();
        if (par_.isPost()) {
            return;
        }
        boolean err_ = true;
        AnaTypeFct function_ = par_.getFct().getFunction();
        if (function_ != null) {
            addParts(_vars, function_,
                    _sum + _val.getIndexInEl()+offsetOp_,1,
                    _val.getErrs(), _val.getErrs());
            err_ = false;
        }
        SettableElResult settable_ = par_.getSettable();
        if (settable_ instanceof ArrOperation && ((ArrOperation) settable_).getFunctionSet() != null) {
            ArrOperation parArr_ = (ArrOperation) settable_;
            addParts(_vars, parArr_.getFunctionSet(),
                    _sum + _val.getIndexInEl()+offsetOp_+1,1,
                    _val.getErrs(), _val.getErrs());
            err_ = false;
        }
        if (err_ && !par_.getErrs().isEmpty()) {
            int begin_ = offsetOp_ + _sum + _val.getIndexInEl();
            _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(par_.getErrs(), ExportCst.JOIN_ERR)), begin_));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, begin_ + 2));
        }
        OperationNode ch_ = null;
        if (settable_ instanceof OperationNode) {
            ch_ = par_.getFirstChild();
        }
        if (ch_ != null) {
            int begin_ = offsetOp_ + _sum + _val.getIndexInEl()+2;
            tryAddMergedParts(_vars,par_.getFunctionTo(), begin_, new StringList(), new StringList());
        }
    }

    private static void processCast(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof CastOperation) {
            int i_ = _sum + _val.getIndexInEl() + ((CastOperation) _val).getOffset();
            int l_;
            if (((CastOperation) _val).isFound()) {
                l_ = 1;
            } else {
                l_ = _vars.getKeyWords().getKeyWordCast().length();
            }
            if (!_val.getErrs().isEmpty()) {
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),i_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,i_+l_));
            }
            _vars.addParts(export(((CastOperation) _val).getPartOffsets()));
        }
        if (_val instanceof ExplicitOperation) {
            int offsetOp_ = ((ExplicitOperation) _val).getOperators().firstKey();
            AnaTypeFct function_ = ((ExplicitOperation) _val).getFunction();
            addParts(_vars, function_,
                    offsetOp_+ _sum + _val.getIndexInEl(), _vars.getKeyWords().getKeyWordExplicit().length(),
                    _val.getErrs(), _val.getErrs()
            );
            addTypes(_vars, ((ExplicitOperation) _val).getPartOffsets());
        }
        if (_val instanceof ImplicitOperation) {
            int offsetOp_ = ((ImplicitOperation) _val).getOperators().firstKey();
            AnaTypeFct function_ = ((ImplicitOperation) _val).getFunction();
            addParts(_vars, function_,
                    offsetOp_+ _sum + _val.getIndexInEl(), _vars.getKeyWords().getKeyWordCast().length(),
                    _val.getErrs(), _val.getErrs()
            );
            addTypes(_vars, ((ImplicitOperation) _val).getPartOffsets());
        }
    }

    private static void processUnarySymbol(VariablesOffsets _vars, int _sum, OperationNode _val) {
        if (_val instanceof SymbolOperation && _val.getFirstChild().getNextSibling() == null) {
            SymbolOperation par_ = (SymbolOperation) _val;
            AnaTypeFct function_ = par_.getFct().getFunction();
            if (function_ != null) {
                addParts(_vars, function_,
                        _sum + _val.getIndexInEl() + par_.getOperatorContent().getOpOffset(), function_.getFunction().getName().length(),
                        _val.getErrs(), _val.getErrs());
            } else if (!_val.getErrs().isEmpty()){
                int i_ = _sum + _val.getIndexInEl() + par_.getOperatorContent().getOpOffset();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_val.getErrs(),ExportCst.JOIN_ERR)),i_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,i_+1));
            } else if (_val instanceof RandCodeOperation && canCallRandeCode(_vars, _val.getFirstChild().getResultClass())) {
                int i_ = _sum + _val.getIndexInEl() + par_.getOperatorContent().getOpOffset();
                _vars.addPart(new PartOffset(ExportCst.HEAD_ITALIC, i_));
                _vars.addPart(new PartOffset(ExportCst.FOOT_ITALIC, i_ + 1));
            }
        }
    }

    private static void middleReport(LinkageStackElementIn _in, VariablesOffsets _vars,
                                     OperationNode _curOp,
                                     OperationNode _nextSiblingOp,
                                     MethodOperation _parent,
                                     Coverage _cov) {
        processShortTernaryReport(_in, _vars, _curOp, _parent);
        processCustomOperator(_in,_vars, _curOp,_nextSiblingOp, _parent);
        processCompoundAffLeftOpReport(_in,_vars, _curOp,_nextSiblingOp, _parent, _cov);
        processCompoundAffRightOp(_in,_vars, _parent);

        processCompareReport(_in,_vars, _parent, _cov);
        processDotSafeReport(_in,_vars, _curOp, _parent, _cov);
        processNullSafeReport(_in,_vars, _curOp, _nextSiblingOp, _parent, _cov);
        processLogicAndOrOperationReport(_in,_vars, _curOp, _nextSiblingOp, _parent, _cov);
    }

    private static void processShortTernaryReport(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _curOp, MethodOperation _parent) {
        if (_parent instanceof ImplementChoice) {
            ImplementChoice sh_ = (ImplementChoice) _parent;
            int index_ = _curOp.getIndexChild();
            int operOff_ = _in.getBeginBlock() + _parent.getIndexInEl() + _parent.getOperators().getKey(index_);
            AnaTypeFct testFct_ = null;
            if (_vars.isImplicit() && index_ == 0) {
                testFct_ = sh_.getImplFct();
            }
            if (index_ == 1) {
                testFct_ = sh_.getTestFct();
            }
            if (testFct_ != null) {
                StringList l_ = new StringList();
                addParts(_vars, testFct_,operOff_,1,l_,l_);
            }
        }
    }
    private static void middleError(LinkageStackElementIn _in,
                                    VariablesOffsets _vars,
                                    OperationNode _curOp,
                                    OperationNode _nextSiblingOp,
                                    MethodOperation _parent) {
        StringList l_ = new StringList();
        MethodOperation.processEmptyError(_curOp,l_);
        MethodOperation.processEmptyError(_nextSiblingOp,l_);
        if (middleOper(_parent)) {
            StrTypes operators_ =  _parent.getOperators();
            AnaTypeFct testFct_ = tryGetTestFct(_vars, _curOp,_parent);
            int index_ = _curOp.getIndexChild();
            if (_parent instanceof RefShortTernaryOperation && index_ == 1) {
                RefShortTernaryOperation sh_ = (RefShortTernaryOperation) _parent;
                l_.addAllElts(sh_.getChildrenErrors());
            }
            int operOff_ = _in.getBeginBlock() + _parent.getIndexInEl() + operators_.getKey(index_);
            if (testFct_ != null) {
                addParts(_vars, testFct_,operOff_,1,l_,l_);
            } else if (!l_.isEmpty()) {
                int len_ = operators_.getValue(index_).length();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(l_,ExportCst.JOIN_ERR)), operOff_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, operOff_ +Math.max(len_,1)));
            } else {
                appendPossibleParts(_vars, _parent, index_);
            }
        }
        processLogicAndOrOperationError(_in, _vars, _curOp, _parent, l_);
        processCustomOperator(_in,_vars, _curOp,_nextSiblingOp, _parent);
        processCompoundAffLeftOpError(_in,_vars, _nextSiblingOp, _parent);
        processCompoundAffRightOp(_in,_vars, _parent);
    }

    private static AnaTypeFct tryGetTestFct(VariablesOffsets _vars, OperationNode _curOp, MethodOperation _parent) {
        int index_ = _curOp.getIndexChild();
        AnaTypeFct testFct_ = null;
        if (_vars.isImplicit()&& _parent instanceof ImplementChoice && index_ == 0) {
            ImplementChoice sh_ = (ImplementChoice) _parent;
            testFct_ = sh_.getImplFct();
        }
        if (_parent instanceof ImplementChoice && index_ == 1) {
            ImplementChoice sh_ = (ImplementChoice) _parent;
            testFct_ = sh_.getTestFct();
        }
        return testFct_;
    }

    private static void processLogicAndOrOperationError(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _curOp, MethodOperation _parent, StringList _list) {
        if (_list.isEmpty()&& _curOp.getIndexChild() == 0 && _parent instanceof QuickOperation) {
            QuickOperation q_ = (QuickOperation) _parent;
            int sum_ = _in.getBeginBlock() + q_.getIndexInEl();
            int begin_ = sum_+q_.getOpOff();
            StringList errs_ = q_.getErrFirst();
            AnaTypeFct functionTest_ = q_.getFunctionTest();
            StringList title_ = new StringList();
            addParts(_vars, functionTest_,begin_,1, errs_,title_);
            errs_ = q_.getErrSecond();
            AnaTypeFct function_ = q_.getFct().getFunction();
            addParts(_vars, function_,begin_+1,1, errs_,title_);
            tryAddMergedParts(_vars, q_.getConvert(), begin_+2, new StringList(), new StringList());
        }
    }

    private static void processImplicit(LinkageStackElementIn _in, VariablesOffsets _vars,
                                        OperationNode _curOp,
                                        MethodOperation _parent) {
        if ((middleOperNotShortTernary(_parent) || _parent instanceof SemiAffectationOperation && ((SemiAffectationOperation) _parent).isPost()) && _curOp.getIndexChild() == 0) {
            int i_ = _in.getBeginBlock()+_parent.getIndexInEl()+ _parent.getOperators().firstKey();
            tryAddMergedParts(_vars, _curOp.getResultClass().getFunction(), i_, new StringList(), new StringList());
        }
    }
    private static void processCustomOperator(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _curOp,
                                              OperationNode _nextSiblingOp, MethodOperation _parentOp) {
        if (!(_parentOp instanceof AbstractUnaryOperation)&&_parentOp instanceof SymbolOperation) {
            int sum_ = _in.getBeginBlock() + _parentOp.getIndexInEl();
            SymbolOperation par_ = (SymbolOperation) _parentOp;
            AnaTypeFct function_ = par_.getFct().getFunction();
            if (function_ != null) {
                addParts(_vars, function_,sum_+par_.getOperatorContent().getOpOffset(),par_.getOperatorContent().getOper().length(),_parentOp.getErrs(),_parentOp.getErrs());
            } else if (_parentOp instanceof AddOperation && ((AddOperation)_parentOp).isCatString() && canCallToString(_vars, _curOp, _nextSiblingOp)) {
                _vars.addPart(new PartOffset(ExportCst.HEAD_ITALIC, sum_+par_.getOperatorContent().getOpOffset()));
                _vars.addPart(new PartOffset(ExportCst.FOOT_ITALIC, sum_+par_.getOperatorContent().getOpOffset() + par_.getOperatorContent().getOper().length()));
            }

        }
    }

    private static boolean canCallToString(VariablesOffsets _vars, OperationNode _curOp, OperationNode _nextSiblingOp) {
        return canCallToString(_vars,_curOp.getResultClass()) || canCallToString(_vars,_nextSiblingOp.getResultClass());
    }
    private static void processDotSafeReport(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _curOp, MethodOperation _parentOp, Coverage _cov) {
        if (_parentOp instanceof SafeDotOperation) {
            int sum_ = _in.getBeginBlock() + _parentOp.getIndexInEl() + ((SafeDotOperation)_parentOp).getOpOff();
            safeReport(_in, _vars, _curOp, _cov, sum_, 1);
        }
    }
    private static void processNullSafeReport(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _curOp, OperationNode _nextSiblingOp, MethodOperation _parentOp, Coverage _cov) {
        if (_parentOp instanceof NullSafeOperation) {
            int sum_ = _in.getBeginBlock() + _parentOp.getIndexInEl();
            int begin_ = sum_+((NullSafeOperation)_parentOp).getOpOff();
            safeReport(_in, _vars, _curOp, _cov, begin_, 1);
            safeReport(_in, _vars, _nextSiblingOp, _cov, begin_+1, 1);
        }
    }

    private static void processCompareReport(LinkageStackElementIn _in, VariablesOffsets _vars,
                                             MethodOperation _parent, Coverage _cov) {
        if (isWideCmp(_parent)) {

            int sum_ = _in.getBeginBlock() + _parent.getIndexInEl();
            int begin_ = sum_+((SymbolOperation)_parent).getOperatorContent().getOpOffset();
            int length_ = ((SymbolOperation)_parent).getOperatorContent().getOper().length();
            if (((SymbolOperation)_parent).getFct().getFunction() == null) {
                safeReport(_in, _vars, _parent, _cov, begin_, length_);
            }
        }
    }

    private static boolean isWideCmp(MethodOperation _parentOp) {
        return _parentOp instanceof EqOperation || _parentOp instanceof CmpOperation;
    }

    private static void processCompoundAffLeftOpError(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _nextSiblingOp, MethodOperation _parentOp) {
        if (!(_parentOp instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) _parentOp;
        AnaTypeFct function_ = par_.getFct().getFunction();
        int opDelta_ = par_.getOper().length() - 1;
        AnaTypeFct functionTest_ = par_.getFunctionTest();
        int sum_ = _in.getBeginBlock() + _parentOp.getIndexInEl();
        int begin_ = sum_+par_.getOpOffset();
        int len_ = opDelta_;
        if (functionTest_ != null) {
            addParts(_vars, functionTest_,begin_,1, _parentOp.getErrs(),_parentOp.getErrs());
            begin_++;
            len_--;
        }
        if (function_ != null) {
            addParts(_vars, function_,begin_,len_,_parentOp.getErrs(),_parentOp.getErrs());
        } else if (hasToCallStringConver(_vars, _nextSiblingOp)){
            _vars.addPart(new PartOffset(ExportCst.HEAD_ITALIC, begin_));
            _vars.addPart(new PartOffset(ExportCst.FOOT_ITALIC,begin_+len_));
        }
    }

    private static boolean hasToCallStringConver(VariablesOffsets _vars, OperationNode _nextSiblingOp) {
        return _nextSiblingOp.getResultClass().isConvertToString() && canCallToString(_vars,_nextSiblingOp.getResultClass());
    }

    private static void processCompoundAffLeftOpReport(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _curOp, OperationNode _nextSiblingOp, MethodOperation _parentOp, Coverage _cov) {
        if (!(_parentOp instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) _parentOp;
        AnaTypeFct function_ = par_.getFct().getFunction();
        AnaTypeFct functionTest_ = par_.getFunctionTest();
        int opDelta_ = par_.getOper().length() - 1;
        int sum_ = _in.getBeginBlock() + _parentOp.getIndexInEl();
        int begin_ = sum_+par_.getOpOffset();
        int len_ = opDelta_;
        if (functionTest_ != null) {
            StringList title_ = new StringList();
            title_.addAllElts(getCoversFoundReport(_in, _vars, _curOp, _cov));
            addParts(_vars, functionTest_,begin_,1, _parentOp.getErrs(),title_);
            begin_++;
            len_--;
            if (StringUtil.quickEq(par_.getOper(),AbsBk.AND_LOG_EQ_SHORT)
                    || StringUtil.quickEq(par_.getOper(),AbsBk.OR_LOG_EQ_SHORT)){
                begin_++;
                len_--;
            }
        } else if (StringUtil.quickEq(par_.getOper(), AbsBk.AND_LOG_EQ)
                || StringUtil.quickEq(par_.getOper(), AbsBk.OR_LOG_EQ)
                || StringUtil.quickEq(par_.getOper(), AbsBk.NULL_EQ)){
            safeReport(_in, _vars, _curOp, _cov, begin_, 1);
            begin_++;
            len_--;
        } else if (StringUtil.quickEq(par_.getOper(),AbsBk.AND_LOG_EQ_SHORT)
                || StringUtil.quickEq(par_.getOper(),AbsBk.OR_LOG_EQ_SHORT)
                ||StringUtil.quickEq(par_.getOper(),AbsBk.NULL_EQ_SHORT)){
            safeReport(_in, _vars, _curOp, _cov, begin_, 1);
            begin_+=2;
            len_-=2;
        }
        if (function_ != null) {
            addParts(_vars, function_,begin_,len_,_parentOp.getErrs(),_parentOp.getErrs());
        } else if (hasToCallStringConver(_vars, _nextSiblingOp)){
            _vars.addPart(new PartOffset(ExportCst.HEAD_ITALIC, begin_));
            _vars.addPart(new PartOffset(ExportCst.FOOT_ITALIC,begin_+len_));
        } else if (par_.isNullSafe()||par_.isRightBool()) {
            safeReport(_in, _vars, _nextSiblingOp, _cov, begin_, 1);
        }
    }
    private static void processCompoundAffRightOp(LinkageStackElementIn _in, VariablesOffsets _vars, MethodOperation _parentOp) {
        if (!(_parentOp instanceof CompoundAffectationOperation)) {
            return;
        }
        CompoundAffectationOperation par_ = (CompoundAffectationOperation) _parentOp;
        int opDelta_ = par_.getOper().length() - 1;
        int sum_ = _in.getBeginBlock() + _parentOp.getIndexInEl();
        int begin_ = sum_+par_.getOpOffset();
        SettableElResult settable_ = par_.getSettable();
        if (settable_ instanceof ArrOperation) {
            addParts(_vars, ((ArrOperation) settable_).getFunctionSet(),opDelta_+begin_,1,_parentOp.getErrs(),_parentOp.getErrs());
        } else {
            addParts(_vars, null,opDelta_+begin_,1,_parentOp.getErrs(),_parentOp.getErrs());
        }
        tryAddMergedParts(_vars, par_.getFunctionImpl(), opDelta_+begin_+1, new StringList(), new StringList());
    }
    private static void processLogicAndOrOperationReport(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _curOp, OperationNode _nextSiblingOp, MethodOperation _parentOp, Coverage _cov) {
        if (!(_parentOp instanceof QuickOperation)) {
            return;
        }
        QuickOperation q_ = (QuickOperation) _parentOp;
        int sum_ = _in.getBeginBlock() + _parentOp.getIndexInEl();
        int begin_ = sum_+q_.getOpOff();
        StringList errs_ = q_.getErrs();
        AnaTypeFct functionTest_ = q_.getFunctionTest();
        if (functionTest_ != null) {
            StringList title_ = new StringList();
            title_.addAllElts(getCoversFoundReport(_in, _vars, _curOp, _cov));
            addParts(_vars, functionTest_,begin_,1, errs_,title_);
        } else {
            safeReport(_in,_vars,_curOp,_cov, begin_, 1);
        }
        AnaTypeFct function_ = q_.getFct().getFunction();
        if (function_ != null) {
            StringList title_ = new StringList();
            title_.addAllElts(getCoversFoundReport(_in, _vars, _nextSiblingOp, _cov));
            addParts(_vars, function_,begin_+1,1, errs_,title_);
        } else {
            safeReport(_in,_vars,_nextSiblingOp,_cov, begin_+1, 1);
        }
        tryAddMergedParts(_vars, q_.getConvert(), begin_+2, new StringList(), new StringList());
    }

    private static void right(LinkageStackElementIn _in, VariablesOffsets _vars,
                              MethodOperation _parent) {
        processRightIndexer(_in,_vars, _parent);
        if (_parent instanceof AnnotationInstanceOperation
                || _parent instanceof IdOperation) {
            _vars.addParts(convert(_parent.getPartOffsetsEnd()));
        }
    }

    private static void processRightIndexer(LinkageStackElementIn _in, VariablesOffsets _vars, MethodOperation _parentOp) {
        if (_parentOp instanceof ArrOperation) {
            ArrOperation par_ = (ArrOperation) _parentOp;
            AnaTypeFct function_;
            if (par_.getArrContent().isVariable()) {
                function_ = par_.getFunctionSet();
            } else {
                function_ = par_.getFunctionGet();
            }
            int begin_ = _in.getBeginBlock() + par_.getIndexInEl() + par_.getLastOpOffset();
            if (function_ != null) {
                addParts(_vars, function_,begin_,1,new StringList(),new StringList());
            } else {
                String er_ = par_.getNbErr();
                if (!er_.isEmpty()) {
                    addParts(_vars, null,begin_,1,new StringList(er_),new StringList(er_));
                }
            }
        }
    }

    private static void processUnaryRightOperations(LinkageStackElementIn _in, VariablesOffsets _vars,
                                                    OperationNode _curOp, MethodOperation _parent) {
        if (_curOp.getIndexChild() == 0 &&_parent instanceof InstanceOfOperation) {
            if (!_parent.getErrs().isEmpty()) {
                int begin_ = ((InstanceOfOperation)_parent).getOffset()+_in.getBeginBlock() + _parent.getIndexInEl();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_parent.getErrs(),ExportCst.JOIN_ERR)),begin_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,begin_+_vars.getKeyWords().getKeyWordInstanceof().length()));
            }
            _vars.addParts(export(((InstanceOfOperation)_parent).getPartOffsets()));
        }
        if (_parent instanceof RangeOperation && _parent.getChildrenNodes().size() == 1) {
            StringList l_ = new StringList();
            MethodOperation.processEmptyError(_curOp,l_);
            if (!l_.isEmpty()) {
                int i_ = _in.getBeginBlock()+_parent.getIndexInEl()+((RangeOperation) _parent).getOpOffset();
                _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(l_,ExportCst.JOIN_ERR)), i_));
                _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, i_ +3));
            } else {
                appendPossibleParts(_vars, _parent, 0);
            }
        }
        processPostIncr(_in,_vars, _curOp,_parent);
    }

    private static void processPostIncr(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _curOp, MethodOperation _parent) {
        if (_curOp.getIndexChild() == 0&&_parent instanceof SemiAffectationOperation) {
            SemiAffectationOperation par_ = (SemiAffectationOperation) _parent;
            int beginBlock_ = _in.getBeginBlock();
            int sum_ = beginBlock_ + par_.getIndexInEl();
            int opOff_ = sum_+par_.getOpOffset();
            if(par_.isPost()) {
                tryAddMergedParts(_vars,par_.getFunctionTo(), opOff_, new StringList(), new StringList());
                boolean err_ = true;
                AnaTypeFct function_ = par_.getFct().getFunction();
                if (function_ != null) {
                    addParts(_vars, function_,opOff_,1,_parent.getErrs(),_parent.getErrs());
                    err_ = false;
                }
                SettableElResult settable_ = par_.getSettable();
                if (settable_ instanceof ArrOperation && ((ArrOperation) settable_).getFunctionSet() != null) {
                    ArrOperation parArr_ = (ArrOperation) settable_;
                    addParts(_vars, parArr_.getFunctionSet(),opOff_+1,1,_parent.getErrs(),_parent.getErrs());
                    err_ = false;
                }
                if (err_ && !_parent.getErrs().isEmpty()) {
                    _vars.addPart(new PartOffset(ExportCst.anchorErr(StringUtil.join(_parent.getErrs(), ExportCst.JOIN_ERR)), opOff_));
                    _vars.addPart(new PartOffset(ExportCst.END_ANCHOR, opOff_ + 2));
                }
            }
        }
    }

    private static void safeReport(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _parent, Coverage _cov, int _begin, int _length) {
        StringList founds_ = getCoversFoundReport(_in, _vars, _parent, _cov);
        if (founds_.isEmpty()) {
            return;
        }
        basicValue(_vars, _begin, _length, founds_);
    }

    private static StringList getCoversFoundReport(LinkageStackElementIn _in, VariablesOffsets _vars, OperationNode _parent, Coverage _cov) {
        if (_in.skipReportElement()) {
            return new StringList();
        }
        AbstractCoverageResult resultLoc_ = getCovers(_in, _parent, _cov);
        return resultLoc_.getCoversFoundReport(_vars);
    }

    private static void basicValue(VariablesOffsets _vars, int _offsetEnd, int _delta, StringList _founds) {
        _vars.addPart(new PartOffset(ExportCst.anchor(StringUtil.join(_founds,ExportCst.FOUND_COVERAGE)), _offsetEnd));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,_offsetEnd+ _delta));
    }

    private static void mergeParts(VariablesOffsets _vars,
                                         AnaTypeFct _id, int _begin,
                                         StringList _errors,
                                         StringList _title) {
        String head_ = addBeginPart(_vars, _id, _errors, _title, -1);
        if (head_.isEmpty()) {
            return;
        }
        _vars.addPart(new PartOffset(head_+ExportCst.IMPLICIT+ExportCst.END_ANCHOR,_begin));
    }
    private static void addParts(VariablesOffsets _vars,
                                                 AnaTypeFct _id, int _begin, int _length,
                                                 StringList _errors,
                                                 StringList _title) {
        addParts(_vars, _id, _begin, _length, _errors, _title, -1);
    }

    private static void addParts(VariablesOffsets _vars, AnaTypeFct _id,
                                                 int _begin, int _length, StringList _errors, StringList _title, int _name) {
        String head_ = addBeginPart(_vars, _id, _errors, _title, _name);
        addEnd(_vars, head_,_begin,_length);
    }

    private static String addBeginPart(VariablesOffsets _vars, AnaTypeFct _id,
                                       StringList _errors, StringList _title, int _name) {
        DisplayedStrings dis_ = _vars.getDisplayedStrings();
        String rel_ = getRelativize(_vars.getCurrentFile(), _id);
        if (rel_.isEmpty()) {
            if (!_errors.isEmpty()) {
                return ExportCst.anchorErr(StringUtil.join(_errors,ExportCst.JOIN_ERR));
            }
            return "";
        }
        return buildLink(_id, _errors, _title, _name, dis_, rel_);
    }
    private static void addEnd(VariablesOffsets _vars, String _head, int _begin, int _length) {
        if (!_head.isEmpty()) {
            _vars.addPart(new PartOffset(_head,_begin));
            _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,_begin+ _length));
        }
    }
    private static String buildLink(AnaTypeFct _id, StringList _errors, StringList _title, int _name, DisplayedStrings _dis, String _rel) {
        NamedFunctionBlock function_ = _id.getFunction();
        String signature_ = function_.getSignature(_dis);
        String tag_;
        if (function_ instanceof OperatorBlock) {
            tag_ = ExportCst.BEGIN_ANCHOR+name(_name)+ ExportCst.title(_title, signature_)+ExportCst.SEP_ATTR
                    +_rel+classErr(_errors)+ExportCst.END;
        } else {
            String cl_ = _id.getType().getFullName();
            tag_ = ExportCst.BEGIN_ANCHOR+name(_name)+ ExportCst.title(_title,cl_ +ExportCst.SEP_TYPE_MEMBER+ signature_)+ExportCst.SEP_ATTR
                    +_rel+classErr(_errors)+ExportCst.END;
        }
        return tag_;
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

    private static String getRelativize(FileBlock _block, AnaTypeFct _id) {
        if (_id == null) {
            return EMPTY;
        }
        NamedFunctionBlock function_ = _id.getFunction();
        if (function_ == null) {
            return EMPTY;
        }
        if (function_ instanceof OperatorBlock) {
            return ExportCst.href(_block, function_.getFile(), function_.getNameOffset());
        }
        if (!ContextUtil.isFromCustFile(_id.getType())) {
            return EMPTY;
        }
        return ExportCst.href(_block, function_.getFile(), function_.getNameOffset());
    }

    private static AbstractCoverageResult getCovers(LinkageStackElementIn _in, OperationNode _oper, Coverage _cov) {
        return _cov.getCovers(_in.getBlock(),_oper, _in.getIndexAnnotationGroupLook(), _in.getIndexAnnotationLook());
    }

    private static void updateFieldAnchor(VariablesOffsets _vars, RootBlock _type, StringList _errs, ClassField _id, int _begin, int _length, int _offset) {
        String head_ = addBeginFieldPart(_type, _errs, _id, _vars.getCurrentFile(), _offset);
        addEnd(_vars, head_,_begin,_length);
    }
    private static String addBeginFieldPart(RootBlock _type, StringList _errs, ClassField _id, FileBlock _currentFileName, int _offset) {
        if (!ContextUtil.isFromCustFile(_type)) {
            if (!_errs.isEmpty()) {
                return ExportCst.anchorErr(StringUtil.join(_errs,ExportCst.JOIN_ERR));
            }
            return "";
        }
        String rel_ = ExportCst.href(_currentFileName, _type.getFile(), _offset);
        return ExportCst.BEGIN_ANCHOR+ExportCst.SEP_ATTR
                +ExportCst.title(_errs,StringExpUtil.getIdFromAllTypes(_id.getClassName()) +ExportCst.SEP_TYPE_MEMBER+ _id.getFieldName())+ExportCst.SEP_ATTR
                +rel_+classErr(_errs)+ExportCst.END;
    }

    static String transform(String _string) {
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
        StringList toStringOwners_ = _vars.getToStringOwners();
        return canCall(_type, toStringOwners_);
    }
    private static boolean canCallRandeCode(VariablesOffsets _vars, AnaClassArgumentMatching _type) {
        StringList toStringOwners_ = _vars.getRandCodeOwners();
        return canCall(_type, toStringOwners_);
    }
    private static boolean canCall(AnaClassArgumentMatching _type, StringList _randCodeOwners) {
        if (_type.isArray()) {
            return false;
        }
        for (String s: _type.getNames()) {
            String id_ = StringExpUtil.getIdFromAllTypes(s);
            if (StringUtil.contains(_randCodeOwners,id_)) {
                return true;
            }
        }
        return false;
    }

    private static void refLabel(VariablesOffsets _vars, String _label, int _offset) {
        if (_label.isEmpty()) {
            return;
        }
        if (_vars.goesToProcess()) {
            return;
        }
        _vars.addPart(new PartOffset(ExportCst.anchorName(_offset),_offset));
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,_offset+_label.length()));
    }

    private static void refLabelError(VariablesOffsets _vars, AbsBk _bl, String _label, int _offset) {
        if (_label.isEmpty()) {
            return;
        }
        if (_vars.goesToProcess()) {
            return;
        }
        StringList errs_ = _bl.getErrorsLabels();
        if (!errs_.isEmpty()) {
            _vars.addPart(new PartOffset(ExportCst.anchorNameErr(_offset,StringUtil.join(errs_,ExportCst.JOIN_ERR)),_offset));
        } else {
            _vars.addPart(new PartOffset(ExportCst.anchorName(_offset),_offset));
        }
        _vars.addPart(new PartOffset(ExportCst.END_ANCHOR,_offset+_label.length()));
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
        if (_op instanceof AbstractDotOperation && !((AbstractDotOperation)_op).getOperators().firstValue().isEmpty()) {
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
        if (_op instanceof RangeOperation) {
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
    private static CustList<PartOffset> convert(InfoErrorDto _info) {
        String message_ = _info.getMessage();
        if (message_.isEmpty()) {
            return new CustList<PartOffset>();
        }
        CustList<PartOffset> list_ = new CustList<PartOffset>();
        if (_info.isWarn()) {
            list_.add(new PartOffset(ExportCst.anchorWar(message_),_info.getBegin()));
        } else {
            list_.add(new PartOffset(ExportCst.anchorErr(message_),_info.getBegin()));
        }
        list_.add(new PartOffset(ExportCst.END_ANCHOR,_info.getBegin()+_info.getLength()));
        return list_;
    }

    private static void appendTitleParts(int _begin, int _end, String _in, CustList<PartOffset> _parts) {
        _parts.add(new PartOffset(ExportCst.anchor(_in),_begin));
        _parts.add(new PartOffset(ExportCst.END_ANCHOR,_end));
    }
}
