给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，
判断二者是否相等，并返回结果。 # 代表退格字符。
class Solution {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s1=fun(S);
        Stack<Character> t1=fun(T);
        while((!s1.empty())&&(!t1.empty())){
            if(s1.pop()!=t1.pop())
               return false;
        }
        return s1.empty()&&t1.empty();
    }

    private Stack<Character> fun(String S){
        Stack<Character> stack=new Stack();
        for(int i=0;i<S.length();i++){
            char c=S.charAt(i);
            if(c=='#'){
                if(!stack.empty())
                   stack.pop();
            }else{
                stack.push(c);
            }
        }
        return stack;
    }
}


你现在是棒球比赛记录员。
给定一个字符串列表，每个字符串可以是以下四种类型之一：
1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。

每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
你需要返回你在所有回合中得分的总和。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/baseball-game
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack=new Stack();
        for(int i=0;i<ops.length;i++){
            if(ops[i].equals("C")){
                stack.pop();
            }else if(ops[i].equals("D")){
                stack.push(2*stack.peek());
            }else if(ops[i].equals("+")){
                int a=stack.pop();
                int b=stack.pop();
                stack.push(b);
                stack.push(a);
                stack.push(a+b);
            }else{
                if(ops[i].charAt(0)=='-'){
                    stack.push(-fun(ops[i],1));
                }else{
                    stack.push(fun(ops[i],0));
                }
            }
        }

        int res=0;
        while(!stack.empty()){
            res+=stack.pop();
        }
        return res;
    }

    private int fun(String s,int l){
        int sum=0;
        for(;l<s.length();l++){
            int a=s.charAt(l)-'0';
            sum=sum*10+a;
        }
        return sum;
    }
}