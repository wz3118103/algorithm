package datastructure.array.wz;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class ExactTypeList<E> {

    private Node<E> head;

    public void add(E value) {
        value = Objects.requireNonNull(value, "value is null");

        Node<E> node = new Node<>(value, null);
        if (head == null) {
            head = node;
        } else {
            // 头插
            node.next = head;
            head = node;
        }
    }

    public boolean contains(E value) {
        Node<E> node = head;
        while (node != null) {
            if (node.value.equals(value)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private static final class Node<E> {

        E value;

        Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }


    private static class Foo {
        // empty class
    }

    private static final class Bar extends Foo {
        // empty class
    }


    public static void main(String[] args) {
        ExactTypeList<? extends Foo> sExactTypeList;
        List<? extends Foo> sNormalList;


        ExactTypeList<Bar> list = new ExactTypeList<>();
        list.add(new Bar());
        sExactTypeList = list;

        List<Bar> normalList = new ArrayList<>();
        normalList.add(new Bar());
        sNormalList = normalList;

//        System.out.println(sExactTypeList.contains(new Bar())); // 编译不通过

        System.out.println(sNormalList.contains(new Bar()));
    }
}