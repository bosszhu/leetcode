package 剑指Offer_20220904;

public class 剑指Offer39数组中出现次数超过一半的数字 {
	/*
	 * 摩尔投票法
	 */
    public int majorityElement(int[] nums) {
    	//初始为0,投票数为0
    	int x = 0,votes = 0;
    	//遍历数组
    	for (int i : nums) {
			if (votes == 0) {//投票数归0后,赋值给新的遍历值
				x = i;
			}
			if (x == i) {//如果继续遍历时还找到当前x,投票数++
				votes += 1;
			} else {//否则投票数--
				votes -= 1;
			}
		}
    	
    	//验证是否存在众数
    	int count = 0;
    	for (int i : nums) {
			if (i == x) {
				count++;
			}
		}
    	return count > nums.length/2 ? x: 0;
    }
}
