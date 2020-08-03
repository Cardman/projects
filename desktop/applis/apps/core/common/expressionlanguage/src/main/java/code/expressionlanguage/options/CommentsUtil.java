package code.expressionlanguage.options;

import code.expressionlanguage.files.CommentDelimiters;
import code.util.CustList;
import code.util.StringList;

public final class CommentsUtil {
    private CommentsUtil() {
    }
    public static void checkAndUpdateComments(CustList<CommentDelimiters> _user,
                                              CustList<CommentDelimiters> _default) {
        boolean ok_ = true;
        for (CommentDelimiters c: _user) {
            String begin_ = c.getBegin();
            String tr_ = begin_.trim();
            if (tr_.isEmpty()) {
                ok_ = false;
                break;
            }
            boolean spaces_ = containsSpace(begin_);
            if (spaces_) {
                ok_ = false;
                break;
            }
            if (tr_.charAt(0) != '\\') {
                ok_ = false;
                break;
            }
        }
        if (!ok_) {
            _user.clear();
            _user.addAllElts(_default);
            return;
        }
        for (CommentDelimiters c: _user) {
            String beginFirst_ = c.getBegin();
            for (CommentDelimiters d: _user) {
                String beginSecond_ = d.getBegin();
                if (c == d) {
                    //same comment
                    continue;
                }
                if (beginFirst_.startsWith(beginSecond_)) {
                    ok_ = false;
                    break;
                }
                if (beginSecond_.startsWith(beginFirst_)) {
                    ok_ = false;
                    break;
                }
            }
            if (!ok_) {
                break;
            }
        }
        if (!ok_) {
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

    private static boolean containsSpace(String _begin) {
        boolean spaces_ = false;
        for (char d: _begin.toCharArray()) {
            if (Character.isWhitespace(d)) {
                spaces_ = true;
                break;
            }
        }
        return spaces_;
    }
}
