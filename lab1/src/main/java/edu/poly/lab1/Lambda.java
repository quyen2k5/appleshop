package edu.poly.lab1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.poly.lab1.bean.Student;

public class Lambda {
    static List<Student> list = Arrays.asList(    // tạo list chứa các sinh viên
                new Student("Nguyen Van Tao", true, 7.5),
                new Student("Tran Thi thu Huong", false, 5.5),
                new Student("Pham Duc Cuong", true, 9.5),
                new Student("Le Thi My Hong", false, 6.5),
                new Student("Dinh Huynh Duc", false, 8.0)
            );


    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
                                
    }
        private static void demo1() {
            List<Integer> list = Arrays.asList(1,9,4,7,5,2); // tạo list chứa các số nguyên
            list.forEach(n -> System.out.println(n));  // dùng vòng lặp chuyển từng số nguyên và xuất số nguyên ra
        }
        
        private static void demo4() {
            demo4Inter o = (x) -> System.out.println(x);
            o.m1(2025);
        }
        private static void demo3() {
            Collections.sort(list, (sv1,sv2) -> -sv1.getMarks().compareTo(sv2.getMarks()));  // so sánh 2 đối tượng nếu lớn hơn 0 thì nó sẽ tăng nhỏ hơn 0 thì sẽ giảm dần 
            list.forEach(sv -> {              // xóa dấu - thì nó sẽ sắp xếp tăng dần
                System.out.println(">> Name: " + sv.getName());
                System.out.println(">> Marks: " + sv.getMarks());
                System.out.println();
            });
        }
        
            // làm việc với đối tượng thông qua các cái list
        private static void demo2() {  
            list.forEach(sv -> {   // dùng vòng lặp duyệt qua nhưng mỗi phần tử của chúng ta bây h nó là 1 sinh viên nen gọi các phương thức get để lấy sinh viên ra
                System.out.println(">> Name: " + sv.getName());
                System.out.println(">> Marks: " + sv.getMarks());
                System.out.println();
            });
        }
}

@FunctionalInterface
interface demo4Inter{
    void m1(int x);
    default void m2() {}
    public static void m3() {}
}
