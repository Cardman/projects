package code.expressionlanguage.exec.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ExecFunctionalInfo {
    private final ExecOverridableBlock overridableBlock;
    private final String fctParam;

    public ExecFunctionalInfo(MethodId _formatted, String _formattedRet, ExecOverridableBlock _overridableBlock, ContextEl _conf) {
        this.overridableBlock = _overridableBlock;
        fctParam = formatReturn(_conf, _formattedRet, _formatted);
    }

    private static String formatReturn(ContextEl _an, String _returnType, MethodId _shortId) {
        LgNames stds_ = _an.getStandards();
        String fctBase_ = stds_.getContent().getReflect().getAliasFct();
        StringList paramsReturn_ = new StringList();
        IdentifiableUtil.appendLeftPart(0,paramsReturn_, _shortId);
        if (_shortId.isRetRef()) {
            paramsReturn_.add("~"+_returnType);
        } else {
            paramsReturn_.add(_returnType);
        }
        return StringUtil.concat(fctBase_, StringExpUtil.TEMPLATE_BEGIN, StringUtil.join(paramsReturn_, StringExpUtil.TEMPLATE_SEP), StringExpUtil.TEMPLATE_END);
    }

    public ExecOverridableBlock getOverridableBlock() {
        return overridableBlock;
    }

    public String getFctParam() {
        return fctParam;
    }
}
