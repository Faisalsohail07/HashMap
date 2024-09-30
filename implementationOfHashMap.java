package HashMap;

import java.util.LinkedList;

public class implementationOfHashMap {
    static class MyHashMap<K, V>{
        public static final int DEFAULT_CAPACITY=4;
        public static final float DEFAULT_LOAD_FACTOR=0.75f;
        private class Node {
            K key;
            V value;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
            private int n; //to get no. entries in map
            private LinkedList<Node>[] buckets;
            public MyHashMap(){
                initBuckets(DEFAULT_CAPACITY);
            }
            private void initBuckets(int N){
                buckets=new  LinkedList[N];
                for(int i=0; i< buckets.length; i++){
                    buckets[i]=new LinkedList<>();

                }
            }
            private int searchInBucket(LinkedList<Node> ll, K key){
                for(int i=0; i<ll.size(); i++){
                    if(ll.get(i).key==key){
                        return i;
                    }
                }
                return -1;
            }
            private int HashFunc(K key){
                int hc=key.hashCode();
                return (Math.abs(hc))% buckets.length;
            }

            public int size(){
                return n;
            }
            public int capacity(){
                return buckets.length;
            }
            public float load(){
                return (n*1.0f)/buckets.length;
            }

            private void rehash(){
                LinkedList<Node>[] oldBuckets= buckets;
                initBuckets(oldBuckets.length*2);
                n=0;
                for(var bucket:oldBuckets){
                    for(var node: bucket){
                        put(node.key, node.value);
                    }
                }
            }
            public void put(K key, V value){
                int bi=HashFunc(key);
                LinkedList<Node> currBucket= buckets[bi];
                int ei= searchInBucket(currBucket, key);
                if(ei==-1){
                    Node node = new Node(key, value);
                    currBucket.add(node);
                    n++;
                }
                else{
                    Node currNode= currBucket.get(ei);
                    currNode.value=value;
                }
                if(n>= buckets.length*DEFAULT_LOAD_FACTOR){
                    rehash();
                }
            }
            public V get(K key){
                int bi= HashFunc(key);
                LinkedList<Node> currBucket= buckets[bi];
                int ei= searchInBucket(currBucket, key);
                if(ei!=-1){// present
                    Node currNode = currBucket.get(ei);
                    return currNode.value;
                }
                else{
                    return null;
                }
            }
            public V remove(K key){
                int bi= HashFunc(key);
                LinkedList<Node> currBucket= buckets[bi];
                int ei= searchInBucket(currBucket, key);
                if(ei!=-1){
                    Node currNode= currBucket.get(ei);
                    V val= currNode.value;
                    currBucket.remove(ei);
                    n--;
                    return val;
                }
                return null;
            }

    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map= new MyHashMap<>();
        System.out.println("Testing Put");
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        System.out.println("Size: "+map.size());
        map.put("c", 36);
        System.out.println("Size: "+map.size());
        System.out.println("Testing get");
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        System.out.println(map.get("c"));
    }
}
