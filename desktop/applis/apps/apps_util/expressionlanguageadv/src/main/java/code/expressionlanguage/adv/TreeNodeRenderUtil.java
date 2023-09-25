package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.syntax.FileBlockIndex;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.dbg.StrResultContextLambda;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.InterruptibleContextEl;
import code.gui.AbsPlainButton;
import code.gui.AbsTextArea;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.initialize.AbsCompoFactory;
import code.threads.AbstractThreadFactory;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class TreeNodeRenderUtil {
    public static final String BEGIN_ENCODE = "&#";
    public static final String END_ENCODE = ";";
    public static final String SPAN = "span";
    public static final String HTML = "html";
    public static final String STYLE = "style";
    public static final String COLOR = "color";
    public static final String BACKGROUND_COLOR = "background-" + COLOR;
    public static final String BLACK = "000000";
    public static final String WHITE = "ffffff";
    public static final String RED = "ff0000";
    public static final String CYAN = "00ffff";
    public static final String EXT_SPACE = "&#160;";

    private TreeNodeRenderUtil() {
    }

    static void renderNode(RenderPointPair _renderPointPairs, AbsTreeGui _tree, AbstractMutableTreeNodeCore<String> _tr, DbgNodeStruct _node, AbsCompoFactory _compo, AbstractThreadFactory _th) {
        String res_ = resultWrap(_renderPointPairs,_node, _compo, _th);
        if (_node.value() == null) {
            return;
        }
        String render_ = format(_node, res_);
        _compo.invokeNow(new FinalRenderingTask(_tree,_tr,render_));
    }

    static String resultWrap(RenderPointPair _renderPointPairs, DbgNodeStruct _node, AbsCompoFactory _compo, AbstractThreadFactory _th) {
        Struct res_ = result(_renderPointPairs, _node, _compo, _th);
        _node.feedChildren(_compo);
        if (res_ == null) {
            return "";
        }
        String v_ = wrapValueInner(res_, _node.getResult());
        _node.repr(v_);
        return wrapValue(v_);
    }
    static Struct result(RenderPointPair _renderPointPairs, DbgNodeStruct _node, AbsCompoFactory _compo, AbstractThreadFactory _th) {
        if (_renderPointPairs == null) {
            return _node.value();
        }
        ResultContextLambda rLda_ = checkExc(_renderPointPairs);
        return result(rLda_, _node, _compo, _th);
    }

    private static Struct result(ResultContextLambda _rLda, DbgNodeStruct _node, AbsCompoFactory _compo, AbstractThreadFactory _th) {
        ContextEl ctx_ = local(_node.getResult(), _th);
        AdvLogDbg logger_ = logger(_node, _compo, ctx_);
        StackCall st_ = StackCall.newInstance(new DefStackStopper(logger_), InitPhase.NOTHING, ctx_, ctx_.getExecutionInfos().getSeed());
        Struct str_ = _node.value();
        if (_rLda != null) {
            String clName_ = str_.getClassName(ctx_);
            StackCallReturnValue result_ = _rLda.eval(new CoreCheckedExecOperationNodeInfos(ExecFormattedRootBlock.build(clName_, ctx_.getClasses()), str_), null);
            CallingState stateAfter_ = result_.getStack().getCallingState();
            if (stateAfter_ != null) {
                for (String l: ResultContextLambda.traceView(result_.getStack(),ctx_)) {
                    logger_.log(l);
                }
            }
            return ExecCatOperation.getDisplayable(result_.getStack().aw().getValue(),ctx_);
        }
        IndirectCalledFctUtil.processString(ArgumentListCall.toStr(str_), ctx_, st_);
        CallingState state_ = st_.getCallingState();
        Struct res_;
        if (str_ instanceof ArrayStruct || str_ instanceof DisplayableStruct) {
            res_ = str_;
        } else if (state_ == null) {
            res_ = null;
        } else {
            res_ = ExecCatOperation.getDisplayable(ProcessMethod.calculate(state_, ctx_, st_).getValue(), ctx_);
            CallingState stateAfter_ = st_.getCallingState();
            if (stateAfter_ != null) {
                for (String f: ResultContextLambda.traceView(st_, ctx_)) {
                    logger_.log(f);
                }
            }
        }
        return res_;
    }

    private static ResultContextLambda checkExc(RenderPointPair _bp) {
        StrResultContextLambda bpc_ = stopExcValue(_bp);
        return stopCurrent(bpc_);
    }

    private static ResultContextLambda stopCurrent(StrResultContextLambda _condition) {
        if (_condition == null) {
            return null;
        }
        return _condition.getResult();
    }

    private static StrResultContextLambda stopExcValue(RenderPointPair _ex) {
        if (!_ex.getExcPointBlockPair().getValue().isEnabled()) {
            return null;
        }
        return _ex.getRender();
    }
    static AdvLogDbg logger(DbgNodeStruct _node, AbsCompoFactory _compo, ContextEl _ctx) {
        AbsTextArea ta_ = _compo.newTextArea();
        ta_.setEditable(false);
        _node.logs(ta_);
        AbsPlainButton stop_ = _compo.newPlainButton("stop");
        stop_.addActionListener(new DbgStopRenderEvent(_ctx));
        _node.stopButton(stop_);
        _node.panel(_compo.newVerticalSplitPane(_compo.newAbsScrollPane(ta_), stop_));
        return new AdvLogDbg(ta_);
    }

    private static ContextEl local(ContextEl _orig, AbstractThreadFactory _compo) {
        return new InterruptibleContextEl(_compo.newAtomicBoolean(), _orig.getExecutionInfos());
//        return new GuiContextEl(_compo.newAtomicBoolean(), null, _orig.getExecutionInfos(), new StringList());
    }
    static String format(DbgNodeStruct _node) {
        return format(_node, wrapValue(_node));
    }

    private static String wrapValue(DbgNodeStruct _node) {
        return wrapValue(wrapValueInner(_node.value(), _node.getResult()));
    }

    static String format(DbgNodeStruct _node, String _value) {
        return "<" + HTML + "><" + SPAN + " " + STYLE + "='" + BACKGROUND_COLOR + ":#"+BLACK+";'>"
                + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+RED+";'>" + transform(_node.str()) + "</" + SPAN + ">"
                + EXT_SPACE
                + wrapTypeDecl(_node.value().getClassName(_node.getResult()))
                + EXT_SPACE
                + _value
                + "</" + SPAN + ">"
                + "</" + HTML + ">";
    }
    private static String wrapTypeDecl(String _type) {
        return "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+WHITE+";'>" +transform(_type) + "</" + SPAN + ">";
    }

    private static String wrapValue(String _v) {
        if (!_v.isEmpty()) {
            return "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+CYAN+";'>" + transform(_v) + "</" + SPAN +">";
        }
        return "";
    }

    private static String wrapValueInner(Struct _str, ContextEl _ctx) {
        if (_str instanceof ArrayStruct) {
            return Long.toString(((ArrayStruct)_str).getLength());
        }
        if (_str instanceof DisplayableStruct) {
            return ((DisplayableStruct)_str).getDisplayedString(_ctx).getInstance();
        }
        return "";
    }
    static String locations(FileBlockIndex _elt) {
        String content_ = resource(_elt.getFile());
        int lenContent_ = content_.length();
        int locIndex_ = _elt.getIndex();
        if (locIndex_ >= lenContent_) {
            return "";
        }
        int min_ = NumberUtil.max(0, content_.lastIndexOf('\n', locIndex_));
        int endLine_ = content_.indexOf('\n', locIndex_);
        if (endLine_ < 0) {
            endLine_ = lenContent_ - 1;
        }
        int max_ = NumberUtil.min(lenContent_, endLine_ +1);
        int until_ = ElResolver.incrAfterWord(locIndex_,content_);
        String before_ = transform(content_.substring(min_, locIndex_));
        String occurrence_ = transform(content_.substring(locIndex_,until_));
        String after_ = transform(content_.substring(until_,max_));
        return "<" + HTML + "><" + SPAN + " " + STYLE + "='" + BACKGROUND_COLOR + ":#"+BLACK+";'>"
            + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+RED+";'>" + locIndex_ + "</" + SPAN + ">"
            + EXT_SPACE
            + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+WHITE+";'>" +before_+ "</" + SPAN + ">"
            + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+RED+";'>" +occurrence_+ "</" + SPAN + ">"
            + "<" + SPAN + " " + STYLE + "='" + COLOR + ":#"+WHITE+";'>" +after_+ "</" + SPAN + ">"
            + "</" + SPAN + ">"
            + "</" + HTML + ">";
    }

    static String resource(FileBlock _res) {
        if (_res == null) {
            return "";
        }
        return StringUtil.nullToEmpty(_res.getContent());
    }
    static String transform(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _string.toCharArray()) {
            str_.append(escapeNum(c));
        }
        return str_.toString();
    }
    private static String escapeNum(char _ch) {
        return BEGIN_ENCODE +(int)_ch+ END_ENCODE;
    }
}
