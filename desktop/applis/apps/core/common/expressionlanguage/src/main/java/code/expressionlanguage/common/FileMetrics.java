package code.expressionlanguage.common;

import code.util.Ints;

public final class FileMetrics {

    private Ints lineReturns;
    private Ints tabulations;

    private int tabWidth;

    public FileMetrics(Ints lineReturns, Ints tabulations, int tabWidth) {
        this.lineReturns = lineReturns;
        this.tabulations = tabulations;
        this.tabWidth = tabWidth;
    }

    public int getRowFile(int _sum) {
        int len_ = lineReturns.size();
        int i_ = 0;
        int s_ = Math.max(0,_sum);
        while (i_ < len_) {
            if (s_ <= lineReturns.get(i_)) {
                break;
            }
            i_++;
        }
        return i_;
    }
    public int getColFile(int _sum, int _row) {
        int j_ = 0;
        int s_ = Math.max(0,_sum);
        int begin_ = lineReturns.get(_row - 1)+1;
        for (int j = begin_; j < s_; j++) {
            if (tabulations.containsObj(j)) {
                j_ += tabWidth;
                j_ -= j_ % tabWidth;
            } else {
                j_++;
            }
        }
        return j_+1;
    }
}
