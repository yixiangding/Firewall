public class Range {
    private String start;
    private String end;

    public Range(String range) {
        String[] twoEnds = range.split("-");
        start = twoEnds[0];
        end = twoEnds[1];
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
