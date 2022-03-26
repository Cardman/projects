package code.bean.nat.analyze.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecialNatClass;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.util.StringMap;

public final class NatAnalyzedCode {

    /**Only used while throwing exception*/

    private AnaFormattedRootBlock globalType = AnaFormattedRootBlock.defValue();

    private final StringMap<AnaLocalVariable> infosVars = new StringMap<AnaLocalVariable>();
    private final StringMap<AnaLoopVariable> loopsVars = new StringMap<AnaLoopVariable>();

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
        return globalType.getFormatted();
    }

    public void setGlobalType(AnaFormattedRootBlock _globalType) {
        globalType = _globalType;
    }

    public StringMap<AnaLocalVariable> getInfosVars() {
        return infosVars;
    }

    public StringMap<AnaLoopVariable> getLoopsVars() {
        return loopsVars;
    }

}
