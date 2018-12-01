package code.stream.comparators;
import java.io.File;

import code.util.ints.Comparing;

public final class FileNameComparator implements Comparing<File> {
    @Override
    public int compare(File _o1, File _o2) {
        return _o1.getName().compareTo(_o2.getName());
    }

}
