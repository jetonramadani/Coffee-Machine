package machine;

import java.util.Scanner;
class CoffeeMachineFunc {
    private static final int WATER_REQUIRED = 200;
    private static final int MILK_REQUIRED = 50;
    private static final int COFFEE_BEANS_REQUIRED = 15;
    private int water;
    private int milk;
    private int coffeeBeans;

    public CoffeeMachineFunc(int watter, int milk, int coffeeBeans) {
        this.water = watter;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
    }


    private int canMakeCoffees() {
        return Math.min(water / WATER_REQUIRED,
                Math.min(milk / MILK_REQUIRED, coffeeBeans / COFFEE_BEANS_REQUIRED));
    }

    public void printAvailable(int numOfCoffees){
        boolean isWatterEnough = numOfCoffees * WATER_REQUIRED <= water;
        boolean isMilkEnough = numOfCoffees * MILK_REQUIRED <= milk;
        boolean isCoffeeBeansEnough = numOfCoffees * COFFEE_BEANS_REQUIRED <= coffeeBeans;
        String res;
        if (isWatterEnough && isMilkEnough && isCoffeeBeansEnough) {
            water -= WATER_REQUIRED * numOfCoffees;
            milk -= MILK_REQUIRED * numOfCoffees;
            coffeeBeans -= COFFEE_BEANS_REQUIRED * numOfCoffees;
            res = "Yes, I can make that amount of coffee";
            int left = canMakeCoffees();
            if (left != 0) {
                res += String.format("(and even %d more than that)", left);
            }
        } else {
            res = String.format("No, I can make only %d cup(s) of coffee", canMakeCoffees());
        }
        System.out.println(res);
    }
}
public class CoffeeMachine {
    private static final Scanner sc = new Scanner(System.in);
    private static CoffeeMachineFunc readDataAndCreate() {
        System.out.println("Write how many ml of water the coffee machine has: ");
        int water = sc.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int milk = sc.nextInt();
        System.out.println("Write how many cups of coffee you will need: ");
        int coffeeBeans = sc.nextInt();
        return new CoffeeMachineFunc(water, milk, coffeeBeans);
    }
    public static void main(String[] args) {
        CoffeeMachineFunc coffeeMaker = readDataAndCreate();
        System.out.println("Write how many cups of coffee you will need: ");
        coffeeMaker.printAvailable(sc.nextInt());
    }
}
