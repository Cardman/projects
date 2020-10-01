package code.expressionlanguage.utilcompo;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecEnumBlock;
import code.expressionlanguage.exec.blocks.ExecInnerElementBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.sml.DocumentBuilder;
import code.sml.util.ResourcesMessagesUtil;
import code.threads.AbstractLock;
import code.threads.LockFactory;
import code.threads.ThreadUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class CustAliases {
    public static final String RUN = "Run";
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

    private CustAliasParameters custAliasParameters = new CustAliasParameters();

    private FileInfos infos;

    public static boolean isEnumType(GeneType _type) {
        return _type instanceof ExecEnumBlock || _type instanceof ExecInnerElementBlock;
    }

    public static StringStruct getStringOfObjectUtil(ContextEl _cont, Struct _arg) {
        if (_arg instanceof RunnableStruct) {
            String className_ = _arg.getClassName(_cont);
            String id_ = StringExpUtil.getIdFromAllTypes(className_);
            ExecRootBlock clBody_ = _cont.getClasses().getClassBody(id_);
            if (!isEnumType(clBody_)) {
                return new StringStruct(_arg.getClassName(_cont));
            }
        }
        return ApplyCoreMethodUtil.getStringOfObjectBase(_cont, _arg);
    }

    public static AbstractFunctionalInstance newFunctional(String _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className,_rootBlock);
        return new RunnableFunctionalInstance(_className,_functional,fs_,_contextEl);
    }

    public void buildOther(LgNamesContent _content) {
        CustList<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasThread, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasStart, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        method_ = new StandardMethod(aliasCurrentThread, params_, aliasThread, false, MethodModifier.STATIC);
        methods_.add( method_);
        method_ = new StandardMethod(aliasThreadCurrentTime, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.STATIC);
        methods_.add( method_);
        method_ = new StandardMethod(aliasJoin, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        method_ = new StandardMethod(aliasJoinOthers, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC);
        methods_.add( method_);
        method_ = new StandardMethod(aliasIsAlive, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        method_ = new StandardMethod(aliasIsEnded, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        method_ = new StandardMethod(aliasEnd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        method_ = new StandardMethod(aliasGetId, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        method_ = new StandardMethod(aliasGetPriority, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetPriority, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasThread0SetPriority0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasSleep, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread0Sleep0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasYield, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasPrint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread0Print0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasPrint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread1Print0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasPrint, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread2Print0(),custAliasParameters.getAliasThread2Print1()));
        methods_.add( method_);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadExitHook, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread0ThreadExitHook0()));
        methods_.add( method_);
        StandardConstructor ctor_;
        params_ = new StringList(aliasRunnable);
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasThread0Thread0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasThread, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasThreadSet, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadSetAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasThreadSet0ThreadSetAdd0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasThreadSetAll, params_, aliasThreadSet, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadSetContains, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasThreadSet0ThreadSetContains0()));
        methods_.add( method_);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadSetRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasThreadSet0ThreadSetRemove0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasThreadSetSnapshot, params_, StringExpUtil.getPrettyArrayType(aliasThread), false, MethodModifier.FINAL);
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasThreadSet, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasReentrantLock, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasLock, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        method_ = new StandardMethod(aliasUnlock, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        method_ = new StandardMethod(aliasIsHeldByCurrentThread, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasReentrantLock0ReentrantLock0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasReentrantLock, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicBoolean, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasGetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicBoolean0SetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicBoolean0CompareAndSetAtomic0(),custAliasParameters.getAliasAtomicBoolean0CompareAndSetAtomic1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicBoolean0GetAndSetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicBoolean0LazySetAtomic0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasAtomicBoolean0AtomicBoolean0()));
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
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0SetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0CompareAndSetAtomic0(),custAliasParameters.getAliasAtomicInteger0CompareAndSetAtomic1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0GetAndSetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0LazySetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0AddAndGetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0GetAndAddAtomic0()));
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
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasAtomicInteger0AtomicInteger0()));
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
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0SetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong(),_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0CompareAndSetAtomic0(),custAliasParameters.getAliasAtomicLong0CompareAndSetAtomic1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0GetAndSetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0LazySetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0AddAndGetAtomic0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0GetAndAddAtomic0()));
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
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasAtomicLong0AtomicLong0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasAtomicLong, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryBinary, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasEntryName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryValue, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(), StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()));
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasEntryBinary0EntryBinary0(),custAliasParameters.getAliasEntryBinary0EntryBinary1()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasEntryBinary, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryText, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasEntryName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryValue, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(), _content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasEntryText0EntryText0(),custAliasParameters.getAliasEntryText0EntryText1()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasEntryText, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasFile, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasRead, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0Read0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasWrite, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0Write0(),custAliasParameters.getAliasFile0Write1()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileReadBin, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileReadBin0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()));
        method_ = new StandardMethod(aliasFileWriteBin, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileWriteBin0(),custAliasParameters.getAliasFile0FileWriteBin1()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileDelete, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0ThreadSetRemove0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileRename, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileRename0(),custAliasParameters.getAliasFile0FileRename1()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasAppendToFile, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0AppendToFile0(),custAliasParameters.getAliasFile0AppendToFile1()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileAbsolutePath, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileAbsolutePath0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileGetName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileGetName0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileGetParentPath, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileGetParentPath0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileGetLength, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileGetLength0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileLastModif, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileLastModif0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileListDirectories, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileListDirectories0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileListFiles, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileListFiles0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileIsDirectory, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileIsDirectory0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileIsFile, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileIsFile0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileIsAbsolute, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileIsAbsolute0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),aliasEntryBinary);
        method_ = new StandardMethod(aliasFileZipBin, params_, _content.getPrimTypes().getAliasPrimBoolean(), true, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZipBin0(),custAliasParameters.getAliasFile0FileZipBin1()));
        methods_.add( method_);
        params_ = new StringList(aliasEntryBinary);
        method_ = new StandardMethod(aliasFileZipBinArray, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()), true, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZipBinArray0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),aliasEntryText);
        method_ = new StandardMethod(aliasFileZipText, params_, _content.getPrimTypes().getAliasPrimBoolean(), true, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZipText0(),custAliasParameters.getAliasFile0FileZipText1()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileZippedBin, params_, StringExpUtil.getPrettyArrayType(aliasEntryBinary), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZippedBin0()));
        methods_.add( method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()));
        method_ = new StandardMethod(aliasFileZippedBinArray, params_, StringExpUtil.getPrettyArrayType(aliasEntryBinary), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZippedBinArray0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileZippedText, params_, StringExpUtil.getPrettyArrayType(aliasEntryText), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZippedText0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileMakeDirs, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileMakeDirs0()));
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasFile, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasIllegalThreadStateException, fields_, constructors_, methods_, _content.getCoreNames().getAliasError(), MethodModifier.ABSTRACT);
        _content.getStandards().addEntry(aliasIllegalThreadStateException, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasConcurrentError, fields_, constructors_, methods_, _content.getCoreNames().getAliasError(), MethodModifier.ABSTRACT);
        _content.getStandards().addEntry(aliasConcurrentError, stdcl_);
    }
    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content, StringList _predefinedClasses, StringList _predefinedInterfacesInitOrder) {
        StringMap<String> stds_ = new StringMap<String>();
        String content_ = infos.getReader().read("resources_lg/threads/runnable.txt");
        KeyWords keyWords_ = _keyWords;
        StringMap<PrimitiveType> primitiveTypes_ = _content.getPrimTypes().getPrimitiveTypes();
        AliasCore coreNames_ = _content.getCoreNames();
        StringList predefinedClasses_ = _predefinedClasses;
        StringList predefinedInterfacesInitOrder_ = _predefinedInterfacesInitOrder;
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        AliasPredefinedTypes predefTypes_ = _content.getPredefTypes();
        AliasCharSequence charSeq_ = _content.getCharSeq();
        AliasReflection reflect_ = _content.getReflect();
        AliasMath mathRef_ = _content.getMathRef();
        AliasNumber nbAlias_ = _content.getNbAlias();
        AliasStackTraceElement stackElt_ = _content.getStackElt();
        String public_ = keyWords_.getKeyWordPublic();
        String private_ = keyWords_.getKeyWordPrivate();
        String interface_ = keyWords_.getKeyWordInterface();
        String int_ = primTypes_.getAliasPrimInteger();
        String boolean_ = primTypes_.getAliasPrimBoolean();
        String class_ = keyWords_.getKeyWordClass();
        String this_ = keyWords_.getKeyWordThis();
        String new_ = keyWords_.getKeyWordNew();
        String return_ = keyWords_.getKeyWordReturn();
        String iter_ = keyWords_.getKeyWordIter();
        String value_ = keyWords_.getKeyWordValue();
        String abstract_ = keyWords_.getKeyWordAbstract();
        String endLine_ = String.valueOf(';');
        String suffixLocal_ = "";
        String suffixParam_ = "";
        String suffixLoop_ = "";
        String suffixCatch_ = "";
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{Runnable}", aliasRunnable);
        map_.put("{abstract}", abstract_);
        map_.put("{void}", coreNames_.getAliasVoid());
        map_.put("{run}", aliasRun);
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        predefinedClasses_.add(aliasRunnable);
        stds_.put(aliasRunnable, content_);
        predefinedInterfacesInitOrder_.add(aliasRunnable);
        content_ = infos.getReader().read("resources_lg/collections/list.txt");
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
        map_.put("{itCtr}",custAliasParameters.getAliasCustIterator0CustIterator0());
        map_.put("{liCtr1}", custAliasParameters.getAliasList0List0());
        map_.put("{liCtr2}", custAliasParameters.getAliasList1List0());
        map_.put("{liAdd1}", custAliasParameters.getAliasList0AddLi0());
        map_.put("{liAdd21}", custAliasParameters.getAliasList1AddLi0());
        map_.put("{liAdd22}", custAliasParameters.getAliasList1AddLi1());
        map_.put("{liRem}", custAliasParameters.getAliasList0RemoveLi0());
        map_.put("{liInd1}", custAliasParameters.getAliasList0This0());
        map_.put("{liInd2}", custAliasParameters.getAliasList1This0());
        map_.put("{i}", tr("i", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasList0RemoveLi0(),custAliasParameters.getAliasList1AddLi0(),custAliasParameters.getAliasList1AddLi1()));
        map_.put("{p}", tr("p", keyWords_, primitiveTypes_, coreNames_));
        map_.put("{out}", tr("out", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasList0RemoveLi0(),custAliasParameters.getAliasList1AddLi0(),custAliasParameters.getAliasList1AddLi1()));
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
        map_.put("{length}", coreNames_.getAliasArrayLength());
        map_.put("{add}", aliasAddLi);
        map_.put("{iter}", iter_);
        map_.put("{value}", value_);
        map_.put("{remove}", aliasRemoveLi);
        map_.put("{iterator}", predefTypes_.getAliasIterator());
        map_.put("{clear}",aliasListClear);
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        predefinedClasses_.add(aliasCustIterator);
        predefinedClasses_.add(aliasList);
        stds_.put(aliasList, content_);
        predefinedInterfacesInitOrder_.add(aliasCustIterator);
        predefinedInterfacesInitOrder_.add(aliasList);
        content_ = infos.getReader().read("resources_lg/collections/table.txt");
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
        map_.put("{p1}", custAliasParameters.getAliasCustPair0CustPair0());
        map_.put("{p2}", custAliasParameters.getAliasCustPair0CustPair1());
        map_.put("{p3}", custAliasParameters.getAliasCustPair0SetFirst0());
        map_.put("{p4}", custAliasParameters.getAliasCustPair0SetSecond0());
        map_.put("{ti}", custAliasParameters.getAliasCustIterTable0CustIterTable0());
        map_.put("{p5}", custAliasParameters.getAliasTable0AddLi0());
        map_.put("{p6}", custAliasParameters.getAliasTable0AddLi1());
        map_.put("{p7}", custAliasParameters.getAliasTable1AddLi0());
        map_.put("{p8}", custAliasParameters.getAliasTable0GetTa0());
        map_.put("{p9}", custAliasParameters.getAliasTable0GetFirstTa0());
        map_.put("{p10}", custAliasParameters.getAliasTable0GetSecondTa0());
        map_.put("{p11}", custAliasParameters.getAliasTable0SetFirst0());
        map_.put("{p12}", custAliasParameters.getAliasTable0SetFirst1());
        map_.put("{p13}", custAliasParameters.getAliasTable0SetSecond0());
        map_.put("{p14}", custAliasParameters.getAliasTable0SetSecond1());
        map_.put("{p15}", custAliasParameters.getAliasTable0RemoveLi0());
        map_.put("{f}", tr("f", keyWords_, primitiveTypes_, coreNames_));
        map_.put("{s}", tr("s", keyWords_, primitiveTypes_, coreNames_));
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
        predefinedClasses_.add(aliasCustPair);
        predefinedClasses_.add(aliasCustIterTable);
        predefinedClasses_.add(aliasTable);
        stds_.put(aliasTable, content_);
        predefinedInterfacesInitOrder_.add(aliasCustPair);
        predefinedInterfacesInitOrder_.add(aliasCustIterTable);
        predefinedInterfacesInitOrder_.add(aliasTable);

        content_ = infos.getReader().read("resources_lg/tests/run.txt");
        map_.put("{a}",tr("a", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{b}",tr("b", keyWords_, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{c}",tr("c", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{a11}",custAliasParameters.getAliasAssert0AssertAssert0());
        map_.put("{a12}",custAliasParameters.getAliasAssert0AssertAssert1());
        map_.put("{a21}",custAliasParameters.getAliasAssert1AssertAssert0());
        map_.put("{a22}",custAliasParameters.getAliasAssert1AssertAssert1());
        map_.put("{a31}",custAliasParameters.getAliasAssert2AssertAssert0());
        map_.put("{a32}",custAliasParameters.getAliasAssert2AssertAssert1());
        map_.put("{a41}",custAliasParameters.getAliasAssert3AssertAssert0());
        map_.put("{a42}",custAliasParameters.getAliasAssert3AssertAssert1());
        map_.put("{a51}",custAliasParameters.getAliasAssert4AssertAssert0());
        map_.put("{a52}",custAliasParameters.getAliasAssert4AssertAssert1());
        map_.put("{a6}",custAliasParameters.getAliasAssert0AssertAssertTrue0());
        map_.put("{a7}",custAliasParameters.getAliasAssert0AssertAssertNull0());
        map_.put("{a8}",custAliasParameters.getAliasAssert0AssertAssertNotNull0());
        map_.put("{a91}",custAliasParameters.getAliasAssert0AssertAssertSame0());
        map_.put("{a92}",custAliasParameters.getAliasAssert0AssertAssertSame1());
        map_.put("{a101}",custAliasParameters.getAliasAssert5AssertAssert0());
        map_.put("{a102}",custAliasParameters.getAliasAssert5AssertAssert1());
        map_.put("{a103}",custAliasParameters.getAliasAssert5AssertAssert2());
        map_.put("{a111}",custAliasParameters.getAliasAssert6AssertAssert0());
        map_.put("{a112}",custAliasParameters.getAliasAssert6AssertAssert1());
        map_.put("{d}",tr("d", keyWords_, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasAssert0AssertAssert0(),custAliasParameters.getAliasAssert0AssertAssert1(),
                custAliasParameters.getAliasAssert1AssertAssert0(),custAliasParameters.getAliasAssert1AssertAssert1(),
                custAliasParameters.getAliasAssert2AssertAssert0(),custAliasParameters.getAliasAssert2AssertAssert1(),
                custAliasParameters.getAliasAssert3AssertAssert0(),custAliasParameters.getAliasAssert3AssertAssert1(),
                custAliasParameters.getAliasAssert4AssertAssert0(),custAliasParameters.getAliasAssert4AssertAssert1(),
                custAliasParameters.getAliasAssert0AssertAssertTrue0(),
                custAliasParameters.getAliasAssert0AssertAssertNull0(),
                custAliasParameters.getAliasAssert0AssertAssertNotNull0(),
                custAliasParameters.getAliasAssert0AssertAssertSame0(),custAliasParameters.getAliasAssert0AssertAssertSame1(),
                custAliasParameters.getAliasAssert5AssertAssert0(),custAliasParameters.getAliasAssert5AssertAssert1(),custAliasParameters.getAliasAssert5AssertAssert2(),
                custAliasParameters.getAliasAssert6AssertAssert0(),custAliasParameters.getAliasAssert6AssertAssert1()));
        map_.put("{co}",custAliasParameters.getAliasExecute0ExecuteConvert0());
        map_.put("{e}",tr("e", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0(),custAliasParameters.getAliasExecute0ExecuteConvert0()));
        map_.put("{f}",tr("f", keyWords_, primitiveTypes_, coreNames_));
        map_.put("{i}",tr("i", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{l}",tr("l", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{m}",tr("m", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{o}",tr("o", keyWords_, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{r1}",custAliasParameters.getAliasExecute0Run0());
        map_.put("{r2}",custAliasParameters.getAliasExecute0Run1());
        map_.put("{r3}",custAliasParameters.getAliasExecute0Run2());
        map_.put("{p}",tr("p", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{s}",tr("s", keyWords_, primitiveTypes_, coreNames_));
        map_.put("{t}",tr("t", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{tt}",tr("tt", keyWords_, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0ExecuteSetupError0(),custAliasParameters.getAliasExecute0ExecuteSetupError1(),custAliasParameters.getAliasExecute0ExecuteSetupError2(),
                custAliasParameters.getAliasExecute0ExecuteSetupNoException0()));
        map_.put("{ex}",tr("ex", keyWords_, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0ExecuteSetupError0(),custAliasParameters.getAliasExecute0ExecuteSetupError1(),custAliasParameters.getAliasExecute0ExecuteSetupError2()));
        map_.put("{et2}",custAliasParameters.getAliasExecute0ExecuteSetupError0());
        map_.put("{et3}",custAliasParameters.getAliasExecute0ExecuteSetupError1());
        map_.put("{et4}",custAliasParameters.getAliasExecute0ExecuteSetupError2());
        map_.put("{et}",custAliasParameters.getAliasExecute1ExecuteSetupError0());
        map_.put("{er}",custAliasParameters.getAliasExecute1ExecuteSetupError1());
        map_.put("{et1}",custAliasParameters.getAliasExecute0ExecuteSetupNoException0());
        map_.put("{info}",custAliasParameters.getAliasExecute0ExecuteTests0());
        map_.put("{res}",tr("res", keyWords_, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0ExecuteSetupError0(),custAliasParameters.getAliasExecute0ExecuteSetupError1(),custAliasParameters.getAliasExecute0ExecuteSetupError2(),
                custAliasParameters.getAliasExecute1ExecuteSetupError0(),custAliasParameters.getAliasExecute1ExecuteSetupError1(),
                custAliasParameters.getAliasExecute0ExecuteSetupNoException0(),custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{out}",tr("out", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{processEx}",tr("processEx", keyWords_, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{ctor}",tr("ctor", keyWords_, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{classTest}",tr("classTest", keyWords_, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{results}",tr("results", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{exc}",tr("exc", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{nbParams}",tr("nbParams", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{params}",tr("params", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{arr}",tr("arr", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{as}",tr("as", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{bs}",tr("bs", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{tts}",tr("tts", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{locType}",tr("locType", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{loc}",tr("loc", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{paramAnn}",tr("paramAnn", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{aParam}",tr("aParam", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{final}",keyWords_.getKeyWordFinal());
        map_.put("{static}",keyWords_.getKeyWordStatic());
        map_.put("{for}",keyWords_.getKeyWordFor());
        map_.put("{if}",keyWords_.getKeyWordIf());
        map_.put("{else}",keyWords_.getKeyWordElse());
        map_.put("{var}",keyWords_.getKeyWordVar());
        map_.put("{annotation}",keyWords_.getKeyWordAnnotation());
        map_.put("{null}",keyWords_.getKeyWordNull());
        map_.put("{throw}",keyWords_.getKeyWordThrow());
        map_.put("{continue}",keyWords_.getKeyWordContinue());
        map_.put("{break}",keyWords_.getKeyWordBreak());
        map_.put("{instanceof}",keyWords_.getKeyWordInstanceof());
        map_.put("{true}",keyWords_.getKeyWordTrue());
        map_.put("{false}",keyWords_.getKeyWordFalse());
        map_.put("{try}",keyWords_.getKeyWordTry());
        map_.put("{catch}",keyWords_.getKeyWordCatch());
        map_.put("{cast}",keyWords_.getKeyWordCast());
        map_.put("{abstract}", abstract_);
        map_.put("{long}",primTypes_.getAliasPrimLong());
        map_.put("{double}",primTypes_.getAliasPrimDouble());
        map_.put("{toSpecString}",keyWords_.getKeyWordToString());
        map_.put("{Class}", reflect_.getAliasClassType());
        map_.put("{InvokeTarget}",reflect_.getAliasInvokeTarget());
        map_.put("{Stack}",stackElt_.getAliasStackTraceElement());
        map_.put("{Object}",coreNames_.getAliasObject());
        map_.put("{String}",charSeq_.getAliasString());
        map_.put("{StringBuilder}",charSeq_.getAliasStringBuilder());
        map_.put("{Method}",reflect_.getAliasMethod());
        map_.put("{Annotation}", reflect_.getAliasAnnotationType());
        map_.put("{Thread}",getAliasThread());
        map_.put("{ObjectsUtil}",coreNames_.getAliasObjectsUtil());
        map_.put("{Fct}",reflect_.getAliasFct());
        map_.put("{Math}",mathRef_.getAliasMath());
        map_.put("{StringUtil}",coreNames_.getAliasStringUtil());
        map_.put("{getAllClasses}",reflect_.getAliasGetAllClasses());
        map_.put("{getDeclaredMethods}",reflect_.getAliasGetDeclaredMethods());
        map_.put("{getAnnotations}",reflect_.getAliasGetAnnotations());
        map_.put("{length}",coreNames_.getAliasArrayLength());
        map_.put("{getMethodName}",reflect_.getAliasGetName());
        map_.put("{getClassName}",reflect_.getAliasGetName());
        map_.put("{getClass}",reflect_.getAliasGetClass());
        map_.put("{isStatic}",reflect_.getAliasIsStatic());
        map_.put("{getParameterTypes}",reflect_.getAliasGetParameterTypes());
        map_.put("{getDeclaredConstructors}",reflect_.getAliasGetDeclaredConstructors());
        map_.put("{invoke}",reflect_.getAliasInvoke());
        map_.put("{newInstance}",reflect_.getAliasNewInstance());
        map_.put("{getCause}",coreNames_.getAliasGetCause());
        map_.put("{joinOthers}",getAliasJoinOthers());
        map_.put("{isAssignableFrom}",reflect_.getAliasIsAssignableFrom());
        map_.put("{current}",stackElt_.getAliasCurrentStack());
        map_.put("{eq}",coreNames_.getAliasSameRef());
        map_.put("{call}",reflect_.getAliasCall());
        map_.put("{valueOf}",nbAlias_.getAliasValueOfMethod());
        map_.put("{append}",charSeq_.getAliasAppend());
        map_.put("{insert}",charSeq_.getAliasInsert());
        map_.put("{toString}",nbAlias_.getAliasToStringMethod());
        map_.put("{plus}",mathRef_.getAliasPlus());
        map_.put("{minus}",mathRef_.getAliasMinus());
        map_.put("{lt}",mathRef_.getAliasLt());
        map_.put("{gt}",mathRef_.getAliasGt());
        map_.put("{Execute}",aliasExecute);
        map_.put("{Result}",aliasResult);
        map_.put("{ExecutedTest}",aliasExecutedTest);
        map_.put("{Test}",aliasTest);
        map_.put("{After}",aliasAfter);
        map_.put("{Before}",aliasBefore);
        map_.put("{Parameters}",aliasParameters);
        map_.put("{Assert}",aliasAssert);
        map_.put("{Difference}",aliasDifference);
        map_.put("{InfoTest}",aliasInfoTest);
        map_.put("{exception}",aliasTestException);
        map_.put("{nullException}",aliasTestNullException);
        map_.put("{annotations}",aliasExecutedTestAnnotations);
        map_.put("{test}",aliasExecutedTestTest);
        map_.put("{before}",aliasExecutedTestBefore);
        map_.put("{after}",aliasExecutedTestAfter);
        map_.put("{methodParam}",aliasParametersMethod);
        map_.put("{method}",aliasExecutedTestMethod);
        map_.put("{location}",aliasParametersLocation);
        map_.put("{pf}",aliasResultParams);
        map_.put("{convert}",aliasExecuteConvert);
        map_.put("{execute}",aliasExecuteExecute);
        map_.put("{setupError}",aliasExecuteSetupError);
        map_.put("{setupNoException}",aliasExecuteSetupNoException);
        map_.put("{failMessage}",aliasResultFailMessage);
        map_.put("{expected}",aliasDifferenceExpected);
        map_.put("{found}",aliasDifferenceFound);
        map_.put("{stackDiff}",aliasDifferenceStackDiff);
        map_.put("{foundNull}",aliasDifferenceFoundNull);
        map_.put("{foundNotTrue}",aliasDifferenceFoundNotTrue);
        map_.put("{assert}",aliasAssertAssert);
        map_.put("{assertTrue}",aliasAssertAssertTrue);
        map_.put("{assertNull}",aliasAssertAssertNull);
        map_.put("{assertNotNull}",aliasAssertAssertNotNull);
        map_.put("{assertSame}",aliasAssertAssertSame);
        map_.put("{success}",aliasResultSuccess);
        map_.put("{tests}",aliasExecuteTests);
        map_.put("{count}",aliasInfoTestCount);
        map_.put("{done}",aliasInfoTestDone);
        map_.put("{currentClass}",aliasInfoTestCurrentClass);
        map_.put("{currentMethod}",aliasInfoTestCurrentMethod);
        map_.put("{currentParams}",aliasInfoTestCurrentParams);
        content_ = StringList.formatQuote(content_, map_);

        predefinedClasses_.add(aliasInfoTest);
        predefinedClasses_.add(aliasDifference);
        predefinedClasses_.add(aliasAssert);
        predefinedClasses_.add(aliasParameters);
        predefinedClasses_.add(aliasBefore);
        predefinedClasses_.add(aliasAfter);
        predefinedClasses_.add(aliasTest);
        predefinedClasses_.add(aliasExecutedTest);
        predefinedClasses_.add(aliasResult);
        predefinedClasses_.add(aliasExecute);
        stds_.put(aliasExecute, content_);
        predefinedInterfacesInitOrder_.add(aliasInfoTest);
        predefinedInterfacesInitOrder_.add(aliasDifference);
        predefinedInterfacesInitOrder_.add(aliasAssert);
        predefinedInterfacesInitOrder_.add(aliasParameters);
        predefinedInterfacesInitOrder_.add(aliasBefore);
        predefinedInterfacesInitOrder_.add(aliasAfter);
        predefinedInterfacesInitOrder_.add(aliasTest);
        predefinedInterfacesInitOrder_.add(aliasExecutedTest);
        predefinedInterfacesInitOrder_.add(aliasResult);
        predefinedInterfacesInitOrder_.add(aliasExecute);
        content_ = infos.getReader().read("resources_lg/threads/formatting.txt");
        map_.put("{Format}",aliasFormatType);
        map_.put("{int}", int_);
        map_.put("{fo1}", custAliasParameters.getAliasFormatType0Print0());
        map_.put("{fo2}", custAliasParameters.getAliasFormatType1Print0());
        map_.put("{fo3}", custAliasParameters.getAliasFormatType1Print1());
        map_.put("{as}", tr("as", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasFormatType1Print0(),custAliasParameters.getAliasFormatType1Print1()));
        map_.put("{e}", tr("e", keyWords_, primitiveTypes_, coreNames_,custAliasParameters.getAliasFormatType1Print0(),custAliasParameters.getAliasFormatType1Print1()));
        map_.put("{print}",getAliasPrint());
        map_.put("{format}",charSeq_.getAliasFormat());
        content_ = StringList.formatQuote(content_, map_);
        predefinedClasses_.add(aliasFormatType);
        stds_.put(aliasFormatType, content_);
        predefinedInterfacesInitOrder_.add(aliasFormatType);
        return stds_;
    }
    public void messages(AnalysisMessages _mess,String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"messages");
        String content_ = infos.getReader().read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        messages(_mess,util_,_cust);
    }
    public void messages(AnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.setDuplicateMergedMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_MERGED_METHOD));
        _mess.setDuplicateField(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_FIELD));
        _mess.setDuplicateVarType(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_VAR_TYPE));
        _mess.setEmptyPkgRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PKG_REF_TYPE));
        _mess.setPrimitiveKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIMITIVE_KEY_WORD));
        _mess.setDefaultPkgRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.DEFAULT_PKG_REF_TYPE));
        _mess.setRefTypeKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.REF_TYPE_KEY_WORD));
        _mess.setDigitFirstPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_PRIMITIVE));
        _mess.setIllegalFirstChar(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_FIRST_CHAR));
        _mess.setEmptyPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PRIMITIVE));
        _mess.setNotWordCharPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_PRIMITIVE));
        _mess.setNotWordCharRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_REF_TYPE));
        _mess.setEmptyRefTypeIn(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_REF_TYPE_IN));
        _mess.setRefTypePrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.REF_TYPE_PRIMITIVE));
        _mess.setDigitFirstRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_REF_TYPE));
        _mess.setDuplicatePrimtive(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_PRIMTIVE));
        _mess.setDuplicateMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_METHOD));
        _mess.setDefaultPkgNoMatch(LgNamesContent.get(_util, _cust, AnalysisMessages.DEFAULT_PKG_NO_MATCH));
        _mess.setDuplicateRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_REF_TYPE));
        _mess.setDigitFirstMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_METHOD));
        _mess.setNotWordCharField(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_FIELD));
        _mess.setNotWordCharMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_METHOD));
        _mess.setVarTypeKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.VAR_TYPE_KEY_WORD));
        _mess.setVarTypePrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.VAR_TYPE_PRIMITIVE));
        _mess.setDigitFirstVarType(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_VAR_TYPE));
        _mess.setDuplicateNumberWord(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_NUMBER_WORD));
        _mess.setMethodPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.METHOD_PRIMITIVE));
        _mess.setFieldPrimitive(LgNamesContent.get(_util, _cust, AnalysisMessages.FIELD_PRIMITIVE));
        _mess.setDuplicateStringWord(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_STRING_WORD));
        _mess.setDuplicateKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_KEY_WORD));
        _mess.setDigitFirstField(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST_FIELD));
        _mess.setDuplicateStartingNb(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_STARTING_NB));
        _mess.setDuplicateStartingUni(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_STARTING_UNI));
        _mess.setDuplicateStarting(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_STARTING));
        _mess.setNotWordCharVarType(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR_VAR_TYPE));
        _mess.setEmptyPreBin(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PRE_BIN));
        _mess.setEmptyVarType(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_VAR_TYPE));
        _mess.setEmptyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_WORD));
        _mess.setEmptyField(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_FIELD));
        _mess.setEmptyNb(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_NB));
        _mess.setNotWordChar(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_WORD_CHAR));
        _mess.setEmptyPreHex(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PRE_HEX));
        _mess.setEmptyMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_METHOD));
        _mess.setDigitFirst(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIRST));
        _mess.setEmptyBinExp(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_BIN_EXP));
        _mess.setEmptyString(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_STRING));
        _mess.setIllegalChar(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CHAR));
        _mess.setMethodKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.METHOD_KEY_WORD));
        _mess.setEmptyRefType(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_REF_TYPE));
        _mess.setFieldKeyWord(LgNamesContent.get(_util, _cust, AnalysisMessages.FIELD_KEY_WORD));
        _mess.setAbstractMethodBody(LgNamesContent.get(_util, _cust, AnalysisMessages.ABSTRACT_METHOD_BODY));
        _mess.setAbstractMethodConc(LgNamesContent.get(_util, _cust, AnalysisMessages.ABSTRACT_METHOD_CONC));
        _mess.setAbstractMethodImpl(LgNamesContent.get(_util, _cust, AnalysisMessages.ABSTRACT_METHOD_IMPL));
        _mess.setAbstractMethodRef(LgNamesContent.get(_util, _cust, AnalysisMessages.ABSTRACT_METHOD_REF));
        _mess.setInaccessibleType(LgNamesContent.get(_util, _cust, AnalysisMessages.INACCESSIBLE_TYPE));
        _mess.setUnexpectedType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_TYPE));
        _mess.setUnexpectedRetType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_RET_TYPE));
        _mess.setMethodsAccesses(LgNamesContent.get(_util, _cust, AnalysisMessages.METHODS_ACCESSES));
        _mess.setEmptyPackage(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PACKAGE));
        _mess.setEmptyPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PART_CLASS_NAME));
        _mess.setBadPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PART_CLASS_NAME));
        _mess.setKeyWordPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_PART_CLASS_NAME));
        _mess.setPrimPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_PART_CLASS_NAME));
        _mess.setDigitPartClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_PART_CLASS_NAME));
        _mess.setBadPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PART_VAR_CLASS_NAME));
        _mess.setKeyWordPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_PART_VAR_CLASS_NAME));
        _mess.setPrimPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_PART_VAR_CLASS_NAME));
        _mess.setDigitPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_PART_VAR_CLASS_NAME));
        _mess.setDuplicatedPartVarClassName(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_PART_VAR_CLASS_NAME));
        _mess.setCallCtorEnd(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_END));
        _mess.setCallCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR));
        _mess.setCallCtorBeforeBlock(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_BEFORE_BLOCK));
        _mess.setCallCtorFirstLine(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_FIRST_LINE));
        _mess.setCallCtorIntFromSuperInt(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_INT_FROM_SUPER_INT));
        _mess.setCallCtorIntNotFromInt(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_INT_NOT_FROM_INT));
        _mess.setCallCtorIntAfterSuperThis(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_INT_AFTER_SUPER_THIS));
        _mess.setCallCtorIntInherits(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_INT_INHERITS));
        _mess.setCallCtorSuperClassEnumSingleton(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_CTOR_SUPER_CLASS_ENUM_SINGLETON));
        _mess.setAnnotFieldNotUniq(LgNamesContent.get(_util, _cust, AnalysisMessages.ANNOT_FIELD_NOT_UNIQ));
        _mess.setAnnotFieldMust(LgNamesContent.get(_util, _cust, AnalysisMessages.ANNOT_FIELD_MUST));
        _mess.setDupSuppliedAnnotField(LgNamesContent.get(_util, _cust, AnalysisMessages.DUP_SUPPLIED_ANNOT_FIELD));
        _mess.setBadExpression(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_EXPRESSION));
        _mess.setBadFieldName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_FIELD_NAME));
        _mess.setKeyWordFieldName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_FIELD_NAME));
        _mess.setPrimFieldName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_FIELD_NAME));
        _mess.setDigitFieldName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_FIELD_NAME));
        _mess.setNotRetrievedFields(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_RETRIEVED_FIELDS));
        _mess.setBadNbFormat(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_NB_FORMAT));
        _mess.setBadCharFormat(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_CHAR_FORMAT));
        _mess.setBadImplicitCast(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_IMPLICIT_CAST));
        _mess.setNotPrimitiveWrapper(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_PRIMITIVE_WRAPPER));
        _mess.setVoidType(LgNamesContent.get(_util, _cust, AnalysisMessages.VOID_TYPE));
        _mess.setBadIndexInParser(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEX_IN_PARSER));
        _mess.setIllegalCharacter(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CHARACTER));
        _mess.setCallIntInherits(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_INHERITS));
        _mess.setCallIntNoNeed(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_NO_NEED));
        _mess.setCallIntNoNeedType(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_NO_NEED_TYPE));
        _mess.setCallIntNeedType(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_NEED_TYPE));
        _mess.setCallIntOnly(LgNamesContent.get(_util, _cust, AnalysisMessages.CALL_INT_ONLY));
        _mess.setBadInheritsType(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INHERITS_TYPE));
        _mess.setBadInheritsTypeInn(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INHERITS_TYPE_INN));
        _mess.setBadInheritsTypeAsInn(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INHERITS_TYPE_AS_INN));
        _mess.setBadInheritsTypeInt(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INHERITS_TYPE_INT));
        _mess.setFinalType(LgNamesContent.get(_util, _cust, AnalysisMessages.FINAL_TYPE));
        _mess.setDuplicateSuper(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_SUPER));
        _mess.setReservedType(LgNamesContent.get(_util, _cust, AnalysisMessages.RESERVED_TYPE));
        _mess.setSuperClass(LgNamesContent.get(_util, _cust, AnalysisMessages.SUPER_CLASS));
        _mess.setUnknownSuperType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNKNOWN_SUPER_TYPE));
        _mess.setCyclicInherits(LgNamesContent.get(_util, _cust, AnalysisMessages.CYCLIC_INHERITS));
        _mess.setAnnotationParam(LgNamesContent.get(_util, _cust, AnalysisMessages.ANNOTATION_PARAM));
        _mess.setCyclicMapping(LgNamesContent.get(_util, _cust, AnalysisMessages.CYCLIC_MAPPING));
        _mess.setAbsMapping(LgNamesContent.get(_util, _cust, AnalysisMessages.ABS_MAPPING));
        _mess.setFinalMapping(LgNamesContent.get(_util, _cust, AnalysisMessages.FINAL_MAPPING));
        _mess.setMustCallIntCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.MUST_CALL_INT_CTOR));
        _mess.setMustNotCallIntCtorAfterThis(LgNamesContent.get(_util, _cust, AnalysisMessages.MUST_NOT_CALL_INT_CTOR_AFTER_THIS));
        _mess.setMustCallIntCtorNeed(LgNamesContent.get(_util, _cust, AnalysisMessages.MUST_CALL_INT_CTOR_NEED));
        _mess.setMustCallIntCtorNotNeed(LgNamesContent.get(_util, _cust, AnalysisMessages.MUST_CALL_INT_CTOR_NOT_NEED));
        _mess.setBadLabel(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_LABEL));
        _mess.setDuplicatedLabel(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_LABEL));
        _mess.setBadMethodName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_METHOD_NAME));
        _mess.setKeyWordMethodName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_METHOD_NAME));
        _mess.setPrimMethodName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_METHOD_NAME));
        _mess.setDigitMethodName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_METHOD_NAME));
        _mess.setBadOperatorName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_OPERATOR_NAME));
        _mess.setBadAccess(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_ACCESS));
        _mess.setBadReturnType(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_RETURN_TYPE));
        _mess.setBadParams(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PARAMS));
        _mess.setBadMethodModifier(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_METHOD_MODIFIER));
        _mess.setBadMethodVararg(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_METHOD_VARARG));
        _mess.setBadIndexerParams(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_PARAMS));
        _mess.setBadIndexerModifier(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_MODIFIER));
        _mess.setBadIndexerModifiers(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_MODIFIERS));
        _mess.setBadIndexerAccesses(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_ACCESSES));
        _mess.setBadIndexerPairGet(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_PAIR_GET));
        _mess.setBadIndexerPairSet(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_INDEXER_PAIR_SET));
        _mess.setDuplicateCustomMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_CUSTOM_METHOD));
        _mess.setReservedCustomMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.RESERVED_CUSTOM_METHOD));
        _mess.setDuplicateIndexer(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_INDEXER));
        _mess.setDuplicateOperator(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATE_OPERATOR));
        _mess.setFunctionalApplyNbDiff(LgNamesContent.get(_util, _cust, AnalysisMessages.FUNCTIONAL_APPLY_NB_DIFF));
        _mess.setFunctionalApplyOnly(LgNamesContent.get(_util, _cust, AnalysisMessages.FUNCTIONAL_APPLY_ONLY));
        _mess.setOperatorNbDiff(LgNamesContent.get(_util, _cust, AnalysisMessages.OPERATOR_NB_DIFF));
        _mess.setSplitComa(LgNamesContent.get(_util, _cust, AnalysisMessages.SPLIT_COMA));
        _mess.setSplitComaLow(LgNamesContent.get(_util, _cust, AnalysisMessages.SPLIT_COMA_LOW));
        _mess.setSplitDiff(LgNamesContent.get(_util, _cust, AnalysisMessages.SPLIT_DIFF));
        _mess.setBadDotted(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_DOTTED));
        _mess.setBadParamName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PARAM_NAME));
        _mess.setKeyWordParamName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_PARAM_NAME));
        _mess.setPrimParamName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_PARAM_NAME));
        _mess.setDigitParamName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_PARAM_NAME));
        _mess.setReservedParamName(LgNamesContent.get(_util, _cust, AnalysisMessages.RESERVED_PARAM_NAME));
        _mess.setDuplicatedParamName(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_PARAM_NAME));
        _mess.setBadReturnTypeInherit(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_RETURN_TYPE_INHERIT));
        _mess.setBadReturnTypeIndexer(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_RETURN_TYPE_INDEXER));
        _mess.setDuplicatedOverriding(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_OVERRIDING));
        _mess.setTwoFinal(LgNamesContent.get(_util, _cust, AnalysisMessages.TWO_FINAL));
        _mess.setFinalNotSubReturnType(LgNamesContent.get(_util, _cust, AnalysisMessages.FINAL_NOT_SUB_RETURN_TYPE));
        _mess.setTwoReturnTypes(LgNamesContent.get(_util, _cust, AnalysisMessages.TWO_RETURN_TYPES));
        _mess.setReturnTypes(LgNamesContent.get(_util, _cust, AnalysisMessages.RETURN_TYPES));
        _mess.setBadVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_VARIABLE_NAME));
        _mess.setKeyWordVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.KEY_WORD_VARIABLE_NAME));
        _mess.setPrimVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.PRIM_VARIABLE_NAME));
        _mess.setDigitVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.DIGIT_VARIABLE_NAME));
        _mess.setDuplicatedVariableName(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_VARIABLE_NAME));
        _mess.setCyclicCtorCall(LgNamesContent.get(_util, _cust, AnalysisMessages.CYCLIC_CTOR_CALL));
        _mess.setDeadCode(LgNamesContent.get(_util, _cust, AnalysisMessages.DEAD_CODE));
        _mess.setDuplicatedCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_CTOR));
        _mess.setDuplicatedGenericSuperTypes(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_GENERIC_SUPER_TYPES));
        _mess.setDuplicatedInnerType(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_INNER_TYPE));
        _mess.setDuplicatedType(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_TYPE));
        _mess.setDuplicatedTypePrim(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_TYPE_PRIM));
        _mess.setDuplicatedTypeStd(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_TYPE_STD));
        _mess.setDuplicatedTypePkg(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_TYPE_PKG));
        _mess.setEmptyExpressionPart(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_EXPRESSION_PART));
        _mess.setDoWhileNotEmpty(LgNamesContent.get(_util, _cust, AnalysisMessages.DO_WHILE_NOT_EMPTY));
        _mess.setDuplicatedFinal(LgNamesContent.get(_util, _cust, AnalysisMessages.DUPLICATED_FINAL));
        _mess.setIllegalCtorEnum(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_ENUM));
        _mess.setIllegalGenericSuperTypeBound(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_GENERIC_SUPER_TYPE_BOUND));
        _mess.setIllegalCtorAnnotation(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_ANNOTATION));
        _mess.setIllegalCtorAbstract(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_ABSTRACT));
        _mess.setIllegalCtorBound(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_BOUND));
        _mess.setIllegalCtorArray(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_ARRAY));
        _mess.setIllegalCtorUnknown(LgNamesContent.get(_util, _cust, AnalysisMessages.ILLEGAL_CTOR_UNKNOWN));
        _mess.setMissingAbrupt(LgNamesContent.get(_util, _cust, AnalysisMessages.MISSING_ABRUPT));
        _mess.setNotInitClass(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_INIT_CLASS));
        _mess.setNullValue(LgNamesContent.get(_util, _cust, AnalysisMessages.NULL_VALUE));
        _mess.setBadParameTypeForId(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PARAME_TYPE_FOR_ID));
        _mess.setNotResolvedOwner(LgNamesContent.get(_util, _cust, AnalysisMessages.NOT_RESOLVED_OWNER));
        _mess.setUndefinedAccessibleField(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_ACCESSIBLE_FIELD));
        _mess.setStaticAccess(LgNamesContent.get(_util, _cust, AnalysisMessages.STATIC_ACCESS));
        _mess.setStaticAccessPrev(LgNamesContent.get(_util, _cust, AnalysisMessages.STATIC_ACCESS_PREV));
        _mess.setUnassignedFinalField(LgNamesContent.get(_util, _cust, AnalysisMessages.UNASSIGNED_FINAL_FIELD));
        _mess.setUnassignedInferingType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNASSIGNED_INFERING_TYPE));
        _mess.setUndefinedCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_CTOR));
        _mess.setUndefinedMethod(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_METHOD));
        _mess.setArrayCloneOnly(LgNamesContent.get(_util, _cust, AnalysisMessages.ARRAY_CLONE_ONLY));
        _mess.setUndefinedSuperCtor(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_SUPER_CTOR));
        _mess.setUndefinedSuperCtorCall(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_SUPER_CTOR_CALL));
        _mess.setUndefinedVariable(LgNamesContent.get(_util, _cust, AnalysisMessages.UNDEFINED_VARIABLE));
        _mess.setUnexpectedAffect(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_AFFECT));
        _mess.setFinalField(LgNamesContent.get(_util, _cust, AnalysisMessages.FINAL_FIELD));
        _mess.setBadOperatorRef(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_OPERATOR_REF));
        _mess.setUnexpectedCatchElseFinally(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CATCH_ELSE_FINALLY));
        _mess.setUnexpectedAbrupt(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_ABRUPT));
        _mess.setUnexpectedAbruptLab(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_ABRUPT_LAB));
        _mess.setUnexpectedCaseDef(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CASE_DEF));
        _mess.setUnexpectedCaseVar(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CASE_VAR));
        _mess.setUnexpectedCaseValue(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CASE_VALUE));
        _mess.setUnexpectedCaseDup(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_CASE_DUP));
        _mess.setUnexpectedDefDup(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_DEF_DUP));
        _mess.setUnexpectedDoTry(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_DO_TRY));
        _mess.setUnexpectedSwitch(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_SWITCH));
        _mess.setUnexpectedMemberInst(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_MEMBER_INST));
        _mess.setUnexpectedBlockExp(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_BLOCK_EXP));
        _mess.setUnexpectedOperandTypes(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_OPERAND_TYPES));
        _mess.setUnknownType(LgNamesContent.get(_util, _cust, AnalysisMessages.UNKNOWN_TYPE));
        _mess.setEmptyType(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_TYPE));
        _mess.setBadParamerizedType(LgNamesContent.get(_util, _cust, AnalysisMessages.BAD_PARAMERIZED_TYPE));
        _mess.setUnexpectedTypeBound(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_TYPE_BOUND));
        _mess.setUnexpectedVararg(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_VARARG));
        _mess.setUnexpectedLeaf(LgNamesContent.get(_util, _cust, AnalysisMessages.UNEXPECTED_LEAF));
        _mess.setEmptyPart(LgNamesContent.get(_util, _cust, AnalysisMessages.EMPTY_PART));
    }
    public void keyWord(KeyWords _kw,String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"keywords");
        String content_ = infos.getReader().read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        keyWord(_kw,util_,_cust);
    }
    public void keyWord(KeyWords _kw,StringMap<String> _util,StringMap<String> _cust) {
        _kw.setKeyWordContinue(LgNamesContent.get(_util, _cust, KeyWords.CONTINUE));
        _kw.setKeyWordInstanceof(LgNamesContent.get(_util, _cust, KeyWords.INSTANCEOF));
        _kw.setKeyWordInterface(LgNamesContent.get(_util, _cust, KeyWords.INTERFACE));
        _kw.setKeyWordAbstract(LgNamesContent.get(_util, _cust, KeyWords.ABSTRACT));
        _kw.setKeyWordElseif(LgNamesContent.get(_util, _cust, KeyWords.ELSEIF));
        _kw.setKeyWordCast(LgNamesContent.get(_util, _cust, KeyWords.CAST));
        _kw.setKeyWordExplicit(LgNamesContent.get(_util, _cust, KeyWords.EXPLICIT));
        _kw.setKeyWordFor(LgNamesContent.get(_util, _cust, KeyWords.FOR));
        _kw.setKeyWordVar(LgNamesContent.get(_util, _cust, KeyWords.VAR));
        _kw.setKeyWordStatic(LgNamesContent.get(_util, _cust, KeyWords.STATIC));
        _kw.setKeyWordStaticCall(LgNamesContent.get(_util, _cust, KeyWords.STATIC_CALL));
        _kw.setKeyWordNull(LgNamesContent.get(_util, _cust, KeyWords.NULL));
        _kw.setKeyWordClass(LgNamesContent.get(_util, _cust, KeyWords.CLASS));
        _kw.setKeyWordFalse(LgNamesContent.get(_util, _cust, KeyWords.FALSE));
        _kw.setKeyWordFinal(LgNamesContent.get(_util, _cust, KeyWords.FINAL));
        _kw.setKeyWordBreak(LgNamesContent.get(_util, _cust, KeyWords.BREAK));
        _kw.setKeyWordIf(LgNamesContent.get(_util, _cust, KeyWords.IF));
        _kw.setKeyWordNew(LgNamesContent.get(_util, _cust, KeyWords.NEW));
        _kw.setKeyWordWhile(LgNamesContent.get(_util, _cust, KeyWords.WHILE));
        _kw.setKeyWordReturn(LgNamesContent.get(_util, _cust, KeyWords.RETURN));
        _kw.setKeyWordTrue(LgNamesContent.get(_util, _cust, KeyWords.TRUE));
        _kw.setKeyWordPublic(LgNamesContent.get(_util, _cust, KeyWords.PUBLIC));
        _kw.setKeyWordPrivate(LgNamesContent.get(_util, _cust, KeyWords.PRIVATE));
        _kw.setKeyWordAnnotation(LgNamesContent.get(_util, _cust, KeyWords.ANNOTATION));
        _kw.setKeyWordToString(LgNamesContent.get(_util, _cust, KeyWords.TO_STRING));
        _kw.setKeyWordNbSufBytePrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_BYTE_PRIM));
        _kw.setKeyWordNbSufByte(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_BYTE));
        _kw.setKeyWordNbSufShortPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_SHORT_PRIM));
        _kw.setKeyWordNbSufShort(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_SHORT));
        _kw.setKeyWordNbSufCharacterPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_CHARACTER_PRIM));
        _kw.setKeyWordNbSufCharacter(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_CHARACTER));
        _kw.setKeyWordNbSufIntegerPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_INTEGER_PRIM));
        _kw.setKeyWordNbSufInteger(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_INTEGER));
        _kw.setKeyWordNbSufLongPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_LONG_PRIM));
        _kw.setKeyWordNbSufLong(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_LONG));
        _kw.setKeyWordNbSufFloatPrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_FLOAT_PRIM));
        _kw.setKeyWordNbSufFloat(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_FLOAT));
        _kw.setKeyWordNbSufDoublePrim(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_DOUBLE_PRIM));
        _kw.setKeyWordNbSufDouble(LgNamesContent.get(_util, _cust, KeyWords.NB_SUF_DOUBLE));
        _kw.setKeyWordIter(LgNamesContent.get(_util, _cust, KeyWords.ITER));
        _kw.setKeyWordValue(LgNamesContent.get(_util, _cust, KeyWords.VALUE));
        _kw.setKeyWordElse(LgNamesContent.get(_util, _cust, KeyWords.ELSE));
        _kw.setKeyWordCatch(LgNamesContent.get(_util, _cust, KeyWords.CATCH));
        _kw.setKeyWordThrow(LgNamesContent.get(_util, _cust, KeyWords.THROW));
        _kw.setKeyWordTry(LgNamesContent.get(_util, _cust, KeyWords.TRY));
        _kw.setKeyWordThis(LgNamesContent.get(_util, _cust, KeyWords.THIS));
        _kw.setKeyWordSuper(LgNamesContent.get(_util, _cust, KeyWords.SUPER));
        _kw.setKeyWordCase(LgNamesContent.get(_util, _cust, KeyWords.CASE));
        _kw.setKeyWordDo(LgNamesContent.get(_util, _cust, KeyWords.DO));
        _kw.setKeyWordEnum(LgNamesContent.get(_util, _cust, KeyWords.ENUM));
        _kw.setKeyWordSwitch(LgNamesContent.get(_util, _cust, KeyWords.SWITCH));
        _kw.setKeyWordIntern(LgNamesContent.get(_util, _cust, KeyWords.INTERN));
        _kw.setKeyWordNormal(LgNamesContent.get(_util, _cust, KeyWords.NORMAL));
        _kw.setKeyWordEscTab(LgNamesContent.get(_util, _cust, KeyWords.ESC_TAB));
        _kw.setKeyWordNbHex(LgNamesContent.get(_util, _cust, KeyWords.NB_HEX));
        _kw.setKeyWordNbHexEnd(LgNamesContent.get(_util, _cust, KeyWords.NB_HEX_END));
        _kw.setKeyWordNbBin(LgNamesContent.get(_util, _cust, KeyWords.NB_BIN));
        _kw.setKeyWordThat(LgNamesContent.get(_util, _cust, KeyWords.THAT));
        _kw.setKeyWordBool(LgNamesContent.get(_util, _cust, KeyWords.BOOL));
        _kw.setKeyWordValues(LgNamesContent.get(_util, _cust, KeyWords.VALUES));
        _kw.setKeyWordLambda(LgNamesContent.get(_util, _cust, KeyWords.LAMBDA));
        _kw.setKeyWordVararg(LgNamesContent.get(_util, _cust, KeyWords.VARARG));
        _kw.setKeyWordId(LgNamesContent.get(_util, _cust, KeyWords.ID));
        _kw.setKeyWordForeach(LgNamesContent.get(_util, _cust, KeyWords.FOREACH));
        _kw.setKeyWordNbExpBin(LgNamesContent.get(_util, _cust, KeyWords.NB_EXP_BIN));
        _kw.setKeyWordClasschoice(LgNamesContent.get(_util, _cust, KeyWords.CLASSCHOICE));
        _kw.setKeyWordFirstopt(LgNamesContent.get(_util, _cust, KeyWords.FIRSTOPT));
        _kw.setKeyWordPackage(LgNamesContent.get(_util, _cust, KeyWords.PACKAGE));
        _kw.setKeyWordFinally(LgNamesContent.get(_util, _cust, KeyWords.FINALLY));
        _kw.setKeyWordEscUnicode(LgNamesContent.get(_util, _cust, KeyWords.ESC_UNICODE));
        _kw.setKeyWordThisaccess(LgNamesContent.get(_util, _cust, KeyWords.THISACCESS));
        _kw.setKeyWordValueOf(LgNamesContent.get(_util, _cust, KeyWords.VALUE_OF));
        _kw.setKeyWordDefaultValue(LgNamesContent.get(_util, _cust, KeyWords.DEFAULT_VALUE));
        _kw.setKeyWordEscLine(LgNamesContent.get(_util, _cust, KeyWords.ESC_LINE));
        _kw.setKeyWordOperator(LgNamesContent.get(_util, _cust, KeyWords.OPERATOR));
        _kw.setKeyWordInterfaces(LgNamesContent.get(_util, _cust, KeyWords.INTERFACES));
        _kw.setKeyWordSuperaccess(LgNamesContent.get(_util, _cust, KeyWords.SUPERACCESS));
        _kw.setKeyWordEscBound(LgNamesContent.get(_util, _cust, KeyWords.ESC_BOUND));
        _kw.setKeyWordEscForm(LgNamesContent.get(_util, _cust, KeyWords.ESC_FORM));
        _kw.setKeyWordEscFeed(LgNamesContent.get(_util, _cust, KeyWords.ESC_FEED));
        _kw.setKeyWordNbExpDec(LgNamesContent.get(_util, _cust, KeyWords.NB_EXP_DEC));
        _kw.setKeyWordProtected(LgNamesContent.get(_util, _cust, KeyWords.PROTECTED));
        _kw.setKeyWordDefault(LgNamesContent.get(_util, _cust, KeyWords.DEFAULT));
        _kw.setKeyWordParent(LgNamesContent.get(_util, _cust, KeyWords.PARENT));
    }
    public void otherAlias(LgNamesContent _content,String _lang, StringMap<String>_cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"types");
        String content_ = infos.getReader().read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherAlias(_content,util_,_cust);
    }
    public void allAlias(LgNamesContent _content,StringMap<String> _util, StringMap<String> _cust) {
        otherAlias(_content,_util,_cust);
    }
    private void otherAlias(LgNamesContent _content,StringMap<String> _util, StringMap<String> _cust) {
        build(_content,_util,_cust);
        build(_util,_cust);
    }
    public void build(StringMap<String> _util, StringMap<String> _cust) {
        setAliasIllegalThreadStateException(LgNamesContent.get(_util, _cust, ILLEGAL_THREAD_STATE_EXCEPTION));
        setAliasFileGetLength(LgNamesContent.get(_util, _cust, FILE_GET_LENGTH));
        setAliasAtomicInteger(LgNamesContent.get(_util, _cust, ATOMIC_INTEGER));
        setAliasCurrentThread(LgNamesContent.get(_util, _cust, CURRENT_THREAD));
        setAliasFormatType(LgNamesContent.get(_util, _cust, FORMAT_TYPE));
        setAliasAtomicBoolean(LgNamesContent.get(_util, _cust, ATOMIC_BOOLEAN));
        setAliasSetAtomic(LgNamesContent.get(_util, _cust, SET_ATOMIC));
        setAliasFileIsDirectory(LgNamesContent.get(_util, _cust, FILE_IS_DIRECTORY));
        setAliasFileGetParentPath(LgNamesContent.get(_util, _cust, FILE_GET_PARENT_PATH));
        setAliasAtomicLong(LgNamesContent.get(_util, _cust, ATOMIC_LONG));
        setAliasFileAbsolutePath(LgNamesContent.get(_util, _cust, FILE_ABSOLUTE_PATH));
        setAliasFileLastModif(LgNamesContent.get(_util, _cust, FILE_LAST_MODIF));
        setAliasFileGetName(LgNamesContent.get(_util, _cust, FILE_GET_NAME));
        setAliasGetAtomic(LgNamesContent.get(_util, _cust, GET_ATOMIC));
        setAliasLazySetAtomic(LgNamesContent.get(_util, _cust, LAZY_SET_ATOMIC));
        setAliasCompareAndSetAtomic(LgNamesContent.get(_util, _cust, COMPARE_AND_SET_ATOMIC));
        setAliasGetAndAddAtomic(LgNamesContent.get(_util, _cust, GET_AND_ADD_ATOMIC));
        setAliasAddAndGetAtomic(LgNamesContent.get(_util, _cust, ADD_AND_GET_ATOMIC));
        setAliasGetAndIncrementAtomic(LgNamesContent.get(_util, _cust, GET_AND_INCREMENT_ATOMIC));
        setAliasIncrementAndGetAtomic(LgNamesContent.get(_util, _cust, INCREMENT_AND_GET_ATOMIC));
        setAliasGetAndDecrementAtomic(LgNamesContent.get(_util, _cust, GET_AND_DECREMENT_ATOMIC));
        setAliasDecrementAndGetAtomic(LgNamesContent.get(_util, _cust, DECREMENT_AND_GET_ATOMIC));
        setAliasGetAndSetAtomic(LgNamesContent.get(_util, _cust, GET_AND_SET_ATOMIC));
        setAliasReentrantLock(LgNamesContent.get(_util, _cust, REENTRANT_LOCK));
        setAliasJoinOthers(LgNamesContent.get(_util, _cust, JOIN_OTHERS));
        setAliasFileIsFile(LgNamesContent.get(_util, _cust, FILE_IS_FILE));
        setAliasThreadExitHook(LgNamesContent.get(_util, _cust, THREAD_EXIT_HOOK));
        setAliasAppendToFile(LgNamesContent.get(_util, _cust, APPEND_TO_FILE));
        setAliasThreadCurrentTime(LgNamesContent.get(_util, _cust, THREAD_CURRENT_TIME));
        setAliasSetPriority(LgNamesContent.get(_util, _cust, SET_PRIORITY));
        setAliasFileListFiles(LgNamesContent.get(_util, _cust, FILE_LIST_FILES));
        setAliasGetPriority(LgNamesContent.get(_util, _cust, GET_PRIORITY));
        setAliasIsHeldByCurrentThread(LgNamesContent.get(_util, _cust, IS_HELD_BY_CURRENT_THREAD));
        setAliasFileListDirectories(LgNamesContent.get(_util, _cust, FILE_LIST_DIRECTORIES));
        setAliasLengthItrTa(LgNamesContent.get(_util, _cust, LENGTH_ITR_TA));
        setAliasExecutedTestAnnotations(LgNamesContent.get(_util, _cust, EXECUTED_TEST_ANNOTATIONS));
        setAliasPairVarFirst(LgNamesContent.get(_util, _cust, PAIR_VAR_FIRST));
        setAliasIndexItrLi(LgNamesContent.get(_util, _cust, INDEX_ITR_LI));
        setAliasListIterTable(LgNamesContent.get(_util, _cust, LIST_ITER_TABLE));
        setAliasDifference(LgNamesContent.get(_util, _cust, DIFFERENCE));
        setAliasTableVarSecond(LgNamesContent.get(_util, _cust, TABLE_VAR_SECOND));
        setAliasIterTaVarFirst(LgNamesContent.get(_util, _cust, ITER_TA_VAR_FIRST));
        setAliasExecutedTestBefore(LgNamesContent.get(_util, _cust, EXECUTED_TEST_BEFORE));
        setAliasCustIteratorVar(LgNamesContent.get(_util, _cust, CUST_ITERATOR_VAR));
        setAliasGetSecondTa(LgNamesContent.get(_util, _cust, GET_SECOND_TA));
        setAliasExecutedTestTest(LgNamesContent.get(_util, _cust, EXECUTED_TEST_TEST));
        setAliasExecutedTestMethod(LgNamesContent.get(_util, _cust, EXECUTED_TEST_METHOD));
        setAliasExecutedTestAfter(LgNamesContent.get(_util, _cust, EXECUTED_TEST_AFTER));
        setAliasCustIterator(LgNamesContent.get(_util, _cust, CUST_ITERATOR));
        setAliasListClear(LgNamesContent.get(_util, _cust, LIST_CLEAR));
        setAliasGetFirstTa(LgNamesContent.get(_util, _cust, GET_FIRST_TA));
        setAliasSetSecondTa(LgNamesContent.get(_util, _cust, SET_SECOND_TA));
        setAliasFileMakeDirs(LgNamesContent.get(_util, _cust, FILE_MAKE_DIRS));
        setAliasFileZippedBin(LgNamesContent.get(_util, _cust, FILE_ZIPPED_BIN));
        setAliasFileZippedBinArray(LgNamesContent.get(_util, _cust, FILE_ZIPPED_BIN_ARRAY));
        setAliasFileZippedText(LgNamesContent.get(_util, _cust, FILE_ZIPPED_TEXT));
        setAliasFileZipBin(LgNamesContent.get(_util, _cust, FILE_ZIP_BIN));
        setAliasFileZipBinArray(LgNamesContent.get(_util, _cust, FILE_ZIP_BIN_ARRAY));
        setAliasFileZipText(LgNamesContent.get(_util, _cust, FILE_ZIP_TEXT));
        setAliasEntryBinary(LgNamesContent.get(_util, _cust, ENTRY_BINARY));
        setAliasEntryText(LgNamesContent.get(_util, _cust, ENTRY_TEXT));
        setAliasEntryName(LgNamesContent.get(_util, _cust, ENTRY_NAME));
        setAliasEntryValue(LgNamesContent.get(_util, _cust, ENTRY_VALUE));
        setAliasFileIsAbsolute(LgNamesContent.get(_util, _cust, FILE_IS_ABSOLUTE));
        setAliasFileReadBin(LgNamesContent.get(_util, _cust, FILE_READ_BIN));
        setAliasFileWriteBin(LgNamesContent.get(_util, _cust, FILE_WRITE_BIN));
        setAliasFileDelete(LgNamesContent.get(_util, _cust, FILE_DELETE));
        setAliasFileRename(LgNamesContent.get(_util, _cust, FILE_RENAME));
        setAliasCustIterTable(LgNamesContent.get(_util, _cust, CUST_ITER_TABLE));
        setAliasTableVarFirst(LgNamesContent.get(_util, _cust, TABLE_VAR_FIRST));
        setAliasSetSecond(LgNamesContent.get(_util, _cust, SET_SECOND));
        setAliasIndexItrTa(LgNamesContent.get(_util, _cust, INDEX_ITR_TA));
        setAliasIterTaVarSecond(LgNamesContent.get(_util, _cust, ITER_TA_VAR_SECOND));
        setAliasLengthItrLi(LgNamesContent.get(_util, _cust, LENGTH_ITR_LI));
        setAliasSetFirstTa(LgNamesContent.get(_util, _cust, SET_FIRST_TA));
        setAliasExecutedTest(LgNamesContent.get(_util, _cust, EXECUTED_TEST));
        setAliasParameters(LgNamesContent.get(_util, _cust, PARAMETERS));
        setAliasTestException(LgNamesContent.get(_util, _cust, TEST_EXCEPTION));
        setAliasTestNullException(LgNamesContent.get(_util, _cust, TEST_NULL_EXCEPTION));
        setAliasPairVarSecond(LgNamesContent.get(_util, _cust, PAIR_VAR_SECOND));
        setAliasExecuteExecute(LgNamesContent.get(_util, _cust, EXECUTE_EXECUTE));
        setAliasExecuteSetupNoException(LgNamesContent.get(_util, _cust, EXECUTE_SETUP_NO_EXCEPTION));
        setAliasAssertAssert(LgNamesContent.get(_util, _cust, ASSERT_ASSERT));
        setAliasAssertAssertNull(LgNamesContent.get(_util, _cust, ASSERT_ASSERT_NULL));
        setAliasAssertAssertNotNull(LgNamesContent.get(_util, _cust, ASSERT_ASSERT_NOT_NULL));
        setAliasDifferenceFoundNull(LgNamesContent.get(_util, _cust, DIFFERENCE_FOUND_NULL));
        setAliasResultSuccess(LgNamesContent.get(_util, _cust, RESULT_SUCCESS));
        setAliasInfoTestCurrentClass(LgNamesContent.get(_util, _cust, INFO_TEST_CURRENT_CLASS));
        setAliasExecuteConvert(LgNamesContent.get(_util, _cust, EXECUTE_CONVERT));
        setAliasConcurrentError(LgNamesContent.get(_util, _cust, CONCURRENT_ERROR));
        setAliasResultFailMessage(LgNamesContent.get(_util, _cust, RESULT_FAIL_MESSAGE));
        setAliasAssertAssertTrue(LgNamesContent.get(_util, _cust, ASSERT_ASSERT_TRUE));
        setAliasInfoTestCurrentMethod(LgNamesContent.get(_util, _cust, INFO_TEST_CURRENT_METHOD));
        setAliasResultParams(LgNamesContent.get(_util, _cust, RESULT_PARAMS));
        setAliasParametersMethod(LgNamesContent.get(_util, _cust, PARAMETERS_METHOD));
        setAliasExecuteSetupError(LgNamesContent.get(_util, _cust, EXECUTE_SETUP_ERROR));
        setAliasAssertAssertSame(LgNamesContent.get(_util, _cust, ASSERT_ASSERT_SAME));
        setAliasDifferenceFound(LgNamesContent.get(_util, _cust, DIFFERENCE_FOUND));
        setAliasDifferenceFoundNotTrue(LgNamesContent.get(_util, _cust, DIFFERENCE_FOUND_NOT_TRUE));
        setAliasParametersLocation(LgNamesContent.get(_util, _cust, PARAMETERS_LOCATION));
        setAliasInfoTestCount(LgNamesContent.get(_util, _cust, INFO_TEST_COUNT));
        setAliasInfoTestDone(LgNamesContent.get(_util, _cust, INFO_TEST_DONE));
        setAliasDifferenceStackDiff(LgNamesContent.get(_util, _cust, DIFFERENCE_STACK_DIFF));
        setAliasExecuteTests(LgNamesContent.get(_util, _cust, EXECUTE_TESTS));
        setAliasDifferenceExpected(LgNamesContent.get(_util, _cust, DIFFERENCE_EXPECTED));
        setAliasInfoTestCurrentParams(LgNamesContent.get(_util, _cust, INFO_TEST_CURRENT_PARAMS));
        setAliasRunnable(LgNamesContent.get(_util, _cust, RUNNABLE));
        setAliasThread(LgNamesContent.get(_util, _cust, THREAD));
        setAliasThreadSet(LgNamesContent.get(_util, _cust, THREAD_SET));
        setAliasThreadSetAll(LgNamesContent.get(_util, _cust, THREAD_SET_ALL));
        setAliasThreadSetAdd(LgNamesContent.get(_util, _cust, THREAD_SET_ADD));
        setAliasThreadSetContains(LgNamesContent.get(_util, _cust, THREAD_SET_CONTAINS));
        setAliasThreadSetRemove(LgNamesContent.get(_util, _cust, THREAD_SET_REMOVE));
        setAliasThreadSetSnapshot(LgNamesContent.get(_util, _cust, THREAD_SET_SNAPSHOT));
        setAliasStart(LgNamesContent.get(_util, _cust, START));
        setAliasJoin(LgNamesContent.get(_util, _cust, JOIN));
        setAliasRun(LgNamesContent.get(_util, _cust, RUN));
        setAliasLengthLi(LgNamesContent.get(_util, _cust, LENGTH_LI));
        setAliasCustPair(LgNamesContent.get(_util, _cust, CUST_PAIR));
        setAliasListTa(LgNamesContent.get(_util, _cust, LIST_TA));
        setAliasGetId(LgNamesContent.get(_util, _cust, GET_ID));
        setAliasIsAlive(LgNamesContent.get(_util, _cust, IS_ALIVE));
        setAliasIsEnded(LgNamesContent.get(_util, _cust, IS_ENDED));
        setAliasEnd(LgNamesContent.get(_util, _cust, END));
        setAliasPrint(LgNamesContent.get(_util, _cust, PRINT));
        setAliasListItr(LgNamesContent.get(_util, _cust, LIST_ITR));
        setAliasRemoveLi(LgNamesContent.get(_util, _cust, REMOVE_LI));
        setAliasArrayLi(LgNamesContent.get(_util, _cust, ARRAY_LI));
        setAliasFirst(LgNamesContent.get(_util, _cust, FIRST));
        setAliasSleep(LgNamesContent.get(_util, _cust, SLEEP));
        setAliasFile(LgNamesContent.get(_util, _cust, FILE));
        setAliasRead(LgNamesContent.get(_util, _cust, READ));
        setAliasList(LgNamesContent.get(_util, _cust, LIST));
        setAliasUnlock(LgNamesContent.get(_util, _cust, UNLOCK));
        setAliasSizeLi(LgNamesContent.get(_util, _cust, SIZE_LI));
        setAliasListVar(LgNamesContent.get(_util, _cust, LIST_VAR));
        setAliasSecond(LgNamesContent.get(_util, _cust, SECOND));
        setAliasAddLi(LgNamesContent.get(_util, _cust, ADD_LI));
        setAliasYield(LgNamesContent.get(_util, _cust, YIELD));
        setAliasSetFirst(LgNamesContent.get(_util, _cust, SET_FIRST));
        setAliasLock(LgNamesContent.get(_util, _cust, LOCK));
        setAliasWrite(LgNamesContent.get(_util, _cust, WRITE));
        setAliasTable(LgNamesContent.get(_util, _cust, TABLE));
        setAliasResult(LgNamesContent.get(_util, _cust, RESULT));
        setAliasAddTa(LgNamesContent.get(_util, _cust, ADD_TA));
        setAliasInfoTest(LgNamesContent.get(_util, _cust, INFO_TEST));
        setAliasRemoveTa(LgNamesContent.get(_util, _cust, REMOVE_TA));
        setAliasBefore(LgNamesContent.get(_util, _cust, BEFORE));
        setAliasGetTa(LgNamesContent.get(_util, _cust, GET_TA));
        setAliasTest(LgNamesContent.get(_util, _cust, TEST));
        setAliasExecute(LgNamesContent.get(_util, _cust, EXECUTE));
        setAliasAfter(LgNamesContent.get(_util, _cust, AFTER));
        setAliasSizeTa(LgNamesContent.get(_util, _cust, SIZE_TA));
        setAliasAssert(LgNamesContent.get(_util, _cust, ASSERT));
        custAliasParameters.build(_util,_cust);
    }

    public void build(LgNamesContent _content,StringMap<String> _util, StringMap<String> _cust) {
        _content.build(_util,_cust);
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
                new KeyValueMemberName(LgNames.SEED_GET,_content.getPredefTypes().getAliasSeedGet()),
                new KeyValueMemberName(RUN,getAliasRun())
        ));
        return list_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = new StringMap<CustList<KeyValueMemberName>>();
        f_.addEntry(getAliasDifference(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(DIFFERENCE_EXPECTED,getAliasDifferenceExpected()),
                new KeyValueMemberName(DIFFERENCE_FOUND,getAliasDifferenceFound()),
                new KeyValueMemberName(DIFFERENCE_FOUND_NOT_TRUE,getAliasDifferenceFoundNotTrue()),
                new KeyValueMemberName(DIFFERENCE_FOUND_NULL,getAliasDifferenceFoundNull()),
                new KeyValueMemberName(DIFFERENCE_STACK_DIFF,getAliasDifferenceStackDiff())
        ));
        f_.addEntry(getAliasInfoTest(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(INFO_TEST_COUNT,getAliasInfoTestCount()),
                new KeyValueMemberName(INFO_TEST_CURRENT_METHOD,getAliasInfoTestCurrentMethod()),
                new KeyValueMemberName(INFO_TEST_DONE,getAliasInfoTestDone()),
                new KeyValueMemberName(INFO_TEST_CURRENT_PARAMS,getAliasInfoTestCurrentParams())
        ));
        f_.addEntry(getAliasResult(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(RESULT_FAIL_MESSAGE,getAliasResultFailMessage()),
                new KeyValueMemberName(RESULT_PARAMS,getAliasResultParams()),
                new KeyValueMemberName(RESULT_SUCCESS,getAliasResultSuccess())
        ));
        f_.addEntry(getAliasExecutedTest(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(EXECUTED_TEST_AFTER,getAliasExecutedTestAfter()),
                new KeyValueMemberName(EXECUTED_TEST_BEFORE,getAliasExecutedTestBefore()),
                new KeyValueMemberName(EXECUTED_TEST_ANNOTATIONS,getAliasExecutedTestAnnotations()),
                new KeyValueMemberName(EXECUTED_TEST_METHOD,getAliasExecutedTestMethod()),
                new KeyValueMemberName(EXECUTED_TEST_TEST,getAliasExecutedTestTest())
        ));
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

    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.addAllElts(custAliasParameters.allTableTypeMethodParamNames());
        return m_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(LgNamesContent _content) {
        StringMap<CustList<KeyValueMemberName>> m_ = new StringMap<CustList<KeyValueMemberName>>();
        m_.addEntry(getAliasThread(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(START,getAliasStart()),
                new KeyValueMemberName(THREAD_CURRENT_TIME,getAliasThreadCurrentTime()),
                new KeyValueMemberName(IS_ALIVE,getAliasIsAlive()),
                new KeyValueMemberName(CURRENT_THREAD,getAliasCurrentThread()),
                new KeyValueMemberName(JOIN,getAliasJoin()),
                new KeyValueMemberName(JOIN_OTHERS,getAliasJoinOthers()),
                new KeyValueMemberName(GET_ID,getAliasGetId()),
                new KeyValueMemberName(GET_PRIORITY,getAliasGetPriority()),
                new KeyValueMemberName(SET_PRIORITY,getAliasSetPriority()),
                new KeyValueMemberName(YIELD,getAliasYield()),
                new KeyValueMemberName(SLEEP,getAliasSleep()),
                new KeyValueMemberName(PRINT,getAliasPrint()),
                new KeyValueMemberName(THREAD_EXIT_HOOK,getAliasThreadExitHook())));
        m_.addEntry(getAliasThreadSet(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(THREAD_SET_ADD,getAliasThreadSetAdd()),
                new KeyValueMemberName(THREAD_SET_ALL,getAliasThreadSetAll()),
                new KeyValueMemberName(THREAD_SET_CONTAINS,getAliasThreadSetContains()),
                new KeyValueMemberName(THREAD_SET_REMOVE,getAliasThreadSetRemove()),
                new KeyValueMemberName(THREAD_SET_SNAPSHOT,getAliasThreadSetSnapshot())));
        m_.addEntry(getAliasReentrantLock(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(LOCK,getAliasLock()),
                new KeyValueMemberName(UNLOCK,getAliasUnlock()),
                new KeyValueMemberName(IS_HELD_BY_CURRENT_THREAD,getAliasIsHeldByCurrentThread())));
        m_.addEntry(getAliasRunnable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(RUN,getAliasRun())));
        m_.addEntry(getAliasFormatType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PRINT,getAliasPrint())));
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
        m_.addEntry(getAliasFile(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(READ,getAliasRead()),
                new KeyValueMemberName(WRITE,getAliasWrite()),
                new KeyValueMemberName(APPEND_TO_FILE,getAliasAppendToFile()),
                new KeyValueMemberName(FILE_ABSOLUTE_PATH,getAliasFileAbsolutePath()),
                new KeyValueMemberName(FILE_GET_LENGTH,getAliasFileGetLength()),
                new KeyValueMemberName(FILE_GET_NAME,getAliasFileGetName()),
                new KeyValueMemberName(FILE_GET_PARENT_PATH,getAliasFileGetParentPath()),
                new KeyValueMemberName(FILE_IS_DIRECTORY,getAliasFileIsDirectory()),
                new KeyValueMemberName(FILE_IS_FILE,getAliasFileIsFile()),
                new KeyValueMemberName(FILE_IS_ABSOLUTE,getAliasFileIsAbsolute()),
                new KeyValueMemberName(FILE_READ_BIN,getAliasFileReadBin()),
                new KeyValueMemberName(FILE_WRITE_BIN,getAliasFileWriteBin()),
                new KeyValueMemberName(FILE_DELETE,getAliasFileDelete()),
                new KeyValueMemberName(FILE_RENAME,getAliasFileRename()),
                new KeyValueMemberName(FILE_LAST_MODIF,getAliasFileLastModif()),
                new KeyValueMemberName(FILE_LIST_DIRECTORIES,getAliasFileListDirectories()),
                new KeyValueMemberName(FILE_LIST_FILES,getAliasFileListFiles()),
                new KeyValueMemberName(FILE_ZIP_BIN,getAliasFileZipBin()),
                new KeyValueMemberName(FILE_ZIP_BIN_ARRAY,getAliasFileZipBinArray()),
                new KeyValueMemberName(FILE_ZIP_TEXT,getAliasFileZipText()),
                new KeyValueMemberName(FILE_ZIPPED_BIN,getAliasFileZippedBin()),
                new KeyValueMemberName(FILE_ZIPPED_BIN_ARRAY,getAliasFileZippedBinArray()),
                new KeyValueMemberName(FILE_ZIPPED_TEXT,getAliasFileZippedText()),
                new KeyValueMemberName(FILE_MAKE_DIRS,getAliasFileMakeDirs())));
        m_.addEntry(getAliasEntryBinary(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENTRY_NAME,getAliasEntryName()),
                new KeyValueMemberName(ENTRY_VALUE,getAliasEntryValue())));
        m_.addEntry(getAliasEntryText(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENTRY_NAME,getAliasEntryName()),
                new KeyValueMemberName(ENTRY_VALUE,getAliasEntryValue())));
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
        m_.addEntry(getAliasExecute(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(EXECUTE_TESTS,getAliasExecuteTests()),
                new KeyValueMemberName(EXECUTE_CONVERT,getAliasExecuteConvert()),
                new KeyValueMemberName(EXECUTE_SETUP_ERROR,getAliasExecuteSetupError()),
                new KeyValueMemberName(EXECUTE_SETUP_NO_EXCEPTION,getAliasExecuteSetupNoException()),
                new KeyValueMemberName(EXECUTE_EXECUTE,getAliasExecuteExecute())
        ));
        m_.addEntry(getAliasParameters(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PARAMETERS_LOCATION,getAliasParametersLocation()),
                new KeyValueMemberName(PARAMETERS_METHOD,getAliasParametersMethod())
        ));
        m_.addEntry(getAliasTest(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TEST_EXCEPTION,getAliasTestException()),
                new KeyValueMemberName(TEST_NULL_EXCEPTION,getAliasTestNullException())
        ));
        m_.addEntry(getAliasAssert(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ASSERT_ASSERT,getAliasAssertAssert()),
                new KeyValueMemberName(ASSERT_ASSERT_NOT_NULL,getAliasAssertAssertNotNull()),
                new KeyValueMemberName(ASSERT_ASSERT_NULL,getAliasAssertAssertNull()),
                new KeyValueMemberName(ASSERT_ASSERT_SAME,getAliasAssertAssertSame()),
                new KeyValueMemberName(ASSERT_ASSERT_TRUE,getAliasAssertAssertTrue())
        ));
        m_.addEntry(getAliasDifference(), new CustList<KeyValueMemberName>(
        ));
        return m_;
    }

    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ = new StringMap<String>();
        ref_.addEntry(THREAD,getAliasThread());
        ref_.addEntry(THREAD_SET,getAliasThreadSet());
        ref_.addEntry(REENTRANT_LOCK,getAliasReentrantLock());
        ref_.addEntry(ATOMIC_BOOLEAN,getAliasAtomicBoolean());
        ref_.addEntry(ATOMIC_INTEGER,getAliasAtomicInteger());
        ref_.addEntry(ATOMIC_LONG,getAliasAtomicLong());
        ref_.addEntry(FILE,getAliasFile());
        ref_.addEntry(ILLEGAL_THREAD_STATE_EXCEPTION,getAliasIllegalThreadStateException());
        ref_.addEntry(CUST_ITERATOR,getAliasCustIterator());
        ref_.addEntry(LIST,getAliasList());
        ref_.addEntry(RUNNABLE,getAliasRunnable());
        ref_.addEntry(FORMAT_TYPE,getAliasFormatType());
        ref_.addEntry(CUST_PAIR,getAliasCustPair());
        ref_.addEntry(CUST_ITER_TABLE,getAliasCustIterTable());
        ref_.addEntry(TABLE,getAliasTable());
        ref_.addEntry(EXECUTE,getAliasExecute());
        ref_.addEntry(INFO_TEST,getAliasInfoTest());
        ref_.addEntry(EXECUTED_TEST,getAliasExecutedTest());
        ref_.addEntry(RESULT,getAliasResult());
        ref_.addEntry(BEFORE,getAliasBefore());
        ref_.addEntry(AFTER,getAliasAfter());
        ref_.addEntry(PARAMETERS,getAliasParameters());
        ref_.addEntry(TEST,getAliasTest());
        ref_.addEntry(ASSERT,getAliasAssert());
        ref_.addEntry(DIFFERENCE,getAliasDifference());
        ref_.addEntry(CONCURRENT_ERROR,getAliasConcurrentError());
        return ref_;
    }
    public ResultErrorStd instance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        try {
            return ApplyCoreMethodUtil.instanceBase(_cont, _method, _args);
        } catch (RuntimeException e) {
            _cont.setException(new ErrorStruct(_cont, getAliasConcurrentError()));
            return new ResultErrorStd();
        }
    }
    public ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, Argument... _args) {
        try {
            return ApplyCoreMethodUtil.invokeBase(_cont, _method, _struct, _exit, _args);
        } catch (RuntimeException e) {
            _cont.setException(new ErrorStruct(_cont, getAliasConcurrentError()));
            return new ResultErrorStd();
        }
    }
    public Argument defaultInstance(ContextEl _cont, String _id) {
        if (StringList.quickEq(_id, _cont.getStandards().getContent().getCoreNames().getAliasObject())) {
            return new Argument(new SimpleObjectStruct());
        }
        if (StringList.quickEq(_id,aliasThread)) {
            _cont.setException(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg()));
            return Argument.createVoid();
        }
        if (StringList.quickEq(_id,aliasThreadSet)) {
            ThreadSetStruct std_ = new ThreadSetStruct();
            return new Argument(std_);
        }
        if (StringList.quickEq(_id,aliasReentrantLock)) {
            AbstractLock re_ = LockFactory.newLock();
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
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String name_ = _method.getName();
        if (StringList.quickEq(name_, _cont.getStandards().getContent().getCoreNames().getAliasObject())) {
            res_.setResult(new SimpleObjectStruct());
            return res_;
        }
        if (StringList.quickEq(name_,aliasThreadSet)) {
            ThreadSetStruct std_ = new ThreadSetStruct();
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasThread)) {
            if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont);
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
            ThreadStruct std_ = new ThreadStruct(thread_);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasReentrantLock)) {
            if (_method.getParametersTypes().isEmpty()) {
                AbstractLock re_ = LockFactory.newLock();
                StdStruct std_ = StdStruct.newInstance(re_, aliasReentrantLock);
                res_.setResult(std_);
                return res_;
            }
            AbstractLock re_ = LockFactory.newLock();
            re_.setSleep(((NumberStruct)_args[0]).longStruct());
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
            AtomicBoolean at_ = new AtomicBoolean(BooleanStruct.isTrue(_args[0]));
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
        if (StringList.quickEq(name_,aliasEntryBinary)) {
            String cont_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimByte();
            cont_ = StringExpUtil.getPrettyArrayType(cont_);
            EntryBinaryStruct std_ = new EntryBinaryStruct(_args[0],_args[1],cont_);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasEntryText)) {
            EntryTextStruct std_ = new EntryTextStruct(_args[0],_args[1]);
            res_.setResult(std_);
            return res_;
        }
        return res_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, ExecutingBlocks _execBlocks, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String className_ = _method.getClassName();
        String type_ = _method.getClassName();
        if (StringList.quickEq(type_, _cont.getStandards().getContent().getCoreNames().getAliasEnums())) {
            return ApplyCoreMethodUtil.getOtherResultBase(_cont, _method, _args);
        }
        if (StringList.quickEq(className_,aliasThreadSet)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasThreadSetAdd)) {
                if (_cont.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _cont.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                ins_.add(_args[0]);
                if (!(_args[0] instanceof ThreadStruct)) {
                    _cont.setException(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe()));
                } else {
                    res_.setResult(NullStruct.NULL_VALUE);
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadSetAll)) {
                if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(((RunnableContextEl)_cont).getCustInit().getThreadSet());
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadSetRemove)) {
                if (_cont.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _cont.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                ins_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadSetContains)) {
                if (_cont.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _cont.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                res_.setResult(ins_.contains(_args[0]));
                return res_;
            }
            ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
            res_.setResult(ins_.toSnapshotArray(_cont));
            return res_;
        }
        if (StringList.quickEq(className_,aliasThread)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasPrint)) {
                if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                CustInitializer cust_ = ((RunnableContextEl)_cont).getCustInit();
                String dtPart_ = cust_.getCurrentFileThread((RunnableContextEl)_cont);
                log(dtPart_, (RunnableContextEl)_cont, _method, _execBlocks, _args);
                ResultErrorStd out_ = new ResultErrorStd();
                out_.setResult(NullStruct.NULL_VALUE);
                return out_;
            }
            if (StringList.quickEq(name_,aliasStart)) {
                Thread thread_ = ((ThreadStruct) _instance).getThread();
                if (ThreadUtil.start(thread_)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                } else {
                    _cont.setException(new ErrorStruct(_cont,getAliasIllegalThreadStateException()));
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasSleep)) {
                if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (!(_args[0] instanceof NumberStruct)) {
                    _cont.setException(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe()));
                    return res_;
                }
                res_.setResult(BooleanStruct.of(ThreadUtil.sleep(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasJoin)) {
                Thread thread_ = ((ThreadStruct) _instance).getThread();
                boolean alive_ = thread_.isAlive();
                ThreadUtil.join(thread_);
                res_.setResult(BooleanStruct.of(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasCurrentThread)) {
                if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(((RunnableContextEl)_cont).getThread());
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadExitHook)) {
                if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (!(_args[0] instanceof ThreadStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadStruct a_ = (ThreadStruct) _args[0];
                ((RunnableContextEl)_cont).getCustInit().initHook(a_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasJoinOthers)) {
                if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                CustInitializer cust_ = ((RunnableContextEl)_cont).getCustInit();
                cust_.joinOthers((RunnableContextEl) _cont);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsAlive)) {
                Thread thread_ = ((ThreadStruct) _instance).getThread();
                boolean alive_ = thread_.isAlive();
                res_.setResult(BooleanStruct.of(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsEnded)) {
                boolean alive_ = ((ThreadStruct) _instance).isEnded();
                res_.setResult(BooleanStruct.of(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasEnd)) {
                ((ThreadStruct) _instance).end();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetId)) {
                Thread thread_ = ((ThreadStruct) _instance).getThread();
                res_.setResult(new LongStruct(thread_.getId()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetPriority)) {
                Thread thread_ = ((ThreadStruct) _instance).getThread();
                res_.setResult(new IntStruct(thread_.getPriority()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetPriority)) {
                Thread thread_ = ((ThreadStruct) _instance).getThread();
                if (ThreadUtil.setPriority(thread_,((NumberStruct)_args[0]).intStruct())) {
                    res_.setResult(NullStruct.NULL_VALUE);
                } else {
                    _cont.setException(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg()));
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasYield)) {
                if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                Thread.yield();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new LongStruct(System.currentTimeMillis()));
            return res_;
        }
        if (StringList.quickEq(className_,aliasReentrantLock)) {
            if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasLock)) {
                AbstractLock re_ = (AbstractLock) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.lock((RunnableContextEl)_cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasUnlock)) {
                AbstractLock re_ = (AbstractLock) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.unlock((RunnableContextEl)_cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsHeldByCurrentThread)) {
                AbstractLock re_ = (AbstractLock) ((StdStruct) _instance).getInstance();
                boolean held_ = re_.heldLock((RunnableContextEl)_cont);
                res_.setResult(BooleanStruct.of(held_));
                return res_;
            }
        }
        if (StringList.quickEq(className_,aliasAtomicBoolean)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
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
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
                re_.set(BooleanStruct.isTrue(_args[0]));
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasCompareAndSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(BooleanStruct.isTrue(_args[0]),BooleanStruct.isTrue(_args[1]))));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.getAndSet(BooleanStruct.isTrue(_args[0]))));
                return res_;
            }
            AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
            re_.lazySet(BooleanStruct.isTrue(_args[0]));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(className_,aliasAtomicInteger)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
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
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                re_.set(((NumberStruct)_args[0]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasCompareAndSetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndSetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndSet(((NumberStruct)_args[0]).intStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndAddAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndAdd(((NumberStruct)_args[0]).intStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasAddAndGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.addAndGet(((NumberStruct)_args[0]).intStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndIncrementAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndIncrement()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIncrementAndGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.incrementAndGet()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndDecrementAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndDecrement()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasDecrementAndGetAtomic)) {
                AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.decrementAndGet()));
                return res_;
            }
            AtomicInteger re_ = (AtomicInteger) ((StdStruct) _instance).getInstance();
            re_.lazySet(((NumberStruct)_args[0]).intStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(className_,aliasAtomicLong)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasGetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
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
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                re_.set(((NumberStruct)_args[0]).longStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasCompareAndSetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(((NumberStruct)_args[0]).longStruct(),((NumberStruct)_args[1]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndSetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndSet(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndAddAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndAdd(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasAddAndGetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.addAndGet(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndIncrementAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndIncrement()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIncrementAndGetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.incrementAndGet()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndDecrementAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndDecrement()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasDecrementAndGetAtomic)) {
                AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.decrementAndGet()));
                return res_;
            }
            AtomicLong re_ = (AtomicLong) ((StdStruct) _instance).getInstance();
            re_.lazySet(((NumberStruct)_args[0]).longStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(className_,aliasEntryText)) {
            String name_ = _method.getConstraints().getName();
            EntryTextStruct inst_ = (EntryTextStruct) _instance;
            if (StringList.quickEq(name_,aliasEntryName)) {
                res_.setResult(inst_.getName());
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringList.quickEq(className_,aliasEntryBinary)) {
            String name_ = _method.getConstraints().getName();
            EntryBinaryStruct inst_ = (EntryBinaryStruct) _instance;
            if (StringList.quickEq(name_,aliasEntryName)) {
                res_.setResult(inst_.getName());
                return res_;
            }
            ArrayStruct bin_ = inst_.getBinary();
            _cont.getInitializingTypeInfos().addSensibleField(inst_, bin_);
            res_.setResult(bin_);
            return res_;
        }
        if (StringList.quickEq(className_,aliasFile)) {
            String name_ = _method.getConstraints().getName();
            if (_cont.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZipBinArray)) {
                res_.setResult(ZipBinStructUtil.zipBinFiles(_args[0],(RunnableContextEl) _cont));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZippedBinArray)) {
                res_.setResult(ZipBinStructUtil.zippedBinaryFilesByteArray(_args[0], (RunnableContextEl) _cont));
                return res_;
            }
            if (!(_args[0] instanceof StringStruct)) {
                _cont.setException(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasRead)) {
                StringStruct str_ = (StringStruct)_args[0];
                String read_ = infos.getFileSystem().contentsOfFile(str_.getInstance(), (RunnableContextEl) _cont);
                if (read_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new StringStruct(read_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasWrite)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                String txt_ = getStandarString(_cont,_args[1]);
                res_.setResult(BooleanStruct.of(infos.getFileSystem().saveTextFile(file_, txt_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileReadBin)) {
                StringStruct str_ = (StringStruct)_args[0];
                byte[] read_ = infos.getFileSystem().loadFile(str_.getInstance(), (RunnableContextEl) _cont);
                if (read_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                int len_ = read_.length;
                ArrayStruct bin_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getPrimTypes().getAliasPrimByte()));
                for (int i = 0; i < len_; i++) {
                    bin_.set(i, new ByteStruct(read_[i]));
                }
                res_.setResult(bin_);
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileWriteBin)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                if (!(_args[1] instanceof ArrayStruct)) {
                    res_.setResult(BooleanStruct.of(false));
                    return res_;
                }
                ArrayStruct arr_ = (ArrayStruct) _args[1];
                int len_ = arr_.getLength();
                byte[] bin_ = new byte[len_];
                for (int i = 0; i < len_; i++) {
                    Struct byte_ = arr_.get(i);
                    if (!(byte_ instanceof NumberStruct)) {
                        continue;
                    }
                    bin_[i] = ((NumberStruct)byte_).byteStruct();
                }
                res_.setResult(BooleanStruct.of(infos.getFileSystem().writeFile(file_, bin_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileDelete)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(BooleanStruct.of(infos.getFileSystem().delete(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileRename)) {
                if (!(_args[1] instanceof StringStruct)) {
                    _cont.setException(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe()));
                    return res_;
                }
                String file_ = ((StringStruct)_args[0]).getInstance();
                String dest_ = ((StringStruct)_args[1]).getInstance();
                res_.setResult(BooleanStruct.of(infos.getFileSystem().rename(file_,dest_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasAppendToFile)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                String txt_ = getStandarString(_cont,_args[1]);
                res_.setResult(BooleanStruct.of(infos.getFileSystem().logToFile(file_, txt_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileAbsolutePath)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new StringStruct(infos.getFileSystem().absolutePath(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileGetLength)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new LongStruct(infos.getFileSystem().length(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileGetName)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new StringStruct(infos.getFileSystem().getName(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileGetParentPath)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new StringStruct(infos.getFileSystem().getParentPath(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileIsDirectory)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(BooleanStruct.of(infos.getFileSystem().isDirectory(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileIsFile)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(BooleanStruct.of(infos.getFileSystem().isFile(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileLastModif)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new LongStruct(infos.getFileSystem().lastModified(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileListFiles)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                StringList files_ = infos.getFileSystem().getFiles(file_, (RunnableContextEl) _cont);
                if (files_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                int len_ = files_.size();
                ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getCharSeq().getAliasString()));
                for (int i = 0; i < len_; i++) {
                    arr_.set(i, new StringStruct(files_.get(i)));
                }
                res_.setResult(arr_);
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileListDirectories)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                StringList files_ = infos.getFileSystem().getFolders(file_, (RunnableContextEl) _cont);
                if (files_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                int len_ = files_.size();
                ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getCharSeq().getAliasString()));
                for (int i = 0; i < len_; i++) {
                    arr_.set(i, new StringStruct(files_.get(i)));
                }
                res_.setResult(arr_);
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZipBin)) {
                String fileName_ = ((StringStruct)_args[0]).getInstance();
                Struct struct_ = ZipBinStructUtil.zipBinFiles(_args[1], (RunnableContextEl) _cont);
                if (struct_ instanceof ArrayStruct) {
                    int len_ = ((ArrayStruct)struct_).getLength();
                    byte[] file_ = new byte[len_];
                    for (int i = 0; i < len_; i++) {
                        Struct byte_ = ((ArrayStruct)struct_).get(i);
                        if (!(byte_ instanceof ByteStruct)) {
                            continue;
                        }
                        file_[i] = ((ByteStruct)byte_).byteStruct();
                    }
                    res_.setResult(BooleanStruct.of(infos.getFileSystem().writeFile(fileName_,file_, (RunnableContextEl) _cont)));
                } else {
                    res_.setResult(BooleanStruct.of(false));
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZipText)) {
                CustList<EntryBinaryStruct> bins_ = new CustList<EntryBinaryStruct>();
                if (_args[1] instanceof ArrayStruct) {
                    ArrayStruct arr_ = (ArrayStruct) _args[1];
                    for (Struct s: arr_.getInstance()) {
                        if (s instanceof EntryTextStruct) {
                            EntryTextStruct cont_ = (EntryTextStruct)s;
                            byte[] encoded_ = StringList.encode(cont_.getText().getInstance());
                            String contType_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimByte();
                            contType_ = StringExpUtil.getPrettyArrayType(contType_);
                            int bLen_ = encoded_.length;
                            ArrayStruct bs_ = new ArrayStruct(bLen_,contType_);
                            for (int j = 0; j < bLen_; j++) {
                                bs_.set(j, new ByteStruct(encoded_[j]));
                            }
                            bins_.add(new EntryBinaryStruct(cont_.getName(), bs_));
                        }
                    }
                }
                int bLen_ = bins_.size();
                String arrType_ = getAliasEntryBinary();
                arrType_ = StringExpUtil.getPrettyArrayType(arrType_);
                ArrayStruct bs_ = new ArrayStruct(bLen_,arrType_);
                for (int j = 0; j < bLen_; j++) {
                    bs_.set(j, bins_.get(j));
                }
                byte[] finalFile_ = ZipBinStructUtil.getZipBinFileAsArray(bs_);
                if (finalFile_ != null) {
                    StringStruct str_ = (StringStruct)_args[0];
                    res_.setResult(BooleanStruct.of(infos.getFileSystem().writeFile(str_.getInstance(),finalFile_, (RunnableContextEl) _cont)));
                    return res_;
                }
                res_.setResult(BooleanStruct.of(false));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZippedBin)) {
                StringStruct str_ = (StringStruct)_args[0];
                byte[] bytes_ = infos.getFileSystem().loadFile(str_.getInstance(), (RunnableContextEl) _cont);
                if (bytes_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                String cont_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimByte();
                cont_ = StringExpUtil.getPrettyArrayType(cont_);
                int bLen_ = bytes_.length;
                ArrayStruct bs_ = new ArrayStruct(bLen_,cont_);
                for (int j = 0; j < bLen_; j++) {
                    bs_.set(j, new ByteStruct(bytes_[j]));
                }
                res_.setResult(ZipBinStructUtil.zippedBinaryFilesByteArray(bs_, (RunnableContextEl) _cont));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZippedText)) {
                StringStruct str_ = (StringStruct)_args[0];
                byte[] bytes_ = infos.getFileSystem().loadFile(str_.getInstance(), (RunnableContextEl) _cont);
                if (bytes_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                CustList<EntryBinaryStruct> arrList_ = ZipBinStructUtil.getEntryBinaryStructs(bytes_, (RunnableContextEl) _cont);
                if (arrList_ != null) {
                    String arrType_ = getAliasEntryText();
                    arrType_ = StringExpUtil.getPrettyArrayType(arrType_);
                    int len_ = arrList_.size();
                    ArrayStruct filesOut_ = new ArrayStruct(len_,arrType_);
                    for (int i = 0; i < len_; i++) {
                        EntryBinaryStruct fileBin_ = arrList_.get(i);
                        ArrayStruct bin_ = fileBin_.getBinary();
                        int contLen_ = bin_.getLength();
                        byte[] prim_ = new byte[contLen_];
                        for (int j = 0; j < contLen_; j++) {
                            prim_[j] = ((NumberStruct)bin_.get(j)).byteStruct();
                        }
                        String dec_ = StringList.decode(prim_);
                        if (dec_ == null) {
                            filesOut_.set(i, new EntryTextStruct(fileBin_.getName(),NullStruct.NULL_VALUE));
                        } else {
                            filesOut_.set(i, new EntryTextStruct(fileBin_.getName(),new StringStruct(dec_)));
                        }
                    }
                    res_.setResult(filesOut_);
                    return res_;
                }
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileIsAbsolute)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(BooleanStruct.of(infos.getFileSystem().isAbsolute(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            String file_ = ((StringStruct)_args[0]).getInstance();
            res_.setResult(BooleanStruct.of(infos.getFileSystem().mkdirs(file_, (RunnableContextEl) _cont)));
            return res_;
        }
        return res_;
    }

    private void log(String _dtPart, RunnableContextEl _cont,
                     ClassMethodId _method, ExecutingBlocks _execBlocks, Struct... _args) {
        if (_method.getConstraints().getParametersTypes().size() == 1) {
            String type_ = _method.getConstraints().getParametersTypes().first();
            String aliasObject_ = _cont.getStandards().getContent().getCoreNames().getAliasObject();
            if (StringList.quickEq(type_, aliasObject_)) {
                String className_ = aliasFormatType;
                ExecRootBlock classBody_ = _execBlocks.getFormatType();
                ExecNamedFunctionBlock fct_ = _execBlocks.getFormatObject();
                Argument arg_ = new Argument(_args[0]);
                ExecTemplates.wrapAndCall(fct_,classBody_,className_,Argument.createVoid(),new CustList<Argument>(arg_),_cont);
                return;
            }
        }
        if (_method.getConstraints().getParametersTypes().size() == 2) {
            String className_ = aliasFormatType;
            ExecRootBlock classBody_ = _execBlocks.getFormatType();
            ExecNamedFunctionBlock fct_ = _execBlocks.getFormatObjectTwo();
            Argument arg_ = new Argument(_args[0]);
            Argument argArr_ = new Argument(_args[1]);
            ExecTemplates.wrapAndCall(fct_,classBody_,className_,Argument.createVoid(),new CustList<Argument>(arg_,argArr_),_cont);
            return;
        }
        String stringAppFile_ = buildLog(_cont, _args);
        stringAppFile_ = StringList.concat(getDateTimeText("_", "_", "_"),":",stringAppFile_);
        String folder_ = _cont.getExecutingOptions().getLogFolder();
        log(folder_,_dtPart,stringAppFile_,_cont);
    }
    void log(String _folerName,String _fileName, String _content,RunnableContextEl _cont){
        infos.getLogger().log(_folerName,_fileName,_content,_cont);
    }
    private static String buildLog(ContextEl _cont,
                                   Struct... _args) {
        String stringAppFile_;
        stringAppFile_ = getStandarString(_cont,_args[0]);
        return stringAppFile_;
    }
    private static String getStandarString(ContextEl _cont, Struct _struct) {
        if (_struct instanceof DisplayableStruct) {
            return ((DisplayableStruct)_struct).getDisplayedString(_cont).getInstance();
        }
        return StringList.concat(_cont.getStandards().getStringOfObject(_cont,_struct).getInstance(),"...");
    }
    public void processFailInit(ContextEl _cont) {
        if (_cont.getInitializingTypeInfos().isInitEnums()) {
            _cont.getInitializingTypeInfos().failInitEnums();
        } else {
            _cont.setException(new ErrorStruct(_cont, aliasConcurrentError));
        }
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

    public CustList<CommentDelimiters> defComments() {
        String content_ = infos.getReader().read("resources_lg/aliases/comments.properties");
        content_ = content_.substring(content_.indexOf('=')+1);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        for (String c: StringList.splitChar(
                content_.trim(),
                ';')) {
            StringList parts_ = StringList.splitChar(
                    c.trim(),
                    ',');
            String begin_ = ParseLinesArgUtil.parseValue(parts_.first());
            String end_ = ParseLinesArgUtil.parseValue(parts_.last());
            comments_.add(new CommentDelimiters(begin_,new StringList(end_)));
        }
        return comments_;
    }
    public String getAliasRunnable() {
        return aliasRunnable;
    }

    public void setAliasRunnable(String _aliasRunnable) {
        aliasRunnable = _aliasRunnable;
    }

    public String getAliasThreadSet() {
        return aliasThreadSet;
    }

    public void setAliasThreadSet(String _aliasThreadSet) {
        aliasThreadSet = _aliasThreadSet;
    }

    public String getAliasThreadSetAll() {
        return aliasThreadSetAll;
    }

    public void setAliasThreadSetAll(String _aliasThreadSetAll) {
        aliasThreadSetAll = _aliasThreadSetAll;
    }

    public String getAliasThreadSetAdd() {
        return aliasThreadSetAdd;
    }

    public void setAliasThreadSetAdd(String _aliasThreadSetAdd) {
        aliasThreadSetAdd = _aliasThreadSetAdd;
    }

    public String getAliasThreadSetContains() {
        return aliasThreadSetContains;
    }

    public void setAliasThreadSetContains(String _aliasThreadSetContains) {
        aliasThreadSetContains = _aliasThreadSetContains;
    }

    public String getAliasThreadSetRemove() {
        return aliasThreadSetRemove;
    }

    public void setAliasThreadSetRemove(String _aliasThreadSetRemove) {
        aliasThreadSetRemove = _aliasThreadSetRemove;
    }

    public String getAliasThreadSetSnapshot() {
        return aliasThreadSetSnapshot;
    }

    public void setAliasThreadSetSnapshot(String _aliasThreadSetSnapshot) {
        aliasThreadSetSnapshot = _aliasThreadSetSnapshot;
    }

    public String getAliasThread() {
        return aliasThread;
    }

    public void setAliasThread(String _aliasThread) {
        aliasThread = _aliasThread;
    }

    public String getAliasThreadExitHook() {
        return aliasThreadExitHook;
    }

    public void setAliasThreadExitHook(String aliasThreadExitHook) {
        this.aliasThreadExitHook = aliasThreadExitHook;
    }

    public String getAliasThreadCurrentTime() {
        return aliasThreadCurrentTime;
    }

    public void setAliasThreadCurrentTime(String _aliasThreadCurrentTime) {
        aliasThreadCurrentTime = _aliasThreadCurrentTime;
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

    public String getAliasCurrentThread() {
        return aliasCurrentThread;
    }

    public void setAliasCurrentThread(String _aliasCurrentThread) {
        aliasCurrentThread = _aliasCurrentThread;
    }

    public String getAliasJoin() {
        return aliasJoin;
    }
    public void setAliasJoin(String _aliasJoin) {
        aliasJoin = _aliasJoin;
    }

    public String getAliasJoinOthers() {
        return aliasJoinOthers;
    }

    public void setAliasJoinOthers(String _aliasJoinOthers) {
        aliasJoinOthers = _aliasJoinOthers;
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

    public String getAliasIsEnded() {
        return aliasIsEnded;
    }

    public void setAliasIsEnded(String aliasIsEnded) {
        this.aliasIsEnded = aliasIsEnded;
    }

    public String getAliasEnd() {
        return aliasEnd;
    }

    public void setAliasEnd(String aliasEnd) {
        this.aliasEnd = aliasEnd;
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

    public String getAliasFormatType() {
        return aliasFormatType;
    }

    public void setAliasFormatType(String _aliasFormatType) {
        aliasFormatType = _aliasFormatType;
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

    public String getAliasFileIsDirectory() {
        return aliasFileIsDirectory;
    }

    public void setAliasFileIsDirectory(String aliasFileIsDirectory) {
        this.aliasFileIsDirectory = aliasFileIsDirectory;
    }

    public String getAliasFileIsFile() {
        return aliasFileIsFile;
    }

    public void setAliasFileIsFile(String aliasFileIsFile) {
        this.aliasFileIsFile = aliasFileIsFile;
    }

    public String getAliasFileGetParentPath() {
        return aliasFileGetParentPath;
    }

    public void setAliasFileGetParentPath(String aliasFileGetParentPath) {
        this.aliasFileGetParentPath = aliasFileGetParentPath;
    }

    public String getAliasFileGetName() {
        return aliasFileGetName;
    }

    public void setAliasFileGetName(String aliasFileGetName) {
        this.aliasFileGetName = aliasFileGetName;
    }

    public String getAliasFileGetLength() {
        return aliasFileGetLength;
    }

    public void setAliasFileGetLength(String aliasFileGetLength) {
        this.aliasFileGetLength = aliasFileGetLength;
    }

    public String getAliasFileAbsolutePath() {
        return aliasFileAbsolutePath;
    }

    public void setAliasFileAbsolutePath(String aliasFileAbsolutePath) {
        this.aliasFileAbsolutePath = aliasFileAbsolutePath;
    }

    public String getAliasFileLastModif() {
        return aliasFileLastModif;
    }

    public void setAliasFileLastModif(String aliasFileLastModif) {
        this.aliasFileLastModif = aliasFileLastModif;
    }

    public String getAliasFileListFiles() {
        return aliasFileListFiles;
    }

    public void setAliasFileListFiles(String aliasFileListFiles) {
        this.aliasFileListFiles = aliasFileListFiles;
    }

    public String getAliasFileListDirectories() {
        return aliasFileListDirectories;
    }

    public void setAliasFileListDirectories(String aliasFileListDirectories) {
        this.aliasFileListDirectories = aliasFileListDirectories;
    }

    public String getAliasFileZippedBin() {
        return aliasFileZippedBin;
    }

    public void setAliasFileZippedBin(String aliasFileZippedBin) {
        this.aliasFileZippedBin = aliasFileZippedBin;
    }

    public String getAliasFileZippedBinArray() {
        return aliasFileZippedBinArray;
    }

    public void setAliasFileZippedBinArray(String aliasFileZippedBinArray) {
        this.aliasFileZippedBinArray = aliasFileZippedBinArray;
    }

    public String getAliasFileZippedText() {
        return aliasFileZippedText;
    }

    public void setAliasFileZippedText(String aliasFileZippedText) {
        this.aliasFileZippedText = aliasFileZippedText;
    }

    public String getAliasFileZipBin() {
        return aliasFileZipBin;
    }

    public void setAliasFileZipBin(String aliasFileZipBin) {
        this.aliasFileZipBin = aliasFileZipBin;
    }

    public String getAliasFileZipBinArray() {
        return aliasFileZipBinArray;
    }

    public void setAliasFileZipBinArray(String aliasFileZipBinArray) {
        this.aliasFileZipBinArray = aliasFileZipBinArray;
    }

    public String getAliasFileZipText() {
        return aliasFileZipText;
    }

    public void setAliasFileZipText(String aliasFileZipText) {
        this.aliasFileZipText = aliasFileZipText;
    }

    public String getAliasEntryBinary() {
        return aliasEntryBinary;
    }

    public void setAliasEntryBinary(String aliasEntryBinary) {
        this.aliasEntryBinary = aliasEntryBinary;
    }

    public String getAliasEntryText() {
        return aliasEntryText;
    }

    public void setAliasEntryText(String aliasEntryText) {
        this.aliasEntryText = aliasEntryText;
    }

    public String getAliasEntryName() {
        return aliasEntryName;
    }

    public void setAliasEntryName(String aliasEntryName) {
        this.aliasEntryName = aliasEntryName;
    }

    public String getAliasEntryValue() {
        return aliasEntryValue;
    }

    public void setAliasEntryValue(String aliasEntryValue) {
        this.aliasEntryValue = aliasEntryValue;
    }

    public String getAliasFileIsAbsolute() {
        return aliasFileIsAbsolute;
    }

    public void setAliasFileIsAbsolute(String aliasFileIsAbsolute) {
        this.aliasFileIsAbsolute = aliasFileIsAbsolute;
    }

    public String getAliasFileReadBin() {
        return aliasFileReadBin;
    }

    public void setAliasFileReadBin(String aliasFileReadBin) {
        this.aliasFileReadBin = aliasFileReadBin;
    }

    public String getAliasFileWriteBin() {
        return aliasFileWriteBin;
    }

    public void setAliasFileWriteBin(String aliasFileWriteBin) {
        this.aliasFileWriteBin = aliasFileWriteBin;
    }

    public String getAliasFileDelete() {
        return aliasFileDelete;
    }

    public void setAliasFileDelete(String aliasFileDelete) {
        this.aliasFileDelete = aliasFileDelete;
    }

    public String getAliasFileRename() {
        return aliasFileRename;
    }

    public void setAliasFileRename(String aliasFileRename) {
        this.aliasFileRename = aliasFileRename;
    }

    public String getAliasFileMakeDirs() {
        return aliasFileMakeDirs;
    }

    public void setAliasFileMakeDirs(String aliasFileMakeDirs) {
        this.aliasFileMakeDirs = aliasFileMakeDirs;
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

    public String getAliasExecute() {
        return aliasExecute;
    }

    public void setAliasExecute(String aliasExecute) {
        this.aliasExecute = aliasExecute;
    }

    public String getAliasResult() {
        return aliasResult;
    }

    public void setAliasResult(String aliasResult) {
        this.aliasResult = aliasResult;
    }

    public String getAliasExecutedTest() {
        return aliasExecutedTest;
    }

    public void setAliasExecutedTest(String aliasExecutedTest) {
        this.aliasExecutedTest = aliasExecutedTest;
    }

    public String getAliasTest() {
        return aliasTest;
    }

    public void setAliasTest(String aliasTest) {
        this.aliasTest = aliasTest;
    }

    public String getAliasAfter() {
        return aliasAfter;
    }

    public void setAliasAfter(String aliasAfter) {
        this.aliasAfter = aliasAfter;
    }

    public String getAliasBefore() {
        return aliasBefore;
    }

    public void setAliasBefore(String aliasBefore) {
        this.aliasBefore = aliasBefore;
    }

    public String getAliasParameters() {
        return aliasParameters;
    }

    public void setAliasParameters(String aliasParameters) {
        this.aliasParameters = aliasParameters;
    }

    public String getAliasAssert() {
        return aliasAssert;
    }

    public void setAliasAssert(String aliasAssert) {
        this.aliasAssert = aliasAssert;
    }

    public String getAliasDifference() {
        return aliasDifference;
    }

    public void setAliasDifference(String aliasDifference) {
        this.aliasDifference = aliasDifference;
    }

    public String getAliasInfoTest() {
        return aliasInfoTest;
    }

    public void setAliasInfoTest(String aliasInfoTest) {
        this.aliasInfoTest = aliasInfoTest;
    }

    public String getAliasTestException() {
        return aliasTestException;
    }

    public void setAliasTestException(String aliasTestException) {
        this.aliasTestException = aliasTestException;
    }

    public String getAliasTestNullException() {
        return aliasTestNullException;
    }

    public void setAliasTestNullException(String aliasTestNullException) {
        this.aliasTestNullException = aliasTestNullException;
    }

    public String getAliasExecutedTestAnnotations() {
        return aliasExecutedTestAnnotations;
    }

    public void setAliasExecutedTestAnnotations(String aliasExecutedTestAnnotations) {
        this.aliasExecutedTestAnnotations = aliasExecutedTestAnnotations;
    }

    public String getAliasExecutedTestTest() {
        return aliasExecutedTestTest;
    }

    public void setAliasExecutedTestTest(String aliasExecutedTestTest) {
        this.aliasExecutedTestTest = aliasExecutedTestTest;
    }

    public String getAliasExecutedTestBefore() {
        return aliasExecutedTestBefore;
    }

    public void setAliasExecutedTestBefore(String aliasExecutedTestBefore) {
        this.aliasExecutedTestBefore = aliasExecutedTestBefore;
    }

    public String getAliasExecutedTestAfter() {
        return aliasExecutedTestAfter;
    }

    public void setAliasExecutedTestAfter(String aliasExecutedTestAfter) {
        this.aliasExecutedTestAfter = aliasExecutedTestAfter;
    }

    public String getAliasExecutedTestMethod() {
        return aliasExecutedTestMethod;
    }

    public void setAliasExecutedTestMethod(String aliasExecutedTestMethod) {
        this.aliasExecutedTestMethod = aliasExecutedTestMethod;
    }

    public String getAliasResultSuccess() {
        return aliasResultSuccess;
    }

    public void setAliasResultSuccess(String aliasResultSuccess) {
        this.aliasResultSuccess = aliasResultSuccess;
    }

    public String getAliasResultFailMessage() {
        return aliasResultFailMessage;
    }

    public void setAliasResultFailMessage(String aliasResultFailMessage) {
        this.aliasResultFailMessage = aliasResultFailMessage;
    }

    public String getAliasResultParams() {
        return aliasResultParams;
    }

    public void setAliasResultParams(String aliasResultParams) {
        this.aliasResultParams = aliasResultParams;
    }

    public String getAliasParametersMethod() {
        return aliasParametersMethod;
    }

    public void setAliasParametersMethod(String aliasParametersMethod) {
        this.aliasParametersMethod = aliasParametersMethod;
    }

    public String getAliasParametersLocation() {
        return aliasParametersLocation;
    }

    public void setAliasParametersLocation(String aliasParametersLocation) {
        this.aliasParametersLocation = aliasParametersLocation;
    }

    public String getAliasExecuteTests() {
        return aliasExecuteTests;
    }

    public void setAliasExecuteTests(String aliasExecuteTests) {
        this.aliasExecuteTests = aliasExecuteTests;
    }

    public String getAliasExecuteExecute() {
        return aliasExecuteExecute;
    }

    public void setAliasExecuteExecute(String aliasExecuteExecute) {
        this.aliasExecuteExecute = aliasExecuteExecute;
    }

    public String getAliasExecuteConvert() {
        return aliasExecuteConvert;
    }

    public void setAliasExecuteConvert(String aliasExecuteConvert) {
        this.aliasExecuteConvert = aliasExecuteConvert;
    }

    public String getAliasExecuteSetupNoException() {
        return aliasExecuteSetupNoException;
    }

    public void setAliasExecuteSetupNoException(String aliasExecuteSetupNoException) {
        this.aliasExecuteSetupNoException = aliasExecuteSetupNoException;
    }

    public String getAliasExecuteSetupError() {
        return aliasExecuteSetupError;
    }

    public void setAliasExecuteSetupError(String aliasExecuteSetupError) {
        this.aliasExecuteSetupError = aliasExecuteSetupError;
    }

    public String getAliasAssertAssert() {
        return aliasAssertAssert;
    }

    public void setAliasAssertAssert(String aliasAssertAssert) {
        this.aliasAssertAssert = aliasAssertAssert;
    }

    public String getAliasAssertAssertTrue() {
        return aliasAssertAssertTrue;
    }

    public void setAliasAssertAssertTrue(String aliasAssertAssertTrue) {
        this.aliasAssertAssertTrue = aliasAssertAssertTrue;
    }

    public String getAliasAssertAssertNull() {
        return aliasAssertAssertNull;
    }

    public void setAliasAssertAssertNull(String aliasAssertAssertNull) {
        this.aliasAssertAssertNull = aliasAssertAssertNull;
    }

    public String getAliasAssertAssertNotNull() {
        return aliasAssertAssertNotNull;
    }

    public void setAliasAssertAssertNotNull(String aliasAssertAssertNotNull) {
        this.aliasAssertAssertNotNull = aliasAssertAssertNotNull;
    }

    public String getAliasAssertAssertSame() {
        return aliasAssertAssertSame;
    }

    public void setAliasAssertAssertSame(String aliasAssertAssertSame) {
        this.aliasAssertAssertSame = aliasAssertAssertSame;
    }

    public String getAliasDifferenceExpected() {
        return aliasDifferenceExpected;
    }

    public void setAliasDifferenceExpected(String aliasDifferenceExpected) {
        this.aliasDifferenceExpected = aliasDifferenceExpected;
    }

    public String getAliasDifferenceFound() {
        return aliasDifferenceFound;
    }

    public void setAliasDifferenceFound(String aliasDifferenceFound) {
        this.aliasDifferenceFound = aliasDifferenceFound;
    }

    public String getAliasDifferenceFoundNull() {
        return aliasDifferenceFoundNull;
    }

    public void setAliasDifferenceFoundNull(String aliasDifferenceFoundNull) {
        this.aliasDifferenceFoundNull = aliasDifferenceFoundNull;
    }

    public String getAliasDifferenceFoundNotTrue() {
        return aliasDifferenceFoundNotTrue;
    }

    public void setAliasDifferenceFoundNotTrue(String aliasDifferenceFoundNotTrue) {
        this.aliasDifferenceFoundNotTrue = aliasDifferenceFoundNotTrue;
    }

    public String getAliasDifferenceStackDiff() {
        return aliasDifferenceStackDiff;
    }

    public void setAliasDifferenceStackDiff(String aliasDifferenceStackDiff) {
        this.aliasDifferenceStackDiff = aliasDifferenceStackDiff;
    }

    public String getAliasInfoTestCount() {
        return aliasInfoTestCount;
    }

    public void setAliasInfoTestCount(String aliasInfoTestCount) {
        this.aliasInfoTestCount = aliasInfoTestCount;
    }

    public String getAliasInfoTestDone() {
        return aliasInfoTestDone;
    }

    public void setAliasInfoTestDone(String aliasInfoTestDone) {
        this.aliasInfoTestDone = aliasInfoTestDone;
    }

    public String getAliasInfoTestCurrentClass() {
        return aliasInfoTestCurrentClass;
    }

    public void setAliasInfoTestCurrentClass(String aliasInfoTestCurrentClass) {
        this.aliasInfoTestCurrentClass = aliasInfoTestCurrentClass;
    }

    public String getAliasInfoTestCurrentMethod() {
        return aliasInfoTestCurrentMethod;
    }

    public void setAliasInfoTestCurrentMethod(String aliasInfoTestCurrentMethod) {
        this.aliasInfoTestCurrentMethod = aliasInfoTestCurrentMethod;
    }

    public String getAliasInfoTestCurrentParams() {
        return aliasInfoTestCurrentParams;
    }

    public void setAliasInfoTestCurrentParams(String aliasInfoTestCurrentParams) {
        this.aliasInfoTestCurrentParams = aliasInfoTestCurrentParams;
    }

    public String getAliasConcurrentError() {
        return aliasConcurrentError;
    }

    public void setAliasConcurrentError(String _aliasConcurrentError) {
        aliasConcurrentError = _aliasConcurrentError;
    }

    public FileInfos getInfos() {
        return infos;
    }

    public void setInfos(FileInfos _infos) {
        infos = _infos;
    }

    static String tr(String _var, KeyWords _keyWords, StringMap<PrimitiveType> _primitiveTypes, AliasCore _coreNames, String... _args) {
        CustList<String> allKeysWords_ = _keyWords.allKeyWords().values();
        allKeysWords_.addAllElts(_primitiveTypes.getKeys());
        allKeysWords_.add(_coreNames.getAliasVoid());
        for (String p:_args) {
            allKeysWords_.add(p);
        }
        return ValidatorStandard.tr(allKeysWords_,_var);
    }
}
