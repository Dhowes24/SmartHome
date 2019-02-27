# SmartHome
Software Development Project

Smart Home Revision

The Smart Home intelligently manages all IoT devices within a home to optimize the user experience, security, and energy efficiency. The system can be programmed to manage smart thermostats or security systems in basic ways. All other devices, such as TVs, washers, dryers, and coffee makers can interface with the Smart Home API to be controlled remotely. All residents of a home can connect a smartphone or smartwatch. The Smart Home uses these devices to track the location of the residents. When returning home, this could be used to enter the house without a key. When at home, this data could be used to optimize heating and cooling, or automatically switch the destination of a Netflix stream to the nearest TV.

Narrative:
In order to lower heating costs
As a homeowner
I want to manage temperature control

Scenario 1:
Given homeowner is on main app page
When they select ‘thermal management’
Then a list of thermal options appears
When they select ‘set a timer’
Then a timer creation interface appears
When they submit
Then the system logs this and will turn heat on and off at the times they specified

Scenario 2:
Given homeowner is on main app page
When they select ‘thermal management’
Then a list of thermal options appears
When they select ‘auto manage’
The system will track user locations and manage heat based on this data

Use Case:
https://drive.google.com/file/d/1lyczQb34oAUq6Xd4ioJHbeB8wJe7C72u/view?usp=sharing
