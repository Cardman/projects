package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.guicompos.EventStruct;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.threads.AbstractDate;
import code.threads.AbstractDateFactory;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class CustAliases implements AbsAliasFileBuilder {
    public static final String ANY_PAIR = "<?,?,";
    public static final String LR = "\n";
    public static final String SPACES_4 = "    ";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy_MM_dd_HH_mm_ss_SSS";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS_DASH = "----_--_--_--_--_--_---";
    public static final String OTHERS = "...";

//    public static final String RESOURCES_LG_ALIASES = "resources_lg/aliases";
//    public static final String FILE_MESSAGES = "messages";
//    public static final String FILE_KEYWORDS = "keywords";
//    public static final String FILE_TYPES = "types";

    private static final String THREAD="3";
    private static final String THREAD_SET="________1814";
    private static final String EXECUTOR_SERVICE="____1127";
    private static final String EXECUTOR_SERVICE_BASE="____1128";
    private static final String SCHEDULED_EXECUTOR_SERVICE="____1129";
    private static final String FUTURE="____1122";
    private static final String ATOMIC_BOOLEAN="____1123";
    private static final String ATOMIC_INTEGER="____1124";
    private static final String ATOMIC_LONG="____1125";
    private static final String ATOMIC_REF="____1125_";
    private static final String FILE="652";
    private static final String ILLEGAL_THREAD_STATE_EXCEPTION="__________1913";
    private static final String CUST_ITERATOR="731";
    private static final String LIST="732";
    private static final String RUNNABLE="____1121";
    private static final String CALLABLE="____1121_";
    private static final String FORMAT_TYPE="_________1865";
    private static final String CUST_PAIR="733";
    private static final String CUST_ITER_TABLE="734";
    private static final String TABLE="735";
    private static final String EXECUTE="___________2083";
    private static final String INFO_TEST="___________2084";
    private static final String EXECUTED_TEST="___________2085";
    private static final String RESULT="___________2086";
    private static final String BEFORE="___________2087";
    private static final String AFTER="___________2088";
    private static final String PARAMETERS="___________2089";
    private static final String TEST="0";
    private static final String ARGS_TEST="___________2090";
    private static final String ASSERT="26";
    private static final String DIFFERENCE="115";
    private static final String ELT_DIFFERENCE="121";
    private static final String CONCURRENT_ERROR="__________1881";
    private static final String ENTRY_BINARY="____1130";
    private static final String ENTRY_TEXT="____1131";
    private static final String ENTRY_STRING_OBJECT="____1132";
    private static final String TABLE_STRING_OBJECT="____1126";
    private static final String START="________1822";
    private static final String THREAD_EQ="________1820";
    private static final String THREAD_RUNNABLE="________1821";
    private static final String THREAD_CURRENT_TIME="____1133";
    private static final String THREAD_CURRENT_NANO_TIME="____1134";
    private static final String IS_ALIVE="________1828";
    private static final String IS_ENDED="________1829";
    private static final String END="________1830";
    private static final String CURRENT_THREAD="________1824";
    private static final String JOIN="________1823";
    private static final String INTERRUPT="________1831";
    private static final String ARGS="________1832";
    private static final String COVERAGE="________1833";
    private static final String JOIN_OTHERS="________1836";
    private static final String GET_ID="________1825";
    private static final String GET_PRIORITY="________1826";
    private static final String SET_PRIORITY="________1827";
    private static final String SLEEP="____1135";
    private static final String PRINT="5";
    private static final String EXECUTOR_SERVICE_EXECUTE="____1136";
    private static final String EXECUTOR_SERVICE_SUBMIT="____1137";
    private static final String EXECUTOR_SERVICE_SHUTDOWN="____1138";
    private static final String EXECUTOR_SERVICE_STOPPED="____1138_";
    private static final String EXECUTOR_SERVICE_SCHEDULE_MILLIS="____1139";
    private static final String EXECUTOR_SERVICE_SCHEDULE_NANOS="____1140";
    private static final String FUTURE_WAIT="____1141";
    private static final String FUTURE_CANCEL="____1142";
    private static final String THREAD_SET_ADD="________1815";
    private static final String THREAD_SET_ALL="________1816";
    private static final String THREAD_SET_CONTAINS="________1817";
    private static final String THREAD_SET_REMOVE="________1818";
    private static final String THREAD_SET_SNAPSHOT="________1819";
    private static final String RUN="____1143";
    private static final String CALLABLE_METHOD="____1143_";
    private static final String GET_ATOMIC="____1144";
    private static final String SET_ATOMIC="____1145";
    private static final String COMPARE_AND_SET_ATOMIC="____1146";
    private static final String GET_AND_SET_ATOMIC="____1147";
    private static final String LAZY_SET_ATOMIC="____1148";
    private static final String ADD_AND_GET_ATOMIC="____1149";
    private static final String GET_AND_ADD_ATOMIC="____1150";
    private static final String INCREMENT_AND_GET_ATOMIC="____1151";
    private static final String GET_AND_INCREMENT_ATOMIC="____1152";
    private static final String DECREMENT_AND_GET_ATOMIC="____1153";
    private static final String GET_AND_DECREMENT_ATOMIC="____1154";
    private static final String READ="653";
    private static final String WRITE="654";
    private static final String APPEND_TO_FILE="655";
    private static final String FILE_ABSOLUTE_PATH="656";
    private static final String FILE_GET_LENGTH="657";
    private static final String FILE_GET_NAME="658";
    private static final String FILE_GET_PARENT_PATH="659";
    private static final String FILE_IS_DIRECTORY="660";
    private static final String FILE_IS_FILE="661";
    private static final String FILE_ROOTS="662";
    private static final String FILE_IS_ABSOLUTE="663";
    private static final String FILE_READ_BIN="664";
    private static final String FILE_WRITE_BIN="665";
    private static final String FILE_DELETE="666";
    private static final String FILE_DIR="667";
    private static final String FILE_RENAME="668";
    private static final String FILE_LAST_MODIF="669";
    private static final String FILE_LIST_DIRECTORIES="670";
    private static final String FILE_LIST_FILES="671";
    private static final String FILE_ZIP_BIN="672";
    private static final String FILE_ZIP_BIN_ARRAY="673";
    private static final String FILE_ZIP_TEXT="674";
    private static final String FILE_ZIPPED_BIN="675";
    private static final String FILE_ZIPPED_BIN_ARRAY="676";
    private static final String FILE_ZIPPED_TEXT="677";
    private static final String FILE_MAKE_DIRS="678";
    private static final String TABLE_CONC_PAIRS="____1155";
    private static final String TABLE_CONC_EMPTY="____1156";
    private static final String TABLE_CONC_SIZE="____1157";
    private static final String TABLE_CONC_KEYS="____1158";
    private static final String TABLE_CONC_VALUES="____1159";
    private static final String TABLE_CONC_HAS_KEY="____1160";
    private static final String TABLE_CONC_HAS_VALUE="____1161";
    private static final String TABLE_CONC_GET="____1162";
    private static final String TABLE_CONC_REMOVE="____1163";
    private static final String TABLE_CONC_PUT="____1164";
    private static final String TABLE_CONC_PUT_ABS="____1165";
    private static final String TABLE_CONC_REPLACE="____1166";
    private static final String TABLE_CONC_CLEAR="____1167";
    private static final String TABLE_CONC_PUT_ALL="____1168";
    private static final String TABLE_ENTRY_KEY="____1169";
    private static final String TABLE_ENTRY_VALUE="____1170";
    private static final String TABLE_ENTRY_OWNER="____1171";
    private static final String ENTRY_NAME="____1172";
    private static final String ENTRY_TIME="____1173";
    private static final String ENTRY_VALUE="____1174";
    private static final String ADD_LI="744";
    private static final String SIZE_LI="745";
    private static final String REMOVE_LI="746";
    private static final String LIST_CLEAR="747";
    private static final String SET_FIRST="748";
    private static final String SET_SECOND="749";
    private static final String GET_FIRST_TA="750";
    private static final String GET_SECOND_TA="751";
    private static final String SET_FIRST_TA="752";
    private static final String SET_SECOND_TA="753";
    private static final String ADD_TA="754";
    private static final String REMOVE_TA="755";
    private static final String SIZE_TA="756";
    private static final String GET_TA="757";
    private static final String EXECUTE_TESTS="___________2091";
    private static final String EXECUTE_CONVERT="___________2092";
    private static final String EXECUTE_SETUP_ERROR="___________2093";
    private static final String EXECUTE_SETUP_NO_EXCEPTION="___________2094";
    private static final String EXECUTE_EXECUTE="___________2095";
    private static final String EXECUTE_GROUP_CLASS="___________2095_";
    private static final String EXECUTE_GROUP_CLASS_METHOD="___________2095__";
    private static final String EXECUTE_FLAT="___________2095___";
    private static final String EXECUTE_LAUNCH="___________2095____";
    private static final String PARAMETERS_LOCATION="___________2096";
    private static final String PARAMETERS_METHOD="___________2097";
    private static final String TEST_EXCEPTION="___________2098";
    private static final String TEST_NULL_EXCEPTION="___________2099";
    private static final String ARGS_TEST_ARGS_VALUE="___________2100";
    private static final String ASSERT_ASSERT="27";
    private static final String ASSERT_NOT="28";
    private static final String ASSERT_ASSERT_NOT_NULL="29";
    private static final String ASSERT_ASSERT_NULL="30";
    private static final String ASSERT_ASSERT_SAME="31";
    private static final String ASSERT_NOT_SAME="32";
    private static final String ASSERT_ASSERT_TRUE="33";
    private static final String ASSERT_ASSERT_FALSE="34";
    private static final String DIFFERENCE_EXPECTED="116";
    private static final String DIFFERENCE_FOUND="117";
    private static final String DIFFERENCE_FOUND_NOT_TRUE="118";
    private static final String DIFFERENCE_FOUND_NULL="119";
    private static final String DIFFERENCE_STACK_DIFF="120";
    private static final String INDEX="122";
    private static final String RUNNABLE_IMPLICIT_0_RUNNER="____1226";
    private static final String CALLABLE_IMPLICIT_0_RUNNER="____1226_";
    private static final String INFO_TEST_COUNT="___________2101";
    private static final String INFO_TEST_CALLS="___________2102";
    private static final String INFO_TEST_CURRENT_METHOD="___________2103";
    private static final String INFO_TEST_DONE="___________2104";
    private static final String INFO_TEST_NB_THREADS="___________2105";
    private static final String RESULT_ARGS ="___________2106";
    private static final String RESULT_CONTAINER ="___________2107";
    private static final String RESULT_EXECUTED ="___________2108";
    private static final String INFO_TEST_CURRENT_PARAMS="___________2109";
    private static final String RESULT_FAIL_MESSAGE="___________2110";
    private static final String RESULT_PARAMS="___________2111";
    private static final String RESULT_TIME="___________2112";
    private static final String RESULT_SUCCESS="___________2113";
    private static final String EXECUTED_TEST_AFTER="___________2114";
    private static final String EXECUTED_TEST_BEFORE="___________2115";
    private static final String EXECUTED_TEST_ARGS_ANNOT="___________2116";
    private static final String EXECUTED_TEST_ANNOTATIONS="___________2117";
    private static final String EXECUTED_TEST_METHOD="___________2118";
    private static final String EXECUTED_TEST_TEST="___________2119";
    private static final String LIST_ITR="758";
    private static final String LENGTH_ITR_LI="759";
    private static final String INDEX_ITR_LI="760";
    private static final String ARRAY_LI="761";
    private static final String LENGTH_LI="762";
    private static final String LIST_ITER_TABLE="763";
    private static final String LENGTH_ITR_TA="764";
    private static final String INDEX_ITR_TA="765";
    private static final String FIRST="766";
    private static final String SECOND="767";
    private static final String LIST_TA="768";
    private static final String LIST_VAR="777";
    private static final String CALLABLE_VAR="777_";
    private static final String CUST_ITERATOR_VAR="778";
    private static final String TABLE_VAR_FIRST="779";
    private static final String TABLE_VAR_SECOND="780";
    private static final String ITER_TA_VAR_FIRST="781";
    private static final String ITER_TA_VAR_SECOND="782";
    private static final String PAIR_VAR_FIRST="783";
    private static final String PAIR_VAR_SECOND="784";
    private static final String MILLION = "1000000";
    private String aliasRunnable;
    private String aliasCallable;
    private String aliasThreadSet;
    private String aliasThreadSetAll;
    private String aliasThreadSetAdd;
    private String aliasThreadSetContains;
    private String aliasThreadSetRemove;
    private String aliasThreadSetSnapshot;
    private String aliasThread;
    private String aliasThreadRunnable;
    private String aliasThreadCurrentTime;
    private String aliasThreadCurrentNanoTime;
    private String aliasExecutorServiceBase;
    private String aliasScheduledExecutorService;
    private String aliasExecutorServiceScheduleMillis;
    private String aliasExecutorServiceScheduleNanos;
    private String aliasExecutorService;
    private String aliasExecutorServiceShutdown;
    private String aliasExecutorServiceStopped;
    private String aliasExecutorServiceExecute;
    private String aliasExecutorServiceSubmit;
    private String aliasRunnableImplicit0Runner;
    private String aliasCallableImplicit0Runner;
    private String aliasFuture;
    private String aliasFutureWait;
    private String aliasFutureCancel;
//    private String aliasThreadExitHook;
    private String aliasCurrentThread;
    private String aliasStart;
    private String aliasThreadEq;
    private String aliasInterrupt;
    private String aliasCoverage;
    private String aliasArgs;
    private String aliasJoin;
    private String aliasJoinOthers;
    private String aliasSleep;
    private String aliasRun;
    private String aliasCallableMethod;
    private String aliasIsAlive;
    private String aliasIsEnded;
    private String aliasEnd;
    private String aliasGetId;
    private String aliasGetPriority;
    private String aliasSetPriority;
//    private String aliasYield;
//    private String aliasReentrantLock;
//    private String aliasLock;
//    private String aliasUnlock;
//    private String aliasIsHeldByCurrentThread;
    private String aliasAtomicBoolean;
    private String aliasAtomicInteger;
    private String aliasAtomicLong;
    private String aliasAtomicRef;
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
    private String aliasTableEntryOwner;
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
    private String aliasCallableVar;

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
    private String aliasArgsTest;
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
    private String aliasExecutedTestArgsAnnot;
    private String aliasExecutedTestTest;
    private String aliasExecutedTestBefore;
    private String aliasExecutedTestAfter;
    private String aliasExecutedTestMethod;
    private String aliasArgsTestArgsValue;

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
    private String aliasExecuteGroupClass;
    private String aliasExecuteGroupClassMethod;
    private String aliasExecuteFlat;
    private String aliasExecuteLaunch;

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
    private String aliasInfoTestCalls;
    private String aliasInfoTestDone;
    private String aliasInfoTestNbThreads;
    private String aliasInfoTestCurrentClass;
    private String aliasInfoTestCurrentMethod;
    private String aliasInfoTestCurrentParams;
    private String aliasResultArgs;
    private String aliasResultContainer;
    private String aliasResultExecuted;

    private String aliasConcurrentError;
    private final StringViewReplaceAliases stringViewReplaceAliases = new StringViewReplaceAliases();
    private final MathAdvAliases mathAdvAliases = new MathAdvAliases();

    private final CustAliasParameters custAliasParameters = new CustAliasParameters();

    private FileInfos infos;
    private AbstractInterceptor interceptor;
    private Translations translations;
    private String language = "";
    private String userLg = "";

    public static StringStruct getStringOfObjectUtil(ContextEl _cont, Struct _arg) {
        return ExecCatOperation.getStringOfObjectBase(_cont, _arg);
    }

    public static EventStruct newFunctional(ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className);
        return new EventStruct(_contextEl, _className.getFormatted(), fs_,_functional, _named);
    }

    public void buildOther(LgNamesContent _content, ExecutingBlocks _executingBlocks) {
        mathAdvAliases.buildOther(_content);
        thread(_content, _executingBlocks);
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        StandardClass stdcl_ = new StandardClass(aliasThreadSet, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfThreadSet(getInterceptor()));
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        StringList params_ = new StringList(aliasThread);
        StandardMethod method_ = new StandardMethod(aliasThreadSetAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new StringList(custAliasParameters.getAliasThreadSet0ThreadSetAdd0()), new FctThreadSetAdd());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasThreadSetAll, params_, aliasThreadSet, false, MethodModifier.STATIC,new FctThreadSetAll(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadSetContains, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasThreadSet0ThreadSetContains0()),new FctThreadSetContains());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasThread);
        method_ = new StandardMethod(aliasThreadSetRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasThreadSet0ThreadSetRemove0()),new FctThreadSetRemove());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasThreadSetSnapshot, params_, StringExpUtil.getPrettyArrayType(aliasThread), false, MethodModifier.FINAL,new FctThreadSetSnap());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        StandardConstructor ctor_ = new StandardConstructor(params_,false,new FctThreadSet(interceptor));
        StandardNamedFunction.addFct(constructors_, ctor_);
        StandardType std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasThreadSet, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasExecutorServiceBase, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        StandardClass service_ = stdcl_;
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        params_ = new StringList();
        method_ = new StandardMethod(aliasExecutorServiceShutdown, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new FctExecutorServiceShutdown(aliasExecutorServiceBase+"."+new MethodId(MethodAccessKind.INSTANCE,aliasExecutorServiceShutdown,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasExecutorServiceStopped, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new FctExecutorServiceStopped());
        StandardNamedFunction.addFct(methods_, method_);
        StandardType.addType(_content.getStandards(), aliasExecutorServiceBase, stdcl_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasExecutorService, fields_, constructors_, methods_, aliasExecutorServiceBase, MethodModifier.FINAL, new DfExecutorService(interceptor, infos.getThreadFactory(), aliasExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,"",params_).getSignature(_content.getDisplayedStrings())));
        stdcl_.addSuperStdTypes(service_);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        params_ = new StringList(aliasRunnable);
        method_ = new StandardMethod(aliasExecutorServiceExecute, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasExecutorService0Execute0()),new FctExecutorServiceExecute0(aliasExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,aliasExecutorServiceExecute,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasRunnable);
        method_ = new StandardMethod(aliasExecutorServiceSubmit, params_, aliasFuture, false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasExecutorService0Submit0()),new FctExecutorServiceSubmit0(aliasExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,aliasExecutorServiceSubmit,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasCallable+"<?>");
        method_ = new StandardMethod(aliasExecutorServiceSubmit, params_, aliasFuture, false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasExecutorService0Submit0()),new FctExecutorServiceSubmit1(aliasExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,aliasExecutorServiceSubmit,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,new FctExecutorService0(interceptor, infos.getThreadFactory(), aliasExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,"",params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(constructors_, ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasExecutorService1ExecutorService0()),new FctExecutorService1(interceptor, infos.getThreadFactory(), aliasExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,"",params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(constructors_, ctor_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasExecutorService, std_);
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasScheduledExecutorService, fields_, constructors_, methods_, aliasExecutorServiceBase, MethodModifier.FINAL, new DfScheduledExecutorService(infos.getThreadFactory(), aliasScheduledExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,"",params_).getSignature(_content.getDisplayedStrings())));
        stdcl_.addSuperStdTypes(service_);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        params_ = new StringList(aliasRunnable,_content.getPrimTypes().getAliasPrimLong(),_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasExecutorServiceScheduleMillis, params_, aliasFuture, false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasExecutorService0ScheduleMillis0(),custAliasParameters.getAliasExecutorService0ScheduleMillis1(),custAliasParameters.getAliasExecutorService0ScheduleMillis2()),new FctScheduledExecutorMillis0(aliasScheduledExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,aliasExecutorServiceScheduleMillis,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasRunnable,_content.getPrimTypes().getAliasPrimLong(),_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasExecutorServiceScheduleNanos, params_, aliasFuture, false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasExecutorService0ScheduleNanos0(),custAliasParameters.getAliasExecutorService0ScheduleNanos1(),custAliasParameters.getAliasExecutorService0ScheduleNanos2()),new FctScheduledExecutorNanos0(aliasScheduledExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,aliasExecutorServiceScheduleNanos,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,new FctScheduledExecutorService0(infos.getThreadFactory(), aliasScheduledExecutorService+"."+new MethodId(MethodAccessKind.INSTANCE,"",params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(constructors_, ctor_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasScheduledExecutorService, std_);
        future(_content);
//        methods_ = new CustList<StandardMethod>();
//        constructors_ = new CustList<StandardConstructor>();
//        fields_ = new CustList<CstFieldInfo>();
//        params_ = new StringList();
//        stdcl_ = new StandardClass(aliasReentrantLock, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfReentrantLock(getInfos(),aliasReentrantLock));
//        method_ = new StandardMethod(aliasLock, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new FctReentrantLock());
//        methods_.add( method_);
//        method_ = new StandardMethod(aliasUnlock, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new FctReentrantUnlock());
//        methods_.add( method_);
//        method_ = new StandardMethod(aliasIsHeldByCurrentThread, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new FctReentrantIsHeldByCurrentThread());
//        methods_.add( method_);
//        params_ = new StringList();
//        ctor_ = new StandardConstructor(params_,false,new FctReentrant0(infos,aliasReentrantLock));
//        constructors_.add(ctor_);
//        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
//        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasReentrantLock0ReentrantLock0()),new FctReentrant1(infos,aliasReentrantLock));
//        constructors_.add(ctor_);
//        std_ = stdcl_;
//        _content.getStandards().addEntry(aliasReentrantLock, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicBoolean, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfAtomicBoolean(getInfos().getThreadFactory(),aliasAtomicBoolean));
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        method_ = new StandardMethod(aliasGetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new FctAtomicBooleanGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicBoolean0SetAtomic0()),new FctAtomicBooleanSet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicBoolean0CompareAndSetAtomic0(),custAliasParameters.getAliasAtomicBoolean0CompareAndSetAtomic1()),new FctAtomicBooleanCompare());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicBoolean0GetAndSetAtomic0()),new FctAtomicBooleanGetSet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicBoolean0LazySetAtomic0()),new FctAtomicBooleanLazy());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,new FctAtomicBoolean0(infos.getThreadFactory(),aliasAtomicBoolean));
        StandardNamedFunction.addFct(constructors_, ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasAtomicBoolean0AtomicBoolean0()),new FctAtomicBoolean1(infos.getThreadFactory(),aliasAtomicBoolean));
        StandardNamedFunction.addFct(constructors_, ctor_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasAtomicBoolean, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicInteger, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfAtomicInteger(getInfos().getThreadFactory(),aliasAtomicInteger));
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        method_ = new StandardMethod(aliasGetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new FctAtomicIntegerGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0SetAtomic0()),new FctAtomicIntegerSet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0CompareAndSetAtomic0(),custAliasParameters.getAliasAtomicInteger0CompareAndSetAtomic1()),new FctAtomicIntegerCompare());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0GetAndSetAtomic0()),new FctAtomicIntegerGetSet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0LazySetAtomic0()),new FctAtomicIntegerLazy());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0AddAndGetAtomic0()),new FctAtomicIntegerAddGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicInteger0GetAndAddAtomic0()),new FctAtomicIntegerGetAdd());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIncrementAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new FctAtomicIntegerIncGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndIncrementAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new FctAtomicIntegerGetInc());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDecrementAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new FctAtomicIntegerDecGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndDecrementAtomic, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new FctAtomicIntegerGetDec());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,new FctAtomicInteger0(infos.getThreadFactory(),aliasAtomicInteger));
        StandardNamedFunction.addFct(constructors_, ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasAtomicInteger0AtomicInteger0()),new FctAtomicInteger1(infos.getThreadFactory(),aliasAtomicInteger));
        StandardNamedFunction.addFct(constructors_, ctor_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasAtomicInteger, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicLong, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfAtomicLong(getInfos().getThreadFactory(),aliasAtomicLong));
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        method_ = new StandardMethod(aliasGetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL, new FctAtomicLongGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0SetAtomic0()),new FctAtomicLongSet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong(),_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasCompareAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0CompareAndSetAtomic0(),custAliasParameters.getAliasAtomicLong0CompareAndSetAtomic1()),new FctAtomicLongCompare());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0GetAndSetAtomic0()),new FctAtomicLongGetSet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0LazySetAtomic0()),new FctAtomicLongLazy());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasAddAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0AddAndGetAtomic0()),new FctAtomicLongAddGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetAndAddAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicLong0GetAndAddAtomic0()),new FctAtomicLongGetAdd());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIncrementAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new FctAtomicLongIncGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndIncrementAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new FctAtomicLongGetInc());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDecrementAndGetAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new FctAtomicLongDecGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAndDecrementAtomic, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new FctAtomicLongGetDec());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,new FctAtomicLong0(infos.getThreadFactory(),aliasAtomicLong));
        StandardNamedFunction.addFct(constructors_, ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasAtomicLong0AtomicLong0()),new FctAtomicLong1(infos.getThreadFactory(),aliasAtomicLong));
        StandardNamedFunction.addFct(constructors_, ctor_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasAtomicLong, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasAtomicRef, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfAtomicRef(aliasAtomicRef));
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        method_ = new StandardMethod(aliasGetAtomic, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new FctAtomicRefGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasSetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicRef0SetAtomic0()),new FctAtomicRefSet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGetAndSetAtomic, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicRef0GetAndSetAtomic0()),new FctAtomicRefGetSet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasLazySetAtomic, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasAtomicRef0LazySetAtomic0()),new FctAtomicRefLazy());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,new FctAtomicRef0(aliasAtomicRef));
        StandardNamedFunction.addFct(constructors_, ctor_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasAtomicRef0AtomicRef0()),new FctAtomicRef1(aliasAtomicRef));
        StandardNamedFunction.addFct(constructors_, ctor_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasAtomicRef, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryStringObject, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        method_ = new StandardMethod(aliasTableEntryKey, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new FctTrenteKey());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableEntryValue, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new FctTrenteValue0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableEntryValue, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasEntryStringObject0TableEntryValue0()),new FctTrenteValue1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableEntryOwner, params_, aliasTableStringObject, false, MethodModifier.FINAL,new FctTrenteOwner());
        StandardNamedFunction.addFct(methods_, method_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasEntryStringObject, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTableStringObject, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcKeys, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.FINAL, new FctTastrKeys0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcKeys, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0ConcKeys0()), new FctTastrKeys1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcValues, params_, StringExpUtil.getPrettyArrayType(_content.getCoreNames().getAliasObject()), false, MethodModifier.FINAL, new FctTastrValues());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTableConcGet, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0Get0()), new FctTastrGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTableConcRemove, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0Remove0()), new FctTastrRemove());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTableConcHasKey, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0ConcHasKey0()), new FctTastrHasKey());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcHasValue, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0ConcHasValue0()), new FctTastrHasValue());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasTableStringObject);
        method_ = new StandardMethod(aliasTableConcPutAll, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0PutAll0()), new FctTastrPutAll());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcPut, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0Put0(),custAliasParameters.getAliasTableStringObject0Put1()), new FctTastrPut());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcPutAbs, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0PutAbs0(),custAliasParameters.getAliasTableStringObject0PutAbs1()), new FctTastrPutAbs());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasTableConcReplace, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasTableStringObject0Replace0(),custAliasParameters.getAliasTableStringObject0Replace1()), new FctTastrReplace());
        StandardNamedFunction.addFct(methods_, method_);

        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcClear, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctTastrClear());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcPairs, params_, StringExpUtil.getPrettyArrayType(aliasEntryStringObject), false, MethodModifier.FINAL, new FctTastrPairs());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcEmpty, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctTastrIsEmpty());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableConcSize, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctTastrSize());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,new FctTastr(interceptor));
        StandardNamedFunction.addFct(constructors_, ctor_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasTableStringObject, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryBinary, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        method_ = new StandardMethod(aliasEntryName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctEntryBinaryName());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryValue, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()), false, MethodModifier.FINAL, new FctEntryBinaryValue());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryTime, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL, new FctEntryBinaryTime0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasEntryTime, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasEntryBinary0EntryTime0()), new FctEntryBinaryTime1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(), StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()));
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasEntryBinary0EntryBinary0(),custAliasParameters.getAliasEntryBinary0EntryBinary1()),new FctEntryBinary());
        StandardNamedFunction.addFct(constructors_, ctor_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasEntryBinary, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        params_ = new StringList();
        stdcl_ = new StandardClass(aliasEntryText, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        method_ = new StandardMethod(aliasEntryName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL, new FctEntryTextName());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryValue, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new FctEntryTextValue());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasEntryTime, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL, new FctEntryTextTime0());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasEntryTime, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasEntryText0EntryTime0()), new FctEntryTextTime1());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(), _content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasEntryText0EntryText0(),custAliasParameters.getAliasEntryText0EntryText1()),new FctEntryText());
        StandardNamedFunction.addFct(constructors_, ctor_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasEntryText, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasFile, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.HYPER_ABSTRACT);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasRead, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0Read0()), new FctFileRead(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasWrite, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0Write0(),custAliasParameters.getAliasFile0Write1()), new FctFileWrite(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileReadBin, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileReadBin0()), new FctFileReadBin(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()));
        method_ = new StandardMethod(aliasFileWriteBin, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileWriteBin0(),custAliasParameters.getAliasFile0FileWriteBin1()), new FctFileWriteBin(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileDelete, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0ThreadSetRemove0()), new FctFileDelete(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileRename, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileRename0(),custAliasParameters.getAliasFile0FileRename1()), new FctFileRename(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFileDir, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(), new FctFileDir0(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileDir, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileDir0()), new FctFileDir1(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasAppendToFile, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0AppendToFile0(),custAliasParameters.getAliasFile0AppendToFile1()), new FctFileAppendToFile(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileAbsolutePath, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileAbsolutePath0()), new FctFileAbsolutePath(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileGetName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileGetName0()), new FctFileGetName(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileGetParentPath, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileGetParentPath0()), new FctFileGetParentPath(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileGetLength, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileGetLength0()), new FctFileGetLength(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileLastModif, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileLastModif0()), new FctFileLastModif(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileListDirectories, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileListDirectories0()), new FctFileListDirectories(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileListFiles, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileListFiles0()), new FctFileListFiles(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileIsDirectory, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileIsDirectory0()), new FctFileIsDirectory(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileIsFile, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileIsFile0()), new FctFileIsFile(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFileRoots, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC, new FctFileRoots(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileIsAbsolute, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileIsAbsolute0()), new FctFileIsAbsolute(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),aliasEntryBinary);
        method_ = new StandardMethod(aliasFileZipBin, params_, _content.getPrimTypes().getAliasPrimBoolean(), true, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZipBin0(),custAliasParameters.getAliasFile0FileZipBin1()), new FctFileZipBin(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(aliasEntryBinary);
        method_ = new StandardMethod(aliasFileZipBinArray, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()), true, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZipBinArray0()), new FctFileZipBinArray(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),aliasEntryText);
        method_ = new StandardMethod(aliasFileZipText, params_, _content.getPrimTypes().getAliasPrimBoolean(), true, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZipText0(),custAliasParameters.getAliasFile0FileZipText1()), new FctFileZipText(this, aliasEntryBinary));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileZippedBin, params_, StringExpUtil.getPrettyArrayType(aliasEntryBinary), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZippedBin0()), new FctFileZippedBin(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimByte()));
        method_ = new StandardMethod(aliasFileZippedBinArray, params_, StringExpUtil.getPrettyArrayType(aliasEntryBinary), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZippedBinArray0()), new FctFileZippedBinArray(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileZippedText, params_, StringExpUtil.getPrettyArrayType(aliasEntryText), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileZippedText0()), new FctFileZippedText(this,aliasEntryText));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFileMakeDirs, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasFile0FileMakeDirs0()), new FctFileMkdirs(this));
        StandardNamedFunction.addFct(methods_, method_);
        std_ = stdcl_;
        StandardType.addType(_content.getStandards(), aliasFile, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasIllegalThreadStateException, fields_, constructors_, methods_, _content.getCoreNames().getAliasError(), StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getErrType());
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        StandardType.addType(_content.getStandards(), aliasIllegalThreadStateException, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasConcurrentError, fields_, constructors_, methods_, _content.getCoreNames().getAliasError(), StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getErrType());
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        StandardType.addType(_content.getStandards(), aliasConcurrentError, stdcl_);
    }
    public void thread(LgNamesContent _content, ExecutingBlocks _executingBlocks) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StringList params_ = new StringList();
        StandardClass stdcl_ = new StandardClass(aliasThread, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        StandardMethod method_ = new StandardMethod(aliasStart, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL, new FctThreadStart(aliasIllegalThreadStateException, aliasThread+"."+"."+new MethodId(MethodAccessKind.INSTANCE,aliasStart,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasCurrentThread, params_, aliasThread, false, MethodModifier.STATIC,new FctThreadCurrent(this));
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasThreadCurrentTime, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.STATIC,new FctThreadMillis(this, aliasThread+"."+new MethodId(MethodAccessKind.STATIC,aliasThreadCurrentTime,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasThreadCurrentNanoTime, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.STATIC,new FctThreadNano(this, aliasThread+"."+new MethodId(MethodAccessKind.STATIC,aliasThreadCurrentNanoTime,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasThreadRunnable, params_, aliasRunnable, false, MethodModifier.FINAL,new FctThreadRunnable(this));
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasJoin, params_, _content.getNbAlias().getAliasBoolean(), false, MethodModifier.FINAL,new FctThreadJoin(aliasThread+"."+new MethodId(MethodAccessKind.INSTANCE,aliasJoin,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasInterrupt, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new FctInterrupt());
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasCoverage, params_, StringExpUtil.getPrettyArrayType(aliasEntryText), false, MethodModifier.STATIC,new FctCoverage(aliasEntryText));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasArgs, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC, new FctArgsGet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()));
        method_ = new StandardMethod(aliasArgs, params_, _content.getCoreNames().getAliasVoid() , false, MethodModifier.STATIC, new StringList(custAliasParameters.getAliasThread0Args0()), new FctArgsSet());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasJoinOthers, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new FctThreadJoinOthers(this, aliasThread+"."+new MethodId(MethodAccessKind.STATIC,aliasStart,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasIsAlive, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new FctThreadIsAlive(aliasThread+"."+new MethodId(MethodAccessKind.INSTANCE,aliasIsAlive,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasIsEnded, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new FctThreadIsEnded());
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasEnd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new FctThreadEnd());
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasGetId, params_, _content.getPrimTypes().getAliasPrimLong(), false, MethodModifier.FINAL,new FctThreadGetId(aliasThread+"."+new MethodId(MethodAccessKind.INSTANCE,aliasGetId,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        method_ = new StandardMethod(aliasGetPriority, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new FctThreadGetPrio());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSetPriority, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(custAliasParameters.getAliasThread0SetPriority0()),new FctThreadSetPrio(aliasThread+"."+new MethodId(MethodAccessKind.INSTANCE,aliasSetPriority,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasSleep, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread0Sleep0()),new FctThreadSleep(this, aliasThread+"."+new MethodId(MethodAccessKind.STATIC,aliasSleep,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
//        params_ = new StringList();
//        method_ = new StandardMethod(aliasYield, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new FctThreadYield(this));
//        methods_.add( method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasPrint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread0Print0()),new FctThreadPrint1(this,_executingBlocks,aliasFormatType));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasPrint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread1Print0()),new FctThreadPrint0(this));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasPrint, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread2Print0(),custAliasParameters.getAliasThread2Print1()),new FctThreadPrint2(this,_executingBlocks,aliasFormatType));
        StandardNamedFunction.addFct(methods_, method_);
//        params_ = new StringList(aliasThread);
//        method_ = new StandardMethod(aliasThreadExitHook, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread0ThreadExitHook0()),new FctThreadExitHook(this));
//        methods_.add( method_);
        params_ = new StringList(aliasThread,aliasThread);
        method_ = new StandardMethod(aliasThreadEq, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(custAliasParameters.getAliasThread0ThreadEq0(),custAliasParameters.getAliasThread0ThreadEq1()),new FctThreadEq(this));
        StandardNamedFunction.addFct(methods_, method_);
        StandardConstructor ctor_;
        params_ = new StringList(aliasRunnable);
        ctor_ = new StandardConstructor(params_,false,new StringList(custAliasParameters.getAliasThread0Thread0()),new FctThread(this, aliasThread+"."+new MethodId(MethodAccessKind.INSTANCE,"",params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(constructors_, ctor_);
        StandardType.addType(_content.getStandards(), aliasThread, stdcl_);
    }
    public void future(LgNamesContent _content) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        StandardClass stdcl_ = new StandardClass(aliasFuture, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(_content.getCoreNames().getObjType());
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasFutureWait, params_, _content.getCoreNames().getAliasObject(), false, MethodModifier.FINAL,new FctFutureAttendre(aliasFuture+"."+new MethodId(MethodAccessKind.INSTANCE,aliasFutureWait,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFutureCancel, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new FctFutureCancel(aliasFuture+"."+new MethodId(MethodAccessKind.INSTANCE,aliasFutureCancel,params_).getSignature(_content.getDisplayedStrings())));
        StandardNamedFunction.addFct(methods_, method_);
        StandardType.addType(_content.getStandards(), aliasFuture, stdcl_);
    }
    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content) {
        StringMap<String> stds_ = new StringMap<String>();
        stds_.put(aliasRunnable, runnableType(_keyWords, _content));
        stds_.put(aliasCallable, callableType(_keyWords, _content));
        stds_.put(aliasList, iteratorType(_keyWords, _content)+listType(_keyWords, _content));
        stds_.put(aliasTable, pair(_keyWords, _content)+iteratorTableType(_keyWords, _content)+tableType(_keyWords, _content));
        String content_ = executeType(_keyWords, _content)+annotations(_keyWords, _content)+assertType(_keyWords, _content)+diff(_keyWords, _content)+eltDiff(_keyWords, _content);
        stds_.put(aliasExecute, content_);
        stds_.put(aliasFormatType, formatter(_keyWords, _content));
        return stds_;
    }

    public void messages(AnalysisMessages _mess, StringMap<String> _cust) {
        messages(_mess, defMessages(getUserLg(), getTranslations(), getLanguage()),_cust);
    }
    public void messages(AnalysisMessages _mess, StringMap<String> _util, StringMap<String> _cust) {
        StringMap<String> keys_ = extractMessagesKeys();
        _mess.build(_util, _cust, keys_);
    }

    public StringMap<String> extractMessagesKeys() {
        TranslationsLg lg_ = lg(getTranslations(), getUserLg(), getLanguage());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.MESSAGES);
        return TranslationsFile.extractKeys(com_);
    }

    public static StringMap<String> defMessages(String _lg, Translations _trs, String _language) {
        TranslationsLg lg_ = lg(_trs, _lg, _language);
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.MESSAGES);
        return TranslationsFile.extractMap(com_);
    }

    public void keyWord(KeyWords _kw, StringMap<String> _cust) {
        keyWord(_kw,defKeywords(getUserLg(), getTranslations(), getLanguage()),_cust);
    }
    public void keyWord(KeyWords _kw,StringMap<String> _util,StringMap<String> _cust) {
        StringMap<String> keys_ = extractKeywordsKeys();
        _kw.build(_util, _cust,keys_);
    }
    public StringMap<String> extractKeywordsKeys() {
        TranslationsLg lg_ = lg(getTranslations(), getUserLg(), getLanguage());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.KEYWORDS);
        return TranslationsFile.extractKeys(com_);
    }

    public static StringMap<String> defKeywords(String _lg, Translations _trs, String _language) {
        TranslationsLg lg_ = lg(_trs, _lg, _language);
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.KEYWORDS);
        return TranslationsFile.extractMap(com_);
    }
    public void otherAlias(LgNamesContent _content, StringMap<String> _cust) {
        StringMap<String> keys_ = extractAliasesKeys();
        StringMap<String> exp_ = defAliases(getUserLg(), getTranslations(), getLanguage());
        otherAlias(_content,exp_,_cust,keys_);
    }

    private void otherAlias(LgNamesContent _content,StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        _content.build(_util, _cust,_mapping);
        build(_util,_cust,_mapping);
    }
    public StringMap<String> extractAliasesKeys() {
        TranslationsLg lg_ = lg(getTranslations(), getUserLg(), getLanguage());
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.TYPES);
        return TranslationsFile.extractKeys(com_);
    }

    public static StringMap<String> defAliases(String _lg, Translations _trs, String _language) {
        TranslationsLg lg_ = lg(_trs, _lg, _language);
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.TYPES);
        return TranslationsFile.extractMap(com_);
    }
    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasIllegalThreadStateException(LgNamesContent.get(_util,_cust,_mapping.getVal(ILLEGAL_THREAD_STATE_EXCEPTION)));
        setAliasFileGetLength(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_GET_LENGTH)));
        setAliasAtomicInteger(LgNamesContent.get(_util,_cust,_mapping.getVal(ATOMIC_INTEGER)));
        setAliasCurrentThread(LgNamesContent.get(_util,_cust,_mapping.getVal(CURRENT_THREAD)));
        setAliasFormatType(LgNamesContent.get(_util,_cust,_mapping.getVal(FORMAT_TYPE)));
        setAliasAtomicBoolean(LgNamesContent.get(_util,_cust,_mapping.getVal(ATOMIC_BOOLEAN)));
        setAliasAtomicRef(LgNamesContent.get(_util,_cust,_mapping.getVal(ATOMIC_REF)));
        setAliasSetAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_ATOMIC)));
        setAliasFileIsDirectory(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_IS_DIRECTORY)));
        setAliasFileGetParentPath(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_GET_PARENT_PATH)));
        setAliasAtomicLong(LgNamesContent.get(_util,_cust,_mapping.getVal(ATOMIC_LONG)));
        setAliasFileAbsolutePath(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_ABSOLUTE_PATH)));
        setAliasFileLastModif(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_LAST_MODIF)));
        setAliasFileGetName(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_GET_NAME)));
        setAliasGetAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_ATOMIC)));
        setAliasLazySetAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(LAZY_SET_ATOMIC)));
        setAliasCompareAndSetAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPARE_AND_SET_ATOMIC)));
        setAliasGetAndAddAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_AND_ADD_ATOMIC)));
        setAliasAddAndGetAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(ADD_AND_GET_ATOMIC)));
        setAliasGetAndIncrementAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_AND_INCREMENT_ATOMIC)));
        setAliasIncrementAndGetAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(INCREMENT_AND_GET_ATOMIC)));
        setAliasGetAndDecrementAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_AND_DECREMENT_ATOMIC)));
        setAliasDecrementAndGetAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(DECREMENT_AND_GET_ATOMIC)));
        setAliasGetAndSetAtomic(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_AND_SET_ATOMIC)));
//        setAliasReentrantLock(LgNamesContent.get(_util,_cust,_mapping.getVal(REENTRANT_LOCK)));
        setAliasJoinOthers(LgNamesContent.get(_util,_cust,_mapping.getVal(JOIN_OTHERS)));
        setAliasFileIsFile(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_IS_FILE)));
        setAliasFileRoots(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_ROOTS)));
//        setAliasThreadExitHook(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_EXIT_HOOK)));
        setAliasAppendToFile(LgNamesContent.get(_util,_cust,_mapping.getVal(APPEND_TO_FILE)));
        setAliasThreadCurrentTime(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_CURRENT_TIME)));
        setAliasThreadRunnable(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_RUNNABLE)));
        setAliasThreadCurrentNanoTime(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_CURRENT_NANO_TIME)));
        setAliasSetPriority(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_PRIORITY)));
        setAliasFileListFiles(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_LIST_FILES)));
        setAliasGetPriority(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_PRIORITY)));
//        setAliasIsHeldByCurrentThread(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_HELD_BY_CURRENT_THREAD)));
        setAliasFileListDirectories(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_LIST_DIRECTORIES)));
        setAliasLengthItrTa(LgNamesContent.get(_util,_cust,_mapping.getVal(LENGTH_ITR_TA)));
        setAliasExecutedTestAnnotations(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTED_TEST_ANNOTATIONS)));
        setAliasPairVarFirst(LgNamesContent.get(_util,_cust,_mapping.getVal(PAIR_VAR_FIRST)));
        setAliasIndexItrLi(LgNamesContent.get(_util,_cust,_mapping.getVal(INDEX_ITR_LI)));
        setAliasListIterTable(LgNamesContent.get(_util,_cust,_mapping.getVal(LIST_ITER_TABLE)));
        setAliasDifference(LgNamesContent.get(_util,_cust,_mapping.getVal(DIFFERENCE)));
        setAliasEltDifference(LgNamesContent.get(_util,_cust,_mapping.getVal(ELT_DIFFERENCE)));
        setAliasIndex(LgNamesContent.get(_util,_cust,_mapping.getVal(INDEX)));
        setAliasTableVarSecond(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_VAR_SECOND)));
        setAliasIterTaVarFirst(LgNamesContent.get(_util,_cust,_mapping.getVal(ITER_TA_VAR_FIRST)));
        setAliasExecutedTestBefore(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTED_TEST_BEFORE)));
        setAliasCustIteratorVar(LgNamesContent.get(_util,_cust,_mapping.getVal(CUST_ITERATOR_VAR)));
        setAliasGetSecondTa(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_SECOND_TA)));
        setAliasExecutedTestTest(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTED_TEST_TEST)));
        setAliasExecutedTestMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTED_TEST_METHOD)));
        setAliasExecutedTestAfter(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTED_TEST_AFTER)));
        setAliasCustIterator(LgNamesContent.get(_util,_cust,_mapping.getVal(CUST_ITERATOR)));
        setAliasListClear(LgNamesContent.get(_util,_cust,_mapping.getVal(LIST_CLEAR)));
        setAliasGetFirstTa(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_FIRST_TA)));
        setAliasSetSecondTa(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_SECOND_TA)));
        setAliasFileMakeDirs(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_MAKE_DIRS)));
        setAliasFileZippedBin(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_ZIPPED_BIN)));
        setAliasFileZippedBinArray(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_ZIPPED_BIN_ARRAY)));
        setAliasFileZippedText(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_ZIPPED_TEXT)));
        setAliasFileZipBin(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_ZIP_BIN)));
        setAliasFileZipBinArray(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_ZIP_BIN_ARRAY)));
        setAliasFileZipText(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_ZIP_TEXT)));
        setAliasTableStringObject(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_STRING_OBJECT)));
        setAliasTableConcKeys(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_KEYS)));
        setAliasTableConcValues(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_VALUES)));
        setAliasTableConcHasKey(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_HAS_KEY)));
        setAliasTableConcHasValue(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_HAS_VALUE)));
        setAliasTableConcGet(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_GET)));
        setAliasTableConcClear(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_CLEAR)));
        setAliasTableConcPutAll(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_PUT_ALL)));
        setAliasTableConcPut(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_PUT)));
        setAliasTableConcPutAbs(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_PUT_ABS)));
        setAliasTableConcRemove(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_REMOVE)));
        setAliasTableConcReplace(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_REPLACE)));
        setAliasTableConcPairs(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_PAIRS)));
        setAliasTableConcEmpty(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_EMPTY)));
        setAliasTableConcSize(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_CONC_SIZE)));
        setAliasEntryStringObject(LgNamesContent.get(_util,_cust,_mapping.getVal(ENTRY_STRING_OBJECT)));
        setAliasTableEntryKey(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_ENTRY_KEY)));
        setAliasTableEntryValue(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_ENTRY_VALUE)));
        setAliasTableEntryOwner(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_ENTRY_OWNER)));
        setAliasEntryBinary(LgNamesContent.get(_util,_cust,_mapping.getVal(ENTRY_BINARY)));
        setAliasEntryText(LgNamesContent.get(_util,_cust,_mapping.getVal(ENTRY_TEXT)));
        setAliasEntryName(LgNamesContent.get(_util,_cust,_mapping.getVal(ENTRY_NAME)));
        setAliasEntryValue(LgNamesContent.get(_util,_cust,_mapping.getVal(ENTRY_VALUE)));
        setAliasEntryTime(LgNamesContent.get(_util,_cust,_mapping.getVal(ENTRY_TIME)));
        setAliasFileIsAbsolute(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_IS_ABSOLUTE)));
        setAliasFileReadBin(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_READ_BIN)));
        setAliasFileWriteBin(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_WRITE_BIN)));
        setAliasFileDelete(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_DELETE)));
        setAliasFileDir(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_DIR)));
        setAliasFileRename(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE_RENAME)));
        setAliasCustIterTable(LgNamesContent.get(_util,_cust,_mapping.getVal(CUST_ITER_TABLE)));
        setAliasTableVarFirst(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE_VAR_FIRST)));
        setAliasSetSecond(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_SECOND)));
        setAliasIndexItrTa(LgNamesContent.get(_util,_cust,_mapping.getVal(INDEX_ITR_TA)));
        setAliasIterTaVarSecond(LgNamesContent.get(_util,_cust,_mapping.getVal(ITER_TA_VAR_SECOND)));
        setAliasLengthItrLi(LgNamesContent.get(_util,_cust,_mapping.getVal(LENGTH_ITR_LI)));
        setAliasSetFirstTa(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_FIRST_TA)));
        setAliasExecutedTest(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTED_TEST)));
        setAliasParameters(LgNamesContent.get(_util,_cust,_mapping.getVal(PARAMETERS)));
        setAliasTestException(LgNamesContent.get(_util,_cust,_mapping.getVal(TEST_EXCEPTION)));
        setAliasTestNullException(LgNamesContent.get(_util,_cust,_mapping.getVal(TEST_NULL_EXCEPTION)));
        setAliasPairVarSecond(LgNamesContent.get(_util,_cust,_mapping.getVal(PAIR_VAR_SECOND)));
        setAliasExecuteExecute(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE_EXECUTE)));
        setAliasExecuteSetupNoException(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE_SETUP_NO_EXCEPTION)));
        setAliasAssertAssert(LgNamesContent.get(_util,_cust,_mapping.getVal(ASSERT_ASSERT)));
        setAliasAssertAssertNull(LgNamesContent.get(_util,_cust,_mapping.getVal(ASSERT_ASSERT_NULL)));
        setAliasAssertAssertNotNull(LgNamesContent.get(_util,_cust,_mapping.getVal(ASSERT_ASSERT_NOT_NULL)));
        setAliasDifferenceFoundNull(LgNamesContent.get(_util,_cust,_mapping.getVal(DIFFERENCE_FOUND_NULL)));
        setAliasResultSuccess(LgNamesContent.get(_util,_cust,_mapping.getVal(RESULT_SUCCESS)));
//        setAliasInfoTestCurrentClass(LgNamesContent.get(_util,_cust,_mapping.getVal(INFO_TEST_CURRENT_CLASS)));
        setAliasExecuteConvert(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE_CONVERT)));
        setAliasConcurrentError(LgNamesContent.get(_util,_cust,_mapping.getVal(CONCURRENT_ERROR)));
        setAliasResultFailMessage(LgNamesContent.get(_util,_cust,_mapping.getVal(RESULT_FAIL_MESSAGE)));
        setAliasAssertAssertTrue(LgNamesContent.get(_util,_cust,_mapping.getVal(ASSERT_ASSERT_TRUE)));
        setAliasAssertAssertNot(LgNamesContent.get(_util,_cust,_mapping.getVal(ASSERT_NOT)));
        setAliasAssertAssertNotSame(LgNamesContent.get(_util,_cust,_mapping.getVal(ASSERT_NOT_SAME)));
        setAliasAssertAssertFalse(LgNamesContent.get(_util,_cust,_mapping.getVal(ASSERT_ASSERT_FALSE)));
        setAliasInfoTestCurrentMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(INFO_TEST_CURRENT_METHOD)));
        setAliasResultParams(LgNamesContent.get(_util,_cust,_mapping.getVal(RESULT_PARAMS)));
        setAliasParametersMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(PARAMETERS_METHOD)));
        setAliasExecuteSetupError(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE_SETUP_ERROR)));
        setAliasExecuteGroupClass(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE_GROUP_CLASS)));
        setAliasExecuteGroupClassMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE_GROUP_CLASS_METHOD)));
        setAliasExecuteFlat(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE_FLAT)));
        setAliasExecuteLaunch(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE_LAUNCH)));
        setAliasAssertAssertSame(LgNamesContent.get(_util,_cust,_mapping.getVal(ASSERT_ASSERT_SAME)));
        setAliasDifferenceFound(LgNamesContent.get(_util,_cust,_mapping.getVal(DIFFERENCE_FOUND)));
        setAliasDifferenceFoundNotTrue(LgNamesContent.get(_util,_cust,_mapping.getVal(DIFFERENCE_FOUND_NOT_TRUE)));
        setAliasParametersLocation(LgNamesContent.get(_util,_cust,_mapping.getVal(PARAMETERS_LOCATION)));
        setAliasInfoTestCount(LgNamesContent.get(_util,_cust,_mapping.getVal(INFO_TEST_COUNT)));
        setAliasInfoTestCalls(LgNamesContent.get(_util,_cust,_mapping.getVal(INFO_TEST_CALLS)));
        setAliasResultTime(LgNamesContent.get(_util,_cust,_mapping.getVal(RESULT_TIME)));
        setAliasInfoTestDone(LgNamesContent.get(_util,_cust,_mapping.getVal(INFO_TEST_DONE)));
        setAliasInfoTestNbThreads(LgNamesContent.get(_util,_cust,_mapping.getVal(INFO_TEST_NB_THREADS)));
        setAliasDifferenceStackDiff(LgNamesContent.get(_util,_cust,_mapping.getVal(DIFFERENCE_STACK_DIFF)));
        setAliasExecuteTests(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE_TESTS)));
        setAliasDifferenceExpected(LgNamesContent.get(_util,_cust,_mapping.getVal(DIFFERENCE_EXPECTED)));
        setAliasInfoTestCurrentParams(LgNamesContent.get(_util,_cust,_mapping.getVal(INFO_TEST_CURRENT_PARAMS)));
        setAliasResultArgs(LgNamesContent.get(_util,_cust,_mapping.getVal(RESULT_ARGS)));
        setAliasResultContainer(LgNamesContent.get(_util,_cust,_mapping.getVal(RESULT_CONTAINER)));
        setAliasResultExecuted(LgNamesContent.get(_util,_cust,_mapping.getVal(RESULT_EXECUTED)));
        setAliasRunnable(LgNamesContent.get(_util,_cust,_mapping.getVal(RUNNABLE)));
        setAliasCallable(LgNamesContent.get(_util,_cust,_mapping.getVal(CALLABLE)));
        setAliasThread(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD)));
        setAliasThreadSet(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_SET)));
        setAliasExecutorServiceBase(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTOR_SERVICE_BASE)));
        setAliasScheduledExecutorService(LgNamesContent.get(_util,_cust,_mapping.getVal(SCHEDULED_EXECUTOR_SERVICE)));
        setAliasExecutorService(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTOR_SERVICE)));
        setAliasExecutorServiceShutdown(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTOR_SERVICE_SHUTDOWN)));
        setAliasExecutorServiceStopped(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTOR_SERVICE_STOPPED)));
        setAliasExecutorServiceExecute(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTOR_SERVICE_EXECUTE)));
        setAliasExecutorServiceSubmit(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTOR_SERVICE_SUBMIT)));
        setAliasExecutorServiceScheduleMillis(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTOR_SERVICE_SCHEDULE_MILLIS)));
        setAliasExecutorServiceScheduleNanos(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTOR_SERVICE_SCHEDULE_NANOS)));
        setAliasRunnableImplicit0Runner(LgNamesContent.get(_util,_cust,_mapping.getVal(RUNNABLE_IMPLICIT_0_RUNNER)));
        setAliasCallableImplicit0Runner(LgNamesContent.get(_util,_cust,_mapping.getVal(CALLABLE_IMPLICIT_0_RUNNER)));
        setAliasFuture(LgNamesContent.get(_util,_cust,_mapping.getVal(FUTURE)));
        setAliasFutureWait(LgNamesContent.get(_util,_cust,_mapping.getVal(FUTURE_WAIT)));
        setAliasFutureCancel(LgNamesContent.get(_util,_cust,_mapping.getVal(FUTURE_CANCEL)));
        setAliasThreadSetAll(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_SET_ALL)));
        setAliasThreadSetAdd(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_SET_ADD)));
        setAliasThreadSetContains(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_SET_CONTAINS)));
        setAliasThreadSetRemove(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_SET_REMOVE)));
        setAliasThreadSetSnapshot(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_SET_SNAPSHOT)));
        setAliasStart(LgNamesContent.get(_util,_cust,_mapping.getVal(START)));
        setAliasThreadEq(LgNamesContent.get(_util,_cust,_mapping.getVal(THREAD_EQ)));
        setAliasJoin(LgNamesContent.get(_util,_cust,_mapping.getVal(JOIN)));
        setAliasInterrupt(LgNamesContent.get(_util,_cust,_mapping.getVal(INTERRUPT)));
        setAliasArgs(LgNamesContent.get(_util,_cust,_mapping.getVal(ARGS)));
        setAliasCoverage(LgNamesContent.get(_util,_cust,_mapping.getVal(COVERAGE)));
        setAliasRun(LgNamesContent.get(_util,_cust,_mapping.getVal(RUN)));
        setAliasCallableMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(CALLABLE_METHOD)));
        setAliasLengthLi(LgNamesContent.get(_util,_cust,_mapping.getVal(LENGTH_LI)));
        setAliasCustPair(LgNamesContent.get(_util,_cust,_mapping.getVal(CUST_PAIR)));
        setAliasListTa(LgNamesContent.get(_util,_cust,_mapping.getVal(LIST_TA)));
        setAliasGetId(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_ID)));
        setAliasIsAlive(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_ALIVE)));
        setAliasIsEnded(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_ENDED)));
        setAliasEnd(LgNamesContent.get(_util,_cust,_mapping.getVal(END)));
        setAliasPrint(LgNamesContent.get(_util,_cust,_mapping.getVal(PRINT)));
        setAliasListItr(LgNamesContent.get(_util,_cust,_mapping.getVal(LIST_ITR)));
        setAliasRemoveLi(LgNamesContent.get(_util,_cust,_mapping.getVal(REMOVE_LI)));
        setAliasArrayLi(LgNamesContent.get(_util,_cust,_mapping.getVal(ARRAY_LI)));
        setAliasFirst(LgNamesContent.get(_util,_cust,_mapping.getVal(FIRST)));
        setAliasSleep(LgNamesContent.get(_util,_cust,_mapping.getVal(SLEEP)));
        setAliasFile(LgNamesContent.get(_util,_cust,_mapping.getVal(FILE)));
        setAliasRead(LgNamesContent.get(_util,_cust,_mapping.getVal(READ)));
        setAliasList(LgNamesContent.get(_util,_cust,_mapping.getVal(LIST)));
//        setAliasUnlock(LgNamesContent.get(_util,_cust,_mapping.getVal(UNLOCK)));
        setAliasSizeLi(LgNamesContent.get(_util,_cust,_mapping.getVal(SIZE_LI)));
        setAliasListVar(LgNamesContent.get(_util,_cust,_mapping.getVal(LIST_VAR)));
        setAliasCallableVar(LgNamesContent.get(_util,_cust,_mapping.getVal(CALLABLE_VAR)));
        setAliasSecond(LgNamesContent.get(_util,_cust,_mapping.getVal(SECOND)));
        setAliasAddLi(LgNamesContent.get(_util,_cust,_mapping.getVal(ADD_LI)));
//        setAliasYield(LgNamesContent.get(_util,_cust,_mapping.getVal(YIELD)));
        setAliasSetFirst(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_FIRST)));
//        setAliasLock(LgNamesContent.get(_util,_cust,_mapping.getVal(LOCK)));
        setAliasWrite(LgNamesContent.get(_util,_cust,_mapping.getVal(WRITE)));
        setAliasTable(LgNamesContent.get(_util,_cust,_mapping.getVal(TABLE)));
        setAliasResult(LgNamesContent.get(_util,_cust,_mapping.getVal(RESULT)));
        setAliasAddTa(LgNamesContent.get(_util,_cust,_mapping.getVal(ADD_TA)));
        setAliasInfoTest(LgNamesContent.get(_util,_cust,_mapping.getVal(INFO_TEST)));
        setAliasRemoveTa(LgNamesContent.get(_util,_cust,_mapping.getVal(REMOVE_TA)));
        setAliasBefore(LgNamesContent.get(_util,_cust,_mapping.getVal(BEFORE)));
        setAliasGetTa(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_TA)));
        setAliasTest(LgNamesContent.get(_util,_cust,_mapping.getVal(TEST)));
        setAliasArgsTest(LgNamesContent.get(_util,_cust,_mapping.getVal(ARGS_TEST)));
        setAliasArgsTestArgsValue(LgNamesContent.get(_util,_cust,_mapping.getVal(ARGS_TEST_ARGS_VALUE)));
        setAliasExecute(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTE)));
        setAliasExecutedTestArgsAnnot(LgNamesContent.get(_util,_cust,_mapping.getVal(EXECUTED_TEST_ARGS_ANNOT)));
        setAliasAfter(LgNamesContent.get(_util,_cust,_mapping.getVal(AFTER)));
        setAliasSizeTa(LgNamesContent.get(_util,_cust,_mapping.getVal(SIZE_TA)));
        setAliasAssert(LgNamesContent.get(_util,_cust,_mapping.getVal(ASSERT)));
        stringViewReplaceAliases.build(_util, _cust, _mapping);
        mathAdvAliases.build(_util, _cust, _mapping);
        custAliasParameters.build(_util,_cust,_mapping);
    }
    public static TranslationsFile en() {
        TranslationsFile en_ = new TranslationsFile();
        en(en_);
        LgNamesContent.en(en_);
        return en_;
    }
    public static void en(TranslationsFile _en){
        _en.add(THREAD,"Thread=$core.Thread");
        _en.add(THREAD_SET,"ThreadSet=$core.ThreadSet");
        _en.add(EXECUTOR_SERVICE,"ExecutorService=$core.ExecutorService");
        _en.add(EXECUTOR_SERVICE_BASE,"ExecutorServiceBase=$core.ServiceBaseExecution");
        _en.add(SCHEDULED_EXECUTOR_SERVICE,"ScheduledExecutorService=$core.ServiceScheduledExecution");
        _en.add(FUTURE,"Future=$core.Future");
        _en.add(ATOMIC_BOOLEAN,"AtomicBoolean=$core.AtomicBoolean");
        _en.add(ATOMIC_INTEGER,"AtomicInteger=$core.AtomicInteger");
        _en.add(ATOMIC_LONG,"AtomicLong=$core.AtomicLong");
        _en.add(ATOMIC_REF,"AtomicRef=$core.AtomicRef");
        _en.add(FILE,"File=$core.File");
        _en.add(ILLEGAL_THREAD_STATE_EXCEPTION,"IllegalThreadStateException=$core.IllegalThreadState");
        _en.add(CUST_ITERATOR,"CustIterator=$core.CustIterator");
        _en.add(LIST,"List=$core.List");
        _en.add(RUNNABLE,"Runnable=$core.Runnable");
        _en.add(CALLABLE,"Callable=$core.Callable");
        _en.add(FORMAT_TYPE,"FormatType=$core.Formatting");
        _en.add(CUST_PAIR,"CustPair=$core.PairImpl");
        _en.add(CUST_ITER_TABLE,"CustIterTable=$core.CustIteratorTable");
        _en.add(TABLE,"Table=$core.Table");
        _en.add(EXECUTE,"Execute=$core.Execute");
        _en.add(INFO_TEST,"InfoTest=$core.InfoTest");
        _en.add(EXECUTED_TEST,"ExecutedTest=$core.ExecutedTest");
        _en.add(RESULT,"Result=$core.Result");
        _en.add(BEFORE,"Before=$core.Before");
        _en.add(AFTER,"After=$core.After");
        _en.add(PARAMETERS,"Parameters=$core.Parameters");
        _en.add(TEST,"Test=$core.Test");
        _en.add(ARGS_TEST,"ArgsTest=$core.ArgsTest");
        _en.add(ASSERT,"Assert=$core.Assert");
        _en.add(DIFFERENCE,"Difference=$core.Difference");
        _en.add(ELT_DIFFERENCE,"EltDifference=$core.EltDifference");
        _en.add(CONCURRENT_ERROR,"ConcurrentError=$core.ConcurrentError");
        _en.add(ENTRY_BINARY,"EntryBinary=$core.BinaryEntry");
        _en.add(ENTRY_TEXT,"EntryText=$core.TextEntry");
        _en.add(ENTRY_STRING_OBJECT,"EntryStringObject=$core.StringObjectEntry");
        _en.add(TABLE_STRING_OBJECT,"TableStringObject=$core.StringObjectTable");
        _en.add(START,"Start=start");
        _en.add(THREAD_EQ,"ThreadEq=tEq");
        _en.add(THREAD_RUNNABLE,"ThreadRunnable=runnable");
        _en.add(THREAD_CURRENT_TIME,"ThreadCurrentTime=currentTime");
        _en.add(THREAD_CURRENT_NANO_TIME,"ThreadCurrentNanoTime=currentTimeNanos");
        _en.add(IS_ALIVE,"IsAlive=isAlive");
        _en.add(IS_ENDED,"IsEnded=isEnded");
        _en.add(END,"End=end");
        _en.add(CURRENT_THREAD,"CurrentThread=currentThread");
        _en.add(JOIN,"Join=join");
        _en.add(INTERRUPT,"Interrupt=interrupt");
        _en.add(ARGS,"Args=args");
        _en.add(COVERAGE,"Coverage=coverage");
        _en.add(JOIN_OTHERS,"JoinOthers=joinOthers");
        _en.add(GET_ID,"GetId=getId");
        _en.add(GET_PRIORITY,"GetPriority=getPriority");
        _en.add(SET_PRIORITY,"SetPriority=setPriority");
        _en.add(SLEEP,"Sleep=sleep");
        _en.add(PRINT,"Print=print");
        _en.add(EXECUTOR_SERVICE_EXECUTE,"ExecutorServiceExecute=execute");
        _en.add(EXECUTOR_SERVICE_SUBMIT,"ExecutorServiceSubmit=submit");
        _en.add(EXECUTOR_SERVICE_SHUTDOWN,"ExecutorServiceShutdown=shutdown");
        _en.add(EXECUTOR_SERVICE_STOPPED,"ExecutorServiceStopped=stopped");
        _en.add(EXECUTOR_SERVICE_SCHEDULE_MILLIS,"ExecutorServiceScheduleMillis=scheduleMillis");
        _en.add(EXECUTOR_SERVICE_SCHEDULE_NANOS,"ExecutorServiceScheduleNanos=scheduleNanos");
        _en.add(FUTURE_WAIT,"FutureWait=wait");
        _en.add(FUTURE_CANCEL,"FutureCancel=cancel");
        _en.add(THREAD_SET_ADD,"ThreadSetAdd=add");
        _en.add(THREAD_SET_ALL,"ThreadSetAll=all");
        _en.add(THREAD_SET_CONTAINS,"ThreadSetContains=contains");
        _en.add(THREAD_SET_REMOVE,"ThreadSetRemove=remove");
        _en.add(THREAD_SET_SNAPSHOT,"ThreadSetSnapshot=snapshot");
        _en.add(RUN,"Run=run");
        _en.add(CALLABLE_METHOD,"CallableMethod=call");
        _en.add(GET_ATOMIC,"GetAtomic=getValue");
        _en.add(SET_ATOMIC,"SetAtomic=setValue");
        _en.add(COMPARE_AND_SET_ATOMIC,"CompareAndSetAtomic=compareAndSet");
        _en.add(GET_AND_SET_ATOMIC,"GetAndSetAtomic=getAndSet");
        _en.add(LAZY_SET_ATOMIC,"LazySetAtomic=lazySet");
        _en.add(ADD_AND_GET_ATOMIC,"AddAndGetAtomic=addAndGet");
        _en.add(GET_AND_ADD_ATOMIC,"GetAndAddAtomic=getAndAdd");
        _en.add(INCREMENT_AND_GET_ATOMIC,"IncrementAndGetAtomic=incrementAndGet");
        _en.add(GET_AND_INCREMENT_ATOMIC,"GetAndIncrementAtomic=getAndIncrement");
        _en.add(DECREMENT_AND_GET_ATOMIC,"DecrementAndGetAtomic=decrementAndGet");
        _en.add(GET_AND_DECREMENT_ATOMIC,"GetAndDecrementAtomic=getAndDecrement");
        _en.add(READ,"Read=read");
        _en.add(WRITE,"Write=write");
        _en.add(APPEND_TO_FILE,"AppendToFile=appendToFile");
        _en.add(FILE_ABSOLUTE_PATH,"FileAbsolutePath=absolute");
        _en.add(FILE_GET_LENGTH,"FileGetLength=length");
        _en.add(FILE_GET_NAME,"FileGetName=name");
        _en.add(FILE_GET_PARENT_PATH,"FileGetParentPath=parentPath");
        _en.add(FILE_IS_DIRECTORY,"FileIsDirectory=isDirectory");
        _en.add(FILE_IS_FILE,"FileIsFile=isFile");
        _en.add(FILE_ROOTS,"FileRoots=roots");
        _en.add(FILE_IS_ABSOLUTE,"FileIsAbsolute=isAbsolute");
        _en.add(FILE_READ_BIN,"FileReadBin=readBin");
        _en.add(FILE_WRITE_BIN,"FileWriteBin=writeBin");
        _en.add(FILE_DELETE,"FileDelete=delete");
        _en.add(FILE_DIR,"FileDir=dir");
        _en.add(FILE_RENAME,"FileRename=renameTo");
        _en.add(FILE_LAST_MODIF,"FileLastModif=lastModification");
        _en.add(FILE_LIST_DIRECTORIES,"FileListDirectories=directories");
        _en.add(FILE_LIST_FILES,"FileListFiles=files");
        _en.add(FILE_ZIP_BIN,"FileZipBin=zipBinary");
        _en.add(FILE_ZIP_BIN_ARRAY,"FileZipBinArray=zipBinaryArray");
        _en.add(FILE_ZIP_TEXT,"FileZipText=zipText");
        _en.add(FILE_ZIPPED_BIN,"FileZippedBin=zippedBinary");
        _en.add(FILE_ZIPPED_BIN_ARRAY,"FileZippedBinArray=zippedBinaryArray");
        _en.add(FILE_ZIPPED_TEXT,"FileZippedText=zippedText");
        _en.add(FILE_MAKE_DIRS,"FileMakeDirs=makeDirectories");
        _en.add(TABLE_CONC_PAIRS,"TableConcPairs=pairs");
        _en.add(TABLE_CONC_EMPTY,"TableConcEmpty=emp");
        _en.add(TABLE_CONC_SIZE,"TableConcSize=size");
        _en.add(TABLE_CONC_KEYS,"TableConcKeys=keys");
        _en.add(TABLE_CONC_VALUES,"TableConcValues=values");
        _en.add(TABLE_CONC_HAS_KEY,"TableConcHasKey=hasKey");
        _en.add(TABLE_CONC_HAS_VALUE,"TableConcHasValue=hasValue");
        _en.add(TABLE_CONC_GET,"TableConcGet=get");
        _en.add(TABLE_CONC_REMOVE,"TableConcRemove=remove");
        _en.add(TABLE_CONC_PUT,"TableConcPut=put");
        _en.add(TABLE_CONC_PUT_ABS,"TableConcPutAbs=putAbs");
        _en.add(TABLE_CONC_REPLACE,"TableConcReplace=replace");
        _en.add(TABLE_CONC_CLEAR,"TableConcClear=clear");
        _en.add(TABLE_CONC_PUT_ALL,"TableConcPutAll=putAll");
        _en.add(TABLE_ENTRY_KEY,"TableEntryKey=key");
        _en.add(TABLE_ENTRY_VALUE,"TableEntryValue=value");
        _en.add(TABLE_ENTRY_OWNER,"TableEntryOwner=owner");
        _en.add(ENTRY_NAME,"EntryName=name");
        _en.add(ENTRY_TIME,"EntryTime=time");
        _en.add(ENTRY_VALUE,"EntryValue=value");
        _en.add(ADD_LI,"AddLi=add");
        _en.add(SIZE_LI,"SizeLi=size");
        _en.add(REMOVE_LI,"RemoveLi=remove");
        _en.add(LIST_CLEAR,"ListClear=clear");
        _en.add(SET_FIRST,"SetFirst=setFirst");
        _en.add(SET_SECOND,"SetSecond=setSecond");
        _en.add(GET_FIRST_TA,"GetFirstTa=getFirst");
        _en.add(GET_SECOND_TA,"GetSecondTa=getSecond");
        _en.add(SET_FIRST_TA,"SetFirstTa=setFirst");
        _en.add(SET_SECOND_TA,"SetSecondTa=setSecond");
        _en.add(ADD_TA,"AddTa=add");
        _en.add(REMOVE_TA,"RemoveTa=remove");
        _en.add(SIZE_TA,"SizeTa=size");
        _en.add(GET_TA,"GetTa=get");
        _en.add(EXECUTE_TESTS,"ExecuteTests=tests");
        _en.add(EXECUTE_CONVERT,"ExecuteConvert=convert");
        _en.add(EXECUTE_SETUP_ERROR,"ExecuteSetupError=setupError");
        _en.add(EXECUTE_SETUP_NO_EXCEPTION,"ExecuteSetupNoException=setupNoException");
        _en.add(EXECUTE_EXECUTE,"ExecuteExecute=execute");
        _en.add(EXECUTE_GROUP_CLASS,"ExecuteGroupClass=groupClass");
        _en.add(EXECUTE_GROUP_CLASS_METHOD,"ExecuteGroupClassMethod=groupClassMethod");
        _en.add(EXECUTE_FLAT,"ExecuteFlat=flat");
        _en.add(EXECUTE_LAUNCH,"ExecuteLaunch=launch");
        _en.add(PARAMETERS_LOCATION,"ParametersLocation=location");
        _en.add(PARAMETERS_METHOD,"ParametersMethod=method");
        _en.add(TEST_EXCEPTION,"TestException=exception");
        _en.add(TEST_NULL_EXCEPTION,"TestNullException=nullException");
        _en.add(ARGS_TEST_ARGS_VALUE,"ArgsTestArgsValue=argsValue");
        _en.add(ASSERT_ASSERT,"AssertAssert=assert");
        _en.add(ASSERT_NOT,"AssertNot=assertNot");
        _en.add(ASSERT_ASSERT_NOT_NULL,"AssertAssertNotNull=assertNotNull");
        _en.add(ASSERT_ASSERT_NULL,"AssertAssertNull=assertNull");
        _en.add(ASSERT_ASSERT_SAME,"AssertAssertSame=assertSame");
        _en.add(ASSERT_NOT_SAME,"AssertNotSame=assertNotSame");
        _en.add(ASSERT_ASSERT_TRUE,"AssertAssertTrue=assertTrue");
        _en.add(ASSERT_ASSERT_FALSE,"AssertAssertFalse=assertFalse");
        _en.add(DIFFERENCE_EXPECTED,"DifferenceExpected=expected");
        _en.add(DIFFERENCE_FOUND,"DifferenceFound=found");
        _en.add(DIFFERENCE_FOUND_NOT_TRUE,"DifferenceFoundNotTrue=foundNotTrue");
        _en.add(DIFFERENCE_FOUND_NULL,"DifferenceFoundNull=foundNull");
        _en.add(DIFFERENCE_STACK_DIFF,"DifferenceStackDiff=stackDiff");
        _en.add(INDEX,"Index=index");
        _en.add(RUNNABLE_IMPLICIT_0_RUNNER,"RunnableImplicit0Runner=runner");
        _en.add(CALLABLE_IMPLICIT_0_RUNNER,"CallableImplicit0Runner=runner");
        _en.add(INFO_TEST_COUNT,"InfoTestCount=count");
        _en.add(INFO_TEST_CALLS,"InfoTestCalls=calls");
        _en.add(INFO_TEST_CURRENT_METHOD,"InfoTestCurrentMethod=currentMethod");
        _en.add(INFO_TEST_DONE,"InfoTestDone=done");
        _en.add(INFO_TEST_NB_THREADS,"InfoTestNbThreads=nbThreads");
        _en.add(RESULT_ARGS,"ResultArgs=args");
        _en.add(RESULT_CONTAINER,"ResultContainer=container");
        _en.add(RESULT_EXECUTED,"ResultExecuted=executed");
        _en.add(INFO_TEST_CURRENT_PARAMS,"InfoTestCurrentParams=currentParams");
        _en.add(RESULT_FAIL_MESSAGE,"ResultFailMessage=failMessage");
        _en.add(RESULT_PARAMS,"ResultParams=params");
        _en.add(RESULT_TIME,"ResultTime=duration");
        _en.add(RESULT_SUCCESS,"ResultSuccess=success");
        _en.add(EXECUTED_TEST_AFTER,"ExecutedTestAfter=after");
        _en.add(EXECUTED_TEST_BEFORE,"ExecutedTestBefore=before");
        _en.add(EXECUTED_TEST_ARGS_ANNOT,"ExecutedTestArgsAnnot=args");
        _en.add(EXECUTED_TEST_ANNOTATIONS,"ExecutedTestAnnotations=annotations");
        _en.add(EXECUTED_TEST_METHOD,"ExecutedTestMethod=method");
        _en.add(EXECUTED_TEST_TEST,"ExecutedTestTest=test");
        _en.add(LIST_ITR,"ListItr=list");
        _en.add(LENGTH_ITR_LI,"LengthItrLi=length");
        _en.add(INDEX_ITR_LI,"IndexItrLi=index");
        _en.add(ARRAY_LI,"ArrayLi=array");
        _en.add(LENGTH_LI,"LengthLi=length");
        _en.add(LIST_ITER_TABLE,"ListIterTable=list");
        _en.add(LENGTH_ITR_TA,"LengthItrTa=length");
        _en.add(INDEX_ITR_TA,"IndexItrTa=index");
        _en.add(FIRST,"First=first");
        _en.add(SECOND,"Second=second");
        _en.add(LIST_TA,"ListTa=list");
        _en.add(LIST_VAR,"ListVar=T");
        _en.add(CALLABLE_VAR,"CallableVar=T");
        _en.add(CUST_ITERATOR_VAR,"CustIteratorVar=T");
        _en.add(TABLE_VAR_FIRST,"TableVarFirst=T");
        _en.add(TABLE_VAR_SECOND,"TableVarSecond=U");
        _en.add(ITER_TA_VAR_FIRST,"IterTaVarFirst=T");
        _en.add(ITER_TA_VAR_SECOND,"IterTaVarSecond=U");
        _en.add(PAIR_VAR_FIRST,"PairVarFirst=T");
        _en.add(PAIR_VAR_SECOND,"PairVarSecond=U");
        StringViewReplaceAliases.en(_en);
        MathAdvAliases.en(_en);
        CustAliasParameters.en(_en);
    }
    public static TranslationsFile fr() {
        TranslationsFile fr_ = new TranslationsFile();
        fr(fr_);
        LgNamesContent.fr(fr_);
        return fr_;
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(THREAD,"Thread=$coeur.Tache");
        _fr.add(THREAD_SET,"ThreadSet=$coeur.EnsTache");
        _fr.add(EXECUTOR_SERVICE,"ExecutorService=$coeur.ServiceExecution");
        _fr.add(EXECUTOR_SERVICE_BASE,"ExecutorServiceBase=$coeur.ServiceBaseExecution");
        _fr.add(SCHEDULED_EXECUTOR_SERVICE,"ScheduledExecutorService=$coeur.ServiceProgrammeExecution");
        _fr.add(FUTURE,"Future=$coeur.Future");
        _fr.add(ATOMIC_BOOLEAN,"AtomicBoolean=$coeur.AtomicBooleen");
        _fr.add(ATOMIC_INTEGER,"AtomicInteger=$coeur.AtomicEntier4");
        _fr.add(ATOMIC_LONG,"AtomicLong=$coeur.AtomicEntier8");
        _fr.add(ATOMIC_REF,"AtomicRef=$coeur.AtomicRef");
        _fr.add(FILE,"File=$coeur.Fichier");
        _fr.add(ILLEGAL_THREAD_STATE_EXCEPTION,"IllegalThreadStateException=$coeur.IllegalEtatTache");
        _fr.add(CUST_ITERATOR,"CustIterator=$coeur.CustIterateur");
        _fr.add(LIST,"List=$coeur.Liste");
        _fr.add(RUNNABLE,"Runnable=$coeur.Executable");
        _fr.add(CALLABLE,"Callable=$coeur.Appelable");
        _fr.add(FORMAT_TYPE,"FormatType=$coeur.Formattage");
        _fr.add(CUST_PAIR,"CustPair=$coeur.PaireImpl");
        _fr.add(CUST_ITER_TABLE,"CustIterTable=$coeur.CustIterateurTable");
        _fr.add(TABLE,"Table=$coeur.Table");
        _fr.add(EXECUTE,"Execute=$coeur.Executer");
        _fr.add(INFO_TEST,"InfoTest=$coeur.InfoTest");
        _fr.add(EXECUTED_TEST,"ExecutedTest=$coeur.TestExecute");
        _fr.add(RESULT,"Result=$coeur.Resultat");
        _fr.add(BEFORE,"Before=$coeur.Avant");
        _fr.add(AFTER,"After=$coeur.Apres");
        _fr.add(PARAMETERS,"Parameters=$coeur.Parametres");
        _fr.add(TEST,"Test=$coeur.Test");
        _fr.add(ARGS_TEST,"ArgsTest=$coeur.ArgsTest");
        _fr.add(ASSERT,"Assert=$coeur.Assertion");
        _fr.add(DIFFERENCE,"Difference=$coeur.Difference");
        _fr.add(ELT_DIFFERENCE,"EltDifference=$coeur.EltDifference");
        _fr.add(CONCURRENT_ERROR,"ConcurrentError=$coeur.ErreurConcurrentielle");
        _fr.add(ENTRY_BINARY,"EntryBinary=$coeur.EltArchBinaire");
        _fr.add(ENTRY_TEXT,"EntryText=$coeur.EltArchTexte");
        _fr.add(ENTRY_STRING_OBJECT,"EntryStringObject=$coeur.EltChaineObjet");
        _fr.add(TABLE_STRING_OBJECT,"TableStringObject=$coeur.TableChaineObjet");
        _fr.add(START,"Start=demarrer");
        _fr.add(THREAD_EQ,"ThreadEq=tEq");
        _fr.add(THREAD_RUNNABLE,"ThreadRunnable=executable");
        _fr.add(THREAD_CURRENT_TIME,"ThreadCurrentTime=instant");
        _fr.add(THREAD_CURRENT_NANO_TIME,"ThreadCurrentNanoTime=instantNanos");
        _fr.add(IS_ALIVE,"IsAlive=estActif");
        _fr.add(IS_ENDED,"IsEnded=estFini");
        _fr.add(END,"End=finir");
        _fr.add(CURRENT_THREAD,"CurrentThread=tacheCourante");
        _fr.add(JOIN,"Join=attendre");
        _fr.add(INTERRUPT,"Interrupt=interrompre");
        _fr.add(ARGS,"Args=args");
        _fr.add(COVERAGE,"Coverage=couverture");
        _fr.add(JOIN_OTHERS,"JoinOthers=attendreAutres");
        _fr.add(GET_ID,"GetId=valId");
        _fr.add(GET_PRIORITY,"GetPriority=valPriorite");
        _fr.add(SET_PRIORITY,"SetPriority=majPriorite");
        _fr.add(SLEEP,"Sleep=dormir");
        _fr.add(PRINT,"Print=afficher");
        _fr.add(EXECUTOR_SERVICE_EXECUTE,"ExecutorServiceExecute=exec");
        _fr.add(EXECUTOR_SERVICE_SUBMIT,"ExecutorServiceSubmit=soumettre");
        _fr.add(EXECUTOR_SERVICE_SHUTDOWN,"ExecutorServiceShutdown=fermer");
        _fr.add(EXECUTOR_SERVICE_STOPPED,"ExecutorServiceStopped=arret");
        _fr.add(EXECUTOR_SERVICE_SCHEDULE_MILLIS,"ExecutorServiceScheduleMillis=programmeMillis");
        _fr.add(EXECUTOR_SERVICE_SCHEDULE_NANOS,"ExecutorServiceScheduleNanos=programmeNanos");
        _fr.add(FUTURE_WAIT,"FutureWait=attendre");
        _fr.add(FUTURE_CANCEL,"FutureCancel=annuler");
        _fr.add(THREAD_SET_ADD,"ThreadSetAdd=ajout");
        _fr.add(THREAD_SET_ALL,"ThreadSetAll=tous");
        _fr.add(THREAD_SET_CONTAINS,"ThreadSetContains=contient");
        _fr.add(THREAD_SET_REMOVE,"ThreadSetRemove=suppr");
        _fr.add(THREAD_SET_SNAPSHOT,"ThreadSetSnapshot=tab");
        _fr.add(RUN,"Run=executer");
        _fr.add(CALLABLE_METHOD,"CallableMethod=appeler");
        _fr.add(GET_ATOMIC,"GetAtomic=valeur");
        _fr.add(SET_ATOMIC,"SetAtomic=majValeur");
        _fr.add(COMPARE_AND_SET_ATOMIC,"CompareAndSetAtomic=compareEtMaj");
        _fr.add(GET_AND_SET_ATOMIC,"GetAndSetAtomic=obtEtMaj");
        _fr.add(LAZY_SET_ATOMIC,"LazySetAtomic=tardMaj");
        _fr.add(ADD_AND_GET_ATOMIC,"AddAndGetAtomic=ajEtObt");
        _fr.add(GET_AND_ADD_ATOMIC,"GetAndAddAtomic=obtEtAj");
        _fr.add(INCREMENT_AND_GET_ATOMIC,"IncrementAndGetAtomic=incrEtObt");
        _fr.add(GET_AND_INCREMENT_ATOMIC,"GetAndIncrementAtomic=obtEtIncr");
        _fr.add(DECREMENT_AND_GET_ATOMIC,"DecrementAndGetAtomic=decrEtObt");
        _fr.add(GET_AND_DECREMENT_ATOMIC,"GetAndDecrementAtomic=obtEtDecr");
        _fr.add(READ,"Read=lire");
        _fr.add(WRITE,"Write=ecrire");
        _fr.add(APPEND_TO_FILE,"AppendToFile=ajouterFinFichier");
        _fr.add(FILE_ABSOLUTE_PATH,"FileAbsolutePath=absolu");
        _fr.add(FILE_GET_LENGTH,"FileGetLength=lg");
        _fr.add(FILE_GET_NAME,"FileGetName=nom");
        _fr.add(FILE_GET_PARENT_PATH,"FileGetParentPath=chParent");
        _fr.add(FILE_IS_DIRECTORY,"FileIsDirectory=estDossier");
        _fr.add(FILE_IS_FILE,"FileIsFile=estFichier");
        _fr.add(FILE_ROOTS,"FileRoots=racines");
        _fr.add(FILE_IS_ABSOLUTE,"FileIsAbsolute=estAbsolu");
        _fr.add(FILE_READ_BIN,"FileReadBin=lireBin");
        _fr.add(FILE_WRITE_BIN,"FileWriteBin=ecrireBin");
        _fr.add(FILE_DELETE,"FileDelete=suppr");
        _fr.add(FILE_DIR,"FileDir=dos");
        _fr.add(FILE_RENAME,"FileRename=renommer");
        _fr.add(FILE_LAST_MODIF,"FileLastModif=derModification");
        _fr.add(FILE_LIST_DIRECTORIES,"FileListDirectories=dossiers");
        _fr.add(FILE_LIST_FILES,"FileListFiles=fichiers");
        _fr.add(FILE_ZIP_BIN,"FileZipBin=archBin");
        _fr.add(FILE_ZIP_BIN_ARRAY,"FileZipBinArray=archBinTab");
        _fr.add(FILE_ZIP_TEXT,"FileZipText=archTexte");
        _fr.add(FILE_ZIPPED_BIN,"FileZippedBin=archiveBin");
        _fr.add(FILE_ZIPPED_BIN_ARRAY,"FileZippedBinArray=archiveBinTab");
        _fr.add(FILE_ZIPPED_TEXT,"FileZippedText=archiveTexte");
        _fr.add(FILE_MAKE_DIRS,"FileMakeDirs=acheminerDossiers");
        _fr.add(TABLE_CONC_PAIRS,"TableConcPairs=paires");
        _fr.add(TABLE_CONC_EMPTY,"TableConcEmpty=vi");
        _fr.add(TABLE_CONC_SIZE,"TableConcSize=taille");
        _fr.add(TABLE_CONC_KEYS,"TableConcKeys=cles");
        _fr.add(TABLE_CONC_VALUES,"TableConcValues=valeurs");
        _fr.add(TABLE_CONC_HAS_KEY,"TableConcHasKey=aCle");
        _fr.add(TABLE_CONC_HAS_VALUE,"TableConcHasValue=aValeur");
        _fr.add(TABLE_CONC_GET,"TableConcGet=val");
        _fr.add(TABLE_CONC_REMOVE,"TableConcRemove=suppr");
        _fr.add(TABLE_CONC_PUT,"TableConcPut=mettre");
        _fr.add(TABLE_CONC_PUT_ABS,"TableConcPutAbs=mettreAbs");
        _fr.add(TABLE_CONC_REPLACE,"TableConcReplace=remplacer");
        _fr.add(TABLE_CONC_CLEAR,"TableConcClear=vider");
        _fr.add(TABLE_CONC_PUT_ALL,"TableConcPutAll=mettreTout");
        _fr.add(TABLE_ENTRY_KEY,"TableEntryKey=cle");
        _fr.add(TABLE_ENTRY_VALUE,"TableEntryValue=valeur");
        _fr.add(TABLE_ENTRY_OWNER,"TableEntryOwner=poss");
        _fr.add(ENTRY_NAME,"EntryName=nom");
        _fr.add(ENTRY_TIME,"EntryTime=temps");
        _fr.add(ENTRY_VALUE,"EntryValue=valeur");
        _fr.add(ADD_LI,"AddLi=ajouter");
        _fr.add(SIZE_LI,"SizeLi=taille");
        _fr.add(REMOVE_LI,"RemoveLi=supprimer");
        _fr.add(LIST_CLEAR,"ListClear=toutSuppr");
        _fr.add(SET_FIRST,"SetFirst=majPremier");
        _fr.add(SET_SECOND,"SetSecond=majDeuxieme");
        _fr.add(GET_FIRST_TA,"GetFirstTa=valPremier");
        _fr.add(GET_SECOND_TA,"GetSecondTa=valDeuxieme");
        _fr.add(SET_FIRST_TA,"SetFirstTa=majPremier");
        _fr.add(SET_SECOND_TA,"SetSecondTa=majDeuxieme");
        _fr.add(ADD_TA,"AddTa=ajouter");
        _fr.add(REMOVE_TA,"RemoveTa=supprimer");
        _fr.add(SIZE_TA,"SizeTa=taille");
        _fr.add(GET_TA,"GetTa=val");
        _fr.add(EXECUTE_TESTS,"ExecuteTests=tests");
        _fr.add(EXECUTE_CONVERT,"ExecuteConvert=convertier");
        _fr.add(EXECUTE_SETUP_ERROR,"ExecuteSetupError=majErreur");
        _fr.add(EXECUTE_SETUP_NO_EXCEPTION,"ExecuteSetupNoException=majSansException");
        _fr.add(EXECUTE_EXECUTE,"ExecuteExecute=executer");
        _fr.add(EXECUTE_GROUP_CLASS,"ExecuteGroupClass=groupClass");
        _fr.add(EXECUTE_GROUP_CLASS_METHOD,"ExecuteGroupClassMethod=groupClassMethod");
        _fr.add(EXECUTE_FLAT,"ExecuteFlat=reunir");
        _fr.add(EXECUTE_LAUNCH,"ExecuteLaunch=lanc");
        _fr.add(PARAMETERS_LOCATION,"ParametersLocation=location");
        _fr.add(PARAMETERS_METHOD,"ParametersMethod=methode");
        _fr.add(TEST_EXCEPTION,"TestException=exception");
        _fr.add(TEST_NULL_EXCEPTION,"TestNullException=nulleException");
        _fr.add(ARGS_TEST_ARGS_VALUE,"ArgsTestArgsValue=argsValeur");
        _fr.add(ASSERT_ASSERT,"AssertAssert=assert");
        _fr.add(ASSERT_NOT,"AssertNot=assertNon");
        _fr.add(ASSERT_ASSERT_NOT_NULL,"AssertAssertNotNull=assertNonNul");
        _fr.add(ASSERT_ASSERT_NULL,"AssertAssertNull=assertNul");
        _fr.add(ASSERT_ASSERT_SAME,"AssertAssertSame=assertMeme");
        _fr.add(ASSERT_NOT_SAME,"AssertNotSame=assertNonMeme");
        _fr.add(ASSERT_ASSERT_TRUE,"AssertAssertTrue=assertVrai");
        _fr.add(ASSERT_ASSERT_FALSE,"AssertAssertFalse=assertFaux");
        _fr.add(DIFFERENCE_EXPECTED,"DifferenceExpected=attendu");
        _fr.add(DIFFERENCE_FOUND,"DifferenceFound=trouve");
        _fr.add(DIFFERENCE_FOUND_NOT_TRUE,"DifferenceFoundNotTrue=trouvePasVrai");
        _fr.add(DIFFERENCE_FOUND_NULL,"DifferenceFoundNull=trouveNull");
        _fr.add(DIFFERENCE_STACK_DIFF,"DifferenceStackDiff=pileDiff");
        _fr.add(INDEX,"Index=indice");
        _fr.add(RUNNABLE_IMPLICIT_0_RUNNER,"RunnableImplicit0Runner=exec");
        _fr.add(CALLABLE_IMPLICIT_0_RUNNER,"CallableImplicit0Runner=exec");
        _fr.add(INFO_TEST_COUNT,"InfoTestCount=nb");
        _fr.add(INFO_TEST_CALLS,"InfoTestCalls=appels");
        _fr.add(INFO_TEST_CURRENT_METHOD,"InfoTestCurrentMethod=methodCourante");
        _fr.add(INFO_TEST_DONE,"InfoTestDone=fait");
        _fr.add(INFO_TEST_NB_THREADS,"InfoTestNbThreads=nbTaches");
        _fr.add(RESULT_ARGS,"ResultArgs=args");
        _fr.add(RESULT_CONTAINER,"ResultContainer=conteneur");
        _fr.add(RESULT_EXECUTED,"ResultExecuted=execute");
        _fr.add(INFO_TEST_CURRENT_PARAMS,"InfoTestCurrentParams=paramsCourants");
        _fr.add(RESULT_FAIL_MESSAGE,"ResultFailMessage=messageErreur");
        _fr.add(RESULT_PARAMS,"ResultParams=params");
        _fr.add(RESULT_TIME,"ResultTime=duree");
        _fr.add(RESULT_SUCCESS,"ResultSuccess=succes");
        _fr.add(EXECUTED_TEST_AFTER,"ExecutedTestAfter=apres");
        _fr.add(EXECUTED_TEST_BEFORE,"ExecutedTestBefore=avant");
        _fr.add(EXECUTED_TEST_ARGS_ANNOT,"ExecutedTestArgsAnnot=args");
        _fr.add(EXECUTED_TEST_ANNOTATIONS,"ExecutedTestAnnotations=annotations");
        _fr.add(EXECUTED_TEST_METHOD,"ExecutedTestMethod=methode");
        _fr.add(EXECUTED_TEST_TEST,"ExecutedTestTest=test");
        _fr.add(LIST_ITR,"ListItr=liste");
        _fr.add(LENGTH_ITR_LI,"LengthItrLi=longueur");
        _fr.add(INDEX_ITR_LI,"IndexItrLi=indice");
        _fr.add(ARRAY_LI,"ArrayLi=tableau");
        _fr.add(LENGTH_LI,"LengthLi=longueur");
        _fr.add(LIST_ITER_TABLE,"ListIterTable=liste");
        _fr.add(LENGTH_ITR_TA,"LengthItrTa=longueur");
        _fr.add(INDEX_ITR_TA,"IndexItrTa=indice");
        _fr.add(FIRST,"First=premier");
        _fr.add(SECOND,"Second=deuxieme");
        _fr.add(LIST_TA,"ListTa=liste");
        _fr.add(LIST_VAR,"ListVar=T");
        _fr.add(CALLABLE_VAR,"CallableVar=T");
        _fr.add(CUST_ITERATOR_VAR,"CustIteratorVar=T");
        _fr.add(TABLE_VAR_FIRST,"TableVarFirst=T");
        _fr.add(TABLE_VAR_SECOND,"TableVarSecond=U");
        _fr.add(ITER_TA_VAR_FIRST,"IterTaVarFirst=T");
        _fr.add(ITER_TA_VAR_SECOND,"IterTaVarSecond=U");
        _fr.add(PAIR_VAR_FIRST,"PairVarFirst=T");
        _fr.add(PAIR_VAR_SECOND,"PairVarSecond=U");
        StringViewReplaceAliases.fr(_fr);
        MathAdvAliases.fr(_fr);
        CustAliasParameters.fr(_fr);
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> t_ = new StringMap<CustList<KeyValueMemberName>>();
        t_.addEntry(getAliasList(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(LIST_VAR),getAliasListVar())));
        t_.addEntry(getAliasCustIterator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CUST_ITERATOR_VAR),getAliasCustIteratorVar())));
        t_.addEntry(getAliasTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TABLE_VAR_FIRST),getAliasTableVarFirst()),
                new KeyValueMemberName(_mapping.getVal(TABLE_VAR_SECOND),getAliasTableVarSecond())));
        t_.addEntry(getAliasCustIterTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ITER_TA_VAR_FIRST),getAliasIterTaVarFirst()),
                new KeyValueMemberName(_mapping.getVal(ITER_TA_VAR_SECOND),getAliasIterTaVarSecond())));
        t_.addEntry(getAliasCustPair(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PAIR_VAR_FIRST),getAliasPairVarFirst()),
                new KeyValueMemberName(_mapping.getVal(PAIR_VAR_SECOND),getAliasPairVarSecond())));
        t_.addEntry(getAliasCallable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CALLABLE_VAR),getAliasCallableVar())));
        return t_;
    }

    public CustList<KeyValueMemberName> allMergeTableTypeMethodNames(StringMap<String> _mapping, LgNamesContent _content) {
        CustList<KeyValueMemberName> list_ = new CustList<KeyValueMemberName>();
        list_.addAllElts(_content.getPredefTypes().allMergeTableTypeMethodNames(_mapping));
        list_.add(new KeyValueMemberName(_mapping.getVal(RUN),getAliasRun()));
        list_.add(new KeyValueMemberName(_mapping.getVal(CALLABLE_METHOD),getAliasCallableMethod()));
        return list_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> f_ = new StringMap<CustList<KeyValueMemberName>>();
        f_.addEntry(getAliasDifference(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(DIFFERENCE_EXPECTED),getAliasDifferenceExpected()),
                new KeyValueMemberName(_mapping.getVal(DIFFERENCE_FOUND),getAliasDifferenceFound()),
                new KeyValueMemberName(_mapping.getVal(DIFFERENCE_FOUND_NOT_TRUE),getAliasDifferenceFoundNotTrue()),
                new KeyValueMemberName(_mapping.getVal(DIFFERENCE_FOUND_NULL),getAliasDifferenceFoundNull()),
                new KeyValueMemberName(_mapping.getVal(DIFFERENCE_STACK_DIFF),getAliasDifferenceStackDiff())
        ));
        f_.addEntry(getAliasEltDifference(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(INDEX),getAliasIndex())
        ));
        f_.addEntry(getAliasRunnable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(RUNNABLE_IMPLICIT_0_RUNNER),getAliasRunnableImplicit0Runner())
        ));
        f_.addEntry(getAliasCallable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CALLABLE_IMPLICIT_0_RUNNER),getAliasCallableImplicit0Runner())
        ));
        f_.addEntry(getAliasInfoTest(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(INFO_TEST_COUNT),getAliasInfoTestCount()),
                new KeyValueMemberName(_mapping.getVal(INFO_TEST_CALLS),getAliasInfoTestCalls()),
                new KeyValueMemberName(_mapping.getVal(INFO_TEST_CURRENT_METHOD),getAliasInfoTestCurrentMethod()),
                new KeyValueMemberName(_mapping.getVal(INFO_TEST_DONE),getAliasInfoTestDone()),
                new KeyValueMemberName(_mapping.getVal(INFO_TEST_NB_THREADS),getAliasInfoTestNbThreads()),
                new KeyValueMemberName(_mapping.getVal(INFO_TEST_CURRENT_PARAMS),getAliasInfoTestCurrentParams())
        ));
        f_.addEntry(getAliasResult(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(RESULT_FAIL_MESSAGE),getAliasResultFailMessage()),
                new KeyValueMemberName(_mapping.getVal(RESULT_PARAMS),getAliasResultParams()),
                new KeyValueMemberName(_mapping.getVal(RESULT_TIME),getAliasResultTime()),
                new KeyValueMemberName(_mapping.getVal(RESULT_SUCCESS),getAliasResultSuccess()),
                new KeyValueMemberName(_mapping.getVal(RESULT_ARGS), getAliasResultArgs()),
                new KeyValueMemberName(_mapping.getVal(RESULT_CONTAINER), getAliasResultContainer()),
                new KeyValueMemberName(_mapping.getVal(RESULT_EXECUTED), getAliasResultExecuted())
        ));
        f_.addEntry(getAliasExecutedTest(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(EXECUTED_TEST_AFTER),getAliasExecutedTestAfter()),
                new KeyValueMemberName(_mapping.getVal(EXECUTED_TEST_BEFORE),getAliasExecutedTestBefore()),
                new KeyValueMemberName(_mapping.getVal(EXECUTED_TEST_ARGS_ANNOT),getAliasExecutedTestArgsAnnot()),
                new KeyValueMemberName(_mapping.getVal(EXECUTED_TEST_ANNOTATIONS),getAliasExecutedTestAnnotations()),
                new KeyValueMemberName(_mapping.getVal(EXECUTED_TEST_METHOD),getAliasExecutedTestMethod()),
                new KeyValueMemberName(_mapping.getVal(EXECUTED_TEST_TEST),getAliasExecutedTestTest())
        ));
        f_.addEntry(getAliasCustIterator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(LIST_ITR),getAliasListItr()),
                new KeyValueMemberName(_mapping.getVal(LENGTH_ITR_LI),getAliasLengthItrLi()),
                new KeyValueMemberName(_mapping.getVal(INDEX_ITR_LI),getAliasIndexItrLi())
        ));
        f_.addEntry(getAliasList(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ARRAY_LI),getAliasArrayLi()),
                new KeyValueMemberName(_mapping.getVal(LENGTH_LI),getAliasLengthLi())
        ));
        f_.addEntry(getAliasCustIterTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(LIST_ITER_TABLE),getAliasListIterTable()),
                new KeyValueMemberName(_mapping.getVal(LENGTH_ITR_TA),getAliasLengthItrTa()),
                new KeyValueMemberName(_mapping.getVal(INDEX_ITR_TA),getAliasIndexItrTa())
        ));
        f_.addEntry(getAliasCustPair(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FIRST),getAliasFirst()),
                new KeyValueMemberName(_mapping.getVal(SECOND),getAliasSecond())
        ));
        f_.addEntry(getAliasTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(LIST_TA),getAliasListTa())
        ));
        f_.addAllEntries(stringViewReplaceAliases.allTableTypeFieldNames(_mapping));
        return f_;
    }

    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.addAllElts(custAliasParameters.allTableTypeMethodParamNames(_mapping));
        m_.addAllElts(stringViewReplaceAliases.allTableTypeMethodParamNames(_mapping));
        m_.addAllElts(mathAdvAliases.allTableTypeMethodParamNames(_mapping));
        return m_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping, StringMap<CustList<KeyValueMemberName>> _methods, LgNamesContent _content) {
        StringMap<CustList<KeyValueMemberName>> m_ = new StringMap<CustList<KeyValueMemberName>>();
        m_.addEntry(getAliasThread(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(START),getAliasStart()),
                new KeyValueMemberName(_mapping.getVal(THREAD_EQ),getAliasThreadEq()),
                new KeyValueMemberName(_mapping.getVal(THREAD_RUNNABLE),getAliasThreadRunnable()),
                new KeyValueMemberName(_mapping.getVal(THREAD_CURRENT_TIME),getAliasThreadCurrentTime()),
                new KeyValueMemberName(_mapping.getVal(THREAD_CURRENT_NANO_TIME),getAliasThreadCurrentNanoTime()),
                new KeyValueMemberName(_mapping.getVal(IS_ALIVE),getAliasIsAlive()),
                new KeyValueMemberName(_mapping.getVal(IS_ENDED),getAliasIsEnded()),
                new KeyValueMemberName(_mapping.getVal(END),getAliasEnd()),
                new KeyValueMemberName(_mapping.getVal(CURRENT_THREAD),getAliasCurrentThread()),
                new KeyValueMemberName(_mapping.getVal(JOIN),getAliasJoin()),
                new KeyValueMemberName(_mapping.getVal(INTERRUPT),getAliasInterrupt()),
                new KeyValueMemberName(_mapping.getVal(ARGS),getAliasArgs()),
                new KeyValueMemberName(_mapping.getVal(COVERAGE),getAliasCoverage()),
                new KeyValueMemberName(_mapping.getVal(JOIN_OTHERS),getAliasJoinOthers()),
                new KeyValueMemberName(_mapping.getVal(GET_ID),getAliasGetId()),
                new KeyValueMemberName(_mapping.getVal(GET_PRIORITY),getAliasGetPriority()),
                new KeyValueMemberName(_mapping.getVal(SET_PRIORITY),getAliasSetPriority()),
                new KeyValueMemberName(_mapping.getVal(SLEEP),getAliasSleep()),
                new KeyValueMemberName(_mapping.getVal(PRINT),getAliasPrint())));
//                new KeyValueMemberName(_mapping.getVal(YIELD),getAliasYield()),
//                new KeyValueMemberName(_mapping.getVal(THREAD_EXIT_HOOK),getAliasThreadExitHook())));

        m_.addEntry(getAliasExecutorService(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(EXECUTOR_SERVICE_EXECUTE), getAliasExecutorServiceExecute()),
                new KeyValueMemberName(_mapping.getVal(EXECUTOR_SERVICE_SUBMIT), getAliasExecutorServiceSubmit()),
                new KeyValueMemberName(_mapping.getVal(EXECUTOR_SERVICE_SHUTDOWN),getAliasExecutorServiceShutdown()),
                new KeyValueMemberName(_mapping.getVal(EXECUTOR_SERVICE_STOPPED),getAliasExecutorServiceStopped())));
        m_.addEntry(getAliasScheduledExecutorService(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(EXECUTOR_SERVICE_SCHEDULE_MILLIS), getAliasExecutorServiceScheduleMillis()),
                new KeyValueMemberName(_mapping.getVal(EXECUTOR_SERVICE_SCHEDULE_NANOS), getAliasExecutorServiceScheduleNanos()),
                new KeyValueMemberName(_mapping.getVal(EXECUTOR_SERVICE_SHUTDOWN),getAliasExecutorServiceShutdown()),
                new KeyValueMemberName(_mapping.getVal(EXECUTOR_SERVICE_STOPPED),getAliasExecutorServiceStopped())));
        m_.addEntry(getAliasFuture(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FUTURE_WAIT), getAliasFutureWait()),
                new KeyValueMemberName(_mapping.getVal(FUTURE_CANCEL), getAliasFutureCancel())));
        m_.addEntry(getAliasThreadSet(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(THREAD_SET_ADD),getAliasThreadSetAdd()),
                new KeyValueMemberName(_mapping.getVal(THREAD_SET_ALL),getAliasThreadSetAll()),
                new KeyValueMemberName(_mapping.getVal(THREAD_SET_CONTAINS),getAliasThreadSetContains()),
                new KeyValueMemberName(_mapping.getVal(THREAD_SET_REMOVE),getAliasThreadSetRemove()),
                new KeyValueMemberName(_mapping.getVal(THREAD_SET_SNAPSHOT),getAliasThreadSetSnapshot())));
//        m_.addEntry(getAliasReentrantLock(), new CustList<KeyValueMemberName>(
//                new KeyValueMemberName(_mapping.getVal(LOCK),getAliasLock()),
//                new KeyValueMemberName(_mapping.getVal(UNLOCK),getAliasUnlock()),
//                new KeyValueMemberName(_mapping.getVal(IS_HELD_BY_CURRENT_THREAD),getAliasIsHeldByCurrentThread())));
        m_.addEntry(getAliasRunnable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(RUN),getAliasRun())));
        m_.addEntry(getAliasCallable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CALLABLE_METHOD),getAliasCallableMethod())));
        m_.addEntry(getAliasFormatType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PRINT),getAliasPrint())));
        m_.addEntry(getAliasAtomicBoolean(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_ATOMIC),getAliasGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(SET_ATOMIC),getAliasSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_AND_SET_ATOMIC),getAliasCompareAndSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_SET_ATOMIC),getAliasGetAndSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(LAZY_SET_ATOMIC),getAliasLazySetAtomic())));
        m_.addEntry(getAliasAtomicRef(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_ATOMIC),getAliasGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(SET_ATOMIC),getAliasSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_SET_ATOMIC),getAliasGetAndSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(LAZY_SET_ATOMIC),getAliasLazySetAtomic())));
        m_.addEntry(getAliasAtomicInteger(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_ATOMIC),getAliasGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(SET_ATOMIC),getAliasSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_AND_SET_ATOMIC),getAliasCompareAndSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_SET_ATOMIC),getAliasGetAndSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(LAZY_SET_ATOMIC),getAliasLazySetAtomic()),
                new KeyValueMemberName(_mapping.getVal(ADD_AND_GET_ATOMIC),getAliasAddAndGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_ADD_ATOMIC),getAliasGetAndAddAtomic()),
                new KeyValueMemberName(_mapping.getVal(INCREMENT_AND_GET_ATOMIC),getAliasIncrementAndGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_INCREMENT_ATOMIC),getAliasGetAndIncrementAtomic()),
                new KeyValueMemberName(_mapping.getVal(DECREMENT_AND_GET_ATOMIC),getAliasDecrementAndGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_DECREMENT_ATOMIC),getAliasGetAndDecrementAtomic())));
        m_.addEntry(getAliasAtomicLong(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_ATOMIC),getAliasGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(SET_ATOMIC),getAliasSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_AND_SET_ATOMIC),getAliasCompareAndSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_SET_ATOMIC),getAliasGetAndSetAtomic()),
                new KeyValueMemberName(_mapping.getVal(LAZY_SET_ATOMIC),getAliasLazySetAtomic()),
                new KeyValueMemberName(_mapping.getVal(ADD_AND_GET_ATOMIC),getAliasAddAndGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_ADD_ATOMIC),getAliasGetAndAddAtomic()),
                new KeyValueMemberName(_mapping.getVal(INCREMENT_AND_GET_ATOMIC),getAliasIncrementAndGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_INCREMENT_ATOMIC),getAliasGetAndIncrementAtomic()),
                new KeyValueMemberName(_mapping.getVal(DECREMENT_AND_GET_ATOMIC),getAliasDecrementAndGetAtomic()),
                new KeyValueMemberName(_mapping.getVal(GET_AND_DECREMENT_ATOMIC),getAliasGetAndDecrementAtomic())));
        m_.addAllEntries(mathAdvAliases.allTableTypeMethodNames(_mapping));
        m_.addEntry(getAliasFile(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(READ),getAliasRead()),
                new KeyValueMemberName(_mapping.getVal(WRITE),getAliasWrite()),
                new KeyValueMemberName(_mapping.getVal(APPEND_TO_FILE),getAliasAppendToFile()),
                new KeyValueMemberName(_mapping.getVal(FILE_ABSOLUTE_PATH),getAliasFileAbsolutePath()),
                new KeyValueMemberName(_mapping.getVal(FILE_GET_LENGTH),getAliasFileGetLength()),
                new KeyValueMemberName(_mapping.getVal(FILE_GET_NAME),getAliasFileGetName()),
                new KeyValueMemberName(_mapping.getVal(FILE_GET_PARENT_PATH),getAliasFileGetParentPath()),
                new KeyValueMemberName(_mapping.getVal(FILE_IS_DIRECTORY),getAliasFileIsDirectory()),
                new KeyValueMemberName(_mapping.getVal(FILE_IS_FILE),getAliasFileIsFile()),
                new KeyValueMemberName(_mapping.getVal(FILE_ROOTS),getAliasFileRoots()),
                new KeyValueMemberName(_mapping.getVal(FILE_IS_ABSOLUTE),getAliasFileIsAbsolute()),
                new KeyValueMemberName(_mapping.getVal(FILE_READ_BIN),getAliasFileReadBin()),
                new KeyValueMemberName(_mapping.getVal(FILE_WRITE_BIN),getAliasFileWriteBin()),
                new KeyValueMemberName(_mapping.getVal(FILE_DELETE),getAliasFileDelete()),
                new KeyValueMemberName(_mapping.getVal(FILE_DIR),getAliasFileDir()),
                new KeyValueMemberName(_mapping.getVal(FILE_RENAME),getAliasFileRename()),
                new KeyValueMemberName(_mapping.getVal(FILE_LAST_MODIF),getAliasFileLastModif()),
                new KeyValueMemberName(_mapping.getVal(FILE_LIST_DIRECTORIES),getAliasFileListDirectories()),
                new KeyValueMemberName(_mapping.getVal(FILE_LIST_FILES),getAliasFileListFiles()),
                new KeyValueMemberName(_mapping.getVal(FILE_ZIP_BIN),getAliasFileZipBin()),
                new KeyValueMemberName(_mapping.getVal(FILE_ZIP_BIN_ARRAY),getAliasFileZipBinArray()),
                new KeyValueMemberName(_mapping.getVal(FILE_ZIP_TEXT),getAliasFileZipText()),
                new KeyValueMemberName(_mapping.getVal(FILE_ZIPPED_BIN),getAliasFileZippedBin()),
                new KeyValueMemberName(_mapping.getVal(FILE_ZIPPED_BIN_ARRAY),getAliasFileZippedBinArray()),
                new KeyValueMemberName(_mapping.getVal(FILE_ZIPPED_TEXT),getAliasFileZippedText()),
                new KeyValueMemberName(_mapping.getVal(FILE_MAKE_DIRS),getAliasFileMakeDirs())));
        m_.addEntry(getAliasTableStringObject(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_PAIRS),getAliasTableConcPairs()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_EMPTY),getAliasTableConcEmpty()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_SIZE),getAliasTableConcSize()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_KEYS),getAliasTableConcKeys()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_VALUES),getAliasTableConcValues()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_HAS_KEY),getAliasTableConcHasKey()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_HAS_VALUE),getAliasTableConcHasValue()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_GET),getAliasTableConcGet()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_REMOVE),getAliasTableConcRemove()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_PUT),getAliasTableConcPut()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_PUT_ABS),getAliasTableConcPutAbs()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_REPLACE),getAliasTableConcReplace()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_CLEAR),getAliasTableConcClear()),
                new KeyValueMemberName(_mapping.getVal(TABLE_CONC_PUT_ALL),getAliasTableConcPutAll())));
        m_.addEntry(getAliasEntryStringObject(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TABLE_ENTRY_KEY),getAliasTableEntryKey()),
                new KeyValueMemberName(_mapping.getVal(TABLE_ENTRY_VALUE),getAliasTableEntryValue()),
                new KeyValueMemberName(_mapping.getVal(TABLE_ENTRY_OWNER),getAliasTableEntryOwner())));
        m_.addEntry(getAliasEntryBinary(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ENTRY_NAME),getAliasEntryName()),
                new KeyValueMemberName(_mapping.getVal(ENTRY_TIME),getAliasEntryTime()),
                new KeyValueMemberName(_mapping.getVal(ENTRY_VALUE),getAliasEntryValue())));
        m_.addEntry(getAliasEntryText(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ENTRY_NAME),getAliasEntryName()),
                new KeyValueMemberName(_mapping.getVal(ENTRY_TIME),getAliasEntryTime()),
                new KeyValueMemberName(_mapping.getVal(ENTRY_VALUE),getAliasEntryValue())));
        m_.addEntry(getAliasCustIterator(), _methods.getVal(_content.getPredefTypes().getAliasIteratorType()));
        m_.addEntry(getAliasList(), merge(_methods,_content.getPredefTypes().getAliasIterable(),
                new KeyValueMemberName(_mapping.getVal(ADD_LI),getAliasAddLi()),
                new KeyValueMemberName(_mapping.getVal(SIZE_LI),getAliasSizeLi()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_LI),getAliasRemoveLi()),
                new KeyValueMemberName(_mapping.getVal(LIST_CLEAR),getAliasListClear())));
        m_.addEntry(getAliasCustPair(), merge(_methods,_content.getPredefTypes().getAliasPairType(),
                new KeyValueMemberName(_mapping.getVal(SET_FIRST),getAliasSetFirst()),
                new KeyValueMemberName(_mapping.getVal(SET_SECOND),getAliasSetSecond())));
        m_.addEntry(getAliasCustIterTable(), _methods.getVal(_content.getPredefTypes().getAliasIteratorTableType()));
        m_.addEntry(getAliasTable(), merge(_methods,_content.getPredefTypes().getAliasIterableTable(),
                new KeyValueMemberName(_mapping.getVal(GET_FIRST_TA),getAliasGetFirstTa()),
                new KeyValueMemberName(_mapping.getVal(GET_SECOND_TA),getAliasGetSecondTa()),
                new KeyValueMemberName(_mapping.getVal(SET_FIRST_TA),getAliasSetFirstTa()),
                new KeyValueMemberName(_mapping.getVal(SET_SECOND_TA),getAliasSetSecondTa()),
                new KeyValueMemberName(_mapping.getVal(ADD_TA),getAliasAddTa()),
                new KeyValueMemberName(_mapping.getVal(REMOVE_TA),getAliasRemoveTa()),
                new KeyValueMemberName(_mapping.getVal(SIZE_TA),getAliasSizeTa()),
                new KeyValueMemberName(_mapping.getVal(GET_TA),getAliasGetTa())
        ));
        m_.addEntry(getAliasExecute(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(EXECUTE_TESTS),getAliasExecuteTests()),
                new KeyValueMemberName(_mapping.getVal(EXECUTE_CONVERT),getAliasExecuteConvert()),
                new KeyValueMemberName(_mapping.getVal(EXECUTE_SETUP_ERROR),getAliasExecuteSetupError()),
                new KeyValueMemberName(_mapping.getVal(EXECUTE_SETUP_NO_EXCEPTION),getAliasExecuteSetupNoException()),
                new KeyValueMemberName(_mapping.getVal(EXECUTE_EXECUTE),getAliasExecuteExecute()),
                new KeyValueMemberName(_mapping.getVal(EXECUTE_GROUP_CLASS),getAliasExecuteGroupClass()),
                new KeyValueMemberName(_mapping.getVal(EXECUTE_GROUP_CLASS_METHOD),getAliasExecuteGroupClassMethod()),
                new KeyValueMemberName(_mapping.getVal(EXECUTE_FLAT),getAliasExecuteFlat()),
                new KeyValueMemberName(_mapping.getVal(EXECUTE_LAUNCH),getAliasExecuteLaunch())
        ));
        m_.addEntry(getAliasParameters(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(PARAMETERS_LOCATION),getAliasParametersLocation()),
                new KeyValueMemberName(_mapping.getVal(PARAMETERS_METHOD),getAliasParametersMethod())
        ));
        m_.addEntry(getAliasTest(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(TEST_EXCEPTION),getAliasTestException()),
                new KeyValueMemberName(_mapping.getVal(TEST_NULL_EXCEPTION),getAliasTestNullException())
        ));
        m_.addEntry(getAliasArgsTest(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ARGS_TEST_ARGS_VALUE),getAliasArgsTestArgsValue())
        ));
        m_.addEntry(getAliasAssert(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(ASSERT_ASSERT),getAliasAssertAssert()),
                new KeyValueMemberName(_mapping.getVal(ASSERT_NOT),getAliasAssertAssertNot()),
                new KeyValueMemberName(_mapping.getVal(ASSERT_ASSERT_NOT_NULL),getAliasAssertAssertNotNull()),
                new KeyValueMemberName(_mapping.getVal(ASSERT_ASSERT_NULL),getAliasAssertAssertNull()),
                new KeyValueMemberName(_mapping.getVal(ASSERT_ASSERT_SAME),getAliasAssertAssertSame()),
                new KeyValueMemberName(_mapping.getVal(ASSERT_NOT_SAME),getAliasAssertAssertNotSame()),
                new KeyValueMemberName(_mapping.getVal(ASSERT_ASSERT_TRUE),getAliasAssertAssertTrue()),
                new KeyValueMemberName(_mapping.getVal(ASSERT_ASSERT_FALSE),getAliasAssertAssertFalse())
        ));
        m_.addEntry(getAliasDifference(), new CustList<KeyValueMemberName>(
        ));
        m_.addEntry(getAliasEltDifference(), new CustList<KeyValueMemberName>(
        ));
        m_.addAllEntries(stringViewReplaceAliases.allTableTypeMethodNames(_mapping));
        return m_;
    }
    public static CustList<KeyValueMemberName> merge(StringMap<CustList<KeyValueMemberName>> _methods, String _key, KeyValueMemberName..._values) {
        CustList<KeyValueMemberName> list_ = new CustList<KeyValueMemberName>(_methods.getVal(_key));
        for (KeyValueMemberName k: _values) {
            list_.add(k);
        }
        return list_;
    }

    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> ref_ = new StringMap<String>();
        ref_.addEntry(_mapping.getVal(THREAD),getAliasThread());
        ref_.addEntry(_mapping.getVal(THREAD_SET),getAliasThreadSet());
        ref_.addEntry(_mapping.getVal(EXECUTOR_SERVICE),getAliasExecutorService());
        ref_.addEntry(_mapping.getVal(EXECUTOR_SERVICE_BASE),getAliasExecutorServiceBase());
        ref_.addEntry(_mapping.getVal(SCHEDULED_EXECUTOR_SERVICE),getAliasScheduledExecutorService());
        ref_.addEntry(_mapping.getVal(FUTURE),getAliasFuture());
//        ref_.addEntry(_mapping.getVal(REENTRANT_LOCK),getAliasReentrantLock());
        ref_.addEntry(_mapping.getVal(ATOMIC_BOOLEAN),getAliasAtomicBoolean());
        ref_.addEntry(_mapping.getVal(ATOMIC_INTEGER),getAliasAtomicInteger());
        ref_.addEntry(_mapping.getVal(ATOMIC_LONG),getAliasAtomicLong());
        ref_.addEntry(_mapping.getVal(ATOMIC_REF),getAliasAtomicRef());
        ref_.addAllEntries(mathAdvAliases.allRefTypes(_mapping));
        ref_.addEntry(_mapping.getVal(FILE),getAliasFile());
        ref_.addEntry(_mapping.getVal(ILLEGAL_THREAD_STATE_EXCEPTION),getAliasIllegalThreadStateException());
        ref_.addEntry(_mapping.getVal(CUST_ITERATOR),getAliasCustIterator());
        ref_.addEntry(_mapping.getVal(LIST),getAliasList());
        ref_.addEntry(_mapping.getVal(RUNNABLE),getAliasRunnable());
        ref_.addEntry(_mapping.getVal(CALLABLE),getAliasCallable());
        ref_.addEntry(_mapping.getVal(FORMAT_TYPE),getAliasFormatType());
        ref_.addEntry(_mapping.getVal(CUST_PAIR),getAliasCustPair());
        ref_.addEntry(_mapping.getVal(CUST_ITER_TABLE),getAliasCustIterTable());
        ref_.addEntry(_mapping.getVal(TABLE),getAliasTable());
        ref_.addEntry(_mapping.getVal(EXECUTE),getAliasExecute());
        ref_.addEntry(_mapping.getVal(INFO_TEST),getAliasInfoTest());
        ref_.addEntry(_mapping.getVal(EXECUTED_TEST),getAliasExecutedTest());
        ref_.addEntry(_mapping.getVal(RESULT),getAliasResult());
        ref_.addEntry(_mapping.getVal(BEFORE),getAliasBefore());
        ref_.addEntry(_mapping.getVal(AFTER),getAliasAfter());
        ref_.addEntry(_mapping.getVal(PARAMETERS),getAliasParameters());
        ref_.addEntry(_mapping.getVal(TEST),getAliasTest());
        ref_.addEntry(_mapping.getVal(ARGS_TEST),getAliasArgsTest());
        ref_.addEntry(_mapping.getVal(ASSERT),getAliasAssert());
        ref_.addEntry(_mapping.getVal(DIFFERENCE),getAliasDifference());
        ref_.addEntry(_mapping.getVal(ELT_DIFFERENCE),getAliasEltDifference());
        ref_.addEntry(_mapping.getVal(CONCURRENT_ERROR),getAliasConcurrentError());
        ref_.addEntry(_mapping.getVal(ENTRY_BINARY),getAliasEntryBinary());
        ref_.addEntry(_mapping.getVal(ENTRY_TEXT),getAliasEntryText());
        ref_.addEntry(_mapping.getVal(ENTRY_STRING_OBJECT),getAliasEntryStringObject());
        ref_.addEntry(_mapping.getVal(TABLE_STRING_OBJECT),getAliasTableStringObject());
        ref_.addAllEntries(stringViewReplaceAliases.allRefTypes(_mapping));
        return ref_;
    }

    public String runnableType(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        String th_ = _kw.getKeyWordThis();
        String vd_ = _content.getCoreNames().getAliasVoid();
        String fct_ = _content.getReflect().getAliasFct();
        String fctVd_ = fct_+"<"+vd_+">";
        String parCast_ = custAliasParameters.getAliasRunnableImplicit0Implicit0();
        String parInner_ = custAliasParameters.getAliasRunnableImplicit0Implicit1();
        return pub_+" "+_kw.getKeyWordInterface()+" "+aliasRunnable+"{"+LR
                +SPACES_4+pub_+" "+_kw.getKeyWordAbstract()+" "+vd_+" "+aliasRun+"();"+LR
                +SPACES_4+pub_+" "+_kw.getKeyWordStatic()+" "+aliasRunnable+" "+_kw.getKeyWordCast()+"("+fctVd_+ parCast_ +"){"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordReturn()+" "+_kw.getKeyWordNew()+"("+ parCast_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+pub_+" "+fctVd_+" "+aliasRunnableImplicit0Runner+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+pub_+"("+fctVd_+ parInner_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+th_+"."+aliasRunnableImplicit0Runner+"="+ parInner_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+pub_+" "+vd_+" "+aliasRun+"(){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+th_+"."+aliasRunnableImplicit0Runner+"."+_content.getReflect().getAliasCall()+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"};"+LR
                +SPACES_4+"}"+LR
                +"}"+LR;
    }

    public String callableType(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        String th_ = _kw.getKeyWordThis();
        String fct_ = _content.getReflect().getAliasFct();
        String fctVd_ = fct_+"<"+aliasCallableVar+">";
        String parCast_ = custAliasParameters.getAliasCallableImplicit0Implicit0();
        String parInner_ = custAliasParameters.getAliasCallableImplicit0Implicit1();
        return pub_+" "+_kw.getKeyWordInterface()+" "+aliasCallable+"<"+aliasCallableVar+">{"+LR
                +SPACES_4+pub_+" "+_kw.getKeyWordAbstract()+" "+aliasCallableVar+" "+aliasCallableMethod+"();"+LR
                +SPACES_4+pub_+" "+_kw.getKeyWordStatic()+" "+aliasCallable+"<"+aliasCallableVar+">"+" "+_kw.getKeyWordCast()+"("+fctVd_+ parCast_ +"){"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordReturn()+" "+_kw.getKeyWordNew()+"("+ parCast_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+pub_+" "+fctVd_+" "+aliasCallableImplicit0Runner+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+pub_+"("+fctVd_+ parInner_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+th_+"."+aliasCallableImplicit0Runner+"="+ parInner_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+pub_+" "+aliasCallableVar+" "+aliasCallableMethod+"(){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+_kw.getKeyWordReturn()+" "+th_+"."+aliasCallableImplicit0Runner+"."+_content.getReflect().getAliasCall()+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"};"+LR
                +SPACES_4+"}"+LR
                +"}"+LR;
    }
    public String iteratorType(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String in_ = primTypes_.getAliasPrimInteger();
        String pri_ = _kw.getKeyWordPrivate();
        String a_ = custAliasParameters.getAliasCustIterator0CustIterator0();
        String th_ = _kw.getKeyWordThis();
        AliasPredefinedTypes pred_ = _content.getPredefTypes();
        String o_ = tr("out", _kw, pr_, co_);
        String ret_ = _kw.getKeyWordReturn();
        return pub_ +" "+_kw.getKeyWordClass()+" "+aliasCustIterator+"<"+ aliasCustIteratorVar +"> :"+ pred_.getAliasIteratorType()+"<"+ aliasCustIteratorVar +">{"+LR
                +SPACES_4+ pri_ +" "+aliasList+"<"+ aliasCustIteratorVar +"> "+ aliasListItr+";"+LR
                +SPACES_4+ pri_ +" "+ in_ +" "+aliasLengthItrLi+";"+LR
                +SPACES_4+ pri_ +" "+ in_ +" "+aliasIndexItrLi+";"+LR
                +SPACES_4+ pub_ +" ("+aliasList+"<"+ aliasCustIteratorVar +"> "+ a_ +"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasListItr+"="+ a_ +";"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasLengthItrLi+"="+ th_ +"."+aliasListItr+"."+aliasSizeLi+"();"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ aliasCustIteratorVar +" "+pred_.getAliasNext()+"(){"+LR
                +SPACES_4+SPACES_4+ aliasCustIteratorVar +" "+ o_ +"="+ th_ +"."+aliasListItr+"["+th_ +"."+aliasIndexItrLi+"];"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasIndexItrLi+"++;"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ o_ +";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+primTypes_.getAliasPrimBoolean()+" "+pred_.getAliasHasNext()+"(){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+aliasIndexItrLi+"<"+aliasLengthItrLi+";"+LR
                +SPACES_4+"}"+LR
                +"}"+LR;
    }
    public String listType(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String pri_ = _kw.getKeyWordPrivate();
        String th_ =  _kw.getKeyWordThis();
        String fieldLen_ = th_ + "." + aliasLengthLi;
        String in_ = primTypes_.getAliasPrimInteger();
        String nw_ = _kw.getKeyWordNew();
        String parCtrCp_ = custAliasParameters.getAliasList0List0();
        String parCtr_ = custAliasParameters.getAliasList1List0();
        String parAppend_ = custAliasParameters.getAliasList0AddLi0();
        String parInsIndex_ = custAliasParameters.getAliasList1AddLi0();
        String parInsElt_ = custAliasParameters.getAliasList1AddLi1();
        String parGet_ = custAliasParameters.getAliasList0This0();
        String parSet_ = custAliasParameters.getAliasList1This0();
        String arrLg_ = co_.getAliasArrayLength();
        String arrClone_ = co_.getAliasClone();
        String vd_ = co_.getAliasVoid();
        String iterKw_ = _kw.getKeyWordIter();
        String outIns_ = tr("out", _kw, pr_, co_,
                parInsIndex_,
                parInsElt_);
        String indIns_ = tr("i", _kw, pr_, co_,
                parInsIndex_,
                parInsElt_);
        String ret_ = _kw.getKeyWordReturn();
        String parRem_ = custAliasParameters.getAliasList0RemoveLi0();
        String outRem_ = tr("out", _kw, pr_, co_,parRem_);
        String indRem_ = tr("i", _kw, pr_, co_,parRem_);
        AliasPredefinedTypes pred_ = _content.getPredefTypes();
        return pub_ +" "+_kw.getKeyWordClass()+" "+aliasList+"<"+aliasListVar+"> :"+pred_.getAliasIterable()+"<"+aliasListVar+">{"+LR
                +SPACES_4+ pri_ +" "+aliasListVar+"[] "+ aliasArrayLi+";"+LR
                +SPACES_4+ pri_ +" "+ in_ +" "+aliasLengthLi+";"+LR
                +SPACES_4+ pub_ +" (){"+LR
                +SPACES_4+SPACES_4+aliasArrayLi+"="+ nw_ +" "+aliasListVar+"["+"0"+"];"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" ("+aliasList+"<"+aliasListVar+"> "+parCtrCp_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasArrayLi+"="+parCtrCp_+"."+aliasArrayLi+"."+ arrClone_ +"();"+LR
                +SPACES_4+SPACES_4+ fieldLen_ +"="+ th_ +"."+aliasArrayLi+"."+ arrLg_ +";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" ("+aliasListVar+"... "+parCtr_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasArrayLi+"="+parCtr_+"."+ arrClone_ +"();"+LR
                +SPACES_4+SPACES_4+ fieldLen_ +"="+ th_ +"."+aliasArrayLi+"."+ arrLg_ +";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasAddLi+"("+aliasListVar+" "+parAppend_+"){"+LR
                +SPACES_4+SPACES_4+aliasAddLi+"("+ fieldLen_ +","+parAppend_+");"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasAddLi+"("+ in_ +" "+parInsIndex_+","+aliasListVar+" "+parInsElt_+"){"+LR
                +SPACES_4+SPACES_4+aliasListVar+"[] "+outIns_+"="+ nw_ +" "+aliasListVar+"["+ fieldLen_ +"+"+"1"+"];"+LR
                +SPACES_4+SPACES_4+ iterKw_ +"("+ in_ +" "+indIns_+"="+"0"+";"+parInsIndex_+";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+outIns_+"["+indIns_+"]="+ th_ +"."+aliasArrayLi+"["+indIns_+"];"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+outIns_+"["+parInsIndex_+"]="+parInsElt_+";"+LR
                +SPACES_4+SPACES_4+ iterKw_ +"("+ in_ +" "+indIns_+"="+parInsIndex_+"+"+"1"+";"+fieldLen_+"+"+"1"+";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+outIns_+"["+indIns_+"]="+ th_ +"."+aliasArrayLi+"["+indIns_+"-"+"1"+"];"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ fieldLen_ +"++;"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasArrayLi+"="+outIns_+";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ in_ +" "+aliasSizeLi+"(){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ fieldLen_ +";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+aliasListVar+" "+ th_ +"("+ in_ +" "+parGet_+"){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ th_ +"."+aliasArrayLi+"["+parGet_+"];"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+ th_ +"("+ in_ +" "+parSet_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasArrayLi+"["+parSet_+"]="+_kw.getKeyWordValue()+";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasRemoveLi+"("+ in_ +" "+ parRem_ +"){"+LR
                +SPACES_4+SPACES_4+aliasListVar+"[] "+ outRem_ +"="+ nw_ +" "+aliasListVar+"["+ fieldLen_ +"-"+"1"+"];"+LR
                +SPACES_4+SPACES_4+ iterKw_ +"("+ in_ +" "+ indRem_ +"="+"0"+";"+ parRem_ +";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ outRem_ +"["+ indRem_ +"]="+ th_ +"."+aliasArrayLi+"["+ indRem_ +"];"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ iterKw_ +"("+ in_ +" "+ indRem_ +"="+ parRem_ +"+"+"1"+";"+ fieldLen_ +";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ outRem_ +"["+ indRem_ +"-"+"1"+"]="+ th_ +"."+aliasArrayLi+"["+ indRem_ +"];"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasArrayLi+" = "+ outRem_ +";"+LR
                +SPACES_4+SPACES_4+fieldLen_+"--;"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasListClear+"(){"+LR
                +SPACES_4+SPACES_4+fieldLen_+"="+"0"+";"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasArrayLi+"="+ nw_ +" "+aliasListVar+"["+"0"+"];"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+pred_.getAliasIteratorType()+"<"+aliasListVar+"> "+pred_.getAliasIterator()+"(){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ nw_ +" "+aliasCustIterator+"<"+aliasListVar+">("+ th_ +");"+LR
                +SPACES_4+"}"+LR
                +"}"+LR;
    }
    public String pair(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        String pri_ = _kw.getKeyWordPrivate();
        String parFirst_ = custAliasParameters.getAliasCustPair0CustPair0();
        String parSecond_ = custAliasParameters.getAliasCustPair0CustPair1();
        AliasCore co_ = _content.getCoreNames();
        String majFirst_ = custAliasParameters.getAliasCustPair0SetFirst0();
        String majSecond_ = custAliasParameters.getAliasCustPair0SetSecond0();
        String th_ = _kw.getKeyWordThis();
        String ret_ = _kw.getKeyWordReturn();
        String vd_ = co_.getAliasVoid();
        AliasPredefinedTypes pred_ = _content.getPredefTypes();
        return pub_ +" "+_kw.getKeyWordClass()+" "+aliasCustPair+"<"+ aliasPairVarFirst +","+ aliasPairVarSecond +"> :"+pred_.getAliasPairType()+"<"+ aliasPairVarFirst +","+ aliasPairVarSecond +">{"+LR
                +SPACES_4+ pri_ +" "+ aliasPairVarFirst +" "+aliasFirst+";"+LR
                +SPACES_4+ pri_ +" "+ aliasPairVarSecond +" "+aliasSecond+";"+LR
                +SPACES_4+ pub_ +" (){"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" ("+ aliasPairVarFirst +" "+parFirst_+","+ aliasPairVarSecond +" "+parSecond_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasFirst+"="+parFirst_+";"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasSecond+"="+parSecond_+";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ aliasPairVarFirst +" "+pred_.getAliasGetFirst()+"(){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+aliasFirst+";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ aliasPairVarSecond +" "+pred_.getAliasGetSecond()+"(){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+aliasSecond+";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasSetFirst+"("+ aliasPairVarFirst +" "+majFirst_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasFirst+"="+majFirst_+";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasSetSecond+"("+ aliasPairVarSecond +" "+majSecond_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasSecond+"="+majSecond_+";"+LR
                +SPACES_4+"}"+LR
                +"}"+LR;
    }
    public String iteratorTableType(KeyWords _kw, LgNamesContent _content) {
        String pri_ = _kw.getKeyWordPrivate();
        String pub_ = _kw.getKeyWordPublic();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String in_ = primTypes_.getAliasPrimInteger();
        String ot_ = tr("out", _kw, pr_, co_);
        String th_ = _kw.getKeyWordThis();
        String par_ = custAliasParameters.getAliasCustIterTable0CustIterTable0();
        AliasPredefinedTypes pred_ = _content.getPredefTypes();
        String ret_ = _kw.getKeyWordReturn();
        return pub_ +" "+_kw.getKeyWordClass()+" "+aliasCustIterTable+"<"+aliasIterTaVarFirst+","+aliasIterTaVarSecond+"> :"+pred_.getAliasIteratorTableType()+"<"+aliasIterTaVarFirst+","+aliasIterTaVarSecond+">{"+LR
                +SPACES_4+ pri_ +" "+aliasTable+"<"+aliasIterTaVarFirst+","+aliasIterTaVarSecond+"> "+aliasListIterTable+";"+LR
                +SPACES_4+ pri_ +" "+ in_ +" "+aliasLengthItrTa+";"+LR
                +SPACES_4+ pri_ +" "+ in_ +" "+aliasIndexItrTa+";"+LR
                +SPACES_4+ pub_ +" ("+aliasTable+"<"+aliasIterTaVarFirst+","+aliasIterTaVarSecond+"> "+ par_ +"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasListIterTable+"="+ par_ +";"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasLengthItrTa+"="+ th_ +"."+aliasListIterTable+"."+aliasSizeTa+"();"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+aliasCustPair+"<"+aliasIterTaVarFirst+","+aliasIterTaVarSecond+"> "+pred_.getAliasNextPair()+"(){"+LR
                +SPACES_4+SPACES_4+aliasCustPair+"<"+aliasIterTaVarFirst+","+aliasIterTaVarSecond+"> "+ ot_ +"="+ th_ +"."+aliasListIterTable+"."+aliasGetTa+"("+ th_ +"."+aliasIndexItrTa+");"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasIndexItrTa+"++;"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ ot_ +";"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+primTypes_.getAliasPrimBoolean()+" "+pred_.getAliasHasNextPair()+"(){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+aliasIndexItrTa+"<"+aliasLengthItrTa+";"+LR
                +SPACES_4+"}"+LR
                +"}"+LR;
    }
    public String tableType(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        String th_ = _kw.getKeyWordThis();
        String ret_ = _kw.getKeyWordReturn();
        String nv_ = _kw.getKeyWordNew();
        AliasPredefinedTypes pred_ = _content.getPredefTypes();
        String vd_ = _content.getCoreNames().getAliasVoid();
        String i_ = _content.getPrimTypes().getAliasPrimInteger();
        String parFirstAdd_ = custAliasParameters.getAliasTable0AddLi0();
        String parSecondAdd_ = custAliasParameters.getAliasTable0AddLi1();
        String parPairAdd_ = custAliasParameters.getAliasTable1AddLi0();
        String parGet_ = custAliasParameters.getAliasTable0GetTa0();
        String parGetFirst_ = custAliasParameters.getAliasTable0GetFirstTa0();
        String parGetSecond_ = custAliasParameters.getAliasTable0GetSecondTa0();
        String parSetFirstInd_ = custAliasParameters.getAliasTable0SetFirst0();
        String parSetFirstElt_ = custAliasParameters.getAliasTable0SetFirst1();
        String parSetSecondInd_ = custAliasParameters.getAliasTable0SetSecond0();
        String parSetSecondElt_ = custAliasParameters.getAliasTable0SetSecond1();
        String parRem_ = custAliasParameters.getAliasTable0RemoveLi0();
        return pub_ +" "+_kw.getKeyWordClass()+" "+aliasTable+"<"+aliasTableVarFirst+","+aliasTableVarSecond+"> :"+pred_.getAliasIterableTable()+"<"+aliasTableVarFirst+","+aliasTableVarSecond+">{"+LR
                +SPACES_4+_kw.getKeyWordPrivate()+" "+aliasList+"<"+aliasCustPair+"<"+aliasTableVarFirst+","+aliasTableVarSecond+">> "+aliasListTa+";"+LR
                +SPACES_4+ pub_ +" (){"+LR
                +SPACES_4+SPACES_4+aliasListTa+"="+ nv_ +" "+aliasList+"<"+aliasCustPair+"<"+aliasTableVarFirst+","+aliasTableVarSecond+">>();"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasAddTa+"("+aliasTableVarFirst+" "+parFirstAdd_+","+aliasTableVarSecond+" "+parSecondAdd_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasListTa+"."+aliasAddLi+"("+ nv_ +" "+aliasCustPair+"<"+aliasTableVarFirst+","+aliasTableVarSecond+">("+parFirstAdd_+","+parSecondAdd_+"));"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasAddTa+"("+aliasCustPair+"<"+aliasTableVarFirst+","+aliasTableVarSecond+"> "+parPairAdd_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasListTa+"."+aliasAddLi+"("+parPairAdd_+");"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ i_ +" "+aliasSizeTa+"(){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+aliasListTa+"."+aliasSizeLi+"();"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+aliasCustPair+"<"+aliasTableVarFirst+","+aliasTableVarSecond+"> "+aliasGetTa+"("+ i_ +" "+parGet_+"){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ th_ +"."+aliasListTa+"["+parGet_+"];"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+aliasTableVarFirst+" "+aliasGetFirstTa+"("+ i_ +" "+parGetFirst_+"){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ th_ +"."+aliasListTa+"["+parGetFirst_+"]."+pred_.getAliasGetFirst()+"();"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+aliasTableVarSecond+" "+aliasGetSecondTa+"("+ i_ +" "+parGetSecond_+"){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ th_ +"."+aliasListTa+"["+parGetSecond_+"]."+pred_.getAliasGetSecond()+"();"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasSetFirstTa+"("+ i_ +" "+parSetFirstInd_+", "+aliasTableVarFirst+" "+parSetFirstElt_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasListTa+"["+parSetFirstInd_+"]."+aliasSetFirst+"("+parSetFirstElt_+");"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasSetSecondTa+"("+ i_ +" "+parSetSecondInd_+", "+aliasTableVarSecond+" "+parSetSecondElt_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasListTa+"["+parSetSecondInd_+"]."+aliasSetSecond+"("+parSetSecondElt_+");"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ vd_ +" "+aliasRemoveTa+"("+ i_ +" "+parRem_+"){"+LR
                +SPACES_4+SPACES_4+ th_ +"."+aliasListTa+"."+aliasRemoveLi+"("+parRem_+");"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+pred_.getAliasIteratorTableType()+"<"+aliasTableVarFirst+","+aliasTableVarSecond+"> "+pred_.getAliasIteratorTable()+"(){"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ nv_ +" "+aliasCustIterTable+"<"+aliasTableVarFirst+","+aliasTableVarSecond+">("+ th_ +");"+LR
                +SPACES_4+"}"+LR
                +"}";
    }
    public String executeType(KeyWords _kw, LgNamesContent _content) {
        String head_= _kw.getKeyWordPublic()+" "+_kw.getKeyWordAbstract()+" "+_kw.getKeyWordFinal()+" "+_kw.getKeyWordClass()+" "+aliasExecute+"{"+LR;
        String par_ = custAliasParameters.getAliasExecute0ExecuteTests0();
        String ls_ = custAliasParameters.getAliasExecute0ExecuteTests1();
        AliasReflection ref_ = _content.getReflect();
        String cl_ = ref_.getAliasClassType();
        String met_ = ref_.getAliasMethod();
        return head_
                +SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+aliasTable+"<"+_content.getReflect().getAliasMethod()+","+aliasResult+"> "+aliasExecuteTests+"("+aliasInfoTest+" "+ par_ +","+aliasTable+"<"+ cl_ +","+aliasTable+"<"+ met_ +","+aliasResult+">> "+ls_+"){"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordReturn()+" "+aliasExecuteLaunch+"("+par_+","+aliasExecuteFlat+"("+ls_+"));"+LR
                +SPACES_4+"}"+LR
                +groupClass(_kw, _content)
                +groupClassMethod(_kw, _content)
                +flat(_kw, _content)
                +launch(_kw, _content)
                +execute(_kw, _content)
                +convert(_kw, _content)
                +updateWithoutExc(_kw, _content)
                +updateErrInvoke(_kw, _content)
                +updateErr(_kw, _content)
                +"}"+LR;
    }
    public String groupClass(KeyWords _kw, LgNamesContent _content) {
        String st_ = _kw.getKeyWordStatic();
        AliasReflection ref_ = _content.getReflect();
        String cl_ = ref_.getAliasClassType();
        String par_ = custAliasParameters.getAliasExecute0GroupClass0();
        String nv_ = _kw.getKeyWordNew();
        String met_ = ref_.getAliasMethod();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String len_ = core_.getAliasArrayLength();
        String c_ = tr("c", _kw, pr_, core_, par_);
        String out_ = tr("out", _kw, pr_, core_, par_);
        String si_ = _kw.getKeyWordIf();
        String pour_ = _kw.getKeyWordFor();
        String v_ = _kw.getKeyWordVar();
        String m_ = tr("m", _kw, pr_, core_, par_);
        String e_ = tr("e", _kw, pr_, core_, par_);
        String cls_ = ref_.getAliasGetAllClasses();
        String bs_ = tr("bs", _kw, pr_, core_, par_);
        String as_ = tr("as", _kw, pr_, core_, par_);
        String tts_ = tr("tts", _kw, pr_, core_, par_);
        String decl_ = ref_.getAliasGetDeclaredMethods();
        String a0_ = tr("a0", _kw, pr_, core_, par_);
        String annots_ = ref_.getAliasGetAnnotations();
        String clKw_ = _kw.getKeyWordClass();
        String r_ = tr("r", _kw, pr_, core_, par_);
        String cast_ = _kw.getKeyWordCast();
        String nul_ = _kw.getKeyWordNull();
        String aParam_ = tr("aPar", _kw, pr_, core_, par_);
        String obUt_ = core_.getAliasObjectsUtil();
        String eq_ = core_.getAliasSameRef();
        String paramAnn_ = tr("ann", _kw, pr_, core_, par_);
        String loc_ = tr("loc", _kw, pr_, core_, par_);
        String locType_ = tr("lTy", _kw, pr_, core_, par_);
        String p_ = tr("p", _kw, pr_, core_, par_);
        String iterer_ = _kw.getKeyWordContinue();
        AliasMathType mref_ = _content.getMathRef();
        String math_ = mref_.getAliasMath();
        String gt_ = mref_.getAliasGt();
        return SPACES_4+_kw.getKeyWordPublic()+" "+ st_ +" "+aliasTable+"<"+ cl_ +","+aliasList+"<"+aliasExecutedTest+">> "+aliasExecuteGroupClass+"("+aliasInfoTest+" "+ par_ +") {"+LR
                +SPACES_4+SPACES_4+aliasTable+"<"+ cl_ +","+aliasList+"<"+aliasExecutedTest+">> "+ out_ +" = "+ nv_ +"();"+LR
                +SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ c_ +": "+ st_ +"("+ cl_ +")."+ cls_ +"()){"+LR
                +SPACES_4+SPACES_4+SPACES_4+aliasList+"<"+ met_ +"> "+ bs_ +" = "+ nv_ +"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+aliasList+"<"+ met_ +"> "+ as_ +" = "+ nv_ +"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+aliasList+"<"+aliasExecutedTest+"> "+ tts_ +" = "+ nv_ +"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ m_ +": "+ c_ +"."+ decl_ +"()){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ e_ +" = "+ nv_ +" "+aliasExecutedTest+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ a0_ +" = "+ m_ +"."+ annots_ +"("+ clKw_ +"("+aliasTest+"));"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ r_ +" = "+ m_ +"."+ annots_ +"("+ clKw_ +"("+aliasArgsTest+"));"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" ("+ st_ +"("+ obUt_ +")."+ eq_ +"("+ a0_+"."+len_ +",1)){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ e_ +"."+aliasExecutedTestTest+" = "+ m_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ e_ +"."+aliasExecutedTestAnnotations+" = "+ a0_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ e_ +"."+aliasExecutedTestArgsAnnot+" = "+ r_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ e_ +"."+aliasExecutedTestBefore+" = "+ bs_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ e_ +"."+aliasExecutedTestAfter+" = "+ as_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ aParam_ +" = "+ m_ +"."+ annots_ +"("+ clKw_ +"("+aliasParameters+"));"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" ("+ st_ +"("+ obUt_ +")."+ eq_ +"("+ aParam_ +"."+len_+", "+"1"+")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ paramAnn_ +" = "+ cast_ +"("+aliasParameters+")"+ aParam_ +"["+"0"+"];"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ loc_ +" = "+ paramAnn_ +"."+aliasParametersMethod+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ locType_ +" = "+ paramAnn_ +"."+aliasParametersLocation+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" ("+ st_ +"("+ obUt_ +")."+ eq_ +"("+ locType_ +","+ nul_ +")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ locType_ +" = "+ c_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ p_ +": "+ locType_ +"."+ decl_ +"()){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ st_ +"("+ obUt_ +")."+ eq_ +"("+ p_ +"."+ref_.getAliasGetName()+"(), "+ loc_ +")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ iterer_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ p_ +"."+ref_.getAliasIsStatic()+"()){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ iterer_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ st_ +"("+ obUt_ +")."+ eq_ +"("+ p_ +"."+ref_.getAliasGetParameterTypes()+"()."+len_+","+"0"+")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ iterer_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ e_ +"."+aliasExecutedTestMethod+" = "+ p_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+_kw.getKeyWordBreak()+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ par_ +"."+aliasInfoTestCount+" = "+ st_ +"("+ math_ +")."+mref_.getAliasPlus()+"("+ par_ +"."+aliasInfoTestCount+","+"1"+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ tts_ +"."+aliasAddLi+"("+ e_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" ("+ st_ +"("+ math_ +")."+ gt_ +"("+ m_ +"."+ annots_ +"("+ clKw_ +"("+aliasBefore+"))."+len_+","+"0"+")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ bs_ +"."+aliasAddLi+"("+ m_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" ("+ st_ +"("+ math_ +")."+ gt_ +"("+ m_ +"."+ annots_ +"("+ clKw_ +"("+aliasAfter+"))."+len_+","+"0"+")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ as_ +"."+aliasAddLi+"("+ m_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_ +" ("+ st_ +"("+ math_ +")."+gt_+"("+ tts_ +"."+aliasSizeLi+"(),"+"0"+")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ out_ +"."+aliasAddTa+"("+ c_ +","+ tts_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordReturn()+" "+ out_ +";"+LR
                +SPACES_4+"}"+LR;
    }
    public String groupClassMethod(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        AliasReflection ref_ = _content.getReflect();
        String st_ = _kw.getKeyWordStatic();
        String cl_ = ref_.getAliasClassType();
        String met_ = ref_.getAliasMethod();
        String nv_ = _kw.getKeyWordNew();
        String parInfo_ =  custAliasParameters.getAliasExecute0GroupClassMethod0();
        String out_ = custAliasParameters.getAliasExecute0GroupClassMethod1();
        String results_ = tr("results", _kw,pr_,core_, parInfo_, out_);
        String pour_ = _kw.getKeyWordFor();
        String v_ = _kw.getKeyWordVar();
        String c_ = tr("c", _kw,pr_,core_, parInfo_, out_);
        String tts_ = tr("tts", _kw, pr_, core_, parInfo_, out_);
        String e_ = tr("e", _kw, pr_, core_, parInfo_, out_);
        String l_ = tr("l", _kw, pr_, core_, parInfo_, out_);
        String tt_ = tr("tt", _kw, pr_, core_, parInfo_, out_);
        String nul_ = _kw.getKeyWordNull();
        String obj_ = core_.getAliasObject();
        String si_ = _kw.getKeyWordIf();
        String arr_ = tr("arr", _kw, pr_, core_, parInfo_, out_);
        String exc_ = tr("exc", _kw, pr_, core_, parInfo_, out_);
        String tr_ = _kw.getKeyWordTry();
        String capt_ = _kw.getKeyWordCatch();
        String els_ = _kw.getKeyWordElse();
        String tarExc_ = ref_.getAliasInvokeTarget();
        String inv_ = ref_.getAliasInvoke();
        String o_ = tr("o", _kw, pr_, core_, parInfo_, out_);
        String instancede_ = _kw.getKeyWordInstanceof();
        String cast_ = _kw.getKeyWordCast();
        String p_ = tr("p", _kw, pr_, core_, parInfo_, out_);
        AliasCharSequenceType charSeq_ = _content.getCharSeq();
        String strBuil_ = charSeq_.getAliasStringBuilder();
        String append_ = charSeq_.getAliasAppend();
        String params_ = tr("params", _kw, pr_, core_, parInfo_, out_);
        String nbParams_ = tr("nbParams", _kw, pr_, core_, parInfo_, out_);
        String i_ = tr("i", _kw, pr_, core_, parInfo_, out_);
        AliasMathType mathRef_ = _content.getMathRef();
        String math_ = mathRef_.getAliasMath();
        String minus_ = mathRef_.getAliasMinus();
        String gq_ = mathRef_.getAliasGt();
        String res_ =  tr("res", _kw, pr_, core_, parInfo_, out_);
        String plus_ = mathRef_.getAliasPlus();
        return SPACES_4+_kw.getKeyWordPublic()+" "+ st_ +" "+aliasTable+"<"+ cl_ +","+aliasTable+"<"+ met_ +","+aliasResult+">> "+aliasExecuteGroupClassMethod+"("+aliasInfoTest+" "+ parInfo_ +","+aliasTable+"<"+ cl_ +","+aliasList+"<"+aliasExecutedTest+">> "+ out_ +") {"+LR
                +SPACES_4+SPACES_4+aliasTable+"<"+ cl_ +","+aliasTable+"<"+ met_ +","+aliasResult+">> "+ results_ +" = "+ nv_ +"();"+LR
                +SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ c_ +", "+ v_ +" "+ l_ +": "+ out_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+aliasTable+"<"+ met_ +","+aliasResult+"> "+ tts_ +" = "+ nv_ +"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ e_ +": "+ l_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ tt_ +" = "+ e_ +"."+aliasExecutedTestTest+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ st_ +"("+ core_.getAliasObjectsUtil()+")."+core_.getAliasSameRef()+"("+ e_ +"."+aliasExecutedTestMethod+","+ nul_ +")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ obj_ +" "+ arr_ +" = "+ nul_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ obj_ +" "+ exc_ +" = "+ nul_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ tr_ +" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ arr_ +" = "+ e_ +"."+aliasExecutedTestMethod+"."+ inv_ +"("+ nul_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"} "+ capt_ +" ("+ tarExc_ +" "+ o_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ exc_ +" = "+ o_ +"."+ core_.getAliasGetCause()+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" ("+ arr_ +" "+ instancede_ +" "+ obj_ +"[][]){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ p_ +": "+ cast_ +"("+ obj_ +"[][])"+ arr_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ params_ +" = "+ nv_ +" "+ strBuil_ +"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ nbParams_ +" = "+ p_ +"."+core_.getAliasArrayLength()+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+_kw.getKeyWordIter()+" ("+primTypes_.getAliasPrimInteger()+" "+ i_ +" = "+"0"+"; "+ st_ +"("+ math_ +")."+ minus_ +"("+ nbParams_ +","+"1"+"); "+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ params_ +"."+append_+"("+aliasExecuteConvert+"("+ p_ +"["+ i_ +"]))."+append_+"(\",\");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ si_ +" ("+ st_ +"("+ math_ +")."+ gq_ +"("+ nbParams_ +","+"0"+")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ params_ +"."+append_+"("+aliasExecuteConvert+"("+ p_ +"["+ st_ +"("+ math_ +")."+ minus_ +"("+ nbParams_ +","+"1"+")]));"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+aliasResult+" "+ res_ +" = "+ nv_ +"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ res_ +"."+ params_ +" = "+ params_ +"."+charSeq_.getAliasCharSequenceToString()+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ parInfo_ +"."+aliasInfoTestCurrentParams+" = "+ res_ +"."+ params_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ res_ +"."+aliasResultContainer+" = "+ c_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ res_ +"."+aliasResultExecuted+" = "+ e_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ res_ +"."+aliasResultArgs+" = "+ p_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ tts_ +"."+aliasAddTa+"("+ tt_ +","+ res_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ parInfo_ +"."+aliasInfoTestCalls+" = "+ st_ +"("+ math_ +")."+ plus_ +"("+ parInfo_ +"."+aliasInfoTestCalls+","+"1"+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"} "+ els_ +" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ res_ +" = "+aliasExecuteSetupError+"("+ e_ +","+ exc_ +",-"+"1"+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ res_ +"."+aliasResultContainer+" = "+ c_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ res_ +"."+aliasResultExecuted+" = "+ e_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ res_ +"."+aliasResultArgs+" = "+ nul_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ tts_ +"."+aliasAddTa+"("+ tt_ +","+ res_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ parInfo_ +"."+aliasInfoTestCalls+" = "+ st_ +"("+ math_ +")."+ plus_ +"("+ parInfo_ +"."+aliasInfoTestCalls+","+"1"+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"} "+ els_ +" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+aliasResult+" "+ res_ +" = "+ nv_ +"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ res_ +"."+aliasResultContainer+" = "+ c_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ res_ +"."+aliasResultExecuted+" = "+ e_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ tts_ +"."+aliasAddTa+"("+ tt_ +","+ res_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ parInfo_ +"."+aliasInfoTestCalls+" = "+ st_ +"("+ math_ +")."+ plus_ +"("+ parInfo_ +"."+aliasInfoTestCalls+","+"1"+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_ +" ("+ st_ +"("+ math_ +")."+ gq_ +"("+ tts_ +"."+aliasSizeTa+"(),"+"0"+")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ results_ +"."+aliasAddTa+"("+ c_ +","+ tts_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordReturn()+" "+ results_ +";"+LR
                +SPACES_4+"}"+LR;
    }
    public String flat(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        AliasReflection ref_ = _content.getReflect();
        String st_ = _kw.getKeyWordStatic();
        String cl_ = ref_.getAliasClassType();
        String met_ = ref_.getAliasMethod();
        String nv_ = _kw.getKeyWordNew();
        String par_ = custAliasParameters.getAliasExecute0Flat0();
        String gp_ = tr("res", _kw, pr_, core_, par_);
        String c_ = tr("c", _kw, pr_, core_, par_);
        String v_ = _kw.getKeyWordVar();
        String f_ = _kw.getKeyWordFor();
        String l_ = tr("l", _kw, pr_, core_, par_);
        String m_ = tr("m", _kw, pr_, core_, par_);
        String r_ = tr("r", _kw, pr_, core_, par_);
        return SPACES_4+_kw.getKeyWordPublic()+" "+st_+" "+aliasTable+"<"+ met_ +","+aliasResult+"> "+aliasExecuteFlat+"("+aliasTable+"<"+ cl_ +","+aliasTable+"<"+ met_ +","+aliasResult+">> "+par_+"){"+LR
                +SPACES_4+SPACES_4+aliasTable+"<"+ met_ +","+aliasResult+"> "+ gp_ +" = "+nv_+"();"+LR
                +SPACES_4+SPACES_4+ f_ +" ("+ v_ +" "+c_+", "+ v_ +" "+ l_ +": "+par_+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ f_ +" ("+ v_ +" "+m_+", "+ v_ +" "+r_+": "+ l_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ gp_ +"."+aliasAddTa+"("+m_+","+r_+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordReturn()+" "+ gp_ +";"+LR
                +SPACES_4+"}"+LR;
    }
    public String launch(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String st_ = _kw.getKeyWordStatic();
        String nv_ = _kw.getKeyWordNew();
        String met_ = _content.getReflect().getAliasMethod();
        String parInfo_ = custAliasParameters.getAliasExecute0Launch0();
        String parRes_ = custAliasParameters.getAliasExecute0Launch1();
        String cast_ = _kw.getKeyWordCast();
        String tts0_ = tr("tts0", _kw, pr_, core_, parInfo_, parRes_);
        String tt0_ = tr("tt0", _kw, pr_, core_, parInfo_, parRes_);
        String pour_ = _kw.getKeyWordFor();
        String v_ = _kw.getKeyWordVar();
        String m_ = tr("m", _kw, pr_, core_, parInfo_, parRes_);
        String tt1_ = tr("tt1", _kw, pr_, core_, parInfo_, parRes_);
        String cap_ = _kw.getKeyWordCatch();
        String o_ = tr("o", _kw, pr_, core_, parInfo_, parRes_);
        String l_ = tr("l", _kw, pr_, core_, parInfo_, parRes_);
        String ret_ = _kw.getKeyWordReturn();
        String res_ = tr("res", _kw, pr_, core_, parInfo_, parRes_);
        return SPACES_4+_kw.getKeyWordPublic()+" "+ st_ +" "+aliasTable+"<"+ met_ +","+aliasResult+"> "+aliasExecuteLaunch+"("+ aliasInfoTest+" "+parInfo_+","+aliasTable+"<"+ met_ +","+aliasResult+"> "+parRes_+"){"+LR
                +SPACES_4+SPACES_4+aliasList+"<"+aliasFuture+"> "+ tts0_ +" = "+ nv_ +"();"+LR
                +SPACES_4+SPACES_4+aliasExecutorService+" "+ tt0_ +" = "+ nv_ +"("+parInfo_+"."+aliasInfoTestNbThreads+");"+LR
                +SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ m_ +", "+ v_ +" "+ l_ +": "+parRes_+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+aliasRunnable+" "+ tt1_ +" = () -> {"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ _kw.getKeyWordTry()+" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ st_ +"("+aliasThread+")."+aliasArgs+"(("+ cast_ +"("+aliasArgsTest+")"+ l_ +"."+aliasResultExecuted+"."+aliasExecutedTestArgsAnnot+"["+"0"+"])."+aliasArgsTestArgsValue+"());"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"} "+ cap_ +" ("+core_.getAliasObject()+" "+ o_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ st_ +"("+aliasThread+")."+aliasArgs+"("+ nv_ +"["+"0"+"]);"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"} "+ cap_ +" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ st_ +"("+aliasThread+")."+aliasArgs+"("+ nv_ +"["+"0"+"]);"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+parInfo_+"."+aliasInfoTestCurrentMethod+" = "+ l_ +"."+aliasResultExecuted+"."+aliasExecutedTestTest+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+parInfo_+"."+aliasInfoTestCurrentParams+" = "+ l_ +"."+aliasResultParams+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+_kw.getKeyWordIf()+" ("+ st_ +"("+core_.getAliasObjectsUtil()+")."+core_.getAliasSameRef()+"("+ l_ +"."+aliasResultArgs+","+_kw.getKeyWordNull()+")) {"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+parInfo_+"."+aliasInfoTestDone+"."+aliasIncrementAndGetAtomic+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+ ret_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ res_ +" = "+aliasExecuteExecute+"("+ l_ +"."+aliasResultExecuted+","+ l_ +"."+aliasResultContainer+","+ l_ +"."+aliasResultArgs+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ l_ +"."+aliasResultTime+" = "+ res_ +"."+aliasResultTime+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ l_ +"."+aliasResultSuccess+" = "+ res_ +"."+aliasResultSuccess+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ l_ +"."+aliasResultFailMessage+" = "+ res_ +"."+aliasResultFailMessage+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+parInfo_+"."+aliasInfoTestDone+"."+aliasIncrementAndGetAtomic+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+"};"+LR
                +SPACES_4+SPACES_4+SPACES_4+ tts0_ +"."+aliasAddLi+"("+ tt0_ +"."+aliasExecutorServiceSubmit+"("+ tt1_ +"));"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ m_ +": "+ tts0_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ m_ +"."+aliasFutureWait+"();"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ tt0_ +"."+aliasExecutorServiceShutdown+"();"+LR
                +SPACES_4+SPACES_4+ st_ +"("+aliasThread+")."+aliasJoinOthers+"();"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+parRes_+";"+LR
                +SPACES_4+"}"+LR;
    }
    public String execute(KeyWords _kw, LgNamesContent _content) {
        String st_ = _kw.getKeyWordStatic();
        AliasReflection ref_ = _content.getReflect();
        String cl_ = ref_.getAliasClassType();
        AliasCore core_ = _content.getCoreNames();
        String obj_ = core_.getAliasObject();
        String execTest_ = custAliasParameters.getAliasExecute0Run0();
        String type_ = custAliasParameters.getAliasExecute0Run1();
        String args_ = custAliasParameters.getAliasExecute0Run2();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        String faux_ = _kw.getKeyWordFalse();
        String v_ = _kw.getKeyWordVar();
        String processEx_ = tr("processEx", _kw, pr_, core_, execTest_, type_, args_);
        String stTime_ = tr("stTime", _kw, pr_, core_, execTest_, type_, args_);
        String diff_ = tr("diff", _kw, pr_, core_, execTest_, type_, args_);
        String ctor_ = tr("ctor", _kw, pr_, core_, execTest_, type_, args_);
        String classTest_ = tr("classTest", _kw, pr_, core_, execTest_, type_, args_);
        String pour_ = _kw.getKeyWordFor();
        String b0_ = tr("b0", _kw, pr_, core_, execTest_, type_, args_);
        String inv_ = ref_.getAliasInvoke();
        String ret_ = _kw.getKeyWordReturn();
        String o_ = tr("o", _kw, pr_, core_, execTest_, type_, args_);
        String capt_ = _kw.getKeyWordCatch();
        return SPACES_4+_kw.getKeyWordPublic()+" "+ st_ +" "+aliasResult+" "+aliasExecuteExecute+"("+aliasExecutedTest+" "+ execTest_ +","+ cl_ +" "+ type_ +","+ obj_ +"[] "+ args_ +"){"+LR
                +SPACES_4+SPACES_4+ v_ +" "+ processEx_ +" = "+ faux_ +";"+LR
                +SPACES_4+SPACES_4+ v_ +" "+ stTime_ +" = "+ st_ +"("+aliasThread+")."+aliasThreadCurrentNanoTime+"();"+LR
                +SPACES_4+SPACES_4+primTypes_.getAliasPrimLong()+" "+ diff_ +" = -"+"1"+";"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordTry()+" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ ctor_ +" = "+ type_ +"."+ref_.getAliasGetDeclaredConstructors()+"("+ faux_ +")["+"0"+"];"+LR
                +SPACES_4+SPACES_4+SPACES_4+ processEx_ +" = "+_kw.getKeyWordTrue()+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+ v_ +" "+ classTest_ +" = "+ ctor_ +"."+ref_.getAliasNewInstance()+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ b0_ +": "+ execTest_ +"."+aliasExecutedTestBefore+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ b0_ +"."+ inv_ +"("+ classTest_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+ stTime_ +" = "+ st_ +"("+aliasThread+")."+aliasThreadCurrentNanoTime+"();"+LR
                +SPACES_4+SPACES_4+SPACES_4+ execTest_ +"."+aliasExecutedTestTest+"."+ inv_ +"("+ classTest_ +", "+ args_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+ diff_ +" = "+ st_ +"("+aliasThread+")."+aliasThreadCurrentNanoTime+"()-"+ stTime_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+ pour_ +" ("+ v_ +" "+ b0_ +": "+ execTest_ +"."+aliasExecutedTestAfter+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ b0_ +"."+ inv_ +"("+ classTest_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+ ret_ +" "+aliasExecuteSetupNoException+"("+ execTest_ +","+ diff_ +");"+LR
                +SPACES_4+SPACES_4+"} "+ capt_ +" ("+ ref_.getAliasInvokeTarget()+" "+ o_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ diff_ +" = "+ diff_ +"==-"+"1"+" ? "+ st_ +"("+aliasThread+")."+aliasThreadCurrentNanoTime+"()-"+ stTime_ +" : "+ diff_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+ ret_ +" "+aliasExecuteSetupError+"("+ execTest_ +","+ o_ +","+ processEx_ +","+ diff_ +");"+LR
                +SPACES_4+SPACES_4+"} "+ capt_ +" ("+ obj_ +" "+ o_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ diff_ +" = "+ diff_ +"==-"+"1"+" ? "+ st_ +"("+aliasThread+")."+aliasThreadCurrentNanoTime+"()-"+ stTime_ +" : "+ diff_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+ ret_ +" "+aliasExecuteSetupError+"("+ execTest_ +","+ o_ +","+ diff_ +");"+LR
                +SPACES_4+SPACES_4+"} "+ capt_ +" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+ diff_ +" = "+ diff_ +"==-"+"1"+" ? "+ st_ +"("+aliasThread+")."+aliasThreadCurrentNanoTime+"()-"+ stTime_ +" : "+ diff_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+ ret_ +" "+aliasExecuteSetupError+"("+ execTest_ +","+_kw.getKeyWordNull()+","+ diff_ +");"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+"}"+LR;
    }
    public String convert(KeyWords _kw, LgNamesContent _content) {
        String st_ = _kw.getKeyWordStatic();
        AliasReflection ref_ = _content.getReflect();
        String cl_ = ref_.getAliasClassType();
        AliasCore core_ = _content.getCoreNames();
        String obj_ = core_.getAliasObject();
        String elt_ = custAliasParameters.getAliasExecute0ExecuteConvert0();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        String ret_ = _kw.getKeyWordReturn();
        String e_ = tr("e", _kw, pr_, core_, elt_);
        String capt_ = _kw.getKeyWordCatch();
        String gCl_ = ref_.getAliasGetClass();
        String clName_ = ref_.getAliasGetName();
        return SPACES_4+_kw.getKeyWordPublic()+" "+st_+" "+_content.getCharSeq().getAliasString()+" "+aliasExecuteConvert+"("+obj_+" "+elt_+"){"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordTry()+" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+ret_+" "+st_+"("+core_.getAliasStringUtil()+")."+core_.getAliasStringUtilValueOf()+"("+elt_+");"+LR
                +SPACES_4+SPACES_4+"} "+capt_+" ("+obj_+" "+e_+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ret_+" "+st_+"("+cl_+")."+ gCl_ +"("+elt_+")."+ clName_ +"();"+LR
                +SPACES_4+SPACES_4+"} "+capt_+" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+ret_+" "+st_+"("+cl_+")."+ gCl_ +"("+elt_+")."+ clName_ +"();"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+"}"+LR;
    }
    public String updateWithoutExc(KeyWords _kw, LgNamesContent _content) {
        String st_ = _kw.getKeyWordStatic();
        AliasCore core_ = _content.getCoreNames();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        String faux_ = _kw.getKeyWordFalse();
        String v_ = _kw.getKeyWordVar();
        String ret_ = _kw.getKeyWordReturn();
        String curTest_ = custAliasParameters.getAliasExecute0ExecuteSetupNoException0();
        String tps_ = custAliasParameters.getAliasExecute0ExecuteSetupNoException1();
        String res_ = tr("res", _kw, pr_, core_, curTest_, tps_);
        String nv_ = _kw.getKeyWordNew();
        String tt_ = tr("tt", _kw, pr_, core_, curTest_, tps_);
        String cast_ = _kw.getKeyWordCast();
        String lg_ = primTypes_.getAliasPrimLong();
        String si_ = _kw.getKeyWordIf();
        String nul_ = _kw.getKeyWordNull();
        AliasCharSequenceType charSeq_ = _content.getCharSeq();
        String strBui_ = charSeq_.getAliasStringBuilder();
        String append_ = charSeq_.getAliasAppend();
        String chaine_ = charSeq_.getAliasCharSequenceToString();
        return SPACES_4+_kw.getKeyWordPublic()+" "+ st_ +" "+aliasResult+" "+aliasExecuteSetupNoException+"("+aliasExecutedTest+" "+ curTest_ +","+ lg_ +" "+ tps_ +"){"+LR
                +SPACES_4+SPACES_4+v_+" "+res_+" = "+ nv_ +" "+aliasResult+"();"+LR
                +SPACES_4+SPACES_4+res_+"."+aliasResultTime+" = "+ tps_ +"/"+ MILLION +";"+LR
                +SPACES_4+SPACES_4+v_+" "+ tt_ +" = "+ cast_ +"("+aliasTest+")"+ curTest_ +"."+aliasExecutedTestAnnotations+"["+"0"+"];"+LR
                +SPACES_4+SPACES_4+ si_ +" ("+ tt_ +"."+aliasTestNullException+"()){"+LR
                +SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultSuccess+" = "+faux_+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultFailMessage+" = "+ nv_ +" "+ strBui_ +"(\"!=\")."+ append_ +"("+ st_ +"("+core_.getAliasStringUtil()+")."+core_.getAliasStringUtilValueOf()+"("+ nul_ +"))."+ chaine_ +"();"+LR
                +SPACES_4+SPACES_4+"} "+_kw.getKeyWordElse()+" "+si_+" (!"+ st_ +"("+core_.getAliasObjectsUtil()+")."+core_.getAliasSameRef()+"("+ tt_ +"."+aliasTestException+"(),"+ nul_ +")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultSuccess+" = "+faux_+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultFailMessage+" = "+ nv_ +" "+ strBui_ +"(\"!=\")."+ append_ +"("+ tt_ +"."+aliasTestException+"()."+_content.getReflect().getAliasGetName()+"())."+ chaine_ +"();"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ret_+" "+res_+";"+LR
                +SPACES_4+"}"+LR;
    }
    public String updateErrInvoke(KeyWords _kw, LgNamesContent _content) {
        String st_ = _kw.getKeyWordStatic();
        AliasCore core_ = _content.getCoreNames();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        String faux_ = _kw.getKeyWordFalse();
        String v_ = _kw.getKeyWordVar();
        String ret_ = _kw.getKeyWordReturn();
        String curTest_ = custAliasParameters.getAliasExecute0ExecuteSetupError0();
        String err_ = custAliasParameters.getAliasExecute0ExecuteSetupError1();
        String spec_ = custAliasParameters.getAliasExecute0ExecuteSetupError2();
        String tps_ = custAliasParameters.getAliasExecute0ExecuteSetupError3();
        String res_ = tr("res", _kw, pr_, core_, curTest_, err_, spec_, tps_);
        String nv_ = _kw.getKeyWordNew();
        String ex_ = tr("ex", _kw, pr_, core_, curTest_, err_, spec_, tps_);
        String tt_ = tr("tt", _kw, pr_, core_, curTest_, err_, spec_, tps_);
        String cast_ = _kw.getKeyWordCast();
        String lg_ = primTypes_.getAliasPrimLong();
        String si_ = _kw.getKeyWordIf();
        String els_ = _kw.getKeyWordElse();
        String nul_ = _kw.getKeyWordNull();
        AliasReflection ref_ = _content.getReflect();
        String strUt_ = core_.getAliasStringUtil();
        String objUt_ = core_.getAliasObjectsUtil();
        String eq_ = core_.getAliasSameRef();
        String valeurDe_ = core_.getAliasStringUtilValueOf();
        String cl_ = ref_.getAliasClassType();
        String estAssignableDe_ = ref_.getAliasIsAssignableFrom();
        String gCl_ = ref_.getAliasGetClass();
        return SPACES_4+_kw.getKeyWordPublic()+" "+ st_ +" "+aliasResult+" "+aliasExecuteSetupError+"("+aliasExecutedTest+" "+curTest_+", "+ ref_.getAliasInvokeTarget()+" "+err_+", "+primTypes_.getAliasPrimBoolean()+" "+spec_+", "+lg_+" "+tps_+"){"+LR
                +SPACES_4+SPACES_4+v_+" "+res_+" = "+nv_+" "+aliasResult+"();"+LR
                +SPACES_4+SPACES_4+res_+"."+aliasResultTime+" = "+tps_+"/"+ MILLION +";"+LR
                +SPACES_4+SPACES_4+v_+" "+ex_+" = "+err_+"."+core_.getAliasGetCause()+"();"+LR
                +SPACES_4+SPACES_4+si_+" ("+spec_+") {"+LR
                +SPACES_4+SPACES_4+SPACES_4+v_+" "+tt_+" = "+cast_+"("+aliasTest+")"+curTest_+"."+aliasExecutedTestAnnotations+"["+"0"+"];"+LR
                +SPACES_4+SPACES_4+SPACES_4+si_+" ("+tt_+"."+aliasTestNullException+"()){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+si_+" (!"+ st_ +"("+ objUt_ +")."+ eq_ +"("+ex_+","+nul_+")){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultSuccess+" = "+faux_+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultFailMessage+" = "+ st_ +"("+ strUt_ +")."+ valeurDe_ +"("+ex_+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+"} "+els_+" "+si_+" (!"+ st_ +"("+ objUt_ +")."+ eq_ +"("+tt_+"."+aliasTestException+"(),"+nul_+")) {"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+si_+" (!"+tt_+"."+aliasTestException+"()."+ estAssignableDe_ +"("+ st_ +"("+ cl_ +")."+ gCl_ +"("+ex_+"))){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultSuccess+" = "+faux_+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultFailMessage+" = "+ st_ +"("+ strUt_ +")."+ valeurDe_ +"("+ex_+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+SPACES_4+"} "+els_+" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultSuccess+" = "+faux_+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultFailMessage+" = "+ st_ +"("+ strUt_ +")."+ valeurDe_ +"("+ex_+");"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"} "+els_+" {"+LR
                +SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultSuccess+" = "+faux_+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+res_+"."+aliasResultFailMessage+" = "+ st_ +"("+ strUt_ +")."+ valeurDe_ +"("+ex_+");"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ret_+" "+res_+";"+LR
                +SPACES_4+"}"+LR;
    }
    public String updateErr(KeyWords _kw, LgNamesContent _content) {
        String st_ = _kw.getKeyWordStatic();
        AliasCore core_ = _content.getCoreNames();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        String faux_ = _kw.getKeyWordFalse();
        String v_ = _kw.getKeyWordVar();
        String ret_ = _kw.getKeyWordReturn();
        String curTest_ = custAliasParameters.getAliasExecute1ExecuteSetupError0();
        String err_ = custAliasParameters.getAliasExecute1ExecuteSetupError1();
        String time_ = custAliasParameters.getAliasExecute1ExecuteSetupError2();
        String res_ = tr("res", _kw, pr_, core_, curTest_, err_, time_);
        String nv_ = _kw.getKeyWordNew();
        String lg_ = primTypes_.getAliasPrimLong();
        String strUt_ = core_.getAliasStringUtil();
        String valeurDe_ = core_.getAliasStringUtilValueOf();
        return SPACES_4+_kw.getKeyWordPublic()+" "+st_+" "+aliasResult+" "+aliasExecuteSetupError+"("+aliasExecutedTest+" "+curTest_+", "+core_.getAliasObject()+" "+err_+", "+lg_+" "+time_+"){"+LR
                +SPACES_4+SPACES_4+v_+" "+res_+" = "+nv_+" "+aliasResult+"();"+LR
                +SPACES_4+SPACES_4+res_+"."+aliasResultTime+" = "+time_+"/"+ MILLION +";"+LR
                +SPACES_4+SPACES_4+res_+"."+aliasResultSuccess+" = "+faux_+";"+LR
                +SPACES_4+SPACES_4+res_+"."+aliasResultFailMessage+" = "+st_+"("+strUt_+")."+valeurDe_+"("+err_+");"+LR
                +SPACES_4+SPACES_4+ret_+" "+res_+";"+LR
                +SPACES_4+"}"+LR;
    }
    public String annotations(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        String cl_ = _kw.getKeyWordClass();
        String ann_ = _kw.getKeyWordAnnotation();
        String nu_ = _kw.getKeyWordNull();
        String i_ = _content.getPrimTypes().getAliasPrimInteger();
        String l_ = _content.getPrimTypes().getAliasPrimLong();
        String m_ = _content.getReflect().getAliasMethod();
        String str_ = _content.getCharSeq().getAliasString();
        String b_ = _content.getPrimTypes().getAliasPrimBoolean();
        String c_ = _content.getReflect().getAliasClassType();
        String anType_ = _content.getReflect().getAliasAnnotationType();
        return pub_ +" "+ cl_ +" "+aliasInfoTest+"{"+LR
                +SPACES_4+ pub_ +" "+ i_ +" "+aliasInfoTestCount+";"+LR
                +SPACES_4+ pub_ +" "+aliasAtomicInteger+" "+aliasInfoTestDone+"="+_kw.getKeyWordNew()+"();"+LR
                +SPACES_4+ pub_ +" "+ i_ +" "+aliasInfoTestNbThreads+";"+LR
                +SPACES_4+ pub_ +" "+ l_ +" "+aliasInfoTestCalls+";"+LR
                +SPACES_4+ pub_ +" "+ m_ +" "+aliasInfoTestCurrentMethod+";"+LR
                +SPACES_4+ pub_ +" "+ str_ +" "+aliasInfoTestCurrentParams+";"+LR
                +"}"+LR
                + pub_ +" "+ cl_ +" "+aliasResult+"{"+LR
                +SPACES_4+ pub_ +" "+ l_ +" "+aliasResultTime+" = -1"+";"+LR
                +SPACES_4+ pub_ +" "+ b_ +" "+aliasResultSuccess+" = "+_kw.getKeyWordTrue()+";"+LR
                +SPACES_4+ pub_ +" "+ str_ +" "+aliasResultParams+" = \"\";"+LR
                +SPACES_4+ pub_ +" "+ str_ +" "+aliasResultFailMessage+" = \"\";"+LR
                +SPACES_4+ pub_ +" "+ c_ +" "+ aliasResultContainer +";"+LR
                +SPACES_4+ pub_ +" "+aliasExecutedTest+" "+ aliasResultExecuted +";"+LR
                +SPACES_4+ pub_ +" "+_content.getCoreNames().getAliasObject()+"[] "+ aliasResultArgs +" = {};"+LR
                +"}"+LR
                + pub_ +" "+ cl_ +" "+aliasExecutedTest+"{"+LR
                +SPACES_4+ pub_ +" "+aliasList+"<"+ m_ +"> "+aliasExecutedTestBefore+";"+LR
                +SPACES_4+ pub_ +" "+ m_ +" "+aliasExecutedTestTest+";"+LR
                +SPACES_4+ pub_ +" "+ anType_ +"[] "+aliasExecutedTestAnnotations+";"+LR
                +SPACES_4+ pub_ +" "+ anType_ +"[] "+aliasExecutedTestArgsAnnot+";"+LR
                +SPACES_4+ pub_ +" "+aliasList+"<"+ m_ +"> "+aliasExecutedTestAfter+";"+LR
                +SPACES_4+ pub_ +" "+ m_ +" "+aliasExecutedTestMethod+";"+LR
                +"}"+LR
                + pub_ +" "+ ann_ +" "+aliasBefore+"{}"+LR
                + pub_ +" "+ ann_ +" "+aliasAfter+"{}"+LR
                + pub_ +" "+ ann_ +" "+aliasParameters+"{"+LR
                +SPACES_4+ str_ +" "+aliasParametersMethod+"();"+LR
                +SPACES_4+ c_ +" "+aliasParametersLocation+"()"+ nu_ +";"+LR
                +"}"+LR
                + pub_ +" "+ ann_ +" "+aliasTest+"{"+LR
                +SPACES_4+ c_ +" "+aliasTestException+"()"+nu_+";"+LR
                +SPACES_4+ b_ +" "+aliasTestNullException+"()"+_kw.getKeyWordFalse()+";"+LR
                +"}"+LR
                + pub_ +" "+ ann_ +" "+aliasArgsTest+"{"+LR
                +SPACES_4+ str_ +"[] "+aliasArgsTestArgsValue+"(){};"+LR
                +"}"+LR;
    }
    public String assertType(KeyWords _kw, LgNamesContent _content) {
        String head_= _kw.getKeyWordPublic()+" "+_kw.getKeyWordAbstract()+" "+_kw.getKeyWordFinal()+" "+_kw.getKeyWordClass()+" "+aliasAssert+"{"+LR;
        return head_
                +assertMethodArrObjectLongs(_kw, _content)
                +assertMethodArrObjectDoubles(_kw, _content)
                +assertMethodArrObjectDoublesDelta(_kw, _content)
                +assertMethodArrObjectStrings(_kw, _content)
                +assertMethodArrObjectStringBuilders(_kw, _content)
                +assertMethodArrObjectBooleans(_kw, _content)
                +assertMethodObjectLongs(_kw, _content)
                +assertMethodObjectDoubles(_kw, _content)
                +assertMethodObjectDoublesDelta(_kw, _content)
                +assertMethodObjectStrings(_kw, _content)
                +assertMethodObjectStringBuilders(_kw, _content)
                +assertMethodObjectBooleans(_kw, _content)
                +assertMethodNotArrObjectLongs(_kw, _content)
                +assertMethodNotArrObjectDoubles(_kw, _content)
                +assertMethodNotArrObjectDoublesDelta(_kw, _content)
                +assertMethodNotArrObjectStrings(_kw, _content)
                +assertMethodNotArrObjectStringBuilders(_kw, _content)
                +assertMethodNotArrObjectBooleans(_kw, _content)
                +assertMethodNotObjectLongs(_kw, _content)
                +assertMethodNotObjectDoubles(_kw, _content)
                +assertMethodNotObjectDoublesDelta(_kw, _content)
                +assertMethodNotObjectStrings(_kw, _content)
                +assertMethodNotObjectStringBuilders(_kw, _content)
                +assertMethodNotObjectBooleans(_kw, _content)
                +assertMethodTrue(_kw, _content)
                +assertMethodFalse(_kw, _content)
                +assertMethodNull(_kw, _content)
                +assertMethodNotNull(_kw, _content)
                +assertMethodSame(_kw, _content)
                +assertMethodNotSame(_kw, _content)
                +checkArrsLda(_kw, _content)
                +checkArrLda(_kw, _content)
                +checkPairLda(_kw, _content)
                +checkSingleLda(_kw, _content)
                +checkArrsNotLda(_kw, _content)
                +checkArrNotLda(_kw, _content)
                +checkPairNotLda(_kw, _content)
                +checkSingleNotLda(_kw, _content)
                +"}"+LR;
    }
    public String assertMethodTrue(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        String type_ = primTypes_.getAliasPrimBoolean();
        AliasCore co_ = _content.getCoreNames();
        String a_ = custAliasParameters.getAliasAssert0AssertAssertTrue0();
        String d_ = tr("d", _kw, pr_, co_, a_);
        String th_ = lancerVar(SPACES_4 + SPACES_4 + SPACES_4, _kw, _content, _kw.getKeyWordTrue(), _kw.getKeyWordFalse(), d_);
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+aliasAssertAssertTrue+"("+type_+" "+a_+"){"+LR;
        String test_= SPACES_4+SPACES_4+_kw.getKeyWordIf()+"(!"+a_+"){"+LR;
        return head_+test_+th_+SPACES_4+SPACES_4+"}"+LR+SPACES_4+"}"+LR;
    }
    public String assertMethodFalse(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        String type_ = primTypes_.getAliasPrimBoolean();
        AliasCore co_ = _content.getCoreNames();
        String a_ = custAliasParameters.getAliasAssert0AssertAssertFalse0();
        String d_ = tr("d", _kw, pr_, co_, a_);
        String th_ = lancerVar(SPACES_4 + SPACES_4 + SPACES_4, _kw, _content, _kw.getKeyWordFalse(), _kw.getKeyWordTrue(), d_);
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+aliasAssertAssertFalse+"("+type_+" "+a_+"){"+LR;
        String test_= SPACES_4+SPACES_4+_kw.getKeyWordIf()+"("+a_+"){"+LR;
        return head_+test_+th_+SPACES_4+SPACES_4+"}"+LR+SPACES_4+"}"+LR;
    }
    public String assertMethodNull(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        String type_ = primTypes_.getAliasPrimBoolean();
        AliasCore co_ = _content.getCoreNames();
        String a_ = custAliasParameters.getAliasAssert0AssertAssertNull0();
        String d_ = tr("d", _kw, pr_, co_, a_);
        String th_ = lancerVar(SPACES_4 + SPACES_4 + SPACES_4, _kw, _content, _kw.getKeyWordNull(), a_, d_);
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+aliasAssertAssertNull+"("+type_+" "+a_+"){"+LR;
        String test_= SPACES_4+SPACES_4+_kw.getKeyWordIf()+"(!"+natEqStruct(_kw,_content,_kw.getKeyWordNull(), a_)+"){"+LR;
        return head_+test_+th_+SPACES_4+SPACES_4+"}"+LR+SPACES_4+"}"+LR;
    }
    public String assertMethodNotNull(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        String type_ = primTypes_.getAliasPrimBoolean();
        AliasCore co_ = _content.getCoreNames();
        String a_ = custAliasParameters.getAliasAssert0AssertAssertNotNull0();
        String d_ = tr("d", _kw, pr_, co_, a_);
        String th_ = lancerNonNull(SPACES_4 + SPACES_4 + SPACES_4, _kw, _content, d_);
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+aliasAssertAssertNotNull+"("+type_+" "+a_+"){"+LR;
        String test_= SPACES_4+SPACES_4+_kw.getKeyWordIf()+"("+natEqStruct(_kw,_content,_kw.getKeyWordNull(), a_)+"){"+LR;
        return head_+test_+th_+SPACES_4+SPACES_4+"}"+LR+SPACES_4+"}"+LR;
    }
    public String assertMethodObjectLongs(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert0AssertAssert0();
        String found_ = custAliasParameters.getAliasAssert0AssertAssert1();
        return assertMethodObject(_kw, _content, primTypes_.getAliasPrimLong(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodObjectDoubles(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert1AssertAssert0();
        String found_ = custAliasParameters.getAliasAssert1AssertAssert1();
        return assertMethodObject(_kw, _content, primTypes_.getAliasPrimDouble(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodObjectDoublesDelta(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert5AssertAssertDouble0();
        String found_ = custAliasParameters.getAliasAssert5AssertAssertDouble1();
        String delta_ = custAliasParameters.getAliasAssert5AssertAssertDouble2();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String d_ = tr("d", _kw, pr_, co_, exp_, found_, delta_);
        String th_ = lancerVar(SPACES_4 + SPACES_4 + SPACES_4, _kw, _content, exp_, found_, d_);
        String dow_ = primTypes_.getAliasPrimDouble();
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+aliasAssertAssert+"("+dow_+" "+exp_+","+dow_+" "+found_+","+dow_+" "+delta_+"){"+LR;
        String test_= SPACES_4+SPACES_4+_kw.getKeyWordIf()+"("+cmpStruct(_kw,_content,exp_,found_, "!=")+"&&"+deltaStruct(_kw,_content,exp_,found_,delta_, ">")+"){"+LR;
        return head_+test_+th_+SPACES_4+SPACES_4+"}"+LR+SPACES_4+"}"+LR;
    }
    public String assertMethodObjectStrings(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert2AssertAssert0();
        String found_ = custAliasParameters.getAliasAssert2AssertAssert1();
        return assertMethodObject(_kw, _content, _content.getCharSeq().getAliasString(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodObjectStringBuilders(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert3AssertAssert0();
        String found_ = custAliasParameters.getAliasAssert3AssertAssert1();
        return assertMethodObject(_kw, _content, _content.getCharSeq().getAliasStringBuilder(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodObjectBooleans(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert4AssertAssert0();
        String found_ = custAliasParameters.getAliasAssert4AssertAssert1();
        return assertMethodObject(_kw, _content, primTypes_.getAliasPrimBoolean(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodSame(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert0AssertAssertSame0();
        String found_ = custAliasParameters.getAliasAssert0AssertAssertSame1();
        return assertMethodObject(_kw, _content, _content.getCoreNames().getAliasObject(),exp_,found_, aliasAssertAssertSame);
    }
    public String assertMethodObject(KeyWords _kw, LgNamesContent _content, String _type, String _a, String _b, String _name) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String d_ = tr("d", _kw, pr_, co_, _a, _b);
        String th_ = lancerVar(SPACES_4 + SPACES_4 + SPACES_4, _kw, _content, _a, _b, d_);
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+ _name +"("+_type+" "+_a+","+_type+" "+_b+"){"+LR;
        String test_= SPACES_4+SPACES_4+_kw.getKeyWordIf()+"(!"+natEqStruct(_kw,_content,_a,_b)+"){"+LR;
        return head_+test_+th_+SPACES_4+SPACES_4+"}"+LR+SPACES_4+"}"+LR;
    }
    public String assertMethodArrObjectLongs(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert0AssertAssertArr0();
        String found_ = custAliasParameters.getAliasAssert0AssertAssertArr1();
        return assertMethodArrObject(_kw, _content, primTypes_.getAliasPrimLong(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodArrObjectDoubles(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert1AssertAssertArr0();
        String found_ = custAliasParameters.getAliasAssert1AssertAssertArr1();
        return assertMethodArrObject(_kw, _content, primTypes_.getAliasPrimDouble(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodArrObjectDoublesDelta(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert5AssertAssertArrDouble0();
        String found_ = custAliasParameters.getAliasAssert5AssertAssertArrDouble1();
        String delta_ = custAliasParameters.getAliasAssert5AssertAssertArrDouble2();
        AliasCore co_ = _content.getCoreNames();
        String dow_ = primTypes_.getAliasPrimDouble();
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+aliasAssertAssert+"("+dow_+"[] "+exp_+","+dow_+"[] "+found_+","+dow_+" "+delta_+"){"+LR;
        return head_+checkArrDelta(_kw, _content, exp_, found_, delta_)+SPACES_4+"}"+LR;
    }
    public String assertMethodArrObjectStrings(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert2AssertAssertArr0();
        String found_ = custAliasParameters.getAliasAssert2AssertAssertArr1();
        return assertMethodArrObject(_kw, _content, _content.getCharSeq().getAliasString(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodArrObjectStringBuilders(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert3AssertAssertArr0();
        String found_ = custAliasParameters.getAliasAssert3AssertAssertArr1();
        return assertMethodArrObject(_kw, _content, _content.getCharSeq().getAliasStringBuilder(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodArrObjectBooleans(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert4AssertAssertArr0();
        String found_ = custAliasParameters.getAliasAssert4AssertAssertArr1();
        return assertMethodArrObject(_kw, _content, primTypes_.getAliasPrimBoolean(),exp_,found_, aliasAssertAssert);
    }
    public String assertMethodArrObject(KeyWords _kw, LgNamesContent _content, String _type, String _a, String _b, String _name) {
        AliasCore co_ = _content.getCoreNames();
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+ _name +"("+_type+"[] "+_a+","+_type+"[] "+_b+"){"+LR;
        return head_+checkArr(_kw,_content,_a,_b)+SPACES_4+"}"+LR;
    }
    private String checkArr(KeyWords _kw, LgNamesContent _content, String _exp, String _found) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String len_ = core_.getAliasArrayLength();
        String la_ = tr("la", _kw, pr_, core_, _exp, _found);
        String ea_ = tr("ea", _kw, pr_, core_, _exp, _found);
        String d_ = tr("d", _kw, pr_, core_, _exp, _found);
        return SPACES_4+SPACES_4+aliasAssertAssert+"("+_exp+"."+ len_+","+_found+"."+ len_+");"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordVar()+" "+ la_ +" = "+_exp+"."+ len_ +";"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordIter()+"("+primTypes_.getAliasPrimInteger()+" "+ ea_ +"="+"0"+";"+ la_ +";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ natEqStruct(_kw,_content,_exp+"["+ ea_+"]",_found+"["+ ea_+"]") +"){"+LR
                + lancerElt(SPACES_4+SPACES_4+SPACES_4+SPACES_4,_kw,_content,_exp,_found,ea_,d_)
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR;
    }
    private String checkArrDelta(KeyWords _kw, LgNamesContent _content, String _exp, String _found, String _delta) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String len_ = core_.getAliasArrayLength();
        String la_ = tr("la", _kw, pr_, core_, _exp, _found);
        String ea_ = tr("ea", _kw, pr_, core_, _exp, _found);
        String d_ = tr("d", _kw, pr_, core_, _exp, _found);
        return SPACES_4+SPACES_4+aliasAssertAssert+"("+_exp+"."+ len_+","+_found+"."+ len_+");"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordVar()+" "+ la_ +" = "+_exp+"."+ len_ +";"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordIter()+"("+primTypes_.getAliasPrimInteger()+" "+ ea_ +"="+"0"+";"+ la_ +";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_+"("+cmpStruct(_kw,_content,_exp+"["+ ea_+"]",_found+"["+ ea_+"]", "!=")+"&&"+deltaStruct(_kw,_content,_exp+"["+ ea_+"]",_found+"["+ ea_+"]",_delta, ">")+"){"+LR
                + lancerElt(SPACES_4+SPACES_4+SPACES_4+SPACES_4,_kw,_content,_exp,_found,ea_,d_)
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR;
    }
    public String assertMethodNotObjectLongs(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert0AssertAssertNot0();
        String found_ = custAliasParameters.getAliasAssert0AssertAssertNot1();
        return assertMethodNotObject(_kw, _content, primTypes_.getAliasPrimLong(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotObjectDoubles(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert1AssertAssertNot0();
        String found_ = custAliasParameters.getAliasAssert1AssertAssertNot1();
        return assertMethodNotObject(_kw, _content, primTypes_.getAliasPrimDouble(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotObjectDoublesDelta(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert5AssertAssertNotDouble0();
        String found_ = custAliasParameters.getAliasAssert5AssertAssertNotDouble1();
        String delta_ = custAliasParameters.getAliasAssert5AssertAssertNotDouble2();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String d_ = tr("d", _kw, pr_, co_, exp_, found_, delta_);
        String th_ = lancerVar(SPACES_4 + SPACES_4 + SPACES_4, _kw, _content, exp_, found_, d_);
        String dow_ = primTypes_.getAliasPrimDouble();
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+aliasAssertAssertNot+"("+dow_+" "+exp_+","+dow_+" "+found_+","+dow_+" "+delta_+"){"+LR;
        String test_= SPACES_4+SPACES_4+_kw.getKeyWordIf()+"("+cmpStruct(_kw,_content,exp_,found_, "==")+"||"+deltaStruct(_kw,_content,exp_,found_,delta_, "<=")+"){"+LR;
        return head_+test_+th_+SPACES_4+SPACES_4+"}"+LR+SPACES_4+"}"+LR;
    }
    public String assertMethodNotObjectStrings(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert2AssertAssertNot0();
        String found_ = custAliasParameters.getAliasAssert2AssertAssertNot1();
        return assertMethodNotObject(_kw, _content, _content.getCharSeq().getAliasString(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotObjectStringBuilders(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert3AssertAssertNot0();
        String found_ = custAliasParameters.getAliasAssert3AssertAssertNot1();
        return assertMethodNotObject(_kw, _content, _content.getCharSeq().getAliasStringBuilder(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotObjectBooleans(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert4AssertAssertNot0();
        String found_ = custAliasParameters.getAliasAssert4AssertAssertNot1();
        return assertMethodNotObject(_kw, _content, primTypes_.getAliasPrimBoolean(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotSame(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert0AssertAssertNotSame0();
        String found_ = custAliasParameters.getAliasAssert0AssertAssertNotSame1();
        return assertMethodNotObject(_kw, _content, _content.getCoreNames().getAliasObject(),exp_,found_, aliasAssertAssertNotSame);
    }
    public String assertMethodNotObject(KeyWords _kw, LgNamesContent _content, String _type, String _a, String _b, String _name) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String d_ = tr("d", _kw, pr_, co_, _a, _b);
        String th_ = lancerVar(SPACES_4 + SPACES_4 + SPACES_4, _kw, _content, _a, _b, d_);
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+ _name +"("+_type+" "+_a+","+_type+" "+_b+"){"+LR;
        String test_= SPACES_4+SPACES_4+_kw.getKeyWordIf()+"("+natEqStruct(_kw,_content,_a,_b)+"){"+LR;
        return head_+test_+th_+SPACES_4+SPACES_4+"}"+LR+SPACES_4+"}"+LR;
    }
    public String assertMethodNotArrObjectLongs(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert0AssertAssertNotArr0();
        String found_ = custAliasParameters.getAliasAssert0AssertAssertNotArr1();
        return assertMethodNotArrObject(_kw, _content, primTypes_.getAliasPrimLong(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotArrObjectDoubles(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert1AssertAssertNotArr0();
        String found_ = custAliasParameters.getAliasAssert1AssertAssertNotArr1();
        return assertMethodNotArrObject(_kw, _content, primTypes_.getAliasPrimDouble(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotArrObjectDoublesDelta(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert5AssertAssertNotArrDouble0();
        String found_ = custAliasParameters.getAliasAssert5AssertAssertNotArrDouble1();
        String delta_ = custAliasParameters.getAliasAssert5AssertAssertNotArrDouble2();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String d_ = tr("d", _kw, pr_, co_, exp_, found_, delta_);
        String th_ = lancerVar(SPACES_4 + SPACES_4, _kw, _content, exp_, found_, d_);
        String dow_ = primTypes_.getAliasPrimDouble();
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+aliasAssertAssertNot+"("+dow_+"[] "+exp_+","+dow_+"[] "+found_+","+dow_+" "+delta_+"){"+LR;
        return head_+checkNotArrDelta(_kw, _content, exp_,found_,delta_)+th_+SPACES_4+"}"+LR;
    }
    public String assertMethodNotArrObjectStrings(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert2AssertAssertNotArr0();
        String found_ = custAliasParameters.getAliasAssert2AssertAssertNotArr1();
        return assertMethodNotArrObject(_kw, _content, _content.getCharSeq().getAliasString(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotArrObjectStringBuilders(KeyWords _kw, LgNamesContent _content) {
        String exp_ = custAliasParameters.getAliasAssert3AssertAssertNotArr0();
        String found_ = custAliasParameters.getAliasAssert3AssertAssertNotArr1();
        return assertMethodNotArrObject(_kw, _content, _content.getCharSeq().getAliasStringBuilder(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotArrObjectBooleans(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        String exp_ = custAliasParameters.getAliasAssert4AssertAssertNotArr0();
        String found_ = custAliasParameters.getAliasAssert4AssertAssertNotArr1();
        return assertMethodNotArrObject(_kw, _content, primTypes_.getAliasPrimBoolean(),exp_,found_, aliasAssertAssertNot);
    }
    public String assertMethodNotArrObject(KeyWords _kw, LgNamesContent _content, String _type, String _a, String _b, String _name) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String d_ = tr("d", _kw, pr_, co_, _a, _b);
        String th_ = lancerVar(SPACES_4 + SPACES_4, _kw, _content, _a, _b, d_);
        String head_= SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+co_.getAliasVoid()+" "+ _name +"("+_type+"[] "+_a+","+_type+"[] "+_b+"){"+LR;
        return head_+checkNotArr(_kw, _content, _a, _b)+th_+SPACES_4+"}"+LR;
    }
    public String natEqStruct(KeyWords _kw, LgNamesContent _content, String _a, String _b) {
        AliasCore core_ = _content.getCoreNames();
        return _kw.getKeyWordStatic()+"("+ core_.getAliasObjectsUtil()+")."+ core_.getAliasSameRef()+"("+_a+","+_b+")";
    }
    public String cmpStruct(KeyWords _kw, LgNamesContent _content, String _a, String _b, String _symbol) {
        AliasNumberType nb_ = _content.getNbAlias();
        return _kw.getKeyWordStatic()+"("+ nb_.getAliasDouble()+")."+ nb_.getAliasCompare()+"("+_a+","+_b+")"+ _symbol +"0";
    }
    public String deltaStruct(KeyWords _kw, LgNamesContent _content, String _a, String _b, String _c, String _symbol) {
        AliasMathType nb_ = _content.getMathRef();
        return _kw.getKeyWordStatic()+"("+ nb_.getAliasMath()+")."+ nb_.getAliasAbs()+"("+_a+"-"+_b+")"+ _symbol +_c;
    }
    public String checkArrsLda(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String len_ = core_.getAliasArrayLength();
        String exp_ = custAliasParameters.getAliasAssert5AssertAssertArr0();
        String found_ = custAliasParameters.getAliasAssert5AssertAssertArr1();
        String f_ = custAliasParameters.getAliasAssert5AssertAssertArr2();
        String la_ = tr("la", _kw, pr_, core_, exp_, found_);
        String ea_ = tr("ea", _kw, pr_, core_, exp_, found_);
        String d_ = tr("d", _kw, pr_, core_, exp_, found_);
        String type_ = core_.getAliasObject()+"[]";
        String fct_ = _content.getReflect().getAliasFct()+ ANY_PAIR +primTypes_.getAliasPrimBoolean()+">";
        return SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+core_.getAliasVoid()+" "+aliasAssertAssert+"("+type_+" "+exp_+","+type_+" "+found_+","+ fct_+" "+f_+"){"+LR
                +SPACES_4+SPACES_4+aliasAssertAssert+"("+exp_+"."+ len_+","+found_+"."+ len_+");"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordVar()+" "+ la_ +" = "+exp_+"."+ len_ +";"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordIter()+"("+primTypes_.getAliasPrimInteger()+" "+ ea_ +"="+"0"+";"+ la_ +";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ lambdaStruct(_content,f_, exp_+"["+ ea_+"]",found_+"["+ ea_+"]") +"){"+LR
                + lancerElt(SPACES_4+SPACES_4+SPACES_4+SPACES_4,_kw,_content,exp_,found_,ea_,d_)
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+"}"+LR;
    }
    public String checkArrLda(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String exp_ = custAliasParameters.getAliasAssert6AssertAssertArr0();
        String f_ = custAliasParameters.getAliasAssert6AssertAssertArr1();
        String ea_ = tr("ea", _kw, pr_, core_, exp_);
        String d_ = tr("d", _kw, pr_, core_, exp_);
        String type_ = core_.getAliasObject()+"[]";
        String fct_ = _content.getReflect().getAliasFct()+"<?,"+primTypes_.getAliasPrimBoolean()+">";
        return SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+core_.getAliasVoid()+" "+aliasAssertAssert+"("+type_+" "+exp_+","+ fct_+" "+f_+"){"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordFor()+"("+_kw.getKeyWordVar()+" "+ ea_ +":"+ exp_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ lambdaStruct(_content,f_, exp_+"[(["+ ea_+"])]") +"){"+LR
                + lancerElt(SPACES_4+SPACES_4+SPACES_4+SPACES_4,_kw,_content,exp_,"(["+ea_+"])",d_)
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+"}"+LR;
    }
    public String checkPairLda(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String exp_ = custAliasParameters.getAliasAssert5AssertAssert0();
        String found_ = custAliasParameters.getAliasAssert5AssertAssert1();
        String f_ = custAliasParameters.getAliasAssert5AssertAssert2();
        String d_ = tr("d", _kw, pr_, core_, exp_, found_);
        String type_ = core_.getAliasObject();
        String fct_ = _content.getReflect().getAliasFct()+ ANY_PAIR +primTypes_.getAliasPrimBoolean()+">";
        return SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+core_.getAliasVoid()+" "+aliasAssertAssert+"("+type_+" "+exp_+","+type_+" "+found_+","+ fct_+" "+f_+"){"+LR
                +SPACES_4+SPACES_4+ si_ +" (!"+ lambdaStruct(_content,f_, exp_,found_) +"){"+LR
                + lancerVar(SPACES_4+SPACES_4+SPACES_4,_kw,_content,exp_,found_,d_)
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+"}"+LR;
    }
    public String checkSingleLda(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String exp_ = custAliasParameters.getAliasAssert6AssertAssert0();
        String f_ = custAliasParameters.getAliasAssert6AssertAssert1();
        String d_ = tr("d", _kw, pr_, core_, exp_);
        String type_ = core_.getAliasObject();
        String fct_ = _content.getReflect().getAliasFct()+"<?,"+primTypes_.getAliasPrimBoolean()+">";
        return SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+core_.getAliasVoid()+" "+aliasAssertAssert+"("+type_+" "+exp_+","+ fct_+" "+f_+"){"+LR
                +SPACES_4+SPACES_4+ si_ +" (!"+ lambdaStruct(_content,f_, exp_) +"){"+LR
                + lancerVarNotTrue(SPACES_4+SPACES_4+SPACES_4,_kw,_content,exp_,d_)
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+"}"+LR;
    }
    public String checkArrsNotLda(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String len_ = core_.getAliasArrayLength();
        String exp_ = custAliasParameters.getAliasAssert5AssertAssertNotArr0();
        String found_ = custAliasParameters.getAliasAssert5AssertAssertNotArr1();
        String f_ = custAliasParameters.getAliasAssert5AssertAssertNotArr2();
        String la_ = tr("la", _kw, pr_, core_, exp_, found_);
        String ea_ = tr("ea", _kw, pr_, core_, exp_, found_);
        String d_ = tr("d", _kw, pr_, core_, exp_, found_);
        String type_ = core_.getAliasObject()+"[]";
        String fct_ = _content.getReflect().getAliasFct()+ ANY_PAIR +primTypes_.getAliasPrimBoolean()+">";
        String ret_ = _kw.getKeyWordReturn();
        return SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+core_.getAliasVoid()+" "+aliasAssertAssertNot+"("+type_+" "+exp_+","+type_+" "+found_+","+ fct_+" "+f_+"){"+LR
                +SPACES_4+SPACES_4+ si_ +" (!"+ natEqStruct(_kw,_content, exp_+"."+ len_,found_+"."+ len_) +"){"+LR
                +SPACES_4+SPACES_4+ SPACES_4+ ret_ +";"+LR
                +SPACES_4+SPACES_4+ "}"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordVar()+" "+ la_ +" = "+exp_+"."+ len_ +";"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordIter()+"("+primTypes_.getAliasPrimInteger()+" "+ ea_ +"="+"0"+";"+ la_ +";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ lambdaStruct(_content,f_, exp_+"["+ ea_+"]",found_+"["+ ea_+"]") +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ret_+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +lancerVarNotExpect(SPACES_4+SPACES_4,_kw,_content,exp_,d_)
                +SPACES_4+"}"+LR;
    }
    public String checkArrNotLda(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String exp_ = custAliasParameters.getAliasAssert6AssertAssertNotArr0();
        String f_ = custAliasParameters.getAliasAssert6AssertAssertNotArr1();
        String ea_ = tr("ea", _kw, pr_, core_, exp_);
        String d_ = tr("d", _kw, pr_, core_, exp_);
        String type_ = core_.getAliasObject()+"[]";
        String fct_ = _content.getReflect().getAliasFct()+"<?,"+primTypes_.getAliasPrimBoolean()+">";
        String ret_ = _kw.getKeyWordReturn();
        return SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+core_.getAliasVoid()+" "+aliasAssertAssertNot+"("+type_+" "+exp_+","+ fct_+" "+f_+"){"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordFor()+"("+_kw.getKeyWordVar()+" "+ ea_ +":"+ exp_ +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ lambdaStruct(_content,f_, exp_+"[(["+ ea_+"])]") +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ret_+";"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +lancerVarNotExpect(SPACES_4+SPACES_4,_kw,_content,exp_,d_)
                +SPACES_4+"}"+LR;
    }
    public String checkPairNotLda(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String exp_ = custAliasParameters.getAliasAssert5AssertAssertNot0();
        String found_ = custAliasParameters.getAliasAssert5AssertAssertNot1();
        String f_ = custAliasParameters.getAliasAssert5AssertAssertNot2();
        String d_ = tr("d", _kw, pr_, core_, exp_, found_);
        String type_ = core_.getAliasObject();
        String fct_ = _content.getReflect().getAliasFct()+ ANY_PAIR +primTypes_.getAliasPrimBoolean()+">";
        return SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+core_.getAliasVoid()+" "+aliasAssertAssertNot+"("+type_+" "+exp_+","+type_+" "+found_+","+ fct_+" "+f_+"){"+LR
                +SPACES_4+SPACES_4+ si_ +" ("+ lambdaStruct(_content,f_, exp_,found_) +"){"+LR
                + lancerVar(SPACES_4+SPACES_4+SPACES_4,_kw,_content,exp_,found_,d_)
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+"}"+LR;
    }
    public String checkSingleNotLda(KeyWords _kw, LgNamesContent _content) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String exp_ = custAliasParameters.getAliasAssert6AssertAssertNot0();
        String f_ = custAliasParameters.getAliasAssert6AssertAssertNot1();
        String d_ = tr("d", _kw, pr_, core_, exp_);
        String type_ = core_.getAliasObject();
        String fct_ = _content.getReflect().getAliasFct()+"<?,"+primTypes_.getAliasPrimBoolean()+">";
        return SPACES_4+_kw.getKeyWordPublic()+" "+_kw.getKeyWordStatic()+" "+core_.getAliasVoid()+" "+aliasAssertAssertNot+"("+type_+" "+exp_+","+ fct_+" "+f_+"){"+LR
                +SPACES_4+SPACES_4+ si_ +" ("+ lambdaStruct(_content,f_, exp_) +"){"+LR
                + lancerVarNotTrue(SPACES_4+SPACES_4+SPACES_4,_kw,_content,exp_,d_)
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+"}"+LR;
    }
    public String lambdaStruct(LgNamesContent _content, String _f, String _a) {
        return _f+"."+_content.getReflect().getAliasCall()+"("+ _a+")";
    }

    public String lambdaStruct(LgNamesContent _content, String _f, String _a, String _b) {
        return _f+"."+_content.getReflect().getAliasCall()+"("+ _a+","+_b+")";
    }
    private String lancerVar(String _pref, KeyWords _kw, LgNamesContent _content, String _expected, String _found, String _d) {
        return _pref + _kw.getKeyWordVar() + " " + _d + " = " + _kw.getKeyWordNew() + " " + aliasDifference + "();" + LR
                + _pref + _d + "." + aliasDifferenceExpected + "=" + _expected + ";" + LR
                + _pref + _d + "." + aliasDifferenceFound + "=" + _found + ";" + LR
                + _pref + _d + "." + aliasDifferenceStackDiff + " = " + _kw.getKeyWordStatic() + "(" + _content.getStackElt().getAliasStackTraceElement() + ")." + _content.getStackElt().getAliasCurrentStack() + "();" + LR
                + _pref + _kw.getKeyWordThrow() + " " + _d + ";" + LR;
    }

    private String lancerVarNotExpect(String _pref, KeyWords _kw, LgNamesContent _content, String _found, String _d) {
        return _pref + _kw.getKeyWordVar() + " " + _d + " = " + _kw.getKeyWordNew() + " " + aliasDifference + "();" + LR
                + _pref + _d + "." + aliasDifferenceFound + "=" + _found + ";" + LR
                + _pref + _d + "." + aliasDifferenceStackDiff + " = " + _kw.getKeyWordStatic() + "(" + _content.getStackElt().getAliasStackTraceElement() + ")." + _content.getStackElt().getAliasCurrentStack() + "();" + LR
                + _pref + _kw.getKeyWordThrow() + " " + _d + ";" + LR;
    }
    private String lancerVarNotTrue(String _pref, KeyWords _kw, LgNamesContent _content, String _found, String _d) {
        return _pref + _kw.getKeyWordVar() + " " + _d + " = " + _kw.getKeyWordNew() + " " + aliasDifference + "();" + LR
                + _pref + _d + "." + aliasDifferenceFoundNotTrue + "=" + _kw.getKeyWordTrue() + ";" + LR
                + _pref + _d + "." + aliasDifferenceFound + "=" + _found + ";" + LR
                + _pref + _d + "." + aliasDifferenceStackDiff + " = " + _kw.getKeyWordStatic() + "(" + _content.getStackElt().getAliasStackTraceElement() + ")." + _content.getStackElt().getAliasCurrentStack() + "();" + LR
                + _pref + _kw.getKeyWordThrow() + " " + _d + ";" + LR;
    }
    private String lancerNonNull(String _pref, KeyWords _kw, LgNamesContent _content, String _d) {
        return _pref + _kw.getKeyWordVar() + " " + _d + " = " + _kw.getKeyWordNew() + " " + aliasDifference + "();" + LR
                + _pref + _d + "." + aliasDifferenceFoundNull + "=" + _kw.getKeyWordTrue() + ";" + LR
                + _pref + _d + "." + aliasDifferenceStackDiff + " = " + _kw.getKeyWordStatic() + "(" + _content.getStackElt().getAliasStackTraceElement() + ")." + _content.getStackElt().getAliasCurrentStack() + "();" + LR
                + _pref + _kw.getKeyWordThrow() + " " + _d + ";" + LR;
    }

    private String lancerElt(String _pref, KeyWords _kw, LgNamesContent _content, String _expected, String _found, String _index, String _d) {
        return _pref + _kw.getKeyWordVar() +" "+_d+" = "+_kw.getKeyWordNew()+" "+aliasEltDifference+"();"+LR
                +_pref + _d + "." + aliasIndex+"="+_index+";"+LR
                +_pref + _d + "." + aliasDifferenceExpected+"="+_expected+"["+_index+"];"+LR
                +_pref + _d + "." + aliasDifferenceFound+"="+_found+"["+_index+"];"+LR
                + _pref + _d + "." + aliasDifferenceStackDiff + " = " + _kw.getKeyWordStatic() + "(" + _content.getStackElt().getAliasStackTraceElement() + ")." + _content.getStackElt().getAliasCurrentStack() + "();" + LR
                + _pref + _kw.getKeyWordThrow() + " " + _d + ";" + LR;
    }

    private String lancerElt(String _pref, KeyWords _kw, LgNamesContent _content, String _expected, String _index, String _d) {
        return _pref + _kw.getKeyWordVar() +" "+_d+" = "+_kw.getKeyWordNew()+" "+aliasEltDifference+"();"+LR
                +_pref + _d + "." + aliasIndex+"="+_index+";"+LR
                +_pref + _d + "." + aliasDifferenceExpected+"="+_expected+"["+_index+"];"+LR
                + _pref + _d + "." + aliasDifferenceStackDiff + " = " + _kw.getKeyWordStatic() + "(" + _content.getStackElt().getAliasStackTraceElement() + ")." + _content.getStackElt().getAliasCurrentStack() + "();" + LR
                + _pref + _kw.getKeyWordThrow() + " " + _d + ";" + LR;
    }
    private String checkNotArr(KeyWords _kw, LgNamesContent _content, String _exp, String _found) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String len_ = core_.getAliasArrayLength();
        String ret_ = _kw.getKeyWordReturn();
        String la_ = tr("la", _kw, pr_, core_, _exp, _found);
        String ea_ = tr("ea", _kw, pr_, core_, _exp, _found);
        return SPACES_4+SPACES_4+ si_ +" (!"+ natEqStruct(_kw,_content,_exp+"."+ len_,_found+"."+ len_) +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ ret_ +";"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordVar()+" "+ la_ +" = "+_exp+"."+ len_ +";"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordIter()+"("+primTypes_.getAliasPrimInteger()+" "+ ea_ +"="+"0"+";"+ la_ +";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_ +" (!"+ natEqStruct(_kw,_content,_exp+"["+ ea_+"]",_found+"["+ ea_+"]") +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ ret_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR;
    }

    private String checkNotArrDelta(KeyWords _kw, LgNamesContent _content, String _exp, String _found, String _delta) {
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore core_ = _content.getCoreNames();
        String si_ = _kw.getKeyWordIf();
        String len_ = core_.getAliasArrayLength();
        String ret_ = _kw.getKeyWordReturn();
        String la_ = tr("la", _kw, pr_, core_, _exp, _found);
        String ea_ = tr("ea", _kw, pr_, core_, _exp, _found);
        return SPACES_4+SPACES_4+ si_ +" (!"+ natEqStruct(_kw,_content,_exp+"."+ len_,_found+"."+ len_) +"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ ret_ +";"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordVar()+" "+ la_ +" = "+_exp+"."+ len_ +";"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordIter()+"("+primTypes_.getAliasPrimInteger()+" "+ ea_ +"="+"0"+";"+ la_ +";"+"1"+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ si_+"("+cmpStruct(_kw,_content,_exp+"["+ ea_+"]",_found+"["+ ea_+"]", "!=")+"&&"+deltaStruct(_kw,_content,_exp+"["+ ea_+"]",_found+"["+ ea_+"]",_delta, ">")+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+SPACES_4+ ret_ +";"+LR
                +SPACES_4+SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+"}"+LR;
    }
    public String formatter(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        String abs_ = _kw.getKeyWordAbstract();
        String fin_ = _kw.getKeyWordFinal();
        String cl_ = _kw.getKeyWordClass();
        String obj_ = _content.getCoreNames().getAliasObject();
        String str_ = _content.getCharSeq().getAliasString();
        String strUt_ = _content.getCoreNames().getAliasStringUtil();
        String strUtVal_ = _content.getCoreNames().getAliasStringUtilValueOf();
        String st_ = _kw.getKeyWordStatic();
        String vd_ = _content.getCoreNames().getAliasVoid();
        String prOne_ = custAliasParameters.getAliasFormatType0Print0();
        String prTwoStr_ = custAliasParameters.getAliasFormatType1Print0();
        String prTwoArr_ = custAliasParameters.getAliasFormatType1Print1();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String as_ = tr( "as", _kw, pr_, co_,
                prTwoStr_,
                prTwoArr_);
        String e_ = tr( "e", _kw, pr_, co_,
                prTwoStr_,
                prTwoArr_);
        return pub_ +" "+ abs_ +" "+ fin_ +" "+ cl_ +" "+aliasFormatType+" {"+LR
                +SPACES_4+ pub_ +" "+ st_ +" "+ vd_ +" "+aliasPrint+"("+obj_+" "+prOne_+"){"+LR
                +SPACES_4+SPACES_4+ st_ +"("+aliasThread+")."+aliasPrint+"("+ st_ +"("+strUt_+")."+strUtVal_+"("+prOne_+"));"+LR
                +SPACES_4+"}"+LR
                +SPACES_4+ pub_ +" "+ st_ +" "+ vd_ +" "+aliasPrint+"("+str_+" "+prTwoStr_+","+obj_+"... "+prTwoArr_+"){"+LR
                +SPACES_4+SPACES_4+str_+"[] "+ as_ +" = "+_kw.getKeyWordNew()+" "+str_+"["+prTwoArr_+"."+_content.getCoreNames().getAliasArrayLength()+"];"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordFor()+"("+_kw.getKeyWordVar()+" "+ e_ +":"+prTwoArr_+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ as_ +"[(["+ e_ +"])] = "+ st_ +"("+strUt_+")."+strUtVal_+"("+ e_ +");"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ st_ +"("+aliasThread+")."+aliasPrint+"("+prTwoStr_+"."+_content.getCharSeq().getAliasFormat()+"("+ as_ +"));"+LR
                +SPACES_4+"}"+LR
                +"}"+LR;
    }
    public String diff(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String out_ = tr("out", _kw, pr_, co_);
        String s_ = tr("s", _kw, pr_, co_);
        String obj_ = co_.getAliasObject();
        String strUt_ = co_.getAliasStringUtil();
        String strUtVal_ = co_.getAliasStringUtilValueOf();
        String bool_ = primTypes_.getAliasPrimBoolean();
        String strBuildConv_ = _content.getCharSeq().getAliasCharSequenceToString();
        String th_ = _kw.getKeyWordThis();
        String v_ = _kw.getKeyWordVar();
        String st_ = _kw.getKeyWordStatic();
        String i_ = _kw.getKeyWordIf();
        String ins_ = _content.getCharSeq().getAliasInsert();
        String ret_ = _kw.getKeyWordReturn();
        String lrInterp_ = "\"\\"+_kw.getKeyWordEscLine()+"\"";
        return pub_ +" "+_kw.getKeyWordClass()+" "+aliasDifference+"{"+LR
                +SPACES_4+ pub_ +" "+ obj_ +" "+aliasDifferenceExpected+";"+LR
                +SPACES_4+ pub_ +" "+ obj_ +" "+aliasDifferenceFound+";"+LR
                +SPACES_4+ pub_ +" "+ bool_ +" "+aliasDifferenceFoundNull+";"+LR
                +SPACES_4+ pub_ +" "+ bool_ +" "+aliasDifferenceFoundNotTrue+";"+LR
                +SPACES_4+ pub_ +" "+_content.getStackElt().getAliasStackTraceElement()+"[] "+aliasDifferenceStackDiff+";"+LR
                +SPACES_4+ pub_ +" "+_content.getCharSeq().getAliasString()+" "+_kw.getKeyWordToString()+"(){"+LR
                +SPACES_4+SPACES_4+v_+" "+ out_ +"="+_kw.getKeyWordNew()+" "+_content.getCharSeq().getAliasStringBuilder()+"();"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordFor()+" ("+ v_ +" "+ s_ +": "+ th_ +"."+aliasDifferenceStackDiff+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ out_ +"."+_content.getCharSeq().getAliasAppend()+"("+ st_ +"("+ strUt_ +")."+ strUtVal_ +"("+ s_ +"));"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ i_ +" ("+ th_ +"."+aliasDifferenceFoundNotTrue+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ out_ +"."+ ins_ +"("+"0"+","+ lrInterp_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+ out_ +"."+ ins_ +"("+"0"+","+ st_ +"("+ strUt_ +")."+ strUtVal_ +"("+ th_ +"."+aliasDifferenceFound+"));"+LR
                +SPACES_4+SPACES_4+SPACES_4+ ret_ +" "+ out_ +"."+ strBuildConv_ +"();"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ i_ +" ("+ th_ +"."+aliasDifferenceFoundNull+"){"+LR
                +SPACES_4+SPACES_4+SPACES_4+ out_ +"."+ ins_ +"("+"0"+","+ lrInterp_ +");"+LR
                +SPACES_4+SPACES_4+SPACES_4+ out_ +"."+ ins_ +"("+"0"+","+ st_ +"("+ strUt_ +")."+ strUtVal_ +"("+_kw.getKeyWordNull()+"));"+LR
                +SPACES_4+SPACES_4+SPACES_4+ ret_ +" "+ out_ +"."+ strBuildConv_ +"();"+LR
                +SPACES_4+SPACES_4+"}"+LR
                +SPACES_4+SPACES_4+ out_ +"."+ ins_ +"("+"0"+","+ lrInterp_ +");"+LR
                +SPACES_4+SPACES_4+ out_ +"."+ ins_ +"("+"0"+","+ st_ +"("+ strUt_ +")."+ strUtVal_ +"("+ th_ +"."+aliasDifferenceFound+"));"+LR
                +SPACES_4+SPACES_4+ out_ +"."+ ins_ +"("+"0"+",\"!=\");"+LR
                +SPACES_4+SPACES_4+ out_ +"."+ ins_ +"("+"0"+","+ st_ +"("+ strUt_ +")."+ strUtVal_ +"("+ th_ +"."+aliasDifferenceExpected+"));"+LR
                +SPACES_4+SPACES_4+ ret_ +" "+ out_ +"."+ strBuildConv_ +"();"+LR
                +SPACES_4+"}"+LR
                +"}"+LR;
    }
    public String eltDiff(KeyWords _kw, LgNamesContent _content) {
        String pub_ = _kw.getKeyWordPublic();
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String out_ =  tr("out", _kw, pr_, co_);
        String append_ = _content.getCharSeq().getAliasAppend();
        String toStr_ = _kw.getKeyWordToString();
        return pub_ +" "+_kw.getKeyWordClass()+" "+aliasEltDifference+":"+aliasDifference+"{"+LR
                +SPACES_4+ pub_ +" "+_content.getPrimTypes().getAliasPrimInteger()+" "+ aliasIndex +";"+LR
                +SPACES_4+ pub_ +" "+_content.getCharSeq().getAliasString()+" "+ toStr_ +"(){"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordVar()+" "+ out_ +"="+_kw.getKeyWordNew()+" "+_content.getCharSeq().getAliasStringBuilder()+"();"+LR
                +SPACES_4+SPACES_4+ out_ +"."+ append_ +"("+ _kw.getKeyWordThis()+"."+aliasIndex +");"+LR
                +SPACES_4+SPACES_4+ out_ +"."+ append_ +"(\":\");"+LR
                +SPACES_4+SPACES_4+ out_ +"."+ append_ +"("+_kw.getKeyWordSuper()+"."+ toStr_ +"());"+LR
                +SPACES_4+SPACES_4+_kw.getKeyWordReturn()+" "+ out_ +"."+_content.getCharSeq().getAliasCharSequenceToString()+"();"+LR
                +SPACES_4+"}"+LR
                +"}"+LR;
    }
    public static String getStandarString(ContextEl _cont, Struct _struct) {
        if (_struct instanceof DisplayableStruct) {
            return ((DisplayableStruct)_struct).getDisplayedString(_cont).getInstance();
        }
        return StringUtil.concat(_cont.getStandards().getStringOfObject(_cont,_struct).getInstance(), OTHERS);
    }
    public void processFailInit(ContextEl _cont, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isInitEnums()) {
            _stackCall.failInitEnums();
        } else {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, aliasConcurrentError, _stackCall)));
        }
    }
    public static String getDateTimeText(AbstractThreadFactory _fact) {
        AbstractDateFactory dateFactory_ = _fact.getDateFactory();
        AbstractDate date_ = dateFactory_.newDate(_fact.millis());
        return date_.format(dateFactory_, YYYY_MM_DD_HH_MM_SS_SSS);
    }

    public CustList<CommentDelimiters> defComments() {
        return defComments(getUserLg(), getTranslations(), getLanguage());
    }

    public static CustList<CommentDelimiters> defComments(String _lg, Translations _trs, String _language) {
        TranslationsLg lg_ = lg(_trs, _lg, _language);
        TranslationsAppli app_ = FileInfos.getAppliTr(lg_);
        TranslationsFile com_ = app_.getMapping().getVal(FileInfos.COMMENTS);
        StringMap<String> mes_ = MessagesCdmBase.valMessages(app_);
        char low_ = StringDataUtil.toLowerCase(mes_.getVal(MessagesCdmBase.DIGITS_SUPP)).charAt(2);
        String un_ = mes_.getVal(MessagesCdmBase.UNICODE);
        String nl_ = mes_.getVal(MessagesCdmBase.NEW_LINE);
        String comments_ = "\\"+ un_ +"005"+low_+"*,*\\"+ un_ +"005"+low_+";\\"+ un_ +"005"+low_+"\\"+ un_ +"005"+low_+",\\"+ nl_ +";\\"+ un_ +"005"+low_+"<"
                +com_.getMapping().getVal(FileInfos.COMM_BEGIN)
                +">,</"+com_.getMapping().getVal(FileInfos.COMM_END)
                +">;\\"+ un_ +"005"+low_+">,\\"+ nl_ +"\n";
        return ParseLinesArgUtil.buildComments(mes_,comments_);
    }

    public static TranslationsLg lg(Translations _trs, String _one, String _two) {
        if (!_one.isEmpty()) {
            return _trs.getMapping().getVal(_one);
        }
        return _trs.getMapping().getVal(_two);
    }
    public String getAliasRunnable() {
        return aliasRunnable;
    }

    public void setAliasRunnable(String _aliasRunnable) {
        aliasRunnable = _aliasRunnable;
    }

    public String getAliasCallable() {
        return aliasCallable;
    }

    public void setAliasCallable(String _v) {
        this.aliasCallable = _v;
    }

    public String getAliasThreadSet() {
        return aliasThreadSet;
    }

    public void setAliasThreadSet(String _aliasThreadSet) {
        aliasThreadSet = _aliasThreadSet;
    }

    public String getAliasExecutorService() {
        return aliasExecutorService;
    }

    public void setAliasExecutorService(String _v) {
        this.aliasExecutorService = _v;
    }

    public String getAliasExecutorServiceBase() {
        return aliasExecutorServiceBase;
    }

    public void setAliasExecutorServiceBase(String _v) {
        this.aliasExecutorServiceBase = _v;
    }

    public String getAliasScheduledExecutorService() {
        return aliasScheduledExecutorService;
    }

    public void setAliasScheduledExecutorService(String _v) {
        this.aliasScheduledExecutorService = _v;
    }

    public String getAliasExecutorServiceScheduleMillis() {
        return aliasExecutorServiceScheduleMillis;
    }

    public void setAliasExecutorServiceScheduleMillis(String _v) {
        this.aliasExecutorServiceScheduleMillis = _v;
    }

    public String getAliasExecutorServiceScheduleNanos() {
        return aliasExecutorServiceScheduleNanos;
    }

    public void setAliasExecutorServiceScheduleNanos(String _v) {
        this.aliasExecutorServiceScheduleNanos = _v;
    }

    public String getAliasExecutorServiceShutdown() {
        return aliasExecutorServiceShutdown;
    }

    public void setAliasExecutorServiceShutdown(String _v) {
        this.aliasExecutorServiceShutdown = _v;
    }

    public String getAliasExecutorServiceStopped() {
        return aliasExecutorServiceStopped;
    }

    public void setAliasExecutorServiceStopped(String _v) {
        this.aliasExecutorServiceStopped = _v;
    }

    public String getAliasExecutorServiceExecute() {
        return aliasExecutorServiceExecute;
    }

    public void setAliasExecutorServiceExecute(String _v) {
        this.aliasExecutorServiceExecute = _v;
    }

    public String getAliasExecutorServiceSubmit() {
        return aliasExecutorServiceSubmit;
    }

    public void setAliasExecutorServiceSubmit(String _v) {
        this.aliasExecutorServiceSubmit = _v;
    }

    public String getAliasRunnableImplicit0Runner() {
        return aliasRunnableImplicit0Runner;
    }

    public void setAliasRunnableImplicit0Runner(String _v) {
        this.aliasRunnableImplicit0Runner = _v;
    }

    public String getAliasCallableImplicit0Runner() {
        return aliasCallableImplicit0Runner;
    }

    public void setAliasCallableImplicit0Runner(String _v) {
        this.aliasCallableImplicit0Runner = _v;
    }

    public String getAliasFuture() {
        return aliasFuture;
    }

    public void setAliasFuture(String _v) {
        this.aliasFuture = _v;
    }

    public String getAliasFutureWait() {
        return aliasFutureWait;
    }

    public void setAliasFutureWait(String _v) {
        this.aliasFutureWait = _v;
    }

    public String getAliasFutureCancel() {
        return aliasFutureCancel;
    }

    public void setAliasFutureCancel(String _v) {
        this.aliasFutureCancel = _v;
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

//    public String getAliasThreadExitHook() {
//        return aliasThreadExitHook;
//    }
//
//    public void setAliasThreadExitHook(String _aliasThreadExitHook) {
//        this.aliasThreadExitHook = _aliasThreadExitHook;
//    }

    public String getAliasThreadCurrentTime() {
        return aliasThreadCurrentTime;
    }

    public void setAliasThreadCurrentTime(String _aliasThreadCurrentTime) {
        aliasThreadCurrentTime = _aliasThreadCurrentTime;
    }

    public String getAliasThreadRunnable() {
        return aliasThreadRunnable;
    }

    public void setAliasThreadRunnable(String _v) {
        this.aliasThreadRunnable = _v;
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

    public String getAliasCallableMethod() {
        return aliasCallableMethod;
    }

    public void setAliasCallableMethod(String _v) {
        this.aliasCallableMethod = _v;
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

    public String getAliasInterrupt() {
        return aliasInterrupt;
    }
    public void setAliasInterrupt(String _aliasJoin) {
        aliasInterrupt = _aliasJoin;
    }

    public String getAliasArgs() {
        return aliasArgs;
    }

    public void setAliasArgs(String _v) {
        this.aliasArgs = _v;
    }

    public String getAliasCoverage() {
        return aliasCoverage;
    }

    public void setAliasCoverage(String _v) {
        this.aliasCoverage = _v;
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
//    public String getAliasYield() {
//        return aliasYield;
//    }
//    public void setAliasYield(String _aliasYield) {
//        aliasYield = _aliasYield;
//    }
//    public String getAliasReentrantLock() {
//        return aliasReentrantLock;
//    }
//    public void setAliasReentrantLock(String _aliasReentrantLock) {
//        aliasReentrantLock = _aliasReentrantLock;
//    }
//    public String getAliasLock() {
//        return aliasLock;
//    }
//    public void setAliasLock(String _aliasLock) {
//        aliasLock = _aliasLock;
//    }
//    public String getAliasUnlock() {
//        return aliasUnlock;
//    }
//    public void setAliasUnlock(String _aliasUnlock) {
//        aliasUnlock = _aliasUnlock;
//    }
//    public String getAliasIsHeldByCurrentThread() {
//        return aliasIsHeldByCurrentThread;
//    }
//    public void setAliasIsHeldByCurrentThread(String _aliasIsHeldByCurrentThread) {
//        aliasIsHeldByCurrentThread = _aliasIsHeldByCurrentThread;
//    }
    public String getAliasAtomicBoolean() {
        return aliasAtomicBoolean;
    }
    public void setAliasAtomicBoolean(String _aliasAtomicBoolean) {
        aliasAtomicBoolean = _aliasAtomicBoolean;
    }

    public String getAliasAtomicRef() {
        return aliasAtomicRef;
    }

    public void setAliasAtomicRef(String _aliasAtomicRef) {
        this.aliasAtomicRef = _aliasAtomicRef;
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

    public MathAdvAliases getMathAdvAliases() {
        return mathAdvAliases;
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

    public String getAliasTableEntryOwner() {
        return aliasTableEntryOwner;
    }

    public void setAliasTableEntryOwner(String _aliasTableEntryOwner) {
        this.aliasTableEntryOwner = _aliasTableEntryOwner;
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

    public String getAliasCallableVar() {
        return aliasCallableVar;
    }

    public void setAliasCallableVar(String _v) {
        this.aliasCallableVar = _v;
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

    public String getAliasArgsTest() {
        return aliasArgsTest;
    }

    public void setAliasArgsTest(String _v) {
        this.aliasArgsTest = _v;
    }

    public String getAliasExecutedTestArgsAnnot() {
        return aliasExecutedTestArgsAnnot;
    }

    public void setAliasExecutedTestArgsAnnot(String _v) {
        this.aliasExecutedTestArgsAnnot = _v;
    }

    public String getAliasArgsTestArgsValue() {
        return aliasArgsTestArgsValue;
    }

    public void setAliasArgsTestArgsValue(String _v) {
        this.aliasArgsTestArgsValue = _v;
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

    public String getAliasExecuteGroupClass() {
        return aliasExecuteGroupClass;
    }

    public void setAliasExecuteGroupClass(String _v) {
        this.aliasExecuteGroupClass = _v;
    }

    public String getAliasExecuteGroupClassMethod() {
        return aliasExecuteGroupClassMethod;
    }

    public void setAliasExecuteGroupClassMethod(String _v) {
        this.aliasExecuteGroupClassMethod = _v;
    }

    public String getAliasExecuteFlat() {
        return aliasExecuteFlat;
    }

    public void setAliasExecuteFlat(String _v) {
        this.aliasExecuteFlat = _v;
    }

    public String getAliasExecuteLaunch() {
        return aliasExecuteLaunch;
    }

    public void setAliasExecuteLaunch(String _v) {
        this.aliasExecuteLaunch = _v;
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

    public String getAliasInfoTestCalls() {
        return aliasInfoTestCalls;
    }

    public void setAliasInfoTestCalls(String _v) {
        this.aliasInfoTestCalls = _v;
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

    public String getAliasInfoTestNbThreads() {
        return aliasInfoTestNbThreads;
    }

    public void setAliasInfoTestNbThreads(String _v) {
        this.aliasInfoTestNbThreads = _v;
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

    public String getAliasResultArgs() {
        return aliasResultArgs;
    }

    public void setAliasResultArgs(String _v) {
        this.aliasResultArgs = _v;
    }

    public String getAliasResultContainer() {
        return aliasResultContainer;
    }

    public void setAliasResultContainer(String _v) {
        this.aliasResultContainer = _v;
    }

    public String getAliasResultExecuted() {
        return aliasResultExecuted;
    }

    public void setAliasResultExecuted(String _v) {
        this.aliasResultExecuted = _v;
    }

    public String getAliasConcurrentError() {
        return aliasConcurrentError;
    }

    public void setAliasConcurrentError(String _aliasConcurrentError) {
        aliasConcurrentError = _aliasConcurrentError;
    }

    public StringViewReplaceAliases getStringViewReplaceAliases() {
        return stringViewReplaceAliases;
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

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations _trs) {
        this.translations = _trs;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String _lg) {
        this.language = _lg;
    }

    public String getUserLg() {
        return userLg;
    }

    public void setUserLg(String _u) {
        this.userLg = _u;
    }

    static String tr(String _var, KeyWords _keyWords, StringMap<PrimitiveType> _primitiveTypes, AliasCore _coreNames, String... _args) {
        CustList<String> allKeysWords_ = _keyWords.allKeyWordsValues();
        allKeysWords_.addAllElts(_primitiveTypes.getKeys());
        allKeysWords_.add(_coreNames.getAliasVoid());
        for (String p:_args) {
            allKeysWords_.add(p);
        }
        return ValidatorStandard.tr(allKeysWords_,_var);
    }
}
