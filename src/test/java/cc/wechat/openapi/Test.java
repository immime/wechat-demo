package cc.wechat.openapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import cc.wechat.data.domain.ContextMenu;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		testStringTokenizer();
		
//		testTreeMap();
		
		Resource resource = new ClassPathResource("config/menu.json"); 
		ContextMenu menu = null;
		try {
			InputStream inJson = resource.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			menu = mapper.readValue(inJson, ContextMenu.class);
			Set<ContextMenu> menus = new HashSet<ContextMenu>();
			recursionAdd(menu, menus);
			
			NavigableMap<String, ContextMenu> map = new TreeMap<String, ContextMenu>();
			for (ContextMenu m : menus) {
				map.put(m.getCode(), m);
			}

			System.err.println(map.keySet());
			
			NavigableSet<String> navigableKeySet = map.navigableKeySet();
			String key = "0_1_2_";
			System.err.println("key:" + key);
			String first = navigableKeySet.first();
			System.err.println("navigableKeySet.first():" + first);
			String floor = navigableKeySet.floor(key);
			System.err.println("navigableKeySet.floor():" + floor);
			String higher = navigableKeySet.higher(key);
			System.err.println("navigableKeySet.higher():" + higher);


			Entry<String, ContextMenu> firstEntry = map.firstEntry();
			System.out.println("firstEntry:" + firstEntry.getValue().getDisplayName());
			
			Entry<String, ContextMenu> floorEntry = map.floorEntry("0_1_1_");
			System.out.println("floorEntry:" + floorEntry.getValue().getDisplayName());
			
			Entry<String, ContextMenu> ceil = map.ceilingEntry("0_1_");
			System.out.println("Menu ceil:" + ceil.getValue().getDisplayName());
			
			SortedMap<String, ContextMenu> tailMap = map.tailMap("0_1_");
			System.out.println("0_1_ tailMap:" + tailMap);

		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}

	private static void testStringTokenizer() {
		String str = "122";
		System.err.println(str.matches("[0-9]"));
		
		
		String menuCode = "0_1_1_";
		StringTokenizer tokenizer = new StringTokenizer(menuCode, "_");
		while (tokenizer.hasMoreElements()) {
			String menuNode = (String) tokenizer.nextElement();
			System.err.println(menuNode);
		}
	}

	private static void testTreeMap() {
		try {

			/**
			 * NavigableMap Map
			 */

			NavigableMap<String, Integer> map = new TreeMap<String, Integer>();
			map.put("1", 11);
			map.put("2", 22);
			map.put("3", 33);
			map.put("4", 44);

			// 小于该key且离该key最近的一个key
			String lowerKey = (String) map.lowerKey("2");
			System.out.println("lowerKey:" + lowerKey);

			Entry<String, Integer> entry = map.higherEntry("2");
			System.out.println("entry.getKey():" + entry.getKey());
			System.out.println("entry.getValue():" + entry.getValue());

			// 小于等于该key且离该key最近的一个key
			String floorKey = map.floorKey("2");
			System.out.println("floorKey:" + floorKey);

			// 大于等于该key且离该key最近的一个key
			String ceilingKey = map.ceilingKey("2");
			System.out.println("ceilingKey:" + ceilingKey);

			// 小于
			SortedMap<String, Integer> headMap = map.headMap("2");
			System.out.println("headMap:" + headMap);

			// 大于等于
			SortedMap<String, Integer> tailMap = map.tailMap("2");
			System.out.println("tailMap:" + tailMap);

			/**
			 * NavigableSet Set
			 */
			NavigableSet<Integer> set = new TreeSet<Integer>();
			set.add(1001);
			set.add(1002);
			set.add(1003);
			set.add(1004);

			// 小于
			Integer lower = set.lower(1003);
			System.out.println("lower:" + lower);
			// 小于等于
			Integer floor = set.floor(1003);
			System.out.println("floor:" + floor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void recursionAdd(final ContextMenu menu, Set<ContextMenu> menus) {
		String code = StringUtils.isNotEmpty(menu.getCode()) ? menu.getCode() : menu.getNode();
		if(!code.endsWith("_")) {
			code = code.concat("_");
		}
		menu.setCode(code);
		Set<ContextMenu> children = menu.getChildren();
		if(CollectionUtils.isNotEmpty(children)) {
			for (ContextMenu child : children) {
				code = menu.getCode().concat(child.getNode());
				child.setCode(code);
				recursionAdd(child, menus);
			}
		}
		
		menus.add(menu);
	}

}
