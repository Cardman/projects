package code.mock;

import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.utilcompo.LightLgNamesWithNewAliases;
import code.expressionlanguage.utilcompo.StringViewReplaceAliases;
import code.expressionlanguage.utilcompo.stds.FctInterrupt;
import code.maths.montecarlo.DefaultGenerator;
import code.util.CustList;
import code.util.StringList;

public final class MockLgNames extends LgNames implements LightLgNamesWithNewAliases {

    public static final String THREAD = "$core.Thread";
    private final StringViewReplaceAliases stringViewReplaceAliases = new StringViewReplaceAliases();

    public MockLgNames() {
        super(DefaultGenerator.oneElt());
    }
    @Override
    public StringViewReplaceAliases getStrAlias() {
        return stringViewReplaceAliases;
    }

    @Override
    public void build() {
        buildBase();
        CustList<StandardMethod> m_ = new CustList<StandardMethod>();
        StandardClass std_ = new StandardClass(THREAD, new CustList<CstFieldInfo>(), new CustList<StandardConstructor>(), m_, getCoreNames().getAliasObject(), StdClassModifier.HYPER_ABSTRACT);
        std_.addSuperStdTypes(getCoreNames().getObjType());
        StandardType.addType(getStandards(),THREAD, std_);
        StandardNamedFunction.addFct(m_,new StandardMethod("currentThread",new StringList(), THREAD,false, MethodModifier.STATIC,new FctInterrupt()));
        StandardNamedFunction.addFct(m_,new StandardMethod("interrupt",new StringList(), THREAD,false, MethodModifier.STATIC,new FctInterrupt()));
    }

    @Override
    public CommonExecutionInfos newContextCommon(Options _opt, Forwards _options) {
        return commonExecutionInfos(new MockInterceptorStdCaller(),_opt,_options,new DefaultInitializer());
    }

    @Override
    public AbstractInterceptorStdCaller interceptor() {
        return new MockInterceptorStdCaller();
    }
}
