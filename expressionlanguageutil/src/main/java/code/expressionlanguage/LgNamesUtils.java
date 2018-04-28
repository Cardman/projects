package code.expressionlanguage;

import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
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

    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> stds_ = super.buildFiles(_context);
        String content_ = ResourceFiles.ressourceFichier("resources_lg/threads/runnable.txt");
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{public}", "$public");
        map_.put("{interface}", "$interface");
        map_.put("{Runnable}", aliasRunnable);
        map_.put("{abstract}", "$abstract");
        map_.put("{void}", getAliasVoid());
        map_.put("{run}", aliasRun);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasRunnable);
        stds_.put(aliasRunnable, content_);
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
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasSleep, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        StandardConstructor ctor_;
        params_ = new StringList(aliasRunnable);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasThread, std_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String name_ = _method.getName();
        if (StringList.quickEq(name_,aliasThread)) {
            Thread thread_ = new Thread((Runnable)_args[0]);
            StdStruct std_ = new StdStruct(thread_, aliasThread);
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
}
