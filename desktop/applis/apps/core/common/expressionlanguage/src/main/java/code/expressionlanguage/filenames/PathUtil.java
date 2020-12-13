package code.expressionlanguage.filenames;

import code.util.StringList;
import code.util.core.StringUtil;

public final class PathUtil {
    private PathUtil() {
    }
    public static String transform(String _current, String _relative) {
        String file_ = StringUtil.replaceBackSlash(_relative);
        boolean folder_ = false;
        if (file_.endsWith("/")) {
            file_ = file_.substring(0,file_.length()-1);
            folder_ = true;
        }
        String current_ = _current;
        while (file_.startsWith("../")) {
            int before_ = current_.lastIndexOf('/', current_.length() - 2);
            if (before_ < 0) {
                break;
            }
            file_ = file_.substring(3);
            current_ = current_.substring(0,before_+1);
        }
        if (StringUtil.quickEq(file_,"..")) {
            StringList parts_ = splitParts(current_);
            parts_.removeLast();
            int nbElements_ = parts_.size() - 1;
            if (nbElements_ <= 0) {
                return "/";
            }
            String join_ = StringUtil.join(parts_.left(nbElements_), '/');
            return StringUtil.replaceBackSlashDot(join_);
        }
        if (file_.startsWith("../")) {
            return "/";
        }
        if (!folder_) {
            return current_+StringUtil.replaceBackSlash(file_);
        }
        return current_+StringUtil.replaceBackSlashDot(file_);
    }

    public static StringList splitParts(String _file) {
        return StringUtil.splitChars(_file, '/','\\');
    }
}
