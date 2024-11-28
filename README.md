# Sandbox use case - Early Warning
# Log Service
Microservice which is responsible for showing the background process to the user.
This service is receiving the log data from the Information Mediator, which are presented to the user in real time.
Purpose of this service is to present, in step by step manner, what is happening in the background once the broadcast is published to the end user.
This way the whole flow from the broadcast creation to the delivery of the early warning to the end user is demonstrated. 
For testing the Early Warning system, check the user service for credentials:

# Tech stack
Microservice is using the following: <br>
Java 17, Spring Boot Reactive, H2, Kafka pub/sub, Docker/Helm charts for deployment.

# Note
In order to test the Early Warning system, make sure to run the rest of the services and frontend.