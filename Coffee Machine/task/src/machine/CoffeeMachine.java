package machine;

import java.util.Scanner;
enum CoffeeType {
    ESPRESSO(1, 250, 0, 16, 4),
    LATTE(2, 350, 75, 20, 7),
    CAPPUCCINO(3, 200, 100, 12, 6);

    private final int ID;
    private final int WATER_REQUIRED;
    private final int MILK_REQUIRED;
    private final int COFFEE_BEANS_REQUIRED;
    private final int COST;

    CoffeeType(int ID, int WATER_REQUIRED, int MILK_REQUIRED, int COFFEE_BEANS_REQUIRED, int COST) {
        this.ID = ID;
        this.WATER_REQUIRED = WATER_REQUIRED;
        this.MILK_REQUIRED = MILK_REQUIRED;
        this.COFFEE_BEANS_REQUIRED = COFFEE_BEANS_REQUIRED;
        this.COST = COST;
    }
    public static CoffeeType getCoffeeType(int id) {
        for (CoffeeType coffee : CoffeeType.values()) {
            if (coffee.ID == id) {
                return coffee;
            }
        }
        throw new RuntimeException();
    }

    public int getWATER_REQUIRED() {
        return WATER_REQUIRED;
    }

    public int getMILK_REQUIRED() {
        return MILK_REQUIRED;
    }

    public int getCOFFEE_BEANS_REQUIRED() {
        return COFFEE_BEANS_REQUIRED;
    }

    public int getCOST() {
        return COST;
    }
}
class CoffeeMachineFunc {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    public CoffeeMachineFunc(int watter, int milk, int coffeeBeans, int cups, int money) {
        this.water = watter;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
    }
    public void add(int watter, int milk, int coffeeBeans, int cups) {
        this.water += watter;
        this.milk += milk;
        this.coffeeBeans += coffeeBeans;
        this.cups += cups;
    }
    public void stats() {
        System.out.printf("The coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "$%d of money",
                water, milk, coffeeBeans, cups, money);
    }
    public void buy(CoffeeType coffeeType) {
        water -= coffeeType.getWATER_REQUIRED();
        milk -= coffeeType.getMILK_REQUIRED();
        coffeeBeans -= coffeeType.getCOFFEE_BEANS_REQUIRED();
        money += coffeeType.getCOST();
        --cups;
    }
    public void checkAvailable(CoffeeType coffeeType){
        boolean isWatterEnough = coffeeType.getWATER_REQUIRED() <= water;
        boolean isMilkEnough = coffeeType.getMILK_REQUIRED() <= milk;
        boolean isCoffeeBeansEnough = coffeeType.getCOFFEE_BEANS_REQUIRED() <= coffeeBeans;

        if (isWatterEnough && isMilkEnough && isCoffeeBeansEnough && cups != 0) {
            System.out.println("I have enough resources, making you a coffee!");
            buy(coffeeType);
        } else {
            System.out.println("I can't make coffee");
        }

    }
    public void take() {
        System.out.printf("I gave you $%d\n", money);
        this.money = 0;
    }
}
public class CoffeeMachine {
    private static final Scanner sc = new Scanner(System.in);
    private static void fill(CoffeeMachineFunc coffeeMaker) {
        System.out.println("Write how many ml of water you want to add: ");
        int water = sc.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        int milk = sc.nextInt();
        System.out.println("Write how many cups of you want to add: ");
        int coffeeBeans = sc.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        int cups = sc.nextInt();
        coffeeMaker.add(water, milk, coffeeBeans, cups);
    }
    public static void main(String[] args) {
        CoffeeMachineFunc coffeeMaker = new CoffeeMachineFunc(400, 540, 120, 9, 550);
        boolean goOn = true;
        while(goOn) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = sc.next();
            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    String input = sc.next();
                    if (!input.equals("back")) {
                        coffeeMaker.checkAvailable(CoffeeType.getCoffeeType(Integer.parseInt(input)));
                    }
                    break;
                case "fill":
                    fill(coffeeMaker);
                    break;
                case "take":
                    coffeeMaker.take();
                    break;
                case "remaining":
                    coffeeMaker.stats();
                    break;
                case "exit":
                    goOn = false;
                    break;
                default:
                    break;
            }
        }
    }
}
