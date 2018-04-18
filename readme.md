# Welcome to the world of curve.
Here you could change your curve from which consisted of any point starting with letter like s, l, v or h to standard bezier point starting with alphbet letter c.

```java
e.g.
FROM:
	M20,10
	V100
	H90
	L140,
	150
	H180
TO：
	M20.0,10.0
	c0.0,0.0,0.0,90.0,0.0,90.0
	c0.0,0.0,70.0,0.0,70.0,0.0
	c0.0,0.0,50.0,50.0,50.0,50.0
	c0.0,0.0,40.0,0.0,40.0,0.0
```



The maven project of illusion is main code which build up in spring-boot framework. Therefore it could run in any cloudy. 



But some issue were in there yet we need to deal with. One of them is that it still cannot process curve which contains any absolute c coordinate, ``e.g.M10,10Ca,b,c,d,e,f``. We fix it out next time.

Welcome yangyangbeiqiu.

nothing
