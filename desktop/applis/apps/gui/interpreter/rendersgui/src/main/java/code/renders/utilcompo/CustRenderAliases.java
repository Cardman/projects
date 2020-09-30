package code.renders.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.util.DefaultBeanAliases;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class CustRenderAliases {
    private static final String ILLEGAL_THREAD_STATE_EXCEPTION = "IllegalThreadStateException";
    private static final String FILE_GET_LENGTH = "FileGetLength";
    private static final String ATOMIC_INTEGER = "AtomicInteger";
    private static final String CURRENT_THREAD = "CurrentThread";
    private static final String FORMAT_TYPE = "FormatType";
    private static final String ATOMIC_BOOLEAN = "AtomicBoolean";
    private static final String SET_ATOMIC = "SetAtomic";
    private static final String FILE_IS_DIRECTORY = "FileIsDirectory";
    private static final String FILE_GET_PARENT_PATH = "FileGetParentPath";
    private static final String ATOMIC_LONG = "AtomicLong";
    private static final String FILE_ABSOLUTE_PATH = "FileAbsolutePath";
    private static final String FILE_LAST_MODIF = "FileLastModif";
    private static final String FILE_GET_NAME = "FileGetName";
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
    private static final String REENTRANT_LOCK = "ReentrantLock";
    private static final String JOIN_OTHERS = "JoinOthers";
    private static final String FILE_IS_FILE = "FileIsFile";
    private static final String THREAD_EXIT_HOOK = "ThreadExitHook";
    private static final String APPEND_TO_FILE = "AppendToFile";
    private static final String THREAD_CURRENT_TIME = "ThreadCurrentTime";
    private static final String SET_PRIORITY = "SetPriority";
    private static final String FILE_LIST_FILES = "FileListFiles";
    private static final String GET_PRIORITY = "GetPriority";
    private static final String IS_HELD_BY_CURRENT_THREAD = "IsHeldByCurrentThread";
    private static final String FILE_LIST_DIRECTORIES = "FileListDirectories";
    private static final String LENGTH_ITR_TA = "LengthItrTa";
    private static final String EXECUTED_TEST_ANNOTATIONS = "ExecutedTestAnnotations";
    private static final String PAIR_VAR_FIRST = "PairVarFirst";
    private static final String INDEX_ITR_LI = "IndexItrLi";
    private static final String LIST_ITER_TABLE = "ListIterTable";
    private static final String DIFFERENCE = "Difference";
    private static final String TABLE_VAR_SECOND = "TableVarSecond";
    private static final String ITER_TA_VAR_FIRST = "IterTaVarFirst";
    private static final String EXECUTED_TEST_BEFORE = "ExecutedTestBefore";
    private static final String CUST_ITERATOR_VAR = "CustIteratorVar";
    private static final String GET_SECOND_TA = "GetSecondTa";
    private static final String EXECUTED_TEST_TEST = "ExecutedTestTest";
    private static final String EXECUTED_TEST_METHOD = "ExecutedTestMethod";
    private static final String EXECUTED_TEST_AFTER = "ExecutedTestAfter";
    private static final String CUST_ITERATOR = "CustIterator";
    private static final String LIST_CLEAR = "ListClear";
    private static final String GET_FIRST_TA = "GetFirstTa";
    private static final String SET_SECOND_TA = "SetSecondTa";
    private static final String FILE_MAKE_DIRS = "FileMakeDirs";
    private static final String FILE_ZIPPED_BIN = "FileZippedBin";
    private static final String FILE_ZIPPED_BIN_ARRAY = "FileZippedBinArray";
    private static final String FILE_ZIPPED_TEXT = "FileZippedText";
    private static final String FILE_ZIP_BIN = "FileZipBin";
    private static final String FILE_ZIP_BIN_ARRAY = "FileZipBinArray";
    private static final String FILE_ZIP_TEXT = "FileZipText";
    private static final String ENTRY_BINARY = "EntryBinary";
    private static final String ENTRY_TEXT = "EntryText";
    private static final String ENTRY_NAME = "EntryName";
    private static final String ENTRY_VALUE = "EntryValue";
    private static final String FILE_IS_ABSOLUTE = "FileIsAbsolute";
    private static final String FILE_READ_BIN = "FileReadBin";
    private static final String FILE_WRITE_BIN = "FileWriteBin";
    private static final String FILE_DELETE = "FileDelete";
    private static final String FILE_RENAME = "FileRename";
    private static final String CUST_ITER_TABLE = "CustIterTable";
    private static final String TABLE_VAR_FIRST = "TableVarFirst";
    private static final String SET_SECOND = "SetSecond";
    private static final String INDEX_ITR_TA = "IndexItrTa";
    private static final String ITER_TA_VAR_SECOND = "IterTaVarSecond";
    private static final String LENGTH_ITR_LI = "LengthItrLi";
    private static final String SET_FIRST_TA = "SetFirstTa";
    private static final String EXECUTED_TEST = "ExecutedTest";
    private static final String PARAMETERS = "Parameters";
    private static final String TEST_EXCEPTION = "TestException";
    private static final String TEST_NULL_EXCEPTION = "TestNullException";
    private static final String PAIR_VAR_SECOND = "PairVarSecond";
    private static final String EXECUTE_EXECUTE = "ExecuteExecute";
    private static final String EXECUTE_SETUP_NO_EXCEPTION = "ExecuteSetupNoException";
    private static final String ASSERT_ASSERT = "AssertAssert";
    private static final String ASSERT_ASSERT_NULL = "AssertAssertNull";
    private static final String ASSERT_ASSERT_NOT_NULL = "AssertAssertNotNull";
    private static final String DIFFERENCE_FOUND_NULL = "DifferenceFoundNull";
    private static final String RESULT_SUCCESS = "ResultSuccess";
    private static final String INFO_TEST_CURRENT_CLASS = "InfoTestCurrentClass";
    private static final String EXECUTE_CONVERT = "ExecuteConvert";
    private static final String CONCURRENT_ERROR = "ConcurrentError";
    private static final String RESULT_FAIL_MESSAGE = "ResultFailMessage";
    private static final String ASSERT_ASSERT_TRUE = "AssertAssertTrue";
    private static final String INFO_TEST_CURRENT_METHOD = "InfoTestCurrentMethod";
    private static final String RESULT_PARAMS = "ResultParams";
    private static final String PARAMETERS_METHOD = "ParametersMethod";
    private static final String EXECUTE_SETUP_ERROR = "ExecuteSetupError";
    private static final String ASSERT_ASSERT_SAME = "AssertAssertSame";
    private static final String DIFFERENCE_FOUND = "DifferenceFound";
    private static final String DIFFERENCE_FOUND_NOT_TRUE = "DifferenceFoundNotTrue";
    private static final String PARAMETERS_LOCATION = "ParametersLocation";
    private static final String INFO_TEST_COUNT = "InfoTestCount";
    private static final String INFO_TEST_DONE = "InfoTestDone";
    private static final String DIFFERENCE_STACK_DIFF = "DifferenceStackDiff";
    private static final String EXECUTE_TESTS = "ExecuteTests";
    private static final String DIFFERENCE_EXPECTED = "DifferenceExpected";
    private static final String INFO_TEST_CURRENT_PARAMS = "InfoTestCurrentParams";
    private static final String RUNNABLE = "Runnable";
    private static final String THREAD = "Thread";
    private static final String THREAD_SET = "ThreadSet";
    private static final String THREAD_SET_ALL = "ThreadSetAll";
    private static final String THREAD_SET_ADD = "ThreadSetAdd";
    private static final String THREAD_SET_CONTAINS = "ThreadSetContains";
    private static final String THREAD_SET_REMOVE = "ThreadSetRemove";
    private static final String THREAD_SET_SNAPSHOT = "ThreadSetSnapshot";
    private static final String START = "Start";
    private static final String JOIN = "Join";
    private static final String LENGTH_LI = "LengthLi";
    private static final String CUST_PAIR = "CustPair";
    private static final String LIST_TA = "ListTa";
    private static final String GET_ID = "GetId";
    private static final String IS_ALIVE = "IsAlive";
    private static final String IS_ENDED = "IsEnded";
    private static final String END = "End";
    private static final String PRINT = "Print";
    private static final String LIST_ITR = "ListItr";
    private static final String REMOVE_LI = "RemoveLi";
    private static final String ARRAY_LI = "ArrayLi";
    private static final String FIRST = "First";
    private static final String SLEEP = "Sleep";
    private static final String FILE = "File";
    private static final String READ = "Read";
    private static final String LIST = "List";
    private static final String UNLOCK = "Unlock";
    private static final String SIZE_LI = "SizeLi";
    private static final String LIST_VAR = "ListVar";
    private static final String SECOND = "Second";
    private static final String ADD_LI = "AddLi";
    private static final String YIELD = "Yield";
    private static final String SET_FIRST = "SetFirst";
    private static final String LOCK = "Lock";
    private static final String WRITE = "Write";
    private static final String TABLE = "Table";
    private static final String RESULT = "Result";
    private static final String ADD_TA = "AddTa";
    private static final String INFO_TEST = "InfoTest";
    private static final String REMOVE_TA = "RemoveTa";
    private static final String BEFORE = "Before";
    private static final String GET_TA = "GetTa";
    private static final String TEST = "Test";
    private static final String EXECUTE = "Execute";
    private static final String AFTER = "After";
    private static final String SIZE_TA = "SizeTa";
    private static final String ASSERT = "Assert";

    private String aliasRunnable;
    private String aliasThreadSet;
    private String aliasThreadSetAll;
    private String aliasThreadSetAdd;
    private String aliasThreadSetContains;
    private String aliasThreadSetRemove;
    private String aliasThreadSetSnapshot;
    private String aliasThread;
    private String aliasThreadCurrentTime;
    private String aliasThreadExitHook;
    private String aliasCurrentThread;
    private String aliasStart;
    private String aliasJoin;
    private String aliasJoinOthers;
    private String aliasSleep;
    private String aliasRun;
    private String aliasIsAlive;
    private String aliasIsEnded;
    private String aliasEnd;
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
    private String aliasFormatType;
    private String aliasPrint;
    private String aliasFile;
    private String aliasRead;
    private String aliasWrite;
    private String aliasAppendToFile;
    private String aliasFileIsDirectory;
    private String aliasFileIsFile;
    private String aliasFileGetParentPath;
    private String aliasFileGetName;
    private String aliasFileGetLength;
    private String aliasFileAbsolutePath;
    private String aliasFileLastModif;
    private String aliasFileListFiles;
    private String aliasFileListDirectories;
    private String aliasFileMakeDirs;
    private String aliasFileZippedBin;
    private String aliasFileZippedBinArray;
    private String aliasFileZippedText;
    private String aliasFileZipBin;
    private String aliasFileZipBinArray;
    private String aliasFileZipText;
    private String aliasEntryBinary;
    private String aliasEntryText;
    private String aliasEntryName;
    private String aliasEntryValue;
    private String aliasFileIsAbsolute;
    private String aliasFileReadBin;
    private String aliasFileWriteBin;
    private String aliasFileDelete;
    private String aliasFileRename;
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

    private String aliasInfoTest;
    private String aliasExecute;
    private String aliasResult;
    private String aliasExecutedTest;
    private String aliasTest;
    private String aliasAfter;
    private String aliasBefore;
    private String aliasParameters;
    private String aliasAssert;
    private String aliasDifference;

    private String aliasTestException;
    private String aliasTestNullException;
    private String aliasExecutedTestAnnotations;
    private String aliasExecutedTestTest;
    private String aliasExecutedTestBefore;
    private String aliasExecutedTestAfter;
    private String aliasExecutedTestMethod;

    private String aliasResultSuccess;
    private String aliasResultFailMessage;
    private String aliasResultParams;

    private String aliasParametersMethod;
    private String aliasParametersLocation;

    private String aliasExecuteTests;
    private String aliasExecuteExecute;
    private String aliasExecuteConvert;
    private String aliasExecuteSetupNoException;
    private String aliasExecuteSetupError;

    private String aliasAssertAssert;
    private String aliasAssertAssertTrue;
    private String aliasAssertAssertNull;
    private String aliasAssertAssertNotNull;
    private String aliasAssertAssertSame;

    private String aliasDifferenceExpected;
    private String aliasDifferenceFound;
    private String aliasDifferenceFoundNull;
    private String aliasDifferenceFoundNotTrue;
    private String aliasDifferenceStackDiff;

    private String aliasInfoTestCount;
    private String aliasInfoTestDone;
    private String aliasInfoTestCurrentClass;
    private String aliasInfoTestCurrentMethod;
    private String aliasInfoTestCurrentParams;

    private String aliasConcurrentError;

    private AbstractResourcesReader reader = new DefaultResourcesReader();

    public AbstractResourcesReader getReader(){
        return reader;
    }
    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content, StringList _predefinedClasses, StringList _predefinedInterfacesInitOrder) {
        StringMap<String> stds_ = new StringMap<String>();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> primitiveTypes_ = primTypes_.getPrimitiveTypes();
        AliasCore coreNames_ = _content.getCoreNames();
        AliasPredefinedTypes predefTypes_ = _content.getPredefTypes();
        String public_ = _keyWords.getKeyWordPublic();
        String private_ = _keyWords.getKeyWordPrivate();
        String int_ = primTypes_.getAliasPrimInteger();
        String boolean_ = primTypes_.getAliasPrimBoolean();
        String class_ = _keyWords.getKeyWordClass();
        String this_ = _keyWords.getKeyWordThis();
        String new_ = _keyWords.getKeyWordNew();
        String return_ = _keyWords.getKeyWordReturn();
        String iter_ = _keyWords.getKeyWordIter();
        String value_ = _keyWords.getKeyWordValue();
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
        map_.put("{i}", tr("i", _keyWords, primitiveTypes_, coreNames_));
        map_.put("{p}", tr("p", _keyWords, primitiveTypes_, coreNames_));
        map_.put("{out}", tr("out", _keyWords, primitiveTypes_, coreNames_));
        map_.put("{ind}", tr("ind", _keyWords, primitiveTypes_, coreNames_));
        map_.put("{param}", suffixParam_);
        map_.put("{local}", suffixLocal_);
        map_.put("{loop}", suffixLoop_);
        map_.put("{cv}", suffixCatch_);
        map_.put("{iteratorType}", predefTypes_.getAliasIteratorType());
        map_.put("{iterable}", predefTypes_.getAliasIterable());
        map_.put("{listItr}", aliasListItr);
        map_.put("{lengthItrLi}", aliasLengthItrLi);
        map_.put("{indexItrLi}", aliasIndexItrLi);
        map_.put("{void}", coreNames_.getAliasVoid());
        map_.put("{this}", this_);
        map_.put("{sizeLi}", aliasSizeLi);
        map_.put("{next}", predefTypes_.getAliasNext());
        map_.put("{hasNext}", predefTypes_.getAliasHasNext());
        map_.put("{return}", return_);
        map_.put("{array}", aliasArrayLi);
        map_.put("{lengthLi}", aliasLengthLi);
        map_.put("{new}", new_);
        map_.put("{clone}", coreNames_.getAliasClone());
        map_.put("{length}", _content.getCharSeq().getAliasLength());
        map_.put("{add}", aliasAddLi);
        map_.put("{iter}", iter_);
        map_.put("{value}", value_);
        map_.put("{remove}", aliasRemoveLi);
        map_.put("{iterator}", predefTypes_.getAliasIterator());
        map_.put("{clear}",aliasListClear);
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        _predefinedClasses.add(aliasCustIterator);
        _predefinedClasses.add(aliasList);
        stds_.put(aliasList, content_);
        _predefinedInterfacesInitOrder.add(aliasCustIterator);
        _predefinedInterfacesInitOrder.add(aliasList);
        content_ = reader.read("resources_lg/collections/table.txt");
        map_.put("{CustPair}",aliasCustPair);
        map_.put("{Pair}",predefTypes_.getAliasPairType());
        map_.put("{U}",aliasPairVarFirst);
        map_.put("{V}",aliasPairVarSecond);
        map_.put("{first}",aliasFirst);
        map_.put("{second}",aliasSecond);
        map_.put("{getFirst}",predefTypes_.getAliasGetFirst());
        map_.put("{getSecond}",predefTypes_.getAliasGetSecond());
        map_.put("{setFirst}",aliasSetFirst);
        map_.put("{setSecond}",aliasSetSecond);
        map_.put("{f}", tr("f", _keyWords, primitiveTypes_, coreNames_));
        map_.put("{s}", tr("s", _keyWords, primitiveTypes_, coreNames_));
        map_.put("{CustIterTable}", aliasCustIterTable);
        map_.put("{IterTypeTable}", predefTypes_.getAliasIteratorTableType());
        map_.put("{listItrTa}", aliasListIterTable);
        map_.put("{lengthItrTa}", aliasLengthItrTa);
        map_.put("{indexItrTa}", aliasIndexItrTa);
        map_.put("{A}",aliasIterTaVarFirst);
        map_.put("{B}",aliasIterTaVarSecond);
        map_.put("{nextPair}",predefTypes_.getAliasNextPair());
        map_.put("{hasNextPair}",predefTypes_.getAliasHasNextPair());
        map_.put("{Table}",aliasTable);
        map_.put("{listTa}",aliasListTa);
        map_.put("{iterableTable}",predefTypes_.getAliasIterableTable());
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
        map_.put("{iteratorTable}",predefTypes_.getAliasIteratorTable());
        content_ = StringList.formatQuote(content_, map_);
        _predefinedClasses.add(aliasCustPair);
        _predefinedClasses.add(aliasCustIterTable);
        _predefinedClasses.add(aliasTable);
        stds_.put(aliasTable, content_);
        _predefinedInterfacesInitOrder.add(aliasCustPair);
        _predefinedInterfacesInitOrder.add(aliasCustIterTable);
        _predefinedInterfacesInitOrder.add(aliasTable);
        return stds_;
    }
    private static String tr(String _var, KeyWords _keyWords, StringMap<PrimitiveType> _primitiveTypes, AliasCore _coreNames) {
        CustList<String> allKeysWords_ = _keyWords.allKeyWords().values();
        allKeysWords_.addAllElts(_primitiveTypes.getKeys());
        allKeysWords_.add(_coreNames.getAliasVoid());
        String candidate_ = _var;
        int index_ = 0;
        while (StringList.contains(allKeysWords_,candidate_)) {
            candidate_ = StringList.concatNbs(_var,index_);
            index_++;
        }
        return candidate_;
    }
    public void buildOther(LgNamesContent _content) {
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
        stdcl_ = new StandardClass(aliasAtomicBoolean, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasAtomicBoolean, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicInteger, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIncrementAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndIncrementAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDecrementAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndDecrementAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasAtomicInteger, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicLong, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong(),_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIncrementAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndIncrementAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDecrementAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndDecrementAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasAtomicLong, std_);
    }
    public Argument defaultInstance(ContextEl _cont, String _id) {
        if (StringList.quickEq(_id,_cont.getStandards().getAliasObject())) {
            return new Argument(new SimpleObjectStruct());
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
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String name_ = _method.getName();
        if (StringList.quickEq(name_,_cont.getStandards().getAliasObject())) {
            res_.setResult(new SimpleObjectStruct());
            return res_;
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
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String className_ = _method.getClassName();
        String type_ = _method.getClassName();
        if (StringList.quickEq(type_, _cont.getStandards().getAliasEnums())) {
            return ApplyCoreMethodUtil.getOtherResultBase(_cont, _method, _args);
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

    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> t_ = new StringMap<CustList<KeyValueMemberName>>();
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

    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames(LgNamesContent _content) {
        CustList<CustList<KeyValueMemberName>> list_ = new CustList<CustList<KeyValueMemberName>>();
        list_.add(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(LgNames.ITERATOR,_content.getPredefTypes().getAliasIterator()),
                new KeyValueMemberName(LgNames.HAS_NEXT,_content.getPredefTypes().getAliasHasNext()),
                new KeyValueMemberName(LgNames.NEXT,_content.getPredefTypes().getAliasNext()),
                new KeyValueMemberName(LgNames.ITERATOR_TABLE,_content.getPredefTypes().getAliasIteratorTable()),
                new KeyValueMemberName(LgNames.HAS_NEXT_PAIR,_content.getPredefTypes().getAliasHasNextPair()),
                new KeyValueMemberName(LgNames.NEXT_PAIR,_content.getPredefTypes().getAliasNextPair()),
                new KeyValueMemberName(LgNames.GET_FIRST,_content.getPredefTypes().getAliasGetFirst()),
                new KeyValueMemberName(LgNames.GET_SECOND,_content.getPredefTypes().getAliasGetSecond()),
                new KeyValueMemberName(LgNames.ENUM_ORDINAL,_content.getPredefTypes().getAliasEnumOrdinal()),
                new KeyValueMemberName(LgNames.ENUM_NAME,_content.getPredefTypes().getAliasEnumName()),
                new KeyValueMemberName(LgNames.SEED_GET,_content.getPredefTypes().getAliasSeedGet())
        ));
        return list_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = new StringMap<CustList<KeyValueMemberName>>();
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

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(LgNamesContent _content) {
        StringMap<CustList<KeyValueMemberName>> m_ = new StringMap<CustList<KeyValueMemberName>>();
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
                new KeyValueMemberName(LgNames.NEXT,_content.getPredefTypes().getAliasNext()),
                new KeyValueMemberName(LgNames.HAS_NEXT,_content.getPredefTypes().getAliasHasNext())));
        m_.addEntry(getAliasList(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ADD_LI,getAliasAddLi()),
                new KeyValueMemberName(SIZE_LI,getAliasSizeLi()),
                new KeyValueMemberName(REMOVE_LI,getAliasRemoveLi()),
                new KeyValueMemberName(LgNames.ITERATOR,_content.getPredefTypes().getAliasIterator()),
                new KeyValueMemberName(LIST_CLEAR,getAliasListClear())));
        m_.addEntry(getAliasCustPair(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(LgNames.GET_FIRST,_content.getPredefTypes().getAliasGetFirst()),
                new KeyValueMemberName(SET_FIRST,getAliasSetFirst()),
                new KeyValueMemberName(LgNames.GET_SECOND,_content.getPredefTypes().getAliasGetSecond()),
                new KeyValueMemberName(SET_SECOND,getAliasSetSecond())));
        m_.addEntry(getAliasCustIterTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(LgNames.NEXT_PAIR,_content.getPredefTypes().getAliasNextPair()),
                new KeyValueMemberName(LgNames.HAS_NEXT_PAIR,_content.getPredefTypes().getAliasHasNextPair())));
        m_.addEntry(getAliasTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_FIRST_TA,getAliasGetFirstTa()),
                new KeyValueMemberName(GET_SECOND_TA,getAliasGetSecondTa()),
                new KeyValueMemberName(SET_FIRST_TA,getAliasSetFirstTa()),
                new KeyValueMemberName(SET_SECOND_TA,getAliasSetSecondTa()),
                new KeyValueMemberName(ADD_TA,getAliasAddTa()),
                new KeyValueMemberName(REMOVE_TA,getAliasRemoveTa()),
                new KeyValueMemberName(SIZE_TA,getAliasSizeTa()),
                new KeyValueMemberName(GET_TA,getAliasGetTa()),
                new KeyValueMemberName(LgNames.ITERATOR_TABLE,_content.getPredefTypes().getAliasIteratorTable())
        ));
        return m_;
    }

    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ = new StringMap<String>();
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
    public void build(StringMap<String> _util, StringMap<String> _cust) {
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
    }
    public void build(LgNamesContent _content,DefaultBeanAliases _bean,StringMap<String> _util, StringMap<String> _cust) {
        _content.setDefaultPkg(get(_util,_cust, LgNames.DEFAULT_PKG));
        _content.getNbAlias().setAliasMaxValueField(get(_util,_cust, LgNames.FIELD_MAX_VALUE));
        _content.getNbAlias().setAliasMinValueField(get(_util,_cust, LgNames.FIELD_MIN_VALUE));
        _content.getNbAlias().setAliasPlusInfinityField(get(_util,_cust, LgNames.FIELD_PLUS_INFINITY));
        _content.getNbAlias().setAliasMinusInfinityField(get(_util,_cust, LgNames.FIELD_MINUS_INFINITY));
        _content.getNbAlias().setAliasNanField(get(_util,_cust, LgNames.FIELD_NAN));
        _content.getCoreNames().setAliasBadEncode(get(_util,_cust, LgNames.BAD_ENCODE));
        _content.getCoreNames().setAliasDivisionZero(get(_util,_cust, LgNames.DIVISION_ZERO));
        _content.getCharSeq().setAliasCharSequence(get(_util,_cust, LgNames.CHAR_SEQUENCE));
        _content.getPredefTypes().setAliasIteratorType(get(_util,_cust, LgNames.ITERATOR_TYPE));
        _content.getPredefTypes().setAliasEnumParam(get(_util,_cust, LgNames.ENUM_PARAM));
        _content.getCoreNames().setAliasGetMessage(get(_util,_cust, LgNames.GET_MESSAGE));
        _content.getPredefTypes().setAliasIteratorTableTypeVarFirst(get(_util,_cust, LgNames.ITERATOR_TABLE_TYPE_VAR_FIRST));
        _content.getPredefTypes().setAliasIteratorTableTypeVarSecond(get(_util,_cust, LgNames.ITERATOR_TABLE_TYPE_VAR_SECOND));
        _content.getNbAlias().setAliasEquals(get(_util,_cust, LgNames.EQUALS));
        _content.getNbAlias().setAliasLong(get(_util,_cust, LgNames.LONG));
        _content.getNbAlias().setAliasShort(get(_util,_cust, LgNames.SHORT));
        _content.getPrimTypes().setAliasPrimChar(get(_util,_cust, LgNames.PRIM_CHAR));
        _content.getNbAlias().setAliasNumber(get(_util,_cust, LgNames.NUMBER));
        _content.getNbAlias().setAliasParseInt(get(_util,_cust, LgNames.PARSE_INT));
        _content.getNbAlias().setAliasCompare(get(_util,_cust, LgNames.COMPARE));
        _content.getNbAlias().setAliasIntValue(get(_util,_cust, LgNames.INT_VALUE));
        _content.getNbAlias().setAliasBoolean(get(_util,_cust, LgNames.BOOLEAN));
        _content.getPrimTypes().setAliasPrimLong(get(_util,_cust, LgNames.PRIM_LONG));
        _content.getNbAlias().setAliasByte(get(_util,_cust, LgNames.BYTE));
        _content.getNbAlias().setAliasFloat(get(_util,_cust, LgNames.FLOAT));
        _content.getNbAlias().setAliasDouble(get(_util,_cust, LgNames.DOUBLE));
        _content.getNbAlias().setAliasInteger(get(_util,_cust, LgNames.INTEGER));
        _content.getNbAlias().setAliasDigit(get(_util,_cust, LgNames.DIGIT));
        _content.getNbAlias().setAliasIsDigit(get(_util,_cust, LgNames.IS_DIGIT));
        _content.getMathRef().setAliasMath(get(_util,_cust, LgNames.MATH));
        _content.getCoreNames().setAliasBadSize(get(_util,_cust, LgNames.BAD_SIZE));
        _content.getCoreNames().setAliasNullPe(get(_util,_cust, LgNames.NULL_PE));
        _content.getCoreNames().setAliasObject(get(_util,_cust, LgNames.OBJECT));
        _content.getPredefTypes().setAliasIterator(get(_util,_cust, LgNames.ITERATOR));
        _content.getCoreNames().setAliasCastType(get(_util,_cust, LgNames.CAST_TYPE));
        _content.getCoreNames().setAliasStore(get(_util,_cust, LgNames.STORE));
        _content.getPredefTypes().setAliasEnumType(get(_util,_cust, LgNames.ENUM_TYPE));
        _content.getPrimTypes().setAliasPrimByte(get(_util,_cust, LgNames.PRIM_BYTE));
        _content.getCoreNames().setAliasError(get(_util,_cust, LgNames.ERROR));
        _content.getCoreNames().setAliasVoid(get(_util,_cust, LgNames.VOID));
        _content.getCoreNames().setAliasGetCause(get(_util,_cust, LgNames.GET_CAUSE));
        _content.getCoreNames().setAliasBadIndex(get(_util,_cust, LgNames.BAD_INDEX));
        _content.getCoreNames().setAliasEnums(get(_util,_cust, LgNames.ENUMS));
        _content.getPredefTypes().setAliasIterable(get(_util,_cust, LgNames.ITERABLE));
        _content.getCoreNames().setAliasNbFormat(get(_util,_cust, LgNames.NB_FORMAT));
        _content.getCoreNames().setAliasSof(get(_util,_cust, LgNames.SOF));
        _content.getNbAlias().setAliasParseFloat(get(_util,_cust, LgNames.PARSE_FLOAT));
        _content.getNbAlias().setAliasToStringMethod(get(_util,_cust, LgNames.TO_STRING_METHOD));
        _content.getNbAlias().setAliasParseLongOrNull(get(_util,_cust, LgNames.PARSE_LONG_OR_NULL));
        _content.getNbAlias().setAliasParseShortOrNull(get(_util,_cust, LgNames.PARSE_SHORT_OR_NULL));
        _content.getNbAlias().setAliasParseFloatOrNull(get(_util,_cust, LgNames.PARSE_FLOAT_OR_NULL));
        _content.getNbAlias().setAliasParseDoubleOrNull(get(_util,_cust, LgNames.PARSE_DOUBLE_OR_NULL));
        _content.getNbAlias().setAliasByteValue(get(_util,_cust, LgNames.BYTE_VALUE));
        _content.getNbAlias().setAliasCharValue(get(_util,_cust, LgNames.CHAR_VALUE));
        _content.getPrimTypes().setAliasPrimBoolean(get(_util,_cust, LgNames.PRIM_BOOLEAN));
        _content.getNbAlias().setAliasParseIntOrNull(get(_util,_cust, LgNames.PARSE_INT_OR_NULL));
        _content.getPrimTypes().setAliasPrimShort(get(_util,_cust, LgNames.PRIM_SHORT));
        _content.getNbAlias().setAliasParseBoolean(get(_util,_cust, LgNames.PARSE_BOOLEAN));
        _content.getNbAlias().setAliasParseShort(get(_util,_cust, LgNames.PARSE_SHORT));
        _content.getPrimTypes().setAliasPrimFloat(get(_util,_cust, LgNames.PRIM_FLOAT));
        _content.getNbAlias().setAliasCompareTo(get(_util,_cust, LgNames.COMPARE_TO));
        _content.getNbAlias().setAliasCharacter(get(_util,_cust, LgNames.CHARACTER));
        _content.getNbAlias().setAliasParseLong(get(_util,_cust, LgNames.PARSE_LONG));
        _content.getNbAlias().setAliasValueOfMethod(get(_util,_cust, LgNames.VALUE_OF_METHOD));
        _content.getPrimTypes().setAliasPrimInteger(get(_util,_cust, LgNames.PRIM_INTEGER));
        _content.getNbAlias().setAliasParseByteOrNull(get(_util,_cust, LgNames.PARSE_BYTE_OR_NULL));
        _content.getPrimTypes().setAliasPrimDouble(get(_util,_cust, LgNames.PRIM_DOUBLE));
        _content.getNbAlias().setAliasBooleanValue(get(_util,_cust, LgNames.BOOLEAN_VALUE));
        _content.getNbAlias().setAliasShortValue(get(_util,_cust, LgNames.SHORT_VALUE));
        _content.getNbAlias().setAliasParseDouble(get(_util,_cust, LgNames.PARSE_DOUBLE));
        _content.getCoreNames().setAliasIllegalArg(get(_util,_cust, LgNames.ILLEGAL_ARG));
        _content.getNbAlias().setAliasParseByte(get(_util,_cust, LgNames.PARSE_BYTE));
        _content.getNbAlias().setAliasIsUpperCase(get(_util,_cust, LgNames.IS_UPPER_CASE));
        _content.getNbAlias().setAliasIsWordChar(get(_util,_cust, LgNames.IS_WORD_CHAR));
        _content.getNbAlias().setAliasIsWhitespace(get(_util,_cust, LgNames.IS_WHITESPACE));
        _content.getNbAlias().setAliasIsLetterOrDigit(get(_util,_cust, LgNames.IS_LETTER_OR_DIGIT));
        _content.getNbAlias().setAliasFloatValue(get(_util,_cust, LgNames.FLOAT_VALUE));
        _content.getNbAlias().setAliasDoubleValue(get(_util,_cust, LgNames.DOUBLE_VALUE));
        _content.getNbAlias().setAliasLongValue(get(_util,_cust, LgNames.LONG_VALUE));
        _content.getNbAlias().setAliasIsLowerCase(get(_util,_cust, LgNames.IS_LOWER_CASE));
        _content.getCharSeq().setAliasIndexOf(get(_util,_cust, LgNames.INDEX_OF));
        _content.getCharSeq().setAliasString(get(_util,_cust, LgNames.STRING));
        _content.getCharSeq().setAliasIsEmpty(get(_util,_cust, LgNames.IS_EMPTY));
        _content.getCharSeq().setAliasTrim(get(_util,_cust, LgNames.TRIM));
        _content.getCharSeq().setAliasGetBytes(get(_util,_cust, LgNames.GET_BYTES));
        _content.getNbAlias().setAliasForDigit(get(_util,_cust, LgNames.FOR_DIGIT));
        _content.getNbAlias().setAliasIsSpace(get(_util,_cust, LgNames.IS_SPACE));
        _content.getReflect().setAliasGetType(get(_util,_cust, LgNames.GET_TYPE));
        _content.getCharSeq().setAliasContains(get(_util,_cust, LgNames.CONTAINS));
        _content.getCharSeq().setAliasReplace(get(_util,_cust, LgNames.REPLACE));
        _content.getCharSeq().setAliasReplaceString(get(_util,_cust, LgNames.REPLACE_STRING));
        _content.getCharSeq().setAliasFormat(get(_util,_cust, LgNames.FORMAT));
        _content.getCharSeq().setAliasEndsWith(get(_util,_cust, LgNames.ENDS_WITH));
        _content.getCharSeq().setAliasCapacity(get(_util,_cust, LgNames.CAPACITY));
        _content.getCharSeq().setAliasSplit(get(_util,_cust, LgNames.SPLIT));
        _content.getCharSeq().setAliasAppend(get(_util,_cust, LgNames.APPEND));
        _content.getNbAlias().setAliasIsLetter(get(_util,_cust, LgNames.IS_LETTER));
        _content.getNbAlias().setAliasIsNan(get(_util,_cust, LgNames.IS_NAN));
        _content.getCharSeq().setAliasLength(get(_util,_cust, LgNames.LENGTH));
        _content.getCharSeq().setAliasCharAt(get(_util,_cust, LgNames.CHAR_AT));
        _content.getCoreNames().setAliasClone(get(_util,_cust, LgNames.CLONE));
        _content.getCoreNames().setAliasName(get(_util,_cust, LgNames.NAME));
        _content.getReflect().setAliasCall(get(_util,_cust, LgNames.CALL));
        _content.getReflect().setAliasMetaInfo(get(_util,_cust, LgNames.META_INFO));
        _content.getReflect().setAliasInstance(get(_util,_cust, LgNames.INSTANCE));
        _content.getCharSeq().setAliasSame(get(_util,_cust, LgNames.SAME));
        _content.getMathRef().setAliasMod(get(_util,_cust, LgNames.MOD));
        _content.getCharSeq().setAliasReverse(get(_util,_cust, LgNames.REVERSE));
        _content.getCharSeq().setAliasInsert(get(_util,_cust, LgNames.INSERT));
        _content.getMathRef().setAliasAbs(get(_util,_cust, LgNames.ABS));
        _content.getPredefTypes().setAliasHasNext(get(_util,_cust, LgNames.HAS_NEXT));
        _content.getPredefTypes().setAliasPairType(get(_util,_cust, LgNames.PAIR_TYPE));
        _content.getMathRef().setAliasQuot(get(_util,_cust, LgNames.QUOT));
        _content.getPredefTypes().setAliasNext(get(_util,_cust, LgNames.NEXT));
        _content.getCoreNames().setAliasOrdinal(get(_util,_cust, LgNames.ORDINAL));
        _content.getPredefTypes().setAliasGetFirst(get(_util,_cust, LgNames.GET_FIRST));
        _content.getReflect().setAliasFct(get(_util,_cust, LgNames.FCT));
        _content.getCharSeq().setAliasDelete(get(_util,_cust, LgNames.DELETE));
        _content.getCharSeq().setAliasClear(get(_util,_cust, LgNames.CLEAR));
        _content.getPredefTypes().setAliasNextPair(get(_util,_cust, LgNames.NEXT_PAIR));
        _content.getCharSeq().setAliasSubstring(get(_util,_cust, LgNames.SUBSTRING));
        _content.getCharSeq().setAliasSetCharAt(get(_util,_cust, LgNames.SET_CHAR_AT));
        _content.getCharSeq().setAliasEqualsIgnoreCase(get(_util,_cust, LgNames.EQUALS_IGNORE_CASE));
        _content.getPredefTypes().setAliasIteratorTableType(get(_util,_cust, LgNames.ITERATOR_TABLE_TYPE));
        _content.getCharSeq().setAliasDeleteCharAt(get(_util,_cust, LgNames.DELETE_CHAR_AT));
        _content.getCharSeq().setAliasStartsWith(get(_util,_cust, LgNames.STARTS_WITH));
        _content.getCharSeq().setAliasLastIndexOf(get(_util,_cust, LgNames.LAST_INDEX_OF));
        _content.getCharSeq().setAliasRegionMatches(get(_util,_cust, LgNames.REGION_MATCHES));
        _content.getPredefTypes().setAliasIteratorTable(get(_util,_cust, LgNames.ITERATOR_TABLE));
        _content.getPredefTypes().setAliasIterableTable(get(_util,_cust, LgNames.ITERABLE_TABLE));
        _content.getCharSeq().setAliasToLowerCase(get(_util,_cust, LgNames.TO_LOWER_CASE));
        _content.getNbAlias().setAliasToLowerCaseChar(get(_util,_cust, LgNames.TO_LOWER_CASE_CHAR));
        _content.getCharSeq().setAliasStringBuilder(get(_util,_cust, LgNames.STRING_BUILDER));
        _content.getCharSeq().setAliasToUpperCase(get(_util,_cust, LgNames.TO_UPPER_CASE));
        _content.getNbAlias().setAliasToUpperCaseChar(get(_util,_cust, LgNames.TO_UPPER_CASE_CHAR));
        _content.getCharSeq().setAliasEnsureCapacity(get(_util,_cust, LgNames.ENSURE_CAPACITY));
        _content.getCharSeq().setAliasSetLength(get(_util,_cust, LgNames.SET_LENGTH));
        _content.getCharSeq().setAliasTrimToSize(get(_util,_cust, LgNames.TRIM_TO_SIZE));
        _content.getPredefTypes().setAliasHasNextPair(get(_util,_cust, LgNames.HAS_NEXT_PAIR));
        _content.getCharSeq().setAliasReplacement(get(_util,_cust, LgNames.REPLACEMENT));
        _content.getCharSeq().setAliasGetOldString(get(_util,_cust, LgNames.GET_OLD_STRING));
        _content.getCharSeq().setAliasGetNewString(get(_util,_cust, LgNames.GET_NEW_STRING));
        _content.getPredefTypes().setAliasGetSecond(get(_util,_cust, LgNames.GET_SECOND));
        _content.getCharSeq().setAliasSubSequence(get(_util,_cust, LgNames.SUB_SEQUENCE));
        _content.getCharSeq().setAliasCompareToIgnoreCase(get(_util,_cust, LgNames.COMPARE_TO_IGNORE_CASE));
        _content.getCharSeq().setAliasToCharArray(get(_util,_cust, LgNames.TO_CHAR_ARRAY));
        _content.getCharSeq().setAliasReplaceMultiple(get(_util,_cust, LgNames.REPLACE_MULTIPLE));
        _content.getCharSeq().setAliasSplitStrings(get(_util,_cust, LgNames.SPLIT_STRINGS));
        _content.getCharSeq().setAliasSplitChars(get(_util,_cust, LgNames.SPLIT_CHARS));
        _content.getNbAlias().setAliasIsInfinite(get(_util,_cust, LgNames.IS_INFINITE));
        _content.getNbAlias().setAliasGetDirectionality(get(_util,_cust, LgNames.GET_DIRECTIONALITY));
        _content.getNbAlias().setAliasGetCharType(get(_util,_cust, LgNames.GET_CHAR_TYPE));
        _content.getPredefTypes().setAliasIterableTableVarSecond(get(_util,_cust, LgNames.ITERABLE_TABLE_VAR_SECOND));
        _content.getReflect().setAliasGetString(get(_util,_cust, LgNames.GET_STRING));
        _content.getReflect().setAliasGetAnnotationsParameters(get(_util,_cust, LgNames.GET_ANNOTATIONS_PARAMETERS));
        _content.getCoreNames().setAliasReadResourcesNames(get(_util,_cust, LgNames.READ_RESOURCES_NAMES));
        _content.getCoreNames().setAliasReadResourcesNamesLength(get(_util,_cust, LgNames.READ_RESOURCES_NAMES_LENGTH));
        _content.getReflect().setAliasInvokeTarget(get(_util,_cust, LgNames.INVOKE_TARGET));
        _content.getReflect().setAliasGetAnnotations(get(_util,_cust, LgNames.GET_ANNOTATIONS));
        _content.getReflect().setAliasGetVariableOwner(get(_util,_cust, LgNames.GET_VARIABLE_OWNER));
        _content.getCoreNames().setAliasReadResources(get(_util,_cust, LgNames.READ_RESOURCES));
        _content.getCoreNames().setAliasReadResourcesIndex(get(_util,_cust, LgNames.READ_RESOURCES_INDEX));
        _content.getCoreNames().setAliasResources(get(_util,_cust, LgNames.RESOURCES));
        _content.getReflect().setAliasClassNotFoundError(get(_util,_cust, LgNames.CLASS_NOT_FOUND_ERROR));
        _content.getPredefTypes().setAliasEnumValues(get(_util,_cust, LgNames.ENUM_VALUES));
        _content.getPredefTypes().setAliasEnumPredValueOf(get(_util,_cust, LgNames.ENUM_PRED_VALUE_OF));
        _content.getPredefTypes().setAliasIteratorTypeVar(get(_util,_cust, LgNames.ITERATOR_TYPE_VAR));
        _content.getReflect().setAliasClassType(get(_util,_cust, LgNames.CLASS_TYPE));
        _content.getPredefTypes().setAliasIterableTableVarFirst(get(_util,_cust, LgNames.ITERABLE_TABLE_VAR_FIRST));
        _content.getPredefTypes().setAliasPairTypeVarFirst(get(_util,_cust, LgNames.PAIR_TYPE_VAR_FIRST));
        _content.getCoreNames().setAliasErrorInitClass(get(_util,_cust, LgNames.ERROR_INIT_CLASS));
        _content.getReflect().setAliasAnnotationType(get(_util,_cust, LgNames.ANNOTATION_TYPE));
        _content.getReflect().setAliasGetGenericVariableOwner(get(_util,_cust, LgNames.GET_GENERIC_VARIABLE_OWNER));
        _content.getPredefTypes().setAliasEnumParamVar(get(_util,_cust, LgNames.ENUM_PARAM_VAR));
        _content.getPredefTypes().setAliasSeedGenerator(get(_util,_cust, LgNames.SEED_GENERATOR));
        _content.getPredefTypes().setAliasSeedDoubleGenerator(get(_util,_cust, LgNames.SEED_DOUBLE_GENERATOR));
        _content.getPredefTypes().setAliasSeedGet(get(_util,_cust, LgNames.SEED_GET));
        _content.getPredefTypes().setAliasPairTypeVarSecond(get(_util,_cust, LgNames.PAIR_TYPE_VAR_SECOND));
        _content.getReflect().setAliasAnnotated(get(_util,_cust, LgNames.ANNOTATED));
        _content.getPredefTypes().setAliasIterableVar(get(_util,_cust, LgNames.ITERABLE_VAR));
        _content.getReflect().setAliasGetDefaultValue(get(_util,_cust, LgNames.GET_DEFAULT_VALUE));
        _content.getReflect().setAliasMakeGeneric(get(_util,_cust, LgNames.MAKE_GENERIC));
        _content.getReflect().setAliasGetAllClasses(get(_util,_cust, LgNames.GET_ALL_CLASSES));
        _content.getReflect().setAliasGetOperators(get(_util,_cust, LgNames.GET_OPERATORS));
        _content.getReflect().setAliasGetDeclaredExplicits(get(_util,_cust, LgNames.GET_DECLARED_EXPLICITS));
        _content.getReflect().setAliasGetDeclaredImplicits(get(_util,_cust, LgNames.GET_DECLARED_IMPLICITS));
        _content.getReflect().setAliasGetDeclaredTrueOperators(get(_util,_cust, LgNames.GET_DECLARED_TRUE_OPERATORS));
        _content.getReflect().setAliasGetDeclaredFalseOperators(get(_util,_cust, LgNames.GET_DECLARED_FALSE_OPERATORS));
        _content.getReflect().setAliasGetDeclaredMethods(get(_util,_cust, LgNames.GET_DECLARED_METHODS));
        _content.getReflect().setAliasGetDeclaredStaticMethods(get(_util,_cust, LgNames.GET_DECLARED_STATIC_METHODS));
        _content.getReflect().setAliasGetDeclaredConstructors(get(_util,_cust, LgNames.GET_DECLARED_CONSTRUCTORS));
        _content.getReflect().setAliasGetDeclaredFields(get(_util,_cust, LgNames.GET_DECLARED_FIELDS));
        _content.getReflect().setAliasGetDeclaredAnonymousTypes(get(_util,_cust, LgNames.GET_DECLARED_ANONYMOUS_TYPES));
        _content.getReflect().setAliasGetDeclaredAnonymousLambda(get(_util,_cust, LgNames.GET_DECLARED_ANONYMOUS_LAMBDA));
        _content.getReflect().setAliasGetDeclaredAnonymousLambdaLocalVars(get(_util,_cust, LgNames.GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS));
        _content.getReflect().setAliasGetDeclaredAnonymousLambdaLoopVars(get(_util,_cust, LgNames.GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS));
        _content.getReflect().setAliasGetDeclaredBlocks(get(_util,_cust, LgNames.GET_DECLARED_BLOCKS));
        _content.getReflect().setAliasGetDeclaredLocalTypes(get(_util,_cust, LgNames.GET_DECLARED_LOCAL_TYPES));
        _content.getReflect().setAliasField(get(_util,_cust, LgNames.FIELD));
        _content.getReflect().setAliasIsNormal(get(_util,_cust, LgNames.IS_NORMAL));
        _content.getCoreNames().setAliasSameRef(get(_util,_cust, LgNames.SAME_REF));
        _content.getReflect().setAliasIsPublic(get(_util,_cust, LgNames.IS_PUBLIC));
        _content.getReflect().setAliasIsArray(get(_util,_cust, LgNames.IS_ARRAY));
        _content.getReflect().setAliasArrayGet(get(_util,_cust, LgNames.ARRAY_GET));
        _content.getReflect().setAliasMethod(get(_util,_cust, LgNames.METHOD));
        _content.getReflect().setAliasGetField(get(_util,_cust, LgNames.GET_FIELD));
        _content.getReflect().setAliasInvoke(get(_util,_cust, LgNames.INVOKE));
        _content.getReflect().setAliasIsEnum(get(_util,_cust, LgNames.IS_ENUM));
        _content.getReflect().setAliasInit(get(_util,_cust, LgNames.INIT));
        _content.getReflect().setAliasForName(get(_util,_cust, LgNames.FOR_NAME));
        _content.getReflect().setAliasIsStatic(get(_util,_cust, LgNames.IS_STATIC));
        _content.getReflect().setAliasIsStaticCall(get(_util,_cust, LgNames.IS_STATIC_CALL));
        _content.getReflect().setAliasIsInstanceMethod(get(_util,_cust, LgNames.IS_INSTANCE_METHOD));
        _content.getReflect().setAliasGetName(get(_util,_cust, LgNames.GET_NAME));
        _content.getReflect().setAliasIsClass(get(_util,_cust, LgNames.IS_CLASS));
        _content.getReflect().setAliasSetField(get(_util,_cust, LgNames.SET_FIELD));
        _content.getReflect().setAliasGetClass(get(_util,_cust, LgNames.GET_CLASS));
        _content.getReflect().setAliasIsFinal(get(_util,_cust, LgNames.IS_FINAL));
        _content.getReflect().setAliasArraySet(get(_util,_cust, LgNames.ARRAY_SET));
        _content.getMathRef().setAliasXor(get(_util,_cust, LgNames.XOR));
        _content.getMathRef().setAliasMult(get(_util,_cust, LgNames.MULT));
        _content.getMathRef().setAliasRandom(get(_util,_cust, LgNames.RANDOM));
        _content.getMathRef().setAliasSeed(get(_util,_cust, LgNames.SEED));
        _content.getMathRef().setAliasNegBin(get(_util,_cust, LgNames.NEG_BIN));
        _content.getMathRef().setAliasMinus(get(_util,_cust, LgNames.MINUS));
        _content.getPredefTypes().setAliasEnumName(get(_util,_cust, LgNames.ENUM_NAME));
        _content.getMathRef().setAliasBinMod(get(_util,_cust, LgNames.BIN_MOD));
        _content.getMathRef().setAliasLt(get(_util,_cust, LgNames.LT));
        _content.getMathRef().setAliasGt(get(_util,_cust, LgNames.GT));
        _content.getMathRef().setAliasLe(get(_util,_cust, LgNames.LE));
        _content.getMathRef().setAliasGe(get(_util,_cust, LgNames.GE));
        _content.getMathRef().setAliasAnd(get(_util,_cust, LgNames.AND));
        _content.getMathRef().setAliasOr(get(_util,_cust, LgNames.OR));
        _content.getMathRef().setAliasPlus(get(_util,_cust, LgNames.PLUS));
        _content.getMathRef().setAliasBinQuot(get(_util,_cust, LgNames.BIN_QUOT));
        _content.getMathRef().setAliasNeg(get(_util,_cust, LgNames.NEG));
        _content.getMathRef().setAliasRotateLeft(get(_util,_cust, LgNames.ROTATE_LEFT));
        _content.getPredefTypes().setAliasEnumOrdinal(get(_util,_cust, LgNames.ENUM_ORDINAL));
        _content.getMathRef().setAliasShiftRight(get(_util,_cust, LgNames.SHIFT_RIGHT));
        _content.getStackElt().setAliasCurrentFullStack(get(_util,_cust, LgNames.CURRENT_FULL_STACK));
        _content.getReflect().setAliasGetBounds(get(_util,_cust, LgNames.GET_BOUNDS));
        _content.getReflect().setAliasGetDeclaringClass(get(_util,_cust, LgNames.GET_DECLARING_CLASS));
        _content.getStackElt().setAliasStackTraceElement(get(_util,_cust, LgNames.STACK_TRACE_ELEMENT));
        _content.getReflect().setAliasEnumValueOf(get(_util,_cust, LgNames.ENUM_VALUE_OF));
        _content.getReflect().setAliasArrayNewInstance(get(_util,_cust, LgNames.ARRAY_NEW_INSTANCE));
        _content.getReflect().setAliasGetEnumConstants(get(_util,_cust, LgNames.GET_ENUM_CONSTANTS));
        _content.getReflect().setAliasArrayGetLength(get(_util,_cust, LgNames.ARRAY_GET_LENGTH));
        _content.getMathRef().setAliasRotateRight(get(_util,_cust, LgNames.ROTATE_RIGHT));
        _content.getReflect().setAliasGetGenericBounds(get(_util,_cust, LgNames.GET_GENERIC_BOUNDS));
        _content.getMathRef().setAliasBitShiftLeft(get(_util,_cust, LgNames.BIT_SHIFT_LEFT));
        _content.getMathRef().setAliasShiftLeft(get(_util,_cust, LgNames.SHIFT_LEFT));
        _content.getReflect().setAliasDefaultInstance(get(_util,_cust, LgNames.DEFAULT_INSTANCE));
        _content.getStackElt().setAliasCurrentStack(get(_util,_cust, LgNames.CURRENT_STACK));
        _content.getMathRef().setAliasBitShiftRight(get(_util,_cust, LgNames.BIT_SHIFT_RIGHT));
        _content.getReflect().setAliasGetParameterNames(get(_util,_cust, LgNames.GET_PARAMETER_NAMES));
        _content.getReflect().setAliasGetPrettyName(get(_util,_cust, LgNames.GET_PRETTY_NAME));
        _content.getReflect().setAliasGetPrettySingleName(get(_util,_cust, LgNames.GET_PRETTY_SINGLE_NAME));
        _content.getReflect().setAliasGetUpperBounds(get(_util,_cust, LgNames.GET_UPPER_BOUNDS));
        _content.getReflect().setAliasGetParameterTypes(get(_util,_cust, LgNames.GET_PARAMETER_TYPES));
        _content.getReflect().setAliasGetGenericReturnType(get(_util,_cust, LgNames.GET_GENERIC_RETURN_TYPE));
        _content.getReflect().setAliasInvokeDirect(get(_util,_cust, LgNames.INVOKE_DIRECT));
        _content.getCoreNames().setAliasStringUtil(get(_util,_cust, LgNames.STRING_UTIL));
        _content.getReflect().setAliasGetLowerBounds(get(_util,_cust, LgNames.GET_LOWER_BOUNDS));
        _content.getReflect().setAliasGetTypeParameters(get(_util,_cust, LgNames.GET_TYPE_PARAMETERS));
        _content.getReflect().setAliasConstructor(get(_util,_cust, LgNames.CONSTRUCTOR));
        _content.getCoreNames().setAliasSetParent(get(_util,_cust, LgNames.SET_PARENT));
        _content.getReflect().setAliasNewInstance(get(_util,_cust, LgNames.NEW_INSTANCE));
        _content.getReflect().setAliasGetEnclosingType(get(_util,_cust, LgNames.GET_ENCLOSING_TYPE));
        _content.getReflect().setAliasGetInterfaces(get(_util,_cust, LgNames.GET_INTERFACES));
        _content.getCoreNames().setAliasObjectsUtil(get(_util,_cust, LgNames.OBJECTS_UTIL));
        _content.getReflect().setAliasGetDeclaredClasses(get(_util,_cust, LgNames.GET_DECLARED_CLASSES));
        _content.getReflect().setAliasGetSuperClass(get(_util,_cust, LgNames.GET_SUPER_CLASS));
        _content.getCoreNames().setAliasGetParent(get(_util,_cust, LgNames.GET_PARENT));
        _content.getReflect().setAliasGetComponentType(get(_util,_cust, LgNames.GET_COMPONENT_TYPE));
        _content.getReflect().setAliasGetFileName(get(_util,_cust, LgNames.GET_FILE_NAME));
        _content.getReflect().setAliasGetGenericSuperClass(get(_util,_cust, LgNames.GET_GENERIC_SUPER_CLASS));
        _content.getReflect().setAliasGetGenericInterfaces(get(_util,_cust, LgNames.GET_GENERIC_INTERFACES));
        _content.getReflect().setAliasIsAbstract(get(_util,_cust, LgNames.IS_ABSTRACT));
        _content.getReflect().setAliasMakeArray(get(_util,_cust, LgNames.MAKE_ARRAY));
        _content.getReflect().setAliasIsInterface(get(_util,_cust, LgNames.IS_INTERFACE));
        _content.getReflect().setAliasMakeWildCard(get(_util,_cust, LgNames.MAKE_WILD_CARD));
        _content.getReflect().setAliasIsTypeVariable(get(_util,_cust, LgNames.IS_TYPE_VARIABLE));
        _content.getReflect().setAliasIsPrivate(get(_util,_cust, LgNames.IS_PRIVATE));
        _content.getReflect().setAliasIsVarargs(get(_util,_cust, LgNames.IS_VARARGS));
        _content.getReflect().setAliasIsInstance(get(_util,_cust, LgNames.IS_INSTANCE));
        _content.getReflect().setAliasGetReturnType(get(_util,_cust, LgNames.GET_RETURN_TYPE));
        _content.getReflect().setAliasGetActualTypeArguments(get(_util,_cust, LgNames.GET_ACTUAL_TYPE_ARGUMENTS));
        _content.getReflect().setAliasIsProtected(get(_util,_cust, LgNames.IS_PROTECTED));
        _content.getReflect().setAliasIsPrimitive(get(_util,_cust, LgNames.IS_PRIMITIVE));
        _content.getReflect().setAliasIsWildCard(get(_util,_cust, LgNames.IS_WILD_CARD));
        _content.getReflect().setAliasIsAnnotation(get(_util,_cust, LgNames.IS_ANNOTATION));
        _content.getReflect().setAliasGetGenericType(get(_util,_cust, LgNames.GET_GENERIC_TYPE));
        _content.getReflect().setAliasIsAssignableFrom(get(_util,_cust, LgNames.IS_ASSIGNABLE_FROM));
        _content.getReflect().setAliasIsVariable(get(_util,_cust, LgNames.IS_VARIABLE));
        _content.getReflect().setAliasIsPackage(get(_util,_cust, LgNames.IS_PACKAGE));
        _content.getDisplayedStrings().setFalseString(get(_util,_cust, LgNames.FALSE_STRING));
        _content.getDisplayedStrings().setTrueString(get(_util,_cust, LgNames.TRUE_STRING));
        _content.getDisplayedStrings().setNullString(get(_util,_cust, LgNames.NULL_STRING));
        _content.getDisplayedStrings().setNullCoverString(get(_util,_cust, LgNames.NULL_COVER_STRING));
        _content.getDisplayedStrings().setNotNullCoverString(get(_util,_cust, LgNames.NOT_NULL_COVER_STRING));
        _content.getDisplayedStrings().setStaticCallString(get(_util,_cust, LgNames.STATIC_CALL_STRING));
        _content.getDisplayedStrings().setStaticString(get(_util,_cust, LgNames.STATIC_STRING));
        _content.getDisplayedStrings().setInfinity(get(_util,_cust, LgNames.INFINITY));
        _content.getDisplayedStrings().setNan(get(_util,_cust, LgNames.NAN));
        _content.getDisplayedStrings().setExponent(get(_util,_cust, LgNames.EXPONENT));

        _bean.setAliasBean(get(_util,_cust, DefaultBeanAliases.BEAN));
        _bean.setAliasMapKeys(get(_util,_cust, DefaultBeanAliases.MAP_KEYS));
        _bean.setAliasMapValues(get(_util,_cust, DefaultBeanAliases.MAP_VALUES));
        _bean.setAliasMapIndexOfEntry(get(_util,_cust, DefaultBeanAliases.MAP_INDEX_OF_ENTRY));
        _bean.setAliasMapAddEntry(get(_util,_cust, DefaultBeanAliases.MAP_ADD_ENTRY));
        _bean.setAliasMapGetValue(get(_util,_cust, DefaultBeanAliases.MAP_GET_VALUE));
        _bean.setAliasMapFirstValue(get(_util,_cust, DefaultBeanAliases.MAP_FIRST_VALUE));
        _bean.setAliasMapLastValue(get(_util,_cust, DefaultBeanAliases.MAP_LAST_VALUE));
        _bean.setAliasMapSetValue(get(_util,_cust, DefaultBeanAliases.MAP_SET_VALUE));
        _bean.setAliasMapPut(get(_util,_cust, DefaultBeanAliases.MAP_PUT));
        _bean.setAliasMapContains(get(_util,_cust, DefaultBeanAliases.MAP_CONTAINS));
        _bean.setAliasMapPutAll(get(_util,_cust, DefaultBeanAliases.MAP_PUT_ALL));
        _bean.setAliasMapGetVal(get(_util,_cust, DefaultBeanAliases.MAP_GET_VAL));
        _bean.setAliasMapRemoveKey(get(_util,_cust, DefaultBeanAliases.MAP_REMOVE_KEY));
        _bean.setAliasMapGetKey(get(_util,_cust, DefaultBeanAliases.MAP_GET_KEY));
        _bean.setAliasMapFirstKey(get(_util,_cust, DefaultBeanAliases.MAP_FIRST_KEY));
        _bean.setAliasMapLastKey(get(_util,_cust, DefaultBeanAliases.MAP_LAST_KEY));
        _bean.setAliasMapSetKey(get(_util,_cust, DefaultBeanAliases.MAP_SET_KEY));
        _bean.setAliasMapSize(get(_util,_cust, DefaultBeanAliases.MAP_SIZE));
        _bean.setAliasMapIsEmpty(get(_util,_cust, DefaultBeanAliases.MAP_IS_EMPTY));
        _bean.setAliasMapClear(get(_util,_cust, DefaultBeanAliases.MAP_CLEAR));
        _bean.setAliasValidator(get(_util,_cust, DefaultBeanAliases.VALIDATOR));
        _bean.setAliasValidate(get(_util,_cust, DefaultBeanAliases.VALIDATE));
        _bean.setAliasDataBaseField(get(_util,_cust, DefaultBeanAliases.DATA_BASE_FIELD));
        _bean.setAliasForms(get(_util,_cust, DefaultBeanAliases.FORMS));
        _bean.setAliasSetForms(get(_util,_cust, DefaultBeanAliases.SET_FORMS));
        _bean.setAliasGetForms(get(_util,_cust, DefaultBeanAliases.GET_FORMS));
        _bean.setAliasLanguage(get(_util,_cust, DefaultBeanAliases.LANGUAGE));
        _bean.setAliasSetLanguage(get(_util,_cust, DefaultBeanAliases.SET_LANGUAGE));
        _bean.setAliasGetLanguage(get(_util,_cust, DefaultBeanAliases.GET_LANGUAGE));
        _bean.setAliasScope(get(_util,_cust, DefaultBeanAliases.SCOPE));
        _bean.setAliasSetScope(get(_util,_cust, DefaultBeanAliases.SET_SCOPE));
        _bean.setAliasGetScope(get(_util,_cust, DefaultBeanAliases.GET_SCOPE));
        _bean.setAliasSetDataBase(get(_util,_cust, DefaultBeanAliases.SET_DATA_BASE));
        _bean.setAliasGetDataBase(get(_util,_cust, DefaultBeanAliases.GET_DATA_BASE));
        _bean.setAliasBeforeDisplaying(get(_util,_cust, DefaultBeanAliases.BEFORE_DISPLAYING));
        _bean.setAliasStringMapObject(get(_util,_cust, DefaultBeanAliases.STRING_MAP_OBJECT));
        _bean.setAliasMessage(get(_util,_cust, DefaultBeanAliases.MESSAGE));
        _bean.setAliasNewMessage(get(_util,_cust, DefaultBeanAliases.NEW_MESSAGE));
        _bean.setAliasMessageFormat(get(_util,_cust, DefaultBeanAliases.MESSAGE_FORMAT));
        _bean.setAliasMessageGetArgs(get(_util,_cust, DefaultBeanAliases.MESSAGE_GET_ARGS));
        _bean.setAliasMessageSetArgs(get(_util,_cust, DefaultBeanAliases.MESSAGE_SET_ARGS));
        _content.getPredefTypes().getParams().setAliasSeedGenerator0Get0(get(_util,_cust,AliasParamPredefinedTypes.SEED_GENERATOR_0_GET_0));
        _content.getCharSeq().getParams().setAliasCharSequence0SubSequence0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_0));
        _content.getCharSeq().getParams().setAliasCharSequence0SubSequence1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_1));
        _content.getCharSeq().getParams().setAliasCharSequence0CharAt0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_CHAR_AT_0));
        _content.getCharSeq().getParams().setAliasCharSequence0Substring0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_0));
        _content.getCharSeq().getParams().setAliasCharSequence0Substring1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_1));
        _content.getCharSeq().getParams().setAliasCharSequence1Substring0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SUBSTRING_0));
        _content.getCharSeq().getParams().setAliasCharSequence0CompareTo0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_COMPARE_TO_0));
        _content.getCharSeq().getParams().setAliasCharSequence0Contains0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_CONTAINS_0));
        _content.getCharSeq().getParams().setAliasCharSequence0StartsWith0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_STARTS_WITH_0));
        _content.getCharSeq().getParams().setAliasCharSequence1StartsWith0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_0));
        _content.getCharSeq().getParams().setAliasCharSequence1StartsWith1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_1));
        _content.getCharSeq().getParams().setAliasCharSequence0EndsWith0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_ENDS_WITH_0));
        _content.getCharSeq().getParams().setAliasCharSequence0IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_INDEX_OF_0));
        _content.getCharSeq().getParams().setAliasCharSequence1IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_0));
        _content.getCharSeq().getParams().setAliasCharSequence1IndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_1));
        _content.getCharSeq().getParams().setAliasCharSequence2IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_2_INDEX_OF_0));
        _content.getCharSeq().getParams().setAliasCharSequence3IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_0));
        _content.getCharSeq().getParams().setAliasCharSequence3IndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_1));
        _content.getCharSeq().getParams().setAliasCharSequence0LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_LAST_INDEX_OF_0));
        _content.getCharSeq().getParams().setAliasCharSequence1LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_0));
        _content.getCharSeq().getParams().setAliasCharSequence1LastIndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_1));
        _content.getCharSeq().getParams().setAliasCharSequence2LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_2_LAST_INDEX_OF_0));
        _content.getCharSeq().getParams().setAliasCharSequence3LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_0));
        _content.getCharSeq().getParams().setAliasCharSequence3LastIndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_1));
        _content.getCharSeq().getParams().setAliasCharSequence0Format0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_FORMAT_0));
        _content.getCharSeq().getParams().setAliasCharSequence0Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_0));
        _content.getCharSeq().getParams().setAliasCharSequence1Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_0));
        _content.getCharSeq().getParams().setAliasCharSequence1Split1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_1));
        _content.getCharSeq().getParams().setAliasCharSequence2Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_2_SPLIT_0));
        _content.getCharSeq().getParams().setAliasCharSequence3Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_0));
        _content.getCharSeq().getParams().setAliasCharSequence3Split1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_1));
        _content.getCharSeq().getParams().setAliasCharSequence0SplitStrings0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_STRINGS_0));
        _content.getCharSeq().getParams().setAliasCharSequence1SplitStrings0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_0));
        _content.getCharSeq().getParams().setAliasCharSequence1SplitStrings1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_1));
        _content.getCharSeq().getParams().setAliasCharSequence0SplitChars0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_CHARS_0));
        _content.getCharSeq().getParams().setAliasCharSequence0RegionMatches0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_0));
        _content.getCharSeq().getParams().setAliasCharSequence0RegionMatches1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_1));
        _content.getCharSeq().getParams().setAliasCharSequence0RegionMatches2(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_2));
        _content.getCharSeq().getParams().setAliasCharSequence0RegionMatches3(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_3));
        _content.getCharSeq().getParams().setAliasCharSequence0Equals0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_0));
        _content.getCharSeq().getParams().setAliasCharSequence0Equals1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_1));
        _content.getCharSeq().getParams().setAliasString0EqualsIgnoreCase0(get(_util,_cust, AliasParamCharSequence.STRING_0_EQUALS_IGNORE_CASE_0));
        _content.getCharSeq().getParams().setAliasString0Compare0(get(_util,_cust, AliasParamCharSequence.STRING_0_COMPARE_0));
        _content.getCharSeq().getParams().setAliasString0Compare1(get(_util,_cust, AliasParamCharSequence.STRING_0_COMPARE_1));
        _content.getCharSeq().getParams().setAliasString0CompareToIgnoreCase0(get(_util,_cust, AliasParamCharSequence.STRING_0_COMPARE_TO_IGNORE_CASE_0));
        _content.getCharSeq().getParams().setAliasString0ReplaceString0(get(_util,_cust, AliasParamCharSequence.STRING_0_REPLACE_STRING_0));
        _content.getCharSeq().getParams().setAliasString0ReplaceString1(get(_util,_cust, AliasParamCharSequence.STRING_0_REPLACE_STRING_1));
        _content.getCharSeq().getParams().setAliasString1ReplaceString0(get(_util,_cust, AliasParamCharSequence.STRING_1_REPLACE_STRING_0));
        _content.getCharSeq().getParams().setAliasString1ReplaceString1(get(_util,_cust, AliasParamCharSequence.STRING_1_REPLACE_STRING_1));
        _content.getCharSeq().getParams().setAliasString0ReplaceMultiple0(get(_util,_cust, AliasParamCharSequence.STRING_0_REPLACE_MULTIPLE_0));
        _content.getCharSeq().getParams().setAliasString0RegionMatches0(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_0));
        _content.getCharSeq().getParams().setAliasString0RegionMatches1(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_1));
        _content.getCharSeq().getParams().setAliasString0RegionMatches2(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_2));
        _content.getCharSeq().getParams().setAliasString0RegionMatches3(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_3));
        _content.getCharSeq().getParams().setAliasString0RegionMatches4(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_4));
        _content.getCharSeq().getParams().setAliasString0ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_0_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString1ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_1_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString2ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_2_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString3ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_3_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString4ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_4_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString5ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_5_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString6ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_6_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString7ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_7_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString8ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_8_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString9ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_0));
        _content.getCharSeq().getParams().setAliasString9ValueOfMethod1(get(_util,_cust, AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_1));
        _content.getCharSeq().getParams().setAliasString9ValueOfMethod2(get(_util,_cust, AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_2));
        _content.getCharSeq().getParams().setAliasString0String0(get(_util,_cust, AliasParamCharSequence.STRING_0_STRING_0));
        _content.getCharSeq().getParams().setAliasString1String0(get(_util,_cust, AliasParamCharSequence.STRING_1_STRING_0));
        _content.getCharSeq().getParams().setAliasString1String1(get(_util,_cust, AliasParamCharSequence.STRING_1_STRING_1));
        _content.getCharSeq().getParams().setAliasString1String2(get(_util,_cust, AliasParamCharSequence.STRING_1_STRING_2));
        _content.getCharSeq().getParams().setAliasString2String0(get(_util,_cust, AliasParamCharSequence.STRING_2_STRING_0));
        _content.getCharSeq().getParams().setAliasString3String0(get(_util,_cust, AliasParamCharSequence.STRING_3_STRING_0));
        _content.getCharSeq().getParams().setAliasString3String1(get(_util,_cust, AliasParamCharSequence.STRING_3_STRING_1));
        _content.getCharSeq().getParams().setAliasString3String2(get(_util,_cust, AliasParamCharSequence.STRING_3_STRING_2));
        _content.getCharSeq().getParams().setAliasString4String0(get(_util,_cust, AliasParamCharSequence.STRING_4_STRING_0));
        _content.getCharSeq().getParams().setAliasStringBuilder0Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder1Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder2Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder3Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_3_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder4Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_4_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder5Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_5_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder6Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_6_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder7Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_7_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder8Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_8_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder9Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder9Append1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_APPEND_1));
        _content.getCharSeq().getParams().setAliasStringBuilder9Append2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_APPEND_2));
        _content.getCharSeq().getParams().setAliasStringBuilder10Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_10_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder11Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder11Append1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_APPEND_1));
        _content.getCharSeq().getParams().setAliasStringBuilder11Append2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_APPEND_2));
        _content.getCharSeq().getParams().setAliasStringBuilder12Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_12_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder13Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_APPEND_0));
        _content.getCharSeq().getParams().setAliasStringBuilder13Append1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_APPEND_1));
        _content.getCharSeq().getParams().setAliasStringBuilder13Append2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_APPEND_2));
        _content.getCharSeq().getParams().setAliasStringBuilder0Delete0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_DELETE_0));
        _content.getCharSeq().getParams().setAliasStringBuilder0Delete1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_DELETE_1));
        _content.getCharSeq().getParams().setAliasStringBuilder0DeleteCharAt0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_DELETE_CHAR_AT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder0Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder0Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder1Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder1Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder2Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder2Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder3Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_3_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder3Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_3_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder4Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_4_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder4Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_4_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder5Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_5_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder5Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_5_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder6Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_6_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder6Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_6_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder7Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_7_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder7Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_7_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder8Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_8_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder8Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_8_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder9Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder9Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder9Insert2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_2));
        _content.getCharSeq().getParams().setAliasStringBuilder9Insert3(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_3));
        _content.getCharSeq().getParams().setAliasStringBuilder10Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_10_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder10Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_10_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder11Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder11Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder11Insert2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_2));
        _content.getCharSeq().getParams().setAliasStringBuilder11Insert3(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_3));
        _content.getCharSeq().getParams().setAliasStringBuilder12Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_12_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder12Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_12_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder13Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder13Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder13Insert2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_2));
        _content.getCharSeq().getParams().setAliasStringBuilder13Insert3(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_3));
        _content.getCharSeq().getParams().setAliasStringBuilder0Replace0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_REPLACE_0));
        _content.getCharSeq().getParams().setAliasStringBuilder0Replace1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_REPLACE_1));
        _content.getCharSeq().getParams().setAliasStringBuilder0Replace2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_REPLACE_2));
        _content.getCharSeq().getParams().setAliasStringBuilder0SetCharAt0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_0));
        _content.getCharSeq().getParams().setAliasStringBuilder0SetCharAt1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_1));
        _content.getCharSeq().getParams().setAliasStringBuilder0SetLength0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SET_LENGTH_0));
        _content.getCharSeq().getParams().setAliasStringBuilder0EnsureCapacity0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_ENSURE_CAPACITY_0));
        _content.getCharSeq().getParams().setAliasStringBuilder0Same0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SAME_0));
        _content.getCharSeq().getParams().setAliasStringBuilder0Same1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SAME_1));
        _content.getCharSeq().getParams().setAliasStringBuilder0StringBuilder0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_STRING_BUILDER_0));
        _content.getCharSeq().getParams().setAliasStringBuilder1StringBuilder0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_STRING_BUILDER_0));
        _content.getCharSeq().getParams().setAliasStringBuilder2StringBuilder0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_STRING_BUILDER_0));
        _content.getCharSeq().getParams().setAliasReplacement0Replacement0(get(_util,_cust, AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_0));
        _content.getCharSeq().getParams().setAliasReplacement0Replacement1(get(_util,_cust, AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_1));
        _content.getCoreNames().getParams().setAliasError0CurrentStack0(get(_util,_cust, AliasParamCore.ERROR_0_CURRENT_STACK_0));
        _content.getCoreNames().getParams().setAliasError0ToStringMethod0(get(_util,_cust, AliasParamCore.ERROR_0_TO_STRING_METHOD_0));
        _content.getCoreNames().getParams().setAliasEnums0Name0(get(_util,_cust, AliasParamCore.ENUMS_0_NAME_0));
        _content.getCoreNames().getParams().setAliasEnums0Ordinal0(get(_util,_cust, AliasParamCore.ENUMS_0_ORDINAL_0));
        _content.getCoreNames().getParams().setAliasObjectsUtil0SameRef0(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SAME_REF_0));
        _content.getCoreNames().getParams().setAliasObjectsUtil0SameRef1(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SAME_REF_1));
        _content.getCoreNames().getParams().setAliasObjectsUtil0GetParent0(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_GET_PARENT_0));
        _content.getCoreNames().getParams().setAliasObjectsUtil0SetParent0(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_0));
        _content.getCoreNames().getParams().setAliasObjectsUtil0SetParent1(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_1));
        _content.getCoreNames().getParams().setAliasStringUtil0ValueOfMethod0(get(_util,_cust, AliasParamCore.STRING_UTIL_0_VALUE_OF_METHOD_0));
        _content.getCoreNames().getParams().setAliasResources0ReadResources0(get(_util,_cust, AliasParamCore.RESOURCES_0_READ_RESOURCES_0));
        _content.getCoreNames().getParams().setAliasResources0ReadResourcesIndex0(get(_util,_cust, AliasParamCore.RESOURCES_0_READ_RESOURCES_INDEX_0));
        _content.getMathRef().getParams().setAliasMath0Abs0(get(_util,_cust, AliasParamMath.MATH_0_ABS_0));
        _content.getMathRef().getParams().setAliasMath1Abs0(get(_util,_cust, AliasParamMath.MATH_1_ABS_0));
        _content.getMathRef().getParams().setAliasMath0Quot0(get(_util,_cust, AliasParamMath.MATH_0_QUOT_0));
        _content.getMathRef().getParams().setAliasMath0Quot1(get(_util,_cust, AliasParamMath.MATH_0_QUOT_1));
        _content.getMathRef().getParams().setAliasMath1Quot0(get(_util,_cust, AliasParamMath.MATH_1_QUOT_0));
        _content.getMathRef().getParams().setAliasMath1Quot1(get(_util,_cust, AliasParamMath.MATH_1_QUOT_1));
        _content.getMathRef().getParams().setAliasMath0Mod0(get(_util,_cust, AliasParamMath.MATH_0_MOD_0));
        _content.getMathRef().getParams().setAliasMath0Mod1(get(_util,_cust, AliasParamMath.MATH_0_MOD_1));
        _content.getMathRef().getParams().setAliasMath1Mod0(get(_util,_cust, AliasParamMath.MATH_1_MOD_0));
        _content.getMathRef().getParams().setAliasMath1Mod1(get(_util,_cust, AliasParamMath.MATH_1_MOD_1));
        _content.getMathRef().getParams().setAliasMath0Plus0(get(_util,_cust, AliasParamMath.MATH_0_PLUS_0));
        _content.getMathRef().getParams().setAliasMath1Plus0(get(_util,_cust, AliasParamMath.MATH_1_PLUS_0));
        _content.getMathRef().getParams().setAliasMath2Plus0(get(_util,_cust, AliasParamMath.MATH_2_PLUS_0));
        _content.getMathRef().getParams().setAliasMath3Plus0(get(_util,_cust, AliasParamMath.MATH_3_PLUS_0));
        _content.getMathRef().getParams().setAliasMath0Minus0(get(_util,_cust, AliasParamMath.MATH_0_MINUS_0));
        _content.getMathRef().getParams().setAliasMath1Minus0(get(_util,_cust, AliasParamMath.MATH_1_MINUS_0));
        _content.getMathRef().getParams().setAliasMath2Minus0(get(_util,_cust, AliasParamMath.MATH_2_MINUS_0));
        _content.getMathRef().getParams().setAliasMath3Minus0(get(_util,_cust, AliasParamMath.MATH_3_MINUS_0));
        _content.getMathRef().getParams().setAliasMath0Neg0(get(_util,_cust, AliasParamMath.MATH_0_NEG_0));
        _content.getMathRef().getParams().setAliasMath0NegBin0(get(_util,_cust, AliasParamMath.MATH_0_NEG_BIN_0));
        _content.getMathRef().getParams().setAliasMath1NegBin0(get(_util,_cust, AliasParamMath.MATH_1_NEG_BIN_0));
        _content.getMathRef().getParams().setAliasMath4Plus0(get(_util,_cust, AliasParamMath.MATH_4_PLUS_0));
        _content.getMathRef().getParams().setAliasMath4Plus1(get(_util,_cust, AliasParamMath.MATH_4_PLUS_1));
        _content.getMathRef().getParams().setAliasMath5Plus0(get(_util,_cust, AliasParamMath.MATH_5_PLUS_0));
        _content.getMathRef().getParams().setAliasMath5Plus1(get(_util,_cust, AliasParamMath.MATH_5_PLUS_1));
        _content.getMathRef().getParams().setAliasMath6Plus0(get(_util,_cust, AliasParamMath.MATH_6_PLUS_0));
        _content.getMathRef().getParams().setAliasMath6Plus1(get(_util,_cust, AliasParamMath.MATH_6_PLUS_1));
        _content.getMathRef().getParams().setAliasMath7Plus0(get(_util,_cust, AliasParamMath.MATH_7_PLUS_0));
        _content.getMathRef().getParams().setAliasMath7Plus1(get(_util,_cust, AliasParamMath.MATH_7_PLUS_1));
        _content.getMathRef().getParams().setAliasMath4Minus0(get(_util,_cust, AliasParamMath.MATH_4_MINUS_0));
        _content.getMathRef().getParams().setAliasMath4Minus1(get(_util,_cust, AliasParamMath.MATH_4_MINUS_1));
        _content.getMathRef().getParams().setAliasMath5Minus0(get(_util,_cust, AliasParamMath.MATH_5_MINUS_0));
        _content.getMathRef().getParams().setAliasMath5Minus1(get(_util,_cust, AliasParamMath.MATH_5_MINUS_1));
        _content.getMathRef().getParams().setAliasMath6Minus0(get(_util,_cust, AliasParamMath.MATH_6_MINUS_0));
        _content.getMathRef().getParams().setAliasMath6Minus1(get(_util,_cust, AliasParamMath.MATH_6_MINUS_1));
        _content.getMathRef().getParams().setAliasMath7Minus0(get(_util,_cust, AliasParamMath.MATH_7_MINUS_0));
        _content.getMathRef().getParams().setAliasMath7Minus1(get(_util,_cust, AliasParamMath.MATH_7_MINUS_1));
        _content.getMathRef().getParams().setAliasMath0Mult0(get(_util,_cust, AliasParamMath.MATH_0_MULT_0));
        _content.getMathRef().getParams().setAliasMath0Mult1(get(_util,_cust, AliasParamMath.MATH_0_MULT_1));
        _content.getMathRef().getParams().setAliasMath1Mult0(get(_util,_cust, AliasParamMath.MATH_1_MULT_0));
        _content.getMathRef().getParams().setAliasMath1Mult1(get(_util,_cust, AliasParamMath.MATH_1_MULT_1));
        _content.getMathRef().getParams().setAliasMath2Mult0(get(_util,_cust, AliasParamMath.MATH_2_MULT_0));
        _content.getMathRef().getParams().setAliasMath2Mult1(get(_util,_cust, AliasParamMath.MATH_2_MULT_1));
        _content.getMathRef().getParams().setAliasMath3Mult0(get(_util,_cust, AliasParamMath.MATH_3_MULT_0));
        _content.getMathRef().getParams().setAliasMath3Mult1(get(_util,_cust, AliasParamMath.MATH_3_MULT_1));
        _content.getMathRef().getParams().setAliasMath0BinQuot0(get(_util,_cust, AliasParamMath.MATH_0_BIN_QUOT_0));
        _content.getMathRef().getParams().setAliasMath0BinQuot1(get(_util,_cust, AliasParamMath.MATH_0_BIN_QUOT_1));
        _content.getMathRef().getParams().setAliasMath1BinQuot0(get(_util,_cust, AliasParamMath.MATH_1_BIN_QUOT_0));
        _content.getMathRef().getParams().setAliasMath1BinQuot1(get(_util,_cust, AliasParamMath.MATH_1_BIN_QUOT_1));
        _content.getMathRef().getParams().setAliasMath2BinQuot0(get(_util,_cust, AliasParamMath.MATH_2_BIN_QUOT_0));
        _content.getMathRef().getParams().setAliasMath2BinQuot1(get(_util,_cust, AliasParamMath.MATH_2_BIN_QUOT_1));
        _content.getMathRef().getParams().setAliasMath3BinQuot0(get(_util,_cust, AliasParamMath.MATH_3_BIN_QUOT_0));
        _content.getMathRef().getParams().setAliasMath3BinQuot1(get(_util,_cust, AliasParamMath.MATH_3_BIN_QUOT_1));
        _content.getMathRef().getParams().setAliasMath0BinMod0(get(_util,_cust, AliasParamMath.MATH_0_BIN_MOD_0));
        _content.getMathRef().getParams().setAliasMath0BinMod1(get(_util,_cust, AliasParamMath.MATH_0_BIN_MOD_1));
        _content.getMathRef().getParams().setAliasMath1BinMod0(get(_util,_cust, AliasParamMath.MATH_1_BIN_MOD_0));
        _content.getMathRef().getParams().setAliasMath1BinMod1(get(_util,_cust, AliasParamMath.MATH_1_BIN_MOD_1));
        _content.getMathRef().getParams().setAliasMath2BinMod0(get(_util,_cust, AliasParamMath.MATH_2_BIN_MOD_0));
        _content.getMathRef().getParams().setAliasMath2BinMod1(get(_util,_cust, AliasParamMath.MATH_2_BIN_MOD_1));
        _content.getMathRef().getParams().setAliasMath3BinMod0(get(_util,_cust, AliasParamMath.MATH_3_BIN_MOD_0));
        _content.getMathRef().getParams().setAliasMath3BinMod1(get(_util,_cust, AliasParamMath.MATH_3_BIN_MOD_1));
        _content.getMathRef().getParams().setAliasMath0And0(get(_util,_cust, AliasParamMath.MATH_0_AND_0));
        _content.getMathRef().getParams().setAliasMath0And1(get(_util,_cust, AliasParamMath.MATH_0_AND_1));
        _content.getMathRef().getParams().setAliasMath1And0(get(_util,_cust, AliasParamMath.MATH_1_AND_0));
        _content.getMathRef().getParams().setAliasMath1And1(get(_util,_cust, AliasParamMath.MATH_1_AND_1));
        _content.getMathRef().getParams().setAliasMath2And0(get(_util,_cust, AliasParamMath.MATH_2_AND_0));
        _content.getMathRef().getParams().setAliasMath2And1(get(_util,_cust, AliasParamMath.MATH_2_AND_1));
        _content.getMathRef().getParams().setAliasMath0Or0(get(_util,_cust, AliasParamMath.MATH_0_OR_0));
        _content.getMathRef().getParams().setAliasMath0Or1(get(_util,_cust, AliasParamMath.MATH_0_OR_1));
        _content.getMathRef().getParams().setAliasMath1Or0(get(_util,_cust, AliasParamMath.MATH_1_OR_0));
        _content.getMathRef().getParams().setAliasMath1Or1(get(_util,_cust, AliasParamMath.MATH_1_OR_1));
        _content.getMathRef().getParams().setAliasMath2Or0(get(_util,_cust, AliasParamMath.MATH_2_OR_0));
        _content.getMathRef().getParams().setAliasMath2Or1(get(_util,_cust, AliasParamMath.MATH_2_OR_1));
        _content.getMathRef().getParams().setAliasMath0Xor0(get(_util,_cust, AliasParamMath.MATH_0_XOR_0));
        _content.getMathRef().getParams().setAliasMath0Xor1(get(_util,_cust, AliasParamMath.MATH_0_XOR_1));
        _content.getMathRef().getParams().setAliasMath1Xor0(get(_util,_cust, AliasParamMath.MATH_1_XOR_0));
        _content.getMathRef().getParams().setAliasMath1Xor1(get(_util,_cust, AliasParamMath.MATH_1_XOR_1));
        _content.getMathRef().getParams().setAliasMath2Xor0(get(_util,_cust, AliasParamMath.MATH_2_XOR_0));
        _content.getMathRef().getParams().setAliasMath2Xor1(get(_util,_cust, AliasParamMath.MATH_2_XOR_1));
        _content.getMathRef().getParams().setAliasMath0ShiftLeft0(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_LEFT_0));
        _content.getMathRef().getParams().setAliasMath0ShiftLeft1(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_LEFT_1));
        _content.getMathRef().getParams().setAliasMath1ShiftLeft0(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_LEFT_0));
        _content.getMathRef().getParams().setAliasMath1ShiftLeft1(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_LEFT_1));
        _content.getMathRef().getParams().setAliasMath0ShiftRight0(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_RIGHT_0));
        _content.getMathRef().getParams().setAliasMath0ShiftRight1(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_RIGHT_1));
        _content.getMathRef().getParams().setAliasMath1ShiftRight0(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_RIGHT_0));
        _content.getMathRef().getParams().setAliasMath1ShiftRight1(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_RIGHT_1));
        _content.getMathRef().getParams().setAliasMath0BitShiftLeft0(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_LEFT_0));
        _content.getMathRef().getParams().setAliasMath0BitShiftLeft1(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_LEFT_1));
        _content.getMathRef().getParams().setAliasMath1BitShiftLeft0(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_LEFT_0));
        _content.getMathRef().getParams().setAliasMath1BitShiftLeft1(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_LEFT_1));
        _content.getMathRef().getParams().setAliasMath0BitShiftRight0(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_0));
        _content.getMathRef().getParams().setAliasMath0BitShiftRight1(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_1));
        _content.getMathRef().getParams().setAliasMath1BitShiftRight0(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_0));
        _content.getMathRef().getParams().setAliasMath1BitShiftRight1(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_1));
        _content.getMathRef().getParams().setAliasMath0RotateLeft0(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_LEFT_0));
        _content.getMathRef().getParams().setAliasMath0RotateLeft1(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_LEFT_1));
        _content.getMathRef().getParams().setAliasMath1RotateLeft0(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_LEFT_0));
        _content.getMathRef().getParams().setAliasMath1RotateLeft1(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_LEFT_1));
        _content.getMathRef().getParams().setAliasMath0RotateRight0(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_RIGHT_0));
        _content.getMathRef().getParams().setAliasMath0RotateRight1(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_RIGHT_1));
        _content.getMathRef().getParams().setAliasMath1RotateRight0(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_RIGHT_0));
        _content.getMathRef().getParams().setAliasMath1RotateRight1(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_RIGHT_1));
        _content.getMathRef().getParams().setAliasMath0Le0(get(_util,_cust, AliasParamMath.MATH_0_LE_0));
        _content.getMathRef().getParams().setAliasMath0Le1(get(_util,_cust, AliasParamMath.MATH_0_LE_1));
        _content.getMathRef().getParams().setAliasMath0Ge0(get(_util,_cust, AliasParamMath.MATH_0_GE_0));
        _content.getMathRef().getParams().setAliasMath0Ge1(get(_util,_cust, AliasParamMath.MATH_0_GE_1));
        _content.getMathRef().getParams().setAliasMath0Lt0(get(_util,_cust, AliasParamMath.MATH_0_LT_0));
        _content.getMathRef().getParams().setAliasMath0Lt1(get(_util,_cust, AliasParamMath.MATH_0_LT_1));
        _content.getMathRef().getParams().setAliasMath0Gt0(get(_util,_cust, AliasParamMath.MATH_0_GT_0));
        _content.getMathRef().getParams().setAliasMath0Gt1(get(_util,_cust, AliasParamMath.MATH_0_GT_1));
        _content.getMathRef().getParams().setAliasMath1Le0(get(_util,_cust, AliasParamMath.MATH_1_LE_0));
        _content.getMathRef().getParams().setAliasMath1Le1(get(_util,_cust, AliasParamMath.MATH_1_LE_1));
        _content.getMathRef().getParams().setAliasMath1Ge0(get(_util,_cust, AliasParamMath.MATH_1_GE_0));
        _content.getMathRef().getParams().setAliasMath1Ge1(get(_util,_cust, AliasParamMath.MATH_1_GE_1));
        _content.getMathRef().getParams().setAliasMath1Lt0(get(_util,_cust, AliasParamMath.MATH_1_LT_0));
        _content.getMathRef().getParams().setAliasMath1Lt1(get(_util,_cust, AliasParamMath.MATH_1_LT_1));
        _content.getMathRef().getParams().setAliasMath1Gt0(get(_util,_cust, AliasParamMath.MATH_1_GT_0));
        _content.getMathRef().getParams().setAliasMath1Gt1(get(_util,_cust, AliasParamMath.MATH_1_GT_1));
        _content.getMathRef().getParams().setAliasMath0Random0(get(_util,_cust, AliasParamMath.MATH_0_RANDOM_0));
        _content.getMathRef().getParams().setAliasMath0Seed0(get(_util,_cust, AliasParamMath.MATH_0_SEED_0));
        _content.getNbAlias().getParams().setAliasBoolean0Compare0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_COMPARE_0));
        _content.getNbAlias().getParams().setAliasBoolean0Compare1(get(_util,_cust, AliasParamNumber.BOOLEAN_0_COMPARE_1));
        _content.getNbAlias().getParams().setAliasBoolean0CompareTo0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_COMPARE_TO_0));
        _content.getNbAlias().getParams().setAliasBoolean0Equals0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_EQUALS_0));
        _content.getNbAlias().getParams().setAliasBoolean0ParseBoolean0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_PARSE_BOOLEAN_0));
        _content.getNbAlias().getParams().setAliasBoolean0ToStringMethod0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_TO_STRING_METHOD_0));
        _content.getNbAlias().getParams().setAliasBoolean0ValueOfMethod0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_VALUE_OF_METHOD_0));
        _content.getNbAlias().getParams().setAliasBoolean1ValueOfMethod0(get(_util,_cust, AliasParamNumber.BOOLEAN_1_VALUE_OF_METHOD_0));
        _content.getNbAlias().getParams().setAliasBoolean0Boolean0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_BOOLEAN_0));
        _content.getNbAlias().getParams().setAliasBoolean1Boolean0(get(_util,_cust, AliasParamNumber.BOOLEAN_1_BOOLEAN_0));
        _content.getNbAlias().getParams().setAliasByte0ToStringMethod0(get(_util,_cust, AliasParamNumber.BYTE_0_TO_STRING_METHOD_0));
        _content.getNbAlias().getParams().setAliasByte0ParseByte0(get(_util,_cust, AliasParamNumber.BYTE_0_PARSE_BYTE_0));
        _content.getNbAlias().getParams().setAliasByte1ParseByte0(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_0));
        _content.getNbAlias().getParams().setAliasByte1ParseByte1(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_1));
        _content.getNbAlias().getParams().setAliasByte0CompareTo0(get(_util,_cust, AliasParamNumber.BYTE_0_COMPARE_TO_0));
        _content.getNbAlias().getParams().setAliasByte0Compare0(get(_util,_cust, AliasParamNumber.BYTE_0_COMPARE_0));
        _content.getNbAlias().getParams().setAliasByte0Compare1(get(_util,_cust, AliasParamNumber.BYTE_0_COMPARE_1));
        _content.getNbAlias().getParams().setAliasByte0ParseByteOrNull0(get(_util,_cust, AliasParamNumber.BYTE_0_PARSE_BYTE_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasByte1ParseByteOrNull0(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasByte1ParseByteOrNull1(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_1));
        _content.getNbAlias().getParams().setAliasByte0Byte0(get(_util,_cust, AliasParamNumber.BYTE_0_BYTE_0));
        _content.getNbAlias().getParams().setAliasByte1Byte0(get(_util,_cust, AliasParamNumber.BYTE_1_BYTE_0));
        _content.getNbAlias().getParams().setAliasShort0ToStringMethod0(get(_util,_cust, AliasParamNumber.SHORT_0_TO_STRING_METHOD_0));
        _content.getNbAlias().getParams().setAliasShort0ParseShort0(get(_util,_cust, AliasParamNumber.SHORT_0_PARSE_SHORT_0));
        _content.getNbAlias().getParams().setAliasShort1ParseShort0(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_0));
        _content.getNbAlias().getParams().setAliasShort1ParseShort1(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_1));
        _content.getNbAlias().getParams().setAliasShort0CompareTo0(get(_util,_cust, AliasParamNumber.SHORT_0_COMPARE_TO_0));
        _content.getNbAlias().getParams().setAliasShort0Compare0(get(_util,_cust, AliasParamNumber.SHORT_0_COMPARE_0));
        _content.getNbAlias().getParams().setAliasShort0Compare1(get(_util,_cust, AliasParamNumber.SHORT_0_COMPARE_1));
        _content.getNbAlias().getParams().setAliasShort0ParseShortOrNull0(get(_util,_cust, AliasParamNumber.SHORT_0_PARSE_SHORT_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasShort1ParseShortOrNull0(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasShort1ParseShortOrNull1(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_1));
        _content.getNbAlias().getParams().setAliasShort0Short0(get(_util,_cust, AliasParamNumber.SHORT_0_SHORT_0));
        _content.getNbAlias().getParams().setAliasShort1Short0(get(_util,_cust, AliasParamNumber.SHORT_1_SHORT_0));
        _content.getNbAlias().getParams().setAliasInteger0ToStringMethod0(get(_util,_cust, AliasParamNumber.INTEGER_0_TO_STRING_METHOD_0));
        _content.getNbAlias().getParams().setAliasInteger0ParseInt0(get(_util,_cust, AliasParamNumber.INTEGER_0_PARSE_INT_0));
        _content.getNbAlias().getParams().setAliasInteger1ParseInt0(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_0));
        _content.getNbAlias().getParams().setAliasInteger1ParseInt1(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_1));
        _content.getNbAlias().getParams().setAliasInteger0CompareTo0(get(_util,_cust, AliasParamNumber.INTEGER_0_COMPARE_TO_0));
        _content.getNbAlias().getParams().setAliasInteger0Compare0(get(_util,_cust, AliasParamNumber.INTEGER_0_COMPARE_0));
        _content.getNbAlias().getParams().setAliasInteger0Compare1(get(_util,_cust, AliasParamNumber.INTEGER_0_COMPARE_1));
        _content.getNbAlias().getParams().setAliasInteger0ParseIntOrNull0(get(_util,_cust, AliasParamNumber.INTEGER_0_PARSE_INT_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasInteger1ParseIntOrNull0(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasInteger1ParseIntOrNull1(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_1));
        _content.getNbAlias().getParams().setAliasInteger0Integer0(get(_util,_cust, AliasParamNumber.INTEGER_0_INTEGER_0));
        _content.getNbAlias().getParams().setAliasInteger1Integer0(get(_util,_cust, AliasParamNumber.INTEGER_1_INTEGER_0));
        _content.getNbAlias().getParams().setAliasLong0ToStringMethod0(get(_util,_cust, AliasParamNumber.LONG_0_TO_STRING_METHOD_0));
        _content.getNbAlias().getParams().setAliasLong0ParseLong0(get(_util,_cust, AliasParamNumber.LONG_0_PARSE_LONG_0));
        _content.getNbAlias().getParams().setAliasLong1ParseLong0(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_0));
        _content.getNbAlias().getParams().setAliasLong1ParseLong1(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_1));
        _content.getNbAlias().getParams().setAliasLong0CompareTo0(get(_util,_cust, AliasParamNumber.LONG_0_COMPARE_TO_0));
        _content.getNbAlias().getParams().setAliasLong0Compare0(get(_util,_cust, AliasParamNumber.LONG_0_COMPARE_0));
        _content.getNbAlias().getParams().setAliasLong0Compare1(get(_util,_cust, AliasParamNumber.LONG_0_COMPARE_1));
        _content.getNbAlias().getParams().setAliasLong0ParseLongOrNull0(get(_util,_cust, AliasParamNumber.LONG_0_PARSE_LONG_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasLong1ParseLongOrNull0(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasLong1ParseLongOrNull1(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_1));
        _content.getNbAlias().getParams().setAliasLong0Long0(get(_util,_cust, AliasParamNumber.LONG_0_LONG_0));
        _content.getNbAlias().getParams().setAliasLong1Long0(get(_util,_cust, AliasParamNumber.LONG_1_LONG_0));
        _content.getNbAlias().getParams().setAliasFloat0ToStringMethod0(get(_util,_cust, AliasParamNumber.FLOAT_0_TO_STRING_METHOD_0));
        _content.getNbAlias().getParams().setAliasFloat0ParseFloat0(get(_util,_cust, AliasParamNumber.FLOAT_0_PARSE_FLOAT_0));
        _content.getNbAlias().getParams().setAliasFloat0CompareTo0(get(_util,_cust, AliasParamNumber.FLOAT_0_COMPARE_TO_0));
        _content.getNbAlias().getParams().setAliasFloat0Compare0(get(_util,_cust, AliasParamNumber.FLOAT_0_COMPARE_0));
        _content.getNbAlias().getParams().setAliasFloat0Compare1(get(_util,_cust, AliasParamNumber.FLOAT_0_COMPARE_1));
        _content.getNbAlias().getParams().setAliasFloat0ParseFloatOrNull0(get(_util,_cust, AliasParamNumber.FLOAT_0_PARSE_FLOAT_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasFloat0IsInfinite0(get(_util,_cust, AliasParamNumber.FLOAT_0_IS_INFINITE_0));
        _content.getNbAlias().getParams().setAliasFloat0IsNan0(get(_util,_cust, AliasParamNumber.FLOAT_0_IS_NAN_0));
        _content.getNbAlias().getParams().setAliasFloat0Float0(get(_util,_cust, AliasParamNumber.FLOAT_0_FLOAT_0));
        _content.getNbAlias().getParams().setAliasFloat1Float0(get(_util,_cust, AliasParamNumber.FLOAT_1_FLOAT_0));
        _content.getNbAlias().getParams().setAliasDouble0ToStringMethod0(get(_util,_cust, AliasParamNumber.DOUBLE_0_TO_STRING_METHOD_0));
        _content.getNbAlias().getParams().setAliasDouble0ParseDouble0(get(_util,_cust, AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_0));
        _content.getNbAlias().getParams().setAliasDouble0CompareTo0(get(_util,_cust, AliasParamNumber.DOUBLE_0_COMPARE_TO_0));
        _content.getNbAlias().getParams().setAliasDouble0Compare0(get(_util,_cust, AliasParamNumber.DOUBLE_0_COMPARE_0));
        _content.getNbAlias().getParams().setAliasDouble0Compare1(get(_util,_cust, AliasParamNumber.DOUBLE_0_COMPARE_1));
        _content.getNbAlias().getParams().setAliasDouble0ParseDoubleOrNull0(get(_util,_cust, AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_OR_NULL_0));
        _content.getNbAlias().getParams().setAliasDouble0IsInfinite0(get(_util,_cust, AliasParamNumber.DOUBLE_0_IS_INFINITE_0));
        _content.getNbAlias().getParams().setAliasDouble0IsNan0(get(_util,_cust, AliasParamNumber.DOUBLE_0_IS_NAN_0));
        _content.getNbAlias().getParams().setAliasDouble0Double0(get(_util,_cust, AliasParamNumber.DOUBLE_0_DOUBLE_0));
        _content.getNbAlias().getParams().setAliasDouble1Double0(get(_util,_cust, AliasParamNumber.DOUBLE_1_DOUBLE_0));
        _content.getNbAlias().getParams().setAliasNumber0ToStringMethod0(get(_util,_cust, AliasParamNumber.NUMBER_0_TO_STRING_METHOD_0));
        _content.getNbAlias().getParams().setAliasNumber0Equals0(get(_util,_cust, AliasParamNumber.NUMBER_0_EQUALS_0));
        _content.getNbAlias().getParams().setAliasNumber1Equals0(get(_util,_cust, AliasParamNumber.NUMBER_1_EQUALS_0));
        _content.getNbAlias().getParams().setAliasNumber1Equals1(get(_util,_cust, AliasParamNumber.NUMBER_1_EQUALS_1));
        _content.getNbAlias().getParams().setAliasNumber0CompareTo0(get(_util,_cust, AliasParamNumber.NUMBER_0_COMPARE_TO_0));
        _content.getNbAlias().getParams().setAliasNumber0Compare0(get(_util,_cust, AliasParamNumber.NUMBER_0_COMPARE_0));
        _content.getNbAlias().getParams().setAliasNumber0Compare1(get(_util,_cust, AliasParamNumber.NUMBER_0_COMPARE_1));
        _content.getNbAlias().getParams().setAliasCharacter0CompareTo0(get(_util,_cust, AliasParamNumber.CHARACTER_0_COMPARE_TO_0));
        _content.getNbAlias().getParams().setAliasCharacter0Compare0(get(_util,_cust, AliasParamNumber.CHARACTER_0_COMPARE_0));
        _content.getNbAlias().getParams().setAliasCharacter0Compare1(get(_util,_cust, AliasParamNumber.CHARACTER_0_COMPARE_1));
        _content.getNbAlias().getParams().setAliasCharacter0Digit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_DIGIT_0));
        _content.getNbAlias().getParams().setAliasCharacter0Digit1(get(_util,_cust, AliasParamNumber.CHARACTER_0_DIGIT_1));
        _content.getNbAlias().getParams().setAliasCharacter0ForDigit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_FOR_DIGIT_0));
        _content.getNbAlias().getParams().setAliasCharacter0ForDigit1(get(_util,_cust, AliasParamNumber.CHARACTER_0_FOR_DIGIT_1));
        _content.getNbAlias().getParams().setAliasCharacter0GetDirectionality0(get(_util,_cust, AliasParamNumber.CHARACTER_0_GET_DIRECTIONALITY_0));
        _content.getNbAlias().getParams().setAliasCharacter0GetType0(get(_util,_cust, AliasParamNumber.CHARACTER_0_GET_TYPE_0));
        _content.getNbAlias().getParams().setAliasCharacter0IsDigit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_DIGIT_0));
        _content.getNbAlias().getParams().setAliasCharacter0IsLetter0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_LETTER_0));
        _content.getNbAlias().getParams().setAliasCharacter0IsLetterOrDigit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_LETTER_OR_DIGIT_0));
        _content.getNbAlias().getParams().setAliasCharacter0IsWordChar0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_WORD_CHAR_0));
        _content.getNbAlias().getParams().setAliasCharacter0IsWhitespace0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_WHITESPACE_0));
        _content.getNbAlias().getParams().setAliasCharacter0IsLowerCase0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_LOWER_CASE_0));
        _content.getNbAlias().getParams().setAliasCharacter0IsUpperCase0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_UPPER_CASE_0));
        _content.getNbAlias().getParams().setAliasCharacter0IsSpace0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_SPACE_0));
        _content.getNbAlias().getParams().setAliasCharacter0ToLowerCaseChar0(get(_util,_cust, AliasParamNumber.CHARACTER_0_TO_LOWER_CASE_CHAR_0));
        _content.getNbAlias().getParams().setAliasCharacter0ToUpperCaseChar0(get(_util,_cust, AliasParamNumber.CHARACTER_0_TO_UPPER_CASE_CHAR_0));
        _content.getNbAlias().getParams().setAliasCharacter0ToStringMethod0(get(_util,_cust, AliasParamNumber.CHARACTER_0_TO_STRING_METHOD_0));
        _content.getNbAlias().getParams().setAliasCharacter0Character0(get(_util,_cust, AliasParamNumber.CHARACTER_0_CHARACTER_0));
        _content.getReflect().getParams().setAliasFct0Call0(get(_util,_cust, AliasParamReflection.FCT_0_CALL_0));
        _content.getReflect().getParams().setAliasClassType0GetClass0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_CLASS_0));
        _content.getReflect().getParams().setAliasClassType0ForName0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_FOR_NAME_0));
        _content.getReflect().getParams().setAliasClassType0ForName1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_FOR_NAME_1));
        _content.getReflect().getParams().setAliasClassType1ForName0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_1_FOR_NAME_0));
        _content.getReflect().getParams().setAliasClassType0IsInstance0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_IS_INSTANCE_0));
        _content.getReflect().getParams().setAliasClassType0IsAssignableFrom0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0));
        _content.getReflect().getParams().setAliasClassType0DefaultInstance0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_DEFAULT_INSTANCE_0));
        _content.getReflect().getParams().setAliasClassType0EnumValueOf0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ENUM_VALUE_OF_0));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredConstructors0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredConstructors1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredFields0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_FIELDS_0));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods3(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredMethods0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_0));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredMethods1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_1));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredMethods2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_2));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredMethods3(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_3));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredExplicits0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredImplicits0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredBlocks0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_0));
        _content.getReflect().getParams().setAliasClassType0GetDeclaredBlocks1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_1));
        _content.getReflect().getParams().setAliasClassType0MakeGeneric0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_MAKE_GENERIC_0));
        _content.getReflect().getParams().setAliasClassType0MakeWildCard0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_MAKE_WILD_CARD_0));
        _content.getReflect().getParams().setAliasClassType0GetOperators0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_0));
        _content.getReflect().getParams().setAliasClassType0GetOperators1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_1));
        _content.getReflect().getParams().setAliasClassType0GetOperators2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_2));
        _content.getReflect().getParams().setAliasClassType0ArrayNewInstance0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0));
        _content.getReflect().getParams().setAliasClassType0ArrayGetLength0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_LENGTH_0));
        _content.getReflect().getParams().setAliasClassType0ArrayGet0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_0));
        _content.getReflect().getParams().setAliasClassType0ArrayGet1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_1));
        _content.getReflect().getParams().setAliasClassType0ArraySet0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_0));
        _content.getReflect().getParams().setAliasClassType0ArraySet1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_1));
        _content.getReflect().getParams().setAliasClassType0ArraySet2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_2));
        _content.getReflect().getParams().setAliasConstructor0NewInstance0(get(_util,_cust, AliasParamReflection.CONSTRUCTOR_0_NEW_INSTANCE_0));
        _content.getReflect().getParams().setAliasField0GetField0(get(_util,_cust, AliasParamReflection.FIELD_0_GET_FIELD_0));
        _content.getReflect().getParams().setAliasField0SetField0(get(_util,_cust, AliasParamReflection.FIELD_0_SET_FIELD_0));
        _content.getReflect().getParams().setAliasField0SetField1(get(_util,_cust, AliasParamReflection.FIELD_0_SET_FIELD_1));
        _content.getReflect().getParams().setAliasMethod0Invoke0(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_0));
        _content.getReflect().getParams().setAliasMethod0Invoke1(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_1));
        _content.getReflect().getParams().setAliasMethod0InvokeDirect0(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_DIRECT_0));
        _content.getReflect().getParams().setAliasMethod0InvokeDirect1(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_DIRECT_1));
        _content.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        _content.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars1(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1));
        _content.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars2(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2));
        _content.getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        _content.getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLocalVars1(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1));
        _content.getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        _content.getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLocalVars1(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1));
        _content.getReflect().getParams().setAliasMethod3GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        _content.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        _content.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars1(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1));
        _content.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars2(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2));
        _content.getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        _content.getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLoopVars1(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1));
        _content.getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        _content.getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLoopVars1(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1));
        _content.getReflect().getParams().setAliasMethod3GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        _content.getReflect().getParams().setAliasAnnotationType0GetString0(get(_util,_cust, AliasParamReflection.ANNOTATION_TYPE_0_GET_STRING_0));
        _content.getReflect().getParams().setAliasAnnotated0GetAnnotations0(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_0));
        _content.getReflect().getParams().setAliasAnnotated0GetAnnotationsParameters0(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0));
        _content.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda0(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0));
        _content.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda1(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1));
        _content.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda2(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2));
        _content.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda3(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3));
    }
    public static String get(StringMap<String> _util, StringMap<String> _cust,String _key) {
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

}
