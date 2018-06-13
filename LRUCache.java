class LRUCache {
    
    private Map<Integer, Node> map;
    private Node head, tail;
    private int capacity;
    
    public LRUCache (int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        head = new Node(0,0);
        tail = new Node(0,0);
        
        head.next = tail;
        tail.pre = head;
    }
    
    private void addNode(Node node) {
        Node n1 = head.next;
        head.next = node;
        node.pre = head;
        
        n1.pre = node;
        node.next = n1;
    }
    
    private void removeNode(Node node) {
        Node n1 = node.pre;
        Node n2 = node.next;
        
        n1.next = n2;
        n2.pre = n1;
        
        node.pre = null;
        node.next = null;
    }
    
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }
    
    private Node popTail() {
        if(map.size() == 0) {
            return null;
        }
        
        Node last = tail.pre;
        removeNode(last);
        
        return last;
    }
    
    public int get(int key) {
        
        if(map.containsKey(key)) {
            Node node = map.get(key);
            
            if(map.size() != 1) {
                moveToHead(node);
            }
            return node.val;
        }
        
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            if(map.size() != 1) {
                moveToHead(node);
            }         
        } else {
            Node node = new Node(key, value);          
            if(map.size() == capacity) {
                Node last = popTail();               
                map.remove(last.key);
            } 
            addNode(node);
            map.put(key, node);
        }
    }
    
    private static class Node {
        int key;
        int val;
        
        Node pre, next;
        
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
