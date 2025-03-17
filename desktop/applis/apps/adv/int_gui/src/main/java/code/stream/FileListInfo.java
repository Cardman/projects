package code.stream;

import code.gui.GuiConstants;

public final class FileListInfo {
    private final AbstractFile[] names;
    private final boolean nul;

    public FileListInfo(AbstractFile[] _names) {
        this.nul = _names == null;
        if (nul) {
            this.names = new AbstractFile[0];
        } else {
            this.names = _names;
        }
    }

    public static BytesInfo extractWithPrefixes(byte[] _file, byte[] _pref) {
        if (startsWith(_file,_pref)) {
            int diff_ = _file.length - _pref.length;
            byte[] mid_ = new byte[diff_];
            for (int i = 0; i < diff_; i++) {
                mid(_file, _pref, mid_, i);
            }
            return new BytesInfo(mid_, false);
//            return new BytesInfo(new Bytes(Bytes.newList(_file).mid(_pref.length)).toArrByte(),false);
        }
        return new BytesInfo(new byte[0],true);
    }

    private static void mid(byte[] _file, byte[] _pref, byte[] _mid, int _i) {
        _mid[_i] = _file[_pref.length+ _i];
    }

    public static boolean startsWith(byte[] _file, byte[] _pref) {
        int len_ = _pref.length;
        if (_file.length < len_) {
            return false;
        }
        for (int i = 0; i <len_;i++) {
            if (_file[i] != _pref[i]) {
                return false;
            }
        }
        return true;
    }
    public static boolean isZip(byte[] _bytes) {
        return _bytes != null && _bytes.length > 3
                && _bytes[0] == (byte)0x50&& _bytes[1] == (byte)0x4b
                && _bytes[2] == (byte)0x03&& _bytes[3] == (byte)0x04;
    }

    public static boolean isWav(byte[] _bytes) {
        if (_bytes.length < 12) {
            return false;
        }
        return _bytes[0] == GuiConstants.VK_R && _bytes[1] == GuiConstants.VK_I && _bytes[2] == GuiConstants.VK_F && _bytes[3] == GuiConstants.VK_F
                &&_bytes[8] == GuiConstants.VK_W && _bytes[9] == GuiConstants.VK_A && _bytes[10] == GuiConstants.VK_V && _bytes[11] == GuiConstants.VK_E;
    }
    public static boolean isMp3(byte[] _bytes) {
        if (isMpFirst3(_bytes)) {
            return true;
        }
        return _bytes.length >= 3 && _bytes[0] == GuiConstants.VK_I && _bytes[1] == GuiConstants.VK_D && _bytes[2] == '3';
    }
    public static boolean isMpFirst3(byte[] _bytes) {
        if (_bytes.length < 2) {
            return false;
        }
        if (_bytes[0] == (byte)255 && _bytes[1] == (byte)251) {
            return true;
        }
        if (_bytes[0] == (byte)255 && _bytes[1] == (byte)243) {
            return true;
        }
        return _bytes[0] == (byte) 255 && _bytes[1] == (byte) 242;
    }

    public static boolean isBinary(BytesInfo _bytes) {
        if (_bytes.isNul()) {
            return false;
        }
        for (byte b: _bytes.getBytes()) {
            if (b < ' ' && b != '\n' && b != '\t' && b != '\r') {
                return true;
            }
        }
        return false;
    }

    public AbstractFile[] getNames() {
        return names;
    }

    public boolean isNul() {
        return nul;
    }
}
