#!/bin/sh
#
# DB must exist, to create DB first :
#-------------------------------------
# CREATE DB :  mysqladmin -u root -p create databasename
# CREATE user and grant privileges:
# mysql> CREATE USER 'some_user'@'localhost' IDENTIFIED BY 'some_pass';
# mysql> GRANT ALL PRIVILEGES ON databasename.* TO 'some_user'@'localhost' WITH GRANT OPTION;
#
#

mysql -u root -h localhost cmsdb < ../dbDump/08_24_13_14_33_49.sql
