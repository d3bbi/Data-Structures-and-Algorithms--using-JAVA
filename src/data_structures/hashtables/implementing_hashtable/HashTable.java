package data_structures.hashtables.implementing_hashtable;

/*
Note: This code can be Optimized with all types of safety but for sake of understanding
      its been kept simple.
*/

import java.util.ArrayList;
import java.util.Arrays;

public class HashTable {
	// create an array that will hold elements of type ArrayList<KeyValue>.
	// ArrayList<keyValue> means the ArrayList holds Objects of type - keyValue as
	// its elements.
	ArrayList<KeyValue>[] data;
	int currentLength;

	/*
	 * constructor has a parameter "int size" we initialize the attribute data equal
	 * to an ArrayList that has size given
	 */
	public HashTable(int size) {
		data = new ArrayList[size];
		currentLength = 0;
	}

	/*
	 * method hash, has a String as parameter initialized an integer "hash" loop we
	 * try to find a key within our arrayList where to store the information given
	 * (needs to be %data.length -within data length)
	 */
	private int hash(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash = (hash + key.codePointAt(i) * i) % data.length;
//			System.out.println("KEY for "+key+" - i is (" + i + ")  {" + key.codePointAt(i) + " * " + i + "}\t"
//					+ key.codePointAt(i) * i + "  [ % "+ data.length +" ]\t" + key.codePointAt(i) * i % data.length + "\thash is "+hash);
		}
//		System.out.println("HASH for "+key+" is "+ hash);
		return hash;
	}

	/*
	 * twe set the key index found into address variable. Then we check, if the
	 * "block" in the arrayList[index address] is empty we create a new arrayList
	 * and we add it to the data[address index]
	 */
	public void set(String key, int value) {
		int address = hash(key);
		if (data[address] == null) {
			ArrayList<KeyValue> arrayAtAddress = new ArrayList<>();
			data[address] = arrayAtAddress;
//			currentLength++;
		}
		KeyValue pair = new KeyValue(key, value);
		data[address].add(pair);
		currentLength++;
	}

	public Integer get(String key) {
		int address = hash(key);
		ArrayList<KeyValue> bucket = data[address];
		if (bucket != null) {
			for (KeyValue keyValue : bucket) {
				if (keyValue.getKey().equals(key)) {
					return keyValue.getValue();
				}
			}
		}
		return null; // return null when key does not exist.
	}

	public String[] keys() {
		ArrayList<KeyValue>[] bucket = data;
		String[] keysArray = new String[currentLength];
		int count = 0;
		for (ArrayList<KeyValue> keyValues : bucket) {
			if (keyValues != null) {
				if (keyValues.size() > 1) {
					int getIndex = 0;
					while(getIndex < keyValues.size()) {
						keysArray[count++] = keyValues.get(getIndex++).getKey();
					}
				} else {
					keysArray[count] = keyValues.get(0).getKey();
					count++;	
				}

			}
		}
		return keysArray;
	}

	public static void main(String[] args) {
		HashTable hashTable = new HashTable(2);
		hashTable.set("grapes", 1200);
		hashTable.set("apple", 51);
		hashTable.set("orange", 31);
		System.out.println("value for key grapes: " + hashTable.get("grapes"));
		System.out.println("value for key apple: " + hashTable.get("apple"));
		System.out.println("value for key orange: " + hashTable.get("orange"));
		System.out.println("list of keys: " + Arrays.toString(hashTable.keys()));
	}
}
