cmpe273-assignment2
===================
Used REST, Mustache, ActiveMQ, STOMP, JavaScript, Jquery, STOMP Protocol...etc to implement following scenario:

1. Multiple Library report books lost and sends message using STOMP protocol to ActiveMQ - Queue  
2. Procurement service will get message from Queue and send it to Third Party service - Publisher via POST using Jetty Client 
3. Procurement service will GET newly ordered books using Jetty client. 
4. Procurement Service will publish books received to ActiveMQ TOPIC using STOMP protocol as per the category of Books 
5.Both Library Service (A and B) have subscribe to Topic (A- all books , B- only books falls under computer Topic 
6.  When message received both library will update its inventory

Run-time Dependency
===========================
Apollo (new ActiveMQ) message broker running at:
54.215.210.214  (Master)
54.219.156.168  (Slave)
Publisher APIs at
http://54.215.210.214:9000/orders (Master)
http://54.219.156.168:9000/orders (Slave)
