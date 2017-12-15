# TestJDBCConnection

## Background

How many times while installing solution to production we need to test each elements of the solution one by one ahead of the first end to end test. I has heppened one more time to me lately. I suggested several time to a young gun to drop enough tooling in production to help troubleshooting the basics. A good example of that is how to test jdbc connection. In that respect I have built this snippet of code that can take any argument depending of the database you need to test. In my case I needed to established connectivity with MS SQL Server and it was not an option to drop sql workbench or any "online" sql interpreter. The best and lighter way was to build a small java application with very little dependency in such a way it will be easy to validate the whole JDBC.

### connectURL tool

```
# assuming you have the testjdbc-0.0.jar in the current directory
>java  -cp "./testjdbc-0.0.jar" connectURL -help
usage: connectUrl
 -help               print this message
 -sql <sql>          <sql command to execute>
 -url <connecturl>   connection URL :
                     jdbc:sqlserver://<hostname>:<port>;databaseName=<db
                     name>;user=<user>;password=<password>
                     
                     

```


### Example testing MS SQL server

```
# by default the system will test sql with the default query as highlighed :
# SELECT TOP 10 * FROM BPASessionLog_NonUnicode
>java -cp "/Users/rumi/Downloads/sqljdbc_6.0/enu/jre8/sqljdbc42.jar:./testjdbc-0.0.jar" connectURL -url "jdbc:sqlserver://localhost:1433;databaseName=BluePrismTraining;user=rumi;password=******"
Connection String :jdbc:sqlserver://localhost:1433;databaseName=BluePrismTraining;user=rumi;password=******
Default SQL Command : SELECT TOP 10 * FROM BPASessionLog_NonUnicode
Start HelloWorld
Start HelloWorld
Action1 HelloWorld
Start HelloWorld
Action1 HelloWorld
Start HelloWorld
Action1 HelloWorld
Start HelloWorld
Start HelloWorld
Start HelloWorld

```

### Example testing MS SQL Server with a specific query

```



```


