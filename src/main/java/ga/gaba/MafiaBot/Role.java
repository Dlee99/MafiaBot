package ga.gaba.MafiaBot;

/**
 * Created by glyczak on 10/14/17.
 */

public enum Role {
    VILLAGER,
    MAFIA,
    DOCTOR,
    COP,
    DRUNK;

    public String toString() {
        switch (this) {
            case VILLAGER:
                return "Villager";
            case MAFIA:
                return "Mafia";
            case DOCTOR:
                return "Doctor";
            case COP:
                return "Cop";
            case DRUNK:
                return "Drunk";
        }
        return null;
    }
}
