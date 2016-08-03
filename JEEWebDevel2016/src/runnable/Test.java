package runnable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		
		/*
		 * test class change
		 */

		List<String> list = Arrays.asList("warsaw", "london", "paris", "madrid");
		
		/*
		list.sort(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
			
		});
		*/
		
		Collections.sort(list, (String o1, String o2) -> o1.compareTo(o2));
		
		
		list.forEach(System.out::println);
		
		
	}

}
