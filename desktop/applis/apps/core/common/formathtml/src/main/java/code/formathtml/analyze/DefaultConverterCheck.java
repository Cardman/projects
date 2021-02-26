package code.formathtml.analyze;

import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.stds.PrimitiveType;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DefaultConverterCheck implements AbstractConverterCheck {
    private final StringMap<PrimitiveType> primitiveTypes;
    private final String aliasString;

    public DefaultConverterCheck(StringMap<PrimitiveType> _primitiveTypes, String _aliasString) {
        this.primitiveTypes = _primitiveTypes;
        this.aliasString = _aliasString;
    }

    @Override
    public boolean isConveritble(String _className) {
        if (StringUtil.quickEq(_className, aliasString)) {
            return true;
        }

        return AnaTypeUtil.isPrimitiveOrWrapper(_className, primitiveTypes);
    }

    @Override
    public String convertType(String _className) {
        return _className;
    }
}
