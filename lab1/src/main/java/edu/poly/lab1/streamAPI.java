package edu.poly.lab1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.poly.lab1.bean.Student;

public class streamAPI {
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
            private static void demo4() {
                double average = list.stream()  // tính điểm trung bình
                    .mapToDouble(sv -> sv.getMarks())
                    .average().getAsDouble();
                System.out.println("average: " + average);

                double sum = list.stream() // tính tổng điểm
                    .mapToDouble(sv -> sv.getMarks())
                    .sum();
                System.out.println("sum: " + sum);

                double min_marks = list.stream() // tính điểm số thấp nhất
                    .mapToDouble(sv -> sv.getMarks())
                    .min().getAsDouble();
                System.out.println("min_marks: " + min_marks);

                boolean all_passed = list.stream().allMatch(sv -> sv.getMarks() >= 5);  // xem sinh viên có điểm lớn hơn hoặc bằng 5 hay không
                System.out.println("all_passed: " + all_passed);

                Student min_sv = list.stream() // tính tích lũy sinh viên thấp nhất
                    .reduce(list.get(0), (min,sv) -> sv.getMarks() < min.getMarks() ? sv:min);
                System.out.println("min_sv: " + min_sv.getName());
            }
        
            private static void demo3() {
                List<Student> result = list.stream()  // lấy ra stream student
                    .filter(sv -> sv.getMarks() >= 7 )  //lọc lấy những sinh viên lớn hơn 7
                    .peek(sv -> sv.setName(sv.getName().toUpperCase())) //duyệt các sinh viên đó và đổi tên thành chữ hoa
                    .collect(Collectors.toList()); // rồi thu thập nó kết quả cho ta 1 stream student

                result.forEach(sv -> { // xuất những sinh viên lớn hơn 7 ra
                    System.out.println(">> Name: " + sv.getName());
                    System.out.println(">> Marks: " + sv.getMarks());
                    System.out.println();
                }); 
            }
            private static void demo2() {
                List<Integer> list = Arrays.asList(1,9,4,7,5,2);
                List<Double> result = list.stream() //Stream<Integer>
                    .filter(n -> n % 2 == 0)  //Stream<Integer>
                    .map(n -> Math.sqrt(n))
                    .peek(d -> System.out.println(d))
                    .collect(Collectors.toList());
            }
            private static void demo1() {
                Stream<Integer> stream1 = Stream.of(1,9,4,7,5,2);
                stream1.forEach(n -> {
                    System.out.println(n);
                });
                List<Integer> list = Arrays.asList(1,9,4,7,5,2);
                // Stream<Integer> stream2 = list.stream();
                list.stream().forEach(n -> {
                    System.out.println(n);
                });
            }
}
