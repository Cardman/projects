package code.expressionlanguage.common;

import code.util.core.NumberUtil;

public final class FileMetrics {

    private final FileMetricsCore metricsCore;

    private final int tabWidth;

    public FileMetrics(FileMetricsCore _metCore, int _width) {
        metricsCore = _metCore;
        this.tabWidth = _width;
    }

    public int getRowFile(int _sum) {
        int len_ = metricsCore.getLineReturns().size();
        int i_ = 0;
        int s_ = NumberUtil.max(0,_sum);
        while (i_ < len_) {
            if (s_ <= metricsCore.getLineReturns().get(i_)) {
                break;
            }
            i_++;
        }
        return i_;
    }
    public int getColFile(int _sum, int _row) {
        int j_ = 0;
        int s_ = NumberUtil.max(0,_sum);
        int begin_ = metricsCore.getLineReturns().get(_row - 1)+1;
        for (int j = begin_; j < s_; j++) {
            if (metricsCore.getTabulations().containsObj(j)) {
                j_ += tabWidth;
                j_ -= j_ % tabWidth;
            } else {
                j_++;
            }
        }
        return j_+1;
    }
}
