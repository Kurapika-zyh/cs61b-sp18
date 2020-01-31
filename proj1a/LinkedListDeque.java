public class LinkedListDeque<Type>{
    /**
     * deque: double-ended-queue
     * */
    private class Node{
        private Type item;
        private Node prev;
        private Node next;

        private Node(Node p, Type i, Node n){
            item = i;
            prev = p;
            next = n;
        }
    }

    /**
     * declare variables
     * */
    private int size;
    private Node Ssentinel = new Node(null,null,null);
    private Node Esentinel = new Node(null,null,null);



    /**
     * create an empty LLDeque
     * */
    public LinkedListDeque(){
        size = 0;

        Ssentinel.prev = Esentinel;
        Ssentinel.next = Esentinel;
        Esentinel.prev = Ssentinel;
        Esentinel.next = Ssentinel;
    }

    /*Constructor*/
    public LinkedListDeque(Type x){
        size = 1;
        Ssentinel.prev = Esentinel;
        Esentinel.next = Ssentinel;

        Ssentinel.next = new Node(Ssentinel, x, Esentinel);
        Esentinel.prev = Ssentinel.next;

    }

    /**
     * methods
     * */
    public int size(){
        return size;
    }


    public boolean isempty(){
        if(size==0)
            return true;
        return false;
    }


    public void addFirst(Type x){
        size += 1;
        Ssentinel.next = new Node(Ssentinel, x, Ssentinel.next);
        Ssentinel.next.next.prev = Ssentinel.next;
    }

    public void addLast(Type x){
        size += 1;
        Esentinel.prev = new Node(Esentinel.prev, x, Esentinel);
        Esentinel.prev.prev.next = Esentinel.prev;
    }

    public void printDeque(){
        Node p = Ssentinel.next;
        for(;p!=Esentinel;p = p.next){
            System.out.print(p.item + " ");
        }
    }

    public Type removeFirst(){
        if(this.isempty())
            return null;

        size -= 1;
        Type firstitem = Ssentinel.next.item;
        Ssentinel.next = Ssentinel.next.next;
        Ssentinel.next.prev = Ssentinel;
        return firstitem;
    }

    public Type removeLast(){
        if(this.isempty()){
            return null;
        }

        size -= 1;
        Type lastitem = Esentinel.prev.item;
        Esentinel.prev = Esentinel.prev.prev;
        Esentinel.prev.next = Esentinel;
        return lastitem;
    }

    /* return the ith item of the deque */
    public Type get(int index){
        // return null if index > length-1
        int length = size;
        if(index>length-1)
            return null;

        Node p = Ssentinel;
        for (int i=0; i<index;i++)
            p = p.next;
        return p.next.item;
    }

    public Type getRecursive(int index){
        int length = size;
        if(index>length-1)
            return null;

        return traverse(Ssentinel.next,index);

    }

    private Type traverse(Node n, int i){
        if(i==0){
            return n.item;
        }
        else {
            return traverse(n.next,i-1);
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> Dllist = new LinkedListDeque<>();
        Dllist.addFirst(666);
        Dllist.addLast(6666);
        Dllist.addLast(66666);
        Dllist.printDeque();                        // expected (666 6666 66666)
        System.out.println("Test get #1");
        System.out.println(Dllist.get(0)); // expected 666
        System.out.println(Dllist.get(1)); // expected 6666
        System.out.println(Dllist.get(5)); // expected null
        System.out.println("Test get #1");
        System.out.println(Dllist.getRecursive(0)); // expected 666
        System.out.println(Dllist.getRecursive(1)); // expected 6666
        System.out.println("Test done!");

        Dllist.removeFirst();
        Dllist.printDeque();                        // expected (6666 66666)
        System.out.println("Test get #2 removeFirst");
        System.out.println(Dllist.get(0)); // expected 6666
        System.out.println(Dllist.get(1)); // expected 66666
        System.out.println("Test getRecursive #2 removeFirst");
        System.out.println(Dllist.getRecursive(0)); // expected 6666
        System.out.println(Dllist.getRecursive(1)); // expected 66666

        Dllist.removeLast();
        Dllist.printDeque();                        // expected 6666
        System.out.println("Test get #3 removeLast");
        System.out.println(Dllist.get(0)); // expected 6666
        System.out.println(Dllist.get(1)); // expected null
        System.out.println("Test getRecursive #3 removeFirst");
        System.out.println(Dllist.getRecursive(0)); // expected 6666
        System.out.println(Dllist.getRecursive(1)); // expected null
    }

}