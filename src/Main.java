import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Xóa nguyên âm ('Cybersoft'): " + xoaNguyenAm("Cybersoft"));
        System.out.println("Số lớn thứ 2: " + timSoLonThuHai(new int[]{1, 3, 4, 5, 0, 2}));
        System.out.println("Tổng số chẵn (n=10): " + tinhTongSoChan(10));
        System.out.println("Đếm số từ: " + demSoTu("Xin chao cac bạn"));
        System.out.println("Chữ cái đầu: " + layChuCaiDau("Xin chao cac bạn"));
        System.out.println("Thuế TNCN (Thu nhập 12): " + tinhThueTNCN(12));
        System.out.println("Kiểm tra đối xứng ('madam'): " + kiemTraDoiXung("madam"));
        System.out.println("Xóa ký tự lặp ('programming'): " + xoaKyTuLap("programming"));

        int[] twoSumRes = twoSumII(new int[]{2, 7, 11, 15}, 9);
        System.out.println("Two Sum II indices: [" + twoSumRes[0] + ", " + twoSumRes[1] + "]");
        System.out.println("Longest Palindrome ('babad'): " + longestPalindrome("babad"));
        System.out.println("Minimum Window Substring: " + minWindow("ADOBECODEBANC", "ABC"));
    }
    // PHAN 1: BAI 1

    public static String xoaNguyenAm(String str) {
        if (str == null) return null;
        return str.replaceAll("[aeiouAEIOU]", "");
    }
// PHAN 1 BAI 2
    public static int timSoLonThuHai(int[] arr) {
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE;
        }
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2 && num != max1) {
                max2 = num;
            }
        }
        return (max2 == Integer.MIN_VALUE) ? Integer.MIN_VALUE : max2;
    }
// PHAN 1 BAI 3
    public static int tinhTongSoChan(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i += 2) {
            sum += i;
        }
        return sum;
    }
// PHAN 1 BAI 4
    public static int demSoTu(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        String[] words = str.trim().split("\\s+");
        return words.length;
    }
// PHAN 1 BAI 5
    public static String layChuCaiDau(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }
        String[] words = str.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0))).append(" ");
            }
        }
        return result.toString().trim();
    }

 //PHAN 2 BAI 1

    public static double tinhThueTNCN(double thuNhapNam) {
        double thue = 0;
        if (thuNhapNam <= 5) {
            thue = thuNhapNam * 0.05;
        } else if (thuNhapNam <= 10) {
            thue = 5 * 0.05 + (thuNhapNam - 5) * 0.10;
        } else if (thuNhapNam <= 18) {
            thue = 5 * 0.05 + 5 * 0.10 + (thuNhapNam - 10) * 0.15;
        } else if (thuNhapNam <= 32) {
            thue = 5 * 0.05 + 5 * 0.10 + 8 * 0.15 + (thuNhapNam - 18) * 0.20;
        } else if (thuNhapNam <= 52) {
            thue = 5 * 0.05 + 5 * 0.10 + 8 * 0.15 + 14 * 0.20 + (thuNhapNam - 32) * 0.25;
        } else if (thuNhapNam <= 80) {
            thue = 5 * 0.05 + 5 * 0.10 + 8 * 0.15 + 14 * 0.20 + 20 * 0.25 + (thuNhapNam - 52) * 0.30;
        } else {
            thue = 5 * 0.05 + 5 * 0.10 + 8 * 0.15 + 14 * 0.20 + 20 * 0.25 + 28 * 0.30 + (thuNhapNam - 80) * 0.35;
        }
        return thue;
    }
    // PHAN 2 BAI 2

    public static boolean kiemTraDoiXung(String str) {
        if (str == null) return false;
        String cleanStr = str.replaceAll("\\s+", "").toLowerCase();
        String revStr = new StringBuilder(cleanStr).reverse().toString();
        return cleanStr.equals(revStr);
    }
// PHAN 2 BAI 3
    public static String xoaKyTuLap(String str) {
        if (str == null) return null;
        StringBuilder sb = new StringBuilder();
        boolean[] seen = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!seen[c]) {
                seen[c] = true;
                sb.append(c);
            }
        }
        return sb.toString();
    }

   //PHAN 3 BAI 1: Sum II - Input Array Is Sorted
    public static int[] twoSumII(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] { left + 1, right + 1 };
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

   //PHAN 3 BAI 2
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    // PHAN 3 BAI 3

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // Bài 11: Minimum Window Substring
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        HashMap<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int requiredTypeCount = targetMap.size();
        int formed = 0;
        HashMap<Character, Integer> windowMap = new HashMap<>();
        int[] ans = {-1, 0, 0};

        int l = 0, r = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            if (targetMap.containsKey(c) && windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                formed++;
            }

            while (l <= r && formed == requiredTypeCount) {
                c = s.charAt(l);
                if (ans[0] == -1 || (r - l + 1) < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                windowMap.put(c, windowMap.get(c) - 1);
                if (targetMap.containsKey(c) && windowMap.get(c).intValue() < targetMap.get(c).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}