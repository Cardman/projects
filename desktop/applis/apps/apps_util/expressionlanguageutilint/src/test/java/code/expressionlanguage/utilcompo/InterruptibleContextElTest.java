package code.expressionlanguage.utilcompo;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.DefStackStopper;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.DfInstancer;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.stds.FctInterrupt;
import code.maths.montecarlo.CustomSeedGene;
import code.sml.util.TranslationsFile;
import code.threads.ConcreteBoolean;
import code.util.StringMap;
import org.junit.Test;

public  final class InterruptibleContextElTest extends EquallableElIntUtil {
    @Test
    public void callsOrException1() {
        InterruptibleContextEl ctx_ = new InterruptibleContextEl(new ConcreteBoolean(),null);
        StackCall st_ = stack(ctx_);
        assertFalse(ctx_.callsOrException(st_));
    }
    @Test
    public void callsOrException2() {
        InterruptibleContextEl ctx_ = new InterruptibleContextEl(new ConcreteBoolean(),null);
        StackCall st_ = stack(ctx_);
        st_.setCallingState(new CustomFoundExc(NullStruct.NULL_VALUE));
        assertTrue(ctx_.callsOrException(st_));
    }
    @Test
    public void callsOrException3() {
        InterruptibleContextEl ctx_ = new InterruptibleContextEl(new ConcreteBoolean(),null);
        StackCall st_ = stack(ctx_);
        call(new FctInterrupt(),null,ctx_,null,null,st_);
        assertTrue(ctx_.getInterrupt().get());
        assertTrue(ctx_.callsOrException(st_));
    }
    @Test
    public void callsOrException4() {
        InterruptibleContextEl ctx_ = new InterruptibleContextEl(new ConcreteBoolean(),null);
        StackCall st_ = stack(ctx_);
        ctx_.stopJoinSleep();
        assertTrue(ctx_.getInterrupt().get());
        assertTrue(ctx_.callsOrException(st_));
    }
    @Test
    public void view1() {
        StringViewReplaceAliases s_ = new StringViewReplaceAliases();
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.en(en_);
        KeyWords kwl_ = new KeyWords();
        TranslationsFile k_ = KeyWords.en();
        kwl_.build(TranslationsFile.extractMap(k_),new StringMap<String>(),TranslationsFile.extractKeys(k_));
        StringViewReplaceAliases.en(en_);
        s_.build(TranslationsFile.extractMap(en_),new StringMap<String>(),TranslationsFile.extractKeys(en_));
        assertFalse(s_.allRefTypes(TranslationsFile.extractKeys(en_)).isEmpty());
        assertFalse(s_.allTableTypeFieldNames(TranslationsFile.extractKeys(en_)).isEmpty());
        assertFalse(s_.allTableTypeMethodNames(TranslationsFile.extractKeys(en_)).isEmpty());
        assertFalse(s_.allTableTypeMethodParamNames(TranslationsFile.extractKeys(en_)).isEmpty());
        assertFalse(s_.buildFiles(kwl_,new LgNamesContent()).isEmpty());
    }
    @Test
    public void view2() {
        StringViewReplaceAliases s_ = new StringViewReplaceAliases();
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.fr(en_);
        KeyWords kwl_ = new KeyWords();
        TranslationsFile k_ = KeyWords.fr();
        kwl_.build(TranslationsFile.extractMap(k_),new StringMap<String>(),TranslationsFile.extractKeys(k_));
        StringViewReplaceAliases.fr(en_);
        s_.build(TranslationsFile.extractMap(en_),new StringMap<String>(),TranslationsFile.extractKeys(en_));
        assertFalse(s_.allRefTypes(TranslationsFile.extractKeys(en_)).isEmpty());
        assertFalse(s_.allTableTypeFieldNames(TranslationsFile.extractKeys(en_)).isEmpty());
        assertFalse(s_.allTableTypeMethodNames(TranslationsFile.extractKeys(en_)).isEmpty());
        assertFalse(s_.allTableTypeMethodParamNames(TranslationsFile.extractKeys(en_)).isEmpty());
        assertFalse(s_.buildFiles(kwl_,new LgNamesContent()).isEmpty());
    }
    public static Struct call(StdCaller _caller, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return value(_caller.call(_exit, _cont, _instance, _firstArgs, _stackCall));
    }

    public static Struct call(DfInstancer _caller, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return value(_caller.call(_exit, _cont, _firstArgs, _stackCall));
    }

    public static Struct value(ArgumentWrapper _a) {
        return _a.getValue().getStruct();
    }
    public static StackCall stack(ContextEl _phase) {
        return StackCall.newInstance(new DefStackStopper(),InitPhase.NOTHING,_phase,new CustomSeedGene());
    }
}
