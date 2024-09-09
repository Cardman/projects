package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.instr.ElResolver;
import code.expressionlanguage.analyze.syntax.FileBlockIndex;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.dbg.StrResultContextLambda;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractLightProgramInfos;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class TreeNodeRenderUtil {

    private TreeNodeRenderUtil() {
    }

    static void renderNode(RenderPointInfosPreference _renderPointPairs, AbsTreeGui _tree, AbstractMutableTreeNodeCore<String> _tr, DbgNodeStruct _node, AbstractLightProgramInfos _compo) {
        Struct res_ = resultWrap(_renderPointPairs,_node, _compo);
        if (_node.value() == null) {
            return;
        }
        _node.repr(res_);
        String render_ = format(_node);
        _compo.getCompoFactory().invokeNow(new FinalRenderingTask(_tree,_tr,render_));
    }

    static Struct resultWrap(RenderPointInfosPreference _renderPointPairs, DbgNodeStruct _node, AbstractLightProgramInfos _compo) {
        return result(_renderPointPairs, _node, _compo);
    }
    static Struct result(RenderPointInfosPreference _renderPointPairs, DbgNodeStruct _node, AbstractLightProgramInfos _compo) {
        AbsCompoFactory c_ = _compo.getCompoFactory();
        AbstractThreadFactory th_ = _compo.getThreadFactory();
        if (_renderPointPairs == null) {
            Struct res_ = _node.value();
            _node.feedChildren(c_);
            return res_;
        }
        AbsTextArea ta_ = c_.newTextArea();
        ta_.setEditable(false);
        _node.logs(ta_);
        StringMap<String> mes_ = MessagesIde.valSessionForm(_compo.currentLg());
        AbsButton stop_ = c_.newPlainButton(StringUtil.nullToEmpty(mes_.getVal(MessagesIde.IDE_POINTS_SESSION_FORM_CANCEL_RENDER)));
        _node.stopButton(stop_);
        _node.panel(c_.newVerticalSplitPane(c_.newAbsScrollPane(ta_), stop_));
        AdvLogDbg logger_ = new AdvLogDbg(ta_);
        RenderPointPair rp_ = _renderPointPairs.getBreakPointCondition();
        if (rp_.isExpandRenderFirst()) {
            ResultContextLambda er_ = stopCurrent(rp_.getExpandRender());
            if (okExpandRend(er_, rp_)) {
                return expandRender(er_,_renderPointPairs,_node,c_,th_,stop_,logger_);
            }
            ResultContextLambda re_ = stopCurrent(rp_.getRenderExpand());
            if (okRendExp(re_, rp_)) {
                return renderExpand(re_,_renderPointPairs,_node,c_,th_,stop_,logger_);
            }
        } else {
            ResultContextLambda re_ = stopCurrent(rp_.getRenderExpand());
            if (okRendExp(re_, rp_)) {
                return renderExpand(re_,_renderPointPairs,_node,c_,th_,stop_,logger_);
            }
            ResultContextLambda er_ = stopCurrent(rp_.getExpandRender());
            if (okExpandRend(er_, rp_)) {
                return expandRender(er_,_renderPointPairs,_node,c_,th_,stop_,logger_);
            }
        }
        Struct res_;
        if (rp_.isExpandFirst()) {
            expand(_renderPointPairs, _node, c_, th_, stop_, logger_);
            res_ = computeStr(_renderPointPairs,_node,th_,stop_, logger_);
        } else {
            res_ = computeStr(_renderPointPairs, _node, th_, stop_, logger_);
            expand(_renderPointPairs, _node, c_, th_, stop_, logger_);
        }
        return res_;
    }

    private static boolean okRendExp(ResultContextLambda _re, RenderPointPair _rp) {
        return _re != null && _rp.isEnableBothExpand();
    }

    private static boolean okExpandRend(ResultContextLambda _re, RenderPointPair _rp) {
        return _re != null && _rp.isEnableBothRender();
    }

    private static Struct renderExpand(ResultContextLambda _re,RenderPointInfosPreference _renderPointPairs, DbgNodeStruct _node, AbsCompoFactory _compo, AbstractThreadFactory _th, AbsButton _stop, AdvLogDbg _logger) {
        ContextEl ctxExp_ = gene(_re, _th, _stop);
        DefStackStopper stopper_ = new DefStackStopper(_logger);
        StackCallReturnValue result_ = _re.eval(ctxExp_, stopper_,_renderPointPairs.build(), null);
        logTrace(result_.getStack(), ctxExp_, _logger);
        StackCall st_ = ResultContextLambda.newInstance(stopper_, ctxExp_, InitPhase.NOTHING);
        String pairType_ = ctxExp_.getStandards().getPredefTypes().getAliasPairType();
        String firstMethod_ = ctxExp_.getStandards().getPredefTypes().getAliasGetFirst();
        Struct pair_ = result_.getStack().aw().getValue();
        getOrRedirect(pair_, ctxExp_, st_,pairType_,firstMethod_);
        Struct repr_ = ProcessMethod.calculate(st_.getCallingState(), ctxExp_, st_).getValue();
        logTrace(st_, ctxExp_, _logger);
        String secondMethod_ = ctxExp_.getStandards().getPredefTypes().getAliasGetSecond();
        getOrRedirect(pair_, ctxExp_, st_,pairType_,secondMethod_);
        Struct map_ = ProcessMethod.calculate(st_.getCallingState(), ctxExp_, st_).getValue();
        logTrace(st_, ctxExp_, _logger);
        retrieveChildren(_node, _compo, ctxExp_, _logger, st_, map_);
        return repr_;
    }

    private static ContextEl gene(ResultContextLambda _re, AbstractThreadFactory _th, AbsButton _stop) {
        GuiBaseUtil.removeActionListeners(_stop);
        ContextEl ctx_ = _re.getContext();
        ContextEl ctxExp_ = generate(_th, ctx_);
        _stop.addActionListener(new DbgStopRenderEvent(ctxExp_));
        return ctxExp_;
    }

    private static Struct expandRender(ResultContextLambda _re,RenderPointInfosPreference _renderPointPairs, DbgNodeStruct _node, AbsCompoFactory _compo, AbstractThreadFactory _th, AbsButton _stop, AdvLogDbg _logger) {
        ContextEl ctxExp_ = gene(_re, _th, _stop);
        DefStackStopper stopper_ = new DefStackStopper(_logger);
        StackCallReturnValue result_ = _re.eval(ctxExp_, stopper_,_renderPointPairs.build(), null);
        logTrace(result_.getStack(), ctxExp_, _logger);
        StackCall st_ = ResultContextLambda.newInstance(stopper_, ctxExp_, InitPhase.NOTHING);
        String pairType_ = ctxExp_.getStandards().getPredefTypes().getAliasPairType();
        String firstMethod_ = ctxExp_.getStandards().getPredefTypes().getAliasGetFirst();
        Struct pair_ = result_.getStack().aw().getValue();
        getOrRedirect(pair_, ctxExp_, st_,pairType_,firstMethod_);
        Struct map_ = ProcessMethod.calculate(st_.getCallingState(), ctxExp_, st_).getValue();
        logTrace(st_, ctxExp_, _logger);
        retrieveChildren(_node, _compo, ctxExp_, _logger, st_, map_);
        String secondMethod_ = ctxExp_.getStandards().getPredefTypes().getAliasGetSecond();
        getOrRedirect(pair_, ctxExp_, st_,pairType_,secondMethod_);
        Struct repr_ = ProcessMethod.calculate(st_.getCallingState(), ctxExp_, st_).getValue();
        logTrace(st_, ctxExp_, _logger);
        return repr_;
    }
    private static Struct computeStr(RenderPointInfosPreference _renderPointPairs, DbgNodeStruct _node, AbstractThreadFactory _th, AbsButton _stop, AdvLogDbg _logger) {
        GuiBaseUtil.removeActionListeners(_stop);
        ResultContextLambda rend_ = checkExc(_renderPointPairs);
        ContextEl ctxRend_ = local(_node.getResult(), _th, rend_);
        _stop.addActionListener(new DbgStopRenderEvent(ctxRend_));
        return result(_renderPointPairs, rend_, _node, ctxRend_, _logger);
    }

    private static void expand(RenderPointInfosPreference _renderPointPairs, DbgNodeStruct _node, AbsCompoFactory _compo, AbstractThreadFactory _th, AbsButton _stop, AdvLogDbg _logger) {
        GuiBaseUtil.removeActionListeners(_stop);
        ResultContextLambda exp_ = checkExcExp(_renderPointPairs);
        ContextEl ctxExp_ = local(_node.getResult(), _th, exp_);
        _stop.addActionListener(new DbgStopRenderEvent(ctxExp_));
        resultTable(_renderPointPairs, exp_, _node, _compo, ctxExp_, _logger);
    }

    private static Struct result(RenderPointInfosPreference _rp, ResultContextLambda _rLda, DbgNodeStruct _node, ContextEl _ctx, AdvLogDbg _logger) {
        DefStackStopper stopper_ = new DefStackStopper(_logger);
        Struct str_ = _node.value();
        if (_rLda != null) {
            StackCallReturnValue result_ = _rLda.eval(_ctx,stopper_,_rp.build(), null);
            logTrace(result_.getStack(), _ctx, _logger);
            return ExecCatOperation.getDisplayable(result_.getStack().aw().getValue(), _ctx);
        }
        StackCall st_ = ResultContextLambda.newInstance(stopper_, _ctx, InitPhase.NOTHING);
        IndirectCalledFctUtil.processString(str_, _ctx, st_);
        CallingState state_ = st_.getCallingState();
        Struct res_;
        if (str_ instanceof ArrayStruct || str_ instanceof DisplayableStruct) {
            res_ = str_;
        } else if (state_ == null) {
            res_ = null;
        } else {
            res_ = ExecCatOperation.getDisplayable(ProcessMethod.calculate(state_, _ctx, st_).getValue(), _ctx);
            logTrace(st_, _ctx, _logger);
        }
        return res_;
    }
    private static void resultTable(RenderPointInfosPreference _rp,ResultContextLambda _rLda, DbgNodeStruct _node, AbsCompoFactory _compo, ContextEl _ctx, AdvLogDbg _logger) {
        if (_rLda == null) {
            _node.feedChildren(_compo);
            return;
        }
        DefStackStopper stopper_ = new DefStackStopper(_logger);
        StackCallReturnValue result_ = _rLda.eval(_ctx, stopper_,_rp.build(), null);
        logTrace(result_.getStack(), _ctx, _logger);
        StackCall st_ = ResultContextLambda.newInstance(stopper_, _ctx, InitPhase.NOTHING);

        retrieveChildren(_node, _compo, _ctx, _logger, st_, result_.getStack().aw().getValue());
    }

    private static void retrieveChildren(DbgNodeStruct _node, AbsCompoFactory _compo, ContextEl _ctx, AdvLogDbg _logger, StackCall _st, Struct _iterable) {
        String table_ = _ctx.getStandards().getPredefTypes().getAliasIterableTable();
        String iteratorMethod_ = _ctx.getStandards().getPredefTypes().getAliasIteratorTable();
        getOrRedirect(_iterable, _ctx, _st,table_,iteratorMethod_);
        Struct map_ = ProcessMethod.calculate(_st.getCallingState(), _ctx, _st).getValue();
        logTrace(_st, _ctx, _logger);
        while (true) {
            String iteratorType_ = _ctx.getStandards().getPredefTypes().getAliasIteratorTableType();
            String hasNextMethod_ = _ctx.getStandards().getPredefTypes().getAliasHasNextPair();
            getOrRedirect(map_, _ctx, _st,iteratorType_,hasNextMethod_);
            Struct has_ = ProcessMethod.calculate(_st.getCallingState(), _ctx, _st).getValue();
            logTrace(_st, _ctx, _logger);
            if (_ctx.callsOrException(_st) || BooleanStruct.isFalse(has_)) {
                break;
            }
            String nextMethod_ = _ctx.getStandards().getPredefTypes().getAliasNextPair();
            getOrRedirect(map_, _ctx, _st,iteratorType_,nextMethod_);
            Struct pair_ = ProcessMethod.calculate(_st.getCallingState(), _ctx, _st).getValue();
            logTrace(_st, _ctx, _logger);
            String pairType_ = _ctx.getStandards().getPredefTypes().getAliasPairType();
            String first_ = _ctx.getStandards().getPredefTypes().getAliasGetFirst();
            getOrRedirect(pair_, _ctx, _st,pairType_,first_);
            Struct prefix_ = ProcessMethod.calculate(_st.getCallingState(), _ctx, _st).getValue();
            logTrace(_st, _ctx, _logger);
            String second_ = _ctx.getStandards().getPredefTypes().getAliasGetSecond();
            getOrRedirect(pair_, _ctx, _st,pairType_,second_);
            Struct value_ = ProcessMethod.calculate(_st.getCallingState(), _ctx, _st).getValue();
            logTrace(_st, _ctx, _logger);
            _node.append(_compo, NumParsers.getString(prefix_).getInstance(),value_);
        }
    }

    private static void getOrRedirect(Struct _argument, ContextEl _conf, StackCall _stackCall, String _cl, String _m) {
        if (_argument == NullStruct.NULL_VALUE) {
            String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
        }
        if (_stackCall.getCallingState() != null) {
            return;
        }
        ExecTypeFunction valBody_ = newCall(_cl,_m, _conf.getClasses());
        ExecTemplates.prepare(_conf,_stackCall,_argument,valBody_,new CustList<ArgumentWrapper>());
    }

    private static ExecTypeFunction newCall(String _cl, String _m,
                                            Classes _classes) {
        ExecFormattedRootBlock formattedType_ = ExecFormattedRootBlock.build(_cl,_classes);
        ExecRootBlock classBody_ = formattedType_.getRootBlock();
        ExecNamedFunctionBlock fct_ = ExecClassesUtil.getMethodBodiesById(classBody_, new MethodId(MethodAccessKind.INSTANCE,_m,new CustList<String>())).first();
        return new ExecTypeFunction(formattedType_, fct_);
    }
    private static void logTrace(StackCall _st, ContextEl _ctx, AdvLogDbg _logger) {
        CallingState stateAfter_ = _st.getCallingState();
        if (stateAfter_ != null) {
            for (String f: ResultContextLambda.traceView(_st, _ctx)) {
                _logger.log(f);
            }
        }
    }

    private static ResultContextLambda checkExc(RenderPointInfosPreference _bp) {
        StrResultContextLambda bpc_ = stopExcValue(_bp.getBreakPointCondition());
        return stopCurrent(bpc_);
    }

    private static ResultContextLambda checkExcExp(RenderPointInfosPreference _bp) {
        StrResultContextLambda bpc_ = stopExcValueExp(_bp.getBreakPointCondition());
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

    private static StrResultContextLambda stopExcValueExp(RenderPointPair _ex) {
        if (!_ex.isEnableExpand()) {
            return null;
        }
        return _ex.getExpand();
    }

    private static ContextEl local(ContextEl _orig, AbstractThreadFactory _compo, ResultContextLambda _lda) {
        ContextEl ctx_ = localCtx(_orig, _lda);
        return generate(_compo, ctx_);
    }

    private static ContextEl generate(AbstractThreadFactory _compo, ContextEl _ctx) {
        return _ctx.copy(_compo.newAtomicBoolean(), NullStruct.NULL_VALUE);
//        return new InterruptibleContextEl(_compo.newAtomicBoolean(), _ctx.getExecutionInfos());
//        return new GuiContextEl(_compo.newAtomicBoolean(), null, _ctx.getExecutionInfos(), new StringList());
    }

    private static ContextEl localCtx(ContextEl _orig, ResultContextLambda _lda) {
        if (_lda != null) {
            return _lda.getContext();
        }
        return _orig;
    }
    static String format(DbgNodeStruct _node) {
        return format(_node, wrapValue(_node));
    }

    private static String wrapValue(DbgNodeStruct _node) {
        return wrapValue(_node.repr());
    }

    static String format(DbgNodeStruct _node, String _value) {
        return "<" + MessagesIde.HTML + "><" + MessagesIde.SPAN + " " + MessagesIde.STYLE + "='" + MessagesIde.BACKGROUND_COLOR + ":#"+ MessagesIde.BLACK+";'>"
                + "<" + MessagesIde.SPAN + " " + MessagesIde.STYLE + "='" + MessagesIde.COLOR + ":#"+ MessagesIde.RED+";'>" + transform(_node.str()) + "</" + MessagesIde.SPAN + ">"
                + MessagesIde.EXT_SPACE
                + wrapTypeDecl(_node.value().getClassName(_node.getResult()))
                + MessagesIde.EXT_SPACE
                + _value
                + "</" + MessagesIde.SPAN + ">"
                + "</" + MessagesIde.HTML + ">";
    }
    private static String wrapTypeDecl(String _type) {
        return "<" + MessagesIde.SPAN + " " + MessagesIde.STYLE + "='" + MessagesIde.COLOR + ":#"+ MessagesIde.WHITE+";'>" +transform(_type) + "</" + MessagesIde.SPAN + ">";
    }

    private static String wrapValue(String _v) {
        if (!_v.isEmpty()) {
            return "<" + MessagesIde.SPAN + " " + MessagesIde.STYLE + "='" + MessagesIde.COLOR + ":#"+ MessagesIde.CYAN+";'>" + transform(_v) + "</" + MessagesIde.SPAN +">";
        }
        return AbsEditorTabList.EMPTY_STRING;
    }

    static String wrapValueInner(Struct _str, ContextEl _ctx) {
        if (_str instanceof ArrayStruct) {
            return Long.toString(((ArrayStruct)_str).getLength());
        }
        if (_str instanceof DisplayableStruct) {
            return ((DisplayableStruct)_str).getDisplayedString(_ctx).getInstance();
        }
        return AbsEditorTabList.EMPTY_STRING;
    }
    static String locations(FileBlockIndex _elt) {
        String content_ = resource(_elt.getFile());
        int lenContent_ = content_.length();
        int locIndex_ = _elt.getIndex();
        if (locIndex_ >= lenContent_) {
            return AbsEditorTabList.EMPTY_STRING;
        }
        int min_ = NumberUtil.max(0, content_.lastIndexOf(AbsEditorTabList.LINE_RETURN_CH, locIndex_));
        int endLine_ = content_.indexOf(AbsEditorTabList.LINE_RETURN_CH, locIndex_);
        if (endLine_ < 0) {
            endLine_ = lenContent_ - 1;
        }
        int max_ = NumberUtil.min(lenContent_, endLine_ +1);
        int until_ = ElResolver.incrAfterWord(locIndex_,content_);
        String before_ = transform(content_.substring(min_, locIndex_));
        String occurrence_ = transform(content_.substring(locIndex_,until_));
        String after_ = transform(content_.substring(until_,max_));
        return "<" + MessagesIde.HTML + "><" + MessagesIde.SPAN + " " + MessagesIde.STYLE + "='" + MessagesIde.BACKGROUND_COLOR + ":#"+ MessagesIde.BLACK+";'>"
            + "<" + MessagesIde.SPAN + " " + MessagesIde.STYLE + "='" + MessagesIde.COLOR + ":#"+ MessagesIde.RED+";'>" + locIndex_ + "</" + MessagesIde.SPAN + ">"
            + MessagesIde.EXT_SPACE
            + "<" + MessagesIde.SPAN + " " + MessagesIde.STYLE + "='" + MessagesIde.COLOR + ":#"+ MessagesIde.WHITE+";'>" +before_+ "</" + MessagesIde.SPAN + ">"
            + "<" + MessagesIde.SPAN + " " + MessagesIde.STYLE + "='" + MessagesIde.COLOR + ":#"+ MessagesIde.RED+";'>" +occurrence_+ "</" + MessagesIde.SPAN + ">"
            + "<" + MessagesIde.SPAN + " " + MessagesIde.STYLE + "='" + MessagesIde.COLOR + ":#"+ MessagesIde.WHITE+";'>" +after_+ "</" + MessagesIde.SPAN + ">"
            + "</" + MessagesIde.SPAN + ">"
            + "</" + MessagesIde.HTML + ">";
    }

    static String resource(FileBlock _res) {
        if (_res == null) {
            return AbsEditorTabList.EMPTY_STRING;
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
        return MessagesIde.BEGIN_ENCODE +(int)_ch+ MessagesIde.END_ENCODE;
    }
}
