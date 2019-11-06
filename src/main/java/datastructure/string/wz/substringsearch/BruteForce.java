package datastructure.string.wz.substringsearch;

public class BruteForce implements SubStringSearch{
    private String pattern;

    public BruteForce(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public int search(String text) {
        // i最大可以取到最后一组紧挨着text的最末尾，因此此时i的位置为：text.length() - 1 - (pattern.length() - 1)
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            int j = 0;
            for (; j < pattern.length(); j++) {
                // i + j最大必须取到text.length() - 1
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == pattern.length()) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SubStringSearch sss = new BruteForce("ABRA");
        System.out.println(sss.search("ABACADABRAC"));
    }
}
