package code.sys.impl;

import code.stream.AbstractListRoot;

import java.io.File;

public final class DefListRoot implements AbstractListRoot {
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
