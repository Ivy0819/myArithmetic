package com.swufestu.myarithmetic;

import static com.swufestu.myarithmetic.Ran.getChildPlace;
import static com.swufestu.myarithmetic.Ran.getNumber;
import static com.swufestu.myarithmetic.Ran.getOperator;

import java.util.ArrayList;

public class BinaryTree
{

    private TreeNode root;
    private int num;
    private ArrayList<TreeNode> opeList = new ArrayList<TreeNode>();

    public BinaryTree(int num){
        this.num = num;
    }

    public int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num = num;
    }

    public void setTreeNode(TreeNode root){
        this.root = root;
    }


    /**
     * 获取最终的表达式，必须在CalAndVal()方法后调用
     *
     * @return str
     */
    public String toString(){
        String str = root.toString();
        str = str.substring(1, str.length()-1);
        return str;
    }

    /**
     * 计算并验证表达式
     *
     * @return result
     */
    public String CalAndVal(){
        return root.getResult();
    }

    /**
     * 计算二叉树的深度(层数)
     *
     * @return deep
     */
    public int getDeep(){
        int i = this.num;
        int deep = 2;
        while(i/2 > 0){
            deep++;
            i /= 2;
        }
        return deep;
    }

    /**
     * 生成二叉树
     *
     */
    public void createBTree(int max){
        TreeNode lchild, rchild, lnode, rnode;

        if(num == 1){
            lchild = new TreeNode(String.valueOf(getNumber(max)), null, null);
            rchild = new TreeNode(String.valueOf(getNumber(max)), null, null);
            root = new TreeNode(String.valueOf(getOperator()), lchild, rchild);
        }
        else{
            int num1 = 0;
            int n = getDeep() - 3;
            boolean[] place = getChildPlace(num);
            root = new TreeNode(String.valueOf(getOperator()), null, null);
            opeList.add(root);

            for(int i = 0; i < n; i++){
                for(int j = 0; j < (int)Math.pow(2, i); j++, num1++){
                    lchild = new TreeNode(String.valueOf(getOperator()), null, null);
                    rchild = new TreeNode(String.valueOf(getOperator()), null, null);
                    opeList.get(j + num1).setChild(lchild, rchild);
                    opeList.add(lchild);
                    opeList.add(rchild);
                }
            }

            for(int i = 0; i < place.length; i++){
                if(place[i]){
                    lnode  = new TreeNode(String.valueOf(getNumber(max)), null, null);
                    rnode  = new TreeNode(String.valueOf(getNumber(max)), null, null);
                    if(i%2 == 0){
                        lchild = new TreeNode(String.valueOf(getOperator()), lnode, rnode);
                        opeList.add(lchild);
                        opeList.get(num1).setLchild(lchild);
                    }
                    else{
                        rchild = new TreeNode(String.valueOf(getOperator()), lnode, rnode);
                        opeList.add(rchild);
                        opeList.get(num1).setRchild(rchild);
                    }
                }
                else{
                    if(i%2 == 0){
                        lchild = new TreeNode(String.valueOf(getNumber(max)), null, null);
                        opeList.get(num1).setLchild(lchild);
                    }
                    else{

                        rchild = new TreeNode(String.valueOf(getNumber(max)), null, null);
                        opeList.get(num1).setRchild(rchild);
                    }
                }
                num1 = num1 + i%2;
            }
        }
    }
}








