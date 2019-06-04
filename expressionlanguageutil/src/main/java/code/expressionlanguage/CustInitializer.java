package code.expressionlanguage;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.Struct;
import code.stream.StreamTextFile;
import code.util.ObjectMap;
import code.util.StringList;

public class CustInitializer extends DefaultInitializer {

	/**Used map in order that the user can easily log when a few thread is used (depends on Thread class implementation)*/
	private final ConcurrentHashMap<Thread, String> threadIdDate = new ConcurrentHashMap<Thread, String>();
	private final ConcurrentHashMap<Thread, Boolean> alive = new ConcurrentHashMap<Thread, Boolean>();
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
    String getCurrentTreadIdDate() {
    	Thread thread_ = Thread.currentThread();
		return threadIdDate.get(thread_);
	}
    public void prExc(RunnableContextEl _cont) {
    	Struct exception_ = _cont.getException();
        if (exception_ instanceof DisplayableStruct) {
        	String toFile_ = getCurrentFileThread(_cont);
        	String text_ = ((DisplayableStruct)exception_).getDisplayedString(_cont).getInstance();
        	text_ = StringList.concat(LgNamesUtils.getDateTimeText("_", "_", "_"),":",text_);
        	ExecutingOptions ex_ = _cont.getExecutingOptions();
        	String folder_ = ex_.getLogFolder();
        	new File(folder_).mkdirs();
        	toFile_ = StringList.concat(folder_,"/",toFile_);
        	StreamTextFile.logToFile(toFile_, text_);
        }
        Thread thread_ = Thread.currentThread();
        threadIdDate.remove(thread_);
        alive.remove(thread_);
    }
    public String getCurrentFileThread(RunnableContextEl _cont) {
        String toFile_ = getCurrentTreadIdDate();
        if (toFile_ == null) {
            toFile_ = _cont.getExecutingOptions().getMainThread();
        }
        return toFile_;
    }

    void putNewCustTreadIdDate(Thread _id, String _value) {
		threadIdDate.put(_id,_value);
	}

	boolean isAlive(Thread _id) {
        return alive.containsKey(_id);
    }

    public void initAlive(Thread _id) {
        alive.put(_id, true);
    }

    long increment() {
    	return countThreads.getAndIncrement();
    }
}
