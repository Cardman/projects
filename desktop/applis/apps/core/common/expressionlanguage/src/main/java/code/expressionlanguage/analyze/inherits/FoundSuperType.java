package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class FoundSuperType {
    private RootBlock type;
    private String name;
    private int location;

    public RootBlock getType() {
        return type;
    }

    public void setType(RootBlock type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
