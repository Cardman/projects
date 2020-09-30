package code.expressionlanguage.stds;

import code.util.StringMap;

public final class LgNamesContent {

    private StringMap<StandardType> standards = new StringMap<StandardType>();

    private AliasCore coreNames = new AliasCore();

    private AliasCharSequence charSeq = new AliasCharSequence();
    private AliasReflection reflect = new AliasReflection();
    private AliasStackTraceElement stackElt = new AliasStackTraceElement();
    private AliasNumber nbAlias = new AliasNumber();
    private AliasMath mathRef = new AliasMath();
    private PrimitiveTypes primTypes = new PrimitiveTypes();
    private AliasPredefinedTypes predefTypes = new AliasPredefinedTypes();
    private DisplayedStrings displayedStrings = new DisplayedStrings();
    private String defaultPkg = "";

    public PrimitiveTypes getPrimTypes() {
        return primTypes;
    }

    public DisplayedStrings getDisplayedStrings() {
        return displayedStrings;
    }

    public AliasNumber getNbAlias() {
        return nbAlias;
    }

    public AliasCore getCoreNames() {
        return coreNames;
    }

    public AliasCharSequence getCharSeq() {
        return charSeq;
    }

    public AliasPredefinedTypes getPredefTypes() {
        return predefTypes;
    }

    public AliasMath getMathRef() {
        return mathRef;
    }

    public AliasReflection getReflect() {
        return reflect;
    }

    public AliasStackTraceElement getStackElt() {
        return stackElt;
    }

    public String getDefaultPkg() {
        return defaultPkg;
    }

    public void setDefaultPkg(String defaultPkg) {
        this.defaultPkg = defaultPkg;
    }

    public StringMap<StandardType> getStandards() {
        return standards;
    }
}
