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
import code.expressionlanguage.variables.VariableSuffix;
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
    private String aliasIllegalThreadStateException;

    private String aliasCustIterator;
    private String aliasList;
    private String aliasListItr;
    private String aliasLengthItrLi;
    private String aliasLengthLi;
    private String aliasIndexItrLi;
    private String aliasSizeLi;
    private String aliasAddLi;
    private String aliasRemoveLi;
    private String aliasArrayLi;
    private String aliasCustIteratorVar;
    private String aliasListVar;

    private String aliasCustPair;
    private String aliasFirst;
    private String aliasSecond;
    private String aliasSetFirst;
    private String aliasSetSecond;
    private String aliasCustIterTable;
    private String aliasListIterTable;
    private String aliasLengthItrTa;
    private String aliasIndexItrTa;
    private String aliasTable;
    private String aliasListTa;
    private String aliasAddTa;
    private String aliasGetTa;
    private String aliasSizeTa;
    private String aliasGetFirstTa;
    private String aliasGetSecondTa;
    private String aliasSetFirstTa;
    private String aliasSetSecondTa;
    private String aliasRemoveTa;
    private String aliasPairVarFirst;
    private String aliasPairVarSecond;
    private String aliasIterTaVarFirst;
    private String aliasIterTaVarSecond;
    private String aliasTableVarFirst;
    private String aliasTableVarSecond;

    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> stds_ = super.buildFiles(_context);
        String content_ = ResourceFiles.ressourceFichier("resources_lg/threads/runnable.txt");
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String private_ = keyWords_.getKeyWordPrivate();
        String interface_ = keyWords_.getKeyWordInterface();
        String int_ = getAliasPrimInteger();
        String boolean_ = getAliasPrimBoolean();
        String class_ = keyWords_.getKeyWordClass();
        String this_ = keyWords_.getKeyWordThis();
        String new_ = keyWords_.getKeyWordNew();
        String return_ = keyWords_.getKeyWordReturn();
        String iter_ = keyWords_.getKeyWordIter();
        String value_ = keyWords_.getKeyWordValue();
        String abstract_ = keyWords_.getKeyWordAbstract();
        String endLine_ = String.valueOf(_context.getOptions().getEndLine());
        String suffix_ = String.valueOf(_context.getOptions().getSuffix());
        String suffixLocal_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixLocal_ = StringList.concat(suffix_,".");
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixLocal_ = suffix_;
        }
        String suffixParam_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixParam_ = StringList.concat(suffix_,".",suffix_);
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixParam_ = suffix_;
        }
        String suffixLoop_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixLoop_ = suffix_;
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixLoop_ = suffix_;
        }
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
        content_ = ResourceFiles.ressourceFichier("resources_lg/collections/list.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{private}", private_);
        map_.put("{class}", class_);
        map_.put("{Iter}", aliasCustIterator);
        map_.put("{T}", aliasCustIteratorVar);
        map_.put("{List}", aliasList);
        map_.put("{E}", aliasListVar);
        map_.put("{int}", int_);
        map_.put("{boolean}", boolean_);
        map_.put("{i}", tr("i",_context));
        map_.put("{p}", tr("p",_context));
        map_.put("{out}", tr("out",_context));
        map_.put("{ind}", tr("ind",_context));
        map_.put("{param}", suffixParam_);
        map_.put("{local}", suffixLocal_);
        map_.put("{loop}", suffixLoop_);
        map_.put("{iteratorType}", getAliasIteratorType());
        map_.put("{iterable}", getAliasIterable());
        map_.put("{listItr}", aliasListItr);
        map_.put("{lengthItrLi}", aliasLengthItrLi);
        map_.put("{indexItrLi}", aliasIndexItrLi);
        map_.put("{void}", getAliasVoid());
        map_.put("{this}", this_);
        map_.put("{sizeLi}", aliasSizeLi);
        map_.put("{next}", getAliasNext());
        map_.put("{hasNext}", getAliasHasNext());
        map_.put("{return}", return_);
        map_.put("{array}", aliasArrayLi);
        map_.put("{lengthLi}", aliasLengthLi);
        map_.put("{new}", new_);
        map_.put("{clone}", getAliasClone());
        map_.put("{length}", getAliasLength());
        map_.put("{add}", aliasAddLi);
        map_.put("{iter}", iter_);
        map_.put("{value}", value_);
        map_.put("{remove}", aliasRemoveLi);
        map_.put("{iterator}", getAliasIterator());
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasCustIterator);
        getPredefinedClasses().add(aliasList);
        stds_.put(aliasList, content_);
        getPredefinedInterfacesInitOrder().add(aliasCustIterator);
        getPredefinedInterfacesInitOrder().add(aliasList);
        content_ = ResourceFiles.ressourceFichier("resources_lg/collections/table.txt");
        map_.put("{CustPair}",aliasCustPair);
        map_.put("{Pair}",getAliasPairType());
        map_.put("{U}",aliasPairVarFirst);
        map_.put("{V}",aliasPairVarSecond);
        map_.put("{first}",aliasFirst);
        map_.put("{second}",aliasSecond);
        map_.put("{getFirst}",getAliasGetFirst());
        map_.put("{getSecond}",getAliasGetSecond());
        map_.put("{setFirst}",aliasSetFirst);
        map_.put("{setSecond}",aliasSetSecond);
        map_.put("{f}", tr("f",_context));
        map_.put("{s}", tr("s",_context));
        map_.put("{CustIterTable}", aliasCustIterTable);
        map_.put("{IterTypeTable}", getAliasIteratorTableType());
        map_.put("{listItrTa}", aliasListIterTable);
        map_.put("{lengthItrTa}", aliasLengthItrTa);
        map_.put("{indexItrTa}", aliasIndexItrTa);
        map_.put("{A}",aliasIterTaVarFirst);
        map_.put("{B}",aliasIterTaVarSecond);
        map_.put("{nextPair}",getAliasNextPair());
        map_.put("{hasNextPair}",getAliasHasNextPair());
        map_.put("{Table}",aliasTable);
        map_.put("{listTa}",aliasListTa);
        map_.put("{iterableTable}",getAliasIterableTable());
        map_.put("{E}",aliasTableVarFirst);
        map_.put("{F}",aliasTableVarSecond);
        map_.put("{addTa}",aliasAddTa);
        map_.put("{sizeTa}",aliasSizeTa);
        map_.put("{getTa}",aliasGetTa);
        map_.put("{getFirstTa}",aliasGetFirstTa);
        map_.put("{getSecondTa}",aliasGetSecondTa);
        map_.put("{setFirstTa}",aliasSetFirstTa);
        map_.put("{setSecondTa}",aliasSetSecondTa);
        map_.put("{removeTa}",aliasRemoveTa);
        map_.put("{iteratorTable}",getAliasIteratorTable());
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasCustPair);
        getPredefinedClasses().add(aliasCustIterTable);
        getPredefinedClasses().add(aliasTable);
        stds_.put(aliasTable, content_);
        getPredefinedInterfacesInitOrder().add(aliasCustPair);
        getPredefinedInterfacesInitOrder().add(aliasCustIterTable);
        getPredefinedInterfacesInitOrder().add(aliasTable);
        return stds_;
    }
    private static String tr(String _var, ContextEl _context) {
        StringList allKeysWords_ = _context.getKeyWords().allKeyWords();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
        String candidate_ = _var;
        int index_ = 0;
        while (StringList.contains(allKeysWords_,candidate_)) {
            candidate_ = StringList.concatNbs(_var,index_);
            index_++;
        }
        return candidate_;
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
        method_ = new StandardMethod(aliasJoin, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasIsAlive, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
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
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasIllegalThreadStateException, fields_, constructors_, methods_, getAliasError(), MethodModifier.ABSTRACT);
        getStandards().put(aliasIllegalThreadStateException, stdcl_);
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
            AtomicInteger at_ = new AtomicInteger(((NumberStruct)_args[0]).intStruct());
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
            AtomicLong at_ = new AtomicLong(((NumberStruct)_args[0]).longStruct());
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
            	CustInitializer cust_ = ((RunnableContextEl)_cont).getCustInit();
                String dtPart_ = cust_.getCurrentFileThread((RunnableContextEl)_cont);
                log(dtPart_, (RunnableContextEl)_cont, _method, _args);
                ResultErrorStd out_ = new ResultErrorStd();
                out_.setResult(NullStruct.NULL_VALUE);
                return out_;
            }
            if (StringList.quickEq(name_,aliasStart)) {
                Thread thread_ = (Thread)((StdStruct) _instance).getInstance();
                try {
                    thread_.start();
                    res_.setResult(NullStruct.NULL_VALUE);
                } catch (Exception e) {
                    res_.setError(getAliasIllegalThreadStateException());
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasSleep)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (!(_args[0] instanceof NumberStruct)) {
                    res_.setError(getAliasNullPe());
                    return res_;
                }
                res_.setResult(new BooleanStruct(sleep(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasJoin)) {
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                CustInitializer cust_ = ((RunnableContextEl)_cont).getCustInit();
                boolean alive_ = cust_.isAlive(thread_);
                try {
                    thread_.join();
                } catch (Exception e) {
                    //skip exception
                }
                res_.setResult(new BooleanStruct(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsAlive)) {
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                CustInitializer cust_ = ((RunnableContextEl)_cont).getCustInit();
                boolean alive_ = cust_.isAlive(thread_);
                res_.setResult(new BooleanStruct(alive_));
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
                try {
                    thread_.setPriority(((NumberStruct)_args[0]).intStruct());
                    res_.setResult(NullStruct.NULL_VALUE);
                } catch (Exception e) {
                    res_.setError(getAliasIllegalArg());
                }
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
                if (!re_.isHeldByCurrentThread()) {
                    res_.setError(getAliasIllegalThreadStateException());
                } else {
                    re_.unlock();
                    res_.setResult(NullStruct.NULL_VALUE);
                }
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
                re_.set(((NumberStruct)_args[0]).intStruct());
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
                re_.set(((NumberStruct)_args[0]).longStruct());
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
        String folder_ = _cont.getExecutingOptions().getLogFolder();
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
                stringAppFile_ = ((DisplayableStruct)_args[0]).getDisplayedString(_cont).getInstance();
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
                    stringAppFile_ = StringList.simpleStringsFormat(((StringStruct)_args[0]).getInstance(), values_);
                } else {
                    stringAppFile_ = _cont.getStandards().getNullString();
                }
            } else {
                if (_args[0] instanceof DisplayableStruct) {
                    stringAppFile_ = ((DisplayableStruct)_args[0]).getDisplayedString(_cont).getInstance();
                } else {
                    stringAppFile_ = StringList.concat(_args[0].getClassName(_cont),"...");
                }

            }
        }
        return stringAppFile_;
    }
    @Override
    public StringMap<StringList> allTableTypeMethodNames() {
        StringMap<StringList> m_ = super.allTableTypeMethodNames();
        m_.put(getAliasThread(), new StringList(
                getAliasStart(),
                getAliasIsAlive(),
                getAliasJoin(),
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
        m_.put(getAliasCustIterator(), new StringList(
                getAliasNext(),
                getAliasHasNext()));
        m_.put(getAliasList(), new StringList(
                getAliasAddLi(),
                getAliasSizeLi(),
                getAliasRemoveLi(),
                getAliasIterator()));
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
        ref_.add(getAliasIllegalThreadStateException());
        ref_.add(getAliasCustIterator());
        ref_.add(getAliasList());
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

    public String getAliasIllegalThreadStateException() {
        return aliasIllegalThreadStateException;
    }

    public void setAliasIllegalThreadStateException(String _aliasIllegalThreadStateException) {
        aliasIllegalThreadStateException = _aliasIllegalThreadStateException;
    }

    public String getAliasCustIterator() {
        return aliasCustIterator;
    }

    public void setAliasCustIterator(String aliasCustIterator) {
        this.aliasCustIterator = aliasCustIterator;
    }

    public String getAliasList() {
        return aliasList;
    }

    public void setAliasList(String aliasList) {
        this.aliasList = aliasList;
    }

    public String getAliasListItr() {
        return aliasListItr;
    }

    public void setAliasListItr(String aliasListItr) {
        this.aliasListItr = aliasListItr;
    }

    public String getAliasLengthItrLi() {
        return aliasLengthItrLi;
    }

    public void setAliasLengthItrLi(String aliasLengthItrLi) {
        this.aliasLengthItrLi = aliasLengthItrLi;
    }

    public String getAliasLengthLi() {
        return aliasLengthLi;
    }

    public void setAliasLengthLi(String aliasLengthLi) {
        this.aliasLengthLi = aliasLengthLi;
    }

    public String getAliasIndexItrLi() {
        return aliasIndexItrLi;
    }

    public void setAliasIndexItrLi(String aliasIndexItrLi) {
        this.aliasIndexItrLi = aliasIndexItrLi;
    }

    public String getAliasSizeLi() {
        return aliasSizeLi;
    }

    public void setAliasSizeLi(String aliasSizeLi) {
        this.aliasSizeLi = aliasSizeLi;
    }

    public String getAliasAddLi() {
        return aliasAddLi;
    }

    public void setAliasAddLi(String aliasAddLi) {
        this.aliasAddLi = aliasAddLi;
    }

    public String getAliasRemoveLi() {
        return aliasRemoveLi;
    }

    public void setAliasRemoveLi(String aliasRemoveLi) {
        this.aliasRemoveLi = aliasRemoveLi;
    }

    public String getAliasArrayLi() {
        return aliasArrayLi;
    }

    public void setAliasArrayLi(String aliasArrayLi) {
        this.aliasArrayLi = aliasArrayLi;
    }

    public String getAliasCustIteratorVar() {
        return aliasCustIteratorVar;
    }

    public void setAliasCustIteratorVar(String aliasCustIteratorVar) {
        this.aliasCustIteratorVar = aliasCustIteratorVar;
    }

    public String getAliasListVar() {
        return aliasListVar;
    }

    public void setAliasListVar(String aliasListVar) {
        this.aliasListVar = aliasListVar;
    }

    public String getAliasCustPair() {
        return aliasCustPair;
    }

    public void setAliasCustPair(String aliasCustPair) {
        this.aliasCustPair = aliasCustPair;
    }

    public String getAliasFirst() {
        return aliasFirst;
    }

    public void setAliasFirst(String aliasFirst) {
        this.aliasFirst = aliasFirst;
    }

    public String getAliasSecond() {
        return aliasSecond;
    }

    public void setAliasSecond(String aliasSecond) {
        this.aliasSecond = aliasSecond;
    }

    public String getAliasSetFirst() {
        return aliasSetFirst;
    }

    public void setAliasSetFirst(String aliasSetFirst) {
        this.aliasSetFirst = aliasSetFirst;
    }

    public String getAliasSetSecond() {
        return aliasSetSecond;
    }

    public void setAliasSetSecond(String aliasSetSecond) {
        this.aliasSetSecond = aliasSetSecond;
    }

    public String getAliasCustIterTable() {
        return aliasCustIterTable;
    }

    public void setAliasCustIterTable(String aliasCustIterTable) {
        this.aliasCustIterTable = aliasCustIterTable;
    }

    public String getAliasListIterTable() {
        return aliasListIterTable;
    }

    public void setAliasListIterTable(String aliasListIterTable) {
        this.aliasListIterTable = aliasListIterTable;
    }

    public String getAliasLengthItrTa() {
        return aliasLengthItrTa;
    }

    public void setAliasLengthItrTa(String aliasLengthItrTa) {
        this.aliasLengthItrTa = aliasLengthItrTa;
    }

    public String getAliasIndexItrTa() {
        return aliasIndexItrTa;
    }

    public void setAliasIndexItrTa(String aliasIndexItrTa) {
        this.aliasIndexItrTa = aliasIndexItrTa;
    }

    public String getAliasTable() {
        return aliasTable;
    }

    public void setAliasTable(String aliasTable) {
        this.aliasTable = aliasTable;
    }

    public String getAliasListTa() {
        return aliasListTa;
    }

    public void setAliasListTa(String aliasListTa) {
        this.aliasListTa = aliasListTa;
    }

    public String getAliasAddTa() {
        return aliasAddTa;
    }

    public void setAliasAddTa(String aliasAddTa) {
        this.aliasAddTa = aliasAddTa;
    }

    public String getAliasGetTa() {
        return aliasGetTa;
    }

    public void setAliasGetTa(String aliasGetTa) {
        this.aliasGetTa = aliasGetTa;
    }

    public String getAliasSizeTa() {
        return aliasSizeTa;
    }

    public void setAliasSizeTa(String aliasSizeTa) {
        this.aliasSizeTa = aliasSizeTa;
    }

    public String getAliasGetFirstTa() {
        return aliasGetFirstTa;
    }

    public void setAliasGetFirstTa(String aliasGetFirstTa) {
        this.aliasGetFirstTa = aliasGetFirstTa;
    }

    public String getAliasGetSecondTa() {
        return aliasGetSecondTa;
    }

    public void setAliasGetSecondTa(String aliasGetSecondTa) {
        this.aliasGetSecondTa = aliasGetSecondTa;
    }

    public String getAliasSetFirstTa() {
        return aliasSetFirstTa;
    }

    public void setAliasSetFirstTa(String aliasSetFirstTa) {
        this.aliasSetFirstTa = aliasSetFirstTa;
    }

    public String getAliasSetSecondTa() {
        return aliasSetSecondTa;
    }

    public void setAliasSetSecondTa(String aliasSetSecondTa) {
        this.aliasSetSecondTa = aliasSetSecondTa;
    }

    public String getAliasRemoveTa() {
        return aliasRemoveTa;
    }

    public void setAliasRemoveTa(String aliasRemoveTa) {
        this.aliasRemoveTa = aliasRemoveTa;
    }

    public String getAliasPairVarFirst() {
        return aliasPairVarFirst;
    }

    public void setAliasPairVarFirst(String aliasPairVarFirst) {
        this.aliasPairVarFirst = aliasPairVarFirst;
    }

    public String getAliasPairVarSecond() {
        return aliasPairVarSecond;
    }

    public void setAliasPairVarSecond(String aliasPairVarSecond) {
        this.aliasPairVarSecond = aliasPairVarSecond;
    }

    public String getAliasIterTaVarFirst() {
        return aliasIterTaVarFirst;
    }

    public void setAliasIterTaVarFirst(String aliasIterTaVarFirst) {
        this.aliasIterTaVarFirst = aliasIterTaVarFirst;
    }

    public String getAliasIterTaVarSecond() {
        return aliasIterTaVarSecond;
    }

    public void setAliasIterTaVarSecond(String aliasIterTaVarSecond) {
        this.aliasIterTaVarSecond = aliasIterTaVarSecond;
    }

    public String getAliasTableVarFirst() {
        return aliasTableVarFirst;
    }

    public void setAliasTableVarFirst(String aliasTableVarFirst) {
        this.aliasTableVarFirst = aliasTableVarFirst;
    }

    public String getAliasTableVarSecond() {
        return aliasTableVarSecond;
    }

    public void setAliasTableVarSecond(String aliasTableVarSecond) {
        this.aliasTableVarSecond = aliasTableVarSecond;
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
            setAliasIllegalThreadStateException("$core.IllegalThreadState");
            setAliasCustIterator("$core.CustIterator");
            setAliasList("$core.List");
            setAliasListItr("list");
            setAliasLengthItrLi("length");
            setAliasLengthLi("length");
            setAliasIndexItrLi("index");
            setAliasSizeLi("size");
            setAliasAddLi("add");
            setAliasRemoveLi("remove");
            setAliasArrayLi("array");
            setAliasCustIteratorVar("T");
            setAliasListVar("T");
            setAliasCustPair("$core.PairImpl");
            setAliasFirst("first");
            setAliasSecond("second");
            setAliasSetFirst("setFirst");
            setAliasSetSecond("setSecond");
            setAliasPairVarFirst("T");
            setAliasPairVarSecond("U");
            setAliasCustIterTable("$core.CustIteratorTable");
            setAliasListIterTable("list");
            setAliasLengthItrTa("length");
            setAliasIndexItrTa("index");
            setAliasIterTaVarFirst("T");
            setAliasIterTaVarSecond("U");
            setAliasTable("$core.Table");
            setAliasListTa("list");
            setAliasAddTa("add");
            setAliasGetTa("get");
            setAliasSizeTa("size");
            setAliasGetFirstTa("getFirst");
            setAliasGetSecondTa("getSecond");
            setAliasSetFirstTa("setFirst");
            setAliasSetSecondTa("setSecond");
            setAliasRemoveTa("remove");
            setAliasTableVarFirst("T");
            setAliasTableVarSecond("U");
        } else {
            setAliasPrint("afficher");
            setAliasRunnable("$coeur.Executable");
            setAliasRun("executer");
            setAliasThread("$coeur.Tache");
            setAliasStart("demarrer");
            setAliasJoin("attendre");
            setAliasSleep("dormir");
            setAliasIsAlive("estActif");
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
            setAliasIllegalThreadStateException("$coeur.IllegalEtatTache");
            setAliasCustIterator("$coeur.CustIterateur");
            setAliasList("$coeur.Liste");
            setAliasListItr("liste");
            setAliasLengthItrLi("longueur");
            setAliasLengthLi("longueur");
            setAliasIndexItrLi("indice");
            setAliasSizeLi("taille");
            setAliasAddLi("ajouter");
            setAliasRemoveLi("supprimer");
            setAliasArrayLi("tableau");
            setAliasCustIteratorVar("T");
            setAliasListVar("T");
            setAliasCustPair("$core.PairImpl");
            setAliasFirst("premier");
            setAliasSecond("deuxieme");
            setAliasSetFirst("majPremier");
            setAliasSetSecond("majDeuxieme");
            setAliasPairVarFirst("T");
            setAliasPairVarSecond("U");
            setAliasCustIterTable("$coeur.CustIterateurTable");
            setAliasListIterTable("liste");
            setAliasLengthItrTa("longueur");
            setAliasIndexItrTa("indice");
            setAliasIterTaVarFirst("T");
            setAliasIterTaVarSecond("U");
            setAliasTable("$coeur.Table");
            setAliasListTa("liste");
            setAliasAddTa("ajouter");
            setAliasGetTa("val");
            setAliasSizeTa("taille");
            setAliasGetFirstTa("valPremier");
            setAliasGetSecondTa("valDeuxieme");
            setAliasSetFirstTa("majPremier");
            setAliasSetSecondTa("majDeuxieme");
            setAliasRemoveTa("supprimer");
            setAliasTableVarFirst("T");
            setAliasTableVarSecond("U");
        }
    }
    private static boolean sleep(long _time) {
        long millis_ = System.currentTimeMillis();
        boolean slept_ = false;
        while (millis_ + _time > System.currentTimeMillis()) {
            slept_ = true;
        }
        return slept_;
    }
}
