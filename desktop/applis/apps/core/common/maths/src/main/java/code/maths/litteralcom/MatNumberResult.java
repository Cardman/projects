package code.maths.litteralcom;

public final class MatNumberResult {
    public static final char DOT = '.';
    public static final char SEP_RATE = '/';
    private final StringBuilder nbInfo;
    private final int index;

    public MatNumberResult(StringBuilder _nbInfo, int _index) {
        this.nbInfo = _nbInfo;
        this.index = _index;
    }

    public static MatNumberResult build(int _from, String _string) {
        StringBuilder nbInfo_ = new StringBuilder();
        int len_ = _string.length();
        int i_ = _from;
        boolean stop_ = false;
        while (i_ < len_) {
            char cur_ = _string.charAt(i_);
            if (!MathExpUtil.isDigit(cur_)) {
                if (cur_ == DOT || cur_ == SEP_RATE) {
                    nbInfo_.append(cur_);
                    i_++;
                } else {
                    stop_ = true;
                }
                break;
            }
            nbInfo_.append(cur_);
            i_++;
        }
        if (i_ >= len_ || stop_) {
            return new MatNumberResult(nbInfo_,i_);
        }
        while (i_ < len_) {
            char cur_ = _string.charAt(i_);
            if (!MathExpUtil.isDigit(cur_)) {
                break;
            }
            nbInfo_.append(cur_);
            i_++;
        }
        return new MatNumberResult(nbInfo_,i_);
    }

    public int getIndex() {
        return index;
    }

    public StringBuilder getNbInfo() {
        return nbInfo;
    }
}
