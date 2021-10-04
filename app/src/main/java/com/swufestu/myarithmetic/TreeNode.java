package com.swufestu.myarithmetic;

public class TreeNode {

    private String str;
    private TreeNode rchild = null;
    private TreeNode lchild = null;

    public TreeNode(String str){
        this.str = str;
    }

    public TreeNode(String str, TreeNode lchild, TreeNode rchild){
        this.str = str;
        this.rchild = rchild;
        this.lchild = lchild;
    }

    public void setChild(TreeNode lchild, TreeNode rchild){
        this.lchild = lchild;
        this.rchild = rchild;
    }

    public TreeNode getRchild() {
        return rchild;
    }
    public void setRchild(TreeNode rchild) {
        this.rchild = rchild;
    }
    public TreeNode getLchild() {
        return lchild;
    }
    public void setLchild(TreeNode lchild) {
        this.lchild = lchild;
    }

    public String getStr(){
        return str;
    }

    /**
     * 获取每个节点的运算结果，并检验除法
     * 1)除数为0
     * 2)不能整除
     * 出现以上两种情况的话将该运算符转换成其他三种运算符
     *
     * @return result
     */
    public String getResult(){
        if(hasChild()){
            switch(str){
                case "+":
                    return String.format("%.2f",Double.valueOf(getLchild().getResult()) + Double.valueOf(getRchild().getResult()));
                case "-":
                    return String.format("%.2f",Double.valueOf(getLchild().getResult()) - Double.valueOf(getRchild().getResult()));
                case "*":
                    return String.format("%.2f",Double.valueOf(getLchild().getResult()) * Double.valueOf(getRchild().getResult()));
                case "/":
                    if(getRchild().getResult().equals("0")){
                        while(str.equals("/")){
                            str = String.valueOf(Ran.getOperator());
                        }
                        return this.getResult();
                    }
//                    else if(Integer.parseInt(getLchild().getResult()) % Integer.parseInt(getRchild().getResult()) != 0){   //不能被整除换操作符
//                        while(str.equals("/")){
//                            str = String.valueOf(Ran.getOperator());
//                        }
//                        return this.getResult();
//                    }
                    else
                        return String.format("%.2f",Double.valueOf(getLchild().getResult())/Double.valueOf(getRchild().getResult()));
                    // return String.valueOf(Integer.parseInt(getLchild().getResult()) / Integer.parseInt(getRchild().getResult()));
            }
        }
        return str;
    }

    /**
     * 先对每个运算式添加括号，然后根据去括号法则，去掉多余的子式的括号
     *
     * @return string
     */
    public String toString(){
        String Lstr = "", Rstr = "", Str = "";
        if(hasChild()){
            //右子树如果有孩子，说明右子树是一个表达式，而不是数字节点。
            if(getRchild().hasChild()){
                //判断左邻括号的运算符是否为'/'
                if(str.equals("/")){
                    //获取右子树的表达式，保留括号
                    Rstr = getRchild().toString();
                }
                //判断左邻括号的运算符是否为'*'或'-'
                else if(str.equals("*") || str.equals("-")){
                    //判断op是否为'+'或'-'
                    if(getRchild().str.equals("+") || getRchild().str.equals("-")){
                        Rstr = getRchild().toString();
                    }
                    else{
                        //获取右子树的表达式，并且去括号
                        Rstr = getRchild().toString().substring(1, getRchild().toString().length()-1);
                    }
                }
                else{
                    //右子树除此之外都是可以去括号的。
                    Rstr = getRchild().toString().substring(1, getRchild().toString().length()-1);
                }
            }
            else{
                Rstr = getRchild().str;
            }
            //左子树的情况同右子树类似
            if(getLchild().hasChild()){
                if(str.equals("*") || str.equals("/")){
                    if(getLchild().str.equals("+") || getLchild().str.equals("-")){
                        Lstr = getLchild().toString();
                    }
                    else{
                        Lstr = getLchild().toString().substring(1, getLchild().toString().length()-1);
                    }
                }
                else{
                    Lstr = getLchild().toString().substring(1, getLchild().toString().length()-1);
                }
            }
            else{
                Lstr = getLchild().str;
            }
            //获取当前的运算式，并加上括号
            Str = "(" + Lstr + str + Rstr + ")";
        }
        else{
            //若没有孩子，说明是数字节点，直接返回数字
            Str = str;
        }
        return Str;
    }

    public boolean hasChild(){
        if(lchild == null && rchild == null)
            return false;
        else
            return true;
    }
}
