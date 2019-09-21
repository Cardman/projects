package code.expressionlanguage;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.Struct;
import code.stream.StreamTextFile;
import code.util.ObjectMap;
import code.util.StringList;

public class CustInitializer extends DefaultInitializer {

	/**Used map in order that the user can easily log when a few thread is used (depends on Thread class implementation)*/
	private final ConcurrentHashMap<ThreadNumber, String> threadIdDate = new ConcurrentHashMap<ThreadNumber, String>();
    private final ConcurrentHashMap<Thread, Boolean> hooks = new ConcurrentHashMap<Thread, Boolean>();
	private final AtomicLong countThreads = new AtomicLong();
    @Override
    protected Struct init(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal, 
            ObjectMap<ClassField, Struct> _fields) {
        String base_ = Templates.getIdFromAllTypes(_className);
        String run_ = ((LgNamesUtils)_context.getStandards()).getAliasRunnable();
        if (PrimitiveTypeUtil.canBeUseAsArgument(run_, base_, _context)) {
            return new RunnableStruct(_context, _className, _fieldName, _ordinal, _fields, _parent);
        }
        return super.init(_context, _parent, _className, _fieldName, _ordinal, _fields);
    }
    @Override
    protected boolean simpleCall(ContextEl _owner) {
        try {
            return super.simpleCall(_owner);
        } catch (OutOfMemoryError _0) {
            _owner.setException(_owner.getMemoryError());
            _owner.getThrowing().removeBlockFinally(_owner);
            return !_owner.hasException();
        }
    }

    public String getInterfaceTask(LgNames _stds) {
        return ((LgNamesUtils)_stds).getAliasRunnable();
    }

    public String getRunTask(LgNames _stds) {
        return ((LgNamesUtils)_stds).getAliasRun();
    }
    String getCurrentTreadIdDate(RunnableContextEl _ctx) {
    	Thread thread_ = Thread.currentThread();
        ThreadNumber id_ = new ThreadNumber(thread_, _ctx.getNumber());
        Iterator<Map.Entry<ThreadNumber, String>> itr_ = threadIdDate.entrySet().iterator();
        while (itr_.hasNext()) {
            Map.Entry<ThreadNumber, String> n_ = itr_.next();
            if (n_.getKey().eq(id_)) {
                return n_.getValue();
            }
        }
		return null;
	}
    public void prExc(RunnableContextEl _cont) {
    	Struct exception_ = _cont.getException();
    	if (exception_ != null) {
            if (exception_ instanceof DisplayableStruct) {
                String text_ = ((DisplayableStruct)exception_).getDisplayedString(_cont).getInstance();
                log(_cont,text_);
            } else {
                _cont.setException(null);
                Argument out_ = new Argument(exception_);
                out_ = ExecOperationNode.processString(out_, _cont);
                CallingState state_ = _cont.getCallingState();
                boolean convert_ = false;
                if (state_ instanceof CustomFoundMethod) {
                    CustomFoundMethod method_ = (CustomFoundMethod) state_;
                    out_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(), method_.getId(), method_.getArguments(), _cont, method_.getRight());
                    convert_ = true;
                }
                if (!_cont.hasException()) {
                    if (convert_) {
                        Argument outConv_ = new Argument();
                        outConv_.setStruct(((DisplayableStruct)out_.getStruct()).getDisplayedString(_cont));
                        out_ = outConv_;
                    }
                    String text_ = out_.getString();
                    log(_cont,text_);
                } else {
                    log(_cont,_cont.getStandards().getNullString());
                }
            }
        }
        removeThreadFromList(_cont);
    }

    private void log(RunnableContextEl _cont,String _txt) {
        String toFile_ = getCurrentFileThread(_cont);
        String text_ = StringList.concat(LgNamesUtils.getDateTimeText("_", "_", "_"),":",_txt);
        ExecutingOptions ex_ = _cont.getExecutingOptions();
        String folder_ = ex_.getLogFolder();
        new File(folder_).mkdirs();
        toFile_ = StringList.concat(folder_,"/",toFile_);
        StreamTextFile.logToFile(toFile_, text_);
    }

    public void removeThreadFromList(RunnableContextEl _ctx) {
        Thread thread_ = Thread.currentThread();
        Iterator<Map.Entry<ThreadNumber, String>> itr_ = threadIdDate.entrySet().iterator();
        ThreadNumber id_ = new ThreadNumber(thread_, _ctx.getNumber());
        while (itr_.hasNext()) {
            Map.Entry<ThreadNumber, String> n_ = itr_.next();
            if (n_.getKey().eq(id_)) {
                itr_.remove();
            }
        }
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
        ThreadNumber id_ = new ThreadNumber(Thread.currentThread(), _ctx.getNumber());
        for (ThreadNumber t: threadIdDate.keySet()) {
            if (t.eq(id_)) {
                continue;
            }
            try {
                t.getThread().join();
            } catch (Exception e) {
                //skip exception
            }
        }
    }
    void joinHooks() {
        for (Thread t: hooks.keySet()) {
            try {
                t.join();
            } catch (Exception e) {
                //skip exception
            }
        }
    }
    void putNewCustTreadIdDate(Thread _id, long _nb, String _value) {
		threadIdDate.put(new ThreadNumber(_id,_nb),_value);
	}

    public void initHook(Thread _id) {
        hooks.put(_id, true);
    }

    long increment() {
    	return countThreads.getAndIncrement();
    }
}
