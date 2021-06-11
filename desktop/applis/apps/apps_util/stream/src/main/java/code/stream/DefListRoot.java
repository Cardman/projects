package code.stream;

import java.io.File;

public class DefListRoot implements AbstractListRoot {
    private final File[] roots = File.listRoots();
    @Override
    public int length() {
        return roots.length;
    }

    @Override
    public String path(int _index) {
        return roots[_index].getAbsolutePath();
    }
}
