
public class LinkedListDeque<Whatever> implements Deque<Whatever>{


    // nested class Stuff Node.
    private class StuffNode {
        public StuffNode prev;
        public Whatever item;
        public StuffNode next;

        public StuffNode(Whatever i, StuffNode p, StuffNode n){
            item = i;
            prev = p;
            next = n;
        }

        public StuffNode(Whatever x) {
            item = x;
            prev = null;
            next = null;
        }

    }

    //the first node and the last node (if existing) is at sentinel.next and sentinel.prev
    private StuffNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new StuffNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(Whatever x){
        sentinel = new StuffNode(null);
        sentinel.next = new StuffNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Returns the first item in the list. */
    @Override
    public Whatever getFirst() {
        return sentinel.next.item;
    }

    /** Returns the last item in the list. */
    @Override
    public Whatever getLast() {
        return sentinel.prev.item;
    }


    //Adds an item of type T to the front of the deque.
    @Override
    public void addFirst(Whatever x){
        StuffNode first = new StuffNode(x);
        /*if (this.isEmpty() == true){
            sentinel.next = new StuffNode(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size = 1;
        }
        else{*/
        first.prev = sentinel;
        first.next = sentinel.next;
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
        //}

    }

    /** Adds an item to the end of the list. */
    @Override
    public void addLast(Whatever x) {
        StuffNode last = new StuffNode(x);
        last.prev = sentinel.prev;
        last.next = sentinel;
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.*/
    @Override
    public void printDeque(){

        StuffNode curr = sentinel.next;
        while (curr != sentinel && curr != null) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    @Override
    public Whatever removeFirst(){
        Whatever first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return first;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    @Override
    public Whatever removeLast(){
        Whatever last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.*/
    @Override
    public Whatever get(int index){
        if (index >= size || index < 0){
            return null;
        }
        else{
            StuffNode temp = sentinel;
            for (int i = 0; i <= index; i++){
                temp = temp.next;
            }
            return temp.item;
        }
    }

    /**Same as get, but uses recursion*/

    public Whatever getRecursive(int index){
        if (index >= size || index < 0){
            return null;
        }
        else{
            if (index == 0) {
                return sentinel.next.item;
            }
            else{
                this.removeFirst();
                return this.getRecursive(index - 1);
            }

        }
    }



}
