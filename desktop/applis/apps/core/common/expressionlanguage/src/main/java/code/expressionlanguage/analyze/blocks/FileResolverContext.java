package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnaBlockCounts;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.TokenCheckerContext;
import code.expressionlanguage.analyze.files.DefaultAccess;
import code.expressionlanguage.analyze.files.FileAliases;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.KeyWords;

public final class FileResolverContext {
    private final AnaBlockCounts counts;
    private final TokenCheckerContext tok;
    private final FileAliases fileAliases;
    private final MethodAccessKind stat;
    private final DefaultAccess def;
    private final boolean dynamic;

    public FileResolverContext(AnalyzedPageEl _page) {
        this(_page.getCountElts(),_page.getKeyWords(),_page.getStaticContext(), _page.getDefaultAccess(),_page.fileAliases(), _page.isDynamic());
    }
    public FileResolverContext(AnaBlockCounts _c,KeyWords _k, MethodAccessKind _s, DefaultAccess _defaultAccess, FileAliases _fa, boolean _dyn) {
        counts = _c;
        tok = new TokenCheckerContext(_k,_fa.getPrimTypes(),_fa.getAliasVoid());
        this.stat = _s;
        fileAliases = _fa;
        def = _defaultAccess;
        dynamic = _dyn;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public AnaBlockCounts getCounts() {
        return counts;
    }

    public FileAliases getFileAliases() {
        return fileAliases;
    }

    public TokenCheckerContext getTok() {
        return tok;
    }

    public KeyWords getKeys() {
        return getTok().getKeys();
    }

    public MethodAccessKind getStat() {
        return stat;
    }

    public DefaultAccess getDef() {
        return def;
    }

}
