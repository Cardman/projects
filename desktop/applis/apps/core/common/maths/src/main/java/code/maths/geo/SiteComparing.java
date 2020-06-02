package code.maths.geo;

import code.util.ints.Comparing;

public final class SiteComparing implements Comparing<Site> {
    @Override
    public int compare(Site _one, Site _two) {
        return SiteInfo.compare(_one.getInfo(),_two.getInfo());
    }
}
