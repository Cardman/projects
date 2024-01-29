package cards.gui.panels;

import code.gui.AbsPanel;
import code.gui.images.MetaDimension;

/**
    */

public class Carpet {

    private CarpetBelote tapisBelote;

    private CarpetPresident tapisPresident;

    private CarpetTarot tapisTarot;

    public static void choose(AbsPanel _sub1, AbsPanel _sub2, AbsPanel _sub3, int _i, AbsPanel _surPanneau) {
        if (_i < 4) {
            _sub1.add(_surPanneau);
        } else if (_i < 8) {
            _sub2.add(_surPanneau);
        } else {
            _sub3.add(_surPanneau);
        }
    }

    public static int keyFour(boolean _horaire, int _i) {
        if (_i % 2 != 1) {
            if (_i == 4) {
                return -1;
            }
            return -2;
        }
        if (_i == 1) {
            return 2;
        } else if (_i == 3) {
            if (_horaire) {
                return 1;
            }
            return 3;
        } else if (_i == 5) {
            if (_horaire) {
                return 3;
            }
            return 1;
        }
        return 0;
    }

    public static int keySix(boolean _horaire, int _i) {
        if (_i == 1) {
            if (_horaire) {
                return 2;
            }
            return 4;
        }
        if (_i == 2) {
            return 3;
        }
        if (_i == 6) {
            if (_horaire) {
                return 4;
            }
            return 2;
        }
        if (_i == 4) {
            if (_horaire) {
                return 1;
            }
            return 5;
        }
        if (_i == 5){
            return -1;
        }
        if (_i == 10) {
            if (_horaire) {
                return 5;
            }
            return 1;
        }
        if (_i == 9) {
            return 0;
        }
        return -2;
    }

    public static int keyThree(boolean _horaire, int _i) {
        if (_i == 0) {
            if (_horaire) {
                return 1;
            }
            return 2;
        }
        if (_i == 2) {
            if (_horaire) {
                return 2;
            }
            return 1;
        }
        if (_i == 7) {
            return 0;
        }
        if (_i == 4) {
            return -1;
        }
        return -2;
    }

    public static int keyFive(boolean _horaire, int _i) {
        if (_i == 0) {
            if (_horaire) {
                return 2;
            }
            return 3;
        }
        if (_i == 2) {
            if (_horaire) {
                return 3;
            }
            return 2;
        }
        if (_i == 3) {
            if (_horaire) {
                return 1;
            }
            return 4;
        }
        if (_i == 5) {
            if (_horaire) {
                return 4;
            }
            return 1;
        }
        if (_i == 7) {
            return 0;
        }
        if (_i == 4) {
            return -1;
        }
        return -2;
    }

    public void setTapisTarot(CarpetTarot _tapisTarot) {
        supprimerTapis();
        tapisTarot = _tapisTarot;
    }

    public void setTapisPresident(CarpetPresident _tapisPresident) {
        supprimerTapis();
        tapisPresident = _tapisPresident;
    }

    public void setTapisBelote(CarpetBelote _tapisBelote) {
        supprimerTapis();
        tapisBelote = _tapisBelote;
    }

    private void supprimerTapis() {
        tapisBelote = null;
        tapisPresident = null;
        tapisTarot = null;
    }
    public static MetaDimension getMaxDimension() {
        return getDimension(false);
    }
    public static MetaDimension getMinDimension() {
        return getDimension(true);
    }
    public static MetaDimension getDimension(boolean _small) {
        if (_small) {
            return new MetaDimension(25,150);
        }
        return new MetaDimension(100,150);
    }
    public static MetaDimension getDimensionForSeveralCards(int _number) {
        return new MetaDimension(100 + 25 * (_number - 1), 150);
    }
    public CarpetTarot getTapisTarot() {
        return tapisTarot;
    }

    public CarpetPresident getTapisPresident() {
        return tapisPresident;
    }

    public CarpetBelote getTapisBelote() {
        return tapisBelote;
    }
}
