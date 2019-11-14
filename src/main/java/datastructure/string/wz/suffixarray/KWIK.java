package datastructure.string.wz.suffixarray;

public class KWIK {

    private static final int CONTEXT = 15;

    private KWIK() {
    }

    public static void kwik(String text, String query) {
        // read in text
        text = text.replaceAll("\\s+", " ");
        int n = text.length();

        // build suffix array
        SuffixArray sa = new SuffixArray(text);

        for (int i = sa.rank(query); i < n; i++) {
            int from1 = sa.index(i);
            int to1   = Math.min(n, from1 + query.length());
            if (!query.equals(text.substring(from1, to1))) {
                break;
            }
            int from2 = Math.max(0, sa.index(i) - CONTEXT);
            int to2   = Math.min(n, sa.index(i) + CONTEXT + query.length());
            System.out.println(text.substring(from2, to2));
        }
    }

    public static void main(String[] args) {
        String text = "it was the best of times it was the worst of times, spring\n" +
                "it was the age of wisdom it was the age of foolishness\n" +
                "it was the epoch of belief it was the epoch of incredulity\n" +
                "it was the season of light it was the season of darkness\n" +
                "it was the spring of hope it was the winter of despair, spring";
        text = text.replaceAll("\\s+", " ");
        kwik(text, "spring");
    }
}
