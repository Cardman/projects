package code.expressionlanguage.adv;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.variables.ViewInstance;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.Struct;

public final class DbgCallStruct extends DbgAbsNodeStruct {

    private final String className;
    private final Struct eval;
    public DbgCallStruct(ResultContext _r, ViewInstance _page) {
        super(_r);
        className = _page.getClassName();
        eval = _page.getEval();
    }
    public String id() {
        return StringExpUtil.getIdFromAllTypes(className);
    }

    @Override
    public Struct value() {
        return eval;
    }

    @Override
    public String str() {
        return className;
    }

}
