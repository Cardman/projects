package code.expressionlanguage.utilimpl;

import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.options.AbsBuildLightResultContextNext;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.utilcompo.MathAdvAliases;
import code.expressionlanguage.utilcompo.stds.DfAtomicInteger;
import code.expressionlanguage.utilcompo.stds.FctAtomicInteger0;
import code.mock.*;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class SampleAtIntLgNames implements AbsBuildLightResultContextNext {

    public static final String THREAD = "$core.AtomicInteger";

    @Override
    public void build(LgNames _fwd) {
        _fwd.buildBase();
        TranslationsFile en_ = new TranslationsFile();
        LgNamesContent.en(en_);
        MathAdvAliases.en(en_);
        MathAdvAliases a_ = new MathAdvAliases();
        StringMap<String> mapp_ = TranslationsFile.extractMap(en_);
        StringMap<String> keys_ = TranslationsFile.extractKeys(en_);
        a_.build(mapp_,new StringMap<String>(), keys_);
        MockLightLgNames stds_ = new MockLightLgNames();
        stds_.setAliasLgInt(a_.getAliasLgInt());
        stds_.setAliasRate(a_.getAliasRate());
        _fwd.getContent().build(mapp_,new StringMap<String>(), keys_);
        _fwd.getContent().getCoreNames().setObjType(new StandardClass(_fwd.getContent().getCoreNames().getAliasObject(),new CustList<CstFieldInfo>(),new CustList<StandardConstructor>(),new CustList<StandardMethod>(),"", MethodModifier.NORMAL));
        a_.buildOther(_fwd.getContent());
        MockThreadFactory fi_ = new MockThreadFactory(new MockTrueRand(),new MockFileSet(0,new long[0],new String[]{"/"}));
        CustList<StandardMethod> m_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        StandardClass std_ = new StandardClass(THREAD, new CustList<CstFieldInfo>(), constructors_, m_, _fwd.getCoreNames().getAliasObject(), MethodModifier.FINAL, new DfAtomicInteger(fi_,THREAD));
        std_.addSuperStdTypes(_fwd.getCoreNames().getObjType());
        StandardType.addType(_fwd.getStandards(),THREAD, std_);
        StringList params_ = new StringList();
        StandardConstructor ctor_ = new StandardConstructor(params_,false,new FctAtomicInteger0(fi_,THREAD));
        StandardNamedFunction.addFct(constructors_, ctor_);
    }
}
