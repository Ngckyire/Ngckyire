1. 描述：20

给定整数 *n* ，按照如下规则打印从 *1* 到 *n* 的每个数：

- 如果这个数被3整除，打印`fizz`。
- 如果这个数被5整除，打印`buzz`。
- 如果这个数能同时被`3`和`5`整除，打印`fizz buzz`。
- 如果这个数既不能被 `3` 整除也不能被 `5` 整除，打印数字`本身`。

样例

**样例 1：**

输入：

```
n = 15
```

输出：

```java
[
  "1", "2", "fizz",
  "4", "buzz", "fizz",
  "7", "8", "fizz",
  "buzz", "11", "fizz",
  "13", "14", "fizz buzz"
]
```

挑战

你是否可以只用一个 `if` 来实现？


```java
public class Solution {

  /**

   \* @param n: An integer

   \* @return: A list of strings.

   */

  public List<String> fizzBuzz(int n) {

​      List<String>arr = new ArrayList<>();
     for(int i=1;i<=n;i++){
            if(i%3==0&&i%5==0){
                arr.add("fizz buzz");
            }
            else if(i%5==0){
               arr.add("buzz");
            }
            else if(i%3==0){
                arr.add("fizz");
            }
            else {
                String y = ""+i ;
                arr.add(y);
            }
        }
    return arr ;
    }
  }

}
```


2. 描述20

   输入一个英文句子，将每个单词的第一个字母改成大写字母

   这个句子可能并不是一个符合语法规则的句子。句子长度小于等于`100`。除了句子的开头，其余字母均为小写

   样例：

   **样例1**

   ```
   输入: s =  "i want to get an accepted"
   输出: "I Want To Get An Accepted"
   ```

   **样例2**

   ```
   输入: s =  "i jidls    mdijf  i  lsidj  i p l   "
   输出: "I Jidls    Mdijf  I  Lsidj  I P L   "
   ```


   ```java
   public class Solution {
    /**
     * @param s: a string
     * @return: a string after capitalizes the first letter
     */
    public String capitalizesFirst(String s) {
          String result="";
              for(int i=0;i<s.length();i++){
                  if(s.charAt(i)>='a'&&s.charAt(i)<='z') {
                      if (i == 0 || s.charAt(i - 1) == ' ') {
                          result += (char) (s.charAt(i) - 32);
                      } else {
                          result += s.charAt(i);
                      }
                  }
                  else {
                      result += s.charAt(i);
                  }
              }
              return result;
          }
    }
   }

3. 描述18

   输入一个任意正整数 `n`，判断它是否为质数，如果是质数就输出 ‘是质数‘，否则输出 ’不是质数‘。
   质数：除了1和它本身之外,不能被其他数整除的正整数称为质数。

   

   样例一

   当 `n = 5` 时，程序执行打印出的结果为：

   ```java
   5 is a prime number
   ```

   样例二

   当 `n = 300` 时，程序执行打印出的结果为：

   ```java
   300 is not a prime number
   ```
```java
   import java.util.Scanner;

   

   public class Main {

     public static void main(String[] args) {

   ​    Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int i;
        for( i=2;i<n;i++){
            if(n%i==0){
                System.out.println(n+"is not a prime number");break;
            }
        }
        if(i==n){
            System.out.println(n+"is a prinme number");
        }

       }

   ​    

     }

   }
```

4. 描述15

   韩信点兵，三人一组余两人，五人一组余三人，七人一组余四人，请问最少需要多少士兵。
   本题无需进行任何输入，请在 `// write your code here` 处编写合适的代码。

   ```java
   public class Main {
   public static void main(String[] args) {
   	for(int i=0;;i++){
               if(i%3==2&&i%5==3&&i%7==0){
                   System.out.println("最少需要"+i+"人");break;
               }
           }
   	
   }
   }

5.18

​	请简述Integer与int，String与StringBuffer与StringBuilder的区别和特点

1. 