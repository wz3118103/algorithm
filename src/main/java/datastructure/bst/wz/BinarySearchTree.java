package datastructure.bst.wz;

import java.util.Comparator;

/**
 * 注意事项：
 * 1）结点需要一个parent结点，否则数据结构实现的时候会比较麻烦
 * 2）注意transplant(u, v)是建立u.parent与v之间的关系；在处理删除的时候，要注意几对父子之间的关系
 * 3）插入的时候，可以简化
 * @param <E>
 */
public class BinarySearchTree<E> {
    Node<E> root;

    Comparator<? super E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public void add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        Node<E> newNode = new Node<>(e, null, null, null);
        if (root == null) {
            root = newNode;
            return;
        }

        Node<E> parent = null;
        Node<E> node = root;

        // 另一种写法
        if(comparator != null) {
            while (node != null) {
                parent = node;
                if (comparator.compare(e, node.element) < 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            newNode.parent = parent;
            if (comparator.compare(e, parent.element) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        } else {
            Comparable<? super E> key = (Comparable<? super E>) e;
            while (node != null) {
                parent = node;
                if (key.compareTo(node.element) < 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            newNode.parent = parent;
            if (key.compareTo(parent.element) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
//        if (comparator != null) {
//            while (node != null) {
//                if (comparator.compare(e, node.element) < 0) {
//                    if (node.left == null) {
//                        node.left = newNode;
//                        newNode.parent = node;
//                        break;
//                    }
//                    node = node.left;
//                } else {
//                    if (node.right == null) {
//                        node.right = newNode;
//                        newNode.parent = node;
//                        break;
//                    }
//                    node = node.right;
//                }
//            }
//        } else {
//            while (node != null) {
//                Comparable<? super E> key = (Comparable<? super E>) e;
//                if (key.compareTo(node.element) < 0) {
//                    if (node.left == null) {
//                        node.left = newNode;
//                        newNode.parent = node;
//                        break;
//                    }
//                    node = node.left;
//                } else {
//                    if (node.right == null) {
//                        node.right = newNode;
//                        newNode.parent = node;
//                        break;
//                    }
//                    node = node.right;
//                }
//            }
//        }
    }

    public Node<E> find(Object e) {
        if (e == null) {
            throw new NullPointerException();
        }

        Node<E> node = root;
        if (comparator != null) {
            while (node != null) {
                if (comparator.compare(node.element, (E) e) > 0) {
                    node = node.left;
                } else if (comparator.compare(node.element, (E) e) < 0) {
                    node = node.right;
                } else {
                    return node;
                }
            }
        } else {
            while (node != null) {
                Comparable<? super E> key = (Comparable<? super E>) node.element;
                if (key.compareTo((E) e) > 0) {
                    node = node.left;
                } else if (key.compareTo((E) e) < 0) {
                    node = node.right;
                } else {
                    return node;
                }
            }
        }
        return null;
    }

    public boolean remove(Object e) {
        if (e == null) {
            throw new NullPointerException();
        }

        Node<E> node = find(e);
        if (node == null) {
            return false;
        }

        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            Node<E> replace = minimum(node.right);
            if (replace.parent != node) {
                transplant(replace, replace.right);
                // 建立replace与node.right之间的父子关系
                replace.right = node.right;
                replace.right.parent = replace;
            }
            // 建立replace与node父亲之间的父子关系
            transplant(node, replace);
            // 建立replace与node.left之间的父子关系
            replace.left = node.left;
            replace.left.parent = replace;
        }
        node.left = null;
        node.right = null;
        node.element = null;

        return true;
    }

    private void transplant(Node<E> u, Node<E> v) {
        if (u.parent == null) {
            root = v;
        } else {
            if (u.parent.left == u) {
                u.parent.left = v;
            } else {
                u.parent.right = v;
            }
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    public Node<E> minimum(Node<E> node) {
        assert node != null;
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void inOrder(Node<E> e) {
        if (e != null) {
            inOrder(e.left);
            System.out.printf(e.element + " ");
            inOrder(e.right);
        }
    }

    public void print() {
        inOrder(root);
        System.out.println();
    }

    public Node<E> root() {
        return root;
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> left, Node<E> right, Node<E> parent) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(78);
        bst.add(63);
        bst.add(86);
        bst.add(21);
        bst.add(43);
        bst.add(80);
        bst.add(97);
        bst.add(56);
        bst.add(90);
        bst.add(32);
        bst.add(70);
        bst.print();

        bst.remove(78);
        bst.print();
        bst.remove(90);
        bst.print();

    }
}
