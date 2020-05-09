package main.java.designpatterns.factory;

public class SimpleFactory {


    public static class PizzaStore {
        SimplePizzaFactory factory;

        public PizzaStore(SimplePizzaFactory factory) {
            this.factory = factory;
        }

        public Pizza orderPizza(String type) {
            Pizza pizza;
            pizza = factory.createPizza(type);
//            pizza.prepare();
//            pizza.bake();
//            pizza.cut();
//            pizza.box();
            return pizza;
        }
    }

    public static class SimplePizzaFactory {
        public Pizza createPizza(String type) {
            Pizza pizza = null;
            switch (type) {
                case "cheese":
                    pizza = new CheesePizza();
                    break;
                case "pepperoni":
                    pizza = new PepperoniPizza();
                    break;
                case "veggie":
                    pizza = new VeggiePizza();
                    break;
            }
            return pizza;
        }
    }

    public abstract static class Pizza {
        String type = "unknown";


    }

    public static class CheesePizza extends Pizza {
        public CheesePizza() {
            this.type = "Cheese Pizza";
        }
    }

    public static class PepperoniPizza extends Pizza {
        public PepperoniPizza() {
            this.type = "Pepperoni Pizza";
        }
    }

    public static class VeggiePizza extends Pizza {
        public VeggiePizza() {
            this.type = "Veggie Pizza";
        }
    }

}
