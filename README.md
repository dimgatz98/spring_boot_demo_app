# spring_boot_demo_app

>Note: This project requires jdk 17.

### Install PostgreSQL
``` bash
# Install postgres using apt
$ sudo apt install -y postgresql postgresql-contrib postgresql-client
# Login to postgres as the default user
$ sudo -u postgres psql
```
And in the postgres client:
```postgres
-- add a password as it will be needed to connect with jpa
postgres=# \password postgres;
-- create and grant permissions to the db which is used from the project
postgres=# CREATE DATABASE api_users;
postgres=# GRANT ALL PRIVILEGES ON DATABASE api_users TO postgres;
```

>Note: You can modify the db and users used in the present project from src/main/resources/application.properties.

### Start app
After building the project you can start it by running DemoApplication.