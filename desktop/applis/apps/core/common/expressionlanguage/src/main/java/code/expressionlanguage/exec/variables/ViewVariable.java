package code.expressionlanguage.exec.variables;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.Cache;

public final class ViewVariable {
    private final String className;
    private final String name;
    private final AbstractWrapper wrapper;
    private final LoopVariable loop;
    private final int deep;

    public ViewVariable(String _n, AbstractWrapper _w, AbstractPageEl _page, ContextEl _ctx) {
        this.className = _w.getClassName(_ctx);
        this.name = _n;
        this.wrapper = _w;
        loop = _page.getVars().getVal(_n);
        deep = -1;
    }

    public ViewVariable(String _n, AbstractWrapper _w, int _deep, ContextEl _ctx, Cache _cache) {
        this.className = _w.getClassName(_ctx);
        this.name = _n;
        this.wrapper = _w;
        loop = _cache.getLoopVar(_n, _deep);
        deep = _deep;
    }

    public String getName() {
        return name;
    }

    public int getDeep() {
        return deep;
    }

    public String getClassName() {
        return className;
    }

    public AbstractWrapper getWrapper() {
        return wrapper;
    }

    public String getIndexClassName() {
        if (loop == null) {
            return "";
        }
        return loop.getIndexClassName();
    }

    public long getIndex() {
        if (loop == null) {
            return -1;
        }
        return loop.getIndex();
    }
}
