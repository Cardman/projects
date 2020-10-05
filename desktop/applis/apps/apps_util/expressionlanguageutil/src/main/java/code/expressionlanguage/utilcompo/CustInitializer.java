package code.expressionlanguage.utilcompo;

import java.util.concurrent.atomic.AtomicLong;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;

import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.Struct;
import code.threads.ThreadUtil;
import code.util.CustList;
import code.util.StringList;

public class CustInitializer extends DefaultInitializer {

	/**Used map in order that the user can easily log when a few thread is used (depends on Thread class implementation)*/
	private final ThreadSetStruct threadSet = new ThreadSetStruct();
    private final ThreadSetStruct hooks = new ThreadSetStruct();
	private final AtomicLong countThreads = new AtomicLong();
    @Override
    protected Struct init(ContextEl _context, Struct _parent,
                          String _className, String _fieldName, int _ordinal,
                          CustList<ClassFieldStruct> _fields) {
        return new RunnableStruct(_context, _className, _fieldName, _ordinal, _fields, _parent, _parent.getClassName(_context));
    }

    @Override
    protected boolean exitAfterCall(ContextEl _owner) {
        if (((RunnableContextEl)_owner).stopped()) {
            return true;
        }
        return super.exitAfterCall(_owner);
    }

    String getCurrentTreadIdDate(RunnableContextEl _ctx) {
    	return _ctx.getIdDate();
	}
    public void prExc(RunnableContextEl _cont) {
        CallingState exc_ = _cont.getCallingState();
    	if (exc_ instanceof Struct) {
            Struct exception_ = (Struct) exc_;
            if (exception_ instanceof DisplayableStruct) {
                String text_ = ((DisplayableStruct)exception_).getDisplayedString(_cont).getInstance();
                log(_cont,text_);
            } else {
                _cont.setCallingState(null);
                Argument out_ = new Argument(exception_);
                out_ = ExecOperationNode.processString(out_, _cont);
                CallingState state_ = _cont.getCallingState();
                boolean convert_ = false;
                if (state_ instanceof CustomFoundMethod) {
                    CustomFoundMethod method_ = (CustomFoundMethod) state_;
                    out_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(),method_.getRootBlock(), method_.getId(), method_.getArguments(), _cont);
                    convert_ = true;
                }
                if (!_cont.callsOrException()) {
                    if (convert_) {
                        out_ = new Argument(ExecCatOperation.getDisplayable(out_,_cont));
                    }
                    String text_ = NumParsers.getString(out_.getStruct()).getInstance();
                    log(_cont,text_);
                } else {
                    log(_cont,_cont.getStandards().getDisplayedStrings().getNullString());
                }
            }
        }
        removeThreadFromList(_cont);
    }

    private void log(RunnableContextEl _cont,String _txt) {
        String toFile_ = getCurrentFileThread(_cont);
        String text_ = StringList.concat(CustAliases.getDateTimeText("_", "_", "_"),":",_txt);
        ExecutingOptions ex_ = _cont.getExecutingOptions();
        String folder_ = ex_.getLogFolder();
        ((LgNamesWithNewAliases)_cont.getStandards()).getCustAliases().log(folder_, toFile_,text_,_cont);
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
    void joinOthers(RunnableContextEl _ctx) {
        for (Struct s: threadSet.toSnapshotArray(_ctx).getInstance()) {
            if (!(s instanceof ThreadStruct)) {
                continue;
            }
            if (s.sameReference(_ctx.getThread())) {
                continue;
            }
            Thread t_ = ((ThreadStruct)s).getThread();
            ThreadUtil.join(t_);
        }
    }
    public void launchHooks(RunnableContextEl _ctx) {
        Struct[] inst_ = hooks.toSnapshotArray(_ctx).getInstance();
        for (Struct s: inst_) {
            if (!(s instanceof ThreadStruct)) {
                continue;
            }
            Thread t_ = ((ThreadStruct)s).getThread();
            t_.start();
        }
        for (Struct s: inst_) {
            if (!(s instanceof ThreadStruct)) {
                continue;
            }
            Thread t_ = ((ThreadStruct)s).getThread();
            ThreadUtil.join(t_);
        }
    }
    public void joinHooks(RunnableContextEl _ctx) {
        for (Struct s: hooks.toSnapshotArray(_ctx).getInstance()) {
            if (!(s instanceof ThreadStruct)) {
                continue;
            }
            Thread t_ = ((ThreadStruct)s).getThread();
            ThreadUtil.join(t_);
        }
    }
    void putNewCustTreadIdDate(RunnableContextEl _id, String _value) {
        _id.setIdDate(_value);
        threadSet.add(_id.getThread());
	}

    public ThreadSetStruct getThreadSet() {
        return threadSet;
    }

    public void initHook(ThreadStruct _id) {
        hooks.add(_id);
    }

    long increment() {
    	return countThreads.getAndIncrement();
    }
}
