package utils.collect;

import utils.collect.entity.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bz
 * @date 2021/1/11
 *
 *  java 8 实现复杂比较
 *
 *  首先按照 id 进行升序排列，其实id 相同按照 age 降序排列
 */
public class Map2Compare {

    public static void main(String[] args) {

        List<Student> studentList = Arrays.asList(
                new Student(10,"张三",25),
                new Student(20,"李四",5),
                new Student(20,"王五",30),
                new Student(20,"赵六",22),
                new Student(30,"韩企",29)
        );

        studentList = studentList.parallelStream()
                .sorted(Comparator.comparing(Student::getId).thenComparing(Student::getAge,Comparator.reverseOrder()))
                .collect(Collectors.toList());

        studentList.forEach(e->System.out.println(e));
    }
}
