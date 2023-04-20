/**
 * Enum type to hold the type of finish that a paint will have
 *
 */
public enum PaintFinish {
    /**
     * Flat paint type
     */
    FLAT,
    /**
     * the look of an eggshell, has some reflection
     */
    EGGSHELL,
    /**
     * Satin look, more gloss/reflectivity
     */
    SATIN,
    /**
     * Semi gloss look
     */
    SEMI_GLOSS,
    /**
     * High gloss finish
     */
    GLOSS;

    /**
     * String version of the enum data type.
     * @return a String for the paint finish type.
     */
    @Override
    public String toString() {
        return switch(this.ordinal()) {
            case 1 -> "Eggshell";
            case 2 -> "Satin";
            case 3 -> "Semi-Gloss";
            case 4 -> "Gloss";
            default -> "Flat";
        };

    }
}
