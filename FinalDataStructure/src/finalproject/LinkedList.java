package finalproject;

import java.util.Scanner;

public class LinkedList {
    Algorithm algorithm = new Algorithm();
    private Node head, tail;
    private int size = 0;

    public void mainMenu(Students std, int index) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter type of operation in student "+std.getId());
        System.out.println("    1.Adds");
        System.out.println("    2.Deletes");
        System.out.println("    3.Updates");
        System.out.println("    4.Searchs");
        System.out.println("    5.Reports");
        System.out.println("    6.Sort");
        System.out.println("    7.Exit");
        int choice = s.nextInt();
        if (choice == 7) {
            return;
        } else if (choice == 1) {
            System.out.println("Enter type of Add ");
            System.out.println("    1.Add First");
            System.out.println("    2.Add Last");
            System.out.println("    3.Add By Index");
            int addChoice = s.nextInt();
            if (addChoice == 1) {
                addFirst(std);
                System.out.println("********************************");
                mainMenu(std,index);
            } else if (addChoice == 2) {
                appened(std);
                System.out.println("********************************");
                mainMenu(std,index);
            } else if (addChoice == 3) {
                this.addByIndex(std, index);
                System.out.println("********************************");
                mainMenu(std,index);
            }
        } else if (choice == 2) {
            System.out.println("Enter type of Deletes ");
            System.out.println("    1.Delete First");
            System.out.println("    2.Delete Last");
            System.out.println("    3.Delete By Index");
            System.out.println("    4.Delete Value");
            int deleteChoice = s.nextInt();
            if (deleteChoice == 1) {
                this.deleteFirst();
                System.out.println("********************************");
                mainMenu(std,index);
            } else if (deleteChoice == 2) {
                this.deleteLast();
                System.out.println("********************************");
                mainMenu(std,index);
            } else if (deleteChoice == 3) {
                System.out.print("Enter Student index : ");
                int deleteIndex=s.nextInt();
                this.deleteByIndex(deleteIndex);
                System.out.println("********************************");
                mainMenu(std,index);
            } else if (deleteChoice == 4) {
                this.deleteValue(std);
                System.out.println("********************************");
                mainMenu(std,index);
            }
        } else if (choice == 3) {
            this.upDate(std);
            System.out.println("********************************");
            mainMenu(std,index);
        } else if (choice == 4) {
            System.out.println("Enter type of Search ");
            System.out.println("    1.Linear Search");
            System.out.println("    2.binarySearch");
            int searchChoice = s.nextInt();
            if (searchChoice == 1) {
                int LinearSearch= algorithm.LinearSearch(this ,std.getId());
                System.out.println("This std is exist in index "+LinearSearch);
                System.out.println("********************************");
                mainMenu(std,index);
            } else if (searchChoice == 2) {
                Node binarySearch =algorithm.binarySearch(this.head,std.getId());
                if(binarySearch==null){
                    System.out.println("Value not present");
                }else{
                    System.out.println("Present");
                }
                System.out.println("********************************");
                mainMenu(std,index);
            } else if (searchChoice == 3) {
                
                System.out.println("********************************");
                mainMenu(std,index);
            }
        } else if (choice == 5) {
            repotrs();
            System.out.println("********************************");
            mainMenu(std,index);
        }else if(choice==6){
            System.out.println("Enter type of Sort ");
            System.out.println("    1.Bubble Sort");
            System.out.println("    2.Merge Sort");
            int sortChoice = s.nextInt();
            if (sortChoice == 1) {
                algorithm.bubbleSort(this);
                System.out.println("Linked List is Sorted");
                System.out.println("********************************");
                mainMenu(std,index);
            } else if (sortChoice == 2) {
                this.sort();
                System.out.println("Linked List is Sorted");
                System.out.println("********************************");
                mainMenu(std,index);
            } 
            System.out.println("********************************");
            mainMenu(std,index);
        }

    }

    
    
    public void appened(Students std) {
        Node nw = new Node(std);
        if (head == null) {
            head = tail = nw;
        } else {
            tail.setNext(nw);
            tail = nw;
        }
        size++;
        System.out.println("Student is add ");
    }

    
    
    public void addFirst(Students std) {
        Node nw = new Node(std);
        if (head == null) {
            head = tail = nw;
        } else {
            nw.setNext(head);
            head = nw;
        }
        size++;
        System.out.println("Student is add ");
    }

    
    
    public void addByIndex(Students std, int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                addFirst(std);
            } else if (index == size) {
                appened(std);
            } else {
                Node nw = new Node(std);
                Node curr = head;
                int counter = 0;
                while (counter != index && curr != null) {
                    if (counter == index - 1) {
                        nw.setNext(curr.getNext());
                        curr.setNext(nw);
                    }
                    curr = curr.getNext();
                    counter++;
                }
                size++;
                System.out.println("Student is add ");
            }
        }
    }

    
    
    public void deleteByIndex(int index) {
        if (index >= 0 && index <= size) {
            if (index == 0) {
                head = head.getNext();
            } else if (index == size - 1) {
                Node curr = head;
                int counter = 0;
                while (counter != index && curr != null) {
                    if (counter == index - 1) {
                        tail = curr;
                        tail.setNext(null);
                    }
                    curr = curr.getNext();
                    counter++;
                }
            } else {
                Node curr = head;
                int counter = 0;
                while (counter != index && curr != null) {
                    if (counter == index - 1) {
                        curr.setNext(curr.getNext().getNext());
                    }
                    curr = curr.getNext();
                    counter++;
                }
            }
            size--;
            System.out.println("Student is deleted ");
        }
    }

    
    
    public void deleteFirst() {
        deleteByIndex(0);
    }

    
    
    public void deleteLast() {
        deleteByIndex(size - 1);
    }

    
    
    public void deleteValue(Students std) {
        int i = indexOf(std);
        if (i == -1) {
            return;
        } else {
            deleteByIndex(i);
        }
    }

    
    
    public boolean contains(Students std) {
        return indexOf(std) != -1;
    }

    
    
    public boolean isEmpty() {
        return head == null;
    }

    
    
    public int indexOf(Students std) {
        int index = 0;
        Node curr = head;
        while (curr != null) {
            if (curr.getStd() == std) {
                return index;
            } else {
                curr = curr.getNext();
                index++;
            }
        }
        return -1;
    }

    
    
    public void upDate(Students std) {
        Scanner s = new Scanner(System.in);
        Node curr = head;
        while (curr != null) {
            if (curr.getStd().getId() == std.getId()) {
                System.out.println("Choose information Update ");
                System.out.println("    1.Enter Student id ");
                System.out.println("    2.Enter Student name ");
                System.out.println("    3.Enter Student address ");
                System.out.println("    4.Enter Student avg ");
                System.out.println("    5.Enter Student level ");
                System.out.println("    6.Enter Student isGraduated ");
                System.out.println("    7.Enter Student installments ");

                int choice = s.nextInt();
                if (choice == 1) {
                    System.out.print("The New Value is : ");
                    curr.getStd().setId(s.nextInt());
                } else if (choice == 2) {
                    System.out.print("The New Value is : ");
                    curr.getStd().setName(s.next());
                } else if (choice == 3) {
                    System.out.print("The New Value is : ");
                    curr.getStd().setAddress(s.next());
                } else if (choice == 4) {
                    System.out.print("The New Value is : ");
                    curr.getStd().setAvg(s.nextDouble());
                } else if (choice == 5) {
                    System.out.print("The New Value is : ");
                    curr.getStd().setLevel(s.nextInt());
                } else if (choice == 6) {
                    System.out.print("The New Value is : ");
                    curr.getStd().setIsGraduated(s.nextBoolean());
                } else if (choice == 7) {
                    System.out.print("The New Value is : ");
                    curr.getStd().setInstallments(s.nextDouble());
                } else {
                    System.out.println("Invalid choose");
                }
                System.out.println("Student Update is done .");
            }
            curr = curr.getNext();
        }

    }

    
    
    public void reportsAll() {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.getStd().toString());
            curr = curr.getNext();
        }
    }

    
    
    public void reportInSpecificAddress() {
        Node curr = head;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a Specific Address : ");
        String address = s.next();
        while (curr != null) {
            if (curr.getStd().getAddress().equalsIgnoreCase(address)) {
                System.out.println(curr.getStd().toString());
            }
            curr = curr.getNext();
        }
    }
    
    public void reportInSpecificName() {
        Node curr = head;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a Specific name : ");
        String name = s.next();
        int counter=0;
        while (curr != null) {
            if (curr.getStd().getName().equalsIgnoreCase(name)) {
                System.out.println(curr.getStd().toString());
                counter++;
            }
            curr = curr.getNext();
        }
        System.out.println("Numder = "+counter);
    }
    
    
    public void reportInSpecificAvg() {
        Node curr = head;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a Specific Avg : ");
        double avg = s.nextDouble();
        while (curr != null) {
            if (curr.getStd().getAvg() >= avg) {
                System.out.println(curr.getStd().toString());
            }
            curr = curr.getNext();
        }
    }

    
    
    public void reportInSpecificLevel() {
        Node curr = head;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a Specific level : ");
        int level = s.nextInt();
        while (curr != null) {
            if (curr.getStd().getLevel() == level) {
                System.out.println(curr.getStd().toString());
            }
            curr = curr.getNext();
        }
    }

    
    
    public void reportStdWhoIsGraduated() {
        Node curr = head;
        while (curr != null) {
            if (curr.getStd().isIsGraduated()) {
                System.out.println(curr.getStd().toString());
            }
            curr = curr.getNext();
        }
    }

    
    
    public void reportStdWho_HasInstallments() {
        Node curr = head;
        while (curr != null) {
            if (curr.getStd().getInstallments() > 0.0) {
                System.out.println(curr.getStd().toString());
            }
            curr = curr.getNext();
        }
    }

    
    
    public void repotrs() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter type of reports ");
        System.out.println("    1.Reports about all students");
        System.out.println("    2.Reports about students in specific Address");
        System.out.println("    3.Reports about students in specific AVG");
        System.out.println("    4.Reports about students in specific Level");
        System.out.println("    5.Reports about students Who is Graduated");
        System.out.println("    6.Reports about students Who has Installments");
        System.out.println("    7.Reports about students in specific name");
        int choice = s.nextInt();
        if (choice == 1) {
            reportsAll();
        } else if (choice == 2) {
            reportInSpecificAddress();
        } else if (choice == 3) {
            reportInSpecificAvg();
        } else if (choice == 4) {
            reportInSpecificLevel();
        } else if (choice == 5) {
            reportStdWhoIsGraduated();
        } else if (choice == 6) {
            reportStdWho_HasInstallments();
        } else if (choice == 7) {
            reportInSpecificName();
        }
    }
    
    
    
    public Node getNode (int i){
        int index=0;
        Node curr =head;
        while(curr!=null){
            if(index==i){
                return curr;
            }else{
                curr=curr.getNext();
                index++;
            }
        }
        return null;
    }
    
    
    
    public void swap(int x, int y){
        Node currX=getNode(x);
        Node currY=getNode(y);
        Node prevX=getNode(x-1);
        Node prevY=getNode(y-1);
        // swap the next between 2 previse
        if(prevX!=null){
            prevX.setNext(currY);
        }else{
            head=currY;
        }
        
        if(prevY!= null){
            prevY.setNext(currX);
        }else{
            head=currX;
        }
        
        // swap the next between 2 curr
        Node temp =currX.getNext();
        currX.setNext(currY.getNext());
        currY.setNext(temp);
        
        // TO Keep Tail
        if(tail==currY){
            tail=currX;
        }else if(tail==currX) {
            tail=currY;
        }
    }

    
    
    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
    
    
    Node sortedMerge(Node x, Node y) {
        if (x == null) {
            return y;
        } else if (y == null) {
            return x;
        } else {
            if (x.getStd().getId() <= y.getStd().getId() ) {
                x.setNext( sortedMerge(x.getNext(), y));
                return x;
            } else {
                y.setNext(sortedMerge(x, y.getNext()));
                return y;
            }
        }
    }
    // Splitting the linked list node, 
    // And combine node using sorted merge function
  
    Node mergeSort(Node node) {
        if (node == null || node.getNext() == null) {
            return node;
        } else {
            // Find middle node
            Node middle = algorithm.getMiddle(node);
            // Sort right sublist
            Node right = mergeSort(middle.getNext());
            // split the linked list
            middle.setNext(null);
            // Sort left sublist
            Node left = mergeSort(node);
            return sortedMerge(left, right);
        }
    }
    // Handles the request to perform merge sort

    
    
    public void sort() {
        if (this.head == null) {
            return;
        }
        // Set new head
        this.head = mergeSort(this.head);
        Node node = this.head;
        // Find last node
        while (node != null && node.getNext() != null) {
            // visit to next node
            node = node.getNext();
        }
        // Set new tail
        this.tail = node;
    }
    
}
