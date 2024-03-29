public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int size;
    private int first;
    private int last;

    /**
     * list in array
     * two case: first < last or opsite case
     */


    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        first = 5;
        last = 4;
    }

    /**
     * Suppose we have a very rare situation occur which causes us to:
     * Insert 1,000,000,000 items.
     * Then remove 990,000,000 items.
     * <p>
     * Define the “usage ratio” R = size / items.length;
     * Typical solution: Half array size when R < 0.25.
     * <p>
     * smaller array when  remove so much item
     */
    private void smaller(int capacity) {
        T[] newArray = (T[]) new Object[capacity / 2];
        //copy array value
        if (first < last) {
            int j = newArray.length / 4;
            for (int i = first; i < last + 1; i++, j++) {
                newArray[j] = array[i];
            }
        } else {
            int j = newArray.length / 4;
            for (int i = first; i < array.length; i++, j++) {
                newArray[j] = array[i];
            }
            for (int i = 0; i < last + 1; i++, j++) {
                newArray[j] = array[i];
            }
        }
        first = newArray.length / 4;
        last = first + size() - 1;
        array = newArray;
    }

    /**
     * bigger array when no space to add item
     */
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        //copy array value
        if (first < last) {
            int j = newArray.length / 4;
            for (int i = 0; i < array.length; i++, j++) {
                newArray[j] = array[i];
            }
        } else {
            int j = newArray.length / 4;
            for (int i = first; i < array.length; i++, j++) {
                newArray[j] = array[i];
            }
            for (int i = 0; i < last + 1; i++, j++) {
                newArray[j] = array[i];
            }
        }
        first = newArray.length / 4;
        last = first + array.length - 1;
        array = newArray;

    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size() == array.length) {
            resize(size * 2);
        }

        if (first == 0) {
            first = array.length - 1; //afer add
        } else {
            first -= 1;    //afer add
        }

        size += 1;
        array[first] = item;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size() == array.length) {
            resize(size * 2);
        }

        if (last == array.length - 1) {
            last = 0;   //afer add
        } else {
            last += 1;  //afer add
        }

        size += 1;
        array[last] = item;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return this.size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        if (size() == 0) {
            return;
        }
        if (first < last) {
            for (int i = first; i < last + 1; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = first; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            for (int i = 0; i < last + 1; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size() == 0) {
            return null;
        }
        T item = array[first];

        first += 1;
        first %= array.length;

        size -= 1;

        double R = (double) size / (double) array.length;
        if (array.length >= 16 && R < 0.25) {
            smaller(array.length);
        }
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size() == 0) {
            return null;
        }

        T item = array[last];

        last -= 1;
        last %= array.length;
        if (last < 0) {
            last += array.length;
        }

        size -= 1;

        double R = (double) size / (double) array.length;
        if (array.length >= 16 && R < 0.25) {
            smaller(array.length);
        }
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (size() == 0 || index > size - 1) {
            return null;
        }
        int i = (first + index) % array.length;
        return array[i];
    }
}
