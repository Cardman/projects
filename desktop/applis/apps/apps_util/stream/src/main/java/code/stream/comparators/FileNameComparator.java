package code.stream.comparators;
import java.io.File;

import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class FileNameComparator implements Comparing<File> {
    @Override
    public int compare(File _o1, File _o2) {
        return StringUtil.compareStrings(_o1.getName(),_o2.getName());
    }

}
