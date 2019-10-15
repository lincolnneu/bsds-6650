## lab 5 Report

### Create table 1
`mysqlslap -uroot -p --concurrency=1 --iterations=1 --create='create_table1.sql' --query='fake_records.sql' --delimiter=";" --no-drop`
![create table 1](01_create_table1.png)
![See myslap table](02_myslap_table.png)
![Content of myslap table](03_myslap_table_content.png)


`mysqlslap -uroot -p --concurrency=1 --iterations=1 --create='create_table2.sql' --query='fake_records.sql' --delimiter=";" --no-drop`
![Content of myslap table](04_create_table2.png)
