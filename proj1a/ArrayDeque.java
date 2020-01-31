public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private double usage;
    private int nextFirst;
    private int nextLast;
    private int initsize = 8;
    private static int eFactor = 2;
    private static int cFactor = 2;

    /* Constructor */
    public ArrayDeque(){
        // create an empty ArrayDeque  (circular array)
        size = 0;
        items = (T[]) new Object[initsize];
        usage = 0;
        nextFirst = 3;
        nextLast = nextFirst + 1;
    }

    /* helper function  check the usage */
    // return true if it needs to be half size
    private boolean checkUsageR(){
        return (usage < 0.25) && (items.length > 8);
    }

    // update usage
    private void updateUsage(){
       usage =  (double) size/items.length;
    }


    /** to double or half before any options if needed */
    private void resize(){
        if(checkUsageR() || size == items.length){
            // need resize
            int targetLength;
            if (checkUsageR())
                targetLength = items.length/cFactor;
            else
                targetLength = items.length*eFactor;

            //start copy
            T[] a = (T[]) new Object[targetLength];
            for (int i=0; i<size;i++){
                a[i] = items[(nextFirst+1+i)%items.length];
            }
            //update items nextFirst nextLast usage
            items = a;
            nextFirst = items.length-1;
            nextLast = size;
            updateUsage();
        }
    }

    /**
     *  methods of deque
     *  get
     *  size
     *  addfirst
     *  addlast
     *  removefist
     *  removelast
     * */

    public int size(){
        return size;
    }

    public T get(int i){
        return items[(nextFirst+1+i)%items.length];
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void addFirst(T x){
        resize();
        items[nextFirst] = x;
        //update nextfirst size usage
        nextFirst = (nextFirst - 1 + items.length)%items.length;
        size += 1;
        updateUsage();

    }

    public void addLast(T x){
        resize();
        items[nextLast] = x;
        //update
        nextLast = (nextLast+1)%items.length;
        size++;
        updateUsage();
    }


    public T removeFirst(){
        if (isEmpty())
            return null;
        resize();

        size--;
        nextFirst = (nextFirst + 1)%items.length;
        updateUsage();
        items[nextFirst] = null;
        T x = items[nextFirst];
        return x;
    }

    public T removeLast(){
        if (isEmpty())
            return null;
        resize();

        size--;
        nextLast = (nextLast-1+items.length)%items.length;
        updateUsage();
        items[nextLast] = null;
        T x = items[nextLast];
        return x;
    }

    /** printDeque */
    public void printDeque() {
        /* As in resize  */
        int oldIndex = nextFirst + 1;
        int count = 0;
        String str = "";
        while (count < size) {
            T val = items[oldIndex % items.length];
            str += count == size - 1 ? val : (val + " ");
            oldIndex++;
            count++;
        }
        System.out.println(str);
        // return str;
    }

}
