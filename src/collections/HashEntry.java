package collections;

/** A class that represents an entry in the hash table: a (key, data) pair */
public class HashEntry {
    private String key;
    private Object data;
    private boolean isDeleted; // whether this entry was deleted

    /** Constructor for HashEntry
     * @param key
     * @param data
     */
    public HashEntry(String key, Object data) {
        this.setKey(key);
        this.setData(data);
        isDeleted = false; //initialize
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void isDeleted(boolean isDeleted){
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
