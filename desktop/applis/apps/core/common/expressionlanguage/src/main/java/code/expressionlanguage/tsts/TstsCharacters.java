package code.expressionlanguage.tsts;

import code.expressionlanguage.common.StringDataLetterUtil;
import code.expressionlanguage.common.StringDataUtil;

public final class TstsCharacters {
    private int max = -1;
    private int maxType = -1;
    private int min = 100;
    private int minType = 100;
    private int maxLetter = -1;
    private int minLetter = -1;
    private int maxLetterDigit = -1;
    private int minLetterDigit = -1;
    public TstsCharacters() {
        for (int i = 0; i < 256*256;i++) {
            int dir_ = StringDataUtil.getDirectionality((char) i);
            int type_ = StringDataUtil.getType((char) i);
            max = Math.max(dir_,max);
            min = Math.min(dir_,min);
            maxType = Math.max(type_,maxType);
            minType = Math.min(type_,minType);
            if (StringDataLetterUtil.isLetter((char) i)) {
                if (minLetter == -1) {
                    minLetter = i;
                }
                maxLetter = i;
            }
            if (StringDataUtil.isLetterOrDigit((char) i)) {
                if (minLetterDigit == -1) {
                    minLetterDigit = i;
                }
                maxLetterDigit = i;
            }
        }
    }

    public int getMax() {
        return max;
    }

    public int getMaxLetter() {
        return maxLetter;
    }

    public int getMaxLetterDigit() {
        return maxLetterDigit;
    }

    public int getMaxType() {
        return maxType;
    }

    public int getMin() {
        return min;
    }

    public int getMinLetter() {
        return minLetter;
    }

    public int getMinLetterDigit() {
        return minLetterDigit;
    }

    public int getMinType() {
        return minType;
    }
}
