public class Stduent {
    String name;
    int age;
    int sno;

    public Stduent(String name, int age, int sno) {
        this.name = name;
        this.age = age;
        this.sno = sno;
    }

    public Stduent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    @Override
    public String toString() {
        return "Stduent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sno=" + sno +
                '}';
    }
}
