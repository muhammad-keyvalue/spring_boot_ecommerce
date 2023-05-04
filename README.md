## Description
​
An order-management service..

### Build the application
​
```
  mvn clean install
```
​
​
### Run the application and dev dependencies
​
```
  docker-compose up
```
​
### Run migrations

  ```
    docker-compose --profile flyway run --rm  migrate-up
  ```

### Rolling back migrations

  ```
    docker-compose --profile flyway run --rm  migrate-down
  ```
​