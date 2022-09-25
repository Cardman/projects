package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class RelativePathUtil {
    private RelativePathUtil() {
    }

    public static String relativize(FileBlock _currentFile, String _refFile) {
        return relativize(_currentFile.getRenderFileName(),_refFile);
    }

    private static String relativize(String _currentFile, String _refFile) {
        int diffFirst_ = -1;
        int countCommon_ = 0;
        boolean finished_ = true;
        int len_ = NumberUtil.min(_currentFile.length(),_refFile.length());
        for (int i =0; i < len_; i++) {
            char curChar_ = _currentFile.charAt(i);
            char relChar_ = _refFile.charAt(i);
            if (curChar_ != relChar_) {
                finished_ = false;
                break;
            }
            if(curChar_ == LinkageUtil.SEP_DIR) {
                diffFirst_ = i;
                countCommon_++;
            }
        }
        if (finished_) {
            return _refFile.substring(len_);
        }
        String relFile_ = _refFile.substring(diffFirst_ + 1);
        if (_currentFile.indexOf(LinkageUtil.SEP_DIR,diffFirst_+1) < 0) {
            return relFile_;
        }
        StringBuilder b_ = new StringBuilder();
        int count_ = StringUtil.indexesOfChar(_currentFile, LinkageUtil.SEP_DIR).size() - countCommon_;
        for (int i = 0; i < count_; i++) {
            b_.append(LinkageUtil.PARENT).append(LinkageUtil.SEP_DIR);
        }
        return b_.append(relFile_).toString();
    }
}
