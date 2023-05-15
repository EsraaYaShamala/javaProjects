package finalproject;

public class Algorithm {

    public int LinearSearch(LinkedList link, int id) {
        Node curr = link.getHead();
        int index = 0;
        while (curr != null) {
            if (curr.getStd().getId() == id) {
                return index;
            }
            curr = curr.getNext();
            index++;
        }
        return -1;
    }

    void bubbleSort(LinkedList link) {
        boolean swap = true;
        while (swap) {
            swap = false;
            Node curr = link.getHead();
            while (curr != null) {
                if (curr.getNext() == null) {
                    return;
                } else if (curr.getStd().getId() > curr.getNext().getStd().getId()) {
                    Students s1 = curr.getStd();
                    Students s2 = curr.getNext().getStd();
                    int indexS1 = link.indexOf(s1);
                    int indexS2 = link.indexOf(s2);
                    link.swap(indexS1, indexS2);
                    swap = true;
                }
                curr = curr.getNext();
            }

        }
    }


    Node middleNode(Node start, Node last) {
        if (start == null) {
            return null;
        }

        Node slow = start;
        Node fast = start.getNext();

        while (fast != last) {
            fast = fast.getNext();
            if (fast != last) {
                slow = slow.getNext();
                fast = fast.getNext();
            }
        }
        return slow;
    }

    Node binarySearch(Node head, int id) {
        Node start = head;
        Node last = null;
        do {
            // Find Middle
            Node mid = middleNode(start, last);

            // If middle is empty
            if (mid == null) {
                return null;
            }
            // If value is present at middle
            if (mid.getStd().getId() == id) {
                return mid;
            } // If value is less than mid
            else if (mid.getStd().getId() < id) {
                start = mid.getNext();
            } // If the value is more than mid.
            else {
                last = mid;
            }
        } while (last == null || last != start);

        // value not present
        return null;
    }
    
    
    Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }

        Node slow = head, fast = head;

        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }
    
    
    
    
    
    
    
    
    
}
