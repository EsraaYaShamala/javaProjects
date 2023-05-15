package finalproject;

import java.util.Scanner;

public class FinalProject {

    public static void main(String[] args) {

        Students std01 = new Students(1, "Yasser", "KanYounis", 88.3, 4, false, 0.0);
        Students std02 = new Students(2, "Reem", "KanYounis", 90, 4, true, 0.0);
        Students std03 = new Students(3, "Yasser", "KanYounis", 89.3, 2, false, 115.0);
        Students std04 = new Students(4, "Esraa", "KanYounis", 98.3, 1, false, 0.0);
        Students std05 = new Students(5, "Yahea", "KanYounis", 83.3, 1, false, 0.0);
        Students std06 = new Students(6, "Alaa", "KanYounis", 85.3, 2, false, 255.0);
        Students std07 = new Students(7, "Mohamed", "KanYounis", 95.3, 4, true, 0.0);
        Students std08 = new Students(8, "Sara", "KanYounis", 91.3, 3, false, 480.0);
        Students std09 = new Students(9, "Lina", "KanYounis", 92.3, 3, false, 0.0);
        Students std10 = new Students(10, "Ola", "KanYounis", 82.3, 2, false, 0.0);

        LinkedList link = new LinkedList();
        link.mainMenu(std01, 1);
        link.mainMenu(std02, 2);
        link.mainMenu(std03, 3);


    }
  

}
