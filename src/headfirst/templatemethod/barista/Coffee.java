package headfirst.templatemethod.barista;

public class Coffee extends CaffeineBeverage {
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

//	void boilWater() {
//		System.out.println("Boiling water LiYazhou");
//	}
}
