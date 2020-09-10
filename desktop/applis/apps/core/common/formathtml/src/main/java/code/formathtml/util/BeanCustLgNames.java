package code.formathtml.util;

import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.options.Options;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.formathtml.structs.ValidatorInfo;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultFullStack;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.*;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.exec.*;
import code.formathtml.structs.MessageStruct;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.Document;
import code.sml.Element;
import code.util.*;

public abstract class BeanCustLgNames extends BeanLgNames {
    protected static final String BEAN = "Bean";
    protected static final String MAP_KEYS = "MapKeys";
    protected static final String MAP_VALUES = "MapValues";
    protected static final String MAP_INDEX_OF_ENTRY = "MapIndexOfEntry";
    protected static final String MAP_ADD_ENTRY = "MapAddEntry";
    protected static final String MAP_GET_VALUE = "MapGetValue";
    protected static final String MAP_FIRST_VALUE = "MapFirstValue";
    protected static final String MAP_LAST_VALUE = "MapLastValue";
    protected static final String MAP_SET_VALUE = "MapSetValue";
    protected static final String MAP_PUT = "MapPut";
    protected static final String MAP_CONTAINS = "MapContains";
    protected static final String MAP_PUT_ALL = "MapPutAll";
    protected static final String MAP_GET_VAL = "MapGetVal";
    protected static final String MAP_REMOVE_KEY = "MapRemoveKey";
    protected static final String MAP_GET_KEY = "MapGetKey";
    protected static final String MAP_FIRST_KEY = "MapFirstKey";
    protected static final String MAP_LAST_KEY = "MapLastKey";
    protected static final String MAP_SET_KEY = "MapSetKey";
    protected static final String MAP_SIZE = "MapSize";
    protected static final String MAP_IS_EMPTY = "MapIsEmpty";
    protected static final String MAP_CLEAR = "MapClear";
    protected static final String VALIDATOR = "Validator";
    protected static final String VALIDATE = "Validate";
    protected static final String DATA_BASE_FIELD = "DataBaseField";
    protected static final String FORMS = "Forms";
    protected static final String SET_FORMS = "SetForms";
    protected static final String GET_FORMS = "GetForms";
    protected static final String LANGUAGE = "Language";
    protected static final String SET_LANGUAGE = "SetLanguage";
    protected static final String GET_LANGUAGE = "GetLanguage";
    protected static final String SCOPE = "Scope";
    protected static final String SET_SCOPE = "SetScope";
    protected static final String GET_SCOPE = "GetScope";
    protected static final String SET_DATA_BASE = "SetDataBase";
    protected static final String GET_DATA_BASE = "GetDataBase";
    protected static final String BEFORE_DISPLAYING = "BeforeDisplaying";
    protected static final String STRING_MAP_OBJECT = "StringMapObject";
    protected static final String MESSAGE = "Message";
    protected static final String NEW_MESSAGE = "NewMessage";
    protected static final String MESSAGE_FORMAT = "MessageFormat";
    protected static final String MESSAGE_GET_ARGS = "MessageGetArgs";
    protected static final String MESSAGE_SET_ARGS = "MessageSetArgs";

    private static final String RETURN_LINE = "\n";

    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;


    private String iteratorTableVarCust;
    private String hasNextPairVarCust;
    private String nextPairVarCust;
    private String firstVarCust;
    private String secondVarCust;
    private String beforeDisplayingVar;

    private String putVarCust;
    private String putVarCustKey;
    private String putVarCustValue;
    private String putAllVarCust;
    private String putAllVarCustArg;
    private String getValVar;
    private String getValVarArg;
    private String getFormsVar;
    private String setFormsVarArg;
    private String setFormsVar;
    private String getDataBaseVar;
    private String setDataBaseVarArg;
    private String setDataBaseVar;
    private String getScopeVar;
    private String setScopeVarArg;
    private String setScopeVar;
    private String setLanguageVarArg;
    private String setLanguageVar;
    private CustList<RendDynOperationNode> expsIterator;
    private CustList<RendDynOperationNode> expsHasNext;
    private CustList<RendDynOperationNode> expsNext;
    private CustList<RendDynOperationNode> expsIteratorTableCust;
    private CustList<RendDynOperationNode> expsHasNextPairCust;
    private CustList<RendDynOperationNode> expsNextPairCust;
    private CustList<RendDynOperationNode> expsFirstCust;
    private CustList<RendDynOperationNode> expsSecondCust;
    private CustList<RendDynOperationNode> expsBeforeDisplaying;

    private CustList<RendDynOperationNode> expsPut;
    private CustList<RendDynOperationNode> expsPutAll;
    private CustList<RendDynOperationNode> expsGetVal;
    private CustList<RendDynOperationNode> expsGetForms;
    private CustList<RendDynOperationNode> expsSetForms;
    private CustList<RendDynOperationNode> expsGetDataBase;
    private CustList<RendDynOperationNode> expsSetDataBase;
    private CustList<RendDynOperationNode> expsGetScope;
    private CustList<RendDynOperationNode> expsSetScope;
    private CustList<RendDynOperationNode> expsSetLanguage;
    private CustList<RendDynOperationNode> expsValidate;
    private CustList<RendDynOperationNode> opsMap;
    private String aliasBean = "code.bean.Bean";
    private String aliasMapKeys = "keys";
    private String aliasMapValues = "values";
    private String aliasMapIndexOfEntry = "indexOfEntry";
    private String aliasMapAddEntry = "addEntry";
    private String aliasMapGetValue = "getValue";
    private String aliasMapFirstValue = "firstValue";
    private String aliasMapLastValue = "lastValue";
    private String aliasMapSetValue = "setValue";
    private String aliasMapPut = "put";
    private String aliasMapContains = "contains";
    private String aliasMapPutAll = "putAll";
    private String aliasMapGetVal = "getVal";
    private String aliasMapRemoveKey = "removeKey";
    private String aliasMapGetKey = "getKey";
    private String aliasMapFirstKey = "firstKey";
    private String aliasMapLastKey = "lastKey";
    private String aliasMapSetKey = "setKey";
    private String aliasMapSize = "size";
    private String aliasMapIsEmpty = "isEmpty";
    private String aliasMapClear = "clear";

    private String aliasValidator="code.bean.Validator";
    private String aliasValidate="validate";
    private String validateVar;
    private String validateVarArgNewValue;
    private String validateVarArgOldValue;
    private String validateVarArgBean;
    private String validateVarArgForm;
    private String validateVarArgClassField;
    private String vlidateVarArgNameField;

    private Struct storedForms = NullStruct.NULL_VALUE;


    private String aliasDataBaseField= "dataBase";

    private String aliasForms= "forms";
    private String aliasSetForms= "setForms";
    private String aliasGetForms= "getForms";
    private String aliasLanguage= "language";
    private String aliasSetLanguage= "setLanguage";
    private String aliasGetLanguage= "getLanguage";
    private String aliasScope= "scope";
    private String aliasSetScope= "setScope";
    private String aliasGetScope= "getScope";
    private String aliasSetDataBase= "setDataBase";
    private String aliasGetDataBase= "getDataBase";
    private String aliasBeforeDisplaying= "beforeDisplaying";
    private String aliasStringMapObject="code.util.StringMapObject";
    private String aliasMessage="code.bean.Message";
    private String aliasNewMessage="newStandardMessage";
    private String aliasMessageFormat="format";
    private String aliasMessageGetArgs="getArgs";
    private String aliasMessageSetArgs="setArgs";

    public BeanCustLgNames(AbstractGenerator _gene) {
        super(_gene);
    }

    @Override
    public void buildOther() {
        CustList<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        std_ = new StandardClass(aliasMessage, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageFormat, params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageGetArgs, params_, StringExpUtil.getPrettyArrayType(getAliasString()), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasMessageSetArgs, params_, getAliasVoid(), true, MethodModifier.NORMAL);
        methods_.add( method_);
        getStandards().addEntry(aliasMessage, std_);
    }

    public void buildIterables(Configuration _context) {
        _context.getImporting().add(new ImportingPage());
        _context.setupInts();
        StringMap<String> args_ = new StringMap<String>();
        StringList l_ = new StringList();
        String locName_ = tr(l_);
        iteratorVar = locName_;
        String simpleIterator_ = getAliasIterator();
        expsIterator= newCall(iteratorVar,StringList.concat(getAliasIterable(),"<?>"),
                new ClassMethodId(getAliasIterable(),new MethodId(MethodAccessKind.INSTANCE,simpleIterator_,new StringList(
                ))),
                StringList.concat(getAliasIteratorType(),"<?>"), args_,_context);
        locName_ = tr(l_);
        hasNextVar = locName_;
        String hasNext_ = getAliasHasNext();
        expsHasNext= newCall(hasNextVar,StringList.concat(getAliasIteratorType(),"<?>"),
                new ClassMethodId(getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,hasNext_,new StringList(
                ))),
                getAliasPrimBoolean(), args_,_context);
        locName_ = tr(l_);
        nextVar = locName_;
        String next_ = getAliasNext();
        expsNext= newCall(nextVar,StringList.concat(getAliasIteratorType(),"<?>"),
                new ClassMethodId(getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,next_,new StringList(
                ))),
                getAliasObject(), args_,_context);

        String nextPair_ = getAliasNextPair();
        String hasNextPair_ = getAliasHasNextPair();
        iteratorTableVarCust= locName_;
        String iteratorTable_ = getAliasIteratorTable();
        expsIteratorTableCust= newCall(iteratorTableVarCust,StringList.concat(getAliasIterableTable(),"<?,?>"),
                new ClassMethodId(getAliasIterableTable(),new MethodId(MethodAccessKind.INSTANCE,iteratorTable_,new StringList(
                ))),
                StringList.concat(getAliasIteratorTableType(),"<?,?>"), args_,_context);
        locName_ = tr(l_);
        hasNextPairVarCust= locName_;
        expsHasNextPairCust= newCall(hasNextPairVarCust,StringList.concat(getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,hasNextPair_,new StringList(
                ))),
                getAliasPrimBoolean(), args_,_context);
        locName_ = tr(l_);
        nextPairVarCust= locName_;
        expsNextPairCust= newCall(nextPairVarCust,StringList.concat(getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,nextPair_,new StringList(
                ))),
                StringList.concat(getAliasPairType(),"<?,?>"), args_,_context);
        locName_ = tr(l_);
        firstVarCust= locName_;
        String first_ = getAliasGetFirst();
        expsFirstCust= newCall(firstVarCust,StringList.concat(getAliasPairType(),"<?,?>"),
                new ClassMethodId(getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,first_,new StringList(
                ))),
                getAliasObject(), args_,_context);
        locName_ = tr(l_);
        secondVarCust= locName_;
        String second_ = getAliasGetSecond();
        expsSecondCust= newCall(secondVarCust,StringList.concat(getAliasPairType(),"<?,?>"),
                new ClassMethodId(getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,second_,new StringList(
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        beforeDisplayingVar = locName_;
        String beforeDisplaying_ = getAliasBeforeDisplaying();
        expsBeforeDisplaying= newCall(beforeDisplayingVar,getAliasBean(),
                new ClassMethodId(getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,beforeDisplaying_,new StringList(
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        putVarCust = locName_;
        locName_ = tr(l_);
        putVarCustKey = locName_;
        locName_ = tr(l_);
        putVarCustValue = locName_;
        String put_ = getAliasMapPut();
        args_ = new StringMap<String>();
        args_.addEntry(putVarCustKey,getAliasString());
        args_.addEntry(putVarCustValue,getAliasObject());
        expsPut= newCall(putVarCust,getAliasStringMapObject(),
                new ClassMethodId(getAliasStringMapObject(),new MethodId(MethodAccessKind.INSTANCE,put_,new StringList(
                        getAliasString(),
                        getAliasObject()
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        putAllVarCust = locName_;
        locName_ = tr(l_);
        putAllVarCustArg = locName_;
        String putAll_ = getAliasMapPutAll();
        args_ = new StringMap<String>();
        args_.addEntry(putAllVarCustArg,getAliasStringMapObject());
        expsPutAll= newCall(putAllVarCust,getAliasStringMapObject(),
                new ClassMethodId(getAliasStringMapObject(),new MethodId(MethodAccessKind.INSTANCE,putAll_,new StringList(
                        getAliasStringMapObject()
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        getValVar = locName_;
        locName_ = tr(l_);
        getValVarArg = locName_;
        String getVal_ = getAliasMapGetVal();
        args_ = new StringMap<String>();
        args_.addEntry(getValVarArg,getAliasString());
        expsGetVal= newCall(getValVar,getAliasStringMapObject(),
                new ClassMethodId(getAliasStringMapObject(),new MethodId(MethodAccessKind.INSTANCE,getVal_,new StringList(
                        getAliasString()
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        setFormsVar = locName_;
        locName_ = tr(l_);
        setFormsVarArg = locName_;
        String setForms_ = getAliasSetForms();
        args_ = new StringMap<String>();
        args_.addEntry(setFormsVarArg,getAliasStringMapObject());
        expsSetForms= newCall(setFormsVar,getAliasBean(),
                new ClassMethodId(getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,setForms_,new StringList(
                        getAliasStringMapObject()
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        getFormsVar = locName_;
        String getForms_ = getAliasGetForms();
        args_ = new StringMap<String>();
        expsGetForms= newCall(getFormsVar,getAliasBean(),
                new ClassMethodId(getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,getForms_,new StringList(
                ))),
                getAliasStringMapObject(), args_,_context);

        locName_ = tr(l_);
        setDataBaseVar = locName_;
        locName_ = tr(l_);
        setDataBaseVarArg = locName_;
        String setDataBase_ = getAliasSetDataBase();
        args_ = new StringMap<String>();
        args_.addEntry(setDataBaseVarArg,getAliasObject());
        expsSetDataBase= newCall(setDataBaseVar,getAliasBean(),
                new ClassMethodId(getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,setDataBase_,new StringList(
                        getAliasObject()
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        getDataBaseVar = locName_;
        String getDataBase_ = getAliasGetDataBase();
        args_ = new StringMap<String>();
        expsGetDataBase= newCall(getDataBaseVar,getAliasBean(),
                new ClassMethodId(getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,getDataBase_,new StringList(
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        setScopeVar = locName_;
        locName_ = tr(l_);
        setScopeVarArg = locName_;
        String setScope_ = getAliasSetScope();
        args_ = new StringMap<String>();
        args_.addEntry(setScopeVarArg,getAliasString());
        expsSetScope= newCall(setScopeVar,getAliasBean(),
                new ClassMethodId(getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,setScope_,new StringList(
                        getAliasString()
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        getScopeVar = locName_;
        String getScope_ = getAliasGetScope();
        args_ = new StringMap<String>();
        expsGetScope= newCall(getScopeVar,getAliasBean(),
                new ClassMethodId(getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,getScope_,new StringList(
                ))),
                getAliasString(), args_,_context);

        locName_ = tr(l_);
        setLanguageVar = locName_;
        locName_ = tr(l_);
        setLanguageVarArg = locName_;
        String setLanguage_ = getAliasSetLanguage();
        args_ = new StringMap<String>();
        args_.addEntry(setLanguageVarArg,getAliasString());
        expsSetLanguage= newCall(setLanguageVar,getAliasBean(),
                new ClassMethodId(getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,setLanguage_,new StringList(
                        getAliasString()
                ))),
                getAliasObject(), args_,_context);

        locName_ = tr(l_);
        validateVar = locName_;
        locName_ = tr(l_);
        validateVarArgNewValue = locName_;
        locName_ = tr(l_);
        validateVarArgOldValue = locName_;
        locName_ = tr(l_);
        validateVarArgBean = locName_;
        locName_ = tr(l_);
        validateVarArgForm = locName_;
        locName_ = tr(l_);
        validateVarArgClassField = locName_;
        locName_ = tr(l_);
        vlidateVarArgNameField = locName_;

        String validate_ = aliasValidate;
        args_ = new StringMap<String>();
        args_.addEntry(validateVarArgNewValue,getAliasObject());
        args_.addEntry(validateVarArgOldValue,getAliasObject());
        args_.addEntry(validateVarArgBean,getAliasObject());
        args_.addEntry(validateVarArgForm,StringExpUtil.getPrettyArrayType(getAliasObject()));
        args_.addEntry(validateVarArgClassField,getAliasString());
        args_.addEntry(vlidateVarArgNameField,getAliasString());
        expsValidate = newCall(validateVar,aliasValidator,
                new ClassMethodId(aliasValidator,new MethodId(MethodAccessKind.INSTANCE,validate_,new StringList(
                        getAliasObject(),
                        getAliasObject(),
                        getAliasObject(),
                        StringExpUtil.getPrettyArrayType(getAliasObject()),
                        getAliasString(),
                        getAliasString()
                ))),
                getAliasObject(), args_,_context);
        newInstance(_context.getContext());
        _context.clearPages();
    }

    private CustList<RendDynOperationNode> newCall(String _varPrevious,String _previous,
                                                   ClassMethodId _id,
                                                   String _res,
                                                   StringMap<String> _args, Configuration _context) {
        CustList<RendDynOperationNode> ops_ = new CustList<RendDynOperationNode>();
        RendDotOperation dot_ = new RendDotOperation(0,new ClassArgumentMatching(_res),_args.size()+2);
        RendInternVariableOperation r_ = new RendInternVariableOperation(0,new ClassArgumentMatching(_previous),0,_varPrevious);
        ops_.add(r_);
        dot_.appendChild(r_);
        String id_ = StringExpUtil.getIdFromAllTypes(_id.getClassName());
        ExecRootBlock classBody_ = _context.getClasses().getClassBody(id_);
        CustList<ExecNamedFunctionBlock> list_ = ExecBlock.getMethodBodiesById(classBody_, _id.getConstraints());
        ExecNamedFunctionBlock fct_ = list_.first();
        RendFctOperation f_ = new RendFctOperation(new ClassArgumentMatching(_res),_id,1,_args.size()+1,fct_,classBody_);
        int i_ = 1;
        for (EntryCust<String,String> e: _args.entryList()) {
            RendInternVariableOperation a_ = new RendInternVariableOperation(i_-1,new ClassArgumentMatching(e.getValue()),i_,e.getKey());
            f_.appendChild(a_);
            ops_.add(a_);
            i_++;
        }
        dot_.appendChild(f_);
        r_.setSiblingSet(f_);
        ops_.add(f_);
        ops_.add(dot_);
        return ops_;
    }
    private void newInstance(ContextEl _context) {
        opsMap = new CustList<RendDynOperationNode>();
        String aliasStringMapObject_ = getAliasStringMapObject();
        ExecRootBlock ex_ = _context.getClasses().getClassBody(aliasStringMapObject_);
        opsMap.add(new RendStandardInstancingOperation(new ClassArgumentMatching(aliasStringMapObject_),new ConstructorId(aliasStringMapObject_,new StringList(),false),ex_));
    }

    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> files_ = super.buildFiles(_context);
        KeyWords keyWords_ = _context.getKeyWords();
        String public_ = keyWords_.getKeyWordPublic();
        String private_ = keyWords_.getKeyWordPrivate();
        String interface_ = keyWords_.getKeyWordInterface();
        String class_ = keyWords_.getKeyWordClass();
        String return_ = keyWords_.getKeyWordReturn();
        String if_ = keyWords_.getKeyWordIf();
        String for_ = keyWords_.getKeyWordFor();
        String null_ = keyWords_.getKeyWordNull();
        String new_ = keyWords_.getKeyWordNew();
        String int_ = getAliasPrimInteger();
        String endLine_ = String.valueOf(';');
        String suffixParam_ = "";
        StringBuilder file_ = new StringBuilder();
        file_.append(public_).append(" ").append(class_).append(" ").append(getAliasBean()).append("{");
        String string_ = getAliasString();
        String language_ = getAliasLanguage();
        String scope_ = getAliasScope();
        String dataBase_ = getAliasDataBaseField();
        String this_ = keyWords_.getKeyWordThis();
        String object_ = getAliasObject();
        String forms_ = getAliasForms();
        String boolean_ = getAliasPrimBoolean();
        String length_ = getAliasLength();
        file_.append(" ").append(private_).append(" ").append(string_).append(" ")
                .append(language_).append(endLine_);
        file_.append(" ").append(private_).append(" ").append(string_).append(" ")
                .append(scope_).append(endLine_);
        file_.append(" ").append(private_).append(" ").append(object_).append(" ")
                .append(dataBase_).append(endLine_);
        file_.append(" ").append(private_).append(" ").append(getAliasStringMapObject()).append(" ")
                .append(forms_).append(endLine_);
        String void_ = getAliasVoid();
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasBeforeDisplaying()).append("(){");
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(string_).append(" ")
                .append(getAliasGetLanguage()).append("(){");
        file_.append("  ").append(return_).append(" ").append(language_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetLanguage()).append("(").append(string_).append(" ").append(language_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(language_).append("=")
                .append(language_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(string_).append(" ")
                .append(getAliasGetScope()).append("(){");
        file_.append("  ").append(return_).append(" ").append(scope_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetScope()).append("(").append(string_).append(" ").append(scope_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(scope_).append("=")
                .append(scope_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(object_).append(" ")
                .append(getAliasGetDataBase()).append("(){");
        file_.append("  ").append(return_).append(" ").append(dataBase_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetDataBase()).append("(").append(object_).append(" ")
                .append(dataBase_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(dataBase_).append("=")
                .append(dataBase_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(getAliasStringMapObject())
                .append(" ").append(getAliasGetForms()).append("(){");
        file_.append("  ").append(return_).append(" ").append(forms_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetForms()).append("(").append(getAliasStringMapObject())
                .append(" ").append(forms_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(forms_).append("=")
                .append(forms_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append("}");
        files_.put(getAliasBean(), file_.toString());
        getPredefinedInterfacesInitOrder().add(getAliasBean());
        file_ = new StringBuilder();
        String keys_ = getAliasMapKeys();
        String values_ = getAliasMapValues();
        String indexOfEntry_ = getAliasMapIndexOfEntry();
        String addEntry_ = getAliasMapAddEntry();
        String getValue_ = getAliasMapGetValue();
        String setValue_ = getAliasMapSetValue();
        String put_ = getAliasMapPut();
        String putAll_ = getAliasMapPutAll();
        String getVal_ = getAliasMapGetVal();
        String removeKey_ = getAliasMapRemoveKey();
        String getKey_ = getAliasMapGetKey();
        String setKey_ = getAliasMapSetKey();
        file_.append(public_).append(" ").append(class_).append(" ").append(getAliasStringMapObject())
                .append("{");
        file_.append(private_).append(" ").append(string_).append("[] ").append(keys_)
                .append("=").append(new_).append(" ").append(string_).append("[0]").append(endLine_);
        file_.append(private_).append(" ").append(object_).append("[] ").append(values_)
                .append("=").append(new_).append(" ").append(object_).append("[0]").append(endLine_);
        file_.append(public_).append(" ").append(string_).append("[] ").append(keys_).append("(){");
        file_.append(return_).append(" ").append(keys_).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(aliasMapFirstKey).append("(){");
        file_.append(return_).append(" ").append(keys_).append("[0]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(aliasMapLastKey).append("(){");
        file_.append(return_).append(" ").append(keys_).append("[").append(keys_).append(".")
                .append(length_).append("-1]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append("[] ").append(values_).append("(){");
        file_.append(return_).append(" ").append(values_).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(aliasMapFirstValue).append("(){");
        file_.append(return_).append(" ").append(values_).append("[0]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(aliasMapLastValue).append("(){");
        file_.append(return_).append(" ").append(values_).append("[").append(values_)
                .append(".").append(length_).append("-1]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(setKey_).append("(")
                .append(int_).append(" ").append(tr("i",_context)).append(",").append(string_)
                .append(" ").append(tr("k",_context)).append("){");
        file_.append(this_).append(".").append(keys_).append("[").append(trParam("i",_context))
                .append("]=").append(trParam("k",_context)).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(getKey_)
                .append("(").append(int_).append(" ").append(tr("i",_context)).append("){");
        file_.append(return_).append(" ").append(this_).append(".").append(keys_).append("[")
                .append(trParam("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(setValue_)
                .append("(").append(int_).append(" ").append(tr("i",_context))
                .append(",").append(object_).append(" ").append(tr("v",_context)).append("){");
        file_.append(this_).append(".").append(values_).append("[").append(trParam("i",_context))
                .append("]=").append(trParam("v",_context)).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(getValue_).append("(")
                .append(int_).append(" ").append(tr("i",_context)).append("){");
        file_.append(return_).append(" ").append(this_).append(".").append(values_)
                .append("[").append(trParam("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(aliasMapClear).append("(){");
        file_.append(keys_).append("=").append(new_).append(" ").append(string_)
                .append("[0]").append(endLine_);
        file_.append(values_).append("=").append(new_).append(" ").append(object_)
                .append("[0]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(boolean_).append(" ").append(aliasMapIsEmpty).append("(){");
        file_.append(return_).append(" ").append(keys_).append(".").append(length_)
                .append("==0").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(int_).append(" ").append(aliasMapSize).append("(){");
        file_.append(return_).append(" ").append(keys_).append(".").append(length_).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ")
                .append(put_).append("(").append(string_).append(" ")
                .append(tr("k",_context)).append(",").append(object_)
                .append(" ").append(tr("v",_context)).append("){");
        file_.append(int_).append(" ").append(tr("index",_context)).append("=")
                .append(indexOfEntry_).append("(").append(trParam("k",_context)).append(")")
                .append(endLine_);
        file_.append(if_).append("(").append(trLoc("index",_context)).append("==-1){");
        file_.append(addEntry_).append("(").append(trParam("k",_context)).append(", ")
                .append(trParam("v",_context)).append(")").append(endLine_);
        file_.append(return_).append(endLine_);
        file_.append("}");
        file_.append(setValue_).append("(").append(trLoc("index",_context)).append(", ")
                .append(trParam("v",_context)).append(")").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(boolean_).append(" ").append(aliasMapContains)
                .append("(").append(string_).append(" ").append(tr("k",_context)).append("){");
        file_.append(return_).append(" ").append(indexOfEntry_).append("(")
                .append(trParam("k",_context)).append(") != -1").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(getVal_).append("(")
                .append(string_).append(" ").append(tr("k",_context)).append("){");
        file_.append(int_).append(" ").append(tr("index",_context)).append("=").append(indexOfEntry_)
                .append("(").append(trParam("k",_context)).append(")").append(endLine_);
        file_.append(if_).append("(").append(trLoc("index",_context)).append("==-1){");
        file_.append(return_).append(" ").append(null_).append(endLine_);
        file_.append("}");
        file_.append(return_).append(" ").append(getValue_).append("(").append(trLoc("index",_context))
                .append(")").append(endLine_);
        file_.append("}");

        file_.append(public_).append(" ").append(void_).append(" ")
                .append(addEntry_).append("(").append(string_).append(" ")
                .append(tr("k",_context)).append(",").append(object_)
                .append(" ").append(tr("v",_context)).append("){");
        file_.append(string_).append("[] ").append(keys_).append("=").append(new_)
                .append(" ").append(string_).append("[").append(this_)
                .append(".").append(keys_).append(".").append(length_)
                .append("+1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context))
                .append("=0").append(endLine_).append(trLoop("i",_context)).append("<")
                .append(this_).append(".")
                .append(keys_).append(".").append(length_).append(endLine_)
                .append(trLoop("i",_context)).append("++){");
        file_.append(trLoc(keys_,_context)).append("[").append(trLoop("i",_context))
                .append("]=").append(this_).append(".").append(keys_).append("[")
                .append(trLoop("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(trLoc(keys_,_context)).append("[").append(this_).append(".").append(keys_)
                .append(".").append(length_).append("]=").append(trParam("k",_context)).append(endLine_);
        file_.append(this_).append(".").append(keys_).append("=").append(trLoc(keys_,_context)).append(endLine_);
        file_.append(object_).append("[] ").append(values_).append("=").append(new_).append(" ")
                .append(object_).append("[").append(this_).append(".").append(values_).append(".")
                .append(length_).append("+1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context))
                .append("=0").append(endLine_).append(trLoop("i",_context)).append("<")
                .append(this_).append(".").append(values_).append(".").append(length_).append(endLine_)
                .append(trLoop("i",_context)).append("++){");
        file_.append(trLoc(values_,_context)).append("[").append(trLoop("i",_context))
                .append("]=").append(this_).append(".").append(values_).append("[")
                .append(trLoop("i",_context)).append("]").append(endLine_);
        file_.append("}");
        file_.append(trLoc(values_,_context)).append("[").append(this_).append(".").append(values_)
                .append(".").append(length_).append("]=").append(trParam("v",_context)).append(endLine_);
        file_.append(this_).append(".").append(values_).append("=").append(trLoc(values_,_context))
                .append(endLine_);
        file_.append("}");

        file_.append(public_).append(" ").append(int_).append(" ").append(indexOfEntry_)
                .append("(").append(string_).append(" ").append(tr("k",_context)).append("){");
        file_.append(for_).append("(").append(int_)
                .append(" ").append(tr("i",_context)).append("=0")
                .append(endLine_).append(trLoop("i",_context)).append("<").append(this_)
                .append(".").append(keys_).append(".").append(length_).append(endLine_)
                .append(trLoop("i",_context)).append("++){");
        file_.append(if_).append("(").append(this_).append(".").append(keys_).append("[").append(trLoop("i",_context)).append("]==").append(trParam("k",_context)).append("){");
        file_.append(return_).append(" ").append(trLoop("i",_context)).append(endLine_);
        file_.append("}");
        file_.append("}");
        file_.append(return_).append(" -1").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(putAll_).append("(")
                .append(getAliasStringMapObject()).append(" ").append(tr("m",_context)).append("){");
        file_.append(int_).append(" ").append(tr("len",_context)).append("=").append("(")
                .append(trParam("m",_context)).append(").").append(keys_).append(".")
                .append(length_).append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context))
                .append("=0").append(endLine_).append(trLoop("i",_context)).append("<")
                .append(trLoc("len",_context)).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(" ").append(put_).append("(").append("(")
                .append(trParam("m",_context)).append(").")
                .append(keys_).append("[").append(trLoop("i",_context))
                .append("], ").append("(").append(trParam("m",_context))
                .append(").").append(values_).append("[").append(trLoop("i",_context))
                .append("])").append(endLine_);
        file_.append("}");
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(removeKey_)
                .append("(").append(string_).append(" ").append(tr("k",_context)).append("){");
        file_.append(int_).append(" ").append(tr("index",_context))
                .append("=").append(indexOfEntry_).append("(").append(trParam("k",_context))
                .append(")").append(endLine_);
        file_.append(if_).append("(").append(trLoc("index",_context)).append("==-1){");
        file_.append(return_).append(endLine_);
        file_.append("}");
        file_.append(string_).append("[] ").append(keys_).append("=").append(new_)
                .append(" ").append(string_).append("[").append(this_).append(".").append(keys_)
                .append(".").append(length_).append("-1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=0")
                .append(endLine_).append(trLoop("i",_context))
                .append("<").append(trLoc("index",_context))
                .append(endLine_).append(trLoop("i",_context))
                .append("++){");
        file_.append(keys_).append("[").append(trLoop("i",_context)).append("]=")
                .append(this_).append(".").append(keys_).append("[").append(trLoop("i",_context))
                .append("]").append(endLine_);
        file_.append("}");
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context))
                .append("=").append(trLoc("index",_context)).append("+1").append(endLine_)
                .append(trLoop("i",_context)).append("<").append(this_).append(".")
                .append(keys_).append(".").append(length_).append(endLine_)
                .append(trLoop("i",_context)).append("++){");
        file_.append(keys_).append("[").append(trLoop("i",_context)).append("-1]=").append(this_)
                .append(".").append(keys_).append("[").append(trLoop("i",_context)).append("]")
                .append(endLine_);
        file_.append("}");
        file_.append(this_).append(".").append(keys_).append("=").append(keys_).append(endLine_);

        file_.append(object_).append("[] ").append(values_).append("=").append(new_).append(" ")
                .append(object_).append("[").append(this_).append(".")
                .append(values_).append(".").append(length_).append("-1]")
                .append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context)).append("=0")
                .append(endLine_).append(trLoop("i",_context))
                .append("<").append(trLoc("index",_context))
                .append(endLine_).append(trLoop("i",_context))
                .append("++){");
        file_.append(values_).append("[").append(trLoop("i",_context)).append("]=")
                .append(this_).append(".").append(values_).append("[").append(trLoop("i",_context))
                .append("]").append(endLine_);
        file_.append("}");
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i",_context))
                .append("=").append(trLoc("index",_context)).append("+1").append(endLine_)
                .append(trLoop("i",_context)).append("<").append(this_).append(".").append(values_)
                .append(".").append(length_).append(endLine_).append(trLoop("i",_context)).append("++){");
        file_.append(values_).append("[").append(trLoop("i",_context)).append("-1]=")
                .append(this_).append(".").append(values_).append("[").append(trLoop("i",_context))
                .append("]").append(endLine_);
        file_.append("}");
        file_.append(this_).append(".").append(values_).append("=").append(values_).append(endLine_);
        file_.append("}");
        file_.append("}");
        files_.put(getAliasStringMapObject(), file_.toString());
        getPredefinedInterfacesInitOrder().add(getAliasStringMapObject());
        file_ = new StringBuilder();
        file_.append(public_).append(" ").append(interface_).append(" ")
                .append(aliasValidator).append("{");
        file_.append(public_).append(" ").append(getAliasMessage()).append(" ")
                .append(aliasValidate).append("(");
        file_.append(object_).append(" ").append(tr("newValue",_context)).append(",");
        file_.append(object_).append(" ").append(tr("oldValue",_context)).append(",");
        file_.append(object_).append(" ").append(tr("bean",_context)).append(",");
        file_.append(object_).append("[] ").append(tr("form",_context)).append(",");
        file_.append(string_).append(" ").append(tr("className",_context)).append(",");
        file_.append(string_).append(" ").append(tr("fieldName",_context));
        file_.append(")");
        file_.append(endLine_);
        file_.append("}");
        files_.put(aliasValidator, file_.toString());
        getPredefinedInterfacesInitOrder().add(aliasValidator);
        return files_;
    }

    private static String tr(StringList _list) {
        String candidate_ = "tmp";
        int index_ = 0;
        while (StringList.contains(_list,candidate_)) {
            candidate_ = StringList.concatNbs("tmp",index_);
            index_++;
        }
        _list.add(candidate_);
        return candidate_;
    }
    private static String tr(String _var, ContextEl _context) {
        CustList<String> allKeysWords_ = _context.getKeyWords().allKeyWords().values();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
        return getCandidate(_var, allKeysWords_);
    }
    private static String trLoop(String _var, ContextEl _context) {
        CustList<String> allKeysWords_ = _context.getKeyWords().allKeyWords().values();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
        return getCandidate(_var, allKeysWords_);
    }
    private static String trParam(String _var, ContextEl _context) {
        CustList<String> allKeysWords_ = _context.getKeyWords().allKeyWords().values();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
        return getCandidate(_var, allKeysWords_);
    }
    private static String trLoc(String _var, ContextEl _context) {
        CustList<String> allKeysWords_ = _context.getKeyWords().allKeyWords().values();
        allKeysWords_.addAllElts(_context.getStandards().getPrimitiveTypes().getKeys());
        allKeysWords_.add(_context.getStandards().getAliasVoid());
        return getCandidate(_var, allKeysWords_);
    }

    private static String getCandidate(String _var, CustList<String> allKeysWords_) {
        String candidate_ = _var;
        int index_ = 0;
        while (StringList.contains(allKeysWords_,candidate_)) {
            candidate_ = StringList.concatNbs(_var,index_);
            index_++;
        }
        return candidate_;
    }

    @Override
    public ReportedMessages setupAll(Navigation _nav, Configuration _conf, StringMap<String> _files) {
        setupRendClasses(_conf,_files);
        _nav.initInstancesPattern();
        _nav.setupRenders();
        ReportedMessages messages_ = _conf.getContext().getAnalyzing().getMessages();
        if (!messages_.isEmptyErrors()) {
            return messages_;
        }
        Classes.forwardAndClear(_conf.getContext());
        buildIterables(_conf);
        AnalysisMessages analysisMessages_ = _conf.getContext().getAnalyzing().getAnalysisMessages();
        Options options_ = _conf.getContext().getAnalyzing().getOptions();
        _conf.getContext().setNullAnalyzing();
        _conf.getContext().setFullStack(new DefaultFullStack(_conf.getContext()));
        Classes.tryInitStaticlyTypes(_conf.getContext(),analysisMessages_,messages_, options_);
        _conf.getContext().setFullStack(new AdvancedFullStack(_conf));
        return messages_;
    }

    private void setupRendClasses(Configuration _conf, StringMap<String> _files) {
        String conf_ = _conf.getFilesConfName();
        StringList content_ = new StringList();
        for (EntryCust<String, String> e: _files.entryList()) {
            if (StringList.quickEq(e.getKey(),conf_)) {
                content_ = StringList.splitStrings(e.getValue(), RETURN_LINE);
                break;
            }
        }
        StringMap<String> classFiles_ = new StringMap<String>();
        for (String f: content_) {
            for (EntryCust<String, String> e: _files.entryList()) {
                if (StringList.quickEq(e.getKey(), f)) {
                    classFiles_.put(f, e.getValue());
                    break;
                }
            }
        }
        //!classFiles_.isEmpty()
        Classes.validateWithoutInit(classFiles_, _conf.getContext());
    }
    @Override
    public void preInitBeans(Configuration _conf) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            _conf.getBuiltBeans().addEntry(e.getKey(),NullStruct.NULL_VALUE);
        }
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateValidators().entryList()) {
            _conf.getBuiltValidators().addEntry(e.getKey(),NullStruct.NULL_VALUE);
        }
    }

    @Override
    public void initBeans(Configuration _conf,String _language,Struct _db) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            _conf.addPage(new ImportingPage());
            Argument arg_ = RenderExpUtil.calculateReuse(info_.getExps(), _conf);
            if (_conf.getContext().hasException()) {
                _conf.removeLastPage();
                return;
            }
            Struct strBean_ = arg_.getStruct();
            String clName_ = strBean_.getClassName(_conf.getContext());
            if (!ExecTemplates.isCorrectExecute(clName_,getAliasBean(),_conf.getContext())) {
                _conf.removeLastPage();
                _conf.getBuiltBeans().setValue(index_,strBean_);
                index_++;
                continue;
            }
            Argument mapArg_ = RenderExpUtil.calculateReuse(opsMap, _conf);
            ExecRootBlock rootBlock_ = _conf.getContext().getClasses().getClassBody(getAliasBean());
            ExecInvokingOperation.setInstanceField(rootBlock_,getAliasBean(),getAliasForms(),getAliasStringMapObject(),
                    new Argument(strBean_),mapArg_, _conf.getContext());
            ExecInvokingOperation.setInstanceField(rootBlock_,getAliasBean(),getAliasDataBaseField(),getAliasObject(),
                    new Argument(strBean_),new Argument(_db), _conf.getContext());
            ExecInvokingOperation.setInstanceField(rootBlock_,getAliasBean(),getAliasLanguage(),getAliasString(),
                    new Argument(strBean_),new Argument(new StringStruct(_language)), _conf.getContext());
            ExecInvokingOperation.setInstanceField(rootBlock_,getAliasBean(),getAliasScope(),getAliasString(),
                    new Argument(strBean_),new Argument(new StringStruct(info_.getScope())), _conf.getContext());
            _conf.removeLastPage();
            _conf.getBuiltBeans().setValue(index_,strBean_);
            index_++;
        }
        index_ = 0;
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateValidators().entryList()) {
            ValidatorInfo info_ = e.getValue();
            _conf.addPage(new ImportingPage());
            Argument arg_ = RenderExpUtil.calculateReuse(info_.getExps(), _conf);
            _conf.removeLastPage();
            if (_conf.getContext().hasException()) {
                return;
            }
            Struct strBean_ = arg_.getStruct();
            _conf.getBuiltValidators().setValue(index_,strBean_);
            index_++;
        }
    }

    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        StringList list_ = _method.getConstraints().getParametersTypes();
        String type_ = _method.getClassName();
        if (StringList.quickEq(type_, getAliasEnums())) {
            return super.getOtherResult(_cont,_instance,_method,_args);
        }
        String name_ = _method.getConstraints().getName();
        MessageStruct instance_ = getMessageStruct(_instance);
        if (StringList.quickEq(name_, aliasNewMessage)) {
            if (list_.isEmpty()) {
                res_.setResult(MessageStruct.newInstance(Message.newStandardMessage(),aliasMessage));
            } else {
                String value_ = AnaApplyCoreMethodUtil.getString(_args[0]).getInstance();
                res_.setResult(MessageStruct.newInstance(Message.newStandardMessage(value_),aliasMessage));
            }
            return res_;
        }
        if (StringList.quickEq(name_, aliasMessageFormat)) {
            res_.setResult(wrapStd(instance_.getMessage()));
            return res_;
        }
        if (StringList.quickEq(name_, aliasMessageGetArgs)) {
            StringList resArgs_ = instance_.getArgs();
            String arrStr_ = StringExpUtil.getPrettyArrayType(getAliasString());
            int len_ = resArgs_.size();
            ArrayStruct arr_ = new ArrayStruct(new Struct[len_],arrStr_);
            for (int i = 0; i < len_; i++){
                arr_.getInstance()[i] = wrapStd(resArgs_.get(i));
            }
            res_.setResult(arr_);
            return res_;
        }
        Struct[] argsInst_ = ExecArrayFieldOperation.getArray(_args[0],_cont).getInstance();
        int len_ = argsInst_.length;
        String[] resArgs_ = new String[len_];
        for (int i = 0; i < len_; i++){
            Struct argInst_ = argsInst_[i];
            if (argInst_ instanceof StringStruct) {
                resArgs_[i] = ((StringStruct)argInst_).getInstance();
            } else {
                resArgs_[i] = _cont.getStandards().getDisplayedStrings().getNullString();
            }
        }
        instance_.setArgs(resArgs_);
        res_.setResult(NullStruct.NULL_VALUE);
        return res_;
    }
    private MessageStruct getMessageStruct(Struct _str) {
        if (_str instanceof MessageStruct) {
            return (MessageStruct) _str;
        }
        return MessageStruct.newInstance(Message.newStandardMessage(),aliasMessage);
    }

    @Override
    public void forwardDataBase(Struct _bean, Struct _to, Configuration _conf) {
        _conf.getLastPage().putInternVars(getDataBaseVar, _bean,_conf.getContext());
        _conf.getLastPage().setEnabledOp(false);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetDataBase, _conf);
        _conf.getLastPage().clearInternVars();
        if (_conf.getContext().hasException()) {
            _conf.getLastPage().setEnabledOp(true);
            return;
        }
        _conf.getLastPage().putInternVars(setDataBaseVar, _to,_conf.getContext());
        _conf.getLastPage().putInternVars(setDataBaseVarArg, argument_.getStruct(),_conf.getContext());
        RenderExpUtil.calculateReuse(expsSetDataBase, _conf);
        _conf.getLastPage().setEnabledOp(true);
        _conf.getLastPage().clearInternVars();
    }

    @Override
    public void storeForms(Struct _bean, Configuration _conf) {
        Argument forms_ = getForms(_bean, _conf);
        _conf.getLastPage().setEnabledOp(true);
        if (_conf.getContext().hasException()) {
            return;
        }
        storedForms = forms_.getStruct();
    }

    public Argument getForms(Struct _bean, Configuration _conf) {
        String clName_ = _bean.getClassName(_conf.getContext());
        _conf.getLastPage().setEnabledOp(false);
        if (!ExecTemplates.isCorrectExecute(clName_,getAliasBean(),_conf.getContext())) {
            return RenderExpUtil.calculateReuse(opsMap, _conf);
        }
        _conf.getLastPage().putInternVars(getFormsVar, _bean,_conf.getContext());
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetForms, _conf);
        _conf.getLastPage().clearInternVars();
        if (_conf.getContext().hasException()) {
            return argument_;
        }
        if (argument_.isNull()) {
            return RenderExpUtil.calculateReuse(opsMap, _conf);
        }
        return argument_;
    }

    @Override
    public void setStoredForms(Struct _bean, Configuration _conf) {
        _conf.getLastPage().setEnabledOp(false);
        _conf.getLastPage().putInternVars(setFormsVar, _bean,_conf.getContext());
        _conf.getLastPage().putInternVars(setFormsVarArg, storedForms,_conf.getContext());
        RenderExpUtil.calculateReuse(expsSetForms, _conf);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
    }

    @Override
    protected void gearFw(Configuration _conf, Struct _mainBean, RendImport _node, boolean _keepField, Struct _bean) {
        Argument forms_ = getForms(_bean, _conf);
        if (_conf.getContext().hasException()) {
            _conf.getLastPage().setEnabledOp(true);
            return;
        }
        Argument formsMap_ = getForms(_mainBean,_conf);
        if (_conf.getContext().hasException()) {
            _conf.getLastPage().setEnabledOp(true);
            return;
        }
        if (_keepField) {
            for (RendBlock f_: RendBlock.getDirectChildren(_node)) {
                if (!(f_ instanceof RendImportForm)) {
                    continue;
                }
                String name_ = ((RendImportForm)f_).getName();
                forwardMap(formsMap_.getStruct(),forms_.getStruct(),new StringStruct(name_),_conf);
                if (_conf.getContext().hasException()) {
                    _conf.getLastPage().setEnabledOp(true);
                    return;
                }
            }
        } else {
            //add option for copying forms (default copy)
            putAllMap(forms_.getStruct(),formsMap_.getStruct(),_conf);
        }
        _conf.getLastPage().setEnabledOp(true);
    }

    @Override
    protected void specificLoad(Configuration _configuration, String _lgCode, Document _document) {
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "lateValidators")) {
                _configuration.setLateValidators(ReadConfiguration.loadStringMapString(c));
                continue;
            }
            if (StringList.quickEq(fieldName_, "tabWidth")) {
                _configuration.setTabWidth(Numbers.parseInt(c.getAttribute("value")));
                continue;
            }
            if (StringList.quickEq(fieldName_, "filesConfName")) {
                _configuration.setFilesConfName(c.getAttribute("value"));
                continue;
            }
            if (StringList.quickEq(fieldName_, "context")) {
                ReadConfiguration.loadContext(c, _lgCode, this,_configuration);
            }
        }
    }

    @Override
    public Argument getCommonArgument(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf) {
        int off_ =_rend.getOff();
        ClassField fieldId_ = _rend.getFieldMetaInfo().getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        boolean staticField_ = _rend.getFieldMetaInfo().isStaticField();
        Argument previous_;
        if (!staticField_) {
            previous_ = new Argument(ExecTemplates.getParent(_rend.getAnc(), className_, _previous.getStruct(), _conf.getContext()));
        } else {
            previous_ = new Argument();
        }
        String fieldType_ = _rend.getFieldMetaInfo().getRealType();
        Argument arg_ = getField(_conf, off_, className_, fieldName_, staticField_, previous_, fieldType_);
        CallingState state_ = _conf.getContext().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(),statusInit_.getRootBlock(), _conf.getContext());
            arg_ = getField(_conf, off_, className_, fieldName_, staticField_, previous_, fieldType_);
        }
        return arg_;
    }

    private static Argument getField(Configuration _conf, int off_, String className_, String fieldName_, boolean staticField_, Argument previous_, String fieldType_) {
        if (_conf.getContext().hasException()) {
            return Argument.createVoid();
        }
        return ExecInvokingOperation.getField(new AdvancedSetOffset(_conf), new AdvancedExiting(_conf), className_, fieldName_, staticField_, fieldType_, previous_, _conf.getContext(), off_);
    }

    @Override
    public Argument getCommonSetting(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf, Argument _right) {
        int off_ = _rend.getOff();
        String fieldType_ = _rend.getFieldMetaInfo().getRealType();
        boolean isStatic_ = _rend.getFieldMetaInfo().isStaticField();
        boolean isFinal_ = _rend.getFieldMetaInfo().isFinalField();
        ClassField fieldId_ = _rend.getFieldMetaInfo().getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        Argument previous_;
        if (!isStatic_) {
            previous_ = new Argument(ExecTemplates.getParent(_rend.getAnc(), className_, _previous.getStruct(), _conf.getContext()));
        } else {
            previous_ = new Argument();
        }
        //Come from code directly so constant static fields can be initialized here
        Argument arg_ = setField(_conf, _right, off_, fieldType_, isStatic_, isFinal_, _rend.getRootBlock(), className_, fieldName_, previous_);
        CallingState state_ = _conf.getContext().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(),statusInit_.getRootBlock(), _conf.getContext());
            arg_ = setField(_conf, _right, off_, fieldType_, isStatic_, isFinal_, _rend.getRootBlock(), className_, fieldName_, previous_);
        }
        return arg_;
    }

    private static Argument setField(Configuration _conf, Argument _right, int off_, String fieldType_, boolean isStatic_, boolean isFinal_, ExecRootBlock _root, String className_, String fieldName_, Argument previous_) {
        if (_conf.getContext().hasException()) {
            return Argument.createVoid();
        }
        return ExecInvokingOperation.setField(new AdvancedSetOffset(_conf), new AdvancedExiting(_conf), _root,className_, fieldName_, isStatic_, isFinal_, false, fieldType_, previous_, _right, _conf.getContext(), off_);
    }

    @Override
    public Argument getCommonFctArgument(RendStdFctOperation _rend, Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(_rend.getMethodName());
        _rend.setRelativeOffsetPossibleLastPage(_rend.getIndexInEl()+off_, _conf);
        ContextEl ctx_ = _conf.getContext();
        MethodId methodId_ = _rend.getClassMethodId().getConstraints();
        String lastType_ = _rend.getLastType();
        String classNameFound_;
        Argument prev_;
        if (!_rend.isStaticMethod()) {
            classNameFound_ = _rend.getClassMethodId().getClassName();
            Struct argPrev_ = _previous.getStruct();
            prev_ = new Argument(ExecTemplates.getParent(0, classNameFound_, argPrev_, ctx_));
            if (ctx_.hasException()) {
                return new Argument();
            }
        } else {
            prev_ = new Argument();
        }
        classNameFound_ = _rend.getClassMethodId().getClassName();
        CustList<RendDynOperationNode> chidren_ = _rend.getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, _rend.getNaturalVararg(), lastType_, first_);
        return ExecInvokingOperation.callStd(new AdvancedExiting(_conf),ctx_, classNameFound_, methodId_, prev_, firstArgs_, null);
    }

    private void forwardMap(Struct _map, Struct _to, Struct _key, Configuration _conf) {
        _conf.getLastPage().putInternVars(getValVar, _map,_conf.getContext());
        _conf.getLastPage().putInternVars(getValVarArg, _key,_conf.getContext());
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetVal, _conf);
        _conf.getLastPage().clearInternVars();
        if (_conf.getContext().hasException()) {
            return;
        }
        _conf.getLastPage().putInternVars(putVarCust, _to, _conf.getContext());
        _conf.getLastPage().putInternVars(putVarCustKey, _key,_conf.getContext());
        _conf.getLastPage().putInternVars(putVarCustValue, argument_.getStruct(), _conf.getContext());
        RenderExpUtil.calculateReuse(expsPut, _conf);
        _conf.getLastPage().clearInternVars();
    }

    public void putAllMap(Struct _map, Struct _other, Configuration _conf) {
        _conf.getLastPage().putInternVars(putAllVarCust, _map,_conf.getContext());
        _conf.getLastPage().putInternVars(putAllVarCustArg, _other,_conf.getContext());
        RenderExpUtil.calculateReuse(expsPutAll, _conf);
        _conf.getLastPage().clearInternVars();
    }

    private String getIteratorVar() {
        return iteratorVar;
    }

    private String getHasNextVar() {
        return hasNextVar;
    }

    private String getNextVar() {
        return nextVar;
    }

    private CustList<RendDynOperationNode> getExpsIterator() {
        return expsIterator;
    }

    private CustList<RendDynOperationNode> getExpsHasNext() {
        return expsHasNext;
    }

    private CustList<RendDynOperationNode> getExpsNext() {
        return expsNext;
    }

    private String getIteratorTableVarCust() {
        return iteratorTableVarCust;
    }

    private String getHasNextPairVarCust() {
        return hasNextPairVarCust;
    }

    private String getNextPairVarCust() {
        return nextPairVarCust;
    }

    private String getFirstVarCust() {
        return firstVarCust;
    }

    private String getSecondVarCust() {
        return secondVarCust;
    }

    private String getBeforeDisplayingVar() {
        return beforeDisplayingVar;
    }

    private CustList<RendDynOperationNode> getExpsIteratorTableCust() {
        return expsIteratorTableCust;
    }

    private CustList<RendDynOperationNode> getExpsHasNextPairCust() {
        return expsHasNextPairCust;
    }

    private CustList<RendDynOperationNode> getExpsNextPairCust() {
        return expsNextPairCust;
    }

    private CustList<RendDynOperationNode> getExpsFirstCust() {
        return expsFirstCust;
    }

    private CustList<RendDynOperationNode> getExpsSecondCust() {
        return expsSecondCust;
    }

    @Override
    public Message validate(Configuration _conf, NodeContainer _cont, String _validatorId) {
        Struct validator_ = _conf.getBuiltValidators().getVal(_validatorId);
        if (validator_ == null) {
            return null;
        }
        return validate(_conf,_cont,validator_);
    }

    public Message validate(Configuration _conf, NodeContainer _cont, Struct _validator) {
        LocalVariable locVar_;
        _conf.getLastPage().putInternVars(validateVar, _validator,_conf.getContext());
        locVar_ = newLocVar(_cont,_conf);
        _conf.getLastPage().putInternVars(validateVarArgNewValue, locVar_);
        locVar_ = LocalVariable.newLocalVariable(_cont.getTypedStruct(),getAliasObject());
        _conf.getLastPage().putInternVars(validateVarArgOldValue, locVar_);
        locVar_ = LocalVariable.newLocalVariable(_cont.getBean(),getAliasObject());
        _conf.getLastPage().putInternVars(validateVarArgBean, locVar_);
        CustList<Struct> params_ = _cont.getStructParam();
        int size_ = params_.size();
        ArrayStruct arr_ = new ArrayStruct(new Struct[size_ +1],StringExpUtil.getPrettyArrayType(getAliasObject()));
        arr_.getInstance()[0] = _cont.getUpdated();
        for (int i = 0; i < size_; i++) {
            arr_.getInstance()[i+1] = params_.get(i);
        }
        locVar_ = LocalVariable.newLocalVariable(arr_,StringExpUtil.getPrettyArrayType(getAliasObject()));
        _conf.getLastPage().putInternVars(validateVarArgForm, locVar_);
        _conf.getLastPage().putInternVars(validateVarArgClassField, new StringStruct(_cont.getIdFieldClass()),_conf.getContext());
        _conf.getLastPage().putInternVars(vlidateVarArgNameField, new StringStruct(_cont.getIdFieldName()),_conf.getContext());
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(expsValidate, _conf);
        _conf.getLastPage().setEnabledOp(true);
        _conf.getLastPage().clearInternVars();
        if (_conf.getContext().hasException()) {
            return null;
        }
        if (arg_.isNull()) {
            return null;
        }
        return getMessageStruct(arg_.getStruct()).getInstance();
    }
    @Override
    public String getStringKey(Configuration _conf, Struct _instance) {
        if (_instance instanceof EnumerableStruct) {
            return ((EnumerableStruct) _instance).getName();
        }
        return processString(new Argument(_instance),_conf);
    }

    @Override
    public void beforeDisplaying(Struct _arg, Configuration _cont) {
        String clName_ = getStructClassName(_arg, _cont.getContext());
        if (!ExecTemplates.isCorrectExecute(clName_,getAliasBean(),_cont.getContext())) {
            return;
        }
        String locName_ = getBeforeDisplayingVar();
        _cont.getLastPage().putInternVars(locName_, _arg,_cont.getContext());
        _cont.getLastPage().setEnabledOp(false);
        RenderExpUtil.calculateReuse(expsBeforeDisplaying,_cont);
        _cont.getLastPage().setEnabledOp(true);
        _cont.getLastPage().clearInternVars();
    }

    public String getScope(Struct _bean, Configuration _cont) {
        _cont.getLastPage().putInternVars(getScopeVar, _bean,_cont.getContext());
        _cont.getLastPage().setEnabledOp(false);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetScope, _cont);
        _cont.getLastPage().setEnabledOp(true);
        _cont.getLastPage().clearInternVars();
        if (_cont.getContext().hasException() || argument_.isNull()) {
            return "";
        }
        return AnaApplyCoreMethodUtil.getString(argument_.getStruct()).getInstance();
    }
    public void setScope(Struct _bean, String _scope,Configuration _cont) {
        _cont.getLastPage().putInternVars(setScopeVar, _bean,_cont.getContext());
        _cont.getLastPage().putInternVars(setScopeVarArg, new StringStruct(_scope),_cont.getContext());
        _cont.getLastPage().setEnabledOp(false);
        RenderExpUtil.calculateReuse(expsSetScope, _cont);
        _cont.getLastPage().setEnabledOp(true);
        _cont.getLastPage().clearInternVars();
    }
    public void setLanguage(Struct _bean, String _scope,Configuration _cont) {
        _cont.getLastPage().putInternVars(setLanguageVar, _bean,_cont.getContext());
        _cont.getLastPage().putInternVars(setLanguageVarArg, new StringStruct(_scope),_cont.getContext());
        _cont.getLastPage().setEnabledOp(false);
        RenderExpUtil.calculateReuse(expsSetLanguage, _cont);
        _cont.getLastPage().setEnabledOp(true);
        _cont.getLastPage().clearInternVars();
    }

    @Override
    public Argument iteratorMultTable(Struct _arg, Configuration _cont) {
        String locName_ = getIteratorTableVarCust();
        _cont.getLastPage().putInternVars(locName_, _arg,_cont.getContext());
        _cont.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsIteratorTableCust(), _cont);
        _cont.getLastPage().clearInternVars();
        _cont.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument hasNextPair(Struct _arg, Configuration _conf) {
        String locName_ = getHasNextPairVarCust();
        _conf.getLastPage().putInternVars(locName_, _arg,_conf.getContext());
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsHasNextPairCust(),_conf);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument nextPair(Struct _arg, Configuration _conf) {
        String locName_ = getNextPairVarCust();
        _conf.getLastPage().putInternVars(locName_, _arg,_conf.getContext());
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsNextPairCust(), _conf);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument first(Struct _arg, Configuration _conf) {
        String locName_ = getFirstVarCust();
        _conf.getLastPage().putInternVars(locName_, _arg,_conf.getContext());
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsFirstCust(), _conf);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument second(Struct _arg, Configuration _conf) {
        String locName_ = getSecondVarCust();
        _conf.getLastPage().putInternVars(locName_, _arg,_conf.getContext());
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsSecondCust(), _conf);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument iterator(Struct _arg, Configuration _cont) {
        String locName_ = getIteratorVar();
        _cont.getLastPage().putInternVars(locName_, _arg,_cont.getContext());
        _cont.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsIterator(), _cont);
        _cont.getLastPage().clearInternVars();
        _cont.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument next(Struct _arg, Configuration _cont) {
        String locName_ = getNextVar();
        _cont.getLastPage().putInternVars(locName_, _arg,_cont.getContext());
        _cont.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsNext(), _cont);
        _cont.getLastPage().clearInternVars();
        _cont.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument hasNext(Struct _arg, Configuration _cont) {
        String locName_ = getHasNextVar();
        _cont.getLastPage().putInternVars(locName_, _arg,_cont.getContext());
        _cont.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsHasNext(), _cont);
        _cont.getLastPage().clearInternVars();
        _cont.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public String processString(Argument _arg, Configuration _cont) {
        Argument arg_ = RendDynOperationNode.processString(_arg, _cont);
        if (_cont.getContext().hasException()) {
            return "";
        }
        return AnaApplyCoreMethodUtil.getString(arg_.getStruct()).getInstance();
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> types_ = super.allRefTypes();
        types_.addEntry(MESSAGE,getAliasMessage());
        types_.addEntry(VALIDATOR,getAliasValidator());
        types_.addEntry(STRING_MAP_OBJECT,getAliasStringMapObject());
        types_.addEntry(BEAN,getAliasBean());
        return types_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> methods_ = super.allTableTypeMethodNames();
        methods_.addEntry(getAliasMessage(),
                new CustList<KeyValueMemberName>(new KeyValueMemberName(NEW_MESSAGE,getAliasNewMessage()),
                        new KeyValueMemberName(MESSAGE_FORMAT,getAliasMessageFormat()),
                        new KeyValueMemberName(MESSAGE_GET_ARGS,getAliasMessageGetArgs()),
                        new KeyValueMemberName(MESSAGE_SET_ARGS,getAliasMessageSetArgs())));
        methods_.addEntry(getAliasValidator(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(VALIDATE,getAliasValidate())));
        methods_.addEntry(getAliasStringMapObject(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(MAP_GET_VAL,getAliasMapGetVal()),
                new KeyValueMemberName(MAP_PUT,getAliasMapPut()),
                new KeyValueMemberName(MAP_PUT_ALL,getAliasMapPutAll()),
                new KeyValueMemberName(MAP_INDEX_OF_ENTRY,getAliasMapIndexOfEntry()),
                new KeyValueMemberName(MAP_ADD_ENTRY,getAliasMapAddEntry()),
                new KeyValueMemberName(MAP_CONTAINS,getAliasMapContains()),
                new KeyValueMemberName(MAP_SIZE,getAliasMapSize()),
                new KeyValueMemberName(MAP_IS_EMPTY,getAliasMapIsEmpty()),
                new KeyValueMemberName(MAP_CLEAR,getAliasMapClear()),
                new KeyValueMemberName(MAP_REMOVE_KEY,getAliasMapRemoveKey()),
                new KeyValueMemberName(MAP_FIRST_VALUE,getAliasMapFirstValue()),
                new KeyValueMemberName(MAP_LAST_VALUE,getAliasMapLastValue()),
                new KeyValueMemberName(MAP_GET_VALUE,getAliasMapGetValue()),
                new KeyValueMemberName(MAP_SET_VALUE,getAliasMapSetValue()),
                new KeyValueMemberName(MAP_FIRST_KEY,getAliasMapFirstKey()),
                new KeyValueMemberName(MAP_LAST_KEY,getAliasMapLastKey()),
                new KeyValueMemberName(MAP_GET_KEY,getAliasMapGetKey()),
                new KeyValueMemberName(MAP_SET_KEY,getAliasMapSetKey())
        ));
        methods_.addEntry(getAliasBean(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BEFORE_DISPLAYING,getAliasBeforeDisplaying()),
                new KeyValueMemberName(GET_DATA_BASE,getAliasGetDataBase()),
                new KeyValueMemberName(GET_LANGUAGE,getAliasGetLanguage()),
                new KeyValueMemberName(GET_SCOPE,getAliasGetScope()),
                new KeyValueMemberName(GET_FORMS,getAliasGetForms()),
                new KeyValueMemberName(SET_DATA_BASE,getAliasSetDataBase()),
                new KeyValueMemberName(SET_LANGUAGE,getAliasSetLanguage()),
                new KeyValueMemberName(SET_SCOPE,getAliasSetScope()),
                new KeyValueMemberName(SET_FORMS,getAliasSetForms())
        ));
        return methods_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> fields_ = super.allTableTypeFieldNames();
        fields_.addEntry(getAliasMessage(),
                new CustList<KeyValueMemberName>(
                        new KeyValueMemberName(MAP_KEYS,getAliasMapKeys()),
                        new KeyValueMemberName(MAP_VALUES,getAliasMapValues())));
        fields_.addEntry(getAliasBean(),
                new CustList<KeyValueMemberName>(
                        new KeyValueMemberName(FORMS,getAliasForms()),
                        new KeyValueMemberName(LANGUAGE,getAliasLanguage()),
                        new KeyValueMemberName(DATA_BASE_FIELD,getAliasDataBaseField()),
                        new KeyValueMemberName(SCOPE,getAliasScope())));
        return fields_;
    }

    public String getAliasMapKeys() {
        return aliasMapKeys;
    }

    public void setAliasMapKeys(String _aliasMapKeys) {
        aliasMapKeys = _aliasMapKeys;
    }

    public String getAliasMapValues() {
        return aliasMapValues;
    }

    public void setAliasMapValues(String _aliasMapValues) {
        aliasMapValues = _aliasMapValues;
    }

    public String getAliasMapIndexOfEntry() {
        return aliasMapIndexOfEntry;
    }

    public void setAliasMapIndexOfEntry(String _aliasMapIndexOfEntry) {
        aliasMapIndexOfEntry = _aliasMapIndexOfEntry;
    }

    public String getAliasMapAddEntry() {
        return aliasMapAddEntry;
    }

    public void setAliasMapAddEntry(String _aliasMapAddEntry) {
        aliasMapAddEntry = _aliasMapAddEntry;
    }

    public String getAliasMapGetValue() {
        return aliasMapGetValue;
    }

    public void setAliasMapGetValue(String _aliasMapGetValue) {
        aliasMapGetValue = _aliasMapGetValue;
    }

    public String getAliasMapFirstValue() {
        return aliasMapFirstValue;
    }

    public void setAliasMapFirstValue(String _aliasMapFirstValue) {
        aliasMapFirstValue = _aliasMapFirstValue;
    }

    public String getAliasMapLastValue() {
        return aliasMapLastValue;
    }

    public void setAliasMapLastValue(String _aliasMapLastValue) {
        aliasMapLastValue = _aliasMapLastValue;
    }

    public String getAliasMapSetValue() {
        return aliasMapSetValue;
    }

    public void setAliasMapSetValue(String _aliasMapSetValue) {
        aliasMapSetValue = _aliasMapSetValue;
    }

    public String getAliasMapPut() {
        return aliasMapPut;
    }

    public void setAliasMapPut(String _aliasMapPut) {
        aliasMapPut = _aliasMapPut;
    }

    public String getAliasMapContains() {
        return aliasMapContains;
    }

    public void setAliasMapContains(String _aliasMapContains) {
        aliasMapContains = _aliasMapContains;
    }

    public String getAliasMapPutAll() {
        return aliasMapPutAll;
    }

    public void setAliasMapPutAll(String _aliasMapPutAll) {
        aliasMapPutAll = _aliasMapPutAll;
    }

    public String getAliasMapGetVal() {
        return aliasMapGetVal;
    }

    public void setAliasMapGetVal(String _aliasMapGetVal) {
        aliasMapGetVal = _aliasMapGetVal;
    }

    public String getAliasMapRemoveKey() {
        return aliasMapRemoveKey;
    }

    public void setAliasMapRemoveKey(String _aliasMapRemoveKey) {
        aliasMapRemoveKey = _aliasMapRemoveKey;
    }

    public String getAliasMapGetKey() {
        return aliasMapGetKey;
    }

    public void setAliasMapGetKey(String _aliasMapGetKey) {
        aliasMapGetKey = _aliasMapGetKey;
    }

    public String getAliasMapFirstKey() {
        return aliasMapFirstKey;
    }

    public void setAliasMapFirstKey(String _aliasMapFirstKey) {
        aliasMapFirstKey = _aliasMapFirstKey;
    }

    public String getAliasMapLastKey() {
        return aliasMapLastKey;
    }

    public void setAliasMapLastKey(String _aliasMapLastKey) {
        aliasMapLastKey = _aliasMapLastKey;
    }

    public String getAliasMapSetKey() {
        return aliasMapSetKey;
    }

    public void setAliasMapSetKey(String _aliasMapSetKey) {
        aliasMapSetKey = _aliasMapSetKey;
    }

    public String getAliasMapSize() {
        return aliasMapSize;
    }

    public void setAliasMapSize(String _aliasMapSize) {
        aliasMapSize = _aliasMapSize;
    }

    public String getAliasMapIsEmpty() {
        return aliasMapIsEmpty;
    }

    public void setAliasMapIsEmpty(String _aliasMapIsEmpty) {
        aliasMapIsEmpty = _aliasMapIsEmpty;
    }

    public String getAliasMapClear() {
        return aliasMapClear;
    }

    public void setAliasMapClear(String _aliasMapClear) {
        aliasMapClear = _aliasMapClear;
    }

    public String getAliasValidator() {
        return aliasValidator;
    }

    public void setAliasValidator(String _aliasValidator) {
        aliasValidator = _aliasValidator;
    }

    public String getAliasValidate() {
        return aliasValidate;
    }

    public void setAliasValidate(String _aliasValidate) {
        aliasValidate = _aliasValidate;
    }

    public String getAliasBean() {
        return aliasBean;
    }

    public void setAliasBean(String _aliasBean) {
        aliasBean = _aliasBean;
    }

    public String getAliasBeforeDisplaying() {
        return aliasBeforeDisplaying;
    }

    public void setAliasBeforeDisplaying(String _aliasBeforeDisplaying) {
        aliasBeforeDisplaying = _aliasBeforeDisplaying;
    }

    public String getAliasDataBaseField() {
        return aliasDataBaseField;
    }

    public void setAliasDataBaseField(String _aliasDataBaseField) {
        aliasDataBaseField = _aliasDataBaseField;
    }

    public String getAliasGetDataBase() {
        return aliasGetDataBase;
    }

    public void setAliasGetDataBase(String _aliasGetDataBase) {
        aliasGetDataBase = _aliasGetDataBase;
    }

    public String getAliasSetDataBase() {
        return aliasSetDataBase;
    }

    public void setAliasSetDataBase(String _aliasSetDataBase) {
        aliasSetDataBase = _aliasSetDataBase;
    }

    public String getAliasForms() {
        return aliasForms;
    }

    public void setAliasForms(String _aliasForms) {
        aliasForms = _aliasForms;
    }

    public String getAliasGetForms() {
        return aliasGetForms;
    }

    public void setAliasGetForms(String _aliasGetForms) {
        aliasGetForms = _aliasGetForms;
    }

    public String getAliasSetForms() {
        return aliasSetForms;
    }

    public void setAliasSetForms(String _aliasSetForms) {
        aliasSetForms = _aliasSetForms;
    }

    public String getAliasLanguage() {
        return aliasLanguage;
    }

    public void setAliasLanguage(String _aliasLanguage) {
        aliasLanguage = _aliasLanguage;
    }
    public String getAliasGetLanguage() {
        return aliasGetLanguage;
    }

    public void setAliasGetLanguage(String _aliasGetLanguage) {
        aliasGetLanguage = _aliasGetLanguage;
    }

    public String getAliasSetLanguage() {
        return aliasSetLanguage;
    }

    public void setAliasSetLanguage(String _aliasSetLanguage) {
        aliasSetLanguage = _aliasSetLanguage;
    }

    public String getAliasScope() {
        return aliasScope;
    }

    public void setAliasScope(String _aliasScope) {
        aliasScope = _aliasScope;
    }

    public String getAliasGetScope() {
        return aliasGetScope;
    }

    public void setAliasGetScope(String _aliasGetScope) {
        aliasGetScope = _aliasGetScope;
    }

    public String getAliasSetScope() {
        return aliasSetScope;
    }

    public void setAliasSetScope(String _aliasSetScope) {
        aliasSetScope = _aliasSetScope;
    }
    public String getAliasStringMapObject() {
        return aliasStringMapObject;
    }

    public void setAliasStringMapObject(String _aliasStringMapObject) {
        aliasStringMapObject = _aliasStringMapObject;
    }

    public String getAliasMessage() {
        return aliasMessage;
    }

    public void setAliasMessage(String _aliasMessage) {
        aliasMessage = _aliasMessage;
    }

    public String getAliasNewMessage() {
        return aliasNewMessage;
    }

    public void setAliasNewMessage(String _aliasNewMessage) {
        aliasNewMessage = _aliasNewMessage;
    }

    public String getAliasMessageFormat() {
        return aliasMessageFormat;
    }

    public void setAliasMessageFormat(String _aliasMessageFormat) {
        aliasMessageFormat = _aliasMessageFormat;
    }

    public String getAliasMessageGetArgs() {
        return aliasMessageGetArgs;
    }

    public void setAliasMessageGetArgs(String _aliasMessageGetArgs) {
        aliasMessageGetArgs = _aliasMessageGetArgs;
    }

    public String getAliasMessageSetArgs() {
        return aliasMessageSetArgs;
    }

    public void setAliasMessageSetArgs(String _aliasMessageSetArgs) {
        aliasMessageSetArgs = _aliasMessageSetArgs;
    }

    public abstract void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess);
}
