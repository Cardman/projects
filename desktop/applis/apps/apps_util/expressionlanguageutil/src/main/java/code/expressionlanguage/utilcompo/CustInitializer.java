package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.guicompos.EventStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.stds.FctThreadPrint0;
import code.threads.AbstractAtomicLong;
import code.util.CustList;
import code.util.core.StringUtil;

public class CustInitializer extends DefaultInitializer {

	/**Used map in order that the user can easily log when a few thread is used (depends on Thread class implementation)*/
	private final ThreadSetStruct threadSet;
//    private final ThreadSetStruct hooks;
	private final AbstractAtomicLong countThreads;

    public CustInitializer(AbstractAtomicLong _value,AbstractInterceptor _concurrent) {
        countThreads = _value;
        threadSet = new ThreadSetStruct(_concurrent);
//        hooks = new ThreadSetStruct(_concurrent);
    }
    @Override
    protected Struct init(ContextEl _context, Struct _parent, ExecFormattedRootBlock _className, String _fieldName, int _ordinal, CustList<ClassFieldStruct> _fields) {
        return new EventStruct(_context,_className.getFormatted(),_fieldName,_ordinal,_fields,_parent, _parent.getClassName(_context));
    }

    String getCurrentTreadIdDate(RunnableContextEl _ctx) {
    	return _ctx.getIdDate();
	}

    public static void log(RunnableContextEl _cont, String _txt) {
        String text_ = StringUtil.concat(CustAliases.getDateTimeText(_cont.getCurrentThreadFactory()),":",_txt);
        FctThreadPrint0.log(((LgNamesWithNewAliases)_cont.getStandards()).getExecContent().getCustAliases().getInfos(), text_,_cont);
    }

    public void removeThreadFromList(RunnableContextEl _ctx) {
        threadSet.remove(_ctx.getThread());
        _ctx.getThread().end();
    }

    public String getCurrentFileThread(RunnableContextEl _cont) {
        String toFile_ = getCurrentTreadIdDate(_cont);
        if (toFile_ == null) {
            toFile_ = _cont.getExecutingOptions().getMainThread();
        }
        return toFile_;
    }

    /**This method must be called only before exit, by one (main) thread only*/
    public void joinOthers(RunnableContextEl _ctx, StackCall _stackCall) {
        for (Struct s: threadSet.toSnapshotArray(_ctx, _stackCall).list()) {
            if (s.sameReference(_ctx.getThread())) {
                continue;
            }
            ((AbsThreadStruct)s).joinThread(_ctx,_stackCall,"");
        }
    }
//    public void launchHooks(RunnableContextEl _ctx, StackCall _stackCall) {
//        CustList<Struct> inst_ = hooks.toSnapshotArray(_ctx, _stackCall).list();
//        for (Struct s: inst_) {
//            if (!(s instanceof ThreadStruct)) {
//                continue;
//            }
//            ((ThreadStruct)s).start();
//        }
//        for (Struct s: inst_) {
//            if (!(s instanceof ThreadStruct)) {
//                continue;
//            }
//            AbstractThread t_ = ((ThreadStruct)s).getThread();
//            t_.join();
//        }
//    }
//    public void joinHooks(RunnableContextEl _ctx) {
//        for (Struct s: hooks.toSnapshotArray(_ctx, StackCall.newInstance(InitPhase.NOTHING,_ctx)).list()) {
//            if (!(s instanceof ThreadStruct)) {
//                continue;
//            }
//            AbstractThread t_ = ((ThreadStruct)s).getThread();
//            t_.join();
//        }
//    }
    public void putNewCustTreadIdDate(RunnableContextEl _id, String _value) {
        _id.setIdDate(_value);
        threadSet.add(_id.getThread());
	}

    public ThreadSetStruct getThreadSet() {
        return threadSet;
    }

//    public void initHook(ThreadStruct _id) {
//        hooks.add(_id);
//    }

    public long increment() {
    	return countThreads.getAndIncrement();
    }
}
