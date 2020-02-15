package code.expressionlanguage.utilcompo;

import java.io.File;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CustomFoundMethod;
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
import code.expressionlanguage.variables.VariableSuffix;
import code.sml.DocumentBuilder;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.*;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public class LgNamesUtils extends LgNames {

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
    private AbstractResourcesReader reader;

    public LgNamesUtils(AbstractResourcesReader _reader) {
        reader = _reader;
    }
    @Override
    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> stds_ = super.buildFiles(_context);
        String content_ = reader.read("resources_lg/threads/runnable.txt");
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
        String suffixCatch_ = "";
        if (_context.getOptions().getSuffixVar() == VariableSuffix.DISTINCT) {
            suffixCatch_ = StringList.concat(suffix_,"..");
        } else if (_context.getOptions().getSuffixVar() != VariableSuffix.NONE) {
            suffixCatch_ = suffix_;
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
        content_ = reader.read("resources_lg/collections/list.txt");
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

        content_ = reader.read("resources_lg/tests/run.txt");
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
        content_ = reader.read("resources_lg/threads/formatting.txt");
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
    public Struct getStringOfObject(ContextEl _cont, Struct _arg) {
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
            ThreadStruct std_ = new ThreadStruct(thread_);
            res_.setResult(std_);
            return res_;
        }
        if (StringList.quickEq(name_,aliasReentrantLock)) {
            AbstractLock re_ = LockFactory.newLock();
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
        } catch (Exception e) {
            ResultErrorStd res_ = new ResultErrorStd();
            res_.setError(aliasConcurrentError);
            processError(_cont,res_);
            return res_;
        }
    }

    protected ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        try {
            return super.invoke(_cont,_method,_struct,_args);
        } catch (Exception e) {
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
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                ins_.add(_args[0],(RunnableContextEl)_cont);
                if (!(_args[0] instanceof ThreadStruct)) {
                    res_.setError(getAliasNullPe());
                } else {
                    res_.setResult(NullStruct.NULL_VALUE);
                }
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadSetAll)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(((RunnableContextEl)_cont).getCustInit().getThreadSet());
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadSetRemove)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                ins_.remove(_args[0], (RunnableContextEl) _cont);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadSetContains)) {
                if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
                res_.setResult(ins_.contains(_args[0], (RunnableContextEl) _cont));
                return res_;
            }
            ThreadSetStruct ins_ = (ThreadSetStruct)_instance;
            res_.setResult(ins_.toSnapshotArray(_cont));
            return res_;
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
                Thread thread_ = ((ThreadStruct) _instance).getThread();
                if (ThreadUtil.start(thread_)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                } else {
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
                res_.setResult(new BooleanStruct(ThreadUtil.sleep(((NumberStruct)_args[0]).longStruct())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasJoin)) {
                Thread thread_ = ((ThreadStruct) _instance).getThread();
                boolean alive_ = thread_.isAlive();
                ThreadUtil.join(thread_);
                res_.setResult(new BooleanStruct(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasCurrentThread)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(((RunnableContextEl)_cont).getThread());
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadExitHook)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (!(_args[0] instanceof ThreadStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                ThreadStruct a_ = (ThreadStruct) _args[0];
                ((RunnableContextEl)_cont).getCustInit().initHook(a_,(RunnableContextEl)_cont);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(name_,aliasJoinOthers)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
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
                res_.setResult(new BooleanStruct(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasIsEnded)) {
                boolean alive_ = ((ThreadStruct) _instance).isEnded();
                res_.setResult(new BooleanStruct(alive_));
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
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                Thread.yield();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new LongStruct(System.currentTimeMillis()));
            return res_;
        }
        if (StringList.quickEq(className_,aliasReentrantLock)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
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
            if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
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
                res_.setResult(new BooleanStruct(re_.compareAndSet(((BooleanStruct)_args[0]).getInstance(),((BooleanStruct)_args[1]).getInstance())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasGetAndSetAtomic)) {
                AtomicBoolean re_ = (AtomicBoolean) ((StdStruct) _instance).getInstance();
                res_.setResult(new BooleanStruct(re_.getAndSet(((BooleanStruct)_args[0]).getInstance())));
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
            if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
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
                res_.setResult(new BooleanStruct(re_.compareAndSet(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct())));
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
            if (_cont.isInitEnums() && _cont.isContainedSensibleFields(_instance)) {
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
                res_.setResult(new BooleanStruct(re_.compareAndSet(((NumberStruct)_args[0]).longStruct(),((NumberStruct)_args[1]).longStruct())));
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
        	if (_cont.isInitEnums()) {
        		_cont.failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
        	}
            if (StringList.quickEq(name_,aliasFileZipBinArray)) {
                res_.setResult(ZipStructUtil.zipBinFiles(_args[0],(RunnableContextEl) _cont));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZippedBinArray)) {
                res_.setResult(ZipStructUtil.zippedBinaryFilesByteArray(_args[0], (RunnableContextEl) _cont));
                return res_;
            }
            if (!(_args[0] instanceof StringStruct)) {
        	    res_.setError(getAliasNullPe());
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
        		String txt_ = getStandarString(_cont,_args[1]);
        		res_.setResult(new BooleanStruct(StreamTextFile.saveTextFile(file_, txt_)));
        		return res_;
        	}
            if (StringList.quickEq(name_,aliasFileReadBin)) {
                StringStruct str_ = (StringStruct)_args[0];
                byte[] read_ = StreamBinaryFile.loadFile(str_.getInstance());
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
                    res_.setResult(new BooleanStruct(false));
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
                res_.setResult(new BooleanStruct(StreamBinaryFile.writeFile(file_, bin_)));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileDelete)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new BooleanStruct(new File(file_).delete()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileRename)) {
                if (!(_args[1] instanceof StringStruct)) {
                    res_.setError(getAliasNullPe());
                    return res_;
                }
                String file_ = ((StringStruct)_args[0]).getInstance();
                String dest_ = ((StringStruct)_args[1]).getInstance();
                res_.setResult(new BooleanStruct(new File(file_).renameTo(new File(dest_))));
                return res_;
            }
        	if (StringList.quickEq(name_,aliasAppendToFile)) {
        		String file_ = ((StringStruct)_args[0]).getInstance();
        		String txt_ = getStandarString(_cont,_args[1]);
        		res_.setResult(new BooleanStruct(StreamTextFile.logToFile(file_, txt_)));
        		return res_;
        	}
            if (StringList.quickEq(name_,aliasFileAbsolutePath)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new StringStruct(StringList.replaceBackSlash(new File(file_).getAbsolutePath())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileGetLength)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new LongStruct(new File(file_).length()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileGetName)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new StringStruct(new File(file_).getName()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileGetParentPath)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new StringStruct(StringList.replaceBackSlash(new File(file_).getParentFile().getAbsolutePath())));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileIsDirectory)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                File info_ = new File(file_);
                res_.setResult(new BooleanStruct(info_.exists()&&info_.isDirectory()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileIsFile)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                File info_ = new File(file_);
                res_.setResult(new BooleanStruct(info_.exists()&&!info_.isDirectory()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileLastModif)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                File info_ = new File(file_);
                res_.setResult(new LongStruct(info_.lastModified()));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileListFiles)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                File info_ = new File(file_);
                File[] files_ = info_.listFiles();
                if (files_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                StringList filesList_ = new StringList();
                for (File f: files_) {
                    if (!f.exists()) {
                        continue;
                    }
                    if (f.isDirectory()) {
                        continue;
                    }
                    filesList_.add(StringList.replaceBackSlash(f.getAbsolutePath()));
                }
                int len_ = filesList_.size();
                ArrayStruct arr_ = new ArrayStruct(new Struct[len_],PrimitiveTypeUtil.getPrettyArrayType(getAliasString()));
                for (int i = 0; i < len_; i++) {
                    arr_.getInstance()[i] = new StringStruct(filesList_.get(i));
                }
                res_.setResult(arr_);
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileListDirectories)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                File info_ = new File(file_);
                File[] files_ = info_.listFiles();
                if (files_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                StringList filesList_ = new StringList();
                for (File f: files_) {
                    if (!f.exists()) {
                        continue;
                    }
                    if (!f.isDirectory()) {
                        continue;
                    }
                    filesList_.add(StringList.replaceBackSlash(f.getAbsolutePath()));
                }
                int len_ = filesList_.size();
                ArrayStruct arr_ = new ArrayStruct(new Struct[len_],PrimitiveTypeUtil.getPrettyArrayType(getAliasString()));
                for (int i = 0; i < len_; i++) {
                    arr_.getInstance()[i] = new StringStruct(filesList_.get(i));
                }
                res_.setResult(arr_);
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZipBin)) {
                res_.setResult(ZipStructUtil.zipBinFiles(_args[0],_args[1]));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZipText)) {
                res_.setResult(ZipStructUtil.zipTextFiles(_args[0],_args[1]));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZippedBin)) {
                res_.setResult(ZipStructUtil.zippedBinaryFiles(_args[0], (RunnableContextEl) _cont));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileZippedText)) {
                res_.setResult(ZipStructUtil.zippedTextFiles(_args[0], (RunnableContextEl) _cont));
                return res_;
            }
            if (StringList.quickEq(name_,aliasFileIsAbsolute)) {
                String file_ = ((StringStruct)_args[0]).getInstance();
                res_.setResult(new BooleanStruct(StreamFolderFile.isAbsolute(file_)));
                return res_;
            }
            String file_ = ((StringStruct)_args[0]).getInstance();
            res_.setResult(new BooleanStruct(new File(file_).mkdirs()));
            return res_;
        }
        return res_;
    }

    private static void log(String _dtPart, RunnableContextEl _cont,
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
        new File(folder_).mkdirs();
        String toFile_ = StringList.concat(folder_,"/",_dtPart);
        StreamTextFile.logToFile(toFile_, stringAppFile_);
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

    @Override
    public StringMap<StringList> allTableTypeVarTypes() {
        StringMap<StringList> t_ = super.allTableTypeVarTypes();
        t_.put(getAliasList(), new StringList(
                getAliasListVar()));
        t_.put(getAliasCustIterator(), new StringList(
                getAliasCustIteratorVar()));
        t_.put(getAliasTable(), new StringList(
                getAliasTableVarFirst(),
                getAliasTableVarSecond()));
        t_.put(getAliasCustIterTable(), new StringList(
                getAliasIterTaVarFirst(),
                getAliasIterTaVarSecond()));
        t_.put(getAliasCustPair(), new StringList(
                getAliasPairVarFirst(),
                getAliasPairVarSecond()));
        return t_;
    }

    @Override
    public CustList<StringList> allMergeTableTypeMethodNames() {
        CustList<StringList> list_ = super.allMergeTableTypeMethodNames();
        list_.add(new StringList(
                getAliasIterator(),
                getAliasHasNext(),
                getAliasNext(),
                getAliasIteratorTable(),
                getAliasHasNextPair(),
                getAliasNextPair(),
                getAliasGetFirst(),
                getAliasGetSecond(),
                getAliasEnumOrdinal(),
                getAliasEnumName(),
                getAliasRun()
        ));
        return list_;
    }

    @Override
    public StringMap<StringList> allTableTypeFieldNames() {
        StringMap<StringList> f_ = super.allTableTypeFieldNames();
        f_.put(getAliasDifference(), new StringList(
                getAliasDifferenceExpected(),
                getAliasDifferenceFound(),
                getAliasDifferenceFoundNotTrue(),
                getAliasDifferenceFoundNull(),
                getAliasDifferenceStackDiff()
        ));
        f_.put(getAliasInfoTest(), new StringList(
                getAliasInfoTestCount(),
                getAliasInfoTestCurrentMethod(),
                getAliasInfoTestDone(),
                getAliasInfoTestCurrentParams()
        ));
        f_.put(getAliasResult(), new StringList(
                getAliasResultFailMessage(),
                getAliasResultParams(),
                getAliasResultSuccess()
        ));
        f_.put(getAliasExecutedTest(), new StringList(
                getAliasExecutedTestAfter(),
                getAliasExecutedTestBefore(),
                getAliasExecutedTestAnnotations(),
                getAliasExecutedTestMethod(),
                getAliasExecutedTestTest()
        ));
        f_.put(getAliasCustIterator(), new StringList(
                getAliasListItr(),
                getAliasLengthItrLi(),
                getAliasIndexItrLi()
        ));
        f_.put(getAliasList(), new StringList(
                getAliasArrayLi(),
                getAliasLengthLi()
        ));
        f_.put(getAliasCustIterTable(), new StringList(
                getAliasListIterTable(),
                getAliasLengthItrTa(),
                getAliasIndexItrTa()
        ));
        f_.put(getAliasCustPair(), new StringList(
                getAliasFirst(),
                getAliasSecond()
        ));
        f_.put(getAliasTable(), new StringList(
                getAliasListTa()
        ));
        return f_;
    }

    @Override
    public StringMap<StringList> allTableTypeMethodNames() {
        StringMap<StringList> m_ = super.allTableTypeMethodNames();
        m_.put(getAliasThread(), new StringList(
                getAliasStart(),
                getAliasThreadCurrentTime(),
                getAliasIsAlive(),
                getAliasCurrentThread(),
                getAliasJoin(),
                getAliasJoinOthers(),
                getAliasGetId(),
                getAliasGetPriority(),
                getAliasSetPriority(),
                getAliasYield(),
                getAliasSleep(),
                getAliasPrint(),
                getAliasThreadExitHook()));
        m_.put(getAliasThreadSet(), new StringList(
                getAliasThreadSetAdd(),
                getAliasThreadSetAll(),
                getAliasThreadSetContains(),
                getAliasThreadSetRemove(),
                getAliasThreadSetSnapshot()));
        m_.put(getAliasReentrantLock(), new StringList(
                getAliasLock(),
                getAliasUnlock(),
                getAliasIsHeldByCurrentThread()));
        m_.put(getAliasRunnable(), new StringList(
                getAliasRun()));
        m_.put(getAliasFormatType(), new StringList(
                getAliasPrint()));
        m_.put(getAliasAtomicBoolean(), new StringList(
                getAliasGetAtomic(),
                getAliasSetAtomic(),
                getAliasCompareAndSetAtomic(),
                getAliasGetAndSetAtomic(),
                getAliasLazySetAtomic()));
        m_.put(getAliasAtomicInteger(), new StringList(
                getAliasGetAtomic(),
                getAliasSetAtomic(),
                getAliasCompareAndSetAtomic(),
                getAliasGetAndSetAtomic(),
                getAliasLazySetAtomic(),
                getAliasAddAndGetAtomic(),
                getAliasGetAndAddAtomic(),
                getAliasIncrementAndGetAtomic(),
                getAliasGetAndIncrementAtomic(),
                getAliasDecrementAndGetAtomic(),
                getAliasGetAndDecrementAtomic()));
        m_.put(getAliasAtomicLong(), new StringList(
                getAliasGetAtomic(),
                getAliasSetAtomic(),
                getAliasCompareAndSetAtomic(),
                getAliasGetAndSetAtomic(),
                getAliasLazySetAtomic(),
                getAliasAddAndGetAtomic(),
                getAliasGetAndAddAtomic(),
                getAliasIncrementAndGetAtomic(),
                getAliasGetAndIncrementAtomic(),
                getAliasDecrementAndGetAtomic(),
                getAliasGetAndDecrementAtomic()));
        m_.put(getAliasFile(), new StringList(
                getAliasRead(),
                getAliasWrite(),
                getAliasAppendToFile(),
                getAliasFileAbsolutePath(),
                getAliasFileGetLength(),
                getAliasFileGetName(),
                getAliasFileGetParentPath(),
                getAliasFileIsDirectory(),
                getAliasFileIsFile(),
                getAliasFileIsAbsolute(),
                getAliasFileReadBin(),
                getAliasFileWriteBin(),
                getAliasFileDelete(),
                getAliasFileRename(),
                getAliasFileLastModif(),
                getAliasFileListDirectories(),
                getAliasFileListFiles(),
                getAliasFileZipBin(),
                getAliasFileZipBinArray(),
                getAliasFileZipText(),
                getAliasFileZippedBin(),
                getAliasFileZippedBinArray(),
                getAliasFileZippedText(),
                getAliasFileMakeDirs()));
        m_.put(getAliasEntryBinary(), new StringList(
                getAliasEntryName(),
                getAliasEntryValue()));
        m_.put(getAliasEntryText(), new StringList(
                getAliasEntryName(),
                getAliasEntryValue()));
        m_.put(getAliasCustIterator(), new StringList(
                getAliasNext(),
                getAliasHasNext()));
        m_.put(getAliasList(), new StringList(
                getAliasAddLi(),
                getAliasSizeLi(),
                getAliasRemoveLi(),
                getAliasIterator(),
                getAliasListClear()));
        m_.put(getAliasCustPair(), new StringList(
                getAliasGetFirst(),
                getAliasSetFirst(),
                getAliasGetSecond(),
                getAliasSetSecond()));
        m_.put(getAliasCustIterTable(), new StringList(
                getAliasNextPair(),
                getAliasHasNextPair()));
        m_.put(getAliasTable(), new StringList(
                getAliasGetFirstTa(),
                getAliasGetSecondTa(),
                getAliasSetFirstTa(),
                getAliasSetSecondTa(),
                getAliasAddTa(),
                getAliasRemoveTa(),
                getAliasSizeTa(),
                getAliasGetTa(),
                getAliasIteratorTable()
        ));
        m_.put(getAliasExecute(), new StringList(
                getAliasExecuteTests(),
                getAliasExecuteConvert(),
                getAliasExecuteSetupError(),
                getAliasExecuteSetupNoException(),
                getAliasExecuteExecute()
        ));
        m_.put(getAliasParameters(), new StringList(
                getAliasParametersLocation(),
                getAliasParametersMethod()
        ));
        m_.put(getAliasTest(), new StringList(
                getAliasTestException(),
                getAliasTestNullException()
        ));
        m_.put(getAliasAssert(), new StringList(
                getAliasAssertAssert(),
                getAliasAssertAssertNotNull(),
                getAliasAssertAssertNull(),
                getAliasAssertAssertSame(),
                getAliasAssertAssertTrue()
        ));
        m_.put(getAliasDifference(), new StringList(
        ));
        return m_;
    }

    @Override
    public StringList allRefTypes() {
        StringList ref_ =  super.allRefTypes();
        ref_.add(getAliasThread());
        ref_.add(getAliasThreadSet());
        ref_.add(getAliasReentrantLock());
        ref_.add(getAliasAtomicBoolean());
        ref_.add(getAliasAtomicInteger());
        ref_.add(getAliasAtomicLong());
        ref_.add(getAliasFile());
        ref_.add(getAliasIllegalThreadStateException());
        ref_.add(getAliasCustIterator());
        ref_.add(getAliasList());
        ref_.add(getAliasRunnable());
        ref_.add(getAliasFormatType());
        ref_.add(getAliasCustPair());
        ref_.add(getAliasCustIterTable());
        ref_.add(getAliasTable());
        ref_.add(getAliasExecute());
        ref_.add(getAliasInfoTest());
        ref_.add(getAliasExecutedTest());
        ref_.add(getAliasResult());
        ref_.add(getAliasBefore());
        ref_.add(getAliasAfter());
        ref_.add(getAliasParameters());
        ref_.add(getAliasTest());
        ref_.add(getAliasAssert());
        ref_.add(getAliasDifference());
        ref_.add(getAliasConcurrentError());
        return ref_;
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

    public void keyWord(KeyWords _kw,String _lang, StringMap<String> _cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"keywords");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        keyWord(_kw,util_,_cust);
    }
    public void keyWord(KeyWords _kw,StringMap<String> _util,StringMap<String> _cust) {
        _kw.setKeyWordContinue(get(_util,_cust,"Continue"));
        _kw.setKeyWordInstanceof(get(_util,_cust,"Instanceof"));
        _kw.setKeyWordInterface(get(_util,_cust,"Interface"));
        _kw.setKeyWordAbstract(get(_util,_cust,"Abstract"));
        _kw.setKeyWordElseif(get(_util,_cust,"Elseif"));
        _kw.setKeyWordCast(get(_util,_cust,"Cast"));
        _kw.setKeyWordExplicit(get(_util,_cust,"Explicit"));
        _kw.setKeyWordFor(get(_util,_cust,"For"));
        _kw.setKeyWordVar(get(_util,_cust,"Var"));
        _kw.setKeyWordStatic(get(_util,_cust,"Static"));
        _kw.setKeyWordStaticCall(get(_util,_cust,"StaticCall"));
        _kw.setKeyWordNull(get(_util,_cust,"Null"));
        _kw.setKeyWordClass(get(_util,_cust,"Class"));
        _kw.setKeyWordFalse(get(_util,_cust,"False"));
        _kw.setKeyWordFinal(get(_util,_cust,"Final"));
        _kw.setKeyWordBreak(get(_util,_cust,"Break"));
        _kw.setKeyWordIf(get(_util,_cust,"If"));
        _kw.setKeyWordNew(get(_util,_cust,"New"));
        _kw.setKeyWordWhile(get(_util,_cust,"While"));
        _kw.setKeyWordReturn(get(_util,_cust,"Return"));
        _kw.setKeyWordTrue(get(_util,_cust,"True"));
        _kw.setKeyWordPublic(get(_util,_cust,"Public"));
        _kw.setKeyWordPrivate(get(_util,_cust,"Private"));
        _kw.setKeyWordAnnotation(get(_util,_cust,"Annotation"));
        _kw.setKeyWordToString(get(_util,_cust,"ToString"));
        _kw.setKeyWordNbSufBytePrim(get(_util,_cust,"NbSufBytePrim"));
        _kw.setKeyWordNbSufByte(get(_util,_cust,"NbSufByte"));
        _kw.setKeyWordNbSufShortPrim(get(_util,_cust,"NbSufShortPrim"));
        _kw.setKeyWordNbSufShort(get(_util,_cust,"NbSufShort"));
        _kw.setKeyWordNbSufCharacterPrim(get(_util,_cust,"NbSufCharacterPrim"));
        _kw.setKeyWordNbSufCharacter(get(_util,_cust,"NbSufCharacter"));
        _kw.setKeyWordNbSufIntegerPrim(get(_util,_cust,"NbSufIntegerPrim"));
        _kw.setKeyWordNbSufInteger(get(_util,_cust,"NbSufInteger"));
        _kw.setKeyWordNbSufLongPrim(get(_util,_cust,"NbSufLongPrim"));
        _kw.setKeyWordNbSufLong(get(_util,_cust,"NbSufLong"));
        _kw.setKeyWordNbSufFloatPrim(get(_util,_cust,"NbSufFloatPrim"));
        _kw.setKeyWordNbSufFloat(get(_util,_cust,"NbSufFloat"));
        _kw.setKeyWordNbSufDoublePrim(get(_util,_cust,"NbSufDoublePrim"));
        _kw.setKeyWordNbSufDouble(get(_util,_cust,"NbSufDouble"));
        _kw.setKeyWordIter(get(_util,_cust,"Iter"));
        _kw.setKeyWordValue(get(_util,_cust,"Value"));
        _kw.setKeyWordElse(get(_util,_cust,"Else"));
        _kw.setKeyWordCatch(get(_util,_cust,"Catch"));
        _kw.setKeyWordThrow(get(_util,_cust,"Throw"));
        _kw.setKeyWordTry(get(_util,_cust,"Try"));
        _kw.setKeyWordThis(get(_util,_cust,"This"));
        _kw.setKeyWordSuper(get(_util,_cust,"Super"));
        _kw.setKeyWordCase(get(_util,_cust,"Case"));
        _kw.setKeyWordDo(get(_util,_cust,"Do"));
        _kw.setKeyWordEnum(get(_util,_cust,"Enum"));
        _kw.setKeyWordSwitch(get(_util,_cust,"Switch"));
        _kw.setKeyWordIntern(get(_util,_cust,"Intern"));
        _kw.setKeyWordNormal(get(_util,_cust,"Normal"));
        _kw.setKeyWordEscTab(get(_util,_cust,"EscTab"));
        _kw.setKeyWordNbHex(get(_util,_cust,"NbHex"));
        _kw.setKeyWordNbBin(get(_util,_cust,"NbBin"));
        _kw.setKeyWordThat(get(_util,_cust,"That"));
        _kw.setKeyWordBool(get(_util,_cust,"Bool"));
        _kw.setKeyWordValues(get(_util,_cust,"Values"));
        _kw.setKeyWordLambda(get(_util,_cust,"Lambda"));
        _kw.setKeyWordVararg(get(_util,_cust,"Vararg"));
        _kw.setKeyWordId(get(_util,_cust,"Id"));
        _kw.setKeyWordForeach(get(_util,_cust,"Foreach"));
        _kw.setKeyWordNbExpBin(get(_util,_cust,"NbExpBin"));
        _kw.setKeyWordClasschoice(get(_util,_cust,"Classchoice"));
        _kw.setKeyWordFirstopt(get(_util,_cust,"Firstopt"));
        _kw.setKeyWordPackage(get(_util,_cust,"Package"));
        _kw.setKeyWordFinally(get(_util,_cust,"Finally"));
        _kw.setKeyWordEscUnicode(get(_util,_cust,"EscUnicode"));
        _kw.setKeyWordThisaccess(get(_util,_cust,"Thisaccess"));
        _kw.setKeyWordValueOf(get(_util,_cust,"ValueOf"));
        _kw.setKeyWordDefaultValue(get(_util,_cust,"DefaultValue"));
        _kw.setKeyWordEscLine(get(_util,_cust,"EscLine"));
        _kw.setKeyWordOperator(get(_util,_cust,"Operator"));
        _kw.setKeyWordInterfaces(get(_util,_cust,"Interfaces"));
        _kw.setKeyWordSuperaccess(get(_util,_cust,"Superaccess"));
        _kw.setKeyWordEscBound(get(_util,_cust,"EscBound"));
        _kw.setKeyWordEscForm(get(_util,_cust,"EscForm"));
        _kw.setKeyWordEscFeed(get(_util,_cust,"EscFeed"));
        _kw.setKeyWordNbExpDec(get(_util,_cust,"NbExpDec"));
        _kw.setKeyWordProtected(get(_util,_cust,"Protected"));
        _kw.setKeyWordDefault(get(_util,_cust,"Default"));
    }
    public void otherAlias(String _lang, StringMap<String>_cust) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath("resources_lg/aliases",_lang,"types");
        String content_ = reader.read(fileName_);
        StringMap<String> util_ = DocumentBuilder.getMessagesFromContent(content_);
        otherAlias(util_,_cust);
    }
    public void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        otherAlias(_util,_cust);
    }
    private void otherAlias(StringMap<String> _util, StringMap<String> _cust) {
        setDefaultPkg(get(_util,_cust,"DefaultPkg"));
        setAliasMaxValueField(get(_util,_cust,"MAX_VALUE"));
        setAliasMinValueField(get(_util,_cust,"MIN_VALUE"));
        setAliasBadEncode(get(_util,_cust,"BadEncode"));
        setAliasDivisionZero(get(_util,_cust,"DivisionZero"));
        setAliasCharSequence(get(_util,_cust,"CharSequence"));
        setAliasIteratorType(get(_util,_cust,"IteratorType"));
        setAliasEnumParam(get(_util,_cust,"EnumParam"));
        setAliasGetMessage(get(_util,_cust,"GetMessage"));
        setAliasIteratorTableTypeVarFirst(get(_util,_cust,"IteratorTableTypeVarFirst"));
        setAliasIteratorTableTypeVarSecond(get(_util,_cust,"IteratorTableTypeVarSecond"));
        setAliasEquals(get(_util,_cust,"Equals"));
        setAliasLong(get(_util,_cust,"Long"));
        setAliasShort(get(_util,_cust,"Short"));
        setAliasPrimChar(get(_util,_cust,"PrimChar"));
        setAliasNumber(get(_util,_cust,"Number"));
        setAliasParseInt(get(_util,_cust,"ParseInt"));
        setAliasCompare(get(_util,_cust,"Compare"));
        setAliasIntValue(get(_util,_cust,"IntValue"));
        setAliasBoolean(get(_util,_cust,"Boolean"));
        setAliasPrimLong(get(_util,_cust,"PrimLong"));
        setAliasByte(get(_util,_cust,"Byte"));
        setAliasFloat(get(_util,_cust,"Float"));
        setAliasDouble(get(_util,_cust,"Double"));
        setAliasInteger(get(_util,_cust,"Integer"));
        setAliasDigit(get(_util,_cust,"Digit"));
        setAliasIsDigit(get(_util,_cust,"IsDigit"));
        setAliasMath(get(_util,_cust,"Math"));
        setAliasBadSize(get(_util,_cust,"BadSize"));
        setAliasNullPe(get(_util,_cust,"NullPe"));
        setAliasObject(get(_util,_cust,"Object"));
        setAliasIterator(get(_util,_cust,"Iterator"));
        setAliasCastType(get(_util,_cust,"CastType"));
        setAliasStore(get(_util,_cust,"Store"));
        setAliasEnumType(get(_util,_cust,"EnumType"));
        setAliasPrimByte(get(_util,_cust,"PrimByte"));
        setAliasError(get(_util,_cust,"Error"));
        setAliasVoid(get(_util,_cust,"Void"));
        setAliasGetCause(get(_util,_cust,"GetCause"));
        setAliasBadIndex(get(_util,_cust,"BadIndex"));
        setAliasEnums(get(_util,_cust,"Enums"));
        setAliasIterable(get(_util,_cust,"Iterable"));
        setAliasNbFormat(get(_util,_cust,"NbFormat"));
        setAliasSof(get(_util,_cust,"Sof"));
        setAliasParseFloat(get(_util,_cust,"ParseFloat"));
        setAliasToStringMethod(get(_util,_cust,"ToStringMethod"));
        setAliasParseLongOrNull(get(_util,_cust,"ParseLongOrNull"));
        setAliasParseShortOrNull(get(_util,_cust,"ParseShortOrNull"));
        setAliasParseFloatOrNull(get(_util,_cust,"ParseFloatOrNull"));
        setAliasParseDoubleOrNull(get(_util,_cust,"ParseDoubleOrNull"));
        setAliasByteValue(get(_util,_cust,"ByteValue"));
        setAliasCharValue(get(_util,_cust,"CharValue"));
        setAliasPrimBoolean(get(_util,_cust,"PrimBoolean"));
        setAliasParseIntOrNull(get(_util,_cust,"ParseIntOrNull"));
        setAliasPrimShort(get(_util,_cust,"PrimShort"));
        setAliasMinValueField(get(_util,_cust,"MinValueField"));
        setAliasParseBoolean(get(_util,_cust,"ParseBoolean"));
        setAliasParseShort(get(_util,_cust,"ParseShort"));
        setAliasPrimFloat(get(_util,_cust,"PrimFloat"));
        setAliasCompareTo(get(_util,_cust,"CompareTo"));
        setAliasCharacter(get(_util,_cust,"Character"));
        setAliasParseLong(get(_util,_cust,"ParseLong"));
        setAliasValueOfMethod(get(_util,_cust,"ValueOfMethod"));
        setAliasPrimInteger(get(_util,_cust,"PrimInteger"));
        setAliasParseByteOrNull(get(_util,_cust,"ParseByteOrNull"));
        setAliasPrimDouble(get(_util,_cust,"PrimDouble"));
        setAliasBooleanValue(get(_util,_cust,"BooleanValue"));
        setAliasShortValue(get(_util,_cust,"ShortValue"));
        setAliasParseDouble(get(_util,_cust,"ParseDouble"));
        setAliasIllegalArg(get(_util,_cust,"IllegalArg"));
        setAliasParseByte(get(_util,_cust,"ParseByte"));
        setAliasIsUpperCase(get(_util,_cust,"IsUpperCase"));
        setAliasIsWordChar(get(_util,_cust,"IsWordChar"));
        setAliasIsWhitespace(get(_util,_cust,"IsWhitespace"));
        setAliasIsLetterOrDigit(get(_util,_cust,"IsLetterOrDigit"));
        setAliasFloatValue(get(_util,_cust,"FloatValue"));
        setAliasDoubleValue(get(_util,_cust,"DoubleValue"));
        setAliasLongValue(get(_util,_cust,"LongValue"));
        setAliasIsLowerCase(get(_util,_cust,"IsLowerCase"));
        setAliasIndexOf(get(_util,_cust,"IndexOf"));
        setAliasString(get(_util,_cust,"String"));
        setAliasIsEmpty(get(_util,_cust,"IsEmpty"));
        setAliasTrim(get(_util,_cust,"Trim"));
        setAliasGetBytes(get(_util,_cust,"GetBytes"));
        setAliasForDigit(get(_util,_cust,"ForDigit"));
        setAliasIsSpace(get(_util,_cust,"IsSpace"));
        setAliasGetType(get(_util,_cust,"GetType"));
        setAliasContains(get(_util,_cust,"Contains"));
        setAliasReplace(get(_util,_cust,"Replace"));
        setAliasFormat(get(_util,_cust,"Format"));
        setAliasEndsWith(get(_util,_cust,"EndsWith"));
        setAliasCapacity(get(_util,_cust,"Capacity"));
        setAliasSplit(get(_util,_cust,"Split"));
        setAliasAppend(get(_util,_cust,"Append"));
        setAliasIsLetter(get(_util,_cust,"IsLetter"));
        setAliasIsNan(get(_util,_cust,"IsNan"));
        setAliasLength(get(_util,_cust,"Length"));
        setAliasCharAt(get(_util,_cust,"CharAt"));
        setAliasClone(get(_util,_cust,"Clone"));
        setAliasName(get(_util,_cust,"Name"));
        setAliasCall(get(_util,_cust,"Call"));
        setAliasSame(get(_util,_cust,"Same"));
        setAliasMod(get(_util,_cust,"Mod"));
        setAliasReverse(get(_util,_cust,"Reverse"));
        setAliasInsert(get(_util,_cust,"Insert"));
        setAliasAbs(get(_util,_cust,"Abs"));
        setAliasHasNext(get(_util,_cust,"HasNext"));
        setAliasPairType(get(_util,_cust,"PairType"));
        setAliasQuot(get(_util,_cust,"Quot"));
        setAliasNext(get(_util,_cust,"Next"));
        setAliasOrdinal(get(_util,_cust,"Ordinal"));
        setAliasGetFirst(get(_util,_cust,"GetFirst"));
        setAliasFct(get(_util,_cust,"Fct"));
        setAliasDelete(get(_util,_cust,"Delete"));
        setAliasClear(get(_util,_cust,"Clear"));
        setAliasNextPair(get(_util,_cust,"NextPair"));
        setAliasSubstring(get(_util,_cust,"Substring"));
        setAliasSetCharAt(get(_util,_cust,"SetCharAt"));
        setAliasEqualsIgnoreCase(get(_util,_cust,"EqualsIgnoreCase"));
        setAliasIteratorTableType(get(_util,_cust,"IteratorTableType"));
        setAliasDeleteCharAt(get(_util,_cust,"DeleteCharAt"));
        setAliasStartsWith(get(_util,_cust,"StartsWith"));
        setAliasLastIndexOf(get(_util,_cust,"LastIndexOf"));
        setAliasRegionMatches(get(_util,_cust,"RegionMatches"));
        setAliasIteratorTable(get(_util,_cust,"IteratorTable"));
        setAliasIterableTable(get(_util,_cust,"IterableTable"));
        setAliasToLowerCase(get(_util,_cust,"ToLowerCase"));
        setAliasStringBuilder(get(_util,_cust,"StringBuilder"));
        setAliasToUpperCase(get(_util,_cust,"ToUpperCase"));
        setAliasEnsureCapacity(get(_util,_cust,"EnsureCapacity"));
        setAliasSetLength(get(_util,_cust,"SetLength"));
        setAliasTrimToSize(get(_util,_cust,"TrimToSize"));
        setAliasHasNextPair(get(_util,_cust,"HasNextPair"));
        setAliasReplacement(get(_util,_cust,"Replacement"));
        setAliasGetOldString(get(_util,_cust,"GetOldString"));
        setAliasGetNewString(get(_util,_cust,"GetNewString"));
        setAliasGetSecond(get(_util,_cust,"GetSecond"));
        setAliasSubSequence(get(_util,_cust,"SubSequence"));
        setAliasCompareToIgnoreCase(get(_util,_cust,"CompareToIgnoreCase"));
        setAliasToCharArray(get(_util,_cust,"ToCharArray"));
        setAliasReplaceMultiple(get(_util,_cust,"ReplaceMultiple"));
        setAliasSplitStrings(get(_util,_cust,"SplitStrings"));
        setAliasSplitChars(get(_util,_cust,"SplitChars"));
        setAliasIsInfinite(get(_util,_cust,"IsInfinite"));
        setAliasGetDirectionality(get(_util,_cust,"GetDirectionality"));
        setAliasGetCharType(get(_util,_cust,"GetCharType"));
        setAliasIterableTableVarSecond(get(_util,_cust,"IterableTableVarSecond"));
        setAliasGetString(get(_util,_cust,"GetString"));
        setAliasGetAnnotationsParameters(get(_util,_cust,"GetAnnotationsParameters"));
        setAliasReadResourcesNames(get(_util,_cust,"ReadResourcesNames"));
        setAliasInvokeTarget(get(_util,_cust,"InvokeTarget"));
        setAliasGetAnnotations(get(_util,_cust,"GetAnnotations"));
        setAliasGetVariableOwner(get(_util,_cust,"GetVariableOwner"));
        setAliasReadResources(get(_util,_cust,"ReadResources"));
        setAliasResources(get(_util,_cust,"Resources"));
        setAliasClassNotFoundError(get(_util,_cust,"ClassNotFoundError"));
        setAliasEnumValues(get(_util,_cust,"EnumValues"));
        setAliasEnumPredValueOf(get(_util,_cust,"EnumPredValueOf"));
        setAliasIteratorTypeVar(get(_util,_cust,"IteratorTypeVar"));
        setAliasClassType(get(_util,_cust,"ClassType"));
        setAliasIterableTableVarFirst(get(_util,_cust,"IterableTableVarFirst"));
        setAliasPairTypeVarFirst(get(_util,_cust,"PairTypeVarFirst"));
        setAliasErrorInitClass(get(_util,_cust,"ErrorInitClass"));
        setAliasAnnotationType(get(_util,_cust,"AnnotationType"));
        setAliasGetGenericVariableOwner(get(_util,_cust,"GetGenericVariableOwner"));
        setAliasEnumParamVar(get(_util,_cust,"EnumParamVar"));
        setAliasPairTypeVarSecond(get(_util,_cust,"PairTypeVarSecond"));
        setAliasAnnotated(get(_util,_cust,"Annotated"));
        setAliasIterableVar(get(_util,_cust,"IterableVar"));
        setAliasGetDefaultValue(get(_util,_cust,"GetDefaultValue"));
        setAliasMakeGeneric(get(_util,_cust,"MakeGeneric"));
        setAliasGetAllClasses(get(_util,_cust,"GetAllClasses"));
        setAliasGetOperators(get(_util,_cust,"GetOperators"));
        setAliasGetDeclaredMethods(get(_util,_cust,"GetDeclaredMethods"));
        setAliasGetDeclaredStaticMethods(get(_util,_cust,"GetDeclaredStaticMethods"));
        setAliasGetDeclaredConstructors(get(_util,_cust,"GetDeclaredConstructors"));
        setAliasGetDeclaredFields(get(_util,_cust,"GetDeclaredFields"));
        setAliasField(get(_util,_cust,"Field"));
        setAliasIsNormal(get(_util,_cust,"IsNormal"));
        setAliasSameRef(get(_util,_cust,"SameRef"));
        setAliasIsPublic(get(_util,_cust,"IsPublic"));
        setAliasIsArray(get(_util,_cust,"IsArray"));
        setAliasArrayGet(get(_util,_cust,"ArrayGet"));
        setAliasMethod(get(_util,_cust,"Method"));
        setAliasGetField(get(_util,_cust,"GetField"));
        setAliasInvoke(get(_util,_cust,"Invoke"));
        setAliasIsEnum(get(_util,_cust,"IsEnum"));
        setAliasInit(get(_util,_cust,"Init"));
        setAliasForName(get(_util,_cust,"ForName"));
        setAliasIsStatic(get(_util,_cust,"IsStatic"));
        setAliasIsStaticCall(get(_util,_cust,"IsStaticCall"));
        setAliasIsInstanceMethod(get(_util,_cust,"IsInstanceMethod"));
        setAliasGetName(get(_util,_cust,"GetName"));
        setAliasIsClass(get(_util,_cust,"IsClass"));
        setAliasSetField(get(_util,_cust,"SetField"));
        setAliasGetClass(get(_util,_cust,"GetClass"));
        setAliasIsFinal(get(_util,_cust,"IsFinal"));
        setAliasArraySet(get(_util,_cust,"ArraySet"));
        setAliasXor(get(_util,_cust,"Xor"));
        setAliasMult(get(_util,_cust,"Mult"));
        setAliasRandom(get(_util,_cust,"Random"));
        setAliasNegBin(get(_util,_cust,"NegBin"));
        setAliasMinus(get(_util,_cust,"Minus"));
        setAliasEnumName(get(_util,_cust,"EnumName"));
        setAliasBinMod(get(_util,_cust,"BinMod"));
        setAliasLt(get(_util,_cust,"Lt"));
        setAliasGt(get(_util,_cust,"Gt"));
        setAliasLe(get(_util,_cust,"Le"));
        setAliasGe(get(_util,_cust,"Ge"));
        setAliasAnd(get(_util,_cust,"And"));
        setAliasOr(get(_util,_cust,"Or"));
        setAliasPlus(get(_util,_cust,"Plus"));
        setAliasBinQuot(get(_util,_cust,"BinQuot"));
        setAliasNeg(get(_util,_cust,"Neg"));
        setAliasRotateLeft(get(_util,_cust,"RotateLeft"));
        setAliasEnumOrdinal(get(_util,_cust,"EnumOrdinal"));
        setAliasShiftRight(get(_util,_cust,"ShiftRight"));
        setAliasCurrentFullStack(get(_util,_cust,"CurrentFullStack"));
        setAliasGetBounds(get(_util,_cust,"GetBounds"));
        setAliasGetDeclaringClass(get(_util,_cust,"GetDeclaringClass"));
        setAliasStackTraceElement(get(_util,_cust,"StackTraceElement"));
        setAliasEnumValueOf(get(_util,_cust,"EnumValueOf"));
        setAliasArrayNewInstance(get(_util,_cust,"ArrayNewInstance"));
        setAliasGetEnumConstants(get(_util,_cust,"GetEnumConstants"));
        setAliasArrayGetLength(get(_util,_cust,"ArrayGetLength"));
        setAliasRotateRight(get(_util,_cust,"RotateRight"));
        setAliasGetGenericBounds(get(_util,_cust,"GetGenericBounds"));
        setAliasBitShiftLeft(get(_util,_cust,"BitShiftLeft"));
        setAliasShiftLeft(get(_util,_cust,"ShiftLeft"));
        setAliasDefaultInstance(get(_util,_cust,"DefaultInstance"));
        setAliasCurrentStack(get(_util,_cust,"CurrentStack"));
        setAliasBitShiftRight(get(_util,_cust,"BitShiftRight"));
        setAliasGetParameterNames(get(_util,_cust,"GetParameterNames"));
        setAliasGetPrettyName(get(_util,_cust,"GetPrettyName"));
        setAliasGetUpperBounds(get(_util,_cust,"GetUpperBounds"));
        setAliasGetParameterTypes(get(_util,_cust,"GetParameterTypes"));
        setAliasGetGenericReturnType(get(_util,_cust,"GetGenericReturnType"));
        setAliasInvokeDirect(get(_util,_cust,"InvokeDirect"));
        setAliasStringUtil(get(_util,_cust,"StringUtil"));
        setAliasGetLowerBounds(get(_util,_cust,"GetLowerBounds"));
        setAliasGetTypeParameters(get(_util,_cust,"GetTypeParameters"));
        setAliasConstructor(get(_util,_cust,"Constructor"));
        setAliasSetParent(get(_util,_cust,"SetParent"));
        setAliasNewInstance(get(_util,_cust,"NewInstance"));
        setAliasGetEnclosingType(get(_util,_cust,"GetEnclosingType"));
        setAliasGetInterfaces(get(_util,_cust,"GetInterfaces"));
        setAliasObjectsUtil(get(_util,_cust,"ObjectsUtil"));
        setAliasGetDeclaredClasses(get(_util,_cust,"GetDeclaredClasses"));
        setAliasGetSuperClass(get(_util,_cust,"GetSuperClass"));
        setAliasGetParent(get(_util,_cust,"GetParent"));
        setAliasGetComponentType(get(_util,_cust,"GetComponentType"));
        setAliasGetFileName(get(_util,_cust,"GetFileName"));
        setAliasGetGenericSuperClass(get(_util,_cust,"GetGenericSuperClass"));
        setAliasGetGenericInterfaces(get(_util,_cust,"GetGenericInterfaces"));
        setAliasIsAbstract(get(_util,_cust,"IsAbstract"));
        setAliasMakeArray(get(_util,_cust,"MakeArray"));
        setAliasIsInterface(get(_util,_cust,"IsInterface"));
        setAliasMakeWildCard(get(_util,_cust,"MakeWildCard"));
        setAliasIsTypeVariable(get(_util,_cust,"IsTypeVariable"));
        setAliasIsPrivate(get(_util,_cust,"IsPrivate"));
        setAliasIsVarargs(get(_util,_cust,"IsVarargs"));
        setAliasIsInstance(get(_util,_cust,"IsInstance"));
        setAliasGetReturnType(get(_util,_cust,"GetReturnType"));
        setAliasGetActualTypeArguments(get(_util,_cust,"GetActualTypeArguments"));
        setAliasIsProtected(get(_util,_cust,"IsProtected"));
        setAliasIsPrimitive(get(_util,_cust,"IsPrimitive"));
        setAliasIsWildCard(get(_util,_cust,"IsWildCard"));
        setAliasIsAnnotation(get(_util,_cust,"IsAnnotation"));
        setAliasGetGenericType(get(_util,_cust,"GetGenericType"));
        setAliasIsAssignableFrom(get(_util,_cust,"IsAssignableFrom"));
        setAliasIsVariable(get(_util,_cust,"IsVariable"));
        setAliasIsPackage(get(_util,_cust,"IsPackage"));
        setAliasIllegalThreadStateException(get(_util,_cust,"IllegalThreadStateException"));
        setAliasFileGetLength(get(_util,_cust,"FileGetLength"));
        setAliasAtomicInteger(get(_util,_cust,"AtomicInteger"));
        setAliasCurrentThread(get(_util,_cust,"CurrentThread"));
        setAliasFormatType(get(_util,_cust,"FormatType"));
        setAliasAtomicBoolean(get(_util,_cust,"AtomicBoolean"));
        setAliasSetAtomic(get(_util,_cust,"SetAtomic"));
        setAliasFileIsDirectory(get(_util,_cust,"FileIsDirectory"));
        setAliasFileGetParentPath(get(_util,_cust,"FileGetParentPath"));
        setAliasAtomicLong(get(_util,_cust,"AtomicLong"));
        setAliasFileAbsolutePath(get(_util,_cust,"FileAbsolutePath"));
        setAliasFileLastModif(get(_util,_cust,"FileLastModif"));
        setAliasFileGetName(get(_util,_cust,"FileGetName"));
        setAliasGetAtomic(get(_util,_cust,"GetAtomic"));
        setAliasLazySetAtomic(get(_util,_cust,"LazySetAtomic"));
        setAliasCompareAndSetAtomic(get(_util,_cust,"CompareAndSetAtomic"));
        setAliasGetAndAddAtomic(get(_util,_cust,"GetAndAddAtomic"));
        setAliasAddAndGetAtomic(get(_util,_cust,"AddAndGetAtomic"));
        setAliasGetAndIncrementAtomic(get(_util,_cust,"GetAndIncrementAtomic"));
        setAliasIncrementAndGetAtomic(get(_util,_cust,"IncrementAndGetAtomic"));
        setAliasGetAndDecrementAtomic(get(_util,_cust,"GetAndDecrementAtomic"));
        setAliasDecrementAndGetAtomic(get(_util,_cust,"DecrementAndGetAtomic"));
        setAliasGetAndSetAtomic(get(_util,_cust,"GetAndSetAtomic"));
        setAliasReentrantLock(get(_util,_cust,"ReentrantLock"));
        setAliasJoinOthers(get(_util,_cust,"JoinOthers"));
        setAliasFileIsFile(get(_util,_cust,"FileIsFile"));
        setAliasThreadExitHook(get(_util,_cust,"ThreadExitHook"));
        setAliasAppendToFile(get(_util,_cust,"AppendToFile"));
        setAliasThreadCurrentTime(get(_util,_cust,"ThreadCurrentTime"));
        setAliasSetPriority(get(_util,_cust,"SetPriority"));
        setAliasFileListFiles(get(_util,_cust,"FileListFiles"));
        setAliasGetPriority(get(_util,_cust,"GetPriority"));
        setAliasIsHeldByCurrentThread(get(_util,_cust,"IsHeldByCurrentThread"));
        setAliasFileListDirectories(get(_util,_cust,"FileListDirectories"));
        setAliasLengthItrTa(get(_util,_cust,"LengthItrTa"));
        setAliasExecutedTestAnnotations(get(_util,_cust,"ExecutedTestAnnotations"));
        setAliasPairVarFirst(get(_util,_cust,"PairVarFirst"));
        setAliasIndexItrLi(get(_util,_cust,"IndexItrLi"));
        setAliasListIterTable(get(_util,_cust,"ListIterTable"));
        setAliasDifference(get(_util,_cust,"Difference"));
        setAliasTableVarSecond(get(_util,_cust,"TableVarSecond"));
        setAliasIterTaVarFirst(get(_util,_cust,"IterTaVarFirst"));
        setAliasExecutedTestBefore(get(_util,_cust,"ExecutedTestBefore"));
        setAliasCustIteratorVar(get(_util,_cust,"CustIteratorVar"));
        setAliasGetSecondTa(get(_util,_cust,"GetSecondTa"));
        setAliasExecutedTestTest(get(_util,_cust,"ExecutedTestTest"));
        setAliasExecutedTestMethod(get(_util,_cust,"ExecutedTestMethod"));
        setAliasExecutedTestAfter(get(_util,_cust,"ExecutedTestAfter"));
        setAliasCustIterator(get(_util,_cust,"CustIterator"));
        setAliasListClear(get(_util,_cust,"ListClear"));
        setAliasGetFirstTa(get(_util,_cust,"GetFirstTa"));
        setAliasSetSecondTa(get(_util,_cust,"SetSecondTa"));
        setAliasFileMakeDirs(get(_util,_cust,"FileMakeDirs"));
        setAliasFileZippedBin(get(_util,_cust,"FileZippedBin"));
        setAliasFileZippedBinArray(get(_util,_cust,"FileZippedBinArray"));
        setAliasFileZippedText(get(_util,_cust,"FileZippedText"));
        setAliasFileZipBin(get(_util,_cust,"FileZipBin"));
        setAliasFileZipBinArray(get(_util,_cust,"FileZipBinArray"));
        setAliasFileZipText(get(_util,_cust,"FileZipText"));
        setAliasEntryBinary(get(_util,_cust,"EntryBinary"));
        setAliasEntryText(get(_util,_cust,"EntryText"));
        setAliasEntryName(get(_util,_cust,"EntryName"));
        setAliasEntryValue(get(_util,_cust,"EntryValue"));
        setAliasFileIsAbsolute(get(_util,_cust,"FileIsAbsolute"));
        setAliasFileReadBin(get(_util,_cust,"FileReadBin"));
        setAliasFileWriteBin(get(_util,_cust,"FileWriteBin"));
        setAliasFileDelete(get(_util,_cust,"FileDelete"));
        setAliasFileRename(get(_util,_cust,"FileRename"));
        setAliasCustIterTable(get(_util,_cust,"CustIterTable"));
        setAliasTableVarFirst(get(_util,_cust,"TableVarFirst"));
        setAliasSetSecond(get(_util,_cust,"SetSecond"));
        setAliasIndexItrTa(get(_util,_cust,"IndexItrTa"));
        setAliasIterTaVarSecond(get(_util,_cust,"IterTaVarSecond"));
        setAliasLengthItrLi(get(_util,_cust,"LengthItrLi"));
        setAliasSetFirstTa(get(_util,_cust,"SetFirstTa"));
        setAliasExecutedTest(get(_util,_cust,"ExecutedTest"));
        setAliasParameters(get(_util,_cust,"Parameters"));
        setAliasTestException(get(_util,_cust,"TestException"));
        setAliasTestNullException(get(_util,_cust,"TestNullException"));
        setAliasPairVarSecond(get(_util,_cust,"PairVarSecond"));
        setAliasExecuteExecute(get(_util,_cust,"ExecuteExecute"));
        setAliasExecuteSetupNoException(get(_util,_cust,"ExecuteSetupNoException"));
        setAliasAssertAssert(get(_util,_cust,"AssertAssert"));
        setAliasAssertAssertNull(get(_util,_cust,"AssertAssertNull"));
        setAliasAssertAssertNotNull(get(_util,_cust,"AssertAssertNotNull"));
        setAliasDifferenceFoundNull(get(_util,_cust,"DifferenceFoundNull"));
        setAliasResultSuccess(get(_util,_cust,"ResultSuccess"));
        setAliasInfoTestCurrentClass(get(_util,_cust,"InfoTestCurrentClass"));
        setAliasExecuteConvert(get(_util,_cust,"ExecuteConvert"));
        setAliasConcurrentError(get(_util,_cust,"ConcurrentError"));
        setAliasResultFailMessage(get(_util,_cust,"ResultFailMessage"));
        setAliasAssertAssertTrue(get(_util,_cust,"AssertAssertTrue"));
        setAliasInfoTestCurrentMethod(get(_util,_cust,"InfoTestCurrentMethod"));
        setAliasResultParams(get(_util,_cust,"ResultParams"));
        setAliasParametersMethod(get(_util,_cust,"ParametersMethod"));
        setAliasExecuteSetupError(get(_util,_cust,"ExecuteSetupError"));
        setAliasAssertAssertSame(get(_util,_cust,"AssertAssertSame"));
        setAliasDifferenceFound(get(_util,_cust,"DifferenceFound"));
        setAliasDifferenceFoundNotTrue(get(_util,_cust,"DifferenceFoundNotTrue"));
        setAliasParametersLocation(get(_util,_cust,"ParametersLocation"));
        setAliasInfoTestCount(get(_util,_cust,"InfoTestCount"));
        setAliasInfoTestDone(get(_util,_cust,"InfoTestDone"));
        setAliasDifferenceStackDiff(get(_util,_cust,"DifferenceStackDiff"));
        setAliasExecuteTests(get(_util,_cust,"ExecuteTests"));
        setAliasDifferenceExpected(get(_util,_cust,"DifferenceExpected"));
        setAliasInfoTestCurrentParams(get(_util,_cust,"InfoTestCurrentParams"));
        setAliasRunnable(get(_util,_cust,"Runnable"));
        setAliasThread(get(_util,_cust,"Thread"));
        setAliasThreadSet(get(_util,_cust,"ThreadSet"));
        setAliasThreadSetAll(get(_util,_cust,"ThreadSetAll"));
        setAliasThreadSetAdd(get(_util,_cust,"ThreadSetAdd"));
        setAliasThreadSetContains(get(_util,_cust,"ThreadSetContains"));
        setAliasThreadSetRemove(get(_util,_cust,"ThreadSetRemove"));
        setAliasThreadSetSnapshot(get(_util,_cust,"ThreadSetSnapshot"));
        setAliasStart(get(_util,_cust,"Start"));
        setAliasJoin(get(_util,_cust,"Join"));
        setAliasRun(get(_util,_cust,"Run"));
        setAliasLengthLi(get(_util,_cust,"LengthLi"));
        setAliasCustPair(get(_util,_cust,"CustPair"));
        setAliasListTa(get(_util,_cust,"ListTa"));
        setAliasGetId(get(_util,_cust,"GetId"));
        setAliasIsAlive(get(_util,_cust,"IsAlive"));
        setAliasIsEnded(get(_util,_cust,"IsEnded"));
        setAliasEnd(get(_util,_cust,"End"));
        setAliasPrint(get(_util,_cust,"Print"));
        setAliasListItr(get(_util,_cust,"ListItr"));
        setAliasRemoveLi(get(_util,_cust,"RemoveLi"));
        setAliasArrayLi(get(_util,_cust,"ArrayLi"));
        setAliasFirst(get(_util,_cust,"First"));
        setAliasSleep(get(_util,_cust,"Sleep"));
        setAliasFile(get(_util,_cust,"File"));
        setAliasRead(get(_util,_cust,"Read"));
        setAliasList(get(_util,_cust,"List"));
        setAliasUnlock(get(_util,_cust,"Unlock"));
        setAliasSizeLi(get(_util,_cust,"SizeLi"));
        setAliasListVar(get(_util,_cust,"ListVar"));
        setAliasSecond(get(_util,_cust,"Second"));
        setAliasAddLi(get(_util,_cust,"AddLi"));
        setAliasYield(get(_util,_cust,"Yield"));
        setAliasSetFirst(get(_util,_cust,"SetFirst"));
        setAliasLock(get(_util,_cust,"Lock"));
        setAliasWrite(get(_util,_cust,"Write"));
        setAliasTable(get(_util,_cust,"Table"));
        setAliasResult(get(_util,_cust,"Result"));
        setAliasAddTa(get(_util,_cust,"AddTa"));
        setAliasInfoTest(get(_util,_cust,"InfoTest"));
        setAliasRemoveTa(get(_util,_cust,"RemoveTa"));
        setAliasBefore(get(_util,_cust,"Before"));
        setAliasGetTa(get(_util,_cust,"GetTa"));
        setAliasTest(get(_util,_cust,"Test"));
        setAliasExecute(get(_util,_cust,"Execute"));
        setAliasAfter(get(_util,_cust,"After"));
        setAliasSizeTa(get(_util,_cust,"SizeTa"));
        setAliasAssert(get(_util,_cust,"Assert"));
        setFalseString(get(_util,_cust,"FalseString"));
        setTrueString(get(_util,_cust,"TrueString"));
        setNullString(get(_util,_cust,"NullString"));
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

}
