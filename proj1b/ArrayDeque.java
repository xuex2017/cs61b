public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int memory;
    private int nextFirst;
    private int nextLast;
    private double usage;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        usage = 0;
        memory = 16;
    }

    public void resize(int cap){
        T[] newitems = (T[]) new Object[cap];
        System.arraycopy(items,nextFirst+1, newitems, 1,size-nextFirst);
        System.arraycopy(items,0,newitems,size-nextFirst+1,nextLast);
        items = newitems;
        nextFirst = 0;
        nextLast = size+1;
        memory = cap;
        usage = size/memory;
    }

//Adds an item of type T to the front of the deque.
    @Override
    public void addFirst(T item){
        if(size == memory -1){
            resize(memory*2);
            memory = memory * 2;
        }
        items[nextFirst] = item;
        if (nextFirst == 0){
            nextFirst = memory-1;
        }
        else
            nextFirst --;
        size ++;
        usage = size/memory;
    }

    //Adds an item of type T to the back of the deque.
    @Override
    public void addLast(T item){
        if(size == memory -1){
            resize(memory*2);
            memory = memory * 2;
        }
        items[nextLast] = item;
        if (nextLast == size-1){
            nextLast = 0;
        }
        else
            nextLast ++;
        size ++;
        usage = size/memory;
    }

    //Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    //Returns the number of items in the deque.
    @Override
    public int size(){
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    @Override
    public void printDeque(){
        for (int count = 0; count<size; count++){
            if(nextFirst+count+1 < memory)
                System.out.print(items[nextFirst+count+1]);
            else System.out.print(items[nextFirst+count+1-memory]);
        }
    }
    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst(){
        if (nextFirst == memory-1) {
            T first = items[0];
            items[0] = null;
            nextFirst = 0;
            size--;
            usage = size/memory;
            if (usage<0.25 && memory>16){
                resize(memory/2);
                memory = memory/2;
                usage = size/memory;
            }
            return first;
        }
        else{
            T first = items[nextFirst+1];
            items[nextFirst+1]=null;
            nextFirst ++;
            size--;
            usage = size/memory;
            if (usage<0.25 && memory>16){
                resize(memory/2);
                memory = memory/2;
                usage = size/memory;
            }
            return first;
        }
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @Override
    public T removeLast(){
        if (nextLast == 0) {
            T last = items[memory-1];
            items[memory-1] = null;
            nextLast = memory-1;
            size--;
            usage = size/memory;
            if (usage<0.25 && memory>16){
                resize(memory/2);
                memory = memory/2;
                usage = size/memory;
            }
            return last;
        }
        else{
            T last = items[nextLast-1];
            items[nextLast-1]=null;
            nextLast --;
            size--;
            usage = size/memory;
            if (usage<0.25 && memory>16){
                resize(memory/2);
                memory = memory/2;
                usage = size/memory;
            }
            return last;
        }
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index){
        return items[nextFirst+index];
    }

    @Override
    public T getFirst() {
        return items[0];
    }

    /** Returns the last item in the list. */
    @Override
    public T getLast() {
        return items[size()];
    }

    
}