# 用法说明

## 一、ngram

参考论文：http://www.aclweb.org/anthology/C10-1005

### 1.1 WordJaccardNgram

![](http://empfs.bs2dl.yy.com/bWQtMTQ4OTM5ODE1NTQ4NzVfMTQ4OTM5ODE1NTQ4OQ.png)

**算法简介**：

假设有两个集合：集合A和集合B，每个集合内各自包含若干元素，那么Jaccard计算两个集合的相似性就是用两个集合元素的交集作为分子，两个集合元素的并集作为分母，除一下就完事。因为交集代表了两个集合相同部分的子集合，并集代表了两个集合加起来总共有多少个元素，用相同部分占总元素数比例来代表集合相似性，两者重叠部分越多则两个集合越相似。

**示列** ： 

句子A:苹果电脑的价格

句子B：苹果ipad的价格

​    两个句子转换为2-gram后，形成如下集合：

​      SetA={苹果，果电，电脑，脑的，的价，价格}

​      SetB={苹果，果ipad，ipad的，的价，价格}

两个集合求交集得出相同语言片段个数为：3.（即为：苹果，的价，价格）

两个集合求并集得出分母大小为：8

所以这两个句子的相似度为:3/8=0.375

**参考文献**

<http://uhra.herts.ac.uk/bitstream/handle/2299/2114/902216.pdf;jsessionid=11177E6A9862D9230203197CF13EE950?sequence=1>

## 1.2 WordNGramContainment

**算法简介**

如1.1类似，假设有两个集合：集合A和集合B，每个集合内各自包含若干元素，那么Jaccard计算两个集合的相似性就是用两个集合元素的交集作为分子，集合A元素个数作为分母。

示列：

**示列** ： 

句子A:苹果电脑的价格

句子B：苹果ipad的价格

​    两个句子转换为2-gram后，形成如下集合：

​      SetA={苹果，果电，电脑，脑的，的价，价格}

​      SetB={苹果，果ipad，ipad的，的价，价格}

两个集合求交集得出相同语言片段个数为：3.（即为：苹果，的价，价格）

集合A的元素个数得出分母大小为：6

所以这两个句子的相似度为:3/6=0.5

**参考文献**

<http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=666900>

## 1.2 CharacterNGram

**算法简介**

假设字符串A和字符串B，和预设权重m：字符串A和B按照char进行分词，然后n-gram，得到集合A和集合B，其中集合A和B为Set集合，且记录元素重复个数n，分别对集合A和B和预设m相乘，得到俩组系数值Array[A]和Array[B],然后在对Array[A]和Array[B]进行余弦计算：

示列：

字符串A：abbbc

字符串B:   bbccd

预设值：m为{a,b}=0.5,{b,b}=2,{b,c}=0.1

1. char分词之后得到：

字符分词A： {a,b,b,b,c}

字符分词B:   {b,b,c,c,d}

2. 2-gram

   集合A：{a,b},{b,b}，{b,b},{b,c},{c,a}

   集合B：{b,b},{b,c},{c,c},{c,d},{d,b}

合并之后：

Set<A> , {a,b}=1,{b,b}=2,{b,c}=1,{c,a}=1

Set<B>,{b,b}=1,{b,c}=1,{c,c}=1,{c,d}=1,{d,b}=1

与预设值相乘

Array[A]:  [1 * 1 , 2 * 2 , 1 * 0.1 ,1*0] = [1,4,0.1,0,0]

Array[A]:  [1 * 2 , 1 *  0.1 , 1 * 0 ,1*0 ,1 * 0] =[2,0.1,0,0,0]

计算余弦

cos(a * b)=(  1 * 2+4 *  0.1 + 0.1 * 0 + 0 * 0 + 0 * 0) / sqrt( 1 + 16 + 0.01 + 4 + 0.01) = 

**参考文献**

<http://aclweb.org/anthology-new/C/C10/C10-1005.pdf>

论文：* A. Barrón-Cedeño, P. Rosso, E. Agirre, and G. Labaka: Plagiarism* Detection across Distant Language Pairs, in: Proceedings of COLING,* pages 37-45, 2010.



## 二 String

### 1.1 简单字符串匹配类型 

#### 1.BoundedSubstringMatchComparator

**算法简介：**

假设字符串A和字符串B，假若字符串（A，B）与字符串（B，A）的头部或者尾部相同则匹配，否则不匹配

示列：

字符串A：name

字符串B：helloName

字符串C：hello

则A和B = 1 ，B 和C = 1，A和C = 0

#### 2.ExactStringMatchComparator

**算法简介**

假设字符串A和字符串B，假若字符串完全相同相同则匹配，否则不匹配

示列：

字符串A：name

字符串B：hello_name

字符串C：hello

则A和B = 1 ，B 和C =0，A和C = 0

#### 3.SubstringMatchComparator

**算法简介**

假设字符串A和字符串B，假若字符串相互属于则匹配，否则不匹配

**示列**

字符串A：name

字符串B：kkkk_name_kjljl

字符串C：hello

则A和B = 1 ，B 和C =0，A和C = 0

### 1.2 CosineSimilarity

**算法简介**

余弦相似度是利用两个n维向量的夹角余弦值来计算它们相似度的方法,经常用于在文本挖掘中比较文档.给定两个向量的属性(维度)A和B,它们的夹角*θ*,余弦相似度以点积和向量长度表示为

![](http://empfs.bs2dl.yy.com/bWQtMTQ4OTQwNDUyMzEwNTRfMTQ4OTQwNDUyMzEwNQ.png)

与欧几里德距离类似，基于余弦相似度的计算方法也是把用户的喜好作为n-维坐标系中的一个点，通过连接这个点与坐标系的原点构成一条直线（向量），两个用户之间的相似度值就是两条直线（向量）间夹角的余弦值。因为连接代表用户评分的点与原点的直线都会相交于原点，夹角越小代表两个用户越相似，夹角越大代表两个用户的相似度越小。同时在三角系数中，角的余弦值是在[-1, 1]之间的，0度角的余弦值是1，180角的余弦值是-1。

![](http://empfs.bs2dl.yy.com/bWQtMTQ4OTQwNTI4NzM3MDZfMTQ4OTQwNTI4NzM3MA.png)



示列：

字符集合A ： test ，String1

字符集合AB： test ，String2

则转化为三维度向量：A（1,1,0），B(1,0,1) 

A与B之间的夹角余弦为（1 * 1 + 1 * 0 + 0 * 1）/sqrt(1+1+1+1)= 0.5

### 1.3 GreedyStringTiling

 **算法简介**

   GST（Greedy String Tiling）算法是一种贪婪串匹配算法，这一算法对两个字符串进行贪婪式搜索以找出最大公有子串，它需要对要计算的两个字符串进行多次搜索，每次找出当前字符串中未“标注”部分的最长公共子串，并把找出的最长公共子串“标注”为已使用，避免最大匹配重复使用。

**示列**

 字符串A：“abcdefghijklmuvwxyz”

字符串B：“ijkabclmdefghpq”

操作如下：

​       （1）假如我们设最小匹配长度为2。第一次搜寻过程，先找到abc，此时最大匹配长度是3，之后找到defgh，因此它的长度大于3，所以此时最大匹配长度5，之后找到ijk，由于其长度小于5，放弃，最后是lm，其长度同样小于当前最大匹配长度5，放弃。

​       （2）将（1）中找到的最大匹配子串”标注为已使用“，重复（1）的过程，不过不再对”已标注子串“搜索，直到（1）中找到的最大匹配子串的长度为设置的最小匹配长度。

用途：GST算法在信息检索、文本编辑、信息提取等领域有重要的应用。

### 1.4 LevenshteinComparator

 **算法简介**

Levenshtein 距离，又称编辑距离，指的是两个字符串之间，由一个字符串转换成另一个字符串所需的最少编辑操作次数。许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。