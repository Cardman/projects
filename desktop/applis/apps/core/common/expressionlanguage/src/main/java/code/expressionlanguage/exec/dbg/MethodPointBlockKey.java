package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.util.CustList;
import code.util.core.StringUtil;

public final class MethodPointBlockKey implements AbsKeyPoint{
    private final MemberCallingsBlock id;
    private final String key;

    public MethodPointBlockKey(MemberCallingsBlock _i, String _k) {
        this.id = _i;
        this.key = _k;
    }

    public CustList<String> names(ContextEl _conf) {
        if (id instanceof NamedFunctionBlock) {
            if (StringUtil.quickEq(((NamedFunctionBlock)id).getName(),"[]=")){
                CustList<String> ls_ = new CustList<String>(((NamedFunctionBlock)id).getParametersNames());
                ls_.add(_conf.getClasses().getKeyWordValue());
                return ls_;
            }
            return ((NamedFunctionBlock)id).getParametersNames();
        }
        return new CustList<String>();
    }
    public boolean match(MethodPointBlockKey _b) {
        return match(_b.key);
    }
    public boolean match(String _i) {
        return StringUtil.quickEq(key,_i);
    }

    public MemberCallingsBlock getId() {
        return id;
    }

    @Override
    public String keyStr() {
        return key;
    }

}
