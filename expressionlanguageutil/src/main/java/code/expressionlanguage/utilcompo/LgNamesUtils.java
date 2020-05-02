package code.expressionlanguage.utilcompo;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.files.CommentDelimiters;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.sml.DocumentBuilder;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.AbstractLock;
import code.stream.LockFactory;
import code.stream.ThreadUtil;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public class LgNamesUtils extends LgNames {
    protected static final String RUN = "Run";
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
    private FileInfos infos;

    public LgNamesUtils(FileInfos _infos) {
        infos = _infos;
    }
    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> stds_ = super.buildFiles(_context);
        String content_ = infos.getReader().read("resources_lg/threads/runnable.txt");
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
        map_.put("{void}", getAliasVoid());
        map_.put("{run}", aliasRun);
        map_.put("{endLine}", endLine_);
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasRunnable);
        stds_.put(aliasRunnable, content_);
        getPredefinedInterfacesInitOrder().add(aliasRunnable);
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
        map_.put("{i}", tr("i",_context));
        map_.put("{p}", tr("p",_context));
        map_.put("{out}", tr("out",_context));
        map_.put("{ind}", tr("ind",_context));
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
        content_ = infos.getReader().read("resources_lg/collections/table.txt");
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

        content_ = infos.getReader().read("resources_lg/tests/run.txt");
        map_.put("{a}",tr("a",_context));
        map_.put("{b}",tr("b",_context));
        map_.put("{c}",tr("c",_context));
        map_.put("{d}",tr("d",_context));
        map_.put("{e}",tr("e",_context));
        map_.put("{f}",tr("f",_context));
        map_.put("{i}",tr("i",_context));
        map_.put("{l}",tr("l",_context));
        map_.put("{m}",tr("m",_context));
        map_.put("{o}",tr("o",_context));
        map_.put("{p}",tr("p",_context));
        map_.put("{s}",tr("s",_context));
        map_.put("{t}",tr("t",_context));
        map_.put("{tt}",tr("tt",_context));
        map_.put("{ex}",tr("ex",_context));
        map_.put("{res}",tr("res",_context));
        map_.put("{out}",tr("out",_context));
        map_.put("{processEx}",tr("processEx",_context));
        map_.put("{ctor}",tr("ctor",_context));
        map_.put("{classTest}",tr("classTest",_context));
        map_.put("{results}",tr("results",_context));
        map_.put("{exc}",tr("exc",_context));
        map_.put("{nbParams}",tr("nbParams",_context));
        map_.put("{params}",tr("params",_context));
        map_.put("{arr}",tr("arr",_context));
        map_.put("{as}",tr("as",_context));
        map_.put("{bs}",tr("bs",_context));
        map_.put("{tts}",tr("tts",_context));
        map_.put("{locType}",tr("locType",_context));
        map_.put("{loc}",tr("loc",_context));
        map_.put("{paramAnn}",tr("paramAnn",_context));
        map_.put("{aParam}",tr("aParam",_context));
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
        map_.put("{long}",getAliasPrimLong());
        map_.put("{double}",getAliasPrimDouble());
        map_.put("{toSpecString}",keyWords_.getKeyWordToString());
        map_.put("{Class}", getAliasClassType());
        map_.put("{InvokeTarget}",getAliasInvokeTarget());
        map_.put("{Stack}",getAliasStackTraceElement());
        map_.put("{Object}",getAliasObject());
        map_.put("{String}",getAliasString());
        map_.put("{StringBuilder}",getAliasStringBuilder());
        map_.put("{Method}",getAliasMethod());
        map_.put("{Annotation}", getAliasAnnotationType());
        map_.put("{Thread}",getAliasThread());
        map_.put("{ObjectsUtil}",getAliasObjectsUtil());
        map_.put("{Fct}",getAliasFct());
        map_.put("{Math}",getAliasMath());
        map_.put("{StringUtil}",getAliasStringUtil());
        map_.put("{getAllClasses}",getAliasGetAllClasses());
        map_.put("{getDeclaredMethods}",getAliasGetDeclaredMethods());
        map_.put("{getAnnotations}",getAliasGetAnnotations());
        map_.put("{length}",getAliasLength());
        map_.put("{getMethodName}",getAliasGetName());
        map_.put("{getClassName}",getAliasGetName());
        map_.put("{getClass}",getAliasGetClass());
        map_.put("{isStatic}",getAliasIsStatic());
        map_.put("{getParameterTypes}",getAliasGetParameterTypes());
        map_.put("{getDeclaredConstructors}",getAliasGetDeclaredConstructors());
        map_.put("{invoke}",getAliasInvoke());
        map_.put("{newInstance}",getAliasNewInstance());
        map_.put("{getCause}",getAliasGetCause());
        map_.put("{joinOthers}",getAliasJoinOthers());
        map_.put("{isAssignableFrom}",getAliasIsAssignableFrom());
        map_.put("{current}",getAliasCurrentStack());
        map_.put("{eq}",getAliasSameRef());
        map_.put("{call}",getAliasCall());
        map_.put("{valueOf}", getAliasValueOfMethod());
        map_.put("{append}",getAliasAppend());
        map_.put("{insert}",getAliasInsert());
        map_.put("{toString}", getAliasToStringMethod());
        map_.put("{plus}",getAliasPlus());
        map_.put("{minus}",getAliasMinus());
        map_.put("{lt}",getAliasLt());
        map_.put("{gt}",getAliasGt());
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

        getPredefinedClasses().add(aliasInfoTest);
        getPredefinedClasses().add(aliasDifference);
        getPredefinedClasses().add(aliasAssert);
        getPredefinedClasses().add(aliasParameters);
        getPredefinedClasses().add(aliasBefore);
        getPredefinedClasses().add(aliasAfter);
        getPredefinedClasses().add(aliasTest);
        getPredefinedClasses().add(aliasExecutedTest);
        getPredefinedClasses().add(aliasResult);
        getPredefinedClasses().add(aliasExecute);
        stds_.put(aliasExecute, content_);
        getPredefinedInterfacesInitOrder().add(aliasInfoTest);
        getPredefinedInterfacesInitOrder().add(aliasDifference);
        getPredefinedInterfacesInitOrder().add(aliasAssert);
        getPredefinedInterfacesInitOrder().add(aliasParameters);
        getPredefinedInterfacesInitOrder().add(aliasBefore);
        getPredefinedInterfacesInitOrder().add(aliasAfter);
        getPredefinedInterfacesInitOrder().add(aliasTest);
        getPredefinedInterfacesInitOrder().add(aliasExecutedTest);
        getPredefinedInterfacesInitOrder().add(aliasResult);
        getPredefinedInterfacesInitOrder().add(aliasExecute);
        content_ = infos.getReader().read("resources_lg/threads/formatting.txt");
        map_.put("{Format}",aliasFormatType);
        map_.put("{int}", int_);
        map_.put("{print}",getAliasPrint());
        map_.put("{format}",getAliasFormat());
        content_ = StringList.formatQuote(content_, map_);
        getPredefinedClasses().add(aliasFormatType);
        stds_.put(aliasFormatType, content_);
        getPredefinedInterfacesInitOrder().add(aliasFormatType);
        return stds_;
    }
    protected static String tr(String _var, ContextEl _context) {
        CustList<String> allKeysWords_ = _context.getKeyWords().allKeyWords().values();
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
        method_ = new StandardMethod(aliasCurrentThread, params_, aliasThread, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasThreadCurrentTime, params_, getAliasPrimLong(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasJoin, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasJoinOthers, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasIsAlive, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasIsEnded, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasEnd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
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
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(aliasPrint, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasPrint, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasObject());
        method_ = new StandardMethod(aliasPrint, params_, getAliasVoid(), true, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadExitHook, params_, getAliasVoid(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        StandardConstructor ctor_;
        params_ = new StringList(aliasRunnable);
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasThread, std_);
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasThreadSet, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadSetAdd, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasThreadSetAll, params_, aliasThreadSet, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadSetContains, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadSetRemove, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasThreadSetSnapshot, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasThread), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasThreadSet, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasReentrantLock, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasLock, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasUnlock, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasIsHeldByCurrentThread, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimLong());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
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
        params_ = new StringList(getAliasPrimBoolean(),getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
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
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIncrementAndGetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndIncrementAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDecrementAndGetAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndDecrementAtomic, params_, getAliasPrimInteger(), false, MethodModifier.FINAL, stdcl_);
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
        params_ = new StringList(getAliasPrimLong(),getAliasPrimLong());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, getAliasVoid(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIncrementAndGetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndIncrementAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDecrementAndGetAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndDecrementAtomic, params_, getAliasPrimLong(), false, MethodModifier.FINAL, stdcl_);
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
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryBinary, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasEntryName, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryValue, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimByte()), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(), PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimByte()));
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasEntryBinary, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryText, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        method_ = new StandardMethod(aliasEntryName, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryValue, params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(), getAliasString());
        ctor_ = new StandardConstructor(params_,false,stdcl_);
        constructors_.add(ctor_);
        std_ = stdcl_;
        getStandards().put(aliasEntryText, std_);

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
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileReadBin, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimByte()), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimByte()));
        method_ = new StandardMethod(aliasFileWriteBin, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileDelete, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasFileRename, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),getAliasString());
        method_ = new StandardMethod(aliasAppendToFile, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileAbsolutePath, params_, getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileGetName, params_, getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileGetParentPath, params_, getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileGetLength, params_, getAliasPrimLong(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileLastModif, params_, getAliasPrimLong(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileListDirectories, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasString()), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileListFiles, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasString()), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileIsDirectory, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileIsFile, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileIsAbsolute, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),aliasEntryBinary);
        method_ = new StandardMethod(aliasFileZipBin, params_, getAliasPrimBoolean(), true, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasEntryBinary);
        method_ = new StandardMethod(aliasFileZipBinArray, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimByte()), true, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString(),aliasEntryText);
        method_ = new StandardMethod(aliasFileZipText, params_, getAliasPrimBoolean(), true, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileZippedBin, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasEntryBinary), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimByte()));
        method_ = new StandardMethod(aliasFileZippedBinArray, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasEntryBinary), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileZippedText, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasEntryText), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasFileMakeDirs, params_, getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasFile, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasIllegalThreadStateException, fields_, constructors_, methods_, getAliasError(), MethodModifier.ABSTRACT);
        getStandards().put(aliasIllegalThreadStateException, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasConcurrentError, fields_, constructors_, methods_, getAliasError(), MethodModifier.ABSTRACT);
        getStandards().put(aliasConcurrentError, stdcl_);
    }
    @Override
    public DisplayableStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        if (_arg instanceof RunnableStruct) {
            String className_ = _arg.getClassName(_cont);
            String id_ = Templates.getIdFromAllTypes(className_);
            RootBlock clBody_ = _cont.getClasses().getClassBody(id_);
            if (!ContextEl.isEnumType(clBody_)) {
                return new StringStruct(_arg.getClassName(_cont));
            }
        }
        return super.getStringOfObject(_cont,_arg);
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
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String name_ = _method.getName();
        if (StringList.quickEq(name_,getAliasObject())) {
            return super.getOtherResult(_cont,_method,_args);
        }
        if (StringList.quickEq(name_,aliasThreadSet)) {
            ThreadSetStruct std_ = new ThreadSetStruct();
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasThread)) {
            if (_cont.isWideInitEnums()) {
                processFailInit(_cont.getContextEl());
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
        if (StringList.quickEq(name_,aliasEntryBinary)) {
            String cont_ = _cont.getStandards().getAliasPrimByte();
            cont_ = PrimitiveTypeUtil.getPrettyArrayType(cont_);
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
    protected ResultErrorStd instance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        try {
            return super.instance(_cont, _method, _args);
        } catch (RuntimeException e) {
            ResultErrorStd res_ = new ResultErrorStd();
            res_.setError(aliasConcurrentError);
            processError(_cont,res_);
            return res_;
        }
    }

    protected ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        try {
            return super.invoke(_cont,_method,_struct,_args);
        } catch (RuntimeException e) {
            ResultErrorStd res_ = new ResultErrorStd();
            res_.setError(aliasConcurrentError);
            processError(_cont,res_);
            return res_;
        }
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
        if (StringList.quickEq(className_,aliasThreadSet)) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_,aliasThreadSetAdd)) {
                if (_cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                ins_.add(_args[0]);
                if (!(_args[0] instanceof ThreadStruct)) {
                    res_.setError(getAliasNullPe());
                } else {
                    res_.setResult(NullStruct.NULL_VALUE);
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadSetAll)) {
                if (_cont.isWideInitEnums()) {
                    processFailInit(_cont.getContextEl());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(((RunnableContextEl)_cont).getCustInit().getThreadSet());
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadSetRemove)) {
                if (_cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                ins_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadSetContains)) {
                if (_cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
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
                if (_cont.isWideInitEnums()) {
                    processFailInit(_cont.getContextEl());
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
                Thread thread_ = ((ThreadStruct) _instance).getThread();
                if (ThreadUtil.start(thread_)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                } else {
                    res_.setError(getAliasIllegalThreadStateException());
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasSleep)) {
                if (_cont.isWideInitEnums()) {
                    processFailInit(_cont.getContextEl());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (!(_args[0] instanceof NumberStruct)) {
                    res_.setError(getAliasNullPe());
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
                if (_cont.isWideInitEnums()) {
                    processFailInit(_cont.getContextEl());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(((RunnableContextEl)_cont).getThread());
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadExitHook)) {
                if (_cont.isWideInitEnums()) {
                    processFailInit(_cont.getContextEl());
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
                if (_cont.isWideInitEnums()) {
                    processFailInit(_cont.getContextEl());
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
                    res_.setError(getAliasIllegalArg());
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasYield)) {
                if (_cont.isWideInitEnums()) {
                    processFailInit(_cont.getContextEl());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                Thread.yield();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_cont.isWideInitEnums()) {
                processFailInit(_cont.getContextEl());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new LongStruct(System.currentTimeMillis()));
            return res_;
        }
        if (StringList.quickEq(className_,aliasReentrantLock)) {
            if (_cont.isWideInitEnums()) {
                processFailInit(_cont.getContextEl());
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
            if (_cont.isContainedSensibleFields(_instance)) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
                re_.set(((BooleanStruct)_args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasCompareAndSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.compareAndSet(((BooleanStruct)_args[0]).getInstance(),((BooleanStruct)_args[1]).getInstance())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
                res_.setResult(BooleanStruct.of(re_.getAndSet(((BooleanStruct)_args[0]).getInstance())));
                return res_;
            }
            AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
            re_.lazySet(((BooleanStruct)_args[0]).getInstance());
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
            if (_cont.isContainedSensibleFields(_instance)) {
                _cont.failInitEnums();
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
            if (_cont.isContainedSensibleFields(_instance)) {
                _cont.failInitEnums();
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
            _cont.getContextEl().addSensibleField(inst_, bin_);
            res_.setResult(bin_);
            return res_;
        }
        if (StringList.quickEq(className_,aliasFile)) {
        	String name_ = _method.getConstraints().getName();
        	if (_cont.isWideInitEnums()) {
                processFailInit(_cont.getContextEl());
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
        	    res_.setError(getAliasNullPe());
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
                ArrayStruct bin_ = new ArrayStruct(new Struct[len_],PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimByte()));
                for (int i = 0; i < len_; i++) {
                    bin_.getInstance()[i] = new ByteStruct(read_[i]);
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
                int len_ = arr_.getInstance().length;
                byte[] bin_ = new byte[len_];
                for (int i = 0; i < len_; i++) {
                    Struct byte_ = arr_.getInstance()[i];
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
                    res_.setError(getAliasNullPe());
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
                ArrayStruct arr_ = new ArrayStruct(new Struct[len_],PrimitiveTypeUtil.getPrettyArrayType(getAliasString()));
                for (int i = 0; i < len_; i++) {
                    arr_.getInstance()[i] = new StringStruct(files_.get(i));
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
                ArrayStruct arr_ = new ArrayStruct(new Struct[len_],PrimitiveTypeUtil.getPrettyArrayType(getAliasString()));
                for (int i = 0; i < len_; i++) {
                    arr_.getInstance()[i] = new StringStruct(files_.get(i));
                }
                res_.setResult(arr_);
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZipBin)) {
                String fileName_ = ((StringStruct)_args[0]).getInstance();
                Struct struct_ = ZipBinStructUtil.zipBinFiles(_args[1], (RunnableContextEl) _cont);
                if (struct_ instanceof ArrayStruct) {
                    Struct[] as_ = ((ArrayStruct)struct_).getInstance();
                    int len_ = as_.length;
                    byte[] file_ = new byte[len_];
                    for (int i = 0; i < len_; i++) {
                        Struct byte_ = as_[i];
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
                            String contType_ = _cont.getStandards().getAliasPrimByte();
                            contType_ = PrimitiveTypeUtil.getPrettyArrayType(contType_);
                            int bLen_ = encoded_.length;
                            ArrayStruct bs_ = new ArrayStruct(new Struct[bLen_],contType_);
                            for (int j = 0; j < bLen_; j++) {
                                bs_.getInstance()[j] = new ByteStruct(encoded_[j]);
                            }
                            bins_.add(new EntryBinaryStruct(cont_.getName(), bs_));
                        }
                    }
                }
                int bLen_ = bins_.size();
                String arrType_ = getAliasEntryBinary();
                arrType_ = PrimitiveTypeUtil.getPrettyArrayType(arrType_);
                ArrayStruct bs_ = new ArrayStruct(new Struct[bLen_],arrType_);
                for (int j = 0; j < bLen_; j++) {
                    bs_.getInstance()[j] = bins_.get(j);
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
                String cont_ = _cont.getStandards().getAliasPrimByte();
                cont_ = PrimitiveTypeUtil.getPrettyArrayType(cont_);
                int bLen_ = bytes_.length;
                ArrayStruct bs_ = new ArrayStruct(new Struct[bLen_],cont_);
                for (int j = 0; j < bLen_; j++) {
                    bs_.getInstance()[j] = new ByteStruct(bytes_[j]);
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
                    arrType_ = PrimitiveTypeUtil.getPrettyArrayType(arrType_);
                    int len_ = arrList_.size();
                    ArrayStruct filesOut_ = new ArrayStruct(new Struct[len_],arrType_);
                    for (int i = 0; i < len_; i++) {
                        EntryBinaryStruct fileBin_ = arrList_.get(i);
                        ArrayStruct bin_ = fileBin_.getBinary();
                        Struct[] contBin_ = bin_.getInstance();
                        int contLen_ = contBin_.length;
                        byte[] prim_ = new byte[contLen_];
                        for (int j = 0; j < contLen_; j++) {
                            prim_[j] = ((NumberStruct)contBin_[j]).byteStruct();
                        }
                        String dec_ = StringList.decode(prim_);
                        if (dec_ == null) {
                            filesOut_.getInstance()[i] = new EntryTextStruct(fileBin_.getName(),NullStruct.NULL_VALUE);
                        } else {
                            filesOut_.getInstance()[i] = new EntryTextStruct(fileBin_.getName(),new StringStruct(dec_));
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
                            ClassMethodId _method, Struct... _args) {
        if (_method.getConstraints().getParametersTypes().size() == 1) {
            String type_ = _method.getConstraints().getParametersTypes().first();
            LgNamesUtils stds_ = (LgNamesUtils) _cont.getStandards();
            String aliasObject_ = stds_.getAliasObject();
            if (StringList.quickEq(type_, aliasObject_)) {
                ClassMethodId polymorph_ = new ClassMethodId(stds_.aliasFormatType,new MethodId(MethodAccessKind.STATIC,stds_.aliasPrint,new StringList(aliasObject_)));
                String className_ = polymorph_.getClassName();
                MethodId ct_ = polymorph_.getConstraints();
                Argument arg_ = new Argument(_args[0]);
                _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_,new CustList<Argument>(arg_),null));
                return;
            }
        }
        if (_method.getConstraints().getParametersTypes().size() == 2) {
            LgNamesUtils stds_ = (LgNamesUtils) _cont.getStandards();
            String aliasString_ = stds_.getAliasString();
            String aliasObject_ = stds_.getAliasObject();
            ClassMethodId polymorph_ = new ClassMethodId(stds_.aliasFormatType,new MethodId(MethodAccessKind.STATIC,stds_.aliasPrint,new StringList(aliasString_,aliasObject_),true));
            String className_ = polymorph_.getClassName();
            MethodId ct_ = polymorph_.getConstraints();
            Argument arg_ = new Argument(_args[0]);
            Argument argArr_ = new Argument(_args[1]);
            _cont.setCallingState(new CustomFoundMethod(Argument.createVoid(),className_,ct_,new CustList<Argument>(arg_,argArr_),null));
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
    void coverFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont) {
        infos.getReporter().coverFile(_folder,_fileName,_content,_rCont);
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
        return StringList.concat(((StringStruct)_cont.getStandards().getStringOfObject(_cont,_struct)).getInstance(),"...");
    }
    protected void processFailInit(ContextEl _cont) {
        if (_cont.isInitEnums()) {
            _cont.failInitEnums();
        } else {
            _cont.setException(new ErrorStruct(_cont, aliasConcurrentError));
        }
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
                new KeyValueMemberName(RUN,getAliasRun())
        ));
        return list_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = super.allTableTypeFieldNames();
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

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = super.allTableTypeMethodNames();
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

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ =  super.allRefTypes();
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
    public AbstractFunctionalInstance newFunctionalInstance(String _className, ContextEl _contextEl){
        ObjectMap<ClassField, Struct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className);
        return new RunnableFunctionalInstance(_className,fs_,_contextEl);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(String _className, ContextEl _contextEl) {
        ObjectMap<ClassField, Struct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className);
        return new RunnableFunctionalInstance(_className,fs_,_contextEl);
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

    public void messages(AnalysisMessages _mess,String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"messages");
        String content_ = infos.getReader().read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        messages(_mess,util_,_cust);
    }
    public void messages(AnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
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
        _mess.setMethodsAccesses(get(_util,_cust,AnalysisMessages.METHODS_ACCESSES));
        _mess.setEmptyPackage(get(_util,_cust,AnalysisMessages.EMPTY_PACKAGE));
        _mess.setEmptyPartClassName(get(_util,_cust,AnalysisMessages.EMPTY_PART_CLASS_NAME));
        _mess.setBadPartClassName(get(_util,_cust,AnalysisMessages.BAD_PART_CLASS_NAME));
        _mess.setBadPartVarClassName(get(_util,_cust,AnalysisMessages.BAD_PART_VAR_CLASS_NAME));
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
        _mess.setBadParamerizedType(get(_util,_cust,AnalysisMessages.BAD_PARAMERIZED_TYPE));
        _mess.setUnexpectedTypeBound(get(_util,_cust,AnalysisMessages.UNEXPECTED_TYPE_BOUND));
        _mess.setUnexpectedVararg(get(_util,_cust,AnalysisMessages.UNEXPECTED_VARARG));
        _mess.setUnexpectedLeaf(get(_util,_cust,AnalysisMessages.UNEXPECTED_LEAF));
    }
    public void keyWord(KeyWords _kw,String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"keywords");
        String content_ = infos.getReader().read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        keyWord(_kw,util_,_cust);
    }
    public void keyWord(KeyWords _kw,StringMap<String> _util,StringMap<String> _cust) {
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
    }
    public void otherAlias(String _lang, StringMap<String>_cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"types");
        String content_ = infos.getReader().read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherAlias(util_,_cust);
    }
    public void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        otherAlias(_util,_cust);
    }
    private void otherAlias(StringMap<String> _util, StringMap<String> _cust) {
        setDefaultPkg(get(_util,_cust, DEFAULT_PKG));
        setAliasMaxValueField(get(_util,_cust, FIELD_MAX_VALUE));
        setAliasMinValueField(get(_util,_cust, FIELD_MIN_VALUE));
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
        setAliasStringBuilder(get(_util,_cust, STRING_BUILDER));
        setAliasToUpperCase(get(_util,_cust, TO_UPPER_CASE));
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
        setAliasInvokeTarget(get(_util,_cust, INVOKE_TARGET));
        setAliasGetAnnotations(get(_util,_cust, GET_ANNOTATIONS));
        setAliasGetVariableOwner(get(_util,_cust, GET_VARIABLE_OWNER));
        setAliasReadResources(get(_util,_cust, READ_RESOURCES));
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
        setAliasPairTypeVarSecond(get(_util,_cust, PAIR_TYPE_VAR_SECOND));
        setAliasAnnotated(get(_util,_cust, ANNOTATED));
        setAliasIterableVar(get(_util,_cust, ITERABLE_VAR));
        setAliasGetDefaultValue(get(_util,_cust, GET_DEFAULT_VALUE));
        setAliasMakeGeneric(get(_util,_cust, MAKE_GENERIC));
        setAliasGetAllClasses(get(_util,_cust, GET_ALL_CLASSES));
        setAliasGetOperators(get(_util,_cust, GET_OPERATORS));
        setAliasGetDeclaredMethods(get(_util,_cust, GET_DECLARED_METHODS));
        setAliasGetDeclaredStaticMethods(get(_util,_cust, GET_DECLARED_STATIC_METHODS));
        setAliasGetDeclaredConstructors(get(_util,_cust, GET_DECLARED_CONSTRUCTORS));
        setAliasGetDeclaredFields(get(_util,_cust, GET_DECLARED_FIELDS));
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
        setAliasIllegalThreadStateException(get(_util,_cust, ILLEGAL_THREAD_STATE_EXCEPTION));
        setAliasFileGetLength(get(_util,_cust, FILE_GET_LENGTH));
        setAliasAtomicInteger(get(_util,_cust, ATOMIC_INTEGER));
        setAliasCurrentThread(get(_util,_cust, CURRENT_THREAD));
        setAliasFormatType(get(_util,_cust, FORMAT_TYPE));
        setAliasAtomicBoolean(get(_util,_cust, ATOMIC_BOOLEAN));
        setAliasSetAtomic(get(_util,_cust, SET_ATOMIC));
        setAliasFileIsDirectory(get(_util,_cust, FILE_IS_DIRECTORY));
        setAliasFileGetParentPath(get(_util,_cust, FILE_GET_PARENT_PATH));
        setAliasAtomicLong(get(_util,_cust, ATOMIC_LONG));
        setAliasFileAbsolutePath(get(_util,_cust, FILE_ABSOLUTE_PATH));
        setAliasFileLastModif(get(_util,_cust, FILE_LAST_MODIF));
        setAliasFileGetName(get(_util,_cust, FILE_GET_NAME));
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
        setAliasReentrantLock(get(_util,_cust, REENTRANT_LOCK));
        setAliasJoinOthers(get(_util,_cust, JOIN_OTHERS));
        setAliasFileIsFile(get(_util,_cust, FILE_IS_FILE));
        setAliasThreadExitHook(get(_util,_cust, THREAD_EXIT_HOOK));
        setAliasAppendToFile(get(_util,_cust, APPEND_TO_FILE));
        setAliasThreadCurrentTime(get(_util,_cust, THREAD_CURRENT_TIME));
        setAliasSetPriority(get(_util,_cust, SET_PRIORITY));
        setAliasFileListFiles(get(_util,_cust, FILE_LIST_FILES));
        setAliasGetPriority(get(_util,_cust, GET_PRIORITY));
        setAliasIsHeldByCurrentThread(get(_util,_cust, IS_HELD_BY_CURRENT_THREAD));
        setAliasFileListDirectories(get(_util,_cust, FILE_LIST_DIRECTORIES));
        setAliasLengthItrTa(get(_util,_cust, LENGTH_ITR_TA));
        setAliasExecutedTestAnnotations(get(_util,_cust, EXECUTED_TEST_ANNOTATIONS));
        setAliasPairVarFirst(get(_util,_cust, PAIR_VAR_FIRST));
        setAliasIndexItrLi(get(_util,_cust, INDEX_ITR_LI));
        setAliasListIterTable(get(_util,_cust, LIST_ITER_TABLE));
        setAliasDifference(get(_util,_cust, DIFFERENCE));
        setAliasTableVarSecond(get(_util,_cust, TABLE_VAR_SECOND));
        setAliasIterTaVarFirst(get(_util,_cust, ITER_TA_VAR_FIRST));
        setAliasExecutedTestBefore(get(_util,_cust, EXECUTED_TEST_BEFORE));
        setAliasCustIteratorVar(get(_util,_cust, CUST_ITERATOR_VAR));
        setAliasGetSecondTa(get(_util,_cust, GET_SECOND_TA));
        setAliasExecutedTestTest(get(_util,_cust, EXECUTED_TEST_TEST));
        setAliasExecutedTestMethod(get(_util,_cust, EXECUTED_TEST_METHOD));
        setAliasExecutedTestAfter(get(_util,_cust, EXECUTED_TEST_AFTER));
        setAliasCustIterator(get(_util,_cust, CUST_ITERATOR));
        setAliasListClear(get(_util,_cust, LIST_CLEAR));
        setAliasGetFirstTa(get(_util,_cust, GET_FIRST_TA));
        setAliasSetSecondTa(get(_util,_cust, SET_SECOND_TA));
        setAliasFileMakeDirs(get(_util,_cust, FILE_MAKE_DIRS));
        setAliasFileZippedBin(get(_util,_cust, FILE_ZIPPED_BIN));
        setAliasFileZippedBinArray(get(_util,_cust, FILE_ZIPPED_BIN_ARRAY));
        setAliasFileZippedText(get(_util,_cust, FILE_ZIPPED_TEXT));
        setAliasFileZipBin(get(_util,_cust, FILE_ZIP_BIN));
        setAliasFileZipBinArray(get(_util,_cust, FILE_ZIP_BIN_ARRAY));
        setAliasFileZipText(get(_util,_cust, FILE_ZIP_TEXT));
        setAliasEntryBinary(get(_util,_cust, ENTRY_BINARY));
        setAliasEntryText(get(_util,_cust, ENTRY_TEXT));
        setAliasEntryName(get(_util,_cust, ENTRY_NAME));
        setAliasEntryValue(get(_util,_cust, ENTRY_VALUE));
        setAliasFileIsAbsolute(get(_util,_cust, FILE_IS_ABSOLUTE));
        setAliasFileReadBin(get(_util,_cust, FILE_READ_BIN));
        setAliasFileWriteBin(get(_util,_cust, FILE_WRITE_BIN));
        setAliasFileDelete(get(_util,_cust, FILE_DELETE));
        setAliasFileRename(get(_util,_cust, FILE_RENAME));
        setAliasCustIterTable(get(_util,_cust, CUST_ITER_TABLE));
        setAliasTableVarFirst(get(_util,_cust, TABLE_VAR_FIRST));
        setAliasSetSecond(get(_util,_cust, SET_SECOND));
        setAliasIndexItrTa(get(_util,_cust, INDEX_ITR_TA));
        setAliasIterTaVarSecond(get(_util,_cust, ITER_TA_VAR_SECOND));
        setAliasLengthItrLi(get(_util,_cust, LENGTH_ITR_LI));
        setAliasSetFirstTa(get(_util,_cust, SET_FIRST_TA));
        setAliasExecutedTest(get(_util,_cust, EXECUTED_TEST));
        setAliasParameters(get(_util,_cust, PARAMETERS));
        setAliasTestException(get(_util,_cust, TEST_EXCEPTION));
        setAliasTestNullException(get(_util,_cust, TEST_NULL_EXCEPTION));
        setAliasPairVarSecond(get(_util,_cust, PAIR_VAR_SECOND));
        setAliasExecuteExecute(get(_util,_cust, EXECUTE_EXECUTE));
        setAliasExecuteSetupNoException(get(_util,_cust, EXECUTE_SETUP_NO_EXCEPTION));
        setAliasAssertAssert(get(_util,_cust, ASSERT_ASSERT));
        setAliasAssertAssertNull(get(_util,_cust, ASSERT_ASSERT_NULL));
        setAliasAssertAssertNotNull(get(_util,_cust, ASSERT_ASSERT_NOT_NULL));
        setAliasDifferenceFoundNull(get(_util,_cust, DIFFERENCE_FOUND_NULL));
        setAliasResultSuccess(get(_util,_cust, RESULT_SUCCESS));
        setAliasInfoTestCurrentClass(get(_util,_cust, INFO_TEST_CURRENT_CLASS));
        setAliasExecuteConvert(get(_util,_cust, EXECUTE_CONVERT));
        setAliasConcurrentError(get(_util,_cust, CONCURRENT_ERROR));
        setAliasResultFailMessage(get(_util,_cust, RESULT_FAIL_MESSAGE));
        setAliasAssertAssertTrue(get(_util,_cust, ASSERT_ASSERT_TRUE));
        setAliasInfoTestCurrentMethod(get(_util,_cust, INFO_TEST_CURRENT_METHOD));
        setAliasResultParams(get(_util,_cust, RESULT_PARAMS));
        setAliasParametersMethod(get(_util,_cust, PARAMETERS_METHOD));
        setAliasExecuteSetupError(get(_util,_cust, EXECUTE_SETUP_ERROR));
        setAliasAssertAssertSame(get(_util,_cust, ASSERT_ASSERT_SAME));
        setAliasDifferenceFound(get(_util,_cust, DIFFERENCE_FOUND));
        setAliasDifferenceFoundNotTrue(get(_util,_cust, DIFFERENCE_FOUND_NOT_TRUE));
        setAliasParametersLocation(get(_util,_cust, PARAMETERS_LOCATION));
        setAliasInfoTestCount(get(_util,_cust, INFO_TEST_COUNT));
        setAliasInfoTestDone(get(_util,_cust, INFO_TEST_DONE));
        setAliasDifferenceStackDiff(get(_util,_cust, DIFFERENCE_STACK_DIFF));
        setAliasExecuteTests(get(_util,_cust, EXECUTE_TESTS));
        setAliasDifferenceExpected(get(_util,_cust, DIFFERENCE_EXPECTED));
        setAliasInfoTestCurrentParams(get(_util,_cust, INFO_TEST_CURRENT_PARAMS));
        setAliasRunnable(get(_util,_cust, RUNNABLE));
        setAliasThread(get(_util,_cust, THREAD));
        setAliasThreadSet(get(_util,_cust, THREAD_SET));
        setAliasThreadSetAll(get(_util,_cust, THREAD_SET_ALL));
        setAliasThreadSetAdd(get(_util,_cust, THREAD_SET_ADD));
        setAliasThreadSetContains(get(_util,_cust, THREAD_SET_CONTAINS));
        setAliasThreadSetRemove(get(_util,_cust, THREAD_SET_REMOVE));
        setAliasThreadSetSnapshot(get(_util,_cust, THREAD_SET_SNAPSHOT));
        setAliasStart(get(_util,_cust, START));
        setAliasJoin(get(_util,_cust, JOIN));
        setAliasRun(get(_util,_cust, RUN));
        setAliasLengthLi(get(_util,_cust, LENGTH_LI));
        setAliasCustPair(get(_util,_cust, CUST_PAIR));
        setAliasListTa(get(_util,_cust, LIST_TA));
        setAliasGetId(get(_util,_cust, GET_ID));
        setAliasIsAlive(get(_util,_cust, IS_ALIVE));
        setAliasIsEnded(get(_util,_cust, IS_ENDED));
        setAliasEnd(get(_util,_cust, END));
        setAliasPrint(get(_util,_cust, PRINT));
        setAliasListItr(get(_util,_cust, LIST_ITR));
        setAliasRemoveLi(get(_util,_cust, REMOVE_LI));
        setAliasArrayLi(get(_util,_cust, ARRAY_LI));
        setAliasFirst(get(_util,_cust, FIRST));
        setAliasSleep(get(_util,_cust, SLEEP));
        setAliasFile(get(_util,_cust, FILE));
        setAliasRead(get(_util,_cust, READ));
        setAliasList(get(_util,_cust, LIST));
        setAliasUnlock(get(_util,_cust, UNLOCK));
        setAliasSizeLi(get(_util,_cust, SIZE_LI));
        setAliasListVar(get(_util,_cust, LIST_VAR));
        setAliasSecond(get(_util,_cust, SECOND));
        setAliasAddLi(get(_util,_cust, ADD_LI));
        setAliasYield(get(_util,_cust, YIELD));
        setAliasSetFirst(get(_util,_cust, SET_FIRST));
        setAliasLock(get(_util,_cust, LOCK));
        setAliasWrite(get(_util,_cust, WRITE));
        setAliasTable(get(_util,_cust, TABLE));
        setAliasResult(get(_util,_cust, RESULT));
        setAliasAddTa(get(_util,_cust, ADD_TA));
        setAliasInfoTest(get(_util,_cust, INFO_TEST));
        setAliasRemoveTa(get(_util,_cust, REMOVE_TA));
        setAliasBefore(get(_util,_cust, BEFORE));
        setAliasGetTa(get(_util,_cust, GET_TA));
        setAliasTest(get(_util,_cust, TEST));
        setAliasExecute(get(_util,_cust, EXECUTE));
        setAliasAfter(get(_util,_cust, AFTER));
        setAliasSizeTa(get(_util,_cust, SIZE_TA));
        setAliasAssert(get(_util,_cust, ASSERT));
        getDisplayedStrings().setFalseString(get(_util,_cust, FALSE_STRING));
        getDisplayedStrings().setTrueString(get(_util,_cust, TRUE_STRING));
        getDisplayedStrings().setNullString(get(_util,_cust, NULL_STRING));
        getDisplayedStrings().setNullCoverString(get(_util,_cust, NULL_COVER_STRING));
        getDisplayedStrings().setNotNullCoverString(get(_util,_cust, NOT_NULL_COVER_STRING));
        getDisplayedStrings().setStaticCallString(get(_util,_cust, STATIC_CALL_STRING));
        getDisplayedStrings().setStaticString(get(_util,_cust, STATIC_STRING));
    }
    protected final String get(StringMap<String> _util, StringMap<String> _cust,String _key) {
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

    public CustList<CommentDelimiters> defComments() {
        String content_ = infos.getReader().read("resources_lg/comments.properties");
        content_ = content_.substring(content_.indexOf('=')+1);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        for (String c: StringList.splitChar(
                content_.trim(),
                ';')) {
            StringList parts_ = StringList.splitChar(
                    c.trim(),
                    ',');
            String begin_ = LgNames.parseValue(parts_.first());
            String end_ = LgNames.parseValue(parts_.last());
            comments_.add(new CommentDelimiters(begin_,new StringList(end_)));
        }
        return comments_;
    }
}
