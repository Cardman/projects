package code.mock;

import code.gui.TextAnswerValue;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class MockTypingUtil {
    private MockTypingUtil() {
    }

    public static MockTyping<Integer> wrap(int[] _values) {
        Ints ints_ = new Ints(new CollCapacity(_values.length));
        for (int i: _values) {
            ints_.add(i);
        }
        return new MockTyping<Integer>(ints_);
    }

    public static MockTyping<String> wrap(String[] _values) {
        return new MockTyping<String>(new StringList(_values));
    }

    public static MockTyping<TextAnswerValue> wrap(TextAnswerValue[] _values) {
        CustList<TextAnswerValue> values_ = new CustList<TextAnswerValue>(new CollCapacity(_values.length));
        for (TextAnswerValue i: _values) {
            values_.add(i);
        }
        return new MockTyping<TextAnswerValue>(values_);
    }
}
