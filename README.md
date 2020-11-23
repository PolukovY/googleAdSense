Ad serving system (simplified clone of Google Ad Sense)

[Technical documentation!](https://docs.google.com/document/d/1uOnr_pkHL4iL8PdwIAwUt5dRsGWQ04eAHBzp9tFBQO4/edit?usp=sharing)


How to start:

 - run all dependency Kafka & zookeeper via docker
    ```
    cd infa & docker-compose up -d 
    ```
 - Start all the services (find the Application class and starts them):
 <img src="https://i.ibb.co/s5Sg6nd/Screen-Shot-2020-11-23-at-15-15-55.png">
 
 - Open statistic service http://localhost:9999/statistic/api/v1/details
 <img src="https://i.ibb.co/ZxP44w8/Screen-Shot-2020-11-23-at-15-26-38.png">
 
 - Open website http://localhost:8085/
<img src="https://i.ibb.co/wrwGV2N/Screen-Shot-2020-11-23-at-15-28-15.png">
The second refresh
<img src="https://i.ibb.co/h7N8fG8/Screen-Shot-2020-11-23-at-15-35-04.png">


The advert is on the blue block. These items changed accordingly in random order.
 
 - Open statistic service to see that count increased.
 
 Try to play with it, click on the link and refresh the page, and see that statistic displayed with correct counts.






