package code.stream.comparators;
import java.io.File;
import code.util.ints.Comparing;

import code.util.consts.ConstFiles;

public final class FileNameComparator implements Comparing<File> {

    private static final boolean SENSITIVE = ConstFiles.filesAreCaseSensitive();

    @Override
    public int compare(File _o1, File _o2) {
        if (!SENSITIVE) {
            return String.CASE_INSENSITIVE_ORDER.compare(_o1.getName(), _o2.getName());
        }
        return _o1.getName().compareTo(_o2.getName());
    }

}
