package enums;

import java.util.Locale;

public enum UserNetworkId {
    IndoLeads(21),
    Involve_Asia(321),
    CueLinks(1010),
    Impact(1001),
    AccessTrade(1),
    FlexOffers(1006),
    Commission_Junction(1002),
    Ebay(1000);

    private final int idUser;

    UserNetworkId(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public static int getNetworkIdByName(String name) {
        name = name.replace("-", "_");
        for (UserNetworkId id : UserNetworkId.values()) {
            if (id.name().equalsIgnoreCase(name.toLowerCase(Locale.ROOT))) {
                return id.getIdUser();
            }
        }
        throw new IllegalArgumentException(
                String.format("Network id with name %s is invalid", name));
    }
}
