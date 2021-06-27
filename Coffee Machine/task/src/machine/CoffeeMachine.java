package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need: ");
        int numOfCoffees = sc.nextInt();
        System.out.printf("For %d cups of coffee you will need:\n", numOfCoffees);
        System.out.printf("%d ml of water\n", numOfCoffees * 200);
        System.out.printf("%d ml of milk\n", numOfCoffees * 50);
        System.out.printf("%d g of coffee beans\n", numOfCoffees * 15);
    }
}
