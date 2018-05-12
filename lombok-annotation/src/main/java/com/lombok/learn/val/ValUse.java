package com.lombok.learn.val;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValUse {

	public static void main(String[] args) {
		val examples = new ArrayList<String>();
		examples.add("val");
		val foo = examples.get(0);
		log.info(foo);

		testMap();
	}

	public static void testMap() {
		val map = new HashMap<Integer, String>();
		map.put(0, "zero");
		map.put(5, "five");
		for (val entry : map.entrySet()) {
			log.info("key[{}]:value[{}]\n", entry.getKey(), entry.getValue());
		}
	}
}
