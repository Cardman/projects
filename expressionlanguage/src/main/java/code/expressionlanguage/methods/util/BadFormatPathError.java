package code.expressionlanguage.methods.util;

import code.util.StringList;

public final class BadFormatPathError extends FoundErrorInterpret {

    private static final String BAD_FORMAT_PATH= "bad format path";

    private String path;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,BAD_FORMAT_PATH,SEP_KEY_VAL,path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String _path) {
        path = _path;
    }
}
