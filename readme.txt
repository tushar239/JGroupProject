JGroups Tutorial

http://www.jgroups.org/manual/html/ch01.html


Group communication uses the terms group and member. Members are part of a group. 
In the more common terminology, a member is a node and a group is a cluster. 
We use these terms interchangeably.

A node is a process, residing on some host. A cluster can have one or more nodes belonging to it. 
There can be multiple nodes on the same host, and all may or may not be part of the same cluster. 
Nodes can of course also run on different hosts.

JGroups is toolkit for reliable group communication. 
Processes can join a group, send messages to all members or single members and receive messages from members in the group. 
The system keeps track of the members in every group, and notifies group members when a new member joins, or an existing member leaves or crashes. 
A group is identified by its name. Groups do not have to be created explicitly; when a process joins a non-existing group, that group will be created automatically. 
Processes of a group can be located on the same host, within the same LAN, or across a WAN. A member can be part of multiple groups.


http://www.jgroups.org/tutorial-3.x/html/ch02.html#d0e168


- run 2-3 instances of the SimpleChat.java program. All the instances will be registered to same cluster 'ChatCluster'. JGroups uses JChannel to connect to a cluster, send and receive messages. 
- viewAccepted and receive methods are available through ReceiverAdapter class. SimpleChat is extending this class.
- whenever you add or remove any instance of the program, all running program's viewAccepted(View new_view) method will be called.
- We are making ReceiverAdapter class both sender and receiver of the messages. Message sent by one instance of the program will be received by all other instances of the program in receive(Message msg) method.
- All the instances of SimpleChat.java are registering to the same cluster 'ChatCluster'. Instances registered in same cluster only receives the messages sent by other program instances.
- AnotherSimpleChat's channel is connected to another cluster 'ChatCluster2'. Message sent by AnotherSimpleChat won't be received by SimpleChat because bother are registered to different clusters.
   
