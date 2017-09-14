package collections;

import org.junit.jupiter.api.Test;

class MyHashMapPutTest {

    @Test
    public void testPut() {
        System.out.println("-------------------*Test one*-------------------");
        MyHashMap myMap = new MyHashMap(5);

        myMap.put("4", "123");
        System.out.println(myMap.get("4"));
        System.out.println(myMap.size());

        myMap.put("9", "234");
        System.out.println(myMap.get("9"));
        System.out.println(myMap.size());

        myMap.delete("4");
        System.out.println(myMap.get("4"));
        System.out.println(myMap.size());

        myMap.put("9", "234");
        System.out.println(myMap.get("9"));
        System.out.println(myMap.size());

        myMap.put("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", "zzz");
        System.out.println(myMap.get("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"));
        System.out.println(myMap.size());
    }


    @Test
    public void Drive() {
        System.out.println("\n-------------------*Test two*-------------------");
        MyHashMap hmap = new MyHashMap(7);
        int errorCounter = 0;
        // insert a bunch of keys, some with the same hash code
        // Strings that hash to the same hash code were taken from the following site:
        // https://stackoverflow.com/questions/12925988/how-to-generate-strings-that-share-the-same-hashcode-in-java
        hmap.put("BB", 4);
        hmap.put("Aa", 2);

        hmap.put("AaAa", 7);
        hmap.put("BBBB", 5);
        hmap.put("BBAb", 10);

        // check if the correct (key, value) entry was stored in the hash table
        if ((Integer)hmap.get("BB") != 4) {
            System.out.println("Wrong value for the key 'BB'");
            errorCounter++;
        }
        if ((Integer)hmap.get("Aa") != 2) {
            System.out.println("Wrong value for the key 'Aa'");
            errorCounter++;
        }
        if ((Integer)hmap.get("AaAa") != 7) {
            System.out.println("Wrong value for the key 'AaAa'");
            errorCounter++;
        }
        if ((Integer)hmap.get("BBAb") != 10) {
            System.out.println("Wrong value for the key 'BBAb'");
            errorCounter++;
        }

        // check "Replacing a value"
        if (((Integer)hmap.put("Aa", 19) != 2) || ((Integer)hmap.get("Aa") != 19)) {
            System.out.println("Overwriting the key Aa did not work correctly. ");
            errorCounter++;
        }

        // Check Deletion
        if ((Integer)hmap.delete("BB") != 4) {
            System.out.println( "Deleting 'BB' did not work correctly. ");
            errorCounter++;
        }

        if (hmap.get("BB") != null) {
            System.out.println( "After we deleted 'BB', value for it should be null, but it is not ");
            errorCounter++;
        }

        // check "Replacing a value"
        hmap.put("BB", 25);
        if (((Integer)hmap.get("BB") != 25)) {
            System.out.println("Deletion followed by inserting the key BB again did not work correctly. ");
            errorCounter++;
        }

        System.out.println("Test two error: " + errorCounter);
    }

}