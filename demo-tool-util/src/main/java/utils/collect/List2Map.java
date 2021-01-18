package utils.collect;

import utils.collect.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bz
 * @date 2021/1/5
 */
public class List2Map {

    public static void main(String[] args) {


        List<Student>  list = Arrays.asList(
                new Student(1,"",20),
                new Student(2,"",30),
                new Student(3,"",40),
                new Student(4,"",50)
        );

        Map<Integer,Integer>  studentMap = list.stream()
                .collect(Collectors.toMap(Student::getId,Student::getAge));


        for(Map.Entry<Integer,Integer> entry : studentMap.entrySet()){
            System.out.println(entry);
        }

    }



}
