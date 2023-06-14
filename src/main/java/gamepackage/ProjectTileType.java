package gamepackage;

@SuppressWarnings("SpellCheckingInspection")
public enum ProjectTileType {
    ABCDEF(10, 15, new int[] {1, 1, 1, 1, 1, 1}), AAABBB(8, 13, new int[] {3, 3}), AABBCC(7, 11, new int[] {2, 2, 2}),
    AAAABB(8, 14, new int[] {2, 4}), AAABBC(7, 11, new int[] {1, 2, 3}), AABBCD(5, 8, new int[] {1, 1, 2, 2});

    private final int singlePoints;
    private final int doublePoints;
    private final int[] counts;

    /**
     * Points for getting one condition
     */
    public int getSinglePoints() {
        return singlePoints;
    }

    /**
     * Points for getting two conditions
     */
    public int getDoublePoints() {
        return doublePoints;
    }

    /**
     * Check if counts match the condition
     *
     * @param counts sorted counts of each of the type of the tiles
     * @return if counts match
     */
    public boolean compareCounts(int[] counts) {
        if (this.counts.length != counts.length)
            return false;
        for (int i = 0; i < counts.length; i++)
            if (this.counts[i] != counts[i])
                return false;
        return true;
    }

    ProjectTileType(int singlePoints, int doublePoints, int[] counts) {
        this.singlePoints = singlePoints;
        this.doublePoints = doublePoints;
        this.counts = counts;
    }
}
