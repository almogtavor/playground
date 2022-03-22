

Run this command to create a new topic into which we’ll write and read some test messages.

`docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
--create \
--topic quickstart`


Run this command. You’ll notice that nothing seems to happen—fear not! It is waiting for your input.


`docker exec --interactive --tty broker \
kafka-console-producer --bootstrap-server broker:9092 \
--topic quickstart`
Type in some lines of text. Each line is a new message.
`this is my first kafka message
hello world!
this is my third kafka message. I’m on a roll :-D`
When you’ve finished, press Ctrl-D to return to your command prompt.

To read:
`docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server broker:9092 \
--topic quickstart \
--from-beginning`


`docker-compose down`