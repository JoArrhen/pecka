interface Sound {
  void makeSound();
}

class Animal implements Sound{
  Animal friend = null;

  public void makeSound() {
    System.out.println("Animal sound");
  }

  public Animal getFriend() {
    return friend;
  }
}
class Cat extends Animal {}
class Dog extends Animal {}

public class Alias {
  public static void main(String[] args) {
    Animal a = new Cat();
    a = new Dog();
    a.friend = new Cat(); 

    System.out.println(a.getFriend().friend);
  }
}