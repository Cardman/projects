package code.expressionlanguage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.resources.ResourceFiles;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public class LgNamesUtils extends LgNames implements LgAdv {

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
    private String aliasIsDaemon;
    private String aliasSetDaemon;
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
        method_ = new StandardMethod(aliasIsDaemon, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasGetPriority, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetDaemon, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
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
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String name_ = _method.getName();
        if (StringList.quickEq(name_,aliasThread)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                return res_;
            }
            Thread thread_ = new Thread((Runnable)_args[0]);
            StdStruct std_ = StdStruct.newInstance(thread_, aliasThread);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasReentrantLock)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                return res_;
            }
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
            AtomicBoolean at_ = new AtomicBoolean((Boolean)_args[0].getInstance());
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
            AtomicInteger at_ = new AtomicInteger(((Number)_args[0].getInstance()).intValue());
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
            AtomicLong at_ = new AtomicLong(((Number)_args[0].getInstance()).longValue());
            StdStruct std_ = StdStruct.newInstance(at_, aliasAtomicLong);
            res_.setResult(std_);
            return res_;
        }
        return res_;
    }

    @Override
    public String getOtherStructClassName(Object _struct, ContextEl _context) {
        if (_struct instanceof Thread) {
            return aliasThread;
        }
        if (_struct instanceof ReentrantLock) {
            return aliasReentrantLock;
        }
        if (_struct instanceof AtomicBoolean) {
            return aliasAtomicBoolean;
        }
        if (_struct instanceof AtomicInteger) {
            return aliasAtomicInteger;
        }
        if (_struct instanceof AtomicLong) {
            return aliasAtomicLong;
        }
        return getAliasObject();
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String className_ = _method.getClassName();
        if (StringList.quickEq(className_,aliasThread)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasStart)) {
                Thread thread_ = (Thread) _instance.getInstance();
                thread_.start();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasSleep)) {
                try {
                    Thread.sleep(((Number)_args[0].getInstance()).longValue());
                    res_.setResult(new BooleanStruct(true));
                } catch (InterruptedException _0) {
                    res_.setResult(new BooleanStruct(false));
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasJoin)) {
                Thread thread_ = (Thread) _instance.getInstance();
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
                Thread thread_ = (Thread) _instance.getInstance();
                boolean alive_ = thread_.isAlive();
                res_.setResult(new BooleanStruct(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsInterrupted)) {
                Thread thread_ = (Thread) _instance.getInstance();
                boolean alive_ = thread_.isInterrupted();
                res_.setResult(new BooleanStruct(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasInterrupt)) {
                Thread thread_ = (Thread) _instance.getInstance();
                thread_.interrupt();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetId)) {
                Thread thread_ = (Thread) _instance.getInstance();
                res_.setResult(new LongStruct(thread_.getId()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetPriority)) {
                Thread thread_ = (Thread) _instance.getInstance();
                res_.setResult(new IntStruct(thread_.getPriority()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsDaemon)) {
                Thread thread_ = (Thread) _instance.getInstance();
                res_.setResult(new BooleanStruct(thread_.isDaemon()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetPriority)) {
                Thread thread_ = (Thread) _instance.getInstance();
                thread_.setPriority(((Number)_args[0].getInstance()).intValue());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetDaemon)) {
                Thread thread_ = (Thread) _instance.getInstance();
                thread_.setDaemon((Boolean)_args[0].getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasYield)) {
                Thread.yield();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasReentrantLock)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasLock)) {
                ReentrantLock re_ = (ReentrantLock) _instance.getInstance();
                re_.lock();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasUnlock)) {
                ReentrantLock re_ = (ReentrantLock) _instance.getInstance();
                re_.unlock();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsHeldByCurrentThread)) {
                ReentrantLock re_ = (ReentrantLock) _instance.getInstance();
                boolean held_ = re_.isHeldByCurrentThread();
                res_.setResult(new BooleanStruct(held_));
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasAtomicBoolean)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) _instance.getInstance();
                boolean held_ = re_.get();
                res_.setResult(new BooleanStruct(held_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) _instance.getInstance();
                re_.set((Boolean)_args[0].getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasAtomicInteger)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) _instance.getInstance();
                int held_ = re_.get();
                res_.setResult(new IntStruct(held_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) _instance.getInstance();
                re_.set(((Number)_args[0].getInstance()).intValue());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasAtomicLong)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicLong re_ = (AtomicLong) _instance.getInstance();
                long held_ = re_.get();
                res_.setResult(new LongStruct(held_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                AtomicLong re_ = (AtomicLong) _instance.getInstance();
                re_.set(((Number)_args[0].getInstance()).longValue());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        return res_;
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
    public String getAliasIsDaemon() {
        return aliasIsDaemon;
    }
    public void setAliasIsDaemon(String _aliasIsDaemon) {
        aliasIsDaemon = _aliasIsDaemon;
    }
    public String getAliasSetDaemon() {
        return aliasSetDaemon;
    }
    public void setAliasSetDaemon(String _aliasSetDaemon) {
        aliasSetDaemon = _aliasSetDaemon;
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
}
