public class ArrayDeque<Type> {
    private int size;
    private Type[] items;
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
        items = (Type[]) new Object[initsize];
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
            Type[] a = (Type[]) new Object[targetLength];
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

    public Type get(int i){
        return items[(nextFirst+1+i)%items.length];
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void addFirst(Type x){
        resize();
        items[nextFirst] = x;
        //update nextfirst size usage
        nextFirst = (nextFirst - 1 + items.length)%items.length;
        size += 1;
        updateUsage();

    }

    public void addLast(Type x){
        resize();
        items[nextLast] = x;
        //update
        nextLast = (nextLast+1)%items.length;
        size++;
        updateUsage();
    }


    public Type removeFirst(){
        if (isEmpty())
            return null;
        resize();

        size--;
        nextFirst = (nextFirst + 1)%items.length;
        updateUsage();
        items[nextFirst] = null;
        Type x = items[nextFirst];
        return x;
    }

    public Type removeLast(){
        if (isEmpty())
            return null;
        resize();

        size--;
        nextLast = (nextLast-1+items.length)%items.length;
        updateUsage();
        items[nextLast] = null;
        Type x = items[nextLast];
        return x;
    }

}
