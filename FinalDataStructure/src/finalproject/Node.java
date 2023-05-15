package finalproject;


public class Node {
    private Students std;
    private Node next;

    public Node(Students std) {
        this.std = std;
    }

    public Students getStd() {
        return std;
    }

    public Node getNext() {
        return next;
    }

    public void setStd(Students std) {
        this.std = std;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node() {
    }

    @Override
    public String toString() {
        return "MyNode{" + "std=" + std.toString() + '}';
    }
    
}
