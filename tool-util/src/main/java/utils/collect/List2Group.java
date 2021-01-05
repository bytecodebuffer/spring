package utils.collect;

import utils.collect.entity.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author bz
 * @date 2021/1/5
 */
public class List2Group {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student(1,"1.jpg",20),
                new Student(2,"2.jpg",30),
                new Student(2,"3.jpg",40),
                new Student(1,"4.jpg",50)
        );

        Map<Integer, List<Student>> listMap = list.stream()
                .collect(Collectors.groupingBy(Student::getId,Collectors.toList()));


        for(Map.Entry<Integer, List<Student>> entry: listMap.entrySet()){
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
        }





    }
}
