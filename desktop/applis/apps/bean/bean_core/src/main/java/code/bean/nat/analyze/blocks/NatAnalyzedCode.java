package code.bean.nat.analyze.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecialNatClass;
import code.util.StringMap;

public final class NatAnalyzedCode {

    /**Only used while throwing exception*/

    private String globalType = "";

    private final StringMap<String> infosVars = new StringMap<String>();
    private final StringMap<String> loopsVars = new StringMap<String>();

    private BeanNatCommonLgNames stds;

    public static NatAnalyzedCode setInnerAnalyzing() {
        return new NatAnalyzedCode();
    }

    public StringMap<SpecialNatClass> getStds() {
        return stds.getStds();
    }

    public void setStds(BeanNatCommonLgNames _stds) {
        stds = _stds;
    }

    public String getGlobalClass() {
        return globalType;
    }

    public void setGlobalType(String _globalType) {
        globalType = _globalType;
    }

    public StringMap<String> getInfosVars() {
        return infosVars;
    }

    public StringMap<String> getLoopsVars() {
        return loopsVars;
    }

}
