package collections;

import java.util.List;
import java.util.ArrayList;

/** An implementation of MyMap interface.
 *  A hash table that uses linear probing to resolve collisions.
 *  Fill in the code in this class and test it thoroughly.
 */
public class MyHashMap implements MyMap {

     private HashEntry[] table; // the actual hashtable - an array of Hash Entries
     private final int maxSize; // the maximum number of entries in the table
     private int currentSize; // how many entries the table contains

    /** A constructor for MyHashMap class
     *  @param maxSize - the maximum number of elements in the hash table.
     */
    public MyHashMap(int maxSize) {
        this.maxSize = maxSize;
        table = new HashEntry[maxSize];

        //initialize the table so there won't be a crash when we try to search in the table
        for (int i = 0; i < maxSize; i++) {
            table[i] = new HashEntry(null, null);
        }

        currentSize = 0; //initialize the size
    }

    /** Insert a (key, data) into the map, overwriting the previous value associated with the given key.
     * @param key If the key is null, throw IllegalArgumentException.
     * @param data
     * @return the previous value associated with this key or null if the key did not map to any value.
     */
    @Override
    public Object put(String key, Object data) {
        if (key == null) {
            keyIsNull();
        }

        int hash = hash(key), counter = 0;
        Object previous;

        //checking weather there is a same key or not
        boolean keyFound = false;
        if (size() > 0) {
            for (String checkKey: keys()) {
                if (checkKey == key) {
                    keyFound = true;
                }
            }
        }

        /*
            break:
            1. when the key is null(empty or deleted) and there is no same key  -> put
            2. when the key is equal                                            -> overwrite and return the previous value
            3. when the searching goes through a full loop                      -> do nothing
        */
        while (counter < maxSize) {

            if (table[hash].getKey() == null && !keyFound) {
                break;
            }
            else if (table[hash].getKey() == key) {
                break;
            }

            hash = (hash + 1) % maxSize;
            counter++; //stop when the search done a full loop
        }

        if (counter == maxSize){
            previous  = null;
        }
        else {
            previous = table[hash].getData();

            if (table[hash].getKey() == null) {
                table[hash] = new HashEntry(key, data); //put a new data into the empty or deleted cell
                currentSize++;
                table[hash].isDeleted(false); //initialize the status
            }
            else {
                table[hash].setData(data); //replacing data
            }
        }

        return previous;
    }

    /** Return the value associated with the given key or null if the key does not map to any value
     *  @param key If null, throw IllegalArgumentException.
     *  @return the value associated with the key.
     */
    @Override
    public Object get(String key) {
        if (key == null) {
            keyIsNull();
        }

        int hash = hash(key), counter = 0;
        Object data;

        /*
            break:
            1. when the key is null but not deleted         -> return null
            2. when the key is equal                        -> get value
            3. when the searching goes through a full loop  -> return null
        */
        while (counter < maxSize) {

            if (table[hash].getKey() == null && !table[hash].isDeleted()) {
                break;
            }
            else if (table[hash].getKey() == key) {
                break;
            }

            hash = (hash + 1) % maxSize;
            counter++; //keep searching till is done a circle
        }


        if (table[hash].getKey() == null || counter == maxSize) {
            data = null; //empty cell or not found
        }
        else {
            data = table[hash].getData();
        }

        return data;
    }

    /** Delete the (key, data) entry from the map or do nothing if this key is not in the map.
     *  @param key
     *  @return The value associated with this key before deletion
     */
    @Override
    public Object delete(String key) {
        if (key == null) {
            keyIsNull();
        }

        int hash = hash(key), counter = 0;
        Object previous;

        /*
            break:
            1. when the key is null but not deleted         -> do nothing
            2. when the key is equal                        -> delete entry
            3. when the searching goes through a full loop  -> do nothing
        */
        while (counter < maxSize) {

            if (table[hash].getKey() == null && !table[hash].isDeleted()) {
                break;
            }
            else if (table[hash].getKey() == key) {
                break;
            }

            hash = (hash + 1) % maxSize;
            counter++; //keep searching till is done a circle
        }

        if (table[hash].getKey() == key) {
            previous = table[hash].getData();
            table[hash].setKey(null); //deleting key
            table[hash].setData(null); //deleting data
            table[hash].isDeleted(true); //mark
            currentSize--; //update size
        }
        else {
            previous = null;
        }

        return previous;
    }

    /** Return the number of entries in the map
     * @return the number of (key, value) entries in the map.
     */
    @Override
    public int size() {
        return currentSize;
    }

    /** Return map keys
     * @return a list of all keys in the map
     */
    public List<String> keys() {
        if (size() == 0) {
            return null;
        }
        else {
            List<String> keys = new ArrayList<>();

            for (int i = 0; i < maxSize; i++){
                if (table[i].getKey() != null){
                    if (!table[i].isDeleted()) {
                        keys.add(table[i].getKey());
                    }
                }
            }

            return keys;
        }
    }

    /** Return map values
     * @return a list of all values in the map
     */
    @Override
    public List<Object> values() {
        if (size() == 0) {
            return null;
        }
        else {
            List<Object> data = new ArrayList<>();

            for (int i = 0; i < maxSize; i++){
                if (table[i].getKey() != null){
                    if (!table[i].isDeleted()) {
                        data.add(table[i].getData());
                    }
                }
            }

            return data;
        }
    }

    /** Returns the index in the hash table that this key hashes to.
     * @param key
     * @return the index in the table
     * */
    private int hash(String key) {
        return (int)(hashCode(key) % maxSize);
    }

    private long hashCode(String key) {
        long hashCode = 0, constant = 31;
        char letter;

        for (int i = 0; i < key.length(); i++) {
            letter = key.charAt(i);
            hashCode = (int)letter + constant * hashCode;
        }

        return hashCode;
    }

    private void keyIsNull() {
        throw new IllegalArgumentException("The key cannot be null.");
    }
}
