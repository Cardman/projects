package code.vi.sys.impl;

import code.util.core.StringUtil;

import java.io.FileInputStream;
import java.io.InputStream;

public final class DefBinStreamIn extends AbstractBinStreamInImpl {
    public DefBinStreamIn(String _fileName) {
        super(tryCreateFileInputStream(_fileName));
    }

    public static InputStream tryCreateFileInputStream(String _file) {
        try {
            return new FileInputStream(StringUtil.nullToEmpty(_file));
        } catch (Exception e) {
            return null;
        }
    }
}
