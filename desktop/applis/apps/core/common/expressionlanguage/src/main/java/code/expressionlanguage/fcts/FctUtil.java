package code.expressionlanguage.fcts;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.core.StringUtil;

public final class FctUtil {
    private FctUtil() {
    }

    public static ErrorStruct getBadIndex(ContextEl _context, String _message, StackCall _stackCall) {
        return new ErrorStruct(_context, _message, _context.getStandards().getContent().getCoreNames().getAliasBadIndex(), _stackCall);
    }

    public static String getEndMessage(int _end, String _s, int _length) {
        return StringUtil.concat(Long.toString(_end), _s, Long.toString(_length));
    }

    public static String getBeginMessage(int _begin) {
        return StringUtil.concat(Long.toString(_begin), "<0");
    }

    public static String getBeginEndMessage(int _len, long _one, int _two) {
        return StringUtil.concat(Long.toString(_one + _two), ">", Long.toString(_len));
    }

    public static ErrorStruct getFormatError(ContextEl _context, String _text, StackCall _stackCall) {
        return new ErrorStruct(_context, _text, _context.getStandards().getContent().getCoreNames().getAliasNbFormat(), _stackCall);
    }

    public static String getNumberRadixMessage(String _nb, int _radix) {
        return StringUtil.concat(_nb, ",", Long.toString(_radix));
    }
}
