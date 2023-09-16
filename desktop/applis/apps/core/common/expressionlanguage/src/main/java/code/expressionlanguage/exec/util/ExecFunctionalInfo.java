package code.expressionlanguage.exec.util;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecMethodKind;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ExecFunctionalInfo {
    private final ExecOverridableBlock overridableBlock;
    private final String fctParam;

    public ExecFunctionalInfo(MethodId _formatted, String _formattedRet, ExecOverridableBlock _overridableBlock, String _ir, String _aliasFct) {
        this.overridableBlock = _overridableBlock;
        fctParam = formatReturn(_formattedRet, _formatted, _ir, _aliasFct, _overridableBlock.getKind());
    }

    private static String formatReturn(String _returnType, MethodId _shortId, String _ir, String _aliasFct, ExecMethodKind _kind) {
        StringList paramsReturn_ = new StringList();
        IdentifiableUtil.appendLeftPart(0,paramsReturn_, _shortId);
        if (_kind == ExecMethodKind.SET_INDEX) {
            paramsReturn_.add(_ir);
            paramsReturn_.add(_ir);
        } else if (_shortId.isRetRef()) {
            paramsReturn_.add("~"+_returnType);
        } else {
            paramsReturn_.add(_returnType);
        }
        return StringUtil.concat(_aliasFct, StringExpUtil.TEMPLATE_BEGIN, StringUtil.join(paramsReturn_, StringExpUtil.TEMPLATE_SEP), StringExpUtil.TEMPLATE_END);
    }

    public ExecOverridableBlock getOverridableBlock() {
        return overridableBlock;
    }

    public String getFctParam() {
        return fctParam;
    }
}
