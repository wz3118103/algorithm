package algorithm.dynamicprogramming.cracking.ch08_recursion_and_dynamic_programming.Q8_13_Stack_of_Boxes;

import java.util.Comparator;

public class BoxComparator implements Comparator<Box> {
	@Override
	public int compare(Box x, Box y){
		return y.height - x.height;
	}
}
