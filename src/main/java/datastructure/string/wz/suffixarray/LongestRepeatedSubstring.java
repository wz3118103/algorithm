package datastructure.string.wz.suffixarray;

public class LongestRepeatedSubstring {
    private LongestRepeatedSubstring() {
    }

    public static String lrs(String text) {
        SuffixArray sa = new SuffixArray(text);
        String lrs = "";
        for (int i = 1; i < sa.length(); i++) {
            int len = sa.lcp(i);
            if (lrs.length() < len) {
                lrs = sa.select(i).substring(0, len);
            }
        }
        return lrs;
    }

    public static void main(String[] args) {
        String text = "it was the best of times it was the worst of times\n" +
                "it was the age of wisdom it was the age of foolishness\n" +
                "it was the epoch of belief it was the epoch of incredulity\n" +
                "it was the season of light it was the season of darkness\n" +
                "it was the spring of hope it was the winter of despair";
        text = text.replaceAll("\\s+", " ");
        System.out.println(lrs(text));
    }
}
