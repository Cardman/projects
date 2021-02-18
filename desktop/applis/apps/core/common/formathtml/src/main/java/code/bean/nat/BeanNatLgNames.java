package code.bean.nat;

import code.expressionlanguage.analyze.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendSettableFieldOperation;
import code.formathtml.fwd.DefaultInputBuilder;
import code.formathtml.fwd.RendForwardInfos;
import code.expressionlanguage.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.*;
import code.formathtml.util.DualAnalyzedContext;
import code.util.*;
import code.util.core.StringUtil;
import code.util.ints.*;

public abstract class BeanNatLgNames extends BeanNatCommonLgNames {

    @Override
    public Argument getCommonSetting(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf, Argument _right, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        ClassField fieldId_ = _rend.getClassField();
        setOtherResult(_context, fieldId_, _previous.getStruct(), _right.getStruct());
        return _right;
    }

    @Override
    public Argument iteratorMultTable(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        return new Argument(new SimpleItrStruct(StringUtil.concat(TYPE_ITERATOR,Templates.TEMPLATE_BEGIN, TYPE_ENTRY,Templates.TEMPLATE_BEGIN, "?,?",Templates.TEMPLATE_END,Templates.TEMPLATE_END),array_));
    }

    @Override
    public Argument hasNextPair(Struct _arg, Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        return new Argument(BooleanStruct.of(simpleItrStruct_.hasNext()));
    }

    @Override
    public Argument nextPair(Struct _arg, Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        Struct resObj_ = simpleItrStruct_.next();
        return new Argument(resObj_);
    }

    @Override
    public Argument first(Struct _arg, Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        PairStruct pairStruct_ = getPairStruct(_arg, _ctx);
        Struct resObj_ = pairStruct_.getFirst();
        return new Argument(resObj_);
    }

    @Override
    public Argument second(Struct _arg, Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        PairStruct pairStruct_ = getPairStruct(_arg, _ctx);
        Struct resObj_ = pairStruct_.getSecond();
        return new Argument(resObj_);
    }

    @Override
    public Argument iteratorList(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        return new Argument(new SimpleItrStruct(StringUtil.concat(TYPE_ITERATOR,Templates.TEMPLATE_BEGIN,"?",Templates.TEMPLATE_END),array_));
    }

    @Override
    public Argument nextList(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        Struct resObj_ = simpleItrStruct_.next();
        return new Argument(resObj_);
    }

    @Override
    public Argument hasNext(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        return new Argument(BooleanStruct.of(simpleItrStruct_.hasNext()));
    }

    public ReportedMessages setupAll(Navigation _nav, Configuration _conf, StringMap<String> _files, DualAnalyzedContext _dual) {
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setReducingOperations(new NativeReducingOperations());
        analyzingDoc_.setContent(this);
        analyzingDoc_.setInputBuilder(new DefaultInputBuilder());
        analyzingDoc_.setConverterCheck(new NativeConverterCheck());
        AnalyzedPageEl page_ = _dual.getAnalyzed();
        page_.setForEachFetch(new NativeForEachFetch(this));
        initInstancesPattern(_nav.getSession(),analyzingDoc_);
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(page_, this, analyzingDoc_, _dual.getContext());
        RendForwardInfos.buildExec(analyzingDoc_, d_, new Forwards(), _conf);
        return page_.getMessages();
    }

    @Override
    public String processString(Argument _arg, ContextEl _ctx, StackCall _stack) {
        Struct struct_ = _arg.getStruct();
        if (struct_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)struct_).getDisplayedString(_ctx).getInstance();
        }
        return struct_.getClassName(_ctx);
    }

    @Override
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        StringList list_ = _method.getParametersTypes();
        return getOtherResultBean(_cont, _method, _args);
    }

    public abstract ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Struct... _args);

    @Override
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        StringList list_ = _method.getConstraints().getParametersTypes();
        if (_instance instanceof ArrayStruct) {
            res_.setResult(BooleanStruct.of(ExecArrayFieldOperation.getArray(_instance,_cont).getLength()==0));
            return res_;
        }
        return getOtherResultBean(_cont, _instance, _method, _args);
    }
    public abstract ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
                                             ClassMethodId _method, Struct... _args);

    ContextEl setupNative(AnalyzedPageEl _page) {
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        Options options_ = new Options();
        int tabWidth_ = 4;
        ContextEl contextEl_ = ContextFactory.simpleBuild(-1, options_, this, tabWidth_);
        ContextFactory.validateStds(a_, kw_, this, new CustList<CommentDelimiters>(), options_, contextEl_.getClasses().getCommon(), new DefaultConstantsCalculator(getNbAlias()), DefaultFileBuilder.newInstance(getContent()), getContent(),tabWidth_, _page, new NativeFieldFilter());
        return contextEl_;
    }

    public abstract ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val);

}
