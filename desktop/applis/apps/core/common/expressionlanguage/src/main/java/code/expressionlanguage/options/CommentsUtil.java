package code.expressionlanguage.options;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class CommentsUtil {
    private CommentsUtil() {
    }
    public static void checkAndUpdateComments(CustList<CommentDelimiters> _user,
                                              CustList<CommentDelimiters> _default) {
        if (!okChars(_user) || !okStart(_user)) {
            _user.clear();
            _user.addAllElts(_default);
            return;
        }
        for (CommentDelimiters c: _user) {
            StringList ends_ = c.getEnd();
            String first_ = ends_.first();
            if (first_.trim().isEmpty()) {
                first_ = " ";
            }
            boolean spaces_ = containsSpace(first_);
            if (spaces_) {
                ends_.clear();
                ends_.add("\r\n");
                ends_.add("\r");
                ends_.add("\n");
            }
        }
    }

    private static boolean okStart(CustList<CommentDelimiters> _user) {
        boolean ok_ = true;
        for (CommentDelimiters c: _user) {
            String beginFirst_ = c.getBegin();
            for (CommentDelimiters d: _user) {
                String beginSecond_ = d.getBegin();
                if (c != d && (beginFirst_.startsWith(beginSecond_) || beginSecond_.startsWith(beginFirst_))) {
                    ok_ = false;
                    break;
                }
            }
            if (!ok_) {
                break;
            }
        }
        return ok_;
    }

    private static boolean okChars(CustList<CommentDelimiters> _user) {
        boolean ok_ = true;
        for (CommentDelimiters c: _user) {
            String begin_ = c.getBegin();
            String tr_ = begin_.trim();
            if (tr_.isEmpty() || containsSpace(begin_) || tr_.charAt(0) != '\\') {
                ok_ = false;
                break;
            }
        }
        return ok_;
    }

    private static boolean containsSpace(String _begin) {
        boolean spaces_ = false;
        for (char d: _begin.toCharArray()) {
            if (StringUtil.isWhitespace(d)) {
                spaces_ = true;
                break;
            }
        }
        return spaces_;
    }
}
