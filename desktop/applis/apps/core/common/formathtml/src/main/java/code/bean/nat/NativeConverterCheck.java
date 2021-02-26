package code.bean.nat;

import code.formathtml.analyze.AbstractConverterCheck;

public final class NativeConverterCheck implements AbstractConverterCheck {
    private final String aliasObject;

    public NativeConverterCheck(String _aliasObject) {
        this.aliasObject = _aliasObject;
    }

    @Override
    public boolean isConveritble(String _className) {
        return true;
    }

    @Override
    public String convertType(String _className) {
        return NativeForEachFetch.unNulizz(_className,aliasObject);
    }
}
