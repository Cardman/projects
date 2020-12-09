package code.util.core;

import code.util.ints.UniformingString;

public final class DefaultUniformingString implements UniformingString {
    @Override
    public String apply(String _input) {
        return StringUtil.removeChars(_input,'\r');
    }
}
