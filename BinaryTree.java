package com.edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class BinaryTree<E> implements Serializable{

    //INNER NODE CLASS
    protected static class Node<E> implements Serializable{

        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node(E data){
            this.data = data;
            left = null;
            right = null;
        }

        public String toString(){
            return data.toString();
        }
    }


    //DATA FIELDS
    protected Node<E> root;


    //CONSTRUCTORS
    public BinaryTree(){
        root = null;
    }

    protected BinaryTree(Node<E> root){
        this.root = root;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree){
        root = new Node<>(data);
        if (leftTree != null)
            root.left = leftTree.root;
        else
            root.left = null;
        if (rightTree != null)
            root.right = rightTree.root;
        else
            root.right = null;
    }


    public BinaryTree<E> getLeftSubtree(){
        if (root != null && root.left != null)
            return new BinaryTree<>(root.left);
        else
            return null;
    }

    public BinaryTree<E> getRightSubtree(){
        if (root != null && root.right != null)
            return new BinaryTree<>(root.right);
        else
            return null;
    }

    public E getData(){
        return root.data;
    }

    public boolean isLeaf(){
        return (root.left == null && root.right == null);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        toString(root, 1, sb);
        return sb.toString();
    }


    private void toString(Node<E> node, int depth,
                          StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null || node.data == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }


    public void preOrderTraverse(BiConsumer<E, Integer> consumer){
        preOrderTraverse(root, 1, consumer);
    }


    private void preOrderTraverse(Node<E> node, int depth,
                                  BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        } else {
            consumer.accept(node.data, depth);
            preOrderTraverse(node.left, depth + 1, consumer);
            preOrderTraverse(node.right, depth + 1, consumer);
        }
    }




    public static BinaryTree<String> readBinaryTree(Scanner scan){

        String data = scan.nextLine().trim();
        if (data.equals("null"))
            return null;
        else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }

}
