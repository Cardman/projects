package code.stream.comparators;

import code.stream.AbstractFile;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class FileNameComparator implements Comparing<AbstractFile> {
    @Override
    public int compare(AbstractFile _o1, AbstractFile _o2) {
        return StringUtil.compareStrings(_o1.getName(),_o2.getName());
    }

}
