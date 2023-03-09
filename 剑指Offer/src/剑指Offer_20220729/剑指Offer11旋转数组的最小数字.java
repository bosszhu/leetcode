package 剑指Offer_20220729;

public class 剑指Offer11旋转数组的最小数字 {
	//也是暴力
//    public int minArray(int[] numbers) {
//    	for (int i = 0; i < numbers.length -1; i++) {
//    		if (numbers[i] < numbers[i+1]) {
//				return numbers[i+1];
//			}
//		}
//    	return numbers[0];
//    }
	
  public int minArray(int[] numbers) {
	  int low = 0,high = numbers.length -1;
	  while (low < high) {
		int poivt = low + (high - low)/2;
		if (numbers[poivt] < numbers[high]) {
			high = poivt;
		} else if (numbers[poivt] > numbers[high]) {
			low = poivt + 1;
		} else {
			high = high - 1;
		}
	}
	  return numbers[high];
  }
}
