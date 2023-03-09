package 剑指Offer_20220808;


public class 剑指Offer58_I翻转单词顺序 {
//	输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
    public String reverseWords(String s) {
    	s = s.trim(); // 删除首尾空格
    	int lastIndex_word = s.length() - 1,firstIndex_word = lastIndex_word;//双指针指向当前整个字符串尾部
        StringBuilder res = new StringBuilder();
        while (firstIndex_word >= 0) {
        	while (firstIndex_word >= 0 && s.charAt(firstIndex_word) != ' ') {//尾部不等于空格
				firstIndex_word--;
			}
        	res.append(s.substring(firstIndex_word+1, lastIndex_word+1) + ' ');
        	while (firstIndex_word >= 0 && s.charAt(firstIndex_word) == ' ') {
				firstIndex_word--;
			}
        	lastIndex_word = firstIndex_word;
		}
    	return res.toString().trim();
    }
}
