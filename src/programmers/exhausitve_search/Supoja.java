package programmers.exhausitve_search;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;
/*
https://programmers.co.kr/learn/courses/30/lessons/42840?language=java
프로그래머스 완전탐색 수포자
 */
public class Supoja {

    List<Person> personList;

    private void initPersonList(){

        personList = new LinkedList<>();
        personList.add(new Person(1, new int[]{1,2,3,4,5}));
        personList.add(new Person(2, new int[]{2,1,2,3,2,4,2,5}));
        personList.add(new Person(3, new int[]{3,3,1,1,2,2,4,4,5,5}));
    }

    public int[] solution(int[] answers) {

        initPersonList();
        personList.forEach(person -> person.setAnswerNum(answers));
        OptionalInt max = personList.stream()
                .mapToInt(Person::getAnswerNum)
                .max();

        return personList.stream()
                .filter(person -> person.getAnswerNum() == max.orElse(-1))
                .mapToInt(Person::getPersonNo)
                .sorted()
                .toArray();
    }

    static class Person{

        int personNo;
        int[] pattern;
        int answerNum;

        Person(int personNo, int[] pattern){
            this.personNo = personNo;
            this.pattern = pattern;
            this.answerNum = 0;
        }

        void setAnswerNum(int[] answers){

            int patternLen = pattern.length;
            for(int i = 0, len = answers.length ; i<len ; i++){
                if(answers[i] == pattern[i%patternLen]){
                    answerNum++;
                }
            }
        }

        int getAnswerNum(){
            return answerNum;
        }

        int getPersonNo(){
            return personNo;
        }
    }

    public static void main(String[] args) {

        Supoja supoja = new Supoja();
        int[] answers = {1,3,2,4,2};
        int[] answer = supoja.solution(answers);
        for(int i : answer){
            System.out.println(i);
        }
    }
}
