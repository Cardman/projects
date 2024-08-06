package code.mock;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.util.core.StringUtil;

public final class MockNameValidating implements AbstractNameValidating {
    @Override
    public boolean okPath(String _input, char... _seps) {
        for (String p: StringUtil.splitChars(_input, _seps)) {
            if (!ok(p)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean ok(String _input) {
//        assert !(_input).contains("-");
        boolean contained_ = false;
        for (char c : _input.toCharArray()) {
            if (c < '0' && c != '.' && c != '$') {
                return false;
            }
            contained_ = true;
            //            } else if (c == '.') {
//                endedById_ = false;
//                containedPrint_ = true;
//            } else if (c == ' ') {
//                if (!containedPrint_) {
//                    return false;
//                }
//                endedById_ = false;
        }
//        for (char c : _input.toCharArray()) {
//            if (c >= '0' || c == '-') {
//                contained_ = true;
//                containedPrint_ = true;
//                endedById_ = true;
//            } else if (c == '.') {
//                endedById_ = false;
//                containedPrint_ = true;
//            } else if (c == ' ') {
//                if (!containedPrint_) {
//                    return false;
//                }
//                endedById_ = false;
//            } else {
//                return false;
//            }
//        }
        return contained_;
    }
}
