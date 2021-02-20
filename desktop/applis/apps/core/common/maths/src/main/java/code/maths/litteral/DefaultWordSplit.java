package code.maths.litteral;

public final class DefaultWordSplit extends AbstractWordSplit {
    @Override
    protected boolean isWordChar(char _char) {
        return MathExpUtil.isWordChar(_char);
    }
}
