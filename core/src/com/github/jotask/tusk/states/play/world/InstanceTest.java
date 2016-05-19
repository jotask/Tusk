package com.github.jotask.tusk.states.play.world;

public class InstanceTest {

    static abstract class Animal { public abstract void sound();}

    static class Mamifer extends Animal{
        @Override
        public void sound() {
            System.out.println("mamifer sound");
        }
    }
    static class Bird extends Animal{
        @Override
        public void sound() {
            System.out.println("bird sound");
        }
    }
    static class Fish extends Animal{
        @Override
        public void sound() {
            System.out.println("fish sound");
        }
    }

    static class Dog extends Mamifer{ }
    static class Cat extends Mamifer { }

    static class Parrot extends Bird{ }

    static class Whale extends Fish{ }

    static class Contact{

        public Body a, b;

        public Contact(Object a, Object b) {
            this.a = new Body(a);
            this.b = new Body(b);
        }
    }

    static class Body{
        public Object obj;
        public Body(Object obj){
            this.obj = obj;
        }
    }

    public static void handleCollision(Contact contact){
        Object a = contact.a.obj;
        Object b = contact.b.obj;

        if ((a instanceof Mamifer && b instanceof Bird) || (a instanceof Bird && b instanceof Mamifer)){
            System.out.println("Mamifer and Bird");
            Bird bird;
            Mamifer mamifer;
            if(a instanceof Mamifer) {
                mamifer = (Mamifer) a; bird = (Bird)b;
            }else{
                mamifer = (Mamifer) b; bird = (Bird)a;
            }
            bird.sound();
            mamifer.sound();
        }

        if ((a instanceof Mamifer && b instanceof Fish) || (a instanceof Fish && b instanceof Mamifer)){
            System.out.println("Mamifer and Fish");
            Fish fish;
            Mamifer mamifer;
            if(a instanceof Mamifer) {
                mamifer = (Mamifer) a; fish = (Fish)b;
            }else{
                mamifer = (Mamifer) b; fish = (Fish)a;
            }
            fish.sound();
            mamifer.sound();
        }

    }

    public static void main(String[] args) {

        Cat cat = new Cat();
        Parrot parrot = new Parrot();
        Whale whale = new Whale();


        Contact c1 = new Contact(cat, parrot);
        Contact c2 = new Contact(cat, whale);
        Contact c3 = new Contact(parrot, whale);

        handleCollision(c1);
        handleCollision(c2);
        handleCollision(c3);

    }


}