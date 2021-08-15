package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.stds.*;
import code.util.*;

public final class NatAnalyzedCode {

    /**Only used while throwing exception*/

    private AnaFormattedRootBlock globalType = AnaFormattedRootBlock.defValue();

    private final StringMap<AnaLocalVariable> infosVars = new StringMap<AnaLocalVariable>();
    private final StringMap<AnaLoopVariable> loopsVars = new StringMap<AnaLoopVariable>();

    private LgNamesContent content;

    public static NatAnalyzedCode setInnerAnalyzing() {
        return new NatAnalyzedCode();
    }

    public StringMap<StandardType> getStandardsTypes() {
        return content.getStandards();
    }

    public String getAliasPrimBoolean() {
        return content.getPrimTypes().getAliasPrimBoolean();
    }

    public String getAliasObject() {
        return content.getCoreNames().getAliasObject();
    }

    public String getAliasPrimInteger() {
        return content.getPrimTypes().getAliasPrimInteger();
    }

    public void setStandards(LgNamesContent _content) {
        content = _content;
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
