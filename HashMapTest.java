package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 面试题：HashMap排序
 * 已知一个HashMap<Integer,User>集合，User有name(String)和age(int)属性。
 * 请写一个方法实现对HashMap排序的功能，该方法接收HashMap<Integer,User>为形参，
 * 返回类型为HashMap<Integer,User>,要求对HashMap中的User的age倒序进行排序。
 * 排序时key=value键值不得拆散。
 * @author Administrator
 *
 */

public class HashMapTest {
	public static void main(String[] args) {
		
		HashMap<Integer, User> users = new HashMap<>();
		users.put(1,new User("张三", 22));
		users.put(2,new User("李四", 23));
		users.put(3,new User("王五", 24));
		users.put(4,new User("赵六", 25));
		System.out.println("排序前：");
		System.out.println(users);
		
		//8.创建新的HashMap用于接收排序后的方法返回值
		HashMap<Integer, User> result = hashMapSort(users);
		System.out.println("排序后：");		
		System.out.println(result);
	}
	
	//排序方法
	public static HashMap<Integer, User> hashMapSort(HashMap<Integer,User> map) {
		//1.将map集合转为Set集合
		Set<Entry<Integer,User>> set = map.entrySet();
		
		//2.将set转为List集合，借此使用Collections工具类的排序方法sort()
		List<Entry<Integer,User>> list = new ArrayList<>(set);
		
		//3.用lambda表达式编写Comparator获取对象的排序规则(按照age倒序排序)
		Comparator<Entry<Integer,User>> com = (o1,o2)->
		o2.getValue().getAge()-o1.getValue().getAge();
		
		//4.使用Collections工具的sort方法对list进行排序
		Collections.sort(list,com);
		
		//5.创建一个新的有序的HashMap子类集合
		LinkedHashMap<Integer,User> linked = new LinkedHashMap<>();
		
		//6.利用foreach循环将存储在list中有序排列后的数据放入LinkedHashMap中
		for (Entry<Integer, User> entry : list) {
			linked.put(entry.getKey(), entry.getValue());
		}
		
		//7.返回结果
		return linked;
		
	}
}


