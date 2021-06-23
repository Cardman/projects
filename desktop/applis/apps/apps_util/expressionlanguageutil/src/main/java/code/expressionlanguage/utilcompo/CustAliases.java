package code.expressionlanguage.utilcompo;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecEnumBlock;
import code.expressionlanguage.exec.blocks.ExecInnerElementBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.scripts.messages.gui.MessCdmBaseGr;
import code.sml.util.ResourcesMessagesUtil;
import code.threads.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

import java.util.Calendar;

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
    private static final String FILE_ROOTS = "FileRoots";
    private static final String THREAD_EXIT_HOOK = "ThreadExitHook";
    private static final String APPEND_TO_FILE = "AppendToFile";
    private static final String THREAD_CURRENT_TIME = "ThreadCurrentTime";
    private static final String THREAD_CURRENT_NANO_TIME = "ThreadCurrentNanoTime";
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
    private static final String ELT_DIFFERENCE = "EltDifference";
    private static final String INDEX = "Index";
    private static final String ASSERT_NOT = "AssertNot";
    private static final String ASSERT_NOT_SAME = "AssertNotSame";
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
    private static final String TABLE_STRING_OBJECT = "TableStringObject";
    private static final String TABLE_CONC_EMPTY = "TableConcEmpty";
    private static final String TABLE_CONC_SIZE = "TableConcSize";
    private static final String TABLE_CONC_KEYS = "TableConcKeys";
    private static final String TABLE_CONC_VALUES = "TableConcValues";
    private static final String TABLE_CONC_HAS_KEY = "TableConcHasKey";
    private static final String TABLE_CONC_HAS_VALUE = "TableConcHasValue";
    private static final String TABLE_CONC_PAIRS = "TableConcPairs";
    private static final String TABLE_CONC_PUT = "TableConcPut";
    private static final String TABLE_CONC_PUT_ABS = "TableConcPutAbs";
    private static final String TABLE_CONC_GET = "TableConcGet";
    private static final String TABLE_CONC_REMOVE = "TableConcRemove";
    private static final String TABLE_CONC_REPLACE = "TableConcReplace";
    private static final String TABLE_CONC_CLEAR = "TableConcClear";
    private static final String TABLE_CONC_PUT_ALL = "TableConcPutAll";
    private static final String ENTRY_STRING_OBJECT = "EntryStringObject";
    private static final String TABLE_ENTRY_KEY = "TableEntryKey";
    private static final String TABLE_ENTRY_VALUE = "TableEntryValue";
    private static final String ENTRY_BINARY = "EntryBinary";
    private static final String ENTRY_TEXT = "EntryText";
    private static final String ENTRY_NAME = "EntryName";
    private static final String ENTRY_VALUE = "EntryValue";
    private static final String ENTRY_TIME = "EntryTime";
    private static final String FILE_IS_ABSOLUTE = "FileIsAbsolute";
    private static final String FILE_READ_BIN = "FileReadBin";
    private static final String FILE_WRITE_BIN = "FileWriteBin";
    private static final String FILE_DELETE = "FileDelete";
    private static final String FILE_RENAME = "FileRename";
    private static final String FILE_DIR = "FileDir";
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
    private static final String ASSERT_ASSERT_FALSE = "AssertAssertFalse";
    private static final String INFO_TEST_CURRENT_METHOD = "InfoTestCurrentMethod";
    private static final String RESULT_TIME = "ResultTime";
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
    private static final String THREAD_EQ = "ThreadEq";
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
    private String aliasThreadCurrentNanoTime;
    private String aliasThreadExitHook;
    private String aliasCurrentThread;
    private String aliasStart;
    private String aliasThreadEq;
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
    private String aliasFileRoots;
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
    private String aliasTableStringObject;
    private String aliasTableConcEmpty;
    private String aliasTableConcSize;
    private String aliasTableConcKeys;
    private String aliasTableConcValues;
    private String aliasTableConcHasKey;
    private String aliasTableConcHasValue;
    private String aliasTableConcPairs;
    private String aliasTableConcPut;
    private String aliasTableConcPutAbs;
    private String aliasTableConcGet;
    private String aliasTableConcRemove;
    private String aliasTableConcReplace;
    private String aliasTableConcClear;
    private String aliasTableConcPutAll;
    private String aliasEntryStringObject;
    private String aliasTableEntryKey;
    private String aliasTableEntryValue;
    private String aliasEntryBinary;
    private String aliasEntryText;
    private String aliasEntryName;
    private String aliasEntryValue;
    private String aliasEntryTime;
    private String aliasFileIsAbsolute;
    private String aliasFileReadBin;
    private String aliasFileWriteBin;
    private String aliasFileDelete;
    private String aliasFileRename;
    private String aliasFileDir;
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
    private String aliasEltDifference;
    private String aliasIndex;
    private String aliasDifference;

    private String aliasTestException;
    private String aliasTestNullException;
    private String aliasExecutedTestAnnotations;
    private String aliasExecutedTestTest;
    private String aliasExecutedTestBefore;
    private String aliasExecutedTestAfter;
    private String aliasExecutedTestMethod;

    private String aliasResultSuccess;
    private String aliasResultTime;
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
    private String aliasAssertAssertNot;
    private String aliasAssertAssertTrue;
    private String aliasAssertAssertFalse;
    private String aliasAssertAssertNull;
    private String aliasAssertAssertNotNull;
    private String aliasAssertAssertSame;
    private String aliasAssertAssertNotSame;

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
    private final StringMap<String> properties = MessCdmBaseGr.ms();

    private final CustAliasParameters custAliasParameters = new CustAliasParameters();

    private FileInfos infos;
    private AbstractInterceptor interceptor;

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

    public static AbstractFunctionalInstance newFunctional(ExecFormattedRootBlock _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className);
        return new RunnableFunctionalInstance(_className.getFormatted(),_functional,fs_,_contextEl, _named);
    }

    public void buildOther(LgNamesContent _content) {
        CustList<CstFieldInfo> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasThread, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasStart, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        method_ = new StandardMethod(aliasCurrentThread, params_, aliasThread, false, MethodModifier.STATIC);
        methods_.add( method_);
        method_ = new StandardMethod(aliasThreadCurrentTime, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.STATIC);
        methods_.add( method_);
        method_ = new StandardMethod(aliasThreadCurrentNanoTime, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.STATIC);
        methods_.add( method_);
        method_ = new StandardMethod(aliasJoin, params_, _content.getNbAlias().getAliasBoolean(), false, MethodModifier.FINAL);
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
        params_ = new StringList(aliasThread,aliasThread);
        method_ = new StandardMethod(aliasThreadEq, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread0ThreadEq0(),custAliasParameters.getAliasThread0ThreadEq1()));
        methods_.add( method_);
        StandardConstructor ctor_;
        params_ = new StringList(aliasRunnable);
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasThread0Thread0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasThread, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
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
        fields_ = new CustList<CstFieldInfo>();
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
        fields_ = new CustList<CstFieldInfo>();
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
        fields_ = new CustList<CstFieldInfo>();
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
        fields_ = new CustList<CstFieldInfo>();
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
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryStringObject, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.ABSTRACT);
        method_ = new StandardMethod(aliasTableEntryKey, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableEntryValue, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableEntryValue, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasEntryStringObject0TableEntryValue0()));
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasEntryStringObject, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTableStringObject, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcKeys, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcKeys, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0ConcKeys0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcValues, params_, StringExpUtil.getPrettyArrayType(_content.getCoreNames().getAliasObject()), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTableConcGet, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0Get0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTableConcRemove, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0Remove0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTableConcHasKey, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0ConcHasKey0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcHasValue, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0ConcHasValue0()));
        methods_.add( method_);
        params_ = new StringList(aliasTableStringObject);
        method_ = new StandardMethod(aliasTableConcPutAll, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0PutAll0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcPut, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0Put0(),custAliasParameters.getAliasTableStringObject0Put1()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcPutAbs, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0PutAbs0(),custAliasParameters.getAliasTableStringObject0PutAbs1()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcReplace, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0Replace0(),custAliasParameters.getAliasTableStringObject0Replace1()));
        methods_.add( method_);

        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcClear, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcPairs, params_, StringExpUtil.getPrettyArrayType(aliasEntryStringObject), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcEmpty, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcSize, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTableStringObject, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryBinary, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasEntryName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryValue, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryTime, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasEntryTime, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasEntryBinary0EntryTime0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(), StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()));
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasEntryBinary0EntryBinary0(),custAliasParameters.getAliasEntryBinary0EntryBinary1()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasEntryBinary, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryText, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasEntryName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryValue, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryTime, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasEntryTime, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasEntryText0EntryTime0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(), _content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasEntryText0EntryText0(),custAliasParameters.getAliasEntryText0EntryText1()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasEntryText, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasFile, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.HYPER_ABSTRACT);
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
        params_ = new StringList();
        method_ = new StandardMethod(aliasFileDir, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileDir, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileDir0()));
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
        params_ = new StringList();
        method_ = new StandardMethod(aliasFileRoots, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC);
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
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasIllegalThreadStateException, fields_, constructors_, methods_, _content.getCoreNames().getAliasError(), StdClassModifier.ABSTRACT);
        _content.getStandards().addEntry(aliasIllegalThreadStateException, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasConcurrentError, fields_, constructors_, methods_, _content.getCoreNames().getAliasError(), StdClassModifier.ABSTRACT);
        _content.getStandards().addEntry(aliasConcurrentError, stdcl_);
    }
    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content, StringList _predefinedClasses, StringList _predefinedInterfacesInitOrder) {
        StringMap<String> stds_ = new StringMap<String>();
        String content_ = properties.getVal("resources_lg/threads/runnable.txt");
        StringMap<PrimitiveType> primitiveTypes_ = _content.getPrimTypes().getPrimitiveTypes();
        AliasCore coreNames_ = _content.getCoreNames();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        AliasPredefinedTypes predefTypes_ = _content.getPredefTypes();
        AliasCharSequenceType charSeq_ = _content.getCharSeq();
        AliasReflection reflect_ = _content.getReflect();
        AliasMathType mathRef_ = _content.getMathRef();
        AliasStackTraceElementType stackElt_ = _content.getStackElt();
        AliasNumberType nbAlias_ = _content.getNbAlias();
        String public_ = _keyWords.getKeyWordPublic();
        String private_ = _keyWords.getKeyWordPrivate();
        String interface_ = _keyWords.getKeyWordInterface();
        String int_ = primTypes_.getAliasPrimInteger();
        String boolean_ = primTypes_.getAliasPrimBoolean();
        String class_ = _keyWords.getKeyWordClass();
        String this_ = _keyWords.getKeyWordThis();
        String new_ = _keyWords.getKeyWordNew();
        String return_ = _keyWords.getKeyWordReturn();
        String iter_ = _keyWords.getKeyWordIter();
        String value_ = _keyWords.getKeyWordValue();
        String abstract_ = _keyWords.getKeyWordAbstract();
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{Runnable}", aliasRunnable);
        map_.put("{abstract}", abstract_);
        map_.put("{void}", coreNames_.getAliasVoid());
        map_.put("{run}", aliasRun);
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasRunnable);
        stds_.put(aliasRunnable, content_);
        _predefinedInterfacesInitOrder.add(aliasRunnable);
        content_ = properties.getVal("resources_lg/collections/list.txt");
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
        map_.put("{i}", tr("i", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasList0RemoveLi0(),custAliasParameters.getAliasList1AddLi0(),custAliasParameters.getAliasList1AddLi1()));
        map_.put("{p}", tr("p", _keyWords, primitiveTypes_, coreNames_));
        map_.put("{out}", tr("out", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasList0RemoveLi0(),custAliasParameters.getAliasList1AddLi0(),custAliasParameters.getAliasList1AddLi1()));
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
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasCustIterator);
        _predefinedClasses.add(aliasList);
        stds_.put(aliasList, content_);
        _predefinedInterfacesInitOrder.add(aliasCustIterator);
        _predefinedInterfacesInitOrder.add(aliasList);
        content_ = properties.getVal("resources_lg/collections/table.txt");
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
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasCustPair);
        _predefinedClasses.add(aliasCustIterTable);
        _predefinedClasses.add(aliasTable);
        stds_.put(aliasTable, content_);
        _predefinedInterfacesInitOrder.add(aliasCustPair);
        _predefinedInterfacesInitOrder.add(aliasCustIterTable);
        _predefinedInterfacesInitOrder.add(aliasTable);

        content_ = properties.getVal("resources_lg/tests/run.txt");
        map_.put("{a}",tr("a", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{b}",tr("b", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{c}",tr("c", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
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
        map_.put("{a211}",custAliasParameters.getAliasAssert5AssertAssertDouble0());
        map_.put("{a212}",custAliasParameters.getAliasAssert5AssertAssertDouble1());
        map_.put("{a213}",custAliasParameters.getAliasAssert5AssertAssertDouble2());
        map_.put("{at11}",custAliasParameters.getAliasAssert0AssertAssertArr0());
        map_.put("{at12}",custAliasParameters.getAliasAssert0AssertAssertArr1());
        map_.put("{at21}",custAliasParameters.getAliasAssert1AssertAssertArr0());
        map_.put("{at22}",custAliasParameters.getAliasAssert1AssertAssertArr1());
        map_.put("{at31}",custAliasParameters.getAliasAssert2AssertAssertArr0());
        map_.put("{at32}",custAliasParameters.getAliasAssert2AssertAssertArr1());
        map_.put("{at41}",custAliasParameters.getAliasAssert3AssertAssertArr0());
        map_.put("{at42}",custAliasParameters.getAliasAssert3AssertAssertArr1());
        map_.put("{at51}",custAliasParameters.getAliasAssert4AssertAssertArr0());
        map_.put("{at52}",custAliasParameters.getAliasAssert4AssertAssertArr1());
        map_.put("{at211}",custAliasParameters.getAliasAssert5AssertAssertArrDouble0());
        map_.put("{at212}",custAliasParameters.getAliasAssert5AssertAssertArrDouble1());
        map_.put("{at213}",custAliasParameters.getAliasAssert5AssertAssertArrDouble2());
        map_.put("{an11}",custAliasParameters.getAliasAssert0AssertAssertNot0());
        map_.put("{an12}",custAliasParameters.getAliasAssert0AssertAssertNot1());
        map_.put("{an21}",custAliasParameters.getAliasAssert1AssertAssertNot0());
        map_.put("{an22}",custAliasParameters.getAliasAssert1AssertAssertNot1());
        map_.put("{an31}",custAliasParameters.getAliasAssert2AssertAssertNot0());
        map_.put("{an32}",custAliasParameters.getAliasAssert2AssertAssertNot1());
        map_.put("{an41}",custAliasParameters.getAliasAssert3AssertAssertNot0());
        map_.put("{an42}",custAliasParameters.getAliasAssert3AssertAssertNot1());
        map_.put("{an51}",custAliasParameters.getAliasAssert4AssertAssertNot0());
        map_.put("{an52}",custAliasParameters.getAliasAssert4AssertAssertNot1());
        map_.put("{an211}",custAliasParameters.getAliasAssert5AssertAssertNotDouble0());
        map_.put("{an212}",custAliasParameters.getAliasAssert5AssertAssertNotDouble1());
        map_.put("{an213}",custAliasParameters.getAliasAssert5AssertAssertNotDouble2());
        map_.put("{ant11}",custAliasParameters.getAliasAssert0AssertAssertNotArr0());
        map_.put("{ant12}",custAliasParameters.getAliasAssert0AssertAssertNotArr1());
        map_.put("{ant21}",custAliasParameters.getAliasAssert1AssertAssertNotArr0());
        map_.put("{ant22}",custAliasParameters.getAliasAssert1AssertAssertNotArr1());
        map_.put("{ant31}",custAliasParameters.getAliasAssert2AssertAssertNotArr0());
        map_.put("{ant32}",custAliasParameters.getAliasAssert2AssertAssertNotArr1());
        map_.put("{ant41}",custAliasParameters.getAliasAssert3AssertAssertNotArr0());
        map_.put("{ant42}",custAliasParameters.getAliasAssert3AssertAssertNotArr1());
        map_.put("{ant51}",custAliasParameters.getAliasAssert4AssertAssertNotArr0());
        map_.put("{ant52}",custAliasParameters.getAliasAssert4AssertAssertNotArr1());
        map_.put("{ant211}",custAliasParameters.getAliasAssert5AssertAssertNotArrDouble0());
        map_.put("{ant212}",custAliasParameters.getAliasAssert5AssertAssertNotArrDouble1());
        map_.put("{ant213}",custAliasParameters.getAliasAssert5AssertAssertNotArrDouble2());
        map_.put("{a6}",custAliasParameters.getAliasAssert0AssertAssertTrue0());
        map_.put("{a61}",custAliasParameters.getAliasAssert0AssertAssertFalse0());
        map_.put("{a7}",custAliasParameters.getAliasAssert0AssertAssertNull0());
        map_.put("{a8}",custAliasParameters.getAliasAssert0AssertAssertNotNull0());
        map_.put("{a91}",custAliasParameters.getAliasAssert0AssertAssertSame0());
        map_.put("{a92}",custAliasParameters.getAliasAssert0AssertAssertSame1());
        map_.put("{an91}",custAliasParameters.getAliasAssert0AssertAssertNotSame0());
        map_.put("{an92}",custAliasParameters.getAliasAssert0AssertAssertNotSame1());
        map_.put("{a101}",custAliasParameters.getAliasAssert5AssertAssert0());
        map_.put("{a102}",custAliasParameters.getAliasAssert5AssertAssert1());
        map_.put("{a103}",custAliasParameters.getAliasAssert5AssertAssert2());
        map_.put("{a111}",custAliasParameters.getAliasAssert6AssertAssert0());
        map_.put("{a112}",custAliasParameters.getAliasAssert6AssertAssert1());
        map_.put("{at101}",custAliasParameters.getAliasAssert5AssertAssertArr0());
        map_.put("{at102}",custAliasParameters.getAliasAssert5AssertAssertArr1());
        map_.put("{at103}",custAliasParameters.getAliasAssert5AssertAssertArr2());
        map_.put("{at111}",custAliasParameters.getAliasAssert6AssertAssertArr0());
        map_.put("{at112}",custAliasParameters.getAliasAssert6AssertAssertArr1());
        map_.put("{an101}",custAliasParameters.getAliasAssert5AssertAssertNot0());
        map_.put("{an102}",custAliasParameters.getAliasAssert5AssertAssertNot1());
        map_.put("{an103}",custAliasParameters.getAliasAssert5AssertAssertNot2());
        map_.put("{an111}",custAliasParameters.getAliasAssert6AssertAssertNot0());
        map_.put("{an112}",custAliasParameters.getAliasAssert6AssertAssertNot1());
        map_.put("{ant101}",custAliasParameters.getAliasAssert5AssertAssertNotArr0());
        map_.put("{ant102}",custAliasParameters.getAliasAssert5AssertAssertNotArr1());
        map_.put("{ant103}",custAliasParameters.getAliasAssert5AssertAssertNotArr2());
        map_.put("{ant111}",custAliasParameters.getAliasAssert6AssertAssertNotArr0());
        map_.put("{ant112}",custAliasParameters.getAliasAssert6AssertAssertNotArr1());
        map_.put("{d}",tr("d", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasAssert0AssertAssert0(),custAliasParameters.getAliasAssert0AssertAssert1(),
                custAliasParameters.getAliasAssert1AssertAssert0(),custAliasParameters.getAliasAssert1AssertAssert1(),
                custAliasParameters.getAliasAssert2AssertAssert0(),custAliasParameters.getAliasAssert2AssertAssert1(),
                custAliasParameters.getAliasAssert3AssertAssert0(),custAliasParameters.getAliasAssert3AssertAssert1(),
                custAliasParameters.getAliasAssert4AssertAssert0(),custAliasParameters.getAliasAssert4AssertAssert1(),
                custAliasParameters.getAliasAssert5AssertAssertDouble0(),custAliasParameters.getAliasAssert5AssertAssertDouble1(),custAliasParameters.getAliasAssert5AssertAssertDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertArr0(),custAliasParameters.getAliasAssert0AssertAssertArr1(),
                custAliasParameters.getAliasAssert1AssertAssertArr0(),custAliasParameters.getAliasAssert1AssertAssertArr1(),
                custAliasParameters.getAliasAssert2AssertAssertArr0(),custAliasParameters.getAliasAssert2AssertAssertArr1(),
                custAliasParameters.getAliasAssert3AssertAssertArr0(),custAliasParameters.getAliasAssert3AssertAssertArr1(),
                custAliasParameters.getAliasAssert4AssertAssertArr0(),custAliasParameters.getAliasAssert4AssertAssertArr1(),
                custAliasParameters.getAliasAssert5AssertAssertArrDouble0(),custAliasParameters.getAliasAssert5AssertAssertArrDouble1(),custAliasParameters.getAliasAssert5AssertAssertArrDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertNot0(),custAliasParameters.getAliasAssert0AssertAssertNot1(),
                custAliasParameters.getAliasAssert1AssertAssertNot0(),custAliasParameters.getAliasAssert1AssertAssertNot1(),
                custAliasParameters.getAliasAssert2AssertAssertNot0(),custAliasParameters.getAliasAssert2AssertAssertNot1(),
                custAliasParameters.getAliasAssert3AssertAssertNot0(),custAliasParameters.getAliasAssert3AssertAssertNot1(),
                custAliasParameters.getAliasAssert4AssertAssertNot0(),custAliasParameters.getAliasAssert4AssertAssertNot1(),
                custAliasParameters.getAliasAssert5AssertAssertNotDouble0(),custAliasParameters.getAliasAssert5AssertAssertNotDouble1(),custAliasParameters.getAliasAssert5AssertAssertNotDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertNotArr0(),custAliasParameters.getAliasAssert0AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert1AssertAssertNotArr0(),custAliasParameters.getAliasAssert1AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert2AssertAssertNotArr0(),custAliasParameters.getAliasAssert2AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert3AssertAssertNotArr0(),custAliasParameters.getAliasAssert3AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert4AssertAssertNotArr0(),custAliasParameters.getAliasAssert4AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert5AssertAssertNotArrDouble0(),custAliasParameters.getAliasAssert5AssertAssertNotArrDouble1(),custAliasParameters.getAliasAssert5AssertAssertNotArrDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertTrue0(),
                custAliasParameters.getAliasAssert0AssertAssertFalse0(),
                custAliasParameters.getAliasAssert0AssertAssertNull0(),
                custAliasParameters.getAliasAssert0AssertAssertNotNull0(),
                custAliasParameters.getAliasAssert0AssertAssertSame0(),custAliasParameters.getAliasAssert0AssertAssertSame1(),
                custAliasParameters.getAliasAssert0AssertAssertNotSame0(),custAliasParameters.getAliasAssert0AssertAssertNotSame1(),
                custAliasParameters.getAliasAssert5AssertAssert0(),custAliasParameters.getAliasAssert5AssertAssert1(),custAliasParameters.getAliasAssert5AssertAssert2(),
                custAliasParameters.getAliasAssert6AssertAssert0(),custAliasParameters.getAliasAssert6AssertAssert1(),
                custAliasParameters.getAliasAssert5AssertAssertArr0(),custAliasParameters.getAliasAssert5AssertAssertArr1(),custAliasParameters.getAliasAssert5AssertAssertArr2(),
                custAliasParameters.getAliasAssert6AssertAssertArr0(),custAliasParameters.getAliasAssert6AssertAssertArr1(),
                custAliasParameters.getAliasAssert5AssertAssertNot0(),custAliasParameters.getAliasAssert5AssertAssertNot1(),custAliasParameters.getAliasAssert5AssertAssertNot2(),
                custAliasParameters.getAliasAssert6AssertAssertNot0(),custAliasParameters.getAliasAssert6AssertAssertNot1(),
                custAliasParameters.getAliasAssert5AssertAssertNotArr0(),custAliasParameters.getAliasAssert5AssertAssertNotArr1(),custAliasParameters.getAliasAssert5AssertAssertNotArr2(),
                custAliasParameters.getAliasAssert6AssertAssertNotArr0(),custAliasParameters.getAliasAssert6AssertAssertNotArr1()));
        map_.put("{la}",tr("la", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasAssert0AssertAssert0(),custAliasParameters.getAliasAssert0AssertAssert1(),
                custAliasParameters.getAliasAssert1AssertAssert0(),custAliasParameters.getAliasAssert1AssertAssert1(),
                custAliasParameters.getAliasAssert2AssertAssert0(),custAliasParameters.getAliasAssert2AssertAssert1(),
                custAliasParameters.getAliasAssert3AssertAssert0(),custAliasParameters.getAliasAssert3AssertAssert1(),
                custAliasParameters.getAliasAssert4AssertAssert0(),custAliasParameters.getAliasAssert4AssertAssert1(),
                custAliasParameters.getAliasAssert5AssertAssertDouble0(),custAliasParameters.getAliasAssert5AssertAssertDouble1(),custAliasParameters.getAliasAssert5AssertAssertDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertArr0(),custAliasParameters.getAliasAssert0AssertAssertArr1(),
                custAliasParameters.getAliasAssert1AssertAssertArr0(),custAliasParameters.getAliasAssert1AssertAssertArr1(),
                custAliasParameters.getAliasAssert2AssertAssertArr0(),custAliasParameters.getAliasAssert2AssertAssertArr1(),
                custAliasParameters.getAliasAssert3AssertAssertArr0(),custAliasParameters.getAliasAssert3AssertAssertArr1(),
                custAliasParameters.getAliasAssert4AssertAssertArr0(),custAliasParameters.getAliasAssert4AssertAssertArr1(),
                custAliasParameters.getAliasAssert5AssertAssertArrDouble0(),custAliasParameters.getAliasAssert5AssertAssertArrDouble1(),custAliasParameters.getAliasAssert5AssertAssertArrDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertNot0(),custAliasParameters.getAliasAssert0AssertAssertNot1(),
                custAliasParameters.getAliasAssert1AssertAssertNot0(),custAliasParameters.getAliasAssert1AssertAssertNot1(),
                custAliasParameters.getAliasAssert2AssertAssertNot0(),custAliasParameters.getAliasAssert2AssertAssertNot1(),
                custAliasParameters.getAliasAssert3AssertAssertNot0(),custAliasParameters.getAliasAssert3AssertAssertNot1(),
                custAliasParameters.getAliasAssert4AssertAssertNot0(),custAliasParameters.getAliasAssert4AssertAssertNot1(),
                custAliasParameters.getAliasAssert5AssertAssertNotDouble0(),custAliasParameters.getAliasAssert5AssertAssertNotDouble1(),custAliasParameters.getAliasAssert5AssertAssertNotDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertNotArr0(),custAliasParameters.getAliasAssert0AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert1AssertAssertNotArr0(),custAliasParameters.getAliasAssert1AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert2AssertAssertNotArr0(),custAliasParameters.getAliasAssert2AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert3AssertAssertNotArr0(),custAliasParameters.getAliasAssert3AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert4AssertAssertNotArr0(),custAliasParameters.getAliasAssert4AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert5AssertAssertNotArrDouble0(),custAliasParameters.getAliasAssert5AssertAssertNotArrDouble1(),custAliasParameters.getAliasAssert5AssertAssertNotArrDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertTrue0(),
                custAliasParameters.getAliasAssert0AssertAssertFalse0(),
                custAliasParameters.getAliasAssert0AssertAssertNull0(),
                custAliasParameters.getAliasAssert0AssertAssertNotNull0(),
                custAliasParameters.getAliasAssert0AssertAssertSame0(),custAliasParameters.getAliasAssert0AssertAssertSame1(),
                custAliasParameters.getAliasAssert0AssertAssertNotSame0(),custAliasParameters.getAliasAssert0AssertAssertNotSame1(),
                custAliasParameters.getAliasAssert5AssertAssert0(),custAliasParameters.getAliasAssert5AssertAssert1(),custAliasParameters.getAliasAssert5AssertAssert2(),
                custAliasParameters.getAliasAssert6AssertAssert0(),custAliasParameters.getAliasAssert6AssertAssert1(),
                custAliasParameters.getAliasAssert5AssertAssertArr0(),custAliasParameters.getAliasAssert5AssertAssertArr1(),custAliasParameters.getAliasAssert5AssertAssertArr2(),
                custAliasParameters.getAliasAssert6AssertAssertArr0(),custAliasParameters.getAliasAssert6AssertAssertArr1(),
                custAliasParameters.getAliasAssert5AssertAssertNot0(),custAliasParameters.getAliasAssert5AssertAssertNot1(),custAliasParameters.getAliasAssert5AssertAssertNot2(),
                custAliasParameters.getAliasAssert6AssertAssertNot0(),custAliasParameters.getAliasAssert6AssertAssertNot1(),
                custAliasParameters.getAliasAssert5AssertAssertNotArr0(),custAliasParameters.getAliasAssert5AssertAssertNotArr1(),custAliasParameters.getAliasAssert5AssertAssertNotArr2(),
                custAliasParameters.getAliasAssert6AssertAssertNotArr0(),custAliasParameters.getAliasAssert6AssertAssertNotArr1()));
        map_.put("{ea}",tr("ea", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasAssert0AssertAssert0(),custAliasParameters.getAliasAssert0AssertAssert1(),
                custAliasParameters.getAliasAssert1AssertAssert0(),custAliasParameters.getAliasAssert1AssertAssert1(),
                custAliasParameters.getAliasAssert2AssertAssert0(),custAliasParameters.getAliasAssert2AssertAssert1(),
                custAliasParameters.getAliasAssert3AssertAssert0(),custAliasParameters.getAliasAssert3AssertAssert1(),
                custAliasParameters.getAliasAssert4AssertAssert0(),custAliasParameters.getAliasAssert4AssertAssert1(),
                custAliasParameters.getAliasAssert5AssertAssertDouble0(),custAliasParameters.getAliasAssert5AssertAssertDouble1(),custAliasParameters.getAliasAssert5AssertAssertDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertArr0(),custAliasParameters.getAliasAssert0AssertAssertArr1(),
                custAliasParameters.getAliasAssert1AssertAssertArr0(),custAliasParameters.getAliasAssert1AssertAssertArr1(),
                custAliasParameters.getAliasAssert2AssertAssertArr0(),custAliasParameters.getAliasAssert2AssertAssertArr1(),
                custAliasParameters.getAliasAssert3AssertAssertArr0(),custAliasParameters.getAliasAssert3AssertAssertArr1(),
                custAliasParameters.getAliasAssert4AssertAssertArr0(),custAliasParameters.getAliasAssert4AssertAssertArr1(),
                custAliasParameters.getAliasAssert5AssertAssertArrDouble0(),custAliasParameters.getAliasAssert5AssertAssertArrDouble1(),custAliasParameters.getAliasAssert5AssertAssertArrDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertNot0(),custAliasParameters.getAliasAssert0AssertAssertNot1(),
                custAliasParameters.getAliasAssert1AssertAssertNot0(),custAliasParameters.getAliasAssert1AssertAssertNot1(),
                custAliasParameters.getAliasAssert2AssertAssertNot0(),custAliasParameters.getAliasAssert2AssertAssertNot1(),
                custAliasParameters.getAliasAssert3AssertAssertNot0(),custAliasParameters.getAliasAssert3AssertAssertNot1(),
                custAliasParameters.getAliasAssert4AssertAssertNot0(),custAliasParameters.getAliasAssert4AssertAssertNot1(),
                custAliasParameters.getAliasAssert5AssertAssertNotDouble0(),custAliasParameters.getAliasAssert5AssertAssertNotDouble1(),custAliasParameters.getAliasAssert5AssertAssertNotDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertNotArr0(),custAliasParameters.getAliasAssert0AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert1AssertAssertNotArr0(),custAliasParameters.getAliasAssert1AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert2AssertAssertNotArr0(),custAliasParameters.getAliasAssert2AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert3AssertAssertNotArr0(),custAliasParameters.getAliasAssert3AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert4AssertAssertNotArr0(),custAliasParameters.getAliasAssert4AssertAssertNotArr1(),
                custAliasParameters.getAliasAssert5AssertAssertNotArrDouble0(),custAliasParameters.getAliasAssert5AssertAssertNotArrDouble1(),custAliasParameters.getAliasAssert5AssertAssertNotArrDouble2(),
                custAliasParameters.getAliasAssert0AssertAssertTrue0(),
                custAliasParameters.getAliasAssert0AssertAssertFalse0(),
                custAliasParameters.getAliasAssert0AssertAssertNull0(),
                custAliasParameters.getAliasAssert0AssertAssertNotNull0(),
                custAliasParameters.getAliasAssert0AssertAssertSame0(),custAliasParameters.getAliasAssert0AssertAssertSame1(),
                custAliasParameters.getAliasAssert0AssertAssertNotSame0(),custAliasParameters.getAliasAssert0AssertAssertNotSame1(),
                custAliasParameters.getAliasAssert5AssertAssert0(),custAliasParameters.getAliasAssert5AssertAssert1(),custAliasParameters.getAliasAssert5AssertAssert2(),
                custAliasParameters.getAliasAssert6AssertAssert0(),custAliasParameters.getAliasAssert6AssertAssert1(),
                custAliasParameters.getAliasAssert5AssertAssertArr0(),custAliasParameters.getAliasAssert5AssertAssertArr1(),custAliasParameters.getAliasAssert5AssertAssertArr2(),
                custAliasParameters.getAliasAssert6AssertAssertArr0(),custAliasParameters.getAliasAssert6AssertAssertArr1(),
                custAliasParameters.getAliasAssert5AssertAssertNot0(),custAliasParameters.getAliasAssert5AssertAssertNot1(),custAliasParameters.getAliasAssert5AssertAssertNot2(),
                custAliasParameters.getAliasAssert6AssertAssertNot0(),custAliasParameters.getAliasAssert6AssertAssertNot1(),
                custAliasParameters.getAliasAssert5AssertAssertNotArr0(),custAliasParameters.getAliasAssert5AssertAssertNotArr1(),custAliasParameters.getAliasAssert5AssertAssertNotArr2(),
                custAliasParameters.getAliasAssert6AssertAssertNotArr0(),custAliasParameters.getAliasAssert6AssertAssertNotArr1()));
        map_.put("{co}",custAliasParameters.getAliasExecute0ExecuteConvert0());
        map_.put("{e}",tr("e", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0(),custAliasParameters.getAliasExecute0ExecuteConvert0()));
        map_.put("{f}",tr("f", _keyWords, primitiveTypes_, coreNames_));
        map_.put("{i}",tr("i", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{l}",tr("l", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{m}",tr("m", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{o}",tr("o", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{r1}",custAliasParameters.getAliasExecute0Run0());
        map_.put("{r2}",custAliasParameters.getAliasExecute0Run1());
        map_.put("{r3}",custAliasParameters.getAliasExecute0Run2());
        map_.put("{p}",tr("p", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{s}",tr("s", _keyWords, primitiveTypes_, coreNames_));
        map_.put("{t}",tr("t", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{tt}",tr("tt", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0ExecuteSetupError0(),custAliasParameters.getAliasExecute0ExecuteSetupError1(),custAliasParameters.getAliasExecute0ExecuteSetupError2(),
                custAliasParameters.getAliasExecute0ExecuteSetupNoException0()));
        map_.put("{ex}",tr("ex", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0ExecuteSetupError0(),custAliasParameters.getAliasExecute0ExecuteSetupError1(),custAliasParameters.getAliasExecute0ExecuteSetupError2()));
        map_.put("{et2}",custAliasParameters.getAliasExecute0ExecuteSetupError0());
        map_.put("{et3}",custAliasParameters.getAliasExecute0ExecuteSetupError1());
        map_.put("{et4}",custAliasParameters.getAliasExecute0ExecuteSetupError2());
        map_.put("{et}",custAliasParameters.getAliasExecute1ExecuteSetupError0());
        map_.put("{er}",custAliasParameters.getAliasExecute1ExecuteSetupError1());
        map_.put("{et1}",custAliasParameters.getAliasExecute0ExecuteSetupNoException0());
        map_.put("{info}",custAliasParameters.getAliasExecute0ExecuteTests0());
        map_.put("{res}",tr("res", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0ExecuteSetupError0(),custAliasParameters.getAliasExecute0ExecuteSetupError1(),custAliasParameters.getAliasExecute0ExecuteSetupError2(),
                custAliasParameters.getAliasExecute1ExecuteSetupError0(),custAliasParameters.getAliasExecute1ExecuteSetupError1(),
                custAliasParameters.getAliasExecute0ExecuteSetupNoException0(),custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{out}",tr("out", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{processEx}",tr("processEx", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{ctor}",tr("ctor", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{classTest}",tr("classTest", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{results}",tr("results", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{exc}",tr("exc", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{nbParams}",tr("nbParams", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{params}",tr("params", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{arr}",tr("arr", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{as}",tr("as", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{bs}",tr("bs", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{tts}",tr("tts", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{locType}",tr("locType", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{loc}",tr("loc", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{paramAnn}",tr("paramAnn", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{aParam}",tr("aParam", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasExecute0ExecuteTests0()));
        map_.put("{final}", _keyWords.getKeyWordFinal());
        map_.put("{static}", _keyWords.getKeyWordStatic());
        map_.put("{for}", _keyWords.getKeyWordFor());
        map_.put("{if}", _keyWords.getKeyWordIf());
        map_.put("{else}", _keyWords.getKeyWordElse());
        map_.put("{var}", _keyWords.getKeyWordVar());
        map_.put("{annotation}", _keyWords.getKeyWordAnnotation());
        map_.put("{null}", _keyWords.getKeyWordNull());
        map_.put("{throw}", _keyWords.getKeyWordThrow());
        map_.put("{continue}", _keyWords.getKeyWordContinue());
        map_.put("{break}", _keyWords.getKeyWordBreak());
        map_.put("{instanceof}", _keyWords.getKeyWordInstanceof());
        map_.put("{true}", _keyWords.getKeyWordTrue());
        map_.put("{false}", _keyWords.getKeyWordFalse());
        map_.put("{try}", _keyWords.getKeyWordTry());
        map_.put("{catch}", _keyWords.getKeyWordCatch());
        map_.put("{cast}", _keyWords.getKeyWordCast());
        map_.put("{abstract}", abstract_);
        map_.put("{long}",primTypes_.getAliasPrimLong());
        map_.put("{double}",primTypes_.getAliasPrimDouble());
        map_.put("{toSpecString}", _keyWords.getKeyWordToString());
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
        map_.put("{valueOf}",coreNames_.getAliasStringUtilValueOf());
        map_.put("{append}",charSeq_.getAliasAppend());
        map_.put("{insert}",charSeq_.getAliasInsert());
        map_.put("{toString}",charSeq_.getAliasCharSequenceToString());
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
        map_.put("{assertNot}",aliasAssertAssertNot);
        map_.put("{assertTrue}",aliasAssertAssertTrue);
        map_.put("{assertFalse}",aliasAssertAssertFalse);
        map_.put("{assertNull}",aliasAssertAssertNull);
        map_.put("{assertNotNull}",aliasAssertAssertNotNull);
        map_.put("{assertSame}",aliasAssertAssertSame);
        map_.put("{assertNotSame}",aliasAssertAssertNotSame);
        map_.put("{EltDifference}",aliasEltDifference);
        map_.put("{super}",_keyWords.getKeyWordSuper());
        map_.put("{index}",aliasIndex);
        map_.put("{Double}",nbAlias_.getAliasDouble());
        map_.put("{abs}",mathRef_.getAliasAbs());
        map_.put("{compare}",nbAlias_.getAliasCompare());
        map_.put("{success}",aliasResultSuccess);
        map_.put("{time}",aliasResultTime);
        map_.put("{tests}",aliasExecuteTests);
        map_.put("{count}",aliasInfoTestCount);
        map_.put("{done}",aliasInfoTestDone);
        map_.put("{currentClass}",aliasInfoTestCurrentClass);
        map_.put("{currentMethod}",aliasInfoTestCurrentMethod);
        map_.put("{currentParams}",aliasInfoTestCurrentParams);
        map_.put("{stTime}",tr("stTime", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{instant}",aliasThreadCurrentNanoTime);
        map_.put("{diff}",tr("diff", _keyWords, primitiveTypes_, coreNames_,
                custAliasParameters.getAliasExecute0Run0(),custAliasParameters.getAliasExecute0Run1(),custAliasParameters.getAliasExecute0Run2()
        ));
        map_.put("{tps1}",custAliasParameters.getAliasExecute0ExecuteSetupNoException1());
        map_.put("{tps2}",custAliasParameters.getAliasExecute0ExecuteSetupError3());
        map_.put("{tps3}",custAliasParameters.getAliasExecute1ExecuteSetupError2());
        content_ = StringUtil.formatQuote(content_, map_);

        _predefinedClasses.add(aliasInfoTest);
        _predefinedClasses.add(aliasDifference);
        _predefinedClasses.add(aliasAssert);
        _predefinedClasses.add(aliasParameters);
        _predefinedClasses.add(aliasBefore);
        _predefinedClasses.add(aliasAfter);
        _predefinedClasses.add(aliasTest);
        _predefinedClasses.add(aliasExecutedTest);
        _predefinedClasses.add(aliasResult);
        _predefinedClasses.add(aliasExecute);
        stds_.put(aliasExecute, content_);
        _predefinedInterfacesInitOrder.add(aliasInfoTest);
        _predefinedInterfacesInitOrder.add(aliasDifference);
        _predefinedInterfacesInitOrder.add(aliasAssert);
        _predefinedInterfacesInitOrder.add(aliasParameters);
        _predefinedInterfacesInitOrder.add(aliasBefore);
        _predefinedInterfacesInitOrder.add(aliasAfter);
        _predefinedInterfacesInitOrder.add(aliasTest);
        _predefinedInterfacesInitOrder.add(aliasExecutedTest);
        _predefinedInterfacesInitOrder.add(aliasResult);
        _predefinedInterfacesInitOrder.add(aliasExecute);
        content_ = properties.getVal("resources_lg/threads/formatting.txt");
        map_.put("{Format}",aliasFormatType);
        map_.put("{int}", int_);
        map_.put("{fo1}", custAliasParameters.getAliasFormatType0Print0());
        map_.put("{fo2}", custAliasParameters.getAliasFormatType1Print0());
        map_.put("{fo3}", custAliasParameters.getAliasFormatType1Print1());
        map_.put("{as}", tr("as", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasFormatType1Print0(),custAliasParameters.getAliasFormatType1Print1()));
        map_.put("{e}", tr("e", _keyWords, primitiveTypes_, coreNames_,custAliasParameters.getAliasFormatType1Print0(),custAliasParameters.getAliasFormatType1Print1()));
        map_.put("{print}",getAliasPrint());
        map_.put("{format}",charSeq_.getAliasFormat());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasFormatType);
        stds_.put(aliasFormatType, content_);
        _predefinedInterfacesInitOrder.add(aliasFormatType);
        return stds_;
    }
    public void messages(AnalysisMessages _mess,String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"messages");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        messages(_mess,util_,_cust);
    }
    public void messages(AnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        _mess.build(_util, _cust);
    }
    public void keyWord(KeyWords _kw,String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"keywords");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        keyWord(_kw,util_,_cust);
    }
    public void keyWord(KeyWords _kw,StringMap<String> _util,StringMap<String> _cust) {
        _kw.build(_util, _cust);
    }
    public void otherAlias(LgNamesContent _content,String _lang, StringMap<String>_cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"types");
        String content_ = properties.getVal(fileName_);
        StringMap<String> util_ = ResourcesMessagesUtil.getMessagesFromContent(content_);
        otherAlias(_content,util_,_cust);
    }
    public void allAlias(LgNamesContent _content,StringMap<String> _util, StringMap<String> _cust) {
        otherAlias(_content,_util,_cust);
    }
    private void otherAlias(LgNamesContent _content,StringMap<String> _util, StringMap<String> _cust) {
        _content.build(_util, _cust);
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
        setAliasFileRoots(LgNamesContent.get(_util, _cust, FILE_ROOTS));
        setAliasThreadExitHook(LgNamesContent.get(_util, _cust, THREAD_EXIT_HOOK));
        setAliasAppendToFile(LgNamesContent.get(_util, _cust, APPEND_TO_FILE));
        setAliasThreadCurrentTime(LgNamesContent.get(_util, _cust, THREAD_CURRENT_TIME));
        setAliasThreadCurrentNanoTime(LgNamesContent.get(_util, _cust, THREAD_CURRENT_NANO_TIME));
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
        setAliasEltDifference(LgNamesContent.get(_util, _cust, ELT_DIFFERENCE));
        setAliasIndex(LgNamesContent.get(_util, _cust, INDEX));
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
        setAliasTableStringObject(LgNamesContent.get(_util, _cust, TABLE_STRING_OBJECT));
        setAliasTableConcKeys(LgNamesContent.get(_util, _cust, TABLE_CONC_KEYS));
        setAliasTableConcValues(LgNamesContent.get(_util, _cust, TABLE_CONC_VALUES));
        setAliasTableConcHasKey(LgNamesContent.get(_util, _cust, TABLE_CONC_HAS_KEY));
        setAliasTableConcHasValue(LgNamesContent.get(_util, _cust, TABLE_CONC_HAS_VALUE));
        setAliasTableConcGet(LgNamesContent.get(_util, _cust, TABLE_CONC_GET));
        setAliasTableConcClear(LgNamesContent.get(_util, _cust, TABLE_CONC_CLEAR));
        setAliasTableConcPutAll(LgNamesContent.get(_util, _cust, TABLE_CONC_PUT_ALL));
        setAliasTableConcPut(LgNamesContent.get(_util, _cust, TABLE_CONC_PUT));
        setAliasTableConcPutAbs(LgNamesContent.get(_util, _cust, TABLE_CONC_PUT_ABS));
        setAliasTableConcRemove(LgNamesContent.get(_util, _cust, TABLE_CONC_REMOVE));
        setAliasTableConcReplace(LgNamesContent.get(_util, _cust, TABLE_CONC_REPLACE));
        setAliasTableConcPairs(LgNamesContent.get(_util, _cust, TABLE_CONC_PAIRS));
        setAliasTableConcEmpty(LgNamesContent.get(_util, _cust, TABLE_CONC_EMPTY));
        setAliasTableConcSize(LgNamesContent.get(_util, _cust, TABLE_CONC_SIZE));
        setAliasEntryStringObject(LgNamesContent.get(_util, _cust, ENTRY_STRING_OBJECT));
        setAliasTableEntryKey(LgNamesContent.get(_util, _cust, TABLE_ENTRY_KEY));
        setAliasTableEntryValue(LgNamesContent.get(_util, _cust, TABLE_ENTRY_VALUE));
        setAliasEntryBinary(LgNamesContent.get(_util, _cust, ENTRY_BINARY));
        setAliasEntryText(LgNamesContent.get(_util, _cust, ENTRY_TEXT));
        setAliasEntryName(LgNamesContent.get(_util, _cust, ENTRY_NAME));
        setAliasEntryValue(LgNamesContent.get(_util, _cust, ENTRY_VALUE));
        setAliasEntryTime(LgNamesContent.get(_util, _cust, ENTRY_TIME));
        setAliasFileIsAbsolute(LgNamesContent.get(_util, _cust, FILE_IS_ABSOLUTE));
        setAliasFileReadBin(LgNamesContent.get(_util, _cust, FILE_READ_BIN));
        setAliasFileWriteBin(LgNamesContent.get(_util, _cust, FILE_WRITE_BIN));
        setAliasFileDelete(LgNamesContent.get(_util, _cust, FILE_DELETE));
        setAliasFileDir(LgNamesContent.get(_util, _cust, FILE_DIR));
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
        setAliasAssertAssertNot(LgNamesContent.get(_util, _cust, ASSERT_NOT));
        setAliasAssertAssertNotSame(LgNamesContent.get(_util, _cust, ASSERT_NOT_SAME));
        setAliasAssertAssertFalse(LgNamesContent.get(_util, _cust, ASSERT_ASSERT_FALSE));
        setAliasInfoTestCurrentMethod(LgNamesContent.get(_util, _cust, INFO_TEST_CURRENT_METHOD));
        setAliasResultParams(LgNamesContent.get(_util, _cust, RESULT_PARAMS));
        setAliasParametersMethod(LgNamesContent.get(_util, _cust, PARAMETERS_METHOD));
        setAliasExecuteSetupError(LgNamesContent.get(_util, _cust, EXECUTE_SETUP_ERROR));
        setAliasAssertAssertSame(LgNamesContent.get(_util, _cust, ASSERT_ASSERT_SAME));
        setAliasDifferenceFound(LgNamesContent.get(_util, _cust, DIFFERENCE_FOUND));
        setAliasDifferenceFoundNotTrue(LgNamesContent.get(_util, _cust, DIFFERENCE_FOUND_NOT_TRUE));
        setAliasParametersLocation(LgNamesContent.get(_util, _cust, PARAMETERS_LOCATION));
        setAliasInfoTestCount(LgNamesContent.get(_util, _cust, INFO_TEST_COUNT));
        setAliasResultTime(LgNamesContent.get(_util, _cust, RESULT_TIME));
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
        setAliasThreadEq(LgNamesContent.get(_util, _cust, THREAD_EQ));
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
        f_.addEntry(getAliasEltDifference(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(INDEX,getAliasIndex())
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
                new KeyValueMemberName(RESULT_TIME,getAliasResultTime()),
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
                new KeyValueMemberName(THREAD_EQ,getAliasThreadEq()),
                new KeyValueMemberName(THREAD_CURRENT_TIME,getAliasThreadCurrentTime()),
                new KeyValueMemberName(THREAD_CURRENT_NANO_TIME,getAliasThreadCurrentNanoTime()),
                new KeyValueMemberName(IS_ALIVE,getAliasIsAlive()),
                new KeyValueMemberName(IS_ENDED,getAliasIsEnded()),
                new KeyValueMemberName(END,getAliasEnd()),
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
                new KeyValueMemberName(FILE_ROOTS,getAliasFileRoots()),
                new KeyValueMemberName(FILE_IS_ABSOLUTE,getAliasFileIsAbsolute()),
                new KeyValueMemberName(FILE_READ_BIN,getAliasFileReadBin()),
                new KeyValueMemberName(FILE_WRITE_BIN,getAliasFileWriteBin()),
                new KeyValueMemberName(FILE_DELETE,getAliasFileDelete()),
                new KeyValueMemberName(FILE_DIR,getAliasFileDir()),
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
        m_.addEntry(getAliasTableStringObject(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TABLE_CONC_PAIRS,getAliasTableConcPairs()),
                new KeyValueMemberName(TABLE_CONC_EMPTY,getAliasTableConcEmpty()),
                new KeyValueMemberName(TABLE_CONC_SIZE,getAliasTableConcSize()),
                new KeyValueMemberName(TABLE_CONC_KEYS,getAliasTableConcKeys()),
                new KeyValueMemberName(TABLE_CONC_VALUES,getAliasTableConcValues()),
                new KeyValueMemberName(TABLE_CONC_HAS_KEY,getAliasTableConcHasKey()),
                new KeyValueMemberName(TABLE_CONC_HAS_VALUE,getAliasTableConcHasValue()),
                new KeyValueMemberName(TABLE_CONC_GET,getAliasTableConcGet()),
                new KeyValueMemberName(TABLE_CONC_REMOVE,getAliasTableConcRemove()),
                new KeyValueMemberName(TABLE_CONC_PUT,getAliasTableConcPut()),
                new KeyValueMemberName(TABLE_CONC_PUT_ABS,getAliasTableConcPutAbs()),
                new KeyValueMemberName(TABLE_CONC_REPLACE,getAliasTableConcReplace()),
                new KeyValueMemberName(TABLE_CONC_CLEAR,getAliasTableConcClear()),
                new KeyValueMemberName(TABLE_CONC_PUT_ALL,getAliasTableConcPutAll())));
        m_.addEntry(getAliasEntryStringObject(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TABLE_ENTRY_KEY,getAliasTableEntryKey()),
                new KeyValueMemberName(TABLE_ENTRY_VALUE,getAliasTableEntryValue())));
        m_.addEntry(getAliasEntryBinary(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENTRY_NAME,getAliasEntryName()),
                new KeyValueMemberName(ENTRY_TIME,getAliasEntryTime()),
                new KeyValueMemberName(ENTRY_VALUE,getAliasEntryValue())));
        m_.addEntry(getAliasEntryText(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENTRY_NAME,getAliasEntryName()),
                new KeyValueMemberName(ENTRY_TIME,getAliasEntryTime()),
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
                new KeyValueMemberName(ASSERT_NOT,getAliasAssertAssertNot()),
                new KeyValueMemberName(ASSERT_ASSERT_NOT_NULL,getAliasAssertAssertNotNull()),
                new KeyValueMemberName(ASSERT_ASSERT_NULL,getAliasAssertAssertNull()),
                new KeyValueMemberName(ASSERT_ASSERT_SAME,getAliasAssertAssertSame()),
                new KeyValueMemberName(ASSERT_NOT_SAME,getAliasAssertAssertNotSame()),
                new KeyValueMemberName(ASSERT_ASSERT_TRUE,getAliasAssertAssertTrue()),
                new KeyValueMemberName(ASSERT_ASSERT_FALSE,getAliasAssertAssertFalse())
        ));
        m_.addEntry(getAliasDifference(), new CustList<KeyValueMemberName>(
        ));
        m_.addEntry(getAliasEltDifference(), new CustList<KeyValueMemberName>(
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
        ref_.addEntry(ELT_DIFFERENCE,getAliasEltDifference());
        ref_.addEntry(CONCURRENT_ERROR,getAliasConcurrentError());
        ref_.addEntry(ENTRY_BINARY,getAliasEntryBinary());
        ref_.addEntry(ENTRY_TEXT,getAliasEntryText());
        ref_.addEntry(ENTRY_STRING_OBJECT,getAliasEntryStringObject());
        ref_.addEntry(TABLE_STRING_OBJECT,getAliasTableStringObject());
        return ref_;
    }
    public ResultErrorStd instance(ContextEl _cont, ConstructorId _method, StackCall _stackCall, Argument... _args) {
        return interceptor.instance(getAliasConcurrentError(),_cont,_method,_stackCall,_args);
    }
    public ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, StackCall _stackCall, Argument... _args) {
        return interceptor.invoke(getAliasConcurrentError(),_cont,_method,_struct,_exit,_stackCall,_args);
    }
    public Argument defaultInstance(ContextEl _cont, String _id, StackCall _stackCall) {
        if (StringUtil.quickEq(_id, _cont.getStandards().getContent().getCoreNames().getAliasObject())) {
            return new Argument(new SimpleObjectStruct());
        }
        if (StringUtil.quickEq(_id,aliasThread)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _id, _cont.getStandards().getContent().getCoreNames().getAliasIllegalType(), _stackCall)));
            return Argument.createVoid();
        }
        if (StringUtil.quickEq(_id,aliasThreadSet)) {
            ThreadSetStruct std_ = new ThreadSetStruct();
            return new Argument(std_);
        }
        if (StringUtil.quickEq(_id,aliasReentrantLock)) {
            AbstractLock re_ = LockFactory.newLock(getInfos().getThreadFactory().newAtomicBoolean());
            AbstractLockStruct std_ = new AbstractLockStruct(re_, aliasReentrantLock);
            return new Argument(std_);
        }
        if (StringUtil.quickEq(_id,aliasAtomicBoolean)) {
            AbstractAtomicBoolean at_ = getInfos().getThreadFactory().newAtomicBoolean();
            AtomicBooleanStruct std_ = new AtomicBooleanStruct(at_, aliasAtomicBoolean);
            return new Argument(std_);
        }
        if (StringUtil.quickEq(_id,aliasAtomicInteger)) {
            AbstractAtomicInteger at_ = getInfos().getThreadFactory().newAtomicInteger();
            AtomicIntegerStruct std_ = new AtomicIntegerStruct(at_, aliasAtomicInteger);
            return new Argument(std_);
        }
        if (StringUtil.quickEq(_id,aliasAtomicLong)) {
            AbstractAtomicLong at_ = getInfos().getThreadFactory().newAtomicLong();
            AtomicLongStruct std_ = new AtomicLongStruct(at_, aliasAtomicLong);
            return new Argument(std_);
        }
        return Argument.createVoid();
    }
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, StackCall _stackCall, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String name_ = _method.getName();
        if (StringUtil.quickEq(name_, _cont.getStandards().getContent().getCoreNames().getAliasObject())) {
            res_.setResult(new SimpleObjectStruct());
            return res_;
        }
        if (StringUtil.quickEq(name_,aliasThreadSet)) {
            ThreadSetStruct std_ = new ThreadSetStruct();
            res_.setResult(std_);
            return res_;
        }
        if (StringUtil.quickEq(name_,aliasThread)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            Struct runnable_ = _args[0];
            AbstractThread thread_;
            if (runnable_ instanceof Runnable) {
                thread_ = getInfos().getThreadFactory().newThread((Runnable) runnable_);
            } else {
                thread_ = getInfos().getThreadFactory().newThread(null);
            }
            ThreadStruct std_ = new ThreadStruct(thread_,getInfos().getThreadFactory().newAtomicBoolean());
            res_.setResult(std_);
            return res_;
        }
        if (StringUtil.quickEq(name_,aliasReentrantLock)) {
            if (_method.getParametersTypesLength() == 0) {
                AbstractLock re_ = LockFactory.newLock(getInfos().getThreadFactory().newAtomicBoolean());
                AbstractLockStruct std_ = new AbstractLockStruct(re_, aliasReentrantLock);
                res_.setResult(std_);
                return res_;
            }
            AbstractLock re_ = LockFactory.newLock(getInfos().getThreadFactory().newAtomicBoolean());
            re_.setSleep(((NumberStruct)_args[0]).longStruct());
            AbstractLockStruct std_ = new AbstractLockStruct(re_, aliasReentrantLock);
            res_.setResult(std_);
            return res_;
        }
        if (StringUtil.quickEq(name_,aliasAtomicBoolean)) {
            if (_method.getParametersTypesLength() == 0) {
                AbstractAtomicBoolean at_ = getInfos().getThreadFactory().newAtomicBoolean();
                AtomicBooleanStruct std_ = new AtomicBooleanStruct(at_, aliasAtomicBoolean);
                res_.setResult(std_);
                return res_;
            }
            AbstractAtomicBoolean at_ = getInfos().getThreadFactory().newAtomicBoolean(BooleanStruct.isTrue(_args[0]));
            AtomicBooleanStruct std_ = new AtomicBooleanStruct(at_, aliasAtomicBoolean);
            res_.setResult(std_);
            return res_;
        }
        if (StringUtil.quickEq(name_,aliasAtomicInteger)) {
            if (_method.getParametersTypesLength() == 0) {
                AbstractAtomicInteger at_ = getInfos().getThreadFactory().newAtomicInteger();
                AtomicIntegerStruct std_ = new AtomicIntegerStruct(at_, aliasAtomicInteger);
                res_.setResult(std_);
                return res_;
            }
            AbstractAtomicInteger at_ = getInfos().getThreadFactory().newAtomicInteger(((NumberStruct)_args[0]).intStruct());
            AtomicIntegerStruct std_ = new AtomicIntegerStruct(at_, aliasAtomicInteger);
            res_.setResult(std_);
            return res_;
        }
        if (StringUtil.quickEq(name_,aliasAtomicLong)) {
            if (_method.getParametersTypesLength() == 0) {
                AbstractAtomicLong at_ = getInfos().getThreadFactory().newAtomicLong();
                AtomicLongStruct std_ = new AtomicLongStruct(at_, aliasAtomicLong);
                res_.setResult(std_);
                return res_;
            }
            AbstractAtomicLong at_ = getInfos().getThreadFactory().newAtomicLong(((NumberStruct)_args[0]).longStruct());
            AtomicLongStruct std_ = new AtomicLongStruct(at_, aliasAtomicLong);
            res_.setResult(std_);
            return res_;
        }
        if (StringUtil.quickEq(name_,aliasEntryBinary)) {
            String cont_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimByte();
            cont_ = StringExpUtil.getPrettyArrayType(cont_);
            EntryBinaryStruct std_ = new EntryBinaryStruct(_args[0],_args[1],cont_);
            res_.setResult(std_);
            return res_;
        }
        if (StringUtil.quickEq(name_,aliasEntryText)) {
            EntryTextStruct std_ = new EntryTextStruct(_args[0],_args[1]);
            res_.setResult(std_);
            return res_;
        }
        if (StringUtil.quickEq(name_,aliasTableStringObject)) {
            res_.setResult(new StringMapStruct());
            return res_;
        }
        return res_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, ExecutingBlocks _execBlocks, StackCall _stackCall, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String className_ = _method.getClassName();
        String type_ = _method.getClassName();
        if (StringUtil.quickEq(type_, _cont.getStandards().getContent().getCoreNames().getAliasEnums())) {
            return ApplyCoreMethodUtil.getOtherResultBase(_cont, _method, _args, _stackCall);
        }
        if (StringUtil.quickEq(className_,aliasThreadSet)) {
            String name_ = _method.getConstraints().getName();
            if (StringUtil.quickEq(name_,aliasThreadSetAdd)) {
                if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                ins_.add(_args[0]);
                if (!(_args[0] instanceof ThreadStruct)) {
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
                } else {
                    res_.setResult(NullStruct.NULL_VALUE);
                }
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasThreadSetAll)) {
                if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont, _stackCall);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(((RunnableContextEl)_cont).getCustInit().getThreadSet());
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasThreadSetRemove)) {
                if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                ins_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasThreadSetContains)) {
                if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                res_.setResult(ins_.contains(_args[0]));
                return res_;
            }
            ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
            res_.setResult(ins_.toSnapshotArray(_cont, _stackCall));
            return res_;
        }
        if (StringUtil.quickEq(className_,aliasThread)) {
            String name_ = _method.getConstraints().getName();
            if (StringUtil.quickEq(name_,aliasPrint)) {
                if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont, _stackCall);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                log((RunnableContextEl)_cont, _method, _execBlocks, _stackCall, _args);
                ResultErrorStd out_ = new ResultErrorStd();
                out_.setResult(NullStruct.NULL_VALUE);
                return out_;
            }
            if (StringUtil.quickEq(name_,aliasStart)) {
                if (((ThreadStruct) _instance).start()) {
                    res_.setResult(NullStruct.NULL_VALUE);
                } else {
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, getAliasIllegalThreadStateException(), _stackCall)));
                }
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasSleep)) {
                if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont, _stackCall);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (!(_args[0] instanceof NumberStruct)) {
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
                    return res_;
                }
                res_.setResult(BooleanStruct.of(ThreadUtil.sleep(((RunnableContextEl) _cont).getCurrentThreadFactory(),((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasJoin)) {
                AbstractThread thread_ = ((ThreadStruct) _instance).getThread();
                ThState state_ = thread_.join();
                if (state_ == ThState.INTERRUPTED) {
                    res_.setResult(NullStruct.NULL_VALUE);
                } else {
                    res_.setResult(BooleanStruct.of(state_ == ThState.ALIVE));
                }
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasCurrentThread)) {
                if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont, _stackCall);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(((RunnableContextEl)_cont).getThread());
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasThreadExitHook)) {
                if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont, _stackCall);
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
            if (StringUtil.quickEq(name_,aliasJoinOthers)) {
                if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont, _stackCall);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                CustInitializer cust_ = ((RunnableContextEl)_cont).getCustInit();
                cust_.joinOthers((RunnableContextEl) _cont, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasIsAlive)) {
                AbstractThread thread_ = ((ThreadStruct) _instance).getThread();
                boolean alive_ = thread_.isAlive();
                res_.setResult(BooleanStruct.of(alive_));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasIsEnded)) {
                boolean alive_ = ((ThreadStruct) _instance).isEnded();
                res_.setResult(BooleanStruct.of(alive_));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasEnd)) {
                ((ThreadStruct) _instance).end();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetId)) {
                AbstractThread thread_ = ((ThreadStruct) _instance).getThread();
                res_.setResult(new LongStruct(thread_.getId()));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetPriority)) {
                AbstractThread thread_ = ((ThreadStruct) _instance).getThread();
                res_.setResult(new IntStruct(thread_.getPriority()));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasSetPriority)) {
                if (((ThreadStruct) _instance).setPriority(((NumberStruct)_args[0]).intStruct())) {
                    res_.setResult(NullStruct.NULL_VALUE);
                } else {
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                }
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasYield)) {
                if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont, _stackCall);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                Thread.yield();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasThreadEq)) {
                if (_args[0] instanceof ThreadStruct) {
                    if (_args[1] instanceof ThreadStruct) {
                        res_.setResult(BooleanStruct.of(((ThreadStruct)_args[0]).getThread().getThread() == ((ThreadStruct)_args[1]).getThread().getThread()));
                        return res_;
                    }
                    res_.setResult(BooleanStruct.of(false));
                    return res_;
                }
                res_.setResult(BooleanStruct.of(_args[0] == _args[1]));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasThreadCurrentNanoTime)) {
                res_.setResult(new LongStruct(((RunnableContextEl)_cont).getCurrentThreadFactory().nanos()));
                return res_;
            }
            res_.setResult(new LongStruct(((RunnableContextEl)_cont).getCurrentThreadFactory().millis()));
            return res_;
        }
        if (StringUtil.quickEq(className_,aliasReentrantLock)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            String name_ = _method.getConstraints().getName();
            if (StringUtil.quickEq(name_,aliasLock)) {
                AbstractLock re_ = ((AbstractLockStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.lock((RunnableContextEl)_cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasUnlock)) {
                AbstractLock re_ = ((AbstractLockStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.unlock((RunnableContextEl)_cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasIsHeldByCurrentThread)) {
                AbstractLock re_ = ((AbstractLockStruct) _instance).getInstance();
                boolean held_ = re_.heldLock((RunnableContextEl)_cont);
                res_.setResult(BooleanStruct.of(held_));
                return res_;
            }
        }
        if (StringUtil.quickEq(className_,aliasAtomicBoolean)) {
            String name_ = _method.getConstraints().getName();
            if (StringUtil.quickEq(name_,aliasGetAtomic)) {
                AbstractAtomicBoolean re_ = ((AtomicBooleanStruct) _instance).getInstance();
                boolean held_ = re_.get();
                res_.setResult(BooleanStruct.of(held_));
                return res_;
            }
            if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                _stackCall.getInitializingTypeInfos().failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasSetAtomic)) {
                AbstractAtomicBoolean re_ = ((AtomicBooleanStruct) _instance).getInstance();
                re_.set(BooleanStruct.isTrue(_args[0]));
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasCompareAndSetAtomic)) {
                AbstractAtomicBoolean re_ = ((AtomicBooleanStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(BooleanStruct.isTrue(_args[0]),BooleanStruct.isTrue(_args[1]))));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetAndSetAtomic)) {
                AbstractAtomicBoolean re_ = ((AtomicBooleanStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.getAndSet(BooleanStruct.isTrue(_args[0]))));
                return res_;
            }
            AbstractAtomicBoolean re_ = ((AtomicBooleanStruct) _instance).getInstance();
            re_.lazySet(BooleanStruct.isTrue(_args[0]));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(className_,aliasAtomicInteger)) {
            String name_ = _method.getConstraints().getName();
            if (StringUtil.quickEq(name_,aliasGetAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                int held_ = re_.get();
                res_.setResult(new IntStruct(held_));
                return res_;
            }
            if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                _stackCall.getInitializingTypeInfos().failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasSetAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                re_.set(((NumberStruct)_args[0]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasCompareAndSetAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct())));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetAndSetAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndSet(((NumberStruct)_args[0]).intStruct())));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetAndAddAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndAdd(((NumberStruct)_args[0]).intStruct())));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasAddAndGetAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.addAndGet(((NumberStruct)_args[0]).intStruct())));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetAndIncrementAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndIncrement()));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasIncrementAndGetAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.incrementAndGet()));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetAndDecrementAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.getAndDecrement()));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasDecrementAndGetAtomic)) {
                AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
                res_.setResult(new IntStruct(re_.decrementAndGet()));
                return res_;
            }
            AbstractAtomicInteger re_ = ((AtomicIntegerStruct) _instance).getInstance();
            re_.lazySet(((NumberStruct)_args[0]).intStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(className_,aliasAtomicLong)) {
            String name_ = _method.getConstraints().getName();
            if (StringUtil.quickEq(name_,aliasGetAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                long held_ = re_.get();
                res_.setResult(new LongStruct(held_));
                return res_;
            }
            if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                _stackCall.getInitializingTypeInfos().failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasSetAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                re_.set(((NumberStruct)_args[0]).longStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasCompareAndSetAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(((NumberStruct)_args[0]).longStruct(),((NumberStruct)_args[1]).longStruct())));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetAndSetAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndSet(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetAndAddAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndAdd(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasAddAndGetAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.addAndGet(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetAndIncrementAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndIncrement()));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasIncrementAndGetAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.incrementAndGet()));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasGetAndDecrementAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.getAndDecrement()));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasDecrementAndGetAtomic)) {
                AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
                res_.setResult(new LongStruct(re_.decrementAndGet()));
                return res_;
            }
            AbstractAtomicLong re_ = ((AtomicLongStruct) _instance).getInstance();
            re_.lazySet(((NumberStruct)_args[0]).longStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(className_,aliasEntryText)) {
            String name_ = _method.getConstraints().getName();
            EntryTextStruct inst_ = (EntryTextStruct) _instance;
            if (StringUtil.quickEq(name_,aliasEntryName)) {
                res_.setResult(inst_.getName());
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasEntryTime)) {
                if (_args.length == 0) {
                    res_.setResult(inst_.getLongTime());
                    return res_;
                }
                inst_.setLongTime(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringUtil.quickEq(className_,aliasEntryStringObject)) {
            String name_ = _method.getConstraints().getName();
            EntryMapStringStruct inst_ = (EntryMapStringStruct) _instance;
            if (StringUtil.quickEq(name_,aliasTableEntryKey)) {
                res_.setResult(inst_.key());
                return res_;
            }
            if (_args.length == 0) {
                res_.setResult(inst_.value(_stackCall));
                return res_;
            }
            res_.setResult(inst_.value(_cont,_stackCall,_args[0]));
            return res_;
        }
        if (StringUtil.quickEq(className_,aliasTableStringObject)) {
            String name_ = _method.getConstraints().getName();
            StringMapStruct inst_ = (StringMapStruct) _instance;
            if (StringUtil.quickEq(name_,aliasTableConcPairs)) {
                res_.setResult(inst_.toSnapshot(_cont,_stackCall));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcKeys)) {
                if (_args.length == 0) {
                    res_.setResult(inst_.toSnapshotKeys(_cont));
                    return res_;
                }
                res_.setResult(inst_.toSnapshotKeys(_cont,_stackCall,_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcValues)) {
                res_.setResult(inst_.toSnapshotValues(_cont,_stackCall));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcHasKey)) {
                res_.setResult(inst_.containsKey(_cont,_stackCall,_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcHasValue)) {
                res_.setResult(inst_.containsValue(_cont,_stackCall,_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcGet)) {
                res_.setResult(inst_.get(_cont,_stackCall,_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcEmpty)) {
                res_.setResult(inst_.isEmpty());
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcSize)) {
                res_.setResult(inst_.size());
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcRemove)) {
                res_.setResult(inst_.remove(_cont,_stackCall,_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcPut)) {
                res_.setResult(inst_.put(_cont,_stackCall,_args[0],_args[1]));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcPutAbs)) {
                res_.setResult(inst_.putIfAbs(_cont,_stackCall,_args[0],_args[1]));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcReplace)) {
                res_.setResult(inst_.replace(_cont,_stackCall,_args[0],_args[1]));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasTableConcPutAll)) {
                inst_.putAll(_cont,_stackCall,_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            inst_.clear(_stackCall);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(className_,aliasEntryBinary)) {
            String name_ = _method.getConstraints().getName();
            EntryBinaryStruct inst_ = (EntryBinaryStruct) _instance;
            if (StringUtil.quickEq(name_,aliasEntryName)) {
                res_.setResult(inst_.getName());
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasEntryTime)) {
                if (_args.length == 0) {
                    res_.setResult(inst_.getLongTime());
                    return res_;
                }
                inst_.setLongTime(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            ArrayStruct bin_ = inst_.getBinary();
            _stackCall.getInitializingTypeInfos().addSensibleField(inst_, bin_);
            res_.setResult(bin_);
            return res_;
        }
        if (StringUtil.quickEq(className_,aliasFile)) {
            String name_ = _method.getConstraints().getName();
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileZipBinArray)) {
                res_.setResult(ZipBinStructUtil.zipBinFiles(_args[0],(RunnableContextEl) _cont));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileZippedBinArray)) {
                res_.setResult(ZipBinStructUtil.zippedBinaryFilesByteArray(_args[0], (RunnableContextEl) _cont));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileRoots)) {
                StringList roots_ = infos.getFileSystem().getRoots((RunnableContextEl) _cont);
                int len_ = roots_.size();
                ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getCharSeq().getAliasString()));
                for (int i = 0; i < len_; i++) {
                    arr_.set(i, new StringStruct(roots_.get(i)));
                }
                res_.setResult(arr_);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileDir)) {
                if (_args.length == 0) {
                    res_.setResult(new StringStruct(infos.getFileSystem().currentDir((RunnableContextEl) _cont)));
                    return res_;
                }
                if (!(_args[0] instanceof StringStruct)) {
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
                    return res_;
                }
                infos.getFileSystem().changeDir(((StringStruct)_args[0]).getInstance(), (RunnableContextEl) _cont);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (!(_args[0] instanceof StringStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasRead)) {
                StringStruct str_ = (StringStruct)_args[0];
                String read_ = infos.getFileSystem().contentsOfFile(str_.getInstance(), (RunnableContextEl) _cont);
                if (read_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new StringStruct(read_));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasWrite)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                String txt_ = getStandarString(_cont,_args[1]);
                res_.setResult(BooleanStruct.of(infos.getFileSystem().saveTextFile(file_, txt_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileReadBin)) {
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
            if (StringUtil.quickEq(name_,aliasFileWriteBin)) {
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
            if (StringUtil.quickEq(name_,aliasFileDelete)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(BooleanStruct.of(infos.getFileSystem().delete(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileRename)) {
                if (!(_args[1] instanceof StringStruct)) {
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
                    return res_;
                }
                String file_ = ((StringStruct)_args[0]).getInstance();
                String dest_ = ((StringStruct)_args[1]).getInstance();
                res_.setResult(BooleanStruct.of(infos.getFileSystem().rename(file_,dest_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasAppendToFile)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                String txt_ = getStandarString(_cont,_args[1]);
                res_.setResult(BooleanStruct.of(infos.getFileSystem().logToFile(file_, txt_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileAbsolutePath)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new StringStruct(infos.getFileSystem().absolutePath(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileGetLength)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new LongStruct(infos.getFileSystem().length(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileGetName)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new StringStruct(infos.getFileSystem().getName(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileGetParentPath)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new StringStruct(infos.getFileSystem().getParentPath(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileIsDirectory)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(BooleanStruct.of(infos.getFileSystem().isDirectory(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileIsFile)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(BooleanStruct.of(infos.getFileSystem().isFile(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileLastModif)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new LongStruct(infos.getFileSystem().lastModified(file_, (RunnableContextEl) _cont)));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileListFiles)) {
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
            if (StringUtil.quickEq(name_,aliasFileListDirectories)) {
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
            if (StringUtil.quickEq(name_,aliasFileZipBin)) {
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
            if (StringUtil.quickEq(name_,aliasFileZipText)) {
                CustList<EntryBinaryStruct> bins_ = new CustList<EntryBinaryStruct>();
                if (_args[1] instanceof ArrayStruct) {
                    ArrayStruct arr_ = (ArrayStruct) _args[1];
                    for (Struct s: arr_.list()) {
                        if (s instanceof EntryTextStruct) {
                            EntryTextStruct cont_ = (EntryTextStruct)s;
                            byte[] encoded_ = StringUtil.encode(cont_.getText().getInstance());
                            String contType_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimByte();
                            contType_ = StringExpUtil.getPrettyArrayType(contType_);
                            int bLen_ = encoded_.length;
                            ArrayStruct bs_ = new ArrayStruct(bLen_,contType_);
                            for (int j = 0; j < bLen_; j++) {
                                bs_.set(j, new ByteStruct(encoded_[j]));
                            }
                            EntryBinaryStruct bin_ = new EntryBinaryStruct(cont_.getName(), bs_);
                            bin_.setLongTime(cont_.getLongTime());
                            bins_.add(bin_);
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
                byte[] finalFile_ = ZipBinStructUtil.getZipBinFileAsArray(bs_,infos.getZipFact());
                if (finalFile_ != null) {
                    StringStruct str_ = (StringStruct)_args[0];
                    res_.setResult(BooleanStruct.of(infos.getFileSystem().writeFile(str_.getInstance(),finalFile_, (RunnableContextEl) _cont)));
                    return res_;
                }
                res_.setResult(BooleanStruct.of(false));
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileZippedBin)) {
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
            if (StringUtil.quickEq(name_,aliasFileZippedText)) {
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
                        String dec_ = StringUtil.decode(prim_);
                        EntryTextStruct txt_;
                        if (dec_ == null) {
                            txt_ = new EntryTextStruct(fileBin_.getName(), NullStruct.NULL_VALUE);
                        } else {
                            txt_ = new EntryTextStruct(fileBin_.getName(), new StringStruct(dec_));
                        }
                        txt_.setLongTime(fileBin_.getLongTime());
                        filesOut_.set(i, txt_);
                    }
                    res_.setResult(filesOut_);
                    return res_;
                }
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasFileIsAbsolute)) {
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

    private void log(RunnableContextEl _cont,
                     ClassMethodId _method, ExecutingBlocks _execBlocks, StackCall _stackCall, Struct... _args) {
        if (_method.getConstraints().getParametersTypesLength() == 1) {
            String type_ = _method.getConstraints().getParametersType(0);
            String aliasObject_ = _cont.getStandards().getContent().getCoreNames().getAliasObject();
            if (StringUtil.quickEq(type_, aliasObject_)) {
                String className_ = aliasFormatType;
                Argument arg_ = new Argument(_args[0]);
                ArgumentListCall argList_ = new ArgumentListCall(new CustList<Argument>(arg_));
                ExecTypeFunction formatObjectPair_ = _execBlocks.getFormatObjectPair();
                ExecTemplates.wrapAndCall(formatObjectPair_, new ExecFormattedRootBlock(formatObjectPair_.getType(), className_),Argument.createVoid(), _cont, _stackCall, argList_);
                return;
            }
        }
        if (_method.getConstraints().getParametersTypesLength() == 2) {
            String className_ = aliasFormatType;
            Argument arg_ = new Argument(_args[0]);
            Argument argArr_ = new Argument(_args[1]);
            ArgumentListCall argList_ = new ArgumentListCall(new CustList<Argument>(arg_,argArr_));
            ExecTypeFunction formatObjectTwoPair_ = _execBlocks.getFormatObjectTwoPair();
            ExecTemplates.wrapAndCall(formatObjectTwoPair_, new ExecFormattedRootBlock(formatObjectTwoPair_.getType(),className_),Argument.createVoid(), _cont, _stackCall, argList_);
            return;
        }
        String stringAppFile_ = buildLog(_cont, _args);
        stringAppFile_ = StringUtil.concat(getDateTimeText("_", "_", "_"),":",stringAppFile_);
        log(stringAppFile_,_cont);
    }
    void log(String _content, RunnableContextEl _cont){
        ExecutingOptions executingOptions_ = _cont.getExecutingOptions();
        String folder_ = executingOptions_.getLogFolder();
        CustInitializer cust_ = _cont.getCustInit();
        String dtPart_ = cust_.getCurrentFileThread(_cont);
        infos.getLogger().log(executingOptions_.getOutput()+folder_,dtPart_,_content+"\n",_cont);
    }
    private static String buildLog(ContextEl _cont,
                                   Struct... _args) {
        return getStandarString(_cont,_args[0]);
    }
    private static String getStandarString(ContextEl _cont, Struct _struct) {
        if (_struct instanceof DisplayableStruct) {
            return ((DisplayableStruct)_struct).getDisplayedString(_cont).getInstance();
        }
        return StringUtil.concat(_cont.getStandards().getStringOfObject(_cont,_struct).getInstance(),"...");
    }
    public void processFailInit(ContextEl _cont, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isInitEnums()) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
        } else {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, aliasConcurrentError, _stackCall)));
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
        return StringUtil.concat(Long.toString(y_),_separatorDate,strMonth_,
                _separatorDate,strDay_,_sep,strHour_,
                _separatorTime,strMinute_,_separatorTime,strSecond_,_separatorTime,strMs_);
    }
    private static String lpadZero(int _nb) {
        if (_nb < 10) {
            return StringUtil.concat("0",Long.toString(_nb));
        }
        return Long.toString(_nb);
    }
    private static String lpadZeroMillis(int _millis) {
        if (_millis < 10) {
            return StringUtil.concat("00",Long.toString(_millis));
        }
        if (_millis < 100) {
            return StringUtil.concat("0",Long.toString(_millis));
        }
        return Long.toString(_millis);
    }

    public CustList<CommentDelimiters> defComments() {
        String content_ = properties.getVal("resources_lg/aliases/comments.properties");
        content_ = content_.substring(content_.indexOf('=')+1);
        return ParseLinesArgUtil.buildComments(content_);
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

    public void setAliasThreadExitHook(String _aliasThreadExitHook) {
        this.aliasThreadExitHook = _aliasThreadExitHook;
    }

    public String getAliasThreadCurrentTime() {
        return aliasThreadCurrentTime;
    }

    public void setAliasThreadCurrentTime(String _aliasThreadCurrentTime) {
        aliasThreadCurrentTime = _aliasThreadCurrentTime;
    }

    public String getAliasThreadCurrentNanoTime() {
        return aliasThreadCurrentNanoTime;
    }

    public void setAliasThreadCurrentNanoTime(String _aliasThreadCurrentNanoTime) {
        this.aliasThreadCurrentNanoTime = _aliasThreadCurrentNanoTime;
    }

    public String getAliasStart() {
        return aliasStart;
    }
    public void setAliasStart(String _aliasStart) {
        aliasStart = _aliasStart;
    }

    public String getAliasThreadEq() {
        return aliasThreadEq;
    }

    public void setAliasThreadEq(String _aliasThreadEq) {
        this.aliasThreadEq = _aliasThreadEq;
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

    public void setAliasIsEnded(String _aliasIsEnded) {
        this.aliasIsEnded = _aliasIsEnded;
    }

    public String getAliasEnd() {
        return aliasEnd;
    }

    public void setAliasEnd(String _aliasEnd) {
        this.aliasEnd = _aliasEnd;
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

    public void setAliasCompareAndSetAtomic(String _aliasCompareAndSetAtomic) {
        this.aliasCompareAndSetAtomic = _aliasCompareAndSetAtomic;
    }

    public String getAliasLazySetAtomic() {
        return aliasLazySetAtomic;
    }

    public void setAliasLazySetAtomic(String _aliasLazySetAtomic) {
        this.aliasLazySetAtomic = _aliasLazySetAtomic;
    }

    public String getAliasAddAndGetAtomic() {
        return aliasAddAndGetAtomic;
    }

    public void setAliasAddAndGetAtomic(String _aliasAddAndGetAtomic) {
        this.aliasAddAndGetAtomic = _aliasAddAndGetAtomic;
    }

    public String getAliasGetAndAddAtomic() {
        return aliasGetAndAddAtomic;
    }

    public void setAliasGetAndAddAtomic(String _aliasGetAndAddAtomic) {
        this.aliasGetAndAddAtomic = _aliasGetAndAddAtomic;
    }

    public String getAliasGetAndSetAtomic() {
        return aliasGetAndSetAtomic;
    }

    public void setAliasGetAndSetAtomic(String _aliasGetAndSetAtomic) {
        this.aliasGetAndSetAtomic = _aliasGetAndSetAtomic;
    }

    public String getAliasIncrementAndGetAtomic() {
        return aliasIncrementAndGetAtomic;
    }

    public void setAliasIncrementAndGetAtomic(String _aliasIncrementAndGetAtomic) {
        this.aliasIncrementAndGetAtomic = _aliasIncrementAndGetAtomic;
    }

    public String getAliasGetAndIncrementAtomic() {
        return aliasGetAndIncrementAtomic;
    }

    public void setAliasGetAndIncrementAtomic(String _aliasGetAndIncrementAtomic) {
        this.aliasGetAndIncrementAtomic = _aliasGetAndIncrementAtomic;
    }

    public String getAliasDecrementAndGetAtomic() {
        return aliasDecrementAndGetAtomic;
    }

    public void setAliasDecrementAndGetAtomic(String _aliasDecrementAndGetAtomic) {
        this.aliasDecrementAndGetAtomic = _aliasDecrementAndGetAtomic;
    }

    public String getAliasGetAndDecrementAtomic() {
        return aliasGetAndDecrementAtomic;
    }

    public void setAliasGetAndDecrementAtomic(String _aliasGetAndDecrementAtomic) {
        this.aliasGetAndDecrementAtomic = _aliasGetAndDecrementAtomic;
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

    public void setAliasFileIsDirectory(String _aliasFileIsDirectory) {
        this.aliasFileIsDirectory = _aliasFileIsDirectory;
    }

    public String getAliasFileIsFile() {
        return aliasFileIsFile;
    }

    public void setAliasFileIsFile(String _aliasFileIsFile) {
        this.aliasFileIsFile = _aliasFileIsFile;
    }

    public String getAliasFileRoots() {
        return aliasFileRoots;
    }

    public void setAliasFileRoots(String _aliasFileRoots) {
        this.aliasFileRoots = _aliasFileRoots;
    }

    public String getAliasFileGetParentPath() {
        return aliasFileGetParentPath;
    }

    public void setAliasFileGetParentPath(String _aliasFileGetParentPath) {
        this.aliasFileGetParentPath = _aliasFileGetParentPath;
    }

    public String getAliasFileGetName() {
        return aliasFileGetName;
    }

    public void setAliasFileGetName(String _aliasFileGetName) {
        this.aliasFileGetName = _aliasFileGetName;
    }

    public String getAliasFileGetLength() {
        return aliasFileGetLength;
    }

    public void setAliasFileGetLength(String _aliasFileGetLength) {
        this.aliasFileGetLength = _aliasFileGetLength;
    }

    public String getAliasFileAbsolutePath() {
        return aliasFileAbsolutePath;
    }

    public void setAliasFileAbsolutePath(String _aliasFileAbsolutePath) {
        this.aliasFileAbsolutePath = _aliasFileAbsolutePath;
    }

    public String getAliasFileLastModif() {
        return aliasFileLastModif;
    }

    public void setAliasFileLastModif(String _aliasFileLastModif) {
        this.aliasFileLastModif = _aliasFileLastModif;
    }

    public String getAliasFileListFiles() {
        return aliasFileListFiles;
    }

    public void setAliasFileListFiles(String _aliasFileListFiles) {
        this.aliasFileListFiles = _aliasFileListFiles;
    }

    public String getAliasFileListDirectories() {
        return aliasFileListDirectories;
    }

    public void setAliasFileListDirectories(String _aliasFileListDirectories) {
        this.aliasFileListDirectories = _aliasFileListDirectories;
    }

    public String getAliasFileZippedBin() {
        return aliasFileZippedBin;
    }

    public void setAliasFileZippedBin(String _aliasFileZippedBin) {
        this.aliasFileZippedBin = _aliasFileZippedBin;
    }

    public String getAliasFileZippedBinArray() {
        return aliasFileZippedBinArray;
    }

    public void setAliasFileZippedBinArray(String _aliasFileZippedBinArray) {
        this.aliasFileZippedBinArray = _aliasFileZippedBinArray;
    }

    public String getAliasFileZippedText() {
        return aliasFileZippedText;
    }

    public void setAliasFileZippedText(String _aliasFileZippedText) {
        this.aliasFileZippedText = _aliasFileZippedText;
    }

    public String getAliasFileZipBin() {
        return aliasFileZipBin;
    }

    public void setAliasFileZipBin(String _aliasFileZipBin) {
        this.aliasFileZipBin = _aliasFileZipBin;
    }

    public String getAliasFileZipBinArray() {
        return aliasFileZipBinArray;
    }

    public void setAliasFileZipBinArray(String _aliasFileZipBinArray) {
        this.aliasFileZipBinArray = _aliasFileZipBinArray;
    }

    public String getAliasFileZipText() {
        return aliasFileZipText;
    }

    public void setAliasFileZipText(String _aliasFileZipText) {
        this.aliasFileZipText = _aliasFileZipText;
    }

    public String getAliasTableStringObject() {
        return aliasTableStringObject;
    }

    public void setAliasTableStringObject(String _aliasTableStringObject) {
        this.aliasTableStringObject = _aliasTableStringObject;
    }

    public String getAliasEntryStringObject() {
        return aliasEntryStringObject;
    }

    public void setAliasEntryStringObject(String _aliasEntryStringObject) {
        this.aliasEntryStringObject = _aliasEntryStringObject;
    }

    public String getAliasTableConcEmpty() {
        return aliasTableConcEmpty;
    }

    public void setAliasTableConcEmpty(String _aliasTableConcEmpty) {
        this.aliasTableConcEmpty = _aliasTableConcEmpty;
    }

    public String getAliasTableConcSize() {
        return aliasTableConcSize;
    }

    public void setAliasTableConcSize(String _aliasTableConcSize) {
        this.aliasTableConcSize = _aliasTableConcSize;
    }

    public String getAliasTableConcKeys() {
        return aliasTableConcKeys;
    }

    public void setAliasTableConcKeys(String _aliasTableConcKeys) {
        this.aliasTableConcKeys = _aliasTableConcKeys;
    }

    public String getAliasTableConcValues() {
        return aliasTableConcValues;
    }

    public void setAliasTableConcValues(String _aliasTableConcValues) {
        this.aliasTableConcValues = _aliasTableConcValues;
    }

    public String getAliasTableConcHasKey() {
        return aliasTableConcHasKey;
    }

    public void setAliasTableConcHasKey(String _aliasTableConcHasKey) {
        this.aliasTableConcHasKey = _aliasTableConcHasKey;
    }

    public String getAliasTableConcHasValue() {
        return aliasTableConcHasValue;
    }

    public void setAliasTableConcHasValue(String _aliasTableConcHasValue) {
        this.aliasTableConcHasValue = _aliasTableConcHasValue;
    }

    public String getAliasTableConcPairs() {
        return aliasTableConcPairs;
    }

    public void setAliasTableConcPairs(String _aliasTableConcPairs) {
        this.aliasTableConcPairs = _aliasTableConcPairs;
    }

    public String getAliasTableConcPut() {
        return aliasTableConcPut;
    }

    public void setAliasTableConcPut(String _aliasTableConcPut) {
        this.aliasTableConcPut = _aliasTableConcPut;
    }

    public String getAliasTableConcPutAbs() {
        return aliasTableConcPutAbs;
    }

    public void setAliasTableConcPutAbs(String _aliasTableConcPutAbs) {
        this.aliasTableConcPutAbs = _aliasTableConcPutAbs;
    }

    public String getAliasTableConcGet() {
        return aliasTableConcGet;
    }

    public void setAliasTableConcGet(String _aliasTableConcGet) {
        this.aliasTableConcGet = _aliasTableConcGet;
    }

    public String getAliasTableConcRemove() {
        return aliasTableConcRemove;
    }

    public void setAliasTableConcRemove(String _aliasTableConcRemove) {
        this.aliasTableConcRemove = _aliasTableConcRemove;
    }

    public String getAliasTableConcReplace() {
        return aliasTableConcReplace;
    }

    public void setAliasTableConcReplace(String _aliasTableConcReplace) {
        this.aliasTableConcReplace = _aliasTableConcReplace;
    }

    public String getAliasTableConcClear() {
        return aliasTableConcClear;
    }

    public void setAliasTableConcClear(String _aliasTableConcClear) {
        this.aliasTableConcClear = _aliasTableConcClear;
    }

    public String getAliasTableConcPutAll() {
        return aliasTableConcPutAll;
    }

    public void setAliasTableConcPutAll(String _aliasTableConcPutAll) {
        this.aliasTableConcPutAll = _aliasTableConcPutAll;
    }

    public String getAliasTableEntryKey() {
        return aliasTableEntryKey;
    }

    public void setAliasTableEntryKey(String _aliasTableEntryKey) {
        this.aliasTableEntryKey = _aliasTableEntryKey;
    }

    public String getAliasTableEntryValue() {
        return aliasTableEntryValue;
    }

    public void setAliasTableEntryValue(String _aliasTableEntryValue) {
        this.aliasTableEntryValue = _aliasTableEntryValue;
    }

    public String getAliasEntryBinary() {
        return aliasEntryBinary;
    }

    public void setAliasEntryBinary(String _aliasEntryBinary) {
        this.aliasEntryBinary = _aliasEntryBinary;
    }

    public String getAliasEntryText() {
        return aliasEntryText;
    }

    public void setAliasEntryText(String _aliasEntryText) {
        this.aliasEntryText = _aliasEntryText;
    }

    public String getAliasEntryName() {
        return aliasEntryName;
    }

    public void setAliasEntryName(String _aliasEntryName) {
        this.aliasEntryName = _aliasEntryName;
    }

    public String getAliasEntryTime() {
        return aliasEntryTime;
    }

    public void setAliasEntryTime(String _aliasEntryTime) {
        this.aliasEntryTime = _aliasEntryTime;
    }

    public String getAliasEntryValue() {
        return aliasEntryValue;
    }

    public void setAliasEntryValue(String _aliasEntryValue) {
        this.aliasEntryValue = _aliasEntryValue;
    }

    public String getAliasFileIsAbsolute() {
        return aliasFileIsAbsolute;
    }

    public void setAliasFileIsAbsolute(String _aliasFileIsAbsolute) {
        this.aliasFileIsAbsolute = _aliasFileIsAbsolute;
    }

    public String getAliasFileReadBin() {
        return aliasFileReadBin;
    }

    public void setAliasFileReadBin(String _aliasFileReadBin) {
        this.aliasFileReadBin = _aliasFileReadBin;
    }

    public String getAliasFileWriteBin() {
        return aliasFileWriteBin;
    }

    public void setAliasFileWriteBin(String _aliasFileWriteBin) {
        this.aliasFileWriteBin = _aliasFileWriteBin;
    }

    public String getAliasFileDelete() {
        return aliasFileDelete;
    }

    public void setAliasFileDelete(String _aliasFileDelete) {
        this.aliasFileDelete = _aliasFileDelete;
    }

    public String getAliasFileDir() {
        return aliasFileDir;
    }

    public void setAliasFileDir(String _aliasFileDir) {
        this.aliasFileDir = _aliasFileDir;
    }

    public String getAliasFileRename() {
        return aliasFileRename;
    }

    public void setAliasFileRename(String _aliasFileRename) {
        this.aliasFileRename = _aliasFileRename;
    }

    public String getAliasFileMakeDirs() {
        return aliasFileMakeDirs;
    }

    public void setAliasFileMakeDirs(String _aliasFileMakeDirs) {
        this.aliasFileMakeDirs = _aliasFileMakeDirs;
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

    public void setAliasCustIterator(String _aliasCustIterator) {
        this.aliasCustIterator = _aliasCustIterator;
    }

    public String getAliasList() {
        return aliasList;
    }

    public void setAliasList(String _aliasList) {
        this.aliasList = _aliasList;
    }

    public String getAliasListItr() {
        return aliasListItr;
    }

    public void setAliasListItr(String _aliasListItr) {
        this.aliasListItr = _aliasListItr;
    }

    public String getAliasLengthItrLi() {
        return aliasLengthItrLi;
    }

    public void setAliasLengthItrLi(String _aliasLengthItrLi) {
        this.aliasLengthItrLi = _aliasLengthItrLi;
    }

    public String getAliasLengthLi() {
        return aliasLengthLi;
    }

    public void setAliasLengthLi(String _aliasLengthLi) {
        this.aliasLengthLi = _aliasLengthLi;
    }

    public String getAliasIndexItrLi() {
        return aliasIndexItrLi;
    }

    public void setAliasIndexItrLi(String _aliasIndexItrLi) {
        this.aliasIndexItrLi = _aliasIndexItrLi;
    }

    public String getAliasSizeLi() {
        return aliasSizeLi;
    }

    public void setAliasSizeLi(String _aliasSizeLi) {
        this.aliasSizeLi = _aliasSizeLi;
    }

    public String getAliasAddLi() {
        return aliasAddLi;
    }

    public void setAliasAddLi(String _aliasAddLi) {
        this.aliasAddLi = _aliasAddLi;
    }

    public String getAliasRemoveLi() {
        return aliasRemoveLi;
    }

    public void setAliasRemoveLi(String _aliasRemoveLi) {
        this.aliasRemoveLi = _aliasRemoveLi;
    }

    public String getAliasArrayLi() {
        return aliasArrayLi;
    }

    public void setAliasArrayLi(String _aliasArrayLi) {
        this.aliasArrayLi = _aliasArrayLi;
    }

    public String getAliasCustIteratorVar() {
        return aliasCustIteratorVar;
    }

    public void setAliasCustIteratorVar(String _aliasCustIteratorVar) {
        this.aliasCustIteratorVar = _aliasCustIteratorVar;
    }

    public String getAliasListVar() {
        return aliasListVar;
    }

    public void setAliasListVar(String _aliasListVar) {
        this.aliasListVar = _aliasListVar;
    }

    public String getAliasCustPair() {
        return aliasCustPair;
    }

    public void setAliasCustPair(String _aliasCustPair) {
        this.aliasCustPair = _aliasCustPair;
    }

    public String getAliasFirst() {
        return aliasFirst;
    }

    public void setAliasFirst(String _aliasFirst) {
        this.aliasFirst = _aliasFirst;
    }

    public String getAliasSecond() {
        return aliasSecond;
    }

    public void setAliasSecond(String _aliasSecond) {
        this.aliasSecond = _aliasSecond;
    }

    public String getAliasSetFirst() {
        return aliasSetFirst;
    }

    public void setAliasSetFirst(String _aliasSetFirst) {
        this.aliasSetFirst = _aliasSetFirst;
    }

    public String getAliasSetSecond() {
        return aliasSetSecond;
    }

    public void setAliasSetSecond(String _aliasSetSecond) {
        this.aliasSetSecond = _aliasSetSecond;
    }

    public String getAliasCustIterTable() {
        return aliasCustIterTable;
    }

    public void setAliasCustIterTable(String _aliasCustIterTable) {
        this.aliasCustIterTable = _aliasCustIterTable;
    }

    public String getAliasListIterTable() {
        return aliasListIterTable;
    }

    public void setAliasListIterTable(String _aliasListIterTable) {
        this.aliasListIterTable = _aliasListIterTable;
    }

    public String getAliasLengthItrTa() {
        return aliasLengthItrTa;
    }

    public void setAliasLengthItrTa(String _aliasLengthItrTa) {
        this.aliasLengthItrTa = _aliasLengthItrTa;
    }

    public String getAliasIndexItrTa() {
        return aliasIndexItrTa;
    }

    public void setAliasIndexItrTa(String _aliasIndexItrTa) {
        this.aliasIndexItrTa = _aliasIndexItrTa;
    }

    public String getAliasTable() {
        return aliasTable;
    }

    public void setAliasTable(String _aliasTable) {
        this.aliasTable = _aliasTable;
    }

    public String getAliasListTa() {
        return aliasListTa;
    }

    public void setAliasListTa(String _aliasListTa) {
        this.aliasListTa = _aliasListTa;
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

    public void setAliasAddTa(String _aliasAddTa) {
        this.aliasAddTa = _aliasAddTa;
    }

    public String getAliasGetTa() {
        return aliasGetTa;
    }

    public void setAliasGetTa(String _aliasGetTa) {
        this.aliasGetTa = _aliasGetTa;
    }

    public String getAliasSizeTa() {
        return aliasSizeTa;
    }

    public void setAliasSizeTa(String _aliasSizeTa) {
        this.aliasSizeTa = _aliasSizeTa;
    }

    public String getAliasGetFirstTa() {
        return aliasGetFirstTa;
    }

    public void setAliasGetFirstTa(String _aliasGetFirstTa) {
        this.aliasGetFirstTa = _aliasGetFirstTa;
    }

    public String getAliasGetSecondTa() {
        return aliasGetSecondTa;
    }

    public void setAliasGetSecondTa(String _aliasGetSecondTa) {
        this.aliasGetSecondTa = _aliasGetSecondTa;
    }

    public String getAliasSetFirstTa() {
        return aliasSetFirstTa;
    }

    public void setAliasSetFirstTa(String _aliasSetFirstTa) {
        this.aliasSetFirstTa = _aliasSetFirstTa;
    }

    public String getAliasSetSecondTa() {
        return aliasSetSecondTa;
    }

    public void setAliasSetSecondTa(String _aliasSetSecondTa) {
        this.aliasSetSecondTa = _aliasSetSecondTa;
    }

    public String getAliasRemoveTa() {
        return aliasRemoveTa;
    }

    public void setAliasRemoveTa(String _aliasRemoveTa) {
        this.aliasRemoveTa = _aliasRemoveTa;
    }

    public String getAliasPairVarFirst() {
        return aliasPairVarFirst;
    }

    public void setAliasPairVarFirst(String _aliasPairVarFirst) {
        this.aliasPairVarFirst = _aliasPairVarFirst;
    }

    public String getAliasPairVarSecond() {
        return aliasPairVarSecond;
    }

    public void setAliasPairVarSecond(String _aliasPairVarSecond) {
        this.aliasPairVarSecond = _aliasPairVarSecond;
    }

    public String getAliasIterTaVarFirst() {
        return aliasIterTaVarFirst;
    }

    public void setAliasIterTaVarFirst(String _aliasIterTaVarFirst) {
        this.aliasIterTaVarFirst = _aliasIterTaVarFirst;
    }

    public String getAliasIterTaVarSecond() {
        return aliasIterTaVarSecond;
    }

    public void setAliasIterTaVarSecond(String _aliasIterTaVarSecond) {
        this.aliasIterTaVarSecond = _aliasIterTaVarSecond;
    }

    public String getAliasTableVarFirst() {
        return aliasTableVarFirst;
    }

    public void setAliasTableVarFirst(String _aliasTableVarFirst) {
        this.aliasTableVarFirst = _aliasTableVarFirst;
    }

    public String getAliasTableVarSecond() {
        return aliasTableVarSecond;
    }

    public void setAliasTableVarSecond(String _aliasTableVarSecond) {
        this.aliasTableVarSecond = _aliasTableVarSecond;
    }

    public String getAliasExecute() {
        return aliasExecute;
    }

    public void setAliasExecute(String _aliasExecute) {
        this.aliasExecute = _aliasExecute;
    }

    public String getAliasResult() {
        return aliasResult;
    }

    public void setAliasResult(String _aliasResult) {
        this.aliasResult = _aliasResult;
    }

    public String getAliasExecutedTest() {
        return aliasExecutedTest;
    }

    public void setAliasExecutedTest(String _aliasExecutedTest) {
        this.aliasExecutedTest = _aliasExecutedTest;
    }

    public String getAliasTest() {
        return aliasTest;
    }

    public void setAliasTest(String _aliasTest) {
        this.aliasTest = _aliasTest;
    }

    public String getAliasAfter() {
        return aliasAfter;
    }

    public void setAliasAfter(String _aliasAfter) {
        this.aliasAfter = _aliasAfter;
    }

    public String getAliasBefore() {
        return aliasBefore;
    }

    public void setAliasBefore(String _aliasBefore) {
        this.aliasBefore = _aliasBefore;
    }

    public String getAliasParameters() {
        return aliasParameters;
    }

    public void setAliasParameters(String _aliasParameters) {
        this.aliasParameters = _aliasParameters;
    }

    public String getAliasAssert() {
        return aliasAssert;
    }

    public void setAliasAssert(String _aliasAssert) {
        this.aliasAssert = _aliasAssert;
    }

    public String getAliasDifference() {
        return aliasDifference;
    }

    public void setAliasDifference(String _aliasDifference) {
        this.aliasDifference = _aliasDifference;
    }

    public String getAliasEltDifference() {
        return aliasEltDifference;
    }

    public void setAliasEltDifference(String _aliasEltDifference) {
        this.aliasEltDifference = _aliasEltDifference;
    }

    public String getAliasIndex() {
        return aliasIndex;
    }

    public void setAliasIndex(String _aliasIndex) {
        this.aliasIndex = _aliasIndex;
    }

    public String getAliasInfoTest() {
        return aliasInfoTest;
    }

    public void setAliasInfoTest(String _aliasInfoTest) {
        this.aliasInfoTest = _aliasInfoTest;
    }

    public String getAliasTestException() {
        return aliasTestException;
    }

    public void setAliasTestException(String _aliasTestException) {
        this.aliasTestException = _aliasTestException;
    }

    public String getAliasTestNullException() {
        return aliasTestNullException;
    }

    public void setAliasTestNullException(String _aliasTestNullException) {
        this.aliasTestNullException = _aliasTestNullException;
    }

    public String getAliasExecutedTestAnnotations() {
        return aliasExecutedTestAnnotations;
    }

    public void setAliasExecutedTestAnnotations(String _aliasExecutedTestAnnotations) {
        this.aliasExecutedTestAnnotations = _aliasExecutedTestAnnotations;
    }

    public String getAliasExecutedTestTest() {
        return aliasExecutedTestTest;
    }

    public void setAliasExecutedTestTest(String _aliasExecutedTestTest) {
        this.aliasExecutedTestTest = _aliasExecutedTestTest;
    }

    public String getAliasExecutedTestBefore() {
        return aliasExecutedTestBefore;
    }

    public void setAliasExecutedTestBefore(String _aliasExecutedTestBefore) {
        this.aliasExecutedTestBefore = _aliasExecutedTestBefore;
    }

    public String getAliasExecutedTestAfter() {
        return aliasExecutedTestAfter;
    }

    public void setAliasExecutedTestAfter(String _aliasExecutedTestAfter) {
        this.aliasExecutedTestAfter = _aliasExecutedTestAfter;
    }

    public String getAliasExecutedTestMethod() {
        return aliasExecutedTestMethod;
    }

    public void setAliasExecutedTestMethod(String _aliasExecutedTestMethod) {
        this.aliasExecutedTestMethod = _aliasExecutedTestMethod;
    }

    public String getAliasResultSuccess() {
        return aliasResultSuccess;
    }

    public void setAliasResultSuccess(String _aliasResultSuccess) {
        this.aliasResultSuccess = _aliasResultSuccess;
    }

    public String getAliasResultFailMessage() {
        return aliasResultFailMessage;
    }

    public void setAliasResultFailMessage(String _aliasResultFailMessage) {
        this.aliasResultFailMessage = _aliasResultFailMessage;
    }

    public String getAliasResultParams() {
        return aliasResultParams;
    }

    public void setAliasResultParams(String _aliasResultParams) {
        this.aliasResultParams = _aliasResultParams;
    }

    public String getAliasParametersMethod() {
        return aliasParametersMethod;
    }

    public void setAliasParametersMethod(String _aliasParametersMethod) {
        this.aliasParametersMethod = _aliasParametersMethod;
    }

    public String getAliasParametersLocation() {
        return aliasParametersLocation;
    }

    public void setAliasParametersLocation(String _aliasParametersLocation) {
        this.aliasParametersLocation = _aliasParametersLocation;
    }

    public String getAliasExecuteTests() {
        return aliasExecuteTests;
    }

    public void setAliasExecuteTests(String _aliasExecuteTests) {
        this.aliasExecuteTests = _aliasExecuteTests;
    }

    public String getAliasExecuteExecute() {
        return aliasExecuteExecute;
    }

    public void setAliasExecuteExecute(String _aliasExecuteExecute) {
        this.aliasExecuteExecute = _aliasExecuteExecute;
    }

    public String getAliasExecuteConvert() {
        return aliasExecuteConvert;
    }

    public void setAliasExecuteConvert(String _aliasExecuteConvert) {
        this.aliasExecuteConvert = _aliasExecuteConvert;
    }

    public String getAliasExecuteSetupNoException() {
        return aliasExecuteSetupNoException;
    }

    public void setAliasExecuteSetupNoException(String _aliasExecuteSetupNoException) {
        this.aliasExecuteSetupNoException = _aliasExecuteSetupNoException;
    }

    public String getAliasExecuteSetupError() {
        return aliasExecuteSetupError;
    }

    public void setAliasExecuteSetupError(String _aliasExecuteSetupError) {
        this.aliasExecuteSetupError = _aliasExecuteSetupError;
    }

    public String getAliasAssertAssert() {
        return aliasAssertAssert;
    }

    public void setAliasAssertAssert(String _aliasAssertAssert) {
        this.aliasAssertAssert = _aliasAssertAssert;
    }

    public String getAliasAssertAssertNot() {
        return aliasAssertAssertNot;
    }

    public void setAliasAssertAssertNot(String _aliasAssertAssertNot) {
        this.aliasAssertAssertNot = _aliasAssertAssertNot;
    }

    public String getAliasAssertAssertNotSame() {
        return aliasAssertAssertNotSame;
    }

    public void setAliasAssertAssertNotSame(String _aliasAssertAssertNotSame) {
        this.aliasAssertAssertNotSame = _aliasAssertAssertNotSame;
    }

    public String getAliasAssertAssertTrue() {
        return aliasAssertAssertTrue;
    }

    public void setAliasAssertAssertTrue(String _aliasAssertAssertTrue) {
        this.aliasAssertAssertTrue = _aliasAssertAssertTrue;
    }

    public String getAliasAssertAssertFalse() {
        return aliasAssertAssertFalse;
    }

    public void setAliasAssertAssertFalse(String _aliasAssertAssertFalse) {
        this.aliasAssertAssertFalse = _aliasAssertAssertFalse;
    }

    public String getAliasAssertAssertNull() {
        return aliasAssertAssertNull;
    }

    public void setAliasAssertAssertNull(String _aliasAssertAssertNull) {
        this.aliasAssertAssertNull = _aliasAssertAssertNull;
    }

    public String getAliasAssertAssertNotNull() {
        return aliasAssertAssertNotNull;
    }

    public void setAliasAssertAssertNotNull(String _aliasAssertAssertNotNull) {
        this.aliasAssertAssertNotNull = _aliasAssertAssertNotNull;
    }

    public String getAliasAssertAssertSame() {
        return aliasAssertAssertSame;
    }

    public void setAliasAssertAssertSame(String _aliasAssertAssertSame) {
        this.aliasAssertAssertSame = _aliasAssertAssertSame;
    }

    public String getAliasDifferenceExpected() {
        return aliasDifferenceExpected;
    }

    public void setAliasDifferenceExpected(String _aliasDifferenceExpected) {
        this.aliasDifferenceExpected = _aliasDifferenceExpected;
    }

    public String getAliasDifferenceFound() {
        return aliasDifferenceFound;
    }

    public void setAliasDifferenceFound(String _aliasDifferenceFound) {
        this.aliasDifferenceFound = _aliasDifferenceFound;
    }

    public String getAliasDifferenceFoundNull() {
        return aliasDifferenceFoundNull;
    }

    public void setAliasDifferenceFoundNull(String _aliasDifferenceFoundNull) {
        this.aliasDifferenceFoundNull = _aliasDifferenceFoundNull;
    }

    public String getAliasDifferenceFoundNotTrue() {
        return aliasDifferenceFoundNotTrue;
    }

    public void setAliasDifferenceFoundNotTrue(String _aliasDifferenceFoundNotTrue) {
        this.aliasDifferenceFoundNotTrue = _aliasDifferenceFoundNotTrue;
    }

    public String getAliasDifferenceStackDiff() {
        return aliasDifferenceStackDiff;
    }

    public void setAliasDifferenceStackDiff(String _aliasDifferenceStackDiff) {
        this.aliasDifferenceStackDiff = _aliasDifferenceStackDiff;
    }

    public String getAliasInfoTestCount() {
        return aliasInfoTestCount;
    }

    public void setAliasInfoTestCount(String _aliasInfoTestCount) {
        this.aliasInfoTestCount = _aliasInfoTestCount;
    }

    public String getAliasResultTime() {
        return aliasResultTime;
    }

    public void setAliasResultTime(String _aliasResultTime) {
        this.aliasResultTime = _aliasResultTime;
    }

    public String getAliasInfoTestDone() {
        return aliasInfoTestDone;
    }

    public void setAliasInfoTestDone(String _aliasInfoTestDone) {
        this.aliasInfoTestDone = _aliasInfoTestDone;
    }

    public String getAliasInfoTestCurrentClass() {
        return aliasInfoTestCurrentClass;
    }

    public void setAliasInfoTestCurrentClass(String _aliasInfoTestCurrentClass) {
        this.aliasInfoTestCurrentClass = _aliasInfoTestCurrentClass;
    }

    public String getAliasInfoTestCurrentMethod() {
        return aliasInfoTestCurrentMethod;
    }

    public void setAliasInfoTestCurrentMethod(String _aliasInfoTestCurrentMethod) {
        this.aliasInfoTestCurrentMethod = _aliasInfoTestCurrentMethod;
    }

    public String getAliasInfoTestCurrentParams() {
        return aliasInfoTestCurrentParams;
    }

    public void setAliasInfoTestCurrentParams(String _aliasInfoTestCurrentParams) {
        this.aliasInfoTestCurrentParams = _aliasInfoTestCurrentParams;
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

    public AbstractInterceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(AbstractInterceptor _interceptor) {
        this.interceptor = _interceptor;
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
