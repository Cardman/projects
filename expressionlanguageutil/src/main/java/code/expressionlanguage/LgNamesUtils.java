package code.expressionlanguage;

import java.io.File;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.resources.ResourceFiles;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public class LgNamesUtils extends LgNames {

    private String aliasRunnable;
    private String aliasThread;
    private String aliasStart;
    private String aliasJoin;
    private String aliasSleep;
    private String aliasRun;
    private String aliasIsAlive;
    private String aliasIsInterrupted;
    private String aliasInterrupt;
    private String aliasGetId;
    private String aliasGetPriority;
    private String aliasSetPriority;
    private String aliasYield;
    private String aliasReentrantLock;
    private String aliasLock;
    private String aliasUnlock;
    private String aliasIsHeldByCurrentThread;
    private String aliasAtomicBoolean;
    private String aliasAtomicInteger;
    private String aliasAtomicLong;
    private String aliasSetAtomic;
    private String aliasGetAtomic;
    private String aliasPrint;
    private String aliasFile;
    private String aliasRead;
    private String aliasWrite;
    private String aliasAppendToFile;

    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> stds_ = super.buildFiles(_context);
        String content_ = ResourceFiles.ressourceFichier("resources_lg/threads/runnable.txt");
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String interface_ = keyWords_.getKeyWordInterface();
        String abstract_ = keyWords_.getKeyWordAbstract();
        String endLine_ = String.valueOf(_context.getOptions().getEndLine());
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{Runnable}", aliasRunnable);
        map_.put("{abstract}", abstract_);
        map_.put("{void}", getAliasVoid());
        map_.put("{run}", aliasRun);
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasRunnable);
        stds_.put(aliasRunnable, content_);
        getPredefinedInterfacesInitOrder().add(aliasRunnable);
        return stds_;
    }
    @Override
    public void buildOther() {
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasThread, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasStart, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasJoin, params_, getAliasBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasIsAlive, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasIsInterrupted, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasInterrupt, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasGetId, params_, getAliasPrimLong(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasGetPriority, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetPriority, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasSleep, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasYield, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasPrint, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasObject());
        method_ = new StandardMethod(aliasPrint, params_, getAliasVoid(), true, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        StandardConstructor ctor_;
        params_ = new StringList(aliasRunnable);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasThread, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasReentrantLock, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasLock, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasUnlock, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasIsHeldByCurrentThread, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasReentrantLock, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicBoolean, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasAtomicBoolean, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicInteger, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasAtomicInteger, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicLong, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasSetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimLong());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasAtomicLong, std_);
        
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasFile, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasRead, params_, getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasWrite, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasAppendToFile, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasFile, std_);
    }
    @Override
    public Argument defaultInstance(ExecutableCode _cont, String _id) {
        if (StringList.quickEq(_id,getAliasObject())) {
            return super.defaultInstance(_cont,_id);
        }
        if (StringList.quickEq(_id,aliasThread)) {
            _cont.setException(new ErrorStruct(_cont,getAliasIllegalArg()));
            return Argument.createVoid();
        }
        if (StringList.quickEq(_id,aliasReentrantLock)) {
            ReentrantLock re_ = new ReentrantLock();
            StdStruct std_ = StdStruct.newInstance(re_, aliasReentrantLock);
            return new Argument(std_);
        }
        if (StringList.quickEq(_id,aliasAtomicBoolean)) {
            AtomicBoolean at_ = new AtomicBoolean();
            StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicBoolean);
            return new Argument(std_);
        }
        if (StringList.quickEq(_id,aliasAtomicInteger)) {
            AtomicInteger at_ = new AtomicInteger();
            StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicInteger);
            return new Argument(std_);
        }
        if (StringList.quickEq(_id,aliasAtomicLong)) {
            AtomicLong at_ = new AtomicLong();
            StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicLong);
            return new Argument(std_);
        }
        return Argument.createVoid();
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String name_ = _method.getName();
        if (StringList.quickEq(name_,getAliasObject())) {
            return super.getOtherResult(_cont,_method,_args);
        }
        if (StringList.quickEq(name_,aliasThread)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            Struct runnable_ = _args[0];
            Thread thread_;
            if (runnable_ instanceof Runnable) {
                thread_ = new Thread((Runnable) runnable_);
            } else {
                thread_ = new Thread((Runnable) null);
            }
            StdStruct std_ = StdStruct.newInstance(thread_, aliasThread);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasReentrantLock)) {
            ReentrantLock re_ = new ReentrantLock();
            StdStruct std_ = StdStruct.newInstance(re_, aliasReentrantLock);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasAtomicBoolean)) {
            if (_method.getParametersTypes().isEmpty()) {
                AtomicBoolean at_ = new AtomicBoolean();
                StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicBoolean);
                res_.setResult(std_);
                return res_;
            }
            AtomicBoolean at_ = new AtomicBoolean(((BooleanStruct)_args[0]).getInstance());
            StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicBoolean);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasAtomicInteger)) {
            if (_method.getParametersTypes().isEmpty()) {
                AtomicInteger at_ = new AtomicInteger();
                StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicInteger);
                res_.setResult(std_);
                return res_;
            }
            AtomicInteger at_ = new AtomicInteger(((NumberStruct)_args[0]).getInstance().intValue());
            StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicInteger);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasAtomicLong)) {
            if (_method.getParametersTypes().isEmpty()) {
                AtomicLong at_ = new AtomicLong();
                StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicLong);
                res_.setResult(std_);
                return res_;
            }
            AtomicLong at_ = new AtomicLong(((NumberStruct)_args[0]).getInstance().longValue());
            StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicLong);
            res_.setResult(std_);
            return res_;
        }
        return res_;
    }

    static String getDateTimeText(String _separatorDate, String _sep, String _separatorTime) {
        Calendar now_ = Calendar.getInstance();
        int y_ = now_.get(Calendar.YEAR);
        int m_ = now_.get(Calendar.MONTH) + 1;
        int d_ = now_.get(Calendar.DAY_OF_MONTH);
        int h_ = now_.get(Calendar.HOUR_OF_DAY);
        int mi_ = now_.get(Calendar.MINUTE);
        int s_ = now_.get(Calendar.SECOND);
        int ms_ = now_.get(Calendar.MILLISECOND);
        String strMonth_ = lpadZero(m_);
        String strDay_ = lpadZero(d_);
        String strHour_ = lpadZero(h_);
        String strMinute_ = lpadZero(mi_);
        String strSecond_ = lpadZero(s_);
        String strMs_ = lpadZeroMillis(ms_);
        return StringList.concat(String.valueOf(y_),_separatorDate,strMonth_,
                _separatorDate,strDay_,_sep,strHour_,
                _separatorTime,strMinute_,_separatorTime,strSecond_,_separatorTime,strMs_);
    }
    private static String lpadZero(int _nb) {
    	if (_nb < 10) {
        	return StringList.concat("0",Integer.toString(_nb));
        }
		return Integer.toString(_nb);
    }
    private static String lpadZeroMillis(int _millis) {
    	if (_millis < 10) {
        	return StringList.concat("00",Integer.toString(_millis));
        }
    	if (_millis < 100) {
        	return StringList.concat("0",Integer.toString(_millis));
        }
		return Integer.toString(_millis);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String className_ = _method.getClassName();
        String type_ = _method.getClassName();
        if (StringList.quickEq(type_, getAliasEnums())) {
            return super.getOtherResult(_cont,_instance,_method,_args);
        }
        if (StringList.quickEq(className_,aliasThread)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasPrint)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
            	CustInitializer cust_ = (CustInitializer) _cont.getInit();
                String dtPart_ = cust_.getCurrentFileThread((RunnableContextEl)_cont);
                log(dtPart_, (RunnableContextEl)_cont, _method, _args);
                ResultErrorStd out_ = new ResultErrorStd();
                out_.setResult(NullStruct.NULL_VALUE);
                return out_;
            }
            if (StringList.quickEq(name_,aliasStart)) {
                Thread thread_ = (Thread)((StdStruct) _instance).getInstance();
                thread_.start();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasSleep)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                try {
                    Thread.sleep(((NumberStruct)_args[0]).getInstance().longValue());
                    res_.setResult(new BooleanStruct(true));
                } catch (InterruptedException _0) {
                    res_.setResult(new BooleanStruct(false));
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasJoin)) {
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                try {
                    boolean alive_ = thread_.isAlive();
                    thread_.join();
                    res_.setResult(new BooleanStruct(alive_));
                } catch (InterruptedException _0) {
                    res_.setResult(NullStruct.NULL_VALUE);
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsAlive)) {
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                boolean alive_ = thread_.isAlive();
                res_.setResult(new BooleanStruct(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsInterrupted)) {
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                boolean alive_ = thread_.isInterrupted();
                res_.setResult(new BooleanStruct(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasInterrupt)) {
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                thread_.interrupt();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetId)) {
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                res_.setResult(new LongStruct(thread_.getId()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetPriority)) {
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(thread_.getPriority()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetPriority)) {
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                thread_.setPriority(((NumberStruct)_args[0]).getInstance().intValue());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasYield)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                Thread.yield();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasReentrantLock)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasLock)) {
                ReentrantLock re_ = (ReentrantLock) ((StdStruct) _instance).getInstance();
                re_.lock();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasUnlock)) {
                ReentrantLock re_ = (ReentrantLock) ((StdStruct) _instance).getInstance();
                re_.unlock();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsHeldByCurrentThread)) {
                ReentrantLock re_ = (ReentrantLock) ((StdStruct) _instance).getInstance();
                boolean held_ = re_.isHeldByCurrentThread();
                res_.setResult(new BooleanStruct(held_));
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasAtomicBoolean)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
                boolean held_ = re_.get();
                res_.setResult(new BooleanStruct(held_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
                re_.set(((BooleanStruct)_args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasAtomicInteger)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                int held_ = re_.get();
                res_.setResult(new IntStruct(held_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                re_.set(((NumberStruct)_args[0]).getInstance().intValue());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasAtomicLong)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                long held_ = re_.get();
                res_.setResult(new LongStruct(held_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                re_.set(((NumberStruct)_args[0]).getInstance().longValue());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasFile)) {
        	String name_ = _method.getConstraints().getName();
        	if (_cont.isInitEnums()) {
        		_cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
        	}
        	if (StringList.quickEq(name_,aliasRead)) {
        		StringStruct str_ = (StringStruct)_args[0];
        		String read_ = StreamTextFile.contentsOfFile(str_.getInstance());
        		if (read_ == null) {
        			res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
        		}
        		res_.setResult(new StringStruct(read_));
        		return res_;
        	}
        	if (StringList.quickEq(name_,aliasWrite)) {
        		String file_ = ((StringStruct)_args[0]).getInstance();
        		String txt_ = ((StringStruct)_args[1]).getInstance();
        		res_.setResult(new BooleanStruct(StreamTextFile.saveTextFile(file_, txt_)));
        		return res_;
        	}
        	if (StringList.quickEq(name_,aliasAppendToFile)) {
        		String file_ = ((StringStruct)_args[0]).getInstance();
        		String txt_ = ((StringStruct)_args[1]).getInstance();
        		res_.setResult(new BooleanStruct(StreamTextFile.logToFile(file_, txt_)));
        		return res_;
        	}
        }
        return res_;
    }

    private static void log(String _dtPart, RunnableContextEl _cont,
                            ClassMethodId _method, Struct... _args) {
        String stringAppFile_ = buildLog(_cont, _method, _args);
        stringAppFile_ = StringList.concat(getDateTimeText("_", "_", "_"),":",stringAppFile_);
        String folder_ = _cont.getExecuting().getLogFolder();
        new File(folder_).mkdirs();
        String toFile_ = StringList.concat(folder_,"/",_dtPart);
        StreamTextFile.logToFile(toFile_, stringAppFile_);
    }
    private static String buildLog(ContextEl _cont, ClassMethodId _method,
            Struct... _args) {
        String stringAppFile_;
        StringList paramType_ = _method.getConstraints().getParametersTypes();
        if (paramType_.size() == 1) {
            if (_args[0] instanceof DisplayableStruct) {
                stringAppFile_ = (((DisplayableStruct)_args[0]).getDisplayedString(_cont).getInstance());
            } else {
                stringAppFile_ = StringList.concat(_args[0].getClassName(_cont),"...");
            }
        } else {
            StringList values_ = new StringList();
            if (_args[1] instanceof ArrayStruct) {
                for (Struct e: ((ArrayStruct)_args[1]).getInstance()) {
                    if (e instanceof DisplayableStruct) {
                        values_.add(((DisplayableStruct)e).getDisplayedString(_cont).getInstance());
                    } else {
                        values_.add(StringList.concat("<",_args[0].getClassName(_cont),"...>"));
                    }
                }
                if (_args[0] instanceof StringStruct) {
                    stringAppFile_ = (StringList.simpleStringsFormat(((StringStruct)_args[0]).getInstance(), values_.toArray()));
                } else {
                    stringAppFile_ = _cont.getStandards().getNullString();
                }
            } else {
                if (_args[0] instanceof DisplayableStruct) {
                    stringAppFile_ = (((DisplayableStruct)_args[0]).getDisplayedString(_cont).getInstance());
                } else {
                    stringAppFile_ = StringList.concat(_args[0].getClassName(_cont),"...");
                }

            }
        }
        return stringAppFile_;
    }
    public StringMap<StringList> allTableTypeMethodNames() {
        StringMap<StringList> m_ = super.allTableTypeMethodNames();
        m_.put(getAliasThread(), new StringList(
                getAliasStart(),
                getAliasIsAlive(),
                getAliasJoin(),
                getAliasIsInterrupted(),
                getAliasInterrupt(),
                getAliasGetId(),
                getAliasGetPriority(),
                getAliasSetPriority(),
                getAliasYield(),
                getAliasSleep(),
                getAliasPrint()));
        m_.put(getAliasReentrantLock(), new StringList(
                getAliasLock(),
                getAliasUnlock(),
                getAliasIsHeldByCurrentThread()));
        m_.put(getAliasRunnable(), new StringList(
                getAliasRun()));
        m_.put(getAliasAtomicBoolean(), new StringList(
                getAliasGetAtomic(),
                getAliasSetAtomic()));
        m_.put(getAliasAtomicInteger(), new StringList(
                getAliasGetAtomic(),
                getAliasSetAtomic()));
        m_.put(getAliasAtomicLong(), new StringList(
                getAliasGetAtomic(),
                getAliasSetAtomic()));
        m_.put(getAliasFile(), new StringList(
                getAliasRead(),
                getAliasWrite(),
                getAliasAppendToFile()));
        return m_;
    }

    @Override
    public StringList allRefTypes() {
        StringList ref_ =  super.allRefTypes();
        ref_.add(getAliasThread());
        ref_.add(getAliasReentrantLock());
        ref_.add(getAliasAtomicBoolean());
        ref_.add(getAliasAtomicInteger());
        ref_.add(getAliasAtomicLong());
        ref_.add(getAliasFile());
        return ref_;
    }

    public String getAliasRunnable() {
        return aliasRunnable;
    }

    public void setAliasRunnable(String _aliasRunnable) {
        aliasRunnable = _aliasRunnable;
    }

    public String getAliasThread() {
        return aliasThread;
    }

    public void setAliasThread(String _aliasThread) {
        aliasThread = _aliasThread;
    }
    public String getAliasStart() {
        return aliasStart;
    }
    public void setAliasStart(String _aliasStart) {
        aliasStart = _aliasStart;
    }
    public String getAliasRun() {
        return aliasRun;
    }
    public void setAliasRun(String _aliasRun) {
        aliasRun = _aliasRun;
    }
    public String getAliasJoin() {
        return aliasJoin;
    }
    public void setAliasJoin(String _aliasJoin) {
        aliasJoin = _aliasJoin;
    }
    public String getAliasSleep() {
        return aliasSleep;
    }
    public void setAliasSleep(String _aliasSleep) {
        aliasSleep = _aliasSleep;
    }
    public String getAliasIsAlive() {
        return aliasIsAlive;
    }
    public void setAliasIsAlive(String _aliasIsAlive) {
        aliasIsAlive = _aliasIsAlive;
    }
    public String getAliasIsInterrupted() {
        return aliasIsInterrupted;
    }
    public void setAliasIsInterrupted(String _aliasIsInterrupted) {
        aliasIsInterrupted = _aliasIsInterrupted;
    }
    public String getAliasInterrupt() {
        return aliasInterrupt;
    }
    public void setAliasInterrupt(String _aliasInterrupt) {
        aliasInterrupt = _aliasInterrupt;
    }
    public String getAliasGetId() {
        return aliasGetId;
    }
    public void setAliasGetId(String _aliasGetId) {
        aliasGetId = _aliasGetId;
    }
    public String getAliasGetPriority() {
        return aliasGetPriority;
    }
    public void setAliasGetPriority(String _aliasGetPriority) {
        aliasGetPriority = _aliasGetPriority;
    }
    public String getAliasSetPriority() {
        return aliasSetPriority;
    }
    public void setAliasSetPriority(String _aliasSetPriority) {
        aliasSetPriority = _aliasSetPriority;
    }
    public String getAliasYield() {
        return aliasYield;
    }
    public void setAliasYield(String _aliasYield) {
        aliasYield = _aliasYield;
    }
    public String getAliasReentrantLock() {
        return aliasReentrantLock;
    }
    public void setAliasReentrantLock(String _aliasReentrantLock) {
        aliasReentrantLock = _aliasReentrantLock;
    }
    public String getAliasLock() {
        return aliasLock;
    }
    public void setAliasLock(String _aliasLock) {
        aliasLock = _aliasLock;
    }
    public String getAliasUnlock() {
        return aliasUnlock;
    }
    public void setAliasUnlock(String _aliasUnlock) {
        aliasUnlock = _aliasUnlock;
    }
    public String getAliasIsHeldByCurrentThread() {
        return aliasIsHeldByCurrentThread;
    }
    public void setAliasIsHeldByCurrentThread(String _aliasIsHeldByCurrentThread) {
        aliasIsHeldByCurrentThread = _aliasIsHeldByCurrentThread;
    }
    public String getAliasAtomicBoolean() {
        return aliasAtomicBoolean;
    }
    public void setAliasAtomicBoolean(String _aliasAtomicBoolean) {
        aliasAtomicBoolean = _aliasAtomicBoolean;
    }
    public String getAliasAtomicInteger() {
        return aliasAtomicInteger;
    }
    public void setAliasAtomicInteger(String _aliasAtomicInteger) {
        aliasAtomicInteger = _aliasAtomicInteger;
    }
    public String getAliasAtomicLong() {
        return aliasAtomicLong;
    }
    public void setAliasAtomicLong(String _aliasAtomicLong) {
        aliasAtomicLong = _aliasAtomicLong;
    }
    public String getAliasSetAtomic() {
        return aliasSetAtomic;
    }
    public void setAliasSetAtomic(String _aliasSet) {
        aliasSetAtomic = _aliasSet;
    }

    public String getAliasGetAtomic() {
        return aliasGetAtomic;
    }

    public void setAliasGetAtomic(String _aliasGetAtomic) {
        aliasGetAtomic = _aliasGetAtomic;
    }
	public String getAliasPrint() {
		return aliasPrint;
	}
	public void setAliasPrint(String _aliasPrint) {
		aliasPrint = _aliasPrint;
	}
	public String getAliasFile() {
		return aliasFile;
	}
	public void setAliasFile(String _aliasFile) {
		aliasFile = _aliasFile;
	}
	public String getAliasRead() {
		return aliasRead;
	}
	public void setAliasRead(String _aliasRead) {
		aliasRead = _aliasRead;
	}
	public String getAliasWrite() {
		return aliasWrite;
	}
	public void setAliasWrite(String _aliasWrite) {
		aliasWrite = _aliasWrite;
	}
	public String getAliasAppendToFile() {
		return aliasAppendToFile;
	}
	public void setAliasAppendToFile(String _aliasAppendToFile) {
		aliasAppendToFile = _aliasAppendToFile;
	}

    public void otherAlias(String _lang) {
        if (StringList.quickEq(_lang, "en")) {
            setAliasPrint("print");
            setAliasRunnable("$core.Runnable");
            setAliasRun("run");
            setAliasThread("$core.Thread");
            setAliasStart("start");
            setAliasJoin("join");
            setAliasSleep("sleep");
            setAliasIsAlive("isAlive");
            setAliasIsInterrupted("isInterrupted");
            setAliasInterrupt("interrupt");
            setAliasGetId("getId");
            setAliasGetPriority("getPriority");
            setAliasSetPriority("setPriority");
            setAliasYield("yield");
            setAliasReentrantLock("$core.ReentrantLock");
            setAliasLock("lock");
            setAliasUnlock("unlock");
            setAliasIsHeldByCurrentThread("isHeldByCurrentThread");
            setAliasAtomicBoolean("$core.AtomicBoolean");
            setAliasAtomicInteger("$core.AtomicInteger");
            setAliasAtomicLong("$core.AtomicLong");
            setAliasSetAtomic("setValue");
            setAliasGetAtomic("getValue");
            setAliasFile("$core.File");
            setAliasRead("read");
            setAliasWrite("write");
            setAliasAppendToFile("appendToFile");
        } else {
            setAliasPrint("afficher");
            setAliasRunnable("$coeur.Executable");
            setAliasRun("executer");
            setAliasThread("$coeur.Tache");
            setAliasStart("demarrer");
            setAliasJoin("attendre");
            setAliasSleep("dormir");
            setAliasIsAlive("estActif");
            setAliasIsInterrupted("estInterrompu");
            setAliasInterrupt("interrompre");
            setAliasGetId("valId");
            setAliasGetPriority("valPriorite");
            setAliasSetPriority("majPriorite");
            setAliasYield("declencher");
            setAliasReentrantLock("$coeur.Verrou");
            setAliasLock("verrouiller");
            setAliasUnlock("deverrouiller");
            setAliasIsHeldByCurrentThread("estActiveeParTacheCourante");
            setAliasAtomicBoolean("$coeur.AtomicBooleen");
            setAliasAtomicInteger("$coeur.AtomicEntier4");
            setAliasAtomicLong("$coeur.AtomicEntier8");
            setAliasSetAtomic("majValeur");
            setAliasGetAtomic("valeur");
            setAliasFile("$coeur.Fichier");
            setAliasRead("lire");
            setAliasWrite("ecrire");
            setAliasAppendToFile("ajouterFinFichier");
        }
    }
}
