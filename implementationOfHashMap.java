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
            private LinkedList<Node>[] box;
            public MyHashMap(){
                initBox(DEFAULT_CAPACITY);
            }
            private void initBox(int N){
                box=new  LinkedList[N];
                for(int i=0; i< box.length; i++){
                    box[i]=new LinkedList<>();

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
            private int HashFunction(K key){
                int hc=key.hashCode();
                return (Math.abs(hc))% box.length;
            }

            public int size(){
                return n;
            }
            public int capacity(){
                return box.length;
            }
            public float load(){
                return (n*1.0f)/box.length;
            }

            private void rehash(){
                LinkedList<Node>[] oldBox= box;
                initBox(oldBox.length*2);
                n=0;
                for(var bucket:oldBox){
                    for(var node: bucket){
                        put(node.key, node.value);
                    }
                }
            }
            public void put(K key, V value){
                int bi=HashFunction(key);
                LinkedList<Node> currBucket= box[bi];
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
                if(n>= box.length*DEFAULT_LOAD_FACTOR){
                    rehash();
                }
            }
            public V get(K key){
                int bi= HashFunction(key);
                LinkedList<Node> currBox= box[bi];
                int ei= searchInBucket(currBox, key);
                if(ei!=-1){// present
                    Node currNode = currBox.get(ei);
                    return currNode.value;
                }
                else{
                    return null;
                }
            }
            public V remove(K key){
                int bi= HashFunction(key);
                LinkedList<Node> currBox= box[bi];
                int ei= searchInBucket(currBox, key);
                if(ei!=-1){
                    Node currNode= currBox.get(ei);
                    V val= currNode.value;
                    currBox.remove(ei);
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
