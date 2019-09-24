package code.expressionlanguage.utilcompo;

import java.io.File;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.RootBlock;
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
    private String aliasThreadCurrentTime;
    private String aliasThreadExitHook;
    private String aliasCurrentThread;
    private String aliasStart;
    private String aliasJoin;
    private String aliasJoinOthers;
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

        content_ = ResourceFiles.ressourceFichier("resources_lg/tests/run.txt");
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
        map_.put("{Class}",getAliasClass());
        map_.put("{InvokeTarget}",getAliasInvokeTarget());
        map_.put("{Stack}",getAliasStackTraceElement());
        map_.put("{Object}",getAliasObject());
        map_.put("{String}",getAliasString());
        map_.put("{StringBuilder}",getAliasStringBuilder());
        map_.put("{Method}",getAliasMethod());
        map_.put("{Annotation}",getAliasAnnotation());
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
        map_.put("{valueOf}",getAliasValueOf());
        map_.put("{append}",getAliasAppend());
        map_.put("{insert}",getAliasInsert());
        map_.put("{toString}",getAliasToString());
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
        content_ = ResourceFiles.ressourceFichier("resources_lg/threads/formatting.txt");
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
                boolean alive_ = thread_.isAlive();
                try {
                    thread_.join();
                } catch (Exception e) {
                    //skip exception
                }
                res_.setResult(new BooleanStruct(alive_));
                return res_;
            }
            if (StringList.quickEq(name_,aliasCurrentThread)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                StdStruct std_ = StdStruct.newInstance(Thread.currentThread(),aliasThread);
                res_.setResult(std_);
                return res_;
            }
            if (StringList.quickEq(name_,aliasThreadExitHook)) {
                if (_cont.isInitEnums()) {
                    _cont.failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (!(_args[0] instanceof StdStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                StdStruct a_ = (StdStruct) _args[0];
                Thread th_ = (Thread) a_.getInstance();
                Runtime.getRuntime().addShutdownHook(th_);
                ((RunnableContextEl)_cont).getCustInit().initHook(th_);
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
                Thread thread_ = (Thread) ((StdStruct) _instance).getInstance();
                boolean alive_ = thread_.isAlive();
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
                ClassMethodId polymorph_ = new ClassMethodId(stds_.aliasFormatType,new MethodId(true,stds_.aliasPrint,new StringList(aliasObject_)));
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
            ClassMethodId polymorph_ = new ClassMethodId(stds_.aliasFormatType,new MethodId(true,stds_.aliasPrint,new StringList(aliasString_,aliasObject_),true));
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
                getAliasAppendToFile(),
                getAliasFileAbsolutePath(),
                getAliasFileGetLength(),
                getAliasFileGetName(),
                getAliasFileGetParentPath(),
                getAliasFileIsDirectory(),
                getAliasFileIsFile(),
                getAliasFileLastModif(),
                getAliasFileListDirectories(),
                getAliasFileListFiles(),
                getAliasFileMakeDirs()));
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

    public void otherAlias(String _lang) {
        if (StringList.quickEq(_lang, "en")) {
            setAliasPrint("print");
            setAliasRunnable("$core.Runnable");
            setAliasFormatType("$core.Formatting");
            setAliasRun("run");
            setAliasThread("$core.Thread");
            setAliasThreadExitHook("exitHook");
            setAliasStart("start");
            setAliasThreadCurrentTime("currentTime");
            setAliasCurrentThread("currentThread");
            setAliasJoin("join");
            setAliasJoinOthers("joinOthers");
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
            setAliasFileAbsolutePath("absolute");
            setAliasFileGetLength("length");
            setAliasFileGetName("name");
            setAliasFileGetParentPath("parentPath");
            setAliasFileIsDirectory("isDirectory");
            setAliasFileIsFile("isFile");
            setAliasFileLastModif("lastModification");
            setAliasFileListDirectories("directories");
            setAliasFileListFiles("files");
            setAliasFileMakeDirs("makeDirectories");
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
            setAliasListClear("clear");
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
            setAliasExecute("$core.Execute");
            setAliasResult("$core.Result");
            setAliasExecutedTest("$core.ExecutedTest");
            setAliasTest("$core.Test");
            setAliasAfter("$core.After");
            setAliasBefore("$core.Before");
            setAliasParameters("$core.Parameters");
            setAliasAssert("$core.Assert");
            setAliasDifference("$core.Difference");
            setAliasInfoTest("$core.InfoTest");
            setAliasTestException("exception");
            setAliasTestNullException("nullException");
            setAliasExecutedTestAfter("after");
            setAliasExecutedTestBefore("before");
            setAliasExecutedTestMethod("method");
            setAliasExecutedTestAnnotations("annotations");
            setAliasExecutedTestTest("test");
            setAliasResultFailMessage("failMessage");
            setAliasResultParams("params");
            setAliasResultSuccess("success");
            setAliasParametersLocation("location");
            setAliasParametersMethod("method");
            setAliasExecuteConvert("convert");
            setAliasExecuteExecute("execute");
            setAliasExecuteSetupError("setupError");
            setAliasExecuteSetupNoException("setupNoException");
            setAliasExecuteTests("tests");
            setAliasAssertAssert("assert");
            setAliasAssertAssertTrue("assertTrue");
            setAliasAssertAssertNotNull("assertNotNull");
            setAliasAssertAssertNull("assertNull");
            setAliasAssertAssertSame("assertSame");
            setAliasDifferenceExpected("expected");
            setAliasDifferenceFound("found");
            setAliasDifferenceFoundNotTrue("foundNotTrue");
            setAliasDifferenceFoundNull("foundNull");
            setAliasDifferenceStackDiff("stackDiff");
            setAliasInfoTestCount("count");
            setAliasInfoTestCurrentClass("currentClass");
            setAliasInfoTestCurrentMethod("currentMethod");
            setAliasInfoTestCurrentParams("currentParams");
            setAliasInfoTestDone("done");
            setAliasConcurrentError("$core.ConcurrentError");
        } else {
            setAliasPrint("afficher");
            setAliasRunnable("$coeur.Executable");
            setAliasFormatType("$coeur.Formattage");
            setAliasRun("executer");
            setAliasThread("$coeur.Tache");
            setAliasThreadExitHook("sortieIntercept");
            setAliasStart("demarrer");
            setAliasThreadCurrentTime("instant");
            setAliasCurrentThread("tacheCourante");
            setAliasJoin("attendre");
            setAliasJoinOthers("attendreAutres");
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
            setAliasFileAbsolutePath("absolu");
            setAliasFileGetLength("lg");
            setAliasFileGetName("nom");
            setAliasFileGetParentPath("chParent");
            setAliasFileIsDirectory("estDossier");
            setAliasFileIsFile("estFichier");
            setAliasFileLastModif("derModification");
            setAliasFileListDirectories("dossiers");
            setAliasFileListFiles("fichiers");
            setAliasFileMakeDirs("acheminerDossiers");
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
            setAliasCustPair("$coeur.PaireImpl");
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
            setAliasListClear("toutSuppr");
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
            setAliasInfoTest("$coeur.InfoTest");
            setAliasExecute("$coeur.Executer");
            setAliasResult("$coeur.Resultat");
            setAliasExecutedTest("$coeur.TestExecute");
            setAliasTest("$coeur.Test");
            setAliasAfter("$coeur.Apres");
            setAliasBefore("$coeur.Avant");
            setAliasParameters("$coeur.Parametres");
            setAliasAssert("$coeur.Assertion");
            setAliasDifference("$coeur.Difference");
            setAliasTestException("exception");
            setAliasTestNullException("nulleException");
            setAliasExecutedTestAfter("apres");
            setAliasExecutedTestBefore("avant");
            setAliasExecutedTestMethod("methode");
            setAliasExecutedTestAnnotations("annotations");
            setAliasExecutedTestTest("test");
            setAliasResultFailMessage("messageErreur");
            setAliasResultParams("params");
            setAliasResultSuccess("succes");
            setAliasParametersLocation("location");
            setAliasParametersMethod("methode");
            setAliasExecuteConvert("convertier");
            setAliasExecuteExecute("executer");
            setAliasExecuteSetupError("majErreur");
            setAliasExecuteSetupNoException("majSansException");
            setAliasExecuteTests("tests");
            setAliasAssertAssert("assert");
            setAliasAssertAssertTrue("assertVrai");
            setAliasAssertAssertNotNull("assertNonNul");
            setAliasAssertAssertNull("assertNul");
            setAliasAssertAssertSame("assertMeme");
            setAliasDifferenceExpected("attendu");
            setAliasDifferenceFound("trouve");
            setAliasDifferenceFoundNotTrue("trouvePasVrai");
            setAliasDifferenceFoundNull("trouveNull");
            setAliasDifferenceStackDiff("pileDiff");
            setAliasInfoTestCount("nb");
            setAliasInfoTestCurrentClass("classeCourante");
            setAliasInfoTestCurrentMethod("methodCourante");
            setAliasInfoTestCurrentParams("paramsCourants");
            setAliasInfoTestDone("fait");
            setAliasConcurrentError("$coeur.ErreurConcurrentielle");
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
