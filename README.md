SuperParser
====
[![Build Status](http://ci.myuplay.com/buildStatus/icon?job=SuperParser)](http://ci.myuplay.com/job/SuperParser/)


Folder Structure
----
HST and STS files should be contained in a file structure similar to the following:

```
*  = Wildcard
/  = Folder delimiter (Might vary on your system but we accept `/\:|.` characters.
[] = Group of characters
|  = Or 
#  = A number of any length
```

`*/[M|A]#/M#/*`

Where the first group `[M|A]#` is the condition parameter. It can start with M or A followed by a number.

The second is a map `M#` and should be under the condition directory.

The program will always find a condition if it starts with A no matter where it is placed.
Because maps also start with M then the only way to find a M condition is if it contains maps.
The program will check to see if a folder is M and if the containing folder is also an M then it is a condition and the M below is a map.

This program will always overwrite the map and condition with the last folders it finds! Repeating a folder structure will always cause it to
set the map or condition to the last folder that matches the expected patern in the path.

Ex: "/M1/A1/M2/M3"

Will result in Condition = M2 and Map = M3


HST Output Format
----
|Map | Condition | Participant# | Action# | Generation | Score | Action | Appliance | Location | Location Type | Final Location | Final Location Type | Extra  |
|----|-----------|--------------|---------|------------|-------|--------|-----------|----------|---------------|----------------|---------------------|--------|
| M# | A or M #  | #            | #       | #          | #     | String | #         | (#, #)   | String        | (#, #)         | String              | String |

Extra is additional info and is usually `'Local Station'`.

If map or condition weren't identified by the folder structure, the output will be '?' in their respective cell.

If participant can't be identified then it will default to '0'.

HST Stats Output Format
----
| Last Action | Last Action by Same Vehicle | Vehicle | Action | Last distance traveled by vehicle |
|-------------|-----------------------------|---------|--------|-----------------------------------|
| #           | #                           | #       | String | ## (Distance with decimal)        |

To be clear the last distance traveled by vehicle column will track the distance that a vehicle traveled in its last action.
This does not mean the distance it is about to travel from its current action! This is useful for tracking and relating
the time between actions on a vehicle and how far it had to travel before it would become idle.

Stats data is available in the tree dropdowns on a per file basis. This is not available in a concatenated format. (Yet)

STS Output Format
----
Map | Condition | Participant | Trial | Performance Score (3) | Generations | Forest (3) | Pasture (3) |Clearing (3) | House (3) | Total Consumable Landscape (3) | Commands Made | Vehicle	Idle | Moving | Fighting | Filling | Treating | Destroyed | Manual | Auto | Resources Used

_This was not in table format due to length_

Any columns with (#) mean that the data spans # columns with the same data in various forms.
The most common form will be (3) and will match the original STS files.

If map, condition, or trial weren't identified by the folder/file structure, the output will be '?' in their respective cell.


