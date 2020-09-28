package code.renders.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.BeanCustLgNames;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.util.ResourcesMessagesUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class LgNamesRenderUtils extends BeanCustLgNames {
    private static final String ATOMIC_INTEGER = "AtomicInteger";
    private static final String ATOMIC_BOOLEAN = "AtomicBoolean";
    private static final String SET_ATOMIC = "SetAtomic";
    private static final String ATOMIC_LONG = "AtomicLong";
    private static final String GET_ATOMIC = "GetAtomic";
    private static final String LAZY_SET_ATOMIC = "LazySetAtomic";
    private static final String COMPARE_AND_SET_ATOMIC = "CompareAndSetAtomic";
    private static final String GET_AND_ADD_ATOMIC = "GetAndAddAtomic";
    private static final String ADD_AND_GET_ATOMIC = "AddAndGetAtomic";
    private static final String GET_AND_INCREMENT_ATOMIC = "GetAndIncrementAtomic";
    private static final String INCREMENT_AND_GET_ATOMIC = "IncrementAndGetAtomic";
    private static final String GET_AND_DECREMENT_ATOMIC = "GetAndDecrementAtomic";
    private static final String DECREMENT_AND_GET_ATOMIC = "DecrementAndGetAtomic";
    private static final String GET_AND_SET_ATOMIC = "GetAndSetAtomic";
    private static final String LENGTH_ITR_TA = "LengthItrTa";
    private static final String PAIR_VAR_FIRST = "PairVarFirst";
    private static final String INDEX_ITR_LI = "IndexItrLi";
    private static final String LIST_ITER_TABLE = "ListIterTable";
    private static final String TABLE_VAR_SECOND = "TableVarSecond";
    private static final String ITER_TA_VAR_FIRST = "IterTaVarFirst";
    private static final String CUST_ITERATOR_VAR = "CustIteratorVar";
    private static final String GET_SECOND_TA = "GetSecondTa";
    private static final String CUST_ITERATOR = "CustIterator";
    private static final String LIST_CLEAR = "ListClear";
    private static final String GET_FIRST_TA = "GetFirstTa";
    private static final String SET_SECOND_TA = "SetSecondTa";
    private static final String CUST_ITER_TABLE = "CustIterTable";
    private static final String TABLE_VAR_FIRST = "TableVarFirst";
    private static final String SET_SECOND = "SetSecond";
    private static final String INDEX_ITR_TA = "IndexItrTa";
    private static final String ITER_TA_VAR_SECOND = "IterTaVarSecond";
    private static final String LENGTH_ITR_LI = "LengthItrLi";
    private static final String SET_FIRST_TA = "SetFirstTa";
    private static final String PAIR_VAR_SECOND = "PairVarSecond";
    private static final String LENGTH_LI = "LengthLi";
    private static final String CUST_PAIR = "CustPair";
    private static final String LIST_TA = "ListTa";
    private static final String LIST_ITR = "ListItr";
    private static final String REMOVE_LI = "RemoveLi";
    private static final String ARRAY_LI = "ArrayLi";
    private static final String FIRST = "First";
    private static final String LIST = "List";
    private static final String SIZE_LI = "SizeLi";
    private static final String LIST_VAR = "ListVar";
    private static final String SECOND = "Second";
    private static final String ADD_LI = "AddLi";
    private static final String SET_FIRST = "SetFirst";
    private static final String TABLE = "Table";
    private static final String ADD_TA = "AddTa";
    private static final String REMOVE_TA = "RemoveTa";
    private static final String GET_TA = "GetTa";
    private static final String SIZE_TA = "SizeTa";
    private String aliasAtomicBoolean;
    private String aliasAtomicInteger;
    private String aliasAtomicLong;
    private String aliasCompareAndSetAtomic;
    private String aliasLazySetAtomic;
    private String aliasSetAtomic;
    private String aliasGetAtomic;
    private String aliasAddAndGetAtomic;
    private String aliasGetAndAddAtomic;
    private String aliasGetAndSetAtomic;
    private String aliasIncrementAndGetAtomic;
    private String aliasGetAndIncrementAtomic;
    private String aliasDecrementAndGetAtomic;
    private String aliasGetAndDecrementAtomic;

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
    private String aliasListClear;
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

    private AbstractResourcesReader reader = new DefaultResourcesReader();

    public LgNamesRenderUtils(AbstractGenerator _gene) {
        super(_gene);
    }

    @Override
    public StringMap<String> buildFiles(AnalyzedPageEl _page) {
        StringMap<String> stds_ = super.buildFiles(_page);
        KeyWords keyWords_ = _page.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String private_ = keyWords_.getKeyWordPrivate();
        String int_ = getAliasPrimInteger();
        String boolean_ = getAliasPrimBoolean();
        String class_ = keyWords_.getKeyWordClass();
        String this_ = keyWords_.getKeyWordThis();
        String new_ = keyWords_.getKeyWordNew();
        String return_ = keyWords_.getKeyWordReturn();
        String iter_ = keyWords_.getKeyWordIter();
        String value_ = keyWords_.getKeyWordValue();
        String endLine_ = String.valueOf(';');
        String suffixLocal_ = "";
        String suffixParam_ = "";
        String suffixLoop_ = "";
        String suffixCatch_ = "";
        StringMap<String> map_;
        String content_ = reader.read("resources_renders/collections/list.txt");
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
        map_.put("{i}", tr("i", _page));
        map_.put("{p}", tr("p", _page));
        map_.put("{out}", tr("out", _page));
        map_.put("{ind}", tr("ind", _page));
        map_.put("{param}", suffixParam_);
        map_.put("{local}", suffixLocal_);
        map_.put("{loop}", suffixLoop_);
        map_.put("{cv}", suffixCatch_);
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
        map_.put("{clear}",aliasListClear);
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasCustIterator);
        getPredefinedClasses().add(aliasList);
        stds_.put(aliasList, content_);
        getPredefinedInterfacesInitOrder().add(aliasCustIterator);
        getPredefinedInterfacesInitOrder().add(aliasList);
        content_ = reader.read("resources_lg/collections/table.txt");
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
        map_.put("{f}", tr("f", _page));
        map_.put("{s}", tr("s", _page));
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
    private static String tr(String _var, AnalyzedPageEl analyzing) {
        CustList<String> allKeysWords_ = analyzing.getKeyWords().allKeyWords().values();
        allKeysWords_.addAllElts(analyzing.getPrimitiveTypes().getKeys());
        allKeysWords_.add(analyzing.getStandards().getAliasVoid());
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
        super.buildOther();
        CustList<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        StandardClass stdcl_;
        StandardConstructor ctor_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicBoolean, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimBoolean(),getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().addEntry(aliasAtomicBoolean, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicInteger, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIncrementAndGetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndIncrementAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDecrementAndGetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndDecrementAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().addEntry(aliasAtomicInteger, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicLong, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasSetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimLong(),getAliasPrimLong());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIncrementAndGetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndIncrementAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDecrementAndGetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndDecrementAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimLong());
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().addEntry(aliasAtomicLong, std_);
    }
    @Override
    public Argument defaultInstance(ContextEl _cont, String _id) {
        if (StringList.quickEq(_id,getAliasObject())) {
            return super.defaultInstance(_cont,_id);
        }
        if (StringList.quickEq(_id,aliasAtomicBoolean)) {
            AtomicBoolean at_ = new AtomicBoolean();
            DefaultStruct std_ = DefaultStruct.newInstance(at_, aliasAtomicBoolean);
            return new Argument(std_);
        }
        if (StringList.quickEq(_id,aliasAtomicInteger)) {
            AtomicInteger at_ = new AtomicInteger();
            DefaultStruct std_ = DefaultStruct.newInstance(at_, aliasAtomicInteger);
            return new Argument(std_);
        }
        if (StringList.quickEq(_id,aliasAtomicLong)) {
            AtomicLong at_ = new AtomicLong();
            DefaultStruct std_ = DefaultStruct.newInstance(at_, aliasAtomicLong);
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
        if (StringList.quickEq(name_,aliasAtomicBoolean)) {
            if (_method.getParametersTypes().isEmpty()) {
                AtomicBoolean at_ = new AtomicBoolean();
                DefaultStruct std_ = DefaultStruct.newInstance(at_, aliasAtomicBoolean);
                res_.setResult(std_);
                return res_;
            }
            AtomicBoolean at_ = new AtomicBoolean(BooleanStruct.isTrue(_args[0]));
            DefaultStruct std_ = DefaultStruct.newInstance(at_, aliasAtomicBoolean);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasAtomicInteger)) {
            if (_method.getParametersTypes().isEmpty()) {
                AtomicInteger at_ = new AtomicInteger();
                DefaultStruct std_ = DefaultStruct.newInstance(at_, aliasAtomicInteger);
                res_.setResult(std_);
                return res_;
            }
            AtomicInteger at_ = new AtomicInteger(((NumberStruct)_args[0]).intStruct());
            DefaultStruct std_ = DefaultStruct.newInstance(at_, aliasAtomicInteger);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasAtomicLong)) {
            if (_method.getParametersTypes().isEmpty()) {
                AtomicLong at_ = new AtomicLong();
                DefaultStruct std_ = DefaultStruct.newInstance(at_, aliasAtomicLong);
                res_.setResult(std_);
                return res_;
            }
            AtomicLong at_ = new AtomicLong(((NumberStruct)_args[0]).longStruct());
            DefaultStruct std_ = DefaultStruct.newInstance(at_, aliasAtomicLong);
            res_.setResult(std_);
            return res_;
        }
        return res_;
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
        if (StringList.quickEq(className_,aliasAtomicBoolean)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((DefaultStruct) _instance).getInstance();
                boolean held_ = re_.get();
                res_.setResult(BooleanStruct.of(held_));
                return res_;
            }
            if (_cont.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                _cont.getInitializingTypeInfos().failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((DefaultStruct) _instance).getInstance();
                re_.set(BooleanStruct.isTrue(_args[0]));
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasCompareAndSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((DefaultStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(BooleanStruct.isTrue(_args[0]),BooleanStruct.isTrue(_args[1]))));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((DefaultStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.getAndSet(BooleanStruct.isTrue(_args[0]))));
                return res_;
            }
            AtomicBoolean re_ = (AtomicBoolean) ((DefaultStruct) _instance).getInstance();
            re_.lazySet(BooleanStruct.isTrue(_args[0]));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(className_,aliasAtomicInteger)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                int held_ = re_.get();
                res_.setResult(new IntStruct(held_));
                return res_;
            }
            if (_cont.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                _cont.getInitializingTypeInfos().failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                re_.set(((NumberStruct)_args[0]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasCompareAndSetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndSetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndSet(((NumberStruct)_args[0]).intStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndAddAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndAdd(((NumberStruct)_args[0]).intStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasAddAndGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.addAndGet(((NumberStruct)_args[0]).intStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndIncrementAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndIncrement()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIncrementAndGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.incrementAndGet()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndDecrementAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndDecrement()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasDecrementAndGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.decrementAndGet()));
                return res_;
            }
            AtomicInteger re_ = (AtomicInteger) ((DefaultStruct) _instance).getInstance();
            re_.lazySet(((NumberStruct)_args[0]).intStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(className_,aliasAtomicLong)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                long held_ = re_.get();
                res_.setResult(new LongStruct(held_));
                return res_;
            }
            if (_cont.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                _cont.getInitializingTypeInfos().failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                re_.set(((NumberStruct)_args[0]).longStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasCompareAndSetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(((NumberStruct)_args[0]).longStruct(),((NumberStruct)_args[1]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndSetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndSet(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndAddAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndAdd(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasAddAndGetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.addAndGet(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndIncrementAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndIncrement()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIncrementAndGetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.incrementAndGet()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndDecrementAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndDecrement()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasDecrementAndGetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.decrementAndGet()));
                return res_;
            }
            AtomicLong re_ = (AtomicLong) ((DefaultStruct) _instance).getInstance();
            re_.lazySet(((NumberStruct)_args[0]).longStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> t_ = super.allTableTypeVarTypes();
        t_.addEntry(getAliasList(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(LIST_VAR,getAliasListVar())));
        t_.addEntry(getAliasCustIterator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CUST_ITERATOR_VAR,getAliasCustIteratorVar())));
        t_.addEntry(getAliasTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TABLE_VAR_FIRST,getAliasTableVarFirst()),
                new KeyValueMemberName(TABLE_VAR_SECOND,getAliasTableVarSecond())));
        t_.addEntry(getAliasCustIterTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITER_TA_VAR_FIRST,getAliasIterTaVarFirst()),
                new KeyValueMemberName(ITER_TA_VAR_SECOND,getAliasIterTaVarSecond())));
        t_.addEntry(getAliasCustPair(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PAIR_VAR_FIRST,getAliasPairVarFirst()),
                new KeyValueMemberName(PAIR_VAR_SECOND,getAliasPairVarSecond())));
        return t_;
    }

    @Override
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = super.allMergeTableTypeMethodNames();
        list_.add(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR,getAliasIterator()),
                new KeyValueMemberName(HAS_NEXT,getAliasHasNext()),
                new KeyValueMemberName(NEXT,getAliasNext()),
                new KeyValueMemberName(ITERATOR_TABLE,getAliasIteratorTable()),
                new KeyValueMemberName(HAS_NEXT_PAIR,getAliasHasNextPair()),
                new KeyValueMemberName(NEXT_PAIR,getAliasNextPair()),
                new KeyValueMemberName(GET_FIRST,getAliasGetFirst()),
                new KeyValueMemberName(GET_SECOND,getAliasGetSecond()),
                new KeyValueMemberName(ENUM_ORDINAL,getAliasEnumOrdinal()),
                new KeyValueMemberName(ENUM_NAME,getAliasEnumName()),
                new KeyValueMemberName(SEED_GET,getAliasSeedGet())
        ));
        return list_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames();
        f_.addEntry(getAliasCustIterator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(LIST_ITR,getAliasListItr()),
                new KeyValueMemberName(LENGTH_ITR_LI,getAliasLengthItrLi()),
                new KeyValueMemberName(INDEX_ITR_LI,getAliasIndexItrLi())
        ));
        f_.addEntry(getAliasList(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ARRAY_LI,getAliasArrayLi()),
                new KeyValueMemberName(LENGTH_LI,getAliasLengthLi())
        ));
        f_.addEntry(getAliasCustIterTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(LIST_ITER_TABLE,getAliasListIterTable()),
                new KeyValueMemberName(LENGTH_ITR_TA,getAliasLengthItrTa()),
                new KeyValueMemberName(INDEX_ITR_TA,getAliasIndexItrTa())
        ));
        f_.addEntry(getAliasCustPair(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIRST,getAliasFirst()),
                new KeyValueMemberName(SECOND,getAliasSecond())
        ));
        f_.addEntry(getAliasTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(LIST_TA,getAliasListTa())
        ));
        return f_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames();
        m_.addEntry(getAliasAtomicBoolean(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ATOMIC,getAliasGetAtomic()),
                new KeyValueMemberName(SET_ATOMIC,getAliasSetAtomic()),
                new KeyValueMemberName(COMPARE_AND_SET_ATOMIC,getAliasCompareAndSetAtomic()),
                new KeyValueMemberName(GET_AND_SET_ATOMIC,getAliasGetAndSetAtomic()),
                new KeyValueMemberName(LAZY_SET_ATOMIC,getAliasLazySetAtomic())));
        m_.addEntry(getAliasAtomicInteger(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ATOMIC,getAliasGetAtomic()),
                new KeyValueMemberName(SET_ATOMIC,getAliasSetAtomic()),
                new KeyValueMemberName(COMPARE_AND_SET_ATOMIC,getAliasCompareAndSetAtomic()),
                new KeyValueMemberName(GET_AND_SET_ATOMIC,getAliasGetAndSetAtomic()),
                new KeyValueMemberName(LAZY_SET_ATOMIC,getAliasLazySetAtomic()),
                new KeyValueMemberName(ADD_AND_GET_ATOMIC,getAliasAddAndGetAtomic()),
                new KeyValueMemberName(GET_AND_ADD_ATOMIC,getAliasGetAndAddAtomic()),
                new KeyValueMemberName(INCREMENT_AND_GET_ATOMIC,getAliasIncrementAndGetAtomic()),
                new KeyValueMemberName(GET_AND_INCREMENT_ATOMIC,getAliasGetAndIncrementAtomic()),
                new KeyValueMemberName(DECREMENT_AND_GET_ATOMIC,getAliasDecrementAndGetAtomic()),
                new KeyValueMemberName(GET_AND_DECREMENT_ATOMIC,getAliasGetAndDecrementAtomic())));
        m_.addEntry(getAliasAtomicLong(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ATOMIC,getAliasGetAtomic()),
                new KeyValueMemberName(SET_ATOMIC,getAliasSetAtomic()),
                new KeyValueMemberName(COMPARE_AND_SET_ATOMIC,getAliasCompareAndSetAtomic()),
                new KeyValueMemberName(GET_AND_SET_ATOMIC,getAliasGetAndSetAtomic()),
                new KeyValueMemberName(LAZY_SET_ATOMIC,getAliasLazySetAtomic()),
                new KeyValueMemberName(ADD_AND_GET_ATOMIC,getAliasAddAndGetAtomic()),
                new KeyValueMemberName(GET_AND_ADD_ATOMIC,getAliasGetAndAddAtomic()),
                new KeyValueMemberName(INCREMENT_AND_GET_ATOMIC,getAliasIncrementAndGetAtomic()),
                new KeyValueMemberName(GET_AND_INCREMENT_ATOMIC,getAliasGetAndIncrementAtomic()),
                new KeyValueMemberName(DECREMENT_AND_GET_ATOMIC,getAliasDecrementAndGetAtomic()),
                new KeyValueMemberName(GET_AND_DECREMENT_ATOMIC,getAliasGetAndDecrementAtomic())));
        m_.addEntry(getAliasCustIterator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(NEXT,getAliasNext()),
                new KeyValueMemberName(HAS_NEXT,getAliasHasNext())));
        m_.addEntry(getAliasList(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ADD_LI,getAliasAddLi()),
                new KeyValueMemberName(SIZE_LI,getAliasSizeLi()),
                new KeyValueMemberName(REMOVE_LI,getAliasRemoveLi()),
                new KeyValueMemberName(ITERATOR,getAliasIterator()),
                new KeyValueMemberName(LIST_CLEAR,getAliasListClear())));
        m_.addEntry(getAliasCustPair(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_FIRST,getAliasGetFirst()),
                new KeyValueMemberName(SET_FIRST,getAliasSetFirst()),
                new KeyValueMemberName(GET_SECOND,getAliasGetSecond()),
                new KeyValueMemberName(SET_SECOND,getAliasSetSecond())));
        m_.addEntry(getAliasCustIterTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(NEXT_PAIR,getAliasNextPair()),
                new KeyValueMemberName(HAS_NEXT_PAIR,getAliasHasNextPair())));
        m_.addEntry(getAliasTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_FIRST_TA,getAliasGetFirstTa()),
                new KeyValueMemberName(GET_SECOND_TA,getAliasGetSecondTa()),
                new KeyValueMemberName(SET_FIRST_TA,getAliasSetFirstTa()),
                new KeyValueMemberName(SET_SECOND_TA,getAliasSetSecondTa()),
                new KeyValueMemberName(ADD_TA,getAliasAddTa()),
                new KeyValueMemberName(REMOVE_TA,getAliasRemoveTa()),
                new KeyValueMemberName(SIZE_TA,getAliasSizeTa()),
                new KeyValueMemberName(GET_TA,getAliasGetTa()),
                new KeyValueMemberName(ITERATOR_TABLE,getAliasIteratorTable())
        ));
        return m_;
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ =  super.allRefTypes();
        ref_.addEntry(ATOMIC_BOOLEAN,getAliasAtomicBoolean());
        ref_.addEntry(ATOMIC_INTEGER,getAliasAtomicInteger());
        ref_.addEntry(ATOMIC_LONG,getAliasAtomicLong());
        ref_.addEntry(CUST_ITERATOR,getAliasCustIterator());
        ref_.addEntry(LIST,getAliasList());
        ref_.addEntry(CUST_PAIR,getAliasCustPair());
        ref_.addEntry(CUST_ITER_TABLE,getAliasCustIterTable());
        ref_.addEntry(TABLE,getAliasTable());
        return ref_;
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

    public String getAliasCompareAndSetAtomic() {
        return aliasCompareAndSetAtomic;
    }

    public void setAliasCompareAndSetAtomic(String aliasCompareAndSetAtomic) {
        this.aliasCompareAndSetAtomic = aliasCompareAndSetAtomic;
    }

    public String getAliasLazySetAtomic() {
        return aliasLazySetAtomic;
    }

    public void setAliasLazySetAtomic(String aliasLazySetAtomic) {
        this.aliasLazySetAtomic = aliasLazySetAtomic;
    }

    public String getAliasAddAndGetAtomic() {
        return aliasAddAndGetAtomic;
    }

    public void setAliasAddAndGetAtomic(String aliasAddAndGetAtomic) {
        this.aliasAddAndGetAtomic = aliasAddAndGetAtomic;
    }

    public String getAliasGetAndAddAtomic() {
        return aliasGetAndAddAtomic;
    }

    public void setAliasGetAndAddAtomic(String aliasGetAndAddAtomic) {
        this.aliasGetAndAddAtomic = aliasGetAndAddAtomic;
    }

    public String getAliasGetAndSetAtomic() {
        return aliasGetAndSetAtomic;
    }

    public void setAliasGetAndSetAtomic(String aliasGetAndSetAtomic) {
        this.aliasGetAndSetAtomic = aliasGetAndSetAtomic;
    }

    public String getAliasIncrementAndGetAtomic() {
        return aliasIncrementAndGetAtomic;
    }

    public void setAliasIncrementAndGetAtomic(String aliasIncrementAndGetAtomic) {
        this.aliasIncrementAndGetAtomic = aliasIncrementAndGetAtomic;
    }

    public String getAliasGetAndIncrementAtomic() {
        return aliasGetAndIncrementAtomic;
    }

    public void setAliasGetAndIncrementAtomic(String aliasGetAndIncrementAtomic) {
        this.aliasGetAndIncrementAtomic = aliasGetAndIncrementAtomic;
    }

    public String getAliasDecrementAndGetAtomic() {
        return aliasDecrementAndGetAtomic;
    }

    public void setAliasDecrementAndGetAtomic(String aliasDecrementAndGetAtomic) {
        this.aliasDecrementAndGetAtomic = aliasDecrementAndGetAtomic;
    }

    public String getAliasGetAndDecrementAtomic() {
        return aliasGetAndDecrementAtomic;
    }

    public void setAliasGetAndDecrementAtomic(String aliasGetAndDecrementAtomic) {
        this.aliasGetAndDecrementAtomic = aliasGetAndDecrementAtomic;
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

    public String getAliasListClear() {
        return aliasListClear;
    }

    public void setAliasListClear(String _aliasListClear) {
        aliasListClear = _aliasListClear;
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

    @Override
    public void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess) {
        StringBuilder messPart_ = new StringBuilder();
        StringBuilder rendMessPart_ = new StringBuilder();
        StringBuilder keyWordsPart_ = new StringBuilder();
        StringBuilder aliasesPart_ = new StringBuilder();
        StringBuilder tagsPart_ = new StringBuilder();
        StringBuilder attrsPart_ = new StringBuilder();
        StringBuilder valuesPart_ = new StringBuilder();
        StringBuilder styleAttrsPart_ = new StringBuilder();
        StringBuilder styleValuesPart_ = new StringBuilder();
        StringBuilder styleUnitsPart_ = new StringBuilder();
        for (Element c: _elt.getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "messages")) {
                messPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "rendmessages")) {
                rendMessPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "keywords")) {
                keyWordsPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "aliases")) {
                aliasesPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "tags")) {
                tagsPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "attrs")) {
                attrsPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "values")) {
                valuesPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "styleAttrs")) {
                styleAttrsPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "styleValues")) {
                styleValuesPart_.append(c.getAttribute("value"));
            }
            if (StringList.quickEq(fieldName_, "styleUnits")) {
                styleUnitsPart_.append(c.getAttribute("value"));
            }
        }
        StringMap<String> mess_ = new StringMap<String>();
        StringMap<String> rendMess_ = new StringMap<String>();
        StringMap<String> kw_ = new StringMap<String>();
        StringMap<String> al_ = new StringMap<String>();
        StringMap<String> tags_ = new StringMap<String>();
        StringMap<String> attrs_ = new StringMap<String>();
        StringMap<String> values_ = new StringMap<String>();
        StringMap<String> styleAttrs_ = new StringMap<String>();
        StringMap<String> styleValues_ = new StringMap<String>();
        StringMap<String> styleUnits_ = new StringMap<String>();
        buildMap(messPart_, mess_);
        buildMap(rendMessPart_, rendMess_);
        buildMap(keyWordsPart_, kw_);
        buildMap(aliasesPart_, al_);
        buildMap(tagsPart_, tags_);
        buildMap(attrsPart_, attrs_);
        buildMap(valuesPart_, values_);
        buildMap(styleAttrsPart_, styleAttrs_);
        buildMap(styleUnitsPart_, styleUnits_);
        buildMap(styleValuesPart_, styleValues_);
        if (StringList.quickEq(_lg, "en")) {
            messages(_mess,_lg,mess_);
            rendMessages(_rMess,_lg,rendMess_);
            keyWord(_kw,_lg,kw_);
            otherAlias(_lg,al_);
            otherTags(_rkw,_lg,tags_);
            otherAttrs(_rkw,_lg,attrs_);
            otherValues(_rkw,_lg,values_);
            otherStyleAttrs(_rkw,_lg,styleAttrs_);
            otherStyleValues(_rkw,_lg,styleValues_);
            otherStyleUnits(_rkw,_lg,styleUnits_);
        } else if (StringList.quickEq(_lg, "fr")) {
            messages(_mess,_lg,mess_);
            rendMessages(_rMess,_lg,rendMess_);
            keyWord(_kw,_lg,kw_);
            otherAlias(_lg,al_);
            otherTags(_rkw,_lg,tags_);
            otherAttrs(_rkw,_lg,attrs_);
            otherValues(_rkw,_lg,values_);
            otherStyleAttrs(_rkw,_lg,styleAttrs_);
            otherStyleValues(_rkw,_lg,styleValues_);
            otherStyleUnits(_rkw,_lg,styleUnits_);
        } else {
            messages(_mess,mess_,new StringMap<String>());
            rendMessages(_rMess,rendMess_,new StringMap<String>());
            keyWord(_kw,kw_,new StringMap<String>());
            allAlias(al_, new StringMap<String>());
            allTags(_rkw,tags_, new StringMap<String>());
            allAttrs(_rkw,attrs_, new StringMap<String>());
            allValues(_rkw,values_,new StringMap<String>());
            allStyleAttrs(_rkw,styleAttrs_,new StringMap<String>());
            allStyleValues(_rkw,styleValues_,new StringMap<String>());
            allStyleUnits(_rkw,styleUnits_,new StringMap<String>());
        }
    }

    private static void buildMap(StringBuilder _parts, StringMap<String> _map) {
        if (_parts.length() > 0) {
            StringList infos_ = StringList.splitChars(_parts.toString(),',');
            for (String l: infos_) {
                int sep_ = l.indexOf('=');
                if (sep_ < 0) {
                    continue;
                }
                String key_ = l.substring(0, sep_).trim();
                String value_ = StringList.removeAllSpaces(l.substring(sep_ +1));
                value_ = ParseLinesArgUtil.parseValue(value_);
                _map.put(key_,value_);
            }
        }
    }

    private void otherStyleUnits(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"styleunits");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherStyleUnits(_rendKw,util_,_cust);
    }
    private void allStyleUnits(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherStyleUnits(_rendKw,_util,_cust);
    }
    private void otherStyleUnits(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setStyleUnitSolid(get(_util,_cust,RendKeyWords.STYLE_UNIT_SOLID));
        _rendKw.setStyleUnitPx(get(_util,_cust,RendKeyWords.STYLE_UNIT_PX));
        _rendKw.setStyleUnitEm(get(_util,_cust,RendKeyWords.STYLE_UNIT_EM));
    }
    private void otherStyleValues(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"stylevalues");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherStyleValues(_rendKw,util_,_cust);
    }
    private void allStyleValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherStyleValues(_rendKw,_util,_cust);
    }
    private void otherStyleValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setStyleValueGreen(get(_util,_cust,RendKeyWords.STYLE_VALUE_GREEN));
        _rendKw.setStyleValueBlue(get(_util,_cust,RendKeyWords.STYLE_VALUE_BLUE));
        _rendKw.setStyleValueYellow(get(_util,_cust,RendKeyWords.STYLE_VALUE_YELLOW));
        _rendKw.setStyleValueMagenta(get(_util,_cust,RendKeyWords.STYLE_VALUE_MAGENTA));
        _rendKw.setStyleValueCyan(get(_util,_cust,RendKeyWords.STYLE_VALUE_CYAN));
        _rendKw.setStyleValueBlack(get(_util,_cust,RendKeyWords.STYLE_VALUE_BLACK));
        _rendKw.setStyleValueGrey(get(_util,_cust,RendKeyWords.STYLE_VALUE_GREY));
        _rendKw.setStyleValueWhite(get(_util,_cust,RendKeyWords.STYLE_VALUE_WHITE));
        _rendKw.setStyleValueRgb(get(_util,_cust,RendKeyWords.STYLE_VALUE_RGB));
        _rendKw.setStyleValueRed(get(_util,_cust,RendKeyWords.STYLE_VALUE_RED));
    }
    private void otherStyleAttrs(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"styleattrs");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherStyleAttrs(_rendKw,util_,_cust);
    }
    private void allStyleAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherStyleAttrs(_rendKw,_util,_cust);
    }
    private void otherStyleAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setStyleAttrColor(get(_util,_cust,RendKeyWords.STYLE_ATTR_COLOR));
        _rendKw.setStyleAttrBackground(get(_util,_cust,RendKeyWords.STYLE_ATTR_BACKGROUND));
        _rendKw.setStyleAttrBorder(get(_util,_cust,RendKeyWords.STYLE_ATTR_BORDER));
        _rendKw.setStyleAttrFontSize(get(_util,_cust,RendKeyWords.STYLE_ATTR_FONTSIZE));
        _rendKw.setStyleAttrFontFam(get(_util,_cust,RendKeyWords.STYLE_ATTR_FONTFAM));
    }
    private void otherValues(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"values");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherValues(_rendKw,util_,_cust);
    }
    private void allValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherValues(_rendKw,_util,_cust);
    }
    private void otherValues(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setValueNumber(get(_util,_cust,RendKeyWords.VALUE_NUMBER));
        _rendKw.setValueRange(get(_util,_cust,RendKeyWords.VALUE_RANGE));
        _rendKw.setValueRadio(get(_util,_cust,RendKeyWords.VALUE_RADIO));
        _rendKw.setValueCheckbox(get(_util,_cust,RendKeyWords.VALUE_CHECKBOX));
        _rendKw.setValueText(get(_util,_cust,RendKeyWords.VALUE_TEXT));
        _rendKw.setValueLiMinLet(get(_util,_cust,RendKeyWords.VALUE_LIMINLET));
        _rendKw.setValueLiDisk(get(_util,_cust,RendKeyWords.VALUE_LIDISK));
        _rendKw.setValueLiRect(get(_util,_cust,RendKeyWords.VALUE_LIRECT));
        _rendKw.setValueLiSquare(get(_util,_cust,RendKeyWords.VALUE_LISQUARE));
        _rendKw.setValueLiNb(get(_util,_cust,RendKeyWords.VALUE_LINB));
        _rendKw.setValueLiMajLat(get(_util,_cust,RendKeyWords.VALUE_LIMAJLAT));
        _rendKw.setValueStyle(get(_util,_cust,RendKeyWords.VALUE_STYLE));
        _rendKw.setValueLiCircle(get(_util,_cust,RendKeyWords.VALUE_LICIRCLE));
        _rendKw.setValueLiMajLet(get(_util,_cust,RendKeyWords.VALUE_LIMAJLET));
        _rendKw.setValueLiMinLat(get(_util,_cust,RendKeyWords.VALUE_LIMINLAT));
        _rendKw.setValueSubmit(get(_util,_cust,RendKeyWords.VALUE_SUBMIT));
    }
    private void otherAttrs(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"attrs");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherAttrs(_rendKw,util_,_cust);
    }
    private void allAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherAttrs(_rendKw,_util,_cust);
    }
    private void otherAttrs(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setAttrType(get(_util,_cust,RendKeyWords.ATTR_TYPE));
        _rendKw.setAttrClassName(get(_util,_cust,RendKeyWords.ATTR_CLASSNAME));
        _rendKw.setAttrMultiple(get(_util,_cust,RendKeyWords.ATTR_MULTIPLE));
        _rendKw.setAttrValidator(get(_util,_cust,RendKeyWords.ATTR_VALIDATOR));
        _rendKw.setAttrSelected(get(_util,_cust,RendKeyWords.ATTR_SELECTED));
        _rendKw.setAttrAction(get(_util,_cust,RendKeyWords.ATTR_ACTION));
        _rendKw.setAttrAlias(get(_util,_cust,RendKeyWords.ATTR_ALIAS));
        _rendKw.setAttrNf(get(_util,_cust,RendKeyWords.ATTR_N_F));
        _rendKw.setAttrNi(get(_util,_cust,RendKeyWords.ATTR_N_I));
        _rendKw.setAttrChecked(get(_util,_cust,RendKeyWords.ATTR_CHECKED));
        _rendKw.setAttrLabel(get(_util,_cust,RendKeyWords.ATTR_LABEL));
        _rendKw.setAttrCondition(get(_util,_cust,RendKeyWords.ATTR_CONDITION));
        _rendKw.setAttrFor(get(_util,_cust,RendKeyWords.ATTR_FOR));
        _rendKw.setAttrTitle(get(_util,_cust,RendKeyWords.ATTR_TITLE));
        _rendKw.setAttrConvert(get(_util,_cust,RendKeyWords.ATTR_CONVERT));
        _rendKw.setAttrEscaped(get(_util,_cust,RendKeyWords.ATTR_ESCAPED));
        _rendKw.setAttrHref(get(_util,_cust,RendKeyWords.ATTR_HREF));
        _rendKw.setAttrCommand(get(_util,_cust,RendKeyWords.ATTR_COMMAND));
        _rendKw.setAttrMessage(get(_util,_cust,RendKeyWords.ATTR_MESSAGE));
        _rendKw.setAttrDefault(get(_util,_cust,RendKeyWords.ATTR_DEFAULT));
        _rendKw.setAttrPrepare(get(_util,_cust,RendKeyWords.ATTR_PREPARE));
        _rendKw.setAttrForm(get(_util,_cust,RendKeyWords.ATTR_FORM));
        _rendKw.setAttrId(get(_util,_cust,RendKeyWords.ATTR_ID));
        _rendKw.setAttrGroupId(get(_util,_cust,RendKeyWords.ATTR_GROUPID));
        _rendKw.setAttrVarValue(get(_util,_cust,RendKeyWords.ATTR_VARVALUE));
        _rendKw.setAttrBean(get(_util,_cust,RendKeyWords.ATTR_BEAN));
        _rendKw.setAttrNa(get(_util,_cust,RendKeyWords.ATTR_N_A));
        _rendKw.setAttrParam(get(_util,_cust,RendKeyWords.ATTR_PARAM));
        _rendKw.setAttrName(get(_util,_cust,RendKeyWords.ATTR_NAME));
        _rendKw.setAttrQuoted(get(_util,_cust,RendKeyWords.ATTR_QUOTED));
        _rendKw.setAttrRel(get(_util,_cust,RendKeyWords.ATTR_REL));
        _rendKw.setAttrSrc(get(_util,_cust,RendKeyWords.ATTR_SRC));
        _rendKw.setAttrDelay(get(_util,_cust,RendKeyWords.ATTR_DELAY));
        _rendKw.setAttrPage(get(_util,_cust,RendKeyWords.ATTR_PAGE));
        _rendKw.setAttrRows(get(_util,_cust,RendKeyWords.ATTR_ROWS));
        _rendKw.setAttrClass(get(_util,_cust,RendKeyWords.ATTR_CLASS));
        _rendKw.setAttrCols(get(_util,_cust,RendKeyWords.ATTR_COLS));
        _rendKw.setAttrWidth(get(_util,_cust,RendKeyWords.ATTR_WIDTH));
        _rendKw.setAttrList(get(_util,_cust,RendKeyWords.ATTR_LIST));
        _rendKw.setAttrMap(get(_util,_cust,RendKeyWords.ATTR_MAP));
        _rendKw.setAttrKey(get(_util,_cust,RendKeyWords.ATTR_KEY));
        _rendKw.setAttrTo(get(_util,_cust,RendKeyWords.ATTR_TO));
        _rendKw.setAttrEq(get(_util,_cust,RendKeyWords.ATTR_EQ));
        _rendKw.setAttrVar(get(_util,_cust,RendKeyWords.ATTR_VAR));
        _rendKw.setAttrStep(get(_util,_cust,RendKeyWords.ATTR_STEP));
        _rendKw.setAttrFrom(get(_util,_cust,RendKeyWords.ATTR_FROM));
        _rendKw.setAttrValue(get(_util,_cust,RendKeyWords.ATTR_VALUE));
        _rendKw.setAttrInit(get(_util,_cust,RendKeyWords.ATTR_INIT));
        _rendKw.setAttrKeepFields(get(_util,_cust,RendKeyWords.ATTR_KEEPFIELDS));
        _rendKw.setAttrValueMessage(get(_util,_cust,RendKeyWords.ATTR_VALUEMESSAGE));
        _rendKw.setAttrConvertField(get(_util,_cust,RendKeyWords.ATTR_CONVERTFIELD));
        _rendKw.setAttrKeyClassName(get(_util,_cust,RendKeyWords.ATTR_KEYCLASSNAME));
        _rendKw.setAttrVarClassName(get(_util,_cust,RendKeyWords.ATTR_VARCLASSNAME));
        _rendKw.setAttrIndexClassName(get(_util,_cust,RendKeyWords.ATTR_INDEXCLASSNAME));
        _rendKw.setAttrEscapedAmp(get(_util,_cust,RendKeyWords.ATTR_ESCAPEDAMP));
        _rendKw.setAttrConvertValue(get(_util,_cust,RendKeyWords.ATTR_CONVERTVALUE));
        _rendKw.setAttrConvertFieldValue(get(_util,_cust,RendKeyWords.ATTR_CONVERTFIELDVALUE));
    }
    private void otherTags(RendKeyWords _rendKw, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"tags");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherTags(_rendKw,util_,_cust);
    }
    private void allTags(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        otherTags(_rendKw,_util,_cust);
    }
    private void otherTags(RendKeyWords _rendKw, StringMap<String> _util, StringMap<String> _cust) {
        _rendKw.setKeyWordTextarea(get(_util,_cust,RendKeyWords.TAG_TEXTAREA));
        _rendKw.setKeyWordSelect(get(_util,_cust,RendKeyWords.TAG_SELECT));
        _rendKw.setKeyWordFinally(get(_util,_cust,RendKeyWords.TAG_FINALLY));
        _rendKw.setKeyWordContinue(get(_util,_cust,RendKeyWords.TAG_CONTINUE));
        _rendKw.setKeyWordPackage(get(_util,_cust,RendKeyWords.TAG_PACKAGE));
        _rendKw.setKeyWordDefault(get(_util,_cust,RendKeyWords.TAG_DEFAULT));
        _rendKw.setKeyWordReturn(get(_util,_cust,RendKeyWords.TAG_RETURN));
        _rendKw.setKeyWordFor(get(_util,_cust,RendKeyWords.TAG_FOR));
        _rendKw.setKeyWordIf(get(_util,_cust,RendKeyWords.TAG_IF));
        _rendKw.setKeyWordClass(get(_util,_cust,RendKeyWords.TAG_CLASS));
        _rendKw.setKeyWordElseif(get(_util,_cust,RendKeyWords.TAG_ELSEIF));
        _rendKw.setKeyWordThrow(get(_util,_cust,RendKeyWords.TAG_THROW));
        _rendKw.setKeyWordBreak(get(_util,_cust,RendKeyWords.TAG_BREAK));
        _rendKw.setKeyWordCatch(get(_util,_cust,RendKeyWords.TAG_CATCH));
        _rendKw.setKeyWordCase(get(_util,_cust,RendKeyWords.TAG_CASE));
        _rendKw.setKeyWordTry(get(_util,_cust,RendKeyWords.TAG_TRY));
        _rendKw.setKeyWordElse(get(_util,_cust,RendKeyWords.TAG_ELSE));
        _rendKw.setKeyWordSwitch(get(_util,_cust,RendKeyWords.TAG_SWITCH));
        _rendKw.setKeyWordWhile(get(_util,_cust,RendKeyWords.TAG_WHILE));
        _rendKw.setKeyWordDo(get(_util,_cust,RendKeyWords.TAG_DO));
        _rendKw.setKeyWordCaption(get(_util,_cust,RendKeyWords.TAG_CAPTION));
        _rendKw.setKeyWordMessage(get(_util,_cust,RendKeyWords.TAG_MESSAGE));
        _rendKw.setKeyWordAnchor(get(_util,_cust,RendKeyWords.TAG_A));
        _rendKw.setKeyWordParam(get(_util,_cust,RendKeyWords.TAG_PARAM));
        _rendKw.setKeyWordForm(get(_util,_cust,RendKeyWords.TAG_FORM));
        _rendKw.setKeyWordSpan(get(_util,_cust,RendKeyWords.TAG_SPAN));
        _rendKw.setKeyWordImg(get(_util,_cust,RendKeyWords.TAG_IMG));
        _rendKw.setKeyWordInput(get(_util,_cust,RendKeyWords.TAG_INPUT));
        _rendKw.setKeyWordLink(get(_util,_cust,RendKeyWords.TAG_LINK));
        _rendKw.setKeyWordSet(get(_util,_cust,RendKeyWords.TAG_SET));
        _rendKw.setKeyWordSubmit(get(_util,_cust,RendKeyWords.TAG_SUBMIT));
        _rendKw.setKeyWordStyle(get(_util,_cust,RendKeyWords.TAG_STYLE));
        _rendKw.setKeyWordField(get(_util,_cust,RendKeyWords.TAG_FIELD));
        _rendKw.setKeyWordImport(get(_util,_cust,RendKeyWords.TAG_IMPORT));
        _rendKw.setKeyWordItalic(get(_util,_cust,RendKeyWords.TAG_I));
        _rendKw.setKeyWordHr(get(_util,_cust,RendKeyWords.TAG_HR));
        _rendKw.setKeyWordTd(get(_util,_cust,RendKeyWords.TAG_TD));
        _rendKw.setKeyWordBody(get(_util,_cust,RendKeyWords.TAG_BODY));
        _rendKw.setKeyWordOption(get(_util,_cust,RendKeyWords.TAG_OPTION));
        _rendKw.setKeyWordUl(get(_util,_cust,RendKeyWords.TAG_UL));
        _rendKw.setKeyWordHTwo(get(_util,_cust,RendKeyWords.TAG_H2));
        _rendKw.setKeyWordPre(get(_util,_cust,RendKeyWords.TAG_PRE));
        _rendKw.setKeyWordBr(get(_util,_cust,RendKeyWords.TAG_BR));
        _rendKw.setKeyWordHSix(get(_util,_cust,RendKeyWords.TAG_H6));
        _rendKw.setKeyWordMap(get(_util,_cust,RendKeyWords.TAG_MAP));
        _rendKw.setKeyWordTh(get(_util,_cust,RendKeyWords.TAG_TH));
        _rendKw.setKeyWordLi(get(_util,_cust,RendKeyWords.TAG_LI));
        _rendKw.setKeyWordHOne(get(_util,_cust,RendKeyWords.TAG_H1));
        _rendKw.setKeyWordOl(get(_util,_cust,RendKeyWords.TAG_OL));
        _rendKw.setKeyWordBold(get(_util,_cust,RendKeyWords.TAG_B));
        _rendKw.setKeyWordDiv(get(_util,_cust,RendKeyWords.TAG_DIV));
        _rendKw.setKeyWordTr(get(_util,_cust,RendKeyWords.TAG_TR));
        _rendKw.setKeyWordHFive(get(_util,_cust,RendKeyWords.TAG_H5));
        _rendKw.setKeyWordPar(get(_util,_cust,RendKeyWords.TAG_P));
        _rendKw.setKeyWordHead(get(_util,_cust,RendKeyWords.TAG_HEAD));
        _rendKw.setKeyWordHFour(get(_util,_cust,RendKeyWords.TAG_H4));
        _rendKw.setKeyWordTable(get(_util,_cust,RendKeyWords.TAG_TABLE));
        _rendKw.setKeyWordHThree(get(_util,_cust,RendKeyWords.TAG_H3));
    }
    private void otherAlias(String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"types");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherAlias(util_,_cust);
    }
    private void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        otherAlias(_util,_cust);
    }
    private void otherAlias(StringMap<String> _util, StringMap<String> _cust) {
        setDefaultPkg(get(_util,_cust, DEFAULT_PKG));
        setAliasMaxValueField(get(_util,_cust, FIELD_MAX_VALUE));
        setAliasMinValueField(get(_util,_cust, FIELD_MIN_VALUE));
        setAliasPlusInfinityField(get(_util,_cust, FIELD_PLUS_INFINITY));
        setAliasMinusInfinityField(get(_util,_cust, FIELD_MINUS_INFINITY));
        setAliasNanField(get(_util,_cust, FIELD_NAN));
        setAliasBadEncode(get(_util,_cust, BAD_ENCODE));
        setAliasDivisionZero(get(_util,_cust, DIVISION_ZERO));
        setAliasCharSequence(get(_util,_cust, CHAR_SEQUENCE));
        setAliasIteratorType(get(_util,_cust, ITERATOR_TYPE));
        setAliasEnumParam(get(_util,_cust, ENUM_PARAM));
        setAliasGetMessage(get(_util,_cust, GET_MESSAGE));
        setAliasIteratorTableTypeVarFirst(get(_util,_cust, ITERATOR_TABLE_TYPE_VAR_FIRST));
        setAliasIteratorTableTypeVarSecond(get(_util,_cust, ITERATOR_TABLE_TYPE_VAR_SECOND));
        setAliasEquals(get(_util,_cust, EQUALS));
        setAliasLong(get(_util,_cust, LONG));
        setAliasShort(get(_util,_cust, SHORT));
        setAliasPrimChar(get(_util,_cust, PRIM_CHAR));
        setAliasNumber(get(_util,_cust, NUMBER));
        setAliasParseInt(get(_util,_cust, PARSE_INT));
        setAliasCompare(get(_util,_cust, COMPARE));
        setAliasIntValue(get(_util,_cust, INT_VALUE));
        setAliasBoolean(get(_util,_cust, BOOLEAN));
        setAliasPrimLong(get(_util,_cust, PRIM_LONG));
        setAliasByte(get(_util,_cust, BYTE));
        setAliasFloat(get(_util,_cust, FLOAT));
        setAliasDouble(get(_util,_cust, DOUBLE));
        setAliasInteger(get(_util,_cust, INTEGER));
        setAliasDigit(get(_util,_cust, DIGIT));
        setAliasIsDigit(get(_util,_cust, IS_DIGIT));
        setAliasMath(get(_util,_cust, MATH));
        setAliasBadSize(get(_util,_cust, BAD_SIZE));
        setAliasNullPe(get(_util,_cust, NULL_PE));
        setAliasObject(get(_util,_cust, OBJECT));
        setAliasIterator(get(_util,_cust, ITERATOR));
        setAliasCastType(get(_util,_cust, CAST_TYPE));
        setAliasStore(get(_util,_cust, STORE));
        setAliasEnumType(get(_util,_cust, ENUM_TYPE));
        setAliasPrimByte(get(_util,_cust, PRIM_BYTE));
        setAliasError(get(_util,_cust, ERROR));
        setAliasVoid(get(_util,_cust, VOID));
        setAliasGetCause(get(_util,_cust, GET_CAUSE));
        setAliasBadIndex(get(_util,_cust, BAD_INDEX));
        setAliasEnums(get(_util,_cust, ENUMS));
        setAliasIterable(get(_util,_cust, ITERABLE));
        setAliasNbFormat(get(_util,_cust, NB_FORMAT));
        setAliasSof(get(_util,_cust, SOF));
        setAliasParseFloat(get(_util,_cust, PARSE_FLOAT));
        setAliasToStringMethod(get(_util,_cust, TO_STRING_METHOD));
        setAliasParseLongOrNull(get(_util,_cust, PARSE_LONG_OR_NULL));
        setAliasParseShortOrNull(get(_util,_cust, PARSE_SHORT_OR_NULL));
        setAliasParseFloatOrNull(get(_util,_cust, PARSE_FLOAT_OR_NULL));
        setAliasParseDoubleOrNull(get(_util,_cust, PARSE_DOUBLE_OR_NULL));
        setAliasByteValue(get(_util,_cust, BYTE_VALUE));
        setAliasCharValue(get(_util,_cust, CHAR_VALUE));
        setAliasPrimBoolean(get(_util,_cust, PRIM_BOOLEAN));
        setAliasParseIntOrNull(get(_util,_cust, PARSE_INT_OR_NULL));
        setAliasPrimShort(get(_util,_cust, PRIM_SHORT));
        setAliasParseBoolean(get(_util,_cust, PARSE_BOOLEAN));
        setAliasParseShort(get(_util,_cust, PARSE_SHORT));
        setAliasPrimFloat(get(_util,_cust, PRIM_FLOAT));
        setAliasCompareTo(get(_util,_cust, COMPARE_TO));
        setAliasCharacter(get(_util,_cust, CHARACTER));
        setAliasParseLong(get(_util,_cust, PARSE_LONG));
        setAliasValueOfMethod(get(_util,_cust, VALUE_OF_METHOD));
        setAliasPrimInteger(get(_util,_cust, PRIM_INTEGER));
        setAliasParseByteOrNull(get(_util,_cust, PARSE_BYTE_OR_NULL));
        setAliasPrimDouble(get(_util,_cust, PRIM_DOUBLE));
        setAliasBooleanValue(get(_util,_cust, BOOLEAN_VALUE));
        setAliasShortValue(get(_util,_cust, SHORT_VALUE));
        setAliasParseDouble(get(_util,_cust, PARSE_DOUBLE));
        setAliasIllegalArg(get(_util,_cust, ILLEGAL_ARG));
        setAliasParseByte(get(_util,_cust, PARSE_BYTE));
        setAliasIsUpperCase(get(_util,_cust, IS_UPPER_CASE));
        setAliasIsWordChar(get(_util,_cust, IS_WORD_CHAR));
        setAliasIsWhitespace(get(_util,_cust, IS_WHITESPACE));
        setAliasIsLetterOrDigit(get(_util,_cust, IS_LETTER_OR_DIGIT));
        setAliasFloatValue(get(_util,_cust, FLOAT_VALUE));
        setAliasDoubleValue(get(_util,_cust, DOUBLE_VALUE));
        setAliasLongValue(get(_util,_cust, LONG_VALUE));
        setAliasIsLowerCase(get(_util,_cust, IS_LOWER_CASE));
        setAliasIndexOf(get(_util,_cust, INDEX_OF));
        setAliasString(get(_util,_cust, STRING));
        setAliasIsEmpty(get(_util,_cust, IS_EMPTY));
        setAliasTrim(get(_util,_cust, TRIM));
        setAliasGetBytes(get(_util,_cust, GET_BYTES));
        setAliasForDigit(get(_util,_cust, FOR_DIGIT));
        setAliasIsSpace(get(_util,_cust, IS_SPACE));
        setAliasGetType(get(_util,_cust, GET_TYPE));
        setAliasContains(get(_util,_cust, CONTAINS));
        setAliasReplace(get(_util,_cust, REPLACE));
        setAliasReplaceString(get(_util,_cust, REPLACE_STRING));
        setAliasFormat(get(_util,_cust, FORMAT));
        setAliasEndsWith(get(_util,_cust, ENDS_WITH));
        setAliasCapacity(get(_util,_cust, CAPACITY));
        setAliasSplit(get(_util,_cust, SPLIT));
        setAliasAppend(get(_util,_cust, APPEND));
        setAliasIsLetter(get(_util,_cust, IS_LETTER));
        setAliasIsNan(get(_util,_cust, IS_NAN));
        setAliasLength(get(_util,_cust, LENGTH));
        setAliasCharAt(get(_util,_cust, CHAR_AT));
        setAliasClone(get(_util,_cust, CLONE));
        setAliasName(get(_util,_cust, NAME));
        setAliasCall(get(_util,_cust, CALL));
        setAliasMetaInfo(get(_util,_cust, META_INFO));
        setAliasInstance(get(_util,_cust, INSTANCE));
        setAliasSame(get(_util,_cust, SAME));
        setAliasMod(get(_util,_cust, MOD));
        setAliasReverse(get(_util,_cust, REVERSE));
        setAliasInsert(get(_util,_cust, INSERT));
        setAliasAbs(get(_util,_cust, ABS));
        setAliasHasNext(get(_util,_cust, HAS_NEXT));
        setAliasPairType(get(_util,_cust, PAIR_TYPE));
        setAliasQuot(get(_util,_cust, QUOT));
        setAliasNext(get(_util,_cust, NEXT));
        setAliasOrdinal(get(_util,_cust, ORDINAL));
        setAliasGetFirst(get(_util,_cust, GET_FIRST));
        setAliasFct(get(_util,_cust, FCT));
        setAliasDelete(get(_util,_cust, DELETE));
        setAliasClear(get(_util,_cust, CLEAR));
        setAliasNextPair(get(_util,_cust, NEXT_PAIR));
        setAliasSubstring(get(_util,_cust, SUBSTRING));
        setAliasSetCharAt(get(_util,_cust, SET_CHAR_AT));
        setAliasEqualsIgnoreCase(get(_util,_cust, EQUALS_IGNORE_CASE));
        setAliasIteratorTableType(get(_util,_cust, ITERATOR_TABLE_TYPE));
        setAliasDeleteCharAt(get(_util,_cust, DELETE_CHAR_AT));
        setAliasStartsWith(get(_util,_cust, STARTS_WITH));
        setAliasLastIndexOf(get(_util,_cust, LAST_INDEX_OF));
        setAliasRegionMatches(get(_util,_cust, REGION_MATCHES));
        setAliasIteratorTable(get(_util,_cust, ITERATOR_TABLE));
        setAliasIterableTable(get(_util,_cust, ITERABLE_TABLE));
        setAliasToLowerCase(get(_util,_cust, TO_LOWER_CASE));
        setAliasToLowerCaseChar(get(_util,_cust, TO_LOWER_CASE_CHAR));
        setAliasStringBuilder(get(_util,_cust, STRING_BUILDER));
        setAliasToUpperCase(get(_util,_cust, TO_UPPER_CASE));
        setAliasToUpperCaseChar(get(_util,_cust, TO_UPPER_CASE_CHAR));
        setAliasEnsureCapacity(get(_util,_cust, ENSURE_CAPACITY));
        setAliasSetLength(get(_util,_cust, SET_LENGTH));
        setAliasTrimToSize(get(_util,_cust, TRIM_TO_SIZE));
        setAliasHasNextPair(get(_util,_cust, HAS_NEXT_PAIR));
        setAliasReplacement(get(_util,_cust, REPLACEMENT));
        setAliasGetOldString(get(_util,_cust, GET_OLD_STRING));
        setAliasGetNewString(get(_util,_cust, GET_NEW_STRING));
        setAliasGetSecond(get(_util,_cust, GET_SECOND));
        setAliasSubSequence(get(_util,_cust, SUB_SEQUENCE));
        setAliasCompareToIgnoreCase(get(_util,_cust, COMPARE_TO_IGNORE_CASE));
        setAliasToCharArray(get(_util,_cust, TO_CHAR_ARRAY));
        setAliasReplaceMultiple(get(_util,_cust, REPLACE_MULTIPLE));
        setAliasSplitStrings(get(_util,_cust, SPLIT_STRINGS));
        setAliasSplitChars(get(_util,_cust, SPLIT_CHARS));
        setAliasIsInfinite(get(_util,_cust, IS_INFINITE));
        setAliasGetDirectionality(get(_util,_cust, GET_DIRECTIONALITY));
        setAliasGetCharType(get(_util,_cust, GET_CHAR_TYPE));
        setAliasIterableTableVarSecond(get(_util,_cust, ITERABLE_TABLE_VAR_SECOND));
        setAliasGetString(get(_util,_cust, GET_STRING));
        setAliasGetAnnotationsParameters(get(_util,_cust, GET_ANNOTATIONS_PARAMETERS));
        setAliasReadResourcesNames(get(_util,_cust, READ_RESOURCES_NAMES));
        setAliasReadResourcesNamesLength(get(_util,_cust, READ_RESOURCES_NAMES_LENGTH));
        setAliasInvokeTarget(get(_util,_cust, INVOKE_TARGET));
        setAliasGetAnnotations(get(_util,_cust, GET_ANNOTATIONS));
        setAliasGetVariableOwner(get(_util,_cust, GET_VARIABLE_OWNER));
        setAliasReadResources(get(_util,_cust, READ_RESOURCES));
        setAliasReadResourcesIndex(get(_util,_cust, READ_RESOURCES_INDEX));
        setAliasResources(get(_util,_cust, RESOURCES));
        setAliasClassNotFoundError(get(_util,_cust, CLASS_NOT_FOUND_ERROR));
        setAliasEnumValues(get(_util,_cust, ENUM_VALUES));
        setAliasEnumPredValueOf(get(_util,_cust, ENUM_PRED_VALUE_OF));
        setAliasIteratorTypeVar(get(_util,_cust, ITERATOR_TYPE_VAR));
        setAliasClassType(get(_util,_cust, CLASS_TYPE));
        setAliasIterableTableVarFirst(get(_util,_cust, ITERABLE_TABLE_VAR_FIRST));
        setAliasPairTypeVarFirst(get(_util,_cust, PAIR_TYPE_VAR_FIRST));
        setAliasErrorInitClass(get(_util,_cust, ERROR_INIT_CLASS));
        setAliasAnnotationType(get(_util,_cust, ANNOTATION_TYPE));
        setAliasGetGenericVariableOwner(get(_util,_cust, GET_GENERIC_VARIABLE_OWNER));
        setAliasEnumParamVar(get(_util,_cust, ENUM_PARAM_VAR));
        setAliasSeedGenerator(get(_util,_cust, SEED_GENERATOR));
        setAliasSeedDoubleGenerator(get(_util,_cust, SEED_DOUBLE_GENERATOR));
        setAliasSeedGet(get(_util,_cust, SEED_GET));
        setAliasPairTypeVarSecond(get(_util,_cust, PAIR_TYPE_VAR_SECOND));
        setAliasAnnotated(get(_util,_cust, ANNOTATED));
        setAliasIterableVar(get(_util,_cust, ITERABLE_VAR));
        setAliasGetDefaultValue(get(_util,_cust, GET_DEFAULT_VALUE));
        setAliasMakeGeneric(get(_util,_cust, MAKE_GENERIC));
        setAliasGetAllClasses(get(_util,_cust, GET_ALL_CLASSES));
        setAliasGetOperators(get(_util,_cust, GET_OPERATORS));
        setAliasGetDeclaredExplicits(get(_util,_cust, GET_DECLARED_EXPLICITS));
        setAliasGetDeclaredImplicits(get(_util,_cust, GET_DECLARED_IMPLICITS));
        setAliasGetDeclaredTrueOperators(get(_util,_cust, GET_DECLARED_TRUE_OPERATORS));
        setAliasGetDeclaredFalseOperators(get(_util,_cust, GET_DECLARED_FALSE_OPERATORS));
        setAliasGetDeclaredMethods(get(_util,_cust, GET_DECLARED_METHODS));
        setAliasGetDeclaredStaticMethods(get(_util,_cust, GET_DECLARED_STATIC_METHODS));
        setAliasGetDeclaredConstructors(get(_util,_cust, GET_DECLARED_CONSTRUCTORS));
        setAliasGetDeclaredFields(get(_util,_cust, GET_DECLARED_FIELDS));
        setAliasGetDeclaredAnonymousTypes(get(_util,_cust, GET_DECLARED_ANONYMOUS_TYPES));
        setAliasGetDeclaredAnonymousLambda(get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA));
        setAliasGetDeclaredAnonymousLambdaLocalVars(get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS));
        setAliasGetDeclaredAnonymousLambdaLoopVars(get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS));
        setAliasGetDeclaredBlocks(get(_util,_cust, GET_DECLARED_BLOCKS));
        setAliasGetDeclaredLocalTypes(get(_util,_cust, GET_DECLARED_LOCAL_TYPES));
        setAliasField(get(_util,_cust, FIELD));
        setAliasIsNormal(get(_util,_cust, IS_NORMAL));
        setAliasSameRef(get(_util,_cust, SAME_REF));
        setAliasIsPublic(get(_util,_cust, IS_PUBLIC));
        setAliasIsArray(get(_util,_cust, IS_ARRAY));
        setAliasArrayGet(get(_util,_cust, ARRAY_GET));
        setAliasMethod(get(_util,_cust, METHOD));
        setAliasGetField(get(_util,_cust, GET_FIELD));
        setAliasInvoke(get(_util,_cust, INVOKE));
        setAliasIsEnum(get(_util,_cust, IS_ENUM));
        setAliasInit(get(_util,_cust, INIT));
        setAliasForName(get(_util,_cust, FOR_NAME));
        setAliasIsStatic(get(_util,_cust, IS_STATIC));
        setAliasIsStaticCall(get(_util,_cust, IS_STATIC_CALL));
        setAliasIsInstanceMethod(get(_util,_cust, IS_INSTANCE_METHOD));
        setAliasGetName(get(_util,_cust, GET_NAME));
        setAliasIsClass(get(_util,_cust, IS_CLASS));
        setAliasSetField(get(_util,_cust, SET_FIELD));
        setAliasGetClass(get(_util,_cust, GET_CLASS));
        setAliasIsFinal(get(_util,_cust, IS_FINAL));
        setAliasArraySet(get(_util,_cust, ARRAY_SET));
        setAliasXor(get(_util,_cust, XOR));
        setAliasMult(get(_util,_cust, MULT));
        setAliasRandom(get(_util,_cust, RANDOM));
        setAliasSeed(get(_util,_cust, SEED));
        setAliasNegBin(get(_util,_cust, NEG_BIN));
        setAliasMinus(get(_util,_cust, MINUS));
        setAliasEnumName(get(_util,_cust, ENUM_NAME));
        setAliasBinMod(get(_util,_cust, BIN_MOD));
        setAliasLt(get(_util,_cust, LT));
        setAliasGt(get(_util,_cust, GT));
        setAliasLe(get(_util,_cust, LE));
        setAliasGe(get(_util,_cust, GE));
        setAliasAnd(get(_util,_cust, AND));
        setAliasOr(get(_util,_cust, OR));
        setAliasPlus(get(_util,_cust, PLUS));
        setAliasBinQuot(get(_util,_cust, BIN_QUOT));
        setAliasNeg(get(_util,_cust, NEG));
        setAliasRotateLeft(get(_util,_cust, ROTATE_LEFT));
        setAliasEnumOrdinal(get(_util,_cust, ENUM_ORDINAL));
        setAliasShiftRight(get(_util,_cust, SHIFT_RIGHT));
        setAliasCurrentFullStack(get(_util,_cust, CURRENT_FULL_STACK));
        setAliasGetBounds(get(_util,_cust, GET_BOUNDS));
        setAliasGetDeclaringClass(get(_util,_cust, GET_DECLARING_CLASS));
        setAliasStackTraceElement(get(_util,_cust, STACK_TRACE_ELEMENT));
        setAliasEnumValueOf(get(_util,_cust, ENUM_VALUE_OF));
        setAliasArrayNewInstance(get(_util,_cust, ARRAY_NEW_INSTANCE));
        setAliasGetEnumConstants(get(_util,_cust, GET_ENUM_CONSTANTS));
        setAliasArrayGetLength(get(_util,_cust, ARRAY_GET_LENGTH));
        setAliasRotateRight(get(_util,_cust, ROTATE_RIGHT));
        setAliasGetGenericBounds(get(_util,_cust, GET_GENERIC_BOUNDS));
        setAliasBitShiftLeft(get(_util,_cust, BIT_SHIFT_LEFT));
        setAliasShiftLeft(get(_util,_cust, SHIFT_LEFT));
        setAliasDefaultInstance(get(_util,_cust, DEFAULT_INSTANCE));
        setAliasCurrentStack(get(_util,_cust, CURRENT_STACK));
        setAliasBitShiftRight(get(_util,_cust, BIT_SHIFT_RIGHT));
        setAliasGetParameterNames(get(_util,_cust, GET_PARAMETER_NAMES));
        setAliasGetPrettyName(get(_util,_cust, GET_PRETTY_NAME));
        setAliasGetPrettySingleName(get(_util,_cust, GET_PRETTY_SINGLE_NAME));
        setAliasGetUpperBounds(get(_util,_cust, GET_UPPER_BOUNDS));
        setAliasGetParameterTypes(get(_util,_cust, GET_PARAMETER_TYPES));
        setAliasGetGenericReturnType(get(_util,_cust, GET_GENERIC_RETURN_TYPE));
        setAliasInvokeDirect(get(_util,_cust, INVOKE_DIRECT));
        setAliasStringUtil(get(_util,_cust, STRING_UTIL));
        setAliasGetLowerBounds(get(_util,_cust, GET_LOWER_BOUNDS));
        setAliasGetTypeParameters(get(_util,_cust, GET_TYPE_PARAMETERS));
        setAliasConstructor(get(_util,_cust, CONSTRUCTOR));
        setAliasSetParent(get(_util,_cust, SET_PARENT));
        setAliasNewInstance(get(_util,_cust, NEW_INSTANCE));
        setAliasGetEnclosingType(get(_util,_cust, GET_ENCLOSING_TYPE));
        setAliasGetInterfaces(get(_util,_cust, GET_INTERFACES));
        setAliasObjectsUtil(get(_util,_cust, OBJECTS_UTIL));
        setAliasGetDeclaredClasses(get(_util,_cust, GET_DECLARED_CLASSES));
        setAliasGetSuperClass(get(_util,_cust, GET_SUPER_CLASS));
        setAliasGetParent(get(_util,_cust, GET_PARENT));
        setAliasGetComponentType(get(_util,_cust, GET_COMPONENT_TYPE));
        setAliasGetFileName(get(_util,_cust, GET_FILE_NAME));
        setAliasGetGenericSuperClass(get(_util,_cust, GET_GENERIC_SUPER_CLASS));
        setAliasGetGenericInterfaces(get(_util,_cust, GET_GENERIC_INTERFACES));
        setAliasIsAbstract(get(_util,_cust, IS_ABSTRACT));
        setAliasMakeArray(get(_util,_cust, MAKE_ARRAY));
        setAliasIsInterface(get(_util,_cust, IS_INTERFACE));
        setAliasMakeWildCard(get(_util,_cust, MAKE_WILD_CARD));
        setAliasIsTypeVariable(get(_util,_cust, IS_TYPE_VARIABLE));
        setAliasIsPrivate(get(_util,_cust, IS_PRIVATE));
        setAliasIsVarargs(get(_util,_cust, IS_VARARGS));
        setAliasIsInstance(get(_util,_cust, IS_INSTANCE));
        setAliasGetReturnType(get(_util,_cust, GET_RETURN_TYPE));
        setAliasGetActualTypeArguments(get(_util,_cust, GET_ACTUAL_TYPE_ARGUMENTS));
        setAliasIsProtected(get(_util,_cust, IS_PROTECTED));
        setAliasIsPrimitive(get(_util,_cust, IS_PRIMITIVE));
        setAliasIsWildCard(get(_util,_cust, IS_WILD_CARD));
        setAliasIsAnnotation(get(_util,_cust, IS_ANNOTATION));
        setAliasGetGenericType(get(_util,_cust, GET_GENERIC_TYPE));
        setAliasIsAssignableFrom(get(_util,_cust, IS_ASSIGNABLE_FROM));
        setAliasIsVariable(get(_util,_cust, IS_VARIABLE));
        setAliasIsPackage(get(_util,_cust, IS_PACKAGE));
        setAliasAtomicInteger(get(_util,_cust, ATOMIC_INTEGER));
        setAliasAtomicBoolean(get(_util,_cust, ATOMIC_BOOLEAN));
        setAliasSetAtomic(get(_util,_cust, SET_ATOMIC));
        setAliasAtomicLong(get(_util,_cust, ATOMIC_LONG));
        setAliasGetAtomic(get(_util,_cust, GET_ATOMIC));
        setAliasLazySetAtomic(get(_util,_cust, LAZY_SET_ATOMIC));
        setAliasCompareAndSetAtomic(get(_util,_cust, COMPARE_AND_SET_ATOMIC));
        setAliasGetAndAddAtomic(get(_util,_cust, GET_AND_ADD_ATOMIC));
        setAliasAddAndGetAtomic(get(_util,_cust, ADD_AND_GET_ATOMIC));
        setAliasGetAndIncrementAtomic(get(_util,_cust, GET_AND_INCREMENT_ATOMIC));
        setAliasIncrementAndGetAtomic(get(_util,_cust, INCREMENT_AND_GET_ATOMIC));
        setAliasGetAndDecrementAtomic(get(_util,_cust, GET_AND_DECREMENT_ATOMIC));
        setAliasDecrementAndGetAtomic(get(_util,_cust, DECREMENT_AND_GET_ATOMIC));
        setAliasGetAndSetAtomic(get(_util,_cust, GET_AND_SET_ATOMIC));
        setAliasLengthItrTa(get(_util,_cust, LENGTH_ITR_TA));
        setAliasPairVarFirst(get(_util,_cust, PAIR_VAR_FIRST));
        setAliasIndexItrLi(get(_util,_cust, INDEX_ITR_LI));
        setAliasListIterTable(get(_util,_cust, LIST_ITER_TABLE));
        setAliasTableVarSecond(get(_util,_cust, TABLE_VAR_SECOND));
        setAliasIterTaVarFirst(get(_util,_cust, ITER_TA_VAR_FIRST));
        setAliasCustIteratorVar(get(_util,_cust, CUST_ITERATOR_VAR));
        setAliasGetSecondTa(get(_util,_cust, GET_SECOND_TA));
        setAliasCustIterator(get(_util,_cust, CUST_ITERATOR));
        setAliasListClear(get(_util,_cust, LIST_CLEAR));
        setAliasGetFirstTa(get(_util,_cust, GET_FIRST_TA));
        setAliasSetSecondTa(get(_util,_cust, SET_SECOND_TA));
        setAliasCustIterTable(get(_util,_cust, CUST_ITER_TABLE));
        setAliasTableVarFirst(get(_util,_cust, TABLE_VAR_FIRST));
        setAliasSetSecond(get(_util,_cust, SET_SECOND));
        setAliasIndexItrTa(get(_util,_cust, INDEX_ITR_TA));
        setAliasIterTaVarSecond(get(_util,_cust, ITER_TA_VAR_SECOND));
        setAliasLengthItrLi(get(_util,_cust, LENGTH_ITR_LI));
        setAliasSetFirstTa(get(_util,_cust, SET_FIRST_TA));
        setAliasPairVarSecond(get(_util,_cust, PAIR_VAR_SECOND));
        setAliasLengthLi(get(_util,_cust, LENGTH_LI));
        setAliasCustPair(get(_util,_cust, CUST_PAIR));
        setAliasListTa(get(_util,_cust, LIST_TA));
        setAliasListItr(get(_util,_cust, LIST_ITR));
        setAliasRemoveLi(get(_util,_cust, REMOVE_LI));
        setAliasArrayLi(get(_util,_cust, ARRAY_LI));
        setAliasFirst(get(_util,_cust, FIRST));
        setAliasList(get(_util,_cust, LIST));
        setAliasSizeLi(get(_util,_cust, SIZE_LI));
        setAliasListVar(get(_util,_cust, LIST_VAR));
        setAliasSecond(get(_util,_cust, SECOND));
        setAliasAddLi(get(_util,_cust, ADD_LI));
        setAliasSetFirst(get(_util,_cust, SET_FIRST));
        setAliasTable(get(_util,_cust, TABLE));
        setAliasAddTa(get(_util,_cust, ADD_TA));
        setAliasRemoveTa(get(_util,_cust, REMOVE_TA));
        setAliasGetTa(get(_util,_cust, GET_TA));
        setAliasSizeTa(get(_util,_cust, SIZE_TA));
        getDisplayedStrings().setFalseString(get(_util,_cust, FALSE_STRING));
        getDisplayedStrings().setTrueString(get(_util,_cust, TRUE_STRING));
        getDisplayedStrings().setNullString(get(_util,_cust, NULL_STRING));
        getDisplayedStrings().setNullCoverString(get(_util,_cust, NULL_COVER_STRING));
        getDisplayedStrings().setNotNullCoverString(get(_util,_cust, NOT_NULL_COVER_STRING));
        getDisplayedStrings().setStaticCallString(get(_util,_cust, STATIC_CALL_STRING));
        getDisplayedStrings().setStaticString(get(_util,_cust, STATIC_STRING));
        getDisplayedStrings().setInfinity(get(_util,_cust, INFINITY));
        getDisplayedStrings().setNan(get(_util,_cust, NAN));
        getDisplayedStrings().setExponent(get(_util,_cust, EXPONENT));
        setAliasBean(get(_util,_cust, BEAN));
        setAliasMapKeys(get(_util,_cust, MAP_KEYS));
        setAliasMapValues(get(_util,_cust, MAP_VALUES));
        setAliasMapIndexOfEntry(get(_util,_cust, MAP_INDEX_OF_ENTRY));
        setAliasMapAddEntry(get(_util,_cust, MAP_ADD_ENTRY));
        setAliasMapGetValue(get(_util,_cust, MAP_GET_VALUE));
        setAliasMapFirstValue(get(_util,_cust, MAP_FIRST_VALUE));
        setAliasMapLastValue(get(_util,_cust, MAP_LAST_VALUE));
        setAliasMapSetValue(get(_util,_cust, MAP_SET_VALUE));
        setAliasMapPut(get(_util,_cust, MAP_PUT));
        setAliasMapContains(get(_util,_cust, MAP_CONTAINS));
        setAliasMapPutAll(get(_util,_cust, MAP_PUT_ALL));
        setAliasMapGetVal(get(_util,_cust, MAP_GET_VAL));
        setAliasMapRemoveKey(get(_util,_cust, MAP_REMOVE_KEY));
        setAliasMapGetKey(get(_util,_cust, MAP_GET_KEY));
        setAliasMapFirstKey(get(_util,_cust, MAP_FIRST_KEY));
        setAliasMapLastKey(get(_util,_cust, MAP_LAST_KEY));
        setAliasMapSetKey(get(_util,_cust, MAP_SET_KEY));
        setAliasMapSize(get(_util,_cust, MAP_SIZE));
        setAliasMapIsEmpty(get(_util,_cust, MAP_IS_EMPTY));
        setAliasMapClear(get(_util,_cust, MAP_CLEAR));
        setAliasValidator(get(_util,_cust, VALIDATOR));
        setAliasValidate(get(_util,_cust, VALIDATE));
        setAliasDataBaseField(get(_util,_cust, DATA_BASE_FIELD));
        setAliasForms(get(_util,_cust, FORMS));
        setAliasSetForms(get(_util,_cust, SET_FORMS));
        setAliasGetForms(get(_util,_cust, GET_FORMS));
        setAliasLanguage(get(_util,_cust, LANGUAGE));
        setAliasSetLanguage(get(_util,_cust, SET_LANGUAGE));
        setAliasGetLanguage(get(_util,_cust, GET_LANGUAGE));
        setAliasScope(get(_util,_cust, SCOPE));
        setAliasSetScope(get(_util,_cust, SET_SCOPE));
        setAliasGetScope(get(_util,_cust, GET_SCOPE));
        setAliasSetDataBase(get(_util,_cust, SET_DATA_BASE));
        setAliasGetDataBase(get(_util,_cust, GET_DATA_BASE));
        setAliasBeforeDisplaying(get(_util,_cust, BEFORE_DISPLAYING));
        setAliasStringMapObject(get(_util,_cust, STRING_MAP_OBJECT));
        setAliasMessage(get(_util,_cust, MESSAGE));
        setAliasNewMessage(get(_util,_cust, NEW_MESSAGE));
        setAliasMessageFormat(get(_util,_cust, MESSAGE_FORMAT));
        setAliasMessageGetArgs(get(_util,_cust, MESSAGE_GET_ARGS));
        setAliasMessageSetArgs(get(_util,_cust, MESSAGE_SET_ARGS));
        getPredefTypes().getParams().setAliasSeedGenerator0Get0(get(_util,_cust,AliasParamPredefinedTypes.SEED_GENERATOR_0_GET_0));
        getCharSeq().getParams().setAliasCharSequence0SubSequence0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_0));
        getCharSeq().getParams().setAliasCharSequence0SubSequence1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_1));
        getCharSeq().getParams().setAliasCharSequence0CharAt0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_CHAR_AT_0));
        getCharSeq().getParams().setAliasCharSequence0Substring0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_0));
        getCharSeq().getParams().setAliasCharSequence0Substring1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_1));
        getCharSeq().getParams().setAliasCharSequence1Substring0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SUBSTRING_0));
        getCharSeq().getParams().setAliasCharSequence0CompareTo0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_COMPARE_TO_0));
        getCharSeq().getParams().setAliasCharSequence0Contains0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_CONTAINS_0));
        getCharSeq().getParams().setAliasCharSequence0StartsWith0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_STARTS_WITH_0));
        getCharSeq().getParams().setAliasCharSequence1StartsWith0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_0));
        getCharSeq().getParams().setAliasCharSequence1StartsWith1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_1));
        getCharSeq().getParams().setAliasCharSequence0EndsWith0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_ENDS_WITH_0));
        getCharSeq().getParams().setAliasCharSequence0IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence1IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence1IndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_1));
        getCharSeq().getParams().setAliasCharSequence2IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_2_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence3IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence3IndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_1));
        getCharSeq().getParams().setAliasCharSequence0LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_LAST_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence1LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence1LastIndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_1));
        getCharSeq().getParams().setAliasCharSequence2LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_2_LAST_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence3LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence3LastIndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_1));
        getCharSeq().getParams().setAliasCharSequence0Format0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_FORMAT_0));
        getCharSeq().getParams().setAliasCharSequence0Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_0));
        getCharSeq().getParams().setAliasCharSequence1Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_0));
        getCharSeq().getParams().setAliasCharSequence1Split1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_1));
        getCharSeq().getParams().setAliasCharSequence2Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_2_SPLIT_0));
        getCharSeq().getParams().setAliasCharSequence3Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_0));
        getCharSeq().getParams().setAliasCharSequence3Split1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_1));
        getCharSeq().getParams().setAliasCharSequence0SplitStrings0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_STRINGS_0));
        getCharSeq().getParams().setAliasCharSequence1SplitStrings0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_0));
        getCharSeq().getParams().setAliasCharSequence1SplitStrings1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_1));
        getCharSeq().getParams().setAliasCharSequence0SplitChars0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_CHARS_0));
        getCharSeq().getParams().setAliasCharSequence0RegionMatches0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_0));
        getCharSeq().getParams().setAliasCharSequence0RegionMatches1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_1));
        getCharSeq().getParams().setAliasCharSequence0RegionMatches2(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_2));
        getCharSeq().getParams().setAliasCharSequence0RegionMatches3(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_3));
        getCharSeq().getParams().setAliasCharSequence0Equals0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_0));
        getCharSeq().getParams().setAliasCharSequence0Equals1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_1));
        getCharSeq().getParams().setAliasString0EqualsIgnoreCase0(get(_util,_cust, AliasParamCharSequence.STRING_0_EQUALS_IGNORE_CASE_0));
        getCharSeq().getParams().setAliasString0Compare0(get(_util,_cust, AliasParamCharSequence.STRING_0_COMPARE_0));
        getCharSeq().getParams().setAliasString0Compare1(get(_util,_cust, AliasParamCharSequence.STRING_0_COMPARE_1));
        getCharSeq().getParams().setAliasString0CompareToIgnoreCase0(get(_util,_cust, AliasParamCharSequence.STRING_0_COMPARE_TO_IGNORE_CASE_0));
        getCharSeq().getParams().setAliasString0ReplaceString0(get(_util,_cust, AliasParamCharSequence.STRING_0_REPLACE_STRING_0));
        getCharSeq().getParams().setAliasString0ReplaceString1(get(_util,_cust, AliasParamCharSequence.STRING_0_REPLACE_STRING_1));
        getCharSeq().getParams().setAliasString1ReplaceString0(get(_util,_cust, AliasParamCharSequence.STRING_1_REPLACE_STRING_0));
        getCharSeq().getParams().setAliasString1ReplaceString1(get(_util,_cust, AliasParamCharSequence.STRING_1_REPLACE_STRING_1));
        getCharSeq().getParams().setAliasString0ReplaceMultiple0(get(_util,_cust, AliasParamCharSequence.STRING_0_REPLACE_MULTIPLE_0));
        getCharSeq().getParams().setAliasString0RegionMatches0(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_0));
        getCharSeq().getParams().setAliasString0RegionMatches1(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_1));
        getCharSeq().getParams().setAliasString0RegionMatches2(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_2));
        getCharSeq().getParams().setAliasString0RegionMatches3(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_3));
        getCharSeq().getParams().setAliasString0RegionMatches4(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_4));
        getCharSeq().getParams().setAliasString0ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_0_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString1ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_1_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString2ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_2_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString3ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_3_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString4ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_4_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString5ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_5_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString6ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_6_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString7ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_7_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString8ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_8_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString9ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString9ValueOfMethod1(get(_util,_cust, AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_1));
        getCharSeq().getParams().setAliasString9ValueOfMethod2(get(_util,_cust, AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_2));
        getCharSeq().getParams().setAliasString0String0(get(_util,_cust, AliasParamCharSequence.STRING_0_STRING_0));
        getCharSeq().getParams().setAliasString1String0(get(_util,_cust, AliasParamCharSequence.STRING_1_STRING_0));
        getCharSeq().getParams().setAliasString1String1(get(_util,_cust, AliasParamCharSequence.STRING_1_STRING_1));
        getCharSeq().getParams().setAliasString1String2(get(_util,_cust, AliasParamCharSequence.STRING_1_STRING_2));
        getCharSeq().getParams().setAliasString2String0(get(_util,_cust, AliasParamCharSequence.STRING_2_STRING_0));
        getCharSeq().getParams().setAliasString3String0(get(_util,_cust, AliasParamCharSequence.STRING_3_STRING_0));
        getCharSeq().getParams().setAliasString3String1(get(_util,_cust, AliasParamCharSequence.STRING_3_STRING_1));
        getCharSeq().getParams().setAliasString3String2(get(_util,_cust, AliasParamCharSequence.STRING_3_STRING_2));
        getCharSeq().getParams().setAliasString4String0(get(_util,_cust, AliasParamCharSequence.STRING_4_STRING_0));
        getCharSeq().getParams().setAliasStringBuilder0Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder1Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder2Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder3Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_3_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder4Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_4_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder5Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_5_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder6Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_6_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder7Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_7_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder8Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_8_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder9Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder9Append1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_APPEND_1));
        getCharSeq().getParams().setAliasStringBuilder9Append2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_APPEND_2));
        getCharSeq().getParams().setAliasStringBuilder10Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_10_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder11Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder11Append1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_APPEND_1));
        getCharSeq().getParams().setAliasStringBuilder11Append2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_APPEND_2));
        getCharSeq().getParams().setAliasStringBuilder12Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_12_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder13Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder13Append1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_APPEND_1));
        getCharSeq().getParams().setAliasStringBuilder13Append2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_APPEND_2));
        getCharSeq().getParams().setAliasStringBuilder0Delete0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_DELETE_0));
        getCharSeq().getParams().setAliasStringBuilder0Delete1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_DELETE_1));
        getCharSeq().getParams().setAliasStringBuilder0DeleteCharAt0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_DELETE_CHAR_AT_0));
        getCharSeq().getParams().setAliasStringBuilder0Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder0Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder1Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder1Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder2Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder2Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder3Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_3_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder3Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_3_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder4Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_4_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder4Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_4_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder5Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_5_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder5Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_5_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder6Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_6_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder6Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_6_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder7Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_7_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder7Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_7_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder8Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_8_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder8Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_8_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder9Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder9Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder9Insert2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_2));
        getCharSeq().getParams().setAliasStringBuilder9Insert3(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_3));
        getCharSeq().getParams().setAliasStringBuilder10Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_10_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder10Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_10_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder11Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder11Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder11Insert2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_2));
        getCharSeq().getParams().setAliasStringBuilder11Insert3(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_3));
        getCharSeq().getParams().setAliasStringBuilder12Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_12_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder12Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_12_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder13Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder13Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder13Insert2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_2));
        getCharSeq().getParams().setAliasStringBuilder13Insert3(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_3));
        getCharSeq().getParams().setAliasStringBuilder0Replace0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_REPLACE_0));
        getCharSeq().getParams().setAliasStringBuilder0Replace1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_REPLACE_1));
        getCharSeq().getParams().setAliasStringBuilder0Replace2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_REPLACE_2));
        getCharSeq().getParams().setAliasStringBuilder0SetCharAt0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_0));
        getCharSeq().getParams().setAliasStringBuilder0SetCharAt1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_1));
        getCharSeq().getParams().setAliasStringBuilder0SetLength0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SET_LENGTH_0));
        getCharSeq().getParams().setAliasStringBuilder0EnsureCapacity0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_ENSURE_CAPACITY_0));
        getCharSeq().getParams().setAliasStringBuilder0Same0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SAME_0));
        getCharSeq().getParams().setAliasStringBuilder0Same1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SAME_1));
        getCharSeq().getParams().setAliasStringBuilder0StringBuilder0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_STRING_BUILDER_0));
        getCharSeq().getParams().setAliasStringBuilder1StringBuilder0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_STRING_BUILDER_0));
        getCharSeq().getParams().setAliasStringBuilder2StringBuilder0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_STRING_BUILDER_0));
        getCharSeq().getParams().setAliasReplacement0Replacement0(get(_util,_cust, AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_0));
        getCharSeq().getParams().setAliasReplacement0Replacement1(get(_util,_cust, AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_1));
        getCoreNames().getParams().setAliasError0CurrentStack0(get(_util,_cust, AliasParamCore.ERROR_0_CURRENT_STACK_0));
        getCoreNames().getParams().setAliasError0ToStringMethod0(get(_util,_cust, AliasParamCore.ERROR_0_TO_STRING_METHOD_0));
        getCoreNames().getParams().setAliasEnums0Name0(get(_util,_cust, AliasParamCore.ENUMS_0_NAME_0));
        getCoreNames().getParams().setAliasEnums0Ordinal0(get(_util,_cust, AliasParamCore.ENUMS_0_ORDINAL_0));
        getCoreNames().getParams().setAliasObjectsUtil0SameRef0(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SAME_REF_0));
        getCoreNames().getParams().setAliasObjectsUtil0SameRef1(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SAME_REF_1));
        getCoreNames().getParams().setAliasObjectsUtil0GetParent0(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_GET_PARENT_0));
        getCoreNames().getParams().setAliasObjectsUtil0SetParent0(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_0));
        getCoreNames().getParams().setAliasObjectsUtil0SetParent1(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_1));
        getCoreNames().getParams().setAliasStringUtil0ValueOfMethod0(get(_util,_cust, AliasParamCore.STRING_UTIL_0_VALUE_OF_METHOD_0));
        getCoreNames().getParams().setAliasResources0ReadResources0(get(_util,_cust, AliasParamCore.RESOURCES_0_READ_RESOURCES_0));
        getCoreNames().getParams().setAliasResources0ReadResourcesIndex0(get(_util,_cust, AliasParamCore.RESOURCES_0_READ_RESOURCES_INDEX_0));
        getMathRef().getParams().setAliasMath0Abs0(get(_util,_cust, AliasParamMath.MATH_0_ABS_0));
        getMathRef().getParams().setAliasMath1Abs0(get(_util,_cust, AliasParamMath.MATH_1_ABS_0));
        getMathRef().getParams().setAliasMath0Quot0(get(_util,_cust, AliasParamMath.MATH_0_QUOT_0));
        getMathRef().getParams().setAliasMath0Quot1(get(_util,_cust, AliasParamMath.MATH_0_QUOT_1));
        getMathRef().getParams().setAliasMath1Quot0(get(_util,_cust, AliasParamMath.MATH_1_QUOT_0));
        getMathRef().getParams().setAliasMath1Quot1(get(_util,_cust, AliasParamMath.MATH_1_QUOT_1));
        getMathRef().getParams().setAliasMath0Mod0(get(_util,_cust, AliasParamMath.MATH_0_MOD_0));
        getMathRef().getParams().setAliasMath0Mod1(get(_util,_cust, AliasParamMath.MATH_0_MOD_1));
        getMathRef().getParams().setAliasMath1Mod0(get(_util,_cust, AliasParamMath.MATH_1_MOD_0));
        getMathRef().getParams().setAliasMath1Mod1(get(_util,_cust, AliasParamMath.MATH_1_MOD_1));
        getMathRef().getParams().setAliasMath0Plus0(get(_util,_cust, AliasParamMath.MATH_0_PLUS_0));
        getMathRef().getParams().setAliasMath1Plus0(get(_util,_cust, AliasParamMath.MATH_1_PLUS_0));
        getMathRef().getParams().setAliasMath2Plus0(get(_util,_cust, AliasParamMath.MATH_2_PLUS_0));
        getMathRef().getParams().setAliasMath3Plus0(get(_util,_cust, AliasParamMath.MATH_3_PLUS_0));
        getMathRef().getParams().setAliasMath0Minus0(get(_util,_cust, AliasParamMath.MATH_0_MINUS_0));
        getMathRef().getParams().setAliasMath1Minus0(get(_util,_cust, AliasParamMath.MATH_1_MINUS_0));
        getMathRef().getParams().setAliasMath2Minus0(get(_util,_cust, AliasParamMath.MATH_2_MINUS_0));
        getMathRef().getParams().setAliasMath3Minus0(get(_util,_cust, AliasParamMath.MATH_3_MINUS_0));
        getMathRef().getParams().setAliasMath0Neg0(get(_util,_cust, AliasParamMath.MATH_0_NEG_0));
        getMathRef().getParams().setAliasMath0NegBin0(get(_util,_cust, AliasParamMath.MATH_0_NEG_BIN_0));
        getMathRef().getParams().setAliasMath1NegBin0(get(_util,_cust, AliasParamMath.MATH_1_NEG_BIN_0));
        getMathRef().getParams().setAliasMath4Plus0(get(_util,_cust, AliasParamMath.MATH_4_PLUS_0));
        getMathRef().getParams().setAliasMath4Plus1(get(_util,_cust, AliasParamMath.MATH_4_PLUS_1));
        getMathRef().getParams().setAliasMath5Plus0(get(_util,_cust, AliasParamMath.MATH_5_PLUS_0));
        getMathRef().getParams().setAliasMath5Plus1(get(_util,_cust, AliasParamMath.MATH_5_PLUS_1));
        getMathRef().getParams().setAliasMath6Plus0(get(_util,_cust, AliasParamMath.MATH_6_PLUS_0));
        getMathRef().getParams().setAliasMath6Plus1(get(_util,_cust, AliasParamMath.MATH_6_PLUS_1));
        getMathRef().getParams().setAliasMath7Plus0(get(_util,_cust, AliasParamMath.MATH_7_PLUS_0));
        getMathRef().getParams().setAliasMath7Plus1(get(_util,_cust, AliasParamMath.MATH_7_PLUS_1));
        getMathRef().getParams().setAliasMath4Minus0(get(_util,_cust, AliasParamMath.MATH_4_MINUS_0));
        getMathRef().getParams().setAliasMath4Minus1(get(_util,_cust, AliasParamMath.MATH_4_MINUS_1));
        getMathRef().getParams().setAliasMath5Minus0(get(_util,_cust, AliasParamMath.MATH_5_MINUS_0));
        getMathRef().getParams().setAliasMath5Minus1(get(_util,_cust, AliasParamMath.MATH_5_MINUS_1));
        getMathRef().getParams().setAliasMath6Minus0(get(_util,_cust, AliasParamMath.MATH_6_MINUS_0));
        getMathRef().getParams().setAliasMath6Minus1(get(_util,_cust, AliasParamMath.MATH_6_MINUS_1));
        getMathRef().getParams().setAliasMath7Minus0(get(_util,_cust, AliasParamMath.MATH_7_MINUS_0));
        getMathRef().getParams().setAliasMath7Minus1(get(_util,_cust, AliasParamMath.MATH_7_MINUS_1));
        getMathRef().getParams().setAliasMath0Mult0(get(_util,_cust, AliasParamMath.MATH_0_MULT_0));
        getMathRef().getParams().setAliasMath0Mult1(get(_util,_cust, AliasParamMath.MATH_0_MULT_1));
        getMathRef().getParams().setAliasMath1Mult0(get(_util,_cust, AliasParamMath.MATH_1_MULT_0));
        getMathRef().getParams().setAliasMath1Mult1(get(_util,_cust, AliasParamMath.MATH_1_MULT_1));
        getMathRef().getParams().setAliasMath2Mult0(get(_util,_cust, AliasParamMath.MATH_2_MULT_0));
        getMathRef().getParams().setAliasMath2Mult1(get(_util,_cust, AliasParamMath.MATH_2_MULT_1));
        getMathRef().getParams().setAliasMath3Mult0(get(_util,_cust, AliasParamMath.MATH_3_MULT_0));
        getMathRef().getParams().setAliasMath3Mult1(get(_util,_cust, AliasParamMath.MATH_3_MULT_1));
        getMathRef().getParams().setAliasMath0BinQuot0(get(_util,_cust, AliasParamMath.MATH_0_BIN_QUOT_0));
        getMathRef().getParams().setAliasMath0BinQuot1(get(_util,_cust, AliasParamMath.MATH_0_BIN_QUOT_1));
        getMathRef().getParams().setAliasMath1BinQuot0(get(_util,_cust, AliasParamMath.MATH_1_BIN_QUOT_0));
        getMathRef().getParams().setAliasMath1BinQuot1(get(_util,_cust, AliasParamMath.MATH_1_BIN_QUOT_1));
        getMathRef().getParams().setAliasMath2BinQuot0(get(_util,_cust, AliasParamMath.MATH_2_BIN_QUOT_0));
        getMathRef().getParams().setAliasMath2BinQuot1(get(_util,_cust, AliasParamMath.MATH_2_BIN_QUOT_1));
        getMathRef().getParams().setAliasMath3BinQuot0(get(_util,_cust, AliasParamMath.MATH_3_BIN_QUOT_0));
        getMathRef().getParams().setAliasMath3BinQuot1(get(_util,_cust, AliasParamMath.MATH_3_BIN_QUOT_1));
        getMathRef().getParams().setAliasMath0BinMod0(get(_util,_cust, AliasParamMath.MATH_0_BIN_MOD_0));
        getMathRef().getParams().setAliasMath0BinMod1(get(_util,_cust, AliasParamMath.MATH_0_BIN_MOD_1));
        getMathRef().getParams().setAliasMath1BinMod0(get(_util,_cust, AliasParamMath.MATH_1_BIN_MOD_0));
        getMathRef().getParams().setAliasMath1BinMod1(get(_util,_cust, AliasParamMath.MATH_1_BIN_MOD_1));
        getMathRef().getParams().setAliasMath2BinMod0(get(_util,_cust, AliasParamMath.MATH_2_BIN_MOD_0));
        getMathRef().getParams().setAliasMath2BinMod1(get(_util,_cust, AliasParamMath.MATH_2_BIN_MOD_1));
        getMathRef().getParams().setAliasMath3BinMod0(get(_util,_cust, AliasParamMath.MATH_3_BIN_MOD_0));
        getMathRef().getParams().setAliasMath3BinMod1(get(_util,_cust, AliasParamMath.MATH_3_BIN_MOD_1));
        getMathRef().getParams().setAliasMath0And0(get(_util,_cust, AliasParamMath.MATH_0_AND_0));
        getMathRef().getParams().setAliasMath0And1(get(_util,_cust, AliasParamMath.MATH_0_AND_1));
        getMathRef().getParams().setAliasMath1And0(get(_util,_cust, AliasParamMath.MATH_1_AND_0));
        getMathRef().getParams().setAliasMath1And1(get(_util,_cust, AliasParamMath.MATH_1_AND_1));
        getMathRef().getParams().setAliasMath2And0(get(_util,_cust, AliasParamMath.MATH_2_AND_0));
        getMathRef().getParams().setAliasMath2And1(get(_util,_cust, AliasParamMath.MATH_2_AND_1));
        getMathRef().getParams().setAliasMath0Or0(get(_util,_cust, AliasParamMath.MATH_0_OR_0));
        getMathRef().getParams().setAliasMath0Or1(get(_util,_cust, AliasParamMath.MATH_0_OR_1));
        getMathRef().getParams().setAliasMath1Or0(get(_util,_cust, AliasParamMath.MATH_1_OR_0));
        getMathRef().getParams().setAliasMath1Or1(get(_util,_cust, AliasParamMath.MATH_1_OR_1));
        getMathRef().getParams().setAliasMath2Or0(get(_util,_cust, AliasParamMath.MATH_2_OR_0));
        getMathRef().getParams().setAliasMath2Or1(get(_util,_cust, AliasParamMath.MATH_2_OR_1));
        getMathRef().getParams().setAliasMath0Xor0(get(_util,_cust, AliasParamMath.MATH_0_XOR_0));
        getMathRef().getParams().setAliasMath0Xor1(get(_util,_cust, AliasParamMath.MATH_0_XOR_1));
        getMathRef().getParams().setAliasMath1Xor0(get(_util,_cust, AliasParamMath.MATH_1_XOR_0));
        getMathRef().getParams().setAliasMath1Xor1(get(_util,_cust, AliasParamMath.MATH_1_XOR_1));
        getMathRef().getParams().setAliasMath2Xor0(get(_util,_cust, AliasParamMath.MATH_2_XOR_0));
        getMathRef().getParams().setAliasMath2Xor1(get(_util,_cust, AliasParamMath.MATH_2_XOR_1));
        getMathRef().getParams().setAliasMath0ShiftLeft0(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_LEFT_0));
        getMathRef().getParams().setAliasMath0ShiftLeft1(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_LEFT_1));
        getMathRef().getParams().setAliasMath1ShiftLeft0(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_LEFT_0));
        getMathRef().getParams().setAliasMath1ShiftLeft1(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_LEFT_1));
        getMathRef().getParams().setAliasMath0ShiftRight0(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_RIGHT_0));
        getMathRef().getParams().setAliasMath0ShiftRight1(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_RIGHT_1));
        getMathRef().getParams().setAliasMath1ShiftRight0(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_RIGHT_0));
        getMathRef().getParams().setAliasMath1ShiftRight1(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_RIGHT_1));
        getMathRef().getParams().setAliasMath0BitShiftLeft0(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_LEFT_0));
        getMathRef().getParams().setAliasMath0BitShiftLeft1(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_LEFT_1));
        getMathRef().getParams().setAliasMath1BitShiftLeft0(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_LEFT_0));
        getMathRef().getParams().setAliasMath1BitShiftLeft1(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_LEFT_1));
        getMathRef().getParams().setAliasMath0BitShiftRight0(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_0));
        getMathRef().getParams().setAliasMath0BitShiftRight1(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_1));
        getMathRef().getParams().setAliasMath1BitShiftRight0(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_0));
        getMathRef().getParams().setAliasMath1BitShiftRight1(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_1));
        getMathRef().getParams().setAliasMath0RotateLeft0(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_LEFT_0));
        getMathRef().getParams().setAliasMath0RotateLeft1(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_LEFT_1));
        getMathRef().getParams().setAliasMath1RotateLeft0(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_LEFT_0));
        getMathRef().getParams().setAliasMath1RotateLeft1(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_LEFT_1));
        getMathRef().getParams().setAliasMath0RotateRight0(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_RIGHT_0));
        getMathRef().getParams().setAliasMath0RotateRight1(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_RIGHT_1));
        getMathRef().getParams().setAliasMath1RotateRight0(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_RIGHT_0));
        getMathRef().getParams().setAliasMath1RotateRight1(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_RIGHT_1));
        getMathRef().getParams().setAliasMath0Le0(get(_util,_cust, AliasParamMath.MATH_0_LE_0));
        getMathRef().getParams().setAliasMath0Le1(get(_util,_cust, AliasParamMath.MATH_0_LE_1));
        getMathRef().getParams().setAliasMath0Ge0(get(_util,_cust, AliasParamMath.MATH_0_GE_0));
        getMathRef().getParams().setAliasMath0Ge1(get(_util,_cust, AliasParamMath.MATH_0_GE_1));
        getMathRef().getParams().setAliasMath0Lt0(get(_util,_cust, AliasParamMath.MATH_0_LT_0));
        getMathRef().getParams().setAliasMath0Lt1(get(_util,_cust, AliasParamMath.MATH_0_LT_1));
        getMathRef().getParams().setAliasMath0Gt0(get(_util,_cust, AliasParamMath.MATH_0_GT_0));
        getMathRef().getParams().setAliasMath0Gt1(get(_util,_cust, AliasParamMath.MATH_0_GT_1));
        getMathRef().getParams().setAliasMath1Le0(get(_util,_cust, AliasParamMath.MATH_1_LE_0));
        getMathRef().getParams().setAliasMath1Le1(get(_util,_cust, AliasParamMath.MATH_1_LE_1));
        getMathRef().getParams().setAliasMath1Ge0(get(_util,_cust, AliasParamMath.MATH_1_GE_0));
        getMathRef().getParams().setAliasMath1Ge1(get(_util,_cust, AliasParamMath.MATH_1_GE_1));
        getMathRef().getParams().setAliasMath1Lt0(get(_util,_cust, AliasParamMath.MATH_1_LT_0));
        getMathRef().getParams().setAliasMath1Lt1(get(_util,_cust, AliasParamMath.MATH_1_LT_1));
        getMathRef().getParams().setAliasMath1Gt0(get(_util,_cust, AliasParamMath.MATH_1_GT_0));
        getMathRef().getParams().setAliasMath1Gt1(get(_util,_cust, AliasParamMath.MATH_1_GT_1));
        getMathRef().getParams().setAliasMath0Random0(get(_util,_cust, AliasParamMath.MATH_0_RANDOM_0));
        getMathRef().getParams().setAliasMath0Seed0(get(_util,_cust, AliasParamMath.MATH_0_SEED_0));
        getNbAlias().getParams().setAliasBoolean0Compare0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_COMPARE_0));
        getNbAlias().getParams().setAliasBoolean0Compare1(get(_util,_cust, AliasParamNumber.BOOLEAN_0_COMPARE_1));
        getNbAlias().getParams().setAliasBoolean0CompareTo0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasBoolean0Equals0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_EQUALS_0));
        getNbAlias().getParams().setAliasBoolean0ParseBoolean0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_PARSE_BOOLEAN_0));
        getNbAlias().getParams().setAliasBoolean0ToStringMethod0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasBoolean0ValueOfMethod0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_VALUE_OF_METHOD_0));
        getNbAlias().getParams().setAliasBoolean1ValueOfMethod0(get(_util,_cust, AliasParamNumber.BOOLEAN_1_VALUE_OF_METHOD_0));
        getNbAlias().getParams().setAliasBoolean0Boolean0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_BOOLEAN_0));
        getNbAlias().getParams().setAliasBoolean1Boolean0(get(_util,_cust, AliasParamNumber.BOOLEAN_1_BOOLEAN_0));
        getNbAlias().getParams().setAliasByte0ToStringMethod0(get(_util,_cust, AliasParamNumber.BYTE_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasByte0ParseByte0(get(_util,_cust, AliasParamNumber.BYTE_0_PARSE_BYTE_0));
        getNbAlias().getParams().setAliasByte1ParseByte0(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_0));
        getNbAlias().getParams().setAliasByte1ParseByte1(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_1));
        getNbAlias().getParams().setAliasByte0CompareTo0(get(_util,_cust, AliasParamNumber.BYTE_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasByte0Compare0(get(_util,_cust, AliasParamNumber.BYTE_0_COMPARE_0));
        getNbAlias().getParams().setAliasByte0Compare1(get(_util,_cust, AliasParamNumber.BYTE_0_COMPARE_1));
        getNbAlias().getParams().setAliasByte0ParseByteOrNull0(get(_util,_cust, AliasParamNumber.BYTE_0_PARSE_BYTE_OR_NULL_0));
        getNbAlias().getParams().setAliasByte1ParseByteOrNull0(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_0));
        getNbAlias().getParams().setAliasByte1ParseByteOrNull1(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_1));
        getNbAlias().getParams().setAliasByte0Byte0(get(_util,_cust, AliasParamNumber.BYTE_0_BYTE_0));
        getNbAlias().getParams().setAliasByte1Byte0(get(_util,_cust, AliasParamNumber.BYTE_1_BYTE_0));
        getNbAlias().getParams().setAliasShort0ToStringMethod0(get(_util,_cust, AliasParamNumber.SHORT_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasShort0ParseShort0(get(_util,_cust, AliasParamNumber.SHORT_0_PARSE_SHORT_0));
        getNbAlias().getParams().setAliasShort1ParseShort0(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_0));
        getNbAlias().getParams().setAliasShort1ParseShort1(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_1));
        getNbAlias().getParams().setAliasShort0CompareTo0(get(_util,_cust, AliasParamNumber.SHORT_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasShort0Compare0(get(_util,_cust, AliasParamNumber.SHORT_0_COMPARE_0));
        getNbAlias().getParams().setAliasShort0Compare1(get(_util,_cust, AliasParamNumber.SHORT_0_COMPARE_1));
        getNbAlias().getParams().setAliasShort0ParseShortOrNull0(get(_util,_cust, AliasParamNumber.SHORT_0_PARSE_SHORT_OR_NULL_0));
        getNbAlias().getParams().setAliasShort1ParseShortOrNull0(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_0));
        getNbAlias().getParams().setAliasShort1ParseShortOrNull1(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_1));
        getNbAlias().getParams().setAliasShort0Short0(get(_util,_cust, AliasParamNumber.SHORT_0_SHORT_0));
        getNbAlias().getParams().setAliasShort1Short0(get(_util,_cust, AliasParamNumber.SHORT_1_SHORT_0));
        getNbAlias().getParams().setAliasInteger0ToStringMethod0(get(_util,_cust, AliasParamNumber.INTEGER_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasInteger0ParseInt0(get(_util,_cust, AliasParamNumber.INTEGER_0_PARSE_INT_0));
        getNbAlias().getParams().setAliasInteger1ParseInt0(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_0));
        getNbAlias().getParams().setAliasInteger1ParseInt1(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_1));
        getNbAlias().getParams().setAliasInteger0CompareTo0(get(_util,_cust, AliasParamNumber.INTEGER_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasInteger0Compare0(get(_util,_cust, AliasParamNumber.INTEGER_0_COMPARE_0));
        getNbAlias().getParams().setAliasInteger0Compare1(get(_util,_cust, AliasParamNumber.INTEGER_0_COMPARE_1));
        getNbAlias().getParams().setAliasInteger0ParseIntOrNull0(get(_util,_cust, AliasParamNumber.INTEGER_0_PARSE_INT_OR_NULL_0));
        getNbAlias().getParams().setAliasInteger1ParseIntOrNull0(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_0));
        getNbAlias().getParams().setAliasInteger1ParseIntOrNull1(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_1));
        getNbAlias().getParams().setAliasInteger0Integer0(get(_util,_cust, AliasParamNumber.INTEGER_0_INTEGER_0));
        getNbAlias().getParams().setAliasInteger1Integer0(get(_util,_cust, AliasParamNumber.INTEGER_1_INTEGER_0));
        getNbAlias().getParams().setAliasLong0ToStringMethod0(get(_util,_cust, AliasParamNumber.LONG_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasLong0ParseLong0(get(_util,_cust, AliasParamNumber.LONG_0_PARSE_LONG_0));
        getNbAlias().getParams().setAliasLong1ParseLong0(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_0));
        getNbAlias().getParams().setAliasLong1ParseLong1(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_1));
        getNbAlias().getParams().setAliasLong0CompareTo0(get(_util,_cust, AliasParamNumber.LONG_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasLong0Compare0(get(_util,_cust, AliasParamNumber.LONG_0_COMPARE_0));
        getNbAlias().getParams().setAliasLong0Compare1(get(_util,_cust, AliasParamNumber.LONG_0_COMPARE_1));
        getNbAlias().getParams().setAliasLong0ParseLongOrNull0(get(_util,_cust, AliasParamNumber.LONG_0_PARSE_LONG_OR_NULL_0));
        getNbAlias().getParams().setAliasLong1ParseLongOrNull0(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_0));
        getNbAlias().getParams().setAliasLong1ParseLongOrNull1(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_1));
        getNbAlias().getParams().setAliasLong0Long0(get(_util,_cust, AliasParamNumber.LONG_0_LONG_0));
        getNbAlias().getParams().setAliasLong1Long0(get(_util,_cust, AliasParamNumber.LONG_1_LONG_0));
        getNbAlias().getParams().setAliasFloat0ToStringMethod0(get(_util,_cust, AliasParamNumber.FLOAT_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasFloat0ParseFloat0(get(_util,_cust, AliasParamNumber.FLOAT_0_PARSE_FLOAT_0));
        getNbAlias().getParams().setAliasFloat0CompareTo0(get(_util,_cust, AliasParamNumber.FLOAT_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasFloat0Compare0(get(_util,_cust, AliasParamNumber.FLOAT_0_COMPARE_0));
        getNbAlias().getParams().setAliasFloat0Compare1(get(_util,_cust, AliasParamNumber.FLOAT_0_COMPARE_1));
        getNbAlias().getParams().setAliasFloat0ParseFloatOrNull0(get(_util,_cust, AliasParamNumber.FLOAT_0_PARSE_FLOAT_OR_NULL_0));
        getNbAlias().getParams().setAliasFloat0IsInfinite0(get(_util,_cust, AliasParamNumber.FLOAT_0_IS_INFINITE_0));
        getNbAlias().getParams().setAliasFloat0IsNan0(get(_util,_cust, AliasParamNumber.FLOAT_0_IS_NAN_0));
        getNbAlias().getParams().setAliasFloat0Float0(get(_util,_cust, AliasParamNumber.FLOAT_0_FLOAT_0));
        getNbAlias().getParams().setAliasFloat1Float0(get(_util,_cust, AliasParamNumber.FLOAT_1_FLOAT_0));
        getNbAlias().getParams().setAliasDouble0ToStringMethod0(get(_util,_cust, AliasParamNumber.DOUBLE_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasDouble0ParseDouble0(get(_util,_cust, AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_0));
        getNbAlias().getParams().setAliasDouble0CompareTo0(get(_util,_cust, AliasParamNumber.DOUBLE_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasDouble0Compare0(get(_util,_cust, AliasParamNumber.DOUBLE_0_COMPARE_0));
        getNbAlias().getParams().setAliasDouble0Compare1(get(_util,_cust, AliasParamNumber.DOUBLE_0_COMPARE_1));
        getNbAlias().getParams().setAliasDouble0ParseDoubleOrNull0(get(_util,_cust, AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_OR_NULL_0));
        getNbAlias().getParams().setAliasDouble0IsInfinite0(get(_util,_cust, AliasParamNumber.DOUBLE_0_IS_INFINITE_0));
        getNbAlias().getParams().setAliasDouble0IsNan0(get(_util,_cust, AliasParamNumber.DOUBLE_0_IS_NAN_0));
        getNbAlias().getParams().setAliasDouble0Double0(get(_util,_cust, AliasParamNumber.DOUBLE_0_DOUBLE_0));
        getNbAlias().getParams().setAliasDouble1Double0(get(_util,_cust, AliasParamNumber.DOUBLE_1_DOUBLE_0));
        getNbAlias().getParams().setAliasNumber0ToStringMethod0(get(_util,_cust, AliasParamNumber.NUMBER_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasNumber0Equals0(get(_util,_cust, AliasParamNumber.NUMBER_0_EQUALS_0));
        getNbAlias().getParams().setAliasNumber1Equals0(get(_util,_cust, AliasParamNumber.NUMBER_1_EQUALS_0));
        getNbAlias().getParams().setAliasNumber1Equals1(get(_util,_cust, AliasParamNumber.NUMBER_1_EQUALS_1));
        getNbAlias().getParams().setAliasNumber0CompareTo0(get(_util,_cust, AliasParamNumber.NUMBER_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasNumber0Compare0(get(_util,_cust, AliasParamNumber.NUMBER_0_COMPARE_0));
        getNbAlias().getParams().setAliasNumber0Compare1(get(_util,_cust, AliasParamNumber.NUMBER_0_COMPARE_1));
        getNbAlias().getParams().setAliasCharacter0CompareTo0(get(_util,_cust, AliasParamNumber.CHARACTER_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasCharacter0Compare0(get(_util,_cust, AliasParamNumber.CHARACTER_0_COMPARE_0));
        getNbAlias().getParams().setAliasCharacter0Compare1(get(_util,_cust, AliasParamNumber.CHARACTER_0_COMPARE_1));
        getNbAlias().getParams().setAliasCharacter0Digit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_DIGIT_0));
        getNbAlias().getParams().setAliasCharacter0Digit1(get(_util,_cust, AliasParamNumber.CHARACTER_0_DIGIT_1));
        getNbAlias().getParams().setAliasCharacter0ForDigit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_FOR_DIGIT_0));
        getNbAlias().getParams().setAliasCharacter0ForDigit1(get(_util,_cust, AliasParamNumber.CHARACTER_0_FOR_DIGIT_1));
        getNbAlias().getParams().setAliasCharacter0GetDirectionality0(get(_util,_cust, AliasParamNumber.CHARACTER_0_GET_DIRECTIONALITY_0));
        getNbAlias().getParams().setAliasCharacter0GetType0(get(_util,_cust, AliasParamNumber.CHARACTER_0_GET_TYPE_0));
        getNbAlias().getParams().setAliasCharacter0IsDigit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_DIGIT_0));
        getNbAlias().getParams().setAliasCharacter0IsLetter0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_LETTER_0));
        getNbAlias().getParams().setAliasCharacter0IsLetterOrDigit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_LETTER_OR_DIGIT_0));
        getNbAlias().getParams().setAliasCharacter0IsWordChar0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_WORD_CHAR_0));
        getNbAlias().getParams().setAliasCharacter0IsWhitespace0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_WHITESPACE_0));
        getNbAlias().getParams().setAliasCharacter0IsLowerCase0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_LOWER_CASE_0));
        getNbAlias().getParams().setAliasCharacter0IsUpperCase0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_UPPER_CASE_0));
        getNbAlias().getParams().setAliasCharacter0IsSpace0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_SPACE_0));
        getNbAlias().getParams().setAliasCharacter0ToLowerCaseChar0(get(_util,_cust, AliasParamNumber.CHARACTER_0_TO_LOWER_CASE_CHAR_0));
        getNbAlias().getParams().setAliasCharacter0ToUpperCaseChar0(get(_util,_cust, AliasParamNumber.CHARACTER_0_TO_UPPER_CASE_CHAR_0));
        getNbAlias().getParams().setAliasCharacter0ToStringMethod0(get(_util,_cust, AliasParamNumber.CHARACTER_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasCharacter0Character0(get(_util,_cust, AliasParamNumber.CHARACTER_0_CHARACTER_0));
        getReflect().getParams().setAliasFct0Call0(get(_util,_cust, AliasParamReflection.FCT_0_CALL_0));
        getReflect().getParams().setAliasClassType0GetClass0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_CLASS_0));
        getReflect().getParams().setAliasClassType0ForName0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_FOR_NAME_0));
        getReflect().getParams().setAliasClassType0ForName1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_FOR_NAME_1));
        getReflect().getParams().setAliasClassType1ForName0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_1_FOR_NAME_0));
        getReflect().getParams().setAliasClassType0IsInstance0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_IS_INSTANCE_0));
        getReflect().getParams().setAliasClassType0IsAssignableFrom0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0));
        getReflect().getParams().setAliasClassType0DefaultInstance0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_DEFAULT_INSTANCE_0));
        getReflect().getParams().setAliasClassType0EnumValueOf0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ENUM_VALUE_OF_0));
        getReflect().getParams().setAliasClassType0GetDeclaredConstructors0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredConstructors1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1));
        getReflect().getParams().setAliasClassType0GetDeclaredFields0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_FIELDS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1));
        getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2));
        getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods3(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3));
        getReflect().getParams().setAliasClassType0GetDeclaredMethods0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredMethods1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_1));
        getReflect().getParams().setAliasClassType0GetDeclaredMethods2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_2));
        getReflect().getParams().setAliasClassType0GetDeclaredMethods3(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_3));
        getReflect().getParams().setAliasClassType0GetDeclaredExplicits0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredImplicits0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredBlocks0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredBlocks1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_1));
        getReflect().getParams().setAliasClassType0MakeGeneric0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_MAKE_GENERIC_0));
        getReflect().getParams().setAliasClassType0MakeWildCard0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_MAKE_WILD_CARD_0));
        getReflect().getParams().setAliasClassType0GetOperators0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_0));
        getReflect().getParams().setAliasClassType0GetOperators1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_1));
        getReflect().getParams().setAliasClassType0GetOperators2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_2));
        getReflect().getParams().setAliasClassType0ArrayNewInstance0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0));
        getReflect().getParams().setAliasClassType0ArrayGetLength0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_LENGTH_0));
        getReflect().getParams().setAliasClassType0ArrayGet0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_0));
        getReflect().getParams().setAliasClassType0ArrayGet1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_1));
        getReflect().getParams().setAliasClassType0ArraySet0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_0));
        getReflect().getParams().setAliasClassType0ArraySet1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_1));
        getReflect().getParams().setAliasClassType0ArraySet2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_2));
        getReflect().getParams().setAliasConstructor0NewInstance0(get(_util,_cust, AliasParamReflection.CONSTRUCTOR_0_NEW_INSTANCE_0));
        getReflect().getParams().setAliasField0GetField0(get(_util,_cust, AliasParamReflection.FIELD_0_GET_FIELD_0));
        getReflect().getParams().setAliasField0SetField0(get(_util,_cust, AliasParamReflection.FIELD_0_SET_FIELD_0));
        getReflect().getParams().setAliasField0SetField1(get(_util,_cust, AliasParamReflection.FIELD_0_SET_FIELD_1));
        getReflect().getParams().setAliasMethod0Invoke0(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_0));
        getReflect().getParams().setAliasMethod0Invoke1(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_1));
        getReflect().getParams().setAliasMethod0InvokeDirect0(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_DIRECT_0));
        getReflect().getParams().setAliasMethod0InvokeDirect1(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_DIRECT_1));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars1(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars2(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2));
        getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLocalVars1(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1));
        getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLocalVars1(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1));
        getReflect().getParams().setAliasMethod3GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars1(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars2(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2));
        getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLoopVars1(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1));
        getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLoopVars1(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1));
        getReflect().getParams().setAliasMethod3GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        getReflect().getParams().setAliasAnnotationType0GetString0(get(_util,_cust, AliasParamReflection.ANNOTATION_TYPE_0_GET_STRING_0));
        getReflect().getParams().setAliasAnnotated0GetAnnotations0(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_0));
        getReflect().getParams().setAliasAnnotated0GetAnnotationsParameters0(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0));
        getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda0(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0));
        getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda1(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1));
        getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda2(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2));
        getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda3(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3));

    }
    private void keyWord(KeyWords _kw,String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"keywords");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        keyWord(_kw,util_,_cust);
    }
    private void keyWord(KeyWords _kw,StringMap<String> _util,StringMap<String> _cust) {
        _kw.setKeyWordContinue(get(_util,_cust, KeyWords.CONTINUE));
        _kw.setKeyWordInstanceof(get(_util,_cust, KeyWords.INSTANCEOF));
        _kw.setKeyWordInterface(get(_util,_cust, KeyWords.INTERFACE));
        _kw.setKeyWordAbstract(get(_util,_cust, KeyWords.ABSTRACT));
        _kw.setKeyWordElseif(get(_util,_cust, KeyWords.ELSEIF));
        _kw.setKeyWordCast(get(_util,_cust, KeyWords.CAST));
        _kw.setKeyWordExplicit(get(_util,_cust, KeyWords.EXPLICIT));
        _kw.setKeyWordFor(get(_util,_cust, KeyWords.FOR));
        _kw.setKeyWordVar(get(_util,_cust, KeyWords.VAR));
        _kw.setKeyWordStatic(get(_util,_cust, KeyWords.STATIC));
        _kw.setKeyWordStaticCall(get(_util,_cust, KeyWords.STATIC_CALL));
        _kw.setKeyWordNull(get(_util,_cust, KeyWords.NULL));
        _kw.setKeyWordClass(get(_util,_cust, KeyWords.CLASS));
        _kw.setKeyWordFalse(get(_util,_cust, KeyWords.FALSE));
        _kw.setKeyWordFinal(get(_util,_cust, KeyWords.FINAL));
        _kw.setKeyWordBreak(get(_util,_cust, KeyWords.BREAK));
        _kw.setKeyWordIf(get(_util,_cust, KeyWords.IF));
        _kw.setKeyWordNew(get(_util,_cust, KeyWords.NEW));
        _kw.setKeyWordWhile(get(_util,_cust, KeyWords.WHILE));
        _kw.setKeyWordReturn(get(_util,_cust, KeyWords.RETURN));
        _kw.setKeyWordTrue(get(_util,_cust, KeyWords.TRUE));
        _kw.setKeyWordPublic(get(_util,_cust, KeyWords.PUBLIC));
        _kw.setKeyWordPrivate(get(_util,_cust, KeyWords.PRIVATE));
        _kw.setKeyWordAnnotation(get(_util,_cust, KeyWords.ANNOTATION));
        _kw.setKeyWordToString(get(_util,_cust, KeyWords.TO_STRING));
        _kw.setKeyWordNbSufBytePrim(get(_util,_cust, KeyWords.NB_SUF_BYTE_PRIM));
        _kw.setKeyWordNbSufByte(get(_util,_cust, KeyWords.NB_SUF_BYTE));
        _kw.setKeyWordNbSufShortPrim(get(_util,_cust, KeyWords.NB_SUF_SHORT_PRIM));
        _kw.setKeyWordNbSufShort(get(_util,_cust, KeyWords.NB_SUF_SHORT));
        _kw.setKeyWordNbSufCharacterPrim(get(_util,_cust, KeyWords.NB_SUF_CHARACTER_PRIM));
        _kw.setKeyWordNbSufCharacter(get(_util,_cust, KeyWords.NB_SUF_CHARACTER));
        _kw.setKeyWordNbSufIntegerPrim(get(_util,_cust, KeyWords.NB_SUF_INTEGER_PRIM));
        _kw.setKeyWordNbSufInteger(get(_util,_cust, KeyWords.NB_SUF_INTEGER));
        _kw.setKeyWordNbSufLongPrim(get(_util,_cust, KeyWords.NB_SUF_LONG_PRIM));
        _kw.setKeyWordNbSufLong(get(_util,_cust, KeyWords.NB_SUF_LONG));
        _kw.setKeyWordNbSufFloatPrim(get(_util,_cust, KeyWords.NB_SUF_FLOAT_PRIM));
        _kw.setKeyWordNbSufFloat(get(_util,_cust, KeyWords.NB_SUF_FLOAT));
        _kw.setKeyWordNbSufDoublePrim(get(_util,_cust, KeyWords.NB_SUF_DOUBLE_PRIM));
        _kw.setKeyWordNbSufDouble(get(_util,_cust, KeyWords.NB_SUF_DOUBLE));
        _kw.setKeyWordIter(get(_util,_cust, KeyWords.ITER));
        _kw.setKeyWordValue(get(_util,_cust, KeyWords.VALUE));
        _kw.setKeyWordElse(get(_util,_cust, KeyWords.ELSE));
        _kw.setKeyWordCatch(get(_util,_cust, KeyWords.CATCH));
        _kw.setKeyWordThrow(get(_util,_cust, KeyWords.THROW));
        _kw.setKeyWordTry(get(_util,_cust, KeyWords.TRY));
        _kw.setKeyWordThis(get(_util,_cust, KeyWords.THIS));
        _kw.setKeyWordSuper(get(_util,_cust, KeyWords.SUPER));
        _kw.setKeyWordCase(get(_util,_cust, KeyWords.CASE));
        _kw.setKeyWordDo(get(_util,_cust, KeyWords.DO));
        _kw.setKeyWordEnum(get(_util,_cust, KeyWords.ENUM));
        _kw.setKeyWordSwitch(get(_util,_cust, KeyWords.SWITCH));
        _kw.setKeyWordIntern(get(_util,_cust, KeyWords.INTERN));
        _kw.setKeyWordNormal(get(_util,_cust, KeyWords.NORMAL));
        _kw.setKeyWordEscTab(get(_util,_cust, KeyWords.ESC_TAB));
        _kw.setKeyWordNbHex(get(_util,_cust, KeyWords.NB_HEX));
        _kw.setKeyWordNbHexEnd(get(_util,_cust, KeyWords.NB_HEX_END));
        _kw.setKeyWordNbBin(get(_util,_cust, KeyWords.NB_BIN));
        _kw.setKeyWordThat(get(_util,_cust, KeyWords.THAT));
        _kw.setKeyWordBool(get(_util,_cust, KeyWords.BOOL));
        _kw.setKeyWordValues(get(_util,_cust, KeyWords.VALUES));
        _kw.setKeyWordLambda(get(_util,_cust, KeyWords.LAMBDA));
        _kw.setKeyWordVararg(get(_util,_cust, KeyWords.VARARG));
        _kw.setKeyWordId(get(_util,_cust, KeyWords.ID));
        _kw.setKeyWordForeach(get(_util,_cust, KeyWords.FOREACH));
        _kw.setKeyWordNbExpBin(get(_util,_cust, KeyWords.NB_EXP_BIN));
        _kw.setKeyWordClasschoice(get(_util,_cust, KeyWords.CLASSCHOICE));
        _kw.setKeyWordFirstopt(get(_util,_cust, KeyWords.FIRSTOPT));
        _kw.setKeyWordPackage(get(_util,_cust, KeyWords.PACKAGE));
        _kw.setKeyWordFinally(get(_util,_cust, KeyWords.FINALLY));
        _kw.setKeyWordEscUnicode(get(_util,_cust, KeyWords.ESC_UNICODE));
        _kw.setKeyWordThisaccess(get(_util,_cust, KeyWords.THISACCESS));
        _kw.setKeyWordValueOf(get(_util,_cust, KeyWords.VALUE_OF));
        _kw.setKeyWordDefaultValue(get(_util,_cust, KeyWords.DEFAULT_VALUE));
        _kw.setKeyWordEscLine(get(_util,_cust, KeyWords.ESC_LINE));
        _kw.setKeyWordOperator(get(_util,_cust, KeyWords.OPERATOR));
        _kw.setKeyWordInterfaces(get(_util,_cust, KeyWords.INTERFACES));
        _kw.setKeyWordSuperaccess(get(_util,_cust, KeyWords.SUPERACCESS));
        _kw.setKeyWordEscBound(get(_util,_cust, KeyWords.ESC_BOUND));
        _kw.setKeyWordEscForm(get(_util,_cust, KeyWords.ESC_FORM));
        _kw.setKeyWordEscFeed(get(_util,_cust, KeyWords.ESC_FEED));
        _kw.setKeyWordNbExpDec(get(_util,_cust, KeyWords.NB_EXP_DEC));
        _kw.setKeyWordProtected(get(_util,_cust, KeyWords.PROTECTED));
        _kw.setKeyWordDefault(get(_util,_cust, KeyWords.DEFAULT));
        _kw.setKeyWordParent(get(_util,_cust, KeyWords.PARENT));
    }


    private void rendMessages(RendAnalysisMessages _mess, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"messagesrender");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        rendMessages(_mess,util_,_cust);
    }
    private void rendMessages(RendAnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.setBadInputName(get(_util,_cust,RendAnalysisMessages.BAD_INPUT_NAME));
        _mess.setStaticInputName(get(_util,_cust,RendAnalysisMessages.STATIC_INPUT_NAME));
        _mess.setUnexpectedChildTag(get(_util,_cust,RendAnalysisMessages.UNEXPECTED_CHILD_TAG));
        _mess.setEmptyAttr(get(_util,_cust,RendAnalysisMessages.EMPTY_ATTR));
        _mess.setUnexpectedExp(get(_util,_cust,RendAnalysisMessages.UNEXPECTED_EXP));
        _mess.setInexistantFile(get(_util,_cust,RendAnalysisMessages.INEXISTANT_FILE));
        _mess.setInexistantKey(get(_util,_cust,RendAnalysisMessages.INEXISTANT_KEY));
        _mess.setBadDocument(get(_util,_cust,RendAnalysisMessages.BAD_DOCUMENT));
    }
    private void messages(AnalysisMessages _mess, String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"messages");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        messages(_mess,util_,_cust);
    }
    private void messages(AnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.setDuplicateMergedMethod(get(_util,_cust,AnalysisMessages.DUPLICATE_MERGED_METHOD));
        _mess.setDuplicateField(get(_util,_cust,AnalysisMessages.DUPLICATE_FIELD));
        _mess.setDuplicateVarType(get(_util,_cust,AnalysisMessages.DUPLICATE_VAR_TYPE));
        _mess.setEmptyPkgRefType(get(_util,_cust,AnalysisMessages.EMPTY_PKG_REF_TYPE));
        _mess.setPrimitiveKeyWord(get(_util,_cust,AnalysisMessages.PRIMITIVE_KEY_WORD));
        _mess.setDefaultPkgRefType(get(_util,_cust,AnalysisMessages.DEFAULT_PKG_REF_TYPE));
        _mess.setRefTypeKeyWord(get(_util,_cust,AnalysisMessages.REF_TYPE_KEY_WORD));
        _mess.setDigitFirstPrimitive(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_PRIMITIVE));
        _mess.setIllegalFirstChar(get(_util,_cust,AnalysisMessages.ILLEGAL_FIRST_CHAR));
        _mess.setEmptyPrimitive(get(_util,_cust,AnalysisMessages.EMPTY_PRIMITIVE));
        _mess.setNotWordCharPrimitive(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_PRIMITIVE));
        _mess.setNotWordCharRefType(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_REF_TYPE));
        _mess.setEmptyRefTypeIn(get(_util,_cust,AnalysisMessages.EMPTY_REF_TYPE_IN));
        _mess.setRefTypePrimitive(get(_util,_cust,AnalysisMessages.REF_TYPE_PRIMITIVE));
        _mess.setDigitFirstRefType(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_REF_TYPE));
        _mess.setDuplicatePrimtive(get(_util,_cust,AnalysisMessages.DUPLICATE_PRIMTIVE));
        _mess.setDuplicateMethod(get(_util,_cust,AnalysisMessages.DUPLICATE_METHOD));
        _mess.setDefaultPkgNoMatch(get(_util,_cust,AnalysisMessages.DEFAULT_PKG_NO_MATCH));
        _mess.setDuplicateRefType(get(_util,_cust,AnalysisMessages.DUPLICATE_REF_TYPE));
        _mess.setDigitFirstMethod(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_METHOD));
        _mess.setNotWordCharField(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_FIELD));
        _mess.setNotWordCharMethod(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_METHOD));
        _mess.setVarTypeKeyWord(get(_util,_cust,AnalysisMessages.VAR_TYPE_KEY_WORD));
        _mess.setVarTypePrimitive(get(_util,_cust,AnalysisMessages.VAR_TYPE_PRIMITIVE));
        _mess.setDigitFirstVarType(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_VAR_TYPE));
        _mess.setDuplicateNumberWord(get(_util,_cust,AnalysisMessages.DUPLICATE_NUMBER_WORD));
        _mess.setMethodPrimitive(get(_util,_cust,AnalysisMessages.METHOD_PRIMITIVE));
        _mess.setFieldPrimitive(get(_util,_cust,AnalysisMessages.FIELD_PRIMITIVE));
        _mess.setDuplicateStringWord(get(_util,_cust,AnalysisMessages.DUPLICATE_STRING_WORD));
        _mess.setDuplicateKeyWord(get(_util,_cust,AnalysisMessages.DUPLICATE_KEY_WORD));
        _mess.setDigitFirstField(get(_util,_cust,AnalysisMessages.DIGIT_FIRST_FIELD));
        _mess.setDuplicateStartingNb(get(_util,_cust,AnalysisMessages.DUPLICATE_STARTING_NB));
        _mess.setDuplicateStartingUni(get(_util,_cust,AnalysisMessages.DUPLICATE_STARTING_UNI));
        _mess.setDuplicateStarting(get(_util,_cust,AnalysisMessages.DUPLICATE_STARTING));
        _mess.setNotWordCharVarType(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR_VAR_TYPE));
        _mess.setEmptyPreBin(get(_util,_cust,AnalysisMessages.EMPTY_PRE_BIN));
        _mess.setEmptyVarType(get(_util,_cust,AnalysisMessages.EMPTY_VAR_TYPE));
        _mess.setEmptyWord(get(_util,_cust,AnalysisMessages.EMPTY_WORD));
        _mess.setEmptyField(get(_util,_cust,AnalysisMessages.EMPTY_FIELD));
        _mess.setEmptyNb(get(_util,_cust,AnalysisMessages.EMPTY_NB));
        _mess.setNotWordChar(get(_util,_cust,AnalysisMessages.NOT_WORD_CHAR));
        _mess.setEmptyPreHex(get(_util,_cust,AnalysisMessages.EMPTY_PRE_HEX));
        _mess.setEmptyMethod(get(_util,_cust,AnalysisMessages.EMPTY_METHOD));
        _mess.setDigitFirst(get(_util,_cust,AnalysisMessages.DIGIT_FIRST));
        _mess.setEmptyBinExp(get(_util,_cust,AnalysisMessages.EMPTY_BIN_EXP));
        _mess.setEmptyString(get(_util,_cust,AnalysisMessages.EMPTY_STRING));
        _mess.setIllegalChar(get(_util,_cust,AnalysisMessages.ILLEGAL_CHAR));
        _mess.setMethodKeyWord(get(_util,_cust,AnalysisMessages.METHOD_KEY_WORD));
        _mess.setEmptyRefType(get(_util,_cust,AnalysisMessages.EMPTY_REF_TYPE));
        _mess.setFieldKeyWord(get(_util,_cust,AnalysisMessages.FIELD_KEY_WORD));
        _mess.setAbstractMethodBody(get(_util,_cust,AnalysisMessages.ABSTRACT_METHOD_BODY));
        _mess.setAbstractMethodConc(get(_util,_cust,AnalysisMessages.ABSTRACT_METHOD_CONC));
        _mess.setAbstractMethodImpl(get(_util,_cust,AnalysisMessages.ABSTRACT_METHOD_IMPL));
        _mess.setAbstractMethodRef(get(_util,_cust,AnalysisMessages.ABSTRACT_METHOD_REF));
        _mess.setInaccessibleType(get(_util,_cust,AnalysisMessages.INACCESSIBLE_TYPE));
        _mess.setUnexpectedType(get(_util,_cust,AnalysisMessages.UNEXPECTED_TYPE));
        _mess.setUnexpectedRetType(get(_util,_cust,AnalysisMessages.UNEXPECTED_RET_TYPE));
        _mess.setMethodsAccesses(get(_util,_cust,AnalysisMessages.METHODS_ACCESSES));
        _mess.setEmptyPackage(get(_util,_cust,AnalysisMessages.EMPTY_PACKAGE));
        _mess.setEmptyPartClassName(get(_util,_cust,AnalysisMessages.EMPTY_PART_CLASS_NAME));
        _mess.setBadPartClassName(get(_util,_cust,AnalysisMessages.BAD_PART_CLASS_NAME));
        _mess.setKeyWordPartClassName(get(_util,_cust,AnalysisMessages.KEY_WORD_PART_CLASS_NAME));
        _mess.setPrimPartClassName(get(_util,_cust,AnalysisMessages.PRIM_PART_CLASS_NAME));
        _mess.setDigitPartClassName(get(_util,_cust,AnalysisMessages.DIGIT_PART_CLASS_NAME));
        _mess.setBadPartVarClassName(get(_util,_cust,AnalysisMessages.BAD_PART_VAR_CLASS_NAME));
        _mess.setKeyWordPartVarClassName(get(_util,_cust,AnalysisMessages.KEY_WORD_PART_VAR_CLASS_NAME));
        _mess.setPrimPartVarClassName(get(_util,_cust,AnalysisMessages.PRIM_PART_VAR_CLASS_NAME));
        _mess.setDigitPartVarClassName(get(_util,_cust,AnalysisMessages.DIGIT_PART_VAR_CLASS_NAME));
        _mess.setDuplicatedPartVarClassName(get(_util,_cust,AnalysisMessages.DUPLICATED_PART_VAR_CLASS_NAME));
        _mess.setCallCtorEnd(get(_util,_cust,AnalysisMessages.CALL_CTOR_END));
        _mess.setCallCtor(get(_util,_cust,AnalysisMessages.CALL_CTOR));
        _mess.setCallCtorBeforeBlock(get(_util,_cust,AnalysisMessages.CALL_CTOR_BEFORE_BLOCK));
        _mess.setCallCtorFirstLine(get(_util,_cust,AnalysisMessages.CALL_CTOR_FIRST_LINE));
        _mess.setCallCtorIntFromSuperInt(get(_util,_cust,AnalysisMessages.CALL_CTOR_INT_FROM_SUPER_INT));
        _mess.setCallCtorIntNotFromInt(get(_util,_cust,AnalysisMessages.CALL_CTOR_INT_NOT_FROM_INT));
        _mess.setCallCtorIntAfterSuperThis(get(_util,_cust,AnalysisMessages.CALL_CTOR_INT_AFTER_SUPER_THIS));
        _mess.setCallCtorIntInherits(get(_util,_cust,AnalysisMessages.CALL_CTOR_INT_INHERITS));
        _mess.setCallCtorSuperClassEnumSingleton(get(_util,_cust,AnalysisMessages.CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON));
        _mess.setAnnotFieldNotUniq(get(_util,_cust,AnalysisMessages.ANNOT_FIELD_NOT_UNIQ));
        _mess.setAnnotFieldMust(get(_util,_cust,AnalysisMessages.ANNOT_FIELD_MUST));
        _mess.setDupSuppliedAnnotField(get(_util,_cust,AnalysisMessages.DUP_SUPPLIED_ANNOT_FIELD));
        _mess.setBadExpression(get(_util,_cust,AnalysisMessages.BAD_EXPRESSION));
        _mess.setBadFieldName(get(_util,_cust,AnalysisMessages.BAD_FIELD_NAME));
        _mess.setKeyWordFieldName(get(_util,_cust,AnalysisMessages.KEY_WORD_FIELD_NAME));
        _mess.setPrimFieldName(get(_util,_cust,AnalysisMessages.PRIM_FIELD_NAME));
        _mess.setDigitFieldName(get(_util,_cust,AnalysisMessages.DIGIT_FIELD_NAME));
        _mess.setNotRetrievedFields(get(_util,_cust,AnalysisMessages.NOT_RETRIEVED_FIELDS));
        _mess.setBadNbFormat(get(_util,_cust,AnalysisMessages.BAD_NB_FORMAT));
        _mess.setBadCharFormat(get(_util,_cust,AnalysisMessages.BAD_CHAR_FORMAT));
        _mess.setBadImplicitCast(get(_util,_cust,AnalysisMessages.BAD_IMPLICIT_CAST));
        _mess.setNotPrimitiveWrapper(get(_util,_cust,AnalysisMessages.NOT_PRIMITIVE_WRAPPER));
        _mess.setVoidType(get(_util,_cust,AnalysisMessages.VOID_TYPE));
        _mess.setBadIndexInParser(get(_util,_cust,AnalysisMessages.BAD_INDEX_IN_PARSER));
        _mess.setIllegalCharacter(get(_util,_cust,AnalysisMessages.ILLEGAL_CHARACTER));
        _mess.setCallIntInherits(get(_util,_cust,AnalysisMessages.CALL_INT_INHERITS));
        _mess.setCallIntNoNeed(get(_util,_cust,AnalysisMessages.CALL_INT_NO_NEED));
        _mess.setCallIntNoNeedType(get(_util,_cust,AnalysisMessages.CALL_INT_NO_NEED_TYPE));
        _mess.setCallIntNeedType(get(_util,_cust,AnalysisMessages.CALL_INT_NEED_TYPE));
        _mess.setCallIntOnly(get(_util,_cust,AnalysisMessages.CALL_INT_ONLY));
        _mess.setBadInheritsType(get(_util,_cust,AnalysisMessages.BAD_INHERITS_TYPE));
        _mess.setBadInheritsTypeInn(get(_util,_cust,AnalysisMessages.BAD_INHERITS_TYPE_INN));
        _mess.setBadInheritsTypeAsInn(get(_util,_cust,AnalysisMessages.BAD_INHERITS_TYPE_AS_INN));
        _mess.setBadInheritsTypeInt(get(_util,_cust,AnalysisMessages.BAD_INHERITS_TYPE_INT));
        _mess.setFinalType(get(_util,_cust,AnalysisMessages.FINAL_TYPE));
        _mess.setDuplicateSuper(get(_util,_cust,AnalysisMessages.DUPLICATE_SUPER));
        _mess.setReservedType(get(_util,_cust,AnalysisMessages.RESERVED_TYPE));
        _mess.setSuperClass(get(_util,_cust,AnalysisMessages.SUPER_CLASS));
        _mess.setUnknownSuperType(get(_util,_cust,AnalysisMessages.UNKNOWN_SUPER_TYPE));
        _mess.setCyclicInherits(get(_util,_cust,AnalysisMessages.CYCLIC_INHERITS));
        _mess.setAnnotationParam(get(_util,_cust,AnalysisMessages.ANNOTATION_PARAM));
        _mess.setCyclicMapping(get(_util,_cust,AnalysisMessages.CYCLIC_MAPPING));
        _mess.setAbsMapping(get(_util,_cust,AnalysisMessages.ABS_MAPPING));
        _mess.setFinalMapping(get(_util,_cust,AnalysisMessages.FINAL_MAPPING));
        _mess.setMustCallIntCtor(get(_util,_cust,AnalysisMessages.MUST_CALL_INT_CTOR));
        _mess.setMustNotCallIntCtorAfterThis(get(_util,_cust,AnalysisMessages.MUST_NOT_CALL_INT_CTOR_AFTER_THIS));
        _mess.setMustCallIntCtorNeed(get(_util,_cust,AnalysisMessages.MUST_CALL_INT_CTOR_NEED));
        _mess.setMustCallIntCtorNotNeed(get(_util,_cust,AnalysisMessages.MUST_CALL_INT_CTOR_NOT_NEED));
        _mess.setBadLabel(get(_util,_cust,AnalysisMessages.BAD_LABEL));
        _mess.setDuplicatedLabel(get(_util,_cust,AnalysisMessages.DUPLICATED_LABEL));
        _mess.setBadMethodName(get(_util,_cust,AnalysisMessages.BAD_METHOD_NAME));
        _mess.setKeyWordMethodName(get(_util,_cust,AnalysisMessages.KEY_WORD_METHOD_NAME));
        _mess.setPrimMethodName(get(_util,_cust,AnalysisMessages.PRIM_METHOD_NAME));
        _mess.setDigitMethodName(get(_util,_cust,AnalysisMessages.DIGIT_METHOD_NAME));
        _mess.setBadOperatorName(get(_util,_cust,AnalysisMessages.BAD_OPERATOR_NAME));
        _mess.setBadAccess(get(_util,_cust,AnalysisMessages.BAD_ACCESS));
        _mess.setBadReturnType(get(_util,_cust,AnalysisMessages.BAD_RETURN_TYPE));
        _mess.setBadParams(get(_util,_cust,AnalysisMessages.BAD_PARAMS));
        _mess.setBadMethodModifier(get(_util,_cust,AnalysisMessages.BAD_METHOD_MODIFIER));
        _mess.setBadMethodVararg(get(_util,_cust,AnalysisMessages.BAD_METHOD_VARARG));
        _mess.setBadIndexerParams(get(_util,_cust,AnalysisMessages.BAD_INDEXER_PARAMS));
        _mess.setBadIndexerModifier(get(_util,_cust,AnalysisMessages.BAD_INDEXER_MODIFIER));
        _mess.setBadIndexerModifiers(get(_util,_cust,AnalysisMessages.BAD_INDEXER_MODIFIERS));
        _mess.setBadIndexerAccesses(get(_util,_cust,AnalysisMessages.BAD_INDEXER_ACCESSES));
        _mess.setBadIndexerPairGet(get(_util,_cust,AnalysisMessages.BAD_INDEXER_PAIR_GET));
        _mess.setBadIndexerPairSet(get(_util,_cust,AnalysisMessages.BAD_INDEXER_PAIR_SET));
        _mess.setDuplicateCustomMethod(get(_util,_cust,AnalysisMessages.DUPLICATE_CUSTOM_METHOD));
        _mess.setReservedCustomMethod(get(_util,_cust,AnalysisMessages.RESERVED_CUSTOM_METHOD));
        _mess.setDuplicateIndexer(get(_util,_cust,AnalysisMessages.DUPLICATE_INDEXER));
        _mess.setDuplicateOperator(get(_util,_cust,AnalysisMessages.DUPLICATE_OPERATOR));
        _mess.setFunctionalApplyNbDiff(get(_util,_cust,AnalysisMessages.FUNCTIONAL_APPLY_NB_DIFF));
        _mess.setFunctionalApplyOnly(get(_util,_cust,AnalysisMessages.FUNCTIONAL_APPLY_ONLY));
        _mess.setOperatorNbDiff(get(_util,_cust,AnalysisMessages.OPERATOR_NB_DIFF));
        _mess.setSplitComa(get(_util,_cust,AnalysisMessages.SPLIT_COMA));
        _mess.setSplitComaLow(get(_util,_cust,AnalysisMessages.SPLIT_COMA_LOW));
        _mess.setSplitDiff(get(_util,_cust,AnalysisMessages.SPLIT_DIFF));
        _mess.setBadDotted(get(_util,_cust,AnalysisMessages.BAD_DOTTED));
        _mess.setBadParamName(get(_util,_cust,AnalysisMessages.BAD_PARAM_NAME));
        _mess.setReservedParamName(get(_util,_cust,AnalysisMessages.RESERVED_PARAM_NAME));
        _mess.setDuplicatedParamName(get(_util,_cust,AnalysisMessages.DUPLICATED_PARAM_NAME));
        _mess.setBadReturnTypeInherit(get(_util,_cust,AnalysisMessages.BAD_RETURN_TYPE_INHERIT));
        _mess.setBadReturnTypeIndexer(get(_util,_cust,AnalysisMessages.BAD_RETURN_TYPE_INDEXER));
        _mess.setDuplicatedOverriding(get(_util,_cust,AnalysisMessages.DUPLICATED_OVERRIDING));
        _mess.setTwoFinal(get(_util,_cust,AnalysisMessages.TWO_FINAL));
        _mess.setFinalNotSubReturnType(get(_util,_cust,AnalysisMessages.FINAL_NOT_SUB_RETURN_TYPE));
        _mess.setTwoReturnTypes(get(_util,_cust,AnalysisMessages.TWO_RETURN_TYPES));
        _mess.setReturnTypes(get(_util,_cust,AnalysisMessages.RETURN_TYPES));
        _mess.setBadVariableName(get(_util,_cust,AnalysisMessages.BAD_VARIABLE_NAME));
        _mess.setKeyWordVariableName(get(_util,_cust,AnalysisMessages.KEY_WORD_VARIABLE_NAME));
        _mess.setPrimVariableName(get(_util,_cust,AnalysisMessages.PRIM_VARIABLE_NAME));
        _mess.setDigitVariableName(get(_util,_cust,AnalysisMessages.DIGIT_VARIABLE_NAME));
        _mess.setDuplicatedVariableName(get(_util,_cust,AnalysisMessages.DUPLICATED_VARIABLE_NAME));
        _mess.setCyclicCtorCall(get(_util,_cust,AnalysisMessages.CYCLIC_CTOR_CALL));
        _mess.setDeadCode(get(_util,_cust,AnalysisMessages.DEAD_CODE));
        _mess.setDuplicatedCtor(get(_util,_cust,AnalysisMessages.DUPLICATED_CTOR));
        _mess.setDuplicatedGenericSuperTypes(get(_util,_cust,AnalysisMessages.DUPLICATED_GENERIC_SUPER_TYPES));
        _mess.setDuplicatedInnerType(get(_util,_cust,AnalysisMessages.DUPLICATED_INNER_TYPE));
        _mess.setDuplicatedType(get(_util,_cust,AnalysisMessages.DUPLICATED_TYPE));
        _mess.setDuplicatedTypePrim(get(_util,_cust,AnalysisMessages.DUPLICATED_TYPE_PRIM));
        _mess.setDuplicatedTypeStd(get(_util,_cust,AnalysisMessages.DUPLICATED_TYPE_STD));
        _mess.setDuplicatedTypePkg(get(_util,_cust,AnalysisMessages.DUPLICATED_TYPE_PKG));
        _mess.setEmptyExpressionPart(get(_util,_cust,AnalysisMessages.EMPTY_EXPRESSION_PART));
        _mess.setDoWhileNotEmpty(get(_util,_cust,AnalysisMessages.DO_WHILE_NOT_EMPTY));
        _mess.setDuplicatedFinal(get(_util,_cust,AnalysisMessages.DUPLICATED_FINAL));
        _mess.setIllegalCtorEnum(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_ENUM));
        _mess.setIllegalGenericSuperTypeBound(get(_util,_cust,AnalysisMessages.ILLEGAL_GENERIC_SUPER_TYPE_BOUND));
        _mess.setIllegalCtorAnnotation(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_ANNOTATION));
        _mess.setIllegalCtorAbstract(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_ABSTRACT));
        _mess.setIllegalCtorBound(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_BOUND));
        _mess.setIllegalCtorArray(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_ARRAY));
        _mess.setIllegalCtorUnknown(get(_util,_cust,AnalysisMessages.ILLEGAL_CTOR_UNKNOWN));
        _mess.setMissingAbrupt(get(_util,_cust,AnalysisMessages.MISSING_ABRUPT));
        _mess.setNotInitClass(get(_util,_cust,AnalysisMessages.NOT_INIT_CLASS));
        _mess.setNullValue(get(_util,_cust,AnalysisMessages.NULL_VALUE));
        _mess.setBadParameTypeForId(get(_util,_cust,AnalysisMessages.BAD_PARAME_TYPE_FOR_ID));
        _mess.setNotResolvedOwner(get(_util,_cust,AnalysisMessages.NOT_RESOLVED_OWNER));
        _mess.setUndefinedAccessibleField(get(_util,_cust,AnalysisMessages.UNDEFINED_ACCESSIBLE_FIELD));
        _mess.setStaticAccess(get(_util,_cust,AnalysisMessages.STATIC_ACCESS));
        _mess.setStaticAccessPrev(get(_util,_cust,AnalysisMessages.STATIC_ACCESS_PREV));
        _mess.setUnassignedFinalField(get(_util,_cust,AnalysisMessages.UNASSIGNED_FINAL_FIELD));
        _mess.setUnassignedInferingType(get(_util,_cust,AnalysisMessages.UNASSIGNED_INFERING_TYPE));
        _mess.setUndefinedCtor(get(_util,_cust,AnalysisMessages.UNDEFINED_CTOR));
        _mess.setUndefinedMethod(get(_util,_cust,AnalysisMessages.UNDEFINED_METHOD));
        _mess.setArrayCloneOnly(get(_util,_cust,AnalysisMessages.ARRAY_CLONE_ONLY));
        _mess.setUndefinedSuperCtor(get(_util,_cust,AnalysisMessages.UNDEFINED_SUPER_CTOR));
        _mess.setUndefinedSuperCtorCall(get(_util,_cust,AnalysisMessages.UNDEFINED_SUPER_CTOR_CALL));
        _mess.setUndefinedVariable(get(_util,_cust,AnalysisMessages.UNDEFINED_VARIABLE));
        _mess.setUnexpectedAffect(get(_util,_cust,AnalysisMessages.UNEXPECTED_AFFECT));
        _mess.setFinalField(get(_util,_cust,AnalysisMessages.FINAL_FIELD));
        _mess.setBadOperatorRef(get(_util,_cust,AnalysisMessages.BAD_OPERATOR_REF));
        _mess.setUnexpectedCatchElseFinally(get(_util,_cust,AnalysisMessages.UNEXPECTED_CATCH_ELSE_FINALLY));
        _mess.setUnexpectedAbrupt(get(_util,_cust,AnalysisMessages.UNEXPECTED_ABRUPT));
        _mess.setUnexpectedAbruptLab(get(_util,_cust,AnalysisMessages.UNEXPECTED_ABRUPT_LAB));
        _mess.setUnexpectedCaseDef(get(_util,_cust,AnalysisMessages.UNEXPECTED_CASE_DEF));
        _mess.setUnexpectedCaseVar(get(_util,_cust,AnalysisMessages.UNEXPECTED_CASE_VAR));
        _mess.setUnexpectedCaseValue(get(_util,_cust,AnalysisMessages.UNEXPECTED_CASE_VALUE));
        _mess.setUnexpectedCaseDup(get(_util,_cust,AnalysisMessages.UNEXPECTED_CASE_DUP));
        _mess.setUnexpectedDefDup(get(_util,_cust,AnalysisMessages.UNEXPECTED_DEF_DUP));
        _mess.setUnexpectedDoTry(get(_util,_cust,AnalysisMessages.UNEXPECTED_DO_TRY));
        _mess.setUnexpectedSwitch(get(_util,_cust,AnalysisMessages.UNEXPECTED_SWITCH));
        _mess.setUnexpectedMemberInst(get(_util,_cust,AnalysisMessages.UNEXPECTED_MEMBER_INST));
        _mess.setUnexpectedBlockExp(get(_util,_cust,AnalysisMessages.UNEXPECTED_BLOCK_EXP));
        _mess.setUnexpectedOperandTypes(get(_util,_cust,AnalysisMessages.UNEXPECTED_OPERAND_TYPES));
        _mess.setUnknownType(get(_util,_cust,AnalysisMessages.UNKNOWN_TYPE));
        _mess.setEmptyType(get(_util,_cust,AnalysisMessages.EMPTY_TYPE));
        _mess.setBadParamerizedType(get(_util,_cust,AnalysisMessages.BAD_PARAMERIZED_TYPE));
        _mess.setUnexpectedTypeBound(get(_util,_cust,AnalysisMessages.UNEXPECTED_TYPE_BOUND));
        _mess.setUnexpectedVararg(get(_util,_cust,AnalysisMessages.UNEXPECTED_VARARG));
        _mess.setUnexpectedLeaf(get(_util,_cust,AnalysisMessages.UNEXPECTED_LEAF));
        _mess.setEmptyPart(get(_util,_cust,AnalysisMessages.EMPTY_PART));
    }
    private String get(StringMap<String> _util, StringMap<String> _cust, String _key) {
        String val_ = _cust.getVal(_key);
        if (val_ == null) {
            String def_ = _util.getVal(_key);
            if (def_ == null) {
                return "";
            }
            return def_;
        }
        return val_;
    }
}
